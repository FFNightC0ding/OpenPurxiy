package dev.purxiy.modules.halo;

import dev.purxiy.loader.fabric.client.ClientBuilder;
import dev.purxiy.loader.fabric.client.ClientBuilder.TypeE;
import net.minecraft.class_10055;
import net.minecraft.class_10192;
import net.minecraft.class_1304;
import net.minecraft.class_1799;
import net.minecraft.class_238;
import net.minecraft.class_591;
import net.minecraft.class_9334;

final class HaloSupport4 {
   static final int X;
   private static final float J = 0.5F;
   private static final float Y = 1.0F;
   static int v = 0;

   private HaloSupport4() {
   }

   static void bounds(class_591 var0, class_10055 var1, float[] var2) {
      long var3 = 97881500983965L;
      long var5 = var3 ^ 5134965156440L;
      long var7 = var3 ^ 77358849032757L;
      M(var2, -4.0F, -8.0F, -4.0F, 4.0F, 0.0F, 4.0F);
      var2[6] = 4.0F;
      byte var9 = 0;
      float[] var10 = ClientBuilder.bounds(var0.field_3398);
      if (var10 != null && var10 != null) {
         M(var2, var10[0], var10[1], var10[2], var10[3], var10[4], var10[5]);
         var2[6] = var10[7];
         var9 = (byte)(var9 | 1);
      }

      if (var0.field_3394.field_3665) {
         float[] var11 = ClientBuilder.bounds(var0.field_3394);
         if (var11 != null) {
            if (var11 != null) {
               k(var2, var11[0], var11[1], var11[2], var11[3], var11[4], var11[5]);
               var2[6] = Math.max(var2[6], var11[7]);
               var9 = (byte)(var9 | 2);
            }
         } else if (var10 == null) {
            k(var2, -4.5F, -8.5F, -4.5F, 4.5F, 0.5F, 4.5F);
            var2[6] = 4.5F;
            var9 = (byte)(var9 | 4);
         }
      }

      if (V(var1.field_55309, var7)) {
         k(var2, -5.0F, -9.0F, -5.0F, 5.0F, 1.0F, 5.0F);
         var9 = (byte)(var9 | 8);
      }

      if (var1.field_55315 != null) {
         k(var2, -4.85F, -9.85F, -4.85F, 4.85F, 0.0F, 4.85F);
         var9 = (byte)(var9 | 16);
      }

      if (!var1.field_53467.method_65606()) {
         x(var5, var1.field_53467.method_72173(), var2);
         var9 = (byte)(var9 | 32);
      }

      if (var1.field_62758) {
         k(var2, -9.0F, -14.0F, -1.0F, 9.0F, -5.0F, 1.0F);
         var9 = (byte)(var9 | 64);
      }

      v = var9;
   }

   private static boolean V(class_1799 var0, long var1) {
      HaloDesign.ensureSourceClassInitialized();
      boolean var3 = var0.method_7960();
      long var4 = 34489974595752L;
      int var7 = var4 < 0L ? -1 : 1;
      if (var3) {
         return false;
      } else {
         class_10192 var7x = (class_10192)var0.method_58694(class_9334.field_54196);
         long var8 = 34489974595751L;
         int var9 = var8 < 0L ? -1 : 1;
         return var7x != null && var7x.comp_3174() == class_1304.field_6169 && var7x.comp_3176().isPresent();
      }
   }

   private static void x(long var0, class_238 var2, float[] var3) {
      HaloDesign.ensureSourceClassInitialized();
      long var4 = 103003044350148L;
      int var7 = var4 < 0L ? -1 : 1;
      if (var2 != null && (!(var2.method_17939() <= 0.0) || !(var2.method_17940() <= 0.0))) {
         float var7x = 10.0F;
         float var8 = (float)(-var2.field_1320) * var7x;
         float var9 = (float)(-var2.field_1323) * var7x;
         float var10 = -4.0F - (float)var2.field_1325 * var7x;
         float var11 = -4.0F - (float)var2.field_1322 * var7x;
         float var12 = (float)var2.field_1321 * var7x;
         float var13 = (float)var2.field_1324 * var7x;
         k(var3, var8, var10, var12, var9, var11, var13);
      } else {
         k(var3, -5.0F, -9.0F, -5.0F, 5.0F, 1.0F, 5.0F);
      }
   }

   private static void M(float[] var0, float var1, float var2, float var3, float var4, float var5, float var6) {
      var0[0] = var1;
      var0[1] = var2;
      var0[2] = var3;
      var0[3] = var4;
      var0[4] = var5;
      var0[5] = var6;
   }

   private static void k(float[] var0, float var1, float var2, float var3, float var4, float var5, float var6) {
      var0[0] = Math.min(var0[0], var1);
      var0[1] = Math.min(var0[1], var2);
      var0[2] = Math.min(var0[2], var3);
      var0[3] = Math.max(var0[3], var4);
      var0[4] = Math.max(var0[4], var5);
      var0[5] = Math.max(var0[5], var6);
   }

   static void bounds(TypeE var0, float[] var1) {
      M(var1, -4.0F, -8.0F, -4.0F, 4.0F, 0.0F, 4.0F);
      float[] var2 = var0 == null ? null : var0.headBounds();
      if (var2 != null && var0.fullModel()) {
         M(var1, var2[0], var2[1], var2[2], var2[3], var2[4], var2[5]);
         var1[6] = var2[7];
      } else if (var2 != null) {
         k(var1, var2[0], var2[1], var2[2], var2[3], var2[4], var2[5]);
         var1[6] = Math.max(4.0F, var2[7]);
      } else {
         k(var1, -4.5F, -8.5F, -4.5F, 4.5F, 0.5F, 4.5F);
         var1[6] = 4.5F;
      }
   }

   static {
      long var0 = 59752855324279L;
      long var2 = 4140751973906859632L ^ var0;
      X = (int)var2;
   }
}
