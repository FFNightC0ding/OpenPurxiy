package dev.purxiy.modules.place;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class PlaceAnimationRenderer implements Listener {
   private final PlaceAnimationRenderer2 s;

   PlaceAnimationRenderer(PlaceAnimationModule var1) {
      this.s = new PlaceAnimationRenderer2(var1);
   }

   public float height() {
      return 214.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.s.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.s.mouseDown(var1, var2, var3);
   }
}
