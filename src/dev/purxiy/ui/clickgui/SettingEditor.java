package dev.purxiy.ui.clickgui;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.PaletteSetting;
import dev.purxiy.i18n.Translations;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.ItemPictures;
import dev.purxiy.render.RenderPipelines;
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
import dev.purxiy.ui.widget.Slider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.minecraft.class_1799;

final class SettingEditor {
   private static final float MAX_WIDTH = 800.0F;
   private static final float MAX_HEIGHT = 640.0F;
   private static final float PAD = 26.0F;
   private static final float HEADER = 92.0F;
   private static final float SEARCH_ROW = 46.0F;
   private static final float CHIP_ROW = 42.0F;
   private static final float FOOTER = 56.0F;
   private static final float TILE_H = 60.0F;
   private static final float GAP = 10.0F;
   private static final float MIN_TILE_W = 214.0F;
   private static final String ALL = "All";
   private static final String CHOSEN = "Chosen";
   private static final int[] SWATCHES = new int[]{
      16757852, 16740230, 15765855, 16177267, 10217082, 8253640, 6091494, 9423103, 9346047, 12160255, 16748498, 15920895
   };
   final PaletteSetting setting;
   private final List<PaletteSetting.PaletteEntry> choices;
   private final List<SettingEditor.EditorState> chips = new ArrayList<>();
   private final Map<String, class_1799> icons = new HashMap<>();
   private final Map<String, SettingEditor.SettingSlider> tiles = new HashMap<>();
   private List<PaletteSetting.PaletteEntry> shown = List.of();
   private String query = "";
   private String chip = "All";
   private int filtered = -1;
   private final Animator open = new Animator(0.0F, 230.0F, 28.0F);
   private final Animator scroll = Animator.smooth(0.0F);
   private final Animator chipX = Animator.snappy(0.0F);
   private final Animator chipW = Animator.snappy(0.0F);
   private boolean chipPlaced;
   private final Animator closeHover = Animator.smooth(0.0F);
   private final Animator allHover = Animator.smooth(0.0F);
   private final Animator clearHover = Animator.smooth(0.0F);
   private final Object closeKey = new Object();
   private final Object allKey = new Object();
   private final Object clearKey = new Object();
   private final Slider search;
   private boolean closing;
   private float scrollTarget;
   private float maxScroll;
   private float ox;
   private float oy;
   private float ow;
   private float oh;
   private float px;
   private float py;
   private float pw;
   private float ph;
   private float gridTop;
   private float gridBottom;
   private float allX;
   private float allW;
   private float clearX;
   private float clearW;
   private float actionY;
   private float actionH;
   private SettingEditor.EditorGeometry swatches;

   SettingEditor(final PaletteSetting var1, float var2, float var3, float var4, float var5) {
      this.setting = var1;
      this.choices = var1.choices();
      this.origin(var2, var3, var4, var5);
      this.open.target(1.0F);
      LinkedHashMap var6 = new LinkedHashMap();

      for (PaletteSetting.PaletteEntry var8 : this.choices) {
         var6.merge(var8.group(), 1, (a, b) -> (Integer)a + (Integer)b);
      }

      this.chips.add(new SettingEditor.EditorState("All", "All"));
      this.chips.add(new SettingEditor.EditorState("Chosen", "Chosen"));
      if (var6.size() > 1) {
         for (String var10 : (Iterable<String>)(Object)(var6.keySet())) {
            this.chips.add(new SettingEditor.EditorState(var10, var10));
         }
      }

      this.search = new Slider(new Slider.Listener() {
         @Override
         public String get() {
            return SettingEditor.this.query;
         }

         @Override
         public void set(String var1x) {
            SettingEditor.this.query = var1x;
            SettingEditor.this.filtered = -1;
            SettingEditor.this.scrollTarget = 0.0F;
         }

         @Override
         public int maxLength() {
            return 48;
         }


         @Override
         public String placeholder() {
            return Translations.tr("Search " + var1.plural());
         }
      }).icon("search");
   }

   void origin(float var1, float var2, float var3, float var4) {
      this.ox = var1;
      this.oy = var2;
      this.ow = var3;
      this.oh = var4;
   }

   float progress() {
      return Math.max(0.0F, Math.min(1.0F, this.open.value()));
   }

   boolean closing() {
      return this.closing;
   }

   boolean finished() {
      return this.closing && this.open.value() < 0.015F;
   }

   void close() {
      if (!this.closing) {
         this.closing = true;
         this.open.target(0.0F);
         this.search.blur();
         if (this.swatches != null) {
            this.swatches.close();
         }

         SoundRequest.play(SoundEvent.COLLAPSE);
      }
   }

   class_1799 icon(PaletteSetting.PaletteEntry var1) {
      return this.icons.computeIfAbsent(var1.id(), var1x -> var1.icon().get());
   }

   private void filter() {
      if (this.filtered != 0) {
         this.filtered = 0;
         String var1 = this.query.trim().toLowerCase(Locale.ROOT);
         ArrayList var2 = new ArrayList();

         for (PaletteSetting.PaletteEntry var4 : this.choices) {
            if ((this.chip.equals("Chosen") ? this.setting.has(var4.id()) : this.chip.equals("All") || var4.group().equals(this.chip))
               && (var1.isEmpty() || this.matches(var4, var1))) {
               var2.add(var4);
            }
         }

         this.shown = var2;
      }
   }

   private boolean matches(PaletteSetting.PaletteEntry var1, String var2) {
      String var3 = var1.literal() ? var1.name() : Translations.tr(var1.name());
      return var3.toLowerCase(Locale.ROOT).contains(var2)
         || var1.name().toLowerCase(Locale.ROOT).contains(var2)
         || var1.id().toLowerCase(Locale.ROOT).contains(var2);
   }

   private int count(String var1) {
      if (var1.equals("All")) {
         return this.choices.size();
      } else if (var1.equals("Chosen")) {
         return this.setting.count();
      } else {
         int var2 = 0;

         for (PaletteSetting.PaletteEntry var4 : this.choices) {
            if (var4.group().equals(var1)) {
               var2++;
            }
         }

         return var2;
      }
   }

   private SettingEditor.SettingSlider tile(PaletteSetting.PaletteEntry var1) {
      return this.tiles.computeIfAbsent(var1.id(), var1x -> new SettingEditor.SettingSlider(this.setting.has(var1x)));
   }

   void render(DrawContext var1) {
      Canvas var2 = var1.canvas;
      float var3 = this.open.update(MotionStyle.PANEL.dt(var1.dt));
      float var4 = Math.max(0.0F, Math.min(1.0F, var3));
      float var5 = var2.width();
      float var6 = var2.height();
      this.pw = Math.min(800.0F, var5 - 96.0F);
      this.ph = Math.min(640.0F, var6 - 92.0F);
      this.px = var5 / 2.0F - this.pw / 2.0F;
      this.py = Math.max(60.0F, var6 / 2.0F - this.ph / 2.0F + 8.0F);
      this.filter();
      var2.rect(0.0F, 0.0F, var5, var6, BlendMode.withAlpha(Theme.tint(-16514294), 0.36F * var4));
      var2.captureBackdrop();
      float var7 = lerp(this.ox, this.px, var3);
      float var8 = lerp(this.oy, this.py, var3);
      float var9 = lerp(this.ow, this.pw, var3);
      float var10 = lerp(this.oh, this.ph, var3);
      float var11 = lerp(this.oh / 2.0F, 24.0F, var4);
      KeyLabel.surface(var2, var7, var8, var9, var10, var11, lerp(0.25F, 1.0F, var4), 0.32F);
      float var12 = this.ox + 12.0F;
      float var13 = this.px + 26.0F;
      float var14 = lerp(20.0F, 42.0F, var3);
      float var15 = lerp(this.oy + this.oh / 2.0F, this.py + 26.0F + 21.0F, var3);
      stack(var2, this, this.setting, lerp(var12, var13, var3), var15, var14, 3);
      float var16 = stackWidth(this.setting, var14, 3);
      float var17 = smooth((var3 - 0.42F) / 0.58F);
      this.count(var2, var3, var17, lerp(var12, var13, var3) + var16 + 8.0F, var15);
      if (!(var17 <= 0.01F)) {
         var2.pushAlpha(var17);
         var2.pushClip(var7, var8, var9, var10, var11);
         float var18 = Math.max(0.01F, var9 / this.pw);
         var2.push();
         var2.translate(var7, var8);
         var2.scale(var18);
         var2.translate(-this.px, -this.py);
         boolean var19 = !this.closing && var4 > 0.92F && (this.swatches == null || this.swatches.closing);
         float var20 = (1.0F - var17) * 8.0F;
         float var21 = this.px + 26.0F + var16 + 14.0F;
         var2.text(Fonts.SEMIBOLD, this.setting.name(), var21, this.py + 26.0F + 4.0F + var20, 20.0F, Icons.TEXT);
         String var22 = this.setting.description() == null ? "" : this.setting.description();
         var2.text(
            Fonts.REGULAR,
            FontMetrics.ellipsize(Fonts.REGULAR, var22, 12.5F, this.pw - (var21 - this.px) - 26.0F - 150.0F, 0.0F),
            var21,
            this.py + 26.0F + 30.0F + var20,
            12.5F,
            Icons.TEXT_2
         );
         this.closeButton(var1, var2, var19);
         float var23 = this.py + 92.0F;
         var2.rect(this.px + 26.0F, var23 - 0.5F, this.pw - 52.0F, 1.0F, 318767103);
         float var24 = var23 + 12.0F + 16.0F + var20 * 1.4F;
         this.actions(var1, var2, var24, var19);
         this.search.render(var1, this.px + 26.0F, var24, this.allX - 10.0F - (this.px + 26.0F), var19);
         this.chips(var1, var2, var23 + 46.0F + 21.0F + 2.0F + var20 * 1.8F, var19);
         this.gridTop = var23 + 46.0F + 42.0F + 8.0F;
         this.gridBottom = this.py + this.ph - 56.0F;
         this.grid(var1, var2, var17, var19);
         this.footer(var2);
         var2.pop();
         var2.popClip();
         var2.popAlpha();
         if (this.swatches != null) {
            this.swatches.render(var1, var2);
            if (this.swatches.finished()) {
               this.swatches = null;
            }
         }
      }
   }

   private void count(Canvas var1, float var2, float var3, float var4, float var5) {
      int var6 = this.setting.count();
      String var7 = var6 == 0 ? Translations.tr("Choose") : var6 + " " + Translations.tr("chosen");
      float var8 = 1.0F - smooth(var2 / 0.4F);
      if (var8 > 0.01F) {
         var1.pushAlpha(var8);
         Translations.pause();
         var1.text(Fonts.SEMIBOLD, var7, var4, var5, 12.0F, var6 == 0 ? Icons.TEXT_2 : Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
         Translations.resume();
         var1.popAlpha();
      }

      if (var3 > 0.01F) {
         String var9 = var6 + " / " + this.choices.size();
         float var10 = var1.textWidth(Fonts.SEMIBOLD, var9, 11.0F);
         float var11 = var10 + 20.0F;
         float var12 = 22.0F;
         float var13 = this.px + this.pw - 26.0F - 30.0F - 12.0F - var11;
         float var14 = this.py + 26.0F + 15.0F - var12 / 2.0F;
         var1.pushAlpha(var3);
         var1.roundRect(var13, var14, var11, var12, var12 / 2.0F, BlendMode.withAlpha(Icons.VIOLET, 0.16F));
         var1.roundRectOutline(var13, var14, var11, var12, var12 / 2.0F, 1.0F, BlendMode.withAlpha(Icons.PURPLE, 0.5F));
         var1.text(Fonts.SEMIBOLD, var9, var13 + var11 / 2.0F, var14 + var12 / 2.0F, 11.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
         var1.popAlpha();
      }
   }

   private void closeButton(DrawContext var1, Canvas var2, boolean var3) {
      float var4 = 30.0F;
      float var5 = this.px + this.pw - 20.0F - var4;
      float var6 = this.py + 20.0F;
      boolean var7 = var3 && var1.hover(this.closeKey, var5, var6, var4, var4, DrawContext.Cursor.HAND);
      float var8 = this.closeHover.target(var7 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      var2.circle(var5 + var4 / 2.0F, var6 + var4 / 2.0F, var4 / 2.0F, BlendMode.withAlpha(-1, 0.05F + 0.08F * var8));
      var2.icon("x", var5 + var4 / 2.0F, var6 + var4 / 2.0F, 15.0F, BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var8));
   }

   private void actions(DrawContext var1, Canvas var2, float var3, boolean var4) {
      float var5 = 30.0F;
      float var6 = this.px + this.pw - 26.0F;
      String var7 = "Clear";
      String var8 = "Choose all";
      this.clearW = var2.textWidth(Fonts.SEMIBOLD, var7, 11.5F) + 40.0F;
      this.allW = var2.textWidth(Fonts.SEMIBOLD, var8, 11.5F) + 40.0F;
      this.clearX = var6 - this.clearW;
      this.allX = this.clearX - 8.0F - this.allW;
      this.actionY = var3 - var5 / 2.0F;
      this.actionH = var5;
      boolean var9 = false;
      boolean var10 = false;

      for (PaletteSetting.PaletteEntry var12 : this.shown) {
         boolean var13 = this.setting.has(var12.id());
         var9 |= !var13;
         var10 |= var13;
      }

      this.action(var1, var2, this.allKey, this.allHover, this.allX, this.allW, var3, "check-check", var8, var9 && var4);
      this.action(var1, var2, this.clearKey, this.clearHover, this.clearX, this.clearW, var3, "eraser", var7, var10 && var4);
   }

   private void action(DrawContext var1, Canvas var2, Object var3, Animator var4, float var5, float var6, float var7, String var8, String var9, boolean var10) {
      float var11 = this.actionH;
      boolean var12 = var10 && var1.hover(var3, var5, var7 - var11 / 2.0F, var6, var11, DrawContext.Cursor.HAND);
      float var13 = var4.target(var12 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      int var14 = var10 ? BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var13) : BlendMode.withAlpha(Icons.TEXT_3, 0.7F);
      var2.roundRect(var5, var7 - var11 / 2.0F, var6, var11, var11 / 2.0F, BlendMode.lerp(218103807, 419430399, var13));
      var2.roundRectOutline(
         var5, var7 - var11 / 2.0F, var6, var11, var11 / 2.0F, 1.0F, BlendMode.lerp(419430399, BlendMode.withAlpha(Icons.PURPLE, 0.6F), var13)
      );
      var2.icon(var8, var5 + 17.0F, var7, 13.0F, var14);
      var2.text(Fonts.SEMIBOLD, var9, var5 + 29.0F, var7, 11.5F, var14, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   private void chips(DrawContext var1, Canvas var2, float var3, boolean var4) {
      float var5 = this.px + 26.0F;
      float var6 = 28.0F;
      SettingEditor.EditorState var7 = null;

      for (SettingEditor.EditorState var9 : this.chips) {
         String var10 = Integer.toString(this.count(var9.name));
         var9.w = var2.textWidth(Fonts.SEMIBOLD, var9.label, 11.5F) + var2.textWidth(Fonts.MEDIUM, var10, 10.5F) + 34.0F;
         var9.x = var5;
         var5 += var9.w + 6.0F;
         if (var9.name.equals(this.chip)) {
            var7 = var9;
         }
      }

      if (var7 != null) {
         if (!this.chipPlaced) {
            this.chipX.snap(var7.x);
            this.chipW.snap(var7.w);
            this.chipPlaced = true;
         }

         float var15 = this.chipX.target(var7.x).update(MotionStyle.TOGGLE.dt(var1.dt));
         float var17 = this.chipW.target(var7.w).update(MotionStyle.TOGGLE.dt(var1.dt));
         var2.glow(var15, var3 - var6 / 2.0F, var17, var6, var6 / 2.0F, 14.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.22F));
         var2.roundRectGradient(
            var15, var3 - var6 / 2.0F, var17, var6, var6 / 2.0F, 0.0F, BlendMode.withAlpha(Icons.INDIGO, 0.85F), BlendMode.withAlpha(Icons.MAGENTA, 0.85F)
         );
         var2.roundRectCorners(var15, var3 - var6 / 2.0F, var17, var6, var6 / 2.0F, 822083583, 352321535, 16777215, 16777215);
      }

      for (SettingEditor.EditorState var18 : this.chips) {
         boolean var19 = var18 == var7;
         boolean var11 = var4 && !var19 && var1.hover(var18.key, var18.x, var3 - var6 / 2.0F, var18.w, var6, DrawContext.Cursor.HAND);
         float var12 = var18.hover.target(var11 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         if (!var19) {
            var2.roundRect(var18.x, var3 - var6 / 2.0F, var18.w, var6, var6 / 2.0F, BlendMode.lerp(184549375, 385875967, var12));
            var2.roundRectOutline(var18.x, var3 - var6 / 2.0F, var18.w, var6, var6 / 2.0F, 1.0F, BlendMode.lerp(385875967, 754974719, var12));
         }

         int var13 = var19 ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var12);
         float var14 = var2.textWidth(Fonts.SEMIBOLD, var18.label, 11.5F);
         var2.text(Fonts.SEMIBOLD, var18.label, var18.x + 14.0F, var3, 11.5F, var13, FontStyle.LEFT, FontWeight.MIDDLE);
         var2.text(
            Fonts.MEDIUM,
            Integer.toString(this.count(var18.name)),
            var18.x + 14.0F + var14 + 6.0F,
            var3,
            10.5F,
            var19 ? BlendMode.withAlpha(Icons.TEXT, 0.75F) : Icons.TEXT_3,
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
      }
   }

   private void grid(DrawContext var1, Canvas var2, float var3, boolean var4) {
      float var5 = this.px + 26.0F;
      float var6 = this.pw - 52.0F;
      float var7 = this.gridBottom - this.gridTop;
      int var8 = Math.max(1, (int)((var6 + 10.0F) / 224.0F));
      float var9 = (var6 - (var8 - 1) * 10.0F) / var8;
      int var10 = (this.shown.size() + var8 - 1) / var8;
      float var11 = var10 == 0 ? 0.0F : var10 * 70.0F - 10.0F;
      this.maxScroll = Math.max(0.0F, var11 - var7 + 6.0F);
      this.scrollTarget = Math.max(0.0F, Math.min(this.maxScroll, this.scrollTarget));
      float var12 = this.scroll.target(this.scrollTarget).update(MotionStyle.PANEL.dt(var1.dt));

      for (SettingEditor.SettingSlider var14 : this.tiles.values()) {
         var14.placed = false;
      }

      if (this.shown.isEmpty()) {
         this.empty(var2, this.gridTop + var7 / 2.0F);
      } else {
         var2.pushClip(this.px, this.gridTop - 4.0F, this.pw, var7 + 8.0F);
         int var27 = Math.max(0, (int)((var12 - 8.0F) / 70.0F));
         int var28 = Math.min(var10 - 1, (int)((var12 + var7 + 8.0F) / 70.0F));

         for (int var15 = var27; var15 <= var28; var15++) {
            float var16 = this.gridTop + 4.0F + var15 * 70.0F - var12;
            float var17 = var15 - var27;
            float var18 = smooth((var3 * 1.25F - var17 * 0.07F) / 0.5F);
            if (!(var18 <= 0.01F)) {
               float var19 = Math.min(smooth((var16 + 51.0F - this.gridTop) / 60.0F), smooth((this.gridBottom - var16 - 9.0F) / 60.0F));

               for (int var20 = 0; var20 < var8; var20++) {
                  int var21 = var15 * var8 + var20;
                  if (var21 >= this.shown.size()) {
                     break;
                  }

                  PaletteSetting.PaletteEntry var22 = this.shown.get(var21);
                  SettingEditor.SettingSlider var23 = this.tile(var22);
                  float var24 = var5 + var20 * (var9 + 10.0F);
                  float var25 = var16 + (1.0F - var18) * 12.0F;
                  boolean var26 = var4 && var18 > 0.95F && var16 + 30.0F > this.gridTop && var16 + 30.0F < this.gridBottom;
                  this.renderTile(var1, var2, var22, var23, var24, var25, var9, var18 * var19, var26);
                  var23.x = var24;
                  var23.y = var25;
                  var23.w = var9;
                  var23.placed = var26;
               }
            }
         }

         var2.popClip();
         if (this.maxScroll > 1.0F) {
            float var29 = var7 - 16.0F;
            float var30 = Math.max(28.0F, var29 * var7 / (var11 + 6.0F));
            float var31 = this.gridTop + 8.0F + (var29 - var30) * (var12 / this.maxScroll);
            float var32 = this.px + this.pw - 11.0F;
            var2.roundRect(var32, this.gridTop + 8.0F, 3.0F, var29, 1.5F, 218103807);
            var2.roundRect(var32, var31, 3.0F, var30, 1.5F, BlendMode.withAlpha(Icons.PURPLE, 0.55F));
         }
      }
   }

   private void renderTile(
      DrawContext var1, Canvas var2, PaletteSetting.PaletteEntry var3, SettingEditor.SettingSlider var4, float var5, float var6, float var7, float var8, boolean var9
   ) {
      boolean var10 = this.setting.has(var3.id());
      float var11 = var4.chosen.target(var10 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var12 = Math.max(0.0F, Math.min(1.0F, var11));
      float var13 = var6 + 30.0F;
      float var14 = var5 + var7 - 14.0F - 11.0F;
      float var15 = var14 - 11.0F - 16.0F;
      boolean var16 = var9 && var1.hover(var4.key, var5, var6, var7, 60.0F, DrawContext.Cursor.HAND);
      boolean var17 = var16 && Math.abs(var1.mouseX - var15) <= 11.0F && Math.abs(var1.mouseY - var13) <= 11.0F;
      if (var17) {
         var1.claim(var4.dotKey, DrawContext.Cursor.HAND);
      }

      float var18 = var4.hover.target(var16 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var19 = var4.dot.target(var17 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      int var20 = this.setting.colorOf(var3.id(), var3.color());
      var2.pushAlpha(var8);
      if (var12 > 0.01F) {
         var2.glow(var5, var6, var7, 60.0F, 14.0F, 18.0F, BlendMode.withAlpha(var20, 0.16F * var12));
      }

      var2.roundRect(
         var5, var6, var7, 60.0F, 14.0F, BlendMode.lerp(BlendMode.lerp(184549375, 369098751, var18), BlendMode.withAlpha(var20, 0.11F + 0.04F * var18), var12)
      );
      var2.roundRectOutline(
         var5, var6, var7, 60.0F, 14.0F, 1.0F, BlendMode.lerp(BlendMode.lerp(352321535, 654311423, var18), BlendMode.withAlpha(var20, 0.6F), var12)
      );
      this.ripple(var2, var4, var1.time, var5, var6, var7, var20);
      float var21 = 40.0F;
      float var22 = var5 + 10.0F;
      float var23 = var13 - var21 / 2.0F;
      var2.roundRect(var22, var23, var21, var21, 11.0F, BlendMode.lerp(251658239, BlendMode.withAlpha(var20, 0.16F), var12));
      if (!ItemPictures.draw(var2, this.icon(var3), var22 + 5.0F, var23 + 5.0F, var21 - 10.0F)) {
         var2.circle(var22 + var21 / 2.0F, var23 + var21 / 2.0F, 6.0F, 352321535);
      }

      float var24 = var22 + var21 + 12.0F;
      float var25 = var15 - 14.0F - var24;
      if (var3.literal()) {
         Translations.pause();
      }

      var2.text(
         Fonts.SEMIBOLD,
         FontMetrics.ellipsize(Fonts.SEMIBOLD, var3.name(), 12.5F, var25, 0.0F),
         var24,
         var13 - (var3.note() == null ? 0 : 8),
         12.5F,
         Icons.TEXT,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      if (var3.literal()) {
         Translations.resume();
      }

      if (var3.note() != null) {
         if (var3.literal()) {
            Translations.pause();
         }

         var2.text(
            Fonts.REGULAR,
            FontMetrics.ellipsize(Fonts.REGULAR, var3.note(), 10.5F, var25, 0.0F),
            var24,
            var13 + 9.0F,
            10.5F,
            Icons.TEXT_3,
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
         if (var3.literal()) {
            Translations.resume();
         }
      }

      boolean var26 = this.setting.customColor(var3.id()) != -1;
      if (var19 > 0.01F) {
         var2.circle(var15, var13, 10.0F, BlendMode.withAlpha(-1, 0.08F * var19));
      }

      var2.glowCircle(var15, var13, 4.5F, 8.0F, BlendMode.withAlpha(var20, 0.35F + 0.25F * var12));
      var2.circle(var15, var13, 4.5F + 0.8F * var19, 0xFF000000 | var20);
      if (var26) {
         var2.ring(var15, var13, 7.5F, 1.0F, BlendMode.withAlpha(var20, 0.7F));
      }

      var2.circle(var14, var13, 11.0F, BlendMode.lerp(BlendMode.lerp(150994943, 318767103, var18), 16777215, var12));
      var2.ring(var14, var13, 10.5F, 1.0F, BlendMode.withAlpha(-1, (0.22F + 0.1F * var18) * (1.0F - var12)));
      if (var12 > 0.01F) {
         var2.push();
         var2.scaleAround(0.55F + 0.45F * var11, var14, var13);
         var2.pushAlpha(var12);
         var2.glowCircle(var14, var13, 11.0F, 10.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.35F));
         var2.roundRectGradient(var14 - 11.0F, var13 - 11.0F, 22.0F, 22.0F, 11.0F, 135.0F, Icons.INDIGO, Icons.MAGENTA);
         var2.roundRectCorners(var14 - 11.0F, var13 - 11.0F, 22.0F, 22.0F, 11.0F, 956301311, 352321535, 16777215, 16777215);
         var2.icon("check", var14, var13 + 0.5F, 13.0F, Icons.TEXT);
         var2.popAlpha();
         var2.pop();
      }

      var2.popAlpha();
   }

   private void ripple(Canvas var1, SettingEditor.SettingSlider var2, float var3, float var4, float var5, float var6, int var7) {
      float var8 = (var3 - var2.rippleAt) * MotionStyle.TOGGLE.speed();
      if (!(var8 < 0.0F) && !(var8 > 0.6F)) {
         float var9 = var8 / 0.6F;
         float var10 = 1.0F - (1.0F - var9) * (1.0F - var9) * (1.0F - var9);
         float var11 = 16.0F + var10 * var6;
         var1.pushClip(var4, var5, var6, 60.0F, 14.0F);
         var1.glowCircle(var2.rippleX, var2.rippleY, var11 * 0.5F, var11, BlendMode.withAlpha(var7, 0.26F * (1.0F - var9) * (1.0F - var9)));
         var1.popClip();
      }
   }

   private void empty(Canvas var1, float var2) {
      float var3 = this.px + this.pw / 2.0F;
      var1.circle(var3, var2 - 26.0F, 22.0F, 285212671);
      var1.icon(this.chip.equals("Chosen") && this.query.isBlank() ? "list-checks" : "search-x", var3, var2 - 26.0F, 20.0F, Icons.TEXT_2);
      String var4 = this.chip.equals("Chosen") && this.query.isBlank() ? "Nothing chosen yet" : "Nothing found";
      String var5 = this.chip.equals("Chosen") && this.query.isBlank() ? "Choose something from All" : "Try another word, or another chip";
      var1.text(Fonts.SEMIBOLD, var4, var3, var2 + 10.0F, 14.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
      var1.text(Fonts.REGULAR, var5, var3, var2 + 31.0F, 12.0F, Icons.TEXT_3, FontStyle.CENTER, FontWeight.MIDDLE);
   }

   private void footer(Canvas var1) {
      float var2 = this.py + this.ph - 56.0F;
      var1.rect(this.px + 26.0F, var2, this.pw - 52.0F, 1.0F, 318767103);
      float var3 = var2 + 28.0F;
      int var4 = this.setting.count();
      String var5 = var4 + " " + Translations.plural(var4, this.setting.singular(), this.setting.plural()) + " " + Translations.tr("chosen");
      Translations.pause();
      var1.text(Fonts.MEDIUM, var5, this.px + 26.0F, var3, 12.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      Translations.resume();
      float var6 = this.px + this.pw - 26.0F;
      float var7 = var1.textWidth(Fonts.MEDIUM, "to go back", 11.0F);
      var1.text(Fonts.MEDIUM, "to go back", var6, var3, 11.0F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.MIDDLE);
      KeyLabel.keyCap(var1, "Esc", var6 - var7 - 8.0F - 32.0F, var3, 9.5F, Icons.TEXT_2);
   }

   static float stackWidth(PaletteSetting var0, float var1, int var2) {
      int var3 = Math.max(1, Math.min(var2, var0.count()));
      return var1 + (var3 - 1) * var1 * 0.62F;
   }

   static void stack(Canvas var0,  SettingEditor var1, PaletteSetting var2, float var3, float var4, float var5, int var6) {
      ArrayList var7 = new ArrayList(var6);
      if (var2.count() > 0) {
         for (PaletteSetting.PaletteEntry var9 : var2.choices()) {
            if (var2.has(var9.id())) {
               var7.add(var9);
               if (var7.size() >= var6) {
                  break;
               }
            }
         }
      }

      if (var7.isEmpty()) {
         KeyLabel.iconTile(var0, var3, var4 - var5 / 2.0F, var5, var5 * 0.3F, var2.icon(), 0.0F, 0.0F);
      } else {
         for (int var14 = var7.size() - 1; var14 >= 0; var14--) {
            PaletteSetting.PaletteEntry var15 = (PaletteSetting.PaletteEntry)var7.get(var14);
            float var10 = var3 + var5 / 2.0F + var14 * var5 * 0.62F;
            int var11 = var2.colorOf(var15.id(), var15.color());
            var0.circle(var10, var4, var5 / 2.0F + 1.5F, Theme.tint(-15593950));
            var0.circle(var10, var4, var5 / 2.0F, BlendMode.lerp(Theme.tint(-14870731), 0xFF000000 | var11, 0.18F));
            var0.ring(var10, var4, var5 / 2.0F - 0.5F, 1.0F, BlendMode.withAlpha(var11, 0.55F));
            class_1799 var12 = var1 != null ? var1.icon(var15) : SettingEditor.SettingToggle.of(var15);
            float var13 = var5 * 0.66F;
            ItemPictures.draw(var0, var12, var10 - var13 / 2.0F, var4 - var13 / 2.0F, var13);
         }
      }
   }

   void mouseDown(float var1, float var2, int var3) {
      if (!this.closing) {
         if (this.swatches != null && !this.swatches.closing) {
            this.swatches.mouseDown(var1, var2);
         } else {
            if (!this.search.contains(var1, var2)) {
               this.search.blur();
            }

            if (!(var1 < this.px) && !(var1 > this.px + this.pw) && !(var2 < this.py) && !(var2 > this.py + this.ph)) {
               float var4 = 30.0F;
               float var5 = this.px + this.pw - 20.0F - var4;
               float var6 = this.py + 20.0F;
               if (var1 >= var5 && var1 <= var5 + var4 && var2 >= var6 && var2 <= var6 + var4) {
                  this.close();
               } else if (!this.search.mouseDown(var1, var2)) {
                  if (var2 >= this.actionY && var2 <= this.actionY + this.actionH) {
                     if (var1 >= this.allX && var1 <= this.allX + this.allW) {
                        this.chooseShown(true);
                        return;
                     }

                     if (var1 >= this.clearX && var1 <= this.clearX + this.clearW) {
                        this.chooseShown(false);
                        return;
                     }
                  }

                  float var7 = this.py + 92.0F + 46.0F + 21.0F + 2.0F;
                  if (Math.abs(var2 - var7) <= 14.0F) {
                     for (SettingEditor.EditorState var9 : this.chips) {
                        if (var1 >= var9.x && var1 <= var9.x + var9.w) {
                           if (!var9.name.equals(this.chip)) {
                              this.chip = var9.name;
                              this.filtered = -1;
                              this.scrollTarget = 0.0F;
                              SoundRequest.play(SoundEvent.TAB);
                           }

                           return;
                        }
                     }
                  }

                  if (!(var2 < this.gridTop) && !(var2 > this.gridBottom)) {
                     for (PaletteSetting.PaletteEntry var15 : this.shown) {
                        SettingEditor.SettingSlider var10 = this.tiles.get(var15.id());
                        if (var10 != null && var10.placed && !(var1 < var10.x) && !(var1 > var10.x + var10.w) && !(var2 < var10.y) && !(var2 > var10.y + 60.0F)
                           )
                         {
                           float var11 = var10.y + 30.0F;
                           float var12 = var10.x + var10.w - 14.0F - 11.0F - 11.0F - 16.0F;
                           if (Math.abs(var1 - var12) <= 11.0F && Math.abs(var2 - var11) <= 11.0F) {
                              this.swatches = new SettingEditor.EditorGeometry(var15, var12, var11);
                              SoundRequest.play(SoundEvent.EXPAND);
                              return;
                           }

                           boolean var13 = !this.setting.has(var15.id());
                           this.setting.choose(var15.id(), var13);
                           var10.rippleAt = RenderPipelines.get().time();
                           var10.rippleX = var1;
                           var10.rippleY = var2;
                           SoundRequest.play(var13 ? SoundEvent.TOGGLE_ON : SoundEvent.TOGGLE_OFF);
                           return;
                        }
                     }
                  }
               }
            } else {
               this.close();
            }
         }
      }
   }

   private void chooseShown(boolean var1) {
      ArrayList var2 = new ArrayList(this.shown.size());

      for (PaletteSetting.PaletteEntry var4 : this.shown) {
         var2.add(var4.id());
      }

      this.setting.chooseAll(var2, var1);
      SoundRequest.play(var1 ? SoundEvent.TOGGLE_ON : SoundEvent.TOGGLE_OFF);
   }

   void mouseDrag(float var1, float var2) {
      this.search.mouseDrag(var1, var2);
   }

   void mouseUp() {
      this.search.mouseUp();
   }

   void scroll(double var1) {
      this.scrollTarget = (float)Math.max(0.0, Math.min((double)this.maxScroll, this.scrollTarget - var1 * 52.0));
   }

   void keyPressed(int var1) {
      if (this.swatches != null && !this.swatches.closing) {
         if (var1 == 256) {
            this.swatches.close();
         }
      } else if (var1 == 256) {
         this.close();
      } else if (this.search.focused()) {
         this.search.keyPressed(var1);
      }
   }

   void charTyped(int var1) {
      if (!this.closing && (this.swatches == null || this.swatches.closing)) {
         if (!this.search.focused()) {
            if (Character.isWhitespace(var1) || Character.isISOControl(var1)) {
               return;
            }

            this.search.focus();
         }

         this.search.charTyped(var1);
      }
   }

   private static float lerp(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   private static float smooth(float var0) {
      var0 = var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private final class EditorGeometry {
      private static final float SIZE = 22.0F;
      private static final float GAP_S = 8.0F;
      private static final float PAD_S = 12.0F;
      private static final float HEAD = 30.0F;
      final PaletteSetting.PaletteEntry choice;
      final float ax;
      final float ay;
      final Animator open = new Animator(0.0F, 320.0F, 27.0F);
      final Animator autoHover = Animator.smooth(0.0F);
      final Animator[] hover = new Animator[SettingEditor.SWATCHES.length];
      final Object autoKey = new Object();
      final Object[] keys = new Object[SettingEditor.SWATCHES.length];
      boolean closing;
      float x;
      float y;
      float w;
      float h;

      EditorGeometry(PaletteSetting.PaletteEntry nullx, float nullxx, float nullxxx) {
         this.choice = nullx;
         this.ax = nullxx;
         this.ay = nullxxx;
         this.open.target(1.0F);

         for (int var5 = 0; var5 < SettingEditor.SWATCHES.length; var5++) {
            this.hover[var5] = Animator.smooth(0.0F);
            this.keys[var5] = new Object();
         }
      }

      void close() {
         if (!this.closing) {
            this.closing = true;
            this.open.target(0.0F);
         }
      }

      boolean finished() {
         return this.closing && this.open.value() < 0.02F;
      }

      void render(DrawContext var1, Canvas var2) {
         float var3 = this.open.update(MotionStyle.PANEL.dt(var1.dt));
         float var4 = Math.max(0.0F, Math.min(1.0F, var3));
         byte var5 = 6;
         this.w = 24.0F + var5 * 22.0F + (var5 - 1) * 8.0F;
         this.h = 106.0F;
         this.x = Math.max(SettingEditor.this.px + 8.0F, Math.min(this.ax - this.w + 30.0F, SettingEditor.this.px + SettingEditor.this.pw - 8.0F - this.w));
         boolean var6 = this.ay + 16.0F + this.h < SettingEditor.this.py + SettingEditor.this.ph - 8.0F;
         this.y = var6 ? this.ay + 16.0F : this.ay - 16.0F - this.h;
         var2.push();
         var2.scaleAround(0.82F + 0.18F * var3, this.ax, this.ay);
         var2.pushAlpha(var4);
         var2.captureBackdrop();
         KeyLabel.surface(var2, this.x, this.y, this.w, this.h, 16.0F, 1.0F, 0.4F);
         var2.roundRect(this.x, this.y, this.w, this.h, 16.0F, Theme.tint(-938866668));
         boolean var7 = !this.closing && var4 > 0.9F;
         float var8 = this.y + 12.0F + 11.0F;
         int var9 = SettingEditor.this.setting.customColor(this.choice.id());
         var2.text(Fonts.SEMIBOLD, "COLOUR", this.x + 12.0F, var8, 10.0F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE, 0.12F);
         String var10 = "Auto";
         float var11 = var2.textWidth(Fonts.SEMIBOLD, var10, 10.5F) + 26.0F;
         float var12 = 20.0F;
         float var13 = this.x + this.w - 12.0F - var11;
         boolean var14 = var7 && var1.hover(this.autoKey, var13, var8 - var12 / 2.0F, var11, var12, DrawContext.Cursor.HAND);
         float var15 = this.autoHover.target(var14 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         var2.roundRect(
            var13,
            var8 - var12 / 2.0F,
            var11,
            var12,
            var12 / 2.0F,
            var9 == -1 ? BlendMode.withAlpha(Icons.VIOLET, 0.25F) : BlendMode.lerp(218103807, 419430399, var15)
         );
         var2.circle(var13 + 10.0F, var8, 3.5F, 0xFF000000 | this.choice.color());
         var2.text(Fonts.SEMIBOLD, var10, var13 + 17.0F, var8, 10.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);

         for (int var16 = 0; var16 < SettingEditor.SWATCHES.length; var16++) {
            float var17 = this.x + 12.0F + var16 % var5 * 30.0F + 11.0F;
            float var18 = this.y + 12.0F + 30.0F + var16 / var5 * 30.0F + 11.0F;
            boolean var19 = var7 && var1.hover(this.keys[var16], var17 - 11.0F, var18 - 11.0F, 22.0F, 22.0F, DrawContext.Cursor.HAND);
            float var20 = this.hover[var16].target(var19 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
            int var21 = 0xFF000000 | SettingEditor.SWATCHES[var16];
            boolean var22 = var9 != -1 && (var9 & 16777215) == SettingEditor.SWATCHES[var16];
            var2.glowCircle(var17, var18, 9.0F, 10.0F, BlendMode.withAlpha(var21, 0.25F + 0.3F * var20));
            var2.circle(var17, var18, 9.0F + 1.5F * var20, var21);
            if (var22) {
               var2.ring(var17, var18, 12.5F, 1.5F, Icons.TEXT);
            }
         }

         var2.popAlpha();
         var2.pop();
      }

      void mouseDown(float var1, float var2) {
         if (!(var1 < this.x) && !(var1 > this.x + this.w) && !(var2 < this.y) && !(var2 > this.y + this.h)) {
            float var3 = this.y + 12.0F + 11.0F;
            if (!(Math.abs(var2 - var3) <= 10.0F) || !(var1 >= this.x + this.w / 2.0F)) {
               byte var4 = 6;

               for (int var5 = 0; var5 < SettingEditor.SWATCHES.length; var5++) {
                  float var6 = this.x + 12.0F + var5 % var4 * 30.0F + 11.0F;
                  float var7 = this.y + 12.0F + 30.0F + var5 / var4 * 30.0F + 11.0F;
                  if (Math.abs(var1 - var6) <= 13.0F && Math.abs(var2 - var7) <= 13.0F) {
                     SettingEditor.this.setting.color(this.choice.id(), SettingEditor.SWATCHES[var5]);
                     SoundRequest.play(SoundEvent.CLICK);
                     this.close();
                     return;
                  }
               }
            } else {
               SettingEditor.this.setting.color(this.choice.id(), -1);
               SoundRequest.play(SoundEvent.CLICK);
               this.close();
            }
         } else {
            this.close();
         }
      }
   }

   private static final class EditorState {
      final String name;
      final String label;
      final Animator hover = Animator.smooth(0.0F);
      final Object key = new Object();
      float x;
      float w;

      EditorState(String var1, String var2) {
         this.name = var1;
         this.label = var2;
      }
   }

   private static final class SettingSlider {
      final Animator hover = Animator.smooth(0.0F);
      final Animator chosen;
      final Animator dot = Animator.smooth(0.0F);
      final Object key = new Object();
      final Object dotKey = new Object();
      float rippleAt = -10.0F;
      float rippleX;
      float rippleY;
      float x;
      float y;
      float w;
      boolean placed;

      SettingSlider(boolean var1) {
         this.chosen = Animator.snappy(var1 ? 1.0F : 0.0F);
      }
   }

   static final class SettingToggle {
      private static final Map<String, class_1799> CACHE = new HashMap<>();

      private SettingToggle() {
      }

      static class_1799 of(PaletteSetting.PaletteEntry var0) {
         return CACHE.computeIfAbsent(var0.id(), var1 -> var0.icon().get());
      }
   }
}
