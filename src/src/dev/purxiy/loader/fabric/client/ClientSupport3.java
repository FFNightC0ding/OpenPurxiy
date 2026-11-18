// Sourced By DevFFNightmare
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

public final class ClientSupport3 {
   public static final ClientSupport3.TypeX<ClientSupport3.Listener5> SKY = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<ClientSupport3.Listener3> PARTICLES = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<ClientSupport3.Listener10> VIEW = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<Consumer<Boolean>> DEPTH = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<Runnable> AFTER_WORLD = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<ClientSupport3.Listener7> HAND_MASK = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<ClientSupport3.Listener6> LAND = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<ClientSupport3.Listener8> BLAST = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<Consumer<class_1297>> STEP = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<Consumer<class_1309>> JUMP = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<ToDoubleFunction<Object>> BODY_HEIGHT = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<ClientSupport3.Listener4> OUTLINE = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<ClientSupport3.Listener> DAMAGE = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<ClientSupport3.Listener9> ENTITY_EVENT = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<ClientSupport3.Listener2> BLOCK_BOUNDS = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<Runnable> HAND_COMPOSITE = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<Consumer<class_1297>> ENTITY_LOAD = new ClientSupport3.TypeX<>();
   public static final ClientSupport3.TypeX<Runnable> FRAME_END = new ClientSupport3.TypeX<>();
   private static final List<ClientSupport3.TypeX<?>> channels = new ArrayList<>(
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

   private ClientSupport3() {
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
         for (ClientSupport3.TypeF var3 : SKY.rows) {
            ((ClientSupport3.Listener5)var3.listener()).extract(var0, var1);
         }

         return null;
      }, null);
   }

   public static void projection(Matrix4f var0) {
      dispatch(() -> {
         for (ClientSupport3.TypeF var2 : SKY.rows) {
            ((ClientSupport3.Listener5)var2.listener()).projection(var0);
         }

         return null;
      }, null);
   }

   public static void steady(Matrix4f var0, Matrix4f var1) {
      dispatch(() -> {
         for (ClientSupport3.TypeF var3 : SKY.rows) {
            ((ClientSupport3.Listener5)var3.listener()).steady(var0, var1);
         }

         return null;
      }, null);
   }

   public static boolean replaceSky() {
      return dispatch(() -> {
         for (ClientSupport3.TypeF var1 : SKY.rows) {
            if (((ClientSupport3.Listener5)var1.listener()).replace()) {
               return true;
            }
         }

         return false;
      }, false);
   }

   public static void skyOverlay() {
      dispatch(() -> {
         for (ClientSupport3.TypeF var1 : SKY.rows) {
            ((ClientSupport3.Listener5)var1.listener()).overlay();
         }

         return null;
      }, null);
   }

   public static boolean hideClouds() {
      return dispatch(() -> {
         for (ClientSupport3.TypeF var1 : SKY.rows) {
            if (((ClientSupport3.Listener5)var1.listener()).cloudsHidden()) {
               return true;
            }
         }

         return false;
      }, false);
   }

   public static int fogColor(class_638 var0, int var1) {
      return dispatch(() -> {
         int var2 = var1;

         for (ClientSupport3.TypeF var4 : SKY.rows) {
            var2 = ((ClientSupport3.Listener5)var4.listener()).fog(var0, var2);
         }

         return var2;
      }, var1);
   }

   public static int cloudColor(int var0) {
      return dispatch(() -> {
         int var1 = var0;

         for (ClientSupport3.TypeF var3 : SKY.rows) {
            var1 = ((ClientSupport3.Listener5)var3.listener()).clouds(var1);
         }

         return var1;
      }, var0);
   }

   public static float skyPresence() {
      return dispatch(() -> {
         float var0 = 0.0F;

         for (ClientSupport3.TypeF var2 : SKY.rows) {
            var0 = Math.max(var0, ((ClientSupport3.Listener5)var2.listener()).presence());
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
         for (ClientSupport3.TypeF var4 : PARTICLES.rows) {
            ((ClientSupport3.Listener3)var4.listener()).render(var0, var1, var2);
         }

         return null;
      }, null);
   }

   public static void view(class_4184 var0, Matrix4fc var1, Matrix4fc var2, GpuBufferSlice var3) {
      dispatch(() -> {
         for (ClientSupport3.TypeF var5 : VIEW.rows) {
            ((ClientSupport3.Listener10)var5.listener()).frame(var0, var1, var2, var3);
         }

         return null;
      }, null);
   }

   public static void keepDepth(boolean var0) {
      dispatch(() -> {
         for (ClientSupport3.TypeF var2 : DEPTH.rows) {
            ((Consumer)var2.listener()).accept(var0);
         }

         return null;
      }, null);
   }

   public static void afterWorld() {
      dispatch(() -> {
         for (ClientSupport3.TypeF var1 : AFTER_WORLD.rows) {
            ((Runnable)var1.listener()).run();
         }

         return null;
      }, null);
   }

   public static void wantHandMask() {
      dispatch(() -> {
         for (ClientSupport3.TypeF var1 : HAND_MASK.rows) {
            ((ClientSupport3.Listener7)var1.listener()).want();
         }

         return null;
      }, null);
   }

   public static GpuTextureView handMask() {
      return dispatch(() -> {
         for (ClientSupport3.TypeF var1 : HAND_MASK.rows) {
            GpuTextureView var2 = ((ClientSupport3.Listener7)var1.listener()).draw();
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
            for (ClientSupport3.TypeF var4 : LAND.rows) {
               ((ClientSupport3.Listener6)var4.listener()).land(var0, var1);
            }

            return null;
         }, null);
      }
   }

   public static void step(class_1297 var0) {
      if (class_310.method_1551().method_18854()) {
         dispatch(() -> {
            for (ClientSupport3.TypeF var2 : STEP.rows) {
               ((Consumer)var2.listener()).accept(var0);
            }

            return null;
         }, null);
      }
   }

   public static void jump(class_1309 var0) {
      if (class_310.method_1551().method_18854()) {
         dispatch(() -> {
            for (ClientSupport3.TypeF var2 : JUMP.rows) {
               ((Consumer)var2.listener()).accept(var0);
            }

            return null;
         }, null);
      }
   }

   public static void blast(class_243 var0, float var1) {
      dispatch(() -> {
         for (ClientSupport3.TypeF var3 : BLAST.rows) {
            ((ClientSupport3.Listener8)var3.listener()).blast(var0, var1);
         }

         return null;
      }, null);
   }

   public static float bodyHeight(Object var0) {
      return dispatch(() -> {
         double var1 = 1.0;

         for (ClientSupport3.TypeF var4 : BODY_HEIGHT.rows) {
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
         for (ClientSupport3.TypeF var2 : OUTLINE.rows) {
            ((ClientSupport3.Listener4)var2.listener()).extract(var0);
         }

         return null;
      }, null);
   }

   public static boolean renderOutline(boolean var0, class_243 var1) {
      return dispatch(() -> {
         boolean var2 = false;

         for (ClientSupport3.TypeF var4 : OUTLINE.rows) {
            var2 |= ((ClientSupport3.Listener4)var4.listener()).render(var0, var1);
         }

         return var2;
      }, false);
   }

   public static void beforeBreak(class_2338 var0) {
      dispatch(() -> {
         for (ClientSupport3.TypeF var2 : OUTLINE.rows) {
            ((ClientSupport3.Listener4)var2.listener()).beforeBreak(var0);
         }

         return null;
      }, null);
   }

   public static void afterBreak(class_2338 var0, boolean var1) {
      dispatch(() -> {
         for (ClientSupport3.TypeF var3 : OUTLINE.rows) {
            ((ClientSupport3.Listener4)var3.listener()).afterBreak(var0, var1);
         }

         return null;
      }, null);
   }

   public static void beforeUse(class_3965 var0) {
      dispatch(() -> {
         for (ClientSupport3.TypeF var2 : OUTLINE.rows) {
            ((ClientSupport3.Listener4)var2.listener()).beforeUse(var0);
         }

         return null;
      }, null);
   }

   public static void afterUse(class_3965 var0, class_1269 var1) {
      dispatch(() -> {
         for (ClientSupport3.TypeF var3 : OUTLINE.rows) {
            ((ClientSupport3.Listener4)var3.listener()).afterUse(var0, var1);
         }

         return null;
      }, null);
   }

   public static void damage(class_1297 var0, class_6880<class_8110> var1, int var2, int var3, Optional<class_243> var4) {
      dispatch(() -> {
         for (ClientSupport3.TypeF var6 : DAMAGE.rows) {
            ((ClientSupport3.Listener)var6.listener()).damage(var0, var1, var2, var3, var4);
         }

         return null;
      }, null);
   }

   public static void entityEvent(class_1297 var0, byte var1) {
      dispatch(() -> {
         for (ClientSupport3.TypeF var3 : ENTITY_EVENT.rows) {
            ((ClientSupport3.Listener9)var3.listener()).event(var0, var1);
         }

         return null;
      }, null);
   }

   public static boolean blockBounds(class_2338 var0, double[] var1) {
      return dispatch(() -> {
         boolean var2 = false;

         for (ClientSupport3.TypeF var4 : BLOCK_BOUNDS.rows) {
            var2 |= ((ClientSupport3.Listener2)var4.listener()).follow(var0, var1);
         }

         return var2;
      }, false);
   }

   public static void handComposite() {
      dispatch(() -> {
         for (ClientSupport3.TypeF var1 : HAND_COMPOSITE.rows) {
            ((Runnable)var1.listener()).run();
         }

         return null;
      }, null);
   }

   public static void entityLoaded(class_1297 var0) {
      dispatch(() -> {
         for (ClientSupport3.TypeF var2 : ENTITY_LOAD.rows) {
            ((Consumer)var2.listener()).accept(var0);
         }

         return null;
      }, null);
   }

   public static void frameEnded() {
      dispatch(() -> {
         for (ClientSupport3.TypeF var1 : FRAME_END.rows) {
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
   public interface Listener10 {
      void frame(class_4184 var1, Matrix4fc var2, Matrix4fc var3, GpuBufferSlice var4);
   }

   @FunctionalInterface
   public interface Listener2 {
      boolean follow(class_2338 var1, double[] var2);
   }

   @FunctionalInterface
   public interface Listener3 {
      void render(class_9909 var1, class_9960 var2, GpuBufferSlice var3);
   }

   public interface Listener4 {
      void extract(class_12074 var1);

      boolean render(boolean var1, class_243 var2);

      void beforeBreak(class_2338 var1);

      void afterBreak(class_2338 var1, boolean var2);

      void beforeUse(class_3965 var1);

      void afterUse(class_3965 var1, class_1269 var2);
   }

   public interface Listener5 {
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
   public interface Listener6 {
      void land(class_1297 var1, double var2);
   }

   public interface Listener7 {
      void want();

      GpuTextureView draw();
   }

   @FunctionalInterface
   public interface Listener8 {
      void blast(class_243 var1, float var2);
   }

   @FunctionalInterface
   public interface Listener9 {
      void event(class_1297 var1, byte var2);
   }

   private record TypeF<T>(long identity, int priority, T listener) {
   }

   public static final class TypeX<T> {
      private volatile List<ClientSupport3.TypeF<T>> rows = List.of();
      private final ReentrantReadWriteLock readOnlyLock = new ReentrantReadWriteLock();

      private TypeX() {
      }

      public static <T> ClientSupport3.TypeX<T> create() {
         ClientSupport3.TypeX var0 = new ClientSupport3.TypeX();
         ClientSupport3.channels.add(var0);
         return var0;
      }

      public int subscribers() {
         ClientSupport3.owner();
         return this.rows.size();
      }

      public <R> R invoke(Function<T, R> var1, R var2) {
         return ClientSupport3.dispatch(() -> {
            Object var3 = var2;

            for (ClientSupport3.TypeF var5 : this.rows) {
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

            for (ClientSupport3.TypeF var7 : this.rows) {
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
         ClientSupport3.owner();
         if (ClientSupport3.depth != 0) {
            throw new IllegalStateException("Visual hook mutation during dispatch");
         } else if (this.rows.size() >= 128) {
            throw new IllegalStateException("Visual hook limit");
         } else {
            WriteLock var3 = this.readOnlyLock.writeLock();
            var3.lock();

            AutoCloseable var4;
            try {
               long var5 = ++ClientSupport3.sequence;
               ArrayList var7 = new ArrayList<>(this.rows);
               var7.add(new ClientSupport3.TypeF<>(var5, var1, Objects.requireNonNull(var2)));
               var7.sort(Comparator.<ClientSupport3.TypeF>comparingInt(var0 -> var0.priority()).thenComparingLong(var0 -> var0.identity()));
               this.rows = List.copyOf(var7);
               var4 = () -> {
                  ClientSupport3.owner();
                  if (ClientSupport3.depth != 0) {
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
