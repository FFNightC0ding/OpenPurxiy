package dev.purxiy.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public final class IntegerSetting extends Setting<Integer> {
   public IntegerSetting(String var1, String var2,  String var3) {
      this(var1, var2, var3, -1);
   }

   public IntegerSetting(String var1, String var2,  String var3, int var4) {
      super(var1, var2, var3, var4);
   }

   public int key() {
      return this.value;
   }

   public boolean matches(int var1) {
      return this.value != -1 && this.value == var1;
   }

   @Override
   public JsonElement save() {
      return new JsonPrimitive(this.value);
   }

   @Override
   public void load(JsonElement var1) {
      if (var1.isJsonPrimitive() && var1.getAsJsonPrimitive().isNumber()) {
         this.set(var1.getAsInt());
      }
   }
}
