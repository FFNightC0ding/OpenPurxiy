package dev.purxiy.modules.chroma;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class ChromaEditorPage implements Listener {
   private final ChromaEditorScreen y;

   ChromaEditorPage(ChromaModule var1) {
      this.y = new ChromaEditorScreen(var1);
   }

   public float height() {
      return 214.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.y.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.y.mouseDown(var1, var2, var3);
   }
}
