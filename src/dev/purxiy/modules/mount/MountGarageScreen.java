package dev.purxiy.modules.mount;

import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import dev.purxiy.loader.fabric.ClientServices;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import net.minecraft.class_1011.class_1012;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.lwjgl.system.MemoryUtil;

public final class MountGarageScreen {
   private static final int Y = 3;
   private static final int A = 48;
   private static final int U = 32;
   private static final long y = 45000000L;
   private static final double j = 70.0;
   private static final float[] b = new float[]{1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F};
   private static final GpuTexture[] O = new GpuTexture[3];
   private static final GpuTextureView[] u = new GpuTextureView[3];
   private static final boolean[] X = new boolean[3];
   private static ByteBuffer M;
   private static CompletableFuture<MountGarageScreen.GarageThumbnail> I;
   private static class_638 L;
   private static long T = 0L;
   private static int f = 0;
   private static boolean n = true;
   private static final String[] c = new String[2];
   private static final long[] d = new long[]{
      1005329748172445115L,
      8181329888204871531L,
      6164500327877363967L,
      -8313222511519368240L,
      5134990274029284488L,
      4876075053321996311L,
      -7663573641828176563L,
      7180122299755517414L,
      -7909848864220912566L,
      -3938525435845930102L,
      24764353798856415L,
      5453557212907758779L,
      8110475066201044582L,
      -3671139400980385928L,
      -1184091258094476533L,
      -6542561237097196148L,
      -5272993342516569779L,
      -7749465235428072468L,
      6313029480256070009L,
      6733309293779803219L,
      8107455277041849697L,
      7442449612224617914L,
      1884771665140893399L,
      4223409080384359870L,
      841160922594807897L,
      2106762410563254259L,
      -1280038322106824823L
   };

   private MountGarageScreen() {
   }

   public static boolean capturing() {
      return false;
   }

   public static void devEnabled(boolean var0) {
      n = var0;
   }

   static void update(float var0, MountDrawBuffers.MountVertex var1) {
      long var2 = 3850668671397L;
      long var4 = var2 ^ 84237487800784L;
      long var6 = var2 ^ 127468650448327L;
      long var8 = var2 ^ 95198952975754L;
      long var10 = var2 ^ 113336739957741L;
      class_310 var12 = class_310.method_1551();
      if (I != null && I.isDone()) {
         MountGarageScreen.GarageThumbnail var13 = null;

         try {
            var13 = I.join();
         } catch (RuntimeException var31) {
         }

         I = null;
         if (var13 != null) {
            k(var10, var13);
         }
      }

      if (var12.field_1687 != L) {
         L = var12.field_1687;
         Arrays.fill(X, false);
      }

      long var32 = System.nanoTime();
      if (I == null && var32 >= T && var12.field_1687 != null && var12.field_1724 != null && n && !MountCameraReader.K.B.is("Performance")) {
         List var15 = MountInteraction.spec().mirrors();
         int var16 = Math.min(var15.size(), 3);
         int var17 = -1;

         for (int var18 = 0; var18 < var16 && var17 < 0; var18++) {
            int var19 = (f + var18) % var16;
            if (d(var4, (MountFactory.WheelMount)var15.get(var19), var0)) {
               var17 = var19;
            }
         }

         if (var17 >= 0) {
            f = (var17 + 1) % var16;
            T = var32 + 45000000L;
            MountFactory.WheelMount var33 = (MountFactory.WheelMount)var15.get(var17);
            Vector3f var34 = var33.center();
            class_243 var20 = MountCameraState.worldPoint(var34.x, var34.y, var34.z, var0);
            float var21 = MountInteraction.yaw(var0) + var33.yaw();
            float var22 = var33.fov();
            float var23 = Math.clamp(var33.width() / var33.height(), 1.5F, 4.2F);
            int var24 = var17;
            int var25 = Math.round(48.0F * var23);
            float var26 = Math.clamp(var1.z, 0.0F, 1.0F);
            int var27 = w(var6, var1.b);
            int var28 = w(var6, var1.Y);
            List var29 = r(var12, var8, var20);
            class_638 var30 = var12.field_1687;
            I = MountWorker.submit(() -> {
               long var11 = 121573428702446L;
               int var13x = (int)((var11 ^ 76452603665999L) >>> 48);
               long var14 = (var11 ^ 76452603665999L) << 16 >>> 16;
               return h(var30, (short)var13x, var24, var25, 48, var20, var21, var22, var23, var26, var27, var14, var28, var29);
            });
         }
      }
   }

   private static boolean d(long var0, MountFactory.WheelMount var2, float var3) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      class_4184 var4 = class_310.method_1551().field_1773.method_19418();
      Vector3f var5 = var2.center();
      class_243 var6 = MountCameraState.worldPoint(var5.x, var5.y, var5.z, var3).method_1020(var4.method_71156()).method_1029();
      Vector3fc var7 = var4.method_19335();
      return var6.field_1352 * var7.x() + var6.field_1351 * var7.y() + var6.field_1350 * var7.z() > 0.3;
   }

   private static List r(class_310 var0, long var1, class_243 var3) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      ArrayList var4 = new ArrayList();

      for (class_1297 var6 : var0.field_1687.method_18112()) {
         long var7 = 93551744216110L;
         int var10 = var7 < 0L ? -1 : 1;
         if (var6 != var0.field_1724) {
            long var10x = 93551744216110L;
            int var12 = var10x < 0L ? -1 : 1;
            boolean var12x = var6 instanceof class_1309;
            long var13 = 93551744216110L;
            int var16 = var13 < 0L ? -1 : 1;
            if (var12x) {
               var12x = var6.method_5767();
               long var16x = 93551744216110L;
               if (!var12x && !(var6.method_5707(var3) > 4900.0)) {
                  var4.add(new MountGarageScreen.GarageBox(var6.method_5829(), var6 instanceof class_1657 ? 5009320 : 8022616));
                  long var18x = 93551744216110L;
                  if (var4.size() >= 32) {
                     break;
                  }

                  long var20x = 93551744216111L;
               }
            }
         }
      }

      return var4;
   }

   private static int w(long var0, Vector3f var2) {
      long var3 = 138595952129321L;
      return R(var3, var2.x) << 16 | R(var3, var2.y) << 8 | R(var3, var2.z);
   }

   private static int R(long var0, float var2) {
      return Math.clamp((long)Math.round((float)Math.pow(Math.max(0.0F, var2), 0.45454545454545453) * 255.0F), 0, 255);
   }

   private static MountGarageScreen.GarageThumbnail h(
      class_638 var0,
      short var1,
      int var2,
      int var3,
      int var4,
      class_243 var5,
      float var6,
      float var7,
      float var8,
      float var9,
      int var10,
      long var11,
      int var13,
      List<GarageBox> var14
   ) {
      long var15 = 47392938308257L;
      long var17 = var15 ^ 94429325333911L;
      int var19 = (int)((var15 ^ 138136469936537L) >>> 56);
      long var20 = (var15 ^ 138136469936537L) << 8 >>> 8;
      long var22 = var15 ^ 20862627059302L;
      MountSampler.MountSamplerState var24 = new MountSampler.MountSamplerState();
      double var25 = Math.toRadians(var6);
      double var27 = Math.toRadians(4.0);
      double var29 = Math.sin(var25);
      MountFactory.MountSpec.ensureSourceClassInitialized();
      double var31 = Math.cos(var25);
      double var33 = Math.sin(var27);
      double var35 = Math.cos(var27);
      double var37 = -var29 * var35;
      double var39 = -var33;
      double var41 = var31 * var35;
      double var43 = -var31;
      double var45 = -var29;
      double var47 = -var29 * var33;
      double var49 = var35;
      double var51 = var31 * var33;
      double var53 = Math.tan(Math.toRadians(var7) / 2.0);
      double var55 = var53 * var8;
      int[] var57 = new int[var3 * var4];

      for (int var58 = 0; var58 < var4; var58++) {
         double var59 = (1.0 - 2.0 * (var58 + 0.5) / var4) * var53;

         for (int var61 = 0; var61 < var3; var61++) {
            double var62 = (2.0 * (var61 + 0.5) / var3 - 1.0) * var55;
            double var64 = var37 + var43 * var62 + var47 * var59;
            double var66 = var39 + var49 * var59;
            double var68 = var41 + var45 * var62 + var51 * var59;
            double var70 = Math.sqrt(var64 * var64 + var66 * var66 + var68 * var68);
            double var72 = var64 / var70;
            double var74 = var66 / var70;
            double var76 = var68 / var70;
            int var78 = var24.trace(var0, var5.field_1352, var5.field_1351, var5.field_1350, var72, var74, var76, var9, 70.0, 130);
            double var79 = var24.distance();
            int var81 = 0;
            int var82 = -1;

            for (MountGarageScreen.GarageBox var84 : var14) {
               long var85 = 47392938308256L;
               int var87 = var85 < 0L ? -1 : 1;
               double var87x = O(var84.B, var5.field_1352, var5.field_1351, var5.field_1350, var72, var17, var74, var76, var79);
               double var89;
               int var91 = (var89 = var87x - 0.0) == 0.0 ? 0 : (var89 < 0.0 ? -1 : 1);
               boolean var92 = false;
               if (var91 >= 0) {
                  double var93;
                  var91 = (var93 = var87x - var79) == 0.0 ? 0 : (var93 < 0.0 ? -1 : 1);
                  var92 = false;
                  if (var91 < 0) {
                     var79 = var87x;
                     var82 = var84.C;
                  }
               }
            }

            var81 = var58 * var3;
            long var98 = 47392938308257L;
            int var99 = var98 < 0L ? -1 : 1;
            var81 += var61;
            var57[var81] = var82 >= 0 ? var82 : c(var78, i(var22, var74, var10, var13), (byte)var19, var20);
         }

         long var95 = 47392938308257L;
         int var63 = var95 < 0L ? -1 : 1;
      }

      return new MountGarageScreen.GarageThumbnail(var2, var3, var4, var57);
   }

   private static double O(class_238 var0, double var1, double var3, double var5, double var7, long var9, double var11, double var13, double var15) {
      double var17 = 0.0;
      MountFactory.MountSpec.ensureSourceClassInitialized();
      double var19 = var15;
      long var21 = 139605984945973L;
      int var24 = var21 < 0L ? -1 : 1;
      double var24x = Math.abs(var7);
      long var26 = 139605984945973L;
      int var44 = var26 < 0L ? -1 : 1;
      if (var24x < 1.0E-9) {
         if (var1 < var0.field_1323) {
            return -1.0;
         }

         long var28 = 139605984945974L;
         int var31 = var28 < 0L ? -1 : 1;
         if (var1 > var0.field_1320) {
            return -1.0;
         }
      } else {
         double var41 = (var0.field_1323 - var1) / var7;
         long var30 = 139605984945974L;
         int var33 = var30 < 0L ? -1 : 1;
         double var32 = (var0.field_1320 - var1) / var7;
         double var38 = Math.max(var17, Math.min(var41, var32));
         long var34 = 139605984945973L;
         int var351 = var34 < 0L ? -1 : 1;
         var17 = var38;
         var19 = Math.min(var15, Math.max(var41, var32));
         if (var38 > var19) {
            return -1.0;
         }
      }

      if (Math.abs(var11) < 1.0E-9) {
         if (var3 < var0.field_1322) {
            return -1.0;
         }

         long var42 = 139605984945974L;
         int var47 = var42 < 0L ? -1 : 1;
         if (var3 > var0.field_1325) {
            return -1.0;
         }
      } else {
         long var43 = 139605984945973L;
         int var48 = var43 < 0L ? -1 : 1;
         double var45 = (var0.field_1322 - var3) / var11;
         double var39 = var0.field_1325 - var3;
         long var46 = 139605984945973L;
         int var55 = var46 < 0L ? -1 : 1;
         double var47 = var39 / var11;
         var17 = Math.max(var17, Math.min(var45, var47));
         var19 = Math.min(var19, Math.max(var45, var47));
         if (var17 > var19) {
            return -1.0;
         }
      }

      double var40 = Math.abs(var13);
      long var44x = 139605984945973L;
      int var50 = var44x < 0L ? -1 : 1;
      if (var40 < 1.0E-9) {
         if (var5 < var0.field_1321 || var5 > var0.field_1324) {
            return -1.0;
         }
      } else {
         double var31x = (var0.field_1321 - var5) / var13;
         double var33x = (var0.field_1324 - var5) / var13;
         long var35x = 139605984945973L;
         int var58 = var35x < 0L ? -1 : 1;
         var17 = Math.max(var17, Math.min(var31x, var33x));
         var19 = Math.min(var19, Math.max(var31x, var33x));
         if (var17 > var19) {
            return -1.0;
         }
      }

      return var17;
   }

   private static int i(long var0, double var2, int var4, int var5) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      long var6 = 63651184190663L;
      int var9 = var6 < 0L ? -1 : 1;
      float var9x = var2 <= 0.0 ? 0.0F : (float)Math.pow(Math.min(1.0, var2 * 1.15), 0.58);
      return K(var5 >> 16 & 0xFF, var4 >> 16 & 0xFF, var9x) << 16 | K(var5 >> 8 & 0xFF, var4 >> 8 & 0xFF, var9x) << 8 | K(var5 & 0xFF, var4 & 0xFF, var9x);
   }

   private static int K(int var0, int var1, float var2) {
      return Math.round(var0 + (var1 - var0) * var2);
   }

   private static int c(int var0, int var1, byte var2, long var3) {
      int var5 = var0 >>> 24;
      return Math.min(255, (var0 >> 16 & 0xFF) + (var1 >> 16 & 0xFF) * var5 / 255) << 16
         | Math.min(255, (var0 >> 8 & 0xFF) + (var1 >> 8 & 0xFF) * var5 / 255) << 8
         | Math.min(255, (var0 & 0xFF) + (var1 & 0xFF) * var5 / 255);
   }

   private static void k(long var0, MountGarageScreen.GarageThumbnail var2) {
      int var3;
      GpuDevice var4;
      label79: {
         MountFactory.MountSpec.ensureSourceClassInitialized();
         var3 = var2.K;
         var4 = RenderSystem.getDevice();
         if (O[var3] != null && O[var3].getWidth(0) == var2.p) {
            int var5 = O[var3].getHeight(0);
            long var6 = 110590200449607L;
            int var20 = var6 < 0L ? -1 : 1;
            long var9 = 110590200449607L;
            int var10 = var9 < 0L ? -1 : 1;
            if (var5 == var2.s) {
               break label79;
            }
         }

         if (u[var3] != null) {
            u[var3].close();
         }

         long var16 = 110590200449607L;
         int var8 = var16 < 0L ? -1 : 1;
         long var8x = 110590200449608L;
         int var9 = var8x < 0L ? -1 : 1;
         if (O[var3] != null) {
            O[var3].close();
         }

         O[var3] = var4.createTexture(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX mirror"), 5, TextureFormat.RGBA8, var2.p, var2.s, 1, 1);
         u[var3] = var4.createTextureView(O[var3]);
      }

      int var17 = var2.p * var2.s * 4;
      long var18 = 110590200449608L;
      int var201 = var18 < 0L ? -1 : 1;
      if (M == null || M.capacity() < var17) {
         if (M != null) {
            MemoryUtil.memFree(M);
         }

         M = MemoryUtil.memAlloc(var17);
      }

      M.clear();

      for (int var12 : var2.U) {
         long var13 = 110590200449608L;
         int var14 = var13 < 0L ? -1 : 1;
         M.put((byte)(var12 >> 16)).put((byte)(var12 >> 8)).put((byte)var12).put((byte)-1);
      }

      long var20x = 110590200449607L;
      int var25 = var20x < 0L ? -1 : 1;
      M.flip();
      var4.createCommandEncoder().writeToTexture(O[var3], M, class_1012.field_4997, 0, 0, 0, 0, var2.p, var2.s);
      X[var3] = true;
   }

   public static void render(WorldRenderContext var0, Matrix4f var1) {
      long var2 = 64567042143260L;
      long var4 = var2 ^ 114439856154285L;
      if (MountCameraState.cockpitActive()) {
         List var6 = MountInteraction.spec().mirrors();

         for (int var7 = 0; var7 < Math.min(3, var6.size()); var7++) {
            if (X[var7] && u[var7] != null) {
               MountFactory.WheelMount var8 = (MountFactory.WheelMount)var6.get(var7);
               MountDrawBatch.draw(
                  var0,
                  u[var7],
                  RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR),
                  var1,
                  l(var4, var8),
                  b,
                  0.1F,
                  0.9F,
                  var8.inner() ? 0.1F : 0.22F
               );
            }
         }
      }
   }

   public static void close() {
      for (int var0 = 0; var0 < 3; var0++) {
         if (u[var0] != null) {
            u[var0].close();
         }

         if (O[var0] != null) {
            O[var0].close();
         }

         u[var0] = null;
         O[var0] = null;
         X[var0] = false;
      }

      if (M != null) {
         MemoryUtil.memFree(M);
      }

      M = null;
      if (I != null) {
         I.cancel(false);
      }

      I = null;
      L = null;
      T = 0L;
      f = 0;
   }

   private static float[] l(long var0, MountFactory.WheelMount var2) {
      long var3 = 56577278407433L;
      MountFactory.MountSpec.ensureSourceClassInitialized();
      Vector3f var5 = var2.tl();
      long var6 = 90899583668912L;
      int var9 = var6 < 0L ? -1 : 1;
      if (var5 == null) {
         return j(var2.center().x, var2.center().y, var3, var2.center().z, var2.width(), var2.height());
      } else {
         Vector3f var9x = var2.tl();
         Vector3f var10 = var2.tr();
         Vector3f var11 = var2.bl();
         return new float[]{
            var9x.x,
            var9x.y,
            var9x.z,
            var10.x,
            var10.y,
            var10.z,
            var10.x + var11.x - var9x.x,
            var10.y + var11.y - var9x.y,
            var10.z + var11.z - var9x.z,
            var11.x,
            var11.y,
            var11.z
         };
      }
   }

   private static float[] j(float var0, float var1, long var2, float var4, float var5, float var6) {
      return new float[]{
         var0 + var5 / 2.0F,
         var1 + var6 / 2.0F,
         var4,
         var0 - var5 / 2.0F,
         var1 + var6 / 2.0F,
         var4,
         var0 - var5 / 2.0F,
         var1 - var6 / 2.0F,
         var4,
         var0 + var5 / 2.0F,
         var1 - var6 / 2.0F,
         var4
      };
   }

   private record GarageThumbnail(int K, int p, int s, int[] U) {
      public int mirror() {
         return this.K;
      }

      public int width() {
         return this.p;
      }

      public int height() {
         return this.s;
      }

      public int[] pixels() {
         return this.U;
      }

      public int integerState21() {
         return this.K;
      }

      public int integerState10() {
         return this.p;
      }

      public int integerState13() {
         return this.s;
      }

      public int[] integerArrayState31() {
         return this.U;
      }
   }

   private record GarageBox(class_238 B, int C) {
      public class_238 box() {
         return this.B;
      }

      public int color() {
         return this.C;
      }

      public class_238 minecraftObjectState1() {
         return this.B;
      }

      public int integerState50() {
         return this.C;
      }
   }
}
