// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import dev.purxiy.loader.api.HudLayer;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_408;

final class OwnerThread {
   private OwnerThread() {
   }

   static void install() {
      guard(HudLayer.SCOREBOARD, VanillaHudElements.SCOREBOARD);
      guard(HudLayer.TITLES, VanillaHudElements.TITLE_AND_SUBTITLE);
      guard(HudLayer.ACTION_BAR, VanillaHudElements.OVERLAY_MESSAGE);
      guard(HudLayer.BOSS_BARS, VanillaHudElements.BOSS_BAR);
      guard(HudLayer.PLAYER_LIST, VanillaHudElements.PLAYER_LIST);
      guard(HudLayer.HOTBAR, VanillaHudElements.HOTBAR, VanillaHudElements.SPECTATOR_MENU);
      guard(HudLayer.HEALTH, VanillaHudElements.HEALTH_BAR);
      guard(HudLayer.ARMOR, VanillaHudElements.ARMOR_BAR);
      guard(HudLayer.HUNGER, VanillaHudElements.FOOD_BAR);
      guard(HudLayer.AIR, VanillaHudElements.AIR_BAR);
      guard(HudLayer.MOUNT_HEALTH, VanillaHudElements.MOUNT_HEALTH);
      guard(HudLayer.INFO_BAR, VanillaHudElements.INFO_BAR, VanillaHudElements.EXPERIENCE_LEVEL);
      guard(HudLayer.ITEM_NAME, VanillaHudElements.HELD_ITEM_TOOLTIP, VanillaHudElements.SPECTATOR_TOOLTIP);
      guard(HudLayer.CROSSHAIR, VanillaHudElements.CROSSHAIR);
      guard(HudLayer.STATUS_EFFECTS, VanillaHudElements.STATUS_EFFECTS);
      guard(HudLayer.CHAT, VanillaHudElements.CHAT);
      guard(HudLayer.SOUND_SUBTITLES, VanillaHudElements.SUBTITLES);
   }

   private static void guard(HudLayer var0, class_2960... var1) {
      for (class_2960 var5 : var1) {
         HudElementRegistry.replaceElement(var5, var1x -> (var2, var3) -> {
            ModuleState var4 = ModuleState.active;
            class_310 var5x = class_310.method_1551();
            if (var4 == null || !var4.router.hidesHud(var0) || var0 == HudLayer.CHAT && var5x.field_1755 instanceof class_408) {
               if (!ModuleWidget.extractHud(var0)) {
                  var1x.render(var2, var3);
               }
            } else if (var0 == HudLayer.PLAYER_LIST) {
               var5x.field_1705.method_1750().method_1921(false);
            }
         });
      }
   }
}
