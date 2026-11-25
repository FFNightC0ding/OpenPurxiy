package dev.purxiy.modules.knife;

import dev.purxiy.loader.fabric.ClientServices;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1109;
import net.minecraft.class_1113;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_3419;
import net.minecraft.class_1113.class_1114;

public final class KnifeFactory {
   public static final Set<String> f = Set.of(
      "blade_draw", "blade_swish", "blade_hit", "heavy_draw", "heavy_swish", "heavy_hit", "crit", "grip", "glint", "flick", "tool_grab", "tool_swish"
   );
   private static final Map<String, class_3414> e = new HashMap<>();
   private static final Map<String, Long> I = new HashMap<>();
   private static final boolean n = "log".equals(System.getProperty("purxiy.wieldSounds"));

   private KnifeFactory() {
   }

   static class_3414 of(KnifeApi var0, String var1) {
      class_3414 var2 = e.get(var0.sounds() + "_" + var1);
      return var2 != null ? var2 : e.get(var1);
   }

   public static void play(KnifeApi var0, String var1, float var2, float var3, boolean var4) {
      if (var0.soundOn(var4)) {
         class_3414 var5 = of(var0, var1);
         if (var5 != null) {
            long var6 = System.nanoTime();
            Long var8 = I.get(var1);
            if (var8 == null || var6 - var8 >= 35000000L) {
               I.put(var1, var6);
               float var9 = var2 * var0.volume();
               if (!(var9 <= 0.001F)) {
                  if (n) {
                     ClientServices.LOGGER.info("Wield sound: {} {} {}", new Object[]{var5.comp_3319(), var9, var3});
                  }

                  float var10 = 1.0F + (float)ThreadLocalRandom.current().nextDouble(-0.035, 0.035);
                  class_310.method_1551()
                     .method_1483()
                     .method_4873(
                        new class_1109(
                           var5.comp_3319(),
                           class_3419.field_15248,
                           Math.min(1.0F, var9),
                           Math.clamp(var3 * var10, 0.5F, 2.0F),
                           class_1113.method_43221(),
                           false,
                           0,
                           class_1114.field_5478,
                           0.0,
                           0.0,
                           0.0,
                           true
                        )
                     );
               }
            }
         }
      }
   }
}
