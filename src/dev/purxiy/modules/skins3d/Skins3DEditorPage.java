package dev.purxiy.modules.skins3d;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class Skins3DEditorPage implements Listener {
   private final Skins3DEditorScreen z;

   Skins3DEditorPage(Skins3DModule var1) {
      this.z = new Skins3DEditorScreen(var1);
   }

   public float height() {
      return 474.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.z.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.z.mouseDown(var1, var2, var3);
   }

   public void mouseDrag(float var1, float var2) {
      this.z.mouseDrag(var1, var2);
   }

   public void mouseUp() {
      this.z.mouseUp();
   }
}
