// Sourced By DevFFNightmare
package dev.purxiy.modules.chroma;

public enum ChromaKind {
   f("Lilac", -12897708, -5990462, -263937),
   v("Cream", -12963028, -5529197, -132623),
   A("Noir", -15461354, -8553086, -592138);

   public final String Q;
   public final int Z;
   public final int V;
   public final int r;

   private ChromaKind(String nullxx, int nullxxx, int nullxxxx, int nullxxxxx) {
      this.Q = nullxx;
      this.Z = nullxxx;
      this.V = nullxxxx;
      this.r = nullxxxxx;
   }

   public static String[] names() {
      ChromaKind[] var0 = values();
      String[] var1 = new String[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = var0[var2].Q;
      }

      return var1;
   }

   public static ChromaKind of(String var0) {
      for (ChromaKind var4 : values()) {
         if (var4.Q.equals(var0)) {
            return var4;
         }
      }

      return f;
   }

}
