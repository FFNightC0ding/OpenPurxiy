// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_310;
import net.minecraft.class_3675;

final class RuntimeInfo {
   private static final Map<String, RuntimeInfo.TypeN> previous = new HashMap<>();

   private RuntimeInfo() {
   }

   static void tick(class_310 var0) {
      boolean var1 = var0.field_1687 != null && var0.field_1755 == null && var0.method_1569() && LoaderMessages.authorized && LoaderMessages.menuAllowed();

      for (JsonElement var3 : LoaderMessages.catalog) {
         JsonObject var4 = var3.getAsJsonObject();
         if (ModuleCatalog.keyToggles(var4)) {
            int var5 = ModuleCatalog.key(var4);
            boolean var6 = var5 > 0 && class_3675.method_15987(var0.method_22683(), var5);
            if (pressed(var4, var5, var6, var1)) {
               LoaderMessages.toggle(var4);
            }
         }
      }
   }

   static boolean pressed(JsonObject var0, int var1, boolean var2, boolean var3) {
      RuntimeInfo.TypeN var4 = previous.put(var0.get("id").getAsString(), new RuntimeInfo.TypeN(var1, var2));
      return var3 && var1 > 0 && var2 && var4 != null && var4.key == var1 && !var4.down;
   }

   private record TypeN(int key, boolean down) {
   }
}
