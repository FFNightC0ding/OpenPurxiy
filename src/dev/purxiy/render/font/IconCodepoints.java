package dev.purxiy.render.font;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.purxiy.assets.AssetPaths;
import dev.purxiy.loader.fabric.ClientServices;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map.Entry;

public final class IconCodepoints {
   private static final Object2IntOpenHashMap<String> CODEPOINTS = new Object2IntOpenHashMap();

   private IconCodepoints() {
   }

   static void load() {
      try (InputStreamReader var0 = new InputStreamReader(AssetPaths.open("assets/purxiy/fonts/lucide-codepoints.json"), StandardCharsets.UTF_8)) {
         JsonObject var1 = JsonParser.parseReader(var0).getAsJsonObject();

         for (Entry var3 : var1.entrySet()) {
            CODEPOINTS.put((String)var3.getKey(), ((JsonElement)var3.getValue()).getAsInt());
         }
      } catch (Exception var6) {
         ClientServices.LOGGER.error("Failed to load icon codepoints", var6);
      }

      CODEPOINTS.defaultReturnValue(-1);
   }

   public static int codepoint(String var0) {
      return CODEPOINTS.getInt(var0);
   }

   public static boolean exists(String var0) {
      return CODEPOINTS.containsKey(var0);
   }
}
