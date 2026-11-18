// Sourced By DevFFNightmare
package dev.purxiy.modules.radiance;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.ClientRegistry16;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import java.util.List;
import net.minecraft.class_10017;
import net.minecraft.class_11659;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_4588;
import org.joml.Matrix4f;

public final class RadianceModule extends ModuleDescriptor {
   public static final String b = "Glow";
   public static final String A = "Aura";
   public static final String C = "Outline";
   public static final String W = "Off";
   public static final String x = "Monsters";
   public static final String D = "All";
   public final ModeSetting q = this.section("Look")
      .mode("style", "Style", "A glowing line, rising light, or only the line", "Glow", "Glow", "Aura", "Outline");
   public final ModeSetting e = this.mode("palette", "Palette", "The colours of the light", "Accent", RadianceLoader2.names()).renamed(RadianceLoader2.o);
   public final ColorSetting p = this.color("color", "Colour", "The rest of the palette is derived from it", -4944641, false).followsAccent();
   public final BooleanSetting N = this.section("What glows").bool("hand", "Your hand", "Your arm in first person", true);
   public final BooleanSetting o = this.bool("items", "Held items", "What you hold, in first person", true);
   public final BooleanSetting E = this.bool("players", "Players", "Other players, and you in third person", true);
   public final ModeSetting H = this.mode("mobs", "Mobs", "Only monsters, or every creature", "All", new String[]{"Off", "Monsters", "All"});
   public final BooleanSetting U = this.bool("drops", "Dropped items", "Items lying on the ground", true);
   public final NumberSetting i = this.section("Fine-tuning")
      .number("width", "Line width", "How thick the line around them is", 2.0, 0.5, 4.0, 0.5, NumberSetting.Listener.unit(" px"));
   public final NumberSetting m = this.number("glow", "Glow", "How far the soft light reaches", 0.6, 0.05, 1.0, 0.05, NumberSetting.Listener.PERCENT);
   public final NumberSetting j = this.number("fill", "Fill", "A soft tint over them, lit at the edges", 0.2, 0.0, 1.0, 0.05, NumberSetting.Listener.PERCENT);
   public final NumberSetting u = this.number(
      "speed", "Animation speed", "How fast the colours flow (0 = still)", 1.0, 0.0, 3.0, 0.05, NumberSetting.Listener.MULTIPLIER
   );
   public final NumberSetting O = this.number("range", "Range", "How far away things still glow", 48.0, 8.0, 128.0, 4.0, NumberSetting.Listener.unit(" blocks"));
   public final BooleanSetting X = this.section("Extras").bool("sparkles", "Sparkles", "Tiny stars twinkle along the edge", true);
   public final BooleanSetting R = this.bool("hits", "Hit flash", "A flash and a ripple when something is hit", true);
   public final BooleanSetting Q = this.bool("health", "Health colours", "The hurt glow warmer, towards rose", false);
   public final BooleanSetting F = this.bool("rarity", "Rarity colours", "Rare items glow in their rarity's colour", false);
   public static boolean r;



   public RadianceModule() {
      super("radiance", "Radiance", "Glowing outlines on hands, items and mobs", "user-star");
      this.p.visibleWhen(() -> this.e.is("Custom"));
      this.m.visibleWhen(() -> !this.q.is("Outline"));
   }

   @Override
   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/radiance.fsh",
               "shaders/core/radiance_flood.fsh",
               "shaders/core/radiance_mask.fsh",
               "shaders/core/radiance_mask.vsh",
               "shaders/core/radiance_rect.vsh",
               "shaders/core/radiance_refine.fsh",
               "shaders/core/radiance_seed.fsh",
               "shaders/include/radiance.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      RadianceSupport.install(this, var1);
      var1.own(RadianceSupport::uninstall);
      var1.own(ClientRegistry16.install(new ClientRegistry16.Listener() {
         @Override
         public void beginFrame(Matrix4f var1, Matrix4f var2) {
            RadianceSupport.beginFrame(var1, var2);
         }

         @Override
         public void endLevel() {
            RadianceSupport.endLevel();
         }

         @Override
         public void extract(class_1297 var1, class_10017 var2) {
            RadianceSupport.extract(var1, var2);
         }

         @Override
         public class_11659 collector(class_10017 var1, class_11659 var2) {
            return RadianceSupport.collector(var1, var2);
         }

         @Override
         public void onDamage(class_1297 var1, int var2, int var3) {
            RadianceSupport.onDamage(var1, var2, var3);
         }

         @Override
         public class_4588 maskBuffer(int var1, class_1921 var2) {
            return RadianceSupport.maskBuffer(var1, var2);
         }

         @Override
         public void drawWorldMask() {
            RadianceSupport.drawWorldMask();
         }

         @Override
         public void hudProjection(int var1, int var2, float var3) {
            RadianceSupport.hudProjection(var1, var2, var3);
         }

         @Override
         public float handLight(float[] var1) {
            return RadianceSupport.handLight(var1);
         }

         @Override
         public class_11659 handCollector(class_11659 var1) {
            return RadianceSupport.handCollector(var1);
         }

         @Override
         public void submittingItem(class_1799 var1) {
            RadianceSupport.submittingItem(var1);
         }

         @Override
         public void drawHandMask() {
            RadianceSupport.drawHandMask();
         }

         @Override
         public void composite() {
            RadianceSupport.composite();
         }
      }));
      var1.own(ClientSupport3.DEPTH.subscribe(0, var0 -> {
         if (var0) {
            RadianceSupport.drawWorldMask();
         }
      }));
   }

public static void ensureSourceClassInitialized() {
   }
}
