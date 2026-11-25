package dev.purxiy.modules.mount;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.IntegerSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.ClientLoader;
import dev.purxiy.loader.fabric.client.ClientRegistry24;
import dev.purxiy.loader.fabric.client.ClientRegistry26;
import dev.purxiy.loader.fabric.client.ClientRenderer5;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import java.util.List;
import java.util.Map;
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

public final class MountModule extends ModuleDescriptor {
   public final ModeSetting u;
   public final ModeSetting y;
   public final ModeSetting B;
   public final ColorSetting J;
   public final ColorSetting r;
   public final BooleanSetting V;
   public final BooleanSetting b;
   public final BooleanSetting q;
   public final BooleanSetting L;
   public final BooleanSetting c;
   public final BooleanSetting d;
   public final BooleanSetting x;
   public final BooleanSetting S;
   public final NumberSetting i;
   public final NumberSetting X;
   public final NumberSetting E;
   public final IntegerSetting z;

   public MountModule() {
      super("mounts", "Purxiy Garage", "Real cars and bikes that follow your own movement", "car-front");
      this.setKey(82);
      this.z = (IntegerSetting)this.add(new IntegerSetting("board_key", "Board / leave key", "The key shown beside your ride", 86));
      this.section("Your collection");
      List var1 = MountFactory.names();
      this.u = (ModeSetting)this.add(
         new ModeSetting("style", "Ride", "Nine rides, each with its own sound and way of moving", (String)var1.get(0), var1)
            .renamed(Map.of("Roadster", "Porsche 911", "Star Horse", "Porsche 911", "Sky Manta", "Porsche 911"))
      );
      this.section("Materials & details");
      this.S = this.bool("custom_finish", "Custom paint", "Pick your own colour beyond the collection's finishes", false);
      this.J = this.color("paint", "Paint", "The bodywork's colour", -4801344, false);
      this.J.visibleWhen(this.S::enabled);
      this.r = this.color("cabin_light", "Cabin light", "The soft light inside the cabin after dark", -5467164, false);
      this.V = this.bool("lights", "Lights", "Headlights come on in the dark; brakes, indicators and reverse lights work on their own", true);
      this.b = this.bool("particles", "Exhaust & effects", "Exhaust smoke and flames on a gear change", true);
      this.B = this.mode(
         "quality",
         "Render quality",
         "Quality draws the ride at double resolution for perfectly smooth edges",
         "Quality",
         new String[]{"Quality", "Performance"}
      );
      this.section("Engine");
      this.x = this.bool("smooth_throttle", "Progressive throttle", "Rides pull away gently and build up to your normal walking and sprinting speed", true);
      this.E = this.number("acceleration", "Take-off time", "Time holding W to reach full throttle after ignition", 2.0, 0.6, 4.0, 0.1, Listener.unit(" s"));
      this.E.visibleWhen(this.x::enabled);
      this.y = this.mode("gearbox", "Gearbox", "Automatic shifts or manual gears using the Controls-menu bindings", "Auto", new String[]{"Auto", "Manual"});
      this.section("Riding");
      this.q = this.bool(
         "auto_camera", "First person on entry", "Get in and look out from the driver's seat; your previous view returns when you get out", true
      );
      this.X = this.number("camera_distance", "Camera distance", "A wider view of your ride; respects walls", 6.0, 4.0, 8.0, 0.25, Listener.unit(" m"));
      this.L = this.bool("screen_focus", "Screen focus", "Look at the in-car screen and a wide field of view narrows so it reads easily", true);
      this.c = this.bool("hud", "Boarding prompt", "Your board key beside the parked ride, in the style of the HUD", true);
      this.d = this.bool("sounds", "Ride sounds", "Ignition, engine, gear changes and horn, different for every ride", true);
      this.i = this.number("volume", "Ride volume", "Follows Minecraft's master volume", 0.4, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.i.visibleWhen(this.d::enabled);
   }

   public boolean keybindToggles() {
      return false;
   }

   protected void onDisable() {
      MountUpdater4.clear();
   }

   public void selectStyle(String var1) {
      if (!this.u.is(var1) && this.u.modes().contains(var1)) {
         this.u.set(var1);
         this.J.set(MountFactory.get(var1).paints().getFirst().color());
      }
   }

   public boolean onKey(int var1, int var2) {
      if (var1 != -1 && var2 == 1 && MountUpdater4.controlsAvailable(class_310.method_1551())) {
         if (!this.z.matches(var1) || var1 == this.key() && !MountUpdater4.mounted() && !MountUpdater4.canEnter()) {
            if (var1 == this.key()) {
               MountUpdater4.keyAction();
               return true;
            } else {
               return false;
            }
         } else {
            MountUpdater4.boardAction();
            return true;
         }
      } else {
         return false;
      }
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ClientServices.acquire(this.id()));
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/garage.fsh",
               "shaders/core/garage.vsh",
               "shaders/core/garage_composite.fsh",
               "shaders/core/garage_composite.vsh",
               "shaders/core/garage_decal.fsh",
               "shaders/core/porsche_surface.fsh",
               "shaders/core/porsche_surface.vsh"
            )
         )
      );
      var1.own(
         ClientRegistry24.install(
            var1,
            List.of(
               "assets/purxiy/garage/c911.pgv",
               "assets/purxiy/garage/dracorus.pgv",
               "assets/purxiy/garage/gt4.pgv",
               "assets/purxiy/garage/kidsbike.pgv",
               "assets/purxiy/garage/m2.pgv",
               "assets/purxiy/garage/portrait/c911.png",
               "assets/purxiy/garage/portrait/dracorus.png",
               "assets/purxiy/garage/portrait/gt4.png",
               "assets/purxiy/garage/portrait/kidsbike.png",
               "assets/purxiy/garage/portrait/m2.png",
               "assets/purxiy/garage/portrait/quad.png",
               "assets/purxiy/garage/portrait/s1000rr.png",
               "assets/purxiy/garage/portrait/urus.png",
               "assets/purxiy/garage/portrait/veneno.png",
               "assets/purxiy/garage/quad.pgv",
               "assets/purxiy/garage/s1000rr.pgv",
               "assets/purxiy/garage/urus.pgv",
               "assets/purxiy/garage/vehicles.json",
               "assets/purxiy/garage/veneno.pgv",
               "assets/purxiy/sounds/mounts/bicycle_horn.ogg",
               "assets/purxiy/sounds/mounts/bicycle_idle.ogg",
               "assets/purxiy/sounds/mounts/bicycle_pedal.ogg",
               "assets/purxiy/sounds/mounts/bicycle_pop.ogg",
               "assets/purxiy/sounds/mounts/bicycle_roll.ogg",
               "assets/purxiy/sounds/mounts/bicycle_shift.ogg",
               "assets/purxiy/sounds/mounts/bicycle_start.ogg",
               "assets/purxiy/sounds/mounts/flat6_high.ogg",
               "assets/purxiy/sounds/mounts/flat6_horn.ogg",
               "assets/purxiy/sounds/mounts/flat6_idle.ogg",
               "assets/purxiy/sounds/mounts/flat6_low.ogg",
               "assets/purxiy/sounds/mounts/flat6_pop.ogg",
               "assets/purxiy/sounds/mounts/flat6_shift.ogg",
               "assets/purxiy/sounds/mounts/flat6_start.ogg",
               "assets/purxiy/sounds/mounts/flat6na_high.ogg",
               "assets/purxiy/sounds/mounts/flat6na_horn.ogg",
               "assets/purxiy/sounds/mounts/flat6na_idle.ogg",
               "assets/purxiy/sounds/mounts/flat6na_low.ogg",
               "assets/purxiy/sounds/mounts/flat6na_pop.ogg",
               "assets/purxiy/sounds/mounts/flat6na_shift.ogg",
               "assets/purxiy/sounds/mounts/flat6na_start.ogg",
               "assets/purxiy/sounds/mounts/i6turbo_high.ogg",
               "assets/purxiy/sounds/mounts/i6turbo_horn.ogg",
               "assets/purxiy/sounds/mounts/i6turbo_idle.ogg",
               "assets/purxiy/sounds/mounts/i6turbo_low.ogg",
               "assets/purxiy/sounds/mounts/i6turbo_pop.ogg",
               "assets/purxiy/sounds/mounts/i6turbo_shift.ogg",
               "assets/purxiy/sounds/mounts/i6turbo_start.ogg",
               "assets/purxiy/sounds/mounts/inline4_high.ogg",
               "assets/purxiy/sounds/mounts/inline4_horn.ogg",
               "assets/purxiy/sounds/mounts/inline4_idle.ogg",
               "assets/purxiy/sounds/mounts/inline4_low.ogg",
               "assets/purxiy/sounds/mounts/inline4_pop.ogg",
               "assets/purxiy/sounds/mounts/inline4_shift.ogg",
               "assets/purxiy/sounds/mounts/inline4_start.ogg",
               "assets/purxiy/sounds/mounts/single_high.ogg",
               "assets/purxiy/sounds/mounts/single_horn.ogg",
               "assets/purxiy/sounds/mounts/single_idle.ogg",
               "assets/purxiy/sounds/mounts/single_low.ogg",
               "assets/purxiy/sounds/mounts/single_pop.ogg",
               "assets/purxiy/sounds/mounts/single_shift.ogg",
               "assets/purxiy/sounds/mounts/single_start.ogg",
               "assets/purxiy/sounds/mounts/straight8_high.ogg",
               "assets/purxiy/sounds/mounts/straight8_horn.ogg",
               "assets/purxiy/sounds/mounts/straight8_idle.ogg",
               "assets/purxiy/sounds/mounts/straight8_low.ogg",
               "assets/purxiy/sounds/mounts/straight8_pop.ogg",
               "assets/purxiy/sounds/mounts/straight8_shift.ogg",
               "assets/purxiy/sounds/mounts/straight8_start.ogg",
               "assets/purxiy/sounds/mounts/v12_high.ogg",
               "assets/purxiy/sounds/mounts/v12_horn.ogg",
               "assets/purxiy/sounds/mounts/v12_idle.ogg",
               "assets/purxiy/sounds/mounts/v12_low.ogg",
               "assets/purxiy/sounds/mounts/v12_pop.ogg",
               "assets/purxiy/sounds/mounts/v12_shift.ogg",
               "assets/purxiy/sounds/mounts/v12_start.ogg",
               "assets/purxiy/sounds/mounts/v8turbo_high.ogg",
               "assets/purxiy/sounds/mounts/v8turbo_horn.ogg",
               "assets/purxiy/sounds/mounts/v8turbo_idle.ogg",
               "assets/purxiy/sounds/mounts/v8turbo_low.ogg",
               "assets/purxiy/sounds/mounts/v8turbo_pop.ogg",
               "assets/purxiy/sounds/mounts/v8turbo_shift.ogg",
               "assets/purxiy/sounds/mounts/v8turbo_start.ogg"
            )
         )
      );
      var1.own(ClientLoader.install(var1, "assets/purxiy/sound-events-mount.json"));
      MountRenderer4.K = this;
      MountRenderer4.g = var1;
      var1.metric("drawn", 0.0);
      var1.metric("world_drawn", 0.0);
      var1.metric("draw_failures", 0.0);
      var1.own(() -> {
         try {
            MountUpdater4.clear();
            MountRenderer10.closeOwned();
         } finally {
            try {
               MountSupport4.close();
               MountRenderer5.close();
               MountFactory2.get().closeOwned();
            } finally {
               MountRenderer12.releasePipelines();
               MountRenderer5.releasePipelines();
               MountRenderer6.releasePipelines();
               MountRenderer7.releasePipelines();
               MountRenderer4.K = null;
               MountRenderer4.g = null;
            }
         }
      });
      var1.own(ClientRenderer5.install(new dev.purxiy.loader.fabric.client.ClientRenderer5.Listener() {
         public void cameraPosition(class_1297 var1, boolean var2, float var3, double[] var4) {
            MountRenderer4.cameraPosition(var1, var2, var3, var4);
         }

         public void cameraRotation(class_1297 var1, float var2, float[] var3) {
            MountRenderer4.cameraRotation(var1, var2, var3);
         }

         public float cameraDistance(class_1297 var1, boolean var2, float var3, float var4) {
            return MountRenderer4.cameraDistance(var1, var2, var3, var4);
         }

         public void avatarState(class_11890 var1, class_10055 var2, float var3) {
            MountRenderer4.avatarState(var1, var2, var3);
         }

         public class_243 avatarOffset(class_10055 var1, class_243 var2) {
            return MountRenderer4.avatarOffset(var1, var2);
         }

         public void avatarAttitude(class_10055 var1, class_4587 var2) {
            MountRenderer4.avatarAttitude(var1, var2);
         }

         public void humanoid(class_572<?> var1, class_10034 var2) {
            MountRenderer4.humanoid(var1, var2);
         }

         public class_238 bounds(class_1297 var1, class_238 var2) {
            return MountRenderer4.bounds(var1, var2);
         }

         public class_241 drivingInput(class_241 var1) {
            return MountRenderer4.drivingInput(var1);
         }

         public class_241 moveInput(class_310 var1, class_241 var2, class_10185 var3) {
            return MountRenderer4.moveInput(var1, var2, var3);
         }

         public class_10185 keyInput(class_310 var1, class_10185 var2) {
            return MountRenderer4.keyInput(var1, var2);
         }

         public void worldStart(class_4184 var1, Matrix4f var2, Matrix4f var3) {
            MountRenderer4.worldStart(var1, var2, var3);
         }

         public void worldEnd() {
            MountRenderer4.worldEnd();
         }

         public boolean cockpit() {
            return MountRenderer4.cockpit();
         }

         public boolean capturing() {
            return MountRenderer4.capturing();
         }

         public boolean applies(class_1297 var1) {
            return MountRenderer4.applies(var1);
         }

         public class_243 view(class_1297 var1) {
            return MountRenderer4.view(var1);
         }

         public class_243 eye(class_1297 var1, class_243 var2) {
            return MountRenderer4.eye(var1, var2);
         }

         public class_239 pick(class_1297 var1, float var2, Supplier<class_239> var3) {
            return MountRenderer4.pick(var1, var2, var3);
         }

         public boolean inClosedCabin(class_10055 var1) {
            return MountRenderer4.inClosedCabin(var1);
         }

         public void render(WorldRenderContext var1) {
            MountRenderer4.render(var1);
         }

         public void renderSheets() {
            MountRenderer4.renderSheets();
         }

         public float fov(float var1) {
            return MountRenderer4.fov(var1);
         }

         public double mouseScale() {
            return MountRenderer4.mouseScale();
         }

         public boolean touchAttack() {
            return MountRenderer4.touchAttack();
         }

         public boolean touchUse() {
            return MountRenderer4.touchUse();
         }

         public boolean blockAttackHold() {
            return MountRenderer4.blockAttackHold();
         }

         public void keyState(class_304 var1, boolean var2) {
            MountRenderer4.keyState(var1, var2);
         }

         public boolean skipSecondaryFrame() {
            return MountRenderer4.skipSecondaryFrame();
         }

         public void mainFrame(Args var1) {
            MountRenderer4.mainFrame(var1);
         }
      }));
      var1.own(ClientRegistry26.install(new dev.purxiy.loader.fabric.client.ClientRegistry26.Listener() {
         public boolean inClosedCabin(class_10055 var1) {
            return MountRenderer5.inClosedCabin(var1);
         }
      }));
      var1.own(ModuleWidget.onTick(var0 -> {
         MountUpdater3.tick();
         MountUpdater4.tick(var0);
         MountRenderer10.tick(var0);
      }));
      var1.own(ModuleWidget.interceptKey(80, (var1x, var2, var3) -> this.onKey(var1x, var3)));
      var1.own(ModuleWidget.onFov(30, var0 -> MountSupport3.fov((float)var0)));
      var1.own(ModuleWidget.onMouseScale(30, var0 -> var0 * MountSupport3.mouseScale()));
      var1.own(ModuleWidget.claim("mount", MountUpdater4::mounted));
      var1.own(ModuleWidget.claim("secondary-world-view", MountRenderer13::capturing));
      var1.own(ModuleWidget.onDisconnect(var0 -> {
         MountUpdater4.clear();
         MountUpdater.stop();
      }));
      var1.onFrame(var1xx -> {
         MountUpdater4.frame((float)var1xx);
         MountSupport3.frame();
         var1.metric("mounted", MountUpdater4.mounted() ? 1.0 : 0.0);
         var1.metric("visible", MountUpdater4.visible() ? 1.0 : 0.0);
      });
      var1.onHud(var0 -> {
         if (ClientServices.drawing()) {
            MountRenderer8.render();
         }
      });
   }

   public static void releasePipelines() {
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new MountRenderer11(this);
   }
}
