package dev.purxiy.modules.glass;

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

public final class GlassRenderer4 implements Studio {
   private static final float N = 34.0F;
   private static final float H = 24.0F;
   private static final long x = 320L;
   private static final float f = 0.9F;
   private static final float h = 0.25F;
   private static final float p = 0.55F;
   private static final Map<ModuleDescriptor, GlassRenderer4> j = new IdentityHashMap<>();
   private static GlassRenderer4 X;
   private static final List<GlassRenderer4.TypeD> M = new ArrayList<>();
   private static double q;
   private static long s = 0L;
   private final ModuleDescriptor I;
   private final GlassFactory w;
   private boolean d;
   private boolean G;
   private boolean O;
   private boolean P;
   private double i;
   private int k;
   private double E;
   private List<GlassFactory.Data2> U;
   private double e;
   private float V;
   private int K;
   private final double[] Z;
   private boolean D;
   private final GlassRenderer4.TypeZ t;
   private final GlassRenderer4.TypeZ W;
   private final GlassRenderer4.TypeZ F;
   private final GlassRenderer4.TypeZ S;
   private final List<GlassRenderer4.TypeZ> A;
   private final float[] o;
   private final float[] Q;
   private boolean r;
   private final Animator b;
   private final Animator c;
   private final Animator z;
   private final Animator L;
   private float y;
   private float R;
   private float C;
   private boolean m;
   private float n;
   private float Y;
   private long B;
   private boolean v;
   private final Object u;
   public static boolean J;
   private static final String[] g = new String[27];
   private static final long[] l = new long[]{
      5359549742651376427L,
      2399876185519773311L,
      8945012812954151082L,
      4743037548254066261L,
      -3280434037780397923L,
      8386098980967968711L,
      2019860632079093231L,
      4103729839954818324L,
      5894619007493626546L,
      -1969767476673296044L,
      6983595514485409870L,
      4846230456774741194L,
      -3554183439519504160L,
      -1549252416421223550L,
      -5339271747235140171L,
      -2618991826173977650L,
      -7197651452502486149L,
      -1779811566182469176L
   };
   private static final Integer[] T = new Integer[18];

   private GlassRenderer4(ModuleDescriptor var1, long var2, GlassFactory var4) {
      long var5 = 436679005513L;
      var5 = 107894901625869L;
      this.i = -1.0;
      this.E = -1.0;
      this.U = List.of();
      this.e = -1.0;
      this.V = 1.0F;
      this.Z = new double[16];
      this.A = new ArrayList<>();
      this.o = new float[4];
      this.Q = new float[4];
      this.b = Animator.smooth(0.0F);
      this.c = new Animator(0.0F, 420.0F, 41.0F);
      this.z = Animator.smooth(0.0F);
      this.L = Animator.smooth(0.0F);
      this.u = new Object();
      this.I = var1;
      this.w = var4;
      this.t = new GlassRenderer4.TypeZ("Play", "play", () -> this.u(17565437561696L));
      this.W = new GlassRenderer4.TypeZ("Loop", "repeat", () -> {
         long var1x = 17565437561696L;
         boolean var3 = false;
         this.D = !this.D;
         long var4x = 12167747971756L;
         int var7 = var4x < 0L ? -1 : 1;
         UiAudioPolicy.play(this.D ? "TOGGLE_ON" : "TOGGLE_OFF");
         long var7x = 12167747971757L;
         int var8x = var7x < 0L ? -1 : 1;
         if (this.D && this.e < 0.0) {
            this.u(var1x);
         }
      });

      for (GlassFactory.Data var8 : var4.b) {
         long var9 = 44297962348595L;
         int var11 = var9 < 0L ? -1 : 1;
         this.A.add(new GlassRenderer4.TypeZ(var8.label(), var8.icon(), () -> {
            long var2x = 436679005513L;
            long var4x = var2x ^ 50807351872119L;
            this.f(var8, var4x);
         }));
      }

      this.F = new GlassRenderer4.TypeZ("", "rotate-ccw", () -> {
         GlassSupport2.resetView();
         UiAudioPolicy.play("TAB", 1.1F);
      });
      long var13 = 44297962348595L;
      int var14 = var13 < 0L ? -1 : 1;
      this.S = new GlassRenderer4.TypeZ("Done", "check", () -> {
         this.O = true;
         UiAudioPolicy.play("CLICK");
      });
   }

   public static GlassRenderer4 of(ModuleDescriptor var0) {
      long var1 = 39044240693629L;
      long var3 = var1 ^ 12968089073998L;
      GlassRenderer4 var5 = j.get(var0);
      if (var5 == null) {
         GlassFactory var6 = GlassFactory.of(var0);
         if (var6 == null) {
            return null;
         }

         var5 = new GlassRenderer4(var0, var3, var6);
         j.put(var0, var5);
      }

      return var5;
   }

   static boolean showing(ModuleDescriptor var0) {
      GlassRenderer4 var1 = X;
      return var1 != null && var1.I == var0 && var1.G;
   }

   static void later(float var0, Consumer<class_746> var1) {
      M.add(new GlassRenderer4.TypeD(q + var0, var1));
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
         && (X == null || X == this || !X.G);
   }

   public TypeQ dock() {
      return TypeQ.RIGHT;
   }

   public boolean keepsWorldRunning() {
      return true;
   }

   public void setOpen(boolean var1) {
      long var2 = 133806112620411L;
      long var4 = var2 ^ 134214332257774L;
      long var6 = var2 ^ 133958332452541L;
      int var8 = (int)((var2 ^ 133958332452541L) >>> 32);
      int var9 = (int)((var2 ^ 133958332452541L) << 32 >>> 40);
      int var10 = (int)(var6 << 56 >>> 56);
      class_310 var11 = class_310.method_1551();
      if (var1 != this.d) {
         this.d = var1;
         this.m = false;
         if (var1) {
            if (X != null && X != this) {
               X.y(var4, true);
            }

            X = this;
            if (!this.G) {
               this.G = true;
            }

            GlassSupport2.open();
            this.P = false;
            this.i = -1.0;
            this.k = this.k(var8, var9, (byte)var10);
            this.E = -1.0;
            this.v = false;
            this.b.snap(0.0F);
            this.z.snap(0.0F);
            this.n();
         } else {
            this.n();
            M.clear();
            this.D = false;
            GlassSupport2.close(CosmeticRegistry.currentOwner() == null);
            if (var11.field_1724 != null) {
               this.w.closed(var11.field_1724);
            }
         }
      }
   }

   public boolean consumeCloseRequest() {
      boolean var1 = this.O;
      this.O = false;
      return var1;
   }

   private void y(long var1, boolean var3) {
      if (this.d) {
         long var4 = 3999089994389L;
         int var7 = var4 < 0L ? -1 : 1;
         if (!var3) {
            return;
         }
      }

      if (var3) {
         long var8 = 3999089994389L;
         int var8x = var8 < 0L ? -1 : 1;
         if (this.d) {
            this.d = false;
            this.n();
            class_310 var7 = class_310.method_1551();
            if (var7.field_1724 != null) {
               this.w.closed(var7.field_1724);
            }

            GlassSupport2.reset();
         }
      }

      if (this.G) {
         this.G = false;
      }

      if (X == this) {
         X = null;
      }
   }

   static void tick(class_310 var0) {
      long var1 = 77382189569085L;
      long var3 = var1 ^ 76707809558184L;
      GlassRenderer4 var5 = X;
      if (var5 != null) {
         if (var0.field_1687 != null && var0.field_1724 != null) {
            if (var5.d && CosmeticRegistry.currentOwner() == null) {
               var5.setOpen(false);
            }

            if (!var5.d && GlassSupport2.active() && CosmeticRegistry.currentOwner() == null) {
               GlassSupport2.hurry();
            }

            if (!var5.d && !GlassSupport2.active()) {
               var5.y(var3, false);
            }
         } else {
            var5.y(var3, true);
         }
      }
   }

   private void u(long var1) {
      class_310 var3 = class_310.method_1551();
      if (var3.field_1724 != null) {
         long var4 = 17565437561695L;
         int var7 = var4 < 0L ? -1 : 1;
         String var7x = this.w.note();
         long var8 = 17565437561695L;
         int var9 = var8 < 0L ? -1 : 1;
         if (var7x == null) {
            this.U = this.w.timeline();
            this.V = Math.max(0.5F, this.w.length());
            this.e = q;
            this.K = 0;
            UiAudioPolicy.play("SELECT", 1.05F, 0.7F);
            return;
         }
      }

      UiAudioPolicy.play("DENY");
   }

   private void f(GlassFactory.Data var1, long var2) {
      class_310 var4 = class_310.method_1551();
      if (var4.field_1724 != null && this.w.note() == null) {
         var1.action().accept(var4.field_1724);
      } else {
         UiAudioPolicy.play("DENY");
      }
   }

   private int k(int var1, int var2, byte var3) {
      int var4 = 1;

      for (Setting var6 : this.I.settings()) {
         try {
            var4 = var4 * 31 + var6.save().hashCode();
         } catch (RuntimeException var8) {
            var4 *= 31;
         }
      }

      return var4;
   }

   private void n() {
      this.e = -1.0;
      this.K = 0;
      this.U = List.of();
   }

   private void Y(long var1) {
      long var3 = 445386699206L;
      byte var5 = 103;
      int var6 = 11738545;
      int var7 = (int)(var3 << 56 >>> 56);
      long var8 = 17565437561696L;
      long var10 = System.nanoTime();
      float var12 = s == 0L ? 0.0F : Math.min(0.1F, (float)(var10 - s) / 1.0E9F);
      s = var10;
      q += var12;
      class_746 var13 = class_310.method_1551().field_1724;
      if (var13 != null) {
         long var14 = 48433979771241L;
         int var32 = var14 < 0L ? -1 : 1;
         if (this.e >= 0.0) {
            for (var17 = q - this.e; this.K < this.U.size() && this.U.get(this.K).at() <= var17; this.U.get(this.K++).action().accept(var13)) {
               if (this.K < this.Z.length) {
                  this.Z[this.K] = q;
               }
            }

            long var19 = 48433979771242L;
            int var36 = var19 < 0L ? -1 : 1;
            double var22;
            int var21 = (var22 = var17 - (this.V + (this.D ? 0.9F : 0.0F))) == 0.0 ? 0 : (var22 < 0.0 ? -1 : 1);
            long var24 = 48433979771241L;
            int var27 = var24 < 0L ? -1 : 1;
            if (var21 >= 0) {
               if (this.D && this.d) {
                  this.u(var8);
               } else if (var17 >= this.V + 1.2) {
                  this.n();
               }
            }
         }

         Iterator var32x = M.iterator();

         while (var32x.hasNext()) {
            GlassRenderer4.TypeD var18 = (GlassRenderer4.TypeD)var32x.next();
            long var34 = 48433979771241L;
            int var361 = var34 < 0L ? -1 : 1;
            if (var18.O <= q) {
               var32x.remove();
               var18.L.accept(var13);
            }
         }

         long var33 = 48433979771242L;
         int var35 = var33 < 0L ? -1 : 1;
         var35 = this.k(var5, var6, (byte)var7);
         long var36 = 48433979771242L;
         int var381 = var36 < 0L ? -1 : 1;
         if (var35 != this.k) {
            this.k = var35;
            this.E = q;
         }

         if (this.E >= 0.0 && q - this.E >= 0.55F) {
            this.E = -1.0;
            long var37 = 48433979771241L;
            int var39 = var37 < 0L ? -1 : 1;
            boolean var26 = this.e >= 0.0 && q - this.e < this.V;
            if (this.d) {
               long var27 = 48433979771242L;
               int var27x = var27 < 0L ? -1 : 1;
               if (this.P) {
                  long var30x = 48433979771241L;
                  int var29 = var30x < 0L ? -1 : 1;
                  if (!var26 && this.w.note() == null) {
                     this.u(var8);
                  }
               }
            }
         }

         long var38x = 48433979771242L;
         int var42 = var38x < 0L ? -1 : 1;
         if (this.d && GlassSupport2.settled()) {
            if (this.i < 0.0) {
               this.i = q;
            }

            if (!this.P) {
               long var39 = 48433979771241L;
               int var28 = var39 < 0L ? -1 : 1;
               if (q - this.i >= 0.25) {
                  long var28x = 48433979771242L;
                  int var28x = var28x < 0L ? -1 : 1;
                  this.P = true;
                  if (this.e < 0.0 && this.w.note() == null) {
                     this.u(var8);
                  }
               }
            }
         }
      }
   }

   public void render(DrawContext var1, float var2, float var3) {
      long var4 = 8070768397309L;
      long var6 = var4 ^ 47673958744727L;
      long var8 = var4 ^ 56969904364345L;
      long var10 = var4 ^ 65513422618957L;
      long var12 = var4 ^ 40449227183142L;
      int var14 = (int)((var4 ^ 40449227183142L) >>> 32);
      int var15 = (int)((var4 ^ 40449227183142L) << 32 >>> 48);
      int var16 = (int)(var12 << 48 >>> 48);
      long var17 = var4 ^ 76098572966201L;
      this.Y(var6);
      Canvas var19 = var1.canvas;
      this.y = var3;
      this.R = var19.width();
      this.C = var19.height();
      float var20 = 16.0F;
      float var21 = Math.max(var20 + 200.0F, var3 - 16.0F);
      float var22 = (var20 + var21) / 2.0F;
      float var23 = 110.0F;
      GlassSupport2.frame(var22 / this.R * 2.0F - 1.0F, 1.0F - (this.C - var23) / this.C);
      float var24 = Easing.SMOOTHSTEP.apply(R((GlassSupport2.progress() - 0.62F) / 0.38F, var10));
      float var25 = this.b.target(this.d ? var24 : 0.0F).update(MotionStyle.MENU.dt(var1.dt)) * var2;
      if (this.m) {
         var1.claim(this.u, TypeE.MOVE);
      }

      if (var25 <= 0.01F) {
         this.o[2] = 0.0F;
      } else {
         var19.pushAlpha(var25);
         float var26 = (1.0F - var25) * 18.0F;
         this.J(var14, var1, var15, var19, var20, var21, var16, var26);
         this.S(var1, var19, var26, var8);
         this.k(var1, var19, var17, var20, var25);
         var19.popAlpha();
      }
   }

   private void J(int var1, DrawContext var2, int var3, Canvas var4, float var5, float var6, int var7, float var8) {
      long var11 = 39164977995739L;
      long var13 = var11 ^ 47970692598888L;
      long var15 = var11 ^ 1758469515800L;
      long var17 = var11 ^ 34481500706155L;
      long var19 = var11 ^ 113745220218569L;
      int var21 = (int)((var11 ^ 113745220218569L) >>> 32);
      int var22 = (int)((var11 ^ 113745220218569L) << 32 >>> 48);
      int var23 = (int)(var19 << 48 >>> 48);
      long var24 = var11 ^ 90433729190758L;
      String var26 = "Live preview";
      float var27 = var4.textWidth(Fonts.SEMIBOLD, var26, 9.5F);
      List var28 = this.q();
      this.r = false;
      float var29 = this.l(var13, var4, var28);
      boolean var30 = 32.0F + var27 + var29 + 10.0F > var6 - 16.0F;
      if (var30 && 26.0F + var29 + 10.0F > var6 - 16.0F) {
         this.r = true;
         var29 = this.l(var13, var4, var28);
      }

      float var31 = var30 ? 26.0F : 32.0F + var27;
      float var32 = var31 + var29 + 10.0F;
      float var33 = Math.max(16.0F, (16.0F + var6) / 2.0F - var32 / 2.0F);
      float var34 = this.C - 22.0F - 34.0F + var8;
      this.o[0] = var33;
      this.o[1] = var34 - 24.0F - 8.0F;
      this.o[2] = var32;
      this.o[3] = 66.0F;
      var4.shadow(var33, var34, var32, 34.0F, 17.0F, 22.0F, 0.0F, 0.0F, 6.0F, 1879048192);
      var4.roundRect(var33, var34, var32, 34.0F, 17.0F, Theme.tint(-300872416));
      var4.roundRectOutlineGradient(var33, var34, var32, 34.0F, 17.0F, 1.0F, 90.0F, 788529151, 184549375);
      float var35 = var34 + 17.0F;
      float var36 = MotionStyle.reduced() ? 0.7F : 0.55F + 0.45F * (float)Math.sin(var2.time * 3.2F);
      int var37 = 52889;
      var4.glowCircle(var33 + 12.0F + 6.0F, var35, 3.0F, 7.0F, BlendMode.withAlpha(Icons.PINK, 0.45F * var36));
      var4.circle(var33 + 12.0F + 6.0F, var35, 3.0F, BlendMode.lerp(Icons.MAGENTA, Icons.PINK, var36));
      if (!var30) {
         var4.text(Fonts.SEMIBOLD, var26, var33 + 12.0F + 14.0F + 6.0F, var35, 9.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      float var38 = var33 + var31;
      char var39 = '캙';
      boolean var40 = this.e >= 0.0 && q - this.e < this.V;
      float var41 = this.L.target(var40 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var2.dt));

      for (GlassRenderer4.TypeZ var43 : var28) {
         float var44 = this.g(var21, var43, var22, var23) ? 14.0F : 5.0F;
         if (this.g(var21, var43, var22, var23)) {
            var4.rect(var38 + var44 / 2.0F - 0.5F, var34 + 9.0F, 1.0F, 16.0F, 486539263);
         }

         boolean var10;
         float var45;
         float var46;
         float var47;
         boolean var48;
         label144: {
            var38 += var44;
            var45 = this.T(var24, var4, var43);
            var46 = 24.0F;
            var47 = var34 + (34.0F - var46) / 2.0F;
            var43.S[0] = var38;
            var43.S[1] = var47;
            var43.S[2] = var45;
            var43.S[3] = var46;
            var48 = var43 != this.F || GlassSupport2.moved();
            var39 = '\udfdb';
            if (var48) {
               var39 = '캙';
               if (!this.m) {
                  boolean var49 = var2.masked();
                  var39 = '\udfdb';
                  if (!var49 && B(var43.S, var2.mouseX, var2.mouseY, var15)) {
                     var10 = true;
                     break label144;
                  }
               }
            }

            var10 = false;
         }

         if (var10) {
            var2.claim(var43, TypeE.HAND);
         }

         float var50 = var43.w.target(var10 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var2.dt));
         float var51 = var43.j.update(MotionStyle.TOGGLE.dt(var2.dt));
         var4.push();
         var4.scaleAround(1.0F - 0.05F * var51, var38 + var45 / 2.0F, var47 + var46 / 2.0F);
         boolean var52 = var43 == this.W && this.D;
         if (var43 == this.t) {
            var4.glow(var38, var47, var45, var46, var46 / 2.0F, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.22F + 0.2F * var50 + 0.15F * var41));
            var4.roundRectGradient(
               var38,
               var47,
               var45,
               var46,
               var46 / 2.0F,
               0.0F,
               BlendMode.withAlpha(Icons.INDIGO, 0.88F + 0.12F * var50),
               BlendMode.withAlpha(Icons.PURPLE, 0.88F + 0.12F * var50)
            );
            if (var41 > 0.01F) {
               var39 = '\udfdb';
               if (this.e >= 0.0) {
                  float var53 = R((float)((q - this.e) / this.V), var17);
                  var4.pushClip(var38, var47, var45, var46, var46 / 2.0F);
                  var4.rect(var38, var47, var45 * var53, var46, BlendMode.withAlpha(-1, 0.13F * var41));
                  var4.popClip();
               }
            }
         } else if (var52) {
            var4.roundRectGradient(
               var38,
               var47,
               var45,
               var46,
               var46 / 2.0F,
               0.0F,
               BlendMode.withAlpha(Icons.INDIGO, 0.7F + 0.2F * var50),
               BlendMode.withAlpha(Icons.MAGENTA, 0.7F + 0.2F * var50)
            );
         } else {
            var4.roundRect(var38, var47, var45, var46, var46 / 2.0F, BlendMode.lerp(352321535, 654311423, var50));
         }

         label128: {
            var4.roundRectOutline(var38, var47, var45, var46, var46 / 2.0F, 1.0F, BlendMode.lerp(452984831, 1124073471, var50));
            if (var43 != this.t) {
               var39 = '캙';
               if (!var52) {
                  var37 = var48 ? BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var50) : BlendMode.withAlpha(Icons.TEXT_3, 0.6F);
                  break label128;
               }
            }

            var37 = Icons.TEXT;
         }

         if (!var43.H.isEmpty() && (!this.r || !this.A.contains(var43))) {
            String var9;
            label114: {
               if (var43 == this.t) {
                  float var54;
                  var37 = (var54 = var41 - 0.5F) == 0.0F ? 0 : (var54 < 0.0F ? -1 : 1);
                  if (var37 > 0) {
                     var9 = "rotate-ccw";
                     break label114;
                  }
               }

               var9 = var43.e;
            }

            int var55 = var43 == this.S ? BlendMode.lerp(Icons.PURPLE, Icons.TEXT, var50) : var37;
            var4.icon(var9, var38 + 11.0F + 5.5F, var47 + var46 / 2.0F, 11.0F, var55);
            var4.text(Fonts.MEDIUM, var43.H, var38 + 11.0F + 11.0F + 5.0F, var47 + var46 / 2.0F, 9.0F, var37, FontStyle.LEFT, FontWeight.MIDDLE);
         } else {
            var4.icon(var43.e, var38 + var45 / 2.0F, var47 + var46 / 2.0F, 12.0F, var37);
         }

         var4.pop();
         var38 += var45;
      }
   }

   private List<GlassRenderer4.TypeZ> q() {
      ArrayList var1 = new ArrayList();
      var1.add(this.t);
      var1.add(this.W);
      var1.addAll(this.A);
      var1.add(this.F);
      var1.add(this.S);
      return var1;
   }

   private boolean g(int var1, GlassRenderer4.TypeZ var2, int var3, int var4) {
      return var2 == this.t || var2 == this.F || !this.A.isEmpty() && var2 == this.A.getFirst();
   }

   private float l(long var1, Canvas var3, List var4) {
      long var5 = 75787146096914L;
      short var7 = 17645;
      char var8 = '釫';
      int var9 = (int)(var5 << 48 >>> 48);
      long var10 = 124938196616381L;
      float var12 = 0.0F;

      for (GlassRenderer4.TypeZ var14 : var4) {
         var12 += this.T(var10, var3, var14) + (this.g(var7, var14, var8, var9) ? 14 : 5);
      }

      return var12;
   }

   private float T(long var1, Canvas var3, GlassRenderer4.TypeZ var4) {
      return !var4.H.isEmpty() && (!this.r || !this.A.contains(var4)) ? 27.0F + var3.textWidth(Fonts.MEDIUM, var4.H, 9.0F) + 12.0F : 28.0F;
   }

   private void S(DrawContext var1, Canvas var2, float var3, long var4) {
      long var6 = 83200743895650L;
      byte var8 = 0;
      short var9 = 19371;
      int var10 = (int)(var6 << 32 >>> 32);
      long var11 = 66806454684336L;
      float var13 = this.o[0] + this.o[2] / 2.0F;
      float var14 = this.C - 22.0F - 34.0F - 8.0F - 24.0F + var3;
      String var15 = this.w.note();
      if (var15 != null) {
         float var16 = var2.textWidth(Fonts.MEDIUM, var15, 8.5F);
         float var17 = var16 + 40.0F;
         float var18 = var13 - var17 / 2.0F;
         this.Q((char)var8, var2, var18, var14, var17, var9, var10);
         var2.icon("info", var18 + 16.0F, var14 + 12.0F, 11.0F, Icons.PINK);
         var2.text(Fonts.MEDIUM, var15, var18 + 28.0F, var14 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      } else {
         List var53 = this.e >= 0.0 ? this.U : this.w.timeline();
         int var54 = var53.size();
         if (var54 != 0) {
            float var55 = 18.0F;
            float[] var19 = new float[var54];
            float var20 = 0.0F;

            for (int var21 = 0; var21 < var54; var21++) {
               var19[var21] = 12.0F + var2.textWidth(Fonts.MEDIUM, ((GlassFactory.Data2)var53.get(var21)).label(), 8.5F);
               var20 += var19[var21] + (var21 > 0 ? var55 : 0.0F);
            }

            long var56 = 57755660521668L;
            int var24 = var56 < 0L ? -1 : 1;
            float var24x = var20 + 28.0F;
            long var25 = 57755660521667L;
            int var28 = var25 < 0L ? -1 : 1;
            float var28x = this.c.value() <= 0.0F ? this.c.snap(var24x).value() : this.c.target(var24x).update(var1.dt);
            float var29 = var13 - var28x / 2.0F;
            this.Q[0] = var29;
            this.Q[1] = var14;
            this.Q[2] = var28x;
            this.Q[3] = 24.0F;
            this.Q((char)var8, var2, var29, var14, var28x, var9, var10);
            float var30 = var14 + 12.0F;
            long var31 = 57755660521667L;
            int var31x = var31 < 0L ? -1 : 1;
            double var34x = this.e >= 0.0 ? q - this.e : -1.0;
            float var36 = var13 - var20 / 2.0F;

            for (int var37 = 0; var37 < var54; var37++) {
               GlassFactory.Data2 var38 = (GlassFactory.Data2)var53.get(var37);
               boolean var39 = var34x >= var38.at();
               long var40 = 57755660521668L;
               int var39x = var40 < 0L ? -1 : 1;
               float var42x = var39 && var37 < this.Z.length ? (float)(q - this.Z[var37]) : 99.0F;
               float var43 = var39 ? (float)Math.exp(-var42x / 0.18F) : 0.0F;
               float var44 = var36 + 3.5F;
               if (var37 > 0) {
                  GlassFactory.Data2 var45 = (GlassFactory.Data2)var53.get(var37 - 1);
                  long var46 = 57755660521667L;
                  int var45x = var46 < 0L ? -1 : 1;
                  float var48x = var36 - var55 + 3.0F;
                  float var49 = var36 - 3.0F;
                  var2.rect(var48x, var30 - 0.5F, var49 - var48x, 1.0F, 587202559);
                  long var50 = 57755660521667L;
                  int var49x = var50 < 0L ? -1 : 1;
                  if (var34x >= var45.at()) {
                     float var52x = R((float)((var34x - var45.at()) / Math.max(0.05F, var38.at() - var45.at())), var11);
                     var2.rect(var48x, var30 - 0.75F, (var49 - var48x) * var52x, 1.5F, BlendMode.withAlpha(Icons.MAGENTA, 0.85F));
                  }
               }

               if (var43 > 0.01F) {
                  var2.glowCircle(var44, var30, 3.5F + 3.0F * var43, 9.0F, BlendMode.withAlpha(Icons.PINK, 0.55F * var43));
               }

               var2.circle(var44, var30, 3.0F + 1.2F * var43, var39 ? BlendMode.lerp(Icons.MAGENTA, -1, 0.5F * var43) : 1090519039);
               var2.text(Fonts.MEDIUM, var38.label(), var36 + 7.0F + 5.0F, var30, 8.5F, var39 ? Icons.TEXT : Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
               var36 += var19[var37] + var55;
            }
         }
      }
   }

   private void Q(char var1, Canvas var2, float var3, float var4, float var5, short var6, int var7) {
      var2.shadow(var3, var4, var5, 24.0F, 12.0F, 16.0F, 0.0F, 0.0F, 4.0F, 1342177280);
      var2.roundRect(var3, var4, var5, 24.0F, 12.0F, Theme.tint(-703525600));
      var2.roundRectOutline(var3, var4, var5, 24.0F, 12.0F, 1.0F, 452984831);
   }

   private void k(DrawContext var1, Canvas var2, long var3, float var5, float var6) {
      byte var7;
      short var8;
      int var9;
      boolean var22;
      label44: {
         long var11 = 83200743895650L;
         var7 = 0;
         var8 = 19371;
         var9 = (int)(var11 << 32 >>> 32);
         if (this.d) {
            long var13 = 72984565928644L;
            int var16 = var13 < 0L ? -1 : 1;
            if (!this.v) {
               var22 = GlassSupport2.settled();
               long var16x = 72984565928644L;
               int var18 = var16x < 0L ? -1 : 1;
               if (var22 && (this.i < 0.0 || q - this.i < 9.0)) {
                  var22 = true;
                  break label44;
               }
            }
         }

         var22 = false;
      }

      float var12 = this.z.target(var22 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt) * 0.6F);
      float var25;
      int varCmp = Float.compare(var25 = var12 - 0.01F, 0.0F);
      long var14 = 72984565928643L;
      int var17 = var14 < 0L ? -1 : 1;
      if (varCmp > 0) {
         String var17x = "Drag to look around  ·  Scroll to zoom  ·  Double-click to reset";
         float var18 = var2.textWidth(Fonts.MEDIUM, var17x, 8.5F);
         float var19 = var18 + 40.0F;
         float var20 = 22.0F - (1.0F - var12) * 6.0F;
         float var21 = Math.max(16.0F, (16.0F + this.y - 16.0F) / 2.0F - var19 / 2.0F);
         var2.pushAlpha(var12 / Math.max(var6, 0.01F) * var6);
         this.Q((char)var7, var2, var21, var20, var19, var8, var9);
         var2.icon("mouse", var21 + 16.0F, var20 + 12.0F, 11.0F, Icons.PURPLE);
         var2.text(Fonts.MEDIUM, var17x, var21 + 28.0F, var20 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         var2.popAlpha();
      }
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      long var4 = 133133122968727L;
      long var6 = var4 ^ 100136540318036L;
      if (!this.d) {
         return false;
      } else if (this.o[2] > 0.0F && B(this.o, var1, var2, var6)) {
         if (var3 == 0) {
            for (GlassRenderer4.TypeZ var9 : this.q()) {
               if (B(var9.S, var1, var2, var6) && (var9 != this.F || GlassSupport2.moved())) {
                  var9.j.snap(1.0F).target(0.0F);
                  var9.L.run();
                  return true;
               }
            }
         }

         return true;
      } else if (var3 > 1) {
         return false;
      } else {
         long var8 = System.currentTimeMillis();
         if (var3 == 0 && var8 - this.B < 320L) {
            this.B = 0L;
            if (GlassSupport2.moved()) {
               GlassSupport2.resetView();
               UiAudioPolicy.play("TAB", 1.1F);
            }

            return true;
         } else {
            this.B = var8;
            this.m = true;
            this.n = var1;
            this.Y = var2;
            return true;
         }
      }
   }

   public void mouseDrag(float var1, float var2) {
      if (this.m) {
         float var3 = var1 - this.n;
         float var4 = var2 - this.Y;
         this.n = var1;
         this.Y = var2;
         if (var3 != 0.0F || var4 != 0.0F) {
            GlassSupport2.drag(var3, var4);
            this.v = true;
            this.B = 0L;
         }
      }
   }

   public void mouseUp(float var1, float var2, int var3) {
      this.m = false;
   }

   public boolean scroll(float var1, float var2, double var3) {
      if (this.d && var3 != 0.0) {
         GlassSupport2.zoom(var3);
         this.v = true;
         return true;
      } else {
         return false;
      }
   }

   public boolean keyPressed(int var1) {
      long var2 = 114866901404709L;
      long var4 = var2 ^ 113805047646021L;
      if (!this.d) {
         return false;
      } else {
         switch (var1) {
            case 32:
               this.t.j.snap(1.0F).target(0.0F);
               this.u(var4);
               return true;
            case 76:
               this.W.L.run();
               return true;
            default:
               return false;
         }
      }
   }

   static GlassRenderer4 current() {
      return X;
   }

   String describe() {
      return String.format(
         Locale.ROOT,
         "%s open %s lent %s playing %s loop %s clock %.2f, camera: %s",
         this.I.id(),
         this.d,
         this.G,
         this.e >= 0.0 ? String.format(Locale.ROOT, "%.2f", q - this.e) : "no",
         this.D,
         q,
         GlassSupport2.describe()
      );
   }

   private static boolean B(float[] var0, float var1, float var2, long var3) {
      float var5 = var0[2];
      long var6 = 37416185017795L;
      int var9 = var6 < 0L ? -1 : 1;
      if (var5 > 0.0F && var1 >= var0[0]) {
         long var9x = 37416185017794L;
         int var10 = var9x < 0L ? -1 : 1;
         if (var1 <= var0[0] + var0[2] && var2 >= var0[1] && var2 <= var0[1] + var0[3]) {
            return true;
         }
      }

      return false;
   }

   private static float R(float var0, long var1) {
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   static void closeOwned() {
      long var0 = 57682445570043L;
      long var2 = var0 ^ 61388537668974L;
      if (X != null) {
         X.y(var2, true);
      }

      GlassSupport2.reset();
      j.clear();
      M.clear();
   }

   public static void ensureSourceClassInitialized() {
   }

   private record TypeD(double O, Consumer<class_746> L) {
      public double at() {
         return this.O;
      }

      public Consumer<class_746> action() {
         return this.L;
      }

      public double doubleState1() {
         return this.O;
      }

      public Consumer<class_746> consumerState5() {
         return this.L;
      }
   }

   private static final class TypeZ {
      final String H;
      final String e;
      final Runnable L;
      final float[] S = new float[4];
      final Animator w = Animator.smooth(0.0F);
      final Animator j = Animator.snappy(0.0F);

      TypeZ(String var1, String var2, Runnable var3) {
         this.H = var1;
         this.e = var2;
         this.L = var3;
      }
   }
}
