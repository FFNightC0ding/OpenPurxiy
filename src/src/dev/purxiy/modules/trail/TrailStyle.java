// Sourced By DevFFNightmare
package dev.purxiy.modules.trail;

public enum TrailStyle {
   q("Line", "A clean line of light that glows softly behind you."),
   D("Path", "A soft path of light glows on the ground behind you."),
   Y("Helix", "Two fine threads of light that wind around each other."),
   T("Stardust", "Soft motes of light drift up from your steps.");

   public final String Z;
   public final String p;
   public static boolean P;

   private TrailStyle(String nullxx, String nullxxx) {
      this.Z = nullxx;
      this.p = nullxxx;
   }

   public static String[] names() {
      TrailStyle[] var0 = values();
      String[] var1 = new String[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = var0[var2].Z;
      }

      return var1;
   }

   public static TrailStyle of(String var0) {
      for (TrailStyle var4 : values()) {
         if (var4.Z.equals(var0)) {
            return var4;
         }
      }

      return q;
   }

   boolean atHips() {
      return this == q || this == Y;
   }

}
