package dev.purxiy.modules.mount;

import dev.purxiy.loader.fabric.ClientServices;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1101;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_3419;
import net.minecraft.class_5819;
import net.minecraft.class_638;
import net.minecraft.class_1113.class_1114;

public final class MountUpdater {
   private static final List<MountUpdater.MountEntity> y = new ArrayList<>();
   private static MountUpdater.MountEntity M;
   private static MountUpdater.MountEntity O;
   private static MountUpdater.MountEntity l;
   private static class_638 r;
   private static String L = "";
   private static MountFactory.MountSpec z;
   private static float b;
   private static float s;
   private static float w;
   private static int d = 0;
   private static int U = 0;
   private static int Y = 0;
   private static int N = 0;
   private static int I = 0;
   private static int R = 0;
   private static boolean G;
   private static boolean j;
   private static final String[] c = new String[10];

   private MountUpdater() {
   }

   private static class_3414 G(long var0, String var2) {
      return class_3414.method_47908(ClientServices.id("mounts." + var2));
   }

   private static String p(String var0) {
      return z.engine() + "_" + var0;
   }

   public static void start(MountFactory.MountSpec var0) {
      long var1 = 20365310275460L;
      long var3 = var1 ^ 49791204147480L;
      long var5 = var1 ^ 7030923777236L;
      long var7 = var1 ^ 132671819149595L;
      stop();
      class_310 var9 = class_310.method_1551();
      z = var0;
      if (Y(var5, var9)) {
         r = var9.field_1687;
         G = true;
         L = var0.id();
         I(G(var7, p("start")), var3, 1.0F, 0.95F);
      }
   }

   public static void tick(
      class_310 var0, boolean var1, MountFactory.MountSpec var2, float var3, float var4, float var5, int var6, float var7, float var8, float var9
   ) {
      long var10 = 136325482300832L;
      long var12 = (var10 ^ 124011465606588L) >>> 32;
      int var14 = (int)((var10 ^ 124011465606588L) << 32 >>> 32);
      long var15 = var10 ^ 74981537917756L;
      long var17 = var10 ^ 122166440967920L;
      long var19 = var10 ^ 19598007147327L;
      if (var1 && Y(var17, var0)) {
         if (r != var0.field_1687 || !L.equals(var2.id())) {
            stop();
            r = var0.field_1687;
            L = var2.id();
         }

         z = var2;
         G = true;
         Y++;
         if (d > 0) {
            d--;
         }

         if (U > 0) {
            U--;
         }

         b = b + (c(var3) - b) * 0.27F;
         s = s + (c(var4) - s) * 0.34F;
         float var21 = MountCameraReader.K.i.floatValue();
         float var22 = var2.hasEngine() ? n((var5 - 0.38F) / 0.62F) : c(var5 * 1.35F);
         y.removeIf(var1x -> {
            MountFactory.MountSpec.ensureSourceClassInitialized();
            long var2x = 39716900874611L;
            int var5x = var2x < 0L ? -1 : 1;
            var5x = var1x.method_4793() ? 1 : 0;
            long var6x = 39716900874610L;
            int var9x = var6x < 0L ? -1 : 1;
            if (var5x == 0) {
               long var9xx = 39716900874610L;
               int var11 = var9xx < 0L ? -1 : 1;
               var5x = Y - var1x.J;
               long var11x = 39716900874611L;
               if (var5x <= 10 || var0.method_1483().method_4877(var1x)) {
                  return false;
               }
            }

            var1x.finish(var0);
            return true;
         });
         if (!var2.hasEngine()) {
            M = t(var12, var0, var14, M, G(var19, p("roll")));
            O = t(var12, var0, var14, O, G(var19, p("pedal")));
            float var23 = c(var7 / 5.6F);
            M.target(var23 * 0.75F * var21 * var22, 0.85F + var23 * 0.3F);
            O.target((var4 > 0.1F ? var23 : 0.0F) * 0.65F * var21 * var22, 0.8F + var23 * 0.5F);
         } else {
            M = t(var12, var0, var14, M, G(var19, p("idle")));
            O = t(var12, var0, var14, O, G(var19, p("low")));
            l = t(var12, var0, var14, l, G(var19, p("high")));
            float var31 = 0.42F + 0.58F * s;
            float var24 = 1.0F - n(b / 0.34F);
            float var25 = n(b / 0.28F) * (1.0F - n((b - 0.52F) / 0.34F));
            float var26 = n((b - 0.46F) / 0.42F);
            float var27 = 0.82F + b * 1.1F;
            float var28 = 0.72F + b * 1.0F;
            float var29 = 0.68F + b * 0.62F;
            M.target(0.85F * var24 * var21 * var22, var27);
            float var30 = U > 4 ? 0.55F : 1.0F;
            O.target(0.92F * var25 * var31 * var21 * var22 * var30, var28);
            l.target(1.0F * var26 * var31 * var21 * var22 * var30, var29);
            if ((var4 > 0.65F || var8 > 0.6F) && b > 0.35F) {
               j = true;
            }

            if (j && var4 < 0.28F && var8 < 0.25F) {
               if (b > 0.25F && d == 0) {
                  I(G(var19, p("pop")), var15, 0.93F + b * 0.13F, 0.46F + b * 0.16F);
                  d = 18;
               }

               j = false;
            }

            w = var8;
         }
      } else {
         stop();
      }
   }

   public static void shift() {
      long var0 = 97743257407549L;
      long var2 = var0 ^ 113425258643105L;
      long var4 = var0 ^ 55843627224738L;
      if (G && U <= 0 && z != null && z.hasEngine()) {
         U = 8;
         I(G(var4, p("shift")), var2, 0.98F + b * 0.06F, 0.85F);
      }
   }

   public static void stunt(MountFactory.MountSpec var0) {
   }

   public static void horn() {
      long var0 = 91279887552671L;
      long var2 = var0 ^ 119612714335747L;
      long var4 = var0 ^ 62858909355520L;
      if (z == null) {
         z = MountInteraction.spec();
      }

      if (G) {
         I(G(var4, p("horn")), var2, 1.0F, 0.9F);
      }
   }

   public static void stop() {
      class_310 var0 = class_310.method_1551();
      G = false;
      N++;
      if (M != null) {
         M.finish(var0);
      }

      if (O != null) {
         O.finish(var0);
      }

      if (l != null) {
         l.finish(var0);
      }

      for (MountUpdater.MountEntity var2 : y) {
         var2.finish(var0);
      }

      y.clear();
      l = null;
      O = null;
      M = null;
      r = null;
      L = "";
      w = 0.0F;
      s = 0.0F;
      b = 0.0F;
      R = 0;
      I = 0;
      Y = 0;
      U = 0;
      d = 0;
      j = false;
   }

   private static boolean Y(long var0, class_310 var2) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      long var3 = 22955134516048L;
      int var6 = var3 < 0L ? -1 : 1;
      if (MountCameraReader.K.enabled() && MountCameraReader.K.d.enabled() && (Double)MountCameraReader.K.i.get() > 0.0) {
         long var6x = 22955134516048L;
         int var8 = var6x < 0L ? -1 : 1;
         if (var2.field_1724 != null) {
            long var8x = 22955134516048L;
            int var10 = var8x < 0L ? -1 : 1;
            if (var2.field_1687 != null) {
               long var10x = 22955134516048L;
               int var12 = var10x < 0L ? -1 : 1;
               if (var2.field_1724.method_5805()) {
                  long var12x = 22955134516048L;
                  int var14 = var12x < 0L ? -1 : 1;
                  if (!var2.method_1493()) {
                     long var14x = 22955134516048L;
                     int var15 = var14x < 0L ? -1 : 1;
                     if (var2.field_1755 == null
                        && var2.method_18506() == null
                        && var2.method_1569()
                        && var2.field_1690.method_71978(class_3419.field_15248) > 0.0F) {
                        return true;
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private static MountUpdater.MountEntity t(long var0, class_310 var2, int var3, MountUpdater.MountEntity var4, class_3414 var5) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      if (var4 != null) {
         boolean var6 = var4.method_4793();
         long var7 = 2868L;
         int var10 = var7 < 0L ? -1 : 1;
         if (!var6) {
            long var10x = 2868L;
            int var11 = var10x < 0L ? -1 : 1;
            if (Y - var4.J < 25 || var2.method_1483().method_4877(var4)) {
               return var4;
            }
         }
      }

      if (var4 != null) {
         var4.finish(var2);
      }

      MountUpdater.MountEntity var12 = new MountUpdater.MountEntity(var5, true, 0.0F, 1.0F);
      var2.method_1483().method_4873(var12);
      return var12;
   }

   private static void I(class_3414 var0, long var1, float var3, float var4) {
      long var5 = 22955134516048L;
      MountFactory.MountSpec.ensureSourceClassInitialized();
      class_310 var7 = class_310.method_1551();
      if (Y(var5, var7) && G && r == var7.field_1687) {
         MountUpdater.MountEntity var8 = new MountUpdater.MountEntity(var0, false, var4 * MountCameraReader.K.i.floatValue(), var3);
         y.add(var8);
         var7.method_1483().method_4873(var8);
      }
   }

   private static float c(float var0) {
      return Math.clamp(var0, 0.0F, 1.0F);
   }

   private static float n(float var0) {
      var0 = c(var0);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static final class MountEntity extends class_1101 {
      private final class_638 b;
      private final boolean S;
      private final int J;
      private final int R;
      private final float M;
      private float F;
      private float t;
      private int g;
      private int O;

      MountEntity(class_3414 var1, boolean var2, float var3, float var4) {
         super(var1, class_3419.field_15248, class_5819.method_43047());
         long var5 = 99976693496366L;
         long var7 = var5 ^ 117854382573885L;
         this.O = 160;
         this.b = MountUpdater.r;
         this.S = var2;
         this.J = MountUpdater.Y;
         this.R = MountUpdater.N;
         float var9 = MountCameraReader.K.i.floatValue();
         this.M = var9 > 0.0F ? var3 / var9 : 0.0F;
         this.field_5446 = var2;
         this.field_5451 = 0;
         this.field_18936 = false;
         this.field_5440 = class_1114.field_5476;
         this.field_5442 = this.F = var3;
         this.field_5441 = this.t = var4;
         this.g(var7, class_310.method_1551());
      }

      void target(float var1, float var2) {
         this.F = Math.max(0.0F, var1);
         this.t = Math.clamp(var2, 0.5F, 2.0F);
      }

      public boolean method_4785() {
         return this.S;
      }

      public boolean method_26273() {
         long var1 = 76906676635837L;
         long var3 = var1 ^ 89140226372589L;
         class_310 var5 = class_310.method_1551();
         return !this.method_4793() && MountUpdater.G && this.R == MountUpdater.N && this.b == var5.field_1687 && MountUpdater.Y(var3, var5);
      }

      public void method_16896() {
         long var1 = 102822791351502L;
         long var3 = var1 ^ 119133603823581L;
         class_310 var5 = class_310.method_1551();
         this.g++;
         if (this.method_26273() && (this.S || this.g <= this.O)) {
            if (!this.S) {
               this.F = this.M * MountCameraReader.K.i.floatValue();
            }

            this.field_5442 = this.field_5442 + (this.F - this.field_5442) * 0.28F;
            this.field_5441 = this.field_5441 + (this.t - this.field_5441) * 0.34F;
            this.g(var3, var5);
         } else {
            this.finish(var5);
         }
      }

      private void g(long var1, class_310 var3) {
         if (var3.field_1724 != null) {
            class_243 var4 = MountInteraction.renderPosition(1.0F);
            double var5 = Math.toRadians(MountInteraction.yaw(1.0F));
            double var7 = 0.9;
            this.field_5439 = var4.field_1352 + Math.sin(var5) * var7;
            this.field_5450 = var4.field_1351 + 0.55;
            this.field_5449 = var4.field_1350 - Math.cos(var5) * var7;
         }
      }

      void finish(class_310 var1) {
         this.method_24876();
         var1.method_1483().method_4870(this);
      }
   }
}
