package dev.purxiy.modules.glass;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.purxiy.anim.Easing;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ClientTheme;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.ClientRegistry22;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import dev.purxiy.ui.clickgui.Studio;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.class_10017;
import net.minecraft.class_10055;
import net.minecraft.class_11659;
import net.minecraft.class_11890;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_591;
import org.joml.Matrix4fc;

public final class GlassModule extends ModuleDescriptor {
   public final ColorSetting o = this.section("Look").color("color", "Colour", "White glass, or tinted any colour you like", -1, false);
   public final NumberSetting j = this.number("frost", "Frosted", "Clear as a window, or soft and milky", 0.35, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final NumberSetting G = this.number("glow", "Glow", "How much light the glass gives off", 0.2, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final BooleanSetting I = this.bool("shimmer", "Shimmer", "Now and then a soft sheen glides over the glass", true);
   public final BooleanSetting B = this.section("What turns to glass").bool("self", "You", "Your body in third person, your arm in first person", true);
   public final BooleanSetting Y = this.bool("players", "Other players", "Everyone else around you", true);
   public final BooleanSetting H = this.bool("items", "Items", "Held, dropped and in item frames", true);
   public final BooleanSetting r = this.bool("armour", "Armour", "Worn armour too (off: it keeps its own look)", true);

   public GlassModule() {
      super("glass", "Glass", "Players and items made of glass", "gem");
   }

   protected void onDisable() {
      GlassRegistry.clear();
   }

   public Studio studio() {
      return GlassRenderer4.of(this);
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/glass.fsh",
               "shaders/core/glass.vsh",
               "shaders/core/glass_halo.fsh",
               "shaders/core/glass_halo.vsh",
               "shaders/core/glass_stage.fsh",
               "shaders/include/glass.glsl",
               "shaders/include/theme.glsl",
               "shaders/include/tint.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      GlassRegistry.install(this, var1);
      var1.own(GlassRegistry::uninstall);
      var1.own(ClientTheme.install(new dev.purxiy.loader.fabric.ClientTheme.Listener() {
         public boolean active() {
            return GlassSupport2.active();
         }

         public float fov(float var1) {
            return GlassSupport2.fov(var1);
         }

         public boolean hides(class_1297 var1) {
            return GlassSupport2.hides(var1);
         }

         public void reset() {
            GlassRenderer4.closeOwned();
         }

         public boolean place(class_4184 var1, class_1937 var2, class_1297 var3, float var4, double[] var5, float[] var6) {
            return GlassSupport2.place(var1, var2, var3, var4, var5, var6);
         }

         public void pose(class_11890 var1, class_10055 var2) {
            if (GlassSupport2.active() && var1 == class_310.method_1551().field_1724) {
               float var3 = Easing.SMOOTHSTEP.apply(Math.clamp((GlassSupport2.progress() - 0.15F) / 0.6F, 0.0F, 1.0F));
               var2.field_53447 = class_3532.method_16439(var3, var2.field_53447, 0.0F);
               var2.field_53448 = class_3532.method_16439(var3, var2.field_53448, -2.0F);
            }
         }
      }));
      var1.own(ModuleWidget.claim("preview-glass", () -> GlassRenderer4.showing(this)));
      var1.own(ModuleWidget.onTick(GlassRenderer4::tick));
      var1.own(GlassRenderer4::closeOwned);
      var1.own(CosmeticRegistry.register(this.id(), this.getClass().getClassLoader(), () -> {
         ClientTheme.resetAll();
         return new GlassSupport5(this);
      }));
      var1.own(GlassSupport3::releaseResources);
      var1.own(GlassRenderer::releaseResources);
      var1.own(ClientRegistry22.install(new dev.purxiy.loader.fabric.client.ClientRegistry22.Listener() {
         public void beginFrame(class_4184 var1, Matrix4fc var2, Matrix4fc var3, GpuBufferSlice var4) {
            GlassRegistry.beginFrame(var1, var2, var3, var4);
         }

         public void playerModel(class_591 var1) {
            GlassRegistry.playerModel(var1);
         }

         public void extract(class_1297 var1, class_10017 var2) {
            GlassRegistry.extract(var1, var2);
         }

         public class_11659 collector(class_10017 var1, class_11659 var2) {
            return GlassRegistry.collector(var1, var2);
         }

         public void enterItem() {
            GlassRegistry.enterItem();
         }

         public void exitItem() {
            GlassRegistry.exitItem();
         }

         public void hudProjection(int var1, int var2, float var3) {
            GlassRegistry.hudProjection(var1, var2, var3);
         }

         public void beginHands(int var1) {
            GlassRegistry.beginHands(var1);
         }

         public class_11659 handCollector(class_11659 var1) {
            return GlassRegistry.handCollector(var1);
         }

         public void renderWorld() {
            GlassRegistry.renderWorld();
         }

         public void renderHand() {
            GlassRegistry.renderHand();
         }
      }));
      var1.own(ClientSupport3.DEPTH.subscribe(0, (Consumer<Boolean>)var0 -> {
         if (var0) {
            GlassRegistry.beforeTranslucent();
         }
      }));
      var1.own(ClientSupport3.AFTER_WORLD.subscribe(50, GlassRegistry::renderWorld));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new GlassRenderer2(this);
   }
}
