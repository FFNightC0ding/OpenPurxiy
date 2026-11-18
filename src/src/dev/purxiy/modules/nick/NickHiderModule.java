// Sourced By DevFFNightmare
package dev.purxiy.modules.nick;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.KeybindSetting;
import dev.purxiy.config.TextSetting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientRegistry5;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_310;

public final class NickHiderModule extends ModuleDescriptor {
   public final BooleanSetting g;
   public final TextSetting E;
   public final TextSetting R;
   public final BooleanSetting o;
   public final KeybindSetting S;
   public final BooleanSetting l;
   public final TextSetting A;
   public final BooleanSetting W;



   public NickHiderModule() {
      super("nick_hider", "Nick Hider", "Hide your name in screenshots and streams", "venetian-mask");
      this.section("You");
      this.g = this.bool("hide_own", "Hide your name", "Chat, tab, name tags, books, signs and more", true);
      this.E = this.add(
         new TextSetting("nickname", "Nickname", "Shown instead of your name · &d colours work", "Purxiy", 64).placeholder("Purxiy").colorCodes()
      );
      this.E.visibleWhen(this.g::enabled);
      this.R = this.add(new TextSetting("also_hide", "Also hide", "Server nicks, alts… separated by commas", "", 256).placeholder("Nick, alt, …").masked());
      this.R.visibleWhen(this.g::enabled);
      this.o = this.bool("hide_own_skin", "Hide your skin", "Wear a default skin, on your head items too", false);
      this.section("Other players");
      this.S = this.add(new KeybindSetting("custom_names", "Custom names", "Give chosen players a name, like Henk → Pieter"));
      this.l = this.bool("hide_others", "Hide other names", "Everyone else gets a numbered stand-in", false);
      this.A = this.add(new TextSetting("others_name", "Their name", "Numbered per player, like Player 3", "Player", 24).placeholder("Player").colorCodes());
      this.A.visibleWhen(this.l::enabled);
      this.W = this.bool("hide_others_skins", "Hide their skins", "Default skins for everyone else", false);
   }

   @Override
   protected void onEnable() {
      NickHiderCache.setEnabled(true);
   }

   @Override
   protected void onDisable() {
      NickHiderCache.setEnabled(false);
   }

   @Override
   protected void attach(ModuleContext var1) {
      NickHiderCache.install(this);
      var1.own(NickHiderCache::release);
      var1.own(ClientRegistry5.install(NickHiderCache.handler()));
      var1.own(ModuleWidget.onTick(NickHiderCache::tick));
      NickHiderCache.tick(class_310.method_1551());
   }

}
