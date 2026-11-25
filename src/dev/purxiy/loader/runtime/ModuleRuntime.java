package dev.purxiy.loader.runtime;

import dev.purxiy.loader.api.ModuleEntrypoint;
import dev.purxiy.loader.security.ModuleEnvelope;
import dev.purxiy.loader.security.ModuleVerifier;
import dev.purxiy.loader.security.VerifiedModule;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import javax.crypto.SecretKey;

public final class ModuleRuntime implements AutoCloseable {
   private final OwnerThread owner = new OwnerThread();
   private final ModuleVerifier verifier;
   private final FrameRouter router;
   private final BiConsumer<String, Double> metrics;
   private final Map<String, ModuleRuntime.Handle> active = new HashMap<>();
   private boolean closed;
   private boolean transitioning;

   public ModuleRuntime(ModuleVerifier verifier, FrameRouter router, BiConsumer<String, Double> metrics) {
      this.verifier = Objects.requireNonNull(verifier);
      this.router = Objects.requireNonNull(router);
      this.metrics = Objects.requireNonNull(metrics);
   }

   public ModuleRuntime.Handle activate(String id, String build, ModuleEnvelope envelope, SecretKey key, Map<String, String> settings) throws Exception {
      ModuleRuntime.Handle var7;
      try (VerifiedModule verified = this.verifier.verify(envelope, key, id, build)) {
         var7 = this.activate(verified, settings);
      }

      return var7;
   }

   public ModuleRuntime.Handle activate(VerifiedModule verified, Map<String, String> settings) throws Exception {
      this.owner.check();
      this.router.requireIdle();
      String id = verified.spec().id();
      if (this.closed) {
         throw new IllegalStateException("Runtime is closed");
      } else if (this.transitioning) {
         throw new IllegalStateException("A module lifecycle transition is already in progress");
      } else if (this.active.containsKey(id)) {
         throw new IllegalStateException("Module already active: " + id);
      } else {
         this.transitioning = true;

         ModuleRuntime.Handle var11;
         try {
            VerifiedModule var4 = verified;

            try {
               MemoryModuleClassLoader loader = new MemoryModuleClassLoader(verified, ModuleEntrypoint.class.getClassLoader());
               ModuleScope scope = null;

               try {
                  scope = new ModuleScope(this.router, loader, settings, (name, value) -> this.metrics.accept(id + "." + name, value));
                  Class<?> type = Class.forName(verified.spec().entrypoint(), true, loader);
                  ModuleEntrypoint entrypoint = type.asSubclass(ModuleEntrypoint.class).getConstructor().newInstance();
                  AutoCloseable instance = Objects.requireNonNull(entrypoint.start(scope), "Module must return cleanup action");
                  ModuleRuntime.Handle handle = new ModuleRuntime.Handle(id, loader, scope, instance);
                  this.active.put(id, handle);
                  var11 = handle;
               } catch (LinkageError | AssertionError | Exception var29) {
                  Throwable e = var29;

                  try {
                     if (scope != null) {
                        scope.close();
                     }
                  } catch (Exception var27) {
                     e.addSuppressed(var27);
                  } finally {
                     loader.close();
                  }

                  throw var29;
               }
            } catch (Throwable var30) {
               if (verified != null) {
                  try {
                     var4.close();
                  } catch (Throwable var26) {
                     var30.addSuppressed(var26);
                  }
               }

               throw var30;
            }

            if (verified != null) {
               verified.close();
            }
         } finally {
            this.transitioning = false;
         }

         return var11;
      }
   }

   public int activeCount() {
      this.owner.check();
      return this.active.size();
   }

   public boolean isActive(String id) {
      this.owner.check();
      return this.active.containsKey(id);
   }

   public String moduleOfClass(String className) {
      this.owner.check();

      for (ModuleRuntime.Handle handle : this.active.values()) {
         if (handle.owns(className)) {
            return handle.id();
         }
      }

      return null;
   }

   void requireIdle() {
      this.owner.check();
      this.router.requireIdle();
      if (this.transitioning) {
         throw new IllegalStateException("A module lifecycle transition is already in progress");
      }
   }

   @Override
   public void close() throws Exception {
      this.owner.check();
      this.router.requireIdle();
      if (this.transitioning) {
         throw new IllegalStateException("A module lifecycle transition is already in progress");
      } else {
         this.closed = true;
         int failed = 0;

         for (ModuleRuntime.Handle handle : this.active.values().toArray(ModuleRuntime.Handle[]::new)) {
            try {
               handle.close();
            } catch (LinkageError | AssertionError | Exception var7) {
               failed++;
            }
         }

         if (failed != 0) {
            throw new IllegalStateException("Failed to close " + failed + " module(s)");
         }
      }
   }

   public final class Handle implements AutoCloseable {
      private final String id;
      private MemoryModuleClassLoader loader;
      private ModuleScope scope;
      private AutoCloseable instance;

      private Handle(String id, MemoryModuleClassLoader loader, ModuleScope scope, AutoCloseable instance) {
         this.id = id;
         this.loader = loader;
         this.scope = scope;
         this.instance = instance;
      }

      public String id() {
         return this.id;
      }

      public boolean isClosed() {
         ModuleRuntime.this.owner.check();
         return this.loader == null;
      }

      WeakReference<ClassLoader> loaderReference() {
         ModuleRuntime.this.owner.check();
         return new WeakReference<>(this.loader);
      }

      int definedClassCount() {
         ModuleRuntime.this.owner.check();
         return this.loader == null ? 0 : this.loader.definedCount();
      }

      boolean owns(String className) {
         ModuleRuntime.this.owner.check();
         return this.loader != null && this.loader.owns(className);
      }

      @Override
      public void close() throws Exception {
         ModuleRuntime.this.owner.check();
         ModuleRuntime.this.router.requireIdle();
         if (this.loader != null) {
            if (ModuleRuntime.this.transitioning) {
               throw new IllegalStateException("A module lifecycle transition is already in progress");
            } else {
               ModuleRuntime.this.transitioning = true;
               int failed = 0;

               try {
                  try {
                     this.scope.detach();
                  } catch (LinkageError | AssertionError | Exception var10) {
                     failed++;
                  }

                  try {
                     this.instance.close();
                  } catch (LinkageError | AssertionError | Exception var9) {
                     failed++;
                  }

                  try {
                     this.scope.close();
                  } catch (LinkageError | AssertionError | Exception var8) {
                     failed++;
                  }
               } finally {
                  this.loader.close();
                  this.loader = null;
                  this.scope = null;
                  this.instance = null;
                  ModuleRuntime.this.active.remove(this.id, this);
                  ModuleRuntime.this.transitioning = false;
               }

               if (failed != 0) {
                  throw new IllegalStateException("Module cleanup failed in " + failed + " stage(s): " + this.id);
               }
            }
         }
      }
   }
}
