package dev.purxiy.modules.knife;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.i18n.Translations.TypeM;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.font.TrueTypeFont;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.DrawContext.TypeE;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_310;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class KnifeRenderer6 {
   private static final float J = 272.0F;
   private static final float U = 26.0F;
   private static final float Z = 6.0F;
   private static final float sy = 288.0F;
   private static final float x = 12.0F;
   private static final float sm = 18.0F;
   private static final float w = 92.0F;
   private static final float V = 15.0F;
   private static final float N = 22.0F;
   private static final float sq = 126.0F;
   private static final float I = 16.0F;
   private static final float sS = 240.0F;
   private static final float sD = 256.0F;
   private static final float O = 316.0F;
   private static final int e = 2;
   private static final int S = 3;
   public static final float sF = 312.0F + i(2) + i(3) + 18.0F + 16.0F + 316.0F + 14.0F;
   private static final int j = -4222985;
   private static final String[] sp = new String[]{
      "Talon  ·  Counter-Strike",
      "Balisong  ·  Counter-Strike",
      "Bayonet  ·  Counter-Strike",
      "Combat knife  ·  Call of Duty",
      "Frost blade  ·  Murder Mystery 2"
   };
   private static final String[] A = new String[]{"TALON", "BALISONG", "BAYONET", "COMBAT", "FROST"};
   private static final String[] m = new String[]{"Signature", "Obsidian", "Doppler", "Gold"};
   private static final String[] s4 = new String[]{"Steel", "Obsidian", "Amethyst", "Gold"};
   private static final String[] C = new String[]{
      "Polished steel with bright, sharpened edges",
      "Blackened steel with silver edges",
      "Violet anodised steel that glows at the edge",
      "Gold-plated steel and warm accents"
   };
   private static final int[][] B = new int[][]{{-6508865, -2036235}, {-13354164, -7035204}, {-8230966, -4086278}, {-2708905, -7520}};
   private static final Matrix4f a = new Matrix4f().translate(0.0F, 0.1F, 0.0F).rotateZ(-0.65F).translate(0.0F, -0.1F, 0.0F);
   private static final String[][] si = new String[][]{
      {"draw", "Draw", "sparkles"},
      {"slash1", "Diagonal cut", "swords"},
      {"slash2", "Rising cut", "swords"},
      {"slash3", "Sweep", "swords"},
      {"crit", "Critical", "zap"},
      {"thrust", "Thrust", "swords"},
      {"chop", "Chop", "pickaxe"},
      {"idle", "Flourish", "play"}
   };
   private final KnifeModule b;
   private static final int d = 32;
   private static final int f = 46;
   private final Animator[] z = new Animator[46 + KnifeFactory2.B.length];
   private final Animator sQ = Animator.smooth(1.0F);
   private final KnifeBuilder[] E = new KnifeBuilder[5];
   private final KnifeBuilder[] sV = new KnifeBuilder[KnifeFactory2.B.length];
   private final String[] y = new String[5];
   private final String[] u = new String[KnifeFactory2.B.length];
   private float T;
   private float L;
   private float h;
   private float k = -0.42F;
   private float n;
   private float Y;
   private float R;
   private float W;
   private float M = Float.NaN;
   private int F = 2;
   private int c = 2;
   private float o = -1.0F;
   private float r = sF - 316.0F - 14.0F;
   private float se = sF;
   private Object v;
   private boolean D;
   private boolean i;
   private int q = -1;
   private int t;
   private int sG = -1;
   private int p = -1;
   private int s = -1;
   private KnifeBuilder Q;
   private String sx = "";
   private final List<KnifeRenderer6.TypeV> g = new ArrayList<>();
   private int P;
   private final float[] sb = new float[32];
   private final float[] l = new float[32];
   private final float[] X = new float[32];
   private final int[] K = new int[32];
   private int sL;
   private final KnifeRenderer7.TypeW sz = new KnifeRenderer7.TypeW();
   private final KnifeRenderer7.TypeW G = new KnifeRenderer7.TypeW();
   private final KnifeCurve sC = new KnifeCurve();
   private final KnifeCurve H = new KnifeCurve();

   public KnifeRenderer6(KnifeModule var1) {
      this.b = var1;

      for (int var2 = 0; var2 < this.z.length; var2++) {
         this.z[var2] = Animator.smooth(0.0F);
      }
   }

   private int W(long var1) {
      KnifeUpdater2.ensureSourceClassInitialized();
      int var3 = KnifeFactory2.index((String)this.b.z.get());
      return var3 >= 0 ? 5 + var3 : KnifeModels.index((String)this.b.U.get());
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 124393316066684L;
      long var8 = var6 ^ 123049718472815L;
      long var10 = var6 ^ 88865190803303L;
      long var12 = var6 ^ 82424011887473L;
      long var14 = var6 ^ 27336515957931L;
      int var16 = (int)((var6 ^ 27336515957931L) >>> 32);
      int var17 = (int)((var6 ^ 27336515957931L) << 32 >>> 48);
      int var18 = (int)(var14 << 48 >>> 48);
      long var19 = var6 ^ 103785006480936L;
      long var21 = var6 ^ 129484849550930L;
      long var23 = var6 ^ 74544152734725L;
      long var25 = var6 ^ 110736977654179L;
      long var27 = var6 ^ 132823201514655L;
      long var29 = var6 ^ 8319018803451L;
      long var31 = var6 ^ 139796496934852L;
      this.T = var2;
      this.L = var3;
      this.h = var4;
      this.D = var5 && !var1.masked();
      float var33 = Math.min(var1.dt, 0.1F);
      int var34 = this.W(var21);
      if (var34 != this.p) {
         this.p = var34;
         this.g.clear();
         if (var34 < 5) {
            KnifeRenderer7.inspects(var34).forEach(var1x -> {
               long var2x = 1132844984L;
               char var4x = 'ꡉ';
               this.g.add(new KnifeRenderer6.TypeV(var1x.j, X(var2x, var1x, var4x), var1x, null));
            });
            this.P = this.g.size();
            D(var34, var16, var17, var18).forEach(var1x -> {
               long var2x = 13228203855773L;
               long var4x = (var2x ^ 87420735121364L) >>> 16;
               int var6x = (int)((var2x ^ 87420735121364L) << 48 >>> 48);
               this.g.add(new KnifeRenderer6.TypeV(var1x.j, X(var4x, var1x, (char)var6x), var1x, null));
            });
         } else {
            this.g.addAll(u(var34 - 5, var8));
            this.P = (int)this.g.stream().filter(var0 -> {
               KnifeUpdater2.ensureSourceClassInitialized();
               boolean var1x = false;
               return var0.z != null && var0.z.o.startsWith("inspect");
            }).count();
         }

         this.y(this.h(var31));
      }

      this.R += var33;
      this.Y = Math.max(0.0F, this.Y - var33);
      this.v(var33, var23);
      if (!Float.isNaN(this.M) && !this.i) {
         this.k = this.k + (this.M - this.k) * (1.0F - (float)Math.exp(-var33 * 4.5F));
         if (Math.abs(this.M - this.k) < 0.002F) {
            this.M = Float.NaN;
         }
      }

      if (!this.i && this.Y == 0.0F && this.q < 0 && Float.isNaN(this.M)) {
         this.k += var33 * 0.16F;
      }

      Canvas var35 = var1.canvas;
      int var36 = var34 < 5 ? var34 : KnifeModels.index((String)this.b.U.get());
      KnifeRenderer6.TypeN var37 = this.z(var29, var36);
      this.x(var1, var35, var34, var37, var27);
      this.z(var1, var35, var10);
      this.T(var1, var35, var34, var25);
      this.b(var1, var35, var34, var12);
      this.k(var1, var19, var35, var34);
   }

   private int h(long var1) {
      KnifeUpdater2.ensureSourceClassInitialized();

      for (int var3 = 0; var3 < this.g.size(); var3++) {
         KnifeRenderer6.TypeV var4 = this.g.get(var3);
         long var5 = 15421010920119L;
         int var8 = var5 < 0L ? -1 : 1;
         long var8x = 15421010920120L;
         int var9 = var8x < 0L ? -1 : 1;
         if (var4.a != null && var4.a.Q.equals("draw") || var4.z != null && var4.z.o.equals("draw")) {
            return var3;
         }
      }

      long var11 = 15421010920119L;
      int var11x = var11 < 0L ? -1 : 1;
      return this.g.isEmpty() ? -1 : 0;
   }

   private static String X(long var0, KnifeRenderer7.TypeZ var2, char var3) {
      KnifeUpdater2.ensureSourceClassInitialized();
      long var4 = 1132844984L;
      int var7 = var4 < 0L ? -1 : 1;
      String var7x;
      if (var2.O) {
         var7x = "hand-metal";
      } else {
         long var8 = 1132844983L;
         int var10 = var8 < 0L ? -1 : 1;
         if (var2.V) {
            var7x = "swords";
         } else {
            boolean var10x = var2.Q.equals("draw");
            var7x = var10x ? "sparkles" : "play";
         }
      }

      return var7x;
   }

   private static List u(int var0, long var1) {
      long var3 = 131479188460051L;
      KnifeUpdater2.ensureSourceClassInitialized();
      KnifeTransform.TypeH var5 = KnifeTransform.sheet(q(var0, var3));
      ArrayList var6 = new ArrayList();
      KnifeTransform.TypeU var7 = var5.clip("inspect");
      if (var7 != null) {
         var6.add(new KnifeRenderer6.TypeV("Inspect", "eye", null, var7));
      }

      for (int var8 = 2; var5.clip("inspect" + var8) != null; var8++) {
         var6.add(new KnifeRenderer6.TypeV("Inspect " + var8, "eye", null, var5.clip("inspect" + var8)));
      }

      long var19 = 33857993229587L;
      int var10 = var19 < 0L ? -1 : 1;

      for (String[] var14 : si) {
         KnifeTransform.TypeU var15 = var5.clip(var14[0]);
         long var16 = 33857993229587L;
         int var17 = var16 < 0L ? -1 : 1;
         if (var15 != null) {
            var6.add(new KnifeRenderer6.TypeV(var15.N != null ? var15.N : var14[1], var14[2], null, var15));
         }
      }

      return var6;
   }

   private static String q(int var0, long var1) {
      return new String[]{"katana", "greatsword", "scythe"}[var0];
   }

   private KnifeRenderer6.TypeN z(long var1, int var3) {
      KnifeUpdater2.ensureSourceClassInitialized();
      if (this.sG >= 0) {
         return this.sG < m.length
            ? new KnifeRenderer6.TypeN(m[this.sG], "Original")
            : new KnifeRenderer6.TypeN((String)this.b.F.get(), KnifeCamos.forModel(var3).get(this.sG - m.length).name());
      } else {
         return new KnifeRenderer6.TypeN((String)this.b.F.get(), (String)this.b.camoSetting().get());
      }
   }

   private void y(int var1) {
      this.q = var1;
      this.W = 0.0F;
      this.t = 0;
      float var2 = -0.42F;
      this.M = var2 + (float) (Math.PI * 2) * (float)Math.round((this.k - var2) / (Math.PI * 2));
   }

   private void v(float var1, long var2) {
      KnifeUpdater2.ensureSourceClassInitialized();
      long var4 = 56000918112633L;
      int var7 = var4 < 0L ? -1 : 1;
      if (this.q >= 0 && this.q < this.g.size()) {
         KnifeRenderer6.TypeV var7x = this.g.get(this.q);
         float var8 = this.W;
         this.W = this.W + var1 * ((Double)this.b.C.get()).floatValue();
         long var9 = 56000918112632L;
         int var12 = var9 < 0L ? -1 : 1;
         long var12x = 56000918112632L;
         int var12x = var12x < 0L ? -1 : 1;
         if (var7x.a == null) {
            if (var7x.z != null) {
               List var22 = var7x.z.U;

               while (this.t < var22.size()) {
                  float var25 = ((KnifeTransform.Data)var22.get(this.t)).time();
                  long var17 = 56000918112633L;
                  int var31 = var17 < 0L ? -1 : 1;
                  if (!(var25 <= this.W)) {
                     break;
                  }

                  KnifeTransform.Data var20 = (KnifeTransform.Data)var22.get(this.t++);
                  if (this.D && var20.time() >= var8 - 0.05F && var20.kind().equals("sound")) {
                     KnifeApi var27 = KnifeRenderer4.stageBlade(this.p - 5);
                     if (var27 != null) {
                        KnifeFactory.play(var27, var20.name(), var20.volume(), var20.pitch(), false);
                     }
                  }
               }
            }
         } else {
            List var15 = var7x.a.events();

            while (this.t < var15.size()) {
               long var16 = 56000918112632L;
               int var15x = var16 < 0L ? -1 : 1;
               float var18x;
               int var6x = (var18x = ((KnifeRenderer7.Data2)var15.get(this.t)).time() - this.W) == 0.0F ? 0 : (var18x < 0.0F ? -1 : 1);
               long var19 = 56000918112632L;
               int var19x = var19 < 0L ? -1 : 1;
               if (var6x > 0) {
                  break;
               }

               KnifeRenderer7.Data2 var21x = (KnifeRenderer7.Data2)var15.get(this.t++);
               if (this.D && var21x.time() >= var8 - 0.05F) {
                  KnifeSupport7.play(var21x);
               }
            }

            long var24 = 56000918112632L;
            int var30 = var24 < 0L ? -1 : 1;
         }

         long var23x = 56000918112633L;
         int var27 = var23x < 0L ? -1 : 1;
         long var26 = 56000918112633L;
         int var33 = var26 < 0L ? -1 : 1;
         this.sQ.target(0.8F).update(MotionStyle.PANEL.dt(var1));
         if (this.W >= var7x.duration()) {
            this.q = -1;
         }
      } else {
         this.q = -1;
         this.sQ.target(1.0F).update(MotionStyle.PANEL.dt(var1));
      }
   }

   private KnifeBuilder P(int var1, long var2, KnifeRenderer6.TypeN var4) {
      long var5 = 67525311875381L;
      KnifeUpdater2.ensureSourceClassInitialized();
      int var7 = (int)(this.R * 60.0F);
      boolean var8 = var1 >= 5;
      String var9 = var8 ? (String)this.b.F.get() : var4.E;
      if (var8 && this.sG >= 0) {
         long var10 = 74197430843934L;
         int var12 = var10 < 0L ? -1 : 1;
         if (this.sG < m.length) {
            var9 = m[this.sG];
         }
      }

      String var30 = var1 + var9 + var4.z + ":" + this.q;
      long var11 = 74197430843935L;
      int var31 = var11 < 0L ? -1 : 1;
      if (this.Q != null && var30.equals(this.sx)) {
         long var14 = 74197430843934L;
         int var32 = var14 < 0L ? -1 : 1;
         if (this.q < 0) {
            return this.Q;
         }

         long var16 = 74197430843934L;
         int var18 = var16 < 0L ? -1 : 1;
         if (var7 == this.s) {
            return this.Q;
         }
      }

      long var31x = 74197430843934L;
      int var321 = var31x < 0L ? -1 : 1;
      this.sx = var30;
      this.s = var7;
      if (var8) {
         this.Q = this.P(var5, var1 - 5, var9);
         return this.Q;
      } else {
         KnifeModels.Model var32x = var4.geometry(var1);
         Matrix4f var17 = new Matrix4f(a);
         float var18 = 0.0F;
         float var19 = 0.0F;
         long var20 = 74197430843935L;
         int var22 = var20 < 0L ? -1 : 1;
         long var22x = 74197430843935L;
         int var24 = var22x < 0L ? -1 : 1;
         if (this.q >= 0) {
            long var24x = 74197430843935L;
            int var26 = var24x < 0L ? -1 : 1;
            long var26x = 74197430843935L;
            int var29 = var26x < 0L ? -1 : 1;
            if (this.g.get(this.q).a != null) {
               KnifeRenderer7.TypeZ var29x = this.g.get(this.q).a;
               var29x.at(this.W, this.sz);
               var17.mul(KnifeGeometry2.onStage(var1, this.sz, var29x.V, new Matrix4f()));
               var18 = this.sz.c;
               var19 = this.sz.S;
            }
         }

         this.Q = var32x.posed(var17, var18, var19);
         return this.Q;
      }
   }

   private KnifeBuilder P(long var1, int var3, String var4) {
      long var5 = 116829598997744L;
      long var7 = 131479188460051L;
      long var9 = 20511819583200L;
      KnifeUpdater2.ensureSourceClassInitialized();
      Matrix4f var11 = W(var3, var5);
      if (this.q >= 0 && this.g.get(this.q).z != null) {
         KnifeTransform.TypeH var12 = KnifeTransform.sheet(q(var3, var7));
         KnifeTransform.TypeU var13 = this.g.get(this.q).z;
         var13.at(this.W, this.sC);
         var12.pose("ready", this.H);
         Quaternionf var14 = this.H.rotation(new Quaternionf());
         Quaternionf var15 = this.sC.rotation(new Quaternionf());
         Quaternionf var16 = new Quaternionf(var14).conjugate().mul(var15);
         Vector3f var17 = var14.conjugate(new Quaternionf()).transform(this.sC.position(new Vector3f()).sub(this.H.position(new Vector3f())));
         var17.mul(0.5F / Math.max(0.5F, var12.M));
         Vector3f var18 = y(var3, var9);
         Matrix4f var19 = new Matrix4f().translate(var17).translate(var18).rotate(var16).translate(-var18.x, -var18.y, -var18.z);
         var11.mul(var19);
      }

      return KnifeFactory2.get(var3, var4).posed(var11, 0.0F, 0.0F);
   }

   private static Matrix4f W(int var0, long var1) {
      long var3 = 20511819583200L;
      KnifeUpdater2.ensureSourceClassInitialized();
      Vector3f var5 = y(var0, var3);
      float var6 = var0 == 2 ? 0.56F : (var0 == 1 ? 0.64F : 0.72F);
      return new Matrix4f().rotateZ(var0 == 2 ? -0.55F : -0.9F).scale(var6).translate(-var5.x, -var5.y, -var5.z);
   }

   private static Vector3f y(int var0, long var1) {
      return switch (var0) {
         case 1 -> new Vector3f(0.0F, 0.29F, 0.0F);
         case 2 -> new Vector3f(-0.3F, -0.22F, 0.0F);
         default -> new Vector3f(0.0F, 0.22F, 0.0F);
      };
   }

   private void x(DrawContext var1, Canvas var2, int var3, KnifeRenderer6.TypeN var4, long var5) {
      long var7 = 74197430843935L;
      byte var9 = 0;
      long var10 = 59287937935254L;
      float var12 = this.T;
      float var13 = this.L;
      float var14 = this.h;
      KnifeUpdater2.ensureSourceClassInitialized();
      float var15 = 272.0F;
      float var16 = var12 + var14 * 0.54F;
      float var17 = var13 + var15 * 0.74F;
      boolean var18 = var3 >= 5;
      int var19 = var18 ? 0 : var3;
      var2.roundRectGradient(var12, var13, var14, var15, 16.0F, 90.0F, Theme.tint(-14805452), Theme.tint(-16316915));
      short var20 = 32337;
      var2.pushClip(var12 + 1.0F, var13 + 1.0F, var14 - 2.0F, var15 - 2.0F, 15.0F);
      int var21 = BlendMode.withAlpha(Theme.tint(-1647873), 0.0F);

      for (int var22 = 0; var22 < 4; var22++) {
         int var23 = BlendMode.withAlpha(Theme.tint(-1647873), 0.015F);
         float var24 = var14 * (0.025F + var22 * 0.01F);
         float var25 = var14 * (0.17F + var22 * 0.035F);
         long var26 = 10925806723554L;
         int var45 = var26 < 0L ? -1 : 1;
         var2.triangle(var16 - var24, var13, var16 + var24, var13, var16 + var25, var17, var23, var23, var21);
         var2.triangle(var16 - var24, var13, var16 + var25, var17, var16 - var25, var17, var23, var21, var21);
      }

      long var43 = 10925806723554L;
      int var401 = var43 < 0L ? -1 : 1;
      var2.glowCircle(var16, var13 - 6.0F, var14 * 0.045F, 46.0F, BlendMode.withAlpha(-1, 0.07F));
      var2.glowCircle(var16, var13 + var15 * 0.46F, var14 * 0.19F, 170.0F, BlendMode.withAlpha(Theme.tint(-4222985), 0.1F));
      long var44 = 10925806723554L;
      int var42 = var44 < 0L ? -1 : 1;
      var2.roundRectGradient(var12, var17 - 30.0F, var14, var15 - (var17 - 30.0F - var13), 0.0F, 90.0F, 0, 1778384896);
      var2.push();
      var2.translate(var16, var17 + 4.0F);
      var2.scale(1.0F, 0.2F);
      var2.softCircle(0.0F, 0.0F, var14 * 0.22F, 70.0F, BlendMode.withAlpha(Theme.tint(-4222985), 0.1F));
      var2.softCircle(0.0F, 0.0F, var14 * 0.1F, 34.0F, 1073741824);
      var2.ring(0.0F, 0.0F, var14 * 0.19F, 1.2F, BlendMode.withAlpha(Theme.tint(-4222985), 0.12F));
      long var28 = 10925806723555L;
      int var31 = var28 < 0L ? -1 : 1;
      var2.pop();
      if (var18) {
         var31 = var3 - 5;
         long var32 = 10925806723554L;
         int var31x = var32 < 0L ? -1 : 1;
         var2.text(
            Fonts.MEDIUM,
            "PURXIY BLADES  ·  0" + (var31 + 1) + " / 0" + KnifeFactory2.B.length,
            var12 + 20.0F,
            var13 + 22.0F,
            8.4F,
            Icons.TEXT_3,
            FontStyle.LEFT,
            FontWeight.MIDDLE,
            0.1F
         );
         var2.text(Fonts.SEMIBOLD, KnifeFactory2.B[var31], var12 + 19.0F, var13 + 48.0F, 24.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
         var2.text(Fonts.REGULAR, KnifeFactory2.C[var31], var12 + 20.0F, var13 + 72.0F, 11.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      } else {
         var2.text(
            Fonts.MEDIUM,
            "PURXIY KNIVES  ·  0" + (var19 + 1) + " / 05",
            var12 + 20.0F,
            var13 + 22.0F,
            8.4F,
            Icons.TEXT_3,
            FontStyle.LEFT,
            FontWeight.MIDDLE,
            0.1F
         );
         var2.text(Fonts.SEMIBOLD, KnifeModels.u[var19], var12 + 19.0F, var13 + 48.0F, 24.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
         var2.text(Fonts.REGULAR, sp[var19], var12 + 20.0F, var13 + 72.0F, 11.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      KnifeBuilder var45 = this.P(var3, var7, var4);
      float var46 = Math.min(var14 * 0.8F, 430.0F) * this.sQ.value();
      KnifeRenderer3.draw(
         var2, 0, var45, KnifeCache.atlas(var18 ? null : var4.camo(var19)), var16, var13 + var15 * 0.45F, var46, this.k, 0.3F, 0.1F, var14 - 8.0F, var15 - 8.0F
      );
      long var33 = 10925806723555L;
      int var36 = var33 < 0L ? -1 : 1;
      boolean var36x = this.D && var1.hover(this.z[13], var12 + 5.0F, var13 + 84.0F, var14 - 10.0F, var15 - 100.0F, TypeE.HAND);
      long var37 = 10925806723555L;
      int var39 = var37 < 0L ? -1 : 1;
      this.Z(var2, var9, var12 + var14 - 18.0F, var10, var13 + 24.0F);
      float var39x = !var36x && !this.i ? 0.75F : 1.0F;
      var2.icon("rotate-3d", var12 + 26.0F, var13 + var15 - 20.0F, 11.0F, BlendMode.withAlpha(Icons.TEXT_3, var39x));
      long var40x = 10925806723554L;
      int var38 = var40x < 0L ? -1 : 1;
      var2.text(
         Fonts.MEDIUM,
         this.i ? "Turning" : "Drag to turn",
         var12 + 38.0F,
         var13 + var15 - 20.0F,
         9.5F,
         !var36x && !this.i ? Icons.TEXT_3 : Icons.TEXT_2,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      var2.popClip();
      var2.roundRectOutlineGradient(
         var12, var13, var14, var15, 16.0F, 1.0F, 90.0F, BlendMode.withAlpha(Theme.tint(-4222985), 0.3F), BlendMode.withAlpha(Theme.tint(-4222985), 0.07F)
      );
   }

   private void Z(Canvas var1, short var2, float var3, long var4, float var6) {
      String var7 = KnifeSupport.keyName(this.b.h.key());
      float var8 = var1.textWidth(Fonts.MEDIUM, "Inspect", 9.5F);
      var1.text(Fonts.MEDIUM, "Inspect", var3 - var8, var6, 9.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
      float var9 = Math.max(18.5F, var1.textWidth(Fonts.SEMIBOLD, var7, 8.5F) + 12.0F);
      KnifeSupport.keyCap(var1, var7, var3 - var8 - 6.0F - var9, var6, 8.5F, Icons.TEXT_2);
   }

   private void z(DrawContext var1, Canvas var2, long var3) {
      long var5 = 128241653834710L;
      long var7 = 1560728121178L;
      KnifeUpdater2.ensureSourceClassInitialized();
      this.Y(var2, var7);
      this.sL = 0;
      float var9 = this.L + 288.0F;
      var2.text(Fonts.MEDIUM, "INSPECT", this.T, var9, 8.4F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE, 0.1F);
      float var10 = var2.textWidth(Fonts.MEDIUM, "INSPECT", 8.4F) + 5.8799996F;
      var2.text(
         Fonts.REGULAR,
         "press " + KnifeSupport.keyName(this.b.h.key()) + ", again to keep going",
         this.T + var10 + 10.0F,
         var9,
         8.5F,
         BlendMode.withAlpha(Icons.TEXT_3, 0.8F),
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      this.I(var1, var2, 0, this.P, var5, var9 + 12.0F, this.F);
      float var11 = var9 + 12.0F + i(this.F) + 18.0F;
      boolean var12 = this.p >= 5;
      long var13 = 37315100267034L;
      int var16 = var13 < 0L ? -1 : 1;
      String var16x = var12 ? "MOVES" : "ON THEIR OWN";
      var2.text(Fonts.MEDIUM, var16x, this.T, var11, 8.4F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE, 0.1F);
      float var17 = var2.textWidth(Fonts.MEDIUM, var16x, 8.4F) + 0.84F * var16x.length();
      long var18 = 37315100267035L;
      int var19 = var18 < 0L ? -1 : 1;
      var2.text(
         Fonts.REGULAR,
         var12 ? "as you draw it, attack and stand still" : "as you draw, walk and stand still",
         this.T + var17 + 10.0F,
         var11,
         8.5F,
         BlendMode.withAlpha(Icons.TEXT_3, 0.8F),
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      this.I(var1, var2, this.P, this.g.size(), var5, var11 + 12.0F, this.c);
   }

   public float height() {
      return this.se;
   }

   private static float i(int var0) {
      return var0 * 26.0F + (var0 - 1) * 6.0F;
   }

   private void Y(Canvas var1, long var2) {
      long var4 = 33857993229587L;
      long var6 = 116541055570391L;
      short var8 = 27134;
      short var9 = 21560;
      int var10 = (int)(var6 << 48 >>> 48);
      long var11 = 136030060397831L;
      KnifeUpdater2.ensureSourceClassInitialized();
      TypeM var13 = Translations.current();
      long var14 = 1560728121178L;
      int var24 = var14 < 0L ? -1 : 1;
      if (this.h != this.o || var13 != this.v) {
         this.o = this.h;
         this.v = var13;
         this.F = this.c = 1;

         for (int var17 = 0; var17 < KnifeModels.u.length; var17++) {
            long var18 = 1560728121177L;
            int var21 = var18 < 0L ? -1 : 1;
            this.F = Math.max(this.F, this.h(var11, var1, KnifeRenderer7.inspects(var17).stream().map(var0 -> var0.j).toList(), 2));
            this.c = Math.max(this.c, this.h(var11, var1, D(var17, (int)var8, (int)var9, var10).stream().map(var0 -> var0.j).toList(), 3));
         }

         long var24x = 1560728121178L;
         int var25 = var24x < 0L ? -1 : 1;

         for (int var20 = 0; var20 < KnifeFactory2.B.length; var20++) {
            List var21 = u(var20, var4);
            long var22 = 1560728121178L;
            int var22x = var22 < 0L ? -1 : 1;
            this.F = Math.max(
               this.F,
               this.h(
                  var11,
                  var1,
                  var21.stream().filter(var0 -> ((KnifeTransform.Data)var0).name().equals("eye")).map(v -> ((KnifeTransform.Data)v).name()).toList(),
                  2
               )
            );
            this.c = Math.max(this.c, this.h(var11, var1, var21.stream().filter(var0 -> {
               KnifeUpdater2.ensureSourceClassInitialized();
               return !((KnifeTransform.Data)var0).name().equals("eye");
            }).map(v -> ((KnifeTransform.Data)v).name()).toList(), 3));
         }

         this.r = 300.0F + i(this.F) + 18.0F + 12.0F + i(this.c) + 16.0F;
         long var25x = 1560728121178L;
         int var28 = var25x < 0L ? -1 : 1;
         this.se = this.r + 316.0F + 14.0F;
      }
   }

   private static List<KnifeRenderer7.TypeZ> D(int var0, int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(KnifeRenderer7.draw(var0));
      KnifeRenderer7.walks(var0).stream().filter(var0x -> {
         KnifeUpdater2.ensureSourceClassInitialized();
         return !var0x.G;
      }).forEach(var4::add);
      KnifeRenderer7.walks(var0).stream().filter(var0x -> var0x.G).forEach(var4::add);
      var4.addAll(KnifeRenderer7.fidgets(var0));
      return var4;
   }

   private int h(long var1, Canvas var3, List var4, int var5) {
      float var6 = 0.0F;
      KnifeUpdater2.ensureSourceClassInitialized();
      int var7 = 1;

      for (String var9 : var4) {
         float var10 = var3.textWidth(Fonts.MEDIUM, var9, 9.5F) + 34.0F;
         long var11 = 136030060397831L;
         int var13 = var11 < 0L ? -1 : 1;
         if (var6 + var10 > this.h + 0.5F) {
            if (var7 == var5) {
               break;
            }

            var7++;
            var6 = 0.0F;
         }

         var6 += var10 + 6.0F;
      }

      return var7;
   }

   private void I(DrawContext var1, Canvas var2, int var3, int var4, long var5, float var7, int var8) {
      long var9 = 28816949893792L;
      long var11 = 62157767460764L;
      KnifeUpdater2.ensureSourceClassInitialized();
      float var13 = this.T;
      float var14 = var7;
      int var15 = 1;

      for (int var16 = var3; var16 < var4 && this.sL < 32; var16++) {
         KnifeRenderer6.TypeV var17 = this.g.get(var16);
         String var18 = var17.I;
         long var19 = 128241653834709L;
         int var22 = var19 < 0L ? -1 : 1;
         boolean var22x = this.q == var16;
         long var23 = 128241653834710L;
         int var26 = var23 < 0L ? -1 : 1;
         String var26x = var22x ? "pause" : var17.n;
         float var27 = var2.textWidth(Fonts.MEDIUM, var18, 9.5F) + 34.0F;
         long var28 = 128241653834709L;
         int var28x = var28 < 0L ? -1 : 1;
         float var30x;
         int var31 = (var30x = var13 + var27 - (this.T + this.h + 0.5F)) == 0.0F ? 0 : (var30x < 0.0F ? -1 : 1);
         long var32 = 128241653834709L;
         int var32x = var32 < 0L ? -1 : 1;
         if (var31 > 0) {
            if (var15 == var8) {
               break;
            }

            var15++;
            var13 = this.T;
            var14 += 32.0F;
         }

         float var34x = this.G(var1, var11, 14 + this.sL, var13, var14, var27, 26.0F);
         long var35 = 128241653834710L;
         int var35x = var35 < 0L ? -1 : 1;
         E(var2, var13, var14, var27, var9, 26.0F, var34x, var22x);
         if (var22x) {
            float var37 = Math.clamp(this.W / var17.duration(), 0.0F, 1.0F);
            var2.roundRect(var13 + 8.0F, var14 + 26.0F - 3.5F, (var27 - 16.0F) * var37, 1.6F, 0.8F, BlendMode.withAlpha(Theme.tint(-4222985), 0.85F));
         }

         var2.icon(var26x, var13 + 15.0F, var14 + 13.0F, 10.0F, var22x ? Icons.TEXT : Theme.tint(-4222985));
         float var43x = var13 + 26.0F;
         float var38 = var14 + 13.0F;
         long var39 = 128241653834710L;
         int var39x = var39 < 0L ? -1 : 1;
         var2.text(
            Fonts.MEDIUM, var18, var43x, var38, 9.5F, var22x ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var34x), FontStyle.LEFT, FontWeight.MIDDLE
         );
         this.sb[this.sL] = var13;
         this.l[this.sL] = var14;
         this.X[this.sL] = var27;
         this.K[this.sL] = var16;
         this.sL++;
         var13 += var27 + 6.0F;
      }

      long var42 = 128241653834709L;
      int var41 = var42 < 0L ? -1 : 1;
   }

   private void T(DrawContext var1, Canvas var2, int var3, long var4) {
      long var6 = 47920144134851L;
      long var8 = 13407349767629L;
      long var10 = 180346639L;
      char var12 = '늙';
      long var13 = 62157767460764L;
      KnifeUpdater2.ensureSourceClassInitialized();
      float var15 = (this.h - 32.0F) / 5.0F;

      for (int var16 = 0; var16 < KnifeModels.u.length; var16++) {
         float var17 = this.T + var16 * (var15 + 8.0F);
         float var18 = this.L + this.r;
         long var19 = 23727099991263L;
         int var22 = var19 < 0L ? -1 : 1;
         boolean var22x = var3 == var16;
         float var23 = this.G(var1, var13, var16, var17, var18, var15, 92.0F);
         m(var2, var17, var18, var15, 92.0F, var10, (short)var12, 12.0F, var22x, var23);
         var2.pushClip(var17 + 1.0F, var18 + 1.0F, var15 - 2.0F, 90.0F, 11.0F);
         var2.glowCircle(
            var17 + var15 / 2.0F, var18 + 32.0F, var15 * 0.3F, 40.0F, BlendMode.withAlpha(Theme.tint(-4222985), var22x ? 0.1F : 0.04F + var23 * 0.04F)
         );
         KnifeBuilder var24 = this.n(var8, var16);
         KnifeRenderer3.draw(
            var2,
            1 + var16,
            var24,
            KnifeCache.atlas(KnifeCamos.find(var16, (String)this.b.camoSetting(var16).get())),
            var17 + var15 / 2.0F,
            var18 + 31.0F - var23 * 1.5F,
            Math.min(var15 * 1.02F, 104.0F),
            -0.42F + var23 * 0.25F,
            0.22F,
            0.1F,
            var15 - 4.0F,
            60.0F
         );
         var2.popClip();
         String var25 = KnifeModels.u[var16];
         float var26 = var17 + 11.0F;
         float var27 = var18 + 92.0F - 26.0F;
         float var28 = var15 - 18.0F;
         long var29 = 23727099991263L;
         int var32 = var29 < 0L ? -1 : 1;
         var2.textEllipsized(
            Fonts.SEMIBOLD,
            var25,
            var26,
            var27,
            11.0F,
            var28,
            var22x ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var23),
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
         var2.textEllipsized(
            Fonts.MEDIUM,
            A[var16],
            var17 + 11.0F,
            var18 + 92.0F - 12.0F,
            7.2F,
            var15 - 18.0F,
            var22x ? BlendMode.lerp(Icons.TEXT_3, Theme.tint(-4222985), 0.6F) : Icons.TEXT_3,
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
         long var32x = 23727099991262L;
         int var32x = var32x < 0L ? -1 : 1;
         if (var22x) {
            H(var2, var17 + var15 - 14.0F, var18 + 14.0F, var6);
         }
      }
   }

   private void b(DrawContext var1, Canvas var2, int var3, long var4) {
      long var6 = 47920144134851L;
      long var8 = 96908250082858L;
      long var10 = 180346639L;
      char var12 = '늙';
      long var13 = 62157767460764L;
      float var15 = this.L + this.r + 92.0F + 18.0F;
      var2.text(Fonts.MEDIUM, "TWO-HANDED", this.T, var15, 8.4F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE, 0.1F);
      KnifeUpdater2.ensureSourceClassInitialized();
      float var16 = var2.textWidth(Fonts.MEDIUM, "TWO-HANDED", 8.4F) + 8.4F;
      var2.text(
         Fonts.REGULAR,
         "held in both hands, with slash arcs and moves of its own",
         this.T + var16 + 10.0F,
         var15,
         8.5F,
         BlendMode.withAlpha(Icons.TEXT_3, 0.8F),
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      int var17 = KnifeFactory2.B.length;
      float var18 = (this.h - (var17 - 1) * 8) / var17;
      float var19 = this.L + this.r + 92.0F + 22.0F + 12.0F - 4.0F;

      for (int var20 = 0; var20 < var17; var20++) {
         float var21 = this.T + var20 * (var18 + 8.0F);
         long var22 = 65782903584268L;
         int var25 = var22 < 0L ? -1 : 1;
         boolean var25x = var3 == 5 + var20;
         float var26 = this.G(var1, var13, 46 + var20, var21, var19, var18, 92.0F);
         m(var2, var21, var19, var18, 92.0F, var10, (short)var12, 12.0F, var25x, var26);
         var2.pushClip(var21 + 1.0F, var19 + 1.0F, var18 - 2.0F, 90.0F, 11.0F);
         var2.glowCircle(
            var21 + var18 / 2.0F, var19 + 32.0F, var18 * 0.3F, 40.0F, BlendMode.withAlpha(Theme.tint(-4222985), var25x ? 0.1F : 0.04F + var26 * 0.04F)
         );
         KnifeRenderer3.draw(
            var2,
            6 + var20,
            this.C(var20, var8),
            KnifeCache.atlas(null),
            var21 + var18 / 2.0F,
            var19 + 31.0F - var26 * 1.5F,
            Math.min(var18 * 1.02F, 150.0F),
            -0.42F + var26 * 0.25F,
            0.22F,
            0.1F,
            var18 - 4.0F,
            60.0F
         );
         var2.popClip();
         String var27 = KnifeFactory2.B[var20];
         float var28 = var21 + 11.0F;
         float var29 = var19 + 92.0F - 26.0F;
         float var30 = var18 - 18.0F;
         long var31 = 65782903584269L;
         int var34 = var31 < 0L ? -1 : 1;
         var2.textEllipsized(
            Fonts.SEMIBOLD,
            var27,
            var28,
            var29,
            11.0F,
            var30,
            var25x ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var26),
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
         var2.textEllipsized(
            Fonts.MEDIUM,
            KnifeFactory2.k[var20],
            var21 + 11.0F,
            var19 + 92.0F - 12.0F,
            7.2F,
            var18 - 18.0F,
            var25x ? BlendMode.lerp(Icons.TEXT_3, Theme.tint(-4222985), 0.6F) : Icons.TEXT_3,
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
         long var34x = 65782903584269L;
         int var34x = var34x < 0L ? -1 : 1;
         if (var25x) {
            H(var2, var21 + var18 - 14.0F, var19 + 14.0F, var6);
         }
      }
   }

   private KnifeBuilder n(long var1, int var3) {
      String var4 = (String)this.b.F.get();
      KnifeUpdater2.ensureSourceClassInitialized();
      String var5 = (String)this.b.camoSetting(var3).get();
      String var6 = var4 + ":" + var5;
      long var7 = 13407349767628L;
      int var9 = var7 < 0L ? -1 : 1;
      if (this.E[var3] == null || !var6.equals(this.y[var3])) {
         this.E[var3] = KnifeModels.get(var3, var4, var5).posed(a, 0.0F, 0.0F);
         this.y[var3] = var6;
      }

      return this.E[var3];
   }

   private KnifeBuilder C(int var1, long var2) {
      long var4 = 116829598997744L;
      KnifeUpdater2.ensureSourceClassInitialized();
      String var6 = (String)this.b.F.get();
      if (this.sV[var1] == null || !var6.equals(this.u[var1])) {
         this.sV[var1] = KnifeFactory2.get(var1, var6).posed(new Matrix4f().scale(var1 == 2 ? 0.72F : 1.0F).mul(W(var1, var4)), 0.0F, 0.0F);
         this.u[var1] = var6;
      }

      return this.sV[var1];
   }

   private static int D(int var0, char var1, short var2, int var3) {
      KnifeUpdater2.ensureSourceClassInitialized();
      return var0 >= 5 ? m.length : m.length + 4;
   }

   private void k(DrawContext var1, long var2, Canvas var4, int var5) {
      byte var6;
      short var7;
      int var8;
      long var9;
      long var11;
      int var13;
      int var14;
      int var15;
      float var16;
      String var18;
      float var19;
      int var20;
      float var17;
      label162: {
         long var21 = 34975824147587L;
         var6 = 0;
         var7 = 8143;
         var8 = (int)(var21 << 32 >>> 32);
         var9 = 23302289223980L;
         var11 = 62157767460764L;
         KnifeUpdater2.ensureSourceClassInitialized();
         int var23 = var5 >= 5 ? 1 : 0;
         var13 = var23 != 0 ? 0 : var5;
         KnifeCamos.Camo var24 = var23 != 0 ? null : this.b.camo();
         var14 = var24 == null ? List.of(m).indexOf(this.b.F.get()) : m.length + KnifeCamos.forModel(var13).indexOf(var24);
         var15 = this.sG >= 0 ? this.sG : var14;
         var16 = this.L + this.r;
         var4.text(
            Fonts.MEDIUM,
            var23 != 0 ? "Blade finish" : "Finish  ·  " + KnifeCamos.game(var13) + " camos",
            this.T,
            var16 + 240.0F,
            10.5F,
            Icons.TEXT_2,
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
         long var25 = 51981034658643L;
         int var17x = var25 < 0L ? -1 : 1;
         var18 = var15 >= 0 ? this.O(var13, var9, var15) : "Custom";
         var17 = this.T + this.h;
         var19 = var16 + 240.0F;
         if (this.sG >= 0) {
            long var28 = 51981034658643L;
            int var45 = var28 < 0L ? -1 : 1;
            if (this.sG != var14) {
               var20 = Icons.TEXT;
               break label162;
            }
         }

         var20 = Theme.tint(-4222985);
      }

      var4.text(Fonts.MEDIUM, var18, var17, var19, 10.5F, var20, FontStyle.RIGHT, FontWeight.MIDDLE);
      int var42 = D(var5, (char)var6, var7, var8);
      float var22 = this.h / var42;
      int var43 = -1;

      for (int var44 = 0; var44 < var42; var44++) {
         float var46 = this.T + var22 * (var44 + 0.5F);
         long var26 = 51981034658643L;
         int var47 = var26 < 0L ? -1 : 1;
         float var29 = var16 + 256.0F + 15.0F;
         float var30 = this.G(var1, var11, 5 + var44, var46 - var22 / 2.0F + 2.0F, var29 - 15.0F - 4.0F, var22 - 4.0F, 54.0F);
         if (this.D && var1.hoverTarget() == this.z[5 + var44]) {
            var43 = var44;
         }

         float var31;
         label169: {
            var31 = 15.0F * (1.0F + 0.06F * var30);
            long var32 = 51981034658644L;
            int var51 = var32 < 0L ? -1 : 1;
            if (var44 != var14) {
               float var35;
               int var36 = (var35 = var30 - 0.01F) == 0.0F ? 0 : (var35 < 0.0F ? -1 : 1);
               long var37 = 51981034658644L;
               int var42x = var37 < 0L ? -1 : 1;
               if (var36 <= 0) {
                  break label169;
               }
            }

            long var54 = 51981034658643L;
            int var65 = var54 < 0L ? -1 : 1;
            var4.ring(
               var46, var29, var31 + 4.5F, var44 == var14 ? 1.6F : 1.2F, BlendMode.withAlpha(Theme.tint(-4222985), var44 == var14 ? 0.9F : 0.45F * var30)
            );
         }

         var4.shadow(var46 - var31, var29 - var31, var31 * 2.0F, var31 * 2.0F, var31, 10.0F, 0.0F, 0.0F, 3.0F, 1879048192);
         if (var44 < m.length) {
            int[] var49 = B[var44];
            long var33x = 51981034658643L;
            int var41 = var33x < 0L ? -1 : 1;
            var4.roundRectGradient(var46 - var31, var29 - var31, var31 * 2.0F, var31 * 2.0F, var31, 120.0F, var49[1], BlendMode.lerp(var49[0], -16777216, 0.3F));
         } else {
            KnifeCamos.Camo var50 = KnifeCamos.forModel(var13).get(var44 - m.length);
            GpuTextureView var52 = class_310.method_1551().method_1531().method_4619(KnifeCache.swatch(var50)).method_71659();
            var4.image(
               var52,
               RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR),
               false,
               var46 - var31,
               var29 - var31,
               var31 * 2.0F,
               var31 * 2.0F,
               0.3125F,
               0.0F,
               0.6875F,
               1.0F,
               var31,
               -1
            );
         }

         var4.softCircle(var46 - var31 * 0.32F, var29 - var31 * 0.42F, var31 * 0.28F, var31 * 0.5F, BlendMode.withAlpha(-1, 0.3F));
         var4.ring(var46, var29, var31 - 0.5F, 1.0F, BlendMode.withAlpha(-1, 0.12F));
         TrueTypeFont var51 = Fonts.MEDIUM;
         var18 = this.O(var13, var9, var44);
         var19 = var29 + 15.0F + 15.0F;
         float var53 = var22 - 6.0F;
         long var34 = 51981034658644L;
         int var67 = var34 < 0L ? -1 : 1;
         var4.textEllipsized(
            var51,
            var18,
            var46,
            var19,
            9.0F,
            var53,
            var44 == var14 ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_3, Icons.TEXT_2, var30),
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
      }

      long var45 = 51981034658643L;
      int var50 = var45 < 0L ? -1 : 1;
      this.sG = var43;
      long var27 = 51981034658643L;
      int var531 = var27 < 0L ? -1 : 1;
      String var47x;
      if (var15 < 0) {
         var47x = "";
      } else {
         long var48 = 51981034658643L;
         int var56 = var48 < 0L ? -1 : 1;
         var47x = var15 < m.length ? C[var15] : KnifeCamos.forModel(var13).get(var15 - m.length).detail();
      }

      var4.textEllipsized(Fonts.REGULAR, var47x, this.T, var16 + 316.0F, 8.5F, this.h, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   private String O(int var1, long var2, int var4) {
      KnifeUpdater2.ensureSourceClassInitialized();
      long var5 = 23302289223979L;
      int var7 = var5 < 0L ? -1 : 1;
      return var4 < m.length ? s4[var4] : KnifeCamos.forModel(var1).get(var4 - m.length).name();
   }

   private float G(DrawContext var1, long var2, int var4, float var5, float var6, float var7, float var8) {
      KnifeUpdater2.ensureSourceClassInitialized();
      long var9 = 62157767460763L;
      int var12 = var9 < 0L ? -1 : 1;
      boolean var12x = this.D && var1.hover(this.z[var4], var5, var6, var7, var8, TypeE.HAND);
      return this.z[var4].target(var12x ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
   }

   private static void m(Canvas var0, float var1, float var2, float var3, float var4, long var5, short var7, float var8, boolean var9, float var10) {
      var0.roundRectGradient(
         var1,
         var2,
         var3,
         92.0F,
         12.0F,
         90.0F,
         BlendMode.lerp(Theme.tint(-15330778), Theme.tint(-4222985), (var9 ? 0.1F : 0.02F) + var10 * 0.04F),
         BlendMode.lerp(Theme.tint(-15922411), Theme.tint(-4222985), (var9 ? 0.06F : 0.01F) + var10 * 0.02F)
      );
      var0.roundRectOutline(var1, var2, var3, 92.0F, 12.0F, var9 ? 1.3F : 1.0F, BlendMode.withAlpha(Theme.tint(-4222985), var9 ? 0.7F : 0.12F + var10 * 0.2F));
   }

   private static void H(Canvas var0, float var1, float var2, long var3) {
      var0.circle(var1, var2, 6.0F, Theme.tint(-4222985));
      var0.icon("check", var1, var2, 8.5F, Icons.BG_1);
   }

   private static void E(Canvas var0, float var1, float var2, float var3, long var4, float var6, float var7, boolean var8) {
      KnifeUpdater2.ensureSourceClassInitialized();
      long var9 = 28816949893791L;
      int var12 = var9 < 0L ? -1 : 1;
      var0.roundRect(var1, var2, var3, 26.0F, 8.0F, var8 ? BlendMode.withAlpha(Icons.VIOLET, 0.26F) : BlendMode.lerp(385875967, 587202559, var7));
      long var12x = 28816949893791L;
      int var13 = var12x < 0L ? -1 : 1;
      var0.roundRectOutline(
         var1, var2, var3, 26.0F, 8.0F, 1.0F, var8 ? BlendMode.withAlpha(Icons.MAGENTA, 0.45F + 0.15F * var7) : BlendMode.lerp(486539263, 956301311, var7)
      );
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      long var4 = 83729766451131L;
      long var6 = var4 ^ 79990609687701L;
      long var8 = var4 ^ 92263068627768L;
      int var10 = (int)((var4 ^ 92263068627768L) >>> 48);
      int var11 = (int)((var4 ^ 92263068627768L) << 16 >>> 48);
      int var12 = (int)(var8 << 32 >>> 32);
      if (this.D && var3 == 0 && !(var1 < this.T) && !(var1 >= this.T + this.h)) {
         float var13 = var1 - this.T;
         float var14 = var2 - this.L;

         for (int var15 = 0; var15 < this.sL; var15++) {
            if (var1 >= this.sb[var15] && var1 < this.sb[var15] + this.X[var15] && var2 >= this.l[var15] && var2 < this.l[var15] + 26.0F) {
               int var16 = this.K[var15];
               if (this.q == var16) {
                  this.q = -1;
               } else {
                  this.y(var16);
               }

               UiAudioPolicy.play("CLICK");
               return true;
            }
         }

         if (var14 >= this.r && var14 < this.r + 92.0F) {
            float var19 = (this.h - 32.0F) / 5.0F;
            int var21 = (int)(var13 / (var19 + 8.0F));
            if (var21 < 5 && var13 - var21 * (var19 + 8.0F) < var19) {
               if (!this.b.U.is(KnifeModels.u[var21]) || this.b.great()) {
                  this.b.U.set(KnifeModels.u[var21]);
                  this.b.z.set("Knife");
                  this.Y = 3.0F;
               }

               UiAudioPolicy.play("SELECT");
               return true;
            }
         }

         float var20 = this.r + 92.0F + 22.0F + 12.0F - 4.0F;
         if (var14 >= var20 && var14 < var20 + 92.0F) {
            int var22 = KnifeFactory2.B.length;
            float var17 = (this.h - (var22 - 1) * 8) / var22;
            int var18 = (int)(var13 / (var17 + 8.0F));
            if (var18 < var22 && var13 - var18 * (var17 + 8.0F) < var17) {
               if (!this.b.z.is(KnifeFactory2.B[var18])) {
                  this.b.z.set(KnifeFactory2.B[var18]);
                  this.Y = 3.0F;
               }

               UiAudioPolicy.play("SELECT");
               return true;
            }
         }

         float var23 = this.r + 256.0F;
         if (var14 >= var23 - 4.0F && var14 < var23 + 30.0F + 22.0F) {
            int var24 = D(this.W(var6), (char)var10, (short)var11, var12);
            int var25 = (int)(var13 / (this.h / var24));
            if (var25 >= 0 && var25 < m.length) {
               this.b.F.set(m[var25]);
               this.b.camoSetting().set("Original");
               UiAudioPolicy.play("CLICK");
               return true;
            }

            if (var25 >= m.length && var25 < var24) {
               this.b.camoSetting().select(var25 - m.length + 1);
               UiAudioPolicy.play("CLICK");
               return true;
            }
         }

         if (var14 >= 84.0F && var14 < 266.0F) {
            this.i = true;
            this.M = Float.NaN;
            this.n = var1;
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void mouseDrag(float var1, float var2) {
      if (this.i) {
         this.k = this.k + (var1 - this.n) * 0.012F;
         this.n = var1;
         this.Y = 6.0F;
      }
   }

   public void mouseUp() {
      this.i = false;
   }

   private record TypeN(String E, String z) {
      KnifeModels.Model geometry(int var1) {
         return KnifeModels.get(var1, this.E, this.z);
      }

      KnifeCamos.Camo camo(int var1) {
         return KnifeCamos.find(var1, this.z);
      }

      public String finish() {
         return this.E;
      }

      public String camo() {
         return this.z;
      }

      public String stringState33() {
         return this.E;
      }

      public String stringState50() {
         return this.z;
      }
   }

   private record TypeV(String I, String n, KnifeRenderer7.TypeZ a, KnifeTransform.TypeU z) {
      float duration() {
         return this.a != null ? this.a.duration() : (this.z != null ? this.z.E : 0.0F);
      }

      public String label() {
         return this.I;
      }

      public String icon() {
         return this.n;
      }

      public KnifeRenderer7.TypeZ trick() {
         return this.a;
      }

      public KnifeTransform.TypeU clip() {
         return this.z;
      }

      public String stringState7() {
         return this.I;
      }

      public String stringState32() {
         return this.n;
      }

      public KnifeRenderer7.TypeZ knifeAnimationLibrary_KnifeTrickAnimationState1() {
         return this.a;
      }

      public KnifeTransform.TypeU knifeTargeting_KnifeMotionClipState3() {
         return this.z;
      }
   }
}
