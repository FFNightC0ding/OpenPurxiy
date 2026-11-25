package dev.purxiy.modules.spotify;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.IntegerSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.Setting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudLayout;
import dev.purxiy.hud.HudModule;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientRegistry18;
import dev.purxiy.loader.fabric.client.ClientRenderer8;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import dev.purxiy.loader.fabric.client.ClientSupport3.Listener10;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import dev.purxiy.render.RenderPipelines;
import java.util.List;
import java.util.Locale;
import net.minecraft.class_310;

public final class SpotifyModule extends ModuleDescriptor {
   public static final String b = "Split";
   public static final String D = "Combined";
   public static final String h = "Lyrics only";
   public static final String N = "Player only";
   public static final String k = "On screen";
   public static final String R = "In the world";
   public static final String C = "On the ground";
   public static final String B = "In the sky";
   public static final String A = "Ground and sky";
   public static final String l = "As sung";
   public static final String s = "Whole line";
   private static final Listener v = var0x -> {
      long var2 = 92946667063537L;
      String var4;
      if (Math.abs(var0x) < 0.001) {
         var4 = "In sync";
      } else {
         Locale var5 = Locale.ROOT;
         Object[] var6 = new Object[]{Math.abs(var0x), null};
         double var7;
         int var9 = (var7 = var0x - 0.0) == 0.0 ? 0 : (var7 < 0.0 ? -1 : 1);
         int var10 = -720189199;
         var6[1] = var9 < 0 ? "earlier" : "later";
         var4 = String.format(var5, "%.2f s %s", var6);
      }

      return var4;
   };
   public final ModeSetting J;
   public final ModeSetting m;
   public final BooleanSetting d;
   public final BooleanSetting w;
   public final ModeSetting G;
   public final ModeSetting E;
   public final ModeSetting z;
   public final ModeSetting F;
   public final NumberSetting L;
   public final NumberSetting X;
   public final BooleanSetting W;
   public final BooleanSetting Y;
   public final NumberSetting p;
   public final ModeSetting c;
   public final ModeSetting t;
   public final BooleanSetting g;
   public final BooleanSetting e;
   public final NumberSetting Z;
   public final ModeSetting S;
   public final ModeSetting y;
   public final NumberSetting r;
   public final IntegerSetting O;
   public final IntegerSetting U;
   public final IntegerSetting i;
   public final IntegerSetting Q;
   public final IntegerSetting K;
   final SpotifyRenderer6 V;
   final SpotifyRenderer7 u;
   private long M;
   private float T;

   public SpotifyModule() {
      super("spotify_player", "Spotify Player", "Your music on screen, with lyrics to sing along", "music");
      long var1 = 6290745105703L;
      long var3 = var1 ^ 109969854405850L;
      var3 = var1 ^ 136013084276938L;
      var3 = var1 ^ 67127426726384L;
      this.M = -1L;
      this.J = this.mode("layout", "Layout", "Where the lyrics and the player go", "Split", new String[]{"Split", "Combined", "Lyrics only", "Player only"});
      this.E = this.mode(
         "lyrics_place",
         "Lyrics",
         "On your screen, or out in the world: on the ground or across the sky",
         "On screen",
         new String[]{"On screen", "In the world"}
      );
      this.z = this.mode(
         "world_where",
         "Where",
         "On the ground in front of you, across the sky, or both: on the ground, and in the sky while you fly or look up",
         "On the ground",
         new String[]{"On the ground", "In the sky", "Ground and sky"}
      );
      this.F = this.mode(
         "world_words",
         "Words",
         "Every word appears as it is sung (on the ground one left, one right of your way), or the whole line at once",
         "As sung",
         new String[]{"As sung", "Whole line"}
      );
      this.L = this.number(
         "world_distance", "Distance", "How far ahead of you the words are written on the ground", 6.0, 3.0, 16.0, 0.5, Listener.unit(" blocks")
      );
      this.X = this.number("world_size", "Size", "How large the words are written", 1.0, 0.6, 1.6, 0.05, Listener.PERCENT);
      this.W = this.bool("world_open_sky", "Only in open sky", "Blocks hide the words: they only show where you can see the sky", true);
      this.Y = this.bool("lyrics_menus", "In the menus", "On the ground of the main menu's world, and beside Minecraft's other menus", true);
      this.section("Player");
      this.V = new SpotifyRenderer6(this);
      this.m = this.mode("controls", "Controls", "The buttons below the song", "Always", new String[]{"Always", "In chat", "Hidden"});
      this.d = this.bool("art", "Album art", null, true);
      this.w = this.bool("visualizer", "Visualizer", "Little bars that move with the music", true);
      this.G = this.mode("colors", "Colours", "Purxiy's purple, or the colour of the album cover", "Purxiy", new String[]{"Purxiy", "Album"});
      this.section("Lyrics");
      this.u = new SpotifyRenderer7(this);
      this.p = this.number("lines", "Lines", "How many lines you see at once", 2.0, 1.0, 5.0, 1.0, Listener.INTEGER);
      this.c = this.mode("width", "Width", null, "Normal", new String[]{"Narrow", "Normal", "Wide"});
      this.t = this.mode("align", "Alignment", "Auto follows where the lyrics are on screen", "Auto", new String[]{"Auto", "Left", "Centre", "Right"});
      this.g = this.bool("sweep", "Word sweep", "The words light up as they are sung", true);
      this.e = this.bool("glow", "Glow", "A soft glow around the line being sung", true);
      this.Z = this.number("lyrics_timing", "Timing", "For a song whose lyrics run early or late", 0.0, -2.0, 2.0, 0.05, v);
      this.section("Playback");
      this.S = this.mode("source", "Follow", "Spotify, or whichever app plays music", "Spotify", new String[]{"Spotify", "Any player"});
      this.y = this.mode("paused", "When paused", null, "Dim", new String[]{"Show", "Dim", "Hide"});
      this.r = this.number("volume_step", "Volume step", "For the volume keys and scrolling", 0.05, 0.01, 0.2, 0.01, Listener.PERCENT);
      this.section("Keys");
      this.O = this.keybind("key_play", "Play / pause", null);
      this.U = this.keybind("key_next", "Next track", null);
      this.i = this.keybind("key_previous", "Previous track", null);
      this.Q = this.keybind("key_volume_up", "Volume up", null);
      this.K = this.keybind("key_volume_down", "Volume down", null);

      for (Setting var6 : List.of(this.m, this.d, this.w, this.G, this.V.size, this.V.background)) {
         var6.visibleWhen(this::showsPlayer);
      }

      this.V.opacity.visibleWhen(() -> {
         SpotifyCurve.ensureSourceClassInitialized();
         return this.showsPlayer() && this.V.background.enabled();
      });
      this.E.visibleWhen(() -> {
         SpotifyCurve.ensureSourceClassInitialized();
         return !this.J.is("Player only");
      });
      this.z.visibleWhen(this::inWorld);
      this.F.visibleWhen(this::inWorld);
      this.L.visibleWhen(() -> {
         SpotifyCurve.ensureSourceClassInitialized();
         return this.inWorld() && !this.inSky();
      });
      this.X.visibleWhen(this::inWorld);
      this.W.visibleWhen(() -> {
         SpotifyCurve.ensureSourceClassInitialized();
         return this.inWorld() && (this.inSky() || this.groundAndSky());
      });
      this.Y.visibleWhen(() -> {
         SpotifyCurve.ensureSourceClassInitialized();
         return !this.J.is("Player only");
      });

      for (Setting var11 : List.of(this.u.size, this.u.background)) {
         var11.visibleWhen(this::showsLyricsStrip);
      }

      this.u.opacity.visibleWhen(() -> {
         SpotifyCurve.ensureSourceClassInitialized();
         return this.showsLyricsStrip() && this.u.background.enabled();
      });
      this.c.visibleWhen(() -> {
         SpotifyCurve.ensureSourceClassInitialized();
         return this.showsLyricsStrip() || this.inWorld();
      });

      for (Setting var12 : List.of(this.p, this.t, this.g, this.e, this.Z)) {
         var12.visibleWhen(() -> {
            SpotifyCurve.ensureSourceClassInitialized();
            return !this.J.is("Player only");
         });
      }

      this.t.visibleWhen(this::showsLyricsStrip);
      this.g.visibleWhen(() -> {
         SpotifyCurve.ensureSourceClassInitialized();
         return !this.J.is("Player only") && !this.inWorld();
      });
      this.enabledByDefault();
   }

   public List<HudModule> parts() {
      return List.of(this.u, this.V);
   }

   boolean showsPlayer() {
      return !this.J.is("Lyrics only");
   }

   public boolean inWorld() {
      return !this.J.is("Player only") && this.E.is("In the world");
   }

   public boolean inSky() {
      return this.z.is("In the sky");
   }

   public boolean groundAndSky() {
      return this.z.is("Ground and sky");
   }

   public boolean onlyOpenSky() {
      return (this.inSky() || this.groundAndSky()) && this.W.enabled();
   }

   public boolean inMenus() {
      return !this.J.is("Player only") && this.Y.enabled();
   }

   boolean showsLyricsStrip() {
      return (this.J.is("Split") || this.J.is("Lyrics only")) && !this.inWorld();
   }

   boolean combined() {
      return this.J.is("Combined") && !this.inWorld();
   }

   void frame(HudBounds var1) {
      this.frame(var1.dt);
   }

   void frame(float var1) {
      RenderPipelines var2 = RenderPipelines.get();
      if (var2.frame() != this.M) {
         this.M = var2.frame();
         SpotifyRegistry var3 = SpotifyRegistry.get();
         var3.keepAlive(this.w.enabled() && this.showsPlayer());
         var3.frame();
         SpotifyCurve var4 = var3.live();
         this.T = var4.track() != null && !var4.playing() ? this.T + var1 : 0.0F;
      }
   }

   boolean visible(SpotifyCurve var1) {
      return var1.track() != null && (!this.y.is("Hide") || !(this.T > 3.0F));
   }

   float pausedAlpha(SpotifyCurve var1) {
      return !var1.sample() && this.y.is("Dim") ? 1.0F - 0.45F * Math.max(0.0F, Math.min(1.0F, (this.T - 1.2F) / 0.6F)) : 1.0F;
   }

   public void onKey(int var1, boolean var2) {
      if (this.enabled() && var1 != -1) {
         SpotifyCurve var3 = SpotifyRegistry.get().live();
         if (var3.track() != null) {
            boolean var4 = this.showsPlayer() && this.V.shownOnScreen();
            if (!var2 && this.O.matches(var1)) {
               var3.togglePlay();
               if (!var4) {
                  HudLayout.push(this.name(), var3.playing() ? "Playing" : "Paused", var3.playing() ? "play" : "pause", true);
               }
            } else if (!var2 && this.U.matches(var1)) {
               var3.next();
               if (!var4) {
                  HudLayout.push(this.name(), "Next track", "skip-forward", true);
               }
            } else if (!var2 && this.i.matches(var1)) {
               var3.previous();
               if (!var4) {
                  HudLayout.push(this.name(), "Previous track", "skip-back", true);
               }
            } else if (this.Q.matches(var1) || this.K.matches(var1)) {
               if (var3.volume() < 0.0F) {
                  HudLayout.push(this.name(), "Play something first to set the volume", "volume-2", false);
                  return;
               }

               float var5 = this.r.floatValue() * (this.Q.matches(var1) ? 1 : -1);
               var3.setVolume(var3.volume() + var5);
               HudLayout.push(
                  this.name(),
                  "Volume " + Math.round(var3.volume() * 100.0F) + "%",
                  var3.volume() <= 0.0F ? "volume-x" : (var3.volume() < 0.5F ? "volume-1" : "volume-2"),
                  true
               );
            }
         }
      }
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ClientServices.acquire(this.id()));
      SpotifyRegistry.install(this, var1);
      var1.own(this.V.start(var1));
      var1.own(this.u.start(var1));
      var1.onFrame(var1x -> this.frame((float)var1x));
      var1.own(ClientSupport3.VIEW.subscribe(0, (Listener10)(var0, var1x, var2, var3) -> SpotifyRenderer5.beginFrame(var0, var1x, var2)));
      var1.own(ClientSupport3.AFTER_WORLD.subscribe(200, SpotifyRenderer5::render));
      var1.own(ClientRenderer8.install(SpotifyRenderer9::draw));
      var1.own(ModuleWidget.onActualAttack((var0, var1x) -> SpotifyRenderer5.attacked(var0, var1x)));
      var1.own(ModuleWidget.onKey((var1x, var2, var3) -> {
         SpotifyCurve.ensureSourceClassInitialized();
         if (var3 != 0 && class_310.method_1551().field_1755 == null) {
            long var4 = 2850671202489L;
            int var6 = var4 < 0L ? -1 : 1;
            this.onKey(var1x, var3 == 2);
         }
      }));
      var1.own(CosmeticRegistry.register(this.id(), this.getClass().getClassLoader(), () -> new SpotifyRenderer8(this)));
      var1.own(SpotifyRegistry::uninstall);
      var1.own(ClientRegistry18.install(new dev.purxiy.loader.fabric.client.ClientRegistry18.Listener() {
         public void frame() {
            SpotifyRegistry.get().frame();
         }
      }));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new SpotifyRenderer10(this);
   }
}
