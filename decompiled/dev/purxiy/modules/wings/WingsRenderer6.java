package dev.purxiy.modules.wings;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class WingsRenderer6 implements Listener {
   private final WingsRenderer4 Q;

   WingsRenderer6(WingsModule var1) {
      this.Q = new WingsRenderer4(var1);
   }

   public float height() {
      return 406.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.Q.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.Q.mouseDown(var1, var2, var3);
   }
}
