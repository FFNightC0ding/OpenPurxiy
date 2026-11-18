// Sourced By DevFFNightmare
package dev.purxiy.modules;

import dev.purxiy.loader.fabric.MenuCommands;
import java.util.List;

public final class SettingSection {
   public static final MenuCommands.TypeV HUD = MenuCommands.hud();
   public static final MenuCommands.TypeJ PROFILE = (MenuCommands.TypeJ)MenuCommands.byId("profile");
   public static final MenuCommands.TypeG INTERFACE = (MenuCommands.TypeG)MenuCommands.byId("interface");
   public static final MenuCommands.TypeX SOUND = (MenuCommands.TypeX)MenuCommands.byId("sound");
   public static final MenuCommands.TypeT MOTION = (MenuCommands.TypeT)MenuCommands.byId("motion");
   public static final MenuCommands.TypeD HOME = (MenuCommands.TypeD)MenuCommands.byId("main_menu");
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
