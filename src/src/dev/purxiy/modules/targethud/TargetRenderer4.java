// Sourced By DevFFNightmare
package dev.purxiy.modules.targethud;

import dev.purxiy.loader.fabric.CosmeticCatalog;
import dev.purxiy.ui.DrawContext;

final class TargetRenderer4 implements CosmeticCatalog.Listener {
   private final TargetRenderer c;

   TargetRenderer4(TargetHud var1) {
      this.c = new TargetRenderer(var1);
   }

   @Override
   public float height() {
      return 222.0F;
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
