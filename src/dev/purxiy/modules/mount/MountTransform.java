package dev.purxiy.modules.mount;

final class MountTransform {
   private static float N;

   private MountTransform() {
   }

   static void apply(MountDrawBuffers.MountVertex var0, MountModule var1, MountFactory.MountSpec var2, float var3, float var4, float var5, float var6) {
      boolean var7 = var1.V.enabled() && MountInteraction.mounted();
      boolean var8 = var2.kind() == MountFactory.Vehicle.BICYCLE;
      float var9 = var7 ? MountInteraction.startup(var6) : 0.0F;
      float var10 = 1.0F - Math.clamp(Math.max(var3 * var4, var5 * 0.8F) * 1.6F - 0.25F, 0.0F, 1.0F);
      N = N + ((var7 && var10 > 0.35F ? 1 : 0) - N) * 0.08F;
      var0.S = var8 ? 0.0F : var9;
      var0.q = var8 ? 0.0F : N * var9;
      var0.j = var8 ? 0.0F : var9 * Math.max(0.55F, N);
      var0.m = var8 ? 0.0F : (var7 ? MountInteraction.brake(var6) * var9 : 0.0F);
      float var11 = MountInteraction.steering(var6);
      boolean var12 = (MountInteraction.indicatorClock() + var6 * 0.05F) % 0.8F < 0.42F;
      var0.C = var7 && var11 > 0.22F && var12 ? 1.0F : 0.0F;
      var0.a = var7 && var11 < -0.22F && var12 ? 1.0F : 0.0F;
      var0.f = var7 && MountInteraction.dashboardGear() == -1 ? 1.0F : 0.0F;
      var0.F = var7 ? var10 : 0.0F;
      if (MountCameraReader.K == null) {
         var0.S = 0.0F;
      }
   }

   static float headlights() {
      return N;
   }
}
