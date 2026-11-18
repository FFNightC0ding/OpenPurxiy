// Sourced By DevFFNightmare
package dev.purxiy.modules.targethud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.render.Canvas;
import net.minecraft.class_1044;
import net.minecraft.class_12137;
import net.minecraft.class_2960;
import net.minecraft.class_310;

final class TargetSupport {
   private static final float I = 2.0F;



   static void figure(Canvas var0, class_2960 var1, float var2, float var3) {
      long var4 = 57545142677754L;
      long var6 = var4 ^ 29298344321248L;
      float var8 = 2.0F;

      GpuTextureView var9;
      try {
         class_1044 var10 = class_310.method_1551().method_1531().method_4619(var1);
         var9 = var10.method_71659();
      } catch (IllegalStateException var14) {
         return;
      }

      boolean var15 = var9.getHeight(0) * 2 == var9.getWidth(0);
      class_12137 var11 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
      float var12 = var2 - 8.0F * var8;
      float var13 = var3 - 32.0F * var8;
      var0.shadow(var2 - 7.0F * var8, var3 - 1.0F, 14.0F * var8, 3.0F, 1.5F, 4.0F, 1711276032);
      k(var0, var9, var11, var15, var12 + 4.0F * var8, var13 + 20.0F * var8, 4, 12, 4, var6, 20, 4, 36, var8);
      k(var0, var9, var11, var15, var12 + 8.0F * var8, var13 + 20.0F * var8, 4, 12, 20, var6, 52, 4, 52, var8);
      k(var0, var9, var11, var15, var12 + 4.0F * var8, var13 + 8.0F * var8, 8, 12, 20, var6, 20, 20, 36, var8);
      k(var0, var9, var11, var15, var12, var13 + 8.0F * var8, 4, 12, 44, var6, 20, 44, 36, var8);
      k(var0, var9, var11, var15, var12 + 12.0F * var8, var13 + 8.0F * var8, 4, 12, 36, var6, 52, 52, 52, var8);
      k(var0, var9, var11, var15, var12 + 4.0F * var8, var13, 8, 8, 8, var6, 8, 40, 8, var8);
   }

   private static void k(
      Canvas var0,
      GpuTextureView var1,
      class_12137 var2,
      boolean var3,
      float var4,
      float var5,
      int var6,
      int var7,
      int var8,
      long var9,
      int var11,
      int var12,
      int var13,
      float var14
   ) {
      TargetHud.ensureSourceClassInitialized();
      float var15 = var3 ? 32.0F : 64.0F;
      long var16 = 51624578797594L;
      if (51624578797594L == 0L) {
         boolean var10000 = false;
      } else {
         int var32 = var16 < 0L ? -1 : 1;
      }

      long var19 = 51624578797594L;
      if (51624578797594L == 0L) {
         boolean var33 = false;
      } else {
         int var34 = var19 < 0L ? -1 : 1;
      }

      if (var3) {
         long var21 = 51624578797594L;
         if (51624578797594L == 0L) {
            boolean var35 = false;
         } else {
            int var36 = var21 < 0L ? -1 : 1;
         }

         long var24 = 51624578797594L;
         if (51624578797594L == 0L) {
            boolean var37 = false;
         } else {
            int var38 = var24 < 0L ? -1 : 1;
         }

         if (var11 >= 32) {
            long var26 = 51624578797594L;
            if (51624578797594L == 0L) {
               boolean var41 = false;
            } else {
               int var42 = var26 < 0L ? -1 : 1;
            }

            var8 = var8 == 20 ? 4 : 44;
            byte var28 = 20;
            var0.image(
               var1, var2, false, var4, var5, var6 * var14, var7 * var14, (var8 + var6) / 64.0F, var28 / var15, var8 / 64.0F, (var28 + var7) / var15, 0.0F, -1
            );
            return;
         }
      }

      float var30 = var6 * var14;
      float var22 = var7 * var14;
      float var23 = var8 / 64.0F;
      float var31 = var11;
      long var25 = 51624578797594L;
      if (51624578797594L == 0L) {
         boolean var39 = false;
      } else {
         int var40 = var25 < 0L ? -1 : 1;
      }

      var0.image(var1, var2, false, var4, var5, var30, var22, var23, var31 / var15, (var8 + var6) / 64.0F, (var11 + var7) / var15, 0.0F, -1);
      if (!var3 || var13 < 32) {
         var0.image(
            var1, var2, false, var4, var5, var6 * var14, var7 * var14, var12 / 64.0F, var13 / var15, (var12 + var6) / 64.0F, (var13 + var7) / var15, 0.0F, -1
         );
      }
   }

}
