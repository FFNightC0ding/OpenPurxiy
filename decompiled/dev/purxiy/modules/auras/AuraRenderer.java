package dev.purxiy.modules.auras;

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

public final class AuraRenderer implements Studio {
   private static final float s = 34.0F;
   private static final float W = 24.0F;
   private static final long p = 320L;
   private static final float t = 0.9F;
   private static final float k = 0.25F;
   private static final float o = 0.55F;
   private static final Map<ModuleDescriptor, AuraRenderer> E = new IdentityHashMap<>();
   private static AuraRenderer O;
   private static final List<AuraRenderer.TypeR> v = new ArrayList<>();
   private static double y;
   private static long N = 0L;
   private final ModuleDescriptor L;
   private final AuraFactory M;
   private boolean e;
   private boolean U;
   private boolean X;
   private boolean f;
   private double G;
   private int R;
   private double r;
   private List<AuraFactory.Data2> l;
   private double c;
   private float i;
   private int D;
   private final double[] J;
   private boolean d;
   private final AuraRenderer.TypeJ u;
   private final AuraRenderer.TypeJ V;
   private final AuraRenderer.TypeJ Q;
   private final AuraRenderer.TypeJ Y;
   private final List<AuraRenderer.TypeJ> S;
   private final float[] x;
   private final float[] T;
   private boolean w;
   private final Animator z;
   private final Animator b;
   private final Animator B;
   private final Animator H;
   private float P;
   private float F;
   private float Z;
   private boolean n;
   private float g;
   private float I;
   private long C;
   private boolean A;
   private final Object h;
   public static int q = 0;
   private static final String[] j = new String[27];

   private AuraRenderer(int var1, ModuleDescriptor var2, short var3, short var4, AuraFactory var5) {
      AuraStyle.ensureSourceClassInitialized();
      this.G = -1.0;
      this.r = -1.0;
      this.l = List.of();
      this.c = -1.0;
      this.i = 1.0F;
      this.J = new double[16];
      this.S = new ArrayList<>();
      this.x = new float[4];
      this.T = new float[4];
      this.z = Animator.smooth(0.0F);
      this.b = new Animator(0.0F, 420.0F, 41.0F);
      this.B = Animator.smooth(0.0F);
      this.H = Animator.smooth(0.0F);
      this.h = new Object();
      this.L = var2;
      this.M = var5;
      this.u = new AuraRenderer.TypeJ("Play", "play", () -> this.Y(21004081665521L));
      this.V = new AuraRenderer.TypeJ("Loop", "repeat", () -> {
         long var1x = 21004081665521L;
         AuraStyle.ensureSourceClassInitialized();
         this.d = !this.d;
         long var3x = 62237175604322L;
         int var6 = var3x < 0L ? -1 : 1;
         UiAudioPolicy.play(this.d ? "TOGGLE_ON" : "TOGGLE_OFF");
         long var6x = 62237175604321L;
         int var7x = var6x < 0L ? -1 : 1;
         if (this.d && this.c < 0.0) {
            this.Y(var1x);
         }
      });

      for (AuraFactory.Data var7 : var5.H) {
         this.S.add(new AuraRenderer.TypeJ(var7.label(), var7.icon(), () -> {
            long var2x = 95448964664876L;
            this.S(var7, var2x);
         }));
      }

      this.Q = new AuraRenderer.TypeJ("", "rotate-ccw", () -> {
         long var0 = 2750401162683L;
         AuraSupport2.resetView();
         UiAudioPolicy.play("TAB", 1.1F);
      });
      this.Y = new AuraRenderer.TypeJ("Done", "check", () -> {
         this.X = true;
         UiAudioPolicy.play("CLICK");
      });
   }

   public static AuraRenderer of(ModuleDescriptor var0) {
      long var1 = 101768069493254L;
      long var3 = var1 ^ 137808054688932L;
      int var5 = (int)((var1 ^ 137808054688932L) >>> 32);
      int var6 = (int)((var1 ^ 137808054688932L) << 32 >>> 48);
      int var7 = (int)(var3 << 48 >>> 48);
      AuraRenderer var8 = E.get(var0);
      if (var8 == null) {
         AuraFactory var9 = AuraFactory.of(var0);
         if (var9 == null) {
            return null;
         }

         var8 = new AuraRenderer(var5, var0, (short)var6, (short)var7, var9);
         E.put(var0, var8);
      }

      return var8;
   }

   static boolean showing(ModuleDescriptor var0) {
      AuraRenderer var1 = O;
      return var1 != null && var1.L == var0 && var1.U;
   }

   static void later(float var0, Consumer<class_746> var1) {
      v.add(new AuraRenderer.TypeR(y + var0, var1));
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
         && (O == null || O == this || !O.U);
   }

   public TypeQ dock() {
      return TypeQ.RIGHT;
   }

   public boolean keepsWorldRunning() {
      return true;
   }

   public void setOpen(boolean var1) {
      long var2 = 71174639822158L;
      long var4 = var2 ^ 38441089018154L;
      long var6 = var2 ^ 65120008411573L;
      class_310 var8 = class_310.method_1551();
      if (var1 != this.e) {
         this.e = var1;
         this.n = false;
         if (var1) {
            if (O != null && O != this) {
               O.E(var6, true);
            }

            O = this;
            if (!this.U) {
               this.U = true;
            }

            AuraSupport2.open();
            this.f = false;
            this.G = -1.0;
            this.R = this.M(var4);
            this.r = -1.0;
            this.A = false;
            this.z.snap(0.0F);
            this.B.snap(0.0F);
            this.Q();
         } else {
            this.Q();
            v.clear();
            this.d = false;
            AuraSupport2.close(CosmeticRegistry.currentOwner() == null);
            if (var8.field_1724 != null) {
               this.M.closed(var8.field_1724);
            }
         }
      }
   }

   public boolean consumeCloseRequest() {
      boolean var1 = this.X;
      this.X = false;
      return var1;
   }

   private void E(long var1, boolean var3) {
      AuraStyle.ensureSourceClassInitialized();
      if (this.e) {
         long var4 = 135799582997754L;
         int var7 = var4 < 0L ? -1 : 1;
         if (!var3) {
            return;
         }
      }

      if (var3) {
         long var8 = 135799582997755L;
         int var8x = var8 < 0L ? -1 : 1;
         if (this.e) {
            this.e = false;
            this.Q();
            class_310 var7 = class_310.method_1551();
            if (var7.field_1724 != null) {
               this.M.closed(var7.field_1724);
            }

            AuraSupport2.reset();
         }
      }

      if (this.U) {
         this.U = false;
      }

      if (O == this) {
         O = null;
      }
   }

   static void tick(class_310 var0) {
      long var1 = 115981754236283L;
      long var3 = var1 ^ 20883584663936L;
      AuraRenderer var5 = O;
      if (var5 != null) {
         if (var0.field_1687 != null && var0.field_1724 != null) {
            if (var5.e && CosmeticRegistry.currentOwner() == null) {
               var5.setOpen(false);
            }

            if (!var5.e && AuraSupport2.active() && CosmeticRegistry.currentOwner() == null) {
               AuraSupport2.hurry();
            }

            if (!var5.e && !AuraSupport2.active()) {
               var5.E(var3, false);
            }
         } else {
            var5.E(var3, true);
         }
      }
   }

   private void Y(long var1) {
      AuraStyle.ensureSourceClassInitialized();
      class_310 var3 = class_310.method_1551();
      if (var3.field_1724 != null) {
         long var4 = 21004081665521L;
         int var7 = var4 < 0L ? -1 : 1;
         String var7x = this.M.note();
         long var8 = 21004081665520L;
         int var9 = var8 < 0L ? -1 : 1;
         if (var7x == null) {
            this.l = this.M.timeline();
            this.i = Math.max(0.5F, this.M.length());
            this.c = y;
            this.D = 0;
            UiAudioPolicy.play("SELECT", 1.05F, 0.7F);
            return;
         }
      }

      UiAudioPolicy.play("DENY");
   }

   private void S(AuraFactory.Data var1, long var2) {
      AuraStyle.ensureSourceClassInitialized();
      class_310 var4 = class_310.method_1551();
      if (var4.field_1724 != null && this.M.note() == null) {
         var1.action().accept(var4.field_1724);
      } else {
         UiAudioPolicy.play("DENY");
      }
   }

   private int M(long var1) {
      int var3 = 1;
      AuraStyle.ensureSourceClassInitialized();

      for (Setting var5 : this.L.settings()) {
         long var6 = 108086617115747L;
         int var10 = var6 < 0L ? -1 : 1;

         try {
            var3 = var3 * 31 + var5.save().hashCode();
         } catch (RuntimeException var9) {
            var3 *= 31;
         }
      }

      return var3;
   }

   private void Q() {
      this.c = -1.0;
      this.D = 0;
      this.l = List.of();
   }

   private void g(long var1) {
      long var3 = 108086617115748L;
      long var5 = 21004081665521L;
      AuraStyle.ensureSourceClassInitialized();
      long var7 = System.nanoTime();
      float var9 = N == 0L ? 0.0F : Math.min(0.1F, (float)(var7 - N) / 1.0E9F);
      N = var7;
      y += var9;
      class_746 var10 = class_310.method_1551().field_1724;
      if (var10 != null) {
         long var11 = 41025600113383L;
         int var26 = var11 < 0L ? -1 : 1;
         if (this.c >= 0.0) {
            for (var14 = y - this.c; this.D < this.l.size(); this.l.get(this.D++).action().accept(var10)) {
               long var16 = 41025600113384L;
               int var31 = var16 < 0L ? -1 : 1;
               if (!(this.l.get(this.D).at() <= var14)) {
                  break;
               }

               if (this.D < this.J.length) {
                  this.J[this.D] = y;
               }
            }

            long var28 = 41025600113383L;
            int var311 = var28 < 0L ? -1 : 1;
            double var19;
            int var18 = (var19 = var14 - (this.i + (this.d ? 0.9F : 0.0F))) == 0.0 ? 0 : (var19 < 0.0 ? -1 : 1);
            long var21 = 41025600113383L;
            int var24 = var21 < 0L ? -1 : 1;
            if (var18 >= 0) {
               if (this.d && this.e) {
                  this.Y(var5);
               } else if (var14 >= this.i + 1.2) {
                  this.Q();
               }
            }
         }

         Iterator var26x = v.iterator();

         while (var26x.hasNext()) {
            AuraRenderer.TypeR var15 = (AuraRenderer.TypeR)var26x.next();
            long var29 = 41025600113383L;
            int var331 = var29 < 0L ? -1 : 1;
            if (var15.I <= y) {
               var26x.remove();
               var15.L.accept(var10);
            }
         }

         long var27 = 41025600113384L;
         int var30 = var27 < 0L ? -1 : 1;
         var30 = this.M(var3);
         long var31x = 41025600113384L;
         int var35 = var31x < 0L ? -1 : 1;
         if (var30 != this.R) {
            this.R = var30;
            this.r = y;
         }

         if (this.r >= 0.0 && y - this.r >= 0.55F) {
            this.r = -1.0;
            boolean var32 = this.c >= 0.0 && y - this.c < this.i;
            if (this.e) {
               long var22 = 41025600113384L;
               int var40 = var22 < 0L ? -1 : 1;
               if (this.f) {
                  long var24 = 41025600113384L;
                  int var24x = var24 < 0L ? -1 : 1;
                  if (!var32 && this.M.note() == null) {
                     this.Y(var5);
                  }
               }
            }
         }

         long var33 = 41025600113384L;
         int var23 = var33 < 0L ? -1 : 1;
         if (this.e && AuraSupport2.settled()) {
            if (this.G < 0.0) {
               this.G = y;
            }

            if (!this.f && y - this.G >= 0.25) {
               long var23x = 41025600113384L;
               int var23x = var23x < 0L ? -1 : 1;
               this.f = true;
               if (this.c < 0.0 && this.M.note() == null) {
                  this.Y(var5);
               }
            }
         }
      }
   }

   public void render(DrawContext var1, float var2, float var3) {
      long var4 = 58039516408871L;
      long var6 = var4 ^ 118140395776315L;
      long var8 = var4 ^ 45774943214253L;
      long var10 = var4 ^ 94515033299919L;
      long var12 = var4 ^ 19350514853583L;
      long var14 = var4 ^ 137032917713867L;
      this.g(var12);
      Canvas var16 = var1.canvas;
      this.P = var3;
      this.F = var16.width();
      this.Z = var16.height();
      float var17 = 16.0F;
      float var18 = Math.max(var17 + 200.0F, var3 - 16.0F);
      float var19 = (var17 + var18) / 2.0F;
      float var20 = 110.0F;
      AuraSupport2.frame(var19 / this.F * 2.0F - 1.0F, 1.0F - (this.Z - var20) / this.Z);
      float var21 = Easing.SMOOTHSTEP.apply(p(var8, (AuraSupport2.progress() - 0.62F) / 0.38F));
      float var22 = this.z.target(this.e ? var21 : 0.0F).update(MotionStyle.MENU.dt(var1.dt)) * var2;
      if (this.n) {
         var1.claim(this.h, TypeE.MOVE);
      }

      if (var22 <= 0.01F) {
         this.x[2] = 0.0F;
      } else {
         var16.pushAlpha(var22);
         float var23 = (1.0F - var22) * 18.0F;
         this.f(var1, var16, var17, var18, var6, var23);
         this.N(var1, var10, var16, var23);
         this.v(var1, var16, var17, var22, var14);
         var16.popAlpha();
      }
   }

   private void f(DrawContext var1, Canvas var2, float var3, float var4, long var5, float var7) {
      long var10 = 93562689873765L;
      long var12 = 97889421810178L;
      long var14 = 32335466133130L;
      long var16 = 55602317036127L;
      long var18 = 69320661838507L;
      short var20 = 16139;
      short var21 = 249;
      int var22 = (int)(var18 << 40 >>> 40);
      AuraStyle.ensureSourceClassInitialized();
      String var23 = "Live preview";
      float var24 = var2.textWidth(Fonts.SEMIBOLD, var23, 9.5F);
      List var25 = this.s();
      this.w = false;
      float var26 = this.P(var16, var2, var25);
      boolean var27 = 32.0F + var24 + var26 + 10.0F > var4 - 16.0F;
      if (var27 && 26.0F + var26 + 10.0F > var4 - 16.0F) {
         this.w = true;
         var26 = this.P(var16, var2, var25);
      }

      float var28 = var27 ? 26.0F : 32.0F + var24;
      float var29 = var28 + var26 + 10.0F;
      float var30 = Math.max(16.0F, (16.0F + var4) / 2.0F - var29 / 2.0F);
      float var31 = this.Z - 22.0F - 34.0F + var7;
      this.x[0] = var30;
      this.x[1] = var31 - 24.0F - 8.0F;
      this.x[2] = var29;
      this.x[3] = 66.0F;
      var2.shadow(var30, var31, var29, 34.0F, 17.0F, 22.0F, 0.0F, 0.0F, 6.0F, 1879048192);
      var2.roundRect(var30, var31, var29, 34.0F, 17.0F, Theme.tint(-300872416));
      var2.roundRectOutlineGradient(var30, var31, var29, 34.0F, 17.0F, 1.0F, 90.0F, 788529151, 184549375);
      float var32 = var31 + 17.0F;
      float var33 = MotionStyle.reduced() ? 0.7F : 0.55F + 0.45F * (float)Math.sin(var1.time * 3.2F);
      long var34 = 105260329780508L;
      int var34x = var34 < 0L ? -1 : 1;
      var2.glowCircle(var30 + 12.0F + 6.0F, var32, 3.0F, 7.0F, BlendMode.withAlpha(Icons.PINK, 0.45F * var33));
      var2.circle(var30 + 12.0F + 6.0F, var32, 3.0F, BlendMode.lerp(Icons.MAGENTA, Icons.PINK, var33));
      if (!var27) {
         var2.text(Fonts.SEMIBOLD, var23, var30 + 12.0F + 14.0F + 6.0F, var32, 9.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      float var37x = var30 + var28;
      boolean var38 = this.c >= 0.0 && y - this.c < this.i;
      float var39 = this.H.target(var38 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));

      for (AuraRenderer.TypeJ var41 : var25) {
         float var42 = this.u(var41, var20, (byte)var21, var22) ? 14.0F : 5.0F;
         long var43 = 105260329780508L;
         int var42x = var43 < 0L ? -1 : 1;
         if (this.u(var41, var20, (byte)var21, var22)) {
            var2.rect(var37x + var42 / 2.0F - 0.5F, var31 + 9.0F, 1.0F, 16.0F, 486539263);
         }

         float var47;
         float var48;
         boolean var49;
         boolean var9;
         label219: {
            var651 = var37x + var42;
            var46 = this.B(var2, var41, var12);
            var47 = 24.0F;
            var48 = var31 + (34.0F - var47) / 2.0F;
            var41.E[0] = var651;
            var41.E[1] = var48;
            var41.E[2] = var46;
            var41.E[3] = var47;
            var49 = var41 != this.Q || AuraSupport2.moved();
            long var50 = 105260329780508L;
            var9 = (boolean)(var50 < 0L ? -1 : 1);
            if (var49) {
               long var53 = 105260329780508L;
               int var71 = var53 < 0L ? -1 : 1;
               if (!this.n) {
                  boolean var55 = var1.masked();
                  long var56 = 105260329780508L;
                  int var36 = var56 < 0L ? -1 : 1;
                  if (!var55 && a(var41.E, var1.mouseX, var1.mouseY, var10)) {
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

         float var51 = var41.t.target(var9 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         float var52 = var41.X.update(MotionStyle.TOGGLE.dt(var1.dt));
         var2.push();
         var2.scaleAround(1.0F - 0.05F * var52, var651 + var46 / 2.0F, var48 + var47 / 2.0F);
         boolean var71 = var41 == this.V && this.d;
         long var54 = 105260329780508L;
         int var36 = var54 < 0L ? -1 : 1;
         if (var41 == this.u) {
            float var57 = var47 / 2.0F;
            int var58 = BlendMode.withAlpha(Icons.VIOLET, 0.22F + 0.2F * var51 + 0.15F * var39);
            long var59 = 105260329780508L;
            int var58x = var59 < 0L ? -1 : 1;
            var2.glow(var651, var48, var46, var47, var57, 12.0F, var58);
            var2.roundRectGradient(
               var651,
               var48,
               var46,
               var47,
               var47 / 2.0F,
               0.0F,
               BlendMode.withAlpha(Icons.INDIGO, 0.88F + 0.12F * var51),
               BlendMode.withAlpha(Icons.PURPLE, 0.88F + 0.12F * var51)
            );
            if (var39 > 0.01F) {
               long var62 = 105260329780507L;
               int var65 = var62 < 0L ? -1 : 1;
               if (this.c >= 0.0) {
                  float var65x = p(var14, (float)((y - this.c) / this.i));
                  var2.pushClip(var651, var48, var46, var47, var47 / 2.0F);
                  var2.rect(var651, var48, var46 * var65x, var47, BlendMode.withAlpha(-1, 0.13F * var39));
                  long var66 = 105260329780508L;
                  int var63 = var66 < 0L ? -1 : 1;
                  var2.popClip();
               }
            }
         } else {
            long var72 = 105260329780507L;
            int var781 = var72 < 0L ? -1 : 1;
            if (var71) {
               float var60 = var47 / 2.0F;
               long var61 = 105260329780508L;
               int var78 = var61 < 0L ? -1 : 1;
               var2.roundRectGradient(
                  var651,
                  var48,
                  var46,
                  var47,
                  var60,
                  0.0F,
                  BlendMode.withAlpha(Icons.INDIGO, 0.7F + 0.2F * var51),
                  BlendMode.withAlpha(Icons.MAGENTA, 0.7F + 0.2F * var51)
               );
            } else {
               var2.roundRect(var651, var48, var46, var47, var47 / 2.0F, BlendMode.lerp(352321535, 654311423, var51));
            }
         }

         label201: {
            var2.roundRectOutline(var651, var48, var46, var47, var47 / 2.0F, 1.0F, BlendMode.lerp(452984831, 1124073471, var51));
            if (var41 != this.u) {
               long var73 = 105260329780508L;
               int var80 = var73 < 0L ? -1 : 1;
               if (!var71) {
                  var36 = var49 ? BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var51) : BlendMode.withAlpha(Icons.TEXT_3, 0.6F);
                  break label201;
               }
            }

            var36 = Icons.TEXT;
         }

         long var75 = 105260329780507L;
         int var81 = var75 < 0L ? -1 : 1;
         if (!var41.T.isEmpty() && (!this.w || !this.S.contains(var41))) {
            label185: {
               if (var41 == this.u) {
                  float var77;
                  var36 = (var77 = var39 - 0.5F) == 0.0F ? 0 : (var77 < 0.0F ? -1 : 1);
                  long var78x = 105260329780508L;
                  int var90 = var78x < 0L ? -1 : 1;
                  if (var36 > 0) {
                     var8 = "rotate-ccw";
                     break label185;
                  }
               }

               var8 = var41.p;
            }

            int var79 = var41 == this.Y ? BlendMode.lerp(Icons.PURPLE, Icons.TEXT, var51) : var36;
            var2.icon(var8, var651 + 11.0F + 5.5F, var48 + var47 / 2.0F, 11.0F, var79);
            var2.text(Fonts.MEDIUM, var41.T, var651 + 11.0F + 11.0F + 5.0F, var48 + var47 / 2.0F, 9.0F, var36, FontStyle.LEFT, FontWeight.MIDDLE);
         } else {
            var2.icon(var41.p, var651 + var46 / 2.0F, var48 + var47 / 2.0F, 12.0F, var36);
            long var76 = 105260329780507L;
            int var87 = var76 < 0L ? -1 : 1;
         }

         var2.pop();
         var37x = var651 + var46;
      }
   }

   private List<AuraRenderer.TypeJ> s() {
      ArrayList var1 = new ArrayList();
      var1.add(this.u);
      var1.add(this.V);
      var1.addAll(this.S);
      var1.add(this.Q);
      var1.add(this.Y);
      return var1;
   }

   private boolean u(AuraRenderer.TypeJ var1, int var2, byte var3, int var4) {
      AuraStyle.ensureSourceClassInitialized();
      return var1 == this.u || var1 == this.Q || !this.S.isEmpty() && var1 == this.S.getFirst();
   }

   private float P(long var1, Canvas var3, List var4) {
      long var5 = 97889421810178L;
      long var7 = 69320661838507L;
      short var9 = 16139;
      short var10 = 249;
      int var11 = (int)(var7 << 40 >>> 40);
      float var12 = 0.0F;
      AuraStyle.ensureSourceClassInitialized();

      for (AuraRenderer.TypeJ var14 : var4) {
         var12 += this.B(var3, var14, var5) + (this.u(var14, var9, (byte)var10, var11) ? 14 : 5);
      }

      return var12;
   }

   private float B(Canvas var1, AuraRenderer.TypeJ var2, long var3) {
      AuraStyle.ensureSourceClassInitialized();
      return !var2.T.isEmpty() && (!this.w || !this.S.contains(var2)) ? 27.0F + var1.textWidth(Fonts.MEDIUM, var2.T, 9.0F) + 12.0F : 28.0F;
   }

   private void N(DrawContext var1, long var2, Canvas var4, float var5) {
      long var6 = 56542638691730L;
      short var8 = 13164;
      char var9 = '\udbe4';
      int var10 = (int)(var6 << 48 >>> 48);
      long var11 = 32335466133130L;
      float var13 = this.x[0] + this.x[2] / 2.0F;
      float var14 = this.Z - 22.0F - 34.0F - 8.0F - 24.0F + var5;
      AuraStyle.ensureSourceClassInitialized();
      String var15 = this.M.note();
      if (var15 != null) {
         float var16 = var4.textWidth(Fonts.MEDIUM, var15, 8.5F);
         float var17 = var16 + 40.0F;
         float var18 = var13 - var17 / 2.0F;
         this.j(var8, var4, var18, var9, var14, var17, var10);
         var4.icon("info", var18 + 16.0F, var14 + 12.0F, 11.0F, Icons.PINK);
         var4.text(Fonts.MEDIUM, var15, var18 + 28.0F, var14 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      } else {
         List var54 = this.c >= 0.0 ? this.l : this.M.timeline();
         int var55 = var54.size();
         if (var55 != 0) {
            float var56 = 18.0F;
            float[] var19 = new float[var55];
            float var20 = 0.0F;

            for (int var21 = 0; var21 < var55; var21++) {
               var19[var21] = 12.0F + var4.textWidth(Fonts.MEDIUM, ((AuraFactory.Data2)var54.get(var21)).label(), 8.5F);
               var20 += var19[var21] + (var21 > 0 ? var56 : 0.0F);
            }

            long var57 = 106912980826088L;
            int var24 = var57 < 0L ? -1 : 1;
            float var24x = var20 + 28.0F;
            long var25 = 106912980826088L;
            int var28 = var25 < 0L ? -1 : 1;
            float var28x = this.b.value() <= 0.0F ? this.b.snap(var24x).value() : this.b.target(var24x).update(var1.dt);
            float var29 = var13 - var28x / 2.0F;
            this.T[0] = var29;
            this.T[1] = var14;
            this.T[2] = var28x;
            this.T[3] = 24.0F;
            this.j(var8, var4, var29, var9, var14, var28x, var10);
            float var30 = var14 + 12.0F;
            long var31 = 106912980826088L;
            int var31x = var31 < 0L ? -1 : 1;
            double var34x = this.c >= 0.0 ? y - this.c : -1.0;
            float var36 = var13 - var20 / 2.0F;

            for (int var37 = 0; var37 < var55; var37++) {
               AuraFactory.Data2 var38 = (AuraFactory.Data2)var54.get(var37);
               boolean var39 = var34x >= var38.at();
               long var40 = 106912980826088L;
               int var39x = var40 < 0L ? -1 : 1;
               float var42x = var39 && var37 < this.J.length ? (float)(y - this.J[var37]) : 99.0F;
               float var43 = var39 ? (float)Math.exp(-var42x / 0.18F) : 0.0F;
               float var44 = var36 + 3.5F;
               if (var37 > 0) {
                  AuraFactory.Data2 var45 = (AuraFactory.Data2)var54.get(var37 - 1);
                  long var46 = 106912980826088L;
                  int var45x = var46 < 0L ? -1 : 1;
                  float var48x = var36 - var56 + 3.0F;
                  float var49 = var36 - 3.0F;
                  var4.rect(var48x, var30 - 0.5F, var49 - var48x, 1.0F, 587202559);
                  float var50 = var45.at();
                  long var51 = 106912980826087L;
                  int var50x = var51 < 0L ? -1 : 1;
                  if (var34x >= var50) {
                     float var53x = p(var11, (float)((var34x - var45.at()) / Math.max(0.05F, var38.at() - var45.at())));
                     var4.rect(var48x, var30 - 0.75F, (var49 - var48x) * var53x, 1.5F, BlendMode.withAlpha(Icons.MAGENTA, 0.85F));
                  }
               }

               if (var43 > 0.01F) {
                  var4.glowCircle(var44, var30, 3.5F + 3.0F * var43, 9.0F, BlendMode.withAlpha(Icons.PINK, 0.55F * var43));
               }

               var4.circle(var44, var30, 3.0F + 1.2F * var43, var39 ? BlendMode.lerp(Icons.MAGENTA, -1, 0.5F * var43) : 1090519039);
               var4.text(Fonts.MEDIUM, var38.label(), var36 + 7.0F + 5.0F, var30, 8.5F, var39 ? Icons.TEXT : Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
               var36 += var19[var37] + var56;
            }
         }
      }
   }

   private void j(int var1, Canvas var2, float var3, char var4, float var5, float var6, int var7) {
      var2.shadow(var3, var5, var6, 24.0F, 12.0F, 16.0F, 0.0F, 0.0F, 4.0F, 1342177280);
      var2.roundRect(var3, var5, var6, 24.0F, 12.0F, Theme.tint(-703525600));
      var2.roundRectOutline(var3, var5, var6, 24.0F, 12.0F, 1.0F, 452984831);
   }

   private void v(DrawContext var1, Canvas var2, float var3, float var4, long var5) {
      short var7;
      char var8;
      int var9;
      boolean var22;
      label44: {
         long var11 = 56542638691730L;
         var7 = 13164;
         var8 = '\udbe4';
         var9 = (int)(var11 << 48 >>> 48);
         AuraStyle.ensureSourceClassInitialized();
         if (this.e) {
            long var13 = 79611893395435L;
            int var16 = var13 < 0L ? -1 : 1;
            if (!this.A) {
               var22 = AuraSupport2.settled();
               long var16x = 79611893395435L;
               int var18 = var16x < 0L ? -1 : 1;
               if (var22 && (this.G < 0.0 || y - this.G < 9.0)) {
                  var22 = true;
                  break label44;
               }
            }
         }

         var22 = false;
      }

      float var12 = this.B.target(var22 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt) * 0.6F);
      float var25;
      int varCmp = Float.compare(var25 = var12 - 0.01F, 0.0F);
      long var14 = 79611893395436L;
      int var17 = var14 < 0L ? -1 : 1;
      if (varCmp > 0) {
         String var17x = "Drag to look around  ·  Scroll to zoom  ·  Double-click to reset";
         float var18 = var2.textWidth(Fonts.MEDIUM, var17x, 8.5F);
         float var19 = var18 + 40.0F;
         float var20 = 22.0F - (1.0F - var12) * 6.0F;
         float var21 = Math.max(16.0F, (16.0F + this.P - 16.0F) / 2.0F - var19 / 2.0F);
         var2.pushAlpha(var12 / Math.max(var4, 0.01F) * var4);
         this.j(var7, var2, var21, var8, var20, var19, var9);
         var2.icon("mouse", var21 + 16.0F, var20 + 12.0F, 11.0F, Icons.PURPLE);
         var2.text(Fonts.MEDIUM, var17x, var21 + 28.0F, var20 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         var2.popAlpha();
      }
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      long var4 = 59274778847399L;
      long var6 = var4 ^ 106587109183426L;
      if (!this.e) {
         return false;
      } else if (this.x[2] > 0.0F && a(this.x, var1, var2, var6)) {
         if (var3 == 0) {
            for (AuraRenderer.TypeJ var9 : this.s()) {
               if (a(var9.E, var1, var2, var6) && (var9 != this.Q || AuraSupport2.moved())) {
                  var9.X.snap(1.0F).target(0.0F);
                  var9.k.run();
                  return true;
               }
            }
         }

         return true;
      } else if (var3 > 1) {
         return false;
      } else {
         long var8 = System.currentTimeMillis();
         if (var3 == 0 && var8 - this.C < 320L) {
            this.C = 0L;
            if (AuraSupport2.moved()) {
               AuraSupport2.resetView();
               UiAudioPolicy.play("TAB", 1.1F);
            }

            return true;
         } else {
            this.C = var8;
            this.n = true;
            this.g = var1;
            this.I = var2;
            return true;
         }
      }
   }

   public void mouseDrag(float var1, float var2) {
      if (this.n) {
         float var3 = var1 - this.g;
         float var4 = var2 - this.I;
         this.g = var1;
         this.I = var2;
         if (var3 != 0.0F || var4 != 0.0F) {
            AuraSupport2.drag(var3, var4);
            this.A = true;
            this.C = 0L;
         }
      }
   }

   public void mouseUp(float var1, float var2, int var3) {
      this.n = false;
   }

   public boolean scroll(float var1, float var2, double var3) {
      if (this.e && var3 != 0.0) {
         AuraSupport2.zoom(var3);
         this.A = true;
         return true;
      } else {
         return false;
      }
   }

   public boolean keyPressed(int var1) {
      long var2 = 33544843214946L;
      long var4 = var2 ^ 14947033896339L;
      if (!this.e) {
         return false;
      } else {
         switch (var1) {
            case 32:
               this.u.X.snap(1.0F).target(0.0F);
               this.Y(var4);
               return true;
            case 76:
               this.V.k.run();
               return true;
            default:
               return false;
         }
      }
   }

   static AuraRenderer current() {
      return O;
   }

   String describe() {
      return String.format(
         Locale.ROOT,
         "%s open %s lent %s playing %s loop %s clock %.2f, camera: %s",
         this.L.id(),
         this.e,
         this.U,
         this.c >= 0.0 ? String.format(Locale.ROOT, "%.2f", y - this.c) : "no",
         this.d,
         y,
         AuraSupport2.describe()
      );
   }

   private static boolean a(float[] var0, float var1, float var2, long var3) {
      AuraStyle.ensureSourceClassInitialized();
      return var0[2] > 0.0F && var1 >= var0[0] && var1 <= var0[0] + var0[2] && var2 >= var0[1] && var2 <= var0[1] + var0[3];
   }

   private static float p(long var0, float var2) {
      AuraStyle.ensureSourceClassInitialized();
      return var2 < 0.0F ? 0.0F : Math.min(var2, 1.0F);
   }

   static void closeOwned() {
      long var0 = 98657039126622L;
      long var2 = var0 ^ 37624653950117L;
      if (O != null) {
         O.E(var2, true);
      }

      AuraSupport2.reset();
      E.clear();
      v.clear();
   }

   private static final class TypeJ {
      final String T;
      final String p;
      final Runnable k;
      final float[] E = new float[4];
      final Animator t = Animator.smooth(0.0F);
      final Animator X = Animator.snappy(0.0F);

      TypeJ(String var1, String var2, Runnable var3) {
         this.T = var1;
         this.p = var2;
         this.k = var3;
      }
   }

   private record TypeR(double I, Consumer<class_746> L) {
      public double at() {
         return this.I;
      }

      public Consumer<class_746> action() {
         return this.L;
      }

      public double doubleState2() {
         return this.I;
      }

      public Consumer<class_746> consumerState5() {
         return this.L;
      }
   }
}
