package dev.purxiy.modules.mount;

public final class MountCurve {
   private float[] H;
   private float[] G;
   private Object s;
   private double z;
   private double o;
   private double b;
   private long X;
   private long U;
   private long C;
   private float d;
   private float k;
   private static final long a;

   public MountCurve.Data sample(boolean var1, Object var2, long var3, float[] var5, float[] var6, double var7, double var9, double var11) {
      long var13 = 92089401573973L;
      long var15 = var13 ^ 52162550790703L;
      if (var1) {
         this.C++;
         return null;
      } else {
         boolean var17 = this.s == var2 && this.H != null && var3 >= this.X && var3 - this.X < 500000000L;
         MountCurve.Data var18 = new MountCurve.Data(
            var17 ? this.H : (float[])var5.clone(),
            var17 ? this.G : (float[])var6.clone(),
            var17 ? (float)(var7 - this.z) : 0.0F,
            var17 ? (float)(var9 - this.o) : 0.0F,
            var17 ? (float)(var11 - this.b) : 0.0F
         );
         this.d = u(var15, var18.k, var5);
         this.k = u(var15, var18.G, var6);
         this.H = (float[])var5.clone();
         this.G = (float[])var6.clone();
         this.s = var2;
         this.X = var3;
         this.z = var7;
         this.o = var9;
         this.b = var11;
         this.U++;
         return var18;
      }
   }

   public void skip() {
      this.C++;
   }

   public void reset() {
      this.H = this.G = null;
      this.s = null;
      this.X = 0L;
      this.d = this.k = 0.0F;
   }

   public long accepted() {
      return this.U;
   }

   public long skipped() {
      return this.C;
   }

   public float lastViewChange() {
      return this.d;
   }

   public float lastProjectionChange() {
      return this.k;
   }

   private static float u(long var0, float[] var2, float[] var3) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      float var4 = 0.0F;

      for (int var5 = 0; var5 < (int)a; var5++) {
         long var6 = 137096268281977L;
         int var8 = var6 < 0L ? -1 : 1;
         var4 = Math.max(var4, Math.abs(var2[var5] - var3[var5]));
      }

      long var9 = 137096268281978L;
      int var7 = var9 < 0L ? -1 : 1;
      return var4;
   }

   static {
      long var0 = 78589899799795L;
      long var2 = 4299884024824823011L ^ var0;
      a = var2;
   }

   public record Data(float[] k, float[] G, float C, float j, float h) {
      public float[] view() {
         return this.k;
      }

      public float[] projection() {
         return this.G;
      }

      public float dx() {
         return this.C;
      }

      public float dy() {
         return this.j;
      }

      public float dz() {
         return this.h;
      }

      public float[] floatArrayState15() {
         return this.k;
      }

      public float[] floatArrayState32() {
         return this.G;
      }

      public float floatState16() {
         return this.C;
      }

      public float floatState38() {
         return this.j;
      }

      public float floatState5() {
         return this.h;
      }
   }
}
