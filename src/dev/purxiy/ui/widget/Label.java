package dev.purxiy.ui.widget;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;

public final class Label {
   private static final float HIT_SLOP = 10.0F;
   private final NumberSetting setting;
   private final Animator knob;
   private final Animator hover = Animator.smooth(0.0F);
   private final Animator active = Animator.snappy(0.0F);
   private boolean dragging;
   private float trackX;
   private float trackW;
   private float trackY;

   public Label(NumberSetting var1) {
      this.setting = var1;
      this.knob = Animator.snappy((float)var1.normalized());
   }

   public boolean dragging() {
      return this.dragging;
   }

   public void render(DrawContext var1, float var2, float var3, float var4) {
      this.trackX = var2;
      this.trackW = var4;
      this.trackY = var3;
      boolean var5 = var1.hover(this, var2 - 10.0F, var3 - 10.0F, var4 + 20.0F, 20.0F, DrawContext.Cursor.HAND) || this.dragging;
      float var6 = this.hover.target(var5 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var7 = this.active.target(this.dragging ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var8 = (float)this.setting.normalized();
      if (this.dragging) {
         this.knob.snap(var8);
      } else {
         this.knob.target(var8).update(MotionStyle.TOGGLE.dt(var1.dt));
      }

      float var9 = Math.max(0.0F, Math.min(1.0F, this.knob.value()));
      Canvas var10 = var1.canvas;
      float var11 = 6.0F;
      var10.roundRect(var2, var3 - var11 / 2.0F, var4, var11, var11 / 2.0F, BlendMode.lerp(452984831, 620756991, var6));
      float var12 = var2 + var4 * var9;
      float var13 = this.setting.min() < 0.0 && this.setting.max() > 0.0 ? (float)(-this.setting.min() / (this.setting.max() - this.setting.min())) : 0.0F;
      float var14 = var2 + var4 * var13;
      float var15 = Math.min(var14, var12);
      float var16 = Math.max(var11, Math.abs(var12 - var14) + (var13 > 0.0F ? var11 / 2.0F : 0.0F));
      if (var13 > 0.0F) {
         var15 = Math.max(var2, Math.min(var15, var2 + var4 - var16));
         var10.rect(var14 - 0.75F, var3 - var11 / 2.0F - 4.0F, 1.5F, var11 + 8.0F, BlendMode.withAlpha(-1, 0.22F + 0.18F * var6));
      }

      if (var7 > 0.01F) {
         var10.glow(var15, var3 - var11 / 2.0F, var16, var11, var11 / 2.0F, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.45F * var7));
      }

      var10.roundRectGradient(var15, var3 - var11 / 2.0F, var16, var11, var11 / 2.0F, 0.0F, Icons.INDIGO, Icons.MAGENTA);
      float var17 = 7.5F + var6 * 1.5F + var7 * 1.5F;
      if (var7 > 0.01F) {
         var10.glowCircle(var12, var3, var17, 16.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.35F * var7));
      }

      var10.shadow(var12 - var17, var3 - var17, var17 * 2.0F, var17 * 2.0F, var17, 8.0F, 0.0F, 0.0F, 2.0F, 1879048192);
      var10.circle(var12, var3, var17, -1);
      var10.circle(var12, var3, var17 * 0.32F * var7, BlendMode.withAlpha(Icons.MAGENTA, var7));
   }

   public boolean mouseDown(float var1, float var2) {
      if (var1 >= this.trackX - 10.0F && var1 <= this.trackX + this.trackW + 10.0F && Math.abs(var2 - this.trackY) <= 10.0F) {
         this.dragging = true;
         this.apply(var1, false);
         return true;
      } else {
         return false;
      }
   }

   public void mouseDrag(float var1) {
      if (this.dragging) {
         this.apply(var1, true);
      }
   }

   public void mouseUp() {
      this.dragging = false;
   }

   private void apply(float var1, boolean var2) {
      double var3 = this.setting.get();
      this.setting.setNormalized((var1 - this.trackX) / this.trackW);
      if (this.setting.get() != var3) {
         float var5 = 0.85F + 0.45F * (float)this.setting.normalized();
         SoundRequest.play(SoundEvent.SLIDER_TICK, var5, var2 ? 1.0F : 0.8F);
      }
   }
}
