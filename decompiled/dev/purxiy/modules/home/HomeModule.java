package dev.purxiy.modules.home;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.TextSetting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.ClientRegistry21;
import dev.purxiy.loader.fabric.client.ClientRegistry3;
import dev.purxiy.loader.fabric.client.ClientRenderer2;
import dev.purxiy.loader.fabric.client.ClientRenderer4;
import dev.purxiy.loader.fabric.client.ClientRegistry3.Listener;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import java.util.List;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_350;
import net.minecraft.class_425;
import net.minecraft.class_437;
import net.minecraft.class_442;
import net.minecraft.class_8021;

public final class HomeModule extends ModuleDescriptor {
   public static final String C = "Morning";
   public static final String m = "Day";
   public static final String N = "Sunset";
   public static final String D = "Night";
   public static final String A = "Real time";
   public static final String p = "Oak";
   public static final String b = "Birch";
   public static final String T = "Cherry";
   public static final String L = "Spruce";
   public static final String d = "Mixed";
   public static final String Y = "Orbit";
   public static final String I = "Drift";
   public static final String Z = "Still";
   public final BooleanSetting Q = this.bool("menus", "Purxiy main menu", "A living world behind Minecraft's menus, and the menus in the Purxiy style", true);
   public final BooleanSetting J = this.bool("loading", "Loading screen", "The Purxiy logo while the game loads, flying to its place in the menu", true);
   public final ModeSetting M;
   public final ModeSetting X;
   public final ModeSetting q;
   public final BooleanSetting z;
   public final BooleanSetting s;
   public final TextSetting H;
   public static boolean R;

   public HomeModule() {
      super("main_menu", "Main Menu", "The world behind the menus, the loading screen and the look of Minecraft's menus", "house");
      this.section("World");
      this.M = this.mode(
         "time", "Time of day", "Real time follows the clock of your computer", "Day", new String[]{"Morning", "Day", "Sunset", "Night", "Real time"}
      );
      this.X = this.mode("trees", "Trees", "What grows around you", "Mixed", new String[]{"Oak", "Birch", "Cherry", "Spruce", "Mixed"});
      this.q = this.mode("camera", "Camera", "Orbit turns slowly round, Drift sways, Still stands", "Orbit", new String[]{"Orbit", "Drift", "Still"});
      this.z = this.bool("grow_on_start", "Grow on start", "The trees grow up from saplings as the game starts", true);
      this.s = this.bool("splash", "Splash text", "Minecraft's yellow line beside the logo", true);
      this.H = (TextSetting)this.add(new TextSetting("forest", "Forest", null, "", 24));
      this.H.visibleWhen(() -> false);
   }

   public boolean worldEnabled() {
      return this.Q.enabled();
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/home_block.fsh",
               "shaders/core/home_block.vsh",
               "shaders/core/home_composite.fsh",
               "shaders/core/home_particle.fsh",
               "shaders/core/home_particle.vsh",
               "shaders/core/home_sky.fsh",
               "shaders/core/home_sky.vsh",
               "shaders/include/home.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      HomeUpdater.install(this, var1);
      var1.own(HomeUpdater::uninstall);
      var1.own(HomeAutoCloseable::releaseResources);
      var1.own(HomeRenderer2::releaseResources);
      var1.own(ClientRegistry3.install(new Listener() {
         public boolean drawBackground(class_437 var1) {
            return HomeUpdater.get().drawBackground(var1);
         }

         public boolean drawnThisFrame() {
            return HomeUpdater.get().drawnThisFrame();
         }

         public boolean plant(double var1, double var3) {
            return HomeUpdater.get().plant(var1, var3);
         }
      }));
      var1.own(ClientRegistry21.install(new dev.purxiy.loader.fabric.client.ClientRegistry21.Listener() {
         public boolean skins(class_437 var1) {
            return HomeSupport2.skins(var1);
         }

         public boolean active() {
            return HomeSupport2.active();
         }

         public void begin(class_437 var1, class_332 var2, int var3, int var4) {
            HomeSupport2.begin(var1, var2, var3, var4);
         }

         public void end(class_437 var1) {
            HomeSupport2.end(var1);
         }

         public void widgetBegin(class_339 var1, class_332 var2) {
            HomeSupport2.widgetBegin(var1, var2);
         }

         public void widgetEnd(class_339 var1, class_332 var2) {
            HomeSupport2.widgetEnd(var1, var2);
         }

         public void scissor(class_332 var1, int var2, int var3, int var4, int var5) {
            HomeSupport2.scissor(var1, var2, var3, var4, var5);
         }

         public void unscissor() {
            HomeSupport2.unscissor();
         }

         public boolean sprite(class_2960 var1, int var2, int var3, int var4, int var5, int var6) {
            return HomeSupport2.sprite(var1, var2, var3, var4, var5, var6);
         }

         public boolean texture(class_2960 var1, int var2, int var3, int var4, int var5) {
            return HomeSupport2.texture(var1, var2, var3, var4, var5);
         }

         public boolean fill(class_332 var1, int var2, int var3, int var4, int var5, int var6) {
            return HomeSupport2.fill(var1, var2, var3, var4, var5, var6);
         }

         public boolean label(class_339 var1, class_2561 var2, int var3) {
            return HomeSupport2.label(var1, var2, var3);
         }

         public boolean link(class_339 var1, class_2561 var2) {
            return HomeSupport2.link(var1, var2);
         }

         public boolean title(class_339 var1, class_2561 var2) {
            return HomeSupport2.title(var1, var2);
         }

         public boolean sliderLabel(class_339 var1, class_2561 var2) {
            return HomeSupport2.sliderLabel(var1, var2);
         }

         public boolean tabLabel(class_339 var1, class_2561 var2, boolean var3) {
            return HomeSupport2.tabLabel(var1, var2, var3);
         }

         public void listRow(class_350<?> var1, class_8021 var2, boolean var3, boolean var4) {
            HomeSupport2.listRow(var1, var2, var3, var4);
         }

         public boolean hidesSelection(class_350<?> var1) {
            return HomeSupport2.hidesSelection(var1);
         }

         public boolean click() {
            return HomeSupport2.click();
         }
      }));
      var1.own(ClientRenderer4.install(new dev.purxiy.loader.fabric.client.ClientRenderer4.Listener() {
         public void render(class_442 var1, class_2561 var2) {
            HomeRenderer3.render(var1, var2);
         }

         public boolean mouseClicked(double var1, double var3, int var5) {
            return HomeRenderer3.mouseClicked(var1, var3, var5);
         }

         public int maskPointer(int var1) {
            return HomeRenderer3.maskPointer(var1);
         }

         public boolean allowKey(int var1) {
            return HomeRenderer3.allowKey(var1);
         }
      }));
      var1.own(ClientRenderer2.install(new dev.purxiy.loader.fabric.client.ClientRenderer2.Listener() {
         public boolean takesOver() {
            return HomeRenderer.takesOver();
         }

         public boolean render(class_425 var1, class_332 var2, int var3, int var4, float var5, boolean var6, long var7, float var9) {
            return HomeRenderer.render(var1, var2, var3, var4, var5, var6, var7, var9);
         }
      }));
      var1.own(ModuleWidget.onTick(var0 -> HomeUpdater.get().tick()));
   }
}
