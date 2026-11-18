// Sourced By DevFFNightmare
package dev.purxiy.modules.trail;

import dev.purxiy.loader.fabric.CosmeticCatalog;
import dev.purxiy.ui.DrawContext;

final class TrailRenderer3 implements CosmeticCatalog.Listener {
   private final TrailRenderer2 c;

   TrailRenderer3(TrailModule var1) {
      this.c = new TrailRenderer2(var1);
   }

   @Override
   public float height() {
      return 307.0F;
   }

   @Override
   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.c.render(var1, var2, var3, var4, var5);
   }

   @Override
   public boolean mouseDown(float var1, float var2, int var3) {
      return this.c.mouseDown(var1, var2, var3);
   }
}
