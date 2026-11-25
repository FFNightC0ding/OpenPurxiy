package dev.purxiy.modules.radiance;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.systems.RenderSystem.class_5590;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.mixin.RenderTypeAccessor;
import dev.purxiy.render.pipeline.SkyPipelines;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import net.minecraft.class_1921;
import net.minecraft.class_287;
import net.minecraft.class_290;
import net.minecraft.class_4588;
import net.minecraft.class_9799;
import net.minecraft.class_9801;
import net.minecraft.class_12247.class_12337;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

final class RadianceRenderer {
   private final String i;
   private final class_9799 f = new class_9799(65536);
   private final List<RadianceRenderer.TypeM> b = new ArrayList<>();
   private class_287 L;
   private int u;
   private class_1921 R;
   private int v;
   private GpuBuffer t;
   private static final long d = -4172745132484853720L;
   private static final long e = 65536L;

   RadianceRenderer(String var1) {
      this.i = var1;
   }

   class_4588 consumer(int var1, class_1921 var2) {
      long var3 = 90196248074698L;
      long var5 = var3 ^ 127108513482093L;
      if (this.L != null && this.u == var1 && this.R == var2) {
         return this.L;
      } else {
         this.i(var5);
         this.L = new class_287(this.f, class_5596.field_27382, class_290.field_1585);
         this.u = var1;
         this.R = var2;
         return this.L;
      }
   }

   boolean isEmpty() {
      long var1 = 126186117054713L;
      long var3 = var1 ^ 91608636071006L;
      this.i(var3);
      return this.b.isEmpty();
   }

   boolean has(int var1) {
      long var2 = 14184540830071L;
      long var4 = var2 ^ 49976945976784L;
      this.i(var4);

      for (RadianceRenderer.TypeM var7 : this.b) {
         if (var7.s == var1) {
            return true;
         }
      }

      return false;
   }

   private void i(long var1) {
      RadianceModule.ensureSourceClassInitialized();
      class_287 var3 = this.L;
      if (var3 != null) {
         this.L = null;
         class_9801 var4 = var3.method_60794();
         long var5 = 36913945304230L;
         int var8 = var5 < 0L ? -1 : 1;
         if (var4 != null && this.R != null) {
            var8 = var4.method_60822().comp_750();
            long var9 = 36913945304230L;
            int var10 = var9 < 0L ? -1 : 1;
            this.b.add(new RadianceRenderer.TypeM(this.u, this.R, this.v, var8, var4));
            this.v += var8;
         } else if (var4 != null) {
            var4.close();
         }

         this.R = null;
      }
   }

   void draw(GpuTextureView var1, GpuTextureView var2, boolean var3) {
      long var4 = 12002907408949L;
      long var6 = var4 ^ 47794791421586L;
      long var8 = var4 ^ 98189868914960L;
      this.i(var6);

      try {
         if (!this.b.isEmpty()) {
            GpuDevice var10 = RenderSystem.getDevice();
            CommandEncoder var11 = var10.createCommandEncoder();
            int var12 = class_290.field_1585.getVertexSize();
            this.E(var8, var10, (long)this.v * var12);
            int var13 = 0;

            for (RadianceRenderer.TypeM var15 : this.b) {
               var11.writeToBuffer(this.t.slice((long)var15.r * var12, (long)var15.m * var12), var15.T.method_60818());
               var13 = Math.max(var13, var15.m / 4);
            }

            class_5590 var30 = RenderSystem.getSequentialBuffer(class_5596.field_27382);
            GpuBuffer var31 = var30.method_68274(var13 * 6);
            Matrix4f var16 = new Matrix4f(RenderSystem.getModelViewMatrix());
            GpuBufferSlice[] var17 = new GpuBufferSlice[128];
            class_12337[] var18 = new class_12337[this.b.size()];

            for (int var19 = 0; var19 < this.b.size(); var19++) {
               RadianceRenderer.TypeM var20 = this.b.get(var19);
               if (var17[var20.s] == null) {
                  var17[var20.s] = RenderSystem.getDynamicUniforms()
                     .method_71106(var16, new Vector4f(var20.s / 255.0F, 0.0F, 0.0F, 1.0F), new Vector3f(), new Matrix4f());
               }

               var18[var19] = (class_12337)((RenderTypeAccessor)var20.j).purxiy$setup().method_75926().get("Sampler0");
            }

            RenderPass var32 = var11.createRenderPass(
               () -> "Purxiy radiance mask (" + this.i + ")", var1, var3 ? OptionalInt.of(0) : OptionalInt.empty(), var2, OptionalDouble.empty()
            );

            try {
               var32.setPipeline(SkyPipelines.RADIANCE_MASK);
               RenderSystem.bindDefaultUniforms(var32);
               var32.setVertexBuffer(0, this.t);
               var32.setIndexBuffer(var31, var30.method_31924());

               for (int var33 = 0; var33 < this.b.size(); var33++) {
                  RadianceRenderer.TypeM var21 = this.b.get(var33);
                  class_12337 var22 = var18[var33];
                  if (var22 != null) {
                     var32.setUniform("DynamicTransforms", var17[var21.s]);
                     var32.bindTexture("Sampler0", var22.comp_5226(), var22.comp_5227());
                     var32.drawIndexed(var21.r, 0, var21.m / 4 * 6, 1);
                     RadianceSupport.metric("drawn");
                  }
               }
            } catch (Throwable var28) {
               if (var32 != null) {
                  try {
                     var32.close();
                  } catch (Throwable var27) {
                     var28.addSuppressed(var27);
                  }
               }

               throw var28;
            }

            if (var32 != null) {
               var32.close();
            }

            return;
         }

         if (var3) {
            RenderSystem.getDevice().createCommandEncoder().clearColorTexture(var1.texture(), 0);
         }
      } finally {
         this.reset();
      }
   }

   void reset() {
      long var1 = 65997226558255L;
      long var3 = var1 ^ 32524170829704L;
      this.i(var3);

      for (RadianceRenderer.TypeM var6 : this.b) {
         var6.T.close();
      }

      this.b.clear();
      this.v = 0;
   }

   private void E(long var1, GpuDevice var3, long var4) {
      RadianceModule.ensureSourceClassInitialized();
      if (this.t == null || this.t.size() < var4) {
         long var6;
         for (var6 = 65536L; var6 < var4; var6 <<= 1) {
            long var8 = 91977685766948L;
            int var11 = var8 < 0L ? -1 : 1;
         }

         long var14 = 91977685766948L;
         int var14x = var14 < 0L ? -1 : 1;
         long var11 = 91977685766949L;
         int var12 = var11 < 0L ? -1 : 1;
         if (this.t != null) {
            this.t.close();
         }

         this.t = var3.createBuffer(ClientServices.label(() -> "Purxiy radiance mask vertices (" + this.i + ")"), 40, var6);
      }
   }

   public void releaseInstanceResources() {
      int var1 = 0;
      this.reset();

      try {
         if (this.t != null) {
            this.t.close();
         }
      } catch (Exception var15) {
         var1++;
      } finally {
         this.t = null;
      }

      try {
         if (this.f != null) {
            this.f.close();
         }
      } catch (Exception var13) {
         var1++;
      } finally {
         ;
      }

      if (var1 != 0) {
         throw new IllegalStateException("Private visual instance GPU cleanup failed");
      }
   }

   private record TypeM(int s, class_1921 j, int r, int m, class_9801 T) {
      public int slot() {
         return this.s;
      }

      public class_1921 outline() {
         return this.j;
      }

      public int firstVertex() {
         return this.r;
      }

      public int vertexCount() {
         return this.m;
      }

      public class_9801 mesh() {
         return this.T;
      }

      public int integerState13() {
         return this.s;
      }

      public class_1921 minecraftObjectState4() {
         return this.j;
      }

      public int integerState12() {
         return this.r;
      }

      public int integerState17() {
         return this.m;
      }

      public class_9801 minecraftObjectState1() {
         return this.T;
      }
   }
}
