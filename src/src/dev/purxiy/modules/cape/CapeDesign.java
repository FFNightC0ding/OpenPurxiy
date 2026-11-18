// Sourced By DevFFNightmare
package dev.purxiy.modules.cape;

public enum CapeDesign {
   B("Purxiy", "Signature", "The mark on deep violet, the light catching its glasses", -4222985),
   U("Midnight", "Minimal", "Black satin, the mark drawn in one line of light", -5665803),
   d("Pearl", "Minimal", "Pearl white with colour flowing through, the mark in violet", -2504449),
   W("Nebula", "Cosmos", "A violet nebula drifting slowly between the stars", -5210894),
   O("Aurora", "Cosmos", "Northern lights over a snowy spruce forest", -9443147),
   G("Eclipse", "Cosmos", "A black sun in a slowly turning corona", -1845505),
   z("Amethyst", "Minecraft", "Crystals from a geode, glittering as they catch the light", -4616961),
   N("The End", "Minecraft", "The End portal's starfield, drifting in layers", -8396608),
   V("Cherry", "Minecraft", "A cherry branch in bloom, petals drifting down", -678969);

   public final String E;
   public final String g;
   public final String L;
   public final int o;



   private CapeDesign(String nullxx, String nullxxx, String nullxxxx, int nullxxxxx) {
      this.E = nullxx;
      this.g = nullxxx;
      this.L = nullxxxx;
      this.o = nullxxxxx;
   }

   public static String[] labels() {
      CapeDesign[] var0 = values();
      String[] var1 = new String[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = var0[var2].E;
      }

      return var1;
   }

   public static CapeDesign byLabel(String var0) {
      for (CapeDesign var4 : values()) {
         if (var4.E.equals(var0)) {
            return var4;
         }
      }

      return B;
   }

}
