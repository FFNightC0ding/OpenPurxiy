package dev.purxiy.modules.knife;

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
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.render.Canvas;
import dev.purxiy.ui.Theme;
import java.nio.ByteBuffer;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import net.minecraft.class_10789;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_6367;
import org.lwjgl.system.MemoryUtil;

public final class KnifeEditorPanel {
   public static final int T = 9;
   private static final int f = class_290.field_1577.getVertexSize();
   private static final int o = 32;
   private static final RenderPipeline u = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/knife_preview"))
      .withVertexShader(ClientServices.id("core/knife_preview"))
      .withFragmentShader(ClientServices.id("core/knife_preview"))
      .withUniform("KnifePreview", class_10789.field_60031)
      .withUniform("PurxiyTheme", class_10789.field_60031)
      .withSampler("CamoSampler")
      .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
      .withDepthWrite(true)
      .withCull(false)
      .withoutBlend()
      .withVertexFormat(class_290.field_1577, class_5596.field_27379)
      .build();
   private static final KnifeEditorPanel.KnifePanelState[] l = new KnifeEditorPanel.KnifePanelState[9];
   private static String J;
   private static final long e = 32L;

   private KnifeEditorPanel() {
   }

   public static void draw(Canvas var0, KnifeBuilder var1, class_2960 var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9) {
      draw(var0, 0, var1, var2, var3, var4, var5, var6, 0.38F, var7, var8, var9);
   }

   public static void draw(
      Canvas var0,
      int var1,
      KnifeBuilder var2,
      class_2960 var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11
   ) {
      long var12 = 102747219823828L;
      long var14 = var12 ^ 110650866676203L;
      if (var2.vertexCount() != 0 && !(var10 <= 0.0F) && !(var11 <= 0.0F) && !(var6 <= 0.0F) && !(var0.alpha() <= 0.0F) && var1 >= 0 && var1 < 9) {
         try {
            GpuDevice var16 = RenderSystem.getDevice();
            if (!var16.precompilePipeline(u).isValid()) {
               return;
            }

            float var17 = (float)Math.hypot(var0.transform().m00(), var0.transform().m01());
            float var18 = (float)Math.hypot(var0.transform().m10(), var0.transform().m11());
            float var19 = Math.min(4096, var16.getMaxTextureSize());
            float var20 = Math.min(Math.max(0.5F, 2.0F * var0.pixelScale() * Math.max(var17, var18)), Math.min(var19 / var10, var19 / var11));
            int var21 = Math.max(1, Math.round(var10 * var20));
            int var22 = Math.max(1, Math.round(var11 * var20));
            if (l[var1] == null) {
               l[var1] = new KnifeEditorPanel.KnifePanelState();
            }

            KnifeEditorPanel.KnifePanelState var23 = l[var1];
            J(var23, var16, var21, var22, var2.vertexCount() * f, var14);
            CommandEncoder var24 = var16.createCommandEncoder();
            if (var23.i != var2) {
               ByteBuffer var25 = var23.p;
               var25.clear();

               for (int var26 = 0; var26 < var2.vertexCount(); var26++) {
                  int var27 = var2.color(var26);
                  var25.putFloat(var2.x(var26)).putFloat(var2.y(var26)).putFloat(var2.z(var26));
                  var25.putFloat(var2.u(var26)).putFloat(var2.v(var26));
                  var25.put((byte)(var27 >> 16)).put((byte)(var27 >> 8)).put((byte)var27).put((byte)Math.round(var2.metal(var26) * 255.0F));
                  var25.put((byte)Math.round(var2.nx(var26) * 127.0F));
                  var25.put((byte)Math.round(var2.ny(var26) * 127.0F));
                  var25.put((byte)Math.round(var2.nz(var26) * 127.0F)).put((byte)0);
               }

               var25.flip();
               var24.writeToBuffer(var23.U.slice(0L, var25.remaining()), var25);
               var23.i = var2;
            }

            float var36 = Math.clamp(var8, -0.95F, 0.95F);
            float var37 = (float)Math.sqrt(1.0F - var36 * var36);
            Std140Builder var38 = Std140Builder.intoBuffer(var23.m.clear())
               .putVec4((float)Math.cos(var7), (float)Math.sin(var7), var37, var36)
               .putVec4(2.0F * var6 / var10, 2.0F * var6 / var11, var9, 0.03125F);
            var24.writeToBuffer(var23.j.slice(), var38.get());
            GpuTextureView var28 = class_310.method_1551().method_1531().method_4619(var3).method_71659();
            GpuBuffer var29 = Theme.uniforms();
            RenderPass var30 = var24.createRenderPass(
               () -> "SourceByDevFFnightmare DeObfDeCompByGuideX knife collection depth preview", var23.N.method_71639(), OptionalInt.of(0), var23.N.method_71640(), OptionalDouble.of(1.0)
            );

            try {
               var30.setPipeline(u);
               var30.setUniform("KnifePreview", var23.j);
               var30.setUniform("PurxiyTheme", var29);
               var30.bindTexture("CamoSampler", var28, RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR));
               var30.setVertexBuffer(0, var23.U);
               var30.draw(0, var2.vertexCount());
               KnifeSwingState.metric("drawn");
            } catch (Throwable var34) {
               if (var30 != null) {
                  try {
                     var30.close();
                  } catch (Throwable var33) {
                     var34.addSuppressed(var33);
                  }
               }

               throw var34;
            }

            if (var30 != null) {
               var30.close();
            }

            var0.image(
               var23.N.method_71639(),
               RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR),
               true,
               var4 - var10 / 2.0F,
               var5 - var11 / 2.0F,
               var10,
               var11,
               0.0F,
               1.0F,
               1.0F,
               0.0F,
               0.0F,
               -1
            );
            J = null;
         } catch (RuntimeException var351) {
            if (!var351.toString().equals(J)) {
               ClientServices.LOGGER.error("SourceByDevFFnightmare DeObfDeCompByGuideX knife collection preview could not render", var351);
            }

            J = var351.toString();
         }
      }
   }

   private static void J(KnifeEditorPanel.KnifePanelState var0, GpuDevice var1, int var2, int var3, int var4, long var5) {
      KnifeSwingState.ensureSourceClassInitialized();
      if (var0.N == null) {
         var0.N = new class_6367("SourceByDevFFnightmare DeObfDeCompByGuideX knife collection supersampled preview", var2, var3, true);
      } else {
         label75: {
            if (var0.N.field_1482 == var2) {
               long var7 = 63567026994495L;
               int var20 = var7 < 0L ? -1 : 1;
               if (var0.N.field_1481 == var3) {
                  break label75;
               }
            }

            var0.N.method_1234(var2, var3);
         }
      }

      long var19 = 63567026994495L;
      int var10 = var19 < 0L ? -1 : 1;
      if (var0.j == null) {
         var0.j = var1.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX knife collection camera"), 136, 32L);
         var0.m = MemoryUtil.memAlloc(32);
      }

      if (var0.p != null) {
         var10 = var0.p.capacity();
         long var11 = 63567026994495L;
         int var14 = var11 < 0L ? -1 : 1;
         if (var10 >= var4) {
            return;
         }
      }

      var10 = 65536;

      while (var10 < var4) {
         int var21 = var10 << 1;
         long var12 = 63567026994495L;
         int var13 = var12 < 0L ? -1 : 1;
         var10 = var21;
      }

      long var22 = 63567026994494L;
      int var23 = var22 < 0L ? -1 : 1;
      long var14 = 63567026994495L;
      int var14x = var14 < 0L ? -1 : 1;
      var0.p = var0.p == null ? MemoryUtil.memAlloc(var10) : MemoryUtil.memRealloc(var0.p, var10);
      long var16x = 63567026994494L;
      if (var0.U != null) {
         var0.U.close();
      }

      var0.U = var1.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX knife collection mesh"), 40, var10);
      var0.i = null;
   }

   public static void close() {
      ClientCache.release(u);

      for (int var0 = 0; var0 < 9; var0++) {
         KnifeEditorPanel.KnifePanelState var1 = l[var0];
         if (var1 != null) {
            if (var1.N != null) {
               var1.N.method_1238();
            }

            if (var1.U != null) {
               var1.U.close();
            }

            if (var1.j != null) {
               var1.j.close();
            }

            if (var1.p != null) {
               MemoryUtil.memFree(var1.p);
            }

            if (var1.m != null) {
               MemoryUtil.memFree(var1.m);
            }

            l[var0] = null;
         }
      }

      J = null;
   }

   private static final class KnifePanelState {
      class_6367 N;
      GpuBuffer U;
      GpuBuffer j;
      ByteBuffer p;
      ByteBuffer m;
      KnifeBuilder i;
   }
}
