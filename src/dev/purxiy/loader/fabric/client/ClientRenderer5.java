// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import java.util.function.Supplier;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.class_10034;
import net.minecraft.class_10055;
import net.minecraft.class_10185;
import net.minecraft.class_11890;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_572;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

public final class ClientRenderer5 {
   private static final ClientSupport3.TypeX<ClientRenderer5.Listener> listeners = ClientSupport3.TypeX.create();

   public static AutoCloseable install(ClientRenderer5.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static void cameraPosition(class_1297 var0, boolean var1, float var2, double[] var3) {
      listeners.invoke(var4 -> {
         var4.cameraPosition(var0, var1, var2, var3);
         return null;
      }, null);
   }

   public static void cameraRotation(class_1297 var0, float var1, float[] var2) {
      listeners.invoke(var3 -> {
         var3.cameraRotation(var0, var1, var2);
         return null;
      }, null);
   }

   public static float cameraDistance(class_1297 var0, boolean var1, float var2, float var3) {
      return listeners.invoke(var4 -> var4.cameraDistance(var0, var1, var2, var3), var3);
   }

   public static void avatarState(class_11890 var0, class_10055 var1, float var2) {
      listeners.invoke(var3 -> {
         var3.avatarState(var0, var1, var2);
         return null;
      }, null);
   }

   public static class_243 avatarOffset(class_10055 var0, class_243 var1) {
      return listeners.invoke(var2 -> var2.avatarOffset(var0, var1), var1);
   }

   public static void avatarAttitude(class_10055 var0, class_4587 var1) {
      listeners.invoke(var2 -> {
         var2.avatarAttitude(var0, var1);
         return null;
      }, null);
   }

   public static void humanoid(class_572<?> var0, class_10034 var1) {
      listeners.invoke(var2 -> {
         var2.humanoid(var0, var1);
         return null;
      }, null);
   }

   public static class_238 bounds(class_1297 var0, class_238 var1) {
      return listeners.invoke(var2 -> var2.bounds(var0, var1), var1);
   }

   public static class_241 drivingInput(class_241 var0) {
      return listeners.invoke(var1 -> var1.drivingInput(var0), var0);
   }

   public static class_241 moveInput(class_310 var0, class_241 var1, class_10185 var2) {
      return listeners.invoke(var3 -> var3.moveInput(var0, var1, var2), var1);
   }

   public static class_10185 keyInput(class_310 var0, class_10185 var1) {
      return listeners.invoke(var2 -> var2.keyInput(var0, var1), var1);
   }

   public static void worldStart(class_4184 var0, Matrix4f var1, Matrix4f var2) {
      listeners.invoke(var3 -> {
         var3.worldStart(var0, var1, var2);
         return null;
      }, null);
   }

   public static void worldEnd() {
      listeners.invoke(var0 -> {
         var0.worldEnd();
         return null;
      }, null);
   }

   public static boolean cockpit() {
      return listeners.invoke(var0 -> var0.cockpit(), false);
   }

   public static boolean capturing() {
      return listeners.invoke(var0 -> var0.capturing(), false);
   }

   public static boolean applies(class_1297 var0) {
      return listeners.invoke(var1 -> var1.applies(var0), false);
   }

   public static class_243 view(class_1297 var0) {
      return listeners.invoke(var1 -> var1.view(var0), null);
   }

   public static class_243 eye(class_1297 var0, class_243 var1) {
      return listeners.invoke(var2 -> var2.eye(var0, var1), var1);
   }

   public static class_239 pick(class_1297 var0, float var1, Supplier<class_239> var2) {
      return listeners.invoke(var3 -> var3.pick(var0, var1, var2), (class_239)var2.get());
   }

   public static boolean inClosedCabin(class_10055 var0) {
      return listeners.invoke(var1 -> var1.inClosedCabin(var0), false);
   }

   public static void render(WorldRenderContext var0) {
      listeners.invoke(var1 -> {
         var1.render(var0);
         return null;
      }, null);
   }

   public static void renderSheets() {
      listeners.invoke(var0 -> {
         var0.renderSheets();
         return null;
      }, null);
   }

   public static float fov(float var0) {
      return listeners.invoke(var1 -> var1.fov(var0), var0);
   }

   public static double mouseScale() {
      return listeners.invoke(var0 -> var0.mouseScale(), 1.0);
   }

   public static boolean touchAttack() {
      return listeners.invoke(var0 -> var0.touchAttack(), false);
   }

   public static boolean touchUse() {
      return listeners.invoke(var0 -> var0.touchUse(), false);
   }

   public static boolean blockAttackHold() {
      return listeners.invoke(var0 -> var0.blockAttackHold(), false);
   }

   public static void keyState(class_304 var0, boolean var1) {
      listeners.invoke(var2 -> {
         var2.keyState(var0, var1);
         return null;
      }, null);
   }

   public static boolean skipSecondaryFrame() {
      return listeners.invoke(var0 -> var0.skipSecondaryFrame(), false);
   }

   public static void mainFrame(Args var0) {
      listeners.invoke(var1 -> {
         var1.mainFrame(var0);
         return null;
      }, null);
   }

   public interface Listener {
      void cameraPosition(class_1297 var1, boolean var2, float var3, double[] var4);

      void cameraRotation(class_1297 var1, float var2, float[] var3);

      float cameraDistance(class_1297 var1, boolean var2, float var3, float var4);

      void avatarState(class_11890 var1, class_10055 var2, float var3);

      class_243 avatarOffset(class_10055 var1, class_243 var2);

      void avatarAttitude(class_10055 var1, class_4587 var2);

      void humanoid(class_572<?> var1, class_10034 var2);

      class_238 bounds(class_1297 var1, class_238 var2);

      class_241 drivingInput(class_241 var1);

      class_241 moveInput(class_310 var1, class_241 var2, class_10185 var3);

      class_10185 keyInput(class_310 var1, class_10185 var2);

      void worldStart(class_4184 var1, Matrix4f var2, Matrix4f var3);

      void worldEnd();

      boolean cockpit();

      boolean capturing();

      boolean applies(class_1297 var1);

      class_243 view(class_1297 var1);

      class_243 eye(class_1297 var1, class_243 var2);

      class_239 pick(class_1297 var1, float var2, Supplier<class_239> var3);

      boolean inClosedCabin(class_10055 var1);

      void render(WorldRenderContext var1);

      void renderSheets();

      float fov(float var1);

      double mouseScale();

      boolean touchAttack();

      boolean touchUse();

      boolean blockAttackHold();

      void keyState(class_304 var1, boolean var2);

      boolean skipSecondaryFrame();

      void mainFrame(Args var1);
   }
}
