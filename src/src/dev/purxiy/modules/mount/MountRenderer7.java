// Sourced By DevFFNightmare
package dev.purxiy.modules.mount;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Builder;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.AddressMode;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat.class_5595;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_10789;
import net.minecraft.class_12137;
import net.minecraft.class_290;
import net.minecraft.class_1011.class_1012;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryUtil;

public final class MountRenderer7 {
   public static final int g = 1712;
   private static final int f = 64;
   private static final RenderPipeline V = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/garage_depth_first"))
      .withVertexShader(ClientServices.id("core/garage"))
      .withFragmentShader(ClientServices.id("core/garage"))
      .withShaderDefine("DEPTH_ONLY")
      .withUniform("GarageFrame", class_10789.field_60031)
      .withUniform("GarageMaterial", class_10789.field_60031)
      .withUniform("DynamicTransforms", class_10789.field_60031)
      .withUniform("Projection", class_10789.field_60031)
      .withSampler("BaseTex")
      .withSampler("SceneDepth")
      .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
      .withDepthWrite(true)
      .withColorWrite(false)
      .withCull(false)
      .withoutBlend()
      .withVertexFormat(class_290.field_1577, class_5596.field_27379)
      .build();
   static final RenderPipeline b = a("opaque", false, 123925693835228L, false);
   static final RenderPipeline B = r(84007011778709L, "opaque_seen", false, false, true);
   static final RenderPipeline R = a("glass", true, 123925693835228L, false);
   static final RenderPipeline W = a("studio_opaque", false, 123925693835228L, true);
   static final RenderPipeline Y = a("studio_glass", true, 123925693835228L, true);
   private static final Map<String, MountRenderer7.TypeK> Q = new LinkedHashMap<>();
   private static final Map<String, CompletableFuture<MountLoader>> P = new LinkedHashMap<>();
   private static final Map<String, Boolean> u = new LinkedHashMap<>();
   private static final int J = 3;
   private static GpuBuffer a;
   private static ByteBuffer l;
   private static GpuTexture q;
   private static GpuTexture k;
   private static GpuTextureView o;
   private static GpuTextureView S;
   private static class_12137 d;

   private static final String[] e = new String[38];
   private static final long[] h = new long[]{
      -6047263098890070798L,
      -5182586218184130984L,
      -2728196486783175439L,
      1689699979052576167L,
      -807711656300508079L,
      2366473525681743157L,
      6543121574520117267L,
      -8938327321826351876L,
      -1945131365454600301L
   };
   private static final Integer[] i = new Integer[9];
   private static final long j = 1712L;

   private static RenderPipeline a(String var0, boolean var1, long var2, boolean var4) {
      long var5 = 84007011778709L;
      return r(var5, var0, var1, var4, false);
   }

   private static RenderPipeline r(long var0, String var2, boolean var3, boolean var4, boolean var5) {
      Builder var6 = RenderPipeline.builder()
         .withLocation(ClientServices.id("pipeline/garage_" + var2))
         .withVertexShader(ClientServices.id("core/garage"))
         .withFragmentShader(ClientServices.id("core/garage"))
         .withUniform("GarageFrame", class_10789.field_60031)
         .withUniform("GarageMaterial", class_10789.field_60031)
         .withSampler("Env")
         .withSampler("BaseTex")
         .withSampler("MrTex")
         .withSampler("NormalTex")
         .withDepthTestFunction(var5 ? DepthTestFunction.EQUAL_DEPTH_TEST : DepthTestFunction.LEQUAL_DEPTH_TEST);
      long var8 = 84007011778709L;
      if (84007011778709L == 0L) {
         boolean var10000 = false;
      } else {
         int var17 = var8 < 0L ? -1 : 1;
      }

      boolean var7;
      label66: {
         if (!var3) {
            long var11 = 84007011778709L;
            if (84007011778709L == 0L) {
               boolean var18 = false;
            } else {
               int var19 = var11 < 0L ? -1 : 1;
            }

            if (!var5) {
               var7 = true;
               break label66;
            }
         }

         var7 = false;
      }

      Builder var15 = var6.withDepthWrite(var7).withCull(false).withVertexFormat(class_290.field_1577, class_5596.field_27379);
      long var9 = 84007011778709L;
      if (84007011778709L == 0L) {
         boolean var20 = false;
      } else {
         int var21 = var9 < 0L ? -1 : 1;
      }

      if (var4) {
         var15.withShaderDefine("STUDIO").withUniform("StudioCamera", class_10789.field_60031);
         long var12 = 84007011778709L;
         if (84007011778709L == 0L) {
            boolean var22 = false;
         } else {
            int var23 = var12 < 0L ? -1 : 1;
         }
      } else {
         var15.withUniform("DynamicTransforms", class_10789.field_60031)
            .withUniform("Projection", class_10789.field_60031)
            .withUniform("Fog", class_10789.field_60031)
            .withSampler("SceneDepth");
      }

      if (var3) {
         var15.withShaderDefine("GLASS_PASS").withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA);
         long var16 = 84007011778708L;
         if (84007011778708L == 0L) {
            boolean var24 = false;
         } else {
            int var25 = var16 < 0L ? -1 : 1;
         }
      } else {
         var15.withoutBlend();
      }

      return var15.build();
   }

   static List<RenderPipeline> pipelines() {
      return List.of(b, R, V, B);
   }

   private MountRenderer7() {
   }

   public static MountRenderer7.TypeK acquire(String var0) {
      MountRenderer7.TypeK var1 = Q.get(var0);
      if (var1 != null) {
         Q.remove(var0);
         Q.put(var0, var1);
         return var1;
      } else if (u.containsKey(var0)) {
         return null;
      } else {
         CompletableFuture var2 = P.computeIfAbsent(var0, MountLoader::load);
         if (!var2.isDone()) {
            return null;
         } else {
            P.remove(var0);

            try {
               var1 = new MountRenderer7.TypeK((MountLoader)var2.join());
            } catch (RuntimeException var4) {
               MountRenderer4.drawFailed();
               u.put(var0, true);
               ClientServices.LOGGER.error("Purxiy Garage could not load {}", var0, var4);
               return null;
            }

            Q.put(var0, var1);

            while (Q.size() > 3) {
               String var3 = Q.keySet().iterator().next();
               Q.remove(var3).close();
            }

            return var1;
         }
      }
   }

   private static void F(long var0) {
      MountFactory.Data7.ensureSourceClassInitialized();
      GpuDevice var2 = RenderSystem.getDevice();
      long var3 = 65534166073126L;
      if (65534166073126L == 0L) {
         boolean var10000 = false;
      } else {
         int var9 = var3 < 0L ? -1 : 1;
      }

      if (a == null) {
         a = var2.createBuffer(ClientServices.label(() -> "Purxiy vehicle frame"), 136, 1712L);
         l = MemoryUtil.memAlloc(1712);
      }

      if (q == null) {
         CommandEncoder var6 = var2.createCommandEncoder();
         q = var2.createTexture(ClientServices.label(() -> "Purxiy vehicle white"), 5, TextureFormat.RGBA8, 1, 1, 1, 1);
         k = var2.createTexture(ClientServices.label(() -> "Purxiy vehicle flat normal"), 5, TextureFormat.RGBA8, 1, 1, 1, 1);
         ByteBuffer var7 = MemoryUtil.memAlloc(4);
         var7.put((byte)-1).put((byte)-1).put((byte)-1).put((byte)-1).flip();
         var6.writeToTexture(q, var7, class_1012.field_4997, 0, 0, 0, 0, 1, 1);
         var7.clear();
         var7.put((byte)-128).put((byte)-128).put((byte)-1).put((byte)-1).flip();
         var6.writeToTexture(k, var7, class_1012.field_4997, 0, 0, 0, 0, 1, 1);
         MemoryUtil.memFree(var7);
         o = var2.createTextureView(q);
         S = var2.createTextureView(k);
      }

      if (d == null) {
         int var8 = Math.max(1, Math.min(8, var2.getMaxSupportedAnisotropy()));
         d = var2.createSampler(AddressMode.REPEAT, AddressMode.REPEAT, FilterMode.LINEAR, FilterMode.LINEAR, var8, OptionalDouble.empty());
      }
   }

   private static void K(CommandEncoder var0, Matrix4fc[] var1, long var2, MountRenderer7.TypeV var4) {
      MountFactory.Data7.ensureSourceClassInitialized();
      Std140Builder var5 = Std140Builder.intoBuffer(l.clear());

      for (int var6 = 0; var6 < 24; var6++) {
         var5.putMat4f(var1[var6]);
      }

      var5.putVec4(var4.T.x, var4.T.y, var4.T.z, var4.z);
      var5.putVec4(var4.V.x, var4.V.y, var4.V.z, var4.u);
      var5.putVec4(var4.b.x, var4.b.y, var4.b.z, var4.B.x);
      long var9 = 120732761882179L;
      if (120732761882179L == 0L) {
         boolean var10000 = false;
      } else {
         int var10 = var9 < 0L ? -1 : 1;
      }

      var5.putVec4(var4.Y.x, var4.Y.y, var4.Y.z, var4.B.y);
      var5.putVec4(var4.N, var4.l, var4.y, var4.E);
      var5.putVec4(var4.h.x, var4.h.y, var4.h.z, var4.Q);
      var5.putVec4(var4.e.x, var4.e.y, var4.e.z, var4.B.z);
      var5.putVec4(var4.S, var4.q, var4.j, var4.m);
      var5.putVec4(var4.C, var4.a, var4.f, var4.F);
      var5.putVec4(var4.W, var4.c, var4.w, var4.v);
      var5.putVec4(var4.D, var4.t, var4.I, var4.i);
      var0.writeToBuffer(a.slice(), var5.get());
   }

   public static void draw(
      MountRenderer7.TypeK var0,
      Matrix4fc[] var1,
      MountRenderer7.TypeV var2,
      MountUpdater7 var3,
      GpuTextureView var4,
      GpuTextureView var5,
      GpuTextureView var6,
      GpuBufferSlice var7,
      GpuBuffer var8
   ) {
      draw(var0, var1, var2, var3, var4, var5, var6, var7, var8, false);
   }

   public static void draw(
      MountRenderer7.TypeK var0,
      Matrix4fc[] var1,
      MountRenderer7.TypeV var2,
      MountUpdater7 var3,
      GpuTextureView var4,
      GpuTextureView var5,
      GpuTextureView var6,
      GpuBufferSlice var7,
      GpuBuffer var8,
      boolean var9
   ) {
      long var10 = 92560870330613L;
      long var12 = var10 ^ 63639749504694L;
      long var14 = var10 ^ 122824748150738L;
      long var16 = var10 ^ 22859256193513L;
      long var18 = var10 ^ 19421955505334L;
      int var20 = (int)((var10 ^ 19421955505334L) >>> 48);
      int var21 = (int)((var10 ^ 19421955505334L) << 16 >>> 32);
      int var22 = (int)(var18 << 48 >>> 48);
      F(var14);
      GpuDevice var23 = RenderSystem.getDevice();
      boolean var24 = var9 && var8 == null && var23.precompilePipeline(V).isValid() && var23.precompilePipeline(B).isValid();
      RenderPipeline var25 = var8 != null ? W : (var24 ? B : b);
      RenderPipeline var26 = var8 != null ? Y : R;
      if (var23.precompilePipeline(var25).isValid() && var23.precompilePipeline(var26).isValid()) {
         CommandEncoder var27 = var23.createCommandEncoder();
         K(var27, var1, var12, var2);
         class_12137 var28 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);

         try (RenderPass var29 = var27.createRenderPass(() -> "Purxiy vehicle", var4, OptionalInt.of(0), var5, OptionalDouble.of(1.0))) {
            var29.setVertexBuffer(0, var0.q);
            var29.setIndexBuffer(var0.i, class_5595.field_27373);
            if (var24) {
               var29.setPipeline(V);
               RenderSystem.bindDefaultUniforms(var29);
               var29.setUniform("DynamicTransforms", var7);
               var29.bindTexture("SceneDepth", var6, var28);
               var29.setUniform("GarageFrame", a);

               for (MountLoader.Data2 var31 : var0.f) {
                  MountLoader.Data var32 = var0.g.B.get(var31.material());
                  var29.setUniform("GarageMaterial", var0.j((char)var20, var21, (short)var22, var31.material()));
                  var29.bindTexture("BaseTex", var0.Q(var16, var32.texBase(), o), d);
                  var29.drawIndexed(var31.baseVertex(), var31.firstIndex(), var31.indexCount(), 1);
                  MountRenderer4.drawn(false);
               }
            }

            for (int var37 = 0; var37 < 2; var37++) {
               List var38 = var37 == 0 ? var0.f : var0.I;
               if (!var38.isEmpty()) {
                  var29.setPipeline(var37 == 0 ? var25 : var26);
                  if (var8 == null) {
                     RenderSystem.bindDefaultUniforms(var29);
                     var29.setUniform("DynamicTransforms", var7);
                     var29.bindTexture("SceneDepth", var6, var28);
                  } else {
                     var29.setUniform("StudioCamera", var8);
                  }

                  var29.setUniform("GarageFrame", a);
                  var29.bindTexture("Env", var3.view(), var3.sampler());

                  for (MountLoader.Data2 var33 : (Iterable<MountLoader.Data2>)(Object)(var38)) {
                     MountLoader.Data var34 = var0.g.B.get(var33.material());
                     var29.setUniform("GarageMaterial", var0.j((char)var20, var21, (short)var22, var33.material()));
                     var29.bindTexture("BaseTex", var0.Q(var16, var34.texBase(), o), d);
                     var29.bindTexture("MrTex", var0.Q(var16, var34.texMr(), o), d);
                     var29.bindTexture("NormalTex", var0.Q(var16, var34.texNormal(), S), d);
                     var29.drawIndexed(var33.baseVertex(), var33.firstIndex(), var33.indexCount(), 1);
                     MountRenderer4.drawn(false);
                  }
               }
            }
         }
      }
   }

   public static void close() {
      for (MountRenderer7.TypeK var1 : Q.values()) {
         var1.close();
      }

      Q.clear();

      for (CompletableFuture var3 : P.values()) {
         if (var3.isDone() && !var3.isCompletedExceptionally() && !var3.isCancelled()) {
            ((MountLoader)var3.join()).releaseCpu();
         }
      }

      P.clear();
      u.clear();
      if (a != null) {
         a.close();
      }

      if (l != null) {
         MemoryUtil.memFree(l);
      }

      a = null;
      l = null;
      if (o != null) {
         o.close();
      }

      if (S != null) {
         S.close();
      }

      if (q != null) {
         q.close();
      }

      if (k != null) {
         k.close();
      }

      k = null;
      q = null;
      S = null;
      o = null;
      if (d != null) {
         d.close();
      }

      d = null;
   }

   public static Vector3f linear(int var0, Vector3f var1) {
      return var1.set(
         (float)Math.pow((var0 >> 16 & 0xFF) / 255.0F, 2.2), (float)Math.pow((var0 >> 8 & 0xFF) / 255.0F, 2.2), (float)Math.pow((var0 & 0xFF) / 255.0F, 2.2)
      );
   }

   public static Matrix4f[] newParts() {
      Matrix4f[] var0 = new Matrix4f[24];

      for (int var1 = 0; var1 < var0.length; var1++) {
         var0[var1] = new Matrix4f();
      }

      return var0;
   }

   public static Vector4f vec4(float var0, float var1, float var2, float var3) {
      return new Vector4f(var0, var1, var2, var3);
   }

   public static void releasePipelines() {
      ClientCache.release(V);
      ClientCache.release(b);
      ClientCache.release(B);
      ClientCache.release(R);
      ClientCache.release(W);
      ClientCache.release(Y);
   }

public static final class TypeK {
      public final MountLoader g;
      private final GpuBuffer q;
      private final GpuBuffer i;
      private final GpuBuffer K;
      private final GpuTexture[] y;
      private final GpuTextureView[] j;
      private final int m;
      private final List<MountLoader.Data2> f = new ArrayList<>();
      private final List<MountLoader.Data2> I = new ArrayList<>();

      private static final String[] b = new String[4];
      private static final long c = 64L;

      TypeK(MountLoader var1) {
         this.g = var1;
         GpuDevice var2 = RenderSystem.getDevice();
         this.q = var2.createBuffer(ClientServices.label(() -> "Purxiy vehicle vertices"), 32, var1.X);
         this.i = var2.createBuffer(ClientServices.label(() -> "Purxiy vehicle indices"), 64, var1.I);
         CommandEncoder var3 = var2.createCommandEncoder();
         this.y = new GpuTexture[var1.L.size()];
         this.j = new GpuTextureView[var1.L.size()];

         for (int var4 = 0; var4 < this.y.length; var4++) {
            MountLoader.TypeT var5 = var1.L.get(var4);
            this.y[var4] = var2.createTexture(ClientServices.label(() -> "Purxiy vehicle texture"), 5, TextureFormat.RGBA8, var5.r, var5.A, 1, var5.O.length);
            int var6 = var5.r;
            int var7 = var5.A;

            for (int var8 = 0; var8 < var5.O.length; var8++) {
               var3.writeToTexture(this.y[var4], var5.O[var8], class_1012.field_4997, var8, 0, 0, 0, var6, var7);
               var6 = Math.max(1, var6 >> 1);
               var7 = Math.max(1, var7 >> 1);
            }

            this.j[var4] = var2.createTextureView(this.y[var4]);
         }

         int var12 = Math.max(1, var2.getUniformOffsetAlignment());
         this.m = (64 + var12 - 1) / var12 * var12;
         ByteBuffer var13 = MemoryUtil.memAlloc(this.m * var1.B.size());

         try {
            for (int var14 = 0; var14 < var1.B.size(); var14++) {
               MountLoader.Data var16 = var1.B.get(var14);
               var13.position(var14 * this.m);
               var13.limit(var13.position() + 64);
               Std140Builder.intoBuffer(var13.slice().order(ByteOrder.nativeOrder()))
                  .putVec4(var16.base()[0], var16.base()[1], var16.base()[2], var16.base().length > 3 ? var16.base()[3] : 1.0F)
                  .putVec4(var16.metal(), var16.rough(), var16.coat(), var16.normalScale())
                  .putVec4(var16.emissive()[0], var16.emissive()[1], var16.emissive()[2], var16.gain())
                  .putVec4(var16.cls(), var16.tint(), var16.alpha(), var16.cutoff());
               var13.limit(var13.capacity());
            }

            var13.position(0);
            this.K = var2.createBuffer(ClientServices.label(() -> "Purxiy vehicle materials"), 128, var13);
         } finally {
            MemoryUtil.memFree(var13);
         }

         for (MountLoader.Data2 var17 : var1.P) {
            (var1.B.get(var17.material()).alpha() == 2 ? this.I : this.f).add(var17);
         }

         var1.releaseCpu();
      }

      void close() {
         this.q.close();
         this.i.close();
         this.K.close();

         for (GpuTextureView var4 : this.j) {
            var4.close();
         }

         for (GpuTexture var8 : this.y) {
            var8.close();
         }
      }

      private GpuBufferSlice j(char var1, int var2, short var3, int var4) {
         return this.K.slice(var4 * this.m, 64L);
      }

      private GpuTextureView Q(long var1, int var3, GpuTextureView var4) {
         return var3 >= 0 ? this.j[var3] : var4;
      }

      GpuTextureView groundView() {
         return this.j[this.g.k];
      }

}

   public static final class TypeV {
      public final Vector3f T = new Vector3f(0.0F, 1.0F, 0.0F);
      public float z;
      public float N = 1.0F;
      public float l;
      public float y;
      public float E;
      public final Vector3f V = new Vector3f(1.0F);
      public final Vector3f b = new Vector3f(0.3F, 0.45F, 0.8F);
      public final Vector3f Y = new Vector3f(0.7F, 0.8F, 0.95F);
      public final Vector3f h = new Vector3f(0.5F);
      public final Vector3f e = new Vector3f(0.42F, 0.3F, 0.78F);
      public final Vector3f B = new Vector3f(0.75F, 0.56F, 0.97F);
      public float Q = 0.5F;
      public float S;
      public float q;
      public float j;
      public float m;
      public float C;
      public float a;
      public float f;
      public float F;
      public float W = 1.0F;
      public float c = 1.0F;
      public float w = 1.0F;
      public float D;
      public float t;
      public float I;
      public float i;
      public float u = 1.0F;
      public float v;
   }
}
