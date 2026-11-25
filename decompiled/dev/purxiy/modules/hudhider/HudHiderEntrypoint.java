package dev.purxiy.modules.hudhider;

import dev.purxiy.loader.api.HudLayer;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.api.ModuleEntrypoint;
import java.util.EnumSet;
import java.util.Locale;

public final class HudHiderEntrypoint implements ModuleEntrypoint {
   public static int a = 0;
   public static int b = 0;

   public AutoCloseable start(ModuleContext var1) {
      EnumSet var2 = EnumSet.noneOf(HudLayer.class);

      for (HudLayer var6 : HudLayer.values()) {
         String var7 = var6.name().toLowerCase(Locale.ROOT);
         boolean var8 = var6 == HudLayer.SCOREBOARD || var6 == HudLayer.TITLES;
         String var9 = var1.settings().getOrDefault(var7, Boolean.toString(var8));
         switch (var9) {
            case "true":
               var2.add(var6);
            case "false":
               break;
            default:
               throw new IllegalArgumentException("Invalid HUD visibility setting: " + var7);
         }
      }

      return var1.hideHudLayers(var2);
   }
}
