package dev.purxiy.modules.spotify;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SpotifyCodec3 {
   private static final Pattern E = Pattern.compile("^\\[([^\\]]*)]");
   private static final Pattern T = Pattern.compile("^\\s*(\\d{1,4}):(\\d{1,2})(?:[.:](\\d{1,3}))?\\s*$");
   private static final Pattern l = Pattern.compile("<(\\d{1,4}):(\\d{1,2})(?:[.:](\\d{1,3}))?>");
   private static final Pattern S = Pattern.compile("^\\s*offset\\s*:\\s*([+-]?\\d+)\\s*$", 2);
   private static final String[] b = new String[5];
   private static final long[] e = new long[]{
      8127929053595797648L, 9131780142160098576L, 7456016190717308834L, 8839821401078479029L, -1796153308253408886L, -911746951071004120L
   };
   private static final Long[] f = new Long[6];

   private SpotifyCodec3() {
   }

   public static SpotifySupport3 parse(String var0) {
      long var1 = 106071990455278L;
      long var3 = var1 ^ 123424909962327L;
      long var5 = var1 ^ 113927840570272L;
      long var7 = (var1 ^ 129792789315125L) >>> 32;
      int var9 = (int)((var1 ^ 129792789315125L) << 32 >>> 32);
      long var10 = var1 ^ 139035707903428L;
      int var12 = (int)((var1 ^ 139035707903428L) >>> 48);
      int var13 = (int)((var1 ^ 139035707903428L) << 16 >>> 32);
      int var14 = (int)(var10 << 48 >>> 48);
      ArrayList var15 = new ArrayList();
      long var16 = 0L;

      for (String var21 : var0.split("\r\n|\r|\n")) {
         String var22 = var21.strip();
         ArrayList var23 = new ArrayList(2);

         Matcher var24;
         while ((var24 = E.matcher(var22)).find()) {
            String var25 = var24.group(1);
            Matcher var26 = T.matcher(var25);
            if (var26.matches()) {
               var23.add(e(var7, var26.group(1), var26.group(2), var26.group(3), var9));
            } else {
               Matcher var27 = S.matcher(var25);
               if (var27.matches()) {
                  try {
                     var16 = Long.parseLong(var27.group(1).replace("+", ""));
                  } catch (NumberFormatException var36) {
                  }
               }
            }

            var22 = var22.substring(var24.end());
         }

         if (!var23.isEmpty()) {
            SpotifyCodec3.TypeS var45 = B((short)var12, var22, var13, var14);
            String var47 = Z(l.matcher(var22).replaceAll(""), var5);
            long var49 = Long.MIN_VALUE;
            long var29 = var45.words().isEmpty() ? var45.end() : var45.words().getFirst().startMs();

            for (long var32 : var23) {
               if (var29 >= 0L && var32 <= var29 && var32 > var49) {
                  var49 = var32;
               }
            }

            for (long var51 : var23) {
               long var34 = var49 == Long.MIN_VALUE ? 0L : var51 - var49;
               var15.add(new SpotifySupport3.Data(var51, var47, G(var45.words(), var34, var3), var45.end() >= 0L ? var45.end() + var34 : -1L));
            }
         }
      }

      if (var15.isEmpty()) {
         return SpotifySupport3.f;
      } else {
         long var37 = -var16;
         ArrayList var38 = new ArrayList(var15.size());
         var15.sort(Comparator.comparingLong(SpotifySupport3.Data::startMs));

         for (SpotifySupport3.Data var41 : var15) {
            long var43 = Math.max(0L, var41.startMs() + var37);
            List var46 = G(var41.words(), var37, var3);
            long var48 = var41.endMs() >= 0L ? Math.max(var43, var41.endMs() + var37) : -1L;
            if (!var41.isBreak() || var38.isEmpty() || !((SpotifySupport3.Data)var38.getLast()).isBreak()) {
               var38.add(new SpotifySupport3.Data(var43, var41.text(), List.copyOf(var46), var48));
            }
         }

         boolean var40 = false;

         for (SpotifySupport3.Data var44 : var38) {
            var40 |= !var44.isBreak();
         }

         return var40 ? new SpotifySupport3(SpotifySupport3.TypeK.k, var38, "") : SpotifySupport3.f;
      }
   }

   private static SpotifyCodec3.TypeS B(short var0, String var1, int var2, int var3) {
      long var4 = 33032773092906L;
      long var6 = var4 ^ 17800669608899L;
      int var8 = (int)((var4 ^ 17800669608899L) >>> 56);
      int var9 = (int)((var4 ^ 17800669608899L) << 8 >>> 32);
      int var10 = (int)(var6 << 40 >>> 40);
      long var11 = var4 ^ 57122268324567L;
      long var13 = (var4 ^ 9313517237233L) >>> 32;
      int var15 = (int)((var4 ^ 9313517237233L) << 32 >>> 32);
      Matcher var16 = l.matcher(var1);
      ArrayList var17 = new ArrayList();
      long var18 = -1L;

      int var20;
      for (var20 = 0; var16.find(); var20 = var16.end()) {
         if (var18 >= 0L) {
            D(var17, (byte)var8, var9, var18, var1.substring(var20, var16.start()), var10);
         }

         var18 = e(var13, var16.group(1), var16.group(2), var16.group(3), var15);
      }

      long var21 = -1L;
      long var23;
      int var25 = (var23 = var18 - 0L) == 0L ? 0 : (var23 < 0L ? -1 : 1);
      if (var25 >= 0) {
         boolean var26 = a(var1.substring(var20), false, var11).isEmpty();
         var21 = var26 ? var18 : -1L;
         D(var17, (byte)var8, var9, var18, var1.substring(var20), var10);
      }

      return new SpotifyCodec3.TypeS((List<SpotifySupport3.Data2>)(var17.size() > 1 ? var17 : List.of()), var21);
   }

   private static void D(List var0, byte var1, int var2, long var3, String var5, int var6) {
      long var7 = 15648715603433L;
      long var9 = var7 ^ 39321936959764L;
      String var11 = a(var5, false, var9);
      if (!var11.isEmpty()) {
         var0.add(new SpotifySupport3.Data2(var3, var11));
      } else {
         int var12 = 0;
         if (!var0.isEmpty()) {
            SpotifySupport3.Data2 var13 = (SpotifySupport3.Data2)var0.getLast();
            long var14;
            int var16 = (var14 = var3 - var13.startMs()) == 0L ? 0 : (var14 < 0L ? -1 : 1);
            var12 = 2260457;
            if (var16 > 0 && !var13.hasEnd()) {
               var0.set(var0.size() - 1, new SpotifySupport3.Data2(var13.startMs(), var13.text(), var3));
            }
         }
      }
   }

   private static List G(List var0, long var1, long var3) {
      long var5;
      int var7 = (var5 = var1 - 0L) == 0L ? 0 : (var5 < 0L ? -1 : 1);
      long var8 = 17840600377272L;
      int var11 = var8 < 0L ? -1 : 1;
      if (var7 != 0) {
         long var11x = 17840600377272L;
         int var13 = var11x < 0L ? -1 : 1;
         if (!var0.isEmpty()) {
            ArrayList var13x = new ArrayList(var0.size());

            for (SpotifySupport3.Data2 var15 : var0) {
               long var16 = 17840600377273L;
               int var18 = var16 < 0L ? -1 : 1;
               var13x.add(
                  new SpotifySupport3.Data2(Math.max(0L, var15.startMs() + var1), var15.text(), var15.hasEnd() ? Math.max(0L, var15.endMs() + var1) : -1L)
               );
               long var18x = 17840600377272L;
               int var19 = var18x < 0L ? -1 : 1;
            }

            return var13x;
         }
      }

      return var0;
   }

   private static long e(long var0, String var2, String var3, String var4, int var5) {
      long var6 = Long.parseLong(var2) * 60000L + Long.parseLong(var3) * 1000L;
      if (var4 != null && !var4.isEmpty()) {
         long var8 = 5746L;
         int var10 = var8 < 0L ? -1 : 1;

         var6 += switch (var4.length()) {
            case 1 -> Long.parseLong(var4) * 100L;
            case 2 -> Long.parseLong(var4) * 10L;
            default -> Long.parseLong(var4);
         };
      }

      return var6;
   }

   private static String Z(String var0, long var1) {
      long var3 = 50546836235517L;
      return a(var0, true, var3);
   }

   private static String a(String var0, boolean var1, long var2) {
      StringBuilder var4 = new StringBuilder(var0.length());
      boolean var5 = false;
      int var6 = 0;

      while (var6 < var0.length()) {
         int var7 = var0.codePointAt(var6);
         long var8 = 50546836235516L;
         int var10 = var8 < 0L ? -1 : 1;
         var10 = var6 + Character.charCount(var7);
         long var11 = 50546836235517L;
         int var24 = var11 < 0L ? -1 : 1;
         var6 = var10;
         if (!Character.isWhitespace(var7)) {
            long var14 = 50546836235516L;
            int var16 = var14 < 0L ? -1 : 1;
            if (var7 != 160) {
               long var16x = 50546836235516L;
               int var25 = var16x < 0L ? -1 : 1;
               if (Character.isISOControl(var7)) {
                  long var18 = 50546836235517L;
                  int var26 = var18 < 0L ? -1 : 1;
               } else {
                  long var25x = 50546836235517L;
                  int var34 = var25x < 0L ? -1 : 1;
                  if (var5) {
                     long var20 = 50546836235516L;
                     int var21 = var20 < 0L ? -1 : 1;
                     if (!var4.isEmpty()) {
                        var4.append(' ');
                     }
                  }

                  var5 = false;
                  var4.appendCodePoint(var7);
                  long var26 = 50546836235517L;
                  int var37 = var26 < 0L ? -1 : 1;
               }
               continue;
            }
         }

         var5 = true;
         long var24x = 50546836235516L;
         int var30 = var24x < 0L ? -1 : 1;
      }

      long var22 = 50546836235516L;
      int var23 = var22 < 0L ? -1 : 1;
      if (var5) {
         long var23x = 50546836235516L;
         int var251 = var23x < 0L ? -1 : 1;
         if (!var1) {
            long var12 = 50546836235517L;
            int var29 = var12 < 0L ? -1 : 1;
            if (!var4.isEmpty()) {
               var4.append(' ');
            }
         }
      }

      return var4.toString();
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ -594129291 ^ 22221) & 32767;
      if (f[var3] == null) {
         f[var3] = e[var3] ^ 1796153308253408885L;
      }

      return f[var3];
   }

   private record TypeS(List<SpotifySupport3.Data2> C, long i) {
      public List<SpotifySupport3.Data2> words() {
         return this.C;
      }

      public long end() {
         return this.i;
      }

      public List<SpotifySupport3.Data2> listState18() {
         return this.C;
      }

      public long longState17() {
         return this.i;
      }
   }
}
