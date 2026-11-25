package dev.purxiy.hud;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.Setting;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.modules.Module;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.KeyLabel;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.clickgui.MenuScreen;
import dev.purxiy.ui.widget.Spacer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import net.minecraft.class_310;

final class HudGroupEditor {
   static final float W = 206.0F;
   private static final float HEADER = 42.0F;
   private static final float FOOTER = 32.0F;
   private static final float ROW = 22.0F;
   private static final float SECTION = 19.0F;
   private static final float PAD = 11.0F;
   private static final float TEXT = 8.5F;
   private static final float RADIUS = 12.0F;
   private static final float GAP = 10.0F;
   private static final int[] SWATCHES = new int[]{-592897, -28744, -9444427, -10827521, -11654};

   private HudModule element;

   private Module module;

   private HudModule anchorUnit;
   private final float[] anchor = new float[4];
   private int side;
   private boolean closing;
   private final List<HudGroupEditor.ControlRow> rows = new ArrayList<>();
   private final Animator appear = Animator.smooth(0.0F);
   private final Animator scroll = new Animator(0.0F, 260.0F, 32.0F);
   private float scrollTarget;
   private float x;
   private float y;
   private float h;
   private float viewH;
   private float contentH;
   private final DrawContext frame = new DrawContext();
   private final Spacer enabled = new Spacer(false);
   private final Animator enabledHover = Animator.smooth(0.0F);
   private final Animator[] footerHover = new Animator[]{Animator.smooth(0.0F), Animator.smooth(0.0F)};
   private final float[][] footer = new float[2][4];
   private final float[] toggle = new float[4];

   private HudGroupEditor.ControlRow pressed;
   private boolean pointerDown;

   boolean visible() {
      return this.module != null;
   }

   boolean active() {
      return this.module != null && !this.closing;
   }


   HudModule element() {
      return this.element;
   }

   float progress() {
      return this.module == null ? 0.0F : Math.max(0.0F, Math.min(1.0F, this.appear.value()));
   }

   boolean hand() {
      return this.visible() && this.frame.cursor() == DrawContext.Cursor.HAND;
   }

   boolean contains(float var1, float var2) {
      return this.active() && var1 >= this.x && var1 <= this.x + 206.0F && var2 >= this.y && var2 <= this.y + this.h;
   }

   void open(HudModule var1) {
      Object var2 = var1.group != null ? var1.group : var1;
      this.openAt(
         var1.settingsModule(),
         var1,
         (HudModule)var2,
         ((HudModule)var2).renderX.value(),
         ((HudModule)var2).renderY.value(),
         ((HudModule)var2).renderWidth(),
         ((HudModule)var2).renderHeight()
      );
   }

   void openAt(Module var1,  HudModule var2,  HudModule var3, float var4, float var5, float var6, float var7) {
      boolean var8 = this.active();
      this.element = var2;
      this.module = var1;
      this.anchorUnit = var3;
      this.anchor[0] = var4;
      this.anchor[1] = var5;
      this.anchor[2] = var6;
      this.anchor[3] = var7;
      this.side = 0;
      this.closing = false;
      this.build(var1, var2);
      this.scroll.snap(0.0F);
      this.scrollTarget = 0.0F;
      this.enabled.snap(CosmeticRegistry.enabled(this.module.id()));
      if (!var8) {
         this.appear.snap(0.0F);
      }

      this.appear.target(1.0F);
      SoundRequest.play(SoundEvent.EXPAND, 1.05F, 0.8F);
   }

   void close() {
      if (this.active()) {
         this.closing = true;
         this.appear.target(0.0F);
         this.mouseUp();
         SoundRequest.play(SoundEvent.COLLAPSE, 1.05F, 0.6F);
      }
   }

   void reset() {
      this.element = null;
      this.module = null;
      this.closing = false;
      this.pressed = null;
      this.appear.snap(0.0F);
      this.rows.clear();
      this.anchorUnit = null;
   }

   private void build(Module var1,  HudModule var2) {
      this.rows.clear();
      Set var3 = Collections.newSetFromMap(new IdentityHashMap());

      for (HudModule var5 : HudRegistry.elements()) {
         if (var5.settingsModule() == var1) {
            Collections.addAll(var3, var5.size, var5.background, var5.opacity, var5.posX, var5.posY, var5.placed);
         }
      }

      HudGroupEditor.SpacerRow var20 = null;

      for (Setting var6 : var1.settings()) {
         if (!var3.contains(var6)) {
            String var7 = var1.sectionBefore(var6);
            if (var7 != null) {
               var20 = new HudGroupEditor.SpacerRow(var7);
               this.rows.add(var20);
            }

            HudGroupEditor.ControlRow var8 = this.row(var6);
            if (var8 != null) {
               this.rows.add(var8);
               if (var20 != null) {
                  var20.members.add(var8);
               }
            }
         }
      }

      if (var2 == null) {
         this.snapRows();
      } else {
         HudGroupModule var22 = var2.group;
         Object var23 = var22 != null ? var22 : var2;
         HudGroupEditor.SpacerRow var24 = new HudGroupEditor.SpacerRow(var22 != null ? "Merged group" : "Layout");
         this.rows.add(var24);
         HudGroupEditor.ControlRow[] var25 = new HudGroupEditor.ControlRow[]{
            new HudGroupEditor.NumberControl(((HudModule)var23).size, () -> true, ((HudModule)var23).size.name()), null, null
         };
         String var9 = ((HudModule)var23).background.name();
         BooleanSetting var10 = ((HudModule)var23).background;
         BooleanSupplier var11 = var10::enabled;
         BooleanSetting var12 = ((HudModule)var23).background;
         var25[1] = new HudGroupEditor.ToggleControl(var9, var11, var12::set, () -> true);
         NumberSetting var13 = ((HudModule)var23).opacity;
         BooleanSetting var14 = ((HudModule)var23).background;
         var25[2] = new HudGroupEditor.NumberControl(var13, var14::enabled, "Opacity");

         for (HudGroupEditor.ControlRow var19 : var25) {
            this.rows.add(var19);
            var24.members.add(var19);
         }

         if (var22 != null) {
            HudGroupEditor.ModeControl var26 = new HudGroupEditor.ModeControl(var22.axis, () -> true, "Direction");
            HudGroupEditor.ButtonControl var27 = new HudGroupEditor.ButtonControl("Remove from group", "unlink", () -> {
               HudLayoutEngine.detach(var2);
               this.close();
            });
            this.rows.add(var26);
            this.rows.add(var27);
            var24.members.add(var26);
            var24.members.add(var27);
         }

         this.snapRows();
      }
   }

   private void snapRows() {
      for (HudGroupEditor.ControlRow var2 : this.rows) {
         var2.presence.snap(var2.visible() ? 1.0F : 0.0F);
      }
   }


   private HudGroupEditor.ControlRow row(Setting<?> var1) {
      if (var1 instanceof BooleanSetting var2) {
         String var6 = var2.name();
         BooleanSupplier var7 = var2::enabled;
         Consumer var8 = v -> var2.set((Boolean)v);
         return new HudGroupEditor.ToggleControl(var6, var7, var8, var2::visible);
      } else if (var1 instanceof NumberSetting var3) {
         return new HudGroupEditor.NumberControl(var3, var3::visible, var3.name());
      } else if (var1 instanceof ModeSetting var4) {
         return new HudGroupEditor.ModeControl(var4, var4::visible, var4.name());
      } else {
         return (HudGroupEditor.ControlRow)(var1 instanceof ColorSetting var5 ? new HudGroupEditor.ColorControl(var5) : new HudGroupEditor.SettingControl(var1));
      }
   }

   void render(Canvas var1, HudBounds var2, float var3, float var4, float var5, float var6) {
      HudModule var7 = this.element;
      Module var8 = this.module;
      if (var8 != null) {
         float var9 = this.appear.update(MotionStyle.PANEL.dt(var5));
         if (this.closing && var9 < 0.02F) {
            this.reset();
         } else {
            this.frame.begin(var1, var6, var5, var3, var4, this.pointerDown);
            this.frame.mask(this.closing);
            float var10 = 0.0F;

            for (HudGroupEditor.ControlRow var12 : this.rows) {
               var12.presence.target(var12.visible() ? 1.0F : 0.0F).update(MotionStyle.PANEL.dt(var5));
               var10 += var12.height() * var12.amount();
            }

            this.contentH = var10 + 4.0F;
            float var23 = Math.max(80.0F, Math.min(var2.height * 0.72F, var2.height - 24.0F) - 42.0F - 32.0F);
            this.viewH = Math.min(this.contentH, var23);
            this.h = 42.0F + this.viewH + 32.0F;
            this.place(var2);
            float var24 = Math.max(0.0F, this.contentH - this.viewH);
            this.scrollTarget = Math.max(0.0F, Math.min(var24, this.scrollTarget));
            float var13 = this.scroll.target(this.scrollTarget).update(var5);
            float var14 = Easing.EMPHASIZED.apply(Math.max(0.0F, Math.min(1.0F, var9)));
            float var15 = this.side == 1 ? this.x : (this.side == -1 ? this.x + 206.0F : this.x + 103.0F);
            float var16 = this.side == 2 ? this.y : (this.side == -2 ? this.y + this.h : this.y + Math.min(this.h / 2.0F, 40.0F));
            float var17 = (1.0F - var14) * 8.0F;
            var1.push();
            var1.translate(this.side == 1 ? -var17 : (this.side == -1 ? var17 : 0.0F), this.side == 2 ? -var17 : (this.side == -2 ? var17 : 0.0F));
            var1.scaleAround(0.94F + 0.06F * var14, var15, var16);
            var1.pushAlpha(Math.min(1.0F, var9 * 1.4F));
            KeyLabel.surface(var1, this.x, this.y, 206.0F, this.h, 12.0F, 0.7F, 0.35F);
            this.header(var1, var7, var8, var5);
            float var18 = this.y + 42.0F;
            var1.rect(this.x + 11.0F, var18 - 0.5F, 184.0F, 1.0F, 318767103);
            var1.pushClip(this.x, var18, 206.0F, this.viewH);
            float var19 = var18 + 2.0F - var13;

            for (HudGroupEditor.ControlRow var21 : this.rows) {
               float var22 = var21.amount();
               if (var22 <= 0.01F) {
                  var21.y = Float.NaN;
               } else {
                  var21.y = var19;
                  if (var19 + var21.height() > var18 - 2.0F && var19 < var18 + this.viewH + 2.0F) {
                     var1.pushAlpha(var22);
                     var21.render(var1, this.x + 11.0F, var19, 184.0F, this.frame, var5);
                     var1.popAlpha();
                  }

                  var19 += var21.height() * var22;
               }
            }

            var1.popClip();
            if (var24 > 0.5F) {
               float var25 = this.viewH - 8.0F;
               float var26 = Math.max(14.0F, var25 * this.viewH / this.contentH);
               float var27 = var18 + 4.0F + (var25 - var26) * (var13 / var24);
               var1.roundRect(this.x + 206.0F - 4.5F, var27, 2.0F, var26, 1.0F, 872415231);
            }

            this.footer(var1, var7, var5);
            var1.popAlpha();
            var1.pop();
         }
      }
   }

   private void place(HudBounds var1) {
      HudModule var2 = this.anchorUnit;
      if (var2 != null && var2.laidOut) {
         this.anchor[0] = var2.renderX.value();
         this.anchor[1] = var2.renderY.value();
         this.anchor[2] = var2.renderWidth();
         this.anchor[3] = var2.renderHeight();
      }

      float var3 = this.anchor[0];
      float var4 = this.anchor[1];
      float var5 = this.anchor[2];
      float var6 = this.anchor[3];
      boolean var7 = var3 + var5 + 10.0F + 206.0F <= var1.width - 6.0F;
      boolean var8 = var3 - 10.0F - 206.0F >= 6.0F;
      if (this.side == 0 || this.side == 1 && !var7 || this.side == -1 && !var8) {
         boolean var9 = var3 + var5 / 2.0F < var1.width / 2.0F;
         this.side = !var7 || !var9 && var8 ? (var8 ? -1 : (var4 + var6 / 2.0F < var1.height / 2.0F ? 2 : -2)) : 1;
      }

      switch (this.side) {
         case -1:
            this.x = var3 - 10.0F - 206.0F;
            break;
         case 1:
            this.x = var3 + var5 + 10.0F;
            break;
         default:
            this.x = clamp(var3 + var5 / 2.0F - 103.0F, 6.0F, var1.width - 6.0F - 206.0F);
      }

      if (Math.abs(this.side) == 1) {
         this.y = clamp(var4 - 4.0F, 6.0F, var1.height - 6.0F - this.h);
      } else {
         this.y = this.side == 2 ? Math.min(var4 + var6 + 10.0F, var1.height - 6.0F - this.h) : Math.max(6.0F, var4 - 10.0F - this.h);
      }
   }

   private void header(Canvas var1,  HudModule var2, Module var3, float var4) {
      float var5 = this.x + 11.0F;
      float var6 = this.y + 21.0F;
      boolean var7 = CosmeticRegistry.enabled(var3.id()) || !var3.toggleable();
      KeyLabel.iconTile(var1, var5, var6 - 11.0F, 22.0F, 7.0F, var2 != null ? var2.icon() : var3.icon(), var7 ? 1.0F : 0.0F, 0.0F);
      float var8 = var5 + 22.0F + 9.0F;
      float var9 = 195.0F - (var8 - this.x) - (var3.toggleable() ? 30 : 0);
      HudGroupModule var10 = var2 != null ? var2.group : null;
      String var11 = var10 != null ? merged(var10, var2) : var3.description();
      String var12 = var2 != null ? var2.name() : var3.name();
      var1.text(
         Fonts.SEMIBOLD,
         FontMetrics.ellipsize(Fonts.SEMIBOLD, var12, 10.0F, var9, 0.0F),
         var8,
         var6 - 5.5F,
         10.0F,
         Icons.TEXT,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      var1.text(
         Fonts.REGULAR, FontMetrics.ellipsize(Fonts.REGULAR, var11, 7.5F, var9, 0.0F), var8, var6 + 6.5F, 7.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE
      );
      if (var3.toggleable()) {
         float var13 = 24.0F;
         float var14 = 13.0F;
         float var15 = this.x + 206.0F - 11.0F - var13;
         float var16 = var6 - var14 / 2.0F;
         this.toggle[0] = var15 - 4.0F;
         this.toggle[1] = var16 - 4.0F;
         this.toggle[2] = var13 + 8.0F;
         this.toggle[3] = var14 + 8.0F;
         boolean var17 = this.frame.hover(this.toggle, var15 - 4.0F, var16 - 4.0F, var13 + 8.0F, var14 + 8.0F, DrawContext.Cursor.HAND);
         this.enabledHover.target(var17 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var4));
         this.enabled.render(this.frame, var15, var16, var13, var14, CosmeticRegistry.enabled(var3.id()), var17, false);
      } else {
         this.toggle[2] = 0.0F;
      }
   }

   private static String merged(HudGroupModule var0, HudModule var1) {
      StringBuilder var2 = new StringBuilder();

      for (HudModule var4 : var0.list) {
         if (var4 != var1) {
            if (!var2.isEmpty()) {
               var2.append(", ");
            }

            var2.append(Translations.tr(var4.name()));
         }
      }

      String var5 = Translations.tr("Merged with");
      return var5 + " " + var2;
   }

   private void footer(Canvas var1,  HudModule var2, float var3) {
      float var10 = this.y + this.h - 32.0F;
      var1.rect(this.x + 11.0F, var10, 184.0F, 1.0F, 318767103);
      float var4 = 18.0F;
      float var5 = var10 + 16.0F;
      String[] var6 = new String[]{"Centre", "All settings"};
      String[] var7 = new String[]{"align-center-horizontal", "sliders-horizontal"};
      float var8 = this.x + 206.0F - 11.0F;
      boolean var9;
      if (var2 != null && (var2.group != null ? var2.group.arranged() : var2.arranged())) {
         var9 = true;
      } else {
         var9 = false;
      }

      boolean var18 = var9;
      this.footer[0][2] = 0.0F;

      for (int var11 = 1; var11 >= (var18 ? 0 : 1); var11--) {
         float var12 = 25.0F + var1.textWidth(Fonts.MEDIUM, var6[var11], 8.0F) + 10.0F;
         float var13 = var11 == 1 ? var8 - var12 : this.x + 11.0F;
         float[] var14 = this.footer[var11];
         var14[0] = var13;
         var14[1] = var5 - var4 / 2.0F;
         var14[2] = var12;
         var14[3] = var4;
         boolean var15 = this.frame.hover(var14, var13, var14[1], var12, var4, DrawContext.Cursor.HAND);
         float var16 = this.footerHover[var11].target(var15 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var3));
         if (var11 == 1) {
            var1.roundRectGradient(
               var13,
               var14[1],
               var12,
               var4,
               var4 / 2.0F,
               0.0F,
               BlendMode.withAlpha(Icons.INDIGO, 0.55F + 0.35F * var16),
               BlendMode.withAlpha(Icons.PURPLE, 0.55F + 0.35F * var16)
            );
         } else {
            var1.roundRect(var13, var14[1], var12, var4, var4 / 2.0F, BlendMode.lerp(318767103, 587202559, var16));
         }

         var1.roundRectOutline(var13, var14[1], var12, var4, var4 / 2.0F, 1.0F, BlendMode.lerp(352321535, 872415231, var16));
         int var17 = var11 == 1 ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var16);
         var1.icon(var7[var11], var13 + 10.0F + 5.0F, var5, 10.0F, var17);
         var1.text(Fonts.MEDIUM, var6[var11], var13 + 10.0F + 10.0F + 5.0F, var5, 8.0F, var17, FontStyle.LEFT, FontWeight.MIDDLE);
      }
   }

   boolean mouseDown(float var1, float var2, int var3) {
      HudModule var4 = this.element;
      Module var5 = this.module;
      if (!this.contains(var1, var2) || var5 == null) {
         return false;
      } else if (var3 != 0) {
         return true;
      } else {
         this.pointerDown = true;
         if (inside(this.toggle, var1, var2)) {
            CosmeticRegistry.requestEnabled(var5.id(), !CosmeticRegistry.enabled(var5.id()));
            SoundRequest.play(CosmeticRegistry.enabled(var5.id()) ? SoundEvent.TOGGLE_ON : SoundEvent.TOGGLE_OFF);
            return true;
         } else if (var4 != null && inside(this.footer[0], var1, var2)) {
            SoundRequest.play(SoundEvent.SELECT, 1.0F, 0.7F);
            HudLayoutEngine.centre((HudModule)(var4.group != null ? var4.group : var4));
            return true;
         } else if (inside(this.footer[1], var1, var2)) {
            SoundRequest.play(SoundEvent.EXPAND);
            class_310 var9 = class_310.method_1551();
            var9.method_1507(new MenuScreen(var9.field_1755, var5));
            return true;
         } else {
            float var6 = this.y + 42.0F;
            if (!(var2 < var6) && !(var2 > var6 + this.viewH)) {
               for (HudGroupEditor.ControlRow var8 : this.rows) {
                  if (!Float.isNaN(var8.y) && var8.amount() > 0.5F && var2 >= var8.y && var2 < var8.y + var8.height() && var8.mouseDown(var1, var2)) {
                     this.pressed = var8;
                     return true;
                  }
               }

               return true;
            } else {
               return true;
            }
         }
      }
   }

   void mouseDrag(float var1, float var2) {
      if (this.pressed != null) {
         this.pressed.mouseDrag(var1, var2);
      }
   }

   void mouseUp() {
      this.pointerDown = false;
      if (this.pressed != null) {
         this.pressed.mouseUp();
         this.pressed = null;
      }
   }

   boolean dragging() {
      return this.pressed != null;
   }

   boolean scroll(float var1, float var2, double var3) {
      if (!this.contains(var1, var2)) {
         return false;
      } else {
         this.scrollTarget -= (float)var3 * 22.0F * 1.5F;
         return true;
      }
   }

   private static boolean inside(float[] var0, float var1, float var2) {
      return var0[2] > 0.0F && var1 >= var0[0] && var1 <= var0[0] + var0[2] && var2 >= var0[1] && var2 <= var0[1] + var0[3];
   }

   private static float clamp(float var0, float var1, float var2) {
      return var2 < var1 ? (var1 + var2) / 2.0F : Math.max(var1, Math.min(var2, var0));
   }

   private final class SettingControl extends HudGroupEditor.ControlRow {
      private final Setting<?> setting;

      SettingControl(Setting<?> nullx) {
         this.setting = nullx;
      }

      @Override
      boolean visible() {
         return this.setting.visible();
      }

      @Override
      void render(Canvas var1, float var2, float var3, float var4, DrawContext var5, float var6) {
         float var7 = this.highlight(var1, var5, var2, var3, var4, var6);
         if (var5.inside(var2 - 5.0F, var3 + 1.0F, var4 + 10.0F, 20.0F)) {
            var5.claim(this, DrawContext.Cursor.HAND);
         }

         float var8 = var3 + 11.0F;
         String var9 = "In menu";
         float var10 = var1.textWidth(Fonts.MEDIUM, var9, 7.5F);
         label(var1, this.setting.name(), var2, var8, var4 - var10 - 22.0F, BlendMode.lerp(Icons.TEXT, -1, var7));
         int var11 = BlendMode.lerp(Icons.PURPLE, Icons.MAGENTA, var7);
         var1.text(Fonts.MEDIUM, var9, var2 + var4 - 10.0F, var8, 7.5F, var11, FontStyle.RIGHT, FontWeight.MIDDLE);
         var1.icon("chevron-right", var2 + var4 - 4.0F, var8, 8.0F, var11);
      }

      @Override
      boolean mouseDown(float var1, float var2) {
         Module var3 = HudGroupEditor.this.module;
         if (var3 != null) {
            SoundRequest.play(SoundEvent.EXPAND);
            class_310 var4 = class_310.method_1551();
            var4.method_1507(new MenuScreen(var4.field_1755, var3));
         }

         return false;
      }
   }

   private static final class ColorControl extends HudGroupEditor.ControlRow {
      private static final float SPACING = 12.5F;
      private final ColorSetting setting;
      private float left;
      private int count;

      ColorControl(ColorSetting var1) {
         this.setting = var1;
      }

      @Override
      boolean visible() {
         return this.setting.visible();
      }

      private int first() {
         return this.setting.themed() ? 1 : 0;
      }

      private boolean custom() {
         if (this.setting.following()) {
            return false;
         } else {
            for (int var4 : HudGroupEditor.SWATCHES) {
               if ((var4 | 0xFF000000) == (this.setting.argb() | 0xFF000000)) {
                  return false;
               }
            }

            return true;
         }
      }

      @Override
      void render(Canvas var1, float var2, float var3, float var4, DrawContext var5, float var6) {
         float var7 = this.highlight(var1, var5, var2, var3, var4, var6);
         float var8 = var3 + 11.0F;
         boolean var9 = this.custom();
         int var10 = this.first();
         this.count = var10 + HudGroupEditor.SWATCHES.length + (var9 ? 1 : 0);
         this.left = var2 + var4 - this.count * 12.5F + 1.5F;
         label(var1, this.setting.name(), var2, var8, var4 - this.count * 12.5F - 8.0F, BlendMode.lerp(Icons.TEXT, -1, var7));

         for (int var11 = 0; var11 < this.count; var11++) {
            boolean var12 = var11 < var10;
            int var13 = var11 - var10;
            int var14 = var12
               ? Theme.follow(this.setting.defaultValue()) | 0xFF000000
               : (var13 < HudGroupEditor.SWATCHES.length ? HudGroupEditor.SWATCHES[var13] : this.setting.argb() | 0xFF000000);
            float var15 = this.left + var11 * 12.5F + 6.25F - 1.5F;
            boolean var16 = var12 ? this.setting.following() : !this.setting.following() && (var14 | 0xFF000000) == (this.setting.argb() | 0xFF000000);
            boolean var17 = var5.inside(var15 - 6.25F, var3, 12.5F, 22.0F);
            if (var17) {
               var5.claim(this, DrawContext.Cursor.HAND);
            }

            float var18 = 3.8F + (var17 ? 0.6F : 0.0F);
            if (var16) {
               var1.ring(var15, var8, var18 + 2.2F, 1.2F, -419430401);
            }

            if (var12) {
               var1.arc(var15, var8, var18, 1.4F, -90.0F, 360.0F, Icons.INDIGO, Icons.PINK, false);
               var1.circle(var15, var8, var18 - 1.4F, var14);
            } else {
               var1.circle(var15, var8, var18, var14);
            }
         }
      }

      @Override
      boolean mouseDown(float var1, float var2) {
         int var3 = (int)Math.floor((var1 - this.left + 1.5F) / 12.5F);
         int var4 = this.first();
         if (var3 >= 0 && var3 < this.count && var3 < var4 + HudGroupEditor.SWATCHES.length) {
            if (var3 < var4) {
               this.setting.follow();
            } else {
               int var5 = HudGroupEditor.SWATCHES[var3 - var4];
               this.setting.set(this.setting.allowsAlpha() ? this.setting.argb() & 0xFF000000 | var5 & 16777215 : var5);
            }

            SoundRequest.play(SoundEvent.TAB);
            return false;
         } else {
            return false;
         }
      }
   }

   private static final class ButtonControl extends HudGroupEditor.ControlRow {
      private final String label;
      private final String icon;
      private final Runnable action;

      ButtonControl(String var1, String var2, Runnable var3) {
         this.label = var1;
         this.icon = var2;
         this.action = var3;
      }

      @Override
      void render(Canvas var1, float var2, float var3, float var4, DrawContext var5, float var6) {
         float var7 = 17.0F;
         float var8 = var3 + 11.0F - var7 / 2.0F;
         boolean var9 = var5.inside(var2, var8, var4, var7);
         if (var9) {
            var5.claim(this, DrawContext.Cursor.HAND);
         }

         float var10 = this.hover.target(var9 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var6));
         var1.roundRect(var2, var8, var4, var7, var7 / 2.0F, BlendMode.lerp(268435455, BlendMode.withAlpha(Icons.PINK, 0.16F), var10));
         var1.roundRectOutline(var2, var8, var4, var7, var7 / 2.0F, 1.0F, BlendMode.lerp(352321535, BlendMode.withAlpha(Icons.PINK, 0.5F), var10));
         int var11 = BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var10);
         float var12 = var1.textWidth(Fonts.MEDIUM, this.label, 8.0F);
         float var13 = var2 + var4 / 2.0F - (var12 + 14.0F) / 2.0F;
         var1.icon(this.icon, var13 + 5.0F, var8 + var7 / 2.0F, 9.0F, var11);
         var1.text(Fonts.MEDIUM, this.label, var13 + 14.0F, var8 + var7 / 2.0F, 8.0F, var11, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      @Override
      boolean mouseDown(float var1, float var2) {
         SoundRequest.play(SoundEvent.CLICK);
         this.action.run();
         return false;
      }
   }

   private static final class NumberControl extends HudGroupEditor.ControlRow {
      private static final float TRACK = 74.0F;
      private final NumberSetting setting;
      private final BooleanSupplier shown;
      private final String name;
      private final Animator knob;
      private final Animator active = Animator.snappy(0.0F);
      private boolean dragging;
      private float trackX;

      NumberControl(NumberSetting var1, BooleanSupplier var2, String var3) {
         this.setting = var1;
         this.shown = var2;
         this.name = var3;
         this.knob = Animator.snappy((float)var1.normalized());
      }

      @Override
      boolean visible() {
         return this.shown.getAsBoolean();
      }

      @Override
      void render(Canvas var1, float var2, float var3, float var4, DrawContext var5, float var6) {
         float var7 = this.highlight(var1, var5, var2, var3, var4, var6);
         float var8 = var3 + 11.0F;
         float var9 = (float)this.setting.normalized();
         if (this.dragging) {
            this.knob.snap(var9);
         } else {
            this.knob.target(var9).update(MotionStyle.TOGGLE.dt(var6));
         }

         float var10 = this.active.target(this.dragging ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var6));
         this.trackX = var2 + var4 - 74.0F - 3.0F;
         String var11 = this.setting.display();
         float var12 = var1.textWidth(Fonts.MEDIUM, var11, 7.5F);
         label(var1, this.name, var2, var8, var4 - 74.0F - var12 - 18.0F, BlendMode.lerp(Icons.TEXT, -1, var7));
         var1.text(
            Fonts.MEDIUM,
            var11,
            this.trackX - 7.0F,
            var8 + Fonts.MEDIUM.capHeight * 8.5F * 0.5F,
            7.5F,
            BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, Math.max(var7, var10)),
            FontStyle.RIGHT,
            FontWeight.BASELINE
         );
         if (var5.inside(this.trackX - 6.0F, var3, 86.0F, 22.0F)) {
            var5.claim(this, DrawContext.Cursor.HAND);
         }

         float var13 = 3.0F;
         float var14 = Math.max(0.0F, Math.min(1.0F, this.knob.value()));
         float var15 = this.trackX + 74.0F * var14;
         var1.roundRect(this.trackX, var8 - var13 / 2.0F, 74.0F, var13, var13 / 2.0F, BlendMode.lerp(536870911, 738197503, var7));
         var1.roundRectGradient(this.trackX, var8 - var13 / 2.0F, Math.max(var13, var15 - this.trackX), var13, var13 / 2.0F, 0.0F, Icons.INDIGO, Icons.MAGENTA);
         float var16 = 4.3F + 0.7F * var7 + 0.8F * var10;
         if (var10 > 0.01F) {
            var1.glowCircle(var15, var8, var16, 10.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.4F * var10));
         }

         var1.shadow(var15 - var16, var8 - var16, var16 * 2.0F, var16 * 2.0F, var16, 5.0F, 0.0F, 0.0F, 1.0F, 1711276032);
         var1.circle(var15, var8, var16, -1);
      }

      @Override
      boolean mouseDown(float var1, float var2) {
         if (!(var1 < this.trackX - 6.0F) && !(var1 > this.trackX + 74.0F + 6.0F)) {
            this.dragging = true;
            this.apply(var1);
            return true;
         } else {
            return false;
         }
      }

      @Override
      void mouseDrag(float var1, float var2) {
         if (this.dragging) {
            this.apply(var1);
         }
      }

      @Override
      void mouseUp() {
         this.dragging = false;
      }

      private void apply(float var1) {
         double var2 = this.setting.get();
         this.setting.setNormalized((var1 - this.trackX) / 74.0F);
         if (this.setting.get() != var2) {
            SoundRequest.play(SoundEvent.SLIDER_TICK, 0.85F + 0.45F * (float)this.setting.normalized(), 0.8F);
         }
      }
   }

   private final class ToggleControl extends HudGroupEditor.ControlRow {
      private final String name;
      private final BooleanSupplier value;
      private final Consumer<Boolean> setter;
      private final BooleanSupplier shown;
      private final Spacer toggle;
      private float x;
      private float w;

      ToggleControl(String nullx, BooleanSupplier nullxx, Consumer<Boolean> nullxxx, BooleanSupplier nullxxxx) {
         this.name = nullx;
         this.value = nullxx;
         this.setter = nullxxx;
         this.shown = nullxxxx;
         this.toggle = new Spacer(nullxx.getAsBoolean());
      }

      @Override
      boolean visible() {
         return this.shown.getAsBoolean();
      }

      @Override
      void render(Canvas var1, float var2, float var3, float var4, DrawContext var5, float var6) {
         this.x = var2;
         this.w = var4;
         float var7 = this.highlight(var1, var5, var2, var3, var4, var6);
         if (var5.inside(var2 - 5.0F, var3 + 1.0F, var4 + 10.0F, 20.0F)) {
            var5.claim(this, DrawContext.Cursor.HAND);
         }

         float var8 = var3 + 11.0F;
         label(var1, this.name, var2, var8, var4 - 34.0F, BlendMode.lerp(Icons.TEXT, -1, var7));
         this.toggle.render(var5, var2 + var4 - 22.0F, var8 - 6.0F, 22.0F, 12.0F, this.value.getAsBoolean(), var7 > 0.5F, false);
      }

      @Override
      boolean mouseDown(float var1, float var2) {
         if (!(var1 < this.x - 5.0F) && !(var1 > this.x + this.w + 5.0F)) {
            boolean var3 = !this.value.getAsBoolean();
            this.setter.accept(var3);
            SoundRequest.play(var3 ? SoundEvent.TOGGLE_ON : SoundEvent.TOGGLE_OFF);
            return false;
         } else {
            return false;
         }
      }
   }

   private abstract static class ControlRow {
      final Animator presence = Animator.smooth(1.0F);
      final Animator hover = Animator.smooth(0.0F);
      float y = Float.NaN;

      float height() {
         return 22.0F;
      }

      float amount() {
         return Math.max(0.0F, Math.min(1.0F, this.presence.value()));
      }

      boolean visible() {
         return true;
      }

      abstract void render(Canvas var1, float var2, float var3, float var4, DrawContext var5, float var6);

      boolean mouseDown(float var1, float var2) {
         return false;
      }

      void mouseDrag(float var1, float var2) {
      }

      void mouseUp() {
      }

      static void label(Canvas var0, String var1, float var2, float var3, float var4, int var5) {
         var0.text(Fonts.MEDIUM, FontMetrics.ellipsize(Fonts.MEDIUM, var1, 8.5F, var4, 0.0F), var2, var3, 8.5F, var5, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      final float highlight(Canvas var1, DrawContext var2, float var3, float var4, float var5, float var6) {
         boolean var7 = var2.inside(var3 - 5.0F, var4 + 1.0F, var5 + 10.0F, this.height() - 2.0F);
         float var8 = this.hover.target(var7 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var6));
         if (var8 > 0.01F) {
            var1.roundRect(var3 - 5.0F, var4 + 1.0F, var5 + 10.0F, this.height() - 2.0F, 7.0F, BlendMode.withAlpha(-1, 0.045F * var8));
         }

         return var8;
      }
   }

   private static final class SpacerRow extends HudGroupEditor.ControlRow {
      final String title;
      final List<HudGroupEditor.ControlRow> members = new ArrayList<>();

      SpacerRow(String var1) {
         this.title = var1;
      }

      @Override
      float height() {
         return 19.0F;
      }

      @Override
      boolean visible() {
         for (HudGroupEditor.ControlRow var2 : this.members) {
            if (var2.visible()) {
               return true;
            }
         }

         return this.members.isEmpty();
      }

      @Override
      void render(Canvas var1, float var2, float var3, float var4, DrawContext var5, float var6) {
         String var7 = Translations.tr(this.title).toUpperCase(Locale.ROOT);
         var1.text(Fonts.SEMIBOLD, var7, var2, var3 + 19.0F - 6.0F, 6.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE, 0.08F);
      }
   }

   private static final class ModeControl extends HudGroupEditor.ControlRow {
      private static final float H = 15.0F;
      private final ModeSetting setting;
      private final BooleanSupplier shown;
      private final String name;
      private final Animator highlightX = Animator.snappy(0.0F);
      private final Animator highlightW = Animator.snappy(0.0F);
      private final float[] optionX;
      private final float[] optionW;
      private boolean first = true;
      private float left;
      private float right;
      private float boxW;

      ModeControl(ModeSetting var1, BooleanSupplier var2, String var3) {
         this.setting = var1;
         this.shown = var2;
         this.name = var3;
         this.optionX = new float[var1.modes().size()];
         this.optionW = new float[var1.modes().size()];
      }

      @Override
      boolean visible() {
         return this.shown.getAsBoolean();
      }

      private boolean segmented() {
         if (this.setting.modes().size() > 3) {
            return false;
         } else {
            float var1 = 4.0F;

            for (String var3 : this.setting.modes()) {
               var1 += Fonts.MEDIUM.width(var3, 7.5F) + 12.0F;
            }

            return var1 <= 112.0F;
         }
      }

      @Override
      void render(Canvas var1, float var2, float var3, float var4, DrawContext var5, float var6) {
         float var7 = this.highlight(var1, var5, var2, var3, var4, var6);
         float var8 = var3 + 11.0F;
         float var9 = var8 - 7.5F;
         List var10 = this.setting.modes();
         int var11 = this.setting.index();
         this.right = var2 + var4;
         if (this.segmented()) {
            float var12 = 4.0F;

            for (int var13 = 0; var13 < var10.size(); var13++) {
               this.optionW[var13] = Fonts.MEDIUM.width((CharSequence)var10.get(var13), 7.5F) + 12.0F;
               var12 += this.optionW[var13];
            }

            this.boxW = var12;
            this.left = var2 + var4 - var12;
            label(var1, this.name, var2, var8, var4 - var12 - 8.0F, BlendMode.lerp(Icons.TEXT, -1, var7));
            var1.roundRect(this.left, var9, var12, 15.0F, 7.5F, 352321535);
            float var20 = this.left + 2.0F;

            for (int var14 = 0; var14 < var10.size(); var14++) {
               this.optionX[var14] = var20;
               var20 += this.optionW[var14];
            }

            if (this.first) {
               this.highlightX.snap(this.optionX[var11] - this.left);
               this.highlightW.snap(this.optionW[var11]);
               this.first = false;
            }

            float var23 = this.left + this.highlightX.target(this.optionX[var11] - this.left).update(MotionStyle.TOGGLE.dt(var6));
            float var15 = this.highlightW.target(this.optionW[var11]).update(MotionStyle.TOGGLE.dt(var6));
            var1.roundRectGradient(var23, var9 + 2.0F, var15, 11.0F, 5.5F, 0.0F, Icons.INDIGO, Icons.PURPLE);

            for (int var16 = 0; var16 < var10.size(); var16++) {
               boolean var17 = var5.inside(this.optionX[var16], var9, this.optionW[var16], 15.0F);
               if (var17) {
                  var5.claim(this.optionX, DrawContext.Cursor.HAND);
               }

               int var18 = var16 == var11 ? Icons.TEXT : (var17 ? BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, 0.7F) : Icons.TEXT_2);
               var1.text(
                  Fonts.MEDIUM,
                  (CharSequence)var10.get(var16),
                  this.optionX[var16] + this.optionW[var16] / 2.0F,
                  var8,
                  7.5F,
                  var18,
                  FontStyle.CENTER,
                  FontWeight.MIDDLE
               );
            }
         } else {
            float var19 = 0.0F;

            for (String var24 : (Iterable<String>)(Object)(var10)) {
               var19 = Math.max(var19, Fonts.MEDIUM.width(var24, 7.5F));
            }

            float var22 = Math.min(var19 + 34.0F, var4 * 0.62F);
            this.boxW = var22;
            this.left = var2 + var4 - var22;
            label(var1, this.name, var2, var8, var4 - var22 - 8.0F, BlendMode.lerp(Icons.TEXT, -1, var7));
            var1.roundRect(this.left, var9, var22, 15.0F, 7.5F, 352321535);
            boolean var25 = var5.inside(this.left, var9, var22, 15.0F);
            if (var25) {
               var5.claim(this.optionX, DrawContext.Cursor.HAND);
            }

            boolean var26 = var25 && var5.mouseX < this.left + 14.0F;
            boolean var27 = var25 && !var26;
            var1.icon("chevron-left", this.left + 8.0F, var8, 8.0F, var26 ? Icons.TEXT : Icons.TEXT_3);
            var1.icon("chevron-right", this.left + var22 - 8.0F, var8, 8.0F, var27 ? Icons.TEXT : Icons.TEXT_3);
            var1.text(
               Fonts.MEDIUM,
               FontMetrics.ellipsize(Fonts.MEDIUM, (String)var10.get(var11), 7.5F, var22 - 30.0F, 0.0F),
               this.left + var22 / 2.0F,
               var8,
               7.5F,
               Icons.TEXT,
               FontStyle.CENTER,
               FontWeight.MIDDLE
            );
         }
      }

      @Override
      boolean mouseDown(float var1, float var2) {
         if (!(var1 < this.left) && !(var1 > this.right)) {
            if (this.segmented()) {
               for (int var3 = 0; var3 < this.optionX.length; var3++) {
                  if (var1 >= this.optionX[var3] && var1 < this.optionX[var3] + this.optionW[var3] && var3 != this.setting.index()) {
                     this.setting.select(var3);
                     SoundRequest.play(SoundEvent.TAB);
                  }
               }
            } else {
               this.setting.select(this.setting.index() + (var1 < this.left + 14.0F ? -1 : 1));
               SoundRequest.play(SoundEvent.TAB);
            }

            return false;
         } else {
            return false;
         }
      }
   }
}
