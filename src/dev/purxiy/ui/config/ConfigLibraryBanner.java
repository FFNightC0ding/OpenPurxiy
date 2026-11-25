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
import dev.purxiy.ui.KeyLabel;
import dev.purxiy.ui.Theme;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

final class ConfigLibraryBanner {
   static final String[] TAGS = new String[]{"All", "PvP", "Visuals", "FPS", "SMP", "Creator"};
   private static final float BANNER = 196.0F;
   private static final float AUTO = 7.5F;
   final ConfigLibraryFade grid = new ConfigLibraryFade();
   private final ConfigLibraryMetrics tags = new ConfigLibraryMetrics(TAGS, (String[])null, false);
   private final ConfigLibraryMetrics sort = new ConfigLibraryMetrics(new String[]{"Popular", "New"}, new String[]{"flame", "clock"}, true);
   private final ConfigLibraryNebula.NebulaCloud look = new ConfigLibraryNebula.NebulaCloud();
   private final Animator banner = Animator.smooth(1.0F);
   private final Animator bannerHover = Animator.smooth(0.0F);
   private final Object bannerKey = new Object();
   private final ConfigLibraryDash.DashBar get = new ConfigLibraryDash.DashBar();
   private final ConfigLibraryDash.DashBar preview = new ConfigLibraryDash.DashBar();
   private final ConfigLibraryDash.DashBar retry = new ConfigLibraryDash.DashBar();
   private final ConfigLibraryDash.DashBar library = new ConfigLibraryDash.DashBar();
   final ConfigLibraryDash.DashState like = new ConfigLibraryDash.DashState();
   private final Object[] dotKeys = new Object[]{new Object(), new Object(), new Object(), new Object(), new Object()};
   private final float[] dotX = new float[5];
   private final List<ConfigLibraryStats> entries = new ArrayList<>();
   private final Animator feature = Animator.smooth(1.0F);
   private int featured;
   private int previous;
   private float since;
   private float dotsY;
   private float bx;
   private float by;
   private float bw;
   private float bh;
   private boolean bannerLive;

   List<ConfigLibraryStats> entries() {
      return this.entries;
   }

   private static List<ConfigLibraryStats> featuredList() {
      ArrayList var0 = new ArrayList();

      for (ConfigLibraryStats var2 : ConfigLibraryTransform.community()) {
         if (var2.featured) {
            var0.add(var2);
         }
      }

      return var0;
   }


   ConfigLibraryStats featured() {
      List var1 = featuredList();
      return var1.isEmpty() ? null : (ConfigLibraryStats)var1.get(Math.floorMod(this.featured, var1.size()));
   }

   void feature(int var1, float var2) {
      List var3 = featuredList();
      if (!var3.isEmpty()) {
         var1 = Math.floorMod(var1, var3.size());
         if (var1 != this.featured) {
            this.previous = this.featured;
            this.featured = var1;
            this.feature.snap(0.0F).target(1.0F);
         }

         this.since = var2;
      }
   }

   void enter(float var1) {
      this.grid.enter(var1);
      this.since = var1;
      ConfigLibraryUpdater.refresh(true);
   }

   void tag(String var1) {
      for (int var2 = 0; var2 < TAGS.length; var2++) {
         if (TAGS[var2].equalsIgnoreCase(var1)) {
            this.tags.select(var2);
         }
      }
   }

   void sort(int var1) {
      this.sort.select(var1);
   }

   float[] getButton() {
      return new float[]{this.get.x + this.get.w / 2.0F, this.get.y + this.get.h / 2.0F};
   }

   float[] bannerSky() {
      return new float[]{this.bx + this.bw * 0.34F, this.by, this.bw * 0.66F, this.bh};
   }

   void render(DrawContext var1, float var2, float var3, float var4, float var5, String var6, float var7, float var8, boolean var9) {
      Canvas var10 = var1.canvas;
      float var11 = var1.time;
      ConfigLibraryUpdater.refresh(false);
      boolean var12 = !var6.isBlank();
      float var13 = this.banner.target(!var12 && !featuredList().isEmpty() ? 1.0F : 0.0F).update(MotionStyle.MENU.dt(var1.dt));
      this.entries.clear();
      String var14 = this.tags.label();

      for (ConfigLibraryStats var16 : ConfigLibraryTransform.community()) {
         if ((var14.equals("All") || List.of(var16.tags).contains(var14)) && ConfigLibraryTransform.matches(var16, var6)) {
            this.entries.add(var16);
         }
      }

      if (this.sort.selected() == 0) {
         this.entries.sort(Comparator.comparingInt(var0 -> -(var0.likes * 4 + var0.downloads)));
      } else {
         this.entries.sort(Comparator.comparingLong(var0 -> -var0.created));
      }

      float var24 = 196.0F * var13;
      float var25 = (var24 + 18.0F) * var13;
      float var17 = var25 + 30.0F + 18.0F;
      float var18 = this.grid.offset();
      this.grid.render(var1, this.entries, var2, var3, var4, var5, var17, var7, var9);
      var10.pushClip(var2 - 30.0F, var3 - 14.0F, var4 + 60.0F, var5 + 28.0F);
      this.bannerLive = var13 > 0.5F;
      if (var13 > 0.01F && var3 - var18 + var24 > var3 - 14.0F) {
         var10.pushAlpha(var13 * var7);
         this.banner(var1, var10, var2, var3 - var18, var4, var24, var11, var8, var9 && var13 > 0.9F);
         var10.popAlpha();
      }

      float var19 = var3 + var25 - var18 + 15.0F;
      if (var19 > var3 - 20.0F) {
         float var20 = smooth((var19 - var3 + 20.0F) / 30.0F);
         var10.pushAlpha(var7 * var20);
         this.tags.render(var1, var2, var19, var9 && var20 > 0.9F);
         float var21 = this.sort.width(var10);
         this.sort.render(var1, var2 + var4 - var21, var19, var9 && var20 > 0.9F);
         int var22 = this.entries.size();
         String var23 = var22 + (this.entries.size() == 1 ? " config" : " configs");
         var10.text(Fonts.MEDIUM, var23, var2 + var4 - var21 - 14.0F, var19, 11.5F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.MIDDLE);
         var10.popAlpha();
      }

      var10.popClip();
      this.retry.shown = false;
      this.library.shown = false;
      if (this.entries.isEmpty()) {
         float var26 = var3 + var17 + 70.0F - var18;
         var10.pushAlpha(var7);
         ConfigLibraryUpdater.LoadState var27 = ConfigLibraryUpdater.state();
         if (var27 == ConfigLibraryUpdater.LoadState.LOADING || var27 == ConfigLibraryUpdater.LoadState.IDLE) {
            for (int var30 = 0; var30 < 3; var30++) {
               float var31 = MotionStyle.reduced() ? 0.5F : (float)(0.5 + 0.5 * Math.sin(var11 * 4.2F - var30 * 0.9F));
               var10.circle(var2 + var4 / 2.0F + (var30 - 1) * 16, var26, 3.6F + 1.2F * var31, BlendMode.withAlpha(Theme.tint(-1783297), 0.25F + 0.6F * var31));
            }

            var10.text(Fonts.MEDIUM, "Looking for shared configs", var2 + var4 / 2.0F, var26 + 30.0F, 12.5F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
         } else if (var27 == ConfigLibraryUpdater.LoadState.OFFLINE && ConfigLibraryTransform.community().isEmpty()) {
            var10.icon("cloud-off", var2 + var4 / 2.0F, var26, 26.0F, Icons.TEXT_3);
            var10.text(
               Fonts.SEMIBOLD, "Can't reach the SourceByDevFFnightmare DeObfDeCompByGuideX server", var2 + var4 / 2.0F, var26 + 32.0F, 14.0F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE
            );
            var10.text(
               Fonts.REGULAR,
               "Discover comes back by itself once it can be reached",
               var2 + var4 / 2.0F,
               var26 + 52.0F,
               11.5F,
               Icons.TEXT_3,
               FontStyle.CENTER,
               FontWeight.MIDDLE
            );
            float var29 = ConfigLibraryDash.DashBar.width(var10, "refresh-cw", "Try again", 30.0F);
            this.retry
               .render(var1, var2 + var4 / 2.0F - var29 / 2.0F, var26 + 88.0F, 30.0F, "refresh-cw", "Try again", ConfigLibraryDash.DashTone.QUIET, var9);
         } else if (ConfigLibraryTransform.community().isEmpty()) {
            var10.icon("telescope", var2 + var4 / 2.0F, var26, 26.0F, Icons.TEXT_3);
            var10.text(Fonts.SEMIBOLD, "Nothing shared yet", var2 + var4 / 2.0F, var26 + 32.0F, 14.0F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
            var10.text(
               Fonts.REGULAR,
               "Publish one of yours with Export: it shows up here for everyone",
               var2 + var4 / 2.0F,
               var26 + 52.0F,
               11.5F,
               Icons.TEXT_3,
               FontStyle.CENTER,
               FontWeight.MIDDLE
            );
            float var28 = ConfigLibraryDash.DashBar.width(var10, "library", "Go to your library", 30.0F);
            this.library
               .render(var1, var2 + var4 / 2.0F - var28 / 2.0F, var26 + 88.0F, 30.0F, "library", "Go to your library", ConfigLibraryDash.DashTone.QUIET, var9);
         } else {
            var10.icon("telescope", var2 + var4 / 2.0F, var26, 26.0F, Icons.TEXT_3);
            var10.text(Fonts.SEMIBOLD, "Nothing here yet", var2 + var4 / 2.0F, var26 + 32.0F, 14.0F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
            var10.text(
               Fonts.REGULAR, "Try another kind, or fewer words", var2 + var4 / 2.0F, var26 + 52.0F, 11.5F, Icons.TEXT_3, FontStyle.CENTER, FontWeight.MIDDLE
            );
         }

         var10.popAlpha();
      }
   }

   private void banner(DrawContext var1, Canvas var2, float var3, float var4, float var5, float var6, float var7, float var8, boolean var9) {
      List var10 = featuredList();
      if (!var10.isEmpty()) {
         this.bx = var3;
         this.by = var4;
         this.bw = var5;
         this.bh = var6;
         boolean var11 = var9 && var1.hover(this.bannerKey, var3, var4, var5, var6, DrawContext.Cursor.ARROW);
         float var12 = this.bannerHover.target(var11 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         if (var11) {
            this.since = this.since + var1.dt;
         } else if ((var7 - this.since) * MotionStyle.MENU.speed() > 7.5F) {
            this.feature(this.featured + 1, var7);
         }

         float var13 = this.feature.update(MotionStyle.PANEL.dt(var1.dt) * 0.5F);
         ConfigLibraryStats var14 = (ConfigLibraryStats)var10.get(Math.floorMod(this.featured, var10.size()));
         ConfigLibraryStats var15 = (ConfigLibraryStats)var10.get(Math.floorMod(this.previous, var10.size()));
         float var16 = 22.0F;
         KeyLabel.surface(var2, var3, var4, var5, var6, var16, 0.35F + var12 * 0.2F, 0.12F + var12 * 0.18F);
         var2.pushClip(var3, var4, var5, var6, var16);
         this.look.time = var7;
         this.look.fill = true;
         this.look.fadeLeft = var5 * 0.2F;
         this.look.hover = Math.max(var12 * 0.7F, 4.0F * var13 * (1.0F - var13));
         float var17 = var3 + var5 * 0.34F;
         float var18 = var5 * 0.66F;
         this.look.pointerX = var12 > 0.01F ? Math.max(-1.0F, Math.min(1.0F, (var1.mouseX - (var17 + var18 / 2.0F)) / (var18 / 2.0F))) : 0.0F;
         this.look.pointerY = var12 > 0.01F ? Math.max(-1.0F, Math.min(1.0F, (var1.mouseY - (var4 + var6 / 2.0F)) / (var6 / 2.0F))) : 0.0F;
         this.look.drawn = Math.min(1.0F, Math.max(0.0F, var8 - 0.1F) / 1.1F);
         float var19 = ConfigLibraryFile.appear(var14, true);
         float var20 = var15 != var14 && var13 < 1.0F ? ConfigLibraryFile.appear(var15, true) : 0.0F;
         if (var19 * var13 + var20 * (1.0F - var13) < 0.999F) {
            ConfigLibraryNebula.draw(var2, var15, var14, var13, var17, var4, var18, var6, this.look);
         }

         float var21 = 1.02F + 0.03F * var12;
         if (var20 > 0.0F) {
            var2.pushAlpha(1.0F - var13);
            ConfigLibraryFile.draw(
               var2,
               var15,
               var17,
               var4,
               var18,
               var6,
               0.0F,
               var21,
               this.look.pointerX * 0.4F,
               this.look.pointerY * 0.4F,
               true,
               ConfigLibraryFile.Mode.LEFT,
               0.42F
            );
            var2.popAlpha();
         }

         if (var19 > 0.0F) {
            var2.pushAlpha(var15 != var14 ? var13 : 1.0F);
            ConfigLibraryFile.draw(
               var2,
               var14,
               var17,
               var4,
               var18,
               var6,
               0.0F,
               var21,
               this.look.pointerX * 0.4F,
               this.look.pointerY * 0.4F,
               true,
               ConfigLibraryFile.Mode.LEFT,
               0.42F
            );
            var2.popAlpha();
         }

         var2.popClip();
         var2.roundRectOutline(var3, var4, var5, var6, var16, 1.0F, BlendMode.withAlpha(-1, 0.1F + 0.06F * var12));
         float var22 = Easing.CUBIC_OUT.apply(Math.min(1.0F, var13 * 1.4F));
         if (var22 < 1.0F && var15 != var14) {
            var2.pushAlpha(1.0F - var22);
            var2.push();
            var2.translate(-18.0F * var22, 0.0F);
            this.words(var1, var2, var15, var3, var4, var5, var6, false);
            var2.pop();
            var2.popAlpha();
         }

         var2.pushAlpha(var22);
         var2.push();
         var2.translate(18.0F * (1.0F - var22), 0.0F);
         this.words(var1, var2, var14, var3, var4, var5, var6, var9 && var22 > 0.9F);
         var2.pop();
         var2.popAlpha();
         int var23 = Math.min(var10.size(), this.dotX.length);
         float var24 = 6.0F;
         float var25 = 22.0F;
         float var26 = 7.0F;
         float var27 = var25 + (var23 - 1) * (var24 + var26);
         float var28 = var3 + var5 - 28.0F - var27;
         this.dotsY = var4 + var6 - 26.0F;
         float var29 = Math.min(1.0F, (var7 - this.since) * MotionStyle.MENU.speed() / 7.5F);

         for (int var30 = 0; var30 < var23; var30++) {
            boolean var31 = var30 == Math.floorMod(this.featured, var10.size());
            float var32 = var31 ? var25 : var24;
            this.dotX[var30] = var28;
            boolean var33 = var9 && var1.hover(this.dotKeys[var30], var28 - 3.0F, this.dotsY - 8.0F, var32 + 6.0F, 16.0F, DrawContext.Cursor.HAND);
            var2.roundRect(var28, this.dotsY - 3.0F, var32, 6.0F, 3.0F, BlendMode.withAlpha(-1, var31 ? 0.22F : (var33 ? 0.5F : 0.28F)));
            if (var31) {
               var2.roundRect(var28, this.dotsY - 3.0F, Math.max(6.0F, var32 * var29), 6.0F, 3.0F, BlendMode.withAlpha(-1, 0.85F));
            }

            var28 += var32 + var26;
         }
      }
   }

   private void words(DrawContext var1, Canvas var2, ConfigLibraryStats var3, float var4, float var5, float var6, float var7, boolean var8) {
      float var9 = var4 + 30.0F;
      float var10 = Math.min(var6 * 0.42F, 440.0F);
      ConfigLibraryDash.label(var2, "FEATURED", var9, var5 + 32.0F, Theme.tint(-1783297), FontStyle.LEFT);
      Translations.pause();
      var2.text(
         Fonts.BOLD,
         FontMetrics.ellipsize(Fonts.BOLD, var3.name, 28.0F, var10, -0.01F),
         var9,
         var5 + 62.0F,
         28.0F,
         Icons.TEXT,
         FontStyle.LEFT,
         FontWeight.MIDDLE,
         -0.01F
      );
      Translations.resume();
      ConfigLibraryDash.face(var2, var3.author, var9, var5 + 85.0F, 18.0F);
      Translations.pause();
      float var11 = var2.text(Fonts.MEDIUM, var3.author, var9 + 26.0F, var5 + 94.0F, 12.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      var11 += var2.text(Fonts.MEDIUM, "  ·  ", var9 + 26.0F + var11, var5 + 94.0F, 12.0F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
      var2.text(Fonts.MEDIUM, ConfigLibraryDash.reach(var3), var9 + 26.0F + var11, var5 + 94.0F, 12.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      Translations.resume();
      Translations.pause();
      List var12 = FontMetrics.wrap(Fonts.REGULAR, var3.description, 12.5F, var10, 0.0F);

      for (int var13 = 0; var13 < Math.min(2, var12.size()); var13++) {
         String var14 = var13 == 1 && var12.size() > 2
            ? FontMetrics.ellipsize(Fonts.REGULAR, (String)var12.get(1) + " " + (String)var12.get(2), 12.5F, var10, 0.0F)
            : (String)var12.get(var13);
         var2.text(Fonts.REGULAR, var14, var9, var5 + 118.0F + var13 * 18, 12.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      Translations.resume();
      float var16 = var5 + var7 - 34.0F;
      float var17;
      if (var3.own) {
         var17 = var9 + this.get.render(var1, var9, var16, 34.0F, "globe", "Yours", ConfigLibraryDash.DashTone.DONE, var8) + 8.0F;
      } else if (var3.added) {
         var17 = var9 + this.get.render(var1, var9, var16, 34.0F, "check", "Added", ConfigLibraryDash.DashTone.DONE, var8) + 8.0F;
      } else {
         var17 = var9 + this.get.render(var1, var9, var16, 34.0F, "download", "Get config", ConfigLibraryDash.DashTone.PRIMARY, var8) + 8.0F;
      }

      var17 += this.preview.render(var1, var17, var16, 34.0F, "eye", "Preview", ConfigLibraryDash.DashTone.QUIET, var8) + 8.0F;
      this.like.render(var1, var17 + 17.0F, var16, 34.0F, "heart", var3.liked, Icons.PINK, 1.0F, var8);
   }

   ConfigLibraryBanner.BannerItem mouseDown(float var1, float var2, float var3) {
      if (this.retry.hit(var1, var2)) {
         this.retry.press();
         return new ConfigLibraryBanner.BannerItem(ConfigLibraryBanner.BannerKind.RETRY, (ConfigLibraryStats)null);
      } else if (this.library.hit(var1, var2)) {
         this.library.press();
         return new ConfigLibraryBanner.BannerItem(ConfigLibraryBanner.BannerKind.LIBRARY, (ConfigLibraryStats)null);
      } else {
         ConfigLibraryStats var4 = this.featured();
         if (this.bannerLive && var4 != null) {
            if (this.get.hit(var1, var2)) {
               this.get.press();
               return new ConfigLibraryBanner.BannerItem(ConfigLibraryBanner.BannerKind.GET, var4);
            }

            if (this.preview.hit(var1, var2)) {
               this.preview.press();
               return new ConfigLibraryBanner.BannerItem(ConfigLibraryBanner.BannerKind.PREVIEW, var4);
            }

            if (this.like.hit(var1, var2)) {
               this.like.press();
               return new ConfigLibraryBanner.BannerItem(ConfigLibraryBanner.BannerKind.LIKE, var4);
            }

            List var5 = featuredList();

            for (int var6 = 0; var6 < Math.min(var5.size(), this.dotX.length); var6++) {
               if (var1 >= this.dotX[var6] - 4.0F && var1 <= this.dotX[var6] + 26.0F && Math.abs(var2 - this.dotsY) <= 9.0F) {
                  this.feature(var6, var3);
                  return ConfigLibraryBanner.BannerItem.CONSUMED;
               }
            }

            if (var1 >= this.bx && var1 <= this.bx + this.bw && var2 >= this.by && var2 <= this.by + this.bh) {
               return new ConfigLibraryBanner.BannerItem(ConfigLibraryBanner.BannerKind.PREVIEW, var4);
            }
         }

         if (this.tags.mouseDown(var1, var2) < 0 && this.sort.mouseDown(var1, var2) < 0) {
            return ConfigLibraryBanner.BannerItem.NONE;
         } else {
            this.grid.scrollTo(0.0F);
            return ConfigLibraryBanner.BannerItem.CONSUMED;
         }
      }
   }

   private static float smooth(float var0) {
      var0 = var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   static enum BannerKind {
      NONE,
      CONSUMED,
      GET,
      PREVIEW,
      LIKE,
      CARD,
      RETRY,
      LIBRARY;

      private static ConfigLibraryBanner.BannerKind[] $values() {
         return new ConfigLibraryBanner.BannerKind[]{NONE, CONSUMED, GET, PREVIEW, LIKE, CARD, RETRY, LIBRARY};
      }
   }

   record BannerItem(ConfigLibraryBanner.BannerKind kind,  ConfigLibraryStats entry) {
      static final ConfigLibraryBanner.BannerItem NONE = new ConfigLibraryBanner.BannerItem(ConfigLibraryBanner.BannerKind.NONE, (ConfigLibraryStats)null);
      static final ConfigLibraryBanner.BannerItem CONSUMED = new ConfigLibraryBanner.BannerItem(
         ConfigLibraryBanner.BannerKind.CONSUMED, (ConfigLibraryStats)null
      );
   }
}
