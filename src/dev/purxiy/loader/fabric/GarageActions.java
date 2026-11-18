// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.class_2960;
import net.minecraft.class_304;
import net.minecraft.class_304.class_11900;
import net.minecraft.class_3675.class_307;

public final class GarageActions {
   private static class_304[] bindings;

   private GarageActions() {
   }

   public static void init() {
      if (bindings == null) {
         class_11900 var0 = class_11900.method_74698(class_2960.method_60655("purxiy_loader", "garage"));
         String[] var1 = new String[]{"action", "shift_up", "shift_down", "horn"};
         int[] var2 = new int[]{71, 61, 45, 72};
         bindings = new class_304[4];

         for (int var3 = 0; var3 < 4; var3++) {
            bindings[var3] = KeyBindingHelper.registerKeyBinding(new class_304("key.purxiy.mount_" + var1[var3], class_307.field_1668, var2[var3], var0));
         }
      }
   }

   public static boolean consume(int var0) {
      return bindings != null && bindings[var0].method_1436();
   }

   public static String name(int var0) {
      return bindings == null ? new String[]{"G", "=", "-", "H"}[var0] : bindings[var0].method_16007().getString();
   }
}
