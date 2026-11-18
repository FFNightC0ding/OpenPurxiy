// Sourced By DevFFNightmare
package dev.purxiy.theme;

import java.util.Arrays;

public final class ThemeColors {
   static final int MAX_LENGTH = 32;
   private static final int[] LEGACY_COLORS = new int[]{
      0, 170, 43520, 43690, 11141120, 11141290, 16755200, 11184810, 5592405, 5592575, 5635925, 5636095, 16733525, 16733695, 16777045, 16777215
   };
   final String raw;
   final String visible;
   final int[] codepoints;
   
   final ColorPair[] paints;
   final boolean hasUpper;
   final boolean hasLower;

   private ThemeColors(String var1, int[] var2,  ColorPair[] var3) {
      this.raw = var1;
      this.codepoints = var2;
      this.paints = var3;
      this.visible = new String(var2, 0, var2.length);
      boolean var4 = false;
      boolean var5 = false;

      for (int var9 : var2) {
         var4 |= Character.isUpperCase(var9);
         var5 |= Character.isLowerCase(var9);
      }

      this.hasUpper = var4;
      this.hasLower = var5;
   }

   static ThemeColors plain(String var0) {
      return new ThemeColors(var0, var0.codePoints().toArray(), (ColorPair[])null);
   }

   public static ThemeColors parse(String var0) {
      String var1 = var0.strip();
      int[] var2 = new int[Math.min(var1.length(), 32)];
      ColorPair[] var3 = new ColorPair[var2.length];
      int var4 = 0;
      boolean var5 = false;
      ColorPair var6 = null;
      int var7 = 0;

      while (var7 < var1.length() && var4 < var2.length) {
         char var8 = var1.charAt(var7);
         if ((var8 == '&' || var8 == 167) && var7 + 1 < var1.length()) {
            char var9 = Character.toLowerCase(var1.charAt(var7 + 1));
            if (var9 == '#' && var7 + 8 <= var1.length() && isHex(var1, var7 + 2, 6)) {
               var6 = ColorPair.color(Integer.parseInt(var1, var7 + 2, var7 + 8, 16));
               var5 = true;
               var7 += 8;
               continue;
            }

            int var10 = Character.digit(var9, 16);

            byte var11 = switch (var9) {
               case 'k' -> 16;
               case 'l' -> 1;
               case 'm' -> 8;
               case 'n' -> 4;
               case 'o' -> 2;
               default -> 0;
            };
            if (var10 >= 0 || var11 != 0 || var9 == 'r') {
               var6 = var10 >= 0 ? ColorPair.color(LEGACY_COLORS[var10]) : (var11 != 0 ? (var6 == null ? new ColorPair(-1, 0, 0) : var6).with(var11) : null);
               var5 = true;
               var7 += 2;
               continue;
            }
         }

         int var14 = var1.codePointAt(var7);
         var7 += Character.charCount(var14);
         if (!Character.isISOControl(var14)) {
            var2[var4] = var14;
            var3[var4] = var6;
            var4++;
         }
      }

      while (var4 > 0 && Character.isWhitespace(var2[var4 - 1])) {
         var4--;
      }

      var7 = 0;

      while (var7 < var4 && Character.isWhitespace(var2[var7])) {
         var7++;
      }

      return new ThemeColors(var0, Arrays.copyOfRange(var2, var7, var4), var5 ? Arrays.copyOfRange(var3, var7, var4) : null);
   }

   private static boolean isHex(String var0, int var1, int var2) {
      for (int var3 = var1; var3 < var1 + var2; var3++) {
         if (Character.digit(var0.charAt(var3), 16) < 0) {
            return false;
         }
      }

      return true;
   }

   public String visible() {
      return this.visible;
   }

   public boolean isEmpty() {
      return this.codepoints.length == 0;
   }

   public int length() {
      return this.codepoints.length;
   }

   
   ColorPair paint(int var1) {
      return this.paints == null ? null : this.paints[var1];
   }

   public int rgb(int var1) {
      ColorPair var2 = this.paint(var1);
      return var2 == null ? -1 : var2.rgb();
   }
}
