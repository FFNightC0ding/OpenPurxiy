package dev.purxiy.modules.serverinfo;

import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudElementGroup;
import net.minecraft.class_642;

public final class ServerHud extends HudElementGroup {
   public static int a = 0;
   public static int b = 0;

   public ServerHud() {
      super("server_info", "Server", "The address of the server you are on", "server", 0.0, 0.0);
   }

   protected String value(HudBounds var1) {
      if (var1.mc.method_1496()) {
         return "Singleplayer";
      } else {
         class_642 var2 = var1.mc.method_1558();
         return var2 == null ? "Offline" : var2.field_3761;
      }
   }

   protected String sample() {
      return "play.purxiy.dev";
   }
}
