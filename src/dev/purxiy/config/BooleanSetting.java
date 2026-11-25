package dev.purxiy.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public final class BooleanSetting extends Setting<Boolean> {
   public BooleanSetting(String var1, String var2,  String var3, boolean var4) {
      super(var1, var2, var3, var4);
   }

   public boolean enabled() {
      return this.value;
   }

   public void toggle() {
      this.value = !this.value;
   }

   @Override
   public JsonElement save() {
      return new JsonPrimitive(this.value);
   }

   @Override
   public void load(JsonElement var1) {
      if (var1.isJsonPrimitive() && var1.getAsJsonPrimitive().isBoolean()) {
         this.value = var1.getAsBoolean();
      }
   }
}
