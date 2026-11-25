package dev.purxiy.loader.fabric.client;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.loader.fabric.ModuleState;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import net.minecraft.class_12074;
import net.minecraft.class_12076;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3965;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import net.minecraft.class_6880;
import net.minecraft.class_8110;
import net.minecraft.class_9909;
import net.minecraft.class_9960;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

public final class SkyPipelineRouter {
   public static final SkyPipelineRouter.PipelineList<SkyPipelineRouter.SkyPipelineHook> SKY = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<SkyPipelineRouter.TerrainPassHook> PARTICLES = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<SkyPipelineRouter.FrameHook> VIEW = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<Consumer<Boolean>> DEPTH = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<Runnable> AFTER_WORLD = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<SkyPipelineRouter.TextureDemand> HAND_MASK = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<SkyPipelineRouter.LandingHook> LAND = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<SkyPipelineRouter.ExplosionHook> BLAST = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<Consumer<class_1297>> STEP = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<Consumer<class_1309>> JUMP = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<ToDoubleFunction<Object>> BODY_HEIGHT = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<SkyPipelineRouter.WorldEventSink> OUTLINE = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<SkyPipelineRouter.Listener> DAMAGE = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<SkyPipelineRouter.EntityEventHook> ENTITY_EVENT = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<SkyPipelineRouter.ChunkFollowPredicate> BLOCK_BOUNDS = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<Runnable> HAND_COMPOSITE = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<Consumer<class_1297>> ENTITY_LOAD = new SkyPipelineRouter.PipelineList<>();
   public static final SkyPipelineRouter.PipelineList<Runnable> FRAME_END = new SkyPipelineRouter.PipelineList<>();
   private static final List<SkyPipelineRouter.PipelineList<?>> channels = new ArrayList<>(
      List.of(
         SKY,
         PARTICLES,
         VIEW,
         DEPTH,
         AFTER_WORLD,
         HAND_MASK,
         LAND,
         BLAST,
         STEP,
         JUMP,
         BODY_HEIGHT,
         OUTLINE,
         DAMAGE,
         ENTITY_EVENT,
         BLOCK_BOUNDS,
         HAND_COMPOSITE,
         ENTITY_LOAD,
         FRAME_END
      )
   );
   private static int depth = 0;
   private static long sequence = 0L;

   private SkyPipelineRouter() {
   }

   private static void owner() {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Visual hooks require owner thread");
      }
   }

   private static <T> T dispatch(Supplier<T> var0, T var1) {
      owner();
      depth++;

      Object var2;
      try {
         return (T)var0.get();
      } catch (AssertionError | Exception | LinkageError var8) {
         if (depth > 1) {
            throw var8;
         }

         depth--;
         ModuleState.callbackFailed(var8);
         depth++;
         var2 = var1;
      } finally {
         depth--;
      }

      return (T)var2;
   }

   public static int subscribers() {
      owner();
      return channels.stream().mapToInt(var0 -> var0.rows.size()).sum();
   }

   public static void extract(class_638 var0, class_12076 var1) {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var3 : SKY.rows) {
            ((SkyPipelineRouter.SkyPipelineHook)var3.listener()).extract(var0, var1);
         }

         return null;
      }, null);
   }

   public static void projection(Matrix4f var0) {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var2 : SKY.rows) {
            ((SkyPipelineRouter.SkyPipelineHook)var2.listener()).projection(var0);
         }

         return null;
      }, null);
   }

   public static void steady(Matrix4f var0, Matrix4f var1) {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var3 : SKY.rows) {
            ((SkyPipelineRouter.SkyPipelineHook)var3.listener()).steady(var0, var1);
         }

         return null;
      }, null);
   }

   public static boolean replaceSky() {
      return dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var1 : SKY.rows) {
            if (((SkyPipelineRouter.SkyPipelineHook)var1.listener()).replace()) {
               return true;
            }
         }

         return false;
      }, false);
   }

   public static void skyOverlay() {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var1 : SKY.rows) {
            ((SkyPipelineRouter.SkyPipelineHook)var1.listener()).overlay();
         }

         return null;
      }, null);
   }

   public static boolean hideClouds() {
      return dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var1 : SKY.rows) {
            if (((SkyPipelineRouter.SkyPipelineHook)var1.listener()).cloudsHidden()) {
               return true;
            }
         }

         return false;
      }, false);
   }

   public static int fogColor(class_638 var0, int var1) {
      return dispatch(() -> {
         int var2 = var1;

         for (SkyPipelineRouter.PipelineSlot var4 : SKY.rows) {
            var2 = ((SkyPipelineRouter.SkyPipelineHook)var4.listener()).fog(var0, var2);
         }

         return var2;
      }, var1);
   }

   public static int cloudColor(int var0) {
      return dispatch(() -> {
         int var1 = var0;

         for (SkyPipelineRouter.PipelineSlot var3 : SKY.rows) {
            var1 = ((SkyPipelineRouter.SkyPipelineHook)var3.listener()).clouds(var1);
         }

         return var1;
      }, var0);
   }

   public static float skyPresence() {
      return dispatch(() -> {
         float var0 = 0.0F;

         for (SkyPipelineRouter.PipelineSlot var2 : SKY.rows) {
            var0 = Math.max(var0, ((SkyPipelineRouter.SkyPipelineHook)var2.listener()).presence());
         }

         if (Float.isFinite(var0) && !(var0 < 0.0F) && !(var0 > 1.0F)) {
            return var0;
         } else {
            throw new IllegalStateException("Invalid sky presence");
         }
      }, 0.0F);
   }

   public static void particles(class_9909 var0, class_9960 var1, GpuBufferSlice var2) {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var4 : PARTICLES.rows) {
            ((SkyPipelineRouter.TerrainPassHook)var4.listener()).render(var0, var1, var2);
         }

         return null;
      }, null);
   }

   public static void view(class_4184 var0, Matrix4fc var1, Matrix4fc var2, GpuBufferSlice var3) {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var5 : VIEW.rows) {
            ((SkyPipelineRouter.FrameHook)var5.listener()).frame(var0, var1, var2, var3);
         }

         return null;
      }, null);
   }

   public static void keepDepth(boolean var0) {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var2 : DEPTH.rows) {
            ((Consumer)var2.listener()).accept(var0);
         }

         return null;
      }, null);
   }

   public static void afterWorld() {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var1 : AFTER_WORLD.rows) {
            ((Runnable)var1.listener()).run();
         }

         return null;
      }, null);
   }

   public static void wantHandMask() {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var1 : HAND_MASK.rows) {
            ((SkyPipelineRouter.TextureDemand)var1.listener()).want();
         }

         return null;
      }, null);
   }

   public static GpuTextureView handMask() {
      return dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var1 : HAND_MASK.rows) {
            GpuTextureView var2 = ((SkyPipelineRouter.TextureDemand)var1.listener()).draw();
            if (var2 != null) {
               return var2;
            }
         }

         return null;
      }, null);
   }

   public static void land(class_1297 var0, double var1) {
      if (class_310.method_1551().method_18854()) {
         dispatch(() -> {
            for (SkyPipelineRouter.PipelineSlot var4 : LAND.rows) {
               ((SkyPipelineRouter.LandingHook)var4.listener()).land(var0, var1);
            }

            return null;
         }, null);
      }
   }

   public static void step(class_1297 var0) {
      if (class_310.method_1551().method_18854()) {
         dispatch(() -> {
            for (SkyPipelineRouter.PipelineSlot var2 : STEP.rows) {
               ((Consumer)var2.listener()).accept(var0);
            }

            return null;
         }, null);
      }
   }

   public static void jump(class_1309 var0) {
      if (class_310.method_1551().method_18854()) {
         dispatch(() -> {
            for (SkyPipelineRouter.PipelineSlot var2 : JUMP.rows) {
               ((Consumer)var2.listener()).accept(var0);
            }

            return null;
         }, null);
      }
   }

   public static void blast(class_243 var0, float var1) {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var3 : BLAST.rows) {
            ((SkyPipelineRouter.ExplosionHook)var3.listener()).blast(var0, var1);
         }

         return null;
      }, null);
   }

   public static float bodyHeight(Object var0) {
      return dispatch(() -> {
         double var1 = 1.0;

         for (SkyPipelineRouter.PipelineSlot var4 : BODY_HEIGHT.rows) {
            var1 *= ((ToDoubleFunction)var4.listener()).applyAsDouble(var0);
         }

         if (Double.isFinite(var1) && !(var1 <= 0.0) && !(var1 > 100.0)) {
            return (float)var1;
         } else {
            throw new IllegalStateException("Invalid body scale");
         }
      }, 1.0F);
   }

   public static void extractOutline(class_12074 var0) {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var2 : OUTLINE.rows) {
            ((SkyPipelineRouter.WorldEventSink)var2.listener()).extract(var0);
         }

         return null;
      }, null);
   }

   public static boolean renderOutline(boolean var0, class_243 var1) {
      return dispatch(() -> {
         boolean var2 = false;

         for (SkyPipelineRouter.PipelineSlot var4 : OUTLINE.rows) {
            var2 |= ((SkyPipelineRouter.WorldEventSink)var4.listener()).render(var0, var1);
         }

         return var2;
      }, false);
   }

   public static void beforeBreak(class_2338 var0) {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var2 : OUTLINE.rows) {
            ((SkyPipelineRouter.WorldEventSink)var2.listener()).beforeBreak(var0);
         }

         return null;
      }, null);
   }

   public static void afterBreak(class_2338 var0, boolean var1) {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var3 : OUTLINE.rows) {
            ((SkyPipelineRouter.WorldEventSink)var3.listener()).afterBreak(var0, var1);
         }

         return null;
      }, null);
   }

   public static void beforeUse(class_3965 var0) {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var2 : OUTLINE.rows) {
            ((SkyPipelineRouter.WorldEventSink)var2.listener()).beforeUse(var0);
         }

         return null;
      }, null);
   }

   public static void afterUse(class_3965 var0, class_1269 var1) {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var3 : OUTLINE.rows) {
            ((SkyPipelineRouter.WorldEventSink)var3.listener()).afterUse(var0, var1);
         }

         return null;
      }, null);
   }

   public static void damage(class_1297 var0, class_6880<class_8110> var1, int var2, int var3, Optional<class_243> var4) {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var6 : DAMAGE.rows) {
            ((SkyPipelineRouter.Listener)var6.listener()).damage(var0, var1, var2, var3, var4);
         }

         return null;
      }, null);
   }

   public static void entityEvent(class_1297 var0, byte var1) {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var3 : ENTITY_EVENT.rows) {
            ((SkyPipelineRouter.EntityEventHook)var3.listener()).event(var0, var1);
         }

         return null;
      }, null);
   }

   public static boolean blockBounds(class_2338 var0, double[] var1) {
      return dispatch(() -> {
         boolean var2 = false;

         for (SkyPipelineRouter.PipelineSlot var4 : BLOCK_BOUNDS.rows) {
            var2 |= ((SkyPipelineRouter.ChunkFollowPredicate)var4.listener()).follow(var0, var1);
         }

         return var2;
      }, false);
   }

   public static void handComposite() {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var1 : HAND_COMPOSITE.rows) {
            ((Runnable)var1.listener()).run();
         }

         return null;
      }, null);
   }

   public static void entityLoaded(class_1297 var0) {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var2 : ENTITY_LOAD.rows) {
            ((Consumer)var2.listener()).accept(var0);
         }

         return null;
      }, null);
   }

   public static void frameEnded() {
      dispatch(() -> {
         for (SkyPipelineRouter.PipelineSlot var1 : FRAME_END.rows) {
            ((Runnable)var1.listener()).run();
         }

         return null;
      }, null);
   }

   @FunctionalInterface
   public interface Listener {
      void damage(class_1297 var1, class_6880<class_8110> var2, int var3, int var4, Optional<class_243> var5);
   }

   @FunctionalInterface
   public interface FrameHook {
      void frame(class_4184 var1, Matrix4fc var2, Matrix4fc var3, GpuBufferSlice var4);
   }

   @FunctionalInterface
   public interface ChunkFollowPredicate {
      boolean follow(class_2338 var1, double[] var2);
   }

   @FunctionalInterface
   public interface TerrainPassHook {
      void render(class_9909 var1, class_9960 var2, GpuBufferSlice var3);
   }

   public interface WorldEventSink {
      void extract(class_12074 var1);

      boolean render(boolean var1, class_243 var2);

      void beforeBreak(class_2338 var1);

      void afterBreak(class_2338 var1, boolean var2);

      void beforeUse(class_3965 var1);

      void afterUse(class_3965 var1, class_1269 var2);
   }

   public interface SkyPipelineHook {
      void extract(class_638 var1, class_12076 var2);

      void projection(Matrix4f var1);

      void steady(Matrix4f var1, Matrix4f var2);

      boolean replace();

      void overlay();

      boolean cloudsHidden();

      int fog(class_638 var1, int var2);

      int clouds(int var1);

      float presence();
   }

   @FunctionalInterface
   public interface LandingHook {
      void land(class_1297 var1, double var2);
   }

   public interface TextureDemand {
      void want();

      GpuTextureView draw();
   }

   @FunctionalInterface
   public interface ExplosionHook {
      void blast(class_243 var1, float var2);
   }

   @FunctionalInterface
   public interface EntityEventHook {
      void event(class_1297 var1, byte var2);
   }

   private record PipelineSlot<T>(long identity, int priority, T listener) {
   }

   public static final class PipelineList<T> {
      private volatile List<SkyPipelineRouter.PipelineSlot<T>> rows = List.of();
      private final ReentrantReadWriteLock readOnlyLock = new ReentrantReadWriteLock();

      private PipelineList() {
      }

      public static <T> SkyPipelineRouter.PipelineList<T> create() {
         SkyPipelineRouter.PipelineList var0 = new SkyPipelineRouter.PipelineList();
         SkyPipelineRouter.channels.add(var0);
         return var0;
      }

      public int subscribers() {
         SkyPipelineRouter.owner();
         return this.rows.size();
      }

      public <R> R invoke(Function<T, R> var1, R var2) {
         return SkyPipelineRouter.dispatch(() -> {
            Object var3 = var2;

            for (SkyPipelineRouter.PipelineSlot var5 : this.rows) {
               var3 = var1.apply((T)(var5.listener()));
            }

            return (R)var3;
         }, (R)var2);
      }

      public <R> R invokeReadOnly(Function<T, R> var1, R var2) {
         ReadLock var3 = this.readOnlyLock.readLock();
         var3.lock();

         Object var4;
         try {
            Object var5 = var2;

            for (SkyPipelineRouter.PipelineSlot var7 : this.rows) {
               var5 = var1.apply((T)(var7.listener()));
            }

            var4 = var5;
         } catch (AssertionError | Exception | LinkageError var11) {
            throw new IllegalStateException("Read-only visual transform failed");
         } finally {
            var3.unlock();
         }

         return (R)var4;
      }

      public AutoCloseable subscribe(int var1, T var2) {
         SkyPipelineRouter.owner();
         if (SkyPipelineRouter.depth != 0) {
            throw new IllegalStateException("Visual hook mutation during dispatch");
         } else if (this.rows.size() >= 128) {
            throw new IllegalStateException("Visual hook limit");
         } else {
            WriteLock var3 = this.readOnlyLock.writeLock();
            var3.lock();

            AutoCloseable var4;
            try {
               long var5 = ++SkyPipelineRouter.sequence;
               ArrayList var7 = new ArrayList<>(this.rows);
               var7.add(new SkyPipelineRouter.PipelineSlot<>(var5, var1, Objects.requireNonNull(var2)));
               var7.sort(Comparator.<SkyPipelineRouter.PipelineSlot>comparingInt(var0 -> var0.priority()).thenComparingLong(var0 -> var0.identity()));
               this.rows = List.copyOf(var7);
               var4 = () -> {
                  SkyPipelineRouter.owner();
                  if (SkyPipelineRouter.depth != 0) {
                     throw new IllegalStateException("Visual hook mutation during dispatch");
                  } else {
                     WriteLock var3x = this.readOnlyLock.writeLock();
                     var3x.lock();

                     try {
                        this.rows = this.rows.stream().filter(var2x -> var2x.identity() != var5).toList();
                     } finally {
                        var3x.unlock();
                     }
                  }
               };
            } finally {
               var3.unlock();
            }

            return var4;
         }
      }
   }
}
