package dev.purxiy.modules.skins3d;

import dev.purxiy.loader.fabric.ClientServices;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.List;
import java.util.Locale;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_10055;
import net.minecraft.class_1132;
import net.minecraft.class_1297;
import net.minecraft.class_1306;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_5218;
import net.minecraft.class_591;
import net.minecraft.class_630;
import net.minecraft.class_638;
import net.minecraft.class_642;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import net.minecraft.class_572.class_573;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public final class Skins3DUpdater {
   private static final int l = 0;
   private static final int k = 1;
   private static final int n = 2;
   private static final double g = 6.0;
   private static Skins3DLoader v;
   private static Skins3DLoader.SkinSource X;
   private static class_638 u;
   private static String B = "";
   private static int i = 0;
   private static int D = 0;
   private static boolean x;
   private static class_243 T;
   private static long I = 0L;
   private static class_243 E;
   private static final Matrix4f Q = new Matrix4f();
   private static final Vector3f Y = new Vector3f();
   private static final String[] b = new String[9];
   private static final long c = 150000000L;

   private Skins3DUpdater() {
   }

   public static boolean unmasked() {
      long var0 = 10325309275380L;
      long var2 = var0 ^ 97244744342957L;
      int var4 = (int)((var0 ^ 97244744342957L) >>> 56);
      int var5 = (int)((var0 ^ 97244744342957L) << 8 >>> 32);
      int var6 = (int)(var2 << 40 >>> 40);
      long var7 = var0 ^ 58080579834982L;
      if (X == null) {
         return false;
      } else {
         return i != 0 ? Skins3DPose.bare(Y(var7), v((byte)var4, M(), var5, var6)) : X.r != null;
      }
   }

   static boolean active() {
      class_310 var0 = class_310.method_1551();
      return var0.field_1724 != null
         && var0.field_1687 != null
         && var0.field_1724.method_5805()
         && !var0.field_1724.method_7325()
         && Skins3DRig.h.enabled()
         && Skins3DRig.h.preset() == SkinPreset.B
         && SkinPreset.spiderTexture(var0.field_1724.method_52814().comp_1626().comp_3627());
   }

   public static boolean onKey(int var0) {
      long var1 = 28717034325412L;
      long var3 = var1 ^ 31485456131011L;
      int var5 = (int)((var1 ^ 31485456131011L) >>> 48);
      int var6 = (int)((var1 ^ 31485456131011L) << 16 >>> 48);
      int var7 = (int)(var3 << 32 >>> 32);
      class_310 var8 = class_310.method_1551();
      if (Skins3DRig.h.j.matches(var0) && var8.field_1755 == null && var8.method_18506() == null && active()) {
         u((short)var5, (char)var6, var7);
         return true;
      } else {
         return false;
      }
   }

   private static void u(short var0, char var1, int var2) {
      long var3 = 7408613910119L;
      long var5 = var3 ^ 93381984179943L;
      long var7 = var3 ^ 98932194936823L;
      SkinPreset.ensureSourceClassInitialized();
      if (X != null && i == 0) {
         class_310 var9 = class_310.method_1551();
         if (X.r == null) {
            i = 1;
         } else {
            label56: {
               Skins3DCamera var10 = X.r;
               boolean var11 = X.A.equals(x(var7));
               class_243 var12 = new class_243(var10.T, var10.L + 0.05, var10.Z);
               double var13 = var11 ? var9.field_1724.method_73189().method_1022(var12) : Double.POSITIVE_INFINITY;
               if (var11) {
                  double var15;
                  int var17 = (var15 = var13 - 6.0) == 0.0 ? 0 : (var15 < 0.0 ? -1 : 1);
                  boolean var18 = false;
                  if (var17 <= 0) {
                     boolean var19 = N(var5, var12);
                     var18 = false;
                     if (var19) {
                        if (!var10.A) {
                           return;
                        }

                        i = 2;
                        E = var12;
                        break label56;
                     }
                  }
               }

               var9.field_1724
                  .method_7353(
                     var11
                        ? class_2561.method_43469("purxiy.spiderman.mask_distance", new Object[]{Math.round(var13)})
                        : class_2561.method_43471("purxiy.spiderman.mask_other_dimension"),
                     true
                  );
               return;
            }
         }

         D = 0;
      }
   }

   private static boolean N(long var0, class_243 var2) {
      class_310 var3 = class_310.method_1551();
      SkinPreset.ensureSourceClassInitialized();
      class_243 var4 = var3.field_1724.method_33571();
      class_3965 var5 = var3.field_1687.method_17742(new class_3959(var4, var2, class_3960.field_17558, class_242.field_1348, var3.field_1724));
      return var5.method_17783() == class_240.field_1333 || var5.method_17784().method_1025(var2) < 0.12249999999999998;
   }

   public static void tick(class_310 var0) {
      long var1 = 138459955185221L;
      long var3 = var1 ^ 41488701390126L;
      long var5 = var1 ^ 104536597331187L;
      long var7 = var1 ^ 102499548938391L;
      long var9 = var1 ^ 51610699949769L;
      long var11 = var1 ^ 38118806464469L;
      long var13 = var1 ^ 61692117814925L;
      long var15 = var1 ^ 5829049601720L;
      int var17 = (int)((var1 ^ 5829049601720L) >>> 32);
      int var18 = (int)((var1 ^ 5829049601720L) << 32 >>> 40);
      int var19 = (int)(var15 << 56 >>> 56);
      if (var0.field_1687 != null && var0.field_1724 != null) {
         if (var0.field_1687 != u) {
            D(var5, var0);
         }

         if (X != null && !var0.method_1493() && !x) {
            if (Skins3DRig.h.enabled() && Skins3DRig.h.preset() == SkinPreset.B) {
               Skins3DFactory.get(SkinPreset.T, false);
               Skins3DFactory.model(SkinPreset.H);
               Skins3DFactory.model(SkinPreset.q);
            }

            if (i != 0 && !active()) {
               cancelAction();
            }

            if (i == 1) {
               int var20 = D++;
               if (W(var3, var20, 0.3F)) {
                  p(0.35F, var7, 0.85F);
               }

               if (W(var3, var20, 0.95F)) {
                  p(0.5F, var7, 1.35F);
               }

               if (D >= J(1.85F) && X.r == null) {
                  V(var0, var13);
               }

               if (D >= J(2.3F)) {
                  i = 0;
               }
            } else if (i == 2) {
               int var22 = D++;
               if (W(var3, var22, 0.3F)) {
                  w(var17, var18, (byte)var19, class_3417.field_14596, 0.6F, 1.75F);
               }

               if (D >= J(0.6F) && X.r != null) {
                  X.r = null;
                  C(var9);
                  p(0.45F, var7, 1.6F);
               }

               if (W(var3, var22, 1.35F)) {
                  p(0.55F, var7, 1.0F);
               }

               if (D >= J(1.95F)) {
                  i = 0;
                  E = null;
               }
            }

            Skins3DCamera var23 = X.r;
            if (var23 != null && i != 2 && X.A.equals(x(var11)) && var0.field_1687.method_22340(class_2338.method_49637(var23.T, var23.L, var23.Z))) {
               boolean var21 = var23.A;
               var23.tick((var1x, var3x, var5x, var7x, var9x, var11x) -> {
                  class_238 var13x = new class_238(var1x - 0.205, var3x, var5x - 0.27, var1x + 0.205, var3x + 0.075, var5x + 0.27);
                  class_243 var14 = class_1297.method_20736(null, new class_243(var7x, var9x, var11x), var13x, var0.field_1687, List.of());
                  return new Skins3DCamera.Data(var14.field_1352, var14.field_1351, var14.field_1350);
               });
               if (var23.L < var0.field_1687.method_31607() - 32) {
                  X.r = null;
                  i = 0;
                  C(var9);
               } else if (var21 != var23.A) {
                  C(var9);
               }
            }
         }
      } else if (u != null) {
         leave();
      }
   }

   private static void V(class_310 var0, long var1) {
      long var3 = 102789260389643L;
      long var5 = 91384910323852L;
      long var7 = 104756426760592L;
      long var9 = 132631108749565L;
      short var11 = 30880;
      int var12 = 9838472;
      int var13 = (int)(var9 << 56 >>> 56);
      class_243 var14 = E(var3, 1.0F);
      double var15 = Math.toRadians(var0.field_1724.field_6283);
      class_243 var17 = new class_243(-Math.sin(var15) * 0.2, 0.13, Math.cos(var15) * 0.2);
      X.A = x(var7);
      X.r = new Skins3DCamera(
         var14.field_1352,
         var14.field_1351 - 0.1,
         var14.field_1350,
         var17.field_1352,
         var17.field_1351,
         var17.field_1350,
         (float)Math.toRadians(-var0.field_1724.field_6283)
      );
      C(var5);
      w(var11, var12, (byte)var13, class_3417.field_14873, 0.35F, 1.25F);
   }

   private static int J(float var0) {
      return Math.round(var0 * 20.0F);
   }

   private static boolean W(long var0, int var2, float var3) {
      SkinPreset.ensureSourceClassInitialized();
      int var4 = J(var3);
      long var5 = 97128256846698L;
      int var8 = var5 < 0L ? -1 : 1;
      if (var2 < var4) {
         long var8x = 97128256846699L;
         int var9 = var8x < 0L ? -1 : 1;
         if (var2 + 1 >= J(var3)) {
            return true;
         }
      }

      return false;
   }

   private static void p(float var0, long var1, float var3) {
      long var4 = 132631108749565L;
      short var6 = 30880;
      int var7 = 9838472;
      int var8 = (int)(var4 << 56 >>> 56);
      w(var6, var7, (byte)var8, (class_3414)class_3417.field_14581.comp_349(), var0, var3);
   }

   private static void w(int var0, int var1, byte var2, class_3414 var3, float var4, float var5) {
      SkinPreset.ensureSourceClassInitialized();
      class_310 var6 = class_310.method_1551();
      if (var6.field_1687 != null && var6.field_1724 != null) {
         var6.field_1687
            .method_8486(
               var6.field_1724.method_23317(),
               var6.field_1724.method_23320() - 0.2,
               var6.field_1724.method_23321(),
               var3,
               class_3419.field_15248,
               var4,
               var5,
               false
            );
      }
   }

   private static void D(long var0, class_310 var2) {
      long var3 = 91384910323852L;
      SkinPreset.ensureSourceClassInitialized();
      cancelAction();
      class_1132 var5 = var2.method_1576();
      long var6 = 38478256421558L;
      int var9 = var6 < 0L ? -1 : 1;
      String var9x;
      if (var5 != null) {
         var9x = "singleplayer:" + var2.method_1576().method_27050(class_5218.field_24188).toAbsolutePath().normalize();
      } else {
         class_642 var10 = var2.method_1558();
         long var11 = 38478256421557L;
         int var14 = var11 < 0L ? -1 : 1;
         if (var10 != null) {
            var9x = "server:" + var2.method_1558().field_3761.trim().toLowerCase(Locale.ROOT);
         } else {
            var9x = "connection:" + var2.field_1724.field_3944.method_48296().method_10755();
         }
      }

      try {
         String var20 = HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(var9x.getBytes(StandardCharsets.UTF_8)));
         long var21 = 38478256421558L;
         int var20x = var21 < 0L ? -1 : 1;
         long var14x = 38478256421557L;
         if (v == null) {
            v = new Skins3DLoader(FabricLoader.getInstance().getConfigDir().resolve("purxiy/spiderman-masks.json"));

            try {
               v.load();
            } catch (IOException var15) {
               ClientServices.LOGGER.warn("Could not read costume masks", var15);
            }
         }

         if (!var20.equals(B)) {
            C(var3);
            B = var20;
            X = v.world(B);
         }

         u = var2.field_1687;
      } catch (NoSuchAlgorithmException var16) {
         throw new IllegalStateException(var16);
      }
   }

   private static String x(long var0) {
      SkinPreset.ensureSourceClassInitialized();
      return u == null ? "" : u.method_27983().method_29177().toString();
   }

   public static void cancelAction() {
      i = 0;
      D = 0;
      x = false;
      E = null;
   }

   public static void leave() {
      long var0 = 120163825104159L;
      long var2 = var0 ^ 68534373848467L;
      cancelAction();
      C(var2);
      u = null;
      X = null;
      B = "";
      T = null;
   }

   private static void C(long var0) {
      SkinPreset.ensureSourceClassInitialized();
      long var2 = 91384910323851L;
      int var6 = var2 < 0L ? -1 : 1;
      if (v != null) {
         try {
            v.save();
         } catch (IOException var5) {
            ClientServices.LOGGER.warn("Could not save costume masks", var5);
         }
      }
   }

   private static int Y(long var0) {
      SkinPreset.ensureSourceClassInitialized();
      long var2 = 67856124373137L;
      int var5 = var2 < 0L ? -1 : 1;
      if (i == 1) {
         var5 = 1;
      } else {
         long var6 = 67856124373138L;
         int var7 = var6 < 0L ? -1 : 1;
         var5 = i == 2 ? 2 : 0;
      }

      return var5;
   }

   private static float v(byte var0, float var1, int var2, int var3) {
      return (D + (x ? 0.0F : var1)) / 20.0F;
   }

   private static float M() {
      return class_310.method_1551().method_61966().method_60637(false);
   }

   static void extract(class_10055 var0, Skins3DGroundPose var1) {
      long var2 = 89672487513511L;
      long var4 = var2 ^ 665767562494L;
      int var6 = (int)((var2 ^ 665767562494L) >>> 56);
      int var7 = (int)((var2 ^ 665767562494L) << 8 >>> 32);
      int var8 = (int)(var4 << 40 >>> 40);
      long var9 = var2 ^ 118989870318901L;
      if (i != 0 && X != null) {
         var1.S = Y(var9);
         var1.J = v((byte)var6, M(), var7, var8);
         float var11 = var1.J;
         boolean var12 = i == 1 ? var11 < 2.0F : var11 < 1.7F;
         boolean var13 = i == 2 && var11 > 0.6F && var11 < 1.7F;
         if (var12) {
            var0.field_55305.method_65605();
            var0.field_63601 = class_1799.field_8037;
            var0.field_55304 = class_573.field_3409;
            var1.h = var0.field_63604 <= 0.0F || var0.field_53408 != class_1306.field_6183;
         }

         if (var13) {
            var0.field_55307.method_65605();
            var0.field_63602 = class_1799.field_8037;
            var0.field_55306 = class_573.field_3409;
            var1.r = var0.field_63604 <= 0.0F || var0.field_53408 != class_1306.field_6182;
         }

         Skins3DCamera var14 = X.r;
         if (i == 2 && var14 != null) {
            class_243 var15 = new class_243(var0.field_53325, var0.field_53326 + 1.3 * var0.field_53453, var0.field_53327);
            Vector3f var16 = u(new class_243(var14.T, var14.L + 0.05, var14.Z).method_1020(var15), var0.field_53446).normalize();
            var1.C[0] = var16.x;
            var1.C[1] = var16.y;
            var1.C[2] = var16.z;
            var1.K = Math.clamp((float)Math.atan2(-var16.x, -var16.z), -1.1F, 1.1F);
            var1.E = Math.clamp((float)Math.atan2(var16.y, Math.hypot(var16.x, var16.z)), -0.6F, 1.0F);
         }
      }
   }

   static void attach(class_591 var0, class_10055 var1, Skins3DPose.PoseAngles var2, Skins3DRestPose var3, Vector3f var4) {
      class_310 var5 = class_310.method_1551();
      if (var5.field_1724 != null && var1.field_53528 == var5.field_1724.method_5628()) {
         if (var2.c) {
            Skins3DBuilder.SkinQuad var6 = Skins3DFactory.model(var2.B ? SkinPreset.q : SkinPreset.H);
            if (var6 != null) {
               class_630 var7 = var0.field_3394;
               Skins3DRig.set(var7, var6.R[0]);
               var7.field_3665 = true;
               var7.field_3657 = var2.k;
               var7.field_3656 = var2.L;
               var7.field_3655 = var2.A;
               var7.field_3654 = var2.G;
               var7.field_3675 = var2.n;
               var7.field_3674 = var2.b;
               var7.field_37938 = var2.u;
               var7.field_37939 = var2.e;
               var7.field_37940 = var2.x;
            }
         }

         byte var27 = 6;
         Q.translation(var3.V[var27], var3.b[var27], var3.F[var27]).rotateZYX(var3.v[var27], var3.D[var27], var3.d[var27]).transformPosition(Y.set(var4));
         double var28 = -Y.x / 16.0F * 0.9375;
         double var9 = -(Y.y / 16.0F - 1.501) * 0.9375;
         double var11 = Y.z / 16.0F * 0.9375;
         double var13 = Math.toRadians(180.0F - var1.field_53446);
         double var15 = Math.cos(var13);
         double var17 = Math.sin(var13);
         double var19 = var28 * var15 + var11 * var17;
         double var21 = -var28 * var17 + var11 * var15;
         double var23 = var1.field_53453;
         double var25 = var1.field_53410 ? -0.125 * var23 : 0.0;
         T = new class_243(var1.field_53325 + var19 * var23, var1.field_53326 + var25 + var9 * var23, var1.field_53327 + var21 * var23);
         I = System.nanoTime();
      }
   }

   private static Vector3f u(class_243 var0, float var1) {
      double var2 = -Math.toRadians(180.0F - var1);
      double var4 = Math.cos(var2);
      double var6 = Math.sin(var2);
      double var8 = var0.field_1352 * var4 + var0.field_1350 * var6;
      double var10 = -var0.field_1352 * var6 + var0.field_1350 * var4;
      return new Vector3f((float)(-var8), (float)(-var0.field_1351), (float)var10);
   }

   private static class_243 E(long var0, float var2) {
      long var3 = 11023773767292L;
      SkinPreset.ensureSourceClassInitialized();
      class_310 var5 = class_310.method_1551();
      if (T != null && System.nanoTime() - I < 150000000L) {
         return T;
      } else {
         class_243 var6 = j();
         class_243 var7 = new class_243(-var6.field_1350, 0.0, var6.field_1352);
         class_243 var8 = var5.field_1724
            .method_30950(var2)
            .method_1031(0.0, (var5.field_1724.method_18276() ? 1.05 : 1.3) * xd(var3), 0.0)
            .method_1019(var6.method_1021(0.45))
            .method_1019(var7.method_1021(-0.25));
         class_243 var9 = var5.field_1724.method_5836(var2);
         class_3965 var10 = var5.field_1687.method_17742(new class_3959(var9, var8, class_3960.field_17558, class_242.field_1348, var5.field_1724));
         return var10.method_17783() != class_240.field_1333 ? var9.method_35590(var10.method_17784(), 0.45) : var8;
      }
   }

   public static void handPose(class_630 var0) {
      long var1 = 112789792083806L;
      long var3 = var1 ^ 73222001949012L;
      long var5 = var1 ^ 61028958776839L;
      int var7 = (int)((var1 ^ 61028958776839L) >>> 56);
      int var8 = (int)((var1 ^ 61028958776839L) << 8 >>> 32);
      int var9 = (int)(var5 << 40 >>> 40);
      if (i != 0 && active()) {
         float var10 = v((byte)var7, M(), var8, var9);
         if (i == 1) {
            float var11 = F(var10 / 0.95F, 0.3F, var3, 0.25F);
            var0.field_3654 -= 1.1F * var11;
            var0.field_3674 -= 0.25F * var11;
            float var12 = F((var10 - 1.6F) / 0.5F, 0.4F, var3, 0.6F);
            var0.field_3654 -= 0.5F * var12;
         } else {
            float var13 = F(var10 / 0.6F, 0.3F, var3, 0.3F);
            var0.field_3654 -= 0.35F * var13;
            float var14 = F((var10 - 0.6F) / 1.35F, 0.3F, var3, 0.35F);
            var0.field_3654 -= 1.0F * var14;
            var0.field_3674 -= 0.2F * var14;
         }
      }
   }

   private static float F(float var0, float var1, long var2, float var4) {
      SkinPreset.ensureSourceClassInitialized();
      long var5 = 39637046769161L;
      int var8 = var5 < 0L ? -1 : 1;
      if (!(var0 <= 0.0F) && !(var0 >= 1.0F)) {
         long var8x = 39637046769162L;
         int var10 = var8x < 0L ? -1 : 1;
         if (var0 < var1) {
            return u(var0 / var1);
         } else {
            long var10x = 39637046769161L;
            int var11 = var10x < 0L ? -1 : 1;
            return var0 > 1.0F - var4 ? u((1.0F - var0) / var4) : 1.0F;
         }
      } else {
         return 0.0F;
      }
   }

   private static float u(float var0) {
      var0 = Math.clamp(var0, 0.0F, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   static boolean resting() {
      return i == 0 && X != null && X.r != null && X.r.A;
   }

   static Skins3DUpdater.Data visual(float var0) {
      long var1 = 133355261235401L;
      long var3 = var1 ^ 41848842449241L;
      long var5 = var1 ^ 44378603975056L;
      int var7 = (int)((var1 ^ 44378603975056L) >>> 56);
      int var8 = (int)((var1 ^ 44378603975056L) << 8 >>> 32);
      int var9 = (int)(var5 << 40 >>> 40);
      long var10 = var1 ^ 131009346984569L;
      class_310 var12 = class_310.method_1551();
      if (X != null && var12.field_1724 != null) {
         Skins3DCamera var13 = X.r;
         if (var13 != null && X.A.equals(x(var3))) {
            if (i == 2) {
               float var14 = v((byte)var7, var0, var8, var9);
               float var15 = 0.34F;
               if (var14 >= 0.6F) {
                  return null;
               }

               if (var14 >= var15) {
                  float var16 = Math.clamp((var14 - var15) / (0.6F - var15), 0.0F, 1.0F);
                  return new Skins3DUpdater.Data(
                     false, l(var16, var0, var10), var13.E + 5.5F * var16, -1.3F * var16, 0.6F * (float)Math.sin(Math.PI * var16), 1.0F
                  );
               }
            }

            return new Skins3DUpdater.Data(
               false,
               new class_243(c(var13.z, var13.T, var0), c(var13.v, var13.L, var0), c(var13.d, var13.Z, var0)),
               (float)c(var13.t, var13.E, var0),
               (float)c(var13.Q, var13.r, var0),
               (float)c(var13.i, var13.M, var0),
               1.0F
            );
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private static class_243 l(float var0, float var1, long var2) {
      long var4 = 102789260389643L;
      SkinPreset.ensureSourceClassInitialized();
      class_243 var6 = E != null ? E : new class_243(X.r.T, X.r.L, X.r.Z);
      class_243 var7 = E(var4, var1).method_1031(0.0, -0.12, 0.0);
      float var8 = 1.0F - (1.0F - var0) * (1.0F - var0);
      class_243 var9 = var6.method_35590(var7, 0.5).method_1031(0.0, 0.35 + 0.08 * var6.method_1022(var7), 0.0);
      class_243 var10 = var6.method_35590(var9, var8);
      class_243 var11 = var9.method_35590(var7, var8);
      return var10.method_35590(var11, var8);
   }

   static Skins3DUpdater.PoseVector web(float var0) {
      long var1 = 25137485277009L;
      long var3 = var1 ^ 83153716464218L;
      long var5 = var1 ^ 78929770841608L;
      int var7 = (int)((var1 ^ 78929770841608L) >>> 56);
      int var8 = (int)((var1 ^ 78929770841608L) << 8 >>> 32);
      int var9 = (int)(var5 << 40 >>> 40);
      long var10 = var1 ^ 27157272079841L;
      if (i == 2 && X != null && X.r != null) {
         float var12 = v((byte)var7, var0, var8, var9);
         if (!(var12 < 0.3F) && !(var12 > 0.65000004F)) {
            float var13 = 0.34F;
            float var14 = Math.clamp((var12 - 0.3F) / 0.04F, 0.0F, 1.0F);
            class_243 var15 = E(var3, var0);
            class_243 var16 = var12 < var13
               ? var15.method_35590(E != null ? E : var15, var14)
               : l(Math.clamp((var12 - var13) / (0.6F - var13), 0.0F, 1.0F), var0, var10);
            float var17 = 1.0F - Math.clamp((var12 - 0.6F) / 0.05F, 0.0F, 1.0F);
            return new Skins3DUpdater.PoseVector(var15, var16, var17);
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private static double c(double var0, double var2, double var4) {
      return var0 + (var2 - var0) * var4;
   }

   private static class_243 j() {
      double var0 = Math.toRadians(class_310.method_1551().field_1724.field_6283);
      return new class_243(-Math.sin(var0), 0.0, Math.cos(var0));
   }

   private static double xd(long v) {
      return (v >>> 32 & 65535L) / 65535.0;
   }

   record Data(boolean K, class_243 N, float i, float V, float C, float r) {
      public boolean shell() {
         return this.K;
      }

      public class_243 position() {
         return this.N;
      }

      public float yaw() {
         return this.i;
      }

      public float pitch() {
         return this.V;
      }

      public float roll() {
         return this.C;
      }

      public float squash() {
         return this.r;
      }

      public boolean booleanState10() {
         return this.K;
      }

      public class_243 minecraftObjectState15() {
         return this.N;
      }

      public float floatState12() {
         return this.i;
      }

      public float floatState15() {
         return this.V;
      }

      public float floatState16() {
         return this.C;
      }

      public float floatState45() {
         return this.r;
      }
   }

   record PoseVector(class_243 G, class_243 L, float C) {
      public class_243 from() {
         return this.G;
      }

      public class_243 to() {
         return this.L;
      }

      public float strength() {
         return this.C;
      }

      public class_243 minecraftObjectState23() {
         return this.G;
      }

      public class_243 minecraftObjectState24() {
         return this.L;
      }

      public float floatState16() {
         return this.C;
      }
   }
}
