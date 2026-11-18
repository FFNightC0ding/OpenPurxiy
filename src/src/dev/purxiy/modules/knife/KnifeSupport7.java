// Sourced By DevFFNightmare
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

public final class KnifeSupport7 {
   public static final Set<String> Q = Set.of(
      "draw", "swish", "whoosh", "toss", "catch", "ringcatch", "click", "ring", "clack", "latch", "tap", "slash", "swing", "hit", "crit"
   );
   private static final Map<String, class_3414> b = new HashMap<>();
   private static final Map<String, Long> v = new HashMap<>();


   private static final long d = 35000000L;

   private KnifeSupport7() {
   }

   public static void play(KnifeRenderer7.Data2 var0) {
      play(var0.sound(), var0.volume(), var0.pitch());
   }

   public static void play(String var0, float var1, float var2) {
      long var3 = 105592135702444L;
      long var5 = var3 ^ 62567339031592L;
      int var7 = (int)((var3 ^ 62567339031592L) >>> 32);
      int var8 = (int)((var3 ^ 62567339031592L) << 32 >>> 48);
      int var9 = (int)(var5 << 48 >>> 48);
      if (KnifeUpdater2.Z.H.enabled()) {
         P(var0, var1, var7, (short)var8, var2, var9);
      }
   }

   public static void attack(String var0, float var1, float var2) {
      long var3 = 19908592919369L;
      long var5 = var3 ^ 82419770378445L;
      int var7 = (int)((var3 ^ 82419770378445L) >>> 32);
      int var8 = (int)((var3 ^ 82419770378445L) << 32 >>> 48);
      int var9 = (int)(var5 << 48 >>> 48);
      if (!KnifeUpdater2.Z.B.is("Sword")) {
         P(var0, var1, var7, (short)var8, var2, var9);
      }
   }

   private static void P(String var0, float var1, int var2, short var3, float var4, int var5) {
      KnifeUpdater2.ensureSourceClassInitialized();
      KnifeModule var6 = KnifeUpdater2.Z;
      class_3414 var7 = b.get(var0);
      if (var7 != null) {
         long var8 = System.nanoTime();
         Long var10 = v.get(var0);
         if (var10 == null || var8 - var10 >= 35000000L) {
            v.put(var0, var8);
            float var11 = var1 * var6.w.get().floatValue();
            if (!(var11 <= 0.001F)) {
               float var12 = 1.0F + (float)ThreadLocalRandom.current().nextDouble(-0.035, 0.035);
               class_310.method_1551()
                  .method_1483()
                  .method_4873(
                     new class_1109(
                        var7.comp_3319(),
                        class_3419.field_15248,
                        Math.min(1.0F, var11),
                        Math.clamp(var4 * var12, 0.5F, 2.0F),
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
