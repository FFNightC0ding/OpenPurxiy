package dev.purxiy.modules.time;

import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.api.ModuleEntrypoint;
import dev.purxiy.time.TimeSupport;
import java.util.Map;
import java.util.Set;

public final class TimeEntrypoint implements ModuleEntrypoint {
   public static boolean a;
   private static final long d = Long.MIN_VALUE;

   public AutoCloseable start(ModuleContext var1) {
      Map var2 = var1.settings();
      String var3 = (String)var2.getOrDefault("preset", "Custom");
      if (!Set.of("Morning", "Day", "Sunset", "Night", "Custom").contains(var3)) {
         throw new IllegalArgumentException("Time preset");
      } else {
         double var4 = Double.parseDouble((String)var2.getOrDefault("time", "6000"));
         if (Double.isFinite(var4) && !(var4 < 0.0) && !(var4 > 24000.0)) {
            int var6 = TimeSupport.selectedTime(var3, (int)Math.round(Math.round(var4 / 250.0) * 250.0));
            var1.onWorldTime((var1x, var3x, var4x) -> {
               boolean var5 = false;
               return var3x && !var4x ? TimeSupport.visualTime(var1x, var6) : var1x;
            });
            long[] var7 = new long[]{Long.MIN_VALUE};
            var1.onWorldFrame(var1x -> {
               long var2x = var1x.present() ? var1x.identity() : Long.MIN_VALUE;
               long var4x = 81643568365217L;
               int var6x = var4x < 0L ? -1 : 1;
               if (var2x != var7[0]) {
                  var7[0] = var2x;
                  if (var1x.present()) {
                     var1x.refreshLighting();
                  }
               }
            });
            var1.refreshWorld();
            return var1::refreshWorld;
         } else {
            throw new IllegalArgumentException("Time range");
         }
      }
   }
}
