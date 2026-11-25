package dev.purxiy.modules.cape;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Snippet;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientBuilder;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.loader.fabric.client.ClientBuilder.SkinMeshSpec;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.ui.Theme;
import java.nio.ByteBuffer;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import net.minecraft.class_10789;
import net.minecraft.class_12137;
import net.minecraft.class_290;
import net.minecraft.class_6367;
import net.minecraft.class_7920;
import net.minecraft.class_8685;
import net.minecraft.class_12079.class_12081;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

public final class CapeRenderer {
   private static final int X = class_290.field_1577.getVertexSize();
   private static final int x = 160;
   private static final int I = 480468;
   public static final float B = 32.5F;
   private static final RenderPipeline o = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/profile_avatar"))
      .withVertexShader(ClientServices.id("core/profile_avatar"))
      .withFragmentShader(ClientServices.id("core/profile_avatar"))
      .withUniform("ProfileAvatar", class_10789.field_60031)
      .withUniform("PurxiyTheme", class_10789.field_60031)
      .withSampler("Sampler0")
      .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
      .withDepthWrite(true)
      .withCull(false)
      .withoutBlend()
      .withVertexFormat(class_290.field_1577, class_5596.field_27379)
      .build();
   private static class_6367 z;
   private static GpuBuffer b;
   private static GpuBuffer R;
   private static ByteBuffer Q;
   private static ByteBuffer Y;
   private static String h;
   private static final Matrix4f M = new Matrix4f();
   private static final Matrix4f H = new Matrix4f();
   private static final Matrix4f q = new Matrix4f();
   private static final Matrix4f O = new Matrix4f();
   private static final Matrix4f k = new Matrix4f();
   private static final Vector3f t = new Vector3f();
   private static final Vector3f K = new Vector3f();
   private static final float[] w = new float[24];
   private static final String[] c = new String[17];
   private static final long[] d = new long[]{
      -4522994936309415217L,
      -7731465408735228774L,
      6832603081524946791L,
      -7972110763098701148L,
      8357872662477288223L,
      8992540693539773672L,
      4195030046511974247L,
      -8385342150809883405L,
      7497156389688101536L,
      -4770624078504970328L,
      -6514995434516343982L,
      -4638241976902822787L,
      -7260402079430334989L,
      -8432641029086258154L,
      -8249532142589190215L,
      7658138787221520353L,
      -3031897558980252261L,
      540145073994476588L,
      5769120440044785460L
   };
   private static final long f = 160L;

   private CapeRenderer() {
   }

   public static boolean draw(
      Canvas var0,
      class_8685 var1,
      CapeRenderer.CapeVertexData var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11
   ) {
      long var12 = 120552164421599L;
      long var14 = var12 ^ 52616029848147L;
      int var16 = (int)((var12 ^ 52616029848147L) >>> 32);
      int var17 = (int)((var12 ^ 52616029848147L) << 32 >>> 56);
      int var18 = (int)(var14 << 40 >>> 40);
      var14 = var12 ^ 16312066975172L;
      int var19 = (int)((var12 ^ 16312066975172L) >>> 48);
      int var20 = (int)((var12 ^ 16312066975172L) << 16 >>> 32);
      int var21 = (int)(var14 << 48 >>> 48);
      long var22 = (var12 ^ 138619288347783L) >>> 16;
      int var24 = (int)((var12 ^ 138619288347783L) << 48 >>> 48);
      if (!(var5 <= 1.0F) && !(var6 <= 1.0F) && !(var7 <= 0.0F) && !(var0.alpha() <= 0.004F)) {
         GpuTextureView var25 = ClientBuilder.view(var1.comp_1626().comp_3627());
         if (var25 == null) {
            return false;
         } else {
            class_12081 var26 = var1.comp_1627();
            GpuTextureView var27 = var26 == null ? null : ClientBuilder.view(var26.comp_3627());

            try {
               GpuDevice var28 = RenderSystem.getDevice();
               if (!var28.precompilePipeline(o).isValid()) {
                  return false;
               } else {
                  float var29 = (float)Math.hypot(var0.transform().m00(), var0.transform().m01());
                  float var30 = (float)Math.hypot(var0.transform().m10(), var0.transform().m11());
                  float var31 = Math.min(4096, var28.getMaxTextureSize());
                  float var32 = Math.min(Math.max(0.5F, 2.0F * var0.pixelScale() * Math.max(var29, var30)), Math.min(var31 / var5, var31 / var6));
                  int var33 = Math.max(1, Math.round(var5 * var32));
                  int var34 = Math.max(1, Math.round(var6 * var32));
                  w(var33, (short)var19, var20, (char)var21, var34);
                  ByteBuffer var35 = Q;
                  var35.clear();
                  int var36 = var1.comp_1629() == class_7920.field_41122 ? 1 : 0;
                  SkinMeshSpec var37 = ClientBuilder.layers(var1);
                  int var40 = E(var35, var22, (short)var24, var2, var36 != 0, var37);
                  int var41 = var27 != null ? D(var35, var16, var2, (byte)var17, var18) : 0;
                  var35.flip();
                  CommandEncoder var42 = var28.createCommandEncoder();
                  var42.writeToBuffer(b.slice(0L, var35.remaining()), var35);
                  W(var5, var6, var7, var8);
                  float var43 = var9 >= 0.999F ? 1000.0F : -3.0F + var9 * 38.5F;
                  int var44 = Theme.tint(-4357889);
                  Std140Builder var45 = Std140Builder.intoBuffer(Y.clear())
                     .putMat4f(q)
                     .putMat4f(H)
                     .putVec4(var43, 3.2F, var11, var10)
                     .putVec4(0.74F * BlendMode.red(var44) / 189.0F, 0.5F * BlendMode.green(var44) / 128.0F, 1.0F * BlendMode.blue(var44) / 255.0F, 0.5F);
                  var42.writeToBuffer(R.slice(), var45.get());
                  GpuBuffer var46 = Theme.uniforms();
                  class_12137 var47 = ClientBuilder.pixels();
                  RenderPass var48 = var42.createRenderPass(
                     () -> "SourceByDevFFnightmare DeObfDeCompByGuideX profile avatar", z.method_71639(), OptionalInt.of(0), z.method_71640(), OptionalDouble.of(1.0)
                  );

                  try {
                     var48.setPipeline(o);
                     var48.setUniform("ProfileAvatar", R);
                     var48.setUniform("PurxiyTheme", var46);
                     var48.setVertexBuffer(0, b);
                     var48.bindTexture("Sampler0", var25, var47);
                     int var49 = -1022360454;
                     var48.draw(0, var40);
                     var36 = (int)var12;
                     CapeCache.metric("drawn");
                     if (var41 > 0) {
                        var48.bindTexture("Sampler0", var27, ClientBuilder.capeSampler(var1));
                        var49 = -1022360458;
                        var48.draw(var40, var41);
                        var36 = (int)var12;
                        CapeCache.metric("drawn");
                     }
                  } catch (Throwable var50) {
                     if (var48 != null) {
                        try {
                           var48.close();
                        } catch (Throwable var49x) {
                           var50.addSuppressed(var49x);
                        }
                     }

                     throw var50;
                  }

                  if (var48 != null) {
                     var48.close();
                  }

                  var0.image(
                     z.method_71639(),
                     RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR),
                     true,
                     var3,
                     var4,
                     var5,
                     var6,
                     0.0F,
                     1.0F,
                     1.0F,
                     0.0F,
                     0.0F,
                     -1
                  );
                  h = null;
                  return true;
               }
            } catch (RuntimeException var51) {
               if (!var51.toString().equals(h)) {
                  ClientServices.LOGGER.error("SourceByDevFFnightmare DeObfDeCompByGuideX profile avatar could not render", var51);
               }

               h = var51.toString();
               return false;
            }
         }
      } else {
         return false;
      }
   }

   private static void W(float var0, float var1, float var2, float var3) {
      float var4 = (float)Math.toRadians(16.0);
      float var5 = var1 / var2;
      float var6 = var5 / (2.0F * (float)Math.tan(var4 / 2.0F));
      float var7 = (var1 / 2.0F - var3) / var2;
      float var8 = (float)Math.toRadians(7.0);
      M.setPerspective(var4, var0 / var1, Math.max(1.0F, var6 - 60.0F), var6 + 60.0F);
      H.setLookAt(0.0F, var7 + var6 * (float)Math.sin(var8), var6 * (float)Math.cos(var8), 0.0F, var7, 0.0F, 0.0F, 1.0F, 0.0F);
      M.mul(H, q);
   }

   private static int E(ByteBuffer var0, long var1, short var3, CapeRenderer.CapeVertexData var4, boolean var5, SkinMeshSpec var6) {
      long var7 = 21676391598936L;
      long var9 = var7 ^ 74232745840920L;
      long var11 = var7 ^ 6303971354380L;
      int var13 = (int)((var7 ^ 6303971354380L) >>> 32);
      int var14 = (int)((var7 ^ 6303971354380L) << 32 >>> 48);
      int var15 = (int)(var11 << 48 >>> 48);
      long var16 = var7 ^ 96775785797543L;
      CapeModule.ensureSourceClassInitialized();
      float var18 = ClientBuilder.yourHeight();
      O.identity().translate(0.0F, 24.0F * var18 + var4.c, 0.0F).rotateY(var4.d).scale(var18, -var18, -var18);
      float var19 = var4.q * 20.0F;
      float var20 = (float)Math.cos(var19 * 0.09F) * 0.05F + 0.05F;
      float var21 = (float)Math.sin(var19 * 0.067F) * 0.05F;
      float var22 = k(var4.w, var16);
      float var23 = 2.55F + 0.32F * (float)Math.sin(var4.q * 9.5F);
      int var24 = 0;
      boolean var25 = var6 == null || !var6.fullModel();
      float[][] var26 = var25 ? ClientBuilder.PIVOT : var6.pivots();
      boolean var27 = var6 != null && var6.rigidHead();
      r(var26[0][0], var26[0][1], var26[0][2], var27 ? 0.0F : var4.y, var27 ? 0.0F : var4.Y, 0.0F);
      int var28 = 2904;
      ClientBuilder.build(k, 0);
      long var29 = 330755487L;
      int var32 = var29 < 0L ? -1 : 1;
      if (var25) {
         var24 += T(var0, -4.0F, var9, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0, 0, 0.0F, 64.0F, 64.0F, 0);
      }

      short var32x = 2904;
      float var33 = 0.5F;
      byte var34 = 0;
      byte var35 = 32;
      float var36 = 8.0F;
      float var37 = 8.0F;
      float var38 = 8.0F;
      float var39 = -4.0F;
      int var40 = var24 + s(var0, var6, 0, -4.0F, -8.0F, var13, var39, (char)var14, var38, var37, var36, (short)var15, var35, var34, var33);
      r(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
      ClientBuilder.build(k, 1);
      if (var25) {
         var40 += T(var0, -4.0F, var9, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 16, 16, 0.0F, 64.0F, 64.0F, 0);
      }

      var33 = 0.25F;
      var34 = 32;
      var35 = 16;
      var36 = 4.0F;
      var37 = 12.0F;
      var38 = 8.0F;
      var39 = -2.0F;
      int var41 = var40 + s(var0, var6, 1, -4.0F, 0.0F, var13, var39, (char)var14, var38, var37, var36, (short)var15, var35, var34, var33);
      float var42 = var5 ? 3.0F : 4.0F;
      r(var26[2][0] * ClientBuilder.armSpread(), var26[2][1], var26[2][2], J(var21, -0.25F, var22), 0.0F, J(var20, var23, var22));
      if (var25) {
         var41 += T(var0, var5 ? -2.0F : -3.0F, var9, -2.0F, -2.0F, var42, 12.0F, 4.0F, 40, 16, 0.0F, 64.0F, 64.0F, 0);
      }

      float var43 = var5 ? -2.0F : -3.0F;
      short var44 = 2904;
      var33 = 0.25F;
      var34 = 32;
      var35 = 40;
      var36 = 4.0F;
      var37 = 12.0F;
      var44 = 2904;
      var39 = -2.0F;
      int var45 = var41 + s(var0, var6, 2, var43, -2.0F, var13, var39, (char)var14, var42, var37, var36, (short)var15, var35, var34, var33);
      r(var26[3][0] * ClientBuilder.armSpread(), var26[3][1], var26[3][2], -var21, 0.0F, -var20);
      if (var25) {
         var45 += T(var0, -1.0F, var9, -2.0F, -2.0F, var42, 12.0F, 4.0F, 32, 48, 0.0F, 64.0F, 64.0F, 0);
      }

      var32x = 2554;
      long var46 = 330755487L;
      int var49 = var46 < 0L ? -1 : 1;
      var33 = 0.25F;
      var34 = 48;
      var35 = 48;
      var36 = 4.0F;
      var37 = 12.0F;
      var39 = -2.0F;
      var49 = var45 + s(var0, var6, 3, -1.0F, -2.0F, var13, var39, (char)var14, var42, var37, var36, (short)var15, var35, var34, var33);
      r(var26[4][0] * ClientBuilder.bodyWidth(), var26[4][1], var26[4][2], 0.0F, 0.0F, 0.0F);
      ClientBuilder.build(k, 4);
      if (var25) {
         var49 += T(var0, -2.0F, var9, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0, 16, 0.0F, 64.0F, 64.0F, 0);
      }

      long var50 = 330755487L;
      int var53 = var50 < 0L ? -1 : 1;
      var33 = 0.25F;
      var34 = 32;
      var35 = 0;
      var36 = 4.0F;
      var37 = 12.0F;
      var38 = 4.0F;
      var39 = -2.0F;
      var28 = var49 + s(var0, var6, 4, -2.0F, 0.0F, var13, var39, (char)var14, var38, var37, var36, (short)var15, var35, var34, var33);
      var53 = var28;
      r(var26[5][0] * ClientBuilder.bodyWidth(), var26[5][1], var26[5][2], 0.0F, 0.0F, 0.0F);
      ClientBuilder.build(k, 5);
      if (var25) {
         var53 = var28 + T(var0, -2.0F, var9, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 16, 48, 0.0F, 64.0F, 64.0F, 0);
      }

      var33 = 0.25F;
      var34 = 48;
      var35 = 0;
      var36 = 4.0F;
      var37 = 12.0F;
      var38 = 4.0F;
      var39 = -2.0F;
      return var53 + s(var0, var6, 5, -2.0F, 0.0F, var13, var39, (char)var14, var38, var37, var36, (short)var15, var35, var34, var33);
   }

   private static int s(
      ByteBuffer var0,
      SkinMeshSpec var1,
      int var2,
      float var3,
      float var4,
      int var5,
      float var6,
      char var7,
      float var8,
      float var9,
      float var10,
      short var11,
      int var12,
      int var13,
      float var14
   ) {
      long var15 = 24245835279444L;
      long var17 = var15 ^ 77208102860308L;
      return var1 == null
         ? T(var0, var3, var17, var4, var6, var8, var9, var10, var12, var13, var14, 64.0F, 64.0F, 1)
         : ClientBuilder.triangles(var0, var1, var2, k);
   }

   private static int D(ByteBuffer var0, int var1, CapeRenderer.CapeVertexData var2, byte var3, int var4) {
      long var5 = 73111670828428L;
      long var7 = var5 ^ 20117203613644L;
      k.set(O);
      ClientBuilder.build(k, 1);
      k.translate(0.0F, 0.0F, 2.0F).rotateX((float)Math.toRadians(var2.G)).rotateY((float) Math.PI);
      return T(var0, -5.0F, var7, 0.0F, -1.0F, 10.0F, 16.0F, 1.0F, 0, 0, 0.0F, 64.0F, 32.0F, 0);
   }

   private static void r(float var0, float var1, float var2, float var3, float var4, float var5) {
      k.set(O).translate(var0, var1, var2).rotateZYX(var5, var4, var3);
   }

   private static int T(
      ByteBuffer var0,
      float var1,
      long var2,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      int var9,
      int var10,
      float var11,
      float var12,
      float var13,
      int var14
   ) {
      long var15 = 48164026049583L;
      float var17 = var1 - var11;
      float var18 = var4 - var11;
      float var19 = var5 - var11;
      float var20 = var1 + var6 + var11;
      float var21 = var4 + var7 + var11;
      float var22 = var5 + var8 + var11;
      float[] var23 = w;
      b(var23, 0, var17, var18, var19);
      b(var23, 1, var20, var18, var19);
      b(var23, 2, var20, var21, var19);
      b(var23, 3, var17, var21, var19);
      b(var23, 4, var17, var18, var22);
      b(var23, 5, var20, var18, var22);
      b(var23, 6, var20, var21, var22);
      b(var23, 7, var17, var21, var22);
      float var24 = var9;
      float var25 = var9 + var8;
      float var26 = var9 + var8 + var6;
      float var27 = var9 + var8 + var6 + var6;
      float var28 = var9 + var8 + var6 + var8;
      float var29 = var9 + var8 + var6 + var8 + var6;
      float var30 = var10;
      float var31 = var10 + var8;
      float var32 = var10 + var8 + var7;
      int var33 = 0;
      var33 += S(var0, 5, 4, 0, 1, var25, var30, var26, var15, var31, 0.0F, -1.0F, 0.0F, 64.0F, var13, var14);
      var33 += S(var0, 2, 3, 7, 6, var26, var31, var27, var15, var30, 0.0F, 1.0F, 0.0F, 64.0F, var13, var14);
      var33 += S(var0, 0, 4, 7, 3, var24, var31, var25, var15, var32, -1.0F, 0.0F, 0.0F, 64.0F, var13, var14);
      var33 += S(var0, 1, 0, 3, 2, var25, var31, var26, var15, var32, 0.0F, 0.0F, -1.0F, 64.0F, var13, var14);
      var33 += S(var0, 5, 1, 2, 6, var26, var31, var28, var15, var32, 1.0F, 0.0F, 0.0F, 64.0F, var13, var14);
      return var33 + S(var0, 4, 5, 6, 7, var28, var31, var29, var15, var32, 0.0F, 0.0F, 1.0F, 64.0F, var13, var14);
   }

   private static int S(
      ByteBuffer var0,
      int var1,
      int var2,
      int var3,
      int var4,
      float var5,
      float var6,
      float var7,
      long var8,
      float var10,
      float var11,
      float var12,
      float var13,
      float var14,
      float var15,
      int var16
   ) {
      long var17 = 93467252845823L;
      k.transformDirection(K.set(var11, var12, var13)).normalize();
      A(var0, var1, var17, var7 / 64.0F, var6 / var15, var16);
      A(var0, var2, var17, var5 / 64.0F, var6 / var15, var16);
      A(var0, var3, var17, var5 / 64.0F, var10 / var15, var16);
      A(var0, var1, var17, var7 / 64.0F, var6 / var15, var16);
      A(var0, var3, var17, var5 / 64.0F, var10 / var15, var16);
      A(var0, var4, var17, var7 / 64.0F, var10 / var15, var16);
      return 6;
   }

   private static void A(ByteBuffer var0, int var1, long var2, float var4, float var5, int var6) {
      CapeModule.ensureSourceClassInitialized();
      k.transformPosition(t.set(w[var1 * 3], w[var1 * 3 + 1], w[var1 * 3 + 2]));
      var0.putFloat(t.x).putFloat(t.y).putFloat(t.z);
      var0.putFloat(var4).putFloat(var5);
      ByteBuffer var7 = var0.put((byte)-1).put((byte)-1);
      long var8 = 93467252845823L;
      int var11 = var8 < 0L ? -1 : 1;
      var7 = var7.put((byte)-1);
      long var11x = 93467252845822L;
      int var12 = var11x < 0L ? -1 : 1;
      var7.put((byte)(var6 == 0 ? 0 : 255));
      var0.put((byte)Math.round(K.x * 127.0F)).put((byte)Math.round(K.y * 127.0F)).put((byte)Math.round(K.z * 127.0F)).put((byte)0);
   }

   private static void b(float[] var0, int var1, float var2, float var3, float var4) {
      var0[var1 * 3] = var2;
      var0[var1 * 3 + 1] = var3;
      var0[var1 * 3 + 2] = var4;
   }

   private static void w(int var0, short var1, int var2, char var3, int var4) {
      CapeModule.ensureSourceClassInitialized();
      GpuDevice var5 = RenderSystem.getDevice();
      if (z == null) {
         z = new class_6367("SourceByDevFFnightmare DeObfDeCompByGuideX profile avatar", var0, var4, true);
      } else if (z.field_1482 != var0 || z.field_1481 != var4) {
         z.method_1234(var0, var4);
      }

      if (R == null) {
         R = var5.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX profile avatar camera"), 136, 160L);
         Y = MemoryUtil.memAlloc(160);
      }

      if (Q == null) {
         Q = MemoryUtil.memAlloc(480468 * X);
         b = var5.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX profile avatar mesh"), 40, 480468 * X);
      }
   }

   private static float k(float var0, long var1) {
      CapeModule.ensureSourceClassInitialized();
      var0 = var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static float J(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   static void close() {
      int var0 = 0;

      try {
         if (o != null) {
            ClientCache.release(o);
         }
      } catch (Exception var65) {
         var0++;
      } finally {
         ;
      }

      try {
         if (b != null) {
            b.close();
         }
      } catch (Exception var63) {
         var0++;
      } finally {
         b = null;
      }

      try {
         if (R != null) {
            R.close();
         }
      } catch (Exception var61) {
         var0++;
      } finally {
         R = null;
      }

      try {
         if (Q != null) {
            MemoryUtil.memFree(Q);
         }
      } catch (Exception var59) {
         var0++;
      } finally {
         Q = null;
      }

      try {
         if (Y != null) {
            MemoryUtil.memFree(Y);
         }
      } catch (Exception var57) {
         var0++;
      } finally {
         Y = null;
      }

      if (z != null) {
         z.method_1238();
         z = null;
      }

      if (var0 != 0) {
         throw new IllegalStateException("Wardrobe avatar cleanup failed");
      }
   }

   public static final class CapeVertexData {
      public float d;
      public float Y;
      public float y;
      public float q;
      public float w;
      public float c;
      public float G = 6.0F;
   }
}
