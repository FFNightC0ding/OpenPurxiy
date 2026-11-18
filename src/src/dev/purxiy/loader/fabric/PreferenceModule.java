// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import com.google.gson.JsonPrimitive;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.Setting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.api.ModuleEntrypoint;
import java.util.ArrayList;
import java.util.List;

public abstract class PreferenceModule implements ModuleEntrypoint {
   private final List<Setting<?>> settings = new ArrayList<>();

   protected PreferenceModule(String var1, String var2, String var3, String var4) {
   }

   @Override
   public final AutoCloseable start(ModuleContext var1) throws Exception {
      for (Setting var3 : this.settings) {
         String var4 = var1.settings().get(var3.id());
         if (var4 != null) {
            if (var3 instanceof BooleanSetting) {
               if (!var4.equals("true") && !var4.equals("false")) {
                  throw new IllegalArgumentException("Invalid preference boolean");
               }

               var3.load(new JsonPrimitive(Boolean.parseBoolean(var4)));
            } else if (var3 instanceof NumberSetting) {
               double var5 = Double.parseDouble(var4);
               if (!Double.isFinite(var5)) {
                  throw new IllegalArgumentException("Invalid preference number");
               }

               var3.load(new JsonPrimitive(var5));
            } else {
               var3.load(new JsonPrimitive(var4));
            }
         }
      }

      return this.apply(var1);
   }

   protected abstract AutoCloseable apply(ModuleContext var1) throws Exception;

   protected <S extends Setting<?>> S add(S var1) {
      this.settings.add(var1);
      return (S)var1;
   }

   protected BooleanSetting bool(String var1, String var2, String var3, boolean var4) {
      return this.add(new BooleanSetting(var1, var2, var3, var4));
   }

   protected NumberSetting number(String var1, String var2, String var3, double var4, double var6, double var8, double var10, NumberSetting.Listener var12) {
      return this.add(new NumberSetting(var1, var2, var3, var4, var6, var8, var10, var12));
   }
}
