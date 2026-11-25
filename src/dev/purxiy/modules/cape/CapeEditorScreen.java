package dev.purxiy.modules.cape;

import dev.purxiy.loader.fabric.CosmeticEditorScreen;
import dev.purxiy.loader.fabric.CosmeticEditorScreen.Listener;
import dev.purxiy.ui.DrawContext;

final class CapeEditorScreen extends CosmeticEditorScreen {
   CapeEditorScreen(final CapeModule var1) {
      super(var1, new Listener() {
         private final CapeEditorControls capeWardrobePanelState1 = new CapeEditorControls(var1);

         public float height() {
            return CapeEditorControls.x;
         }

         public void render(DrawContext var1x, float var2) {
            this.capeWardrobePanelState1.render(var1x, 0.0F, 0.0F, var2, true);
         }

         public boolean mouseDown(float var1x, float var2, int var3) {
            return this.capeWardrobePanelState1.mouseDown(var1x, var2, var3);
         }

         public void mouseDrag(float var1x, float var2) {
            this.capeWardrobePanelState1.mouseDrag(var1x, var2);
         }

         public void mouseUp() {
            this.capeWardrobePanelState1.mouseUp();
         }
      }, null);
   }
}
