// Sourced By DevFFNightmare
package dev.purxiy.modules.ripple;

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

public final class RippleRenderer implements Studio {
   private static final float Y = 34.0F;
   private static final float o = 24.0F;
   private static final long r = 320L;
   private static final float N = 0.9F;
   private static final float q = 0.25F;
   private static final float G = 0.55F;
   private static final Map<ModuleDescriptor, RippleRenderer> A = new IdentityHashMap<>();
   
   private static RippleRenderer L;
   private static final List<RippleRenderer.TypeU> j = new ArrayList<>();
   private static double C;
   private static long Z = 0L;
   private final ModuleDescriptor x;
   private final RippleFactory X;
   private boolean Q;
   private boolean p;
   private boolean F;
   private boolean T;
   private double e = -1.0;
   private int t;
   private double R = -1.0;
   private List<RippleFactory.Data2> y = List.of();
   private double g = -1.0;
   private float h = 1.0F;
   private int d;
   private final double[] W = new double[16];
   private boolean f;
   private final RippleRenderer.TypeV l;
   private final RippleRenderer.TypeV B;
   private final RippleRenderer.TypeV b;
   private final RippleRenderer.TypeV u;
   private final List<RippleRenderer.TypeV> s = new ArrayList<>();
   private final float[] a = new float[4];
   private final float[] E = new float[4];
   private boolean c;
   private final Animator n = Animator.smooth(0.0F);
   private final Animator P = new Animator(0.0F, 420.0F, 41.0F);
   private final Animator K = Animator.smooth(0.0F);
   private final Animator i = Animator.smooth(0.0F);
   private float k;
   private float J;
   private float D;
   private boolean S;
   private float M;
   private float m;
   private long O;
   private boolean U;
   private final Object V = new Object();
   public static boolean I;



   private static final Integer[] H = new Integer[18];

   private RippleRenderer(long var1, ModuleDescriptor var3, RippleFactory var4) {
      this.x = var3;
      this.X = var4;
      this.l = new RippleRenderer.TypeV("Play", "play", () -> this.t(26562, (short)-24235, (short)-15920));
      this.B = new RippleRenderer.TypeV("Loop", "repeat", () -> {
         long var1x = 114085628068304L;
         short var3x = 26562;
         char var4x = 'ꅕ';
         int var5 = (int)(var1x << 48 >>> 48);
         this.f = !this.f;
         long var6x = 85027704098420L;
         if (85027704098420L == 0L) {
            boolean var10000x = false;
         } else {
            int var14 = var6x < 0L ? -1 : 1;
         }

         UiAudioPolicy.play(this.f ? "TOGGLE_ON" : "TOGGLE_OFF");
         long var9 = 85027704098420L;
         if (85027704098420L == 0L) {
            boolean var15 = false;
         } else {
            int var16 = var9 < 0L ? -1 : 1;
         }

         if (this.f) {
            long var12x = 85027704098421L;
            if (85027704098421L == 0L) {
               boolean var17 = false;
            } else {
               int var18 = var12x < 0L ? -1 : 1;
            }

            if (this.g < 0.0) {
               this.t(var3x, (short)var4x, (short)var5);
            }
         }
      });

      for (RippleFactory.Data var6 : var4.b) {
         long var7 = 113218808070202L;
         if (113218808070202L == 0L) {
            boolean var10000 = false;
         } else {
            int var11 = var7 < 0L ? -1 : 1;
         }

         this.s.add(new RippleRenderer.TypeV(var6.label(), var6.icon(), () -> {
            long var2 = 112971764968312L;
            this.U(var6, var2);
         }));
      }

      this.b = new RippleRenderer.TypeV("", "rotate-ccw", () -> {
         RippleSupport.resetView();
         int var0 = 39482115;
         int var1x = 39468098;
         UiAudioPolicy.play("TAB", 1.1F);
      });
      long var10 = 113218808070202L;
      if (113218808070202L == 0L) {
         boolean var12 = false;
      } else {
         int var13 = var10 < 0L ? -1 : 1;
      }

      this.u = new RippleRenderer.TypeV("Done", "check", () -> {
         this.F = true;
         int var1x = 694999186;
         int var2 = 694979693;
         UiAudioPolicy.play("CLICK");
      });
   }

   
   public static RippleRenderer of(ModuleDescriptor var0) {
      long var1 = 126349213167932L;
      long var3 = var1 ^ 22064205508870L;
      RippleRenderer var5 = A.get(var0);
      if (var5 == null) {
         RippleFactory var6 = RippleFactory.of(var0);
         if (var6 == null) {
            return null;
         }

         var5 = new RippleRenderer(var3, var0, var6);
         A.put(var0, var5);
      }

      return var5;
   }

   static boolean showing(ModuleDescriptor var0) {
      RippleRenderer var1 = L;
      return var1 != null && var1.x == var0 && var1.p;
   }

   static void later(float var0, Consumer<class_746> var1) {
      j.add(new RippleRenderer.TypeU(C + var0, var1));
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
         && (L == null || L == this || !L.p);
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
      long var2 = 137876466356640L;
      long var4 = var2 ^ 108008852518559L;
      long var6 = var2 ^ 112438268365798L;
      class_310 var8 = class_310.method_1551();
      if (var1 != this.Q) {
         this.Q = var1;
         this.S = false;
         if (var1) {
            if (L != null && L != this) {
               L.e(true, var4);
            }

            L = this;
            if (!this.p) {
               this.p = true;
            }

            RippleSupport.open();
            this.T = false;
            this.e = -1.0;
            this.t = this.U(var6);
            this.R = -1.0;
            this.U = false;
            this.n.snap(0.0F);
            this.K.snap(0.0F);
            this.W();
         } else {
            this.W();
            j.clear();
            this.f = false;
            RippleSupport.close(CosmeticRegistry.currentOwner() == null);
            if (var8.field_1724 != null) {
               this.X.closed(var8.field_1724);
            }
         }
      }
   }

   @Override
   public boolean consumeCloseRequest() {
      boolean var1 = this.F;
      this.F = false;
      return var1;
   }

   private void e(boolean var1, long var2) {
      if (this.Q) {
         long var4 = 34488998799167L;
         if (34488998799167L == 0L) {
            boolean var10000 = false;
         } else {
            int var9 = var4 < 0L ? -1 : 1;
         }

         if (!var1) {
            return;
         }
      }

      if (var1) {
         long var8 = 34488998799166L;
         if (34488998799166L == 0L) {
            boolean var10 = false;
         } else {
            int var11 = var8 < 0L ? -1 : 1;
         }

         if (this.Q) {
            this.Q = false;
            this.W();
            class_310 var7 = class_310.method_1551();
            if (var7.field_1724 != null) {
               this.X.closed(var7.field_1724);
            }

            RippleSupport.reset();
         }
      }

      if (this.p) {
         this.p = false;
      }

      if (L == this) {
         L = null;
      }
   }

   static void tick(class_310 var0) {
      long var1 = 73044045234627L;
      long var3 = var1 ^ 102464982034172L;
      RippleRenderer var5 = L;
      if (var5 != null) {
         if (var0.field_1687 != null && var0.field_1724 != null) {
            if (var5.Q && CosmeticRegistry.currentOwner() == null) {
               var5.setOpen(false);
            }

            if (!var5.Q && RippleSupport.active() && CosmeticRegistry.currentOwner() == null) {
               RippleSupport.hurry();
            }

            if (!var5.Q && !RippleSupport.active()) {
               var5.e(false, var3);
            }
         } else {
            var5.e(true, var3);
         }
      }
   }

   private void t(int var1, short var2, short var3) {
      long var4 = (long)var1 << 32 | 2706702336L | 49616L;
      class_310 var6 = class_310.method_1551();
      if (var6.field_1724 != null) {
         String var7 = this.X.note();
         if (var7 == null) {
            this.y = this.X.timeline();
            this.h = Math.max(0.5F, this.X.length());
            this.g = C;
            this.d = 0;
            UiAudioPolicy.play("SELECT", 1.05F, 0.7F);
            return;
         }
      }

      UiAudioPolicy.play("DENY");
   }

   private void U(RippleFactory.Data var1, long var2) {
      class_310 var4 = class_310.method_1551();
      if (var4.field_1724 != null && this.X.note() == null) {
         var1.action().accept(var4.field_1724);
      } else {
         UiAudioPolicy.play("DENY");
      }
   }

   private int U(long var1) {
      int var3 = 1;

      for (Setting var5 : this.x.settings()) {
         long var6 = 29853491878469L;
         if (29853491878469L == 0L) {
            boolean var10000 = false;
         } else {
            int var11 = var6 < 0L ? -1 : 1;
         }

         try {
            var3 = var3 * 31 + var5.save().hashCode();
         } catch (RuntimeException var10) {
            var3 *= 31;
         }
      }

      return var3;
   }

   private void W() {
      this.g = -1.0;
      this.d = 0;
      this.y = List.of();
   }

   private void O(long var1) {
      long var3 = 114085628068304L;
      short var5 = 26562;
      char var6 = 'ꅕ';
      int var7 = (int)(var3 << 48 >>> 48);
      long var8 = 29853491878470L;
      long var10 = System.nanoTime();
      float var12 = Z == 0L ? 0.0F : Math.min(0.1F, (float)(var10 - Z) / 1.0E9F);
      Z = var10;
      C += var12;
      class_746 var13 = class_310.method_1551().field_1724;
      if (var13 != null) {
         long var14 = 132821389274936L;
         if (132821389274936L == 0L) {
            boolean var10000 = false;
         } else {
            int var40 = var14 < 0L ? -1 : 1;
         }

         if (this.g >= 0.0) {
            double var17;
            for (var17 = C - this.g; this.d < this.y.size() && this.y.get(this.d).at() <= var17; this.y.get(this.d++).action().accept(var13)) {
               if (this.d < this.W.length) {
                  this.W[this.d] = C;
               }
            }

            long var19 = 132821389274935L;
            if (132821389274935L == 0L) {
               boolean var41 = false;
            } else {
               int var42 = var19 < 0L ? -1 : 1;
            }

            double var22;
            int var21 = (var22 = var17 - (this.h + (this.f ? 0.9F : 0.0F))) == 0.0 ? 0 : (var22 < 0.0 ? -1 : 1);
            long var24 = 132821389274936L;
            if (132821389274936L == 0L) {
               boolean var43 = false;
            } else {
               int var44 = var24 < 0L ? -1 : 1;
            }

            if (var21 >= 0) {
               if (this.f && this.Q) {
                  this.t(var5, (short)var6, (short)var7);
               } else if (var17 >= this.h + 1.2) {
                  this.W();
               }
            }
         }

         Iterator var32 = j.iterator();

         while (var32.hasNext()) {
            RippleRenderer.TypeU var18 = (RippleRenderer.TypeU)var32.next();
            long var34 = 132821389274935L;
            if (132821389274935L == 0L) {
               boolean var45 = false;
            } else {
               int var46 = var34 < 0L ? -1 : 1;
            }

            if (var18.F <= C) {
               var32.remove();
               var18.r.accept(var13);
            }
         }

         long var33 = 132821389274935L;
         if (132821389274935L == 0L) {
            boolean var47 = false;
         } else {
            int var48 = var33 < 0L ? -1 : 1;
         }

         int var35 = this.U(var8);
         long var36 = 132821389274935L;
         if (132821389274935L == 0L) {
            boolean var49 = false;
         } else {
            int var50 = var36 < 0L ? -1 : 1;
         }

         if (var35 != this.t) {
            this.t = var35;
            this.R = C;
         }

         if (this.R >= 0.0 && C - this.R >= 0.55F) {
            this.R = -1.0;
            long var37 = 132821389274935L;
            if (132821389274935L == 0L) {
               boolean var51 = false;
            } else {
               int var52 = var37 < 0L ? -1 : 1;
            }

            boolean var26 = this.g >= 0.0 && C - this.g < this.h;
            if (this.Q) {
               long var27 = 132821389274935L;
               if (132821389274935L == 0L) {
                  boolean var53 = false;
               } else {
                  int var54 = var27 < 0L ? -1 : 1;
               }

               if (this.T) {
                  long var30 = 132821389274936L;
                  if (132821389274936L == 0L) {
                     boolean var55 = false;
                  } else {
                     int var56 = var30 < 0L ? -1 : 1;
                  }

                  if (!var26 && this.X.note() == null) {
                     this.t(var5, (short)var6, (short)var7);
                  }
               }
            }
         }

         long var38 = 132821389274936L;
         if (132821389274936L == 0L) {
            boolean var57 = false;
         } else {
            int var58 = var38 < 0L ? -1 : 1;
         }

         if (this.Q && RippleSupport.settled()) {
            if (this.e < 0.0) {
               this.e = C;
            }

            if (!this.T && C - this.e >= 0.25) {
               long var39 = 132821389274935L;
               if (132821389274935L == 0L) {
                  boolean var59 = false;
               } else {
                  int var60 = var39 < 0L ? -1 : 1;
               }

               this.T = true;
               if (this.g < 0.0) {
                  long var28 = 132821389274935L;
                  if (132821389274935L == 0L) {
                     boolean var61 = false;
                  } else {
                     int var62 = var28 < 0L ? -1 : 1;
                  }

                  if (this.X.note() == null) {
                     this.t(var5, (short)var6, (short)var7);
                  }
               }
            }
         }
      }
   }

   @Override
   public void render(DrawContext var1, float var2, float var3) {
      long var4 = 140596550189533L;
      long var6 = var4 ^ 55039320247938L;
      long var8 = var4 ^ 46505960397695L;
      long var10 = var4 ^ 15539742373961L;
      long var12 = var4 ^ 134268859285709L;
      int var14 = (int)((var4 ^ 134268859285709L) >>> 48);
      int var15 = (int)((var4 ^ 134268859285709L) << 16 >>> 32);
      int var16 = (int)(var12 << 48 >>> 48);
      long var17 = var4 ^ 7781622460133L;
      this.O(var17);
      Canvas var19 = var1.canvas;
      this.k = var3;
      this.J = var19.width();
      this.D = var19.height();
      float var20 = 16.0F;
      float var21 = Math.max(var20 + 200.0F, var3 - 16.0F);
      float var22 = (var20 + var21) / 2.0F;
      float var23 = 110.0F;
      RippleSupport.frame(var22 / this.J * 2.0F - 1.0F, 1.0F - (this.D - var23) / this.D);
      float var24 = Easing.SMOOTHSTEP.apply(K((RippleSupport.progress() - 0.62F) / 0.38F, var8));
      float var25 = this.n.target(this.Q ? var24 : 0.0F).update(MotionStyle.MENU.dt(var1.dt)) * var2;
      if (this.S) {
         var1.claim(this.V, DrawContext.TypeE.MOVE);
      }

      if (var25 <= 0.01F) {
         this.a[2] = 0.0F;
      } else {
         var19.pushAlpha(var25);
         float var26 = (1.0F - var25) * 18.0F;
         this.X(var1, var19, var20, var10, var21, var26);
         this.X(var6, var1, var19, var26);
         this.Z((char)var14, var1, var19, var20, var15, var25, (char)var16);
         var19.popAlpha();
      }
   }

   private void X(DrawContext var1, Canvas var2, float var3, long var4, float var6, float var7) {
      long var10 = 84866512453976L;
      long var12 = 94090592098978L;
      long var14 = 12820278125793L;
      short var16 = 2984;
      int var17 = 15998884;
      int var18 = (int)(var14 << 56 >>> 56);
      long var19 = 75631981462493L;
      long var21 = 73301618860645L;
      String var23 = "Live preview";
      float var24 = var2.textWidth(Fonts.SEMIBOLD, var23, 9.5F);
      List var25 = this.N();
      this.c = false;
      float var26 = this.x(var2, var25, var10);
      boolean var27 = 32.0F + var24 + var26 + 10.0F > var6 - 16.0F;
      if (var27 && 26.0F + var26 + 10.0F > var6 - 16.0F) {
         this.c = true;
         var26 = this.x(var2, var25, var10);
      }

      float var28 = var27 ? 26.0F : 32.0F + var24;
      float var29 = var28 + var26 + 10.0F;
      float var30 = Math.max(16.0F, (16.0F + var6) / 2.0F - var29 / 2.0F);
      float var31 = this.D - 22.0F - 34.0F + var7;
      this.a[0] = var30;
      this.a[1] = var31 - 24.0F - 8.0F;
      this.a[2] = var29;
      this.a[3] = 66.0F;
      var2.shadow(var30, var31, var29, 34.0F, 17.0F, 22.0F, 0.0F, 0.0F, 6.0F, 1879048192);
      var2.roundRect(var30, var31, var29, 34.0F, 17.0F, Theme.tint(-300872416));
      var2.roundRectOutlineGradient(var30, var31, var29, 34.0F, 17.0F, 1.0F, 90.0F, 788529151, 184549375);
      float var32 = var31 + 17.0F;
      float var33 = MotionStyle.reduced() ? 0.7F : 0.55F + 0.45F * (float)Math.sin(var1.time * 3.2F);
      long var34 = 125331704691091L;
      if (125331704691091L == 0L) {
         boolean var10000 = false;
      } else {
         int var79 = var34 < 0L ? -1 : 1;
      }

      var2.glowCircle(var30 + 12.0F + 6.0F, var32, 3.0F, 7.0F, BlendMode.withAlpha(Icons.PINK, 0.45F * var33));
      var2.circle(var30 + 12.0F + 6.0F, var32, 3.0F, BlendMode.lerp(Icons.MAGENTA, Icons.PINK, var33));
      if (!var27) {
         var2.text(Fonts.SEMIBOLD, var23, var30 + 12.0F + 14.0F + 6.0F, var32, 9.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      float var37 = var30 + var28;
      long var38 = 125331704691091L;
      if (125331704691091L == 0L) {
         boolean var80 = false;
      } else {
         int var81 = var38 < 0L ? -1 : 1;
      }

      boolean var41 = this.g >= 0.0 && C - this.g < this.h;
      float var42 = this.i.target(var41 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));

      for (RippleRenderer.TypeV var44 : ((Iterable<RippleRenderer.TypeV>)(Object)var25)) {
         float var45 = this.H(var16, var17, var44, (byte)var18) ? 14.0F : 5.0F;
         long var46 = 125331704691091L;
         if (125331704691091L == 0L) {
            boolean var82 = false;
         } else {
            int var83 = var46 < 0L ? -1 : 1;
         }

         if (this.H(var16, var17, var44, (byte)var18)) {
            var2.rect(var37 + var45 / 2.0F - 0.5F, var31 + 9.0F, 1.0F, 16.0F, 486539263);
         }

         var37 += var45;
         float var49 = this.W(var2, var19, var44);
         float var50 = 24.0F;
         float var51 = var31 + (34.0F - var50) / 2.0F;
         var44.W[0] = var37;
         var44.W[1] = var51;
         var44.W[2] = var49;
         var44.W[3] = var50;
         boolean var52 = var44 != this.b || RippleSupport.moved();
         long var53 = 125331704691092L;
         if (125331704691092L == 0L) {
            boolean var84 = false;
         } else {
            int var85 = var53 < 0L ? -1 : 1;
         }

         boolean var9;
         label269: {
            if (var52) {
               long var55 = 125331704691092L;
               if (125331704691092L == 0L) {
                  boolean var86 = false;
               } else {
                  int var87 = var55 < 0L ? -1 : 1;
               }

               if (!this.S) {
                  boolean var57 = var1.masked();
                  long var58 = 125331704691091L;
                  if (125331704691091L == 0L) {
                     boolean var88 = false;
                  } else {
                     int var89 = var58 < 0L ? -1 : 1;
                  }

                  if (!var57 && f(var21, var44.W, var1.mouseX, var1.mouseY)) {
                     var9 = true;
                     break label269;
                  }
               }
            }

            var9 = false;
         }

         if (var9) {
            var1.claim(var44, DrawContext.TypeE.HAND);
         }

         float var54 = var44.C.target(var9 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         float var70 = var44.l.update(MotionStyle.TOGGLE.dt(var1.dt));
         var2.push();
         var2.scaleAround(1.0F - 0.05F * var70, var37 + var49 / 2.0F, var51 + var50 / 2.0F);
         boolean var56 = var44 == this.B && this.f;
         long var71 = 125331704691092L;
         if (125331704691092L == 0L) {
            boolean var90 = false;
         } else {
            int var91 = var71 < 0L ? -1 : 1;
         }

         if (var44 == this.l) {
            long var60 = 125331704691092L;
            if (125331704691092L == 0L) {
               boolean var92 = false;
            } else {
               int var93 = var60 < 0L ? -1 : 1;
            }

            var2.glow(var37, var51, var49, var50, var50 / 2.0F, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.22F + 0.2F * var54 + 0.15F * var42));
            var2.roundRectGradient(
               var37,
               var51,
               var49,
               var50,
               var50 / 2.0F,
               0.0F,
               BlendMode.withAlpha(Icons.INDIGO, 0.88F + 0.12F * var54),
               BlendMode.withAlpha(Icons.PURPLE, 0.88F + 0.12F * var54)
            );
            if (var42 > 0.01F) {
               long var62 = 125331704691092L;
               if (125331704691092L == 0L) {
                  boolean var94 = false;
               } else {
                  int var95 = var62 < 0L ? -1 : 1;
               }

               if (this.g >= 0.0) {
                  float var64 = K((float)((C - this.g) / this.h), var12);
                  long var65 = 125331704691091L;
                  if (125331704691091L == 0L) {
                     boolean var96 = false;
                  } else {
                     int var97 = var65 < 0L ? -1 : 1;
                  }

                  var2.pushClip(var37, var51, var49, var50, var50 / 2.0F);
                  var2.rect(var37, var51, var49 * var64, var50, BlendMode.withAlpha(-1, 0.13F * var42));
                  var2.popClip();
               }
            }
         } else {
            long var72 = 125331704691091L;
            if (125331704691091L == 0L) {
               boolean var98 = false;
            } else {
               int var99 = var72 < 0L ? -1 : 1;
            }

            if (var56) {
               long var63 = 125331704691092L;
               if (125331704691092L == 0L) {
                  boolean var100 = false;
               } else {
                  int var101 = var63 < 0L ? -1 : 1;
               }

               var2.roundRectGradient(
                  var37,
                  var51,
                  var49,
                  var50,
                  var50 / 2.0F,
                  0.0F,
                  BlendMode.withAlpha(Icons.INDIGO, 0.7F + 0.2F * var54),
                  BlendMode.withAlpha(Icons.MAGENTA, 0.7F + 0.2F * var54)
               );
            } else {
               var2.roundRect(var37, var51, var49, var50, var50 / 2.0F, BlendMode.lerp(352321535, 654311423, var54));
            }
         }

         int var36;
         label251: {
            var2.roundRectOutline(var37, var51, var49, var50, var50 / 2.0F, 1.0F, BlendMode.lerp(452984831, 1124073471, var54));
            if (var44 != this.l) {
               long var73 = 125331704691091L;
               if (125331704691091L == 0L) {
                  boolean var102 = false;
               } else {
                  int var103 = var73 < 0L ? -1 : 1;
               }

               if (!var56) {
                  var36 = var52 ? BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var54) : BlendMode.withAlpha(Icons.TEXT_3, 0.6F);
                  break label251;
               }
            }

            var36 = Icons.TEXT;
         }

         long var61 = 125331704691091L;
         if (125331704691091L == 0L) {
            boolean var104 = false;
         } else {
            int var105 = var61 < 0L ? -1 : 1;
         }

         if (!var44.F.isEmpty() && (!this.c || !this.s.contains(var44))) {
            String var8;
            label235: {
               if (var44 == this.l) {
                  float var76;
                  var36 = (var76 = var42 - 0.5F) == 0.0F ? 0 : (var76 < 0.0F ? -1 : 1);
                  long var77 = 125331704691092L;
                  if (125331704691092L == 0L) {
                     boolean var108 = false;
                  } else {
                     int var109 = var77 < 0L ? -1 : 1;
                  }

                  if (var36 > 0) {
                     var8 = "rotate-ccw";
                     break label235;
                  }
               }

               var8 = var44.X;
            }

            int var78 = var44 == this.u ? BlendMode.lerp(Icons.PURPLE, Icons.TEXT, var54) : var36;
            var2.icon(var8, var37 + 11.0F + 5.5F, var51 + var50 / 2.0F, 11.0F, var78);
            var2.text(Fonts.MEDIUM, var44.F, var37 + 11.0F + 11.0F + 5.0F, var51 + var50 / 2.0F, 9.0F, var36, FontStyle.LEFT, FontWeight.MIDDLE);
         } else {
            var2.icon(var44.X, var37 + var49 / 2.0F, var51 + var50 / 2.0F, 12.0F, var36);
            long var75 = 125331704691092L;
            if (125331704691092L == 0L) {
               boolean var106 = false;
            } else {
               int var107 = var75 < 0L ? -1 : 1;
            }
         }

         var2.pop();
         var37 += var49;
      }
   }

   private List<RippleRenderer.TypeV> N() {
      ArrayList var1 = new ArrayList();
      var1.add(this.l);
      var1.add(this.B);
      var1.addAll(this.s);
      var1.add(this.b);
      var1.add(this.u);
      return var1;
   }

   private boolean H(int var1, int var2, RippleRenderer.TypeV var3, byte var4) {
      return var3 == this.l || var3 == this.b || !this.s.isEmpty() && var3 == this.s.getFirst();
   }

   private float x(Canvas var1, List var2, long var3) {
      long var5 = 12820278125793L;
      short var7 = 2984;
      int var8 = 15998884;
      int var9 = (int)(var5 << 56 >>> 56);
      long var10 = 75631981462493L;
      float var12 = 0.0F;

      for (RippleRenderer.TypeV var14 : ((Iterable<RippleRenderer.TypeV>)(Object)var2)) {
         var12 += this.W(var1, var10, var14) + (this.H(var7, var8, var14, (byte)var9) ? 14 : 5);
      }

      return var12;
   }

   private float W(Canvas var1, long var2, RippleRenderer.TypeV var4) {
      return var4.F.isEmpty() || this.c && this.s.contains(var4) ? 28.0F : 27.0F + var1.textWidth(Fonts.MEDIUM, var4.F, 9.0F) + 12.0F;
   }

   private void X(long var1, DrawContext var3, Canvas var4, float var5) {
      long var6 = 94090592098978L;
      long var8 = 117226986138187L;
      float var10 = this.a[0] + this.a[2] / 2.0F;
      float var11 = this.D - 22.0F - 34.0F - 8.0F - 24.0F + var5;
      String var12 = this.X.note();
      if (var12 != null) {
         float var13 = var4.textWidth(Fonts.MEDIUM, var12, 8.5F);
         float var14 = var13 + 40.0F;
         float var15 = var10 - var14 / 2.0F;
         this.v(var4, var15, var11, var14, var8);
         var4.icon("info", var15 + 16.0F, var11 + 12.0F, 11.0F, Icons.PINK);
         var4.text(Fonts.MEDIUM, var12, var15 + 28.0F, var11 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      } else {
         List var49 = this.g >= 0.0 ? this.y : this.X.timeline();
         int var50 = var49.size();
         if (var50 != 0) {
            float var51 = 18.0F;
            float[] var16 = new float[var50];
            float var17 = 0.0F;

            for (int var18 = 0; var18 < var50; var18++) {
               var16[var18] = 12.0F + var4.textWidth(Fonts.MEDIUM, ((RippleFactory.Data2)var49.get(var18)).label(), 8.5F);
               var17 += var16[var18] + (var18 > 0 ? var51 : 0.0F);
            }

            long var52 = 85564209467231L;
            if (85564209467231L == 0L) {
               boolean var10000 = false;
            } else {
               int var53 = var52 < 0L ? -1 : 1;
            }

            float var21 = var17 + 28.0F;
            long var22 = 85564209467230L;
            if (85564209467230L == 0L) {
               boolean var54 = false;
            } else {
               int var55 = var22 < 0L ? -1 : 1;
            }

            float var24 = this.P.value() <= 0.0F ? this.P.snap(var21).value() : this.P.target(var21).update(var3.dt);
            float var25 = var10 - var24 / 2.0F;
            this.E[0] = var25;
            this.E[1] = var11;
            this.E[2] = var24;
            this.E[3] = 24.0F;
            this.v(var4, var25, var11, var24, var8);
            float var26 = var11 + 12.0F;
            long var27 = 85564209467231L;
            if (85564209467231L == 0L) {
               boolean var56 = false;
            } else {
               int var57 = var27 < 0L ? -1 : 1;
            }

            double var30 = this.g >= 0.0 ? C - this.g : -1.0;
            float var32 = var10 - var17 / 2.0F;

            for (int var33 = 0; var33 < var50; var33++) {
               RippleFactory.Data2 var34 = (RippleFactory.Data2)var49.get(var33);
               boolean var35 = var30 >= var34.at();
               long var36 = 85564209467230L;
               if (85564209467230L == 0L) {
                  boolean var58 = false;
               } else {
                  int var59 = var36 < 0L ? -1 : 1;
               }

               float var38 = var35 && var33 < this.W.length ? (float)(C - this.W[var33]) : 99.0F;
               float var39 = var35 ? (float)Math.exp(-var38 / 0.18F) : 0.0F;
               float var40 = var32 + 3.5F;
               if (var33 > 0) {
                  RippleFactory.Data2 var41 = (RippleFactory.Data2)var49.get(var33 - 1);
                  long var42 = 85564209467230L;
                  if (85564209467230L == 0L) {
                     boolean var60 = false;
                  } else {
                     int var61 = var42 < 0L ? -1 : 1;
                  }

                  float var44 = var32 - var51 + 3.0F;
                  float var45 = var32 - 3.0F;
                  var4.rect(var44, var26 - 0.5F, var45 - var44, 1.0F, 587202559);
                  long var46 = 85564209467231L;
                  if (85564209467231L == 0L) {
                     boolean var62 = false;
                  } else {
                     int var63 = var46 < 0L ? -1 : 1;
                  }

                  if (var30 >= var41.at()) {
                     float var48 = K((float)((var30 - var41.at()) / Math.max(0.05F, var34.at() - var41.at())), var6);
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

   private void v(Canvas var1, float var2, float var3, float var4, long var5) {
      var1.shadow(var2, var3, var4, 24.0F, 12.0F, 16.0F, 0.0F, 0.0F, 4.0F, 1342177280);
      var1.roundRect(var2, var3, var4, 24.0F, 12.0F, Theme.tint(-703525600));
      var1.roundRectOutline(var2, var3, var4, 24.0F, 12.0F, 1.0F, 452984831);
   }

   private void Z(char var1, DrawContext var2, Canvas var3, float var4, int var5, float var6, char var7) {
      long var8;
      boolean var19;
      label47: {
         long var11 = 6334150328592L;
         var8 = var11 ^ 122444150614875L;
         if (this.Q) {
            boolean var13 = false;
            if (!this.U) {
               var19 = RippleSupport.settled();
               var13 = false;
               if (var19 && (this.e < 0.0 || C - this.e < 9.0)) {
                  var19 = true;
                  break label47;
               }
            }
         }

         var19 = false;
      }

      float var12 = this.K.target(var19 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var2.dt) * 0.6F);
      float var23;
      int varCmp = Float.compare(var23 = var12 - 0.01F, 0.0F);
      if (varCmp > 0) {
         String var14 = "Drag to look around  ·  Scroll to zoom  ·  Double-click to reset";
         float var15 = var3.textWidth(Fonts.MEDIUM, var14, 8.5F);
         float var16 = var15 + 40.0F;
         float var17 = 22.0F - (1.0F - var12) * 6.0F;
         float var18 = Math.max(16.0F, (16.0F + this.k - 16.0F) / 2.0F - var16 / 2.0F);
         var3.pushAlpha(var12 / Math.max(var6, 0.01F) * var6);
         this.v(var3, var18, var17, var16, var8);
         var3.icon("mouse", var18 + 16.0F, var17 + 12.0F, 11.0F, Icons.PURPLE);
         var3.text(Fonts.MEDIUM, var14, var18 + 28.0F, var17 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         var3.popAlpha();
      }
   }

   @Override
   public boolean mouseDown(float var1, float var2, int var3) {
      long var4 = 3415839266275L;
      long var6 = var4 ^ 72230932772742L;
      if (!this.Q) {
         return false;
      } else if (this.a[2] > 0.0F && f(var6, this.a, var1, var2)) {
         if (var3 == 0) {
            for (RippleRenderer.TypeV var9 : this.N()) {
               if (f(var6, var9.W, var1, var2) && (var9 != this.b || RippleSupport.moved())) {
                  var9.l.snap(1.0F).target(0.0F);
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
         if (var3 == 0 && var8 - this.O < 320L) {
            this.O = 0L;
            if (RippleSupport.moved()) {
               RippleSupport.resetView();
               UiAudioPolicy.play("TAB", 1.1F);
            }

            return true;
         } else {
            this.O = var8;
            this.S = true;
            this.M = var1;
            this.m = var2;
            return true;
         }
      }
   }

   @Override
   public void mouseDrag(float var1, float var2) {
      if (this.S) {
         float var3 = var1 - this.M;
         float var4 = var2 - this.m;
         this.M = var1;
         this.m = var2;
         if (var3 != 0.0F || var4 != 0.0F) {
            RippleSupport.drag(var3, var4);
            this.U = true;
            this.O = 0L;
         }
      }
   }

   @Override
   public void mouseUp(float var1, float var2, int var3) {
      this.S = false;
   }

   @Override
   public boolean scroll(float var1, float var2, double var3) {
      if (this.Q && var3 != 0.0) {
         RippleSupport.zoom(var3);
         this.U = true;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean keyPressed(int var1) {
      long var2 = 94925238039621L;
      long var4 = var2 ^ 54525151032725L;
      int var6 = (int)((var2 ^ 54525151032725L) >>> 32);
      int var7 = (int)((var2 ^ 54525151032725L) << 32 >>> 48);
      int var8 = (int)(var4 << 48 >>> 48);
      if (!this.Q) {
         return false;
      } else {
         switch (var1) {
            case 32:
               this.l.l.snap(1.0F).target(0.0F);
               this.t(var6, (short)var7, (short)var8);
               return true;
            case 76:
               this.B.L.run();
               return true;
            default:
               return false;
         }
      }
   }

   
   static RippleRenderer current() {
      return L;
   }

   String describe() {
      return String.format(
         Locale.ROOT,
         "%s open %s lent %s playing %s loop %s clock %.2f, camera: %s",
         this.x.id(),
         this.Q,
         this.p,
         this.g >= 0.0 ? String.format(Locale.ROOT, "%.2f", C - this.g) : "no",
         this.f,
         C,
         RippleSupport.describe()
      );
   }

   private static boolean f(long var0, float[] var2, float var3, float var4) {
      if (var2[2] > 0.0F) {
         long var5 = 73301618860645L;
         if (73301618860645L == 0L) {
            boolean var10000 = false;
         } else {
            int var8 = var5 < 0L ? -1 : 1;
         }

         if (var3 >= var2[0] && var3 <= var2[0] + var2[2] && var4 >= var2[1] && var4 <= var2[1] + var2[3]) {
            return true;
         }
      }

      return false;
   }

   private static float K(float var0, long var1) {
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   static void closeOwned() {
      long var0 = 18972681772424L;
      long var2 = var0 ^ 15528243542711L;
      if (L != null) {
         L.e(true, var2);
      }

      RippleSupport.reset();
      A.clear();
      j.clear();
   }

public static void ensureSourceClassInitialized() {
   }

private record TypeU(double F, Consumer<class_746> r) {
      public double at() {
         return this.F;
      }

      public Consumer<class_746> action() {
         return this.r;
      }

      public double doubleState25() {
         return this.F;
      }

      public Consumer<class_746> consumerState22() {
         return this.r;
      }
   }

   private static final class TypeV {
      final String F;
      final String X;
      final Runnable L;
      final float[] W = new float[4];
      final Animator C = Animator.smooth(0.0F);
      final Animator l = Animator.snappy(0.0F);

      TypeV(String var1, String var2, Runnable var3) {
         this.F = var1;
         this.X = var2;
         this.L = var3;
      }
   }
}
