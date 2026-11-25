package dev.purxiy.ui.clickgui;

import dev.purxiy.loader.fabric.MenuKeyBinding;

public final class StudioAnchor {
   private StudioAnchor() {
   }

   public static int key() {
      return MenuKeyBinding.menuKey();
   }

   public static void setKey(int var0) {
      MenuKeyBinding.setMenuKey(var0);
   }
}
