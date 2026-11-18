// Sourced By DevFFNightmare
package dev.purxiy.modules.profile;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.TextSetting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientRegistry20;
import dev.purxiy.loader.fabric.client.ClientRegistry5;
import dev.purxiy.loader.fabric.client.ClientRegistry8;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_1297;
import net.minecraft.class_310;

public final class ProfileModule extends ModuleDescriptor {
   public static final String U = "Photo";
   public static final String f = "Skin";
   public final TextSetting m;
   public final ModeSetting v;
   public final BooleanSetting R;
   public final BooleanSetting o;
   public final BooleanSetting n;
   public final BooleanSetting I;
   public final BooleanSetting Z;



   public ProfileModule() {
      super("profile", "Profile", "What your profile keeps, and whether purxiy.com shows it", "circle-user-round");
      this.section("You");
      this.m = this.add(new TextSetting("name", "Display name", "Shown instead of your Minecraft name", "", 24).placeholder("Your Minecraft name"));
      this.v = this.mode("portrait", "Portrait", "Your own photo, or your skin as a 3D model", "Photo", new String[]{"Photo", "Skin"});
      this.section("Statistics");
      this.R = this.bool("track", "Keep statistics", "Play time, distance, blocks, fights and your records", true);
      this.n = this.bool("idle", "Count idle time", "Keep counting while you stand still for a while", false);
      this.section("Journey");
      this.o = this.bool("journey", "Record your route", "Draws the map of where you went in each session", true);
      this.section("Friends");
      this.I = this.bool("middle_click", "Middle-click friends", "Middle-click a player to add them as a friend, or to remove them", true);
      this.section("Website");
      this.Z = this.bool("sync", "Show on purxiy.com", "Your statistics in your dashboard on the website. Never your route or what you type", false);
   }

   public String displayName() {
      String var1 = this.m.get().trim();
      return var1.isEmpty() ? ClientRegistry5.plain(class_310.method_1551().method_1548().method_1676()) : var1;
   }

   public boolean photoPortrait() {
      return this.v.is("Photo");
   }

   @Override
   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ClientServices.acquire(this.id()));
      ProfileUpdater.install(this, var1);
      var1.own(ProfileUpdater::uninstall);
      var1.own(ClientRegistry20.install(new ClientRegistry20.Listener() {
         @Override
         public void onBlockBroken() {
            ProfileUpdater.onBlockBroken();
         }

         @Override
         public void onBlockPlaced() {
            ProfileUpdater.onBlockPlaced();
         }

         @Override
         public void onDamage(class_1297 var1, int var2) {
            ProfileUpdater.onDamage(var1, var2);
         }

         @Override
         public void onEntityEvent(class_1297 var1, byte var2) {
            ProfileUpdater.onEntityEvent(var1, var2);
         }

         @Override
         public void onDeath() {
            ProfileUpdater.onDeath();
         }

         @Override
         public void onChat() {
            ProfileUpdater.onChat();
         }
      }));
      var1.own(ModuleWidget.onTick(ProfileUpdater::tick));
      var1.own(ModuleWidget.onDisconnect(var0 -> ProfileUpdater.endSession()));
      var1.own(ClientRegistry8.install(ProfileUpdater::snapshotForUi));
   }

}
