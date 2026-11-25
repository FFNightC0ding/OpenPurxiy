package dev.purxiy.modules.auras;

public enum AuraStyle {
   J("Orbit", "Comets", "Three soft lights circle you, each trailing a fading tail."),
   L("Fireflies", "Motes", "Soft motes drift around you like fireflies on a summer night."),
   v("Spiral", "Rising", "Threads of light spiral up around you and dissolve above your head."),
   G("Constellation", "Stars", "A small constellation of stars turns slowly around you.");

   public final String b;
   public final String U;
   public final String l;
   public static int u = 0;

   private AuraStyle(String nullxx, String nullxxx, String nullxxxx) {
      this.b = nullxx;
      this.U = nullxxx;
      this.l = nullxxxx;
   }

   public static String[] names() {
      AuraStyle[] var0 = values();
      String[] var1 = new String[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = var0[var2].b;
      }

      return var1;
   }

   public static AuraStyle of(String var0) {
      for (AuraStyle var4 : values()) {
         if (var4.b.equalsIgnoreCase(var0)) {
            return var4;
         }
      }

      return J;
   }

   public static void ensureSourceClassInitialized() {
   }
}
