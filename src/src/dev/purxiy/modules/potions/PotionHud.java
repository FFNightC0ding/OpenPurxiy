// Sourced By DevFFNightmare
package dev.purxiy.modules.potions;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudDragState;
import dev.purxiy.hud.HudElementGroup;
import dev.purxiy.hud.HudModule;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.api.HudLayer;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_329;
import net.minecraft.class_4081;
import net.minecraft.class_6880;

public final class PotionHud extends HudModule {
   private static final float Q = 6.0F;
   private static final float z = 21.0F;
   private static final float D = 13.0F;
   private static final String[] b = new String[]{"", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
   private final BooleanSetting K = this.bool("hide_vanilla", "Hide vanilla icons", "Remove the effect icons in the top right corner", true);
   private final BooleanSetting x = this.bool("names", "Names", "Effect names next to the icons", true);
   private final List<PotionHud.TypeR> J = new ArrayList<>();
   private final Object2IntOpenHashMap<class_6880<class_1291>> P = new Object2IntOpenHashMap();
   public static boolean W;


   private static final long[] d = new long[]{
      8715079255896730395L, -740542126877646642L, 3268498513166776199L, -1073400109908550347L, -1203273198613288840L, -4345614506805686378L
   };


   public PotionHud() {
      super("potion_effects", "Potion Effects", "Active effects and their timers", "flask-conical", 1.0, 0.0);
      this.enabledByDefault();
   }

   public boolean hidesVanillaIcons() {
      return this.enabled() && this.K.enabled();
   }

   @Override
   protected boolean mergeable() {
      return true;
   }

   @Override
   protected void renderMiniature(Canvas var1, HudBounds var2) {
      this.render(var1, var2);

      for (int var3 = 0; var3 < this.J.size(); var3++) {
         int var4 = this.J.get(var3).effect().comp_349().method_5556() | 0xFF000000;
         float var5 = 12.5F;
         float var6 = 6.0F + var3 * 21.0F + 6.5F;
         var1.glowCircle(var5, var6, 2.6000001F, 6.5F, BlendMode.withAlpha(var4, 0.35F));
         var1.circle(var5, var6, 4.6800003F, BlendMode.lerp(var4, -1, 0.15F));
      }
   }

   private void U(HudBounds var1, short var2, char var3, int var4) {
      long var5 = 1579316015881L;
      long var7 = var5 ^ 73213639898822L;
      PotionHud.TypeR.ensureSourceClassInitialized();
      this.J.clear();
      if (var1.live()) {
         ArrayList var9 = new ArrayList<>(var1.player.method_6026());
         var9.sort(Comparator.comparingInt(class_1293::method_5584).reversed());

         for (class_1293 var11 : (Iterable<class_1293>)(Object)(var9)) {
            class_6880 var12 = var11.method_5579();
            int var13 = var11.method_5584();
            int var14 = Math.max(var13, this.P.getInt(var12));
            this.P.put(var12, var14);
            this.J
               .add(
                  new PotionHud.TypeR(
                     var12,
                     J(var11, var7),
                     var13,
                     var11.method_48559(),
                     ((class_1291)var12.comp_349()).method_18792() == class_4081.field_18272,
                     var14 <= 0 ? 1.0F : (float)var13 / var14
                  )
               );
         }

         this.P.keySet().removeIf(var1x -> var9.stream().noneMatch(var1xx -> ((net.minecraft.class_1293)(Object)var1xx).method_5579().equals(var1x)));
      }

      boolean var15 = this.J.isEmpty();
      if (var15 && var1.editing) {
         this.J.add(new PotionHud.TypeR(class_1294.field_5904, Translations.tr("Speed II"), 1920, false, false, 0.7F));
         this.J.add(new PotionHud.TypeR(class_1294.field_5925, Translations.tr("Night Vision"), 3600, false, false, 0.95F));
         this.J.add(new PotionHud.TypeR(class_1294.field_5899, Translations.tr("Poison"), 180, false, true, 0.2F));
      }
   }

   private static String J(class_1293 var0, long var1) {
      PotionHud.TypeR.ensureSourceClassInitialized();
      String var3 = var0.method_5579().comp_349().method_5560().getString();
      int var4 = var0.method_5578();
      long var5 = 74740323468750L;
      if (74740323468750L == 0L) {
         boolean var10000 = false;
      } else {
         int var8 = var5 < 0L ? -1 : 1;
      }

      return var4 > 0 && var4 < b.length ? var3 + " " + b[var4] : var3;
   }

   private static String V(int var0, int var1, PotionHud.TypeR var2) {
      PotionHud.TypeR.ensureSourceClassInitialized();
      if (var2.infinite()) {
         return "∞";
      } else {
         int var3 = var2.duration() / 20;
         return String.format(Locale.ROOT, "%d:%02d", var3 / 60, var3 % 60);
      }
   }

   @Override
   protected boolean hasContent(HudBounds var1) {
      long var2 = 6605862167983L;
      long var4 = var2 ^ 8167861819046L;
      int var6 = (int)((var2 ^ 8167861819046L) >>> 48);
      int var7 = (int)((var2 ^ 8167861819046L) << 16 >>> 48);
      int var8 = (int)(var4 << 32 >>> 32);
      this.U(var1, (short)var6, (char)var7, var8);
      return !this.J.isEmpty();
   }

   @Override
   protected void measure(HudBounds var1, float[] var2) {
      long var3 = 137798753650226L;
      int var5 = (int)((var3 ^ 59731771353584L) >>> 32);
      int var6 = (int)((var3 ^ 59731771353584L) << 32 >>> 32);
      long var7 = var3 ^ 136599074698555L;
      int var9 = (int)((var3 ^ 136599074698555L) >>> 48);
      int var10 = (int)((var3 ^ 136599074698555L) << 16 >>> 48);
      int var11 = (int)(var7 << 32 >>> 32);
      this.U(var1, (short)var9, (char)var10, var11);
      float var12 = 0.0F;

      for (PotionHud.TypeR var14 : this.J) {
         float var15 = 19.0F + Fonts.SEMIBOLD.width(HudElementGroup.stable(V(var5, var6, var14)), 8.5F);
         if (this.x.enabled()) {
            var15 += Fonts.MEDIUM.width(var14.name(), 9.0F) + 10.0F;
         }

         var12 = Math.max(var12, var15);
      }

      var2[0] = 12.0F + Math.max(var12, 40.0F);
      var2[1] = 12.0F + Math.max(1, this.J.size()) * 21.0F - 4.0F;
   }

   @Override
   protected void render(Canvas var1, HudBounds var2) {
      long var3 = 26069153082894L;
      int var5 = (int)((var3 ^ 101932800377292L) >>> 32);
      int var6 = (int)((var3 ^ 101932800377292L) << 32 >>> 32);
      float var7 = this.contentWidth();
      this.panel(var1, var7, this.contentHeight());

      for (int var8 = 0; var8 < this.J.size(); var8++) {
         PotionHud.TypeR var9 = this.J.get(var8);
         float var10 = 6.0F + var8 * 21.0F;
         float var11 = var10 + 6.5F + 0.5F;
         boolean var12 = !var9.infinite() && var9.duration() < 200;
         float var13 = var12 ? 0.55F + 0.45F * (float)Math.abs(Math.cos(var2.time * 3.2)) : 1.0F;
         var1.pushAlpha(var13);
         float var14 = 25.0F;
         if (this.x.enabled()) {
            Translations.pause();
            this.text(var1, Fonts.MEDIUM, var9.name(), var14, var11, 9.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
            Translations.resume();
         }

         this.text(var1, Fonts.SEMIBOLD, V(var5, var6, var9), var7 - 6.0F, var11, 8.5F, Icons.TEXT_2, FontStyle.RIGHT, FontWeight.MIDDLE);
         var1.popAlpha();
         float var15 = var10 + 13.0F + 2.5F;
         float var16 = var7 - 12.0F - 13.0F - 6.0F;
         var1.roundRect(var14, var15, var16, 1.5F, 0.75F, 654311423);
         int var17 = var9.harmful() ? Icons.PINK : Icons.INDIGO;
         int var18 = var9.harmful() ? -30116 : Icons.MAGENTA;
         var1.roundRectGradient(var14, var15, Math.max(1.5F, var16 * Math.max(0.0F, Math.min(1.0F, var9.fraction()))), 1.5F, 0.75F, 0.0F, var17, var18);
      }
   }

   @Override
   protected void renderVanilla(HudDragState var1, HudBounds var2) {
      for (int var3 = 0; var3 < this.J.size(); var3++) {
         PotionHud.TypeR var4 = this.J.get(var3);
         boolean var5 = !var4.infinite() && var4.duration() < 200;
         float var6 = var5 ? 0.55F + 0.45F * (float)Math.abs(Math.cos(var2.time * 3.2)) : 1.0F;
         var1.sprite(class_329.method_71644(var4.effect()), 6.0F, 6.0F + var3 * 21.0F, 13.0F, var6);
      }
   }

   @Override
   protected void opened(ModuleContext var1) {
      if (this.K.enabled()) {
         var1.hideHudLayers(Set.of(HudLayer.STATUS_EFFECTS));
      }
   }

private record TypeR(class_6880<class_1291> K, String Q, int d, boolean b, boolean D, float G) {
      public static boolean J;

      public class_6880<class_1291> effect() {
         return this.K;
      }

      public String name() {
         return this.Q;
      }

      public int duration() {
         return this.d;
      }

      public boolean infinite() {
         return this.b;
      }

      public boolean harmful() {
         return this.D;
      }

      public float fraction() {
         return this.G;
      }

      public static void ensureSourceClassInitialized() {
      }

      public class_6880<class_1291> minecraftObjectState1() {
         return this.K;
      }

      public String stringState26() {
         return this.Q;
      }

      public int integerState34() {
         return this.d;
      }

      public boolean booleanState50() {
         return this.b;
      }

      public boolean booleanState15() {
         return this.D;
      }

      public float floatState28() {
         return this.G;
      }
   }
}
