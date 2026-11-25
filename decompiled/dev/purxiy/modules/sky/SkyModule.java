package dev.purxiy.modules.sky;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.Setting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import dev.purxiy.loader.fabric.client.ClientSupport3.Listener5;
import dev.purxiy.ui.Theme;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.class_12076;
import net.minecraft.class_638;
import org.joml.Matrix4f;

public final class SkyModule extends ModuleDescriptor {
   public static final String B = "Original";
   public static final String W = "Accent";
   public static final String P = "Custom";
   public final ModeSetting S;
   public final ModeSetting D;
   public final List<SkyModule.Colours> Y;
   public final NumberSetting j;
   public final NumberSetting v;
   public final NumberSetting y;
   public final NumberSetting M;
   public final NumberSetting r;
   public final NumberSetting I;
   public final BooleanSetting T;
   public final BooleanSetting n;
   public final NumberSetting h;
   public final ModeSetting e;
   public final BooleanSetting Q;
   public final BooleanSetting u;
   public final NumberSetting l;
   public final NumberSetting N;
   public final BooleanSetting o;
   public final BooleanSetting O;
   public final ModeSetting g;
   private static final long c = -5659072711935131648L;

   public SkyModule() {
      super("sky", "Sky", "Eight living skies, from cosmic light to snow clouds and fire", "orbit");
      long var1 = 9754120855158L;
      long var3 = var1 ^ 20287485936236L;
      this.S = this.mode("style", "Sky style", "Eight living skies, including rain clouds, winter white and fire", "Galaxy", SkyKind.names());
      this.D = this.mode("palette", "Palette", "The colours of your dimension", "Purxiy", SkyCatalog.names());
      this.Y = this.d(var3);
      this.j = this.number("intensity", "Pattern intensity", "The depth and glow of the flowing patterns", 0.8, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.v = this.number("speed", "Animation speed", "How fast everything drifts and flows", 1.0, 0.0, 3.0, 0.05, Listener.MULTIPLIER);
      this.y = this.number("liveliness", "Liveliness", "How much it swirls, twinkles and breathes", 0.6, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.M = this.number("breathing", "Breathing speed", "Tempo of the glow that pulses through it", 1.0, 0.0, 3.0, 0.05, Listener.MULTIPLIER);
      this.r = this.number("nebula", "Nebula", "Glowing clouds of cosmic dust", 0.8, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.I = this.number("stars", "Stars", "How many stars fill the sky", 0.75, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.T = this.bool("shooting_stars", "Shooting stars", "Streaks of light, and now and then a shower", true);
      this.n = this.bool("comets", "Comets", "Now and then a comet crosses the sky", true);
      this.h = this.number("aurora", "Aurora", "Curtains of light drifting overhead", 0.55, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.e = this.mode("planet", "Planet", "A detailed 3D world with cloud layers, rings and orbiting moons", "World", new String[]{"Off", "Giant", "World"});
      this.Q = this.bool("singularity", "Singularity", "A black hole that bends the light around it", true);
      this.u = this.bool("sun_moon", "Sun & moon", "Keep track of the time of day", true);
      this.l = this.number("daylight", "Daylight", "How much the day brightens the sky", 0.6, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.N = this.number("brightness", "Brightness", null, 1.0, 0.5, 1.5, 0.05, Listener.PERCENT);
      this.o = this.bool("clouds", "Tint clouds", "Clouds take on the colours of the sky", true);
      this.O = this.bool("steady", "Steady sky", "The sky stays still while you walk", true);
      this.g = this.mode("quality", "Quality", "Detail versus frame rate", "High", new String[]{"Low", "Medium", "High"});
      this.D.visibleWhen(this::galaxy);
      this.j.visibleWhen(() -> {
         SkyKind.ensureSourceClassInitialized();
         return !this.galaxy();
      });
      this.r.visibleWhen(this::galaxy);
      this.I.visibleWhen(this::galaxy);
      this.M.visibleWhen(() -> {
         SkyKind.ensureSourceClassInitialized();
         return (Double)this.y.get() > 0.0;
      });
      this.T.visibleWhen(() -> {
         SkyKind.ensureSourceClassInitialized();
         return this.galaxy() && (Double)this.I.get() > 0.0;
      });
      this.n.visibleWhen(this::galaxy);
      this.h.visibleWhen(this::galaxy);
      this.e.visibleWhen(this::galaxy);
      this.Q.visibleWhen(this::galaxy);
      this.u.visibleWhen(this::galaxy);
      this.o.visibleWhen(this::galaxy);
   }

   public boolean galaxy() {
      return this.S.is("Galaxy");
   }

   public SkyModule.Colours current() {
      return this.Y.get(this.S.index());
   }

   public boolean colourSetting(Setting<?> var1) {
      for (SkyModule.Colours var3 : this.Y) {
         if (var1 == var3.q || var1 == var3.G) {
            return true;
         }
      }

      return false;
   }

   private List d(long var1) {
      SkyKind.ensureSourceClassInitialized();
      ArrayList var3 = new ArrayList();

      for (SkyKind var7 : SkyKind.values()) {
         String var8 = var7.K.toLowerCase(Locale.ROOT);
         int var9 = var7 == SkyKind.X ? SkyCatalog.M.J.key() : var7.j.key();
         ModeSetting var10 = this.mode(
            var8 + "_colors", "Colours", "Its own colours, your accent, or one you pick", "Original", new String[]{"Original", "Accent", "Custom"}
         );
         ColorSetting var11 = this.color(var8 + "_color", "Colour", "The colour this sky takes on", 0xFF000000 | var9, false);
         var10.visibleWhen(() -> this.S.is(var7.K));
         var11.visibleWhen(() -> {
            SkyKind.ensureSourceClassInitialized();
            long var3x = 118791576768857L;
            int var5 = var3x < 0L ? -1 : 1;
            return this.S.is(var7.K) && var10.is("Custom");
         });
         var3.add(new SkyModule.Colours(var7, var10, var11));
      }

      return List.copyOf(var3);
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/sky.fsh",
               "shaders/core/sky.vsh",
               "shaders/core/sky_body.fsh",
               "shaders/core/sky_body.vsh",
               "shaders/core/sky_space.fsh",
               "shaders/include/planet.glsl",
               "shaders/include/sky.glsl",
               "shaders/include/sky_styles.glsl",
               "shaders/include/sky_weather.glsl",
               "shaders/include/tint.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      SkySupport.install(this, var1);
      var1.own(SkySupport::uninstall);
      var1.own(ClientSupport3.SKY.subscribe(0, new Listener5() {
         public void extract(class_638 var1, class_12076 var2) {
            SkySupport.extract(var1, var2);
         }

         public void projection(Matrix4f var1) {
            SkySupport.captureProjection(var1);
         }

         public void steady(Matrix4f var1, Matrix4f var2) {
            SkySupport.captureSteadyProjection(var1, var2);
         }

         public boolean replace() {
            return SkySupport.replaceVanilla();
         }

         public void overlay() {
            SkySupport.drawOverVanilla();
         }

         public boolean cloudsHidden() {
            return SkySupport.hideVanillaClouds();
         }

         public int fog(class_638 var1, int var2) {
            return SkySupport.fogColor(var1, var2);
         }

         public int clouds(int var1) {
            return SkySupport.cloudColor(var1);
         }

         public float presence() {
            return SkySupport.shown();
         }
      }));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new SkyRenderer2(this);
   }

   public record Colours(SkyKind r, ModeSetting q, ColorSetting G) {
      public boolean original() {
         return this.q.is("Original");
      }

      public int colour() {
         return (this.q.is("Accent") ? Theme.accent() : this.G.get()) & 16777215;
      }

      public SkyKind style() {
         return this.r;
      }

      public ModeSetting mode() {
         return this.q;
      }

      public ColorSetting own() {
         return this.G;
      }

      public SkyKind skyAppearancePresetState1() {
         return this.r;
      }

      public ModeSetting choiceSettingState7() {
         return this.q;
      }

      public ColorSetting colorSettingState22() {
         return this.G;
      }
   }
}
