// Sourced By DevFFNightmare
package dev.purxiy.hud;

import dev.purxiy.render.RenderPipelines;
import net.minecraft.class_243;
import net.minecraft.class_4184;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3fc;
import org.joml.Vector4f;

public final class HudPlacement {
   public static final float DEFAULT_FOCAL = 1.428148F;
   private static final Matrix4f viewProj = new Matrix4f();
   private static final Vector4f clip = new Vector4f();
   private static double camX;
   private static double camY;
   private static double camZ;
   private static float rightX = 1.0F;
   private static float rightZ;
   private static float focal = 1.428148F;
   private static float partialTick;
   private static long frame = -4611686018427387904L;

   private HudPlacement() {
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2) {
      viewProj.set(var2).mul(var1);
      focal = Math.abs(var2.m11());
      class_243 var3 = var0.method_71156();
      camX = var3.field_1352;
      camY = var3.field_1351;
      camZ = var3.field_1350;
      Vector3fc var4 = var0.method_35689();
      float var5 = (float)Math.sqrt(var4.x() * var4.x() + var4.z() * var4.z());
      if (var5 > 1.0E-4F) {
         rightX = -var4.x() / var5;
         rightZ = -var4.z() / var5;
      }

      partialTick = var0.method_55437();
      frame = RenderPipelines.get().frame();
   }

   public static boolean fresh() {
      return RenderPipelines.get().frame() - frame <= 1L;
   }

   public static boolean project(double var0, double var2, double var4, float var6, float var7, float[] var8) {
      clip.set((float)(var0 - camX), (float)(var2 - camY), (float)(var4 - camZ), 1.0F);
      viewProj.transform(clip);
      if (clip.w <= 0.05F) {
         return false;
      } else {
         var8[0] = (clip.x / clip.w * 0.5F + 0.5F) * var6;
         var8[1] = (0.5F - clip.y / clip.w * 0.5F) * var7;
         return true;
      }
   }

   public static float distance(double var0, double var2, double var4) {
      double var6 = var0 - camX;
      double var8 = var2 - camY;
      double var10 = var4 - camZ;
      return (float)Math.sqrt(var6 * var6 + var8 * var8 + var10 * var10);
   }

   public static void across(double var0, double var2, float[] var4) {
      float var5 = (float)(var0 - camX);
      float var6 = (float)(var2 - camZ);
      float var7 = (float)Math.sqrt(var5 * var5 + var6 * var6);
      if (var7 < 0.001F) {
         var4[0] = rightX;
         var4[1] = rightZ;
      } else {
         float var8 = -var6 / var7;
         float var9 = var5 / var7;
         boolean var10 = var8 * rightX + var9 * rightZ < 0.0F;
         var4[0] = var10 ? -var8 : var8;
         var4[1] = var10 ? -var9 : var9;
      }
   }

   public static float focal() {
      return focal;
   }

   public static float partialTick() {
      return partialTick;
   }
}
