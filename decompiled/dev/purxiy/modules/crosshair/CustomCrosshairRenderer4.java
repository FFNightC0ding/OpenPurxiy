package dev.purxiy.modules.crosshair;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class CustomCrosshairRenderer4 implements Listener {
   private final CustomCrosshairRenderer p;

   CustomCrosshairRenderer4(CustomCrosshairModule var1) {
      this.p = new CustomCrosshairRenderer(var1);
   }

   public float height() {
      return 282.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.p.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.p.mouseDown(var1, var2, var3);
   }
}
