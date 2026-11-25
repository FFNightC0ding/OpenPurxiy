package dev.purxiy.modules.skins3d;

import dev.purxiy.duck.ScreenHook;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_10055;
import net.minecraft.class_11890;
import net.minecraft.class_1306;
import net.minecraft.class_1657;
import net.minecraft.class_310;
import net.minecraft.class_591;
import net.minecraft.class_630;
import net.minecraft.class_572.class_573;
import org.joml.Vector3f;

public final class Skins3DTransform {
   private static final Int2ObjectMap<Skins3DTransform.SkinTransform> x = new Int2ObjectOpenHashMap();
   private static final Map<SkinPreset, Skins3DApi> v = new EnumMap<>(SkinPreset.class);
   private static final Map<Skins3DBuilder.SkinQuad, Skins3DCodec.Data> D = new IdentityHashMap<>();
   private static final Skins3DRestPose e = new Skins3DRestPose();
   private static final Skins3DCurve G = new Skins3DCurve();
   private static final Skins3DCurve k = new Skins3DCurve();
   private static final Skins3DPose.PoseAngles W = new Skins3DPose.PoseAngles();
   private static final Vector3f b = new Vector3f();
   private static final float g = 2.1333334F;
   private static long I = 0L;
   private static int n = 0;
   private static final class_630[] j = new class_630[7];
   private static final Map<class_10055, Skins3DGroundPose> V = new WeakHashMap<>();
   private static final Integer[] c = new Integer[4];
   private static final long d = 10000000000L;

   private Skins3DTransform() {
   }

   public static void extract(class_11890 var0, class_10055 var1) {
      long var2 = 57455603522383L;
      long var4 = var2 ^ 14465140358441L;
      long var6 = var2 ^ 36207992010836L;
      long var8 = var2 ^ 137315384877413L;
      int var10 = (int)((var2 ^ 137315384877413L) >>> 32);
      int var11 = (int)((var2 ^ 137315384877413L) << 32 >>> 48);
      int var12 = (int)(var8 << 48 >>> 48);
      SkinPreset var13 = var1.field_53520 == null ? null : SkinPreset.sculpted(var1.field_53520.comp_1626().comp_3627());
      V.remove(var1);
      Skins3DApi var14 = var13 == null ? null : v.get(var13);
      if (var14 != null) {
         Skins3DTransform.SkinTransform var15 = (Skins3DTransform.SkinTransform)x.get(var0.method_5628());
         long var16 = System.nanoTime();
         if (var15 == null) {
            var15 = new Skins3DTransform.SkinTransform();
            var15.b = var16;
            x.put(var0.method_5628(), var15);
         }

         float var18 = Math.min(0.1F, Math.max(0.0F, (float)(var16 - var15.b) / 1.0E9F));
         var15.b = var16;
         boolean var19 = var0.method_5765()
            || var0.method_5681()
            || var1.field_53412
            || var0.method_6113()
            || var1.field_53411
            || var1.field_53459
            || var1.field_53542;
         if (!var19) {
            double var20 = var0.method_23318();
            float var22 = var15.c ? (float)(var20 - var15.v) / Math.max(var18, 0.001F) / 20.0F : 0.0F;
            var15.v = var20;
            var15.c = true;
            boolean var23 = var0 instanceof class_1657 var24 && var24.method_31549().field_7479;
            boolean var33 = !var0.method_24828() && !var23 && !var0.method_6101();
            if (var33) {
               if (var15.N == 0.0F) {
                  var15.D = Math.cos(var15.w) >= 0.0 ? -1.0F : 1.0F;
               }

               var15.N += var18;
            } else {
               if (var15.N > 0.32F) {
                  var15.J = 1.0F;
                  var15.G = Math.min(1.0F, 0.35F + (var15.N - 0.32F) / 0.9F);
               }

               var15.N = 0.0F;
            }

            var15.Y = d(var15.Y, var33 && var15.N > 0.09F ? 1.0F : 0.0F, var18, 9.0F);
            var15.f = d(var15.f, var22 > 0.02F ? 1.0F : 0.0F, var18, 7.0F);
            var15.C = d(var15.C, var0.method_5624() ? 1.0F : 0.0F, var18, 6.0F);
            var15.y = d(var15.y, var1.field_53410 ? 1.0F : 0.0F, var18, 10.0F);
            var15.J = Math.max(0.0F, var15.J - var18 / 0.38F);
            float var25 = var1.field_53450;
            float var26 = var15.U ? var25 - var15.M : 0.0F;
            if (Math.abs(var26) > 4.0F) {
               var26 = 0.0F;
            }

            var15.M = var25;
            var15.U = true;
            var15.w = (var15.w + var26 * 0.6662F * var14.cadence(var15.C)) % 6283.1855F;
            Skins3DGroundPose var27 = new Skins3DGroundPose();
            var27.L = var14;
            var27.W = Math.min(1.0F, var1.field_53451 * 1.25F);
            var27.H = var15.C;
            var27.N = var15.w;
            var27.f = var15.Y;
            var27.d = var15.f;
            var27.j = var15.D;
            var27.c = E(1.0F - var15.J, var6) * var15.G * (var15.J > 0.0F ? 1 : 0);
            var27.U = var15.y;
            var27.V = var1.field_53328 / 20.0F;
            boolean var28 = var0 == class_310.method_1551().field_1724;
            c(var15, var4, var27, var14, var13, var18, var28);
            var27.p = var15.A;
            var27.k = var15.K;
            var27.R = var15.q;
            var27.h = h(var10, var1, (short)var11, (short)var12, class_1306.field_6183);
            var27.r = h(var10, var1, (short)var11, (short)var12, class_1306.field_6182);
            var27.u = var1.field_63604 > 0.0F;
            if (var13 == SkinPreset.B && var0 == class_310.method_1551().field_1724) {
               Skins3DUpdater.extract(var1, var27);
            }

            V.put(var1, var27);
            if (var16 - I > 5000000000L) {
               I = var16;
               x.values().removeIf(var2x -> {
                  SkinPreset.ensureSourceClassInitialized();
                  return var16 - var2x.b > 10000000000L;
               });
            }
         }
      }
   }

   private static void c(Skins3DTransform.SkinTransform var0, long var1, Skins3DGroundPose var3, Skins3DApi var4, SkinPreset var5, float var6, boolean var7) {
      SkinPreset.ensureSourceClassInitialized();
      boolean var8 = var3.W < 0.08F && var0.Y < 0.1F && var0.y < 0.5F;
      var0.A = var8 ? var0.A + var6 : 0.0F;
      long var9 = 63112419731045L;
      int var12 = var9 < 0L ? -1 : 1;
      if (!var8) {
         var0.K = 0;
         var0.k = Math.max(var0.k, 2.5F);
      }

      long var12x = 63112419731045L;
      int var29 = var12x < 0L ? -1 : 1;
      if (var0.K != 0) {
         var0.q = var0.q + var6 / var4.habitSeconds(var0.K);
         if (var0.q >= 1.0F) {
            var0.Q = var0.K;
            var0.K = 0;
            var0.k = 5.0F + ThreadLocalRandom.current().nextFloat() * 6.0F;
         }
      } else if (var8 && var4.habits() > 0) {
         var0.k -= var6;
         if (var0.k <= 0.0F) {
            float var14;
            int var15 = (var14 = var0.A - 2.5F) == 0.0F ? 0 : (var14 < 0.0F ? -1 : 1);
            long var16 = 63112419731046L;
            int var18 = var16 < 0L ? -1 : 1;
            if (var15 > 0) {
               var18 = var4.habits();
               int var19 = 1 + ThreadLocalRandom.current().nextInt(var18);
               long var20 = 63112419731045L;
               int var23 = var20 < 0L ? -1 : 1;
               long var23x = 63112419731045L;
               if (var19 == var0.Q) {
                  long var25x = 63112419731045L;
                  long var27x = 63112419731045L;
                  if (var18 > 1) {
                     var19 = var19 % var18 + 1;
                  }
               }

               var0.K = var19;
               var0.q = 0.0F;
            }
         }
      }

      long var29x = 63112419731045L;
      int var311 = var29x < 0L ? -1 : 1;
      if (var7) {
         long var30 = 63112419731045L;
         int var35 = var30 < 0L ? -1 : 1;
         if (n > 0) {
            long var32 = 63112419731046L;
            int var37 = var32 < 0L ? -1 : 1;
            if (n <= var4.habits()) {
               var0.K = n;
               var0.q = 0.0F;
               n = 0;
            }
         }
      }

      if (var5 == SkinPreset.I && var7) {
         float var31x = Skins3DTimer.sincePlayed();
         float var17 = var4.habitSeconds(1);
         if (var31x >= 0.0F && var31x < var17) {
            var0.K = 1;
            var0.q = var31x / var17;
         }
      }
   }

   private static boolean h(int var0, class_10055 var1, short var2, short var3, class_1306 var4) {
      SkinPreset.ensureSourceClassInitialized();
      class_573 var5 = var4 == class_1306.field_6183 ? var1.field_55304 : var1.field_55306;
      boolean var6 = var1.field_63604 > 0.0F && var1.field_53408 == var4;
      return !var6 && !var1.field_53414 && (var5 == class_573.field_3409 || var5 == class_573.field_3410);
   }

   static boolean moves(class_10055 var0) {
      return V.get(var0) != null;
   }

   static void pose(class_591 var0, class_10055 var1, Skins3DBuilder.SkinQuad var2) {
      long var3 = 24993557561374L;
      long var5 = var3 ^ 78974967880742L;
      int var7 = (int)((var3 ^ 40536908671022L) >>> 48);
      long var8 = (var3 ^ 40536908671022L) << 16 >>> 16;
      long var10 = var3 ^ 73982423552421L;
      Skins3DGroundPose var12 = V.get(var1);
      if (var12 != null && var12.L != null) {
         Skins3DRestPose var13 = y(var0, e, (short)var7, var8);
         Skins3DCurve var14 = Skins3DCodec.read(var13, G);
         Skins3DCurve var15 = k.set(var14);
         var12.L.move(var15, var14, var12);
         Skins3DCodec.write(var13, var15, o(var2, var5), 1.0F - var12.f);
         var12.L.finish(var13, var12);
         boolean var16 = var12.S != 0;
         if (var16) {
            Skins3DPose.figure(var13, var12, W, b);
         }

         if (var1.field_53410) {
            var13.b[6] = var13.b[6] - 2.1333334F;
         }

         if (var13.finite()) {
            h(var0, var10, var13);
            if (var16) {
               Skins3DUpdater.attach(var0, var1, W, var13, b);
            }
         }
      }
   }

   static void uncrouch(class_591 var0) {
      var0.field_3391.field_3654 -= 0.5F;
      var0.field_3401.field_3654 -= 0.4F;
      var0.field_27433.field_3654 -= 0.4F;
      var0.field_3392.field_3655 -= 4.0F;
      var0.field_3397.field_3655 -= 4.0F;
      var0.field_3398.field_3656 -= 4.2F;
      var0.field_3391.field_3656 -= 3.2F;
      var0.field_3401.field_3656 -= 3.2F;
      var0.field_27433.field_3656 -= 3.2F;
   }

   private static Skins3DCodec.Data o(Skins3DBuilder.SkinQuad var0, long var1) {
      SkinPreset.ensureSourceClassInitialized();
      Skins3DCodec.Data var3 = D.get(var0);
      if (var3 == null) {
         if (D.size() > 32) {
            D.clear();
         }

         var3 = Skins3DCodec.Data.of(var0.y, var0.R[4], var0.n);
         D.put(var0, var3);
      }

      return var3;
   }

   private static class_630[] o(char var0, int var1, int var2, class_591 var3) {
      j[0] = var3.field_3398;
      j[1] = var3.field_3391;
      j[2] = var3.field_3401;
      j[3] = var3.field_27433;
      j[4] = var3.field_3392;
      j[5] = var3.field_3397;
      j[6] = var3.method_63512();
      return j;
   }

   private static Skins3DRestPose y(class_591 var0, Skins3DRestPose var1, short var2, long var3) {
      long var5 = 55411387689008L;
      long var7 = var5 ^ 133928076705828L;
      int var9 = (int)((var5 ^ 133928076705828L) >>> 48);
      int var10 = (int)((var5 ^ 133928076705828L) << 16 >>> 32);
      int var11 = (int)(var7 << 48 >>> 48);
      SkinPreset.ensureSourceClassInitialized();
      class_630[] var12 = o((char)var9, var10, var11, var0);

      for (int var13 = 0; var13 < 7; var13++) {
         class_630 var14 = var12[var13];
         var1.V[var13] = var14.field_3657;
         var1.b[var13] = var14.field_3656;
         var1.F[var13] = var14.field_3655;
         var1.d[var13] = var14.field_3654;
         var1.D[var13] = var14.field_3675;
         var1.v[var13] = var14.field_3674;
         var1.N[var13] = var14.field_37938;
         var1.z[var13] = var14.field_37939;
         var1.x[var13] = var14.field_37940;
         var1.y[var13] = ((ScreenHook)(Object)var14).purxiy$bend();
      }

      return var1;
   }

   private static void h(class_591 var0, long var1, Skins3DRestPose var3) {
      long var4 = 83201962043412L;
      byte var6 = 0;
      int var7 = 1269561188;
      int var8 = (int)(var4 << 48 >>> 48);
      SkinPreset.ensureSourceClassInitialized();
      class_630[] var9 = o((char)var6, var7, var8, var0);

      for (int var10 = 0; var10 < 7; var10++) {
         class_630 var11 = var9[var10];
         long var12 = 94498475315643L;
         int var14 = var12 < 0L ? -1 : 1;
         var11.field_3657 = var3.V[var10];
         var11.field_3656 = var3.b[var10];
         var11.field_3655 = var3.F[var10];
         var11.field_3654 = var3.d[var10];
         var11.field_3675 = var3.D[var10];
         var11.field_3674 = var3.v[var10];
         var11.field_37938 = var3.N[var10];
         var11.field_37939 = var3.z[var10];
         var11.field_37940 = var3.x[var10];
      }

      ((ScreenHook)(Object)var0.field_3401).purxiy$bend(var3.y[2], -1.0F);
      long var15 = 94498475315643L;
      int var16 = var15 < 0L ? -1 : 1;
      ((ScreenHook)(Object)var0.field_27433).purxiy$bend(var3.y[3], -1.0F);
      ((ScreenHook)(Object)var0.field_3392).purxiy$bend(var3.y[4], 1.0F);
      ((ScreenHook)(Object)var0.field_3397).purxiy$bend(var3.y[5], 1.0F);
   }

   static void devHabit(int var0) {
      n = var0;
   }

   public static void clear() {
      V.clear();
      x.clear();
   }

   private static float d(float var0, float var1, float var2, float var3) {
      return var0 + (var1 - var0) * (1.0F - (float)Math.exp(-var2 * var3));
   }

   private static float E(float var0, long var1) {
      SkinPreset.ensureSourceClassInitialized();
      long var3 = 22742797050650L;
      int var6 = var3 < 0L ? -1 : 1;
      float var6x = Math.max(0.0F, Math.min(1.0F, var0));
      long var7 = 22742797050651L;
      int var9 = var7 < 0L ? -1 : 1;
      if (var6x < 0.22F) {
         float var13 = var6x / 0.22F;
         return 1.0F - (1.0F - var13) * (1.0F - var13) * (1.0F - var13);
      } else {
         float var9x = (var6x - 0.22F) / 0.78F;
         long var10 = 22742797050651L;
         int var11 = var10 < 0L ? -1 : 1;
         return 1.0F - (var9x < 0.5F ? 4.0F * var9x * var9x * var9x : 1.0F - (float)Math.pow(-2.0F * var9x + 2.0F, 3.0) / 2.0F);
      }
   }

   private static final class SkinTransform {
      long b;
      double v;
      boolean c;
      float N;
      float Y;
      float f;
      float C;
      float y;
      float J;
      float G;
      float D = 1.0F;
      float M;
      float w;
      boolean U;
      float A;
      float k = 4.0F;
      int K;
      int Q;
      float q;
   }
}
