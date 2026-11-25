package dev.purxiy.modules.fire;

import dev.purxiy.loader.fabric.ClientServices;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_1058;
import net.minecraft.class_1079;
import net.minecraft.class_1080;
import net.minecraft.class_1088;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3298;
import net.minecraft.class_7771;

final class LowFireCache {
   static final LowFireCache Q = new LowFireCache(ClientServices.id("fire"), true);
   static final LowFireCache j = new LowFireCache(ClientServices.id("fire_vanilla"), false);
   private static final class_2960[] f = new class_2960[]{
      class_2960.method_60656("textures/block/fire_0.png"), class_2960.method_60656("textures/block/fire_1.png")
   };
   private static final int r = 512;
   private final class_2960 q;
   private final boolean b;
   private final LowFireCache.FireSprite[] B = new LowFireCache.FireSprite[2];
   private class_1043 h;
   private int F = 1;
   private int T = 1;
   private class_1058 K;
   private int e = -1;
   private int o = -1;
   private int i = -1;
   private class_1058 v;
   private static final String[] c = new String[6];
   private static final long[] d = new long[]{
      -5826426814020017178L,
      -8490234656817552414L,
      -7119552652103713910L,
      7326755687555511718L,
      7848553083717353688L,
      8016500729776362515L,
      -3786170466967389248L,
      -5579154825957998299L,
      -1999521603817208458L,
      -7831808966968973359L,
      -7214226975888354623L,
      5063955348486923432L
   };
   private static final Integer[] g = new Integer[12];

   private LowFireCache(class_2960 var1, boolean var2) {
      this.q = var1;
      this.b = var2;
   }

   class_2960 id() {
      return this.q;
   }

   boolean ensure(LowFireLoader var1) {
      long var2 = 81995955168412L;
      long var4 = var2 ^ 93125995461268L;
      class_310 var6 = class_310.method_1551();
      class_1058 var7 = var6.method_72703().method_73030(class_1088.field_5397);
      boolean var8 = !this.b || var1.F == this.e && var1.c == this.o && var1.g == this.i;
      if (this.h != null && var7 == this.K && var8) {
         return true;
      } else if (var7 == this.v) {
         return false;
      } else {
         try {
            this.J(var4, var6, var1);
            this.K = var7;
            this.e = var1.F;
            this.o = var1.c;
            this.i = var1.g;
            return true;
         } catch (Exception var10) {
            this.v = var7;
            ClientServices.LOGGER.error("SourceByDevFFnightmare DeObfDeCompByGuideX could not read the fire textures", var10);
            return false;
         }
      }
   }

   class_1043 texture() {
      return this.h;
   }

   int sprite(int var1, long var2, float[] var4) {
      LowFireCache.FireSprite var5 = this.B[var1];
      var4[0] = (float)var5.s / this.F;
      var4[1] = (float)(var5.s + var5.U) / this.F;
      var4[2] = (float)var5.v / this.T;
      var4[3] = var5.G;
      return var5.C[(int)Math.floorMod(var2, (long)var5.C.length)];
   }

   void frame(int var1, long var2, float[] var4) {
      LowFireCache.FireSprite var5 = this.B[var1];
      int var6 = var5.C[(int)Math.floorMod(var2, (long)var5.C.length)];
      var4[0] = (float)var5.s / this.F;
      var4[1] = (float)(var6 * var5.v) / this.T;
      var4[2] = (float)(var5.s + var5.U) / this.F;
      var4[3] = (float)((var6 + 1) * var5.v) / this.T;
   }

   private void J(long var1, class_310 var3, LowFireLoader var4) throws Exception {
      long var5 = 62871306410811L;
      long var7 = 104013241559842L;
      LowFireLoader.ensureSourceClassInitialized();
      class_1011[] var9 = new class_1011[2];
      boolean var10 = false;
      boolean var40 = false;
      boolean var39 = false /* VF: Semaphore variable */;

      try {
         var39 = true;
         var40 = true;
         var10 = true;

         for (int var47 = 0; var47 < 2; var47++) {
            class_3298 var12x = var3.method_1478().getResourceOrThrow(f[var47]);
            long var13 = 33130272988680L;
            int var55 = var13 < 0L ? -1 : 1;

            try (InputStream var16x = var12x.method_14482()) {
               var9[var47] = class_1011.method_4309(var16x);
            }

            this.B[var47] = M(var9[var47], var5, var12x.method_14481().method_43041(class_1079.field_55537), var47 == 0 ? 0 : this.B[0].U);
         }

         long var44 = 33130272988679L;
         int var14 = var44 < 0L ? -1 : 1;
         var14 = this.B[0].U + this.B[1].U;
         int var15 = Math.max(this.B[0].v * this.B[0].G, this.B[1].v * this.B[1].G);
         class_1011 var56 = new class_1011(var14, var15, true);
         float[] var17 = this.b ? ramp(colours(var4)) : null;
         float[] var18 = new float[3];

         for (int var19 = 0; var19 < 2; var19++) {
            long var20 = 33130272988680L;
            int var221 = var20 < 0L ? -1 : 1;
            k(var9[var19], var7, this.B[var19], var56, var17, var18);
         }

         label314: {
            long var59 = 33130272988679L;
            int var22 = var59 < 0L ? -1 : 1;
            if (this.h != null && this.F == var14) {
               long var22x = 33130272988679L;
               int var25 = var22x < 0L ? -1 : 1;
               if (this.T == var15) {
                  long var25x = 33130272988680L;
                  this.h.method_4526(var56);
                  this.h.method_4524();
                  break label314;
               }
            }

            this.h = new class_1043(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX " + this.q.method_12832()), var56);
            var3.method_1531().method_4616(this.q, this.h);
         }

         this.F = var14;
         this.T = var15;
         var10 = false;
         var40 = false;
         var39 = false;
      } finally {
         if (var39) {
            if (var40 && var10) {
               for (class_1011 var31 : var9) {
                  long var32 = 33130272988680L;
                  int var35 = var32 < 0L ? -1 : 1;
                  long var35x = 33130272988679L;
                  if (var31 != null) {
                     var31.close();
                  }
               }
            }
         }
      }

      if (var40 && var10) {
         for (class_1011 var31x : var9) {
            long var32 = 33130272988680L;
            int var65 = var32 < 0L ? -1 : 1;
            long var35 = 33130272988679L;
            int var72 = var35 < 0L ? -1 : 1;
            if (var31x != null) {
               var31x.close();
            }
         }
      }

      if (var10) {
         for (class_1011 var52 : var9) {
            long var54 = 33130272988680L;
            int var67 = var54 < 0L ? -1 : 1;
            long var57 = 33130272988679L;
            int var73 = var57 < 0L ? -1 : 1;
            if (var52 != null) {
               var52.close();
            }
         }
      }

      for (class_1011 var53 : var9) {
         long var55 = 33130272988679L;
         int var69 = var55 < 0L ? -1 : 1;
         long var58 = 33130272988680L;
         int var74 = var58 < 0L ? -1 : 1;
         long var60 = 33130272988679L;
         int var77 = var60 < 0L ? -1 : 1;
         if (var53 != null) {
            var53.close();
         }
      }

      long var47 = 33130272988679L;
      int var56 = var47 < 0L ? -1 : 1;
   }

   private static LowFireCache.FireSprite M(class_1011 var0, long var1, Optional<class_1079> var3, int var4) {
      LowFireLoader.ensureSourceClassInitialized();
      class_7771 var5 = var3.<class_7771>map(var1x -> var1x.method_24143(var0.method_4307(), var0.method_4323()))
         .orElse(new class_7771(var0.method_4307(), var0.method_4307()));
      int var6 = Math.max(1, var0.method_4307() / var5.comp_1049());
      int var7 = Math.max(1, var6 * (var0.method_4323() / var5.comp_1050()));
      ArrayList<Integer> var8 = new ArrayList();
      int var9 = var3.<Integer>map(class_1079::comp_3456).orElse(1);
      List<class_1080>var10 = var3.<List>flatMap(class_1079::comp_3453).orElse(List.of());
      int var11 = var10.isEmpty() ? 1 : 0;
      long var12 = 62871306410811L;
      int var32 = var12 < 0L ? -1 : 1;
      if (var11 != 0) {
         for (int var15 = 0; var15 < var7; var15++) {
            var11 = var8.size();
            long var16 = 62871306410811L;
            int var35 = var16 < 0L ? -1 : 1;
            if (var11 >= 512) {
               break;
            }

            for (int var19 = 0; var19 < var9; var19++) {
               long var20 = 62871306410811L;
               int var20x = var20 < 0L ? -1 : 1;
               var8.add(var15);
            }

            long var35x = 62871306410811L;
            int var38 = var35x < 0L ? -1 : 1;
         }

         long var30 = 62871306410810L;
         int var34 = var30 < 0L ? -1 : 1;
      } else {
         for (class_1080 var33 : var10) {
            long var17 = 62871306410811L;
            int var36 = var17 < 0L ? -1 : 1;
            var36 = var33.method_4691(var9);

            for (int var37 = 0; var37 < var36; var37++) {
               long var21 = 62871306410810L;
               int var24 = var21 < 0L ? -1 : 1;
               long var24x = 62871306410811L;
               var11 = var8.size();
               long var26x = 62871306410811L;
               if (var11 >= 512) {
                  break;
               }

               var8.add(Math.min(var33.comp_3451(), var7 - 1));
            }

            long var38 = 62871306410811L;
            int var211 = var38 < 0L ? -1 : 1;
         }
      }

      long var32x = 62871306410811L;
      int var351 = var32x < 0L ? -1 : 1;
      long var34 = 62871306410811L;
      int var41 = var34 < 0L ? -1 : 1;
      if (var8.isEmpty()) {
         var8.add(0);
      }

      return new LowFireCache.FireSprite(var4, var5.comp_1049(), var5.comp_1050(), var7, var8.stream().mapToInt(Integer::intValue).toArray());
   }

   private static void k(class_1011 var0, long var1, LowFireCache.FireSprite var3, class_1011 var4, float[] var5, float[] var6) {
      long var7 = 135759525649434L;
      LowFireLoader.ensureSourceClassInitialized();
      int var9 = Math.max(1, var0.method_4307() / var3.U);

      for (int var10 = 0; var10 < var3.G; var10++) {
         int var11 = var10 % var9 * var3.U;
         int var12 = var10 / var9 * var3.v;

         for (int var13 = 0; var13 < var3.v; var13++) {
            long var14 = 104013241559842L;
            int var24 = var14 < 0L ? -1 : 1;

            for (int var17 = 0; var17 < var3.U; var17++) {
               long var18 = 104013241559842L;
               int var20 = var18 < 0L ? -1 : 1;
               var20 = var0.method_61940(var11 + var17, var12 + var13);
               int var21 = var20 >>> 24;
               int var22 = var21 == 0 ? 0 : (var5 == null ? var20 : d(var7, var20, var21, var5, var6));
               var4.method_61941(var3.s + var17, var10 * var3.v + var13, var22);
            }

            long var24x = 104013241559842L;
            int var181 = var24x < 0L ? -1 : 1;
         }

         long var23 = 104013241559842L;
         int var15 = var23 < 0L ? -1 : 1;
      }
   }

   private static int d(long var0, int var2, int var3, float[] var4, float[] var5) {
      long var6 = 62685612347762L;
      float var8 = (var2 >> 8 & 0xFF) / 255.0F;
      float var9 = (var2 & 0xFF) / 255.0F;
      float var10 = (float)Math.pow(Math.clamp(((var8 + 0.5F * var9) / 1.5F - 0.16F) / 0.5F, 0.0F, 1.0F), 0.75);
      o(var4, var10, var5, var6);
      return var3 << 24 | channel(var5[0]) << 16 | channel(var5[1]) << 8 | channel(var5[2]);
   }

   static float[] colours(LowFireLoader var0) {
      long var1 = 72801480495885L;
      long var3 = var1 ^ 100286693325786L;
      float[] var5 = new float[9];
      a(var0.F, var5, 0, var3);
      a(var0.c, var5, 3, var3);
      a(var0.g, var5, 6, var3);
      return var5;
   }

   static float[] ramp(float[] var0) {
      long var1 = 87394874544536L;
      long var3 = var1 ^ 49472664698931L;
      float[] var5 = new float[12];

      for (int var6 = 0; var6 < 3; var6++) {
         var5[var6] = var0[3 + var6] + (var0[6 + var6] - var0[3 + var6]) * 0.45F;
         var5[3 + var6] = var0[3 + var6];
         var5[6 + var6] = var0[var6];
         var5[9 + var6] = var0[var6] + (1.0F - var0[var6]) * 0.7F;
      }

      Y(var3, var5, 0.35F);
      return var5;
   }

   private static void o(float[] var0, float var1, float[] var2, long var3) {
      LowFireLoader.ensureSourceClassInitialized();
      float var5;
      int var6 = (var5 = var1 - 0.3F) == 0.0F ? 0 : (var5 < 0.0F ? -1 : 1);
      long var7 = 62685612347762L;
      int var10 = var7 < 0L ? -1 : 1;
      float var11;
      byte var10x;
      if (var6 < 0) {
         var10x = 0;
         var11 = var1 / 0.3F;
         long var12 = 62685612347762L;
         int var13 = var12 < 0L ? -1 : 1;
      } else if (var1 < 0.62F) {
         var10x = 3;
         var11 = (var1 - 0.3F) / 0.32F;
         long var15 = 62685612347762L;
         int var19 = var15 < 0L ? -1 : 1;
      } else {
         var10x = 6;
         var11 = (var1 - 0.62F) / 0.38F;
      }

      float var16 = var11 * var11 * (3.0F - 2.0F * var11);

      for (int var13 = 0; var13 < 3; var13++) {
         var2[var13] = var0[var10x + var13] + (var0[var10x + 3 + var13] - var0[var10x + var13]) * var16;
      }

      long var17 = 62685612347761L;
      int var14 = var17 < 0L ? -1 : 1;
   }

   private static void Y(long var0, float[] var2, float var3) {
      LowFireLoader.ensureSourceClassInitialized();
      float var4 = 0.2126F * var2[0] + 0.7152F * var2[1] + 0.0722F * var2[2];

      for (int var5 = 0; var5 < 3; var5++) {
         var2[var5] = Math.max(0.0F, (var4 + (var2[var5] - var4) * 1.21F) * 0.867F);
      }

      long var8 = 109414105797034L;
      int var7 = var8 < 0L ? -1 : 1;
   }

   private static void a(int var0, float[] var1, int var2, long var3) {
      var1[var2] = (var0 >> 16 & 0xFF) / 255.0F;
      var1[var2 + 1] = (var0 >> 8 & 0xFF) / 255.0F;
      var1[var2 + 2] = (var0 & 0xFF) / 255.0F;
   }

   static int channel(float var0) {
      return Math.clamp((long)Math.round(var0 * 255.0F), 0, 255);
   }

   static void release() {
      for (LowFireCache var3 : new LowFireCache[]{Q, j}) {
         if (var3.h != null) {
            class_310.method_1551().method_1531().method_4615(var3.q);
            var3.h = null;
         }

         var3.K = null;
         var3.v = null;
      }
   }

   private record FireSprite(int s, int U, int v, int G, int[] C) {
      public int x() {
         return this.s;
      }

      public int width() {
         return this.U;
      }

      public int height() {
         return this.v;
      }

      public int frames() {
         return this.G;
      }

      public int[] ticks() {
         return this.C;
      }

      public int integerState13() {
         return this.s;
      }

      public int integerState58() {
         return this.U;
      }

      public int integerState4() {
         return this.v;
      }

      public int integerState18() {
         return this.G;
      }

      public int[] integerArrayState12() {
         return this.C;
      }
   }
}
