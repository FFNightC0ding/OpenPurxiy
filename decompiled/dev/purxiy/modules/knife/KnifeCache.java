package dev.purxiy.modules.knife;

import dev.purxiy.loader.fabric.ClientServices;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_1060;
import net.minecraft.class_2960;
import net.minecraft.class_310;

public final class KnifeCache {
   private static final class_2960 G = ClientServices.id("textures/knife/white.png");
   private static final Map<KnifeCamos.Camo, class_2960> j = new HashMap<>();
   private static final Map<KnifeCamos.Camo, class_2960> a = new HashMap<>();

   private KnifeCache() {
   }

   public static class_2960 atlas(KnifeCamos.Camo var0) {
      return var0 == null ? G : j.get(var0);
   }

   public static class_2960 swatch(KnifeCamos.Camo var0) {
      return a.get(var0);
   }

   static void release() {
      class_1060 var0 = class_310.method_1551().method_1531();

      for (class_2960 var2 : j.values()) {
         var0.method_4615(var2);
      }

      for (class_2960 var4 : a.values()) {
         var0.method_4615(var4);
      }

      var0.method_4615(G);
      j.clear();
      a.clear();
   }

   static {
      for (List<KnifeCamos.Camo> list : KnifeCamos.Y) {
         for (KnifeCamos.Camo camo : list) {
            j.put(camo, ClientServices.id("textures/knife/camos/" + camo.id() + ".png"));
            a.put(camo, ClientServices.id("textures/knife/camos/" + camo.id() + "_swatch.png"));
         }
      }
   }
}
