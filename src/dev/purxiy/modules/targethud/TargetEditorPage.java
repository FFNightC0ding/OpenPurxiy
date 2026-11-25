package dev.purxiy.modules.targethud;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class TargetEditorPage implements Listener {
   private final TargetRenderer c;

   TargetEditorPage(TargetHud var1) {
      this.c = new TargetRenderer(var1);
   }

   public float height() {
      return 222.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.c.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.c.mouseDown(var1, var2, var3);
   }
}
