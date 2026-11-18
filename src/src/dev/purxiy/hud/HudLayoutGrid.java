// Sourced By DevFFNightmare
package dev.purxiy.hud;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.modules.Category;
import dev.purxiy.modules.Module;
import dev.purxiy.modules.SettingSection;
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
import dev.purxiy.ui.widget.Spacer;
import java.util.ArrayList;
import java.util.List;

final class HudLayoutGrid {
   private static final int COLUMNS = 3;
   private static final float TILE_W = 92.0F;
   private static final float TILE_H = 70.0F;
   private static final float MINI_H = 41.0F;
   private static final float INSET = 4.0F;
   private static final float GAP = 6.0F;
   private static final float PAD = 11.0F;
   private static final float HEADER = 42.0F;
   private static final float FOOTER = 30.0F;
   private static final float INSET_TOP = 3.0F;
   private static final float INSET_BOTTOM = 11.0F;
   private static final float MAX_ROWS = 3.5F;
   private static final float RADIUS = 16.0F;
   private static final float TILE_RADIUS = 10.0F;
   private static final float MARGIN = 14.0F;
   private static final float OFFSET = 6.0F;
   private static final float SEED_W = 34.0F;
   private static final float SEED_H = 16.0F;
   private static final float WIDEN = 0.3F;
   private static final float UNFOLD_DELAY = 0.04F;
   private static final float UNFOLD = 0.46F;
   private static final float TILE_DELAY = 0.05F;
   private static final float TILE_IN = 0.3F;
   private static final float ROW_STAGGER = 0.04F;
   private static final float COLUMN_STAGGER = 0.015F;
   private static final float FLIGHT_LEAD = 0.16F;
   private static final float FLIGHT = 0.5F;
   private static final float EXIT = 0.32F;
   private static final float DRIFT = 6.0F;
   private static final float TILE_DRIFT = 10.0F;
   private static final Easing SHAPE = Easing.bezier(0.22F, 0.0F, 0.0F, 1.0F);
   private static final Easing RISE = Easing.bezier(0.2F, 0.0F, 0.0F, 1.0F);
   private static final Easing FLY = Easing.bezier(0.42F, 0.0F, 0.12F, 1.0F);
   private final List<HudLayoutGrid.TypeI> tiles = new ArrayList<>();
   private final HudBounds mini = new HudBounds();
   private final HudBounds sample = new HudBounds();
   private final float[] measured = new float[2];
   private final DrawContext frame = new DrawContext();
   private final Spacer glass = new Spacer(true);
   private final Animator closeHover = Animator.smooth(0.0F);
   private final Animator scroll = new Animator(0.0F, 260.0F, 32.0F);
   private float scrollTarget;
   private float scrollLimit;
   private final float[] sheet = new float[4];
   private final float[] view = new float[4];
   private float viewTop;
   private float viewBottom;
   private final float[] closeButton = new float[4];
   private final float[] glassToggle = new float[4];
   private boolean open;
   private boolean closing;
   private float clock;
   private float out;
   private float originX;
   private float originY;
   private boolean down;
   private boolean pointerDown;
   private float holdClock = -1.0F;
   private float holdOut = -1.0F;

   boolean visible() {
      return this.open;
   }

   boolean active() {
      return this.open && !this.closing;
   }

   float hudShown() {
      if (!this.open) {
         return 1.0F;
      } else {
         float var1 = clamp01(this.clock / 0.12F);
         return 1.0F - var1 * (1.0F - this.handOff());
      }
   }

   private float handOff() {
      return Easing.SMOOTHSTEP.apply(clamp01((this.out - 0.72F) / 0.28F));
   }

   boolean hand() {
      return this.open && this.frame.cursor() == DrawContext.TypeE.HAND;
   }

   boolean contains(float var1, float var2) {
      return this.active() && inside(this.sheet, var1, var2);
   }

   void open(float var1, float var2) {
      if (!this.active()) {
         if (!this.open) {
            this.clock = 0.0F;
            this.out = 0.0F;
            this.originX = var1;
            this.originY = var2;
            this.down = var2 < HudRegistry.layout().height / 2.0F;
            this.scroll.snap(0.0F);
            this.scrollTarget = 0.0F;
            this.tiles.clear();

            for (Module var4 : SettingSection.of(Category.HUD)) {
               this.tiles.add(new HudLayoutGrid.TypeI(var4, picture(var4)));
            }
         }

         this.open = true;
         this.closing = false;
         this.glass.snap(SettingSection.HUD.glass.enabled());
         SoundRequest.play(SoundEvent.OPEN, 1.08F, 0.75F);
      }
   }

   void close() {
      if (this.active()) {
         this.closing = true;
         this.pointerDown = false;
         SoundRequest.play(SoundEvent.CLOSE, 1.08F, 0.6F);
      }
   }

   void reset() {
      this.open = false;
      this.closing = false;
      this.clock = 0.0F;
      this.out = 0.0F;
      this.tiles.clear();
   }

   void hold(float var1, float var2) {
      this.holdClock = var1;
      this.holdOut = var2;
   }

   
   private static HudModule picture(Module var0) {
      List var1 = HudRegistry.parts(var0.id());
      return var1.isEmpty() ? null : (HudModule)var1.getLast();
   }

   void render(Canvas var1, HudBounds var2, float var3, float var4, float var5, float var6, boolean var7) {
      if (this.open) {
         int var8 = this.tiles.size();
         int var9 = var2.width - 28.0F >= sheetWidth(3) ? 3 : 2;
         int var10 = Math.max(1, (var8 + var9 - 1) / var9);
         float var11 = sheetWidth(var9);
         float var12 = 3.0F + var10 * 70.0F + (var10 - 1) * 6.0F + 11.0F;
         float var13 = this.down ? this.originY + 6.0F : this.originY - 6.0F;
         float var14 = (this.down ? var2.height - 14.0F - var13 : var13 - 14.0F) - 42.0F - 30.0F;
         float var15 = 84.0F;
         float var16 = Math.max(var15, Math.min(Math.min(var12, 269.0F), var14));
         float var17 = 42.0F + var16 + 30.0F;
         float var18 = clamp(this.originX - var11 / 2.0F, 14.0F, var2.width - 14.0F - var11);
         float var19 = clamp(this.down ? var13 : var13 - var17, 6.0F, var2.height - 6.0F - var17);
         set(this.sheet, var18, var19, var11, var17);
         this.viewTop = var19 + 42.0F;
         this.viewBottom = var19 + 42.0F + var16;
         this.scrollLimit = Math.max(0.0F, var12 - var16);
         this.scrollTarget = clamp(this.scrollTarget, 0.0F, this.scrollLimit);
         float var20 = this.scroll.target(this.scrollTarget).update(MotionStyle.PANEL.dt(var5));
         int var21 = (int)(var20 / 76.0F);
         int var22 = Math.max(var21, Math.min(var10 - 1, (int)((var20 + var16 - 3.0F) / 76.0F)));
         float var23 = 0.05F + (var22 - var21) * 0.04F + (var9 - 1) * 0.015F;
         float var24 = Math.max(0.5F, var23 + Math.max(0.36F, 0.34F));
         float var25 = MotionStyle.MENU.speed();
         if (this.closing) {
            this.out = Math.min(1.0F, this.out + var5 * var25 / 0.32F);
            if (this.out >= 1.0F && this.holdOut < 0.0F) {
               this.reset();
               return;
            }
         } else {
            this.out = Math.max(0.0F, this.out - var5 * var25 / 0.32F);
            this.clock = Math.min(this.clock + var5 * var25, var24);
         }

         if (this.holdClock >= 0.0F) {
            this.clock = Math.min(this.holdClock, var24);
         }

         if (this.holdOut >= 0.0F) {
            this.out = Math.min(1.0F, this.holdOut);
         }

         float var26 = this.out;
         float var27 = 1.0F - Easing.SMOOTHSTEP.apply(clamp01(var26 / 0.45F));
         float var28 = this.down ? 1.0F : -1.0F;
         float var29 = SHAPE.apply(clamp01(this.clock / 0.3F)) * (1.0F - Easing.CUBIC_IN_OUT.apply(clamp01((var26 - 0.2F) / 0.8F)));
         float var30 = SHAPE.apply(clamp01((this.clock - 0.04F) / 0.46F)) * (1.0F - Easing.CUBIC_IN_OUT.apply(clamp01(var26 / 0.8F)));
         float var31 = clamp01(this.clock / 0.07F) * (1.0F - Easing.SMOOTHSTEP.apply(clamp01((var26 - 0.45F) / 0.55F)));
         float var32 = this.originX;
         float var33 = lerp(var32 - 17.0F, var18, var29);
         float var34 = lerp(var32 + 17.0F, var18 + var11, var29);
         float var35 = lerp(this.down ? var13 : var13 - 16.0F, var19, var30);
         float var36 = lerp(this.down ? var13 + 16.0F : var13, var19 + var17, var30);
         float var37 = var34 - var33;
         float var38 = var36 - var35;
         float var39 = Math.min(lerp(8.0F, 16.0F, Math.min(var29, var30)), Math.min(var37, var38) / 2.0F);
         float var40 = (var33 + var34) / 2.0F - var11 / 2.0F;
         float var41 = Easing.STANDARD.apply(clamp01(this.clock / 0.3F)) * (1.0F - Easing.SMOOTHSTEP.apply(var26));
         var1.rect(-50.0F, -50.0F, var2.width + 100.0F, var2.height + 100.0F, BlendMode.withAlpha(Theme.tint(-16317170), 0.42F * var41));
         var1.captureBackdrop();
         if (var31 > 0.003F) {
            var1.pushAlpha(var31);
            KeyLabel.surface(var1, var33, var35, var37, var38, var39, 1.0F, 0.3F);
            var1.popAlpha();
         }

         float var42 = Math.max(this.viewTop, var35 + 42.0F);
         float var43 = Math.min(this.viewBottom, var36 - 30.0F);
         set(this.view, var40, var42, var11, Math.max(0.0F, var43 - var42));
         this.frame.begin(var1, var6, var5, var3, var4, this.pointerDown);
         this.frame.mask(this.closing || var7);
         boolean var44 = inside(this.view, var3, var4);
         var1.pushClip(var33, var35, var37, var38, var39);
         float var45 = RISE.apply(clamp01((this.clock - 0.07F) / 0.26F));
         float var46 = RISE.apply(clamp01((this.clock - 0.16F) / 0.3F));
         float var47 = this.down ? var45 : var46;
         float var48 = this.down ? var46 : var45;
         this.header(var1, var40, var35, var11, var5, var47 * var27, -var28 * (1.0F - var47) * 6.0F);
         this.footer(var1, var40, var36 - 30.0F, var11, var5, var48 * var27, -var28 * (1.0F - var48) * 6.0F);
         float var49 = Easing.CUBIC_IN_OUT.apply(clamp01(var26 / 0.72F));

         for (int var50 = 0; var50 < var8; var50++) {
            HudLayoutGrid.TypeI var51 = this.tiles.get(var50);
            int var52 = var50 % var9;
            int var53 = var50 / var9;
            float var54 = var40 + 11.0F + var52 * 98.0F;
            float var55 = this.viewTop + 3.0F + var53 * 76.0F - var20;
            set(var51.rect, var54, var55, 92.0F, 70.0F);
            var51.shown = clamp01(1.0F - (Math.max(0.0F, var42 - var55) + Math.max(0.0F, var55 + 70.0F - var43)) / 52.5F);
            var51.rest = clamp01(1.0F - (Math.max(0.0F, this.viewTop - var55) + Math.max(0.0F, var55 + 70.0F - this.viewBottom)) / 52.5F);
            int var56 = Math.max(0, Math.min(var22 - var21, this.down ? var53 - var21 : var22 - var53));
            float var57 = 0.05F + var56 * 0.04F + var52 * 0.015F;
            var51.in = RISE.apply(clamp01((this.clock - var57) / 0.3F));
            var51.fade = RISE.apply(clamp01((this.clock - var57 - 0.06F) / 0.3F));
            float var58 = Math.max(0.0F, var57 - 0.16F);
            float var59 = var57 - 0.16F + 0.5F;
            var51.flight = FLY.apply(clamp01((this.clock - var58) / (var59 - var58))) * (1.0F - var49);
         }

         var1.pushClip(var40, var42, var11, Math.max(0.0F, var43 - var42), var37 < var11 - 0.5F ? var39 : 0.0F);

         for (HudLayoutGrid.TypeI var62 : this.tiles) {
            this.card(var1, var62, var27, var5, var44);
         }

         var1.captureBackdrop();
         this.prepareContexts(var2);
         float var61 = this.handOff();

         for (HudLayoutGrid.TypeI var66 : this.tiles) {
            this.picture(var1, var66, var27, var61, false);
         }

         for (HudLayoutGrid.TypeI var67 : this.tiles) {
            this.caption(var1, var67, var27, var5, var44);
         }

         var1.popClip();
         this.edges(var1, var40, var11, var42, Math.max(0.0F, var43 - var42), var20, Math.min(var47, var48) * var27);
         var1.popClip();

         for (HudLayoutGrid.TypeI var68 : this.tiles) {
            this.picture(var1, var68, var27, var61, true);
         }
      }
   }

   private static float sheetWidth(int var0) {
      return 22.0F + var0 * 92.0F + (var0 - 1) * 6.0F;
   }

   private void header(Canvas var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = var3 + 21.0F + 1.0F;
      boolean var9 = var6 > 0.6F;
      this.closeButton[2] = 0.0F;
      if (!(var6 <= 0.01F)) {
         var1.pushAlpha(var6);
         var1.push();
         var1.translate(0.0F, var7);
         float var10 = 22.0F;
         float var11 = var2 + 11.0F;
         KeyLabel.iconTile(var1, var11, var8 - var10 / 2.0F, var10, 7.0F, "layout-grid", 1.0F, 0.0F);
         float var12 = var11 + var10 + 9.0F;
         var1.text(Fonts.SEMIBOLD, "HUD elements", var12, var8 - 5.5F, 10.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
         int var13 = 0;

         for (HudLayoutGrid.TypeI var15 : this.tiles) {
            var13 += var15.module.enabled() ? 1 : 0;
         }

         var1.text(Fonts.MEDIUM, var13 + " of " + this.tiles.size() + " active", var12, var8 + 6.5F, 7.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
         float var18 = 9.0F;
         float var19 = var2 + var4 - 11.0F - var18;
         boolean var16 = false;
         if (var9) {
            set(this.closeButton, var19 - var18, var8 - var18, var18 * 2.0F, var18 * 2.0F);
            var16 = this.frame.hover(this.closeButton, var19 - var18, var8 - var18, var18 * 2.0F, var18 * 2.0F, DrawContext.TypeE.HAND);
         }

         float var17 = this.closeHover.target(var16 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var5));
         var1.circle(var19, var8, var18, BlendMode.lerp(251658239, 587202559, var17));
         var1.icon("x", var19, var8, 9.0F, BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var17));
         var1.pop();
         var1.popAlpha();
      }
   }

   private void footer(Canvas var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      this.glassToggle[2] = 0.0F;
      if (!(var6 <= 0.01F)) {
         float var8 = var3 + 15.0F - 1.0F;
         var1.pushAlpha(var6);
         var1.push();
         var1.translate(0.0F, var7);
         var1.text(Fonts.MEDIUM, "Right-click a tile for its settings", var2 + 11.0F + 1.0F, var8, 7.0F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
         String var9 = SettingSection.HUD.glass.name();
         float var10 = 18.0F;
         float var11 = 10.0F;
         float var12 = var2 + var4 - 11.0F - var10;
         float var13 = var1.textWidth(Fonts.MEDIUM, var9, 7.5F);
         boolean var14 = false;
         if (var6 > 0.6F) {
            set(this.glassToggle, var12 - var13 - 9.0F, var8 - 8.0F, var13 + 9.0F + var10, 16.0F);
            var14 = this.frame.hover(this.glassToggle, this.glassToggle[0], this.glassToggle[1], this.glassToggle[2], 16.0F, DrawContext.TypeE.HAND);
         }

         var1.text(Fonts.MEDIUM, var9, var12 - 7.0F, var8, 7.5F, var14 ? Icons.TEXT : Icons.TEXT_2, FontStyle.RIGHT, FontWeight.MIDDLE);
         this.glass.render(this.frame, var12, var8 - var11 / 2.0F, var10, var11, SettingSection.HUD.glass.enabled(), var14, false);
         var1.pop();
         var1.popAlpha();
      }
   }

   private void edges(Canvas var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      if (!(var7 <= 0.01F) && !(this.scrollLimit <= 0.5F)) {
         var1.pushAlpha(var7);
         float var8 = clamp01(var6 / 14.0F);
         float var9 = clamp01((this.scrollLimit - var6) / 14.0F);
         var1.rect(var2 + 11.0F, var4 - 0.5F, var3 - 22.0F, 1.0F, BlendMode.withAlpha(-1, 0.07F * var8));
         var1.rect(var2 + 11.0F, var4 + var5 - 0.5F, var3 - 22.0F, 1.0F, BlendMode.withAlpha(-1, 0.07F * var9));
         float var10 = var5 - 16.0F;
         float var11 = Math.max(18.0F, var10 * var5 / (var5 + this.scrollLimit));
         float var12 = var4 + 8.0F + (var10 - var11) * (var6 / this.scrollLimit);
         var1.roundRect(var2 + var3 - 5.5F, var12, 2.0F, var11, 1.0F, 788529151);
         var1.popAlpha();
      }
   }

   private void card(Canvas var1, HudLayoutGrid.TypeI var2, float var3, float var4, boolean var5) {
      float[] var6 = var2.rect;
      boolean var7 = var5 && var2.shown > 0.5F && var2.in > 0.6F && this.frame.inside(var6[0], var6[1], var6[2], var6[3]);
      float var8 = var2.hover.target(var7 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var4));
      float var9 = var2.on.target(var2.module.enabled() ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var4));
      float var10 = var2.in * var3 * var2.shown;
      if (!(var10 <= 0.01F)) {
         var1.pushAlpha(var10);
         this.enter(var1, var2);
         if (var8 > 0.01F) {
            var1.glow(var6[0], var6[1], var6[2], var6[3], 10.0F, 14.0F, BlendMode.withAlpha(Icons.VIOLET, 0.14F * var8));
         }

         var1.roundRect(var6[0], var6[1], var6[2], var6[3], 10.0F, BlendMode.lerp(150994943, 335544319, Math.max(var8, var9 * 0.45F)));
         var1.roundRect(var6[0] + 4.0F, var6[1] + 4.0F, var6[2] - 8.0F, 41.0F, 7.0F, BlendMode.withAlpha(Theme.tint(-16448756), 0.36F));
         var1.roundRectOutlineGradient(
            var6[0],
            var6[1],
            var6[2],
            var6[3],
            10.0F,
            1.0F,
            90.0F,
            BlendMode.lerp(BlendMode.lerp(352321535, BlendMode.withAlpha(Icons.PURPLE, 0.3F), var9), BlendMode.withAlpha(Icons.PURPLE, 0.85F), var8),
            BlendMode.lerp(BlendMode.lerp(100663295, BlendMode.withAlpha(Icons.INDIGO, 0.1F), var9), BlendMode.withAlpha(Icons.INDIGO, 0.35F), var8)
         );
         var1.pop();
         var1.popAlpha();
      }
   }

   private void enter(Canvas var1, HudLayoutGrid.TypeI var2) {
      float[] var3 = var2.rect;
      var1.push();
      var1.translate(0.0F, this.lift(var2));
      var1.scaleAround(grow(var2), var3[0] + var3[2] / 2.0F, var3[1] + var3[3] / 2.0F);
   }

   private float lift(HudLayoutGrid.TypeI var1) {
      return (this.down ? -1 : 1) * (1.0F - var1.in) * 10.0F - var1.hover.value() * 1.5F;
   }

   private static float grow(HudLayoutGrid.TypeI var0) {
      return 0.94F + 0.06F * var0.in;
   }

   private void picture(Canvas var1, HudLayoutGrid.TypeI var2, float var3, float var4, boolean var5) {
      HudModule var6 = var2.element;
      float[] var7 = var2.rect;
      float var8 = var2.on.value();
      float var9 = var2.flight;
      float var10 = grow(var2);
      float var11 = var7[2] - 8.0F;
      float var12 = 41.0F;
      float var13 = var7[1] + var7[3] / 2.0F;
      float var14 = var7[0] + var7[2] / 2.0F;
      float var15 = var13 + (var7[1] + 4.0F + var12 / 2.0F - var13) * var10 + this.lift(var2);
      float var16 = var2.fade * var3 * var2.shown;
      if (var6 != null && (var2.module.enabled() || var2.module != SettingSection.byId("spotify_player"))) {
         boolean var17 = false;
         float var18;
         float var19;
         if (var17) {
            var18 = 120.0F;
            var19 = 48.0F;
         } else {
            if (!var6.laidOut) {
               var6.measure(this.mini, this.measured);
               var6.width = Math.max(1.0F, this.measured[0]);
               var6.height = Math.max(1.0F, this.measured[1]);
            }

            var18 = var6.width;
            var19 = var6.height;
         }

         float var20 = Math.min(1.0F, Math.min((var11 - 10.0F) / var18, (var12 - 7.0F) / var19)) * var10;
         boolean var21 = !var17 && var6.laidOut && var6.presence.value() > 0.5F;
         boolean var22 = !var21 || var9 > 0.985F;
         if (var5 != var22) {
            float var23;
            float var24;
            float var25;
            float var26;
            if (var21) {
               float var27 = var6.renderX.value() + var6.renderWidth() / 2.0F;
               float var28 = var6.renderY.value() + var6.renderHeight() / 2.0F;
               float var29 = var2.rest > 0.0F ? var15 : clamp(var15, this.viewTop + var12 / 2.0F, this.viewBottom - var12 / 2.0F);
               var23 = lerp(var27, var14, var9);
               var24 = lerp(var28, var29, var9);
               var25 = lerp(var6.renderScale.value(), var20, var9);
               var26 = lerp(1.0F, var2.shown * (0.4F + 0.6F * var8), var9) * (1.0F - var4);
            } else {
               var23 = var14;
               var24 = var15;
               var25 = var20 * (0.9F + 0.1F * var2.fade);
               var26 = var16 * (0.4F + 0.6F * var8);
            }

            if (!(var26 <= 0.01F)) {
               if (!var5) {
                  var1.pushClip(var7[0] + 4.0F + (1.0F - var10) * var11 / 2.0F, var15 - var12 * var10 / 2.0F, var11 * var10, var12 * var10, 7.0F);
               }

               var1.push();
               var1.translate(var23 - var18 * var25 / 2.0F, var24 - var19 * var25 / 2.0F);
               var1.scale(var25);
               var1.pushAlpha(var26);
               if (var17) {
                  var6.renderMiniature(var1, this.sample);
               } else {
                  HudGroupModule var30 = var6.group;
                  var6.group = null;
                  var6.renderMiniature(var1, this.mini);
                  var6.group = var30;
               }

               var1.popAlpha();
               var1.pop();
               if (!var5) {
                  var1.popClip();
               }
            }
         }
      } else if (!var5 && !(var16 <= 0.01F)) {
         if (var6 == null) {
            var1.pushAlpha(var16 * (0.45F + 0.55F * var8));
            var1.icon(var2.module.icon(), var14, var15, 17.0F * var10, BlendMode.lerp(Icons.TEXT_2, Icons.PURPLE, var8));
         } else {
            var1.pushAlpha(var16 * (0.4F + 0.6F * var8));
            var1.push();
            var1.scaleAround(var10 * 0.9F, var14, var15);
            music(var1, var14, var15);
            var1.pop();
         }

         var1.popAlpha();
      }
   }

   private static void music(Canvas var0, float var1, float var2) {
      float var3 = 76.0F;
      float var4 = 30.0F;
      float var5 = var1 - var3 / 2.0F;
      float var6 = var2 - var4 / 2.0F;
      var0.roundRect(var5, var6, var3, var4, 7.0F, Theme.tint(-938800875));
      var0.roundRectOutline(var5, var6, var3, var4, 7.0F, 1.0F, 452984831);
      var0.roundRectGradient(var5 + 5.0F, var6 + 5.0F, 20.0F, 20.0F, 5.0F, 135.0F, Icons.INDIGO, Icons.MAGENTA);
      var0.icon("music", var5 + 15.0F, var6 + 15.0F, 10.0F, -419430401);
      var0.roundRect(var5 + 30.0F, var6 + 8.0F, 30.0F, 3.5F, 1.75F, 1728053247);
      var0.roundRect(var5 + 30.0F, var6 + 14.0F, 20.0F, 3.0F, 1.5F, 872415231);
      var0.roundRect(var5 + 30.0F, var6 + 21.0F, 40.0F, 1.5F, 0.75F, 654311423);
      var0.roundRectGradient(var5 + 30.0F, var6 + 21.0F, 16.0F, 1.5F, 0.75F, 0.0F, Icons.INDIGO, Icons.MAGENTA);
   }

   private void caption(Canvas var1, HudLayoutGrid.TypeI var2, float var3, float var4, boolean var5) {
      float[] var6 = var2.rect;
      var2.gear[2] = 0.0F;
      float var7 = var2.in * var3 * var2.shown;
      if (!(var7 <= 0.01F)) {
         float var8 = var2.hover.value();
         float var9 = this.lift(var2);
         boolean var10 = var2.module.enabled();
         var1.pushAlpha(var7);
         this.enter(var1, var2);
         float var11 = var6[1] + 4.0F + 41.0F + 12.5F;
         float var12 = 16.0F;
         float var13 = 9.0F;
         float var14 = var6[0] + 7.0F;
         var1.text(
            Fonts.MEDIUM,
            FontMetrics.ellipsize(Fonts.MEDIUM, var2.module.name(), 7.5F, var6[0] + var6[2] - 7.0F - var12 - 5.0F - var14, 0.0F),
            var14,
            var11,
            7.5F,
            var10 ? Icons.TEXT : Icons.TEXT_2,
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
         var2.toggle.render(this.frame, var6[0] + var6[2] - 7.0F - var12, var11 - var13 / 2.0F, var12, var13, var10, var8 > 0.5F, false);
         if (var2.element != null && var2.element.group != null) {
            float var15 = var6[0] + 4.0F + 8.0F;
            float var16 = var6[1] + 4.0F + 8.0F;
            var1.circle(var15, var16, 5.5F, Theme.tint(-1291122411));
            var1.icon("link", var15, var16, 6.5F, Icons.PURPLE);
         }

         float var21 = 6.5F;
         float var22 = var6[0] + var6[2] - 4.0F - 3.5F - var21;
         float var17 = var6[1] + 4.0F + 3.5F + var21;
         boolean var18 = var5 && var2.shown > 0.5F && var2.in > 0.6F;
         if (var18) {
            set(var2.gear, var22 - var21, var17 - var21 + var9, var21 * 2.0F, var21 * 2.0F);
         }

         boolean var19 = var18 && this.frame.inside(var22 - var21, var17 - var21 + var9, var21 * 2.0F, var21 * 2.0F);
         float var20 = var2.gearHover.target(var19 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var4));
         if (var8 > 0.01F) {
            var1.pushAlpha(var8);
            var1.shadow(var22 - var21, var17 - var21, var21 * 2.0F, var21 * 2.0F, var21, 6.0F, 0.0F, 0.0F, 1.0F, 1426063360);
            var1.circle(var22, var17, var21, BlendMode.lerp(Theme.tint(-535424986), Theme.tint(-265806780), var20));
            var1.ring(var22, var17, var21 - 0.5F, 1.0F, BlendMode.lerp(654311423, BlendMode.withAlpha(Icons.PURPLE, 0.9F), var20));
            var1.icon("settings-2", var22, var17, 7.5F, BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var20));
            var1.popAlpha();
         }

         if (var18 && this.frame.inside(var6[0], var6[1], var6[2], var6[3])) {
            this.frame.claim(var2, DrawContext.TypeE.HAND);
         }

         var1.pop();
         var1.popAlpha();
      }
   }

   private void prepareContexts(HudBounds var1) {
      HudBounds var2 = this.mini;
      var2.mc = var1.mc;
      var2.player = var1.player;
      var2.level = var1.level;
      var2.time = var1.time;
      var2.dt = var1.dt;
      var2.partialTick = var1.partialTick;
      var2.editing = true;
      var2.width = var1.width;
      var2.height = var1.height;
      HudBounds var3 = this.sample;
      var3.mc = var1.mc;
      var3.player = null;
      var3.level = null;
      var3.time = var1.time;
      var3.dt = var1.dt;
      var3.partialTick = 1.0F;
      var3.editing = true;
      var3.width = var1.width;
      var3.height = var1.height;
   }

   boolean mouseDown(float var1, float var2, int var3) {
      if (!this.active()) {
         return false;
      } else if (!this.contains(var1, var2)) {
         return false;
      } else {
         this.pointerDown = var3 == 0;
         if (inside(this.closeButton, var1, var2)) {
            SoundRequest.play(SoundEvent.CLICK);
            this.close();
            return true;
         } else if (var3 == 0 && inside(this.glassToggle, var1, var2)) {
            SettingSection.HUD.glass.toggle();
            SoundRequest.play(SettingSection.HUD.glass.enabled() ? SoundEvent.TOGGLE_ON : SoundEvent.TOGGLE_OFF);
            return true;
         } else {
            HudLayoutGrid.TypeI var4 = this.tileAt(var1, var2);
            if (var4 == null) {
               return true;
            } else {
               if (var3 != 1 && (var3 != 0 || !(var4.hover.value() > 0.3F) || !inside(var4.gear, var1, var2))) {
                  if (var3 == 0) {
                     var4.module.toggle();
                     SoundRequest.play(var4.module.enabled() ? SoundEvent.TOGGLE_ON : SoundEvent.TOGGLE_OFF);
                  }
               } else {
                  HudLayoutEngine.openSettings(var4.module, var4.element, new float[]{this.sheet[0], var4.rect[1], this.sheet[2], var4.rect[3]});
               }

               return true;
            }
         }
      }
   }

   void mouseUp() {
      this.pointerDown = false;
   }

   boolean scroll(float var1, float var2, double var3) {
      if (!this.contains(var1, var2)) {
         return false;
      } else {
         this.scrollBy(var3);
         return true;
      }
   }

   void scrollBy(double var1) {
      this.scrollTarget = clamp(this.scrollTarget - (float)var1 * 76.0F * 0.6F, 0.0F, this.scrollLimit);
   }

   
   private HudLayoutGrid.TypeI tileAt(float var1, float var2) {
      if (!inside(this.view, var1, var2)) {
         return null;
      } else {
         for (HudLayoutGrid.TypeI var4 : this.tiles) {
            if (var4.shown > 0.5F && inside(var4.rect, var1, var2)) {
               return var4;
            }
         }

         return null;
      }
   }

   
   String hint(float var1, float var2) {
      HudLayoutGrid.TypeI var3 = this.tileAt(var1, var2);
      if (var3 != null) {
         return var3.module.enabled() ? "Click to switch it off  ·  Right-click for its settings" : "Click to switch it on  ·  Right-click for its settings";
      } else {
         return "Every HUD element  ·  Esc or a click outside closes this";
      }
   }

   private static void set(float[] var0, float var1, float var2, float var3, float var4) {
      var0[0] = var1;
      var0[1] = var2;
      var0[2] = var3;
      var0[3] = var4;
   }

   private static boolean inside(float[] var0, float var1, float var2) {
      return var0[2] > 0.0F && var1 >= var0[0] && var1 <= var0[0] + var0[2] && var2 >= var0[1] && var2 <= var0[1] + var0[3];
   }

   private static float lerp(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   private static float clamp01(float var0) {
      return Math.max(0.0F, Math.min(1.0F, var0));
   }

   private static float clamp(float var0, float var1, float var2) {
      return var2 < var1 ? (var1 + var2) / 2.0F : Math.max(var1, Math.min(var2, var0));
   }

   private static final class TypeI {
      final Module module;
      
      final HudModule element;
      final Spacer toggle;
      final Animator hover = Animator.smooth(0.0F);
      final Animator gearHover = Animator.smooth(0.0F);
      final Animator on;
      final float[] rect = new float[4];
      final float[] gear = new float[4];
      float in;
      float fade;
      float flight;
      float shown = 1.0F;
      float rest = 1.0F;

      TypeI(Module var1,  HudModule var2) {
         this.module = var1;
         this.element = var2;
         this.toggle = new Spacer(var1.enabled());
         this.on = Animator.smooth(var1.enabled() ? 1.0F : 0.0F);
      }
   }
}
