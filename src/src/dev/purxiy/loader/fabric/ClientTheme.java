// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import dev.purxiy.loader.fabric.client.ClientSupport3;
import net.minecraft.class_10055;
import net.minecraft.class_11890;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_4184;

public final class ClientTheme {
   private static final ClientSupport3.TypeX<ClientTheme.Listener> controllers = ClientSupport3.TypeX.create();

   private ClientTheme() {
   }

   public static AutoCloseable install(ClientTheme.Listener var0) {
      return controllers.subscribe(0, var0);
   }

   public static void resetAll() {
      controllers.invoke(var0 -> {
         var0.reset();
         return null;
      }, null);
   }

   public static boolean active() {
      boolean[] var0 = new boolean[]{false};
      return controllers.invoke(var1 -> var0[0] |= var1.active(), false);
   }

   public static float fov(float var0) {
      float[] var1 = new float[]{var0};
      return controllers.invoke(var1x -> {
         if (var1x.active()) {
            var1[0] = var1x.fov(var1[0]);
         }

         return var1[0];
      }, var0);
   }

   public static boolean hides(class_1297 var0) {
      boolean[] var1 = new boolean[]{false};
      return controllers.invoke(var2 -> var1[0] |= var2.hides(var0), false);
   }

   public static boolean place(class_4184 var0, class_1937 var1, class_1297 var2, float var3, double[] var4, float[] var5) {
      boolean[] var6 = new boolean[]{false};
      return controllers.invoke(var7 -> {
         if (!var7.active()) {
            return var6[0];
         } else if (var6[0]) {
            throw new IllegalStateException("Two preview cameras acquired one frame");
         } else {
            var6[0] = var7.place(var0, var1, var2, var3, var4, var5);
            return var6[0];
         }
      }, false);
   }

   public static void pose(class_11890 var0, class_10055 var1) {
      controllers.invoke(var2 -> {
         var2.pose(var0, var1);
         return null;
      }, null);
   }

   public interface Listener {
      boolean active();

      float fov(float var1);

      boolean hides(class_1297 var1);

      boolean place(class_4184 var1, class_1937 var2, class_1297 var3, float var4, double[] var5, float[] var6);

      void pose(class_11890 var1, class_10055 var2);

      void reset();
   }
}
