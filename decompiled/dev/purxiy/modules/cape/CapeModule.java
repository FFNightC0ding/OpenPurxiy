package dev.purxiy.modules.cape;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.ClientRegistry23;
import dev.purxiy.loader.fabric.client.ClientRegistry24;
import dev.purxiy.loader.fabric.client.ClientRegistry27;
import dev.purxiy.ui.clickgui.Studio;
import java.util.List;
import net.minecraft.class_10055;
import net.minecraft.class_11659;
import net.minecraft.class_12137;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_572;
import net.minecraft.class_8685;

public final class CapeModule extends ModuleDescriptor {
   public static final String C = "Flowing";
   public static final String Q = "Vanilla";
   public final ModeSetting h = this.mode("design", "Cape", "Which cape you wear", CapeDesign.B.E, CapeDesign.labels());
   public final NumberSetting e = this.section("Look").number("glow", "Glow", "How brightly it shines in the dark", 0.7, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final NumberSetting H = this.number("speed", "Animation", "How fast it moves; at 0 it holds still", 1.0, 0.0, 2.0, 0.05, Listener.MULTIPLIER);
   public final ModeSetting r = this.section("Wear")
      .mode("motion", "Movement", "Flowing bends like cloth; Vanilla is the game's own", "Flowing", new String[]{"Flowing", "Vanilla"});
   public final BooleanSetting F = this.bool("elytra", "On your elytra", "Your elytra wears the design too", true);
   public static boolean J;

   public CapeModule() {
      super("cape", "Cape", "Wear an animated Purxiy cape", "flag");
   }

   public CapeDesign selected() {
      return CapeDesign.byLabel((String)this.h.get());
   }

   public boolean flowing() {
      return this.r.is("Flowing");
   }

   protected void onDisable() {
      CapeCache.release();
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ClientRegistry24.install(var1, List.of("assets/purxiy/textures/cape/mark_sdf.png")));
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/cape.fsh",
               "shaders/core/cape.vsh",
               "shaders/core/profile_avatar.fsh",
               "shaders/core/profile_avatar.vsh",
               "shaders/include/cape.glsl",
               "shaders/include/theme.glsl",
               "shaders/include/tint.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      CapeCache.install(this, var1);
      var1.own(CapeCache::uninstall);
      var1.own(CosmeticRegistry.register(this.id(), this.getClass().getClassLoader(), () -> new CapeRenderer2(this)));
      var1.own(CapeRenderer::close);
      var1.own(ClientRegistry23.install(new dev.purxiy.loader.fabric.client.ClientRegistry23.Listener() {
         public class_8685 dress(class_8685 var1) {
            return CapeCache.dress(var1);
         }

         public class_8685 wear(class_8685 var1) {
            return CapeCache.wear(var1);
         }

         public boolean wears(class_8685 var1) {
            return CapeCache.wears(var1);
         }

         public void chest(class_1799 var1) {
            CapeCache.chest(var1);
         }

         public class_12137 sampler() {
            return CapeCache.sampler();
         }
      }));
      var1.own(ClientRegistry27.install(new dev.purxiy.loader.fabric.client.ClientRegistry27.Listener() {
         public boolean active() {
            return CapeSupport.active();
         }

         public void submit(class_572<class_10055> var1, class_10055 var2, class_4587 var3, class_11659 var4, int var5, int var6) {
            CapeSupport.submit(var1, var2, var3, var4, var5, var6);
         }
      }));
      var1.onFrame(var0 -> CapeCache.frame());
   }

   public Studio studio() {
      return null;
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new CapeRenderer4(this);
   }

   public static void ensureSourceClassInitialized() {
   }
}
