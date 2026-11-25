package dev.purxiy.ui.config;

import dev.purxiy.modules.Category;
import dev.purxiy.modules.Module;
import dev.purxiy.modules.SettingSection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

final class ConfigLibraryFactory {
   private static final Set<String> HEAVY = Set.of(
      "sky", "ambience", "bokeh", "glass", "mist", "weather", "chroma", "cyberspace", "radiance", "afterglow", "shockwave", "xray", "cute_world"
   );
   private static final Set<String> CREATOR = Set.of("nick_hider", "hud_hider");
   private static final Set<String> SMP = Set.of("coordinates", "direction", "session_time", "mounts");

   private ConfigLibraryFactory() {
   }

   static List<String> of(Collection<String> var0) {
      int var1 = 0;
      int var2 = 0;
      int var3 = 0;
      int var4 = 0;
      boolean var5 = false;

      for (String var7 : var0) {
         Module var8 = SettingSection.byId(var7);
         if (var8 != null && var8.toggleable()) {
            Category var9 = var8.category();
            if (var9 == Category.COMBAT) {
               var1++;
            } else if (var9 == Category.RENDER || var9 == Category.COSMETICS) {
               var2++;
            }

            var3 += HEAVY.contains(var7) ? 1 : 0;
            var4 += SMP.contains(var7) ? 1 : 0;
            var5 |= CREATOR.contains(var7);
         }
      }

      ArrayList var10 = new ArrayList();
      if (var1 >= 2) {
         var10.add("PvP");
      }

      if (var3 >= 2 || var2 >= 4) {
         var10.add("Visuals");
      }

      if (var3 == 0 && !var0.isEmpty()) {
         var10.add("FPS");
      }

      if (var4 >= 2) {
         var10.add("SMP");
      }

      if (var5) {
         var10.add("Creator");
      }

      if (var0.size() <= 6) {
         var10.add("Minimal");
      }

      return var10;
   }
}
