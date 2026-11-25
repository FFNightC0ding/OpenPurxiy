package dev.purxiy.ui.config;

import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.profile.ProfileFormatters;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.font.TrueTypeFont;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

final class ConfigLibrarySupport extends ConfigLibraryPage {
   private static final float ROW = 60.0F;
   private static final float CODE_BOX = 34.0F;
   final ConfigLibraryStats entry;
   private final ConfigLibrarySupport.Listener actions;
   private final String code;
   private final ConfigLibraryDash.DashBar copy = new ConfigLibraryDash.DashBar();
   private final ConfigLibraryDash.DashBar share = new ConfigLibraryDash.DashBar();
   private final ConfigLibraryDash.DashState copyShare = new ConfigLibraryDash.DashState();
   private final ConfigLibraryDash.DashBar file = new ConfigLibraryDash.DashBar();
   private final ConfigLibraryDash.DashBar publish = new ConfigLibraryDash.DashBar();
   private final ConfigLibraryDash.DashBar done = new ConfigLibraryDash.DashBar();
   private float copiedAt = -100.0F;
   private float shareCopiedAt = -100.0F;
   private float savedAt = -100.0F;

   private ConfigLibraryError.LibraryError shared;
   private boolean asking;

   private String problem;
   private float time;

   ConfigLibrarySupport(float[] var1, ConfigLibraryStats var2, ConfigLibrarySupport.Listener var3) {
      super(var1);
      this.entry = var2;
      this.actions = var3;
      this.code = ConfigLibraryTransform.encode(var2);
      this.shared = ConfigLibraryError.known(var2);
      ConfigLibraryHost.checkStatus(false);
   }

   @Override
   float width() {
      return 520.0F;
   }

   @Override
   String title() {
      return "Export “" + this.entry.name + "”";
   }


   @Override
   String subtitle() {
      return "Anyone with SourceByDevFFnightmare DeObfDeCompByGuideX can import it. Your keybinds and typed text stay yours.";
   }

   private boolean discover() {
      return ConfigLibraryHost.online();
   }

   private boolean behind() {
      return ConfigLibraryTransform.isActive(this.entry) && ConfigLibraryTransform.changedSinceSaved();
   }

   @Override
   float bodyHeight(Canvas var1, float var2) {
      return 224.0F + (this.discover() ? 60.0F : 0.0F) + (this.behind() ? 30 : 0);
   }

   @Override
   void body(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6) {
      this.time = var1.time;
      float var7 = var3 + var5;
      float var8 = var4 + 30.0F - 4.0F;
      ConfigLibraryPage.iconTile(var2, var3, var8, 36.0F, "braces", Icons.MAGENTA, 0.6F);
      float var9 = var3 + 50.0F;
      int var10 = this.code.length();
      this.words(var2, var9, var8, "Config code", var10 + " letters  ·  the whole config as text", Icons.TEXT_2, var5 - 50.0F - 110.0F);
      boolean var11 = var1.time - this.copiedAt < 1.6F;
      float var12 = ConfigLibraryDash.DashBar.width(var2, var11 ? "check" : "copy", var11 ? "Copied" : "Copy", 32.0F);
      this.copy
         .render(
            var1,
            var7 - var12,
            var8,
            32.0F,
            var11 ? "check" : "copy",
            var11 ? "Copied" : "Copy",
            var11 ? ConfigLibraryDash.DashTone.DONE : ConfigLibraryDash.DashTone.PRIMARY,
            var6
         );
      float var13 = var4 + 60.0F - 6.0F;
      var2.roundRect(var3 + 50.0F, var13, var5 - 50.0F, 34.0F, 10.0F, 1073741824);
      var2.roundRectOutline(var3 + 50.0F, var13, var5 - 50.0F, 34.0F, 10.0F, 1.0F, 318767103);
      Translations.pause();
      var2.text(
         Fonts.MEDIUM,
         FontMetrics.ellipsize(Fonts.MEDIUM, this.code, 11.0F, var5 - 50.0F - 24.0F, 0.02F),
         var3 + 62.0F,
         var13 + 17.0F,
         11.0F,
         BlendMode.withAlpha(Icons.TEXT_2, 0.8F),
         FontStyle.LEFT,
         FontWeight.MIDDLE,
         0.02F
      );
      Translations.resume();
      var4 = var13 + 34.0F + 10.0F;
      var2.rect(var3, var4, var5, 1.0F, 285212671);
      var8 = var4 + 30.0F;
      this.shareRow(var1, var2, var3, var8, var5, var6);
      var4 += 60.0F;
      var2.rect(var3, var4, var5, 1.0F, 285212671);
      var8 = var4 + 30.0F;
      boolean var14 = var1.time - this.savedAt < 1.6F;
      ConfigLibraryPage.iconTile(var2, var3, var8, 36.0F, "file-down", Icons.MAGENTA, 0.35F);
      this.words(var2, var3 + 50.0F, var8, "Save as a file", "A .json in your configs folder, with its photo", Icons.TEXT_2, var5 - 50.0F - 110.0F);
      var12 = ConfigLibraryDash.DashBar.width(var2, var14 ? "check" : null, var14 ? "Saved" : "Save", 32.0F);
      this.file
         .render(
            var1,
            var7 - var12,
            var8,
            32.0F,
            var14 ? "check" : null,
            var14 ? "Saved" : "Save",
            var14 ? ConfigLibraryDash.DashTone.DONE : ConfigLibraryDash.DashTone.QUIET,
            var6
         );
      var4 += 60.0F;
      if (this.discover()) {
         var2.rect(var3, var4, var5, 1.0F, 285212671);
         var8 = var4 + 30.0F;
         boolean var15 = ConfigLibraryTransform.isShared(this.entry);
         ConfigLibraryPage.iconTile(var2, var3, var8, 36.0F, "globe", Icons.MAGENTA, var15 ? 0.8F : 0.35F);
         this.words(
            var2,
            var3 + 50.0F,
            var8,
            "Publish in Discover",
            var15 ? "Everyone can find it  ·  " + ConfigLibraryTransform.shared().size() + " of 5 published" : "Everyone can find it, with its photo",
            Icons.TEXT_2,
            var5 - 50.0F - 120.0F
         );
         String var16 = var15 ? "Stop" : "Publish";
         var12 = ConfigLibraryDash.DashBar.width(var2, (String)null, var16, 32.0F);
         this.publish.render(var1, var7 - var12, var8, 32.0F, (String)null, var16, ConfigLibraryDash.DashTone.QUIET, var6);
         var4 += 60.0F;
      } else {
         this.publish.shown = false;
      }

      if (this.behind()) {
         int var25 = ConfigLibraryTransform.unsavedChanges();
         var2.circle(var3 + 4.0F, var4 + 16.0F, 2.6F, Theme.tint(-1783297));
         var2.text(
            Fonts.REGULAR,
            FontMetrics.ellipsize(
               Fonts.REGULAR,
               "This is the config as you saved it: save first to take your " + var25 + (var25 == 1 ? " change" : " changes") + " along",
               11.5F,
               var5 - 14.0F,
               0.0F
            ),
            var3 + 14.0F,
            var4 + 16.0F,
            11.5F,
            Icons.TEXT_3,
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
      }
   }

   private void shareRow(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6) {
      float var7 = var3 + var5;
      boolean var8 = ConfigLibraryHost.shareCodes();
      ConfigLibraryError.LibraryError var9 = this.shared;
      ConfigLibraryPage.iconTile(var2, var3, var4, 36.0F, "hash", Icons.MAGENTA, var9 != null ? 0.8F : 0.35F);
      if (var9 != null) {
         String var10 = var9.code();
         float var11 = 0.0F;

         for (int var12 = 0; var12 < var10.length(); var12++) {
            var11 += var2.textWidth(Fonts.BOLD, String.valueOf(var10.charAt(var12)), 22.0F) + (var12 < var10.length() - 1 ? 7 : 0);
         }

         var2.text(Fonts.SEMIBOLD, "Share code", var3 + 50.0F, var4 - 9.0F, 13.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
         TrueTypeFont var21 = Fonts.REGULAR;
         String var13 = until(var9.expiresAt());
         var2.text(var21, "Lasts until " + var13, var3 + 50.0F, var4 + 10.0F, 11.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         boolean var14 = var1.time - this.shareCopiedAt < 1.6F;
         float var15 = var7 - 16.0F;
         this.copyShare.render(var1, var15, var4, 32.0F, var14 ? "check" : "copy", var14, Theme.tint(-1783297), 1.0F, var6);
         float var16 = var15 - 16.0F - 14.0F - var11;
         Translations.pause();

         for (int var17 = 0; var17 < var10.length(); var17++) {
            String var18 = String.valueOf(var10.charAt(var17));
            var16 += var2.text(Fonts.BOLD, var18, var16, var4, 22.0F, Theme.tint(-1783297), FontStyle.LEFT, FontWeight.MIDDLE) + 7.0F;
         }

         Translations.resume();
         this.share.shown = false;
      } else {
         this.copyShare.shown = false;
         int var19 = Icons.TEXT_2;
         String var20;
         if (this.asking) {
            var20 = "Getting a code from the SourceByDevFFnightmare DeObfDeCompByGuideX server…";
         } else if (this.problem != null) {
            String var24 = this.problem;

            var20 = switch (var24) {
               case "offline", "slow_down" -> "Can't reach the SourceByDevFFnightmare DeObfDeCompByGuideX server: try again in a moment";
               case "too_large" -> "This config is too large for a share code";
               default -> "The server would not make a code";
            };
            var19 = -29785;
         } else if (!var8) {
            var20 = ConfigLibraryHost.unknown() ? "Asking the SourceByDevFFnightmare DeObfDeCompByGuideX server…" : "A 5-digit code: comes with the SourceByDevFFnightmare DeObfDeCompByGuideX server";
         } else {
            var20 = "A 5-digit code anyone can type into Import";
         }

         this.words(var2, var3 + 50.0F, var4, "Share code", var20, var19, var5 - 50.0F - 120.0F);
         String var23 = this.problem != null ? "Try again" : "Get code";
         float var25 = ConfigLibraryDash.DashBar.width(var2, (String)null, var23, 32.0F);
         if (this.asking) {
            float var27 = MotionStyle.reduced() ? 0.5F : this.time * 1.4F % 1.0F;
            var2.arc(var7 - var25 / 2.0F, var4, 9.0F, 1.6F, var27 * 360.0F, 100.0F, BlendMode.withAlpha(Theme.tint(-1783297), 0.0F), Theme.tint(-1783297), true);
            this.share.shown = false;
         } else if (!var8 && this.problem == null) {
            ConfigLibraryPage.disabled(var1, this.share, var7 - var25, var4, 32.0F, (String)null, var23);
         } else {
            this.share.render(var1, var7 - var25, var4, 32.0F, (String)null, var23, ConfigLibraryDash.DashTone.QUIET, var6);
         }
      }
   }

   private void words(Canvas var1, float var2, float var3, String var4, String var5, int var6, float var7) {
      var1.text(
         Fonts.SEMIBOLD,
         FontMetrics.ellipsize(Fonts.SEMIBOLD, var4, 13.5F, var7, 0.0F),
         var2,
         var3 - 9.0F,
         13.5F,
         Icons.TEXT,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      var1.text(
         Fonts.REGULAR, FontMetrics.ellipsize(Fonts.REGULAR, var5, 11.5F, var7, 0.0F), var2, var3 + 10.0F, 11.5F, var6, FontStyle.LEFT, FontWeight.MIDDLE
      );
   }

   private static String until(long var0) {
      return ProfileFormatters.date(LocalDate.ofInstant(Instant.ofEpochMilli(var0), ZoneId.systemDefault()));
   }

   @Override
   void footer(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6) {
      float var7 = ConfigLibraryDash.DashBar.width(var2, (String)null, "Done", 34.0F);
      this.done.render(var1, var4 - var7, var5, 34.0F, (String)null, "Done", ConfigLibraryDash.DashTone.QUIET, var6);
   }

   void copyCode() {
      ConfigLibraryTransform.toClipboard(this.code);
      this.copiedAt = this.time;
      this.actions.copied(this.entry);
      SoundRequest.play(SoundEvent.KEY_SET);
   }

   void askCode() {
      if (!this.asking) {
         this.asking = true;
         this.problem = null;
         ConfigLibraryError.create(this.entry, (var1, var2) -> {
            this.asking = false;
            if (var1 == null) {
               this.problem = var2;
               SoundRequest.play(SoundEvent.DENY);
            } else {
               this.shared = var1;
               SoundRequest.play(SoundEvent.SELECT);
            }
         });
      }
   }

   @Override
   boolean press(float var1, float var2) {
      if (this.copy.hit(var1, var2)) {
         this.copy.press();
         this.copyCode();
         return true;
      } else if (this.share.hit(var1, var2)) {
         this.share.press();
         ConfigLibraryHost.checkStatus(true);
         this.askCode();
         return true;
      } else if (this.copyShare.hit(var1, var2) && this.shared != null) {
         this.copyShare.press();
         ConfigLibraryTransform.toClipboard(this.shared.code());
         this.shareCopiedAt = this.time;
         SoundRequest.play(SoundEvent.KEY_SET);
         return true;
      } else if (this.file.hit(var1, var2)) {
         this.file.press();
         this.savedAt = this.time;
         this.actions.saveFile(this.entry);
         return true;
      } else if (this.publish.hit(var1, var2)) {
         this.publish.press();
         this.actions.publish(this.entry, this.publish.x + this.publish.w / 2.0F, this.publish.y + this.publish.h / 2.0F);
         return true;
      } else if (this.done.hit(var1, var2)) {
         this.close();
         SoundRequest.play(SoundEvent.COLLAPSE);
         return true;
      } else {
         return false;
      }
   }

   @Override
   void submit() {
      this.close();
      SoundRequest.play(SoundEvent.COLLAPSE);
   }

   @Override
   boolean key(int var1, boolean var2) {
      if (var2 && var1 == 67) {
         this.copyCode();
         return true;
      } else {
         return false;
      }
   }

   interface Listener {
      void copied(ConfigLibraryStats var1);

      void saveFile(ConfigLibraryStats var1);

      void publish(ConfigLibraryStats var1, float var2, float var3);
   }
}
