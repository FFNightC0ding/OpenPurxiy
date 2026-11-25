package dev.purxiy.config;

import com.google.gson.JsonElement;
import java.util.function.BooleanSupplier;

public abstract class Setting<T> {
   private final String id;
   private final String name;

   private final String description;
   private final T defaultValue;
   protected T value;
   private BooleanSupplier visible = () -> true;

   protected Setting(String var1, String var2,  String var3, T var4) {
      this.id = var1;
      this.name = var2;
      this.description = var3;
      this.defaultValue = (T)var4;
      this.value = (T)var4;
   }

   public String id() {
      return this.id;
   }

   public String name() {
      return this.name;
   }


   public String description() {
      return this.description;
   }

   public T get() {
      return this.value;
   }

   public void set(T var1) {
      this.value = this.sanitize((T)var1);
   }

   public T defaultValue() {
      return this.defaultValue;
   }

   public boolean isDefault() {
      return this.value.equals(this.defaultValue);
   }

   public void reset() {
      this.value = this.defaultValue;
   }

   public Setting<T> visibleWhen(BooleanSupplier var1) {
      this.visible = var1;
      return this;
   }

   public boolean visible() {
      return this.visible.getAsBoolean();
   }

   protected T sanitize(T var1) {
      return (T)var1;
   }

   public abstract JsonElement save();

   public abstract void load(JsonElement var1);
}
