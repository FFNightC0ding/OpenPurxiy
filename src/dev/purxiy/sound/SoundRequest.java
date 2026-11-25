package dev.purxiy.sound;

import dev.purxiy.loader.fabric.UiAudioPolicy;

public final class SoundRequest {
   private SoundRequest() {
   }

   public static void play(SoundEvent var0) {
      play(var0, 1.0F, 1.0F);
   }

   public static void play(SoundEvent var0, float var1) {
      play(var0, var1, 1.0F);
   }

   public static void play(SoundEvent var0, float var1, float var2) {
      UiAudioPolicy.play(var0.name(), var1, var2);
   }
}
