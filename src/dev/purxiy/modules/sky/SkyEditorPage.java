package dev.purxiy.modules.sky;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class SkyEditorPage implements Listener {
   private final SkyRenderer M;

   SkyEditorPage(SkyModule var1) {
      this.M = new SkyRenderer(var1);
   }

   public float height() {
      return 335.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.M.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.M.mouseDown(var1, var2, var3);
   }
}
