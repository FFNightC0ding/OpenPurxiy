package dev.purxiy.modules.targethud;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.Setting;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.DrawContext.TypeE;
import java.util.List;
import net.minecraft.class_1068;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;

public final class TargetRenderer {
   public static final float b = 222.0F;
   private static final float x = 51.0F;
   private static final float m = 132.0F;
   private static final float I = 156.0F;
   private static final String[] l = new String[]{"Screen", "Beside target"};
   private static final String U = "Stardust";
   private static final float v = 0.33F;
   private static final float w = 0.95F;
   private static final float L = 16.0F;
   private static final float Z = 64.0F;
   private final TargetHud y;
   private final TargetHud J = new TargetHud();
   private final HudBounds P = new HudBounds();
   private final Object[] T = new Object[]{new Object(), new Object()};
   private final TargetRenderer2.TypeC E = new TargetRenderer2.TypeC();
   private final Animator X = new Animator(1.0F, 200.0F, 28.0F);
   private int S = -1;
   private float e = 1.0F;
   private float B;
   private float F = -1.0F;
   private float D;
   private float z;
   private float o;
   private float R;
   private boolean M;
   private static final long[] d = new long[]{
      -228689898824946139L,
      -8951358468975438705L,
      -5942364522005603365L,
      3131000805237593048L,
      8156347080353602426L,
      1709151294579561078L,
      -5143887127887101438L,
      2523797330884685784L,
      1322842620795200260L,
      -4694673345423985028L,
      7244431590807897721L,
      7164609009537285275L,
      6379621383912760605L,
      -8444213758325787259L,
      -3836695705121028026L,
      7090393228583805794L,
      3041057126585892011L,
      -3295736017637450585L,
      -6692274647482025400L,
      6343222719139480046L,
      -8229888956943204693L,
      5469894583541802010L
   };
   private static final Integer[] f = new Integer[22];

   public TargetRenderer(TargetHud var1) {
      this.y = var1;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 136116872944489L;
      long var8 = var6 ^ 69742598684528L;
      long var10 = var6 ^ 18709615203420L;
      int var12 = (int)((var6 ^ 18709615203420L) >>> 48);
      int var13 = (int)((var6 ^ 18709615203420L) << 16 >>> 32);
      int var14 = (int)(var10 << 48 >>> 48);
      long var15 = var6 ^ 64111615996496L;
      long var17 = var6 ^ 122259252373863L;
      long var19 = var6 ^ 92991172803491L;
      this.z = var2;
      this.o = var3;
      this.R = var4;
      this.M = var5;
      Canvas var21 = var1.canvas;
      var21.text(Fonts.MEDIUM, "Choose a placement", var2, var3 + 10.0F, 13.5F, Icons.TEXT);
      var21.text(Fonts.REGULAR, "On your screen, or in the world right beside them", var2, var3 + 30.0F, 11.5F, Icons.TEXT_2);
      this.e(var17, var1);
      float var22 = (var4 - 8.0F) / 2.0F;
      float var23 = Math.max(0.4F, Math.min(0.62F, (var22 * 0.66999996F - 16.0F - 14.0F) / 180.0F));

      for (int var24 = 0; var24 < 2; var24++) {
         float var25 = var2 + var24 * (var22 + 8.0F);
         float var26 = var3 + 51.0F;
         boolean var27 = this.y.a.is(l[var24]);
         boolean var28 = var5 && var1.hover(this.T[var24], var25, var26, var22, 156.0F, TypeE.HAND);
         var21.roundRect(var25, var26, var22, 156.0F, 10.0F, var27 ? Theme.tint(-13623231) : (var28 ? Theme.tint(-14542287) : Theme.tint(-15725540)));
         this.n(var21, var25 + 3.0F, var26 + 3.0F, var22 - 6.0F, var19, 129.0F);
      }

      var21.captureBackdrop();
      class_2960 var39 = class_1068.method_62620().comp_1626().comp_3627();
      TargetSupport5 var40 = TargetSupport5.current();

      for (int var41 = 0; var41 < 2; var41++) {
         float var42 = var2 + var41 * (var22 + 8.0F);
         float var43 = var3 + 51.0F;
         boolean var29 = this.y.a.is(l[var41]);
         float var30 = var42 + 3.0F;
         float var31 = var43 + 3.0F;
         float var32 = var22 - 6.0F;
         float var33 = 129.0F;
         float var34 = var30 + var32 * 0.33F;
         float var35 = var31 + var33 - 14.0F;
         float var36 = var35 - 64.0F;
         var21.pushClip(var30, var31, var32, var33, 8.0F);
         TargetSupport.figure(var21, var39, var34, var35);
         var21.push();
         var21.translate(var34, var36 - 6.0F);
         var21.scale(0.95F);
         TargetRenderer2.draw(var21, this.E, var40.look(), var40.Z.floatValue());
         var21.pop();
         u((char)var12, var21, var13, var34, var14, var36 + 26.0F);
         if (var41 == 0) {
            V(var21, var30, var31, var15, var32, var33);
            Y(var21, var30 + var32 / 2.0F, var31 + var33 - 3.0F, var8);
         }

         float var37 = var41 == 0 ? var30 + var32 - 10.0F - 180.0F * var23 : var34 + 16.0F + 5.0F;
         float var38 = var41 == 0 ? var31 + var33 - 20.0F - 66.0F * var23 : var36;
         var21.push();
         var21.translate(var37, var38);
         var21.scale(var23);
         this.J.renderSample(var21, this.P);
         var21.pop();
         var21.popClip();
         var21.roundRectOutline(var42, var43, var22, 156.0F, 10.0F, 1.0F, var29 ? Theme.tint(-4222985) : Theme.tint(633708021));
         var21.text(
            Fonts.MEDIUM, l[var41], var42 + var22 / 2.0F, var43 + 132.0F + 12.0F, 10.5F, var29 ? Icons.TEXT : Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE
         );
      }
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      if (this.M && var3 == 0 && !(var1 < this.z) && !(var1 >= this.z + this.R)) {
         float var4 = var2 - this.o;
         float var5 = var1 - this.z;
         if (!(var4 < 51.0F) && !(var4 >= 207.0F)) {
            float var6 = (this.R + 8.0F) / 2.0F;
            int var7 = (int)(var5 / var6);
            if (var7 < 2 && var5 - var7 * var6 < var6 - 8.0F) {
               this.y.a.select(var7);
               UiAudioPolicy.play("CLICK");
               return true;
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private void e(long var1, DrawContext var3) {
      List var4 = this.y.settings();
      TargetHud.ensureSourceClassInitialized();
      List var5 = this.J.settings();

      for (int var6 = 0; var6 < var4.size(); var6++) {
         long var7 = 23079519711758L;
         int var12 = var7 < 0L ? -1 : 1;
         ((Setting)var5.get(var6)).load(((Setting)var4.get(var6)).save());
      }

      TargetSupport5 var22;
      TargetRenderer2.TypeC var23;
      label108: {
         long var8 = 23079519711757L;
         int var8x = var8 < 0L ? -1 : 1;
         this.J.a.set("Screen");
         HudBounds var11x = this.P;
         RenderPipelines var12 = RenderPipelines.get();
         var11x.mc = class_310.method_1551();
         var11x.player = null;
         var11x.level = null;
         var11x.time = var12.time();
         var11x.dt = var3.dt;
         var11x.partialTick = 1.0F;
         var11x.editing = true;
         var11x.width = var3.canvas.width();
         var11x.height = var3.canvas.height();
         var22 = TargetSupport5.current();
         boolean var13 = var22.T.is("Minecraft");
         int var14 = (var22.a.enabled() ? 2 : 0) + (var13 ? 1 : 0);
         var23 = this.E;
         long var15 = 23079519711757L;
         int var24 = var15 < 0L ? -1 : 1;
         if (var14 == this.S) {
            long var17 = 23079519711757L;
            int var25 = var17 < 0L ? -1 : 1;
            if (var23.h != null) {
               long var20 = 23079519711757L;
               int var20x = var20 < 0L ? -1 : 1;
               if (!var23.h.stale()) {
                  break label108;
               }
            }
         }

         this.S = var14;
         var23.h = TargetRenderer3.of(class_2561.method_43470("Stardust"), 9.0F, Fonts.SEMIBOLD, Fonts.BOLD, Icons.TEXT, var22.a.enabled(), var13);
      }

      var23.m = null;
      var23.P = var22.M.enabled() ? class_1068.method_62620().comp_1626().comp_3627() : null;
      var23.X = null;
      float var24 = MotionStyle.HOVER.dt(var3.dt);
      this.D += var24;
      float var9 = this.J.healthShown();
      if (this.F >= 0.0F) {
         long var10 = 23079519711758L;
         int var35 = var10 < 0L ? -1 : 1;
         if (var9 < this.F - 0.01F) {
            this.e = Math.max(this.e, Math.max(this.X.value(), this.F));
            this.B = this.D + 0.4F;
            var23.d = 1.0F;
         }
      }

      if (this.F < 0.0F) {
         this.X.snap(var9);
         this.e = var9;
      }

      this.F = var9;
      float var25 = this.X.target(var9).update(var24);
      long var26 = 23079519711757L;
      int var27 = var26 < 0L ? -1 : 1;
      long var27x = 23079519711757L;
      int var23x = var27x < 0L ? -1 : 1;
      if (this.D > this.B) {
         this.e = Math.max(var25, this.e - var24 * 1.4F);
      }

      var23.d = Math.max(0.0F, var23.d - var24 / 0.5F);
      boolean var16x = var22.f.is("Off");
      long var28 = 23079519711758L;
      int var26x = var28 < 0L ? -1 : 1;
      var23.R = var16x ? 0 : (var22.f.is("Bar") ? 1 : 2);
      var23.G = var25;
      var23.t = this.e;
      var23.z = this.J.absorptionShown();
      var23.D = (var9 + var23.z) * 20.0F;
      var23.Z = var23.z > 0.0F;
      var23.e = var9 <= 0.3F;
      TargetRenderer2.measure(var23);
   }

   private void n(Canvas var1, float var2, float var3, float var4, long var5, float var7) {
      float var8 = this.D;
      var1.pushClip(var2, var3, var4, 129.0F, 8.0F);
      TargetHud.ensureSourceClassInitialized();
      var1.roundRectGradient(var2, var3, var4, 129.0F, 8.0F, 90.0F, Theme.tint(-13425058), Theme.tint(-6259509));
      var1.glowCircle(var2 + var4 * 0.84F, var3 + 30.96F, 7.0F, 18.0F, BlendMode.withAlpha(Theme.tint(-6410), 0.46F));
      var1.circle(var2 + var4 * 0.84F, var3 + 30.96F, 4.5F, Theme.tint(-463105));

      for (int var9 = 0; var9 < 11; var9++) {
         float var10 = var2 + var4 * (0.05F + 0.085F * var9) + var9 % 3 * 3;
         float var11 = var3 + 129.0F * (0.1F + 0.065F * (var9 * 7 % 4));
         float var12 = 0.35F + 0.3F * (float)Math.sin(var8 * 1.7F + var9 * 1.9F);
         long var13 = 52085539102921L;
         int var15 = var13 < 0L ? -1 : 1;
         var1.circle(var10, var11, 0.7F, BlendMode.withAlpha(-1, var12 * 0.55F));
      }

      var1.roundRect(var2 - 20.0F, var3 + 129.0F - 32.0F, var4 * 0.62F, 42.0F, 21.0F, Theme.tint(-11847048));
      long var16 = 52085539102922L;
      int var17 = var16 < 0L ? -1 : 1;
      var1.roundRect(var2 + var4 * 0.45F, var3 + 129.0F - 27.0F, var4 * 0.75F, 42.0F, 22.0F, Theme.tint(-12635800));
      var1.rect(var2 + var4 * 0.62F, var3 + 129.0F - 26.0F, var4 * 0.3F, 12.0F, Theme.tint(-13752760));
      var1.rect(var2 + var4 * 0.62F, var3 + 129.0F - 26.0F, var4 * 0.3F, 1.0F, 587202559);
      var1.roundRectGradient(var2, var3 + 129.0F - 14.0F, var4, 14.0F, 0.0F, 90.0F, -12821953, -14535649);
      var1.rect(var2, var3 + 129.0F - 14.0F, var4, 1.0F, 864673931);
      var1.popClip();
   }

   private static void u(char var0, Canvas var1, int var2, float var3, int var4, float var5) {
      var1.softCircle(var3, var5, 2.0F, 7.0F, 855638016);
      var1.roundRect(var3 - 4.5F, var5 - 0.6F, 3.0F, 1.2F, 0.6F, -419430401);
      var1.roundRect(var3 + 1.5F, var5 - 0.6F, 3.0F, 1.2F, 0.6F, -419430401);
      var1.roundRect(var3 - 0.6F, var5 - 4.5F, 1.2F, 3.0F, 0.6F, -419430401);
      var1.roundRect(var3 - 0.6F, var5 + 1.5F, 1.2F, 3.0F, 0.6F, -419430401);
   }

   private static void Y(Canvas var0, float var1, float var2, long var3) {
      float var5 = 7.0F;
      TargetHud.ensureSourceClassInitialized();
      float var6 = 1.5F;
      float var7 = 9.0F * var5 + 8.0F * var6;
      float var8 = var1 - var7 / 2.0F;
      float var9 = var2 - var5;
      var0.roundRect(var8 - 2.0F, var9 - 2.0F, var7 + 4.0F, var5 + 4.0F, 3.5F, 1493172224);

      for (int var10 = 0; var10 < 9; var10++) {
         float var11 = var8 + var10 * (var5 + var6);
         var0.roundRect(var11, var9, var5, var5, 2.0F, var10 == 0 ? 872415231 : 352321535);
         long var12 = 75463029285912L;
         int var14 = var12 < 0L ? -1 : 1;
         if (var10 == 0) {
            var0.roundRectOutline(var11 - 0.5F, var9 - 0.5F, var5 + 1.0F, var5 + 1.0F, 2.5F, 1.0F, Theme.tint(-858344193));
         }
      }
   }

   private static void V(Canvas var0, float var1, float var2, long var3, float var5, float var6) {
      float var7 = 6.0F;
      float var8 = 7.0F;
      float var9 = 1.2F;
      int var10 = 1090519039;
      float var11 = var1 + var7;
      float var12 = var2 + var7;
      float var13 = var1 + var5 - var7;
      float var14 = var2 + 129.0F - var7;
      var0.roundRect(var11, var12, var8, var9, var9 / 2.0F, var10);
      var0.roundRect(var11, var12, var9, var8, var9 / 2.0F, var10);
      var0.roundRect(var13 - var8, var12, var8, var9, var9 / 2.0F, var10);
      var0.roundRect(var13 - var9, var12, var9, var8, var9 / 2.0F, var10);
      var0.roundRect(var11, var14 - var9, var8, var9, var9 / 2.0F, var10);
      var0.roundRect(var11, var14 - var8, var9, var8, var9 / 2.0F, var10);
      var0.roundRect(var13 - var8, var14 - var9, var8, var9, var9 / 2.0F, var10);
      var0.roundRect(var13 - var9, var14 - var8, var9, var8, var9 / 2.0F, var10);
   }
}
