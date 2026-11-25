package dev.purxiy.modules.hand;

import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.IntegerSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientApi2;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.ClientRegistry17;
import dev.purxiy.loader.fabric.client.ClientRegistry19;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import dev.purxiy.loader.fabric.client.ClientSupport3.Listener7;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import dev.purxiy.ui.clickgui.Studio;
import java.util.List;
import java.util.Locale;
import java.util.function.BooleanSupplier;
import net.minecraft.class_11659;
import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_746;
import org.joml.Matrix4f;

public final class HandMovementModule extends ModuleDescriptor {
   public static final String x = "Main hand";
   public static final String c = "Off hand";
   public static final String g = "Vanilla";
   public static final String Y = "Smooth";
   public static final String v = "Chop";
   public static final String J = "Slash";
   public static final String y = "Punch";
   public static final String h = "Vanilla";
   public static final String b = "Smooth";
   public static final String j = "Instant";
   private static final Listener i = var0x -> {
      long var2 = 130862439952554L;
      return Math.abs(var0x) < 0.005 ? "0" : String.format(Locale.ROOT, "%+.2f", var0x).replace('-', '−');
   };
   private static final Listener V = var0x -> (Math.abs(var0x) < 0.5 ? "0" : String.format(Locale.ROOT, "%+d", Math.round(var0x)).replace('-', '−')) + "°";
   public final ModeSetting F = this.section("Placement")
      .mode("preset", "Placement", "A starting point: the studio fine-tunes it", "Vanilla", HandMovementCatalog.names());
   public final ModeSetting H = this.mode("editing", "Editing", "Which hand the sliders move", "Main hand", new String[]{"Main hand", "Off hand"});
   public final BooleanSetting A = this.bool("mirror", "Mirror off hand", "Off hand follows, mirrored", true);
   public final HandMovementModule.HandPlacement p = new HandMovementModule.HandPlacement(this, "main");
   public final HandMovementModule.HandPlacement a = new HandMovementModule.HandPlacement(this, "off");
   public final ModeSetting m = this.section("Swing")
      .mode("swing", "Style", "How you hit, chop and mine", "Smooth", new String[]{"Vanilla", "Smooth", "Chop", "Slash", "Punch"});
   public final NumberSetting q = this.number("swing_speed", "Speed", "How quick it looks (only to you)", 1.0, 0.5, 2.0, 0.05, Listener.MULTIPLIER);
   public final NumberSetting k = this.number("swing_amount", "Intensity", "How far your hand swings", 1.0, 0.0, 1.5, 0.05, Listener.PERCENT);
   public final BooleanSetting N = this.section("Swing blur").bool("blur", "Swing blur", "A clean trail as you chop or hit", true);
   public final NumberSetting u = this.number("blur_length", "Length", "How far back the trail reaches", 0.35, 0.1, 1.0, 0.05, Listener.PERCENT);
   public final NumberSetting D = this.number("blur_strength", "Strength", "How clearly the trail shows", 0.6, 0.1, 1.0, 0.05, Listener.PERCENT);
   public final BooleanSetting K = this.bool("blur_radiance", "Radiance light", "With Radiance, the trail glows too", true);
   public final NumberSetting t = this.section("Movement").number("sway", "Sway", "Your hand trails as you look around", 1.0, 0.0, 2.0, 0.05, Listener.PERCENT);
   public final NumberSetting C = this.number("bobbing", "Bobbing", "As you walk (with View Bobbing on)", 1.0, 0.0, 1.5, 0.05, Listener.PERCENT);
   public final NumberSetting n = this.number("momentum", "Momentum", "Leans as you strafe, dips as you land", 0.5, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final NumberSetting G = this.number("breathing", "Breathing", "A slow float while you stand still", 0.4, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final ModeSetting r = this.section("Items")
      .mode("equip", "Switching", "How a new item comes up", "Smooth", new String[]{"Vanilla", "Smooth", "Instant"});
   public final BooleanSetting l = this.bool("cooldown_dip", "Cooldown dip", "Lowered while your attack recharges", true);
   public final BooleanSetting O = this.bool("empty_hand", "Empty hand", "Show your arm when you hold nothing", true);
   public final IntegerSetting U = this.keybind("studio_key", "Studio key", "Opens the Hand Studio in game");
   private final HandMovementRenderer2 Q = new HandMovementRenderer2(this);
   public static int s = 0;
   private static final long[] f = new long[]{6107629626829720024L, 8162663087821625729L, 126987695618089703L, -4415572070856986426L};

   public HandMovementModule() {
      super("hand_movement", "Hand Movement", "Place, swing and move your hand your way", "hand");
      this.H.visibleWhen(() -> {
         SwingStyle.ensureSourceClassInitialized();
         return !this.A.enabled();
      });
      this.p.visibleWhen(() -> {
         SwingStyle.ensureSourceClassInitialized();
         return this.A.enabled() || this.H.is("Main hand");
      });
      this.a.visibleWhen(() -> {
         SwingStyle.ensureSourceClassInitialized();
         return !this.A.enabled() && this.H.is("Off hand");
      });
      this.u.visibleWhen(this.N::enabled);
      this.D.visibleWhen(this.N::enabled);
      this.K.visibleWhen(this.N::enabled);
      this.enabledByDefault();
   }

   public Studio studio() {
      return this.Q;
   }

   public void onKey(int var1) {
      if (this.U.matches(var1)) {
         CosmeticRegistry.open(this.id());
      }
   }

   public void choosePreset(String var1) {
      HandMovementCatalog.Data var2 = HandMovementCatalog.byName(var1);
      if (var2 != null) {
         this.edited().set(var2.values());
         this.F.set(var1);
      }
   }

   public HandMovementModule.HandPlacement offPlacement() {
      return this.A.enabled() ? this.p : this.a;
   }

   public HandMovementModule.HandPlacement edited() {
      return !this.A.enabled() && !this.H.is("Main hand") ? this.a : this.p;
   }

   public SwingStyle swingStyle() {
      String var1 = (String)this.m.get();

      return switch (var1) {
         case "Vanilla" -> SwingStyle.m;
         case "Chop" -> SwingStyle.v;
         case "Slash" -> SwingStyle.Q;
         case "Punch" -> SwingStyle.a;
         default -> SwingStyle.f;
      };
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/hand_trail.fsh",
               "shaders/core/hand_trail.vsh",
               "shaders/core/hand_trail_composite.fsh",
               "shaders/core/hand_trail_composite.vsh",
               "shaders/include/hand_trail.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      HandMovementSupport4.install(this, var1);
      var1.own(HandMovementSupport4::uninstall);
      var1.own(HandMovementSupport2::releaseResources);
      var1.own(HandMovementSupport3::releaseResources);
      var1.own(HandMovementRenderer3::releaseResources);
      var1.own(ClientRegistry17.install(new dev.purxiy.loader.fabric.client.ClientRegistry17.Listener() {
         public boolean active() {
            return HandMovementSupport4.active();
         }

         public boolean swingsOwned() {
            return HandMovementSupport4.swingsOwned();
         }

         public double clock() {
            return HandMovementSupport4.clock();
         }

         public void frame() {
            HandMovementSupport4.frame();
         }

         public void onSwing(class_1268 var1, int var2) {
            HandMovementSupport4.onSwing(var1, var2);
         }

         public boolean swingingWithin(class_1268 var1, float var2) {
            return HandMovementSupport4.swingingWithin(var1, var2);
         }

         public class_1268 lastSwung() {
            return HandMovementSupport4.lastSwung();
         }

         public float swingSeconds(class_1268 var1) {
            return HandMovementSupport4.swingSeconds(var1);
         }

         public void beginHands(class_4587 var1, float var2, class_746 var3) {
            HandMovementSupport4.beginHands(var1, var2, var3);
         }

         public float swayPitch(float var1) {
            return HandMovementSupport4.swayPitch(var1);
         }

         public float swayYaw(float var1) {
            return HandMovementSupport4.swayYaw(var1);
         }

         public void place(class_4587 var1, class_1268 var2, int var3) {
            HandMovementSupport4.place(var1, var2, var3);
         }

         public Matrix4f placement(class_1268 var1, int var2, float var3, Matrix4f var4) {
            return HandMovementSupport4.placement(var1, var2, var3, var4);
         }

         public float equip(class_1268 var1, float var2) {
            return HandMovementSupport4.equip(var1, var2);
         }

         public boolean instantSwitch() {
            return HandMovementSupport4.instantSwitch();
         }

         public boolean cooldownDip() {
            return HandMovementSupport4.cooldownDip();
         }

         public boolean emptyHandShown() {
            return HandMovementSupport4.emptyHandShown();
         }

         public boolean swingItem(class_4587 var1, class_1268 var2, int var3) {
            return HandMovementSupport4.swingItem(var1, var2, var3);
         }

         public void armPose(class_4587 var1, class_1268 var2, int var3, float var4) {
            HandMovementSupport4.armPose(var1, var2, var3, var4);
         }

         public class_1799 preview(class_1268 var1) {
            return HandMovementSupport4.preview(var1);
         }

         public float studioLowered(class_1799 var1) {
            return HandMovementSupport4.studioLowered(var1);
         }

         public void traceLowered(float var1) {
            HandMovementSupport4.traceLowered(var1);
         }
      }));
      var1.own(ClientRegistry19.install(new dev.purxiy.loader.fabric.client.ClientRegistry19.Listener() {
         public void hudProjection(int var1, int var2, float var3) {
            HandMovementSupport2.hudProjection(var1, var2, var3);
         }

         public float fov() {
            return HandMovementSupport2.fov();
         }

         public void beginHands(int var1, boolean var2) {
            HandMovementSupport2.beginHands(var1, var2);
         }

         public class_11659 collector(class_1268 var1, class_11659 var2) {
            return HandMovementSupport2.collector(var1, var2);
         }

         public void endHand() {
            HandMovementSupport2.endHand();
         }

         public void sweeps(boolean var1) {
            HandMovementSupport2.sweeps(var1);
         }

         public void sweep(class_1268 var1, ClientApi2 var2) {
            HandMovementSupport2.sweep(var1, var2);
         }

         public void composite() {
            HandMovementSupport2.composite();
         }
      }));
      var1.own(ClientSupport3.HAND_COMPOSITE.subscribe(0, HandMovementSupport2::composite));
      var1.own(ClientSupport3.FRAME_END.subscribe(0, HandMovementSupport2::frameEnded));
      var1.own(ClientSupport3.HAND_MASK.subscribe(0, new Listener7() {
         public void want() {
            HandMovementRenderer3.want();
         }

         public GpuTextureView draw() {
            return HandMovementRenderer3.draw();
         }
      }));
      var1.own(ModuleWidget.claim("hand-studio", HandMovementRenderer2::measuring));
      var1.own(CosmeticRegistry.register(this.id(), this.getClass().getClassLoader(), () -> new HandMovementScreen(this, this.Q)));
      var1.own(ModuleWidget.onKey((var1x, var2, var3) -> {
         if (var3 == 1 && class_310.method_1551().field_1755 == null) {
            this.onKey(var1x);
         }
      }));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new HandMovementRenderer5(this);
   }

   public static final class HandPlacement {
      public final NumberSetting V;
      public final NumberSetting t;
      public final NumberSetting i;
      public final NumberSetting r;
      public final NumberSetting I;
      public final NumberSetting Y;
      public final NumberSetting f;
      private static final String[] b = new String[21];

      HandPlacement(HandMovementModule var1, String var2) {
         this.V = var1.number(var2 + "_x", "Sideways", "Towards the middle, or out", 0.0, -0.6, 0.6, 0.01, HandMovementModule.i);
         this.t = var1.number(var2 + "_y", "Height", "Up, or down out of the way", 0.0, -0.6, 0.6, 0.01, HandMovementModule.i);
         this.i = var1.number(var2 + "_z", "Depth", "Closer, or further away", 0.0, -0.6, 0.6, 0.01, HandMovementModule.i);
         this.r = var1.number(var2 + "_pitch", "Tilt", "Tipped forwards or back", 0.0, -90.0, 90.0, 1.0, HandMovementModule.V);
         this.I = var1.number(var2 + "_yaw", "Turn", "Turned in or out", 0.0, -90.0, 90.0, 1.0, HandMovementModule.V);
         this.Y = var1.number(var2 + "_roll", "Roll", "Rolled around your view", 0.0, -90.0, 90.0, 1.0, HandMovementModule.V);
         this.f = var1.number(var2 + "_size", "Size", "Smaller or larger", 1.0, 0.4, 1.6, 0.01, Listener.PERCENT);
      }

      void visibleWhen(BooleanSupplier var1) {
         for (NumberSetting var5 : this.all()) {
            var5.visibleWhen(var1);
         }
      }

      public NumberSetting[] all() {
         return new NumberSetting[]{this.V, this.t, this.i, this.r, this.I, this.Y, this.f};
      }

      public boolean matches(double[] var1) {
         NumberSetting[] var2 = this.all();

         for (int var3 = 0; var3 < var2.length; var3++) {
            if (Math.abs((Double)var2[var3].get() - var1[var3]) > 1.0E-4) {
               return false;
            }
         }

         return true;
      }

      public void set(double[] var1) {
         NumberSetting[] var2 = this.all();

         for (int var3 = 0; var3 < var2.length; var3++) {
            var2[var3].set(var1[var3]);
         }
      }

      public void reset() {
         for (NumberSetting var4 : this.all()) {
            var4.reset();
         }
      }

      public boolean isDefault() {
         for (NumberSetting var4 : this.all()) {
            if (!var4.isDefault()) {
               return false;
            }
         }

         return true;
      }
   }
}
