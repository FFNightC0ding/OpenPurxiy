// Sourced By DevFFNightmare
package dev.purxiy.hud;

import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ClientTheme;
import dev.purxiy.render.RenderPipelines;
import java.util.List;
import net.minecraft.class_310;
import net.minecraft.class_408;
import net.minecraft.class_437;

public final class HudSlot {
   private static class_437 chat;

   private HudSlot() {
   }

   static AutoCloseable install(String var0, HudModule var1) {
      return HudRegistry.install(var1);
   }

   public static void syncScreen() {
      class_437 var0 = class_310.method_1551().field_1755;
      boolean var1 = var0 instanceof class_408 && ClientServices.hudLook().chatEditor() && !ClientTheme.active();
      if (chat != null && (!var1 || chat != var0)) {
         HudLayoutEngine.endChat();
         chat = null;
      }

      if (var1 && chat == null) {
         chat = var0;
         HudLayoutEngine.beginChat(var0);
      }
   }

   public static boolean active() {
      syncScreen();
      return HudLayoutEngine.active();
   }

   public static HudSlot.TypeB bounds(String var0) {
      for (HudModule var2 : HudRegistry.elements()) {
         if (var2.id().equals(var0)) {
            return var2.editBounds();
         }
      }

      return null;
   }

   public static boolean press(double var0, double var2, int var4) {
      syncScreen();
      RenderPipelines var5 = RenderPipelines.get();
      return HudLayoutEngine.mouseClicked(var5.hudFromGui(var0), var5.hudFromGui(var2), var4);
   }

   public static void drag(double var0, double var2) {
      RenderPipelines var4 = RenderPipelines.get();
      HudLayoutEngine.mouseDragged(var4.hudFromGui(var0), var4.hudFromGui(var2));
   }

   public static boolean scroll(double var0, double var2, double var4) {
      syncScreen();
      RenderPipelines var6 = RenderPipelines.get();
      return HudLayoutEngine.mouseScrolled(var6.hudFromGui(var0), var6.hudFromGui(var2), var4);
   }

   public static void release() {
      HudLayoutEngine.mouseReleased(HudLayoutEngine.pointerX(), HudLayoutEngine.pointerY());
   }

   public static List<HudModule> elements() {
      return HudRegistry.elements().stream().filter(var0 -> !(var0 instanceof HudGroupModule)).toList();
   }

   public record TypeB(float x, float y, float width, float height) {
   }
}
