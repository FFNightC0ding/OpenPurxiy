// Sourced By DevFFNightmare
package dev.purxiy.modules.knife;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.purxiy.duck.RenderPassHolder;
import dev.purxiy.duck.ScreenHook;
import dev.purxiy.loader.fabric.client.ClientRegistry17;
import dev.purxiy.loader.fabric.client.ClientRegistry19;
import dev.purxiy.loader.fabric.mixin.ModelPartAccessor;
import dev.purxiy.render.RenderPipelines;
import java.util.Locale;
import net.minecraft.class_1007;
import net.minecraft.class_11659;
import net.minecraft.class_12249;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1664;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4608;
import net.minecraft.class_591;
import net.minecraft.class_630;
import net.minecraft.class_742;
import net.minecraft.class_746;
import net.minecraft.class_7920;
import net.minecraft.class_898;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_630.class_593;
import net.minecraft.class_630.class_618;
import net.minecraft.class_630.class_628;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public final class KnifeRenderer {
   private static final Vector3f i = new Vector3f();
   private static final Vector3f T = new Vector3f();
   private static final Vector3f r = new Vector3f();
   private static final Vector3f y = new Vector3f();
   private static final Vector3f F = new Vector3f();
   private static final Vector3f W = new Vector3f();
   private static final float N = 0.5F;
   private static final float b = 0.08F;
   private static final Quaternionf J = new Quaternionf();
   private static final KnifeGeometry.TypeZ M = new KnifeGeometry.TypeZ();
   private static final KnifeGeometry.TypeZ k = new KnifeGeometry.TypeZ();
   private static final Vector3f B = new Vector3f(-0.2F, -0.72F, -0.28F);
   private static final Vector3f w = new Vector3f(0.15F, 0.35F, -1.0F);
   private static final Matrix4f V = new Matrix4f();
   private static final Matrix4f u = new Matrix4f();
   private static long p = -1L;
   private static int q = 1;
   private static final float g = 0.97F;
   private static final float S = 4.0F;

   private static final String[] c = new String[3];

   private KnifeRenderer() {
   }

   public static boolean render(
      class_742 var0, class_1268 var1, class_1799 var2, float var3, float var4, class_4587 var5, class_11659 var6, int var7, class_898 var8
   ) {
      long var9 = 123385394400531L;
      long var11 = var9 ^ 85071464739107L;
      int var13 = (int)((var9 ^ 85071464739107L) >>> 48);
      int var14 = (int)((var9 ^ 85071464739107L) << 16 >>> 32);
      int var15 = (int)(var11 << 48 >>> 48);
      long var16 = var9 ^ 43331220555090L;
      if (var1 == class_1268.field_5808 && !var0.method_7325() && var0 instanceof class_746 var18) {
         KnifeApi var19 = KnifeFactory3.of(var2);
         if (var19 == null) {
            return false;
         } else {
            class_310 var20 = class_310.method_1551();
            float var21 = var20.method_61966().method_60637(true);
            boolean var22 = !var0.method_6079().method_7960();
            KnifeCurve var23 = KnifeSupport3.frame(var19, var18, var21, var22, var19.draws());
            int var24 = var0.method_6068() == class_1306.field_6183 ? 1 : -1;
            boolean var25 = var0.method_52814().comp_1629() == class_7920.field_41122;
            var5.method_22903();
            float var26 = (float)(Math.tan(Math.toRadians(ClientRegistry19.fov()) * 0.5) / Math.tan(Math.toRadians(var19.fov()) * 0.5));
            var5.method_23760().method_23761().mulLocal(new Matrix4f().scaling(var26, var26, 1.0F));
            Matrix4f var27 = ClientRegistry17.placement(class_1268.field_5808, var24, 0.5F, new Matrix4f());
            var5.method_34425(ClientRegistry17.placement(class_1268.field_5808, var24, 1.0F, new Matrix4f()).invert().mul(var27));
            if (var4 > 0.001F && var0.method_6047().method_7909() != var2.method_7909()) {
               float var28 = var4 * var4 * (3.0F - 2.0F * var4);
               var5.method_46416(0.0F, -0.85F * var28, 0.12F * var28);
               var5.method_22907(new Quaternionf().rotationX(-0.6F * var28));
               var27.translate(0.0F, -0.85F * var28, 0.12F * var28).rotateX(-0.6F * var28);
            }

            Vector3f var41 = new Vector3f(0.1F * var24, -0.3F, -0.5F);
            Vector3f var29 = var27.transformPosition(new Vector3f(var41)).sub(var41);
            var29.set(var29.x * 0.5F, Math.min(var29.y * 0.25F, 0.08F), 0.0F);
            var27.invert();
            G(KnifeGeometry.H, var24, var29, var27, F);
            G(KnifeGeometry.F, var24, var29, var27, W);
            V.set(var5.method_23760().method_23761());
            u.set(RenderSystem.getModelViewMatrix());
            p = RenderPipelines.get().frame();
            q = var24;
            var23.position(i);
            var23.rotation(J);
            var5.method_22903();
            var5.method_22905(var24, 1.0F, 1.0F);
            var5.method_46416(i.x, i.y, i.z);
            var5.method_22907(J);
            KnifeTransform.TypeH var30 = KnifeTransform.sheet(var19.sheet());
            var5.method_22905(var30.M, var30.M, var30.M);
            ClientRegistry19.sweeps(false);
            var19.submit(var5, var6, var7, var2.method_7958(), KnifeSupport6.glint(var19));
            var5.method_22909();
            if (!var0.method_5767()) {
               J.transform(T.set(0.0F, 1.0F, 0.0F));
               float var31 = var19.armScale();
               float[] var32 = var19.grips();
               float var33 = var32 != null ? var32[0] : var30.k;
               float var34 = var32 != null ? var32[1] : var30.b;
               float var35 = (var25 ? 1.5F : 2.0F) * var31 / 16.0F;
               U(var33 * var30.M, 0.0F, r);
               F(var19.handleRadius(var33) * var30.M, var35, r, var16);
               KnifeGeometry.solve(true, var25, r, T, F, var31, M);
               class_1007 var36 = var8.method_74405(var0);
               class_2960 var37 = var0.method_52814().comp_1626().comp_3627();
               Y(var36, var5, var6, var7, (short)var13, var14, var37, var0, var24, true, (char)var15, M);
               float var38 = var23.left();
               if (var38 > 0.01F) {
                  U(var34 * var30.M, var23.R[7], r);
                  F(var19.handleRadius(var34 - var23.R[7] / var30.M) * var30.M, var35, r, var16);
                  Vector3f var39 = new Vector3f(T);
                  if (var38 < 0.999F) {
                     float var40 = var38 * var38 * (3.0F - 2.0F * var38);
                     r.lerp(B, 1.0F - var40);
                     var39.lerp(new Vector3f(w).normalize(), 1.0F - var40).normalize();
                  }

                  KnifeGeometry.solve(false, var25, r, var39, W, var31, k);
                  Y(var36, var5, var6, var7, (short)var13, var14, var37, var0, var24, false, (char)var15, k);
               }
            }

            ClientRegistry19.sweeps(true);
            var5.method_22909();
            KnifeUpdater2.metric("great_drawn");
            KnifeUpdater2.metric("drawn");
            return true;
         }
      } else {
         return false;
      }
   }

   private static void G(Vector3f var0, int var1, Vector3f var2, Matrix4f var3, Vector3f var4) {
      var4.set(var0.x * var1, var0.y, var0.z).add(var2);
      var4.z = Math.max(var4.z, 0.08F);
      var3.transformPosition(var4);
      var4.x *= var1;
   }

   private static void U(float var0, float var1, Vector3f var2) {
      J.transform(var2.set(0.0F, -var0 + var1, 0.0F)).add(i);
   }

   private static void F(float var0, float var1, Vector3f var2, long var3) {
      KnifeUpdater2.ensureSourceClassInitialized();
      float var5 = var0 - var1 * 0.3F;
      if (!(var5 <= 0.0F)) {
         Vector3f var6 = new Vector3f(var2).negate();
         long var7 = 96066348828736L;
         if (96066348828736L == 0L) {
            boolean var10000 = false;
         } else {
            int var12 = var7 < 0L ? -1 : 1;
         }

         var6.fma(-var6.dot(T), T);
         long var10 = 96066348828736L;
         if (96066348828736L == 0L) {
            boolean var13 = false;
         } else {
            int var14 = var10 < 0L ? -1 : 1;
         }

         if (var6.lengthSquared() > 1.0E-8F) {
            var2.fma(var5, var6.normalize());
         }
      }
   }

   private static void Y(
      class_1007 var0,
      class_4587 var1,
      class_11659 var2,
      int var3,
      short var4,
      int var5,
      class_2960 var6,
      class_742 var7,
      int var8,
      boolean var9,
      char var10,
      KnifeGeometry.TypeZ var11
   ) {
      KnifeUpdater2.ensureSourceClassInitialized();
      boolean var12 = var9 == (var8 == 1);
      var1.method_22903();
      var1.method_22905(var8, 1.0F, 1.0F);
      var1.method_34425(var11.z);
      var1.method_22905(var8, 1.0F, 1.0F);
      if (var12) {
         var0.method_4220(var1, var2, var3, var6, var7.method_74091(class_1664.field_7570));
      } else {
         var0.method_4221(var1, var2, var3, var6, var7.method_74091(class_1664.field_7568));
      }

      class_591 var13 = (class_591)(var0.method_4038());
      ((ScreenHook)(Object)(var12 ? var13.field_3401 : var13.field_27433)).purxiy$bend(var11.M, -1.0F);
      var1.method_22909();
      var1.method_22903();
      var1.method_22905(var8, 1.0F, 1.0F);
      var1.method_34425(var11.y);
      var1.method_22905(var8, 1.0F, 1.0F);
      boolean var14 = var7.method_74091(var12 ? class_1664.field_7570 : class_1664.field_7568);
      var2.method_73483(
         var1,
         class_12249.method_76000(var6),
         new KnifeRenderer.TypeM(var12 ? var13.field_3401 : var13.field_27433, var14 ? (var12 ? var13.field_3486 : var13.field_3484) : null, var11.T, var3)
      );
      var1.method_22909();
   }

   
   public static Matrix4f drawnIn() {
      return p == RenderPipelines.get().frame() ? V : null;
   }

   public static Matrix4f modelView() {
      return u;
   }

   public static int drawnSide() {
      return q;
   }

   static String describeArms() {
      return String.format(Locale.ROOT, "main %s %s %s other %s %s %s", M.e, M.h, M.R, k.e, k.h, k.R);
   }

private record TypeM(class_630 c,  class_630 w, float X, int m) implements RenderPassHolder {
      @Override
      public void render(class_4665 var1, class_4588 var2) {
         J(this.c, var1, var2, this.X, this.m);
         if (this.w != null) {
            J(this.w, var1, var2, this.X, this.m);
         }
      }

      private static void J(class_630 var0, class_4665 var1, class_4588 var2, float var3, int var4) {
         KnifeUpdater2.ensureSourceClassInitialized();

         for (class_628 var6 : ((ModelPartAccessor)(Object)(var0)).purxiy$cubes()) {
            float var7 = (var6.field_3645 + var6.field_3648) * 0.5F;
            float var8 = (var6.field_3643 + var6.field_3646) * 0.5F;
            float var9 = var6.field_3647 - var6.field_3644;
            float var10 = (var6.field_3646 - var6.field_3643) * 0.5F;

            for (class_593 var14 : var6.field_3649) {
               class_618[] var15 = var14.comp_3184();
               boolean var16 = true;
               boolean var17 = true;
               float var18 = 0.0F;
               float var19 = 0.0F;

               for (class_618 var23 : var15) {
                  boolean var24 = Math.abs(var23.comp_4805() - var6.field_3644) < 0.001F;
                  var16 &= var24;
                  var17 &= !var24;
                  if (var24) {
                     var18 = var23.comp_3188();
                  } else {
                     var19 = var23.comp_3188();
                  }
               }

               if (!var17) {
                  Vector3fc var30 = var14.comp_3185();

                  for (class_618 var34 : var15) {
                     boolean var25 = Math.abs(var34.comp_4805() - var6.field_3644) < 0.001F;
                     float var26 = (var34.comp_4804() - var7) * 0.97F;
                     float var27 = (var34.comp_4806() - var8) * 0.97F;
                     float var28 = var25 ? -var3 : var10;
                     float var29 = !var16 && !var25 ? var18 + (var19 - var18) * 4.0F / var9 : var34.comp_3188();
                     var2.method_56824(var1, var26 / 16.0F, var28 / 16.0F, var27 / 16.0F)
                        .method_39415(-1)
                        .method_22913(var34.comp_3187(), var29)
                        .method_22922(class_4608.field_21444)
                        .method_60803(var4)
                        .method_60831(var1, var30.x(), var30.y(), var30.z());
                  }
               }
            }
         }
      }

      public class_630 arm() {
         return this.c;
      }

      
      public class_630 sleeve() {
         return this.w;
      }

      public float length() {
         return this.X;
      }

      public int light() {
         return this.m;
      }

      public class_630 minecraftObjectState1() {
         return this.c;
      }

      
      public class_630 minecraftObjectState2() {
         return this.w;
      }

      public float floatState17() {
         return this.X;
      }

      public int integerState17() {
         return this.m;
      }
   }
}
