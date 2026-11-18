// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import com.google.gson.JsonElement;
import dev.purxiy.config.Setting;
import dev.purxiy.modules.Module;
import dev.purxiy.ui.DrawContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import net.minecraft.class_310;

public final class CosmeticCatalog {
   private static final Map<String, CosmeticCatalog.TypeU> owned = new HashMap<>();
   private static final Set<String> supported = Set.of(
      "mounts",
      "skins_3d",
      "cape",
      "wings",
      "halo",
      "knives",
      "spotify_player",
      "custom_crosshair",
      "creamy_keys",
      "name_tags",
      "shockwave",
      "place_animation",
      "chroma",
      "sky",
      "trail",
      "roles",
      "bokeh",
      "glass",
      "mist",
      "haze",
      "hand_movement",
      "target_hud"
   );

   private CosmeticCatalog() {
   }

   public static AutoCloseable install(Module var0, CosmeticCatalog.Listener var1) {
      check();
      CosmeticCatalog.TypeU var2 = new CosmeticCatalog.TypeU(var0, var1);
      if (owned.putIfAbsent(var0.id(), var2) != null) {
         throw new IllegalStateException("Preview already owned");
      } else {
         return () -> {
            check();
            if (owned.remove(var0.id(), var2) && var2.widget != null) {
               var2.widget.close();
            }
         };
      }
   }

   public static boolean available(String var0) {
      check();
      CosmeticCatalog.TypeU var1 = owned.get(var0);
      return var1 != null && var1.widget != null;
   }

   public static boolean settingVisible(String var0, String var1) {
      check();
      CosmeticCatalog.TypeU var2 = owned.get(var0);
      if (var2 == null) {
         return true;
      } else {
         Setting var3 = var2.module.setting(var1);
         return var3 == null || var3.visible();
      }
   }

   public static boolean supports(String var0) {
      return supported.contains(var0);
   }

   public static float height(String var0) {
      check();
      CosmeticCatalog.TypeU var1 = owned.get(var0);
      return var1 != null && var1.widget != null ? var1.widget.height() : 0.0F;
   }

   public static void render(String var0, DrawContext var1, float var2, float var3, float var4, boolean var5) {
      check();
      CosmeticCatalog.TypeU var6 = owned.get(var0);
      if (var6 != null && var6.widget != null) {
         var6.widget.render(var1, var2, var3, var4, var5);
      }
   }

   public static boolean mouseDown(String var0, float var1, float var2, int var3) {
      check();
      CosmeticCatalog.TypeU var4 = owned.get(var0);
      if (var4 != null && var4.widget != null) {
         Map var5 = values(var4);
         boolean var6 = var4.widget.mouseDown(var1, var2, var3);
         persistChanges(var4, var5);
         return var6;
      } else {
         return false;
      }
   }

   public static void mouseDrag(String var0, float var1, float var2) {
      check();
      CosmeticCatalog.TypeU var3 = owned.get(var0);
      if (var3 != null && var3.widget != null) {
         Map var4 = values(var3);
         var3.widget.mouseDrag(var1, var2);
         persistChanges(var3, var4);
      }
   }

   public static void mouseUp(String var0) {
      check();
      CosmeticCatalog.TypeU var1 = owned.get(var0);
      if (var1 != null && var1.widget != null) {
         Map var2 = values(var1);
         var1.widget.mouseUp();
         persistChanges(var1, var2);
      }
   }

   private static Map<String, String> values(CosmeticCatalog.TypeU var0) {
      LinkedHashMap var1 = new LinkedHashMap();

      for (Setting var3 : var0.module.settings()) {
         JsonElement var4 = var3.save();
         var1.put(var3.id(), var4.isJsonPrimitive() ? var4.getAsString() : var4.toString());
      }

      return var1;
   }

   private static void persistChanges(CosmeticCatalog.TypeU var0, Map<String, String> var1) {
      Map var2 = values(var0);
      ((java.util.Set<java.util.Map.Entry<?, ?>>)(Object)var2.entrySet()).removeIf(var1x -> Objects.equals(var1.get(var1x.getKey()), var1x.getValue()));
      if (!var2.isEmpty()) {
         try {
            CosmeticRegistry.mergeSettings(var0.module.id(), var2, false);
            MenuCommands.refresh();
         } catch (IOException var4) {
            throw new IllegalStateException("Could not save preview selection", var4);
         }
      }
   }

   private static void check() {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Preview requires owner thread");
      }
   }

   public interface Listener {
      float height();

      void render(DrawContext var1, float var2, float var3, float var4, boolean var5);

      default boolean mouseDown(float var1, float var2, int var3) {
         return false;
      }

      default void mouseDrag(float var1, float var2) {
      }

      default void mouseUp() {
      }

      default void close() {
         this.mouseUp();
      }
   }

   private record TypeU(Module module, CosmeticCatalog.Listener widget) {
   }
}
