package dev.purxiy.ui.config;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.widget.Slider;

final class ConfigLibraryCover {
   static final float COVER = 128.0F;
   static final float BODY = 66.0F;
   static final float HEIGHT = 194.0F;
   static final float RADIUS = 18.0F;
   private static final float PAD = 16.0F;
   private static final float ROUND = 28.0F;
   final ConfigLibraryStats entry;
   final Animator lx = Animator.smooth(0.0F);
   final Animator ly = Animator.smooth(0.0F);
   boolean placed;
   private final Object key = new Object();
   private final Animator hover = Animator.smooth(0.0F);
   private final Animator press = Animator.snappy(0.0F);
   private final Animator actions = Animator.smooth(0.0F);
   private final Animator use;
   private final Animator renameShown = Animator.smooth(0.0F);
   private final ConfigLibraryNebula.NebulaCloud look = new ConfigLibraryNebula.NebulaCloud();
   private final ConfigLibraryDash.DashBar main = new ConfigLibraryDash.DashBar();
   private final Animator loadShown = Animator.smooth(0.0F);
   final ConfigLibraryDash.DashState export = new ConfigLibraryDash.DashState();
   final ConfigLibraryDash.DashState like = new ConfigLibraryDash.DashState();
   final ConfigLibraryDash.DashState more = new ConfigLibraryDash.DashState();
   float bornAt = -100.0F;
   float entered = 1.0F;
   float loadedAt = -100.0F;
   float x;
   float y;
   float w;
   boolean shown;
   boolean pressed;

   private Slider field;
   private String draft = "";

   ConfigLibraryCover(ConfigLibraryStats var1) {
      this.entry = var1;
      this.use = Animator.snappy(ConfigLibraryTransform.isActive(var1) ? 1.0F : 0.0F);
   }

   float[] cover() {
      return new float[]{this.x, this.y, this.w, 128.0F};
   }

   boolean renaming() {
      return this.field != null;
   }

   void startRename() {
      this.draft = this.entry.name;
      this.field = new Slider(new Slider.Listener() {
         @Override
         public String get() {
            return ConfigLibraryCover.this.draft;
         }

         @Override
         public void set(String var1) {
            ConfigLibraryCover.this.draft = var1.replace('\n', ' ');
         }

         @Override
         public int maxLength() {
            return 32;
         }

         @Override
         public String placeholder() {
            return "Config name";
         }
      });
      this.field.focus();
      this.field.selectAll();
   }

   void endRename(boolean var1) {
      if (this.field != null) {
         if (!var1) {
            ConfigLibraryTransform.rename(this.entry, this.draft);
         }

         this.field = null;
      }
   }


   Slider field() {
      return this.field;
   }

   void render(DrawContext var1, float var2, float var3, float var4, float var5, boolean var6) {
      this.x = var2;
      this.y = var3;
      this.w = var4;
      this.shown = var5 > 0.5F;
      Canvas var7 = var1.canvas;
      float var8 = var1.time;
      ConfigLibraryStats var9 = this.entry;
      boolean var10 = !var9.mine();
      boolean var11 = var6 && var1.hover(this.key, var2, var3, var4, 194.0F, DrawContext.Cursor.HAND);
      float var12 = this.hover.target(var11 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var13 = this.press.target(this.pressed && var11 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var14 = this.actions.target(var11 && !this.renaming() ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var15 = this.use.target(ConfigLibraryTransform.isActive(var9) ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var16 = this.renameShown.target(this.renaming() ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var17 = Math.max(0.0F, (var8 - this.bornAt) * MotionStyle.CASCADE.speed());
      float var18 = var17 < 1.6F ? Easing.EMPHASIZED.apply(Math.min(1.0F, var17 / 0.55F)) : 1.0F;
      float var19 = var5 * var18;
      if (!(var19 <= 0.005F)) {
         var7.push();
         var7.translate(0.0F, (1.0F - var19) * 18.0F - var12 * 3.0F);
         var7.scaleAround((0.96F + 0.04F * var19) * (1.0F - var13 * 0.016F), var2 + var4 / 2.0F, var3 + 97.0F);
         var7.pushAlpha(var19);
         float var20 = 0.25F + var12 * 0.6F;
         float var21 = Math.max(var15 * 0.85F, var12 * 0.3F);
         var7.shadow(
            var2, var3, var4, 194.0F, 18.0F, 22.0F + var20 * 22.0F, 0.0F, 0.0F, 6.0F + var20 * 10.0F, BlendMode.withAlpha(-16777216, 0.3F + var20 * 0.14F)
         );
         if (var21 > 0.005F) {
            var7.glow(var2, var3, var4, 194.0F, 18.0F, 28.0F, BlendMode.withAlpha(BlendMode.lerp(Icons.VIOLET, var9.accent(), 0.5F), 0.2F * var21));
         }

         var7.glass(var2, var3, var4, 194.0F, 18.0F, Icons.GLASS_TINT);
         if (var17 < 1.6F) {
            float var22 = var17 / 1.6F;
            var7.glow(var2, var3, var4, 194.0F, 18.0F, 22.0F + 30.0F * var22, BlendMode.withAlpha(var9.accent(), 0.55F * (1.0F - var22) * (1.0F - var22)));
         }

         this.cover(var1, var7, var2, var3, var4, var12, var8, var17);
         var7.roundRectOutlineGradient(
            var2,
            var3,
            var4,
            194.0F,
            18.0F,
            1.0F,
            90.0F,
            BlendMode.lerp(721420287, BlendMode.withAlpha(BlendMode.lerp(Icons.PURPLE, var9.accent(), 0.4F), 0.9F), var21),
            BlendMode.lerp(150994943, BlendMode.withAlpha(Icons.INDIGO, 0.4F), var21)
         );
         boolean var35 = var6 && var14 > 0.5F;
         float var23 = var2 + var4 - 12.0F - 14.0F;
         float var24 = var3 + 12.0F + 14.0F;
         this.more.render(var1, var23, var24 - (1.0F - stagger(var14, 0)) * 4.0F, 28.0F, "ellipsis", false, 0, stagger(var14, 0), var35);
         var23 -= 34.0F;
         if (var10) {
            this.export.shown = false;
            float var37 = var9.own ? 0.0F : Math.max(stagger(var14, 1), var9.liked ? 1.0F : 0.0F);
            this.like.render(var1, var23, var24 - (1.0F - var37) * 4.0F, 28.0F, "heart", var9.liked, Icons.PINK, var37, var6 && var37 > 0.5F);
         } else {
            this.like.shown = false;
            boolean var25 = ConfigLibraryTransform.isShared(var9);
            this.export.render(var1, var23, var24 - (1.0F - stagger(var14, 1)) * 4.0F, 28.0F, "share", false, 0, stagger(var14, 1), var35);
            if (var25) {
               float var26 = var2 + 12.0F + 11.0F;
               float var27 = var3 + 12.0F + 11.0F;
               ConfigLibraryUpdater.SyncState var28 = ConfigLibraryUpdater.status(var9);
               var7.circle(var26, var27, 11.0F, Theme.tint(-1340994014));
               var7.roundRectOutline(var26 - 11.0F, var27 - 11.0F, 22.0F, 22.0F, 11.0F, 1.0F, 654311423);
               if (var28 == ConfigLibraryUpdater.SyncState.SENDING) {
                  float var29 = MotionStyle.reduced() ? 0.0F : var8 * 300.0F;
                  var7.arc(
                     var26,
                     var27,
                     9.5F,
                     1.4F,
                     var29,
                     110.0F,
                     BlendMode.withAlpha(Theme.tint(-1783297), 0.0F),
                     BlendMode.withAlpha(Theme.tint(-1783297), 0.9F),
                     true
                  );
               }

               boolean var45 = var28 == ConfigLibraryUpdater.SyncState.WAITING || var28 == ConfigLibraryUpdater.SyncState.REFUSED;
               var7.icon(var45 ? "cloud-off" : "globe", var26, var27, 11.5F, var45 ? Icons.TEXT_3 : BlendMode.lerp(Icons.TEXT_2, Theme.tint(-1783297), 0.4F));
            }
         }

         if (var10) {
            float var38 = var3 + 128.0F - 15.0F;
            float var40 = var2 + 16.0F;
            var7.icon("heart", var40 + 5.0F, var38, 11.0F, var9.liked ? Icons.PINK : Icons.TEXT_2);
            var40 += 13.0F
               + var7.text(Fonts.MEDIUM, ConfigLibraryDash.count(var9.likes), var40 + 13.0F, var38, 10.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
            var7.icon("download", var40 + 14.0F, var38, 11.0F, Icons.TEXT_2);
            var7.text(Fonts.MEDIUM, ConfigLibraryDash.count(var9.downloads), var40 + 22.0F, var38, 10.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         }

         if (var35) {
            if (var10) {
               this.tip(var7, this.like, var9.liked ? "Unlike" : "Like");
            } else {
               this.tip(var7, this.export, "Export");
            }

            this.tip(var7, this.more, "More");
         }

         float var39 = var3 + 128.0F + 33.0F;
         float var42 = 1.0F;
         boolean var43 = !var10 && ConfigLibraryTransform.isActive(var9);
         ConfigLibraryDash.DashTone var30;
         String var44;
         String var46;
         if (var10 && var9.own) {
            var44 = "Yours";
            var46 = "globe";
            var30 = ConfigLibraryDash.DashTone.DONE;
         } else if (var10) {
            boolean var31 = var9.added;
            var44 = var31 ? "Added" : "Get";
            var46 = var31 ? "check" : "download";
            var30 = var31 ? ConfigLibraryDash.DashTone.DONE : ConfigLibraryDash.DashTone.QUIET;
         } else if (var43) {
            var44 = "In use";
            var46 = "check";
            var30 = ConfigLibraryDash.DashTone.DONE;
         } else {
            var44 = "Load";
            var46 = null;
            var30 = ConfigLibraryDash.DashTone.PRIMARY;
            var42 = this.loadShown.target(var11 && !this.renaming() ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         }

         float var47 = ConfigLibraryDash.DashBar.width(var7, var46, var44, 28.0F);
         float var32 = var4 - 32.0F - var47 - 10.0F;
         if (var16 < 0.99F) {
            var7.pushAlpha(1.0F - var16);
            if (!(var42 > 0.01F)) {
               this.main.shown = false;
            } else {
               var7.pushAlpha(var42);
               var7.push();
               var7.translate((1.0F - var42) * 6.0F, 0.0F);
               this.main.render(var1, var2 + var4 - 16.0F - var47, var39, 28.0F, var46, var44, var30, var6 && !this.renaming() && var42 > 0.5F);
               var7.pop();
               var7.popAlpha();
            }

            Translations.pause();
            var7.text(
               Fonts.SEMIBOLD,
               FontMetrics.ellipsize(Fonts.SEMIBOLD, var9.name, 14.5F, var32, 0.0F),
               var2 + 16.0F,
               var39 - 10.0F,
               14.5F,
               Icons.TEXT,
               FontStyle.LEFT,
               FontWeight.MIDDLE
            );
            Translations.resume();
            if (var10) {
               ConfigLibraryDash.face(var7, var9.author, var2 + 16.0F, var39 + 4.0F, 15.0F);
               Translations.pause();
               var7.text(
                  Fonts.MEDIUM,
                  FontMetrics.ellipsize(Fonts.MEDIUM, var9.author, 11.5F, var32 - 21.0F, 0.0F),
                  var2 + 16.0F + 21.0F,
                  var39 + 11.5F,
                  11.5F,
                  Icons.TEXT_2,
                  FontStyle.LEFT,
                  FontWeight.MIDDLE
               );
               Translations.resume();
            } else {
               String var33 = ConfigLibraryDash.modules(var9.moduleCount());
               String var34 = var33 + "  ·  saved " + ConfigLibraryDash.ago(var9.edited);
               if (var7.textWidth(Fonts.REGULAR, var34, 11.5F) > var32) {
                  var34 = ConfigLibraryDash.modules(var9.moduleCount());
               }

               var7.text(
                  Fonts.REGULAR,
                  FontMetrics.ellipsize(Fonts.REGULAR, var34, 11.5F, var32, 0.0F),
                  var2 + 16.0F,
                  var39 + 11.0F,
                  11.5F,
                  BlendMode.lerp(Icons.TEXT_2, Theme.tint(-3159830), var12 * 0.5F),
                  FontStyle.LEFT,
                  FontWeight.MIDDLE
               );
            }

            var7.popAlpha();
         }

         if (this.field != null) {
            var7.pushAlpha(var16);
            this.field.render(var1, var2 + 16.0F - 6.0F, var39, var4 - 32.0F + 12.0F, var6);
            var7.popAlpha();
         }

         var7.popAlpha();
         var7.pop();
      }
   }

   private void cover(DrawContext var1, Canvas var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      ConfigLibraryNebula.NebulaCloud var9 = this.look;
      var9.time = var7;
      var9.hover = var6;
      var9.fadeBottom = 0.3F;
      var9.zoom = 1.16F;
      var9.drawn = Math.min(this.entered, var8 < 3.0F ? Math.min(1.0F, var8 / 1.25F) : 1.0F);
      float var10 = (var7 - this.loadedAt) * MotionStyle.TOGGLE.speed();
      if (var10 >= 0.0F && var10 < 1.0F) {
         var9.hover = Math.max(var6, 1.0F - var10);
      }

      var9.pointerX = var6 > 0.01F ? Math.max(-1.0F, Math.min(1.0F, (var1.mouseX - (var3 + var5 / 2.0F)) / (var5 / 2.0F))) : 0.0F;
      var9.pointerY = var6 > 0.01F ? Math.max(-1.0F, Math.min(1.0F, (var1.mouseY - (var4 + 64.0F)) / 64.0F)) : 0.0F;
      var2.pushClip(var3, var4, var5, 128.0F, 18.0F);
      ConfigLibraryStats var11 = this.entry;
      float var12 = ConfigLibraryFile.appear(var11, false);
      if (var12 < 1.0F) {
         ConfigLibraryNebula.draw(var2, var11, var3, var4, var5, 128.0F, var9);
      }

      if (var12 > 0.0F) {
         float var13 = 1.03F + 0.04F * var6;
         ConfigLibraryFile.draw(
            var2, var11, var3, var4, var5, 128.0F, 18.0F, var13, var9.pointerX * 0.6F, var9.pointerY * 0.6F, false, ConfigLibraryFile.Mode.BOTTOM, 0.4F
         );
      }

      if (ConfigLibraryFile.importing(var11)) {
         ConfigLibraryDash.reading(var2, var3, var4, var5, 128.0F, var7);
      }

      if (var10 >= 0.0F && var10 < 1.0F) {
         float var17 = Easing.CUBIC_IN_OUT.apply(var10);
         float var14 = 70.0F;
         float var15 = var3 - var14 * 2.0F + (var5 + var14 * 3.0F) * var17;
         var2.push();
         var2.rotateAround(0.32F, var15 + var14 / 2.0F, var4 + 64.0F);
         int var16 = BlendMode.withAlpha(-1, 0.16F * (1.0F - var10));
         var2.roundRectGradient(var15 - var14 / 2.0F, var4 - 60.0F, var14 / 2.0F, 248.0F, 0.0F, 0.0F, BlendMode.withAlpha(var16, 0.0F), var16);
         var2.roundRectGradient(var15, var4 - 60.0F, var14 / 2.0F, 248.0F, 0.0F, 0.0F, var16, BlendMode.withAlpha(var16, 0.0F));
         var2.pop();
      }

      var2.popClip();
   }

   private void tip(Canvas var1, ConfigLibraryDash.DashState var2, String var3) {
      float var4 = (var2.hover.value() - 0.55F) / 0.45F;
      if (var4 > 0.01F) {
         ConfigLibraryDash.tooltip(var1, var2.x + var2.d / 2.0F, var2.y + var2.d, Translations.shown(var3), (String)null, Math.min(1.0F, var4), true);
      }
   }

   private static float stagger(float var0, int var1) {
      return Math.max(0.0F, Math.min(1.0F, var0 * 1.3F - var1 * 0.15F));
   }

   ConfigLibraryCover.Action hit(float var1, float var2) {
      if (!this.shown) {
         return ConfigLibraryCover.Action.NONE;
      } else if (this.field != null && this.field.contains(var1, var2)) {
         return ConfigLibraryCover.Action.FIELD;
      } else if (this.more.hit(var1, var2)) {
         return ConfigLibraryCover.Action.MORE;
      } else {
         if (this.entry.mine()) {
            if (this.export.hit(var1, var2)) {
               return this.entry.mine() ? ConfigLibraryCover.Action.EXPORT : ConfigLibraryCover.Action.LIKE;
            }
         } else if (this.like.hit(var1, var2)) {
            return this.entry.mine() ? ConfigLibraryCover.Action.EXPORT : ConfigLibraryCover.Action.LIKE;
         }

         if (this.main.hit(var1, var2)) {
            return this.entry.mine()
               ? ConfigLibraryCover.Action.LOAD
               : (this.entry.own ? ConfigLibraryCover.Action.OPEN : ConfigLibraryCover.Action.GET);
         } else {
            return var1 >= this.x && var1 <= this.x + this.w && var2 >= this.y && var2 <= this.y + 194.0F
               ? ConfigLibraryCover.Action.OPEN
               : ConfigLibraryCover.Action.NONE;
         }
      }
   }

   boolean contains(float var1, float var2) {
      return this.shown && var1 >= this.x && var1 <= this.x + this.w && var2 >= this.y && var2 <= this.y + 194.0F;
   }

   void pressMain() {
      this.main.press();
   }

   static enum Action {
      NONE,
      OPEN,
      LOAD,
      GET,
      EXPORT,
      LIKE,
      MORE,
      FIELD;

      private static ConfigLibraryCover.Action[] $values() {
         return new ConfigLibraryCover.Action[]{NONE, OPEN, LOAD, GET, EXPORT, LIKE, MORE, FIELD};
      }
   }
}
