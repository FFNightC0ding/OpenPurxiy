package dev.purxiy.ui.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.purxiy.i18n.Translations;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_310;

final class ConfigLibraryError {
   private static final Pattern CODE = Pattern.compile("^\\s*(\\d{5})\\s*$");
   private static final Map<String, ConfigLibraryError.LibraryError> MADE = new HashMap<>();

   private ConfigLibraryError() {
   }


   static String code( String var0) {
      if (var0 == null) {
         return null;
      } else {
         Matcher var1 = CODE.matcher(var0);
         return var1.matches() ? var1.group(1) : null;
      }
   }


   static ConfigLibraryError.LibraryError known(ConfigLibraryStats var0) {
      ConfigLibraryError.LibraryError var1 = MADE.get(var0.id);
      return var1 != null && var1.edited() == var0.edited && var1.expiresAt() > System.currentTimeMillis() + 60000L ? var1 : null;
   }

   static void create(ConfigLibraryStats var0, BiConsumer<ConfigLibraryError.LibraryError, String> var1) {
      long var2 = var0.edited;
      JsonObject var4 = ConfigLibraryTransform.body(var0);
      var4.remove("v");
      JsonArray var5 = new JsonArray();

      for (String var7 : ConfigLibraryFactory.of(var0.modules)) {
         var5.add(var7);
      }

      var4.add("tags", var5);
      if (!var4.has("settings")) {
         var4.add("settings", new JsonObject());
      }

      ConfigLibraryHost.call("POST", "/api/share", var4, ConfigLibraryLoader.token()).whenComplete((var4x, var5x) -> main(() -> {
         if (var5x != null) {
            var1.accept(null, problem(var5x));
         } else {
            try {
               ConfigLibraryError.LibraryError var6 = new ConfigLibraryError.LibraryError(var4x.get("code").getAsString(), var4x.get("expiresAt").getAsLong(), var2);
               MADE.put(var0.id, var6);
               var1.accept(var6, null);
            } catch (RuntimeException var7x) {
               var1.accept(null, "error");
            }
         }
      }));
   }

   static void fetch(String var0, BiConsumer<ConfigLibraryStats, String> var1) {
      ConfigLibraryHost.call("GET", "/api/share/" + var0, (JsonObject)null, (String)null).whenComplete((var1x, var2) -> main(() -> {
         if (var2 != null) {
            var1.accept(null, problem(var2));
         } else {
            ConfigLibraryStats var4;
            if (var1x.get("config") instanceof JsonObject var5) {
               var4 = ConfigLibraryTransform.decode(var5.toString(), ConfigLibraryStats.ConfigOrigin.PASTED, Translations.tr("Shared config"));
            } else {
               var4 = null;
            }

            var1.accept(var4, var4 == null ? "error" : null);
         }
      }));
   }

   private static String problem(Throwable var0) {
      return ConfigLibraryHost.unreachable(var0) ? "offline" : Objects.requireNonNullElse(ConfigLibraryHost.refusal(var0), "error");
   }

   private static void main(Runnable var0) {
      class_310.method_1551().execute(var0);
   }

   record LibraryError(String code, long expiresAt, long edited) {
   }
}
