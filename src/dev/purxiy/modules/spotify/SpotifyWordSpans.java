package dev.purxiy.modules.spotify;

import java.util.ArrayList;

final class SpotifyWordSpans {
   private SpotifyWordSpans() {
   }

   static int[] spans(String var0) {
      ArrayList var1 = new ArrayList();
      int var2 = 0;
      int var3 = var0.length();

      while (var2 < var3) {
         while (var2 < var3 && Character.isWhitespace(var0.charAt(var2))) {
            var2++;
         }

         if (var2 >= var3) {
            break;
         }

         int var4 = var2;

         while (var2 < var3 && !Character.isWhitespace(var0.charAt(var2))) {
            var2++;
         }

         var1.add(var4);
         var1.add(var2);
      }

      int[] var6 = new int[var1.size()];

      for (int var5 = 0; var5 < var6.length; var5++) {
         var6[var5] = (Integer)var1.get(var5);
      }

      return var6;
   }

   static long[] appearAt(SpotifyLyrics.Data var0, long var1, int[] var3) {
      long[] var4 = new long[var3.length];
      long var5 = var0.startMs();
      long var7 = Math.max(var5 + 1L, var1);

      for (int var9 = 0; var9 < var3.length; var9++) {
         if (var3[var9] <= 0) {
            var4[var9] = var0.startMs();
         } else {
            long var10 = var5;
            long var12 = var7;

            while (var12 - var10 > 4L) {
               long var14 = var10 + var12 >>> 1;
               if (SpotifySyllables.position(var0, var7, var14) >= var3[var9]) {
                  var12 = var14;
               } else {
                  var10 = var14;
               }
            }

            var4[var9] = var12;
            var5 = var12;
         }
      }

      return var4;
   }
}
