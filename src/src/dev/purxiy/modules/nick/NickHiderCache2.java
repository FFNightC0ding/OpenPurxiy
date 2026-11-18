// Sourced By DevFFNightmare
package dev.purxiy.modules.nick;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedDeque;

final class NickHiderCache2 {
   private static final int g;
   private static final int o;
   private static final int j = 0;
   private static final int b = 1;
   private static final int r;
   private static final ConcurrentLinkedDeque<NickHiderCache2> h;
   int[] N = new int[64];
   int[] W = new int[64];
   Object[] V = new Object[64];
   private Object[] M = new Object[64];
   private int[] F = new int[64];
   private boolean z;
   int D;
   private final NickHiderBuilder.TypeL y = new NickHiderBuilder.TypeL();
   int[] A = new int[64];
   int[] q = new int[64];
   NickHiderData[] K = new NickHiderData[64];
   int U;

   static NickHiderCache2 acquire() {
      NickHiderCache2 var0 = h.poll();
      return var0 != null ? var0 : new NickHiderCache2();
   }

   void release() {
      Arrays.fill(this.V, 0, this.D, null);
      Arrays.fill(this.M, 0, this.D, null);
      Arrays.fill(this.K, 0, this.U, null);
      this.y.clear();
      this.D = 0;
      this.U = 0;
      this.z = false;
      if (this.N.length <= 4096 && this.A.length <= 4096) {
         ConcurrentLinkedDeque var1 = h;
         if (var1.size() < 8) {
            var1.push(this);
         }
      }
   }

   void add(int var1, int var2,  Object var3,  Object var4) {
      if (this.D == this.N.length) {
         int var5 = this.D * 2;
         this.N = Arrays.copyOf(this.N, var5);
         this.W = Arrays.copyOf(this.W, var5);
         this.V = Arrays.copyOf(this.V, var5);
         this.M = Arrays.copyOf(this.M, var5);
         this.F = Arrays.copyOf(this.F, var5);
      }

      this.N[this.D] = var2;
      this.W[this.D] = var1;
      this.V[this.D] = var3;
      this.M[this.D] = var4;
      this.F[this.D] = NickHiderSupport.fold(var2);
      this.z |= var4 != null;
      this.D++;
   }

   boolean run(NickHiderBuilder var1) {
      long var2 = 3805978262154L;
      long var4 = var2 ^ 64442663788247L;
      int var6 = (int)((var2 ^ 64442663788247L) >>> 32);
      int var7 = (int)((var2 ^ 64442663788247L) << 32 >>> 48);
      int var8 = (int)(var4 << 48 >>> 48);
      if (this.D < var1.minLength()) {
         return false;
      } else {
         var1.find(this.N, this.F, this.z ? this.M : null, this.D, this.y);
         boolean var9 = false;

         for (int var10 = 0; var10 < this.y.o; var10++) {
            var9 |= this.y.I[var10].alias() != null;
         }

         if (var9) {
            this.y(var6, (short)var7, (short)var8);
         }

         return var9;
      }
   }

   private void y(int var1, short var2, short var3) {
      long var4 = 63677720831069L;
      long var6 = var4 ^ 84866274169086L;
      long var8 = var4 ^ 44136604822538L;
      this.U = 0;
      NickHiderCodec.ensureSourceClassInitialized();
      int var10 = 0;
      int var11 = 0;

      while (var11 < this.D) {
         short var12 = 9309;
         if (var10 < this.y.o) {
            var12 = 9309;
            if (this.y.p[var10] == var11) {
               int var13 = this.y.P[var10];
               NickHiderBuilder.Data var14 = this.y.I[var10++];
               if (var14.alias() != null) {
                  this.X(var14, var8, var14.alias(), var11, var13);
               } else {
                  for (int var15 = var11; var15 < var13; var15++) {
                     this.w(this.N[var15], var15, var6, null);
                  }
               }

               var11 = var13;
               continue;
            }
         }

         this.w(this.N[var11], var11, var6, null);
         var11++;
      }
   }

   private void X(NickHiderBuilder.Data var1, long var2, NickHiderCodec var4, int var5, int var6) {
      long var7 = 40308904134825L;
      long var9 = 26286060390140L;
      long var11 = 128391198946467L;
      NickHiderCodec.ensureSourceClassInitialized();
      int var13 = this.T(var7, var5, var6);
      int var14 = var13 == 1 ? 0 : this.o(var1, var5, var6, var9);
      int var15 = var4.length();
      int var16 = var6 - var5;

      for (int var17 = 0; var17 < var15; var17++) {
         int var18 = var4.y[var17];
         long var19 = 19577623332951L;
         if (19577623332951L == 0L) {
            boolean var10000 = false;
         } else {
            int var26 = var19 < 0L ? -1 : 1;
         }

         if (var14 == 1) {
            var18 = Character.toUpperCase(var18);
            long var22 = 19577623332950L;
            if (19577623332950L == 0L) {
               boolean var27 = false;
            } else {
               int var28 = var22 < 0L ? -1 : 1;
            }
         } else {
            long var25 = 19577623332950L;
            if (19577623332950L == 0L) {
               boolean var29 = false;
            } else {
               int var30 = var25 < 0L ? -1 : 1;
            }

            if (var14 == 2) {
               var18 = Character.toLowerCase(var18);
            }
         }

         this.w(NickHiderSupport.restyle(var18, var13), var5 + (int)((long)var17 * var16 / var15), var11, var4.paint(var17));
      }
   }

   private int T(long var1, int var3, int var4) {
      NickHiderCodec.ensureSourceClassInitialized();
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;

      for (int var8 = var3; var8 < var4; var8++) {
         int var9 = this.N[var8];
         long var10 = 40308904134825L;
         if (40308904134825L == 0L) {
            boolean var10000 = false;
         } else {
            int var28 = var10 < 0L ? -1 : 1;
         }

         int var13 = (NickHiderSupport.ignorable(var9) ? 1 : 0);
         long var14 = 40308904134824L;
         if (40308904134824L == 0L) {
            boolean var29 = false;
         } else {
            int var30 = var14 < 0L ? -1 : 1;
         }

         if ((var13 != 0)) {
            long var17 = 40308904134824L;
            if (40308904134824L == 0L) {
               boolean var31 = false;
            } else {
               int var32 = var17 < 0L ? -1 : 1;
            }
         } else {
            var7++;
            long var26 = 40308904134824L;
            if (40308904134824L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var26 < 0L ? -1 : 1;
            }

            var13 = this.N[var8];
            long var19 = 40308904134825L;
            if (40308904134825L == 0L) {
               boolean var35 = false;
            } else {
               int var36 = var19 < 0L ? -1 : 1;
            }

            switch (NickHiderSupport.variant(var13)) {
               case 1:
                  var5++;
                  long var21 = 40308904134825L;
                  if (40308904134825L == 0L) {
                     boolean var37 = false;
                  } else {
                     int var38 = var21 < 0L ? -1 : 1;
                  }
                  break;
               case 2:
                  var6++;
            }
         }
      }

      long var23 = 40308904134824L;
      if (40308904134824L == 0L) {
         boolean var39 = false;
      } else {
         int var40 = var23 < 0L ? -1 : 1;
      }

      long var12 = 40308904134825L;
      if (40308904134825L == 0L) {
         boolean var41 = false;
      } else {
         int var42 = var12 < 0L ? -1 : 1;
      }

      int var11 = var5 * 2;
      long var15 = 40308904134825L;
      if (40308904134825L == 0L) {
         boolean var43 = false;
      } else {
         int var44 = var15 < 0L ? -1 : 1;
      }

      if (var11 > var7) {
         var11 = 1;
      } else {
         long var27 = 40308904134824L;
         if (40308904134824L == 0L) {
            boolean var45 = false;
         } else {
            int var46 = var27 < 0L ? -1 : 1;
         }

         var11 = var6 * 2 > var7 ? 2 : 0;
      }

      return var11;
   }

   private int o(NickHiderBuilder.Data var1, int var2, int var3, long var4) {
      NickHiderCodec.ensureSourceClassInitialized();
      boolean var6 = false;
      boolean var7 = false;

      for (int var8 = var2; var8 < var3; var8++) {
         int var9 = NickHiderSupport.plain(this.N[var8]);
         long var10 = 26286060390139L;
         if (26286060390139L == 0L) {
            boolean var10000 = false;
         } else {
            int var18 = var10 < 0L ? -1 : 1;
         }

         var6 |= Character.isUpperCase(var9);
         var7 |= Character.isLowerCase(var9);
      }

      long var16 = 26286060390140L;
      if (26286060390140L == 0L) {
         boolean var19 = false;
      } else {
         int var20 = var16 < 0L ? -1 : 1;
      }

      if (var6 && !var7) {
         boolean var11 = var1.hasLower();
         long var12 = 26286060390139L;
         if (26286060390139L == 0L) {
            boolean var21 = false;
         } else {
            int var22 = var12 < 0L ? -1 : 1;
         }

         if (var11) {
            return 1;
         }
      }

      long var17 = 26286060390139L;
      if (26286060390139L == 0L) {
         boolean var23 = false;
      } else {
         int var24 = var17 < 0L ? -1 : 1;
      }

      if (var7) {
         long var14 = 26286060390139L;
         if (26286060390139L == 0L) {
            boolean var25 = false;
         } else {
            int var26 = var14 < 0L ? -1 : 1;
         }

         if (!var6 && var1.hasUpper()) {
            return 2;
         }
      }

      return 0;
   }

   private void w(int var1, int var2,  long var3, NickHiderData var5) {
      NickHiderCodec.ensureSourceClassInitialized();
      long var6 = 128391198946467L;
      if (128391198946467L == 0L) {
         boolean var10000 = false;
      } else {
         int var13 = var6 < 0L ? -1 : 1;
      }

      long var9 = 128391198946466L;
      if (128391198946466L == 0L) {
         boolean var14 = false;
      } else {
         int var15 = var9 < 0L ? -1 : 1;
      }

      if (this.U == this.A.length) {
         int var12 = this.U * 2;
         this.A = Arrays.copyOf(this.A, var12);
         this.q = Arrays.copyOf(this.q, var12);
         this.K = Arrays.copyOf(this.K, var12);
      }

      this.A[this.U] = var1;
      this.q[this.U] = var2;
      this.K[this.U] = var5;
      this.U++;
   }

   String output() {
      return new String(this.A, 0, this.U);
   }

   String input() {
      return new String(this.N, 0, this.D);
   }

   static {
      long var0 = 10577949980598L;
      long[] var2 = new long[3];
      int var3 = 0;
      String var4 = "\u000bÂÃ@Þów´\u0013U´¿Þówö¦T\u0002\u0093Þóg¶";
      int var5 = "\u000bÂÃ@Þów´\u0013U´¿Þówö¦T\u0002\u0093Þóg¶".length();
      byte var6 = 0;

      do {
         int var7 = var6;
         var6 = (byte)(var6 + 8);
         byte[] var8 = var4.substring(var7, var6).getBytes(StandardCharsets.ISO_8859_1);
         var7 = var3++;
         long var9 = (
               (var8[0] & 255L) << 56
                  | (var8[1] & 255L) << 48
                  | (var8[2] & 255L) << 40
                  | (var8[3] & 255L) << 32
                  | (var8[4] & 255L) << 24
                  | (var8[5] & 255L) << 16
                  | (var8[6] & 255L) << 8
                  | var8[7] & 255L
            )
            ^ var0;
         var2[var7] = var9;
      } while (var6 < var5);

      r = (int)var2[0];
      g = (int)var2[1];
      o = (int)var2[2];
      h = new ConcurrentLinkedDeque<>();
   }
}
