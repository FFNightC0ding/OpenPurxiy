// Sourced By DevFFNightmare
package dev.purxiy.ui.config;

import dev.purxiy.anim.MotionStyle;
import dev.purxiy.modules.Category;
import dev.purxiy.modules.Module;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class ConfigLibraryRenderer7 {
   static final float WIDTH = 2.0F;
   private static final float EDGE = 0.075F;
   private static final float GOLDEN = 2.3999631F;
   private static final float STEP = 0.16F;
   private static ConfigLibraryRenderer7.TypeW[] stars = new ConfigLibraryRenderer7.TypeW[0];
   private static ConfigLibraryRenderer7.TypeZ[] clusters = new ConfigLibraryRenderer7.TypeZ[0];
   private static final Map<String, Integer> INDEX = new HashMap<>();
   private static boolean built;
   private static final float[] POINT = new float[2];

   private ConfigLibraryRenderer7() {
   }

   static ConfigLibraryRenderer7.TypeW[] stars() {
      build();
      return stars;
   }

   static int indexOf(String var0) {
      build();
      Integer var1 = INDEX.get(var0);
      return var1 == null ? -1 : var1;
   }

   private static void build() {
      if (!built && !SettingSection.all().isEmpty()) {
         built = true;
         ArrayList var0 = new ArrayList();
         ArrayList var1 = new ArrayList();
         ArrayList var2 = new ArrayList();

         for (Category var6 : Category.values()) {
            ArrayList var7 = new ArrayList();

            for (Module var9 : SettingSection.of(var6)) {
               if (var9.toggleable()) {
                  var7.add(var9);
               }
            }

            if (!var7.isEmpty()) {
               var1.add(new ConfigLibraryRenderer7.TypeZ(var6, 0, var7.size()));
               var2.add(var7);
            }
         }

         int var21 = var1.size();
         float[] var22 = new float[var21];
         float[] var23 = new float[var21];

         for (int var24 = 0; var24 < var21; var24++) {
            ConfigLibraryRenderer7.TypeZ var27 = (ConfigLibraryRenderer7.TypeZ)var1.get(var24);
            var27.r = 0.038F * (float)Math.sqrt(var27.count) + 0.04F;
            float var31 = var21 == 1 ? 0.5F : (float)var24 / (var21 - 1);
            var22[var24] = 0.275F + var31 * 1.45F;
            var23[var24] = (var24 % 2 == 0 ? 0.34F : 0.66F) + (hash(var24 * 31 + 7) - 0.5F) * 0.08F;
            var27.x = var22[var24];
            var27.y = var23[var24];
         }

         for (int var25 = 0; var25 < 160; var25++) {
            for (int var28 = 0; var28 < var21; var28++) {
               for (int var32 = var28 + 1; var32 < var21; var32++) {
                  ConfigLibraryRenderer7.TypeZ var35 = (ConfigLibraryRenderer7.TypeZ)var1.get(var28);
                  ConfigLibraryRenderer7.TypeZ var10 = (ConfigLibraryRenderer7.TypeZ)var1.get(var32);
                  float var11 = var10.x - var35.x;
                  float var12 = (var10.y - var35.y) * 1.15F;
                  float var13 = (float)Math.sqrt(var11 * var11 + var12 * var12);
                  float var14 = (var35.r + var10.r) * 0.94F;
                  if (var13 < var14 && var13 > 1.0E-4F) {
                     float var15 = (var14 - var13) * 0.5F / var13;
                     var35.x -= var11 * var15;
                     var35.y -= var12 * var15 * 0.6F;
                     var10.x += var11 * var15;
                     var10.y += var12 * var15 * 0.6F;
                  }
               }
            }

            for (int var29 = 0; var29 < var21; var29++) {
               ConfigLibraryRenderer7.TypeZ var33 = (ConfigLibraryRenderer7.TypeZ)var1.get(var29);
               var33.x = var33.x + (var22[var29] - var33.x) * 0.02F;
               var33.y = var33.y + (var23[var29] - var33.y) * 0.02F;
               var33.x = clamp(var33.x, 0.075F + var33.r, 1.925F - var33.r);
               var33.y = clamp(var33.y, 0.075F + var33.r * 0.82F, 0.925F - var33.r * 0.82F);
            }
         }

         ConfigLibraryRenderer7.TypeZ[] var26 = new ConfigLibraryRenderer7.TypeZ[var21];

         for (int var30 = 0; var30 < var21; var30++) {
            ConfigLibraryRenderer7.TypeZ var34 = (ConfigLibraryRenderer7.TypeZ)var1.get(var30);
            List var36 = (List)var2.get(var30);
            ConfigLibraryRenderer7.TypeZ var37 = new ConfigLibraryRenderer7.TypeZ(var34.category, var0.size(), var36.size());
            var37.x = var34.x;
            var37.y = var34.y;
            var37.r = var34.r;
            var26[var30] = var37;
            float var38 = hash(var34.category.ordinal() * 977 + 3) * (float) (Math.PI * 2);

            for (int var39 = 0; var39 < var36.size(); var39++) {
               Module var40 = (Module)var36.get(var39);
               int var41 = var40.id().hashCode();
               float var42 = var34.r * (float)Math.sqrt((var39 + 0.6F) / var36.size()) * 0.9F;
               float var16 = var38 + var39 * 2.3999631F + (hash(var41) - 0.5F) * 0.7F;
               float var17 = var34.r / (float)Math.sqrt(var36.size()) * 0.22F;
               float var18 = var34.x + (float)Math.cos(var16) * var42 + (hash(var41 * 3 + 1) - 0.5F) * var17;
               float var19 = var34.y + (float)Math.sin(var16) * var42 * 0.82F + (hash(var41 * 5 + 2) - 0.5F) * var17;
               float var20 = hash(var41 * 7 + 3);
               var20 = var39 == 0 ? 0.75F + var20 * 0.25F : var20 * var20 * 0.8F;
               INDEX.put(var40.id(), var0.size());
               var0.add(new ConfigLibraryRenderer7.TypeW(var40, var30, var18, var19, var20, hash(var41 * 11 + 5), 0.45F + 0.55F * hash(var41 * 13 + 7)));
            }
         }

         stars = (TypeW[])(var0.toArray(new ConfigLibraryRenderer7.TypeW[0]));
         clusters = var26;
      }
   }

   static ConfigLibraryRenderer7.TypeA figure(Collection<String> var0) {
      build();
      int var1 = stars.length;
      boolean[] var2 = new boolean[var1];

      for (String var4 : var0) {
         Integer var5 = INDEX.get(var4);
         if (var5 != null) {
            var2[var5] = true;
         }
      }

      float[] var32 = new float[var1];
      Arrays.fill(var32, 2.0F);
      ArrayList var33 = new ArrayList();
      ArrayList var34 = new ArrayList();
      ArrayList var6 = new ArrayList();
      Integer[] var7 = new Integer[clusters.length];

      for (int var8 = 0; var8 < var7.length; var8++) {
         var7[var8] = var8;
      }

      Arrays.sort(var7, (var0x, var1x) -> Float.compare(clusters[var0x].x, clusters[var1x].x));
      int var35 = 0;

      for (boolean var12 : var2) {
         var35 += var12 ? 1 : 0;
      }

      int var36 = 0;
      int var37 = -1;
      float var38 = -1.0F;
      float var39 = Math.max(1, var35 - 1);
      Integer[] var13 = var7;
      int var14 = var7.length;

      for (int var15 = 0; var15 < var14; var15++) {
         int var16 = var13[var15];
         ConfigLibraryRenderer7.TypeZ var17 = clusters[var16];
         int[] var18 = new int[var17.count];
         int var19 = 0;

         for (int var20 = var17.first; var20 < var17.first + var17.count; var20++) {
            if (var2[var20]) {
               var18[var19++] = var20;
               if (stars[var20].size > var38) {
                  var38 = stars[var20].size;
                  var37 = var20;
               }
            }
         }

         if (var19 != 0) {
            int var45 = 0;

            for (int var21 = 1; var21 < var19; var21++) {
               if (stars[var18[var21]].x < stars[var18[var45]].x) {
                  var45 = var21;
               }
            }

            boolean[] var46 = new boolean[var19];
            int[] var22 = new int[var19];
            float[] var23 = new float[var19];
            Arrays.fill(var23, Float.MAX_VALUE);
            Arrays.fill(var22, -1);
            var23[var45] = 0.0F;
            float var24 = 0.0F;
            int var25 = 0;

            for (int var26 = 0; var26 < var19; var26++) {
               int var27 = -1;

               for (int var28 = 0; var28 < var19; var28++) {
                  if (!var46[var28] && (var27 < 0 || var23[var28] < var23[var27])) {
                     var27 = var28;
                  }
               }

               var46[var27] = true;
               int var49 = var18[var27];
               var32[var49] = 0.85F * var36++ / var39;
               if (var22[var27] >= 0) {
                  int var29 = var18[var22[var27]];
                  var33.add(var29);
                  var33.add(var49);
                  var34.add(var32[var29]);
                  var6.add(var32[var49]);
                  var24 += var23[var27];
                  var25++;
               }

               for (int var51 = 0; var51 < var19; var51++) {
                  if (!var46[var51]) {
                     float var30 = distance(stars[var49], stars[var18[var51]]);
                     if (var30 < var23[var51]) {
                        var23[var51] = var30;
                        var22[var51] = var27;
                     }
                  }
               }
            }

            if (var19 >= 5) {
               float var47 = Float.MAX_VALUE;
               int var48 = -1;
               int var50 = -1;

               for (int var52 = 0; var52 < var19; var52++) {
                  for (int var54 = var52 + 1; var54 < var19; var54++) {
                     if (var22[var52] != var54 && var22[var54] != var52) {
                        float var31 = distance(stars[var18[var52]], stars[var18[var54]]);
                        if (var31 < var47) {
                           var47 = var31;
                           var48 = var18[var52];
                           var50 = var18[var54];
                        }
                     }
                  }
               }

               if (var48 >= 0 && var47 < var24 / Math.max(1, var25) * 1.35F) {
                  var33.add(var48);
                  var33.add(var50);
                  float var53 = Math.max(var32[var48], var32[var50]);
                  var34.add(var53);
                  var6.add(var53 + 0.16F);
               }
            }
         }
      }

      int[] var40 = new int[var33.size()];
      float[] var41 = new float[var34.size()];
      float[] var42 = new float[var6.size()];

      for (int var43 = 0; var43 < var40.length; var43++) {
         var40[var43] = (Integer)var33.get(var43);
      }

      for (int var44 = 0; var44 < var41.length; var44++) {
         var41[var44] = (Float)var34.get(var44);
         var42[var44] = Math.max((Float)var6.get(var44), (Float)var34.get(var44) + 0.08F);
      }

      return new ConfigLibraryRenderer7.TypeA(var2, var32, var40, var41, var42, var35, var37);
   }

   static void draw(Canvas var0, ConfigLibraryFactory2 var1, float var2, float var3, float var4, float var5, ConfigLibraryRenderer7.TypeT var6) {
      draw(var0, var1, var1, 1.0F, var2, var3, var4, var5, var6);
   }

   static void draw(
      Canvas var0,
      ConfigLibraryFactory2 var1,
      ConfigLibraryFactory2 var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      ConfigLibraryRenderer7.TypeT var8
   ) {
      build();
      var3 = clamp(var3, 0.0F, 1.0F);
      boolean var9 = MotionStyle.reduced();
      float var10 = var8.time;
      int var11 = BlendMode.lerp(var1.accent(), var2.accent(), var3);
      int var12 = BlendMode.lerp(var1.accentDeep(), var2.accentDeep(), var3);
      if (var8.night) {
         night(var0, var1, var2, var3, var4, var5, var6, var7, var8, var11, var12);
      }

      if (var3 < 1.0F && var1 != var2) {
         dust(var0, var1, var4, var5, var6, var7, var8, 1.0F - var3, var9);
      }

      dust(var0, var2, var4, var5, var6, var7, var8, var1 == var2 ? 1.0F : var3, var9);
      float var13 = scale(var6, var7, var8);
      float var14 = clamp(var13 / 116.0F, 0.8F, 1.7F) * var8.starScale;
      ConfigLibraryRenderer7.TypeA var15 = var1.figure();
      ConfigLibraryRenderer7.TypeA var16 = var2.figure();
      int var17 = BlendMode.lerp(var11, -1, 0.38F);
      if (var8.unlit > 0.005F) {
         for (int var18 = 0; var18 < stars.length; var18++) {
            if (!var15.lit[var18] && !var16.lit[var18]) {
               point(var18, var4, var5, var6, var7, var8);
               var0.circle(
                  POINT[0],
                  POINT[1],
                  (0.55F + 0.45F * stars[var18].size) * var14 * 0.8F,
                  BlendMode.withAlpha(Theme.tint(-2501899), var8.unlit * (0.45F + 0.4F * stars[var18].size) * fadeLeft(POINT[0], var4, var8))
               );
            }
         }
      }

      float var40 = 0.42F + 0.26F * var8.hover;
      if (var15 == var16) {
         lines(var0, var15, var15, 1.0F, true, var4, var5, var6, var7, var8, var17, var40, var14);
      } else {
         lines(var0, var15, var16, 1.0F - var3, true, var4, var5, var6, var7, var8, var17, var40, var14);
         lines(var0, var16, var15, var3, false, var4, var5, var6, var7, var8, var17, var40, var14);
      }

      int var19 = BlendMode.lerp(-1, var11, 0.16F);

      for (int var20 = 0; var20 < stars.length; var20++) {
         float var21 = (var15.lit[var20] ? 1.0F - var3 : 0.0F) + (var16.lit[var20] ? var3 : 0.0F);
         if (!(var21 <= 0.005F)) {
            ConfigLibraryRenderer7.TypeA var22 = var16.lit[var20] ? var16 : var15;
            float var23 = clamp((var8.drawn - var22.at[var20]) / 0.16F, 0.0F, 1.0F);
            if (!(var23 <= 0.0F)) {
               ConfigLibraryRenderer7.TypeW var24 = stars[var20];
               point(var20, var4, var5, var6, var7, var8);
               float var25 = POINT[0];
               float var26 = POINT[1];
               float var27 = fadeLeft(var25, var4, var8);
               float var28 = var9 ? 1.0F : 0.86F + 0.14F * (float)Math.sin(var10 * (1.1F + var24.phase * 1.4F) + var24.phase * (float) (Math.PI * 2));
               float var29 = var15.lit[var20] != var16.lit[var20] ? 4.0F * var3 * (1.0F - var3) * (var16.lit[var20] ? 1 : 0) : 0.0F;
               float var30 = 1.0F + (1.0F - var23) * (1.0F - var23) * 1.6F * (var23 > 0.0F ? 1 : 0);
               float var31 = (1.05F + 0.85F * var24.size) * var14 * var30 * (1.0F + var29 * 0.9F);
               boolean var32 = var20 == var22.lead && var22.count > 2;
               if (var32) {
                  var31 *= 1.3F;
               }

               float var33 = var21 * Math.min(1.0F, var23 * 1.6F) * var27;
               float var34 = !(var24.size > 0.5F) && !var32 ? 4.2F : 7.0F;
               var0.glowCircle(
                  var25,
                  var26,
                  var31 * 0.6F,
                  var31 * (var34 + 2.0F * var8.hover),
                  BlendMode.withAlpha(var11, (0.26F + 0.16F * var8.hover + var29 * 0.5F) * var33 * var28)
               );
               var0.circle(var25, var26, var31, BlendMode.withAlpha(var19, var33 * var28));
               if (var32 && var21 > 0.5F) {
                  float var35 = var31 * (5.5F + 1.5F * var8.hover);
                  int var36 = BlendMode.withAlpha(var19, 0.55F * var33 * var28);
                  int var37 = BlendMode.withAlpha(var19, 0.0F);
                  float var38 = Math.max(0.55F, 0.42F * var14);
                  var0.line(var25 - var35, var26, var25, var26, var38, var37, var36);
                  var0.line(var25, var26, var25 + var35, var26, var38, var36, var37);
                  var0.line(var25, var26 - var35, var25, var26, var38, var37, var36);
                  var0.line(var25, var26, var25, var26 + var35, var38, var36, var37);
               }
            }
         }
      }

      if (var8.highlight >= 0 && var8.highlight < stars.length) {
         point(var8.highlight, var4, var5, var6, var7, var8);
         float var41 = var9 ? 0.5F : 0.5F + 0.5F * (float)Math.sin(var10 * 4.2F);
         float var42 = (5.5F + var41 * 1.5F) * var14;
         var0.glowCircle(POINT[0], POINT[1], var42 * 0.6F, var42 * 2.4F, BlendMode.withAlpha(Icons.MAGENTA, 0.35F));
         var0.ring(POINT[0], POINT[1], var42, 1.3F, BlendMode.withAlpha(Icons.PINK, 0.9F));
      }
   }

   private static void night(
      Canvas var0,
      ConfigLibraryFactory2 var1,
      ConfigLibraryFactory2 var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      ConfigLibraryRenderer7.TypeT var8,
      int var9,
      int var10
   ) {
      int var11 = BlendMode.lerp(var1.night(), var2.night(), var3);
      int var12 = BlendMode.shade(var11, 0.05F);
      float var13 = var7 * (1.0F - var8.fadeBottom);
      if (var8.fadeLeft > 0.0F) {
         float var14 = Math.min(var8.fadeLeft, var6);
         var0.roundRectCorners(var4, var5, var14, var7, 0.0F, BlendMode.withAlpha(var11, 0.0F), var11, var12, BlendMode.withAlpha(var12, 0.0F));
         var0.roundRectCorners(var4 + var14, var5, var6 - var14, var7, 0.0F, var11, var11, var12, var12);
      } else if (var8.fadeBottom <= 0.0F) {
         var0.roundRectCorners(var4, var5, var6, var7, 0.0F, var11, var11, var12, var12);
      } else {
         float var22 = var0.pixelScale();
         float[] var15 = new float[]{0.0F, 1.0F - var8.fadeBottom, 1.0F - var8.fadeBottom * 0.55F, 1.0F - var8.fadeBottom * 0.22F, 1.0F};
         float[] var16 = new float[]{1.0F, 1.0F, 0.42F, 0.1F, 0.0F};

         for (int var17 = 0; var17 < 4; var17++) {
            float var18 = Math.round((var5 + var7 * var15[var17]) * var22) / var22;
            float var19 = Math.round((var5 + var7 * var15[var17 + 1]) * var22) / var22;
            int var20 = var17 == 0 ? var11 : BlendMode.fade(var12, var16[var17]);
            int var21 = BlendMode.fade(var12, var16[var17 + 1]);
            var0.roundRectCorners(var4, var18, var6, var19 - var18, 0.0F, var20, var20, var21, var21);
         }
      }

      float var23 = lerp(nebula(var1, 0), nebula(var2, 0), var3);
      float var24 = lerp(nebula(var1, 1), nebula(var2, 1), var3);
      float var25 = lerp(nebula(var1, 2), nebula(var2, 2), var3);
      float var26 = lerp(nebula(var1, 3), nebula(var2, 3), var3);
      float var27 = MotionStyle.reduced() ? 0.0F : (float)Math.sin(var8.time * 0.21F) * 0.03F;
      float var28 = var8.fadeLeft > 0.0F ? 0.7F : 1.0F;
      var0.glowCircle(var4 + var6 * (var23 + var27), var5 + var7 * var24, var7 * 0.3F, var7 * 1.05F, BlendMode.withAlpha(var9, 0.3F * var28));
      var0.glowCircle(var4 + var6 * (var25 - var27), var5 + var7 * var26, var7 * 0.26F, var7 * 0.9F, BlendMode.withAlpha(var10, 0.26F * var28));
      float var29 = var5 + var7 * (1.0F - var8.fadeBottom * 0.85F);
      var0.glowCircle(var4 + var6 * 0.5F, var29 + var7 * 0.2F, var6 * 0.32F, var7 * 0.9F, BlendMode.withAlpha(var10, 0.16F * var28));
   }

   private static float nebula(ConfigLibraryFactory2 var0, int var1) {
      int var2 = var0.id.hashCode();

      return switch (var1) {
         case 0 -> 0.18F + 0.3F * hash(var2 + 11);
         case 1 -> 0.3F + 0.4F * hash(var2 + 13);
         case 2 -> 0.55F + 0.3F * hash(var2 + 17);
         default -> 0.35F + 0.45F * hash(var2 + 19);
      };
   }

   private static void dust(
      Canvas var0, ConfigLibraryFactory2 var1, float var2, float var3, float var4, float var5, ConfigLibraryRenderer7.TypeT var6, float var7, boolean var8
   ) {
      if (!(var7 <= 0.01F)) {
         int var9 = var1.id.hashCode() * 73244475;
         int var10 = (int)clamp(var4 * var5 / 620.0F, 12.0F, 110.0F);
         float var11 = var6.pointerX * var6.hover * 1.6F;
         float var12 = var6.pointerY * var6.hover * 1.2F;

         for (int var13 = 0; var13 < var10; var13++) {
            float var14 = var2 + hash(var9 + var13 * 4) * var4 + var11;
            float var15 = var3 + hash(var9 + var13 * 4 + 1) * var5 * (1.0F - var6.fadeBottom * 0.5F) + var12;
            float var16 = hash(var9 + var13 * 4 + 2);
            float var17 = hash(var9 + var13 * 4 + 3) * (float) (Math.PI * 2);
            float var18 = var8 ? 0.7F : 0.55F + 0.45F * (float)Math.sin(var6.time * (0.6F + var16 * 1.7F) + var17);
            float var19 = (0.1F + 0.34F * var16 * var16) * var18 * var7 * fadeLeft(var14, var2, var6);
            if (var19 > 0.01F) {
               var0.circle(var14, var15, 0.32F + 0.42F * var16, BlendMode.withAlpha(-1, var19));
            }
         }
      }
   }

   private static void lines(
      Canvas var0,
      ConfigLibraryRenderer7.TypeA var1,
      ConfigLibraryRenderer7.TypeA var2,
      float var3,
      boolean var4,
      float var5,
      float var6,
      float var7,
      float var8,
      ConfigLibraryRenderer7.TypeT var9,
      int var10,
      float var11,
      float var12
   ) {
      float var13 = Math.max(0.7F, 0.95F * var12);
      byte var14 = 0;

      for (int var15 = 0; var14 < var1.lines.length; var15++) {
         label27: {
            int var16 = var1.lines[var14];
            int var17 = var1.lines[var14 + 1];
            float var18 = var3;
            if (var1 != var2 && var2.hasLine(var16, var17)) {
               if (!var4) {
                  break label27;
               }

               var18 = 1.0F;
            }

            if (!(var18 <= 0.01F)) {
               float var19 = clamp((var9.drawn - var1.lineFrom[var15]) / (var1.lineTo[var15] - var1.lineFrom[var15]), 0.0F, 1.0F);
               if (!(var19 <= 0.0F)) {
                  point(var16, var5, var6, var7, var8, var9);
                  float var20 = POINT[0];
                  float var21 = POINT[1];
                  point(var17, var5, var6, var7, var8, var9);
                  float var22 = POINT[0];
                  float var23 = POINT[1];
                  float var24 = var22 - var20;
                  float var25 = var23 - var21;
                  float var26 = (float)Math.sqrt(var24 * var24 + var25 * var25);
                  if (!(var26 < 6.0F)) {
                     float var27 = Math.min(var26 * 0.25F, 3.2F * var12);
                     float var28 = var24 / var26;
                     float var29 = var25 / var26;
                     float var30 = var20 + var28 * var27;
                     float var31 = var21 + var29 * var27;
                     float var32 = var20 + var28 * (var27 + (var26 - var27 * 2.0F) * var19);
                     float var33 = var21 + var29 * (var27 + (var26 - var27 * 2.0F) * var19);
                     float var34 = Math.min(fadeLeft(var30, var5, var9), fadeLeft(var32, var5, var9));
                     var0.line(var30, var31, var32, var33, var13 * 3.6F, BlendMode.withAlpha(var10, var11 * var18 * var34 * 0.16F));
                     var0.line(var30, var31, var32, var33, var13, BlendMode.withAlpha(var10, var11 * var18 * var34));
                  }
               }
            }
         }

         var14 += 2;
      }
   }

   private static float scale(float var0, float var1, ConfigLibraryRenderer7.TypeT var2) {
      return (var2.fill ? Math.max(var0 / 2.0F, var1) : Math.min(var0 / 2.0F, var1)) * var2.zoom;
   }

   private static void point(int var0, float var1, float var2, float var3, float var4, ConfigLibraryRenderer7.TypeT var5) {
      float var6 = scale(var3, var4, var5);
      float var7 = var1 + var3 / 2.0F - 2.0F * var6 / 2.0F;
      float var8 = var2 + var4 / 2.0F - var6 / 2.0F;
      ConfigLibraryRenderer7.TypeW var9 = stars[var0];
      float var10 = var9.depth * var5.hover * 3.2F;
      POINT[0] = var7 + var9.x * var6 + var5.pointerX * var10;
      POINT[1] = var8 + var9.y * var6 + var5.pointerY * var10 * 0.8F;
   }

   static int starAt(
      ConfigLibraryFactory2 var0, float var1, float var2, float var3, float var4, ConfigLibraryRenderer7.TypeT var5, float var6, float var7, float var8
   ) {
      build();
      ConfigLibraryRenderer7.TypeA var9 = var0.figure();
      int var10 = -1;
      float var11 = var8 * var8;

      for (int var12 = 0; var12 < stars.length; var12++) {
         if (var9.lit[var12] || !(var5.unlit <= 0.005F)) {
            point(var12, var1, var2, var3, var4, var5);
            float var13 = POINT[0] - var6;
            float var14 = POINT[1] - var7;
            float var15 = var13 * var13 + var14 * var14;
            if (var15 < var11) {
               var11 = var15;
               var10 = var12;
            }
         }
      }

      return var10;
   }

   static float[] position(int var0, float var1, float var2, float var3, float var4, ConfigLibraryRenderer7.TypeT var5) {
      build();
      point(var0, var1, var2, var3, var4, var5);
      return new float[]{POINT[0], POINT[1]};
   }

   private static float fadeLeft(float var0, float var1, ConfigLibraryRenderer7.TypeT var2) {
      return var2.fadeLeft <= 0.0F ? 1.0F : smooth((var0 - var1) / var2.fadeLeft);
   }

   private static float distance(ConfigLibraryRenderer7.TypeW var0, ConfigLibraryRenderer7.TypeW var1) {
      float var2 = var0.x - var1.x;
      float var3 = var0.y - var1.y;
      return (float)Math.sqrt(var2 * var2 + var3 * var3);
   }

   static float hash(int var0) {
      var0 = var0 ^ 61 ^ var0 >>> 16;
      var0 *= 9;
      var0 ^= var0 >>> 4;
      var0 *= 668265261;
      var0 ^= var0 >>> 15;
      return (var0 & 16777215) / 1.6777216E7F;
   }

   static float clamp(float var0, float var1, float var2) {
      return var0 < var1 ? var1 : Math.min(var0, var2);
   }

   private static float lerp(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   private static float smooth(float var0) {
      var0 = clamp(var0, 0.0F, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   static final class TypeA {
      final boolean[] lit;
      final float[] at;
      final int[] lines;
      final float[] lineFrom;
      final float[] lineTo;
      final int count;
      final int lead;

      TypeA(boolean[] var1, float[] var2, int[] var3, float[] var4, float[] var5, int var6, int var7) {
         this.lit = var1;
         this.at = var2;
         this.lines = var3;
         this.lineFrom = var4;
         this.lineTo = var5;
         this.count = var6;
         this.lead = var7;
      }

      boolean hasLine(int var1, int var2) {
         for (byte var3 = 0; var3 < this.lines.length; var3 += 2) {
            if (this.lines[var3] == var1 && this.lines[var3 + 1] == var2 || this.lines[var3] == var2 && this.lines[var3 + 1] == var1) {
               return true;
            }
         }

         return false;
      }
   }

   static final class TypeT {
      float time;
      float drawn = 1.0F;
      float hover;
      float pointerX;
      float pointerY;
      float unlit;
      boolean fill;
      boolean night = true;
      float fadeBottom;
      float fadeLeft;
      int highlight = -1;
      float starScale = 1.0F;
      float zoom = 1.0F;
   }

   static final class TypeW {
      final Module module;
      final int cluster;
      final float x;
      final float y;
      final float size;
      final float phase;
      final float depth;

      TypeW(Module var1, int var2, float var3, float var4, float var5, float var6, float var7) {
         this.module = var1;
         this.cluster = var2;
         this.x = var3;
         this.y = var4;
         this.size = var5;
         this.phase = var6;
         this.depth = var7;
      }
   }

   static final class TypeZ {
      final Category category;
      final int first;
      final int count;
      float x;
      float y;
      float r;

      TypeZ(Category var1, int var2, int var3) {
         this.category = var1;
         this.first = var2;
         this.count = var3;
      }
   }
}
