package dev.purxiy.modules.halo;

public enum HaloDesign {
   o("Seraph", "Celestial", "A ring of soft light, your colour glowing round it", -924417, 0.0F, 0.0F, 0.0F),
   v("Sigil", "Arcane", "Two rings of light, the outer one broken into arcs that turn slowly", -3561473, 0.0F, 0.0F, 0.0F),
   C("Crown", "Royal", "Spires of light rise from the ring, a wave of light running round", -7512, 0.0F, 0.0F, 2.6F),
   W("Stardust", "Cosmic", "A band of tiny stars drifting round your head, twinkling", -4597761, 0.0F, 0.8F, 0.8F),
   L("Eclipse", "Shadow", "A dark ring, light spilling round its edge", -6521857, 0.0F, 0.15F, 0.3F),
   m("Fallen", "Broken", "A cracked halo, its pieces drifting apart, the breaks still glowing", -23618, 7.0F, 0.45F, 1.6F);

   public final String U;
   public final String A;
   public final String f;
   public final int g;
   public final float Q;
   public final float n;
   public final float b;
   public static int O = 0;
   private static final long a = -4050287437258162170L;

   private HaloDesign(String nullxx, String nullxxx, String nullxxxx, int nullxxxxx, float nullxxxxxx, float nullxxxxxxx, float nullxxxxxxxx) {
      this.U = nullxx;
      this.A = nullxxx;
      this.f = nullxxxx;
      this.g = nullxxxxx;
      this.Q = nullxxxxxx;
      this.n = nullxxxxxxx;
      this.b = nullxxxxxxxx;
   }

   public static String[] labels() {
      HaloDesign[] var0 = values();
      String[] var1 = new String[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = var0[var2].U;
      }

      return var1;
   }

   public static HaloDesign byLabel(String var0) {
      for (HaloDesign var4 : values()) {
         if (var4.U.equals(var0)) {
            return var4;
         }
      }

      return o;
   }

   public static void ensureSourceClassInitialized() {
   }
}
