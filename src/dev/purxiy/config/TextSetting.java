package dev.purxiy.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public final class TextSetting extends Setting<String> {
   private final int maxLength;

   private String placeholder;
   private boolean masked;
   private boolean colorCodes;

   public TextSetting(String var1, String var2,  String var3, String var4, int var5) {
      super(var1, var2, var3, var4);
      this.maxLength = var5;
   }

   public int maxLength() {
      return this.maxLength;
   }

   public TextSetting placeholder(String var1) {
      this.placeholder = var1;
      return this;
   }


   public String placeholder() {
      return this.placeholder;
   }

   public TextSetting masked() {
      this.masked = true;
      return this;
   }

   public boolean isMasked() {
      return this.masked;
   }

   public TextSetting colorCodes() {
      this.colorCodes = true;
      return this;
   }

   public boolean hasColorCodes() {
      return this.colorCodes;
   }

   protected String sanitize(String var1) {
      StringBuilder var2 = new StringBuilder(Math.min(var1.length(), this.maxLength));
      int var3 = 0;

      while (var3 < var1.length()) {
         int var4 = var1.codePointAt(var3);
         var3 += Character.charCount(var4);
         if (var4 != 10 && var4 != 13 && var4 != 9) {
            if (Character.isISOControl(var4)) {
               continue;
            }
         } else {
            var4 = 32;
         }

         if (var2.length() + Character.charCount(var4) > this.maxLength) {
            break;
         }

         var2.appendCodePoint(var4);
      }

      return var2.toString();
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
