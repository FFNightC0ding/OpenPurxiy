package dev.purxiy.modules.place;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ChunkRenderProvider;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.client.SectionLightSnapshot;
import java.util.Locale;
import net.minecraft.class_11658;
import net.minecraft.class_11661;
import net.minecraft.class_1269;
import net.minecraft.class_1750;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2804;
import net.minecraft.class_4587;
import net.minecraft.class_7522;

public final class PlaceAnimationModule extends ModuleDescriptor {
   public static final String S = "Pop";
   public static final String j = "Grow";
   public static final String r = "Twist";
   public static final String X = "Drop";
   public final ModeSetting o = this.section("Animation")
      .mode("style", "Style", "How a block comes into place", "Pop", new String[]{"Pop", "Grow", "Twist", "Drop"});
   public final NumberSetting l = this.number(
      "duration", "Duration", "How long a block takes to settle", 0.34, 0.12, 1.0, 0.02, var0 -> String.format(Locale.ROOT, "%.2fs", var0)
   );
   public final NumberSetting J = this.number(
      "bounce", "Bounce", "How far it springs past its full size before it settles", 0.5, 0.0, 1.0, 0.05, Listener.PERCENT
   );
   public final NumberSetting v = this.section("Look").number("glow", "Glow", "Soft light on the block as it appears", 0.6, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final ModeSetting D = this.mode("palette", "Palette", "The colours of the glow", "Accent", PlaceAnimationLoader.names())
      .renamed(PlaceAnimationLoader.k);
   public final ColorSetting q = this.color("color", "Colour", "The rest of the palette is derived from it", -4944641, false).followsAccent();
   public final BooleanSetting L = this.section("Which blocks").bool("breaking", "Broken blocks", "Blocks you break shrink away", true);
   public final BooleanSetting A = this.bool("others", "Other players", "Blocks other players place grow in too", false);

   public PlaceAnimationModule() {
      super("place_animation", "Place Animation", "Blocks grow into place as you build", "boxes");
      this.q.visibleWhen(() -> this.D.is("Custom"));
   }

   public PlaceMotion.Style motionStyle() {
      String var1 = (String)this.o.get();

      return switch (var1) {
         case "Grow" -> PlaceMotion.Style.C;
         case "Twist" -> PlaceMotion.Style.z;
         case "Drop" -> PlaceMotion.Style.y;
         default -> PlaceMotion.Style.I;
      };
   }

   protected void onDisable() {
      PlaceAnimationSupport.clear();
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ClientServices.acquire(this.id()));
      PlaceAnimationSupport.install(this, var1);
      var1.own(PlaceAnimationSupport::uninstall);
      var1.own(ChunkRenderProvider.install(new dev.purxiy.loader.fabric.client.ChunkRenderProvider.Listener() {
         public void placeStarted(class_1750 var1) {
            PlaceAnimationSupport.placeStarted(var1);
         }

         public void placeEnded(class_1750 var1, class_1269 var2) {
            PlaceAnimationSupport.placeEnded(var1, var2);
         }

         public void blockChanged(class_2338 var1, class_2680 var2, class_2680 var3) {
            PlaceAnimationSupport.blockChanged(var1, var2, var3);
         }

         public void destroyStarted(class_2338 var1) {
            PlaceAnimationSupport.destroyStarted(var1);
         }

         public void destroyEnded(boolean var1) {
            PlaceAnimationSupport.destroyEnded(var1);
         }

         public void serverUpdateStarted(class_2338 var1) {
            PlaceAnimationSupport.serverUpdateStarted(var1);
         }

         public void serverUpdateEnded(class_2338 var1) {
            PlaceAnimationSupport.serverUpdateEnded(var1);
         }

         public void hideIn(class_7522<class_2680> var1, int var2, int var3, int var4) {
            PlaceAnimationSupport.hideIn(var1, var2, var3, var4);
         }

         public SectionLightSnapshot lightIn(int var1, int var2, int var3) {
            return PlaceAnimationSupport.lightIn(var1, var2, var3);
         }

         public void lightIn(class_2804[] var1, int var2, int var3, int var4) {
            PlaceAnimationSupport.lightIn(var1, var2, var3, var4);
         }

         public void sectionCopied(int var1, int var2, int var3) {
            PlaceAnimationSupport.sectionCopied(var1, var2, var3);
         }

         public void sectionMeshed(int var1, int var2, int var3) {
            PlaceAnimationSupport.sectionMeshed(var1, var2, var3);
         }

         public void beginFrame() {
            PlaceAnimationSupport.beginFrame();
         }

         public void submit(class_4587 var1, class_11658 var2, class_11661 var3) {
            PlaceAnimationSupport.submit(var1, var2, var3);
         }

         public boolean follow(class_2338 var1, double[] var2) {
            return PlaceAnimationSupport.follow(var1, var2);
         }

         public boolean follow(class_2338 var1, class_4587 var2, double var3, double var5, double var7) {
            return PlaceAnimationSupport.follow(var1, var2, var3, var5, var7);
         }
      }));
      var1.own(SkyPipelineRouter.BLOCK_BOUNDS.subscribe(0, PlaceAnimationSupport::follow));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new PlaceAnimationRenderer(this);
   }
}
