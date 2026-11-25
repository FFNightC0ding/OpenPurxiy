package dev.purxiy.render.font;

import com.mojang.blaze3d.textures.GpuTextureView;

public final class FontFamily {
   static final int PENDING = 0;
   static final int READY = 1;
   static final int EMPTY = 2;
   public final int codepoint;
   final int index;
   public final float advance;
   float x0;
   float y0;
   float x1;
   float y1;
   float u0;
   float v0;
   float u1;
   float v1;

   GpuTextureView page;
   volatile int state = 0;

   float[] kerningAfter;

   FontFamily(int var1, int var2, float var3) {
      this.codepoint = var1;
      this.index = var2;
      this.advance = var3;
   }

   public boolean ready() {
      return this.state == 1;
   }

   public boolean empty() {
      return this.state == 2;
   }

   public float left() {
      return this.x0;
   }

   public float top() {
      return this.y0;
   }

   public float right() {
      return this.x1;
   }

   public float bottom() {
      return this.y1;
   }


   public GpuTextureView pageView() {
      return this.page;
   }

   public float u0() {
      return this.u0;
   }

   public float v0() {
      return this.v0;
   }

   public float u1() {
      return this.u1;
   }

   public float v1() {
      return this.v1;
   }
}
