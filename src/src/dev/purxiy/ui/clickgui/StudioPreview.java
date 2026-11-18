// Sourced By DevFFNightmare
package dev.purxiy.ui.clickgui;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.ChoiceListSetting;
import dev.purxiy.config.KeybindSetting;
import dev.purxiy.loader.fabric.Marker;
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
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.widget.Slider;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;

final class StudioPreview {
   private static final float MAX_WIDTH = 580.0F;
   private static final float HEADER = 104.0F;
   private static final float LABELS = 30.0F;
   private static final float FOOTER = 64.0F;
   private static final float PAD = 28.0F;
   private static final float LINE = 50.0F;
   private static final float EMPTY = 136.0F;
   private static final float TRASH = 30.0F;
   private static final float ARROW = 30.0F;
   
   private final KeybindSetting setting;
   
   private final ChoiceListSetting players;
   private final List<StudioPreview.TypeS> lines = new ArrayList<>();
   private final Animator open = new Animator(0.0F, 300.0F, 30.0F);
   private final Animator scroll = Animator.smooth(0.0F);
   private final Animator closeHover = Animator.smooth(0.0F);
   private final Animator addHover = Animator.smooth(0.0F);
   private final Object closeKey = new Object();
   private final Object addKey = new Object();
   private boolean closing;
   private boolean scrollToEnd;
   private float scrollTarget;
   private float maxScroll;
   private float px;
   private float py;
   private float pw;
   private float ph;
   private float listTop;
   private float listBottom;
   private float addX;
   private float addY;
   private float addW;
   private float addH;

   StudioPreview(KeybindSetting var1) {
      this.setting = var1;
      this.players = null;

      for (KeybindSetting.TypeC var3 : var1.get()) {
         StudioPreview.TypeS var4 = new StudioPreview.TypeS(var3.from(), var3.to());
         var4.presence.snap(1.0F);
         this.lines.add(var4);
      }

      this.opened();
   }

   StudioPreview(ChoiceListSetting var1) {
      this.setting = null;
      this.players = var1;

      for (String var3 : var1.names()) {
         StudioPreview.TypeS var4 = new StudioPreview.TypeS(var3, "");
         var4.presence.snap(1.0F);
         this.lines.add(var4);
      }

      this.opened();
   }

   private boolean single() {
      return this.players != null;
   }

   private void opened() {
      this.open.target(1.0F);
      if (this.lines.isEmpty()) {
         this.add();
      }
   }

   private Slider.Listener model(final Supplier<String> var1, final Consumer<String> var2, final String var3, final boolean var4) {
      return new Slider.Listener() {
         @Override
         public String get() {
            return (String)var1.get();
         }

         @Override
         public void set(String var1x) {
            var2.accept(var1x);
            StudioPreview.this.save();
         }

         @Override
         public int maxLength() {
            return StudioPreview.this.single() ? 16 : 64;
         }

         
         @Override
         public String placeholder() {
            return var3;
         }

         @Override
         public boolean colorCodes() {
            return var4;
         }
      };
   }

   private void save() {
      if (this.players != null) {
         ArrayList var1 = new ArrayList(this.lines.size());

         for (StudioPreview.TypeS var3 : this.lines) {
            var1.add(var3.from);
         }

         this.players.set(var1);
      } else if (this.setting != null) {
         ArrayList var4 = new ArrayList(this.lines.size());

         for (StudioPreview.TypeS var6 : this.lines) {
            var4.add(new KeybindSetting.TypeC(var6.from, var6.to));
         }

         this.setting.set(var4);
      }
   }

   private void add() {
      for (StudioPreview.TypeS var2 : this.lines) {
         var2.blur();
      }

      StudioPreview.TypeS var3 = new StudioPreview.TypeS("", "");
      this.lines.add(var3);
      var3.fromField.focus();
      this.scrollToEnd = true;
      this.save();
   }

   boolean closing() {
      return this.closing;
   }

   boolean finished() {
      return this.closing && this.open.value() < 0.02F;
   }

   void close() {
      if (!this.closing) {
         this.closing = true;
         this.open.target(0.0F);

         for (StudioPreview.TypeS var2 : this.lines) {
            var2.blur();
         }

         this.lines.removeIf(StudioPreview.TypeS::blank);
         this.save();
         SoundRequest.play(SoundEvent.COLLAPSE);
      }
   }

   void render(DrawContext var1) {
      Canvas var2 = var1.canvas;
      float var3 = this.open.update(MotionStyle.PANEL.dt(var1.dt));
      float var4 = Math.max(0.0F, Math.min(1.0F, var3));
      float var5 = var2.width();
      float var6 = var2.height();
      float var7 = 0.0F;

      for (StudioPreview.TypeS var9 : this.lines) {
         var7 += 50.0F * var9.presence.target(1.0F).update(MotionStyle.PANEL.dt(var1.dt));
      }

      if (this.lines.isEmpty()) {
         var7 = 136.0F;
      }

      float var28 = 76.0F;
      float var29 = var6 - 34.0F;
      this.pw = Math.min(580.0F, var5 - 120.0F);
      this.ph = Math.min(var29 - var28, 134.0F + var7 + 64.0F + 8.0F);
      this.px = var5 / 2.0F - this.pw / 2.0F;
      this.py = Math.max(var28, Math.min(var6 / 2.0F - this.ph / 2.0F, var29 - this.ph)) + (1.0F - var3) * 18.0F;
      var2.rect(0.0F, 0.0F, var5, var6, BlendMode.withAlpha(Theme.tint(-16514294), 0.34F * var4));
      var2.captureBackdrop();
      var2.pushAlpha(var4);
      KeyLabel.surface(var2, this.px, this.py, this.pw, this.ph, 24.0F, 1.0F, 0.3F);
      boolean var10 = !this.closing && var4 > 0.9F;
      boolean var11 = this.single();
      String var12 = this.players != null ? this.players.name() : "Custom names";
      String var13 = this.players != null ? this.players.description() : "Show chosen players under a name you pick";
      KeyLabel.iconTile(var2, this.px + 28.0F, this.py + 28.0F, 48.0F, 14.0F, var11 ? "users-round" : "user-round-pen", 1.0F, 0.0F);
      var2.text(Fonts.SEMIBOLD, var12, this.px + 28.0F + 64.0F, this.py + 30.0F, 21.0F, Icons.TEXT);
      if (var13 != null) {
         var2.text(Fonts.REGULAR, var13, this.px + 28.0F + 64.0F, this.py + 59.0F, 12.5F, Icons.TEXT_2);
      }

      this.closeButton(var1, var2, var10);
      float var14 = this.pw - 56.0F;
      float var15 = var11 ? var14 - 30.0F - 8.0F : (var14 - 30.0F - 8.0F - 30.0F) / 2.0F;
      float var16 = this.px + 28.0F;
      float var17 = var16 + var15 + 30.0F;
      float var18 = this.py + 104.0F + 15.0F - 2.0F;
      var2.rect(this.px + 28.0F, this.py + 104.0F - 0.5F, var14, 1.0F, 318767103);
      label(var2, "Player", var16 + 2.0F, var18);
      if (!var11) {
         label(var2, "Shown as", var17 + 2.0F, var18);
      }

      this.listTop = this.py + 104.0F + 30.0F;
      this.listBottom = this.py + this.ph - 64.0F;
      float var19 = this.listBottom - this.listTop;
      this.maxScroll = Math.max(0.0F, (this.lines.isEmpty() ? 136.0F : this.lines.size() * 50.0F) - var19);
      if (this.scrollToEnd) {
         this.scrollTarget = this.maxScroll;
         this.scrollToEnd = false;
      }

      this.scrollTarget = Math.max(0.0F, Math.min(this.maxScroll, this.scrollTarget));
      float var20 = this.scroll.target(this.scrollTarget).update(MotionStyle.PANEL.dt(var1.dt));
      var2.pushClip(this.px, this.listTop, this.pw, var19);
      if (this.lines.isEmpty()) {
         this.empty(var2, this.listTop + var19 / 2.0F);
      }

      float var21 = this.listTop - var20;

      for (StudioPreview.TypeS var23 : this.lines) {
         float var24 = 50.0F * var23.presence.value();
         var23.y = var21;
         if (var21 + var24 > this.listTop - 4.0F && var21 < this.listBottom + 4.0F) {
            float var25 = var23.presence.value();
            var2.pushAlpha(var25);
            float var26 = var21 + 25.0F + (1.0F - var25) * 6.0F;
            boolean var27 = var10 && var26 > this.listTop && var26 < this.listBottom;
            var23.fromField.render(var1, var16, var26, var15, var27);
            if (var23.toField != null) {
               var2.icon(
                  "arrow-right",
                  var16 + var15 + 15.0F,
                  var26,
                  14.0F,
                  BlendMode.withAlpha(!var23.from.isBlank() && !var23.to.isBlank() ? Icons.PURPLE : Icons.TEXT_3, 0.9F)
               );
               var23.toField.render(var1, var17, var26, var15, var27);
            }

            this.trash(var1, var2, var23, this.px + this.pw - 28.0F - 15.0F, var26, var27);
            var2.popAlpha();
         }

         var21 += var24;
      }

      var2.popClip();
      this.footer(var1, var2, var10);
      var2.popAlpha();
   }

   private static void label(Canvas var0, String var1, float var2, float var3) {
      var0.text(Fonts.SEMIBOLD, var1.toUpperCase(Locale.ROOT), var2, var3, 10.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE, 0.14F);
   }

   private void empty(Canvas var1, float var2) {
      float var3 = this.px + this.pw / 2.0F;
      var1.circle(var3, var2 - 30.0F, 22.0F, 285212671);
      var1.icon("users-round", var3, var2 - 30.0F, 20.0F, Icons.TEXT_2);
      boolean var4 = this.single();
      var1.text(Fonts.SEMIBOLD, var4 ? "No players yet" : "No custom names yet", var3, var2 + 8.0F, 14.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
      var1.text(
         Fonts.REGULAR,
         var4 ? "Add as many players as you like." : "Add a player and the name you want to see instead.",
         var3,
         var2 + 30.0F,
         12.0F,
         Icons.TEXT_3,
         FontStyle.CENTER,
         FontWeight.MIDDLE
      );
   }

   private void trash(DrawContext var1, Canvas var2, StudioPreview.TypeS var3, float var4, float var5, boolean var6) {
      boolean var7 = var6 && var1.hover(var3.trashKey, var4 - 15.0F, var5 - 15.0F, 30.0F, 30.0F, DrawContext.TypeE.HAND);
      float var8 = var3.trashHover.target(var7 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      if (var8 > 0.01F) {
         var2.circle(var4, var5, 15.0F, BlendMode.withAlpha(Icons.PINK, 0.14F * var8));
      }

      var2.icon("trash-2", var4, var5, 14.0F, BlendMode.lerp(Icons.TEXT_3, Icons.PINK, var8));
   }

   private void closeButton(DrawContext var1, Canvas var2, boolean var3) {
      float var4 = 30.0F;
      float var5 = this.px + this.pw - 20.0F - var4;
      float var6 = this.py + 20.0F;
      boolean var7 = var3 && var1.hover(this.closeKey, var5, var6, var4, var4, DrawContext.TypeE.HAND);
      float var8 = this.closeHover.target(var7 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      var2.circle(var5 + var4 / 2.0F, var6 + var4 / 2.0F, var4 / 2.0F, BlendMode.withAlpha(-1, 0.05F + 0.08F * var8));
      var2.icon("x", var5 + var4 / 2.0F, var6 + var4 / 2.0F, 15.0F, BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var8));
   }

   private void footer(DrawContext var1, Canvas var2, boolean var3) {
      float var4 = this.py + this.ph - 64.0F;
      var2.rect(this.px + 28.0F, var4, this.pw - 56.0F, 1.0F, 318767103);
      float var5 = var4 + 32.0F;
      String var6 = "Add player";
      this.addH = 34.0F;
      this.addW = var2.textWidth(Fonts.SEMIBOLD, var6, 12.5F) + 48.0F;
      this.addX = this.px + 28.0F;
      this.addY = var5 - this.addH / 2.0F;
      boolean var7 = var3 && var1.hover(this.addKey, this.addX, this.addY, this.addW, this.addH, DrawContext.TypeE.HAND);
      float var8 = this.addHover.target(var7 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      var2.glow(this.addX, this.addY, this.addW, this.addH, this.addH / 2.0F, 18.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.18F + 0.22F * var8));
      var2.roundRectGradient(this.addX, this.addY, this.addW, this.addH, this.addH / 2.0F, 135.0F, Icons.INDIGO, Icons.MAGENTA);
      var2.roundRectCorners(this.addX, this.addY, this.addW, this.addH, this.addH / 2.0F, 872415231, 352321535, 16777215, 16777215);
      var2.icon("plus", this.addX + 20.0F, var5, 14.0F, Icons.TEXT);
      var2.text(Fonts.SEMIBOLD, var6, this.addX + 34.0F, var5, 12.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      float var9 = this.px + this.pw - 28.0F;
      float var10 = var2.textWidth(Fonts.MEDIUM, "to go back", 11.0F);
      var2.text(Fonts.MEDIUM, "to go back", var9, var5, 11.0F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.MIDDLE);
      KeyLabel.keyCap(var2, "Esc", var9 - var10 - 8.0F - 32.0F, var5, 9.5F, Icons.TEXT_2);
   }

   void mouseDown(float var1, float var2, int var3) {
      if (!this.closing) {
         for (StudioPreview.TypeS var5 : this.lines) {
            if (!var5.fromField.contains(var1, var2)) {
               var5.fromField.blur();
            }

            if (var5.toField != null && !var5.toField.contains(var1, var2)) {
               var5.toField.blur();
            }
         }

         if (!(var1 < this.px) && !(var1 > this.px + this.pw) && !(var2 < this.py) && !(var2 > this.py + this.ph)) {
            float var11 = 30.0F;
            float var12 = this.px + this.pw - 20.0F - var11;
            float var6 = this.py + 20.0F;
            if (var1 >= var12 && var1 <= var12 + var11 && var2 >= var6 && var2 <= var6 + var11) {
               this.close();
            } else if (var1 >= this.addX && var1 <= this.addX + this.addW && var2 >= this.addY && var2 <= this.addY + this.addH) {
               this.add();
               SoundRequest.play(SoundEvent.CLICK);
            } else if (!(var2 < this.listTop) && !(var2 > this.listBottom)) {
               for (StudioPreview.TypeS var8 : this.lines) {
                  if (var8.fromField.mouseDown(var1, var2) || var8.toField != null && var8.toField.mouseDown(var1, var2)) {
                     return;
                  }

                  float var9 = this.px + this.pw - 28.0F - 15.0F;
                  float var10 = var8.y + 25.0F;
                  if (Math.abs(var1 - var9) <= 15.0F && Math.abs(var2 - var10) <= 15.0F) {
                     this.lines.remove(var8);
                     this.save();
                     SoundRequest.play(SoundEvent.TOGGLE_OFF);
                     return;
                  }
               }
            }
         } else {
            this.close();
         }
      }
   }

   void mouseDrag(float var1, float var2) {
      for (StudioPreview.TypeS var4 : this.lines) {
         var4.fromField.mouseDrag(var1, var2);
         if (var4.toField != null) {
            var4.toField.mouseDrag(var1, var2);
         }
      }
   }

   void mouseUp() {
      for (StudioPreview.TypeS var2 : this.lines) {
         var2.fromField.mouseUp();
         if (var2.toField != null) {
            var2.toField.mouseUp();
         }
      }
   }

   void scroll(double var1) {
      this.scrollTarget = (float)Math.max(0.0, Math.min((double)this.maxScroll, this.scrollTarget - var1 * 40.0));
   }

   void keyPressed(int var1) {
      for (StudioPreview.TypeS var3 : this.lines) {
         if (var3.fromField.focused()) {
            var3.fromField.keyPressed(var1);
            return;
         }

         if (var3.toField != null && var3.toField.focused()) {
            var3.toField.keyPressed(var1);
            return;
         }
      }

      switch (var1) {
         case 256:
            this.close();
            break;
         case 257:
         case 335:
            this.add();
            SoundRequest.play(SoundEvent.CLICK);
      }
   }

   void charTyped(int var1) {
      for (StudioPreview.TypeS var3 : this.lines) {
         if (var3.fromField.charTyped(var1) || var3.toField != null && var3.toField.charTyped(var1)) {
            return;
         }
      }
   }

   private final class TypeS {
      String from;
      String to;
      final Slider fromField;
      
      final Slider toField;
      final Animator presence = Animator.smooth(0.0F);
      final Animator trashHover = Animator.smooth(0.0F);
      final Object trashKey = new Object();
      float y;

      TypeS(String nullx, String nullxx) {
         this.from = nullx;
         this.to = nullxx;
         ChoiceListSetting var4 = StudioPreview.this.players;
         String var5 = var4 != null ? var4.placeholder() : "Name, like Henk";
         this.fromField = new Slider(StudioPreview.this.model(() -> this.from, var1 -> this.from = var1, var5, false)).suggestions(Marker::knownNames);
         if (var4 != null) {
            this.toField = null;
            this.fromField.onSubmit(() -> {
               if (!this.from.isBlank() && StudioPreview.this.lines.getLast() == this) {
                  StudioPreview.this.add();
               }
            });
         } else {
            Slider var6 = new Slider(StudioPreview.this.model(() -> this.to, var1 -> this.to = var1, "Shown as, like Pieter", true));
            this.toField = var6;
            Slider var7 = this.fromField;
            var7.onSubmit(var6::focus);
         }
      }

      void blur() {
         this.fromField.blur();
         if (this.toField != null) {
            this.toField.blur();
         }
      }

      boolean blank() {
         return this.from.isBlank() && this.to.isBlank();
      }
   }
}
