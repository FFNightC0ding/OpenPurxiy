package dev.purxiy.modules.weather;

import dev.purxiy.loader.fabric.ClientServices;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1101;
import net.minecraft.class_1113;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_3419;
import net.minecraft.class_5819;
import net.minecraft.class_638;
import net.minecraft.class_1113.class_1114;
import net.minecraft.class_2902.class_2903;

public final class WeatherUpdater {
   private static final class_3414 k = x((short)0, "rain", 27656449962640L);
   private static final class_3414 T = x((short)0, "snow", 27656449962640L);
   private static final class_3414 f = x((short)0, "fire", 27656449962640L);
   private static final class_3414 w = x((short)0, "wind", 27656449962640L);
   private static final class_3414 q = x((short)0, "thunder", 27656449962640L);
   private static final List<WeatherUpdater.WeatherEntity> O = new ArrayList<>();
   private static final List<WeatherUpdater.WeatherEntity> Z = new ArrayList<>();
   private static final List<WeatherUpdater.WeatherDrop> l = new ArrayList<>();
   private static class_638 s;
   private static WeatherUpdater.WeatherEntity x;
   private static WeatherUpdater.WeatherEntity V;
   private static String o = "";
   private static long B = 0L;
   private static int Q = 0;
   private static float G = 1.0F;
   private static boolean X;
   private static final long[] c = new long[]{2603978691604866104L, 4020877952481369652L};
   private static final Long[] d = new Long[2];

   private WeatherUpdater() {
   }

   private static class_3414 x(short var0, String var1, long var2) {
      return class_3414.method_47908(ClientServices.id("weather." + var1));
   }

   public static void tick(class_310 var0) {
      long var1 = 18005957105502L;
      long var3 = var1 ^ 62257135338323L;
      long var5 = var1 ^ 128758287123501L;
      int var7 = (int)((var1 ^ 128758287123501L) >>> 32);
      int var8 = (int)((var1 ^ 128758287123501L) << 32 >>> 48);
      int var9 = (int)(var5 << 48 >>> 48);
      long var10 = var1 ^ 52132564555751L;
      long var12 = var1 ^ 6866691832889L;
      long var14 = var1 ^ 38320001944601L;
      if (!L(var3, var0)) {
         stop();
      } else {
         if (s != var0.field_1687) {
            stop();
            s = var0.field_1687;
            G = X(var7, var0, var8, var9);
         }

         X = true;
         B++;
         String var16 = (String)WeatherParticlePass.K.t.get();
         if (!var16.equals(o)) {
            if (x != null) {
               u(x);
            }

            x = null;
            o = var16;
            m(var0, var14);
         }

         if (B % 5L == 1L) {
            G = G + (X(var7, var0, var8, var9) - G) * 0.3F;
         }

         float var17 = WeatherParticlePass.K.Q.floatValue() * G;
         float var18 = Math.clamp(WeatherParticlePass.K.O.floatValue(), 0.1F, 2.0F);
         float var19 = Math.clamp(WeatherParticlePass.K.B.floatValue(), 0.0F, 2.0F);
         boolean var20 = var16.equals("Rain") && WeatherParticlePass.K.j.enabled();

         class_3414 var21 = switch (var16) {
            case "Snow" -> T;
            case "Fire" -> f;
            default -> k;
         };
         x = N(var0, x, var10, var21);
         float var22 = var16.equals("Snow") ? 0.4F : (var16.equals("Fire") ? 0.72F : 0.85F);
         x.target(var17 * var22 * (0.35F + 0.4F * var18), 1.0F);
         float var28 = var17 * (0.045F + var19 * 0.1F + (var20 ? 0.17F : 0.0F));
         V = N(var0, V, var10, w);
         V.target(var28, 0.9F + var19 * 0.05F);
         X(var12, var0, O);
         X(var12, var0, Z);
         if (!var20) {
            m(var0, var14);
         } else {
            for (int var24 = l.size() - 1; var24 >= 0; var24--) {
               WeatherUpdater.WeatherDrop var25 = l.get(var24);
               if (B >= var25.P) {
                  l.remove(var24);
                  if (Z.size() < 4) {
                     float var26 = (float)(1.0 / (1.0 + var25.y / 180.0));
                     WeatherUpdater.WeatherEntity var27 = new WeatherUpdater.WeatherEntity(
                        q, false, WeatherParticlePass.K.Q.floatValue() * var26 * (0.4F + 0.6F * G), 0.9F + (float)Math.random() * 0.13F
                     );
                     Z.add(var27);
                     var0.method_1483().method_4873(var27);
                  }
               }
            }
         }
      }
   }

   public static void thunder(double var0) {
      long var2 = 73053429241860L;
      long var4 = var2 ^ 117158578326537L;
      class_310 var6 = class_310.method_1551();
      if (X
         && s == var6.field_1687
         && L(var4, var6)
         && WeatherParticlePass.K.j.enabled()
         && WeatherParticlePass.K.t.is("Rain")
         && Double.isFinite(var0)
         && l.size() < 6) {
         var0 = Math.max(0.0, var0);
         long var7 = Math.clamp((long)Math.ceil(var0 / 343.0 * 20.0), 1, 80);
         l.add(new WeatherUpdater.WeatherDrop(B + var7, var0));
      }
   }

   public static void stop() {
      if (X || s != null || x != null || V != null || !O.isEmpty() || !Z.isEmpty() || !l.isEmpty()) {
         class_310 var0 = class_310.method_1551();
         X = false;
         Q++;
         if (x != null) {
            x.finish(var0);
         }

         if (V != null) {
            V.finish(var0);
         }

         for (WeatherUpdater.WeatherEntity var2 : O) {
            var2.finish(var0);
         }

         for (WeatherUpdater.WeatherEntity var4 : Z) {
            var4.finish(var0);
         }

         O.clear();
         Z.clear();
         l.clear();
         V = null;
         x = null;
         s = null;
         o = "";
         B = 0L;
         G = 1.0F;
      }
   }

   public static int ownedVoices() {
      return (x != null && !x.method_4793() ? 1 : 0) + (V != null && !V.method_4793() ? 1 : 0) + (int)O.stream().filter(var0 -> {
         WeatherModule.ensureSourceClassInitialized();
         return !var0.method_4793();
      }).count() + (int)Z.stream().filter(var0 -> {
         WeatherModule.ensureSourceClassInitialized();
         return !var0.method_4793();
      }).count();
   }

   public static int pendingThunder() {
      return l.size();
   }

   private static boolean L(long var0, class_310 var2) {
      WeatherModule.ensureSourceClassInitialized();
      if (WeatherParticlePass.K.enabled() && WeatherParticlePass.K.x.enabled() && (Double)WeatherParticlePass.K.Q.get() > 0.0) {
         long var3 = 45075828741133L;
         int var6 = var3 < 0L ? -1 : 1;
         if (var2.field_1687 != null) {
            long var6x = 45075828741133L;
            int var8 = var6x < 0L ? -1 : 1;
            if (var2.field_1724 != null) {
               long var8x = 45075828741133L;
               int var9 = var8x < 0L ? -1 : 1;
               if (var2.field_1724.method_5805() && !var2.method_1493() && var2.field_1690.method_71978(class_3419.field_15252) > 0.0F) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private static float X(int var0, class_310 var1, int var2, int var3) {
      WeatherModule.ensureSourceClassInitialized();
      if (var1.field_1724.method_5869()) {
         return 0.035F;
      } else {
         int var4 = var1.field_1724.method_31477();
         int var5 = var1.field_1724.method_31479();
         int var6 = var1.field_1687.method_8624(class_2903.field_13197, var4, var5);
         double var7 = var6 - var1.field_1724.method_23320();
         return var7 <= 0.0 ? 1.0F : (float)(0.28 * Math.clamp(1.0 - var7 / 20.0, 0.0, 1.0));
      }
   }

   private static void m(class_310 var0, long var1) {
      WeatherModule.ensureSourceClassInitialized();
      l.clear();

      for (WeatherUpdater.WeatherEntity var4 : Z) {
         long var5 = 55775598205766L;
         int var7 = var5 < 0L ? -1 : 1;
         var4.finish(var0);
      }

      long var8 = 55775598205767L;
      int var9 = var8 < 0L ? -1 : 1;
      Z.clear();
   }

   private static WeatherUpdater.WeatherEntity N(class_310 var0, WeatherUpdater.WeatherEntity var1, long var2, class_3414 var4) {
      WeatherModule.ensureSourceClassInitialized();
      if (var1 != null) {
         long var5 = 69313663893688L;
         int var8 = var5 < 0L ? -1 : 1;
         boolean var8x = var1.method_4793();
         long var9 = 69313663893688L;
         int var10 = var9 < 0L ? -1 : 1;
         if (!var8x && (B - var1.G < 40L || var0.method_1483().method_4877((class_1113)var1))) {
            return var1;
         }
      }

      if (var1 != null) {
         var1.finish(var0);
      }

      WeatherUpdater.WeatherEntity var12 = new WeatherUpdater.WeatherEntity(var4, true, 0.0F, 1.0F);
      var0.method_1483().method_4873(var12);
      return var12;
   }

   private static void u(WeatherUpdater.WeatherEntity var0) {
      var0.T = true;
      var0.target(0.0F, 1.0F);
      O.add(var0);
   }

   private static void X(long var0, class_310 var2, List var3) {
      var3.removeIf(var1 -> {
         WeatherModule.ensureSourceClassInitialized();
         long var2x = 117780800231801L;
         int var5 = var2x < 0L ? -1 : 1;
         boolean var5x = ((WeatherUpdater.WeatherEntity)var1).method_4793();
         long var6 = 117780800231801L;
         int var7 = var6 < 0L ? -1 : 1;
         if (var5x || B - ((WeatherUpdater.WeatherEntity)var1).G >= 40L && !var2.method_1483().method_4877((class_1113)var1)) {
            ((WeatherUpdater.WeatherEntity)var1).finish(var2);
            return true;
         } else {
            return false;
         }
      });
   }

   private static long a(int var0, long var1) {
      byte var3 = 1;
      if (d[var3] == null) {
         d[var3] = c[var3] ^ 4020877952481369628L;
      }

      return d[var3];
   }

   private record WeatherDrop(long P, double y) {
      public long due() {
         return this.P;
      }

      public double distance() {
         return this.y;
      }

      public long longState34() {
         return this.P;
      }

      public double doubleState22() {
         return this.y;
      }
   }

   private static final class WeatherEntity extends class_1101 {
      private final class_638 l = WeatherUpdater.s;
      private final int h = WeatherUpdater.Q;
      private final long G = WeatherUpdater.B;
      private final boolean X;
      private final float J;
      private float D;
      private float U;
      private boolean T;
      private int g;
      private static final String a = "Rain";

      WeatherEntity(class_3414 var1, boolean var2, float var3, float var4) {
         super(var1, class_3419.field_15252, class_5819.method_43047());
         this.X = this.field_5446 = var2;
         this.J = var3 / Math.max(0.001F, WeatherParticlePass.K.Q.floatValue());
         this.field_5451 = 0;
         this.field_18936 = true;
         this.field_5440 = class_1114.field_5478;
         this.field_5439 = this.field_5450 = this.field_5449 = 0.0;
         this.field_5442 = this.D = var3;
         this.field_5441 = this.U = var4;
      }

      void target(float var1, float var2) {
         this.D = Math.clamp(var1, 0.0F, 1.0F);
         this.U = Math.clamp(var2, 0.5F, 2.0F);
      }

      public boolean method_4785() {
         return this.X;
      }

      public boolean method_26273() {
         long var1 = 58327182784706L;
         long var3 = var1 ^ 32930919361743L;
         class_310 var5 = class_310.method_1551();
         return !this.method_4793()
            && WeatherUpdater.X
            && this.h == WeatherUpdater.Q
            && this.l == var5.field_1687
            && WeatherUpdater.L(var3, var5)
            && (this.X || WeatherParticlePass.K.j.enabled() && WeatherParticlePass.K.t.is("Rain"));
      }

      public void method_16896() {
         class_310 var1 = class_310.method_1551();
         this.g++;
         if (this.method_26273() && (this.X || this.g <= 320)) {
            if (!this.X) {
               this.D = this.J * WeatherParticlePass.K.Q.floatValue();
            }

            this.field_5442 = this.field_5442 + (this.D - this.field_5442) * 0.13F;
            this.field_5441 = this.field_5441 + (this.U - this.field_5441) * 0.1F;
            if (this.T && this.field_5442 < 0.001F) {
               this.finish(var1);
            }
         } else {
            this.finish(var1);
         }
      }

      void finish(class_310 var1) {
         this.method_24876();
         var1.method_1483().method_4870(this);
      }
   }
}
