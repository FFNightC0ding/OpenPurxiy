// Sourced By DevFFNightmare
package dev.purxiy.modules.ambience;

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
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import dev.purxiy.render.pipeline.SkyPipelines;
import dev.purxiy.render.pipeline.SkyVertexFormat;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.OptionalLong;
import net.minecraft.class_12206;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_5636;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_9909;
import net.minecraft.class_9916;
import net.minecraft.class_9925;
import net.minecraft.class_9960;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryUtil;

public final class AmbienceRegistry {
   private static final float G = 1.6F;
   private static final float d = 15.0F;
   private static final float V = 0.3F;
   private static final float g = 0.9F;
   private static final int B = 48;
   private static final int J = SkyVertexFormat.AMBIENCE_VERTEX_SIZE;
   private static final int A = J * 4;
   private static final AmbienceUpdater Y = new AmbienceUpdater();
   private static final AmbienceUpdater.TypeV I = new AmbienceUpdater.TypeV();
   private static long s = 0L;
   private static float P;
   private static float F;
   private static float a;
   private static float v = 1.0F;
   private static class_638 Z;
   private static double n;
   private static double X;
   private static double T;
   private static float r;
   private static boolean K;
   private static float p;
   private static float R;
   private static float S;
   private static float N;
   private static float y;
   private static float M;
   private static float b;
   private static final float[] l = new float[9];
   private static final float[] k = new float[9];
   private static long[] t = new long[256];
   private static ByteBuffer C;
   private static int c = 0;
   private static GpuBuffer U;
   private static GpuBuffer O;
   private static ByteBuffer u;
   private static String h;
   private static boolean m;
   private static GpuQuery e;
   private static double i;
   private static double Q;
   private static int q = 0;
   static AmbienceModule W;
   private static ModuleContext L;
   private static int j = 0;
   private static int z = 0;
   public static int x = 0;

   private static final String[] o = new String[16];
   private static final long[] w = new long[]{
      -5433191076464176614L,
      8448080549814548924L,
      462813104044987330L,
      5074669103227987654L,
      96020205199960586L,
      5935390806652710667L,
      -3199421331659794739L,
      1387469284824346886L,
      -9080625299399089061L,
      8037418397990552881L,
      -398992637938701669L,
      -2866155072356942311L,
      -4912657963292501380L,
      2464311792359058025L
   };
   private static final Integer[] D = new Integer[14];
   private static final long[] E = new long[]{
      4497587307087353947L,
      8053668246649208137L,
      3903558501604860893L,
      614715820938845701L,
      5111927599462605077L,
      6630443850951239176L,
      68133922690782526L,
      1152609040160909081L,
      6940672879420230919L,
      3107745347898168124L,
      5466256652740285025L,
      7170161333344282280L,
      5091197085590315760L,
      5149318101408680550L,
      548218878500742654L,
      1127318469589983030L,
      8328258359665140584L,
      3932950889529805758L,
      2791942227014357880L
   };
   private static final Long[] H = new Long[19];

   private AmbienceRegistry() {
   }

   public static void addPass(class_9909 var0, class_9960 var1, GpuBufferSlice var2) {
      long var3 = 91531811038056L;
      long var5 = var3 ^ 65798955726390L;
      long var7 = var3 ^ 7713252422681L;
      long var9 = var3 ^ 36457908052735L;
      long var11 = var3 ^ 44731147092607L;
      if (!ModuleWidget.claimed("mirror-capture")) {
         long var13 = System.nanoTime();
         boolean var15 = s != 0L && var13 - s < 500000000L;
         float var16 = var15 ? Math.min((float)(var13 - s) / 1.0E9F, 0.1F) : 0.0F;
         s = var13;
         P += var16;
         class_310 var17 = class_310.method_1551();
         class_638 var18 = var17.field_1687;
         if (var18 != Z) {
            Y.clear();
            Z = var18;
            K = false;
         }

         AmbienceModule var19 = W;
         float var20 = var19.enabled() ? 1.0F : 0.0F;
         F = var15 ? o(F, var11, var20, var16 / 1.6F) : var20;
         a = F * F * (3.0F - 2.0F * F);
         if (var18 != null && !(a <= 0.0F)) {
            class_4184 var21 = var17.field_1773.method_19418();
            float var22 = var17.method_61966().method_60637(false);
            v = o(v, var11, var21.method_19334() == class_5636.field_27888 ? 1.0F : 0.0F, K ? var16 / 0.4F : 1.0F);
            AmbienceCatalog var23 = AmbienceCatalog.byName(var19.s.get());
            r(var7, var19, var16);
            W(var18, var21, var19, var22, var16, var9);
            Y.populate(var23, r(var23, var19.h.floatValue()));
            Y.update(I, var16);
            K = true;
            c = v > 0.0F ? A(var5, var21) : 0;
            if (m) {
               Q = Q + (System.nanoTime() - var13) / 1000000.0;
            }

            if (c != 0 && s()) {
               class_9916 var24 = var0.method_61911("purxiy_ambience");
               class_9925 var25;
               if (var1.field_53094 != null) {
                  var1.field_53094 = var24.method_61933(var1.field_53094);
                  var24.method_61928(var1.field_53091);
                  var25 = var1.field_53094;
               } else {
                  var1.field_53091 = var24.method_61933(var1.field_53091);
                  var25 = var1.field_53091;
               }

               class_9925 var26 = var1.field_53091;
               int var27 = c;
               var24.method_61929(() -> {
                  long var4 = 31973499809363L;
                  long var6 = var4 ^ 8944888768681L;
                  RenderSystem.setShaderFog(var2);
                  p((class_276)var25.get(), var6, (class_276)var26.get(), var27);
               });
            }
         } else {
            Y.clear();
            K = false;
         }
      }
   }

   private static void r(long var0, AmbienceModule var2, float var3) {
      long var4 = 15310865373507L;
      AmbienceLoader var6 = AmbienceLoader.resolve(var2.z.get(), var2.F.argb());
      S(k, 0, var6.B, var4);
      S(k, 3, var6.D, var4);
      S(k, 6, var6.d, var4);
      float var7 = K ? 1.0F - (float)Math.exp(-var3 * 3.5) : 1.0F;

      for (int var8 = 0; var8 < l.length; var8++) {
         l[var8] = l[var8] + (k[var8] - l[var8]) * var7;
         long var9 = 92618968962929L;
         if (92618968962929L == 0L) {
            boolean var10000 = false;
         } else {
            int var19 = var9 < 0L ? -1 : 1;
         }
      }

      p = p + (var2.E.floatValue() - p) * var7;
      float var16 = R;
      float var18 = var2.e.floatValue();
      long var10 = 92618968962928L;
      if (92618968962928L == 0L) {
         boolean var20 = false;
      } else {
         int var21 = var10 < 0L ? -1 : 1;
      }

      var16 += (var18 - R) * var7;
      long var13 = 92618968962928L;
      if (92618968962928L == 0L) {
         boolean var22 = false;
      } else {
         int var23 = var13 < 0L ? -1 : 1;
      }

      R = var16;
      S = S + (var2.w.floatValue() - S) * var7;
      N = N + (var2.v.floatValue() - N) * var7;
      y = y + (var2.g.floatValue() - y) * var7;
      M = M + (var2.P.floatValue() - M) * var7;
      b = b + ((var2.y.enabled() ? 1.0F : 0.0F) - b) * var7;
   }

   private static void W(class_638 var0, class_4184 var1, AmbienceModule var2, float var3, float var4, long var5) {
      AmbienceUpdater.TypeV var7 = I;
      class_243 var8 = var1.method_71156();
      var7.Y = var0;
      var7.s = var8.field_1352;
      var7.h = var8.field_1351;
      var7.G = var8.field_1350;
      var7.g = y;
      long var9 = 125447098433943L;
      if (125447098433943L == 0L) {
         boolean var10000 = false;
      } else {
         int var34 = var9 < 0L ? -1 : 1;
      }

      var7.n = Math.max(4.0F, y * 0.38F);
      var7.P = P;
      var7.o = S;
      var7.z = var0.method_8430(var3);
      var7.N = M;
      var7.w = var2.t.enabled();
      var7.c = 0.0F;
      if (var0.method_8597().comp_642()) {
         float var12 = var1.method_75756().method_75689(class_12206.field_64340, var3) * (float) (Math.PI / 180.0);
         var7.c = AmbienceUpdater.smoothstep(-0.12F, 0.32F, class_3532.method_15362(var12));
      }

      float var28 = P * 0.013F + 0.9F * class_3532.method_15374(P * 0.031F);
      float var13 = (0.65F + 0.35F * class_3532.method_15374(P * 0.047F + 1.3F)) * (1.0F + 0.8F * var7.z);
      var7.Z = class_3532.method_15362(var28) * var13;
      var7.W = class_3532.method_15374(var28) * var13;
      var7.M = 0;
      double var14 = y + 4.0;

      for (class_1657 var17 : var0.method_18456()) {
         long var18 = 125447098433942L;
         if (125447098433942L == 0L) {
            boolean var35 = false;
         } else {
            int var36 = var18 < 0L ? -1 : 1;
         }

         if (var7.M == 8) {
            break;
         }

         class_243 var21 = var17.method_30950(var3);
         boolean var22 = var17.method_7325();
         long var23 = 125447098433943L;
         if (125447098433943L == 0L) {
            boolean var37 = false;
         } else {
            int var38 = var23 < 0L ? -1 : 1;
         }

         if (!var22) {
            if (var21.method_1025(var8) > var14 * var14) {
               long var26 = 125447098433942L;
               if (125447098433942L == 0L) {
                  boolean var39 = false;
               } else {
                  int var40 = var26 < 0L ? -1 : 1;
               }
            } else {
               int var33 = var7.M++;
               var7.y[var33] = var21.field_1352;
               var7.F[var33] = var21.field_1351;
               var7.I[var33] = var21.field_1350;
               var7.t[var33] = var17.method_17682();
               var7.C[var33] = (float)(var17.method_23317() - var17.field_6014) * 20.0F;
               var7.r[var33] = (float)(var17.method_23318() - var17.field_6036) * 20.0F;
               var7.L[var33] = (float)(var17.method_23321() - var17.field_5969) * 20.0F;
            }
         }
      }

      long var29 = 125447098433942L;
      if (125447098433942L == 0L) {
         boolean var41 = false;
      } else {
         int var42 = var29 < 0L ? -1 : 1;
      }

      class_746 var30 = class_310.method_1551().field_1724;
      boolean var19 = var30 != null
         && Math.abs(var30.method_23317() - var30.field_6014)
               + Math.abs(var30.method_23318() - var30.field_6036)
               + Math.abs(var30.method_23321() - var30.field_5969)
            < 0.01;
      r = var19 ? r + var4 : 0.0F;
      var7.d = var2.N.enabled() ? AmbienceUpdater.smoothstep(2.0F, 6.0F, r) : 0.0F;
      if (var30 != null) {
         class_243 var20 = var30.method_30950(var3);
         var7.V = var20.field_1352;
         var7.l = var20.field_1351;
         var7.H = var20.field_1350;
      }

      double var31 = Math.max(Math.abs(var8.field_1352 - n), Math.max(Math.abs(var8.field_1351 - X), Math.abs(var8.field_1350 - T)));
      long var32 = 125447098433942L;
      if (125447098433942L == 0L) {
         boolean var43 = false;
      } else {
         int var44 = var32 < 0L ? -1 : 1;
      }

      if (K && var31 > Math.max(8.0, y * 0.5)) {
         Y.scatter();
      }

      n = var8.field_1352;
      X = var8.field_1351;
      T = var8.field_1350;
   }

   private static int r(AmbienceCatalog var0, float var1) {
      return Math.round(I.g * 15.0F * var0.C * var1);
   }

   private static int A(long var0, class_4184 var2) {
      long var3 = 92658581524580L;
      long var5 = 64542734552944L;
      AmbienceUpdater var7 = Y;
      Vector3fc var8 = var2.method_19335();
      float var9 = var8.x();
      float var10 = var8.y();
      float var11 = var8.z();
      int var12 = t.length;
      long var13 = 115343132775773L;
      if (115343132775773L == 0L) {
         boolean var10000 = false;
      } else {
         int var39 = var13 < 0L ? -1 : 1;
      }

      if (var12 < var7.W) {
         t = new long[Math.max(var7.W, t.length * 2)];
      }

      int var16 = 0;

      for (int var17 = 0; var17 < var7.W; var17++) {
         float var18;
         var12 = (var18 = var7.Y[var17] - 0.003F) == 0.0F ? 0 : (var18 < 0.0F ? -1 : 1);
         long var19 = 115343132775774L;
         if (115343132775774L == 0L) {
            boolean var40 = false;
         } else {
            int var41 = var19 < 0L ? -1 : 1;
         }

         if (var12 <= 0) {
            long var22 = 115343132775773L;
            if (115343132775773L == 0L) {
               boolean var42 = false;
            } else {
               int var43 = var22 < 0L ? -1 : 1;
            }
         } else {
            float var35 = (float)(var7.H[var17] - I.s);
            float var23 = (float)(var7.A[var17] - I.h);
            float var24 = (float)(var7.F[var17] - I.G);
            float var25 = var35 * var9 + var23 * var10 + var24 * var11;
            float var26;
            var12 = (var26 = var25 - ((float)Math.sqrt(var35 * var35 + var23 * var23 + var24 * var24) * 0.17F - 0.6F)) == 0.0F ? 0 : (var26 < 0.0F ? -1 : 1);
            long var27 = 115343132775774L;
            if (115343132775774L == 0L) {
               boolean var44 = false;
            } else {
               int var45 = var27 < 0L ? -1 : 1;
            }

            if (var12 < 0) {
               long var29 = 115343132775773L;
               if (115343132775773L == 0L) {
                  boolean var46 = false;
               } else {
                  int var47 = var29 < 0L ? -1 : 1;
               }
            } else {
               t[var16++] = (long)Float.floatToRawIntBits(Math.max(var25, 0.0F)) << 32 | var17;
            }
         }
      }

      long var33 = 115343132775774L;
      if (115343132775774L == 0L) {
         boolean var48 = false;
      } else {
         int var49 = var33 < 0L ? -1 : 1;
      }

      if (var16 == 0) {
         return 0;
      } else {
         Arrays.sort(t, 0, var16);
         Z(var16, var3);
         long var34 = MemoryUtil.memAddress(C);
         float var21 = a * v;

         for (int var36 = 0; var36 < var16; var36++) {
            I(var34 + (long)var36 * A, var5, (int)t[var16 - 1 - var36], var21);
            long var38 = 115343132775773L;
            if (115343132775773L == 0L) {
               boolean var50 = false;
            } else {
               int var51 = var38 < 0L ? -1 : 1;
            }
         }

         long var37 = 115343132775774L;
         if (115343132775774L == 0L) {
            boolean var52 = false;
         } else {
            int var53 = var37 < 0L ? -1 : 1;
         }

         C.clear().limit(var16 * A);
         return var16;
      }
   }

   private static void I(long var0, long var2, int var4, float var5) {
      long var6 = 139215199988189L;
      AmbienceUpdater var8 = Y;
      AmbienceCatalog var9 = AmbienceCatalog.e.get(var8.k[var4]);
      float var10 = var8.Y[var4] * var5;
      if (var9.F == 0 || var9.F == 1) {
         var10 *= 1.0F - 0.35F * I.z;
      }

      float var11 = var8.d[var4];
      int var12 = var11 < 0.6F ? 0 : 3;
      long var13 = 64542734552943L;
      if (64542734552943L == 0L) {
         boolean var10000 = false;
      } else {
         int var53 = var13 < 0L ? -1 : 1;
      }

      float var16 = var11 < 0.6F ? var11 / 0.6F : (var11 - 0.6F) / 0.4F;
      float var17 = l[var12] + (l[var12 + 3] - l[var12]) * var16;
      float var18 = l[var12 + 1] + (l[var12 + 4] - l[var12 + 1]) * var16;
      float var19 = l[var12 + 2] + (l[var12 + 5] - l[var12 + 2]) * var16;
      float var20 = var9.b;
      float var21 = var17 + (1.0F - var17) * var20;
      float var22 = var18 + (1.0F - var18) * var20;
      float var23 = var19 + (1.0F - var19) * var20;
      if (var9.F == 2 || var9.F == 3) {
         float var24 = Math.max(var8.hC[var4] * 0.9F, var8.D[var4] * (0.25F + 0.75F * I.c));
         float var25 = var9.F == 2 ? 0.85F : 0.7F;
         float var26 = var25 + (1.0F - var25) * var24;
         var21 *= var26;
         var22 *= var26;
         var23 *= var26;
      }

      float var42 = var8.S[var4] + P * var8.hY[var4];
      float var43 = 1.0F;
      float var44 = 0.0F;
      long var27 = 64542734552944L;
      if (64542734552944L == 0L) {
         boolean var54 = false;
      } else {
         int var55 = var27 < 0L ? -1 : 1;
      }

      switch (var9.F) {
         case 0:
            var43 = 1.0F - N * 0.55F * (0.5F + 0.5F * class_3532.method_15374(var42));
            long var30 = 64542734552943L;
            if (64542734552943L == 0L) {
               boolean var60 = false;
            } else {
               int var61 = var30 < 0L ? -1 : 1;
            }
            break;
         case 1:
            long var32 = 64542734552943L;
            if (64542734552943L == 0L) {
               boolean var58 = false;
            } else {
               int var59 = var32 < 0L ? -1 : 1;
            }

            var43 = 1.0F - N * 0.45F;
            var44 = N * 1.4F * (float)Math.pow(0.5F + 0.5F * class_3532.method_15374(var42 * 1.6F), 12.0);
            break;
         case 2:
            float var34 = 0.16F * var8.T[var4];
            long var35 = 64542734552943L;
            if (64542734552943L == 0L) {
               boolean var56 = false;
            } else {
               int var57 = var35 < 0L ? -1 : 1;
            }

            var44 = 0.58F + var34;
            break;
         case 3:
            var44 = N * 0.9F * (float)Math.pow(0.5F + 0.5F * class_3532.method_15374(var42 * 1.3F), 28.0);
      }

      float var45 = var8.r[var4] * p;
      double var31 = var8.A[var4];
      long var33 = 64542734552943L;
      if (64542734552943L == 0L) {
         boolean var62 = false;
      } else {
         int var63 = var33 < 0L ? -1 : 1;
      }

      long var47 = 64542734552944L;
      if (64542734552944L == 0L) {
         boolean var64 = false;
      } else {
         int var65 = var47 < 0L ? -1 : 1;
      }

      label178: {
         if (var9.F == 3) {
            byte var37 = var8.j[var4];
            long var38 = 64542734552944L;
            if (64542734552944L == 0L) {
               boolean var66 = false;
            } else {
               int var67 = var38 < 0L ? -1 : 1;
            }

            if (var37 == 2) {
               var45 *= (float)Math.sqrt(Math.max(0.0F, var8.h[var4] / var8.o[var4]));
               break label178;
            }
         }

         byte var50 = var8.j[var4];
         long var51 = 64542734552944L;
         if (64542734552944L == 0L) {
            boolean var68 = false;
         } else {
            int var69 = var51 < 0L ? -1 : 1;
         }

         if (var50 == 3) {
            var31 += 0.008 * class_3532.method_15374(P * 2.2F + var8.S[var4]);
         }
      }

      var33 = 64542734552943L;
      if (64542734552943L == 0L) {
         boolean var70 = false;
      } else {
         int var71 = var33 < 0L ? -1 : 1;
      }

      boolean var48 = var9.F == 2;
      MemoryUtil.memPutFloat(var0, (float)(var8.H[var4] - I.s));
      MemoryUtil.memPutFloat(var0 + 4L, (float)(var31 - I.h));
      MemoryUtil.memPutFloat(var0 + 8L, (float)(var8.F[var4] - I.G));
      MemoryUtil.memPutFloat(var0 + 12L, var45);
      long var36 = var0 + 16L;
      long var52 = 64542734552944L;
      if (64542734552944L == 0L) {
         boolean var72 = false;
      } else {
         int var73 = var52 < 0L ? -1 : 1;
      }

      MemoryUtil.memPutFloat(var36, var9.F);
      MemoryUtil.memPutFloat(var0 + 20L, var8.t[var4]);
      var36 = var0 + 24L;
      long var40 = 64542734552943L;
      if (64542734552943L == 0L) {
         boolean var74 = false;
      } else {
         int var75 = var40 < 0L ? -1 : 1;
      }

      MemoryUtil.memPutFloat(var36, var8.T[var4]);
      MemoryUtil.memPutFloat(var0 + 28L, var44);
      MemoryUtil.memPutFloat(var0 + 32L, var48 ? var8.s[var4] : 0.0F);
      MemoryUtil.memPutFloat(var0 + 36L, var48 ? var8.z[var4] : 0.0F);
      MemoryUtil.memPutFloat(var0 + 40L, var48 ? var8.L[var4] : 0.0F);
      MemoryUtil.memPutFloat(var0 + 44L, var48 ? var8.x[var4] : 1.0F);
      j(var0 + 48L, var21, var22, var6, var23, var10 * var9.p * Math.min(var43, 1.0F));
      j(var0 + 52L, var17, var18, var6, var19, var10 * var9.T * var43);
      MemoryUtil.memCopy(var0, var0 + J, J);
      MemoryUtil.memCopy(var0, var0 + J * 2L, J);
      MemoryUtil.memCopy(var0, var0 + J * 3L, J);
   }

   private static void j(long var0, float var2, float var3, long var4, float var6, float var7) {
      MemoryUtil.memPutByte(var0, (byte)q(var2));
      MemoryUtil.memPutByte(var0 + 1L, (byte)q(var3));
      MemoryUtil.memPutByte(var0 + 2L, (byte)q(var6));
      MemoryUtil.memPutByte(var0 + 3L, (byte)q(var7));
   }

   private static int q(float var0) {
      return Math.round(Math.clamp(var0, 0.0F, 1.0F) * 255.0F);
   }

   private static void Z(int var0, long var1) {
      int var3 = var0 * A;
      if (C != null) {
         int var4 = C.capacity();
         long var5 = 92658581524580L;
         if (92658581524580L == 0L) {
            boolean var10000 = false;
         } else {
            int var10 = var5 < 0L ? -1 : 1;
         }

         if (var4 >= var3) {
            return;
         }
      }

      int var8 = Math.max(var3, C == null ? 64 * A : C.capacity() * 2);
      long var9 = 92658581524579L;
      if (92658581524579L == 0L) {
         boolean var11 = false;
      } else {
         int var12 = var9 < 0L ? -1 : 1;
      }

      C = C == null ? MemoryUtil.memAlloc(var8) : MemoryUtil.memRealloc(C, var8);
   }

   private static boolean s() {
      return RenderSystem.getDevice().precompilePipeline(SkyPipelines.AMBIENCE).isValid();
   }

   private static void p(class_276 var0, long var1, class_276 var3, int var4) {
      long var5 = 51665339021557L;
      long var7 = 88307164040166L;

      try {
         GpuTextureView var9 = var0.method_71639();
         GpuTextureView var32 = var3.method_71640();
         if (var9 == null || var32 == null) {
            return;
         }

         GpuDevice var33 = RenderSystem.getDevice();
         CommandEncoder var12 = var33.createCommandEncoder();
         k(var33, var4 * A, var7);
         var12.writeToBuffer(U.slice(0L, (long)var4 * A), C);
         Std140Builder var13 = Std140Builder.intoBuffer(u.clear())
            .putVec4(0.05F, class_310.method_1551().field_1773.method_32796(), 0.3F, 0.0F)
            .putVec4(var9.getWidth(0), var9.getHeight(0), 0.9F, 0.0F)
            .putVec4(R, b, N, 0.0F);
         var12.writeToBuffer(O.slice(), var13.get());
         GpuBufferSlice var34 = RenderSystem.getDynamicUniforms()
            .method_71106(RenderSystem.getModelViewMatrix(), new Vector4f(1.0F), new Vector3f(), new Matrix4f());
         class_5590 var15 = RenderSystem.getSequentialBuffer(class_5596.field_27382);
         GpuBuffer var16 = var15.method_68274(var4 * 6);
         GpuQuery var17 = m ? M(var5, var12) : null;
         boolean var18 = false;

         try {
            var18 = true;

            try (RenderPass var19 = var12.createRenderPass(() -> "Purxiy ambience", var9, OptionalInt.empty())) {
               long var20 = 23325127722746L;
               if (23325127722746L == 0L) {
                  boolean var38 = false;
               } else {
                  int var39 = var20 < 0L ? -1 : 1;
               }

               var19.setPipeline(SkyPipelines.AMBIENCE);
               RenderSystem.bindDefaultUniforms(var19);
               var19.setUniform("DynamicTransforms", var34);
               var19.setUniform("PurxiyAmbience", O);
               var19.bindTexture("DepthSampler", var32, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
               var19.setVertexBuffer(0, U);
               var19.setIndexBuffer(var16, var15.method_31924());
               var19.drawIndexed(0, 0, var4 * 6, 1);
               metric("drawn");
            }
         } finally {
            if (var18 && var17 != null) {
               var12.timerQueryEnd(var17);
               e = var17;
            }
         }

         if (var17 != null) {
            var12.timerQueryEnd(var17);
            e = var17;
         }
      } catch (Throwable var31) {
         metric("draw_failures");
         String var10 = var31.toString();
         long var11 = 23325127722745L;
         if (23325127722745L == 0L) {
            boolean var35 = false;
         } else {
            int var10000 = var11 < 0L ? -1 : 1;
         }

         long var14 = 23325127722746L;
         if (23325127722746L == 0L) {
            boolean var36 = false;
         } else {
            int var37 = var14 < 0L ? -1 : 1;
         }

         if (!var10.equals(h)) {
            h = var10;
            ClientServices.LOGGER.error("Purxiy ambience failed to draw", var31);
         }
      }
   }

   private static void k(GpuDevice var0, int var1, long var2) {
      if (O == null) {
         O = var0.createBuffer(ClientServices.label(() -> "Purxiy ambience uniforms"), 136, 48L);
         u = MemoryUtil.memAlloc(48);
      }

      if (U != null) {
         long var4 = U.size();
         long var6 = 88307164040165L;
         if (88307164040165L == 0L) {
            boolean var10000 = false;
         } else {
            int var18 = var6 < 0L ? -1 : 1;
         }

         long var9;
         int var11 = (var9 = var4 - var1) == 0L ? 0 : (var9 < 0L ? -1 : 1);
         long var12 = 88307164040165L;
         if (88307164040165L == 0L) {
            boolean var19 = false;
         } else {
            int var20 = var12 < 0L ? -1 : 1;
         }

         if (var11 >= 0) {
            return;
         }
      }

      long var15;
      for (var15 = 65536L; var15 < var1; var15 <<= 1) {
         long var16 = 88307164040165L;
         if (88307164040165L == 0L) {
            boolean var21 = false;
         } else {
            int var22 = var16 < 0L ? -1 : 1;
         }
      }

      long var17 = 88307164040166L;
      if (88307164040166L == 0L) {
         boolean var23 = false;
      } else {
         int var24 = var17 < 0L ? -1 : 1;
      }

      if (U != null) {
         U.close();
      }

      U = var0.createBuffer(ClientServices.label(() -> "Purxiy ambience vertices"), 40, var15);
   }

   public static void setTiming(boolean var0) {
      m = var0;
      i = 0.0;
      Q = 0.0;
      q = 0;
   }

   private static GpuQuery M(long var0, CommandEncoder var2) {
      if (e != null) {
         OptionalLong var3 = e.getValue();
         long var4 = 51665339021557L;
         if (51665339021557L == 0L) {
            boolean var10000 = false;
         } else {
            int var9 = var4 < 0L ? -1 : 1;
         }

         if (var3.isEmpty()) {
            return null;
         }

         e.close();
         e = null;
         i = i + var3.getAsLong() / 1000000.0;
         long var7 = 51665339021556L;
         if (51665339021556L == 0L) {
            boolean var10 = false;
         } else {
            int var11 = var7 < 0L ? -1 : 1;
         }

         if (++q == 240) {
            ClientServices.LOGGER
               .info(
                  "Purxiy ambience: {} ms CPU, {} ms GPU per frame ({} particles, {} drawn, {})",
                  new Object[]{String.format(Locale.ROOT, "%.3f", Q / q), String.format(Locale.ROOT, "%.3f", i / q), Y.W, c, W.s.get()}
               );
            i = 0.0;
            Q = 0.0;
            q = 0;
         }
      }

      return var2.timerQueryBegin();
   }

   private static void S(float[] var0, int var1, int var2, long var3) {
      var0[var1] = (var2 >> 16 & 0xFF) / 255.0F;
      var0[var1 + 1] = (var2 >> 8 & 0xFF) / 255.0F;
      var0[var1 + 2] = (var2 & 0xFF) / 255.0F;
   }

   private static float o(float var0, long var1, float var3, float var4) {
      long var5 = 135865405219094L;
      if (135865405219094L == 0L) {
         boolean var10000 = false;
      } else {
         int var8 = var5 < 0L ? -1 : 1;
      }

      return var0 < var3 ? Math.min(var3, var0 + var4) : Math.max(var3, var0 - var4);
   }

   public static void install(AmbienceModule var0, ModuleContext var1) {
      W = var0;
      L = var1;
   }

   public static void metric(String var0) {
      if (L != null) {
         L.metric(var0, var0.equals("drawn") ? ++j : ++z);
      }
   }

   public static void uninstall() {
      int var0 = 0;

      try {
         ClientCache.release(SkyPipelines.AMBIENCE);
      } catch (Exception var72) {
         var0++;
      }

      try {
         if (e != null) {
            e.close();
         }
      } catch (Exception var70) {
         var0++;
      } finally {
         e = null;
      }

      try {
         if (U != null) {
            U.close();
         }
      } catch (Exception var68) {
         var0++;
      } finally {
         U = null;
      }

      try {
         if (O != null) {
            O.close();
         }
      } catch (Exception var66) {
         var0++;
      } finally {
         O = null;
      }

      try {
         if (C != null) {
            MemoryUtil.memFree(C);
         }
      } catch (Exception var64) {
         var0++;
      } finally {
         C = null;
      }

      try {
         if (u != null) {
            MemoryUtil.memFree(u);
         }
      } catch (Exception var62) {
         var0++;
      } finally {
         u = null;
      }

      Y.clear();
      Z = null;
      W = null;
      L = null;
      s = 0L;
      K = false;
      if (var0 != 0) {
         throw new IllegalStateException("World visual GPU cleanup failed");
      }
   }

private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 12600) & 32767;
      if (H[var3] == null) {
         H[var3] = E[var3] ^ var1;
      }

      return H[var3];
   }

   public static void ensureSourceClassInitialized() {
   }

}
