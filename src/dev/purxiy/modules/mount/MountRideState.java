package dev.purxiy.modules.mount;

public final class MountRideState {
   private MountRideState.RideState y = MountRideState.RideState.F;
   private float T;
   private float G;
   private float R;
   private float s;
   private float f;
   private float E;
   private float m;
   private float z = 0.45F;

   public void summon(float var1) {
      this.y = MountRideState.RideState.W;
      this.T = this.G = this.f = this.E = this.m = 0.0F;
      this.R = this.s = var1;
   }

   public boolean enter(boolean var1) {
      return this.enter(var1, 0.45F);
   }

   public boolean enter(boolean var1, float var2) {
      if (this.y == MountRideState.RideState.W && var1) {
         this.y = MountRideState.RideState.E;
         this.z = Math.max(0.1F, var2);
         return true;
      } else {
         return false;
      }
   }

   public void leave() {
      if (this.y == MountRideState.RideState.E) {
         this.y = MountRideState.RideState.W;
      }
   }

   public void clear() {
      this.y = MountRideState.RideState.F;
      this.T = this.G = this.f = this.E = this.m = 0.0F;
   }

   public MountRideState.RideState state() {
      return this.y;
   }

   public boolean riding() {
      return this.y == MountRideState.RideState.E;
   }

   public boolean visible() {
      return this.y != MountRideState.RideState.F;
   }

   public void tick(float var1, float var2, float var3, boolean var4) {
      this.tick(var1, var2, var3, var4, 12.0F);
   }

   public void tick(float var1, float var2, float var3, boolean var4, float var5) {
      long var6 = 12984718576519L;
      long var8 = var6 ^ 28851534120152L;
      if (this.visible() && Float.isFinite(var1) && !(var1 <= 0.0F)) {
         float var10 = Math.min(var1, 0.1F);
         this.m += var10;
         this.G = this.T;
         this.s = this.R;
         float var11 = this.riding() ? 1.0F : 0.0F;
         this.T = var4 ? var11 : R(this.T, var11, var8, var10 / (this.riding() ? this.z : 0.45F));
         float var12 = this.riding() && Float.isFinite(var2) ? Math.clamp(var2, 0.0F, 40.0F) : 0.0F;
         this.f = this.f + (var12 - this.f) * (1.0F - (float)Math.exp(-var10 * 9.0F));
         float var13 = this.riding() && Float.isFinite(var3) ? wrap(var3 - this.R) : 0.0F;
         float var14 = Float.isFinite(var5) ? Math.clamp(var5, 1.0F, 30.0F) : 12.0F;
         float var15 = var13 * (1.0F - (float)Math.exp(-var10 * var14));
         this.R += var15;
         this.E = this.E + (Math.clamp(var13 / 40.0F, -1.0F, 1.0F) - this.E) * (1.0F - (float)Math.exp(-var10 * 8.0F));
      }
   }

   public void tickDriving(float var1, float var2, float var3, float var4, boolean var5) {
      this.tick(var1, var2, var3, var5);
      if (this.riding() && Float.isFinite(var1) && !(var1 <= 0.0F)) {
         if (Float.isFinite(var3)) {
            this.R = this.s + wrap(var3 - this.s);
         }

         if (Float.isFinite(var4)) {
            this.E = Math.clamp(var4, -1.0F, 1.0F);
         }
      }
   }

   public float boarding(float var1) {
      return this.G + (this.T - this.G) * Math.clamp(var1, 0.0F, 1.0F);
   }

   public float yaw(float var1) {
      return this.s + wrap(this.R - this.s) * Math.clamp(var1, 0.0F, 1.0F);
   }

   public float speed() {
      return this.f;
   }

   public float steering() {
      return this.E;
   }

   public float time(float var1) {
      return this.m + Math.clamp(var1, 0.0F, 1.0F) * 0.05F;
   }

   public static float wrap(float var0) {
      return ((var0 + 180.0F) % 360.0F + 360.0F) % 360.0F - 180.0F;
   }

   private static float R(float var0, float var1, long var2, float var4) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      long var5 = 19734183055198L;
      int var7 = var5 < 0L ? -1 : 1;
      return var0 < var1 ? Math.min(var1, var0 + var4) : Math.max(var1, var0 - var4);
   }

   public static enum RideState {
      F,
      W,
      E;
   }
}
