package dev.purxiy.modules.mount;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import java.util.Arrays;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.class_12137;
import net.minecraft.class_12206;
import net.minecraft.class_1664;
import net.minecraft.class_1944;
import net.minecraft.class_2338;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_7920;
import net.minecraft.class_8685;
import org.joml.Matrix4f;
import org.joml.Vector3f;

final class MountVertexBatch {
   private static final float[] E = new float[]{1.0F, 0.8F, 0.6F};
   private static final int T = 360;
   private static final float[][] X = new float[3][1080];
   private static final float[][] A = new float[3][720];
   private static final int[] c = new int[3];
   private static float J = 64.0F;

   private MountVertexBatch() {
   }

   static void render(WorldRenderContext var0, MountFactory.MountSpec var1, MountGeometry var2, float var3, Matrix4f var4) {
      long var5 = 115876448186847L;
      long var7 = var5 ^ 50201756271529L;
      long var9 = var5 ^ 92832433825266L;
      class_310 var11 = class_310.method_1551();
      if (var11.field_1724 != null && var11.field_1687 != null && !var11.field_1724.method_5767() && !MountGarageScreen.capturing()) {
         float var12 = Math.clamp((MountInteraction.boarding(var3) - 0.66F) / 0.34F, 0.0F, 1.0F);
         if (!(var12 <= 0.0F)) {
            float var13 = var12 * var12 * (3.0F - 2.0F * var12);
            class_8685 var14 = var11.field_1724.method_52814();
            GpuTextureView var15 = var11.method_1531().method_4619(var14.comp_1626().comp_3627()).method_71659();
            if (var15 != null) {
               boolean var16 = var15.getHeight(0) * 2 == var15.getWidth(0);
               J = var16 ? 32.0F : 64.0F;
               boolean var17 = var14.comp_1629() == class_7920.field_41122;
               Arrays.fill(c, 0);
               H(var11, var1, var2, 1, var13, var16, var9, var17);
               H(var11, var1, var2, -1, var13, var16, var9, var17);
               float var18 = J(var11, var3, var7);
               class_12137 var19 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);

               for (int var20 = 0; var20 < 3; var20++) {
                  if (c[var20] != 0) {
                     MountDrawBatch.drawTriangles(
                        var0, var15, var19, var4, Arrays.copyOf(X[var20], c[var20] * 3), Arrays.copyOf(A[var20], c[var20] * 2), var18 * E[var20]
                     );
                  }
               }
            }
         }
      }
   }

   private static void H(class_310 var0, MountFactory.MountSpec var1, MountGeometry var2, int var3, float var4, boolean var5, long var6, boolean var8) {
      long var9 = 47587550228907L;
      long var11 = 23321539982481L;
      long var13 = 110122435751159L;
      MountFactory.MountSpec.ensureSourceClassInitialized();
      Vector3f var15 = var1.kind() == MountFactory.Vehicle.CAR ? e(var1, var2, var11, var3) : v(var1, var9, var2, var3);
      if (var15 != null) {
         boolean var16 = var1.kind() == MountFactory.Vehicle.CAR;
         Vector3f var17 = var1.eye();
         Vector3f var18 = new Vector3f(var17.x + var3 * (var16 ? 0.2F : 0.24F), var17.y - (var16 ? 0.36F : 0.3F), var17.z + (var16 ? -0.02F : 0.02F));
         Vector3f var19 = new Vector3f(var18).lerp(var15, var4);
         Vector3f var20 = new Vector3f(var18).lerp(var19, 0.5F);
         float var21 = var3;
         long var22 = 67128907912236L;
         int var25 = var22 < 0L ? -1 : 1;
         Vector3f var25x = var20.add(var21 * 0.05F, var16 ? -0.13F : -0.1F, 0.0F);
         Vector3f var26 = new Vector3f(var19).sub(var25x).normalize();
         Vector3f var27 = new Vector3f(var19).sub(new Vector3f(var26).mul(0.055F));
         Vector3f var28 = new Vector3f(var19).add(new Vector3f(var26).mul(0.03F));
         int var29 = var8 ? 3 : 4;
         long var30 = 67128907912237L;
         int var32 = var30 < 0L ? -1 : 1;
         var32 = var3 > 0 && !var5 ? 32 : 40;
         long var33 = 67128907912236L;
         int var35 = var33 < 0L ? -1 : 1;
         var35 = var3 > 0 && !var5 ? 48 : 16;
         float var36 = 0.03F;
         G(var13, var18, var25x, var36 * 1.15F, var32, var35, var29, 0, 5, var3);
         G(var13, var25x, var27, var36, var32, var35, var29, 5, 10, var3);
         G(var13, var27, var28, var36 * 1.3F, var32, var35, var29, 10, 12, var3);
         if (!var5) {
            boolean var37 = var0.field_1724.method_74091(var3 > 0 ? class_1664.field_7568 : class_1664.field_7570);
            long var38 = 67128907912236L;
            int var40 = var38 < 0L ? -1 : 1;
            if (var37) {
               var40 = var3 > 0 ? 48 : 40;
               int var41 = var3 > 0 ? 48 : 32;
               G(var13, var18, var25x, var36 * 1.15F + 0.004F, var40, var41, var29, 0, 5, var3);
               G(var13, var25x, var27, var36 + 0.004F, var40, var41, var29, 5, 10, var3);
            }
         }
      }
   }

   private static Vector3f e(MountFactory.MountSpec var0, MountGeometry var1, long var2, int var4) {
      MountFactory.PivotJoint var5 = var0.steer();
      if (var5 == null) {
         return null;
      } else {
         Vector3f var6 = new Vector3f(var5.axis()).normalize();
         Vector3f var7 = new Vector3f(0.0F, 1.0F, 0.0F).sub(new Vector3f(var6).mul(var6.y)).normalize();
         Vector3f var8 = new Vector3f(var6).cross(var7);
         float var9 = var4 * 1.42F + Math.clamp(var1.v * var5.ratio(), -0.9F, 0.9F);
         float var10 = class_3532.method_15362(var9) * var0.rimRadius();
         float var11 = class_3532.method_15374(var9) * var0.rimRadius();
         return new Vector3f(var5.pivot()).add(var7.x * var10 + var8.x * var11, var7.y * var10 + var8.y * var11, var7.z * var10 + var8.z * var11);
      }
   }

   private static Vector3f v(MountFactory.MountSpec var0, long var1, MountGeometry var3, int var4) {
      Vector3f var5 = var4 > 0 ? var0.gripLeft() : var0.gripRight();
      if (var5 == null) {
         return null;
      } else {
         MountFactory.PivotJoint var6 = var0.steer();
         if (var6 == null) {
            return new Vector3f(var5);
         } else {
            float var7 = (var0.bike() ? var3.G : var3.v) * var6.ratio();
            Vector3f var8 = var6.pivot();
            Vector3f var9 = var6.axis();
            return new Matrix4f()
               .translate(var8)
               .rotate(var7, var9.x, var9.y, var9.z)
               .translate(-var8.x, -var8.y, -var8.z)
               .transformPosition(new Vector3f(var5));
         }
      }
   }

   private static void G(long var0, Vector3f var2, Vector3f var3, float var4, int var5, int var6, int var7, int var8, int var9, int var10) {
      long var11 = 86820238238334L;
      byte var13 = 0;
      short var14 = 20214;
      int var15 = (int)(var11 << 32 >>> 32);
      MountFactory.MountSpec.ensureSourceClassInitialized();
      Vector3f var16 = new Vector3f(var3).sub(var2).normalize();
      Vector3f var17 = new Vector3f(var16).cross(0.0F, 1.0F, 0.0F);
      long var18 = 110122435751159L;
      int var21 = var18 < 0L ? -1 : 1;
      if (var17.lengthSquared() < 1.0E-6F) {
         var17.set(1.0F, 0.0F, 0.0F);
      }

      var17.normalize();
      Vector3f var21x = new Vector3f(var17).cross(var16).normalize();
      Vector3f var22 = C(var2, var17, var21x, -var4, var4);
      Vector3f var23 = C(var2, var17, var21x, var4, var4);
      Vector3f var24 = C(var2, var17, var21x, var4, -var4);
      Vector3f var25 = C(var2, var17, var21x, -var4, -var4);
      Vector3f var26 = C(var3, var17, var21x, -var4, var4);
      Vector3f var27 = C(var3, var17, var21x, var4, var4);
      Vector3f var28 = C(var3, var17, var21x, var4, -var4);
      Vector3f var29 = C(var3, var17, var21x, -var4, -var4);
      float var30 = var6 + 4 + var8;
      float var31 = var6 + 4 + var9;
      float var32 = var5;
      float var33 = var5 + 4;
      float var34 = var5 + 4 + var7;
      float var35 = var5 + 8 + var7;
      float var36 = var35 + var7;
      F(0, var22, var23, var13, var27, var26, var14, var35, var15, var30, var36, var31);
      F(2, var25, var24, var13, var28, var29, var14, var33, var15, var30, var33 + var7, var31);
      float var43 = var10 > 0 ? var34 : var32;
      var36 = (var10 > 0 ? var34 : var32) + 4.0F;
      F(1, var23, var24, var13, var28, var27, var14, var43, var15, var30, var36, var31);
      var43 = var10 > 0 ? var32 : var34;
      var36 = (var10 > 0 ? var32 : var34) + 4.0F;
      F(1, var22, var25, var13, var29, var26, var14, var43, var15, var30, var36, var31);
      F(1, var22, var23, var13, var24, var25, var14, var5 + 4, var15, var6, var5 + 4 + var7, var6 + 4);
      F(1, var26, var27, var13, var28, var29, var14, var5 + 4 + var7, var15, var6, var5 + 4 + 2 * var7, var6 + 4);
   }

   private static Vector3f C(Vector3f var0, Vector3f var1, Vector3f var2, float var3, float var4) {
      return new Vector3f(var0.x + var1.x * var3 + var2.x * var4, var0.y + var1.y * var3 + var2.y * var4, var0.z + var1.z * var3 + var2.z * var4);
   }

   private static void F(
      int var0, Vector3f var1, Vector3f var2, short var3, Vector3f var4, Vector3f var5, short var6, float var7, int var8, float var9, float var10, float var11
   ) {
      long var12 = 86820238238334L;
      long var14 = var12 ^ 13869253149843L;
      int var16 = (int)((var12 ^ 13869253149843L) >>> 32);
      int var17 = (int)((var12 ^ 13869253149843L) << 32 >>> 48);
      int var18 = (int)(var14 << 48 >>> 48);
      d(var0, var16, var1, (char)var17, (char)var18, var7, var9);
      d(var0, var16, var2, (char)var17, (char)var18, var10, var9);
      d(var0, var16, var4, (char)var17, (char)var18, var10, var11);
      d(var0, var16, var1, (char)var17, (char)var18, var7, var9);
      d(var0, var16, var4, (char)var17, (char)var18, var10, var11);
      d(var0, var16, var5, (char)var17, (char)var18, var7, var11);
   }

   private static void d(int var0, int var1, Vector3f var2, char var3, char var4, float var5, float var6) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      int var7 = c[var0];
      if (var7 < 360) {
         X[var0][var7 * 3] = var2.x;
         X[var0][var7 * 3 + 1] = var2.y;
         X[var0][var7 * 3 + 2] = var2.z;
         A[var0][var7 * 2] = var5 / 64.0F;
         A[var0][var7 * 2 + 1] = var6 / J;
         c[var0] = var7 + 1;
      }
   }

   private static float J(class_310 var0, float var1, long var2) {
      class_2338 var4 = class_2338.method_49638(MountInteraction.renderPosition(var1).method_1031(0.0, 1.0, 0.0));
      float var5 = var0.field_1687.method_8314(class_1944.field_9284, var4) / 15.0F;
      float var6 = var0.field_1687.method_8314(class_1944.field_9282, var4) / 15.0F;
      float var7 = (Float)var0.field_1773.method_19418().method_75756().method_75689(class_12206.field_64340, var1) * (float) (Math.PI / 180.0);
      float var8 = var0.field_1687.method_8597().comp_642() ? Math.clamp(class_3532.method_15362(var7) * 3.0F + 0.25F, 0.0F, 1.0F) : 0.0F;
      return Math.clamp(0.3F + var5 * (0.13F + 0.58F * var8) + var6 * 0.65F, 0.3F, 1.0F);
   }
}
