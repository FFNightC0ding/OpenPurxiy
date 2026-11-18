// Sourced By DevFFNightmare
package dev.purxiy.modules.halo;

import dev.purxiy.loader.fabric.CosmeticCatalog;
import dev.purxiy.ui.DrawContext;

final class HaloRenderer6 implements CosmeticCatalog.Listener {
   private final HaloRenderer2 R;

   HaloRenderer6(HaloModule var1) {
      this.R = new HaloRenderer2(var1);
   }

   @Override
   public float height() {
      return 493.0F;
   }

   @Override
   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.R.render(var1, var2, var3, var4, var5);
   }

   @Override
   public boolean mouseDown(float var1, float var2, int var3) {
      return this.R.mouseDown(var1, var2, var3);
   }
}
