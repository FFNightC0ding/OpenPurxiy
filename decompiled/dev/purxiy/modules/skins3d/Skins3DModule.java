package dev.purxiy.modules.skins3d;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.IntegerSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientBuilder;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.ClientLoader;
import dev.purxiy.loader.fabric.client.ClientRegistry24;
import dev.purxiy.loader.fabric.client.ClientRenderer9;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import dev.purxiy.loader.fabric.client.ClientTransform;
import dev.purxiy.loader.fabric.client.ClientBuilder.Listener2;
import dev.purxiy.loader.fabric.client.ClientBuilder.TypeE;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import java.nio.ByteBuffer;
import java.util.List;
import net.minecraft.class_10055;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_591;
import net.minecraft.class_630;
import net.minecraft.class_742;
import net.minecraft.class_8685;
import org.joml.Matrix4f;

public final class Skins3DModule extends ModuleDescriptor {
   public final ModeSetting B = this.mode("skin", "Skin", "What you wear", SkinPreset.n.N, SkinPreset.labels());
   public final BooleanSetting M = this.bool("size", "Own size", "Each skin its own height and build, only for you", true);
   public final BooleanSetting C = this.bool("hide_armor", "Hide armor", "Your armor is not drawn over your skin in third person", false);
   public final NumberSetting b = this.section("3D layer")
      .number("depth", "Depth", "How far the outer layer stands out", 1.0, 0.5, 1.5, 0.05, Listener.MULTIPLIER);
   public final BooleanSetting v = this.bool("others", "Other players", "Everyone's skin in 3D, not only yours", true);
   public final BooleanSetting g = this.bool("first_person", "First person", "Your sleeves in 3D on your hands", true);
   public final BooleanSetting E = this.section("UwU Girl")
      .bool("uwu_voice", "UwU voice", "Occasionally says uwu, more often at three hunger icons or less", true);
   public final NumberSetting x = this.number("uwu_volume", "Voice volume", "Also follows the game's Players volume", 0.55, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final IntegerSetting j = this.section("Spider-Man")
      .keybind("mask_key", "Mask key", "Take off and throw the mask; press again nearby to pick it up and wear it");

   public Skins3DModule() {
      super("skins_3d", "3D Skins", "Real 3D skins, and a wardrobe of new ones", "shirt");
      this.E.visibleWhen(() -> this.preset() == SkinPreset.I);
      this.x.visibleWhen(() -> {
         SkinPreset.ensureSourceClassInitialized();
         return this.preset() == SkinPreset.I && this.E.enabled();
      });
      this.j.visibleWhen(() -> this.preset() == SkinPreset.B);
   }

   public SkinPreset preset() {
      return SkinPreset.byLabel((String)this.B.get());
   }

   protected void onDisable() {
      Skins3DUpdater.cancelAction();
      Skins3DUpdater3.stop(class_310.method_1551());
      Skins3DTransform3.release();
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientRegistry24.install(
            var1,
            List.of(
               "assets/purxiy/models/skins3d/mario.vxm",
               "assets/purxiy/models/skins3d/peter_griffin.vxm",
               "assets/purxiy/models/skins3d/pikachu.vxm",
               "assets/purxiy/models/skins3d/spiderman.vxm",
               "assets/purxiy/models/skins3d/spiderman_mask_dropped.vxm",
               "assets/purxiy/models/skins3d/spiderman_mask_shell.vxm",
               "assets/purxiy/models/skins3d/spiderman_unmasked.vxm",
               "assets/purxiy/models/skins3d/spongebob.vxm",
               "assets/purxiy/models/skins3d/uwu_girl.vxm",
               "assets/purxiy/sounds/skins3d/uwu.ogg",
               "assets/purxiy/textures/skins3d/egirl.png",
               "assets/purxiy/textures/skins3d/mario.png",
               "assets/purxiy/textures/skins3d/peter_griffin.png",
               "assets/purxiy/textures/skins3d/pikachu.png",
               "assets/purxiy/textures/skins3d/shinobi.png",
               "assets/purxiy/textures/skins3d/spiderman.png",
               "assets/purxiy/textures/skins3d/spiderman_unmasked.png",
               "assets/purxiy/textures/skins3d/spongebob.png",
               "assets/purxiy/textures/skins3d/stargazer.png",
               "assets/purxiy/textures/skins3d/uwu_girl.png"
            )
         )
      );
      var1.own(ClientLoader.install(var1, "assets/purxiy/sound-events-skins3d.json"));
      var1.own(
         ClientCodec.install(
            var1, List.of("shaders/core/mount.fsh", "shaders/core/mount.vsh", "shaders/core/skins3d_stage.fsh", "shaders/core/skins3d_stage.vsh")
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      Skins3DTransform3.install(this, var1);
      var1.own(Skins3DTransform3::uninstall);
      var1.own(ClientTransform.install(new dev.purxiy.loader.fabric.client.ClientTransform.Listener() {
         public void pose(class_591 var1, class_10055 var2) {
            Skins3DTransform3.pose(var1, var2);
         }

         public void hand(class_591 var1, class_2960 var2, class_630 var3) {
            Skins3DTransform3.hand(var1, var2, var3);
         }

         public void handPose(class_630 var1) {
            Skins3DTransform3.handPose(var1);
         }

         public void hideArmor(Object var1, class_10055 var2) {
            Skins3DTransform3.hideArmor(var1, var2);
         }

         public class_8685 wear(class_742 var1, class_8685 var2) {
            return Skins3DTransform3.wear(var1, var2);
         }

         public void size(Object var1, class_10055 var2) {
            Skins3DTransform3.size(var1, var2);
         }

         public void shape(class_591 var1, class_10055 var2) {
            Skins3DTransform3.shape(var1, var2);
         }

         public void frame() {
            Skins3DTransform3.frame();
         }

         public void reloaded() {
            Skins3DTransform3.reloaded();
         }
      }));
      Skins3DTransform3.equipment(ClientRenderer9.equipment());
      var1.own(ClientBuilder.model(new Listener2() {
         private static final String stringState40 = "Invalid avatar part";

         public TypeE body(class_8685 var1) {
            Skins3DBuilder.TypeR var2 = Skins3DTransform3.layers(var1);
            return var2 == null ? null : new TypeE(var1, var2.C, var2.n, var2.y, Skins3DTransform3.layer(var2.R[0]));
         }

         public int triangles(ByteBuffer var1, TypeE var2, int var3, Matrix4f var4) {
            if (var3 >= 0 && var3 < 6) {
               Skins3DBuilder.TypeR var5 = Skins3DTransform3.layers(var2.skin());
               return var5 == null ? 0 : Skins3DTransform3.triangles(var1, var5.R[var3], var4);
            } else {
               throw new IllegalArgumentException("Invalid avatar part");
            }
         }

         public void build(Matrix4f var1, int var2) {
            Skins3DTransform3.build(var1, var2);
         }

         public float height() {
            return Skins3DTransform3.yourHeight();
         }

         public float width() {
            return Skins3DTransform3.bodyWidth();
         }

         public float armSpread() {
            return Skins3DTransform3.armSpread();
         }
      }));
      var1.own(ClientRenderer9.paint(Skins3DTransform3::paintPart));
      var1.own(ClientRenderer9.extract(Skins3DTransform::extract));
      var1.own(ClientRenderer9.world(Skins3DRenderer3::render));
      var1.own(ClientBuilder.sculpted(new dev.purxiy.loader.fabric.client.ClientBuilder.Listener() {
         public float[] bounds(class_630 var1) {
            return Skins3DTransform3.bounds(var1);
         }

         public int accent() {
            SkinPreset var1x = Skins3DModule.this.preset();
            return var1x == SkinPreset.n ? 0 : var1x.y;
         }
      }));
      var1.own(ClientSupport3.BODY_HEIGHT.subscribe(0, Skins3DTransform3::drawnHeight));
      var1.own(CosmeticRegistry.register(this.id(), this.getClass().getClassLoader(), () -> new Skins3DScreen(this)));
      var1.own(Skins3DRenderer2::release);
      var1.own(ModuleWidget.onTick(var0 -> {
         Skins3DUpdater.tick(var0);
         Skins3DUpdater3.tick(var0);
      }));
      var1.own(ModuleWidget.onDisconnect(var0 -> {
         Skins3DUpdater.leave();
         Skins3DUpdater3.stop(var0);
         Skins3DTransform.clear();
      }));
      var1.own(ModuleWidget.onKey((var0, var1x, var2) -> {
         SkinPreset.ensureSourceClassInitialized();
         if (var2 == 1) {
            Skins3DUpdater.onKey(var0);
         }
      }));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new Skins3DRenderer4(this);
   }
}
