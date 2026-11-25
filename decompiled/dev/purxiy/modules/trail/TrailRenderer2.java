package dev.purxiy.modules.trail;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import dev.purxiy.loader.fabric.ClientServices;
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
import net.minecraft.class_6367;
import org.joml.Matrix4f;

public final class TrailRenderer2 {
   private static final int D = 2;
   private static final float J = 8.0F;
   private static final float a = 51.0F;
   private static final float W = 94.0F;
   private static final float T = 121.0F;
   private static final int o = 2;
   public static final float O = 307.0F;
   private static final TrailStyle[] k = TrailStyle.values();
   private static final class_6367[] Q = new class_6367[k.length];
   private static final TrailSupport4.TypeI[] U = new TrailSupport4.TypeI[k.length];
   private static final TrailSupport2 L = new TrailSupport2();
   private final TrailModule K;
   private final Object[] e = new Object[k.length];
   private final TrailRenderer2.TypeM[] y = new TrailRenderer2.TypeM[k.length];
   private final int[] x = new int[3];
   private float M;
   private float j;
   private float V;
   private boolean p;
   private static final String[] c = new String[2];

   public TrailRenderer2(TrailModule var1) {
      this.K = var1;

      for (int var2 = 0; var2 < k.length; var2++) {
         this.e[var2] = new Object();
         this.y[var2] = new TrailRenderer2.TypeM(k[var2], var2);
      }
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.M = var2;
      this.j = var3;
      this.V = var4;
      this.p = var5 && !var1.masked();
      Canvas var6 = var1.canvas;
      int var7 = Math.clamp((long)this.K.X.index(), 0, k.length - 1);
      var6.text(Fonts.MEDIUM, "Choose a look", var2, var3 + 10.0F, 13.5F, Icons.TEXT);
      var6.text(Fonts.REGULAR, k[var7].p, var2, var3 + 30.0F, 11.5F, Icons.TEXT_2);
      float var8 = this.x();
      float var9 = Math.min(var1.dt, 0.1F);
      int[] var10 = TrailRenderer.palette(this.K, this.x);

      for (int var11 = 0; var11 < k.length; var11++) {
         float var12 = this.Z(var11);
         float var13 = this.c(var11);
         boolean var14 = var11 == var7;
         boolean var15 = this.p && var1.hover(this.e[var11], var12, var13, var8, 121.0F, TypeE.HAND);
         var6.roundRect(var12, var13, var8, 121.0F, 10.0F, var14 ? Theme.tint(-13623231) : (var15 ? Theme.tint(-14542287) : Theme.tint(-15725540)));
         float var16 = var12 + 3.0F;
         float var17 = var13 + 3.0F;
         float var18 = var8 - 6.0F;
         float var19 = 91.0F;
         var6.pushClip(var16, var17, var18, var19, 8.0F);
         this.y[var11].render(var6, var16, var17, var18, var19, var9, var10, this.K);
         var6.popClip();
         var6.roundRectOutline(var12, var13, var8, 121.0F, 10.0F, 1.0F, var14 ? Theme.tint(-4222985) : Theme.tint(633708021));
         var6.text(
            Fonts.MEDIUM, k[var11].Z, var12 + var8 / 2.0F, var13 + 94.0F + 12.0F, 10.5F, var14 ? Icons.TEXT : Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE
         );
      }
   }

   private float x() {
      return (this.V - 8.0F) / 2.0F;
   }

   private float Z(int var1) {
      return this.M + var1 % 2 * (this.x() + 8.0F);
   }

   private float c(int var1) {
      return this.j + 51.0F + var1 / 2 * 129.0F;
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      if (this.p && var3 == 0) {
         for (int var4 = 0; var4 < k.length; var4++) {
            float var5 = this.Z(var4);
            float var6 = this.c(var4);
            if (var1 >= var5 && var1 < var5 + this.x() && var2 >= var6 && var2 < var6 + 121.0F) {
               if (this.K.X.index() != var4) {
                  this.K.X.select(var4);
                  UiAudioPolicy.play("CLICK");
               }

               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private static final class TypeM {
      private static final float x = 3.0F;
      private static final float S = 3.7F;
      private static final float E = 0.3F;
      private static final float b = (float)Math.toRadians(34.0);
      private static final float K = 2.5F;
      private static final float v = 2.2F;
      private static final float G = 2.0F;
      private static final float o = 1.3F;
      private static final float Q = 1.3F;
      private final TrailStyle w;
      private final int j;
      private final TrailSupport c;
      private final TrailSupport2.TypeF P = new TrailSupport2.TypeF();
      private final TrailSupport4.TypeN F = new TrailSupport4.TypeN();
      private final Matrix4f d = new Matrix4f();
      private final Matrix4f t = new Matrix4f();
      private double R;
      private float a;
      private float Z;
      private double I;
      private double z;
      private double s;
      private String Y;
      private static final long[] g = new long[]{-705432166487766562L, 7358668628964115509L, -7271703775052600303L};

      TypeM(TrailStyle var1, int var2) {
         this.w = var1;
         this.j = var2;
         this.c = new TrailSupport(31 + var2 * 17);
         this.Z = 0.4F + var2 * 1.45F;
      }

      void render(Canvas var1, float var2, float var3, float var4, float var5, float var6, int[] var7, TrailModule var8) {
         long var9 = 133646663436390L;
         long var11 = var9 ^ 107126287476635L;
         int var13 = (int)((var9 ^ 107126287476635L) >>> 32);
         int var14 = (int)((var9 ^ 107126287476635L) << 32 >>> 48);
         int var15 = (int)(var11 << 48 >>> 48);
         long var16 = var9 ^ 134754369723811L;
         long var18 = var9 ^ 114145567892546L;
         float var20 = var8.B.floatValue() * 1.3F;
         float var21 = var8.t.floatValue() * 1.3F;
         float var22 = this.R == 0.0 ? Math.min(var20, 1.5F) : var6;
         int var23 = Math.max(1, (int)Math.ceil(var22 / 0.02F));

         for (int var24 = 0; var24 < var23; var24++) {
            this.U(var22 / var23, var7, var13, var21, var20, var14, (short)var15);
         }

         this.a += var6;
         this.b(var18, var1, var2, var3, var4, var5, var7);

         try {
            this.I(var16, var1, var2, var3, var4, var5, var7, var8, var21, var20);
         } catch (RuntimeException var251) {
            if (!var251.toString().equals(this.Y)) {
               ClientServices.LOGGER.error("Purxiy trail preview could not render", var251);
            }

            this.Y = var251.toString();
         }
      }

      private void b(long var1, Canvas var3, float var4, float var5, float var6, float var7, int[] var8) {
         float var9 = (float)Math.atan2(2.7F, 3.7F);
         float var10 = var5 + var7 * 0.5F + (float)(Math.tan(Math.atan2(3.0, 3.7F) - var9) / Math.tan(b * 0.5F)) * var7 * 0.5F;
         var3.roundRectGradient(var4, var5, var6, var7, 0.0F, 90.0F, Theme.tint(-15988201), Theme.tint(-14936269));
         var3.push();
         var3.translate(var4 + var6 * 0.5F, var10);
         var3.scale(1.0F, 0.34F);
         var3.softCircle(0.0F, 0.0F, var6 * 0.32F, var6 * 0.26F, BlendMode.withAlpha(0xFF000000 | var8[1], 0.12F));
         var3.pop();
      }

      private void U(float var1, int[] var2, int var3, float var4, float var5, int var6, short var7) {
         this.R += var1;
         TrailRenderer.ensureSourceClassInitialized();
         this.Z += var1 * 2.0F;
         float var8 = (float)Math.sin(this.Z);
         float var9 = (float)Math.cos(this.Z);
         this.I = 2.5F * var8;
         this.s = 2.2F * var8 * var9;
         this.z = this.w.atHips() ? 0.85 : 0.0;
         this.c.follow(this.I, this.z, this.s, this.R, var1, this.w, var2, var4, var5);
      }

      private void I(long var1, Canvas var3, float var4, float var5, float var6, float var7, int[] var8, TrailModule var9, float var10, float var11) {
         TrailRenderer.ensureSourceClassInitialized();
         if (TrailSupport4.ready(TrailSupport4.K)) {
            float var12 = (float)Math.hypot(var3.transform().m00(), var3.transform().m01());
            float var13 = Math.max(0.5F, var3.pixelScale() * var12);
            int var14 = Math.max(1, Math.round(var6 * var13));
            int var15 = Math.max(1, Math.round(var7 * var13));
            class_6367 var16 = TrailRenderer2.Q[this.j];
            long var17 = 3315642439109L;
            int var27 = var17 < 0L ? -1 : 1;
            if (var16 == null) {
               var16 = new class_6367("Purxiy trail preview " + this.w.Z, var14, var15, true);
               TrailRenderer2.Q[this.j] = var16;
            } else {
               label49: {
                  if (var16.field_1482 == var14) {
                     long var20 = 3315642439108L;
                     int var211 = var20 < 0L ? -1 : 1;
                     if (var16.field_1481 == var15) {
                        break label49;
                     }
                  }

                  var16.method_1234(var14, var15);
               }
            }

            TrailSupport4.TypeI var27x = TrailRenderer2.U[this.j];
            long var21 = 3315642439109L;
            int var24 = var21 < 0L ? -1 : 1;
            if (var27x == null) {
               var27x = new TrailSupport4.TypeI("preview " + this.w.Z);
               TrailRenderer2.U[this.j] = var27x;
            }

            this.d.setPerspective(b, var6 / var7, 0.05F, 40.0F);
            this.t.setLookAt(0.0F, 0.0F, 0.0F, 0.0F, -2.7F, -3.7F, 0.0F, 1.0F, 0.0F);
            TrailSupport4.TypeN var24x = this.F;
            var24x.n.set(this.d).mul(this.t);
            var24x.n.invert(var24x.w);
            this.t.positiveX(var24x.L);
            this.t.positiveY(var24x.e);
            var24x.V = this.a;
            var24x.r = var9.I.floatValue();
            var24x.Z = var10;
            var24x.g = this.w;
            var24x.B = this.d.m11() * var15 * 0.5F;
            var24x.m = Math.max(4.0F, var15 * 0.05F);
            var24x.H = Math.max(12.0F, var15 * 0.3F);
            var24x.b = 0.7F;
            var24x.T = 0.2F;
            System.arraycopy(var8, 0, var24x.K, 0, 3);
            TrailSupport2.TypeF var25 = this.P;
            var25.O = this.w;
            var25.T = var11;
            var25.E = var10;
            var25.z = var9.I.floatValue();
            var25.X = this.a;
            var25.y = 0.0;
            var25.x = 3.0;
            var25.B = 3.7F;
            var25.b = 0.3F;
            var27x.reset();
            TrailRenderer2.L.path(this.c.j, this.R, var25, var27x);
            TrailRenderer2.L.motes(this.c.b, var25, var27x);
            float var26 = this.w.atHips() ? 0.0F : 0.12F;
            TrailRenderer2.L.orb((float)this.I, (float)(this.z + var26 - 3.0), (float)(this.s - 3.7F), 0.1F * var10, var27x);
            TrailSupport4.drawPreview(var27x, var24x, var16);
            var3.image(
               var16.method_71639(),
               RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR),
               true,
               var4,
               var5,
               var6,
               var7,
               0.0F,
               1.0F,
               1.0F,
               0.0F,
               0.0F,
               -1
            );
         }
      }
   }
}
