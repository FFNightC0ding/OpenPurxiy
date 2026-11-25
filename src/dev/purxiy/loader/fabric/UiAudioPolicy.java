package dev.purxiy.loader.fabric;

import java.util.Objects;
import java.util.function.Consumer;
import net.minecraft.class_310;

public final class UiAudioPolicy {
   private static UiAudioPolicy.Listener listener;
   private static long generation = 0L;

   private UiAudioPolicy() {
   }

   public static boolean active() {
      check();
      return listener != null;
   }

   public static void play(String var0) {
      play(var0, 1.0F, 1.0F);
   }

   public static void play(String var0, float var1) {
      play(var0, var1, 1.0F);
   }

   public static void play(String var0, float var1, float var2) {
      check();
      if (var0 != null
         && var0.matches("[A-Z][A-Z0-9_]{0,63}")
         && Float.isFinite(var1)
         && !(var1 < 0.5F)
         && !(var1 > 2.0F)
         && Float.isFinite(var2)
         && !(var2 < 0.0F)
         && !(var2 > 1.0F)) {
         UiAudioPolicy.Listener var3 = listener;
         if (var3 != null) {
            var3.play(var0, var1, var2);
         }
      } else {
         throw new IllegalArgumentException("Invalid UI audio request");
      }
   }

   public static AutoCloseable subscribe(Consumer<String> var0) {
      return subscribeDetailed((var1, var2, var3) -> var0.accept(var1));
   }

   public static AutoCloseable subscribeDetailed(UiAudioPolicy.Listener var0) {
      check();
      Objects.requireNonNull(var0);
      if (listener != null) {
         throw new IllegalStateException("UI sound policy already has an owner");
      } else {
         listener = var0;
         long var1 = ++generation;
         return () -> {
            check();
            if (var1 == generation) {
               listener = null;
               generation++;
            }
         };
      }
   }

   private static void check() {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("UI audio requires owner thread");
      }
   }

   @FunctionalInterface
   public interface Listener {
      void play(String var1, float var2, float var3);
   }
}
