// Sourced By DevFFNightmare
package dev.purxiy.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class ChoiceListSetting extends Setting<List<String>> {
   public static final int MAX_LENGTH = 16;
   private final String placeholder;

   public ChoiceListSetting(String var1, String var2,  String var3, String var4) {
      super(var1, var2, var3, List.of());
      this.placeholder = var4;
   }

   public String placeholder() {
      return this.placeholder;
   }

   public List<String> names() {
      ArrayList var1 = new ArrayList(this.value.size());

      for (String var3 : this.value) {
         String var4 = var3.strip();
         if (!var4.isEmpty()) {
            Stream var5 = var1.stream();
            if (var5.noneMatch(x -> ((String)x).equalsIgnoreCase(var4))) {
               var1.add(var4);
            }
         }
      }

      return var1;
   }

   protected List<String> sanitize(List<String> var1) {
      ArrayList var2 = new ArrayList(var1.size());

      for (String var4 : var1) {
         if (var4 != null) {
            String var5 = var4.replace('\n', ' ').replace('\r', ' ').replace('\t', ' ');
            var2.add(var5.length() > 16 ? var5.substring(0, 16) : var5);
         }
      }

      return List.copyOf(var2);
   }

   @Override
   public JsonElement save() {
      JsonArray var1 = new JsonArray();

      for (String var3 : this.names()) {
         var1.add(var3);
      }

      return var1;
   }

   @Override
   public void load(JsonElement var1) {
      ArrayList var2 = new ArrayList();
      if (var1.isJsonArray()) {
         for (JsonElement var4 : var1.getAsJsonArray()) {
            if (var4.isJsonPrimitive() && !var4.getAsString().isBlank()) {
               var2.add(var4.getAsString().strip());
            }
         }
      } else {
         if (!var1.isJsonPrimitive()) {
            return;
         }

         for (String var6 : var1.getAsString().split("[,;\\s]+")) {
            if (!var6.isBlank()) {
               var2.add(var6);
            }
         }
      }

      this.set(var2);
   }
}
