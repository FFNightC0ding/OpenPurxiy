package dev.purxiy.modules.ping;

import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudElementGroup;
import dev.purxiy.ui.Icons;
import net.minecraft.class_634;
import net.minecraft.class_640;

public final class PingHud extends HudElementGroup {
   public static boolean a;
   public static boolean b;

   public PingHud() {
      super("ping", "Ping", "Your connection to the server", "wifi", 0.0, 0.0);
   }

   private static int z(int var0, byte var1, int var2, HudBounds var3) {
      class_634 var4 = var3.mc.method_1562();
      if (var4 != null && var3.player != null) {
         class_640 var5 = var4.method_2871(var3.player.method_5667());
         return var5 == null ? -1 : var5.method_2959();
      } else {
         return -1;
      }
   }

   protected String value(HudBounds var1) {
      long var2 = 24537442885558L;
      long var4 = var2 ^ 62320737539631L;
      int var6 = (int)((var2 ^ 62320737539631L) >>> 32);
      int var7 = (int)((var2 ^ 62320737539631L) << 32 >>> 56);
      int var8 = (int)(var4 << 40 >>> 40);
      if (var1.mc.method_1496()) {
         return "Local";
      } else {
         int var9 = z(var6, (byte)var7, var8, var1);
         return var9 < 0 ? "—" : Integer.toString(var9);
      }
   }

   protected String sample() {
      return "24";
   }

   protected String label(HudBounds var1) {
      return var1.live() && var1.mc.method_1496() ? null : "ms";
   }

   protected int valueColor(HudBounds var1) {
      long var2 = 68272715740636L;
      long var4 = var2 ^ 18591940705349L;
      int var6 = (int)((var2 ^ 18591940705349L) >>> 32);
      int var7 = (int)((var2 ^ 18591940705349L) << 32 >>> 56);
      int var8 = (int)(var4 << 40 >>> 40);
      int var9 = var1.live() ? z(var6, (byte)var7, var8, var1) : 24;
      return var9 > 180 && !var1.mc.method_1496() ? Icons.PINK : Icons.TEXT;
   }
}
