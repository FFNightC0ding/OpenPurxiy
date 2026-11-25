package dev.purxiy.modules.spotify;

import java.util.ArrayList;
import java.util.List;

final class SpotifyAuth {
   private static final long s = 3000L;
   private static final int D = 3;
   private static final String a = "\\s+";
   private static final long[] b = new long[]{
      2089752876346418155L, 4109227165945435561L, 8114932373262133933L, 998455060822514912L, -5051534517052200918L, -4082858175238793447L
   };
   private static final Long[] c = new Long[6];

   private SpotifyAuth() {
   }

   static SpotifyLyrics wordsOnto(SpotifyLyrics var0, SpotifyLyrics var1) {
      long var2 = 10739107374609L;
      long var4 = var2 ^ 125240739552771L;
      int var6 = (int)((var2 ^ 125240739552771L) >>> 48);
      int var7 = (int)((var2 ^ 125240739552771L) << 16 >>> 32);
      int var8 = (int)(var4 << 48 >>> 48);
      var4 = var2 ^ 129668369305318L;
      int var9 = (int)((var2 ^ 129668369305318L) >>> 48);
      int var10 = (int)((var2 ^ 129668369305318L) << 16 >>> 32);
      int var11 = (int)(var4 << 48 >>> 48);
      long var12 = var2 ^ 122023730547733L;
      List<SpotifyLyrics.Data>var14 = var0.lines();
      ArrayList<SpotifyLyrics.Data>var15 = new ArrayList();

      for (SpotifyLyrics.Data var17 : var1.lines()) {
         if (var17.wordTimed()) {
            var15.add(var17);
         }
      }

      if (var15.isEmpty()) {
         return null;
      } else {
         ArrayList var27 = new ArrayList();

         for (SpotifyLyrics.Data var18 : var14) {
            if (!var18.isBreak()) {
               String var19 = SpotifyLyricCache.normalize(var18.text());
               SpotifyLyrics.Data var20 = null;
               int var21 = 0;

               for (SpotifyLyrics.Data var23 : var15) {
                  if (SpotifyLyricCache.normalize(var23.text()).equals(var19)) {
                     var20 = var23;
                     var21++;
                  }
               }

               if (var21 == 1 && o(var14, (char)var6, var7, var19, (short)var8) == 1) {
                  var27.add(var18.startMs() - var20.startMs());
               }
            }
         }

         if (var27.size() < 3) {
            return null;
         } else {
            var27.sort(null);
            long var29 = (Long)var27.get(var27.size() / 2);
            ArrayList var30 = new ArrayList(var14.size());

            for (int var31 = 0; var31 < var14.size(); var31++) {
               SpotifyLyrics.Data var32 = (SpotifyLyrics.Data)var14.get(var31);
               SpotifyLyrics.Data var33 = !var32.isBreak() && !var32.wordTimed() ? j(var15, (short)var9, var32, var10, var29, (char)var11) : null;
               long var34 = var0.nextStart(var31, Long.MAX_VALUE);
               SpotifyLyrics.Data var25 = var33 == null ? null : c(var32, var33, var34, var12);
               var30.add(var25 != null ? var25 : var32);
            }

            return new SpotifyLyrics(SpotifyLyrics.LyricStyle.k, var30, var0.plain());
         }
      }
   }

   private static int o(List<SpotifyLyrics.Data> var0, char var1, int var2, String var3, short var4) {
      SpotifyCurve.ensureSourceClassInitialized();
      int var5 = 0;

      for (SpotifyLyrics.Data var7 : var0) {
         if (!var7.isBreak()) {
            boolean var8 = SpotifyLyricCache.normalize(var7.text()).equals(var3);
            if (var8) {
               var5++;
            }
         }
      }

      return var5;
   }

   private static SpotifyLyrics.Data j(List<SpotifyLyrics.Data> var0, short var1, SpotifyLyrics.Data var2, int var3, long var4, char var6) {
      String var7 = SpotifyLyricCache.normalize(var2.text());
      SpotifyCurve.ensureSourceClassInitialized();
      SpotifyLyrics.Data var8 = null;
      long var9 = 3000L;

      for (SpotifyLyrics.Data var12 : var0) {
         long var13 = Math.abs(var12.startMs() + var4 - var2.startMs());
         if (var13 <= var9 && SpotifyLyricCache.normalize(var12.text()).equals(var7)) {
            var8 = var12;
            var9 = var13;
         }
      }

      return var8;
   }

   private static SpotifyLyrics.Data c(SpotifyLyrics.Data var0, SpotifyLyrics.Data var1, long var2, long var4) {
      long var6 = 52751142868653L;
      long var8 = 49959025034290L;
      String[] var10 = H(var0.text(), var8);
      SpotifyCurve.ensureSourceClassInitialized();
      long[][] var11 = r(var6, var1);
      if (var11 != null) {
         int var12 = var11.length;
         long var13 = 113519101159940L;
         int var16 = var13 < 0L ? -1 : 1;
         if (var12 == var10.length && var10.length != 0) {
            long var16x = var0.startMs() - var1.startMs();
            long var18 = 113519101159940L;
            int var20 = var18 < 0L ? -1 : 1;
            long var20x;
            var12 = (var20x = var2 - Long.MAX_VALUE) == 0L ? 0 : (var20x < 0L ? -1 : 1);
            long var22 = 113519101159939L;
            int var25 = var22 < 0L ? -1 : 1;
            long var25x = var12 == 0 ? Long.MAX_VALUE : var2 - 1L;
            ArrayList var27 = new ArrayList(var10.length);
            long var28 = var0.startMs();

            for (int var30 = 0; var30 < var10.length; var30++) {
               long var31 = 113519101159940L;
               int var31x = var31 < 0L ? -1 : 1;
               long var33x = Math.max(var28, Math.min(var25x, var11[var30][0] + var16x));
               long var35;
               var12 = (var35 = var11[var30][1] - 0L) == 0L ? 0 : (var35 < 0L ? -1 : 1);
               long var37 = 113519101159939L;
               int var37x = var37 < 0L ? -1 : 1;
               long var39x = var12 > 0 ? Math.min(var25x, var11[var30][1] + var16x) : -1L;
               var27.add(new SpotifyLyrics.TimedLyricLine(var33x, var30 + 1 < var10.length ? var10[var30] + " " : var10[var30], var39x > var33x ? var39x : -1L));
               var28 = var33x;
            }

            long var43 = 113519101159940L;
            int var32 = var43 < 0L ? -1 : 1;
            long var32x = var1.hasEnd() ? Math.min(var25x, var1.endMs() + var16x) : -1L;
            return new SpotifyLyrics.Data(var0.startMs(), var0.text(), List.copyOf(var27), var32x > var0.startMs() ? var32x : -1L);
         }
      }

      return null;
   }

   private static String[] H(String var0, long var1) {
      SpotifyCurve.ensureSourceClassInitialized();
      String var3 = var0.strip();
      boolean var4 = var3.isEmpty();
      long var5 = 49959025034290L;
      int var7 = var5 < 0L ? -1 : 1;
      return var4 ? new String[0] : var3.split("\\s+");
   }

   private static long[][] r(long var0, SpotifyLyrics.Data var2) {
      long[] var3;
      long var4 = 49959025034290L;
      String var6 = var2.text();
      String[] var7 = H(var6, var4);
      long[][] var8 = new long[var7.length][2];
      List var9 = var2.words();
      SpotifyCurve.ensureSourceClassInitialized();
      int[] var10 = new int[var9.size()];
      int var11 = 0;

      for (int var12 = 0; var12 < var9.size(); var12++) {
         long var13 = 52751142868653L;
         int var32 = var13 < 0L ? -1 : 1;
         String var33 = ((SpotifyLyrics.TimedLyricLine)var9.get(var12)).text().strip();
         int var34 = var6.indexOf(var33, var11);
         boolean var35 = var33.isEmpty();
         long var36 = 52751142868652L;
         int var381 = var36 < 0L ? -1 : 1;
         if (var35 || var34 < 0) {
            return null;
         }

         var10[var12] = var34;
         var11 = var34 + var33.length();
      }

      long var31 = 52751142868653L;
      int var15 = var31 < 0L ? -1 : 1;
      var15 = 0;

      for (int var16 = 0; var16 < var7.length; var16++) {
         int var17 = var6.indexOf(var7[var16], var15);
         if (var17 < 0) {
            return null;
         }

         int var18 = var17 + var7[var16].length();
         var15 = var18;
         int var19 = -1;
         int var20 = -1;

         for (int var21 = 0; var21 < var10.length; var21++) {
            int var14 = var10[var21];
            long var22 = 52751142868652L;
            int var25 = var22 < 0L ? -1 : 1;
            if (var14 <= var17) {
               var19 = var21;
            }

            var14 = var10[var21];
            long var25x = 52751142868653L;
            if (var14 < var18) {
               var20 = var21;
            }
         }

         long var37 = 52751142868652L;
         int var40 = var37 < 0L ? -1 : 1;
         if (var19 < 0) {
            return null;
         }

         long var23x;
         label87: {
            var8[var16][0] = ((SpotifyLyrics.TimedLyricLine)var9.get(var19)).startMs();
            var3 = var8[var16];
            long var38 = 52751142868652L;
            if (var20 >= 0) {
               boolean var28 = ((SpotifyLyrics.TimedLyricLine)var9.get(var20)).hasEnd();
               long var29 = 52751142868652L;
               int var29x = var29 < 0L ? -1 : 1;
               if (var28) {
                  var23x = ((SpotifyLyrics.TimedLyricLine)var9.get(var20)).endMs();
                  break label87;
               }
            }

            var23x = -1L;
         }

         var3[1] = var23x;
      }

      return var8;
   }

   private static long a(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 31904) & 32767;
      if (c[var3] == null) {
         c[var3] = b[var3] ^ var1;
      }

      return c[var3];
   }
}
