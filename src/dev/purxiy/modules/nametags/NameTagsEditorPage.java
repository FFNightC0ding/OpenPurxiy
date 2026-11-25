package dev.purxiy.modules.nametags;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class NameTagsEditorPage implements Listener {
   private final NameTagsRenderer Y;

   NameTagsEditorPage(NameTagsModule var1) {
      this.Y = new NameTagsRenderer(var1);
   }

   public float height() {
      return 222.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.Y.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.Y.mouseDown(var1, var2, var3);
   }
}
