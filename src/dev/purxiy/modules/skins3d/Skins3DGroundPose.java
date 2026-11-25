package dev.purxiy.modules.skins3d;

public final class Skins3DGroundPose {
   public Skins3DApi L;
   public float W;
   public float H;
   public float N;
   public float f;
   public float d;
   public float c;
   public float j = 1.0F;
   public float U;
   public float V;
   public float p;
   public int k;
   public float R;
   public boolean h = true;
   public boolean r = true;
   public boolean u;
   public int S;
   public float J;
   public final float[] C = new float[]{0.0F, 0.5F, -0.87F};
   public float K;
   public float E;

   public float idle() {
      return (1.0F - this.W) * (1.0F - this.f);
   }

   public float ground() {
      return this.W * (1.0F - this.f);
   }
}
