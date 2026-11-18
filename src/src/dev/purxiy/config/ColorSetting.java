// Sourced By DevFFNightmare
package dev.purxiy.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import dev.purxiy.ui.Theme;
import java.util.Locale;

public final class ColorSetting extends Setting<Integer> {
   private static final String ACCENT = "accent";
   private final boolean alpha;
   private boolean themed;
   private boolean following;

   public ColorSetting(String var1, String var2,  String var3, int var4, boolean var5) {
      super(var1, var2, var3, var4);
      this.alpha = var5;
   }

   public ColorSetting followsAccent() {
      this.themed = true;
      this.following = true;
      return this;
   }

   public boolean themed() {
      return this.themed;
   }

   public boolean following() {
      return this.following;
   }

   public void follow() {
      if (this.themed) {
         this.value = this.defaultValue();
         this.following = true;
      }
   }

   public boolean allowsAlpha() {
      return this.alpha;
   }

   public int argb() {
      return this.following ? Theme.follow(this.defaultValue()) : this.value;
   }

   public Integer get() {
      return this.argb();
   }

   public void set(Integer var1) {
      this.following = false;
      super.set(var1);
   }

   @Override
   public boolean isDefault() {
      return this.themed ? this.following : super.isDefault();
   }

   @Override
   public void reset() {
      super.reset();
      this.following = this.themed;
   }

   public String hex() {
      int var1 = this.argb();
      return this.alpha && var1 >>> 24 != 255 ? String.format(Locale.ROOT, "#%08X", var1) : String.format(Locale.ROOT, "#%06X", var1 & 16777215);
   }

   protected Integer sanitize(Integer var1) {
      return this.alpha ? var1 : var1 | 0xFF000000;
   }

   @Override
   public JsonElement save() {
      return new JsonPrimitive(this.following ? "accent" : String.format(Locale.ROOT, "#%08X", this.value));
   }

   @Override
   public void load(JsonElement var1) {
      if (var1.isJsonPrimitive()) {
         String var2 = var1.getAsString();
         if (var2.equals("accent")) {
            this.follow();
            return;
         }

         var2 = var2.replace("#", "");

         try {
            long var3 = Long.parseLong(var2, 16);
            this.set((int)(var2.length() <= 6 ? var3 | 4278190080L : var3));
            if (this.themed && this.value.equals(this.defaultValue())) {
               this.following = true;
            }
         } catch (NumberFormatException var5) {
         }
      }
   }
}
