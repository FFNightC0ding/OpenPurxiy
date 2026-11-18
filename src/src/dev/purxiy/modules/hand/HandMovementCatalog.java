// Sourced By DevFFNightmare
package dev.purxiy.modules.hand;

import java.util.List;

public final class HandMovementCatalog {
   public static final String k = "Vanilla";
   public static final String K = "Low";
   public static final String g = "Compact";
   public static final String Q = "Wide";
   public static final String R = "Centred";
   public static final String t = "Custom";
   public static final List<HandMovementCatalog.Data> P = List.of(
      new HandMovementCatalog.Data("Vanilla", "Where Minecraft puts it", "rotate-ccw", new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0}),
      new HandMovementCatalog.Data("Low", "Lower, so more of the world shows", "arrow-down-to-line", new double[]{0.03, -0.12, 0.02, 4.0, 0.0, 0.0, 1.0}),
      new HandMovementCatalog.Data("Compact", "Smaller and tucked into the corner", "minimize-2", new double[]{0.05, -0.05, 0.02, 0.0, 0.0, 0.0, 0.8}),
      new HandMovementCatalog.Data("Wide", "Further out to the side", "move-horizontal", new double[]{0.14, -0.03, 0.0, 0.0, 6.0, 0.0, 1.0}),
      new HandMovementCatalog.Data("Centred", "In front of you, near the crosshair", "crosshair", new double[]{-0.34, -0.02, -0.04, 0.0, -8.0, 0.0, 0.9})
   );



   private HandMovementCatalog() {
   }

   static String[] names() {
      String[] var0 = new String[P.size() + 1];

      for (int var1 = 0; var1 < P.size(); var1++) {
         var0[var1] = P.get(var1).name();
      }

      var0[P.size()] = "Custom";
      return var0;
   }

   
   public static HandMovementCatalog.Data byName(String var0) {
      for (HandMovementCatalog.Data var2 : P) {
         if (var2.name().equals(var0)) {
            return var2;
         }
      }

      return null;
   }

public record Data(String X, String K, String B, double[] m) {
      public String name() {
         return this.X;
      }

      public String description() {
         return this.K;
      }

      public String icon() {
         return this.B;
      }

      public double[] values() {
         return this.m;
      }

      public String stringState6() {
         return this.X;
      }

      public String stringState14() {
         return this.K;
      }

      public String stringState49() {
         return this.B;
      }

      public double[] doubleArrayState22() {
         return this.m;
      }
   }
}
