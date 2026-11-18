// Sourced By DevFFNightmare
package dev.purxiy.modules.mount;

import dev.purxiy.loader.fabric.CosmeticCatalog;
import dev.purxiy.ui.DrawContext;

final class MountRenderer11 implements CosmeticCatalog.Listener {
   private final MountRenderer3 C;

   MountRenderer11(MountModule var1) {
      this.C = new MountRenderer3(var1);
   }

   @Override
   public float height() {
      return 616.0F;
   }

   @Override
   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.C.render(var1, var2, var3, var4, var5);
   }

   @Override
   public boolean mouseDown(float var1, float var2, int var3) {
      return this.C.mouseDown(var1, var2, var3);
   }

   @Override
   public void mouseDrag(float var1, float var2) {
      this.C.mouseDrag(var1, var2);
   }

   @Override
   public void mouseUp() {
      this.C.mouseUp();
   }

   @Override
   public void close() {
      this.C.close();
   }
}
