// Sourced By DevFFNightmare
package dev.purxiy.modules.knife;

import dev.purxiy.loader.fabric.CosmeticEditorScreen;
import dev.purxiy.ui.DrawContext;

final class KnifeRenderer2 extends CosmeticEditorScreen {
   KnifeRenderer2(final KnifeModule var1) {
      super(var1, new CosmeticEditorScreen.Listener() {
         private final KnifeRenderer6 knifeWardrobePanelState1 = new KnifeRenderer6(var1);

         @Override
         public float height() {
            return this.knifeWardrobePanelState1.height();
         }

         @Override
         public void render(DrawContext var1x, float var2) {
            this.knifeWardrobePanelState1.render(var1x, 0.0F, 0.0F, var2, true);
         }

         @Override
         public boolean mouseDown(float var1x, float var2, int var3) {
            return this.knifeWardrobePanelState1.mouseDown(var1x, var2, var3);
         }

         @Override
         public void mouseDrag(float var1x, float var2) {
            this.knifeWardrobePanelState1.mouseDrag(var1x, var2);
         }

         @Override
         public void mouseUp() {
            this.knifeWardrobePanelState1.mouseUp();
         }
      }, null);
   }
}
