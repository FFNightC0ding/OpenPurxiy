// Sourced By DevFFNightmare
package dev.purxiy.modules.mount;

import dev.purxiy.loader.fabric.GarageActions;
import net.minecraft.class_304;
import net.minecraft.class_304.class_11900;

public final class MountUpdater3 {
   private static class_304 C;
   private static class_304 i;
   private static class_304 O;
   private static class_304 a;

   private MountUpdater3() {
   }

   public static void init(class_11900 var0) {
   }

   public static void tick() {
      while (GarageActions.consume(0)) {
         MountUpdater4.action();
      }

      while (GarageActions.consume(1)) {
         MountUpdater4.shift(1);
      }

      while (GarageActions.consume(2)) {
         MountUpdater4.shift(-1);
      }

      while (GarageActions.consume(3)) {
         MountUpdater4.horn();
      }
   }

   public static String actionName() {
      return GarageActions.name(0);
   }

   public static String upName() {
      return GarageActions.name(1);
   }

   public static String downName() {
      return GarageActions.name(2);
   }
}
