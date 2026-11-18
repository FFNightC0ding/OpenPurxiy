// Sourced By DevFFNightmare
package dev.purxiy.modules.freelook;

import dev.purxiy.anim.Easing;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientRegistry13;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_3675;
import net.minecraft.class_3726;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_5498;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public final class FreelookModule extends ModuleDescriptor {
   public static final double e = 1.5;
   public static final double q = 12.0;
   private static final double r = 0.15;
   private static final float I = 90.0F;
   private static final float A = 0.45F;
   private static final float R = 0.42F;
   private static final double U = 0.3;
   private static final double Z = 1.12;
   private static final double b = 0.45;
   private static final double j = 1.2;
   private static final double X = 0.35;
   private static final double g = 9.0;
   private static final double O = 0.0625;
   public final ModeSetting l;
   public final BooleanSetting m;
   public final NumberSetting J;
   public final NumberSetting v;
   public final BooleanSetting f;
   private boolean t;
   private boolean L;
   
   private class_5498 z;
   
   private class_638 S;
   private float N;
   private float D;
   private float a;
   private float Q;
   private float H;
   private float Y;
   private float E;
   private long h;
   private float o;
   private float s;
   private double M = 4.0;
   private double u = 4.0;
   private double B;
   private double n;
   private boolean G = true;
   private int x = -1;
   private double d;
   private long p;
   private double T;
   private int K;
   private long i;
   public static boolean k;


   private static final long y = 2544982781050486964L;

   public FreelookModule() {
      super("freelook", "Freelook", "Look around without turning", "scan-eye");
      this.setKey(342);
      this.enabledByDefault();
      this.l = this.mode("activation", "Activation", "Hold the key or click to toggle", "Hold", new String[]{"Hold", "Toggle"});
      this.m = this.bool("animation", "Animation", "Glide from close to far and back. Off, it jumps at once", true);
      this.J = this.number(
         "animation_speed", "Animation speed", "How fast the camera glides out and back", 1.0, 0.25, 3.0, 0.05, NumberSetting.Listener.MULTIPLIER
      );
      this.J.visibleWhen(this.m::enabled);
      this.v = this.number("distance", "Distance", "How far away it floats. Scroll to change it", 4.0, 1.5, 12.0, 0.1, NumberSetting.Listener.unit(" blocks"));
      this.f = this.bool("through_walls", "Through walls", "See yourself through the wall behind you", false);
   }

   @Override
   public boolean keybindToggles() {
      return false;
   }

   @Override
   protected void onDisable() {
      long var1 = 139613134365837L;
      long var3 = var1 ^ 675150503766L;
      this.G(var3, class_310.method_1551());
   }

   public boolean active() {
      return this.z != null;
   }

   public boolean engaged() {
      return this.L;
   }

   public void onKey(int var1, int var2) {
      long var3 = 99417144606692L;
      long var5 = var3 ^ 44378236167416L;
      long var7 = var3 ^ 39968039596875L;
      long var9 = var3 ^ 109910661989877L;
      int var11 = (int)((var3 ^ 109910661989877L) >>> 32);
      int var12 = (int)((var3 ^ 109910661989877L) << 32 >>> 48);
      int var13 = (int)(var9 << 48 >>> 48);
      if (var1 != -1 && var1 == this.key()) {
         class_310 var14 = class_310.method_1551();
         boolean var15 = this.l.is("Toggle");
         if (var2 == 0) {
            this.t = false;
            if (!var15) {
               this.s(var7);
            }
         } else if (var2 == 1 && !this.t) {
            this.t = true;
            if (var14.field_1755 != null || var14.method_18506() != null) {
               return;
            }

            if (var15 && this.L) {
               this.s(var7);
            } else if (this.I(var5, var14)) {
               this.V(var11, var12, var13, var14);
            }
         }
      }
   }

   public void turn(double var1, double var3) {
      this.N += (float)(var1 * 0.15);
      this.D = class_3532.method_15363(this.D + (float)(var3 * 0.15), -90.0F, 90.0F);
   }

   public boolean onScroll(double var1, double var3) {
      double var5 = var3 == 0.0 ? -var1 : var3;
      if (this.L && Double.isFinite(var5) && var5 != 0.0) {
         this.M = class_3532.method_15350(this.M * Math.pow(1.12, -class_3532.method_15350(var5, -10.0, 10.0)), 1.5, 12.0);
         this.v.set(this.M);
         this.i = System.nanoTime();
         return true;
      } else {
         return false;
      }
   }

   public float sinceScroll() {
      return this.i == 0L ? Float.MAX_VALUE : (float)(System.nanoTime() - this.i) / 1.0E9F;
   }

   public double wantedDistance() {
      return this.M;
   }

   private boolean I(long var1, class_310 var3) {
      class_746 var4 = var3.field_1724;
      if (this.enabled() && var3.field_1687 != null) {
         long var5 = 125583880493851L;
         if (125583880493851L == 0L) {
            boolean var10000 = false;
         } else {
            int var15 = var5 < 0L ? -1 : 1;
         }

         if (var4 != null) {
            long var8 = 125583880493851L;
            if (125583880493851L == 0L) {
               boolean var16 = false;
            } else {
               int var17 = var8 < 0L ? -1 : 1;
            }

            if (var4.method_5805() && !var4.method_6113()) {
               long var10 = 125583880493852L;
               if (125583880493852L == 0L) {
                  boolean var18 = false;
               } else {
                  int var19 = var10 < 0L ? -1 : 1;
               }

               if (var3.method_1560() == var4 && var3.method_1569()) {
                  boolean var12 = var3.field_1729.method_1613();
                  long var13 = 125583880493852L;
                  if (125583880493852L == 0L) {
                     boolean var20 = false;
                  } else {
                     int var21 = var13 < 0L ? -1 : 1;
                  }

                  if (var12 && !ModuleWidget.claimed("freecam-detached")) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private void V(int var1, int var2, int var3, class_310 var4) {
      long var5 = 63349868050961L;
      long var7 = var5 ^ 72305270424763L;
      long var9 = var5 ^ 45052290420532L;
      class_746 var11 = var4.field_1724;
      if (var11 != null) {
         if (!this.active()) {
            this.z = var4.field_1690.method_31044();
            boolean var12 = this.z == class_5498.field_26666;
            this.N = var11.method_36454() + (var12 ? 180 : 0);
            this.D = var12 ? -var11.method_36455() : var11.method_36455();
            this.M = this.u = this.v.get();
            this.B = 0.0;
            this.s = 0.0F;
            this.E = 1.0F;
            this.G = true;
            this.d = 0.0;
            this.S = var4.field_1687;
            boolean var13 = this.z.method_31034();
            if (var13) {
               var4.field_1690.method_31043(class_5498.field_26665);
            }
         } else {
            this.M = this.v.get();
         }

         this.L = true;
         this.W(var7, 1.0F, this.B(0.45F, var9) * (1.0F - this.s));
      }
   }

   private void s(long var1) {
      long var3 = 132350148361898L;
      long var5 = 19122396460325L;
      if (this.L) {
         this.L = false;
         this.W(var3, 0.0F, this.B(0.42F, var5) * Math.max(0.35F, this.s));
      }
   }

   private float B(float var1, long var2) {
      return this.m.enabled() ? var1 / this.J.floatValue() : 0.0F;
   }

   private void W(long var1, float var3, float var4) {
      this.a = this.s;
      long var5 = 132350148361898L;
      if (132350148361898L == 0L) {
         boolean var10000 = false;
      } else {
         int var10 = var5 < 0L ? -1 : 1;
      }

      this.Q = var3;
      this.H = this.E;
      long var8 = 132350148361898L;
      if (132350148361898L == 0L) {
         boolean var11 = false;
      } else {
         int var12 = var8 < 0L ? -1 : 1;
      }

      this.Y = var3;
      this.h = System.nanoTime();
      this.o = var4 <= 0.0F ? 0.0F : Math.max(0.05F, var4);
   }

   private void j(long var1, class_310 var3) {
      long var4 = 134318383061267L;
      if (134318383061267L == 0L) {
         boolean var10000 = false;
      } else {
         int var7 = var4 < 0L ? -1 : 1;
      }

      if (this.z != null && var3.field_1690.method_31044() != this.z) {
         var3.field_1690.method_31043(this.z);
      }

      this.z = null;
      this.L = false;
      this.s = this.E = 0.0F;
      this.d = 0.0;
   }

   private void G(long var1, class_310 var3) {
      long var4 = 134318383061267L;
      if (this.active()) {
         this.j(var4, var3);
      }
   }

   private void i(long var1, class_310 var3) {
      long var4 = 138755685731503L;
      long var6 = 89326068142889L;
      if (this.active()) {
         while (var3.field_1690.field_1824.method_1436()) {
         }

         if (this.L) {
            boolean var8 = this.l.is("Toggle") || var3.method_1569() && class_3675.method_15987(var3.method_22683(), this.key());
            long var9 = 53543336393772L;
            if (53543336393772L == 0L) {
               boolean var10000 = false;
            } else {
               int var12 = var9 < 0L ? -1 : 1;
            }

            if (!var8 || !this.b(var6, var3)) {
               this.t = false;
               this.s(var4);
            }
         }
      }
   }

   private boolean b(long var1, class_310 var3) {
      class_746 var4 = var3.field_1724;
      if (this.enabled()) {
         long var5 = 89326068142888L;
         if (89326068142888L == 0L) {
            boolean var10000 = false;
         } else {
            int var10 = var5 < 0L ? -1 : 1;
         }

         if (var4 != null) {
            long var8 = 89326068142889L;
            if (89326068142889L == 0L) {
               boolean var11 = false;
            } else {
               int var12 = var8 < 0L ? -1 : 1;
            }

            if (var4.method_5805() && !var4.method_6113() && var3.method_1560() == var4) {
               return true;
            }
         }
      }

      return false;
   }

   public void frame() {
      long var1 = 92473605305653L;
      long var3 = var1 ^ 46719399726830L;
      long var5 = var1 ^ 50800334695462L;
      class_310 var7 = class_310.method_1551();
      long var8 = System.nanoTime();
      this.T = this.p == 0L ? 0.0 : class_3532.method_15350((var8 - this.p) / 1.0E9, 0.0, 0.1);
      this.p = var8;
      this.K++;
      if (this.active()) {
         if (var7.field_1687 != null
            && var7.field_1687 == this.S
            && var7.field_1724 != null
            && var7.method_1560() == var7.field_1724
            && !ModuleWidget.claimed("freecam-detached")) {
            float var10 = this.o <= 0.0F ? 1.0F : Math.min(1.0F, (float)(var8 - this.h) / 1.0E9F / this.o);
            float var11 = this.Q > this.a ? Easing.CUBIC_OUT.apply(var10) : Easing.CUBIC_IN_OUT.apply(var10);
            this.s = class_3532.method_16439(var11, this.a, this.Q);
            this.E = class_3532.method_16439(var11, this.H, this.Y);
            double var12 = this.m.enabled() ? 6.64 * this.J.get() / 0.3 : 1000000.0;
            double var14 = this.u - this.M;
            double var16 = this.B + var12 * var14;
            double var18 = Math.exp(-var12 * this.T);
            this.u = this.M + (var14 + var16 * this.T) * var18;
            this.B = (this.B - var12 * var16 * this.T) * var18;
            if (!this.L && var10 >= 1.0F) {
               this.j(var5, var7);
            }
         } else {
            this.G(var3, var7);
         }
      }
   }

   public FreelookModule.View view(class_243 var1, class_243 var2, float var3, float var4) {
      class_5498 var5 = this.z;
      boolean var6 = var5 == null || var5.method_31034();
      double var7 = class_3532.method_15363(this.s, 0.0F, 1.0F);
      float var9 = var3 + class_3532.method_15393(this.N - var3) * this.E;
      float var10 = var4 + (this.D - var4) * this.E;
      double var11 = var6 ? 0.0 : var2.method_1022(var1);
      double var13 = var11 + (this.u - var11) * var7;
      class_243 var15 = class_243.field_1353;
      if (!var6) {
         class_243 var16 = var1.method_1020(class_243.method_1030(var4, var3).method_1021(var11));
         var15 = var2.method_1020(var16).method_1021(1.0 - var7);
      }

      return new FreelookModule.View(var9, var10, var13, var15);
   }

   public double afterWalls(double var1, double var3) {
      if (this.x == this.K) {
         return Math.min(this.d, var3);
      } else {
         this.x = this.K;
         if (!this.G && !(var3 < this.n)) {
            this.n = this.n + (var3 - this.n) * (1.0 - Math.exp(-9.0 * this.T));
         } else {
            this.n = var3;
         }

         this.G = this.n >= var1 - 0.001;
         this.d = Math.max(0.0, this.n);
         return this.d;
      }
   }

   public static double clearance(class_1937 var0, class_1297 var1, class_243 var2, Vector3fc var3, double var4) {
      double var6 = var4;

      for (int var8 = 0; var8 < 8; var8++) {
         class_243 var9 = var2.method_1031(((var8 & 1) * 2 - 1) * 0.1, ((var8 >> 1 & 1) * 2 - 1) * 0.1, ((var8 >> 2 & 1) * 2 - 1) * 0.1);
         class_243 var10 = var9.method_1031(-var3.x() * var4, -var3.y() * var4, -var3.z() * var4);
         class_3965 var11 = var0.method_17742(new class_3959(var9, var10, class_3960.field_23142, class_242.field_1348, var1));
         if (var11.method_17783() != class_240.field_1333) {
            var6 = Math.min(var6, var11.method_17784().method_1022(var2));
         }
      }

      return var6;
   }

   public static double throughFirstWall(class_1937 var0, class_1297 var1, class_243 var2, Vector3fc var3, double var4) {
      class_243 var6 = new class_243(-var3.x(), -var3.y(), -var3.z());
      class_3965 var7 = var0.method_17742(new class_3959(var2, var2.method_1019(var6.method_1021(var4)), class_3960.field_23142, class_242.field_1348, var1));
      if (var7.method_17783() == class_240.field_1333) {
         return var4;
      } else {
         double var8 = var7.method_17784().method_1022(var2);
         class_2339 var10 = new class_2339();
         double var11 = var8;

         for (double var13 = var8 + 0.0625; var13 < var4; var13 += 0.0625) {
            class_243 var15 = var2.method_1019(var6.method_1021(var13));
            var10.method_10103(class_3532.method_15357(var15.field_1352), class_3532.method_15357(var15.field_1351), class_3532.method_15357(var15.field_1350));
            class_265 var16 = var0.method_8320(var10).method_26202(var0, var10, class_3726.method_16194());
            if (var16.method_1110() || !var16.method_1107().method_996(var10).method_1014(1.0E-4).method_1006(var15)) {
               return var11;
            }

            var11 = var13;
         }

         return var4;
      }
   }

   public boolean hidesBody() {
      long var1 = 66514344484672L;
      long var3 = var1 ^ 40573021721543L;
      return !this.I(var3) ? false : this.d < 0.45 || this.s < 0.999F && this.d < 1.2;
   }

   public float handsDown() {
      long var1 = 2096550932350L;
      long var3 = var1 ^ 27969150018553L;
      if (this.active() && this.I(var3)) {
         double var5 = Math.max(this.d / 1.2, this.s / 0.35);
         return (float)class_3532.method_15350(var5, 0.0, 1.0);
      } else {
         return 1.0F;
      }
   }

   public boolean inHead() {
      return this.active() && this.handsDown() < 1.0F;
   }

   private boolean I(long var1) {
      return this.z == null || this.z.method_31034();
   }

   public boolean passesWalls() {
      return this.active() && this.f.enabled();
   }

   @Override
   protected void attach(ModuleContext var1) {
      var1.own(ModuleWidget.onKey((var1x, var2, var3) -> this.onKey(var1x, var3)));
      var1.own(ModuleWidget.onScroll(200, this::onScroll));
      var1.own(ModuleWidget.onClientTickStart(0, var1x -> this.i(53543336393772L, var1x)));
      var1.own(ModuleWidget.onDisconnect(var1x -> this.G(138980936694747L, var1x)));
      var1.onFrame(var2 -> {
         this.frame();
         var1.metric("active", this.active() ? 1.0 : 0.0);
      });
      var1.own(
         ClientRegistry13.install(
            new ClientRegistry13.Listener() {
               @Override
               public boolean active() {
                  return FreelookModule.this.active();
               }

               @Override
               public boolean turn(double var1, double var3) {
                  if (!FreelookModule.this.engaged()) {
                     return false;
                  } else {
                     FreelookModule.this.turn(var1, var3);
                     return true;
                  }
               }

               @Override
               public boolean inHead() {
                  return FreelookModule.this.inHead();
               }

               @Override
               public boolean passesWalls() {
                  return FreelookModule.this.passesWalls();
               }

               @Override
               public float handsDown() {
                  return FreelookModule.this.handsDown();
               }

               @Override
               public ClientRegistry13.TypeK position(class_1937 var1, class_1297 var2, class_243 var3, class_243 var4, float var5, float var6, Vector3fc var7) {
                  if (this.active() && var2 == class_310.method_1551().field_1724) {
                     FreelookModule.View var8 = FreelookModule.this.view(var3, var4, var5, var6);
                     float var9 = (float)Math.toRadians(var8.yaw());
                     float var10 = (float)Math.toRadians(var8.pitch());
                     Vector3f var11 = new Vector3f(
                        -((float)Math.sin(var9)) * (float)Math.cos(var10), -((float)Math.sin(var10)), (float)Math.cos(var9) * (float)Math.cos(var10)
                     );
                     double var12 = FreelookModule.this.f.enabled()
                        ? FreelookModule.throughFirstWall(var1, var2, var3, var11, var8.distance())
                        : FreelookModule.clearance(var1, var2, var3, var11, var8.distance());
                     double var14 = FreelookModule.this.afterWalls(var8.distance(), var12);
                     class_243 var16 = var8.offset();
                     return new ClientRegistry13.TypeK(
                        var8.yaw(),
                        var8.pitch(),
                        new class_243(
                           var3.field_1352 - var11.x() * var14 + var16.field_1352,
                           var3.field_1351 - var11.y() * var14 + var16.field_1351,
                           var3.field_1350 - var11.z() * var14 + var16.field_1350
                        ),
                        FreelookModule.this.hidesBody()
                     );
                  } else {
                     return null;
                  }
               }
            }
         )
      );
   }

public record View(float i, float s, double z, class_243 x) {
      public static int f;

      public float yaw() {
         return this.i;
      }

      public float pitch() {
         return this.s;
      }

      public double distance() {
         return this.z;
      }

      public class_243 offset() {
         return this.x;
      }

      public float floatState12() {
         return this.i;
      }

      public float floatState39() {
         return this.s;
      }

      public double doubleState5() {
         return this.z;
      }

      public class_243 minecraftObjectState3() {
         return this.x;
      }
   }
}
