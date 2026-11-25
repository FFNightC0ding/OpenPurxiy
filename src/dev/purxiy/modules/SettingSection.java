package dev.purxiy.modules;

import dev.purxiy.loader.fabric.MenuCommands;
import java.util.List;

public final class SettingSection {
   public static final MenuCommands.VisualModule HUD = MenuCommands.hud();
   public static final MenuCommands.ProfileModule PROFILE = (MenuCommands.ProfileModule)MenuCommands.byId("profile");
   public static final MenuCommands.SliderModule INTERFACE = (MenuCommands.SliderModule)MenuCommands.byId("interface");
   public static final MenuCommands.TriggerModule SOUND = (MenuCommands.TriggerModule)MenuCommands.byId("sound");
   public static final MenuCommands.ToggleModule MOTION = (MenuCommands.ToggleModule)MenuCommands.byId("motion");
   public static final MenuCommands.SimpleModule HOME = (MenuCommands.SimpleModule)MenuCommands.byId("main_menu");
   public static final Module NICK_HIDER = MenuCommands.byId("nick_hider");

   private SettingSection() {
   }

   public static List<Module> all() {
      return MenuCommands.all();
   }

   public static Module byId(String var0) {
      return MenuCommands.byId(var0);
   }

   public static List<Module> of(Category var0) {
      return all().stream().filter(var1 -> var1.category() == var0).toList();
   }

   public static List<Category> shownCategories() {
      return MenuCommands.categories();
   }

   public static boolean shown(Category var0) {
      return shownCategories().contains(var0);
   }
}
