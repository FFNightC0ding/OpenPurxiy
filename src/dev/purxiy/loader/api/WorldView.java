// Sourced By DevFFNightmare
package dev.purxiy.loader.api;

public interface WorldView {
   boolean present();

   long identity();

   boolean hasSkyLight();

   boolean hasFixedTime();

   void refreshLighting();
}
