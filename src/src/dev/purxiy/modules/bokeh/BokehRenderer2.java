// Sourced By DevFFNightmare
package dev.purxiy.modules.bokeh;

import dev.purxiy.loader.fabric.CosmeticCatalog;
import dev.purxiy.ui.DrawContext;

final class BokehRenderer2 implements CosmeticCatalog.Listener {
   private final BokehRenderer3 B;

   BokehRenderer2(BokehModule var1) {
      this.B = new BokehRenderer3(var1);
   }

   @Override
   public float height() {
      return 216.0F;
   }

   @Override
   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.B.render(var1, var2, var3, var4, var5);
   }

   @Override
   public boolean mouseDown(float var1, float var2, int var3) {
      return this.B.mouseDown(var1, var2, var3);
   }
}
