// Sourced By DevFFNightmare
package dev.purxiy.onboarding;

import dev.purxiy.core.IdentityPaths;
import dev.purxiy.core.ImageFiles;
import dev.purxiy.loader.fabric.AccountGate;
import dev.purxiy.loader.fabric.ClientServices;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.class_310;
import net.minecraft.class_442;

public final class OnboardingRequest {
   private static boolean shown;
   private static int inWorld = 0;

   private OnboardingRequest() {
   }

   public static void init() {
      ImageFiles.init();
      ClientTickEvents.END_CLIENT_TICK.register(OnboardingRequest::tick);
   }

   private static void tick(class_310 var0) {
      if (AccountGate.ready() && !shown && var0.method_53466() && var0.method_18506() == null) {
         boolean var1 = var0.field_1687 != null && var0.field_1724 != null && var0.field_1755 == null;
         inWorld = var1 ? inWorld + 1 : 0;
         if (var0.field_1755 instanceof class_442 || inWorld >= 50) {
            shown = true;
            if (!IdentityPaths.welcomeDone() && !scripted()) {
               ClientServices.LOGGER.info("First start of Purxiy: showing the welcome");
               var0.method_1507(new WelcomeScreen(var0.field_1755));
            }
         }
      }
   }

   private static boolean scripted() {
      return false;
   }

   static void markDone() {
      IdentityPaths.setWelcomeDone(true);
   }

   public static void replay() {
      class_310 var0 = class_310.method_1551();
      var0.method_1507(new WelcomeScreen(var0.field_1755));
   }

   public static void dev(class_310 var0, String var1) {
      String[] var2 = var1.split(";", 2);
      String var3 = var2[0];
      switch (var3) {
         case "open":
            var0.method_1507(new WelcomeScreen(var0.field_1755));
            break;
         case "photo":
            var0.method_1507(WelcomeScreen.photo(var0.field_1755));
            break;
         case "reset":
            IdentityPaths.setWelcomeDone(false);
            break;
         default:
            if (var0.field_1755 instanceof WelcomeScreen var6) {
               var6.devAction(var2[0], var2.length > 1 ? var2[1] : "");
            } else {
               ClientServices.LOGGER.warn("welcome={} needs an open welcome", var1);
            }
      }
   }
}
