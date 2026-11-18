// Sourced By DevFFNightmare
package dev.purxiy.modules.spotify;

import dev.purxiy.loader.fabric.CosmeticEditorScreen;
import dev.purxiy.ui.DrawContext;

final class SpotifyRenderer8 extends CosmeticEditorScreen {
   SpotifyRenderer8(final SpotifyModule var1) {
      super(var1, new CosmeticEditorScreen.Listener() {
         private final SpotifyRenderer4 playbackLayoutPickerState1 = new SpotifyRenderer4(var1);

         @Override
         public float height() {
            return 348.0F;
         }

         @Override
         public void render(DrawContext var1x, float var2) {
            this.playbackLayoutPickerState1.render(var1x, 0.0F, 0.0F, var2, true);
         }

         @Override
         public boolean mouseDown(float var1x, float var2, int var3) {
            return this.playbackLayoutPickerState1.mouseDown(var1x, var2, var3);
         }

         @Override
         public void mouseDrag(float var1x, float var2) {
            this.playbackLayoutPickerState1.mouseDrag(var1x, var2);
         }

         @Override
         public void mouseUp() {
            this.playbackLayoutPickerState1.mouseUp();
         }
      }, null);
   }
}
