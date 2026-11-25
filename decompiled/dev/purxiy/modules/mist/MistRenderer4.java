package dev.purxiy.modules.mist;

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
import java.util.List;
import java.util.OptionalInt;
import net.minecraft.class_10789;
import net.minecraft.class_290;
import net.minecraft.class_6367;
import org.lwjgl.system.MemoryUtil;

public final class MistRenderer4 {
   public static final float a = 216.0F;
   private static final float c = 44.0F;
   private static final float M = 146.0F;
   private static final float Q = 120.0F;
   private static final float O = 8.0F;
   private static final int A = -4222985;
   private static final List<MistCatalog> Z = MistCatalog.l;
   private static final int V = 80;
   private static final String[] X = new String[]{
      "Pearl in the shade, warm cream in the sun, amber when it is low",
      "Purxiy's own: lilac in the shade, a creamy blush in the sun",
      "Pure and silvery, a little blue in the shade",
      "Rose and peach, as if the sun never quite got up"
   };
   private static final RenderPipeline I = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/mist_preview"))
      .withVertexShader(ClientServices.id("core/mist"))
      .withFragmentShader(ClientServices.id("core/mist_preview"))
      .withUniform("PurxiyMistPreview", class_10789.field_60031)
      .withoutBlend()
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static final class_6367[] n = new class_6367[Z.size()];
   private static final GpuBuffer[] U = new GpuBuffer[Z.size()];
   private static ByteBuffer F;
   private static String v;
   private final MistModule k;
   private final Animator[] f = new Animator[Z.size()];
   private float K;
   private float m;
   private float y;
   private float J;
   private boolean w;
   private static final Integer[] g = new Integer[4];
   private static final long h = 80L;

   public MistRenderer4(MistModule var1) {
      this.k = var1;

      for (int var2 = 0; var2 < this.f.length; var2++) {
         this.f[var2] = Animator.smooth(0.0F);
      }
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 85502108283715L;
      long var8 = var6 ^ 135314025602810L;
      this.K = var2;
      this.m = var3;
      this.y = var4;
      this.w = var5 && !var1.masked();
      this.J = this.J + Math.min(var1.dt, 0.1F) * (0.3F + 0.7F * this.k.H.floatValue());
      Canvas var10 = var1.canvas;
      var10.text(Fonts.MEDIUM, "Choose a colour", var2, var3 + 10.0F, 13.5F, Icons.TEXT);
      var10.text(Fonts.REGULAR, "An autumn morning in each one, in your density, height and sun", var2, var3 + 30.0F, 11.5F, Icons.TEXT_2);
      float var11 = this.g();
      int var12 = this.k.P.index();

      for (int var13 = 0; var13 < Z.size(); var13++) {
         float var14 = var2 + var13 * (var11 + 8.0F);
         float var15 = var3 + 44.0F;
         boolean var16 = this.w && var1.hover(this.f[var13], var14, var15, var11, 146.0F, TypeE.HAND);
         float var17 = this.f[var13].target(var16 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
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
         this.a(var10, var8, var19, var20, var21, var22, var13, var23);
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
            Z.get(var13).C,
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
         X[Math.clamp((long)var12, 0, X.length - 1)],
         var2 + 14.0F,
         var3 + 216.0F - 12.0F,
         10.5F,
         var4 - 14.0F,
         Icons.TEXT_2,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
   }

   private float g() {
      return (this.y - 8.0F * (Z.size() - 1)) / Z.size();
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      if (this.w && var3 == 0) {
         float var4 = this.g();

         for (int var5 = 0; var5 < Z.size(); var5++) {
            float var6 = this.K + var5 * (var4 + 8.0F);
            float var7 = this.m + 44.0F;
            if (var1 >= var6 && var1 < var6 + var4 && var2 >= var7 && var2 < var7 + 146.0F) {
               if (this.k.P.index() != var5) {
                  this.k.P.select(var5);
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

   private void a(Canvas var1, long var2, float var4, float var5, float var6, float var7, int var8, float var9) {
      MistRenderer3.ensureSourceClassInitialized();

      try {
         GpuDevice var10 = RenderSystem.getDevice();
         if (!var10.precompilePipeline(I).isValid()) {
            return;
         }

         float var11 = (float)Math.hypot(var1.transform().m00(), var1.transform().m01());
         float var12 = Math.max(0.5F, var1.pixelScale() * var11);
         int var13 = Math.max(1, Math.round(var6 * var12));
         int var14 = Math.max(1, Math.round(117.0F * var12));
         class_6367 var15 = n[var8];
         long var16 = 60278836784569L;
         int var33 = var16 < 0L ? -1 : 1;
         if (var15 == null) {
            var15 = new class_6367("Purxiy mist preview " + Z.get(var8).C, var13, var14, false);
            n[var8] = var15;
         } else {
            label96: {
               if (var15.field_1482 == var13) {
                  long var19 = 60278836784569L;
                  int var201 = var19 < 0L ? -1 : 1;
                  if (var15.field_1481 == var14) {
                     break label96;
                  }
               }

               var15.method_1234(var13, var14);
            }
         }

         GpuBuffer var33x = U[var8];
         long var20 = 60278836784568L;
         int var23 = var20 < 0L ? -1 : 1;
         if (var33x == null) {
            var33x = var10.createBuffer(ClientServices.label(() -> "Purxiy mist preview"), 136, 80L);
            U[var8] = var33x;
         }

         if (F == null) {
            F = MemoryUtil.memAlloc(80);
         }

         CommandEncoder var23x = var10.createCommandEncoder();
         var23x.writeToBuffer(var33x.slice(), this.h(var13, var14, var8, var9));
         long var24 = 60278836784569L;
         int var24x = var24 < 0L ? -1 : 1;
         RenderPass var26x = var23x.createRenderPass(() -> "Purxiy mist preview", var15.method_71639(), OptionalInt.empty());

         try {
            var26x.setPipeline(I);
            RenderSystem.bindDefaultUniforms(var26x);
            long var27 = 60278836784568L;
            int var29 = var27 < 0L ? -1 : 1;
            var26x.setUniform("PurxiyMistPreview", var33x);
            var29 = 1029213700;
            var26x.draw(0, 3);
            int var22 = 1029233298;
            int var18x = -1029214791;
            MistRenderer3.metric("drawn");
         } catch (Throwable var31) {
            if (var26x != null) {
               try {
                  var26x.close();
               } catch (Throwable var30) {
                  var31.addSuppressed(var30);
               }
            }

            throw var31;
         }

         if (var26x != null) {
            var26x.close();
         }

         var1.image(
            var15.method_71639(),
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
      } catch (RuntimeException var321) {
         if (!var321.toString().equals(v)) {
            ClientServices.LOGGER.error("Purxiy mist preview could not render", var321);
         }

         v = var321.toString();
      }
   }

   private ByteBuffer h(int var1, int var2, int var3, float var4) {
      MistCatalog var5 = Z.get(var3);
      MistModule var6 = this.k;
      float var7 = (var6.l.floatValue() - 3.0F) / 29.0F;
      Std140Builder var8 = Std140Builder.intoBuffer(F.clear())
         .putVec4(var1, var2, var3, var4)
         .putVec4(MistCatalog.channel(var5.N, 0), MistCatalog.channel(var5.N, 1), MistCatalog.channel(var5.N, 2), 0.0F)
         .putVec4(MistCatalog.channel(var5.h, 0), MistCatalog.channel(var5.h, 1), MistCatalog.channel(var5.h, 2), 0.0F)
         .putVec4(MistCatalog.channel(var5.I, 0), MistCatalog.channel(var5.I, 1), MistCatalog.channel(var5.I, 2), 0.0F)
         .putVec4((var6.U.floatValue() - 0.1F) / 0.9F, var7, var6.I.floatValue(), this.J % 3600.0F);
      return var8.get();
   }
}
