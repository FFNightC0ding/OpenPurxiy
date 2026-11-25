package dev.purxiy.modules.halo;

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

public final class HaloRenderer3 implements Studio {
   private static final float k = 34.0F;
   private static final float a = 24.0F;
   private static final long Y = 320L;
   private static final float M = 0.9F;
   private static final float t = 0.25F;
   private static final float n = 0.55F;
   private static final Map<ModuleDescriptor, HaloRenderer3> e = new IdentityHashMap<>();
   private static HaloRenderer3 j;
   private static final List<HaloRenderer3.TypeI> s = new ArrayList<>();
   private static double i;
   private static long l = 0L;
   private final ModuleDescriptor b;
   private final HaloFactory m;
   private boolean q;
   private boolean p;
   private boolean S;
   private boolean C;
   private double L = -1.0;
   private int O;
   private double G = -1.0;
   private List<HaloFactory.Data> P = List.of();
   private double Q = -1.0;
   private float Z;
   private int I;
   private final double[] T;
   private boolean g;
   private final HaloRenderer3.TypeR V;
   private final HaloRenderer3.TypeR y;
   private final HaloRenderer3.TypeR v;
   private final HaloRenderer3.TypeR N;
   private final List<HaloRenderer3.TypeR> o;
   private final float[] H;
   private final float[] u;
   private boolean U;
   private final Animator f;
   private final Animator r;
   private final Animator w;
   private final Animator J;
   private float X;
   private float W;
   private float R;
   private boolean F;
   private float D;
   private float d;
   private long h;
   private boolean z;
   private final Object A;

   private HaloRenderer3(ModuleDescriptor var1, int var2, int var3, HaloFactory var4) {
      HaloDesign.ensureSourceClassInitialized();
      this.Z = 1.0F;
      this.T = new double[16];
      this.o = new ArrayList<>();
      this.H = new float[4];
      this.u = new float[4];
      this.f = Animator.smooth(0.0F);
      this.r = new Animator(0.0F, 420.0F, 41.0F);
      this.w = Animator.smooth(0.0F);
      this.J = Animator.smooth(0.0F);
      this.A = new Object();
      this.b = var1;
      this.m = var4;
      this.V = new HaloRenderer3.TypeR("Play", "play", () -> this.i(61401315007493L));
      this.y = new HaloRenderer3.TypeR("Loop", "repeat", () -> {
         long var1x = 61401315007493L;
         HaloDesign.ensureSourceClassInitialized();
         boolean var3x = false;
         this.g = !this.g;
         long var4x = 89029238749646L;
         int var7 = var4x < 0L ? -1 : 1;
         UiAudioPolicy.play(this.g ? "TOGGLE_ON" : "TOGGLE_OFF");
         long var7x = 89029238749647L;
         int var8 = var7x < 0L ? -1 : 1;
         if (this.g && this.Q < 0.0) {
            this.i(var1x);
         }
      });

      for (HaloFactory.Data2 var6 : var4.K) {
         this.o.add(new HaloRenderer3.TypeR(var6.label(), var6.icon(), () -> {
            long var2x = 88051366480661L;
            byte var4x = 0;
            int var5 = 1343557227;
            int var6x = (int)(var2x << 48 >>> 48);
            this.L((char)var4x, var5, var6, (char)var6x);
         }));
      }

      this.v = new HaloRenderer3.TypeR("", "rotate-ccw", () -> {
         HaloSupport9.resetView();
         int var0 = -1130228283;
         UiAudioPolicy.play("TAB", 1.1F);
      });
      this.N = new HaloRenderer3.TypeR("Done", "check", () -> {
         this.S = true;
         int var1x = -1675041567;
         UiAudioPolicy.play("CLICK");
      });
   }

   public static HaloRenderer3 of(ModuleDescriptor var0) {
      long var1 = 53014991737354L;
      int var3 = (int)((var1 ^ 61891361244772L) >>> 32);
      int var4 = (int)((var1 ^ 61891361244772L) << 32 >>> 32);
      HaloRenderer3 var5 = e.get(var0);
      if (var5 == null) {
         HaloFactory var6 = HaloFactory.of(var0);
         if (var6 == null) {
            return null;
         }

         var5 = new HaloRenderer3(var0, var3, var4, var6);
         e.put(var0, var5);
      }

      return var5;
   }

   static boolean showing(ModuleDescriptor var0) {
      HaloRenderer3 var1 = j;
      return var1 != null && var1.b == var0 && var1.p;
   }

   static void later(float var0, Consumer<class_746> var1) {
      s.add(new HaloRenderer3.TypeI(i + var0, var1));
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
         && (j == null || j == this || !j.p);
   }

   public TypeQ dock() {
      return TypeQ.RIGHT;
   }

   public boolean keepsWorldRunning() {
      return true;
   }

   public void setOpen(boolean var1) {
      long var2 = 127336339001441L;
      long var4 = var2 ^ 100839660002338L;
      long var6 = var2 ^ 136142777301080L;
      class_310 var8 = class_310.method_1551();
      if (var1 != this.q) {
         this.q = var1;
         this.F = false;
         if (var1) {
            if (j != null && j != this) {
               j.e(true, var6);
            }

            j = this;
            if (!this.p) {
               this.p = true;
            }

            HaloSupport9.open();
            this.C = false;
            this.L = -1.0;
            this.O = this.o(var4);
            this.G = -1.0;
            this.z = false;
            this.f.snap(0.0F);
            this.w.snap(0.0F);
            this.r();
         } else {
            this.r();
            s.clear();
            this.g = false;
            HaloSupport9.close(CosmeticRegistry.currentOwner() == null);
            if (var8.field_1724 != null) {
               this.m.closed(var8.field_1724);
            }
         }
      }
   }

   public boolean consumeCloseRequest() {
      boolean var1 = this.S;
      this.S = false;
      return var1;
   }

   private void e(boolean var1, long var2) {
      HaloDesign.ensureSourceClassInitialized();
      if (this.q) {
         long var4 = 8924818434104L;
         int var7 = var4 < 0L ? -1 : 1;
         if (!var1) {
            return;
         }
      }

      if (var1) {
         long var8 = 8924818434104L;
         int var8x = var8 < 0L ? -1 : 1;
         if (this.q) {
            this.q = false;
            this.r();
            class_310 var7 = class_310.method_1551();
            if (var7.field_1724 != null) {
               this.m.closed(var7.field_1724);
            }

            HaloSupport9.reset();
         }
      }

      if (this.p) {
         this.p = false;
      }

      if (j == this) {
         j = null;
      }
   }

   static void tick(class_310 var0) {
      long var1 = 79196214000311L;
      long var3 = var1 ^ 70483476353678L;
      HaloRenderer3 var5 = j;
      if (var5 != null) {
         if (var0.field_1687 != null && var0.field_1724 != null) {
            if (var5.q && CosmeticRegistry.currentOwner() == null) {
               var5.setOpen(false);
            }

            if (!var5.q && HaloSupport9.active() && CosmeticRegistry.currentOwner() == null) {
               HaloSupport9.hurry();
            }

            if (!var5.q && !HaloSupport9.active()) {
               var5.e(false, var3);
            }
         } else {
            var5.e(true, var3);
         }
      }
   }

   private void i(long var1) {
      HaloDesign.ensureSourceClassInitialized();
      class_310 var3 = class_310.method_1551();
      if (var3.field_1724 != null) {
         long var4 = 61401315007492L;
         int var7 = var4 < 0L ? -1 : 1;
         String var7x = this.m.note();
         long var8 = 61401315007492L;
         int var9 = var8 < 0L ? -1 : 1;
         if (var7x == null) {
            this.P = this.m.timeline();
            this.Z = Math.max(0.5F, this.m.length());
            this.Q = i;
            this.I = 0;
            UiAudioPolicy.play("SELECT", 1.05F, 0.7F);
            return;
         }
      }

      UiAudioPolicy.play("DENY");
   }

   private void L(char var1, int var2, HaloFactory.Data2 var3, char var4) {
      HaloDesign.ensureSourceClassInitialized();
      class_310 var5 = class_310.method_1551();
      if (var5.field_1724 != null && this.m.note() == null) {
         var3.action().accept(var5.field_1724);
      } else {
         UiAudioPolicy.play("DENY");
      }
   }

   private int o(long var1) {
      HaloDesign.ensureSourceClassInitialized();
      int var3 = 1;

      for (Setting var5 : this.b.settings()) {
         try {
            long var6 = 44501722582083L;
            int var8 = var6 < 0L ? -1 : 1;
            var3 = var3 * 31 + var5.save().hashCode();
         } catch (RuntimeException var91) {
            var3 *= 31;
         }
      }

      return var3;
   }

   private void r() {
      this.Q = -1.0;
      this.I = 0;
      this.P = List.of();
   }

   private void j(long var1) {
      long var3 = 61401315007493L;
      long var5 = 44501722582083L;
      HaloDesign.ensureSourceClassInitialized();
      long var7 = System.nanoTime();
      float var9 = l == 0L ? 0.0F : Math.min(0.1F, (float)(var7 - l) / 1.0E9F);
      l = var7;
      i += var9;
      class_746 var10 = class_310.method_1551().field_1724;
      if (var10 != null) {
         long var11 = 14000685483466L;
         int var27 = var11 < 0L ? -1 : 1;
         if (this.Q >= 0.0) {
            for (var14 = i - this.Q; this.I < this.P.size(); this.P.get(this.I++).action().accept(var10)) {
               long var16 = 14000685483467L;
               int var32 = var16 < 0L ? -1 : 1;
               if (!(this.P.get(this.I).at() <= var14)) {
                  break;
               }

               if (this.I < this.T.length) {
                  this.T[this.I] = i;
               }
            }

            long var29 = 14000685483467L;
            int var321 = var29 < 0L ? -1 : 1;
            double var19;
            int var18 = (var19 = var14 - (this.Z + (this.g ? 0.9F : 0.0F))) == 0.0 ? 0 : (var19 < 0.0 ? -1 : 1);
            long var21 = 14000685483467L;
            int var24 = var21 < 0L ? -1 : 1;
            if (var18 >= 0) {
               if (this.g && this.q) {
                  this.i(var3);
               } else if (var14 >= this.Z + 1.2) {
                  this.r();
               }
            }
         }

         Iterator var27x = s.iterator();

         while (var27x.hasNext()) {
            HaloRenderer3.TypeI var15 = (HaloRenderer3.TypeI)var27x.next();
            long var30 = 14000685483466L;
            int var341 = var30 < 0L ? -1 : 1;
            if (var15.k <= i) {
               var27x.remove();
               var15.z.accept(var10);
            }
         }

         long var28 = 14000685483466L;
         int var31 = var28 < 0L ? -1 : 1;
         var31 = this.o(var5);
         long var32x = 14000685483466L;
         int var36 = var32x < 0L ? -1 : 1;
         if (var31 != this.O) {
            this.O = var31;
            this.G = i;
         }

         if (this.G >= 0.0 && i - this.G >= 0.55F) {
            this.G = -1.0;
            boolean var33 = this.Q >= 0.0 && i - this.Q < this.Z;
            if (this.q) {
               long var22 = 14000685483466L;
               int var41 = var22 < 0L ? -1 : 1;
               if (this.C) {
                  long var24 = 14000685483467L;
                  int var24x = var24 < 0L ? -1 : 1;
                  if (!var33 && this.m.note() == null) {
                     this.i(var3);
                  }
               }
            }
         }

         long var34 = 14000685483467L;
         int var23 = var34 < 0L ? -1 : 1;
         if (this.q && HaloSupport9.settled()) {
            if (this.L < 0.0) {
               this.L = i;
            }

            if (!this.C) {
               long var23x = 14000685483466L;
               int var23x = var23x < 0L ? -1 : 1;
               if (i - this.L >= 0.25) {
                  long var25x = 14000685483467L;
                  int var25x = var25x < 0L ? -1 : 1;
                  this.C = true;
                  if (this.Q < 0.0 && this.m.note() == null) {
                     this.i(var3);
                  }
               }
            }
         }
      }
   }

   public void render(DrawContext var1, float var2, float var3) {
      long var4 = 89791259112357L;
      long var6 = var4 ^ 97681101170405L;
      int var8 = (int)((var4 ^ 97681101170405L) >>> 32);
      int var9 = (int)((var4 ^ 97681101170405L) << 32 >>> 56);
      int var10 = (int)(var6 << 40 >>> 40);
      long var11 = var4 ^ 77435452056396L;
      var6 = var4 ^ 113894401568055L;
      int var13 = (int)((var4 ^ 113894401568055L) >>> 32);
      int var14 = (int)((var4 ^ 113894401568055L) << 32 >>> 48);
      int var15 = (int)(var6 << 48 >>> 48);
      long var16 = var4 ^ 102331387016814L;
      long var18 = var4 ^ 118647919096411L;
      this.j(var16);
      Canvas var20 = var1.canvas;
      this.X = var3;
      this.W = var20.width();
      this.R = var20.height();
      float var21 = 16.0F;
      float var22 = Math.max(var21 + 200.0F, var3 - 16.0F);
      float var23 = (var21 + var22) / 2.0F;
      float var24 = 110.0F;
      HaloSupport9.frame(var23 / this.W * 2.0F - 1.0F, 1.0F - (this.R - var24) / this.R);
      float var25 = Easing.SMOOTHSTEP.apply(r((HaloSupport9.progress() - 0.62F) / 0.38F, var18));
      float var26 = this.f.target(this.q ? var25 : 0.0F).update(MotionStyle.MENU.dt(var1.dt)) * var2;
      if (this.F) {
         var1.claim(this.A, TypeE.MOVE);
      }

      if (var26 <= 0.01F) {
         this.H[2] = 0.0F;
      } else {
         var20.pushAlpha(var26);
         float var27 = (1.0F - var26) * 18.0F;
         this.Y(var1, var20, var8, var21, (byte)var9, var22, var27, var10);
         this.D(var13, var1, (char)var14, var20, var27, (char)var15);
         this.u(var1, var20, var11, var21, var26);
         var20.popAlpha();
      }
   }

   private void Y(DrawContext var1, Canvas var2, int var3, float var4, byte var5, float var6, float var7, int var8) {
      long var11 = 10432732222784L;
      long var13 = var11 ^ 120164528171960L;
      long var15 = var11 ^ 77046418395253L;
      long var17 = var11 ^ 17443198560936L;
      long var19 = var11 ^ 86043467054811L;
      long var21 = var11 ^ 56349841167550L;
      String var23 = "Live preview";
      float var24 = var2.textWidth(Fonts.SEMIBOLD, var23, 9.5F);
      HaloDesign.ensureSourceClassInitialized();
      List var25 = this.I();
      this.U = false;
      float var26 = this.h(var2, var17, var25);
      boolean var27 = 32.0F + var24 + var26 + 10.0F > var6 - 16.0F;
      if (var27 && 26.0F + var26 + 10.0F > var6 - 16.0F) {
         this.U = true;
         var26 = this.h(var2, var17, var25);
      }

      float var28 = var27 ? 26.0F : 32.0F + var24;
      float var29 = var28 + var26 + 10.0F;
      float var30 = Math.max(16.0F, (16.0F + var6) / 2.0F - var29 / 2.0F);
      float var31 = this.R - 22.0F - 34.0F + var7;
      this.H[0] = var30;
      this.H[1] = var31 - 24.0F - 8.0F;
      this.H[2] = var29;
      this.H[3] = 66.0F;
      var2.shadow(var30, var31, var29, 34.0F, 17.0F, 22.0F, 0.0F, 0.0F, 6.0F, 1879048192);
      var2.roundRect(var30, var31, var29, 34.0F, 17.0F, Theme.tint(-300872416));
      var2.roundRectOutlineGradient(var30, var31, var29, 34.0F, 17.0F, 1.0F, 90.0F, 788529151, 184549375);
      float var32 = var31 + 17.0F;
      float var33 = MotionStyle.reduced() ? 0.7F : 0.55F + 0.45F * (float)Math.sin(var1.time * 3.2F);
      int var34 = 2429;
      var2.glowCircle(var30 + 12.0F + 6.0F, var32, 3.0F, 7.0F, BlendMode.withAlpha(Icons.PINK, 0.45F * var33));
      var2.circle(var30 + 12.0F + 6.0F, var32, 3.0F, BlendMode.lerp(Icons.MAGENTA, Icons.PINK, var33));
      if (!var27) {
         var2.text(Fonts.SEMIBOLD, var23, var30 + 12.0F + 14.0F + 6.0F, var32, 9.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      float var35 = var30 + var28;
      boolean var36 = this.Q >= 0.0 && i - this.Q < this.Z;
      float var37 = this.J.target(var36 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));

      for (HaloRenderer3.TypeR var39 : var25) {
         float var40 = this.Q(var39, var13) ? 14.0F : 5.0F;
         if (this.Q(var39, var13)) {
            var2.rect(var35 + var40 / 2.0F - 0.5F, var31 + 9.0F, 1.0F, 16.0F, 486539263);
         }

         boolean var10;
         float var41;
         float var42;
         float var43;
         boolean var44;
         label144: {
            var35 += var40;
            var41 = this.q(var2, var15, var39);
            var42 = 24.0F;
            var43 = var31 + (34.0F - var42) / 2.0F;
            var39.f[0] = var35;
            var39.f[1] = var43;
            var39.f[2] = var41;
            var39.f[3] = var42;
            var44 = var39 != this.v || HaloSupport9.moved();
            short var45 = 2429;
            if (var44) {
               var45 = 2429;
               if (!this.F) {
                  boolean var46 = var1.masked();
                  var45 = 2429;
                  if (!var46 && X(var39.f, var1.mouseX, var1.mouseY, var19)) {
                     var10 = true;
                     break label144;
                  }
               }
            }

            var10 = false;
         }

         if (var10) {
            var1.claim(var39, TypeE.HAND);
         }

         float var58 = var39.h.target(var10 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         float var47 = var39.l.update(MotionStyle.TOGGLE.dt(var1.dt));
         var2.push();
         var2.scaleAround(1.0F - 0.05F * var47, var35 + var41 / 2.0F, var43 + var42 / 2.0F);
         boolean var48 = var39 == this.y && this.g;
         int var49 = 15;
         if (var39 == this.V) {
            var2.glow(var35, var43, var41, var42, var42 / 2.0F, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.22F + 0.2F * var58 + 0.15F * var37));
            var2.roundRectGradient(
               var35,
               var43,
               var41,
               var42,
               var42 / 2.0F,
               0.0F,
               BlendMode.withAlpha(Icons.INDIGO, 0.88F + 0.12F * var58),
               BlendMode.withAlpha(Icons.PURPLE, 0.88F + 0.12F * var58)
            );
            if (var37 > 0.01F && this.Q >= 0.0) {
               float var50 = r((float)((i - this.Q) / this.Z), var21);
               var2.pushClip(var35, var43, var41, var42, var42 / 2.0F);
               int var56 = 15;
               var2.rect(var35, var43, var41 * var50, var42, BlendMode.withAlpha(-1, 0.13F * var37));
               var2.popClip();
            }
         } else {
            int var57 = 2429;
            if (var48) {
               var2.roundRectGradient(
                  var35,
                  var43,
                  var41,
                  var42,
                  var42 / 2.0F,
                  0.0F,
                  BlendMode.withAlpha(Icons.INDIGO, 0.7F + 0.2F * var58),
                  BlendMode.withAlpha(Icons.MAGENTA, 0.7F + 0.2F * var58)
               );
            } else {
               var2.roundRect(var35, var43, var41, var42, var42 / 2.0F, BlendMode.lerp(352321535, 654311423, var58));
            }
         }

         var2.roundRectOutline(var35, var43, var41, var42, var42 / 2.0F, 1.0F, BlendMode.lerp(452984831, 1124073471, var58));
         if (var39 != this.V && !var48) {
            var34 = var44 ? BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var58) : BlendMode.withAlpha(Icons.TEXT_3, 0.6F);
         } else {
            var34 = Icons.TEXT;
         }

         var49 = 5002560;
         if (!var39.c.isEmpty() && (!this.U || !this.o.contains(var39))) {
            String var9;
            label114: {
               if (var39 == this.V) {
                  float var51;
                  var34 = (var51 = var37 - 0.5F) == 0.0F ? 0 : (var51 < 0.0F ? -1 : 1);
                  if (var34 > 0) {
                     var9 = "rotate-ccw";
                     break label114;
                  }
               }

               var9 = var39.I;
            }

            int var52 = var39 == this.N ? BlendMode.lerp(Icons.PURPLE, Icons.TEXT, var58) : var34;
            var2.icon(var9, var35 + 11.0F + 5.5F, var43 + var42 / 2.0F, 11.0F, var52);
            var2.text(Fonts.MEDIUM, var39.c, var35 + 11.0F + 11.0F + 5.0F, var43 + var42 / 2.0F, 9.0F, var34, FontStyle.LEFT, FontWeight.MIDDLE);
         } else {
            var2.icon(var39.I, var35 + var41 / 2.0F, var43 + var42 / 2.0F, 12.0F, var34);
            var49 = 5002560;
         }

         var2.pop();
         var35 += var41;
      }
   }

   private List<HaloRenderer3.TypeR> I() {
      ArrayList var1 = new ArrayList();
      var1.add(this.V);
      var1.add(this.y);
      var1.addAll(this.o);
      var1.add(this.v);
      var1.add(this.N);
      return var1;
   }

   private boolean Q(HaloRenderer3.TypeR var1, long var2) {
      HaloDesign.ensureSourceClassInitialized();
      return var1 == this.V || var1 == this.v || !this.o.isEmpty() && var1 == this.o.getFirst();
   }

   private float h(Canvas var1, long var2, List var4) {
      long var5 = 87341568492853L;
      long var7 = 110178582691576L;
      HaloDesign.ensureSourceClassInitialized();
      float var9 = 0.0F;

      for (HaloRenderer3.TypeR var11 : var4) {
         var9 += this.q(var1, var5, var11) + (this.Q(var11, var7) ? 14 : 5);
      }

      return var9;
   }

   private float q(Canvas var1, long var2, HaloRenderer3.TypeR var4) {
      HaloDesign.ensureSourceClassInitialized();
      return !var4.c.isEmpty() && (!this.U || !this.o.contains(var4)) ? 27.0F + var1.textWidth(Fonts.MEDIUM, var4.c, 9.0F) + 12.0F : 28.0F;
   }

   private void D(int var1, DrawContext var2, char var3, Canvas var4, float var5, char var6) {
      long var7 = 59632185941650L;
      long var9 = var7 ^ 17184401434140L;
      long var11 = var7 ^ 13738598653804L;
      float var13 = this.H[0] + this.H[2] / 2.0F;
      HaloDesign.ensureSourceClassInitialized();
      float var14 = this.R - 22.0F - 34.0F - 8.0F - 24.0F + var5;
      String var15 = this.m.note();
      if (var15 != null) {
         float var16 = var4.textWidth(Fonts.MEDIUM, var15, 8.5F);
         float var17 = var16 + 40.0F;
         float var18 = var13 - var17 / 2.0F;
         this.c(var4, var9, var18, var14, var17);
         var4.icon("info", var18 + 16.0F, var14 + 12.0F, 11.0F, Icons.PINK);
         var4.text(Fonts.MEDIUM, var15, var18 + 28.0F, var14 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      } else {
         List var40 = this.Q >= 0.0 ? this.P : this.m.timeline();
         int var41 = var40.size();
         if (var41 != 0) {
            float var42 = 18.0F;
            float[] var19 = new float[var41];
            float var20 = 0.0F;

            for (int var21 = 0; var21 < var41; var21++) {
               var19[var21] = 12.0F + var4.textWidth(Fonts.MEDIUM, ((HaloFactory.Data)var40.get(var21)).label(), 8.5F);
               var20 += var19[var21] + (var21 > 0 ? var42 : 0.0F);
            }

            short var43 = 13884;
            float var22 = var20 + 28.0F;
            var43 = 13884;
            float var23 = this.r.value() <= 0.0F ? this.r.snap(var22).value() : this.r.target(var22).update(var2.dt);
            float var24 = var13 - var23 / 2.0F;
            this.u[0] = var24;
            this.u[1] = var14;
            this.u[2] = var23;
            this.u[3] = 24.0F;
            this.c(var4, var9, var24, var14, var23);
            float var25 = var14 + 12.0F;
            short var26 = 13122;
            double var27 = this.Q >= 0.0 ? i - this.Q : -1.0;
            float var29 = var13 - var20 / 2.0F;

            for (int var30 = 0; var30 < var41; var30++) {
               HaloFactory.Data var31 = (HaloFactory.Data)var40.get(var30);
               boolean var32 = var27 >= var31.at();
               var26 = 13884;
               float var33 = var32 && var30 < this.T.length ? (float)(i - this.T[var30]) : 99.0F;
               float var34 = var32 ? (float)Math.exp(-var33 / 0.18F) : 0.0F;
               float var35 = var29 + 3.5F;
               if (var30 > 0) {
                  HaloFactory.Data var36 = (HaloFactory.Data)var40.get(var30 - 1);
                  var26 = 13122;
                  float var37 = var29 - var42 + 3.0F;
                  float var38 = var29 - 3.0F;
                  var4.rect(var37, var25 - 0.5F, var38 - var37, 1.0F, 587202559);
                  if (var27 >= var36.at()) {
                     float var39 = r((float)((var27 - var36.at()) / Math.max(0.05F, var31.at() - var36.at())), var11);
                     var4.rect(var37, var25 - 0.75F, (var38 - var37) * var39, 1.5F, BlendMode.withAlpha(Icons.MAGENTA, 0.85F));
                  }
               }

               if (var34 > 0.01F) {
                  var4.glowCircle(var35, var25, 3.5F + 3.0F * var34, 9.0F, BlendMode.withAlpha(Icons.PINK, 0.55F * var34));
               }

               var4.circle(var35, var25, 3.0F + 1.2F * var34, var32 ? BlendMode.lerp(Icons.MAGENTA, -1, 0.5F * var34) : 1090519039);
               var4.text(Fonts.MEDIUM, var31.label(), var29 + 7.0F + 5.0F, var25, 8.5F, var32 ? Icons.TEXT : Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
               var29 += var19[var30] + var42;
            }
         }
      }
   }

   private void c(Canvas var1, long var2, float var4, float var5, float var6) {
      var1.shadow(var4, var5, var6, 24.0F, 12.0F, 16.0F, 0.0F, 0.0F, 4.0F, 1342177280);
      var1.roundRect(var4, var5, var6, 24.0F, 12.0F, Theme.tint(-703525600));
      var1.roundRectOutline(var4, var5, var6, 24.0F, 12.0F, 1.0F, 452984831);
   }

   private void u(DrawContext var1, Canvas var2, long var3, float var5, float var6) {
      long var7;
      boolean var21;
      label44: {
         var7 = 63347502758030L;
         HaloDesign.ensureSourceClassInitialized();
         if (this.q) {
            long var10 = 26145353797865L;
            int varCmp = var10 < 0L ? -1 : 1;
            if (!this.z) {
               var21 = HaloSupport9.settled();
               long var13 = 26145353797864L;
               int var14 = var13 < 0L ? -1 : 1;
               if (var21 && (this.L < 0.0 || i - this.L < 9.0)) {
                  var21 = true;
                  break label44;
               }
            }
         }

         var21 = false;
      }

      float var11 = this.w.target(var21 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt) * 0.6F);
      float var12;
      int varCmp = Float.compare(var12 = var11 - 0.01F, 0.0F);
      long var24 = 26145353797864L;
      int var16 = var24 < 0L ? -1 : 1;
      if (varCmp > 0) {
         String var16x = "Drag to look around  ·  Scroll to zoom  ·  Double-click to reset";
         float var17 = var2.textWidth(Fonts.MEDIUM, var16x, 8.5F);
         float var18 = var17 + 40.0F;
         float var19 = 22.0F - (1.0F - var11) * 6.0F;
         float var20 = Math.max(16.0F, (16.0F + this.X - 16.0F) / 2.0F - var18 / 2.0F);
         var2.pushAlpha(var11 / Math.max(var6, 0.01F) * var6);
         this.c(var2, var7, var20, var19, var18);
         var2.icon("mouse", var20 + 16.0F, var19 + 12.0F, 11.0F, Icons.PURPLE);
         var2.text(Fonts.MEDIUM, var16x, var20 + 28.0F, var19 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         var2.popAlpha();
      }
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      long var4 = 65589094995016L;
      long var6 = var4 ^ 137007975170003L;
      if (!this.q) {
         return false;
      } else if (this.H[2] > 0.0F && X(this.H, var1, var2, var6)) {
         if (var3 == 0) {
            for (HaloRenderer3.TypeR var9 : this.I()) {
               if (X(var9.f, var1, var2, var6) && (var9 != this.v || HaloSupport9.moved())) {
                  var9.l.snap(1.0F).target(0.0F);
                  var9.V.run();
                  return true;
               }
            }
         }

         return true;
      } else if (var3 > 1) {
         return false;
      } else {
         long var8 = System.currentTimeMillis();
         if (var3 == 0 && var8 - this.h < 320L) {
            this.h = 0L;
            if (HaloSupport9.moved()) {
               HaloSupport9.resetView();
               UiAudioPolicy.play("TAB", 1.1F);
            }

            return true;
         } else {
            this.h = var8;
            this.F = true;
            this.D = var1;
            this.d = var2;
            return true;
         }
      }
   }

   public void mouseDrag(float var1, float var2) {
      if (this.F) {
         float var3 = var1 - this.D;
         float var4 = var2 - this.d;
         this.D = var1;
         this.d = var2;
         if (var3 != 0.0F || var4 != 0.0F) {
            HaloSupport9.drag(var3, var4);
            this.z = true;
            this.h = 0L;
         }
      }
   }

   public void mouseUp(float var1, float var2, int var3) {
      this.F = false;
   }

   public boolean scroll(float var1, float var2, double var3) {
      if (this.q && var3 != 0.0) {
         HaloSupport9.zoom(var3);
         this.z = true;
         return true;
      } else {
         return false;
      }
   }

   public boolean keyPressed(int var1) {
      long var2 = 112071331353426L;
      long var4 = var2 ^ 90390681407319L;
      if (!this.q) {
         return false;
      } else {
         switch (var1) {
            case 32:
               this.V.l.snap(1.0F).target(0.0F);
               this.i(var4);
               return true;
            case 76:
               this.y.V.run();
               return true;
            default:
               return false;
         }
      }
   }

   static HaloRenderer3 current() {
      return j;
   }

   String describe() {
      return String.format(
         Locale.ROOT,
         "%s open %s lent %s playing %s loop %s clock %.2f, camera: %s",
         this.b.id(),
         this.q,
         this.p,
         this.Q >= 0.0 ? String.format(Locale.ROOT, "%.2f", i - this.Q) : "no",
         this.g,
         i,
         HaloSupport9.describe()
      );
   }

   private static boolean X(float[] var0, float var1, float var2, long var3) {
      HaloDesign.ensureSourceClassInitialized();
      return var0[2] > 0.0F && var1 >= var0[0] && var1 <= var0[0] + var0[2] && var2 >= var0[1] && var2 <= var0[1] + var0[3];
   }

   private static float r(float var0, long var1) {
      HaloDesign.ensureSourceClassInitialized();
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   static void closeOwned() {
      long var0 = 48239081235727L;
      long var2 = var0 ^ 39318041972022L;
      if (j != null) {
         j.e(true, var2);
      }

      HaloSupport9.reset();
      e.clear();
      s.clear();
   }

   private record TypeI(double k, Consumer<class_746> z) {
      public double at() {
         return this.k;
      }

      public Consumer<class_746> action() {
         return this.z;
      }

      public double doubleState9() {
         return this.k;
      }

      public Consumer<class_746> consumerState14() {
         return this.z;
      }
   }

   private static final class TypeR {
      final String c;
      final String I;
      final Runnable V;
      final float[] f = new float[4];
      final Animator h = Animator.smooth(0.0F);
      final Animator l = Animator.snappy(0.0F);

      TypeR(String var1, String var2, Runnable var3) {
         this.c = var1;
         this.I = var2;
         this.V = var3;
      }
   }
}
