// Sourced By DevFFNightmare
package dev.purxiy.render;

import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.i18n.Translations;
import dev.purxiy.render.font.FontFamily;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.font.IconCodepoints;
import dev.purxiy.render.font.TrueTypeFont;
import dev.purxiy.render.pipeline.VertexBuffer;
import dev.purxiy.render.texture.TextureLoader;
import java.util.List;
import net.minecraft.class_12137;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;

public final class Canvas {
   private static final int STACK = 64;
   private static final float AA = 1.5F;
   private static final float OPEN = 1000000.0F;
   private final VertexBuffer quads;
   private final Matrix3x2fStack transform = new Matrix3x2fStack(64);
   private final Matrix3x2f shear = new Matrix3x2f();
   private final float[] alphaStack = new float[64];
   private int alphaDepth;
   private float alpha = 1.0F;
   private final float[] clipStack = new float[320];
   private int clipDepth;
   private float clipX0 = -1000000.0F;
   private float clipY0 = -1000000.0F;
   private float clipX1 = 1000000.0F;
   private float clipY1 = 1000000.0F;
   private float clipR;
   private float width;
   private float height;
   private float pixelScale = 1.0F;
   private float blurStrength = 1.0F;

   Canvas(VertexBuffer var1) {
      this.quads = var1;
   }

   void begin(float var1, float var2, float var3) {
      this.width = var1;
      this.height = var2;
      this.pixelScale = var3;
      this.transform.clear();
      this.alphaDepth = 0;
      this.alpha = 1.0F;
      this.clipDepth = 0;
      this.clipX0 = -1000000.0F;
      this.clipY0 = -1000000.0F;
      this.clipX1 = 1000000.0F;
      this.clipY1 = 1000000.0F;
      this.clipR = 0.0F;
      this.blurStrength = 1.0F;
   }

   
   String unbalanced() {
      return this.alphaDepth == 0 && this.clipDepth == 0 ? null : "alpha depth " + this.alphaDepth + ", clip depth " + this.clipDepth;
   }

   public float width() {
      return this.width;
   }

   public float height() {
      return this.height;
   }

   public float pixelScale() {
      return this.pixelScale;
   }

   public void push() {
      this.transform.pushMatrix();
   }

   public void pop() {
      this.transform.popMatrix();
   }

   public void translate(float var1, float var2) {
      this.transform.translate(var1, var2);
   }

   public void scale(float var1) {
      this.transform.scale(var1);
   }

   public void scale(float var1, float var2) {
      this.transform.scale(var1, var2);
   }

   public void rotate(float var1) {
      this.transform.rotate(var1);
   }

   public void scaleAround(float var1, float var2, float var3) {
      this.transform.translate(var2, var3).scale(var1).translate(-var2, -var3);
   }

   public void rotateAround(float var1, float var2, float var3) {
      this.transform.translate(var2, var3).rotate(var1).translate(-var2, -var3);
   }

   public void shear(float var1) {
      this.transform.mul(this.shear.set(1.0F, 0.0F, var1, 1.0F, 0.0F, 0.0F));
   }

   public Matrix3x2f transform() {
      return this.transform;
   }

   public void pushAlpha(float var1) {
      this.alphaStack[this.alphaDepth++] = this.alpha;
      this.alpha = this.alpha * Math.max(0.0F, Math.min(1.0F, var1));
   }

   public void popAlpha() {
      this.alpha = this.alphaStack[--this.alphaDepth];
   }

   public float alpha() {
      return this.alpha;
   }

   public void pushClip(float var1, float var2, float var3, float var4, float var5) {
      int var6 = this.clipDepth++ * 5;
      this.clipStack[var6] = this.clipX0;
      this.clipStack[var6 + 1] = this.clipY0;
      this.clipStack[var6 + 2] = this.clipX1;
      this.clipStack[var6 + 3] = this.clipY1;
      this.clipStack[var6 + 4] = this.clipR;
      float var7 = this.tx(var1, var2);
      float var8 = this.ty(var1, var2);
      float var9 = this.tx(var1 + var3, var2);
      float var10 = this.ty(var1 + var3, var2);
      float var11 = this.tx(var1 + var3, var2 + var4);
      float var12 = this.ty(var1 + var3, var2 + var4);
      float var13 = this.tx(var1, var2 + var4);
      float var14 = this.ty(var1, var2 + var4);
      this.clipX0 = Math.max(this.clipX0, min4(var7, var9, var11, var13));
      this.clipY0 = Math.max(this.clipY0, min4(var8, var10, var12, var14));
      this.clipX1 = Math.min(this.clipX1, max4(var7, var9, var11, var13));
      this.clipY1 = Math.min(this.clipY1, max4(var8, var10, var12, var14));
      this.clipR = var5 * this.transformScale();
   }

   public void pushClip(float var1, float var2, float var3, float var4) {
      this.pushClip(var1, var2, var3, var4, 0.0F);
   }

   public void popClip() {
      int var1 = --this.clipDepth * 5;
      this.clipX0 = this.clipStack[var1];
      this.clipY0 = this.clipStack[var1 + 1];
      this.clipX1 = this.clipStack[var1 + 2];
      this.clipY1 = this.clipStack[var1 + 3];
      this.clipR = this.clipStack[var1 + 4];
   }

   public void captureBackdrop() {
      this.quads.requestCapture(this.blurStrength);
   }

   public void blurStrength(float var1) {
      this.blurStrength = Math.max(0.0F, Math.min(1.0F, var1));
   }

   public void rect(float var1, float var2, float var3, float var4, int var5) {
      this.box(var1, var2, var3, var4, 0.0F, 0.0F, 0.0F, 0.0F, var5, var5, var5, var5, 0.0F, 0, 0);
   }

   public void roundRect(float var1, float var2, float var3, float var4, float var5, int var6) {
      this.box(var1, var2, var3, var4, var5, var5, var5, var5, var6, var6, var6, var6, 0.0F, 0, 0);
   }

   public void roundRect(float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, int var9) {
      this.box(var1, var2, var3, var4, var5, var6, var7, var8, var9, var9, var9, var9, 0.0F, 0, 0);
   }

   public void roundRectCorners(float var1, float var2, float var3, float var4, float var5, int var6, int var7, int var8, int var9) {
      this.box(var1, var2, var3, var4, var5, var5, var5, var5, var6, var7, var8, var9, 0.0F, 0, 0);
   }

   public void roundRectGradient(float var1, float var2, float var3, float var4, float var5, float var6, int var7, int var8) {
      double var9 = Math.toRadians(var6);
      float var11 = (float)Math.cos(var9);
      float var12 = (float)Math.sin(var9);
      float var13 = var3 * 0.5F;
      float var14 = var4 * 0.5F;
      float var15 = Math.abs(var13 * var11) + Math.abs(var14 * var12);
      this.box(
         var1,
         var2,
         var3,
         var4,
         var5,
         var5,
         var5,
         var5,
         BlendMode.lerp(var7, var8, 0.5F + (-var13 * var11 - var14 * var12) / (2.0F * var15)),
         BlendMode.lerp(var7, var8, 0.5F + (var13 * var11 - var14 * var12) / (2.0F * var15)),
         BlendMode.lerp(var7, var8, 0.5F + (var13 * var11 + var14 * var12) / (2.0F * var15)),
         BlendMode.lerp(var7, var8, 0.5F + (-var13 * var11 + var14 * var12) / (2.0F * var15)),
         0.0F,
         0,
         0
      );
   }

   public void roundRectRadial(float var1, float var2, float var3, float var4, float var5, int var6, int var7) {
      this.box(var1, var2, var3, var4, var5, var5, var5, var5, var6, var6, var6, var6, 0.0F, var7, 8);
   }

   public void roundRectBordered(float var1, float var2, float var3, float var4, float var5, int var6, float var7, int var8) {
      this.box(var1, var2, var3, var4, var5, var5, var5, var5, var6, var6, var6, var6, var7, var8, 0);
   }

   public void roundRectOutline(float var1, float var2, float var3, float var4, float var5, float var6, int var7) {
      this.box(var1, var2, var3, var4, var5, var5, var5, var5, 0, 0, 0, 0, var6, var7, 0);
   }

   public void roundRectOutlineGradient(float var1, float var2, float var3, float var4, float var5, float var6, float var7, int var8, int var9) {
      double var10 = Math.toRadians(var7);
      float var12 = (float)Math.cos(var10);
      float var13 = (float)Math.sin(var10);
      float var14 = var3 * 0.5F;
      float var15 = var4 * 0.5F;
      float var16 = Math.abs(var14 * var12) + Math.abs(var15 * var13);
      this.box(
         var1,
         var2,
         var3,
         var4,
         var5,
         var5,
         var5,
         var5,
         BlendMode.lerp(var8, var9, 0.5F + (-var14 * var12 - var15 * var13) / (2.0F * var16)),
         BlendMode.lerp(var8, var9, 0.5F + (var14 * var12 - var15 * var13) / (2.0F * var16)),
         BlendMode.lerp(var8, var9, 0.5F + (var14 * var12 + var15 * var13) / (2.0F * var16)),
         BlendMode.lerp(var8, var9, 0.5F + (-var14 * var12 + var15 * var13) / (2.0F * var16)),
         var6,
         0,
         32
      );
   }

   public void circle(float var1, float var2, float var3, int var4) {
      this.box(var1 - var3, var2 - var3, var3 * 2.0F, var3 * 2.0F, var3, var3, var3, var3, var4, var4, var4, var4, 0.0F, 0, 0);
   }

   public void circleRadial(float var1, float var2, float var3, int var4, int var5) {
      this.roundRectRadial(var1 - var3, var2 - var3, var3 * 2.0F, var3 * 2.0F, var3, var4, var5);
   }

   public void shadow(float var1, float var2, float var3, float var4, float var5, float var6, int var7) {
      this.shadow(var1, var2, var3, var4, var5, var6, 0.0F, 0.0F, 0.0F, var7);
   }

   public void shadow(float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, int var10) {
      this.softBox(var1 - var7 + var8, var2 - var7 + var9, var3 + var7 * 2.0F, var4 + var7 * 2.0F, Math.max(0.0F, var5 + var7), var6, var10, 0);
   }

   public void glow(float var1, float var2, float var3, float var4, float var5, float var6, int var7) {
      this.softBox(var1, var2, var3, var4, var5, var6, var7, 16);
   }

   public void glowCircle(float var1, float var2, float var3, float var4, int var5) {
      this.softBox(var1 - var3, var2 - var3, var3 * 2.0F, var3 * 2.0F, var3, var4, var5, 16);
   }

   public void softCircle(float var1, float var2, float var3, float var4, int var5) {
      this.softBox(var1 - var3, var2 - var3, var3 * 2.0F, var3 * 2.0F, var3, var4, var5, 0);
   }

   private void softBox(float var1, float var2, float var3, float var4, float var5, float var6, int var7, int var8) {
      if (!(var3 <= 0.0F) && !(var4 <= 0.0F)) {
         float var9 = Math.max(0.01F, var6 * 0.5F);
         float var10 = var3 * 0.5F;
         float var11 = var4 * 0.5F;
         float var12 = var9 * 3.0F + this.pixelSize();
         float var13 = Math.min(var5, Math.min(var10, var11));
         int var14 = BlendMode.fade(var7, this.alpha);
         if (var14 >>> 24 != 0) {
            this.quad(
               (GpuTextureView)null,
               (class_12137)null,
               var1 + var10,
               var2 + var11,
               var10 + var12,
               var11 + var12,
               var10,
               var11,
               var13,
               var13,
               var13,
               var13,
               1,
               0.0F,
               var9,
               var8,
               0.0F,
               0.0F,
               var14,
               var14,
               var14,
               var14,
               0,
               0.0F,
               0.0F,
               0.0F,
               0.0F
            );
         }
      }
   }

   public void ring(float var1, float var2, float var3, float var4, int var5) {
      this.arc(var1, var2, var3, var4, 0.0F, 360.0F, var5, var5, true);
   }

   public void arc(float var1, float var2, float var3, float var4, float var5, float var6, int var7) {
      this.arc(var1, var2, var3, var4, var5, var6, var7, var7, true);
   }

   public void arc(float var1, float var2, float var3, float var4, float var5, float var6, int var7, int var8, boolean var9) {
      if (var6 != 0.0F && !(var4 <= 0.0F)) {
         if (var6 < 0.0F) {
            var5 += var6;
            var6 = -var6;
            int var10 = var7;
            var7 = var8;
            var8 = var10;
         }

         float var17 = (float)Math.toRadians(Math.min(var6, 360.0F));
         float var11 = (float)Math.toRadians(var5);
         float var12 = var4 * 0.5F;
         float var13 = var3 + var12 + this.pixelSize() * 1.5F;
         int var14 = (var9 ? 2 : 0) | (var7 != var8 ? 4 : 0);
         int var15 = BlendMode.fade(var7, this.alpha);
         int var16 = BlendMode.fade(var8, this.alpha);
         this.quad(
            (GpuTextureView)null,
            (class_12137)null,
            var1,
            var2,
            var13,
            var13,
            var3,
            var3,
            var11,
            var17,
            var12,
            0.0F,
            2,
            0.0F,
            0.0F,
            var14,
            0.0F,
            0.0F,
            var15,
            var15,
            var15,
            var15,
            var16,
            0.0F,
            0.0F,
            0.0F,
            0.0F
         );
      }
   }

   public void line(float var1, float var2, float var3, float var4, float var5, int var6) {
      this.line(var1, var2, var3, var4, var5, var6, var6);
   }

   public void line(float var1, float var2, float var3, float var4, float var5, int var6, int var7) {
      float var8 = var3 - var1;
      float var9 = var4 - var2;
      float var10 = (float)Math.sqrt(var8 * var8 + var9 * var9);
      float var11 = var5 * 0.5F;
      this.push();
      this.translate((var1 + var3) * 0.5F, (var2 + var4) * 0.5F);
      this.rotate((float)Math.atan2(var9, var8));
      this.box(-var10 * 0.5F - var11, -var11, var10 + var5, var5, var11, var11, var11, var11, var6, var7, var7, var6, 0.0F, 0, 0);
      this.pop();
   }

   public void triangle(float var1, float var2, float var3, float var4, float var5, float var6, int var7) {
      this.triangle(var1, var2, var3, var4, var5, var6, var7, var7, var7);
   }

   public void triangle(float var1, float var2, float var3, float var4, float var5, float var6, int var7, int var8, int var9) {
      float var10 = this.tx(var1, var2);
      float var11 = this.ty(var1, var2);
      float var12 = this.tx(var3, var4);
      float var13 = this.ty(var3, var4);
      float var14 = this.tx(var5, var6);
      float var15 = this.ty(var5, var6);
      if (!(Math.max(var10, Math.max(var12, var14)) < this.clipX0)
         && !(Math.min(var10, Math.min(var12, var14)) > this.clipX1)
         && !(Math.max(var11, Math.max(var13, var15)) < this.clipY0)
         && !(Math.min(var11, Math.min(var13, var15)) > this.clipY1)) {
         var7 = BlendMode.fade(var7, this.alpha);
         var8 = BlendMode.fade(var8, this.alpha);
         var9 = BlendMode.fade(var9, this.alpha);
         if ((var7 | var8 | var9) >>> 24 != 0) {
            long var16 = this.quads.reserve((GpuTextureView)null, (class_12137)null);
            VertexBuffer.shared(
               var16,
               1000000.0F,
               1000000.0F,
               0.0F,
               0.0F,
               0.0F,
               0.0F,
               0,
               0.0F,
               0.0F,
               0,
               this.clipX0,
               this.clipY0,
               this.clipX1,
               this.clipY1,
               this.clipR,
               this.pixelSize(),
               0.0F,
               0.0F,
               0
            );
            VertexBuffer.corner(var16, 0, var10, var11, 0.0F, 0.0F, 0.0F, 0.0F, BlendMode.toAbgr(var7));
            VertexBuffer.corner(var16, 1, var12, var13, 0.0F, 0.0F, 0.0F, 0.0F, BlendMode.toAbgr(var8));
            VertexBuffer.corner(var16, 2, var14, var15, 0.0F, 0.0F, 0.0F, 0.0F, BlendMode.toAbgr(var9));
            VertexBuffer.corner(var16, 3, var14, var15, 0.0F, 0.0F, 0.0F, 0.0F, BlendMode.toAbgr(var9));
         }
      }
   }

   public void image(TextureLoader var1, float var2, float var3, float var4, float var5) {
      this.image(var1, var2, var3, var4, var5, 0.0F, -1);
   }

   public void image(TextureLoader var1, float var2, float var3, float var4, float var5, float var6, int var7) {
      this.image(var1.view(), var1.sampler(), true, var2, var3, var4, var5, 0.0F, 0.0F, 1.0F, 1.0F, var6, var7);
   }

   public void logo(TextureLoader var1, float var2, float var3, float var4, float var5, int var6) {
      this.picture(var1.view(), var1.sampler(), var2, var3, var4, var5, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, var6, 128);
   }

   public void imageSheen(TextureLoader var1, float var2, float var3, float var4, float var5, int var6, float var7, float var8, float var9) {
      if (!(var4 <= 0.0F) && !(var5 <= 0.0F)) {
         int var10 = BlendMode.fade(var6, this.alpha);
         if (var10 >>> 24 != 0) {
            float var11 = var4 * 0.5F;
            float var12 = var5 * 0.5F;
            float var13 = this.pixelSize() * 1.5F;
            float var14 = var13 / var4;
            float var15 = var13 / var5;
            this.quad(
               var1.view(),
               var1.sampler(),
               var2 + var11,
               var3 + var12,
               var11 + var13,
               var12 + var13,
               var11,
               var12,
               0.0F,
               0.0F,
               0.0F,
               0.0F,
               3,
               var7,
               0.0F,
               80,
               var8,
               var9,
               var10,
               var10,
               var10,
               var10,
               0,
               -var14,
               -var15,
               1.0F + var14,
               1.0F + var15
            );
         }
      }
   }

   public void image(
      GpuTextureView var1,
      class_12137 var2,
      boolean var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      float var12,
      int var13
   ) {
      this.picture(var1, var2, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var3 ? 0 : 1);
   }

   private void picture(
      GpuTextureView var1,
      class_12137 var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      int var12,
      int var13
   ) {
      if (!(var5 <= 0.0F) && !(var6 <= 0.0F)) {
         int var14 = BlendMode.fade(var12, this.alpha);
         if (var14 >>> 24 != 0) {
            float var15 = var5 * 0.5F;
            float var16 = var6 * 0.5F;
            float var17 = this.pixelSize() * 1.5F;
            float var18 = Math.min(var11, Math.min(var15, var16));
            float var19 = (var9 - var7) * var17 / var5;
            float var20 = (var10 - var8) * var17 / var6;
            this.quad(
               var1,
               var2,
               var3 + var15,
               var4 + var16,
               var15 + var17,
               var16 + var17,
               var15,
               var16,
               var18,
               var18,
               var18,
               var18,
               3,
               0.0F,
               0.0F,
               var13,
               0.0F,
               0.0F,
               var14,
               var14,
               var14,
               var14,
               0,
               var7 - var19,
               var8 - var20,
               var9 + var19,
               var10 + var20
            );
         }
      }
   }

   public void imageCorners(
      GpuTextureView var1,
      class_12137 var2,
      boolean var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      float var12,
      float var13,
      float var14,
      float var15,
      int var16,
      int var17,
      int var18,
      int var19
   ) {
      if (!(var6 <= 0.0F) && !(var7 <= 0.0F)) {
         int var20 = BlendMode.fade(var16, this.alpha);
         int var21 = BlendMode.fade(var17, this.alpha);
         int var22 = BlendMode.fade(var18, this.alpha);
         int var23 = BlendMode.fade(var19, this.alpha);
         if ((var20 | var21 | var22 | var23) >>> 24 != 0) {
            float var24 = var6 * 0.5F;
            float var25 = var7 * 0.5F;
            float var26 = this.pixelSize() * 1.5F;
            float var27 = Math.min(var24, var25);
            float var28 = (var10 - var8) * var26 / var6;
            float var29 = (var11 - var9) * var26 / var7;
            this.quad(
               var1,
               var2,
               var4 + var24,
               var5 + var25,
               var24 + var26,
               var25 + var26,
               var24,
               var25,
               Math.min(Math.max(var12, 0.0F), var27),
               Math.min(Math.max(var13, 0.0F), var27),
               Math.min(Math.max(var14, 0.0F), var27),
               Math.min(Math.max(var15, 0.0F), var27),
               3,
               0.0F,
               0.0F,
               var3 ? 0 : 1,
               0.0F,
               0.0F,
               var20,
               var21,
               var22,
               var23,
               0,
               var8 - var28,
               var9 - var29,
               var10 + var28,
               var11 + var29
            );
         }
      }
   }

   public void glass(float var1, float var2, float var3, float var4, float var5, int var6) {
      this.glass(var1, var2, var3, var4, var5, var6, 1.0F, 1.25F, 0.018F);
   }

   public void glass(float var1, float var2, float var3, float var4, float var5, int var6, float var7, float var8, float var9) {
      if (!(var3 <= 0.0F) && !(var4 <= 0.0F)) {
         float var10 = var7 * this.alpha;
         if (!(var10 <= 0.0F)) {
            float var11 = var3 * 0.5F;
            float var12 = var4 * 0.5F;
            float var13 = this.pixelSize() * 1.5F;
            float var14 = Math.min(var5, Math.min(var11, var12));
            this.quads.ensureBackdrop(this.blurStrength);
            this.quad(
               (GpuTextureView)null,
               (class_12137)null,
               var1 + var11,
               var2 + var12,
               var11 + var13,
               var12 + var13,
               var11,
               var12,
               var14,
               var14,
               var14,
               var14,
               5,
               0.0F,
               var10,
               0,
               var8,
               var9,
               var6,
               var6,
               var6,
               var6,
               0,
               0.0F,
               0.0F,
               0.0F,
               0.0F
            );
         }
      }
   }

   public float text(TrueTypeFont var1, CharSequence var2, float var3, float var4, float var5, int var6) {
      return this.text(var1, var2, var3, var4, var5, var6, FontStyle.LEFT, FontWeight.TOP, 0.0F);
   }

   public float text(TrueTypeFont var1, CharSequence var2, float var3, float var4, float var5, int var6, FontStyle var7, FontWeight var8) {
      return this.text(var1, var2, var3, var4, var5, var6, var7, var8, 0.0F);
   }

   public float text(TrueTypeFont var1, CharSequence var2, float var3, float var4, float var5, int var6, FontStyle var7, FontWeight var8, float var9) {
      return this.drawText(var1, var2, var3, var4, var5, var6, var6, var7, var8, var9, 0.0F, 0.0F, 0.0F, 0, Float.NaN, 0.0F);
   }

   public float textGradient(
      TrueTypeFont var1, CharSequence var2, float var3, float var4, float var5, int var6, int var7, FontStyle var8, FontWeight var9, float var10
   ) {
      return this.drawText(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, 0.0F, 0.0F, 0.0F, 0, Float.NaN, 0.0F);
   }

   public float textSweep(
      TrueTypeFont var1,
      CharSequence var2,
      float var3,
      float var4,
      float var5,
      int var6,
      int var7,
      float var8,
      float var9,
      FontStyle var10,
      FontWeight var11,
      float var12
   ) {
      return this.drawText(var1, var2, var3, var4, var5, var6, var7, var10, var11, var12, 0.0F, 0.0F, 0.0F, 0, var8, Math.max(0.01F, var9));
   }

   public float paragraph(TrueTypeFont var1, String var2, float var3, float var4, float var5, float var6, float var7, int var8, FontStyle var9) {
      List var10 = FontMetrics.wrap(var1, var2, var5, var6, 0.0F);
      float var11 = var5 * var7;
      float var12 = var3 + var6 * var9.factor;

      for (int var13 = 0; var13 < var10.size(); var13++) {
         this.text(var1, (CharSequence)var10.get(var13), var12, var4 + var13 * var11, var5, var8, var9, FontWeight.TOP, 0.0F);
      }

      return var10.size() * var11;
   }

   public float textEllipsized(TrueTypeFont var1, String var2, float var3, float var4, float var5, float var6, int var7, FontStyle var8, FontWeight var9) {
      return this.text(var1, FontMetrics.ellipsize(var1, var2, var5, var6, 0.0F), var3, var4, var5, var7, var8, var9, 0.0F);
   }

   public void textGlow(
      TrueTypeFont var1, CharSequence var2, float var3, float var4, float var5, int var6, FontStyle var7, FontWeight var8, float var9, float var10, float var11
   ) {
      this.drawText(var1, var2, var3, var4, var5, var6, var6, var7, var8, var9, var10, Math.max(0.01F, var11), 0.0F, 0, Float.NaN, 0.0F);
   }

   public void textOutlined(
      TrueTypeFont var1, CharSequence var2, float var3, float var4, float var5, int var6, int var7, float var8, FontStyle var9, FontWeight var10, float var11
   ) {
      this.drawText(var1, var2, var3, var4, var5, var6, var6, var9, var10, var11, 0.0F, 0.0F, var8, var7, Float.NaN, 0.0F);
   }

   public float textWidth(TrueTypeFont var1, CharSequence var2, float var3) {
      return var1.width(var2, var3, 0.0F);
   }

   public float textWidth(TrueTypeFont var1, CharSequence var2, float var3, float var4) {
      return var1.width(var2, var3, var4);
   }

   private float drawText(
      TrueTypeFont var1,
      CharSequence var2,
      float var3,
      float var4,
      float var5,
      int var6,
      int var7,
      FontStyle var8,
      FontWeight var9,
      float var10,
      float var11,
      float var12,
      float var13,
      int var14,
      float var15,
      float var16
   ) {
      var2 = Translations.display(var2);
      int var17 = BlendMode.fade(var6, this.alpha);
      int var18 = BlendMode.fade(var7, this.alpha);
      boolean var19 = !Float.isNaN(var15);
      boolean var20 = var17 != var18 && !var19;
      float var21 = var1.measure(var2, var5, var10);
      if ((var17 | var18) >>> 24 != 0 && !var2.isEmpty()) {
         float var22 = var3 - var21 * var8.factor;
         float var23 = var22;
         float var24 = Math.max(var21, 0.001F);

         float var26 = switch (var9) {
            case TOP -> var4 + var1.ascent * var5;
            case MIDDLE -> var4 + var1.capHeight * var5 * 0.5F;
            case BASELINE -> var4;
            case BOTTOM -> var4 - var1.descent * var5;
         };
         float var27 = var5 * this.pixelScale * this.transformScale();
         float var28 = 0.25F * var27;
         float var29 = var11 + (var27 < 24.0F ? (24.0F - var27) * 0.012F : 0.0F);
         int var30 = BlendMode.fade(var14, this.alpha);
         class_12137 var31 = Fonts.ATLAS.sampler();
         FontFamily var32 = null;
         int var34 = 0;

         while (var34 < var2.length()) {
            int var35 = Character.codePointAt(var2, var34);
            var34 += Character.charCount(var35);
            FontFamily var33 = var1.glyph(var35);
            if (var32 != null) {
               var22 += var1.kerning(var32, var33) * var5;
            }

            if (var33.ready()) {
               int var36 = var17;
               int var37 = var17;
               if (var19) {
                  float var38 = var22 + var33.left() * var5;
                  float var39 = var22 + var33.right() * var5;
                  var36 = BlendMode.lerp(var18, var17, Math.max(0.0F, Math.min(1.0F, (var15 - var38) / var16 + 0.5F)));
                  var37 = BlendMode.lerp(var18, var17, Math.max(0.0F, Math.min(1.0F, (var15 - var39) / var16 + 0.5F)));
               } else if (var20) {
                  var36 = BlendMode.lerp(var17, var18, (var22 + var33.left() * var5 - var23) / var24);
                  var37 = BlendMode.lerp(var17, var18, (var22 + var33.right() * var5 - var23) / var24);
               }

               this.glyph(var33, var22, var26, var5, var31, var36, var37, var30, var28, var29, var12, var13);
            }

            var22 += (var33.advance + var10) * var5;
            var32 = var33;
         }

         return var21;
      } else {
         return var21;
      }
   }

   private void glyph(
      FontFamily var1, float var2, float var3, float var4, class_12137 var5, int var6, int var7, int var8, float var9, float var10, float var11, float var12
   ) {
      float var13 = var2 + var1.left() * var4;
      float var14 = var3 + var1.top() * var4;
      float var15 = (var1.right() - var1.left()) * var4 * 0.5F;
      float var16 = (var1.bottom() - var1.top()) * var4 * 0.5F;
      this.quad(
         var1.pageView(),
         var5,
         var13 + var15,
         var14 + var16,
         var15,
         var16,
         var15,
         var16,
         0.0F,
         0.0F,
         0.0F,
         0.0F,
         4,
         var10,
         var9,
         0,
         var11,
         var12,
         var6,
         var7,
         var7,
         var6,
         var8,
         var1.u0(),
         var1.v0(),
         var1.u1(),
         var1.v1()
      );
   }

   public void icon(String var1, float var2, float var3, float var4, int var5) {
      int var6 = IconCodepoints.codepoint(var1);
      if (var6 >= 0) {
         this.icon(var6, var2, var3, var4, var5);
      }
   }

   public void icon(int var1, float var2, float var3, float var4, int var5) {
      TrueTypeFont var6 = Fonts.ICONS;
      FontFamily var7 = var6.glyph(var1);
      if (var7.ready()) {
         int var8 = BlendMode.fade(var5, this.alpha);
         if (var8 >>> 24 != 0) {
            float var9 = -var6.ascent;
            float var10 = var6.descent;
            float var11 = var2 - var7.advance * var4 * 0.5F;
            float var12 = var3 - (var9 + var10) * 0.5F * var4;
            float var13 = var4 * this.pixelScale * this.transformScale();
            float var14 = 0.25F * var13;
            float var15 = var13 < 24.0F ? (24.0F - var13) * 0.01F : 0.0F;
            this.glyph(var7, var11, var12, var4, Fonts.ATLAS.sampler(), var8, var8, 0, var14, var15, 0.0F, 0.0F);
         }
      }
   }

   private void box(
      float var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      int var9,
      int var10,
      int var11,
      int var12,
      float var13,
      int var14,
      int var15
   ) {
      if (!(var3 <= 0.0F) && !(var4 <= 0.0F)) {
         float var16 = var3 * 0.5F;
         float var17 = var4 * 0.5F;
         float var18 = Math.min(var16, var17);
         float var19 = this.pixelSize() * 1.5F;
         float var20 = this.alpha;
         var9 = BlendMode.fade(var9, var20);
         var10 = BlendMode.fade(var10, var20);
         var11 = BlendMode.fade(var11, var20);
         var12 = BlendMode.fade(var12, var20);
         int var21 = BlendMode.fade(var14, var20);
         if ((var9 | var10 | var11 | var12) >>> 24 != 0 || !(var13 <= 0.0F) && var21 >>> 24 != 0 || (var15 & 8) != 0) {
            this.quad(
               (GpuTextureView)null,
               (class_12137)null,
               var1 + var16,
               var2 + var17,
               var16 + var19,
               var17 + var19,
               var16,
               var17,
               Math.min(Math.max(var5, 0.0F), var18),
               Math.min(Math.max(var6, 0.0F), var18),
               Math.min(Math.max(var7, 0.0F), var18),
               Math.min(Math.max(var8, 0.0F), var18),
               0,
               var13,
               0.0F,
               var15,
               0.0F,
               0.0F,
               var9,
               var10,
               var11,
               var12,
               var21,
               0.0F,
               0.0F,
               0.0F,
               0.0F
            );
         }
      }
   }

   private void quad(
       GpuTextureView var1,
       class_12137 var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      float var12,
      int var13,
      float var14,
      float var15,
      int var16,
      float var17,
      float var18,
      int var19,
      int var20,
      int var21,
      int var22,
      int var23,
      float var24,
      float var25,
      float var26,
      float var27
   ) {
      float var28 = var3 - var5;
      float var29 = var4 - var6;
      float var30 = var3 + var5;
      float var31 = var4 + var6;
      Matrix3x2fStack var32 = this.transform;
      float var33 = var32.m00();
      float var34 = var32.m01();
      float var35 = var32.m10();
      float var36 = var32.m11();
      float var37 = var32.m20();
      float var38 = var32.m21();
      float var39 = var33 * var28 + var35 * var29 + var37;
      float var40 = var34 * var28 + var36 * var29 + var38;
      float var41 = var33 * var30 + var35 * var29 + var37;
      float var42 = var34 * var30 + var36 * var29 + var38;
      float var43 = var33 * var30 + var35 * var31 + var37;
      float var44 = var34 * var30 + var36 * var31 + var38;
      float var45 = var33 * var28 + var35 * var31 + var37;
      float var46 = var34 * var28 + var36 * var31 + var38;
      if (!(max4(var39, var41, var43, var45) < this.clipX0)
         && !(min4(var39, var41, var43, var45) > this.clipX1)
         && !(max4(var40, var42, var44, var46) < this.clipY0)
         && !(min4(var40, var42, var44, var46) > this.clipY1)) {
         float var47 = (float)Math.sqrt(Math.abs(var33 * var36 - var34 * var35));
         float var48 = 1.0F / (this.pixelScale * Math.max(var47, 1.0E-4F));
         long var49 = this.quads.reserve(var1, var2);
         VertexBuffer.shared(
            var49,
            var7,
            var8,
            var9,
            var10,
            var11,
            var12,
            var13,
            var14,
            var15,
            var16,
            this.clipX0,
            this.clipY0,
            this.clipX1,
            this.clipY1,
            this.clipR,
            var48,
            var17,
            var18,
            BlendMode.toAbgr(var23)
         );
         VertexBuffer.corner(var49, 0, var39, var40, -var5, -var6, var24, var25, BlendMode.toAbgr(var19));
         VertexBuffer.corner(var49, 1, var45, var46, -var5, var6, var24, var27, BlendMode.toAbgr(var22));
         VertexBuffer.corner(var49, 2, var43, var44, var5, var6, var26, var27, BlendMode.toAbgr(var21));
         VertexBuffer.corner(var49, 3, var41, var42, var5, -var6, var26, var25, BlendMode.toAbgr(var20));
      }
   }

   private float pixelSize() {
      return 1.0F / (this.pixelScale * Math.max(this.transformScale(), 1.0E-4F));
   }

   private float transformScale() {
      Matrix3x2fStack var1 = this.transform;
      return (float)Math.sqrt(Math.abs(var1.m00() * var1.m11() - var1.m01() * var1.m10()));
   }

   private float tx(float var1, float var2) {
      Matrix3x2fStack var3 = this.transform;
      return var3.m00() * var1 + var3.m10() * var2 + var3.m20();
   }

   private float ty(float var1, float var2) {
      Matrix3x2fStack var3 = this.transform;
      return var3.m01() * var1 + var3.m11() * var2 + var3.m21();
   }

   private static float min4(float var0, float var1, float var2, float var3) {
      return Math.min(Math.min(var0, var1), Math.min(var2, var3));
   }

   private static float max4(float var0, float var1, float var2, float var3) {
      return Math.max(Math.max(var0, var1), Math.max(var2, var3));
   }
}
