package dev.purxiy.modules.knife;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.systems.RenderSystem.class_5590;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.assets.IrisCompat;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.HandSweepPipeline;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.ui.Theme;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1297;
import net.minecraft.class_2394;
import net.minecraft.class_2398;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_287;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_746;
import net.minecraft.class_9799;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryUtil;

public final class KnifeWieldPipeline {
   static final float w = 0.2F;
   static final float K = 0.3F;
   private static final float c = 0.36F;
   private static final float z = 1.06F;
   private static final float P = 0.8F;
   private static final float L = 0.36F;
   private static final float h = 0.055F;
   private static final float a = 0.62F;
   private static final int t = 2048;
   private static float p = Float.NaN;
   private static final ArrayDeque<KnifeWieldPipeline.WieldHit> M = new ArrayDeque<>();
   private static final Matrix4f D = new Matrix4f();
   private static final Matrix4f k = new Matrix4f();
   private static class_243 C = class_243.field_1353;
   private static boolean S;
   private static boolean N;
   private static float Z;
   private static long l = 0L;
   private static GpuBuffer i;
   private static GpuBuffer G;
   private static ByteBuffer F;
   private static final class_9799 V = new class_9799(65536);
   private static final int q = 96;
   private static String d;
   private static final String[] e = new String[12];
   private static final long[] f = new long[]{
      -1093721513628016635L,
      -3261373329747442190L,
      -7900407485553780270L,
      -515109914231900153L,
      -5733114913206637358L,
      -8933944743391710420L,
      8727268930642354209L,
      -1483533846276067121L,
      4050341173107182776L,
      -6118925510599517121L,
      2375796535300838678L,
      -1825231459554378730L,
      2042046056080527691L,
      8977924339529443586L,
      8307905414470898918L,
      -7972482774530416802L,
      -8450291287416559780L,
      2008877790386843295L
   };
   private static final Integer[] g = new Integer[18];
   private static final long[] j = new long[]{1296412967159179214L, 8120490478536106115L, 5115137419690641650L};
   private static final Long[] m = new Long[3];

   private KnifeWieldPipeline() {
   }

   public static float glint(KnifeApi var0) {
      if (Float.isNaN(p)) {
         return -1.0F;
      } else {
         float var1 = (KnifeAttackShell.clock() - p) / 0.62F;
         if (var1 >= 1.0F) {
            return -1.0F;
         } else {
            float var2 = var1 * var1 * (3.0F - 2.0F * var1);
            return -0.15F + 1.3F * var2;
         }
      }
   }

   static void glintQuietly() {
      p = KnifeAttackShell.clock();
   }

   public static void event(KnifeApi var0, String var1) {
      if (var1.equals("glint")) {
         p = KnifeAttackShell.clock();
         KnifeFactory.play(var0, "glint", 0.7F, 1.0F, false);
      }
   }

   public static void hit(KnifeApi var0, class_1297 var1, class_243 var2, boolean var3) {
      long var4 = 121311392733340L;
      int var6 = (int)((var4 ^ 131301458004907L) >>> 56);
      long var7 = (var4 ^ 131301458004907L) << 8 >>> 8;
      long var9 = var4 ^ 51408958877226L;
      if (var0.hitFx()) {
         class_243 var11 = var2;
         if (var2 == null && var1 != null) {
            var11 = var1.method_5829().method_1005();
         }

         if (var11 != null) {
            float var12 = k(var9);
            float var13 = var1 == null ? 1.1F : Math.clamp(var1.method_17682() * 0.85F, 0.8F, 2.4F);
            M.add(new KnifeWieldPipeline.WieldHit(var11, var12, KnifeAttackShell.clock(), var3, var0.light(), var0.deep(), var13));

            while (M.size() > 8) {
               M.removeFirst();
            }

            N((byte)var6, var0, var11, var7, var12, var3);
         }
      }
   }

   private static float k(long var0) {
      KnifeSwingState.ensureSourceClassInitialized();
      if (KnifeAttackShell.pathCount() < 6) {
         return 0.6F;
      } else {
         Vector3f var2 = new Vector3f();
         Vector3f var3 = new Vector3f();
         Vector3f var4 = new Vector3f();
         KnifeAttackShell.moment(0, var2, var3);
         KnifeAttackShell.moment(5, var2, var4);
         float var5 = var3.x / -Math.min(var3.z, -0.05F);
         float var6 = var3.y / -Math.min(var3.z, -0.05F);
         float var7 = var4.x / -Math.min(var4.z, -0.05F);
         float var8 = var4.y / -Math.min(var4.z, -0.05F);
         float var9 = var5 - var7;
         float var10 = var6 - var8;
         long var11 = 71006928762549L;
         int var14 = var11 < 0L ? -1 : 1;
         float var14x = var9 * var9;
         long var15 = 71006928762549L;
         int var16 = var15 < 0L ? -1 : 1;
         if (var14x + var10 * var10 < 1.0E-6F) {
            KnifeAttackShell.moment(0, var2, var3);
            return (float)Math.atan2(var3.y - var2.y, var3.x - var2.x) + (float) (Math.PI / 2);
         } else {
            return (float)Math.atan2(var10, var9 * KnifeRenderer.drawnSide());
         }
      }
   }

   private static void N(byte var0, KnifeApi var1, class_243 var2, long var3, float var5, boolean var6) {
      KnifeSwingState.ensureSourceClassInitialized();
      class_310 var7 = class_310.method_1551();
      if (var7.field_1687 != null && S) {
         class_4184 var8 = var7.field_1773.method_19418();
         Vector3f var9 = var8.method_23767().transform(new Vector3f(1.0F, 0.0F, 0.0F));
         Vector3f var10 = var8.method_23767().transform(new Vector3f(0.0F, 1.0F, 0.0F));
         Vector3f var11 = new Vector3f(var9).mul((float)Math.cos(var5)).add(new Vector3f(var10).mul((float)Math.sin(var5)));
         ThreadLocalRandom var12 = ThreadLocalRandom.current();
         int var13 = var6 ? 16 : 10;

         for (int var14 = 0; var14 < var13; var14++) {
            float var15 = (float)var12.nextGaussian() * 0.5F;
            Vector3f var16 = new Vector3f(var11)
               .mul(0.09F + var12.nextFloat() * 0.12F)
               .add(new Vector3f(var10).mul(var15 * 0.08F))
               .add(new Vector3f(var9).mul((float)var12.nextGaussian() * 0.04F));
            long var17 = 27754124530486L;
            int var19 = var17 < 0L ? -1 : 1;
            if (var12.nextBoolean()) {
               var16.negate().mul(0.6F);
            }

            KnifeKeybind.spark(var7.field_1687, var2, var16, var1.light(), var6);
         }

         KnifeWieldPipeline.WieldStage var20 = var1.ambient();
         if (var20 != KnifeWieldPipeline.WieldStage.M && var1.ambientFx()) {
            for (int var21 = 0; var21 < (var6 ? 9 : 5); var21++) {
               Vector3f var22 = new Vector3f(var11)
                  .mul((var12.nextFloat() - 0.3F) * 0.08F)
                  .add((float)var12.nextGaussian() * 0.03F, 0.02F + var12.nextFloat() * 0.04F, (float)var12.nextGaussian() * 0.03F);
               KnifeKeybind.shed(var7.field_1687, var20, var2, var22, var1.light(), var1.deep());
            }
         }
      }
   }

   public static boolean replacesParticle(class_2394 var0, double var1, double var3, double var5) {
      KnifeApi var7 = KnifeAttackShell.item();
      if (var7 != null && var7.slashFx() && var0.method_10295() == class_2398.field_11227) {
         class_746 var8 = class_310.method_1551().field_1724;
         return var8 != null && var8.method_5649(var1, var3, var5) < 36.0;
      } else {
         return false;
      }
   }

   public static void level(class_4184 var0, Matrix4fc var1, Matrix4fc var2) {
      D.set(var1);
      k.set(var2);
      C = var0.method_71156();
      S = true;
   }

   public static void frame() {
      long var0 = 70463598036159L;
      long var2 = var0 ^ 85373275455002L;
      int var4 = (int)((var0 ^ 85373275455002L) >>> 32);
      int var5 = (int)((var0 ^ 85373275455002L) << 32 >>> 48);
      int var6 = (int)(var2 << 48 >>> 48);
      N = false;
      if (IrisCompat.inUse() && (e(var4, var5, (short)var6) || !M.isEmpty())) {
         SkyPipelineRouter.wantHandMask();
         N = true;
      }
   }

   private static boolean e(int var0, int var1, short var2) {
      KnifeSwingState.ensureSourceClassInitialized();
      if (KnifeAttackShell.item() != null && KnifeAttackShell.pathCount() != 0) {
         float var3 = KnifeAttackShell.clock();
         Vector3f var4 = new Vector3f();
         Vector3f var5 = new Vector3f();

         for (int var6 = 0; var6 < KnifeAttackShell.pathCount(); var6++) {
            float var7 = var3 - KnifeAttackShell.moment(var6, var4, var5);
            float var8;
            int var9 = (var8 = var7 - 0.3F) == 0.0F ? 0 : (var8 < 0.0F ? -1 : 1);
            if (var9 > 0) {
               return false;
            }

            if (KnifeAttackShell.arcOf(var6) != 0) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public static void composite() {
      long var0 = 9462897091673L;
      long var2 = (var0 ^ 16326936352650L) >>> 16;
      int var4 = (int)((var0 ^ 16326936352650L) << 48 >>> 48);
      long var5 = var0 ^ 26090573710309L;
      long var7 = var0 ^ 5672962105084L;
      int var9 = (int)((var0 ^ 5672962105084L) >>> 32);
      int var10 = (int)((var0 ^ 5672962105084L) << 32 >>> 48);
      int var11 = (int)(var7 << 48 >>> 48);

      try {
         KnifeApi var12 = KnifeAttackShell.item();
         Matrix4f var16 = KnifeRenderer.drawnIn();
         if (var12 != null && var16 != null) {
            C(var12, var16, var2, (char)var4);
         }

         float var14 = KnifeAttackShell.clock();
         if (var12 == null) {
            M.clear();
         }

         M.removeIf(var1 -> {
            KnifeSwingState.ensureSourceClassInitialized();
            return var14 - var1.born() > 0.36F;
         });
         if ((var12 == null || var16 == null || !e(var9, var10, (short)var11)) && M.isEmpty()) {
            return;
         }

         f(var5, var12, var16);
      } catch (Throwable var151) {
         KnifeSwingState.metric("draw_failures");
         String var13 = var151.toString();
         if (!var13.equals(d)) {
            d = var13;
            ClientServices.LOGGER.error("SourceByDevFFnightmare DeObfDeCompByGuideX wield light failed", var151);
         }
      }
   }

   private static void f(long var0, KnifeApi var2, Matrix4f var3) {
      long var4 = 60435682463237L;
      long var6 = 11635100425452L;
      long var8 = 73966287458970L;
      class_310 var10 = class_310.method_1551();
      KnifeSwingState.ensureSourceClassInitialized();
      class_276 var11 = var10.method_1522();
      GpuTextureView var12 = var11.method_71639();
      GpuTextureView var13 = var11.method_71640();
      GpuDevice var14 = RenderSystem.getDevice();
      if (var12 != null && var13 != null && var14.precompilePipeline(KnifeWieldPipelines.W).isValid() && var14.precompilePipeline(KnifeWieldPipelines.U).isValid()) {
         boolean var15 = IrisCompat.inUse();
         GpuTextureView var16 = null;
         if (var15) {
            var16 = N ? SkyPipelineRouter.handMask() : null;
            if (var16 == null) {
               return;
            }
         }

         class_287 var17 = new class_287(V, class_5596.field_27382, class_290.field_1575);
         int var18 = 0;
         if (var2 != null && var3 != null) {
            Matrix4f var19 = new Matrix4f(KnifeRenderer.modelView()).mul(var3).scale(KnifeRenderer.drawnSide(), 1.0F, 1.0F);
            var18 += f(var17, var19, var2.light(), var4);
         }

         float var40 = (float)var11.field_1482 / var11.field_1481;
         int var20 = var18 + q(var17, var40, var8);
         if (var20 == 0) {
            class_9801 var21 = var17.method_60794();
            if (var21 != null) {
               var21.close();
            }
         } else {
            CommandEncoder var41 = var14.createCommandEncoder();
            i(var6, var14);
            int var22 = class_290.field_1575.getVertexSize();
            class_9801 var23 = var17.method_60800();

            try {
               long var24;
               label167: {
                  var24 = var20 * 4L * var22;
                  if (i != null) {
                     long var26 = 34230620809147L;
                     int var47 = var26 < 0L ? -1 : 1;
                     long var29;
                     int var31 = (var29 = i.size() - var24) == 0L ? 0 : (var29 < 0L ? -1 : 1);
                     long var32 = 34230620809147L;
                     int var34 = var32 < 0L ? -1 : 1;
                     if (var31 >= 0) {
                        break label167;
                     }
                  }

                  if (i != null) {
                     i.close();
                  }

                  long var44;
                  for (var44 = 65536L; var44 < var24; var44 <<= 1) {
                     long var28 = 34230620809148L;
                     int var50 = var28 < 0L ? -1 : 1;
                  }

                  long var46 = 34230620809148L;
                  int var51 = var46 < 0L ? -1 : 1;
                  i = var14.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX wield light vertices"), 40, var44);
               }

               var41.writeToBuffer(i.slice(0L, var24), var23.method_60818());
            } catch (Throwable var39) {
               if (var23 != null) {
                  try {
                     var23.close();
                  } catch (Throwable var37) {
                     var39.addSuppressed(var37);
                  }
               }

               throw var39;
            }

            if (var23 != null) {
               var23.close();
            }

            int var42 = var2 != null ? var2.deep() : Theme.tint(-6521857);
            Matrix4f var43 = new Matrix4f().setPerspective((float)Math.toRadians(HandSweepPipeline.fov()), var40, 0.05F, 100.0F);
            var41.writeToBuffer(
               G.slice(),
               Std140Builder.intoBuffer(F.clear())
                  .putMat4f(var43)
                  .putVec4((var42 >> 16 & 0xFF) / 255.0F, (var42 >> 8 & 0xFF) / 255.0F, (var42 & 0xFF) / 255.0F, (float)(KnifeAttackShell.clock() % 3600.0))
                  .putVec4(var11.field_1482, var11.field_1481, 0.0F, 0.0F)
                  .get()
            );
            class_5590 var25 = RenderSystem.getSequentialBuffer(class_5596.field_27382);
            GpuBuffer var45 = var25.method_68274(var20 * 6);
            RenderPass var27 = var15
               ? var41.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX wield light", var12, OptionalInt.empty())
               : var41.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX wield light", var12, OptionalInt.empty(), var13, OptionalDouble.empty());

            try {
               var27.setPipeline(var15 ? KnifeWieldPipelines.U : KnifeWieldPipelines.W);
               long var47 = 34230620809147L;
               int var52 = var47 < 0L ? -1 : 1;
               long var48 = 34230620809148L;
               int var33 = var48 < 0L ? -1 : 1;
               var33 = 268556908;
               var27.setUniform("PurxiyWield", G);
               if (var15) {
                  var27.bindTexture("MaskSampler", var16, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
               }

               long var34 = 34230620809148L;
               int var35 = var34 < 0L ? -1 : 1;
               var27.setVertexBuffer(0, i);
               var27.setIndexBuffer(var45, var25.method_31924());
               var27.drawIndexed(0, 0, var20 * 6, 1);
               KnifeSwingState.metric("drawn");
            } catch (Throwable var38) {
               if (var27 != null) {
                  try {
                     var27.close();
                  } catch (Throwable var36) {
                     var38.addSuppressed(var36);
                  }
               }

               throw var38;
            }

            if (var27 != null) {
               var27.close();
            }
         }
      }
   }

   private static int f(class_287 var0, Matrix4f var1, int var2, long var3) {
      float var5 = KnifeAttackShell.clock();
      Vector3f var6 = new Vector3f();
      Vector3f var7 = new Vector3f();
      Vector3f var8 = new Vector3f();
      Vector3f var9 = new Vector3f();
      Vector3f var10 = new Vector3f();
      Vector3f var11 = new Vector3f();
      KnifeSwingState.ensureSourceClassInitialized();
      Vector3f var12 = new Vector3f();
      Vector3f var13 = new Vector3f();
      int var14 = 0;
      int var15 = KnifeAttackShell.pathCount();
      float var16 = KnifeAttackShell.moment(0, var6, var7);
      int var17 = KnifeAttackShell.arcOf(0);
      int var18 = var2 >> 16 & 0xFF;
      int var19 = var2 >> 8 & 0xFF;
      int var20 = var2 & 0xFF;

      for (int var21 = 1; var21 < var15; var21++) {
         long var22 = 60435682463237L;
         int var25 = var22 < 0L ? -1 : 1;
         long var25x = 60435682463236L;
         int var27 = var25x < 0L ? -1 : 1;
         if (var14 >= 2048) {
            break;
         }

         float var27x = KnifeAttackShell.moment(var21, var8, var9);
         int var28 = KnifeAttackShell.arcOf(var21);
         float var29;
         int var30 = (var29 = var5 - var16 - 0.3F) == 0.0F ? 0 : (var29 < 0.0F ? -1 : 1);
         long var31 = 60435682463236L;
         int var33 = var31 < 0L ? -1 : 1;
         if (var30 > 0) {
            break;
         }

         boolean var33x = var17 < 0;
         float var34 = var33x ? 0.3F : 0.2F;
         float var35 = (var5 - var16) / var34;
         float var36 = (var5 - var27x) / var34;
         long var37 = 60435682463236L;
         int var40 = var37 < 0L ? -1 : 1;
         long var40x = 60435682463236L;
         if (var17 != 0) {
            long var42x = 60435682463236L;
            if (var17 == var28) {
               long var44x = 60435682463236L;
               if (var35 <= 1.0F) {
                  float var46x = Math.min(1.0F, KnifeAttackShell.arcStrengthOf(var21) * 0.75F);
                  int var47 = Math.round(255.0F * var46x);
                  float var48 = var33x ? 0.8F : 0.36F;
                  H(var6, var7, var10, var11, var1, var48);
                  H(var8, var9, var12, var13, var1, var48);
                  var0.method_22912(var10.x, var10.y, var10.z).method_22913(var35, 0.0F).method_1336(var18, var19, var20, var47);
                  var0.method_22912(var11.x, var11.y, var11.z).method_22913(var35, 1.0F).method_1336(var18, var19, var20, var47);
                  var0.method_22912(var13.x, var13.y, var13.z).method_22913(Math.min(var36, 1.0F), 1.0F).method_1336(var18, var19, var20, var47);
                  var0.method_22912(var12.x, var12.y, var12.z).method_22913(Math.min(var36, 1.0F), 0.0F).method_1336(var18, var19, var20, var47);
                  var14++;
               }
            }
         }

         var6.set(var8);
         var7.set(var9);
         var16 = var27x;
         var17 = var28;
      }

      long var49 = 60435682463236L;
      int var23 = var49 < 0L ? -1 : 1;
      return var14;
   }

   private static void H(Vector3f var0, Vector3f var1, Vector3f var2, Vector3f var3, Matrix4f var4, float var5) {
      var2.set(var0).lerp(var1, var5);
      var3.set(var0).lerp(var1, 1.06F);
      var4.transformPosition(var2);
      var4.transformPosition(var3);
   }

   private static int q(class_287 var0, float var1, long var2) {
      long var4 = 99545494809620L;
      long var6 = 10661553527729L;
      long var8 = 16333123574376L;
      short var10 = 3802;
      char var11 = '\uda07';
      int var12 = (int)(var8 << 48 >>> 48);
      KnifeSwingState.ensureSourceClassInitialized();
      if (!M.isEmpty() && S) {
         float var13 = KnifeAttackShell.clock();
         float var14 = (float)Math.tan(Math.toRadians(HandSweepPipeline.fov()) * 0.5);
         int var15 = 0;

         for (KnifeWieldPipeline.WieldHit var17 : M) {
            float var18 = var13 - var17.born();
            Vector4f var19 = J(var17.at(), var1, var4);
            long var20 = 73966287458970L;
            int var37 = var20 < 0L ? -1 : 1;
            if (var19 == null) {
               long var23 = 73966287458970L;
               int var26 = var23 < 0L ? -1 : 1;
            } else {
               long var37x = 73966287458969L;
               int var26 = var37x < 0L ? -1 : 1;
               var26 = var15 + M(var0, var17, var19, var17.angle(), var18, var14, var1, 1.0F, var6);
               boolean var27 = var17.crit();
               long var28 = 73966287458970L;
               int var28x = var28 < 0L ? -1 : 1;
               if (var27) {
                  var26 += M(var0, var17, var19, var17.angle() + 1.25F, var18 - 0.03F, var14, var1, 0.8F, var6);
               }

               var15 = var26 + p(var0, var10, var17, var19, var18, var11, (char)var12, var14, var1);
            }
         }

         long var36 = 73966287458970L;
         int var30 = var36 < 0L ? -1 : 1;
         return var15;
      } else {
         return 0;
      }
   }

   private static int M(class_287 var0, KnifeWieldPipeline.WieldHit var1, Vector4f var2, float var3, float var4, float var5, float var6, float var7, long var8) {
      KnifeSwingState.ensureSourceClassInitialized();
      long var10 = 10661553527728L;
      int var13 = var10 < 0L ? -1 : 1;
      long var13x = 10661553527728L;
      int var15 = var13x < 0L ? -1 : 1;
      if (var4 < 0.0F) {
         return 0;
      } else {
         float var15x = var1.length();
         long var16 = 10661553527729L;
         int var19 = var16 < 0L ? -1 : 1;
         float var19x = var15x * 0.5F * var7 * (var1.crit() ? 1.2F : 1.0F);
         float var20 = 1.0F / k.m11();
         float var21 = var19x / Math.max(0.3F, var2.w) / var20;
         float var22 = Math.min(1.0F, var4 / 0.055F);
         float var23 = Math.max(0.0F, 1.0F - (var4 - 0.055F) / 0.305F);
         float var24 = var4 < 0.055F ? 1.0F : var23 * var23;
         float var25 = (float)Math.cos(var3);
         float var26 = (float)Math.sin(var3);
         byte var27 = 16;
         float var28 = var21 * 0.06F * (1.0F + 0.6F * Math.min(1.0F, var4 / 0.36F));
         float var29 = -2.4F;
         int var30 = var1.light() >> 16 & 0xFF;
         int var31 = var1.light() >> 8 & 0xFF;
         int var32 = var1.light() & 0xFF;
         int var33 = 0;
         float[] var34 = null;

         for (int var35 = 0; var35 <= var27; var35++) {
            float var36 = (float)var35 / var27;
            long var37 = 10661553527729L;
            int var37x = var37 < 0L ? -1 : 1;
            float var39x = var22 + 1.0E-4F;
            long var40 = 10661553527729L;
            int var40x = var40 < 0L ? -1 : 1;
            if (var36 > var39x) {
               break;
            }

            float var42x = (var36 - 0.5F) * 2.0F * var21;
            float var43 = (1.0F - (2.0F * var36 - 1.0F) * (2.0F * var36 - 1.0F)) * var21 * 0.12F;
            float var44 = (float)Math.sin(Math.PI * var36) * 0.85F + 0.15F;
            float var45 = var2.x + var25 * var42x - var26 * var43;
            float var46 = var2.y + var26 * var42x + var25 * var43;
            float var47 = -var26 * var28 * var44;
            float var48 = var25 * var28 * var44;
            float var49 = Math.clamp((var22 - var36) * 3.0F, 0.0F, 1.0F);
            int var50 = Math.round(255.0F * var24 * Math.min(1.0F, 0.25F + var49));
            float[] var51 = new float[]{var45, var46, var47, var48, var50};
            long var52 = 10661553527728L;
            int var52x = var52 < 0L ? -1 : 1;
            long var54x = 10661553527729L;
            if (var34 != null) {
               for (byte var56 = -1; var56 <= 1; var56 = (byte)(var56 + 2)) {
                  i(var0, var34, var51, var56, var29, var5, var6, var30, var31, var32);
                  long var57 = 10661553527729L;
                  int var57x = var57 < 0L ? -1 : 1;
                  var33++;
               }
            }

            long var60x = 10661553527728L;
            int var56 = var60x < 0L ? -1 : 1;
            var34 = var51;
         }

         return var33;
      }
   }

   private static int p(class_287 var0, int var1, KnifeWieldPipeline.WieldHit var2, Vector4f var3, float var4, char var5, char var6, float var7, float var8) {
      KnifeSwingState.ensureSourceClassInitialized();
      float var9 = 0.2F;
      if (var4 > var9) {
         return 0;
      } else {
         float var10 = 1.0F / k.m11();
         float var11 = 1.0F / Math.max(0.3F, var3.w) / var10;
         int var12 = var2.crit() ? 9 : 6;
         Random var13 = new Random(Float.floatToIntBits(var2.born()));
         int var14 = var2.light() >> 16 & 0xFF;
         int var15 = var2.light() >> 8 & 0xFF;
         int var16 = var2.light() & 0xFF;
         float var17 = Math.min(1.0F, var4 / 0.06F);
         float var18 = 1.0F - (1.0F - var17) * (1.0F - var17);
         float var19 = 1.0F - Math.max(0.0F, (var4 - 0.05F) / (var9 - 0.05F));
         int var20 = 0;

         for (int var21 = 0; var21 < var12; var21++) {
            float var22 = var2.angle();
            float var23 = var22 + (float) (Math.PI / 2) * (var21 % 2 == 0 ? 1 : -1) + (float)var13.nextGaussian() * 0.55F;
            short var24 = 23144;
            float var25 = (0.35F + var13.nextFloat() * 0.45F) * (var2.crit() ? 1.3F : 1.0F) * var11;
            float var26 = 0.12F * var11 * var18;
            float var27 = var26 + var25 * var18;
            float var28 = (float)Math.cos(var23);
            float var29 = (float)Math.sin(var23);
            float var30 = 0.022F * var11 * (1.0F - 0.5F * var18);
            int var31 = Math.round(255.0F * var19 * var19);
            float[] var32 = new float[]{var3.x + var28 * var26, var3.y + var29 * var26, -var29 * var30, var28 * var30, var31};
            float[] var33 = new float[]{var3.x + var28 * var27, var3.y + var29 * var27, 0.0F, 0.0F, 0.0F};

            for (byte var34 = -1; var34 <= 1; var34 = (byte)(var34 + 2)) {
               var24 = 23144;
               i(var0, var32, var33, var34, -2.3F, var7, var8, var14, var15, var16);
               var20++;
            }
         }

         return var20;
      }
   }

   private static void i(class_287 var0, float[] var1, float[] var2, int var3, float var4, float var5, float var6, int var7, int var8, int var9) {
      Vector3f var10 = t(var1[0] + var1[2] * var3, var1[1] + var1[3] * var3, var4, var5, var6);
      Vector3f var11 = t(var1[0], var1[1], var4, var5, var6);
      Vector3f var12 = t(var2[0], var2[1], var4, var5, var6);
      Vector3f var13 = t(var2[0] + var2[2] * var3, var2[1] + var2[3] * var3, var4, var5, var6);
      int var14 = (int)var1[4];
      int var15 = (int)var2[4];
      var0.method_22912(var10.x, var10.y, var10.z).method_22913(0.0F, 0.0F).method_1336(var7, var8, var9, var14);
      var0.method_22912(var11.x, var11.y, var11.z).method_22913(0.0F, 0.9F).method_1336(var7, var8, var9, var14);
      var0.method_22912(var12.x, var12.y, var12.z).method_22913(0.0F, 0.9F).method_1336(var7, var8, var9, var15);
      var0.method_22912(var13.x, var13.y, var13.z).method_22913(0.0F, 0.0F).method_1336(var7, var8, var9, var15);
   }

   private static Vector3f t(float var0, float var1, float var2, float var3, float var4) {
      return new Vector3f(var0 * var3 * -var2, var1 * var3 * -var2, var2);
   }

   private static Vector4f J(class_243 var0, float var1, long var2) {
      KnifeSwingState.ensureSourceClassInitialized();
      Vector4f var4 = new Vector4f(
         (float)(var0.field_1352 - C.field_1352), (float)(var0.field_1351 - C.field_1351), (float)(var0.field_1350 - C.field_1350), 1.0F
      );
      D.transform(var4);
      float var5 = -var4.z;
      long var6 = 99545494809620L;
      int var8 = var6 < 0L ? -1 : 1;
      k.transform(var4);
      return var4.w <= 0.05F ? null : new Vector4f(var4.x / var4.w * var1, var4.y / var4.w, 0.0F, var5);
   }

   private static void C(KnifeApi var0, Matrix4f var1, long var2, char var4) {
      KnifeSwingState.ensureSourceClassInitialized();
      long var5 = System.nanoTime();
      float var7 = l == 0L ? 0.0F : Math.min(0.1F, (float)(var5 - l) / 1.0E9F);
      l = var5;
      KnifeWieldPipeline.WieldStage var8 = var0.ambient();
      class_310 var9 = class_310.method_1551();
      class_746 var10 = var9.field_1724;
      if (var8 != KnifeWieldPipeline.WieldStage.M && var0.ambientFx() && var10 != null && var9.field_1687 != null) {
         short var11 = 29651;
         if (S && !var9.method_1493() && KnifeAttackShell.pathCount() != 0) {
            class_243 var12 = var10.method_18798();
            float var13 = (float)var12.method_37267();
            float var14 = Math.clamp((var13 - 0.03F) / 0.12F, 0.0F, 1.0F);
            short var15 = 29651;
            boolean var16 = KnifeAttackShell.arcOf(0) != 0;
            float var17 = var10.method_5624() ? 1.6F : 1.0F;
            float var18 = var8 == KnifeWieldPipeline.WieldStage.H ? 12.0F : (var8 == KnifeWieldPipeline.WieldStage.X ? 14.0F : 11.0F);
            long var19 = 104999330L;
            int var21 = var19 < 0L ? -1 : 1;
            float var21x = var18 * var14 * var17 + (var16 ? 40.0F : 0.0F) + (var8 == KnifeWieldPipeline.WieldStage.X ? 1.2F : 0.6F);
            Z += var21x * var7;
            if (Z < 1.0F) {
               return;
            }

            Matrix4f var22 = new Matrix4f(KnifeRenderer.modelView()).mul(var1).scale(KnifeRenderer.drawnSide(), 1.0F, 1.0F);
            float var23 = (float)Math.tan(Math.toRadians(HandSweepPipeline.fov()) * 0.5);
            class_276 var24 = var9.method_1522();
            float var25 = (float)var24.field_1482 / var24.field_1481;
            Matrix4f var26 = new Matrix4f(D).invert();
            Vector3f var27 = new Vector3f();
            Vector3f var28 = new Vector3f();
            KnifeAttackShell.moment(0, var27, var28);
            ThreadLocalRandom var29 = ThreadLocalRandom.current();
            class_243 var30 = new class_243(var12.field_1352, Math.max(-0.05, var12.field_1351 * 0.5), var12.field_1350);
            int var31 = 4;

            while (Z >= 1.0F && var31-- > 0) {
               Z--;
               Vector3f var32 = new Vector3f(var27).lerp(var28, 0.25F + 0.8F * var29.nextFloat());
               var22.transformPosition(var32);
               float var33;
               int var34 = (var33 = var32.z - -0.08F) == 0.0F ? 0 : (var33 < 0.0F ? -1 : 1);
               long var35 = 104999330L;
               int var37 = var35 < 0L ? -1 : 1;
               if (var34 <= 0) {
                  float var37x = var32.x / (-var32.z * var23 * var25);
                  float var38 = var32.y / (-var32.z * var23);
                  float var39 = -var32.z * 1.15F;
                  Vector3f var40 = new Vector3f(var37x / k.m00() * var39, var38 / k.m11() * var39, -var39);
                  var26.transformPosition(var40);
                  class_243 var41 = C.method_1031(var40.x, var40.y, var40.z);
                  double var42 = var29.nextGaussian();
                  class_243 var44 = var30.method_1031(
                     var42 * 0.012, var29.nextGaussian() * 0.012 + (var8 == KnifeWieldPipeline.WieldStage.H ? 0.0 : 0.012), var29.nextGaussian() * 0.012
                  );
                  KnifeKeybind.shed(
                     var9.field_1687,
                     var8,
                     var41,
                     new Vector3f((float)var44.field_1352, (float)var44.field_1351, (float)var44.field_1350),
                     var0.light(),
                     var0.deep()
                  );
               }
            }

            Z = Math.min(Z, 2.0F);
            return;
         }
      }

      Z = 0.0F;
   }

   private static void i(long var0, GpuDevice var2) {
      KnifeSwingState.ensureSourceClassInitialized();
      if (G == null) {
         G = var2.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX wield light uniforms"), 136, 96L);
         F = MemoryUtil.memAlloc(96);
      }
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (i != null) {
            i.close();
         }
      } catch (Exception var44) {
         var0++;
      } finally {
         i = null;
      }

      try {
         if (G != null) {
            G.close();
         }
      } catch (Exception var42) {
         var0++;
      } finally {
         G = null;
      }

      try {
         if (F != null) {
            MemoryUtil.memFree(F);
         }
      } catch (Exception var40) {
         var0++;
      } finally {
         F = null;
      }

      try {
         if (V != null) {
            V.close();
         }
      } catch (Exception var38) {
         var0++;
      } finally {
         ;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 8780) & 32767;
      if (m[var3] == null) {
         m[var3] = j[var3] ^ var1;
      }

      return m[var3];
   }

   public static enum WieldStage {
      M,
      H,
      X,
      g,
      K;
   }

   private record WieldHit(class_243 i, float o, float m, boolean n, int N, int a, float L) {
      public class_243 at() {
         return this.i;
      }

      public float angle() {
         return this.o;
      }

      public float born() {
         return this.m;
      }

      public boolean crit() {
         return this.n;
      }

      public int light() {
         return this.N;
      }

      public int deep() {
         return this.a;
      }

      public float length() {
         return this.L;
      }

      public class_243 minecraftObjectState14() {
         return this.i;
      }

      public float floatState11() {
         return this.o;
      }

      public float floatState59() {
         return this.m;
      }

      public boolean booleanState33() {
         return this.n;
      }

      public int integerState28() {
         return this.N;
      }

      public int integerState26() {
         return this.a;
      }

      public float floatState57() {
         return this.L;
      }
   }
}
