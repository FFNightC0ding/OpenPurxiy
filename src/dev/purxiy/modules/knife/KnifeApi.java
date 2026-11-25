package dev.purxiy.modules.knife;

import net.minecraft.class_11659;
import net.minecraft.class_4587;
import org.joml.Vector3f;

public interface KnifeApi {
   String sheet();

   Object identity();

   void submit(class_4587 var1, class_11659 var2, int var3, boolean var4, float var5);

   void blade(Vector3f var1, Vector3f var2);

   default float armScale() {
      return KnifeGeometry.R;
   }

   default float handleRadius(float var1) {
      return 0.0F;
   }

   default float[] grips() {
      return null;
   }

   int light();

   int deep();

   KnifeWieldPipeline.WieldStage ambient();

   String sounds();

   float volume();

   boolean soundOn(boolean var1);

   default boolean hitSounds() {
      return true;
   }

   boolean slashFx();

   boolean hitFx();

   boolean ambientFx();

   float fov();

   float tempo();

   boolean draws();

   float idleEvery();

   default boolean flows() {
      return false;
   }

   default float flowPause() {
      return 0.0F;
   }

   default float flowTempo() {
      return 1.0F;
   }

   default boolean airCuts() {
      return true;
   }
}
