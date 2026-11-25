package dev.purxiy.modules.keybinds;

import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.PreferenceModule;

public final class KeybindsModule extends PreferenceModule {
   public static int q = 0;

   public KeybindsModule() {
      super("keybinds", "Keybinds", "Every key you have bound, in one place", "command");
   }

   protected AutoCloseable apply(ModuleContext var1) {
      return var1.own(CosmeticRegistry.register("keybinds", this.getClass().getClassLoader(), KeybindsScreen::new));
   }
}
