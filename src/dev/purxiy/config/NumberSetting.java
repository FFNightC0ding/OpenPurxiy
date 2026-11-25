package dev.purxiy.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.Locale;
import java.util.function.IntPredicate;

public final class NumberSetting extends Setting<Double> {
   private final double min;
   private final double max;
   private final double step;
   private final NumberSetting.Listener format;

   private NumberSetting.TargetSlot slots;

   private IntPredicate marked;

   public NumberSetting(String var1, String var2,  String var3, double var4, double var6, double var8, double var10, NumberSetting.Listener var12) {
      super(var1, var2, var3, var4);
      this.min = var6;
      this.max = var8;
      this.step = var10;
      this.format = var12;
   }

   public NumberSetting slots(NumberSetting.TargetSlot var1,  IntPredicate var2) {
      this.slots = var1;
      this.marked = var2;
      return this;
   }


   public NumberSetting.TargetSlot slots() {
      return this.slots;
   }

   public boolean marked(int var1) {
      return this.marked != null && this.marked.test(var1);
   }

   public double min() {
      return this.min;
   }

   public double max() {
      return this.max;
   }

   public double step() {
      return this.step;
   }

   public float floatValue() {
      return this.value.floatValue();
   }

   public int intValue() {
      return (int)Math.round(this.value);
   }

   public double normalized() {
      return (this.value - this.min) / (this.max - this.min);
   }

   public void setNormalized(double var1) {
      this.set(this.min + (this.max - this.min) * Math.max(0.0, Math.min(1.0, var1)));
   }

   public String display() {
      return this.format.apply(this.value);
   }

   public String display(double var1) {
      return this.format.apply(var1);
   }

   protected Double sanitize(Double var1) {
      double var2 = Math.max(this.min, Math.min(this.max, var1));
      if (this.step > 0.0) {
         var2 = this.min + Math.round((var2 - this.min) / this.step) * this.step;
         var2 = Math.max(this.min, Math.min(this.max, var2));
         var2 = Math.round(var2 * 1000000.0) / 1000000.0;
      }

      return var2;
   }

   @Override
   public JsonElement save() {
      return new JsonPrimitive(this.value);
   }

   @Override
   public void load(JsonElement var1) {
      if (var1.isJsonPrimitive() && var1.getAsJsonPrimitive().isNumber()) {
         this.set(var1.getAsDouble());
      }
   }

   @FunctionalInterface
   public interface Listener {
      NumberSetting.Listener INTEGER = var0 -> Integer.toString((int)Math.round(var0));
      NumberSetting.Listener DECIMAL = var0 -> String.format(Locale.ROOT, "%.1f", var0);
      NumberSetting.Listener PERCENT = var0 -> Math.round(var0 * 100.0) + "%";
      NumberSetting.Listener MULTIPLIER = var0 -> String.format(Locale.ROOT, "%.2f×", var0);

      String apply(double var1);

      static NumberSetting.Listener unit(String var0) {
         return var1 -> {
            String var3 = Math.abs(var1 - Math.rint(var1)) < 1.0E-6 ? Integer.toString((int)Math.rint(var1)) : String.format(Locale.ROOT, "%.1f", var1);
            return var3 + var0;
         };
      }
   }

   public static enum TargetSlot {
      HOTBAR,
      INVENTORY;

      private static NumberSetting.TargetSlot[] $values() {
         return new NumberSetting.TargetSlot[]{HOTBAR, INVENTORY};
      }
   }
}
