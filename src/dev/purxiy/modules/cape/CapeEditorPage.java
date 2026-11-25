package dev.purxiy.modules.cape;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class CapeEditorPage implements Listener {
   private final CapeEditorControls g;

   CapeEditorPage(CapeModule var1) {
      this.g = new CapeEditorControls(var1);
   }

   public float height() {
      return CapeEditorControls.x;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.g.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.g.mouseDown(var1, var2, var3);
   }

   public void mouseDrag(float var1, float var2) {
      this.g.mouseDrag(var1, var2);
   }

   public void mouseUp() {
      this.g.mouseUp();
   }
}
