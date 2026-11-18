// Sourced By DevFFNightmare
package dev.purxiy.modules.skins3d;

import dev.purxiy.loader.fabric.CosmeticCatalog;
import dev.purxiy.ui.DrawContext;

final class Skins3DRenderer4 implements CosmeticCatalog.Listener {
   private final Skins3DRenderer2 z;

   Skins3DRenderer4(Skins3DModule var1) {
      this.z = new Skins3DRenderer2(var1);
   }

   @Override
   public float height() {
      return 474.0F;
   }

   @Override
   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.z.render(var1, var2, var3, var4, var5);
   }

   @Override
   public boolean mouseDown(float var1, float var2, int var3) {
      return this.z.mouseDown(var1, var2, var3);
   }

   @Override
   public void mouseDrag(float var1, float var2) {
      this.z.mouseDrag(var1, var2);
   }

   @Override
   public void mouseUp() {
      this.z.mouseUp();
   }
}
