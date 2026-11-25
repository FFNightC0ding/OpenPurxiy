package dev.purxiy.modules.afterglow;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.purxiy.anim.Easing;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ClientTheme;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.RenderHooks;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.ClientLoader;
import dev.purxiy.loader.fabric.client.ResourceProvider;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import dev.purxiy.ui.clickgui.Studio;
import java.util.List;
import net.minecraft.class_10017;
import net.minecraft.class_10055;
import net.minecraft.class_11658;
import net.minecraft.class_11659;
import net.minecraft.class_11890;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1937;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_898;
import net.minecraft.class_9909;
import net.minecraft.class_9960;
import net.minecraft.class_10017.class_11680;

public final class AfterglowModule extends ModuleDescriptor {
   public static final String X = "Everyone";
   public static final String I = "Players & monsters";
   public static final String Y = "Players";
   public final ModeSetting Z = this.mode("style", "Style", "The light they dissolve into", "Motes", AfterglowCatalog.names());
   public final ModeSetting b = this.mode("palette", "Palette", "The colours of the light", "Accent", AfterglowLoader.names()).renamed(AfterglowLoader.n);
   public final ColorSetting r = this.color("color", "Colour", "The rest of the palette is derived from it", -4944641, false).followsAccent();
   public final ModeSetting H = this.mode("who", "Who", "Whose fall turns to light", "Everyone", new String[]{"Everyone", "Players & monsters", "Players"});
   public final BooleanSetting h = this.bool("yours", "Only your kills", "Only what you defeat yourself dissolves", false);
   public final NumberSetting g = this.number("speed", "Speed", "How fast they dissolve", 1.0, 0.5, 2.0, 0.05, Listener.MULTIPLIER);
   public final NumberSetting W = this.number("amount", "Light", "How much light they release", 1.0, 0.0, 2.0, 0.05, Listener.PERCENT);
   public final NumberSetting s = this.number("glow", "Glow", "The burning edge and the light it sheds", 0.7, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final BooleanSetting w = this.bool("fall", "Fall over", "Topple over as in vanilla while they dissolve", false);
   public final BooleanSetting D = this.bool("sound", "Sound", "A soft shimmer where they dissolve", true);

   public AfterglowModule() {
      super("afterglow", "Afterglow", "The fallen dissolve into drifting light", "ghost");
      this.r.visibleWhen(() -> this.b.is("Custom"));
   }

   public Studio studio() {
      return AfterglowRenderer.of(this);
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ResourceProvider.install(var1, List.of("assets/purxiy/sounds/world/afterglow.ogg")));
      var1.own(ClientLoader.install(var1, "assets/purxiy/sound-events-afterglow.json"));
      var1.own(ClientCodec.install(var1, List.of("shaders/core/afterglow.fsh", "shaders/core/afterglow.vsh", "shaders/include/afterglow.glsl")));
      var1.own(ClientServices.acquire(this.id()));
      AfterglowRegistry.install(this, var1);
      var1.own(AfterglowRegistry::uninstall);
      var1.own(ClientTheme.install(new dev.purxiy.loader.fabric.ClientTheme.Listener() {
         public boolean active() {
            return AfterglowFrameView.active();
         }

         public float fov(float var1) {
            return AfterglowFrameView.fov(var1);
         }

         public boolean hides(class_1297 var1) {
            return AfterglowFrameView.hides(var1);
         }

         public void reset() {
            AfterglowRenderer.closeOwned();
         }

         public boolean place(class_4184 var1, class_1937 var2, class_1297 var3, float var4, double[] var5, float[] var6) {
            return AfterglowFrameView.place(var1, var2, var3, var4, var5, var6);
         }

         public void pose(class_11890 var1, class_10055 var2) {
            if (AfterglowFrameView.active() && var1 == class_310.method_1551().field_1724) {
               float var3 = Easing.SMOOTHSTEP.apply(Math.clamp((AfterglowFrameView.progress() - 0.15F) / 0.6F, 0.0F, 1.0F));
               var2.field_53447 = class_3532.method_16439(var3, var2.field_53447, 0.0F);
               var2.field_53448 = class_3532.method_16439(var3, var2.field_53448, -2.0F);
            }
         }
      }));
      var1.own(ModuleWidget.claim("preview-afterglow", () -> AfterglowRenderer.showing(this)));
      var1.own(ModuleWidget.onTick(AfterglowRenderer::tick));
      var1.own(AfterglowRenderer::closeOwned);
      var1.own(CosmeticRegistry.register(this.id(), this.getClass().getClassLoader(), () -> {
         ClientTheme.resetAll();
         return new AfterglowSupport(this);
      }));
      var1.own(RenderHooks.install(new dev.purxiy.loader.fabric.RenderHooks.Listener() {
         public void beginFrame() {
            AfterglowRegistry.beginFrame();
         }

         public void addPass(class_9909 var1, class_9960 var2, GpuBufferSlice var3) {
            AfterglowRegistry.addPass(var1, var2, var3);
         }

         public void extract(class_1297 var1, class_10017 var2) {
            AfterglowRegistry.extract(var1, var2);
         }

         public boolean beginSubmit(class_10017 var1, double var2, double var4, double var6) {
            return AfterglowRegistry.beginSubmit(var1, var2, var4, var6);
         }

         public boolean dissolving(class_10017 var1) {
            return AfterglowRegistry.dissolving(var1);
         }

         public class_11659 collector(class_10017 var1, class_11659 var2) {
            return AfterglowRegistry.collector(var1, var2);
         }

         public List<class_11680> shadow(class_10017 var1, List<class_11680> var2) {
            return AfterglowRegistry.shadow(var1, var2);
         }

         public void submitGhosts(class_898 var1, class_11658 var2, class_4587 var3, class_11659 var4) {
            AfterglowRegistry.submitGhosts(var1, var2, var3, var4);
         }

         public boolean hidesPoof(class_1309 var1) {
            return AfterglowRegistry.hidesPoof(var1);
         }

         public void onDamage(class_1297 var1, int var2) {
            AfterglowRegistry.onDamage(var1, var2);
         }
      }));
   }
}
