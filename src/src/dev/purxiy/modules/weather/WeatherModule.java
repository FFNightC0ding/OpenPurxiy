// Sourced By DevFFNightmare
package dev.purxiy.modules.weather;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.ClientLoader;
import dev.purxiy.loader.fabric.client.ClientRegistry2;
import dev.purxiy.loader.fabric.client.ClientRegistry24;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import java.util.List;
import org.joml.Matrix4f;

public final class WeatherModule extends ModuleDescriptor {
   public final ModeSetting t = this.section("Weather").mode("mode", "Weather", "Choose the atmosphere around you", "Rain", "Rain", "Snow", "Fire");
   public final NumberSetting O = this.number(
      "intensity", "Intensity", "From a gentle shower to heavy precipitation", 1.0, 0.1, 2.0, 0.05, NumberSetting.Listener.PERCENT
   );
   public final NumberSetting B = this.number("wind", "Wind", "The strength of the moving gusts", 0.45, 0.0, 2.0, 0.05, NumberSetting.Listener.PERCENT);
   public final NumberSetting J = this.number(
      "size", "Drop size", "The size of droplets, crystals and embers", 1.0, 0.5, 2.0, 0.05, NumberSetting.Listener.MULTIPLIER
   );
   public final NumberSetting o = this.number(
      "range", "Range", "How far around you the drops fall, in blocks", 24.0, 8.0, 40.0, 1.0, NumberSetting.Listener.INTEGER
   );
   public final BooleanSetting N = this.section("Surfaces").bool("ground", "Ground effects", "Glassy wet ground, snow or embers, far and wide", true);
   public final NumberSetting Z = this.number(
      "coverage", "Coverage", "How much of the open ground is covered", 0.85, 0.0, 1.0, 0.05, NumberSetting.Listener.PERCENT
   );
   public final BooleanSetting H = this.bool("reflections", "Reflections", "Mirror the scenery, not only the sky", true);
   public final BooleanSetting j = this.section("Storm").bool("storm", "Thunderstorm", "Branching lightning with distant rolling thunder", false);
   public final NumberSetting f = this.number(
      "lightning", "Lightning", "Brightness of the lightning and its glow; zero turns flashes off", 0.65, 0.0, 1.0, 0.05, NumberSetting.Listener.PERCENT
   );
   public final BooleanSetting x = this.section("Sound").bool("sound", "Weather sounds", "Original soundscapes that follow the weather", true);
   public final NumberSetting Q = this.number(
      "volume", "Volume", "Also follows Minecraft's Weather and Master volume", 0.65, 0.0, 1.0, 0.05, NumberSetting.Listener.PERCENT
   );
   public static boolean P;



   public WeatherModule() {
      super("weather", "Weather", "Sculpt the weather: glassy rain, soft snowfall and ember showers", "cloud-rain");
      this.Z.visibleWhen(this.N::enabled);
      this.H.visibleWhen(() -> this.N.enabled() && this.t.is("Rain"));
      this.j.visibleWhen(() -> this.t.is("Rain"));
      this.f.visibleWhen(() -> this.t.is("Rain") && this.j.enabled());
      this.Q.visibleWhen(this.x::enabled);
   }

   @Override
   protected void onDisable() {
      WeatherUpdater.stop();
   }

   @Override
   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientRegistry24.install(
            var1,
            List.of(
               "assets/purxiy/sounds/weather/fire.ogg",
               "assets/purxiy/sounds/weather/rain.ogg",
               "assets/purxiy/sounds/weather/snow.ogg",
               "assets/purxiy/sounds/weather/thunder_1.ogg",
               "assets/purxiy/sounds/weather/thunder_2.ogg",
               "assets/purxiy/sounds/weather/thunder_3.ogg",
               "assets/purxiy/sounds/weather/wind.ogg"
            )
         )
      );
      var1.own(ClientLoader.install(var1, "assets/purxiy/sound-events-weather.json"));
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/weather_atmosphere.fsh",
               "shaders/core/weather_atmosphere.vsh",
               "shaders/core/weather_particles.fsh",
               "shaders/core/weather_particles.vsh",
               "shaders/core/weather_sky.fsh",
               "shaders/core/weather_surface.fsh",
               "shaders/core/weather_surface.vsh",
               "shaders/include/weather_atmosphere.glsl",
               "shaders/include/weather_surface.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      WeatherSupport5.install(this, var1);
      var1.own(WeatherSupport5::uninstall);
      var1.own(WeatherSupport4::releaseResources);
      var1.own(WeatherSupport2::releaseResources);
      var1.own(WeatherSupport3::releaseResources);
      var1.own(ClientSupport3.VIEW.subscribe(0, (var0, var1x, var2, var3) -> {
         WeatherSupport2.capture(new Matrix4f(var1x), new Matrix4f(var2));
         WeatherSupport3.capture(var1x, var2);
         WeatherSupport5.capture(var1x, var2);
      }));
      var1.own(ClientSupport3.PARTICLES.subscribe(-100, (var0, var1x, var2) -> {
         WeatherSupport2.addPass(var0, var1x, var2);
         WeatherSupport3.addPass(var0, var1x, var2);
      }));
      var1.own(ClientSupport3.PARTICLES.subscribe(100, WeatherSupport5::addPass));
      var1.own(ClientSupport3.AFTER_WORLD.subscribe(0, () -> {
         WeatherSupport3.renderAfterLevel();
         WeatherSupport5.renderAfterLevel();
      }));
      var1.own(ModuleWidget.onTick(WeatherUpdater::tick));
      var1.own(ModuleWidget.onDisconnect(var0 -> {
         WeatherUpdater.stop();
         WeatherSupport5.close();
         WeatherSupport3.close();
         WeatherSupport2.close();
      }));
      var1.own(ClientRegistry2.install(new ClientRegistry2.Listener() {
         @Override
         public boolean hideVanillaClouds() {
            return WeatherSupport2.hideVanillaClouds();
         }
      }));
   }

public static void ensureSourceClassInitialized() {
   }
}
