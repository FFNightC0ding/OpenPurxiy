// Sourced By DevFFNightmare
package dev.purxiy.ui;

import dev.purxiy.render.Canvas;

public final class DrawContext {
   public Canvas canvas;
   public float time;
   public float dt;
   public float mouseX;
   public float mouseY;
   public boolean mouseDown;
   private boolean masked;
   
   private Object hoverTarget;
   private DrawContext.TypeE cursor = DrawContext.TypeE.ARROW;

   public void begin(Canvas var1, float var2, float var3, float var4, float var5, boolean var6) {
      this.canvas = var1;
      this.time = var2;
      this.dt = var3;
      this.mouseX = var4;
      this.mouseY = var5;
      this.mouseDown = var6;
      this.masked = false;
      this.hoverTarget = null;
      this.cursor = DrawContext.TypeE.ARROW;
   }

   public void mask(boolean var1) {
      this.masked = var1;
   }

   public boolean masked() {
      return this.masked;
   }

   public boolean inside(float var1, float var2, float var3, float var4) {
      return !this.masked && this.mouseX >= var1 && this.mouseY >= var2 && this.mouseX < var1 + var3 && this.mouseY < var2 + var4;
   }

   public boolean insideCircle(float var1, float var2, float var3) {
      float var4 = this.mouseX - var1;
      float var5 = this.mouseY - var2;
      return !this.masked && var4 * var4 + var5 * var5 <= var3 * var3;
   }

   public boolean hover(Object var1, float var2, float var3, float var4, float var5, DrawContext.TypeE var6) {
      if (this.inside(var2, var3, var4, var5)) {
         this.claim(var1, var6);
         return true;
      } else {
         return false;
      }
   }

   public void claim(Object var1, DrawContext.TypeE var2) {
      this.hoverTarget = var1;
      this.cursor = var2;
   }

   
   public Object hoverTarget() {
      return this.hoverTarget;
   }

   public DrawContext.TypeE cursor() {
      return this.cursor;
   }

   public static enum TypeE {
      ARROW,
      HAND,
      TEXT,
      MOVE;

      private static DrawContext.TypeE[] $values() {
         return new DrawContext.TypeE[]{ARROW, HAND, TEXT, MOVE};
      }
   }
}
