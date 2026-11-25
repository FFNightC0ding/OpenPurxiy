package dev.purxiy.modules.skins3d;

import dev.purxiy.duck.ModuleOwner;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.client.ClientBuilder;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.loader.fabric.client.ClientRegistry23;
import dev.purxiy.ui.WBuilder;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.class_10055;
import net.minecraft.class_10186;
import net.minecraft.class_10192;
import net.minecraft.class_10201;
import net.minecraft.class_1060;
import net.minecraft.class_1304;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4588;
import net.minecraft.class_5321;
import net.minecraft.class_591;
import net.minecraft.class_630;
import net.minecraft.class_742;
import net.minecraft.class_746;
import net.minecraft.class_7920;
import net.minecraft.class_8685;
import net.minecraft.class_9334;
import net.minecraft.class_10186.class_10190;
import net.minecraft.class_4587.class_4665;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public final class Skins3DTransform3 {
   static final double t = 20.0;
   private static final float O = 0.02F;
   private static final Vector3f A = new Vector3f();
   private static final Vector3f i = new Vector3f();
   private static final int[] n = new int[4];
   private static final float[] r = new float[3];
   private static class_10201 W;
   private static final int z = 0;
   private static final int j = 1;
   private static final int V = 2;
   private static final int e = 3;
   private static final int b = 4;
   private static final int N = 5;
   private static final int E = 6;
   private static final int R = 7;
   private static final float[] x = new float[]{1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F};
   private static final float[] X = new float[8];
   private static long o = 0L;
   private static SkinPreset.Data H;
   private static boolean C;
   private static boolean G;
   private static long c = 0L;
   private static long Y = 0L;
   private static long p = 0L;
   private static long L = 0L;
   private static final int[] w = new int[]{0, 1, 2, 0, 2, 3};
   private static final Map<class_630, Skins3DBuilder.TypeO> T = new WeakHashMap<>();
   private static final Map<Skins3DBuilder.TypeO, float[]> U = new WeakHashMap<>();
   static Skins3DModule h;
   private static ModuleContext a;
   private static int K = 0;
   private static int v = 0;
   private static final long[] g = new long[]{
      5667518994762989534L,
      -852873523591102133L,
      7390316698924059672L,
      8022148283532844347L,
      -8622219690009705374L,
      -166746484388873160L,
      8071947853031373816L,
      2475503512936257039L
   };

   private Skins3DTransform3() {
   }

   private static Skins3DModule X() {
      return h;
   }

   public static void pose(class_591 var0, class_10055 var1) {
      long var2 = 82130353501644L;
      long var4 = var2 ^ 37624786460548L;
      long var6 = var2 ^ 41715429018657L;
      long var8 = var2 ^ 100093810389967L;
      ModuleOwner var10 = (ModuleOwner)var0;
      if (var10.purxiy$body()) {
         Skins3DBuilder.TypeR var11 = T(var1, var10.purxiy$slim(), var6);
         y(var0, var8, var11 != null && var11.C ? var11 : null);
         if (var11 == null) {
            D(var0);
         } else if (var11.C) {
            boolean var12 = Skins3DTransform.moves(var1);
            if (var12 && var1.field_53410) {
               Skins3DTransform.uncrouch(var0);
            }

            rig(var0, var11);
            if (var1.field_53410 && !var12) {
               var0.field_3398.field_3656 -= 2.0F;
               var0.field_3391.field_3656 -= 2.0F;
               var0.field_3401.field_3656 -= 2.0F;
               var0.field_27433.field_3656 -= 2.0F;
               var0.field_3392.field_3656 -= 2.0F;
               var0.field_3397.field_3656 -= 2.0F;
            }

            D(var0);
            Skins3DTransform.pose(var0, var1, var11);
         } else {
            boolean var15 = !v(var1.field_55309, var4, class_1304.field_6169);
            boolean var13 = !v(var1.field_53418, var4, class_1304.field_6174);
            boolean var14 = !v(var1.field_53419, var4, class_1304.field_6172);
            set(var0.field_3394, var15 ? var11.R[0] : null);
            set(var0.field_3483, var13 && var14 ? var11.R[1] : null);
            set(var0.field_3486, var13 ? var11.R[2] : null);
            set(var0.field_3484, var13 ? var11.R[3] : null);
            set(var0.field_3479, var14 ? var11.R[4] : null);
            set(var0.field_3482, var14 ? var11.R[5] : null);
         }
      }
   }

   public static void hand(class_591 var0, class_2960 var1, class_630 var2) {
      ModuleOwner var3 = (ModuleOwner)var0;
      boolean var4 = var2 == var0.field_27433;
      class_630 var5 = var4 ? var0.field_3484 : var0.field_3486;
      Skins3DBuilder.TypeR var6 = var3.purxiy$body() && X().enabled() && X().g.enabled() ? Skins3DFactory.get(var1, var3.purxiy$slim()) : null;
      set(var5, var6 == null ? null : var6.R[var4 ? 3 : 2]);
      if (SkinPreset.voxelModel(var1) != null) {
         Skins3DBuilder.TypeR var7 = Skins3DFactory.get(var1, var3.purxiy$slim());
         set(var2, var7 == null ? null : var7.R[var4 ? 3 : 2]);
         set(var5, null);
      } else {
         set(var2, null);
      }
   }

   public static void handPose(class_630 var0) {
      Skins3DBuilder.TypeO var1 = T.get(var0);
      if (var1 != null) {
         var0.field_3656 = var0.field_3656 + Math.max(0.0F, 10.0F - var1.r);
      }

      if (var1 != null && var1.d == 2) {
         Skins3DUpdater.handPose(var0);
      }
   }

   private static Skins3DBuilder.TypeR T(class_10055 var0, boolean var1, long var2) {
      SkinPreset.ensureSourceClassInitialized();
      if (X().enabled() && var0.field_53520 != null) {
         boolean var4;
         label39: {
            class_746 var5 = class_310.method_1551().field_1724;
            if (var5 != null) {
               long var6 = 122332880288236L;
               int var8 = var6 < 0L ? -1 : 1;
               if (var0.field_53528 == var5.method_5628()) {
                  var4 = true;
                  break label39;
               }
            }

            var4 = false;
         }

         long var9 = 122332880288236L;
         int var7 = var9 < 0L ? -1 : 1;
         return var4 || X().v.enabled() && !(var0.field_53332 > 400.0) ? Skins3DFactory.get(var0.field_53520.comp_1626().comp_3627(), var1) : null;
      } else {
         return null;
      }
   }

   private static void D(class_591 var0) {
      set(var0.field_3394, null);
      set(var0.field_3483, null);
      set(var0.field_3486, null);
      set(var0.field_3484, null);
      set(var0.field_3479, null);
      set(var0.field_3482, null);
   }

   private static void y(class_591 var0, long var1, Skins3DBuilder.TypeR var3) {
      SkinPreset.ensureSourceClassInitialized();
      set(var0.field_3398, var3 == null ? null : var3.R[0]);
      set(var0.field_3391, var3 == null ? null : var3.R[1]);
      set(var0.field_3401, var3 == null ? null : var3.R[2]);
      set(var0.field_27433, var3 == null ? null : var3.R[3]);
      set(var0.field_3392, var3 == null ? null : var3.R[4]);
      set(var0.field_3397, var3 == null ? null : var3.R[5]);
   }

   private static void F(int var0, long var1, class_630 var3, Skins3DBuilder.TypeR var4, int var5) {
      SkinPreset.ensureSourceClassInitialized();
      float[] var6 = Skins3DBuilder.U[var5];
      float[] var7 = var4.y[var5];
      float var8 = var7[0] / var6[0];
      var3.field_3657 *= var8;
      if (var5 == 2 || var5 == 3) {
         var3.field_3655 *= var8;
      }

      var3.field_3656 = var3.field_3656 + (var7[1] - var6[1]);
      var3.field_3655 = var3.field_3655 + (var7[2] - var6[2]);
   }

   public static void rig(class_591 var0, Skins3DBuilder.TypeR var1) {
      long var2 = 69902917143081L;
      int var4 = (int)((var2 ^ 79007183966683L) >>> 32);
      long var5 = (var2 ^ 79007183966683L) << 32 >>> 32;
      F(var4, var5, var0.field_3401, var1, 2);
      F(var4, var5, var0.field_27433, var1, 3);
      F(var4, var5, var0.field_3392, var1, 4);
      F(var4, var5, var0.field_3397, var1, 5);
      float[] var7 = var1.y[0];
      float var8 = (float)Math.sin(var0.field_3391.field_3654);
      float var9 = (float)Math.cos(var0.field_3391.field_3654);
      var0.field_3398.field_3657 = var0.field_3391.field_3657 + var7[0];
      var0.field_3398.field_3656 = var0.field_3391.field_3656 + var9 * var7[1] - var8 * var7[2];
      var0.field_3398.field_3655 = var0.field_3391.field_3655 + var8 * var7[1] + var9 * var7[2];
      if (var1.n) {
         var0.field_3398.field_3654 = var0.field_3391.field_3654;
         var0.field_3398.field_3675 = var0.field_3391.field_3675;
         var0.field_3398.field_3674 = var0.field_3391.field_3674;
      } else {
         var0.field_3398.field_3654 = Math.clamp(var0.field_3398.field_3654, -0.35F, 0.4F);
         var0.field_3398.field_3675 = Math.clamp(var0.field_3398.field_3675, -0.7F, 0.7F);
      }
   }

   static void set(class_630 var0, Skins3DBuilder.TypeO var1) {
      if (var1 == null) {
         T.remove(var0);
      } else {
         T.put(var0, var1);
      }
   }

   private static boolean v(class_1799 var0, long var1, class_1304 var3) {
      long var4 = 137454363172568L;
      byte var6 = 0;
      short var7 = 32003;
      int var8 = (int)(var4 << 32 >>> 32);
      SkinPreset.ensureSourceClassInitialized();
      return var0.method_7960() ? false : var3 == class_1304.field_6169 || n(var0, (char)var6, var3, (char)var7, var8);
   }

   private static boolean n(class_1799 var0, char var1, class_1304 var2, char var3, int var4) {
      SkinPreset.ensureSourceClassInitialized();
      boolean var5 = var0.method_7960();
      if (var5) {
         return false;
      } else {
         class_10192 var6 = (class_10192)var0.method_58694(class_9334.field_54196);
         if (var6 != null && var6.comp_3174() == var2 && !var6.comp_3176().isEmpty()) {
            class_10201 var7 = W;
            if (var7 == null) {
               return true;
            } else {
               class_10186 var8 = var7.method_64087((class_5321)var6.comp_3176().get());
               return !var8.method_63996(var2 == class_1304.field_6172 ? class_10190.field_54126 : class_10190.field_54125).isEmpty();
            }
         } else {
            return false;
         }
      }
   }

   public static void hideArmor(Object var0, class_10055 var1) {
      long var2 = 6751909022958L;
      long var4 = var2 ^ 135410040623670L;
      int var6 = (int)((var2 ^ 135410040623670L) >>> 48);
      int var7 = (int)((var2 ^ 135410040623670L) << 16 >>> 48);
      int var8 = (int)(var4 << 32 >>> 32);
      class_310 var9 = class_310.method_1551();
      if (var0 == var9.field_1724 && X().enabled() && X().C.enabled() && !var9.field_1690.method_31044().method_31034()) {
         if (n(var1.field_55309, (char)var6, class_1304.field_6169, (char)var7, var8)) {
            var1.field_55309 = class_1799.field_8037;
         }

         if (n(var1.field_53418, (char)var6, class_1304.field_6174, (char)var7, var8)) {
            var1.field_53418 = class_1799.field_8037;
         }

         if (n(var1.field_53419, (char)var6, class_1304.field_6172, (char)var7, var8)) {
            var1.field_53419 = class_1799.field_8037;
         }

         if (n(var1.field_53420, (char)var6, class_1304.field_6166, (char)var7, var8)) {
            var1.field_53420 = class_1799.field_8037;
         }
      }
   }

   public static void equipment(class_10201 var0) {
      W = var0;
   }

   public static void paint(Skins3DBuilder.TypeO var0, class_4665 var1, class_4588 var2, int var3, int var4, int var5) {
      long var6 = 74501651973357L;
      long var8 = var6 ^ 78438708496251L;
      boolean var10 = false;
      float var11 = Skins3DBuilder.I[var0.d] * X().b.floatValue();
      Matrix4f var12 = var1.method_23761();
      int var13 = var5 >>> 24;
      int var14 = var5 >>> 16 & 0xFF;
      int var15 = var5 >>> 8 & 0xFF;
      int var16 = var5 & 0xFF;

      for (int var17 = 0; var17 < 4; var17++) {
         float var18 = Skins3DBuilder.H[var17];
         n[var17] = var13 << 24 | Math.round(var14 * var18) << 16 | Math.round(var15 * var18) << 8 | Math.round(var16 * var18);
      }

      int[] var42 = var0.V;
      int[] var43 = var0.F;
      float var19 = var43[0];
      float var20 = var43[1];
      float var21 = var43[2];
      float var22 = var0.h;
      boolean var23 = WBuilder.active();
      int var24 = 0;

      for (byte var25 = 0; var24 < var0.p; var25 = (byte)(var25 + 5)) {
         int var26 = var42[var25];
         int[] var27 = Skins3DBuilder.C[var26 & 7];
         if (var23 && WBuilder.lower(var20 + var22 * ((var42[var25 + 1] >>> 8 & 0xFF) + (var42[var25 + 3] >>> 8 & 0xFF)) / 2.0F)) {
            WBuilder.turnNormal(var27[0], var27[1], var27[2], r);
            var1.method_56820(r[0], r[1], r[2], A);
         } else {
            var1.method_56820(var27[0], var27[1], var27[2], A);
         }

         float var28 = A.x;
         float var29 = A.y;
         float var30 = A.z;
         float var31 = var26 >>> 3 & 63;
         float var32 = var26 >>> 9 & 63;

         for (int var33 = 0; var33 < 4; var33++) {
            int var34 = var42[var25 + 1 + var33];
            float var35 = var19 + (var34 & 0xFF) * var22 + N(var34 >>> 24, var8, var11);
            float var36 = var20 + (var34 >>> 8 & 0xFF) * var22 + N(var34 >>> 26, var8, var11);
            float var37 = var21 + (var34 >>> 16 & 0xFF) * var22 + N(var34 >>> 28, var8, var11);
            if (var23) {
               WBuilder.map(var35, var36, var37, r);
               var35 = r[0];
               var36 = r[1];
               var37 = r[2];
            }

            var12.transformPosition(var35 / 16.0F, var36 / 16.0F, var37 / 16.0F, i);
            int var38 = var26 >>> 23 + 2 * var33 & 3;
            float var39 = (var31 + ((var38 & 1) != 0 ? 0.98F : 0.02F)) / 64.0F;
            float var40 = (var32 + ((var38 & 2) != 0 ? 0.98F : 0.02F)) / 64.0F;
            var2.method_23919(i.x, i.y, i.z, n[var26 >>> 15 + 2 * var33 & 3], var39, var40, var4, var3, var28, var29, var30);
         }

         var24++;
      }

      var10 = false;
   }

   private static float N(int var0, long var1, float var3) {
      SkinPreset.ensureSourceClassInitialized();
      long var4 = 5036584928149L;
      int var7 = var4 < 0L ? -1 : 1;
      var7 = var0 & 3;
      long var8 = 5036584928150L;
      int var9 = var8 < 0L ? -1 : 1;
      return var7 == 1 ? -var3 : (var7 == 2 ? var3 : 0.0F);
   }

   public static Skins3DBuilder.TypeR voxelsOf(class_2960 var0, boolean var1) {
      return Skins3DFactory.get(var0, var1);
   }

   public static Skins3DBuilder.TypeR layers(class_8685 var0) {
      return X().enabled() ? Skins3DFactory.get(var0.comp_1626().comp_3627(), var0.comp_1629() == class_7920.field_41122) : null;
   }

   public static int triangles(ByteBuffer var0, Skins3DBuilder.TypeO var1, Matrix4f var2) {
      float var3 = Skins3DBuilder.I[var1.d] * X().b.floatValue();
      int var4 = 0;

      for (int var5 = 0; var5 < var1.p && var0.remaining() >= 168; var5++) {
         int[] var6 = Skins3DBuilder.C[var1.normal(var5)];
         var2.transformDirection(A.set(var6[0], var6[1], var6[2])).normalize();
         byte var7 = (byte)Math.round(A.x * 127.0F);
         byte var8 = (byte)Math.round(A.y * 127.0F);
         byte var9 = (byte)Math.round(A.z * 127.0F);
         float var10 = var1.texelU(var5);
         float var11 = var1.texelV(var5);

         for (int var15 : w) {
            var2.transformPosition(i.set(var1.coordinate(var5, var15, 0, var3), var1.coordinate(var5, var15, 1, var3), var1.coordinate(var5, var15, 2, var3)));
            int var16 = var1.uvCorner(var5, var15);
            var0.putFloat(i.x).putFloat(i.y).putFloat(i.z);
            var0.putFloat((var10 + ((var16 & 1) != 0 ? 0.98F : 0.02F)) / 64.0F).putFloat((var11 + ((var16 & 2) != 0 ? 0.98F : 0.02F)) / 64.0F);
            byte var17 = (byte)Math.round(Skins3DBuilder.H[var1.shade(var5, var15)] * 255.0F);
            var0.put(var17).put(var17).put(var17).put((byte)-1);
            var0.put(var7).put(var8).put(var9).put((byte)0);
            var4++;
         }
      }

      return var4;
   }

   public static class_8685 wear(class_742 var0, class_8685 var1) {
      if (!C && var0 == class_310.method_1551().field_1724) {
         return X().enabled() ? X().preset().dress(var1) : var1;
      } else {
         return var1;
      }
   }

   public static void size(Object var0, class_10055 var1) {
      if (var0 == class_310.method_1551().field_1724) {
         float var2 = x[0];
         float var3 = var2 * (24.0F + 8.0F * x[2]) / 32.0F;
         if (var2 != 1.0F || var3 != 1.0F) {
            var1.field_53453 *= var2;
            var1.field_53331 *= var3;
            if (var1.field_53338 != null) {
               var1.field_53338 = var1.field_53338.method_1021(var3);
            }
         }

         var1.field_61822 = var1.field_61822 * (var2 * (0.5F + 0.5F * x[4]));
      }
   }

   public static void shape(class_591 var0, class_10055 var1) {
      long var2 = 79096146228307L;
      long var4 = var2 ^ 93325512336504L;
      class_746 var6 = class_310.method_1551().field_1724;
      if (var6 != null && var1.field_53528 == var6.method_5628() && !E(var4)) {
         var0.field_3398.field_37938 = var0.field_3398.field_37938 * x[1];
         var0.field_3398.field_37939 = var0.field_3398.field_37939 * x[2];
         var0.field_3398.field_37940 = var0.field_3398.field_37940 * x[3];
         var0.field_3391.field_37938 = var0.field_3391.field_37938 * x[4];
         var0.field_3391.field_37940 = var0.field_3391.field_37940 * x[5];
         float var7 = (4.0F * x[4] + 1.0F) / 5.0F;
         var0.field_3401.field_3657 *= var7;
         var0.field_3401.field_3655 *= var7;
         var0.field_27433.field_3657 *= var7;
         var0.field_27433.field_3655 *= var7;
         var0.field_3392.field_3657 = var0.field_3392.field_3657 * x[4];
         var0.field_3397.field_3657 = var0.field_3397.field_3657 * x[4];
         var0.field_3392.field_37938 = var0.field_3392.field_37938 * x[6];
         var0.field_3392.field_37940 = var0.field_3392.field_37940 * x[7];
         var0.field_3397.field_37938 = var0.field_3397.field_37938 * x[6];
         var0.field_3397.field_37940 = var0.field_3397.field_37940 * x[7];
      }
   }

   private static boolean E(long var0) {
      float[] var2 = x;
      SkinPreset.ensureSourceClassInitialized();

      for (float var5 : var2) {
         long var6 = 20963931459627L;
         int var9 = var6 < 0L ? -1 : 1;
         if (var5 != 1.0F) {
            return false;
         }
      }

      return true;
   }

   public static float yourHeight() {
      return x[0];
   }

   public static float bodyWidth() {
      return x[4];
   }

   public static float armSpread() {
      return (4.0F * x[4] + 1.0F) / 5.0F;
   }

   public static void build(Matrix4f var0, int var1) {
      switch (var1) {
         case 0:
            var0.scale(x[1], x[2], x[3]);
            break;
         case 1:
            var0.scale(x[4], 1.0F, x[5]);
         case 2:
         case 3:
         default:
            break;
         case 4:
         case 5:
            var0.scale(x[6], 1.0F, x[7]);
      }
   }

   public static float drawnHeight(Object var0) {
      return var0 == class_310.method_1551().field_1724 ? x[0] : 1.0F;
   }

   private static void H(long var0) {
      SkinPreset.ensureSourceClassInitialized();
      Skins3DModule var2 = X();
      long var3 = 1030478032368L;
      int var6 = var3 < 0L ? -1 : 1;
      SkinPreset var6x = var2.enabled() && X().M.enabled() ? X().preset() : SkinPreset.n;
      SkinPreset.Data var7 = H != null && var6x != SkinPreset.n ? H : var6x.h;
      float var8 = var6x.t;
      X[0] = var7.scale(var8);
      X[1] = var7.headWidth();
      X[2] = var7.headHeight();
      X[3] = var7.headDepth();
      X[4] = var7.bodyWidth();
      X[5] = var7.bodyDepth();
      X[6] = var7.legWidth();
      X[7] = var7.legDepth();
      long var9 = System.nanoTime();
      float var11 = o == 0L ? 1.0F : Math.min(0.25F, (float)(var9 - o) / 1.0E9F);
      o = var9;
      float var12 = 1.0F - (float)Math.exp(-var11 * 9.0F);

      for (int var13 = 0; var13 < x.length; var13++) {
         x[var13] = x[var13] + (X[var13] - x[var13]) * var12;
         long var14 = 1030478032368L;
         int var17 = var14 < 0L ? -1 : 1;
         long var17x = 1030478032368L;
         int var17x = var17x < 0L ? -1 : 1;
         if (Math.abs(X[var13] - x[var13]) < 0.001F) {
            x[var13] = X[var13];
         }
      }

      long var19 = 1030478032369L;
      int var141 = var19 < 0L ? -1 : 1;
   }

   public static class_8685 own() {
      C = true;

      class_8685 var0;
      try {
         var0 = ClientRegistry23.dress(ClientBuilder.ownSkin());
      } finally {
         C = false;
      }

      return var0;
   }

   static void release() {
   }

   public static void frame() {
      long var0 = 44963591759209L;
      long var2 = var0 ^ 44027905624216L;
      H(var2);
   }

   public static void reloaded() {
      Skins3DRenderer3.close();
      Skins3DFactory.clear();
   }

   static void releasePresetTextures() {
      class_1060 var0 = class_310.method_1551().method_1531();

      for (SkinPreset var4 : SkinPreset.values()) {
         class_2960 var5 = var4.texture();
         if (var5 != null) {
            var0.method_4615(var5);
         }
      }

      var0.method_4615(SkinPreset.T);
   }

   static float[] bounds(class_630 var0) {
      Skins3DBuilder.TypeO var1 = T.get(var0);
      float[] var2 = var1 == null ? null : layer(var1);
      return var2 == null ? null : (float[])var2.clone();
   }

   static boolean paintPart(class_630 var0, class_4665 var1, class_4588 var2, int var3, int var4, int var5) {
      Skins3DBuilder.TypeO var6 = T.get(var0);
      if (var6 == null) {
         return false;
      } else {
         if (!Float.isNaN(var6.t)) {
            WBuilder.retarget(var6.t);
         }

         paint(var6, var1, var2, var3, var4, var5);
         metric("drawn");
         return true;
      }
   }

   static float[] layer(Skins3DBuilder.TypeO var0) {
      float var1 = Skins3DBuilder.I[var0.d] * h.b.floatValue();
      float[] var2 = U.get(var0);
      if (var2 != null && var2[6] == var1) {
         return var2;
      } else if (var0.p == 0) {
         return null;
      } else {
         float[] var3 = new float[]{Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE, var1, 0.0F};

         for (int var4 = 0; var4 < var0.p; var4++) {
            for (int var5 = 0; var5 < 4; var5++) {
               for (int var6 = 0; var6 < 3; var6++) {
                  float var7 = var0.coordinate(var4, var5, var6, var1);
                  var3[var6] = Math.min(var3[var6], var7);
                  var3[var6 + 3] = Math.max(var3[var6 + 3], var7);
               }
            }
         }

         float var12 = var3[1] + (var3[4] - var3[1]) * 0.4F;
         float var13 = var3[1] + (var3[4] - var3[1]) * 0.6F;
         float var14 = Float.MAX_VALUE;
         float var15 = -Float.MAX_VALUE;

         for (int var8 = 0; var8 < var0.p; var8++) {
            for (int var9 = 0; var9 < 4; var9++) {
               float var10 = var0.coordinate(var8, var9, 1, var1);
               if (var10 >= var12 && var10 <= var13) {
                  float var11 = var0.coordinate(var8, var9, 0, var1);
                  var14 = Math.min(var14, var11);
                  var15 = Math.max(var15, var11);
               }
            }
         }

         var3[7] = var15 > var14 ? (var15 - var14) / 2.0F : (var3[3] - var3[0]) / 2.0F;
         U.put(var0, var3);
         return var3;
      }
   }

   public static void install(Skins3DModule var0, ModuleContext var1) {
      h = var0;
      a = var1;
   }

   public static void metric(String var0) {
      if (a != null) {
         a.metric(var0, var0.equals("drawn") ? ++K : ++v);
      }
   }

   public static void uninstall() {
      Skins3DUpdater.leave();

      try {
         Skins3DRenderer3.close();
      } finally {
         ClientCache.release(Skins3DRenderer3.q);
         ClientCache.release(Skins3DRenderer3.D);
         Skins3DFactory.shutdown();
         Skins3DTransform.clear();
         T.clear();
         U.clear();
         releasePresetTextures();
         W = null;
      }

      h = null;
      a = null;
   }
}
