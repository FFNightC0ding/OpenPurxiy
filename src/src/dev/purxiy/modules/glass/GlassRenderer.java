// Sourced By DevFFNightmare
package dev.purxiy.modules.glass;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.GpuQuery;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.systems.RenderSystem.class_5590;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.render.pipeline.BlurPipeline;
import dev.purxiy.render.pipeline.SkyVertexFormat;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Locale;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import net.minecraft.class_12137;
import net.minecraft.class_310;

import org.lwjgl.system.MemoryUtil;

final class GlassRenderer {
   static final VertexFormat l = VertexFormat.builder()
      .add("Position", VertexFormatElement.POSITION)
      .add("UV0", VertexFormatElement.UV0)
      .add("UV2", VertexFormatElement.UV2)
      .add("Normal", VertexFormatElement.NORMAL)
      .padding(1)
      .add("Bounds", SkyVertexFormat.POS_LOCAL)
      .add("Color", VertexFormatElement.COLOR)
      .build();
   static final int Q = l.getVertexSize();
   private static final int E = 2432;
   static final GlassRenderer x = new GlassRenderer("world");
   static final GlassRenderer f = new GlassRenderer("hand");
   private static boolean w;
   
   private static GpuQuery S;
   private static double z;
   private static double W;
   private static int A = 0;
   private static int i = 0;
   private final String M;
   private final BlurPipeline y = new BlurPipeline();
   
   private GpuTexture B;
   
   private GpuTexture u;
   
   private GpuTexture F;
   
   private GpuTexture I;
   
   private GpuTextureView m;
   
   private GpuTextureView V;
   
   private GpuTextureView U;
   
   private GpuTextureView R;
   private int g;
   private int G;
   
   private GpuTexture O;
   
   private GpuTextureView n;
   private final GpuBuffer[] v = new GpuBuffer[2];
   private final GpuBuffer[] j = new GpuBuffer[2];
   
   private GpuBuffer k;
   
   private GpuBuffer c;
   private int o;
   
   private ByteBuffer H;
   
   private ByteBuffer N;

   private static final String[] b = new String[45];
   private static final long[] d = new long[]{
      8013137651538628038L,
      -1513917034087359810L,
      -929436442775133583L,
      -3563803372754449745L,
      -3365334781116882231L,
      6197126882483322543L,
      3996723385753820197L,
      -481475423554873489L,
      -2844743780493384912L,
      -7840671400791721679L,
      -1573795026586046590L,
      -3539577567267873160L,
      3415752848156853126L,
      -2520772646441956241L,
      8238675789704242565L,
      4280467044825134865L,
      2891871018008847839L,
      7629463180431439864L,
      8358961355062582926L,
      -4935919808191098959L,
      6460740706051749800L,
      -6611642928178133740L
   };
   private static final Integer[] e = new Integer[22];
   private static final long[] h = new long[]{
      448470122135181169L,
      3731743169182759295L,
      62961813098220130L,
      6598730335361180519L,
      5585060103121584438L,
      1502639477266082975L,
      1306791685077364212L,
      1978220280129454186L,
      2165209357876807353L,
      6520492480304154200L,
      658238097194940187L,
      9052672013958412341L,
      2524648974023822554L,
      2298907502400741942L,
      771853207943256189L,
      7463934829437611741L,
      4685869612893761398L,
      2033884302629082291L,
      7867987664721996466L
   };
   private static final Long[] p = new Long[19];

   GlassRenderer(String var1) {
      this.M = var1;
   }

   static boolean ready() {
      GpuDevice var0 = RenderSystem.getDevice();
      return var0.precompilePipeline(GlassSupport3.y).isValid()
         && var0.precompilePipeline(GlassSupport3.A).isValid()
         && var0.precompilePipeline(GlassSupport3.d).isValid()
         && var0.precompilePipeline(GlassSupport3.a).isValid()
         && var0.precompilePipeline(GlassSupport3.L).isValid()
         && var0.precompilePipeline(GlassSupport3.F).isValid()
         && var0.precompilePipeline(GlassSupport3.M).isValid()
         && var0.precompilePipeline(GlassSupport3.j).isValid()
         && var0.precompilePipeline(GlassSupport3.B).isValid()
         && var0.precompilePipeline(GlassSupport3.w).isValid();
   }

   GpuTextureView keepDepth(GpuTexture var1, int var2, int var3) {
      GpuDevice var4 = RenderSystem.getDevice();
      if (this.O == null || this.O.getWidth(0) != var2 || this.O.getHeight(0) != var3) {
         if (this.O != null) {
            this.n.close();
            this.O.close();
         }

         this.O = var4.createTexture(() -> "Purxiy glass " + this.M + " opaque depth", 5, TextureFormat.DEPTH32, var2, var3, 1, 1);
         this.n = var4.createTextureView(this.O);
      }

      var4.createCommandEncoder().copyTextureToTexture(var1, this.O, 0, 0, 0, 0, 0, var2, var3);
      return this.n;
   }

   void draw(
      GlassSupport var1,
      GpuTexture var2,
      GpuTextureView var3,
      int var4,
      int var5,
      GlassSupport4 var6,
       GpuTextureView var7,
       GpuBufferSlice var8
   ) {
      long var9 = 102557690267443L;
      long var11 = var9 ^ 114550279712334L;
      long var13 = var9 ^ 108089264342372L;
      long var15 = var9 ^ 123721463322451L;
      long var17 = var9 ^ 63020979249645L;
      long var19 = var9 ^ 113675210550870L;
      long var21 = var9 ^ 4182124073537L;
      boolean var23 = !var6.y;
      if (!var23 || var7 != null && var8 != null) {
         boolean var24 = w && (this == x || this == f);
         long var25 = System.nanoTime();
         GpuDevice var27 = RenderSystem.getDevice();
         CommandEncoder var28 = var27.createCommandEncoder();
         this.L(var27, var17, var4, var5);
         this.o ^= 1;
         this.c = this.j[this.o];
         List var29 = var1.runs();
         int var30 = this.F(var27, var28, var1, var21);
         if (var30 != 0 && !var29.isEmpty()) {
            int var31 = 0;

            for (GlassSupport.Data var33 : (Iterable<GlassSupport.Data>)(Object)(var29)) {
               var31 = Math.max(var31, var33.count() / 4);
            }

            this.z(var28, var6, var4, var13, var5);
            GpuQuery var54 = var24 ? L(var28, var11) : null;

            try {
               class_12137 var55 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
               class_12137 var34 = RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
               class_5590 var35 = RenderSystem.getSequentialBuffer(class_5596.field_27382);
               GpuBuffer var36 = var35.method_68274(var31 * 6);
               GpuTextureView var37 = class_310.method_1551().field_1773.method_22974().method_71650();

               try (RenderPass var38 = var28.createRenderPass(() -> "Purxiy glass " + this.M + " (far side)", var3, OptionalInt.empty())) {
                  var38.setPipeline(var23 ? GlassSupport3.y : GlassSupport3.A);
                  var38.setUniform("PurxiyGlass", this.c);
                  if (var23) {
                     var38.setUniform("Fog", var8);
                     var38.bindTexture("DepthSampler", var7, var55);
                  }

                  var38.bindTexture("Sampler2", var37, var34);
                  var38.setVertexBuffer(0, this.k);
                  var38.setIndexBuffer(var36, var35.method_31924());
                  X(var38, var15, var29);
               }

               var28.copyTextureToTexture(var2, this.B, 0, 0, 0, 0, 0, var4, var5);
               float var56 = var6.l;
               GpuTextureView var39 = var56 > 0.01F ? this.y.run(this.m, var56 < 0.3F ? 2 : (var56 < 0.65F ? 3 : 4), 1.0F + var56 * 1.5F) : this.m;

               try (RenderPass var40 = var28.createRenderPass(() -> "Purxiy glass " + this.M, var3, OptionalInt.empty(), this.V, OptionalDouble.of(1.0))) {
                  var40.setUniform("PurxiyGlass", this.c);
                  if (var23) {
                     var40.setUniform("Fog", var8);
                     var40.bindTexture("DepthSampler", var7, var55);
                  }

                  var40.bindTexture("Sampler2", var37, var34);
                  var40.bindTexture("SceneSampler", this.m, var34);
                  var40.bindTexture("FrostSampler", var39, var34);
                  var40.setVertexBuffer(0, this.k);
                  var40.setIndexBuffer(var36, var35.method_31924());

                  for (int var41 = 0; var41 < 2; var41++) {
                     var40.setPipeline(var41 == 0 ? (var23 ? GlassSupport3.d : GlassSupport3.L) : (var23 ? GlassSupport3.a : GlassSupport3.F));
                     X(var40, var15, var29);
                  }
               }

               if (var6.Z > 0.01F) {
                  this.D(var19, var28, var3, var55, var34);
               }
            } finally {
               if (var54 != null) {
                  var28.timerQueryEnd(var54);
                  S = var54;
               }
            }

            if (var24) {
               W = W + (System.nanoTime() - var25) / 1000000.0;
               i += var30;
            }
         }
      }
   }

   private static void X(RenderPass var0, long var1, List var3) {
      GlassRenderer4.ensureSourceClassInitialized();
      int var4 = 0;

      for (GlassSupport.Data var6 : (Iterable<GlassSupport.Data>)(Object)(var3)) {
         var0.bindTexture("Sampler0", var6.texture().comp_5226(), var6.texture().comp_5227());
         var0.drawIndexed(var4, 0, var6.count() / 4 * 6, 1);
         GlassRegistry.metric("drawn");
         var4 += var6.count();
      }
   }

   private void D(long var1, CommandEncoder var3, GpuTextureView var4, class_12137 var5, class_12137 var6) {
      GlassRenderer4.ensureSourceClassInitialized();

      try (RenderPass var7 = var3.createRenderPass(() -> "Purxiy glass " + this.M + " halo source", this.U, OptionalInt.of(0))) {
         var7.setPipeline(GlassSupport3.M);
         var7.setUniform("PurxiyGlass", this.c);
         long var8 = 63932739479908L;
         if (63932739479908L == 0L) {
            boolean var10000 = false;
         } else {
            int var29 = var8 < 0L ? -1 : 1;
         }

         int var11 = -2143693940;
         int var10 = -2143688347;
         var7.bindTexture("GlassDepthSampler", this.V, var5);
         var7.draw(0, 3);
         var11 = -2143688347;
         GlassRegistry.metric("drawn");
      }

      long var21 = 63932739479909L;
      if (63932739479909L == 0L) {
         boolean var30 = false;
      } else {
         int var31 = var21 < 0L ? -1 : 1;
      }

      try (RenderPass var23 = var3.createRenderPass(() -> "Purxiy glass " + this.M + " halo across", this.R, OptionalInt.empty())) {
         var23.setPipeline(GlassSupport3.j);
         var23.bindTexture("HaloSampler", this.U, var6);
         int var9 = 2143695126;
         var23.draw(0, 3);
         GlassRegistry.metric("drawn");
      }

      try (RenderPass var24 = var3.createRenderPass(() -> "Purxiy glass " + this.M + " halo down", this.U, OptionalInt.empty())) {
         var24.setPipeline(GlassSupport3.B);
         var24.bindTexture("HaloSampler", this.R, var6);
         int var22 = 2143695126;
         var24.draw(0, 3);
         GlassRegistry.metric("drawn");
         long var27 = 63932739479909L;
         if (63932739479909L == 0L) {
            boolean var32 = false;
         } else {
            int var33 = var27 < 0L ? -1 : 1;
         }
      }

      try (RenderPass var25 = var3.createRenderPass(() -> "Purxiy glass " + this.M + " halo", var4, OptionalInt.empty())) {
         var25.setPipeline(GlassSupport3.w);
         long var28 = 63932739479908L;
         if (63932739479908L == 0L) {
            boolean var34 = false;
         } else {
            int var35 = var28 < 0L ? -1 : 1;
         }

         var25.setUniform("PurxiyGlass", this.c);
         var25.bindTexture("GlassDepthSampler", this.V, var5);
         var25.bindTexture("HaloSampler", this.U, var6);
         var25.draw(0, 3);
         GlassRegistry.metric("drawn");
      }
   }

   private int F(GpuDevice var1, CommandEncoder var2, GlassSupport var3, long var4) {
      long var6 = 65723156555870L;
      short var8 = 15302;
      short var9 = 23910;
      int var10 = (int)(var6 << 48 >>> 48);
      GlassRenderer4.ensureSourceClassInitialized();
      int var11 = var3.vertices();
      if (var11 == 0) {
         return 0;
      } else {
         long var12 = (long)var11 * Q;
         long var14 = 103951776585073L;
         if (103951776585073L == 0L) {
            boolean var10000 = false;
         } else {
            int var38 = var14 < 0L ? -1 : 1;
         }

         label117: {
            if (this.H != null) {
               int var17 = this.H.capacity();
               long var18 = 103951776585074L;
               if (103951776585074L == 0L) {
                  boolean var39 = false;
               } else {
                  int var40 = var18 < 0L ? -1 : 1;
               }

               if (var17 >= var12) {
                  break label117;
               }
            }

            int var31;
            for (var31 = 65536; var31 < var12; var31 <<= 1) {
               long var33 = 103951776585073L;
               if (103951776585073L == 0L) {
                  boolean var41 = false;
               } else {
                  int var42 = var33 < 0L ? -1 : 1;
               }
            }

            long var34 = 103951776585073L;
            if (103951776585073L == 0L) {
               boolean var43 = false;
            } else {
               int var44 = var34 < 0L ? -1 : 1;
            }

            long var21 = 103951776585074L;
            if (103951776585074L == 0L) {
               boolean var45 = false;
            } else {
               int var46 = var21 < 0L ? -1 : 1;
            }

            this.H = this.H == null ? MemoryUtil.memAlloc(var31) : MemoryUtil.memRealloc(this.H, var31);
         }

         GpuBuffer var29;
         label119: {
            var29 = this.v[this.o];
            if (var29 != null) {
               long var15 = 103951776585073L;
               if (103951776585073L == 0L) {
                  boolean var47 = false;
               } else {
                  int var48 = var15 < 0L ? -1 : 1;
               }

               if (var29.size() >= var12) {
                  break label119;
               }
            }

            if (var29 != null) {
               var29.close();
            }

            var29 = var1.createBuffer(ClientServices.label(() -> "Purxiy glass " + this.M + " vertices"), 40, this.H.capacity());
            this.v[this.o] = var29;
         }

         this.k = var29;
         float[] var30 = var3.data();
         int[] var16 = var3.lights();
         int[] var32 = var3.meta();
         long var35 = MemoryUtil.memAddress(this.H);

         for (int var20 = 0; var20 < var11; var20++) {
            long var37 = var35 + (long)var20 * Q;
            int var23 = var20 * 12;
            MemoryUtil.memPutFloat(var37, var30[var23]);
            MemoryUtil.memPutFloat(var37 + 4L, var30[var23 + 1]);
            MemoryUtil.memPutFloat(var37 + 8L, var30[var23 + 2]);
            MemoryUtil.memPutFloat(var37 + 12L, var30[var23 + 3]);
            MemoryUtil.memPutFloat(var37 + 16L, var30[var23 + 4]);
            int var24 = var16[var20];
            MemoryUtil.memPutShort(var37 + 20L, (short)(var24 & 65535));
            MemoryUtil.memPutShort(var37 + 22L, (short)(var24 >>> 16 & 65535));
            MemoryUtil.memPutByte(var37 + 24L, G(var8, var30[var23 + 5], (char)var9, (char)var10));
            MemoryUtil.memPutByte(var37 + 25L, G(var8, var30[var23 + 6], (char)var9, (char)var10));
            MemoryUtil.memPutByte(var37 + 26L, G(var8, var30[var23 + 7], (char)var9, (char)var10));
            long var25 = 103951776585074L;
            if (103951776585074L == 0L) {
               boolean var49 = false;
            } else {
               int var50 = var25 < 0L ? -1 : 1;
            }

            MemoryUtil.memPutByte(var37 + 27L, (byte)0);
            MemoryUtil.memPutFloat(var37 + 28L, var30[var23 + 8]);
            MemoryUtil.memPutFloat(var37 + 32L, var30[var23 + 9]);
            MemoryUtil.memPutFloat(var37 + 36L, var30[var23 + 10]);
            MemoryUtil.memPutFloat(var37 + 40L, var30[var23 + 11]);
            int var28 = var32[var20];
            MemoryUtil.memPutByte(var37 + 44L, (byte)(var28 & 0xFF));
            MemoryUtil.memPutByte(var37 + 45L, (byte)(var28 >> 8 & 0xFF));
            MemoryUtil.memPutByte(var37 + 46L, (byte)0);
            MemoryUtil.memPutByte(var37 + 47L, (byte)-1);
         }

         long var36 = 103951776585073L;
         if (103951776585073L == 0L) {
            boolean var51 = false;
         } else {
            int var52 = var36 < 0L ? -1 : 1;
         }

         var2.writeToBuffer(this.k.slice(0L, var12), this.H.clear().limit((int)var12));
         return var11;
      }
   }

   private static byte G(int var0, float var1, char var2, char var3) {
      return (byte)((int)(Math.clamp(var1, -1.0F, 1.0F) * 127.0F) & 0xFF);
   }

   private void z(CommandEncoder var1, GlassSupport4 var2, int var3, long var4, int var6) {
      GlassRenderer4.ensureSourceClassInitialized();
      float[] var7 = var2.f;
      float var8 = Math.max(0.05F, Math.max(var7[0], Math.max(var7[1], var7[2])));
      float var9 = var2.l;
      Std140Builder var10 = Std140Builder.intoBuffer(this.N.clear()).putMat4f(var2.E).putMat4f(var2.k).putMat4f(var2.X);
      float var11 = var3;
      long var12 = 69307560493655L;
      if (69307560493655L == 0L) {
         boolean var10000 = false;
      } else {
         int var31 = var12 < 0L ? -1 : 1;
      }

      Std140Builder var15 = var10.putVec4(var11, var6, var2.b, Math.max(0.75F, var6 / 1080.0F))
         .putVec4(var7[0], var7[1], var7[2], 0.85F)
         .putVec4(var7[0] / var8, var7[1] / var8, var7[2] / var8, var2.Z)
         .putVec4(var9, var2.m, 0.06F + 0.22F * var9, var2.V)
         .putVec4(var2.n.x, var2.n.y, var2.n.z, var2.Q)
         .putVec4(var2.q.x, var2.q.y, var2.q.z, var2.y ? 1.0F : 0.0F)
         .putVec4(var2.v.x, var2.v.y, var2.v.z, 1.0F)
         .putVec4(var2.N.x, var2.N.y, var2.N.z, 0.6F)
         .putVec4(var2.B.x, var2.B.y, var2.B.z, var2.U)
         .putVec4(var2.w[0], var2.w[1], var2.w[2], var2.Y)
         .putVec4(var2.I[0], var2.I[1], var2.I[2], 0.0F)
         .putVec4(var2.e[0], var2.e[1], var2.e[2], var2.o);
      float[] var16 = var2.r;

      for (int var17 = 0; var17 < 128; var17++) {
         long var18 = 69307560493654L;
         if (69307560493654L == 0L) {
            boolean var32 = false;
         } else {
            int var33 = var18 < 0L ? -1 : 1;
         }

         int var21 = var17 * 4;
         long var22 = 69307560493654L;
         if (69307560493654L == 0L) {
            boolean var34 = false;
         } else {
            int var35 = var22 < 0L ? -1 : 1;
         }

         int var24 = var2.T * 2;
         long var25 = 69307560493654L;
         if (69307560493654L == 0L) {
            boolean var36 = false;
         } else {
            int var37 = var25 < 0L ? -1 : 1;
         }

         if (var17 < var24 && var21 + 3 < var16.length) {
            var15.putVec4(var16[var21], var16[var21 + 1], var16[var21 + 2], var16[var21 + 3]);
            long var27 = 69307560493655L;
            if (69307560493655L == 0L) {
               boolean var38 = false;
            } else {
               int var39 = var27 < 0L ? -1 : 1;
            }
         } else {
            var15.putVec4(0.0F, 0.0F, 0.0F, 0.0F);
         }
      }

      long var30 = 69307560493654L;
      if (69307560493654L == 0L) {
         boolean var40 = false;
      } else {
         int var41 = var30 < 0L ? -1 : 1;
      }

      var1.writeToBuffer(this.c.slice(), var15.get());
   }

   private void L(GpuDevice var1, long var2, int var4, int var5) {
      long var6 = 36398533821784L;
      long var8 = 477628514457L;
      short var10 = 186;
      var6 = 10813830269854L;
      var6 = 1099533600889L;
      GlassRenderer4.ensureSourceClassInitialized();
      if (this.N == null) {
         for (int var11 = 0; var11 < 2; var11++) {
            long var12 = 110052726171357L;
            if (110052726171357L == 0L) {
               boolean var10000 = false;
            } else {
               int var24 = var12 < 0L ? -1 : 1;
            }

            this.j[var11] = var1.createBuffer(ClientServices.label(() -> "Purxiy glass " + this.M + " uniforms"), 136, 2432L);
         }

         long var20 = 110052726171357L;
         if (110052726171357L == 0L) {
            boolean var25 = false;
         } else {
            int var26 = var20 < 0L ? -1 : 1;
         }

         this.N = MemoryUtil.memAlloc(2432);
      }

      if (this.B != null) {
         long var21 = 110052726171358L;
         if (110052726171358L == 0L) {
            boolean var27 = false;
         } else {
            int var28 = var21 < 0L ? -1 : 1;
         }

         long var14 = 110052726171358L;
         if (110052726171358L == 0L) {
            boolean var29 = false;
         } else {
            int var30 = var14 < 0L ? -1 : 1;
         }

         if (this.g == var4) {
            long var16 = 110052726171358L;
            if (110052726171358L == 0L) {
               boolean var31 = false;
            } else {
               int var32 = var16 < 0L ? -1 : 1;
            }

            if (this.G == var5) {
               return;
            }
         }
      }

      this.T(var8, (byte)var10);
      this.B = var1.createTexture(() -> "Purxiy glass " + this.M + " scene", 5, TextureFormat.RGBA8, var4, var5, 1, 1);
      this.m = var1.createTextureView(this.B);
      this.u = var1.createTexture(() -> "Purxiy glass " + this.M + " depth", 12, TextureFormat.DEPTH32, var4, var5, 1, 1);
      this.V = var1.createTextureView(this.u);
      int var22 = Math.max(1, var4 / 4);
      int var23 = Math.max(1, var5 / 4);
      this.F = var1.createTexture(() -> "Purxiy glass " + this.M + " halo", 12, TextureFormat.RGBA8, var22, var23, 1, 1);
      this.U = var1.createTextureView(this.F);
      this.I = var1.createTexture(() -> "Purxiy glass " + this.M + " halo blur", 12, TextureFormat.RGBA8, var22, var23, 1, 1);
      this.R = var1.createTextureView(this.I);
      this.g = var4;
      this.G = var5;
   }

   private void T(long var1, byte var3) {
      GlassRenderer4.ensureSourceClassInitialized();
      long var4 = 477628514457L;
      if (477628514457L == 0L) {
         boolean var10000 = false;
      } else {
         int var7 = var4 < 0L ? -1 : 1;
      }

      if (this.m != null) {
         this.m.close();
         this.B.close();
         this.V.close();
         this.u.close();
         this.U.close();
         this.F.close();
         this.R.close();
         this.I.close();
         this.m = this.V = this.U = this.R = null;
         this.B = this.u = this.F = this.I = null;
      }
   }

   static void setTiming(boolean var0) {
      w = var0;
      W = 0.0;
      z = 0.0;
      i = 0;
      A = 0;
   }

   
   private static GpuQuery L(CommandEncoder var0, long var1) {
      GlassRenderer4.ensureSourceClassInitialized();
      if (S != null) {
         OptionalLong var3 = S.getValue();
         long var4 = 58721900800381L;
         if (58721900800381L == 0L) {
            boolean var10000 = false;
         } else {
            int var9 = var4 < 0L ? -1 : 1;
         }

         if (var3.isEmpty()) {
            return null;
         }

         S.close();
         S = null;
         z = z + var3.getAsLong() / 1000000.0;
         long var7 = 58721900800381L;
         if (58721900800381L == 0L) {
            boolean var10 = false;
         } else {
            int var11 = var7 < 0L ? -1 : 1;
         }

         if (++A == 120) {
            ClientServices.LOGGER
               .info(
                  "Purxiy glass: {} ms CPU (every layer), {} ms GPU (a layer) per frame ({} vertices on average)",
                  new Object[]{String.format(Locale.ROOT, "%.3f", W / A), String.format(Locale.ROOT, "%.3f", z / A), i / A}
               );
            W = 0.0;
            z = 0.0;
            i = 0;
            A = 0;
         }
      }

      return var0.timerQueryBegin();
   }

   public void releaseInstanceResources() {
      int var1 = 0;
      this.y.close();

      try {
         if (this.m != null) {
            this.m.close();
         }
      } catch (Exception var561) {
         var1++;
      } finally {
         this.m = null;
      }

      try {
         if (this.V != null) {
            this.V.close();
         }
      } catch (Exception var559) {
         var1++;
      } finally {
         this.V = null;
      }

      try {
         if (this.U != null) {
            this.U.close();
         }
      } catch (Exception var557) {
         var1++;
      } finally {
         this.U = null;
      }

      try {
         if (this.R != null) {
            this.R.close();
         }
      } catch (Exception var555) {
         var1++;
      } finally {
         this.R = null;
      }

      try {
         if (this.n != null) {
            this.n.close();
         }
      } catch (Exception var553) {
         var1++;
      } finally {
         this.n = null;
      }

      for (int var2 = 0; var2 < this.v.length; var2++) {
         try {
            if (this.v[var2] != null) {
               this.v[var2].close();
            }
         } catch (Exception var551) {
            var1++;
         } finally {
            this.v[var2] = null;
         }
      }

      for (int var563 = 0; var563 < this.j.length; var563++) {
         try {
            if (this.j[var563] != null) {
               this.j[var563].close();
            }
         } catch (Exception var549) {
            var1++;
         } finally {
            this.j[var563] = null;
         }
      }

      try {
         if (this.k != null) {
            this.k.close();
         }
      } catch (Exception var547) {
         var1++;
      } finally {
         this.k = null;
      }

      try {
         if (this.c != null) {
            this.c.close();
         }
      } catch (Exception var545) {
         var1++;
      } finally {
         this.c = null;
      }

      try {
         if (this.B != null) {
            this.B.close();
         }
      } catch (Exception var543) {
         var1++;
      } finally {
         this.B = null;
      }

      try {
         if (this.u != null) {
            this.u.close();
         }
      } catch (Exception var541) {
         var1++;
      } finally {
         this.u = null;
      }

      try {
         if (this.F != null) {
            this.F.close();
         }
      } catch (Exception var539) {
         var1++;
      } finally {
         this.F = null;
      }

      try {
         if (this.I != null) {
            this.I.close();
         }
      } catch (Exception var537) {
         var1++;
      } finally {
         this.I = null;
      }

      try {
         if (this.O != null) {
            this.O.close();
         }
      } catch (Exception var535) {
         var1++;
      } finally {
         this.O = null;
      }

      try {
         if (this.H != null) {
            MemoryUtil.memFree(this.H);
         }
      } catch (Exception var533) {
         var1++;
      } finally {
         this.H = null;
      }

      try {
         if (this.N != null) {
            MemoryUtil.memFree(this.N);
         }
      } catch (Exception var531) {
         var1++;
      } finally {
         this.N = null;
      }

      if (var1 != 0) {
         throw new IllegalStateException("Private visual instance GPU cleanup failed");
      }
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (S != null) {
            S.close();
         }
      } catch (Exception var5) {
         var0++;
      } finally {
         S = null;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }

private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 2659) & 32767;
      if (p[var3] == null) {
         p[var3] = h[var3] ^ var1;
      }

      return p[var3];
   }

}
