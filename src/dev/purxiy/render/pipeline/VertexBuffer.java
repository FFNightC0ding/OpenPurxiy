// Sourced By DevFFNightmare
package dev.purxiy.render.pipeline;

import com.mojang.blaze3d.textures.GpuTextureView;
import java.nio.ByteBuffer;
import java.util.Arrays;
import net.minecraft.class_12137;

import org.lwjgl.system.MemoryUtil;

public final class VertexBuffer implements AutoCloseable {
   private static final int INITIAL_QUADS = 4096;
   private long address;
   private int capacity = 4096;
   private int quads;
   private GpuTextureView[] views = new GpuTextureView[64];
   private class_12137[] samplers = new class_12137[64];
   private int[] firsts = new int[64];
   private int[] counts = new int[64];
   private boolean[] captures = new boolean[64];
   private float[] strengths = new float[64];
   private int batches;
   private boolean pendingCapture;
   private float pendingStrength;
   private boolean hasCapture;

   public VertexBuffer() {
      this.address = MemoryUtil.nmemAlloc((long)this.capacity * SkyVertexFormat.QUAD_SIZE);
      if (this.address == 0L) {
         throw new OutOfMemoryError("Purxiy: could not allocate the quad buffer");
      }
   }

   public void reset() {
      this.quads = 0;
      this.batches = 0;
      this.pendingCapture = false;
      this.hasCapture = false;

      for (int var1 = 0; var1 < this.views.length; var1++) {
         this.views[var1] = null;
         this.samplers[var1] = null;
      }
   }

   public void requestCapture(float var1) {
      this.pendingCapture = true;
      this.pendingStrength = var1;
      this.hasCapture = true;
   }

   public void ensureBackdrop(float var1) {
      if (!this.hasCapture) {
         if (this.batches == 0) {
            this.requestCapture(var1);
         } else {
            this.captures[0] = true;
            this.strengths[0] = var1;
            this.hasCapture = true;
         }
      }
   }

   public long reserve( GpuTextureView var1,  class_12137 var2) {
      int var3 = this.batches - 1;
      boolean var4 = var3 < 0 || this.pendingCapture;
      if (!var4 && var1 != null) {
         if (this.views[var3] == null) {
            this.views[var3] = var1;
            this.samplers[var3] = var2;
         } else if (this.views[var3] != var1 || this.samplers[var3] != var2) {
            var4 = true;
         }
      }

      if (var4) {
         var3 = this.pushBatch(var1, var2);
      }

      this.counts[var3]++;
      if (this.quads == this.capacity) {
         this.grow();
      }

      return this.address + (long)(this.quads++) * SkyVertexFormat.QUAD_SIZE;
   }

   private int pushBatch( GpuTextureView var1,  class_12137 var2) {
      if (this.batches == this.views.length) {
         int var3 = this.batches * 2;
         this.views = Arrays.copyOf(this.views, var3);
         this.samplers = Arrays.copyOf(this.samplers, var3);
         this.firsts = Arrays.copyOf(this.firsts, var3);
         this.counts = Arrays.copyOf(this.counts, var3);
         this.captures = Arrays.copyOf(this.captures, var3);
         this.strengths = Arrays.copyOf(this.strengths, var3);
      }

      int var4 = this.batches++;
      this.views[var4] = var1;
      this.samplers[var4] = var2;
      this.firsts[var4] = this.quads;
      this.counts[var4] = 0;
      this.captures[var4] = this.pendingCapture;
      this.strengths[var4] = this.pendingStrength;
      this.pendingCapture = false;
      return var4;
   }

   private void grow() {
      int var1 = this.capacity * 2;
      long var2 = MemoryUtil.nmemRealloc(this.address, (long)var1 * SkyVertexFormat.QUAD_SIZE);
      if (var2 == 0L) {
         throw new OutOfMemoryError("Purxiy: could not grow the quad buffer to " + var1 + " quads");
      } else {
         this.address = var2;
         this.capacity = var1;
      }
   }

   public static void shared(
      long var0,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      int var8,
      float var9,
      float var10,
      int var11,
      float var12,
      float var13,
      float var14,
      float var15,
      float var16,
      float var17,
      float var18,
      float var19,
      int var20
   ) {
      MemoryUtil.memPutFloat(var0 + 16L + 8L, var2);
      MemoryUtil.memPutFloat(var0 + 16L + 12L, var3);
      MemoryUtil.memPutFloat(var0 + 32L, var4);
      MemoryUtil.memPutFloat(var0 + 32L + 4L, var5);
      MemoryUtil.memPutFloat(var0 + 32L + 8L, var6);
      MemoryUtil.memPutFloat(var0 + 32L + 12L, var7);
      MemoryUtil.memPutFloat(var0 + 48L, var8);
      MemoryUtil.memPutFloat(var0 + 48L + 4L, var9);
      MemoryUtil.memPutFloat(var0 + 48L + 8L, var10);
      MemoryUtil.memPutFloat(var0 + 48L + 12L, var11);
      MemoryUtil.memPutFloat(var0 + 64L, var12);
      MemoryUtil.memPutFloat(var0 + 64L + 4L, var13);
      MemoryUtil.memPutFloat(var0 + 64L + 8L, var14);
      MemoryUtil.memPutFloat(var0 + 64L + 12L, var15);
      MemoryUtil.memPutFloat(var0 + 80L, var16);
      MemoryUtil.memPutFloat(var0 + 80L + 4L, var17);
      MemoryUtil.memPutFloat(var0 + 80L + 8L, var18);
      MemoryUtil.memPutFloat(var0 + 80L + 12L, var19);
      MemoryUtil.memPutInt(var0 + 100L, var20);
      MemoryUtil.memCopy(var0, var0 + SkyVertexFormat.VERTEX_SIZE, SkyVertexFormat.VERTEX_SIZE);
      MemoryUtil.memCopy(var0, var0 + SkyVertexFormat.VERTEX_SIZE * 2L, SkyVertexFormat.VERTEX_SIZE);
      MemoryUtil.memCopy(var0, var0 + SkyVertexFormat.VERTEX_SIZE * 3L, SkyVertexFormat.VERTEX_SIZE);
   }

   public static void corner(long var0, int var2, float var3, float var4, float var5, float var6, float var7, float var8, int var9) {
      long var10 = var0 + (long)var2 * SkyVertexFormat.VERTEX_SIZE;
      MemoryUtil.memPutFloat(var10 + 0L, var3);
      MemoryUtil.memPutFloat(var10 + 0L + 4L, var4);
      MemoryUtil.memPutFloat(var10 + 0L + 8L, var5);
      MemoryUtil.memPutFloat(var10 + 0L + 12L, var6);
      MemoryUtil.memPutFloat(var10 + 16L, var7);
      MemoryUtil.memPutFloat(var10 + 16L + 4L, var8);
      MemoryUtil.memPutInt(var10 + 96L, var9);
   }

   public int quadCount() {
      return this.quads;
   }

   public int batchCount() {
      return this.batches;
   }

   
   public GpuTextureView batchView(int var1) {
      return this.views[var1];
   }

   
   public class_12137 batchSampler(int var1) {
      return this.samplers[var1];
   }

   public int batchFirst(int var1) {
      return this.firsts[var1];
   }

   public int batchQuads(int var1) {
      return this.counts[var1];
   }

   public boolean batchCaptures(int var1) {
      return this.captures[var1];
   }

   public float batchCaptureStrength(int var1) {
      return this.strengths[var1];
   }

   public ByteBuffer data() {
      return MemoryUtil.memByteBuffer(this.address, this.quads * SkyVertexFormat.QUAD_SIZE);
   }

   @Override
   public void close() {
      if (this.address != 0L) {
         MemoryUtil.nmemFree(this.address);
         this.address = 0L;
      }
   }
}
