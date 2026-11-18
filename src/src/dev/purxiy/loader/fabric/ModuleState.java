// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import dev.purxiy.hud.HudRegistry;
import dev.purxiy.loader.api.HudCanvas;
import dev.purxiy.loader.api.WorldView;
import dev.purxiy.loader.runtime.FrameRouter;
import dev.purxiy.loader.runtime.LazyModules;
import dev.purxiy.loader.runtime.ModuleRuntime;
import dev.purxiy.loader.runtime.ModuleSource;
import dev.purxiy.loader.security.ModuleVerifier;
import java.time.Clock;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import org.slf4j.LoggerFactory;

public final class ModuleState implements AutoCloseable {
   static ModuleState active;
   final FrameRouter router = new FrameRouter();
   final ModuleRuntime runtime;
   final LazyModules modules;
   final Map<String, Double> metrics = new HashMap<>();
   private long lastFrame;
   private class_638 previousWorld;
   private long worldIdentity;
   private final java.util.ArrayDeque<String> faultedModules = new java.util.ArrayDeque<>();

   public ModuleState(ModuleVerifier var1, ModuleSource var2) {
      FrameRouter var3 = this.router;
      Map var4 = this.metrics;
      this.runtime = new ModuleRuntime(var1, var3, var4::put);
      this.modules = new LazyModules(this.runtime, var2, Clock.systemUTC(), LoaderMessages::denied);
   }

   public static boolean moduleActive(String var0) {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Module state requires owner thread");
      } else {
         return active != null && active.runtime.isActive(var0);
      }
   }

   public static double fov(double var0) {
      ModuleState var2 = active;
      if (var2 == null) {
         return var0;
      } else {
         double var3 = var2.runtime.isActive("zoom") ? var2.metrics.getOrDefault("zoom.factor", 1.0) : 1.0;
         return Math.toDegrees(2.0 * Math.atan(Math.tan(Math.toRadians(var0) * 0.5) / Math.max(1.0, Math.min(64.0, var3))));
      }
   }

   public static void frame() {
      ModuleState var0 = active;
      if (var0 != null) {
         try {
            var0.drainFaults();
            var0.modules.pump();
            if (var0.runtime.activeCount() == 0) {
               var0.metrics.clear();
            }

            long var1 = System.nanoTime();
            var0.router.frame(var0.lastFrame == 0L ? 0.0 : Math.min(0.25, (var1 - var0.lastFrame) / 1.0E9));
            ModuleState.TypeT var3 = var0.new TypeT(class_310.method_1551().field_1687);

            try {
               var0.router.world(var3);
            } finally {
               var3.level = null;
               var3.valid = false;
            }

            var0.lastFrame = var1;
         } catch (AssertionError | Exception | LinkageError var8) {
            fault(var0, var8);
         }
      }
   }

   public static long worldTime(class_638 var0, long var1) {
      ModuleState var3 = active;
      if (var3 != null && class_310.method_1551().method_18854()) {
         try {
            return var3.router.worldTime(var1, var0.method_8597().comp_642(), var0.method_8597().comp_5219());
         } catch (AssertionError | Exception | LinkageError var5) {
            fault(var3, var5);
            return var1;
         }
      } else {
         return var1;
      }
   }

   private static void refreshLighting(class_638 var0) {
      if (var0 != null) {
         class_310 var1 = class_310.method_1551();
         var0.method_75728().method_76405();
         var0.method_8533();
         class_4184 var2 = var1.field_1773.method_19418();
         if (var2.method_19332()) {
            var2.method_75756().method_75686();
            var2.method_75756().method_75690(var0, var2.method_71156());
         }

         var1.field_1773.method_22974().method_3314();
      }
   }

   public static void hud(class_332 var0) {
      ModuleState var1 = active;
      if (var1 != null) {
         ModuleState.TypeR var2 = new ModuleState.TypeR(var0);

         try {
            ClientServices.borrowVanilla(var0, () -> var1.router.hud(var2));
            HudRegistry.extract(var0, class_310.method_1551().method_61966());
         } catch (AssertionError | Exception | LinkageError var7) {
            fault(var1, var7);
         } finally {
            var2.graphics = null;
         }
      }
   }

   public static void callbackFailed() {
      if (active != null) {
         fault(active);
      }
   }

   public static void callbackFailed(Throwable var0) {
      if (active != null) {
         fault(active, var0);
      }
   }

   private static void fault(ModuleState var0, Throwable var1) {
      String var2 = failingModule(var0, var1);
      if (var2 != null) {
         if (!var0.faultedModules.contains(var2)) {
            var0.faultedModules.addLast(var2);
         }

         try {
            LoggerFactory.getLogger("PurxiyLoader")
               .error("Module {} faulted; only that module is disabled: {}", var2, NameValidation.capture(var1));
         } catch (RuntimeException | LinkageError var4) {
            LoggerFactory.getLogger("PurxiyLoader").error("Module {} faulted; only that module is disabled", var2);
         }

         return;
      }

      try {
         LoggerFactory.getLogger("PurxiyLoader").error("Module callback fault metadata: {}", NameValidation.capture(var1));
      } catch (RuntimeException | LinkageError var3) {
         LoggerFactory.getLogger("PurxiyLoader").error("Module callback fault metadata unavailable");
      }

      fault(var0);
   }

   /**
    * Which module owns the throwing frame. Callbacks are singletons the router has no owner for, so the module is
    * recovered from the stack: every module class is defined by that module's own classloader.
    */
   private static String failingModule(ModuleState var0, Throwable var1) {
      for (Throwable var2 = var1; var2 != null; var2 = var2.getCause()) {
         for (StackTraceElement var6 : var2.getStackTrace()) {
            try {
               String var7 = var0.runtime.moduleOfClass(var6.getClassName());
               if (var7 != null) {
                  return var7;
               }
            } catch (RuntimeException | LinkageError var9) {
               return null;
            }
         }
      }

      return null;
   }

   private void drainFaults() {
      while (!this.faultedModules.isEmpty()) {
         String var1 = this.faultedModules.removeFirst();

         try {
            if (this.modules.isRequested(var1)) {
               this.modules.disable(var1);
            }
         } catch (AssertionError | Exception | LinkageError var3) {
            LoggerFactory.getLogger("PurxiyLoader").error("Could not disable faulted module {}", var1);
         }

         try {
            LoaderMessages.moduleFailed(var1);
         } catch (AssertionError | Exception | LinkageError var4) {
         }
      }
   }

   private static void fault(ModuleState var0) {
      try {
         var0.close();
      } catch (AssertionError | Exception | LinkageError var2) {
      }

      LoaderMessages.status = "Modules gestopt vanwege een fout. Herstart de client.";
      LoggerFactory.getLogger("PurxiyLoader").error("Module callback failed; module host stopped and hooks detached.");
   }

   @Override
   public void close() throws Exception {
      try {
         this.modules.close();
      } finally {
         this.metrics.clear();
         this.previousWorld = null;
         if (active == this) {
            active = null;
         }

         refreshLighting(class_310.method_1551().field_1687);
      }
   }

   private static final class TypeR implements HudCanvas {
      private class_332 graphics;

      private TypeR(class_332 var1) {
         this.graphics = var1;
      }

      private void check() {
         if (this.graphics == null || !class_310.method_1551().method_18854()) {
            throw new IllegalStateException("HUD canvas outside its frame");
         }
      }

      @Override
      public int width() {
         this.check();
         return this.graphics.method_51421();
      }

      @Override
      public int height() {
         this.check();
         return this.graphics.method_51443();
      }

      @Override
      public void fill(int var1, int var2, int var3, int var4, int var5) {
         this.check();
         this.graphics.method_25294(var1, var2, var3, var4, var5);
      }
   }

   private final class TypeT implements WorldView {
      private class_638 level;
      private final long identity;
      private boolean valid = true;

      TypeT(class_638 nullx) {
         this.level = nullx;
         if (nullx != ModuleState.this.previousWorld) {
            ModuleState.this.previousWorld = nullx;
            ModuleState.this.worldIdentity++;
         }

         this.identity = ModuleState.this.worldIdentity;
      }

      private void check() {
         if (!this.valid || !class_310.method_1551().method_18854()) {
            throw new IllegalStateException("World view outside its frame");
         }
      }

      @Override
      public boolean present() {
         this.check();
         return this.level != null;
      }

      @Override
      public long identity() {
         this.check();
         return this.identity;
      }

      @Override
      public boolean hasSkyLight() {
         this.check();
         return this.level != null && this.level.method_8597().comp_642();
      }

      @Override
      public boolean hasFixedTime() {
         this.check();
         return this.level != null && this.level.method_8597().comp_5219();
      }

      @Override
      public void refreshLighting() {
         this.check();
         ModuleState.refreshLighting(this.level);
      }
   }
}
