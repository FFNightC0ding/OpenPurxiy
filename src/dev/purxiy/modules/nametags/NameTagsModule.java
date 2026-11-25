package dev.purxiy.modules.nametags;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.NametagCapturePipeline;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter.FrameHook;
import net.minecraft.class_10017;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_4587;

public final class NameTagsModule extends ModuleDescriptor {
   public final ModeSetting G = this.mode("style", "Style", "Frosted glass, a solid pill or only the name", "Glass", new String[]{"Glass", "Solid", "Minimal"});
   public final BooleanSetting d;
   public final ModeSetting w;
   public final BooleanSetting s;
   public final BooleanSetting V;
   public final BooleanSetting n;
   public final ModeSetting r;
   public final NumberSetting o;
   public final BooleanSetting O;
   public final NumberSetting N;
   public final NumberSetting t;
   public final NumberSetting C;
   public final ModeSetting f;
   public final BooleanSetting Q;
   public final ModeSetting F;
   public final NumberSetting I;

   public NameTagsModule() {
      super("name_tags", "Name Tags", "Clean tags for players, pets and the items around you", "tag");
      this.section("Players");
      this.d = this.bool("faces", "Faces", "Their face next to the name (mobs get an icon)", true);
      this.w = this.mode("health", "Health", "A line along the tag, or hearts as a number", "Bar", new String[]{"Off", "Bar", "Hearts"});
      this.s = this.bool("below_name", "Below name", "The line a server shows under names, like a score", true);
      this.V = this.bool("own", "Your own tag", "See your own tag in third person", true);
      this.section("Mobs & items");
      this.n = this.bool("mobs", "Named mobs", "Pets and every other mob with a name", true);
      this.r = this.mode("items", "Dropped items", "Name and amount above items on the ground", "Nearby", new String[]{"Off", "Looking at", "Nearby"});
      this.o = this.number(
         "item_range", "Item range", "How far away loaded item tags show, including through walls", 12.0, 4.0, 128.0, 1.0, Listener.unit(" blocks")
      );
      this.o.visibleWhen(() -> {
         TagPainter.ensureSourceClassInitialized();
         return !this.r.is("Off");
      });
      this.O = this.bool("piles", "Group piles", "Items lying together share one card", true);
      this.O.visibleWhen(() -> {
         TagPainter.ensureSourceClassInitialized();
         return !this.r.is("Off");
      });
      this.section("Look");
      this.N = this.number("size", "Size", null, 1.0, 0.5, 2.0, 0.05, Listener.PERCENT);
      this.t = this.number("shrink", "Shrink with distance", "At 0% every tag keeps the same size", 0.6, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.C = this.number("opacity", "Background opacity", null, 0.8, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.C.visibleWhen(() -> {
         TagPainter.ensureSourceClassInitialized();
         return !this.G.is("Minimal");
      });
      this.f = this.mode("font", "Font", "SourceByDevFFnightmare DeObfDeCompByGuideX's Inter or Minecraft's own letters", "SourceByDevFFnightmare DeObfDeCompByGuideX", new String[]{"SourceByDevFFnightmare DeObfDeCompByGuideX", "Minecraft"});
      this.Q = this.bool("team_colours", "Team colours", "Names in the colours the server gives them", true);
      this.section("Visibility");
      this.F = this.mode(
         "walls", "Through walls", "Players, mobs and dropped items behind blocks; sneaking tags stay hidden", "Dim", new String[]{"Hide", "Dim", "Show"}
      );
      this.I = this.number("entity_range", "Player & mob range", "Maximum distance for player and mob tags", 128.0, 4.0, 512.0, 1.0, Listener.unit(" blocks"));
      this.enabledByDefault();
   }

   public TagPainter.Look look() {
      String var1 = (String)this.G.get();

      return switch (var1) {
         case "Solid" -> TagPainter.Look.e;
         case "Minimal" -> TagPainter.Look.h;
         default -> TagPainter.Look.c;
      };
   }

   protected void onDisable() {
      NameTagsCapture.clear();
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ClientServices.acquire(this.id()));
      NameTagsCapture.install(this, var1);
      var1.own(NameTagsCapture::uninstall);
      var1.own(NametagCapturePipeline.install(new dev.purxiy.loader.fabric.client.NametagCapturePipeline.Listener() {
         public void extract(class_1297 var1, class_10017 var2) {
            NameTagsCapture.extract(var1, var2);
         }

         public boolean capture(class_10017 var1, class_4587 var2) {
            return NameTagsCapture.capture(var1, var2);
         }

         public boolean showOwn(class_1309 var1) {
            return NameTagsCapture.showOwn(var1);
         }

         public boolean bounds(class_1297 var1, float[] var2) {
            return NameTagsCapture.bounds(var1, var2);
         }
      }));
      var1.own(SkyPipelineRouter.VIEW.subscribe(0, (FrameHook)(var0, var1x, var2, var3) -> NameTagsCapture.beginFrame(var0, var1x, var2)));
      var1.own(SkyPipelineRouter.AFTER_WORLD.subscribe(110, NameTagsCapture::render));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new NameTagsEditorPage(this);
   }
}
