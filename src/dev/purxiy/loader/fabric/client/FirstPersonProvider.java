package dev.purxiy.loader.fabric.client;

import dev.purxiy.loader.fabric.ModuleState;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_310;
import org.joml.Vector3fc;

public final class FirstPersonProvider {
   private static List<FirstPersonProvider.FirstPersonSource> controllers = List.of();
   private static long sequence = 0L;
   private static int depth = 0;

   private FirstPersonProvider() {
   }

   private static void owner() {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Camera owner thread");
      }
   }

   public static int subscribers() {
      owner();
      return controllers.size();
   }

   public static AutoCloseable install(FirstPersonProvider.Listener var0) {
      owner();
      if (depth == 0 && controllers.size() < 8) {
         long var1 = ++sequence;
         ArrayList var3 = new ArrayList<>(controllers);
         var3.add(new FirstPersonProvider.FirstPersonSource(var1, Objects.requireNonNull(var0)));
         controllers = List.copyOf(var3);
         return () -> {
            owner();
            if (depth != 0) {
               throw new IllegalStateException("Camera mutation during dispatch");
            } else {
               controllers = controllers.stream().filter(var2 -> var2.identity() != var1).toList();
            }
         };
      } else {
         throw new IllegalStateException("Camera mutation or capacity");
      }
   }

   private static <T> T dispatch(Supplier<T> var0, T var1) {
      owner();
      depth++;

      Object var2;
      try {
         return (T)var0.get();
      } catch (AssertionError | Exception | LinkageError var8) {
         if (depth > 1) {
            throw var8;
         }

         depth--;
         ModuleState.callbackFailed(var8);
         depth++;
         var2 = var1;
      } finally {
         depth--;
      }

      return (T)var2;
   }

   public static boolean turn(double var0, double var2) {
      return dispatch(() -> {
         for (FirstPersonProvider.FirstPersonSource var5 : controllers) {
            if (var5.controller().turn(var0, var2)) {
               return true;
            }
         }

         return false;
      }, false);
   }

   public static FirstPersonProvider.CameraPose position(class_1937 var0, class_1297 var1, class_243 var2, class_243 var3, float var4, float var5, Vector3fc var6) {
      return dispatch(
         () -> {
            FirstPersonProvider.CameraPose var7 = null;

            for (FirstPersonProvider.FirstPersonSource var9 : controllers) {
               FirstPersonProvider.CameraPose var10 = var9.controller().position(var0, var1, var2, var3, var4, var5, var6);
               if (var10 != null) {
                  if (!Float.isFinite(var10.yaw())
                     || !Float.isFinite(var10.pitch())
                     || var10.position() == null
                     || !Double.isFinite(var10.position().field_1352)
                     || !Double.isFinite(var10.position().field_1351)
                     || !Double.isFinite(var10.position().field_1350)) {
                     throw new IllegalStateException("Invalid camera output");
                  }

                  if (var7 != null) {
                     throw new IllegalStateException("Two camera controllers acquired one frame");
                  }

                  var7 = var10;
               }
            }

            return var7;
         },
         null
      );
   }

   public static boolean inHead() {
      return dispatch(() -> controllers.stream().anyMatch(var0 -> var0.controller().inHead()), false);
   }

   public static boolean passesWalls() {
      return dispatch(() -> controllers.stream().anyMatch(var0 -> var0.controller().passesWalls()), false);
   }

   public static boolean active() {
      return dispatch(() -> controllers.stream().anyMatch(var0 -> var0.controller().active()), false);
   }

   public static float handsDown() {
      return dispatch(() -> {
         float var0 = 0.0F;

         for (FirstPersonProvider.FirstPersonSource var2 : controllers) {
            if (var2.controller().active()) {
               var0 = Math.max(var0, var2.controller().handsDown());
            }
         }

         if (Float.isFinite(var0) && !(var0 < 0.0F) && !(var0 > 1.0F)) {
            return var0;
         } else {
            throw new IllegalStateException("Invalid camera hand blend");
         }
      }, 0.0F);
   }

   public interface Listener {
      boolean active();

      boolean turn(double var1, double var3);

      FirstPersonProvider.CameraPose position(class_1937 var1, class_1297 var2, class_243 var3, class_243 var4, float var5, float var6, Vector3fc var7);

      boolean inHead();

      boolean passesWalls();

      float handsDown();
   }

   private record FirstPersonSource(long identity, FirstPersonProvider.Listener controller) {
   }

   public record CameraPose(float yaw, float pitch, class_243 position, boolean hideBody) {
   }
}
