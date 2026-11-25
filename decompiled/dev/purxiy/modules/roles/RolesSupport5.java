package dev.purxiy.modules.roles;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.render.Canvas;
import net.minecraft.class_1044;
import net.minecraft.class_12137;
import net.minecraft.class_2960;
import net.minecraft.class_310;

final class RolesSupport5 {
   private static final float h = 2.0F;

   static void figure(Canvas var0, class_2960 var1, float var2, float var3) {
      long var4 = 95776523028069L;
      long var6 = var4 ^ 23313953798678L;
      int var8 = (int)((var4 ^ 23313953798678L) >>> 48);
      int var9 = (int)((var4 ^ 23313953798678L) << 16 >>> 32);
      int var10 = (int)(var6 << 48 >>> 48);
      float var11 = 2.0F;

      GpuTextureView var12;
      try {
         class_1044 var13 = class_310.method_1551().method_1531().method_4619(var1);
         var12 = var13.method_71659();
      } catch (IllegalStateException var17) {
         return;
      }

      boolean var18 = var12.getHeight(0) * 2 == var12.getWidth(0);
      class_12137 var14 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
      float var15 = var2 - 8.0F * var11;
      float var16 = var3 - 32.0F * var11;
      var0.shadow(var2 - 7.0F * var11, var3 - 1.0F, 14.0F * var11, 3.0F, 1.5F, 4.0F, 1711276032);
      n((short)var8, var0, var12, var14, var9, var18, var15 + 4.0F * var11, var16 + 20.0F * var11, 4, 12, 4, 20, 4, 36, var11, (short)var10);
      n((short)var8, var0, var12, var14, var9, var18, var15 + 8.0F * var11, var16 + 20.0F * var11, 4, 12, 20, 52, 4, 52, var11, (short)var10);
      n((short)var8, var0, var12, var14, var9, var18, var15 + 4.0F * var11, var16 + 8.0F * var11, 8, 12, 20, 20, 20, 36, var11, (short)var10);
      n((short)var8, var0, var12, var14, var9, var18, var15, var16 + 8.0F * var11, 4, 12, 44, 20, 44, 36, var11, (short)var10);
      n((short)var8, var0, var12, var14, var9, var18, var15 + 12.0F * var11, var16 + 8.0F * var11, 4, 12, 36, 52, 52, 52, var11, (short)var10);
      n((short)var8, var0, var12, var14, var9, var18, var15 + 4.0F * var11, var16, 8, 8, 8, 8, 40, 8, var11, (short)var10);
   }

   private static void n(
      short var0,
      Canvas var1,
      GpuTextureView var2,
      class_12137 var3,
      int var4,
      boolean var5,
      float var6,
      float var7,
      int var8,
      int var9,
      int var10,
      int var11,
      int var12,
      int var13,
      float var14,
      short var15
   ) {
      RolesRegistry.ensureSourceClassInitialized();
      float var16 = var5 ? 32.0F : 64.0F;
      short var17 = 15475;
      var17 = 15475;
      if (var5) {
         var17 = 6589;
         short var18 = 0;
         if (var11 >= 32) {
            var18 = 15475;
            var10 = var10 == 20 ? 4 : 44;
            byte var25 = 20;
            var1.image(
               var2,
               var3,
               false,
               var6,
               var7,
               var8 * var14,
               var9 * var14,
               (var10 + var8) / 64.0F,
               var25 / var16,
               var10 / 64.0F,
               (var25 + var9) / var16,
               0.0F,
               -1
            );
            return;
         }
      }

      float var23 = var8 * var14;
      float var19 = var9;
      var1.image(var2, var3, false, var6, var7, var23, var19 * var14, var10 / 64.0F, var11 / var16, (var10 + var8) / 64.0F, (var11 + var9) / var16, 0.0F, -1);
      if (!var5 || var13 < 32) {
         var1.image(
            var2, var3, false, var6, var7, var8 * var14, var9 * var14, var12 / 64.0F, var13 / var16, (var12 + var8) / 64.0F, (var13 + var9) / var16, 0.0F, -1
         );
      }
   }
}
