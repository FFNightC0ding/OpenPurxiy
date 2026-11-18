// Sourced By DevFFNightmare
package dev.purxiy.modules.wings;

import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import net.minecraft.class_12137;
import org.joml.Vector3f;

final class WingsRenderer3 {
   private static final int p = 780;
   private final WingsTransform.TypeR t = new WingsTransform.TypeR();
   private final Vector3f P = new Vector3f();
   private final int[] i = new int[780];
   private final float[] E = new float[780];
   private final float[] M = new float[4];
   private final float[] j = new float[4];
   private final int[] W = new int[4];
   private float b = 1.0F;
   private float x;
   private float D = 1.0F;
   private float F;

   private static final Integer[] c = new Integer[4];

   void draw(Canvas var1, WingDesign var2, int var3, float var4, float var5, WingsSupport2 var6, float var7, float var8, float var9, float var10, float var11) {
      long var12 = 26898191149235L;
      long var14 = var12 ^ 90529614716068L;
      long var16 = var12 ^ 25232700767810L;
      GpuTextureView var18 = WingsCache.view();
      GpuTextureView var19 = var2.e ? WingsCache.view() : WingsCache.glowView();
      class_12137 var20 = WingsCache.sampler();
      if (var18 != null && var19 != null && var20 != null && !(var1.alpha() <= 0.004F)) {
         this.t.clear();
         this.P.set(1.7F, 2.6F, 2.2F);
         WingsTransform.build(this.t, var6, true, this.P, var2, var3, var4, var5);
         this.P.z += 0.15F;
         WingsTransform.build(this.t, var6, false, this.P, var2, var3, var4, var5);
         WingsTransform.TypeR var21 = this.t;
         this.b = (float)Math.cos(var10);
         this.x = (float)Math.sin(var10);
         this.D = (float)Math.cos(var11);
         this.F = (float)Math.sin(var11);
         int var22 = var21.E / 4;
         float var23 = Float.MAX_VALUE;
         float var24 = -Float.MAX_VALUE;

         for (int var25 = 0; var25 < var22; var25++) {
            float var26 = 0.0F;

            for (int var27 = 0; var27 < 4; var27++) {
               int var28 = var25 * 4 + var27;
               var26 += this.d(var21.c[var28 * 3], var21.c[var28 * 3 + 2]);
            }

            this.E[var25] = var26 / 4.0F;
            this.i[var25] = var25;
            var23 = Math.min(var23, var26 / 4.0F);
            var24 = Math.max(var24, var26 / 4.0F);
         }

         b(this.i, this.E, var14, var22);
         float var30 = Math.max(0.5F, var24 - var23);

         for (int var31 = 0; var31 < var22; var31++) {
            int var32 = this.i[var31];
            float var33 = 0.84F + 0.16F * (this.E[var32] - var23) / var30;
            this.H(var1, var21, var32, var18, var20, false, var16, var33, var7, var8, var9);
            int var29 = var21.T[var32 * 4] | var21.T[var32 * 4 + 1] | var21.T[var32 * 4 + 2] | var21.T[var32 * 4 + 3];
            if (var29 >>> 24 > 2) {
               this.H(var1, var21, var32, var19, var20, true, var16, 1.0F, var7, var8, var9);
            }
         }
      } else {
         WingsCache.prepare();
         WingsCache.ready();
      }
   }

   private void H(
      Canvas var1,
      WingsTransform.TypeR var2,
      int var3,
      GpuTextureView var4,
      class_12137 var5,
      boolean var6,
      long var7,
      float var9,
      float var10,
      float var11,
      float var12
   ) {
      long var13 = 116662926371884L;
      WingsUpdater2.ensureSourceClassInitialized();
      int var15 = var3 * 4;
      float var16 = Float.MAX_VALUE;
      float var17 = -Float.MAX_VALUE;
      float var18 = Float.MAX_VALUE;
      float var19 = -Float.MAX_VALUE;

      for (int var20 = 0; var20 < 4; var20++) {
         long var21 = 15961357457136L;
         if (15961357457136L == 0L) {
            boolean var10000 = false;
         } else {
            int var63 = var21 < 0L ? -1 : 1;
         }

         int var24 = var15 + var20;
         var16 = Math.min(var16, var2.G[var24 * 2]);
         var17 = Math.max(var17, var2.G[var24 * 2]);
         var18 = Math.min(var18, var2.G[var24 * 2 + 1]);
         var19 = Math.max(var19, var2.G[var24 * 2 + 1]);
      }

      long var45 = 15961357457137L;
      if (15961357457137L == 0L) {
         boolean var64 = false;
      } else {
         int var65 = var45 < 0L ? -1 : 1;
      }

      for (int var23 = 0; var23 < 4; var23++) {
         int var47 = var15 + var23;
         long var25 = 15961357457136L;
         if (15961357457136L == 0L) {
            boolean var66 = false;
         } else {
            int var67 = var25 < 0L ? -1 : 1;
         }

         boolean var28 = var2.G[var47 * 2] - var16 < (var17 - var16) * 0.5F;
         boolean var29 = var19 - var2.G[var47 * 2 + 1] < (var19 - var18) * 0.5F;
         long var30 = 15961357457136L;
         if (15961357457136L == 0L) {
            boolean var68 = false;
         } else {
            int var69 = var30 < 0L ? -1 : 1;
         }

         int var32 = var29 ? (var28 ? 0 : 1) : (var28 ? 3 : 2);
         this.W[var32] = var47;
         float var33 = var2.c[var47 * 3];
         float var34 = var2.c[var47 * 3 + 2];
         this.M[var32] = var10 + (-var33 * this.b + var34 * this.x) * var12;
         this.j[var32] = var11 + (var2.c[var47 * 3 + 1] * this.D + this.d(var33, var34) * this.F) * var12;
      }

      float var46 = this.M[0];
      float var48 = this.j[0];
      float var49 = this.M[1] - var46;
      float var26 = this.j[1] - var48;
      float var27 = this.M[3] - var46;
      float var53 = this.j[3] - var48;
      float var55 = var16;
      float var56 = var17;
      int var31 = this.v(var2, this.W[0], var6, var9, var13);
      int var57 = this.v(var2, this.W[1], var6, var9, var13);
      int var58 = this.v(var2, this.W[2], var6, var9, var13);
      int var59 = this.v(var2, this.W[3], var6, var9, var13);
      float var35 = (float)Math.hypot(var49, var26);
      if (!(var35 < 0.001F)) {
         float var36 = (float)Math.atan2(var26, var49);
         float var37 = (float)Math.cos(var36);
         float var38 = (float)Math.sin(var36);
         float var39 = var27 * var37 + var53 * var38;
         float var40 = -var27 * var38 + var53 * var37;
         long var41 = 15961357457136L;
         if (15961357457136L == 0L) {
            boolean var70 = false;
         } else {
            int var71 = var41 < 0L ? -1 : 1;
         }

         if (var40 < 0.0F) {
            var46 = this.M[1];
            var48 = this.j[1];
            var49 = -var49;
            var26 = -var26;
            var27 = this.M[2] - var46;
            var53 = this.j[2] - var48;
            var55 = var17;
            var56 = var16;
            int var44 = var31;
            var31 = var57;
            var57 = var44;
            var44 = var58;
            var58 = var59;
            var59 = var44;
            var36 = (float)Math.atan2(var26, var49);
            var37 = (float)Math.cos(var36);
            var38 = (float)Math.sin(var36);
            var39 = var27 * var37 + var53 * var38;
            var40 = -var27 * var38 + var53 * var37;
         }

         if (!(var40 < 0.001F)) {
            var1.push();
            var1.translate(var46, var48);
            var1.rotate(var36);
            var1.shear(var39 / var40);
            var1.imageCorners(var4, var5, false, 0.0F, 0.0F, var35, var40, var55, var19, var56, var18, 0.0F, 0.0F, 0.0F, 0.0F, var31, var57, var58, var59);
            var1.pop();
         }
      }
   }

   private float d(float var1, float var2) {
      return var1 * this.x + var2 * this.b;
   }

   private int v(WingsTransform.TypeR var1, int var2, boolean var3, float var4, long var5) {
      WingsUpdater2.ensureSourceClassInitialized();
      if (var3) {
         return var1.T[var2];
      } else {
         int var7 = var1.g[var2];
         return BlendMode.withAlpha(BlendMode.lerp(-16777216, var7 | 0xFF000000, var4), (var7 >>> 24) / 255.0F);
      }
   }

   private static void b(int[] var0, float[] var1, long var2, int var4) {
      WingsUpdater2.ensureSourceClassInitialized();

      for (int var5 = 1; var5 < var4; var5++) {
         int var6 = var0[var5];
         float var7 = var1[var6];

         int var8;
         for (var8 = var5 - 1; var8 >= 0; var8--) {
            float var9;
            int var10 = (var9 = var1[var0[var8]] - var7) == 0.0F ? 0 : (var9 < 0.0F ? -1 : 1);
            long var11 = 81504128873494L;
            if (81504128873494L == 0L) {
               boolean var10000 = false;
            } else {
               int var17 = var11 < 0L ? -1 : 1;
            }

            long var14 = 81504128873495L;
            if (81504128873495L == 0L) {
               boolean var18 = false;
            } else {
               int var19 = var14 < 0L ? -1 : 1;
            }

            if (var10 <= 0) {
               break;
            }

            var0[var8 + 1] = var0[var8];
         }

         long var16 = 81504128873495L;
         if (81504128873495L == 0L) {
            boolean var20 = false;
         } else {
            int var21 = var16 < 0L ? -1 : 1;
         }

         var0[var8 + 1] = var6;
      }
   }

}
