package dev.purxiy.modules.skins3d;

import java.util.random.RandomGenerator;

final class Skins3DUpdater4 {
   static final int q = 6;
   static final int h = 400;
   private final RandomGenerator W;
   private long x;
   private long B = -1L;
   private long v = -400L;
   private boolean X;

   Skins3DUpdater4(RandomGenerator var1) {
      this.W = var1;
   }

   boolean tick(int var1) {
      long var2 = 92315460116732L;
      long var4 = var2 ^ 112989781347959L;
      this.x++;
      boolean var6 = var1 <= 6;
      if (this.B < 0L) {
         this.X = var6;
         this.B = this.x + (var6 ? this.N(30, 60) : this.s(var4));
      } else if (var6 != this.X) {
         this.X = var6;
         this.B = var6 ? Math.min(this.B, this.x + this.N(30, 60)) : this.x + this.s(var4);
      }

      if (this.x >= this.B && this.x - this.v >= 400L) {
         this.v = this.x;
         this.B = this.x + (var6 ? this.N(600, 1000) : this.s(var4));
         return true;
      } else {
         return false;
      }
   }

   void reset() {
      this.x = 0L;
      this.B = -1L;
      this.v = -400L;
      this.X = false;
   }

   private int s(long var1) {
      return this.N(3600, 6000);
   }

   private int N(int var1, int var2) {
      return this.W.nextInt(var1, var2 + 1);
   }
}
