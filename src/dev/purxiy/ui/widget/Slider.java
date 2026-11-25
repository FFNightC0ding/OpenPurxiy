package dev.purxiy.ui.widget;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.TextSetting;
import dev.purxiy.i18n.Translations;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.theme.ThemeColors;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import java.util.Collection;
import java.util.Locale;
import java.util.function.Supplier;
import net.minecraft.class_310;

public final class Slider {
   public static final float HEIGHT = 32.0F;
   private static final float PAD = 12.0F;
   private static final float ICON = 22.0F;
   private static final float SIZE = 12.5F;
   private static final String DOT = "•";
   private final Slider.Listener model;
   private final Animator hover = Animator.smooth(0.0F);
   private final Animator focus = Animator.smooth(0.0F);
   private final Animator caretX = Animator.snappy(0.0F);
   private final Animator scroll = Animator.snappy(0.0F);
   private final Animator iconHover = Animator.smooth(0.0F);
   private final Object iconKey = new Object();

   private Runnable onSubmit;

   private Supplier<? extends Collection<String>> suggestions;
   private boolean focused;

   private String lead;
   private boolean dragging;
   private int caret;
   private int anchor;
   private float lastEdit = -10.0F;
   private long lastClick;
   private float time;
   private float x;
   private float y;
   private float w;

   public Slider(final TextSetting var1) {
      this(new Slider.Listener() {
         @Override
         public String get() {
            return var1.get();
         }

         @Override
         public void set(String var1x) {
            var1.set(var1x);
         }

         @Override
         public int maxLength() {
            return var1.maxLength();
         }


         @Override
         public String placeholder() {
            return var1.placeholder();
         }

         @Override
         public boolean masked() {
            return var1.isMasked();
         }

         @Override
         public boolean colorCodes() {
            return var1.hasColorCodes();
         }
      });
   }

   public Slider(Slider.Listener var1) {
      this.model = var1;
   }

   public Slider onSubmit(Runnable var1) {
      this.onSubmit = var1;
      return this;
   }

   public Slider suggestions(Supplier<? extends Collection<String>> var1) {
      this.suggestions = var1;
      return this;
   }

   public boolean focused() {
      return this.focused;
   }

   public Slider icon(String var1) {
      this.lead = var1;
      return this;
   }

   public void selectAll() {
      this.anchor = 0;
      this.caret = this.model.get().length();
   }

   public void focus() {
      this.focused = true;
      this.caret = this.anchor = this.model.get().length();
      this.lastEdit = this.time;
   }

   public boolean contains(float var1, float var2) {
      return var1 >= this.x && var1 <= this.x + this.w && var2 >= this.y && var2 <= this.y + 32.0F;
   }

   public void blur() {
      this.focused = false;
      this.dragging = false;
      this.anchor = this.caret;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      Translations.pause();

      try {
         this.renderField(var1, var2, var3, var4, var5);
      } finally {
         Translations.resume();
      }
   }

   private void renderField(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      Canvas var6 = var1.canvas;
      this.time = var1.time;
      this.x = var2;
      this.y = var3 - 16.0F;
      this.w = var4;
      String var7 = this.model.get();
      this.caret = Math.min(this.caret, var7.length());
      this.anchor = Math.min(this.anchor, var7.length());
      boolean var8 = var5 && var1.hover(this, var2, this.y, var4, 32.0F, DrawContext.Cursor.TEXT);
      float var9 = this.hover.target(var8 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var10 = this.focus.target(this.focused ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      if (var10 > 0.01F) {
         var6.glow(var2, this.y, var4, 32.0F, 10.0F, 16.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.2F * var10));
      }

      var6.roundRect(
         var2, this.y, var4, 32.0F, 10.0F, BlendMode.lerp(BlendMode.lerp(234881023, 385875967, var9), BlendMode.withAlpha(Icons.VIOLET, 0.17F), var10)
      );
      var6.roundRectOutline(
         var2, this.y, var4, 32.0F, 10.0F, 1.0F, BlendMode.lerp(BlendMode.lerp(486539263, 822083583, var9), BlendMode.withAlpha(Icons.MAGENTA, 0.7F), var10)
      );
      float var11 = var2 + 12.0F + this.leadWidth();
      float var12 = var4 - 12.0F - 22.0F - 4.0F - this.leadWidth();
      float var13 = this.width(var7.substring(0, this.caret));
      String var14 = this.focused && this.caret == var7.length() && this.anchor == this.caret ? this.completion(var7) : null;
      float var15 = 0.0F;
      if (this.focused) {
         var15 = this.scroll.target();
         if (var13 - var15 > var12 - 2.0F) {
            var15 = var13 - var12 + 2.0F;
         } else if (var13 < var15) {
            var15 = var13;
         }

         var15 = Math.max(0.0F, Math.min(var15, this.width(var7) - var12 + 2.0F));
      }

      float var16 = this.scroll.target(var15).update(MotionStyle.TOGGLE.dt(var1.dt));
      var6.pushClip(var2 + 4.0F + this.leadWidth(), this.y + 2.0F, var12 + 12.0F - 4.0F, 28.0F);
      if (var7.isEmpty()) {
         String var17 = this.model.placeholder();
         if (var17 != null) {
            var6.text(
               Fonts.MEDIUM,
               Translations.tr(var17),
               var11,
               var3,
               12.5F,
               BlendMode.withAlpha(Icons.TEXT_3, 0.9F - 0.3F * var10),
               FontStyle.LEFT,
               FontWeight.MIDDLE
            );
         }
      } else if (!this.focused && this.model.masked()) {
         int var26 = Math.min(var7.codePointCount(0, var7.length()), 28);
         var6.text(Fonts.MEDIUM, "•".repeat(var26), var11, var3, 12.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE, 0.12F);
      } else if (!this.focused && this.model.colorCodes()) {
         this.colored(var6, var7, var11, var3);
      } else {
         if (this.focused && this.anchor != this.caret) {
            float var24 = this.width(var7.substring(0, Math.min(this.anchor, this.caret)));
            float var18 = this.width(var7.substring(0, Math.max(this.anchor, this.caret)));
            var6.roundRect(var11 - var16 + var24 - 1.0F, var3 - 9.0F, var18 - var24 + 2.0F, 18.0F, 4.0F, BlendMode.withAlpha(Icons.PURPLE, 0.38F));
         }

         float var25 = var6.text(Fonts.MEDIUM, var7, var11 - var16, var3, 12.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
         if (var14 != null) {
            var6.text(Fonts.MEDIUM, var14, var11 - var16 + var25, var3, 12.5F, BlendMode.withAlpha(Icons.TEXT_3, 0.85F), FontStyle.LEFT, FontWeight.MIDDLE);
         }
      }

      if (this.focused) {
         float var27 = this.caretX.target(var13).update(MotionStyle.TOGGLE.dt(var1.dt));
         float var29 = var1.time - this.lastEdit;
         float var19 = var29 < 0.5F ? 1.0F : 0.5F + 0.5F * (float)Math.cos((var29 - 0.5F) * 5.5F);
         var6.roundRect(var11 - var16 + var27 - 0.75F, var3 - 8.0F, 1.6F, 16.0F, 0.8F, BlendMode.withAlpha(Icons.MAGENTA, Math.max(0.0F, var19) * var10));
      } else {
         this.caretX.snap(var13);
      }

      var6.popClip();
      if (this.lead != null) {
         var6.icon(this.lead, var2 + 12.0F + 6.0F, var3, 13.0F, BlendMode.lerp(Icons.TEXT_3, Icons.TEXT_2, Math.max(var9, var10)));
      }

      float var28 = var2 + var4 - 11.0F - 6.0F;
      boolean var30 = this.focused && !var7.isEmpty();
      boolean var31 = var30 && var5 && var1.hover(this.iconKey, var28 - 11.0F, this.y, 22.0F, 32.0F, DrawContext.Cursor.HAND);
      float var20 = this.iconHover.target(var31 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      String var21 = var30 ? "x" : (this.model.masked() && !var7.isEmpty() ? "eye-off" : "pen-line");
      int var22 = var30 ? BlendMode.lerp(Icons.TEXT_3, Icons.TEXT, var20) : BlendMode.withAlpha(Icons.TEXT_3, 0.55F + 0.45F * var9);
      if (var30 && var20 > 0.01F) {
         var6.circle(var28, var3, 9.0F, BlendMode.withAlpha(-1, 0.08F * var20));
      }

      if (var30 || this.lead == null) {
         var6.icon(var21, var28, var3, var30 ? 12.0F : 12.5F, var22);
      }
   }

   private void colored(Canvas var1, String var2, float var3, float var4) {
      ThemeColors var5 = ThemeColors.parse(var2);
      if (var5.isEmpty()) {
         var1.text(Fonts.MEDIUM, var2, var3, var4, 12.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
      } else {
         String var6 = var5.visible();
         float var7 = var3;
         int var8 = 0;
         int var10 = 0;

         while (var10 < var5.length()) {
            int var11 = var5.rgb(var10);
            int var9 = var10 + 1;

            while (var9 < var5.length() && var5.rgb(var9) == var11) {
               var9++;
            }

            int var12 = var6.offsetByCodePoints(var8, var9 - var10);
            int var13 = var11 < 0 ? Icons.TEXT : 0xFF000000 | var11;
            var7 += var1.text(Fonts.MEDIUM, var6.substring(var8, var12), var7, var4, 12.5F, var13, FontStyle.LEFT, FontWeight.MIDDLE);
            var8 = var12;
            var10 = var9;
         }
      }
   }


   private String completion(String var1) {
      if (this.suggestions != null && !var1.isBlank()) {
         String var2 = var1.toLowerCase(Locale.ROOT);

         for (String var4 : this.suggestions.get()) {
            if (var4.length() > var1.length() && var4.toLowerCase(Locale.ROOT).startsWith(var2)) {
               return var4.substring(var1.length());
            }
         }

         return null;
      } else {
         return null;
      }
   }

   private float leadWidth() {
      return this.lead == null ? 0.0F : 20.0F;
   }

   private float width(String var1) {
      return Fonts.MEDIUM.measure(var1, 12.5F, 0.0F);
   }

   private int indexAt(float var1) {
      String var2 = this.model.get();
      float var3 = var1 - (this.x + 12.0F + this.leadWidth() - this.scroll.value());
      int var4 = 0;
      float var5 = Math.abs(var3);
      int var6 = 0;

      while (var6 < var2.length()) {
         var6 += Character.charCount(var2.codePointAt(var6));
         float var7 = Math.abs(var3 - this.width(var2.substring(0, var6)));
         if (var7 < var5) {
            var4 = var6;
            var5 = var7;
         }
      }

      return var4;
   }

   public boolean mouseDown(float var1, float var2) {
      if (!this.contains(var1, var2)) {
         this.blur();
         return false;
      } else {
         String var3 = this.model.get();
         float var4 = this.x + this.w - 11.0F - 6.0F;
         if (this.focused && !var3.isEmpty() && var1 >= var4 - 11.0F) {
            this.edit("", 0, var3.length());
            SoundRequest.play(SoundEvent.TOGGLE_OFF);
            return true;
         } else {
            long var5 = System.currentTimeMillis();
            boolean var7 = this.focused;
            this.focused = true;
            if (!var7) {
               SoundRequest.play(SoundEvent.CLICK);
            }

            if (var7 && var5 - this.lastClick < 320L) {
               this.anchor = 0;
               this.caret = var3.length();
            } else {
               this.caret = this.indexAt(var1);
               if (!class_310.method_1551().method_74187() || !var7) {
                  this.anchor = this.caret;
               }

               this.dragging = true;
            }

            this.lastClick = var5;
            this.lastEdit = this.time;
            return true;
         }
      }
   }

   public void mouseDrag(float var1, float var2) {
      if (this.dragging && this.focused) {
         this.caret = this.indexAt(var1);
      }
   }

   public void mouseUp() {
      this.dragging = false;
   }

   public boolean keyPressed(int var1) {
      if (!this.focused) {
         return false;
      } else {
         class_310 var2 = class_310.method_1551();
         boolean var3 = var2.method_74188();
         boolean var4 = var2.method_74187();
         String var5 = this.model.get();
         int var6 = Math.min(this.anchor, this.caret);
         int var7 = Math.max(this.anchor, this.caret);
         String var8 = this.caret == var5.length() && var6 == var7 ? this.completion(var5) : null;
         switch (var1) {
            case 65:
               if (var3) {
                  this.anchor = 0;
                  this.caret = var5.length();
               }
               break;
            case 67:
            case 88:
               if (var3 && var6 != var7) {
                  var2.field_1774.method_1455(var5.substring(var6, var7));
                  if (var1 == 88) {
                     this.edit("", var6, var7);
                  }
               }
               break;
            case 86:
               if (var3) {
                  this.edit(var2.field_1774.method_1460(), var6, var7);
               }
               break;
            case 256:
               this.blur();
               SoundRequest.play(SoundEvent.CLICK);
               break;
            case 257:
            case 258:
            case 335:
               if (var8 != null && var1 == 258) {
                  this.edit(var8, var5.length(), var5.length());
               } else {
                  this.blur();
                  SoundRequest.play(SoundEvent.CLICK);
                  if (this.onSubmit != null) {
                     this.onSubmit.run();
                  }
               }
               break;
            case 259:
               if (var6 != var7) {
                  this.edit("", var6, var7);
               } else if (this.caret > 0) {
                  this.edit("", var3 ? wordStart(var5, this.caret) : var5.offsetByCodePoints(this.caret, -1), this.caret);
               }
               break;
            case 261:
               if (var6 != var7) {
                  this.edit("", var6, var7);
               } else if (this.caret < var5.length()) {
                  this.edit("", this.caret, var3 ? wordEnd(var5, this.caret) : var5.offsetByCodePoints(this.caret, 1));
               }
               break;
            case 262:
               if (var8 != null) {
                  this.edit(var8, var5.length(), var5.length());
               } else {
                  this.move(
                     var6 != var7 && !var4
                        ? var7
                        : (var3 ? wordEnd(var5, this.caret) : (this.caret < var5.length() ? var5.offsetByCodePoints(this.caret, 1) : var5.length())),
                     var4
                  );
               }
               break;
            case 263:
               this.move(
                  var6 != var7 && !var4 ? var6 : (var3 ? wordStart(var5, this.caret) : (this.caret > 0 ? var5.offsetByCodePoints(this.caret, -1) : 0)), var4
               );
               break;
            case 264:
            case 269:
               this.move(var5.length(), var4);
               break;
            case 265:
            case 268:
               this.move(0, var4);
         }

         return true;
      }
   }

   public boolean charTyped(int var1) {
      if (!this.focused) {
         return false;
      } else {
         if (!Character.isISOControl(var1)) {
            this.edit(Character.toString(var1), Math.min(this.anchor, this.caret), Math.max(this.anchor, this.caret));
         }

         return true;
      }
   }

   private void move(int var1, boolean var2) {
      this.caret = var1;
      if (!var2) {
         this.anchor = var1;
      }

      this.lastEdit = this.time;
   }

   private void edit(String var1, int var2, int var3) {
      String var4 = this.model.get();
      int var5 = this.model.maxLength() - (var4.length() - (var3 - var2));
      if (var1.length() > var5) {
         var1 = var5 <= 0 ? "" : var1.substring(0, Character.isHighSurrogate(var1.charAt(var5 - 1)) ? var5 - 1 : var5);
      }

      if (var1.isEmpty() && var2 == var3) {
         SoundRequest.play(SoundEvent.DENY);
      } else {
         String var6 = var4.substring(0, var2);
         String var7 = var6 + var1 + var4.substring(var3);
         this.model.set(var7);
         String var8 = this.model.get();
         this.caret = Math.max(0, Math.min(var8.length(), var2 + var1.length() - (var7.length() - var8.length())));
         this.anchor = this.caret;
         this.lastEdit = this.time;
         SoundRequest.play(SoundEvent.TYPE, var1.isEmpty() ? 0.9F : 1.0F);
      }
   }

   private static int wordStart(String var0, int var1) {
      while (var1 > 0 && !Character.isLetterOrDigit(var0.charAt(var1 - 1))) {
         var1--;
      }

      while (var1 > 0 && Character.isLetterOrDigit(var0.charAt(var1 - 1))) {
         var1--;
      }

      return var1;
   }

   private static int wordEnd(String var0, int var1) {
      while (var1 < var0.length() && !Character.isLetterOrDigit(var0.charAt(var1))) {
         var1++;
      }

      while (var1 < var0.length() && Character.isLetterOrDigit(var0.charAt(var1))) {
         var1++;
      }

      return var1;
   }

   public interface Listener {
      String get();

      void set(String var1);

      int maxLength();


      default String placeholder() {
         return null;
      }

      default boolean masked() {
         return false;
      }

      default boolean colorCodes() {
         return false;
      }
   }
}
