package dev.purxiy.modules.cute;

import dev.purxiy.loader.api.ModuleContext;
import java.util.List;
import net.minecraft.class_11890;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_2338;
import net.minecraft.class_2398;
import net.minecraft.class_310;
import net.minecraft.class_5819;

public final class CuteWorldTicker {
   private static volatile CuteWorldTicker.PaletteSelection E = new CuteWorldTicker.PaletteSelection(false, "Sakura");
   private static CuteWorldTicker.PaletteSelection f = E;
   private static int h = 0;
   private static int I = 0;
   static CuteWorldModule r;
   private static ModuleContext q;
   private static int x = 0;
   private static int p = 0;
   private static final String[] b = new String[16];

   private CuteWorldTicker() {
   }

   public static void tick(class_310 var0) {
      CuteWorldUpdater.tick(var0);
      CuteWorldModule var1 = r;
      CuteWorldTicker.PaletteSelection var2 = new CuteWorldTicker.PaletteSelection(var1.enabled() && (Boolean)var1.L.get(), (String)var1.O.get());
      if (!var2.equals(f)) {
         f = var2;
         h = 4;
      }

      if (h > 0 && --h == 0 && !E.equals(f)) {
         E = f;
         if (var0.field_1687 != null) {
            var0.field_1769.method_3279();
         }
      }

      if (var1.enabled() && (Boolean)var1.N.get() && var0.field_1687 != null && var0.field_1724 != null && !var0.method_1493()) {
         I++;
         int var3 = Math.max(5, (int)(18.0 - 12.0 * (Double)var1.R.get()));
         if (I % var3 == 0) {
            class_5819 var4 = var0.field_1687.field_9229;
            List var5 = var0.field_1687
               .method_8390(
                  class_1309.class,
                  var0.field_1724.method_5829().method_1014(12.0),
                  var2x -> {
                     CuteWorldUpdater.ensureSourceClassInitialized();
                     return var2x.method_5805()
                        && !var2x.method_5767()
                        && !var2x.method_7325()
                        && (
                           !(var2x instanceof class_1531) && var2x instanceof class_11890
                              ? (Boolean)var1.f.get()
                              : (Boolean)var1.e.get() && (var2x != var0.field_1724 || !var0.field_1690.method_31044().method_31034())
                        );
                  }
               );
            if (!var5.isEmpty()) {
               class_1309 var6 = (class_1309)var5.get(var4.method_43048(var5.size()));
               if (var0.field_1724.method_6057(var6)) {
                  var0.field_1687
                     .method_8406(
                        class_2398.field_11201,
                        var6.method_23317() + (var4.method_43058() - 0.5) * var6.method_17681(),
                        var6.method_23318() + var6.method_17682() + 0.2,
                        var6.method_23321() + (var4.method_43058() - 0.5) * var6.method_17681(),
                        0.0,
                        0.015,
                        0.0
                     );
               }
            }

            for (int var14 = 0; var14 < 2; var14++) {
               double var7 = var0.field_1724.method_23317() + (var4.method_43058() - 0.5) * 16.0;
               double var9 = var0.field_1724.method_23318() + var4.method_43058() * 4.0;
               double var11 = var0.field_1724.method_23321() + (var4.method_43058() - 0.5) * 16.0;
               class_2338 var13 = class_2338.method_49637(var7, var9, var11);
               if (var0.field_1687.method_22347(var13)) {
                  var0.field_1687.method_8406(var14 == 0 ? class_2398.field_43379 : class_2398.field_11207, var7, var9, var11, 0.0, 0.0, 0.0);
               }
            }
         }
      }
   }

   public static int nature(int var0, String var1) {
      CuteWorldTicker.PaletteSelection var2 = E;
      if (!var2.y) {
         Object var10000;
         return var0;
      } else {
         byte var5 = -1;
         switch (var1.hashCode()) {
            case -1106736996:
               if (var1.equals("leaves")) {
                  var5 = 2;
               }
               break;
            case 98615734:
               if (var1.equals("grass")) {
                  var5 = 0;
               }
               break;
            case 112903447:
               if (var1.equals("water")) {
                  var5 = 1;
               }
         }

         int var10000_1 = switch (var5) {
            case 0 -> {
               String var11 = var2.b;
               switch (var11) {
                  case "Peach":
                     yield -2890341;
                  case "Lavender":
                     yield -4601379;
                  default:
                     yield -4923458;
               }
            }
            case 1 -> {
               String var10 = var2.b;
               switch (var10) {
                  case "Peach":
                     yield -7217955;
                  case "Lavender":
                     yield -5001749;
                  default:
                     yield -6631444;
               }
            }
            case 2 -> {
               String var9 = var2.b;
               switch (var9) {
                  case "Peach":
                     yield -16234;
                  case "Lavender":
                     yield -3104262;
                  default:
                     yield -24108;
               }
            }
            default -> {
               String var6 = var2.b;
               switch (var6) {
                  case "Peach":
                     yield -666429;
                  case "Lavender":
                     yield -2964241;
                  default:
                     yield -864545;
               }
            }
         };

         if (!var1.equals("sky") && !var1.equals("fog") && !var1.equals("cloud")) {
            return CuteWorldTransform.mix(var0, var10000_1, var1.equals("leaves") ? 0.95 : 0.65);
         } else {
            double var4 = Math.max(var0 >> 16 & 0xFF, Math.max(var0 >> 8 & 0xFF, var0 & 0xFF)) / 255.0;
            int var11 = CuteWorldTransform.mix(-16777216, var10000_1, var4);
            return CuteWorldTransform.mix(var0, var11, var1.equals("cloud") ? 0.4 : 0.32);
         }
      }
   }

   public static void install(CuteWorldModule var0, ModuleContext var1) {
      r = var0;
      q = var1;
   }

   public static void metric(String var0) {
      if (q != null) {
         q.metric(var0, var0.equals("drawn") ? ++x : ++p);
      }
   }

   public static void uninstall() {
      CuteWorldUpdater.close();
      E = new CuteWorldTicker.PaletteSelection(false, "Sakura");
      f = E;
      if (class_310.method_1551().field_1687 != null) {
         class_310.method_1551().field_1769.method_3279();
      }

      r = null;
      q = null;
   }

   private record PaletteSelection(boolean y, String b) {
      public boolean enabled() {
         return this.y;
      }

      public String palette() {
         return this.b;
      }

      public boolean booleanState16() {
         return this.y;
      }

      public String stringState5() {
         return this.b;
      }
   }
}
