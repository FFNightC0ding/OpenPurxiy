package dev.purxiy.loader.runtime;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public final class SettingValues {
   public static final int MAX_VALUE_CHARS = 262144;

   private SettingValues() {
   }

   public static boolean supported(JsonObject schema) {
      String var1 = schema.get("kind").getAsString();

      return switch (var1) {
         case "BooleanSetting", "NumberSetting", "ModeSetting", "ColorSetting", "TextSetting", "KeySetting", "PlayerListSetting", "NameListSetting", "SelectionSetting" -> true;
         default -> false;
      };
   }

   public static boolean accepts(JsonObject schema, String value) {
      try {
         normalize(schema, value);
         return true;
      } catch (RuntimeException var3) {
         return false;
      }
   }

   public static String normalize(JsonObject schema, String value) {
      if (value != null && value.length() <= 262144) {
         String var2 = schema.get("kind").getAsString();

         return switch (var2) {
            case "BooleanSetting" -> {
               if (!value.equals("true") && !value.equals("false")) {
                  throw new IllegalArgumentException("Boolean setting");
               }

               yield value;
            }
            case "NumberSetting" -> number(schema, value);
            case "ModeSetting" -> {
               String current = value;
               if (schema.has("renamed") && schema.getAsJsonObject("renamed").has(value)) {
                  current = schema.getAsJsonObject("renamed").get(value).getAsString();
               }

               String selected = current;
               if (schema.getAsJsonArray("choices").asList().stream().noneMatch(choice -> selected.equals(choice.getAsString()))) {
                  throw new IllegalArgumentException("Mode setting");
               }

               yield selected;
            }
            case "ColorSetting" -> {
               boolean themed = schema.has("themed") ? schema.get("themed").getAsBoolean() : "accent".equals(schema.get("default").getAsString());
               if (value.equals("accent")) {
                  if (!themed) {
                     throw new IllegalArgumentException("Unthemed colour");
                  }

                  yield value;
               } else {
                  int color = color(value);
                  if (!schema.has("alpha") || !schema.get("alpha").getAsBoolean()) {
                     color |= -16777216;
                  }

                  String encoded = String.format(Locale.ROOT, "#%08X", color);
                  yield themed && schema.has("designColor") && encoded.equalsIgnoreCase(schema.get("designColor").getAsString()) ? "accent" : encoded;
               }
            }
            case "TextSetting" -> text(value, schema.has("maxLength") ? schema.get("maxLength").getAsInt() : 1024);
            case "KeySetting" -> {
               if (!value.matches("-1|0|[1-9][0-9]{0,4}")) {
                  throw new IllegalArgumentException("Key setting");
               }

               int key = Integer.parseInt(value);
               if (key > 65535) {
                  throw new IllegalArgumentException("Key setting range");
               }

               yield Integer.toString(key);
            }
            case "PlayerListSetting" -> playerList(value);
            case "NameListSetting" -> nameList(value);
            case "SelectionSetting" -> selection(value);
            default -> throw new IllegalArgumentException("Unsupported setting kind");
         };
      } else {
         throw new IllegalArgumentException("Setting size");
      }
   }

   private static String number(JsonObject schema, String value) {
      double number = Double.parseDouble(value);
      double min = schema.get("min").getAsDouble();
      double max = schema.get("max").getAsDouble();
      if (Double.isFinite(number) && !(number < min) && !(number > max)) {
         double step = schema.has("step") ? schema.get("step").getAsDouble() : 0.0;
         if (step > 0.0) {
            number = Math.max(min, Math.min(max, min + Math.round((number - min) / step) * step));
            number = Math.round(number * 1000000.0) / 1000000.0;
         }

         return BigDecimal.valueOf(number).stripTrailingZeros().toPlainString();
      } else {
         throw new IllegalArgumentException("Number range");
      }
   }

   private static int color(String value) {
      if (!value.matches("#?(?:[0-9a-fA-F]{6}|[0-9a-fA-F]{8})")) {
         throw new IllegalArgumentException("Colour setting");
      } else {
         String hex = value.startsWith("#") ? value.substring(1) : value;
         return (int)Long.parseLong(hex, 16) | (hex.length() == 6 ? -16777216 : 0);
      }
   }

   private static String text(String value, int max) {
      if (max >= 0 && max <= 262144) {
         StringBuilder result = new StringBuilder(Math.min(max, value.length()));
         int i = 0;

         while (i < value.length()) {
            int point = value.codePointAt(i);
            i += Character.charCount(point);
            if (point != 10 && point != 13 && point != 9) {
               if (Character.isISOControl(point)) {
                  continue;
               }
            } else {
               point = 32;
            }

            if (result.length() + Character.charCount(point) > max) {
               break;
            }

            result.appendCodePoint(point);
         }

         return result.toString();
      } else {
         throw new IllegalArgumentException("Text size");
      }
   }

   private static JsonElement structured(String value) {
      int depth = 0;
      boolean quoted = false;
      boolean escaped = false;

      for (int i = 0; i < value.length(); i++) {
         char current = value.charAt(i);
         if (quoted) {
            if (escaped) {
               escaped = false;
            } else if (current == '\\') {
               escaped = true;
            } else if (current == '"') {
               quoted = false;
            }
         } else if (current == '"') {
            quoted = true;
         } else if (current == '[' || current == '{') {
            if (++depth > 4) {
               throw new IllegalArgumentException("Setting nesting");
            }
         } else if (current == ']' || current == '}') {
            if (--depth < 0) {
               throw new IllegalArgumentException("Setting structure");
            }
         }
      }

      if (depth == 0 && !quoted) {
         return JsonParser.parseString(value);
      } else {
         throw new IllegalArgumentException("Setting structure");
      }
   }

   private static String playerList(String value) {
      ArrayList<String> names = new ArrayList<>();
      if (value.stripLeading().startsWith("[")) {
         JsonArray array = structured(value).getAsJsonArray();
         if (array.size() > 2048) {
            throw new IllegalArgumentException("Player count");
         }

         for (JsonElement item : array) {
            if (!item.isJsonPrimitive() || !item.getAsJsonPrimitive().isString()) {
               throw new IllegalArgumentException("Player name");
            }

            names.add(item.getAsString());
         }
      } else {
         names.addAll(Arrays.asList(value.split("[,;\\s]+")));
      }

      JsonArray result = new JsonArray();
      HashSet<String> seen = new HashSet<>();

      for (String name : names) {
         String clean = text(name.strip(), 16);
         if (!clean.isBlank() && seen.add(clean.toLowerCase(Locale.ROOT))) {
            result.add(clean);
         }
      }

      if (result.size() > 2048) {
         throw new IllegalArgumentException("Player count");
      } else {
         return result.toString();
      }
   }

   private static String nameList(String value) {
      JsonArray array = structured(value).getAsJsonArray();
      if (array.size() > 2048) {
         throw new IllegalArgumentException("Rename count");
      } else {
         JsonArray result = new JsonArray();

         for (JsonElement item : array) {
            JsonObject entry = item.getAsJsonObject();
            if (!entry.keySet().equals(Set.of("from", "to"))) {
               throw new IllegalArgumentException("Rename fields");
            }

            JsonObject clean = new JsonObject();

            for (String key : List.of("from", "to")) {
               JsonElement name = entry.get(key);
               if (!name.isJsonPrimitive() || !name.getAsJsonPrimitive().isString()) {
                  throw new IllegalArgumentException("Rename value");
               }

               clean.addProperty(key, text(name.getAsString(), 64));
            }

            result.add(clean);
         }

         return result.toString();
      }
   }

   private static String selection(String value) {
      JsonObject object = structured(value).getAsJsonObject();
      if (!object.keySet().equals(Set.of("ids", "colors"))) {
         throw new IllegalArgumentException("Selection fields");
      } else {
         JsonArray chosen = object.getAsJsonArray("ids");
         JsonObject colors = object.getAsJsonObject("colors");
         if (chosen.size() <= 4096 && colors.size() <= 4096) {
            TreeSet<String> ids = new TreeSet<>();

            for (JsonElement item : chosen) {
               if (!item.isJsonPrimitive() || !item.getAsJsonPrimitive().isString()) {
                  throw new IllegalArgumentException("Selection ID");
               }

               String id = item.getAsString();
               requireId(id);
               ids.add(id);
            }

            TreeMap<String, String> normalizedColors = new TreeMap<>();

            for (Entry<String, JsonElement> entry : colors.entrySet()) {
               requireId(entry.getKey());
               if (!entry.getValue().isJsonPrimitive() || !entry.getValue().getAsJsonPrimitive().isString()) {
                  throw new IllegalArgumentException("Selection colour");
               }

               normalizedColors.put(entry.getKey(), String.format(Locale.ROOT, "#%06X", color(entry.getValue().getAsString()) & 16777215));
            }

            JsonObject result = new JsonObject();
            JsonArray outIds = new JsonArray();
            ids.forEach(outIds::add);
            JsonObject outColors = new JsonObject();
            normalizedColors.forEach(outColors::addProperty);
            result.add("ids", outIds);
            result.add("colors", outColors);
            return result.toString();
         } else {
            throw new IllegalArgumentException("Selection count");
         }
      }
   }

   private static void requireId(String id) {
      if (!id.matches("[A-Za-z0-9_.:/-]{1,128}")
         || id.startsWith("/")
         || id.endsWith("/")
         || id.contains("//")
         || Arrays.asList(id.split("/")).stream().anyMatch(part -> part.equals(".") || part.equals(".."))) {
         throw new IllegalArgumentException("Selection identifier");
      }
   }
}
