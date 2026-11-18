// Sourced By DevFFNightmare
package dev.purxiy.onboarding;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.core.ImageFiles;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.modules.Category;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.persist.FCodec;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
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
import dev.purxiy.ui.clickgui.MenuScreen;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_11876;
import net.minecraft.class_11905;
import net.minecraft.class_11908;
import net.minecraft.class_11909;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;

public final class WelcomeScreen extends class_437 {
   private static final float COLUMN = 600.0F;
   private static final float NAV = 46.0F;
   
   private static TextureLoader mark;
   
   private static TextureLoader wordmark;
   
   private final class_437 parent;
   private AutoCloseable rendererLease;
   private final boolean photoOnly;
   private final List<OnboardingStep> steps = new ArrayList<>();
   
   private final LanguageStep language;
   private final DrawContext frame = new DrawContext();
   private final OnboardingProgress backdrop = new OnboardingProgress();
   private final Animator open = new Animator(0.0F, 150.0F, 24.0F);
   private final Animator progress = Animator.smooth(0.0F);
   private final Animator chrome = Animator.smooth(0.0F);
   private final Animator scale;
   private final Animator[] dots;
   private final StepNavigation back = new StepNavigation();
   private final StepNavigation next = new StepNavigation();
   private final StepNavigation skip = new StepNavigation();
   private int index;
   
   private OnboardingStep leaving;
   private int direction = 1;
   private float leftAt = -100.0F;
   private float languageAt = -100.0F;
   private boolean closing;
   
   private Runnable after;
   private boolean pointerDown;
   private boolean started;
   
   private Object lastHover;
   private float devMouseX = Float.NaN;
   private float devMouseY = Float.NaN;

   @Override
   protected void method_25426() {
      if (this.rendererLease == null) {
         this.rendererLease = ClientServices.acquire("welcome");
      }
   }

   public WelcomeScreen( class_437 var1) {
      this(var1, false);
   }

   private WelcomeScreen( class_437 var1, boolean var2) {
      super(class_2561.method_43470("Welcome to Purxiy"));
      this.parent = var1;
      this.photoOnly = var2;
      if (var2) {
         this.language = null;
         this.steps.add(new PhotoStep(this, true));
      } else {
         this.language = new LanguageStep(this);
         this.steps.add(new IntroStep(this));
         this.steps.add(this.language);
         this.steps.add(new NameStep(this));
         this.steps.add(new PhotoStep(this, false));
         this.steps.add(new InterfaceSizeStep(this));
         this.steps.add(new EssentialsStep(this));
         this.steps.add(new CompleteStep(this, var1 instanceof MenuScreen));
      }

      this.dots = new Animator[Math.max(0, this.steps.size() - 2)];

      for (int var3 = 0; var3 < this.dots.length; var3++) {
         this.dots[var3] = Animator.snappy(0.0F);
      }

      this.open.target(1.0F);
      this.scale = Animator.smooth(SettingSection.INTERFACE.scale.floatValue());
      if (mark == null) {
         mark = TextureLoader.load("textures/brand/mark.png");
         wordmark = TextureLoader.load("textures/brand/wordmark.png");
      }
   }

   public static WelcomeScreen photo( class_437 var0) {
      return new WelcomeScreen(var0, true);
   }

   private OnboardingStep current() {
      return this.steps.get(this.index);
   }

   @Override
   public boolean method_25421() {
      return true;
   }

   @Override
   public boolean method_25422() {
      return false;
   }

   @Override
   public void method_25420(class_332 var1, int var2, int var3, float var4) {
      if (this.field_22787.field_1687 == null) {
         this.method_57728(var1, var4);
      }
   }

   @Override
   public void method_25432() {
      FCodec.save();
      if (this.rendererLease != null) {
         try {
            this.rendererLease.close();
         } catch (Exception var2) {
         }

         this.rendererLease = null;
      }
   }

   @Override
   public void method_25394(class_332 var1, int var2, int var3, float var4) {
      RenderPipelines var5 = RenderPipelines.get();
      float var6 = var5.delta();
      var5.setUserScale(this.scale.target(SettingSection.INTERFACE.scale.floatValue()).update(MotionStyle.MENU.dt(var6)));
      Canvas var7 = var5.canvas();
      float var8 = var5.time();
      if (!this.started) {
         this.started = true;
         this.current().enter(var8 + 0.12F);
         SoundRequest.play(SoundEvent.OPEN);
      }

      float var9 = Float.isNaN(this.devMouseX) ? var5.mouseX() : this.devMouseX;
      float var10 = Float.isNaN(this.devMouseY) ? var5.mouseY() : this.devMouseY;
      DrawContext var11 = this.frame;
      var11.begin(var7, var8, var6, var9, var10, this.pointerDown);
      float var12 = clamp01(this.open.update(MotionStyle.MENU.dt(var6)));
      if (this.closing && var12 < 0.02F) {
         Runnable var18 = this.after;
         this.after = null;
         if (var18 != null) {
            var18.run();
         }
      } else {
         float var13 = this.photoOnly ? 0.6F : (float)this.index / (this.steps.size() - 1);
         float var14 = this.progress.target(var13).update(MotionStyle.MENU.dt(var6) * 0.45F);
         this.backdrop.render(var11, var12, var14);
         var7.captureBackdrop();
         var11.mask(this.closing);
         var7.pushAlpha(var12);
         this.topBar(var11, var7, var8);
         OnboardingStep var15 = this.current();
         if (this.leaving != null) {
            float var16 = clamp01((var8 - this.leftAt) * MotionStyle.MENU.speed() / 0.26F);
            if (var16 >= 1.0F) {
               this.leaving = null;
            } else {
               float var17 = Easing.CUBIC_OUT.apply(var16);
               var7.pushAlpha(1.0F - var17);
               var7.push();
               var7.translate(-this.direction * 44 * var17, 0.0F);
               var11.mask(true);
               this.drawStep(var11, this.leaving, false);
               var11.mask(this.closing);
               var7.pop();
               var7.popAlpha();
            }
         }

         float var19 = Easing.EMPHASIZED.apply(clamp01((var8 - var15.enteredAt) * MotionStyle.MENU.speed() / 0.55F));
         var7.push();
         var7.translate(this.leaving == null && !(var19 < 1.0F) ? 0.0F : this.direction * 44 * (1.0F - var19), 0.0F);
         this.drawStep(var11, var15, !this.closing && var19 > 0.35F);
         var7.pop();
         var7.popAlpha();
         Object var20 = var11.hoverTarget();
         if (var20 != null && var20 != this.lastHover) {
            SoundRequest.play(SoundEvent.HOVER);
         }

         this.lastHover = var20;
         switch (var11.cursor()) {
            case HAND:
               var1.method_74037(class_11876.field_62455);
               break;
            case TEXT:
               var1.method_74037(class_11876.field_62453);
               break;
            case MOVE:
               var1.method_74037(class_11876.field_62458);
         }
      }
   }

   private void topBar(DrawContext var1, Canvas var2, float var3) {
      float var4 = var2.width();
      float var5 = margin(var4);
      float var6 = 40.0F;
      float var7 = this.chrome.target(this.current().full() ? 0.0F : 1.0F).update(MotionStyle.MENU.dt(var1.dt));
      if (var7 > 0.01F) {
         var2.pushAlpha(var7);
         float var8 = 24.0F;
         float var9 = var8 * mark.aspect();
         var2.glowCircle(var5 + var9 / 2.0F, var6, var8 * 0.5F, var8 * 1.2F, BlendMode.withAlpha(Icons.MAGENTA, 0.16F));
         var2.logo(mark, var5, var6 - var8 / 2.0F, var9, var8, -1);
         float var10 = 11.0F;
         float var11 = var10 * wordmark.aspect();
         var2.image(wordmark, var5 + var9 + 12.0F, var6 - var10 / 2.0F, var11, var10, 0.0F, BlendMode.withAlpha(-1, 0.9F));
         if (this.dots.length > 0) {
            float var12 = 8.0F;
            float var13 = 0.0F;
            float[] var14 = new float[this.dots.length];

            for (int var15 = 0; var15 < this.dots.length; var15++) {
               var14[var15] = 6.0F + 18.0F * this.dots[var15].target(var15 + 1 == this.index ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
               var13 += var14[var15] + (var15 > 0 ? var12 : 0.0F);
            }

            float var24 = var4 / 2.0F - var13 / 2.0F;

            for (int var16 = 0; var16 < this.dots.length; var16++) {
               float var17 = this.dots[var16].value();
               boolean var18 = var16 + 1 < this.index;
               int var19 = var18 ? BlendMode.withAlpha(Theme.tint(-3561729), 0.75F) : 788529151;
               var2.roundRect(var24, var6 - 3.0F, var14[var16], 6.0F, 3.0F, var19);
               if (var17 > 0.01F) {
                  var2.pushAlpha(var17);
                  var2.glow(var24, var6 - 3.0F, var14[var16], 6.0F, 3.0F, 10.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.4F));
                  var2.roundRectGradient(var24, var6 - 3.0F, var14[var16], 6.0F, 3.0F, 0.0F, Icons.PURPLE, Icons.PINK);
                  var2.popAlpha();
               }

               var24 += var14[var16] + var12;
            }
         }

         var2.popAlpha();
      }

      boolean var20 = this.index == this.steps.size() - 1 && !this.photoOnly;
      if (!var20) {
         String var21 = this.photoOnly ? "Close" : "Skip setup";
         String var22 = this.photoOnly ? "x" : null;
         float var23 = StepNavigation.width(var2, var21, var22, (String)null, 34.0F);
         this.skip.render(var1, var4 - var5 - var23, var6, 34.0F, var21, var22, (String)null, StepNavigation.TypeS.GHOST, true, !this.closing);
      }
   }

   private void drawStep(DrawContext var1, OnboardingStep var2, boolean var3) {
      Canvas var4 = var1.canvas;
      float var5 = var4.width();
      float var6 = var4.height();
      float var7 = var1.time;
      if (var2.full()) {
         var2.render(var1, 0.0F, 0.0F, var5, var3);
      } else {
         float var8 = margin(var5);
         float var9 = Math.min(600.0F, var5 - var8 * 2.0F);
         float var10 = (var5 - var9) / 2.0F;
         float var11 = var5 / 2.0F;
         float var12 = Math.min(var9 + 100.0F, var5 - var8 * 2.0F);
         float var13 = FontMetrics.wrap(Fonts.REGULAR, var2.subtitle, 14.0F, var12, 0.0F).size() * 14 * 1.45F;
         float var14 = 76.0F + var13;
         float var15 = var2.height(var9);
         float var16 = var14 + 34.0F + var15 + 40.0F + 46.0F;
         float var17 = Math.max(84.0F, (var6 - var16) / 2.0F - 6.0F);
         var17 = Math.max(76.0F, Math.min(var17, var6 * 0.86F - var16));
         float var18 = 0.25F + 0.75F * Easing.SMOOTHSTEP.apply(clamp01((var7 - this.languageAt) / 0.4F));
         float var19 = var2.in(var7, 0);
         float var20 = var2.in(var7, 1);
         float var21 = var2.in(var7, 2);
         OnboardingState.eyebrow(var4, var2.eyebrow, var11, var17 + 6.0F + (1.0F - var19) * 8.0F, var19 * var18);
         var4.pushAlpha(var20 * var18);
         var4.text(Fonts.BOLD, var2.title(), var11, var17 + 42.0F + (1.0F - var20) * 10.0F, 30.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE, -0.012F);
         var4.popAlpha();
         var4.pushAlpha(var21 * var18);
         var4.paragraph(
            Fonts.REGULAR, var2.subtitle, var11 - var12 / 2.0F, var17 + 66.0F + (1.0F - var21) * 8.0F, 14.0F, var12, 1.45F, Icons.TEXT_2, FontStyle.CENTER
         );
         var4.popAlpha();
         float var22 = var17 + var14 + 34.0F;
         var2.render(var1, var10, var22, var9, var3);
         float var23 = var22 + var15 + 40.0F + 23.0F;
         float var24 = var2.in(var7, 6);
         var4.pushAlpha(var24);
         boolean var25 = var3 && var24 > 0.7F;
         if (this.index > 0 && !this.photoOnly) {
            this.back.render(var1, var10, var23, 46.0F, "Back", "arrow-left", (String)null, StepNavigation.TypeS.GHOST, true, var25);
         }

         String var26 = var2.nextLabel();
         float var27 = StepNavigation.width(var4, var26, (String)null, "arrow-right", 46.0F);
         float var28 = var10 + var9 - var27;
         this.next.render(var1, var28, var23, 46.0F, var26, (String)null, "arrow-right", StepNavigation.TypeS.PRIMARY, var2.ready(), var25);
         float var29 = Math.max(21.0F, var4.textWidth(Fonts.SEMIBOLD, "Enter", 10.0F) + 12.0F);
         var4.pushAlpha(0.8F);
         KeyLabel.keyCap(var4, "Enter", var28 - 14.0F - var29, var23, 10.0F, Icons.TEXT_3);
         var4.popAlpha();
         var4.popAlpha();
      }
   }

   private static float margin(float var0) {
      return Math.max(32.0F, Math.min(72.0F, var0 * 0.045F));
   }

   boolean next() {
      if (this.closing) {
         return false;
      } else {
         OnboardingStep var1 = this.current();
         if (!var1.ready()) {
            SoundRequest.play(SoundEvent.DENY);
            return false;
         } else if (this.photoOnly) {
            this.leave();
            return true;
         } else if (this.index >= this.steps.size() - 1) {
            this.finish(false);
            return true;
         } else {
            this.go(this.index + 1, 1);
            return true;
         }
      }
   }

   private void back() {
      if (this.photoOnly) {
         this.leave();
      } else if (this.index > 0) {
         this.go(this.index - 1, -1);
      }
   }

   private void go(int var1, int var2) {
      float var3 = RenderPipelines.get().time();
      OnboardingStep var4 = this.current();
      var4.leave();
      this.leaving = var4;
      this.leftAt = var3;
      this.direction = var2;
      this.index = Math.max(0, Math.min(this.steps.size() - 1, var1));
      this.current().enter(var3 + 0.08F);
      SoundRequest.play(var2 > 0 ? SoundEvent.SELECT : SoundEvent.TAB);
   }

   void languageChanged() {
      this.languageAt = RenderPipelines.get().time();
   }

   void finish(boolean var1) {
      if (!this.closing) {
         this.current().leave();
         OnboardingRequest.markDone();
         FCodec.save();
         String var2 = this.language != null ? this.language.gameLanguage() : null;
         class_310 var3 = this.field_22787;
         class_437 var4 = this.parent;
         this.close(() -> {
            if (var1 && !(var4 instanceof MenuScreen)) {
               FCodec.lastCategory = Category.PROFILE;
               var3.method_1507(new MenuScreen(var4));
            } else {
               var3.method_1507(var4);
            }

            if (var2 != null && var3.method_1526().method_4668(var2) != null) {
               var3.field_1690.field_1883 = var2;
               var3.method_1526().method_4667(var2);
               var3.field_1690.method_1640();
               var3.method_1521();
            }
         });
         SoundRequest.play(SoundEvent.CLOSE);
      }
   }

   private void leave() {
      ImageFiles.saveFrame();
      class_437 var1 = this.parent;
      class_310 var2 = this.field_22787;
      this.close(() -> var2.method_1507(var1));
      SoundRequest.play(SoundEvent.CLOSE);
   }

   private void close(Runnable var1) {
      this.closing = true;
      this.open.target(0.0F);
      this.after = var1;
   }

   private float toCanvas(double var1) {
      return RenderPipelines.get().fromGui(var1);
   }

   @Override
   public boolean method_25402(class_11909 var1, boolean var2) {
      return this.pointerPressed(this.toCanvas(var1.comp_4798()), this.toCanvas(var1.comp_4799()), var1.method_74245());
   }

   @Override
   public boolean method_25406(class_11909 var1) {
      return this.pointerReleased(this.toCanvas(var1.comp_4798()), this.toCanvas(var1.comp_4799()));
   }

   @Override
   public boolean method_25403(class_11909 var1, double var2, double var4) {
      if (!this.closing) {
         this.current().mouseDrag(this.toCanvas(var1.comp_4798()), this.toCanvas(var1.comp_4799()));
      }

      return true;
   }

   @Override
   public boolean method_25401(double var1, double var3, double var5, double var7) {
      if (!this.closing) {
         this.current().scroll(this.toCanvas(var1), this.toCanvas(var3), var7);
      }

      return true;
   }

   private boolean pointerPressed(float var1, float var2, int var3) {
      this.pointerDown = true;
      if (this.closing) {
         return true;
      } else {
         if (var3 == 0 && this.skip.hit(var1, var2)) {
            if (this.photoOnly) {
               this.leave();
            } else {
               this.finish(false);
            }
         } else if (var3 == 0 && this.back.hit(var1, var2)) {
            this.back();
         } else if (var3 == 0 && this.next.hit(var1, var2)) {
            this.next();
         } else {
            this.current().mouseDown(var1, var2, var3);
         }

         return true;
      }
   }

   private boolean pointerReleased(float var1, float var2) {
      this.pointerDown = false;
      this.current().mouseUp(var1, var2);
      return true;
   }

   @Override
   public boolean method_25404(class_11908 var1) {
      int var2 = var1.comp_4795();
      if (this.closing) {
         return true;
      } else {
         OnboardingStep var3 = this.current();
         if (var3.capturesKeys()) {
            var3.keyPressed(var2);
            return true;
         } else if (var3.keyPressed(var2)) {
            return true;
         } else {
            switch (var2) {
               case 256:
                  this.back();
                  break;
               case 257:
               case 335:
                  this.next();
            }

            return true;
         }
      }
   }

   @Override
   public boolean method_25400(class_11905 var1) {
      if (!this.closing) {
         this.current().charTyped(var1.comp_4793());
      }

      return true;
   }

   @Override
   public void method_29638(List<Path> var1) {
      if (ImageFiles.importFiles(var1)) {
         SoundRequest.play(SoundEvent.EXPAND);
      } else {
         SoundRequest.play(SoundEvent.DENY);
      }
   }

   public void devAction(String var1, String var2) {
      String[] var3 = var2.split(";");
      switch (var1) {
         case "step":
            int var6 = Integer.parseInt(var3[0]);
            if (var6 != this.index) {
               this.go(var6, Integer.signum(var6 - this.index));
            }
            break;
         case "next":
            this.next();
            break;
         case "back":
            this.back();
            break;
         case "lang":
            if (this.language != null) {
               this.language.dev(var3[0].startsWith("ru") ? 1 : 0, var3.length > 1 && Boolean.parseBoolean(var3[1]));
            }
            break;
         case "name":
            SettingSection.PROFILE.name.set(var2);
            break;
         case "move":
            this.devMouseX = Float.parseFloat(var3[0]);
            this.devMouseY = Float.parseFloat(var3[1]);
            break;
         case "click":
            float var7 = Float.parseFloat(var3[0]);
            float var8 = Float.parseFloat(var3[1]);
            this.devMouseX = var7;
            this.devMouseY = var8;
            this.pointerPressed(var7, var8, 0);
            this.pointerReleased(var7, var8);
            break;
         case "drag":
            float var9 = Float.parseFloat(var3[0]);
            float var10 = Float.parseFloat(var3[1]);
            float var11 = Float.parseFloat(var3[2]);
            float var12 = Float.parseFloat(var3[3]);
            this.pointerPressed(var9, var10, 0);

            for (int var13 = 1; var13 <= 8; var13++) {
               this.current().mouseDrag(var9 + (var11 - var9) * var13 / 8.0F, var10 + (var12 - var10) * var13 / 8.0F);
            }

            this.pointerReleased(var11, var12);
            break;
         case "scroll":
            this.current().scroll(Float.parseFloat(var3[0]), Float.parseFloat(var3[1]), Double.parseDouble(var3[2]));
            break;
         case "key":
            this.current().keyPressed(Integer.parseInt(var3[0]));
            break;
         case "import":
            ImageFiles.importFiles(List.of(Path.of(var2)));
            break;
         case "finish":
            this.finish(var2.equals("open"));
            break;
         default:
            ClientServices.LOGGER.warn("Unknown welcome dev action {}", var1);
      }
   }

   private static float clamp01(float var0) {
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }
}
