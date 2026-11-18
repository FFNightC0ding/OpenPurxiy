// Sourced By DevFFNightmare
package dev.purxiy.modules;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.IntegerSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.Setting;
import dev.purxiy.loader.fabric.MenuCommands;
import dev.purxiy.ui.clickgui.Studio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class Module {
   public static final int NO_KEY = -1;
   private final String id;
   private final String name;
   private final String description;
   private final String icon;
   private final Category category;
   private final List<Setting<?>> settings = new ArrayList<>();
   private final Map<Setting<?>, String> sections = new IdentityHashMap<>();
   
   private String pendingSection;
   private final boolean toggleable;
   private final boolean inEdition;
   private boolean enabled;
   private boolean onByDefault;
   private boolean previewing;
   private int key = -1;

   public Module(String var1, String var2, String var3, String var4, Category var5) {
      this(var1, var2, var3, var4, var5, true);
   }

   protected Module(String var1, String var2, String var3, String var4, Category var5, boolean var6) {
      this.id = var1;
      this.name = var2;
      this.description = var3;
      this.icon = var4;
      this.category = var5;
      this.toggleable = var6;
      this.inEdition = true;
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

   public String icon() {
      return this.icon;
   }

   public Category category() {
      return this.category;
   }

   public boolean toggleable() {
      return this.toggleable;
   }

   public boolean enabled() {
      return this.toggleable && this.inEdition && (this.enabled || this.previewing) && MenuCommands.allowed();
   }

   public boolean switchedOn() {
      return this.toggleable && this.enabled;
   }

   public void setEnabled(boolean var1) {
      if (this.toggleable && this.enabled != var1) {
         boolean var2 = this.enabled();
         this.enabled = var1;
         this.changed(var2);
      }
   }

   public void setPreviewing(boolean var1) {
      if (this.toggleable && this.previewing != var1) {
         boolean var2 = this.enabled();
         this.previewing = var1;
         this.changed(var2);
      }
   }

   public void licenceChanged(boolean var1) {
      this.changed(var1);
   }

   private void changed(boolean var1) {
      if (this.enabled() != var1) {
         if (this.enabled()) {
            this.onEnable();
         } else {
            this.onDisable();
         }
      }
   }

   public void toggle() {
      this.setEnabled(!this.enabled);
   }

   public int key() {
      return this.key;
   }

   public boolean keybindToggles() {
      return true;
   }

   
   public Studio studio() {
      return null;
   }

   public void setKey(int var1) {
      this.key = var1;
   }

   public List<Setting<?>> settings() {
      return Collections.unmodifiableList(this.settings);
   }

   
   public Setting<?> setting(String var1) {
      for (Setting var3 : this.settings) {
         if (var3.id().equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   
   public String sectionBefore(Setting<?> var1) {
      return this.sections.get(var1);
   }

   protected void enabledByDefault() {
      this.enabled = true;
      this.onByDefault = true;
   }

   public boolean onByDefault() {
      return this.onByDefault;
   }

   protected void onEnable() {
   }

   protected void onDisable() {
   }

   protected <S extends Setting<?>> S add(S var1) {
      this.settings.add(var1);
      if (this.pendingSection != null) {
         this.sections.put(var1, this.pendingSection);
         this.pendingSection = null;
      }

      return (S)var1;
   }

   protected Module section(String var1) {
      this.pendingSection = var1;
      return this;
   }

   public BooleanSetting bool(String var1, String var2,  String var3, boolean var4) {
      return this.add(new BooleanSetting(var1, var2, var3, var4));
   }

   public NumberSetting number(
      String var1, String var2,  String var3, double var4, double var6, double var8, double var10, NumberSetting.Listener var12
   ) {
      return this.add(new NumberSetting(var1, var2, var3, var4, var6, var8, var10, var12));
   }

   public ModeSetting mode(String var1, String var2,  String var3, String var4, String... var5) {
      return this.add(new ModeSetting(var1, var2, var3, var4, List.of(var5)));
   }

   public ColorSetting color(String var1, String var2,  String var3, int var4, boolean var5) {
      return this.add(new ColorSetting(var1, var2, var3, var4, var5));
   }

   public IntegerSetting keybind(String var1, String var2,  String var3) {
      return this.add(new IntegerSetting(var1, var2, var3));
   }
}
