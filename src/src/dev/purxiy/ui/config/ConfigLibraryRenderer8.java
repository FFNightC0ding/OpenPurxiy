// Sourced By DevFFNightmare
package dev.purxiy.ui.config;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.profile.ProfileSupport2;
import dev.purxiy.profile.ProfileSupport5;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_1044;
import net.minecraft.class_1068;
import net.minecraft.class_12137;
import net.minecraft.class_2960;
import net.minecraft.class_310;

final class ConfigLibraryRenderer8 {
   static final float RADIUS = 18.0F;
   static final int DONE = -1783297;
   static final int DANGER = -29785;
   private static final Map<String, class_2960> FACES = new HashMap<>();

   private ConfigLibraryRenderer8() {
   }

   static void label(Canvas var0, String var1, float var2, float var3, int var4, FontStyle var5) {
      var0.text(Fonts.SEMIBOLD, var1, var2, var3, 9.5F, var4, var5, FontWeight.MIDDLE, 0.12F);
   }

   static void tooltip(Canvas var0, float var1, float var2, String var3,  String var4, float var5) {
      tooltip(var0, var1, var2, var3, var4, var5, false);
   }

   static void tooltip(Canvas var0, float var1, float var2, String var3,  String var4, float var5, boolean var6) {
      if (!(var5 <= 0.01F)) {
         Translations.pause();
         float var7 = var0.textWidth(Fonts.SEMIBOLD, var3, 12.0F);
         Translations.resume();
         if (var4 != null) {
            var7 = Math.max(var7, var0.textWidth(Fonts.MEDIUM, var4, 10.5F));
         }

         float var8 = var7 + 22.0F;
         float var9 = var4 == null ? 28.0F : 44.0F;
         float var10 = Math.max(8.0F, Math.min(var0.width() - var8 - 8.0F, var1 - var8 / 2.0F));
         float var11 = var6 ? var2 + 8.0F - (1.0F - var5) * 4.0F : var2 - var9 - 10.0F + (1.0F - var5) * 4.0F;
         var0.pushAlpha(var5);
         var0.shadow(var10, var11, var8, var9, 10.0F, 18.0F, 0.0F, 0.0F, 5.0F, 1996488704);
         var0.roundRect(var10, var11, var8, var9, 10.0F, Theme.tint(-233434583));
         var0.roundRectOutline(var10, var11, var8, var9, 10.0F, 1.0F, 620756991);
         Translations.pause();
         var0.text(Fonts.SEMIBOLD, var3, var10 + 11.0F, var11 + 14.0F, 12.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
         Translations.resume();
         if (var4 != null) {
            var0.text(Fonts.MEDIUM, var4, var10 + 11.0F, var11 + 30.0F, 10.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         }

         var0.popAlpha();
      }
   }

   static String count(int var0) {
      return var0 < 1000 ? Integer.toString(var0) : ProfileSupport2.countShort(var0);
   }

   static String modules(int var0) {
      return var0 + (var0 == 1 ? " module" : " modules");
   }

   static String reach(ConfigLibraryFactory2 var0) {
      String var1 = Translations.tr(count(var0.likes));
      return var1
         + " "
         + Translations.plural(var0.likes, "like", "likes")
         + "  ·  "
         + Translations.tr(count(var0.downloads))
         + " "
         + Translations.plural(var0.downloads, "download", "downloads");
   }

   static String ago(long var0) {
      LocalDate var2 = LocalDate.ofInstant(Instant.ofEpochMilli(var0), ZoneId.systemDefault());
      long var3 = ChronoUnit.DAYS.between(var2, LocalDate.now());
      if (var3 <= 0L) {
         return "today";
      } else if (var3 == 1L) {
         return "yesterday";
      } else {
         return var3 < 14L ? var3 + " days ago" : ProfileSupport2.date(var2);
      }
   }

   static void reading(Canvas var0, float var1, float var2, float var3, float var4, float var5) {
      var0.rect(var1, var2, var3, var4, 805306368);
      float var6 = MotionStyle.reduced() ? 0.5F : var5 * 0.8F % 1.0F;
      float var7 = Math.max(60.0F, var3 * 0.3F);
      float var8 = var1 - var7 + (var3 + var7 * 2.0F) * var6;
      var0.push();
      var0.rotateAround(0.32F, var8, var2 + var4 / 2.0F);
      int var9 = 788529151;
      var0.roundRectGradient(var8 - var7 / 2.0F, var2 - var4, var7 / 2.0F, var4 * 3.0F, 0.0F, 0.0F, BlendMode.withAlpha(var9, 0.0F), var9);
      var0.roundRectGradient(var8, var2 - var4, var7 / 2.0F, var4 * 3.0F, 0.0F, 0.0F, var9, BlendMode.withAlpha(var9, 0.0F));
      var0.pop();
   }

   static void face(Canvas var0, String var1, float var2, float var3, float var4) {
      float var5 = var4 * 0.3F;
      if (!var1.equals(ConfigLibraryTransform.you()) || !ProfileSupport5.face(var0, var2, var3, var4, var5, -1)) {
         class_2960 var6 = FACES.computeIfAbsent(
            var1, var0x -> class_1068.method_4648(UUID.nameUUIDFromBytes(("OfflinePlayer:" + var0x).getBytes(StandardCharsets.UTF_8))).comp_1626().comp_3627()
         );

         GpuTextureView var7;
         try {
            class_1044 var8 = class_310.method_1551().method_1531().method_4619(var6);
            var7 = var8.method_71659();
         } catch (IllegalStateException var9) {
            var7 = null;
         }

         if (var7 == null) {
            var0.roundRect(var2, var3, var4, var4, var5, 587202559);
         } else {
            class_12137 var10 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
            var0.image(var7, var10, false, var2, var3, var4, var4, 0.125F, 0.125F, 0.25F, 0.25F, var5, -1);
            var0.image(var7, var10, false, var2, var3, var4, var4, 0.625F, 0.125F, 0.75F, 0.25F, var5, -1);
            var0.roundRectOutline(var2, var3, var4, var4, var5, 1.0F, 536870911);
         }
      }
   }

   static void dashed(Canvas var0, float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, int var9, float var10) {
      var5 = Math.min(var5, Math.min(var3, var4) / 2.0F);
      float var11 = var3 - 2.0F * var5;
      float var12 = var4 - 2.0F * var5;
      float var13 = (float)(Math.PI * var5 / 2.0);
      float[] var14 = new float[]{var11, var13, var12, var13, var11, var13, var12, var13};
      float var15 = 0.0F;

      for (float var19 : var14) {
         var15 += var19;
      }

      float var26 = var6 + var7;
      int var27 = Math.max(4, Math.round(var15 / var26));
      float var28 = var15 / (var27 * var26);
      float var29 = var6 * var28;
      float var20 = var26 * var28;
      float var21 = (var10 % var20 + var20) % var20;

      for (int var22 = 0; var22 < var27; var22++) {
         float var23 = var21 + var22 * var20;
         float var24 = var23 + var29;
         dashPart(var0, var1, var2, var3, var4, var5, var14, var23, Math.min(var15, var24), var8, var9);
         if (var24 > var15) {
            dashPart(var0, var1, var2, var3, var4, var5, var14, 0.0F, var24 - var15, var8, var9);
         }
      }
   }

   private static void dashPart(
      Canvas var0, float var1, float var2, float var3, float var4, float var5, float[] var6, float var7, float var8, float var9, int var10
   ) {
      float var11 = 0.0F;

      for (int var12 = 0; var12 < 8; var12++) {
         float var13 = var6[var12];
         float var14 = Math.max(var7, var11);
         float var15 = Math.min(var8, var11 + var13);
         if (var15 > var14 && var13 > 0.0F) {
            float var16 = (var14 - var11) / var13;
            float var17 = (var15 - var11) / var13;
            switch (var12) {
               case 0:
                  var0.line(var1 + var5 + var16 * var13, var2 + var9 / 2.0F, var1 + var5 + var17 * var13, var2 + var9 / 2.0F, var9, var10);
                  break;
               case 1:
               case 3:
               case 5:
               default:
                  float var18 = var12 != 1 && var12 != 3 ? var1 + var5 : var1 + var3 - var5;
                  float var19 = var12 != 1 && var12 != 7 ? var2 + var4 - var5 : var2 + var5;

                  var0.arc(var18, var19, var5 - var9 / 2.0F, var9, switch (var12) {
                     case 1 -> -90.0F;
                     default -> 180.0F;
                     case 3 -> 0.0F;
                     case 5 -> 90.0F;
                  } + var16 * 90.0F, (var17 - var16) * 90.0F, var10);
                  break;
               case 2:
                  var0.line(var1 + var3 - var9 / 2.0F, var2 + var5 + var16 * var13, var1 + var3 - var9 / 2.0F, var2 + var5 + var17 * var13, var9, var10);
                  break;
               case 4:
                  var0.line(
                     var1 + var3 - var5 - var16 * var13, var2 + var4 - var9 / 2.0F, var1 + var3 - var5 - var17 * var13, var2 + var4 - var9 / 2.0F, var9, var10
                  );
                  break;
               case 6:
                  var0.line(var1 + var9 / 2.0F, var2 + var4 - var5 - var16 * var13, var1 + var9 / 2.0F, var2 + var4 - var5 - var17 * var13, var9, var10);
            }
         }

         var11 += var13;
      }
   }

   static final class TypeC {
      final Animator hover = Animator.smooth(0.0F);
      private final Animator bump = Animator.snappy(0.0F);
      float x;
      float y;
      float w;
      float h;
      boolean shown;

      static float width(Canvas var0,  String var1, String var2, float var3) {
         float var4 = textSize(var3);
         float var5 = var2.isEmpty() ? 0.0F : var0.textWidth(Fonts.MEDIUM, var2, var4);
         float var6 = var1 == null ? 0.0F : (var2.isEmpty() ? 0.0F : var4 + 6.0F);
         return var2.isEmpty() ? var3 : Math.round(var5 + var6 + var3 * 0.9F);
      }

      private static float textSize(float var0) {
         return var0 >= 34.0F ? 12.5F : (var0 >= 30.0F ? 12.0F : 11.5F);
      }

      float render(DrawContext var1, float var2, float var3, float var4,  String var5, String var6, ConfigLibraryRenderer8.TypeI var7, boolean var8) {
         Canvas var9 = var1.canvas;
         float var10 = width(var9, var5, var6, var4);
         return this.render(var1, var2, var3, var10, var4, var5, var6, var7, var8);
      }

      float render(
         DrawContext var1, float var2, float var3, float var4, float var5,  String var6, String var7, ConfigLibraryRenderer8.TypeI var8, boolean var9
      ) {
         label76: {
            Canvas var10 = var1.canvas;
            this.x = var2;
            this.y = var3 - var5 / 2.0F;
            this.w = var4;
            this.h = var5;
            this.shown = var9 && var8 != ConfigLibraryRenderer8.TypeI.DONE;
            boolean var11 = this.shown && var1.hover(this, var2, this.y, var4, var5, DrawContext.TypeE.HAND);
            float var12 = this.hover.target(var11 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
            float var13 = this.bump.update(MotionStyle.TOGGLE.dt(var1.dt));
            float var14 = var5 / 2.0F;
            var10.push();
            var10.scaleAround(1.0F - 0.06F * var13, var2 + var4 / 2.0F, var3);
            int var15 = 0;

            var15 = switch (var8) {
               case PRIMARY -> {
                  if (var12 > 0.01F) {
                     var10.glow(var2, this.y, var4, var5, var14, 16.0F, BlendMode.withAlpha(Icons.VIOLET, 0.36F * var12));
                  }

                  var10.roundRectGradient(
                     var2,
                     this.y,
                     var4,
                     var5,
                     var14,
                     0.0F,
                     BlendMode.withAlpha(Icons.INDIGO, 0.78F + 0.22F * var12),
                     BlendMode.withAlpha(Icons.PURPLE, 0.78F + 0.22F * var12)
                  );
                  var10.roundRectCorners(var2, this.y, var4, var5, var14, 654311423, 654311423, 16777215, 16777215);
                  var10.roundRectOutline(var2, this.y, var4, var5, var14, 1.0F, 872415231);
                  if ((var8 == ConfigLibraryRenderer8.TypeI.TEXT || var8 == ConfigLibraryRenderer8.TypeI.DANGER) && var12 > 0.01F) {
                     var10.roundRect(var2, this.y, var4, var5, var14, BlendMode.withAlpha(-1, 0.06F * var12));
                  }

                  float var16 = textSize(var5);
                  if (var7.isEmpty()) {
                     if (var6 != null) {
                        var10.icon(var6, var2 + var4 / 2.0F, var3, var5 * 0.44F, var15);
                     }
                  } else {
                     float var17 = var10.textWidth(Fonts.MEDIUM, var7, var16);
                     float var18 = var6 == null ? 0.0F : var16 + 6.0F;
                     float var19 = var2 + var4 / 2.0F - (var17 + var18) / 2.0F;
                     if (var6 != null) {
                        var10.icon(var6, var19 + var16 / 2.0F, var3, var16 + 1.0F, var15);
                     }

                     var10.text(Fonts.MEDIUM, var7, var19 + var18, var3, var16, var15, FontStyle.LEFT, FontWeight.MIDDLE);
                  }

                  var10.pop();
                  yield (int)var4;
               }
               case QUIET -> {
                  var10.roundRect(var2, this.y, var4, var5, var14, BlendMode.lerp(285212671, 520093695, var12));
                  var10.roundRectOutline(var2, this.y, var4, var5, var14, 1.0F, BlendMode.lerp(419430399, 922746879, var12));
                  yield BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, Math.max(var12, 0.35F));
               }
               case DONE -> {
                  var10.roundRectOutline(var2, this.y, var4, var5, var14, 1.0F, BlendMode.withAlpha(Theme.tint(-1783297), 0.22F));
                  yield Theme.tint(-1783297);
               }
               default -> BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var12);
               case DANGER -> BlendMode.lerp(BlendMode.withAlpha(-29785, 0.8F), -29785, var12);
            };
         }
         return 0.0F;
      }

      boolean hit(float var1, float var2) {
         return this.shown && var1 >= this.x && var1 <= this.x + this.w && var2 >= this.y && var2 <= this.y + this.h;
      }

      void press() {
         this.bump.snap(1.0F).target(0.0F);
      }
   }

   static enum TypeI {
      PRIMARY,
      QUIET,
      DONE,
      TEXT,
      DANGER;

      private static ConfigLibraryRenderer8.TypeI[] $values() {
         return new ConfigLibraryRenderer8.TypeI[]{PRIMARY, QUIET, DONE, TEXT, DANGER};
      }
   }

   static final class TypeW {
      final Animator hover = Animator.smooth(0.0F);
      private final Animator bump = Animator.snappy(0.0F);
      float x;
      float y;
      float d;
      boolean shown;

      void render(DrawContext var1, float var2, float var3, float var4, String var5, boolean var6, int var7, float var8, boolean var9) {
         Canvas var10 = var1.canvas;
         this.x = var2 - var4 / 2.0F;
         this.y = var3 - var4 / 2.0F;
         this.d = var4;
         this.shown = var9 && var8 > 0.5F;
         boolean var11 = this.shown && var1.hover(this, this.x, this.y, var4, var4, DrawContext.TypeE.HAND);
         float var12 = this.hover.target(var11 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         float var13 = this.bump.update(MotionStyle.TOGGLE.dt(var1.dt));
         if (!(var8 <= 0.01F)) {
            var10.pushAlpha(var8);
            var10.push();
            var10.scaleAround(1.0F + 0.22F * var13 + 0.06F * var12, var2, var3);
            var10.shadow(this.x, this.y, var4, var4, var4 / 2.0F, 10.0F, 0.0F, 0.0F, 2.0F, 1426063360);
            var10.circle(var2, var3, var4 / 2.0F, BlendMode.lerp(Theme.tint(-1206776286), Theme.tint(-669116362), var12));
            var10.roundRectOutline(this.x, this.y, var4, var4, var4 / 2.0F, 1.0F, BlendMode.lerp(620756991, 1157627903, var12));
            var10.icon(var5, var2, var3 + (var5.equals("heart") ? 0.5F : 0.0F), var4 * 0.46F, var6 ? var7 : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var12));
            var10.pop();
            var10.popAlpha();
         }
      }

      boolean hit(float var1, float var2) {
         float var3 = this.d / 2.0F;
         float var4 = var1 - (this.x + var3);
         float var5 = var2 - (this.y + var3);
         return this.shown && var4 * var4 + var5 * var5 <= var3 * var3;
      }

      void press() {
         this.bump.snap(1.0F).target(0.0F);
      }
   }
}
