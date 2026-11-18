// Sourced By DevFFNightmare
package dev.purxiy.modules.hitsplash;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import it.unimi.dsi.fastutil.ints.Int2DoubleMap;
import it.unimi.dsi.fastutil.ints.Int2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_1657;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3726;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import net.minecraft.class_6880;
import net.minecraft.class_746;
import net.minecraft.class_8110;
import net.minecraft.class_8111;
import net.minecraft.class_2350.class_2351;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

public final class HitSplashRenderer {
   static final int m = 128;
   static final int H = 96;
   static final int e = 16;
   private static final int O = 104;
   private static final float P = 22.0F;
   private static final float V = 0.9F;
   private static final float a = 2.5F;
   private static final float E = 0.5F;
   static final float C = 0.3F;
   static final float U = 0.14F;
   private static final float r = 1.3F;
   private static final float n = 0.32F;
   private static final float t = 0.9F;
   private static final float g = 0.8F;
   private static final float I = 0.55F;
   private static final float b = 0.08F;
   private static final float f = 0.8F;
   private static final float S = 1.2F;
   private static final double y = 48.0;
   private static final double z = 4.0;
   static final List<HitSplashRenderer.TypeW> u = new ArrayList<>();
   static final List<HitSplashRenderer.TypeA> T = new ArrayList<>();
   private static final Int2ObjectMap<HitSplashRenderer.TypeX> l = new Int2ObjectOpenHashMap();
   private static final Int2DoubleMap s = new Int2DoubleOpenHashMap();
   
   private static class_638 W;
   static double L;
   private static long F = 0L;
   private static boolean j;
   static final Matrix4f B = new Matrix4f();
   static final Matrix4f D = new Matrix4f();
   static float c;
   static double d;
   static double N;
   static double v;
   
   static GpuBufferSlice J;
   private static boolean o;
   private static boolean w;
   private static double Y;
   private static String p;
   static HitSplashModule k;
   private static ModuleContext Q;
   private static int A = 0;
   private static int q = 0;
   public static int x = 0;

   private static final String[] i = new String[4];
   private static final long[] G = new long[]{
      6941244044879431820L,
      3036792270769396450L,
      719900644807567439L,
      2613087945058748847L,
      -4033834117065263227L,
      -1515034989791502231L,
      -2102817760537827533L
   };


   private HitSplashRenderer() {
   }

   public static void onAttack(class_1657 var0, class_1297 var1) {
      long var2 = 125222608283788L;
      long var4 = var2 ^ 116722645381241L;
      class_310 var6 = class_310.method_1551();
      if (var0 == var6.field_1724 && k.enabled() && var1 instanceof class_1309) {
         class_243 var7 = var0.method_33571();
         class_243 var8 = Q(var1.method_5829(), var7, var4, var7.method_1019(var0.method_5828(1.0F).method_1021(8.0)));
         float var9 = var0.method_7261(0.5F);
         boolean var10 = var9 > 0.9F
            && var0.field_6017 > 0.0
            && !var0.method_24828()
            && !var0.method_6101()
            && !var0.method_5799()
            && !var0.method_74025()
            && !var0.method_5765()
            && !var0.method_5624();
         l.put(var1.method_5628(), new HitSplashRenderer.TypeX(L, var8.method_1020(var1.method_73189()), var7, var9, var10));
      }
   }

   public static void onDamage( class_1297 var0, class_6880<class_8110> var1, int var2, int var3, Optional<class_243> var4) {
      long var5 = 11739345172159L;
      long var7 = var5 ^ 19131759504970L;
      class_310 var9 = class_310.method_1551();
      class_746 var10 = var9.field_1724;
      class_638 var11 = var9.field_1687;
      HitSplashModule var12 = k;
      if (var12.enabled()
         && var10 != null
         && var11 != null
         && var0 instanceof class_1309 var13
         && var0 != var10
         && !(var0 instanceof class_1531)
         && !var0.method_5756(var10)) {
         HitSplashRenderer.TypeX var14 = (HitSplashRenderer.TypeX)l.get(var0.method_5628());
         boolean var15 = var14 != null && L - var14.Z < 0.8F;
         class_1297 var16 = var2 >= 0 ? var11.method_8469(var2) : null;
         class_1297 var17 = var3 >= 0 ? var11.method_8469(var3) : null;
         boolean var18 = var16 == var10 || var2 < 0 && var3 < 0 && var15 && var1.method_40225(class_8111.field_42348);
         boolean var19 = !var18 && var12.y.enabled() && var16 instanceof class_1657 && var16 != var10 && var16.method_5858(var10) < 2304.0;
         if (var18 || var19) {
            class_238 var20 = var13.method_5829();
            float var21 = 1.0F;
            boolean var22 = false;
            class_243 var23;
            class_243 var24;
            if (!var18 || !var15 || var17 != null && var17 != var10) {
               if (var17 != null && var17 != var16) {
                  class_243 var25 = var17.method_18798();
                  var24 = var17.method_73189().method_1020(var25.method_1021(3.0));
                  var23 = Q(var20, var24, var7, var17.method_73189().method_1019(var25.method_1021(2.0)));
               } else if (var4.isPresent()) {
                  var24 = (class_243)var4.get();
                  var23 = Q(var20, var24, var7, var20.method_1005());
               } else if (var16 != null) {
                  var24 = var16.method_33571();
                  var23 = Q(var20, var24, var7, var20.method_1005());
               } else {
                  var24 = var10.method_33571();
                  var23 = Q(var20, var24, var7, var20.method_1005());
               }
            } else {
               var23 = var13.method_73189().method_1019(var14.A);
               var24 = var14.B;
               var21 = var14.D;
               var22 = var14.N;
            }

            double var33 = var23.field_1352 - var24.field_1352;
            double var27 = var23.field_1350 - var24.field_1350;
            double var29 = Math.sqrt(var33 * var33 + var27 * var27);
            if (var29 < 0.001) {
               double var31 = Math.toRadians(var13.method_36454());
               var33 = -Math.sin(var31);
               var27 = Math.cos(var31);
            } else {
               var33 /= var29;
               var27 /= var29;
            }

            splash(var11, var13, var23, var33, var27, var21, var22 ? 1.3F : 1.0F);
            if (var18) {
               s.put(var0.method_5628(), L);
            }
         }
      }
   }

   public static void onEntityEvent( class_1297 var0, byte var1) {
      long var2 = 44957226823556L;
      long var4 = var2 ^ 76079031247570L;
      if (var1 == 3 && var0 instanceof class_1309 var6 && k.enabled()) {
         double var7 = s.getOrDefault(var0.method_5628(), Double.NEGATIVE_INFINITY);
         class_638 var9 = class_310.method_1551().field_1687;
         if (var9 != null && !(L - var7 > 1.2F)) {
            s.remove(var0.method_5628());
            HitSplashRenderer.TypeX var10 = (HitSplashRenderer.TypeX)l.get(var0.method_5628());
            class_243 var11 = var6.method_5829().method_1005();
            double var12 = 1.0;
            double var14 = 0.0;
            if (var10 != null) {
               double var16 = var11.field_1352 - var10.B.field_1352;
               double var18 = var11.field_1350 - var10.B.field_1350;
               double var20 = Math.sqrt(var16 * var16 + var18 * var18);
               if (var20 > 0.001) {
                  var12 = var16 / var20;
                  var14 = var18 / var20;
               }
            }

            j(var9, var6, var4, var11, var12, var14);
         }
      }
   }

   private static class_243 Q(class_238 var0, class_243 var1, long var2, class_243 var4) {
      HitSplashRenderer2.ensureSourceClassInitialized();
      double var5 = var0.field_1325 - var0.field_1322;
      double var7 = var0.field_1322 + var5 * 0.42;
      double var9 = var0.field_1322 + var5 * 0.82;
      if (var1.method_1025(var4) > 1.0E-6) {
         Optional var11 = var0.method_1014(0.05)
            .method_992(var1, var1.method_1019(var4.method_1020(var1).method_1029().method_1021(var1.method_1022(var4) + 4.0)));
         if (var11.isPresent()) {
            class_243 var24 = (class_243)var11.get();
            return new class_243(var24.field_1352, Math.clamp(var24.field_1351, var7, var9), var24.field_1350);
         }
      }

      class_243 var23 = new class_243(
         (var0.field_1323 + var0.field_1320) * 0.5, var0.field_1322 + (var0.field_1325 - var0.field_1322) * 0.62, (var0.field_1321 + var0.field_1324) * 0.5
      );
      double var12 = var1.field_1352 - var23.field_1352;
      double var14 = var1.field_1350 - var23.field_1350;
      double var16 = Math.sqrt(var12 * var12 + var14 * var14);
      long var18 = 30559544088821L;
      if (30559544088821L == 0L) {
         boolean var10000 = false;
      } else {
         int var25 = var18 < 0L ? -1 : 1;
      }

      if (var16 < 0.001) {
         return var23;
      } else {
         double var21 = (var0.field_1320 - var0.field_1323) * 0.5;
         return var23.method_1031(var12 / var16 * var21, 0.0, var14 / var16 * var21);
      }
   }

   static void splash(class_638 var0, class_1309 var1, class_243 var2, double var3, double var5, float var7, float var8) {
      long var9 = 90889729139995L;
      long var11 = var9 ^ 11129812250656L;
      long var13 = var9 ^ 129361506629664L;
      ThreadLocalRandom var15 = ThreadLocalRandom.current();
      float var16 = k.N.floatValue();
      float var17 = Math.clamp(var7, 0.3F, 1.0F) * var8;
      class_243 var18 = var1.method_73189();
      double var19 = var18.field_1352 - var3 * 0.1;
      double var21 = var18.field_1350 - var5 * 0.1;
      double var23 = y(var0, var13, var19, var18.field_1351 + 0.3, var21);
      if (!Double.isNaN(var23)) {
         float var25 = (float)Math.atan2(-var5, -var3) + (float)var15.nextGaussian() * 0.6F;
         addSplat(
            new HitSplashRenderer.TypeW(
               var19, var23, var21, 0.5F * var16 * (0.72F + 0.28F * Math.min(var17, 1.2F)), var25, var15.nextInt(4), var15.nextBoolean(), true, var17
            )
         );
      }

      c(
         var11,
         new HitSplashRenderer.TypeA(var2.field_1352, var2.field_1351, var2.field_1350, 0.0, 0.0, 0.0, 0.08F * var16 * (0.7F + 0.3F * var17), var17, true)
      );
      int var43 = Math.round((6.0F + 4.0F * var15.nextFloat()) * Math.min(var17, 1.3F));
      float var26 = (float)Math.sqrt(var16);

      for (int var27 = 0; var27 < var43; var27++) {
         float var28 = var15.nextFloat();
         double var29;
         float var31;
         if (var28 < 0.25F) {
            var29 = var15.nextGaussian() * 0.45;
            var31 = 2.4F + 2.4F * var15.nextFloat();
         } else if (var28 < 0.7F) {
            var29 = (var15.nextBoolean() ? 1.0 : -1.0) * (1.45 + var15.nextGaussian() * 0.4);
            var31 = 1.8F + 1.8F * var15.nextFloat();
         } else {
            var29 = Math.PI + var15.nextGaussian() * 0.55;
            var31 = 1.4F + 1.6F * var15.nextFloat();
         }

         double var32 = Math.cos(var29);
         double var34 = Math.sin(var29);
         double var36 = (var3 * var32 - var5 * var34) * var31 * var26;
         double var38 = (var3 * var34 + var5 * var32) * var31 * var26;
         double var40 = 0.8 + 2.0 * var15.nextDouble();
         float var42 = (0.028F + 0.027F * var15.nextFloat()) * var16;
         c(
            var11,
            new HitSplashRenderer.TypeA(
               var2.field_1352 + var15.nextGaussian() * 0.04,
               var2.field_1351 + var15.nextGaussian() * 0.04,
               var2.field_1350 + var15.nextGaussian() * 0.04,
               var36,
               var40,
               var38,
               var42,
               var17,
               false
            )
         );
      }
   }

   private static void j(class_638 var0, class_1309 var1, long var2, class_243 var4, double var5, double var7) {
      long var9 = 97541769499963L;
      long var11 = 42944180690235L;
      HitSplashRenderer2.ensureSourceClassInitialized();
      ThreadLocalRandom var13 = ThreadLocalRandom.current();
      float var14 = k.N.floatValue();
      class_243 var15 = var1.method_73189();
      double var16 = y(var0, var11, var15.field_1352, var15.field_1351 + 0.3, var15.field_1350);
      if (!Double.isNaN(var16)) {
         addSplat(
            new HitSplashRenderer.TypeW(
               var15.field_1352, var16, var15.field_1350, 0.5F * var14 * 1.3F, (float)Math.atan2(var7, var5), var13.nextInt(4), var13.nextBoolean(), true, 1.4F
            )
         );
      }

      c(var9, new HitSplashRenderer.TypeA(var4.field_1352, var4.field_1351, var4.field_1350, 0.0, 0.0, 0.0, 0.2F * var14, 1.4F, true));
      byte var18 = 12;

      for (int var19 = 0; var19 < var18; var19++) {
         double var20 = (var19 + var13.nextDouble() * 0.6) / var18 * Math.PI * 2.0;
         float var22 = (1.8F + 1.4F * var13.nextFloat()) * (float)Math.sqrt(var14);
         c(
            var9,
            new HitSplashRenderer.TypeA(
               var4.field_1352,
               var4.field_1351,
               var4.field_1350,
               Math.cos(var20) * var22,
               1.6 + 1.6 * var13.nextDouble(),
               Math.sin(var20) * var22,
               (0.03F + 0.025F * var13.nextFloat()) * var14,
               1.2F,
               false
            )
         );
      }
   }

   private static double y(class_638 var0, long var1, double var3, double var5, double var7) {
      HitSplashRenderer2.ensureSourceClassInitialized();
      class_243 var9 = new class_243(var3, var5, var7);
      class_3965 var10 = var0.method_17742(
         new class_3959(var9, var9.method_1031(0.0, -4.0, 0.0), class_3960.field_17558, class_242.field_1347, class_3726.method_16194())
      );
      return var10.method_17783() == class_240.field_1332
            && var10.method_17780() == class_2350.field_11036
            && var0.method_8316(var10.method_17777()).method_15769()
         ? var10.method_17784().field_1351
         : Double.NaN;
   }

   private static void c(long var0, HitSplashRenderer.TypeA var2) {
      HitSplashRenderer2.ensureSourceClassInitialized();
      long var3 = 97541769499963L;
      if (97541769499963L == 0L) {
         boolean var10000 = false;
      } else {
         int var6 = var3 < 0L ? -1 : 1;
      }

      if (T.size() >= 96) {
         T.removeFirst();
      }

      var2.h = L;
      T.add(var2);
   }

   static void addSplat(HitSplashRenderer.TypeW var0) {
      var0.i = L;
      float var1 = (var0.f ? 0.55F : 0.08F) * Math.min(var0.Q, 1.3F);

      for (HitSplashRenderer.TypeW var3 : u) {
         double var4 = var3.M - var0.M;
         double var6 = var3.o - var0.o;
         double var8 = var4 * var4 + var6 * var6;
         if (var8 < 2.560000076293946 && Math.abs(var3.z - var0.z) < 1.0) {
            var3.q = Math.min(var3.flare(L) + var1 * (float)Math.exp(-var8 / 1.280000038146973), 2.5F);
            var3.e = L;
         }
      }

      if (u.size() >= 128) {
         u.removeFirst();
      }

      u.add(var0);
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2, GpuBufferSlice var3) {
      j = k.enabled();
      if (j) {
         B.set(var2).mul(var1);
         B.invert(D);
         c = var2.m11();
         class_243 var4 = var0.method_71156();
         d = var4.field_1352;
         N = var4.field_1351;
         v = var4.field_1350;
         J = var3;
      }
   }

   public static void render() {
      long var0 = 68704788641583L;
      long var2 = var0 ^ 6655842782905L;
      long var4 = var0 ^ 21285035852024L;
      boolean var6 = j;
      j = false;
      class_310 var7 = class_310.method_1551();
      class_638 var8 = var7.field_1687;
      if (var8 != W) {
         W = var8;
         clear();
      }

      if (var6 && var8 != null) {
         long var9 = System.nanoTime();
         float var11 = F == 0L ? 0.0F : Math.min((float)(var9 - F) / 1.0E9F, 0.1F);
         F = var9;
         boolean var12 = false;
         if (!var7.method_1493()) {
            L += var11;
            h(var4, var8, var11);
         }

         var12 = false;
         e(var2);
         if (!u.isEmpty() || !T.isEmpty()) {
            try {
               HitSplashRenderer3.draw(var7);
            } catch (Throwable var15) {
               metric("draw_failures");
               String var14 = var15.toString();
               if (!var14.equals(p)) {
                  p = var14;
                  ClientServices.LOGGER.error("Purxiy hit splash failed", var15);
               }
            }
         }
      } else {
         if (!u.isEmpty() || !T.isEmpty()) {
            clear();
         }

         F = 0L;
      }
   }

   private static void h(long var0, class_638 var2, float var3) {
      long var4 = 109742258750740L;
      HitSplashRenderer2.ensureSourceClassInitialized();
      if (!T.isEmpty() && !(var3 <= 0.0F)) {
         int var6 = Math.max(1, (int)Math.ceil(var3 / 0.025F));
         float var7 = var3 / var6;
         int var8 = 0;

         while (var8 < var6) {
            for (HitSplashRenderer.TypeA var10 : T) {
               long var11 = 49647062431702L;
               if (49647062431702L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var20 = var11 < 0L ? -1 : 1;
               }

               long var14 = 49647062431702L;
               if (49647062431702L == 0L) {
                  boolean var21 = false;
               } else {
                  int var22 = var14 < 0L ? -1 : 1;
               }

               if (!var10.N) {
                  long var17 = 49647062431702L;
                  if (49647062431702L == 0L) {
                     boolean var23 = false;
                  } else {
                     int var24 = var17 < 0L ? -1 : 1;
                  }

                  if (!var10.b) {
                     Y(var2, var4, var10, var7);
                  }
               }
            }

            var8++;
            long var19 = 49647062431703L;
            if (49647062431703L == 0L) {
               boolean var25 = false;
            } else {
               int var26 = var19 < 0L ? -1 : 1;
            }
         }
      }
   }

   private static void Y(class_638 var0, long var1, HitSplashRenderer.TypeA var3, float var4) {
      var3.r -= 22.0F * var4;
      double var5 = Math.exp(-0.9F * var4);
      HitSplashRenderer2.ensureSourceClassInitialized();
      var3.P *= var5;
      var3.r *= var5;
      var3.f *= var5;
      class_243 var7 = new class_243(var3.i, var3.D, var3.l);
      class_243 var8 = var7.method_1031(var3.P * var4, var3.r * var4, var3.f * var4);
      class_3965 var9 = var0.method_17742(new class_3959(var7, var8, class_3960.field_17558, class_242.field_1347, class_3726.method_16194()));
      if (var9.method_17783() != class_240.field_1332) {
         var3.i = var8.field_1352;
         var3.D = var8.field_1351;
         var3.l = var8.field_1350;
      } else {
         class_243 var10 = var9.method_17784();
         long var11 = 109742258750740L;
         if (109742258750740L == 0L) {
            boolean var10000 = false;
         } else {
            int var39 = var11 < 0L ? -1 : 1;
         }

         if (!var0.method_8316(var9.method_17777()).method_15769()) {
            var3.b = true;
         } else {
            class_2350 var14 = var9.method_17780();
            long var15 = 109742258750740L;
            if (109742258750740L == 0L) {
               boolean var40 = false;
            } else {
               int var41 = var15 < 0L ? -1 : 1;
            }

            int var17 = HitSplashRenderer.TypeP.S[var14.ordinal()];
            long var18 = 109742258750740L;
            if (109742258750740L == 0L) {
               boolean var42 = false;
            } else {
               int var43 = var18 < 0L ? -1 : 1;
            }

            switch (var17) {
               case 1:
                  var3.b = true;
                  long var20 = 109742258750739L;
                  if (109742258750739L == 0L) {
                     boolean var50 = false;
                  } else {
                     int var51 = var20 < 0L ? -1 : 1;
                  }

                  double var22 = Math.sqrt(var3.P * var3.P + var3.f * var3.f);
                  ThreadLocalRandom var24 = ThreadLocalRandom.current();
                  addSplat(
                     new HitSplashRenderer.TypeW(
                        var10.field_1352,
                        var10.field_1351,
                        var10.field_1350,
                        var3.n * (2.7F + 0.45F * (float)var22),
                        (float)Math.atan2(var3.f, var3.P),
                        4 + var24.nextInt(4),
                        var24.nextBoolean(),
                        false,
                        var3.G
                     )
                  );
                  break;
               case 2:
                  long var25 = 109742258750739L;
                  if (109742258750739L == 0L) {
                     boolean var48 = false;
                  } else {
                     int var49 = var25 < 0L ? -1 : 1;
                  }

                  var3.i = var10.field_1352;
                  var3.D = var10.field_1351 - 0.01;
                  var3.l = var10.field_1350;
                  var3.r = -Math.abs(var3.r) * 0.3;
                  break;
               default:
                  double var27 = var10.field_1352;
                  double var29 = var14.method_10148() * 0.01;
                  long var31 = 109742258750740L;
                  if (109742258750740L == 0L) {
                     boolean var44 = false;
                  } else {
                     int var45 = var31 < 0L ? -1 : 1;
                  }

                  var3.i = var27 + var29;
                  var3.D = var10.field_1351;
                  var3.l = var10.field_1350 + var14.method_10165() * 0.01;
                  if (var14.method_10166() == class_2351.field_11048) {
                     double var34 = -var3.P;
                     long var36 = 109742258750739L;
                     if (109742258750739L == 0L) {
                        boolean var46 = false;
                     } else {
                        int var47 = var36 < 0L ? -1 : 1;
                     }

                     var3.P = var34 * 0.15;
                     var3.f *= 0.5;
                  } else {
                     var3.f = -var3.f * 0.15;
                     var3.P *= 0.5;
                  }
            }
         }
      }
   }

   private static void e(long var0) {
      T.removeIf(var0x -> {
         HitSplashRenderer2.ensureSourceClassInitialized();
         boolean var1 = false;
         return var0x.b || L - var0x.h > (var0x.N ? 0.16F : 2.5F) || var0x.D < -512.0;
      });
      HitSplashRenderer2.ensureSourceClassInitialized();
      float var2 = k.J.floatValue();
      u.removeIf(var1 -> {
         HitSplashRenderer2.ensureSourceClassInitialized();
         return var1.life(L, var2) >= 1.0F;
      });
      int var3 = 0;

      for (HitSplashRenderer.TypeW var5 : u) {
         if (var5.T < 0.0) {
            var3++;
         }
      }

      for (int var11 = 0; var11 < u.size() && var3 > 104; var11++) {
         HitSplashRenderer.TypeW var13 = u.get(var11);
         long var6 = 62058982832533L;
         if (62058982832533L == 0L) {
            boolean var10000 = false;
         } else {
            int var14 = var6 < 0L ? -1 : 1;
         }

         long var9 = 62058982832534L;
         if (62058982832534L == 0L) {
            boolean var15 = false;
         } else {
            int var16 = var9 < 0L ? -1 : 1;
         }

         if (var13.T < 0.0) {
            var13.T = L;
            var3--;
         }
      }

      long var12 = 62058982832534L;
      if (62058982832534L == 0L) {
         boolean var17 = false;
      } else {
         int var18 = var12 < 0L ? -1 : 1;
      }
   }

   public static void previewHit(class_1309 var0, class_243 var1, boolean var2) {
      long var3 = 71887795463429L;
      long var5 = var3 ^ 99688812789232L;
      class_638 var7 = class_310.method_1551().field_1687;
      if (var7 != null) {
         class_243 var8 = Q(var0.method_5829(), var1, var5, var0.method_5829().method_1005());
         double var9 = var8.field_1352 - var1.field_1352;
         double var11 = var8.field_1350 - var1.field_1350;
         double var13 = Math.max(Math.sqrt(var9 * var9 + var11 * var11), 0.001);
         splash(var7, var0, var8, var9 / var13, var11 / var13, 1.0F, var2 ? 1.3F : 1.0F);
      }
   }

   public static void previewKill(class_1309 var0, class_243 var1) {
      long var2 = 15914791506219L;
      long var4 = var2 ^ 109588763876477L;
      class_638 var6 = class_310.method_1551().field_1687;
      if (var6 != null) {
         class_243 var7 = var0.method_5829().method_1005();
         double var8 = var7.field_1352 - var1.field_1352;
         double var10 = var7.field_1350 - var1.field_1350;
         double var12 = Math.max(Math.sqrt(var8 * var8 + var10 * var10), 0.001);
         j(var6, var0, var4, var7, var8 / var12, var10 / var12);
      }
   }

   public static void clear() {
      u.clear();
      T.clear();
      l.clear();
      s.clear();
   }

   private static void H(long var0, class_310 var2, int var3, class_638 var4, boolean var5) {
      long var6 = 85701429597298L;
      long var8 = var6 ^ 94810757237895L;
      HitSplashRenderer2.ensureSourceClassInitialized();
      class_746 var10 = var2.field_1724;
      if (var10 != null) {
         class_1309 var11 = null;
         if (var2.field_1765 instanceof class_3966 var12 && var12.method_17782() instanceof class_1309 var13) {
            var11 = var13;
         } else {
            double var30 = 64.0;

            for (class_1297 var17 : var4.method_18112()) {
               int var18 = (var17 instanceof class_1309 ? 1 : 0);
               long var19 = 19952L;
               if (19952L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var39 = var19 < 0L ? -1 : 1;
               }

               if ((var18 != 0)) {
                  class_1309 var22 = (class_1309)var17;
                  long var23 = 19953L;
                  if (19953L == 0L) {
                     boolean var40 = false;
                  } else {
                     int var41 = var23 < 0L ? -1 : 1;
                  }

                  if (var17 != var10) {
                     var18 = (var17 instanceof class_1531 ? 1 : 0);
                     int var21 = -347827086;
                     if ((var18 == 0)) {
                        double var26;
                        var18 = (var26 = var17.method_5858(var10) - var30) == 0.0 ? 0 : (var26 < 0.0 ? -1 : 1);
                        var21 = -347827086;
                        if (var18 < 0) {
                           var30 = var17.method_5858(var10);
                           var11 = var22;
                        }
                     }
                  }
               }
            }
         }

         long var28 = 19953L;
         if (19953L == 0L) {
            boolean var42 = false;
         } else {
            int var43 = var28 < 0L ? -1 : 1;
         }

         if (var11 != null) {
            class_243 var15 = var10.method_33571();
            class_243 var31 = Q(var11.method_5829(), var15, var8, var11.method_5829().method_1005());
            double var32 = var31.field_1352 - var15.field_1352;
            double var35 = var31.field_1350 - var15.field_1350;
            double var37 = Math.max(Math.sqrt(var32 * var32 + var35 * var35), 0.001);
            double var38 = var32 / var37;
            double var25 = var35 / var37;
            splash(var4, var11, var31, var38, var25, 1.0F, 1.0F);
            s.put(var11.method_5628(), L);
            l.put(var11.method_5628(), new HitSplashRenderer.TypeX(L, var31.method_1020(var11.method_73189()), var15, 1.0F, false));
         }
      }
   }

   static float smoothstep(float var0, float var1, float var2) {
      float var3 = Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

   public static void install(HitSplashModule var0, ModuleContext var1) {
      k = var0;
      Q = var1;
   }

   public static void metric(String var0) {
      if (Q != null) {
         Q.metric(var0, var0.equals("drawn") ? ++A : ++q);
      }
   }

   public static void uninstall() {
      clear();
      W = null;
      j = false;
      k = null;
      Q = null;
      F = 0L;
   }

static final class TypeA {
      static final float d = 0.16F;
      double i;
      double D;
      double l;
      double P;
      double r;
      double f;
      final float n;
      final float G;
      final boolean N;
      double h;
      boolean b;

      TypeA(double var1, double var3, double var5, double var7, double var9, double var11, float var13, float var14, boolean var15) {
         this.i = var1;
         this.D = var3;
         this.l = var5;
         this.P = var7;
         this.r = var9;
         this.f = var11;
         this.n = var13;
         this.G = var14;
         this.N = var15;
      }
   }

   static class TypeP {
      static final int[] S = new int[class_2350.values().length];

      static {
         try {
            S[class_2350.field_11036.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
         }

         try {
            S[class_2350.field_11033.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
         }
      }
   }

   static final class TypeW {
      final double M;
      final double z;
      final double o;
      final float t;
      final float a;
      final float p;
      final int Y;
      final boolean f;
      final float Q;
      double i;
      float q;
      double e;
      double T = -1.0;

      TypeW(double var1, double var3, double var5, float var7, float var8, int var9, boolean var10, boolean var11, float var12) {
         this.M = var1;
         this.z = var3;
         this.o = var5;
         this.t = var7;
         this.a = (float)Math.cos(var8);
         this.p = (float)Math.sin(var8);
         this.Y = var9 + (var10 ? 8 : 0);
         this.f = var11;
         this.Q = var12;
      }

      float flare(double var1) {
         return this.q * (float)Math.exp(-(var1 - this.e) / 0.9F);
      }

      float life(double var1, float var3) {
         float var4 = (float)((var1 - this.i) / var3);
         if (this.T >= 0.0) {
            var4 = Math.max(var4, 0.7F) + (float)(var1 - this.T) / 0.5F * 0.3F;
         }

         return var4;
      }

      float heat(double var1, float var3) {
         float var4 = (float)(var1 - this.i);
         float var5 = this.life(var1, var3);
         float var6 = 0.32F + 0.68F * (float)Math.exp(-var4 / 1.3F) + this.flare(var1);
         return var6 * this.Q * (this.f ? 1.0F : 0.45F) * (1.0F - HitSplashRenderer.smoothstep(0.55F, 1.0F, var5));
      }
   }

   private record TypeX(double Z, class_243 A, class_243 B, float D, boolean N) {
      public double at() {
         return this.Z;
      }

      public class_243 offset() {
         return this.A;
      }

      public class_243 eye() {
         return this.B;
      }

      public float charge() {
         return this.D;
      }

      public boolean crit() {
         return this.N;
      }

      public double doubleState41() {
         return this.Z;
      }

      public class_243 minecraftObjectState6() {
         return this.A;
      }

      public class_243 minecraftObjectState7() {
         return this.B;
      }

      public float floatState24() {
         return this.D;
      }

      public boolean booleanState39() {
         return this.N;
      }
   }
}
