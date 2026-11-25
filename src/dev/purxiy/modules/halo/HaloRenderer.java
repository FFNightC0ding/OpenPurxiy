package dev.purxiy.modules.halo;

import dev.purxiy.loader.fabric.CosmeticEditorScreen;
import dev.purxiy.loader.fabric.CosmeticEditorScreen.Listener;
import dev.purxiy.ui.DrawContext;

final class HaloRenderer extends CosmeticEditorScreen {
   HaloRenderer(final HaloModule var1) {
      super(var1, new Listener() {
         private final HaloEditorScreen haloWardrobePanelState1 = new HaloEditorScreen(var1);

         public float height() {
            return 493.0F;
         }

         public void render(DrawContext var1x, float var2) {
            this.haloWardrobePanelState1.render(var1x, 0.0F, 0.0F, var2, true);
         }

         public boolean mouseDown(float var1x, float var2, int var3) {
            return this.haloWardrobePanelState1.mouseDown(var1x, var2, var3);
         }
      }, () -> new HaloSupport(var1));
   }
}
