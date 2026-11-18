// Sourced By DevFFNightmare
package dev.purxiy.modules.nick;

import java.util.Arrays;

public final class NickHiderCodec {
   static final int m = 32;
   private static final int[] I = new int[]{
      0, 170, 43520, 43690, 11141120, 11141290, 16755200, 11184810, 5592405, 5592575, 5635925, 5636095, 16733525, 16733695, 16777045, 16777215
   };
   final String L;
   final String S;
   final int[] y;
   
   final NickHiderData[] x;
   final boolean a;
   final boolean R;
   public static boolean M;



   private NickHiderCodec(String var1, int[] var2,  NickHiderData[] var3, long var4) {
      this.L = var1;
      this.y = var2;
      this.x = var3;
      this.S = new String(var2, 0, var2.length);
      boolean var6 = false;
      boolean var7 = false;

      for (int var11 : var2) {
         long var12 = 28344919240206L;
         if (28344919240206L == 0L) {
            boolean var10000 = false;
         } else {
            int var16 = var12 < 0L ? -1 : 1;
         }

         var6 |= Character.isUpperCase(var11);
         var7 |= Character.isLowerCase(var11);
      }

      this.a = var6;
      long var15 = 28344919240205L;
      if (28344919240205L == 0L) {
         boolean var17 = false;
      } else {
         int var18 = var15 < 0L ? -1 : 1;
      }

      this.R = var7;
   }

   static NickHiderCodec plain(String var0) {
      long var1 = 106145766916861L;
      long var3 = var1 ^ 133377733487859L;
      return new NickHiderCodec(var0, var0.codePoints().toArray(), null, var3);
   }

   public static NickHiderCodec parse(String var0) {
      long var1 = 3485388523668L;
      long var3 = var1 ^ 59063389105475L;
      long var5 = var1 ^ 29601209247386L;
      String var7 = var0.strip();
      int[] var8 = new int[Math.min(var7.length(), 32)];
      NickHiderData[] var9 = new NickHiderData[var8.length];
      int var10 = 0;
      boolean var11 = false;
      NickHiderData var12 = null;
      int var13 = 0;

      while (var13 < var7.length() && var10 < var8.length) {
         char var14 = var7.charAt(var13);
         if ((var14 == '&' || var14 == 167) && var13 + 1 < var7.length()) {
            char var15 = Character.toLowerCase(var7.charAt(var13 + 1));
            if (var15 == '#' && var13 + 8 <= var7.length() && y(var7, var13 + 2, 6, var3)) {
               var12 = NickHiderData.color(Integer.parseInt(var7, var13 + 2, var13 + 8, 16));
               var11 = true;
               var13 += 8;
               continue;
            }

            int var16 = Character.digit(var15, 16);

            byte var17 = switch (var15) {
               case 'k' -> 16;
               case 'l' -> 1;
               case 'm' -> 8;
               case 'n' -> 4;
               case 'o' -> 2;
               default -> 0;
            };
            if (var16 >= 0 || var17 != 0 || var15 == 'r') {
               var12 = var16 >= 0 ? NickHiderData.color(I[var16]) : (var17 != 0 ? (var12 == null ? new NickHiderData(-1, 0, 0) : var12).with(var17) : null);
               var11 = true;
               var13 += 2;
               continue;
            }
         }

         int var19 = var7.codePointAt(var13);
         var13 += Character.charCount(var19);
         if (!Character.isISOControl(var19)) {
            var8[var10] = var19;
            var9[var10] = var12;
            var10++;
         }
      }

      while (var10 > 0 && Character.isWhitespace(var8[var10 - 1])) {
         var10--;
      }

      int var18 = 0;

      while (var18 < var10 && Character.isWhitespace(var8[var18])) {
         var18++;
      }

      return new NickHiderCodec(var0, Arrays.copyOfRange(var8, var18, var10), var11 ? Arrays.copyOfRange(var9, var18, var10) : null, var5);
   }

   private static boolean y(String var0, int var1, int var2, long var3) {
      for (int var5 = var1; var5 < var1 + 6; var5++) {
         int var6 = var0.charAt(var5);
         long var7 = 60044801205719L;
         if (60044801205719L == 0L) {
            boolean var10000 = false;
         } else {
            int var17 = var7 < 0L ? -1 : 1;
         }

         long var10 = 60044801205718L;
         if (60044801205718L == 0L) {
            boolean var18 = false;
         } else {
            int var19 = var10 < 0L ? -1 : 1;
         }

         var6 = Character.digit((char)var6, 16);
         long var12 = 60044801205718L;
         if (60044801205718L == 0L) {
            boolean var20 = false;
         } else {
            int var21 = var12 < 0L ? -1 : 1;
         }

         if (var6 < 0) {
            return false;
         }
      }

      long var16 = 60044801205718L;
      if (60044801205718L == 0L) {
         boolean var22 = false;
      } else {
         int var23 = var16 < 0L ? -1 : 1;
      }

      return true;
   }

   public String visible() {
      return this.S;
   }

   public boolean isEmpty() {
      return this.y.length == 0;
   }

   public int length() {
      return this.y.length;
   }

   
   NickHiderData paint(int var1) {
      return this.x == null ? null : this.x[var1];
   }

   public int rgb(int var1) {
      NickHiderData var2 = this.paint(var1);
      return var2 == null ? -1 : var2.rgb();
   }

public static void ensureSourceClassInitialized() {
   }
}
