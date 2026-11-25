package dev.purxiy.modules.sky;

import java.util.Arrays;

public enum SkyKind {
   X("Galaxy", 0, 0, null),
   a("Aether", 2106411, 5068643, new SkyCodec.Data(12831186, 0.0F, 0.0F, 1.0F, 1.0F, 0.15F, 0.04F)),
   A("Nocturne", 2167601, 4533852, new SkyCodec.Data(8412316, 0.6F, 0.6F, 1.6F, 0.15F, 0.08F, 0.05F)),
   V("Amethyst", 2167352, 5652599, new SkyCodec.Data(12885759, 0.55F, 0.6F, 1.4F, 0.1F, 0.1F, 0.05F)),
   I("Opaline", 2174525, 5202286, new SkyCodec.Data(7686804, 0.3F, 0.6F, 1.5F, 0.1F, 0.08F, 0.05F)),
   H("Rain", 3161677, 8951458, new SkyCodec.Data(4016729, 0.0F, 0.0F, 3.6F, 0.25F, 0.06F, 0.04F)),
   t("Snow", 9149355, 14476778, new SkyCodec.Data(12899042, 0.0F, 0.0F, 2.0F, 0.2F, 0.06F, 0.03F)),
   Y("Fire", 6824731, 12409134, new SkyCodec.Data(16730631, 0.6F, 0.85F, 1.2F, 0.0F, 0.1F, 0.06F));

   public final String K;
   final int Z;
   final int C;
   final SkyCodec.Data j;
   public static boolean n;

   private SkyKind(String nullxx, int nullxxx, int nullxxxx, SkyCodec.Data nullxxxxx) {
      this.K = nullxx;
      this.Z = nullxxx;
      this.C = nullxxxx;
      this.j = nullxxxxx;
   }

   static String[] names() {
      return Arrays.stream(values()).map(var0 -> var0.K).toArray(String[]::new);
   }

   public static void ensureSourceClassInitialized() {
   }
}
