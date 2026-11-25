package dev.purxiy.modules.bokeh;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Snippet;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
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
import java.nio.ByteBuffer;
import java.util.OptionalInt;
import net.minecraft.class_10789;
import net.minecraft.class_290;
import net.minecraft.class_6367;
import org.lwjgl.system.MemoryUtil;

public final class BokehRenderer3 {
   public static final float E = 216.0F;
   private static final float i = 44.0F;
   private static final float S = 146.0F;
   private static final float G = 120.0F;
   private static final float u = 8.0F;
   private static final int f = -4222985;
   private static final BokehKind[] x = BokehKind.values();
   private static final int X = 12;
   private static final int b = 544;
   private static final RenderPipeline I = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/bokeh_preview"))
      .withVertexShader(ClientServices.id("core/bokeh"))
      .withFragmentShader(ClientServices.id("core/bokeh_preview"))
      .withUniform("PurxiyBokehPreview", class_10789.field_60031)
      .withUniform("PurxiyTheme", class_10789.field_60031)
      .withoutBlend()
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static final class_6367[] Y = new class_6367[x.length];
   private static final GpuBuffer[] F = new GpuBuffer[x.length];
   private static ByteBuffer s;
   private static String j;
   private static final float[][] L = new float[][]{
      {0.14F, 0.7F, 0.105F, 0.85F, 1.0F, 0.8F, 0.5F},
      {0.4F, 0.82F, 0.075F, 0.7F, 0.8F, 0.62F, 1.0F},
      {0.76F, 0.68F, 0.12F, 0.75F, 1.0F, 0.66F, 0.86F},
      {0.9F, 0.3F, 0.085F, 0.65F, 0.95F, 0.9F, 1.0F},
      {0.26F, 0.34F, 0.13F, 0.55F, 0.7F, 0.52F, 1.0F},
      {0.56F, 0.48F, 0.07F, 0.9F, 1.0F, 0.84F, 0.58F},
      {0.07F, 0.18F, 0.06F, 0.65F, 1.0F, 0.7F, 0.9F},
      {0.64F, 0.14F, 0.1F, 0.55F, 0.78F, 0.6F, 1.0F},
      {0.88F, 0.9F, 0.055F, 0.8F, 1.0F, 0.86F, 0.62F},
      {0.36F, 0.1F, 0.05F, 0.85F, 0.96F, 0.92F, 1.0F},
      {0.52F, 0.93F, 0.045F, 0.75F, 1.0F, 0.7F, 0.92F},
      {0.04F, 0.5F, 0.05F, 0.8F, 0.84F, 0.7F, 1.0F}
   };
   private final BokehModule Q;
   private final Animator[] m = new Animator[x.length];
   private float l;
   private float o;
   private float v;
   private float q;
   private boolean M;
   private static final String[] c = new String[16];
   private static final long g = 544L;

   public BokehRenderer3(BokehModule var1) {
      this.Q = var1;

      for (int var2 = 0; var2 < x.length; var2++) {
         this.m[var2] = Animator.smooth(0.0F);
      }
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 16804074380797L;
      long var8 = var6 ^ 116695873786831L;
      this.l = var2;
      this.o = var3;
      this.v = var4;
      this.M = var5 && !var1.masked();
      this.q = this.q + Math.min(var1.dt, 0.1F);
      Canvas var10 = var1.canvas;
      var10.text(Fonts.MEDIUM, "Choose a bokeh", var2, var3 + 10.0F, 13.5F, Icons.TEXT);
      var10.text(Fonts.REGULAR, "City lights far out of focus, as your lens draws them", var2, var3 + 30.0F, 11.5F, Icons.TEXT_2);
      float var11 = this.p();
      int var12 = this.Q.t.index();

      for (int var13 = 0; var13 < x.length; var13++) {
         float var14 = var2 + var13 * (var11 + 8.0F);
         float var15 = var3 + 44.0F;
         boolean var16 = this.M && var1.hover(this.m[var13], var14, var15, var11, 146.0F, TypeE.HAND);
         float var17 = this.m[var13].target(var16 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         boolean var18 = var13 == var12;
         var10.roundRect(
            var14, var15, var11, 146.0F, 10.0F, BlendMode.lerp(Theme.tint(-15725031), Theme.tint(-4222985), (var18 ? 0.1F : 0.025F) + var17 * 0.04F)
         );
         float var19 = var14 + 3.0F;
         float var20 = var15 + 3.0F;
         float var21 = var11 - 6.0F;
         float var22 = 117.0F;
         var10.pushClip(var19, var20, var21, var22, 8.0F);
         float var23 = Math.max(var18 ? 1.0F : 0.0F, var17 * 0.6F);
         this.c(var8, var10, var19, var20, var21, var22, var13, var23);
         var10.popClip();
         var10.roundRectOutline(
            var14, var15, var11, 146.0F, 10.0F, var18 ? 1.3F : 1.0F, BlendMode.withAlpha(Theme.tint(-4222985), var18 ? 0.7F : 0.12F + var17 * 0.2F)
         );
         if (var18) {
            var10.circle(var14 + var11 - 13.0F, var15 + 13.0F, 6.0F, Theme.tint(-552595943));
            var10.icon("check", var14 + var11 - 13.0F, var15 + 13.0F, 9.0F, Theme.tint(-4222985));
         }

         var10.text(
            Fonts.SEMIBOLD,
            x[var13].V,
            var14 + var11 / 2.0F,
            var15 + 120.0F + 13.0F,
            11.5F,
            var18 ? Theme.tint(-4222985) : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var17),
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
      }

      var10.circle(var2 + 3.0F, var3 + 216.0F - 12.0F, 2.5F, Theme.tint(-4222985));
      var10.textEllipsized(
         Fonts.REGULAR,
         x[Math.clamp((long)var12, 0, x.length - 1)].r,
         var2 + 14.0F,
         var3 + 216.0F - 12.0F,
         10.5F,
         var4 - 14.0F,
         Icons.TEXT_2,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
   }

   private float p() {
      return (this.v - 8.0F * (x.length - 1)) / x.length;
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      if (this.M && var3 == 0) {
         float var4 = this.p();

         for (int var5 = 0; var5 < x.length; var5++) {
            float var6 = this.l + var5 * (var4 + 8.0F);
            float var7 = this.o + 44.0F;
            if (var1 >= var6 && var1 < var6 + var4 && var2 >= var7 && var2 < var7 + 146.0F) {
               if (this.Q.t.index() != var5) {
                  this.Q.t.select(var5);
                  UiAudioPolicy.play("SELECT");
               }

               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private void c(long var1, Canvas var3, float var4, float var5, float var6, float var7, int var8, float var9) {
      long var10 = 83571923648419L;
      byte var12 = 0;
      short var13 = 19458;
      int var14 = (int)(var10 << 32 >>> 32);
      BokehRenderer4.ensureSourceClassInitialized();

      try {
         GpuDevice var15 = RenderSystem.getDevice();
         if (!var15.precompilePipeline(I).isValid()) {
            return;
         }

         float var16 = (float)Math.hypot(var3.transform().m00(), var3.transform().m01());
         float var17 = Math.max(0.5F, var3.pixelScale() * var16);
         int var18 = Math.max(1, Math.round(var6 * var17));
         int var19 = Math.max(1, Math.round(117.0F * var17));
         class_6367 var20 = Y[var8];
         long var21 = 111509715340850L;
         int var39 = var21 < 0L ? -1 : 1;
         if (var20 == null) {
            var20 = new class_6367("Purxiy bokeh preview " + x[var8].V, var18, var19, false);
            Y[var8] = var20;
         } else {
            label97: {
               if (var20.field_1482 == var18) {
                  long var24 = 111509715340850L;
                  int var251 = var24 < 0L ? -1 : 1;
                  if (var20.field_1481 == var19) {
                     break label97;
                  }
               }

               var20.method_1234(var18, var19);
            }
         }

         GpuBuffer var39x = F[var8];
         long var25 = 111509715340849L;
         int var28 = var25 < 0L ? -1 : 1;
         if (var39x == null) {
            var39x = var15.createBuffer(ClientServices.label(() -> "Purxiy bokeh preview"), 136, 544L);
            F[var8] = var39x;
         }

         if (s == null) {
            s = MemoryUtil.memAlloc(544);
         }

         CommandEncoder var28x = var15.createCommandEncoder();
         var28x.writeToBuffer(var39x.slice(), this.f(var18, (char)var12, var19, (char)var13, var8, var14, var9));
         GpuBuffer var29 = Theme.uniforms();
         RenderPass var30 = var28x.createRenderPass(() -> "Purxiy bokeh preview", var20.method_71639(), OptionalInt.empty());

         try {
            var30.setPipeline(I);
            long var31 = 111509715340849L;
            int var31x = var31 < 0L ? -1 : 1;
            RenderSystem.bindDefaultUniforms(var30);
            var30.setUniform("PurxiyBokehPreview", var39x);
            long var34x = 111509715340850L;
            int var33 = var34x < 0L ? -1 : 1;
            var30.setUniform("PurxiyTheme", var29);
            var33 = -520564237;
            var30.draw(0, 3);
            int var23x = -520565198;
            BokehRenderer4.metric("drawn");
         } catch (Throwable var36) {
            if (var30 != null) {
               try {
                  var30.close();
               } catch (Throwable var35) {
                  var36.addSuppressed(var35);
               }
            }

            throw var36;
         }

         if (var30 != null) {
            var30.close();
         }

         var3.image(
            var20.method_71639(),
            RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR),
            true,
            var4,
            var5,
            var6,
            117.0F,
            0.0F,
            1.0F,
            1.0F,
            0.0F,
            0.0F,
            -1
         );
      } catch (RuntimeException var37) {
         if (!var37.toString().equals(j)) {
            ClientServices.LOGGER.error("Purxiy bokeh preview could not render", var37);
         }

         j = var37.toString();
      }
   }

   private ByteBuffer f(int var1, char var2, int var3, char var4, int var5, int var6, float var7) {
      BokehRenderer4.ensureSourceClassInitialized();
      float var8 = 0.9F + 0.12F * (float)Math.sin(this.q * 0.7F + var5 * 0.4F);
      Std140Builder var9 = Std140Builder.intoBuffer(s.clear())
         .putVec4(var1, var3, var5, var7)
         .putVec4(this.Q.b.floatValue(), this.Q.h.floatValue(), this.Q.Q.get() ? 1.0F : 0.0F, 12.0F);

      for (int var10 = 0; var10 < 16; var10++) {
         short var11 = 0;
         if (var10 < 12) {
            float[] var12 = L[var10];
            double var13 = this.q * (0.05 + 0.013 * var10) + var10 * 1.7 + var5 * 0.9;
            float var15 = var12[0] + 0.035F * (float)Math.sin(var13 * 2.1);
            var11 = 19458;
            float var16 = var12[1] + 0.03F * (float)Math.cos(var13 * 1.6);
            float var17 = 0.88F + 0.12F * (float)Math.sin(this.q * (0.9 + 0.17 * var10) + var10);
            var9.putVec4(var15, var16, var12[2] * var3 * var8, var12[3] * var17);
         } else {
            var9.putVec4(0.0F, 0.0F, 1.0F, 0.0F);
         }
      }

      int var18 = 19458;
      int var21 = 0;

      while (true) {
         short var22 = 0;
         if (var21 >= 16) {
            var18 = 450002851;
            return var9.get();
         }

         if (var21 < 12) {
            float[] var24 = L[var21];
            var22 = 19458;
            var9.putVec4(var24[4], var24[5], var24[6], 1.0F);
         } else {
            var9.putVec4(0.0F, 0.0F, 0.0F, 0.0F);
         }

         var21++;
      }
   }
}
