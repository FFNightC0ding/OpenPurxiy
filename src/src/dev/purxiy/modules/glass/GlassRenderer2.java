// Sourced By DevFFNightmare
package dev.purxiy.modules.glass;

import dev.purxiy.loader.fabric.CosmeticCatalog;
import dev.purxiy.ui.DrawContext;

final class GlassRenderer2 implements CosmeticCatalog.Listener {
   private final GlassRenderer3 Y;

   GlassRenderer2(GlassModule var1) {
      this.Y = new GlassRenderer3(var1);
   }

   @Override
   public float height() {
      return 242.0F;
   }

   @Override
   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.Y.render(var1, var2, var3, var4, var5);
   }

   @Override
   public boolean mouseDown(float var1, float var2, int var3) {
      return this.Y.mouseDown(var1, var2, var3);
   }

   @Override
   public void mouseDrag(float var1, float var2) {
      this.Y.mouseDrag(var1, var2);
   }

   @Override
   public void mouseUp() {
      this.Y.mouseUp();
   }
}
