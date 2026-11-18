// Sourced By DevFFNightmare
package dev.purxiy.modules.jumpcircles;

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

public final class JumpCirclesRenderer implements Studio {
   private static final float b = 34.0F;
   private static final float T = 24.0F;
   private static final long p = 320L;
   private static final float G = 0.9F;
   private static final float Y = 0.25F;
   private static final float i = 0.55F;
   private static final Map<ModuleDescriptor, JumpCirclesRenderer> q = new IdentityHashMap<>();
   
   private static JumpCirclesRenderer B;
   private static final List<JumpCirclesRenderer.TypeN> y = new ArrayList<>();
   private static double S;
   private static long Q = 0L;
   private final ModuleDescriptor d;
   private final JumpCirclesFactory H;
   private boolean C;
   private boolean v;
   private boolean t;
   private boolean s;
   private double M;
   private int A;
   private double J;
   private List<JumpCirclesFactory.Data2> D;
   private double r;
   private float P;
   private int a;
   private final double[] h;
   private boolean F;
   private final JumpCirclesRenderer.TypeU k;
   private final JumpCirclesRenderer.TypeU E;
   private final JumpCirclesRenderer.TypeU L;
   private final JumpCirclesRenderer.TypeU w;
   private final List<JumpCirclesRenderer.TypeU> o;
   private final float[] e;
   private final float[] R;
   private boolean O;
   private final Animator Z;
   private final Animator W;
   private final Animator l;
   private final Animator N;
   private float x;
   private float I;
   private float f;
   private boolean U;
   private float X;
   private float K;
   private long V;
   private boolean c;
   private final Object n;
   public static boolean u;


   private static final long[] m = new long[]{
      2604263047951000742L,
      3711605729906620146L,
      -4555420009126788700L,
      5456044182789801209L,
      -4355841204582703096L,
      -3777619606760087867L,
      298479085380913310L,
      -674685935455385121L,
      5875740827106679040L,
      -1021958677920041667L,
      -7052770725081932386L,
      -4883538435524188708L,
      9081672567330722073L,
      -8685611475978414583L,
      6930218334088679016L,
      7490873606250630691L,
      5710459467292505532L,
      2235379403895295461L
   };
   private static final Integer[] z = new Integer[18];

   private JumpCirclesRenderer(ModuleDescriptor var1, long var2, JumpCirclesFactory var4) {
      long var5 = 137986509506137L;
      var5 = 47953094292915L;
      JumpCirclesRenderer2.ensureSourceClassInitialized();
      this.M = -1.0;
      this.J = -1.0;
      this.D = List.of();
      this.r = -1.0;
      this.P = 1.0F;
      this.h = new double[16];
      this.o = new ArrayList<>();
      this.e = new float[4];
      this.R = new float[4];
      this.Z = Animator.smooth(0.0F);
      this.W = new Animator(0.0F, 420.0F, 41.0F);
      this.l = Animator.smooth(0.0F);
      this.N = Animator.smooth(0.0F);
      this.n = new Object();
      this.d = var1;
      this.H = var4;
      this.k = new JumpCirclesRenderer.TypeU("Play", "play", () -> this.Y(132115444808547L));
      this.E = new JumpCirclesRenderer.TypeU("Loop", "repeat", () -> {
         long var1x = 132115444808547L;
         JumpCirclesRenderer2.ensureSourceClassInitialized();
         boolean var3 = false;
         this.F = !this.F;
         long var4x = 34839736032211L;
         if (34839736032211L == 0L) {
            boolean var10000x = false;
         } else {
            int var10 = var4x < 0L ? -1 : 1;
         }

         UiAudioPolicy.play(this.F ? "TOGGLE_ON" : "TOGGLE_OFF");
         long var7 = 34839736032211L;
         if (34839736032211L == 0L) {
            boolean var11 = false;
         } else {
            int var12x = var7 < 0L ? -1 : 1;
         }

         if (this.F && this.r < 0.0) {
            this.Y(var1x);
         }
      });

      for (JumpCirclesFactory.Data var8 : var4.G) {
         long var9 = 99087810314947L;
         if (99087810314947L == 0L) {
            boolean var10000 = false;
         } else {
            int var14 = var9 < 0L ? -1 : 1;
         }

         this.o.add(new JumpCirclesRenderer.TypeU(var8.label(), var8.icon(), () -> {
            long var2x = 137986509506137L;
            long var4x = (var2x ^ 22959916066311L) >>> 32;
            int var6 = (int)((var2x ^ 22959916066311L) << 32 >>> 32);
            this.W(var4x, var8, var6);
         }));
      }

      long var13 = 99087810314947L;
      if (99087810314947L == 0L) {
         boolean var15 = false;
      } else {
         int var16 = var13 < 0L ? -1 : 1;
      }

      this.L = new JumpCirclesRenderer.TypeU("", "rotate-ccw", () -> {
         long var0 = 47953094292915L;
         JumpCirclesSupport2.resetView();
         UiAudioPolicy.play("TAB", 1.1F);
      });
      this.w = new JumpCirclesRenderer.TypeU("Done", "check", () -> {
         this.t = true;
         UiAudioPolicy.play("CLICK");
      });
   }

   
   public static JumpCirclesRenderer of(ModuleDescriptor var0) {
      long var1 = 20032002566162L;
      long var3 = var1 ^ 79331231132369L;
      JumpCirclesRenderer var5 = q.get(var0);
      if (var5 == null) {
         JumpCirclesFactory var6 = JumpCirclesFactory.of(var0);
         if (var6 == null) {
            return null;
         }

         var5 = new JumpCirclesRenderer(var0, var3, var6);
         q.put(var0, var5);
      }

      return var5;
   }

   static boolean showing(ModuleDescriptor var0) {
      JumpCirclesRenderer var1 = B;
      return var1 != null && var1.d == var0 && var1.v;
   }

   static void later(float var0, Consumer<class_746> var1) {
      y.add(new JumpCirclesRenderer.TypeN(S + var0, var1));
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
         && (B == null || B == this || !B.v);
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
      long var2 = 3955695549956L;
      long var4 = var2 ^ 123179719647106L;
      long var6 = var2 ^ 114500828452496L;
      class_310 var8 = class_310.method_1551();
      if (var1 != this.C) {
         this.C = var1;
         this.U = false;
         if (var1) {
            if (B != null && B != this) {
               B.i(var4, true);
            }

            B = this;
            if (!this.v) {
               this.v = true;
            }

            JumpCirclesSupport2.open();
            this.s = false;
            this.M = -1.0;
            this.A = this.C(var6);
            this.J = -1.0;
            this.c = false;
            this.Z.snap(0.0F);
            this.l.snap(0.0F);
            this.m();
         } else {
            this.m();
            y.clear();
            this.F = false;
            JumpCirclesSupport2.close(CosmeticRegistry.currentOwner() == null);
            if (var8.field_1724 != null) {
               this.H.closed(var8.field_1724);
            }
         }
      }
   }

   @Override
   public boolean consumeCloseRequest() {
      boolean var1 = this.t;
      this.t = false;
      return var1;
   }

   private void i(long var1, boolean var3) {
      JumpCirclesRenderer2.ensureSourceClassInitialized();
      if (this.C) {
         long var4 = 127066653401477L;
         if (127066653401477L == 0L) {
            boolean var10000 = false;
         } else {
            int var9 = var4 < 0L ? -1 : 1;
         }

         if (!var3) {
            return;
         }
      }

      if (var3) {
         long var8 = 127066653401478L;
         if (127066653401478L == 0L) {
            boolean var10 = false;
         } else {
            int var11 = var8 < 0L ? -1 : 1;
         }

         if (this.C) {
            this.C = false;
            this.m();
            class_310 var7 = class_310.method_1551();
            if (var7.field_1724 != null) {
               this.H.closed(var7.field_1724);
            }

            JumpCirclesSupport2.reset();
         }
      }

      if (this.v) {
         this.v = false;
      }

      if (B == this) {
         B = null;
      }
   }

   static void tick(class_310 var0) {
      long var1 = 131112278870468L;
      long var3 = var1 ^ 5153800579138L;
      JumpCirclesRenderer var5 = B;
      if (var5 != null) {
         if (var0.field_1687 != null && var0.field_1724 != null) {
            if (var5.C && CosmeticRegistry.currentOwner() == null) {
               var5.setOpen(false);
            }

            if (!var5.C && JumpCirclesSupport2.active() && CosmeticRegistry.currentOwner() == null) {
               JumpCirclesSupport2.hurry();
            }

            if (!var5.C && !JumpCirclesSupport2.active()) {
               var5.i(var3, false);
            }
         } else {
            var5.i(var3, true);
         }
      }
   }

   private void Y(long var1) {
      JumpCirclesRenderer2.ensureSourceClassInitialized();
      class_310 var3 = class_310.method_1551();
      if (var3.field_1724 != null) {
         long var4 = 132115444808547L;
         if (132115444808547L == 0L) {
            boolean var10000 = false;
         } else {
            int var11 = var4 < 0L ? -1 : 1;
         }

         String var7 = this.H.note();
         long var8 = 132115444808547L;
         if (132115444808547L == 0L) {
            boolean var12 = false;
         } else {
            int var13 = var8 < 0L ? -1 : 1;
         }

         if (var7 == null) {
            this.D = this.H.timeline();
            this.P = Math.max(0.5F, this.H.length());
            this.r = S;
            this.a = 0;
            UiAudioPolicy.play("SELECT", 1.05F, 0.7F);
            return;
         }
      }

      UiAudioPolicy.play("DENY");
   }

   private void W(long var1, JumpCirclesFactory.Data var3, int var4) {
      JumpCirclesRenderer2.ensureSourceClassInitialized();
      class_310 var5 = class_310.method_1551();
      if (var5.field_1724 != null && this.H.note() == null) {
         var3.action().accept(var5.field_1724);
      } else {
         UiAudioPolicy.play("DENY");
      }
   }

   private int C(long var1) {
      JumpCirclesRenderer2.ensureSourceClassInitialized();
      int var3 = 1;

      for (Setting var5 : this.d.settings()) {
         try {
            int var6 = var3 * 31;
            long var7 = 118447898306708L;
            if (118447898306708L == 0L) {
               boolean var10000 = false;
            } else {
               int var11 = var7 < 0L ? -1 : 1;
            }

            var3 = var6 + var5.save().hashCode();
         } catch (RuntimeException var10) {
            var3 *= 31;
         }
      }

      return var3;
   }

   private void m() {
      this.r = -1.0;
      this.a = 0;
      this.D = List.of();
   }

   private void q(long var1) {
      long var3 = 132115444808547L;
      long var5 = 118447898306708L;
      JumpCirclesRenderer2.ensureSourceClassInitialized();
      long var7 = System.nanoTime();
      float var9 = Q == 0L ? 0.0F : Math.min(0.1F, (float)(var7 - Q) / 1.0E9F);
      Q = var7;
      S += var9;
      class_746 var10 = class_310.method_1551().field_1724;
      if (var10 != null) {
         long var11 = 61308390560147L;
         if (61308390560147L == 0L) {
            boolean var10000 = false;
         } else {
            int var34 = var11 < 0L ? -1 : 1;
         }

         if (this.r >= 0.0) {
            double var14;
            for (var14 = S - this.r; this.a < this.D.size() && this.D.get(this.a).at() <= var14; this.D.get(this.a++).action().accept(var10)) {
               if (this.a < this.h.length) {
                  this.h[this.a] = S;
               }
            }

            long var16 = 61308390560147L;
            if (61308390560147L == 0L) {
               boolean var35 = false;
            } else {
               int var36 = var16 < 0L ? -1 : 1;
            }

            double var19;
            int var18 = (var19 = var14 - (this.P + (this.F ? 0.9F : 0.0F))) == 0.0 ? 0 : (var19 < 0.0 ? -1 : 1);
            long var21 = 61308390560148L;
            if (61308390560148L == 0L) {
               boolean var37 = false;
            } else {
               int var38 = var21 < 0L ? -1 : 1;
            }

            if (var18 >= 0) {
               if (this.F && this.C) {
                  this.Y(var3);
               } else if (var14 >= this.P + 1.2) {
                  this.m();
               }
            }
         }

         Iterator var27 = y.iterator();

         while (var27.hasNext()) {
            JumpCirclesRenderer.TypeN var15 = (JumpCirclesRenderer.TypeN)var27.next();
            long var29 = 61308390560148L;
            if (61308390560148L == 0L) {
               boolean var39 = false;
            } else {
               int var40 = var29 < 0L ? -1 : 1;
            }

            if (var15.m <= S) {
               var27.remove();
               var15.h.accept(var10);
            }
         }

         long var28 = 61308390560147L;
         if (61308390560147L == 0L) {
            boolean var41 = false;
         } else {
            int var42 = var28 < 0L ? -1 : 1;
         }

         int var30 = this.C(var5);
         long var31 = 61308390560147L;
         if (61308390560147L == 0L) {
            boolean var43 = false;
         } else {
            int var44 = var31 < 0L ? -1 : 1;
         }

         if (var30 != this.A) {
            this.A = var30;
            this.J = S;
         }

         if (this.J >= 0.0 && S - this.J >= 0.55F) {
            this.J = -1.0;
            boolean var32 = this.r >= 0.0 && S - this.r < this.P;
            if (this.C) {
               long var22 = 61308390560148L;
               if (61308390560148L == 0L) {
                  boolean var45 = false;
               } else {
                  int var46 = var22 < 0L ? -1 : 1;
               }

               if (this.s) {
                  long var24 = 61308390560148L;
                  if (61308390560148L == 0L) {
                     boolean var47 = false;
                  } else {
                     int var48 = var24 < 0L ? -1 : 1;
                  }

                  if (!var32 && this.H.note() == null) {
                     this.Y(var3);
                  }
               }
            }
         }

         long var33 = 61308390560148L;
         if (61308390560148L == 0L) {
            boolean var49 = false;
         } else {
            int var50 = var33 < 0L ? -1 : 1;
         }

         if (this.C && JumpCirclesSupport2.settled()) {
            if (this.M < 0.0) {
               this.M = S;
            }

            if (!this.s) {
               long var23 = 61308390560147L;
               if (61308390560147L == 0L) {
                  boolean var51 = false;
               } else {
                  int var52 = var23 < 0L ? -1 : 1;
               }

               if (S - this.M >= 0.25) {
                  long var25 = 61308390560147L;
                  if (61308390560147L == 0L) {
                     boolean var53 = false;
                  } else {
                     int var54 = var25 < 0L ? -1 : 1;
                  }

                  this.s = true;
                  if (this.r < 0.0 && this.H.note() == null) {
                     this.Y(var3);
                  }
               }
            }
         }
      }
   }

   @Override
   public void render(DrawContext var1, float var2, float var3) {
      long var4 = 57841039920762L;
      long var6 = var4 ^ 131303321289622L;
      long var8 = var4 ^ 42451990878605L;
      long var10 = var4 ^ 133995897015169L;
      long var12 = var4 ^ 44274966639350L;
      long var14 = var4 ^ 3682170314734L;
      this.q(var14);
      Canvas var16 = var1.canvas;
      this.x = var3;
      this.I = var16.width();
      this.f = var16.height();
      float var17 = 16.0F;
      float var18 = Math.max(var17 + 200.0F, var3 - 16.0F);
      float var19 = (var17 + var18) / 2.0F;
      float var20 = 110.0F;
      JumpCirclesSupport2.frame(var19 / this.I * 2.0F - 1.0F, 1.0F - (this.f - var20) / this.f);
      float var21 = Easing.SMOOTHSTEP.apply(N((JumpCirclesSupport2.progress() - 0.62F) / 0.38F, var6));
      float var22 = this.Z.target(this.C ? var21 : 0.0F).update(MotionStyle.MENU.dt(var1.dt)) * var2;
      if (this.U) {
         var1.claim(this.n, DrawContext.TypeE.MOVE);
      }

      if (var22 <= 0.01F) {
         this.e[2] = 0.0F;
      } else {
         var16.pushAlpha(var22);
         float var23 = (1.0F - var22) * 18.0F;
         this.x(var1, var8, var16, var17, var18, var23);
         this.J(var10, var1, var16, var23);
         this.f(var1, var16, var17, var22, var12);
         var16.popAlpha();
      }
   }

   private void x(DrawContext var1, long var2, Canvas var4, float var5, float var6, float var7) {
      long var10 = 39519439660851L;
      long var12 = 74699236281836L;
      long var14 = 14281912324783L;
      long var16 = 94214246015918L;
      long var18 = 121306043438012L;
      String var20 = "Live preview";
      float var21 = var4.textWidth(Fonts.SEMIBOLD, var20, 9.5F);
      List var22 = this.U();
      JumpCirclesRenderer2.ensureSourceClassInitialized();
      this.O = false;
      float var23 = this.Y(var4, var16, var22);
      boolean var24 = 32.0F + var21 + var23 + 10.0F > var6 - 16.0F;
      if (var24 && 26.0F + var23 + 10.0F > var6 - 16.0F) {
         this.O = true;
         var23 = this.Y(var4, var16, var22);
      }

      float var25 = var24 ? 26.0F : 32.0F + var21;
      float var26 = var25 + var23 + 10.0F;
      float var27 = Math.max(16.0F, (16.0F + var6) / 2.0F - var26 / 2.0F);
      float var28 = this.f - 22.0F - 34.0F + var7;
      this.e[0] = var27;
      this.e[1] = var28 - 24.0F - 8.0F;
      this.e[2] = var26;
      this.e[3] = 66.0F;
      var4.shadow(var27, var28, var26, 34.0F, 17.0F, 22.0F, 0.0F, 0.0F, 6.0F, 1879048192);
      var4.roundRect(var27, var28, var26, 34.0F, 17.0F, Theme.tint(-300872416));
      var4.roundRectOutlineGradient(var27, var28, var26, 34.0F, 17.0F, 1.0F, 90.0F, 788529151, 184549375);
      float var29 = var28 + 17.0F;
      float var30 = MotionStyle.reduced() ? 0.7F : 0.55F + 0.45F * (float)Math.sin(var1.time * 3.2F);
      long var31 = 19822170953718L;
      if (19822170953718L == 0L) {
         boolean var10000 = false;
      } else {
         int var75 = var31 < 0L ? -1 : 1;
      }

      var4.glowCircle(var27 + 12.0F + 6.0F, var29, 3.0F, 7.0F, BlendMode.withAlpha(Icons.PINK, 0.45F * var30));
      var4.circle(var27 + 12.0F + 6.0F, var29, 3.0F, BlendMode.lerp(Icons.MAGENTA, Icons.PINK, var30));
      if (!var24) {
         var4.text(Fonts.SEMIBOLD, var20, var27 + 12.0F + 14.0F + 6.0F, var29, 9.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      float var34 = var27 + var25;
      boolean var35 = this.r >= 0.0 && S - this.r < this.P;
      float var36 = this.N.target(var35 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));

      for (JumpCirclesRenderer.TypeU var38 : ((Iterable<JumpCirclesRenderer.TypeU>)(Object)var22)) {
         float var39 = this.i(var18, var38) ? 14.0F : 5.0F;
         long var40 = 19822170953719L;
         if (19822170953719L == 0L) {
            boolean var76 = false;
         } else {
            int var77 = var40 < 0L ? -1 : 1;
         }

         if (this.i(var18, var38)) {
            var4.rect(var34 + var39 / 2.0F - 0.5F, var28 + 9.0F, 1.0F, 16.0F, 486539263);
         }

         var34 += var39;
         float var43 = this.X(var4, var38, var10);
         float var44 = 24.0F;
         float var45 = var28 + (34.0F - var44) / 2.0F;
         var38.T[0] = var34;
         var38.T[1] = var45;
         var38.T[2] = var43;
         var38.T[3] = var44;
         boolean var46 = var38 != this.L || JumpCirclesSupport2.moved();
         long var47 = 19822170953719L;
         if (19822170953719L == 0L) {
            boolean var78 = false;
         } else {
            int var79 = var47 < 0L ? -1 : 1;
         }

         boolean var9;
         label264: {
            if (var46) {
               long var50 = 19822170953718L;
               if (19822170953718L == 0L) {
                  boolean var80 = false;
               } else {
                  int var81 = var50 < 0L ? -1 : 1;
               }

               if (!this.U) {
                  boolean var52 = var1.masked();
                  long var53 = 19822170953719L;
                  if (19822170953719L == 0L) {
                     boolean var82 = false;
                  } else {
                     int var83 = var53 < 0L ? -1 : 1;
                  }

                  if (!var52 && e(var38.T, var14, var1.mouseX, var1.mouseY)) {
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

         float var48 = var38.t.target(var9 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         float var49 = var38.g.update(MotionStyle.TOGGLE.dt(var1.dt));
         var4.push();
         var4.scaleAround(1.0F - 0.05F * var49, var34 + var43 / 2.0F, var45 + var44 / 2.0F);
         boolean var65 = var38 == this.E && this.F;
         long var51 = 19822170953719L;
         if (19822170953719L == 0L) {
            boolean var84 = false;
         } else {
            int var85 = var51 < 0L ? -1 : 1;
         }

         if (var38 == this.k) {
            float var66 = var44 / 2.0F;
            long var54 = 19822170953719L;
            if (19822170953719L == 0L) {
               boolean var86 = false;
            } else {
               int var87 = var54 < 0L ? -1 : 1;
            }

            var4.glow(var34, var45, var43, var44, var66, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.22F + 0.2F * var48 + 0.15F * var36));
            var4.roundRectGradient(
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
               long var57 = 19822170953718L;
               if (19822170953718L == 0L) {
                  boolean var88 = false;
               } else {
                  int var89 = var57 < 0L ? -1 : 1;
               }

               if (this.r >= 0.0) {
                  float var60 = N((float)((S - this.r) / this.P), var12);
                  long var61 = 19822170953719L;
                  if (19822170953719L == 0L) {
                     boolean var90 = false;
                  } else {
                     int var91 = var61 < 0L ? -1 : 1;
                  }

                  var4.pushClip(var34, var45, var43, var44, var44 / 2.0F);
                  var4.rect(var34, var45, var43 * var60, var44, BlendMode.withAlpha(-1, 0.13F * var36));
                  var4.popClip();
               }
            }
         } else {
            long var67 = 19822170953719L;
            if (19822170953719L == 0L) {
               boolean var92 = false;
            } else {
               int var93 = var67 < 0L ? -1 : 1;
            }

            if (var65) {
               float var55 = var44 / 2.0F;
               long var56 = 19822170953719L;
               if (19822170953719L == 0L) {
                  boolean var94 = false;
               } else {
                  int var95 = var56 < 0L ? -1 : 1;
               }

               var4.roundRectGradient(
                  var34,
                  var45,
                  var43,
                  var44,
                  var55,
                  0.0F,
                  BlendMode.withAlpha(Icons.INDIGO, 0.7F + 0.2F * var48),
                  BlendMode.withAlpha(Icons.MAGENTA, 0.7F + 0.2F * var48)
               );
            } else {
               var4.roundRect(var34, var45, var43, var44, var44 / 2.0F, BlendMode.lerp(352321535, 654311423, var48));
            }
         }

         int var33;
         label246: {
            var4.roundRectOutline(var34, var45, var43, var44, var44 / 2.0F, 1.0F, BlendMode.lerp(452984831, 1124073471, var48));
            if (var38 != this.k) {
               long var68 = 19822170953719L;
               if (19822170953719L == 0L) {
                  boolean var96 = false;
               } else {
                  int var97 = var68 < 0L ? -1 : 1;
               }

               if (!var65) {
                  var33 = var46 ? BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var48) : BlendMode.withAlpha(Icons.TEXT_3, 0.6F);
                  break label246;
               }
            }

            var33 = Icons.TEXT;
         }

         long var70 = 19822170953718L;
         if (19822170953718L == 0L) {
            boolean var98 = false;
         } else {
            int var99 = var70 < 0L ? -1 : 1;
         }

         if (!var38.z.isEmpty() && (!this.O || !this.o.contains(var38))) {
            String var8;
            label230: {
               if (var38 == this.k) {
                  float var72;
                  var33 = (var72 = var36 - 0.5F) == 0.0F ? 0 : (var72 < 0.0F ? -1 : 1);
                  long var73 = 19822170953719L;
                  if (19822170953719L == 0L) {
                     boolean var102 = false;
                  } else {
                     int var103 = var73 < 0L ? -1 : 1;
                  }

                  if (var33 > 0) {
                     var8 = "rotate-ccw";
                     break label230;
                  }
               }

               var8 = var38.E;
            }

            int var74 = var38 == this.w ? BlendMode.lerp(Icons.PURPLE, Icons.TEXT, var48) : var33;
            var4.icon(var8, var34 + 11.0F + 5.5F, var45 + var44 / 2.0F, 11.0F, var74);
            var4.text(Fonts.MEDIUM, var38.z, var34 + 11.0F + 11.0F + 5.0F, var45 + var44 / 2.0F, 9.0F, var33, FontStyle.LEFT, FontWeight.MIDDLE);
         } else {
            var4.icon(var38.E, var34 + var43 / 2.0F, var45 + var44 / 2.0F, 12.0F, var33);
            long var71 = 19822170953719L;
            if (19822170953719L == 0L) {
               boolean var100 = false;
            } else {
               int var101 = var71 < 0L ? -1 : 1;
            }
         }

         var4.pop();
         var34 += var43;
      }
   }

   private List<JumpCirclesRenderer.TypeU> U() {
      ArrayList var1 = new ArrayList();
      var1.add(this.k);
      var1.add(this.E);
      var1.addAll(this.o);
      var1.add(this.L);
      var1.add(this.w);
      return var1;
   }

   private boolean i(long var1, JumpCirclesRenderer.TypeU var3) {
      JumpCirclesRenderer2.ensureSourceClassInitialized();
      return var3 == this.k || var3 == this.L || !this.o.isEmpty() && var3 == this.o.getFirst();
   }

   private float Y(Canvas var1, long var2, List var4) {
      long var5 = 39519439660851L;
      long var7 = 121306043438012L;
      JumpCirclesRenderer2.ensureSourceClassInitialized();
      float var9 = 0.0F;

      for (JumpCirclesRenderer.TypeU var11 : ((Iterable<JumpCirclesRenderer.TypeU>)(Object)var4)) {
         var9 += this.X(var1, var11, var5) + (this.i(var7, var11) ? 14 : 5);
      }

      return var9;
   }

   private float X(Canvas var1, JumpCirclesRenderer.TypeU var2, long var3) {
      JumpCirclesRenderer2.ensureSourceClassInitialized();
      return var2.z.isEmpty() || this.O && this.o.contains(var2) ? 28.0F : 27.0F + var1.textWidth(Fonts.MEDIUM, var2.z, 9.0F) + 12.0F;
   }

   private void J(long var1, DrawContext var3, Canvas var4, float var5) {
      long var6 = 74699236281836L;
      long var8 = 108877147471538L;
      JumpCirclesRenderer2.ensureSourceClassInitialized();
      float var10 = this.e[0] + this.e[2] / 2.0F;
      float var11 = this.f - 22.0F - 34.0F - 8.0F - 24.0F + var5;
      String var12 = this.H.note();
      if (var12 != null) {
         float var13 = var4.textWidth(Fonts.MEDIUM, var12, 8.5F);
         float var14 = var13 + 40.0F;
         float var15 = var10 - var14 / 2.0F;
         this.A(var4, var8, var15, var11, var14);
         var4.icon("info", var15 + 16.0F, var11 + 12.0F, 11.0F, Icons.PINK);
         var4.text(Fonts.MEDIUM, var12, var15 + 28.0F, var11 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      } else {
         List var49 = this.r >= 0.0 ? this.D : this.H.timeline();
         int var50 = var49.size();
         if (var50 != 0) {
            float var51 = 18.0F;
            float[] var16 = new float[var50];
            float var17 = 0.0F;

            for (int var18 = 0; var18 < var50; var18++) {
               var16[var18] = 12.0F + var4.textWidth(Fonts.MEDIUM, ((JumpCirclesFactory.Data2)var49.get(var18)).label(), 8.5F);
               var17 += var16[var18] + (var18 > 0 ? var51 : 0.0F);
            }

            long var52 = 84960685098490L;
            if (84960685098490L == 0L) {
               boolean var10000 = false;
            } else {
               int var53 = var52 < 0L ? -1 : 1;
            }

            float var21 = var17 + 28.0F;
            long var22 = 84960685098491L;
            if (84960685098491L == 0L) {
               boolean var54 = false;
            } else {
               int var55 = var22 < 0L ? -1 : 1;
            }

            float var24 = this.W.value() <= 0.0F ? this.W.snap(var21).value() : this.W.target(var21).update(var3.dt);
            float var25 = var10 - var24 / 2.0F;
            this.R[0] = var25;
            this.R[1] = var11;
            this.R[2] = var24;
            this.R[3] = 24.0F;
            this.A(var4, var8, var25, var11, var24);
            float var26 = var11 + 12.0F;
            long var27 = 84960685098490L;
            if (84960685098490L == 0L) {
               boolean var56 = false;
            } else {
               int var57 = var27 < 0L ? -1 : 1;
            }

            double var30 = this.r >= 0.0 ? S - this.r : -1.0;
            float var32 = var10 - var17 / 2.0F;

            for (int var33 = 0; var33 < var50; var33++) {
               JumpCirclesFactory.Data2 var34 = (JumpCirclesFactory.Data2)var49.get(var33);
               boolean var35 = var30 >= var34.at();
               long var36 = 84960685098491L;
               if (84960685098491L == 0L) {
                  boolean var58 = false;
               } else {
                  int var59 = var36 < 0L ? -1 : 1;
               }

               float var38 = var35 && var33 < this.h.length ? (float)(S - this.h[var33]) : 99.0F;
               float var39 = var35 ? (float)Math.exp(-var38 / 0.18F) : 0.0F;
               float var40 = var32 + 3.5F;
               if (var33 > 0) {
                  JumpCirclesFactory.Data2 var41 = (JumpCirclesFactory.Data2)var49.get(var33 - 1);
                  long var42 = 84960685098490L;
                  if (84960685098490L == 0L) {
                     boolean var60 = false;
                  } else {
                     int var61 = var42 < 0L ? -1 : 1;
                  }

                  float var44 = var32 - var51 + 3.0F;
                  float var45 = var32 - 3.0F;
                  var4.rect(var44, var26 - 0.5F, var45 - var44, 1.0F, 587202559);
                  long var46 = 84960685098491L;
                  if (84960685098491L == 0L) {
                     boolean var62 = false;
                  } else {
                     int var63 = var46 < 0L ? -1 : 1;
                  }

                  if (var30 >= var41.at()) {
                     float var48 = N((float)((var30 - var41.at()) / Math.max(0.05F, var34.at() - var41.at())), var6);
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

   private void A(Canvas var1, long var2, float var4, float var5, float var6) {
      var1.shadow(var4, var5, var6, 24.0F, 12.0F, 16.0F, 0.0F, 0.0F, 4.0F, 1342177280);
      var1.roundRect(var4, var5, var6, 24.0F, 12.0F, Theme.tint(-703525600));
      var1.roundRectOutline(var4, var5, var6, 24.0F, 12.0F, 1.0F, 452984831);
   }

   private void f(DrawContext var1, Canvas var2, float var3, float var4, long var5) {
      long var7;
      boolean var21;
      label71: {
         var7 = 108877147471538L;
         JumpCirclesRenderer2.ensureSourceClassInitialized();
         if (this.C) {
            long var10 = 31747241409675L;
            if (31747241409675L == 0L) {
               boolean var10000 = false;
            } else {
               int var25 = var10 < 0L ? -1 : 1;
            }

            if (!this.c) {
               var21 = JumpCirclesSupport2.settled();
               long var13 = 31747241409675L;
               if (31747241409675L == 0L) {
                  boolean var26 = false;
               } else {
                  int var27 = var13 < 0L ? -1 : 1;
               }

               if (var21 && (this.M < 0.0 || S - this.M < 9.0)) {
                  var21 = true;
                  break label71;
               }
            }
         }

         var21 = false;
      }

      float var11 = this.l.target(var21 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt) * 0.6F);
      float var12;
      int varCmp = Float.compare(var12 = var11 - 0.01F, 0.0F);
      long var24 = 31747241409675L;
      if (31747241409675L == 0L) {
         boolean var28 = false;
      } else {
         int var29 = var24 < 0L ? -1 : 1;
      }

      if (varCmp > 0) {
         String var16 = "Drag to look around  ·  Scroll to zoom  ·  Double-click to reset";
         float var17 = var2.textWidth(Fonts.MEDIUM, var16, 8.5F);
         float var18 = var17 + 40.0F;
         float var19 = 22.0F - (1.0F - var11) * 6.0F;
         float var20 = Math.max(16.0F, (16.0F + this.x - 16.0F) / 2.0F - var18 / 2.0F);
         var2.pushAlpha(var11 / Math.max(var4, 0.01F) * var4);
         this.A(var2, var7, var20, var19, var18);
         var2.icon("mouse", var20 + 16.0F, var19 + 12.0F, 11.0F, Icons.PURPLE);
         var2.text(Fonts.MEDIUM, var16, var20 + 28.0F, var19 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         var2.popAlpha();
      }
   }

   @Override
   public boolean mouseDown(float var1, float var2, int var3) {
      long var4 = 43325593023976L;
      long var6 = var4 ^ 47943694116679L;
      if (!this.C) {
         return false;
      } else if (this.e[2] > 0.0F && e(this.e, var6, var1, var2)) {
         if (var3 == 0) {
            for (JumpCirclesRenderer.TypeU var9 : this.U()) {
               if (e(var9.T, var6, var1, var2) && (var9 != this.L || JumpCirclesSupport2.moved())) {
                  var9.g.snap(1.0F).target(0.0F);
                  var9.h.run();
                  return true;
               }
            }
         }

         return true;
      } else if (var3 > 1) {
         return false;
      } else {
         long var8 = System.currentTimeMillis();
         if (var3 == 0 && var8 - this.V < 320L) {
            this.V = 0L;
            if (JumpCirclesSupport2.moved()) {
               JumpCirclesSupport2.resetView();
               UiAudioPolicy.play("TAB", 1.1F);
            }

            return true;
         } else {
            this.V = var8;
            this.U = true;
            this.X = var1;
            this.K = var2;
            return true;
         }
      }
   }

   @Override
   public void mouseDrag(float var1, float var2) {
      if (this.U) {
         float var3 = var1 - this.X;
         float var4 = var2 - this.K;
         this.X = var1;
         this.K = var2;
         if (var3 != 0.0F || var4 != 0.0F) {
            JumpCirclesSupport2.drag(var3, var4);
            this.c = true;
            this.V = 0L;
         }
      }
   }

   @Override
   public void mouseUp(float var1, float var2, int var3) {
      this.U = false;
   }

   @Override
   public boolean scroll(float var1, float var2, double var3) {
      if (this.C && var3 != 0.0) {
         JumpCirclesSupport2.zoom(var3);
         this.c = true;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean keyPressed(int var1) {
      long var2 = 38646194498529L;
      long var4 = var2 ^ 100117878788226L;
      if (!this.C) {
         return false;
      } else {
         switch (var1) {
            case 32:
               this.k.g.snap(1.0F).target(0.0F);
               this.Y(var4);
               return true;
            case 76:
               this.E.h.run();
               return true;
            default:
               return false;
         }
      }
   }

   
   static JumpCirclesRenderer current() {
      return B;
   }

   String describe() {
      return String.format(
         Locale.ROOT,
         "%s open %s lent %s playing %s loop %s clock %.2f, camera: %s",
         this.d.id(),
         this.C,
         this.v,
         this.r >= 0.0 ? String.format(Locale.ROOT, "%.2f", S - this.r) : "no",
         this.F,
         S,
         JumpCirclesSupport2.describe()
      );
   }

   private static boolean e(float[] var0, long var1, float var3, float var4) {
      JumpCirclesRenderer2.ensureSourceClassInitialized();
      float var5 = var0[2];
      long var6 = 14281912324782L;
      if (14281912324782L == 0L) {
         boolean var10000 = false;
      } else {
         int var11 = var6 < 0L ? -1 : 1;
      }

      if (var5 > 0.0F && var3 >= var0[0]) {
         long var9 = 14281912324783L;
         if (14281912324783L == 0L) {
            boolean var12 = false;
         } else {
            int var13 = var9 < 0L ? -1 : 1;
         }

         if (var3 <= var0[0] + var0[2] && var4 >= var0[1] && var4 <= var0[1] + var0[3]) {
            return true;
         }
      }

      return false;
   }

   private static float N(float var0, long var1) {
      JumpCirclesRenderer2.ensureSourceClassInitialized();
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   static void closeOwned() {
      long var0 = 85497403945394L;
      long var2 = var0 ^ 68527962263604L;
      if (B != null) {
         B.i(var2, true);
      }

      JumpCirclesSupport2.reset();
      q.clear();
      y.clear();
   }

private record TypeN(double m, Consumer<class_746> h) {
      public double at() {
         return this.m;
      }

      public Consumer<class_746> action() {
         return this.h;
      }

      public double doubleState11() {
         return this.m;
      }

      public Consumer<class_746> consumerState1() {
         return this.h;
      }
   }

   private static final class TypeU {
      final String z;
      final String E;
      final Runnable h;
      final float[] T = new float[4];
      final Animator t = Animator.smooth(0.0F);
      final Animator g = Animator.snappy(0.0F);

      TypeU(String var1, String var2, Runnable var3) {
         this.z = var1;
         this.E = var2;
         this.h = var3;
      }
   }
}
