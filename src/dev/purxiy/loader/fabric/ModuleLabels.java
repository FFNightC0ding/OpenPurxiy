package dev.purxiy.loader.fabric;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.purxiy.i18n.Translations;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_310;

public final class ModuleLabels {
   private ModuleLabels() {
   }

   public static List<String> names(boolean var0) {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Module labels require owner thread");
      } else {
         ModuleState var1 = ModuleState.active;
         if (var1 == null) {
            return List.of();
         } else {
            ArrayList var2 = new ArrayList();

            for (JsonElement var4 : LoaderMessages.catalog) {
               JsonObject var5 = var4.getAsJsonObject();
               String var6 = var5.has("category") ? var5.get("category").getAsString() : "";
               if (!var6.equals("CLIENT")
                  && (!var6.equals("HUD") || var0)
                  && (!var5.has("toggleable") || var5.get("toggleable").getAsBoolean())
                  && var1.runtime.isActive(var5.get("id").getAsString())) {
                  var2.add(Translations.tr(LinkAccountScreen.name(var5)));
               }
            }

            return List.copyOf(var2);
         }
      }
   }
}
