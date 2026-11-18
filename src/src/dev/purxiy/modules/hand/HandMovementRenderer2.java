// Sourced By DevFFNightmare
package dev.purxiy.modules.hand;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.clickgui.Studio;
import java.util.Locale;
import net.minecraft.class_1041;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_3675;
import net.minecraft.class_5498;
import net.minecraft.class_746;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public final class HandMovementRenderer2 implements Studio {
   private static final float o = 10.0F;
   private static final long B = 320L;
   private static final float F = 30.0F;
   private static final float x = 17.0F;
   private static final double y = 0.015;
   private static final double P = 2.5;
   private static boolean U;
   private final HandMovementModule i;
   private boolean e;
   private HandMovementRenderer2.TypeW O = HandMovementRenderer2.TypeW.m;
   
   private class_5498 d;
   private boolean j;
   private final float[][] C = new float[][]{new float[512], new float[512]};
   private final int[] l = new int[2];
   private final float[] p = new float[512];
   private final Animator[] H = new Animator[]{Animator.smooth(0.0F), Animator.smooth(0.0F)};
   private final Animator[] f = new Animator[]{Animator.smooth(0.0F), Animator.smooth(0.0F)};
   private final Object[] n = new Object[]{new Object(), new Object()};
   private final Animator t = Animator.smooth(0.0F);
   private final Animator E = Animator.bouncy(0.0F);
   private float G = 1.0F;
   private float k;
   private float Z;
   private float V;
   private float W;
   private float v;
   private float q;
   
   private class_1268 Y;
   
   private class_1268 N;
   private int J;
   private float s;
   private float a;
   private float b;
   private float w;
   private boolean g;
   private double M;
   private double Q;
   private boolean A;
   private boolean S;
   private long D;
   
   private class_1268 c;
   private String R = "";
   private float T = -10.0F;
   private final Animator u = Animator.smooth(0.0F);
   private final Animator wj = Animator.smooth(0.0F);
   private final float[] r = new float[4];
   private final Animator L = new Animator(0.0F, 260.0F, 32.0F);
   private boolean h;
   private float z = -10.0F;
   private String K = "";
   private String X = "";
   private float m = -10.0F;
   private final Animator I = new Animator(0.0F, 420.0F, 41.0F);





   HandMovementRenderer2(HandMovementModule var1) {
      this.i = var1;
   }

   public static boolean measuring() {
      return U;
   }

   @Override
   public String title() {
      return "Hand Studio";
   }

   @Override
   public String subtitle() {
      return "Drag your hand into place and watch it move, live";
   }

   @Override
   public String icon() {
      return "hand-grab";
   }

   @Override
   public Studio.TypeQ dock() {
      long var1 = 33154159603150L;
      long var3 = var1 ^ 60687315000126L;
      return z(var3, class_1268.field_5808) > 0 ? Studio.TypeQ.TOP_LEFT : Studio.TypeQ.TOP_RIGHT;
   }

   @Override
   public void setOpen(boolean var1) {
      this.N = null;
      this.Y = null;
      this.T = -10.0F;
      if (U != var1) {
         U = var1;
         class_310 var2 = class_310.method_1551();
         if (var1) {
            this.d = var2.field_1690.method_31044();
            this.j = var2.field_1690.field_1842;
            var2.field_1690.method_31043(class_5498.field_26664);
            var2.field_1690.field_1842 = false;
            HandMovementSupport4.setPreview(this.O.stack());
            this.h = false;
            this.t.snap(0.0F);
         } else {
            if (this.d != null) {
               var2.field_1690.method_31043(this.d);
               var2.field_1690.field_1842 = this.j;
               this.d = null;
            }

            HandMovementSupport4.setPreview(null);
            HandMovementSupport4.setDemoSwing(false);
            HandMovementSupport4.setDemoWalk(false);
            this.N = null;
            this.Y = null;
         }
      }
   }

   @Override
   public boolean consumeCloseRequest() {
      boolean var1 = this.e;
      this.e = false;
      return var1;
   }

   @Override
   public void render(DrawContext var1, float var2, float var3) {
      long var4 = 28297225104985L;
      long var6 = var4 ^ 3397955665761L;
      long var8 = var4 ^ 27117367022342L;
      long var10 = var4 ^ 94531343020597L;
      int var12 = (int)((var4 ^ 94531343020597L) >>> 32);
      int var13 = (int)((var4 ^ 94531343020597L) << 32 >>> 40);
      int var14 = (int)(var10 << 56 >>> 56);
      long var15 = var4 ^ 100480653171105L;
      long var17 = var4 ^ 24149376640454L;
      var10 = var4 ^ 15366427911558L;
      int var19 = (int)((var4 ^ 15366427911558L) >>> 32);
      int var20 = (int)((var4 ^ 15366427911558L) << 32 >>> 56);
      int var21 = (int)(var10 << 40 >>> 40);
      var10 = var4 ^ 103982665244434L;
      int var22 = (int)((var4 ^ 103982665244434L) >>> 32);
      int var23 = (int)((var4 ^ 103982665244434L) << 32 >>> 48);
      int var24 = (int)(var10 << 48 >>> 48);
      long var25 = (var4 ^ 104486149488328L) >>> 16;
      int var27 = (int)((var4 ^ 104486149488328L) << 48 >>> 48);
      long var28 = var4 ^ 78383280611392L;
      Canvas var30 = var1.canvas;
      float var31 = this.t.target(U ? 1.0F : 0.0F).update(MotionStyle.MENU.dt(var1.dt)) * var2;
      this.G = var30.pixelScale();
      this.k = var30.width();
      this.Z = var30.height();
      boolean var32 = this.dock().right();
      this.V = var32 ? 0.0F : var3 + 16.0F;
      this.W = var32 ? var3 - 16.0F : this.k;
      this.v = var1.mouseX;
      this.q = var1.mouseY;
      if (!(var31 <= 0.01F)) {
         this.P(var12, var13, (byte)var14);
         boolean var33 = W(var17, this.r, var1.mouseX, var1.mouseY);
         boolean var34 = !var1.masked() && this.f(var1.mouseX, var25, (short)var27);
         this.Y = this.N != null ? this.N : (var34 && !var33 ? this.w(var8, var1.mouseX, var1.mouseY) : null);
         if (this.Y != null) {
            var1.claim(this.n[j(var28, this.Y)], DrawContext.TypeE.MOVE);
         }

         var30.pushAlpha(var31);
         this.a(var1, var6, var30);
         this.q(var1, var30, var15);
         this.y(var19, (byte)var20, var21, var1, var30);
         this.r(var22, var1, var30, var23, (short)var24, var31);
         var30.popAlpha();
      }
   }

   private void P(int var1, int var2, byte var3) {
      long var4 = 83862408535148L;
      long var6 = var4 ^ 20563439784565L;
      class_310 var8 = class_310.method_1551();
      SwingStyle.ensureSourceClassInitialized();
      class_276 var9 = var8.method_1522();
      Matrix4f var10 = new Matrix4f(HandMovementSupport2.projection()).mul(HandMovementSupport2.modelView());

      for (class_1268 var14 : class_1268.values()) {
         int var15 = j(var6, var14);
         int var16 = HandMovementSupport2.geometry().hull(var14, var10, var9.field_1482, var9.field_1481, this.p);

         for (int var17 = 0; var17 < var16 * 2; var17++) {
            this.C[var15][var17] = this.p[var17] / this.G;
         }

         this.l[var15] = var16;
      }
   }

   private void a(DrawContext var1, long var2, Canvas var4) {
      long var5 = 24675105398404L;
      long var7 = 104412560494105L;
      SwingStyle.ensureSourceClassInitialized();
      class_1268 var9 = this.c(var5);
      boolean var10 = this.i.A.enabled();
      class_1268[] var11 = class_1268.values();

      for (class_1268 var15 : var11) {
         int var16 = j(var7, var15);
         boolean var17 = this.Y != null && (this.Y == var15 || var10);
         float var18 = this.H[var16].target(var17 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         float var19 = this.f[var16].target(var9 != var15 && !var10 ? 0.0F : 1.0F).update(MotionStyle.HOVER.dt(var1.dt));
         int var20 = this.l[var16];
         long var21 = 29323150774584L;
         if (29323150774584L == 0L) {
            boolean var10000 = false;
         } else {
            int var56 = var21 < 0L ? -1 : 1;
         }

         long var24 = 29323150774584L;
         if (29323150774584L == 0L) {
            boolean var57 = false;
         } else {
            int var58 = var24 < 0L ? -1 : 1;
         }

         if (var20 >= 3) {
            float var26;
            int var27 = (var26 = var18 + var19 * 0.4F - 0.01F) == 0.0F ? 0 : (var26 < 0.0F ? -1 : 1);
            long var28 = 29323150774583L;
            if (29323150774583L == 0L) {
               boolean var59 = false;
            } else {
               int var60 = var28 < 0L ? -1 : 1;
            }

            if (var27 > 0) {
               float[] var30 = this.C[var16];
               boolean var31 = this.N != null && (this.N == var15 || var10);
               int var32 = var31 ? Icons.MAGENTA : Theme.tint(-3558657);
               float var33 = Math.max(var18, 0.32F * var19);
               float var34 = 0.0F;
               float var35 = 0.0F;

               for (int var36 = 0; var36 < var20; var36++) {
                  long var37 = 29323150774584L;
                  if (29323150774584L == 0L) {
                     boolean var61 = false;
                  } else {
                     int var62 = var37 < 0L ? -1 : 1;
                  }

                  var34 += var30[var36 * 2];
                  var35 += var30[var36 * 2 + 1];
               }

               float var49 = var34 / var20;
               float var50 = var35 / var20;
               long var38 = 29323150774584L;
               if (29323150774584L == 0L) {
                  boolean var63 = false;
               } else {
                  int var64 = var38 < 0L ? -1 : 1;
               }

               int var41 = BlendMode.withAlpha(Icons.VIOLET, 0.07F * var18);

               for (int var42 = 0; var42 < var20; var42++) {
                  long var43 = 29323150774583L;
                  if (29323150774583L == 0L) {
                     boolean var65 = false;
                  } else {
                     int var66 = var43 < 0L ? -1 : 1;
                  }

                  int var45 = (var42 + 1) % var20;
                  long var46 = 29323150774584L;
                  if (29323150774584L == 0L) {
                     boolean var67 = false;
                  } else {
                     int var68 = var46 < 0L ? -1 : 1;
                  }

                  if (var18 > 0.01F) {
                     var4.triangle(var49, var50, var30[var42 * 2], var30[var42 * 2 + 1], var30[var45 * 2], var30[var45 * 2 + 1], var41);
                  }
               }

               long var51 = 29323150774584L;
               if (29323150774584L == 0L) {
                  boolean var69 = false;
               } else {
                  int var70 = var51 < 0L ? -1 : 1;
               }

               for (int var44 = 0; var44 < var20; var44++) {
                  int var53 = (var44 + 1) % var20;
                  long var54 = 29323150774583L;
                  if (29323150774583L == 0L) {
                     boolean var71 = false;
                  } else {
                     int var72 = var54 < 0L ? -1 : 1;
                  }

                  var4.line(
                     var30[var44 * 2], var30[var44 * 2 + 1], var30[var53 * 2], var30[var53 * 2 + 1], 7.0F, BlendMode.withAlpha(Icons.PURPLE, 0.1F * var33)
                  );
               }

               long var52 = 29323150774583L;
               if (29323150774583L == 0L) {
                  boolean var73 = false;
               } else {
                  int var74 = var52 < 0L ? -1 : 1;
               }

               for (int var55 = 0; var55 < var20; var55++) {
                  int var47 = (var55 + 1) % var20;
                  var4.line(var30[var55 * 2], var30[var55 * 2 + 1], var30[var47 * 2], var30[var47 * 2 + 1], 1.4F, BlendMode.withAlpha(var32, 0.85F * var33));
               }
            }
         }

         long var48 = 29323150774583L;
         if (29323150774583L == 0L) {
            boolean var75 = false;
         } else {
            int var76 = var48 < 0L ? -1 : 1;
         }
      }
   }

   private void q(DrawContext var1, Canvas var2, long var3) {
      long var5 = 49858628603099L;
      short var7 = 11608;
      char var8 = '鷘';
      int var9 = (int)(var5 << 48 >>> 48);
      long var10 = 45176885982960L;
      long var12 = 24675105398404L;
      SwingStyle.ensureSourceClassInitialized();
      boolean var14 = this.N != null && this.J == 0 && this.g;
      float var15 = this.wj.target(var14 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      long var16 = 73523660814327L;
      if (73523660814327L == 0L) {
         boolean var10000 = false;
      } else {
         int var45 = var16 < 0L ? -1 : 1;
      }

      class_1268 var19 = this.N != null ? this.N : this.c(var12);
      if (!(var15 <= 0.01F)) {
         int var20 = z(var10, var19);
         float[] var21 = HandMovementSupport4.shown(var19);
         float[] var22 = this.g(var20 * 0.56F, var7, (short)var8, var9, -0.52F, -0.72F);
         float[] var23 = this.g(var20 * (0.56F + var21[0]), var7, (short)var8, var9, -0.52F + var21[1], -0.72F - var21[2]);
         long var24 = 73523660814327L;
         if (73523660814327L == 0L) {
            boolean var46 = false;
         } else {
            int var47 = var24 < 0L ? -1 : 1;
         }

         if (var22 != null) {
            long var27 = 73523660814328L;
            if (73523660814328L == 0L) {
               boolean var48 = false;
            } else {
               int var49 = var27 < 0L ? -1 : 1;
            }

            if (var23 != null) {
               var2.pushAlpha(var15);
               float var29 = (float)Math.hypot(var23[0] - var22[0], var23[1] - var22[1]);
               float var30;
               int var31 = (var30 = var29 - 8.0F) == 0.0F ? 0 : (var30 < 0.0F ? -1 : 1);
               long var32 = 73523660814327L;
               if (73523660814327L == 0L) {
                  boolean var50 = false;
               } else {
                  int var51 = var32 < 0L ? -1 : 1;
               }

               if (var31 > 0) {
                  int var35 = (int)(var29 / 9.0F);

                  for (int var36 = 0; var36 < var35; var36++) {
                     float var37 = var36;
                     long var38 = 73523660814327L;
                     if (73523660814327L == 0L) {
                        boolean var52 = false;
                     } else {
                        int var53 = var38 < 0L ? -1 : 1;
                     }

                     float var41 = (var37 + 0.2F) / var35;
                     float var42 = (var36 + 0.7F) / var35;
                     var2.line(
                        A(var22[0], var23[0], var41),
                        A(var22[1], var23[1], var41),
                        A(var22[0], var23[0], var42),
                        A(var22[1], var23[1], var42),
                        1.2F,
                        BlendMode.withAlpha(Theme.tint(-1449985), 0.45F)
                     );
                  }
               }

               long var43 = 73523660814328L;
               if (73523660814328L == 0L) {
                  boolean var54 = false;
               } else {
                  int var55 = var43 < 0L ? -1 : 1;
               }

               boolean var44 = var29 < 3.0F;
               var2.ring(var22[0], var22[1], 7.0F, 1.2F, BlendMode.withAlpha(var44 ? Icons.MAGENTA : Theme.tint(-1449985), var44 ? 0.95F : 0.55F));
               var2.text(
                  Fonts.SEMIBOLD,
                  "VANILLA",
                  var22[0],
                  var22[1] + 16.0F,
                  7.5F,
                  BlendMode.withAlpha(Theme.tint(-1449985), 0.6F),
                  FontStyle.CENTER,
                  FontWeight.MIDDLE,
                  0.1F
               );
               var2.glowCircle(var23[0], var23[1], 3.2F, 8.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.5F));
               var2.circle(var23[0], var23[1], 3.2F, -1);
               var2.popAlpha();
               return;
            }
         }
      }
   }

   
   private float[] g(float var1, int var2, short var3, int var4, float var5, float var6) {
      SwingStyle.ensureSourceClassInitialized();
      Vector4f var7 = HandMovementSupport2.projection().transform(new Vector4f(var1, var5, var6, 1.0F));
      return var7.w <= 1.0E-4F ? null : new float[]{(var7.x / var7.w * 0.5F + 0.5F) * this.k, (0.5F - var7.y / var7.w * 0.5F) * this.Z};
   }

   private void y(int var1, byte var2, int var3, DrawContext var4, Canvas var5) {
      long var6 = 22289545874399L;
      long var8 = var6 ^ 2425290397019L;
      long var10 = var6 ^ 82135912465862L;
      long var12 = var6 ^ 84816059402124L;
      SwingStyle.ensureSourceClassInitialized();
      class_1268 var14 = this.Y;
      boolean var15 = this.N != null && this.g;
      String var16 = !var15 && !(var4.time - this.T < 1.2F) ? (var14 == null ? "" : this.U(var14, var12)) : this.R;
      float var17 = this.u.target((var14 == null || var16.isEmpty()) && !var15 ? 0.0F : 1.0F).update(MotionStyle.HOVER.dt(var4.dt));
      if (!(var17 <= 0.01F) && !var16.isEmpty()) {
         class_1268 var18 = var14 != null ? var14 : this.c(var8);
         int var19 = this.l[j(var10, var18)];
         if (var19 >= 3) {
            float[] var20 = this.C[j(var10, var18)];
            float var21 = Float.MAX_VALUE;
            float var22 = -Float.MAX_VALUE;
            float var23 = Float.MAX_VALUE;

            for (int var24 = 0; var24 < var19; var24++) {
               var21 = Math.min(var21, var20[var24 * 2]);
               var22 = Math.max(var22, var20[var24 * 2]);
               var23 = Math.min(var23, var20[var24 * 2 + 1]);
            }

            float var35 = this.E.update(var4.dt);
            float var25 = 9.5F;
            float var26 = var5.textWidth(Fonts.SEMIBOLD, var16, var25);
            float var27 = var26 + 26.0F;
            float var28 = 22.0F;
            float var29 = Math.max(this.V, 12.0F);
            float var30 = Math.max(var29, Math.min(this.W, this.k - 12.0F) - var27);
            float var31 = Math.clamp((Math.max(var21, this.V) + Math.min(var22, this.W)) / 2.0F - var27 / 2.0F, var29, var30);
            float var32 = var23 - var28 - 12.0F;
            int var33 = 5189;
            var33 = 7785439;
            if (var32 < 12.0F) {
               var31 = Math.clamp(this.v + 16.0F, var29, var30);
               var32 = Math.clamp(this.q - var28 - 14.0F, 12.0F, this.Z - var28 - 12.0F);
            }

            var5.pushAlpha(var17);
            var5.push();
            var5.scaleAround(1.0F + 0.08F * var35, var31 + var27 / 2.0F, var32 + var28 / 2.0F);
            var5.shadow(var31, var32, var27, var28, var28 / 2.0F, 14.0F, 0.0F, 0.0F, 4.0F, 1711276032);
            var5.roundRect(var31, var32, var27, var28, var28 / 2.0F, Theme.tint(-233566170));
            float var34 = var28 / 2.0F;
            var5.roundRectOutline(
               var31, var32, var27, var28, var34, 1.0F, BlendMode.lerp(654311423, BlendMode.withAlpha(Icons.MAGENTA, 0.6F), var15 ? 1.0F : 0.0F)
            );
            var5.circle(var31 + 11.0F, var32 + var28 / 2.0F, 2.5F, var15 ? Icons.MAGENTA : Icons.PURPLE);
            var5.text(Fonts.SEMIBOLD, var16, var31 + 18.0F, var32 + var28 / 2.0F, var25, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
            var5.pop();
            var5.popAlpha();
         }
      }
   }

   private void r(int var1, DrawContext var2, Canvas var3, int var4, short var5, float var6) {
      long var10 = 78263697273163L;
      long var12 = var10 ^ 111448299755772L;
      int var14 = (int)((var10 ^ 111448299755772L) >>> 32);
      int var15 = (int)((var10 ^ 111448299755772L) << 32 >>> 48);
      int var16 = (int)(var12 << 48 >>> 48);
      long var17 = var10 ^ 82896875895508L;
      long var19 = var10 ^ 7655078009037L;
      long var21 = var10 ^ 62863313628014L;
      long var23 = var10 ^ 75460579251296L;
      var12 = var10 ^ 35222102460960L;
      int var25 = (int)((var10 ^ 35222102460960L) >>> 48);
      int var26 = (int)((var10 ^ 35222102460960L) << 16 >>> 32);
      int var27 = (int)(var12 << 48 >>> 48);
      SwingStyle.ensureSourceClassInitialized();
      RenderPipelines var28 = RenderPipelines.get();
      class_310 var29 = class_310.method_1551();
      float var30 = var28.fromGui(var29.method_22683().method_4502() - 50) - 10.0F;
      float var31 = Math.max(this.V, 16.0F);
      float var32 = Math.min(this.W, this.k - 16.0F);
      String var33 = "Hand Studio";
      float var34 = var3.textWidth(Fonts.SEMIBOLD, var33, 9.5F);
      float var35 = 0.0F;

      for (HandMovementRenderer2.TypeM var39 : HandMovementRenderer2.TypeM.values()) {
         var35 += this.X(var3, var39, var14, (short)var15, (short)var16) + (A(var21, var39) ? 14 : 5);
      }

      float var58 = 30.0F + var34;
      float var59 = var58 + var35 + 8.0F;
      float var60 = this.dock().right()
         ? Math.min(var32 - var59, (var31 + var32) / 2.0F - var59 / 2.0F)
         : Math.max(var31, (var31 + var32) / 2.0F - var59 / 2.0F);
      float var61 = var30 - 30.0F;
      if (!this.h) {
         this.L.snap(var61 + 14.0F);
         this.h = true;
      }

      float var40 = this.L.target(var61).update(MotionStyle.MENU.dt(var2.dt));
      this.r[0] = var60;
      this.r[1] = var40 - 17.0F - 6.0F;
      this.r[2] = var59;
      this.r[3] = 53.0F;
      this.U(var2, var3, var60 + var59 / 2.0F, var23, var40 - 6.0F - 17.0F);
      var3.shadow(var60, var40, var59, 30.0F, 15.0F, 20.0F, 0.0F, 0.0F, 6.0F, 1711276032);
      var3.roundRect(var60, var40, var59, 30.0F, 15.0F, Theme.tint(-300872416));
      var3.roundRectOutlineGradient(var60, var40, var59, 30.0F, 15.0F, 1.0F, 90.0F, 788529151, 184549375);
      var3.icon("hand-grab", var60 + 12.0F + 6.0F, var40 + 15.0F, 12.0F, Icons.PURPLE);
      var3.text(Fonts.SEMIBOLD, var33, var60 + 12.0F + 12.0F + 6.0F, var40 + 15.0F, 9.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      float var41 = var60 + 12.0F + 12.0F + 6.0F + var34;

      for (HandMovementRenderer2.TypeM var45 : HandMovementRenderer2.TypeM.values()) {
         float var46 = A(var21, var45) ? 14.0F : 5.0F;
         if (A(var21, var45)) {
            var3.rect(var41 + var46 / 2.0F - 0.5F, var40 + 8.0F, 1.0F, 14.0F, 486539263);
         }

         boolean var7;
         float var47;
         float var48;
         float var49;
         label104: {
            var41 += var46;
            var47 = this.X(var3, var45, var14, (short)var15, (short)var16);
            var48 = 22.0F;
            var49 = var40 + (30.0F - var48) / 2.0F;
            var45.s[0] = var41;
            var45.s[1] = var49;
            var45.s[2] = var47;
            var45.s[3] = var48;
            if (this.N == null) {
               boolean var50 = var2.masked();
               if (!var50 && W(var17, var45.s, var2.mouseX, var2.mouseY)) {
                  var7 = true;
                  break label104;
               }
            }

            var7 = false;
         }

         if (var7) {
            var2.claim(var45, DrawContext.TypeE.HAND);
         }

         float var51 = var45.T.target(var7 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var2.dt));
         boolean var52 = this.q(var45, (char)var25, var26, (char)var27);
         boolean var53 = var45 == HandMovementRenderer2.TypeM.y && this.R(var19, var2.time);
         if (var53) {
            float var54 = var48 / 2.0F;
            int var55 = BlendMode.withAlpha(Icons.MAGENTA, 0.85F + 0.15F * var51);
            float var56 = 0.85F + 0.15F * var51;
            var3.roundRectGradient(var41, var49, var47, var48, var54, 0.0F, var55, BlendMode.withAlpha(Icons.PINK, var56));
         } else if (var52) {
            float var63 = var48 / 2.0F;
            var3.roundRectGradient(
               var41,
               var49,
               var47,
               var48,
               var63,
               0.0F,
               BlendMode.withAlpha(Icons.INDIGO, 0.85F + 0.15F * var51),
               BlendMode.withAlpha(Icons.PURPLE, 0.85F + 0.15F * var51)
            );
         } else {
            var3.roundRect(var41, var49, var47, var48, var48 / 2.0F, BlendMode.lerp(352321535, 620756991, var51));
         }

         var3.roundRectOutline(var41, var49, var47, var48, var48 / 2.0F, 1.0F, BlendMode.lerp(452984831, 1090519039, var51));
         int var8;
         if (!var52 && !var53) {
            var8 = BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var51);
         } else {
            var8 = Icons.TEXT;
         }

         String var9;
         label84: {
            if (var45 == HandMovementRenderer2.TypeM.u) {
               boolean var64 = HandMovementSupport4.demoSwing();
               if (var64) {
                  var9 = "pause";
                  break label84;
               }
            }

            var9 = var45.W;
         }

         if (var45.b == null) {
            var3.icon(var9, var41 + var47 / 2.0F, var49 + var48 / 2.0F, 12.0F, var8);
         } else {
            String var65 = var53 ? "Confirm" : var45.b;
            var3.icon(
               var9,
               var41 + 11.0F + 5.5F,
               var49 + var48 / 2.0F,
               11.0F,
               var45 == HandMovementRenderer2.TypeM.r ? BlendMode.lerp(Icons.PURPLE, Icons.TEXT, var51) : var8
            );
            var3.text(Fonts.MEDIUM, var65, var41 + 11.0F + 11.0F + 5.0F, var49 + var48 / 2.0F, 8.8F, var8, FontStyle.LEFT, FontWeight.MIDDLE);
         }

         var41 += var47;
      }
   }

   private static boolean A(long var0, HandMovementRenderer2.TypeM var2) {
      SwingStyle.ensureSourceClassInitialized();
      long var3 = 138549985122853L;
      if (138549985122853L == 0L) {
         boolean var10000 = false;
      } else {
         int var6 = var3 < 0L ? -1 : 1;
      }

      return var2 == HandMovementRenderer2.TypeM.u || var2 == HandMovementRenderer2.TypeM.G || var2 == HandMovementRenderer2.TypeM.y;
   }

   private float X(Canvas var1, HandMovementRenderer2.TypeM var2, int var3, short var4, short var5) {
      long var6 = 37876168305079L;
      long var8 = var6 ^ 109585057038385L;
      SwingStyle.ensureSourceClassInitialized();
      if (var2.b == null) {
         return 26.0F;
      } else {
         if (var2 == HandMovementRenderer2.TypeM.y) {
            boolean var11 = this.R(var8, RenderPipelines.get().time());
            if (var11) {
               String var12 = "Confirm";
               return 27.0F + var1.textWidth(Fonts.MEDIUM, var12, 8.8F) + 11.0F;
            }
         }

         String var10 = var2.b;
         return 27.0F + var1.textWidth(Fonts.MEDIUM, var10, 8.8F) + 11.0F;
      }
   }

   private boolean q(HandMovementRenderer2.TypeM var1, char var2, int var3, char var4) {
      SwingStyle.ensureSourceClassInitialized();
      int var5 = var1.ordinal();

      return switch (var5) {
         case 0 -> HandMovementSupport4.demoSwing();
         case 1 -> HandMovementSupport4.demoWalk();
         default -> var1.shown() == this.O;
      };
   }

   private boolean R(long var1, float var3) {
      SwingStyle.ensureSourceClassInitialized();
      return var3 - this.z < 2.5F;
   }

   private void U(DrawContext var1, Canvas var2, float var3, long var4, float var6) {
      long var7 = 48745893361102L;
      SwingStyle.ensureSourceClassInitialized();
      String var9 = this.N(var1.time, var7);
      if (!var9.equals(this.K)) {
         this.X = this.K;
         this.K = var9;
         this.m = var1.time;
      }

      float var10 = var2.textWidth(Fonts.MEDIUM, this.K, 7.8F) + 22.0F;
      long var11 = 3915514553642L;
      if (3915514553642L == 0L) {
         boolean var10000 = false;
      } else {
         int var23 = var11 < 0L ? -1 : 1;
      }

      float var14 = this.I.value() <= 0.0F ? this.I.snap(var10).value() : this.I.target(var10).update(var1.dt);
      float var15 = var3 - var14 / 2.0F;
      float var16 = Easing.STANDARD.apply(Math.max(0.0F, Math.min(1.0F, (var1.time - this.m) / 0.22F)));
      float var17 = var6 + 8.5F;
      long var18 = 3915514553642L;
      if (3915514553642L == 0L) {
         boolean var24 = false;
      } else {
         int var25 = var18 < 0L ? -1 : 1;
      }

      long var20 = 3915514553642L;
      if (3915514553642L == 0L) {
         boolean var26 = false;
      } else {
         int var27 = var20 < 0L ? -1 : 1;
      }

      var2.roundRect(var15, var6, var14, 17.0F, 8.5F, Theme.tint(-703525600));
      var2.roundRectOutline(var15, var6, var14, 17.0F, 8.5F, 1.0F, 452984831);
      var2.pushClip(var15, var6, var14, 17.0F, 8.5F);
      if (var16 < 1.0F && !this.X.isEmpty()) {
         var2.pushAlpha(1.0F - var16);
         var2.text(Fonts.MEDIUM, this.X, var3, var17 - 5.0F * var16, 7.8F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
         var2.popAlpha();
      }

      var2.pushAlpha(var16);
      var2.text(Fonts.MEDIUM, this.K, var3, var17 + 5.0F * (1.0F - var16), 7.8F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
      var2.popAlpha();
      var2.popClip();
   }

   private String N(float var1, long var2) {
      long var4 = 72398014212486L;
      long var6 = 24675105398404L;
      long var8 = 41043952577554L;
      long var10 = 98296624401491L;
      short var12 = 5301;
      int var13 = -2097224442;
      SwingStyle.ensureSourceClassInitialized();
      if (this.R(var4, var1)) {
         return "Click again to put " + this.U(this.c(var6), var10).toLowerCase(Locale.ROOT) + " back where Minecraft holds it";
      } else {
         if (this.N != null) {
            long var14 = 48745893361101L;
            if (48745893361101L == 0L) {
               boolean var10000 = false;
            } else {
               int var26 = var14 < 0L ? -1 : 1;
            }

            long var17 = 48745893361102L;
            if (48745893361102L == 0L) {
               boolean var27 = false;
            } else {
               int var28 = var17 < 0L ? -1 : 1;
            }

            if (this.g) {
               long var20 = 48745893361102L;
               if (48745893361102L == 0L) {
                  boolean var35 = false;
               } else {
                  int var36 = var20 < 0L ? -1 : 1;
               }
               return switch (this.J) {
                  case 0 -> "Near where Minecraft holds it, it settles there  ·  Hold Alt to place it freely";
                  default -> c(var8) == class_1268.field_5808 ? "Turning freely" : (h(var12, var13) ? "Rolling  ·  Let go of Shift to turn" : "Turning  ·  Hold Shift to roll");
               };
            }
         }

         long var22 = 48745893361102L;
         if (48745893361102L == 0L) {
            boolean var29 = false;
         } else {
            int var30 = var22 < 0L ? -1 : 1;
         }

         if (this.Y != null) {
            return "Drag to move  ·  Right-drag to turn  ·  Scroll to resize  ·  Double-click to reset";
         } else {
            if (this.l[0] < 3) {
               int var23 = this.l[1];
               long var18 = 48745893361101L;
               if (48745893361101L == 0L) {
                  boolean var31 = false;
               } else {
                  int var32 = var18 < 0L ? -1 : 1;
               }

               if (var23 < 3) {
                  return "Your hand is out of view: pick something to hold below";
               }
            }

            boolean var24 = this.i.switchedOn();
            long var25 = 48745893361102L;
            if (48745893361102L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var25 < 0L ? -1 : 1;
            }

            return !var24 ? "Hand Movement is off: turn it on to use this in game" : "Point at your hand, then drag it where you like it  ·  Space to hit";
         }
      }
   }

   @Override
   public boolean mouseDown(float var1, float var2, int var3) {
      long var4 = 129101307624825L;
      long var6 = var4 ^ 128088987861030L;
      long var8 = var4 ^ 122478540437771L;
      long var10 = var4 ^ 133180436543206L;
      long var12 = var4 ^ 66251604225196L;
      long var14 = (var4 ^ 56974222470632L) >>> 16;
      int var16 = (int)((var4 ^ 56974222470632L) << 48 >>> 48);
      int var17 = (int)((var4 ^ 107611469535359L) >>> 32);
      int var18 = (int)((var4 ^ 107611469535359L) << 32 >>> 32);
      if (!U) {
         return false;
      } else if (W(var10, this.r, var1, var2)) {
         if (var3 == 0) {
            this.n(var1, var2, var8);
         }

         return true;
      } else {
         class_1268 var19 = this.f(var1, var14, (short)var16) ? this.w(var6, var1, var2) : null;
         if (var19 != null && var3 <= 1) {
            if (!this.i.A.enabled()) {
               this.i.H.set(var19 == class_1268.field_5808 ? "Main hand" : "Off hand");
            }

            long var20 = System.currentTimeMillis();
            if (var3 == 0 && this.c == var19 && var20 - this.D < 320L) {
               this.c = null;
               this.V(var19, var12).reset();
               this.R = "Back where Minecraft holds it";
               this.T = RenderPipelines.get().time();
               this.E.snap(0.0F).impulse(10.0F);
               HandMovementScreen.play(HandMovementKind.SELECT, 1.0F, 0.7F);
               return true;
            } else {
               this.D = var20;
               this.c = var19;
               this.N = var19;
               this.J = var3;
               this.s = this.b = var1;
               this.a = this.w = var2;
               this.g = false;
               HandMovementModule.HandPlacement var22 = this.V(var19, var12);
               this.M = var3 == 0 ? var22.V.get() : (h(var17, var18) ? var22.Y.get() : var22.I.get());
               this.Q = var3 == 0 ? var22.t.get() : var22.r.get();
               this.A = this.S = false;
               HandMovementScreen.play(HandMovementKind.CLICK, 1.05F, 0.8F);
               return true;
            }
         } else {
            return false;
         }
      }
   }

   @Override
   public void mouseDrag(float var1, float var2) {
      long var3 = 62113983525542L;
      long var5 = var3 ^ 19140954932310L;
      long var7 = var3 ^ 55879581552583L;
      long var9 = var3 ^ 124613429109619L;
      int var11 = (int)((var3 ^ 49252723234720L) >>> 32);
      int var12 = (int)((var3 ^ 49252723234720L) << 32 >>> 32);
      class_1268 var13 = this.N;
      if (var13 != null) {
         float var14 = var1 - this.s;
         float var15 = var2 - this.a;
         this.s = var1;
         this.a = var2;
         if (this.g || !(Math.hypot(var1 - this.b, var2 - this.w) < 2.0)) {
            this.g = true;
            HandMovementModule.HandPlacement var16 = this.V(var13, var9);
            int var17 = z(var5, var13);
            if (this.J == 0) {
               float[] var18 = HandMovementSupport4.shown(var13);
               float var19 = 0.72F + var18[2];
               double var20 = var19 * Math.tan(Math.toRadians(HandMovementSupport2.fov()) / 2.0) / (this.Z / 2.0F);
               this.M += var17 * var14 * var20;
               this.Q -= var15 * var20;
               this.A = this.g(var16.V, this.M, 0.015, var7, this.A);
               this.S = this.g(var16.t, this.Q, 0.015, var7, this.S);
               this.R = "Sideways " + var16.V.display() + "   Height " + var16.t.display();
            } else if (h(var11, var12)) {
               this.M += var17 * var14 * 0.45;
               this.A = this.g(var16.Y, this.M, 2.5, var7, this.A);
               this.R = "Roll " + var16.Y.display();
            } else {
               this.M += var17 * var14 * 0.45;
               this.Q += var15 * 0.45;
               this.A = this.g(var16.I, this.M, 2.5, var7, this.A);
               this.S = this.g(var16.r, this.Q, 2.5, var7, this.S);
               this.R = "Turn " + var16.I.display() + "   Tilt " + var16.r.display();
            }

            this.T = RenderPipelines.get().time();
         }
      }
   }

   private boolean g(NumberSetting var1, double var2, double var4, long var6, boolean var8) {
      long var9 = 41043952577554L;
      SwingStyle.ensureSourceClassInitialized();
      boolean var11 = c(var9) != class_1268.field_5808 && Math.abs(var2) < var4;
      var1.set(var11 ? 0.0 : var2);
      if (var11 && !var8) {
         HandMovementScreen.play(HandMovementKind.ORBIT_TICK, 1.1F, 0.6F);
      }

      return var11;
   }

   @Override
   public void mouseUp(float var1, float var2, int var3) {
      if (this.N != null && this.g) {
         HandMovementScreen.play(HandMovementKind.TAB, 0.95F);
      }

      this.N = null;
   }

   @Override
   public boolean scroll(float var1, float var2, double var3) {
      long var5 = 137983004370972L;
      long var7 = var5 ^ 136799489774915L;
      long var9 = var5 ^ 57541576145353L;
      long var11 = var5 ^ 117715206518424L;
      long var13 = (var5 ^ 65718606100621L) >>> 16;
      int var15 = (int)((var5 ^ 65718606100621L) << 48 >>> 48);
      int var16 = (int)((var5 ^ 116321287867674L) >>> 32);
      int var17 = (int)((var5 ^ 116321287867674L) << 32 >>> 32);
      if (U && var3 != 0.0 && this.f(var1, var13, (short)var15)) {
         class_1268 var18 = this.w(var7, var1, var2);
         if (var18 == null) {
            var18 = this.c(var11);
         }

         HandMovementModule.HandPlacement var19 = this.V(var18, var9);
         int var20 = var3 > 0.0 ? 1 : -1;
         NumberSetting var21 = h(var16, var17) ? var19.i : var19.f;
         double var22 = var21.get();
         var21.set(var22 + (h(var16, var17) ? -0.02 : 0.05) * var20);
         if (var21.get() == var22) {
            HandMovementScreen.play(HandMovementKind.DENY, 1.0F, 0.6F);
            return true;
         } else {
            this.R = (h(var16, var17) ? "Depth " : "Size ") + var21.display();
            this.T = RenderPipelines.get().time();
            this.E.snap(0.0F).impulse(12.0F);
            HandMovementScreen.play(HandMovementKind.SLIDER_TICK, 0.8F + 0.5F * (float)var21.normalized());
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public boolean keyPressed(int var1) {
      long var2 = 13951940845206L;
      long var4 = var2 ^ 41398657607782L;
      long var6 = var2 ^ 76534886633283L;
      long var8 = var2 ^ 29418086858770L;
      int var10 = (int)((var2 ^ 26413822688144L) >>> 32);
      int var11 = (int)((var2 ^ 26413822688144L) << 32 >>> 32);
      if (!U) {
         return false;
      } else {
         HandMovementModule.HandPlacement var12 = this.V(this.c(var8), var6);
         double var13 = h(var10, var11) ? 0.05 : 0.01;
         switch (var1) {
            case 32:
               HandMovementSupport4.hitOnce();
               return true;
            case 262:
               K(var12.V, var13 * z(var4, this.c(var8)));
               break;
            case 263:
               K(var12.V, -var13 * z(var4, this.c(var8)));
               break;
            case 264:
               K(var12.t, -var13);
               break;
            case 265:
               K(var12.t, var13);
               break;
            default:
               return false;
         }

         this.R = "Sideways " + var12.V.display() + "   Height " + var12.t.display();
         this.T = RenderPipelines.get().time();
         return true;
      }
   }

   private static void K(NumberSetting var0, double var1) {
      var0.set(var0.get() + var1);
      HandMovementScreen.play(HandMovementKind.SLIDER_TICK, 0.8F + 0.5F * (float)var0.normalized(), 0.7F);
   }

   private void n(float var1, float var2, long var3) {
      long var5 = 13515205692319L;
      long var7 = 72398014212486L;
      long var9 = 80452702523861L;
      long var11 = 24675105398404L;
      SwingStyle.ensureSourceClassInitialized();
      HandMovementRenderer2.TypeM[] var13 = HandMovementRenderer2.TypeM.values();

      for (HandMovementRenderer2.TypeM var17 : var13) {
         int var18 = (W(var5, var17.s, var1, var2) ? 1 : 0);
         long var19 = 28649519678578L;
         if (28649519678578L == 0L) {
            boolean var10000 = false;
         } else {
            int var39 = var19 < 0L ? -1 : 1;
         }

         if ((var18 != 0)) {
            float var38 = RenderPipelines.get().time();
            var18 = var17.ordinal();
            long var23 = 28649519678578L;
            if (28649519678578L == 0L) {
               boolean var42 = false;
            } else {
               int var43 = var23 < 0L ? -1 : 1;
            }

            switch (var18) {
               case 0:
                  HandMovementSupport4.setDemoSwing(!HandMovementSupport4.demoSwing());
                  HandMovementScreen.play(HandMovementSupport4.demoSwing() ? HandMovementKind.TOGGLE_ON : HandMovementKind.TOGGLE_OFF);
                  long var25 = 28649519678578L;
                  if (28649519678578L == 0L) {
                     boolean var50 = false;
                  } else {
                     int var51 = var25 < 0L ? -1 : 1;
                  }
                  break;
               case 1:
                  HandMovementSupport4.setDemoWalk(!HandMovementSupport4.demoWalk());
                  HandMovementScreen.play(HandMovementSupport4.demoWalk() ? HandMovementKind.TOGGLE_ON : HandMovementKind.TOGGLE_OFF);
                  break;
               case 2:
               case 3:
               case 4:
               case 5:
               case 6:
               case 7:
               default:
                  HandMovementRenderer2.TypeW var27 = var17.shown();
                  long var28 = 28649519678578L;
                  if (28649519678578L == 0L) {
                     boolean var48 = false;
                  } else {
                     int var49 = var28 < 0L ? -1 : 1;
                  }

                  if (var27 != null && var27 != this.O) {
                     this.O = var27;
                     HandMovementSupport4.setPreview(var27.stack());
                     HandMovementScreen.play(HandMovementKind.SELECT, 1.1F, 0.7F);
                  }
                  break;
               case 8:
                  boolean var31 = this.R(var7, var38);
                  long var32 = 28649519678577L;
                  if (28649519678577L == 0L) {
                     boolean var44 = false;
                  } else {
                     int var45 = var32 < 0L ? -1 : 1;
                  }

                  if (var31) {
                     this.z = -10.0F;
                     this.V(this.c(var11), var9).reset();
                     HandMovementScreen.play(HandMovementKind.COLLAPSE);
                  } else {
                     this.z = var38;
                     long var34 = 28649519678577L;
                     if (28649519678577L == 0L) {
                        boolean var46 = false;
                     } else {
                        int var47 = var34 < 0L ? -1 : 1;
                     }

                     HandMovementScreen.play(HandMovementKind.TAB, 1.15F);
                  }
                  break;
               case 9:
                  this.e = true;
                  HandMovementScreen.play(HandMovementKind.CLICK);
            }

            return;
         }

         long var22 = 28649519678577L;
         if (28649519678577L == 0L) {
            boolean var40 = false;
         } else {
            int var41 = var22 < 0L ? -1 : 1;
         }
      }
   }

   public void devDrag(float var1, float var2, int var3, boolean var4) {
      int var5 = this.l[0];
      if (var5 >= 3) {
         float var6 = 0.0F;
         float var7 = 0.0F;

         for (int var8 = 0; var8 < var5; var8++) {
            var6 += this.C[0][var8 * 2];
            var7 += this.C[0][var8 * 2 + 1];
         }

         float var11 = Math.max(this.V + 4.0F, Math.min(var6 / var5, this.W - 4.0F));
         float var9 = var7 / var5;
         if (this.mouseDown(var11, var9, var3)) {
            for (int var10 = 1; var10 <= 10; var10++) {
               this.mouseDrag(var11 + var1 * var10 / 10.0F, var9 + var2 * var10 / 10.0F);
            }

            if (!var4) {
               this.mouseUp(var11 + var1, var9 + var2, var3);
            }
         }
      }
   }

   public void devScroll(int var1) {
      long var2 = 26141509730419L;
      long var4 = var2 ^ 69063533098627L;
      float var6 = z(var4, class_1268.field_5808) > 0 ? this.k - 60.0F : 60.0F;

      for (int var7 = 0; var7 < Math.abs(var1); var7++) {
         this.scroll(var6, this.Z - 120.0F, Integer.signum(var1));
      }
   }

   public void devChip(String var1) {
      long var2 = 98285606769729L;
      long var4 = var2 ^ 74138028609587L;
      HandMovementRenderer2.TypeM var6 = HandMovementRenderer2.TypeM.valueOf(var1.toUpperCase(Locale.ROOT));
      if (var6.s[2] > 0.0F) {
         this.n(var6.s[0] + var6.s[2] / 2.0F, var6.s[1] + var6.s[3] / 2.0F, var4);
      }
   }

   private boolean f(float var1, long var2, short var4) {
      SwingStyle.ensureSourceClassInitialized();
      return var1 >= this.V && var1 <= this.W;
   }

   
   private class_1268 w(long var1, float var3, float var4) {
      long var5 = 59125094519349L;
      long var7 = 104412560494105L;
      class_1268 var9 = null;
      float var10 = 10.0F;
      SwingStyle.ensureSourceClassInitialized();
      class_1268[] var11 = class_1268.values();

      for (class_1268 var15 : var11) {
         int var16 = j(var7, var15);
         float var17 = y(this.C[var16], var5, this.l[var16], var3, var4);
         long var18 = 1192776940895L;
         if (1192776940895L == 0L) {
            boolean var10000 = false;
         } else {
            int var23 = var18 < 0L ? -1 : 1;
         }

         long var21 = 1192776940894L;
         if (1192776940894L == 0L) {
            boolean var24 = false;
         } else {
            int var25 = var21 < 0L ? -1 : 1;
         }

         if (var17 < var10) {
            var10 = var17;
            var9 = var15;
         }
      }

      return var9;
   }

   private static float y(float[] var0, long var1, int var3, float var4, float var5) {
      SwingStyle.ensureSourceClassInitialized();
      long var6 = 59125094519348L;
      if (59125094519348L == 0L) {
         boolean var10000 = false;
      } else {
         int var35 = var6 < 0L ? -1 : 1;
      }

      if (var3 < 3) {
         return Float.MAX_VALUE;
      } else {
         boolean var9 = true;
         float var10 = 0.0F;
         float var11 = Float.MAX_VALUE;

         for (int var12 = 0; var12 < var3; var12++) {
            int var13 = (var12 + 1) % var3;
            float var14 = var0[var12 * 2];
            float var15 = var0[var12 * 2 + 1];
            float var16 = var0[var13 * 2];
            float var17 = var0[var13 * 2 + 1];
            float var18 = (var16 - var14) * (var5 - var15) - (var17 - var15) * (var4 - var14);
            long var19 = 59125094519349L;
            if (59125094519349L == 0L) {
               boolean var36 = false;
            } else {
               int var37 = var19 < 0L ? -1 : 1;
            }

            if (var18 != 0.0F) {
               float var21;
               int var22 = (var21 = var10 - 0.0F) == 0.0F ? 0 : (var21 < 0.0F ? -1 : 1);
               long var23 = 59125094519348L;
               if (59125094519348L == 0L) {
                  boolean var38 = false;
               } else {
                  int var39 = var23 < 0L ? -1 : 1;
               }

               if (var22 == 0) {
                  long var26 = 59125094519348L;
                  if (59125094519348L == 0L) {
                     boolean var40 = false;
                  } else {
                     int var41 = var26 < 0L ? -1 : 1;
                  }

                  var10 = Math.signum(var18);
               } else {
                  float var34;
                  var22 = (var34 = Math.signum(var18) - var10) == 0.0F ? 0 : (var34 < 0.0F ? -1 : 1);
                  long var27 = 59125094519348L;
                  if (59125094519348L == 0L) {
                     boolean var42 = false;
                  } else {
                     int var43 = var27 < 0L ? -1 : 1;
                  }

                  if (var22 != 0) {
                     var9 = false;
                  }
               }
            }

            float var30 = var16 - var14;
            float var32 = var17 - var15;
            float var33 = Math.clamp(((var4 - var14) * var30 + (var5 - var15) * var32) / Math.max(1.0E-6F, var30 * var30 + var32 * var32), 0.0F, 1.0F);
            var11 = Math.min(var11, (float)Math.hypot(var4 - (var14 + var30 * var33), var5 - (var15 + var32 * var33)));
         }

         long var29 = 59125094519349L;
         if (59125094519349L == 0L) {
            boolean var44 = false;
         } else {
            int var45 = var29 < 0L ? -1 : 1;
         }

         return var9 ? 0.0F : var11;
      }
   }

   private HandMovementModule.HandPlacement V(class_1268 var1, long var2) {
      SwingStyle.ensureSourceClassInitialized();
      if (var1 != class_1268.field_5808) {
         long var4 = 80452702523860L;
         if (80452702523860L == 0L) {
            boolean var10000 = false;
         } else {
            int var7 = var4 < 0L ? -1 : 1;
         }

         if (!this.i.A.enabled()) {
            return this.i.a;
         }
      }

      return this.i.p;
   }

   private class_1268 c(long var1) {
      SwingStyle.ensureSourceClassInitialized();
      return !this.i.A.enabled() && !this.i.H.is("Main hand") ? class_1268.field_5810 : class_1268.field_5808;
   }

   private String U(class_1268 var1, long var2) {
      boolean var4 = this.i.A.enabled();
      long var5 = 98296624401490L;
      if (98296624401490L == 0L) {
         boolean var10000 = false;
      } else {
         int var8 = var5 < 0L ? -1 : 1;
      }

      if (var4) {
         return "Both hands";
      } else {
         return var1 == class_1268.field_5808 ? "Main hand" : "Off hand";
      }
   }

   private static int z(long var0, class_1268 var2) {
      SwingStyle.ensureSourceClassInitialized();
      class_746 var3 = class_310.method_1551().field_1724;
      class_1306 var4 = var3 != null ? var3.method_6068() : class_1306.field_6183;
      class_1306 var5 = var2 == class_1268.field_5808 ? var4 : var4.method_5928();
      return var5 == class_1306.field_6183 ? 1 : -1;
   }

   private static int j(long var0, class_1268 var2) {
      return var2 == class_1268.field_5808 ? 0 : 1;
   }

   private static boolean h(int var0, int var1) {
      SwingStyle.ensureSourceClassInitialized();
      class_1041 var2 = class_310.method_1551().method_22683();
      return class_3675.method_15987(var2, 340) || class_3675.method_15987(var2, 344);
   }

   private static boolean W(long var0, float[] var2, float var3, float var4) {
      SwingStyle.ensureSourceClassInitialized();
      if (var2[2] > 0.0F) {
         long var5 = 13515205692318L;
         if (13515205692318L == 0L) {
            boolean var10000 = false;
         } else {
            int var11 = var5 < 0L ? -1 : 1;
         }

         if (var3 >= var2[0] && var3 <= var2[0] + var2[2]) {
            long var8 = 13515205692319L;
            if (13515205692319L == 0L) {
               boolean var12 = false;
            } else {
               int var13 = var8 < 0L ? -1 : 1;
            }

            if (var4 >= var2[1] && var4 <= var2[1] + var2[3]) {
               return true;
            }
         }
      }

      return false;
   }

   private static float A(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

private static enum TypeM {
      u("Swing", "play"),
      D("Walk", "footprints"),
      G(null, "hand-grab"),
      n(null, "sword"),
      P(null, "pickaxe"),
      U(null, "axe"),
      F(null, "box"),
      K(null, "hand"),
      y("Reset", "rotate-ccw"),
      r("Done", "check");

      
      final String b;
      final String W;
      final float[] s = new float[4];
      final Animator T = Animator.smooth(0.0F);
      private static final long[] a = new long[]{
         -4119573843248648195L, 3745770704461229640L, -6753188904328871356L, 7931749428903943833L, 8047322515016822180L
      };
      private static final Integer[] c = new Integer[5];

      private TypeM( String nullxx, String nullxxx) {
         this.b = nullxx;
         this.W = nullxxx;
      }

      
      HandMovementRenderer2.TypeW shown() {
         return switch (this) {
            case G -> HandMovementRenderer2.TypeW.m;
            case n -> HandMovementRenderer2.TypeW.r;
            case P -> HandMovementRenderer2.TypeW.i;
            case U -> HandMovementRenderer2.TypeW.T;
            case F -> HandMovementRenderer2.TypeW.M;
            case K -> HandMovementRenderer2.TypeW.q;
            default -> null;
         };
      }

}

   private static enum TypeW {
      m("Held", "hand-grab", null),
      r("Sword", "sword", class_1802.field_8802),
      i("Pickaxe", "pickaxe", class_1802.field_8377),
      T("Axe", "axe", class_1802.field_8556),
      M("Block", "box", class_1802.field_27064),
      q("Hand", "hand", class_1802.field_8162);

      final String Y;
      final String A;
      
      final class_1792 W;
      private static final long a = -2973152696468504570L;

      private TypeW(String nullxx, String nullxxx,  class_1792 nullxxxx) {
         this.Y = nullxx;
         this.A = nullxxx;
         this.W = nullxxxx;
      }

      
      class_1799 stack() {
         return this.W == null ? null : (this.W == class_1802.field_8162 ? class_1799.field_8037 : new class_1799(this.W));
      }

}
}
