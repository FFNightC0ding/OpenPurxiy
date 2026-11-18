// Sourced By DevFFNightmare
package dev.purxiy.hud;

import net.minecraft.class_310;
import net.minecraft.class_638;
import net.minecraft.class_746;

public final class HudBounds {
   public class_310 mc;
   
   public class_746 player;
   
   public class_638 level;
   public float time;
   public float dt;
   public float partialTick;
   public boolean editing;
   public float width;
   public float height;

   public boolean live() {
      return this.player != null && this.level != null;
   }
}
