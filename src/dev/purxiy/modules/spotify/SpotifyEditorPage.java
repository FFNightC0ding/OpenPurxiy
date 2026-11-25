package dev.purxiy.modules.spotify;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class SpotifyEditorPage implements Listener {
   private final SpotifyRenderPass D;

   SpotifyEditorPage(SpotifyModule var1) {
      this.D = new SpotifyRenderPass(var1);
   }

   public float height() {
      return 348.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.D.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.D.mouseDown(var1, var2, var3);
   }

   public void mouseDrag(float var1, float var2) {
      this.D.mouseDrag(var1, var2);
   }

   public void mouseUp() {
      this.D.mouseUp();
   }
}
