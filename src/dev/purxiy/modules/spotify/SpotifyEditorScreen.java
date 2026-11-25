package dev.purxiy.modules.spotify;

import dev.purxiy.loader.fabric.CosmeticEditorScreen;
import dev.purxiy.loader.fabric.CosmeticEditorScreen.Listener;
import dev.purxiy.ui.DrawContext;

final class SpotifyEditorScreen extends CosmeticEditorScreen {
   SpotifyEditorScreen(final SpotifyModule var1) {
      super(var1, new Listener() {
         private final SpotifyRenderPass playbackLayoutPickerState1 = new SpotifyRenderPass(var1);

         public float height() {
            return 348.0F;
         }

         public void render(DrawContext var1x, float var2) {
            this.playbackLayoutPickerState1.render(var1x, 0.0F, 0.0F, var2, true);
         }

         public boolean mouseDown(float var1x, float var2, int var3) {
            return this.playbackLayoutPickerState1.mouseDown(var1x, var2, var3);
         }

         public void mouseDrag(float var1x, float var2) {
            this.playbackLayoutPickerState1.mouseDrag(var1x, var2);
         }

         public void mouseUp() {
            this.playbackLayoutPickerState1.mouseUp();
         }
      }, null);
   }
}
