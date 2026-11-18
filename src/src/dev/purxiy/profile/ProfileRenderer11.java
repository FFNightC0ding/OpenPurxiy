// Sourced By DevFFNightmare
package dev.purxiy.profile;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.modules.Category;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import net.minecraft.class_310;
import net.minecraft.class_746;

public final class ProfileRenderer11 {
   private static final float TOP = 150.0F;
   static final float GAP = 16.0F;
   private static final int OVERVIEW = 0;
   private static final int FRIENDS = 1;
   private static int lastView = 0;
   private final ProfileRenderer5 hero = new ProfileRenderer5();
   private final ProfileRenderer8 tiles = new ProfileRenderer8();
   private final ProfileRenderer3 activity = new ProfileRenderer3();
   private final ProfileRenderer journey = new ProfileRenderer();
   private final ProfileRenderer2 playstyle = new ProfileRenderer2();
   private final ProfileRenderer7 sky = new ProfileRenderer7();
   private final ProfileRenderer4 records = new ProfileRenderer4();
   private final ProfileRenderer6 friends = new ProfileRenderer6();
   private final ProfileRenderer9.TypeH views = new ProfileRenderer9.TypeH(new String[]{"Overview", "Friends"}, new String[]{"chart-spline", "users-round"});
   private final Animator view = Animator.smooth(0.0F);
   private float friendsAt = -100.0F;
   private final Animator scroll = Animator.smooth(0.0F);
   private final Animator settingsHover = Animator.smooth(0.0F);
   private final Object settingsKey = new Object();
   private final Animator cardHover = Animator.smooth(0.0F);
   private final Object cardKey = new Object();
   private boolean capture;
   private float savedAt = -100.0F;
   private float flashAt = -100.0F;
   private boolean saveFailed;
   private float scrollTarget;
   private float maxScroll;
   private float enterTime = -100.0F;
   private float exitTime = -100.0F;
   private boolean shown;
   private int direction;
   
   private ProfileSupport6 snapshot;
   private float snapshotAt = -100.0F;
   private float columnX;
   private float columnY;
   private float columnW;
   private float columnH;
   private float settingsX;
   private float settingsY;
   private float settingsW;
   private float cardX;
   private float cardY;
   private float cardW;
   private float cardH;
   private float heroX;
   private float heroY;
   private float heroW;
   private float heroH;

   public void show(float var1, int var2, float var3) {
      if (!this.shown) {
         this.shown = true;
         this.direction = var2;
         this.enterTime = var1 + var3;
         this.scroll.snap(0.0F);
         this.scrollTarget = 0.0F;
         this.snapshot = null;
         this.hero.enter(this.enterTime);
         this.activity.enter(this.enterTime);
         this.playstyle.enter();
         this.views.select(lastView);
         this.view.snap(lastView);
         if (lastView == 1) {
            this.friendsAt = this.enterTime;
            this.friends.enter(this.enterTime);
         }
      }
   }

   public void hide(float var1, int var2) {
      if (this.shown) {
         this.shown = false;
         this.direction = var2;
         this.exitTime = var1;
         this.friends.leave();
      }
   }

   public boolean shown() {
      return this.shown;
   }

   public static String orbitLine() {
      ProfileLoader var0 = ProfileSupport3.lifetime(ProfileSupport.data());
      int var1 = ProfileSupport3.level(var0.play);
      return "Level " + var1 + "  ·  " + ProfileSupport2.duration(var0.play) + " played";
   }

   public float[] settingsOrigin() {
      return new float[]{this.heroX, this.heroY, this.heroW};
   }

   public void render(DrawContext var1, float var2, float var3, boolean var4) {
      float var5 = var1.time;
      float var6 = this.shown ? 0.0F : (var5 - this.exitTime) * MotionStyle.MENU.speed() / 0.18F;
      if ((this.shown || !(var6 >= 1.0F)) && !(var2 <= 0.01F)) {
         Canvas var7 = var1.canvas;
         float var8 = var4 ? smooth((var2 - 0.45F) / 0.4F) : smooth(var2 * 1.6F - 0.3F);
         float var9 = var8 * var3 * (this.shown ? 1.0F : 1.0F - Easing.CUBIC_OUT.apply(ProfileCurve.clamp01(var6)));
         if (!(var9 <= 0.005F)) {
            float var10 = (var5 - this.enterTime) * MotionStyle.CASCADE.speed();
            if (var5 - this.snapshotAt > 0.25F || this.snapshot == null) {
               this.snapshot = ProfileSupport6.take();
               this.snapshotAt = var5;
            }

            ProfileSupport6 var11 = this.snapshot;
            boolean var12 = this.shown && !var1.masked() && var2 > 0.95F && var3 > 0.95F;
            float var13 = var7.width();
            float var14 = var7.height();
            float var15 = Math.max(32.0F, Math.min(72.0F, var13 * 0.045F));
            float var16 = var13 - var15 * 2.0F;
            float var17 = var14 - 150.0F - 46.0F;
            float var18 = this.shown ? this.direction * 30 * (1.0F - Easing.EMPHASIZED.apply(ProfileCurve.clamp01(var10 / 0.5F))) : -this.direction * 26 * var6;
            var7.pushAlpha(var9);
            var7.push();
            var7.translate(var18, 0.0F);
            this.header(var1, var7, var11, var15, var13, smooth((var2 - 0.5F) / 0.5F), var5, var12);
            this.heroW = Math.max(270.0F, Math.min(350.0F, var16 * 0.28F));
            this.heroX = var15;
            this.heroY = 150.0F;
            this.heroH = var17;
            float var19 = Easing.EMPHASIZED.apply(ProfileCurve.clamp01(var10 / 0.55F));
            var7.push();
            var7.translate(0.0F, (1.0F - var19) * 18.0F);
            var7.pushAlpha(var19);
            this.hero.render(var1, var11, this.heroX, this.heroY, this.heroW, this.heroH, var19, var5, var12 && !this.capture);
            if (this.capture && var19 > 0.999F && var18 == 0.0F && var9 > 0.999F) {
               ProfileSupport4.capture(this.heroX, this.heroY, this.heroW, this.heroH, 18.0F, var7.pixelScale());
               this.capture = false;
               this.flashAt = var5;
            }

            float var20 = var5 > this.flashAt ? 1.0F - ProfileCurve.clamp01((var5 - this.flashAt) / 0.5F) : 0.0F;
            if (var20 > 0.0F) {
               var7.roundRect(this.heroX, this.heroY, this.heroW, this.heroH, 18.0F, BlendMode.withAlpha(-1, 0.35F * var20 * var20));
            }

            var7.popAlpha();
            var7.pop();
            this.columnX = var15 + this.heroW + 16.0F + 2.0F;
            this.columnW = var13 - var15 - this.columnX;
            this.columnY = 150.0F;
            this.columnH = var17;
            float var21 = this.view.target(this.views.selected()).update(MotionStyle.MENU.dt(var1.dt));
            if (var21 < 0.999F) {
               var7.pushAlpha(1.0F - var21);
               var7.push();
               var7.translate(-var21 * 40.0F, 0.0F);
               this.column(var1, var7, var11, var5, var10, var12 && var21 < 0.05F);
               var7.pop();
               var7.popAlpha();
            }

            if (var21 > 0.001F) {
               var7.pushAlpha(var21);
               var7.push();
               var7.translate((1.0F - var21) * 40.0F, 0.0F);
               float var22 = Math.min(var10, (var5 - this.friendsAt) * MotionStyle.CASCADE.speed());
               this.friends.render(var1, this.columnX, this.columnY, this.columnW, this.columnH, var22, var12 && var21 > 0.95F);
               var7.pop();
               var7.popAlpha();
            }

            var7.pop();
            if (this.maxScroll > 1.0F && var21 < 0.5F) {
               float var28 = this.scroll.value();
               float var23 = var17 - 20.0F;
               float var24 = var17 + this.maxScroll;
               float var25 = Math.max(36.0F, var23 * var17 / var24);
               float var26 = 160.0F + (var23 - var25) * (var28 / this.maxScroll);
               float var27 = this.columnX + this.columnW + Math.min(20.0F, var15 * 0.4F);
               var7.roundRect(var27 - 1.5F, var26, 3.0F, var25, 1.5F, BlendMode.withAlpha(-1, 0.18F * (1.0F - var21 * 2.0F)));
            }

            String var29 = this.views.selected() == 1
               ? "Type a name and press Enter  ·  Middle-click a player in game to add them  ·  Esc for the orbit"
               : (
                  SettingSection.PROFILE.photoPortrait()
                     ? "Click your photo to change it  ·  Scroll for more  ·  Esc for the orbit"
                     : "Drag your model to turn it  ·  Scroll for more  ·  Esc for the orbit"
               );
            var7.text(
               Fonts.MEDIUM,
               var29,
               var13 / 2.0F,
               var14 - 24.0F,
               10.5F,
               BlendMode.withAlpha(Icons.TEXT_3, smooth((var2 - 0.5F) / 0.5F)),
               FontStyle.CENTER,
               FontWeight.MIDDLE
            );
            var7.popAlpha();
         }
      }
   }

   private void column(DrawContext var1, Canvas var2, ProfileSupport6 var3, float var4, float var5, boolean var6) {
      float var7 = this.columnX;
      float var8 = this.columnW;
      boolean var9 = var8 < 700.0F;
      float var10 = var9 ? var8 : (var8 - 16.0F) * 0.58F;
      float var11 = var9 ? var8 : var8 - 16.0F - var10;
      float var12 = 286.0F;
      float var13 = 244.0F;
      float var14 = 448.0F + (var9 ? var12 * 2.0F + 16.0F : var12) + 16.0F + (var9 ? var13 * 2.0F + 16.0F : var13) + 6.0F;
      this.maxScroll = Math.max(0.0F, var14 - this.columnH);
      this.scrollTarget = Math.max(0.0F, Math.min(this.maxScroll, this.scrollTarget));
      float var15 = this.scroll.target(this.scrollTarget).update(MotionStyle.MENU.dt(var1.dt));
      float var16 = this.columnY;
      float var17 = this.columnY + this.columnH;
      var2.pushClip(var7 - 30.0F, var16 - 14.0F, var8 + 60.0F, this.columnH + 28.0F);
      float var18 = var16 - var15;
      if (this.visible(var18, 124.0F, var16, var17)) {
         var2.pushAlpha(this.fade(var18, 124.0F, var16, var17));
         this.tiles.render(var1, var3, var7, var18, var8, var5, var6 && this.inside(var1, var18, 124.0F, var16, var17));
         var2.popAlpha();
      }

      var18 += 140.0F;
      if (this.begin(var2, var18, 292.0F, var16, var17, var5, 0.16F)) {
         this.activity.render(var1, var3, var7, var18, var8, var4, var6 && this.inside(var1, var18, 292.0F, var16, var17));
         this.end(var2);
      }

      var18 += 308.0F;
      float var19 = var9 ? var7 : var7 + var10 + 16.0F;
      float var20 = var9 ? var18 + var12 + 16.0F : var18;
      if (this.begin(var2, var18, var12, var16, var17, var5, 0.24F)) {
         this.journey.render(var1, var3, var7, var18, var10, var12, var4, var5 - 0.24F, var6 && this.inside(var1, var18, var12, var16, var17));
         this.end(var2);
      }

      if (this.begin(var2, var20, var12, var16, var17, var5, 0.3F)) {
         this.playstyle.render(var1, var3, var19, var20, var11, var12, var4, var5 - 0.3F, var6 && this.inside(var1, var20, var12, var16, var17));
         this.end(var2);
      }

      var18 = (var9 ? var20 : var18) + var12 + 16.0F;
      float var21 = var9 ? var7 : var7 + var10 + 16.0F;
      float var22 = var9 ? var18 + var13 + 16.0F : var18;
      if (this.begin(var2, var18, var13, var16, var17, var5, 0.36F)) {
         this.sky.render(var1, var3, var7, var18, var10, var13, var4, var5 - 0.36F, var6 && this.inside(var1, var18, var13, var16, var17));
         this.end(var2);
      }

      if (this.begin(var2, var22, var13, var16, var17, var5, 0.42F)) {
         this.records.render(var1, var3, var21, var22, var11, var13, var5 - 0.42F, var6 && this.inside(var1, var22, var13, var16, var17));
         this.end(var2);
      }

      var2.popClip();
   }

   private boolean begin(Canvas var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      if (!this.visible(var2, var3, var4, var5)) {
         return false;
      } else {
         float var8 = Easing.EMPHASIZED.apply(ProfileCurve.clamp01((var6 - var7) / 0.55F));
         if (var8 <= 0.005F) {
            return false;
         } else {
            var1.push();
            var1.translate(0.0F, (1.0F - var8) * 18.0F);
            var1.pushAlpha(var8 * this.fade(var2, var3, var4, var5));
            return true;
         }
      }
   }

   private void end(Canvas var1) {
      var1.popAlpha();
      var1.pop();
   }

   private boolean visible(float var1, float var2, float var3, float var4) {
      return var1 + var2 > var3 - 14.0F && var1 < var4 + 14.0F;
   }

   private float fade(float var1, float var2, float var3, float var4) {
      float var5 = Math.min(var1 + var2 - var3 + 14.0F, var4 - var1);
      return smooth(var5 / 90.0F);
   }

   private boolean inside(DrawContext var1, float var2, float var3, float var4, float var5) {
      return var1.mouseY >= Math.max(var2, var4 - 6.0F) && var1.mouseY <= Math.min(var2 + var3, var5 + 6.0F);
   }

   private void header(DrawContext var1, Canvas var2, ProfileSupport6 var3, float var4, float var5, float var6, float var7, boolean var8) {
      if (!(var6 <= 0.01F)) {
         float var9 = (1.0F - var6) * 10.0F;
         var2.pushAlpha(var6);
         var2.text(Fonts.BOLD, Category.PROFILE.displayName, var4, 82.0F + var9, 28.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.TOP, -0.01F);
         var2.text(Fonts.REGULAR, Category.PROFILE.tagline, var4, 118.0F + var9, 13.0F, Icons.TEXT_2);
         float var10 = 30.0F;
         float var11 = 104.0F + var9;
         this.settingsX = var5 - var4 - var10;
         this.settingsY = var11 - var10 / 2.0F;
         this.settingsW = var10;
         boolean var12 = var8 && var1.hover(this.settingsKey, this.settingsX, this.settingsY, var10, var10, DrawContext.TypeE.HAND);
         float var13 = this.settingsHover.target(var12 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         var2.circle(this.settingsX + var10 / 2.0F, var11, var10 / 2.0F, BlendMode.withAlpha(-1, 0.05F + 0.08F * var13));
         var2.roundRectOutline(this.settingsX, this.settingsY, var10, var10, var10 / 2.0F, 1.0F, BlendMode.lerp(352321535, 822083583, var13));
         var2.push();
         var2.rotateAround(var13 * 0.6F, this.settingsX + var10 / 2.0F, var11);
         var2.icon("settings", this.settingsX + var10 / 2.0F, var11, 14.0F, BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var13));
         var2.pop();
         if (ProfileSupport4.takeSaved() != null) {
            this.savedAt = var7;
            this.saveFailed = false;
         }

         if (ProfileSupport4.takeFailed()) {
            this.savedAt = var7;
            this.saveFailed = true;
         }

         boolean var14 = var7 - this.savedAt < 2.4F;
         String var15 = var14 ? (this.saveFailed ? "Could not save" : "Saved to screenshots") : "Save card";
         String var16 = var14 ? (this.saveFailed ? "circle-alert" : "check") : "image-down";
         this.cardW = var2.textWidth(Fonts.MEDIUM, var15, 11.5F) + 44.0F;
         this.cardH = 30.0F;
         this.cardX = this.settingsX - 10.0F - this.cardW;
         this.cardY = var11 - this.cardH / 2.0F;
         boolean var17 = var8 && var1.hover(this.cardKey, this.cardX, this.cardY, this.cardW, this.cardH, DrawContext.TypeE.HAND);
         float var18 = this.cardHover.target(var17 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         if (var18 > 0.01F || var14) {
            var2.glow(
               this.cardX,
               this.cardY,
               this.cardW,
               this.cardH,
               this.cardH / 2.0F,
               14.0F,
               BlendMode.withAlpha(Icons.VIOLET, 0.3F * Math.max(var18, var14 ? 1.0F : 0.0F))
            );
         }

         var2.roundRectGradient(
            this.cardX,
            this.cardY,
            this.cardW,
            this.cardH,
            this.cardH / 2.0F,
            0.0F,
            BlendMode.withAlpha(Icons.INDIGO, 0.7F + 0.3F * var18),
            BlendMode.withAlpha(Icons.PURPLE, 0.7F + 0.3F * var18)
         );
         var2.roundRectOutline(this.cardX, this.cardY, this.cardW, this.cardH, this.cardH / 2.0F, 1.0F, 872415231);
         var2.icon(var16, this.cardX + 18.0F, var11, 13.0F, Icons.TEXT);
         var2.text(Fonts.MEDIUM, var15, this.cardX + 32.0F, var11, 11.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
         float var19 = this.cardX - 10.0F;
         ProfileLoader2.TypeW var20 = var3.live;
         boolean var21 = var20 != null;
         String var22;
         if (var21) {
            String var30 = FontMetrics.ellipsize(Fonts.MEDIUM, var20.place, 11.5F, 200.0F, 0.0F);
            var22 = var30 + "  ·  " + ProfileSupport2.duration(var20.totals.play);
         } else {
            long var23 = 0L;

            for (ProfileLoader2.TypeD var26 : var3.data.places.values()) {
               var23 = Math.max(var23, var26.last);
            }

            var22 = var23 == 0L
               ? "Not played with Purxiy yet"
               : "Last played " + ProfileSupport2.date(LocalDate.ofInstant(Instant.ofEpochMilli(var23), ZoneId.systemDefault()));
         }

         float var31 = var2.textWidth(Fonts.MEDIUM, var22, 11.5F);
         float var24 = var31 + (var21 ? 34 : 22);
         float var32 = 30.0F;
         float var33 = var19 - var24;
         float var27 = var4
            + Math.max(var2.textWidth(Fonts.BOLD, Category.PROFILE.displayName, 28.0F), var2.textWidth(Fonts.REGULAR, Category.PROFILE.tagline, 13.0F))
            + 28.0F;
         boolean var28 = var33 - 12.0F - this.views.width() >= var27;
         this.views.render(var1, var28 ? var33 - 12.0F : var19, var11, var8);
         if (!var28) {
            var2.popAlpha();
         } else {
            var2.roundRect(var33, var11 - var32 / 2.0F, var24, var32, var32 / 2.0F, 285212671);
            var2.roundRectOutline(var33, var11 - var32 / 2.0F, var24, var32, var32 / 2.0F, 1.0F, 352321535);
            if (var21) {
               float var29 = MotionStyle.reduced() ? 0.8F : 0.55F + 0.45F * (float)Math.sin(var7 * 3.2F);
               var2.glowCircle(var33 + 15.0F, var11, 2.8F, 7.0F, BlendMode.withAlpha(Icons.PINK, 0.5F * var29));
               var2.circle(var33 + 15.0F, var11, 2.8F, BlendMode.lerp(Icons.MAGENTA, Icons.PINK, var29));
            }

            var2.text(Fonts.MEDIUM, var22, var19 - 11.0F, var11, 11.5F, Icons.TEXT_2, FontStyle.RIGHT, FontWeight.MIDDLE);
            var2.popAlpha();
         }
      }
   }

   public ProfileRenderer11.TypeK mouseDown(float var1, float var2, int var3, float var4) {
      if (!this.shown) {
         return ProfileRenderer11.TypeK.NONE;
      } else if (var1 >= this.settingsX && var1 <= this.settingsX + this.settingsW && var2 >= this.settingsY && var2 <= this.settingsY + this.settingsW) {
         return ProfileRenderer11.TypeK.SETTINGS;
      } else if (var1 >= this.cardX && var1 <= this.cardX + this.cardW && var2 >= this.cardY && var2 <= this.cardY + this.cardH) {
         this.capture = true;
         SoundRequest.play(SoundEvent.CLICK);
         return ProfileRenderer11.TypeK.CONSUMED;
      } else if (this.views.mouseDown(var1, var2)) {
         this.switchView(var4);
         return ProfileRenderer11.TypeK.CONSUMED;
      } else {
         boolean var5 = this.views.selected() == 1;
         if (var5 && this.friends.mouseDown(var1, var2, var3)) {
            return ProfileRenderer11.TypeK.CONSUMED;
         } else if (var3 == 0 && this.hero.mouseDown(var1, var2, var4)) {
            return ProfileRenderer11.TypeK.CONSUMED;
         } else if (var1 >= this.columnX && var1 <= this.columnX + this.columnW && var2 >= this.columnY && var2 <= this.columnY + this.columnH) {
            return !var5 && !this.activity.mouseDown(var1, var2) ? ProfileRenderer11.TypeK.NONE : ProfileRenderer11.TypeK.CONSUMED;
         } else {
            return ProfileRenderer11.TypeK.NONE;
         }
      }
   }

   private void switchView(float var1) {
      lastView = this.views.selected();
      if (lastView == 1) {
         this.friendsAt = var1;
         this.friends.enter(var1);
      } else {
         this.friends.leave();
      }
   }

   public void mouseDrag(float var1, float var2) {
      this.hero.mouseDrag(var1);
      this.friends.mouseDrag(var1, var2);
   }

   public void mouseUp(float var1, float var2, float var3) {
      this.hero.mouseUp(var3);
      this.friends.mouseUp();
   }

   public boolean keyPressed(int var1, boolean var2, boolean var3, float var4) {
      return this.shown && this.views.selected() == 1 && this.friends.keyPressed(var1, var2);
   }

   public boolean charTyped(int var1, float var2) {
      return this.shown && this.views.selected() == 1 && this.friends.charTyped(var1);
   }

   public boolean scroll(double var1) {
      if (this.shown && this.views.selected() == 1) {
         return this.friends.scroll(var1);
      } else if (this.shown && !(this.maxScroll <= 0.0F)) {
         this.scrollTarget = (float)Math.max(0.0, Math.min((double)this.maxScroll, this.scrollTarget - var1 * 64.0));
         return true;
      } else {
         return false;
      }
   }

   public void dev(String var1, String var2) {
      switch (var1) {
         case "scroll":
            this.scrollTarget = Float.parseFloat(var2);
            this.scroll.snap(Math.max(0.0F, Math.min(this.maxScroll, this.scrollTarget)));
            break;
         case "view":
            this.views.select(var2.equals("friends") ? 1 : 0);
            this.switchView(RenderPipelines.get().time());
            break;
         case "friends":
            String[] var5 = var2.split(";", 2);
            this.friends.dev(var5[0], var5.length > 1 ? var5[1] : "");
            break;
         case "metric":
            this.activity.select(Integer.parseInt(var2), -1);
            break;
         case "range":
            this.activity.select(-1, Integer.parseInt(var2));
            break;
         case "dump":
            ProfileLoader2 var6 = ProfileSupport.data();
            ProfileLoader var7 = var6.days.getOrDefault(ProfileSupport.today(), new ProfileLoader());
            ProfileCodec2 var8 = ProfileSupport.journey();
            ClientServices.LOGGER
               .info(
                  "Profile today: play {}s, distance {} (foot {}, sprint {}, fly {}), broken {}, placed {}, hits {}, kills {}+{}, deaths {}, chat {}; sessions {}, places {}, top speed {}, route {} points / {} blocks / {} marks, imported {} ({} worlds)",
                  new Object[]{
                     Math.round(var7.play),
                     Math.round(var7.distance()),
                     Math.round(var7.foot),
                     Math.round(var7.sprint),
                     Math.round(var7.fly),
                     var7.broken,
                     var7.placed,
                     var7.hits,
                     var7.kills,
                     var7.mobKills,
                     var7.deaths,
                     var7.chat,
                     var6.sessions.size(),
                     var6.places.size(),
                     Math.round(var6.topSpeed * 10.0) / 10.0,
                     var8 == null ? 0 : var8.points(),
                     var8 == null ? 0L : Math.round(var8.length()),
                     var8 == null ? 0 : var8.marks.size(),
                     var6.imported,
                     var6.importedWorlds
                  }
               );
            class_746 var9 = class_310.method_1551().field_1724;
            if (var9 != null) {
               ClientServices.LOGGER
                  .info(
                     "Profile: you are at {} (flying {}, on ground {})", new Object[]{var9.method_73189(), var9.method_31549().field_7479, var9.method_24828()}
                  );
            }
            break;
         case "stats":
            RenderPipelines.TypeM var10 = RenderPipelines.get().stats();
            ClientServices.LOGGER
               .info(
                  "Profile page: {} quads, {} batches, {} passes, {} ms on the CPU",
                  new Object[]{var10.quads, var10.batches, var10.passes, String.format(Locale.ROOT, "%.2f", var10.cpuMillis)}
               );
      }
   }

   private static float smooth(float var0) {
      var0 = var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   public static enum TypeK {
      NONE,
      CONSUMED,
      SETTINGS;

      private static ProfileRenderer11.TypeK[] $values() {
         return new ProfileRenderer11.TypeK[]{NONE, CONSUMED, SETTINGS};
      }
   }
}
