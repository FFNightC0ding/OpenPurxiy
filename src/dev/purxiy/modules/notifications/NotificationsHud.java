package dev.purxiy.modules.notifications;

import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudGroupBounds;
import dev.purxiy.hud.HudLayout;
import dev.purxiy.hud.HudModule;
import dev.purxiy.hud.HudLayout.HudToast;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;
import java.util.ArrayList;
import java.util.List;

public final class NotificationsHud extends HudModule {
   private static final float n = 150.0F;
   private static final float G = 240.0F;
   private static final float M = 32.0F;
   private static final float D = 5.0F;
   private static final float v = 0.35F;
   private final NumberSetting O = this.number("duration", "Duration", "How long a notification stays", 2.5, 1.5, 6.0, 0.5, Listener.unit("s"));
   private final List<HudToast> x = new ArrayList<>();
   private float p = 150.0F;
   public static int w = 0;
   public static boolean B;

   public NotificationsHud() {
      super("notifications", "Notifications", "A quiet message when a keybind toggles a module", "bell", 1.0, 1.0);
      this.enabledByDefault();
   }

   private void G(HudBounds var1, long var2) {
      this.x.clear();
      long var4 = 720288752439L;
      int var6 = var4 < 0L ? -1 : 1;
      HudLayout.prune(var1.time, this.O.floatValue());
      this.x.addAll(HudLayout.toasts());
      if (this.x.isEmpty() && var1.editing) {
         this.x.add(new HudToast("Freelook", "Enabled", "scan-eye", true, var1.time - 1.0F));
      }
   }

   protected boolean hasContent(HudBounds var1) {
      long var2 = 137930309389101L;
      long var4 = var2 ^ 138357331355674L;
      this.G(var1, var4);
      return !this.x.isEmpty();
   }

   protected void measure(HudBounds var1, float[] var2) {
      long var3 = 67310310281663L;
      long var5 = var3 ^ 67690088395400L;
      this.G(var1, var5);
      int var7 = Math.max(1, this.x.size());
      float var8 = 0.0F;

      for (HudToast var10 : this.x) {
         var8 = Math.max(var8, Math.max(Fonts.SEMIBOLD.width(var10.title(), 9.5F), Fonts.MEDIUM.width(var10.message(), 8.5F)));
      }

      this.p = Math.clamp(32.0F + var8 + 10.0F, 150.0F, 240.0F);
      var2[0] = this.p;
      var2[1] = var7 * 32.0F + (var7 - 1) * 5.0F;
   }

   protected void render(Canvas var1, HudBounds var2) {
      long var3 = 116831985691848L;
      long var5 = var3 ^ 131322856889928L;
      boolean var7 = this.rightSide(var2);
      boolean var8 = this.bottomSide(var2);
      float var9 = this.contentHeight();
      float var10 = this.O.floatValue();

      for (int var11 = 0; var11 < this.x.size(); var11++) {
         HudToast var12 = this.x.get(var11);
         float var13 = var12.lifetime(var10);
         float var14 = (var2.time - var12.createdAt()) * MotionStyle.MENU.speed();
         boolean var15 = var2.editing && HudLayout.toasts().isEmpty();
         float var16 = var15 ? 1.0F : Easing.EMPHASIZED.apply(Math.min(1.0F, var14 / 0.35F));
         float var17 = var15 ? 1.0F : 1.0F - Easing.ACCELERATE.apply(Math.max(0.0F, Math.min(1.0F, (var14 - var13) / 0.35F)));
         float var18 = var16 * var17;
         if (!(var18 <= 0.01F)) {
            float var19 = var8 ? var9 - 32.0F - var11 * 37.0F : var11 * 37.0F;
            float var20 = (1.0F - var16) * 36.0F * (var7 ? 1 : -1) + (1.0F - var17) * 12.0F * (var7 ? 1 : -1);
            var1.push();
            var1.translate(var20, 0.0F);
            var1.pushAlpha(var18);
            this.Z(var1, var12, var19, var15 ? 0.6F : Math.max(0.0F, 1.0F - var14 / var13), var5);
            var1.popAlpha();
            var1.pop();
         }
      }
   }

   private void Z(Canvas var1, HudToast var2, float var3, float var4, long var5) {
      this.panel(var1, 0.0F, var3, this.p, 32.0F, 9.0F);
      HudGroupBounds.iconTile(var1, 7.0F, var3 + 7.0F, 18.0F, 6.0F, var2.icon(), var2.positive() ? 1.0F : 0.0F, 0.0F);
      long var7 = 32083090944639L;
      int var10 = var7 < 0L ? -1 : 1;
      this.text(var1, Fonts.SEMIBOLD, var2.title(), 32.0F, var3 + 12.0F, 9.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      this.text(var1, Fonts.MEDIUM, var2.message(), 32.0F, var3 + 22.5F, 8.5F, var2.positive() ? Icons.PURPLE : Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      float var10x = (this.p - 18.0F) * var4;
      if (var10x > 1.0F) {
         var1.roundRectGradient(9.0F, var3 + 32.0F - 3.0F, var10x, 1.4F, 0.7F, 0.0F, Icons.INDIGO, Icons.MAGENTA);
      }
   }

   protected void closeContent() {
      this.x.clear();
      HudLayout.clear();
   }
}
