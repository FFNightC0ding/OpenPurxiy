// Sourced By DevFFNightmare
package dev.purxiy.ui.clickgui;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ChoiceListSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.IntegerSetting;
import dev.purxiy.config.KeybindSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.PaletteSetting;
import dev.purxiy.config.Setting;
import dev.purxiy.config.TextSetting;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.CosmeticCatalog;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.modules.Module;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.texture.TextureLoader;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.KeyLabel;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.widget.ColorPicker;
import dev.purxiy.ui.widget.Divider;
import dev.purxiy.ui.widget.KeybindButton;
import dev.purxiy.ui.widget.Label;
import dev.purxiy.ui.widget.ModeButton;
import dev.purxiy.ui.widget.Pagination;
import dev.purxiy.ui.widget.PalettePicker;
import dev.purxiy.ui.widget.Slider;
import dev.purxiy.ui.widget.Spacer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

final class ModuleList {
   private static final float MAX_WIDTH = 620.0F;
   private static final float HEADER = 108.0F;
   private static final float FOOTER = 52.0F;
   private static final float PAD = 28.0F;
   private static final float ROW = 58.0F;
   private static final float DOCK_WIDTH = 440.0F;
   private static final float DOCK_HEADER = 84.0F;
   private static final float DOCK_MARGIN = 22.0F;
   private static final float DOCK_WIDTH_RIGHT = 392.0F;
   final Module module;
   private final ModuleRow origin;
   private final Animator open = new Animator(0.0F, 250.0F, 29.0F);
   private final Animator scroll = Animator.smooth(0.0F);
   private final Animator closeHover = Animator.smooth(0.0F);
   private final Animator resetHover = Animator.smooth(0.0F);
   private final List<ModuleList.TypeU> rows = new ArrayList<>();
   private final Object closeKey = new Object();
   private final Object resetKey = new Object();
   private boolean closing;
   private float scrollTarget;
   private float maxScroll;
   private float px;
   private float py;
   private float pw;
   private float ph;
   private float rowsTop;
   private float rowsBottom;
   
   private StudioPreview sheet;
   
   private SettingEditor picker;
   private final Animator dock = new Animator(0.0F, 190.0F, 25.0F);
   private boolean studioOpen;
   private final Animator live = Animator.smooth(0.0F);

   ModuleList(Module var1, ModuleRow var2) {
      this.module = var1;
      this.origin = var2;
      this.open.target(1.0F);
      if (var1.toggleable()) {
         List var3 = this.rows;
         String var4 = "Turn " + var1.name() + " on or off";
         BooleanSupplier var5 = var1::switchedOn;
         var3.add(new ModuleList.TypeF("Enabled", var4, var5, var1::setEnabled, () -> true));
      }

      if (CosmeticRegistry.supportsEditor(var1.id())) {
         this.rows.add(new ModuleList.TypeE(var1));
      }

      if (CosmeticCatalog.supports(var1.id())) {
         this.rows.add(new ModuleList.TypeW(var1.id()));
      }

      ModuleList.TypeB var21 = null;

      for (Setting var23 : var1.settings()) {
         String var6 = var1.sectionBefore(var23);
         if (var6 != null) {
            var21 = new ModuleList.TypeB(var6);
            this.rows.add(var21);
         }

         Object var7 = null;
         if (var23 instanceof BooleanSetting var8) {
            String var17 = var8.name();
            String var18 = var8.description();
            BooleanSupplier var19 = var8::enabled;
            Consumer var20 = v -> var8.set((Boolean)v);
            var7 = new ModuleList.TypeF(var17, var18, var19, var20, var8::visible);
         } else if (var23 instanceof NumberSetting var9) {
            var7 = var9.slots() != null ? new ModuleList.TypeD(var9) : new ModuleList.TypeM(var9);
         } else if (var23 instanceof ModeSetting var10) {
            var7 = var1.id().equals("interface") && var23.id().equals("logo") ? new ModuleList.TypeL(var10) : new ModuleList.TypeV(var10);
         } else if (var23 instanceof ColorSetting var11) {
            var7 = var1.id().equals("interface") && var23.id().equals("accent") ? new ModuleList.TypeJ(var11) : new ModuleList.TypeH(var11);
         } else if (var23 instanceof TextSetting var12) {
            var7 = new ModuleList.TypeI(var12);
         } else if (var23 instanceof KeybindSetting var13) {
            var7 = new ModuleList.TypeC(var13, () -> this.openSheet(() -> new StudioPreview(var13)), () -> var13.get().size() + " names");
         } else if (var23 instanceof ChoiceListSetting var14) {
            var7 = new ModuleList.TypeC(var14, () -> this.openSheet(() -> new StudioPreview(var14)), () -> var14.names().size() + " players");
         } else if (var23 instanceof PaletteSetting var15) {
            var7 = new ModuleList.TypeO(var15, this::openPicker, () -> this.picker);
         } else if (var23 instanceof IntegerSetting var16) {
            var7 = new ModuleList.TypeY(var16);
         }

         if (var7 != null) {
            this.rows.add((ModuleList.TypeU)var7);
            if (var21 != null) {
               var21.members.add((ModuleList.TypeU)var7);
            }
         }
      }

      if (var1.toggleable()) {
         this.rows.add(new ModuleList.TypeY(var1));
      }
   }

   float progress() {
      return this.open.value();
   }

   boolean closing() {
      return this.closing;
   }

   boolean finished() {
      return this.closing && this.open.value() < 0.015F;
   }

   void close() {
      this.closeStudio();
      if (this.sheet != null) {
         this.sheet.close();
      }

      if (this.picker != null) {
         this.picker.close();
      }

      if (!this.closing) {
         this.closing = true;
         this.open.target(0.0F);
         SoundRequest.play(SoundEvent.COLLAPSE);
      }
   }

   
   Studio studio() {
      return this.studioOpen ? this.module.studio() : null;
   }

   float docked() {
      return Math.max(0.0F, Math.min(1.0F, this.dock.value()));
   }

   boolean contains(float var1, float var2) {
      return var1 >= this.px && var1 <= this.px + this.pw && var2 >= this.py && var2 <= this.py + this.ph;
   }

   float right() {
      return this.px + this.pw;
   }

   float left() {
      return this.px;
   }

   void openStudio() {
      Studio var1 = this.module.studio();
      if (var1 != null && !this.studioOpen && !this.closing) {
         this.studioOpen = true;
         this.dock.target(1.0F);
         this.scrollTarget = 0.0F;
         var1.setOpen(true);
         SoundRequest.play(SoundEvent.EXPAND);
      }
   }

   void closeStudio() {
      Studio var1 = this.module.studio();
      if (this.studioOpen && var1 != null) {
         this.studioOpen = false;
         this.dock.target(0.0F);
         var1.setOpen(false);
         SoundRequest.play(SoundEvent.COLLAPSE);
      }
   }

   private void openSheet(Supplier<StudioPreview> var1) {
      if (this.sheet == null && !this.closing) {
         this.sheet = (StudioPreview)var1.get();
         SoundRequest.play(SoundEvent.EXPAND);
      }
   }

   private void openPicker(PaletteSetting var1, float[] var2) {
      if (this.picker == null && this.sheet == null && !this.closing) {
         this.picker = new SettingEditor(var1, var2[0], var2[1], var2[2], var2[3]);
         SoundRequest.play(SoundEvent.EXPAND);
      }
   }

   
   private SettingEditor activePicker() {
      return this.picker != null && !this.picker.closing() ? this.picker : null;
   }

   
   private StudioPreview activeSheet() {
      return this.sheet != null && !this.sheet.closing() ? this.sheet : null;
   }

   boolean busy() {
      if (this.sheet == null && this.picker == null) {
         for (ModuleList.TypeU var2 : this.rows) {
            if (var2.capturesKeys()) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   boolean creamyPreviewAt(float var1, float var2) {
      if (!this.closing && this.module.id().equals("creamy_keys") && !(var2 < this.rowsTop) && !(var2 >= this.rowsBottom)) {
         for (ModuleList.TypeU var4 : this.rows) {
            if (var4 instanceof ModuleList.TypeW && var2 >= var4.y && var2 < var4.y + var4.height()) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   void render(DrawContext var1) {
      boolean var2 = var1.masked();
      if (this.sheet != null || this.picker != null) {
         var1.mask(true);
      }

      this.renderPanel(var1);
      var1.mask(var2);
      if (this.sheet != null) {
         this.sheet.render(var1);
         if (this.sheet.finished()) {
            this.sheet = null;
         }
      }

      if (this.picker != null) {
         this.picker.render(var1);
         if (this.picker.finished()) {
            this.picker = null;
         }
      }
   }

   private void renderPanel(DrawContext var1) {
      Canvas var2 = var1.canvas;
      float var3 = this.open.update(MotionStyle.PANEL.dt(var1.dt));
      float var4 = Math.max(0.0F, Math.min(1.0F, var3));
      float var5 = var2.width();
      float var6 = var2.height();
      float var7 = 0.0F;

      for (ModuleList.TypeU var9 : this.rows) {
         var9.presence.target(var9.visible() ? 1.0F : 0.0F).update(MotionStyle.PANEL.dt(var1.dt));
         var7 += var9.height();
      }

      float var41 = this.dock.update(MotionStyle.PANEL.dt(var1.dt));
      float var42 = Math.max(0.0F, Math.min(1.0F, var41));
      float var10 = lerp(108.0F, 84.0F, var42);
      float var11 = 76.0F;
      float var12 = var6 - 34.0F;
      float var13 = Math.min(620.0F, var5 - 96.0F);
      float var14 = Math.min(var12 - var11, 108.0F + var7 + 52.0F + 8.0F);
      Studio var15 = this.module.studio();
      Studio.TypeQ var16 = var15 != null ? var15.dock() : Studio.TypeQ.TOP_LEFT;
      boolean var17 = var16 == Studio.TypeQ.RIGHT;
      float var18 = var17 ? Math.min(392.0F, var5 * 0.34F) : Math.min(440.0F, var5 * 0.42F);
      float var19 = Math.min(var6 - 44.0F, 84.0F + var7 + 52.0F + 8.0F);
      this.pw = lerp(var13, var18, var41);
      this.ph = lerp(var14, var19, var41);
      this.px = lerp(var5 / 2.0F - var13 / 2.0F, var16.right() ? var5 - 22.0F - var18 : 22.0F, var41);
      this.py = lerp(Math.max(var11, Math.min(var6 / 2.0F - var14 / 2.0F, var12 - var14)), var17 ? Math.max(22.0F, var6 / 2.0F - var19 / 2.0F) : 22.0F, var41);
      var2.rect(0.0F, 0.0F, var5, var6, BlendMode.withAlpha(Theme.tint(-16514294), 0.38F * var4 * (1.0F - var42)));
      var2.captureBackdrop();
      ModuleRow var20 = this.origin;
      float var21 = lerp(var20.x, this.px, var3);
      float var22 = lerp(var20.y, this.py, var3);
      float var23 = lerp(var20.w, this.pw, var3);
      float var24 = lerp(112.0F, this.ph, var3);
      float var25 = lerp(18.0F, 24.0F, var4);
      KeyLabel.surface(var2, var21, var22, var23, var24, var25, lerp(0.3F, 1.0F, var4), this.module.switchedOn() ? 0.35F : 0.12F);
      if (var42 > 0.01F) {
         var2.roundRect(var21, var22, var23, var24, var25, BlendMode.withAlpha(Theme.tint(-16119788), 0.5F * var42));
      }

      float var26 = lerp(28.0F, 22.0F, var42);
      float var27 = lerp(40.0F, lerp(52.0F, 42.0F, var42), var3);
      KeyLabel.iconTile(
         var2,
         lerp(var20.x + 18.0F, this.px + var26, var3),
         lerp(var20.y + 18.0F, this.py + var26, var3),
         var27,
         lerp(12.0F, lerp(15.0F, 13.0F, var42), var3),
         this.module.icon(),
         this.module.switchedOn() ? 1.0F : 0.0F,
         0.0F
      );
      float var28 = this.px + var26 + lerp(70.0F, 56.0F, var42);
      float var29 = lerp(14.5F, lerp(22.0F, 18.0F, var42), var3);
      float var30 = lerp(var20.y + 66.0F, this.py + lerp(29.0F, 23.0F, var42), var3);
      var2.text(Fonts.SEMIBOLD, this.module.name(), lerp(var20.x + 18.0F, var28, var3), var30, var29, Icons.TEXT);
      float var31 = lerp(11.5F, lerp(12.5F, 11.5F, var42), var3);
      float var32 = lerp(var20.w - 36.0F, this.pw - var26 * 2.0F - lerp(70.0F, 56.0F, var42) - 48.0F, var3);
      var2.text(
         Fonts.REGULAR,
         FontMetrics.ellipsize(Fonts.REGULAR, this.module.description(), var31, var32, 0.0F),
         lerp(var20.x + 18.0F, var28, var3),
         lerp(var20.y + 86.0F, this.py + lerp(61.0F, 49.0F, var42), var3),
         var31,
         Icons.TEXT_2
      );
      this.livePill(var1, var2, var28 + var2.textWidth(Fonts.SEMIBOLD, this.module.name(), var29) + 10.0F, var30 + var29 * 0.5F);
      float var33 = smooth((var3 - 0.45F) / 0.55F);
      if (!(var33 <= 0.01F)) {
         var2.pushAlpha(var33);
         this.closeButton(var1, var2);
         this.rowsTop = this.py + var10;
         this.rowsBottom = this.py + this.ph - 52.0F;
         var2.rect(this.px + 28.0F, this.rowsTop - 0.5F, this.pw - 56.0F, 1.0F, 318767103);
         float var34 = this.rowsBottom - this.rowsTop;
         this.maxScroll = Math.max(0.0F, var7 - var34);
         this.scrollTarget = Math.max(0.0F, Math.min(this.maxScroll, this.scrollTarget));
         float var35 = this.scroll.target(this.scrollTarget).update(MotionStyle.PANEL.dt(var1.dt));
         var2.pushClip(this.px, this.rowsTop, this.pw, var34);
         float var36 = this.rowsTop - var35;

         for (int var37 = 0; var37 < this.rows.size(); var37++) {
            ModuleList.TypeU var38 = this.rows.get(var37);
            float var39 = var38.height();
            if (var39 > 0.5F && var36 + var39 > this.rowsTop - 4.0F && var36 < this.rowsBottom + 4.0F) {
               float var40 = (1.0F - var33) * (8 + var37 * 4);
               var2.pushAlpha(var38.presence.value());
               var38.render(var1, this.px + 28.0F, var36 + var40, this.pw - 56.0F, this.closing ? 0.0F : 1.0F);
               var2.popAlpha();
               if (var37 < this.rows.size() - 1 && var39 > 1.0F && !(var38 instanceof ModuleList.TypeB)) {
                  var2.rect(this.px + 28.0F, var36 + var39 - 0.5F, this.pw - 56.0F, 1.0F, BlendMode.fade(268435455, var38.presence.value()));
               }
            }

            var38.y = var36;
            var36 += var39;
         }

         var2.popClip();
         this.footer(var1, var2);
         var2.popAlpha();
      }
   }

   private void livePill(DrawContext var1, Canvas var2, float var3, float var4) {
      float var5 = this.live.target(this.studioOpen ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      if (!(var5 <= 0.01F)) {
         var2.pushAlpha(var5);
         float var6 = var2.textWidth(Fonts.SEMIBOLD, "LIVE", 9.0F);
         float var7 = var6 + 24.0F;
         float var8 = 18.0F;
         float var9 = var4 - var8 / 2.0F;
         var2.roundRect(var3, var9, var7, var8, var8 / 2.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.16F));
         var2.roundRectOutline(var3, var9, var7, var8, var8 / 2.0F, 1.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.5F));
         float var10 = 0.55F + 0.45F * (float)Math.sin(var1.time * 3.2F);
         var2.glowCircle(var3 + 9.0F, var4, 2.5F, 6.0F, BlendMode.withAlpha(Icons.PINK, 0.5F * var10));
         var2.circle(var3 + 9.0F, var4, 2.5F, BlendMode.lerp(Icons.MAGENTA, Icons.PINK, var10));
         var2.text(Fonts.SEMIBOLD, "LIVE", var3 + 16.0F, var4, 9.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE, 0.08F);
         var2.popAlpha();
      }
   }

   private void closeButton(DrawContext var1, Canvas var2) {
      float var3 = 30.0F;
      float var4 = this.px + this.pw - 20.0F - var3;
      float var5 = this.py + 20.0F;
      boolean var6 = var1.hover(this.closeKey, var4, var5, var3, var3, DrawContext.TypeE.HAND);
      float var7 = this.closeHover.target(var6 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      var2.circle(var4 + var3 / 2.0F, var5 + var3 / 2.0F, var3 / 2.0F, BlendMode.withAlpha(-1, 0.05F + 0.08F * var7));
      var2.icon("x", var4 + var3 / 2.0F, var5 + var3 / 2.0F, 15.0F, BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var7));
   }

   private void footer(DrawContext var1, Canvas var2) {
      float var3 = this.py + this.ph - 52.0F;
      var2.rect(this.px + 28.0F, var3, this.pw - 56.0F, 1.0F, 318767103);
      float var4 = var3 + 26.0F;
      boolean var5 = false;

      for (Setting var7 : this.module.settings()) {
         var5 |= !var7.isDefault();
      }

      String var12 = "Reset to defaults";
      float var14 = var2.textWidth(Fonts.MEDIUM, var12, 12.0F);
      boolean var8 = var5 && var1.hover(this.resetKey, this.px + 28.0F - 6.0F, var4 - 14.0F, var14 + 32.0F, 28.0F, DrawContext.TypeE.HAND);
      float var9 = this.resetHover.target(var8 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      int var10 = var5 ? BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var9) : BlendMode.withAlpha(Icons.TEXT_3, 0.6F);
      var2.icon("rotate-ccw", this.px + 28.0F + 7.0F, var4, 13.0F, var10);
      var2.text(Fonts.MEDIUM, var12, this.px + 28.0F + 20.0F, var4, 12.0F, var10, FontStyle.LEFT, FontWeight.MIDDLE);
      float var11 = this.px + this.pw - 28.0F;
      var12 = this.studioOpen ? "to go back" : "to close";
      var14 = var2.textWidth(Fonts.MEDIUM, var12, 11.0F);
      var2.text(Fonts.MEDIUM, var12, var11, var4, 11.0F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.MIDDLE);
      KeyLabel.keyCap(var2, "Esc", var11 - var14 - 8.0F - 32.0F, var4, 9.5F, Icons.TEXT_2);
   }

   boolean mouseDown(float var1, float var2, int var3) {
      if (this.closing) {
         return true;
      } else {
         StudioPreview var4 = this.activeSheet();
         if (var4 != null) {
            var4.mouseDown(var1, var2, var3);
            return true;
         } else if (this.picker != null) {
            this.picker.mouseDown(var1, var2, var3);
            return true;
         } else {
            for (ModuleList.TypeU var6 : this.rows) {
               if (var6 instanceof ModuleList.TypeI var7 && !var7.field.contains(var1, var2)) {
                  var7.field.blur();
               }
            }

            if (!(var1 < this.px) && !(var1 > this.px + this.pw) && !(var2 < this.py) && !(var2 > this.py + this.ph)) {
               float var12 = 30.0F;
               float var13 = this.px + this.pw - 20.0F - var12;
               float var14 = this.py + 20.0F;
               if (var1 >= var13 && var1 <= var13 + var12 && var2 >= var14 && var2 <= var14 + var12) {
                  if (this.studioOpen) {
                     this.closeStudio();
                  } else {
                     this.close();
                  }

                  return true;
               } else {
                  float var8 = this.py + this.ph - 26.0F;
                  if (var2 > var8 - 14.0F && var2 < var8 + 14.0F && var1 < this.px + 28.0F + 150.0F) {
                     boolean var16 = false;

                     for (Setting var11 : this.module.settings()) {
                        var16 |= !var11.isDefault();
                        var11.reset();
                     }

                     if (var16) {
                        SoundRequest.play(SoundEvent.CLICK);
                     }

                     return true;
                  } else {
                     if (var2 >= this.rowsTop && var2 <= this.rowsBottom) {
                        for (ModuleList.TypeU var10 : this.rows) {
                           if (var10.presence.value() > 0.5F && var10.mouseDown(var1, var2, var3)) {
                              return true;
                           }
                        }
                     }

                     for (ModuleList.TypeU var17 : this.rows) {
                        var17.clickedElsewhere();
                     }

                     return true;
                  }
               }
            } else {
               if (!this.studioOpen) {
                  this.close();
               }

               return true;
            }
         }
      }
   }

   void mouseDrag(float var1, float var2) {
      if (this.sheet != null) {
         this.sheet.mouseDrag(var1, var2);
      }

      if (this.picker != null) {
         this.picker.mouseDrag(var1, var2);
      }

      for (ModuleList.TypeU var4 : this.rows) {
         var4.mouseDrag(var1, var2);
      }
   }

   void mouseUp() {
      if (this.sheet != null) {
         this.sheet.mouseUp();
      }

      if (this.picker != null) {
         this.picker.mouseUp();
      }

      for (ModuleList.TypeU var2 : this.rows) {
         var2.mouseUp();
      }
   }

   void scroll(double var1) {
      StudioPreview var3 = this.activeSheet();
      if (var3 != null) {
         var3.scroll(var1);
      } else if (this.picker != null) {
         this.picker.scroll(var1);
      } else {
         this.scrollTarget = (float)Math.max(0.0, Math.min((double)this.maxScroll, this.scrollTarget - var1 * 48.0));
      }
   }

   boolean keyPressed(int var1) {
      StudioPreview var2 = this.activeSheet();
      if (var2 != null) {
         var2.keyPressed(var1);
         return true;
      } else if (this.picker != null) {
         SettingEditor var5 = this.activePicker();
         if (var5 != null) {
            var5.keyPressed(var1);
         }

         return true;
      } else {
         for (ModuleList.TypeU var4 : this.rows) {
            if (var4.keyPressed(var1)) {
               return true;
            }
         }

         if (var1 == 256) {
            if (this.studioOpen) {
               this.closeStudio();
            } else {
               this.close();
            }

            return true;
         } else {
            return false;
         }
      }
   }

   boolean charTyped(int var1) {
      StudioPreview var2 = this.activeSheet();
      if (var2 != null) {
         var2.charTyped(var1);
         return true;
      } else if (this.picker != null) {
         SettingEditor var5 = this.activePicker();
         if (var5 != null) {
            var5.charTyped(var1);
         }

         return true;
      } else {
         for (ModuleList.TypeU var4 : this.rows) {
            if (var4.charTyped(var1)) {
               return true;
            }
         }

         return false;
      }
   }

   private static float lerp(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   private static float smooth(float var0) {
      var0 = var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static final class TypeB extends ModuleList.TypeU {
      private static final float HEIGHT = 40.0F;
      final List<ModuleList.TypeU> members = new ArrayList<>();

      TypeB(String var1) {
         super(var1, (String)null);
      }

      @Override
      float baseHeight() {
         return 40.0F;
      }

      @Override
      boolean visible() {
         for (ModuleList.TypeU var2 : this.members) {
            if (var2.visible()) {
               return true;
            }
         }

         return false;
      }

      @Override
      void render(DrawContext var1, float var2, float var3, float var4, float var5) {
         Canvas var6 = var1.canvas;
         float var7 = var3 + 40.0F - 13.0F;
         var6.circle(var2 + 2.5F, var7, 2.5F, BlendMode.withAlpha(Icons.PURPLE, 0.9F));
         var6.text(Fonts.SEMIBOLD, this.label.toUpperCase(Locale.ROOT), var2 + 12.0F, var7, 10.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE, 0.14F);
      }
   }

   private static final class TypeC extends ModuleList.TypeU {
      private final Setting<?> setting;
      private final Runnable open;
      private final Supplier<String> label;
      private final Animator hover = Animator.smooth(0.0F);
      private final Object key = new Object();
      private float x;
      private float w;

      TypeC(Setting<?> var1, Runnable var2, Supplier<String> var3) {
         super(var1.name(), var1.description());
         this.setting = var1;
         this.open = var2;
         this.label = var3;
      }

      @Override
      boolean visible() {
         return this.setting.visible();
      }

      @Override
      void render(DrawContext var1, float var2, float var3, float var4, float var5) {
         this.x = var2;
         this.w = var4;
         super.render(var1, var2, var3, var4, var5);
         Canvas var6 = var1.canvas;
         boolean var7 = var5 > 0.0F && var1.hover(this.key, var2, var3, var4, 58.0F, DrawContext.TypeE.HAND);
         float var8 = this.hover.target(var7 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         String var9 = this.label.get();
         boolean var10 = var9.startsWith("Add ");
         float var11 = var3 + 29.0F;
         float var12 = 30.0F;
         float var13 = var6.textWidth(Fonts.SEMIBOLD, var9, 12.0F) + 44.0F;
         float var14 = var2 + var4 - var13;
         float var15 = var11 - var12 / 2.0F;
         var6.roundRect(var14, var15, var13, var12, 10.0F, BlendMode.lerp(318767103, 486539263, var8));
         var6.roundRectOutline(var14, var15, var13, var12, 10.0F, 1.0F, BlendMode.lerp(486539263, BlendMode.withAlpha(Icons.PURPLE, 0.65F), var8));
         var6.text(Fonts.SEMIBOLD, var9, var14 + 14.0F, var11, 12.0F, var10 ? Icons.TEXT_2 : Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
         var6.icon("chevron-right", var14 + var13 - 16.0F, var11, 13.0F, BlendMode.lerp(Icons.TEXT_3, Icons.TEXT, var8));
      }

      @Override
      boolean mouseDown(float var1, float var2, int var3) {
         if (var2 >= this.y && var2 <= this.y + 58.0F && var1 >= this.x && var1 <= this.x + this.w) {
            this.open.run();
            return true;
         } else {
            return false;
         }
      }
   }

   private static final class TypeD extends ModuleList.TypeU {
      private final NumberSetting setting;
      private final ModeButton picker;
      private float cell = 22.0F;

      TypeD(NumberSetting var1) {
         super(var1.name(), var1.description());
         this.setting = var1;
         this.picker = new ModeButton(var1);
      }

      @Override
      boolean visible() {
         return this.setting.visible();
      }

      @Override
      float baseHeight() {
         return Math.max(58.0F, this.picker.height(this.cell) + 24.0F);
      }

      @Override
      float reserve(float var1) {
         return this.picker.width(this.picker.fit(var1 * 0.6F)) + 8.0F;
      }

      @Override
      void render(DrawContext var1, float var2, float var3, float var4, float var5) {
         this.cell = this.picker.fit(var4 * 0.6F);
         float var6 = this.baseHeight();
         super.render(var1, var2, var3 + (var6 - 58.0F) / 2.0F, var4, var5);
         this.picker.render(var1, var2 + var4, var3 + (var6 - this.picker.height(this.cell)) / 2.0F, this.cell, var5 > 0.0F);
      }

      @Override
      boolean mouseDown(float var1, float var2, int var3) {
         return var2 >= this.y && var2 < this.y + this.baseHeight() && this.picker.mouseDown(var1, var2);
      }
   }

   private static final class TypeE extends ModuleList.TypeU {
      final Module module;

      TypeE(Module var1) {
         super("Live preview", "Try your changes in the game");
         this.module = var1;
      }

      @Override
      void render(DrawContext var1, float var2, float var3, float var4, float var5) {
         super.render(var1, var2, var3, var4, var5);
         var1.canvas
            .text(
               Fonts.MEDIUM,
               CosmeticRegistry.available(this.module.id()) ? "Open" : "Enable & open",
               var2 + var4,
               var3 + 29.0F,
               12.0F,
               Icons.TEXT,
               FontStyle.RIGHT,
               FontWeight.MIDDLE
            );
      }

      @Override
      boolean mouseDown(float var1, float var2, int var3) {
         if (var3 == 0 && !(var2 < this.y) && !(var2 > this.y + 58.0F)) {
            CosmeticRegistry.open(this.module.id());
            return true;
         } else {
            return false;
         }
      }
   }

   private static final class TypeF extends ModuleList.TypeU {
      private final BooleanSupplier value;
      private final Consumer<Boolean> setter;
      private final BooleanSupplier visible;
      private final Spacer toggle;
      private final Object key = new Object();
      private float x;
      private float w;

      TypeF(String var1, String var2, BooleanSupplier var3, Consumer<Boolean> var4, BooleanSupplier var5) {
         super(var1, var2);
         this.value = var3;
         this.setter = var4;
         this.visible = var5;
         this.toggle = new Spacer(var3.getAsBoolean());
      }

      @Override
      float reserve(float var1) {
         return 46.0F;
      }

      @Override
      boolean visible() {
         return this.visible.getAsBoolean();
      }

      @Override
      void render(DrawContext var1, float var2, float var3, float var4, float var5) {
         this.x = var2;
         this.w = var4;
         super.render(var1, var2, var3, var4, var5);
         boolean var6 = var5 > 0.0F && var1.hover(this.key, var2, var3, var4, 58.0F, DrawContext.TypeE.HAND);
         this.toggle.render(var1, var2 + var4 - 46.0F, var3 + 29.0F - 13.0F, 46.0F, 26.0F, this.value.getAsBoolean(), var6, false);
      }

      @Override
      boolean mouseDown(float var1, float var2, int var3) {
         if (var2 >= this.y && var2 <= this.y + 58.0F && var1 >= this.x && var1 <= this.x + this.w) {
            boolean var4 = !this.value.getAsBoolean();
            this.setter.accept(var4);
            SoundRequest.play(var4 ? SoundEvent.TOGGLE_ON : SoundEvent.TOGGLE_OFF);
            return true;
         } else {
            return false;
         }
      }
   }

   private static final class TypeH extends ModuleList.TypeU {
      private final ColorSetting setting;
      private final ColorPicker picker;

      TypeH(ColorSetting var1) {
         super(var1.name(), var1.description());
         this.setting = var1;
         this.picker = new ColorPicker(var1);
      }

      @Override
      boolean visible() {
         return this.setting.visible();
      }

      @Override
      float baseHeight() {
         return 58.0F + this.picker.extraHeight();
      }

      @Override
      void render(DrawContext var1, float var2, float var3, float var4, float var5) {
         super.render(var1, var2, var3, var4, var5);
         this.picker.renderSwatch(var1, var2 + var4, var3 + 29.0F);
         this.picker.renderPicker(var1, var2, var3 + 58.0F, var4);
      }

      @Override
      boolean mouseDown(float var1, float var2, int var3) {
         return !(var2 < this.y) && !(var2 > this.y + this.baseHeight()) ? this.picker.mouseDown(var1, var2) : false;
      }

      @Override
      void mouseDrag(float var1, float var2) {
         this.picker.mouseDrag(var1, var2);
      }

      @Override
      void mouseUp() {
         this.picker.mouseUp();
      }
   }

   private static final class TypeI extends ModuleList.TypeU {
      private final TextSetting setting;
      final Slider field;

      TypeI(TextSetting var1) {
         super(var1.name(), var1.description());
         this.setting = var1;
         this.field = new Slider(var1);
      }

      @Override
      boolean visible() {
         return this.setting.visible();
      }

      @Override
      void render(DrawContext var1, float var2, float var3, float var4, float var5) {
         super.render(var1, var2, var3, var4, var5);
         float var6 = Math.min(250.0F, var4 * 0.46F);
         this.field.render(var1, var2 + var4 - var6, var3 + 29.0F, var6, var5 > 0.0F);
      }

      @Override
      boolean mouseDown(float var1, float var2, int var3) {
         return var2 >= this.y && var2 <= this.y + 58.0F && this.field.mouseDown(var1, var2);
      }

      @Override
      void mouseDrag(float var1, float var2) {
         this.field.mouseDrag(var1, var2);
      }

      @Override
      void mouseUp() {
         this.field.mouseUp();
      }

      @Override
      boolean keyPressed(int var1) {
         return this.editing() && this.field.keyPressed(var1);
      }

      @Override
      boolean charTyped(int var1) {
         return this.editing() && this.field.charTyped(var1);
      }

      @Override
      boolean capturesKeys() {
         return this.editing();
      }

      @Override
      void clickedElsewhere() {
         this.field.blur();
      }

      private boolean editing() {
         if (this.field.focused() && !this.visible()) {
            this.field.blur();
         }

         return this.field.focused();
      }
   }

   private static final class TypeJ extends ModuleList.TypeU {
      private final PalettePicker picker;

      TypeJ(ColorSetting var1) {
         super(var1.name(), var1.description());
         this.picker = new PalettePicker(var1);
      }

      @Override
      float baseHeight() {
         return 132.0F + this.picker.extraHeight();
      }

      @Override
      float reserve(float var1) {
         return 130.0F;
      }

      @Override
      void render(DrawContext var1, float var2, float var3, float var4, float var5) {
         super.render(var1, var2, var3, var4, var5);
         Canvas var6 = var1.canvas;
         float var7 = var3 + 29.0F;
         String var8 = this.picker.label();
         float var9 = var2 + var4 - var6.textWidth(Fonts.MEDIUM, var8, 12.0F) - 13.0F;
         var6.text(Fonts.MEDIUM, var8, var2 + var4, var7, 12.0F, Icons.TEXT_2, FontStyle.RIGHT, FontWeight.MIDDLE);
         var6.glowCircle(var9, var7, 4.0F, 9.0F, BlendMode.withAlpha(Theme.accent(), 0.55F));
         var6.circle(var9, var7, 4.0F, Theme.accent());
         this.picker.render(var1, var2, var3 + 58.0F - 4.0F, var4);
      }

      @Override
      boolean mouseDown(float var1, float var2, int var3) {
         return var2 >= this.y && var2 <= this.y + this.baseHeight() && this.picker.mouseDown(var1, var2);
      }

      @Override
      void mouseDrag(float var1, float var2) {
         this.picker.mouseDrag(var1, var2);
      }

      @Override
      void mouseUp() {
         this.picker.mouseUp();
      }
   }

   private static final class TypeL extends ModuleList.TypeU {
      private static final float TILE = 40.0F;
      
      private static TextureLoader mark;
      private final Divider control;

      TypeL(ModeSetting var1) {
         super(var1.name(), var1.description());
         this.control = new Divider(var1);
      }

      @Override
      float reserve(float var1) {
         return this.control.width();
      }

      @Override
      void render(DrawContext var1, float var2, float var3, float var4, float var5) {
         if (mark == null) {
            mark = TextureLoader.load("textures/brand/mark.png");
         }

         Canvas var6 = var1.canvas;
         float var7 = var3 + 29.0F;
         var6.roundRect(var2, var7 - 20.0F, 40.0F, 40.0F, 11.0F, BlendMode.withAlpha(Theme.tint(-16119788), 0.55F));
         var6.roundRectOutline(var2, var7 - 20.0F, 40.0F, 40.0F, 11.0F, 1.0F, 452984831);
         var6.glowCircle(var2 + 20.0F, var7, 8.0F, 22.0F, BlendMode.withAlpha(Theme.logoTint(-3069451), 0.3F));
         float var8 = 29.6F;
         float var9 = var8 * mark.aspect();
         var6.logo(mark, var2 + 20.0F - var9 / 2.0F, var7 - var8 / 2.0F, var9, var8, -1);
         super.render(var1, var2 + 40.0F + 14.0F, var3, var4 - 40.0F - 14.0F, var5);
         this.control.render(var1, var2 + var4, var7);
      }

      @Override
      boolean mouseDown(float var1, float var2, int var3) {
         return var2 >= this.y && var2 <= this.y + 58.0F && this.control.mouseDown(var1, var2);
      }
   }

   private static final class TypeM extends ModuleList.TypeU {
      private static final float TRACK = 200.0F;
      private static final float VALUE_W = 72.0F;
      private static final float VALUE_GAP = 14.0F;
      private static final float VALUE_SIZE = 12.5F;
      private final NumberSetting setting;
      private final Label slider;
      private float valueW = 72.0F;

      TypeM(NumberSetting var1) {
         super(var1.name(), var1.description());
         this.setting = var1;
         this.slider = new Label(var1);
      }

      @Override
      boolean visible() {
         return this.setting.visible();
      }

      private static float track(float var0) {
         return Math.min(200.0F, Math.max(120.0F, var0 * 0.36F));
      }

      @Override
      float reserve(float var1) {
         return track(var1) + 14.0F + this.valueW + 8.0F;
      }

      @Override
      void render(DrawContext var1, float var2, float var3, float var4, float var5) {
         this.valueW = this.valueWidth(var1.canvas);
         super.render(var1, var2, var3, var4, var5);
         float var6 = var3 + 29.0F;
         float var7 = var2 + var4;
         float var8 = track(var4);
         this.slider.render(var1, var7 - this.valueW - 14.0F - var8, var6, var8);
         var1.canvas.text(Fonts.SEMIBOLD, this.setting.display(), var7 - this.valueW / 2.0F, var6, 12.5F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
      }

      private float valueWidth(Canvas var1) {
         NumberSetting var2 = this.setting;
         float var3 = 0.0F;

         for (double var7 : new double[]{var2.min(), var2.max(), var2.defaultValue()}) {
            var3 = Math.max(var3, var1.textWidth(Fonts.SEMIBOLD, Translations.display(var2.display(var7)), 12.5F));
         }

         return Math.max(72.0F, var3 + 4.0F);
      }

      @Override
      boolean mouseDown(float var1, float var2, int var3) {
         return this.slider.mouseDown(var1, var2);
      }

      @Override
      void mouseDrag(float var1, float var2) {
         this.slider.mouseDrag(var1);
      }

      @Override
      void mouseUp() {
         this.slider.mouseUp();
      }
   }

   private static final class TypeN extends ModuleList.TypeU {
      private static final float HEIGHT = 86.0F;
      private final Studio studio;
      private final Runnable open;
      private final BooleanSupplier visible;
      private final Animator hover = Animator.smooth(0.0F);
      private final Object key = new Object();
      private float x;
      private float w;

      TypeN(Studio var1, Runnable var2, BooleanSupplier var3) {
         super(var1.title(), var1.subtitle());
         this.studio = var1;
         this.open = var2;
         this.visible = var3;
      }

      @Override
      float baseHeight() {
         return 86.0F;
      }

      @Override
      boolean visible() {
         return this.visible.getAsBoolean() && this.studio.available();
      }

      @Override
      void render(DrawContext var1, float var2, float var3, float var4, float var5) {
         this.x = var2;
         this.w = var4;
         Canvas var6 = var1.canvas;
         float var7 = var3 + 12.0F + 31.0F;
         float var8 = 62.0F;
         float var9 = var7 - var8 / 2.0F;
         boolean var10 = var5 > 0.0F && var1.hover(this.key, var2, var9, var4, var8, DrawContext.TypeE.HAND);
         float var11 = this.hover.target(var10 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         if (var11 > 0.01F) {
            var6.glow(var2, var9, var4, var8, 16.0F, 18.0F, BlendMode.withAlpha(Icons.VIOLET, 0.22F * var11));
         }

         var6.roundRectGradient(
            var2,
            var9,
            var4,
            var8,
            16.0F,
            0.0F,
            BlendMode.lerp(Theme.tint(707468114), Theme.tint(976494698), var11),
            BlendMode.lerp(Theme.tint(437915696), Theme.tint(639767880), var11)
         );
         var6.roundRectOutlineGradient(
            var2,
            var9,
            var4,
            var8,
            16.0F,
            1.0F,
            0.0F,
            BlendMode.lerp(BlendMode.withAlpha(Icons.PURPLE, 0.4F), BlendMode.withAlpha(Icons.MAGENTA, 0.8F), var11),
            352321535
         );
         float var12 = MotionStyle.reduced() ? 0.0F : (float)Math.sin(var1.time * 2.1F) * 1.6F;
         KeyLabel.iconTile(var6, var2 + 12.0F, var7 - 19.0F + var12, 38.0F, 12.0F, this.studio.icon(), 1.0F, var11);
         float var13 = var2 + 12.0F + 38.0F + 14.0F;
         String var14 = this.studio.action();
         float var15 = var6.textWidth(Fonts.SEMIBOLD, var14, 11.5F) + 40.0F;
         float var16 = 30.0F;
         float var17 = var2 + var4 - 14.0F - var15;
         var6.text(Fonts.SEMIBOLD, this.label, var13, var7 - 9.0F, 13.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
         var6.text(
            Fonts.REGULAR,
            FontMetrics.ellipsize(Fonts.REGULAR, this.description, 11.5F, var17 - var13 - 12.0F, 0.0F),
            var13,
            var7 + 10.0F,
            11.5F,
            Icons.TEXT_2,
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
         var6.glow(var17, var7 - var16 / 2.0F, var15, var16, var16 / 2.0F, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.2F + 0.25F * var11));
         var6.roundRectGradient(
            var17,
            var7 - var16 / 2.0F,
            var15,
            var16,
            var16 / 2.0F,
            0.0F,
            BlendMode.withAlpha(Icons.INDIGO, 0.8F + 0.2F * var11),
            BlendMode.withAlpha(Icons.PURPLE, 0.8F + 0.2F * var11)
         );
         var6.roundRectOutline(var17, var7 - var16 / 2.0F, var15, var16, var16 / 2.0F, 1.0F, 872415231);
         var6.text(Fonts.SEMIBOLD, var14, var17 + 14.0F, var7, 11.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
         var6.icon("chevron-right", var17 + var15 - 15.0F + 2.0F * var11, var7, 12.0F, Icons.TEXT);
      }

      @Override
      boolean mouseDown(float var1, float var2, int var3) {
         if (this.visible() && var2 >= this.y + 12.0F && var2 <= this.y + 12.0F + 62.0F && var1 >= this.x && var1 <= this.x + this.w) {
            this.open.run();
            return true;
         } else {
            return false;
         }
      }
   }

   private static final class TypeO extends ModuleList.TypeU {
      private static final float STACK = 20.0F;
      private final PaletteSetting setting;
      private final BiConsumer<PaletteSetting, float[]> open;
      private final Supplier<SettingEditor> picker;
      private final Animator hover = Animator.smooth(0.0F);
      private final Object key = new Object();
      private float x;
      private float w;
      private float bx;
      private float by;
      private float bw;
      private float bh;

      TypeO(PaletteSetting var1, BiConsumer<PaletteSetting, float[]> var2, Supplier<SettingEditor> var3) {
         super(var1.name(), var1.description());
         this.setting = var1;
         this.open = var2;
         this.picker = var3;
      }

      @Override
      boolean visible() {
         return this.setting.visible();
      }

      @Override
      float reserve(float var1) {
         return this.bw > 0.0F ? this.bw : 150.0F;
      }

      @Override
      void render(DrawContext var1, float var2, float var3, float var4, float var5) {
         this.x = var2;
         this.w = var4;
         super.render(var1, var2, var3, var4, var5);
         Canvas var6 = var1.canvas;
         float var7 = var3 + 29.0F;
         int var8 = this.setting.count();
         String var9 = var8 == 0 ? Translations.tr("Choose") : var8 + " " + Translations.tr("chosen");
         float var10 = SettingEditor.stackWidth(this.setting, 20.0F, 3);
         Translations.pause();
         float var11 = var6.textWidth(Fonts.SEMIBOLD, var9, 12.0F);
         Translations.resume();
         this.bh = 32.0F;
         this.bw = 12.0F + var10 + 8.0F + var11 + 10.0F + 14.0F + 10.0F;
         this.bx = var2 + var4 - this.bw;
         this.by = var7 - this.bh / 2.0F;
         SettingEditor var12 = this.picker.get();
         boolean var13 = var12 != null && var12.setting == this.setting;
         if (var13) {
            var12.origin(this.bx, this.by, this.bw, this.bh);
         } else {
            boolean var14 = var5 > 0.0F && var1.hover(this.key, var2, var3, var4, 58.0F, DrawContext.TypeE.HAND);
            float var15 = this.hover.target(var14 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
            if (var15 > 0.01F) {
               var6.glow(this.bx, this.by, this.bw, this.bh, this.bh / 2.0F, 14.0F, BlendMode.withAlpha(Icons.VIOLET, 0.18F * var15));
            }

            var6.roundRect(this.bx, this.by, this.bw, this.bh, this.bh / 2.0F, BlendMode.lerp(318767103, 486539263, var15));
            var6.roundRectOutline(
               this.bx, this.by, this.bw, this.bh, this.bh / 2.0F, 1.0F, BlendMode.lerp(486539263, BlendMode.withAlpha(Icons.PURPLE, 0.65F), var15)
            );
            SettingEditor.stack(var6, (SettingEditor)null, this.setting, this.bx + 12.0F, var7, 20.0F, 3);
            Translations.pause();
            var6.text(
               Fonts.SEMIBOLD, var9, this.bx + 12.0F + var10 + 8.0F, var7, 12.0F, var8 == 0 ? Icons.TEXT_2 : Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE
            );
            Translations.resume();
            var6.icon("chevron-right", this.bx + this.bw - 16.0F + 2.0F * var15, var7, 13.0F, BlendMode.lerp(Icons.TEXT_3, Icons.TEXT, var15));
         }
      }

      @Override
      boolean mouseDown(float var1, float var2, int var3) {
         if (this.visible() && var2 >= this.y && var2 <= this.y + 58.0F && var1 >= this.x && var1 <= this.x + this.w) {
            this.open.accept(this.setting, new float[]{this.bx, this.by, this.bw, this.bh});
            return true;
         } else {
            return false;
         }
      }
   }

   private abstract static class TypeU {
      final Animator presence = Animator.smooth(1.0F);
      final String label;
      final String description;
      float y;

      TypeU(String var1, String var2) {
         this.label = var1;
         this.description = var2;
      }

      float baseHeight() {
         return 58.0F;
      }

      final float height() {
         return this.baseHeight() * Math.max(0.0F, Math.min(1.0F, this.presence.value()));
      }

      boolean visible() {
         return true;
      }

      float reserve(float var1) {
         return var1 * 0.5F - 16.0F;
      }

      void render(DrawContext var1, float var2, float var3, float var4, float var5) {
         Canvas var6 = var1.canvas;
         float var7 = var3 + 29.0F;
         float var8 = Math.min(var4 * 0.5F, var4 - this.reserve(var4) - 16.0F);
         if (this.description != null && !this.description.isEmpty() && var8 >= 90.0F) {
            var6.text(Fonts.MEDIUM, this.label, var2, var7 - 9.0F, 13.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
            var6.text(
               Fonts.REGULAR,
               FontMetrics.ellipsize(Fonts.REGULAR, this.description, 11.5F, var8, 0.0F),
               var2,
               var7 + 10.0F,
               11.5F,
               Icons.TEXT_3,
               FontStyle.LEFT,
               FontWeight.MIDDLE
            );
         } else {
            var6.text(Fonts.MEDIUM, this.label, var2, var7, 13.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
         }
      }

      boolean mouseDown(float var1, float var2, int var3) {
         return false;
      }

      void mouseDrag(float var1, float var2) {
      }

      void mouseUp() {
      }

      boolean keyPressed(int var1) {
         return false;
      }

      boolean charTyped(int var1) {
         return false;
      }

      boolean capturesKeys() {
         return false;
      }

      void clickedElsewhere() {
      }
   }

   private static final class TypeV extends ModuleList.TypeU {
      private final ModeSetting setting;
      private final Divider control;
      private final Pagination stepper;
      private boolean compact;
      private float right;

      TypeV(ModeSetting var1) {
         super(var1.name(), var1.description());
         this.setting = var1;
         this.control = new Divider(var1);
         this.stepper = new Pagination(var1);
      }

      @Override
      boolean visible() {
         return this.setting.visible();
      }

      @Override
      void render(DrawContext var1, float var2, float var3, float var4, float var5) {
         super.render(var1, var2, var3, var4, var5);
         this.right = var2 + var4;
         this.compact = this.control.width() > var4 * 0.52F;
         if (this.compact) {
            this.stepper.render(var1, this.right, var3 + 29.0F);
         } else {
            this.control.render(var1, this.right, var3 + 29.0F);
         }
      }

      @Override
      boolean mouseDown(float var1, float var2, int var3) {
         return var2 >= this.y && var2 <= this.y + 58.0F && (this.compact ? this.stepper.mouseDown(var1, var2) : this.control.mouseDown(var1, var2));
      }
   }

   private static final class TypeW extends ModuleList.TypeU {
      final String id;

      TypeW(String var1) {
         super("Preview", (String)null);
         this.id = var1;
      }

      @Override
      boolean visible() {
         return CosmeticCatalog.available(this.id);
      }

      @Override
      float baseHeight() {
         return CosmeticCatalog.height(this.id);
      }

      @Override
      void render(DrawContext var1, float var2, float var3, float var4, float var5) {
         CosmeticCatalog.render(this.id, var1, var2, var3, var4, var5 > 0.5F);
      }

      @Override
      boolean mouseDown(float var1, float var2, int var3) {
         return var2 >= this.y && var2 < this.y + this.height() && CosmeticCatalog.mouseDown(this.id, var1, var2, var3);
      }

      @Override
      void mouseDrag(float var1, float var2) {
         CosmeticCatalog.mouseDrag(this.id, var1, var2);
      }

      @Override
      void mouseUp() {
         CosmeticCatalog.mouseUp(this.id);
      }
   }

   private static final class TypeY extends ModuleList.TypeU {
      private final KeybindButton button;
      private BooleanSupplier visible = () -> true;

      TypeY(Module var1) {
         super(var1.keybindToggles() ? "Keybind" : "Action key", "Choose a key for " + var1.name());
         this.button = new KeybindButton(var1);
      }

      TypeY(IntegerSetting var1) {
         super(var1.name(), var1.description());
         IntSupplier var2 = var1::key;
         this.button = new KeybindButton(var2, var1::set);
         this.visible = var1::visible;
      }

      @Override
      boolean visible() {
         return this.visible.getAsBoolean();
      }

      @Override
      void render(DrawContext var1, float var2, float var3, float var4, float var5) {
         super.render(var1, var2, var3, var4, var5);
         this.button.render(var1, var2 + var4, var3 + 29.0F);
      }

      @Override
      boolean mouseDown(float var1, float var2, int var3) {
         return var2 >= this.y && var2 <= this.y + 58.0F && this.button.mouseDown(var1, var2);
      }

      @Override
      boolean keyPressed(int var1) {
         return this.button.keyPressed(var1);
      }

      @Override
      boolean capturesKeys() {
         return this.button.listening();
      }

      @Override
      void clickedElsewhere() {
         if (this.button.listening()) {
            this.button.mouseDown(Float.NaN, Float.NaN);
         }
      }
   }
}
