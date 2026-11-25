package dev.purxiy.modules.spotify;

import java.lang.Character.UnicodeScript;
import java.util.Arrays;
import java.util.List;

public final class SpotifySupport4 {
   private static final float N = 0.8F;
   private static final float p = 150.0F;
   private static final float f = 480.0F;
   private static final float g = 330.0F;
   private static final long d = 300L;
   private static final long R = 700L;
   private static final float K = 0.92F;
   private static final float q = 0.6F;
   private static final float E = 0.15F;
   private static final float s = 1.2F;

   private SpotifySupport4() {
   }

   public static long sungEnd(SpotifySupport3 var0, int var1, long var2) {
      List var4 = var0.lines();
      SpotifySupport3.Data var5 = (SpotifySupport3.Data)var4.get(var1);
      long var6 = var0.nextStart(var1, var2 > var5.startMs() ? var2 : var5.startMs() + 6000L);
      if (var5.hasEnd()) {
         return Math.min(var5.endMs(), Math.max(var6, var5.startMs() + 1L));
      } else {
         long var8 = Math.max(1L, var6 - var5.startMs());
         long var10 = Math.max(700L, (long)(syllables(var5.text()) * pace(var0, var2)) + 300L);
         if (!var5.words().isEmpty()) {
            SpotifySupport3.Data2 var12 = var5.words().getLast();
            if (var12.hasEnd()) {
               return Math.min(var12.endMs(), Math.max(var6, var5.startMs() + 1L));
            }

            var10 = Math.max(var10, var12.startMs() - var5.startMs() + Math.max(250L, (long)(syllables(var12.text()) * pace(var0, var2))));
         }

         return var5.startMs() + Math.min((long)((float)var8 * 0.92F), var10);
      }
   }

   static float pace(SpotifySupport3 var0, long var1) {
      float var3 = var0.X;
      if (var3 > 0.0F) {
         return var3;
      } else {
         List var4 = var0.lines();
         float[] var5 = new float[var4.size()];
         int var6 = 0;

         for (int var7 = 0; var7 < var4.size(); var7++) {
            SpotifySupport3.Data var8 = (SpotifySupport3.Data)var4.get(var7);
            if (!var8.isBreak()) {
               long var9 = var0.nextStart(var7, var1 > var8.startMs() ? var1 : var8.startMs() + 6000L);
               var5[var6++] = (float)(var9 - var8.startMs()) / syllables(var8.text());
            }
         }

         float var11 = 330.0F;
         if (var6 >= 4) {
            Arrays.sort(var5, 0, var6);
            float var12 = var6 % 2 == 1 ? var5[var6 / 2] : (var5[var6 / 2 - 1] + var5[var6 / 2]) / 2.0F;
            var11 = Math.max(150.0F, Math.min(480.0F, var12 * 0.8F));
         }

         var0.X = var11;
         return var11;
      }
   }

   public static float position(SpotifySupport3.Data var0, long var1, long var3) {
      long var5 = 75470216148656L;
      long var7 = var5 ^ 47832962629434L;
      String var9 = var0.text();
      int var10 = var9.length();
      if (var3 <= var0.startMs() || var10 == 0) {
         return 0.0F;
      } else if (var3 >= var1) {
         return var10;
      } else {
         if (!var0.words().isEmpty()) {
            float var11 = v(var0, var1, var3, var7);
            if (var11 >= 0.0F) {
               return var11;
            }
         }

         float var12 = (float)(var3 - var0.startMs()) / (float)Math.max(1L, var1 - var0.startMs());
         return estimatedPosition(var9, var12);
      }
   }

   private static float v(SpotifySupport3.Data var0, long var1, long var3, long var5) {
      SpotifyCurve.ensureSourceClassInitialized();
      String var7 = var0.text();
      List var8 = var0.words();
      int[] var9 = new int[var8.size()];
      int[] var10 = new int[var8.size()];
      int var11 = 0;

      for (int var12 = 0; var12 < var8.size(); var12++) {
         SpotifySupport3.Data2 var13 = (SpotifySupport3.Data2)var8.get(var12);
         long var14 = 122197151730058L;
         int var33 = var14 < 0L ? -1 : 1;
         String var17 = var13.text().strip();
         int var18 = var7.indexOf(var17, var11);
         boolean var19 = var17.isEmpty();
         long var20 = 122197151730058L;
         int var22 = var20 < 0L ? -1 : 1;
         if (var19 || var18 < 0) {
            return -1.0F;
         }

         var9[var12] = var18;
         var10[var12] = var18 + var17.length();
         var11 = var10[var12];
      }

      int var30 = var8.size();
      long var32 = 122197151730058L;
      int var16 = var32 < 0L ? -1 : 1;

      for (int var16x = var30 - 1; var16x >= 0; var16x--) {
         SpotifySupport3.Data2 var33 = (SpotifySupport3.Data2)var8.get(var16x);
         long var34 = var33.startMs();
         long var35;
         var30 = (var35 = var3 - var34) == 0L ? 0 : (var35 < 0L ? -1 : 1);
         long var22 = 122197151730058L;
         int var25 = var22 < 0L ? -1 : 1;
         if (var30 >= 0) {
            long var36 = var16x + 1 < var8.size() ? ((SpotifySupport3.Data2)var8.get(var16x + 1)).startMs() : var1;
            long var27 = var33.hasEnd() ? Math.min(var33.endMs(), var36) : var36;
            float var29 = Math.min(1.0F, (float)(var3 - var34) / (float)Math.max(1L, var27 - var34));
            return var9[var16x] + var29 * (var10[var16x] - var9[var16x]);
         }

         long var25x = 122197151730058L;
         int var25x = var25x < 0L ? -1 : 1;
      }

      return 0.0F;
   }

   static float estimatedPosition(String var0, float var1) {
      long var2 = 50764007165437L;
      long var4 = var2 ^ 64450192185741L;
      int var6 = var0.length();
      int var7 = 0;
      float var8 = 0.0F;
      int[] var9 = new int[var6];
      int[] var10 = new int[var6];
      float[] var11 = new float[var6];
      int var12 = 0;

      while (var12 < var6) {
         while (var12 < var6 && var0.charAt(var12) == ' ') {
            var12++;
         }

         if (var12 >= var6) {
            break;
         }

         int var13 = var12;

         while (var12 < var6 && var0.charAt(var12) != ' ') {
            var12++;
         }

         String var14 = var0.substring(var13, var12);
         float var15 = syllables(var14) + 0.15F + (v(var4, var14) ? 0.6F : 0.0F);
         var9[var7] = var13;
         var10[var7] = var12;
         var11[var7] = var15;
         var7++;
         var8 += var15;
      }

      if (var7 == 0) {
         return var6 * Math.max(0.0F, Math.min(1.0F, var1));
      } else {
         var11[var7 - 1]++;
         float var19 = Math.max(0.0F, Math.min(1.0F, var1)) * ++var8;

         for (int var20 = 0; var20 < var7; var20++) {
            if (var19 <= var11[var20] || var20 == var7 - 1) {
               String var21 = var0.substring(var9[var20], var10[var20]);
               float var16 = (v(var4, var21) ? 0.6F : 0.0F) + (var20 == var7 - 1 ? 1.2F : 0.0F);
               float var17 = Math.min(1.0F, Math.max(0.0F, var19) / Math.max(0.001F, var11[var20] - var16));
               return var9[var20] + var17 * (var10[var20] - var9[var20]);
            }

            var19 -= var11[var20];
         }

         return var6;
      }
   }

   private static boolean v(long var0, String var2) {
      SpotifyCurve.ensureSourceClassInitialized();
      char var3 = var2.charAt(var2.length() - 1);
      long var4 = 22774337950831L;
      int var7 = var4 < 0L ? -1 : 1;
      short var7x = 6506;
      long var8 = 22774337950832L;
      int var10 = var8 < 0L ? -1 : 1;
      if (var3 != ',') {
         long var10x = 22774337950831L;
         int var12 = var10x < 0L ? -1 : 1;
         var7x = 25876;
         long var12x = 22774337950832L;
         int var14 = var12x < 0L ? -1 : 1;
         if (var3 != '.') {
            long var14x = 22774337950832L;
            int var16 = var14x < 0L ? -1 : 1;
            var7x = 26461;
            long var16x = 22774337950832L;
            int var18 = var16x < 0L ? -1 : 1;
            if (var3 != '!') {
               long var18x = 22774337950832L;
               int var20 = var18x < 0L ? -1 : 1;
               var7x = 15736;
               long var20x = 22774337950832L;
               int var22 = var20x < 0L ? -1 : 1;
               if (var3 != '?') {
                  long var22x = 22774337950831L;
                  int var24 = var22x < 0L ? -1 : 1;
                  var7x = 22958;
                  long var24x = 22774337950832L;
                  int var26 = var24x < 0L ? -1 : 1;
                  if (var3 != ';') {
                     long var26x = 22774337950832L;
                     int var28 = var26x < 0L ? -1 : 1;
                     var7x = 19859;
                     long var28x = 22774337950832L;
                     int var30 = var28x < 0L ? -1 : 1;
                     if (var3 != ':') {
                        long var30x = 22774337950831L;
                        int var31 = var30x < 0L ? -1 : 1;
                        if (var3 != 8230) {
                           return false;
                        }
                     }
                  }
               }
            }
         }
      }

      return true;
   }

   public static int syllables(String var0) {
      long var1 = 113785690009736L;
      long var3 = var1 ^ 82415368710227L;
      int var5 = 0;
      boolean var6 = false;
      int var7 = 0;

      while (var7 < var0.length()) {
         int var8 = var0.codePointAt(var7);
         var7 += Character.charCount(var8);
         UnicodeScript var9 = UnicodeScript.of(var8);
         if (var9 != UnicodeScript.HAN
            && var9 != UnicodeScript.HIRAGANA
            && var9 != UnicodeScript.KATAKANA
            && var9 != UnicodeScript.HANGUL
            && var9 != UnicodeScript.THAI) {
            boolean var10 = d(var3, Character.toLowerCase(var8));
            if (var10 && !var6) {
               var5++;
            }

            var6 = var10;
         } else {
            var5++;
            var6 = false;
         }
      }

      return Math.max(1, var5);
   }

   private static boolean d(long var0, int var2) {
      SpotifyCurve.ensureSourceClassInitialized();
      int var3 = 426023131;
      if ("aeiouyàáâãäåæèéêëìíîïòóôõöøùúûüýÿœ".indexOf(var2) < 0) {
         long var4 = 50062564825307L;
         int var6 = var4 < 0L ? -1 : 1;
         long var6x = 50062564825307L;
         int var8 = var6x < 0L ? -1 : 1;
         if (var2 < 1072 || "аеёиоуыэюя".indexOf(var2) < 0) {
            return false;
         }
      }

      return true;
   }
}
