// Sourced By DevFFNightmare
package dev.purxiy.modules.nametags;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.CosmeticCatalog;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientRegistry9;
import dev.purxiy.loader.fabric.client.ClientSupport3;
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
         "item_range",
         "Item range",
         "How far away loaded item tags show, including through walls",
         12.0,
         4.0,
         128.0,
         1.0,
         NumberSetting.Listener.unit(" blocks")
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
      this.N = this.number("size", "Size", null, 1.0, 0.5, 2.0, 0.05, NumberSetting.Listener.PERCENT);
      this.t = this.number("shrink", "Shrink with distance", "At 0% every tag keeps the same size", 0.6, 0.0, 1.0, 0.05, NumberSetting.Listener.PERCENT);
      this.C = this.number("opacity", "Background opacity", null, 0.8, 0.0, 1.0, 0.05, NumberSetting.Listener.PERCENT);
      this.C.visibleWhen(() -> {
         TagPainter.ensureSourceClassInitialized();
         return !this.G.is("Minimal");
      });
      this.f = this.mode("font", "Font", "Purxiy's Inter or Minecraft's own letters", "Purxiy", new String[]{"Purxiy", "Minecraft"});
      this.Q = this.bool("team_colours", "Team colours", "Names in the colours the server gives them", true);
      this.section("Visibility");
      this.F = this.mode(
         "walls", "Through walls", "Players, mobs and dropped items behind blocks; sneaking tags stay hidden", "Dim", new String[]{"Hide", "Dim", "Show"}
      );
      this.I = this.number(
         "entity_range", "Player & mob range", "Maximum distance for player and mob tags", 128.0, 4.0, 512.0, 1.0, NumberSetting.Listener.unit(" blocks")
      );
      this.enabledByDefault();
   }

   public TagPainter.Look look() {
      String var1 = this.G.get();

      return switch (var1) {
         case "Solid" -> TagPainter.Look.e;
         case "Minimal" -> TagPainter.Look.h;
         default -> TagPainter.Look.c;
      };
   }

   @Override
   protected void onDisable() {
      NameTagsRenderer4.clear();
   }

   @Override
   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ClientServices.acquire(this.id()));
      NameTagsRenderer4.install(this, var1);
      var1.own(NameTagsRenderer4::uninstall);
      var1.own(ClientRegistry9.install(new ClientRegistry9.Listener() {
         @Override
         public void extract(class_1297 var1, class_10017 var2) {
            NameTagsRenderer4.extract(var1, var2);
         }

         @Override
         public boolean capture(class_10017 var1, class_4587 var2) {
            return NameTagsRenderer4.capture(var1, var2);
         }

         @Override
         public boolean showOwn(class_1309 var1) {
            return NameTagsRenderer4.showOwn(var1);
         }

         @Override
         public boolean bounds(class_1297 var1, float[] var2) {
            return NameTagsRenderer4.bounds(var1, var2);
         }
      }));
      var1.own(ClientSupport3.VIEW.subscribe(0, (var0, var1x, var2, var3) -> NameTagsRenderer4.beginFrame(var0, var1x, var2)));
      var1.own(ClientSupport3.AFTER_WORLD.subscribe(110, NameTagsRenderer4::render));
   }

   @Override
   protected CosmeticCatalog.Listener createPreview() {
      return new NameTagsRenderer2(this);
   }

}
