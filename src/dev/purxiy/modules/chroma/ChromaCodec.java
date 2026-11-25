package dev.purxiy.modules.chroma;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongLinkedOpenHashSet;
import java.util.Arrays;

public final class ChromaCodec {
   public static final int X = 512;
   public static final int j = 32;
   static final int I = 16;
   public static final int t = 8;
   public static final int G = 65535;
   public static final int E = 65534;
   public static final float p = 4.0F;
   static final int L = 6000;
   private final Long2ObjectMap<byte[]> V = new Long2ObjectOpenHashMap();
   private final LongLinkedOpenHashSet K = new LongLinkedOpenHashSet();
   private int k = Integer.MIN_VALUE;
   private int g = Integer.MIN_VALUE;
   private final LongArrayList T = new LongArrayList();
   private int x;
   private int Z = 1;
   public static int m = 0;

   public static int expiry(int var0) {
      return Math.floorMod(var0, 65534) + 1;
   }

   public static int remaining(int var0, int var1) {
      if (var0 == 0) {
         return Integer.MIN_VALUE;
      } else if (var0 == 65535) {
         return Integer.MAX_VALUE;
      } else {
         int var2 = Math.floorMod(var0 - 1 - var1, 65534);
         return var2 > 32767 ? var2 - 65534 : var2;
      }
   }

   public static int height(double var0) {
      return Math.clamp(Math.round((var0 + 64.0) / 1.5), 0, 255);
   }

   public static float heightOf(int var0) {
      return var0 * 1.5F - 64.0F;
   }

   public static float band(float var0) {
      return var0 > 0.0F ? 1.0F - B(9.0F, 18.0F, var0) : 1.0F - B(4.0F, 10.0F, -var0);
   }

   public static float time(int var0, int var1) {
      if (var0 == Integer.MIN_VALUE) {
         return 0.0F;
      } else {
         return var0 == Integer.MAX_VALUE ? 1.0F : Math.clamp((float)var0 / Math.max(var1, 1), 0.0F, 1.0F);
      }
   }

   static long key(int var0, int var1) {
      return (long)var0 << 32 | var1 & 4294967295L;
   }

   static int keyX(long var0) {
      return (int)(var0 >> 32);
   }

   static int keyZ(long var0) {
      return (int)var0;
   }

   public void paint(double var1, double var3, double var5, float var7, int var8, int var9) {
      long var10 = 71826099496423L;
      long var12 = var10 ^ 110498893460532L;
      if (!(var7 <= 0.0F)) {
         int var14 = height(var3);
         float var15 = var7 + 2.0F;
         int var16 = (int)Math.floor(var1 - var15);
         int var17 = (int)Math.floor(var1 + var15);
         int var18 = (int)Math.floor(var5 - var15);
         int var19 = (int)Math.floor(var5 + var15);

         for (int var20 = var18; var20 <= var19; var20++) {
            for (int var21 = var16; var21 <= var17; var21++) {
               double var22 = var21 + 0.5 - var1;
               double var24 = var20 + 0.5 - var5;
               float var26 = (float)Math.sqrt(var22 * var22 + var24 * var24);
               float var27 = strength(var7, var26);
               if (!(var27 <= 0.0F)) {
                  float var28 = Math.min(var26 / var15, 1.0F);
                  int var29 = var9 <= 0 ? '\uffff' : expiry(var8 + Math.round(var9 * (1.0F - 0.4F * var28 * var28)));
                  this.e(var21, var20, var12, Math.round(var27 * 255.0F), var29, var14, var8);
               }
            }
         }
      }
   }

   public static float strength(float var0, float var1) {
      return Math.clamp((var0 - var1) / 4.0F + 0.5F, 0.0F, 1.0F);
   }

   private void e(int var1, int var2, long var3, int var5, int var6, int var7, int var8) {
      short var9;
      long var10;
      byte[] var12;
      int var13;
      long var14 = 74829538193408L;
      var9 = 28753;
      var10 = 4127095371L;
      var12 = this.G(var1 >> 5, var2 >> 5, true, var14);
      var13 = ((var2 & 31) << 5 | var1 & 31) << 2;
      int var16 = (var12[var13] & 255) << 8 | var12[var13 + 1] & 255;
      int var17 = remaining(var16, var8);
      long var18 = 40874506424787L;
      int var21 = var18 < 0L ? -1 : 1;
      label47:
      if (var17 > 0) {
         var21 = Math.round((var12[var13 + 3] & 255) * time(var17, this.Z));
         long var22 = 40874506424786L;
         int var25 = var22 < 0L ? -1 : 1;
         long var25x = 40874506424787L;
         if (var21 > var5) {
            var5 = var21;
            var7 = var12[var13 + 2] & 255;
         }

         long var27x = 40874506424786L;
         short var29x = 16285;
         long var30 = 40874506424786L;
         int var30x = var30 < 0L ? -1 : 1;
         if (var16 != 65535) {
            long var32x = 40874506424786L;
            if (remaining(var6, var8) >= var17) {
               break label47;
            }
         }

         var6 = var16;
      }

      var12[var13] = (byte)(var6 >> 8);
      var12[var13 + 1] = (byte)var6;
      var12[var13 + 2] = (byte)var7;
      var12[var13 + 3] = (byte)var5;
      this.d(var9, var10, var1 >> 5, var2 >> 5);
   }

   public void setFade(int var1) {
      this.Z = Math.max(var1, 1);
   }

   public int fade() {
      return this.Z;
   }

   public float colour(int var1, int var2, double var3, int var5) {
      long var6 = 49820737555445L;
      long var8 = var6 ^ 115729359158261L;
      byte[] var10 = this.G(var1 >> 5, var2 >> 5, false, var8);
      if (var10 == null) {
         return 0.0F;
      } else {
         int var11 = ((var2 & 31) << 5 | var1 & 31) << 2;
         int var12 = (var10[var11] & 255) << 8 | var10[var11 + 1] & 255;
         float var13 = (var10[var11 + 3] & 255) / 255.0F;
         return var13 * time(remaining(var12, var5), this.Z) * band((float)(var3 - heightOf(var10[var11 + 2] & 255)));
      }
   }

   private byte[] G(int var1, int var2, boolean var3, long var4) {
      long var6 = key(var1, var2);
      byte[] var8 = (byte[])this.V.get(var6);
      if (var8 == null && var3) {
         var8 = new byte[4096];
         this.V.put(var6, var8);
      }

      return var8;
   }

   private void d(int var1, long var2, int var4, int var5) {
      long var6 = 123497321757259L;
      long var8 = var6 ^ 130423953909784L;
      if (this.l(var4, var5, var8)) {
         this.K.add(key(var4, var5));
      }
   }

   private boolean l(int var1, int var2, long var3) {
      long var5 = 7487278223955L;
      int var8 = var5 < 0L ? -1 : 1;
      if (this.k != Integer.MIN_VALUE) {
         long var8x = 7487278223955L;
         int var10 = var8x < 0L ? -1 : 1;
         if (var1 >= this.k) {
            long var10x = 7487278223955L;
            int var13 = var10x < 0L ? -1 : 1;
            if (var1 < this.k + 16) {
               long var13x = 7487278223954L;
               if (var2 >= this.g && var2 < this.g + 16) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public void follow(double var1, double var3) {
      int var5 = Math.floorDiv((int)Math.floor(var1), 32) - 8;
      int var6 = Math.floorDiv((int)Math.floor(var3), 32) - 8;
      if (var5 != this.k || var6 != this.g) {
         int var7 = this.k;
         int var8 = this.g;
         this.k = var5;
         this.g = var6;

         for (int var9 = var6; var9 < var6 + 16; var9++) {
            for (int var10 = var5; var10 < var5 + 16; var10++) {
               boolean var11 = var7 != Integer.MIN_VALUE && var10 >= var7 && var10 < var7 + 16 && var9 >= var8 && var9 < var8 + 16;
               if (!var11) {
                  this.K.add(key(var10, var9));
               }
            }
         }
      }
   }

   public void resend() {
      if (this.k != Integer.MIN_VALUE) {
         for (int var1 = this.g; var1 < this.g + 16; var1++) {
            for (int var2 = this.k; var2 < this.k + 16; var2++) {
               this.K.add(key(var2, var1));
            }
         }
      }
   }

   public int windowX() {
      return this.k == Integer.MIN_VALUE ? Integer.MIN_VALUE : this.k * 32;
   }

   public int windowZ() {
      return this.g == Integer.MIN_VALUE ? Integer.MIN_VALUE : this.g * 32;
   }

   public boolean synced() {
      return this.k != Integer.MIN_VALUE && this.K.isEmpty();
   }

   public int upload(ChromaCodec.Listener var1, int var2) {
      long var3 = 85823743457498L;
      long var5 = var3 ^ 79994322872969L;
      int var7 = 0;

      while (!this.K.isEmpty() && var7 < var2) {
         long var8 = this.K.removeFirstLong();
         int var10 = keyX(var8);
         int var11 = keyZ(var8);
         if (this.l(var10, var11, var5)) {
            var1.write(var10 * 32 & 511, var11 * 32 & 511, (byte[])this.V.get(var8));
            var7++;
         }
      }

      return var7;
   }

   public void sweep(int var1, int var2, int var3, double var4, double var6) {
      long var8 = 91423448991315L;
      int var10 = (int)((var8 ^ 38997695790104L) >>> 32);
      long var11 = (var8 ^ 38997695790104L) << 32 >>> 32;
      long var13 = var8 ^ 58323969181688L;
      if (this.x >= this.T.size()) {
         this.T.clear();
         this.T.addAll(this.V.keySet());
         this.x = 0;
         if (this.V.size() > 6000) {
            this.o(var13, var4, var6);
         }
      }

      int var15 = var2 <= 0 ? Integer.MAX_VALUE : var2;

      for (int var16 = 0; var16 < var3 && this.x < this.T.size(); var16++) {
         long var17 = this.T.getLong(this.x++);
         byte[] var19 = (byte[])this.V.get(var17);
         if (var19 != null) {
            boolean var20 = false;
            boolean var21 = false;

            for (byte var22 = 0; var22 < var19.length; var22 = (byte)(var22 + 4)) {
               int var23 = (var19[var22] & 255) << 8 | var19[var22 + 1] & 255;
               if (var23 != 0) {
                  int var24 = remaining(var23, var1);
                  if (var24 <= 0) {
                     var19[var22] = var19[var22 + 1] = var19[var22 + 2] = var19[var22 + 3] = 0;
                     var20 = true;
                  } else {
                     if (var24 > var15) {
                        int var25 = expiry(var1 + var15);
                        var19[var22] = (byte)(var25 >> 8);
                        var19[var22 + 1] = (byte)var25;
                        var20 = true;
                     }

                     var21 = true;
                  }
               }
            }

            int var26 = keyX(var17);
            int var27 = keyZ(var17);
            if (!var21) {
               this.V.remove(var17);
               var20 = true;
            }

            if (var20) {
               this.d(var10, var11, var26, var27);
            }
         }
      }
   }

   private void o(long var1, double var3, double var5) {
      short var7 = 28753;
      long var8 = 4127095371L;
      int var10 = Math.floorDiv((int)Math.floor(var3), 32);
      int var11 = Math.floorDiv((int)Math.floor(var5), 32);
      long[] var12 = this.V.keySet().toLongArray();
      long[] var13 = new long[var12.length];

      for (int var14 = 0; var14 < var12.length; var14++) {
         long var15 = keyX(var12[var14]) - var10;
         long var17 = 112346500727211L;
         int var32 = var17 < 0L ? -1 : 1;
         long var20 = keyZ(var12[var14]) - var11;
         var13[var14] = var15 * var15 + var20 * var20;
      }

      long var29 = 112346500727210L;
      int var30 = var29 < 0L ? -1 : 1;
      long[] var30x = (long[])var13.clone();
      Arrays.sort(var30x);
      long var18 = var30x[4500];

      for (int var31 = 0; var31 < var12.length; var31++) {
         long var21 = 112346500727210L;
         int var24 = var21 < 0L ? -1 : 1;
         long var24x;
         int var26 = (var24x = var13[var31] - var18) == 0L ? 0 : (var24x < 0L ? -1 : 1);
         long var27 = 112346500727211L;
         int var27x = var27 < 0L ? -1 : 1;
         if (var26 > 0) {
            this.V.remove(var12[var31]);
            this.d(var7, var8, keyX(var12[var31]), keyZ(var12[var31]));
         }
      }

      this.T.clear();
      long var32 = 112346500727210L;
      int var211 = var32 < 0L ? -1 : 1;
      this.T.addAll(this.V.keySet());
   }

   public void clear() {
      this.V.clear();
      this.K.clear();
      this.T.clear();
      this.x = 0;
      this.k = this.g = Integer.MIN_VALUE;
   }

   public int regionCount() {
      return this.V.size();
   }

   private static float B(float var0, float var1, float var2) {
      float var3 = Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

   public static void ensureSourceClassInitialized() {
   }

   @FunctionalInterface
   public interface Listener {
      void write(int var1, int var2, byte[] var3);
   }
}
