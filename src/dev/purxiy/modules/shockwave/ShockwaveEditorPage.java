package dev.purxiy.modules.shockwave;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class ShockwaveEditorPage implements Listener {
   private final ShockwaveEditorScreen E;

   ShockwaveEditorPage(ShockwaveModule var1) {
      this.E = new ShockwaveEditorScreen(var1);
   }

   public float height() {
      return 214.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.E.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.E.mouseDown(var1, var2, var3);
   }
}
