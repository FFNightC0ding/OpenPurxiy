// Sourced By DevFFNightmare
package dev.purxiy.profile;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.class_310;

final class ProfileRenderer {
   private static final int[] SCALES = new int[]{5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
   private static final float OLD = 0.0F;
   private final Animator hoverAlpha = Animator.smooth(0.0F);
   private final Object mapKey = new Object();
   private final List<ProfileRenderer.TypeT> pieces = new ArrayList<>();
   
   private ProfileCodec2 cached;
   private int cachedPoints = -1;
   private float cachedW;
   private float cachedH;
   private float laidOutAt = -1.0F;
   private static final float RELAYOUT = 0.5F;
   private double centerX;
   private double centerZ;
   private float scale = 1.0F;
   
   private String dimension;
   private float hoverX;
   private float hoverY;
   private float hoverTime;
   private boolean hoverFound;
   private static final String[] MODE_NAMES = new String[]{"On foot", "Sprinting", "Elytra", "Riding", "Swimming", "Flying"};
   private static final int[] MODE_COLORS = new int[]{-4611329, -6587393, -1733377, -34104, -8599041, -9610241};

   void render(DrawContext var1, ProfileSupport6 var2, float var3, float var4, float var5, float var6, float var7, float var8, boolean var9) {
      Canvas var10 = var1.canvas;
      ProfileCodec2 var11 = ProfileSupport.journey();
      boolean var12 = ProfileSupport.journeyLive();
      ProfileRenderer9.card(var10, var3, var4, var5, var6, 0.0F);
      String var13;
      if (var11 == null) {
         var13 = "Your route, seen from above";
      } else {
         LocalDateTime var14 = LocalDateTime.ofInstant(Instant.ofEpochMilli(var11.start), ZoneId.systemDefault());
         String var15 = var12 ? "This session" : "Last session, " + ProfileSupport2.date(var14.toLocalDate());
         var13 = var15 + (var11.place.isEmpty() ? "" : "  ·  " + var11.place);
      }

      ProfileRenderer9.title(var10, "Journey", var13, var3, var4);
      float var22 = var3 + 16.0F;
      float var23 = var4 + 58.0F;
      float var16 = var5 - 32.0F;
      float var17 = var6 - 58.0F - 52.0F;
      var10.roundRect(var22, var23, var16, var17, 12.0F, 855638016);
      var10.roundRectOutline(var22, var23, var16, var17, 12.0F, 1.0F, 268435455);
      if (var11 != null && var11.points() >= 2) {
         this.layout(var11, var16, var17, var7);
         if (this.dimension != null) {
            this.pill(var10, dimensionName(this.dimension), var12, var3 + var5 - 20.0F, var4 + 30.0F, var7);
         }

         var10.pushClip(var22, var23, var16, var17, 12.0F);
         this.grid(var10, var22, var23, var16, var17);
         this.dust(var10, var22, var23, var16, var17, var7);
         float var18 = Easing.EMPHASIZED.apply(ProfileCurve.clamp01((var8 - 0.2F) / 1.4F));
         var10.push();
         var10.translate(var22, var23);

         for (ProfileRenderer.TypeT var20 : this.pieces) {
            int var21 = Math.max(2, Math.round(var20.n * var18));
            if (var20.n >= 2) {
               var21 = Math.min(var21, var20.n);
               ProfileCurve.ribbon(
                  var10, var20.x, var20.y, var20.t, var21, 3.0F, 8.0F, BlendMode.withAlpha(Icons.INDIGO, 0.12F), BlendMode.withAlpha(Icons.MAGENTA, 0.32F)
               );
               ProfileCurve.ribbon(var10, var20.x, var20.y, var20.t, var21, 2.1F, 1.0F, BlendMode.withAlpha(Theme.tint(-7373864), 0.6F), Theme.tint(-1012489));
            }
         }

         var10.pop();
         this.marks(var10, var11, var22, var23, var18);
         this.ends(var10, var11, var22, var23, var12, var7, var18);
         this.scaleBar(var10, var22 + 14.0F, var23 + var17 - 16.0F);
         this.compass(var10, var22 + var16 - 18.0F, var23 + 18.0F);
         var10.popClip();
         this.hover(var1, var10, var11, var22, var23, var16, var17, var9);
         this.footer(var10, var2, var11, var3, var4 + var6 - 30.0F, var5, var12);
      } else {
         this.empty(var10, var22, var23, var16, var17, var7);
         this.footer(var10, var2, (ProfileCodec2)null, var3, var4 + var6 - 30.0F, var5, var12);
      }
   }

   private void layout(ProfileCodec2 var1, float var2, float var3, float var4) {
      boolean var5 = var1 == this.cached && var2 == this.cachedW && var3 == this.cachedH;
      if (!var5 || var1.points() != this.cachedPoints && (!(var4 - this.laidOutAt < 0.5F) || !(var4 >= this.laidOutAt))) {
         this.laidOutAt = var4;
         this.cached = var1;
         this.cachedPoints = var1.points();
         this.cachedW = var2;
         this.cachedH = var3;
         this.pieces.clear();
         this.dimension = var1.mainDimension();
         double var6 = Double.MAX_VALUE;
         double var8 = -Double.MAX_VALUE;
         double var10 = Double.MAX_VALUE;
         double var12 = -Double.MAX_VALUE;
         int var14 = 0;

         for (ProfileCodec2.TypeY var16 : var1.segments) {
            if (var16.dimension.equals(this.dimension)) {
               for (int var17 = 0; var17 < var16.size(); var17++) {
                  var6 = Math.min(var6, (double)var16.xs.getFloat(var17));
                  var8 = Math.max(var8, (double)var16.xs.getFloat(var17));
                  var10 = Math.min(var10, (double)var16.zs.getFloat(var17));
                  var12 = Math.max(var12, (double)var16.zs.getFloat(var17));
               }

               var14 = Math.max(var14, var16.times.isEmpty() ? 0 : var16.times.getInt(var16.size() - 1));
            }
         }

         if (!(var6 > var8)) {
            float var28 = 26.0F;
            double var29 = Math.max(48.0, var8 - var6);
            double var18 = Math.max(48.0, var12 - var10);
            this.scale = (float)Math.min((var2 - var28 * 2.0F) / var29, (var3 - var28 * 2.0F) / var18);
            this.centerX = (var6 + var8) / 2.0;
            this.centerZ = (var10 + var12) / 2.0;
            int var20 = Math.max(1, var1.duration());

            for (ProfileCodec2.TypeY var22 : var1.segments) {
               if (var22.dimension.equals(this.dimension) && var22.size() >= 2) {
                  int var23 = var22.size();
                  float[] var24 = new float[var23];
                  float[] var25 = new float[var23];
                  float[] var26 = new float[var23];

                  for (int var27 = 0; var27 < var23; var27++) {
                     var24[var27] = this.mapX(var22.xs.getFloat(var27), var2);
                     var25[var27] = this.mapY(var22.zs.getFloat(var27), var3);
                     var26[var27] = 0.0F + 1.0F * (float)Math.pow((double)var22.times.getInt(var27) / var20, 1.6);
                  }

                  int var30 = ProfileCurve.simplify(var24, var25, var26, var23, 0.45F);
                  this.pieces.add(new ProfileRenderer.TypeT(var24, var25, var26, var30));
               }
            }
         }
      }
   }

   private float mapX(double var1, float var3) {
      return (float)(var3 / 2.0F + (var1 - this.centerX) * this.scale);
   }

   private float mapY(double var1, float var3) {
      return (float)(var3 / 2.0F + (var1 - this.centerZ) * this.scale);
   }

   private void grid(Canvas var1, float var2, float var3, float var4, float var5) {
      float var6 = niceScale(70.0F / this.scale) * this.scale;
      if (!(var6 < 12.0F)) {
         float var7 = (float)((-this.centerX * this.scale + var4 / 2.0F) % var6);
         float var8 = (float)((-this.centerZ * this.scale + var5 / 2.0F) % var6);

         for (float var9 = var2 + (var7 < 0.0F ? var7 + var6 : var7); var9 < var2 + var4; var9 += var6) {
            var1.rect(var9 - 0.5F, var3, 1.0F, var5, 150994943);
         }

         for (float var10 = var3 + (var8 < 0.0F ? var8 + var6 : var8); var10 < var3 + var5; var10 += var6) {
            var1.rect(var2, var10 - 0.5F, var4, 1.0F, 150994943);
         }
      }
   }

   private void dust(Canvas var1, float var2, float var3, float var4, float var5, float var6) {
      for (int var7 = 0; var7 < 46; var7++) {
         float var8 = var2 + 6.0F + hash(var7 * 3) * (var4 - 12.0F);
         float var9 = var3 + 6.0F + hash(var7 * 3 + 1) * (var5 - 12.0F);
         float var10 = MotionStyle.reduced() ? 0.6F : 0.5F + 0.5F * (float)Math.sin(var6 * (0.6F + hash(var7 * 3 + 2)) + var7);
         var1.circle(var8, var9, 0.5F + hash(var7 * 5) * 0.7F, BlendMode.withAlpha(-1, 0.04F + 0.12F * var10));
      }

      var1.roundRectRadial(var2, var3, var4, var5, 12.0F, 0, 805306368);
   }

   private static float hash(int var0) {
      double var1 = Math.sin(var0 * 12.9898 + 78.233) * 43758.5453;
      return (float)(var1 - Math.floor(var1));
   }

   private void marks(Canvas var1, ProfileCodec2 var2, float var3, float var4, float var5) {
      if (!(var5 < 0.95F)) {
         for (ProfileCodec2.TypeB var7 : var2.marks) {
            if (var7.dimension().equals(this.dimension)) {
               float var8 = var3 + this.mapX(var7.x(), this.cachedW);
               float var9 = var4 + this.mapY(var7.z(), this.cachedH);
               if ("death".equals(var7.type())) {
                  var1.softCircle(var8, var9, 7.0F, 6.0F, -1728053248);
                  var1.icon("skull", var8, var9, 12.0F, Icons.PINK);
               } else {
                  var1.icon("swords", var8, var9, 10.0F, BlendMode.withAlpha(Theme.tint(-4611329), 0.75F));
               }
            }
         }
      }
   }

   private void ends(Canvas var1, ProfileCodec2 var2, float var3, float var4, boolean var5, float var6, float var7) {
      ProfileCodec2.TypeY var8 = null;
      ProfileCodec2.TypeY var9 = null;

      for (ProfileCodec2.TypeY var11 : var2.segments) {
         if (var11.dimension.equals(this.dimension) && var11.size() > 0) {
            if (var8 == null) {
               var8 = var11;
            }

            var9 = var11;
         }
      }

      if (var8 != null) {
         float var29 = var3 + this.mapX(var8.xs.getFloat(0), this.cachedW);
         float var30 = var4 + this.mapY(var8.zs.getFloat(0), this.cachedH);
         var1.circle(var29, var30, 4.5F, Theme.tint(-871692011));
         var1.ring(var29, var30, 3.8F, 1.6F, Icons.TEXT_2);
         if (!(var7 < 0.999F)) {
            int var12 = var9.size() - 1;
            float var13 = var3 + this.mapX(var9.xs.getFloat(var12), this.cachedW);
            float var14 = var4 + this.mapY(var9.zs.getFloat(var12), this.cachedH);
            class_310 var15 = class_310.method_1551();
            if (var5 && var15.field_1724 != null && !ProfileSupport.demo()) {
               var13 = var3 + this.mapX(var15.field_1724.method_23317(), this.cachedW);
               var14 = var4 + this.mapY(var15.field_1724.method_23321(), this.cachedH);
            }

            float var16 = MotionStyle.reduced() ? 0.5F : var6 * 0.7F % 1.0F;
            if (var5) {
               float var17 = var15.field_1724 != null && !ProfileSupport.demo() ? var15.field_1724.method_36454() : 135.0F;
               double var18 = Math.toRadians(var17 + 90.0F);
               float var20 = 0.5F;
               float var21 = 30.0F;
               int var22 = BlendMode.withAlpha(Icons.PINK, 0.42F);
               int var23 = BlendMode.withAlpha(Icons.MAGENTA, 0.0F);

               for (int var24 = 0; var24 < 4; var24++) {
                  double var25 = var18 - var20 + var20 * var24 / 2.0F;
                  double var27 = var18 - var20 + var20 * (var24 + 1) / 2.0F;
                  var1.triangle(
                     var13,
                     var14,
                     var13 + (float)Math.cos(var25) * var21,
                     var14 + (float)Math.sin(var25) * var21,
                     var13 + (float)Math.cos(var27) * var21,
                     var14 + (float)Math.sin(var27) * var21,
                     var22,
                     var23,
                     var23
                  );
               }

               var1.ring(var13, var14, 6.0F + var16 * 12.0F, 1.4F, BlendMode.withAlpha(Icons.PINK, 0.6F * (1.0F - var16)));
            }

            var1.glowCircle(var13, var14, 4.0F, 14.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.55F));
            var1.circle(var13, var14, 5.0F, -1);
            var1.circle(var13, var14, 3.2F, var5 ? Icons.MAGENTA : Theme.tint(-1012489));
         }
      }
   }

   private void scaleBar(Canvas var1, float var2, float var3) {
      int var4 = niceScale(90.0F / this.scale);
      float var5 = var4 * this.scale;
      var1.rect(var2, var3 - 0.5F, var5, 1.2F, 1728053247);
      var1.rect(var2, var3 - 3.5F, 1.2F, 7.0F, 1728053247);
      var1.rect(var2 + var5 - 1.2F, var3 - 3.5F, 1.2F, 7.0F, 1728053247);
      String var6 = var4 >= 1000 ? var4 / 1000 + " km" : var4 + " m";
      var1.text(Fonts.MEDIUM, var6, var2 + var5 + 8.0F, var3, 9.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   private void compass(Canvas var1, float var2, float var3) {
      var1.circle(var2, var3, 10.0F, 1426063360);
      var1.ring(var2, var3, 10.0F, 1.0F, 452984831);
      var1.triangle(var2, var3 - 7.0F, var2 - 3.0F, var3 + 1.0F, var2 + 3.0F, var3 + 1.0F, Theme.tint(-1012489));
      var1.text(Fonts.BOLD, "N", var2, var3 + 5.0F, 6.5F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
   }

   private void pill(Canvas var1, String var2, boolean var3, float var4, float var5, float var6) {
      float var7 = var1.textWidth(Fonts.MEDIUM, var2, 10.5F);
      float var8 = var7 + (var3 ? 30 : 20);
      float var9 = 22.0F;
      float var10 = var4 - var8;
      var1.roundRect(var10, var5 - var9 / 2.0F, var8, var9, var9 / 2.0F, 318767103);
      var1.roundRectOutline(var10, var5 - var9 / 2.0F, var8, var9, var9 / 2.0F, 1.0F, 352321535);
      if (var3) {
         float var11 = MotionStyle.reduced() ? 0.8F : 0.55F + 0.45F * (float)Math.sin(var6 * 3.2F);
         var1.glowCircle(var10 + 12.0F, var5, 2.5F, 6.0F, BlendMode.withAlpha(Icons.PINK, 0.5F * var11));
         var1.circle(var10 + 12.0F, var5, 2.5F, BlendMode.lerp(Icons.MAGENTA, Icons.PINK, var11));
      }

      var1.text(Fonts.MEDIUM, var2, var4 - 10.0F, var5, 10.5F, Icons.TEXT_2, FontStyle.RIGHT, FontWeight.MIDDLE);
   }

   private void footer(Canvas var1, ProfileSupport6 var2,  ProfileCodec2 var3, float var4, float var5, float var6, boolean var7) {
      ProfileLoader var8 = null;
      if (var3 != null) {
         if (var7 && var2.live != null) {
            var8 = var2.live.totals;
         } else {
            for (ProfileLoader2.TypeW var10 : var2.data.sessions) {
               if (var10.start == var3.start) {
                  var8 = var10.totals;
               }
            }
         }
      }

      float var15 = var4 + 20.0F;
      double var18 = var3 == null ? 0.0 : var3.length();
      var15 = this.stat(var1, "route", ProfileSupport2.distance(var8 != null ? var8.distance() : var18), var15, var5);
      var15 = this.stat(var1, "clock", ProfileSupport2.duration(var8 != null ? var8.play : (var3 == null ? 0.0 : var3.duration())), var15, var5);
      int var12 = 0;
      if (var3 != null) {
         for (ProfileCodec2.TypeB var14 : var3.marks) {
            if ("death".equals(var14.type())) {
               var12++;
            }
         }
      }

      this.stat(var1, "skull", Integer.toString(var12), var15, var5);
      if (var8 != null && var8.distance() > 1.0) {
         this.modes(var1, var8, var4 + var6 - 20.0F - Math.min(210.0F, var6 * 0.4F), var5, Math.min(210.0F, var6 * 0.4F));
      }
   }

   private float stat(Canvas var1, String var2, String var3, float var4, float var5) {
      var1.icon(var2, var4 + 6.0F, var5, 12.5F, Icons.TEXT_3);
      var1.text(Fonts.SEMIBOLD, var3, var4 + 17.0F, var5, 12.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      return var4 + 17.0F + var1.textWidth(Fonts.SEMIBOLD, var3, 12.0F) + 18.0F;
   }

   private void modes(Canvas var1, ProfileLoader var2, float var3, float var4, float var5) {
      double[] var6 = new double[]{var2.foot, var2.sprint, var2.glide, var2.ride, var2.swim, var2.fly};
      double var7 = var2.distance();
      int var9 = 0;

      for (int var10 = 1; var10 < var6.length; var10++) {
         if (var6[var10] > var6[var9]) {
            var9 = var10;
         }
      }

      String var17 = MODE_NAMES[var9];
      String var11 = var17 + "  " + Math.round(var6[var9] / var7 * 100.0) + "%";
      var1.text(Fonts.MEDIUM, var11, var3 + var5, var4 - 7.0F, 10.0F, Icons.TEXT_2, FontStyle.RIGHT, FontWeight.MIDDLE);
      float var12 = var4 + 4.0F;
      float var13 = 5.0F;
      var1.roundRect(var3, var12, var5, var13, var13 / 2.0F, 318767103);
      var1.pushClip(var3, var12, var5, var13, var13 / 2.0F);
      float var14 = var3;

      for (int var15 = 0; var15 < var6.length; var15++) {
         float var16 = (float)(var6[var15] / var7) * var5;
         if (var16 > 0.3F) {
            var1.rect(var14, var12, var16 + 0.5F, var13, Theme.tint(MODE_COLORS[var15]));
            var14 += var16;
         }
      }

      var1.popClip();
   }

   private void hover(DrawContext var1, Canvas var2, ProfileCodec2 var3, float var4, float var5, float var6, float var7, boolean var8) {
      boolean var9 = var8 && var1.hover(this.mapKey, var4, var5, var6, var7, DrawContext.TypeE.ARROW);
      if (var9) {
         float var10 = 196.0F;
         this.hoverFound = false;

         for (ProfileCodec2.TypeY var12 : var3.segments) {
            if (var12.dimension.equals(this.dimension)) {
               for (int var13 = 0; var13 < var12.size(); var13++) {
                  float var14 = var4 + this.mapX(var12.xs.getFloat(var13), var6);
                  float var15 = var5 + this.mapY(var12.zs.getFloat(var13), var7);
                  float var16 = (var14 - var1.mouseX) * (var14 - var1.mouseX) + (var15 - var1.mouseY) * (var15 - var1.mouseY);
                  if (var16 < var10) {
                     var10 = var16;
                     this.hoverX = var14;
                     this.hoverY = var15;
                     this.hoverTime = var12.times.getInt(var13);
                     this.hoverFound = true;
                  }
               }
            }
         }
      }

      float var17 = this.hoverAlpha.target(var9 && this.hoverFound ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      if (!(var17 <= 0.01F)) {
         var2.pushAlpha(var17);
         var2.glowCircle(this.hoverX, this.hoverY, 3.0F, 10.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.6F));
         var2.circle(this.hoverX, this.hoverY, 3.5F, -1);
         var2.popAlpha();
         LocalDateTime var18 = LocalDateTime.ofInstant(Instant.ofEpochMilli(var3.start + (long)this.hoverTime * 1000L), ZoneId.systemDefault());
         String var19 = String.format(Locale.ROOT, "%02d:%02d", var18.getHour(), var18.getMinute());
         ProfileRenderer9.tooltip(
            var2, this.hoverX, this.hoverY - 4.0F, "You were here at " + var19, ProfileSupport2.duration(this.hoverTime) + " into the session", var17
         );
      }
   }

   private void empty(Canvas var1, float var2, float var3, float var4, float var5, float var6) {
      float var7 = var2 + var4 / 2.0F;
      float var8 = var3 + var5 / 2.0F;
      float var9 = MotionStyle.reduced() ? 0.0F : var6 * 18.0F;

      for (int var10 = 0; var10 < 14; var10++) {
         float var11 = var10 / 13.0F;
         float var12 = var7 - 90.0F + var11 * 180.0F;
         float var13 = var8 - 22.0F + (float)Math.sin(var11 * 5.2F) * 10.0F;
         float var14 = 0.12F + 0.3F * (float)Math.max(0.0, Math.sin((var11 * 180.0F - var9) * 0.035F));
         var1.circle(var12, var13, 1.6F, BlendMode.withAlpha(Theme.tint(-4611329), var14));
      }

      var1.text(Fonts.MEDIUM, "Your route draws itself as you move", var7, var8 + 10.0F, 12.0F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
      var1.text(
         Fonts.REGULAR, "Only its shape is kept, never a coordinate on screen", var7, var8 + 28.0F, 10.5F, Icons.TEXT_3, FontStyle.CENTER, FontWeight.MIDDLE
      );
   }

   private static int niceScale(float var0) {
      for (int var4 : SCALES) {
         if (var4 >= var0) {
            return var4;
         }
      }

      return SCALES[SCALES.length - 1];
   }

   static String dimensionName(String var0) {
      return switch (var0) {
         case "minecraft:overworld" -> "Overworld";
         case "minecraft:the_nether" -> "The Nether";
         case "minecraft:the_end" -> "The End";
         default -> {
            String var4 = var0.substring(var0.indexOf(58) + 1).replace('_', ' ');
            yield var4.isEmpty() ? var0 : Character.toUpperCase(var4.charAt(0)) + var4.substring(1);
         }
      };
   }

   private static final class TypeT {
      final float[] x;
      final float[] y;
      final float[] t;
      final int n;

      TypeT(float[] var1, float[] var2, float[] var3, int var4) {
         this.x = var1;
         this.y = var2;
         this.t = var3;
         this.n = var4;
      }
   }
}
