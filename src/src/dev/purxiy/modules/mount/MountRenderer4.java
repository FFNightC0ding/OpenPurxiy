// Sourced By DevFFNightmare
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

public final class MountRenderer4 {
   static MountModule K;
   static ModuleContext g;
   private static int y = 0;
   private static int p = 0;
   private static int a = 0;



   private MountRenderer4() {
   }

   public static void cameraPosition(class_1297 var0, boolean var1, float var2, double[] var3) {
      float[] var4 = MountUpdater4.devCamera();
      if (var4 != null && var0 == class_310.method_1551().field_1724 && MountUpdater4.visible()) {
         class_243 var9 = MountRenderer5.worldPoint(var4[0], var4[1], var4[2], var2);
         var3[0] = var9.field_1352;
         var3[1] = var9.field_1351;
         var3[2] = var9.field_1350;
      } else if (!var1 && var0 == class_310.method_1551().field_1724 && MountRenderer5.cockpitActive()) {
         class_243 var8 = MountRenderer5.cockpitPosition(var2);
         var3[0] = var8.field_1352;
         var3[1] = var8.field_1351;
         var3[2] = var8.field_1350;
      } else if (!var1
         && var0 == class_310.method_1551().field_1724
         && MountUpdater4.visible()
         && !MountUpdater4.mounted()
         && MountUpdater4.boarding(var2) > 0.0F) {
         class_243 var7 = MountRenderer5.seatEye(var2);
         float var6 = MountUpdater4.boarding(var2);
         var3[0] += (var7.field_1352 - var3[0]) * var6;
         var3[1] += (var7.field_1351 - var3[1]) * var6;
         var3[2] += (var7.field_1350 - var3[2]) * var6;
      } else if (var1
         && var0 == class_310.method_1551().field_1724
         && MountUpdater4.visible()
         && (MountUpdater4.mounted() || MountUpdater4.boarding(var2) > 0.0F)) {
         class_243 var5 = MountRenderer5.cameraOffset(var2);
         var3[0] += var5.field_1352;
         var3[1] += var5.field_1351;
         var3[2] += var5.field_1350;
      }
   }

   public static void cameraRotation(class_1297 var0, float var1, float[] var2) {
      float[] var3 = MountUpdater4.devCamera();
      if (var3 != null && var0 == class_310.method_1551().field_1724 && MountUpdater4.visible()) {
         class_243 var4 = MountRenderer5.worldPoint(var3[0], var3[1], var3[2], var1);
         class_243 var5 = MountRenderer5.worldPoint(var3[3], var3[4], var3[5], var1).method_1020(var4);
         var2[0] = (float)Math.toDegrees(Math.atan2(-var5.field_1352, var5.field_1350));
         var2[1] = (float)Math.toDegrees(-Math.atan2(var5.field_1351, var5.method_37267()));
      } else if (var0 == class_310.method_1551().field_1724 && MountUpdater4.mounted()) {
         var2[0] += MountUpdater4.cameraYawOffset(var1);
      }
   }

   public static float cameraDistance(class_1297 var0, boolean var1, float var2, float var3) {
      if (var1 && var0 == class_310.method_1551().field_1724 && MountUpdater4.visible()) {
         float var4 = MountUpdater4.boarding(var2);
         float var5 = Math.max(var3, K.X.floatValue() * MountUpdater4.spec().cameraScale());
         return var3 + (var5 - var3) * var4;
      } else {
         return var3;
      }
   }

   public static void avatarState(class_11890 var0, class_10055 var1, float var2) {
      if (MountRenderer5.isRider(var1)) {
         float var3 = MountUpdater4.boarding(var2);
         float var4 = var1.field_53446 + var1.field_53447;
         if (MountUpdater4.mounted()) {
            var4 += MountUpdater4.cameraYawOffset(var2);
         }

         var1.field_53446 = class_3532.method_17821(var3, var1.field_53446, MountUpdater4.yaw(var2));
         var1.field_53447 = Math.clamp(class_3532.method_15393(var4 - var1.field_53446), -80.0F, 80.0F);
         var1.field_53451 *= 1.0F - var3;
         var1.field_53410 = false;
      }
   }

   public static class_243 avatarOffset(class_10055 var0, class_243 var1) {
      return MountRenderer5.isRider(var0) ? var1.method_1019(MountRenderer5.riderOffset(MountRenderer5.partialTick())) : var1;
   }

   public static void avatarAttitude(class_10055 var0, class_4587 var1) {
      if (MountRenderer5.isRider(var0)) {
         float var2 = MountRenderer5.partialTick();
         float var3 = MountUpdater4.boarding(var2);
         float var4 = 0.70406246F;
         var1.method_46416(0.0F, var4, 0.0F);
         var1.method_22907(class_7833.field_40714.rotation(-MountRenderer5.riderPitch(var2) * var3 + MountRenderer5.riderForward() * var3));
         var1.method_22907(class_7833.field_40718.rotation(-MountRenderer5.riderBank(var2) * var3));
         float var5 = 1.0F + (MountUpdater4.spec().riderScale() - 1.0F) * var3;
         var1.method_22905(var5, var5, var5);
         var1.method_46416(0.0F, -var4, 0.0F);
      }
   }

   public static void humanoid(class_572<?> var0, class_10034 var1) {
      if (var1 instanceof class_10055 var2 && MountRenderer5.isRider(var2)) {
         float var3 = MountRenderer5.partialTick();
         float var4 = MountUpdater4.boarding(var3);
         MountFactory.Data7 var5 = MountUpdater4.spec();
         boolean var6 = var5.bike();
         boolean var7 = var5.kind() == MountFactory.TypeK.BICYCLE;
         boolean var8 = var5.kind() == MountFactory.TypeK.QUAD;
         float var9 = var7 ? MountUpdater4.crank(var3) : 0.0F;
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
            float var15 = MountUpdater4.steering() * (var6 ? 0.16F : 0.12F);
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
      if (var0 == var2.field_1724 && var2.field_1724 != null && MountUpdater4.visible()) {
         float var3 = MountRenderer5.partialTick();
         if (!MountUpdater4.mounted() && MountUpdater4.boarding(var3) <= 0.0F) {
            return var1;
         } else {
            class_243 var4 = MountRenderer5.riderOffset(var3);
            class_238 var5 = var1.method_997(var4).method_1014(0.8 * var2.field_1724.method_55693());
            return var1.method_991(var5);
         }
      } else {
         return var1;
      }
   }

   public static class_241 drivingInput(class_241 var0) {
      return MountUpdater4.driving() ? MountUpdater4.drivingInput(var0) : var0;
   }

   public static class_241 moveInput(class_310 var0, class_241 var1, class_10185 var2) {
      if (!MountUpdater4.driving()) {
         return var1;
      } else if (!MountUpdater4.controlsAvailable(var0)) {
         return class_241.field_1340;
      } else if (MountUpdater4.entering()) {
         return new class_241(0.0F, 1.0F);
      } else {
         float var3 = MountUpdater4.inputScale();
         if (!Float.isFinite(var3)) {
            var3 = 0.0F;
         }

         var3 = Math.clamp(var3, 0.0F, 1.0F);
         return new class_241(0.0F, ((var2.comp_3159() ? 1 : 0) - (var2.comp_3160() ? 1 : 0)) * var3);
      }
   }

   public static class_10185 keyInput(class_310 var0, class_10185 var1) {
      if (MountUpdater4.driving() && MountUpdater4.controlsAvailable(var0)) {
         return MountUpdater4.entering()
            ? new class_10185(false, false, false, false, false, false, false)
            : new class_10185(
               var1.comp_3159(),
               var1.comp_3160(),
               false,
               false,
               var1.comp_3163() || MountUpdater4.wantsHop(),
               var1.comp_3164(),
               var1.comp_3165() || var1.comp_3159()
            );
      } else {
         return var1;
      }
   }

   public static void worldStart(class_4184 var0, Matrix4f var1, Matrix4f var2) {
      MountRenderer5.beginWorld();
      MountRenderer8.beginFrame(var0, var1, var2);
      if (!MountRenderer13.capturing()) {
         MountRenderer5.frameMatrices(var1, var2);
      }
   }

   public static void worldEnd() {
      MountRenderer5.endWorld();
   }

   public static boolean cockpit() {
      return MountRenderer5.cockpitActive();
   }

   public static boolean capturing() {
      return MountRenderer13.capturing();
   }

   public static boolean applies(class_1297 var0) {
      return MountSupport6.applies(var0);
   }

   public static class_243 view(class_1297 var0) {
      return MountSupport6.view(var0);
   }

   public static class_243 eye(class_1297 var0, class_243 var1) {
      return MountSupport6.eye(var0, var1);
   }

   public static class_239 pick(class_1297 var0, float var1, Supplier<class_239> var2) {
      return MountSupport6.pick(var0, var1, var2);
   }

   public static boolean inClosedCabin(class_10055 var0) {
      return MountRenderer5.inClosedCabin(var0);
   }

   public static void render(WorldRenderContext var0) {
      MountRenderer5.render(var0);
   }

   public static void renderSheets() {
      MountRenderer5.renderSheets();
   }

   public static float fov(float var0) {
      return MountSupport3.fov(var0);
   }

   public static double mouseScale() {
      return MountSupport3.mouseScale();
   }

   public static boolean touchAttack() {
      return MountRenderer10.attack();
   }

   public static boolean touchUse() {
      return MountRenderer10.use();
   }

   public static boolean blockAttackHold() {
      return MountRenderer10.blockAttackHold();
   }

   public static void keyState(class_304 var0, boolean var1) {
      MountRenderer10.keyState(var0, var1);
   }

   public static boolean skipSecondaryFrame() {
      return MountSupport7.skipSecondaryFrame();
   }

   public static void mainFrame(Args var0) {
      MountSupport7.mainFrame(var0);
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
