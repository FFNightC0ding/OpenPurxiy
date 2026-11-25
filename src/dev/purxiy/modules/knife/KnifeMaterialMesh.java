package dev.purxiy.modules.knife;

import dev.purxiy.duck.RenderPassHolder;
import dev.purxiy.loader.fabric.client.SwingStateProvider;
import dev.purxiy.loader.fabric.client.HandSweepPipeline;
import net.minecraft.class_1007;
import net.minecraft.class_11659;
import net.minecraft.class_12249;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1664;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4608;
import net.minecraft.class_742;
import net.minecraft.class_7833;
import net.minecraft.class_7920;
import net.minecraft.class_898;
import net.minecraft.class_4587.class_4665;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;
import org.joml.Vector3f;

public final class KnifeMaterialMesh {
   private static final int[] W = new int[]{0, 1, 2, 2};
   private static final int[] j = new int[]{2, 1, 0, 0};
   private static final Vector3f N = new Vector3f(-0.29F, 0.52F, 0.8F).normalize();
   private static final KnifeGestureRenderer.GesturePose H = new KnifeGestureRenderer.GesturePose();
   private static final String[] b = new String[4];

   private KnifeMaterialMesh() {
   }

   public static boolean render(
      class_742 var0, class_1268 var1, class_1799 var2, float var3, float var4, class_4587 var5, class_11659 var6, int var7, class_898 var8
   ) {
      long var9 = 47006238017272L;
      long var11 = var9 ^ 82713670109588L;
      if (KnifeRenderer.render(var0, var1, var2, var3, var4, var5, var6, var7, var8)) {
         KnifeSwingState.metric("knife_drawn");
         KnifeSwingState.metric("drawn");
         return true;
      } else if (KnifeSwingState.replaces(var2, var1) && !var0.method_7325()) {
         class_1306 var13 = var1 == class_1268.field_5808 ? var0.method_6068() : var0.method_6068().method_5928();
         int var14 = var13 == class_1306.field_6183 ? 1 : -1;
         KnifeGestureRenderer.GesturePose var15 = KnifeSwingState.frame(var2, var1, var3);
         KnifeModule var16 = KnifeSwingState.Z;
         int var17 = KnifeModels.index((String)var16.U.get());
         float var18 = ((Double)var16.l.get()).floatValue();
         boolean var19 = var0.method_52814().comp_1629() == class_7920.field_41122;
         var5.method_22903();
         var5.method_46416(var14 * 0.56F, -0.52F + var4 * -0.6F, -0.72F);
         Matrix4f var20 = new Matrix4f(var5.method_23760().method_23761()).scale(var14, 1.0F, 1.0F);
         var5.method_22903();
         Vector3f var21 = KnifePlacement.fist();
         var5.method_46416(var14 * var21.x, var21.y, var21.z);
         if (!SwingStateProvider.swingItem(var5, var1, var14)) {
            B(var5, var14, var3);
         }

         var5.method_46416(-var14 * var21.x, -var21.y, -var21.z);
         var5.method_22905(var14, 1.0F, 1.0F);
         Matrix4f var22 = var20.invert().mul(var5.method_23760().method_23761());
         var5.method_34425(sized(var17, var15.r, var18, new Matrix4f()));
         var5.method_22905(var14, 1.0F, 1.0F);
         KnifeSwingState.Data var23 = KnifeSwingState.trick(var1);
         if (var23 != null) {
            HandSweepPipeline.sweep(
               var1,
               (var5x, var6x) -> {
                  KnifeGestureRenderer.GesturePose var7x = var23.at(var5x, H);
                  var6x.translation(-var14 * var21.x, -var21.y, -var21.z)
                     .scale(var14, 1.0F, 1.0F)
                     .mul(sized(var17, var7x.r, var18, new Matrix4f()))
                     .scale(var14, 1.0F, 1.0F);
               }
            );
         }

         KnifeModels.Model var24 = var16.geometry();
         class_2960 var25 = KnifeCache.atlas(var16.camo());
         float var26 = var15.c;
         float var27 = var15.S;
         int var28 = class_4608.field_21444;
         boolean var29 = var2.method_7958();
         boolean var30 = var23 == null;
         Object var31 = null;
         b(var24, var25, var26, var27, var5, var11, var6, var7, var28, var29, (Quaternionfc)var31, var30);
         var5.method_22909();
         if (!var0.method_5767()) {
            HandSweepPipeline.sweeps(false);
            class_1007 var36 = var8.method_74405(var0);
            class_2960 var37 = var0.method_52814().comp_1626().comp_3627();
            var5.method_22903();
            var5.method_22905(var14, 1.0F, 1.0F);
            var5.method_34425(KnifePlacement.arm(var22, var15.N, var19, new Matrix4f()));
            var5.method_22905(var14, 1.0F, 1.0F);
            if (var14 == 1) {
               var36.method_4220(var5, var6, var7, var37, var0.method_74091(class_1664.field_7570));
            } else {
               var36.method_4221(var5, var6, var7, var37, var0.method_74091(class_1664.field_7568));
            }

            var5.method_22909();
            if (var15.t > 0.001F) {
               var5.method_22903();
               var5.method_22905(var14, 1.0F, 1.0F);
               var5.method_34425(KnifePlacement.otherArm(var15.G, var19, new Matrix4f()));
               var5.method_22905(var14, 1.0F, 1.0F);
               if (var14 == 1) {
                  var36.method_4221(var5, var6, var7, var37, var0.method_74091(class_1664.field_7568));
               } else {
                  var36.method_4220(var5, var6, var7, var37, var0.method_74091(class_1664.field_7570));
               }

               var5.method_22909();
            }

            HandSweepPipeline.sweeps(true);
         }

         var5.method_22909();
         KnifeSwingState.metric("knife_drawn");
         KnifeSwingState.metric("drawn");
         return true;
      } else {
         return false;
      }
   }

   static Matrix4f sized(int var0, Matrix4f var1, float var2, Matrix4f var3) {
      var3.set(var1);
      if (Math.abs(var2 - 1.0F) > 1.0E-4F) {
         Vector3f var4 = KnifePlacement.grip(var0);
         var3.translate(var4).scale(var2).translate(-var4.x, -var4.y, -var4.z);
      }

      return var3;
   }

   private static void B(class_4587 var0, int var1, float var2) {
      float var3 = class_3532.method_15355(var2);
      var0.method_46416(
         var1 * -0.4F * class_3532.method_15374(var3 * (float) Math.PI),
         0.2F * class_3532.method_15374(var3 * (float) (Math.PI * 2)),
         -0.2F * class_3532.method_15374(var2 * (float) Math.PI)
      );
      float var4 = class_3532.method_15374(var2 * var2 * (float) Math.PI);
      float var5 = class_3532.method_15374(var3 * (float) Math.PI);
      var0.method_22907(class_7833.field_40716.rotationDegrees(var1 * (45.0F - 20.0F * var4)));
      var0.method_22907(class_7833.field_40718.rotationDegrees(var1 * -20.0F * var5));
      var0.method_22907(class_7833.field_40714.rotationDegrees(-80.0F * var5));
      var0.method_22907(class_7833.field_40716.rotationDegrees(var1 * -45.0F));
   }

   static void submit(
      KnifeModels.Model var0, class_2960 var1, float var2, float var3, class_4587 var4, class_11659 var5, int var6, int var7, boolean var8, Quaternionfc var9
   ) {
      long var10 = 25958871293785L;
      long var12 = var10 ^ 130181494673461L;
      b(var0, var1, var2, var3, var4, var12, var5, var6, var7, var8, var9, true);
   }

   private static void b(
      KnifeModels.Model var0,
      class_2960 var1,
      float var2,
      float var3,
      class_4587 var4,
      long var5,
      class_11659 var7,
      int var8,
      int var9,
      boolean var10,
      Quaternionfc var11,
      boolean var12
   ) {
      long var13 = 29905347914235L;
      KnifeSwingState.ensureSourceClassInitialized();
      Vector3f var15 = var11 == null ? N : var11.transform(new Vector3f(N));
      long var16 = 107727325122411L;
      int var19 = var16 < 0L ? -1 : 1;
      g(var13, var0.blade(), var1, var4, var7, var8, var9, var10, var15);
      long var19x = 107727325122411L;
      int var22 = var19x < 0L ? -1 : 1;
      if (!var12) {
         HandSweepPipeline.sweeps(false);
      }

      long var22x = 107727325122411L;
      KnifeBuilder var24x = var0.handleA();
      long var25 = 107727325122412L;
      int var25x = var25 < 0L ? -1 : 1;
      if (var24x != null) {
         var4.method_22903();
         var4.method_34425(KnifeModels.hinge(new Matrix4f(), -1, var2));
         g(var13, var0.handleA(), var1, var4, var7, var8, var9, var10, var15);
         var4.method_22909();
      }

      if (var0.handleB() != null) {
         var4.method_22903();
         var4.method_34425(KnifeModels.hinge(new Matrix4f(), 1, var3));
         g(var13, var0.handleB(), var1, var4, var7, var8, var9, var10, var15);
         var4.method_22909();
      }

      if (!var12) {
         HandSweepPipeline.sweeps(true);
      }
   }

   private static void g(long var0, KnifeBuilder var2, class_2960 var3, class_4587 var4, class_11659 var5, int var6, int var7, boolean var8, Vector3f var9) {
      KnifeSwingState.ensureSourceClassInitialized();
      var5.method_73483(var4, class_12249.method_75982(var3), new KnifeMaterialMesh.MaterialMesh(var2, var6, var7, var9));
      if (var8) {
         var5.method_73483(var4, class_12249.method_75997(), (var2xx, var3x) -> {
            long var4x = 106373868798318L;
            byte var6x = 0;
            short var7x = 24767;
            int var8x = (int)(var4x << 32 >>> 32);
            Z(var2, (char)var6x, var2xx, var3x, var6, var7x, var8x);
         });
      }
   }

   private static void Z(KnifeBuilder var0, char var1, class_4665 var2, class_4588 var3, int var4, short var5, int var6) {
      KnifeSwingState.ensureSourceClassInitialized();
      int[] var7 = var2.method_23761().determinant3x3() < 0.0F ? j : W;

      for (byte var8 = 0; var8 < var0.vertexCount(); var8 = (byte)(var8 + 3)) {
         for (int var12 : var7) {
            KnifeBuilder.Data var13 = var0.B[var8 + var12];
            var3.method_56824(var2, var13.x(), var13.y(), var13.z())
               .method_39415(-1)
               .method_22913((var13.x() + var13.z() * 0.5F) * 4.0F, var13.y() * 4.0F)
               .method_22922(class_4608.field_21444)
               .method_60803(var4)
               .method_60831(var2, var13.nx(), var13.ny(), var13.nz());
         }
      }
   }

   private record MaterialMesh(KnifeBuilder U, int H, int h, Vector3f b) implements RenderPassHolder {
      public void render(class_4665 var1, class_4588 var2) {
         Vector3f var3 = new Vector3f();
         Vector3f var4 = new Vector3f();
         int[] var5 = var1.method_23761().determinant3x3() < 0.0F ? KnifeMaterialMesh.j : KnifeMaterialMesh.W;

         for (byte var6 = 0; var6 < this.U.vertexCount(); var6 = (byte)(var6 + 3)) {
            for (int var10 : var5) {
               KnifeBuilder.Data var11 = this.U.B[var6 + var10];
               var1.method_56821(var4.set(var11.nx(), var11.ny(), var11.nz()), var3);
               float var12 = (float)Math.pow(Math.max(0.0F, Math.abs(var3.dot(this.b))), 36.0) * var11.metal() * 0.42F;
               int var13 = var11.color();
               int var14 = Math.min(255, (var13 >> 16 & 0xFF) + (int)(var12 * 225.0F));
               int var15 = Math.min(255, (var13 >> 8 & 0xFF) + (int)(var12 * 238.0F));
               int var16 = Math.min(255, (var13 & 0xFF) + (int)(var12 * 255.0F));
               var2.method_56824(var1, var11.x(), var11.y(), var11.z())
                  .method_1336(var14, var15, var16, 255)
                  .method_22913(var11.u(), var11.v())
                  .method_22922(this.h)
                  .method_60803(this.H)
                  .method_60831(var1, var11.nx(), var11.ny(), var11.nz());
            }
         }
      }

      public KnifeBuilder mesh() {
         return this.U;
      }

      public int light() {
         return this.H;
      }

      public int overlay() {
         return this.h;
      }

      public Vector3f sheen() {
         return this.b;
      }

      public KnifeBuilder knifeMaterialMeshState6() {
         return this.U;
      }

      public int integerState54() {
         return this.H;
      }

      public int integerState30() {
         return this.h;
      }

      public Vector3f vector3fState10() {
         return this.b;
      }
   }
}
