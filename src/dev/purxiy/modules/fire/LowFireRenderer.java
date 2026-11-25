package dev.purxiy.modules.fire;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
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
import dev.purxiy.render.pipeline.SkyPipelines;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.OptionalLong;
import net.minecraft.class_10017;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_12249;
import net.minecraft.class_1282;
import net.minecraft.class_1294;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_8103;
import net.minecraft.class_4587.class_4665;
import org.joml.Quaternionf;
import org.lwjgl.system.MemoryUtil;

public final class LowFireRenderer {
   static final int A = 48;
   static final int L = 96;
   private static final int W = 14;
   private static final int p = 6368;
   private static final float j = 0.085F;
   private static final float T = 0.115F;
   private static final float s = 0.92F;
   private static final float n = 1.2F;
   private static final float r = 0.23F;
   private static final float I = 0.34F;
   private static final double S = Math.PI * 2;
   private static final float ax = 140.0F;
   private static final float D = 13.0F;
   private static final float X = 30.0F;
   private static final float C = 300.0F;
   private static final float x = 1.9F;
   private static final float Y = 0.3F;
   private static final float aJ = 16.0F;
   private static long P = 0L;
   private static class_638 G;
   private static boolean q;
   private static boolean R;
   private static float v;
   private static float a;
   private static float z;
   private static float N;
   private static float k = 1.0F;
   private static float O;
   private static float ao;
   private static float aF;
   private static float J;
   private static float aP = 10.0F;
   private static int aA = 0;
   private static float a3;
   private static float B;
   private static boolean c;
   private static final LowFireCodec ar = new LowFireCodec();
   private static final float[] aE = new float[3];
   private static float K;
   private static float y;
   private static float f;
   private static final float[] au = new float[9];
   private static final float[] aS = new float[9];
   private static boolean l;
   private static GpuBuffer U;
   private static ByteBuffer M;
   private static GpuTexture h;
   private static GpuTexture aY;
   private static GpuTextureView F;
   private static GpuTextureView H;
   private static int b = 0;
   private static int g = 0;
   private static final float[] w = new float[4];
   private static final float[] o = new float[4];
   private static final float[] u = new float[768];
   private static final int[] ag = new int[2];
   private static float e;
   private static float aD;
   private static String i;
   private static String V;
   private static boolean E;
   private static GpuQuery d;
   private static double Q;
   private static double an;
   private static int t = 0;
   static LowFireModule ak;
   private static ModuleContext m;
   private static int ai = 0;
   private static int Z = 0;
   public static boolean aT;
   private static final long[] cb = new long[]{
      -4052466282704973138L,
      4222369890819697573L,
      -3123970770710942612L,
      2266650934656593801L,
      7468048283896833270L,
      3801357192865700760L,
      -2833204994123866409L,
      -3499583772049533734L,
      1670373310819586960L,
      -7732250815866569972L,
      1114882652216588417L,
      -4380242901314090058L,
      -303810614789515607L,
      -93639524710923191L,
      3784740477874400153L,
      2210045918509765143L,
      -7535666031445975808L,
      1097207643447908978L,
      8844039934696965166L,
      -2451640185064075781L,
      2301888907130511306L,
      -6287819402267821027L,
      765644210638909011L,
      8704129290363901256L,
      5863866848487845912L
   };
   private static final Integer[] db = new Integer[25];
   private static final long eb = 6368L;

   private LowFireRenderer() {
   }

   public static boolean replacesOverlay() {
      return ak.enabled() && F();
   }

   public static float vanillaDrop() {
      LowFireModule var0 = ak;
      if (!var0.enabled()) {
         return 0.0F;
      } else {
         float var1 = ((Integer)class_310.method_1551().field_1690.method_41808().method_41753()).intValue() * (float) (Math.PI / 180.0);
         float var2 = (2.0F * var0.g.floatValue() - 1.0F) * 0.5F * (float)Math.tan(var1 * 0.5F);
         return Math.max(0.0F, 0.2F - var2);
      }
   }

   public static void render(boolean var0) {
      long var1 = 63969979368792L;
      long var3 = var1 ^ 94806113764042L;
      int var5 = (int)((var1 ^ 86980688067562L) >>> 32);
      int var6 = (int)((var1 ^ 86980688067562L) << 32 >>> 32);
      long var7 = var1 ^ 78280199287720L;
      long var9 = var1 ^ 28101409271251L;
      LowFireLayerCache.update();
      long var11 = System.nanoTime();
      boolean var13 = P != 0L && var11 - P < 500000000L;
      float var14 = var13 ? Math.min((float)(var11 - P) / 1.0E9F, 0.1F) : 0.0F;
      P = var11;
      LowFireModule var15 = ak;
      class_310 var16 = class_310.method_1551();
      class_746 var17 = var16.field_1724;
      if (!var15.enabled() || var17 == null || var16.field_1687 == null || var16.field_1687 != G) {
         G = var16.field_1687;
         Y();
         if (!var15.enabled() || var17 == null) {
            return;
         }
      }

      long var18 = System.nanoTime();

      try {
         e(var15, var3, var16, var17, var14);
         if (var16.field_1690.method_31044().method_31034() && !var0 && !var17.method_7325() && F() && p(var5, var6)) {
            F(var9, var15, var16);
            if (E) {
               an = an + (System.nanoTime() - var18) / 1000000.0;
            }
         }
      } catch (Throwable var21) {
         metric("draw_failures");
         c(var7, var21);
      }
   }

   private static void Y() {
      q = false;
      R = false;
      v = 0.0F;
      a = 0.0F;
      z = 0.0F;
      N = 0.0F;
      k = 1.0F;
      aF = 0.0F;
      J = 0.0F;
      c = false;
      l = false;
      ar.clear();
   }

   private static boolean p(int var0, int var1) {
      LowFireLoader.ensureSourceClassInitialized();
      return v > 0.002F || k < 1.0F || ar.count() > 0 || N > 0.01F;
   }

   private static void e(LowFireModule var0, long var1, class_310 var3, class_746 var4, float var5) {
      long var6;
      short var8;
      short var9;
      int var10;
      boolean var11;
      String var12;
      boolean var51;
      label216: {
         var6 = 19786472530956L;
         long var14 = 112305837206885L;
         var8 = 26148;
         var9 = 15752;
         var10 = (int)(var14 << 48 >>> 48);
         LowFireLoader.ensureSourceClassInitialized();
         var11 = var4.method_6059(class_1294.field_5918);
         var12 = (String)var0.W.get();
         if (var4.method_5809()) {
            var51 = var4.method_7325();
            long var16 = 118850055980945L;
            int var55 = var16 < 0L ? -1 : 1;
            if (!var51 && (!var11 || !var12.equals("Hide"))) {
               var51 = true;
               break label216;
            }
         }

         var51 = false;
      }

      boolean var54 = var51;
      long var15 = 118850055980946L;
      float var18;
      if (V != null) {
         var54 = !V.equals("off") && !V.equals("water");
      }

      label198: {
         O(var6, var0, var5);
         float var19 = K * Math.max(v, 0.0F);
         var18 = (float)var3.method_22683().method_4489() / Math.max(1, var3.method_22683().method_4506());
         if (var54) {
            long var20 = 118850055980946L;
            int var58 = var20 < 0L ? -1 : 1;
            if (!q) {
               R = false;
               long var23 = 118850055980945L;
               int var611 = var23 < 0L ? -1 : 1;
               k = 1.0F;
               float var25;
               int varCmp = Float.compare(var25 = v - 0.25F, 0.0F);
               long var26 = 118850055980946L;
               int var29 = var26 < 0L ? -1 : 1;
               if (varCmp < 0) {
                  long var28 = 118850055980946L;
                  int var31 = var28 < 0L ? -1 : 1;
                  N = 1.0F;
                  z = Math.max(z, 0.45F);
                  ar.burst(Math.round(20.0F * var0.r.floatValue()), var18, K, 1.0F);
               }
               break label198;
            }
         }

         if (!var54 && q) {
            R = var4.method_5721() || var4.field_27857 || var4.field_28628 || "water".equals(V);
            long var56 = 118850055980946L;
            int var56x = var56 < 0L ? -1 : 1;
            if (R) {
               k = 0.0F;
               ar.quench();
               long var22 = 118850055980946L;
               int var59 = var22 < 0L ? -1 : 1;
            } else {
               ar.burst(Math.round(6.0F * var0.r.floatValue()), var18, var19, 0.4F);
            }
         }
      }

      q = var54;
      aP += var5;
      int var55 = var4.field_6235;
      long var57 = 118850055980946L;
      int var591 = var57 < 0L ? -1 : 1;
      long var58 = 118850055980946L;
      int var631 = var58 < 0L ? -1 : 1;
      if (var55 > aA && q && var0.d.enabled()) {
         float var24;
         int varCmp9 = Float.compare(var24 = aP - 0.3F, 0.0F);
         long var60 = 118850055980945L;
         int var71 = var60 < 0L ? -1 : 1;
         if (varCmp9 > 0) {
            class_1282 var27 = var4.method_6081();
            long var62 = 118850055980946L;
            int var32 = var62 < 0L ? -1 : 1;
            if (var27 != null && var27.method_48789(class_8103.field_42246)) {
               G();
            }
         }
      }

      long var59x = 118850055980946L;
      int var68 = var59x < 0L ? -1 : 1;
      aA = var55;
      J(var8, (char)var9, var10, var5);
      z = z * (float)Math.exp(-var5 * 3.2F);
      N = N * (float)Math.exp(-var5 * 2.2F);
      k = Math.min(1.0F, k + var5 / 1.9F);
      float var61 = q && var11 && var12.equals("Calm") ? 1.0F : 0.0F;
      O = O + (var61 - O) * (1.0F - (float)Math.exp(-var5 * 3.0F));
      class_4184 var63x = var3.field_1773.method_19418();
      float var29 = var63x.method_19330();
      float var30 = var63x.method_19329();
      float var31 = c ? class_3532.method_15393(var29 - a3) : 0.0F;
      float var32 = c ? var30 - B : 0.0F;
      a3 = var29;
      B = var30;
      c = true;
      class_243 var33 = var4.method_18798().method_1021(20.0);
      float var34 = var29 * (float) (Math.PI / 180.0);
      float var35 = (float)(-var33.field_1352 * Math.cos(var34) - var33.field_1350 * Math.sin(var34));
      float var36 = (float)(-var33.field_1352 * Math.sin(var34) + var33.field_1350 * Math.cos(var34));
      float var37 = var4.method_24828() ? 0.0F : (float)(-var33.field_1351);
      boolean var38 = var0.V.enabled();
      long var39 = 118850055980946L;
      int var39x = var39 < 0L ? -1 : 1;
      float var41x = var5 > 0.0F ? var31 / var5 : 0.0F;
      float var42 = var38 ? Math.clamp(-var41x / 420.0F - var35 / 9.0F, -1.0F, 1.0F) : 0.0F;
      float var43 = var38 ? Math.clamp(var37 / 24.0F, -0.25F, 0.6F) - Math.clamp(var36 / 40.0F, 0.0F, 0.15F) : 0.0F;
      aF = aF + (var42 - aF) * (1.0F - (float)Math.exp(-var5 * 5.0F));
      J = J + (var43 - J) * (1.0F - (float)Math.exp(-var5 * 4.0F));
      float var44 = var0.I.floatValue();
      ao = ao + var5 * var44 * (1.0F - 0.45F * O) * (1.0F + 0.5F * z + 0.25F * Math.abs(J));
      if (ao > 3600.0F) {
         ao -= 3600.0F;
      }

      float var45 = q ? 16.0F * var0.r.floatValue() * (1.0F - 0.6F * O) * (1.0F + 1.5F * z) : 0.0F;
      float var46 = ((Integer)var3.field_1690.method_41808().method_41753()).intValue();
      float var47 = var38 ? -var31 / var46 * 0.6F : 0.0F;
      float var48 = var38 ? var32 / var46 * 0.6F : 0.0F;
      float var49 = var38 ? -var35 * 0.03F : 0.0F;
      float var50 = var38 ? var37 * 0.025F : 0.0F;
      ar.update(var5 * var44, var45, var18, j(), var49, var50, var47, var48);
   }

   private static void G() {
      z = 1.0F;
      aP = 0.0F;
      class_310 var0 = class_310.method_1551();
      float var1 = (float)var0.method_22683().method_4489() / Math.max(1, var0.method_22683().method_4506());
      ar.burst(Math.round(9.0F * ak.r.floatValue()), var1, j(), 0.8F);
   }

   private static void J(int var0, char var1, int var2, float var3) {
      LowFireLoader.ensureSourceClassInitialized();
      float var4 = q ? 1.0F : 0.0F;
      float var5 = q ? 140.0F : (R ? 300.0F : 30.0F);
      float var6 = q ? 13.0F : 2.0F * (float)Math.sqrt(var5);

      while (var3 > 0.0F) {
         float var7 = Math.min(var3, 0.004166667F);
         a = a + (-var5 * (v - var4) - var6 * a) * var7;
         v = v + a * var7;
         var3 -= var7;
      }

      if (!q && v < 0.002F) {
         v = 0.0F;
         a = 0.0F;
      }
   }

   private static float j() {
      return K * Math.max(v, 0.0F) * (1.0F + 0.3F * z) * (1.0F - 0.45F * O) * (1.0F + 0.35F * J);
   }

   private static void O(long var0, LowFireModule var2, float var3) {
      long var4 = 39550505403022L;
      LowFireLoader var6 = LowFireLoader.resolve((String)var2.R.get(), var2.c.argb());
      LowFireLoader.ensureSourceClassInitialized();
      D(var4, aS, 0, var6.F);
      D(var4, aS, 3, var6.c);
      D(var4, aS, 6, var6.g);
      float var7 = l ? 1.0F - (float)Math.exp(-var3 * 6.0) : 1.0F;

      for (int var8 = 0; var8 < au.length; var8++) {
         long var9 = 19786472530956L;
         int var12 = var9 < 0L ? -1 : 1;
         au[var8] = au[var8] + (aS[var8] - au[var8]) * var7;
      }

      long var22 = 19786472530955L;
      int var11 = var22 < 0L ? -1 : 1;
      String var11x = (String)var2.E.get();
      float[] var12 = aE;
      float var13 = aE[0];
      float var14 = var11x.equals("Flames") ? 1.0F : 0.0F;
      float var15 = aE[0];
      long var16 = 19786472530956L;
      int var19 = var16 < 0L ? -1 : 1;
      var12[0] = var13 + (var14 - var15) * var7;
      float[] var19x = aE;
      var13 = aE[1];
      var14 = var11x.equals("Pixel") ? 1.0F : 0.0F;
      var15 = aE[1];
      long var20 = 19786472530956L;
      int var20x = var20 < 0L ? -1 : 1;
      var19x[1] = var13 + (var14 - var15) * var7;
      aE[2] = aE[2] + ((var11x.equals("Glow") ? 1.0F : 0.0F) - aE[2]) * var7;
      K = K + (var2.g.floatValue() - K) * var7;
      y = y + (var2.y.floatValue() - y) * var7;
      f = f + ((var2.B.enabled() && !var11x.equals("Glow") ? 1.0F : 0.0F) - f) * var7;
      l = true;
   }

   private static void F(long var0, LowFireModule var2, class_310 var3) {
      long var4 = 16045573056091L;
      short var6 = 3735;
      char var7 = '\ue6ae';
      int var8 = (int)(var4 << 48 >>> 48);
      long var9 = 62728177096054L;
      var4 = 126303912339732L;
      short var11 = 29407;
      byte var12 = 107;
      int var13 = (int)(var4 << 40 >>> 40);
      long var14 = 125851911885977L;
      long var16 = 129046097771131L;
      class_276 var18 = var3.method_1522();
      GpuTextureView var19 = var18.method_71639();
      LowFireLoader.ensureSourceClassInitialized();
      GpuTexture var20 = var18.method_30277();
      if (var19 != null && var20 != null) {
         int var21 = var18.field_1482;
         int var22 = var18.field_1481;
         float var23 = Math.max(0.75F, var22 / 1080.0F);
         float var24 = (float)var21 / var22;
         float var25 = j() * (1.0F - 0.4F * aE[2]);
         float var26 = Math.clamp(K / 0.3F, 0.5F, 1.6F);
         x(var25, var24, var16, var26);
         float var27 = aD * 1.3F + 0.03F;
         float var28 = f * Math.min(1.0F, v * 2.0F) * (1.0F - 0.5F * O);
         float var29 = e + 3.0F * (0.028F + 0.04F * aE[2]) * var26 + 0.02F;
         float var30 = Math.max(var29, 7.0F * (0.022F + 0.05F * Math.max(K, 0.1F)) * (1.0F + 0.8F * aE[2]) + 0.01F);
         long var31 = 39174218573963L;
         int var34 = var31 < 0L ? -1 : 1;
         long var34x = 39174218573963L;
         int var36 = var34x < 0L ? -1 : 1;
         if (var28 > 0.001F) {
            var30 = Math.max(var30, var27 + 0.01F);
         }

         long var36x = 39174218573963L;
         int var38 = var36x < 0L ? -1 : 1;
         long var38x = 39174218573962L;
         int var83 = var38x < 0L ? -1 : 1;
         if (k < 1.0F) {
            float var40 = Math.max(K, 0.12F);
            var30 = Math.max(var30, var40 * (1.1F + 1.9F * k) + 0.02F);
         }

         long var83x = 39174218573962L;
         int var42 = var83x < 0L ? -1 : 1;
         if (N > 0.001F) {
            var30 = Math.max(var30, (Math.max(K, 0.1F) * 1.6F + 0.05F) * 1.35F + 0.02F);
         }

         var29 = Math.min(var30, 1.0F);
         GpuDevice var42x = RenderSystem.getDevice();
         CommandEncoder var43 = var42x.createCommandEncoder();
         A(var9, var42x);
         GpuTextureView var44 = H;
         float var45 = aE[0];
         float var46 = aE[1];
         int var47 = 0;
         if (var46 > 0.001F) {
            LowFireCache var48 = LowFireCache.Q;
            class_1043 var49 = var48.ensure(LowFireLoader.resolve((String)var2.R.get(), var2.c.argb())) ? var48.texture() : null;
            long var50 = 39174218573963L;
            int var53 = var50 < 0L ? -1 : 1;
            if (var49 != null) {
               var44 = var49.method_71659();
               long var53x = 39174218573963L;
               var47 = var48.sprite(1, a(var3, var14), w);
            } else {
               var45 += var46;
               var46 = 0.0F;
            }
         }

         int var84 = Math.max(2, (int)Math.ceil(var24 / 0.34F) + 1);
         float var85 = var24 / (var84 - 1) * 1.02F;
         GpuTextureView var86 = H;
         long var51 = 39174218573963L;
         int var54 = var51 < 0L ? -1 : 1;
         float var54x;
         int var55 = (var54x = var28 - 0.001F) == 0.0F ? 0 : (var54x < 0.0F ? -1 : 1);
         long var56 = 39174218573962L;
         int var59 = var56 < 0L ? -1 : 1;
         if (var55 > 0) {
            v(var6, var7, var42x, var21, var22, (short)var8);
            var59 = Math.min(var22, (int)Math.ceil((var27 + 0.02F) * var22) + 8);
            var43.copyTextureToTexture(var20, h, 0, 0, 0, 0, 0, var21, var59);
            var86 = F;
         }

         float[] var88 = LowFireCache.ramp(au);
         Std140Builder var60 = Std140Builder.intoBuffer(M.clear())
            .putVec4(var21, var22, var23, ao)
            .putVec4(-1.0F, -1.0F, 1.0F, var29 * 2.0F - 1.0F)
            .putVec4(var25, y, aF, e)
            .putVec4(var45, var46, aE[2], O)
            .putVec4(z, N, k, Math.max(v, 0.0F))
            .putVec4(var28, var27, var26, K)
            .putVec4(w[0], w[1], w[2], w[3])
            .putVec4(var47, var85, var84, 0.0F)
            .putVec4(0.085F, 0.115F, ag[0], ag[1])
            .putVec4(var88[0], var88[1], var88[2], 0.0F)
            .putVec4(var88[3], var88[4], var88[5], 0.0F)
            .putVec4(var88[6], var88[7], var88[8], 0.0F)
            .putVec4(var88[9], var88[10], var88[11], 0.0F)
            .putVec4(au[6], au[7], au[8], 0.0F);

         for (byte var61 = 0; var61 < u.length; var61 = (byte)(var61 + 4)) {
            var60.putVec4(u[var61], u[var61 + 1], u[var61 + 2], u[var61 + 3]);
            long var62 = 39174218573963L;
            int var61x = var62 < 0L ? -1 : 1;
         }

         ar.write(var60, var22, var23);
         long var89 = 39174218573962L;
         int var60x = var89 < 0L ? -1 : 1;
         long var63x = 39174218573963L;
         int var65 = var63x < 0L ? -1 : 1;
         var43.writeToBuffer(U.slice(), var60.get());
         GpuQuery var65x;
         if (E) {
            var65x = j(var11, var12, var13, var43);
         } else {
            var65x = null;
         }

         boolean var67 = false;

         try {
            var67 = true;
            RenderPass var68 = var43.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX low fire", var19, OptionalInt.empty());

            try {
               var68.setPipeline(SkyPipelines.FIRE);
               long var69 = 39174218573962L;
               int var67x = var69 < 0L ? -1 : 1;
               var68.setUniform("PurxiyFire", U);
               var68.bindTexture("PixelSampler", var44, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
               long var71x = 39174218573963L;
               int var33x = var71x < 0L ? -1 : 1;
               var68.bindTexture("SceneSampler", var86, RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR));
               var55 = -178141175;
               var68.draw(0, 6 + ar.count() * 6);
               var33x = -178132853;
               metric("drawn");
            } catch (Throwable var75) {
               if (var68 != null) {
                  try {
                     var68.close();
                  } catch (Throwable var74) {
                     var75.addSuppressed(var74);
                  }
               }

               throw var75;
            }

            if (var68 != null) {
               var68.close();
            }
         } finally {
            if (var67 && var65x != null) {
               var43.timerQueryEnd(var65x);
               d = var65x;
            }
         }

         if (var65x != null) {
            var43.timerQueryEnd(var65x);
            d = var65x;
         }
      }
   }

   private static void x(float var0, float var1, long var2, float var4) {
      long var5 = 13819249399931L;
      double var7 = ao;
      LowFireLoader.ensureSourceClassInitialized();
      e = 0.0F;
      aD = 0.0F;
      float var9 = 3.0F * (0.028F + 0.04F * aE[2]) * var4;

      for (int var10 = 0; var10 < 2; var10++) {
         float var11 = var10 == 0 ? 0.085F : 0.115F;
         float var12 = var10 == 0 ? 0.92F : 1.2F;
         float var13 = 0.5F * var11 * var10;
         float var14 = 0.0F;

         for (int var15 = 0; var15 < 48; var15++) {
            int var16 = var15 - 2;
            float var17 = hash(var16, var10, 1);
            float var18 = hash(var16, var10, 2);
            float var19 = hash(var16, var10, 3);
            float var20 = var13 + (var16 + 0.5F) * var11 + (var17 - 0.5F) * var11 * 0.5F;
            float var21 = 0.6F + 0.5F * var19;
            float var22 = 0.78F + 0.22F * z(var7 * (0.8 + 0.7 * var17) + var17 * 17.0, var5, var16) + 0.05F * z(var7 * 6.0 + var18 * 9.0, var5, var16 + 1000);
            long var23 = 129046097771131L;
            int var26 = var23 < 0L ? -1 : 1;
            float var26x = var0 * var12 * d(var20 / var1) * var21 * var22;
            float var27 = var26x * 0.23F * (0.85F + 0.3F * var18);
            float var28 = (z(var7 * 0.6 + var17 * 11.0, var5, var16 + 2000) - 0.5F) * 1.2F * var27;
            float var29 = -1.0F;
            if (var10 == 0) {
               long var30 = 129046097771131L;
               int var32 = var30 < 0L ? -1 : 1;
               double var33 = var7 * (0.7 + 0.6 * var18) + var17 * 3.0;
               long var35 = 129046097771131L;
               int var35x = var35 < 0L ? -1 : 1;
               double var37x = frac(var33);
               if (var37x < 0.55) {
                  var29 = (float)(var37x / 0.55);
               }
            }

            float var41 = (float)(((Math.PI * 2) * var7 * (1.1 + 0.7 * var18) - var17 * 6.0) % (Math.PI * 2));
            float var31 = 0.55F * var27;
            int var32 = (var10 * 48 + var15) * 8;
            long var42 = 129046097771131L;
            int var34 = var42 < 0L ? -1 : 1;
            u[var32] = var20;
            u[var32 + 1] = var26x;
            u[var32 + 2] = var27;
            u[var32 + 3] = var28;
            u[var32 + 4] = var41;
            u[var32 + 5] = var31;
            u[var32 + 6] = var29;
            u[var32 + 7] = var20 + var28 + (float)Math.sin((Math.PI * 5.0 / 2.0) - var41) * var31 + (var19 - 0.5F) * var27 * 0.8F * Math.max(var29, 0.0F);
            var14 = Math.max(var14, 1.8F * var27);
            e = Math.max(e, var26x * (var29 >= 0.0F ? 1.45F : 1.0F));
            aD = Math.max(aD, var26x);
         }

         ag[var10] = Math.clamp((long)((int)Math.floor((var14 + 0.012F * var4 + var9) / var11 + 0.75F)), 1, 6);
         long var40 = 129046097771130L;
         int var40x = var40 < 0L ? -1 : 1;
      }

      long var39 = 129046097771131L;
      int var38 = var39 < 0L ? -1 : 1;
   }

   private static float d(float var0) {
      float var1 = Math.abs(var0 - 0.5F) * 2.0F;
      float var2 = Math.clamp((var1 - 0.1F) / 0.85F, 0.0F, 1.0F);
      return 0.55F + 0.45F * var2 * var2 * (3.0F - 2.0F * var2);
   }

   private static long a(class_310 var0, long var1) {
      LowFireLoader.ensureSourceClassInitialized();
      return var0.field_1687 == null ? 0L : var0.field_1687.method_75260();
   }

   private static boolean F() {
      return RenderSystem.getDevice().precompilePipeline(SkyPipelines.FIRE).isValid();
   }

   private static void A(long var0, GpuDevice var2) {
      LowFireLoader.ensureSourceClassInitialized();
      if (U == null) {
         U = var2.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX low fire"), 136, 6368L);
         M = MemoryUtil.memAlloc(6368);
         aY = var2.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX low fire blank", 5, TextureFormat.RGBA8, 1, 1, 1, 1);
         H = var2.createTextureView(aY);
         class_1011 var3 = new class_1011(1, 1, true);

         try {
            var2.createCommandEncoder().writeToTexture(aY, var3);
         } catch (Throwable var7) {
            try {
               var3.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }

            throw var7;
         }

         var3.close();
      }
   }

   private static void v(int var0, int var1, GpuDevice var2, int var3, int var4, short var5) {
      long var6 = (long)var0 << 32 | 3870162944L | 42587L;
      long var8 = (var6 ^ 74302767833680L) >>> 32;
      int var10 = (int)((var6 ^ 74302767833680L) << 32 >>> 32);
      LowFireLoader.ensureSourceClassInitialized();
      if (h == null || b != var3 || g != var4) {
         if (h != null) {
            F.close();
            h.close();
         }

         h = var2.createTexture(() -> {
            long var3x = var8 << 32 | (long)var10 << 32 >>> 32;
            return "SourceByDevFFnightmare DeObfDeCompByGuideX low fire scene";
         }, 5, TextureFormat.RGBA8, var3, var4, 1, 1);
         F = var2.createTextureView(h);
         b = var3;
         g = var4;
      }
   }

   public static boolean renderEntityFlame(class_4665 var0, class_4597 var1, class_10017 var2, Quaternionf var3) {
      long var4 = 125389103138764L;
      long var6 = var4 ^ 71800738927677L;
      long var8 = var4 ^ 16588244693308L;
      long var10 = var4 ^ 533690782549L;
      LowFireModule var12 = ak;
      boolean var13 = var12.enabled() && var12.o.enabled();
      if (!var13 && !LowFireLayerCache.active()) {
         return false;
      } else {
         try {
            LowFireCache var14 = var13 ? LowFireCache.Q : LowFireCache.j;
            if (!var14.ensure(LowFireLoader.resolve((String)var12.R.get(), var12.c.argb()))) {
               return false;
            } else {
               long var15 = a(class_310.method_1551(), var10);
               float var17 = var2.field_53329 * 1.4F;
               var0.method_67800(var17, var17, var17);
               float var18 = 0.5F;
               float var19 = var2.field_53330 / var17;
               float var20 = 0.0F;
               var0.method_67798(var3);
               var0.method_67796(0.0F, 0.0F, 0.3F - (int)var19 * 0.02F);
               float var21 = 0.0F;
               int var22 = 0;

               for (class_4588 var23 = var1.method_73477(class_12249.method_75990(var14.id())); var19 > 0.0F; var22++) {
                  var14.frame(var22 % 2, var15, o);
                  float var24 = o[0];
                  float var25 = o[1];
                  float var26 = o[2];
                  float var27 = o[3];
                  if (var22 / 2 % 2 == 0) {
                     float var28 = var26;
                     var26 = var24;
                     var24 = var28;
                  }

                  d(var6, var0, var23, -var18, -var20, var21, var26, var27);
                  d(var6, var0, var23, var18, -var20, var21, var24, var27);
                  d(var6, var0, var23, var18, 1.4F - var20, var21, var24, var25);
                  d(var6, var0, var23, -var18, 1.4F - var20, var21, var26, var25);
                  var19 -= 0.45F;
                  var20 -= 0.45F;
                  var18 *= 0.9F;
                  var21 -= 0.03F;
               }

               return true;
            }
         } catch (Throwable var291) {
            metric("draw_failures");
            c(var8, var291);
            return false;
         }
      }
   }

   private static void d(long var0, class_4665 var2, class_4588 var3, float var4, float var5, float var6, float var7, float var8) {
      var3.method_56824(var2, var4, var5, var6)
         .method_39415(-1)
         .method_22913(var7, var8)
         .method_60796(0, 10)
         .method_60803(240)
         .method_60831(var2, 0.0F, 1.0F, 0.0F);
   }

   public static void devForce(String var0) {
      V = var0.equals("end") ? null : (var0.equals("flare") ? "on" : var0);
      if (var0.equals("flare") && q) {
         G();
      }
   }

   public static void setTiming(boolean var0) {
      E = var0;
      Q = 0.0;
      an = 0.0;
      t = 0;
   }

   private static GpuQuery j(int var0, byte var1, int var2, CommandEncoder var3) {
      LowFireLoader.ensureSourceClassInitialized();
      if (d != null) {
         OptionalLong var4 = d.getValue();
         if (var4.isEmpty()) {
            return null;
         }

         d.close();
         d = null;
         Q = Q + var4.getAsLong() / 1000000.0;
         int var5 = t + 1;
         t = var5;
         if (var5 == 240) {
            ClientServices.LOGGER
               .info(
                  "SourceByDevFFnightmare DeObfDeCompByGuideX low fire: {} ms CPU, {} ms GPU per frame ({} embers, {})",
                  new Object[]{String.format(Locale.ROOT, "%.3f", an / t), String.format(Locale.ROOT, "%.3f", Q / t), ar.count(), ak.E.get()}
               );
            Q = 0.0;
            an = 0.0;
            t = 0;
         }
      }

      return var3.timerQueryBegin();
   }

   private static void c(long var0, Throwable var2) {
      LowFireLoader.ensureSourceClassInitialized();
      String var3 = var2.toString();
      long var4 = 137559961057007L;
      int var7 = var4 < 0L ? -1 : 1;
      long var7x = 137559961057007L;
      int var8 = var7x < 0L ? -1 : 1;
      if (!var3.equals(i)) {
         i = var3;
         ClientServices.LOGGER.error("SourceByDevFFnightmare DeObfDeCompByGuideX low fire failed", var2);
      }
   }

   static float hash(int var0, int var1, int var2) {
      int var3 = var0 * -1640531527 + var1 * -2048144789 + var2 * -1028477387;
      var3 ^= var3 >>> 15;
      var3 *= 739982445;
      var3 ^= var3 >>> 12;
      var3 *= 695872825;
      var3 ^= var3 >>> 15;
      return (var3 >>> 8) / 1.6777216E7F;
   }

   private static float z(double var0, long var2, int var4) {
      double var5 = Math.floor(var0);
      int var7 = (int)var5;
      float var8 = (float)(var0 - var5);
      float var9 = var8 * var8 * (3.0F - 2.0F * var8);
      float var10 = hash(var7, var4, 7);
      float var11 = hash(var7 + 1, var4, 7);
      return var10 + (var11 - var10) * var9;
   }

   static double frac(double var0) {
      return var0 - Math.floor(var0);
   }

   private static void D(long var0, float[] var2, int var3, int var4) {
      var2[var3] = (var4 >> 16 & 0xFF) / 255.0F;
      var2[var3 + 1] = (var4 >> 8 & 0xFF) / 255.0F;
      var2[var3 + 2] = (var4 & 0xFF) / 255.0F;
   }

   public static void install(LowFireModule var0, ModuleContext var1) {
      ak = var0;
      m = var1;
   }

   public static void metric(String var0) {
      if (m != null) {
         m.metric(var0, var0.equals("drawn") ? ++ai : ++Z);
      }
   }

   public static void uninstall() {
      int var0 = 0;

      try {
         ClientCache.release(SkyPipelines.FIRE);
      } catch (Exception var128) {
         var0++;
      }

      try {
         if (F != null) {
            F.close();
         }
      } catch (Exception var126) {
         var0++;
      } finally {
         F = null;
      }

      try {
         if (H != null) {
            H.close();
         }
      } catch (Exception var124) {
         var0++;
      } finally {
         H = null;
      }

      try {
         if (d != null) {
            d.close();
         }
      } catch (Exception var122) {
         var0++;
      } finally {
         d = null;
      }

      try {
         if (U != null) {
            U.close();
         }
      } catch (Exception var120) {
         var0++;
      } finally {
         U = null;
      }

      try {
         if (h != null) {
            h.close();
         }
      } catch (Exception var118) {
         var0++;
      } finally {
         h = null;
      }

      try {
         if (aY != null) {
            aY.close();
         }
      } catch (Exception var116) {
         var0++;
      } finally {
         aY = null;
      }

      try {
         if (M != null) {
            MemoryUtil.memFree(M);
         }
      } catch (Exception var114) {
         var0++;
      } finally {
         M = null;
      }

      LowFireLayerCache.release();
      LowFireCache.release();
      Y();
      G = null;
      ak = null;
      m = null;
      P = 0L;
      l = false;
      if (var0 != 0) {
         throw new IllegalStateException("World visual GPU cleanup failed");
      }
   }

   private static int a(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 19791;
      if (db[var3] == null) {
         db[var3] = (int)(cb[var3] ^ var1);
      }

      return db[var3];
   }
}
