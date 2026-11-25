package dev.purxiy.modules.cute;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.SkinTextureProvider;
import dev.purxiy.loader.fabric.client.TickRouter;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_10042;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_583;

public final class CuteWorldModule extends ModuleDescriptor {
   public final BooleanSetting f;
   public final BooleanSetting e;
   public final ModeSetting w;
   public final BooleanSetting n;
   public final BooleanSetting h;
   public final NumberSetting g;
   public final BooleanSetting L;
   public final ModeSetting O;
   public final BooleanSetting N;
   public final NumberSetting R;

   public CuteWorldModule() {
      super("cute_world", "Cute World", "Sparkling eyes, rosy cheeks and a world full of little smiles", "heart");
      this.section("Little faces");
      this.f = this.bool("players", "Player faces", "Give each player's own skin a cute expression", true);
      this.e = this.bool("mobs", "Mob faces", "Cute creatures, from creepers to cows", true);
      this.w = this.mode("expression", "Expression", "Choose their little personality", "Sparkle", new String[]{"Sparkle", "Happy", "Sleepy"});
      this.n = this.bool("blush", "Rosy cheeks", "A soft touch of pink beneath the eyes", true);
      this.h = this.bool("adapt", "Read skin colours", "Use the face's own skin tone and eye colour; keep hair and clothes", true);
      this.g = this.number("softness", "Plush colours", "Soften mob textures into pastels", 0.3, 0.0, 0.7, 0.05, Listener.PERCENT);
      this.section("A softer world");
      this.L = this.bool("scenery", "Pastel scenery", "Candy-coloured leaves, soft grass, water, sky and clouds", true);
      this.O = this.mode("palette", "Palette", "The colours of your little world", "Sakura", new String[]{"Sakura", "Peach", "Lavender"});
      this.section("A little love");
      this.N = this.bool("hearts", "Hearts & sparkles", "Occasional hearts over creatures and sparkles in the air", true);
      this.R = this.number("amount", "Sprinkles", "How often little hearts and sparkles appear", 0.5, 0.1, 1.0, 0.1, Listener.PERCENT);
      this.R.visibleWhen(this.N::get);
      this.O.visibleWhen(this.L::get);
      this.g.visibleWhen(this.e::get);
      this.h.visibleWhen(this.f::get);
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ClientServices.acquire(this.id()));
      CuteWorldTicker.install(this, var1);
      var1.own(CuteWorldTicker::uninstall);
      var1.own(TickRouter.install(new dev.purxiy.loader.fabric.client.TickRouter.Listener() {
         public void tick(class_310 var1) {
            CuteWorldTicker.tick(var1);
         }

         public int nature(int var1, String var2) {
            return CuteWorldTicker.nature(var1, var2);
         }
      }));
      var1.own(SkinTextureProvider.install(new dev.purxiy.loader.fabric.client.SkinTextureProvider.Listener() {
         public boolean applies(class_10042 var1) {
            return CuteWorldUpdater.applies(var1);
         }

         public class_2960 texture(class_2960 var1, class_10042 var2, class_583<?> var3) {
            return CuteWorldUpdater.texture(var1, var2, var3);
         }

         public void clear() {
            CuteWorldUpdater.clear();
         }
      }));
      var1.own(ModuleWidget.onTick(CuteWorldTicker::tick));
   }
}
