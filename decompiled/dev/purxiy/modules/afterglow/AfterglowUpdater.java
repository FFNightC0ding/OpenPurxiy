package dev.purxiy.modules.afterglow;

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
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.render.pipeline.SkyPipelines;
import dev.purxiy.render.pipeline.SkyVertexFormat;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.OptionalLong;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_5819;
import net.minecraft.class_9909;
import net.minecraft.class_9916;
import net.minecraft.class_9925;
import net.minecraft.class_9960;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryUtil;

final class AfterglowUpdater {
   private static final int e = 2400;
   private static final float T = 0.2F;
   private static final float U = 0.9F;
   private static final int a = 48;
   private static final int z = SkyVertexFormat.AMBIENCE_VERTEX_SIZE;
   private static final int Z = z * 4;
   private final class_5819 u = class_5819.method_43047();
   int V;
   private double[] P = new double[64];
   private double[] s = new double[64];
   private double[] W = new double[64];
   private float[] I = new float[64];
   private float[] v = new float[64];
   private float[] b = new float[64];
   private float[] h = new float[64];
   private float[] d = new float[64];
   private float[] Q = new float[64];
   private float[] L = new float[64];
   private float[] q = new float[64];
   private float[] k = new float[64];
   private float[] c = new float[64];
   private float[] A = new float[64];
   private float[] w = new float[64];
   private float[] B = new float[64];
   private float[] f = new float[64];
   private float[] j = new float[64];
   private float[] g = new float[64];
   private float[] H = new float[64];
   private float[] R = new float[64];
   private float[] n = new float[64];
   private AfterglowCatalog[] S = new AfterglowCatalog[64];
   private long[] t = new long[256];
   private ByteBuffer Y;
   private int N;
   private GpuBuffer G;
   private GpuBuffer J;
   private ByteBuffer M;
   private String K;
   private boolean y;
   private GpuQuery C;
   private double i;
   private int F;
   private static final String[] m = new String[13];
   private static final long[] r = new long[]{
      3101710184998666950L,
      2763186465275890826L,
      7769062168358667673L,
      7587235340288932080L,
      2809561593014278164L,
      7937123408331570315L,
      8386524653798433529L,
      6065814225983875390L,
      1850112887706676100L,
      9145943866515631572L,
      2152662631956877885L,
      4709306115053050868L,
      1585251927385661102L,
      7400508799517816614L,
      1960828406150853247L,
      7416870102675814519L,
      8916129834606662129L,
      2046607561167570097L,
      3898858064563930582L
   };
   private static final Long[] x = new Long[19];

   void clear() {
      this.V = 0;
   }

   void emit(AfterglowCatalog var1, double var2, double var4, double var6, float var8, float var9, float var10, float var11) {
      long var12 = 116334771831716L;
      long var14 = var12 ^ 282617045688L;
      if (this.V < 2400) {
         this.U(var14, this.V + 1);
         class_5819 var16 = this.u;
         int var17 = this.V++;
         float var18 = class_3532.method_16439(var16.method_43057(), var1.S, var1.z) * var11;
         float var19 = class_3532.method_16439(var16.method_43057(), var1.t, var1.P) * var11;
         this.P[var17] = var2 + var8 * 0.02;
         this.s[var17] = var4 + var9 * 0.02;
         this.W[var17] = var6 + var10 * 0.02;
         this.I[var17] = var8 * var18 + (var16.method_43057() - 0.5F) * 0.12F;
         this.v[var17] = var9 * var18 * 0.5F + var19;
         this.b[var17] = var10 * var18 + (var16.method_43057() - 0.5F) * 0.12F;
         this.h[var17] = 0.0F;
         this.d[var17] = class_3532.method_16439(var16.method_43057(), var1.X, var1.U);
         this.Q[var17] = class_3532.method_16439(var16.method_43057() * var16.method_43057(), var1.V, var1.C);
         float var20 = var16.method_43057();
         this.L[var17] = var20 < 0.72F ? var16.method_43057() * 0.6F : 0.6F + var16.method_43057() * 0.4F;
         this.q[var17] = var16.method_43057();
         this.k[var17] = var16.method_43057() * (float) (Math.PI * 2);
         this.c[var17] = 1.5F + var16.method_43057() * 2.5F;
         this.A[var17] = var16.method_43057() * (float) (Math.PI * 2);
         this.w[var17] = class_3532.method_16439(var16.method_43057(), var1.c, var1.Y) * (var16.method_43056() ? 1.0F : -1.0F);
         this.S[var17] = var1;
         if (var1.m == 2) {
            float var21 = var16.method_43057() * (float) (Math.PI * 2);
            float var22 = (float)Math.acos(2.0F * var16.method_43057() - 1.0F);
            float var23 = var16.method_43057() * (float) (Math.PI * 2);
            float var24 = class_3532.method_15374(var23 * 0.5F);
            this.B[var17] = class_3532.method_15374(var22) * class_3532.method_15362(var21) * var24;
            this.f[var17] = class_3532.method_15362(var22) * var24;
            this.j[var17] = class_3532.method_15374(var22) * class_3532.method_15374(var21) * var24;
            this.g[var17] = class_3532.method_15362(var23 * 0.5F);
            float var25 = var16.method_43057() * (float) (Math.PI * 2);
            float var26 = (float)Math.acos(2.0F * var16.method_43057() - 1.0F);
            float var27 = this.w[var17];
            this.H[var17] = class_3532.method_15374(var26) * class_3532.method_15362(var25) * var27;
            this.R[var17] = class_3532.method_15362(var26) * var27;
            this.n[var17] = class_3532.method_15374(var26) * class_3532.method_15374(var25) * var27;
         } else {
            this.B[var17] = this.f[var17] = this.j[var17] = 0.0F;
            this.g[var17] = 1.0F;
         }
      }
   }

   void update(float var1, float var2) {
      long var3 = 1028005498687L;
      long var5 = var3 ^ 5268392586238L;
      if (!(var1 <= 0.0F)) {
         for (int var7 = 0; var7 < this.V; var7++) {
            this.h[var7] = this.h[var7] + var1;
            if (this.h[var7] >= this.d[var7]) {
               this.I(var5, var7--);
            } else {
               AfterglowCatalog var8 = this.S[var7];
               float var9 = (float)Math.exp(-var8.e * var1);
               float var10 = this.k[var7] + var2 * this.c[var7] * 0.6F;
               this.I[var7] = this.I[var7] * var9 + class_3532.method_15362(var10) * var8.D * var1;
               this.v[var7] = this.v[var7] * var9 + var8.n * var1;
               this.b[var7] = this.b[var7] * var9 + class_3532.method_15374(var10 * 1.3F + 0.7F) * var8.D * var1;
               this.P[var7] = this.P[var7] + this.I[var7] * var1;
               this.s[var7] = this.s[var7] + this.v[var7] * var1;
               this.W[var7] = this.W[var7] + this.b[var7] * var1;
               this.A[var7] = this.A[var7] + this.w[var7] * var1;
               if (var8.m == 2) {
                  this.i(var7, var1);
               }
            }
         }
      }
   }

   private void i(int var1, float var2) {
      float var3 = this.H[var1] * var2 * 0.5F;
      float var4 = this.R[var1] * var2 * 0.5F;
      float var5 = this.n[var1] * var2 * 0.5F;
      float var6 = this.B[var1];
      float var7 = this.f[var1];
      float var8 = this.j[var1];
      float var9 = this.g[var1];
      float var10 = var6 + var3 * var9 + var4 * var8 - var5 * var7;
      float var11 = var7 + var4 * var9 + var5 * var6 - var3 * var8;
      float var12 = var8 + var5 * var9 + var3 * var7 - var4 * var6;
      float var13 = var9 - var3 * var6 - var4 * var7 - var5 * var8;
      float var14 = class_3532.method_48119(var10 * var10 + var11 * var11 + var12 * var12 + var13 * var13);
      this.B[var1] = var10 * var14;
      this.f[var1] = var11 * var14;
      this.j[var1] = var12 * var14;
      this.g[var1] = var13 * var14;
   }

   private void I(long var1, int var3) {
      AfterglowRenderer.ensureSourceClassInitialized();
      int var4 = --this.V;
      long var5 = 4561209926848L;
      int var7 = var5 < 0L ? -1 : 1;
      if (var3 != var4) {
         this.P[var3] = this.P[var4];
         this.s[var3] = this.s[var4];
         this.W[var3] = this.W[var4];
         this.I[var3] = this.I[var4];
         this.v[var3] = this.v[var4];
         this.b[var3] = this.b[var4];
         this.h[var3] = this.h[var4];
         this.d[var3] = this.d[var4];
         this.Q[var3] = this.Q[var4];
         this.L[var3] = this.L[var4];
         this.q[var3] = this.q[var4];
         this.k[var3] = this.k[var4];
         this.c[var3] = this.c[var4];
         this.A[var3] = this.A[var4];
         this.w[var3] = this.w[var4];
         this.B[var3] = this.B[var4];
         this.f[var3] = this.f[var4];
         this.j[var3] = this.j[var4];
         this.g[var3] = this.g[var4];
         this.H[var3] = this.H[var4];
         this.R[var3] = this.R[var4];
         this.n[var3] = this.n[var4];
         this.S[var3] = this.S[var4];
      }
   }

   private void U(long var1, int var3) {
      AfterglowRenderer.ensureSourceClassInitialized();
      long var4 = 116065074536731L;
      int var7 = var4 < 0L ? -1 : 1;
      var7 = this.P.length;
      long var8 = 116065074536732L;
      int var10 = var8 < 0L ? -1 : 1;
      if (var3 > var7) {
         var10 = Math.min(2400, Math.max(var3, this.P.length * 2));
         this.P = Arrays.copyOf(this.P, var10);
         this.s = Arrays.copyOf(this.s, var10);
         this.W = Arrays.copyOf(this.W, var10);
         this.I = Arrays.copyOf(this.I, var10);
         this.v = Arrays.copyOf(this.v, var10);
         this.b = Arrays.copyOf(this.b, var10);
         this.h = Arrays.copyOf(this.h, var10);
         this.d = Arrays.copyOf(this.d, var10);
         this.Q = Arrays.copyOf(this.Q, var10);
         this.L = Arrays.copyOf(this.L, var10);
         this.q = Arrays.copyOf(this.q, var10);
         this.k = Arrays.copyOf(this.k, var10);
         this.c = Arrays.copyOf(this.c, var10);
         this.A = Arrays.copyOf(this.A, var10);
         this.w = Arrays.copyOf(this.w, var10);
         this.B = Arrays.copyOf(this.B, var10);
         this.f = Arrays.copyOf(this.f, var10);
         this.j = Arrays.copyOf(this.j, var10);
         this.g = Arrays.copyOf(this.g, var10);
         this.H = Arrays.copyOf(this.H, var10);
         this.R = Arrays.copyOf(this.R, var10);
         this.n = Arrays.copyOf(this.n, var10);
         this.S = Arrays.copyOf(this.S, var10);
      }
   }

   void addPass(class_9909 var1, class_9960 var2, GpuBufferSlice var3, float[] var4, float var5, float var6) {
      long var7 = 36148653872915L;
      long var9 = var7 ^ 51336736554704L;
      class_4184 var11 = class_310.method_1551().field_1773.method_19418();
      this.N = this.V > 0 ? this.U(var11, var4, var9, var6) : 0;
      if (this.N != 0 && RenderSystem.getDevice().precompilePipeline(SkyPipelines.AMBIENCE).isValid()) {
         class_9916 var12 = var1.method_61911("purxiy_afterglow");
         class_9925 var13;
         if (var2.field_53094 != null) {
            var2.field_53094 = var12.method_61933(var2.field_53094);
            var12.method_61928(var2.field_53091);
            var13 = var2.field_53094;
         } else {
            var2.field_53091 = var12.method_61933(var2.field_53091);
            var13 = var2.field_53091;
         }

         class_9925 var14 = var2.field_53091;
         int var15 = this.N;
         var12.method_61929(() -> {
            long var6x = 132798992448440L;
            RenderSystem.setShaderFog(var3);
            this.n((class_276)var13.get(), (class_276)var14.get(), var15, var6x, var5);
         });
      }
   }

   private int U(class_4184 var1, float[] var2, long var3, float var5) {
      long var6 = 35751434599794L;
      long var8 = 16859927699754L;
      AfterglowRenderer.ensureSourceClassInitialized();
      class_243 var10 = var1.method_71156();
      Vector3fc var11 = var1.method_19335();
      float var12 = var11.x();
      float var13 = var11.y();
      float var14 = var11.z();
      if (this.t.length < this.V) {
         this.t = new long[Math.max(this.V, this.t.length * 2)];
      }

      int var15 = 0;

      for (int var16 = 0; var16 < this.V; var16++) {
         long var17 = 15737840101826L;
         int var20 = var17 < 0L ? -1 : 1;
         float var20x = (float)(this.P[var16] - var10.field_1352);
         float var21 = (float)(this.s[var16] - var10.field_1351);
         float var22 = (float)(this.W[var16] - var10.field_1350);
         float var23 = var20x * var12 + var21 * var13 + var22 * var14;
         float var24;
         int var25 = (var24 = var23 - ((float)Math.sqrt(var20x * var20x + var21 * var21 + var22 * var22) * 0.17F - 0.6F)) == 0.0F ? 0 : (var24 < 0.0F ? -1 : 1);
         long var26 = 15737840101827L;
         int var28 = var26 < 0L ? -1 : 1;
         if (var25 < 0) {
            long var28x = 15737840101826L;
            int var29 = var28x < 0L ? -1 : 1;
         } else {
            this.t[var15++] = (long)Float.floatToRawIntBits(Math.max(var23, 0.0F)) << 32 | var16;
         }
      }

      long var30 = 15737840101826L;
      int var19 = var30 < 0L ? -1 : 1;
      if (var15 == 0) {
         return 0;
      } else {
         Arrays.sort(this.t, 0, var15);
         this.x(var15, var6);
         long var19x = MemoryUtil.memAddress(this.Y);

         for (int var31 = 0; var31 < var15; var31++) {
            this.E(var19x + (long)var31 * Z, (int)this.t[var15 - 1 - var31], var10, var2, var5, var8);
         }

         long var32 = 15737840101827L;
         int var35 = var32 < 0L ? -1 : 1;
         this.Y.clear().limit(var15 * Z);
         return var15;
      }
   }

   private void E(long var1, int var3, class_243 var4, float[] var5, float var6, long var7) {
      long var9 = 63835945860923L;
      AfterglowRenderer.ensureSourceClassInitialized();
      AfterglowCatalog var11 = this.S[var3];
      float var12 = this.h[var3] / this.d[var3];
      float var13 = AfterglowRegistry.smoothstep(0.0F, 0.07F, this.h[var3]) * (1.0F - AfterglowRegistry.smoothstep(0.35F, 1.0F, var12));
      float var14 = this.L[var3];
      int var15 = var14 < 0.6F ? 0 : 3;
      long var16 = 16859927699754L;
      int var19 = var16 < 0L ? -1 : 1;
      float var19x = var14 < 0.6F ? var14 / 0.6F : (var14 - 0.6F) / 0.4F;
      float var20 = var5[var15] + (var5[var15 + 3] - var5[var15]) * var19x;
      float var21 = var5[var15 + 1] + (var5[var15 + 4] - var5[var15 + 1]) * var19x;
      float var22 = var5[var15 + 2] + (var5[var15 + 5] - var5[var15 + 2]) * var19x;
      float var23 = var11.M + (1.0F - var11.M) * 0.3F * (1.0F - AfterglowRegistry.smoothstep(0.0F, 0.3F, this.h[var3]));
      float var24 = var20 + (1.0F - var20) * var23;
      float var25 = var21 + (1.0F - var21) * var23;
      float var26 = var22 + (1.0F - var22) * var23;
      float var27 = this.k[var3] + var6 * this.c[var3];
      float var28 = 1.0F;
      float var29 = 0.0F;
      long var30 = 16859927699753L;
      int var46 = var30 < 0L ? -1 : 1;
      switch (var11.m) {
         case 0:
            var28 = 1.0F - 0.3F * (0.5F + 0.5F * class_3532.method_15374(var27));
            long var33 = 16859927699754L;
            int var47 = var33 < 0L ? -1 : 1;
            break;
         case 1:
            long var35 = 16859927699754L;
            int var35x = var35 < 0L ? -1 : 1;
            var28 = 0.75F;
            var29 = 1.3F * (float)Math.pow(0.5F + 0.5F * class_3532.method_15374(var27 * 1.6F), 10.0);
            break;
         case 2:
            var29 = 0.58F + 0.16F * this.q[var3];
      }

      float var46 = this.Q[var3] * (1.0F - 0.35F * var12);
      long var34 = 16859927699754L;
      int var34x = var34 < 0L ? -1 : 1;
      boolean var36x = var11.m == 2;
      MemoryUtil.memPutFloat(var1, (float)(this.P[var3] - var4.field_1352));
      long var37 = var1 + 4L;
      double var39 = this.s[var3];
      long var41 = 16859927699753L;
      int var41x = var41 < 0L ? -1 : 1;
      MemoryUtil.memPutFloat(var37, (float)(var39 - var4.field_1351));
      MemoryUtil.memPutFloat(var1 + 8L, (float)(this.W[var3] - var4.field_1350));
      MemoryUtil.memPutFloat(var1 + 12L, var46);
      MemoryUtil.memPutFloat(var1 + 16L, var11.m);
      var37 = var1 + 20L;
      long var43x = 16859927699754L;
      int var43x = var43x < 0L ? -1 : 1;
      MemoryUtil.memPutFloat(var37, this.A[var3]);
      MemoryUtil.memPutFloat(var1 + 24L, this.q[var3]);
      MemoryUtil.memPutFloat(var1 + 28L, var29);
      MemoryUtil.memPutFloat(var1 + 32L, var36x ? this.B[var3] : 0.0F);
      MemoryUtil.memPutFloat(var1 + 36L, var36x ? this.f[var3] : 0.0F);
      MemoryUtil.memPutFloat(var1 + 40L, var36x ? this.j[var3] : 0.0F);
      MemoryUtil.memPutFloat(var1 + 44L, var36x ? this.g[var3] : 1.0F);
      J(var1 + 48L, var9, var24, var25, var26, var13 * var11.G * Math.min(var28, 1.0F));
      J(var1 + 52L, var9, var20, var21, var22, var13 * var11.T * var28);
      MemoryUtil.memCopy(var1, var1 + z, z);
      MemoryUtil.memCopy(var1, var1 + z * 2L, z);
      MemoryUtil.memCopy(var1, var1 + z * 3L, z);
   }

   private static void J(long var0, long var2, float var4, float var5, float var6, float var7) {
      MemoryUtil.memPutByte(var0, (byte)t(var4));
      MemoryUtil.memPutByte(var0 + 1L, (byte)t(var5));
      MemoryUtil.memPutByte(var0 + 2L, (byte)t(var6));
      MemoryUtil.memPutByte(var0 + 3L, (byte)t(var7));
   }

   private static int t(float var0) {
      return Math.round(Math.clamp(var0, 0.0F, 1.0F) * 255.0F);
   }

   private void x(int var1, long var2) {
      AfterglowRenderer.ensureSourceClassInitialized();
      int var4 = var1 * Z;
      if (this.Y != null) {
         int var5 = this.Y.capacity();
         long var6 = 35751434599794L;
         int var8 = var6 < 0L ? -1 : 1;
         if (var5 >= var4) {
            return;
         }
      }

      int var9 = Math.max(var4, this.Y == null ? 64 * Z : this.Y.capacity() * 2);
      long var10 = 35751434599793L;
      int var11 = var10 < 0L ? -1 : 1;
      this.Y = this.Y == null ? MemoryUtil.memAlloc(var9) : MemoryUtil.memRealloc(this.Y, var9);
   }

   private void n(class_276 var1, class_276 var2, int var3, long var4, float var6) {
      long var7 = 41541732366792L;
      long var9 = 15345828410761L;
      byte var11 = 0;
      int var12 = 234158758;
      int var13 = (int)(var9 << 48 >>> 48);
      AfterglowRenderer.ensureSourceClassInitialized();

      try {
         GpuTextureView var14 = var1.method_71639();
         GpuTextureView var40 = var2.method_71640();
         if (var14 == null || var40 == null) {
            return;
         }

         GpuDevice var41 = RenderSystem.getDevice();
         CommandEncoder var17 = var41.createCommandEncoder();
         this.w(var41, var7, var3 * Z);
         var17.writeToBuffer(this.G.slice(0L, (long)var3 * Z), this.Y);
         Std140Builder var18 = Std140Builder.intoBuffer(this.M.clear())
            .putVec4(0.05F, class_310.method_1551().field_1773.method_32796(), 0.2F, 0.0F)
            .putVec4(var14.getWidth(0), var14.getHeight(0), 0.9F, 0.0F)
            .putVec4(0.85F * var6, 0.4F, 0.6F, 0.0F);
         var17.writeToBuffer(this.J.slice(), var18.get());
         GpuBufferSlice var19 = RenderSystem.getDynamicUniforms()
            .method_71106(RenderSystem.getModelViewMatrix(), new Vector4f(1.0F), new Vector3f(), new Matrix4f());
         class_5590 var20 = RenderSystem.getSequentialBuffer(class_5596.field_27382);
         GpuBuffer var21 = var20.method_68274(var3 * 6);
         GpuQuery var22 = this.y ? this.C(var17, var11, var12, (char)var13) : null;
         boolean var23 = false;

         try {
            var23 = true;
            long var24 = 132798992448439L;
            int var27 = var24 < 0L ? -1 : 1;
            RenderPass var27x = var17.createRenderPass(() -> "Purxiy afterglow", var14, OptionalInt.empty());

            try {
               var27x.setPipeline(SkyPipelines.AMBIENCE);
               RenderSystem.bindDefaultUniforms(var27x);
               long var28 = 132798992448439L;
               int var26x = var28 < 0L ? -1 : 1;
               var27x.setUniform("DynamicTransforms", var19);
               var27x.setUniform("PurxiyAmbience", this.J);
               var27x.bindTexture("DepthSampler", var40, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
               var26x = 1396360185;
               var27x.setVertexBuffer(0, this.G);
               var27x.setIndexBuffer(var21, var20.method_31924());
               var27x.drawIndexed(0, 0, var3 * 6, 1);
               AfterglowRegistry.metric("drawn");
            } catch (Throwable var36) {
               if (var27x != null) {
                  try {
                     var27x.close();
                  } catch (Throwable var35) {
                     var36.addSuppressed(var35);
                  }
               }

               throw var36;
            }

            if (var27x != null) {
               var27x.close();
            }
         } finally {
            if (var23 && var22 != null) {
               var17.timerQueryEnd(var22);
               this.C = var22;
            }
         }

         if (var22 != null) {
            var17.timerQueryEnd(var22);
            this.C = var22;
         }
      } catch (Throwable var38) {
         AfterglowRegistry.metric("draw_failures");
         String var15 = var38.toString();
         long var16 = 132798992448439L;
         int var18 = var16 < 0L ? -1 : 1;
         if (!var15.equals(this.K)) {
            this.K = var15;
            ClientServices.LOGGER.error("Purxiy afterglow failed to draw its light", var38);
         }
      }
   }

   private void w(GpuDevice var1, long var2, int var4) {
      AfterglowRenderer.ensureSourceClassInitialized();
      if (this.J == null) {
         this.J = var1.createBuffer(ClientServices.label(() -> "Purxiy afterglow uniforms"), 136, 48L);
         this.M = MemoryUtil.memAlloc(48);
      }

      if (this.G != null) {
         long var5 = this.G.size();
         long var7 = 41541732366792L;
         int var19 = var7 < 0L ? -1 : 1;
         long var10;
         int var12 = (var10 = var5 - var4) == 0L ? 0 : (var10 < 0L ? -1 : 1);
         long var13 = 41541732366791L;
         int var14 = var13 < 0L ? -1 : 1;
         if (var12 >= 0) {
            return;
         }
      }

      long var16;
      for (var16 = 32768L; var16 < var4; var16 <<= 1) {
         long var17 = 41541732366791L;
         int var191 = var17 < 0L ? -1 : 1;
      }

      long var18 = 41541732366791L;
      int var20 = var18 < 0L ? -1 : 1;
      long var19 = 41541732366791L;
      int var22 = var19 < 0L ? -1 : 1;
      if (this.G != null) {
         this.G.close();
      }

      this.G = var1.createBuffer(ClientServices.label(() -> "Purxiy afterglow vertices"), 40, var16);
   }

   void setTiming(boolean var1) {
      this.y = var1;
      this.i = 0.0;
      this.F = 0;
   }

   private GpuQuery C(CommandEncoder var1, short var2, int var3, char var4) {
      AfterglowRenderer.ensureSourceClassInitialized();
      if (this.C != null) {
         OptionalLong var5 = this.C.getValue();
         int var6 = 0;
         if (var5.isEmpty()) {
            return null;
         }

         var6 = 234158758;
         this.C.close();
         this.C = null;
         this.i = this.i + var5.getAsLong() / 1000000.0;
         if (++this.F == 120) {
            ClientServices.LOGGER
               .info(
                  "Purxiy afterglow: {} ms GPU per frame for its light ({} motes, {} drawn)",
                  new Object[]{String.format(Locale.ROOT, "%.3f", this.i / this.F), this.V, this.N}
               );
            this.i = 0.0;
            this.F = 0;
         }
      }

      return var1.timerQueryBegin();
   }

   public void releaseInstanceResources() {
      int var1 = 0;

      try {
         if (this.C != null) {
            this.C.close();
         }
      } catch (Exception var66) {
         var1++;
      } finally {
         this.C = null;
      }

      try {
         if (this.G != null) {
            this.G.close();
         }
      } catch (Exception var64) {
         var1++;
      } finally {
         this.G = null;
      }

      try {
         if (this.J != null) {
            this.J.close();
         }
      } catch (Exception var62) {
         var1++;
      } finally {
         this.J = null;
      }

      try {
         if (this.Y != null) {
            MemoryUtil.memFree(this.Y);
         }
      } catch (Exception var60) {
         var1++;
      } finally {
         this.Y = null;
      }

      try {
         if (this.M != null) {
            MemoryUtil.memFree(this.M);
         }
      } catch (Exception var58) {
         var1++;
      } finally {
         this.M = null;
      }

      if (var1 != 0) {
         throw new IllegalStateException("Private visual instance GPU cleanup failed");
      }
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 32557) & 32767;
      if (x[var3] == null) {
         x[var3] = r[var3] ^ var1;
      }

      return x[var3];
   }
}
