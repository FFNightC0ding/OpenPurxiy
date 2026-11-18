// Sourced By DevFFNightmare
package dev.purxiy.anim;

public final class Animator {
   private static final float STEP = 0.004166667F;
   private float value;
   private float velocity;
   private float target;
   private final float stiffness;
   private final float damping;

   public Animator(float var1, float var2, float var3) {
      this.value = var1;
      this.target = var1;
      this.stiffness = var2;
      this.damping = var3;
   }

   public static Animator smooth(float var0) {
      return new Animator(var0, 220.0F, 30.0F);
   }

   public static Animator snappy(float var0) {
      return new Animator(var0, 420.0F, 30.0F);
   }

   public static Animator bouncy(float var0) {
      return new Animator(var0, 260.0F, 14.0F);
   }

   public Animator target(float var1) {
      this.target = var1;
      return this;
   }

   public Animator snap(float var1) {
      this.value = var1;
      this.target = var1;
      this.velocity = 0.0F;
      return this;
   }

   public Animator impulse(float var1) {
      this.velocity += var1;
      return this;
   }

   public float update(float var1) {
      float var3 = Math.min(var1, 0.1F);

      while (var3 > 0.0F) {
         float var2 = Math.min(var3, 0.004166667F);
         float var4 = -this.stiffness * (this.value - this.target) - this.damping * this.velocity;
         this.velocity += var4 * var2;
         this.value = this.value + this.velocity * var2;
         var3 -= var2;
      }

      if (this.settled()) {
         this.value = this.target;
         this.velocity = 0.0F;
      }

      return this.value;
   }

   public float value() {
      return this.value;
   }

   public float target() {
      return this.target;
   }

   public boolean settled() {
      return Math.abs(this.value - this.target) < 1.0E-4F && Math.abs(this.velocity) < 0.001F;
   }
}
