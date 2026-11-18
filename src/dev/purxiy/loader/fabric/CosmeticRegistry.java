// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import com.google.gson.JsonObject;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.ui.clickgui.MenuScreen;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.class_310;
import net.minecraft.class_437;

public final class CosmeticRegistry {
   private static final Map<String, CosmeticRegistry.TypeP> pages = new HashMap<>();
   private static long revision = 0L;
   private static String pendingOpen;
   private static int pendingTicks = 0;
   private static long pendingGeneration = -1L;
   private static class_437 pendingFrom;
   private static final Map<String, CosmeticRegistry.TypeN> changes = new LinkedHashMap<>();

   private CosmeticRegistry() {
   }

   public static class_437 menu() {
      check();
      MenuCommands.refresh();
      return new MenuScreen((class_437)null);
   }

   public static boolean hasEditor(String var0) {
      check();
      return pages.containsKey(var0);
   }

   public static boolean supportsEditor(String var0) {
      return pages.containsKey(var0)
         || Set.of(
               "skins_3d",
               "cape",
               "wings",
               "halo",
               "knives",
               "spotify_player",
               "custom_crosshair",
               "creamy_keys",
               "shockwave",
               "jump_circles",
               "aura",
               "hit_splash",
               "afterglow",
               "glass",
               "ripple",
               "hand_movement",
               "keybinds"
            )
            .contains(var0);
   }

   private static JsonObject entry(String var0) {
      check();
      return LoaderMessages.catalog
         .asList()
         .stream()
         .map(var0x -> var0x.getAsJsonObject())
         .filter(var1 -> var1.get("id").getAsString().equals(var0))
         .findFirst()
         .orElseThrow(() -> new IllegalArgumentException("Unknown module page settings"));
   }

   public static void settings(String var0) {
      entry(var0);
      MenuCommands.refresh();
      class_310.method_1551().method_1507(new MenuScreen(class_310.method_1551().field_1755, SettingSection.byId(var0)));
   }

   public static void storeSettings(String var0, Map<String, String> var1) throws IOException {
      JsonObject var2 = entry(var0);
      if (!var2.has("settingDefinitions")) {
         throw new IllegalArgumentException("Module settings absent");
      } else {
         Set var3 = var2.getAsJsonArray("settingDefinitions")
            .asList()
            .stream()
            .map(var0x -> var0x.getAsJsonObject().get("id").getAsString())
            .collect(Collectors.toSet());
         if (!var3.containsAll(var1.keySet())) {
            throw new IllegalArgumentException("Undeclared page setting");
         } else {
            SettingSchema.save(var2, Map.copyOf(var1));
         }
      }
   }

   public static Map<String, String> readSettings(String var0) {
      return Map.copyOf(SettingSchema.read(entry(var0)));
   }

   public static void mergeSettings(String var0, Map<String, String> var1, boolean var2) throws IOException {
      Map var3 = readSettings(var0);
      LinkedHashMap var4 = new LinkedHashMap(var3);
      var4.putAll(var1);
      if (!var4.equals(var3)) {
         storeSettings(var0, var4);
         if (var2) {
            CosmeticRegistry.TypeN var5 = changes.get(var0);
            changes.put(var0, new CosmeticRegistry.TypeN(var5 == null ? null : var5.enabled, true));
         }
      }
   }

   public static boolean enabled(String var0) {
      entry(var0);
      CosmeticRegistry.TypeN var1 = changes.get(var0);
      return var1 != null && var1.enabled != null ? var1.enabled : LoaderMessages.requested(var0);
   }

   public static void requestEnabled(String var0, boolean var1) {
      entry(var0);
      if (changes.size() >= 128 && !changes.containsKey(var0)) {
         throw new IllegalStateException("Pending module action limit");
      } else {
         CosmeticRegistry.TypeN var2 = changes.get(var0);
         changes.put(var0, new CosmeticRegistry.TypeN(var1, var2 != null && var2.reload));
      }
   }

   static void tickChanges() {
      if (pendingOpen != null) {
         if (!LoaderMessages.authorized || !LoaderMessages.menuAllowed() || class_310.method_1551().field_1755 != pendingFrom || ++pendingTicks > 600) {
            pendingOpen = null;
            pendingFrom = null;
         } else if (pages.containsKey(pendingOpen) && pages.get(pendingOpen).generation != pendingGeneration && !changes.containsKey(pendingOpen)) {
            String var0 = pendingOpen;
            pendingOpen = null;
            pendingFrom = null;
            pendingGeneration = -1L;
            open(var0);
         }
      }

      check();
      if (LoaderMessages.authorized && LoaderMessages.menuAllowed()) {
         ArrayList var8 = new ArrayList<>(changes.entrySet());
         var8.sort(Comparator.comparingInt((java.util.Map.Entry var0x) -> Boolean.FALSE.equals(((CosmeticRegistry.TypeN)var0x.getValue()).enabled) ? 0 : 1));

         for (Entry var2 : ((Iterable<Entry>)(Object)var8)) {
            JsonObject var3 = entry((String)var2.getKey());
            CosmeticRegistry.TypeN var4 = (CosmeticRegistry.TypeN)var2.getValue();
            boolean var5 = LoaderMessages.requested((String)var2.getKey());
            boolean var6 = Boolean.TRUE.equals(var4.enabled) && !var5;
            boolean var7 = var4.reload && var5 && !Boolean.FALSE.equals(var4.enabled);
            if (!var6 && !var7 || LoaderMessages.canLoad()) {
               changes.remove(var2.getKey(), var4);
               if (var4.enabled != null && var5 != var4.enabled) {
                  LoaderMessages.toggle(var3);
               } else if (var7) {
                  LoaderMessages.reconfigure(var3);
               }
            }
         }
      } else {
         changes.clear();
      }
   }

   public static long revision() {
      check();
      return revision;
   }

   public static boolean available(String var0) {
      check();
      return pages.containsKey(var0);
   }

   public static String currentOwner() {
      check();
      class_437 var0 = class_310.method_1551().field_1755;
      if (var0 == null) {
         return null;
      } else {
         ClassLoader var1 = var0.getClass().getClassLoader();

         for (Entry var3 : pages.entrySet()) {
            if (((CosmeticRegistry.TypeP)var3.getValue()).owner == var1) {
               return (String)var3.getKey();
            }
         }

         return null;
      }
   }

   public static void open(String var0) {
      check();
      if (MenuCommands.allowed()) {
         MenuCommands.flush();
         CosmeticRegistry.TypeP var1 = pages.get(var0);
         CosmeticRegistry.TypeN var2 = changes.get(var0);
         if (var1 == null || var2 != null && var2.reload) {
            if (supportsEditor(var0)) {
               pendingOpen = var0;
               pendingFrom = class_310.method_1551().field_1755;
               pendingTicks = 0;
               pendingGeneration = var1 == null ? -1L : var1.generation;
               requestEnabled(var0, true);
            }
         } else {
            UiAudioPolicy.play("OPEN");
            class_310.method_1551().method_1507(Objects.requireNonNull(var1.create.get()));
         }
      }
   }

   public static AutoCloseable register(String var0, ClassLoader var1, Supplier<class_437> var2) {
      check();
      if (!var0.matches("[a-z][a-z0-9_-]{0,63}")) {
         throw new IllegalArgumentException("Invalid page identity");
      } else {
         CosmeticRegistry.TypeP var3 = new CosmeticRegistry.TypeP(Objects.requireNonNull(var1), Objects.requireNonNull(var2), revision + 1L);
         if (pages.putIfAbsent(var0, var3) != null) {
            throw new IllegalStateException("Module page already owned");
         } else {
            revision++;
            return () -> {
               check();
               if (pages.remove(var0, var3)) {
                  revision++;
                  class_310 var3x = class_310.method_1551();
                  if (var3x.field_1755 != null && var3x.field_1755.getClass().getClassLoader() == var1) {
                     var3x.method_1507(menu());
                  }
               }
            };
         }
      }
   }

   private static void check() {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Module pages require owner thread");
      }
   }

   private record TypeN(Boolean enabled, boolean reload) {
   }

   private record TypeP(ClassLoader owner, Supplier<class_437> create, long generation) {
   }
}
