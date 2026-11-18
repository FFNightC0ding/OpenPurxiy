// Sourced By DevFFNightmare
package dev.purxiy.modules.roles;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_5250;
import net.minecraft.class_5251;

final class RolesSupport4 {



   private RolesSupport4() {
   }

   static class_5250 legacy(String var0, class_2583 var1) {
      long var2 = 50213247587020L;
      long var4 = var2 ^ 98381489182160L;
      long var6 = var2 ^ 122935209305836L;
      int var8 = (int)((var2 ^ 122935209305836L) >>> 32);
      int var9 = (int)((var2 ^ 122935209305836L) << 32 >>> 48);
      int var10 = (int)(var6 << 48 >>> 48);
      class_5250 var11 = class_2561.method_43473();
      StringBuilder var12 = new StringBuilder();
      class_2583 var13 = var1;

      for (int var14 = 0; var14 < var0.length(); var14++) {
         char var15 = var0.charAt(var14);
         if ((var15 == '&' || var15 == 167) && var14 + 1 < var0.length()) {
            char var16 = Character.toLowerCase(var0.charAt(var14 + 1));
            class_2583 var17 = null;
            byte var18 = 1;
            if (var16 == '#' && var14 + 8 <= var0.length() && M(var0, var14 + 2, var4)) {
               var17 = var1.method_27703(class_5251.method_27717(Integer.parseInt(var0, var14 + 2, var14 + 8, 16)));
               var18 = 7;
            } else {
               class_124 var19 = class_124.method_544(var16);
               if (var19 == class_124.field_1070) {
                  var17 = var1;
               } else if (var19 != null && var19.method_543()) {
                  var17 = var1.method_10977(var19);
               } else if (var19 != null) {
                  var17 = var13.method_27706(var19);
               }
            }

            if (var17 != null) {
               G(var11, var8, var12, var9, var13, (char)var10);
               var13 = var17;
               var14 += var18;
               continue;
            }
         }

         var12.append(var15);
      }

      G(var11, var8, var12, var9, var13, (char)var10);
      return var11;
   }

   private static void G(class_5250 var0, int var1, StringBuilder var2, int var3, class_2583 var4, char var5) {
      RolesRegistry.ensureSourceClassInitialized();
      if (!var2.isEmpty()) {
         var0.method_10852(class_2561.method_43470(var2.toString()).method_10862(var4));
         var2.setLength(0);
      }
   }

   private static boolean M(String var0, int var1, long var2) {
      RolesRegistry.ensureSourceClassInitialized();

      for (int var4 = var1; var4 < var1 + 6; var4++) {
         int var5 = var0.charAt(var4);
         long var6 = 128441391122203L;
         if (128441391122203L == 0L) {
            boolean var10000 = false;
         } else {
            int var16 = var6 < 0L ? -1 : 1;
         }

         long var9 = 128441391122204L;
         if (128441391122204L == 0L) {
            boolean var17 = false;
         } else {
            int var18 = var9 < 0L ? -1 : 1;
         }

         var5 = Character.digit((char)var5, 16);
         long var11 = 128441391122203L;
         if (128441391122203L == 0L) {
            boolean var19 = false;
         } else {
            int var20 = var11 < 0L ? -1 : 1;
         }

         if (var5 < 0) {
            return false;
         }
      }

      long var15 = 128441391122204L;
      if (128441391122204L == 0L) {
         boolean var21 = false;
      } else {
         int var22 = var15 < 0L ? -1 : 1;
      }

      return true;
   }

   static String plain(String var0) {
      return legacy(var0, class_2583.field_24360).getString();
   }

   
   static class_5250 fromName(class_2561 var0, String var1) {
      long var2 = 117389550978820L;
      long var4 = var2 ^ 87567511377807L;
      ArrayList var6 = new ArrayList();
      ArrayList var7 = new ArrayList();
      StringBuilder var8 = new StringBuilder();
      var0.method_27658((var3, var4x) -> {
         var6.add(var3);
         var7.add(var4x);
         var8.append(var4x);
         return Optional.empty();
      }, class_2583.field_24360);
      int var9 = find(var8, var1);
      if (var9 < 0) {
         return null;
      } else {
         class_5250 var10 = class_2561.method_43473();
         int var11 = 0;

         for (int var12 = 0; var12 < var7.size(); var12++) {
            String var13 = (String)var7.get(var12);
            int var14 = var11 + var13.length();
            if (var14 > var9) {
               String var15 = var11 >= var9 ? var13 : X(var4, var13, var9 - var11) + var13.substring(var9 - var11);
               var10.method_10852(class_2561.method_43470(var15).method_10862((class_2583)var6.get(var12)));
            }

            var11 = var14;
         }

         return var10;
      }
   }

   static int find(CharSequence var0, String var1) {
      long var2 = 83693019208907L;
      long var4 = var2 ^ 136000328801295L;
      if (var1.isEmpty()) {
         return -1;
      } else {
         String var6 = var0.toString().toLowerCase(Locale.ROOT);
         String var7 = var1.toLowerCase(Locale.ROOT);

         for (int var8 = var6.indexOf(var7); var8 >= 0; var8 = var6.indexOf(var7, var8 + 1)) {
            int var9 = var8 + var7.length();
            boolean var10 = var8 == 0 || !H(var4, var6.charAt(var8 - 1)) || var8 >= 2 && var6.charAt(var8 - 2) == 167;
            boolean var11 = var9 >= var6.length() || !H(var4, var6.charAt(var9)) || var6.charAt(var9) == 167;
            if (var10 && var11) {
               return var8;
            }
         }

         return -1;
      }
   }

   private static boolean H(long var0, char var2) {
      RolesRegistry.ensureSourceClassInitialized();
      long var3 = 61225965158596L;
      if (61225965158596L == 0L) {
         boolean var10000 = false;
      } else {
         int var12 = var3 < 0L ? -1 : 1;
      }

      boolean var6 = Character.isLetterOrDigit(var2);
      long var7 = 61225965158596L;
      if (61225965158596L == 0L) {
         boolean var13 = false;
      } else {
         int var14 = var7 < 0L ? -1 : 1;
      }

      if (!var6) {
         long var9 = 61225965158595L;
         if (61225965158595L == 0L) {
            boolean var15 = false;
         } else {
            int var16 = var9 < 0L ? -1 : 1;
         }

         if (var2 != '_') {
            return false;
         }
      }

      return true;
   }

   private static String X(long var0, String var2, int var3) {
      StringBuilder var4 = new StringBuilder();
      RolesRegistry.ensureSourceClassInitialized();

      for (int var5 = 0; var5 + 1 < var3; var5++) {
         long var6 = 41126527783051L;
         if (41126527783051L == 0L) {
            boolean var10000 = false;
         } else {
            int var19 = var6 < 0L ? -1 : 1;
         }

         long var9 = 41126527783050L;
         if (41126527783050L == 0L) {
            boolean var20 = false;
         } else {
            int var21 = var9 < 0L ? -1 : 1;
         }

         char var11 = var2.charAt(var5);
         long var12 = 41126527783050L;
         if (41126527783050L == 0L) {
            boolean var22 = false;
         } else {
            int var23 = var12 < 0L ? -1 : 1;
         }

         if (var11 == 167) {
            class_124 var15 = class_124.method_544(var2.charAt(var5 + 1));
            long var16 = 41126527783051L;
            if (41126527783051L == 0L) {
               boolean var24 = false;
            } else {
               int var25 = var16 < 0L ? -1 : 1;
            }

            if (var15 != null) {
               if (var15.method_543() || var15 == class_124.field_1070) {
                  var4.setLength(0);
               }

               var4.append('§').append(var15.method_36145());
            }

            var5++;
         }
      }

      long var18 = 41126527783051L;
      if (41126527783051L == 0L) {
         boolean var26 = false;
      } else {
         int var27 = var18 < 0L ? -1 : 1;
      }

      return var4.toString();
   }

}
