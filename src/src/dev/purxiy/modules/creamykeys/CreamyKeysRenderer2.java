// Sourced By DevFFNightmare
package dev.purxiy.modules.creamykeys;

import dev.purxiy.loader.fabric.CosmeticCatalog;
import dev.purxiy.ui.DrawContext;

final class CreamyKeysRenderer2 implements CosmeticCatalog.Listener {
   private final CreamyKeysRenderer u;

   CreamyKeysRenderer2(CreamyKeysModule var1) {
      this.u = new CreamyKeysRenderer(var1);
   }

   @Override
   public float height() {
      return 360.0F;
   }

   @Override
   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.u.render(var1, var2, var3, var4, var5);
   }

   @Override
   public boolean mouseDown(float var1, float var2, int var3) {
      return this.u.mouseDown(var1, var2, var3);
   }
}
