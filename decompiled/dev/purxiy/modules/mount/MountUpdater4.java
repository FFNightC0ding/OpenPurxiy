package dev.purxiy.modules.mount;

import dev.purxiy.hud.HudLayout;
import dev.purxiy.ui.KeyLabel;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1109;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2398;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3675;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_5498;
import net.minecraft.class_638;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.joml.Vector3f;

public final class MountUpdater4 {
   private static final MountUpdater5 E = new MountUpdater5();
   private static final MountUpdater8 O = new MountUpdater8();
   private static final MountUpdater2 p = new MountUpdater2();
   private static final MountUpdater6 x = new MountUpdater6();
   private static class_638 q;
   private static class_243 y = class_243.field_1353;
   private static class_243 N = class_243.field_1353;
   private static class_243 t;
   private static class_243 S;
   private static String i;
   private static MountFactory.Data7 C;
   private static class_5498 u;
   private static int b = 0;
   private static float H;
   private static float W;
   private static float l;
   private static boolean z;
   private static boolean B;
   private static boolean I;
   private static final List<Vector3f> P = new ArrayList<>();
   private static float[] a;
   private static String r = "";
   private static String o = "";
   private static int A = 0;
   private static final String[] d = new String[10];
   private static final long[] e = new long[]{-4240945175380005791L, 3839337608787655132L};
   private static final Integer[] f = new Integer[2];

   private MountUpdater4() {
   }

   public static MountFactory.Data7 spec() {
      String var0 = (String)MountRenderer4.K.u.get();
      if (C == null || !var0.equals(i)) {
         i = var0;
         C = MountFactory.get(var0);
      }

      return C;
   }

   public static void tick(class_310 var0) {
      long var1 = 128796687313692L;
      long var3 = var1 ^ 57816350325851L;
      long var5 = var1 ^ 16723142343361L;
      long var7 = var1 ^ 23231103171963L;
      long var9 = var1 ^ 123838541886142L;
      if (q != var0.field_1687) {
         clear();
         q = var0.field_1687;
      }

      if (!available(var0)) {
         clear();
      } else if (var0.method_1493()) {
         MountUpdater.stop();
      } else {
         b++;
         if (A > 0) {
            A--;
         }

         String var11 = (String)MountRenderer4.K.u.get();
         if (!var11.equals(i)) {
            boolean var12 = E.visible();
            spec();
            if (var12) {
               clear();
               summon();
            }
         }

         MountFactory.Data7 var23 = spec();
         if (!var11.equals(r) && MountRenderer7.acquire(var23.id()) != null) {
            r = var11;
         }

         MountRenderer5.warmUp();
         if (!E.visible()) {
            t = var0.field_1724.method_73189();
            MountUpdater.stop();
         } else {
            class_243 var13 = var0.field_1724.method_73189();
            class_243 var14 = t == null ? class_243.field_1353 : var13.method_1020(t);
            double var15 = var14.method_37267();
            if (t != null && var13.method_1025(t) > 64.0) {
               clear();
            } else {
               t = var13;
               boolean var17 = controlsAvailable(var0);
               if (E.riding() && var17 && var0.field_1690.field_1832.method_1434()) {
                  dismount();
               }

               boolean var18 = entering();
               if (var18 && !I && var23.kind() == MountFactory.TypeK.CAR && E.boarding(1.0F) > 0.62F) {
                  I = true;
                  e(class_3417.field_14819, 0.72F, var3, 0.55F);
               }

               if (E.riding() && !var18 && !B) {
                  B = true;
                  MountUpdater.start(var23);
               }

               if (E.riding() && var0.field_1724.method_31549().field_7479) {
                  var0.field_1724.method_31549().field_7479 = false;
                  var0.field_1724.method_7355();
               }

               N = y;
               boolean var19 = var0.field_1724.method_24828() || !x.airborne();
               if (!E.riding()) {
                  E.tick(0.05F, 0.0F, E.yaw(1.0F), false);
               } else {
                  float var20 = var17 && !var18
                     ? (var0.field_1690.field_1913.method_1434() ? 1 : 0) - (var0.field_1690.field_1849.method_1434() ? 1 : 0)
                     : 0.0F;
                  p.tick(0.05F, var18 ? 0.0 : var14.field_1352, var18 ? 0.0 : var14.field_1350, var20, var19, var17 && !var18, var23);
                  E.tickDriving(0.05F, var18 ? 0.0F : (float)(var15 * 20.0), p.yaw(1.0F), p.steering(1.0F), false);
               }

               float var24 = var17 && !var18 ? (var0.field_1690.field_1894.method_1434() ? 1 : 0) - (var0.field_1690.field_1881.method_1434() ? 1 : 0) : 0.0F;
               W = var24;
               int var21 = O.gear();
               O.tick(
                  0.05F,
                  E.riding() && !var18,
                  var23,
                  var24,
                  var18 ? 0.0F : (float)(var15 * 20.0),
                  p.signedDistance() * 20.0F,
                  -p.steering(1.0F),
                  MountRenderer4.K.E.floatValue(),
                  MountRenderer4.K.y.is("Manual"),
                  x.landing(1.0F)
               );
               if (O.gear() != var21 && var17) {
                  MountUpdater.shift();
                  n(var0, var7);
               }

               l += 0.05F;
               z = E.riding() && !var18 && var17 && var0.field_1724.method_24828() && var24 != 0.0F && i(var0, var5, var23, var24);
               if (!E.riding()) {
                  if (S != null) {
                     y = y.method_35590(S, 0.22);
                     if (y.method_1025(S) < 4.0E-4) {
                        y = S;
                        S = null;
                     }
                  }

                  class_243 var25 = y.method_1020(N);
                  p.tick(0.05F, var25.field_1352, var25.field_1350, 0.0F, false, false, var23);
                  if (var13.method_1025(y) > 16384.0) {
                     clear();
                  }
               } else {
                  float var22 = E.boarding(1.0F);
                  if (var22 >= 0.999F) {
                     y = var13;
                  } else if (var22 > 0.8F || var13.method_1025(y) < 0.09) {
                     y = y.method_35590(var13, 0.36);
                  }

                  if (var17 && !var18) {
                     j(var0, var15, var9, var23);
                  }
               }

               if (E.visible()) {
                  x.tick(var0, var23, y, yaw(1.0F), E.riding() ? var13.field_1351 : (x.ready() ? x.y(1.0F) : y.field_1351), E.riding());
                  MountUpdater.tick(
                     var0,
                     E.riding() && var17 && !var18,
                     var23,
                     O.rpm(1.0F),
                     O.throttle(1.0F),
                     O.startup(1.0F),
                     O.gear(),
                     E.speed(),
                     O.stunt(1.0F),
                     p.steering(1.0F)
                  );
               }
            }
         }
      }
   }

   public static boolean available(class_310 var0) {
      return MountRenderer4.K.enabled()
         && var0.field_1687 != null
         && var0.field_1724 != null
         && var0.field_1724.method_5805()
         && !var0.field_1724.method_7325()
         && !var0.field_1724.method_5765()
         && !var0.field_1724.method_6113()
         && !var0.field_1724.method_6128()
         && !var0.field_1724.method_5681()
         && !var0.field_1724.method_5869();
   }

   public static void keyAction() {
      class_310 var0 = class_310.method_1551();
      if (available(var0) && var0.field_1755 == null && var0.method_18506() == null && var0.method_1569() && var0.field_1729.method_1613()) {
         boolean var1 = class_3675.method_15987(var0.method_22683(), 342) || class_3675.method_15987(var0.method_22683(), 346);
         if (var1) {
            cycle();
         } else {
            summon();
         }
      }
   }

   public static void boardAction() {
      if (controlsAvailable(class_310.method_1551()) && E.visible()) {
         interact();
      }
   }

   public static void interact() {
      long var0 = 6564380137859L;
      long var2 = var0 ^ 6417063471942L;
      long var4 = (var0 ^ 118417532987175L) >>> 16;
      int var6 = (int)((var0 ^ 118417532987175L) << 48 >>> 48);
      class_310 var7 = class_310.method_1551();
      if (available(var7)) {
         if (!E.visible()) {
            summon();
         } else if (E.riding()) {
            dismount();
         } else {
            MountFactory.Data7 var8 = spec();
            float var9 = C(var8, d(var7.field_1724.method_33571()), var2);
            if (!E.enter(canEnter(), var9)) {
               u(var4, (char)var6, "Come closer to your ride");
            } else {
               O.enter(var8);
               p.heading(E.yaw(1.0F));
               if (var7.field_1724.method_31549().field_7479) {
                  var7.field_1724.method_31549().field_7479 = false;
                  var7.field_1724.method_7355();
               }

               var7.field_1724.method_5728(false);
               H = p.yaw(1.0F) - MountUpdater5.wrap(p.yaw(1.0F) - var7.field_1724.method_36454());
               W = 0.0F;
               I = false;
               B = false;
               MountRenderer2.boot();
               if (MountRenderer4.K.q.enabled()) {
                  u = var7.field_1690.method_31044();
                  var7.field_1690.method_31043(class_5498.field_26664);
               }

               S = null;
               t = var7.field_1724.method_73189();
               A = 0;
            }
         }
      }
   }

   private static Vector3f d(class_243 var0) {
      class_243 var1 = var0.method_1020(renderPosition(1.0F));
      double var2 = Math.toRadians(E.yaw(1.0F));
      double var4 = Math.cos(var2);
      double var6 = Math.sin(var2);
      return new Vector3f(
         (float)(var1.field_1352 * var4 + var1.field_1350 * var6), (float)var1.field_1351, (float)(-var1.field_1352 * var6 + var1.field_1350 * var4)
      );
   }

   private static float C(MountFactory.Data7 var0, Vector3f var1, long var2) {
      MountFactory.Data7.ensureSourceClassInitialized();
      Vector3f var4 = var0.eye();
      Vector3f var5 = var0.seat();
      float var6 = var1.x >= 0.0F ? 1.0F : -1.0F;
      P.clear();
      P.add(var1);
      if (var0.kind() != MountFactory.TypeK.CAR) {
         P.add(new Vector3f(var6 * 0.55F, var4.y + 0.16F, var5.z - 0.02F));
         P.add(new Vector3f(var6 * 0.14F, var4.y + 0.14F, var5.z + 0.05F));
         P.add(new Vector3f(var4));
         return 0.85F;
      } else {
         float var7 = var0.halfWidth();
         float var8 = var4.z + 0.08F;
         long var9 = 199535768261L;
         int var12 = var9 < 0L ? -1 : 1;
         P.add(new Vector3f(var6 * (var7 + 0.14F), var4.y + 0.07F, var8));
         long var12x = 199535768260L;
         int var13 = var12x < 0L ? -1 : 1;
         P.add(new Vector3f(var6 * (var7 - 0.16F), var4.y - 0.05F, var8 - 0.02F));
         if (var6 > 0.0F) {
            P.add(new Vector3f(var4));
            return 1.2F;
         } else {
            P.add(new Vector3f(-var4.x, var4.y, var4.z));
            P.add(new Vector3f(-var4.x * 0.4F, var4.y - 0.04F, var4.z - 0.01F));
            P.add(new Vector3f(var4));
            return 2.0F;
         }
      }
   }

   public static boolean entering() {
      return E.riding() && E.boarding(1.0F) < 1.0F;
   }

   static Vector3f entryEye(float var0, Vector3f var1) {
      if (P.size() < 2) {
         return var1.set(spec().eye());
      } else {
         float var2 = Math.clamp(E.boarding(var0), 0.0F, 1.0F);
         int var3 = P.size() - 1;
         float var4 = var2 * var2 * (3.0F - 2.0F * var2) * var3;
         int var5 = Math.min(var3 - 1, (int)var4);
         float var6 = var4 - var5;
         float var7 = var6 * var6;
         float var8 = var7 * var6;
         Vector3f var9 = P.get(Math.max(0, var5 - 1));
         Vector3f var10 = P.get(var5);
         Vector3f var11 = P.get(var5 + 1);
         Vector3f var12 = P.get(Math.min(var3, var5 + 2));
         return var1.set(
            p(var9.x, var10.x, var11.x, var12.x, var6, var7, var8),
            p(var9.y, var10.y, var11.y, var12.y, var6, var7, var8),
            p(var9.z, var10.z, var11.z, var12.z, var6, var7, var8)
         );
      }
   }

   private static float p(float var0, float var1, float var2, float var3, float var4, float var5, float var6) {
      return 0.5F
         * (2.0F * var1 + (var2 - var0) * var4 + (2.0F * var0 - 5.0F * var1 + 4.0F * var2 - var3) * var5 + (3.0F * var1 - var0 - 3.0F * var2 + var3) * var6);
   }

   public static void frame(float var0) {
      class_310 var1 = class_310.method_1551();
      if (entering() && var1.field_1724 != null && var1.field_1690.method_31044().method_31034()) {
         float var2 = var1.field_1724.method_36455();
         var1.field_1724.method_36457(var2 + (8.0F - var2) * (1.0F - (float)Math.exp(-var0 * 5.0F)));
      }
   }

   public static void devCamera(float[] var0) {
      a = var0;
   }

   public static float[] devCamera() {
      return a;
   }

   public static void summon() {
      long var0 = 17018388337430L;
      long var2 = var0 ^ 86641791435857L;
      long var4 = (var0 ^ 106864147377586L) >>> 16;
      int var6 = (int)((var0 ^ 106864147377586L) << 48 >>> 48);
      long var7 = var0 ^ 127502611837810L;
      long var9 = var0 ^ 105940383117435L;
      class_310 var11 = class_310.method_1551();
      if (available(var11)) {
         MountFactory.Data7 var12 = spec();
         float var13 = var11.field_1724.method_36454();
         double[] var14 = new double[]{0.0, 45.0, -45.0, 90.0, -90.0, 180.0};
         double var15 = Math.max(3.2, var12.halfLength() * 0.5 + 1.9);
         class_243 var18 = k(var11, var12, var13, var7, var14, var15);
         if (var18 == null) {
            u(var4, (char)var6, "Find a flat, open space to summon your ride");
         } else {
            K(var9);
            MountUpdater.stop();
            O.reset();
            x.reset();
            W = 0.0F;
            N = var18;
            y = var18;
            MountRenderer8.reset();
            S = null;
            float var19 = var11.field_1724.method_36454() + 160.0F;
            E.summon(var19);
            p.reset(var19);
            t = var11.field_1724.method_73189();
            e(class_3417.field_26980, 0.85F, var2, 0.7F);
            if (MountRenderer4.K.b.enabled()) {
               for (int var20 = 0; var20 < 28; var20++) {
                  double var21 = var20 * Math.PI * 2.0 / 28.0;
                  var11.field_1687
                     .method_8406(
                        class_2398.field_11207,
                        var18.field_1352 + Math.cos(var21) * 1.4,
                        var18.field_1351 + 0.12,
                        var18.field_1350 + Math.sin(var21) * 1.4,
                        0.0,
                        0.022,
                        0.0
                     );
               }
            }

            A = 0;
         }
      }
   }

   private static class_243 k(class_310 var0, MountFactory.Data7 var1, float var2, long var3, double[] var5, double var6) {
      MountFactory.Data7.ensureSourceClassInitialized();
      double var8 = Math.toRadians(var2);

      for (double var13 : var5) {
         double var15 = var8 + Math.toRadians(var13);
         double var17 = var0.field_1724.method_23317() - Math.sin(var15) * var6;
         double var19 = var0.field_1724.method_23321() + Math.cos(var15) * var6;
         class_2338 var21 = class_2338.method_49637(var17, var0.field_1724.method_23318(), var19);
         if (var0.field_1687.method_22340(var21)) {
            class_243 var22 = new class_243(var17, var0.field_1724.method_23318() + 1.5, var19);
            long var23 = 136944581288035L;
            int var26 = var23 < 0L ? -1 : 1;
            class_3965 var26x = var0.field_1687
               .method_17742(new class_3959(var22, var22.method_1031(0.0, -4.5, 0.0), class_3960.field_17558, class_242.field_1347, var0.field_1724));
            long var27 = 136944581288036L;
            int var29 = var27 < 0L ? -1 : 1;
            if (var26x.method_17783() == class_240.field_1332
               && var26x.method_17780() == class_2350.field_11036
               && var0.field_1687.method_8316(var26x.method_17777()).method_15769()) {
               class_243 var29x = var26x.method_17784();
               double var30 = var1.halfWidth();
               double var32 = var1.halfLength();
               double var34 = var1.height();
               double var36 = var8 + Math.toRadians(160.0);
               double var38 = Math.abs(Math.cos(var36)) * var30 + Math.abs(Math.sin(var36)) * var32;
               double var40 = Math.abs(Math.sin(var36)) * var30 + Math.abs(Math.cos(var36)) * var32;
               class_238 var42 = new class_238(
                  var29x.field_1352 - var38,
                  var29x.field_1351 + 0.05,
                  var29x.field_1350 - var40,
                  var29x.field_1352 + var38,
                  var29x.field_1351 + var34,
                  var29x.field_1350 + var40
               );
               if (var0.field_1687.method_8587(var0.field_1724, var42)) {
                  boolean var43 = true;

                  for (int var44 = 0; var44 < 4; var44++) {
                     long var45 = 136944581288035L;
                     int var47 = var45 < 0L ? -1 : 1;
                     double var47x = (var44 % 2 == 0 ? -0.72 : 0.72) * var30;
                     double var49 = (var44 < 2 ? -0.72 : 0.72) * var32;
                     class_243 var51 = var29x.method_1031(
                        var47x * Math.cos(var36) - var49 * Math.sin(var36), 0.25, var47x * Math.sin(var36) + var49 * Math.cos(var36)
                     );
                     class_3965 var52 = var0.field_1687
                        .method_17742(new class_3959(var51, var51.method_1031(0.0, -0.7, 0.0), class_3960.field_17558, class_242.field_1348, var0.field_1724));
                     long var53 = 136944581288036L;
                     int var55 = var53 < 0L ? -1 : 1;
                     if (var52.method_17783() != class_240.field_1332) {
                        long var55x = 136944581288036L;
                        int var56 = var55x < 0L ? -1 : 1;
                        var43 = false;
                        break;
                     }
                  }

                  long var57 = 136944581288036L;
                  int var451 = var57 < 0L ? -1 : 1;
                  if (var43) {
                     return var29x;
                  }
               }
            }
         }
      }

      return null;
   }

   public static boolean canEnter() {
      class_310 var0 = class_310.method_1551();
      MountFactory.Data7 var1 = spec();
      double var2 = Math.max(3.4, var1.halfLength() + 0.9);
      if (E.visible() && !E.riding() && available(var0) && !(var0.field_1724.method_5707(y) > var2 * var2)) {
         class_243 var4 = y.method_1031(0.0, 0.7, 0.0);
         class_3965 var5 = var0.field_1687
            .method_17742(new class_3959(var0.field_1724.method_33571(), var4, class_3960.field_17558, class_242.field_1348, var0.field_1724));
         return var5.method_17783() == class_240.field_1333 || var5.method_17784().method_1025(var4) < 0.25;
      } else {
         return false;
      }
   }

   public static void dismount() {
      long var0 = 99971246413243L;
      long var2 = var0 ^ 42196908537311L;
      long var4 = var0 ^ 59150603892438L;
      if (E.riding()) {
         class_310 var6 = class_310.method_1551();
         MountFactory.Data7 var7 = spec();
         E.leave();
         O.leave();
         W = 0.0F;
         MountUpdater.stop();
         float var8 = E.yaw(1.0F) - 160.0F;
         double[] var9 = new double[]{160.0, 0.0, -20.0, 20.0, 90.0, -90.0};
         double var10 = var7.halfLength() + 1.1;
         S = k(var6, var7, var8, var2, var9, var10);
         K(var4);
         A = 0;
      }
   }

   public static void cycle() {
      MountModule var0 = MountRenderer4.K;
      List var1 = var0.u.modes();
      String var2 = (String)var1.get((var1.indexOf(var0.u.get()) + 1) % var1.size());
      clear();
      var0.selectStyle(var2);
      spec();
      summon();
   }

   public static void clear() {
      long var0 = 18929430103174L;
      long var2 = var0 ^ 138637617391595L;
      E.clear();
      O.reset();
      x.reset();
      p.reset(0.0F);
      W = 0.0F;
      H = 0.0F;
      MountUpdater.stop();
      K(var2);
      t = null;
      S = null;
      A = 0;
      MountRenderer8.reset();
   }

   private static void K(long var0) {
      MountFactory.Data7.ensureSourceClassInitialized();
      if (u != null) {
         class_310 var2 = class_310.method_1551();
         long var3 = 122184506467181L;
         int var5 = var3 < 0L ? -1 : 1;
         if (var2.field_1690.method_31044() == class_5498.field_26664) {
            var2.field_1690.method_31043(u);
         }

         u = null;
      }
   }

   private static void j(class_310 var0, double var1, long var3, MountFactory.Data7 var5) {
      MountFactory.Data7.ensureSourceClassInitialized();
      if (MountRenderer4.K.b.enabled() && var5.hasEngine()) {
         if (!var5.bike()) {
            float var6 = p.steering(1.0F);
            long var7 = 6058999798177L;
            int var27 = var7 < 0L ? -1 : 1;
            var6 = Math.abs(var6);
            long var10 = 6058999798178L;
            int var12 = var10 < 0L ? -1 : 1;
            if (var6 > 0.75F) {
               float var12x;
               int var13 = (var12x = E.speed() - 4.6F) == 0.0F ? 0 : (var12x < 0.0F ? -1 : 1);
               long var14 = 6058999798178L;
               int var16 = var14 < 0L ? -1 : 1;
               if (var13 > 0) {
                  long var16x = 6058999798177L;
                  int var19 = var16x < 0L ? -1 : 1;
                  var19 = b % 2;
                  long var20 = 6058999798177L;
                  int var20x = var20 < 0L ? -1 : 1;
                  if (var19 == 0) {
                     for (int var22x = 2; var22x < 4; var22x++) {
                        MountFactory.Data6 var23 = var5.wheels()[var22x];
                        if (var23 != null) {
                           class_243 var24 = f(var23.center().x, 0.05F, var23.center().z);
                           var0.field_1687.method_8406(class_2398.field_11204, var24.field_1352, var24.field_1351, var24.field_1350, 0.0, 0.01, 0.0);
                        }
                     }
                  }
               }
            }
         }

         long var26 = 6058999798177L;
         int var9 = var26 < 0L ? -1 : 1;
         var9 = !(O.throttle(1.0F) > 0.45F) && !(O.stunt(1.0F) > 0.2F) ? 7 : 2;
         if (b % var9 == 0) {
            double var27 = Math.toRadians(yaw(1.0F));

            for (Vector3f var29 : var5.exhausts()) {
               class_243 var30 = f(var29.x, var29.y, var29.z);
               double var15 = 0.028 + O.throttle(1.0F) * 0.055 + O.stunt(1.0F) * 0.025;
               var0.field_1687
                  .method_8406(
                     class_2398.field_11251, var30.field_1352, var30.field_1351, var30.field_1350, Math.sin(var27) * var15, 0.012, -Math.cos(var27) * var15
                  );
            }
         }
      }
   }

   private static void n(class_310 var0, long var1) {
      MountFactory.Data7.ensureSourceClassInitialized();
      MountFactory.Data7 var3 = spec();
      if (MountRenderer4.K.b.enabled() && !(O.rpm(1.0F) < 0.5F)) {
         for (Vector3f var5 : var3.exhausts()) {
            class_243 var6 = f(var5.x, var5.y, var5.z - 0.05F);
            var0.field_1687.method_8406(class_2398.field_27783, var6.field_1352, var6.field_1351, var6.field_1350, 0.0, 0.003, 0.0);
         }
      }
   }

   private static class_243 f(float var0, float var1, float var2) {
      return MountRenderer5.worldPoint(var0, var1, var2, 1.0F);
   }

   private static boolean i(class_310 var0, long var1, MountFactory.Data7 var3, float var4) {
      MountFactory.Data7.ensureSourceClassInitialized();
      double var5 = Math.toRadians(yaw(1.0F));
      double var7 = -Math.sin(var5) * var4;
      double var9 = Math.cos(var5) * var4;
      class_243 var11 = var0.field_1724.method_73189();
      class_238 var12 = var0.field_1724.method_5829();

      for (double var16 : new double[]{0.45, 0.85}) {
         class_238 var18 = var12.method_989(var7 * var16, 0.07, var9 * var16);
         if (!var0.field_1687.method_8587(var0.field_1724, var18)) {
            class_238 var19 = var12.method_989(var7 * var16, 1.15, var9 * var16);
            return var0.field_1687.method_8587(var0.field_1724, var19) && var0.field_1687.method_8587(var0.field_1724, var12.method_989(0.0, 1.25, 0.0));
         }
      }

      return false;
   }

   public static boolean controlsAvailable(class_310 var0) {
      return available(var0)
         && var0.field_1755 == null
         && var0.method_18506() == null
         && !var0.method_1493()
         && var0.method_1569()
         && var0.field_1729.method_1613();
   }

   public static void action() {
      long var0 = 123289133336643L;
      long var2 = var0 ^ 87752544188320L;
      class_310 var4 = class_310.method_1551();
      if (mounted() && controlsAvailable(var4)) {
         MountFactory.Data7 var5 = spec();
         if (var5.kind() == MountFactory.TypeK.BICYCLE) {
            MountUpdater.horn();
         } else if (O.action()) {
            MountUpdater.stunt(var5);
            p(actionLabel(), var2);
         }
      }
   }

   public static void horn() {
      if (mounted() && controlsAvailable(class_310.method_1551())) {
         MountUpdater.horn();
      }
   }

   public static void shift(int var0) {
      long var1 = 103489868065132L;
      long var3 = (var1 ^ 53140651251656L) >>> 16;
      int var5 = (int)((var1 ^ 53140651251656L) << 48 >>> 48);
      long var6 = var1 ^ 107690316846735L;
      long var8 = var1 ^ 68292760813323L;
      class_310 var10 = class_310.method_1551();
      if (mounted() && controlsAvailable(var10) && O.shift(var0)) {
         boolean var11 = !MountRenderer4.K.y.is("Manual");
         MountRenderer4.K.y.set("Manual");
         MountUpdater.shift();
         n(var10, var8);
         if (var11) {
            u(var3, (char)var5, "Manual · Gear " + O.gear());
         } else {
            p("Manual · Gear " + O.gear(), var6);
         }
      }
   }

   public static float inputScale() {
      class_310 var0 = class_310.method_1551();
      return mounted()
            && MountRenderer4.K.x.enabled()
            && controlsAvailable(var0)
            && var0.field_1724.method_24828()
            && !var0.field_1724.method_31549().field_7479
         ? O.inputScale(MountRenderer4.K.y.is("Manual"))
         : 1.0F;
   }

   public static boolean driving() {
      return mounted() && available(class_310.method_1551());
   }

   public static boolean wantsHop() {
      return z;
   }

   public static class_241 drivingInput(class_241 var0) {
      long var1 = 72938026838082L;
      long var3 = var1 ^ 71432795725836L;
      if (!driving()) {
         return var0;
      } else {
         class_310 var5 = class_310.method_1551();
         if (!controlsAvailable(var5)) {
            return class_241.field_1340;
         } else if (entering()) {
            return M(var3, var5);
         } else {
            MountUpdater2.Data var6 = p.input(var0.field_1342, var5.field_1724.method_36454());
            return new class_241(var6.sideways(), var6.forward());
         }
      }
   }

   private static class_241 M(long var0, class_310 var2) {
      class_243 var3 = y.method_1020(var2.field_1724.method_73189());
      MountFactory.Data7.ensureSourceClassInitialized();
      double var4 = var3.method_37267();
      long var6 = 2892849481805L;
      int var9 = var6 < 0L ? -1 : 1;
      if (var4 < 0.12) {
         return class_241.field_1340;
      } else {
         float var9x = (float)Math.min(1.0, var4 / 0.45);
         double var10 = Math.toRadians(
            MountUpdater5.wrap((float)Math.toDegrees(Math.atan2(-var3.field_1352, var3.field_1350)) - var2.field_1724.method_36454())
         );
         return new class_241((float)(-Math.sin(var10)) * var9x, (float)Math.cos(var10) * var9x);
      }
   }

   public static float cameraYawOffset(float var0) {
      if (!driving()) {
         return 0.0F;
      } else {
         float var1 = p.yaw(var0) - H;
         if (!entering()) {
            return var1;
         } else {
            float var2 = Math.clamp((boarding(var0) - 0.08F) / 0.8F, 0.0F, 1.0F);
            return var1 * var2 * var2 * (3.0F - 2.0F * var2);
         }
      }
   }

   public static float speedKmh(float var0) {
      return driving() ? p.speedKmh(var0) : 0.0F;
   }

   public static int dashboardGear() {
      if (driving() && !(startup(1.0F) < 0.1F)) {
         if (!(p.signedDistance() < -0.001F) && (!(W < 0.0F) || !(p.speedKmh(1.0F) < 2.5F))) {
            return W == 0.0F && p.speedKmh(1.0F) < 0.2F ? 0 : gear();
         } else {
            return -1;
         }
      } else {
         return 0;
      }
   }

   public static String actionLabel() {
      return switch (MountUpdater4.TypeB.G[spec().kind().ordinal()]) {
         case 1 -> "Wheelie";
         case 2 -> "Bell";
         default -> "Rev engine";
      };
   }

   public static float throttle(float var0) {
      return O.throttle(var0);
   }

   public static float rpm(float var0) {
      return O.rpm(var0);
   }

   public static float startup(float var0) {
      return O.startup(var0);
   }

   public static float stunt(float var0) {
      return O.stunt(var0);
   }

   public static float brake(float var0) {
      return O.brake(var0);
   }

   public static int gear() {
      return O.gear();
   }

   public static float actionCooldown() {
      return O.cooldown();
   }

   public static float demand() {
      return W;
   }

   public static float indicatorClock() {
      return l;
   }

   public static void pose(MountGeometry var0, float var1) {
      MountFactory.Data7 var2 = spec();
      var0.P = yaw(var1);
      var0.s = x.pitch(var1);
      var0.u = x.roll(var1);
      var0.W = Math.clamp(O.pitch(var1), -0.06F, 0.06F);
      var0.B = O.bank(var1);
      float var3 = (float)Math.sin(startup(var1) * Math.PI) * (float)Math.sin(time(var1) * 32.0F) * 0.006F;
      float var4 = var2.hasEngine() && mounted() ? (float)Math.sin(time(var1) * 44.0F) * 0.0014F * (1.0F - Math.min(1.0F, speed() / 3.0F)) : 0.0F;
      var0.j = O.heave(var1) + var3 + var4 + (var2.bike() ? 0.0F : (float)Math.sin(time(var1) * 11.0F) * 0.004F * Math.min(1.0F, speed() / 5.6F));
      float var5 = var2.kind() == MountFactory.TypeK.MOTORBIKE ? 0.15F : (var2.kind() == MountFactory.TypeK.BICYCLE ? -0.075F : 0.0F);
      var0.E = -O.lean(var1) + var5 * (1.0F - boarding(var1));
      var0.r = var2.kind() == MountFactory.TypeK.MOTORBIKE ? O.stunt(var1) * 0.62F : 0.0F;
      var0.v = p.steering(var1);
      var0.G = p.angleRadians(var2, var1);
      var0.H = p.crank(var1);
      var0.l = 0.0F;

      for (int var6 = 0; var6 < 4; var6++) {
         var0.p[var6] = p.spin(var6, var1);
         var0.L[var6] = x.travel(var6, var1);
      }

      var0.n = speedKmh(var1);
      var0.i = rpm(var1);
   }

   private static void e(class_3414 var0, float var1, long var2, float var4) {
      MountFactory.Data7.ensureSourceClassInitialized();
      MountModule var5 = MountRenderer4.K;
      if (var5.d.enabled() && (Double)var5.i.get() > 0.0) {
         class_310.method_1551().method_1483().method_4873(class_1109.method_4757(var0, var1, var4 * var5.i.floatValue()));
      }
   }

   private static void p(String var0, long var1) {
      o = var0;
      A = 90;
   }

   private static void u(long var0, char var2, String var3) {
      long var4 = 121269902573220L;
      long var6 = var4 ^ 89770700095815L;
      p(var3, var6);
      HudLayout.push(MountRenderer4.K.name(), var3, MountRenderer4.K.icon(), false);
   }

   public static String notice() {
      return A > 0 ? o : "";
   }

   public static String keyName() {
      return MountRenderer4.K.key() == -1 ? "Unbound" : KeyLabel.keyName(MountRenderer4.K.key());
   }

   public static String boardKeyName() {
      return KeyLabel.keyName(MountRenderer4.K.z.key());
   }

   public static boolean visible() {
      return MountRenderer4.K.enabled() && E.visible();
   }

   public static boolean mounted() {
      return visible() && E.riding();
   }

   public static String style() {
      return spec().name();
   }

   public static boolean bike() {
      return spec().bike();
   }

   public static class_243 renderPosition(float var0) {
      class_310 var1 = class_310.method_1551();
      class_243 var2 = mounted() && E.boarding(1.0F) >= 0.999F && var1.field_1724 != null
         ? var1.field_1724.method_30950(var0)
         : N.method_35590(y, Math.clamp(var0, 0.0F, 1.0F));
      return x.ready() ? new class_243(var2.field_1352, x.y(var0), var2.field_1350) : var2;
   }

   public static float yaw(float var0) {
      return E.yaw(var0);
   }

   public static float speed() {
      return E.speed();
   }

   public static float steering() {
      return steering(1.0F);
   }

   public static float steering(float var0) {
      return mounted() ? p.steering(var0) : E.steering();
   }

   public static float boarding(float var0) {
      return E.boarding(var0);
   }

   public static float time(float var0) {
      return E.time(var0);
   }

   public static float crank(float var0) {
      return p.crank(var0);
   }

   public static float lean(float var0) {
      return O.lean(var0);
   }

   static class TypeB {
      static final int[] G = new int[MountFactory.TypeK.values().length];

      static {
         try {
            G[MountFactory.TypeK.MOTORBIKE.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
         }

         try {
            G[MountFactory.TypeK.BICYCLE.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
         }
      }
   }
}
