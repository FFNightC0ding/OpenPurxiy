package dev.purxiy.loader.fabric;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.IntegerSetting;
import dev.purxiy.config.KeybindSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.Setting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.api.ModuleEntrypoint;
import dev.purxiy.modules.Category;
import dev.purxiy.modules.Module;
import dev.purxiy.ui.clickgui.Studio;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class ModuleDescriptor extends Module implements ModuleEntrypoint {
   public static final int NO_KEY = -1;
   private final String id;
   private final String name;
   private final List<Setting<?>> settings = new ArrayList<>();
   private boolean running;
   private int defaultKey = -1;

   protected ModuleDescriptor(String var1, String var2, String var3, String var4) {
      super(var1, var2, var3, var4, Category.RENDER);
      this.id = var1;
      this.name = var2;
   }

   @Override
   public final String id() {
      return this.id;
   }

   @Override
   public final String name() {
      return this.name;
   }

   @Override
   public final List<Setting<?>> settings() {
      return List.copyOf(this.settings);
   }

   @Override
   public final Setting<?> setting(String var1) {
      return this.settings.stream().filter(var1x -> var1x.id().equals(var1)).findFirst().orElse(null);
   }

   @Override
   public final boolean enabled() {
      return this.running;
   }

   @Override
   public final boolean switchedOn() {
      return CosmeticRegistry.enabled(this.id);
   }

   @Override
   public final void setEnabled(boolean var1) {
      CosmeticRegistry.requestEnabled(this.id, var1);
   }

   @Override
   public final void toggle() {
      this.setEnabled(!this.switchedOn());
   }

   public final Map<String, String> values() {
      LinkedHashMap var1 = new LinkedHashMap();

      for (Setting var3 : this.settings) {
         JsonElement var4 = var3.save();
         var1.put(var3.id(), var4.isJsonPrimitive() ? var4.getAsString() : var4.toString());
      }

      return Map.copyOf(var1);
   }

   @Override
   public final int key() {
      return MenuKeyBinding.moduleKey(this.id, this.defaultKey);
   }

   @Override
   public final void setKey(int var1) {
      this.defaultKey = var1;
   }

   @Override
   protected final void enabledByDefault() {
   }

   protected final ModuleDescriptor section(String var1) {
      return this;
   }

   @Override
   public boolean keybindToggles() {
      return true;
   }

   @Override
   public Studio studio() {
      return null;
   }

   @Override
   public final AutoCloseable start(ModuleContext var1) throws Exception {
      if (this.running) {
         throw new IllegalStateException("Module already started");
      } else {
         for (Setting var3 : this.settings) {
            String var4 = var1.settings().get(var3.id());
            if (var4 != null) {
               if (!(var3 instanceof BooleanSetting)) {
                  if (var3 instanceof NumberSetting) {
                     double var5 = Double.parseDouble(var4);
                     if (!Double.isFinite(var5)) {
                        throw new IllegalArgumentException("Invalid game setting number");
                     }

                     var3.load(new JsonPrimitive(var5));
                  } else if (!(var3 instanceof IntegerSetting)) {
                     if (var3 instanceof KeybindSetting) {
                        if (var4.length() > 32768) {
                           throw new IllegalArgumentException("Game name list is too long");
                        }

                        JsonElement var10 = JsonParser.parseString(var4);
                        if (!var10.isJsonArray() || var10.getAsJsonArray().size() > 256) {
                           throw new IllegalArgumentException("Invalid game name list");
                        }

                        var3.load(var10);
                     } else {
                        var3.load(new JsonPrimitive(var4));
                     }
                  } else {
                     int var9 = Integer.parseInt(var4);
                     if (var9 != -1 && (var9 < 32 || var9 > 348)) {
                        throw new IllegalArgumentException("Invalid game key setting");
                     }

                     var3.load(new JsonPrimitive(var9));
                  }
               } else {
                  if (!var4.equals("true") && !var4.equals("false")) {
                     throw new IllegalArgumentException("Invalid game setting boolean");
                  }

                  var3.load(new JsonPrimitive(Boolean.parseBoolean(var4)));
               }
            }
         }

         this.running = true;

         try {
            this.onEnable();
            this.attach(var1);
            var1.own(CosmeticCatalog.install(this, this.createPreview()));
         } catch (AssertionError | Exception | LinkageError var8) {
            this.running = false;

            try {
               this.onDisable();
            } catch (AssertionError | Exception | LinkageError var7) {
               var8.addSuppressed(var7);
            }

            throw var8;
         }

         return () -> {
            if (this.running) {
               this.running = false;
               this.onDisable();
            }
         };
      }
   }

   protected abstract void attach(ModuleContext var1) throws Exception;

   protected CosmeticCatalog.Listener createPreview() {
      return null;
   }

   @Override
   protected void onEnable() {
   }

   @Override
   protected void onDisable() {
   }

   @Override
   protected <S extends Setting<?>> S add(S var1) {
      this.settings.add(var1);
      return (S)var1;
   }

   public final <S extends Setting<?>> S registerSetting(S var1) {
      return this.add((S)var1);
   }

   @Override
   public final BooleanSetting bool(String var1, String var2, String var3, boolean var4) {
      return this.add(new BooleanSetting(var1, var2, var3, var4));
   }

   @Override
   public final NumberSetting number(String var1, String var2, String var3, double var4, double var6, double var8, double var10, NumberSetting.Listener var12) {
      return this.add(new NumberSetting(var1, var2, var3, var4, var6, var8, var10, var12));
   }

   @Override
   public final ModeSetting mode(String var1, String var2, String var3, String var4, String... var5) {
      return this.add(new ModeSetting(var1, var2, var3, var4, List.of(var5)));
   }

   @Override
   public final ColorSetting color(String var1, String var2, String var3, int var4, boolean var5) {
      return this.add(new ColorSetting(var1, var2, var3, var4, var5));
   }

   @Override
   public final IntegerSetting keybind(String var1, String var2, String var3) {
      return this.add(new IntegerSetting(var1, var2, var3));
   }
}
