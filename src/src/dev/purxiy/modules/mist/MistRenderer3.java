// Sourced By DevFFNightmare
package dev.purxiy.modules.mist;

import dev.purxiy.assets.IrisCompat;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_12206;
import net.minecraft.class_1294;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_5636;
import net.minecraft.class_638;
import net.minecraft.class_2874.class_12326;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;

public final class MistRenderer3 {
   private static final float N = 420.0F;
   private static final float j = 3.2F;
   private static final float e = 2.2F;
   static final Matrix4f o = new Matrix4f();
   static final Matrix4f A = new Matrix4f();
   static double y;
   static double f;
   static double H;
   static float X = 0.05F;
   static float D = 512.0F;
   static float n;
   private static boolean x;
   static boolean t;
   static boolean U;
   static boolean W;
   static final MistCodec2 v = new MistCodec2();
   static final MistCodec O = new MistCodec();
   static final Vector3f i = new Vector3f(0.0F, 1.0F, 0.0F);
   static final float[] Y = new float[3];
   static final float[] a = new float[3];
   static final float[] K = new float[3];
   static float C;
   static float J;
   static float h;
   private static float z;
   static float Q = 0.5F;
   static float s = 10.0F;
   static float c = 6.0F;
   static float V = 1.0F;
   static float S = 0.6F;
   static float q = 0.7F;
   static float G = 1.0F;
   private static final float[] T = new float[15];
   private static boolean u;
   static double l;
   static double B;
   static double L;
   static double w;
   private static long F = 0L;
   
   private static class_638 p;
   private static boolean M;
   static int R = 0;
   static final float[] b = new float[]{0.72F, 0.34F, 0.22F, 4.0F, 0.075F, 1.5F, 1.0F, 0.22F, 10.0F};
   
   private static String E;
   static MistModule m;
   private static ModuleContext k;
   private static int g = 0;
   private static int Z = 0;
   public static boolean P;


   private static final long[] I = new long[]{
      -4597359564574214087L,
      2176695072158442132L,
      -696569392566902949L,
      6709408014652848905L,
      8868161466644488750L,
      8299870112899753603L,
      6199825180029863506L,
      -3989962353554177113L
   };


   private MistRenderer3() {
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2) {
      long var3 = 79377776194864L;
      long var5 = var3 ^ 35792074431805L;
      W = false;
      U = false;
      if (!ModuleWidget.claimed("mirror-capture")) {
         class_310 var7 = class_310.method_1551();
         x = (h > 0.0F || m.enabled()) && !var7.field_1773.method_35765();
         if (x) {
            o.set(var2).mul(var1);
            A.set(o).invert();
            float var8 = var2.m22();
            float var9 = var2.m32();
            X = var9 / (var8 - 1.0F);
            D = var9 / (var8 + 1.0F);
            class_243 var10 = var0.method_71156();
            y = var10.field_1352;
            f = var10.field_1351;
            H = var10.field_1350;
            n = var0.method_55437();
            t = IrisCompat.inUse();
            if (t) {
               ClientSupport3.wantHandMask();
            }

            e(var5, var0);
         }
      }
   }

   static void keepDepth(boolean var0) {
      long var1 = 81584196947852L;
      long var3 = var1 ^ 5399358932420L;
      if (x && !(h <= 0.0F) && !(G <= 0.001F) && !ModuleWidget.claimed("mirror-capture")) {
         try {
            MistRenderer.keepDepth(var0);
            if (var0) {
               W = true;
            } else {
               U = true;
            }
         } catch (Throwable var6) {
            metric("draw_failures");
            i(var6, var3);
         }
      }
   }

   public static void render() {
      long var0 = 85555118119669L;
      long var2 = var0 ^ 3391776854205L;
      long var4 = var0 ^ 122428023674240L;
      if (!ModuleWidget.claimed("mirror-capture")) {
         boolean var6 = x;
         x = false;
         class_310 var7 = class_310.method_1551();
         long var8 = System.nanoTime();
         float var10 = F == 0L ? 0.0F : Math.min((float)(var8 - F) / 1.0E9F, 0.1F);
         F = var8;
         class_638 var11 = var7.field_1687;
         if (var6 && var11 != null) {
            if (var11 != p) {
               p = var11;
               v.forget();
               O.forget();
            }

            float var12 = var7.method_1493() ? 0.0F : var10;
            w += var12;
            d(var7, var11, var10, var4);
            L(var12);
            if (!(h <= 0.001F)) {
               try {
                  v.update(var11, y, f, H, var10);
                  if (q > 0.001F) {
                     O.update(var11, y, f, H, var10);
                  }

                  MistRenderer.draw(var7);
               } catch (Throwable var14) {
                  metric("draw_failures");
                  i(var14, var2);
               }
            }
         } else {
            F = 0L;
            if (!m.enabled() || var11 == null) {
               z = 0.0F;
               h = 0.0F;
            }
         }
      }
   }

   private static void i(Throwable var0, long var1) {
      String var3 = var0.toString();
      long var4 = 86700085414471L;
      if (86700085414471L == 0L) {
         boolean var10000 = false;
      } else {
         int var9 = var4 < 0L ? -1 : 1;
      }

      long var7 = 86700085414471L;
      if (86700085414471L == 0L) {
         boolean var10 = false;
      } else {
         int var11 = var7 < 0L ? -1 : 1;
      }

      if (!var3.equals(E)) {
         E = var3;
         ClientServices.LOGGER.error("Purxiy mist failed", var0);
      }
   }

   private static void e(long var0, class_4184 var2) {
      class_310 var3 = class_310.method_1551();
      class_638 var4 = var3.field_1687;
      if (var4 != null) {
         float var5 = var2.method_75756().method_75689(class_12206.field_64340, n) * (float) (Math.PI / 180.0);
         float var6 = var2.method_75756().method_75689(class_12206.field_64341, n) * (float) (Math.PI / 180.0);
         float var7 = class_3532.method_15362(var5);
         float var8 = class_3532.method_15362(var6);
         float var9 = smoothstep(-0.1F, 0.3F, var7);
         float var10 = smoothstep(-0.04F, 0.1F, var7);
         float var11 = smoothstep(-0.04F, 0.14F, var8) * (1.0F - var9);
         float var12 = 1.0F - smoothstep(0.06F, 0.55F, var7);
         float var13 = var4.method_8430(n);
         float var14 = var4.method_8478(n);
         float var15 = 1.0F - 0.38F * var13 - 0.22F * var14;
         int var16 = var2.method_75756().method_75689(class_12206.field_63742, n);
         var16 = ClientSupport3.fogColor(var4, var16);
         float var17 = (var16 >> 16 & 0xFF) / 255.0F;
         float var18 = (var16 >> 8 & 0xFF) / 255.0F;
         float var19 = (var16 & 0xFF) / 255.0F;
         float var20 = Math.max(0.2126F * var17 + 0.7152F * var18 + 0.0722F * var19, 0.02F);
         boolean var21 = var7 > -0.05F;
         long var22 = 115160585267212L;
         if (115160585267212L == 0L) {
            boolean var10000 = false;
         } else {
            int var49 = var22 < 0L ? -1 : 1;
         }

         if (var21) {
            i.set(-class_3532.method_15374(var5), var7, 0.0F).normalize();
            long var25 = 115160585267212L;
            if (115160585267212L == 0L) {
               boolean var50 = false;
            } else {
               int var51 = var25 < 0L ? -1 : 1;
            }
         } else {
            i.set(-class_3532.method_15374(var6), var8, 0.0F).normalize();
         }

         for (int var47 = 0; var47 < 3; var47++) {
            float var26 = T[var47];
            float var27 = T[3 + var47];
            float var28 = T[6 + var47];
            float var29 = T[9 + var47];
            float var30 = var29 + (var26 - var29) * var9;
            long var31 = 115160585267213L;
            if (115160585267213L == 0L) {
               boolean var52 = false;
            } else {
               int var53 = var31 < 0L ? -1 : 1;
            }

            float var34 = (var47 == 0 ? var17 : (var47 == 1 ? var18 : var19)) / var20;
            var30 *= 1.0F + (Math.clamp(var34, 0.6F, 1.5F) - 1.0F) * 0.15F * var9;
            long var35 = 115160585267213L;
            if (115160585267213L == 0L) {
               boolean var54 = false;
            } else {
               int var55 = var35 < 0L ? -1 : 1;
            }

            Y[var47] = var30 * (0.55F + 0.45F * var15) * b[0] * (t ? 0.9F : 1.0F);
            long var38 = 115160585267212L;
            if (115160585267212L == 0L) {
               boolean var56 = false;
            } else {
               int var57 = var38 < 0L ? -1 : 1;
            }

            float var40 = (var47 == 0 ? 0.55F : (var47 == 1 ? 0.62F : 0.86F)) * 0.16F;
            float var41;
            if (var21) {
               float var42 = (var27 + (var28 - var27) * var12) * var10;
               long var43 = 115160585267213L;
               if (115160585267213L == 0L) {
                  boolean var58 = false;
               } else {
                  int var59 = var43 < 0L ? -1 : 1;
               }

               var41 = var42 * (1.0F - 0.78F * var13) * b[1] * (t ? 0.7F : 1.0F);
            } else {
               var41 = var40 * var11 * (1.0F - 0.85F * var13);
            }

            a[var47] = var41;
            K[var47] = T[12 + var47] * b[8] * (1.0F - 0.65F * var9);
         }
      }
   }

   private static boolean M(class_310 var0, class_638 var1, long var2) {
      if (var1.method_8597().comp_5220() != class_12326.field_64386) {
         return false;
      } else {
         class_4184 var4 = var0.field_1773.method_19418();
         return var4.method_19334() != class_5636.field_27888
            ? false
            : !(var4.method_19331() instanceof class_1309 var5 && (var5.method_6059(class_1294.field_5919) || var5.method_6059(class_1294.field_38092)));
      }
   }

   private static void d(class_310 var0, class_638 var1, float var2, long var3) {
      long var5 = 44212217249717L;
      MistModule var7 = m;
      boolean var8 = var7.enabled() && M(var0, var1, var5);
      long var9 = 38032550924148L;
      if (38032550924148L == 0L) {
         boolean var10000 = false;
      } else {
         int var35 = var9 < 0L ? -1 : 1;
      }

      if (var8) {
         z = Math.min(1.0F, z + var2 / 3.2F);
      } else {
         z = Math.max(0.0F, z - var2 / (var7.enabled() ? 0.35F : 2.2F));
      }

      long var12 = 38032550924148L;
      if (38032550924148L == 0L) {
         boolean var36 = false;
      } else {
         int var37 = var12 < 0L ? -1 : 1;
      }

      float var14 = z * z;
      float var15 = 3.0F - 2.0F * z;
      long var16 = 38032550924148L;
      if (38032550924148L == 0L) {
         boolean var38 = false;
      } else {
         int var39 = var16 < 0L ? -1 : 1;
      }

      h = var14 * var15;
      float var19 = u ? 1.0F - (float)Math.exp(-var2 * 4.0) : 1.0F;
      float var20 = u ? 1.0F - (float)Math.exp(-var2 * 2.5) : 1.0F;
      u = true;
      Q = Q + (var7.U.floatValue() - Q) * var19;
      long var21 = 38032550924148L;
      if (38032550924148L == 0L) {
         boolean var40 = false;
      } else {
         int var41 = var21 < 0L ? -1 : 1;
      }

      s = s + (var7.l.floatValue() - s) * var19;
      c = c + (var7.E.floatValue() - c) * var19;
      V = V + (var7.H.floatValue() - V) * var19;
      S = S + (var7.I.floatValue() - S) * var19;
      q = q + (var7.Y.floatValue() - q) * var19;
      G = G + ((var7.e.enabled() ? 1.0F : 0.0F) - G) * var19;
      MistCatalog var23 = MistCatalog.byName(var7.P.get());
      int[] var24 = new int[]{var23.N, var23.h, var23.I, var23.U, var23.p};

      for (int var25 = 0; var25 < 5; var25++) {
         long var26 = 38032550924148L;
         if (38032550924148L == 0L) {
            boolean var42 = false;
         } else {
            int var43 = var26 < 0L ? -1 : 1;
         }

         for (int var28 = 0; var28 < 3; var28++) {
            int var29 = var24[var25];
            long var30 = 38032550924149L;
            if (38032550924149L == 0L) {
               boolean var44 = false;
            } else {
               int var45 = var30 < 0L ? -1 : 1;
            }

            float var32 = MistCatalog.channel(var29, var28);
            T[var25 * 3 + var28] = T[var25 * 3 + var28] + (var32 - T[var25 * 3 + var28]) * var20;
         }

         long var34 = 38032550924149L;
         if (38032550924149L == 0L) {
            boolean var46 = false;
         } else {
            int var47 = var34 < 0L ? -1 : 1;
         }
      }

      long var33 = 38032550924149L;
      if (38032550924149L == 0L) {
         boolean var48 = false;
      } else {
         int var49 = var33 < 0L ? -1 : 1;
      }

      C = S * (t ? 0.5F : 1.0F);
      J = S;
   }

   private static void L(float var0) {
      float var1 = V * 0.55F;
      double var2 = w * 0.011 + 0.8 * Math.sin(w * 0.017);
      double var4 = 0.75 + 0.25 * Math.sin(w * 0.041 + 1.7);
      l = (l + Math.cos(var2) * var4 * var1 * var0) % 8192.0;
      B = (B + Math.sin(var2) * var4 * var1 * var0) % 8192.0;
      L = (L + 0.03 * V * var0) % 256.0;
   }

   static float sigma() {
      return b[4] * Q * (0.4F + 0.6F * Q);
   }

   static float clearRadius() {
      float var0 = 1.0F - h;
      return c + var0 * var0 * 140.0F;
   }

   static float skyReach() {
      return 420.0F;
   }

   static float smoothstep(float var0, float var1, float var2) {
      float var3 = Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

   public static void install(MistModule var0, ModuleContext var1) {
      m = var0;
      k = var1;
   }

   public static void metric(String var0) {
      if (k != null) {
         k.metric(var0, var0.equals("drawn") ? ++g : ++Z);
      }
   }

   public static void uninstall() {
      v.forget();
      O.forget();
      p = null;
      x = false;
      h = 0.0F;
      m = null;
      k = null;
      F = 0L;
      u = false;
   }

public static void ensureSourceClassInitialized() {
   }

}
