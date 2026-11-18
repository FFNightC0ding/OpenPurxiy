// Sourced By DevFFNightmare
package dev.purxiy.modules.cape;

import dev.purxiy.loader.fabric.CosmeticEditorScreen;
import dev.purxiy.ui.DrawContext;

final class CapeRenderer2 extends CosmeticEditorScreen {
   CapeRenderer2(final CapeModule var1) {
      super(var1, new CosmeticEditorScreen.Listener() {
         private final CapeRenderer3 capeWardrobePanelState1 = new CapeRenderer3(var1);

         @Override
         public float height() {
            return CapeRenderer3.x;
         }

         @Override
         public void render(DrawContext var1x, float var2) {
            this.capeWardrobePanelState1.render(var1x, 0.0F, 0.0F, var2, true);
         }

         @Override
         public boolean mouseDown(float var1x, float var2, int var3) {
            return this.capeWardrobePanelState1.mouseDown(var1x, var2, var3);
         }

         @Override
         public void mouseDrag(float var1x, float var2) {
            this.capeWardrobePanelState1.mouseDrag(var1x, var2);
         }

         @Override
         public void mouseUp() {
            this.capeWardrobePanelState1.mouseUp();
         }
      }, null);
   }
}
