package dev.purxiy.modules.haze;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.purxiy.assets.IrisCompat;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_12206;
import net.minecraft.class_1294;
import net.minecraft.class_1309;
import net.minecraft.class_1944;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_5636;
import net.minecraft.class_638;
import net.minecraft.class_2874.class_12326;
import net.minecraft.class_2902.class_2903;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;

public final class HazeRenderPass {
   private static final float X = 3.0F;
   private static final float T = 2.2F;
   static final double r = 32768.0;
   static final float H = 180.0F;
   static final float Y = 20.0F;
   static final Matrix4f K = new Matrix4f();
   static final Matrix4f i = new Matrix4f();
   static double Q;
   static double o;
   static double Z;
   static float j = 0.05F;
   static float N = 512.0F;
   static float h;
   static GpuBufferSlice n;
   private static boolean M;
   static boolean k;
   static boolean P;
   static boolean q;
   static final float[] R = new float[3];
   static final float[] m = new float[3];
   static final float[] J = new float[3];
   static final float[] z7 = new float[3];
   static final Vector3f I = new Vector3f(0.0F, 1.0F, 0.0F);
   static float y;
   static float l;
   static float S;
   private static float V;
   private static float B = 1.0F;
   static float zX = 0.45F;
   static float F = 24.0F;
   static float g = 8.0F;
   static float a = 1.0F;
   static float A = 0.6F;
   static float D = 1.0F;
   static float b = 0.55F;
   private static final float[] d = new float[18];
   private static boolean C;
   static float O = 64.0F;
   private static boolean G;
   private static int u = 0;
   private static float w;
   static double v;
   static double s;
   static double U;
   private static long z = 0L;
   private static class_638 e;
   private static boolean W;
   static int zx = 0;
   static final float[] c = new float[]{0.021F, 0.12F, 3.0F, 1.0F, 1.0F, 0.55F};
   private static String f;
   static HazeModule t;
   private static ModuleContext p;
   private static int E = 0;
   private static int x = 0;
   public static int L = 0;

   private HazeRenderPass() {
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2, GpuBufferSlice var3) {
      long var4 = 71700696293024L;
      long var6 = var4 ^ 25802720436040L;
      q = false;
      P = false;
      if (!ModuleWidget.claimed("mirror-capture")) {
         class_310 var8 = class_310.method_1551();
         M = (S > 0.0F || t.enabled()) && !var8.field_1773.method_35765();
         if (M) {
            K.set(var2).mul(var1);
            i.set(K).invert();
            float var9 = var2.m22();
            float var10 = var2.m32();
            j = var10 / (var9 - 1.0F);
            N = var10 / (var9 + 1.0F);
            class_243 var11 = var0.method_71156();
            Q = var11.field_1352;
            o = var11.field_1351;
            Z = var11.field_1350;
            h = var0.method_55437();
            n = var3;
            k = IrisCompat.inUse();
            if (k) {
               SkyPipelineRouter.wantHandMask();
            }

            N(var0, var6);
         }
      }
   }

   static void keepDepth(boolean var0) {
      long var1 = 33761901466699L;
      long var3 = var1 ^ 129010560399283L;
      if (M && !(S <= 0.0F) && !(D <= 0.001F) && !ModuleWidget.claimed("mirror-capture")) {
         try {
            HazePipeline.keepDepth(var0);
            if (var0) {
               q = true;
            } else {
               P = true;
            }
         } catch (Throwable var6) {
            metric("draw_failures");
            Z(var6, var3);
         }
      }
   }

   public static void render() {
      long var0 = 137571042249361L;
      long var2 = var0 ^ 81531822330557L;
      long var4 = var0 ^ 25286949333353L;
      if (!ModuleWidget.claimed("mirror-capture")) {
         boolean var6 = M;
         M = false;
         class_310 var7 = class_310.method_1551();
         long var8 = System.nanoTime();
         float var10 = z == 0L ? 0.0F : Math.min((float)(var8 - z) / 1.0E9F, 0.1F);
         z = var8;
         class_638 var11 = var7.field_1687;
         if (var6 && var11 != null && n != null) {
            if (var11 != e) {
               e = var11;
               G = false;
            }

            float var12 = var7.method_1493() ? 0.0F : var10;
            U += var12;
            f(var2, var7, var11, var10);
            u(var12);
            if (!(S <= 0.001F)) {
               try {
                  HazePipeline.draw(var7);
               } catch (Throwable var14) {
                  metric("draw_failures");
                  Z(var14, var4);
               }
            }
         } else {
            z = 0L;
            if (!t.enabled() || var11 == null) {
               V = 0.0F;
               S = 0.0F;
            }
         }
      }
   }

   private static void Z(Throwable var0, long var1) {
      String var3 = var0.toString();
      long var4 = 118615499809783L;
      int var7 = var4 < 0L ? -1 : 1;
      long var7x = 118615499809784L;
      int var8 = var7x < 0L ? -1 : 1;
      if (!var3.equals(f)) {
         f = var3;
         ClientServices.LOGGER.error("SourceByDevFFnightmare DeObfDeCompByGuideX haze failed", var0);
      }
   }

   private static void N(class_4184 var0, long var1) {
      class_310 var3 = class_310.method_1551();
      class_638 var4 = var3.field_1687;
      if (var4 != null) {
         float var5 = (Float)var0.method_75756().method_75689(class_12206.field_64340, h) * (float) (Math.PI / 180.0);
         float var6 = (Float)var0.method_75756().method_75689(class_12206.field_64341, h) * (float) (Math.PI / 180.0);
         float var7 = class_3532.method_15362(var5);
         float var8 = class_3532.method_15362(var6);
         float var9 = smoothstep(-0.12F, 0.28F, var7);
         float var10 = smoothstep(-0.04F, 0.1F, var7);
         float var11 = smoothstep(-0.04F, 0.14F, var8) * (1.0F - var9);
         float var12 = 1.0F - smoothstep(0.06F, 0.55F, var7);
         float var13 = var4.method_8430(h);
         float var14 = var4.method_8478(h);
         float var15 = 1.0F - 0.3F * var13 - 0.2F * var14;
         float var16 = k ? 0.9F : 1.0F;
         boolean var17 = var7 > -0.05F;
         long var18 = 94840250696168L;
         int var35 = var18 < 0L ? -1 : 1;
         if (var17) {
            I.set(-class_3532.method_15374(var5), var7, 0.0F).normalize();
            long var21 = 94840250696167L;
            int var36 = var21 < 0L ? -1 : 1;
         } else {
            I.set(-class_3532.method_15374(var6), var8, 0.0F).normalize();
         }

         y = var17 ? 1.0F : 0.0F;
         float var35x = c[3] * var15 * var16;
         float var22 = c[4] * (1.0F - 0.25F * var13);

         for (int var23 = 0; var23 < 3; var23++) {
            float var24 = d[15 + var23] * var22;
            R[var23] = var24 + (d[var23] * var35x - var24) * var9;
            m[var23] = var24 + (d[3 + var23] * var35x - var24) * var9;
            J[var23] = var24 * 1.15F + (d[6 + var23] * var35x - var24 * 1.15F) * var9;
            float var25 = d[9 + var23];
            float var26 = d[12 + var23];
            long var27 = 94840250696168L;
            int var30 = var27 < 0L ? -1 : 1;
            float var30x = (var23 == 0 ? 0.55F : (var23 == 1 ? 0.62F : 0.86F)) * 0.14F;
            float var31;
            if (var17) {
               long var32 = 94840250696167L;
               int var32x = var32 < 0L ? -1 : 1;
               var31 = (var25 + (var26 - var25) * var12) * var10 * (1.0F - 0.8F * var13) * (k ? 0.55F : 1.0F);
            } else {
               var31 = var30x * var11 * (1.0F - 0.85F * var13);
            }

            z7[var23] = var31;
         }

         long var36 = 94840250696168L;
         int var37 = var36 < 0L ? -1 : 1;
         l = SkyPipelineRouter.skyPresence();
      }
   }

   private static boolean N(int var0, class_310 var1, char var2, short var3, class_638 var4) {
      if (var4.method_8597().comp_5220() != class_12326.field_64386) {
         return false;
      } else {
         class_4184 var5 = var1.field_1773.method_19418();
         return var5.method_19334() != class_5636.field_27888
            ? false
            : !(var5.method_19331() instanceof class_1309 var6 && (var6.method_6059(class_1294.field_5919) || var6.method_6059(class_1294.field_38092)));
      }
   }

   private static void f(long var0, class_310 var2, class_638 var3, float var4) {
      long var5 = 103571382159963L;
      short var7 = 24114;
      char var8 = '靱';
      int var9 = (int)(var5 << 48 >>> 48);
      long var10 = 94574622851028L;
      HazeModule var12 = t;
      boolean var13 = var12.enabled() && N(var7, var2, var8, (short)var9, var3);
      long var14 = 60721615607852L;
      int var38 = var14 < 0L ? -1 : 1;
      if (var13) {
         V = Math.min(1.0F, V + var4 / 3.0F);
         long var17 = 60721615607851L;
         int var19 = var17 < 0L ? -1 : 1;
      } else {
         V = Math.max(0.0F, V - var4 / (var12.enabled() ? 0.35F : 2.2F));
      }

      float var38x = var3.method_8314(class_1944.field_9284, class_2338.method_49637(Q, o, Z)) / 15.0F;
      float var18 = C ? 1.0F - (float)Math.exp(-var4 * 4.0) : 1.0F;
      float var19 = C ? 1.0F - (float)Math.exp(-var4 * 2.5) : 1.0F;
      float var20 = C ? 1.0F - (float)Math.exp(-var4 * 1.6) : 1.0F;
      B = B + (smoothstep(0.15F, 0.7F, var38x) - B) * var20;
      float var21 = V * V * (3.0F - 2.0F * V);
      long var22 = 60721615607851L;
      int var25 = var22 < 0L ? -1 : 1;
      S = var21 * B;
      C = true;
      zX = zX + (var12.U.floatValue() - zX) * var18;
      F = F + (var12.Q.floatValue() - F) * var18;
      g = g + (var12.s.floatValue() - g) * var18;
      a = a + (var12.V.floatValue() - a) * var18;
      A = A + (var12.k.floatValue() - A) * var18;
      D = D + ((var12.u.enabled() ? 1.0F : 0.0F) - D) * var18;
      short var25x = 22579;
      HazeCatalog var26 = HazeCatalog.byName((String)var12.H.get(), var12.d.argb());
      var5 = 6578993950584502566L;
      int[] var27 = new int[]{var26.R, var26.c, var26.r, var26.E, var26.N, var26.n};

      for (int var28 = 0; var28 < 6; var28++) {
         long var29 = 60721615607852L;
         int var29x = var29 < 0L ? -1 : 1;

         for (int var32 = 0; var32 < 3; var32++) {
            float var33 = HazeCatalog.channel(var27[var28], var32);
            long var34 = 60721615607852L;
            int var33x = var34 < 0L ? -1 : 1;
            d[var28 * 3 + var32] = d[var28 * 3 + var32] + (var33 - d[var28 * 3 + var32]) * var19;
         }

         long var40x = 60721615607852L;
         int var42 = var40x < 0L ? -1 : 1;
      }

      b = b + (var26.L - b) * var19;
      long var39 = 60721615607852L;
      int var28 = var39 < 0L ? -1 : 1;
      N(var3, var4, var10);
   }

   private static void N(class_638 var0, float var1, long var2) {
      long var4 = 94574622851027L;
      int var7 = var4 < 0L ? -1 : 1;
      w += var1;
      long var7x = 94574622851027L;
      int var10 = var7x < 0L ? -1 : 1;
      if (--u <= 0 || !G) {
         u = 4;
         float var10x = w;
         short var11 = 5663;
         w = 0.0F;
         int var12 = class_3532.method_15357(Q);
         int var13 = class_3532.method_15357(Z);
         double var14 = 0.0;
         int var16 = 0;
         byte var17 = -48;

         while (var17 <= 48) {
            long var18 = 94574622851028L;
            int var18x = var18 < 0L ? -1 : 1;

            for (byte var20 = -48; var20 <= 48; var20 = (byte)(var20 + 12)) {
               int var21 = var12 + var20;
               int var22 = var13 + var17;
               long var23 = 94574622851027L;
               int var23x = var23 < 0L ? -1 : 1;
               long var25x = 94574622851027L;
               if (var0.method_8393(var21 >> 4, var22 >> 4)) {
                  var14 += var0.method_8624(class_2903.field_13203, var21, var22);
                  var16++;
               }
            }

            var17 = (byte)(var17 + 12);
            long var28 = 94574622851028L;
            int var33 = var28 < 0L ? -1 : 1;
         }

         long var27 = 94574622851028L;
         int var31 = var27 < 0L ? -1 : 1;
         if (var16 == 0) {
            if (!G) {
               O = (float)o - 1.6F;
            }
         } else {
            float var29 = (float)(var14 / var16);
            O = G ? O + (var29 - O) * (1.0F - (float)Math.exp(-var10x * 0.6F)) : var29;
            G = true;
         }
      }
   }

   private static void u(float var0) {
      float var1 = a * 0.8F;
      double var2 = U * 0.013 + 0.9 * Math.sin(U * 0.019);
      double var4 = 0.75 + 0.25 * Math.sin(U * 0.037 + 1.3);
      v = (v + Math.cos(var2) * var4 * var1 * var0) % 32768.0;
      s = (s + Math.sin(var2) * var4 * var1 * var0) % 32768.0;
   }

   static float sigma() {
      return c[0] * (float)Math.pow(zX, 1.2);
   }

   static float clearRadius() {
      float var0 = 1.0F - V * V * (3.0F - 2.0F * V);
      return g + var0 * var0 * 160.0F;
   }

   static float smoothstep(float var0, float var1, float var2) {
      float var3 = Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

   public static void install(HazeModule var0, ModuleContext var1) {
      t = var0;
      p = var1;
   }

   public static void metric(String var0) {
      if (p != null) {
         p.metric(var0, var0.equals("drawn") ? ++E : ++x);
      }
   }

   public static void uninstall() {
      e = null;
      M = false;
      S = 0.0F;
      t = null;
      p = null;
      z = 0L;
      C = false;
   }

   public static void ensureSourceClassInitialized() {
   }
}
