// Sourced By DevFFNightmare
package dev.purxiy.render.font;

import dev.purxiy.i18n.Translations;
import java.util.ArrayList;
import java.util.List;

public final class FontMetrics {
   private static final String ELLIPSIS = "…";

   private FontMetrics() {
   }

   public static String ellipsize(TrueTypeFont var0, String var1, float var2, float var3, float var4) {
      var1 = Translations.shown(var1);
      Translations.pause();

      String var5;
      try {
         var5 = cut(var0, var1, var2, var3, var4);
      } finally {
         Translations.resume();
      }

      return var5;
   }

   private static String cut(TrueTypeFont var0, String var1, float var2, float var3, float var4) {
      if (var0.width(var1, var2, var4) <= var3) {
         return var1;
      } else {
         float var5 = var0.width("…", var2, var4);
         int var6 = 0;
         int var7 = var1.length();

         while (var6 < var7) {
            int var8 = var6 + var7 + 1 >>> 1;
            if (var0.width(var1.substring(0, var8), var2, var4) + var5 <= var3) {
               var6 = var8;
            } else {
               var7 = var8 - 1;
            }
         }

         String var9 = var1.substring(0, var6);
         if (!var9.isEmpty() && Character.isHighSurrogate(var9.charAt(var9.length() - 1))) {
            var9 = var9.substring(0, var9.length() - 1);
         }

         return var9.stripTrailing() + "…";
      }
   }

   public static List<String> wrap(TrueTypeFont var0, String var1, float var2, float var3, float var4) {
      var1 = Translations.shown(var1);
      Translations.pause();

      List var5;
      try {
         var5 = lines(var0, var1, var2, var3, var4);
      } finally {
         Translations.resume();
      }

      return var5;
   }

   private static List<String> lines(TrueTypeFont var0, String var1, float var2, float var3, float var4) {
      ArrayList var5 = new ArrayList();

      for (String var9 : var1.split("\n", -1)) {
         StringBuilder var10 = new StringBuilder();

         for (String var14 : var9.split(" ")) {
            if (!var14.isEmpty()) {
               String var15 = var10.isEmpty() ? var14 : var10 + " " + var14;
               if (var0.width(var15, var2, var4) <= var3) {
                  var10.setLength(0);
                  var10.append(var15);
               } else {
                  if (!var10.isEmpty()) {
                     var5.add(var10.toString());
                     var10.setLength(0);
                  }

                  while (var0.width(var14, var2, var4) > var3 && var14.length() > 1) {
                     int var16 = var14.length() - 1;

                     while (var16 > 1 && var0.width(var14.substring(0, var16), var2, var4) > var3) {
                        var16--;
                     }

                     var5.add(var14.substring(0, var16));
                     var14 = var14.substring(var16);
                  }

                  var10.append(var14);
               }
            }
         }

         var5.add(var10.toString());
      }

      return var5;
   }
}
