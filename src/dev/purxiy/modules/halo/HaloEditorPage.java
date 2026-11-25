package dev.purxiy.modules.halo;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class HaloEditorPage implements Listener {
   private final HaloEditorScreen R;

   HaloEditorPage(HaloModule var1) {
      this.R = new HaloEditorScreen(var1);
   }

   public float height() {
      return 493.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.R.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.R.mouseDown(var1, var2, var3);
   }
}
