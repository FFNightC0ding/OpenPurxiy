// Sourced By DevFFNightmare
package dev.purxiy.modules.roles;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.TextSetting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.CosmeticCatalog;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientRegistry14;
import dev.purxiy.loader.fabric.client.ClientSupport2;
import net.minecraft.class_10017;
import net.minecraft.class_1297;
import net.minecraft.class_2561;
import net.minecraft.class_640;

public final class RolesModule extends ModuleDescriptor {
   public static final String p = "None";
   public static final String j = "Only you";
   public static final String G = "Everyone";
   public final BooleanSetting f;
   public final BooleanSetting n;
   public final ModeSetting V;
   public final TextSetting E;
   public final ModeSetting t;
   public final BooleanSetting C;



   public RolesModule() {
      super("roles", "Roles", "DonutSMP staff roles before your name. Only you see them", "badge-check");
      this.section("Roles");
      this.f = this.bool("staff_star", "Staff star", "A green ★, as DonutSMP's staff wear it", true);
      this.n = this.bool("media", "Media", "The pink camera with its plus", false);
      this.V = this.mode("donut_level", "Donut level", "One, two or three light blue plusses", "None", new String[]{"None", "+", "++", "+++"});
      this.E = this.add(
         new TextSetting("custom_prefix", "Custom prefix", "Any text before your name · &c colours work", "", 48).placeholder("&6[VIP]").colorCodes()
      );
      this.section("Shown");
      this.t = this.mode("apply_to", "Worn by", "Only your name, or every player's", "Only you", new String[]{"Only you", "Everyone"});
      this.C = this.bool("tab_list", "Tab list", "The roles in the player list too", true);
   }

   public boolean everyone() {
      return this.t.is("Everyone");
   }

   @Override
   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ClientServices.acquire(this.id()));
      RolesRegistry.install(this, var1);
      var1.own(RolesRegistry::uninstall);
      var1.own(ClientRegistry14.install(new ClientRegistry14.Listener() {
         @Override
         public class_2561 prefix() {
            return RolesRegistry.prefix();
         }

         @Override
         public boolean any() {
            return RolesRegistry.any();
         }

         @Override
         public class_2561 withRoles(class_2561 var1, String var2) {
            return RolesRegistry.withRoles(var1, var2);
         }

         @Override
         public class_2561 own(class_2561 var1) {
            return RolesRegistry.own(var1);
         }

         @Override
         public void decorate(class_1297 var1, class_10017 var2) {
            RolesRegistry.decorate(var1, var2);
         }

         @Override
         public class_2561 name(class_1297 var1, class_2561 var2) {
            return RolesRegistry.name(var1, var2);
         }

         @Override
         public class_2561 tab(class_640 var1, class_2561 var2) {
            return RolesRegistry.tab(var1, var2);
         }
      }));
      var1.own(ClientSupport2.chat(RolesSupport6::intercept));
      var1.own(ClientSupport2.click(RolesSupport::click));
   }

   @Override
   protected CosmeticCatalog.Listener createPreview() {
      return new RolesRenderer4(this);
   }

}
