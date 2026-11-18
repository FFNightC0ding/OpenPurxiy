// Sourced By DevFFNightmare
package dev.purxiy.modules.bokeh;

public enum BokehKind {
   O("Round", "Soft round discs, the classic creamy look."),
   e("Oval", "Tall ovals, as a cinema lens draws them."),
   C("Blades", "Six softly curved blades, like a stopped-down aperture."),
   p("Star", "Every light becomes a little Purxiy star."),
   o("Heart", "Every light becomes a soft little heart.");

   public final String V;
   public final String r;
   public static boolean y;

   private BokehKind(String nullxx, String nullxxx) {
      this.V = nullxx;
      this.r = nullxxx;
   }

   public static String[] names() {
      BokehKind[] var0 = values();
      String[] var1 = new String[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = var0[var2].V;
      }

      return var1;
   }

}
