// Sourced By DevFFNightmare
package dev.purxiy.ui.config;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.modules.SettingSection;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_310;

public final class ConfigLibraryTransform {
   public static final int SHARE_SLOTS = 5;
   static final String CODE = "purxiy-config:";
   private static final int MAX_NAME = 32;
   private static final int MAX_DESCRIPTION = 140;
   static final Pattern ONLINE_CODE = Pattern.compile("(?i)^\\s*(PRX-[0-9A-Z]{4}-[0-9A-Z]{4})\\s*$");
   private static final long UNDO_MS = 7000L;
   private static final List<ConfigLibraryFactory2> MINE = new ArrayList<>();
   private static final List<ConfigLibraryFactory2> SHARED = new ArrayList<>();
   private static final List<String> UNPUBLISH = new ArrayList<>();
   private static final Map<String, Long> UNPUBLISH_AT = new HashMap<>();
   private static final Set<String> GONE = new HashSet<>();
   
   private static ConfigLibraryFactory2 active;
   private static boolean loaded;
   static boolean dry;
   private static String dryClipboard = "";
   private static int unsaved = 0;
   private static long changedCheckedAt = 0L;

   private ConfigLibraryTransform() {
   }

   private static void load() {
      if (!loaded && !SettingSection.all().isEmpty()) {
         loaded = true;
         ConfigLibraryLoader.TypeQ var0 = ConfigLibraryLoader.load();
         MINE.addAll(var0.configs());

         for (String var2 : var0.shared()) {
            ConfigLibraryFactory2 var3 = find(var2);
            if (var3 != null && !SHARED.contains(var3) && SHARED.size() < 5) {
               SHARED.add(var3);
            }
         }

         for (ConfigLibraryFactory2 var6 : MINE) {
            if (var6.code != null && !SHARED.contains(var6)) {
               UNPUBLISH.add(var6.code);
               var6.code = null;
            }
         }

         for (String var7 : var0.unpublish()) {
            if (!UNPUBLISH.contains(var7)) {
               UNPUBLISH.add(var7);
            }
         }

         active = find(var0.active());
         ConfigLibraryRenderer13.tidy(MINE);
         ConfigLibraryUpdater.kick();
      }
   }

   
   private static ConfigLibraryFactory2 find( String var0) {
      if (var0 == null) {
         return null;
      } else {
         for (ConfigLibraryFactory2 var2 : MINE) {
            if (var2.id.equals(var0)) {
               return var2;
            }
         }

         return null;
      }
   }

   static void persist() {
      if (loaded) {
         ConfigLibraryLoader.save(MINE, SHARED, active, UNPUBLISH);
      }
   }

   static String you() {
      try {
         String var0 = SettingSection.PROFILE.displayName().strip();
         if (!var0.isEmpty()) {
            return var0.length() > 24 ? var0.substring(0, 24) : var0;
         }
      } catch (RuntimeException var1) {
      }

      return class_310.method_1551().method_1548().method_1676();
   }

   static List<String> liveModules() {
      return ConfigLibraryTransform2.liveModules();
   }

   public static List<ConfigLibraryFactory2> mine() {
      load();
      return Collections.unmodifiableList(MINE);
   }

   public static List<ConfigLibraryFactory2> shared() {
      load();
      return Collections.unmodifiableList(SHARED);
   }

   public static List<ConfigLibraryFactory2> community() {
      return ConfigLibraryUpdater.community();
   }

   public static boolean isShared(ConfigLibraryFactory2 var0) {
      return SHARED.contains(var0);
   }

   
   public static ConfigLibraryFactory2 active() {
      load();
      return active;
   }

   public static boolean isActive(ConfigLibraryFactory2 var0) {
      return active == var0;
   }

   public static void liveSaved() {
      changedCheckedAt = 0L;
   }

   static boolean changedSinceSaved() {
      return unsavedChanges() > 0;
   }

   static int unsavedChanges() {
      load();
      if (active == null) {
         return 0;
      } else {
         long var0 = System.currentTimeMillis();
         if (var0 - changedCheckedAt > 400L) {
            changedCheckedAt = var0;
            unsaved = ConfigLibraryTransform2.differences(active, ConfigLibraryTransform2.capture());
         }

         return unsaved;
      }
   }

   static void update(ConfigLibraryFactory2 var0) {
      load();
      take(var0, ConfigLibraryTransform2.capture());
      active = var0;
      changedCheckedAt = 0L;
      changed(var0);
   }

   
   static ConfigLibraryTransform.TypeK revert() {
      load();
      return active == null ? null : apply(active);
   }

   private static void take(ConfigLibraryFactory2 var0, ConfigLibraryTransform2.TypeU var1) {
      if (!var0.modules.equals(var1.modules())) {
         var0.modules.clear();
         var0.modules.addAll(var1.modules());
         var0.modulesChanged();
      }

      var0.settings = var1.settings();
   }

   static String newName() {
      load();
      return unique(Translations.tr("My config"));
   }

   static ConfigLibraryTransform.TypeC create(String var0, boolean var1) {
      load();
      ConfigLibraryTransform.TypeK var2 = new ConfigLibraryTransform.TypeK(ConfigLibraryTransform2.capture(), active);
      if (var1) {
         ConfigLibraryTransform2.blank();
      }

      ConfigLibraryTransform2.TypeU var3 = ConfigLibraryTransform2.capture();
      String var4 = clean(var0);
      ConfigLibraryFactory2 var5 = ConfigLibraryFactory2.create(
         unique(var4.isEmpty() ? Translations.tr("My config") : var4), you(), "", var3.modules(), ConfigLibraryFactory2.TypeT.SAVED
      );
      var5.settings = var3.settings();
      MINE.addFirst(var5);
      active = var5;
      changedCheckedAt = 0L;
      persist();
      return new ConfigLibraryTransform.TypeC(var5, var2);
   }

   static void uncreate(ConfigLibraryTransform.TypeC var0) {
      MINE.remove(var0.entry());
      undo(var0.undo());
   }

   static ConfigLibraryTransform.TypeK apply(ConfigLibraryFactory2 var0) {
      load();
      ConfigLibraryTransform.TypeK var1 = new ConfigLibraryTransform.TypeK(ConfigLibraryTransform2.capture(), active);
      ConfigLibraryTransform2.apply(var0.modules, var0.settings);
      active = var0;
      changedCheckedAt = 0L;
      persist();
      return var1;
   }

   static void undo(ConfigLibraryTransform.TypeK var0) {
      ConfigLibraryTransform2.apply(var0.before().modules(), var0.before().settings());
      active = var0.active() != null && MINE.contains(var0.active()) ? var0.active() : null;
      changedCheckedAt = 0L;
      persist();
   }

   static void rename(ConfigLibraryFactory2 var0, String var1) {
      String var2 = clean(var1);
      if (!var2.isEmpty() && !var2.equals(var0.name)) {
         var0.name = var2;
         changed(var0);
      }
   }

   static void changed(ConfigLibraryFactory2 var0) {
      var0.edited = System.currentTimeMillis();
      persist();
      ConfigLibraryUpdater.kick();
   }

   static ConfigLibraryFactory2 duplicate(ConfigLibraryFactory2 var0) {
      ConfigLibraryFactory2 var1 = new ConfigLibraryFactory2(
         UUID.randomUUID().toString(),
         unique(var0.name + " copy"),
         var0.author,
         var0.description,
         var0.modules,
         var0.tags,
         ConfigLibraryFactory2.TypeT.SAVED,
         System.currentTimeMillis(),
         var0.hue
      );
      var1.settings = var0.settings == null ? null : var0.settings.deepCopy();
      var1.photo = var0.photo;
      int var2 = MINE.indexOf(var0);
      MINE.add(var2 < 0 ? 0 : var2 + 1, var1);
      persist();
      return var1;
   }

   static ConfigLibraryTransform.TypeX delete(ConfigLibraryFactory2 var0) {
      ConfigLibraryTransform.TypeX var1 = new ConfigLibraryTransform.TypeX(var0, MINE.indexOf(var0), SHARED.indexOf(var0), active == var0);
      MINE.remove(var0);
      if (SHARED.remove(var0)) {
         offline(var0);
      }

      if (active == var0) {
         active = null;
      }

      persist();
      return var1;
   }

   static void restore(ConfigLibraryTransform.TypeX var0) {
      ConfigLibraryFactory2 var1 = var0.entry();
      if (!MINE.contains(var1)) {
         MINE.add(Math.max(0, Math.min(MINE.size(), var0.index())), var1);
         if (var0.slot() >= 0 && SHARED.size() < 5) {
            SHARED.add(Math.min(SHARED.size(), var0.slot()), var1);
            online(var1);
         }

         if (var0.wasActive()) {
            active = var1;
         }

         persist();
         ConfigLibraryUpdater.kick();
      }
   }

   static boolean share(ConfigLibraryFactory2 var0) {
      if (SHARED.contains(var0)) {
         return true;
      } else if (SHARED.size() >= 5) {
         return false;
      } else {
         SHARED.add(var0);
         online(var0);
         persist();
         ConfigLibraryUpdater.kick();
         return true;
      }
   }

   static void unshare(ConfigLibraryFactory2 var0) {
      if (SHARED.remove(var0)) {
         offline(var0);
         persist();
         ConfigLibraryUpdater.kick();
      }
   }

   private static void offline(ConfigLibraryFactory2 var0) {
      if (var0.code != null && !UNPUBLISH.contains(var0.code)) {
         UNPUBLISH.add(var0.code);
         UNPUBLISH_AT.put(var0.code, System.currentTimeMillis() + 7000L);
      }
   }

   private static void online(ConfigLibraryFactory2 var0) {
      if (var0.code != null && UNPUBLISH.remove(var0.code)) {
         UNPUBLISH_AT.remove(var0.code);
      } else if (var0.code != null && GONE.contains(var0.code)) {
         var0.code = null;
      }
   }

   static void forget(String var0) {
      if (!UNPUBLISH.contains(var0)) {
         UNPUBLISH.add(var0);
         persist();
      }
   }

   
   static String nextUnpublish() {
      long var0 = System.currentTimeMillis();

      for (String var3 : UNPUBLISH) {
         if (UNPUBLISH_AT.getOrDefault(var3, 0L) <= var0) {
            return var3;
         }
      }

      return null;
   }

   static long unpublishDue() {
      long var0 = Long.MAX_VALUE;

      for (String var3 : UNPUBLISH) {
         var0 = Math.min(var0, UNPUBLISH_AT.getOrDefault(var3, 0L));
      }

      return var0;
   }

   static void unpublished(String var0) {
      UNPUBLISH.remove(var0);
      UNPUBLISH_AT.remove(var0);
      GONE.add(var0);

      for (ConfigLibraryFactory2 var2 : MINE) {
         if (var0.equals(var2.code) && !SHARED.contains(var2)) {
            var2.code = null;
         }
      }

      persist();
   }

   static void lost(ConfigLibraryFactory2 var0) {
      SHARED.remove(var0);
      var0.code = null;
      var0.synced = 0L;
      var0.syncedPhoto = null;
      persist();
   }

   static ConfigLibraryFactory2 adopt(ConfigLibraryFactory2 var0, ConfigLibraryFactory2.TypeT var1) {
      load();
      ConfigLibraryFactory2 var2 = new ConfigLibraryFactory2(
         UUID.randomUUID().toString(), unique(var0.name), var0.author, var0.description, var0.modules, var0.tags, var1, System.currentTimeMillis(), var0.hue
      );
      var2.settings = ConfigLibraryTransform2.shareable(var0.settings);
      var2.origin = var0.code;
      MINE.addFirst(var2);
      persist();
      if (var0.photo != null) {
         ConfigLibraryRenderer13.adopt(var0, var2);
      }

      return var2;
   }

   static boolean addedFrom(String var0) {
      for (ConfigLibraryFactory2 var2 : MINE) {
         if (var0.equals(var2.origin)) {
            return true;
         }
      }

      return false;
   }

   static boolean matches(ConfigLibraryFactory2 var0, String var1) {
      if (var1.isBlank()) {
         return true;
      } else {
         String var2 = var1.trim().toLowerCase(Locale.ROOT);
         if (!var0.name.toLowerCase(Locale.ROOT).contains(var2) && !var0.author.toLowerCase(Locale.ROOT).contains(var2)) {
            if (var0.code != null && var0.code.toLowerCase(Locale.ROOT).contains(var2)) {
               return true;
            } else {
               for (String var6 : var0.tags) {
                  if (var6.toLowerCase(Locale.ROOT).contains(var2)) {
                     return true;
                  }
               }

               return false;
            }
         } else {
            return true;
         }
      }
   }

   static void photo(ConfigLibraryFactory2 var0,  String var1) {
      if (!Objects.equals(var0.photo, var1)) {
         var0.photo = var1;
         changed(var0);
      }
   }

   static void copy(ConfigLibraryFactory2 var0) {
      toClipboard(encode(var0));
   }

   static void toClipboard(String var0) {
      if (dry) {
         dryClipboard = var0;
      } else {
         class_310.method_1551().field_1774.method_1455(var0);
      }
   }

   static String clipboard() {
      return dry ? dryClipboard : class_310.method_1551().field_1774.method_1460();
   }

   
   static ConfigLibraryFactory2 read( String var0) {
      load();
      return decode(var0, ConfigLibraryFactory2.TypeT.PASTED, Translations.tr("Imported config"));
   }

   static ConfigLibraryFactory2 add(ConfigLibraryFactory2 var0) {
      load();
      if (!MINE.contains(var0)) {
         var0.name = unique(var0.name);
         MINE.addFirst(var0);
         persist();
      }

      return var0;
   }

   
   static ConfigLibraryFactory2 paste(String var0) {
      ConfigLibraryFactory2 var1 = read(var0);
      return var1 == null ? null : add(var1);
   }

   
   static String onlineCode( String var0) {
      if (var0 == null) {
         return null;
      } else {
         Matcher var1 = ONLINE_CODE.matcher(var0);
         return var1.matches() ? var1.group(1).toUpperCase(Locale.ROOT) : null;
      }
   }

   static void dryClipboard(String var0) {
      dry = true;
      dryClipboard = var0;
   }

   
   static Path export(ConfigLibraryFactory2 var0) {
      try {
         Path var1 = folder();
         Files.createDirectories(var1);
         String var2 = var0.name.replaceAll("[\\\\/:*?\"<>|]", "").strip();
         Path var3 = var1.resolve((var2.isEmpty() ? "config" : var2) + ".json");
         JsonObject var4 = body(var0);
         byte[] var5 = ConfigLibraryRenderer13.read(var0);
         if (var5 != null) {
            var4.addProperty("photo", Base64.getEncoder().encodeToString(var5));
         }

         var4.addProperty("code", encode(var0));
         Files.writeString(var3, new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(var4), StandardCharsets.UTF_8);
         return var3;
      } catch (Exception var6) {
         ClientServices.LOGGER.warn("Could not export config {}", var0.name, var6);
         return null;
      }
   }

   static JsonObject body(ConfigLibraryFactory2 var0) {
      JsonObject var1 = new JsonObject();
      var1.addProperty("v", 2);
      var1.addProperty("name", var0.name);
      var1.addProperty("author", var0.author);
      var1.addProperty("description", var0.description);
      var1.addProperty("hue", var0.hue);
      JsonArray var2 = new JsonArray();
      Set var3 = var0.modules;
      var3.forEach(v -> var2.add((Boolean)v));
      var1.add("modules", var2);
      if (var0.settings != null) {
         var1.add("settings", ConfigLibraryTransform2.shareable(var0.settings));
      }

      return var1;
   }

   static String encode(ConfigLibraryFactory2 var0) {
      byte[] var1 = body(var0).toString().getBytes(StandardCharsets.UTF_8);
      Deflater var2 = new Deflater(9);
      var2.setInput(var1);
      var2.finish();
      ByteArrayOutputStream var3 = new ByteArrayOutputStream();
      byte[] var4 = new byte[1024];

      while (!var2.finished()) {
         var3.write(var4, 0, var2.deflate(var4));
      }

      var2.end();
      return "purxiy-config:" + Base64.getUrlEncoder().withoutPadding().encodeToString(var3.toByteArray());
   }

   
   static ConfigLibraryFactory2 decode( String var0, ConfigLibraryFactory2.TypeT var1, String var2) {
      if (var0 == null) {
         return null;
      } else {
         var0 = var0.strip();

         try {
            JsonObject var3;
            if (var0.startsWith("purxiy-config:")) {
               byte[] var4 = Base64.getUrlDecoder().decode(var0.substring("purxiy-config:".length()).strip());
               Inflater var5 = new Inflater();
               var5.setInput(var4);
               ByteArrayOutputStream var6 = new ByteArrayOutputStream();
               byte[] var7 = new byte[4096];

               while (!var5.finished() && var6.size() < 4194304) {
                  int var8 = var5.inflate(var7);
                  if (var8 == 0 && (var5.needsInput() || var5.needsDictionary())) {
                     break;
                  }

                  var6.write(var7, 0, var8);
               }

               var5.end();
               var3 = JsonParser.parseString(var6.toString(StandardCharsets.UTF_8)).getAsJsonObject();
            } else {
               if (!var0.startsWith("{")) {
                  return null;
               }

               var3 = JsonParser.parseString(var0).getAsJsonObject();
            }

            ArrayList var16 = new ArrayList();
            JsonObject var18;
            if (var3.get("settings") instanceof JsonObject var19) {
               var18 = var19;
            } else {
               var18 = null;
            }

            JsonObject var20 = var18;
            JsonElement var22 = var3.get("modules");
            if (var22 != null && var22.isJsonArray()) {
               for (JsonElement var25 : var22.getAsJsonArray()) {
                  var16.add(var25.getAsString());
               }
            } else {
               if (var22 == null || !var22.isJsonObject()) {
                  return null;
               }

               var20 = new JsonObject();

               for (Entry var10 : var22.getAsJsonObject().entrySet()) {
                  if (((JsonElement)var10.getValue()).isJsonObject()) {
                     JsonObject var11 = ((JsonElement)var10.getValue()).getAsJsonObject();
                     if (var11.has("enabled") && var11.get("enabled").getAsBoolean()) {
                        var16.add((String)var10.getKey());
                     }

                     if (var11.get("settings") instanceof JsonObject var13) {
                        var20.add((String)var10.getKey(), var13);
                     }
                  }
               }

               var20 = ConfigLibraryTransform2.carried(var20);
            }

            List var24 = ConfigLibraryTransform2.carriedModules(var16);
            String var26 = clean(var3.has("name") ? var3.get("name").getAsString() : var2);
            String var27 = var3.has("author") ? clean(var3.get("author").getAsString()) : you();
            String var28 = var3.has("description") ? var3.get("description").getAsString().strip() : "";
            ConfigLibraryFactory2 var29 = ConfigLibraryFactory2.create(
               var26.isEmpty() ? clean(var2) : var26, var27.isEmpty() ? you() : var27, var28.length() > 140 ? var28.substring(0, 140) : var28, var24, var1
            );
            if (var3.has("hue")) {
               var29.hue = Math.max(0.0F, Math.min(1.0F, var3.get("hue").getAsFloat()));
            }

            var29.settings = ConfigLibraryTransform2.shareable(var20);
            return var29;
         } catch (RuntimeException | DataFormatException var14) {
            return null;
         }
      }
   }

   static List<ConfigLibraryFactory2> importFiles(List<Path> var0) {
      load();
      ArrayList var1 = new ArrayList();

      for (Path var3 : var0) {
         try {
            if (Files.isRegularFile(var3) && Files.size(var3) <= 8388608L) {
               String var4 = var3.getFileName().toString();
               String var5 = var4.contains(".") ? var4.substring(0, var4.lastIndexOf(46)) : var4;
               if (var5.equalsIgnoreCase("purxiy")) {
                  var5 = Translations.tr("Imported setup");
               }

               String var6 = Files.readString(var3, StandardCharsets.UTF_8);
               ConfigLibraryFactory2 var7 = decode(var6, ConfigLibraryFactory2.TypeT.FILE, var5);
               if (var7 != null) {
                  var7.name = unique(var7.name.isEmpty() ? clean(var5) : var7.name);
                  MINE.addFirst(var7);
                  var1.add(var7);
                  if (var6.startsWith("{")) {
                     JsonElement var8 = JsonParser.parseString(var6).getAsJsonObject().get("photo");
                     if (var8 instanceof JsonElement && var8.isJsonPrimitive()) {
                        ConfigLibraryRenderer13.importBytes(var7, Base64.getDecoder().decode(var8.getAsString()), (var1x, var2) -> {
                           if (var1x != null) {
                              var7.photo = var1x;
                              persist();
                           }
                        });
                     }
                  }
               }
            }
         } catch (Exception var9) {
            ClientServices.LOGGER.warn("Could not read dropped config {}", var3, var9);
         }
      }

      if (!var1.isEmpty()) {
         persist();
      }

      return var1;
   }

   static Path folder() {
      return FabricLoader.getInstance().getConfigDir().resolve("purxiy").resolve("configs");
   }

   static String clean(String var0) {
      String var1 = var0.replaceAll("[\\p{Cntrl}]", "").strip();
      return var1.length() > 32 ? var1.substring(0, 32).strip() : var1;
   }

   private static String unique(String var0) {
      String var1 = clean(var0);
      if (!taken(var1)) {
         return var1;
      } else {
         Matcher var2 = Pattern.compile("^(.*\\S) (\\d{1,4})$").matcher(var1);
         int var3 = 2;
         if (var2.matches()) {
            var1 = var2.group(1);
            var3 = Integer.parseInt(var2.group(2)) + 1;
         }

         while (taken(var1 + " " + var3)) {
            var3++;
         }

         return var1 + " " + var3;
      }
   }

   private static boolean taken(String var0) {
      for (ConfigLibraryFactory2 var2 : MINE) {
         if (var2.name.equalsIgnoreCase(var0)) {
            return true;
         }
      }

      return false;
   }

   static void clear() {
      load();

      for (ConfigLibraryFactory2 var1 : new ArrayList<>(SHARED)) {
         unshare(var1);
      }

      MINE.clear();
      active = null;
      persist();
   }

   record TypeC(ConfigLibraryFactory2 entry, ConfigLibraryTransform.TypeK undo) {
   }

   record TypeK(ConfigLibraryTransform2.TypeU before,  ConfigLibraryFactory2 active) {
   }

   record TypeX(ConfigLibraryFactory2 entry, int index, int slot, boolean wasActive) {
   }
}
