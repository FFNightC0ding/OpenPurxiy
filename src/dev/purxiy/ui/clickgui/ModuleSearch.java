package dev.purxiy.ui.clickgui;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.modules.Module;
import dev.purxiy.modules.SettingSection;
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
import dev.purxiy.ui.widget.Spacer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

final class ModuleSearch {
   private static final float WIDTH = 560.0F;
   private static final float INPUT_H = 52.0F;
   private static final float ROW_H = 50.0F;
   private static final int MAX_RESULTS = 7;
   private final Animator open = new Animator(0.0F, 320.0F, 34.0F);
   private final Animator highlight = Animator.snappy(0.0F);
   private final Animator caretMove = Animator.snappy(0.0F);
   private final StringBuilder query = new StringBuilder();
   private final List<Module> results = new ArrayList<>();
   private final Map<Module, Spacer> switches = new HashMap<>();
   private final Object[] rowKeys = new Object[7];
   private boolean active;
   private int selected;
   private float lastType = -10.0F;
   private float resultsStart;
   private float x;
   private float inputY;
   private float listY;

   ModuleSearch() {
      for (int var1 = 0; var1 < 7; var1++) {
         this.rowKeys[var1] = new Object();
      }
   }

   boolean active() {
      return this.active;
   }

   boolean visible() {
      return this.active || this.open.value() > 0.01F;
   }

   void show(float var1) {
      if (!this.active) {
         this.active = true;
         this.open.target(1.0F);
         this.query.setLength(0);
         this.refresh(var1);
         SoundRequest.play(SoundEvent.EXPAND);
      }
   }

   void hide() {
      if (this.active) {
         this.active = false;
         this.open.target(0.0F);
         SoundRequest.play(SoundEvent.COLLAPSE);
      }
   }

   private void refresh(float var1) {
      String var2 = this.query.toString().trim().toLowerCase(Locale.ROOT);
      ArrayList var3 = new ArrayList();

      for (Module var5 : SettingSection.all()) {
         int var6 = var2.isEmpty() ? 0 : score(var2, var5);
         if (var6 >= 0) {
            var3.add(new ModuleSearch.SearchHit(var5, var6));
         }
      }

      var3.sort(Comparator.<ModuleSearch.SearchHit>comparingInt(var0 -> -var0.score).thenComparing(var0 -> var0.module.name()));
      this.results.clear();

      for (int var7 = 0; var7 < var3.size() && var7 < 7; var7++) {
         this.results.add(((ModuleSearch.SearchHit)var3.get(var7)).module);
      }

      this.selected = 0;
      this.resultsStart = var1;
   }

   static int score(String var0, Module var1) {
      int var2 = Math.max(fuzzy(var0, var1.name().toLowerCase(Locale.ROOT)), fuzzy(var0, Translations.tr(var1.name()).toLowerCase(Locale.ROOT)));
      if (var2 >= 0) {
         var2 += 1000;
      }

      int var3 = Math.max(
         fuzzy(var0, var1.category().displayName.toLowerCase(Locale.ROOT)), fuzzy(var0, Translations.tr(var1.category().displayName).toLowerCase(Locale.ROOT))
      );
      if (var3 >= 0) {
         var2 = Math.max(var2, 300 + var3 / 2);
      }

      int var4 = !var1.description().toLowerCase(Locale.ROOT).contains(var0) && !Translations.tr(var1.description()).toLowerCase(Locale.ROOT).contains(var0)
         ? -1
         : 200;
      return Math.max(var2, var4);
   }

   private static int fuzzy(String var0, String var1) {
      if (var1.startsWith(var0)) {
         return 900 - var1.length();
      } else {
         int var2 = var1.indexOf(var0);
         if (var2 >= 0) {
            boolean var10 = var1.charAt(var2 - 1) == ' ' || var1.charAt(var2 - 1) == '-';
            return (var10 ? 800 : 600) - var2;
         } else {
            int var3 = 0;
            int var4 = 0;
            int var5 = 0;

            for (int var6 = 0; var6 < var0.length(); var6++) {
               char var7 = var0.charAt(var6);
               int var8 = var1.indexOf(var7, var4);
               if (var8 < 0) {
                  return -1;
               }

               boolean var9 = var8 == 0 || var1.charAt(var8 - 1) == ' ';
               var5 = var8 == var4 ? var5 + 1 : 0;
               var3 += 1 + (var9 ? 12 : 0) + var5 * 6;
               var4 = var8 + 1;
            }

            return var3;
         }
      }
   }

   void render(DrawContext var1) {
      float var2 = this.open.update(MotionStyle.MENU.dt(var1.dt));
      if (!(var2 <= 0.01F) || this.active) {
         Canvas var3 = var1.canvas;
         float var4 = var3.width();
         float var5 = var3.height();
         float var6 = Math.max(0.0F, Math.min(1.0F, var2));
         var3.rect(0.0F, 0.0F, var4, var5, BlendMode.withAlpha(Theme.tint(-16514294), 0.35F * var6));
         var3.captureBackdrop();
         this.x = var4 / 2.0F - 280.0F;
         this.inputY = 70.0F - (1.0F - var6) * 16.0F;
         var3.pushAlpha(var6);
         KeyLabel.surface(var3, this.x, this.inputY, 560.0F, 52.0F, 26.0F, 1.0F, 0.45F);
         var3.icon("search", this.x + 28.0F, this.inputY + 26.0F, 18.0F, Icons.PURPLE);
         float var7 = this.x + 52.0F;
         float var8 = this.inputY + 26.0F;
         String var9 = this.query.toString();
         float var10 = Fonts.MEDIUM.measure(var9, 16.0F, 0.0F);
         if (var9.isEmpty()) {
            var3.text(Fonts.MEDIUM, "Search modules…", var7, var8, 16.0F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
         } else {
            Translations.pause();
            var3.text(Fonts.MEDIUM, var9, var7, var8, 16.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
            Translations.resume();
         }

         float var11 = this.caretMove.target(var10).update(MotionStyle.TOGGLE.dt(var1.dt));
         float var12 = var1.time - this.lastType < 0.5F ? 1.0F : 0.5F + 0.5F * (float)Math.cos((var1.time - this.lastType - 0.5F) * 5.5F);
         var3.roundRect(var7 + var11 + 1.5F, var8 - 10.0F, 2.0F, 20.0F, 1.0F, BlendMode.withAlpha(Icons.MAGENTA, Math.max(0.0F, var12)));
         KeyLabel.keyCap(var3, "Esc", this.x + 560.0F - 22.0F - 30.0F, var8, 9.5F, Icons.TEXT_2);
         this.listY = this.inputY + 52.0F + 12.0F;
         if (this.results.isEmpty()) {
            float var16 = 64.0F;
            KeyLabel.surface(var3, this.x, this.listY, 560.0F, var16, 20.0F, 1.0F, 0.0F);
            String var17 = Translations.tr("No modules match “%s”").replace("%s", var9);
            Translations.pause();
            var3.text(Fonts.MEDIUM, var17, this.x + 280.0F, this.listY + var16 / 2.0F, 13.0F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
            Translations.resume();
         } else {
            float var13 = this.results.size() * 50.0F + 12.0F;
            KeyLabel.surface(var3, this.x, this.listY, 560.0F, var13, 20.0F, 1.0F, 0.0F);
            float var14 = this.highlight.target(this.selected * 50.0F).update(MotionStyle.HOVER.dt(var1.dt));
            var3.roundRect(this.x + 6.0F, this.listY + 6.0F + var14, 548.0F, 50.0F, 14.0F, 352321535);
            var3.roundRectOutline(this.x + 6.0F, this.listY + 6.0F + var14, 548.0F, 50.0F, 14.0F, 1.0F, 352321535);

            for (int var15 = 0; var15 < this.results.size(); var15++) {
               this.row(var1, var3, var15, this.listY + 6.0F + var15 * 50.0F);
            }
         }

         var3.popAlpha();
      }
   }

   private void row(DrawContext var1, Canvas var2, int var3, float var4) {
      Module var5 = this.results.get(var3);
      float var6 = Easing.EMPHASIZED
         .apply(Math.max(0.0F, Math.min(1.0F, ((var1.time - this.resultsStart) * MotionStyle.CASCADE.speed() - var3 * 0.02F) / 0.3F)));
      if (var1.hover(this.rowKeys[var3], this.x + 6.0F, var4, 548.0F, 50.0F, DrawContext.Cursor.HAND) && this.selected != var3) {
         this.selected = var3;
      }

      var2.pushAlpha(var6);
      float var7 = (1.0F - var6) * 6.0F;
      boolean var8 = var3 == this.selected;
      KeyLabel.iconTile(var2, this.x + 18.0F, var4 + 9.0F + var7, 32.0F, 10.0F, var5.icon(), var5.switchedOn() ? 1.0F : 0.0F, var8 ? 1.0F : 0.0F);
      var2.text(Fonts.SEMIBOLD, var5.name(), this.x + 62.0F, var4 + 18.0F + var7, 14.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      var2.text(
         Fonts.REGULAR,
         Translations.tr(var5.category().displayName) + "  ·  " + Translations.tr(var5.description()),
         this.x + 62.0F,
         var4 + 35.0F + var7,
         11.0F,
         Icons.TEXT_3,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      float var9 = this.x + 560.0F - 22.0F;
      if (var5.toggleable()) {
         Spacer var10 = this.switches.computeIfAbsent(var5, var0 -> new Spacer(var0.switchedOn()));
         var10.render(var1, var9 - 38.0F, var4 + 25.0F - 11.0F + var7, 38.0F, 22.0F, var5.switchedOn(), var8, false);
      } else {
         var2.icon("chevron-right", var9 - 8.0F, var4 + 25.0F + var7, 16.0F, var8 ? Icons.TEXT : Icons.TEXT_3);
      }

      var2.popAlpha();
   }

   ModuleSearch.SearchAction charTyped(int var1, float var2) {
      if (this.active && !Character.isISOControl(var1)) {
         if (this.query.length() < 40) {
            this.query.appendCodePoint(var1);
            this.lastType = var2;
            this.refresh(var2);
            SoundRequest.play(SoundEvent.TYPE);
         }

         return ModuleSearch.SearchAction.NONE;
      } else {
         return ModuleSearch.SearchAction.NONE;
      }
   }

   ModuleSearch.SearchAction keyPressed(int var1, boolean var2, boolean var3, float var4) {
      if (!this.active) {
         return ModuleSearch.SearchAction.NONE;
      } else {
         switch (var1) {
            case 256:
               this.hide();
               return new ModuleSearch.SearchAction((Module)null, true);
            case 257:
            case 335:
               return this.activate(var2);
            case 258:
            case 264:
               this.move(var2 && var1 == 258 ? -1 : 1);
               break;
            case 259:
               if (this.query.isEmpty()) {
                  this.hide();
                  return new ModuleSearch.SearchAction((Module)null, true);
               }

               if (var3) {
                  this.query.setLength(0);
               } else {
                  this.query.setLength(this.query.offsetByCodePoints(this.query.length(), -1));
               }

               this.lastType = var4;
               this.refresh(var4);
               SoundRequest.play(SoundEvent.TYPE, 0.9F);
               break;
            case 262:
               if (!this.results.isEmpty()) {
                  Module var5 = this.results.get(this.selected);
                  this.hide();
                  return new ModuleSearch.SearchAction(var5, true);
               }
               break;
            case 265:
               this.move(-1);
         }

         return ModuleSearch.SearchAction.NONE;
      }
   }

   private void move(int var1) {
      if (!this.results.isEmpty()) {
         int var2 = Math.max(0, Math.min(this.results.size() - 1, this.selected + var1));
         if (var2 != this.selected) {
            this.selected = var2;
            SoundRequest.play(SoundEvent.HOVER);
         }
      }
   }

   private ModuleSearch.SearchAction activate(boolean var1) {
      if (this.results.isEmpty()) {
         SoundRequest.play(SoundEvent.DENY);
         return ModuleSearch.SearchAction.NONE;
      } else {
         Module var2 = this.results.get(this.selected);
         if (!var1 && var2.toggleable()) {
            var2.toggle();
            SoundRequest.play(var2.switchedOn() ? SoundEvent.TOGGLE_ON : SoundEvent.TOGGLE_OFF);
            return ModuleSearch.SearchAction.NONE;
         } else {
            this.hide();
            return new ModuleSearch.SearchAction(var2, true);
         }
      }
   }

   ModuleSearch.SearchAction mouseDown(float var1, float var2, int var3) {
      if (!this.active) {
         return ModuleSearch.SearchAction.NONE;
      } else if (var1 >= this.x && var1 <= this.x + 560.0F && var2 >= this.listY && var2 <= this.listY + this.results.size() * 50.0F + 12.0F) {
         int var4 = (int)((var2 - this.listY - 6.0F) / 50.0F);
         if (var4 >= 0 && var4 < this.results.size()) {
            this.selected = var4;
            return this.activate(var3 == 1);
         } else {
            return ModuleSearch.SearchAction.NONE;
         }
      } else if (var1 >= this.x && var1 <= this.x + 560.0F && var2 >= this.inputY && var2 <= this.inputY + 52.0F) {
         return ModuleSearch.SearchAction.NONE;
      } else {
         this.hide();
         return new ModuleSearch.SearchAction((Module)null, true);
      }
   }

   record SearchAction( Module openSettings, boolean close) {
      static final ModuleSearch.SearchAction NONE = new ModuleSearch.SearchAction((Module)null, false);
   }

   private record SearchHit(Module module, int score) {
   }
}
