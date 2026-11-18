// Sourced By DevFFNightmare
package dev.purxiy.modules.knife;

import dev.purxiy.loader.fabric.CosmeticCatalog;
import dev.purxiy.ui.DrawContext;

final class KnifeRenderer8 implements CosmeticCatalog.Listener {
   private final KnifeRenderer6 M;

   KnifeRenderer8(KnifeModule var1) {
      this.M = new KnifeRenderer6(var1);
   }

   @Override
   public float height() {
      return this.M.height();
   }

   @Override
   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.M.render(var1, var2, var3, var4, var5);
   }

   @Override
   public boolean mouseDown(float var1, float var2, int var3) {
      return this.M.mouseDown(var1, var2, var3);
   }

   @Override
   public void mouseDrag(float var1, float var2) {
      this.M.mouseDrag(var1, var2);
   }

   @Override
   public void mouseUp() {
      this.M.mouseUp();
   }
}
