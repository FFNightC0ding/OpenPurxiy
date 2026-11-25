package dev.purxiy.modules.glass;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class GlassEditorPage implements Listener {
   private final GlassEditorScreen Y;

   GlassEditorPage(GlassModule var1) {
      this.Y = new GlassEditorScreen(var1);
   }

   public float height() {
      return 242.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.Y.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.Y.mouseDown(var1, var2, var3);
   }

   public void mouseDrag(float var1, float var2) {
      this.Y.mouseDrag(var1, var2);
   }

   public void mouseUp() {
      this.Y.mouseUp();
   }
}
