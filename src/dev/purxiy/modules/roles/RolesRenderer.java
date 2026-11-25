package dev.purxiy.modules.roles;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.fabric.client.TabListProvider;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;
import net.minecraft.class_1044;
import net.minecraft.class_1068;
import net.minecraft.class_12137;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;

public final class RolesRenderer {
   public static final float a = 196.0F;
   private static final float U = 48.0F;
   private static final float C = 140.0F;
   private static final float z = 1.2F;
   private static final float J = 1.3F;
   private static final String l = "Alex";
   private static final UUID k = UUID.nameUUIDFromBytes("SourceByDevFFnightmare DeObfDeCompByGuideX Roles preview".getBytes(StandardCharsets.UTF_8));
   private static final float g = 9.0F;
   private final RolesModule K;
   private final RolesOverlay.RolePlate i = new RolesOverlay.RolePlate();
   private final RolesOverlay.RolePlate W = new RolesOverlay.RolePlate();
   private RolesPlate Z = null;
   private RolesPlate P = null;
   private class_2561 r = null;
   private class_2561 n = class_2561.method_43473();
   private int j = -1;
   private float w;
   private static final long[] d = new long[]{
      7272483631027985967L,
      -8463171373838917252L,
      -2317175055765778957L,
      -7548180094770351718L,
      -4114069441540845582L,
      -8030411847025464757L,
      3661909744912868533L,
      -5625909756987223593L,
      -4158655244299579696L,
      749080892068982076L,
      -6382584803050380661L,
      -4000055865276395430L,
      -5992969738357636580L,
      -4603964508394471892L,
      7960074745127529437L,
      -632388556350458453L,
      -5423063759962429607L,
      7902759345137345083L
   };
   private static final Integer[] e = new Integer[18];

   public RolesRenderer(RolesModule var1) {
      this.K = var1;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 2322431928198L;
      long var8 = var6 ^ 46956343535432L;
      long var10 = var6 ^ 128845115699446L;
      int var12 = (int)((var6 ^ 128845115699446L) >>> 48);
      int var13 = (int)((var6 ^ 128845115699446L) << 16 >>> 32);
      int var14 = (int)(var10 << 48 >>> 48);
      long var15 = var6 ^ 37726516370327L;
      var10 = var6 ^ 125958802629336L;
      int var17 = (int)((var6 ^ 125958802629336L) >>> 32);
      int var18 = (int)((var6 ^ 125958802629336L) << 32 >>> 48);
      int var19 = (int)(var10 << 48 >>> 48);
      long var20 = var6 ^ 644230448828L;
      this.w = this.w + MotionStyle.HOVER.dt(var1.dt);
      Canvas var22 = var1.canvas;
      var22.text(Fonts.MEDIUM, "Your name tag", var2, var3 + 10.0F, 13.5F, Icons.TEXT);
      var22.text(Fonts.REGULAR, "Live, as only you see it", var2, var3 + 30.0F, 11.5F, Icons.TEXT_2);
      this.S(var8);
      float var23 = var3 + 48.0F;
      var22.roundRect(var2, var23, var4, 140.0F, 10.0F, Theme.tint(-15725540));
      this.e(var22, var2 + 3.0F, var17, (char)var18, var23 + 3.0F, var4 - 6.0F, 134.0F, (short)var19);
      var22.captureBackdrop();
      RolesNameTagSettings var24 = RolesNameTagSettings.current();
      boolean var25 = var24.enabled();
      float var26 = var23 + 140.0F - 18.0F;
      float var27 = var2 + var4 * 0.6F;
      float var28 = var2 + (var27 - var2) * 0.27F;
      float var29 = var2 + (var27 - var2) * 0.75F;
      var22.pushClip(var2 + 3.0F, var23 + 3.0F, var4 - 6.0F, 134.0F, 8.0F);
      W(var22, R(var20), var28, var26);
      W(var22, class_1068.method_4648(k).comp_1626().comp_3627(), var29, var26);
      float var30 = var26 - 83.2F - 6.0F;
      float var31 = (var29 - var28) / 2.0F - 6.0F;
      this.R(var22, (short)var12, this.i, var13, var28, var30, var14, var31, var25, var24);
      this.R(var22, (short)var12, this.W, var13, var29, var30, var14, var31, var25, var24);
      this.i(var22, var15, var27 + 10.0F, var23 + 58.8F, var2 + var4 - 16.0F - var27 - 10.0F);
      var22.popClip();
      var22.roundRectOutline(var2, var23, var4, 140.0F, 10.0F, 1.0F, Theme.tint(633708021));
   }

   private static void W(Canvas var0, class_2960 var1, float var2, float var3) {
      var0.push();
      var0.translate(var2, var3);
      var0.scale(1.3F);
      RolesLifetime.figure(var0, var1, 0.0F, 0.0F);
      var0.pop();
   }

   private void R(Canvas var1, short var2, RolesOverlay.RolePlate var3, int var4, float var5, float var6, int var7, float var8, boolean var9, RolesNameTagSettings var10) {
      RolesRegistry.ensureSourceClassInitialized();
      var1.push();
      var1.translate(var5, var6);
      if (var9) {
         float var11 = Math.min(1.2F, var8 * 2.0F / Math.max(1.0F, var3.width()));
         var1.scale(var11);
         RolesOverlay.draw(var1, var3, var10.look(), var10.e.floatValue());
      } else {
         float var14 = var3.t.S * Fonts.SEMIBOLD.capHeight / 7.0F;
         float var12 = var3.t.U + 2.0F * var14;
         float var13 = 9.0F * var14;
         var1.scale(Math.min(1.2F, var8 * 2.0F / Math.max(1.0F, var12)));
         var1.rect(-var12 / 2.0F, -var13, var12, var13, 1073741824);
         var3.t.draw(var1, -var3.t.U / 2.0F, -var13 / 2.0F);
      }

      var1.pop();
   }

   private void i(Canvas var1, long var2, float var4, float var5, float var6) {
      long var7 = 110680833432629L;
      long var9 = 2788584743226L;
      RolesRegistry.ensureSourceClassInitialized();
      float var11 = Math.max(this.Z.U, this.P.U) + 9.0F + 4.0F;
      float var12 = Math.min(1.3F, var6 / Math.max(1.0F, var11 + 2.0F));
      float var13 = 21.0F * var12;
      float var14 = var5 - var13 / 2.0F;
      boolean var15 = this.K.C.enabled();
      float var16 = var14 - 9.0F;
      int var17 = var15 ? Icons.TEXT : Icons.TEXT_2;
      long var18 = 35542332980241L;
      int var20 = var18 < 0L ? -1 : 1;
      var1.text(Fonts.MEDIUM, "Tab list", var4, var16, 10.0F, var17, FontStyle.LEFT, FontWeight.MIDDLE);
      var1.push();
      var1.translate(var4, var14);
      var1.scale(var12);
      var1.rect(0.0F, 0.0F, var11 + 2.0F, 21.0F, Integer.MIN_VALUE);
      p(var1, 1.0F, 1.0F, var11, R(var9), this.Z, var7);
      p(var1, 1.0F, 11.0F, var11, class_1068.method_4648(k).comp_1626().comp_3627(), this.P, var7);
      var1.pop();
      if (!var15) {
         var1.text(Fonts.REGULAR, "Off: the server's ranks stay here", var4, var14 + var13 + 10.0F, 9.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      }
   }

   private static void p(Canvas var0, float var1, float var2, float var3, class_2960 var4, RolesPlate var5, long var6) {
      long var8 = 59769929493648L;
      var0.rect(var1, var2, var3, 8.0F, 553648127);
      Q(var0, var4, var1, var2, 8.0F, var8);
      var5.draw(var0, var1 + 9.0F + 1.0F, var2 + 4.5F);
   }

   private static void Q(Canvas var0, class_2960 var1, float var2, float var3, float var4, long var5) {
      GpuTextureView var7;
      try {
         class_1044 var8 = class_310.method_1551().method_1531().method_4619(var1);
         var7 = var8.method_71659();
      } catch (IllegalStateException var10) {
         return;
      }

      float var11 = var7.getHeight(0) * 2 == var7.getWidth(0) ? 32.0F : 64.0F;
      class_12137 var9 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
      var0.image(var7, var9, false, var2, var3, 8.0F, 8.0F, 0.125F, 8.0F / var11, 0.25F, 16.0F / var11, 0.0F, -1);
      var0.image(var7, var9, false, var2, var3, 8.0F, 8.0F, 0.625F, 8.0F / var11, 0.75F, 16.0F / var11, 0.0F, -1);
   }

   private void S(long var1) {
      long var3;
      RolesNameTagSettings var5;
      label152: {
         var3 = 2788584743226L;
         RolesRegistry.ensureSourceClassInitialized();
         class_310 var6 = class_310.method_1551();
         var5 = RolesNameTagSettings.current();
         class_2561 var7 = RolesRegistry.prefix();
         long var8 = 44703556288718L;
         int var11 = var8 < 0L ? -1 : 1;
         Object var11x = var6.field_1724 != null ? var6.field_1724.method_5476() : class_2561.method_43470(var6.method_1548().method_1676());
         boolean var12 = !var5.enabled() || var5.o.is("Minecraft");
         boolean var13 = !var5.enabled() || var5.C.enabled();
         int var14 = Objects.hash(
            this.K.enabled(), this.K.everyone(), this.K.C.enabled(), var5.enabled(), var12, var13, var5.X.enabled(), var5.d.get(), TabListProvider.generation()
         );
         long var15 = 44703556288718L;
         int var32 = var15 < 0L ? -1 : 1;
         if (var14 == this.j && var7 == this.r && var11x.equals(this.n) && this.i.t != null) {
            long var17 = 44703556288718L;
            int var19 = var17 < 0L ? -1 : 1;
            if (!this.i.t.stale() && !this.Z.stale()) {
               break label152;
            }
         }

         this.j = var14;
         this.r = var7;
         this.n = (class_2561)var11x;
         boolean var32x = this.K.enabled();
         class_2561 var18 = RolesRegistry.own((class_2561)var11x);
         Object var19 = var32x && this.K.everyone() ? RolesRegistry.withRoles(class_2561.method_43470("Alex"), "Alex") : class_2561.method_43470("Alex");
         long var20 = 44703556288717L;
         int var22 = var20 < 0L ? -1 : 1;
         this.i.t = RolesPlate.of(var18, 9.0F, Fonts.SEMIBOLD, Fonts.BOLD, Icons.TEXT, var13, var12);
         this.W.t = RolesPlate.of((class_2561)var19, 9.0F, Fonts.SEMIBOLD, Fonts.BOLD, Icons.TEXT, var13, var12);
         boolean var22x = var32x && this.K.C.enabled();
         float var23 = 7.0F / Fonts.SEMIBOLD.capHeight;
         this.Z = RolesPlate.of((class_2561)(var22x ? var18 : var11x), var23, Fonts.SEMIBOLD, Fonts.BOLD, Icons.TEXT, true, true);
         long var24 = 44703556288718L;
         int var25 = var24 < 0L ? -1 : 1;
         this.P = RolesPlate.of((class_2561)(var22x ? var19 : class_2561.method_43470("Alex")), var23, Fonts.SEMIBOLD, Fonts.BOLD, Icons.TEXT, true, true);
      }

      for (RolesOverlay.RolePlate var9 : new RolesOverlay.RolePlate[]{this.i, this.W}) {
         var9.j = var5.X.enabled() ? (var9 == this.i ? R(var3) : class_1068.method_4648(k).comp_1626().comp_3627()) : null;
         var9.s = null;
         long var10 = 44703556288717L;
         int var36 = var10 < 0L ? -1 : 1;
         boolean var27 = var5.d.is("Off");
         long var31 = 44703556288718L;
         int var241 = var31 < 0L ? -1 : 1;
         var9.J = var27 ? 0 : (var5.d.is("Bar") ? 1 : 2);
         var9.T = 1.0F;
         var9.a = 1.0F;
         var9.i = 0.0F;
         var9.O = 20.0F;
         var9.m = false;
         var9.W = false;
         var9.U = 0.0F;
         RolesOverlay.measure(var9);
      }
   }

   private void e(Canvas var1, float var2, int var3, char var4, float var5, float var6, float var7, short var8) {
      var1.pushClip(var2, var5, var6, 134.0F, 8.0F);
      var1.roundRectGradient(var2, var5, var6, 134.0F, 8.0F, 90.0F, Theme.tint(-13753512), Theme.tint(-6522676));
      var1.glowCircle(var2 + var6 * 0.86F, var5 + 29.48F, 7.0F, 18.0F, BlendMode.withAlpha(Theme.tint(-6410), 0.45F));
      RolesRegistry.ensureSourceClassInitialized();
      var1.circle(var2 + var6 * 0.86F, var5 + 29.48F, 4.5F, Theme.tint(-463105));

      for (int var9 = 0; var9 < 12; var9++) {
         float var10 = var2 + var6 * (0.05F + 0.079F * var9) + var9 % 3 * 3;
         float var11 = var5 + 134.0F * (0.1F + 0.06F * (var9 * 7 % 5));
         float var12 = 0.35F + 0.3F * (float)Math.sin(this.w * 1.7F + var9 * 1.9F);
         var1.circle(var10, var11, 0.7F, BlendMode.withAlpha(-1, var12 * 0.6F));
      }

      var1.roundRect(var2 - 20.0F, var5 + 134.0F - 34.0F, var6 * 0.5F, 44.0F, 22.0F, Theme.tint(-11847048));
      var1.roundRect(var2 + var6 * 0.38F, var5 + 134.0F - 28.0F, var6 * 0.8F, 44.0F, 24.0F, Theme.tint(-12635800));
      var1.roundRectGradient(var2, var5 + 134.0F - 20.0F, var6, 20.0F, 0.0F, 90.0F, -12821953, -14535649);
      var1.rect(var2, var5 + 134.0F - 20.0F, var6, 1.0F, 864673931);
      var1.popClip();
   }

   private static class_2960 R(long var0) {
      RolesRegistry.ensureSourceClassInitialized();
      class_310 var2 = class_310.method_1551();
      long var3 = 2788584743225L;
      int var5 = var3 < 0L ? -1 : 1;
      return var2.field_1724 != null
         ? var2.field_1724.method_52814().comp_1626().comp_3627()
         : class_1068.method_4648(var2.method_1548().method_44717()).comp_1626().comp_3627();
   }
}
