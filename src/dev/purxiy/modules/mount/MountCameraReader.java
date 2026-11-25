package dev.purxiy.modules.mount;

import dev.purxiy.loader.api.ModuleContext;
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
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_572;
import net.minecraft.class_7833;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

public final class MountCameraReader {
   static MountModule K;
   static ModuleContext g;
   private static int y = 0;
   private static int p = 0;
   private static int a = 0;

   private MountCameraReader() {
   }

   public static void cameraPosition(class_1297 var0, boolean var1, float var2, double[] var3) {
      float[] var4 = MountInteraction.devCamera();
      if (var4 != null && var0 == class_310.method_1551().field_1724 && MountInteraction.visible()) {
         class_243 var9 = MountCameraState.worldPoint(var4[0], var4[1], var4[2], var2);
         var3[0] = var9.field_1352;
         var3[1] = var9.field_1351;
         var3[2] = var9.field_1350;
      } else if (!var1 && var0 == class_310.method_1551().field_1724 && MountCameraState.cockpitActive()) {
         class_243 var8 = MountCameraState.cockpitPosition(var2);
         var3[0] = var8.field_1352;
         var3[1] = var8.field_1351;
         var3[2] = var8.field_1350;
      } else if (!var1
         && var0 == class_310.method_1551().field_1724
         && MountInteraction.visible()
         && !MountInteraction.mounted()
         && MountInteraction.boarding(var2) > 0.0F) {
         class_243 var7 = MountCameraState.seatEye(var2);
         float var6 = MountInteraction.boarding(var2);
         var3[0] += (var7.field_1352 - var3[0]) * var6;
         var3[1] += (var7.field_1351 - var3[1]) * var6;
         var3[2] += (var7.field_1350 - var3[2]) * var6;
      } else if (var1
         && var0 == class_310.method_1551().field_1724
         && MountInteraction.visible()
         && (MountInteraction.mounted() || MountInteraction.boarding(var2) > 0.0F)) {
         class_243 var5 = MountCameraState.cameraOffset(var2);
         var3[0] += var5.field_1352;
         var3[1] += var5.field_1351;
         var3[2] += var5.field_1350;
      }
   }

   public static void cameraRotation(class_1297 var0, float var1, float[] var2) {
      float[] var3 = MountInteraction.devCamera();
      if (var3 != null && var0 == class_310.method_1551().field_1724 && MountInteraction.visible()) {
         class_243 var4 = MountCameraState.worldPoint(var3[0], var3[1], var3[2], var1);
         class_243 var5 = MountCameraState.worldPoint(var3[3], var3[4], var3[5], var1).method_1020(var4);
         var2[0] = (float)Math.toDegrees(Math.atan2(-var5.field_1352, var5.field_1350));
         var2[1] = (float)Math.toDegrees(-Math.atan2(var5.field_1351, var5.method_37267()));
      } else if (var0 == class_310.method_1551().field_1724 && MountInteraction.mounted()) {
         var2[0] += MountInteraction.cameraYawOffset(var1);
      }
   }

   public static float cameraDistance(class_1297 var0, boolean var1, float var2, float var3) {
      if (var1 && var0 == class_310.method_1551().field_1724 && MountInteraction.visible()) {
         float var4 = MountInteraction.boarding(var2);
         float var5 = Math.max(var3, K.X.floatValue() * MountInteraction.spec().cameraScale());
         return var3 + (var5 - var3) * var4;
      } else {
         return var3;
      }
   }

   public static void avatarState(class_11890 var0, class_10055 var1, float var2) {
      if (MountCameraState.isRider(var1)) {
         float var3 = MountInteraction.boarding(var2);
         float var4 = var1.field_53446 + var1.field_53447;
         if (MountInteraction.mounted()) {
            var4 += MountInteraction.cameraYawOffset(var2);
         }

         var1.field_53446 = class_3532.method_17821(var3, var1.field_53446, MountInteraction.yaw(var2));
         var1.field_53447 = Math.clamp(class_3532.method_15393(var4 - var1.field_53446), -80.0F, 80.0F);
         var1.field_53451 *= 1.0F - var3;
         var1.field_53410 = false;
      }
   }

   public static class_243 avatarOffset(class_10055 var0, class_243 var1) {
      return MountCameraState.isRider(var0) ? var1.method_1019(MountCameraState.riderOffset(MountCameraState.partialTick())) : var1;
   }

   public static void avatarAttitude(class_10055 var0, class_4587 var1) {
      if (MountCameraState.isRider(var0)) {
         float var2 = MountCameraState.partialTick();
         float var3 = MountInteraction.boarding(var2);
         float var4 = 0.70406246F;
         var1.method_46416(0.0F, var4, 0.0F);
         var1.method_22907(class_7833.field_40714.rotation(-MountCameraState.riderPitch(var2) * var3 + MountCameraState.riderForward() * var3));
         var1.method_22907(class_7833.field_40718.rotation(-MountCameraState.riderBank(var2) * var3));
         float var5 = 1.0F + (MountInteraction.spec().riderScale() - 1.0F) * var3;
         var1.method_22905(var5, var5, var5);
         var1.method_46416(0.0F, -var4, 0.0F);
      }
   }

   public static void humanoid(class_572<?> var0, class_10034 var1) {
      if (var1 instanceof class_10055 var2 && MountCameraState.isRider(var2)) {
         float var3 = MountCameraState.partialTick();
         float var4 = MountInteraction.boarding(var3);
         MountFactory.MountSpec var5 = MountInteraction.spec();
         boolean var6 = var5.bike();
         boolean var7 = var5.kind() == MountFactory.Vehicle.BICYCLE;
         boolean var8 = var5.kind() == MountFactory.Vehicle.QUAD;
         float var9 = var7 ? MountInteraction.crank(var3) : 0.0F;
         float var10 = var7 ? (float)Math.sin(var9) * 0.42F : 0.0F;
         float var11 = var7 ? -((float)Math.sin(var9)) * 0.42F : 0.0F;
         float var12 = var7 ? 0.28F : (var6 ? 0.22F : (var8 ? 0.26F : 0.08F));
         float var13 = var7 ? -1.05F : (var6 ? -1.1F : (var8 ? -1.05F : -1.3F));
         var0.field_3392.field_3654 = class_3532.method_16439(var4, var0.field_3392.field_3654, var13 + var10);
         var0.field_3397.field_3654 = class_3532.method_16439(var4, var0.field_3397.field_3654, var13 + var11);
         var0.field_3392.field_3675 = class_3532.method_16439(var4, var0.field_3392.field_3675, var12);
         var0.field_3397.field_3675 = class_3532.method_16439(var4, var0.field_3397.field_3675, -var12);
         var0.field_3392.field_3674 = class_3532.method_16439(var4, var0.field_3392.field_3674, 0.04F);
         var0.field_3397.field_3674 = class_3532.method_16439(var4, var0.field_3397.field_3674, -0.04F);
         if (!var1.field_53414 && var1.field_63604 <= 0.0F) {
            float var14 = var5.reach();
            float var15 = MountInteraction.steering() * (var6 ? 0.16F : 0.12F);
            var0.field_3401.field_3654 = class_3532.method_16439(var4, var0.field_3401.field_3654, var14 + var15);
            var0.field_27433.field_3654 = class_3532.method_16439(var4, var0.field_27433.field_3654, var14 - var15);
            var0.field_3401.field_3675 = class_3532.method_16439(var4, var0.field_3401.field_3675, var6 ? 0.1F : -0.18F);
            var0.field_27433.field_3675 = class_3532.method_16439(var4, var0.field_27433.field_3675, var6 ? -0.1F : 0.18F);
            var0.field_3401.field_3674 = class_3532.method_16439(var4, var0.field_3401.field_3674, 0.02F);
            var0.field_27433.field_3674 = class_3532.method_16439(var4, var0.field_27433.field_3674, -0.02F);
         }
      }
   }

   public static class_238 bounds(class_1297 var0, class_238 var1) {
      class_310 var2 = class_310.method_1551();
      if (var0 == var2.field_1724 && var2.field_1724 != null && MountInteraction.visible()) {
         float var3 = MountCameraState.partialTick();
         if (!MountInteraction.mounted() && MountInteraction.boarding(var3) <= 0.0F) {
            return var1;
         } else {
            class_243 var4 = MountCameraState.riderOffset(var3);
            class_238 var5 = var1.method_997(var4).method_1014(0.8 * var2.field_1724.method_55693());
            return var1.method_991(var5);
         }
      } else {
         return var1;
      }
   }

   public static class_241 drivingInput(class_241 var0) {
      return MountInteraction.driving() ? MountInteraction.drivingInput(var0) : var0;
   }

   public static class_241 moveInput(class_310 var0, class_241 var1, class_10185 var2) {
      if (!MountInteraction.driving()) {
         return var1;
      } else if (!MountInteraction.controlsAvailable(var0)) {
         return class_241.field_1340;
      } else if (MountInteraction.entering()) {
         return new class_241(0.0F, 1.0F);
      } else {
         float var3 = MountInteraction.inputScale();
         if (!Float.isFinite(var3)) {
            var3 = 0.0F;
         }

         var3 = Math.clamp(var3, 0.0F, 1.0F);
         return new class_241(0.0F, ((var2.comp_3159() ? 1 : 0) - (var2.comp_3160() ? 1 : 0)) * var3);
      }
   }

   public static class_10185 keyInput(class_310 var0, class_10185 var1) {
      if (MountInteraction.driving() && MountInteraction.controlsAvailable(var0)) {
         return MountInteraction.entering()
            ? new class_10185(false, false, false, false, false, false, false)
            : new class_10185(
               var1.comp_3159(),
               var1.comp_3160(),
               false,
               false,
               var1.comp_3163() || MountInteraction.wantsHop(),
               var1.comp_3164(),
               var1.comp_3165() || var1.comp_3159()
            );
      } else {
         return var1;
      }
   }

   public static void worldStart(class_4184 var0, Matrix4f var1, Matrix4f var2) {
      MountCameraState.beginWorld();
      MountRenderPass.beginFrame(var0, var1, var2);
      if (!MountGarageScreen.capturing()) {
         MountCameraState.frameMatrices(var1, var2);
      }
   }

   public static void worldEnd() {
      MountCameraState.endWorld();
   }

   public static boolean cockpit() {
      return MountCameraState.cockpitActive();
   }

   public static boolean capturing() {
      return MountGarageScreen.capturing();
   }

   public static boolean applies(class_1297 var0) {
      return MountCameraPick.applies(var0);
   }

   public static class_243 view(class_1297 var0) {
      return MountCameraPick.view(var0);
   }

   public static class_243 eye(class_1297 var0, class_243 var1) {
      return MountCameraPick.eye(var0, var1);
   }

   public static class_239 pick(class_1297 var0, float var1, Supplier<class_239> var2) {
      return MountCameraPick.pick(var0, var1, var2);
   }

   public static boolean inClosedCabin(class_10055 var0) {
      return MountCameraState.inClosedCabin(var0);
   }

   public static void render(WorldRenderContext var0) {
      MountCameraState.render(var0);
   }

   public static void renderSheets() {
      MountCameraState.renderSheets();
   }

   public static float fov(float var0) {
      return MountViewState.fov(var0);
   }

   public static double mouseScale() {
      return MountViewState.mouseScale();
   }

   public static boolean touchAttack() {
      return MountInputRenderer.attack();
   }

   public static boolean touchUse() {
      return MountInputRenderer.use();
   }

   public static boolean blockAttackHold() {
      return MountInputRenderer.blockAttackHold();
   }

   public static void keyState(class_304 var0, boolean var1) {
      MountInputRenderer.keyState(var0, var1);
   }

   public static boolean skipSecondaryFrame() {
      return MountFrameSplit.skipSecondaryFrame();
   }

   public static void mainFrame(Args var0) {
      MountFrameSplit.mainFrame(var0);
   }

   static void drawn(boolean var0) {
      if (g != null) {
         g.metric("drawn", ++y);
         if (var0) {
            g.metric("world_drawn", ++p);
         }
      }
   }

   static void drawFailed() {
      if (g != null) {
         g.metric("draw_failures", ++a);
      }
   }
}
