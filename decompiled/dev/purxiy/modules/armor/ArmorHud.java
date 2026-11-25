package dev.purxiy.modules.armor;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudDragState;
import dev.purxiy.hud.HudModule;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_10192;
import net.minecraft.class_1304;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_3489;
import net.minecraft.class_9334;

public final class ArmorHud extends HudModule {
   private static final float Q = 13.0F;
   private static final float e = 5.0F;
   private static final float k = 17.0F;
   private static final float F = 8.5F;
   private static final class_1304[] H = new class_1304[]{class_1304.field_6169, class_1304.field_6174, class_1304.field_6172, class_1304.field_6166};
   private static final int I = -7670328;
   private static final int K = -11654;
   private static final int E = -41852;
   private final ModeSetting q = this.mode("layout", "Layout", null, "Vertical", new String[]{"Vertical", "Horizontal"});
   private final ModeSetting S = this.mode("durability", "Durability", null, "Value", new String[]{"Value", "Percent", "Bar only"});
   private final BooleanSetting G = this.bool("held", "Held items", "Main and off hand as well", true);
   private final List<class_1799> h = new ArrayList<>();
   private List<class_1799> B;
   public static boolean X;

   public ArmorHud() {
      super("armor_status", "Armor Status", "Durability of everything you wear", "shield-half", 1.0, 0.72);
      this.enabledByDefault();
   }

   private List r(long var1) {
      ArmorHud.TypeT.ensureSourceClassInitialized();
      if (this.B == null) {
         this.B = List.of(
            new class_1799(class_1802.field_8805),
            new class_1799(class_1802.field_8058),
            new class_1799(class_1802.field_8348),
            new class_1799(class_1802.field_8285),
            new class_1799(class_1802.field_22022)
         );

         for (int var3 = 0; var3 < this.B.size(); var3++) {
            long var4 = 83577373988411L;
            int var7 = var4 < 0L ? -1 : 1;
            class_1799 var7x = this.B.get(var3);
            var7x.method_7974((int)(var7x.method_7936() * (0.1F + 0.2F * var3)));
         }
      }

      return this.B;
   }

   private void p(long var1, HudBounds var3) {
      long var4 = 83577373988411L;
      ArmorHud.TypeT.ensureSourceClassInitialized();
      this.h.clear();
      if (!var3.live()) {
         if (var3.editing) {
            this.h.addAll(this.r(var4));
         }
      } else {
         for (class_1304 var9 : H) {
            class_1799 var10 = var3.player.method_6118(var9);
            long var11 = 63589241098169L;
            int var14 = var11 < 0L ? -1 : 1;
            long var14x = 63589241098169L;
            int var31 = var14x < 0L ? -1 : 1;
            boolean var16 = var10.method_7960();
            long var17 = 63589241098168L;
            int var18 = var17 < 0L ? -1 : 1;
            if (!var16) {
               this.h.add(var10);
            }
         }

         long var23 = 63589241098168L;
         int var23x = var23 < 0L ? -1 : 1;
         boolean var24 = this.G.enabled();
         long var27 = 63589241098169L;
         int var281 = var27 < 0L ? -1 : 1;
         if (var24) {
            for (class_1799 var30 : new class_1799[]{var3.player.method_6047(), var3.player.method_6079()}) {
               long var31 = 63589241098168L;
               int var19 = var31 < 0L ? -1 : 1;
               long var19x = 63589241098168L;
               int var19x = var19x < 0L ? -1 : 1;
               var24 = var30.method_7960();
               long var21x = 63589241098169L;
               int var21x = var21x < 0L ? -1 : 1;
               if (!var24) {
                  this.h.add(var30);
               }
            }
         }

         var24 = this.h.isEmpty();
         long var28x = 63589241098169L;
         int var33 = var28x < 0L ? -1 : 1;
         if (var24 && var3.editing) {
            this.h.addAll(this.r(var4));
         }
      }
   }

   protected boolean hasContent(HudBounds var1) {
      long var2 = 96537112422049L;
      long var4 = var2 ^ 121054901807384L;
      this.p(var4, var1);
      return !this.h.isEmpty();
   }

   private boolean d(long var1) {
      return this.q.is("Horizontal");
   }

   protected boolean mergeable() {
      return true;
   }

   protected void renderMiniature(Canvas var1, HudBounds var2) {
      long var3 = 105351810363349L;
      long var5 = var3 ^ 29906544265064L;
      long var7 = var3 ^ 70112986267588L;
      this.render(var1, var2);

      for (int var9 = 0; var9 < this.h.size(); var9++) {
         float[] var10 = this.v(var7, var9);
         class_1799 var11 = this.h.get(var9);
         var1.roundRect(var10[0] + 0.5F, var10[1] + 0.5F, 12.0F, 12.0F, 3.0F, 536870911);
         var1.icon(r(var5, var11), var10[0] + 6.5F, var10[1] + 6.5F, 8.06F, Icons.TEXT_2);
      }
   }

   private static String r(long var0, class_1799 var2) {
      ArmorHud.TypeT.ensureSourceClassInitialized();
      class_10192 var3 = (class_10192)var2.method_58694(class_9334.field_54196);
      if (var3 != null) {
         int var12 = ArmorHud.TypeT.J[var3.comp_3174().ordinal()];
         long var13 = 75737617475773L;
         int var15 = var13 < 0L ? -1 : 1;

         return switch (var12) {
            case 1 -> "hard-hat";
            case 2 -> "shirt";
            case 3 -> "footprints";
            default -> "shield-half";
         };
      } else {
         boolean var4 = var2.method_31573(class_3489.field_42611);
         long var5 = 75737617475773L;
         int var8 = var5 < 0L ? -1 : 1;
         String var8x;
         if (var4) {
            var8x = "sword";
         } else {
            var4 = var2.method_31573(class_3489.field_42612);
            long var9 = 75737617475772L;
            int var10 = var9 < 0L ? -1 : 1;
            var8x = var4 ? "axe" : "box";
         }

         return var8x;
      }
   }

   private String B(long var1, class_1799 var3) {
      ArmorHud.TypeT.ensureSourceClassInitialized();
      if (!var3.method_7963()) {
         return var3.method_7947() > 1 ? Integer.toString(var3.method_7947()) : "";
      } else {
         int var4 = var3.method_7936() - var3.method_7919();
         String var5 = (String)this.S.get();
         byte var6 = -1;
         switch (var5.hashCode()) {
            case -1748399175:
               boolean var7 = var5.equals("Bar only");
               long var8 = 130828646028520L;
               int var13 = var8 < 0L ? -1 : 1;
               if (var7) {
                  var6 = 1;
               }
               break;
            case 985725989:
               if (var5.equals("Percent")) {
                  long var11 = 130828646028520L;
                  int var12 = var11 < 0L ? -1 : 1;
                  var6 = 0;
               }
         }
         return switch (var6) {
            case 0 -> Math.round(100.0F * var4 / var3.method_7936()) + "%";
            case 1 -> "";
            default -> Integer.toString(var4);
         };
      }
   }

   private float r(short var1, int var2, int var3) {
      return this.S.is("Bar only") ? 23.0F : 19.0F + Fonts.SEMIBOLD.width("0000", 8.5F);
   }

   protected void measure(HudBounds var1, float[] var2) {
      long var3 = 95275960731473L;
      long var5 = var3 ^ 131694569203353L;
      long var7 = var3 ^ 122538368370920L;
      long var9 = var3 ^ 97440555150961L;
      int var11 = (int)((var3 ^ 97440555150961L) >>> 48);
      int var12 = (int)((var3 ^ 97440555150961L) << 16 >>> 32);
      int var13 = (int)(var9 << 48 >>> 48);
      this.p(var7, var1);
      int var14 = Math.max(1, this.h.size());
      if (this.d(var5)) {
         var2[0] = 10.0F + var14 * this.r((short)var11, var12, var13) + (var14 - 1) * 4;
         var2[1] = 23.0F;
      } else {
         var2[0] = 10.0F + this.r((short)var11, var12, var13);
         var2[1] = 10.0F + var14 * 17.0F - 2.0F;
      }
   }

   private float[] v(long var1, int var3) {
      long var4 = 36703210283464L;
      long var6 = 15633889012000L;
      byte var8 = 0;
      int var9 = 238554214;
      int var10 = (int)(var6 << 48 >>> 48);
      ArmorHud.TypeT.ensureSourceClassInitialized();
      return this.d(var4) ? new float[]{5.0F + var3 * (this.r(var8, var9, var10) + 4.0F), 3.0F} : new float[]{5.0F, 5.0F + var3 * 17.0F - 1.0F};
   }

   protected void render(Canvas var1, HudBounds var2) {
      long var3 = 88386520202039L;
      long var5 = var3 ^ 42468600623071L;
      long var7 = var3 ^ 53284534217510L;
      this.panel(var1, this.contentWidth(), this.contentHeight());

      for (int var9 = 0; var9 < this.h.size(); var9++) {
         class_1799 var10 = this.h.get(var9);
         float[] var11 = this.v(var7, var9);
         float var12 = var11[0];
         float var13 = var11[1];
         String var14 = this.B(var5, var10);
         if (!var14.isEmpty()) {
            this.text(var1, Fonts.SEMIBOLD, var14, var12 + 13.0F + 5.0F, var13 + 8.5F - 1.0F, 8.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
         }

         if (var10.method_7963()) {
            float var15 = 1.0F - (float)var10.method_7919() / var10.method_7936();
            int var16 = var15 > 0.5F ? BlendMode.lerp(-11654, -7670328, (var15 - 0.5F) * 2.0F) : BlendMode.lerp(-41852, -11654, var15 * 2.0F);
            float var17 = this.S.is("Bar only") ? 19.0F : 13.0F;
            float var18 = var13 + 13.0F + 1.5F;
            var1.roundRect(var12, var18, var17, 1.6F, 0.8F, 872415231);
            var1.roundRect(var12, var18, Math.max(1.6F, var17 * var15), 1.6F, 0.8F, var16);
         }
      }
   }

   protected void renderVanilla(HudDragState var1, HudBounds var2) {
      long var3 = 78431161654321L;
      long var5 = var3 ^ 43157964180512L;

      for (int var7 = 0; var7 < this.h.size(); var7++) {
         float[] var8 = this.v(var5, var7);
         var1.item(this.h.get(var7), var8[0], var8[1], 13.0F);
      }
   }

   static class TypeT {
      static final int[] J = new int[class_1304.values().length];
      public static boolean Y;

      public static void ensureSourceClassInitialized() {
      }

      static {
         try {
            J[class_1304.field_6169.ordinal()] = 1;
         } catch (NoSuchFieldError var3) {
         }

         try {
            J[class_1304.field_6174.ordinal()] = 2;
         } catch (NoSuchFieldError var2) {
         }

         try {
            J[class_1304.field_6166.ordinal()] = 3;
         } catch (NoSuchFieldError var1) {
         }
      }
   }
}
