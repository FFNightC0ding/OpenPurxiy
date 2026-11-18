// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ChoiceListSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.IntegerSetting;
import dev.purxiy.config.KeybindSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.PaletteSetting;
import dev.purxiy.config.Setting;
import dev.purxiy.config.TextSetting;
import dev.purxiy.hud.HudModule;
import dev.purxiy.hud.HudRegistry;
import dev.purxiy.loader.fabric.client.ClientRegistry5;
import dev.purxiy.modules.Category;
import dev.purxiy.modules.Module;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_310;

public final class MenuCommands {
   private static final Map<String, MenuCommands.TypeP> views = new LinkedHashMap<>();
   private static JsonArray source;
   private static boolean flushing;
   private static String savedGroups;

   private MenuCommands() {
   }

   public static boolean allowed() {
      return LoaderMessages.authorized && LoaderMessages.menuAllowed();
   }

   private static void ensure() {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Menu requires owner thread");
      } else {
         if (source != LoaderMessages.catalog) {
            LinkedHashMap var0 = new LinkedHashMap();

            for (JsonElement var2 : LoaderMessages.catalog) {
               JsonObject var3 = var2.getAsJsonObject();
               String var5 = var3.get("id").getAsString();

               var0.put(var3.get("id").getAsString(), switch (var5) {
                  case "hud" -> new MenuCommands.TypeV(var3);
                  case "profile" -> new MenuCommands.TypeJ(var3);
                  case "interface" -> new MenuCommands.TypeG(var3);
                  case "sound" -> new MenuCommands.TypeX(var3);
                  case "motion" -> new MenuCommands.TypeT(var3);
                  case "main_menu" -> new MenuCommands.TypeD(var3);
                  default -> new MenuCommands.TypeP(var3);
               });
            }

            views.clear();
            views.putAll(var0);
            source = LoaderMessages.catalog;
         }
      }
   }

   public static List<Module> all() {
      ensure();
      return List.copyOf(views.values());
   }

   public static void refresh() {
      ensure();

      for (MenuCommands.TypeP var1 : views.values()) {
         var1.refreshUnchanged();
      }
   }

   public static Module byId(String var0) {
      ensure();
      return views.get(var0);
   }

   public static MenuCommands.TypeV hud() {
      ensure();
      return (MenuCommands.TypeV)Objects.requireNonNull(views.get("hud"), "HUD metadata unavailable");
   }

   public static List<Category> categories() {
      ensure();
      return Arrays.stream(Category.values())
         .filter(var0 -> var0 == Category.PROFILE || var0 == Category.CONFIGS || views.values().stream().anyMatch(var1 -> var1.category() == var0))
         .toList();
   }

   public static void loadValue(Setting<?> var0, String var1) {
      if (var0 instanceof BooleanSetting) {
         if (!var1.equals("true") && !var1.equals("false")) {
            throw new IllegalArgumentException("Boolean format");
         }

         var0.load(new JsonPrimitive(Boolean.parseBoolean(var1)));
      } else if (var0 instanceof NumberSetting) {
         double var2 = Double.parseDouble(var1);
         if (!Double.isFinite(var2)) {
            throw new IllegalArgumentException("Number format");
         }

         var0.load(new JsonPrimitive(var2));
      } else if (var0 instanceof IntegerSetting) {
         var0.load(new JsonPrimitive(Integer.parseInt(var1)));
      } else if (!(var0 instanceof KeybindSetting) && !(var0 instanceof ChoiceListSetting) && !(var0 instanceof PaletteSetting)) {
         var0.load(new JsonPrimitive(var1));
      } else {
         var0.load(JsonParser.parseString(var1));
      }
   }

   private static String value(Setting<?> var0) {
      JsonElement var1 = var0.save();
      return var1.isJsonPrimitive() ? var1.getAsString() : var1.toString();
   }

   private static String configMode(ModeSetting var0, String var1) {
      if (var0.modes().contains(var1)) {
         return var1;
      } else {
         return (var1.equals("Lilac") || var1.equals("Purxiy")) && var0.modes().contains("Accent") ? "Accent" : null;
      }
   }

   public static void loadConfigValue(Module var0, Setting<?> var1, JsonElement var2) {
      if (var1 instanceof ModeSetting var3) {
         String var4 = configMode(var3, var2.getAsString());
         if (var4 != null) {
            var3.load(new JsonPrimitive(var4));
         }
      } else {
         var1.load(var2);
      }
   }

   public static boolean equivalent(String var0, String var1, JsonElement var2, JsonElement var3) {
      if (Objects.equals(var2, var3)) {
         return true;
      } else if (var2 != null && var3 != null) {
         MenuCommands.TypeP var4 = (MenuCommands.TypeP)byId(var0);
         if (var4 == null) {
            return false;
         } else {
            Setting var5 = var4.setting(var1);
            if (var5 == null) {
               return false;
            } else {
               try {
                  if (var5 instanceof ModeSetting var12) {
                     String var14 = configMode(var12, var2.getAsString());
                     return var14 == null || var14.equals(var3.getAsString());
                  } else if (var5 instanceof NumberSetting var11) {
                     double var13 = var2.getAsDouble();
                     if (!Double.isFinite(var13)) {
                        return false;
                     } else {
                        NumberSetting var9 = new NumberSetting(
                           var1, var11.name(), var11.description(), var13, var11.min(), var11.max(), var11.step(), NumberSetting.Listener.DECIMAL
                        );
                        return Math.abs(var9.get() - var3.getAsDouble()) < 1.0E-6;
                     }
                  } else if (var5 instanceof ColorSetting var6) {
                     ColorSetting var7 = new ColorSetting(var1, var6.name(), var6.description(), var6.get(), true).followsAccent();
                     var7.load(var2);
                     return var7.save().getAsString().equalsIgnoreCase(var3.getAsString());
                  } else {
                     return false;
                  }
               } catch (RuntimeException var10) {
                  return false;
               }
            }
         }
      } else {
         return false;
      }
   }

   private static ColorSetting color(JsonObject var0, String var1, String var2, String var3) {
      String var4 = var0.has("designColor") ? var0.get("designColor").getAsString() : var0.get("default").getAsString();
      int var5 = (int)Long.parseLong(var4.replace("#", ""), 16);
      ColorSetting var6 = new ColorSetting(var1, var2, var3, var5, var0.has("alpha") && var0.get("alpha").getAsBoolean());
      return var0.has("themed") && var0.get("themed").getAsBoolean() ? var6.followsAccent() : var6;
   }

   public static void flush() {
      if (!flushing && allowed()) {
         flushing = true;

         try {
            ensure();

            for (MenuCommands.TypeP var1 : views.values()) {
               var1.persist();
            }

            HudRegistry.persist();
         } catch (IOException var5) {
            LoaderMessages.status = "Instellingen opslaan is niet gelukt.";
         } finally {
            flushing = false;
         }
      }
   }

   public static void persistHud(HudModule var0) {
      ensure();
      String var1 = var0.settingsModule().id();
      MenuCommands.TypeP var2 = views.get(var1);
      if (var2 != null) {
         LinkedHashMap var3 = new LinkedHashMap();

         for (Setting var5 : var0.settingsModule().settings()) {
            if (var2.declared.contains(var5.id())) {
               var3.put(var5.id(), value(var5));
            }
         }

         if (!((java.util.Set<java.util.Map.Entry<?, ?>>)(Object)var3.entrySet()).stream().allMatch(var1x -> Objects.equals(var2.saved.get(var1x.getKey()), var1x.getValue()))) {
            try {
               CosmeticRegistry.mergeSettings(var1, var3, false);
               var2.refreshUnchanged();
            } catch (IOException var6) {
               throw new IllegalStateException("HUD settings could not be saved");
            }
         }
      }
   }

   private static Path groupFile() {
      return FabricLoader.getInstance().getConfigDir().resolve("purxiy/loader/hud-groups.json");
   }

   public static void loadGroups() {
      try {
         Path var0 = groupFile();
         if (Files.isRegularFile(var0) && Files.size(var0) < 65536L) {
            JsonObject var1 = JsonParser.parseString(Files.readString(var0)).getAsJsonObject();

            for (Setting var3 : hud().settings()) {
               if (var3.id().matches("group[1-8]_(?:size|background|opacity|x|y|placed|members|axis)") && var1.has(var3.id())) {
                  var3.load(var1.get(var3.id()));
               }
            }
         }
      } catch (IllegalArgumentException | IOException | JsonParseException var4) {
      }
   }

   public static void saveGroups(List<? extends HudModule> var0) {
      if (!var0.isEmpty()) {
         JsonObject var1 = new JsonObject();

         for (Setting var3 : hud().settings()) {
            if (var3.id().matches("group[1-8]_(?:size|background|opacity|x|y|placed|members|axis)")) {
               var1.add(var3.id(), var3.save());
            }
         }

         String var10 = var1.toString();
         if (!var10.equals(savedGroups)) {
            try {
               Path var11 = groupFile();
               Files.createDirectories(var11.getParent());
               Path var4 = Files.createTempFile(var11.getParent(), "groups-", ".tmp");

               try {
                  Files.writeString(var4, var10);
                  Files.move(var4, var11, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
                  savedGroups = var10;
               } finally {
                  Files.deleteIfExists(var4);
               }
            } catch (IOException var9) {
               throw new IllegalStateException("HUD groups could not be saved");
            }
         }
      }
   }

   public static final class TypeD extends MenuCommands.TypeP {
      public final Setting<?> forest = this.setting("forest");

      TypeD(JsonObject var1) {
         super(var1);
      }
   }

   public static final class TypeG extends MenuCommands.TypeP {
      public final NumberSetting scale = (NumberSetting)this.setting("scale");
      public final NumberSetting dim = (NumberSetting)this.setting("dim");
      public final ModeSetting language = (ModeSetting)this.setting("language");
      public final BooleanSetting blur = (BooleanSetting)this.setting("blur");
      public final BooleanSetting stars = (BooleanSetting)this.setting("stars");

      TypeG(JsonObject var1) {
         super(var1);
      }
   }

   public static final class TypeJ extends MenuCommands.TypeP {
      public static final String PHOTO = "Photo";
      public static final String SKIN = "Skin";
      public final TextSetting name = (TextSetting)this.setting("name");
      public final ModeSetting portrait = (ModeSetting)this.setting("portrait");
      public final BooleanSetting track = (BooleanSetting)this.setting("track");
      public final BooleanSetting journey = (BooleanSetting)this.setting("journey");
      public final BooleanSetting idle = (BooleanSetting)this.setting("idle");
      public final BooleanSetting middleClick = (BooleanSetting)this.setting("middle_click");
      public final BooleanSetting sync = (BooleanSetting)this.setting("sync");

      TypeJ(JsonObject var1) {
         super(var1);
      }

      public String displayName() {
         String var1 = this.name.get().strip();
         return var1.isEmpty() ? ClientRegistry5.plain(class_310.method_1551().method_1548().method_1676()) : var1;
      }

      public boolean photoPortrait() {
         return this.portrait.is("Photo");
      }
   }

   public static class TypeP extends Module {
      final JsonObject metadata;
      final Set<String> declared = new HashSet<>();
      final Map<String, String> saved = new LinkedHashMap<>();

      TypeP(JsonObject var1) {
         super(
            var1.get("id").getAsString(),
            var1.get("name").getAsString(),
            var1.has("description") ? var1.get("description").getAsString() : "",
            var1.has("icon") ? var1.get("icon").getAsString() : "sparkles",
            Category.valueOf(var1.get("category").getAsString()),
            var1.get("toggleable").getAsBoolean()
         );
         this.metadata = var1;
         Map var2 = SettingSchema.read(var1);

         for (JsonElement var4 : var1.getAsJsonArray("settingDefinitions")) {
            JsonObject var5 = var4.getAsJsonObject();
            String var6 = var5.get("id").getAsString();
            String var7 = var5.get("name").getAsString();
            String var8 = var5.has("description") ? var5.get("description").getAsString() : null;
            JsonElement var9 = var5.get("default");
            String var11 = var5.get("kind").getAsString();

            Setting var16 = (Setting)(
               switch (var11) {
                  case "BooleanSetting" -> new BooleanSetting(var6, var7, var8, var9.getAsBoolean());
                  case "NumberSetting" -> new NumberSetting(
                     var6,
                     var7,
                     var8,
                     var9.getAsDouble(),
                     var5.get("min").getAsDouble(),
                     var5.get("max").getAsDouble(),
                     var5.get("step").getAsDouble(),
                     var6.matches(".*(?:size|opacity|scale).*")
                        ? NumberSetting.Listener.PERCENT
                        : (var5.get("step").getAsDouble() == 1.0 ? NumberSetting.Listener.INTEGER : NumberSetting.Listener.DECIMAL)
                  );
                  case "ModeSetting" -> new ModeSetting(
                     var6, var7, var8, var9.getAsString(), var5.getAsJsonArray("choices").asList().stream().<String>map(JsonElement::getAsString).toList()
                  );
                  case "ColorSetting" -> MenuCommands.color(var5, var6, var7, var8);
                  case "TextSetting" -> new TextSetting(var6, var7, var8, var9.getAsString(), var5.has("maxLength") ? var5.get("maxLength").getAsInt() : 256);
                  case "KeySetting" -> new IntegerSetting(var6, var7, var8);
                  case "NameListSetting" -> new KeybindSetting(var6, var7, var8);
                  case "PlayerListSetting" -> new ChoiceListSetting(var6, var7, var8, "Player name");
                  case "SelectionSetting" -> new PaletteSetting(var6, var7, var8, "item", "items", "list", List.of());
                  default -> throw new IllegalArgumentException("Unsupported signed setting type");
               }
            );
            this.add(var16);
            this.declared.add(var6);
            if (var2.containsKey(var6)) {
               MenuCommands.loadValue(var16, (String)var2.get(var6));
            }

            this.saved.put(var6, MenuCommands.value(var16));
            var16.visibleWhen(() -> CosmeticCatalog.settingVisible(this.id(), var6));
            if (var6.equals("x") || var6.equals("y") || var6.equals("placed") || var6.matches(".*_(?:x|y|placed)") || var6.matches("group[1-8]_.*")) {
               var16.visibleWhen(() -> false);
            }
         }

         if (this.setting("opacity") != null && this.setting("background") instanceof BooleanSetting var14) {
            Setting var15 = this.setting("opacity");
            var15.visibleWhen(var14::enabled);
         }
      }

      @Override
      public boolean enabled() {
         return MenuCommands.allowed() && ModuleState.moduleActive(this.id());
      }

      @Override
      public boolean switchedOn() {
         return CosmeticRegistry.enabled(this.id());
      }

      @Override
      public void setEnabled(boolean var1) {
         if (MenuCommands.allowed()) {
            CosmeticRegistry.requestEnabled(this.id(), var1);
         }
      }

      @Override
      public void toggle() {
         this.setEnabled(!this.switchedOn());
      }

      @Override
      public int key() {
         return ModuleCatalog.key(this.metadata);
      }

      @Override
      public void setKey(int var1) {
         try {
            ModuleCatalog.key(this.metadata, var1);
         } catch (IOException var3) {
            throw new IllegalStateException("Key could not be saved");
         }
      }

      @Override
      public boolean keybindToggles() {
         return ModuleCatalog.keyToggles(this.metadata);
      }

      @Override
      public boolean onByDefault() {
         return this.metadata.has("onByDefault") && this.metadata.get("onByDefault").getAsBoolean();
      }

      void refreshUnchanged() {
         Map var1 = SettingSchema.read(this.metadata);

         for (Setting var3 : this.settings()) {
            if (this.declared.contains(var3.id()) && Objects.equals(this.saved.get(var3.id()), MenuCommands.value(var3))) {
               String var4 = (String)var1.get(var3.id());
               if (var4 != null) {
                  MenuCommands.loadValue(var3, var4);
                  this.saved.put(var3.id(), MenuCommands.value(var3));
               }
            }
         }
      }

      void persist() throws IOException {
         LinkedHashMap var1 = new LinkedHashMap();

         for (Setting var3 : this.settings()) {
            if (this.declared.contains(var3.id()) && !Objects.equals(this.saved.get(var3.id()), MenuCommands.value(var3))) {
               var1.put(var3.id(), MenuCommands.value(var3));
            }
         }

         if (!var1.isEmpty()) {
            boolean var4 = HudRegistry.applyValues(this.id(), var1);
            boolean var5 = this.id().equals("hud") && var1.keySet().stream().allMatch(var0 -> ((String)var0).matches("group[1-8]_.*"));
            CosmeticRegistry.mergeSettings(this.id(), var1, !var4 && !var5);
            this.saved.putAll(var1);
         }
      }
   }

   public static final class TypeT extends MenuCommands.TypeP {
      public final BooleanSetting reduce = (BooleanSetting)this.setting("reduce");

      TypeT(JsonObject var1) {
         super(var1);
      }
   }

   public static final class TypeV extends MenuCommands.TypeP {
      public final NumberSetting scale = (NumberSetting)this.setting("scale");
      public final BooleanSetting glass = (BooleanSetting)this.setting("glass");
      public final BooleanSetting chatEditor = (BooleanSetting)this.setting("chat_editor");
      public final BooleanSetting snap = (BooleanSetting)this.setting("snap");
      public final BooleanSetting noOverlap = (BooleanSetting)this.setting("no_overlap");

      TypeV(JsonObject var1) {
         super(var1);
      }

      @Override
      protected <S extends Setting<?>> S add(S var1) {
         Setting var2 = this.setting(var1.id());
         if (var2 != null) {
            if (var2.getClass() != var1.getClass()) {
               throw new IllegalArgumentException("HUD setting type differs");
            } else {
               return (S)var2;
            }
         } else {
            return super.add((S)var1);
         }
      }

      public <S extends Setting<?>> S internal(S var1) {
         Setting var2 = this.add(var1);
         var2.visibleWhen(() -> false);
         return (S)var2;
      }
   }

   public static final class TypeX extends MenuCommands.TypeP {
      public final BooleanSetting enabled = (BooleanSetting)this.setting("enabled");
      public final NumberSetting volume = (NumberSetting)this.setting("volume");

      TypeX(JsonObject var1) {
         super(var1);
      }
   }
}
