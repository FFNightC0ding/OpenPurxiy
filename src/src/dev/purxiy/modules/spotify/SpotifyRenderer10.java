// Sourced By DevFFNightmare
package dev.purxiy.modules.spotify;

import dev.purxiy.loader.fabric.CosmeticCatalog;
import dev.purxiy.ui.DrawContext;

final class SpotifyRenderer10 implements CosmeticCatalog.Listener {
   private final SpotifyRenderer4 D;

   SpotifyRenderer10(SpotifyModule var1) {
      this.D = new SpotifyRenderer4(var1);
   }

   @Override
   public float height() {
      return 348.0F;
   }

   @Override
   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.D.render(var1, var2, var3, var4, var5);
   }

   @Override
   public boolean mouseDown(float var1, float var2, int var3) {
      return this.D.mouseDown(var1, var2, var3);
   }

   @Override
   public void mouseDrag(float var1, float var2) {
      this.D.mouseDrag(var1, var2);
   }

   @Override
   public void mouseUp() {
      this.D.mouseUp();
   }
}
