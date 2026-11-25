package dev.purxiy.modules.chroma;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter.FrameHook;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import java.util.List;

public final class ChromaModule extends ModuleDescriptor {
   private static final int[] i = new int[]{30, 120, 600, 0};
   public final ModeSetting Z = this.section("The grey world").mode("tone", "Tone", "The shade the world waits in", "Lilac", ChromaKind.names());
   public final NumberSetting f = this.number("hint", "Hint of colour", "How much colour the grey world keeps", 0.06, 0.0, 0.5, 0.01, Listener.PERCENT);
   public final BooleanSetting p = this.bool("living", "Living colour", "Players, mobs and items keep their colour", true);
   public final NumberSetting s = this.section("Your brush")
      .number("brush", "Brush size", "How far colour blooms around you", 6.0, 3.0, 12.0, 0.5, Listener.unit(" blocks"));
   public final ModeSetting g = this.mode(
      "memory", "Memory", "How long colour stays where you have been", "2 min", new String[]{"30 s", "2 min", "10 min", "Forever"}
   );
   public final BooleanSetting L = this.bool("splashes", "Splashes", "Landings, hits and blasts splash colour", true);
   public final BooleanSetting o = this.bool("others", "Other players", "Colour blooms where they walk too", false);

   public ChromaModule() {
      super("chroma", "Chroma", "The world waits in grey, and colour blooms wherever you walk", "paintbrush");
   }

   public int memorySeconds() {
      int var1 = this.g.index();
      return var1 >= 0 && var1 < i.length ? i[var1] : 120;
   }

   private void h() {
      ChromaRenderPass.onEnable();
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/chroma.fsh",
               "shaders/core/chroma.vsh",
               "shaders/core/chroma_preview.fsh",
               "shaders/include/chroma.glsl",
               "shaders/include/chroma_common.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      ChromaRenderPass.install(this, var1);
      var1.own(ChromaRenderPass::uninstall);
      this.h();
      var1.own(ChromaRenderer::releaseResources);
      var1.own(SkyPipelineRouter.VIEW.subscribe(10, (FrameHook)(var0, var1x, var2, var3) -> ChromaRenderPass.beginFrame(var0, var1x, var2)));
      var1.own(SkyPipelineRouter.DEPTH.subscribe(10, ChromaRenderPass::keepDepth));
      var1.own(SkyPipelineRouter.AFTER_WORLD.subscribe(10, ChromaRenderPass::render));
      var1.own(SkyPipelineRouter.BLAST.subscribe(0, ChromaRenderPass::onBlast));
      var1.own(ModuleWidget.onActualAttack(ChromaRenderPass::onAttack));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new ChromaEditorPage(this);
   }
}
