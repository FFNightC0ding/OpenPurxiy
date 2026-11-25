package dev.purxiy.modules.coordinates;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudElementGroup;
import dev.purxiy.hud.HudModule;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.class_156;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2561;

public final class CoordinatesHud extends HudModule {
   private static final float t = 7.0F;
   private static final float q = 13.0F;
   private static final float e = 9.5F;
   private static final float i = 9.0F;
   private final ModeSetting v = this.mode("layout", "Layout", null, "Stacked", new String[]{"Stacked", "Single line"});
   private final ModeSetting u = this.mode("precision", "Precision", null, "Blocks", new String[]{"Blocks", "Decimals"});
   private final BooleanSetting P = this.bool("facing", "Facing", "The direction you are looking, like North (-Z)", true);
   private final BooleanSetting M = this.bool("biome", "Biome", null, false);
   private final BooleanSetting T = this.bool("other", "Other dimension", "Matching Overworld or Nether position", false);
   private final List<String[]> D = new ArrayList<>();
   private class_2338 L;
   private class_1937 w;
   private String A = "";
   public static boolean U;
   public static boolean n;

   public CoordinatesHud() {
      super("coordinates", "Coordinates", "Where you are, always in view", "navigation", 0.0, 0.0);
      this.enabledByDefault();
   }

   private String P(double var1, long var3) {
      return this.u.is("Blocks") ? Integer.toString((int)Math.floor(var1)) : String.format(Locale.ROOT, "%.1f", var1);
   }

   private void D(long var1, HudBounds var3) {
      long var4 = 80295919145199L;
      long var6 = 65781026583964L;
      this.D.clear();
      double var8 = 128.5;
      double var10 = 72.0;
      double var12 = -340.3;
      float var14 = 180.0F;
      String var15 = "Cherry Grove";
      boolean var16 = false;
      boolean var17 = var3.live();
      long var18 = 7750037655374L;
      int var41 = var18 < 0L ? -1 : 1;
      if (var17) {
         var8 = var3.player.method_23317();
         var10 = var3.player.method_23318();
         var12 = var3.player.method_23321();
         var14 = var3.player.method_5705(var3.partialTick);
         var16 = var3.level.method_27983() == class_1937.field_25180;
         long var21 = 7750037655375L;
         int var42 = var21 < 0L ? -1 : 1;
         long var23 = 7750037655375L;
         int var43 = var23 < 0L ? -1 : 1;
         if (this.M.enabled()) {
            class_2338 var25 = var3.player.method_24515();
            long var26 = 7750037655374L;
            int var27 = var26 < 0L ? -1 : 1;
            if (!var25.equals(this.L) || var3.level != this.w) {
               this.L = var25;
               this.w = var3.level;
               this.A = var3.level
                  .method_23753(var25)
                  .method_40230()
                  .map(var0 -> class_2561.method_43471(class_156.method_646("biome", var0.method_29177())).getString())
                  .orElse("Unknown");
            }

            var15 = this.A;
         }
      }

      this.D.add(new String[]{"X", this.P(var8, var6)});
      List var41x = this.D;
      String[] var22 = new String[]{"Y", null};
      long var42 = 7750037655374L;
      int var431 = var42 < 0L ? -1 : 1;
      var22[1] = this.P(var10, var6);
      var41x.add(var22);
      this.D.add(new String[]{"Z", this.P(var12, var6)});
      long var43x = 7750037655375L;
      int var28 = var43x < 0L ? -1 : 1;
      if (this.P.enabled()) {
         this.D.add(new String[]{"@compass", L(var4, var14)});
      }

      var17 = this.M.enabled();
      long var28x = 7750037655374L;
      if (var17) {
         this.D.add(new String[]{"@trees", var15});
      }

      if (this.T.enabled()) {
         double var30x = var16 ? 8.0 : 0.125;
         List var32 = this.D;
         String[] var33 = new String[2];
         long var34 = 7750037655374L;
         int var34x = var34 < 0L ? -1 : 1;
         var33[0] = "@globe";
         long var37x = 7750037655374L;
         int var36 = var37x < 0L ? -1 : 1;
         var33[1] = (var16 ? "Overworld  " : "Nether  ") + this.P(var8 * var30x, var6) + ", " + this.P(var12 * var30x, var6);
         var32.add(var33);
      }
   }

   private static String L(long var0, float var2) {
      float var3 = (var2 % 360.0F + 360.0F) % 360.0F;
      int var4 = Math.round(var3 / 90.0F) % 4;
      long var5 = 80295919145199L;
      int var7 = var5 < 0L ? -1 : 1;

      return switch (var4) {
         case 0 -> "South  +Z";
         case 1 -> "West  -X";
         case 2 -> "North  -Z";
         default -> "East  +X";
      };
   }

   private boolean u(long var1) {
      return this.v.is("Single line");
   }

   protected boolean mergeable() {
      return true;
   }

   protected float cornerRadius() {
      long var1 = 83844129632745L;
      long var3 = var1 ^ 116076312021056L;
      return this.u(var3) ? 10.0F : 7.0F;
   }

   protected void measure(HudBounds var1, float[] var2) {
      long var3 = 110101566893888L;
      long var5 = var3 ^ 72500658624233L;
      long var7 = var3 ^ 109055510405135L;
      this.D(var7, var1);
      if (this.u(var5)) {
         float var9 = 7.0F;

         for (int var10 = 0; var10 < 3; var10++) {
            var9 += Fonts.SEMIBOLD.width(this.D.get(var10)[0], 9.0F) + 4.0F + Fonts.MEDIUM.width(HudElementGroup.stable(this.D.get(var10)[1]), 9.5F);
            var9 += var10 < 2 ? 9.0F : 0.0F;
         }

         var2[0] = var9 + 7.0F;
         var2[1] = 20.0F;
      } else {
         float var14 = 60.0F;

         for (String[] var11 : this.D) {
            float var12 = var11[0].startsWith("@") ? 14.0F : Fonts.SEMIBOLD.width(var11[0], 9.0F) + 6.0F;
            var14 = Math.max(var14, var12 + Fonts.MEDIUM.width(HudElementGroup.stable(var11[1]), 9.5F));
         }

         var2[0] = 7.0F + var14 + 7.0F;
         var2[1] = 5.0F + this.D.size() * 13.0F + 5.0F;
      }
   }

   protected void render(Canvas var1, HudBounds var2) {
      long var3 = 53531637551652L;
      long var5 = var3 ^ 23625188371341L;
      float var7 = this.contentWidth();
      float var8 = this.contentHeight();
      this.panel(var1, 0.0F, 0.0F, var7, var8, this.cornerRadius());
      if (this.u(var5)) {
         float var9 = 7.0F;

         for (int var10 = 0; var10 < 3; var10++) {
            var9 += this.text(var1, Fonts.SEMIBOLD, this.D.get(var10)[0], var9, 10.0F, 9.0F, accent(), FontStyle.LEFT, FontWeight.MIDDLE) + 4.0F;
            this.text(var1, Fonts.MEDIUM, this.D.get(var10)[1], var9, 10.0F, 9.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
            var9 += Fonts.MEDIUM.width(HudElementGroup.stable(this.D.get(var10)[1]), 9.5F);
            if (var10 < 2) {
               var1.circle(var9 + 4.5F, 10.0F, 0.9F, Icons.TEXT_3);
               var9 += 9.0F;
            }
         }
      } else {
         float var13 = 11.5F;

         for (int var14 = 0; var14 < this.D.size(); var14++) {
            String[] var11 = this.D.get(var14);
            if (var14 == 3) {
               var1.rect(7.0F, var13 - 6.5F - 0.5F, var7 - 14.0F, 1.0F, 318767103);
            }

            if (var11[0].startsWith("@")) {
               this.icon(var1, var11[0].substring(1), 11.5F, var13, 9.0F, accent());
               this.text(var1, Fonts.MEDIUM, var11[1], 21.0F, var13, 9.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
            } else {
               this.text(var1, Fonts.SEMIBOLD, var11[0], 7.0F, var13, 9.0F, accent(), FontStyle.LEFT, FontWeight.MIDDLE);
               this.text(var1, Fonts.MEDIUM, var11[1], 19.0F, var13, 9.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
            }

            var13 += 13.0F;
         }
      }
   }
}
