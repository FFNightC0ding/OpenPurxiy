package dev.purxiy.modules.roles;

import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.mixin.RolesChatAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_124;
import net.minecraft.class_2519;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2960;
import net.minecraft.class_303;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_338;
import net.minecraft.class_3532;
import net.minecraft.class_5250;
import net.minecraft.class_5481;
import net.minecraft.class_7591;
import net.minecraft.class_2558.class_10610;
import net.minecraft.class_2558.class_11506;
import net.minecraft.class_2568.class_10613;
import net.minecraft.class_303.class_7590;

public final class RolesSupport {
   public static final class_2960 W = class_2960.method_60655("purxiy", "roles");
   private static final int j = 13213951;
   private static final int m = 16184319;
   private static final int h = 11840722;
   private static final int E = 8156569;
   private static final int N = 16748451;
   static final class_7591 q = new class_7591(-3563265, null, class_2561.method_43470("SourceByDevFFnightmare DeObfDeCompByGuideX"), "SourceByDevFFnightmare DeObfDeCompByGuideX Roles");
   private static final class_7591 i = new class_7591(-3563265, null, class_2561.method_43470("SourceByDevFFnightmare DeObfDeCompByGuideX"), "SourceByDevFFnightmare DeObfDeCompByGuideX Roles");
   private static final String[] w = new String[]{"Your tag", "Staff star", "Media", "Donut level", "Prefix", "Name", "Worn by", "Tab list"};

   private RolesSupport() {
   }

   static void showMenu() {
      class_310 var0 = class_310.method_1551();
      remove(var0.field_1705.method_1743());
      var0.field_1705.method_1743().method_44811(menu(), null, q);
   }

   static void answer(RolesChatFilter.Data var0, boolean var1) {
      long var2 = 49642820809336L;
      long var4 = var2 ^ 24019949054907L;
      long var6 = var2 ^ 55112763752442L;
      class_310 var8 = class_310.method_1551();
      boolean var9 = !var0.error() && L(var8.field_1705.method_1743(), var4);
      if (var1 && (var9 || var0.error())) {
         if (var0.error()) {
            t(var6, var0);
         }
      } else {
         t(var6, var0);
      }
   }

   private static void t(long var0, RolesChatFilter.Data var2) {
      RolesRegistry.ensureSourceClassInitialized();
      class_5250 var3 = class_2561.method_43473();
      class_5250 var4 = class_2561.method_43470(Translations.tr("Roles"));
      long var5 = 34332593327490L;
      int var8 = var5 < 0L ? -1 : 1;
      var3 = var3.method_10852(var4.method_54663(13213951).method_27692(class_124.field_1067))
         .method_10852(class_2561.method_43470(" › ").method_54663(8156569));
      var4 = class_2561.method_43470(var2.message());
      boolean var8x = var2.error();
      long var9 = 34332593327489L;
      int var11 = var9 < 0L ? -1 : 1;
      class_5250 var11x = var3.method_10852(var4.method_54663(var8x ? 16748451 : 16184319));
      boolean var12 = var2.error();
      long var13 = 34332593327490L;
      int var15 = var13 < 0L ? -1 : 1;
      if (var12) {
         var11x.method_10852(class_2561.method_43470("   " + Translations.tr("Type .fr for the menu")).method_54663(8156569));
         long var15x = 34332593327489L;
         int var16 = var15x < 0L ? -1 : 1;
      } else if (RolesRegistry.J.enabled() && RolesRegistry.any()) {
         var11x.method_10852(class_2561.method_43470("   ")).method_10852(preview());
      }

      class_310.method_1551().field_1705.method_1743().method_44811(var11x, null, i);
   }

   public static boolean click(class_2558 var0) {
      if (var0 instanceof class_11506 var1 && W.equals(var1.comp_4378())) {
         String var2 = var1.comp_4379().flatMap(var0x -> var0x.method_68658()).orElse("");
         RolesChatFilter.run(var2, true);
         return true;
      } else {
         return false;
      }
   }

   static class_2561 menu() {
      long var0 = 87828406652800L;
      long var2 = var0 ^ 1378479200287L;
      long var4 = var0 ^ 58252909170655L;
      int var6 = (int)((var0 ^ 58252909170655L) >>> 56);
      int var7 = (int)((var0 ^ 58252909170655L) << 8 >>> 32);
      int var8 = (int)(var4 << 40 >>> 40);
      var4 = var0 ^ 92196128436287L;
      int var9 = (int)((var0 ^ 92196128436287L) >>> 48);
      int var10 = (int)((var0 ^ 92196128436287L) << 16 >>> 48);
      int var11 = (int)(var4 << 32 >>> 32);
      long var12 = var0 ^ 23945498542845L;
      long var14 = var0 ^ 21635460596672L;
      long var16 = var0 ^ 63758777236513L;
      RolesModule var18 = RolesRegistry.J;
      class_327 var19 = class_310.method_1551().field_1772;
      int var20 = 0;

      for (String var24 : w) {
         var20 = Math.max(var20, var19.method_1727(Translations.tr(var24)));
      }

      int var37 = var20 + 8;
      class_5250 var38 = class_2561.method_43473();
      class_5250 var39 = var38.method_10852(class_2561.method_43470(Translations.tr("Roles")).method_54663(13213951).method_27692(class_124.field_1067))
         .method_27693("  ");
      String var40 = var18.switchedOn() ? "off" : "on";
      boolean var25 = var18.switchedOn();
      var39.method_10852(F((char)var9, var25, (short)var10, var11, var40))
         .method_10852(class_2561.method_43470("   " + Translations.tr("only on your screen")).method_54663(8156569));
      T(var38, var19, var37, "Your tag", var12)
         .method_10852(
            (class_2561)(var18.enabled() && RolesRegistry.any()
               ? preview()
               : class_2561.method_43470(Translations.tr(var18.enabled() ? "no roles chosen" : "Roles are off")).method_54663(8156569))
         );
      T(var38, var19, var37, "Staff star", var12).method_10852(F((char)var9, var18.f.enabled(), (short)var10, var11, "toggle star"));
      T(var38, var19, var37, "Media", var12).method_10852(F((char)var9, var18.n.enabled(), (short)var10, var11, "toggle media"));
      class_5250 var26 = T(var38, var19, var37, "Donut level", var12);

      for (int var27 = 0; var27 <= 3; var27++) {
         if (var27 > 0) {
            var26.method_10852(class_2561.method_43470("   ").method_54663(8156569));
         }

         String var28 = var27 == 0 ? Translations.tr("None") : "+".repeat(var27);
         boolean var29 = var18.V.index() == var27;
         var40 = "donut " + var27;
         var26.method_10852(f(var16, var28, var29, var40));
      }

      class_5250 var43 = T(var38, var19, var37, "Prefix", var12);
      boolean var44 = !RolesCache.plain((String)var18.E.get()).isBlank();
      var43.method_10852(
         var44
            ? RolesCache.legacy(((String)var18.E.get()).strip(), class_2583.field_24360.method_36139(16184319))
            : class_2561.method_43470(Translations.tr("none")).method_54663(8156569)
      );
      var43.method_27693("   ")
         .method_10852(W(Translations.tr("Edit"), var2, ".fr set prefix " + ((String)var18.E.get()).strip(), "Type your prefix after it: &c, &6 … colour it"));
      if (var44) {
         var43.method_27693("  ").method_10852(u(Translations.tr("Clear"), 8156569, "set prefix", "Click to remove your prefix", var14));
      }

      RolesRegistry.RoleCache var45 = RolesRegistry.nick();
      boolean var30 = var45.enabled() && var45.p.enabled();
      class_5250 var31 = T(var38, var19, var37, "Name", var12);
      var31.method_10852(
         var30
            ? RolesCache.legacy(((String)var45.S.get()).strip(), class_2583.field_24360.method_36139(16184319))
            : class_2561.method_43470(F((byte)var6, var7, var8)).method_54663(16184319)
      );
      var31.method_27693("   ")
         .method_10852(W(Translations.tr("Edit"), var2, ".fr set name ", "Type the name you want to be shown with (the Nick Hider shows it everywhere)"));
      if (var30) {
         var31.method_27693("  ").method_10852(u(Translations.tr("Reset"), 8156569, "set name", "Click to show your own name again", var14));
      }

      class_5250 var32 = T(var38, var19, var37, "Worn by", var12);
      String var33 = Translations.tr("Only you");
      boolean var34 = !var18.everyone();
      var40 = "apply self";
      var32.method_10852(f(var16, var33, var34, var40))
         .method_10852(class_2561.method_43470("   ").method_54663(8156569))
         .method_10852(f(var16, Translations.tr("Everyone"), var18.everyone(), "apply all"));
      T(var38, var19, var37, "Tab list", var12).method_10852(F((char)var9, var18.C.enabled(), (short)var10, var11, "toggle tab"));
      return var38;
   }

   private static class_5250 T(class_5250 var0, class_327 var1, int var2, String var3, long var4) {
      RolesRegistry.ensureSourceClassInitialized();
      String var6 = Translations.tr(var3);
      class_5250 var7 = class_2561.method_43473();
      var7.method_10852(class_2561.method_43470(var6).method_54663(11840722));
      long var8 = 99119692877180L;
      int var11 = var8 < 0L ? -1 : 1;
      short var11x = 17451;
      int var12 = Math.max(1, var2 - var1.method_1727(var6));
      int var13 = Math.max(1, var1.method_1727(" "));
      String var14 = Character.toString(57345).repeat(var12 % var13);
      var7.method_10852(class_2561.method_43470(" ".repeat(var12 / var13)));
      long var15 = 99119692877180L;
      int var16 = var15 < 0L ? -1 : 1;
      if (!var14.isEmpty()) {
         var7.method_10852(class_2561.method_43470(var14).method_10862(class_2583.field_24360.method_27704(RolesShadow.S)));
      }

      var0.method_27693("\n").method_10852(var7);
      return var7;
   }

   private static class_2561 F(char var0, boolean var1, short var2, int var3, String var4) {
      long var5 = 31040559579071L;
      long var7 = var5 ^ 70881733620735L;
      RolesRegistry.ensureSourceClassInitialized();
      String var9 = var1 ? "● " + Translations.tr("on") : "○ " + Translations.tr("off");
      int var10 = var1 ? 13213951 : 8156569;
      return u(var9, var10, var4, var1 ? "Click to switch it off" : "Click to switch it on", var7);
   }

   private static class_2561 f(long var0, String var2, boolean var3, String var4) {
      long var5 = 101482578529344L;
      RolesRegistry.ensureSourceClassInitialized();
      int var7 = var3 ? 13213951 : 11840722;
      long var8 = 129870612820897L;
      int var11 = var8 < 0L ? -1 : 1;
      class_5250 var11x = u(var2, var7, var4, var3 ? "Chosen" : "Click to choose", var5);
      return var3 ? var11x.method_27692(class_124.field_1067) : var11x;
   }

   private static class_5250 u(String var0, int var1, String var2, String var3, long var4) {
      return class_2561.method_43470(var0)
         .method_10862(
            class_2583.field_24360
               .method_36139(var1)
               .method_10958(new class_11506(W, Optional.of(class_2519.method_23256(var2))))
               .method_10949(new class_10613(class_2561.method_43470(Translations.tr(var3)).method_54663(16184319)))
         );
   }

   private static class_2561 W(String var0, long var1, String var3, String var4) {
      return class_2561.method_43470(var0)
         .method_10862(
            class_2583.field_24360
               .method_36139(13213951)
               .method_30938(true)
               .method_10958(new class_10610(var3))
               .method_10949(new class_10613(class_2561.method_43470(Translations.tr(var4)).method_54663(16184319)))
         );
   }

   static class_2561 preview() {
      long var0 = 115622863505469L;
      long var2 = var0 ^ 20008654243938L;
      int var4 = (int)((var0 ^ 20008654243938L) >>> 56);
      int var5 = (int)((var0 ^ 20008654243938L) << 8 >>> 32);
      int var6 = (int)(var2 << 40 >>> 40);
      class_310 var7 = class_310.method_1551();
      Object var8 = var7.field_1724 != null ? var7.field_1724.method_5476() : class_2561.method_43470(F((byte)var4, var5, var6));
      return RolesRegistry.own((class_2561)var8);
   }

   private static String F(byte var0, int var1, int var2) {
      RolesRegistry.ensureSourceClassInitialized();
      class_310 var3 = class_310.method_1551();
      return var3.field_1724 != null ? var3.field_1724.method_7334().name() : var3.method_1548().method_1676();
   }

   static void remove(class_338 var0) {
      RolesChatAccessor var1 = (RolesChatAccessor)var0;
      if (var1.purxiy$allMessages().removeIf(var0x -> var0x.comp_894() == q)) {
         var1.purxiy$trimmedMessages().removeIf(var0x -> var0x.comp_897() == q);
      }
   }

   private static boolean L(class_338 var0, long var1) {
      RolesRegistry.ensureSourceClassInitialized();
      RolesChatAccessor var3 = (RolesChatAccessor)var0;
      List var4 = var3.purxiy$allMessages();
      int var5 = -1;

      for (int var6 = 0; var6 < var4.size(); var6++) {
         if (((class_303)var4.get(var6)).comp_894() == q) {
            long var7 = 62666965309891L;
            int var10 = var7 < 0L ? -1 : 1;
            var5 = var6;
            break;
         }
      }

      long var24 = 62666965309890L;
      int var9 = var24 < 0L ? -1 : 1;
      if (var5 < 0) {
         return false;
      } else {
         class_303 var9x = (class_303)var4.get(var5);
         class_303 var10 = new class_303(var9x.comp_892(), menu(), null, q);

         for (int var11 = var4.size() - 1; var11 > var5; var11--) {
            long var12 = 62666965309891L;
            int var14 = var12 < 0L ? -1 : 1;
            if (((class_303)var4.get(var11)).comp_894() == q) {
               var4.remove(var11);
            }
         }

         long var25 = 62666965309891L;
         int var13 = var25 < 0L ? -1 : 1;
         var4.set(var5, var10);
         List var13x = var3.purxiy$trimmedMessages();
         int var14 = -1;

         for (int var15 = 0; var15 < var13x.size(); var15++) {
            if (((class_7590)var13x.get(var15)).comp_897() == q) {
               long var16 = 62666965309891L;
               int var19 = var16 < 0L ? -1 : 1;
               var14 = var15;
               break;
            }
         }

         var13x.removeIf(var0x -> false);
         long var26 = 62666965309890L;
         int var18 = var26 < 0L ? -1 : 1;
         class_310 var18x = class_310.method_1551();
         int var19 = class_3532.method_15357(
            class_338.method_1806((Double)var18x.field_1690.method_42556().method_41753()) / (Double)var18x.field_1690.method_42554().method_41753()
         );
         List var20 = var10.method_75757(var18x.field_1772, var19);
         ArrayList var21 = new ArrayList(var20.size());

         for (int var22 = var20.size() - 1; var22 >= 0; var22--) {
            var21.add(new class_7590(var10.comp_892(), (class_5481)var20.get(var22), q, var22 == var20.size() - 1));
         }

         long var27 = 62666965309891L;
         int var22 = var27 < 0L ? -1 : 1;
         if (var14 >= 0) {
            var13x.addAll(var14, var21);
         }

         return true;
      }
   }
}
