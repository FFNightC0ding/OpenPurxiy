// Sourced By DevFFNightmare
package dev.purxiy.modules.roles;

import dev.purxiy.i18n.Translations;
import java.util.Locale;

public final class RolesSupport6 {
   public static final String E = ".fr";





   private RolesSupport6() {
   }

   public static boolean matches(String var0) {
      String var1 = var0.strip();
      return var1.regionMatches(true, 0, ".fr", 0, ".fr".length()) && (var1.length() == ".fr".length() || var1.charAt(".fr".length()) == ' ');
   }

   public static boolean intercept(String var0) {
      if (!matches(var0)) {
         return false;
      } else {
         run(var0.strip().substring(".fr".length()).strip(), false);
         return true;
      }
   }

   static void run(String var0, boolean var1) {
      long var2 = 32156461207243L;
      long var4 = (var2 ^ 931655340731L) >>> 16;
      int var6 = (int)((var2 ^ 931655340731L) << 48 >>> 48);
      long var7 = var2 ^ 128790750088992L;
      long var9 = var2 ^ 16852097476145L;
      long var11 = var2 ^ 8361385612933L;
      long var13 = (var2 ^ 38635132795010L) >>> 32;
      int var15 = (int)((var2 ^ 38635132795010L) << 32 >>> 32);
      String[] var16 = var0.isEmpty() ? new String[0] : var0.split("\\s+");
      String var17 = var16.length > 0 ? H(var16[0]) : "";
      String var18 = var16.length > 1 ? H(var16[1]) : "";
      if (!var17.isEmpty() && !var17.equals("menu") && !var17.equals("help")) {
         RolesSupport6.Data var19 = switch (var17) {
            case "on" -> T(var4, true, (short)var6);
            case "off" -> T(var4, false, (short)var6);
            case "toggle" -> u(var18, var13, var15);
            case "donut" -> O(var18, var11);
            case "set" -> F(var18, after(var0, 2), var9);
            case "apply" -> H(var18, var7);
            default -> RolesSupport6.Data.error(Translations.tr("Unknown command: .fr " + var0));
         };
         if (!var19.error()) {
            RolesRegistry.persist();
         }

         RolesSupport.answer(var19, var1);
      } else {
         RolesSupport.showMenu();
      }
   }

   private static RolesModule D() {
      return RolesRegistry.J;
   }

   private static RolesSupport6.Data T(long var0, boolean var2, short var3) {
      D().setEnabled(var2);
      return RolesSupport6.Data.done(Translations.tr(var2 ? "Roles on" : "Roles off"));
   }

   private static RolesSupport6.Data u(String var0, long var1, int var3) {
      long var4 = 68291912480329L;
      long var6 = (var4 ^ 39563554843193L) >>> 16;
      int var8 = (int)((var4 ^ 39563554843193L) << 48 >>> 48);
      long var9 = var4 ^ 56326814339666L;
      RolesRegistry.ensureSourceClassInitialized();
      RolesModule var11 = D();
      byte var13 = -1;
      switch (var0.hashCode()) {
         case 0:
            if (var0.equals("")) {
               long var21 = 15899L;
               if (15899L == 0L) {
                  boolean var29 = false;
               } else {
                  int var30 = var21 < 0L ? -1 : 1;
               }

               var13 = 0;
            }
            break;
         case 114581:
            boolean var14 = var0.equals("tab");
            long var15 = 15899L;
            if (15899L == 0L) {
               boolean var10000 = false;
            } else {
               int var28 = var15 < 0L ? -1 : 1;
            }

            if (var14) {
               var13 = 4;
            }
            break;
         case 3540562:
            if (var0.equals("star")) {
               var13 = 1;
            }
            break;
         case 103772132:
            if (var0.equals("media")) {
               var13 = 3;
            }
            break;
         case 109757152:
            if (var0.equals("staff")) {
               var13 = 2;
            }
      }

      long var22 = 15899L;
      if (15899L == 0L) {
         boolean var31 = false;
      } else {
         int var32 = var22 < 0L ? -1 : 1;
      }
      return switch (var13) {
         case 0 -> {
            boolean var25 = !var11.switchedOn();
            yield T(var6, var25, (short)var8);
         }
         case 1, 2 -> {
            var11.f.toggle();
            boolean var24 = var11.f.enabled();
            boolean var27 = var11.f.enabled();
            yield m(var9, var24, Translations.tr(var27 ? "Staff star on" : "Staff star off"));
         }
         case 3 -> {
            var11.n.toggle();
            boolean var23 = var11.n.enabled();
            boolean var26 = var11.n.enabled();
            yield m(var9, var23, Translations.tr(var26 ? "Media on" : "Media off"));
         }
         case 4 -> {
            var11.C.toggle();
            boolean var17 = var11.C.enabled();
            long var18 = 15899L;
            if (15899L == 0L) {
               boolean var34 = false;
            } else {
               int var35 = var18 < 0L ? -1 : 1;
            }

            yield RolesSupport6.Data.done(Translations.tr(var17 ? "Tab list on" : "Tab list off"));
         }
         default -> RolesSupport6.Data.error(Translations.tr("Toggle media, star or tab"));
      };
   }

   private static RolesSupport6.Data O(String var0, long var1) {
      long var3 = 14460767806491L;
      RolesRegistry.ensureSourceClassInitialized();
      byte var6 = -1;
      switch (var0.hashCode()) {
         case 43:
            if (var0.equals("+")) {
               long var22 = 29299379394637L;
               if (29299379394637L == 0L) {
                  boolean var42 = false;
               } else {
                  int var43 = var22 < 0L ? -1 : 1;
               }

               var6 = 4;
            }
            break;
         case 48:
            if (var0.equals("0")) {
               long var21 = 29299379394638L;
               if (29299379394638L == 0L) {
                  boolean var40 = false;
               } else {
                  int var41 = var21 < 0L ? -1 : 1;
               }

               var6 = 0;
            }
            break;
         case 49:
            if (var0.equals("1")) {
               long var20 = 29299379394638L;
               if (29299379394638L == 0L) {
                  boolean var38 = false;
               } else {
                  int var39 = var20 < 0L ? -1 : 1;
               }

               var6 = 3;
            }
            break;
         case 50:
            if (var0.equals("2")) {
               long var19 = 29299379394638L;
               if (29299379394638L == 0L) {
                  boolean var36 = false;
               } else {
                  int var37 = var19 < 0L ? -1 : 1;
               }

               var6 = 5;
            }
            break;
         case 51:
            if (var0.equals("3")) {
               long var18 = 29299379394638L;
               if (29299379394638L == 0L) {
                  boolean var34 = false;
               } else {
                  int var35 = var18 < 0L ? -1 : 1;
               }

               var6 = 7;
            }
            break;
         case 1376:
            if (var0.equals("++")) {
               long var17 = 29299379394638L;
               if (29299379394638L == 0L) {
                  boolean var32 = false;
               } else {
                  int var33 = var17 < 0L ? -1 : 1;
               }

               var6 = 6;
            }
            break;
         case 42699:
            boolean var7 = var0.equals("+++");
            long var8 = 29299379394637L;
            if (29299379394637L == 0L) {
               boolean var30 = false;
            } else {
               int var31 = var8 < 0L ? -1 : 1;
            }

            if (var7) {
               var6 = 8;
            }
            break;
         case 109935:
            if (var0.equals("off")) {
               long var25 = 29299379394638L;
               if (29299379394638L == 0L) {
                  boolean var28 = false;
               } else {
                  int var29 = var25 < 0L ? -1 : 1;
               }

               var6 = 2;
            }
            break;
         case 3387192:
            if (var0.equals("none")) {
               long var11 = 29299379394637L;
               if (29299379394637L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var27 = var11 < 0L ? -1 : 1;
               }

               var6 = 1;
            }
      }
      byte var23 = switch (var6) {
         case 0, 1, 2 -> 0;
         case 3, 4 -> 1;
         case 5, 6 -> 2;
         case 7, 8 -> 3;
         default -> -1;
      };
      long var24 = 29299379394638L;
      if (29299379394638L == 0L) {
         boolean var45 = false;
      } else {
         int var46 = var24 < 0L ? -1 : 1;
      }

      long var26 = 29299379394638L;
      if (29299379394638L == 0L) {
         boolean var47 = false;
      } else {
         int var48 = var26 < 0L ? -1 : 1;
      }

      if (var23 < 0) {
         return RolesSupport6.Data.error(Translations.tr("The Donut level is 0, 1, 2 or 3"));
      } else {
         D().V.select(var23);
         boolean var13 = var23 > 0;
         long var14 = 29299379394638L;
         if (29299379394638L == 0L) {
            boolean var49 = false;
         } else {
            int var50 = var14 < 0L ? -1 : 1;
         }

         return m(var3, var13, Translations.tr(var23 > 0 ? "Donut level " + "+".repeat(var23) : "No Donut level"));
      }
   }

   private static RolesSupport6.Data F(String var0, String var1, long var2) {
      long var4 = 14460767806491L;
      RolesRegistry.ensureSourceClassInitialized();
      RolesModule var6 = D();
      byte var8 = -1;
      switch (var0.hashCode()) {
         case -980110702:
            if (var0.equals("prefix")) {
               long var22 = 20257972037882L;
               if (20257972037882L == 0L) {
                  boolean var25 = false;
               } else {
                  int var26 = var22 < 0L ? -1 : 1;
               }

               var8 = 0;
            }
            break;
         case 3373707:
            boolean var9 = var0.equals("name");
            long var10 = 20257972037882L;
            if (20257972037882L == 0L) {
               boolean var10000 = false;
            } else {
               int var24 = var10 < 0L ? -1 : 1;
            }

            if (var9) {
               var8 = 1;
            }
      }

      long var23 = 20257972037881L;
      if (20257972037881L == 0L) {
         boolean var27 = false;
      } else {
         int var28 = var23 < 0L ? -1 : 1;
      }

      switch (var8) {
         case 0:
            var6.E.set(var1);
            boolean var12 = !RolesSupport4.plain(var6.E.get()).isBlank();
            long var13 = 20257972037882L;
            if (20257972037882L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var13 < 0L ? -1 : 1;
            }

            return m(var4, var12, Translations.tr(var12 ? "Prefix set" : "Prefix cleared"));
         case 1:
            RolesRegistry.TypeU var15 = RolesRegistry.nick();
            long var16 = 20257972037882L;
            if (20257972037882L == 0L) {
               boolean var29 = false;
            } else {
               int var30 = var16 < 0L ? -1 : 1;
            }

            String var19 = RolesSupport4.plain(var1);
            long var20 = 20257972037882L;
            if (20257972037882L == 0L) {
               boolean var31 = false;
            } else {
               int var32 = var20 < 0L ? -1 : 1;
            }

            if (var19.isBlank()) {
               var15.p.set(false);
               var15.persist();
               int var18 = -1388698252;
               int var11 = -1388697350;
               return RolesSupport6.Data.done(Translations.tr("Your own name shows again"));
            }

            var15.S.set(var1);
            var15.p.set(true);
            var15.setEnabled(true);
            return RolesSupport6.Data.done(Translations.tr("Shown as " + RolesSupport4.plain(var15.S.get()).strip()));
         default:
            return RolesSupport6.Data.error(Translations.tr("Set a prefix or a name"));
      }
   }

   private static RolesSupport6.Data H(String var0, long var1) {
      RolesModule var3 = D();
      RolesRegistry.ensureSourceClassInitialized();
      byte var5 = -1;
      switch (var0.hashCode()) {
         case -1006804125:
            if (var0.equals("others")) {
               var5 = 5;
            }
            break;
         case 3480:
            if (var0.equals("me")) {
               long var12 = 114475585389035L;
               if (114475585389035L == 0L) {
                  boolean var20 = false;
               } else {
                  int var21 = var12 < 0L ? -1 : 1;
               }

               var5 = 2;
            }
            break;
         case 96673:
            if (var0.equals("all")) {
               long var11 = 114475585389034L;
               if (114475585389034L == 0L) {
                  boolean var18 = false;
               } else {
                  int var19 = var11 < 0L ? -1 : 1;
               }

               var5 = 3;
            }
            break;
         case 119839:
            if (var0.equals("you")) {
               long var10 = 114475585389035L;
               if (114475585389035L == 0L) {
                  boolean var16 = false;
               } else {
                  int var17 = var10 < 0L ? -1 : 1;
               }

               var5 = 1;
            }
            break;
         case 3526476:
            if (var0.equals("self")) {
               long var9 = 114475585389034L;
               if (114475585389034L == 0L) {
                  boolean var14 = false;
               } else {
                  int var15 = var9 < 0L ? -1 : 1;
               }

               var5 = 0;
            }
            break;
         case 281977195:
            if (var0.equals("everyone")) {
               long var6 = 114475585389035L;
               if (114475585389035L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var13 = var6 < 0L ? -1 : 1;
               }

               var5 = 4;
            }
      }

      switch (var5) {
         case 0:
         case 1:
         case 2:
            var3.t.set("Only you");
            return RolesSupport6.Data.done(Translations.tr("Roles on your name only"));
         case 3:
         case 4:
         case 5:
            var3.t.set("Everyone");
            return RolesSupport6.Data.done(Translations.tr("Roles on every player"));
         default:
            return RolesSupport6.Data.error(Translations.tr("Apply to self or all"));
      }
   }

   private static RolesSupport6.Data m(long var0, boolean var2, String var3) {
      RolesRegistry.ensureSourceClassInitialized();
      RolesModule var4 = D();
      String var5 = var3;
      if (var2) {
         long var6 = 14460767806491L;
         if (14460767806491L == 0L) {
            boolean var10000 = false;
         } else {
            int var9 = var6 < 0L ? -1 : 1;
         }

         if (!var4.switchedOn()) {
            var4.setEnabled(true);
            var5 = var3 + " · " + Translations.tr("Roles on");
         }
      }

      return RolesSupport6.Data.done(var5);
   }

   private static String H(String var0) {
      return var0.toLowerCase(Locale.ROOT);
   }

   static String after(String var0, int var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1; var3++) {
         while (var2 < var0.length() && var0.charAt(var2) == ' ') {
            var2++;
         }

         while (var2 < var0.length() && var0.charAt(var2) != ' ') {
            var2++;
         }
      }

      return var0.substring(Math.min(var2, var0.length())).strip();
   }

   
   static String verbOf(String var0) {
      if (!matches(var0)) {
         return null;
      } else {
         String var1 = var0.strip().substring(".fr".length()).strip();
         return var1.isEmpty() ? "" : H(var1.split("\\s+")[0]);
      }
   }

record Data(String a, boolean n) {
      static RolesSupport6.Data done(String var0) {
         return new RolesSupport6.Data(var0, false);
      }

      static RolesSupport6.Data error(String var0) {
         return new RolesSupport6.Data(var0, true);
      }

      public String message() {
         return this.a;
      }

      public boolean error() {
         return this.n;
      }

      public String stringState40() {
         return this.a;
      }

      public boolean booleanState33() {
         return this.n;
      }
   }
}
