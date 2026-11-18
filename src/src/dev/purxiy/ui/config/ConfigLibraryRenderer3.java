// Sourced By DevFFNightmare
package dev.purxiy.ui.config;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.modules.Category;
import dev.purxiy.modules.Module;
import dev.purxiy.modules.SettingSection;
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
import dev.purxiy.ui.widget.Slider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

final class ConfigLibraryRenderer3 {
   private static final float PAD = 26.0F;
   private static final float CHIP = 24.0F;
   final ConfigLibraryFactory2 entry;
   
   private final ConfigLibraryRenderer9 card;
   private final float[] origin;
   private final float originRadius;
   private final Animator open = new Animator(0.0F, 240.0F, 28.0F);
   private final Animator scroll = Animator.smooth(0.0F);
   private final Animator starHover = Animator.smooth(0.0F);
   private final ConfigLibraryRenderer7.TypeT look = new ConfigLibraryRenderer7.TypeT();
   private final ConfigLibraryRenderer8.TypeC primary = new ConfigLibraryRenderer8.TypeC();
   private final ConfigLibraryRenderer8.TypeC second = new ConfigLibraryRenderer8.TypeC();
   private final ConfigLibraryRenderer8.TypeC export = new ConfigLibraryRenderer8.TypeC();
   private final ConfigLibraryRenderer8.TypeC rename = new ConfigLibraryRenderer8.TypeC();
   private final ConfigLibraryRenderer8.TypeC duplicate = new ConfigLibraryRenderer8.TypeC();
   private final ConfigLibraryRenderer8.TypeC delete = new ConfigLibraryRenderer8.TypeC();
   private final ConfigLibraryRenderer8.TypeW close = new ConfigLibraryRenderer8.TypeW();
   private final ConfigLibraryRenderer8.TypeC photo = new ConfigLibraryRenderer8.TypeC();
   private final ConfigLibraryRenderer8.TypeW unphoto = new ConfigLibraryRenderer8.TypeW();
   private final ConfigLibraryRenderer8.TypeC report = new ConfigLibraryRenderer8.TypeC();
   private final Animator reveal = Animator.smooth(0.0F);
   private final Animator photoHover = Animator.smooth(0.0F);
   private final Object codeKey = new Object();
   private float codeX = -1.0F;
   private float codeY;
   private float codeW;
   private boolean reported;
   private final Object skyKey = new Object();
   private final List<ConfigLibraryRenderer3.TypeA> groups = new ArrayList<>();
   private final List<Module> on = new ArrayList<>();
   private final List<Module> off = new ArrayList<>();
   private ConfigLibraryTransform2.TypeD changes = ConfigLibraryTransform2.TypeD.NONE;
   private float studiedAt = Float.NaN;
   private boolean closing;
   private float scrollTarget;
   private float maxScroll;
   private float px;
   private float py;
   private float pw;
   private float ph;
   private float skyX;
   private float skyY;
   private float skyW;
   private float skyH;
   private float listTop;
   private float listBottom;
   private float listX;
   private float listW;
   private int star = -1;
   private float starX;
   private float starY;
   
   private Module chipHover;
   
   private Slider field;
   private String draft = "";

   ConfigLibraryRenderer3(ConfigLibraryFactory2 var1,  ConfigLibraryRenderer9 var2, float[] var3, float var4) {
      this.entry = var1;
      this.card = var2;
      this.origin = var3;
      this.originRadius = var4;
      this.open.target(1.0F);
      this.study();
   }

   private void study() {
      this.groups.clear();
      this.on.clear();
      this.off.clear();
      this.changes = ConfigLibraryTransform2.changes(this.entry.settings, ConfigLibraryTransform2.capture());
      HashSet var1 = new HashSet<>(ConfigLibraryTransform.liveModules());

      for (Category var5 : Category.values()) {
         ArrayList var6 = new ArrayList();
         int var7 = 0;

         for (Module var9 : SettingSection.of(var5)) {
            if (var9.toggleable()) {
               var7++;
               boolean var10 = this.entry.modules.contains(var9.id());
               if (var10) {
                  var6.add(var9);
               }

               if (var10 && !var1.contains(var9.id())) {
                  this.on.add(var9);
               } else if (!var10 && var1.contains(var9.id())) {
                  this.off.add(var9);
               }
            }
         }

         if (!var6.isEmpty()) {
            this.groups.add(new ConfigLibraryRenderer3.TypeA(var5, var6, var7));
         }
      }
   }

   
   private static String detail(ConfigLibraryTransform2.TypeD var0) {
      ArrayList var1 = new ArrayList();
      if (var0.settings() > 0) {
         int var2 = var0.settings();
         var1.add(Translations.tr(var2 + (var0.settings() == 1 ? " setting" : " settings")));
      }

      if (var0.hud()) {
         var1.add(Translations.tr("HUD layout"));
      }

      if (var0.menuSize()) {
         var1.add(Translations.tr("menu size"));
      }

      if (var0.keys()) {
         var1.add(Translations.tr("keybinds"));
      }

      if (var1.isEmpty()) {
         return null;
      } else {
         String var3 = (String)var1.removeLast();
         return var1.isEmpty() ? var3 : String.join(", ", var1) + " " + Translations.tr("and") + " " + var3;
      }
   }

   void close() {
      if (!this.closing) {
         this.closing = true;
         this.open.target(0.0F);
         this.endRename(false);
      }
   }

   boolean closing() {
      return this.closing;
   }

   boolean finished() {
      return this.closing && this.open.value() < 0.02F;
   }

   
   ConfigLibraryRenderer9 card() {
      return this.card;
   }

   float[] rect() {
      return new float[]{this.px, this.py, this.pw, this.ph};
   }

   float[] secondButton() {
      return new float[]{this.second.x + this.second.w / 2.0F, this.second.y + this.second.h / 2.0F};
   }

   float[] primaryButton() {
      return new float[]{this.primary.x + this.primary.w / 2.0F, this.primary.y + this.primary.h / 2.0F};
   }

   float[] exportButton() {
      return new float[]{this.export.x, this.export.y, this.export.w, this.export.h};
   }

   void reported() {
      this.reported = true;
   }

   
   Slider field() {
      return this.field;
   }

   void startRename() {
      this.draft = this.entry.name;
      this.field = new Slider(new Slider.Listener() {
         @Override
         public String get() {
            return ConfigLibraryRenderer3.this.draft;
         }

         @Override
         public void set(String var1) {
            ConfigLibraryRenderer3.this.draft = var1.replace('\n', ' ');
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

   void render(DrawContext var1) {
      Canvas var2 = var1.canvas;
      float var3 = var1.time;
      float var4 = this.open.update(MotionStyle.PANEL.dt(var1.dt));
      float var5 = Math.max(0.0F, Math.min(1.0F, var4));
      float var6 = var2.width();
      float var7 = var2.height();
      ConfigLibraryFactory2 var8 = this.entry;
      this.pw = Math.min(920.0F, var6 - 2.0F * Math.max(40.0F, Math.min(72.0F, var6 * 0.045F)));
      this.ph = Math.min(586.0F, var7 - 96.0F);
      this.px = var6 / 2.0F - this.pw / 2.0F;
      this.py = Math.max(64.0F, var7 / 2.0F - this.ph / 2.0F + 12.0F);
      var2.rect(0.0F, 0.0F, var6, var7, BlendMode.withAlpha(Theme.tint(-16514294), 0.42F * var5));
      var2.captureBackdrop();
      float[] var9 = this.origin;
      float var10 = lerp(var9[0], this.px, var4);
      float var11 = lerp(var9[1], this.py, var4);
      float var12 = lerp(var9[2], this.pw, var4);
      float var13 = lerp(var9[3], this.ph, var4);
      float var14 = lerp(this.originRadius, 24.0F, var5);
      KeyLabel.surface(var2, var10, var11, var12, var13, var14, lerp(0.4F, 1.0F, var5), ConfigLibraryTransform.isActive(var8) ? 0.4F : 0.15F);
      float var15 = this.pw * 0.43F;
      this.skyX = this.px + 26.0F;
      this.skyY = this.py + 26.0F;
      this.skyW = var15 - 26.0F;
      this.skyH = Math.min(this.skyW * 0.72F, this.ph * 0.5F);
      float var16 = lerp(var9[0], this.skyX, var4);
      float var17 = lerp(var9[1], this.skyY, var4);
      float var18 = lerp(var9[2], this.skyW, var4);
      float var19 = lerp(var9[3], this.skyH, var4);
      boolean var20 = var4 > 0.45F;
      boolean var21 = !this.closing && var5 > 0.9F && !var1.masked();
      boolean var22 = var21 && var1.hover(this.skyKey, var16, var17, var18, var19, DrawContext.TypeE.ARROW);
      this.look.time = var3;
      this.look.unlit = 0.3F * smooth((var4 - 0.3F) / 0.7F);
      this.look.hover = var22 ? 0.6F : 0.25F;
      this.look.pointerX = var22 ? Math.max(-1.0F, Math.min(1.0F, (var1.mouseX - (var16 + var18 / 2.0F)) / (var18 / 2.0F))) : 0.0F;
      this.look.pointerY = var22 ? Math.max(-1.0F, Math.min(1.0F, (var1.mouseY - (var17 + var19 / 2.0F)) / (var19 / 2.0F))) : 0.0F;
      this.look.highlight = this.chipHover != null ? ConfigLibraryRenderer7.indexOf(this.chipHover.id()) : -1;
      this.star = var22 ? ConfigLibraryRenderer7.starAt(var8, var16, var17, var18, var19, this.look, var1.mouseX, var1.mouseY, 9.0F) : -1;
      if (this.star >= 0 && this.look.highlight < 0) {
         this.look.highlight = this.star;
      }

      var2.pushClip(var16, var17, var18, var19, lerp(this.originRadius, 16.0F, var5));
      float var23 = ConfigLibraryRenderer13.appear(var8, true);
      if (var23 < 1.0F) {
         ConfigLibraryRenderer7.draw(var2, var8, var16, var17, var18, var19, this.look);
      }

      float var24 = this.reveal.target(!var22 && this.chipHover == null ? 0.0F : 1.0F).update(MotionStyle.HOVER.dt(var1.dt));
      if (var23 > 0.0F) {
         ConfigLibraryRenderer13.draw(
            var2,
            var8,
            var16,
            var17,
            var18,
            var19,
            0.0F,
            1.02F + 0.025F * var24,
            this.look.pointerX * 0.4F,
            this.look.pointerY * 0.4F,
            true,
            ConfigLibraryRenderer13.Mode.NONE,
            0.0F
         );
         if (var24 > 0.01F) {
            var2.rect(var16, var17, var18, var19, BlendMode.withAlpha(var8.night(), 0.62F * var24 * var23));
            var2.pushAlpha(var24 * var23);
            this.look.night = false;
            ConfigLibraryRenderer7.draw(var2, var8, var16, var17, var18, var19, this.look);
            this.look.night = true;
            var2.popAlpha();
         }
      }

      if (ConfigLibraryRenderer13.importing(var8)) {
         ConfigLibraryRenderer8.reading(var2, var16, var17, var18, var19, var3);
      }

      var2.popClip();
      var2.roundRectOutline(var16, var17, var18, var19, lerp(this.originRadius, 16.0F, var5), 1.0F, BlendMode.withAlpha(-1, 0.1F * var5));
      if (var8.mine() && var20) {
         this.photoButtons(var1, var2, var8, var16, var17, var18, var19, smooth((var4 - 0.62F) / 0.38F), var21, var22);
      } else {
         this.photo.shown = false;
         this.unphoto.shown = false;
      }

      float var25 = lerp(14.5F, 22.0F, var4);
      float var26 = var9[1] + 128.0F + 33.0F - 10.0F;
      float var27 = lerp(this.card != null ? var26 : this.skyY + this.skyH + 34.0F, this.skyY + this.skyH + 34.0F, var4);
      float var28 = lerp(var9[0] + 16.0F, this.skyX, var4);
      if (this.field == null) {
         Translations.pause();
         var2.text(
            Fonts.BOLD,
            FontMetrics.ellipsize(Fonts.BOLD, var8.name, var25, this.skyW, 0.0F),
            var28,
            var27,
            var25,
            BlendMode.withAlpha(Icons.TEXT, this.card != null ? 1.0F : var5),
            FontStyle.LEFT,
            FontWeight.MIDDLE,
            -0.01F
         );
         Translations.resume();
      }

      float var29 = smooth((var4 - 0.62F) / 0.38F);
      if (var20 && !(var29 <= 0.01F)) {
         var2.pushClip(var10, var11, var12, var13, var14);
         var2.pushAlpha(var29);
         if (this.field != null) {
            this.field.render(var1, this.skyX - 4.0F, var27, this.skyW + 8.0F, var21);
         }

         this.left(var1, var2, var8, var27, var3, var21);
         var2.popAlpha();
         float var30 = smooth((var4 - 0.74F) / 0.26F);
         var2.push();
         var2.translate((1.0F - var30) * 14.0F, 0.0F);
         var2.pushAlpha(var30);
         this.right(var1, var2, var8, var3, var21);
         var2.popAlpha();
         var2.pop();
         var2.pushAlpha(var29);
         this.close.render(var1, this.px + this.pw - 20.0F - 15.0F, this.py + 20.0F + 15.0F, 30.0F, "x", false, 0, 1.0F, var21);
         var2.popAlpha();
         var2.popClip();
         if (this.star >= 0 && (ConfigLibraryRenderer13.appear(var8, true) <= 0.0F || this.reveal.value() > 0.6F)) {
            float var31 = this.starHover.target(1.0F).update(MotionStyle.HOVER.dt(var1.dt));
            Module var32 = ConfigLibraryRenderer7.stars()[this.star].module;
            float[] var33 = ConfigLibraryRenderer7.position(this.star, var16, var17, var18, var19, this.look);
            this.starX = var33[0];
            this.starY = var33[1];
            boolean var34 = var8.figure().lit[this.star];
            ConfigLibraryRenderer8.tooltip(
               var2, this.starX, this.starY - 6.0F, Translations.shown(var32.name()), var34 ? "On in this config" : "Off in this config", var31
            );
         } else {
            this.starHover.target(0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         }
      }
   }

   private void left(DrawContext var1, Canvas var2, ConfigLibraryFactory2 var3, float var4, float var5, boolean var6) {
      float var7 = this.skyX;
      float var8 = this.skyW;
      float var9 = var4 + 26.0F;
      ConfigLibraryRenderer8.face(var2, var3.author, var7, var9 - 9.0F, 18.0F);
      String var10 = var3.mine() && (var3.source == ConfigLibraryFactory2.TypeT.SAVED || var3.author.equals(ConfigLibraryTransform.you()))
         ? Translations.shown("Made by you")
         : var3.author;
      Translations.pause();
      float var11 = var2.text(Fonts.MEDIUM, var10, var7 + 26.0F, var9, 12.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      var11 += var2.text(Fonts.MEDIUM, "  ·  ", var7 + 26.0F + var11, var9, 12.0F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
      Translations.resume();
      String var12 = var3.mine() ? "edited " + ConfigLibraryRenderer8.ago(var3.edited) : "shared " + ConfigLibraryRenderer8.ago(var3.created);
      var2.text(Fonts.MEDIUM, var12, var7 + 26.0F + var11, var9, 12.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      var9 += 24.0F;
      if (!var3.description.isBlank()) {
         Translations.pause();
         List var13 = FontMetrics.wrap(Fonts.REGULAR, var3.description, 12.5F, var8, 0.0F);

         for (int var14 = 0; var14 < Math.min(3, var13.size()); var14++) {
            var2.text(Fonts.REGULAR, (CharSequence)var13.get(var14), var7, var9 + var14 * 18, 12.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         }

         Translations.resume();
         var9 += Math.min(3, var13.size()) * 18 + 6;
      }

      boolean var22 = ConfigLibraryTransform.isShared(var3);
      if (!var3.mine() || var22) {
         var2.icon("heart", var7 + 6.0F, var9, 12.0F, var3.liked ? Icons.PINK : Icons.TEXT_3);
         Translations.pause();
         float var23 = var7
            + 16.0F
            + var2.text(
               Fonts.MEDIUM,
               Translations.tr(ConfigLibraryRenderer8.count(var3.likes)) + " " + Translations.plural(var3.likes, "like", "likes"),
               var7 + 16.0F,
               var9,
               11.5F,
               Icons.TEXT_2,
               FontStyle.LEFT,
               FontWeight.MIDDLE
            );
         var2.icon("download", var23 + 20.0F, var9, 12.0F, Icons.TEXT_3);
         var23 += 30.0F
            + var2.text(
               Fonts.MEDIUM,
               Translations.tr(ConfigLibraryRenderer8.count(var3.downloads)) + " " + Translations.plural(var3.downloads, "download", "downloads"),
               var23 + 30.0F,
               var9,
               11.5F,
               Icons.TEXT_2,
               FontStyle.LEFT,
               FontWeight.MIDDLE
            );
         Translations.resume();
         this.codeX = -1.0F;
         if (var22) {
            ConfigLibraryUpdater.TypeG var15 = ConfigLibraryUpdater.status(var3);
            float var16 = var2.text(Fonts.MEDIUM, "  ·  ", var23, var9, 11.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
            if (var3.code != null && var15 == ConfigLibraryUpdater.TypeG.ONLINE) {
               Translations.pause();
               float var32 = var2.textWidth(Fonts.MEDIUM, var3.code, 11.5F);
               boolean var18 = var6 && var1.hover(this.codeKey, var23 + var16 - 4.0F, var9 - 10.0F, var32 + 26.0F, 20.0F, DrawContext.TypeE.HAND);
               var2.text(Fonts.MEDIUM, var3.code, var23 + var16, var9, 11.5F, var18 ? Theme.tint(-1783297) : Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
               Translations.resume();
               var2.icon("copy", var23 + var16 + var32 + 9.0F, var9, 10.5F, var18 ? Theme.tint(-1783297) : Icons.TEXT_3);
               this.codeX = var23 + var16 - 4.0F;
               this.codeY = var9;
               this.codeW = var32 + 26.0F;
            } else {
               var2.text(Fonts.MEDIUM, switch (var15) {
                  case WAITING -> "waiting for the server";
                  case REFUSED -> "not online";
                  default -> "going online…";
               }, var23 + var16, var9, 11.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
            }
         }

         var9 += 22.0F;
      }

      if (var3.tags.length > 0) {
         StringBuilder var25 = new StringBuilder();

         for (String var36 : var3.tags) {
            var25.append(var25.isEmpty() ? "" : "  ·  ").append(Translations.tr(var36));
         }

         Translations.pause();
         var2.text(Fonts.MEDIUM, var25, var7, var9, 11.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
         Translations.resume();
      }

      float var26 = this.py + this.ph - 26.0F - 17.0F;
      if (var3.mine()) {
         boolean var28 = ConfigLibraryTransform.isActive(var3);
         boolean var31 = var28 && ConfigLibraryTransform.changedSinceSaved();
         float var35;
         if (var31) {
            var35 = var7 + this.primary.render(var1, var7, var26, 34.0F, "save", "Save changes", ConfigLibraryRenderer8.TypeI.PRIMARY, var6) + 8.0F;
            var35 += this.second.render(var1, var35, var26, 34.0F, "rotate-ccw", "Undo changes", ConfigLibraryRenderer8.TypeI.QUIET, var6) + 8.0F;
         } else {
            var35 = var7
               + this.primary
                  .render(
                     var1,
                     var7,
                     var26,
                     34.0F,
                     var28 ? "check" : null,
                     var28 ? "In use" : "Load config",
                     var28 ? ConfigLibraryRenderer8.TypeI.DONE : ConfigLibraryRenderer8.TypeI.PRIMARY,
                     var6
                  )
               + 8.0F;
            this.second.shown = false;
         }

         this.export.render(var1, var35, var26, 34.0F, "share", "Export", ConfigLibraryRenderer8.TypeI.QUIET, var6);
         float var37 = var26 - 40.0F;
         float var19 = var7 - 8.0F;
         var19 += this.rename.render(var1, var19, var37, 26.0F, "pen-line", "Rename", ConfigLibraryRenderer8.TypeI.TEXT, var6);
         var19 += this.duplicate.render(var1, var19, var37, 26.0F, "copy-plus", "Duplicate", ConfigLibraryRenderer8.TypeI.TEXT, var6);
         this.delete.render(var1, var19, var37, 26.0F, "trash-2", "Delete", ConfigLibraryRenderer8.TypeI.DANGER, var6);
      } else {
         float var29;
         if (var3.own) {
            var29 = var7 + this.primary.render(var1, var7, var26, 34.0F, "globe", "Yours", ConfigLibraryRenderer8.TypeI.DONE, var6) + 8.0F;
         } else {
            var29 = var7
               + this.primary
                  .render(
                     var1,
                     var7,
                     var26,
                     34.0F,
                     var3.added ? "check" : "download",
                     var3.added ? "Added" : "Get config",
                     var3.added ? ConfigLibraryRenderer8.TypeI.DONE : ConfigLibraryRenderer8.TypeI.PRIMARY,
                     var6
                  )
               + 8.0F;
         }

         this.second
            .render(
               var1,
               var29,
               var26,
               34.0F,
               "heart",
               var3.liked ? "Liked" : "Like",
               var3.liked ? ConfigLibraryRenderer8.TypeI.DONE : ConfigLibraryRenderer8.TypeI.QUIET,
               var6
            );
         this.second.shown = var6 && !var3.own;
         this.export.shown = false;
         if (!var3.own) {
            this.report
               .render(
                  var1,
                  var7 - 8.0F,
                  var26 - 40.0F,
                  26.0F,
                  "flag",
                  this.reported ? "Reported" : "Report",
                  this.reported ? ConfigLibraryRenderer8.TypeI.DONE : ConfigLibraryRenderer8.TypeI.TEXT,
                  var6 && !this.reported
               );
         } else {
            this.report.shown = false;
         }
      }
   }

   private void photoButtons(
      DrawContext var1, Canvas var2, ConfigLibraryFactory2 var3, float var4, float var5, float var6, float var7, float var8, boolean var9, boolean var10
   ) {
      if (var8 <= 0.01F) {
         this.photo.shown = false;
         this.unphoto.shown = false;
      } else {
         float var11 = this.photoHover
            .target(!var10 && !(this.photo.hover.value() > 0.5F) && !(this.unphoto.hover.value() > 0.5F) ? 0.0F : 1.0F)
            .update(MotionStyle.HOVER.dt(var1.dt));
         boolean var12 = var3.photo != null;
         String var13 = var12 ? "Change photo" : "Add photo";
         String var14 = var12 ? "image" : "image-plus";
         float var15 = 30.0F;
         float var16 = ConfigLibraryRenderer8.TypeC.width(var2, var14, var13, var15);
         float var17 = var5 + var7 - 12.0F - var15 / 2.0F;
         float var18 = var4 + var6 - 12.0F - var16 - (var12 ? var15 + 6.0F : 0.0F);
         var2.pushAlpha(var8 * (0.78F + 0.22F * var11));
         var2.shadow(var18, var17 - var15 / 2.0F, var16, var15, var15 / 2.0F, 12.0F, 0.0F, 0.0F, 3.0F, 1426063360);
         var2.roundRect(var18, var17 - var15 / 2.0F, var16, var15, var15 / 2.0F, Theme.tint(-1072558558));
         this.photo.render(var1, var18, var17, var15, var14, var13, ConfigLibraryRenderer8.TypeI.QUIET, var9 && !ConfigLibraryRenderer13.importing(var3));
         if (var12) {
            this.unphoto.render(var1, var4 + var6 - 12.0F - var15 / 2.0F, var17, var15, "trash-2", false, 0, 1.0F, var9);
            if (this.unphoto.hover.value() > 0.55F) {
               ConfigLibraryRenderer8.tooltip(
                  var2,
                  var4 + var6 - 12.0F - var15 / 2.0F,
                  var17 - var15 / 2.0F,
                  Translations.shown("Remove photo"),
                  (String)null,
                  (this.unphoto.hover.value() - 0.55F) / 0.45F
               );
            }
         } else {
            this.unphoto.shown = false;
         }

         var2.popAlpha();
      }
   }

   private void right(DrawContext var1, Canvas var2, ConfigLibraryFactory2 var3, float var4, boolean var5) {
      float var6 = this.px + this.pw * 0.43F + 22.0F;
      float var7 = this.px + this.pw - 26.0F - var6;
      float var8 = this.py + 26.0F + 8.0F;
      if (!(var4 - this.studiedAt < 1.0F)) {
         this.studiedAt = var4;
         this.study();
      }

      boolean var9 = ConfigLibraryTransform.isActive(var3) && !ConfigLibraryTransform.changedSinceSaved();
      ConfigLibraryRenderer8.label(
         var2,
         var9 ? "IN USE" : (ConfigLibraryTransform.isActive(var3) ? "IN USE  ·  IF YOU UNDO YOUR CHANGES" : "IF YOU LOAD IT"),
         var6,
         var8,
         Icons.TEXT_3,
         FontStyle.LEFT
      );
      var8 += 22.0F;
      boolean var10 = !this.on.isEmpty() || !this.off.isEmpty();
      String var11;
      if (var9) {
         var11 = "This is the config you are using";
      } else if (!var10 && !this.changes.any()) {
         var11 = "Exactly what you have now";
      } else if (!var10) {
         var11 = "The same modules, set differently";
      } else {
         int var12 = this.on.size();
         var11 = "Turns on " + var12 + " and off " + this.off.size() + " of your modules";
      }

      var2.text(Fonts.MEDIUM, var11, var6, var8, 13.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      var8 += 22.0F;
      String var27 = var9 ? null : detail(this.changes);
      if (var27 != null) {
         var2.text(Fonts.MEDIUM, (var10 ? "+ " : "") + var27, var6, var8 - 4.0F, 11.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         var8 += 16.0F;
      }

      if (!var9 && var10) {
         float var13 = var6;
         float var14 = var8;
         int var15 = 1;
         int var16 = 0;
         ArrayList var17 = new ArrayList<>(this.on);
         var17.addAll(this.off);

         for (Module var19 : (Iterable<Module>)(Object)(var17)) {
            boolean var20 = this.on.contains(var19);
            String var21 = Translations.shown(var19.name());
            float var22 = chipWidth(var2, var21);
            if (var13 + var22 > var6 + var7) {
               if (var15 == 2) {
                  break;
               }

               var15++;
               var13 = var6;
               var14 += 30.0F;
            }

            this.chip(var1, var2, var19, var21, var13, var14, var22, var20 ? 1 : -1, false);
            var13 += var22 + 6.0F;
            var16++;
         }

         int var35 = var17.size() - var16;
         if (var35 > 0) {
            var2.text(Fonts.MEDIUM, "+" + var35 + " more", var13 + 2.0F, var14 + 12.0F, 11.0F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
         }

         var8 = var14 + 24.0F + 14.0F;
      }

      var2.rect(var6, var8, var7, 1.0F, 318767103);
      var8 += 22.0F;
      ConfigLibraryRenderer8.label(var2, "WHAT'S INSIDE", var6, var8, Icons.TEXT_3, FontStyle.LEFT);
      int var28 = var3.moduleCount();
      int var29 = this.groups.size();
      var2.text(
         Fonts.MEDIUM,
         ConfigLibraryRenderer8.modules(var28) + " in " + var29 + (var29 == 1 ? " category" : " categories"),
         var6 + var7,
         var8,
         11.5F,
         Icons.TEXT_2,
         FontStyle.RIGHT,
         FontWeight.MIDDLE
      );
      var8 += 18.0F;
      this.listX = var6;
      this.listW = var7;
      this.listTop = var8;
      this.listBottom = this.py + this.ph - 26.0F;
      float var30 = 0.0F;

      for (ConfigLibraryRenderer3.TypeA var33 : this.groups) {
         var30 += this.groupHeight(var2, var33, var7);
      }

      this.maxScroll = Math.max(0.0F, var30 - (this.listBottom - this.listTop));
      this.scrollTarget = Math.max(0.0F, Math.min(this.maxScroll, this.scrollTarget));
      float var32 = this.scroll.target(this.scrollTarget).update(MotionStyle.PANEL.dt(var1.dt));
      this.chipHover = null;
      var2.pushClip(var6 - 8.0F, this.listTop, var7 + 16.0F, this.listBottom - this.listTop);
      float var34 = this.listTop - var32;

      for (ConfigLibraryRenderer3.TypeA var38 : this.groups) {
         float var40 = this.groupHeight(var2, var38, var7);
         if (var34 + var40 > this.listTop - 4.0F && var34 < this.listBottom + 4.0F) {
            float var42 = Math.min(var34 + var40 - this.listTop + 10.0F, this.listBottom - var34);
            var2.pushAlpha(smooth(var42 / 40.0F));
            this.group(var1, var2, var38, var6, var34, var7, var5 && var1.mouseY >= this.listTop && var1.mouseY <= this.listBottom);
            var2.popAlpha();
         }

         var34 += var40;
      }

      var2.popClip();
      if (this.maxScroll > 1.0F) {
         float var37 = this.listBottom - this.listTop - 8.0F;
         float var39 = Math.max(30.0F, var37 * (this.listBottom - this.listTop) / var30);
         float var41 = this.listTop + 4.0F + (var37 - var39) * (var32 / this.maxScroll);
         var2.roundRect(var6 + var7 + 10.0F, var41, 3.0F, var39, 1.5F, BlendMode.withAlpha(-1, 0.16F));
      }
   }

   private float groupHeight(Canvas var1, ConfigLibraryRenderer3.TypeA var2, float var3) {
      float var4 = 0.0F;
      int var5 = 1;

      for (Module var7 : var2.modules()) {
         float var8 = chipWidth(var1, Translations.shown(var7.name()));
         if (var4 + var8 > var3 && var4 > 0.0F) {
            var5++;
            var4 = 0.0F;
         }

         var4 += var8 + 6.0F;
      }

      return 26.0F + var5 * 30.0F + 10.0F;
   }

   private void group(DrawContext var1, Canvas var2, ConfigLibraryRenderer3.TypeA var3, float var4, float var5, float var6, boolean var7) {
      float var8 = var5 + 11.0F;
      var2.icon(var3.category().icon, var4 + 7.0F, var8, 13.0F, Icons.TEXT_2);
      var2.text(Fonts.SEMIBOLD, var3.category().displayName, var4 + 20.0F, var8, 12.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      var2.text(Fonts.MEDIUM, var3.modules().size() + " of " + var3.total(), var4 + var6, var8, 11.0F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.MIDDLE);
      float var9 = var4;
      float var10 = var5 + 26.0F;

      for (Module var12 : var3.modules()) {
         String var13 = Translations.shown(var12.name());
         float var14 = chipWidth(var2, var13);
         if (var9 + var14 > var4 + var6 && var9 > var4) {
            var9 = var4;
            var10 += 30.0F;
         }

         this.chip(var1, var2, var12, var13, var9, var10, var14, 0, var7);
         var9 += var14 + 6.0F;
      }
   }

   private static float chipWidth(Canvas var0, String var1) {
      Translations.pause();
      float var2 = var0.textWidth(Fonts.MEDIUM, var1, 11.0F) + 34.0F;
      Translations.resume();
      return var2;
   }

   private void chip(DrawContext var1, Canvas var2, Module var3, String var4, float var5, float var6, float var7, int var8, boolean var9) {
      boolean var10 = var9 && var1.hover(var3, var5, var6, var7, 24.0F, DrawContext.TypeE.ARROW);
      boolean var11 = var10 || this.star >= 0 && ConfigLibraryRenderer7.stars()[this.star].module == var3;
      if (var10) {
         this.chipHover = var3;
      }

      int var12 = this.entry.accent();
      int var13 = var8 > 0 ? BlendMode.withAlpha(var12, var11 ? 0.3F : 0.17F) : (var8 < 0 ? (var11 ? 385875967 : 184549375) : (var11 ? 520093695 : 234881023));
      int var14 = var8 > 0 ? BlendMode.withAlpha(var12, 0.5F) : (var11 ? BlendMode.withAlpha(var12, 0.7F) : 385875967);
      var2.roundRect(var5, var6, var7, 24.0F, 12.0F, var13);
      var2.roundRectOutline(var5, var6, var7, 24.0F, 12.0F, 1.0F, var14);
      if (var11) {
         var2.glow(var5, var6, var7, 24.0F, 12.0F, 10.0F, BlendMode.withAlpha(var12, 0.25F));
      }

      int var15 = var8 < 0 ? Icons.TEXT_3 : (var8 > 0 ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var11 ? 1.0F : 0.0F));
      var2.icon(var8 > 0 ? "plus" : (var8 < 0 ? "minus" : var3.icon()), var5 + 13.0F, var6 + 12.0F, 11.5F, var8 > 0 ? Theme.tint(-1783297) : var15);
      Translations.pause();
      float var16 = var2.text(Fonts.MEDIUM, var4, var5 + 24.0F, var6 + 12.0F, 11.0F, var15, FontStyle.LEFT, FontWeight.MIDDLE);
      Translations.resume();
      if (var8 < 0) {
         var2.rect(var5 + 23.0F, var6 + 12.0F, var16 + 2.0F, 1.0F, BlendMode.withAlpha(Icons.TEXT_3, 0.9F));
      }
   }

   boolean contains(float var1, float var2) {
      return var1 >= this.px && var1 <= this.px + this.pw && var2 >= this.py && var2 <= this.py + this.ph;
   }

   ConfigLibraryRenderer3.TypeL mouseDown(float var1, float var2) {
      if (this.closing) {
         return ConfigLibraryRenderer3.TypeL.CONSUMED;
      } else {
         if (this.field != null) {
            if (this.field.mouseDown(var1, var2)) {
               return ConfigLibraryRenderer3.TypeL.CONSUMED;
            }

            this.endRename(false);
         }

         if (!this.contains(var1, var2) || this.close.hit(var1, var2)) {
            return ConfigLibraryRenderer3.TypeL.CLOSE;
         } else if (this.photo.hit(var1, var2)) {
            this.photo.press();
            return ConfigLibraryRenderer3.TypeL.PHOTO;
         } else if (this.unphoto.hit(var1, var2)) {
            this.unphoto.press();
            return ConfigLibraryRenderer3.TypeL.UNPHOTO;
         } else if (this.codeX >= 0.0F && var1 >= this.codeX && var1 <= this.codeX + this.codeW && Math.abs(var2 - this.codeY) <= 10.0F) {
            return ConfigLibraryRenderer3.TypeL.COPY_CODE;
         } else if (!this.entry.mine() && this.report.hit(var1, var2)) {
            this.report.press();
            return ConfigLibraryRenderer3.TypeL.REPORT;
         } else if (this.primary.hit(var1, var2)) {
            this.primary.press();
            if (!this.entry.mine()) {
               return ConfigLibraryRenderer3.TypeL.GET;
            } else {
               return ConfigLibraryTransform.isActive(this.entry) && ConfigLibraryTransform.changedSinceSaved()
                  ? ConfigLibraryRenderer3.TypeL.SAVE
                  : ConfigLibraryRenderer3.TypeL.LOAD;
            }
         } else if (this.second.hit(var1, var2)) {
            this.second.press();
            return this.entry.mine() ? ConfigLibraryRenderer3.TypeL.REVERT : ConfigLibraryRenderer3.TypeL.LIKE;
         } else if (this.export.hit(var1, var2)) {
            this.export.press();
            return ConfigLibraryRenderer3.TypeL.EXPORT;
         } else {
            if (this.entry.mine()) {
               if (this.rename.hit(var1, var2)) {
                  return ConfigLibraryRenderer3.TypeL.RENAME;
               }

               if (this.duplicate.hit(var1, var2)) {
                  this.duplicate.press();
                  return ConfigLibraryRenderer3.TypeL.DUPLICATE;
               }

               if (this.delete.hit(var1, var2)) {
                  return ConfigLibraryRenderer3.TypeL.DELETE;
               }
            }

            return ConfigLibraryRenderer3.TypeL.CONSUMED;
         }
      }
   }

   void mouseDrag(float var1, float var2) {
      if (this.field != null) {
         this.field.mouseDrag(var1, var2);
      }
   }

   void mouseUp() {
      if (this.field != null) {
         this.field.mouseUp();
      }
   }

   void scroll(double var1) {
      this.scrollTarget = (float)Math.max(0.0, Math.min((double)this.maxScroll, this.scrollTarget - var1 * 48.0));
   }

   void scrollTo(float var1) {
      this.scrollTarget = var1;
   }

   private static float lerp(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   private static float smooth(float var0) {
      var0 = var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private record TypeA(Category category, List<Module> modules, int total) {
   }

   static enum TypeL {
      NONE,
      CONSUMED,
      CLOSE,
      LOAD,
      SAVE,
      REVERT,
      GET,
      EXPORT,
      LIKE,
      RENAME,
      DUPLICATE,
      DELETE,
      PHOTO,
      UNPHOTO,
      COPY_CODE,
      REPORT;

      private static ConfigLibraryRenderer3.TypeL[] $values() {
         return new ConfigLibraryRenderer3.TypeL[]{
            NONE, CONSUMED, CLOSE, LOAD, SAVE, REVERT, GET, EXPORT, LIKE, RENAME, DUPLICATE, DELETE, PHOTO, UNPHOTO, COPY_CODE, REPORT
         };
      }
   }
}
