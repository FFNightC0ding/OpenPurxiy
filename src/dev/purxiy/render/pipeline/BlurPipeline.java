package dev.purxiy.render.pipeline;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import java.nio.ByteBuffer;
import java.util.OptionalInt;
import net.minecraft.class_12137;
import org.lwjgl.system.MemoryUtil;

public final class BlurPipeline implements AutoCloseable {
   public static final int MAX_ITERATIONS = 5;
   private static final int USAGE = 14;
   private final GpuTexture[] textures = new GpuTexture[5];
   private final GpuTextureView[] views = new GpuTextureView[5];
   private int sourceWidth = -1;
   private int sourceHeight = -1;
   private GpuBuffer uniforms;
   private int uniformStride;
   private ByteBuffer staging;

   public GpuTextureView run(GpuTextureView var1, int var2, float var3) {
      int var4 = var1.getWidth(0);
      int var5 = var1.getHeight(0);
      this.ensureResources(var4, var5);
      var2 = Math.max(1, Math.min(5, var2));
      GpuDevice var6 = RenderSystem.getDevice();
      CommandEncoder var7 = var6.createCommandEncoder();
      class_12137 var8 = RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
      this.staging.clear();
      int var9 = 0;

      for (int var10 = 0; var10 < var2; var10++) {
         int var11 = var10 == 0 ? var4 : this.textures[var10 - 1].getWidth(0);
         int var12 = var10 == 0 ? var5 : this.textures[var10 - 1].getHeight(0);
         this.putParams(var9++, var11, var12, var3);
      }

      for (int var15 = var2 - 1; var15 > 0; var15--) {
         this.putParams(var9++, this.textures[var15].getWidth(0), this.textures[var15].getHeight(0), var3);
      }

      this.staging.position(0).limit(var9 * this.uniformStride);
      var7.writeToBuffer(this.uniforms.slice(0L, (long)var9 * this.uniformStride), this.staging);
      var9 = 0;
      GpuTextureView var16 = var1;

      for (int var17 = 0; var17 < var2; var17++) {
         this.pass(var7, SkyPipelines.BLUR_DOWN, var16, this.views[var17], var8, var9++);
         var16 = this.views[var17];
      }

      for (int var18 = var2 - 1; var18 > 0; var18--) {
         this.pass(var7, SkyPipelines.BLUR_UP, this.views[var18], this.views[var18 - 1], var8, var9++);
      }

      return this.views[0];
   }

   private void putParams(int var1, int var2, int var3, float var4) {
      int var5 = var1 * this.uniformStride;
      this.staging.putFloat(var5, 0.5F / var2);
      this.staging.putFloat(var5 + 4, 0.5F / var3);
      this.staging.putFloat(var5 + 8, var4);
      this.staging.putFloat(var5 + 12, 0.0F);
   }

   private void pass(CommandEncoder var1, RenderPipeline var2, GpuTextureView var3, GpuTextureView var4, class_12137 var5, int var6) {
      try (RenderPass var7 = var1.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX backdrop blur", var4, OptionalInt.empty())) {
         var7.setPipeline(var2);
         var7.setUniform("BlurInfo", this.uniforms.slice((long)var6 * this.uniformStride, 16L));
         var7.bindTexture("InSampler", var3, var5);
         var7.draw(0, 3);
      }
   }

   private void ensureResources(int var1, int var2) {
      GpuDevice var3 = RenderSystem.getDevice();
      if (this.uniforms == null) {
         int var4 = Math.max(16, var3.getUniformOffsetAlignment());
         this.uniformStride = (16 + var4 - 1) / var4 * var4;
         byte var5 = 10;
         this.uniforms = var3.createBuffer(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX blur params", 136, (long)this.uniformStride * var5);
         this.staging = MemoryUtil.memAlloc(this.uniformStride * var5);
      }

      if (var1 != this.sourceWidth || var2 != this.sourceHeight) {
         this.releaseTextures();
         this.sourceWidth = var1;
         this.sourceHeight = var2;

         for (int var8 = 0; var8 < 5; var8++) {
            int var9 = Math.max(1, var1 >> var8 + 1);
            int var6 = Math.max(1, var2 >> var8 + 1);
            int var7 = var8;
            this.textures[var8] = var3.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX blur level " + var7, 14, TextureFormat.RGBA8, var9, var6, 1, 1);
            this.views[var8] = var3.createTextureView(this.textures[var8]);
         }
      }
   }

   private void releaseTextures() {
      for (int var1 = 0; var1 < 5; var1++) {
         if (this.views[var1] != null) {
            this.views[var1].close();
            this.views[var1] = null;
         }

         if (this.textures[var1] != null) {
            this.textures[var1].close();
            this.textures[var1] = null;
         }
      }
   }

   @Override
   public void close() {
      this.releaseTextures();
      if (this.uniforms != null) {
         this.uniforms.close();
         this.uniforms = null;
      }

      if (this.staging != null) {
         MemoryUtil.memFree(this.staging);
         this.staging = null;
      }

      this.sourceWidth = this.sourceHeight = -1;
   }
}
