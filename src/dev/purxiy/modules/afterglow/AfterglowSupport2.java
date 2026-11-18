// Sourced By DevFFNightmare
package dev.purxiy.modules.afterglow;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.purxiy.loader.fabric.mixin.ModelPartAccessor;
import dev.purxiy.loader.fabric.mixin.RenderSetupAccessor;
import dev.purxiy.loader.fabric.mixin.RenderTypeAccessor;
import dev.purxiy.loader.fabric.mixin.TextureBindingAccessor;
import dev.purxiy.render.pipeline.SkyPipelines;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_10017;
import net.minecraft.class_12247;
import net.minecraft.class_12250;
import net.minecraft.class_1309;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_3879;
import net.minecraft.class_4587;
import net.minecraft.class_5819;
import net.minecraft.class_630;
import net.minecraft.class_10017.class_11680;
import net.minecraft.class_12247.class_12248;
import net.minecraft.class_630.class_593;
import net.minecraft.class_630.class_618;
import net.minecraft.class_630.class_628;
import org.joml.Matrix4f;
import org.joml.Vector3f;

final class AfterglowSupport2 {
   private static final class_1921 U = net.minecraft.PurxiyPipelineAccess.layer("purxiy_afterglow_hidden", class_12247.method_75927(SkyPipelines.AFTERGLOW).method_75938());
   static final int z = 384;
   private static final int l = 16;
   final class_1309 n;
   final int S;
   final AfterglowCatalog k;
   float H;
   float D;
   float v;
   float a;
   float b;
   float C;
   
   class_10017 V;
   boolean W;
   boolean c;
   float e;
   float i = 1.8F;
   float x;
   float h;
   double O;
   double I;
   double L;
   float o;
   float d;
   float Q;
   final float[] q = new float[6144];
   int K;
   float p;
   private long t = -1L;
   private float y;
   private float J;
   private final float[] m = new float[3];
   private final float[] s = new float[12];
   private final Vector3f A = new Vector3f();
   
   float[] Y;
   int F;
   int j;
   private final Map<class_1921, class_1921> M = new IdentityHashMap<>();
   private final class_12250 u = new class_12250("purxiy_afterglow", this::M);


   private static final long[] r = new long[]{
      6238804089697355577L,
      711001644989401771L,
      -6638551826421345677L,
      -4593736360579637948L,
      6811792563418319532L,
      6837801382036519349L,
      2972706746668243013L,
      -107056189173114263L,
      4025882379012126989L,
      -7735962354610693277L,
      -281026281671357484L,
      -8245713514202518128L
   };
   private static final Integer[] w = new Integer[12];

   AfterglowSupport2(class_1309 var1, int var2, AfterglowCatalog var3) {
      this.n = var1;
      this.S = var2;
      this.k = var3;
   }

   boolean finished() {
      return this.v >= 1.0F;
   }

   private Matrix4f M() {
      return new Matrix4f(
         this.o,
         this.d,
         this.Q,
         this.a,
         this.i,
         this.x,
         this.h,
         this.S,
         AfterglowRegistry.packedFirst(),
         AfterglowRegistry.packedSecond(),
         AfterglowRegistry.packedAccent(),
         AfterglowRegistry.glow(),
         this.b,
         this.C,
         this.e,
         0.0F
      );
   }

   
   class_1921 swap(class_1921 var1) {
      long var2 = 136210033866738L;
      long var4 = var2 ^ 70336678960773L;
      class_1921 var6 = this.M.get(var1);
      if (var6 == null) {
         var6 = this.h(var4, var1);
         this.M.put(var1, var6 == null ? U : var6);
      }

      return var6 == U ? null : var6;
   }

   
   private class_1921 h(long var1, class_1921 var3) {
      AfterglowRenderer.ensureSourceClassInitialized();
      if (var3.method_23031() != SkyPipelines.AFTERGLOW.getVertexFormat()) {
         return null;
      } else {
         RenderSetupAccessor var4 = (RenderSetupAccessor)(Object)((RenderTypeAccessor)var3).purxiy$setup();
         Object var5 = var4.purxiy$textures().get("Sampler0");
         if (var5 == null) {
            return null;
         } else {
            class_2960 var6 = ((TextureBindingAccessor)var5).purxiy$location();
            RenderPipeline var7 = var3.method_73243();
            RenderPipeline var8;
            if (var7.getBlendFunction().isEmpty()) {
               long var9 = 74875614882167L;
               if (74875614882167L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var16 = var9 < 0L ? -1 : 1;
               }

               var8 = SkyPipelines.AFTERGLOW;
            } else {
               long var14 = 74875614882166L;
               if (74875614882166L == 0L) {
                  boolean var17 = false;
               } else {
                  int var18 = var14 < 0L ? -1 : 1;
               }

               if (!var7.getBlendFunction().get().equals(BlendFunction.TRANSLUCENT)) {
                  return null;
               }

               var8 = var7.getShaderDefines().comp_3104().contains("EMISSIVE") ? SkyPipelines.AFTERGLOW_EMISSIVE : SkyPipelines.AFTERGLOW_TRANSLUCENT;
            }

            class_12248 var15 = class_12247.method_75927(var8)
               .method_75934("Sampler0", var6)
               .method_75933(this.u)
               .method_75930(var4.purxiy$layeringTransform());
            if (var8 != SkyPipelines.AFTERGLOW_EMISSIVE) {
               var15.method_75928();
            }

            boolean var10 = var4.purxiy$sortOnUpload();
            long var11 = 74875614882166L;
            if (74875614882166L == 0L) {
               boolean var19 = false;
            } else {
               int var20 = var11 < 0L ? -1 : 1;
            }

            if (var10) {
               var15.method_75937();
            }

            return net.minecraft.PurxiyPipelineAccess.layer("purxiy_afterglow", var15.method_75938());
         }
      }
   }

   List<class_11680> shadow(List<class_11680> var1) {
      float var2 = 1.0F - AfterglowRegistry.smoothstep(0.1F, 0.75F, this.v);
      return var2 >= 1.0F
         ? var1
         : var1.stream()
            .map(var1x -> new class_11680(var1x.comp_4546(), var1x.comp_4547(), var1x.comp_4548(), var1x.comp_4549(), var1x.comp_4550() * var2))
            .toList();
   }

   <S> void capture(class_3879<? super S> var1, S var2, class_4587 var3, long var4) {
      long var6 = 100903701077099L;
      long var8 = var6 ^ 125895531537260L;
      int var10 = (int)((var6 ^ 125895531537260L) >>> 48);
      int var11 = (int)((var6 ^ 125895531537260L) << 16 >>> 48);
      int var12 = (int)(var8 << 32 >>> 32);
      if (this.t != var4) {
         this.t = var4;
         this.K = 0;
         this.p = 0.0F;
         this.y = Float.POSITIVE_INFINITY;
         this.J = Float.NEGATIVE_INFINITY;
      }

      if (this.K < 384) {
         var1.method_2819(var2);
         this.R((char)var10, var1.method_63512(), var3, (char)var11, var12);
      }
   }

   private void R(char var1, class_630 var2, class_4587 var3, char var4, int var5) {
      long var6 = (long)var1 << 48 | (long)var4 << 48 >>> 16 | (long)var5 << 32 >>> 32;
      long var8 = var6 ^ 0L;
      int var10 = (int)((var6 ^ 0L) >>> 48);
      int var11 = (int)((var6 ^ 0L) << 16 >>> 48);
      int var12 = (int)(var8 << 32 >>> 32);
      int var13 = (int)((var6 ^ 54122002325440L) >>> 32);
      long var14 = (var6 ^ 54122002325440L) << 32 >>> 32;
      AfterglowRenderer.ensureSourceClassInitialized();
      ModelPartAccessor var16 = (ModelPartAccessor)(Object)(var2);
      List var17 = var16.purxiy$cubes();
      Map var18 = var16.purxiy$children();
      if (var2.field_3665 && (!var17.isEmpty() || !var18.isEmpty())) {
         var3.method_22903();
         var2.method_22703(var3);
         if (!var2.field_38456) {
            Matrix4f var19 = var3.method_23760().method_23761();

            label74:
            for (Object var21 : var17) {
               label72:
               while (true) {
                  class_628 var22 = (class_628)var21;
                  class_593[] var23 = var22.field_3649;
                  int var24 = var23.length;

                  while (true) {
                     int var25 = 0;

                     while (var25 < var24) {
                        class_593 var26 = var23[var25];
                        var21 = var26.comp_3184();
                        if (var1 < 0) {
                           continue label72;
                        }

                        Object var27 = var21;
                        int var28 = 0;

                        while (true) {
                           if (var28 < 4) {
                              var19.transformPosition(
                                 ((class_618[])var27)[var28].method_74250(),
                                 ((class_618[])var27)[var28].method_74251(),
                                 ((class_618[])var27)[var28].method_74252(),
                                 this.A
                              );
                           } else if (var5 >= 0) {
                              this.b(var13, var14);
                              var25++;
                              break;
                           }

                           if (var4 <= 0) {
                              break;
                           }

                           this.s[var28 * 3] = this.A.x - this.o;
                           this.s[var28 * 3 + 1] = this.A.y - this.d;
                           this.s[var28 * 3 + 2] = this.A.z - this.Q;
                           var28++;
                        }
                     }

                     if (var5 >= 0) {
                        continue label74;
                     }
                  }
               }
            }
         }

         for (class_630 var30 : (Iterable<class_630>)(Object)(var18.values())) {
            if (var1 >= 0) {
               this.R((char)var10, var30, var3, (char)var11, var12);
            }
         }

         if (var5 > 0) {
            var3.method_22909();
         }
      }
   }

   private void b(int var1, long var2) {
      long var4 = (long)var1 << 32 | var2 << 32 >>> 32;
      AfterglowRenderer.ensureSourceClassInitialized();
      if (this.K < a(2970, 4075526017403746852L ^ var4)) {
         float[] var6 = this.s;
         float var7 = var6[3] - var6[0];
         float var8 = var6[4] - var6[1];
         float var9 = var6[5] - var6[2];
         float var10 = var6[a(776, 6417240268708672189L ^ var4)] - var6[0];
         float var11 = var6[a(19220, 8322652768456194724L ^ var4)] - var6[1];
         float var12 = var6[a(15584, 2584196139415162204L ^ var4)] - var6[2];
         float var13 = var8 * var12 - var9 * var11;
         float var14 = var9 * var10 - var7 * var12;
         float var15 = var7 * var11 - var8 * var10;
         float var16 = (float)Math.sqrt(var13 * var13 + var14 * var14 + var15 * var15);
         if (!(var16 < 1.0E-5F)) {
            int var17 = this.K++ * a(19495, 5601776684493585811L ^ var4);
            System.arraycopy(var6, 0, this.q, var17, a(6818, 8879817051380312863L ^ var4));
            this.q[var17 + a(23508, 7181026034004175463L ^ var4)] = var13 / var16;
            this.q[var17 + a(10445, 6971033306131519871L ^ var4)] = var14 / var16;
            this.q[var17 + a(22087, 1683399476841981936L ^ var4)] = var15 / var16;
            this.q[var17 + a(23509, 8318838785103833699L ^ var4)] = var16;
            this.p += var16;
            byte var18 = 0;

            while (true) {
               AfterglowSupport2 var19;
               if (var18 < a(23508, 7181026034004175463L ^ var4)) {
                  AfterglowSupport3.toBody(var6[var18], var6[var18 + 1], var6[var18 + 2], this.x, this.h, this.m);
                  if (var1 < 0) {
                     break;
                  }

                  this.y = Math.min(this.y, this.m[1]);
                  var19 = this;
               } else {
                  var19 = this;
                  if (var1 >= 0) {
                     this.e = this.y;
                     this.i = Math.max(this.J - this.y, 0.2F);
                     break;
                  }
               }

               var19.J = Math.max(this.J, this.m[1]);
               var18 = (byte)(var18 + 3);
            }
         }
      }
   }

   void plan(int var1, class_5819 var2) {
      long var3 = 55678907687347L;
      long var5 = var3 ^ 27717434917119L;
      float[] var7 = new float[var1 * 4];
      long[] var8 = new long[var1];
      float var9 = 0.12F;

      for (int var10 = 0; var10 < var1; var10++) {
         int var11 = this.h(var2.method_43057() * this.p, var5);
         float var12 = var2.method_43057();
         float var13 = var2.method_43057();
         this.point(var11, var12, var13, this.A);
         AfterglowSupport3.toBody(this.A.x, this.A.y, this.A.z, this.x, this.h, this.m);
         float var14 = AfterglowSupport3.field(this.m[0], this.m[1], this.m[2], this.e, this.i, this.S) * (1.0F - var9) + var2.method_43057() * var9;
         var7[var10 * 4] = var11;
         var7[var10 * 4 + 1] = var12;
         var7[var10 * 4 + 2] = var13;
         var7[var10 * 4 + 3] = var14;
         var8[var10] = (long)Float.floatToRawIntBits(var14) << 32 | var10;
      }

      Arrays.sort(var8);
      this.Y = new float[var1 * 4];

      for (int var15 = 0; var15 < var1; var15++) {
         System.arraycopy(var7, (int)var8[var15] * 4, this.Y, var15 * 4, 4);
      }

      this.F = var1;
   }

   private int h(float var1, long var2) {
      AfterglowRenderer.ensureSourceClassInitialized();

      for (int var4 = 0; var4 < this.K; var4++) {
         var1 -= this.quadArea(var4);
         long var5 = 47926304792908L;
         if (47926304792908L == 0L) {
            boolean var10000 = false;
         } else {
            int var9 = var5 < 0L ? -1 : 1;
         }

         if (var1 <= 0.0F) {
            return var4;
         }
      }

      long var8 = 47926304792907L;
      if (47926304792907L == 0L) {
         boolean var10 = false;
      } else {
         int var11 = var8 < 0L ? -1 : 1;
      }

      return this.K - 1;
   }

   void point(int var1, float var2, float var3, Vector3f var4) {
      float var5 = (1.0F - var2) * (1.0F - var3);
      float var6 = var2 * (1.0F - var3);
      float var7 = var2 * var3;
      float var8 = (1.0F - var2) * var3;
      int var9 = var1 * 16;
      float[] var10 = this.q;
      var4.set(
         var10[var9] * var5 + var10[var9 + 3] * var6 + var10[var9 + 6] * var7 + var10[var9 + 9] * var8,
         var10[var9 + 1] * var5 + var10[var9 + 4] * var6 + var10[var9 + 7] * var7 + var10[var9 + 10] * var8,
         var10[var9 + 2] * var5 + var10[var9 + 5] * var6 + var10[var9 + 8] * var7 + var10[var9 + 11] * var8
      );
   }

   float normal(int var1, int var2) {
      return this.q[var1 * 16 + 12 + var2];
   }

   float quadArea(int var1) {
      return this.q[var1 * 16 + 15];
   }

private static int a(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 4464;
      if (w[var3] == null) {
         w[var3] = (int)(r[var3] ^ var1);
      }

      return w[var3];
   }

}
