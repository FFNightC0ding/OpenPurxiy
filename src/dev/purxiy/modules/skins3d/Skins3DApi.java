package dev.purxiy.modules.skins3d;

public interface Skins3DApi {
   float cadence(float var1);

   default int habits() {
      return 0;
   }

   default float habitSeconds(int var1) {
      return 3.0F;
   }

   void move(Skins3DCurve var1, Skins3DCurve var2, Skins3DGroundPose var3);

   default void finish(Skins3DRestPose var1, Skins3DGroundPose var2) {
   }
}
