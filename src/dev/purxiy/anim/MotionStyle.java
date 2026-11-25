package dev.purxiy.anim;

import dev.purxiy.loader.fabric.ClientServices;

public enum MotionStyle {
   MENU,
   ORBIT,
   HOVER,
   TOGGLE,
   PANEL,
   CASCADE;

   public float speed() {
      return ClientServices.motion(this.name());
   }

   public float dt(float var1) {
      return var1 * this.speed();
   }

   public static float stagger(float var0) {
      return reduced() ? 0.0F : var0 / CASCADE.speed();
   }

   public static boolean reduced() {
      return ClientServices.reducedMotion();
   }

   private static MotionStyle[] $values() {
      return new MotionStyle[]{MENU, ORBIT, HOVER, TOGGLE, PANEL, CASCADE};
   }
}
