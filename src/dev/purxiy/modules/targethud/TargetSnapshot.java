package dev.purxiy.modules.targethud;

import java.util.Arrays;
import java.util.UUID;

public final class TargetSnapshot {
   public static final float G = -1000.0F;
   public final UUID h;
   public int s;
   public float q = -1000.0F;
   public int n;
   public int w;
   public int U;
   public int d;
   public float a = -1000.0F;
   public float o = -1000.0F;
   public int C;
   public int m;
   public int J;
   public int M;
   public int F;
   public int Y;
   public int R;
   public int z;
   public float l = -1000.0F;
   public float v = -1000.0F;
   public final float[] S = new float[4];
   public float B = -1000.0F;
   public float e = -1000.0F;
   public float O = -1000.0F;
   public int L;
   public int K;
   public int j;
   public int W;
   float V;
   int k;

   TargetSnapshot(UUID var1) {
      this.h = var1;
      Arrays.fill(this.S, -1000.0F);
   }

   void died(float var1) {
      this.O = var1;
      this.L = this.s;
      this.K = this.n;
      this.j = this.w;
      this.W = this.d;
      this.s = 0;
      this.q = -1000.0F;
      this.n = 0;
      this.w = 0;
      this.U = 0;
      this.d = 0;
      this.C = 0;
      this.m = 0;
      this.J = 0;
      this.M = 0;
      this.F = 0;
      this.Y = 0;
      this.R = 0;
      this.z = 0;
      this.k = 0;
      Arrays.fill(this.S, -1000.0F);
      this.e = -1000.0F;
   }
}
