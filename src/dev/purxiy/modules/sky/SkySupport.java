package dev.purxiy.modules.sky;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.CompiledRenderPipeline;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.GpuQuery;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.loader.fabric.mixin.VisualClientLevelAccessor;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.pipeline.SkyPipelines;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Random;
import net.minecraft.class_1011;
import net.minecraft.class_12076;
import net.minecraft.class_12137;
import net.minecraft.class_1294;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_5636;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_2874.class_12326;
import org.joml.Matrix4d;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.joml.Vector4d;
import org.lwjgl.system.MemoryUtil;

public final class SkySupport {
   private static final int E = 256;
   private static final int xO = 17;
   private static final int xq = 128 + (39 + (SkyKind.values().length - 1) * 2) * 16;
   private static final float xy = 1.6F;
   private static final float xg = (float) (Math.PI * 2);
   private static final float xd = 7.0F;
   private static final Vector3f J = m(35.0F, 30.0F);
   private static final Vector3f S = new Vector3f(J).cross(0.0F, 1.0F, 0.0F).normalize();
   private static final Vector3f D = new Vector3f(S).cross(J);
   private static final float x9 = (float)Math.tan(0.15);
   private static final float Z = (float)Math.asin(3.5 * Math.sin(0.15)) + 0.01F;
   private static final double xv = 0.03;
   private static final Vector3f x1 = m(215.0F, 40.0F);
   private static final Vector3f o = new Vector3f(x1).cross(0.0F, 1.0F, 0.0F).normalize();
   private static final Vector3f xj = new Vector3f(o).cross(x1);
   private static final float e = 0.036F;
   private static final float xK = 0.08279999F;
   private static final float a = 0.53999996F;
   private static final double x3 = 210.0;
   private static final double x = 120.0;
   private static final double n = 260.0;
   private static final double l = 40.0;
   private static final double xx = 30.0;
   private static final int Q = 0;
   private static final int k = 1;
   private static final int xN = 2;
   private static final int N = 3;
   private static final int g = 4;
   private static final int f = 5;
   private static final int z = 6;
   private static final int xt = 7;
   private static final int xr = 8;
   private static final int p = 9;
   private static final int x8 = 10;
   private static final int m = 11;
   private static final int y = 12;
   private static final int w = 13;
   private static final int W = 14;
   private static final int i = 15;
   private static final int xw = 16;
   private static final int xH = 17;
   private static final int xc = 18;
   private static final int xA = 19;
   private static final int d = 0;
   private static final int h = 1;
   private static final int O = 2;
   private static final int x7 = 3;
   private static final int T = 4;
   private static final int xh = 5;
   private static final int xp = 6;
   private static final int xV = 7;
   private static final int xk = 8;
   private static final int xC = 9;
   private static final int xT = 10;
   private static final int V = 11;
   private static final int xM = 12;
   private static final int xB = 13;
   private static final int xU = 14;
   private static final int u = 15;
   private static final float[] xf = new float[57];
   private static final float[] P = new float[57];
   private static final float[] U = new float[15];
   private static final float[] xl = new float[15];
   private static final SkyKind[] r = SkyKind.values();
   private static final float[] c = new float[r.length - 1];
   private static final SkyCodec[] q = new SkyCodec[r.length];
   private static SkyCatalog x_;
   private static SkyCatalog X;
   private static int xE;
   private static boolean xn;
   private static float xz;
   private static float C;
   private static float t;
   private static int b;
   private static float xQ;
   private static final Matrix4f K;
   private static final Matrix4f x2;
   private static long M;
   private static long xI;
   private static double xG;
   private static double s;
   private static float xX;
   private static float xZ;
   private static final Vector3f R;
   private static final Vector3f H;
   private static final Vector3f x6;
   private static float L;
   private static float xo;
   private static float x4;
   private static float x5;
   private static float xe;
   private static float v;
   private static boolean xF;
   private static boolean j;
   private static GpuTexture xi;
   private static GpuTextureView G;
   private static GpuTexture xu;
   private static GpuTextureView xR;
   private static GpuBuffer xW;
   private static ByteBuffer xb;
   private static String F;
   private static final float[] Y;
   private static final float[] xS;
   private static boolean x0;
   private static GpuQuery A;
   private static double xJ;
   private static int xD;
   private static int B;
   static SkyModule I;
   private static ModuleContext xm;
   private static int xa;
   private static int xP;
   private static final long[] eb;
   private static final Long[] fb;

   private SkySupport() {
   }

   public static void extract(class_638 var0, class_12076 var1) {
      long var2 = 133986315261858L;
      long var4 = var2 ^ 80464914810538L;
      int var6 = (int)((var2 ^ 80464914810538L) >>> 48);
      int var7 = (int)((var2 ^ 80464914810538L) << 16 >>> 32);
      int var8 = (int)(var4 << 48 >>> 48);
      long var9 = var2 ^ 44381899297769L;
      long var11 = var2 ^ 4613124203721L;
      long var13 = var2 ^ 1018183969995L;
      long var15 = var2 ^ 19719587593573L;
      if (!ModuleWidget.claimed("mirror-capture")) {
         long var17 = System.nanoTime();
         boolean var19 = xI != 0L && var17 - xI < 500000000L;
         float var20 = var19 ? (float)(var17 - xI) / 1.0E9F : 0.0F;
         xI = var17;
         j = false;
         xn = var1.field_64464 == class_12326.field_64386;
         SkyModule var21 = I;
         float var22 = var21.enabled() ? 1.0F : 0.0F;
         xe = var19 && xn ? W(xe, var22, Math.min(var20, 0.1F) / 1.6F, var13) : var22;
         v = xe * xe * (3.0F - 2.0F * xe);
         if (!(v <= 0.0F) && xn) {
            xz = var1.field_63091;
            C = var1.field_64465;
            t = var1.field_64466;
            b = var1.field_63096.method_75261();
            xQ = ((VisualClientLevelAccessor)var0).purxiy$skyFlashTime() > 0 ? 1.0F : xQ * (float)Math.exp(-var20 * 7.0);
            var20 = Math.min(var20, 0.1F);
            float var23 = class_310.method_1551().method_1493() ? 0.0F : var21.v.floatValue();
            xG += var20 * var23;
            s = s + var20 * var23 * (0.2F + 0.8F * xl[6]);
            xX = (xX + var20 * var23 * var21.M.floatValue() * (float) (Math.PI * 2) / 7.0F) % (float) (Math.PI * 2);
            xZ = (xZ + var20 * var23 * 0.0025F) % (float) (Math.PI * 2);
            float var24 = (float)Math.cos(xz);
            U[0] = var21.r.floatValue();
            U[1] = var21.I.floatValue();
            U[2] = var21.T.enabled() ? 1.0F : 0.0F;
            U[3] = var21.h.floatValue();
            U[4] = var21.e.is("Off") ? 0.0F : 1.0F;
            U[12] = var21.e.is("Giant") ? 0.0F : 1.0F;
            U[5] = var21.u.enabled() ? 1.0F : 0.0F;
            U[6] = var21.y.floatValue();
            U[7] = var21.o.enabled() ? 1.0F : 0.0F;
            U[8] = Q(-0.12F, 0.32F, var24) * var21.l.floatValue();
            U[9] = (float)Math.exp(-(var24 / 0.24F) * (var24 / 0.24F));
            U[10] = var21.Q.enabled() ? 1.0F : 0.0F;
            U[11] = var21.n.enabled() ? 1.0F : 0.0F;
            U[13] = var21.j.floatValue();
            U[14] = var21.N.floatValue();
            float var25 = xF ? 1.0F - (float)Math.exp(-var20 * 3.5) : 1.0F;

            for (int var26 = 0; var26 < c.length; var26++) {
               float var27 = var21.S.index() == var26 + 1 ? 1.0F : 0.0F;
               c[var26] = c[var26] + (var27 - c[var26]) * var25;
               if (Math.abs(c[var26] - var27) < 1.0E-4F) {
                  c[var26] = var27;
               }
            }

            for (int var29 = 0; var29 < 15; var29++) {
               xl[var29] = xl[var29] + (U[var29] - xl[var29]) * var25;
            }

            for (int var30 = 0; var30 < q.length; var30++) {
               q[var30].follow(var21, r[var30], var25);
            }

            C(var11, c(var9, var21), var21.N.floatValue());

            for (int var31 = 0; var31 < P.length; var31++) {
               P[var31] = P[var31] + (xf[var31] - P[var31]) * var25;
            }

            h(var15);
            F((short)var6, var7, (char)var8);
            x5 = Q(0.35F, 0.85F, (float)(Math.sin(xG * 0.029) * Math.sin(xG * 0.0173 + 1.3))) * (0.4F + 0.6F * xl[6]);
            xF = true;
         } else {
            xF = false;
         }
      }
   }

   private static SkyCatalog c(long var0, SkyModule var2) {
      SkyKind.ensureSourceClassInitialized();
      SkyCatalog var3 = SkyCatalog.byName((String)var2.D.get());
      long var4 = 89616269853771L;
      int var6 = var4 < 0L ? -1 : 1;
      if (var3 != X || q[0].generation() != xE) {
         X = var3;
         xE = q[0].generation();
         x_ = var3.in(q[0]);
      }

      return x_;
   }

   private static void C(long var0, SkyCatalog var2, float var3) {
      long var4 = 15227L;
      int var6 = -431394900;
      long var7 = 77576919536058L;
      long var9 = 30576098575316L;
      short var11 = 7119;
      short var12 = 3454;
      int var13 = (int)(var9 << 48 >>> 48);
      SkyKind.ensureSourceClassInitialized();
      float var14 = xl[8];
      P(var7, 0, var2.S, var2.x, var14);
      P(var7, 1, var2.B, var2.w, var14);
      P(var7, 2, var2.A, var2.e, var14);
      P(var7, 3, var2.H, var2.b, var14);
      float var15 = xl[9] * 0.2F;

      for (int var16 = 0; var16 < 3; var16++) {
         xf[6 + var16] = xf[6 + var16] + (xf[9 + var16] - xf[6 + var16]) * var15;
      }

      V(4, var4, var2.O, var6);
      int var45 = 2646;
      V(5, var4, var2.k, var6);
      var9 = 466324774331485575L;
      V(6, var4, var2.W, var6);
      V(7, var4, var2.j, var6);
      V(8, var4, var2.Z, var6);
      int var421 = 2458;
      long var17 = 138461375427435L;
      int var20 = var17 < 0L ? -1 : 1;
      var9 = 6869720220568555102L;
      V(9, var4, var2.p, var6);
      V(10, var4, var2.P, var6);
      V(11, var4, var2.z, var6);
      V(12, var4, var2.m, var6);
      V(13, var4, var2.C, var6);
      V(14, var4, var2.I, var6);
      V(15, var4, var2.R, var6);
      V(16, var4, var2.L, var6);
      V(17, var4, var2.d, var6);
      xf[54] = var2.U;
      xf[55] = var2.E;
      xf[56] = var2.s;
      float var20x = O(var11, var12, var13);

      for (int var21 = 0; var21 < 3; var21++) {
         long var22 = 138461375427435L;
         int var24 = var22 < 0L ? -1 : 1;
         xf[6 + var21] = xf[6 + var21] * var20x;
      }

      long var48 = 138461375427435L;
      int var23 = var48 < 0L ? -1 : 1;
      var23 = 0;

      while (true) {
         long var24 = 138461375427434L;
         int var27 = var24 < 0L ? -1 : 1;
         if (var23 >= c.length) {
            long var49 = 138461375427435L;
            int var51 = var49 < 0L ? -1 : 1;

            for (int var29 = 0; var29 < 51; var29++) {
               xf[var29] = xf[var29] * var3;
            }

            long var50 = 138461375427434L;
            int var29 = var50 < 0L ? -1 : 1;
            return;
         }

         long var27x = 138461375427435L;
         float var30x;
         var421 = (var30x = c[var23] - 0.0F) == 0.0F ? 0 : (var30x < 0.0F ? -1 : 1);
         long var31 = 138461375427435L;
         if (var421 <= 0) {
            long var34 = 138461375427435L;
            int var36 = var34 < 0L ? -1 : 1;
         } else {
            SkyKind var51 = r[var23 + 1];
            int var35 = q[var23 + 1].carry(var51.Z);
            int var36 = q[var23 + 1].carry(var51.C);

            for (int var37 = 0; var37 < 3; var37++) {
               int var38 = 16 - var37 * 8;
               float var39 = (var35 >> var38 & 0xFF) / 255.0F;
               long var40 = 138461375427434L;
               int var38x = var40 < 0L ? -1 : 1;
               float var42x = (var36 >> var38 & 0xFF) / 255.0F;
               xf[6 + var37] = xf[6 + var37] + (var39 + (var42x - var39) * var14) * c[var23];
            }
         }

         var23++;
         long var52x = 138461375427435L;
         int var58 = var52x < 0L ? -1 : 1;
      }
   }

   private static void h(long var0) {
      long var2 = 17795386483527L;
      SkyKind.ensureSourceClassInitialized();
      double var4 = Math.floor(xG / 210.0);
      double var6 = xG - var4 * 210.0;
      float var8 = xl[11];
      long var9 = 114569556699847L;
      int var12 = var9 < 0L ? -1 : 1;
      if (!(var8 <= 0.001F) && !(var6 > 120.0)) {
         Random var12x = new Random((long)var4 * 2685821657736338717L + 17L);
         Vector3f var13 = m(var12x.nextFloat() * 360.0F, 25.0F + var12x.nextFloat() * 30.0F);
         Vector3f var14 = new Vector3f(var13);
         long var15 = 114569556699846L;
         int var17 = var15 < 0L ? -1 : 1;
         Vector3f var17x = var14.cross(0.0F, 1.0F, 0.0F).normalize().mul(var12x.nextBoolean() ? 1.0F : -1.0F);
         Vector3f var18 = Z(var13, var2, new Vector3f(var17x).mul(0.9F).add(0.0F, -0.3F, 0.0F));
         float var19 = (float)Math.toRadians(18.0 + var12x.nextFloat() * 14.0) * (float)(var6 / 120.0);
         R.set(var13).mul((float)Math.cos(var19)).add(new Vector3f(var18).mul((float)Math.sin(var19)));
         Vector3f var20 = new Vector3f(var13).mul(-((float)Math.sin(var19))).add(new Vector3f(var18).mul((float)Math.cos(var19)));
         Vector3f var21 = Z(R, var2, new Vector3f((float)Math.sin(xz), -((float)Math.cos(xz)), 0.0F));
         H.set(Z(R, var2, var21.mul(0.7F).sub(var20.mul(0.3F))));
         xo = (float)Math.toRadians(14.0 + var12x.nextFloat() * 12.0);
         L = xl[11] * Q(0.0F, 12.0F, (float)var6) * (1.0F - Q(105.0F, 120.0F, (float)var6));
      } else {
         L = 0.0F;
      }
   }

   private static void F(short var0, int var1, char var2) {
      SkyKind.ensureSourceClassInitialized();
      double var3 = Math.floor(xG / 260.0);
      float var5 = (float)(xG - var3 * 260.0 - 40.0);
      if (!(var5 < 0.0F) && !(var5 > 30.0)) {
         Random var6 = new Random((long)var3 * -7046029254386353131L + 3L);
         x6.set(m(var6.nextFloat() * 360.0F, 35.0F + var6.nextFloat() * 35.0F));
         x4 = Q(0.0F, 5.0F, var5) * (1.0F - Q(24.0F, 30.0F, var5));
      } else {
         x4 = 0.0F;
      }
   }

   public static void captureProjection(Matrix4f var0) {
      K.set(var0);
   }

   public static void captureSteadyProjection(Matrix4f var0, Matrix4f var1) {
      x2.set(var0).mul(var1);
      M = RenderPipelines.get().frame();
   }

   private static Matrix4d q(long var0) {
      long var3 = 32617772237852L;
      SkyKind.ensureSourceClassInitialized();
      if (I.O.enabled()) {
         long var5;
         int var2 = (var5 = M - RenderPipelines.get().frame()) == 0L ? 0 : (var5 < 0L ? -1 : 1);
         long var7 = 97837950858375L;
         int var13 = var7 < 0L ? -1 : 1;
         if (var2 == 0 && !d(var3)) {
            boolean var13x = true;
            return new Matrix4d(var13x ? x2 : K).mul(new Matrix4d(RenderSystem.getModelViewMatrix()));
         }
      }

      boolean var10 = false;
      return new Matrix4d(var10 ? x2 : K).mul(new Matrix4d(RenderSystem.getModelViewMatrix()));
   }

   private static boolean d(long var0) {
      SkyKind.ensureSourceClassInitialized();
      class_746 var2 = class_310.method_1551().field_1724;
      return var2 != null && (var2.field_44911 > 0.0F || var2.field_44912 > 0.0F || var2.method_6059(class_1294.field_5916));
   }

   private static Matrix4f O(Matrix4d var0) {
      Matrix4d var1 = var0.invert(new Matrix4d());
      Vector3d var2 = i(var1, 0.0, 0.0);
      Vector3d var3 = i(var1, 1.0, 0.0).sub(var2);
      Vector3d var4 = i(var1, 0.0, 1.0).sub(var2);
      double var5 = 1.0 / var2.length();
      var2.mul(var5);
      var3.mul(var5);
      var4.mul(var5);
      return new Matrix4f(
         (float)var3.x,
         (float)var3.y,
         (float)var3.z,
         0.0F,
         (float)var4.x,
         (float)var4.y,
         (float)var4.z,
         0.0F,
         (float)var2.x,
         (float)var2.y,
         (float)var2.z,
         0.0F,
         0.0F,
         0.0F,
         0.0F,
         1.0F
      );
   }

   private static Vector3d i(Matrix4d var0, double var1, double var3) {
      Vector4d var5 = var0.transform(new Vector4d(var1, var3, -1.0, 1.0));
      Vector4d var6 = var0.transform(new Vector4d(var1, var3, 1.0, 1.0));
      return new Vector3d(var6.x / var6.w - var5.x / var5.w, var6.y / var6.w - var5.y / var5.w, var6.z / var6.w - var5.z / var5.w);
   }

   private static void P(Matrix4d var0, Vector3f var1, Vector3f var2, Vector3f var3, float var4, long var5, boolean var7, int var8, int var9, float[] var10) {
      SkyKind.ensureSourceClassInitialized();
      var10[0] = var10[1] = var10[2] = var10[3] = 0.0F;
      if (var7) {
         double var11 = Double.MAX_VALUE;
         double var13 = Double.MAX_VALUE;
         double var15 = -Double.MAX_VALUE;
         double var17 = -Double.MAX_VALUE;
         double var19 = Math.cos(var4);
         double var21 = Math.sin(var4);
         Vector4d var23 = new Vector4d();

         for (int var24 = 0; var24 < 24; var24++) {
            double var25 = var24 * Math.PI * 2.0 / 24.0;
            double var27 = Math.cos(var25) * var21;
            double var29 = Math.sin(var25) * var21;
            long var31 = 125815353116160L;
            int var34 = var31 < 0L ? -1 : 1;
            var23.set(
               var1.x * var19 + var2.x * var27 + var3.x * var29,
               var1.y * var19 + var2.y * var27 + var3.y * var29,
               var1.z * var19 + var2.z * var27 + var3.z * var29,
               0.0
            );
            var0.transform(var23);
            long var34x = 125815353116161L;
            int var351 = var34x < 0L ? -1 : 1;
            if (var23.w <= 1.0E-6) {
               var10[0] = -1.0F;
               var10[1] = -1.0F;
               var10[2] = 1.0F;
               var10[3] = 1.0F;
               return;
            }

            var11 = Math.min(var11, var23.x / var23.w);
            var15 = Math.max(var15, var23.x / var23.w);
            var13 = Math.min(var13, var23.y / var23.w);
            var17 = Math.max(var17, var23.y / var23.w);
         }

         double var43 = (var15 - var11) * 0.04;
         long var26 = 125815353116161L;
         int var44 = var26 < 0L ? -1 : 1;
         double var44x = var43 + 4.0 / var8;
         double var45 = (var17 - var13) * 0.04 + 4.0 / var9;
         long var33 = 125815353116160L;
         int var35 = var33 < 0L ? -1 : 1;
         double var35x = Math.max(-1.0, var11 - var44x);
         double var37 = Math.max(-1.0, var13 - var45);
         double var39 = Math.min(1.0, var15 + var44x);
         double var41 = Math.min(1.0, var17 + var45);
         if (var35x < var39 && var37 < var41) {
            var10[0] = (float)var35x;
            var10[1] = (float)var37;
            var10[2] = (float)var39;
            var10[3] = (float)var41;
         }
      }
   }

   public static boolean replaceVanilla() {
      long var0 = 90616585451086L;
      long var2 = var0 ^ 140484423911819L;
      long var4 = var0 ^ 13057221030851L;
      if (ModuleWidget.claimed("mirror-capture")) {
         return false;
      } else {
         if (xn && xF && v >= 1.0F && j(var2)) {
            j = L(1.0F, var4);
         }

         return j;
      }
   }

   public static void drawOverVanilla() {
      long var0 = 107381103712330L;
      long var2 = var0 ^ 83592307222415L;
      long var4 = var0 ^ 61726513782215L;
      if (!ModuleWidget.claimed("mirror-capture") && xn && xF && !j && v > 0.0F && v < 1.0F && j(var2)) {
         L(v, var4);
      }
   }

   public static boolean replaced() {
      return j;
   }

   public static float shown() {
      return xn && xF ? v : 0.0F;
   }

   public static boolean hideVanillaClouds() {
      long var0 = 119811855734734L;
      long var2 = var0 ^ 131085931861018L;
      int var4 = (int)((var0 ^ 131085931861018L) >>> 32);
      int var5 = (int)((var0 ^ 131085931861018L) << 32 >>> 48);
      int var6 = (int)(var2 << 48 >>> 48);
      return ModuleWidget.claimed("mirror-capture") ? false : xn && xF && j && v >= 0.998F && O(var4, var5, var6) <= 0.002F;
   }

   private static float O(int var0, int var1, int var2) {
      float var3 = 1.0F;
      SkyKind.ensureSourceClassInitialized();
      float[] var4 = c;
      int var5 = var4.length;
      int var6 = 0;

      while (true) {
         float var7;
         if (var6 < var5) {
            var7 = var4[var6];
         } else {
            var7 = var3;
            if (var0 > 0) {
               return Math.clamp(var3, 0.0F, 1.0F);
            }
         }

         var3 -= var7;
         var6++;
      }
   }

   public static int fogColor(class_638 var0, int var1) {
      long var2 = 33608745697194L;
      long var4 = var2 ^ 29442404921543L;
      return xF && !(v <= 0.0F) && var0.method_8597().comp_5220() == class_12326.field_64386 ? t(var1, P[6], P[7], var4, P[8], v) : var1;
   }

   public static int cloudColor(int var0) {
      long var1 = 15998065205650L;
      long var3 = var1 ^ 115750437877412L;
      int var5 = (int)((var1 ^ 115750437877412L) >>> 32);
      int var6 = (int)((var1 ^ 115750437877412L) << 32 >>> 48);
      int var7 = (int)(var3 << 48 >>> 48);
      float var8 = xl[7] * v;
      if (xF && xn && !(var8 <= 0.0F)) {
         int var9 = var0 & 0xFF000000;

         for (int var10 = 0; var10 < 3; var10++) {
            int var11 = 16 - var10 * 8;
            float var12 = (var0 >> var11 & 0xFF) / 255.0F * P[51 + var10] * 0.8F + P[6 + var10] * 0.35F;
            var9 |= X(var5, (char)var6, var0 >> var11, var12, (char)var7, var8) << var11;
         }

         return var9;
      } else {
         return var0;
      }
   }

   private static boolean j(long var0) {
      GpuDevice var2 = RenderSystem.getDevice();
      SkyKind.ensureSourceClassInitialized();
      int var3 = I.g.index();
      CompiledRenderPipeline var4 = var2.precompilePipeline(SkyPipelines.sky(var3));
      long var5 = 50230805266372L;
      int var7 = var5 < 0L ? -1 : 1;
      return var4.isValid()
         && var2.precompilePipeline(SkyPipelines.skySpace(var3)).isValid()
         && var2.precompilePipeline(SkyPipelines.skyBodies(var3)).isValid();
   }

   private static boolean L(float var0, long var1) {
      long var3 = 125815353116161L;
      long var5 = 5653108918548L;
      long var7 = 423033285336L;
      byte var9 = 92;
      long var10 = 97837950858376L;
      long var12 = 15468606354144L;
      long var14 = 30576098575316L;
      short var16 = 7119;
      short var17 = 3454;
      int var18 = (int)(var14 << 48 >>> 48);
      long var19 = 46325385586275L;
      SkyKind.ensureSourceClassInitialized();

      try {
         class_276 var21 = class_310.method_1551().method_1522();
         GpuTextureView var68 = var21.method_71639();
         if (var68 == null) {
            return false;
         } else {
            if (G == null) {
               T(var12);
            }

            E((var21.field_1482 + 1) / 2, (var21.field_1481 + 1) / 2, var5);
            float var23 = O(var16, var17, var18);
            boolean var24 = var23 > 0.002F && xl[0] + xl[3] > 0.002F;
            int var25 = I.g.index();
            Matrix4d var26 = q(var10);
            boolean var27 = var23 * xl[4] > 0.002F;
            float[] var28 = Y;
            int var29 = var21.field_1481;
            int var30 = var21.field_1482;
            P(var26, J, S, D, Z, var3, var27, var30, var29, var28);
            var27 = var23 * xl[10] > 0.002F;
            var28 = xS;
            var29 = var21.field_1481;
            var30 = var21.field_1482;
            P(var26, x1, o, xj, 0.53999996F, var3, var27, var30, var29, var28);
            boolean var32 = Y[2] > Y[0] || xS[2] > xS[0];
            CommandEncoder var33 = RenderSystem.getDevice().createCommandEncoder();
            Matrix4f var34 = O(var26);
            long var35 = 98450555766157L;
            int var38 = var35 < 0L ? -1 : 1;
            long var38x = 98450555766157L;
            if (B > 0) {
               B--;
               ClientServices.LOGGER
                  .info(
                     "SourceByDevFFnightmare DeObfDeCompByGuideX sky camera: centre {} {} {}, right {} {} {}, planet {} {}",
                     new Object[]{
                        var34.m20(),
                        var34.m21(),
                        var34.m22(),
                        var34.m00(),
                        var34.m01(),
                        var34.m02(),
                        (Y[0] + Y[2]) * 0.5F * var21.field_1482 * 0.5F,
                        (Y[1] + Y[3]) * 0.5F * var21.field_1481 * 0.5F
                     }
                  );
            }

            Z(var19, var33, var34, var0, var21.field_1482, var21.field_1481, var24);
            class_12137 var41x = RenderSystem.getSamplerCache().method_75297(FilterMode.LINEAR);
            GpuQuery var42 = x0 ? u(var7, var9, var33) : null;
            boolean var43 = false;

            try {
               var43 = true;
               if (var24) {
                  RenderPass var44 = var33.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX sky (soft layers)", xR, OptionalInt.empty());

                  try {
                     long var45 = 98450555766157L;
                     int var44x = var45 < 0L ? -1 : 1;
                     var44.setPipeline(SkyPipelines.skySpace(var25));
                     var44.setUniform("PurxiySky", xW);
                     var44.bindTexture("NoiseSampler", G, var41x);
                     long var48 = 98450555766156L;
                     int var47 = var48 < 0L ? -1 : 1;
                     var44.draw(0, 3);
                     var47 = 1315407245;
                     metric("drawn");
                  } catch (Throwable var61) {
                     if (var44 != null) {
                        try {
                           var44.close();
                        } catch (Throwable var58) {
                           var61.addSuppressed(var58);
                        }
                     }

                     throw var61;
                  }

                  if (var44 != null) {
                     var44.close();
                  }
               }

               RenderPass var73 = var33.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX sky", var68, OptionalInt.empty());

               try {
                  long var75 = 98450555766156L;
                  int var76 = var75 < 0L ? -1 : 1;
                  var73.setPipeline(SkyPipelines.sky(var25));
                  RenderSystem.bindDefaultUniforms(var73);
                  var73.setUniform("PurxiySky", xW);
                  var73.bindTexture("NoiseSampler", G, var41x);
                  var73.bindTexture("SpaceSampler", xR, RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR));
                  var73.draw(0, 3);
                  var76 = 1315407245;
                  metric("drawn");
               } catch (Throwable var60) {
                  if (var73 != null) {
                     try {
                        var73.close();
                     } catch (Throwable var57) {
                        var60.addSuppressed(var57);
                     }
                  }

                  throw var60;
               }

               if (var73 != null) {
                  var73.close();
               }

               if (var32) {
                  long var74 = 98450555766156L;
                  int var43x = var74 < 0L ? -1 : 1;
                  RenderPass var77x = var33.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX sky (planet, singularity)", var68, OptionalInt.empty());

                  try {
                     var77x.setPipeline(SkyPipelines.skyBodies(var25));
                     var77x.setUniform("PurxiySky", xW);
                     var77x.bindTexture("NoiseSampler", G, var41x);
                     int var46 = 1315412515;
                     var77x.draw(0, 12);
                     metric("drawn");
                  } catch (Throwable var59) {
                     if (var77x != null) {
                        try {
                           var77x.close();
                        } catch (Throwable var56) {
                           var59.addSuppressed(var56);
                        }
                     }

                     throw var59;
                  }

                  if (var77x != null) {
                     var77x.close();
                  }
               } else {
                  var43 = false;
               }
            } finally {
               if (var43 && var42 != null) {
                  var33.timerQueryEnd(var42);
                  A = var42;
               }
            }

            if (var42 != null) {
               var33.timerQueryEnd(var42);
               A = var42;
            }

            return true;
         }
      } catch (Throwable var63) {
         metric("draw_failures");
         String var22 = var63.toString();
         if (!var22.equals(F)) {
            F = var22;
            ClientServices.LOGGER.error("SourceByDevFFnightmare DeObfDeCompByGuideX sky failed, showing vanilla's", var63);
         }

         return false;
      }
   }

   public static void setTiming(boolean var0) {
      x0 = var0;
      xJ = 0.0;
      xD = 0;
   }

   public static void logCamera(int var0) {
      B = var0;
   }

   private static GpuQuery u(long var0, byte var2, CommandEncoder var3) {
      SkyKind.ensureSourceClassInitialized();
      if (A != null) {
         OptionalLong var4 = A.getValue();
         long var5 = 423033285336L;
         int var8 = var5 < 0L ? -1 : 1;
         if (var4.isEmpty()) {
            return null;
         }

         A.close();
         A = null;
         xJ = xJ + var4.getAsLong() / 1000000.0;
         long var8x = 423033285335L;
         int var9 = var8x < 0L ? -1 : 1;
         if (++xD == 240) {
            ClientServices.LOGGER
               .info(
                  "SourceByDevFFnightmare DeObfDeCompByGuideX sky: {} ms GPU per frame ({} quality, {}x{})",
                  new Object[]{
                     String.format(Locale.ROOT, "%.3f", xJ / xD),
                     I.g.get(),
                     class_310.method_1551().method_1522().field_1482,
                     class_310.method_1551().method_1522().field_1481
                  }
               );
            xJ = 0.0;
            xD = 0;
         }
      }

      return var3.timerQueryBegin();
   }

   private static void Z(long var0, CommandEncoder var2, Matrix4f var3, float var4, int var5, int var6, boolean var7) {
      long var8 = 30576098575316L;
      short var10 = 7119;
      short var11 = 3454;
      int var12 = (int)(var8 << 48 >>> 48);
      SkyKind.ensureSourceClassInitialized();
      Matrix4f var13 = new Matrix4f().rotationY((float) (-Math.PI / 2)).rotateX(t + xZ).transpose();
      boolean var14 = class_310.method_1551().field_1773.method_19418().method_19334() != class_5636.field_27888;
      float var15 = P[54];
      float var16 = P[55];
      float var17 = P[56];
      float var18 = (float)Math.sqrt(var15 * var15 + var16 * var16 + var17 * var17);
      long var19 = 46325385586274L;
      int var22 = var19 < 0L ? -1 : 1;
      if (var18 > 1.0E-4F) {
         var15 /= var18;
         var16 /= var18;
         var17 /= var18;
      }

      Std140Builder var22x = Std140Builder.intoBuffer(xb.clear())
         .putMat4f(var3)
         .putMat4f(var13)
         .putVec4(-((float)Math.sin(xz)), (float)Math.cos(xz), 0.0F, xl[5])
         .putVec4(-((float)Math.sin(C)), (float)Math.cos(C), 0.0F, b * (float) (Math.PI * 2) / 8.0F)
         .putVec4(J.x, J.y, J.z, x9)
         .putVec4(S.x, S.y, S.z, xl[4] * O(var10, var11, var12))
         .putVec4(D.x, D.y, D.z, (float)(xG * 0.03 % 628.3185482025146))
         .putVec4(var15, var16, var17, xl[12])
         .putVec4(Y[0], Y[1], Y[2], Y[3])
         .putVec4(x1.x, x1.y, x1.z, 0.036F)
         .putVec4(o.x, o.y, o.z, xl[10] * O(var10, var11, var12));
      long var23 = 46325385586275L;
      int var26 = var23 < 0L ? -1 : 1;
      Std140Builder var26x = var22x.putVec4(xj.x, xj.y, xj.z, 0.08279999F)
         .putVec4(xS[0], xS[1], xS[2], xS[3])
         .putVec4(R.x, R.y, R.z, L)
         .putVec4(H.x, H.y, H.z, xo)
         .putVec4(x6.x, x6.y, x6.z, x4 * xl[2])
         .putVec4((float)xG, (float)s, xX, xl[2])
         .putVec4(xl[0], xl[1], xl[3], xl[6])
         .putVec4(xl[8], xl[9], 0.0F, xQ)
         .putVec4(1.0F / var5, 1.0F / var6, var7 ? 1.0F : 0.0F, var4)
         .putVec4(var14 ? 1.0F : 0.0F, x5, 0.0F, 0.0F)
         .putVec4(c[0], c[1], c[2], c[3])
         .putVec4(c[4], c[5], c[6], 0.0F)
         .putVec4(xl[13], xl[14], 0.0F, 0.0F);

      for (int var27 = 0; var27 < 17; var27++) {
         var26x.putVec4(P[var27 * 3], P[var27 * 3 + 1], P[var27 * 3 + 2], 1.0F);
         long var28 = 46325385586275L;
         int var28x = var28 < 0L ? -1 : 1;
      }

      long var37 = 46325385586274L;
      int var30 = var37 < 0L ? -1 : 1;
      var30 = 1;

      while (true) {
         long var31 = 46325385586275L;
         int var30x = var31 < 0L ? -1 : 1;
         if (var30 >= q.length) {
            long var38 = 46325385586275L;
            int var40 = var38 < 0L ? -1 : 1;
            var2.writeToBuffer(xW.slice(), var26x.get());
            return;
         }

         q[var30].write(var26x);
         long var34x = 46325385586274L;
         int var32 = var34x < 0L ? -1 : 1;
         var30++;
      }
   }

   private static void T(long var0) {
      long var2 = 48162728707056L;
      GpuDevice var4 = RenderSystem.getDevice();
      SkyKind.ensureSourceClassInitialized();
      xi = var4.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX sky noise", 5, TextureFormat.RGBA8, 256, 256, 1, 1);
      class_1011 var5 = new class_1011(256, 256, false);

      try {
         for (int var6 = 0; var6 < 256; var6++) {
            long var7 = 15468606354143L;
            int var21 = var7 < 0L ? -1 : 1;

            for (int var10 = 0; var10 < 256; var10++) {
               int var11 = z(var10, var6, 0, var2);
               long var12 = 15468606354144L;
               int var15 = var12 < 0L ? -1 : 1;
               var15 = z(var10 + 37, var6 + 17, 0, var2);
               int var16 = z(var10, var6, 1, var2);
               int var17x = z(var10 + 37, var6 + 17, 1, var2);
               var5.method_61941(var10, var6, var17x << 24 | var11 << 16 | var15 << 8 | var16);
            }

            long var21x = 15468606354144L;
         }

         var4.createCommandEncoder().writeToTexture(xi, var5);
         long var20 = 15468606354144L;
         int var8 = var20 < 0L ? -1 : 1;
      } catch (Throwable var171) {
         try {
            var5.close();
         } catch (Throwable var161) {
            var171.addSuppressed(var161);
         }

         throw var171;
      }

      var5.close();
      G = var4.createTextureView(xi);
      xW = var4.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX sky uniforms"), 136, xq);
      xb = MemoryUtil.memAlloc(xq);
   }

   private static void E(int var0, int var1, long var2) {
      SkyKind.ensureSourceClassInitialized();
      if (xu != null) {
         int var4 = xu.getWidth(0);
         long var5 = 5653108918548L;
         int var7 = var5 < 0L ? -1 : 1;
         if (var4 == var0 && xu.getHeight(0) == var1) {
            return;
         }
      }

      if (xR != null) {
         xR.close();
         xu.close();
      }

      GpuDevice var8 = RenderSystem.getDevice();
      xu = var8.createTexture(() -> {
         long var0x = 111816964834266L;
         return "SourceByDevFFnightmare DeObfDeCompByGuideX sky soft layers";
      }, 12, TextureFormat.RGBA8, var0, var1, 1, 1);
      xR = var8.createTextureView(xu);
   }

   private static int z(int var0, int var1, int var2, long var3) {
      int var5 = (var1 & 0xFF) * 256 + (var0 & 0xFF) + var2 * 65536 + 24301;
      var5 ^= var5 >>> 16;
      var5 *= 2146121005;
      var5 ^= var5 >>> 15;
      var5 *= -2073254261;
      var5 ^= var5 >>> 16;
      return var5 & 0xFF;
   }

   private static Vector3f m(float var0, float var1) {
      double var2 = Math.toRadians(var0);
      double var4 = Math.toRadians(var1);
      return new Vector3f((float)(Math.sin(var2) * Math.cos(var4)), (float)Math.sin(var4), (float)(-Math.cos(var2) * Math.cos(var4)));
   }

   private static Vector3f Z(Vector3f var0, long var1, Vector3f var3) {
      SkyKind.ensureSourceClassInitialized();
      Vector3f var4 = new Vector3f(var3).sub(new Vector3f(var0).mul(var0.dot(var3)));
      float var5 = var4.length();
      long var6 = 17795386483526L;
      int var8 = var6 < 0L ? -1 : 1;
      if (var5 < 1.0E-4F) {
         var4.set(var0).cross(0.0F, 1.0F, 0.0F);
         var5 = var4.length();
      }

      return var4.div(var5);
   }

   private static void V(int var0, long var1, int var3, int var4) {
      xf[var0 * 3] = (var3 >> 16 & 0xFF) / 255.0F;
      xf[var0 * 3 + 1] = (var3 >> 8 & 0xFF) / 255.0F;
      xf[var0 * 3 + 2] = (var3 & 0xFF) / 255.0F;
   }

   private static void P(long var0, int var2, int var3, int var4, float var5) {
      SkyKind.ensureSourceClassInitialized();

      for (int var6 = 0; var6 < 3; var6++) {
         int var7 = 16 - var6 * 8;
         float var8 = (var3 >> var7 & 0xFF) / 255.0F;
         float var9 = (var4 >> var7 & 0xFF) / 255.0F;
         xf[var2 * 3 + var6] = var8 + (var9 - var8) * var5;
      }
   }

   private static int t(int var0, float var1, float var2, long var3, float var5, float var6) {
      long var7 = 114121194673974L;
      short var9 = 26570;
      char var10 = '\ue945';
      int var11 = (int)(var7 << 48 >>> 48);
      return var0 & 0xFF000000
         | X(var9, var10, var0 >> 16, var1, (char)var11, var6) << 16
         | X(var9, var10, var0 >> 8, var2, (char)var11, var6) << 8
         | X(var9, var10, var0, var5, (char)var11, var6);
   }

   private static int X(int var0, char var1, int var2, float var3, char var4, float var5) {
      float var6 = (var2 & 0xFF) / 255.0F;
      return Math.round(Math.clamp(var6 + (var3 - var6) * var5, 0.0F, 1.0F) * 255.0F);
   }

   private static float W(float var0, float var1, float var2, long var3) {
      SkyKind.ensureSourceClassInitialized();
      long var5 = 133251608329065L;
      int var7 = var5 < 0L ? -1 : 1;
      return var0 < var1 ? Math.min(var1, var0 + var2) : Math.max(var1, var0 - var2);
   }

   private static float Q(float var0, float var1, float var2) {
      float var3 = Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

   public static void install(SkyModule var0, ModuleContext var1) {
      I = var0;
      xm = var1;
   }

   public static void metric(String var0) {
      if (xm != null) {
         xm.metric(var0, var0.equals("drawn") ? ++xa : ++xP);
      }
   }

   public static void uninstall() {
      int var0 = 0;

      try {
         ClientCache.release(SkyPipelines.sky(0));
      } catch (Exception var192) {
         var0++;
      }

      try {
         ClientCache.release(SkyPipelines.skySpace(0));
      } catch (Exception var191) {
         var0++;
      }

      try {
         ClientCache.release(SkyPipelines.skyBodies(0));
      } catch (Exception var190) {
         var0++;
      }

      try {
         ClientCache.release(SkyPipelines.sky(1));
      } catch (Exception var189) {
         var0++;
      }

      try {
         ClientCache.release(SkyPipelines.skySpace(1));
      } catch (Exception var188) {
         var0++;
      }

      try {
         ClientCache.release(SkyPipelines.skyBodies(1));
      } catch (Exception var187) {
         var0++;
      }

      try {
         ClientCache.release(SkyPipelines.sky(2));
      } catch (Exception var186) {
         var0++;
      }

      try {
         ClientCache.release(SkyPipelines.skySpace(2));
      } catch (Exception var185) {
         var0++;
      }

      try {
         ClientCache.release(SkyPipelines.skyBodies(2));
      } catch (Exception var184) {
         var0++;
      }

      try {
         if (G != null) {
            G.close();
         }
      } catch (Exception var182) {
         var0++;
      } finally {
         G = null;
      }

      try {
         if (xR != null) {
            xR.close();
         }
      } catch (Exception var180) {
         var0++;
      } finally {
         xR = null;
      }

      try {
         if (A != null) {
            A.close();
         }
      } catch (Exception var178) {
         var0++;
      } finally {
         A = null;
      }

      try {
         if (xW != null) {
            xW.close();
         }
      } catch (Exception var176) {
         var0++;
      } finally {
         xW = null;
      }

      try {
         if (xi != null) {
            xi.close();
         }
      } catch (Exception var174) {
         var0++;
      } finally {
         xi = null;
      }

      try {
         if (xu != null) {
            xu.close();
         }
      } catch (Exception var172) {
         var0++;
      } finally {
         xu = null;
      }

      try {
         if (xb != null) {
            MemoryUtil.memFree(xb);
         }
      } catch (Exception var170) {
         var0++;
      } finally {
         xb = null;
      }

      j = false;
      xn = false;
      v = 0.0F;
      xe = 0.0F;
      I = null;
      xm = null;
      xI = 0L;
      xF = false;
      if (var0 != 0) {
         throw new IllegalStateException("World visual GPU cleanup failed");
      }
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 27699) & 32767;
      if (fb[var3] == null) {
         fb[var3] = eb[var3] ^ var1;
      }

      return fb[var3];
   }

   static {
      for (int var0 = 0; var0 < q.length; var0++) {
         q[var0] = new SkyCodec();
      }

      xE = 0;
      b = 0;
      K = new Matrix4f();
      x2 = new Matrix4f();
      M = Long.MIN_VALUE;
      xI = 0L;
      R = new Vector3f();
      H = new Vector3f();
      x6 = new Vector3f();
      Y = new float[4];
      xS = new float[4];
      xD = 0;
      B = 0;
      xa = 0;
      xP = 0;
      eb = new long[]{1346373062445822417L, -2103473856920565623L, 7188566353384851749L, 1765549318640854796L};
      fb = new Long[4];
   }
}
