package dev.purxiy.modules.knife;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.IntegerSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.Setting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.ClientLoader;
import dev.purxiy.loader.fabric.client.ResourceProvider;
import dev.purxiy.loader.fabric.client.AttackAnimationProvider;
import dev.purxiy.loader.fabric.client.HudRenderPass;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.client.ClientUpdater;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter.FrameHook;
import dev.purxiy.loader.fabric.client.ClientUpdater.ModulePayload;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import java.util.List;
import net.minecraft.class_10444;
import net.minecraft.class_11566;
import net.minecraft.class_11659;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_4587;
import net.minecraft.class_6880;
import net.minecraft.class_742;
import net.minecraft.class_811;
import net.minecraft.class_898;

public final class KnifeModule extends ModuleDescriptor {
   public final ModeSetting U;
   public final ModeSetting F;
   private final ModeSetting[] j;
   public final IntegerSetting h;
   public final IntegerSetting g;
   public final BooleanSetting p;
   public final NumberSetting A;
   public final BooleanSetting r;
   public final NumberSetting q;
   public final NumberSetting x;
   public final BooleanSetting D;
   public final NumberSetting C;
   public final BooleanSetting R;
   public final BooleanSetting J;
   public final BooleanSetting H;
   public final ModeSetting B;
   public final NumberSetting w;
   public final BooleanSetting d;
   public final NumberSetting l;
   public final ModeSetting z;
   public final NumberSetting P;
   public final BooleanSetting c;
   public final BooleanSetting n;
   public final BooleanSetting t;

   public KnifeModule() {
      super("knives", "Knives", "Five iconic knives and three blades for both hands", "sword");
      long var1 = 17517801914293L;
      long var3 = var1 ^ 6673685000019L;
      this.U = this.mode("model", "Knife", "Five sculpted blades, each with tricks of its own", "Karambit", KnifeModels.u);
      this.F = this.mode("finish", "Finish", "The blade's metal and handle accents", "Signature", new String[]{"Signature", "Obsidian", "Doppler", "Gold"});
      this.j = new ModeSetting[5];
      this.h = (IntegerSetting)this.add(new IntegerSetting("inspect_key", "Inspect key", "Play a trick; press again to chain the next", 73));
      this.g = (IntegerSetting)this.add(new IntegerSetting("slash_key", "Slash key", "Slash the air; it hits nothing"));
      this.p = this.section("Animation").bool("auto_inspect", "Idle tricks", "Plays with your blade while you stand still", true);
      this.A = this.number("interval", "Trick interval", "Seconds of idle time between tricks", 12.0, 5.0, 30.0, 1.0, var0 -> Math.round(var0) + " s");
      this.r = this.bool("walk_tricks", "Walking tricks", "Keeps playing with your blade as you move", true);
      this.q = this.number("walk_pause", "Pause between", "None: each trick flows straight into the next", 0.0, 0.0, 6.0, 0.1, var0 -> {
         KnifeSwingState.ensureSourceClassInitialized();
         int var2 = 0;
         String var3x;
         if (var0 < 0.05) {
            var3x = "None";
         } else {
            double var4 = var0 % 1.0;
            var2 = 1543203561;
            var3x = (var4 == 0.0 ? String.valueOf(Math.round(var0)) : String.valueOf(Math.round(var0 * 10.0) / 10.0)) + " s";
         }

         return var3x;
      });
      this.x = this.number("flow_tempo", "Tempo on the move", "How fast the tricks play as you move", 1.25, 1.0, 1.6, 0.05, Listener.MULTIPLIER);
      this.D = this.bool("air_slashes", "Air slashes", "Tricks may slash the air in front of you", true);
      this.C = this.number("speed", "Animation speed", "Tempo of every trick and the draw", 1.0, 0.65, 1.5, 0.05, Listener.MULTIPLIER);
      this.R = this.bool("draw", "Draw animation", "A flourish when you equip a sword", true);
      this.J = this.bool("two_handed", "Two-handed tricks", "Your other hand joins in while it holds nothing", true);
      this.H = this.section("Sound").bool("sounds", "Trick sounds", "Clean steel sounds for every flip, spin and catch", true);
      this.B = this.mode(
         "attack_sounds", "Attack sounds", "A swish on every attack, a cut when it hits", "Knife", new String[]{"Knife", "Knife and sword", "Sword"}
      );
      this.w = this.number("volume", "Volume", "Of the knife's sounds", 0.7, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.d = this.section("Placement").bool("offhand", "Off-hand knives", "Replace swords in your other hand too", true);
      this.l = this.number("size", "Knife size", "Scale around the grip", 1.0, 0.7, 1.3, 0.05, Listener.PERCENT);
      this.z = this.mode("weapon", "Weapon", "A knife, or a blade held in both hands", "Knife", z(var3));
      this.P = this.section("Two-handed blade")
         .number(
            "arms_fov",
            "Arms field of view",
            "How wide your arms and the blade are drawn: wider shows more of your arms",
            100.0,
            70.0,
            120.0,
            1.0,
            var0 -> Math.round(var0) + "°"
         );
      this.c = this.bool("slash_arcs", "Slash arcs", "The blade leaves an arc of light as it swings", true);
      this.n = this.bool("hit_effects", "Hit effects", "A cut of light and sparks on every hit", true);
      this.t = this.bool("walk_effects", "Walking effects", "Petals, embers or wisps drift off the blade as you move", true);
      this.A.visibleWhen(this.p::enabled);
      this.q.visibleWhen(this.r::enabled);
      this.x.visibleWhen(this.r::enabled);
      this.w.visibleWhen(() -> {
         KnifeSwingState.ensureSourceClassInitialized();
         boolean var1x = false;
         return this.H.enabled() || !this.B.is("Sword");
      });

      for (int var5 = 0; var5 < this.j.length; var5++) {
         this.j[var5] = (ModeSetting)this.add(
            new ModeSetting(KnifeCamos.settingId(var5), KnifeModels.u[var5] + " camo", "Remembered for this knife", "Original", KnifeCamos.choices(var5))
         );
      }

      this.F.visibleWhen(() -> {
         KnifeSwingState.ensureSourceClassInitialized();
         boolean var1x = false;
         return this.camo() == null || this.great();
      });

      for (Setting var8 : new Setting[]{this.J, this.d, this.l}) {
         var8.visibleWhen(() -> {
            KnifeSwingState.ensureSourceClassInitialized();
            boolean var1x = false;
            return !this.great();
         });
      }

      for (Setting var13 : new Setting[]{this.P, this.c, this.n, this.t}) {
         var13.visibleWhen(this::great);
      }
   }

   private static String[] z(long var0) {
      String[] var2 = new String[KnifeBlades.B.length + 1];
      var2[0] = "Knife";
      System.arraycopy(KnifeBlades.B, 0, var2, 1, KnifeBlades.B.length);
      return var2;
   }

   public boolean great() {
      return KnifeBlades.index((String)this.z.get()) >= 0;
   }

   public ModeSetting camoSetting(int var1) {
      return this.j[var1];
   }

   public ModeSetting camoSetting() {
      return this.camoSetting(KnifeModels.index((String)this.U.get()));
   }

   public KnifeCamos.Camo camo() {
      return KnifeCamos.find(KnifeModels.index((String)this.U.get()), (String)this.camoSetting().get());
   }

   public KnifeModels.Model geometry() {
      return KnifeModels.get(KnifeModels.index((String)this.U.get()), (String)this.F.get(), (String)this.camoSetting().get());
   }

   public boolean collectionSetting(Setting<?> var1) {
      if (var1 != this.U && var1 != this.F && var1 != this.z) {
         for (ModeSetting var5 : this.j) {
            if (var5 == var1) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   protected void onDisable() {
      KnifeSwingState.reset();
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ResourceProvider.install(
            var1,
            List.of(
               "assets/purxiy/knife/tricks.txt",
               "assets/purxiy/sounds/knife/catch_1.ogg",
               "assets/purxiy/sounds/knife/catch_2.ogg",
               "assets/purxiy/sounds/knife/catch_3.ogg",
               "assets/purxiy/sounds/knife/clack_1.ogg",
               "assets/purxiy/sounds/knife/clack_2.ogg",
               "assets/purxiy/sounds/knife/clack_3.ogg",
               "assets/purxiy/sounds/knife/click_1.ogg",
               "assets/purxiy/sounds/knife/click_2.ogg",
               "assets/purxiy/sounds/knife/click_3.ogg",
               "assets/purxiy/sounds/knife/crit_1.ogg",
               "assets/purxiy/sounds/knife/crit_2.ogg",
               "assets/purxiy/sounds/knife/crit_3.ogg",
               "assets/purxiy/sounds/knife/draw_1.ogg",
               "assets/purxiy/sounds/knife/draw_2.ogg",
               "assets/purxiy/sounds/knife/draw_3.ogg",
               "assets/purxiy/sounds/knife/hit_1.ogg",
               "assets/purxiy/sounds/knife/hit_2.ogg",
               "assets/purxiy/sounds/knife/hit_3.ogg",
               "assets/purxiy/sounds/knife/latch_1.ogg",
               "assets/purxiy/sounds/knife/latch_2.ogg",
               "assets/purxiy/sounds/knife/latch_3.ogg",
               "assets/purxiy/sounds/knife/ring_1.ogg",
               "assets/purxiy/sounds/knife/ring_2.ogg",
               "assets/purxiy/sounds/knife/ring_3.ogg",
               "assets/purxiy/sounds/knife/ringcatch_1.ogg",
               "assets/purxiy/sounds/knife/ringcatch_2.ogg",
               "assets/purxiy/sounds/knife/ringcatch_3.ogg",
               "assets/purxiy/sounds/knife/slash_1.ogg",
               "assets/purxiy/sounds/knife/slash_2.ogg",
               "assets/purxiy/sounds/knife/slash_3.ogg",
               "assets/purxiy/sounds/knife/swing_1.ogg",
               "assets/purxiy/sounds/knife/swing_2.ogg",
               "assets/purxiy/sounds/knife/swing_3.ogg",
               "assets/purxiy/sounds/knife/swish_1.ogg",
               "assets/purxiy/sounds/knife/swish_2.ogg",
               "assets/purxiy/sounds/knife/swish_3.ogg",
               "assets/purxiy/sounds/knife/tap_1.ogg",
               "assets/purxiy/sounds/knife/tap_2.ogg",
               "assets/purxiy/sounds/knife/tap_3.ogg",
               "assets/purxiy/sounds/knife/toss_1.ogg",
               "assets/purxiy/sounds/knife/toss_2.ogg",
               "assets/purxiy/sounds/knife/toss_3.ogg",
               "assets/purxiy/sounds/knife/whoosh_1.ogg",
               "assets/purxiy/sounds/knife/whoosh_2.ogg",
               "assets/purxiy/sounds/knife/whoosh_3.ogg",
               "assets/purxiy/sounds/wield/blade_draw_1.ogg",
               "assets/purxiy/sounds/wield/blade_draw_2.ogg",
               "assets/purxiy/sounds/wield/blade_draw_3.ogg",
               "assets/purxiy/sounds/wield/blade_hit_1.ogg",
               "assets/purxiy/sounds/wield/blade_hit_2.ogg",
               "assets/purxiy/sounds/wield/blade_hit_3.ogg",
               "assets/purxiy/sounds/wield/blade_swish_1.ogg",
               "assets/purxiy/sounds/wield/blade_swish_2.ogg",
               "assets/purxiy/sounds/wield/blade_swish_3.ogg",
               "assets/purxiy/sounds/wield/crit_1.ogg",
               "assets/purxiy/sounds/wield/crit_2.ogg",
               "assets/purxiy/sounds/wield/crit_3.ogg",
               "assets/purxiy/sounds/wield/flick_1.ogg",
               "assets/purxiy/sounds/wield/flick_2.ogg",
               "assets/purxiy/sounds/wield/flick_3.ogg",
               "assets/purxiy/sounds/wield/glint_1.ogg",
               "assets/purxiy/sounds/wield/glint_2.ogg",
               "assets/purxiy/sounds/wield/glint_3.ogg",
               "assets/purxiy/sounds/wield/grip_1.ogg",
               "assets/purxiy/sounds/wield/grip_2.ogg",
               "assets/purxiy/sounds/wield/grip_3.ogg",
               "assets/purxiy/sounds/wield/heavy_draw_1.ogg",
               "assets/purxiy/sounds/wield/heavy_draw_2.ogg",
               "assets/purxiy/sounds/wield/heavy_draw_3.ogg",
               "assets/purxiy/sounds/wield/heavy_hit_1.ogg",
               "assets/purxiy/sounds/wield/heavy_hit_2.ogg",
               "assets/purxiy/sounds/wield/heavy_hit_3.ogg",
               "assets/purxiy/sounds/wield/heavy_swish_1.ogg",
               "assets/purxiy/sounds/wield/heavy_swish_2.ogg",
               "assets/purxiy/sounds/wield/heavy_swish_3.ogg",
               "assets/purxiy/sounds/wield/tool_grab_1.ogg",
               "assets/purxiy/sounds/wield/tool_grab_2.ogg",
               "assets/purxiy/sounds/wield/tool_grab_3.ogg",
               "assets/purxiy/sounds/wield/tool_swish_1.ogg",
               "assets/purxiy/sounds/wield/tool_swish_2.ogg",
               "assets/purxiy/sounds/wield/tool_swish_3.ogg",
               "assets/purxiy/textures/knife/camos/autotronic.png",
               "assets/purxiy/textures/knife/camos/autotronic.png.mcmeta",
               "assets/purxiy/textures/knife/camos/autotronic_swatch.png",
               "assets/purxiy/textures/knife/camos/black_laminate.png",
               "assets/purxiy/textures/knife/camos/black_laminate.png.mcmeta",
               "assets/purxiy/textures/knife/camos/black_laminate_swatch.png",
               "assets/purxiy/textures/knife/camos/candy.png",
               "assets/purxiy/textures/knife/camos/candy.png.mcmeta",
               "assets/purxiy/textures/knife/camos/candy_swatch.png",
               "assets/purxiy/textures/knife/camos/case_hardened.png",
               "assets/purxiy/textures/knife/camos/case_hardened.png.mcmeta",
               "assets/purxiy/textures/knife/camos/case_hardened_swatch.png",
               "assets/purxiy/textures/knife/camos/chroma.png",
               "assets/purxiy/textures/knife/camos/chroma.png.mcmeta",
               "assets/purxiy/textures/knife/camos/chroma_swatch.png",
               "assets/purxiy/textures/knife/camos/cod_damascus.png",
               "assets/purxiy/textures/knife/camos/cod_damascus.png.mcmeta",
               "assets/purxiy/textures/knife/camos/cod_damascus_swatch.png",
               "assets/purxiy/textures/knife/camos/cod_gold.png",
               "assets/purxiy/textures/knife/camos/cod_gold.png.mcmeta",
               "assets/purxiy/textures/knife/camos/cod_gold_swatch.png",
               "assets/purxiy/textures/knife/camos/crimson_web.png",
               "assets/purxiy/textures/knife/camos/crimson_web.png.mcmeta",
               "assets/purxiy/textures/knife/camos/crimson_web_swatch.png",
               "assets/purxiy/textures/knife/camos/damascus_steel.png",
               "assets/purxiy/textures/knife/camos/damascus_steel.png.mcmeta",
               "assets/purxiy/textures/knife/camos/damascus_steel_swatch.png",
               "assets/purxiy/textures/knife/camos/doppler.png",
               "assets/purxiy/textures/knife/camos/doppler.png.mcmeta",
               "assets/purxiy/textures/knife/camos/doppler_swatch.png",
               "assets/purxiy/textures/knife/camos/fade.png",
               "assets/purxiy/textures/knife/camos/fade.png.mcmeta",
               "assets/purxiy/textures/knife/camos/fade_swatch.png",
               "assets/purxiy/textures/knife/camos/gemstone.png",
               "assets/purxiy/textures/knife/camos/gemstone.png.mcmeta",
               "assets/purxiy/textures/knife/camos/gemstone_swatch.png",
               "assets/purxiy/textures/knife/camos/ice_shard.png",
               "assets/purxiy/textures/knife/camos/ice_shard.png.mcmeta",
               "assets/purxiy/textures/knife/camos/ice_shard_swatch.png",
               "assets/purxiy/textures/knife/camos/interstellar.png",
               "assets/purxiy/textures/knife/camos/interstellar.png.mcmeta",
               "assets/purxiy/textures/knife/camos/interstellar_swatch.png",
               "assets/purxiy/textures/knife/camos/lore.png",
               "assets/purxiy/textures/knife/camos/lore.png.mcmeta",
               "assets/purxiy/textures/knife/camos/lore_swatch.png",
               "assets/purxiy/textures/knife/camos/marble_fade.png",
               "assets/purxiy/textures/knife/camos/marble_fade.png.mcmeta",
               "assets/purxiy/textures/knife/camos/marble_fade_swatch.png",
               "assets/purxiy/textures/knife/camos/orion.png",
               "assets/purxiy/textures/knife/camos/orion.png.mcmeta",
               "assets/purxiy/textures/knife/camos/orion_swatch.png",
               "assets/purxiy/textures/knife/camos/sapphire.png",
               "assets/purxiy/textures/knife/camos/sapphire.png.mcmeta",
               "assets/purxiy/textures/knife/camos/sapphire_swatch.png",
               "assets/purxiy/textures/knife/camos/slaughter.png",
               "assets/purxiy/textures/knife/camos/slaughter.png.mcmeta",
               "assets/purxiy/textures/knife/camos/slaughter_swatch.png",
               "assets/purxiy/textures/knife/camos/tiger_tooth.png",
               "assets/purxiy/textures/knife/camos/tiger_tooth.png.mcmeta",
               "assets/purxiy/textures/knife/camos/tiger_tooth_swatch.png",
               "assets/purxiy/textures/knife/white.png",
               "assets/purxiy/wield/greatsword.txt",
               "assets/purxiy/wield/katana.txt",
               "assets/purxiy/wield/scythe.txt"
            )
         )
      );
      var1.own(ClientLoader.install(var1, "assets/purxiy/sound-events-knife.json"));
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/knife_preview.fsh",
               "shaders/core/knife_preview.vsh",
               "shaders/core/wield_arc.fsh",
               "shaders/core/wield_arc.vsh",
               "shaders/include/theme.glsl",
               "shaders/include/tint.glsl",
               "shaders/include/wield.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      KnifeSwingState.install(this, var1);
      var1.own(ClientUpdater.install(new dev.purxiy.loader.fabric.client.ClientUpdater.Listener() {
         public boolean update(class_10444 var1, class_1799 var2, class_811 var3, class_11566 var4) {
            return KnifeUpdater.update(var1, var2, var3, var4);
         }

         public void submit(ModulePayload var1, class_811 var2, class_4587 var3, class_11659 var4, int var5, int var6, boolean var7, int var8) {
            KnifeUpdater.submit(var1, var2, var3, var4, var5, var6, var7, var8);
         }
      }));
      var1.own(
         HudRenderPass.install(
            new dev.purxiy.loader.fabric.client.HudRenderPass.Listener() {
               public boolean render(
                  class_742 var1, class_1268 var2, class_1799 var3, float var4, float var5, class_4587 var6, class_11659 var7, int var8, class_898 var9
               ) {
                  return KnifeMaterialMesh.render(var1, var2, var3, var4, var5, var6, var7, var8, var9);
               }
            }
         )
      );
      var1.own(ModuleWidget.onTick(KnifeSwingState::tick));
      var1.own(ModuleWidget.onDisconnect(var0 -> {
         KnifeSwingState.reset();
         KnifeAttackShell.reset();
      }));
      var1.own(ModuleWidget.onKey((var0, var1x, var2) -> {
         long var3 = 58184662080315L;
         if (var2 == 1 && class_310.method_1551().field_1755 == null) {
            KnifeSwingState.onKey(var0);
         }
      }));
      var1.own(SkyPipelineRouter.VIEW.subscribe(0, (FrameHook)(var0, var1x, var2, var3) -> KnifeWieldPipeline.level(var0, var1x, var2)));
      var1.onFrame(var0 -> KnifeWieldPipeline.frame());
      var1.own(SkyPipelineRouter.HAND_COMPOSITE.subscribe(20, KnifeWieldPipeline::composite));
      var1.own(CosmeticRegistry.register(this.id(), this.getClass().getClassLoader(), () -> new KnifeEditorScreen(this)));
      var1.own(KnifeSwingState::uninstall);
      var1.own(KnifeWieldPipeline::releaseResources);
      var1.own(KnifeWieldPipelines::releaseResources);
      var1.own(AttackAnimationProvider.install(new dev.purxiy.loader.fabric.client.AttackAnimationProvider.Listener() {
         public boolean replaces(class_1799 var1, class_1268 var2) {
            return KnifeSwingState.replaces(var1, var2);
         }

         public boolean inspect() {
            return KnifeSwingState.inspect();
         }

         public boolean slash() {
            return KnifeSwingState.slash();
         }

         public void onSwing() {
            KnifeSwingState.onSwing();
         }

         public void attackStarts() {
            KnifeSwingState.attackStarts();
         }

         public void attackSwings() {
            KnifeSwingState.attackSwings();
         }

         public void onAttack(class_1657 var1, class_1297 var2) {
            KnifeSwingState.onAttack(var1, var2);
         }

         public boolean mutes(class_6880<class_3414> var1, double var2, double var4, double var6) {
            return KnifeSwingState.mutes(var1, var2, var4, var6);
         }

         public boolean inspecting() {
            return KnifeSwingState.inspecting();
         }
      }));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new KnifeEditorPage(this);
   }
}
