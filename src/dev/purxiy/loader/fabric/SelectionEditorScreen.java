// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.purxiy.loader.runtime.SettingValues;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_4185;
import net.minecraft.class_437;

final class SelectionEditorScreen extends class_437 {
   private final class_437 parent;
   private final JsonObject setting;
   private final Map<String, String> draft;
   private final String kind;
   private final List<SelectionEditorScreen.TypeP> rows = new ArrayList<>();
   private final Set<String> selected = new TreeSet<>();
   private final Map<String, String> colors = new TreeMap<>();
   private final Map<String, String> choices = new TreeMap<>();
   private String filter = "";
   private String error = "";
   private int page;

   SelectionEditorScreen(class_437 var1, JsonObject var2, Map<String, String> var3) {
      super(class_2561.method_43470(var2.get("name").getAsString()));
      this.parent = var1;
      this.setting = var2;
      this.draft = var3;
      this.kind = var2.get("kind").getAsString();
      JsonElement var4 = JsonParser.parseString(SettingValues.normalize(var2, (String)var3.get(var2.get("id").getAsString())));
      if (this.kind.equals("SelectionSetting")) {
         JsonObject var5 = var4.getAsJsonObject();
         var5.getAsJsonArray("ids").forEach(var1x -> this.selected.add(var1x.getAsString()));
         var5.getAsJsonObject("colors").entrySet().forEach(var1x -> this.colors.put((String)var1x.getKey(), ((JsonElement)var1x.getValue()).getAsString()));
         if (var2.has("catalog")) {
            for (JsonElement var7 : var2.getAsJsonArray("catalog")) {
               JsonObject var8 = var7.getAsJsonObject();
               this.choices.put(var8.get("id").getAsString(), var8.get("name").getAsString());
            }
         }

         this.selected.forEach(var1x -> this.choices.putIfAbsent(var1x, var1x));
      } else {
         for (JsonElement var10 : var4.getAsJsonArray()) {
            if (this.kind.equals("NameListSetting")) {
               JsonObject var11 = var10.getAsJsonObject();
               this.rows.add(new SelectionEditorScreen.TypeP(var11.get("from").getAsString(), var11.get("to").getAsString()));
            } else {
               this.rows.add(new SelectionEditorScreen.TypeP(var10.getAsString(), ""));
            }
         }
      }
   }

   @Override
   protected void method_25426() {
      int var1 = Math.min(this.field_22789 - 24, 460);
      int var2 = (this.field_22789 - var1) / 2;
      byte var3 = 52;
      int var4 = Math.max(1, (this.field_22790 - 124) / 24);
      boolean var5 = this.kind.equals("SelectionSetting");
      List var6 = this.choices
         .entrySet()
         .stream()
         .filter(var1x -> (var1x.getKey() + " " + var1x.getValue()).toLowerCase(Locale.ROOT).contains(this.filter.toLowerCase(Locale.ROOT)))
         .toList();
      int var7 = var5 ? var6.size() : this.rows.size();
      this.page = Math.clamp((long)this.page, 0, Math.max(0, (var7 - 1) / var4));
      if (var5) {
         class_342 var8 = new class_342(this.field_22793, var2, 28, var1 - 68, 20, class_2561.method_43470("Zoeken"));
         var8.method_1880(128);
         var8.method_1852(this.filter);
         var8.method_1863(var1x -> this.filter = var1x);
         this.method_37063(var8);
         this.method_37063(ModuleClick.builder(class_2561.method_43470("Zoeken"), var1x -> {
            this.page = 0;
            this.refresh();
         }).method_46434(var2 + var1 - 64, 28, 64, 20).method_46431());
      }

      for (int var14 = this.page * var4; var14 < Math.min(var7, (this.page + 1) * var4); var14++) {
         if (var5) {
            Entry var9 = (Entry)var6.get(var14);
            String var10 = (String)var9.getKey();
            class_4185 var11 = ModuleClick.builder(this.selectionLabel(var10, (String)var9.getValue(), var1 - 112), var4x -> {
               if (!this.selected.remove(var10)) {
                  this.selected.add(var10);
               }

               var4x.method_25355(this.selectionLabel(var10, (String)var9.getValue(), var1 - 112));
            }).method_46434(var2, var3, var1 - 104, 20).method_46431();
            this.method_37063(var11);
            class_342 var12 = new class_342(this.field_22793, var2 + var1 - 100, var3, 100, 20, class_2561.method_43470("Eigen kleur"));
            var12.method_1880(9);
            var12.method_1852(this.colors.getOrDefault(var10, ""));
            var12.method_47404(class_2561.method_43470("Standaard"));
            var12.method_1863(var2x -> {
               if (var2x.isBlank()) {
                  this.colors.remove(var10);
               } else {
                  this.colors.put(var10, var2x);
               }
            });
            this.method_37063(var12);
         } else {
            SelectionEditorScreen.TypeP var16 = this.rows.get(var14);
            boolean var17 = this.kind.equals("NameListSetting");
            int var18 = var17 ? (var1 - 40) / 2 : var1 - 32;
            class_342 var19 = new class_342(this.field_22793, var2, var3, var18, 20, class_2561.method_43470(var17 ? "Huidige naam" : "Speler"));
            var19.method_1880(var17 ? 64 : 16);
            var19.method_1852(var16.from);
            var19.method_1863(var1x -> var16.from = var1x);
            this.method_37063(var19);
            var19.method_47404(class_2561.method_43470(var17 ? "Huidige naam" : "Speler"));
            if (var17) {
               class_342 var13 = new class_342(this.field_22793, var2 + var18 + 4, var3, var18, 20, class_2561.method_43470("Toon als"));
               var13.method_1880(64);
               var13.method_1852(var16.to);
               var13.method_47404(class_2561.method_43470("Toon als"));
               var13.method_1863(var1x -> var16.to = var1x);
               this.method_37063(var13);
            }

            this.method_37063(ModuleClick.builder(class_2561.method_43470("×"), var2x -> {
               this.rows.remove(var16);
               this.refresh();
            }).method_46434(var2 + var1 - 28, var3, 28, 20).method_46431());
         }

         var3 += 24;
      }

      if (!var5) {
         this.method_37063(ModuleClick.builder(class_2561.method_43470("Naam toevoegen"), var2x -> {
            this.rows.add(new SelectionEditorScreen.TypeP("", ""));
            this.page = (this.rows.size() - 1) / var4;
            this.refresh();
         }).method_46434(var2, this.field_22790 - 54, var1, 20).method_46431()).field_22763 = this.rows.size() < 2048;
      }

      int var15 = (var1 - 12) / 4;
      this.method_37063(ModuleClick.builder(class_2561.method_43470("Vorige"), var1x -> {
         this.page--;
         this.refresh();
      }).method_46434(var2, this.field_22790 - 28, var15, 20).method_46431()).field_22763 = this.page > 0;
      this.method_37063(ModuleClick.builder(class_2561.method_43470("Volgende"), var1x -> {
         this.page++;
         this.refresh();
      }).method_46434(var2 + var15 + 4, this.field_22790 - 28, var15, 20).method_46431()).field_22763 = (this.page + 1) * var4 < var7;
      this.method_37063(
         ModuleClick.builder(class_2561.method_43470("Terug"), var1x -> this.method_25419())
            .method_46434(var2 + 2 * (var15 + 4), this.field_22790 - 28, var15, 20)
            .method_46431()
      );
      this.method_37063(
         ModuleClick.builder(class_2561.method_43470("Klaar"), var1x -> this.apply())
            .method_46434(var2 + 3 * (var15 + 4), this.field_22790 - 28, var15, 20)
            .method_46431()
      );
   }

   private class_2561 selectionLabel(String var1, String var2, int var3) {
      String var4 = this.selected.contains(var1) ? "✓ " : "  ";
      return class_2561.method_43470(var4 + this.field_22793.method_27523(var2, var3 - 16));
   }

   private void refresh() {
      this.method_37067();
      this.method_25426();
   }

   private void apply() {
      try {
         Object var1;
         if (this.kind.equals("SelectionSetting")) {
            JsonObject var8 = new JsonObject();
            JsonArray var9 = new JsonArray();
            Set var10 = this.selected;
            var10.forEach(v -> var9.add((String)v));
            JsonObject var11 = new JsonObject();
            Map var6 = this.colors;
            var6.forEach((k, v) -> var11.addProperty((String)k, (String)v));
            var8.add("ids", var9);
            var8.add("colors", var11);
            var1 = var8;
         } else {
            JsonArray var2 = new JsonArray();

            for (SelectionEditorScreen.TypeP var4 : this.rows) {
               if (this.kind.equals("NameListSetting")) {
                  JsonObject var5 = new JsonObject();
                  var5.addProperty("from", var4.from);
                  var5.addProperty("to", var4.to);
                  var2.add(var5);
               } else {
                  var2.add(var4.from);
               }
            }

            var1 = var2;
         }

         this.draft.put(this.setting.get("id").getAsString(), SettingValues.normalize(this.setting, var1.toString()));
         this.method_25419();
      } catch (RuntimeException var7) {
         this.error = "Controleer de namen en kleuren. Gebruik voor een kleur bijvoorbeeld #B48CFF.";
      }
   }

   @Override
   public void method_25419() {
      this.field_22787.method_1507(this.parent);
   }

   @Override
   public boolean method_25421() {
      return false;
   }

   @Override
   public void method_25394(class_332 var1, int var2, int var3, float var4) {
      super.method_25394(var1, var2, var3, var4);
      var1.method_27534(this.field_22793, this.field_22785, this.field_22789 / 2, 10, -1);
      if (!this.error.isEmpty()) {
         var1.method_27534(this.field_22793, class_2561.method_43470(this.error), this.field_22789 / 2, this.field_22790 - 66, -34953);
      }
   }

   private static final class TypeP {
      String from;
      String to;

      TypeP(String var1, String var2) {
         this.from = var1;
         this.to = var2;
      }
   }
}
