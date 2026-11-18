// Sourced By DevFFNightmare
package dev.purxiy.modules.skins3d;

import dev.purxiy.loader.fabric.ClientServices;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1109;
import net.minecraft.class_1113;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_3419;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_1113.class_1114;

public final class Skins3DUpdater3 {
   private static final class_3414 J = class_3414.method_47908(ClientServices.id("skins3d.uwu"));
   private static final Skins3DUpdater4 v = new Skins3DUpdater4(ThreadLocalRandom.current());
   
   private static class_746 U;
   
   private static class_638 l;
   
   private static class_1113 s;
   private static int f = 0;
   private static long K = 0L;



   private Skins3DUpdater3() {
   }

   public static void tick(class_310 var0) {
      Skins3DModule var1 = Skins3DTransform3.h;
      if (var1.enabled()
         && var1.preset() == SkinPreset.I
         && var1.E.enabled()
         && !(var1.x.floatValue() <= 0.001F)
         && var0.field_1724 != null
         && var0.field_1687 != null
         && var0.field_1724.method_5805()
         && !var0.field_1724.method_7325()
         && SkinPreset.I.texture().equals(var0.field_1724.method_52814().comp_1626().comp_3627())) {
         if (U != var0.field_1724 || l != var0.field_1687) {
            stop(var0);
            U = var0.field_1724;
            l = var0.field_1687;
         }

         if (!var0.method_1493() && var0.field_1755 == null) {
            int var2 = U.method_31549().field_7477 ? 20 : U.method_7344().method_7586();
            long var3 = System.nanoTime();
            if (v.tick(var2) && (K == 0L || var3 - K >= 20000000000L)) {
               s = new class_1109(
                  J.comp_3319(),
                  class_3419.field_15248,
                  var1.x.floatValue(),
                  1.0F,
                  class_1113.method_43221(),
                  false,
                  0,
                  class_1114.field_5478,
                  0.0,
                  0.0,
                  0.0,
                  true
               );
               var0.method_1483().method_4873(s);
               K = var3;
               f++;
               if (Boolean.getBoolean("purxiy.dev")) {
                  ClientServices.LOGGER.info("UwU voice: played #{} (food={}, hungry={})", new Object[]{f, var2, var2 <= 6});
               }
            }
         }
      } else {
         stop(var0);
      }
   }

   public static float sincePlayed() {
      return K != 0L && U != null ? (float)(System.nanoTime() - K) / 1.0E9F : -1.0F;
   }

   public static void stop(class_310 var0) {
      if (s != null) {
         var0.method_1483().method_4870(s);
         s = null;
      }

      v.reset();
      U = null;
      l = null;
   }

}
