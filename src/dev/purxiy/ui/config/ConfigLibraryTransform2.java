// Sourced By DevFFNightmare
package dev.purxiy.ui.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import dev.purxiy.config.ChoiceListSetting;
import dev.purxiy.config.IntegerSetting;
import dev.purxiy.config.KeybindSetting;
import dev.purxiy.config.Setting;
import dev.purxiy.config.TextSetting;
import dev.purxiy.hud.HudRegistry;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.MenuCommands;
import dev.purxiy.modules.Category;
import dev.purxiy.modules.Module;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.persist.FCodec;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

public final class ConfigLibraryTransform2 {
   static final String KEY = "@key";

   private ConfigLibraryTransform2() {
   }

   static boolean carries(Module var0) {
      Category var1 = var0.category();
      return var0 != SettingSection.PROFILE && var1 != Category.PROFILE && var1 != Category.CONFIGS;
   }

   private static boolean carries(Module var0, Setting<?> var1) {
      return var1 != SettingSection.INTERFACE.language && var1 != SettingSection.HOME.forest;
   }

   private static boolean personal(Module var0, Setting<?> var1) {
      return var1 instanceof IntegerSetting
         ? true
         : (var1 instanceof TextSetting || var1 instanceof KeybindSetting || var1 instanceof ChoiceListSetting) && var0 != SettingSection.HUD;
   }

   static ConfigLibraryTransform2.TypeU capture() {
      MenuCommands.flush();
      MenuCommands.refresh();
      LinkedHashSet var0 = new LinkedHashSet();
      JsonObject var1 = new JsonObject();

      for (Module var3 : SettingSection.all()) {
         if (carries(var3)) {
            if (var3.switchedOn()) {
               var0.add(var3.id());
            }

            JsonObject var4 = new JsonObject();
            if (var3.toggleable()) {
               var4.addProperty("@key", var3.key());
            }

            for (Setting var6 : var3.settings()) {
               if (carries(var3, var6)) {
                  try {
                     var4.add(var6.id(), var6.save());
                  } catch (RuntimeException var8) {
                     ClientServices.LOGGER.warn("Could not keep {}.{} in a config", new Object[]{var3.id(), var6.id(), var8});
                  }
               }
            }

            if (!var4.isEmpty()) {
               var1.add(var3.id(), var4);
            }
         }
      }

      return new ConfigLibraryTransform2.TypeU(var0, var1);
   }

   static List<String> liveModules() {
      ArrayList var0 = new ArrayList();

      for (Module var2 : SettingSection.all()) {
         if (var2.switchedOn() && carries(var2)) {
            var0.add(var2.id());
         }
      }

      return var0;
   }

   static JsonObject carried(JsonObject var0) {
      JsonObject var1 = new JsonObject();

      for (Module var3 : SettingSection.all()) {
         if (carries(var3) && var0.get(var3.id()) instanceof JsonObject var5) {
            JsonObject var6 = new JsonObject();
            if (var3.toggleable() && var5.get("@key") instanceof JsonPrimitive var8) {
               var6.add("@key", var8);
            }

            for (Setting var11 : var3.settings()) {
               JsonElement var9 = var5.get(var11.id());
               if (var9 != null && carries(var3, var11)) {
                  var6.add(var11.id(), var9);
               }
            }

            if (!var6.isEmpty()) {
               var1.add(var3.id(), var6);
            }
         }
      }

      return var1;
   }

   
   static JsonObject shareable( JsonObject var0) {
      if (var0 == null) {
         return null;
      } else {
         JsonObject var1 = new JsonObject();

         for (Entry var3 : var0.entrySet()) {
            if (var3.getValue() instanceof JsonObject var5) {
               Module var6 = SettingSection.byId((String)var3.getKey());
               JsonObject var7 = new JsonObject();

               for (Entry var9 : var5.entrySet()) {
                  if (!((String)var9.getKey()).equals("@key")) {
                     Setting var10 = var6 == null ? null : var6.setting((String)var9.getKey());
                     if (var10 == null || !personal(var6, var10)) {
                        var7.add((String)var9.getKey(), ((JsonElement)var9.getValue()).deepCopy());
                     }
                  }
               }

               if (!var7.isEmpty()) {
                  var1.add((String)var3.getKey(), var7);
               }
            }
         }

         return var1;
      }
   }

   static List<String> carriedModules(List<String> var0) {
      ArrayList var1 = new ArrayList();

      for (String var3 : var0) {
         Module var4 = SettingSection.byId(var3);
         if (var4 == null || carries(var4)) {
            var1.add(var3);
         }
      }

      return var1;
   }

   static boolean matches(ConfigLibraryFactory2 var0, ConfigLibraryTransform2.TypeU var1) {
      return differences(var0, var1, true) == 0;
   }

   static int differences(ConfigLibraryFactory2 var0, ConfigLibraryTransform2.TypeU var1) {
      return differences(var0, var1, false);
   }

   private static int differences(ConfigLibraryFactory2 var0, ConfigLibraryTransform2.TypeU var1, boolean var2) {
      int var3 = 0;

      for (Module var5 : SettingSection.all()) {
         if (var5.toggleable() && carries(var5) && var0.modules.contains(var5.id()) != var1.modules().contains(var5.id())) {
            if (var2) {
               return 1;
            }

            var3++;
         }
      }

      if (var0.settings == null) {
         return var3;
      } else {
         for (Entry var14 : var0.settings.entrySet()) {
            if (var14.getValue() instanceof JsonObject var7 && var1.settings().get((String)var14.getKey()) instanceof JsonObject var9) {
               for (Entry var11 : var7.entrySet()) {
                  JsonElement var12 = var9.get((String)var11.getKey());
                  if (var12 != null && !MenuCommands.equivalent((String)var14.getKey(), (String)var11.getKey(), (JsonElement)var11.getValue(), var12)) {
                     if (var2) {
                        return 1;
                     }

                     var3++;
                  }
               }
            }
         }

         return var3;
      }
   }

   static void blank() {
      for (Module var1 : SettingSection.all()) {
         if (carries(var1)) {
            boolean var2 = var1.category() == Category.CLIENT;

            for (Setting var4 : var1.settings()) {
               if (carries(var1, var4) && !personal(var1, var4) && (!var2 || HudRegistry.layout(var4))) {
                  var4.reset();
               }
            }

            if (!var2 && var1.toggleable()) {
               try {
                  var1.setEnabled(var1.onByDefault());
               } catch (RuntimeException var5) {
                  ClientServices.LOGGER.error("Could not switch {} for a blank config", var1.id(), var5);
               }
            }
         }
      }

      FCodec.save();
   }

   static ConfigLibraryTransform2.TypeD changes( JsonObject var0, ConfigLibraryTransform2.TypeU var1) {
      if (var0 == null) {
         return ConfigLibraryTransform2.TypeD.NONE;
      } else {
         int var2 = 0;
         boolean var3 = false;
         boolean var4 = false;
         boolean var5 = false;

         for (Module var7 : SettingSection.all()) {
            if (carries(var7) && var0.get(var7.id()) instanceof JsonObject var9 && var1.settings().get(var7.id()) instanceof JsonObject var10) {
               JsonElement var15 = var9.get("@key");
               if (var7.toggleable() && var15 != null && !var15.equals(var10.get("@key"))) {
                  var5 = true;
               }

               for (Setting var12 : var7.settings()) {
                  JsonElement var13 = var9.get(var12.id());
                  if (var13 != null && carries(var7, var12) && !var13.equals(var10.get(var12.id()))) {
                     if (var12 instanceof IntegerSetting) {
                        var5 = true;
                     } else if (HudRegistry.layout(var12)) {
                        var3 = true;
                     } else if (var12 == SettingSection.INTERFACE.scale) {
                        var4 = true;
                     } else {
                        var2++;
                     }
                  }
               }
            }
         }

         return new ConfigLibraryTransform2.TypeD(var2, var3, var4, var5);
      }
   }

   static void apply(Set<String> var0,  JsonObject var1) {
      for (Module var3 : SettingSection.all()) {
         if (carries(var3)) {
            JsonObject var4;
            if (var1 != null && var1.get(var3.id()) instanceof JsonObject var6) {
               var4 = var6;
            } else {
               var4 = null;
            }

            JsonObject var12 = var4;
            if (var4 != null) {
               for (Setting var7 : var3.settings()) {
                  JsonElement var8 = var12.get(var7.id());
                  if (var8 != null && carries(var3, var7)) {
                     try {
                        MenuCommands.loadConfigValue(var3, var7, var8);
                     } catch (RuntimeException var11) {
                        ClientServices.LOGGER.warn("Ignoring {}.{} = {} from a config", new Object[]{var3.id(), var7.id(), var8});
                     }
                  }
               }

               if (var3.toggleable() && var12.get("@key") instanceof JsonPrimitive var15 && var15.isNumber()) {
                  var3.setKey(var15.getAsInt());
               }
            }

            if (var3.toggleable()) {
               try {
                  var3.setEnabled(var0.contains(var3.id()));
               } catch (RuntimeException var10) {
                  ClientServices.LOGGER.error("Could not switch {} for a config", var3.id(), var10);
               }
            }
         }
      }

      FCodec.save();
   }

   record TypeD(int settings, boolean hud, boolean menuSize, boolean keys) {
      static final ConfigLibraryTransform2.TypeD NONE = new ConfigLibraryTransform2.TypeD(0, false, false, false);

      boolean any() {
         return this.settings > 0 || this.hud || this.menuSize || this.keys;
      }
   }

   public record TypeU(Set<String> modules, JsonObject settings) {
   }
}
