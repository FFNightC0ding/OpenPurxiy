package dev.purxiy.modules.hand;

import dev.purxiy.anim.Animator;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientRegistry;
import dev.purxiy.loader.fabric.mixin.HandSwingAccessor;
import java.util.Locale;
import net.minecraft.class_11902;
import net.minecraft.class_1268;
import net.minecraft.class_1292;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1799;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_5134;
import net.minecraft.class_742;
import net.minecraft.class_746;
import net.minecraft.class_7833;
import net.minecraft.class_9285;
import net.minecraft.class_9334;
import org.joml.Matrix4f;

public final class HandMovementSupport4 {
   static final float H = 0.56F;
   static final float m = -0.52F;
   static final float R = -0.72F;
   private static final float o = -0.55F;
   private static final float v = -0.72F;
   private static final float p = 0.35F;
   private static final float O = 1.05F;
   private static long P = 0L;
   private static float b;
   private static double T;
   private static float g;
   private static float h;
   private static boolean M;
   private static final float[] q = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F};
   private static final float[] f = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F};
   private static final float[] D = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
   private static final float[] Y = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
   private static String z = "";
   private static final HandMovementSupport d = new HandMovementSupport();
   private static final HandMovementSupport a = new HandMovementSupport();
   private static final SwingStyle.TypeJ E = new SwingStyle.TypeJ();
   private static boolean K;
   private static float S;
   private static float I;
   private static float V;
   private static float n;
   private static float x;
   private static float u;
   private static final Animator F = new Animator(0.0F, 90.0F, 14.0F);
   private static final Animator w = new Animator(0.0F, 80.0F, 15.0F);
   private static final Animator Q = new Animator(0.0F, 70.0F, 13.0F);
   private static final Animator c = new Animator(0.0F, 170.0F, 12.0F);
   private static final Animator N = Animator.smooth(0.0F);
   private static boolean i = true;
   private static float X;
   private static boolean U;
   private static boolean C;
   private static float B;
   private static float t;
   private static final Animator Z = Animator.smooth(0.0F);
   private static class_1799 r;
   private static boolean L;
   private static float A = -1.0F;
   private static double k = Double.NEGATIVE_INFINITY;
   private static float e = 12.5F;
   private static double G = Double.NEGATIVE_INFINITY;
   private static double l;
   static HandMovementModule y;
   private static ModuleContext j;
   private static int s = 0;
   private static int J = 0;

   private HandMovementSupport4() {
   }

   private static HandMovementModule S() {
      return y;
   }

   public static boolean active() {
      return h > 0.0F;
   }

   public static boolean swingsOwned() {
      return S().enabled();
   }

   public static double clock() {
      return T;
   }

   public static void frame() {
      long var0 = 9752237704562L;
      long var2 = var0 ^ 78956898982604L;
      long var4 = var0 ^ 113545740387678L;
      long var6 = var0 ^ 46557582818193L;
      long var8 = var0 ^ 120944204378531L;
      long var10 = var0 ^ 2455821040474L;
      long var12 = var0 ^ 113564894053134L;
      int var14 = (int)((var0 ^ 113564894053134L) >>> 32);
      int var15 = (int)((var0 ^ 113564894053134L) << 32 >>> 48);
      int var16 = (int)(var12 << 48 >>> 48);
      long var17 = System.nanoTime();
      boolean var19 = P != 0L && var17 - P < 500000000L;
      b = var19 ? Math.min((float)(var17 - P) / 1.0E9F, 0.1F) : 0.0F;
      P = var17;
      T = T + b;
      HandMovementModule var20 = S();
      boolean var21 = var20.enabled();
      g = var19 ? x(g, var21 ? 1.0F : 0.0F, var8, b / 0.35F) : (var21 ? 1.0F : 0.0F);
      h = g * g * (3.0F - 2.0F * g);
      L(var10, var20);
      P(var2, var20.p, q, D);
      P(var2, var20.offPlacement(), f, Y);
      M = true;
      class_310 var22 = class_310.method_1551();
      class_746 var23 = var22.field_1724;
      if (var23 == null) {
         K = false;
      } else {
         v(var4, var23);
         t(var14, var23, var15, var16);
         z(var6);
      }
   }

   private static void L(long var0, HandMovementModule var2) {
      SwingStyle.ensureSourceClassInitialized();
      String var3 = (String)var2.F.get();
      boolean var4 = var3.equals(z);
      long var5 = 11980089671208L;
      int var17 = var5 < 0L ? -1 : 1;
      if (!var4) {
         var4 = z.isEmpty();
         long var8 = 11980089671208L;
         int var10 = var8 < 0L ? -1 : 1;
         if (!var4) {
            var2.choosePreset(var3);
         }
      }

      String var17x = "Custom";

      for (HandMovementCatalog.Data var10 : HandMovementCatalog.P) {
         long var11 = 11980089671207L;
         int var13 = var11 < 0L ? -1 : 1;
         var4 = var2.edited().matches(var10.values());
         long var13x = 11980089671207L;
         int var14 = var13x < 0L ? -1 : 1;
         if (var4) {
            var17x = var10.name();
         }
      }

      long var18 = 11980089671208L;
      int var21 = var18 < 0L ? -1 : 1;
      var2.F.set(var17x);
      z = var17x;
   }

   private static void P(long var0, HandMovementModule.HandPlacement var2, float[] var3, float[] var4) {
      SwingStyle.ensureSourceClassInitialized();
      NumberSetting[] var5 = var2.all();
      if (!M) {
         for (int var6 = 0; var6 < 7; var6++) {
            var3[var6] = var5[var6].floatValue();
            var4[var6] = 0.0F;
         }

         long var14 = 86934621822910L;
         int var16 = var14 < 0L ? -1 : 1;
      } else {
         float var15 = b;

         while (var15 > 0.0F) {
            float var7 = Math.min(var15, 0.004166667F);

            for (int var8 = 0; var8 < 7; var8++) {
               float var9 = var5[var8].floatValue();
               long var10 = 86934621822909L;
               int var13 = var10 < 0L ? -1 : 1;
               float var13x = 260.0F * (var9 - var3[var8]) - 30.0F * var4[var8];
               var4[var8] += var13x * var7;
               var3[var8] += var4[var8] * var7;
            }

            long var16 = 86934621822909L;
            int var17 = var16 < 0L ? -1 : 1;
            var15 -= var7;
         }
      }
   }

   private static void v(long var0, class_746 var2) {
      float var3;
      float var4;
      label50: {
         SwingStyle.ensureSourceClassInitialized();
         var3 = var2.method_5695(1.0F);
         var4 = var2.method_5705(1.0F);
         long var5 = 122709148139563L;
         int var15 = var5 < 0L ? -1 : 1;
         if (K) {
            float var8 = var3 - S;
            long var9 = 122709148139563L;
            int var18 = var9 < 0L ? -1 : 1;
            if (!(Math.abs(var8) > 120.0F)) {
               long var11 = 122709148139564L;
               int var13 = var11 < 0L ? -1 : 1;
               float var16 = var4 - I;
               long var13x = 122709148139564L;
               int var14 = var13x < 0L ? -1 : 1;
               if (!(Math.abs(var16) > 240.0F)) {
                  break label50;
               }
            }
         }

         S = var3;
         I = var4;
         n = 0.0F;
         V = 0.0F;
         K = true;
      }

      float var15 = 380.0F;
      float var6 = 27.0F;
      float var7 = b;

      while (var7 > 0.0F) {
         float var17 = Math.min(var7, 0.004166667F);
         long var19 = 122709148139563L;
         int var131 = var19 < 0L ? -1 : 1;
         V = V + (var15 * (var3 - S) - var6 * V) * var17;
         n = n + (var15 * (var4 - I) - var6 * n) * var17;
         S = S + V * var17;
         I = I + n * var17;
         var7 -= var17;
      }

      long var18 = 122709148139564L;
      int var23 = var18 < 0L ? -1 : 1;
      x = Math.clamp(var3 - S, -45.0F, 45.0F);
      u = Math.clamp(var4 - I, -45.0F, 45.0F);
   }

   private static void t(int var0, class_746 var1, int var2, int var3) {
      SwingStyle.ensureSourceClassInitialized();
      class_243 var4 = var1.method_18798();
      float var5 = var1.method_36454() * (float) (Math.PI / 180.0);
      float var6 = (float)(-var4.field_1352 * Math.cos(var5) - var4.field_1350 * Math.sin(var5));
      float var7 = (float)(-var4.field_1352 * Math.sin(var5) + var4.field_1350 * Math.cos(var5));
      boolean var8 = var1.method_31549().field_7479;
      float var9 = !var8 && !var1.method_24828() ? (float)var4.field_1351 : 0.0F;
      if (var1.method_5765() || var1.method_7325()) {
         var9 = 0.0F;
         var7 = 0.0F;
         var6 = 0.0F;
      }

      float var10 = Z.value();
      var7 += var10 * 0.21F;
      F.target(Math.clamp(var6, -0.4F, 0.4F)).update(b);
      w.target(Math.clamp(var7, -0.4F, 0.4F)).update(b);
      Q.target(Math.clamp(var9, -1.2F, 0.6F)).update(b);
      boolean var11 = var1.method_24828();
      if (var11 && !i) {
         float var12;
         int var13 = (var12 = X - -0.25F) == 0.0F ? 0 : (var12 < 0.0F ? -1 : 1);
         if (var13 < 0) {
            c.impulse(-Math.min(1.6F, -X * 1.3F));
         }
      }

      int var16;
      label53: {
         i = var11;
         X = (float)var4.field_1351;
         c.target(0.0F).update(b);
         if (Math.abs(var6) + Math.abs(var7) < 0.02F) {
            float var17;
            var16 = (var17 = var9 - 0.0F) == 0.0F ? 0 : (var17 < 0.0F ? -1 : 1);
            if (var16 == 0 && !var1.method_6115()) {
               var16 = 1;
               break label53;
            }
         }

         var16 = 0;
      }

      N.target(var16 != 0 && var10 < 0.05F ? 1.0F : 0.0F).update(b * 0.6F);
   }

   private static void z(long var0) {
      SwingStyle.ensureSourceClassInitialized();
      Z.target(C ? 1.0F : 0.0F).update(b);
      float var2;
      int var3 = (var2 = Z.value() - 0.001F) == 0.0F ? 0 : (var2 < 0.0F ? -1 : 1);
      long var4 = 37961446293218L;
      int var7 = var4 < 0L ? -1 : 1;
      if (var3 > 0) {
         t = t + b * 2.6F * Z.value();
      }

      if (U) {
         long var7x = 37961446293219L;
         int var8 = var7x < 0L ? -1 : 1;
         B = B - b;
         if (B <= 0.0F) {
            hitOnce();
            B = Math.max(1.05F, e / 20.0F + 0.45F);
         }
      }
   }

   public static void onSwing(class_1268 var0, int var1) {
      long var2 = 17082835425229L;
      long var4 = var2 ^ 22367978729603L;
      if (A < 0.0F) {
         j(var0, var1, var4);
      }
   }

   private static void j(class_1268 var0, int var1, long var2) {
      long var4 = 71774361367021L;
      HandMovementModule var6 = S();
      float var7 = Math.max(1, var1) * 0.05F / var6.q.floatValue();
      X(var0, var4).push(T, var7, SwingStyle.valueOf(ClientRegistry.select(var0, var6.swingStyle().name())), var6.k.floatValue());
   }

   private static HandMovementSupport X(class_1268 var0, long var1) {
      return var0 == class_1268.field_5808 ? d : a;
   }

   public static void swingAt(class_1268 var0, boolean var1, double var2, SwingStyle.TypeJ var4) {
      long var5 = 36774387319335L;
      long var7 = var5 ^ 105782647141322L;
      if (A >= 0.0F) {
         HandMovementModule var9 = S();
         float var10 = Math.max(0.0F, A - (float)(T - var2) / 0.3F);
         if (var0 == class_1268.field_5808) {
            if (var1) {
               var9.swingStyle().arm(var10, var9.k.floatValue(), var4);
            } else {
               var9.swingStyle().item(var10, var9.k.floatValue(), var4);
            }
         } else {
            var4.rest(45.0F);
         }
      } else {
         X(var0, var7).evaluate(var2, var1, var4);
      }
   }

   public static boolean swingingWithin(class_1268 var0, float var1) {
      long var2 = 117593454398034L;
      long var4 = var2 ^ 48052517993407L;
      return A >= 0.0F ? var0 == class_1268.field_5808 : X(var0, var4).within(T, var1);
   }

   public static class_1268 lastSwung() {
      return a.lastStart() > d.lastStart() ? class_1268.field_5810 : class_1268.field_5808;
   }

   public static float swingSeconds(class_1268 var0) {
      long var1 = 25738622514840L;
      long var3 = var1 ^ 94764026529653L;
      float var5 = X(var0, var3).lastDuration();
      return var5 > 0.0F ? var5 : 0.3F / S().q.floatValue();
   }

   public static void beginHands(class_4587 var0, float var1, class_746 var2) {
      if (active()) {
         HandMovementModule var3 = S();
         class_310 var4 = class_310.method_1551();
         class_1297 var5 = var4.method_1560();
         boolean var6 = (Boolean)var4.field_1690.method_42448().method_41753() && var5 instanceof class_742;
         if (var6) {
            class_11902 var7 = ((class_742)var5).method_74192();
            float var8 = var7.method_74205(var1);
            float var9 = var7.method_74204(var1);
            float var10 = 1.0F + (var3.C.floatValue() - 1.0F) * h;
            if (Math.abs(var10 - 1.0F) > 0.001F && var9 > 0.0F) {
               Matrix4f var11 = d(new Matrix4f(), var8, var9, 1.0F).invert();
               var0.method_34425(d(var11, var8, var9, var10));
            }

            float var23 = Z.value();
            if (var23 > 0.001F) {
               var0.method_34425(d(new Matrix4f(), t, 0.1F * var23, var10));
            }
         }

         float var19 = var3.n.floatValue() * h;
         float var20 = var3.G.floatValue() * h * N.value();
         float var21 = (float)T;
         float var22 = F.value();
         float var24 = w.value();
         float var12 = Q.value();
         float var13 = c.value();
         float var14 = -var22 * 0.16F * var19;
         float var15 = (-var12 * 0.05F + var13 * 0.05F - var24 * 0.05F) * var19 + var20 * 0.01F * (float)Math.sin(var21 * 1.75F);
         float var16 = var24 * 0.14F * var19;
         float var17 = var22 * 18.0F * var19;
         float var18 = (var13 * 5.0F + var12 * 3.0F) * var19 + var20 * 1.1F * (float)Math.sin(var21 * 1.75F - 0.9F);
         if (Math.abs(var14) + Math.abs(var15) + Math.abs(var16) + Math.abs(var17) + Math.abs(var18) > 1.0E-5F) {
            var0.method_46416(var14, var15, var16);
            var0.method_46416(0.0F, -0.55F, -0.72F);
            var0.method_22907(class_7833.field_40718.rotationDegrees(var17));
            var0.method_22907(class_7833.field_40714.rotationDegrees(var18));
            var0.method_46416(0.0F, 0.55F, 0.72F);
         }
      }
   }

   private static Matrix4f d(Matrix4f var0, float var1, float var2, float var3) {
      float var4 = var2 * var3;
      float var5 = var1 * (float) Math.PI;
      var0.translate(class_3532.method_15374(var5) * var4 * 0.5F, -Math.abs(class_3532.method_15362(var5) * var4), 0.0F);
      var0.rotate(class_7833.field_40718.rotationDegrees(class_3532.method_15374(var5) * var4 * 3.0F));
      var0.rotate(class_7833.field_40714.rotationDegrees(Math.abs(class_3532.method_15362(var5 - 0.2F) * var4) * 5.0F));
      return var0;
   }

   public static float swayPitch(float var0) {
      return active() ? class_3532.method_16439(h, var0, x * 0.1F * S().t.floatValue()) : var0;
   }

   public static float swayYaw(float var0) {
      return active() ? class_3532.method_16439(h, var0, u * 0.1F * S().t.floatValue()) : var0;
   }

   public static void place(class_4587 var0, class_1268 var1, int var2) {
      if (active()) {
         float[] var3 = var1 == class_1268.field_5808 ? q : f;
         float var4 = var3[0] * h;
         float var5 = var3[1] * h;
         float var6 = var3[2] * h;
         float var7 = var3[3] * h;
         float var8 = var3[4] * h;
         float var9 = var3[5] * h;
         float var10 = 1.0F + (var3[6] - 1.0F) * h;
         if (!(Math.abs(var4) + Math.abs(var5) + Math.abs(var6) + Math.abs(var7) + Math.abs(var8) + Math.abs(var9) + Math.abs(var10 - 1.0F) < 1.0E-5F)) {
            var0.method_46416(var2 * var4, var5, -var6);
            float var11 = var2 * 0.56F;
            var0.method_46416(var11, -0.52F, -0.72F);
            var0.method_22907(class_7833.field_40716.rotationDegrees(-var2 * var8));
            var0.method_22907(class_7833.field_40714.rotationDegrees(-var7));
            var0.method_22907(class_7833.field_40718.rotationDegrees(-var2 * var9));
            var0.method_22905(var10, var10, var10);
            var0.method_46416(-var11, 0.52F, 0.72F);
         }
      }
   }

   public static Matrix4f placement(class_1268 var0, int var1, float var2, Matrix4f var3) {
      var3.identity();
      if (!active()) {
         return var3;
      } else {
         float[] var4 = var0 == class_1268.field_5808 ? q : f;
         float var5 = h * var2;
         float var6 = 1.0F + (var4[6] - 1.0F) * var5;
         float var7 = var1 * 0.56F;
         return var3.translate(var1 * var4[0] * var5, var4[1] * var5, -var4[2] * var5)
            .translate(var7, -0.52F, -0.72F)
            .rotateY((float)Math.toRadians(-var1 * var4[4] * var5))
            .rotateX((float)Math.toRadians(-var4[3] * var5))
            .rotateZ((float)Math.toRadians(-var1 * var4[5] * var5))
            .scale(var6)
            .translate(-var7, 0.52F, 0.72F);
      }
   }

   public static float equip(class_1268 var0, float var1) {
      if (active() && !(var1 <= 0.0F)) {
         HandMovementModule var2 = S();
         String var4 = (String)var2.r.get();

         float var3 = switch (var4) {
            case "Smooth" -> var1 * var1 * (3.0F - 2.0F * var1);
            case "Instant" -> 0.0F;
            default -> var1;
         };
         float[] var6 = var0 == class_1268.field_5808 ? q : f;
         float var7 = (1.0F + Math.max(0.0F, var6[1]) * 1.6F) / Math.max(0.4F, var6[6]);
         return class_3532.method_16439(h, var1, var3 * var7);
      } else {
         return var1;
      }
   }

   public static boolean instantSwitch() {
      return S().enabled() && S().r.is("Instant");
   }

   public static boolean cooldownDip() {
      return !S().enabled() || S().l.enabled();
   }

   public static boolean emptyHandShown() {
      return !S().enabled() || S().O.enabled();
   }

   public static boolean swingItem(class_4587 var0, class_1268 var1, int var2) {
      if (!swingsOwned()) {
         return false;
      } else {
         HandMovementSupport2.swingFrom(var0, var1, var2, false);
         swingAt(var1, false, T, E);
         E.applyItem(var0, var2);
         return true;
      }
   }

   public static void armPose(class_4587 var0, class_1268 var1, int var2, float var3) {
      var0.method_46416(var2 * 0.64000005F, -0.6F + var3 * -0.6F, -0.71999997F);
      HandMovementSupport2.swingFrom(var0, var1, var2, true);
      swingAt(var1, true, T, E);
      E.applyArm(var0, var2);
      var0.method_46416(var2 * -1.0F, 3.6F, 3.5F);
      var0.method_22907(class_7833.field_40718.rotationDegrees(var2 * 120.0F));
      var0.method_22907(class_7833.field_40714.rotationDegrees(200.0F));
      var0.method_22907(class_7833.field_40716.rotationDegrees(var2 * -135.0F));
      var0.method_46416(var2 * 5.6F, 0.0F, 0.0F);
   }

   public static class_1799 preview(class_1268 var0) {
      if (var0 != class_1268.field_5808) {
         return null;
      } else {
         return L ? class_1799.field_8037 : r;
      }
   }

   public static void setPreview(class_1799 var0) {
      r = var0 != null && !var0.method_7960() ? var0 : null;
      L = var0 != null && var0.method_7960();
   }

   public static void setDemoSwing(boolean var0) {
      if (var0 && !U) {
         B = 0.25F;
      }

      U = var0;
   }

   public static boolean demoSwing() {
      return U;
   }

   public static void setDemoWalk(boolean var0) {
      C = var0;
   }

   public static boolean demoWalk() {
      return C;
   }

   public static void hitOnce() {
      long var0 = 116092066376341L;
      long var2 = var0 ^ 83050956381555L;
      long var4 = var0 ^ 125667813084635L;
      class_746 var6 = class_310.method_1551().field_1724;
      if (var6 != null) {
         class_1799 var7 = preview(class_1268.field_5808);
         if (var7 == null) {
            j(class_1268.field_5808, ((HandSwingAccessor)var6).purxiy$swingDuration(), var4);
            e = var6.method_7279();
         } else {
            j(class_1268.field_5808, p(var6, var2, var7), var4);
            e = S(var6, var7);
         }

         k = T;
      }
   }

   private static int p(class_746 var0, long var1, class_1799 var3) {
      SwingStyle.ensureSourceClassInitialized();
      int var4 = var3.method_75218().comp_4976();
      boolean var5 = class_1292.method_5576(var0);
      long var6 = 37508027021286L;
      int var9 = var6 < 0L ? -1 : 1;
      if (var5) {
         return var4 - (1 + class_1292.method_5575(var0));
      } else {
         class_1293 var9x = var0.method_6112(class_1294.field_5901);
         return var9x != null ? var4 + (1 + var9x.method_5578()) * 2 : var4;
      }
   }

   private static float S(class_746 var0, class_1799 var1) {
      double var2 = ((class_9285)var1.method_58695(class_9334.field_49636, class_9285.field_49326))
         .method_57481(class_5134.field_23723, var0.method_45326(class_5134.field_23723), class_1304.field_6173);
      return (float)(20.0 / Math.max(0.05, var2));
   }

   public static float studioLowered(class_1799 var0) {
      double var1 = (T - k) / 0.05;
      if (var1 >= 0.0 && !(var1 > e + 12.0)) {
         int var3 = (int)var1;
         boolean var4 = cooldownDip();
         float var5 = 1.0F;
         float var6 = 1.0F;

         for (int var7 = 0; var7 <= var3; var7++) {
            float var8 = Math.min(1.0F, (var7 + 1) / e);
            var5 = var6;
            var6 += class_3532.method_15363((var4 ? var8 * var8 * var8 : 1.0F) - var6, -0.4F, 0.4F);
            if (var6 >= 1.0F && var7 + 1 >= e) {
               if (var7 < var3) {
                  var5 = 1.0F;
               }
               break;
            }
         }

         float var9 = class_3532.method_16439((float)(var1 - var3), var5, var6);
         return class_310.method_1551().method_65386().method_75483(var0) * (1.0F - var9);
      } else {
         return 0.0F;
      }
   }

   public static void trace(float var0) {
      l = T;
      G = T + var0;
   }

   public static void traceLowered(float var0) {
      if (T <= G) {
         ClientServices.LOGGER.info("Hand trace {} ms: lowered {}", Math.round((T - l) * 1000.0), String.format(Locale.ROOT, "%.4f", var0));
      }
   }

   public static void freeze(float var0) {
      A = var0;
   }

   public static float[] shown(class_1268 var0) {
      return var0 == class_1268.field_5808 ? q : f;
   }

   private static float x(float var0, float var1, long var2, float var4) {
      SwingStyle.ensureSourceClassInitialized();
      long var5 = 111192861111504L;
      int var7 = var5 < 0L ? -1 : 1;
      return var0 < var1 ? Math.min(var1, var0 + var4) : Math.max(var1, var0 - var4);
   }

   public static void install(HandMovementModule var0, ModuleContext var1) {
      y = var0;
      j = var1;
   }

   public static void metric(String var0) {
      if (j != null) {
         j.metric(var0, var0.equals("drawn") ? ++s : ++J);
      }
   }

   public static void uninstall() {
      setPreview(null);
      setDemoSwing(false);
      setDemoWalk(false);
      y = null;
      j = null;
      P = 0L;
   }
}
