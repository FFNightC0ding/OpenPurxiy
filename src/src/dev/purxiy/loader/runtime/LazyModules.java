// Sourced By DevFFNightmare
package dev.purxiy.loader.runtime;

import dev.purxiy.loader.auth.AccessDeniedException;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.LongSupplier;

public final class LazyModules implements AutoCloseable {
   private final Consumer<AccessDeniedException> denied;
   private final OwnerThread owner = new OwnerThread();
   private final ModuleRuntime runtime;
   private final ModuleSource source;
   private final Clock clock;
   private final LongSupplier ticks;
   private static final long MAX_GRANT_NANOS = TimeUnit.MINUTES.toNanos(5L);
   private final ThreadPoolExecutor workers;
   private final Map<String, LazyModules.Slot> slots = new HashMap<>();
   private final ArrayBlockingQueue<LazyModules.Completed> completed = new ArrayBlockingQueue<>(10);
   private final AtomicBoolean closed = new AtomicBoolean();
   private final AtomicInteger inFlight = new AtomicInteger();
   private long sequence;
   private final ArrayBlockingQueue<LazyModules.Renewed> renewals = new ArrayBlockingQueue<>(10);

   public LazyModules(ModuleRuntime runtime, ModuleSource source, Clock clock) {
      this(runtime, source, clock, ignored -> {});
   }

   public LazyModules(ModuleRuntime runtime, ModuleSource source, Clock clock, Consumer<AccessDeniedException> denied) {
      this(runtime, source, clock, denied, System::nanoTime);
   }

   LazyModules(ModuleRuntime runtime, ModuleSource source, Clock clock, Consumer<AccessDeniedException> denied, LongSupplier ticks) {
      this.denied = Objects.requireNonNull(denied);
      this.runtime = Objects.requireNonNull(runtime);
      this.source = Objects.requireNonNull(source);
      this.clock = Objects.requireNonNull(clock);
      this.ticks = Objects.requireNonNull(ticks);
      this.workers = new ThreadPoolExecutor(2, 2, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(8), task -> {
         Thread thread = new Thread(task, "purxiy-module-worker");
         thread.setDaemon(true);
         thread.setContextClassLoader(LazyModules.class.getClassLoader());
         return thread;
      }, new AbortPolicy());
   }

   private LazyModules.Deadline deadline(Instant until, Instant startedWall, long startedTicks) {
      long remaining;
      try {
         remaining = Duration.between(startedWall, until).toNanos();
      } catch (ArithmeticException var8) {
         remaining = until.isAfter(startedWall) ? MAX_GRANT_NANOS : 0L;
      }

      remaining = Math.max(0L, Math.min(MAX_GRANT_NANOS, remaining));
      return new LazyModules.Deadline(until, startedTicks + remaining, startedTicks + Math.max(0L, remaining - TimeUnit.SECONDS.toNanos(60L)));
   }

   private static boolean due(long now, long target) {
      return now - target >= 0L;
   }

   private void accept(LazyModules.Slot slot, LazyModules.Deadline deadline) {
      slot.until = deadline.until;
      slot.refreshAt = deadline.until.minusSeconds(60L);
      slot.deadlineNanos = deadline.end;
      slot.refreshNanos = deadline.refresh;
   }

   public CompletableFuture<Void> enable(String id, String build, Map<String, String> settings) {
      this.owner.check();
      this.runtime.requireIdle();
      if (this.closed.get()) {
         throw new IllegalStateException("Lazy modules closed");
      } else {
         Map<String, String> values = Map.copyOf(settings);
         if (this.slots.size() >= 128 && !this.slots.containsKey(id)) {
            throw new IllegalStateException("Module request limit");
         } else {
            this.disable(id);
            if (this.inFlight.incrementAndGet() > 10) {
               this.inFlight.decrementAndGet();
               return CompletableFuture.failedFuture(new RejectedExecutionException("Module preparation limit"));
            } else {
               LazyModules.Slot slot = new LazyModules.Slot();
               slot.generation = ++this.sequence;
               slot.future = new CompletableFuture<>();
               slot.build = build;
               this.slots.put(id, slot);

               try {
                  this.workers
                     .execute(
                        () -> {
                           ModuleSource.Prepared prepared = null;
                           Throwable failure = null;
                           Instant startedWall = this.clock.instant();
                           long startedTicks = this.ticks.getAsLong();
                           LazyModules.Deadline deadline = null;

                           try {
                              prepared = this.source.fetch(id, build);
                           } catch (LinkageError | AssertionError | Exception var15) {
                              failure = (Throwable)(var15 instanceof AccessDeniedException access
                                 ? new AccessDeniedException(access.reason())
                                 : new IllegalStateException("Module preparation failed: " + var15.getClass().getSimpleName()));
                           }

                           if (prepared != null) {
                              deadline = this.deadline(prepared.authorizedUntil(), startedWall, startedTicks);
                           }

                           LazyModules.Completed result = new LazyModules.Completed(id, slot.generation, values, prepared, deadline, failure);
                           synchronized (this.completed) {
                              if (this.closed.get() || !this.completed.offer(result)) {
                                 if (prepared != null) {
                                    prepared.close();
                                 }

                                 this.inFlight.decrementAndGet();
                                 slot.future.completeExceptionally(new RejectedExecutionException("Module loader closed or full"));
                              }
                           }
                        }
                     );
               } catch (RejectedExecutionException var7) {
                  this.inFlight.decrementAndGet();
                  this.slots.remove(id);
                  slot.future.completeExceptionally(var7);
               }

               return slot.future;
            }
         }
      }
   }

   public void disable(String id) {
      this.owner.check();
      this.runtime.requireIdle();
      LazyModules.Slot slot = this.slots.remove(id);
      if (slot != null) {
         if (!slot.future.isDone()) {
            slot.future.cancel(false);
         }

         if (slot.handle != null) {
            try {
               slot.handle.close();
            } catch (Exception var4) {
               throw new IllegalStateException("Module stop failed", var4);
            }
         }
      }
   }

   public void pump() {
      this.owner.check();
      this.runtime.requireIdle();

      LazyModules.Renewed result;
      while ((result = this.renewals.poll()) != null) {
         this.inFlight.decrementAndGet();
         LazyModules.Slot slot = this.slots.get(result.id);
         if (slot != null && slot.generation == result.generation) {
            slot.refreshing = false;
            if (result.denial != null) {
               this.disable(result.id);
               this.denied.accept(result.denial);
            } else if (result.deadline != null && result.deadline.until.isAfter(this.clock.instant()) && !due(this.ticks.getAsLong(), result.deadline.end)) {
               this.accept(slot, result.deadline);
            } else {
               slot.refreshAt = this.clock.instant().plusSeconds(15L);
               slot.refreshNanos = this.ticks.getAsLong() + TimeUnit.SECONDS.toNanos(15L);
            }
         }
      }

      LazyModules.Completed completed;
      while ((completed = (LazyModules.Completed)(this.completed.poll())) != null) {
         this.inFlight.decrementAndGet();
         LazyModules.Slot slot = this.slots.get(completed.id);

         try (ModuleSource.Prepared prepared = completed.prepared) {
            if (!this.closed.get() && slot != null && slot.generation == completed.generation) {
               if (slot.future.isCancelled()) {
                  this.slots.remove(completed.id);
               } else if (completed.failure instanceof AccessDeniedException access) {
                  this.slots.remove(completed.id);
                  slot.future.completeExceptionally(access);
                  this.denied.accept(access);
               } else {
                  if (completed.failure != null) {
                     throw new IllegalStateException("Module preparation failed", completed.failure);
                  }

                  if (prepared == null
                     || completed.deadline == null
                     || !this.clock.instant().isBefore(prepared.authorizedUntil())
                     || due(this.ticks.getAsLong(), completed.deadline.end)) {
                     throw new SecurityException("Authorization expired before activation");
                  }

                  slot.handle = this.runtime.activate(prepared.module(), completed.settings);
                  this.accept(slot, completed.deadline);
                  slot.future.complete(null);
               }
            }
         } catch (LinkageError | AssertionError | Exception var9) {
            if (slot != null && slot.generation == completed.generation) {
               this.slots.remove(completed.id);
               slot.future.completeExceptionally(new IllegalStateException("Module activation failed: " + var9.getClass().getSimpleName()));
            }
         }
      }

      for (Entry<String, LazyModules.Slot> entry : List.copyOf(this.slots.entrySet())) {
         LazyModules.Slot slot = entry.getValue();
         if (slot.handle == null || this.clock.instant().isBefore(slot.until) && !due(this.ticks.getAsLong(), slot.deadlineNanos) && !slot.future.isCancelled()
            )
          {
            if (slot.handle != null
               && this.source instanceof ModuleSource.Renewable renewable
               && !slot.refreshing
               && (!this.clock.instant().isBefore(slot.refreshAt) || due(this.ticks.getAsLong(), slot.refreshNanos))) {
               if (this.inFlight.incrementAndGet() > 10) {
                  this.inFlight.decrementAndGet();
               } else {
                  slot.refreshing = true;

                  try {
                     this.workers.execute(() -> {
                        Instant until = null;
                        Instant startedWall = this.clock.instant();
                        long startedTicks = this.ticks.getAsLong();
                        LazyModules.Deadline deadline = null;
                        AccessDeniedException refusal = null;

                        try {
                           until = renewable.renew(entry.getKey(), slot.build);
                        } catch (AccessDeniedException var12x) {
                           refusal = new AccessDeniedException(var12x.reason());
                        } catch (LinkageError | AssertionError | Exception var13x) {
                        }

                        if (until != null) {
                           deadline = this.deadline(until, startedWall, startedTicks);
                        }

                        synchronized (this.completed) {
                           if (this.closed.get() || !this.renewals.offer(new LazyModules.Renewed(entry.getKey(), slot.generation, deadline, refusal))) {
                              this.inFlight.decrementAndGet();
                           }
                        }
                     });
                  } catch (RejectedExecutionException var6) {
                     this.inFlight.decrementAndGet();
                     slot.refreshing = false;
                     slot.refreshAt = this.clock.instant().plusSeconds(15L);
                     slot.refreshNanos = this.ticks.getAsLong() + TimeUnit.SECONDS.toNanos(15L);
                  }
               }
            }
         } else {
            this.disable(entry.getKey());
         }
      }
   }

   public int requestedCount() {
      this.owner.check();
      return this.slots.size();
   }

   public boolean isRequested(String id) {
      this.owner.check();
      return this.slots.containsKey(id);
   }

   int preparingCount() {
      this.owner.check();
      return this.inFlight.get();
   }

   @Override
   public void close() throws Exception {
      this.owner.check();
      this.runtime.requireIdle();
      synchronized (this.completed) {
         this.closed.set(true);

         for (LazyModules.Slot slot : List.copyOf(this.slots.values())) {
            if (!slot.future.isDone()) {
               slot.future.cancel(false);
            }
         }

         LazyModules.Completed result;
         while ((result = this.completed.poll()) != null) {
            if (result.prepared != null) {
               result.prepared.close();
            }
         }
      }

      this.workers.shutdownNow();
      int failed = 0;

      for (String id : List.copyOf(this.slots.keySet())) {
         try {
            this.disable(id);
         } catch (RuntimeException var7) {
            failed++;
         }
      }

      try {
         this.runtime.close();
      } catch (Exception var6) {
         failed++;
      }

      if (this.source instanceof AutoCloseable resource) {
         try {
            resource.close();
         } catch (Exception var5) {
            failed++;
         }
      }

      if (failed != 0) {
         throw new IllegalStateException("Module shutdown failed in " + failed + " stage(s)");
      }
   }

   private record Completed(
      String id, long generation, Map<String, String> settings, ModuleSource.Prepared prepared, LazyModules.Deadline deadline, Throwable failure
   ) {
   }

   private record Deadline(Instant until, long end, long refresh) {
   }

   private record Renewed(String id, long generation, LazyModules.Deadline deadline, AccessDeniedException denial) {
   }

   private static final class Slot {
      long generation;
      ModuleRuntime.Handle handle;
      Instant until;
      Instant refreshAt;
      long deadlineNanos;
      long refreshNanos;
      String build;
      boolean refreshing;
      CompletableFuture<Void> future;
   }
}
