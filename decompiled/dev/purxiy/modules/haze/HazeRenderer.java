package dev.purxiy.modules.haze;

import dev.purxiy.loader.fabric.CosmeticCatalog.Listener;
import dev.purxiy.ui.DrawContext;

final class HazeRenderer implements Listener {
   private final HazeRenderer3 U;

   HazeRenderer(HazeModule var1) {
      this.U = new HazeRenderer3(var1);
   }

   public float height() {
      return 258.0F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.U.render(var1, var2, var3, var4, var5);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      return this.U.mouseDown(var1, var2, var3);
   }
}
