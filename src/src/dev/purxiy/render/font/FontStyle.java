// Sourced By DevFFNightmare
package dev.purxiy.render.font;

public enum FontStyle {
   LEFT(0.0F),
   CENTER(0.5F),
   RIGHT(1.0F);

   public final float factor;

   private FontStyle(float nullxx) {
      this.factor = nullxx;
   }

   private static FontStyle[] $values() {
      return new FontStyle[]{LEFT, CENTER, RIGHT};
   }
}
