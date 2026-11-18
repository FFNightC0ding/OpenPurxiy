// Sourced By DevFFNightmare
package dev.purxiy.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.List;
import java.util.Map;

public final class ModeSetting extends Setting<String> {
   private final List<String> modes;
   private Map<String, String> renamed = Map.of();

   public ModeSetting(String var1, String var2,  String var3, String var4, List<String> var5) {
      super(var1, var2, var3, var4);
      if (!var5.contains(var4)) {
         throw new IllegalArgumentException("Default mode " + var4 + " is not one of " + var5);
      } else {
         this.modes = List.copyOf(var5);
      }
   }

   public ModeSetting renamed(Map<String, String> var1) {
      this.renamed = Map.copyOf(var1);
      return this;
   }

   public List<String> modes() {
      return this.modes;
   }

   public int index() {
      return Math.max(0, this.modes.indexOf(this.value));
   }

   public void select(int var1) {
      this.value = this.modes.get(Math.floorMod(var1, this.modes.size()));
   }

   public boolean is(String var1) {
      return this.value.equals(var1);
   }

   protected String sanitize(String var1) {
      String var2 = this.renamed.getOrDefault(var1, var1);
      return this.modes.contains(var2) ? var2 : this.value;
   }

   @Override
   public JsonElement save() {
      return new JsonPrimitive(this.value);
   }

   @Override
   public void load(JsonElement var1) {
      if (var1.isJsonPrimitive()) {
         this.set(var1.getAsString());
      }
   }
}
