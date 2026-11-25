package dev.purxiy.modules.hand;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class HandMovementRenderer5 implements Listener {
   private final HandMovementRenderer4 m;

   HandMovementRenderer5(HandMovementModule var1) {
      this.m = new HandMovementRenderer4(var1);
   }

   public float height() {
      return 100.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.m.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return var3 == 0 && this.m.mouseDown(var1, var2);
   }
}
