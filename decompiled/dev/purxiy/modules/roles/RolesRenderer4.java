package dev.purxiy.modules.roles;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class RolesRenderer4 implements Listener {
   private final RolesRenderer I;

   RolesRenderer4(RolesModule var1) {
      this.I = new RolesRenderer(var1);
   }

   public float height() {
      return 196.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.I.render(var1, var2, var3, var4, var5);
   }
}
