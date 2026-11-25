package dev.purxiy.ui.clickgui;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.modules.Category;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.profile.ProfilePanel;
import dev.purxiy.profile.ProfileSupport;
import dev.purxiy.profile.ProfileSkin;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.texture.TextureLoader;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.config.ConfigLibraryPanel;
import java.util.Arrays;

final class StudioPanel {
   private static final Category[] CATS = SettingSection.shownCategories().toArray(new Category[0]);
   private static final int N = CATS.length;
   private static final float TAB_SIZE = 34.0F;
   private static final float TAB_GAP = 8.0F;
   private static final float TILT = -0.16F;
   private static final float STAGGER = 0.035F;
   private final Animator pos;
   private final Animator[] hover = new Animator[N];
   private final Animator[] tabActive = new Animator[N];
   private final Animator homeHover = Animator.smooth(0.0F);
   private final Object[] keys = new Object[N];
   private final Object homeKey = new Object();
   private final TextureLoader mark;
   private final TextureLoader wordmark;
   private int lastFront;
   private boolean dragging;
   private boolean dragMoved;
   private float dragStartX;
   private float dragStartPos;
   private float dragLastX;
   private long dragLastTime;
   private float dragVelocity;
   private final float[] gx = new float[N];
   private final float[] gy = new float[N];
   private final float[] gw = new float[N];
   private final float[] gh = new float[N];
   private final float[] gDepth = new float[N];
   private float homeX;
   private float homeY;
   private float homeW;
   private float homeH;
   private float spacing = 200.0F;
   private float view;
   private Category hitCategory;

   StudioPanel(Category var1, TextureLoader var2, TextureLoader var3) {
      this.mark = var2;
      this.wordmark = var3;
      int var4 = index(var1);
      this.pos = new Animator(var4 - 0.8F, 150.0F, 21.0F);
      this.pos.target(var4);
      this.lastFront = var4;

      for (int var5 = 0; var5 < N; var5++) {
         this.hover[var5] = Animator.smooth(0.0F);
         this.tabActive[var5] = Animator.snappy(0.0F);
         this.keys[var5] = new Object();
      }
   }

   Category front() {
      return CATS[Math.floorMod(Math.round(this.pos.target()), N)];
   }

   private static int index(Category var0) {
      for (int var1 = 0; var1 < N; var1++) {
         if (CATS[var1] == var0) {
            return var1;
         }
      }

      return 0;
   }

   static Category next(Category var0, int var1) {
      return CATS[Math.floorMod(index(var0) + var1, N)];
   }

   void rotate(int var1) {
      this.pos.target(Math.round(this.pos.target()) + var1);
   }

   void focus(Category var1) {
      int var2 = Math.round(this.pos.target());
      int var3 = Math.floorMod(index(var1) - Math.floorMod(var2, N), N);
      if (var3 > N / 2) {
         var3 -= N;
      }

      this.pos.target(var2 + var3);
   }

   boolean dragging() {
      return this.dragging;
   }

   void snapTo(Category var1) {
      this.pos.snap(index(var1));
      this.lastFront = index(var1);
   }

   boolean settled() {
      return !this.dragging && Math.abs(this.pos.value() - this.pos.target()) < 0.12F;
   }

   void render(DrawContext var1, float var2, float var3, Category var4) {
      this.view = var3;
      Canvas var5 = var1.canvas;
      float var6 = var5.width();
      float var7 = var5.height();
      float var8 = var1.time;
      boolean var9 = MotionStyle.reduced();
      float var10 = this.dragging ? this.pos.value() : this.pos.update(MotionStyle.ORBIT.dt(var1.dt));
      int var11 = Math.floorMod(Math.round(var10), N);
      if (var11 != this.lastFront) {
         this.lastFront = var11;
         if (var3 < 0.5F && var2 > 0.9F) {
            SoundRequest.play(SoundEvent.ORBIT_TICK);
         }
      }

      float var12 = var6 / 2.0F;
      float var13 = var7 * 0.44F;
      float var14 = Math.min(var6 * 0.31F, var7 * 0.62F);
      float var15 = var14 * 0.3F;
      float var16 = 0.84F + 0.16F * var2;
      this.spacing = var14 * (float)Math.sin((Math.PI * 2) / N);
      float var17 = (float)Math.cos(-0.16F);
      float var18 = (float)Math.sin(-0.16F);
      float var19 = 40.0F;
      float[] var20 = new float[N];
      float var21 = 8.0F * (N - 1);

      for (int var22 = 0; var22 < N; var22++) {
         float var23 = this.tabActive[var22].target(CATS[var22] == var4 && var3 > 0.5F ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
         float var24 = Fonts.SEMIBOLD.width(CATS[var22].displayName, 13.0F);
         var20[var22] = 34.0F + (var24 + 26.0F) * var23;
         var21 += var20[var22];
      }

      float var40 = var6 / 2.0F - var21 / 2.0F;
      int var41 = index(var4);
      Integer[] var42 = new Integer[N];
      float[] var25 = new float[N];
      float[] var26 = new float[N];
      float[] var27 = new float[N];
      float[] var28 = new float[N];
      float[] var29 = new float[N];
      float[] var30 = new float[N];
      float[] var31 = new float[N];

      for (int var32 = 0; var32 < N; var32++) {
         var42[var32] = var32;
         float var33 = wrap(var32 - var10);
         double var34 = (Math.PI / 2) + var33 * 2.0F * Math.PI / N;
         float var36 = (float)Math.cos(var34) * var14 * var16;
         float var37 = (float)Math.sin(var34) * var15 * var16;
         var27[var32] = var12 + var36 * var17 - var37 * var18;
         var28[var32] = var13 + var36 * var18 + var37 * var17;
         var30[var32] = (float)(Math.sin(var34) + 1.0) / 2.0F;
         var25[var32] = Math.max(0.0F, 1.0F - Math.abs(var33) / 0.55F);
         var29[var32] = 31.0F * (0.56F + 0.44F * var30[var32]) + var25[var32] * 3.0F + this.hover[var32].value() * 2.5F;
         var31[var32] = var40 + var20[var32] / 2.0F;
         var40 += var20[var32] + 8.0F;
         float var38 = smooth(var2 * 1.25F - Math.abs(var33) * 0.06F);
         var26[var32] = (0.3F + 0.7F * var30[var32] * var30[var32]) * var38;
         boolean var39 = var3 > 0.5F;
         this.gx[var32] = var39 ? var31[var32] : var27[var32];
         this.gy[var32] = var39 ? var19 : var28[var32];
         this.gw[var32] = var39 ? var20[var32] : var29[var32] * 2.0F;
         this.gh[var32] = var39 ? 34.0F : var29[var32] * 2.0F;
         this.gDepth[var32] = var39 ? 1.0F : var30[var32];
      }

      Arrays.sort(var42, (var1x, var2x) -> Float.compare(var30[var1x], var30[var2x]));
      int var43 = -1;
      if (var3 < 0.05F || var3 > 0.95F) {
         for (int var44 = N - 1; var44 >= 0; var44--) {
            int var47 = var42[var44];
            if ((var3 > 0.5F || var26[var47] > 0.2F) && this.hitPlanet(var47, var1.mouseX, var1.mouseY) && !var1.masked()) {
               var43 = var47;
               break;
            }
         }
      }

      if (var43 >= 0 && !this.dragging) {
         var1.claim(this.keys[var43], DrawContext.Cursor.HAND);
      }

      for (int var45 = 0; var45 < N; var45++) {
         this.hover[var45].target(var45 == var43 && !this.dragging ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      }

      float var46 = smooth(var3);
      float var48 = smooth(var3 / 0.55F);
      float var35 = (1.0F - var46) * (1.0F - var46) * var2;
      this.ring(var5, var12, var13, var14 * var16, var15 * var16, var35, false);

      for (int var49 = 0; var49 < N; var49++) {
         int var54 = var42[var49];
         if (var30[var54] < 0.5F && (var54 != var41 || var3 <= 0.001F)) {
            this.orbitPlanet(
               var5, var54, var27[var54], var28[var54], var29[var54], var25[var54], var26[var54] * (1.0F - var48), var48, var12, var13, var30[var54]
            );
         }
      }

      this.core(var1, var5, var12, var13, var46, var2, var8, var9, var19);
      this.ring(var5, var12, var13, var14 * var16, var15 * var16, var35, true);

      for (int var50 = 0; var50 < N; var50++) {
         int var55 = var42[var50];
         if (var30[var55] >= 0.5F && (var55 != var41 || var3 <= 0.001F)) {
            this.orbitPlanet(
               var5, var55, var27[var55], var28[var55], var29[var55], var25[var55], var26[var55] * (1.0F - var48), var48, var12, var13, var30[var55]
            );
         }
      }

      if (var3 > 0.001F) {
         for (int var51 = 0; var51 < N; var51++) {
            if (var51 != var41) {
               float var56 = smooth((var3 - 0.3F - Math.abs(var51 - var41) * 0.045F) / 0.45F);
               if (var56 > 0.005F) {
                  float var58 = 0.6F + 0.4F * var56;
                  this.planet(var5, var51, var31[var51], var19 - (1.0F - var56) * 8.0F, var20[var51] * var58, 34.0F * var58, 0.0F, var2 * var56, 1.0F);
               }
            }
         }

         float var52 = lerp(var27[var41], var31[var41], var46);
         float var57 = lerp(var28[var41], var19, var46);
         this.planet(
            var5,
            var41,
            var52,
            var57,
            lerp(var29[var41] * 2.0F, var20[var41], var46),
            lerp(var29[var41] * 2.0F, 34.0F, var46),
            lerp(var25[var41], 0.0F, var46),
            lerp(var26[var41], var2, var46),
            var46
         );
      }

      float var53 = (1.0F - smooth(var3 / 0.4F)) * var2;
      if (var53 > 0.01F) {
         this.info(var5, var12, var13 + var15 * var16 + 64.0F, var10, var53);
         var5.text(
            Fonts.MEDIUM,
            "Scroll or drag to turn  ·  Enter to open  ·  Type to search",
            var12,
            var7 - 26.0F,
            10.5F,
            BlendMode.withAlpha(Icons.TEXT_3, var53),
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
      }
   }

   void renderTooltips(DrawContext var1, float var2, Category var3) {
      if (!(this.view < 0.9F)) {
         Canvas var4 = var1.canvas;

         for (int var5 = 0; var5 < N; var5++) {
            float var6 = this.hover[var5].value();
            if (CATS[var5] != var3 && !(var6 < 0.02F)) {
               String var7 = CATS[var5].displayName;
               float var8 = Fonts.MEDIUM.width(var7, 11.5F) + 20.0F;
               float var9 = 24.0F;
               float var10 = this.gx[var5] - var8 / 2.0F;
               float var11 = this.gy[var5] + 17.0F + 9.0F - (1.0F - var6) * 4.0F;
               var4.pushAlpha(Math.min(1.0F, var6) * var2);
               var4.shadow(var10, var11, var8, var9, 8.0F, 14.0F, 0.0F, 0.0F, 4.0F, 1711276032);
               var4.roundRect(var10, var11, var8, var9, 8.0F, Theme.tint(-233434583));
               var4.roundRectOutline(var10, var11, var8, var9, 8.0F, 1.0F, 536870911);
               var4.text(Fonts.MEDIUM, var7, this.gx[var5], var11 + var9 / 2.0F, 11.5F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
               var4.popAlpha();
            }
         }
      }
   }

   private void ring(Canvas var1, float var2, float var3, float var4, float var5, float var6, boolean var7) {
      if (!(var6 <= 0.01F)) {
         var1.push();
         var1.translate(var2, var3);
         var1.rotate(-0.16F);
         var1.scale(1.0F, var5 / var4);
         int var8 = BlendMode.withAlpha(Icons.PURPLE, (var7 ? 0.75F : 0.22F) * var6);
         int var9 = BlendMode.withAlpha(Icons.INDIGO, (var7 ? 0.45F : 0.14F) * var6);
         if (var7) {
            var1.arc(0.0F, 0.0F, var4, 2.2F, 0.0F, 180.0F, var8, var9, false);
            var1.arc(0.0F, 0.0F, var4, 9.0F, 20.0F, 140.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.07F * var6), BlendMode.withAlpha(Icons.VIOLET, 0.0F), false);
         } else {
            var1.arc(0.0F, 0.0F, var4, 2.0F, 180.0F, 180.0F, var9, var8, false);
         }

         var1.pop();
      }
   }

   private void orbitPlanet(Canvas var1, int var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, float var10, float var11) {
      if (!(var7 <= 0.01F)) {
         float var12 = var3 + (var9 - var3) * 0.18F * var8;
         float var13 = var4 + (40.0F - var4) * 0.12F * var8;
         float var14 = 1.0F - 0.3F * var8;
         this.planet(var1, var2, var12, var13, var5 * 2.0F * var14, var5 * 2.0F * var14, var6, var7, 0.0F);
         float var15 = (1.0F - var6) * Math.max(0.0F, var11 * 2.0F - 1.0F) * (1.0F - var8);
         if (var15 > 0.01F) {
            var1.text(
               Fonts.SEMIBOLD,
               CATS[var2].displayName,
               var12,
               var13 + var5 * var14 + 14.0F,
               11.0F,
               BlendMode.withAlpha(Icons.TEXT_2, var7 * var15 * (0.55F + 0.45F * this.hover[var2].value())),
               FontStyle.CENTER,
               FontWeight.MIDDLE
            );
         }
      }
   }

   private void planet(Canvas var1, int var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9) {
      if (!(var8 <= 0.01F)) {
         Category var10 = CATS[var2];
         float var11 = var6 / 2.0F;
         float var12 = this.hover[var2].value();
         float var13 = this.tabActive[var2].value();
         float var14 = lerp(var7, var13, var9);
         var1.pushAlpha(var8);
         if (var14 > 0.01F) {
            var1.glow(var3 - var5 / 2.0F, var4 - var6 / 2.0F, var5, var6, var11, lerp(34.0F, 16.0F, var9), BlendMode.withAlpha(Icons.MAGENTA, 0.38F * var14));
         }

         if (var9 < 0.999F) {
            var1.pushAlpha(1.0F - var9);
            var1.circleRadial(var3 - var5 / 2.0F + var11, var4, var11, Theme.tint(-13884592), Theme.tint(-15593946));
            var1.popAlpha();
         }

         if (var9 > 0.001F) {
            var1.pushAlpha(var9);
            var1.roundRect(var3 - var5 / 2.0F, var4 - var6 / 2.0F, var5, var6, var11, BlendMode.lerp(318767103, 520093695, var12));
            var1.popAlpha();
         }

         if (var14 > 0.01F) {
            var1.pushAlpha(var14);
            var1.roundRectGradient(var3 - var5 / 2.0F, var4 - var6 / 2.0F, var5, var6, var11, 135.0F, Icons.INDIGO, Icons.MAGENTA);
            var1.popAlpha();
         }

         var1.roundRectCorners(var3 - var5 / 2.0F, var4 - var6 / 2.0F, var5, var6, var11, 620756991, 285212671, 16777215, 83886079);
         var1.roundRectOutline(
            var3 - var5 / 2.0F, var4 - var6 / 2.0F, var5, var6, var11, 1.0F, BlendMode.lerp(486539263, 1308622847, Math.max(var12, var14 * 0.6F))
         );
         float var15 = lerp(var6 * 0.4F, 16.0F, var9);
         float var16 = var3 + (-var5 / 2.0F + 17.0F) * var13 * var9;
         int var17 = BlendMode.lerp(BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var12), Icons.TEXT, var14);
         float var18 = lerp(var6 * 0.7F, 20.0F, var9);
         if (var10 == Category.PROFILE && ProfileSkin.face(var1, var16 - var18 / 2.0F, var4 - var18 / 2.0F, var18, var18 / 2.0F, -1)) {
            var1.ring(var16, var4, var18 / 2.0F, 1.2F, BlendMode.withAlpha(-1, 0.2F + 0.3F * Math.max(var12, var14)));
         } else {
            var1.icon(var10.icon, var16, var4, var15, var17);
         }

         if (var13 * var9 > 0.01F) {
            var1.pushClip(var3 - var5 / 2.0F, var4 - var6 / 2.0F, var5, var6, var11);
            var1.text(
               Fonts.SEMIBOLD,
               var10.displayName,
               var3 - var5 / 2.0F + 34.0F - 2.0F,
               var4,
               13.0F,
               BlendMode.withAlpha(Icons.TEXT, var13 * var9),
               FontStyle.LEFT,
               FontWeight.MIDDLE
            );
            var1.popClip();
         }

         var1.popAlpha();
      }
   }

   private void core(DrawContext var1, Canvas var2, float var3, float var4, float var5, float var6, float var7, boolean var8, float var9) {
      float var10 = var8 ? 0.0F : (float)Math.sin(var7 * 1.15) * 4.0F * (1.0F - var5);
      float var11 = 150.0F * (0.9F + 0.1F * var6);
      float var12 = lerp(var11, 30.0F, var5);
      float var13 = var12 * this.mark.aspect();
      float var14 = lerp(var3, 36.0F + 30.0F * this.mark.aspect() / 2.0F, var5);
      float var15 = lerp(var4 - 8.0F + var10, var9, var5);
      this.homeX = var14 - var13 / 2.0F;
      this.homeY = var15 - var12 / 2.0F;
      this.homeW = var13;
      this.homeH = var12;
      boolean var16 = var5 > 0.9F && var1.hover(this.homeKey, this.homeX - 6.0F, this.homeY - 6.0F, var13 + 12.0F, var12 + 12.0F, DrawContext.Cursor.HAND);
      float var17 = this.homeHover.target(var16 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var18 = var8 ? 0.85F : 0.78F + 0.22F * (float)Math.sin(var7 * 1.8);
      var2.pushAlpha(var6);
      var2.glowCircle(var14, var15 + var12 * 0.05F, var12 * 0.36F, var12 * 1.05F, BlendMode.withAlpha(Theme.logoTint(-3069451), (0.2F + 0.12F * var17) * var18));
      var2.glowCircle(var14, var15, var12 * 0.2F, var12 * 0.6F, BlendMode.withAlpha(Theme.logoTint(-7860231), 0.16F * var18));
      var2.push();
      var2.scaleAround(1.0F + var17 * 0.06F, var14, var15);
      var2.logo(this.mark, var14 - var13 / 2.0F, var15 - var12 / 2.0F, var13, var12, -1);
      var2.pop();
      float var19 = (1.0F - smooth(var5 / 0.35F)) * var6;
      if (var19 > 0.01F) {
         float var20 = 13.0F;
         float var21 = var20 * this.wordmark.aspect();
         var2.image(this.wordmark, var3 - var21 / 2.0F, 30.0F, var21, var20, 0.0F, BlendMode.withAlpha(-1, 0.92F * var19));
      }

      float var23 = smooth((var5 - 0.6F) / 0.4F);
      if (var23 > 0.01F) {
         float var24 = 10.5F;
         float var22 = var24 * this.wordmark.aspect();
         var2.image(
            this.wordmark, this.homeX + var13 + 12.0F - (1.0F - var23) * 8.0F, var9 - var24 / 2.0F, var22, var24, 0.0F, BlendMode.withAlpha(-1, 0.9F * var23)
         );
      }

      var2.popAlpha();
   }

   private void info(Canvas var1, float var2, float var3, float var4, float var5) {
      int var6 = Math.round(var4);
      float var7 = 1.0F - Math.min(1.0F, Math.abs(var4 - var6) * 2.2F);
      Category var8 = CATS[Math.floorMod(var6, N)];
      float var9 = var5 * var7 * var7;
      if (!(var9 <= 0.01F)) {
         float var10 = (1.0F - var7) * 6.0F;
         var1.text(Fonts.BOLD, var8.displayName, var2, var3 + var10, 32.0F, BlendMode.withAlpha(Icons.TEXT, var9), FontStyle.CENTER, FontWeight.TOP, -0.01F);
         var1.text(Fonts.REGULAR, var8.tagline, var2, var3 + 44.0F + var10, 13.5F, BlendMode.withAlpha(Icons.TEXT_2, var9), FontStyle.CENTER, FontWeight.TOP);
         if (var8 == Category.PROFILE || var8 == Category.CONFIGS) {
            boolean var11 = var8 == Category.PROFILE;
            int var12 = var11 ? (ProfileSupport.live() != null ? 1 : 0) : ConfigLibraryPanel.inUse();
            String var13 = var11 ? ProfilePanel.orbitLine() : ConfigLibraryPanel.orbitLine();
            float var14 = var1.textWidth(Fonts.MEDIUM, var13, 11.5F);
            float var15 = var14 + 30.0F;
            float var16 = 26.0F;
            float var17 = var3 + 74.0F + var10;
            var1.pushAlpha(var9);
            var1.roundRect(var2 - var15 / 2.0F, var17, var15, var16, var16 / 2.0F, 352321535);
            var1.roundRectOutline(var2 - var15 / 2.0F, var17, var15, var16, var16 / 2.0F, 1.0F, 486539263);
            var1.circle(var2 - var15 / 2.0F + 13.0F, var17 + var16 / 2.0F, 2.6F, var12 > 0 ? Icons.MAGENTA : Icons.TEXT_3);
            var1.text(Fonts.MEDIUM, var13, var2 + 5.0F, var17 + var16 / 2.0F, 11.5F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
            var1.popAlpha();
         }
      }
   }

   Category hitCategory() {
      return this.hitCategory;
   }

   StudioPanel.PanelMode mouseDown(float var1, float var2) {
      if (this.view > 0.5F
         && var1 >= this.homeX - 6.0F
         && var1 <= this.homeX + this.homeW + 6.0F
         && var2 >= this.homeY - 6.0F
         && var2 <= this.homeY + this.homeH + 6.0F) {
         return StudioPanel.PanelMode.HOME;
      } else {
         int var3 = -1;
         float var4 = -1.0F;

         for (int var5 = 0; var5 < N; var5++) {
            if (this.hitPlanet(var5, var1, var2) && this.gDepth[var5] > var4) {
               var4 = this.gDepth[var5];
               var3 = var5;
            }
         }

         if (var3 >= 0) {
            this.hitCategory = CATS[var3];
            if (this.view > 0.5F) {
               return StudioPanel.PanelMode.TAB;
            } else {
               return CATS[var3] == this.front() && Math.abs(this.pos.value() - this.pos.target()) < 0.35F
                  ? StudioPanel.PanelMode.OPEN_FRONT
                  : StudioPanel.PanelMode.FOCUS;
            }
         } else if (this.view < 0.5F) {
            this.dragging = true;
            this.dragMoved = false;
            this.dragStartX = var1;
            this.dragLastX = var1;
            this.dragStartPos = this.pos.value();
            this.dragLastTime = System.nanoTime();
            this.dragVelocity = 0.0F;
            return StudioPanel.PanelMode.DRAG;
         } else {
            return StudioPanel.PanelMode.NONE;
         }
      }
   }

   void mouseDrag(float var1) {
      if (this.dragging) {
         if (Math.abs(var1 - this.dragStartX) > 4.0F) {
            this.dragMoved = true;
         }

         float var2 = this.dragStartPos - (var1 - this.dragStartX) / this.spacing;
         long var3 = System.nanoTime();
         float var5 = Math.max(0.001F, (float)(var3 - this.dragLastTime) / 1.0E9F);
         float var6 = -(var1 - this.dragLastX) / this.spacing / var5;
         this.dragVelocity = this.dragVelocity * 0.6F + var6 * 0.4F;
         this.dragLastX = var1;
         this.dragLastTime = var3;
         this.pos.snap(var2);
      }
   }

   boolean mouseUp() {
      if (!this.dragging) {
         return false;
      } else {
         this.dragging = false;
         float var1 = this.pos.value();
         float var2 = Math.max(-2.5F, Math.min(2.5F, this.dragVelocity * 0.18F));
         this.pos.snap(var1).impulse(this.dragVelocity * 0.5F).target(Math.round(var1 + var2));
         return this.dragMoved;
      }
   }

   private boolean hitPlanet(int var1, float var2, float var3) {
      float var4 = this.gw[var1];
      float var5 = this.gh[var1];
      if (this.view < 0.5F) {
         float var6 = var5 / 2.0F + 5.0F;
         float var7 = var2 - this.gx[var1];
         float var8 = var3 - this.gy[var1];
         return var7 * var7 + var8 * var8 <= var6 * var6;
      } else {
         return var2 >= this.gx[var1] - var4 / 2.0F
            && var2 <= this.gx[var1] + var4 / 2.0F
            && var3 >= this.gy[var1] - var5 / 2.0F
            && var3 <= this.gy[var1] + var5 / 2.0F;
      }
   }

   private static float wrap(float var0) {
      float var1 = var0 % N;
      if (var1 > N / 2.0F) {
         var1 -= N;
      } else if (var1 < -N / 2.0F) {
         var1 += N;
      }

      return var1;
   }

   private static float smooth(float var0) {
      var0 = var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static float lerp(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   static enum PanelMode {
      NONE,
      OPEN_FRONT,
      FOCUS,
      TAB,
      HOME,
      DRAG;

      private static StudioPanel.PanelMode[] $values() {
         return new StudioPanel.PanelMode[]{NONE, OPEN_FRONT, FOCUS, TAB, HOME, DRAG};
      }
   }
}
