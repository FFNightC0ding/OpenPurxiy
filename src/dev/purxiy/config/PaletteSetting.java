package dev.purxiy.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.function.Supplier;
import net.minecraft.class_1799;

public final class PaletteSetting extends Setting<PaletteSetting.PaletteContent> {
   private final String singular;
   private final String plural;
   private final String icon;
   private Supplier<List<PaletteSetting.PaletteEntry>> catalog = List::of;

   private List<PaletteSetting.PaletteEntry> choices;
   private int generation;

   public PaletteSetting(String var1, String var2,  String var3, String var4, String var5, String var6, Collection<String> var7) {
      super(var1, var2, var3, new PaletteSetting.PaletteContent(List.copyOf(var7), Map.of()));
      this.singular = var4;
      this.plural = var5;
      this.icon = var6;
   }

   public PaletteSetting catalog(Supplier<List<PaletteSetting.PaletteEntry>> var1) {
      this.catalog = var1;
      this.choices = null;
      return this;
   }

   public List<PaletteSetting.PaletteEntry> choices() {
      if (this.choices == null) {
         this.choices = List.copyOf(this.catalog.get());
      }

      return this.choices;
   }


   public PaletteSetting.PaletteEntry choice(String var1) {
      for (PaletteSetting.PaletteEntry var3 : this.choices()) {
         if (var3.id().equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   public String singular() {
      return this.singular;
   }

   public String plural() {
      return this.plural;
   }

   public String icon() {
      return this.icon;
   }

   public int generation() {
      return this.generation;
   }

   public void set(PaletteSetting.PaletteContent var1) {
      PaletteSetting.PaletteContent var2 = this.value;
      super.set(var1);
      if (!this.value.equals(var2)) {
         this.generation++;
      }
   }

   @Override
   public void reset() {
      this.set(this.defaultValue());
   }

   public boolean has(String var1) {
      return this.value.has(var1);
   }

   public int count() {
      return this.value.ids().size();
   }

   public void choose(String var1, boolean var2) {
      if (this.has(var1) != var2) {
         TreeSet var3 = new TreeSet<>(this.value.ids());
         if (var2) {
            var3.add(var1);
         } else {
            var3.remove(var1);
         }

         this.set(new PaletteSetting.PaletteContent(List.copyOf(var3), this.value.colors()));
      }
   }

   public void toggle(String var1) {
      this.choose(var1, !this.has(var1));
   }

   public void chooseAll(Collection<String> var1, boolean var2) {
      TreeSet var3 = new TreeSet<>(this.value.ids());
      if (var2) {
         var3.addAll(var1);
      } else {
         var3.removeAll(var1);
      }

      this.set(new PaletteSetting.PaletteContent(List.copyOf(var3), this.value.colors()));
   }

   public int customColor(String var1) {
      Integer var2 = this.value.colors().get(var1);
      return var2 == null ? -1 : var2 | 0xFF000000;
   }

   public int colorOf(String var1, int var2) {
      Integer var3 = this.value.colors().get(var1);
      return (var3 == null ? var2 : var3) | 0xFF000000;
   }

   public void color(String var1, int var2) {
      TreeMap var3 = new TreeMap<>(this.value.colors());
      if (var2 == -1) {
         var3.remove(var1);
      } else {
         var3.put(var1, var2 | 0xFF000000);
      }

      this.set(new PaletteSetting.PaletteContent(this.value.ids(), var3));
   }

   @Override
   public JsonElement save() {
      JsonObject var1 = new JsonObject();
      JsonArray var2 = new JsonArray();

      for (String var4 : this.value.ids()) {
         var2.add(var4);
      }

      var1.add("ids", var2);
      JsonObject var6 = new JsonObject();

      for (Entry var5 : this.value.colors().entrySet()) {
         var6.add((String)var5.getKey(), new JsonPrimitive(String.format(Locale.ROOT, "#%06X", (Integer)var5.getValue() & 16777215)));
      }

      var1.add("colors", var6);
      return var1;
   }

   @Override
   public void load(JsonElement var1) {
      if (var1.isJsonObject()) {
         JsonObject var2 = var1.getAsJsonObject();
         ArrayList var3 = new ArrayList();
         JsonElement var4 = var2.get("ids");
         if (var4 instanceof JsonArray) {
            for (JsonElement var6 : (JsonArray)var4) {
               if (var6.isJsonPrimitive()) {
                  var3.add(var6.getAsString());
               }
            }
         }

         TreeMap var12 = new TreeMap();
         if (var2.get("colors") instanceof JsonObject var7) {
            for (Entry var9 : var7.entrySet()) {
               try {
                  var12.put((String)var9.getKey(), Integer.parseUnsignedInt(((JsonElement)var9.getValue()).getAsString().replace("#", ""), 16) | 0xFF000000);
               } catch (RuntimeException var11) {
               }
            }
         }

         this.set(new PaletteSetting.PaletteContent(var3, var12));
      }
   }

   public record PaletteContent(List<String> ids, Map<String, Integer> colors) {
      public PaletteContent(List<String> ids, Map<String, Integer> colors) {
         ids = List.copyOf(new TreeSet(ids));
         colors = Collections.unmodifiableMap(new TreeMap(colors));
         this.ids = ids;
         this.colors = colors;
      }

      public boolean has(String var1) {
         return Collections.binarySearch(this.ids, var1) >= 0;
      }
   }

   public record PaletteEntry(String id, String name, Supplier<class_1799> icon, String group, int color,  String note, boolean literal) {
   }
}
