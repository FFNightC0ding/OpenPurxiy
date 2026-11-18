// Sourced By DevFFNightmare
package dev.purxiy.modules.wings;

import dev.purxiy.loader.fabric.CosmeticEditorScreen;
import dev.purxiy.ui.DrawContext;

final class WingsRenderer extends CosmeticEditorScreen {
   WingsRenderer(final WingsModule var1) {
      super(var1, new CosmeticEditorScreen.Listener() {
         private final WingsRenderer4 wingWardrobePanelState1 = new WingsRenderer4(var1);

         @Override
         public float height() {
            return 406.0F;
         }

         @Override
         public void render(DrawContext var1x, float var2) {
            this.wingWardrobePanelState1.render(var1x, 0.0F, 0.0F, var2, true);
         }

         @Override
         public boolean mouseDown(float var1x, float var2, int var3) {
            return this.wingWardrobePanelState1.mouseDown(var1x, var2, var3);
         }
      }, () -> new WingsSupport3(var1));
   }
}
