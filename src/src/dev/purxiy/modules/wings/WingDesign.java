// Sourced By DevFFNightmare
package dev.purxiy.modules.wings;

import dev.purxiy.render.BlendMode;

public enum WingDesign {
   r("Seraph", "Celestial", "Soft white feathers, your colour blushing in their tips", -1186561, true, false, false),
   b("Nocturne", "Shadow", "Night-black feathers, their edges lit like starlight", -6521857, true, false, false),
   p("Lumen", "Light", "Wings of light, a slow glow running through them", -2900737, true, false, true),
   d("Phoenix", "Flame", "Feathers of fire, from gold to rose, embers in their tips", -25506, false, false, false),
   Z("Fairy", "Fae", "Thin glassy wings that shimmer in every colour", -4591361, true, true, false);

   public final String x;
   public final String z;
   public final String Q;
   public final int H;
   public final boolean h;
   public final boolean w;
   public final boolean e;
   private static final int n = -776;



   private WingDesign(String nullxx, String nullxxx, String nullxxxx, int nullxxxxx, boolean nullxxxxxx, boolean nullxxxxxxx, boolean nullxxxxxxxx) {
      this.x = nullxx;
      this.z = nullxxx;
      this.Q = nullxxxx;
      this.H = nullxxxxx;
      this.h = nullxxxxxx;
      this.w = nullxxxxxxx;
      this.e = nullxxxxxxxx;
   }

   public static String[] labels() {
      WingDesign[] var0 = values();
      String[] var1 = new String[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = var0[var2].x;
      }

      return var1;
   }

   public static WingDesign byLabel(String var0) {
      for (WingDesign var4 : values()) {
         if (var4.x.equals(var0)) {
            return var4;
         }
      }

      return r;
   }

   int feather(int var1, float var2, float var3, float var4, float var5, int var6) {
      long var7 = 125235670896248L;
      long var9 = var7 ^ 59360005148923L;
      int var11 = (int)((var7 ^ 59360005148923L) >>> 32);
      int var12 = (int)((var7 ^ 59360005148923L) << 32 >>> 56);
      int var13 = (int)(var9 << 40 >>> 40);
      var9 = var7 ^ 40648110148620L;
      int var14 = (int)((var7 ^ 40648110148620L) >>> 48);
      int var15 = (int)((var7 ^ 40648110148620L) << 16 >>> 32);
      int var16 = (int)(var9 << 48 >>> 48);
      long var17 = var7 ^ 5816040583302L;
      boolean var19 = var1 <= 2;
      float var20 = var19
         ? 0.78F + 0.22F * X(0.0F, (short)var14, var15, 0.45F, var3, (char)var16)
         : 0.86F + 0.14F * X(0.0F, (short)var14, var15, 0.6F, var3, (char)var16);
      switch (this) {
         case r:
            int var21 = BlendMode.lerp(var6, -1, 0.42F);

            float var22 = X(0.42F, (short)var14, var15, 1.0F, var3, (char)var16) * switch (var1) {
               case 0 -> 0.72F;
               case 1 -> 0.5F;
               case 2 -> 0.38F;
               case 3 -> 0.2F;
               default -> 0.08F;
            } * (0.55F + 0.45F * var2);
            int var23 = BlendMode.lerp(-776, var21, var22);
            return K(var23, var20 * (0.97F + 0.03F * var4), var17);
         case b:
            int var24 = BlendMode.lerp(-15922666, BlendMode.lerp(var6, -16777216, 0.78F), 0.55F);
            int var25 = BlendMode.lerp(-14016192, BlendMode.lerp(var6, -16777216, 0.45F), 0.4F);
            float var26 = 0.5F + 0.5F * (float)Math.sin(var2 * 6.5F + var3 * 2.2F - var5 * 0.7F + var4 * 2.0F);
            int var27 = BlendMode.lerp(var24, var25, 0.25F + 0.45F * var3 * (0.6F + 0.4F * var26));
            return K(var27, var20, var17);
         case p:
            int var28 = BlendMode.lerp(-1, var6, 0.18F);
            return BlendMode.withAlpha(var28, 0.2F + 0.1F * X(0.0F, (short)var14, var15, 0.4F, var3, (char)var16));
         case d:
            float var29 = Math.min(1.0F, var3 * 0.75F + var2 * 0.35F);
            int var30 = var29 < 0.5F ? BlendMode.lerp(-9846, -28088, var29 / 0.5F) : BlendMode.lerp(-28088, -49300, (var29 - 0.5F) / 0.5F);
            if (!var19) {
               var30 = BlendMode.lerp(var30, -6232, 0.35F * (1.0F - var2));
            }

            return K(var30, 0.88F + 0.12F * var20, var17);
         case Z:
            float var31 = var2 * 1.1F + var3 * 0.7F + var5 * 0.08F + var4;
            int var32 = Y(var11, (byte)var12, var13, var31);
            int var33 = BlendMode.lerp(BlendMode.lerp(var6, -1, 0.3F), var32, 0.45F);
            return BlendMode.withAlpha(var33, 0.42F + 0.4F * var3);
         default:
            return -776;
      }
   }

   int light(int var1, float var2, float var3, float var4, float var5, int var6, float var7) {
      long var8 = 3321562811090L;
      long var10 = var8 ^ 78199463236689L;
      int var12 = (int)((var8 ^ 78199463236689L) >>> 32);
      int var13 = (int)((var8 ^ 78199463236689L) << 32 >>> 56);
      int var14 = (int)(var10 << 40 >>> 40);
      var10 = var8 ^ 94677438400678L;
      int var15 = (int)((var8 ^ 94677438400678L) >>> 48);
      int var16 = (int)((var8 ^ 94677438400678L) << 16 >>> 32);
      int var17 = (int)(var10 << 48 >>> 48);
      float var18 = var5 / 3.4F % 1.0F * 1.6F - 0.3F;
      float var19 = var2 * 0.82F + var3 * 0.18F;
      float var20 = (float)Math.exp(-(var19 - var18) * (var19 - var18) / 0.018F);
      switch (this) {
         case r:
            int var21 = BlendMode.lerp(var6, -1, 0.55F);
            return BlendMode.withAlpha(var21, var7 * (0.16F + 0.5F * var20) * (0.6F + 0.4F * var3));
         case b:
            float var22 = (float)Math.pow(Math.max(0.0, Math.sin(var5 * (0.9F + var4 * 1.3F) + var4 * 40.0F)), 24.0)
               * X(0.6F, (short)var15, var16, 1.0F, var3, (char)var17);
            int var23 = BlendMode.lerp(var6, -1, 0.2F + 0.55F * var22 + 0.2F * var20);
            return BlendMode.withAlpha(var23, Math.min(1.0F, var7 * (0.75F + 0.5F * var20 + 0.8F * var22)));
         case p:
            int var24 = BlendMode.lerp(BlendMode.lerp(-1, var6, 0.1F + 0.38F * var3 * var3), -1, 0.35F * var20);
            float var25 = 0.88F + 0.12F * (float)Math.sin(var5 * 1.6F - var2 * 3.0F);
            float var26 = var1 <= 2 ? 1.0F : 0.85F;
            return BlendMode.withAlpha(var24, Math.min(1.0F, (0.3F + 0.75F * var7) * var26 * (0.82F * var25 + 0.3F * var20)));
         case d:
            float var27 = 0.75F + 0.25F * (float)Math.sin(var5 * 9.0F + var4 * 31.0F + var3 * 4.0F) * (float)Math.sin(var5 * 5.3F + var4 * 17.0F);
            int var28 = BlendMode.lerp(-19641, -42436, var3);
            return BlendMode.withAlpha(var28, var7 * (0.3F + 0.5F * X(0.45F, (short)var15, var16, 1.0F, var3, (char)var17)) * var27 + var7 * 0.3F * var20);
         case Z:
            int var29 = BlendMode.lerp(Y(var12, (byte)var13, var14, var2 * 1.1F + var5 * 0.08F + var4 + 0.3F), -1, 0.5F);
            return BlendMode.withAlpha(var29, Math.min(1.0F, var7 * (0.75F + 0.5F * var20)));
         default:
            return 0;
      }
   }

   private static int Y(int var0, byte var1, int var2, float var3) {
      long var4 = 74880198909571L;
      long var6 = var4 ^ 124868890705327L;
      float var8 = (var3 % 1.0F + 1.0F) % 1.0F * 6.0F;
      float var9 = B(Math.abs(var8 - 3.0F) - 1.0F, var6);
      float var10 = B(2.0F - Math.abs(var8 - 2.0F), var6);
      float var11 = B(2.0F - Math.abs(var8 - 4.0F), var6);
      return BlendMode.argb(1.0F, 0.55F + 0.45F * var9, 0.55F + 0.45F * var10, 0.55F + 0.45F * var11);
   }

   private static int K(int var0, float var1, long var2) {
      return var0 & 0xFF000000 | Math.round((var0 >> 16 & 0xFF) * var1) << 16 | Math.round((var0 >> 8 & 0xFF) * var1) << 8 | Math.round((var0 & 0xFF) * var1);
   }

   private static float X(float var0, short var1, int var2, float var3, float var4, char var5) {
      long var6 = 93589812527732L;
      long var8 = var6 ^ 106196185932120L;
      float var10 = B((var4 - var0) / (var3 - var0), var8);
      return var10 * var10 * (3.0F - 2.0F * var10);
   }

   private static float B(float var0, long var1) {
      WingsUpdater2.ensureSourceClassInitialized();
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

}
