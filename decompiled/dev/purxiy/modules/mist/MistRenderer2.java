package dev.purxiy.modules.mist;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class MistRenderer2 implements Listener {
   private final MistRenderer4 J;

   MistRenderer2(MistModule var1) {
      this.J = new MistRenderer4(var1);
   }

   public float height() {
      return 216.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.J.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.J.mouseDown(var1, var2, var3);
   }
}
