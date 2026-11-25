package dev.purxiy.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public final class KeybindSetting extends Setting<List<KeybindSetting.KeyCombo>> {
   public static final int MAX_LENGTH = 64;

   public KeybindSetting(String var1, String var2,  String var3) {
      super(var1, var2, var3, List.of());
   }

   protected List<KeybindSetting.KeyCombo> sanitize(List<KeybindSetting.KeyCombo> var1) {
      ArrayList var2 = new ArrayList(var1.size());

      for (KeybindSetting.KeyCombo var4 : var1) {
         if (var4 != null) {
            var2.add(new KeybindSetting.KeyCombo(cut(var4.from()), cut(var4.to())));
         }
      }

      return List.copyOf(var2);
   }

   private static String cut( String var0) {
      if (var0 == null) {
         return "";
      } else {
         String var1 = var0.replace('\n', ' ').replace('\r', ' ');
         return var1.length() > 64 ? var1.substring(0, 64) : var1;
      }
   }

   @Override
   public JsonElement save() {
      JsonArray var1 = new JsonArray();

      for (KeybindSetting.KeyCombo var3 : this.value) {
         JsonObject var4 = new JsonObject();
         var4.addProperty("from", var3.from());
         var4.addProperty("to", var3.to());
         var1.add(var4);
      }

      return var1;
   }

   @Override
   public void load(JsonElement var1) {
      if (var1.isJsonArray()) {
         ArrayList var2 = new ArrayList();

         for (JsonElement var4 : var1.getAsJsonArray()) {
            if (var4.isJsonObject()) {
               JsonObject var5 = var4.getAsJsonObject();
               String var6 = var5.has("from") && var5.get("from").isJsonPrimitive() ? var5.get("from").getAsString() : "";
               String var7 = var5.has("to") && var5.get("to").isJsonPrimitive() ? var5.get("to").getAsString() : "";
               if (!var6.isBlank() || !var7.isBlank()) {
                  var2.add(new KeybindSetting.KeyCombo(var6, var7));
               }
            }
         }

         this.set(var2);
      }
   }

   public record KeyCombo(String from, String to) {
      public boolean complete() {
         return !this.from.isBlank() && !this.to.isBlank();
      }
   }
}
