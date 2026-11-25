package dev.purxiy.modules.mount;

import dev.purxiy.anim.Animator;
import net.minecraft.class_310;

public final class MountViewState {
   static final float i = 74.0F;
   private static final float T = 0.2F;
   private static final float y = 0.35F;
   private static final float w = 84.0F;
   private static final Animator l = Animator.smooth(0.0F);
   private static final Animator P = Animator.smooth(0.0F);
   private static float p;
   private static float j;
   private static long B = 0L;
   private static double k = 1.0;

   private MountViewState() {
   }

   public static void frame() {
      long var0 = System.nanoTime();
      float var2 = B == 0L ? 0.0F : Math.min(0.1F, (float)(var0 - B) / 1.0E9F);
      B = var0;
      MountInteraction.frame(var2);
      P.target(MountInteraction.mounted() && !class_310.method_1551().field_1690.method_31044().method_31034() ? 1.0F : 0.0F).update(var2);
      boolean var3 = MountCameraReader.K.enabled() && MountCameraReader.K.L.enabled() && MountCameraState.cockpitActive();
      if (!var3) {
         j = 0.0F;
         p = 0.0F;
         l.snap(0.0F);
         k = 1.0;
      } else {
         if (MountInputRenderer.aimedAtScreen()) {
            p += var2;
            j = 0.0F;
         } else if ((j += var2) > 0.35F) {
            p = 0.0F;
         }

         l.target(p >= 0.2F ? 1.0F : 0.0F).update(var2);
      }
   }

   public static float fov(float var0) {
      float var1 = Math.clamp(P.value(), 0.0F, 1.0F);
      if (var1 > 0.001F && var0 > 84.0F) {
         double var2 = Math.tan(Math.toRadians(var0) / 2.0);
         double var4 = Math.tan(Math.toRadians(84.0) / 2.0);
         var0 = (float)Math.toDegrees(2.0 * Math.atan(var2 + (var4 - var2) * var1));
      }

      float var9 = Math.clamp(l.value(), 0.0F, 1.0F);
      if (!(var9 <= 0.001F) && !(var0 <= 74.0F)) {
         double var3 = Math.tan(Math.toRadians(var0) / 2.0);
         double var5 = Math.tan(Math.toRadians(74.0) / 2.0);
         double var7 = var3 + (var5 - var3) * var9;
         k = var3 / var7;
         return (float)Math.toDegrees(2.0 * Math.atan(var7));
      } else {
         k = 1.0;
         return var0;
      }
   }

   public static double mouseScale() {
      return k <= 1.0 ? 1.0 : 1.0 / Math.sqrt(k);
   }

   public static float focus() {
      return l.value();
   }
}
