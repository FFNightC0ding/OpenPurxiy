// Sourced By DevFFNightmare
package dev.purxiy.modules.afterglow;

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
import dev.purxiy.ui.clickgui.Studio;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.class_310;
import net.minecraft.class_746;

public final class AfterglowRenderer implements Studio {
   private static final float S = 34.0F;
   private static final float i = 24.0F;
   private static final long U = 320L;
   private static final float F = 0.9F;
   private static final float l = 0.25F;
   private static final float j = 0.55F;
   private static final Map<ModuleDescriptor, AfterglowRenderer> N = new IdentityHashMap<>();
   
   private static AfterglowRenderer X;
   private static final List<AfterglowRenderer.TypeA> E = new ArrayList<>();
   private static double w;
   private static long e = 0L;
   private final ModuleDescriptor g;
   private final AfterglowFactory R;
   private boolean P;
   private boolean k;
   private boolean D;
   private boolean x;
   private double z = -1.0;
   private int t;
   private double J = -1.0;
   private List<AfterglowFactory.Data> Q = List.of();
   private double M = -1.0;
   private float s = 1.0F;
   private int u;
   private final double[] T = new double[16];
   private boolean y;
   private final AfterglowRenderer.TypeW f;
   private final AfterglowRenderer.TypeW O;
   private final AfterglowRenderer.TypeW K;
   private final AfterglowRenderer.TypeW A;
   private final List<AfterglowRenderer.TypeW> V = new ArrayList<>();
   private final float[] c = new float[4];
   private final float[] W = new float[4];
   private boolean q;
   private final Animator L = Animator.smooth(0.0F);
   private final Animator Z = new Animator(0.0F, 420.0F, 41.0F);
   private final Animator p = Animator.smooth(0.0F);
   private final Animator n = Animator.smooth(0.0F);
   private float I;
   private float H;
   private float h;
   private boolean B;
   private float C;
   private float b;
   private long v;
   private boolean d;
   private final Object G = new Object();
   public static boolean o;

   private static final String[] m = new String[27];
   private static final long[] r = new long[]{
      -4094610214925778280L,
      -7094888987049826764L,
      5096733359862297013L,
      5120168384161813772L,
      6189585631530277124L,
      8879598347971813886L,
      -8552314067752529393L,
      8824376959460691794L,
      -2322570850063784776L,
      754737584217436703L,
      2621374234033040715L,
      -3911618924399984269L,
      -2327850315765087643L,
      8138192241072701131L,
      -7488967154250862928L,
      1622352703378133190L,
      -6558011440302004655L,
      401862757279144254L
   };
   private static final Integer[] Y = new Integer[18];

   private AfterglowRenderer(ModuleDescriptor var1, long var2, AfterglowFactory var4) {
      this.g = var1;
      this.R = var4;
      this.f = new AfterglowRenderer.TypeW("Play", "play", () -> this.y(119885006926195L));
      this.O = new AfterglowRenderer.TypeW("Loop", "repeat", () -> {
         long var1x = 119885006926195L;
         this.y = !this.y;
         long var3 = 122553144809361L;
         if (122553144809361L == 0L) {
            boolean var10000x = false;
         } else {
            int var9 = var3 < 0L ? -1 : 1;
         }

         UiAudioPolicy.play(this.y ? "TOGGLE_ON" : "TOGGLE_OFF");
         long var6x = 122553144809360L;
         if (122553144809360L == 0L) {
            boolean var10x = false;
         } else {
            int var11x = var6x < 0L ? -1 : 1;
         }

         if (this.y && this.M < 0.0) {
            this.y(var1x);
         }
      });

      for (AfterglowFactory.Data2 var6 : var4.Y) {
         long var7 = 5209175827272L;
         if (5209175827272L == 0L) {
            boolean var10000 = false;
         } else {
            int var11 = var7 < 0L ? -1 : 1;
         }

         this.V.add(new AfterglowRenderer.TypeW(var6.label(), var6.icon(), () -> {
            long var2x = 847028658734L;
            this.B(var6, var2x);
         }));
      }

      this.K = new AfterglowRenderer.TypeW("", "rotate-ccw", () -> {
         AfterglowSupport4.resetView();
         UiAudioPolicy.play("TAB", 1.1F);
      });
      long var10 = 5209175827272L;
      if (5209175827272L == 0L) {
         boolean var12 = false;
      } else {
         int var13 = var10 < 0L ? -1 : 1;
      }

      this.A = new AfterglowRenderer.TypeW("Done", "check", () -> {
         this.D = true;
         UiAudioPolicy.play("CLICK");
      });
   }

   
   public static AfterglowRenderer of(ModuleDescriptor var0) {
      long var1 = 60973055219996L;
      long var3 = var1 ^ 56937280387668L;
      AfterglowRenderer var5 = N.get(var0);
      if (var5 == null) {
         AfterglowFactory var6 = AfterglowFactory.of(var0);
         if (var6 == null) {
            return null;
         }

         var5 = new AfterglowRenderer(var0, var3, var6);
         N.put(var0, var5);
      }

      return var5;
   }

   static boolean showing(ModuleDescriptor var0) {
      AfterglowRenderer var1 = X;
      return var1 != null && var1.g == var0 && var1.k;
   }

   static void later(float var0, Consumer<class_746> var1) {
      E.add(new AfterglowRenderer.TypeA(w + var0, var1));
   }

   @Override
   public String title() {
      return "Live preview";
   }

   @Override
   public String subtitle() {
      return "Step out of yourself and watch it play on you";
   }

   @Override
   public String icon() {
      return "scan-eye";
   }

   @Override
   public String action() {
      return "Preview";
   }

   @Override
   public boolean available() {
      class_310 var1 = class_310.method_1551();
      return var1.field_1687 != null
         && var1.field_1724 != null
         && var1.field_1724.method_5805()
         && !var1.field_1724.method_7325()
         && (X == null || X == this || !X.k);
   }

   @Override
   public Studio.TypeQ dock() {
      return Studio.TypeQ.RIGHT;
   }

   @Override
   public boolean keepsWorldRunning() {
      return true;
   }

   @Override
   public void setOpen(boolean var1) {
      long var2 = 5999236004155L;
      long var4 = var2 ^ 71101596336229L;
      long var6 = var2 ^ 100212734616753L;
      class_310 var8 = class_310.method_1551();
      if (var1 != this.P) {
         this.P = var1;
         this.B = false;
         if (var1) {
            if (X != null && X != this) {
               X.W(var4, true);
            }

            X = this;
            if (!this.k) {
               this.k = true;
            }

            AfterglowSupport4.open();
            this.x = false;
            this.z = -1.0;
            this.t = this.g(var6);
            this.J = -1.0;
            this.d = false;
            this.L.snap(0.0F);
            this.p.snap(0.0F);
            this.w();
         } else {
            this.w();
            E.clear();
            this.y = false;
            AfterglowSupport4.close(CosmeticRegistry.currentOwner() == null);
            if (var8.field_1724 != null) {
               this.R.closed(var8.field_1724);
            }
         }
      }
   }

   @Override
   public boolean consumeCloseRequest() {
      boolean var1 = this.D;
      this.D = false;
      return var1;
   }

   private void W(long var1, boolean var3) {
      if (this.P) {
         long var4 = 76821650487645L;
         if (76821650487645L == 0L) {
            boolean var10000 = false;
         } else {
            int var9 = var4 < 0L ? -1 : 1;
         }

         if (!var3) {
            return;
         }
      }

      if (var3) {
         long var8 = 76821650487645L;
         if (76821650487645L == 0L) {
            boolean var10 = false;
         } else {
            int var11 = var8 < 0L ? -1 : 1;
         }

         if (this.P) {
            this.P = false;
            this.w();
            class_310 var7 = class_310.method_1551();
            if (var7.field_1724 != null) {
               this.R.closed(var7.field_1724);
            }

            AfterglowSupport4.reset();
         }
      }

      if (this.k) {
         this.k = false;
      }

      if (X == this) {
         X = null;
      }
   }

   static void tick(class_310 var0) {
      long var1 = 129929843226656L;
      long var3 = var1 ^ 57130733699454L;
      AfterglowRenderer var5 = X;
      if (var5 != null) {
         if (var0.field_1687 != null && var0.field_1724 != null) {
            if (var5.P && CosmeticRegistry.currentOwner() == null) {
               var5.setOpen(false);
            }

            if (!var5.P && AfterglowSupport4.active() && CosmeticRegistry.currentOwner() == null) {
               AfterglowSupport4.hurry();
            }

            if (!var5.P && !AfterglowSupport4.active()) {
               var5.W(var3, false);
            }
         } else {
            var5.W(var3, true);
         }
      }
   }

   private void y(long var1) {
      class_310 var3 = class_310.method_1551();
      if (var3.field_1724 != null) {
         long var4 = 119885006926195L;
         if (119885006926195L == 0L) {
            boolean var10000 = false;
         } else {
            int var11 = var4 < 0L ? -1 : 1;
         }

         String var7 = this.R.note();
         long var8 = 119885006926194L;
         if (119885006926194L == 0L) {
            boolean var12 = false;
         } else {
            int var13 = var8 < 0L ? -1 : 1;
         }

         if (var7 == null) {
            this.Q = this.R.timeline();
            this.s = Math.max(0.5F, this.R.length());
            this.M = w;
            this.u = 0;
            UiAudioPolicy.play("SELECT", 1.05F, 0.7F);
            return;
         }
      }

      UiAudioPolicy.play("DENY");
   }

   private void B(AfterglowFactory.Data2 var1, long var2) {
      class_310 var4 = class_310.method_1551();
      if (var4.field_1724 != null && this.R.note() == null) {
         var1.action().accept(var4.field_1724);
      } else {
         UiAudioPolicy.play("DENY");
      }
   }

   private int g(long var1) {
      int var3 = 1;

      for (Setting var5 : this.g.settings()) {
         try {
            long var6 = 103699135749514L;
            if (103699135749514L == 0L) {
               boolean var10000 = false;
            } else {
               int var10 = var6 < 0L ? -1 : 1;
            }

            var3 = var3 * 31 + var5.save().hashCode();
         } catch (RuntimeException var9) {
            var3 *= 31;
         }
      }

      return var3;
   }

   private void w() {
      this.M = -1.0;
      this.u = 0;
      this.Q = List.of();
   }

   private void s(long var1) {
      long var3 = 119885006926195L;
      long var5 = 103699135749514L;
      long var7 = System.nanoTime();
      float var9 = e == 0L ? 0.0F : Math.min(0.1F, (float)(var7 - e) / 1.0E9F);
      e = var7;
      w += var9;
      class_746 var10 = class_310.method_1551().field_1724;
      if (var10 != null) {
         long var11 = 98949560279467L;
         if (98949560279467L == 0L) {
            boolean var10000 = false;
         } else {
            int var38 = var11 < 0L ? -1 : 1;
         }

         if (this.M >= 0.0) {
            double var14;
            for (var14 = w - this.M; this.u < this.Q.size(); this.Q.get(this.u++).action().accept(var10)) {
               double var16 = this.Q.get(this.u).at();
               long var18 = 98949560279468L;
               if (98949560279468L == 0L) {
                  boolean var39 = false;
               } else {
                  int var40 = var18 < 0L ? -1 : 1;
               }

               if (!(var16 <= var14)) {
                  break;
               }

               if (this.u < this.T.length) {
                  this.T[this.u] = w;
               }
            }

            long var30 = 98949560279467L;
            if (98949560279467L == 0L) {
               boolean var41 = false;
            } else {
               int var42 = var30 < 0L ? -1 : 1;
            }

            double var19;
            int var32 = (var19 = var14 - (this.s + (this.y ? 0.9F : 0.0F))) == 0.0 ? 0 : (var19 < 0.0 ? -1 : 1);
            long var21 = 98949560279468L;
            if (98949560279468L == 0L) {
               boolean var43 = false;
            } else {
               int var44 = var21 < 0L ? -1 : 1;
            }

            if (var32 >= 0) {
               if (this.y && this.P) {
                  this.y(var3);
               } else if (var14 >= this.s + 1.2) {
                  this.w();
               }
            }
         }

         Iterator var28 = E.iterator();

         while (var28.hasNext()) {
            AfterglowRenderer.TypeA var15 = (AfterglowRenderer.TypeA)var28.next();
            long var31 = 98949560279468L;
            if (98949560279468L == 0L) {
               boolean var45 = false;
            } else {
               int var46 = var31 < 0L ? -1 : 1;
            }

            if (var15.J <= w) {
               var28.remove();
               var15.h.accept(var10);
            }
         }

         long var29 = 98949560279467L;
         if (98949560279467L == 0L) {
            boolean var47 = false;
         } else {
            int var48 = var29 < 0L ? -1 : 1;
         }

         int var33 = this.g(var5);
         long var34 = 98949560279468L;
         if (98949560279468L == 0L) {
            boolean var49 = false;
         } else {
            int var50 = var34 < 0L ? -1 : 1;
         }

         if (var33 != this.t) {
            this.t = var33;
            this.J = w;
         }

         if (this.J >= 0.0 && w - this.J >= 0.55F) {
            this.J = -1.0;
            long var35 = 98949560279468L;
            if (98949560279468L == 0L) {
               boolean var51 = false;
            } else {
               int var52 = var35 < 0L ? -1 : 1;
            }

            boolean var23 = this.M >= 0.0 && w - this.M < this.s;
            if (this.P) {
               long var24 = 98949560279467L;
               if (98949560279467L == 0L) {
                  boolean var53 = false;
               } else {
                  int var54 = var24 < 0L ? -1 : 1;
               }

               if (this.x) {
                  long var26 = 98949560279468L;
                  if (98949560279468L == 0L) {
                     boolean var55 = false;
                  } else {
                     int var56 = var26 < 0L ? -1 : 1;
                  }

                  if (!var23 && this.R.note() == null) {
                     this.y(var3);
                  }
               }
            }
         }

         long var36 = 98949560279468L;
         if (98949560279468L == 0L) {
            boolean var57 = false;
         } else {
            int var58 = var36 < 0L ? -1 : 1;
         }

         if (this.P && AfterglowSupport4.settled()) {
            if (this.z < 0.0) {
               this.z = w;
            }

            if (!this.x) {
               long var37 = 98949560279467L;
               if (98949560279467L == 0L) {
                  boolean var59 = false;
               } else {
                  int var60 = var37 < 0L ? -1 : 1;
               }

               if (w - this.z >= 0.25) {
                  long var25 = 98949560279467L;
                  if (98949560279467L == 0L) {
                     boolean var61 = false;
                  } else {
                     int var62 = var25 < 0L ? -1 : 1;
                  }

                  this.x = true;
                  if (this.M < 0.0 && this.R.note() == null) {
                     this.y(var3);
                  }
               }
            }
         }
      }
   }

   @Override
   public void render(DrawContext var1, float var2, float var3) {
      long var4 = 37320541543783L;
      long var6 = var4 ^ 132006373178571L;
      long var8 = var4 ^ 107111942058840L;
      long var10 = var4 ^ 110480729763292L;
      long var12 = var4 ^ 23079192719417L;
      long var14 = var4 ^ 69918376725145L;
      this.s(var6);
      Canvas var16 = var1.canvas;
      this.I = var3;
      this.H = var16.width();
      this.h = var16.height();
      float var17 = 16.0F;
      float var18 = Math.max(var17 + 200.0F, var3 - 16.0F);
      float var19 = (var17 + var18) / 2.0F;
      float var20 = 110.0F;
      AfterglowSupport4.frame(var19 / this.H * 2.0F - 1.0F, 1.0F - (this.h - var20) / this.h);
      float var21 = Easing.SMOOTHSTEP.apply(S((AfterglowSupport4.progress() - 0.62F) / 0.38F, var8));
      float var22 = this.L.target(this.P ? var21 : 0.0F).update(MotionStyle.MENU.dt(var1.dt)) * var2;
      if (this.B) {
         var1.claim(this.G, DrawContext.TypeE.MOVE);
      }

      if (var22 <= 0.01F) {
         this.c[2] = 0.0F;
      } else {
         var16.pushAlpha(var22);
         float var23 = (1.0F - var22) * 18.0F;
         this.m(var1, var16, var17, var18, var10, var23);
         this.E(var14, var1, var16, var23);
         this.X(var12, var1, var16, var17, var22);
         var16.popAlpha();
      }
   }

   private void m(DrawContext var1, Canvas var2, float var3, float var4, long var5, float var7) {
      long var10 = 30406050066585L;
      long var12 = 71596242038323L;
      long var14 = 63525694028087L;
      long var16 = 71037364399679L;
      long var18 = 88390847785807L;
      String var20 = "Live preview";
      float var21 = var2.textWidth(Fonts.SEMIBOLD, var20, 9.5F);
      List var22 = this.W();
      this.q = false;
      float var23 = this.y(var2, var12, var22);
      boolean var24 = 32.0F + var21 + var23 + 10.0F > var4 - 16.0F;
      if (var24 && 26.0F + var23 + 10.0F > var4 - 16.0F) {
         this.q = true;
         var23 = this.y(var2, var12, var22);
      }

      float var25 = var24 ? 26.0F : 32.0F + var21;
      float var26 = var25 + var23 + 10.0F;
      float var27 = Math.max(16.0F, (16.0F + var4) / 2.0F - var26 / 2.0F);
      float var28 = this.h - 22.0F - 34.0F + var7;
      this.c[0] = var27;
      this.c[1] = var28 - 24.0F - 8.0F;
      this.c[2] = var26;
      this.c[3] = 66.0F;
      var2.shadow(var27, var28, var26, 34.0F, 17.0F, 22.0F, 0.0F, 0.0F, 6.0F, 1879048192);
      var2.roundRect(var27, var28, var26, 34.0F, 17.0F, Theme.tint(-300872416));
      var2.roundRectOutlineGradient(var27, var28, var26, 34.0F, 17.0F, 1.0F, 90.0F, 788529151, 184549375);
      float var29 = var28 + 17.0F;
      float var30 = MotionStyle.reduced() ? 0.7F : 0.55F + 0.45F * (float)Math.sin(var1.time * 3.2F);
      long var31 = 76459296813243L;
      if (76459296813243L == 0L) {
         boolean var10000 = false;
      } else {
         int var74 = var31 < 0L ? -1 : 1;
      }

      var2.glowCircle(var27 + 12.0F + 6.0F, var29, 3.0F, 7.0F, BlendMode.withAlpha(Icons.PINK, 0.45F * var30));
      var2.circle(var27 + 12.0F + 6.0F, var29, 3.0F, BlendMode.lerp(Icons.MAGENTA, Icons.PINK, var30));
      if (!var24) {
         var2.text(Fonts.SEMIBOLD, var20, var27 + 12.0F + 14.0F + 6.0F, var29, 9.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      float var34 = var27 + var25;
      boolean var35 = this.M >= 0.0 && w - this.M < this.s;
      float var36 = this.n.target(var35 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));

      for (AfterglowRenderer.TypeW var38 : ((Iterable<AfterglowRenderer.TypeW>)(Object)var22)) {
         float var39 = this.C(var18, var38) ? 14.0F : 5.0F;
         long var40 = 76459296813242L;
         if (76459296813242L == 0L) {
            boolean var75 = false;
         } else {
            int var76 = var40 < 0L ? -1 : 1;
         }

         if (this.C(var18, var38)) {
            var2.rect(var34 + var39 / 2.0F - 0.5F, var28 + 9.0F, 1.0F, 16.0F, 486539263);
         }

         var34 += var39;
         float var43 = this.W(var2, var10, var38);
         float var44 = 24.0F;
         float var45 = var28 + (34.0F - var44) / 2.0F;
         var38.o[0] = var34;
         var38.o[1] = var45;
         var38.o[2] = var43;
         var38.o[3] = var44;
         boolean var46 = var38 != this.K || AfterglowSupport4.moved();
         long var47 = 76459296813242L;
         if (76459296813242L == 0L) {
            boolean var77 = false;
         } else {
            int var78 = var47 < 0L ? -1 : 1;
         }

         boolean var9;
         label264: {
            if (var46) {
               long var50 = 76459296813243L;
               if (76459296813243L == 0L) {
                  boolean var79 = false;
               } else {
                  int var80 = var50 < 0L ? -1 : 1;
               }

               if (!this.B) {
                  boolean var52 = var1.masked();
                  long var53 = 76459296813242L;
                  if (76459296813242L == 0L) {
                     boolean var81 = false;
                  } else {
                     int var82 = var53 < 0L ? -1 : 1;
                  }

                  if (!var52 && x(var38.o, var1.mouseX, var1.mouseY, var14)) {
                     var9 = true;
                     break label264;
                  }
               }
            }

            var9 = false;
         }

         if (var9) {
            var1.claim(var38, DrawContext.TypeE.HAND);
         }

         float var48 = var38.c.target(var9 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         float var49 = var38.U.update(MotionStyle.TOGGLE.dt(var1.dt));
         var2.push();
         var2.scaleAround(1.0F - 0.05F * var49, var34 + var43 / 2.0F, var45 + var44 / 2.0F);
         boolean var65 = var38 == this.O && this.y;
         long var51 = 76459296813242L;
         if (76459296813242L == 0L) {
            boolean var83 = false;
         } else {
            int var84 = var51 < 0L ? -1 : 1;
         }

         if (var38 == this.f) {
            float var66 = var44 / 2.0F;
            long var54 = 76459296813243L;
            if (76459296813243L == 0L) {
               boolean var85 = false;
            } else {
               int var86 = var54 < 0L ? -1 : 1;
            }

            var2.glow(var34, var45, var43, var44, var66, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.22F + 0.2F * var48 + 0.15F * var36));
            var2.roundRectGradient(
               var34,
               var45,
               var43,
               var44,
               var44 / 2.0F,
               0.0F,
               BlendMode.withAlpha(Icons.INDIGO, 0.88F + 0.12F * var48),
               BlendMode.withAlpha(Icons.PURPLE, 0.88F + 0.12F * var48)
            );
            if (var36 > 0.01F) {
               long var57 = 76459296813243L;
               if (76459296813243L == 0L) {
                  boolean var87 = false;
               } else {
                  int var88 = var57 < 0L ? -1 : 1;
               }

               if (this.M >= 0.0) {
                  float var60 = S((float)((w - this.M) / this.s), var16);
                  var2.pushClip(var34, var45, var43, var44, var44 / 2.0F);
                  long var61 = 76459296813242L;
                  if (76459296813242L == 0L) {
                     boolean var89 = false;
                  } else {
                     int var90 = var61 < 0L ? -1 : 1;
                  }

                  var2.rect(var34, var45, var43 * var60, var44, BlendMode.withAlpha(-1, 0.13F * var36));
                  var2.popClip();
               }
            }
         } else {
            long var67 = 76459296813243L;
            if (76459296813243L == 0L) {
               boolean var91 = false;
            } else {
               int var92 = var67 < 0L ? -1 : 1;
            }

            if (var65) {
               long var55 = 76459296813243L;
               if (76459296813243L == 0L) {
                  boolean var93 = false;
               } else {
                  int var94 = var55 < 0L ? -1 : 1;
               }

               var2.roundRectGradient(
                  var34,
                  var45,
                  var43,
                  var44,
                  var44 / 2.0F,
                  0.0F,
                  BlendMode.withAlpha(Icons.INDIGO, 0.7F + 0.2F * var48),
                  BlendMode.withAlpha(Icons.MAGENTA, 0.7F + 0.2F * var48)
               );
            } else {
               var2.roundRect(var34, var45, var43, var44, var44 / 2.0F, BlendMode.lerp(352321535, 654311423, var48));
            }
         }

         int var33;
         label246: {
            var2.roundRectOutline(var34, var45, var43, var44, var44 / 2.0F, 1.0F, BlendMode.lerp(452984831, 1124073471, var48));
            if (var38 != this.f) {
               long var68 = 76459296813243L;
               if (76459296813243L == 0L) {
                  boolean var95 = false;
               } else {
                  int var96 = var68 < 0L ? -1 : 1;
               }

               if (!var65) {
                  var33 = var46 ? BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var48) : BlendMode.withAlpha(Icons.TEXT_3, 0.6F);
                  break label246;
               }
            }

            var33 = Icons.TEXT;
         }

         long var70 = 76459296813242L;
         if (76459296813242L == 0L) {
            boolean var97 = false;
         } else {
            int var98 = var70 < 0L ? -1 : 1;
         }

         if (!var38.r.isEmpty() && (!this.q || !this.V.contains(var38))) {
            String var8;
            label230: {
               if (var38 == this.f) {
                  float var71;
                  var33 = (var71 = var36 - 0.5F) == 0.0F ? 0 : (var71 < 0.0F ? -1 : 1);
                  long var72 = 76459296813243L;
                  if (76459296813243L == 0L) {
                     boolean var101 = false;
                  } else {
                     int var102 = var72 < 0L ? -1 : 1;
                  }

                  if (var33 > 0) {
                     var8 = "rotate-ccw";
                     break label230;
                  }
               }

               var8 = var38.p;
            }

            int var73 = var38 == this.A ? BlendMode.lerp(Icons.PURPLE, Icons.TEXT, var48) : var33;
            var2.icon(var8, var34 + 11.0F + 5.5F, var45 + var44 / 2.0F, 11.0F, var73);
            var2.text(Fonts.MEDIUM, var38.r, var34 + 11.0F + 11.0F + 5.0F, var45 + var44 / 2.0F, 9.0F, var33, FontStyle.LEFT, FontWeight.MIDDLE);
         } else {
            var2.icon(var38.p, var34 + var43 / 2.0F, var45 + var44 / 2.0F, 12.0F, var33);
            long var56 = 76459296813242L;
            if (76459296813242L == 0L) {
               boolean var99 = false;
            } else {
               int var100 = var56 < 0L ? -1 : 1;
            }
         }

         var2.pop();
         var34 += var43;
      }
   }

   private List<AfterglowRenderer.TypeW> W() {
      ArrayList var1 = new ArrayList();
      var1.add(this.f);
      var1.add(this.O);
      var1.addAll(this.V);
      var1.add(this.K);
      var1.add(this.A);
      return var1;
   }

   private boolean C(long var1, AfterglowRenderer.TypeW var3) {
      return var3 == this.f || var3 == this.K || !this.V.isEmpty() && var3 == this.V.getFirst();
   }

   private float y(Canvas var1, long var2, List var4) {
      long var5 = 30406050066585L;
      long var7 = 88390847785807L;
      float var9 = 0.0F;

      for (AfterglowRenderer.TypeW var11 : ((Iterable<AfterglowRenderer.TypeW>)(Object)var4)) {
         var9 += this.W(var1, var5, var11) + (this.C(var7, var11) ? 14 : 5);
      }

      return var9;
   }

   private float W(Canvas var1, long var2, AfterglowRenderer.TypeW var4) {
      return var4.r.isEmpty() || this.q && this.V.contains(var4) ? 28.0F : 27.0F + var1.textWidth(Fonts.MEDIUM, var4.r, 9.0F) + 12.0F;
   }

   private void E(long var1, DrawContext var3, Canvas var4, float var5) {
      long var6 = 71037364399679L;
      long var8 = 75797430029611L;
      float var10 = this.c[0] + this.c[2] / 2.0F;
      float var11 = this.h - 22.0F - 34.0F - 8.0F - 24.0F + var5;
      String var12 = this.R.note();
      if (var12 != null) {
         float var13 = var4.textWidth(Fonts.MEDIUM, var12, 8.5F);
         float var14 = var13 + 40.0F;
         float var15 = var10 - var14 / 2.0F;
         this.q(var4, var15, var8, var11, var14);
         var4.icon("info", var15 + 16.0F, var11 + 12.0F, 11.0F, Icons.PINK);
         var4.text(Fonts.MEDIUM, var12, var15 + 28.0F, var11 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      } else {
         List var49 = this.M >= 0.0 ? this.Q : this.R.timeline();
         int var50 = var49.size();
         if (var50 != 0) {
            float var51 = 18.0F;
            float[] var16 = new float[var50];
            float var17 = 0.0F;

            for (int var18 = 0; var18 < var50; var18++) {
               var16[var18] = 12.0F + var4.textWidth(Fonts.MEDIUM, ((AfterglowFactory.Data)var49.get(var18)).label(), 8.5F);
               var17 += var16[var18] + (var18 > 0 ? var51 : 0.0F);
            }

            long var52 = 33425475835901L;
            if (33425475835901L == 0L) {
               boolean var10000 = false;
            } else {
               int var53 = var52 < 0L ? -1 : 1;
            }

            float var21 = var17 + 28.0F;
            long var22 = 33425475835902L;
            if (33425475835902L == 0L) {
               boolean var54 = false;
            } else {
               int var55 = var22 < 0L ? -1 : 1;
            }

            float var24 = this.Z.value() <= 0.0F ? this.Z.snap(var21).value() : this.Z.target(var21).update(var3.dt);
            float var25 = var10 - var24 / 2.0F;
            this.W[0] = var25;
            this.W[1] = var11;
            this.W[2] = var24;
            this.W[3] = 24.0F;
            this.q(var4, var25, var8, var11, var24);
            float var26 = var11 + 12.0F;
            long var27 = 33425475835901L;
            if (33425475835901L == 0L) {
               boolean var56 = false;
            } else {
               int var57 = var27 < 0L ? -1 : 1;
            }

            double var30 = this.M >= 0.0 ? w - this.M : -1.0;
            float var32 = var10 - var17 / 2.0F;

            for (int var33 = 0; var33 < var50; var33++) {
               AfterglowFactory.Data var34 = (AfterglowFactory.Data)var49.get(var33);
               boolean var35 = var30 >= var34.at();
               long var36 = 33425475835902L;
               if (33425475835902L == 0L) {
                  boolean var58 = false;
               } else {
                  int var59 = var36 < 0L ? -1 : 1;
               }

               float var38 = var35 && var33 < this.T.length ? (float)(w - this.T[var33]) : 99.0F;
               float var39 = var35 ? (float)Math.exp(-var38 / 0.18F) : 0.0F;
               float var40 = var32 + 3.5F;
               if (var33 > 0) {
                  AfterglowFactory.Data var41 = (AfterglowFactory.Data)var49.get(var33 - 1);
                  long var42 = 33425475835902L;
                  if (33425475835902L == 0L) {
                     boolean var60 = false;
                  } else {
                     int var61 = var42 < 0L ? -1 : 1;
                  }

                  float var44 = var32 - var51 + 3.0F;
                  float var45 = var32 - 3.0F;
                  var4.rect(var44, var26 - 0.5F, var45 - var44, 1.0F, 587202559);
                  long var46 = 33425475835901L;
                  if (33425475835901L == 0L) {
                     boolean var62 = false;
                  } else {
                     int var63 = var46 < 0L ? -1 : 1;
                  }

                  if (var30 >= var41.at()) {
                     float var48 = S((float)((var30 - var41.at()) / Math.max(0.05F, var34.at() - var41.at())), var6);
                     var4.rect(var44, var26 - 0.75F, (var45 - var44) * var48, 1.5F, BlendMode.withAlpha(Icons.MAGENTA, 0.85F));
                  }
               }

               if (var39 > 0.01F) {
                  var4.glowCircle(var40, var26, 3.5F + 3.0F * var39, 9.0F, BlendMode.withAlpha(Icons.PINK, 0.55F * var39));
               }

               var4.circle(var40, var26, 3.0F + 1.2F * var39, var35 ? BlendMode.lerp(Icons.MAGENTA, -1, 0.5F * var39) : 1090519039);
               var4.text(Fonts.MEDIUM, var34.label(), var32 + 7.0F + 5.0F, var26, 8.5F, var35 ? Icons.TEXT : Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
               var32 += var16[var33] + var51;
            }
         }
      }
   }

   private void q(Canvas var1, float var2, long var3, float var5, float var6) {
      var1.shadow(var2, var5, var6, 24.0F, 12.0F, 16.0F, 0.0F, 0.0F, 4.0F, 1342177280);
      var1.roundRect(var2, var5, var6, 24.0F, 12.0F, Theme.tint(-703525600));
      var1.roundRectOutline(var2, var5, var6, 24.0F, 12.0F, 1.0F, 452984831);
   }

   private void X(long var1, DrawContext var3, Canvas var4, float var5, float var6) {
      long var7;
      boolean var21;
      label71: {
         var7 = 75797430029611L;
         if (this.P) {
            long var10 = 58329255275869L;
            if (58329255275869L == 0L) {
               boolean var10000 = false;
            } else {
               int var25 = var10 < 0L ? -1 : 1;
            }

            if (!this.d) {
               var21 = AfterglowSupport4.settled();
               long var13 = 58329255275869L;
               if (58329255275869L == 0L) {
                  boolean var26 = false;
               } else {
                  int var27 = var13 < 0L ? -1 : 1;
               }

               if (var21 && (this.z < 0.0 || w - this.z < 9.0)) {
                  var21 = true;
                  break label71;
               }
            }
         }

         var21 = false;
      }

      float var11 = this.p.target(var21 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var3.dt) * 0.6F);
      float var12;
      int varCmp = Float.compare(var12 = var11 - 0.01F, 0.0F);
      long var24 = 58329255275869L;
      if (58329255275869L == 0L) {
         boolean var28 = false;
      } else {
         int var29 = var24 < 0L ? -1 : 1;
      }

      if (varCmp > 0) {
         String var16 = "Drag to look around  ·  Scroll to zoom  ·  Double-click to reset";
         float var17 = var4.textWidth(Fonts.MEDIUM, var16, 8.5F);
         float var18 = var17 + 40.0F;
         float var19 = 22.0F - (1.0F - var11) * 6.0F;
         float var20 = Math.max(16.0F, (16.0F + this.I - 16.0F) / 2.0F - var18 / 2.0F);
         var4.pushAlpha(var11 / Math.max(var6, 0.01F) * var6);
         this.q(var4, var20, var7, var19, var18);
         var4.icon("mouse", var20 + 16.0F, var19 + 12.0F, 11.0F, Icons.PURPLE);
         var4.text(Fonts.MEDIUM, var16, var20 + 28.0F, var19 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         var4.popAlpha();
      }
   }

   @Override
   public boolean mouseDown(float var1, float var2, int var3) {
      long var4 = 59059070193980L;
      long var6 = var4 ^ 13677198272523L;
      if (!this.P) {
         return false;
      } else if (this.c[2] > 0.0F && x(this.c, var1, var2, var6)) {
         if (var3 == 0) {
            for (AfterglowRenderer.TypeW var9 : this.W()) {
               if (x(var9.o, var1, var2, var6) && (var9 != this.K || AfterglowSupport4.moved())) {
                  var9.U.snap(1.0F).target(0.0F);
                  var9.w.run();
                  return true;
               }
            }
         }

         return true;
      } else if (var3 > 1) {
         return false;
      } else {
         long var8 = System.currentTimeMillis();
         if (var3 == 0 && var8 - this.v < 320L) {
            this.v = 0L;
            if (AfterglowSupport4.moved()) {
               AfterglowSupport4.resetView();
               UiAudioPolicy.play("TAB", 1.1F);
            }

            return true;
         } else {
            this.v = var8;
            this.B = true;
            this.C = var1;
            this.b = var2;
            return true;
         }
      }
   }

   @Override
   public void mouseDrag(float var1, float var2) {
      if (this.B) {
         float var3 = var1 - this.C;
         float var4 = var2 - this.b;
         this.C = var1;
         this.b = var2;
         if (var3 != 0.0F || var4 != 0.0F) {
            AfterglowSupport4.drag(var3, var4);
            this.d = true;
            this.v = 0L;
         }
      }
   }

   @Override
   public void mouseUp(float var1, float var2, int var3) {
      this.B = false;
   }

   @Override
   public boolean scroll(float var1, float var2, double var3) {
      if (this.P && var3 != 0.0) {
         AfterglowSupport4.zoom(var3);
         this.d = true;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean keyPressed(int var1) {
      long var2 = 45284156789723L;
      long var4 = var2 ^ 74936128643752L;
      if (!this.P) {
         return false;
      } else {
         switch (var1) {
            case 32:
               this.f.U.snap(1.0F).target(0.0F);
               this.y(var4);
               return true;
            case 76:
               this.O.w.run();
               return true;
            default:
               return false;
         }
      }
   }

   
   static AfterglowRenderer current() {
      return X;
   }

   String describe() {
      return String.format(
         Locale.ROOT,
         "%s open %s lent %s playing %s loop %s clock %.2f, camera: %s",
         this.g.id(),
         this.P,
         this.k,
         this.M >= 0.0 ? String.format(Locale.ROOT, "%.2f", w - this.M) : "no",
         this.y,
         w,
         AfterglowSupport4.describe()
      );
   }

   private static boolean x(float[] var0, float var1, float var2, long var3) {
      if (var0[2] > 0.0F && var1 >= var0[0]) {
         long var5 = 63525694028087L;
         if (63525694028087L == 0L) {
            boolean var10000 = false;
         } else {
            int var10 = var5 < 0L ? -1 : 1;
         }

         if (var1 <= var0[0] + var0[2]) {
            long var8 = 63525694028087L;
            if (63525694028087L == 0L) {
               boolean var11 = false;
            } else {
               int var12 = var8 < 0L ? -1 : 1;
            }

            if (var2 >= var0[1] && var2 <= var0[1] + var0[3]) {
               return true;
            }
         }
      }

      return false;
   }

   private static float S(float var0, long var1) {
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   static void closeOwned() {
      long var0 = 137552575704015L;
      long var2 = var0 ^ 62414923600529L;
      if (X != null) {
         X.W(var2, true);
      }

      AfterglowSupport4.reset();
      N.clear();
      E.clear();
   }

public static void ensureSourceClassInitialized() {
   }

private record TypeA(double J, Consumer<class_746> h) {
      public double at() {
         return this.J;
      }

      public Consumer<class_746> action() {
         return this.h;
      }

      public double doubleState6() {
         return this.J;
      }

      public Consumer<class_746> consumerState1() {
         return this.h;
      }
   }

   private static final class TypeW {
      final String r;
      final String p;
      final Runnable w;
      final float[] o = new float[4];
      final Animator c = Animator.smooth(0.0F);
      final Animator U = Animator.snappy(0.0F);

      TypeW(String var1, String var2, Runnable var3) {
         this.r = var1;
         this.p = var2;
         this.w = var3;
      }
   }
}
