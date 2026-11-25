package dev.purxiy.loader.fabric.client;

import java.util.Objects;
import net.minecraft.class_310;

public final class ProfileProvider {
   private static ProfileProvider.Listener source;
   private static long generation = 0L;

   private ProfileProvider() {
   }

   public static boolean available() {
      return source != null;
   }

   public static long revision() {
      return generation;
   }

   public static AutoCloseable install(ProfileProvider.Listener var0) {
      if (source != null) {
         throw new IllegalStateException("Profile already owned");
      } else {
         source = Objects.requireNonNull(var0);
         long var1 = ++generation;
         return () -> {
            if (var1 == generation) {
               source = null;
               generation++;
            }
         };
      }
   }

   public static String snapshot() {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Profile snapshot thread");
      } else {
         return source == null ? "{}" : source.snapshot();
      }
   }

   public interface Listener {
      String snapshot();
   }
}
