package dev.purxiy.hud;

import dev.purxiy.loader.fabric.ClientServices;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class HudElementIds {
   private static final List<String> ORDER = List.of(
      "watermark",
      "active_modules",
      "fps",
      "coordinates",
      "keystrokes",
      "direction",
      "armor_status",
      "potion_effects",
      "notifications",
      "cps",
      "speed",
      "ping",
      "clock",
      "memory",
      "session_time",
      "server_info",
      "freecam_speed",
      "radar",
      "region_map",
      "staff_list",
      "target_hud"
   );
   private static final Map<String, HudElementIds.HudIdTable> boxes = new HashMap<>();
   private static long laidOutFrame = Long.MIN_VALUE;

   private HudElementIds() {
   }

   static void measure(String var0, long var1, float var3, float var4, float var5, float var6, float var7, boolean var8) {
      boxes.put(var0, new HudElementIds.HudIdTable(var0, var1, var3, var4, var5, var6, var7, var8));
      laidOutFrame = Long.MIN_VALUE;
   }

   static void position(String var0, long var1, float[] var3) {
      if (laidOutFrame != var1) {
         layout(var1);
      }

      HudElementIds.HudIdTable var4 = boxes.get(var0);
      if (var4 != null && var4.frame == var1) {
         var3[0] = var4.x;
         var3[1] = var4.y;
      } else {
         throw new IllegalStateException("HUD was not measured before rendering");
      }
   }

   private static void layout(long var0) {
      List var2 = boxes.values().stream().filter(var2x -> var2x.frame == var0).sorted(Comparator.<HudElementIds.HudIdTable>comparingInt(var0x -> {
         int var1 = ORDER.indexOf(var0x.id);
         return var1 < 0 ? ORDER.size() : var1;
      }).thenComparing(var0x -> var0x.id)).toList();

      for (HudElementIds.HudIdTable var4 : ((Iterable<HudElementIds.HudIdTable>)(Object)var2)) {
         var4.x = var4.baseX;
         var4.y = var4.baseY;
      }

      if (!ClientServices.hudLook().noOverlap()) {
         laidOutFrame = var0;
      } else {
         for (int var10 = 0; var10 < var2.size(); var10++) {
            HudElementIds.HudIdTable var11 = (HudElementIds.HudIdTable)var2.get(var10);
            if (!var11.placed) {
               boolean var5 = var11.y + var11.height / 2.0F > var11.canvasHeight / 2.0F;

               for (int var6 = 0; var6 < var2.size(); var6++) {
                  boolean var7 = false;

                  for (int var8 = 0; var8 < var2.size(); var8++) {
                     HudElementIds.HudIdTable var9 = (HudElementIds.HudIdTable)var2.get(var8);
                     if (var10 != var8
                        && (var8 < var10 || var9.placed)
                        && var11.x < var9.x + var9.width + 3.0F
                        && var11.x + var11.width + 3.0F > var9.x
                        && var11.y < var9.y + var9.height + 3.0F
                        && var11.y + var11.height + 3.0F > var9.y) {
                        var11.y = var5 ? var9.y - var11.height - 3.0F : var9.y + var9.height + 3.0F;
                        var7 = true;
                     }
                  }

                  if (!var7) {
                     break;
                  }
               }

               var11.y = Math.max(6.0F, Math.min(var11.canvasHeight - 6.0F - var11.height, var11.y));
            }
         }

         laidOutFrame = var0;
      }
   }

   static void remove(String var0) {
      boxes.remove(var0);
      laidOutFrame = Long.MIN_VALUE;
   }

   private static final class HudIdTable {
      final String id;
      final long frame;
      final float baseX;
      final float baseY;
      final float width;
      final float height;
      final float canvasHeight;
      final boolean placed;
      float x;
      float y;

      HudIdTable(String var1, long var2, float var4, float var5, float var6, float var7, float var8, boolean var9) {
         this.id = var1;
         this.frame = var2;
         this.baseX = var4;
         this.baseY = var5;
         this.width = var6;
         this.height = var7;
         this.canvasHeight = var8;
         this.placed = var9;
      }
   }
}
