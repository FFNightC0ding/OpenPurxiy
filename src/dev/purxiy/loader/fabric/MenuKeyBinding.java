package dev.purxiy.loader.fabric;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.class_11908;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_3675.class_307;

public final class MenuKeyBinding {
   private static class_304 menu;

   private MenuKeyBinding() {
   }

   static void menu(class_304 var0) {
      menu = Objects.requireNonNull(var0);
   }

   public static boolean menuMatches(class_11908 var0) {
      return menu != null && menu.method_1417(var0);
   }

   public static int menuKey() {
      return menu == null ? 344 : KeyBindingHelper.getBoundKeyOf(menu).method_1444();
   }

   public static void setMenuKey(int var0) {
      try {
         set("menu", var0);
      } catch (IOException var2) {
         throw new IllegalStateException("Menu key could not be saved", var2);
      }
   }

   public static int moduleKey(String var0, int var1) {
      check();

      for (JsonElement var3 : LoaderMessages.catalog) {
         JsonObject var4 = var3.getAsJsonObject();
         if (var4.get("id").getAsString().equals(var0)) {
            return ModuleCatalog.key(var4);
         }
      }

      return var1;
   }

   public static List<MenuKeyBinding.BindingRow> bound() {
      check();
      ArrayList var0 = new ArrayList();
      if (menu != null) {
         var0.add(new MenuKeyBinding.BindingRow("menu", "SourceByDevFFnightmare DeObfDeCompByGuideX menu", "Opens SourceByDevFFnightmare DeObfDeCompByGuideX anywhere", KeyBindingHelper.getBoundKeyOf(menu).method_1444(), false));
      }

      for (JsonElement var2 : LoaderMessages.catalog) {
         JsonObject var3 = var2.getAsJsonObject();
         String var4 = var3.get("id").getAsString();
         boolean var5 = ModuleState.active != null && ModuleState.active.runtime.isActive(var4);
         int var6 = ModuleCatalog.key(var3);
         if (ModuleCatalog.toggleable(var3) && var6 != -1 && (ModuleCatalog.keyToggles(var3) || var5)) {
            var0.add(
               new MenuKeyBinding.BindingRow(var4, LinkAccountScreen.name(var3), ModuleCatalog.keyToggles(var3) ? "Turns it on and off" : "Its action", var6, true)
            );
         }

         if (var5 && var3.has("settingDefinitions")) {
            Map var7 = SettingSchema.read(var3);

            for (JsonElement var9 : var3.getAsJsonArray("settingDefinitions")) {
               JsonObject var10 = var9.getAsJsonObject();
               if (var10.get("kind").getAsString().equals("KeySetting")) {
                  int var11 = Integer.parseInt((String)var7.get(var10.get("id").getAsString()));
                  if (var11 != -1) {
                     var0.add(
                        new MenuKeyBinding.BindingRow(
                           var4 + "/" + var10.get("id").getAsString(), LinkAccountScreen.name(var3), var10.get("name").getAsString(), var11, true
                        )
                     );
                  }
               }
            }
         }
      }

      return List.copyOf(var0);
   }

   public static void set(String var0, int var1) throws IOException {
      check();
      if (var1 == -1 || var1 >= 32 && var1 <= 348) {
         if (var0.equals("menu")) {
            if (menu == null || var1 == -1) {
               throw new IllegalArgumentException("The menu must keep a key");
            }

            menu.method_1422(class_307.field_1668.method_1447(var1));
            class_304.method_1426();
            class_310.method_1551().field_1690.method_1640();
         } else {
            String[] var2 = var0.split("/", -1);
            if (var2.length > 2) {
               throw new IllegalArgumentException("Invalid binding identity");
            }

            JsonObject var3 = LoaderMessages.catalog
               .asList()
               .stream()
               .map(var0x -> var0x.getAsJsonObject())
               .filter(var1x -> var1x.get("id").getAsString().equals(var2[0]))
               .findFirst()
               .orElseThrow(() -> new IllegalArgumentException("Unknown module"));
            if (var2.length == 1) {
               if (!ModuleCatalog.toggleable(var3)) {
                  throw new IllegalArgumentException("This module has no toggle key");
               }

               ModuleCatalog.key(var3, var1);
            } else {
               if (!var3.has("settingDefinitions")
                  || var3.getAsJsonArray("settingDefinitions")
                     .asList()
                     .stream()
                     .map(var0x -> var0x.getAsJsonObject())
                     .noneMatch(var1x -> var1x.get("id").getAsString().equals(var2[1]) && var1x.get("kind").getAsString().equals("KeySetting"))) {
                  throw new IllegalArgumentException("Unknown action key");
               }

               Map var4 = SettingSchema.read(var3);
               var4.put(var2[1], Integer.toString(var1));
               SettingSchema.save(var3, var4);
               LoaderMessages.reconfigure(var3);
            }
         }
      } else {
         throw new IllegalArgumentException("Invalid keyboard binding");
      }
   }

   public static String keyName(int var0) {
      return var0 == -1 ? "None" : class_307.field_1668.method_1447(var0).method_27445().getString();
   }

   private static void check() {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Bindings require owner thread");
      }
   }

   public record BindingRow(String id, String title, String action, int key, boolean removable) {
   }
}
