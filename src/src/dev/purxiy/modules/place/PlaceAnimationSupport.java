// Sourced By DevFFNightmare
package dev.purxiy.modules.place;

import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientSupport4;
import dev.purxiy.loader.fabric.mixin.PlaceLevelRendererInvoker;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.class_1058;
import net.minecraft.class_1059;
import net.minecraft.class_10889;
import net.minecraft.class_11658;
import net.minecraft.class_11661;
import net.minecraft.class_11954;
import net.minecraft.class_12249;
import net.minecraft.class_1269;
import net.minecraft.class_1750;
import net.minecraft.class_1921;
import net.minecraft.class_1944;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2464;
import net.minecraft.class_2586;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2804;
import net.minecraft.class_2841;
import net.minecraft.class_310;
import net.minecraft.class_4076;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4608;
import net.minecraft.class_5611;
import net.minecraft.class_5819;
import net.minecraft.class_638;
import net.minecraft.class_742;
import net.minecraft.class_7522;
import net.minecraft.class_7764;
import net.minecraft.class_777;
import net.minecraft.class_7833;
import net.minecraft.class_824;
import net.minecraft.class_2350.class_2351;
import net.minecraft.class_4587.class_4665;
import org.joml.Vector3fc;

public final class PlaceAnimationSupport {
   private static final int a = 96;
   private static final int Q = 8;
   private static final double t = 48.0;
   private static final double A = 7.5;
   private static final long b = 4000000000L;
   private static final float B = 0.9985F;
   private static final float j = 1.006F;
   private static final float N = 0.95F;
   private static final float q = 0.8F;
   
   private static class_1921 x;
   private static final Map<class_7764, Boolean> U = new WeakHashMap<>();
   private static final List<PlaceAnimationSupport.TypeP> u = new ArrayList<>();
   private static volatile PlaceAnimationSupport.TypeG i = PlaceAnimationSupport.TypeG.u;
   private static int e = 0;
   private static int r = 0;
   
   private static class_1750 W;
   private static final List<PlaceAnimationSupport.TypeR> X = new ArrayList<>();
   
   private static class_2338 R;
   
   private static class_2680 n;
   
   private static class_2680 y;
   
   private static class_638 Y;
   private static long D = 0L;
   private static final PlaceMotion.TypeW v = new PlaceMotion.TypeW();
   
   private static String I;
   private static float C = -1.0F;
   private static boolean E;
   private static boolean Z;
   private static final Long2ObjectOpenHashMap<long[]> l = new Long2ObjectOpenHashMap();
   private static final Long2ObjectOpenHashMap<long[]> P = new Long2ObjectOpenHashMap();
   private static int M = 0;
   private static int f = 0;
   private static boolean w;
   private static final int[] G = new int[]{0, 1, 2, 0, 2, 3};
   private static final class_2350[] J = new class_2350[]{
      class_2350.field_11033, class_2350.field_11036, class_2350.field_11043, class_2350.field_11035, class_2350.field_11039, class_2350.field_11034, null
   };
   static PlaceAnimationModule H;
   private static ModuleContext T;
   private static int c = 0;
   private static int m = 0;
   public static boolean z;

   private static final String[] g = new String[7];
   private static final long[] h = new long[]{
      -6291364059267607919L,
      2395644663011595814L,
      924625016310230463L,
      7138187890955228808L,
      -5939402086054196259L,
      5866095205929091499L,
      -3430501988646289851L,
      -6412267165128805217L,
      5800160285335121537L,
      4332924947205634346L,
      8539379586937322344L,
      -1369924649470297767L,
      1127550179798881813L
   };


   private PlaceAnimationSupport() {
   }

   public static void placeStarted(class_1750 var0) {
      if (var0.method_8045().method_8608() && r++ == 0) {
         X.clear();
         W = var0;
      }
   }

   public static void placeEnded(class_1750 var0, class_1269 var1) {
      long var2 = 133245363759588L;
      long var4 = var2 ^ 57767956167388L;
      long var6 = var2 ^ 76654946315008L;
      if (var0.method_8045().method_8608() && r != 0 && --r <= 0) {
         class_1750 var8 = W;
         W = null;
         if (!X.isEmpty() && var1.method_23665() && var8 != null && H.enabled()) {
            class_2350 var9 = var8.method_7717() ? class_2350.field_11033 : var8.method_8038().method_10153();

            try {
               q(new ArrayList<>(X), var4, var9);
            } catch (RuntimeException var11) {
            }
         }

         X.clear();
      }
   }

   public static void blockChanged(class_2338 var0, class_2680 var1, class_2680 var2) {
      long var3 = 68793944615835L;
      long var5 = var3 ^ 128797257195926L;
      long var7 = (var3 ^ 19310063376050L) >>> 32;
      int var9 = (int)((var3 ^ 19310063376050L) << 32 >>> 32);
      if (r > 0) {
         if (l(var1, var5, var2) && X.size() < 8) {
            X.add(new PlaceAnimationSupport.TypeR(var0.method_10062(), var2));
         }
      } else if (!u.isEmpty()) {
         W(var0, var2, var7, var9);
      }
   }

   public static void destroyStarted(class_2338 var0) {
      class_310 var1 = class_310.method_1551();
      R = null;
      n = null;
      if (var1.field_1687 != null && H.enabled() && H.L.enabled()) {
         class_2680 var2 = var1.field_1687.method_8320(var0);
         if (var2.method_26217() == class_2464.field_11458 && !var2.method_26215()) {
            R = var0.method_10062();
            n = var2;
         }
      }
   }

   public static void destroyEnded(boolean var0) {
      long var1 = 124809361126017L;
      long var3 = var1 ^ 84780337672293L;
      long var5 = var1 ^ 121471555599467L;
      int var7 = (int)((var1 ^ 121471555599467L) >>> 48);
      int var8 = (int)((var1 ^ 121471555599467L) << 16 >>> 48);
      int var9 = (int)(var5 << 32 >>> 32);
      class_2338 var10 = R;
      class_2680 var11 = n;
      R = null;
      n = null;
      class_310 var12 = class_310.method_1551();
      if (var0 && var10 != null && var11 != null && var12.field_1687 != null && var12.field_1687.method_8320(var10).method_26204() != var11.method_26204()) {
         try {
            v(var12.field_1687, (short)var7, (char)var8, var9, var10, var11);
         } catch (RuntimeException var14) {
         }
      }
   }

   public static void serverUpdateStarted(class_2338 var0) {
      class_310 var1 = class_310.method_1551();
      y = var1.field_1687 != null && H.enabled() ? var1.field_1687.method_8320(var0) : null;
   }

   public static void serverUpdateEnded(class_2338 var0) {
      long var1 = 116308498162447L;
      long var3 = var1 ^ 40053030334519L;
      long var5 = var1 ^ 48194134103404L;
      long var7 = var1 ^ 37909710596354L;
      long var9 = var1 ^ 93802806960619L;
      long var11 = var1 ^ 130027190174103L;
      int var13 = (int)((var1 ^ 6431403206611L) >>> 48);
      long var14 = (var1 ^ 6431403206611L) << 16 >>> 16;
      class_2680 var16 = y;
      y = null;
      class_310 var17 = class_310.method_1551();
      if (var16 != null && var17.field_1687 != null && var17.field_1724 != null) {
         class_2680 var18 = var17.field_1687.method_8320(var0);
         if (var18 != var16 && l(var16, var7, var18) && (var16.method_26215() || var16.method_45474())) {
            try {
               if (v(var17.field_1687, var0.method_10062(), var18, var5)) {
                  return;
               }

               if (!H.A.enabled()) {
                  return;
               }

               class_243 var19 = class_243.method_24953(var0);
               if (var19.method_1025(var17.field_1724.method_33571()) > 2304.0) {
                  return;
               }

               class_742 var20 = C(var17, var19, var11);
               if (var20 != null) {
                  q(
                     List.of(new PlaceAnimationSupport.TypeR(var0.method_10062(), var18)),
                     var3,
                     s(var17.field_1687, var0, (char)var13, var20.method_33571(), var14)
                  );
               }
            } catch (RuntimeException var21) {
            }
         }
      }
   }

   private static boolean v(class_638 var0, class_2338 var1, class_2680 var2, long var3) {
      long var5 = 24804553098618L;
      long var7 = 73730273673831L;
      short var9 = 17166;
      char var10 = '\uaac5';
      int var11 = (int)(var7 << 48 >>> 48);
      PlaceMotion.ensureSourceClassInitialized();

      for (PlaceAnimationSupport.TypeP var13 : u) {
         if (!var13.T) {
            long var14 = 72693337159267L;
            if (72693337159267L == 0L) {
               boolean var10000 = false;
            } else {
               int var40 = var14 < 0L ? -1 : 1;
            }

            if (var13.n == PlaceAnimationSupport.TypeO.s) {
               long var17 = 72693337159267L;
               if (72693337159267L == 0L) {
                  boolean var41 = false;
               } else {
                  int var42 = var17 < 0L ? -1 : 1;
               }

               int var19 = var13.V.size();
               long var20 = 72693337159266L;
               if (72693337159266L == 0L) {
                  boolean var43 = false;
               } else {
                  int var44 = var20 < 0L ? -1 : 1;
               }

               if (var19 < 8) {
                  float var23;
                  var19 = (var23 = var13.age(v(var5)) - 0.9F) == 0.0F ? 0 : (var23 < 0.0F ? -1 : 1);
                  long var24 = 72693337159266L;
                  if (72693337159266L == 0L) {
                     boolean var45 = false;
                  } else {
                     int var46 = var24 < 0L ? -1 : 1;
                  }

                  if (var19 > 0) {
                     long var39 = 72693337159266L;
                     if (72693337159266L == 0L) {
                        boolean var53 = false;
                     } else {
                        int var54 = var39 < 0L ? -1 : 1;
                     }
                  } else {
                     for (PlaceAnimationSupport.TypeU var28 : var13.V) {
                        long var29 = 72693337159266L;
                        if (72693337159266L == 0L) {
                           boolean var47 = false;
                        } else {
                           int var48 = var29 < 0L ? -1 : 1;
                        }

                        var19 = var28.Q.method_19455(var1);
                        long var32 = 72693337159266L;
                        if (72693337159266L == 0L) {
                           boolean var49 = false;
                        } else {
                           int var50 = var32 < 0L ? -1 : 1;
                        }

                        if (var19 == 1 && !var28.Q.equals(var1)) {
                           var13.V.add(new PlaceAnimationSupport.TypeU(var1, var2, var0));
                           return true;
                        }
                     }

                     long var38 = 72693337159266L;
                     if (72693337159266L == 0L) {
                        boolean var51 = false;
                     } else {
                        int var52 = var38 < 0L ? -1 : 1;
                     }
                  }
               }
            }
         }
      }

      long var35 = 72693337159266L;
      if (72693337159266L == 0L) {
         boolean var55 = false;
      } else {
         int var56 = var35 < 0L ? -1 : 1;
      }

      return false;
   }

   
   private static class_742 C(class_310 var0, class_243 var1, long var2) {
      PlaceMotion.ensureSourceClassInitialized();

      for (class_742 var5 : var0.field_1687.method_18456()) {
         long var6 = 34679693304472L;
         if (34679693304472L == 0L) {
            boolean var10000 = false;
         } else {
            int var19 = var6 < 0L ? -1 : 1;
         }

         if (var5 != var0.field_1724) {
            long var9 = 34679693304472L;
            if (34679693304472L == 0L) {
               boolean var20 = false;
            } else {
               int var21 = var9 < 0L ? -1 : 1;
            }

            long var11 = 34679693304471L;
            if (34679693304471L == 0L) {
               boolean var22 = false;
            } else {
               int var23 = var11 < 0L ? -1 : 1;
            }

            if (var5.field_6252) {
               double var14;
               int var16 = (var14 = var5.method_33571().method_1025(var1) - 56.25) == 0.0 ? 0 : (var14 < 0.0 ? -1 : 1);
               long var17 = 34679693304471L;
               if (34679693304471L == 0L) {
                  boolean var24 = false;
               } else {
                  int var25 = var17 < 0L ? -1 : 1;
               }

               if (var16 < 0) {
                  return var5;
               }
            }
         }
      }

      return null;
   }

   private static class_2350 s(class_638 var0, class_2338 var1, char var2, class_243 var3, long var4) {
      PlaceMotion.ensureSourceClassInitialized();
      if (var0.method_8320(var1.method_10074()).method_26206(var0, var1.method_10074(), class_2350.field_11036)) {
         return class_2350.field_11033;
      } else {
         class_2350 var6 = class_2350.field_11033;
         double var7 = Double.MAX_VALUE;

         for (class_2350 var12 : class_2350.values()) {
            class_2338 var13 = var1.method_10093(var12);
            boolean var14 = false;
            if (var0.method_8320(var13).method_26206(var0, var13, var12.method_10153())) {
               double var15 = class_243.method_24953(var13).method_1025(var3);
               long var17 = 118821397939420L;
               if (118821397939420L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var21 = var17 < 0L ? -1 : 1;
               }

               long var19 = 118821397939419L;
               if (118821397939419L == 0L) {
                  boolean var22 = false;
               } else {
                  int var23 = var19 < 0L ? -1 : 1;
               }

               if (var15 < var7) {
                  var7 = var15;
                  var6 = var12;
               }
            }
         }

         return var6;
      }
   }

   private static boolean l(class_2680 var0, long var1, class_2680 var3) {
      PlaceMotion.ensureSourceClassInitialized();
      long var4 = 83230634199565L;
      if (83230634199565L == 0L) {
         boolean var10000 = false;
      } else {
         int var9 = var4 < 0L ? -1 : 1;
      }

      if (!var3.method_26215()) {
         long var7 = 83230634199564L;
         if (83230634199564L == 0L) {
            boolean var10 = false;
         } else {
            int var11 = var7 < 0L ? -1 : 1;
         }

         if (var0.method_26204() != var3.method_26204() && (var3.method_26217() == class_2464.field_11458 || var3.method_31709())) {
            return true;
         }
      }

      return false;
   }

   private static void q(List var0, long var1, class_2350 var3) {
      long var4 = 86284767263384L;
      short var6 = 20089;
      char var7 = '볦';
      int var8 = (int)(var4 << 48 >>> 48);
      long var9 = 112329766203653L;
      long var11 = 67998896542702L;
      long var13 = 64262948556768L;
      var4 = 73730273673831L;
      short var15 = 17166;
      char var16 = '\uaac5';
      int var17 = (int)(var4 << 48 >>> 48);
      PlaceMotion.ensureSourceClassInitialized();
      class_310 var18 = class_310.method_1551();
      class_638 var19 = var18.field_1687;
      if (var19 != null) {
         for (PlaceAnimationSupport.TypeR var21 : ((Iterable<PlaceAnimationSupport.TypeR>)(Object)var0)) {
            p(var21.w, var11);
         }

         ArrayList var42 = new ArrayList(var0.size());
         long var43 = 85374220276536L;
         if (85374220276536L == 0L) {
            boolean var10000 = false;
         } else {
            int var50 = var43 < 0L ? -1 : 1;
         }

         ArrayList var24 = var42;
         class_238 var25 = null;

         label100:
         for (PlaceAnimationSupport.TypeR var27 : ((Iterable<PlaceAnimationSupport.TypeR>)(Object)var0)) {
            class_2680 var28 = var19.method_8320(var27.w);
            if (var28.method_26204() != var27.Y.method_26204()) {
               long var46 = 85374220276536L;
               if (85374220276536L == 0L) {
                  boolean var59 = false;
               } else {
                  int var60 = var46 < 0L ? -1 : 1;
               }
            } else {
               for (PlaceAnimationSupport.TypeU var30 : ((Iterable<PlaceAnimationSupport.TypeU>)(Object)var24)) {
                  long var31 = 85374220276536L;
                  if (85374220276536L == 0L) {
                     boolean var51 = false;
                  } else {
                     int var52 = var31 < 0L ? -1 : 1;
                  }

                  boolean var34 = var30.Q.equals(var27.w);
                  long var35 = 85374220276536L;
                  if (85374220276536L == 0L) {
                     boolean var53 = false;
                  } else {
                     int var54 = var35 < 0L ? -1 : 1;
                  }

                  if (var34) {
                     long var38 = 85374220276535L;
                     if (85374220276535L == 0L) {
                        boolean var55 = false;
                     } else {
                        int var56 = var38 < 0L ? -1 : 1;
                     }
                     continue label100;
                  }
               }

               var24.add(new PlaceAnimationSupport.TypeU(var27.w, var28, var19));
               long var45 = 85374220276536L;
               if (85374220276536L == 0L) {
                  boolean var57 = false;
               } else {
                  int var58 = var45 < 0L ? -1 : 1;
               }

               class_238 var32 = Y(var19, var13, var27.w, var28);
               var25 = var25 == null ? var32 : var25.method_991(var32);
            }
         }

         long var44 = 85374220276535L;
         if (85374220276535L == 0L) {
            boolean var61 = false;
         } else {
            int var62 = var44 < 0L ? -1 : 1;
         }

         if (!var24.isEmpty() && var25 != null) {
            PlaceAnimationModule var47 = H;
            PlaceMotion.Style var48 = var47.motionStyle();
            class_243 var49 = n(var25, var6, (short)var7, var3, var48.fromFace(), var8);
            A(var9, new PlaceAnimationSupport.TypeP(var24, var49, var3, var48, var47.J.floatValue(), var47.l.floatValue(), false, System.nanoTime()));
         }
      }
   }

   private static void v(class_638 var0, short var1, char var2, int var3, class_2338 var4, class_2680 var5) {
      long var6 = 35155335548650L;
      long var8 = var6 ^ 133938329059311L;
      long var10 = var6 ^ 37525252693252L;
      long var12 = var6 ^ 41280531923210L;
      p(var4, var10);
      PlaceAnimationModule var14 = H;
      class_238 var15 = Y(var0, var12, var4, var5);
      List var16 = List.of(new PlaceAnimationSupport.TypeU(var4, var5, var0));
      A(
         var8,
         new PlaceAnimationSupport.TypeP(
            var16, var15.method_1005(), class_2350.field_11033, PlaceMotion.Style.I, var14.J.floatValue(), var14.l.floatValue() * 0.8F, true, System.nanoTime()
         )
      );
   }

   private static void A(long var0, PlaceAnimationSupport.TypeP var2) {
      long var3 = 6360580864854L;
      byte var5 = 0;
      int var6 = 97054761;
      int var7 = (int)(var3 << 48 >>> 48);
      PlaceMotion.ensureSourceClassInitialized();
      if (u.size() >= 96) {
         Z(u.removeFirst(), var5, var6, (char)var7);
      }

      u.add(var2);
   }

   private static class_243 n(class_238 var0, int var1, short var2, class_2350 var3, boolean var4, int var5) {
      PlaceMotion.ensureSourceClassInitialized();
      class_243 var6 = var0.method_1005();
      if (!var4) {
         return var6;
      } else {
         return switch (PlaceAnimationSupport.TypeK.A[var3.ordinal()]) {
            case 1 -> new class_243(var6.field_1352, var0.field_1322, var6.field_1350);
            case 2 -> new class_243(var6.field_1352, var0.field_1325, var6.field_1350);
            case 3 -> new class_243(var6.field_1352, var6.field_1351, var0.field_1321);
            case 4 -> new class_243(var6.field_1352, var6.field_1351, var0.field_1324);
            case 5 -> new class_243(var0.field_1323, var6.field_1351, var6.field_1350);
            case 6 -> new class_243(var0.field_1320, var6.field_1351, var6.field_1350);
            default -> throw new MatchException(null, null);
         };
      }
   }

   private static class_238 Y(class_638 var0, long var1, class_2338 var3, class_2680 var4) {
      PlaceMotion.ensureSourceClassInitialized();
      class_265 var5 = var4.method_26218(var0, var3);
      return var5.method_1110() ? new class_238(var3) : var5.method_1107().method_996(var3);
   }

   private static void W(class_2338 var0, class_2680 var1, long var2, int var4) {
      long var5 = 51808845593897L;
      long var7 = var5 ^ 118816060473166L;
      int var9 = (int)((var5 ^ 118816060473166L) >>> 32);
      int var10 = (int)((var5 ^ 118816060473166L) << 32 >>> 48);
      int var11 = (int)(var7 << 48 >>> 48);
      boolean var12 = false;
      PlaceMotion.ensureSourceClassInitialized();

      for (int var13 = u.size() - 1; var13 >= 0; var13--) {
         PlaceAnimationSupport.TypeP var14 = u.get(var13);

         for (int var15 = var14.V.size() - 1; var15 >= 0; var15--) {
            PlaceAnimationSupport.TypeU var16 = var14.V.get(var15);
            boolean var17 = var16.Q.equals(var0);
            int var18 = -1344897751;
            if (!var17) {
               long var19 = 12062L;
               if (12062L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var26 = var19 < 0L ? -1 : 1;
               }
            } else {
               long var25 = 12062L;
               if (12062L == 0L) {
                  boolean var27 = false;
               } else {
                  int var28 = var25 < 0L ? -1 : 1;
               }

               if (var14.T) {
                  if (!var1.method_26215()) {
                     u.remove(var13);
                  }
                  break;
               }

               if (var1.method_26204() == var16.j.method_26204()) {
                  var17 = var12 | var16.restate(var1);
                  var12 = var17;
               } else {
                  var14.V.remove(var15);
                  if (var14.n == PlaceAnimationSupport.TypeO.i && !var16.t) {
                     e--;
                  }

                  var12 = true;
               }
            }
         }

         long var23 = 12062L;
         if (12062L == 0L) {
            boolean var29 = false;
         } else {
            int var30 = var23 < 0L ? -1 : 1;
         }

         if (var14.V.isEmpty()) {
            u.remove(var13);
         }
      }

      long var22 = 12062L;
      if (12062L == 0L) {
         boolean var31 = false;
      } else {
         int var32 = var22 < 0L ? -1 : 1;
      }

      if (var12) {
      }
   }

   private static void p(class_2338 var0, long var1) {
      long var3 = 73730273673831L;
      short var5 = 17166;
      char var6 = '\uaac5';
      int var7 = (int)(var3 << 48 >>> 48);
      PlaceMotion.ensureSourceClassInitialized();
      boolean var8 = false;

      for (int var9 = u.size() - 1; var9 >= 0; var9--) {
         PlaceAnimationSupport.TypeP var10 = u.get(var9);
         boolean var11 = var10.V.removeIf(var2 -> {
            PlaceMotion.ensureSourceClassInitialized();
            if (var2.Q.equals(var0)) {
               if (var10.n == PlaceAnimationSupport.TypeO.i && !var2.t) {
                  e--;
               }

               return true;
            } else {
               return false;
            }
         });
         long var12 = 67998896542701L;
         if (67998896542701L == 0L) {
            boolean var10000 = false;
         } else {
            int var20 = var12 < 0L ? -1 : 1;
         }

         if (var11) {
            var8 = true;
         }

         var11 = var10.V.isEmpty();
         long var15 = 67998896542702L;
         if (67998896542702L == 0L) {
            boolean var21 = false;
         } else {
            int var22 = var15 < 0L ? -1 : 1;
         }

         if (var11) {
            u.remove(var9);
         }
      }

      long var18 = 67998896542702L;
      if (67998896542702L == 0L) {
         boolean var23 = false;
      } else {
         int var24 = var18 < 0L ? -1 : 1;
      }

      long var13 = 67998896542702L;
      if (67998896542702L == 0L) {
         boolean var25 = false;
      } else {
         int var26 = var13 < 0L ? -1 : 1;
      }

      if (var8) {
      }
   }

   public static void clear() {
      long var0 = 5841575033481L;
      long var2 = var0 ^ 48628705472919L;
      long var4 = var0 ^ 77372556884206L;
      int var6 = (int)((var0 ^ 77372556884206L) >>> 32);
      int var7 = (int)((var0 ^ 77372556884206L) << 32 >>> 48);
      int var8 = (int)(var4 << 48 >>> 48);
      class_310 var9 = class_310.method_1551();

      for (PlaceAnimationSupport.TypeP var11 : u) {
         if (!var11.T && var11.n == PlaceAnimationSupport.TypeO.s) {
            Q(var9, var2, var11);
         }
      }

      u.clear();
      e = 0;
      r = 0;
      X.clear();
      W = null;
   }

   private static void Z(PlaceAnimationSupport.TypeP var0, short var1, int var2, char var3) {
      long var4 = 6360580864854L;
      long var6 = var4 ^ 49077142138952L;
      long var8 = var4 ^ 77817742899505L;
      int var10 = (int)((var4 ^ 77817742899505L) >>> 32);
      int var11 = (int)((var4 ^ 77817742899505L) << 32 >>> 48);
      int var12 = (int)(var8 << 48 >>> 48);
      PlaceMotion.ensureSourceClassInitialized();
      if (!var0.T && var0.n == PlaceAnimationSupport.TypeO.s) {
         Q(class_310.method_1551(), var6, var0);
      }

      if (var0.n == PlaceAnimationSupport.TypeO.i) {
         for (PlaceAnimationSupport.TypeU var14 : var0.V) {
            if (!var14.t) {
               e--;
            }
         }
      }
   }

   private static void a(int var0, int var1, short var2) {
      long var3 = (long)var0 << 32 | 2865037312L | 33383L;
      int var5 = (int)((var3 ^ 102207738715260L) >>> 48);
      long var6 = (var3 ^ 102207738715260L) << 16 >>> 16;
      i = r(u, (short)var5, var6);
   }

   private static PlaceAnimationSupport.TypeG r(List var0, short var1, long var2) {
      long var4 = (long)var1 << 48 | var2 << 16 >>> 16;
      int var6 = (int)((var4 ^ 31098243061609L) >>> 32);
      int var7 = (int)((var4 ^ 31098243061609L) << 32 >>> 32);
      PlaceMotion.ensureSourceClassInitialized();
      int var8 = 0;
      byte var9 = 0;

      for (PlaceAnimationSupport.TypeP var11 : ((Iterable<PlaceAnimationSupport.TypeP>)(Object)var0)) {
         byte var12 = (byte)(var11.T ? 1 : 0);
         if (var2 >= 0L) {
            if (!var11.T && var11.n == PlaceAnimationSupport.TypeO.s) {
               var8 += var11.V.size();
            }

            var12 = var9;
         }

         if (var12 != 0) {
            break;
         }
      }

      if (var2 > 0L && var8 == 0) {
         return PlaceAnimationSupport.TypeG.u;
      } else {
         long[] var22 = new long[var8];
         class_2680[] var23 = new class_2680[var8];
         byte[] var24 = new byte[var8];
         byte[] var13 = new byte[var8];
         class_638 var14 = class_310.method_1551().field_1687;
         int var15 = 0;
         Iterator var16 = var0.iterator();

         label99:
         while (true) {
            Object var17;
            if (var16.hasNext()) {
               var17 = var16.next();
            } else {
               var17 = new PlaceAnimationSupport.TypeG(var22, var23, var24, var13);
               if (var2 > 1L) {
                  return (PlaceAnimationSupport.TypeG)var17;
               }
            }

            while (true) {
               label95:
               while (true) {
                  PlaceAnimationSupport.TypeP var18 = (PlaceAnimationSupport.TypeP)var17;
                  PlaceAnimationSupport.TypeP var19 = var18;
                  if (var1 >= 0) {
                     if (var2 <= 1L) {
                        if (!var18.T) {
                           continue label99;
                        }
                        break;
                     }

                     if (var18.T) {
                        if (var1 < 0 || var9 == 0) {
                           continue label99;
                        }
                        break;
                     }

                     var19 = var18;
                  }

                  if (var19.n == PlaceAnimationSupport.TypeO.s) {
                     for (PlaceAnimationSupport.TypeU var21 : var18.V) {
                        if (var1 >= 0) {
                           var22[var15] = var21.Q.method_10063();
                           var17 = var23;
                           if (var2 < 0L) {
                              continue label95;
                           }

                           var23[var15] = var21.g;
                           if (var2 >= 1L) {
                              if (var14 != null) {
                                 var24[var15] = (byte)F(var14, var21.Q, class_1944.field_9284, var6, var7);
                                 var13[var15] = (byte)F(var14, var21.Q, class_1944.field_9282, var6, var7);
                              }

                              var15++;
                           }
                        }
                     }

                     if (var1 < 0 || var9 == 0) {
                        continue label99;
                     }
                     break;
                  }

                  if (var1 < 0 || var9 == 0) {
                     continue label99;
                  }
                  break;
               }

               var17 = new PlaceAnimationSupport.TypeG(var22, var23, var24, var13);
               if (var2 > 1L) {
                  return (PlaceAnimationSupport.TypeG)var17;
               }
            }
         }
      }
   }

   public static void hideIn( class_7522<class_2680> var0, int var1, int var2, int var3) {
      PlaceAnimationSupport.TypeG var4 = i;
      if (var4.c.length != 0 && var0 instanceof class_2841 var5) {
         for (int var6 = 0; var6 < var4.c.length; var6++) {
            long var7 = var4.c[var6];
            int var9 = class_2338.method_10061(var7);
            int var10 = class_2338.method_10071(var7);
            int var11 = class_2338.method_10083(var7);
            if (class_4076.method_18675(var9) == var1 && class_4076.method_18675(var10) == var2 && class_4076.method_18675(var11) == var3) {
               var5.method_16678(var9 & 15, var10 & 15, var11 & 15, var4.l[var6]);
            }
         }
      }
   }

   private static int F(class_638 var0, class_2338 var1, class_1944 var2, int var3, int var4) {
      int var5 = 0;
      PlaceMotion.ensureSourceClassInitialized();
      class_2350[] var6 = class_2350.values();
      int var7 = var6.length;
      int var8 = 0;
      byte var9 = 0;

      while (var8 < var7) {
         class_2350 var10 = var6[var8];
         int var11 = var0.method_8314(var2, var1.method_10093(var10));
         if (var4 >= 0) {
            int var12;
            int var13;
            label29: {
               if (var2 == class_1944.field_9284 && var10 == class_2350.field_11036) {
                  var13 = var11;
                  byte var14 = var9;
                  if (var3 >= 0) {
                     var12 = 15607;
                     if (var4 <= 0) {
                        break label29;
                     }

                     var14 = 15;
                  }

                  if (var11 == var14) {
                     return 15;
                  }
               }

               var13 = var5;
               var12 = var11 - 1;
            }

            var5 = Math.max(var13, var12);
            var8++;
         }
      }

      return var5;
   }

   
   public static ClientSupport4 lightIn(int var0, int var1, int var2) {
      PlaceAnimationSupport.TypeG var3 = i;
      if (var3.c.length == 0) {
         return null;
      } else {
         int var4 = 0;
         long[] var5 = new long[var3.c.length];
         byte[] var6 = new byte[var3.c.length];
         byte[] var7 = new byte[var3.c.length];

         for (int var8 = 0; var8 < var3.c.length; var8++) {
            long var9 = var3.c[var8];
            int var11 = class_4076.method_18675(class_2338.method_10061(var9)) - var0;
            int var12 = class_4076.method_18675(class_2338.method_10071(var9)) - var1;
            int var13 = class_4076.method_18675(class_2338.method_10083(var9)) - var2;
            if (var11 >= 0 && var11 < 3 && var12 >= 0 && var12 < 3 && var13 >= 0 && var13 < 3) {
               var5[var4] = var9;
               var6[var4] = var3.p[var8];
               var7[var4] = var3.i[var8];
               var4++;
            }
         }

         return var4 == 0 ? null : new ClientSupport4(Arrays.copyOf(var5, var4), Arrays.copyOf(var6, var4), Arrays.copyOf(var7, var4));
      }
   }

   public static void lightIn(class_2804[] var0, int var1, int var2, int var3) {
      PlaceAnimationSupport.TypeG var4 = i;
      if (var4.c.length != 0) {
         boolean[] var5 = new boolean[var0.length];

         for (int var6 = 0; var6 < var4.c.length; var6++) {
            long var7 = var4.c[var6];
            int var9 = class_2338.method_10061(var7);
            int var10 = class_2338.method_10071(var7);
            int var11 = class_2338.method_10083(var7);
            if (class_4076.method_18675(var9) == var1 && class_4076.method_18675(var10) == var2 && class_4076.method_18675(var11) == var3) {
               for (int var12 = 0; var12 < var0.length && var12 < 2; var12++) {
                  class_2804 var13 = var0[var12];
                  byte var14 = var12 == class_1944.field_9284.ordinal() ? var4.p[var6] : var4.i[var6];
                  if (var13 != null && var13.method_12139(var9 & 15, var10 & 15, var11 & 15) != var14) {
                     if (!var5[var12]) {
                        var13 = var13.method_12144();
                        var0[var12] = var13;
                        var5[var12] = true;
                     }

                     var13.method_12145(var9 & 15, var10 & 15, var11 & 15, var14);
                  }
               }
            }
         }
      }
   }

   public static void sectionCopied(int var0, int var1, int var2) {
      if (e > 0) {
         for (PlaceAnimationSupport.TypeP var4 : u) {
            if (var4.n == PlaceAnimationSupport.TypeO.i) {
               for (PlaceAnimationSupport.TypeU var6 : var4.V) {
                  if (!var6.M && var6.in(var0, var1, var2)) {
                     var6.M = true;
                  }
               }
            }
         }
      }
   }

   public static void sectionMeshed(int var0, int var1, int var2) {
      if (e > 0) {
         for (PlaceAnimationSupport.TypeP var4 : u) {
            if (var4.n == PlaceAnimationSupport.TypeO.i) {
               for (PlaceAnimationSupport.TypeU var6 : var4.V) {
                  if (var6.M && !var6.t && var6.in(var0, var1, var2)) {
                     var6.t = true;
                     e--;
                  }
               }
            }
         }
      }
   }

   private static void Q(class_310 var0, long var1, PlaceAnimationSupport.TypeP var3) {
      PlaceMotion.ensureSourceClassInitialized();
      if (var0.field_1769 instanceof PlaceLevelRendererInvoker var5) {
         for (PlaceAnimationSupport.TypeU var7 : var3.V) {
            var5.purxiy$setBlockDirty(var7.Q, true);
         }
      }
   }

   public static void beginFrame() {
      long var0 = 132991314315553L;
      long var2 = var0 ^ 89739813863999L;
      long var4 = var0 ^ 65948665629510L;
      int var6 = (int)((var0 ^ 65948665629510L) >>> 32);
      int var7 = (int)((var0 ^ 65948665629510L) << 32 >>> 48);
      int var8 = (int)(var4 << 48 >>> 48);
      D = System.nanoTime();
      class_310 var9 = class_310.method_1551();
      if (var9.field_1687 != Y) {
         Y = var9.field_1687;
         u.clear();
         e = 0;
      } else if (!u.isEmpty()) {
         if (!H.enabled()) {
            clear();
         } else {
            boolean var10 = false;

            for (int var11 = u.size() - 1; var11 >= 0; var11--) {
               PlaceAnimationSupport.TypeP var12 = u.get(var11);
               if (!var9.field_1687.method_8477(var12.V.getFirst().Q)) {
                  if (var12.n == PlaceAnimationSupport.TypeO.i) {
                     for (PlaceAnimationSupport.TypeU var20 : var12.V) {
                        if (!var20.t) {
                           e--;
                        }
                     }
                  }

                  var10 |= var12.n == PlaceAnimationSupport.TypeO.s && !var12.T;
                  u.remove(var11);
               } else if (var12.n == PlaceAnimationSupport.TypeO.s) {
                  if (!(var12.age(D) < 1.0F)) {
                     if (var12.T) {
                        u.remove(var11);
                     } else {
                        var12.n = PlaceAnimationSupport.TypeO.i;
                        var12.j = D;
                        e = e + var12.V.size();
                        var10 = true;
                     }
                  }
               } else {
                  boolean var13 = true;

                  for (PlaceAnimationSupport.TypeU var15 : var12.V) {
                     if (var15.t) {
                        var15.x++;
                     }

                     var13 &= var15.t && var15.x >= 2;
                  }

                  if (var13 || D - var12.j > 4000000000L) {
                     for (PlaceAnimationSupport.TypeU var21 : var12.V) {
                        if (!var21.t) {
                           e--;
                        }
                     }

                     u.remove(var11);
                  }
               }
            }

            if (var10) {

               for (PlaceAnimationSupport.TypeP var17 : u) {
                  if (var17.n == PlaceAnimationSupport.TypeO.i && var17.j == D) {
                     Q(var9, var2, var17);
                  }
               }
            }

            if (e < 0) {
               e = 0;
            }
         }
      }
   }

   public static void submit(class_4587 var0, class_11658 var1, class_11661 var2) {
      long var3 = 63730806911482L;
      long var5 = var3 ^ 135438369193511L;
      long var7 = var3 ^ 5972023063326L;
      var5 = var3 ^ 68441414751733L;
      int var9 = (int)((var3 ^ 68441414751733L) >>> 32);
      int var10 = (int)((var3 ^ 68441414751733L) << 32 >>> 48);
      int var11 = (int)(var5 << 48 >>> 48);
      class_310 var12 = class_310.method_1551();
      if (!u.isEmpty() && var12.field_1687 != null) {
         try {
            class_243 var13 = var1.field_63082.field_63078;
            PlaceAnimationModule var14 = H;
            PlaceAnimationLoader var15 = PlaceAnimationLoader.resolve(var14.D.get(), var14.q.argb());
            float var16 = var14.v.floatValue();
            class_824 var17 = var12.method_31975();
            float var18 = var12.method_61966().method_60637(false);

            for (PlaceAnimationSupport.TypeP var20 : u) {
               float var21 = var20.n == PlaceAnimationSupport.TypeO.i ? 1.0F : Math.min(var20.age(D), 1.0F);
               if (var20.T) {
                  PlaceMotion.broken(var21, v);
               } else {
                  PlaceMotion.placed(var20.R, var21, var20.L, v);
               }

               float var22 = var20.n == PlaceAnimationSupport.TypeO.i ? 0.9985F : 1.0F;
               float var23 = var16 * v.W;

               for (PlaceAnimationSupport.TypeU var25 : var20.V) {
                  var0.method_22903();
                  f(var0, var9, (char)var10, var20, var11, var25, var13, var22);
                  if (var25.T) {
                     var2.method_73485(var0, var25.c);
                     metric("drawn");
                  }

                  if (var25.r && !var20.T) {
                     class_2586 var26 = var12.field_1687.method_8321(var25.Q);
                     if (var26 != null) {
                        class_11954 var27 = var17.method_74348(var26, var18, null);
                        if (var27 != null) {
                           var17.method_3555(var27, var0, var2, var1.field_63082);
                        }
                     }
                  }

                  if (var23 > 0.004F && var25.T) {
                     List var31 = var25.parts(var12);
                     int var32 = var15.V;
                     int var28 = var15.L;
                     var2.method_73483(var0, class_12249.method_76005(), (var4, var5x) -> {
                        long var6 = 57964375062897L;
                        E(var4, var5x, var6, var31, var23, var32, var28, true);
                     });
                     if (var25.seeThrough(var31)) {
                        if (x == null) {
                           x = class_12249.method_75974(class_1059.field_5275, 0.0F, 0.0F);
                        }

                        var2.method_73483(var0, x, (var4, var5x) -> {
                           long var6 = 73496822091741L;
                           long var8 = var6 ^ 130222033024684L;
                           E(var4, var5x, var8, var31, var23, var32, var28, false);
                        });
                     }
                  }

                  var0.method_22909();
               }
            }
         } catch (RuntimeException var29) {
         }
      }
   }

   private static long[] K(int var0, int var1, long var2, int var4) {
      PlaceMotion.ensureSourceClassInitialized();
      PlaceAnimationSupport.TypeG var5 = i;
      long[] var6 = new long[0];
      long[] var7 = var5.c;
      int var8 = var7.length;

      for (int var9 = 0; var9 < var8; var9++) {
         long var10 = 68216909674148L;
         if (68216909674148L == 0L) {
            boolean var10000 = false;
         } else {
            int var27 = var10 < 0L ? -1 : 1;
         }

         long var13 = var7[var9];
         long var15 = 68216909674147L;
         if (68216909674147L == 0L) {
            boolean var28 = false;
         } else {
            int var29 = var15 < 0L ? -1 : 1;
         }

         int var18 = class_4076.method_18675(class_2338.method_10061(var13));
         long var19 = 68216909674147L;
         if (68216909674147L == 0L) {
            boolean var30 = false;
         } else {
            int var31 = var19 < 0L ? -1 : 1;
         }

         if (var18 == var0) {
            var18 = class_2338.method_10071(var13);
            long var21 = 68216909674147L;
            if (68216909674147L == 0L) {
               boolean var32 = false;
            } else {
               int var33 = var21 < 0L ? -1 : 1;
            }

            var18 = class_4076.method_18675(var18);
            long var23 = 68216909674148L;
            if (68216909674148L == 0L) {
               boolean var34 = false;
            } else {
               int var35 = var23 < 0L ? -1 : 1;
            }

            if (var18 == var1 && class_4076.method_18675(class_2338.method_10083(var13)) == var4) {
               var6 = Arrays.copyOf(var6, var6.length + 1);
               var6[var6.length - 1] = var13;
            }
         }
      }

      return var6;
   }

   private static void s(long var0, class_638 var2) {
      long var3 = 93817125659657L;
      PlaceMotion.ensureSourceClassInitialized();
      f++;
      ObjectIterator var5 = P.values().iterator();

      while (var5.hasNext()) {
         long[] var6 = (long[])var5.next();

         for (long var10 : var6) {
            class_2338 var12 = class_2338.method_10092(var10);
            long var13 = 88371620314461L;
            if (88371620314461L == 0L) {
               boolean var10000 = false;
            } else {
               int var24 = var13 < 0L ? -1 : 1;
            }

            long var16 = 88371620314462L;
            if (88371620314462L == 0L) {
               boolean var25 = false;
            } else {
               int var26 = var16 < 0L ? -1 : 1;
            }

            boolean var18 = var2.method_8320(var12).method_26215();
            long var19 = 88371620314462L;
            if (88371620314462L == 0L) {
               boolean var27 = false;
            } else {
               int var28 = var19 < 0L ? -1 : 1;
            }

            if (!var18) {
               if (q(var3, var12)) {
                  long var21 = 88371620314461L;
                  if (88371620314461L == 0L) {
                     boolean var29 = false;
                  } else {
                     int var30 = var21 < 0L ? -1 : 1;
                  }
               } else {
                  M++;
                  ClientServices.LOGGER
                     .warn("Place animation check: {} is missing this frame (frame {}, {} gaps so far)", new Object[]{var12.method_23854(), f, M});
               }
            }
         }

         long var23 = 88371620314461L;
         if (88371620314461L == 0L) {
            boolean var31 = false;
         } else {
            int var32 = var23 < 0L ? -1 : 1;
         }
      }
   }

   private static boolean q(long var0, class_2338 var2) {
      PlaceMotion.ensureSourceClassInitialized();

      for (PlaceAnimationSupport.TypeP var4 : u) {
         for (PlaceAnimationSupport.TypeU var6 : var4.V) {
            long var7 = 93817125659656L;
            if (93817125659656L == 0L) {
               boolean var10000 = false;
            } else {
               int var11 = var7 < 0L ? -1 : 1;
            }

            if (var6.Q.equals(var2)) {
               return true;
            }
         }

         long var10 = 93817125659657L;
         if (93817125659657L == 0L) {
            boolean var12 = false;
         } else {
            int var13 = var10 < 0L ? -1 : 1;
         }
      }

      return false;
   }

   
   private static PlaceAnimationSupport.TypeP x(class_2338 var0, long var1) {
      PlaceMotion.ensureSourceClassInitialized();

      for (PlaceAnimationSupport.TypeP var4 : u) {
         long var5 = 76859810266496L;
         if (76859810266496L == 0L) {
            boolean var10000 = false;
         } else {
            int var16 = var5 < 0L ? -1 : 1;
         }

         if (!var4.T && var4.n == PlaceAnimationSupport.TypeO.s) {
            for (PlaceAnimationSupport.TypeU var9 : var4.V) {
               long var10 = 76859810266496L;
               if (76859810266496L == 0L) {
                  boolean var17 = false;
               } else {
                  int var18 = var10 < 0L ? -1 : 1;
               }

               boolean var12 = var9.Q.equals(var0);
               long var13 = 76859810266496L;
               if (76859810266496L == 0L) {
                  boolean var19 = false;
               } else {
                  int var20 = var13 < 0L ? -1 : 1;
               }

               if (var12) {
                  return var4;
               }
            }
         }

         long var15 = 76859810266497L;
         if (76859810266497L == 0L) {
            boolean var21 = false;
         } else {
            int var22 = var15 < 0L ? -1 : 1;
         }
      }

      return null;
   }

   public static boolean follow(class_2338 var0, double[] var1) {
      long var2 = 48303993650244L;
      long var4 = var2 ^ 67490662218046L;
      long var6 = var2 ^ 120989091480005L;
      PlaceAnimationSupport.TypeP var8 = x(var0, var6);
      if (var8 == null) {
         return false;
      } else {
         PlaceMotion.TypeW var9 = PlaceMotion.placed(var8.R, Math.min(var8.age(v(var4)), 1.0F), var8.L, new PlaceMotion.TypeW());
         class_2351 var10 = var8.C.method_10166();
         class_2350 var11 = var8.C.method_10153();
         double[] var12 = new double[]{var8.W.field_1352, var8.W.field_1351, var8.W.field_1350};
         int[] var13 = new int[]{var11.method_10148(), var11.method_10164(), var11.method_10165()};

         for (int var14 = 0; var14 < 3; var14++) {
            float var15 = var10.ordinal() == var14 ? var9.Z : var9.t;
            var1[var14] = var12[var14] + (var1[var14] - var12[var14]) * var15 + var13[var14] * var9.u;
            var1[3 + var14] = var1[3 + var14] * var15;
         }

         if (var9.h != 0.0F) {
            int var24 = var10 == class_2351.field_11048 ? 4 : 3;
            int var25 = var10 == class_2351.field_11051 ? 4 : 5;
            double var16 = Math.abs(Math.cos(var9.h));
            double var18 = Math.abs(Math.sin(var9.h));
            double var20 = var1[var24];
            double var22 = var1[var25];
            var1[var24] = var16 * var20 + var18 * var22;
            var1[var25] = var18 * var20 + var16 * var22;
         }

         return true;
      }
   }

   public static boolean follow(class_2338 var0, class_4587 var1, double var2, double var4, double var6) {
      long var8 = 88853496176923L;
      long var10 = var8 ^ 77243166499937L;
      long var12 = var8 ^ 23263701081242L;
      PlaceAnimationSupport.TypeP var14 = x(var0, var12);
      if (var14 == null) {
         return false;
      } else {
         PlaceMotion.placed(var14.R, Math.min(var14.age(v(var10)), 1.0F), var14.L, v);
         class_2350 var15 = var14.C.method_10153();
         double var16 = var14.W.field_1352 - var2;
         double var18 = var14.W.field_1351 - var4;
         double var20 = var14.W.field_1350 - var6;
         var1.method_22904(var16 + var15.method_10148() * v.u, var18 + var15.method_10164() * v.u, var20 + var15.method_10165() * v.u);
         class_2351 var22 = var14.C.method_10166();
         if (v.h != 0.0F) {
            var1.method_22907(switch (PlaceAnimationSupport.TypeK.a[var22.ordinal()]) {
               case 1 -> class_7833.field_40714.rotation(v.h);
               case 2 -> class_7833.field_40716.rotation(v.h);
               case 3 -> class_7833.field_40718.rotation(v.h);
               default -> throw new MatchException(null, null);
            });
         }

         var1.method_22905(
            var22 == class_2351.field_11048 ? v.Z : v.t, var22 == class_2351.field_11052 ? v.Z : v.t, var22 == class_2351.field_11051 ? v.Z : v.t
         );
         var1.method_22904(-var16, -var18, -var20);
         return true;
      }
   }

   private static void f(
      class_4587 var0, int var1, char var2, PlaceAnimationSupport.TypeP var3, int var4, PlaceAnimationSupport.TypeU var5, class_243 var6, float var7
   ) {
      var0.method_22904(var3.W.field_1352 - var6.field_1352, var3.W.field_1351 - var6.field_1351, var3.W.field_1350 - var6.field_1350);
      PlaceMotion.ensureSourceClassInitialized();
      class_2350 var8 = var3.C.method_10153();
      if (v.u != 0.0F) {
         var0.method_46416(var8.method_10148() * v.u, var8.method_10164() * v.u, var8.method_10165() * v.u);
      }

      class_2351 var9 = var3.C.method_10166();
      if (v.h != 0.0F) {
         var0.method_22907(switch (PlaceAnimationSupport.TypeK.a[var9.ordinal()]) {
            case 1 -> class_7833.field_40714.rotation(v.h);
            case 2 -> class_7833.field_40716.rotation(v.h);
            case 3 -> class_7833.field_40718.rotation(v.h);
            default -> throw new MatchException(null, null);
         });
      }

      float var10 = v.Z * var7;
      float var11 = v.t * var7;
      var0.method_22905(
         var9 == class_2351.field_11048 ? var10 : var11, var9 == class_2351.field_11052 ? var10 : var11, var9 == class_2351.field_11051 ? var10 : var11
      );
      var0.method_22904(var5.Q.method_10263() - var3.W.field_1352, var5.Q.method_10264() - var3.W.field_1351, var5.Q.method_10260() - var3.W.field_1350);
   }

   private static void E(class_4665 var0, class_4588 var1, long var2, List var4, float var5, int var6, int var7, boolean var8) {
      long var9 = 102570561779327L;
      long var11 = 81601942156065L;
      PlaceMotion.ensureSourceClassInitialized();
      float var13 = Math.clamp(var5, 0.0F, 1.0F);

      for (class_10889 var15 : ((Iterable<class_10889>)(Object)var4)) {
         for (class_2350 var19 : J) {
            long var20 = 57964375062897L;
            if (57964375062897L == 0L) {
               boolean var10000 = false;
            } else {
               int var51 = var20 < 0L ? -1 : 1;
            }

            for (class_777 var24 : var15.method_68509(var19)) {
               long var25 = 57964375062896L;
               if (57964375062896L == 0L) {
                  boolean var52 = false;
               } else {
                  int var53 = var25 < 0L ? -1 : 1;
               }

               boolean var27 = n(var9, var24.comp_3724());
               long var28 = 57964375062897L;
               if (57964375062897L == 0L) {
                  boolean var54 = false;
               } else {
                  int var55 = var28 < 0L ? -1 : 1;
               }

               if (var27 == var8) {
                  class_2350 var30 = var24.comp_3723();
                  int var31 = 0;

                  for (int var32 = var8 ? 6 : 4; var31 < var32; var31++) {
                     long var33 = 57964375062897L;
                     if (57964375062897L == 0L) {
                        boolean var56 = false;
                     } else {
                        int var57 = var33 < 0L ? -1 : 1;
                     }

                     int var35 = var8 ? G[var31] : var31;
                     Vector3fc var36 = var24.method_76648(var35);
                     float var37 = Math.clamp(var36.y(), 0.0F, 1.0F);
                     int var38 = e(var7 >> 16, var11, var6 >> 16, var37);
                     int var39 = e(var7 >> 8, var11, var6 >> 8, var37);
                     int var40 = e(var7, var11, var6, var37);
                     class_4588 var41 = var1.method_56824(
                        var0, 0.5F + (var36.x() - 0.5F) * 1.006F, 0.5F + (var36.y() - 0.5F) * 1.006F, 0.5F + (var36.z() - 0.5F) * 1.006F
                     );
                     long var42 = 57964375062897L;
                     if (57964375062897L == 0L) {
                        boolean var58 = false;
                     } else {
                        int var59 = var42 < 0L ? -1 : 1;
                     }

                     if (var8) {
                        var41.method_1336(var38, var39, var40, Math.round(var13 * 255.0F));
                        long var44 = 57964375062897L;
                        if (57964375062897L == 0L) {
                           boolean var60 = false;
                        } else {
                           int var61 = var44 < 0L ? -1 : 1;
                        }
                     } else {
                        float var50 = var13 * 0.95F;
                        long var45 = var24.method_76649(var35);
                        var41.method_1336(
                              Math.min(Math.round(var38 * var50), 255), Math.min(Math.round(var39 * var50), 255), Math.min(Math.round(var40 * var50), 255), 255
                           )
                           .method_22913(class_5611.method_76641(var45), class_5611.method_76642(var45))
                           .method_22922(class_4608.field_21444)
                           .method_60803(15728880)
                           .method_60831(var0, var30.method_10148(), var30.method_10164(), var30.method_10165());
                     }
                  }

                  long var49 = 57964375062897L;
                  if (57964375062897L == 0L) {
                     boolean var62 = false;
                  } else {
                     int var63 = var49 < 0L ? -1 : 1;
                  }
               }
            }

            long var48 = 57964375062896L;
            if (57964375062896L == 0L) {
               boolean var64 = false;
            } else {
               int var65 = var48 < 0L ? -1 : 1;
            }
         }

         long var47 = 57964375062897L;
         if (57964375062897L == 0L) {
            boolean var66 = false;
         } else {
            int var67 = var47 < 0L ? -1 : 1;
         }
      }
   }

   private static boolean n(long var0, class_1058 var2) {
      PlaceMotion.ensureSourceClassInitialized();
      class_7764 var3 = var2.method_45851();
      Boolean var4 = U.get(var3);
      if (var4 == null) {
         var4 = true;

         label95: {
            try {
               int var5 = 0;

               label85:
               while (var5 < var3.method_45815()) {
                  long var6 = 102570561779327L;
                  if (102570561779327L == 0L) {
                     boolean var10000 = false;
                  } else {
                     int var21 = var6 < 0L ? -1 : 1;
                  }

                  for (int var9 = 0; var9 < var3.method_45807(); var9++) {
                     long var10 = 102570561779326L;
                     if (102570561779326L == 0L) {
                        boolean var22 = false;
                     } else {
                        int var23 = var10 < 0L ? -1 : 1;
                     }

                     boolean var13 = var3.method_45810(0, var9, var5);
                     long var14 = 102570561779326L;
                     if (102570561779326L == 0L) {
                        boolean var24 = false;
                     } else {
                        int var25 = var14 < 0L ? -1 : 1;
                     }

                     if (var13) {
                        var4 = false;
                        long var16 = 102570561779326L;
                        if (102570561779326L == 0L) {
                           boolean var26 = false;
                        } else {
                           int var27 = var16 < 0L ? -1 : 1;
                        }
                        break label85;
                     }
                  }

                  var5++;
                  long var20 = 102570561779326L;
                  if (102570561779326L == 0L) {
                     boolean var28 = false;
                  } else {
                     int var29 = var20 < 0L ? -1 : 1;
                  }
               }
            } catch (RuntimeException var18) {
               var4 = false;
               break label95;
            }

            long var19 = 102570561779326L;
            if (102570561779326L == 0L) {
               boolean var30 = false;
            } else {
               int var31 = var19 < 0L ? -1 : 1;
            }
         }

         U.put(var3, var4);
      }

      return var4;
   }

   private static int e(int var0, long var1, int var3, float var4) {
      int var5 = var0 & 0xFF;
      int var6 = var3 & 0xFF;
      int var7 = var6 + Math.round((255 - var6) * 0.15F * var4);
      return Math.round(var5 + (var7 - var5) * var4);
   }

   private static long v(long var0) {
      PlaceMotion.ensureSourceClassInitialized();
      return D != 0L ? D : System.nanoTime();
   }

   private static void a(RuntimeException var0, long var1) {
      PlaceMotion.ensureSourceClassInitialized();
      String var3 = var0.toString();
      long var4 = 66623604736740L;
      if (66623604736740L == 0L) {
         boolean var10000 = false;
      } else {
         int var9 = var4 < 0L ? -1 : 1;
      }

      long var7 = 66623604736740L;
      if (66623604736740L == 0L) {
         boolean var10 = false;
      } else {
         int var11 = var7 < 0L ? -1 : 1;
      }

      if (!var3.equals(I)) {
         I = var3;
         ClientServices.LOGGER.error("Purxiy place animation failed", var0);
      }
   }

   private static void z(char var0, class_310 var1, int var2, class_2338 var3, int var4) {
      PlaceMotion.ensureSourceClassInitialized();
      if (var1.field_1769 instanceof PlaceLevelRendererInvoker var6) {
         var6.purxiy$setBlockDirty(var3, true);
      }
   }

   public static void install(PlaceAnimationModule var0, ModuleContext var1) {
      H = var0;
      T = var1;
   }

   public static void metric(String var0) {
      if (T != null) {
         T.metric(var0, var0.equals("drawn") ? ++c : ++m);
      }
   }

   public static void uninstall() {
      clear();
      Y = null;
      R = null;
      n = null;
      y = null;
      U.clear();
      l.clear();
      P.clear();
      H = null;
      T = null;
   }

private record TypeG(long[] c, class_2680[] l, byte[] p, byte[] i) {
      static final PlaceAnimationSupport.TypeG u = new PlaceAnimationSupport.TypeG(new long[0], new class_2680[0], new byte[0], new byte[0]);

      public long[] positions() {
         return this.c;
      }

      public class_2680[] replacements() {
         return this.l;
      }

      public byte[] sky() {
         return this.p;
      }

      public byte[] block() {
         return this.i;
      }

      public long[] longArrayState7() {
         return this.c;
      }

      public class_2680[] minecraftObjectArrayState1() {
         return this.l;
      }

      public byte[] byteArrayState11() {
         return this.p;
      }

      public byte[] byteArrayState12() {
         return this.i;
      }
   }

   static class TypeK {
      static final int[] A;
      static final int[] a = new int[class_2351.values().length];

      static {
         try {
            a[class_2351.field_11048.ordinal()] = 1;
         } catch (NoSuchFieldError var9) {
         }

         try {
            a[class_2351.field_11052.ordinal()] = 2;
         } catch (NoSuchFieldError var8) {
         }

         try {
            a[class_2351.field_11051.ordinal()] = 3;
         } catch (NoSuchFieldError var7) {
         }

         A = new int[class_2350.values().length];

         try {
            A[class_2350.field_11033.ordinal()] = 1;
         } catch (NoSuchFieldError var6) {
         }

         try {
            A[class_2350.field_11036.ordinal()] = 2;
         } catch (NoSuchFieldError var5) {
         }

         try {
            A[class_2350.field_11043.ordinal()] = 3;
         } catch (NoSuchFieldError var4) {
         }

         try {
            A[class_2350.field_11035.ordinal()] = 4;
         } catch (NoSuchFieldError var3) {
         }

         try {
            A[class_2350.field_11039.ordinal()] = 5;
         } catch (NoSuchFieldError var2) {
         }

         try {
            A[class_2350.field_11034.ordinal()] = 6;
         } catch (NoSuchFieldError var1) {
         }
      }
   }

   private static enum TypeO {
      s,
      i;

}

   private static final class TypeP {
      final List<PlaceAnimationSupport.TypeU> V;
      final class_243 W;
      final class_2350 C;
      final PlaceMotion.Style R;
      final float L;
      final float N;
      final boolean T;
      final long B;
      PlaceAnimationSupport.TypeO n = PlaceAnimationSupport.TypeO.s;
      long j;

      TypeP(List<PlaceAnimationSupport.TypeU> var1, class_243 var2, class_2350 var3, PlaceMotion.Style var4, float var5, float var6, boolean var7, long var8) {
         this.V = new ArrayList<>(var1);
         this.W = var2;
         this.C = var3;
         this.R = var4;
         this.L = var5;
         this.N = Math.max(var6, 0.05F);
         this.T = var7;
         this.B = var8;
      }

      float age(long var1) {
         return PlaceAnimationSupport.C >= 0.0F ? PlaceAnimationSupport.C : (float)(var1 - this.B) / 1.0E9F / this.N;
      }
   }

   private record TypeR(class_2338 w, class_2680 Y) {
      public class_2338 pos() {
         return this.w;
      }

      public class_2680 state() {
         return this.Y;
      }

      public class_2338 minecraftObjectState6() {
         return this.w;
      }

      public class_2680 minecraftObjectState5() {
         return this.Y;
      }
   }

   private static final class TypeU {
      final class_2338 Q;
      class_2680 j;
      class_2680 g;
      final PlaceAnimationSupport2 c;
      boolean T;
      boolean r;
      
      private List<class_10889> y;
      
      private Boolean I;
      boolean M;
      boolean t;
      int x;

      private static final String[] b = new String[2];

      TypeU(class_2338 var1, class_2680 var2, class_638 var3) {
         this.Q = var1;
         this.c = new PlaceAnimationSupport2(var1, var2, var3);
         this.restate(var2);
      }

      boolean restate(class_2680 var1) {
         this.j = var1;
         this.c.field_62247 = var1;
         this.T = var1.method_26217() == class_2464.field_11458;
         this.r = var1.method_31709();
         this.y = null;
         this.I = null;
         class_2680 var2 = this.g;
         this.g = var1.method_26227().method_15759();
         return var2 != this.g;
      }

      boolean seeThrough(List<class_10889> var1) {
         long var2 = 112028102552230L;
         long var4 = var2 ^ 62302840151257L;
         if (this.I == null) {
            boolean var6 = false;

            for (class_10889 var8 : var1) {
               for (class_2350 var12 : PlaceAnimationSupport.J) {
                  for (class_777 var14 : var8.method_68509(var12)) {
                     var6 |= !PlaceAnimationSupport.n(var4, var14.comp_3724());
                  }
               }
            }

            this.I = var6;
         }

         return this.I;
      }

      List<class_10889> parts(class_310 var1) {
         if (this.y == null) {
            this.y = var1.method_1541().method_3349(this.j).method_68512(class_5819.method_43049(this.j.method_26190(this.Q)));
         }

         return this.y;
      }

      boolean in(int var1, int var2, int var3) {
         return class_4076.method_18675(this.Q.method_10263()) == var1
            && class_4076.method_18675(this.Q.method_10264()) == var2
            && class_4076.method_18675(this.Q.method_10260()) == var3;
      }

      @Override
      public String toString() {
         return this.Q.method_23854() + " " + this.j.method_26204().method_9518().getString() + (this.M ? " copied" : "") + (this.t ? " meshed" : "");
      }

}
}
