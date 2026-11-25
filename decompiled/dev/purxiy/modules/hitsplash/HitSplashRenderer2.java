package dev.purxiy.modules.hitsplash;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.Setting;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.DrawContext.TypeE;
import dev.purxiy.ui.clickgui.Studio;
import dev.purxiy.ui.clickgui.Studio.TypeQ;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.class_310;
import net.minecraft.class_746;

public final class HitSplashRenderer2 implements Studio {
   private static final float g = 34.0F;
   private static final float f = 24.0F;
   private static final long t = 320L;
   private static final float M = 0.9F;
   private static final float d = 0.25F;
   private static final float z = 0.55F;
   private static final Map<ModuleDescriptor, HitSplashRenderer2> m = new IdentityHashMap<>();
   private static HitSplashRenderer2 B;
   private static final List<HitSplashRenderer2.TypeX> U = new ArrayList<>();
   private static double I;
   private static long Z = 0L;
   private final ModuleDescriptor u;
   private final HitSplashFactory i;
   private boolean s;
   private boolean l;
   private boolean A;
   private boolean D;
   private double p = -1.0;
   private int X;
   private double q = -1.0;
   private List<HitSplashFactory.Data> Q = List.of();
   private double y = -1.0;
   private float C = 1.0F;
   private int e;
   private final double[] Y = new double[16];
   private boolean R;
   private final HitSplashRenderer2.TypeG a;
   private final HitSplashRenderer2.TypeG o;
   private final HitSplashRenderer2.TypeG c;
   private final HitSplashRenderer2.TypeG H;
   private final List<HitSplashRenderer2.TypeG> E = new ArrayList<>();
   private final float[] O = new float[4];
   private final float[] b = new float[4];
   private boolean h;
   private final Animator F = Animator.smooth(0.0F);
   private final Animator N = new Animator(0.0F, 420.0F, 41.0F);
   private final Animator w = Animator.smooth(0.0F);
   private final Animator T = Animator.smooth(0.0F);
   private float L;
   private float n;
   private float W;
   private boolean v;
   private float J;
   private float j;
   private long k;
   private boolean G;
   private final Object S = new Object();
   public static int x = 0;
   private static final String[] K = new String[27];
   private static final Integer[] V = new Integer[18];

   private HitSplashRenderer2(long var1, ModuleDescriptor var3, HitSplashFactory var4) {
      this.u = var3;
      this.i = var4;
      this.a = new HitSplashRenderer2.TypeG("Play", "play", () -> this.V(20730927235736L));
      this.o = new HitSplashRenderer2.TypeG("Loop", "repeat", () -> {
         long var1x = 20730927235736L;
         this.R = !this.R;
         long var3x = 2626183414249L;
         int var6x = var3x < 0L ? -1 : 1;
         UiAudioPolicy.play(this.R ? "TOGGLE_ON" : "TOGGLE_OFF");
         long var6xx = 2626183414248L;
         int var7x = var6xx < 0L ? -1 : 1;
         if (this.R && this.y < 0.0) {
            this.V(var1x);
         }
      });

      for (HitSplashFactory.Data2 var6 : var4.a) {
         long var7 = 344055033112L;
         int var9 = var7 < 0L ? -1 : 1;
         this.E.add(new HitSplashRenderer2.TypeG(var6.label(), var6.icon(), () -> {
            long var2 = 109788550974474L;
            this.B(var6, var2);
         }));
      }

      this.c = new HitSplashRenderer2.TypeG("", "rotate-ccw", () -> {
         HitSplashSupport.resetView();
         UiAudioPolicy.play("TAB", 1.1F);
      });
      long var10 = 344055033112L;
      int var11 = var10 < 0L ? -1 : 1;
      this.H = new HitSplashRenderer2.TypeG("Done", "check", () -> {
         this.A = true;
         UiAudioPolicy.play("CLICK");
      });
   }

   public static HitSplashRenderer2 of(ModuleDescriptor var0) {
      long var1 = 95207692727627L;
      long var3 = var1 ^ 95414308264018L;
      HitSplashRenderer2 var5 = m.get(var0);
      if (var5 == null) {
         HitSplashFactory var6 = HitSplashFactory.of(var0);
         if (var6 == null) {
            return null;
         }

         var5 = new HitSplashRenderer2(var3, var0, var6);
         m.put(var0, var5);
      }

      return var5;
   }

   static boolean showing(ModuleDescriptor var0) {
      HitSplashRenderer2 var1 = B;
      return var1 != null && var1.u == var0 && var1.l;
   }

   static void later(float var0, Consumer<class_746> var1) {
      U.add(new HitSplashRenderer2.TypeX(I + var0, var1));
   }

   public String title() {
      return "Live preview";
   }

   public String subtitle() {
      return "Step out of yourself and watch it play on you";
   }

   public String icon() {
      return "scan-eye";
   }

   public String action() {
      return "Preview";
   }

   public boolean available() {
      class_310 var1 = class_310.method_1551();
      return var1.field_1687 != null
         && var1.field_1724 != null
         && var1.field_1724.method_5805()
         && !var1.field_1724.method_7325()
         && (B == null || B == this || !B.l);
   }

   public TypeQ dock() {
      return TypeQ.RIGHT;
   }

   public boolean keepsWorldRunning() {
      return true;
   }

   public void setOpen(boolean var1) {
      long var2 = 12147428020248L;
      long var4 = var2 ^ 20293980449219L;
      long var6 = var2 ^ 11948561293476L;
      class_310 var8 = class_310.method_1551();
      if (var1 != this.s) {
         this.s = var1;
         this.v = false;
         if (var1) {
            if (B != null && B != this) {
               B.w(var4, true);
            }

            B = this;
            if (!this.l) {
               this.l = true;
            }

            HitSplashSupport.open();
            this.D = false;
            this.p = -1.0;
            this.X = this.x(var6);
            this.q = -1.0;
            this.G = false;
            this.F.snap(0.0F);
            this.w.snap(0.0F);
            this.M();
         } else {
            this.M();
            U.clear();
            this.R = false;
            HitSplashSupport.close(CosmeticRegistry.currentOwner() == null);
            if (var8.field_1724 != null) {
               this.i.closed(var8.field_1724);
            }
         }
      }
   }

   public boolean consumeCloseRequest() {
      boolean var1 = this.A;
      this.A = false;
      return var1;
   }

   private void w(long var1, boolean var3) {
      if (this.s) {
         long var4 = 28008628828634L;
         int var7 = var4 < 0L ? -1 : 1;
         if (!var3) {
            return;
         }
      }

      if (var3) {
         long var8 = 28008628828635L;
         int var8x = var8 < 0L ? -1 : 1;
         if (this.s) {
            this.s = false;
            this.M();
            class_310 var7 = class_310.method_1551();
            if (var7.field_1724 != null) {
               this.i.closed(var7.field_1724);
            }

            HitSplashSupport.reset();
         }
      }

      if (this.l) {
         this.l = false;
      }

      if (B == this) {
         B = null;
      }
   }

   static void tick(class_310 var0) {
      long var1 = 24001738417774L;
      long var3 = var1 ^ 13937492402101L;
      HitSplashRenderer2 var5 = B;
      if (var5 != null) {
         if (var0.field_1687 != null && var0.field_1724 != null) {
            if (var5.s && CosmeticRegistry.currentOwner() == null) {
               var5.setOpen(false);
            }

            if (!var5.s && HitSplashSupport.active() && CosmeticRegistry.currentOwner() == null) {
               HitSplashSupport.hurry();
            }

            if (!var5.s && !HitSplashSupport.active()) {
               var5.w(var3, false);
            }
         } else {
            var5.w(var3, true);
         }
      }
   }

   private void V(long var1) {
      class_310 var3 = class_310.method_1551();
      if (var3.field_1724 != null) {
         long var4 = 20730927235736L;
         int var7 = var4 < 0L ? -1 : 1;
         String var7x = this.i.note();
         long var8 = 20730927235736L;
         int var9 = var8 < 0L ? -1 : 1;
         if (var7x == null) {
            this.Q = this.i.timeline();
            this.C = Math.max(0.5F, this.i.length());
            this.y = I;
            this.e = 0;
            UiAudioPolicy.play("SELECT", 1.05F, 0.7F);
            return;
         }
      }

      UiAudioPolicy.play("DENY");
   }

   private void B(HitSplashFactory.Data2 var1, long var2) {
      class_310 var4 = class_310.method_1551();
      if (var4.field_1724 != null && this.i.note() == null) {
         var1.action().accept(var4.field_1724);
      } else {
         UiAudioPolicy.play("DENY");
      }
   }

   private int x(long var1) {
      int var3 = 1;

      for (Setting var5 : this.u.settings()) {
         long var6 = 2000228036795L;
         int var10 = var6 < 0L ? -1 : 1;

         try {
            var3 = var3 * 31 + var5.save().hashCode();
         } catch (RuntimeException var9) {
            var3 *= 31;
         }
      }

      return var3;
   }

   private void M() {
      this.y = -1.0;
      this.e = 0;
      this.Q = List.of();
   }

   private void L(long var1) {
      long var3 = 20730927235736L;
      long var5 = 2000228036796L;
      long var7 = System.nanoTime();
      float var9 = Z == 0L ? 0.0F : Math.min(0.1F, (float)(var7 - Z) / 1.0E9F);
      Z = var7;
      I += var9;
      class_746 var10 = class_310.method_1551().field_1724;
      if (var10 != null) {
         long var11 = 140461280802571L;
         int var27 = var11 < 0L ? -1 : 1;
         if (this.y >= 0.0) {
            for (var14 = I - this.y; this.e < this.Q.size() && this.Q.get(this.e).at() <= var14; this.Q.get(this.e++).action().accept(var10)) {
               if (this.e < this.Y.length) {
                  this.Y[this.e] = I;
               }
            }

            long var16 = 140461280802572L;
            int var31 = var16 < 0L ? -1 : 1;
            double var19;
            int var18 = (var19 = var14 - (this.C + (this.R ? 0.9F : 0.0F))) == 0.0 ? 0 : (var19 < 0.0 ? -1 : 1);
            long var21 = 140461280802572L;
            int var24 = var21 < 0L ? -1 : 1;
            if (var18 >= 0) {
               if (this.R && this.s) {
                  this.V(var3);
               } else if (var14 >= this.C + 1.2) {
                  this.M();
               }
            }
         }

         Iterator var27x = U.iterator();

         while (var27x.hasNext()) {
            HitSplashRenderer2.TypeX var15 = (HitSplashRenderer2.TypeX)var27x.next();
            long var29 = 140461280802571L;
            int var32 = var29 < 0L ? -1 : 1;
            if (var15.m <= I) {
               var27x.remove();
               var15.c.accept(var10);
            }
         }

         long var28 = 140461280802571L;
         int var30 = var28 < 0L ? -1 : 1;
         var30 = this.x(var5);
         long var31 = 140461280802571L;
         int var34 = var31 < 0L ? -1 : 1;
         if (var30 != this.X) {
            this.X = var30;
            this.q = I;
         }

         if (this.q >= 0.0 && I - this.q >= 0.55F) {
            this.q = -1.0;
            boolean var32 = this.y >= 0.0 && I - this.y < this.C;
            if (this.s) {
               long var22 = 140461280802572L;
               int var39 = var22 < 0L ? -1 : 1;
               if (this.D) {
                  long var24 = 140461280802572L;
                  int var24x = var24 < 0L ? -1 : 1;
                  if (!var32 && this.i.note() == null) {
                     this.V(var3);
                  }
               }
            }
         }

         long var33 = 140461280802572L;
         int var23 = var33 < 0L ? -1 : 1;
         if (this.s && HitSplashSupport.settled()) {
            if (this.p < 0.0) {
               this.p = I;
            }

            if (!this.D) {
               long var23x = 140461280802571L;
               int var23x = var23x < 0L ? -1 : 1;
               if (I - this.p >= 0.25) {
                  long var25x = 140461280802571L;
                  int var25x = var25x < 0L ? -1 : 1;
                  this.D = true;
                  if (this.y < 0.0 && this.i.note() == null) {
                     this.V(var3);
                  }
               }
            }
         }
      }
   }

   public void render(DrawContext var1, float var2, float var3) {
      long var4 = 5821624030603L;
      long var6 = var4 ^ 113688548108878L;
      long var8 = var4 ^ 68406817359548L;
      long var10 = var4 ^ 84982109415063L;
      long var12 = var4 ^ 135191669121671L;
      long var14 = var4 ^ 127173707766884L;
      this.L(var12);
      Canvas var16 = var1.canvas;
      this.L = var3;
      this.n = var16.width();
      this.W = var16.height();
      float var17 = 16.0F;
      float var18 = Math.max(var17 + 200.0F, var3 - 16.0F);
      float var19 = (var17 + var18) / 2.0F;
      float var20 = 110.0F;
      HitSplashSupport.frame(var19 / this.n * 2.0F - 1.0F, 1.0F - (this.W - var20) / this.W);
      float var21 = Easing.SMOOTHSTEP.apply(Z((HitSplashSupport.progress() - 0.62F) / 0.38F, var6));
      float var22 = this.F.target(this.s ? var21 : 0.0F).update(MotionStyle.MENU.dt(var1.dt)) * var2;
      if (this.v) {
         var1.claim(this.S, TypeE.MOVE);
      }

      if (var22 <= 0.01F) {
         this.O[2] = 0.0F;
      } else {
         var16.pushAlpha(var22);
         float var23 = (1.0F - var22) * 18.0F;
         this.B(var1, var16, var17, var14, var18, var23);
         this.Q(var1, var16, var23, var8);
         this.s(var10, var1, var16, var17, var22);
         var16.popAlpha();
      }
   }

   private void B(DrawContext var1, Canvas var2, float var3, long var4, float var6, float var7) {
      long var10 = 13971166246986L;
      byte var12 = 0;
      short var13 = 3252;
      int var14 = (int)(var10 << 32 >>> 32);
      long var15 = 107946995580869L;
      long var17 = 56123207400284L;
      short var19 = 23495;
      int var20 = -138027045;
      long var21 = 112874442161250L;
      String var23 = "Live preview";
      float var24 = var2.textWidth(Fonts.SEMIBOLD, var23, 9.5F);
      List var25 = this.B();
      this.h = false;
      float var26 = this.s(var2, var25, var21);
      boolean var27 = 32.0F + var24 + var26 + 10.0F > var6 - 16.0F;
      if (var27 && 26.0F + var26 + 10.0F > var6 - 16.0F) {
         this.h = true;
         var26 = this.s(var2, var25, var21);
      }

      float var28 = var27 ? 26.0F : 32.0F + var24;
      float var29 = var28 + var26 + 10.0F;
      float var30 = Math.max(16.0F, (16.0F + var6) / 2.0F - var29 / 2.0F);
      float var31 = this.W - 22.0F - 34.0F + var7;
      this.O[0] = var30;
      this.O[1] = var31 - 24.0F - 8.0F;
      this.O[2] = var29;
      this.O[3] = 66.0F;
      var2.shadow(var30, var31, var29, 34.0F, 17.0F, 22.0F, 0.0F, 0.0F, 6.0F, 1879048192);
      var2.roundRect(var30, var31, var29, 34.0F, 17.0F, Theme.tint(-300872416));
      var2.roundRectOutlineGradient(var30, var31, var29, 34.0F, 17.0F, 1.0F, 90.0F, 788529151, 184549375);
      float var32 = var31 + 17.0F;
      float var33 = MotionStyle.reduced() ? 0.7F : 0.55F + 0.45F * (float)Math.sin(var1.time * 3.2F);
      long var34 = 130715659799022L;
      int var34x = var34 < 0L ? -1 : 1;
      var2.glowCircle(var30 + 12.0F + 6.0F, var32, 3.0F, 7.0F, BlendMode.withAlpha(Icons.PINK, 0.45F * var33));
      var2.circle(var30 + 12.0F + 6.0F, var32, 3.0F, BlendMode.lerp(Icons.MAGENTA, Icons.PINK, var33));
      if (!var27) {
         var2.text(Fonts.SEMIBOLD, var23, var30 + 12.0F + 14.0F + 6.0F, var32, 9.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      float var37x = var30 + var28;
      boolean var38 = this.y >= 0.0 && I - this.y < this.C;
      float var39 = this.T.target(var38 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));

      for (HitSplashRenderer2.TypeG var41 : var25) {
         float var42 = this.z(var41, var19, var20) ? 14.0F : 5.0F;
         long var43 = 130715659799022L;
         int var42x = var43 < 0L ? -1 : 1;
         if (this.z(var41, var19, var20)) {
            var2.rect(var37x + var42 / 2.0F - 0.5F, var31 + 9.0F, 1.0F, 16.0F, 486539263);
         }

         float var47;
         float var48;
         boolean var49;
         boolean var9;
         label219: {
            var63 = var37x + var42;
            var46 = this.y(var12, (char)var13, var2, var14, var41);
            var47 = 24.0F;
            var48 = var31 + (34.0F - var47) / 2.0F;
            var41.l[0] = var63;
            var41.l[1] = var48;
            var41.l[2] = var46;
            var41.l[3] = var47;
            var49 = var41 != this.c || HitSplashSupport.moved();
            long var50 = 130715659799022L;
            var9 = (boolean)(var50 < 0L ? -1 : 1);
            if (var49) {
               long var53 = 130715659799022L;
               int var68 = var53 < 0L ? -1 : 1;
               if (!this.v) {
                  boolean var55 = var1.masked();
                  long var56 = 130715659799022L;
                  int var36 = var56 < 0L ? -1 : 1;
                  if (!var55 && K(var17, var41.l, var1.mouseX, var1.mouseY)) {
                     var9 = (boolean)1;
                     break label219;
                  }
               }
            }

            var9 = (boolean)0;
         }

         if (var9) {
            var1.claim(var41, TypeE.HAND);
         }

         float var51 = var41.H.target(var9 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         float var52 = var41.M.update(MotionStyle.TOGGLE.dt(var1.dt));
         var2.push();
         var2.scaleAround(1.0F - 0.05F * var52, var63 + var46 / 2.0F, var48 + var47 / 2.0F);
         boolean var68 = var41 == this.o && this.R;
         long var54 = 130715659799022L;
         int var36 = var54 < 0L ? -1 : 1;
         if (var41 == this.a) {
            var2.glow(var63, var48, var46, var47, var47 / 2.0F, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.22F + 0.2F * var51 + 0.15F * var39));
            long var57 = 130715659799022L;
            int var60 = var57 < 0L ? -1 : 1;
            var2.roundRectGradient(
               var63,
               var48,
               var46,
               var47,
               var47 / 2.0F,
               0.0F,
               BlendMode.withAlpha(Icons.INDIGO, 0.88F + 0.12F * var51),
               BlendMode.withAlpha(Icons.PURPLE, 0.88F + 0.12F * var51)
            );
            if (var39 > 0.01F) {
               long var59 = 130715659799023L;
               int var74 = var59 < 0L ? -1 : 1;
               if (this.y >= 0.0) {
                  float var62 = Z((float)((I - this.y) / this.C), var15);
                  long var63 = 130715659799022L;
                  int var611 = var63 < 0L ? -1 : 1;
                  var2.pushClip(var63, var48, var46, var47, var47 / 2.0F);
                  var2.rect(var63, var48, var46 * var62, var47, BlendMode.withAlpha(-1, 0.13F * var39));
                  var2.popClip();
               }
            }
         } else {
            long var69 = 130715659799022L;
            int var76 = var69 < 0L ? -1 : 1;
            if (var68) {
               long var60 = 130715659799023L;
               int var82 = var60 < 0L ? -1 : 1;
               var2.roundRectGradient(
                  var63,
                  var48,
                  var46,
                  var47,
                  var47 / 2.0F,
                  0.0F,
                  BlendMode.withAlpha(Icons.INDIGO, 0.7F + 0.2F * var51),
                  BlendMode.withAlpha(Icons.MAGENTA, 0.7F + 0.2F * var51)
               );
            } else {
               var2.roundRect(var63, var48, var46, var47, var47 / 2.0F, BlendMode.lerp(352321535, 654311423, var51));
            }
         }

         label201: {
            var2.roundRectOutline(var63, var48, var46, var47, var47 / 2.0F, 1.0F, BlendMode.lerp(452984831, 1124073471, var51));
            if (var41 != this.a) {
               long var70 = 130715659799023L;
               int var61 = var70 < 0L ? -1 : 1;
               if (!var68) {
                  var36 = var49 ? BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var51) : BlendMode.withAlpha(Icons.TEXT_3, 0.6F);
                  break label201;
               }
            }

            var36 = Icons.TEXT;
         }

         long var58 = 130715659799022L;
         int var78 = var58 < 0L ? -1 : 1;
         if (!var41.y.isEmpty() && (!this.h || !this.E.contains(var41))) {
            label185: {
               if (var41 == this.a) {
                  float var72;
                  var36 = (var72 = var39 - 0.5F) == 0.0F ? 0 : (var72 < 0.0F ? -1 : 1);
                  long var73 = 130715659799022L;
                  int var87 = var73 < 0L ? -1 : 1;
                  if (var36 > 0) {
                     var8 = "rotate-ccw";
                     break label185;
                  }
               }

               var8 = var41.f;
            }

            int var74 = var41 == this.H ? BlendMode.lerp(Icons.PURPLE, Icons.TEXT, var51) : var36;
            var2.icon(var8, var63 + 11.0F + 5.5F, var48 + var47 / 2.0F, 11.0F, var74);
            var2.text(Fonts.MEDIUM, var41.y, var63 + 11.0F + 11.0F + 5.0F, var48 + var47 / 2.0F, 9.0F, var36, FontStyle.LEFT, FontWeight.MIDDLE);
         } else {
            var2.icon(var41.f, var63 + var46 / 2.0F, var48 + var47 / 2.0F, 12.0F, var36);
            long var61 = 130715659799023L;
            int var85 = var61 < 0L ? -1 : 1;
         }

         var2.pop();
         var37x = var63 + var46;
      }
   }

   private List<HitSplashRenderer2.TypeG> B() {
      ArrayList var1 = new ArrayList();
      var1.add(this.a);
      var1.add(this.o);
      var1.addAll(this.E);
      var1.add(this.c);
      var1.add(this.H);
      return var1;
   }

   private boolean z(HitSplashRenderer2.TypeG var1, int var2, int var3) {
      return var1 == this.a || var1 == this.c || !this.E.isEmpty() && var1 == this.E.getFirst();
   }

   private float s(Canvas var1, List var2, long var3) {
      long var5 = 13971166246986L;
      byte var7 = 0;
      short var8 = 3252;
      int var9 = (int)(var5 << 32 >>> 32);
      short var10 = 23495;
      int var11 = -138027045;
      float var12 = 0.0F;

      for (HitSplashRenderer2.TypeG var14 : var2) {
         var12 += this.y(var7, (char)var8, var1, var9, var14) + (this.z(var14, var10, var11) ? 14 : 5);
      }

      return var12;
   }

   private float y(short var1, char var2, Canvas var3, int var4, HitSplashRenderer2.TypeG var5) {
      return !var5.y.isEmpty() && (!this.h || !this.E.contains(var5)) ? 27.0F + var3.textWidth(Fonts.MEDIUM, var5.y, 9.0F) + 12.0F : 28.0F;
   }

   private void Q(DrawContext var1, Canvas var2, float var3, long var4) {
      long var6 = 107946995580869L;
      long var8 = 77964584970366L;
      float var10 = this.O[0] + this.O[2] / 2.0F;
      float var11 = this.W - 22.0F - 34.0F - 8.0F - 24.0F + var3;
      String var12 = this.i.note();
      if (var12 != null) {
         float var13 = var2.textWidth(Fonts.MEDIUM, var12, 8.5F);
         float var14 = var13 + 40.0F;
         float var15 = var10 - var14 / 2.0F;
         this.S(var2, var15, var11, var14, var8);
         var2.icon("info", var15 + 16.0F, var11 + 12.0F, 11.0F, Icons.PINK);
         var2.text(Fonts.MEDIUM, var12, var15 + 28.0F, var11 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      } else {
         List var49 = this.y >= 0.0 ? this.Q : this.i.timeline();
         int var50 = var49.size();
         if (var50 != 0) {
            float var51 = 18.0F;
            float[] var16 = new float[var50];
            float var17 = 0.0F;

            for (int var18 = 0; var18 < var50; var18++) {
               var16[var18] = 12.0F + var2.textWidth(Fonts.MEDIUM, ((HitSplashFactory.Data)var49.get(var18)).label(), 8.5F);
               var17 += var16[var18] + (var18 > 0 ? var51 : 0.0F);
            }

            long var52 = 65404967424822L;
            int var21 = var52 < 0L ? -1 : 1;
            float var21x = var17 + 28.0F;
            long var22 = 65404967424822L;
            int var24 = var22 < 0L ? -1 : 1;
            float var24x = this.N.value() <= 0.0F ? this.N.snap(var21x).value() : this.N.target(var21x).update(var1.dt);
            float var25 = var10 - var24x / 2.0F;
            this.b[0] = var25;
            this.b[1] = var11;
            this.b[2] = var24x;
            this.b[3] = 24.0F;
            this.S(var2, var25, var11, var24x, var8);
            float var26 = var11 + 12.0F;
            long var27 = 65404967424823L;
            int var30 = var27 < 0L ? -1 : 1;
            double var30x = this.y >= 0.0 ? I - this.y : -1.0;
            float var32 = var10 - var17 / 2.0F;

            for (int var33 = 0; var33 < var50; var33++) {
               HitSplashFactory.Data var34 = (HitSplashFactory.Data)var49.get(var33);
               boolean var35 = var30x >= var34.at();
               long var36 = 65404967424823L;
               int var36x = var36 < 0L ? -1 : 1;
               float var38x = var35 && var33 < this.Y.length ? (float)(I - this.Y[var33]) : 99.0F;
               float var39 = var35 ? (float)Math.exp(-var38x / 0.18F) : 0.0F;
               float var40 = var32 + 3.5F;
               if (var33 > 0) {
                  HitSplashFactory.Data var41 = (HitSplashFactory.Data)var49.get(var33 - 1);
                  long var42 = 65404967424822L;
                  int var42x = var42 < 0L ? -1 : 1;
                  float var44x = var32 - var51 + 3.0F;
                  float var45 = var32 - 3.0F;
                  var2.rect(var44x, var26 - 0.5F, var45 - var44x, 1.0F, 587202559);
                  long var46 = 65404967424822L;
                  int var46x = var46 < 0L ? -1 : 1;
                  if (var30x >= var41.at()) {
                     float var48x = Z((float)((var30x - var41.at()) / Math.max(0.05F, var34.at() - var41.at())), var6);
                     var2.rect(var44x, var26 - 0.75F, (var45 - var44x) * var48x, 1.5F, BlendMode.withAlpha(Icons.MAGENTA, 0.85F));
                  }
               }

               if (var39 > 0.01F) {
                  var2.glowCircle(var40, var26, 3.5F + 3.0F * var39, 9.0F, BlendMode.withAlpha(Icons.PINK, 0.55F * var39));
               }

               var2.circle(var40, var26, 3.0F + 1.2F * var39, var35 ? BlendMode.lerp(Icons.MAGENTA, -1, 0.5F * var39) : 1090519039);
               var2.text(Fonts.MEDIUM, var34.label(), var32 + 7.0F + 5.0F, var26, 8.5F, var35 ? Icons.TEXT : Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
               var32 += var16[var33] + var51;
            }
         }
      }
   }

   private void S(Canvas var1, float var2, float var3, float var4, long var5) {
      var1.shadow(var2, var3, var4, 24.0F, 12.0F, 16.0F, 0.0F, 0.0F, 4.0F, 1342177280);
      var1.roundRect(var2, var3, var4, 24.0F, 12.0F, Theme.tint(-703525600));
      var1.roundRectOutline(var2, var3, var4, 24.0F, 12.0F, 1.0F, 452984831);
   }

   private void s(long var1, DrawContext var3, Canvas var4, float var5, float var6) {
      long var7;
      boolean var21;
      label44: {
         var7 = 77964584970366L;
         if (this.s) {
            long var10 = 79169193292572L;
            int varCmp = var10 < 0L ? -1 : 1;
            if (!this.G) {
               var21 = HitSplashSupport.settled();
               long var13 = 79169193292572L;
               int var14 = var13 < 0L ? -1 : 1;
               if (var21 && (this.p < 0.0 || I - this.p < 9.0)) {
                  var21 = true;
                  break label44;
               }
            }
         }

         var21 = false;
      }

      float var11 = this.w.target(var21 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var3.dt) * 0.6F);
      float var12;
      int varCmp = Float.compare(var12 = var11 - 0.01F, 0.0F);
      long var24 = 79169193292572L;
      int var16 = var24 < 0L ? -1 : 1;
      if (varCmp > 0) {
         String var16x = "Drag to look around  ·  Scroll to zoom  ·  Double-click to reset";
         float var17 = var4.textWidth(Fonts.MEDIUM, var16x, 8.5F);
         float var18 = var17 + 40.0F;
         float var19 = 22.0F - (1.0F - var11) * 6.0F;
         float var20 = Math.max(16.0F, (16.0F + this.L - 16.0F) / 2.0F - var18 / 2.0F);
         var4.pushAlpha(var11 / Math.max(var6, 0.01F) * var6);
         this.S(var4, var20, var19, var18, var7);
         var4.icon("mouse", var20 + 16.0F, var19 + 12.0F, 11.0F, Icons.PURPLE);
         var4.text(Fonts.MEDIUM, var16x, var20 + 28.0F, var19 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         var4.popAlpha();
      }
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      long var4 = 44917452601406L;
      long var6 = var4 ^ 30584916755298L;
      if (!this.s) {
         return false;
      } else if (this.O[2] > 0.0F && K(var6, this.O, var1, var2)) {
         if (var3 == 0) {
            for (HitSplashRenderer2.TypeG var9 : this.B()) {
               if (K(var6, var9.l, var1, var2) && (var9 != this.c || HitSplashSupport.moved())) {
                  var9.M.snap(1.0F).target(0.0F);
                  var9.z.run();
                  return true;
               }
            }
         }

         return true;
      } else if (var3 > 1) {
         return false;
      } else {
         long var8 = System.currentTimeMillis();
         if (var3 == 0 && var8 - this.k < 320L) {
            this.k = 0L;
            if (HitSplashSupport.moved()) {
               HitSplashSupport.resetView();
               UiAudioPolicy.play("TAB", 1.1F);
            }

            return true;
         } else {
            this.k = var8;
            this.v = true;
            this.J = var1;
            this.j = var2;
            return true;
         }
      }
   }

   public void mouseDrag(float var1, float var2) {
      if (this.v) {
         float var3 = var1 - this.J;
         float var4 = var2 - this.j;
         this.J = var1;
         this.j = var2;
         if (var3 != 0.0F || var4 != 0.0F) {
            HitSplashSupport.drag(var3, var4);
            this.G = true;
            this.k = 0L;
         }
      }
   }

   public void mouseUp(float var1, float var2, int var3) {
      this.v = false;
   }

   public boolean scroll(float var1, float var2, double var3) {
      if (this.s && var3 != 0.0) {
         HitSplashSupport.zoom(var3);
         this.G = true;
         return true;
      } else {
         return false;
      }
   }

   public boolean keyPressed(int var1) {
      long var2 = 44785271894347L;
      long var4 = var2 ^ 64191133463507L;
      if (!this.s) {
         return false;
      } else {
         switch (var1) {
            case 32:
               this.a.M.snap(1.0F).target(0.0F);
               this.V(var4);
               return true;
            case 76:
               this.o.z.run();
               return true;
            default:
               return false;
         }
      }
   }

   static HitSplashRenderer2 current() {
      return B;
   }

   String describe() {
      return String.format(
         Locale.ROOT,
         "%s open %s lent %s playing %s loop %s clock %.2f, camera: %s",
         this.u.id(),
         this.s,
         this.l,
         this.y >= 0.0 ? String.format(Locale.ROOT, "%.2f", I - this.y) : "no",
         this.R,
         I,
         HitSplashSupport.describe()
      );
   }

   private static boolean K(long var0, float[] var2, float var3, float var4) {
      float var5 = var2[2];
      long var6 = 56123207400284L;
      int var9 = var6 < 0L ? -1 : 1;
      if (var5 > 0.0F) {
         long var9x = 56123207400283L;
         int var10 = var9x < 0L ? -1 : 1;
         if (var3 >= var2[0] && var3 <= var2[0] + var2[2] && var4 >= var2[1] && var4 <= var2[1] + var2[3]) {
            return true;
         }
      }

      return false;
   }

   private static float Z(float var0, long var1) {
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   static void closeOwned() {
      long var0 = 36408664510191L;
      long var2 = var0 ^ 62003387103028L;
      if (B != null) {
         B.w(var2, true);
      }

      HitSplashSupport.reset();
      m.clear();
      U.clear();
   }

   public static void ensureSourceClassInitialized() {
   }

   private static final class TypeG {
      final String y;
      final String f;
      final Runnable z;
      final float[] l = new float[4];
      final Animator H = Animator.smooth(0.0F);
      final Animator M = Animator.snappy(0.0F);

      TypeG(String var1, String var2, Runnable var3) {
         this.y = var1;
         this.f = var2;
         this.z = var3;
      }
   }

   private record TypeX(double m, Consumer<class_746> c) {
      public double at() {
         return this.m;
      }

      public Consumer<class_746> action() {
         return this.c;
      }

      public double doubleState11() {
         return this.m;
      }

      public Consumer<class_746> consumerState15() {
         return this.c;
      }
   }
}
