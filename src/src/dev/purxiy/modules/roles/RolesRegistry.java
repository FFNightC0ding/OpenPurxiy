// Sourced By DevFFNightmare
package dev.purxiy.modules.roles;

import com.mojang.authlib.GameProfile;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.TextSetting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;
import net.minecraft.class_10017;
import net.minecraft.class_1297;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_310;
import net.minecraft.class_5250;
import net.minecraft.class_634;
import net.minecraft.class_640;
import net.minecraft.class_742;

public final class RolesRegistry {
   static final int j = 16714660;
   static final int C = 41727;
   static final int F = 5635925;
   static final int h = 11184810;
   static final String O = "★";
   private static final int U = 512;
   private static class_2561 N = class_2561.method_43473();
   
   private static Object m;
   
   private static Object o;
   
   private static Object u;
   
   private static Object f;
   private static final Map<RolesRegistry.TypeD, class_2561> G = new HashMap<>();
   private static final Pattern D = Pattern.compile("[.*!~+\\-]?[A-Za-z0-9_]{2,16}|[.*!~+\\-][A-Za-z0-9_][A-Za-z0-9_ ]{0,14}[A-Za-z0-9_]");
   static RolesModule J;
   private static ModuleContext M;
   private static int p = 0;
   private static int d = 0;
   public static boolean l;





   private RolesRegistry() {
   }

   private static RolesModule S() {
      return J;
   }

   public static class_2561 prefix() {
      long var0 = 65076557514884L;
      long var2 = var0 ^ 888536192592L;
      RolesModule var4 = S();
      if (var4.n.get() != m || var4.V.get() != o || var4.f.get() != u || var4.E.get() != f) {
         m = var4.n.get();
         o = var4.V.get();
         u = var4.f.get();
         f = var4.E.get();
         N = A(var4, var2);
         G.clear();
      }

      return N;
   }

   private static class_2561 A(RolesModule var0, long var1) {
      long var3 = 74988745395216L;
      class_5250 var5 = class_2561.method_43473();
      if (var0.n.enabled()) {
         var5.method_10852(class_2561.method_43470(Character.toString(128249)).method_54663(16714660));
         var5.method_10852(class_2561.method_43470("+").method_54663(41727));
         var5.method_10852(w(var3));
      }

      int var6 = var0.V.index();
      if (var6 > 0) {
         var5.method_10852(class_2561.method_43470("+".repeat(var6)).method_54663(41727));
         var5.method_10852(w(var3));
      }

      if (var0.f.enabled()) {
         var5.method_10852(class_2561.method_43470("★").method_54663(5635925));
         var5.method_10852(w(var3));
      }

      String var7 = var0.E.get().strip();
      long var8 = 65838392172244L;
      if (65838392172244L == 0L) {
         boolean var10000 = false;
      } else {
         int var11 = var8 < 0L ? -1 : 1;
      }

      if (!RolesSupport4.plain(var7).isBlank()) {
         var5.method_10852(RolesSupport4.legacy(var7, class_2583.field_24360));
         var5.method_10852(w(var3));
      }

      return var5;
   }

   private static class_2561 w(long var0) {
      return class_2561.method_43470(" ").method_54663(11184810);
   }

   public static boolean any() {
      return !prefix().method_10855().isEmpty();
   }

   public static class_2561 withRoles(class_2561 var0, String var1) {
      class_2561 var2 = prefix();
      if (var2.method_10855().isEmpty()) {
         return var0;
      } else {
         RolesRegistry.TypeD var3 = new RolesRegistry.TypeD(var0, var1);
         Object var4 = G.get(var3);
         if (var4 == null) {
            if (G.size() >= 512) {
               G.clear();
            }

            class_5250 var5 = RolesSupport4.fromName(var0, var1);
            var4 = class_2561.method_43473().method_10852(var2).method_10852((class_2561)(var5 != null ? var5 : var0));
            G.put(var3, (class_2561)var4);
         }

         return (class_2561)var4;
      }
   }

   public static class_2561 own(class_2561 var0) {
      class_310 var1 = class_310.method_1551();
      if (!S().enabled()) {
         return var0;
      } else {
         String var2 = var1.field_1724 != null ? var1.field_1724.method_7334().name() : var1.method_1548().method_1676();
         return withRoles(var0, var2);
      }
   }

   public static void decorate(class_1297 var0, class_10017 var1) {
      if (var1.field_53337 != null) {
         var1.field_53337 = name(var0, var1.field_53337);
      }
   }

   public static class_2561 name(class_1297 var0, class_2561 var1) {
      long var2 = 44982120138262L;
      long var4 = var2 ^ 97956805550931L;
      if (var0 instanceof class_742 var6 && S().enabled()) {
         GameProfile var7 = var6.method_7334();
         return Y(var7, var6 == class_310.method_1551().field_1724, var4) ? withRoles(var1, var7.name()) : var1;
      } else {
         return var1;
      }
   }

   public static class_2561 tab(class_640 var0, class_2561 var1) {
      long var2 = 34292167111665L;
      int var4 = (int)((var2 ^ 48200656759724L) >>> 48);
      long var5 = (var2 ^ 48200656759724L) << 16 >>> 16;
      long var7 = var2 ^ 121831774459572L;
      RolesModule var9 = S();
      if (var9.enabled() && var9.C.enabled()) {
         GameProfile var10 = var0.method_2966();
         return Y(var10, U((char)var4, var5, var10.id()), var7) ? withRoles(var1, var10.name()) : var1;
      } else {
         return var1;
      }
   }

   private static boolean Y(GameProfile var0, boolean var1, long var2) {
      long var4 = 58709529445803L;
      if (!var1) {
         boolean var6 = S().everyone();
         long var7 = 125337611789636L;
         if (125337611789636L == 0L) {
            boolean var10000 = false;
         } else {
            int var10 = var7 < 0L ? -1 : 1;
         }

         if (!var6 || !z(var0, var4)) {
            return false;
         }
      }

      return true;
   }

   private static boolean U( char var0, long var1, UUID var3) {
      if (var3 == null) {
         return false;
      } else {
         class_310 var4 = class_310.method_1551();
         class_634 var5 = var4.method_1562();
         return var4.field_1724 != null && var3.equals(var4.field_1724.method_5667())
            || var3.equals(var4.method_1548().method_44717())
            || var5 != null && Objects.equals(var3, var5.method_2879().id());
      }
   }

   private static boolean z(GameProfile var0, long var1) {
      long var3 = 58709529445802L;
      if (58709529445802L == 0L) {
         boolean var10000 = false;
      } else {
         int var8 = var3 < 0L ? -1 : 1;
      }

      if (var0 != null) {
         long var6 = 58709529445803L;
         if (58709529445803L == 0L) {
            boolean var9 = false;
         } else {
            int var10 = var6 < 0L ? -1 : 1;
         }

         if (var0.id() != null && var0.id().version() != 2 && var0.name() != null && D.matcher(var0.name()).matches()) {
            return true;
         }
      }

      return false;
   }

   static void persist() {
      try {
         CosmeticRegistry.storeSettings("roles", J.values());
      } catch (IOException var1) {
         ClientServices.LOGGER.warn("Could not save local Roles settings");
      }
   }

   static RolesRegistry.TypeU nick() {
      return new RolesRegistry.TypeU();
   }

   public static void install(RolesModule var0, ModuleContext var1) {
      J = var0;
      M = var1;
   }

   public static void metric(String var0) {
      if (M != null) {
         M.metric(var0, var0.equals("drawn") ? ++p : ++d);
      }
   }

   public static void uninstall() {
      RolesSupport.remove(class_310.method_1551().field_1705.method_1743());
      G.clear();
      N = class_2561.method_43473();
      f = null;
      u = null;
      o = null;
      m = null;
      J = null;
      M = null;
   }

public static void ensureSourceClassInitialized() {
   }

private record TypeD(class_2561 V, String B) {
      public class_2561 name() {
         return this.V;
      }

      public String player() {
         return this.B;
      }

      public class_2561 minecraftObjectState1() {
         return this.V;
      }

      public String stringState49() {
         return this.B;
      }
   }

   static final class TypeU {
      final BooleanSetting p;
      final TextSetting S;



      TypeU() {
         Map var1 = CosmeticRegistry.readSettings("nick_hider");
         this.p = new BooleanSetting("hide_own", "Hide own", null, Boolean.parseBoolean((String)(var1.getOrDefault("hide_own", "true"))));
         this.S = new TextSetting("nickname", "Nickname", null, (String)(var1.getOrDefault("nickname", "Purxiy")), 64);
      }

      boolean enabled() {
         return CosmeticRegistry.enabled("nick_hider");
      }

      void setEnabled(boolean var1) {
         this.persist();
         CosmeticRegistry.requestEnabled("nick_hider", var1);
      }

      void persist() {
         try {
            CosmeticRegistry.mergeSettings("nick_hider", Map.of("hide_own", Boolean.toString(this.p.enabled()), "nickname", this.S.get()), true);
         } catch (IOException var2) {
            ClientServices.LOGGER.warn("Could not save nickname settings");
         }
      }

}
}
