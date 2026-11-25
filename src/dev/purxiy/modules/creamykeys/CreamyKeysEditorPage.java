package dev.purxiy.modules.creamykeys;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class CreamyKeysEditorPage implements Listener {
   private final CreamyKeysRenderer u;

   CreamyKeysEditorPage(CreamyKeysModule var1) {
      this.u = new CreamyKeysRenderer(var1);
   }

   public float height() {
      return 360.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.u.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.u.mouseDown(var1, var2, var3);
   }
}
