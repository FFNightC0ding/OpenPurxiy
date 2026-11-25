package dev.purxiy.modules.mount;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class MountRenderer11 implements Listener {
   private final MountRenderer3 C;

   MountRenderer11(MountModule var1) {
      this.C = new MountRenderer3(var1);
   }

   public float height() {
      return 616.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.C.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.C.mouseDown(var1, var2, var3);
   }

   public void mouseDrag(float var1, float var2) {
      this.C.mouseDrag(var1, var2);
   }

   public void mouseUp() {
      this.C.mouseUp();
   }

   public void close() {
      this.C.close();
   }
}
