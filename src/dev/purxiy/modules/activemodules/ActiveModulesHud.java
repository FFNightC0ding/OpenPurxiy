package dev.purxiy.modules.activemodules;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudModule;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.ModuleLabels;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ActiveModulesHud extends HudModule {
   private static final float J = 14.0F;
   private static final float o = 9.5F;
   private static final float i = 6.0F;
   private static final float O = 2.0F;
   private static final List<String> f = List.of("Hand Movement", "Name Tags", "Zoom", "Freelook");
   private final ModeSetting Y = this.mode("sort", "Order", null, "Length", new String[]{"Length", "Alphabetical"});
   private final BooleanSetting X = this.bool("gradient", "Gradient", "Colour the list with the SourceByDevFFnightmare DeObfDeCompByGuideX gradient", true);
   private final BooleanSetting C = this.bool("hud", "HUD elements", "List enabled HUD elements as well", false);
   private final Map<String, ActiveModulesHud.ModuleEntry> T = new LinkedHashMap<>();
   private final List<ActiveModulesHud.ModuleEntry> F = new ArrayList<>();
   private boolean Z = true;
   private int S = Integer.MIN_VALUE;
   private String u = "";
   private final List<String> W = new ArrayList<>();
   private long R = -1L;
   private boolean x;
   private boolean w;
   public static boolean r;
   private static final String[] b = new String[19];

   public ActiveModulesHud() {
      super("active_modules", "Active Modules", "Every module you have switched on, as a list", "list", 1.0, 0.0);
      this.enabledByDefault();
   }

   private List E(long var1, HudBounds var3) {
      ActiveModulesHud.ModuleEntry.ensureSourceClassInitialized();
      long var4 = RenderPipelines.get().frame();
      boolean var6 = this.C.enabled();
      long var7 = 106691088815136L;
      int var10 = var7 < 0L ? -1 : 1;
      long var10x;
      int var12 = (var10x = var4 - this.R) == 0L ? 0 : (var10x < 0L ? -1 : 1);
      long var13 = 106691088815136L;
      int var15 = var13 < 0L ? -1 : 1;
      if (var12 == 0 && var3.editing == this.x && var6 == this.w) {
         return this.W;
      } else {
         this.R = var4;
         this.x = var3.editing;
         this.w = var6;
         List var15x = this.W;
         var15x.clear();
         long var16 = 106691088815137L;
         int var18 = var16 < 0L ? -1 : 1;
         var15x.addAll(ModuleLabels.names(var6));
         long var18x = 106691088815137L;
         int var19 = var18x < 0L ? -1 : 1;
         if (var15x.isEmpty() && var3.editing) {
            var15x.addAll(f);
         }

         return var15x;
      }
   }

   protected boolean hasContent(HudBounds var1) {
      long var2 = 115106226684289L;
      long var4 = var2 ^ 10688927094176L;

      for (ActiveModulesHud.ModuleEntry var7 : this.T.values()) {
         if (var7.p.value() > 0.01F) {
            return true;
         }
      }

      return !this.E(var4, var1).isEmpty();
   }

   protected void measure(HudBounds var1, float[] var2) {
      long var3 = 40695951416122L;
      long var5 = var3 ^ 74817118752539L;
      List<String>var7 = this.E(var5, var1);

      for (ActiveModulesHud.ModuleEntry var9 : this.T.values()) {
         var9.K = false;
      }

      for (String var16 : var7) {
         ActiveModulesHud.ModuleEntry var10 = this.T.get(var16);
         if (var10 == null) {
            var10 = new ActiveModulesHud.ModuleEntry(var16);
            this.T.put(var16, var10);
            this.Z = true;
         }

         var10.K = true;
      }

      int var15 = Translations.revision();
      String var17 = (String)this.Y.get();
      if (var15 != this.S || !var17.equals(this.u)) {
         this.S = var15;
         this.u = var17;

         for (ActiveModulesHud.ModuleEntry var11 : this.T.values()) {
            var11.S = -1.0F;
         }

         this.Z = true;
      }

      for (ActiveModulesHud.ModuleEntry var21 : this.T.values()) {
         var21.p.target(var21.K ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         if (var21.S < 0.0F) {
            var21.S = Fonts.SEMIBOLD.width(var21.b, 9.5F) + 12.0F + 2.0F + 2.0F;
         }
      }

      if (this.T.values().removeIf(var0 -> {
         ActiveModulesHud.ModuleEntry.ensureSourceClassInitialized();
         return !var0.K && var0.p.value() <= 0.01F;
      })) {
         this.Z = true;
      }

      if (this.Z) {
         this.Z = false;
         this.F.clear();
         this.F.addAll(this.T.values());
         if (this.Y.is("Length")) {
            this.F.sort(Comparator.<ActiveModulesHud.ModuleEntry>comparingDouble(var0 -> -var0.S).thenComparing(var0 -> var0.b));
         } else {
            this.F.sort(Comparator.comparing(var0 -> Translations.tr(var0.b)));
         }
      }

      float var20 = 0.0F;
      float var22 = 40.0F;

      for (ActiveModulesHud.ModuleEntry var13 : this.F) {
         if (!var13.k) {
            var13.O.snap(var20);
            var13.k = true;
         }

         var13.O.target(var20).update(MotionStyle.HOVER.dt(var1.dt));
         var20 += 14.0F * Math.max(0.0F, Math.min(1.0F, var13.p.value()));
         var22 = Math.max(var22, var13.S);
      }

      var2[0] = var22;
      var2[1] = Math.max(14.0F, var20);
   }

   protected void render(Canvas var1, HudBounds var2) {
      boolean var3 = this.rightSide(var2);
      float var4 = this.contentWidth();
      int var5 = this.F.size();

      for (int var6 = 0; var6 < var5; var6++) {
         ActiveModulesHud.ModuleEntry var7 = this.F.get(var6);
         float var8 = Math.max(0.0F, Math.min(1.0F, var7.p.value()));
         if (!(var8 <= 0.01F)) {
            float var9 = var7.S;
            float var10 = (1.0F - var8) * (var9 * 0.6F + 10.0F) * (var3 ? 1 : -1);
            float var11 = (var3 ? var4 - var9 : 0.0F) + var10;
            float var12 = var7.O.value();
            int var13 = this.X.enabled() ? BlendMode.lerp(Icons.PURPLE, Icons.INDIGO, var5 <= 1 ? 0.0F : (float)var6 / (var5 - 1)) : Icons.TEXT;
            var1.pushAlpha(var8);
            float var14 = 4.0F;
            if (var3) {
               this.panel(var1, var11, var12, var9, 14.0F, var14);
               var1.roundRect(var11 + var9 - 2.0F - 1.0F, var12 + 3.0F, 2.0F, 8.0F, 1.0F, var13);
               this.text(var1, Fonts.SEMIBOLD, var7.b, var11 + var9 - 2.0F - 1.0F - 6.0F, var12 + 7.0F, 9.5F, Icons.TEXT, FontStyle.RIGHT, FontWeight.MIDDLE);
            } else {
               this.panel(var1, var11, var12, var9, 14.0F, var14);
               var1.roundRect(var11 + 1.0F, var12 + 3.0F, 2.0F, 8.0F, 1.0F, var13);
               this.text(var1, Fonts.SEMIBOLD, var7.b, var11 + 1.0F + 2.0F + 6.0F, var12 + 7.0F, 9.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
            }

            var1.popAlpha();
         }
      }
   }

   private static final class ModuleEntry {
      final String b;
      final Animator p = Animator.smooth(0.0F);
      final Animator O = new Animator(0.0F, 260.0F, 32.0F);
      boolean K;
      boolean k;
      float S = -1.0F;
      public static int n;

      ModuleEntry(String var1) {
         this.b = var1;
      }

      public static void ensureSourceClassInitialized() {
      }
   }
}
