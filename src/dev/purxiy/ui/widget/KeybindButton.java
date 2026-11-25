package dev.purxiy.ui.widget;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.modules.Module;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.KeyLabel;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

public final class KeybindButton {
   private static final float HEIGHT = 30.0F;
   private final IntSupplier key;
   private final IntConsumer setKey;
   private final Animator hover = Animator.smooth(0.0F);
   private final Animator listen = Animator.smooth(0.0F);
   private final Animator width = Animator.snappy(0.0F);
   private boolean listening;
   private float x;
   private float y;
   private float w;

   public KeybindButton(Module var1) {
      this(var1::key, var1::setKey);
   }

   public KeybindButton(IntSupplier var1, IntConsumer var2) {
      this.key = var1;
      this.setKey = var2;
   }

   public boolean listening() {
      return this.listening;
   }

   public void render(DrawContext var1, float var2, float var3) {
      Canvas var4 = var1.canvas;
      String var5 = this.listening ? "Press a key…" : KeyLabel.keyName(this.key.getAsInt());
      float var6 = Math.max(84.0F, var4.textWidth(Fonts.SEMIBOLD, var5, 12.0F) + 30.0F);
      if (this.width.value() == 0.0F) {
         this.width.snap(var6);
      }

      this.w = this.width.target(var6).update(MotionStyle.TOGGLE.dt(var1.dt));
      this.x = var2 - this.w;
      this.y = var3 - 15.0F;
      boolean var7 = var1.hover(this, this.x, this.y, this.w, 30.0F, DrawContext.Cursor.HAND);
      float var8 = this.hover.target(var7 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var9 = this.listen.target(this.listening ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var10 = 0.55F + 0.45F * (float)Math.sin(var1.time * 6.0);
      if (var9 > 0.01F) {
         var4.glow(this.x, this.y, this.w, 30.0F, 10.0F, 16.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.35F * var9 * var10));
      }

      var4.roundRect(
         this.x, this.y, this.w, 30.0F, 10.0F, BlendMode.lerp(BlendMode.lerp(318767103, 486539263, var8), BlendMode.withAlpha(Icons.VIOLET, 0.22F), var9)
      );
      var4.roundRectOutline(
         this.x,
         this.y,
         this.w,
         30.0F,
         10.0F,
         1.0F,
         BlendMode.lerp(BlendMode.lerp(486539263, 872415231, var8), BlendMode.withAlpha(Icons.MAGENTA, 0.5F + 0.5F * var10), var9)
      );
      int var11 = this.listening ? Icons.TEXT : (this.key.getAsInt() == -1 ? Icons.TEXT_3 : Icons.TEXT);
      var4.text(Fonts.SEMIBOLD, var5, this.x + this.w / 2.0F, var3, 12.0F, var11, FontStyle.CENTER, FontWeight.MIDDLE);
   }

   public boolean mouseDown(float var1, float var2) {
      if (var1 >= this.x && var1 <= this.x + this.w && var2 >= this.y && var2 <= this.y + 30.0F) {
         this.listening = !this.listening;
         SoundRequest.play(SoundEvent.CLICK);
         return true;
      } else {
         if (this.listening) {
            this.listening = false;
         }

         return false;
      }
   }

   public boolean keyPressed(int var1) {
      if (!this.listening) {
         return false;
      } else {
         this.listening = false;
         if (var1 == 256) {
            SoundRequest.play(SoundEvent.CLICK);
         } else if (var1 != 259 && var1 != 261) {
            this.setKey.accept(var1);
            SoundRequest.play(SoundEvent.KEY_SET);
         } else {
            this.setKey.accept(-1);
            SoundRequest.play(SoundEvent.TOGGLE_OFF);
         }

         return true;
      }
   }
}
