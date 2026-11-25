package dev.purxiy.modules.outline;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter.FrameHook;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter.WorldEventSink;
import java.util.List;
import net.minecraft.class_12074;
import net.minecraft.class_1269;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_3965;

public final class BlockOutlineModule extends ModuleDescriptor {
   public final ModeSetting G = this.mode("style", "Style", "Every edge, or only the corners", "Outline", new String[]{"Outline", "Corners"});
   public final ColorSetting D = this.color("color", "Colour", null, -4907020, true).followsAccent();
   public final BooleanSetting N = this.bool("gradient", "Gradient", "Blend into a second colour towards the top", true);
   public final ColorSetting H = this.color("color2", "Second colour", null, -42279, true).followsAccent();
   public final NumberSetting B = this.number("width", "Line width", null, 2.0, 0.5, 5.0, 0.5, Listener.unit(" px"));
   public final NumberSetting h = this.number("glow", "Glow", "A soft halo of light around the lines", 0.7, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final NumberSetting g = this.number("fill", "Fill", "Tint the faces as well", 0.0, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final NumberSetting V = this.number(
      "aim_light", "Aim light", "A soft light that follows your crosshair over the block", 0.6, 0.0, 1.0, 0.05, Listener.PERCENT
   );
   public final BooleanSetting r = this.bool("glide", "Glide", "Slide smoothly from block to block", true);
   public final NumberSetting U = this.number(
      "speed", "Animation speed", "How fast the outline glides, fades and shimmers", 1.0, 0.25, 3.0, 0.05, Listener.MULTIPLIER
   );
   public final NumberSetting Z = this.number(
      "shimmer", "Shimmer", "Now and then, a sheen of light sweeps over the edges", 0.4, 0.0, 1.0, 0.05, Listener.PERCENT
   );
   public final BooleanSetting I = this.bool("mining", "Mining progress", "The outline draws itself in while you break a block", true);
   public final BooleanSetting v = this.bool("feedback", "Feedback", "A flash when you place a block, a ripple when one breaks", true);
   public final BooleanSetting R = this.bool("hidden_edges", "Hidden edges", "Faintly dashed edges behind the block, like a blueprint", false);
   public static int n = 0;

   public BlockOutlineModule() {
      super("block_outline", "Block Outline", "A cleaner outline for the block you target", "square-dashed");
      this.H.visibleWhen(this.N::enabled);
   }

   public boolean corners() {
      return this.G.is("Corners");
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ClientCodec.install(var1, List.of("shaders/core/outline.fsh", "shaders/core/outline.vsh", "shaders/include/outline.glsl")));
      var1.own(ClientServices.acquire(this.id()));
      BlockOutlineRenderer.install(this, var1);
      var1.own(BlockOutlineRenderer::uninstall);
      var1.own(SkyPipelineRouter.VIEW.subscribe(0, (FrameHook)(var0, var1x, var2, var3) -> BlockOutlineRenderer.beginFrame(var2)));
      var1.own(SkyPipelineRouter.OUTLINE.subscribe(0, new WorldEventSink() {
         public void extract(class_12074 var1) {
            BlockOutlineRenderer.extract(var1);
         }

         public boolean render(boolean var1, class_243 var2) {
            return BlockOutlineRenderer.render(var1, var2);
         }

         public void beforeBreak(class_2338 var1) {
            BlockOutlineRenderer.beforeBreak(var1);
         }

         public void afterBreak(class_2338 var1, boolean var2) {
            BlockOutlineRenderer.afterBreak(var1, var2);
         }

         public void beforeUse(class_3965 var1) {
            BlockOutlineRenderer.beforeUse(var1);
         }

         public void afterUse(class_3965 var1, class_1269 var2) {
            BlockOutlineRenderer.afterUse(var1, var2);
         }
      }));
   }

   public static void ensureSourceClassInitialized() {
   }
}
