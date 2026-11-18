// Sourced By DevFFNightmare
package dev.purxiy.modules.mount;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.render.Canvas;
import dev.purxiy.ui.Theme;
import java.nio.ByteBuffer;
import net.minecraft.class_6367;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryUtil;

public final class MountRenderer {
   private static final MountUpdater7 a = new MountUpdater7();
   private static final MountGeometry E = new MountGeometry();
   private static final MountRenderer7.TypeV Z = new MountRenderer7.TypeV();
   private static class_6367 g;
   private static GpuBuffer P;
   private static ByteBuffer w;
   private static boolean S;
   private static String F;

   private static final String[] c = new String[5];

   private MountRenderer() {
   }

   public static boolean ready(MountFactory.Data7 var0) {
      return MountRenderer7.acquire(var0.id()) != null;
   }

   public static void draw(
      Canvas var0,
      MountFactory.Data7 var1,
      int var2,
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
      if (!(var8 <= 0.0F) && !(var9 <= 0.0F) && !(var5 <= 0.0F) && !(var0.alpha() <= 0.0F)) {
         MountRenderer7.TypeK var12 = MountRenderer7.acquire(var1.id());
         if (var12 != null) {
            try {
               GpuDevice var13 = RenderSystem.getDevice();
               float var14 = (float)Math.hypot(var0.transform().m00(), var0.transform().m01());
               float var15 = (float)Math.hypot(var0.transform().m10(), var0.transform().m11());
               float var16 = Math.min(4096, var13.getMaxTextureSize());
               float var17 = Math.min(Math.max(0.5F, 2.0F * var0.pixelScale() * Math.max(var14, var15)), Math.min(var16 / var8, var16 / var9));
               int var18 = Math.max(1, Math.round(var8 * var17));
               int var19 = Math.max(1, Math.round(var9 * var17));
               if (g == null) {
                  g = new class_6367("Purxiy garage showroom", var18, var19, true);
               } else if (g.field_1482 != var18 || g.field_1481 != var19) {
                  g.method_1234(var18, var19);
               }

               if (P == null) {
                  P = var13.createBuffer(ClientServices.label(() -> "Purxiy garage camera"), 136, 128L);
                  w = MemoryUtil.memAlloc(128);
               }

               if (!S) {
                  a.studio();
                  S = true;
               }

               E.P = 0.0F;
               E.s = E.u = E.W = E.B = E.j = E.E = E.r = E.l = 0.0F;
               E.v = var11;
               E.G = var11 * (float)Math.toRadians(var1.turnAngle());
               E.H = var10;

               for (int var20 = 0; var20 < 4; var20++) {
                  E.p[var20] = var10;
                  E.L[var20] = 0.0F;
               }

               E.compute(new Matrix4f(), var1);
               float var28 = 0.24F;
               float var21 = (float)Math.sqrt(1.0F - var28 * var28);
               Matrix4f var22 = new Matrix4f().translation(0.0F, 0.0F, -9.0F).rotateX((float)Math.asin(var28)).rotateY(var6).translate(0.0F, -var7, 0.0F);
               float var23 = var8 / var9;
               float var24 = var9 / 2.0F / var5;
               Matrix4f var25 = new Matrix4f().ortho(-var24 * var23, var24 * var23, -var24, var24, 1.0F, 18.0F);
               CommandEncoder var26 = var13.createCommandEncoder();
               var26.writeToBuffer(P.slice(), Std140Builder.intoBuffer(w.clear()).putMat4f(var22).putMat4f(var25).get());
               MountRenderer7.linear(var2, Z.h);
               Z.Q = 0.55F;
               Z.u = 0.72F;
               Z.T.set(-0.45F, 0.78F, 0.44F).normalize();
               Z.z = 1.0F;
               MountRenderer7.linear(Theme.tint(-3558145), Z.V);
               Z.b.set(0.55F, 0.52F, 0.62F);
               Z.Y.set(0.42F, 0.38F, 0.55F);
               Z.N = 1.0F;
               Z.l = 0.0F;
               Z.y = 0.0F;
               Z.S = 1.0F;
               Z.q = 0.0F;
               Z.j = 0.55F;
               Z.m = Z.C = Z.a = Z.f = Z.F = 0.0F;
               Z.W = 1.0F / var18;
               Z.c = 1.0F / var19;
               Z.I = 0.0F;
               MountRenderer7.draw(var12, E.A, Z, a, g.method_71639(), g.method_71640(), null, null, P);
               var0.image(
                  g.method_71639(),
                  RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR),
                  true,
                  var3 - var8 / 2.0F,
                  var4 - var9 / 2.0F,
                  var8,
                  var9,
                  0.0F,
                  1.0F,
                  1.0F,
                  0.0F,
                  0.0F,
                  -1
               );
               F = null;
            } catch (RuntimeException var27) {
               MountRenderer4.drawFailed();
               if (!var27.toString().equals(F)) {
                  ClientServices.LOGGER.error("Purxiy garage preview could not render", var27);
               }

               F = var27.toString();
            }
         }
      }
   }

   public static class_6367 portrait(MountFactory.Data7 var0, int var1, int var2) {
      MountRenderer7.TypeK var3 = MountRenderer7.acquire(var0.id());
      if (var3 == null) {
         return null;
      } else {
         GpuDevice var4 = RenderSystem.getDevice();
         class_6367 var5 = new class_6367("Purxiy garage portrait", var1, var2, true);
         if (P == null) {
            P = var4.createBuffer(ClientServices.label(() -> "Purxiy garage camera"), 136, 128L);
            w = MemoryUtil.memAlloc(128);
         }

         if (!S) {
            a.studio();
            S = true;
         }

         E.P = 0.0F;
         E.s = E.u = E.W = E.B = E.j = E.E = E.r = E.l = 0.0F;
         E.v = 0.35F;
         E.G = 0.25F;
         E.H = 0.0F;

         for (int var6 = 0; var6 < 4; var6++) {
            E.p[var6] = 0.0F;
            E.L[var6] = 0.0F;
         }

         E.compute(new Matrix4f(), var0);
         float var16 = 0.17F;
         float var7 = var0.hi().z - var0.lo().z;
         float var8 = var0.hi().y;
         float var9 = Math.max(var7 * 0.32F, var8 * 0.6F);
         float var10 = var8 * 0.46F;
         Matrix4f var11 = new Matrix4f()
            .translation(0.0F, 0.0F, -9.0F)
            .rotateX((float)Math.asin(var16))
            .rotateY(0.72F)
            .translate(0.0F, -var10, -(var0.hi().z + var0.lo().z) * 0.5F);
         float var12 = (float)var1 / var2;
         Matrix4f var13 = new Matrix4f().ortho(-var9 * var12, var9 * var12, -var9, var9, 1.0F, 18.0F);
         CommandEncoder var14 = var4.createCommandEncoder();
         var14.writeToBuffer(P.slice(), Std140Builder.intoBuffer(w.clear()).putMat4f(var11).putMat4f(var13).get());
         MountFactory.Data4 var15 = var0.paints().getFirst();
         MountRenderer7.linear(var15.color(), Z.h);
         Z.Q = 0.55F;
         Z.u = 0.72F;
         Z.T.set(-0.45F, 0.78F, 0.44F).normalize();
         Z.z = 1.0F;
         Z.V.set(1.0F, 0.96F, 0.92F);
         Z.b.set(0.55F, 0.52F, 0.62F);
         Z.Y.set(0.42F, 0.38F, 0.55F);
         Z.N = 1.0F;
         Z.l = 0.0F;
         Z.y = 0.0F;
         Z.S = 1.0F;
         Z.q = 0.0F;
         Z.j = 0.55F;
         Z.m = Z.C = Z.a = Z.f = Z.F = 0.0F;
         Z.W = 1.0F / var1;
         Z.c = 1.0F / var2;
         Z.I = 0.0F;
         Z.v = 0.0F;
         MountRenderer7.draw(var3, E.A, Z, a, var5.method_71639(), var5.method_71640(), null, null, P);
         return var5;
      }
   }

   public static void close() {
      if (g != null) {
         g.method_1238();
      }

      g = null;
      if (P != null) {
         P.close();
      }

      P = null;
      if (w != null) {
         MemoryUtil.memFree(w);
      }

      w = null;
      a.close();
      S = false;
      F = null;
   }

}
