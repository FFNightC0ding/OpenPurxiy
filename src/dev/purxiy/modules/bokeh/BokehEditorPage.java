package dev.purxiy.modules.bokeh;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class BokehEditorPage implements Listener {
   private final BokehEditorScreen B;

   BokehEditorPage(BokehModule var1) {
      this.B = new BokehEditorScreen(var1);
   }

   public float height() {
      return 216.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.B.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.B.mouseDown(var1, var2, var3);
   }
}
