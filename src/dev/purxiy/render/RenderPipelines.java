package dev.purxiy.render;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.systems.RenderSystem.class_5590;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat.class_5595;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.pipeline.BlurPipeline;
import dev.purxiy.render.pipeline.SkyPipelines;
import dev.purxiy.render.pipeline.VertexBuffer;
import dev.purxiy.ui.Theme;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Consumer;
import net.minecraft.class_1011;
import net.minecraft.class_1041;
import net.minecraft.class_12137;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_318;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

import org.lwjgl.system.MemoryUtil;

public final class RenderPipelines {
   private static final RenderPipelines INSTANCE = new RenderPipelines();
   private static final int FRAME_UBO_SIZE = 112;
   private static final int SHEET_UBO_SIZE = 112;
   private static final int WORLD_BLUR_ITERATIONS = 2;
   private static final float SHEET_FADE = 0.2F;
   private static final Matrix4fc IDENTITY = new Matrix4f();
   private final ClipRect hud = new ClipRect("SourceByDevFFnightmare DeObfDeCompByGuideX HUD");
   private final ClipRect ui = new ClipRect("SourceByDevFFnightmare DeObfDeCompByGuideX UI");
   private final ClipRect world = new ClipRect("SourceByDevFFnightmare DeObfDeCompByGuideX World");
   private final ClipRect menu = new ClipRect("SourceByDevFFnightmare DeObfDeCompByGuideX Menu");
   private final ClipRect sheet = new ClipRect("SourceByDevFFnightmare DeObfDeCompByGuideX Sheet");
   private final BlurPipeline blur = new BlurPipeline();
   private final List<Consumer<class_1011>> screenshotRequests = new ArrayList<>();
   private GpuTexture whiteTexture;
   private GpuTextureView whiteView;
   private boolean gpuReady;
   private GpuBuffer sheetUniforms;
   private ByteBuffer sheetStaging;

   private GpuTextureView sheetDepth;
   private float sheetM22 = -1.0F;
   private float sheetM32 = -0.1F;
   private float sheetBehind = 1.0F;

   private RenderPipelines.WorldView sheetSky;

   private RenderPipelines.LowFireView sheetStanding;
   private final long startNanos = System.nanoTime();
   private long lastNanos = this.startNanos;
   private long frame;
   private double time;
   private float delta;
   private int framebufferWidth = 1;
   private int framebufferHeight = 1;
   private float designHeight = 720.0F;
   private int blurIterations = 4;
   private String lastError;

   private RenderPipelines() {
   }

   public static RenderPipelines get() {
      return INSTANCE;
   }

   public Canvas canvas() {
      return this.ui.canvas;
   }

   public Canvas hudCanvas() {
      return this.hud.canvas;
   }

   public Canvas menuCanvas() {
      return this.menu.canvas;
   }

   public Canvas worldCanvas() {
      return this.world.canvas;
   }

   public float time() {
      return (float)this.time;
   }

   public float delta() {
      return this.delta;
   }

   public long frame() {
      return this.frame;
   }

   public RenderPipelines.FogRange stats() {
      return this.ui.stats;
   }

   public RenderPipelines.FogRange hudStats() {
      return this.hud.stats;
   }

   public RenderPipelines.FogRange worldStats() {
      return this.world.stats;
   }

   public void setDesignHeight(float var1) {
      this.designHeight = Math.max(240.0F, var1);
   }

   public void setUserScale(float var1) {
      this.ui.userScale = clampScale(var1);
   }

   public void setHudScale(float var1) {
      this.hud.userScale = clampScale(var1);
   }

   public void setBlurIterations(int var1) {
      this.blurIterations = Math.max(1, Math.min(5, var1));
   }

   public float mouseX() {
      return this.mouse(true, this.ui);
   }

   public float mouseY() {
      return this.mouse(false, this.ui);
   }

   public float hudMouseX() {
      return this.mouse(true, this.hud);
   }

   public float hudMouseY() {
      return this.mouse(false, this.hud);
   }

   public float fromGui(double var1) {
      return (float)(var1 * class_310.method_1551().method_22683().method_4495() / this.ui.pixelScale);
   }

   public float hudFromGui(double var1) {
      return (float)(var1 * class_310.method_1551().method_22683().method_4495() / this.hud.pixelScale);
   }

   public float hudToGui() {
      return this.hud.pixelScale / class_310.method_1551().method_22683().method_4495();
   }

   public void requestScreenshot(Consumer<class_1011> var1) {
      this.screenshotRequests.add(var1);
   }

   public void beginFrame() {
      long var1 = System.nanoTime();
      this.delta = Math.min(0.1F, (float)(var1 - this.lastNanos) / 1.0E9F);
      this.lastNanos = var1;
      this.time = (var1 - this.startNanos) / 1.0E9;
      this.frame++;
      Translations.frame();
      Theme.frame(this.delta);
      class_276 var3 = class_310.method_1551().method_1522();
      this.framebufferWidth = Math.max(1, var3.field_1482);
      this.framebufferHeight = Math.max(1, var3.field_1481);
      this.begin(this.hud);
      this.begin(this.ui);
      this.beginMenu();
      if (!this.gpuReady) {
         this.initGpu();
      }

      Fonts.ATLAS.flushUploads();
   }

   private void begin(ClipRect var1) {
      var1.pixelScale = Math.max(0.5F, this.framebufferHeight / this.designHeight) * var1.userScale;
      var1.quads.reset();
      var1.canvas.begin(this.framebufferWidth / var1.pixelScale, this.framebufferHeight / var1.pixelScale, var1.pixelScale);
      var1.open = true;
   }

   private void beginMenu() {
      ClipRect var1 = this.menu;
      var1.pixelScale = (float)Math.max(0.5, (double)class_310.method_1551().method_22683().method_4495());
      var1.quads.reset();
      var1.canvas.begin(this.framebufferWidth / var1.pixelScale, this.framebufferHeight / var1.pixelScale, var1.pixelScale);
      var1.open = true;
   }

   public void flushMenu() {
      this.flush(this.menu);
   }

   public void flushHud() {
      this.flush(this.hud);
   }

   public void beginWorld() {
      class_276 var1 = class_310.method_1551().method_1522();
      this.framebufferWidth = Math.max(1, var1.field_1482);
      this.framebufferHeight = Math.max(1, var1.field_1481);
      if (!this.gpuReady) {
         this.initGpu();
      }

      this.begin(this.world);
      Fonts.ATLAS.flushUploads();
   }

   public void flushWorld() {
      this.flush(this.world);
   }

   public Canvas beginSheet(Matrix4fc var1, float var2, float var3, float var4, Matrix4fc var5, float var6) {
      return this.beginSheet(var1, var2, var3, var4, var5, var6, (RenderPipelines.WorldView)null);
   }

   public Canvas beginSheet(Matrix4fc var1, float var2, float var3, float var4, Matrix4fc var5, float var6,  RenderPipelines.WorldView var7) {
      return this.beginSheet(var1, var2, var3, var4, var5, var6, var7, (RenderPipelines.LowFireView)null);
   }

   public Canvas beginSheet(
      Matrix4fc var1,
      float var2,
      float var3,
      float var4,
      Matrix4fc var5,
      float var6,
       RenderPipelines.WorldView var7,
       RenderPipelines.LowFireView var8
   ) {
      return this.beginSheet(var1, var2, var3, var4, var5, var6, var7, var8, (GpuTextureView)null);
   }

   public Canvas beginSheet(
      Matrix4fc var1,
      float var2,
      float var3,
      float var4,
      Matrix4fc var5,
      float var6,
       RenderPipelines.WorldView var7,
       RenderPipelines.LowFireView var8,
       GpuTextureView var9
   ) {
      class_276 var10 = class_310.method_1551().method_1522();
      this.framebufferWidth = Math.max(1, var10.field_1482);
      this.framebufferHeight = Math.max(1, var10.field_1481);
      if (!this.gpuReady) {
         this.initGpu();
      }

      ClipRect var11 = this.sheet;
      var11.pixelScale = Math.max(0.001F, var4);
      var11.projection.set(var1);
      var11.quads.reset();
      var11.canvas.begin(var2, var3, var11.pixelScale);
      var11.open = true;
      this.sheetDepth = var9 != null ? var9 : var10.method_71640();
      this.sheetM22 = var5.m22();
      this.sheetM32 = var5.m32();
      this.sheetBehind = Math.max(0.0F, Math.min(1.0F, var6));
      this.sheetSky = var7;
      this.sheetStanding = var7 == null ? var8 : null;
      Fonts.ATLAS.flushUploads();
      return var11.canvas;
   }

   public void flushSheet() {
      this.flush(this.sheet);
   }

   public void endFrame() {
      this.flush(this.ui);
      if (!this.screenshotRequests.isEmpty()) {
         class_276 var1 = class_310.method_1551().method_1522();

         for (Consumer var3 : this.screenshotRequests) {
            class_318.method_1663(var1, var3);
         }

         this.screenshotRequests.clear();
      }
   }

   private void flush(ClipRect var1) {
      if (var1.open) {
         var1.open = false;
         long var2 = System.nanoTime();
         String var4 = var1.canvas.unbalanced();
         if (var4 != null) {
            this.reportError(var1.label + ": unbalanced canvas state at end of frame: " + var4, (Throwable)null);
         }

         var1.stats.quads = var1.quads.quadCount();
         var1.stats.batches = var1.quads.batchCount();
         var1.stats.passes = 0;
         var1.stats.captures = 0;
         if (var1.quads.quadCount() > 0) {
            try {
               this.submit(var1);
            } catch (Throwable var6) {
               this.reportError(var1.label + " submission failed", var6);
            }
         }

         var1.stats.cpuMillis = (float)(System.nanoTime() - var2) / 1000000.0F;
      }
   }

   private void submit(ClipRect var1) {
      class_276 var2 = class_310.method_1551().method_1522();
      GpuTextureView var3 = var2.method_71639();
      if (var3 != null) {
         GpuDevice var4 = RenderSystem.getDevice();
         CommandEncoder var5 = var4.createCommandEncoder();
         VertexBuffer var6 = var1.quads;
         ByteBuffer var7 = var6.data();
         this.ensureBuffers(var1, var7.remaining());
         var5.writeToBuffer(var1.vertexBuffer.slice(0L, var7.remaining()), var7);
         this.writeFrameUniforms(var5, var1);
         boolean var8 = var1 == this.sheet;
         if (var8) {
            this.writeSheetUniforms(var5);
         }

         class_5590 var9 = RenderSystem.getSequentialBuffer(class_5596.field_27382);
         GpuBuffer var10 = var9.method_68274(var6.quadCount() * 6);
         class_5595 var11 = var9.method_31924();
         class_12137 var12 = RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
         GpuTextureView var13 = this.whiteView;
         int var14 = 0;

         for (int var15 = var6.batchCount(); var14 < var15; var1.stats.passes++) {
            if (var6.batchCaptures(var14)) {
               float var16 = var6.batchCaptureStrength(var14);
               int var17 = var1 != this.world && !var8 ? this.blurIterations : Math.min(2, this.blurIterations);
               var13 = this.blur.run(var3, var17, 0.6F + 2.4F * var16);
               var1.stats.captures++;
            }

            try (RenderPass var21 = var5.createRenderPass(() -> var1.label, var3, OptionalInt.empty())) {
               var21.setPipeline(var8 ? SkyPipelines.UI_SHEET : SkyPipelines.UI);
               var21.setUniform("PurxiyFrame", var1.frameUniforms);
               if (var8) {
                  var21.setUniform("PurxiySheet", this.sheetUniforms);
                  var21.bindTexture(
                     "DepthSampler",
                     this.sheetDepth != null ? this.sheetDepth : this.whiteView,
                     RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST)
                  );
               }

               var21.setVertexBuffer(0, var1.vertexBuffer);
               var21.setIndexBuffer(var10, var11);
               var21.bindTexture("Sampler1", var13, var12);

               while (true) {
                  GpuTextureView var22 = var6.batchView(var14);
                  if (var22 != null) {
                     var21.bindTexture("Sampler0", var22, var6.batchSampler(var14));
                  } else {
                     var21.bindTexture("Sampler0", this.whiteView, var12);
                  }

                  var21.drawIndexed(0, var6.batchFirst(var14) * 6, var6.batchQuads(var14) * 6, 1);
                  var14++;
                  if (var14 >= var15 || var6.batchCaptures(var14)) {
                     break;
                  }
               }
            }
         }
      }
   }

   private void writeFrameUniforms(CommandEncoder var1, ClipRect var2) {
      if (var2 != this.sheet) {
         var2.projection.setOrtho(0.0F, var2.canvas.width(), var2.canvas.height(), 0.0F, -1.0F, 1.0F);
      }

      ByteBuffer var3 = var2.frameStaging;
      var3.clear();
      var2.projection.get(0, var3);
      var3.putFloat(64, this.framebufferWidth);
      var3.putFloat(68, this.framebufferHeight);
      var3.putFloat(72, var2.pixelScale);
      var3.putFloat(76, (float)(this.time % 3600.0));
      Theme.writeLogo(var3, 80);
      var3.position(0).limit(112);
      var1.writeToBuffer(var2.frameUniforms.slice(), var3);
   }

   private void writeSheetUniforms(CommandEncoder var1) {
      if (this.sheetUniforms == null) {
         this.sheetUniforms = RenderSystem.getDevice().createBuffer(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX sheet", 136, 112L);
         this.sheetStaging = MemoryUtil.memAlloc(112);
      }

      ByteBuffer var2 = this.sheetStaging;
      var2.clear();
      var2.putFloat(0, this.sheetM22);
      var2.putFloat(4, this.sheetM32);
      var2.putFloat(8, this.sheetBehind);
      var2.putFloat(12, 0.2F);
      RenderPipelines.WorldView var3 = this.sheetSky;
      var2.putFloat(16, var3 != null ? 1.0F : 0.0F);
      var2.putFloat(20, var3 != null ? var3.cameraY() : 0.0F);
      var2.putFloat(24, var3 != null ? var3.cloudY() : 0.0F);
      var2.putFloat(28, var3 != null ? var3.fogStart() : 0.0F);
      var2.putFloat(32, var3 != null ? var3.fogEnd() : 1.0F);
      RenderPipelines.LowFireView var4 = this.sheetStanding;
      var2.putFloat(36, var4 != null ? 1.0F : 0.0F);
      var2.putFloat(40, var4 != null ? var4.lowTop() : 0.0F);
      var2.putFloat(44, 0.0F);
      (var3 != null ? var3.world() : (var4 != null ? var4.world() : IDENTITY)).get(48, var2);
      var2.position(0).limit(112);
      var1.writeToBuffer(this.sheetUniforms.slice(), var2);
   }

   private void ensureBuffers(ClipRect var1, int var2) {
      GpuDevice var3 = RenderSystem.getDevice();
      if (var1.frameUniforms == null) {
         var1.frameUniforms = var3.createBuffer(() -> var1.label + " frame uniforms", 136, 112L);
         var1.frameStaging = MemoryUtil.memAlloc(112);
      }

      if (var1.vertexBuffer == null || var1.vertexBuffer.size() < var2) {
         long var4 = 1048576L;

         while (var4 < var2) {
            var4 <<= 1;
         }

         if (var1.vertexBuffer != null) {
            var1.vertexBuffer.close();
         }

         var1.vertexBuffer = var3.createBuffer(() -> var1.label + " vertices", 40, var4);
      }
   }

   private void initGpu() {
      GpuDevice var1 = RenderSystem.getDevice();
      this.whiteTexture = var1.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX white", 5, TextureFormat.RGBA8, 1, 1, 1, 1);

      try (class_1011 var2 = new class_1011(1, 1, false)) {
         var2.method_61941(0, 0, -1);
         var1.createCommandEncoder().writeToTexture(this.whiteTexture, var2);
      }

      this.whiteView = var1.createTextureView(this.whiteTexture);
      this.gpuReady = true;
      ClientServices.LOGGER.info("SourceByDevFFnightmare DeObfDeCompByGuideX renderer ready ({} {}, {})", new Object[]{var1.getBackendName(), var1.getVersion(), var1.getRenderer()});
   }

   private float mouse(boolean var1, ClipRect var2) {
      class_310 var3 = class_310.method_1551();
      class_1041 var4 = var3.method_22683();
      return var1
         ? (float)(var3.field_1729.method_1603() * this.framebufferWidth / Math.max(1, var4.method_4480()) / var2.pixelScale)
         : (float)(var3.field_1729.method_1604() * this.framebufferHeight / Math.max(1, var4.method_4507()) / var2.pixelScale);
   }

   private static float clampScale(float var0) {
      return Math.max(0.25F, Math.min(4.0F, var0));
   }

   private void reportError(String var1, Throwable var2) {
      String var3 = var1 + (var2 == null ? "" : var2.toString());
      if (!var3.equals(this.lastError)) {
         this.lastError = var3;
         ClientServices.LOGGER.error(var1, var2);
      }
   }

   public record WorldView(Matrix4fc world, float cameraY, float cloudY, float fogStart, float fogEnd) {
   }

   public static final class FogRange {
      public int quads;
      public int batches;
      public int passes;
      public int captures;
      public float cpuMillis;
   }

   public record LowFireView(Matrix4fc world, float lowTop) {
   }
}
