// Sourced By DevFFNightmare
package dev.purxiy.modules.nametags;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.anim.Easing;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1044;
import net.minecraft.class_12137;
import net.minecraft.class_2960;
import net.minecraft.class_310;

public final class TagPainter {
   public static final float W = 9.0F;
   public static final float l = 7.0F;
   public static final float E = 8.0F;
   public static final int H = 4;
   public static final float X = 118.0F;
   private static final float s = 17.0F;
   private static final float w = 6.0F;
   private static final float I = 27.0F;
   private static final float B = 8.0F;
   private static final float m = 3.0F;
   private static final float Y = 7.0F;
   private static final float V = 2.0F;
   private static final float n = 7.5F;
   private static final float S = 15.0F;
   private static final float O = 5.5F;
   private static final float r = 12.0F;
   private static final float P = 7.5F;
   private static final float a = 13.5F;
   private static final int K = -11654;
   private static final int U = -41852;
   private static final int z = -30116;
   private static final int F = -1711290648;
   public static int g = 0;
   private static final String b = " ♥";
   private static final long[] c = new long[]{
      -8850582999523952428L,
      6679337349184755490L,
      -9196496205887039659L,
      2960374800920791029L,
      -105759765960989425L,
      -7811902198247935331L,
      1813059860662339196L,
      -4818337146754848154L,
      -3667985015201636301L,
      4213037290037814953L,
      2736743662484881981L,
      8978710764073118733L,
      -6503201669237388311L,
      -18916130034403675L,
      5095298999518465227L,
      -846667027240638874L,
      -359828249075812102L,
      7161511231061144748L,
      8028399827244358834L,
      -2018482843015011734L,
      -3767728327782921894L,
      -5693835593344923554L,
      4836229643903477240L,
      -5837061232201072355L,
      4864441069281849220L,
      2505409158578655285L,
      6926122380309385749L,
      5519882819420270537L,
      790738693969885319L,
      1943937055689611950L,
      7672929357101864763L,
      630895073926859685L,
      5794218394888785645L,
      -4459592914770193508L
   };


   private TagPainter() {
   }

   public static float measure(TagPainter.TypeQ var0) {
      long var1 = 55580168463164L;
      long var3 = var1 ^ 50677415412464L;
      long var5 = var1 ^ 132767789609255L;
      boolean var7 = var0.m != null;
      float var8 = var7 ? 27.0F : 17.0F;
      float var9 = V(var5, var0, var8);
      float var10 = var0.g.K + w(var0, var3);
      if (var7) {
         var10 = Math.max(var10, var0.m.K);
      }

      var0.A = var8;
      var0.L = Math.max(var8, var9 + var10 + 7.0F);
      return var0.L;
   }

   public static float height(TagPainter.TypeQ var0) {
      return var0.A;
   }

   public static void draw(Canvas var0, TagPainter.TypeQ var1, TagPainter.Look var2, float var3) {
      long var4 = 57018896886002L;
      long var6 = var4 ^ 69340648091935L;
      long var8 = var4 ^ 112346310156752L;
      long var10 = var4 ^ 96770146640971L;
      long var12 = var4 ^ 11958998827629L;
      int var14 = (int)((var4 ^ 11958998827629L) >>> 56);
      int var15 = (int)((var4 ^ 11958998827629L) << 8 >>> 32);
      int var16 = (int)(var12 << 40 >>> 40);
      var12 = var4 ^ 137150990909537L;
      int var17 = (int)((var4 ^ 137150990909537L) >>> 32);
      int var18 = (int)((var4 ^ 137150990909537L) << 32 >>> 56);
      int var19 = (int)(var12 << 40 >>> 40);
      long var20 = var4 ^ 133689778731753L;
      long var22 = var4 ^ 8464549580514L;
      boolean var24 = var1.m != null;
      float var25 = var1.L;
      float var26 = var1.A;
      float var27 = var24 ? 8.0F : 6.0F;
      boolean var28 = var2 == TagPainter.Look.h;
      boolean var29 = var1.R == 1;
      var0.push();
      var0.translate(-var25 / 2.0F, -var26);
      if (!var28) {
         O(var0, var17, var25, (byte)var18, var19, var26, var27, var2, var3);
      }

      if (var1.s > 0.01F) {
         float var30 = var1.s * var1.s;
         if (var28) {
            var0.glow(0.0F, 0.0F, var25, var26, var27, 12.0F, BlendMode.withAlpha(Icons.PINK, 0.16F * var30));
         } else {
            var0.glow(0.0F, 0.0F, var25, var26, var27, 12.0F, BlendMode.withAlpha(Icons.PINK, 0.2F * var30));
            var0.roundRectOutline(0.0F, 0.0F, var25, var26, var27, 1.0F, BlendMode.withAlpha(-41852, 0.7F * var30));
         }
      }

      float var35 = V(var20, var1, var26);
      float var31 = var24 ? 9.5F : var26 / 2.0F - (var29 && !var28 ? 0.75F : 0.0F);
      if (var1.r != null || var1.K != null) {
         float var32 = var26 - 6.0F;
         float var33 = (var24 ? 8.0F : 6.0F) - 3.0F;
         if (var1.r != null) {
            T(var0, var1.r, 3.0F, 3.0F, var6, var32, var33, var28);
         } else {
            V(var0, var1.K, var10, 3.0F, 3.0F, var32, var33, var28);
         }
      }

      if (var28) {
         var1.g.drawShadow(var0, var35, var31, 1.0F);
      }

      var1.g.draw(var0, var35, var31);
      if (var24) {
         float var36 = 19.0F;
         if (var28) {
            var1.m.drawShadow(var0, var35, var36, 1.0F);
         }

         var1.m.draw(var0, var35, var36);
      }

      if (var1.R == 2) {
         C(var0, var22, var1, var35 + var1.g.K + 6.0F, var31, var28);
      } else if (var29) {
         if (var28) {
            k(var0, var1, var8, var35, var31 + Fonts.SEMIBOLD.capHeight * 9.0F / 2.0F + 3.0F, var1.g.K);
         } else {
            T(var0, var1, (byte)var14, var25, var26, var15, var27, var16);
         }
      }

      var0.pop();
   }

   private static float V(long var0, TagPainter.TypeQ var2, float var3) {
      if (var2.r == null && var2.K == null) {
         return 7.0F;
      } else {
         boolean var4 = var2.m != null;
         return 3.0F + (var3 - 6.0F) + (var4 ? 6 : 5);
      }
   }

   private static float w(TagPainter.TypeQ var0, long var1) {
      return var0.R != 2 ? 0.0F : 6.0F + Fonts.SEMIBOLD.width(NameTagsSupport2.hearts(var0.z), 7.5F, 0.0F) + 2.0F + Fonts.SEMIBOLD.width("♥", 7.5F, 0.0F);
   }

   private static void C(Canvas var0, long var1, TagPainter.TypeQ var3, float var4, float var5, boolean var6) {
      String var7 = NameTagsSupport2.hearts(var3.z);
      float var8 = Fonts.SEMIBOLD.width(var7, 7.5F, 0.0F);
      long var9 = 57627478369807L;
      if (57627478369807L == 0L) {
         boolean var10000 = false;
      } else {
         int var15 = var9 < 0L ? -1 : 1;
      }

      int var12 = var3.U ? -11654 : (var3.o ? -41852 : Icons.PINK);
      long var13 = 57627478369808L;
      if (57627478369808L == 0L) {
         boolean var16 = false;
      } else {
         int var17 = var13 < 0L ? -1 : 1;
      }

      if (var6) {
         var0.textGlow(Fonts.SEMIBOLD, var7 + " ♥", var4, var5 + 0.5F, 7.5F, -1090519040, FontStyle.LEFT, FontWeight.MIDDLE, 0.0F, 0.6F, 1.6F);
      }

      var0.text(Fonts.SEMIBOLD, var7, var4, var5, 7.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      var0.text(Fonts.SEMIBOLD, "♥", var4 + var8 + 2.0F, var5, 7.5F, var12, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   private static void T(Canvas var0, TagPainter.TypeQ var1, byte var2, float var3, float var4, int var5, float var6, int var7) {
      long var8 = 62929138920095L;
      long var10 = var8 ^ 130888788664638L;
      float var12 = var4 - 2.0F;
      var0.pushClip(0.0F, 0.0F, var3, var4, var6);
      var0.rect(0.0F, var12, var3, 2.0F, 452984831);
      x(var10, var0, var1, 0.0F, var12, var3, 2.0F, 0.0F);
      var0.popClip();
   }

   private static void k(Canvas var0, TagPainter.TypeQ var1, long var2, float var4, float var5, float var6) {
      long var7 = 85973421018017L;
      float var9 = 1.6F;
      var0.shadow(var4, var5, var6, var9, var9 / 2.0F, 3.0F, 0.0F, 0.0F, 0.5F, 1711276032);
      var0.roundRect(var4, var5, var6, var9, var9 / 2.0F, 1090519039);
      x(var7, var0, var1, var4, var5, var6, var9, var9 / 2.0F);
   }

   private static void x(long var0, Canvas var2, TagPainter.TypeQ var3, float var4, float var5, float var6, float var7, float var8) {
      float var9 = NameTagsSupport2.clamp01(var3.u);
      float var10 = NameTagsSupport2.clamp01(var3.P);
      if (var10 > var9 + 0.003F) {
         var2.roundRect(var4, var5, var6 * var10, var7, var8, Theme.tint(-1711290648));
      }

      if (var9 > 0.002F) {
         float var11 = Math.max(var7, var6 * var9);
         var2.roundRectGradient(var4, var5, var11, var7, var8, 0.0F, var3.o ? Icons.PINK : Icons.INDIGO, var3.o ? -30116 : Icons.MAGENTA);
         var2.roundRectCorners(var4, var5, var11, var7, var8, 956301311, 956301311, 16777215, 16777215);
      }

      float var16 = NameTagsSupport2.clamp01(var3.W);
      long var12 = 85973421018017L;
      if (85973421018017L == 0L) {
         boolean var10000 = false;
      } else {
         int var17 = var12 < 0L ? -1 : 1;
      }

      if (var16 > 0.003F) {
         float var15 = var4 + var6 * Math.min(var9, 1.0F - var16);
         var2.roundRect(var15, var5, var6 * var16, var7, var8, -11654);
      }
   }

   private static void T(Canvas var0, class_2960 var1, float var2, float var3, long var4, float var6, float var7, boolean var8) {
      var0.shadow(3.0F, 3.0F, var6, var6, var7, var8 ? 6.0F : 4.0F, 0.0F, 0.0F, 1.0F, var8 ? 1711276032 : 1073741824);
      long var9 = 14066549168620L;
      if (14066549168620L == 0L) {
         boolean var10000 = false;
      } else {
         int var14 = var9 < 0L ? -1 : 1;
      }

      long var12 = 14066549168620L;
      if (14066549168620L == 0L) {
         boolean var15 = false;
      } else {
         int var16 = var12 < 0L ? -1 : 1;
      }

      if (!M(var0, var1, 3.0F, 3.0F, var6, var7)) {
         var0.roundRectGradient(3.0F, 3.0F, var6, var6, var7, 135.0F, BlendMode.withAlpha(Icons.INDIGO, 0.6F), BlendMode.withAlpha(Icons.MAGENTA, 0.5F));
      }

      var0.roundRectOutline(3.0F, 3.0F, var6, var6, var7, 1.0F, 788529151);
   }

   private static void V(Canvas var0, String var1, long var2, float var4, float var5, float var6, float var7, boolean var8) {
      if (var8) {
         var0.shadow(3.0F, 3.0F, var6, var6, var7, 6.0F, 0.0F, 0.0F, 1.0F, 1426063360);
      }

      var0.roundRectGradient(3.0F, 3.0F, var6, var6, var7, 135.0F, BlendMode.withAlpha(Icons.INDIGO, 0.62F), BlendMode.withAlpha(Icons.MAGENTA, 0.5F));
      var0.roundRectOutline(3.0F, 3.0F, var6, var6, var7, 1.0F, 788529151);
      var0.icon(var1, 3.0F + var6 / 2.0F, 3.0F + var6 / 2.0F, var6 * 0.62F, Icons.TEXT);
   }

   private static boolean M(Canvas var0, class_2960 var1, float var2, float var3, float var4, float var5) {
      try {
         class_1044 var6 = class_310.method_1551().method_1531().method_4619(var1);
         GpuTextureView var7 = var6.method_71659();
         class_12137 var8 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
         var0.image(var7, var8, false, 3.0F, 3.0F, var4, var4, 0.125F, 0.125F, 0.25F, 0.25F, var5, -1);
         var0.image(var7, var8, false, 3.0F, 3.0F, var4, var4, 0.625F, 0.125F, 0.75F, 0.25F, var5, -1);
         return true;
      } catch (IllegalStateException var9) {
         return false;
      }
   }

   public static float measure(TagPainter.TypeO var0) {
      long var1 = 97820274896495L;
      long var3 = var1 ^ 14155731618296L;
      if (q(var0, var3)) {
         TagPainter.TypeO.TypeC var9 = var0.O.getFirst();
         float var10 = var9.M.isEmpty() ? 0.0F : 4.5F + var9.amountWidth();
         var0.x = 15.0F;
         var0.N = 13.5F + var9.r.K + var10 + 7.0F;
         return var0.N;
      } else {
         float var5 = 0.0F;

         for (TagPainter.TypeO.TypeC var7 : var0.O) {
            float var8 = var7.M.isEmpty() ? 0.0F : 10.0F + var7.amountWidth();
            var5 = Math.max(var5, var7.r.K + var8);
         }

         if (var0.B > 0) {
            var5 = Math.max(var5, Fonts.MEDIUM.width(var0.moreText(), 7.0F, 0.0F));
         }

         var0.x = 9.0F + var0.O.size() * 12.0F + (var0.B > 0 ? 11.0F : 0.0F);
         var0.N = Math.max(64.0F, 13.5F + var5 + 7.0F);
         return var0.N;
      }
   }

   public static float height(TagPainter.TypeO var0) {
      return var0.x;
   }

   public static void draw(Canvas var0, TagPainter.TypeO var1, TagPainter.Look var2, float var3, float var4) {
      long var5 = 67361217844389L;
      long var7 = var5 ^ 115911177564978L;
      long var9 = var5 ^ 125501267393590L;
      int var11 = (int)((var5 ^ 125501267393590L) >>> 32);
      int var12 = (int)((var5 ^ 125501267393590L) << 32 >>> 56);
      int var13 = (int)(var9 << 40 >>> 40);
      long var14 = var5 ^ 115764997609902L;
      long var16 = var5 ^ 93425043480911L;
      boolean var18 = var2 == TagPainter.Look.h;
      boolean var19 = q(var1, var7);
      float var20 = var1.N;
      float var21 = var1.x;
      float var22 = var19 ? 5.5F : 7.0F;
      boolean var23 = false;

      for (TagPainter.TypeO.TypeC var25 : var1.O) {
         var23 |= var25.t;
      }

      var0.push();
      var0.translate(-var20 / 2.0F, -var21);
      if (!var18) {
         O(var0, var11, var20, (byte)var12, var13, var21, var22, var2, var3);
         if (var23) {
            p(var14, var0, var20, var21, var22, var4);
         }
      }

      float var30 = var19 ? var21 / 2.0F : 10.5F;

      for (TagPainter.TypeO.TypeC var26 : var1.O) {
         E(var0, 7.5F, var30, var26, var18, var16, var4);
         if (var18) {
            var26.r.drawShadow(var0, 13.5F, var30, 1.0F);
         }

         var26.r.draw(var0, 13.5F, var30);
         if (!var26.M.isEmpty()) {
            float var27 = 7.5F;
            float var28 = var19 ? 13.5F + var26.r.K + 4.5F : var20 - 7.0F;
            FontStyle var29 = var19 ? FontStyle.LEFT : FontStyle.RIGHT;
            if (var18) {
               var0.textGlow(Fonts.SEMIBOLD, var26.M, var28, var30 + 0.5F, var27, -1090519040, var29, FontWeight.MIDDLE, 0.0F, 0.6F, 1.6F);
            }

            var0.text(Fonts.SEMIBOLD, var26.M, var28, var30, var27, Icons.TEXT_2, var29, FontWeight.MIDDLE);
         }

         var30 += 12.0F;
      }

      if (var1.B > 0) {
         float var32 = 7.0F;
         float var33 = var30 - 0.5F;
         if (var18) {
            var0.textGlow(Fonts.MEDIUM, var1.moreText(), 13.5F, var33 + 0.5F, var32, -1090519040, FontStyle.LEFT, FontWeight.MIDDLE, 0.0F, 0.6F, 1.6F);
         }

         var0.text(Fonts.MEDIUM, var1.moreText(), 13.5F, var33, var32, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      var0.pop();
   }

   private static boolean q(TagPainter.TypeO var0, long var1) {
      return var0.O.size() == 1 && var0.B == 0;
   }

   private static void E(Canvas var0, float var1, float var2, TagPainter.TypeO.TypeC var3, boolean var4, long var5, float var7) {
      if (var4) {
         var0.softCircle(7.5F, var2, 2.5F, 4.0F, Integer.MIN_VALUE);
      }

      if (var3.t) {
         float var8 = 0.5F + 0.5F * (float)Math.sin(var7 * 2.2F);
         var0.glowCircle(7.5F, var2, 2.2F, 5.0F, BlendMode.withAlpha(Icons.PURPLE, 0.35F + 0.25F * var8));
      }

      var0.circle(7.5F, var2, 2.1F, var3.X);
      var0.circle(6.9F, var2 - 0.6F, 0.8F, 1509949439);
   }

   private static void p(long var0, Canvas var2, float var3, float var4, float var5, float var6) {
      var2.roundRectOutlineGradient(0.0F, 0.0F, var3, var4, var5, 1.0F, 0.0F, BlendMode.withAlpha(Icons.PURPLE, 0.55F), BlendMode.withAlpha(Icons.PINK, 0.3F));
      float var7 = var6 % 3.2F / 1.2F;
      long var8 = 92403586911498L;
      if (92403586911498L == 0L) {
         boolean var10000 = false;
      } else {
         int var12 = var8 < 0L ? -1 : 1;
      }

      if (var7 < 1.0F) {
         float var11 = -16.0F + (var3 + 32.0F) * Easing.SINE_IN_OUT.apply(var7);
         var2.pushClip(0.0F, 0.0F, var3, var4, var5);
         var2.push();
         var2.translate(var11, var4 / 2.0F);
         var2.rotate(0.35F);
         var2.glow(-5.0F, -var4, 10.0F, var4 * 2.0F, 5.0F, 12.0F, BlendMode.withAlpha(Theme.tint(-1451777), 0.16F * (float)Math.sin(Math.PI * var7)));
         var2.pop();
         var2.popClip();
      }
   }

   private static void O(Canvas var0, int var1, float var2, byte var3, int var4, float var5, float var6, TagPainter.Look var7, float var8) {
      if (!(var8 <= 0.01F)) {
         var0.shadow(0.0F, 0.0F, var2, var5, var6, 9.0F, 0.0F, 0.0F, 1.5F, BlendMode.withAlpha(-16777216, 0.24F * var8));
         if (var7 == TagPainter.Look.c) {
            int var9 = BlendMode.withAlpha(Theme.tint(-16119788), 0.62F);
            var0.glass(0.0F, 0.0F, var2, var5, var6, var9, var8, 1.15F, 0.012F);
         } else {
            var0.roundRect(0.0F, 0.0F, var2, var5, var6, BlendMode.withAlpha(Theme.tint(-16053995), 0.86F * var8));
         }

         var0.roundRectCorners(0.0F, 0.0F, var2, var5, var6, BlendMode.fade(352321535, var8), BlendMode.fade(184549375, var8), 16777215, 16777215);
         var0.roundRectOutlineGradient(0.0F, 0.0F, var2, var5, var6, 1.0F, 90.0F, BlendMode.fade(654311423, var8), BlendMode.fade(150994943, var8));
      }
   }

public static void ensureSourceClassInitialized() {
   }

   public static enum Look {
      c,
      e,
      h;

}

   public static final class TypeO {
      public final List<TagPainter.TypeO.TypeC> O = new ArrayList<>(4);
      public int B;
      float N;
      float x;
      private int u = -1;
      private String m = "";
      private static final String a = " more";

      String moreText() {
         if (this.u != this.B) {
            this.u = this.B;
            this.m = "+" + this.B + a;
         }

         return this.m;
      }

public static final class TypeC {
         public NameTagsRenderer3 r;
         public String M = "";
         public int X = -4936494;
         public boolean t;
         private float S = -1.0F;

         float amountWidth() {
            if (this.S < 0.0F) {
               this.S = this.M.isEmpty() ? 0.0F : Fonts.SEMIBOLD.width(this.M, 7.5F, 0.0F);
            }

            return this.S;
         }
      }
   }

   public static final class TypeQ {
      public NameTagsRenderer3 g;
      
      public NameTagsRenderer3 m;
      
      public class_2960 r;
      
      public String K;
      public int R;
      public float u;
      public float P;
      public float W;
      public float z;
      public boolean U;
      public float s;
      public boolean o;
      float L;
      float A;

      public float width() {
         return this.L;
      }
   }
}
