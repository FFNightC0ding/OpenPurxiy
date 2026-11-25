package dev.purxiy.loader.fabric;

import dev.purxiy.hud.HudElement;
import dev.purxiy.hud.HudSlot;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.Fonts;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.time.Duration;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ClientServices {
   public static final String MOD_ID = "purxiy";
   public static final Logger LOGGER = LoggerFactory.getLogger("DeObfDeCompByGuideX/Graphics");
   private static int accent = -4944641;
   private static int logo = -4944641;
   private static final Set<String> users = new HashSet<>();
   private static class_332 vanilla;
   private static boolean frameOpen;
   private static float globalMotion = 1.0F;
   private static boolean reducedMotion;
   private static final float[] motionChannels = new float[]{1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F};
   private static long motionGeneration = 0L;
   private static boolean motionLeased;
   private static final ClientServices.ThemeSettings DEFAULT_INTERFACE = new ClientServices.ThemeSettings(-4944641, -4944641, "Auto", 1.0F, true, 0.45F, true, false);
   private static final ClientServices.LayoutSettings DEFAULT_HUD = new ClientServices.LayoutSettings(1.0F, true, true, true, true);
   private static ClientServices.ThemeSettings interfaceLook = DEFAULT_INTERFACE;
   private static ClientServices.LayoutSettings hudLook = DEFAULT_HUD;

   private ClientServices() {
   }

   public static class_2960 id(String var0) {
      return class_2960.method_60655("purxiy", var0);
   }

   public static Supplier<String> label(Supplier<String> var0) {
      String var1 = Objects.requireNonNull((String)var0.get());
      return () -> var1;
   }

   public static Thread visualWorker(Runnable var0, String var1) {
      Thread var2 = AccessController.doPrivileged((PrivilegedAction<Thread>)(() -> new Thread((ThreadGroup)null, var0, var1, 0L, false)));
      var2.setContextClassLoader(ClientServices.class.getClassLoader());
      var2.setDaemon(true);
      return var2;
   }

   public static ExecutorService visualExecutor(String var0) {
      return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), var1 -> visualWorker(var1, var0));
   }

   public static HttpClient visualHttpClient(Executor var0) {
      return AccessController.doPrivileged(
         (PrivilegedAction<HttpClient>)(() -> HttpClient.newBuilder()
            .executor(var0)
            .connectTimeout(Duration.ofSeconds(3L))
            .followRedirects(Redirect.NORMAL)
            .build())
      );
   }

   public static int accent() {
      return accent;
   }

   public static int logo() {
      return logo;
   }

   public static void colors(int var0, int var1) {
      accent = var0 | 0xFF000000;
      logo = var1 | 0xFF000000;
   }

   public static ClientServices.ThemeSettings interfaceLook() {
      return interfaceLook;
   }

   public static ClientServices.LayoutSettings hudLook() {
      return hudLook;
   }

   public static AutoCloseable leaseInterface(int var0, int var1, String var2, float var3, boolean var4, float var5, boolean var6, boolean var7) {
      checkThread();
      if (interfaceLook != DEFAULT_INTERFACE) {
         throw new IllegalStateException("Interface settings already owned");
      } else if (Float.isFinite(var3) && !(var3 < 0.8F) && !(var3 > 1.25F) && Float.isFinite(var5) && !(var5 < 0.2F) && !(var5 > 0.85F)) {
         ClientServices.ThemeSettings var8 = new ClientServices.ThemeSettings(var0, var1, var2, var3, var4, var5, var6, var7);
         interfaceLook = var8;
         colors(var0, var1);
         Translations.bind(() -> interfaceLook.language());
         return () -> {
            checkThread();
            if (interfaceLook == var8) {
               interfaceLook = DEFAULT_INTERFACE;
               colors(DEFAULT_INTERFACE.accent(), DEFAULT_INTERFACE.logo());
            }
         };
      } else {
         throw new IllegalArgumentException("Interface settings out of range");
      }
   }

   public static AutoCloseable leaseHud(float var0, boolean var1, boolean var2, boolean var3, boolean var4) {
      checkThread();
      if (hudLook != DEFAULT_HUD) {
         throw new IllegalStateException("HUD settings already owned");
      } else if (Float.isFinite(var0) && !(var0 < 0.75F) && !(var0 > 1.5F)) {
         ClientServices.LayoutSettings var5 = new ClientServices.LayoutSettings(var0, var1, var2, var3, var4);
         hudLook = var5;
         return () -> {
            checkThread();
            if (hudLook == var5) {
               hudLook = DEFAULT_HUD;
            }
         };
      } else {
         throw new IllegalArgumentException("HUD scale out of range");
      }
   }

   public static boolean reducedMotion() {
      return reducedMotion;
   }

   public static float motion(String var0) {
      byte var1 = switch (var0) {
         case "MENU" -> 0;
         case "ORBIT" -> 1;
         case "HOVER" -> 2;
         case "TOGGLE" -> 3;
         case "PANEL" -> 4;
         case "CASCADE" -> 5;
         default -> throw new IllegalArgumentException("Motion channel");
      };
      return globalMotion * motionChannels[var1] * (reducedMotion && var1 != 2 ? 1.35F : 1.0F);
   }

   public static void motionPreferences(float var0, boolean var1, float... var2) {
      checkThread();
      if (var2.length == motionChannels.length && Float.isFinite(var0) && !(var0 < 0.05F) && !(var0 > 20.0F)) {
         for (float var6 : var2) {
            if (!Float.isFinite(var6) || var6 < 0.05F || var6 > 20.0F) {
               throw new IllegalArgumentException("Motion preference");
            }
         }

         globalMotion = var0;
         reducedMotion = var1;
         System.arraycopy(var2, 0, motionChannels, 0, var2.length);
      } else {
         throw new IllegalArgumentException("Motion preferences");
      }
   }

   public static AutoCloseable leaseMotion(float var0, boolean var1, float... var2) {
      checkThread();
      if (motionLeased) {
         throw new IllegalStateException("Motion preferences already have an owner");
      } else {
         float var3 = globalMotion;
         boolean var4 = reducedMotion;
         float[] var5 = (float[])motionChannels.clone();
         motionPreferences(var0, var1, var2);
         motionLeased = true;
         long var6 = ++motionGeneration;
         return () -> {
            checkThread();
            if (var6 == motionGeneration) {
               motionPreferences(var3, var4, var5);
               motionLeased = false;
               motionGeneration++;
            }
         };
      }
   }

   public static AutoCloseable acquire(String var0) {
      checkThread();
      if (!users.add(var0)) {
         throw new IllegalStateException("HUD renderer already acquired for module");
      } else {
         try {
            Fonts.load();
         } catch (RuntimeException | LinkageError var2) {
            users.remove(var0);
            throw var2;
         }

         return () -> {
            checkThread();
            users.remove(var0);
         };
      }
   }

   public static void beginFrame() {
      HudSlot.syncScreen();
      frameOpen = !users.isEmpty();
      if (frameOpen) {
         RenderPipelines.get().setUserScale(interfaceLook.scale());
         RenderPipelines.get().setHudScale(hudLook.scale());
         RenderPipelines.get().beginFrame();
         HudElement.frame(RenderPipelines.get().delta());
      }
   }

   public static void flushHud() {
      if (frameOpen) {
         RenderPipelines.get().flushMenu();
         RenderPipelines.get().flushHud();
      }
   }

   public static void endFrame() {
      try {
         if (frameOpen) {
            RenderPipelines.get().endFrame();
         }
      } finally {
         frameOpen = false;
         SkyPipelineRouter.frameEnded();
      }
   }

   public static void borrowVanilla(class_332 var0, Runnable var1) {
      checkThread();
      if (vanilla != null) {
         throw new IllegalStateException("Nested HUD drawing");
      } else {
         vanilla = var0;

         try {
            var1.run();
         } finally {
            vanilla = null;
         }
      }
   }

   public static class_332 vanilla() {
      checkThread();
      if (vanilla == null) {
         throw new IllegalStateException("Vanilla HUD outside its callback");
      } else {
         return vanilla;
      }
   }

   public static boolean drawing() {
      return frameOpen;
   }

   private static void checkThread() {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Graphics owner thread required");
      }
   }

   public record ThemeSettings(int accent, int logo, String language, float scale, boolean blur, float dim, boolean stars, boolean pause) {
   }

   public record LayoutSettings(float scale, boolean glass, boolean noOverlap, boolean chatEditor, boolean snap) {
   }
}
