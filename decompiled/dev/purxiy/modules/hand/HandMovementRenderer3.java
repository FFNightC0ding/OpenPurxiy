package dev.purxiy.modules.hand;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.systems.RenderSystem.class_5590;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.OptionalInt;
import net.minecraft.class_12137;
import net.minecraft.class_276;
import net.minecraft.class_287;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_9799;
import net.minecraft.class_9801;
import org.lwjgl.system.MemoryUtil;

public final class HandMovementRenderer3 {
   private static final int d = 13024;
   private static boolean U;
   private static GpuTexture Y;
   private static GpuTextureView b;
   private static int j = 0;
   private static int T = 0;
   private static GpuBuffer v;
   private static GpuBuffer R;
   private static ByteBuffer W;
   private static final class_9799 K = new class_9799(16384);
   private static final long g = 13024L;

   private HandMovementRenderer3() {
   }

   public static void want() {
      U = true;
   }

   static boolean takeWanted() {
      boolean var0 = U;
      U = false;
      return var0;
   }

   public static GpuTextureView draw() {
      long var0 = 59610203900659L;
      long var2 = var0 ^ 41891943785921L;
      HandMovementRenderer var4 = HandMovementSupport2.geometry();
      List var5 = var4.runs();
      if (!var5.isEmpty() && HandMovementSupport2.masked()) {
         GpuDevice var6 = RenderSystem.getDevice();
         if (!var6.precompilePipeline(HandMovementSupport3.C).isValid()) {
            return null;
         } else {
            class_310 var7 = class_310.method_1551();
            class_276 var8 = var7.method_1522();
            J(var6, var8.field_1482, var2, var8.field_1481);
            CommandEncoder var9 = var6.createCommandEncoder();
            class_287 var10 = new class_287(K, class_5596.field_27382, class_290.field_1577);
            float[] var11 = var4.data();
            int[] var12 = var4.colors();
            int var13 = 0;
            int var14 = 0;

            for (HandMovementRenderer.Data var16 : var5) {
               for (int var17 = var16.first(); var17 < var16.first() + var16.count(); var17++) {
                  int var18 = var17 * 8;
                  var10.method_22912(var11[var18], var11[var18 + 1], var11[var18 + 2])
                     .method_22913(var11[var18 + 3], var11[var18 + 4])
                     .method_39415(var12[var17])
                     .method_22914(var11[var18 + 5], var11[var18 + 6], var11[var18 + 7]);
               }

               var13 += var16.count();
               var14 = Math.max(var14, var16.count() / 4);
            }

            if (var13 == 0) {
               return null;
            } else {
               int var29 = class_290.field_1577.getVertexSize();
               class_9801 var30 = var10.method_60800();

               try {
                  if (v == null || v.size() < (long)var13 * var29) {
                     if (v != null) {
                        v.close();
                     }

                     long var32 = 16384L;

                     while (var32 < (long)var13 * var29) {
                        var32 <<= 1;
                     }

                     v = var6.createBuffer(ClientServices.label(() -> "Purxiy hand mask vertices"), 40, var32);
                  }

                  var9.writeToBuffer(v.slice(0L, (long)var13 * var29), var30.method_60818());
               } catch (Throwable var28) {
                  if (var30 != null) {
                     try {
                        var30.close();
                     } catch (Throwable var26) {
                        var28.addSuppressed(var26);
                     }
                  }

                  throw var28;
               }

               if (var30 != null) {
                  var30.close();
               }

               Std140Builder var31 = Std140Builder.intoBuffer(W.clear()).putMat4f(HandMovementSupport2.projection()).putMat4f(HandMovementSupport2.modelView());

               for (int var33 = 0; var33 < 6; var33++) {
                  var31.putVec4(0.0F, 0.0F, 0.0F, 0.0F);
               }

               ByteBuffer var34 = var31.get();
               MemoryUtil.memSet(MemoryUtil.memAddress(W) + var34.limit(), 0, 13024 - var34.limit());
               var9.writeToBuffer(R.slice(), W.clear().limit(13024));
               class_5590 var35 = RenderSystem.getSequentialBuffer(class_5596.field_27382);
               GpuBuffer var19 = var35.method_68274(var14 * 6);
               class_12137 var20 = RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
               RenderPass var21 = var9.createRenderPass(() -> "Purxiy hand mask", b, OptionalInt.of(0));

               try {
                  var21.setPipeline(HandMovementSupport3.C);
                  var21.setUniform("PurxiyHandTrail", R);
                  var21.bindTexture("Sampler2", var7.field_1773.method_22974().method_71650(), var20);
                  var21.setVertexBuffer(0, v);
                  var21.setIndexBuffer(var19, var35.method_31924());
                  int var22 = 0;

                  for (HandMovementRenderer.Data var24 : var5) {
                     var21.bindTexture("Sampler0", var24.texture().comp_5226(), var24.texture().comp_5227());
                     var21.drawIndexed(var22, 0, var24.count() / 4 * 6, 1);
                     HandMovementSupport4.metric("drawn");
                     var22 += var24.count();
                  }
               } catch (Throwable var27) {
                  if (var21 != null) {
                     try {
                        var21.close();
                     } catch (Throwable var25) {
                        var27.addSuppressed(var25);
                     }
                  }

                  throw var27;
               }

               if (var21 != null) {
                  var21.close();
               }

               return b;
            }
         }
      } else {
         return null;
      }
   }

   private static void J(GpuDevice var0, int var1, long var2, int var4) {
      SwingStyle.ensureSourceClassInitialized();
      if (R == null) {
         R = var0.createBuffer(ClientServices.label(() -> "Purxiy hand mask uniforms"), 136, 13024L);
         W = MemoryUtil.memCalloc(13024);
      }

      long var5 = 17792697560882L;
      int var8 = var5 < 0L ? -1 : 1;
      if (Y != null) {
         long var8x = 17792697560881L;
         int var9 = var8x < 0L ? -1 : 1;
         if (j == var1 && T == var4) {
            return;
         }
      }

      if (Y != null) {
         b.close();
         Y.close();
      }

      Y = var0.createTexture(() -> "Purxiy hand mask", 12, TextureFormat.RED8, var1, var4, 1, 1);
      b = var0.createTextureView(Y);
      j = var1;
      T = var4;
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (b != null) {
            b.close();
         }
      } catch (Exception var90) {
         var0++;
      } finally {
         b = null;
      }

      try {
         if (v != null) {
            v.close();
         }
      } catch (Exception var88) {
         var0++;
      } finally {
         v = null;
      }

      try {
         if (R != null) {
            R.close();
         }
      } catch (Exception var86) {
         var0++;
      } finally {
         R = null;
      }

      try {
         if (Y != null) {
            Y.close();
         }
      } catch (Exception var84) {
         var0++;
      } finally {
         Y = null;
      }

      try {
         if (W != null) {
            MemoryUtil.memFree(W);
         }
      } catch (Exception var82) {
         var0++;
      } finally {
         W = null;
      }

      try {
         if (K != null) {
            K.close();
         }
      } catch (Exception var80) {
         var0++;
      } finally {
         ;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }
}
