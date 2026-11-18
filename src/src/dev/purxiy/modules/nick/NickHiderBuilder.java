// Sourced By DevFFNightmare
package dev.purxiy.modules.nick;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

final class NickHiderBuilder {
   static final int r;
   static final int K;
   static final int N;
   static final int P = 1;
   static final int Q = 0;
   static final NickHiderBuilder U;
   private final NickHiderBuilder.TypeE E;
   private final int p;
   private final int t;

   private NickHiderBuilder(NickHiderBuilder.TypeE var1, int var2, int var3) {
      this.E = var1;
      this.p = var2;
      this.t = var3;
   }

   boolean isEmpty() {
      return this.t == 0;
   }

   int size() {
      return this.t;
   }

   int minLength() {
      return this.p;
   }

   int find(int[] var1, int[] var2,  Object[] var3, int var4, NickHiderBuilder.TypeL var5) {
      long var6 = 42012666535671L;
      long var8 = var6 ^ 26822381474889L;
      int var10 = (int)((var6 ^ 122635096307203L) >>> 56);
      long var11 = (var6 ^ 122635096307203L) << 8 >>> 8;
      var5.o = 0;
      if (var4 >= this.p && this.t != 0) {
         for (int var13 = 0; var13 < var4; var13++) {
            NickHiderBuilder.TypeE var14 = this.E.child(var2[var13]);
            if (var14 != null && M(var8, var1, var2, var3, var13)) {
               NickHiderBuilder.Data var15 = var14.r != null && B(var1, var2, var3, var4, (byte)var10, var11, var13) ? var14.r : null;
               int var16 = var13 + 1;

               for (int var17 = var13 + 1; var17 < var4; var17++) {
                  if (!NickHiderSupport.ignorable(var1[var17])) {
                     var14 = var14.child(var2[var17]);
                     if (var14 == null) {
                        break;
                     }

                     if (var14.r != null && B(var1, var2, var3, var4, (byte)var10, var11, var17)) {
                        var15 = var14.r;
                        var16 = var17 + 1;
                     }
                  }
               }

               if (var15 != null) {
                  var5.add(var13, var16, var15);
                  var13 = var16 - 1;
               }
            }
         }

         return var5.o;
      } else {
         return 0;
      }
   }

   private static boolean M(long var0, int[] var2,  int[] var3, Object[] var4, int var5) {
      long var6 = 37743333875968L;
      NickHiderCodec.ensureSourceClassInitialized();
      if (var5 != 0) {
         int var8 = var2[var5];
         long var9 = 68516616440509L;
         if (68516616440509L == 0L) {
            boolean var10000 = false;
         } else {
            int var25 = var9 < 0L ? -1 : 1;
         }

         int var11 = var3[var5];
         long var12 = 68516616440510L;
         if (68516616440510L == 0L) {
            boolean var26 = false;
         } else {
            int var27 = var12 < 0L ? -1 : 1;
         }

         if (NickHiderSupport.isWord(var8, var11)) {
            int var15;
            for (var15 = var5 - 1; var15 > 0; var15--) {
               var8 = var2[var15];
               long var16 = 68516616440510L;
               if (68516616440510L == 0L) {
                  boolean var28 = false;
               } else {
                  int var29 = var16 < 0L ? -1 : 1;
               }

               boolean var18 = NickHiderSupport.ignorable(var8);
               long var19 = 68516616440509L;
               if (68516616440509L == 0L) {
                  boolean var30 = false;
               } else {
                  int var31 = var19 < 0L ? -1 : 1;
               }

               if (!var18) {
                  break;
               }
            }

            long var23 = 68516616440510L;
            if (68516616440510L == 0L) {
               boolean var32 = false;
            } else {
               int var33 = var23 < 0L ? -1 : 1;
            }

            var8 = var2[var15];
            long var24 = 68516616440510L;
            if (68516616440510L == 0L) {
               boolean var34 = false;
            } else {
               int var35 = var24 < 0L ? -1 : 1;
            }

            return !NickHiderSupport.isWord(var8, var3[var15]) || Y(var4, var15, var6, var5);
         }
      }

      return true;
   }

   private static boolean B(int[] var0, int[] var1,  Object[] var2, int var3, byte var4, long var5, int var7) {
      long var8 = 81075616000244L;
      long var10 = var8 ^ 118675001084404L;
      NickHiderCodec.ensureSourceClassInitialized();
      long var12 = 81075616000243L;
      if (81075616000243L == 0L) {
         boolean var10000 = false;
      } else {
         int var29 = var12 < 0L ? -1 : 1;
      }

      if (var7 != var3 - 1) {
         int var15 = var0[var7];
         long var16 = 81075616000244L;
         if (81075616000244L == 0L) {
            boolean var30 = false;
         } else {
            int var31 = var16 < 0L ? -1 : 1;
         }

         int var18 = var1[var7];
         long var19 = 81075616000243L;
         if (81075616000243L == 0L) {
            boolean var32 = false;
         } else {
            int var33 = var19 < 0L ? -1 : 1;
         }

         if (NickHiderSupport.isWord(var15, var18)) {
            int var21;
            for (var21 = var7 + 1; var21 < var3 - 1; var21++) {
               boolean var22 = false;
               long var23 = 81075616000244L;
               if (81075616000244L == 0L) {
                  boolean var34 = false;
               } else {
                  int var35 = var23 < 0L ? -1 : 1;
               }

               var15 = var0[var21];
               if (!NickHiderSupport.ignorable(var15)) {
                  break;
               }
            }

            long var27 = 81075616000243L;
            if (81075616000243L == 0L) {
               boolean var36 = false;
            } else {
               int var37 = var27 < 0L ? -1 : 1;
            }

            var15 = var0[var21];
            int var24 = 0;
            var24 = var1[var21];
            return !NickHiderSupport.isWord(var15, var24) || Y(var2, var7, var10, var21);
         }
      }

      return true;
   }

   private static boolean Y( Object[] var0, int var1, long var2, int var4) {
      NickHiderCodec.ensureSourceClassInitialized();
      return var0 != null && !Objects.equals(var0[var1], var0[var4]);
   }

   static {
      long var0 = 60802250913208L;
      long[] var2 = new long[3];
      int var3 = 0;
      String var4 = "Cdv\u0081 \u00915¼¿\u009e\u008a¥ \u00915»Ô\u008d-\u001a \u00915º";
      int var5 = "Cdv\u0081 \u00915¼¿\u009e\u008a¥ \u00915»Ô\u008d-\u001a \u00915º".length();
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
      N = (int)var2[2];
      K = (int)var2[1];
      U = new NickHiderBuilder.TypeR().build();
   }

   record Data(String c,  NickHiderCodec n, int b, boolean x, boolean L) {
      static NickHiderBuilder.Data of(String var0,  NickHiderCodec var1, int var2) {
         return new NickHiderBuilder.Data(var0, var1, var2, var0.chars().anyMatch(Character::isUpperCase), var0.chars().anyMatch(Character::isLowerCase));
      }

      public String canonical() {
         return this.c;
      }

      
      public NickHiderCodec alias() {
         return this.n;
      }

      public int priority() {
         return this.b;
      }

      public boolean hasUpper() {
         return this.x;
      }

      public boolean hasLower() {
         return this.L;
      }

      public String stringState11() {
         return this.c;
      }

      
      public NickHiderCodec formattedNicknameState2() {
         return this.n;
      }

      public int integerState44() {
         return this.b;
      }

      public boolean booleanState7() {
         return this.x;
      }

      public boolean booleanState51() {
         return this.L;
      }
   }

   private static final class TypeE {
      private static final int[] x = new int[0];
      private static final NickHiderBuilder.TypeE[] o = new NickHiderBuilder.TypeE[0];
      
      private NickHiderBuilder.TypeE[] C;
      private int[] z = x;
      private NickHiderBuilder.TypeE[] Z = o;
      private int i;
      
      NickHiderBuilder.Data r;

      
      NickHiderBuilder.TypeE child(int var1) {
         if (this.C != null && var1 < 128) {
            return this.C[var1];
         } else {
            for (int var2 = 0; var2 < this.i; var2++) {
               if (this.z[var2] == var1) {
                  return this.Z[var2];
               }
            }

            return null;
         }
      }

      NickHiderBuilder.TypeE childOrCreate(int var1) {
         NickHiderBuilder.TypeE var2 = this.child(var1);
         if (var2 != null) {
            return var2;
         } else {
            var2 = new NickHiderBuilder.TypeE();
            if (this.i == this.z.length) {
               int var3 = Math.max(2, this.i * 2);
               this.z = Arrays.copyOf(this.z, var3);
               this.Z = Arrays.copyOf(this.Z, var3);
            }

            this.z[this.i] = var1;
            this.Z[this.i] = var2;
            this.i++;
            if (this.C == null && this.i > 12) {
               this.C = new NickHiderBuilder.TypeE[128];

               for (int var5 = 0; var5 < this.i; var5++) {
                  if (this.z[var5] < 128) {
                     this.C[this.z[var5]] = this.Z[var5];
                  }
               }
            } else if (this.C != null && var1 < 128) {
               this.C[var1] = var2;
            }

            return var2;
         }
      }
   }

   static final class TypeL {
      int[] p = new int[4];
      int[] P = new int[4];
      NickHiderBuilder.Data[] I = new NickHiderBuilder.Data[4];
      int o;

      void add(int var1, int var2, NickHiderBuilder.Data var3) {
         if (this.o == this.p.length) {
            int var4 = this.o * 2;
            this.p = Arrays.copyOf(this.p, var4);
            this.P = Arrays.copyOf(this.P, var4);
            this.I = Arrays.copyOf(this.I, var4);
         }

         this.p[this.o] = var1;
         this.P[this.o] = var2;
         this.I[this.o] = var3;
         this.o++;
      }

      void clear() {
         Arrays.fill(this.I, 0, this.o, null);
         this.o = 0;
      }
   }

   static final class TypeR {
      private final NickHiderBuilder.TypeE q = new NickHiderBuilder.TypeE();
      private int I = Integer.MAX_VALUE;
      private int p;

      NickHiderBuilder.TypeR add(String var1, NickHiderBuilder.Data var2) {
         NickHiderBuilder.TypeE var3 = this.q;
         int var4 = 0;
         int var5 = 0;

         while (var5 < var1.length()) {
            int var6 = var1.codePointAt(var5);
            var5 += Character.charCount(var6);
            if (var4 <= 0 || !NickHiderSupport.ignorable(var6)) {
               var3 = var3.childOrCreate(NickHiderSupport.fold(var6));
               var4++;
            }
         }

         if (var4 == 0) {
            return this;
         } else {
            if (var3.r == null) {
               this.p++;
               this.I = Math.min(this.I, var4);
               var3.r = var2;
            } else if (var2.priority() > var3.r.priority()) {
               var3.r = var2;
            }

            return this;
         }
      }

      NickHiderBuilder build() {
         return new NickHiderBuilder(this.q, this.p == 0 ? Integer.MAX_VALUE : this.I, this.p);
      }
   }
}
