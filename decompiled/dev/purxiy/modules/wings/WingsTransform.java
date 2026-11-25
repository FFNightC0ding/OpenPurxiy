package dev.purxiy.modules.wings;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_4588;
import net.minecraft.class_4587.class_4665;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public final class WingsTransform {
   public static final int y = 0;
   public static final int O = 1;
   public static final int P = 2;
   public static final int s = 3;
   public static final int H = 4;
   public static final int r = 5;
   public static final int t = 6;
   private static final float[] o = new float[]{6.8F, 6.4F, 5.2F};
   private static final float[] K = new float[]{58.0F, -86.0F, -106.0F};
   private static final float[] p = new float[]{50.0F, 24.0F, -6.0F};
   private static final float D = 2.2F;
   private static final int R = 3;
   static final List<WingsTransform.Data> i = C(105661031486033L);
   static final List<WingsTransform.Data> X = List.of(
      new WingsTransform.Data(6, WingsCache.TypeF.Z, 0.25F, 19.0F, 19.0F, 60.0F, 22.0F, 0.0F, 1.0F, 0.0F, 0.2F, 0.0F),
      new WingsTransform.Data(6, WingsCache.TypeF.w, 0.3F, 13.5F, 13.5F, 18.0F, -28.0F, 0.15F, 0.6F, 0.0F, 0.7F, 0.0F)
   );
   private static final float[] M = new float[4];
   private static final float[] c = new float[4];
   private static final float[] f = new float[3];
   private static final Matrix4f z = new Matrix4f();
   private static final Matrix3f C = new Matrix3f();
   private static final Vector3f n = new Vector3f();
   private static final Vector3f G = new Vector3f();
   private static final float[] l = new float[4];
   private static final float[] u = new float[4];
   private static final float[] g = new float[4];
   private static final int[] W = new int[4];
   private static final int[] a = new int[4];
   private static final long[] b = new long[]{
      1852831147757743398L,
      2760093502487508774L,
      418398866753516171L,
      -1308995495690336530L,
      5199736695396892552L,
      9176040239841372478L,
      -3276931315897157330L,
      -5263229677051550045L,
      -3863338218027575160L,
      -2623895444333047313L,
      -2984806842456149132L,
      3090895464992183270L,
      -3713799163664926212L,
      -7267653863366284177L,
      5213976943137515435L,
      -7291107091307664188L,
      7797632398126293463L,
      -8055101853542815820L,
      -226075461419041835L,
      -2462866492445538269L,
      -1411947937289508135L,
      -8953674421055739885L,
      -2597947182399713386L,
      1121343254219564881L,
      -4144443836506230787L
   };
   private static final Integer[] d = new Integer[25];

   private WingsTransform() {
   }

   private static List C(long var0) {
      long var2 = 33718136279134L;
      long var4 = 804612247842L;
      short var6 = 187;
      short var7 = 22176;
      int var8 = (int)(var4 << 48 >>> 48);
      ArrayList var9 = new ArrayList();
      int var10 = 0;

      while (var10 < 4) {
         long var11 = 105661031486032L;
         int var30 = var11 < 0L ? -1 : 1;
         float var14 = var10 / 3.0F;
         var9.add(
            new WingsTransform.Data(
               3,
               WingsCache.TypeF.i,
               0.05F + 0.28F * var14,
               6.6F - 1.2F * var14,
               3.4F,
               -98.0F - 4.0F * var14,
               -120.0F + 10.0F * var14,
               -0.62F,
               0.02F + 0.06F * var14,
               4.0F,
               D(var2, var10, 1),
               0.0F
            )
         );
         var10++;
         long var15 = 105661031486032L;
         int var39 = var15 < 0L ? -1 : 1;
      }

      float[] var28 = new float[]{9.9F, 10.7F, 11.5F, 12.3F, 13.0F, 13.5F, 13.8F, 13.7F, 13.1F, 11.7F};
      int var12 = 0;

      while (var12 < 10) {
         float var13 = var12 / 9.0F;
         float var31 = D(var2, var12, 2);
         float var34 = -82.0F + 88.0F * (float)Math.pow(var13, 0.8F) + (var31 - 0.5F) * 2.0F;
         var9.add(
            new WingsTransform.Data(
               0,
               WingsCache.TypeF.g,
               2.0F + 0.98F * var13,
               var28[var12] * (0.97F + 0.06F * var31),
               3.4F,
               -100.0F - 8.0F * var13,
               var34,
               0.08F + 0.016F * (9 - var12),
               0.72F + 0.28F * var13,
               7.0F + 5.0F * var13,
               var31,
               0.0F
            )
         );
         long var16 = 105661031486032L;
         int var46 = var16 < 0L ? -1 : 1;
         var12++;
         long var19 = 105661031486033L;
         int var54 = var19 < 0L ? -1 : 1;
      }

      for (int var29 = 0; var29 < 11; var29++) {
         long var32 = 105661031486033L;
         int var38 = var32 < 0L ? -1 : 1;
         float var17 = var29 / 10.0F;
         float var18 = D(var2, var29, 3);
         var9.add(
            new WingsTransform.Data(
               1,
               WingsCache.TypeF.H,
               2.0F - 0.95F * var17,
               (9.7F + 0.6F * var17) * (0.97F + 0.06F * var18),
               3.6F,
               -100.0F + 8.0F * var17,
               -88.0F - 9.0F * var17 + (var18 - 0.5F) * 2.5F,
               0.02F * var17,
               0.68F - 0.33F * var17,
               4.0F,
               var18,
               0.0F
            )
         );
         long var47 = 105661031486032L;
         int var57 = var47 < 0L ? -1 : 1;
      }

      float[] var30 = new float[]{9.8F, 9.1F, 8.1F, 6.9F};
      int var33 = 0;

      while (var33 < 4) {
         float var35 = var33;
         long var37 = 105661031486032L;
         int var48 = var37 < 0L ? -1 : 1;
         float var48x = var35 / 3.0F;
         var9.add(
            new WingsTransform.Data(
               2,
               WingsCache.TypeF.L,
               0.95F - 0.6F * var48x,
               var30[var33],
               3.8F,
               -92.0F + 2.0F * var48x,
               -102.0F - 16.0F * var48x,
               -0.06F,
               0.33F - 0.2F * var48x,
               3.0F,
               D(var2, var33, 4),
               0.0F
            )
         );
         var33++;
         long var20 = 105661031486032L;
         int var21 = var20 < 0L ? -1 : 1;
      }

      int var36 = 0;

      while (var36 < 11) {
         float var38 = var36 / 10.0F;
         float var40 = D(var2, var36, 5);
         long var43 = 105661031486033L;
         int var58 = var43 < 0L ? -1 : 1;
         var9.add(
            new WingsTransform.Data(
               3,
               WingsCache.TypeF.i,
               2.0F - 0.95F * var38,
               (4.7F + 0.3F * var38) * (0.94F + 0.12F * var40),
               3.5F,
               -98.0F + 7.0F * var38,
               -84.0F - 9.0F * var38 + (var40 - 0.5F) * 4.0F,
               -0.24F,
               0.68F - 0.33F * var38,
               3.0F,
               var40,
               0.25F
            )
         );
         var36++;
         long var22 = 105661031486032L;
         int var22x = var22 < 0L ? -1 : 1;
      }

      int var39 = 0;

      while (var39 < 8) {
         float var41 = var39 / 7.0F;
         float var44 = D(var2, var39, 6);
         float var49 = -80.0F + 76.0F * (float)Math.pow(var41, 0.8F) + (var44 - 0.5F) * 4.0F;
         long var52 = 105661031486033L;
         int var64 = var52 < 0L ? -1 : 1;
         var9.add(
            new WingsTransform.Data(
               3,
               WingsCache.TypeF.i,
               2.02F + 0.86F * var41,
               (4.6F - 0.9F * var41) * (0.94F + 0.12F * var44),
               3.1F,
               -100.0F - 6.0F * var41,
               var49,
               -0.22F,
               0.72F + 0.22F * var41,
               3.0F,
               var44,
               0.25F
            )
         );
         var39++;
         long var23 = 105661031486033L;
         int var25 = var23 < 0L ? -1 : 1;
      }

      int var42 = 0;

      while (var42 < 14) {
         float var45 = var42 / 13.0F;
         float var50 = D(var2, var42, 7);
         long var53 = 105661031486032L;
         int var66 = var53 < 0L ? -1 : 1;
         float var56 = 0.3F + 2.5F * var45;
         var9.add(
            new WingsTransform.Data(
               4,
               WingsCache.TypeF.i,
               var56,
               (3.9F - 0.9F * var45) * (0.92F + 0.16F * var50),
               3.4F - 0.5F * var45,
               -92.0F - 12.0F * var45,
               A(var56, var6, var7, (short)var8) + 10.0F + (var50 - 0.5F) * 6.0F,
               -0.4F,
               I(var56),
               2.0F,
               var50,
               0.75F
            )
         );
         var42++;
         long var24 = 105661031486032L;
         int var251 = var24 < 0L ? -1 : 1;
      }

      int var46 = 0;

      while (var46 < 20) {
         float var51 = var46 / 19.0F;
         float var54 = D(var2, var46, 8);
         float var21 = 0.02F + 2.9F * var51;
         long var55 = 105661031486032L;
         int var69 = var55 < 0L ? -1 : 1;
         var9.add(
            new WingsTransform.Data(
               5,
               WingsCache.TypeF.Y,
               var21,
               (3.0F - 0.8F * var51) * (0.9F + 0.2F * var54),
               3.1F - 0.6F * var51,
               -100.0F - 8.0F * var51,
               A(var21, var6, var7, (short)var8) + 32.0F + (var54 - 0.5F) * 8.0F,
               -0.56F,
               I(var21),
               0.0F,
               var54,
               1.25F
            )
         );
         var46++;
         long var25 = 105661031486033L;
         int var26 = var25 < 0L ? -1 : 1;
      }

      return List.copyOf(var9);
   }

   private static float A(float var0, int var1, short var2, short var3) {
      if (var0 < 1.0F) {
         return -102.0F - 16.0F * (1.0F - var0) * 0.6F;
      } else {
         return var0 < 2.0F ? -88.0F - 9.0F * (2.0F - var0) : -82.0F + 88.0F * (float)Math.pow(Math.min(1.0F, (var0 - 2.0F) / 0.98F), 0.8F);
      }
   }

   private static float I(float var0) {
      return Math.min(1.0F, var0 / 3.0F) * 0.9F;
   }

   private static float D(long var0, int var2, int var3) {
      int var4 = var2 * 668265261 + var3 * 374761393;
      var4 ^= var4 >>> 15;
      var4 *= 739982445;
      var4 ^= var4 >>> 12;
      return (var4 & 65535) / 65535.0F;
   }

   static void build(WingsTransform.TypeR var0, WingsSupport2 var1, boolean var2, Vector3f var3, WingDesign var4, int var5, float var6, float var7) {
      long var8 = 102051715568459L;
      long var10 = var8 ^ 10932397058017L;
      long var12 = var8 ^ 14136147724592L;
      int var14 = (int)((var8 ^ 14136147724592L) >>> 48);
      int var15 = (int)((var8 ^ 14136147724592L) << 16 >>> 32);
      int var16 = (int)(var12 << 48 >>> 48);
      long var17 = (var8 ^ 135992425234593L) >>> 16;
      int var19 = (int)((var8 ^ 135992425234593L) << 48 >>> 48);
      float var20 = var1.Q;
      float var21 = C(var10, var1.X);
      float var22 = 2.2F * (1.0F - X((short)var14, var15, var21, (char)var16)) * var20;
      z.identity()
         .translate(var2 ? var3.x : -var3.x, var3.y - var22, var3.z)
         .scale(var2 ? 1.0F : -1.0F, -1.0F, -1.0F)
         .rotateZ(var1.v)
         .rotateY(var1.b)
         .rotateX(var1.p)
         .scale(var20);
      z.normal(C);

      for (int var23 = 0; var23 < 3; var23++) {
         f[var23] = (float)Math.toRadians(T(K[var23], p[var23], var23 == 0 ? X((short)var14, var15, var21, (char)var16) : var21));
      }

      M[0] = 0.0F;
      c[0] = 0.0F;

      for (int var26 = 0; var26 < 3; var26++) {
         M[var26 + 1] = M[var26] + o[var26] * (float)Math.cos(f[var26]);
         c[var26 + 1] = c[var26] + o[var26] * (float)Math.sin(f[var26]);
      }

      float var27 = 26.0F;

      for (WingsTransform.Data var25 : var4.w ? X : i) {
         Q(var0, var1, var25, var2, var4, var5, var6, var7, var17, var21, var27, (char)var19);
      }
   }

   private static void Q(
      WingsTransform.TypeR var0,
      WingsSupport2 var1,
      WingsTransform.Data var2,
      boolean var3,
      WingDesign var4,
      int var5,
      float var6,
      float var7,
      long var8,
      float var10,
      float var11,
      char var12
   ) {
      long var13 = 43430448436202L;
      long var15 = var13 ^ 34712969958216L;
      long var17 = var13 ^ 102361349168581L;
      int var19 = (int)((var13 ^ 102361349168581L) >>> 32);
      int var20 = (int)((var13 ^ 102361349168581L) << 32 >>> 48);
      int var21 = (int)(var17 << 48 >>> 48);
      long var22 = var13 ^ 33072929527422L;
      WingsUpdater2.ensureSourceClassInitialized();
      float var24 = Math.max(0.0F, Math.min(2.999F, var2.i));
      int var25 = (int)var24;
      float var26 = var24 - var25;
      float var27 = M[var25] + (M[var25 + 1] - M[var25]) * var26;
      float var28 = c[var25] + (c[var25 + 1] - c[var25]) * var26;
      float var29 = f[var25];
      float var30 = var27 + -((float)Math.sin(var29)) * var2.p;
      float var31 = var28 + (float)Math.cos(var29) * var2.p;
      float var32;
      if (var2.N == 0) {
         var32 = (float)Math.pow(var10, 1.35F);
      } else {
         var32 = var2.N != 1 && var2.N != 2 ? (float)Math.pow(var10, 0.8F) : var10;
      }

      float var34 = T(var2.E, var2.W, var32);
      long var35 = 662696051L;
      int var53 = var35 < 0L ? -1 : 1;
      if (var2.N == 0) {
         float var38 = var1.i * 14.0F * var2.Y;
         var34 += var38 * var32;
      } else if (var2.N == 1) {
         var34 += var1.i * 3.0F * var32;
      }

      var34 += var1.y * 4.5F * (float)Math.sin(var7 * 37.0F + var2.M * 50.0F) * (0.4F + var2.Y);
      float var53x = var34 + var1.G * 2.2F * (float)Math.sin(var7 * 23.0F + var2.M * 31.0F) * var2.Y;
      float var39 = var2.a;
      float var40 = var2.D;
      if (var2.N == 6) {
         i(var0, var1, var2, var3, var4, var5, var6, var7, var10, var15, var30, var31, var53x, 26.0F);
      } else {
         float var41 = (float)Math.toRadians(var2.H * (0.4F + 0.6F * var32) + var1.K * 6.0F * var2.Y);
         float var42 = (float)Math.toRadians(var53x);
         l[0] = var30;
         u[0] = var31;
         g[0] = var42;

         for (int var43 = 1; var43 <= 3; var43++) {
            float var44 = (var43 - 0.5F) / 3.0F;
            float var45 = var42 - var41 * var44;
            l[var43] = l[var43 - 1] + var39 / 3.0F * (float)Math.cos(var45);
            u[var43] = u[var43 - 1] + var39 / 3.0F * (float)Math.sin(var45);
            g[var43] = var42 - var41 * var43 / 3.0F;
         }

         int[] var54 = W;
         int[] var55 = a;

         for (int var56 = 0; var56 <= 3; var56++) {
            float var46 = var56 / 3.0F;
            var54[var56] = var4.feather(var2.N, var2.Y, var46, var2.M, var7, var5);
            var55[var56] = var4.light(var2.N, var2.Y, var46, var2.M, var7, var5, var6);
         }

         WingsCache.TypeF var57 = var2.F;
         float var58 = var40 / 2.0F;

         for (int var47 = 0; var47 < 3; var47++) {
            int var48 = var0.E;
            R(var0, var1, var2, var47, var19, (short)var20, (char)var21, var58, true, var57.u(0.0F), var57.v(var47 / 3.0F), var54[var47], var55[var47], 26.0F);
            R(var0, var1, var2, var47, var19, (short)var20, (char)var21, var58, false, var57.u(1.0F), var57.v(var47 / 3.0F), var54[var47], var55[var47], 26.0F);
            R(
               var0,
               var1,
               var2,
               var47 + 1,
               var19,
               (short)var20,
               (char)var21,
               var58,
               false,
               var57.u(1.0F),
               var57.v((var47 + 1) / 3.0F),
               var54[var47 + 1],
               var55[var47 + 1],
               26.0F
            );
            long var49 = 662696051L;
            int var49x = var49 < 0L ? -1 : 1;
            R(
               var0,
               var1,
               var2,
               var47 + 1,
               var19,
               (short)var20,
               (char)var21,
               var58,
               true,
               var57.u(0.0F),
               var57.v((var47 + 1) / 3.0F),
               var54[var47 + 1],
               var55[var47 + 1],
               26.0F
            );
            if (!var3) {
               var0.d(var22, var48);
            }
         }
      }
   }

   private static void R(
      WingsTransform.TypeR var0,
      WingsSupport2 var1,
      WingsTransform.Data var2,
      int var3,
      int var4,
      short var5,
      char var6,
      float var7,
      boolean var8,
      float var9,
      float var10,
      int var11,
      int var12,
      float var13
   ) {
      long var14 = 134583532686895L;
      long var16 = var14 ^ 131561157866190L;
      float var18 = g[var3];
      float var19 = var8 ? var7 : -var7;
      float var20 = l[var3] - (float)Math.sin(var18) * var19;
      float var21 = u[var3] + (float)Math.cos(var18) * var19;
      float var22 = var3 / 3.0F;
      float var23 = (var20 * var20 + var21 * var21) / 676.0F;
      float var24 = var2.L - 0.04F * var2.a * (float)Math.sin(Math.PI * var22) + var1.V * var23;
      float var25 = 2.0F * var1.V / 676.0F;
      G.set(-var25 * var20, -var25 * var21, 1.0F).normalize();
      n.set(var20, var21, var24);
      z.transformPosition(n);
      C.transform(G).normalize();
      var0.n(n, G, var16, var9, var10, var11, var12);
   }

   private static void i(
      WingsTransform.TypeR var0,
      WingsSupport2 var1,
      WingsTransform.Data var2,
      boolean var3,
      WingDesign var4,
      int var5,
      float var6,
      float var7,
      float var8,
      long var9,
      float var11,
      float var12,
      float var13,
      float var14
   ) {
      long var15 = 93602938483882L;
      long var17 = 52369661290895L;
      long var19 = 63134211818900L;
      WingsUpdater2.ensureSourceClassInitialized();
      float var21 = (float)Math.toRadians(var13 - 45.0F);
      float var22 = var2.a * (0.55F + 0.45F * (float)Math.sqrt(C(var15, var8)));
      float var23 = (float)Math.cos(var21) * var22;
      float var24 = (float)Math.sin(var21) * var22;
      float var25 = -((float)Math.sin(var21)) * var22;
      float var26 = (float)Math.cos(var21) * var22;
      int var27 = var0.E;
      byte var28 = 4;

      for (int var29 = 0; var29 < var28; var29++) {
         for (int var30 = 0; var30 < var28; var30++) {
            int var31 = var0.E;
            y(var0, var1, var2, var4, var5, var6, var7, var11, var12, var23, var24, var25, var26, (float)var30 / var28, (float)var29 / var28, 26.0F, var17);
            long var32 = 62594019606690L;
            int var35 = var32 < 0L ? -1 : 1;
            y(
               var0,
               var1,
               var2,
               var4,
               var5,
               var6,
               var7,
               var11,
               var12,
               var23,
               var24,
               var25,
               var26,
               (float)(var30 + 1) / var28,
               (float)var29 / var28,
               26.0F,
               var17
            );
            y(
               var0,
               var1,
               var2,
               var4,
               var5,
               var6,
               var7,
               var11,
               var12,
               var23,
               var24,
               var25,
               var26,
               (float)(var30 + 1) / var28,
               (float)(var29 + 1) / var28,
               26.0F,
               var17
            );
            long var35x = 62594019606689L;
            int var37 = var35x < 0L ? -1 : 1;
            long var37x = 62594019606690L;
            int var38 = var37x < 0L ? -1 : 1;
            y(
               var0,
               var1,
               var2,
               var4,
               var5,
               var6,
               var7,
               var11,
               var12,
               var23,
               var24,
               var25,
               var26,
               (float)var30 / var28,
               (float)(var29 + 1) / var28,
               26.0F,
               var17
            );
            if (!var3) {
               var0.d(var19, var31);
            }
         }

         long var39 = 62594019606689L;
         int var40 = var39 < 0L ? -1 : 1;
      }
   }

   private static void y(
      WingsTransform.TypeR var0,
      WingsSupport2 var1,
      WingsTransform.Data var2,
      WingDesign var3,
      int var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      float var12,
      float var13,
      float var14,
      float var15,
      long var16
   ) {
      long var18 = 15119689727201L;
      float var20 = var7 + var9 * var13 + var11 * var14;
      float var21 = var8 + var10 * var13 + var12 * var14;
      float var22 = (float)Math.hypot(var13, var14) / 1.4142F;
      float var23 = (var20 * var20 + var21 * var21) / 676.0F;
      float var24 = var2.L + var1.V * var23 * 1.3F - 0.6F * (float)Math.sin(Math.PI * Math.min(1.0F, var13)) * (float)Math.sin(Math.PI * Math.min(1.0F, var14));
      float var25 = 2.0F * var1.V * 1.3F / 676.0F;
      G.set(-var25 * var20, -var25 * var21, 1.0F).normalize();
      n.set(var20, var21, var24);
      z.transformPosition(n);
      C.transform(G).normalize();
      WingsCache.TypeF var26 = var2.F;
      int var27 = var3.feather(var2.N, var2.Y * var22, var22, var2.M, var6, var4);
      int var28 = var3.light(var2.N, var2.Y * var22, var22, var2.M, var6, var4, var5);
      var0.n(n, G, var18, var26.u(var13), var26.v(var14), var27, var28);
   }

   private static float C(long var0, float var2) {
      WingsUpdater2.ensureSourceClassInitialized();
      return var2 < 0.0F ? 0.0F : Math.min(var2, 1.0F);
   }

   private static float X(short var0, int var1, float var2, char var3) {
      long var4 = 88010596096635L;
      long var6 = var4 ^ 5678780706513L;
      var2 = C(var6, var2);
      return var2 * var2 * (3.0F - 2.0F * var2);
   }

   private static float T(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   record Data(int N, WingsCache.TypeF F, float i, float a, float D, float E, float W, float L, float Y, float H, float M, float p) {
      public int row() {
         return this.N;
      }

      public WingsCache.TypeF cell() {
         return this.F;
      }

      public float anchor() {
         return this.i;
      }

      public float length() {
         return this.a;
      }

      public float width() {
         return this.D;
      }

      public float folded() {
         return this.E;
      }

      public float spread() {
         return this.W;
      }

      public float depth() {
         return this.L;
      }

      public float out() {
         return this.Y;
      }

      public float bend() {
         return this.H;
      }

      public float seed() {
         return this.M;
      }

      public float lead() {
         return this.p;
      }

      public int integerState28() {
         return this.N;
      }

      public WingsCache.TypeF featherTextureAtlas_FeatherKindState9() {
         return this.F;
      }

      public float floatState12() {
         return this.i;
      }

      public float floatState31() {
         return this.a;
      }

      public float floatState24() {
         return this.D;
      }

      public float floatState44() {
         return this.E;
      }

      public float floatState4() {
         return this.W;
      }

      public float floatState57() {
         return this.L;
      }

      public float floatState26() {
         return this.Y;
      }

      public float floatState7() {
         return this.H;
      }

      public float floatState27() {
         return this.M;
      }

      public float floatState35() {
         return this.p;
      }
   }

   public static final class TypeR {
      private static final int w = 3120;
      final float[] c = new float[9360];
      final float[] I = new float[9360];
      final float[] G = new float[6240];
      final int[] g = new int[3120];
      final int[] T = new int[3120];
      int E;
      boolean D;
      private float f;
      private float H = -0.9F;
      private float u;
      private static final long[] a = new long[]{-3792842297734169273L, 8074859811864570951L};
      private static final Integer[] b = new Integer[2];

      void clear() {
         this.E = 0;
         this.D = false;
      }

      private void n(Vector3f var1, Vector3f var2, long var3, float var5, float var6, int var7, int var8) {
         WingsUpdater2.ensureSourceClassInitialized();
         int var9 = this.E++;
         this.c[var9 * 3] = var1.x;
         this.c[var9 * 3 + 1] = var1.y;
         this.c[var9 * 3 + 2] = var1.z;
         long var10 = 15119689727201L;
         int var12 = var10 < 0L ? -1 : 1;
         this.I[var9 * 3] = var2.x;
         this.I[var9 * 3 + 1] = var2.y;
         this.I[var9 * 3 + 2] = var2.z;
         this.G[var9 * 2] = var5;
         this.G[var9 * 2 + 1] = var6;
         this.g[var9] = var7;
         this.T[var9] = var8;
         if (var8 >>> 24 > 3) {
            this.D = true;
         }
      }

      private void d(long var1, int var3) {
         long var4 = 121651298290845L;
         this.Q(var3 + 1, var3 + 3, var4);
      }

      private void Q(int var1, int var2, long var3) {
         WingsUpdater2.ensureSourceClassInitialized();

         for (int var5 = 0; var5 < 3; var5++) {
            float var6 = this.c[var1 * 3 + var5];
            this.c[var1 * 3 + var5] = this.c[var2 * 3 + var5];
            int var7 = var2 * 3 + var5;
            long var8 = 121651298290844L;
            int var18 = var8 < 0L ? -1 : 1;
            this.c[var7] = var6;
            float var11 = this.I[var1 * 3 + var5];
            this.I[var1 * 3 + var5] = this.I[var2 * 3 + var5];
            this.I[var2 * 3 + var5] = var11;
         }

         long var15 = 121651298290844L;
         int var13 = var15 < 0L ? -1 : 1;

         for (int var9 = 0; var9 < 2; var9++) {
            float var10 = this.G[var1 * 2 + var9];
            this.G[var1 * 2 + var9] = this.G[var2 * 2 + var9];
            this.G[var2 * 2 + var9] = var10;
            long var17 = 121651298290845L;
            int var11 = var17 < 0L ? -1 : 1;
         }

         long var16 = 121651298290844L;
         int var14 = var16 < 0L ? -1 : 1;
         var14 = this.g[var1];
         this.g[var1] = this.g[var2];
         this.g[var2] = var14;
         int var18 = this.T[var1];
         this.T[var1] = this.T[var2];
         this.T[var2] = var18;
      }

      public void emit(class_4665 var1, class_4588 var2, int var3, int var4) {
         for (byte var5 = 0; var5 < this.E; var5 = (byte)(var5 + 4)) {
            for (int var6 = 0; var6 < 4; var6++) {
               this.d(var1, var2, var5 + var6, 1.0F, var3, var4);
            }

            for (int var7 = 0; var7 < 4; var7++) {
               this.d(var1, var2, var5 + (4 - var7) % 4, -1.0F, var3, var4);
            }
         }
      }

      private void d(class_4665 var1, class_4588 var2, int var3, float var4, int var5, int var6) {
         float var7 = this.I[var3 * 3] * var4 * 0.55F + this.f;
         float var8 = this.I[var3 * 3 + 1] * var4 * 0.55F + this.H;
         float var9 = this.I[var3 * 3 + 2] * var4 * 0.55F + this.u;
         float var10 = (float)Math.sqrt(var7 * var7 + var8 * var8 + var9 * var9);
         var2.method_56824(var1, this.c[var3 * 3] / 16.0F, this.c[var3 * 3 + 1] / 16.0F, this.c[var3 * 3 + 2] / 16.0F)
            .method_39415(this.g[var3])
            .method_22913(this.G[var3 * 2], this.G[var3 * 2 + 1])
            .method_22922(var6)
            .method_60803(var5)
            .method_60831(var1, var7 / var10, var8 / var10, var9 / var10);
      }

      void lightFrom(float var1, float var2, float var3) {
         this.f = var1;
         this.H = var2;
         this.u = var3;
      }

      public void emitLight(class_4665 var1, class_4588 var2, int var3) {
         for (byte var4 = 0; var4 < this.E; var4 = (byte)(var4 + 4)) {
            int var5 = (this.T[var4] | this.T[var4 + 1] | this.T[var4 + 2] | this.T[var4 + 3]) >>> 24;
            if (var5 > 2) {
               for (int var6 = 0; var6 < 8; var6++) {
                  int var7 = var4 + (var6 < 4 ? var6 : (8 - var6) % 4);
                  var2.method_56824(var1, this.c[var7 * 3] / 16.0F, this.c[var7 * 3 + 1] / 16.0F, this.c[var7 * 3 + 2] / 16.0F)
                     .method_39415(this.T[var7])
                     .method_22913(this.G[var7 * 2], this.G[var7 * 2 + 1])
                     .method_22922(0)
                     .method_60803(var3)
                     .method_60831(var1, this.I[var7 * 3], this.I[var7 * 3 + 1], this.I[var7 * 3 + 2]);
               }
            }
         }
      }
   }
}
