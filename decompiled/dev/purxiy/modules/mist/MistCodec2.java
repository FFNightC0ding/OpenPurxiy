package dev.purxiy.modules.mist;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Comparator;
import net.minecraft.class_1923;
import net.minecraft.class_2806;
import net.minecraft.class_2818;
import net.minecraft.class_2826;
import net.minecraft.class_2902;
import net.minecraft.class_638;
import net.minecraft.class_2902.class_2903;

final class MistCodec2 {
   static final int S = 512;
   static final int C = 32;
   static final int q = 100;
   private static final int E = 40;
   private static final int o = 40;
   private static final float O = 1440.0F;
   private static final float Q = 360.0F;
   private static final int Y = 96;
   private static final int g = 3;
   private static final int[] f = Z(114095058622580L);
   private final short[] j = new short[262144];
   private final short[] V = new short[262144];
   private final byte[] w = new byte[262144];
   static final byte c = 1;
   static final byte m = 2;
   private final long[] t = new long[1024];
   private final long[] e = new long[1024];
   private static final long r = Long.MIN_VALUE;
   private class_638 l;
   private int U = Integer.MIN_VALUE;
   private int x;
   private int A;
   private float p;
   private float R;
   private int B = Integer.MIN_VALUE;
   private int N;
   private final int[] z = new int[1024];
   private int h;
   private boolean a = true;
   private float T = 64.0F;
   private boolean i;
   private static final long[] b = new long[]{
      -4059246635653462896L,
      -113479905694395979L,
      -6678741542420639159L,
      -6680853055605168017L,
      6381529036639253238L,
      4319079693291868507L,
      -7463706432987384870L,
      -2953609036418923354L,
      7153792983984377918L,
      1260309202437151376L,
      -6625443627367363944L,
      7653025839122235893L,
      887969100409321109L,
      8307421818301027129L,
      6364929959782972595L,
      -6933657255928540412L,
      -2698899880198057103L,
      -2377698979742164828L,
      -902699681679545442L,
      1761766016837848121L,
      7116606840705650398L,
      8681228223144913324L
   };
   private static final long[] k = new long[]{-5694827901498095484L, 455108570712193224L, -1890769353307102630L, 2618265844561223902L};
   private static final Long[] n = new Long[4];

   MistCodec2() {
      Arrays.fill(this.t, Long.MIN_VALUE);
   }

   int base() {
      return this.U;
   }

   float regionalGround() {
      return this.T;
   }

   boolean allDirty() {
      return this.a;
   }

   int dirtyCount() {
      return this.h;
   }

   int dirty(int var1) {
      return this.z[var1];
   }

   void uploaded() {
      this.a = false;
      this.h = 0;
   }

   void forget() {
      this.l = null;
      Arrays.fill(this.t, Long.MIN_VALUE);
      Arrays.fill(this.w, (byte)0);
      this.a = true;
      this.h = 0;
      this.i = false;
      this.B = Integer.MIN_VALUE;
   }

   void update(class_638 var1, double var2, double var4, double var6, float var8) {
      long var9 = 54684624355779L;
      long var11 = var9 ^ 30291096781508L;
      long var13 = var9 ^ 113732482601575L;
      long var15 = var9 ^ 59308543369108L;
      if (var1 != this.l) {
         this.forget();
         this.l = var1;
      }

      int var17 = this.i ? Math.round(this.T) : (int)Math.floor(var4);
      if (this.U == Integer.MIN_VALUE || Math.abs(var17 - (this.U + 100)) > 40) {
         this.U = var17 - 100;
         this.a = true;
      }

      int var18 = (int)Math.floor(var2) >> 4;
      int var19 = (int)Math.floor(var6) >> 4;
      if (var18 != this.B || var19 != this.N) {
         int var20 = 0;

         for (int var21 = 0; var21 < f.length && var20 < 40; var21++) {
            int var22 = var18 + unpackX(f[var21]);
            int var23 = var19 + unpackZ(f[var21]);
            int var24 = slot(var22, var23);
            long var25 = class_1923.method_8331(var22, var23);
            if (this.t[var24] != var25) {
               this.x(var1, var22, var23, var24, var25, true, var13);
               var20++;
            }
         }

         if (var20 < 40) {
            this.B = var18;
            this.N = var19;
         }
      }

      for (this.p = Math.min(this.p + var8 * 1440.0F, 96.0F); this.p >= 1.0F; this.p--) {
         this.x = (this.x + 1) % f.length;
         this.G(var1, var15, var18 + unpackX(f[this.x]), var19 + unpackZ(f[this.x]));
      }

      byte var27 = 49;

      for (this.R = Math.min(this.R + var8 * 360.0F, 96.0F); this.R >= 1.0F; this.R--) {
         this.A = (this.A + 1) % var27;
         this.G(var1, var15, var18 + unpackX(f[this.A]), var19 + unpackZ(f[this.A]));
      }

      this.n(var11, var2, var6);
   }

   private void G(class_638 var1, long var2, int var4, int var5) {
      long var6 = 95434872034212L;
      MistRenderer3.ensureSourceClassInitialized();
      int var8 = slot(var4, var5);
      long var9 = class_1923.method_8331(var4, var5);
      this.x(var1, var4, var5, var8, var9, this.t[var8] != var9, var6);
   }

   private void x(class_638 var1, int var2, int var3, int var4, long var5, boolean var7, long var8) {
      long var10 = 20428062700848L;
      long var12 = 110959498293381L;
      MistRenderer3.ensureSourceClassInitialized();
      class_2818 var14 = var1.method_2935().method_2857(var2, var3, class_2806.field_12803, false);
      if (var14 == null) {
         if (!var7) {
            long var15 = 95434872034211L;
            int var53 = var15 < 0L ? -1 : 1;
            if (this.e[var4] == 0L) {
               return;
            }
         }

         this.t[var4] = var5;
         this.e[var4] = 0L;
         int var49 = Math.floorMod(var2, 32) * 16;
         int var16 = Math.floorMod(var3, 32) * 16;

         for (int var17 = 0; var17 < 16; var17++) {
            Arrays.fill(this.w, (var16 + var17) * 512 + var49, (var16 + var17) * 512 + var49 + 16, (byte)0);
            long var18 = 95434872034212L;
            int var20 = var18 < 0L ? -1 : 1;
         }

         long var52 = 95434872034211L;
         int var19 = var52 < 0L ? -1 : 1;
         this.F(var10, var4);
      } else {
         class_2902 var50 = var14.method_12032(class_2903.field_13203);
         class_2902 var51 = var14.method_12032(class_2903.field_13197);
         long var53 = o(var50.method_12598(), var12, var51.method_12598());
         long var19 = 95434872034211L;
         int var54 = var19 < 0L ? -1 : 1;
         if (!var7) {
            long var22 = 95434872034211L;
            int var24 = var22 < 0L ? -1 : 1;
            long var25 = 95434872034211L;
            int var25x = var25 < 0L ? -1 : 1;
            if (this.t[var4] == var5) {
               long var27 = 95434872034211L;
               int var27x = var27 < 0L ? -1 : 1;
               if (this.e[var4] == var53) {
                  return;
               }
            }
         }

         this.t[var4] = var5;
         this.e[var4] = var53;
         var54 = Math.floorMod(var2, 32) * 16;
         int var23 = Math.floorMod(var3, 32) * 16;
         class_2826[] var24 = var14.method_12006();
         int var55 = var14.method_31607();

         for (int var26 = 0; var26 < 16; var26++) {
            long var57 = 95434872034211L;
            int var58 = var57 < 0L ? -1 : 1;

            for (int var29 = 0; var29 < 16; var29++) {
               int var30 = var50.method_12603(var29, var26);
               int var31 = var51.method_12603(var29, var26);
               int var32 = (var23 + var26) * 512 + var54 + var29;
               long var33 = 95434872034212L;
               int var35 = var33 < 0L ? -1 : 1;
               this.j[var32] = (short)var30;
               this.V[var32] = (short)Math.max(var30, var31);
               byte var35x = 1;
               int var36 = var30 - 1 - var55 >> 4;
               long var37 = 95434872034211L;
               int var39 = var37 < 0L ? -1 : 1;
               long var39x = 95434872034212L;
               int var41 = var39x < 0L ? -1 : 1;
               if (var30 > var55) {
                  long var41x = 95434872034211L;
                  int var44 = var41x < 0L ? -1 : 1;
                  if (var36 >= 0 && var36 < var24.length) {
                     class_2826 var44x = var24[var36];
                     long var45 = 95434872034212L;
                     int var45x = var45 < 0L ? -1 : 1;
                     long var47x = 95434872034211L;
                     int var47x = var47x < 0L ? -1 : 1;
                     if (!var44x.method_38292() && !var44x.method_12255(var29, var30 - 1 & 15, var26).method_15769()) {
                        var35x = (byte)(var35x | 2);
                     }
                  }
               }

               this.w[var32] = var35x;
            }

            long var58x = 95434872034212L;
            int var65 = var58x < 0L ? -1 : 1;
         }

         long var56 = 95434872034212L;
         int var62 = var56 < 0L ? -1 : 1;
         this.F(var10, var4);
      }
   }

   private void F(long var1, int var3) {
      MistRenderer3.ensureSourceClassInitialized();
      if (!this.a) {
         for (int var4 = 0; var4 < this.h; var4++) {
            int var5 = this.z[var4];
            long var6 = 20428062700848L;
            int var8 = var6 < 0L ? -1 : 1;
            if (var5 == var3) {
               return;
            }
         }

         long var9 = 20428062700847L;
         int var10 = var9 < 0L ? -1 : 1;
         this.z[this.h++] = var3;
      }
   }

   private void n(long var1, double var3, double var5) {
      int var7 = 31015;
      int var8 = (int)Math.floor(var3);
      int var9 = (int)Math.floor(var5);
      MistRenderer3.ensureSourceClassInitialized();
      double var10 = 0.0;
      int var12 = 0;
      byte var13 = -24;

      while (var13 <= 24) {
         long var14 = 46389732852487L;
         int var16 = var14 < 0L ? -1 : 1;

         for (byte var17 = -24; var17 <= 24; var17 = (byte)(var17 + 3)) {
            var7 = Math.floorMod(var9 + var13, 512) * 512;
            int var18 = Math.floorMod(var8 + var17, 512);
            long var19 = 46389732852487L;
            int var22 = var19 < 0L ? -1 : 1;
            var22 = var7 + var18;
            long var23 = 46389732852487L;
            int var23x = var23 < 0L ? -1 : 1;
            if ((this.w[var22] & 1) != 0) {
               var10 += this.j[var22];
               var12++;
            }
         }

         var13 = (byte)(var13 + 3);
         long var28 = 46389732852486L;
         int var30 = var28 < 0L ? -1 : 1;
      }

      long var27 = 46389732852486L;
      int var28 = var27 < 0L ? -1 : 1;
      if (var12 > 0) {
         float var16 = (float)(var10 / var12);
         this.T = this.i ? this.T + (var16 - this.T) * 0.05F : var16;
         this.i = true;
      }
   }

   void encode(int var1, ByteBuffer var2) {
      int var3 = var1 % 32 * 16;
      int var4 = var1 / 32 * 16;

      for (int var5 = 0; var5 < 16; var5++) {
         this.encodeRow((var4 + var5) * 512 + var3, 16, var2);
      }
   }

   void encodeRow(int var1, int var2, ByteBuffer var3) {
      for (int var4 = var1; var4 < var1 + var2; var4++) {
         byte var5 = this.w[var4];
         boolean var6 = (var5 & 1) != 0;
         var3.put((byte)(var6 ? relative(this.j[var4], this.U) : 0));
         var3.put((byte)(var6 ? relative(this.V[var4], this.U) : 0));
         var3.put((byte)((var5 & 2) != 0 ? 255 : 0));
         var3.put((byte)(var6 ? 255 : 0));
      }
   }

   static int relative(int var0, int var1) {
      return Math.clamp((long)(var0 - var1), 0, 255);
   }

   static int slot(int var0, int var1) {
      return Math.floorMod(var0, 32) + Math.floorMod(var1, 32) * 32;
   }

   private static long o(long[] var0, long var1, long[] var3) {
      int var4 = 30177;
      long var5 = -3750763034362895579L;
      MistRenderer3.ensureSourceClassInitialized();

      for (long var10 : var0) {
         long var12 = 110959498293380L;
         int var15 = var12 < 0L ? -1 : 1;
         var5 = (var5 ^ var10) * 1099511628211L;
      }

      long[] var19 = var3;
      var4 = var3.length;
      long var21 = 110959498293380L;
      int var20 = var21 < 0L ? -1 : 1;
      var20 = var4;

      for (int var22 = 0; var22 < var20; var22++) {
         long var13 = var19[var22];
         long var15 = 110959498293380L;
         int var16 = var15 < 0L ? -1 : 1;
         var5 = (var5 ^ var13) * 1099511628211L;
      }

      long var23 = 110959498293381L;
      int var131 = var23 < 0L ? -1 : 1;
      return var5 == 0L ? 1L : var5;
   }

   private static int[] Z(long var0) {
      long var2 = 1460056192772L;
      byte var4 = 16;
      Integer[] var5 = new Integer[1024];
      int var6 = 0;
      int var7 = -var4;

      while (var7 < var4) {
         long var8 = 114095058622580L;
         int var11 = var8 < 0L ? -1 : 1;
         var11 = -var4;

         while (var11 < var4) {
            long var12 = 114095058622579L;
            int var14 = var12 < 0L ? -1 : 1;
            var5[var6++] = P(var11, var7, var2);
            var11++;
            long var14x = 114095058622580L;
            int var15 = var14x < 0L ? -1 : 1;
         }

         var7++;
         long var17 = 114095058622580L;
         int var22 = var17 < 0L ? -1 : 1;
      }

      Arrays.sort(var5, Comparator.comparingInt(var0x -> {
         int var1 = unpackX(var0x);
         int var2x = unpackZ(var0x);
         return var1 * var1 + var2x * var2x;
      }));
      int[] var16 = new int[var5.length];
      int var9 = 0;

      while (var9 < var5.length) {
         long var10 = 114095058622580L;
         int var13 = var10 < 0L ? -1 : 1;
         var16[var9] = var5[var9];
         var9++;
         long var13x = 114095058622580L;
         int var141 = var13x < 0L ? -1 : 1;
      }

      return var16;
   }

   private static int P(int var0, int var1, long var2) {
      return var0 + 128 << 8 | var1 + 128;
   }

   static int unpackX(int var0) {
      return (var0 >> 8) - 128;
   }

   static int unpackZ(int var0) {
      return (var0 & 0xFF) - 128;
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 13468) & 32767;
      if (n[var3] == null) {
         n[var3] = k[var3] ^ var1;
      }

      return n[var3];
   }
}
