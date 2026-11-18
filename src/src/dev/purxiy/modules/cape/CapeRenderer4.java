// Sourced By DevFFNightmare
package dev.purxiy.modules.cape;

import dev.purxiy.loader.fabric.CosmeticCatalog;
import dev.purxiy.ui.DrawContext;

final class CapeRenderer4 implements CosmeticCatalog.Listener {
   private final CapeRenderer3 g;

   CapeRenderer4(CapeModule var1) {
      this.g = new CapeRenderer3(var1);
   }

   @Override
   public float height() {
      return CapeRenderer3.x;
   }

   @Override
   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.g.render(var1, var2, var3, var4, var5);
   }

   @Override
   public boolean mouseDown(float var1, float var2, int var3) {
      return this.g.mouseDown(var1, var2, var3);
   }

   @Override
   public void mouseDrag(float var1, float var2) {
      this.g.mouseDrag(var1, var2);
   }

   @Override
   public void mouseUp() {
      this.g.mouseUp();
   }
}
