package dev.purxiy.modules.targethud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudModule;
import dev.purxiy.hud.HudPlacement;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.loader.fabric.client.TargetProvider;
import dev.purxiy.loader.fabric.client.TabListProvider;
import dev.purxiy.loader.fabric.client.NametagCapturePipeline;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.mixin.LivingEntityAccessor;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.font.TrueTypeFont;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1044;
import net.minecraft.class_1068;
import net.minecraft.class_11890;
import net.minecraft.class_11901;
import net.minecraft.class_12137;
import net.minecraft.class_1291;
import net.minecraft.class_1294;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2394;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3489;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_6880;
import net.minecraft.class_746;
import net.minecraft.class_7923;
import net.minecraft.class_9334;
import net.minecraft.class_9381;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;

public final class TargetHud extends HudModule {
   private static final float H = 180.0F;
   private static final float rp = 66.0F;
   public static final float A = 180.0F;
   public static final float rV = 66.0F;
   private static final float e = 7.0F;
   private static final float Z = 9.0F;
   private static final float P = 26.0F;
   private static final float q = 6.0F;
   private static final float rg = 41.0F;
   private static final float rf = 13.5F;
   private static final float rG = 27.0F;
   private static final float rq = 38.5F;
   private static final float y = 3.5F;
   private static final float r0 = 44.0F;
   private static final float rA = 1.5F;
   private static final float rj = 49.0F;
   private static final float G = 12.0F;
   private static final float rx = 4.0F;
   private static final float Y = 52.666668F;
   private static final float rJ = 11.0F;
   private static final float C = 0.45F;
   private static final float I = 0.26F;
   private static final float N = 3.4F;
   private static final float rc = 6.8F;
   private static final float s = 0.2F;
   private static final float rw = 0.1F;
   private static final float f = 7.0F;
   private static final float rR = 5.0F;
   private static final float E = 0.34F;
   private static final float J = 0.5F;
   private static final int i = -11654;
   private static final int o = -7670328;
   private static final int k = -41852;
   private static final int rD = -30116;
   private static final float M = -1000.0F;
   private static final Easing V = Easing.EMPHASIZED;
   private static final Easing g = Easing.ACCELERATE;
   private static final Easing r_ = Easing.bezier(0.34F, 1.45F, 0.64F, 1.0F);
   private static final class_1304[] r5 = new class_1304[]{class_1304.field_6169, class_1304.field_6174, class_1304.field_6172, class_1304.field_6166};
   private static final UUID rW = UUID.nameUUIDFromBytes("purxiy:target-sample".getBytes(StandardCharsets.UTF_8));
   private static Int2ObjectOpenHashMap<class_6880<class_1291>> p;
   private static Map<class_6880<class_1291>, String> rU;
   public final ModeSetting a;
   private final NumberSetting d;
   private final NumberSetting rL;
   private final ModeSetting S;
   private final BooleanSetting h;
   private final BooleanSetting rz;
   private final ModeSetting R;
   private final BooleanSetting[] j = new BooleanSetting[PvpMode.values().length];
   private final ModeSetting K;
   private final BooleanSetting z;
   private final ModeSetting n;
   private TargetHud.TargetPopup ry;
   private TargetHud.TargetPopup b;
   private float rt;
   private boolean u;
   private long B = -1L;
   private PvpMode x = PvpMode.s;
   private PvpMode rS;
   private String T = "";
   private String rF = "";
   private float t = -1000.0F;
   private float rX = 1.0F;
   private final Animator rO = new Animator(0.0F, 420.0F, 40.0F);
   private boolean X = true;
   private final Object2FloatOpenHashMap<UUID> O = new Object2FloatOpenHashMap();
   private final TargetHud.TargetHudState[] v = new TargetHud.TargetHudState[]{new TargetHud.TargetHudState(), new TargetHud.TargetHudState(), new TargetHud.TargetHudState()};
   private final TargetHud.TargetHudState[] c = new TargetHud.TargetHudState[]{new TargetHud.TargetHudState(), new TargetHud.TargetHudState(), new TargetHud.TargetHudState()};
   private class_1799[] L;
   private class_1799[] rh;
   private final float[] W = new float[2];
   private final float[] F = new float[4];
   private int rE = 1;
   private int rB = 1;
   private float U = 1.0F;
   private float rn = 1.0F;
   private float r4;
   private float r;
   private float l;
   private float Q;
   private float w;
   private float m = 1.0F;
   private boolean r1;
   private final Animator D = new Animator(0.0F, 420.0F, 42.0F);
   private final Animator rs = Animator.smooth(1.0F);
   public static int rK = 0;

   public TargetHud() {
      super("target_hud", "Target HUD", "Health and fight stats of whoever you aim at", "crosshair", 0.62, 0.56);
      this.a = this.mode(
         "placement", "Placement", "On your screen, or in the world right beside whoever you fight", "Screen", new String[]{"Screen", "Beside target"}
      );
      this.d = this.number("range", "Range", "How far away someone can be when you aim at them", 12.0, 3.0, 64.0, 1.0, Listener.unit(" blocks"));
      this.rL = this.number("hold", "Hold time", "How long it stays after you look away", 3.0, 0.0, 10.0, 0.5, Listener.unit("s"));
      this.S = this.mode("targets", "Targets", null, "Players", new String[]{"Players", "Players & mobs"});
      this.h = this.bool("fights", "Lock on in fights", "Also show whoever you hit or who hits you", true);
      this.rz = this.bool("auto_detect", "Auto-detect PvP", "Recognise the mode from gear, items and the fight", true);
      this.R = this.mode("pvp", "PvP mode", "Which stats to show", PvpMode.U.R, PvpMode.labels());
      this.R.visibleWhen(() -> !this.rz.enabled());

      for (PvpMode var4 : PvpMode.values()) {
         if (var4 != PvpMode.s) {
            BooleanSetting var5 = this.bool("detect_" + var4.name().toLowerCase(Locale.ROOT), var4.S, var4.C, true);
            var5.visibleWhen(this.rz::enabled);
            this.j[var4.ordinal()] = var5;
         }
      }

      this.K = this.mode("health", "Health", null, "Hearts", new String[]{"Hearts", "Points", "Percent"});
      this.z = this.bool("damage_numbers", "Damage numbers", "Hits and heals float up from the health bar", true);
      this.n = this.mode("sounds", "Sounds", "Lock-on, totem pops and eliminations", "All", new String[]{"All", "Events", "Off"});
      this.O.defaultReturnValue(-1000.0F);
      this.enabledByDefault();
   }

   public float range() {
      return this.d.floatValue();
   }

   public float holdSeconds() {
      return this.rL.floatValue();
   }

   public boolean includesMobs() {
      return !this.S.is("Players");
   }

   public boolean locksInFights() {
      return this.h.enabled();
   }

   public boolean detects(PvpMode var1) {
      BooleanSetting var2 = this.j[var1.ordinal()];
      return var2 != null && var2.enabled();
   }

   public boolean beside() {
      return this.a.is("Beside target");
   }

   protected void onDisable() {
      this.ry = null;
      this.b = null;
      this.rt = 0.0F;
      this.u = false;
      this.r1 = false;
   }

   protected boolean hasContent(HudBounds var1) {
      long var2 = 47435793210286L;
      long var4 = var2 ^ 16263883476352L;
      this.i(var1, var4);
      return this.ry != null;
   }

   protected boolean shown() {
      return this.enabled() && (!this.beside() || !"hud".equals(CosmeticRegistry.currentOwner()) || this.ry != null || TargetUpdater.target() != null);
   }

   public void renderSample(Canvas var1, HudBounds var2) {
      this.render(var1, var2);
   }

   public float healthShown() {
      long var1 = 56992424996253L;
      long var3 = var1 ^ 46924606216400L;
      TargetHud.TargetPopup var5 = this.ry;
      return var5 == null ? 1.0F : W(var5.E / var5.e, var3);
   }

   public float absorptionShown() {
      long var1 = 134325227844469L;
      long var3 = var1 ^ 109212130494008L;
      TargetHud.TargetPopup var5 = this.ry;
      return var5 == null ? 0.0F : W(var5.o / var5.e, var3);
   }

   protected void measure(HudBounds var1, float[] var2) {
      long var3 = 130692073799059L;
      long var5 = var3 ^ 91479142296509L;
      this.i(var1, var5);
      var2[0] = 180.0F;
      var2[1] = 66.0F;
   }

   private float e(long var1) {
      return this.u ? V.apply(this.rt) : 1.0F - g.apply(1.0F - this.rt);
   }

   private void e(long var1, boolean var3) {
      long var4 = 12938981666123L;
      if (var3 != this.u) {
         float var6 = this.e(var4);
         this.u = var3;
         float var7 = 0.0F;
         float var8 = 1.0F;

         for (int var9 = 0; var9 < 18; var9++) {
            long var10 = 132730111941639L;
            int var13 = var10 < 0L ? -1 : 1;
            this.rt = (var7 + var8) / 2.0F;
            long var13x = 132730111941640L;
            int var16 = var13x < 0L ? -1 : 1;
            if (this.e(var4) < var6) {
               var7 = this.rt;
               long var16x = 132730111941639L;
            } else {
               var8 = this.rt;
            }
         }

         long var19 = 132730111941639L;
         int var11 = var19 < 0L ? -1 : 1;
         this.rt = (var7 + var8) / 2.0F;
      }
   }

   private void i(HudBounds var1, long var2) {
      long var4 = 132087030886385L;
      long var6 = 13089001651465L;
      long var8 = 26179959199954L;
      long var10 = 110351255260530L;
      byte var12 = 0;
      long var13 = 119528890862957L;
      long var15 = 138961779395969L;
      long var17 = 16785335916959L;
      short var19 = 3908;
      int var20 = 2358297;
      int var21 = (int)(var17 << 56 >>> 56);
      long var22 = 132730111941640L;
      long var24 = 30807060807413L;
      long var26 = RenderPipelines.get().frame();
      if (var26 != this.B) {
         this.B = var26;
         float var28 = var1.time;
         float var29 = MotionStyle.MENU.dt(var1.dt);
         float var30 = MotionStyle.HOVER.dt(var1.dt);
         class_1309 var31 = var1.live() ? TargetUpdater.target() : null;
         TargetHud.TargetPopup var32 = this.ry;
         if (var31 != null) {
            float var54 = TargetUpdater.acquiredAt();
            long var34 = 41705128973869L;
            int var57 = var34 < 0L ? -1 : 1;
            long var37 = 41705128973869L;
            int var61 = var37 < 0L ? -1 : 1;
            if (var32 == null || var32.ek != var31) {
               TargetHud.TargetPopup var59 = new TargetHud.TargetPopup(var31, var54);
               this.A((char)var12, var59, var13, var28);
               long var43 = 41705128973870L;
               int var65 = var43 < 0L ? -1 : 1;
            } else {
               label197:
               if (var32.V != var54) {
                  if (this.u) {
                     float var40;
                     int var41 = (var40 = this.rt - 0.9F) == 0.0F ? 0 : (var40 < 0.0F ? -1 : 1);
                     long var42 = 41705128973869L;
                     int var42x = var42 < 0L ? -1 : 1;
                     if (var41 > 0) {
                        var32.V = var54;
                        long var45 = 41705128973869L;
                        int var49 = var45 < 0L ? -1 : 1;
                        break label197;
                     }
                  }

                  TargetHud.TargetPopup var58 = new TargetHud.TargetPopup(var31, var54);
                  this.A((char)var12, var58, var13, var28);
               }
            }

            long var60 = 41705128973870L;
            int var68 = var60 < 0L ? -1 : 1;
         } else if (var1.editing && !this.beside()) {
            long var33 = 41705128973869L;
            int var36 = var33 < 0L ? -1 : 1;
            if (var32 == null || !var32.k) {
               TargetHud.TargetPopup var36x = new TargetHud.TargetPopup(null, var28);
               this.A((char)var12, var36x, var13, var28);
            }
         }

         boolean var55;
         label181: {
            label180: {
               var32 = this.ry;
               if (var32 != null) {
                  if (var31 != null) {
                     break label180;
                  }

                  long var56 = 41705128973869L;
                  int var55x = var56 < 0L ? -1 : 1;
                  if (var1.editing && !this.beside()) {
                     break label180;
                  }
               }

               var55 = false;
               break label181;
            }

            var55 = true;
         }

         long var35 = 41705128973870L;
         int var56 = var35 < 0L ? -1 : 1;
         if (!var55) {
            long var38 = 41705128973869L;
            int var631 = var38 < 0L ? -1 : 1;
            if (this.u) {
               long var61 = 41705128973869L;
               int var69 = var61 < 0L ? -1 : 1;
               if (var32 != null) {
                  long var63x = 41705128973870L;
                  int var75 = var63x < 0L ? -1 : 1;
                  if (!var32.k && var32.t == -1000.0F && var32.eM > 1.0F) {
                     this.b(TargetKind.TARGET_RELEASE, 1.0F, var15, false);
                  }
               }
            }
         }

         this.e(var22, var55);
         this.rt = this.u ? Math.min(1.0F, this.rt + var29 / 0.45F) : Math.max(0.0F, this.rt - var29 / 0.26F);
         if (!this.u && this.rt <= 0.0F) {
            if (var32 != null && !var32.k) {
               this.O.put(var32.q, var28);
            }

            this.ry = null;
            this.b = null;
            this.X = true;
            this.r1 = false;
            this.rE = 1;
            this.rB = 1;
            this.U = 1.0F;
            this.rn = 1.0F;
         } else if (var32 != null) {
            PvpMode var57 = this.A(var8, var32, var28);
            long var39 = 41705128973869L;
            int var66 = var39 < 0L ? -1 : 1;
            long var62 = 41705128973870L;
            int var71 = var62 < 0L ? -1 : 1;
            if (var32.k) {
               long var64 = 41705128973870L;
               int var43 = var64 < 0L ? -1 : 1;
               this.x(var32, var4, var57, var28);
            } else if (var32.ek != null) {
               this.F(var19, var32, var32.ek, var20, var1, var57, (byte)var21);
            }

            this.D(var57, var24, var32, var28);
            this.z(var32, var28, var10, var29, var30);
            TargetHud.TargetPopup var65 = this.b;
            long var46 = 41705128973869L;
            int var77 = var46 < 0L ? -1 : 1;
            if (var65 != null) {
               var65.A += var29;
               this.z(var65, var28, var10, var29, var30);
               if (var65.A > 0.2F) {
                  this.b = null;
               }
            }

            float var49 = p(A(var6, var57, var32));
            long var50 = 41705128973870L;
            int var47 = var50 < 0L ? -1 : 1;
            if (this.X) {
               this.rO.snap(var49);
               this.X = false;
            } else {
               this.rO.target(var49).update(MotionStyle.TOGGLE.dt(var1.dt));
            }
         }
      }
   }

   private void A(char var1, TargetHud.TargetPopup var2, long var3, float var5) {
      long var6 = 119528890862957L;
      long var8 = var6 ^ 20716546883820L;
      long var10 = var6 ^ 113736814911526L;
      TargetHud.TargetPopup var12 = this.ry;
      boolean var13 = var12 != null && this.u && this.e(var10) > 0.05F;
      this.b = var13 ? var12 : null;
      if (this.b != null) {
         this.b.A = 0.0F;
      }

      boolean var14 = false;
      if (var13 && this.r1 && !var2.k) {
         this.rn = 0.0F;
         this.r4 = this.Q;
         this.r = this.w;
         this.l = this.m;
      }

      this.ry = var2;
      var14 = false;
      if (!var2.k) {
         float var15;
         int var16 = (var15 = var5 - this.O.getFloat(var2.q) - 12.0F) == 0.0F ? 0 : (var15 < 0.0F ? -1 : 1);
         var14 = false;
         if (var16 > 0) {
            this.b(TargetKind.TARGET_LOCK, 1.0F, var8, false);
         }

         if (this.O.size() > 128) {
            this.O.object2FloatEntrySet().removeIf(var1x -> var5 - var1x.getFloatValue() > 60.0F);
         }

         this.O.put(var2.q, var5);
      }
   }

   private PvpMode A(long var1, TargetHud.TargetPopup var3, float var4) {
      if (!this.rz.enabled()) {
         return PvpMode.byLabel((String)this.R.get());
      } else if (!var3.k) {
         return TargetScore.mode();
      } else {
         ArrayList var5 = new ArrayList();
         PvpMode[] var6 = PvpMode.values();
         int var7 = var6.length;

         for (int var8 = 0; var8 < var7; var8++) {
            long var9 = 26179959199953L;
            int var12 = var9 < 0L ? -1 : 1;
            PvpMode var12x = var6[var8];
            long var13 = 26179959199953L;
            int var16 = var13 < 0L ? -1 : 1;
            boolean var16x = this.detects(var12x);
            long var17 = 26179959199953L;
            int var17x = var17 < 0L ? -1 : 1;
            if (var16x) {
               var5.add(var12x);
            }
         }

         long var19 = 26179959199953L;
         int var10 = var19 < 0L ? -1 : 1;
         return var5.isEmpty() ? PvpMode.s : (PvpMode)var5.get((int)(var4 / 3.4F) % var5.size());
      }
   }

   private void D(PvpMode var1, long var2, TargetHud.TargetPopup var4, float var5) {
      long var6 = 13089001651465L;
      long var8 = 92313731998783L;
      long var10 = 12938981666123L;
      if (var1 != this.x) {
         boolean var12 = this.e(var10) > 0.3F && var4.eM > 0.35F;
         long var13 = 30807060807413L;
         int var16 = var13 < 0L ? -1 : 1;
         if (var12) {
            long var16x = 30807060807412L;
            int var19 = var16x < 0L ? -1 : 1;
            this.rS = this.x;
            this.T = A(var6, this.x, var4);
            this.rF = a(var8, this.x, var4);
            this.t = var5;
            if (!var4.k) {
               boolean var19x = this.n.is("All");
               long var20 = 30807060807413L;
               int var20x = var20 < 0L ? -1 : 1;
               if (var19x) {
                  playSound(TargetKind.ORBIT_TICK, 1.15F, 0.7F);
               }
            }
         } else {
            this.rS = null;
         }

         this.x = var1;
      }

      this.rX = this.rS == null ? 1.0F : Math.min(1.0F, (var5 - this.t) * MotionStyle.MENU.speed() / 0.45F);
      if (this.rX >= 1.0F) {
         this.rS = null;
      }
   }

   private void b(TargetKind var1, float var2, long var3, boolean var5) {
      boolean var6 = this.n.is("Off");
      long var7 = 138961779395969L;
      int var9 = var7 < 0L ? -1 : 1;
      if (!var6 && (var5 || this.n.is("All"))) {
         playSound(var1, var2);
      }
   }

   protected boolean pin(HudBounds var1, float[] var2) {
      long var3 = 118631184461137L;
      long var5 = var3 ^ 9143286016662L;
      TargetHud.TargetPopup var7 = this.ry;
      if (this.beside() && var7 != null && !var7.k) {
         float var8 = MotionStyle.MENU.dt(var1.dt);
         boolean var9 = !this.r1;
         boolean var10 = HudPlacement.fresh() && this.a(var7, var5, var1, var2, var8, var9);
         if (var10) {
            if (this.rn < 1.0F) {
               this.rn = Math.min(1.0F, this.rn + var8 / 0.5F);
               float var11 = Easing.STANDARD.apply(this.rn);
               var2[0] = class_3532.method_16439(var11, this.r4, var2[0]);
               var2[1] = class_3532.method_16439(var11, this.r, var2[1]);
               var2[2] = class_3532.method_16439(var11, this.l, var2[2]);
            }

            this.Q = var2[0];
            this.w = var2[1];
            this.m = var2[2];
            this.r1 = true;
         } else if (this.r1) {
            var2[0] = this.Q;
            var2[1] = this.w;
            var2[2] = this.m;
         } else {
            var2[0] = -720.0F;
            var2[1] = -264.0F;
            var2[2] = this.scale();
         }

         if (var9) {
            this.rs.snap(var10 ? 1.0F : 0.0F);
         } else {
            this.rs.target(var10 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         }

         return true;
      } else {
         this.r1 = false;
         return false;
      }
   }

   private boolean a(TargetHud.TargetPopup var1, long var2, HudBounds var4, float[] var5, float var6, boolean var7) {
      long var8 = 93246394192873L;
      class_1309 var10 = var1.ek;
      if (var10 != null) {
         boolean var11 = var10.method_31481();
         long var12 = 109625547114438L;
         int var14 = var12 < 0L ? -1 : 1;
         if (!var11) {
            float var15 = HudPlacement.partialTick();
            var1.Q = class_3532.method_16436(var15, var10.field_6038, var10.method_23317());
            var1.x = class_3532.method_16436(var15, var10.field_5971, var10.method_23318());
            var1.eT = class_3532.method_16436(var15, var10.field_5989, var10.method_23321());
            var1.f = var10.method_17682() + (var10 instanceof class_11890 ? 0.1F : 0.0F);
            var1.n = var10.method_17681() / 2.0F;
            var1.X = true;
         }
      }

      if (!var1.X) {
         return false;
      } else {
         float var52 = var4.width;
         float var53 = var4.height;
         float[] var13 = this.W;
         double var14 = var1.Q;
         double var16 = var1.eT;
         double var18 = var1.x + var1.f;
         double var20 = var1.x + var1.f * 0.55;
         HudPlacement.across(var14, var16, var13);
         double var22 = var13[0] * (var1.n + 0.2F);
         double var24 = var13[1] * (var1.n + 0.2F);
         if (!HudPlacement.project(var14, var18, var16, var52, var53, var13)) {
            return false;
         } else {
            float var26 = var13[1];
            float var27 = -Float.MAX_VALUE;
            float var28 = Float.MAX_VALUE;

            for (int var29 = 0; var29 < 2; var29++) {
               long var30 = 109625547114439L;
               int var59 = var30 < 0L ? -1 : 1;
               double var33 = var29 == 0 ? var18 : var20;
               boolean var35 = HudPlacement.project(var14 + var22, var33, var16 + var24, var52, var53, var13);
               long var36 = 109625547114438L;
               int var32 = var36 < 0L ? -1 : 1;
               if (!var35) {
                  return false;
               }

               long var38 = 109625547114438L;
               int var42 = var38 < 0L ? -1 : 1;
               var27 = Math.max(var27, var13[0]);
               var35 = HudPlacement.project(var14 - var22, var33, var16 - var24, var52, var53, var13);
               long var40 = 109625547114438L;
               int var34 = var40 < 0L ? -1 : 1;
               if (!var35) {
                  return false;
               }

               var28 = Math.min(var28, var13[0]);
            }

            float var54;
            float var31;
            boolean var56;
            float var58;
            float var59x;
            float var55;
            float var32x;
            int var34;
            label209: {
               long var37x = 109625547114438L;
               int var58x = var37x < 0L ? -1 : 1;
               var58 = HudPlacement.project(var14, var1.x, var16, var52, var53, var13) ? var13[1] : var53 * 2.0F;
               var59x = this.scale() * distanceScale(HudPlacement.distance(var14, var20, var16), HudPlacement.focal());
               var54 = 180.0F * var59x;
               var55 = 66.0F * var59x;
               float var61 = 7.0F * var59x;
               var31 = var27 + var61;
               var32x = var28 - var61 - var54;
               float var41 = var52 - 6.0F - (var31 + var54);
               float var42 = var32x - 6.0F;
               var56 = var27 > 0.0F && var28 < var52;
               var34 = this.rE;
               long var43 = 109625547114438L;
               int var65 = var43 < 0L ? -1 : 1;
               if (this.rE > 0) {
                  if (!(var41 < 0.0F) || !(var42 > var41)) {
                     break label209;
                  }
               } else if (!(var41 >= 24.0F * var59x)) {
                  if (!(var42 < 0.0F)) {
                     break label209;
                  }

                  float var46;
                  int var39 = (var46 = var41 - var42) == 0.0F ? 0 : (var46 < 0.0F ? -1 : 1);
                  long var47 = 109625547114439L;
                  int var45 = var47 < 0L ? -1 : 1;
                  if (var39 <= 0) {
                     break label209;
                  }
               }

               var34 = -this.rE;
            }

            long var60 = 109625547114439L;
            int var65 = var60 < 0L ? -1 : 1;
            if (var7) {
               long var62 = 109625547114438L;
               int var79 = var62 < 0L ? -1 : 1;
               this.rE = var34;
               this.U = 1.0F;
            } else {
               long var63 = 109625547114439L;
               int var80 = var63 < 0L ? -1 : 1;
               long var66 = 109625547114438L;
               int var85 = var66 < 0L ? -1 : 1;
               if (var34 != this.rE && var56) {
                  long var44 = 109625547114439L;
                  int var75 = var44 < 0L ? -1 : 1;
                  this.rE = var34;
                  long var71 = 109625547114439L;
                  int var52x = var71 < 0L ? -1 : 1;
                  this.U = this.U < 1.0F ? 1.0F - this.U : 0.0F;
               }
            }

            if (this.U < 1.0F) {
               this.U = Math.min(1.0F, this.U + var6 / 0.34F);
            }

            float var671;
            var65 = (var671 = this.U - 0.5F) == 0.0F ? 0 : (var671 < 0.0F ? -1 : 1);
            long var67x = 109625547114438L;
            int var68 = var67x < 0L ? -1 : 1;
            this.rB = var65 < 0 ? -this.rE : this.rE;
            float var68x = this.rB > 0 ? var31 : var32x;
            float var83 = Math.min(var68x, Math.max(var52 - 6.0F - var54, var28 - var54 * 0.4F));
            float var84 = Math.max(var83, Math.min(6.0F, var27 - var54 * 0.6F));
            float var45 = var84 + this.rB * 10 * var59x * (1.0F - this.M(var8));
            float var72 = 0.0F;
            if (var10 != null && NametagCapturePipeline.bounds(var10, this.F)) {
               float var73 = this.F[0] * var52;
               float var48 = this.F[1] * var53;
               float var49 = this.F[2] * var52;
               float var50 = this.F[3] * var53;
               float var51 = 5.0F * var59x;
               if (var45 < var49 + var51 && var45 + var54 > var73 - var51 && var26 < var50 + var51 && var26 + var55 > var48 - var51) {
                  var72 = var50 + var51 - var26;
               }
            }

            if (var7) {
               long var74 = 109625547114438L;
               int var94 = var74 < 0L ? -1 : 1;
               this.D.snap(var72);
            } else {
               this.D.target(var72).update(MotionStyle.HOVER.dt(var4.dt));
            }

            float var75 = var26 + Math.max(0.0F, this.D.value());
            var75 = Math.max(var75, Math.min(6.0F, var58 - var55 * 0.6F));
            var5[0] = var45;
            var5[1] = var75;
            var5[2] = var59x;
            return true;
         }
      }
   }

   private float M(long var1) {
      return this.U >= 1.0F ? 1.0F : Easing.SINE_IN_OUT.apply(Math.abs(1.0F - 2.0F * this.U));
   }

   static float distanceScale(float var0, float var1) {
      float var2 = var1 / 1.428148F * 4.5F / Math.max(0.5F, var0);
      return Math.max(0.6F, Math.min(1.0F, (float)Math.sqrt(var2)));
   }

   private void F(int var1, TargetHud.TargetPopup var2, class_1309 var3, int var4, HudBounds var5, PvpMode var6, byte var7) {
      long var8 = 16785335916959L;
      long var10 = var8 ^ 16033470049583L;
      long var12 = var8 ^ 40977355832927L;
      long var14 = var8 ^ 119498463427067L;
      class_746 var16 = var5.player;
      var2.ex = TabListProvider.plain(var3.method_5477().getString());
      var2.eb = var3 instanceof class_11890;
      var2.e1 = var3 instanceof class_11901 var17 ? var17.method_52814().comp_1626().comp_3627() : null;
      short var24 = 3908;
      String var18 = p(class_1309.class.cast(var3), var10);
      var2.eL = var18;
      var2.J = var2.eb ? "PLAYER" : var3.method_5864().method_5897().getString().toUpperCase(Locale.ROOT);
      var2.E = Math.max(0.0F, var3.method_6032());
      var2.e = Math.max(1.0F, var3.method_6063());
      var2.o = Math.max(0.0F, var3.method_6067());
      if (var16 != null) {
         class_243 var19 = var16.method_5836(var5.partialTick);
         var2.e5 = (float)Math.sqrt(var3.method_5829().method_49271(var19));
         var2.S = var16.method_6032() + var16.method_6067();
      }

      double var26 = var3.method_23317() - var3.field_6014;
      double var21 = var3.method_23321() - var3.field_5969;
      var2.w = (float)Math.sqrt(var26 * var26 + var21 * var21) * 20.0F;
      var2.u = var3.method_23318() - var3.field_6036 < -0.15;
      var24 = 3908;
      var2.eC = var3.method_6128();
      var2.U = var6 == PvpMode.D ? pdist(var3, var14) : 0.0F;
      var2.i = var3.method_6047().method_57826(class_9334.field_54274) || var3.method_6079().method_57826(class_9334.field_54274);
      var2.D = TargetUpdater.shield(var3) != null;
      var2.eV = var3.method_6039();

      for (int var23 = 0; var23 < r5.length; var23++) {
         var2.en[var23] = var3.method_6118(r5[var23]);
      }

      var2.c = var3.method_6096();
      var2.em = var3.method_6047();
      V(var12, var3, var2.e6);
      TargetSnapshot var27 = TargetUpdater.stats(var3);
      var2.l = var27.s;
      var2.e9 = var27.q;
      var2.eO = var27.n;
      var2.Y = var27.w;
      var2.L = var27.U;
      var2.Z = var27.C;
      var2.eh = var27.J;
      var2.F = var27.M;
      var2.d = var27.F;
      var2.eA = var27.R;
      var2.ep = var27.z;
      var2.P = var27.l;
      var2.G = var27.B;
      var2.g = var27.e;
      var2.s = var27.O;
      var2.W = var27.L;
      var2.R = var27.K;
      System.arraycopy(var27.S, 0, var2.e2, 0, 4);
      if (var2.a) {
         var2.a = false;
         var2.b = var2.e9;
         var2.I = var2.s;
         var2.eo = var2.L;
         var2.p = var2.P;
      }
   }

   private static float pdist(class_1309 var0, long var1) {
      if (var0.method_24828()) {
         return 0.0F;
      } else {
         class_243 var3 = var0.method_73189();
         class_3965 var4 = var0.method_73183()
            .method_17742(new class_3959(var3, var3.method_1031(0.0, -64.0, 0.0), class_3960.field_17558, class_242.field_1347, var0));
         return var4.method_17783() == class_240.field_1333 ? 64.0F : (float)(var3.field_1351 - var4.method_17784().field_1351);
      }
   }

   private static void V(long var0, class_1309 var2, List var3) {
      var3.clear();
      long var4 = 46183435265983L;
      int var7 = var4 < 0L ? -1 : 1;
      if (p == null) {
         Int2ObjectOpenHashMap var7x = new Int2ObjectOpenHashMap();
         class_7923.field_41174.method_42017().forEach(var1 -> var7x.putIfAbsent(((class_1291)var1.comp_349()).method_5556() & 16777215, var1));
         p = var7x;
      }

      for (class_2394 var8 : (List<class_2394>)(Object)var2.method_5841().method_12789(LivingEntityAccessor.purxiy$effectParticles())) {
         int var9 = var8 instanceof class_9381 ? 1 : 0;
         long var10 = 46183435265983L;
         int var12 = var10 < 0L ? -1 : 1;
         if (var9 != 0) {
            class_9381 var12x = (class_9381)var8;
            var9 = Math.round(var12x.method_58259() * 255.0F);
            long var13 = 46183435265983L;
            int var15 = var13 < 0L ? -1 : 1;
            var15 = var9 << 16 | Math.round(var12x.method_58263() * 255.0F) << 8 | Math.round(var12x.method_58264() * 255.0F);
            class_6880 var16 = (class_6880)p.get(var15);
            if (var16 != null) {
               boolean var17 = var3.contains(var16);
               long var18 = 46183435265984L;
               int var19 = var18 < 0L ? -1 : 1;
               if (!var17) {
                  var3.add(var16);
               }
            }
         }
      }
   }

   private void x(TargetHud.TargetPopup var1, long var2, PvpMode var4, float var5) {
      short var7;
      char var8;
      int var9;
      long var10;
      boolean var12;
      label232: {
         long var13 = 4117300522783L;
         var7 = 958;
         var8 = 'ꈼ';
         var9 = (int)(var13 << 48 >>> 48);
         var10 = 116954903437308L;
         var1.ex = "Stardust";
         var1.eb = true;
         var1.J = "PLAYER";
         var1.eL = "user";
         var1.e1 = class_1068.method_62620().comp_1626().comp_3627();
         var1.e = 20.0F;
         if (var4 != PvpMode.U) {
            long var15 = 132087030886384L;
            int var19 = var15 < 0L ? -1 : 1;
            if (var4 != PvpMode.D && var4 != PvpMode.g && var4 != PvpMode.a) {
               var12 = false;
               break label232;
            }
         }

         var12 = true;
      }

      int var14 = (int)(var5 / 6.8F);
      float var46 = var5 - var14 * 6.8F;
      long var16 = 132087030886385L;
      int var43 = var16 < 0L ? -1 : 1;
      boolean var19 = var12 && var46 >= 5.0F;
      long var20 = 132087030886384L;
      int var19x = var20 < 0L ? -1 : 1;
      float var22x;
      int varCmp0 = Float.compare(var22x = var46 - 1.2F, 0.0F);
      long var23 = 132087030886384L;
      int var23x = var23 < 0L ? -1 : 1;
      if (varCmp0 < 0) {
         long var26 = 132087030886385L;
         int var25 = var26 < 0L ? -1 : 1;
         var1.E = 16.0F;
      } else {
         float var48;
         int varCmp1 = Float.compare(var48 = var46 - 2.6F, 0.0F);
         long var27 = 132087030886384L;
         int var27x = var27 < 0L ? -1 : 1;
         if (varCmp1 < 0) {
            long var29 = 132087030886384L;
            int var29x = var29 < 0L ? -1 : 1;
            var1.E = 12.5F;
         } else {
            float var50x;
            int varCmp2 = Float.compare(var50x = var46 - 3.8F, 0.0F);
            long var30 = 132087030886385L;
            int var52 = var30 < 0L ? -1 : 1;
            if (varCmp2 < 0) {
               long var32 = 132087030886384L;
               int var33 = var32 < 0L ? -1 : 1;
               var1.E = 15.0F;
            } else {
               float var52x;
               int varCmp3 = Float.compare(var52x = var46 - 5.0F, 0.0F);
               long var33 = 132087030886385L;
               int var55 = var33 < 0L ? -1 : 1;
               if (varCmp3 < 0) {
                  long var35x = 132087030886385L;
                  int var34 = var35x < 0L ? -1 : 1;
                  var1.E = 8.5F;
               } else {
                  var1.E = var19 ? 1.0F : 8.5F;
               }
            }
         }
      }

      var1.o = var19 ? 8.0F : (var12 ? 4.0F : 0.0F);
      var1.e5 = 3.2F + 0.4F * (float)Math.sin(var5 * 0.9);
      var1.S = 17.0F;
      var1.w = 5.6F + 2.4F * (float)Math.sin(var5 * 0.7);
      long var49x = 132087030886385L;
      int var25 = var49x < 0L ? -1 : 1;
      var25 = 2 + var14 % 4 + (var19 ? 1 : 0);
      long var51 = 132087030886384L;
      int var551 = var51 < 0L ? -1 : 1;
      var1.l = var25;
      float var31;
      if (var12) {
         long var53 = 132087030886384L;
         int var62 = var53 < 0L ? -1 : 1;
         var31 = (var19 ? var14 : var14 - 1) * 6.8F + 5.0F;
      } else {
         var31 = -1000.0F;
      }

      float var6;
      label211: {
         var1.e9 = var31;
         var1.i = !var19 || !(var46 < 5.7F);
         int var54 = (var46 >= 1.2F ? 1 : 0) + (var46 >= 3.8F ? 1 : 0);
         var25 = 11 + var54;
         long var56 = 132087030886384L;
         int var63 = var56 < 0L ? -1 : 1;
         var1.eO = var25;
         var1.Y = 7;
         var1.L = 2 + var54;
         var1.Z = 9 + var14 % 5;
         var1.eh = 18;
         var1.F = 3;
         var1.d = 5;
         var1.eA = 4;
         var1.ep = 3;
         long var57 = 132087030886384L;
         int var6x = var57 < 0L ? -1 : 1;
         var1.P = -1000.0F;
         if (var46 > 1.0F) {
            long var37 = 132087030886385L;
            int var71 = var37 < 0L ? -1 : 1;
            if (var46 < 3.6F) {
               long var39 = 132087030886384L;
               int var39x = var39 < 0L ? -1 : 1;
               var6 = var46 < 2.6F ? 9.0F * Easing.QUAD_OUT.apply((var46 - 1.0F) / 1.6F) : 9.0F * (1.0F - (var46 - 2.6F));
               break label211;
            }
         }

         var6 = 0.0F;
      }

      var1.U = var6;
      var1.u = var46 >= 2.6F && var46 < 3.6F;
      long var55x = 132087030886385L;
      int var73 = var55x < 0L ? -1 : 1;
      var1.eC = false;
      var1.D = true;
      var1.eV = var46 >= 0.6F && var46 < 2.0F;
      var1.G = var14 * 6.8F + 2.0F;
      var1.g = var1.G + 5.0F;
      var1.s = -1000.0F;
      var1.e6.clear();
      var1.e6.add(class_1294.field_5904);
      var1.e6.add(class_1294.field_5910);
      var1.e6.add(class_1294.field_5918);
      class_1799[] var34 = var12 ? this.Z(var7, (short)var8, (char)var9) : this.f(var10);
      System.arraycopy(var34, 0, var1.en, 0, 4);
      long var65 = 132087030886385L;
      int var59 = var65 < 0L ? -1 : 1;
      var1.c = 20;
      var1.em = var34[4];
      long var59x = 132087030886385L;
      int var40 = var59x < 0L ? -1 : 1;
      if (var1.a) {
         var1.a = false;
         var1.b = var1.e9;
         var1.eo = var1.L;
      }
   }

   private class_1799[] f(long var1) {
      long var3 = 121833051165318L;
      byte var5 = 0;
      int var6 = 1859024828;
      int var7 = (int)(var3 << 48 >>> 48);
      if (this.L == null) {
         class_1792[] var8 = new class_1792[]{
            class_1802.field_8805, class_1802.field_8058, class_1802.field_8348, class_1802.field_8285, class_1802.field_8802
         };
         this.L = E((char)var5, var6, var7, var8);
      }

      return this.L;
   }

   private class_1799[] Z(int var1, short var2, char var3) {
      long var4 = 4117300522783L;
      long var6 = var4 ^ 120331385748889L;
      int var8 = (int)((var4 ^ 120331385748889L) >>> 48);
      int var9 = (int)((var4 ^ 120331385748889L) << 16 >>> 32);
      int var10 = (int)(var6 << 48 >>> 48);
      if (this.rh == null) {
         class_1792[] var11 = new class_1792[]{
            class_1802.field_22027, class_1802.field_22028, class_1802.field_22029, class_1802.field_22030, class_1802.field_22022
         };
         this.rh = E((char)var8, var9, var10, var11);
      }

      return this.rh;
   }

   private static class_1799[] E(char var0, int var1, int var2, class_1792... var3) {
      float[] var4 = new float[]{0.12F, 0.38F, 0.22F, 0.71F, 0.1F};
      class_1799[] var5 = new class_1799[var3.length];

      for (int var6 = 0; var6 < var3.length; var6++) {
         var5[var6] = new class_1799(var3[var6]);
         var5[var6].method_7974((int)(var5[var6].method_7936() * var4[var6]));
      }

      return var5;
   }

   private void z(TargetHud.TargetPopup var1, float var2, long var3, float var5, float var6) {
      byte var7 = 0;
      long var8 = 126351793994883L;
      long var10 = 138961779395969L;
      long var12 = 101999988171860L;
      short var14 = 26584;
      int var15 = 521679812;
      var1.eM += var5;
      float var16 = Math.min(1.0F, var1.E / var1.e);
      long var17 = 110351255260529L;
      int var42 = var17 < 0L ? -1 : 1;
      if (!Float.isNaN(var1.H)) {
         float var20 = var1.E - var1.H;
         long var21 = 110351255260529L;
         int var44 = var21 < 0L ? -1 : 1;
         float var24;
         int var25 = (var24 = var20 - -0.05F) == 0.0F ? 0 : (var24 < 0.0F ? -1 : 1);
         long var26 = 110351255260530L;
         int var26x = var26 < 0L ? -1 : 1;
         if (var25 <= 0) {
            this.k((char)var7, var8, var1, -var20, var2);
            long var29 = 110351255260529L;
            int var51 = var29 < 0L ? -1 : 1;
         } else if (var20 >= 1.0F) {
            long var48 = 110351255260530L;
            int var501 = var48 < 0L ? -1 : 1;
            var1.v = var2;
            if (var20 >= 2.0F) {
               long var31 = 110351255260530L;
               int var53 = var31 < 0L ? -1 : 1;
               if (var1.eM > 0.4F) {
                  this.s(var14, var1, var20, var15, var2);
               }
            }
         }
      }

      var1.H = var1.E;
      var1.h.target(var16).update(var6);
      var1.N.target(Math.min(1.0F, var1.o / var1.e)).update(var6);
      var1.O.target(var1.w).update(var6);
      var1.ed.target(var1.U).update(var6);
      float var42x = var1.h.value();
      long var43 = 110351255260530L;
      int var40 = var43 < 0L ? -1 : 1;
      float var44;
      int var45 = (var44 = var42x - var1.eU) == 0.0F ? 0 : (var44 < 0.0F ? -1 : 1);
      long var47 = 110351255260529L;
      int var47x = var47 < 0L ? -1 : 1;
      if (var45 >= 0) {
         long var49 = 110351255260529L;
         int var52 = var49 < 0L ? -1 : 1;
         var1.eU = var42x;
      } else if (var2 >= var1.C) {
         var1.eU = Math.max(var42x, var1.eU - var5 * 0.85F);
      }

      long var50 = 110351255260529L;
      int var531 = var50 < 0L ? -1 : 1;
      var1.m.update(var5);
      var1.eq.update(var5);
      if (var1.e9 > var1.b) {
         long var32 = 110351255260530L;
         int var56 = var32 < 0L ? -1 : 1;
         var1.b = var1.e9;
         if (var2 - var1.e9 < 0.5F) {
            this.b(var12, var1, var2);
         }
      }

      if (var1.L > var1.eo) {
         var1.eq.impulse(9.0F);
      }

      var1.eo = var1.L;
      long var51 = 110351255260530L;
      int var57 = var51 < 0L ? -1 : 1;
      if (var1.P > var1.p) {
         var1.p = var1.P;
         float var35 = var2 - var1.P;
         long var36 = 110351255260529L;
         int var39 = var36 < 0L ? -1 : 1;
         if (var35 < 0.5F) {
            var1.B = var2;
         }
      }

      if (var1.s > var1.I) {
         var1.I = var1.s;
         float var52;
         var45 = (var52 = var1.t - -1000.0F) == 0.0F ? 0 : (var52 < 0.0F ? -1 : 1);
         long var54 = 110351255260530L;
         int var62 = var54 < 0L ? -1 : 1;
         if (var45 == 0 && !var1.k) {
            long var39 = 110351255260530L;
            int var35 = var39 < 0L ? -1 : 1;
            if (var2 - var1.s < 1.0F) {
               var1.t = var2;
               this.b(TargetKind.TARGET_KILL, 1.0F, var10, true);
            }
         }
      }

            float var53 = MotionStyle.MENU.speed();
      var1.y.removeIf(var2xx -> (var2 - var2xx.bornAt()) * var53 > 1.05F);
      var1.T.removeIf(var2xx -> {
         boolean var3x = false;
         return (var2 - var2xx.bornAt()) * var53 > var2xx.life();
      });
   }

   private void k(char var1, long var2, TargetHud.TargetPopup var4, float var5, float var6) {
      long var7 = 126351793994883L;
      int var9 = (int)((var7 ^ 23306888810311L) >>> 32);
      int var10 = (int)((var7 ^ 23306888810311L) << 32 >>> 32);
      var4.ef = var6;
      var4.C = var6 + 0.35F / Math.max(0.01F, MotionStyle.MENU.speed());
      var4.M = var6;
      var4.K = Math.min(2.2F, 0.6F + var5 * 0.22F) * (ThreadLocalRandom.current().nextBoolean() ? 1 : -1);
      this.s(var9, var4, -var5, var10, var6);
   }

   private void s(int var1, TargetHud.TargetPopup var2, float var3, int var4, float var5) {
      if (this.z.enabled()) {
         TargetHud.DamagePopup var6 = var2.y.isEmpty() ? null : var2.y.getLast();
         if (var6 != null) {
            short var7 = 26584;
            float var8 = var6.bornAt();
            if (var5 - var8 < 0.15F) {
               float var9 = var6.amount();
               var7 = 26584;
               var9 = Math.signum(var9);
               if (var9 == Math.signum(var3)) {
                  var2.y.removeLast();
                  var3 += var6.amount();
               }
            }
         }

         float var11 = var2.ea + 8.0F + var2.y.size() % 3 * 7;
         var2.y.add(new TargetHud.DamagePopup(var3, var5, var11));
      }
   }

   private void b(long var1, TargetHud.TargetPopup var3, float var4) {
      long var5 = 113825531342527L;
      long var7 = 138961779395969L;
      var3.z = var4;
      var3.m.snap(0.0F).impulse(11.0F);
      if (!var3.k) {
         this.b(TargetKind.TARGET_POP, 1.0F + Math.min(8, Math.max(0, var3.l - 1)) * 0.035F, var7, true);
      }

      int var9 = n(this.x, var5);
      float var10 = var9 < 0 ? 20.0F : 7.0F + var9 * 56.666668F + 6.5F;
      float var11 = var9 < 0 ? 20.0F : 55.0F;
      var3.r = var10;
      var3.j = var11;
      ThreadLocalRandom var12 = ThreadLocalRandom.current();

      for (int var13 = 0; var13 < 14; var13++) {
         double var14 = (Math.PI * 2) * var13 / 14.0 + var12.nextDouble(-0.2, 0.2);
         float var16 = (float)var12.nextDouble(20.0, 42.0);
         long var17 = 101999988171859L;
         int var20 = var17 < 0L ? -1 : 1;
         var20 = var13 % 3;
         long var21 = 101999988171859L;
         int var23 = var21 < 0L ? -1 : 1;
         if (var20 == 0) {
            var20 = -7670328;
         } else {
            long var23x = 101999988171860L;
            int var24 = var23x < 0L ? -1 : 1;
            var20 = var13 % 3 == 1 ? -11654 : -2858;
         }

         var3.T
            .add(
               new TargetHud.FloatingNumber(
                  var10,
                  var11,
                  (float)Math.cos(var14) * var16,
                  (float)Math.sin(var14) * var16 - 10.0F,
                  var4,
                  (float)var12.nextDouble(0.5, 0.85),
                  var20,
                  (float)var12.nextDouble(0.7, 1.3)
               )
            );
      }
   }

   private static int n(PvpMode var0, long var1) {
      return switch (TargetHud.TargetHudBatch.X[var0.ordinal()]) {
         case 1, 2 -> 0;
         case 3 -> 1;
         case 4 -> 2;
         default -> -1;
      };
   }

   protected void render(Canvas var1, HudBounds var2) {
      long var3 = 77887353914705L;
      long var5 = var3 ^ 105146258187245L;
      long var7 = var3 ^ 105202964443164L;
      long var9 = var3 ^ 10450511126866L;
      long var11 = var3 ^ 19894525744824L;
      long var13 = var3 ^ 109095045944191L;
      long var15 = var3 ^ 84739830048794L;
      long var17 = var3 ^ 45444424398083L;
      int var19 = (int)((var3 ^ 45444424398083L) >>> 32);
      int var20 = (int)((var3 ^ 45444424398083L) << 32 >>> 48);
      int var21 = (int)(var17 << 48 >>> 48);
      this.i(var2, var13);
      TargetHud.TargetPopup var22 = this.ry;
      float var23 = this.e(var15);
      boolean var24 = this.pinned();
      float var25 = var24 ? this.rs.value() * this.M(var11) : 1.0F;
      if (var22 != null && !(var23 <= 0.004F) && !(var25 <= 0.004F)) {
         float var26 = var2.time;
         float var27 = this.u ? 0.86F + 0.14F * r_.apply(this.rt) : 0.93F + 0.07F * var23;
         float var28 = var24 ? -this.rB * 12 * (1.0F - var23) : 0.0F;
         var1.push();
         var1.translate(90.0F + m(var22, var5, var26) + var28, 33.0F + (var24 ? 0.0F : (1.0F - var23) * 6.0F));
         var1.scale(var27);
         var1.translate(-90.0F, -33.0F);
         var1.pushAlpha(var23 * var25);
         this.w(var9, var1, var22, var26);
         TargetHud.TargetPopup var29 = this.b;
         float var30 = 1.0F;
         if (var29 != null) {
            float var31 = Math.min(1.0F, var29.A / 0.18F);
            var1.push();
            var1.translate(-8.0F * V.apply(var31), 0.0F);
            var1.pushAlpha(1.0F - var31);
            this.U(var19, var20, var1, var29, var26, (char)var21);
            var1.popAlpha();
            var1.pop();
            var30 = W((var22.eM - 0.06F) / 0.22F, var7);
         }

         var1.push();
         var1.translate(8.0F * (1.0F - V.apply(var30)), 0.0F);
         var1.pushAlpha(var30);
         this.U(var19, var20, var1, var22, var26, (char)var21);
         var1.popAlpha();
         var1.pop();
         var1.popAlpha();
         var1.pop();
      }
   }

   private static float m(TargetHud.TargetPopup var0, long var1, float var3) {
      float var4 = (var3 - var0.M) * MotionStyle.MENU.speed();
      if (!(var4 < 0.0F)) {
         long var5 = 28002470487739L;
         int var7 = var5 < 0L ? -1 : 1;
         if (!(var4 > 0.4F)) {
            return var0.K * (float)Math.sin(var4 * 58.0F) * (float)Math.exp(-var4 * 11.0F);
         }
      }

      return 0.0F;
   }

   private void w(long var1, Canvas var3, TargetHud.TargetPopup var4, float var5) {
      long var6 = 28007100265805L;
      long var8 = 24359658639940L;
      this.panel(var3, 0.0F, 0.0F, 180.0F, 66.0F, 9.0F);
      float var10 = 1.0F - W(var4.eM / 0.9F, var6);
      long var11 = 87237816542211L;
      int var14 = var11 < 0L ? -1 : 1;
      if (var10 > 0.0F) {
         var3.glow(0.0F, 0.0F, 180.0F, 66.0F, 9.0F, 16.0F, BlendMode.withAlpha(Icons.VIOLET, 0.22F * var10 * var10));
      }

      float var14x = F(var5, var8, var4.z, 0.8F);
      if (var14x > 0.0F) {
         var3.glow(0.0F, 0.0F, 180.0F, 66.0F, 9.0F, 18.0F, BlendMode.withAlpha(-11654, 0.3F * var14x));
         var3.roundRectOutline(0.0F, 0.0F, 180.0F, 66.0F, 9.0F, 1.0F, BlendMode.withAlpha(-11654, 0.7F * var14x));
      }

      long var15 = 87237816542210L;
      int var22 = var15 < 0L ? -1 : 1;
      if (var4.t != -1000.0F) {
         float var17 = W((var5 - var4.t) / 0.3F, var6);
         float var18 = 1.0F - 0.6F * W((var5 - var4.t - 0.6F) / 0.8F, var6);
         var3.glow(0.0F, 0.0F, 180.0F, 66.0F, 9.0F, 18.0F, BlendMode.withAlpha(Icons.PINK, 0.26F * var17 * var18));
         var3.roundRectOutline(0.0F, 0.0F, 180.0F, 66.0F, 9.0F, 1.0F, BlendMode.withAlpha(Icons.PINK, 0.55F * var17 * var18));
      } else {
         long var21 = 87237816542211L;
         int var23 = var21 < 0L ? -1 : 1;
         if (var4.E / var4.e <= 0.3F) {
            float var19 = 0.5F + 0.5F * (float)Math.sin(var5 * 6.0F);
            var3.roundRectOutline(0.0F, 0.0F, 180.0F, 66.0F, 9.0F, 1.0F, BlendMode.withAlpha(Icons.PINK, 0.18F + 0.2F * var19));
         }
      }

      long var22x = 87237816542210L;
      int var25 = var22x < 0L ? -1 : 1;
      if (var4.eM < 0.8F) {
         float var23x = var4.eM / 0.8F;
         float var20 = -30.0F + 240.0F * Easing.SINE_IN_OUT.apply(var23x);
         var3.pushClip(0.0F, 0.0F, 180.0F, 66.0F, 9.0F);
         var3.push();
         var3.translate(var20, 33.0F);
         var3.rotate(0.38F);
         var3.glow(-7.0F, -66.0F, 14.0F, 132.0F, 7.0F, 18.0F, BlendMode.withAlpha(-1, 0.08F * (float)Math.sin(Math.PI * var23x)));
         var3.pop();
         var3.popClip();
      }
   }

   private void U(int var1, int var2, Canvas var3, TargetHud.TargetPopup var4, float var5, char var6) {
      long var7 = 122605391966290L;
      long var9 = var7 ^ 69476905487068L;
      long var11 = var7 ^ 13035906126583L;
      long var13 = var7 ^ 110259570139044L;
      long var15 = var7 ^ 45660158779500L;
      long var17 = var7 ^ 118724740418237L;
      long var19 = var7 ^ 84372152985535L;
      int var21 = (int)((var7 ^ 84372152985535L) >>> 56);
      int var22 = (int)((var7 ^ 84372152985535L) << 8 >>> 32);
      int var23 = (int)(var19 << 40 >>> 40);
      var19 = var7 ^ 113418576730105L;
      int var24 = (int)((var7 ^ 113418576730105L) >>> 48);
      int var25 = (int)((var7 ^ 113418576730105L) << 16 >>> 48);
      int var26 = (int)(var19 << 32 >>> 32);
      long var27 = var7 ^ 63964408064727L;
      long var29 = var7 ^ 85586376279157L;
      this.F(var3, var4, var5, var9);
      this.J(var3, var4, var17, var5);
      this.e(var3, var4, var5, var11);
      this.j(var3, var27, var4, var5);
      this.b(var3, var4, (short)var24, (short)var25, var26, var5);
      this.S((byte)var21, var22, var3, var4, var5, var23);
      this.Y(var3, var15, var4, var5);
      this.X(var3, var4, var5, var29);
      this.T(var13, var3, var4, var5);
   }

   private void F(Canvas var1, TargetHud.TargetPopup var2, float var3, long var4) {
      long var6 = 28007100265805L;
      long var8 = 24359658639940L;
      long var10 = 8728679802551L;
      float var12 = 7.0F;
      float var13 = 7.0F;
      float var14 = var12 + 13.0F;
      float var15 = var13 + 13.0F;
      float var16 = 0.6F + 0.4F * Easing.BACK_OUT.apply(W(var2.eM / 0.4F, var6));
      var1.push();
      var1.scaleAround(var16, var14, var15);
      var1.pushAlpha(W(var2.eM / 0.12F, var6));
      long var17 = 88725712304781L;
      int var20 = var17 < 0L ? -1 : 1;
      var1.shadow(var12, var13, 26.0F, 26.0F, 6.0F, 8.0F, 0.0F, 0.0F, 1.5F, 1426063360);
      if (!p(var1, var2.e1, var10, var12, var13, 26.0F, 6.0F)) {
         var1.roundRectGradient(var12, var13, 26.0F, 26.0F, 6.0F, 135.0F, BlendMode.withAlpha(Icons.INDIGO, 0.6F), BlendMode.withAlpha(Icons.MAGENTA, 0.5F));
         var1.icon(var2.eL, var14, var15, 13.0F, Icons.TEXT);
      }

      float var20x = F(var3, var8, var2.ef, 0.35F);
      long var21 = 88725712304782L;
      int var23 = var21 < 0L ? -1 : 1;
      if (var20x > 0.0F) {
         var1.roundRect(var12, var13, 26.0F, 26.0F, 6.0F, BlendMode.withAlpha(-41852, 0.5F * var20x));
      }

      float var23x = F(var3, var8, var2.z, 0.7F);
      long var24 = 88725712304781L;
      int var26 = var24 < 0L ? -1 : 1;
      if (var23x > 0.0F) {
         var1.glow(var12, var13, 26.0F, 26.0F, 6.0F, 12.0F, BlendMode.withAlpha(-11654, 0.55F * var23x));
         var1.roundRect(var12, var13, 26.0F, 26.0F, 6.0F, BlendMode.withAlpha(-11654, 0.45F * var23x));
      }

      if (var2.t != -1000.0F) {
         long var26x = 88725712304782L;
         int var29 = var26x < 0L ? -1 : 1;
         var1.roundRect(var12, var13, 26.0F, 26.0F, 6.0F, BlendMode.withAlpha(Theme.tint(-16053995), 0.62F * W((var3 - var2.t) / 0.35F, var6)));
         float var29x = Easing.BACK_OUT.apply(W((var3 - var2.t - 0.08F) / 0.35F, var6));
         long var30 = 88725712304782L;
         int var30x = var30 < 0L ? -1 : 1;
         if (var29x > 0.0F) {
            var1.push();
            var1.scaleAround(var29x, var14, var15);
            var1.icon("skull", var14, var15, 14.56F, Icons.PINK);
            var1.pop();
         }
      }

      var1.roundRectOutline(var12, var13, 26.0F, 26.0F, 6.0F, 1.0F, 788529151);
      var1.popAlpha();
      var1.pop();
   }

   private static boolean p(Canvas var0, class_2960 var1, long var2, float var4, float var5, float var6, float var7) {
      if (var1 == null) {
         return false;
      } else {
         try {
            class_1044 var8 = class_310.method_1551().method_1531().method_4619(var1);
            GpuTextureView var9 = var8.method_71659();
            class_12137 var10 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
            var0.image(var9, var10, false, 7.0F, 7.0F, 26.0F, 26.0F, 0.125F, 0.125F, 0.25F, 0.25F, 6.0F, -1);
            var0.image(var9, var10, false, 7.0F, 7.0F, 26.0F, 26.0F, 0.625F, 0.125F, 0.75F, 0.25F, 6.0F, -1);
            return true;
         } catch (IllegalStateException var111) {
            return false;
         }
      }
   }

   private void J(Canvas var1, TargetHud.TargetPopup var2, long var3, float var5) {
      long var6 = 28007100265805L;
      float var8 = V.apply(W(var2.eM / 0.5F, var6));
      float var9 = W((var2.eM - 0.55F) / 0.45F, var6);
      float var10 = W(var2.eM / 0.12F, var6) * (1.0F - 0.55F * var9);
      if (var2.t != -1000.0F) {
         var10 *= 1.0F - W((var5 - var2.t) / 0.3F, var6);
      }

      if (!(var10 <= 0.01F)) {
         float var11 = 9.0F - 6.5F * var8 + 0.5F * var9 * (float)Math.sin(var5 * 2.4F);
         float var12 = 2.0F + 3.5F * var8;
         float var13 = 1.2F;
         float var14 = 7.0F - var11;
         float var15 = 7.0F - var11;
         float var16 = 33.0F + var11;
         float var17 = 33.0F + var11;
         int var18 = BlendMode.withAlpha(BlendMode.lerp(Icons.PURPLE, Icons.MAGENTA, 0.5F), var10);
         var1.roundRect(var14, var15, var12, var13, var13 / 2.0F, var18);
         var1.roundRect(var14, var15, var13, var12, var13 / 2.0F, var18);
         var1.roundRect(var16 - var12, var15, var12, var13, var13 / 2.0F, var18);
         var1.roundRect(var16 - var13, var15, var13, var12, var13 / 2.0F, var18);
         var1.roundRect(var14, var17 - var13, var12, var13, var13 / 2.0F, var18);
         var1.roundRect(var14, var17 - var12, var13, var12, var13 / 2.0F, var18);
         var1.roundRect(var16 - var12, var17 - var13, var12, var13, var13 / 2.0F, var18);
         var1.roundRect(var16 - var13, var17 - var12, var13, var12, var13 / 2.0F, var18);
      }
   }

   private void e(Canvas var1, TargetHud.TargetPopup var2, float var3, long var4) {
      long var6 = 28007100265805L;
      long var8 = 54947665933818L;
      short var10 = 12793;
      char var11 = '耛';
      int var12 = (int)(var8 << 48 >>> 48);
      float var13 = this.I(var1, var2, var10, var11, var12);
      float var14 = V.apply(W((var2.eM - 0.05F) / 0.3F, var6));
      Translations.pause();
      String var15 = FontMetrics.ellipsize(Fonts.SEMIBOLD, var2.ex, 10.0F, 173.0F - var13 - 6.0F - 41.0F, 0.0F);
      var1.pushAlpha(var14);
      float var16 = this.text(var1, Fonts.SEMIBOLD, var15, 41.0F + 6.0F * (1.0F - var14), 13.5F, 10.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      var1.popAlpha();
      Translations.resume();
      if (var2.t != -1000.0F) {
         float var17 = Easing.STANDARD.apply(W((var3 - var2.t - 0.12F) / 0.35F, var6));
         var1.roundRect(40.0F, 13.0F, (var16 + 2.0F) * var17, 1.1F, 0.55F, Icons.PINK);
      }
   }

   private float I(Canvas var1, TargetHud.TargetPopup var2, int var3, char var4, int var5) {
      long var6 = 54947665933818L;
      long var8 = var6 ^ 44536283316407L;
      long var10 = var6 ^ 63900612610291L;
      long var12 = var6 ^ 107807826235845L;
      float var14 = this.rO.value();
      float var15 = V.apply(W((var2.eM - 0.15F) / 0.3F, var8));
      float var16 = 173.0F - var14 + 8.0F * (1.0F - var15);
      float var17 = 8.0F;
      var1.pushAlpha(var15);
      var1.roundRectGradient(var16, var17, var14, 11.0F, 5.5F, 0.0F, BlendMode.withAlpha(Icons.INDIGO, 0.34F), BlendMode.withAlpha(Icons.MAGENTA, 0.26F));
      var1.roundRectOutlineGradient(
         var16, var17, var14, 11.0F, 5.5F, 1.0F, 0.0F, BlendMode.withAlpha(Icons.PURPLE, 0.6F), BlendMode.withAlpha(Icons.MAGENTA, 0.3F)
      );
      var1.pushClip(var16, var17, var14, 11.0F, 5.5F);
      float var18 = this.rX;
      if (var18 < 1.0F && this.rS != null) {
         float var19 = W(var18 * 1.6F, var8);
         var1.pushAlpha(1.0F - var19);
         G(var1, this.rF, this.T, var16, 13.5F - 5.0F * V.apply(var19));
         var1.popAlpha();
      }

      float var20 = this.rS == null ? 1.0F : V.apply(W((var18 - 0.25F) / 0.75F, var8));
      var1.pushAlpha(var20);
      G(var1, a(var12, this.x, var2), A(var10, this.x, var2), var16, 13.5F + 5.0F * (1.0F - var20));
      var1.popAlpha();
      var1.popClip();
      var1.popAlpha();
      return var14;
   }

   private static void G(Canvas var0, String var1, String var2, float var3, float var4) {
      var0.icon(var1, var3 + 5.0F + 3.25F, var4, 6.5F, Icons.TEXT);
      var0.text(Fonts.BOLD, var2, var3 + 5.0F + 6.5F + 3.0F, var4 + 0.2F, 6.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE, 0.08F);
   }

   private static String A(long var0, PvpMode var2, TargetHud.TargetPopup var3) {
      return var2 == PvpMode.s ? FontMetrics.ellipsize(Fonts.BOLD, var3.J, 6.0F, 64.0F, 0.08F) : var2.badge();
   }

   private static String a(long var0, PvpMode var2, TargetHud.TargetPopup var3) {
      return var2 == PvpMode.s ? var3.eL : var2.Z;
   }

   private static float p(String var0) {
      return 14.5F + Fonts.BOLD.width(var0, 6.0F, 0.08F) + 6.0F;
   }

   private void j(Canvas var1, long var2, TargetHud.TargetPopup var4, float var5) {
      short var6 = 9927;
      int var7 = -1992124386;
      long var8 = 28007100265805L;
      long var10 = 1362391103L;
      char var12 = '\uda42';
      float var13 = V.apply(W((var4.eM - 0.1F) / 0.3F, var8));
      var1.pushAlpha(var13);
      boolean var14 = var4.t != -1000.0F;
      long var15 = 94208312990341L;
      int var18 = var15 < 0L ? -1 : 1;
      boolean var18x = !var14 && var4.E / var4.e <= 0.3F;
      float var19 = var18x ? (float)Math.pow(Math.max(0.0, Math.sin(var5 * 6.4)), 12.0) : 0.0F;
      float var20 = 45.5F;
      var1.push();
      var1.scaleAround(1.0F + 0.2F * var19, var20, 27.0F);
      this.icon(var1, "heart", var20, 27.0F, 9.0F, var14 ? Icons.TEXT_3 : Icons.PINK);
      var1.pop();
      float var21 = var14 ? 0.0F : var4.h.value() * var4.e;
      float var22 = 53.0F;
      float var23 = var22
         + this.text(
            var1, Fonts.SEMIBOLD, this.E(var21, var6, var4.e, var7), var22, 27.0F, 10.0F, var18x ? Icons.PINK : Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE
         );
      var4.ea = var23;
      float var24 = var4.N.value() * var4.e;
      long var25 = 94208312990340L;
      int var28 = var25 < 0L ? -1 : 1;
      long var28x = 94208312990340L;
      if (var24 >= 0.05F && !var14) {
         this.text(var1, Fonts.SEMIBOLD, "+" + this.E(var24, var6, var4.e, var7), var23 + 3.0F, 27.5F, 8.0F, -11654, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      this.text(var1, Fonts.MEDIUM, f(var10, (short)var12, var4.e5), 173.0F, 27.5F, 8.0F, Icons.TEXT_2, FontStyle.RIGHT, FontWeight.MIDDLE);
      var1.popAlpha();
   }

   private void b(Canvas var1, TargetHud.TargetPopup var2, short var3, short var4, int var5, float var6) {
      long var7 = 9505218750379L;
      long var9 = var7 ^ 19644952662758L;
      long var11 = var7 ^ 33546779702767L;
      float var13 = 7.0F;
      float var14 = 38.5F;
      float var15 = 166.0F;
      var1.pushAlpha(W((var2.eM - 0.08F) / 0.2F, var9));
      var1.roundRect(var13, var14, var15, 3.5F, 1.75F, 536870911);
      float var16 = W(var2.h.value(), var9);
      float var17 = W(var2.eU, var9);
      if (var17 > var16 + 0.003F) {
         var1.roundRect(var13, var14, var15 * var17, 3.5F, 1.75F, Theme.tint(-1711290648));
      }

      boolean var18 = var2.t == -1000.0F && var2.E / var2.e <= 0.3F;
      float var19 = var18 ? 0.5F + 0.5F * (float)Math.sin(var6 * 7.0F) : 0.0F;
      short var20 = 2213;
      if (var16 > 0.001F) {
         float var21 = Math.max(3.5F, var15 * var16);
         float var22 = F(var6, var11, var2.v, 0.6F);
         if (var22 > 0.0F) {
            var1.glow(var13, var14, var21, 3.5F, 1.75F, 8.0F, BlendMode.withAlpha(-7670328, 0.55F * var22));
         }

         if (var18) {
            var1.glow(var13, var14, var21, 3.5F, 1.75F, 8.0F, BlendMode.withAlpha(Icons.PINK, 0.25F + 0.25F * var19));
         }

         var1.roundRectGradient(
            var13, var14, var21, 3.5F, 1.75F, 0.0F, var18 ? BlendMode.lerp(Icons.PINK, -41852, var19) : Icons.INDIGO, var18 ? -30116 : Icons.MAGENTA
         );
         int var23 = 456124331;
         var1.roundRectCorners(var13, var14, var21, 3.5F, 1.75F, 956301311, 956301311, 16777215, 16777215);
         float var24 = var6 % 3.6F / 1.1F;
         var23 = 456124331;
         if (var24 < 1.0F) {
            float var25 = var13 - 12.0F + (var21 + 24.0F) * Easing.SINE_IN_OUT.apply(var24);
            var1.pushClip(var13, var14, var21, 3.5F, 1.75F);
            var1.glow(var25 - 5.0F, var14 - 1.0F, 10.0F, 5.5F, 2.0F, 6.0F, 1040187391);
            var1.popClip();
         }
      }

      float var27 = W(var2.N.value(), var9);
      if (var27 > 0.003F) {
         var20 = 2213;
         if (var2.t == -1000.0F) {
            float var28 = Math.max(1.4F, var15 * var27);
            var1.glow(var13, var14 - 0.5F, var28, 1.4F, 0.7F, 5.0F, BlendMode.withAlpha(-11654, 0.45F));
            var1.roundRect(var13, var14 - 0.5F, var28, 1.4F, 0.7F, -11654);
         }
      }

      var1.popAlpha();
   }

   private void S(byte var1, int var2, Canvas var3, TargetHud.TargetPopup var4, float var5, int var6) {
      long var7 = 38749847214061L;
      long var9 = var7 ^ 64075805745824L;
      long var11 = var7 ^ 139161560427622L;
      long var13 = var7 ^ 58383808595369L;
      long var15 = var7 ^ 71712271484505L;
      boolean var17 = false;

      for (class_1799 var21 : var4.en) {
         var17 |= !var21.method_7960();
      }

      boolean var32 = false;
      if (var17) {
         float var22 = 2.0F;
         float var23 = (166.0F - var22 * 3.0F) / 4.0F;
         var3.pushAlpha(W((var4.eM - 0.14F) / 0.25F, var9));

         for (int var24 = 0; var24 < 4; var24++) {
            class_1799 var25 = var4.en[var24];
            float var26 = F(var5, var13, var4.e2[var24], 0.9F);
            float var27 = var24;
            int var28 = 14735341;
            float var29 = 7.0F + var27 * (var23 + var22);
            var28 = 2309670;
            float var30 = var29 + (var26 > 0.0F ? (float)Math.sin(var5 * 60.0F) * 1.2F * var26 : 0.0F);
            var3.roundRect(var30, 44.0F, var23, 1.5F, 0.75F, var25.method_7960() ? 234881023 : 536870911);
            if (!var25.method_7960()) {
               float var31 = M(var11, var25);
               var3.roundRect(var30, 44.0F, Math.max(1.5F, var23 * var31), 1.5F, 0.75F, var25.method_7963() ? q(var15, var31) : Icons.TEXT_3);
            }

            if (var26 > 0.0F) {
               var3.glow(var30, 44.0F, var23, 1.5F, 0.75F, 6.0F, BlendMode.withAlpha(Icons.PINK, 0.8F * var26));
            }
         }

         var32 = false;
         var3.popAlpha();
      }
   }

   private void Y(Canvas var1, long var2, TargetHud.TargetPopup var4, float var5) {
      long var6 = 28007100265805L;
      long var8 = 106043655465993L;
      long var10 = 14480140447187L;
      float var12 = var4.t == -1000.0F ? 0.0F : Easing.STANDARD.apply(W((var5 - var4.t - 0.15F) / 0.35F, var6));
      if (var12 < 1.0F) {
         var1.pushAlpha(1.0F - var12);
         float var13 = this.rX;
         if (var13 < 1.0F && this.rS != null) {
            float var14 = W(var13 * 1.6F, var6);
            var1.push();
            var1.translate(0.0F, -4.0F * V.apply(var14));
            var1.pushAlpha(1.0F - var14);
            this.r(var1, var4, this.rS, this.c, var5, var10, false);
            var1.popAlpha();
            var1.pop();
         }

         float var15 = this.rS == null ? 1.0F : V.apply(W((var13 - 0.25F) / 0.75F, var6));
         var1.push();
         var1.translate(0.0F, 4.0F * (1.0F - var15));
         var1.pushAlpha(var15);
         this.r(var1, var4, this.x, this.v, var5, var10, true);
         var1.popAlpha();
         var1.pop();
         var1.popAlpha();
      }

      if (var12 > 0.0F) {
         this.t(var1, var4, var8, var12);
      }
   }

   private void r(Canvas var1, TargetHud.TargetPopup var2, PvpMode var3, TargetHud.TargetHudState[] var4, float var5, long var6, boolean var8) {
      long var9 = 133110249443787L;
      long var11 = 28007100265805L;
      short var13 = 7970;
      long var14 = 3488078214L;
      this.L(var2, var3, var9, var4, var5);

      for (int var16 = 0; var16 < var4.length; var16++) {
         float var17 = var8 ? V.apply(W((var2.eM - 0.2F - 0.06F * var16) / 0.3F, var11)) : 1.0F;
         long var18 = 14480140447187L;
         int var21 = var18 < 0L ? -1 : 1;
         float var21x;
         int var22 = (var21x = var17 - 0.01F) == 0.0F ? 0 : (var21x < 0.0F ? -1 : 1);
         long var23 = 14480140447186L;
         int var25 = var23 < 0L ? -1 : 1;
         if (var22 <= 0) {
            long var25x = 14480140447187L;
            int var26 = var25x < 0L ? -1 : 1;
         } else {
            var1.push();
            var1.translate(0.0F, 4.0F * (1.0F - var17));
            var1.pushAlpha(var17);
            this.W(var13, var1, var14, var4[var16], 7.0F + var16 * 56.666668F, 49.0F);
            var1.popAlpha();
            var1.pop();
         }
      }

      long var27 = 14480140447186L;
      int var28 = var27 < 0L ? -1 : 1;
   }

   private void W(int var1, Canvas var2, long var3, TargetHud.TargetHudState var5, float var6, float var7) {
      long var8 = 34234377427334L;
      long var10 = var8 ^ 40158885973476L;
      float var12 = 12.0F;
      float var13 = 49.0F + var12 / 2.0F;
      var2.roundRect(var6, 49.0F, 52.666668F, var12, 4.0F, BlendMode.lerp(234881023, BlendMode.withAlpha(var5.B, 0.16F), var5.b));
      var2.roundRectOutline(var6, 49.0F, 52.666668F, var12, 4.0F, 1.0F, BlendMode.lerp(285212671, BlendMode.withAlpha(var5.B, 0.5F), var5.b));
      float var14 = var6 + 6.5F;
      if (var5.Z > 0.0F) {
         var2.glowCircle(var14, var13, 2.0F, 7.0F, BlendMode.withAlpha(var5.e, 0.45F * var5.Z));
      }

      var2.push();
      var2.scaleAround(1.0F + 0.35F * var5.Q, var14, var13);
      this.icon(var2, var5.r, var14, var13, 7.5F, var5.e);
      var2.pop();
      short var15 = 7970;
      if (var5.S >= 0.0F) {
         var2.ring(var14, var13, 5.4F, 1.0F, 452984831);
         var2.arc(var14, var13, 5.4F, 1.0F, -90.0F, 360.0F * var5.S, var5.e);
      }

      float var16 = var6 + 12.0F;
      float var17 = var6 + 52.666668F - 3.0F - var16;
      if (var5.Y != null) {
         this.r(var2, var5.Y, var16, var10, var13, var17);
      } else {
         float var18 = Math.max(7.0F, Math.min(8.5F, 8.5F * var17 / Math.max(1.0F, Fonts.SEMIBOLD.width(var5.V, 8.5F))));
         String var19 = FontMetrics.ellipsize(Fonts.SEMIBOLD, var5.V, var18, var17, 0.0F);
         var2.push();
         var2.scaleAround(1.0F + 0.2F * var5.Q, var16, var13);
         float var20 = this.text(var2, Fonts.SEMIBOLD, var19, var16, var13, var18, var5.u, FontStyle.LEFT, FontWeight.MIDDLE);
         var2.pop();
         if (!var5.l.isEmpty()) {
            long var21 = 3488078213L;
            int var23 = var21 < 0L ? -1 : 1;
            if (var20 + 2.5F + Fonts.MEDIUM.width(var5.l, 6.5F) <= var17) {
               this.text(var2, Fonts.MEDIUM, var5.l, var16 + var20 + 2.5F, var13 + 0.4F, 6.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
            }
         }
      }
   }

   private void r(Canvas var1, List var2, float var3, long var4, float var6, float var7) {
      long var8 = 120789730589952L;
      if (var2.isEmpty()) {
         this.text(var1, Fonts.SEMIBOLD, "None", var3, var6, 8.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
      } else {
         int var10 = (int)((var7 + 2.5F) / 10.5F);
         int var11 = var2.size();
         long var12 = 65579717612641L;
         int var15 = var12 < 0L ? -1 : 1;
         var15 = var11 > var10 ? var10 - 1 : var2.size();

         for (int var16 = 0; var16 < var15; var16++) {
            class_6880 var17 = (class_6880)var2.get(var16);
            long var18 = 65579717612641L;
            int var19 = var18 < 0L ? -1 : 1;
            this.icon(var1, e(var8, var17), var3 + 4.0F + var16 * 10.5F, var6, 8.0F, ((class_1291)var17.comp_349()).method_5556() | 0xFF000000);
         }

         long var20 = 65579717612641L;
         int var22 = var20 < 0L ? -1 : 1;
         if (var15 < var2.size()) {
            this.text(var1, Fonts.SEMIBOLD, "+" + (var2.size() - var15), var3 + var15 * 10.5F, var6, 7.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         }
      }
   }

   private void t(Canvas var1, TargetHud.TargetPopup var2, long var3, float var5) {
      float var6 = 7.0F;
      float var7 = 49.0F;
      float var8 = 166.0F;
      float var9 = var7 + 6.0F;
      var1.pushAlpha(var5);
      var1.push();
      var1.translate(0.0F, 4.0F * (1.0F - var5));
      var1.roundRectGradient(var6, var7, var8, 12.0F, 4.0F, 0.0F, BlendMode.withAlpha(Icons.PINK, 0.2F), BlendMode.withAlpha(Icons.MAGENTA, 0.12F));
      var1.roundRectOutline(var6, var7, var8, 12.0F, 4.0F, 1.0F, BlendMode.withAlpha(Icons.PINK, 0.45F));
      this.icon(var1, "skull", var6 + 6.5F, var9, 7.5F, Icons.PINK);
      var1.textGradient(Fonts.BOLD, "ELIMINATED", var6 + 12.0F, var9 + 0.2F, 7.5F, Icons.PINK, Icons.MAGENTA, FontStyle.LEFT, FontWeight.MIDDLE, 0.12F);
      StringBuilder var10 = new StringBuilder();
      if (var2.W > 0) {
         var10.append(var2.W).append(' ').append(Translations.plural(var2.W, "pop", "pops"));
      }

      if (var2.R > 0) {
         boolean var11 = var10.isEmpty();
         long var12 = 106043655465992L;
         int var14 = var12 < 0L ? -1 : 1;
         var10.append(var11 ? "" : "  ·  ").append(var2.R).append(' ').append(Translations.plural(var2.R, "hit", "hits"));
      }

      if (!var10.isEmpty()) {
         this.text(var1, Fonts.MEDIUM, var10.toString(), var6 + var8 - 5.0F, var9 + 0.3F, 7.0F, Icons.TEXT_2, FontStyle.RIGHT, FontWeight.MIDDLE);
      }

      var1.pop();
      var1.popAlpha();
   }

   private void X(Canvas var1, TargetHud.TargetPopup var2, float var3, long var4) {
      short var6 = 9927;
      int var7 = -1992124386;
      long var8 = 28007100265805L;
      float var10 = MotionStyle.MENU.speed();

      for (TargetHud.DamagePopup var12 : var2.y) {
         float var13 = (var3 - var12.bornAt()) * var10;
         long var14 = 37750268736551L;
         int var17 = var14 < 0L ? -1 : 1;
         float var17x = var13 < 0.08F ? var13 / 0.08F : 1.0F - W((var13 - 0.55F) / 0.45F, var8);
         float var18 = 12.0F * Easing.EXPO_OUT.apply(W(var13, var8));
         float var19 = 1.0F + 0.3F * (1.0F - W(var13 / 0.15F, var8));
         float var20 = 23.0F - var18;
         boolean var21 = var12.amount() > 0.0F;
         String var22 = (var21 ? "+" : "−") + this.E(Math.abs(var12.amount()), var6, var2.e, var7);
         var1.push();
         long var23 = 37750268736551L;
         int var26 = var23 < 0L ? -1 : 1;
         var1.scaleAround(var19, var12.x(), var20);
         var1.pushAlpha(var17x);
         var1.textGlow(Fonts.BOLD, var22, var12.x(), var20, 8.5F, -1728053248, FontStyle.LEFT, FontWeight.MIDDLE, 0.0F, 0.8F, 1.8F);
         TrueTypeFont var26x = Fonts.BOLD;
         float var27 = var12.x();
         long var28 = 37750268736551L;
         int var28x = var28 < 0L ? -1 : 1;
         var1.text(var26x, var22, var27, var20, 8.5F, var21 ? -7670328 : -41852, FontStyle.LEFT, FontWeight.MIDDLE);
         var1.popAlpha();
         var1.pop();
      }
   }

   private void T(long var1, Canvas var3, TargetHud.TargetPopup var4, float var5) {
      long var6 = 28007100265805L;
      float var8 = MotionStyle.MENU.speed();
      float var9 = (var5 - var4.z) * var8 / 0.5F;
      if (var9 >= 0.0F && var9 < 1.0F) {
         float var10 = Easing.EXPO_OUT.apply(var9);
         var3.ring(var4.r, var4.j, 3.0F + 16.0F * var10, 1.6F * (1.0F - var9) + 0.3F, BlendMode.withAlpha(-11654, 0.85F * (1.0F - var9)));
      }

      for (TargetHud.FloatingNumber var11 : var4.T) {
         float var12 = (var5 - var11.bornAt()) * var8;
         float var13 = W(var12 / var11.life(), var6);
         float var14 = var11.x() + var11.vx() * var12;
         float var15 = var11.y() + var11.vy() * var12 + 38.0F * var12 * var12;
         float var16 = var11.size() * (1.0F - 0.6F * var13);
         long var17 = 12942956769269L;
         int var19 = var17 < 0L ? -1 : 1;
         if (var13 < 0.5F) {
            var3.glowCircle(var14, var15, var16, 3.0F, BlendMode.withAlpha(var11.color(), 0.5F * (1.0F - var13 * 2.0F)));
         }

         var3.circle(var14, var15, var16, BlendMode.withAlpha(var11.color(), 1.0F - var13));
      }
   }

   private void L(TargetHud.TargetPopup var1, PvpMode var2, long var3, TargetHud.TargetHudState[] var5, float var6) {
      long var7 = 136344217223378L;
      long var9 = 26324827680876L;
      short var11 = 6129;
      short var12 = 14848;
      int var13 = (int)(var9 << 48 >>> 48);
      long var14 = 117347309961634L;
      long var16 = 75789503998584L;
      long var18 = 25405781146984L;
      long var20 = 127689813628645L;
      long var22 = 127664657121647L;
      long var24 = 103398532876520L;
      long var26 = 16362739791252L;
      long var28 = 82225468408813L;
      long var30 = 81308535542909L;
      switch (TargetHud.TargetHudBatch.X[var2.ordinal()]) {
         case 1:
            this.k(var5[0], var11, var1, (char)var12, var6, (char)var13);
            TargetHud.TargetHudState var32 = var5[1];
            long var33 = 133110249443786L;
            int var63 = var33 < 0L ? -1 : 1;
            P(var32, var1, var6, var7);
            Z(var14, var5[2], var1);
            break;
         case 2: {
            this.k(var5[0], var11, var1, (char)var12, var6, (char)var13);
            u(var5[1], "circle-dot", var1.F, "pearls", var22, "pearl");
            long var36 = 133110249443786L;
            int var641 = var36 < 0L ? -1 : 1;
            Y(var28, var5[2], var1, var6);
            break;
         }
         case 3: {
            G(var5[0], var1, var26, var6);
            this.k(var5[1], var11, var1, (char)var12, var6, (char)var13);
            TargetHud.TargetHudState var39 = var5[2];
            long var40 = 133110249443786L;
            int var651 = var40 < 0L ? -1 : 1;
            u(var39, "wind", var1.d, "charges", var22, "charge");
            break;
         }
         case 4:
            u(var5[0], "flask-conical", var1.Z, "pots", var22, "pot");
            TargetHud.TargetHudState var43 = var5[1];
            long var44 = 133110249443787L;
            int var66 = var44 < 0L ? -1 : 1;
            u(var43, "sparkle", var1.eh, "bottles", var22, "bottle");
            this.k(var5[2], var11, var1, (char)var12, var6, (char)var13);
            break;
         case 5:
            i(var5[0], var20, var1, var6);
            x(var5[1], var1, var24);
            long var47 = 133110249443786L;
            int var67 = var47 < 0L ? -1 : 1;
            this.m(var5[2], var1, var30);
            break;
         case 6: {
            c(var5[0], var1, var6, var16);
            long var50 = 133110249443787L;
            int var681 = var50 < 0L ? -1 : 1;
            x(var5[1], var1, var24);
            Z(var14, var5[2], var1);
            break;
         }
         case 7: {
            Y(var28, var5[0], var1, var6);
            u(var5[1], "bow-arrow", var1.eA, "arrows", var22, "arrow");
            TargetHud.TargetHudState var53 = var5[2];
            long var54 = 133110249443787L;
            int var691 = var54 < 0L ? -1 : 1;
            Z(var14, var53, var1);
            break;
         }
         case 8: {
            u(var5[0], "flask-conical", var1.Z, "pots", var22, "pot");
            long var57 = 133110249443786L;
            int var701 = var57 < 0L ? -1 : 1;
            var5[1].set("sparkles", "", "", Icons.TEXT, accent()).Y = var1.e6;
            Z(var14, var5[2], var1);
            break;
         }
         case 9:
            TargetHud.TargetHudState var60 = var5[0];
            long var61 = 133110249443786L;
            int var72 = var61 < 0L ? -1 : 1;
            var60.set("gauge", String.format(Locale.ROOT, "%.1f", var1.O.value()), "b/s", Icons.TEXT, accent());
            x(var5[1], var1, var24);
            this.m(var5[2], var1, var30);
            break;
         default: {
            TargetHud.TargetHudState var64 = var5[0];
            long var65 = 133110249443786L;
            int var68 = var65 < 0L ? -1 : 1;
            var64.set("shield-half", Integer.toString(var1.c), "armor", Icons.TEXT, accent());
            var64 = var5[1];
            String var68x = c(var18, var1.em);
            boolean var69 = var1.em.method_7960();
            long var70 = 133110249443787L;
            int var62 = var70 < 0L ? -1 : 1;
            var64.set(var68x, var69 ? "Empty hand" : var1.em.method_7964().getString(), "", var1.em.method_7960() ? Icons.TEXT_2 : Icons.TEXT, accent());
            this.m(var5[2], var1, var30);
         }
      }
   }

   private void k(TargetHud.TargetHudState var1, int var2, TargetHud.TargetPopup var3, char var4, float var5, char var6) {
      long var7 = 26324827680876L;
      long var9 = var7 ^ 2021164605992L;
      var1.set("shield-plus", Integer.toString(var3.l), Translations.plural(var3.l, "pop", "pops"), var3.l > 0 ? -11654 : Icons.TEXT, -11654);
      var1.Q = var3.m.value();
      var1.b = F(var5, var9, var3.z, 0.8F);
   }

   private static void P(TargetHud.TargetHudState var0, TargetHud.TargetPopup var1, float var2, long var3) {
      long var5 = 136344217223378L;
      int var8 = var5 < 0L ? -1 : 1;
      if (var1.i) {
         long var8x = 136344217223378L;
         int var9 = var8x < 0L ? -1 : 1;
         var0.set("circle-check", "Totem", "", Icons.TEXT, -7670328);
      } else {
         var0.set("circle-x", "No totem", "", Icons.PINK, Icons.PINK);
         var0.B = Icons.PINK;
         var0.b = 0.25F + 0.25F * (float)Math.sin(var2 * 6.0F);
      }
   }

   private static void Z(long var0, TargetHud.TargetHudState var2, TargetHud.TargetPopup var3) {
      long var4 = 103007098305419L;
      long var6 = 107782046421428L;
      float var8 = 2.0F;

      for (class_1799 var12 : var3.en) {
         long var13 = 117347309961633L;
         int var16 = var13 < 0L ? -1 : 1;
         long var16x = 117347309961633L;
         int var18 = var16x < 0L ? -1 : 1;
         long var18x = 117347309961633L;
         int var19 = var18x < 0L ? -1 : 1;
         if (!var12.method_7960() && var12.method_7963()) {
            var8 = Math.min(var8, M(var4, var12));
         }
      }

      long var20 = 117347309961634L;
      int var211 = var20 < 0L ? -1 : 1;
      float var15;
      int var14 = (var15 = var8 - 1.0F) == 0.0F ? 0 : (var15 < 0.0F ? -1 : 1);
      long var21x = 117347309961633L;
      int var26 = var21x < 0L ? -1 : 1;
      if (var14 > 0) {
         var2.set("shield-half", "—", "armor", Icons.TEXT_2, accent());
      } else {
         var2.set("shield-half", Math.round(var8 * 100.0F) + "%", "armor", q(var6, var8), accent());
      }
   }

   private static void i(TargetHud.TargetHudState var0, long var1, TargetHud.TargetPopup var3, float var4) {
      long var5 = 127689813628644L;
      int var8 = var5 < 0L ? -1 : 1;
      boolean var8x = var3.L >= 3;
      var0.set("flame", Integer.toString(var3.L), "combo", Icons.TEXT, var8x ? -30116 : accent());
      long var9 = 127689813628644L;
      int var10 = var9 < 0L ? -1 : 1;
      var0.Q = var3.eq.value();
      var0.Z = var8x ? 0.6F + 0.4F * (float)Math.sin(var4 * 9.0F) : 0.0F;
   }

   private static void x(TargetHud.TargetHudState var0, TargetHud.TargetPopup var1, long var2) {
      var0.set("swords", var1.eO + "–" + var1.Y, "hits", Icons.TEXT, accent());
   }

   private void m(TargetHud.TargetHudState var1, TargetHud.TargetPopup var2, long var3) {
      short var5 = 9927;
      int var6 = -1992124386;
      float var7 = var2.S - (var2.E + var2.o);
      String var8 = (var7 >= 0.0F ? "+" : "−") + this.E(Math.abs(var7), var5, var2.e, var6);
      float var9;
      int var10 = (var9 = var7 - 0.25F) == 0.0F ? 0 : (var9 < 0.0F ? -1 : 1);
      long var11 = 81308535542909L;
      int var14 = var11 < 0L ? -1 : 1;
      if (var10 > 0) {
         var1.set("trending-up", var8, "ahead", -7670328, -7670328);
      } else {
         float var14x;
         var10 = (var14x = var7 - -0.25F) == 0.0F ? 0 : (var14x < 0.0F ? -1 : 1);
         long var15 = 81308535542908L;
         int var13x = var15 < 0L ? -1 : 1;
         if (var10 < 0) {
            var13x = -509657001;
            long var17 = 81308535542908L;
            int var19 = var17 < 0L ? -1 : 1;
            var1.set("trending-down", var8, "behind", Icons.PINK, Icons.PINK);
         } else {
            var1.set("minus", "Even", "", Icons.TEXT_2, Icons.TEXT_2);
         }
      }
   }

   private static void c(TargetHud.TargetHudState var0, TargetHud.TargetPopup var1, float var2, long var3) {
      long var5 = 28007100265805L;
      float var7 = var1.g - var2;
      if (var7 > 0.0F) {
         long var8 = 75789503998584L;
         int var10 = var8 < 0L ? -1 : 1;
         float var11;
         int var12 = (var11 = var1.g - -1000.0F) == 0.0F ? 0 : (var11 < 0.0F ? -1 : 1);
         long var13 = 75789503998583L;
         int var14 = var13 < 0L ? -1 : 1;
         if (var12 != 0) {
            var0.set("shield-off", String.format(Locale.ROOT, "%.1fs", var7), "down", Icons.PINK, Icons.PINK);
            var0.S = W(var7 / Math.max(0.1F, var1.g - var1.G), var5);
            var0.B = Icons.PINK;
            var0.b = 0.35F;
            return;
         }
      }

      if (!var1.D) {
         var0.set("shield-off", "None", "shield", Icons.TEXT_3, Icons.TEXT_3);
      } else if (var1.eV) {
         var0.set("shield", "Up", "shield", Icons.TEXT, accent());
         var0.Z = 0.6F;
      } else if (var2 - var1.g < 0.8F) {
         long var16 = 75789503998584L;
         int var10 = var16 < 0L ? -1 : 1;
         var10 = 511093368;
         var0.set("shield-check", "Ready", "", -7670328, -7670328);
         var0.B = -7670328;
         var0.b = 1.0F - W((var2 - var1.g) / 0.8F, var5);
      } else {
         var0.set("shield", "Down", "shield", Icons.TEXT_2, Icons.TEXT_2);
      }
   }

   private static void G(TargetHud.TargetHudState var0, TargetHud.TargetPopup var1, long var2, float var4) {
      float var5 = var1.ed.value();
      float var6;
      int var7 = (var6 = var5 - 0.3F) == 0.0F ? 0 : (var6 < 0.0F ? -1 : 1);
      long var8 = 16362739791251L;
      int var11 = var8 < 0L ? -1 : 1;
      if (var7 < 0) {
         var0.set("footprints", "Ground", "", Icons.TEXT_2, accent());
      } else if (var1.u && var5 > 2.5F) {
         long var13 = 16362739791252L;
         int var10x = var13 < 0L ? -1 : 1;
         var10x = -1085622336;
         var0.set("chevrons-down", String.format(Locale.ROOT, "%.1f", var5), "m up", Icons.PINK, Icons.PINK);
         var0.B = Icons.PINK;
         var0.b = 0.3F + 0.3F * (float)Math.sin(var4 * 12.0F);
      } else {
         long var11x = 16362739791252L;
         int var10 = var11x < 0L ? -1 : 1;
         var0.set(var1.eC ? "feather" : "arrow-up-from-line", String.format(Locale.ROOT, "%.1f", var5), "m up", Icons.TEXT, accent());
      }
   }

   private static void Y(long var0, TargetHud.TargetHudState var2, TargetHud.TargetPopup var3, float var4) {
      long var5 = 24359658639940L;
      var2.set("apple", Integer.toString(var3.ep), Translations.plural(var3.ep, "gapple", "gapples"), Icons.TEXT, -11654);
      var2.b = F(var4, var5, var3.B, 1.0F);
      var2.B = -11654;
   }

   private static void u(TargetHud.TargetHudState var0, String var1, int var2, String var3, long var4, String var6) {
      var0.set(var1, Integer.toString(var2), Translations.plural(var2, var6, var3), var2 > 0 ? Icons.TEXT : Icons.TEXT_2, accent());
   }

   private static String c(long var0, class_1799 var2) {
      long var3 = 25405781146984L;
      int var6 = var3 < 0L ? -1 : 1;
      boolean var6x = var2.method_7960();
      long var7 = 25405781146983L;
      int var10 = var7 < 0L ? -1 : 1;
      if (var6x) {
         return "hand";
      } else {
         long var10x = 25405781146984L;
         var6x = var2.method_31573(class_3489.field_42611);
         long var12x = 25405781146984L;
         if (var6x) {
            return "sword";
         } else {
            long var14x = 25405781146984L;
            var6x = var2.method_31573(class_3489.field_42612);
            long var16x = 25405781146984L;
            if (var6x) {
               return "axe";
            } else {
               long var18x = 25405781146984L;
               var6x = var2.method_31574(class_1802.field_49814);
               long var20x = 25405781146983L;
               if (var6x) {
                  return "hammer";
               } else {
                  long var22x = 25405781146984L;
                  var6x = var2.method_31573(class_3489.field_63257);
                  long var24x = 25405781146983L;
                  if (var6x) {
                     return "move-up-right";
                  } else {
                     long var26x = 25405781146984L;
                     var6x = var2.method_31574(class_1802.field_8102);
                     long var28x = 25405781146984L;
                     if (!var6x) {
                        long var30x = 25405781146983L;
                        var6x = var2.method_31574(class_1802.field_8399);
                        long var32x = 25405781146984L;
                        if (!var6x) {
                           long var34x = 25405781146984L;
                           var6x = var2.method_57826(class_9334.field_56396);
                           long var36x = 25405781146983L;
                           if (var6x) {
                              return "shield";
                           }

                           var6x = var2.method_57826(class_9334.field_54274);
                           long var38x = 25405781146983L;
                           if (var6x) {
                              return "shield-plus";
                           }

                           var6x = var2.method_31574(class_1802.field_8301);
                           long var40x = 25405781146984L;
                           if (!var6x) {
                              long var42x = 25405781146983L;
                              var6x = var2.method_31574(class_1802.field_23141);
                              long var44x = 25405781146983L;
                              if (!var6x) {
                                 long var46x = 25405781146984L;
                                 var6x = var2.method_57826(class_9334.field_49651);
                                 long var48x = 25405781146984L;
                                 if (var6x) {
                                    return "flask-conical";
                                 }

                                 long var50x = 25405781146984L;
                                 var6x = var2.method_31574(class_1802.field_8634);
                                 long var52x = 25405781146983L;
                                 if (var6x) {
                                    return "circle-dot";
                                 }

                                 var6x = var2.method_57826(class_9334.field_50075);
                                 long var54x = 25405781146984L;
                                 if (var6x) {
                                    return "apple";
                                 }

                                 var6x = var2.method_7909() instanceof class_1747;
                                 long var56x = 25405781146984L;
                                 return var6x ? "box" : "package";
                              }
                           }

                           return "gem";
                        }
                     }

                     return "bow-arrow";
                  }
               }
            }
         }
      }
   }

   private static String e(long var0, class_6880 var2) {
      if (rU == null) {
         rU = Map.ofEntries(
            Map.entry(class_1294.field_5904, "wind"),
            Map.entry(class_1294.field_5910, "biceps-flexed"),
            Map.entry(class_1294.field_5918, "flame"),
            Map.entry(class_1294.field_5924, "heart-pulse"),
            Map.entry(class_1294.field_5907, "shield"),
            Map.entry(class_1294.field_5898, "heart"),
            Map.entry(class_1294.field_5914, "heart"),
            Map.entry(class_1294.field_5905, "eye-off"),
            Map.entry(class_1294.field_5909, "turtle"),
            Map.entry(class_1294.field_5911, "heart-crack"),
            Map.entry(class_1294.field_5899, "biohazard"),
            Map.entry(class_1294.field_5920, "skull"),
            Map.entry(class_1294.field_5913, "arrow-big-up"),
            Map.entry(class_1294.field_5906, "feather"),
            Map.entry(class_1294.field_5925, "eye"),
            Map.entry(class_1294.field_5923, "droplets"),
            Map.entry(class_1294.field_5917, "zap")
         );
      }

      return rU.getOrDefault(var2, "sparkle");
   }

   private String E(float var1, int var2, float var3, int var4) {
      long var5 = 42638443190302L;
      long var7 = var5 ^ 38338130002653L;
      String var9 = (String)this.K.get();

      return switch (var9) {
         case "Points" -> b(var1, var7);
         case "Percent" -> Math.round(100.0F * var1 / var3) + "%";
         default -> b(var1 / 2.0F, var7);
      };
   }

   private static String b(float var0, long var1) {
      float var3 = Math.round(var0 * 10.0F) / 10.0F;
      long var4 = 4508706691779L;
      int var6 = var4 < 0L ? -1 : 1;
      return var3 == (int)var3 ? Integer.toString((int)var3) : String.format(Locale.ROOT, "%.1f", var3);
   }

   private static String f(long var0, short var2, float var3) {
      return var3 >= 10.0F ? Math.round(var3) + " m" : String.format(Locale.ROOT, "%.1f m", var3);
   }

   private static float M(long var0, class_1799 var2) {
      return var2.method_7963() ? 1.0F - (float)var2.method_7919() / var2.method_7936() : 1.0F;
   }

   private static int q(long var0, float var2) {
      float var3;
      int var4 = (var3 = var2 - 0.5F) == 0.0F ? 0 : (var3 < 0.0F ? -1 : 1);
      long var5 = 107782046421428L;
      int var7 = var5 < 0L ? -1 : 1;
      return var4 > 0 ? BlendMode.lerp(-11654, -7670328, (var2 - 0.5F) * 2.0F) : BlendMode.lerp(-41852, -11654, var2 * 2.0F);
   }

   private static float F(float var0, long var1, float var3, float var4) {
      long var5 = 24359658639940L;
      int var8 = var5 < 0L ? -1 : 1;
      if (var3 == -1000.0F) {
         return 0.0F;
      } else {
         float var8x = (var0 - var3) * MotionStyle.MENU.speed() / var4;
         if (!(var8x < 0.0F)) {
            long var9 = 24359658639940L;
            int var10 = var9 < 0L ? -1 : 1;
            if (!(var8x >= 1.0F)) {
               return (1.0F - var8x) * (1.0F - var8x);
            }
         }

         return 0.0F;
      }
   }

   private static float W(float var0, long var1) {
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new TargetEditorPage(this);
   }

   protected void opened(ModuleContext var1) {
      TargetUpdater.install(this);
      var1.own(TargetProvider.install(new dev.purxiy.loader.fabric.client.TargetProvider.Listener() {
         public class_1309 target() {
            return TargetUpdater.target();
         }

         public float fightAt() {
            return TargetUpdater.fightAt();
         }
      }));
      var1.own(TargetUpdater::disposeOwnedState);
      var1.own(ModuleWidget.onTick(TargetUpdater::tick));
      var1.own(ModuleWidget.onActualAttack((var0, var1x) -> TargetUpdater.onAttack(var1x)));
      var1.own(ModuleWidget.onSound(TargetUpdater::onSound));
      var1.own(SkyPipelineRouter.ENTITY_LOAD.subscribe(0, TargetUpdater::onEntityLoad));
      var1.own(SkyPipelineRouter.ENTITY_EVENT.subscribe(0, TargetUpdater::onEntityEvent));
      var1.own(
         SkyPipelineRouter.DAMAGE
            .subscribe(0, (dev.purxiy.loader.fabric.client.SkyPipelineRouter.Listener)(var0, var1x, var2, var3, var4) -> TargetUpdater.onDamage(var0, var1x, var2))
      );
   }

   static void playSound(TargetKind var0) {
      UiAudioPolicy.play(var0.name());
   }

   static void playSound(TargetKind var0, float var1) {
      UiAudioPolicy.play(var0.name(), var1, 1.0F);
   }

   static void playSound(TargetKind var0, float var1, float var2) {
      UiAudioPolicy.play(var0.name(), var1, var2);
   }

   public static void ensureSourceClassInitialized() {
   }

   private static String p(class_1309 var0, long var1) {
      return Float.toString(pdist(var0, var1));
   }

   private record DamagePopup(float J, float A, float y) {
      public float amount() {
         return this.J;
      }

      public float bornAt() {
         return this.A;
      }

      public float x() {
         return this.y;
      }

      public float floatState10() {
         return this.J;
      }

      public float floatState56() {
         return this.A;
      }

      public float floatState2() {
         return this.y;
      }
   }

   private static final class TargetHudState {
      String r = "";
      String V = "";
      String l = "";
      int u;
      int e;
      int B;
      float Q;
      float Z;
      float b;
      float S = -1.0F;
      List<class_6880<class_1291>> Y;

      TargetHud.TargetHudState set(String var1, String var2, String var3, int var4, int var5) {
         this.r = var1;
         this.V = var2;
         this.l = var3;
         this.u = var4;
         this.e = var5;
         this.B = var5;
         this.Q = 0.0F;
         this.Z = 0.0F;
         this.b = 0.0F;
         this.S = -1.0F;
         this.Y = null;
         return this;
      }
   }

   private static final class TargetPopup {
      final class_1309 ek;
      final boolean k;
      final UUID q;
      float V;
      float eM;
      float A;
      boolean a = true;
      String ex = "";
      class_2960 e1;
      String eL = "user";
      String J = "PLAYER";
      boolean eb;
      float E;
      float e = 20.0F;
      float o;
      float e5;
      float w;
      float U;
      float S;
      boolean u;
      boolean eC;
      boolean i;
      boolean D;
      boolean eV;
      final class_1799[] en = new class_1799[]{class_1799.field_8037, class_1799.field_8037, class_1799.field_8037, class_1799.field_8037};
      int c;
      class_1799 em = class_1799.field_8037;
      final List<class_6880<class_1291>> e6 = new ArrayList<>();
      int l;
      int eO;
      int Y;
      int L;
      int Z;
      int eh;
      int F;
      int d;
      int eA;
      int ep;
      int W;
      int R;
      float e9 = -1000.0F;
      float P = -1000.0F;
      float G = -1000.0F;
      float g = -1000.0F;
      float s = -1000.0F;
      final float[] e2 = new float[]{-1000.0F, -1000.0F, -1000.0F, -1000.0F};
      final Animator h = new Animator(0.0F, 170.0F, 26.0F);
      final Animator N = new Animator(0.0F, 170.0F, 26.0F);
      final Animator m = new Animator(0.0F, 320.0F, 14.0F);
      final Animator eq = new Animator(0.0F, 320.0F, 14.0F);
      final Animator O = Animator.smooth(0.0F);
      final Animator ed = Animator.smooth(0.0F);
      float eU;
      float C = -1000.0F;
      float H = Float.NaN;
      float ef = -1000.0F;
      float v = -1000.0F;
      float M = -1000.0F;
      float K;
      float z = -1000.0F;
      float B = -1000.0F;
      float t = -1000.0F;
      float b = -1000.0F;
      float I = -1000.0F;
      float p = -1000.0F;
      int eo;
      float ea = 71.0F;
      float r;
      float j;
      double Q;
      double x;
      double eT;
      float f;
      float n;
      boolean X;
      final List<TargetHud.DamagePopup> y = new ArrayList<>();
      final List<TargetHud.FloatingNumber> T = new ArrayList<>();

      TargetPopup(class_1309 var1, float var2) {
         this.ek = var1;
         this.k = var1 == null;
         this.q = var1 == null ? TargetHud.rW : var1.method_5667();
         this.V = var2;
      }
   }

   private record FloatingNumber(float b, float n, float f, float a, float l, float R, int k, float F) {
      public float x() {
         return this.b;
      }

      public float y() {
         return this.n;
      }

      public float vx() {
         return this.f;
      }

      public float vy() {
         return this.a;
      }

      public float bornAt() {
         return this.l;
      }

      public float life() {
         return this.R;
      }

      public int color() {
         return this.k;
      }

      public float size() {
         return this.F;
      }

      public float floatState32() {
         return this.b;
      }

      public float floatState22() {
         return this.n;
      }

      public float floatState55() {
         return this.f;
      }

      public float floatState31() {
         return this.a;
      }

      public float floatState37() {
         return this.l;
      }

      public float floatState46() {
         return this.R;
      }

      public int integerState46() {
         return this.k;
      }

      public float floatState36() {
         return this.F;
      }
   }

   static class TargetHudBatch {
      static final int[] X = new int[PvpMode.values().length];

      static {
         try {
            X[PvpMode.U.ordinal()] = 1;
         } catch (NoSuchFieldError var9) {
         }

         try {
            X[PvpMode.a.ordinal()] = 2;
         } catch (NoSuchFieldError var8) {
         }

         try {
            X[PvpMode.D.ordinal()] = 3;
         } catch (NoSuchFieldError var7) {
         }

         try {
            X[PvpMode.g.ordinal()] = 4;
         } catch (NoSuchFieldError var6) {
         }

         try {
            X[PvpMode.X.ordinal()] = 5;
         } catch (NoSuchFieldError var5) {
         }

         try {
            X[PvpMode.Y.ordinal()] = 6;
         } catch (NoSuchFieldError var4) {
         }

         try {
            X[PvpMode.l.ordinal()] = 7;
         } catch (NoSuchFieldError var3) {
         }

         try {
            X[PvpMode.j.ordinal()] = 8;
         } catch (NoSuchFieldError var2) {
         }

         try {
            X[PvpMode.P.ordinal()] = 9;
         } catch (NoSuchFieldError var1) {
         }
      }
   }
}
