package dev.purxiy.modules.knife;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class KnifeGestureRenderer {
   public static final int i = 14;
   public static final int t = 0;
   public static final int A = 1;
   public static final int D = 2;
   static final int L = 240;
   private static final int w = 4;
   private static final float U = 0.05F;
   private static final float c = 0.14F;
   public static final KnifeGestureRenderer.Data M = new KnifeGestureRenderer.Data(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
   private static final int v = 0;
   private static final int q = 7;
   private static final int k = 14;
   private static final int B = 15;
   private static final int O = 22;
   private static final int Z = 24;
   private static final int r = 25;
   static final String[] y = new String[]{"karambit", "butterfly", "m9", "tactical", "crystal"};
   private static List<List<KnifeGestureRenderer.GestureTrick>> P;
   private static List<List<KnifeGestureRenderer.GestureTrick>> u;
   private static List<List<KnifeGestureRenderer.GestureTrick>> f;
   private static KnifeGestureRenderer.GestureTrick[] H;
   private static final List<String> n = List.of("draw", "inspect", "walk", "fidget");
   private static final String[] p = new String[]{"hand", "knife", "handles", "spin", "off"};
   private static final int[] I = new int[]{6, 6, 2, 2, 7};
   private static final String[][] Y = new String[][]{
      {"x", "y", "z", "twist", "cock", "flex"}, {"x", "y", "z", "pitch", "yaw", "roll", "a", "b"}, {"x", "y", "z", "twist", "cock", "flex"}
   };
   private static final String[][] b = new String[][]{
      {"x", "y", "z", "twist", "cock", "flex"},
      {"x", "y", "z", "pitch", "yaw", "roll"},
      {"a", "b"},
      {"drive", "wobble"},
      {"x", "y", "z", "twist", "cock", "flex", "in"}
   };
   private static final int m = 0;
   private static final int g = 6;
   private static final int C = 14;
   private static final int R = 20;
   private static final int x = 21;
   private static final int s = 23;
   private static final int[][] e = new int[][]{{0, 6}, {6, 14}, {14, 20}};
   static float V = 90.0F;
   static float W = 0.0F;
   static float G = 0.22F;
   private static final float h = 0.12F;
   private static final String[] d = new String[84];
   private static final long[] j = new long[]{
      1066266840650185317L,
      -8090992823233778799L,
      2237813964980776106L,
      3249262387659289286L,
      -7500047450196204513L,
      7413634086977106118L,
      6551843042452603241L,
      1213597457313182776L,
      5245481804350644479L,
      1687217229126780447L,
      997955354408586960L,
      -1825763144033947282L,
      -6974442461898152262L,
      2020079716528535192L,
      -7321053292077445559L,
      4004228282439186358L,
      2421966706509989649L,
      552686775379417965L,
      -5886720396845876905L,
      781379102230478672L,
      -8490786775522935540L,
      -6703211948582882787L,
      -7514638452640277405L,
      4188534600747889124L,
      3963723144887586516L,
      6056765706266638622L,
      -8563792781410476247L,
      -5280736103293589448L,
      -3616073731603180742L,
      -9171209959705012565L,
      5957352320900953567L,
      6100685884028304053L,
      -4389609517565397765L,
      -4216572357231722342L,
      -6974821156457661770L,
      6373976577146612794L,
      6129431987516650174L,
      -2988370844620418241L,
      -8168724555857209475L,
      -936002741983633383L,
      -492570799784445462L,
      1878075168309173781L
   };

   private KnifeGestureRenderer() {
   }

   public static List<KnifeGestureRenderer.GestureTrick> inspects(int var0) {
      return P.get(var0);
   }

   public static List<KnifeGestureRenderer.GestureTrick> fidgets(int var0) {
      return u.get(var0);
   }

   public static List<KnifeGestureRenderer.GestureTrick> walks(int var0) {
      return f.get(var0);
   }

   public static KnifeGestureRenderer.GestureTrick draw(int var0) {
      return H[var0];
   }

   public static KnifeGestureRenderer.GestureTrick inspect(int var0) {
      return P.get(var0).getFirst();
   }

   public static List<KnifeGestureRenderer.GestureTrick> all() {
      ArrayList<KnifeGestureRenderer.GestureTrick> var0 = new ArrayList();

      for (int var1 = 0; var1 < y.length; var1++) {
         var0.add(H[var1]);
         var0.addAll(P.get(var1));
         var0.addAll(f.get(var1));
         var0.addAll(u.get(var1));
      }

      return var0;
   }

   public static float ease(float var0) {
      return var0 * var0 * var0 * (var0 * (var0 * 6.0F - 15.0F) + 10.0F);
   }

   public static void load(String var0) {
      long var1 = 42084472905600L;
      long var3 = var1 ^ 103131916678430L;
      long var5 = var1 ^ 33397624052943L;
      List<KnifeGestureRenderer.GestureTrack>var7 = H(var0, var3);
      ArrayList var8 = new ArrayList();
      ArrayList var9 = new ArrayList();
      ArrayList var10 = new ArrayList();
      KnifeGestureRenderer.GestureTrick[] var11 = new KnifeGestureRenderer.GestureTrick[y.length];

      for (int var12 = 0; var12 < y.length; var12++) {
         var8.add(new ArrayList());
         var9.add(new ArrayList());
         var10.add(new ArrayList());
      }

      for (KnifeGestureRenderer.GestureTrack var13 : var7) {
         for (int var14 = 0; var14 < y.length; var14++) {
            if (var13.U == var14 || var13.U < 0) {
               KnifeGestureRenderer.GestureTrack var15 = var13.U < 0 ? var13.forKnife(var14) : var13;
               KnifeGestureRenderer.GestureTrick var16 = d(var5, var15);
               String var17 = var15.v;
               switch (var17) {
                  case "draw":
                     if (var11[var14] != null) {
                        throw new IllegalArgumentException("Two draws for " + y[var14]);
                     }

                     var11[var14] = var16;
                     break;
                  case "inspect":
                     ((List)var8.get(var14)).add(var16);
                     break;
                  case "walk":
                     ((List<KnifeGestureRenderer.GestureTrick>)var10.get(var14)).add(var16);
                     break;
                  default:
                     ((List)var9.get(var14)).add(var16);
               }
            }
         }
      }

      for (int var20 = 0; var20 < y.length; var20++) {
         if (var11[var20] == null || ((List)var8.get(var20)).isEmpty()) {
            throw new IllegalArgumentException("Missing a draw or an inspect for " + y[var20]);
         }

         var8.set(var20, List.copyOf((Collection)var8.get(var20)));
         var9.set(var20, List.copyOf((Collection)var9.get(var20)));
         var10.set(var20, List.copyOf((Collection)var10.get(var20)));
      }

      P = List.copyOf(var8);
      u = List.copyOf(var9);
      f = List.copyOf(var10);
      H = var11;
   }

   private static List H(String var0, long var1) {
      long var3 = 98427380277820L;
      long var5 = 112718675887863L;
      long var7 = 66002259784658L;
      long var9 = 16957573068167L;
      ArrayList var11 = new ArrayList();
      KnifeGestureRenderer.GestureTrack var12 = null;
      int var13 = -1;
      String var14 = null;
      int var15 = 0;
      HashMap var16 = new HashMap();
      HashMap var17 = new HashMap();
      StringBuilder var18 = new StringBuilder();
      String[] var19 = var0.split("\n");
      int var20 = var19.length;
      int var21 = 0;

      while (var21 < var20) {
         String var22 = var19[var21];
         var15++;
         int var23 = var22.indexOf(35);
         String var24 = (var23 >= 0 ? var22.substring(0, var23) : var22).strip();
         long var25 = 135836046518941L;
         int var59 = var25 < 0L ? -1 : 1;
         if (var24.isEmpty()) {
            long var28 = 135836046518941L;
            int var601 = var28 < 0L ? -1 : 1;
         } else {
            long var58 = 135836046518942L;
            int var60 = var58 < 0L ? -1 : 1;
            if (var24.startsWith("[")) {
               boolean var30 = var24.endsWith("]");
               long var31 = 135836046518942L;
               int var641 = var31 < 0L ? -1 : 1;
               if (!var30) {
                  throw new IllegalArgumentException("A knife trick header needs its ] on line " + var15 + ": " + var24);
               }

               var12 = new KnifeGestureRenderer.GestureTrack(var24.substring(1, var24.length() - 1).trim());
               var12.z.append((CharSequence)var18);
               var11.add(var12);
               var17 = new HashMap(var16);
               var13 = -1;
               var14 = null;
            } else {
               String[] var60x = var24.split("\\s+");
               String var61 = var60x[0].toLowerCase(Locale.ROOT);
               int var32 = var61.equals("pose") ? 1 : 0;
               long var33 = 135836046518941L;
               int var63 = var33 < 0L ? -1 : 1;
               label256:
               if (var32 == 0) {
                  long var63x = 135836046518941L;
                  int var68 = var63x < 0L ? -1 : 1;
                  if (var12 == null) {
                     throw new IllegalArgumentException("A knife key before any trick on line " + var15 + ": " + var24);
                  }

                  var12.z.append(var24).append('\n');
                  byte var64 = 0;
                  int var65 = var61.charAt(0);
                  long var39 = 135836046518942L;
                  int var41 = var39 < 0L ? -1 : 1;
                  if (Character.isLetter((char)var65)) {
                     var41 = List.of(p).indexOf(var61);
                     int var70 = var61.equals("spring") ? 1 : 0;
                     long var73 = 135836046518941L;
                     int var89 = var73 < 0L ? -1 : 1;
                     if (var70 != 0) {
                        t(var12, var3, var60x);
                        long var82 = 135836046518942L;
                        int var103 = var82 < 0L ? -1 : 1;
                        break label256;
                     }

                     long var80 = 135836046518942L;
                     int var96 = var80 < 0L ? -1 : 1;
                     if (var41 >= 0) {
                        var13 = var41;
                        var14 = null;
                     } else {
                        long var83 = 135836046518942L;
                        int var27x = var83 < 0L ? -1 : 1;
                        var70 = -884157189;
                        var27x = 884156325;
                        if (!List.of("hold", "sound", "kick").contains(var61)) {
                           throw var12.error("Unknown knife track '" + var60x[0] + "' on line " + var15);
                        }

                        var13 = -1;
                        var14 = var61;
                        long var49 = 135836046518942L;
                        int var52 = var49 < 0L ? -1 : 1;
                     }

                     var64 = 1;
                     long var84 = 135836046518941L;
                     int var108 = var84 < 0L ? -1 : 1;
                     if (var60x.length == 1) {
                        long var86 = 135836046518941L;
                        int var50 = var86 < 0L ? -1 : 1;
                        break label256;
                     }
                  }

                  String[] var69 = Arrays.copyOfRange(var60x, var64, var60x.length);

                  try {
                     long var72 = 135836046518941L;
                     int var76 = var72 < 0L ? -1 : 1;
                     if (var13 >= 0) {
                        var12.N[var13].add(i(var69, var13, var17, var12, var15, var7), var12, var15);
                        long var75 = 135836046518942L;
                        int var94 = var75 < 0L ? -1 : 1;
                     } else {
                        var65 = -884157195;
                        var76 = -47060;
                        long var81 = 135836046518941L;
                        int var55 = var81 < 0L ? -1 : 1;
                        var55 = -884198220 - var76;
                        var76 = -884151650;
                        if ("hold".equals(var14)) {
                           var12.F.add(F(var69, var12, var9, var15));
                           long var48 = 135836046518941L;
                           int var110 = var48 < 0L ? -1 : 1;
                        } else {
                           var65 = -884157293;
                           var55 = 884168620;
                           long var85 = 135836046518942L;
                           int var111 = var85 < 0L ? -1 : 1;
                           var76 = -884151650;
                           if ("sound".equals(var14)) {
                              var12.t
                                 .add(
                                    new KnifeGestureRenderer.GestureSound(
                                       Float.parseFloat(var69[0]),
                                       var69[1].toLowerCase(Locale.ROOT),
                                       var69.length > 2 ? Float.parseFloat(var69[2]) : 1.0F,
                                       var69.length > 3 ? Float.parseFloat(var69[3]) : 1.0F
                                    )
                                 );
                              long var50 = 135836046518941L;
                              int var114 = var50 < 0L ? -1 : 1;
                           } else {
                              var65 = -884157203;
                              long var87 = 135836046518942L;
                              int var115 = var87 < 0L ? -1 : 1;
                              var55 = -884177991;
                              var76 = -884151650;
                              if (!"kick".equals(var14)) {
                                 throw var12.error("Knife keys outside a track on line " + var15);
                              }

                              var12.L.add(C(var69, var12, var15, var5));
                              long var52 = 135836046518941L;
                              int var53 = var52 < 0L ? -1 : 1;
                           }
                        }
                     }
                  } catch (NumberFormatException | ArrayIndexOutOfBoundsException var541) {
                     throw var12.error("Unreadable knife key on line " + var15 + ": " + var24);
                  }
               } else {
                  long var35 = 135836046518941L;
                  int var37 = var35 < 0L ? -1 : 1;
                  var37 = var60x.length > 3 ? List.of(p).indexOf(var60x[1].toLowerCase(Locale.ROOT)) : -1;
                  long var38 = 135836046518942L;
                  int var40 = var38 < 0L ? -1 : 1;
                  if (var37 < 0) {
                     throw new IllegalArgumentException("A pose is: pose <track> <name> and a value per channel, on line " + var15);
                  }

                  var32 = var60x.length;
                  long var40x = 135836046518941L;
                  int var54 = var40x < 0L ? -1 : 1;
                  if (var32 != I[var37] + 3) {
                     throw new IllegalArgumentException("A pose is: pose <track> <name> and a value per channel, on line " + var15);
                  }

                  float[] var42 = new float[I[var37]];
                  int var43 = 0;

                  while (var43 < var42.length) {
                     long var44 = 135836046518941L;
                     int var46 = var44 < 0L ? -1 : 1;
                     var42[var43] = Float.parseFloat(var60x[var43 + 3]);
                     var43++;
                     long var46x = 135836046518942L;
                     int var85 = var46x < 0L ? -1 : 1;
                  }

                  String var74 = p[var37] + "@" + var60x[2].toLowerCase(Locale.ROOT);
                  var16.put(var74, var42);
                  long var45 = 135836046518941L;
                  int var55 = var45 < 0L ? -1 : 1;
                  var17.put(var74, var42);
                  var18.append(var24).append('\n');
                  if (var12 != null) {
                     var12.z.append(var24).append('\n');
                     long var47 = 135836046518942L;
                     int var86 = var47 < 0L ? -1 : 1;
                  }
               }
            }
         }

         var21++;
         long var59x = 135836046518942L;
         int var611 = var59x < 0L ? -1 : 1;
      }

      return var11;
   }

   private static String[] i(String[] var0, int var1, Map var2, KnifeGestureRenderer.GestureTrack var3, int var4, long var5) {
      long var7 = 66002259784657L;
      int var10 = var7 < 0L ? -1 : 1;
      if (var0.length >= 2) {
         long var10x = 66002259784657L;
         int var27 = var10x < 0L ? -1 : 1;
         if (!var0[1].startsWith("@")) {
            long var12 = 66002259784658L;
            int var14 = var12 < 0L ? -1 : 1;
            if (!var0[1].equals("=")) {
               return var0;
            }
         }

         long var27x = 66002259784657L;
         int var291 = var27x < 0L ? -1 : 1;
         float[] var14;
         if (var0[1].equals("=")) {
            List var15 = var3.N[var1].o;
            long var16 = 66002259784657L;
            int var35 = var16 < 0L ? -1 : 1;
            if (var15.isEmpty()) {
               throw var3.error("A key like the one before it needs one before it, on line " + var4);
            }

            var14 = Arrays.copyOfRange((float[])var15.getLast(), 1, I[var1] + 1);
            long var18 = 66002259784657L;
            int var38 = var18 < 0L ? -1 : 1;
         } else {
            String var28 = var0[1].substring(1).toLowerCase(Locale.ROOT);
            float[] var30 = (float[])var2.get(p[var1] + "@" + var28);
            long var17 = 66002259784658L;
            int var37 = var17 < 0L ? -1 : 1;
            if (var30 == null) {
               boolean var19 = p[var1].equals("off");
               long var20 = 66002259784658L;
               int var40 = var20 < 0L ? -1 : 1;
               if (var19) {
                  long var22 = 66002259784658L;
                  int var25 = var22 < 0L ? -1 : 1;
                  if (var2.containsKey("hand@" + var28)) {
                     var30 = Arrays.copyOf((float[])var2.get("hand@" + var28), I[var1]);
                     var30[I[var1] - 1] = 1.0F;
                  }
               }
            }

            long var36 = 66002259784658L;
            int var21 = var36 < 0L ? -1 : 1;
            if (var30 == null) {
               String var39 = p[var1];
               String var41 = var0[1];
               throw var3.error("Unknown " + var39 + " pose '" + var41 + "' on line " + var4);
            }

            var14 = (float[])var30.clone();
         }

         int var29x = 2;

         while (var29x < var0.length) {
            long var31 = 66002259784657L;
            int var381 = var31 < 0L ? -1 : 1;
            String[] var34 = var0[var29x].split("=");
            long var37x = 66002259784658L;
            int var21 = var37x < 0L ? -1 : 1;
            var21 = var34.length == 2 ? List.of(b[var1]).indexOf(var34[0].toLowerCase(Locale.ROOT)) : -1;
            long var40 = 66002259784657L;
            int var54 = var40 < 0L ? -1 : 1;
            if (var21 < 0) {
               throw var3.error("Unknown override '" + var0[var29x] + "' on line " + var4);
            }

            var14[var21] = Float.parseFloat(var34[1]);
            var29x++;
            long var25 = 66002259784657L;
            int var25x = var25 < 0L ? -1 : 1;
         }

         String[] var32 = new String[var14.length + 1];
         var32[0] = var0[0];
         int var33 = 0;

         while (var33 < var14.length) {
            long var35 = 66002259784657L;
            int var45 = var35 < 0L ? -1 : 1;
            var32[var33 + 1] = Float.toString(var14[var33]);
            var33++;
            long var38 = 66002259784657L;
            int var52 = var38 < 0L ? -1 : 1;
         }

         return var32;
      } else {
         return var0;
      }
   }

   private static void t(KnifeGestureRenderer.GestureTrack var0, long var1, String[] var3) {
      int var4 = 385250348;
      int var5 = List.of("hand", "knife", "off").indexOf(var3[1].toLowerCase(Locale.ROOT));
      long var6 = 98427380277820L;
      int var8 = var6 < 0L ? -1 : 1;
      if (var5 >= 0 && var3.length >= 4) {
         var0.M[var5 * 2] = Float.parseFloat(var3[2]);
         var0.M[var5 * 2 + 1] = Float.parseFloat(var3[3]);
      } else {
         throw var0.error("A spring is: spring hand|knife|off hz damping");
      }
   }

   private static KnifeGestureRenderer.GestureSegment F(String[] var0, KnifeGestureRenderer.GestureTrack var1, long var2, int var4) {
      int var5 = 1042172894;
      float var6 = Float.parseFloat(var0[0]);
      int var7 = -1042169611;
      int var8 = List.of("main", "off", "air").indexOf(var0[1].toLowerCase(Locale.ROOT));
      if (var8 < 0) {
         throw var1.error("A hold is main, off or air, on line " + var4);
      } else {
         float var9 = 0.0F;
         float var10 = 20.0F;
         byte var11 = 2;
         long var12 = 16957573068166L;
         int var14 = var12 < 0L ? -1 : 1;
         if (var8 == 2) {
            long var14x = 16957573068167L;
            int var16 = var14x < 0L ? -1 : 1;
            var9 = var0.length > 2 ? Float.parseFloat(var0[2]) : 1.0F;
            var5 = var0.length;
            long var16x = 16957573068167L;
            int var18 = var16x < 0L ? -1 : 1;
            var11 = (byte)(var5 > 3 ? "xyz".indexOf(var0[3].toLowerCase(Locale.ROOT)) : 2);
            long var18x = 16957573068166L;
            int var20 = var18x < 0L ? -1 : 1;
            if (var11 < 0) {
               throw var1.error("A toss turns about the knife's x, y or z, on line " + var4);
            }

            long var20x = 16957573068166L;
            int var22 = var20x < 0L ? -1 : 1;
            var10 = var0.length > 4 ? Float.parseFloat(var0[4]) : var10;
         }

         return new KnifeGestureRenderer.GestureSegment(var6, var8, var9, var11, var10);
      }
   }

   private static KnifeGestureRenderer.GestureChannel C(String[] var0, KnifeGestureRenderer.GestureTrack var1, int var2, long var3) {
      int var5 = -1554181913;
      int var6 = List.of("hand", "knife", "off").indexOf(var0[1].toLowerCase(Locale.ROOT));
      long var7 = 112718675887863L;
      int var9 = var7 < 0L ? -1 : 1;
      if (var6 < 0) {
         throw var1.error("A kick pushes the hand, the knife or the off hand, on line " + var2);
      } else {
         var9 = List.of(Y[var6]).indexOf(var0[2].toLowerCase(Locale.ROOT));
         if (var9 < 0) {
            throw var1.error("Unknown channel '" + var0[2] + "' for a kick on line " + var2);
         } else {
            return new KnifeGestureRenderer.GestureChannel(Float.parseFloat(var0[0]), var6, var9, Float.parseFloat(var0[3]));
         }
      }
   }

   private static KnifeGestureRenderer.GestureTrick d(long var0, KnifeGestureRenderer.GestureTrack var2) {
      long var3 = 131198733642413L;
      short var5 = 30547;
      short var6 = 5609;
      int var7 = (int)(var3 << 48 >>> 48);
      long var8 = 80838359906136L;
      long var10 = 136287332365601L;
      long var12 = 87260279445843L;
      long var14 = 19613954193065L;
      long var16 = 43589597932906L;
      long var18 = 70623759842839L;
      long var20 = 47678772338330L;
      float var22 = var2.duration();
      if (var22 < 0.2F) {
         throw var2.error("A knife trick lasts a moment at least");
      } else {
         ArrayList var23;
         label219: {
            var23 = new ArrayList<>(var2.F);
            long var24 = 61681963778894L;
            int var59 = var24 < 0L ? -1 : 1;
            var23.sort(Comparator.comparingDouble(KnifeGestureRenderer.GestureSegment::time));
            if (!var23.isEmpty()) {
               float var27;
               int var28 = (var27 = ((KnifeGestureRenderer.GestureSegment)var23.getFirst()).F - 0.0F) == 0.0F ? 0 : (var27 < 0.0F ? -1 : 1);
               long var29 = 61681963778894L;
               int var65 = var29 < 0L ? -1 : 1;
               if (var28 <= 0) {
                  break label219;
               }
            }

            var23.addFirst(new KnifeGestureRenderer.GestureSegment(0.0F, 0, 0.0F, 2, 0.0F));
         }

         var2.S = var23;
         int var59 = 0;

         while (var59 < var23.size()) {
            KnifeGestureRenderer.GestureSegment var25 = (KnifeGestureRenderer.GestureSegment)var23.get(var59);
            long var26 = 61681963778894L;
            int var70 = var26 < 0L ? -1 : 1;
            long var67 = 61681963778895L;
            int var68 = var67 < 0L ? -1 : 1;
            if (var25.y == 2) {
               long var32x = 61681963778894L;
               int var69 = var32x < 0L ? -1 : 1;
               if (var59 == 0) {
                  throw var2.error("A tossed knife is thrown from a hand and caught by one");
               }

               long var35 = 61681963778895L;
               int var33 = var35 < 0L ? -1 : 1;
               if (var59 + 1 >= var23.size()) {
                  throw var2.error("A tossed knife is thrown from a hand and caught by one");
               }

               long var38 = 61681963778894L;
               int var38x = var38 < 0L ? -1 : 1;
               if (((KnifeGestureRenderer.GestureSegment)var23.get(var59 + 1)).y == 2) {
                  throw var2.error("A tossed knife is thrown from a hand and caught by one");
               }

               KnifeGestureRenderer.GestureSegment var41 = (KnifeGestureRenderer.GestureSegment)var23.get(var59 + 1);
               long var42 = 61681963778895L;
               int var41x = var42 < 0L ? -1 : 1;
               if (var41.F - var25.F < 0.12F) {
                  throw var2.error("A toss lasts a moment at least");
               }
            }

            long var70x = 61681963778894L;
            int var77 = var70x < 0L ? -1 : 1;
            if (var25.y == 1) {
               long var73 = 61681963778894L;
               int var841 = var73 < 0L ? -1 : 1;
               if (!var2.i) {
                  throw var2.error("A trick where your other hand holds the knife is marked | twohand");
               }
            }

            var59++;
            long var74 = 61681963778894L;
            int var85 = var74 < 0L ? -1 : 1;
         }

         int var60 = (int)Math.ceil(var22 * 240.0F) + 1;
         float[][] var61 = L(var2, var60, var22, var10, List.of());
         KnifeGestureRenderer.GestureFrame[] var63 = O(var2, var5, var6, var23, var61, var60, var7);
         ArrayList var65 = new ArrayList();
         KnifeGestureRenderer.GestureFrame[] var66 = var63;
         int var68x = var63.length;
         int var30 = 0;

         while (var30 < var68x) {
            KnifeGestureRenderer.GestureFrame var31 = var66[var30];
            long var71 = 61681963778894L;
            int var34 = var71 < 0L ? -1 : 1;
            var31.knock(var61, var65);
            var30++;
            long var75 = 61681963778894L;
            int var91 = var75 < 0L ? -1 : 1;
         }

         var61 = L(var2, var60, var22, var10, var65);
         float[] var69x = new float[var60 * 25];
         Matrix4f[] var72 = new Matrix4f[var60];
         Matrix4f[] var33x = new Matrix4f[var60];
         Matrix4f[] var34x = new Matrix4f[var60];
         R(var2, var23, var61, var8, var60, var72, var33x, var34x);
         var63 = O(var2, var5, var6, var23, var61, var60, var7);
         KnifeGestureRenderer.GestureFrame[] var76x = var63;
         int var36x = var63.length;
         int var37 = 0;

         while (var37 < var36x) {
            KnifeGestureRenderer.GestureFrame var77 = var76x[var37];
            var77.fly(var2, var61, var34x);
            var37++;
            long var39 = 61681963778894L;
            int var82 = var39 < 0L ? -1 : 1;
         }

         Quaternionf var78 = new Quaternionf();
         Vector3f var79 = new Vector3f();

         for (int var40 = 0; var40 < var60; var40++) {
            long var81 = 61681963778895L;
            int var44 = var81 < 0L ? -1 : 1;
            var44 = var40 * 25;
            P(var69x, var44 + 0, var12, var72[var40], var79, var78);
            P(var69x, var44 + 7, var12, var33x[var40], var79, var78);
            long var45 = 61681963778895L;
            int var46 = var45 < 0L ? -1 : 1;
            P(var69x, var44 + 15, var12, var34x[var40], var79, var78);
            var69x[var44 + 14] = Math.clamp(var61[var40][20], 0.0F, 1.0F);
            var69x[var44 + 22] = var61[var40][12];
            var69x[var44 + 22 + 1] = var61[var40][13];
            var69x[var44 + 24] = a(var23, var18, var40 / 240.0F);
         }

         int[] var80 = new int[]{0, 7, 15};
         int var82 = var80.length;
         int var83 = 0;

         while (var83 < var82) {
            int var43 = var80[var83];
            int var85 = 1;

            while (var85 < var60) {
               int var87 = (var85 - 1) * 25 + var43;
               long var46 = 61681963778894L;
               int var49 = var46 < 0L ? -1 : 1;
               var49 = var87 + 3;
               int var50 = var85 * 25 + var43 + 3;
               float var51 = var69x[var49] * var69x[var50]
                  + var69x[var49 + 1] * var69x[var50 + 1]
                  + var69x[var49 + 2] * var69x[var50 + 2]
                  + var69x[var49 + 3] * var69x[var50 + 3];
               long var52 = 61681963778894L;
               int var54 = var52 < 0L ? -1 : 1;
               if (var51 < 0.0F) {
                  var54 = 0;

                  while (var54 < 4) {
                     var69x[var50 + var54] = -var69x[var50 + var54];
                     long var55 = 61681963778895L;
                     int var59x = var55 < 0L ? -1 : 1;
                     var54++;
                     long var57x = 61681963778895L;
                     int var61x = var57x < 0L ? -1 : 1;
                  }
               }

               var85++;
               long var89 = 61681963778895L;
               int var58 = var89 < 0L ? -1 : 1;
            }

            var83++;
            long var88 = 61681963778894L;
            int var50 = var88 < 0L ? -1 : 1;
         }

         ArrayList<GestureSound> var84= new ArrayList<>(var2.t);
         E(var16, var2, var23, var61, var34x, var63, var60, var84);
         var84.sort(Comparator.comparingDouble(KnifeGestureRenderer.GestureSound::time));
         float[] var86 = U(var2, var23, var69x, var60, var22, var14);
         return new KnifeGestureRenderer.GestureTrick(
            var2.U,
            var2.v,
            var2.P,
            var20,
            var2.i,
            var2.X,
            var2.J,
            var86[0],
            var86[1],
            var22,
            var69x,
            var84.toArray(x$0 -> new KnifeGestureRenderer.GestureSound[x$0]),
            var2.z.toString()
         );
      }
   }

   private static float[] U(KnifeGestureRenderer.GestureTrack var0, List<GestureSegment> var1, float[] var2, int var3, float var4, long var5) {
      KnifeGestureRenderer.GesturePose var7 = new KnifeGestureRenderer.GesturePose().rest(Math.max(0, var0.U));
      Vector3f var8 = var7.N.getTranslation(new Vector3f());
      Quaternionf var9 = var7.N.getNormalizedRotation(new Quaternionf());
      Matrix4f var10 = new Matrix4f(var7.N).invertAffine().mul(var7.r);
      Vector3f var11 = var10.getTranslation(new Vector3f());
      Quaternionf var12 = var10.getNormalizedRotation(new Quaternionf());
      float[] var13 = new float[var3];
      float var14 = 1.0E-6F;
      Matrix4f var15 = new Matrix4f();
      Matrix4f var16 = new Matrix4f();

      for (int var17 = 0; var17 < var3; var17++) {
         long var18 = 19613954193064L;
         int var21 = var18 < 0L ? -1 : 1;
         var21 = var17 * 25;
         var15.translationRotate(
            var2[var21 + 0],
            var2[var21 + 0 + 1],
            var2[var21 + 0 + 2],
            new Quaternionf(var2[var21 + 0 + 3], var2[var21 + 0 + 4], var2[var21 + 0 + 5], var2[var21 + 0 + 6])
         );
         var16.translationRotate(
            var2[var21 + 15],
            var2[var21 + 15 + 1],
            var2[var21 + 15 + 2],
            new Quaternionf(var2[var21 + 15 + 3], var2[var21 + 15 + 4], var2[var21 + 15 + 5], var2[var21 + 15 + 6])
         );
         long var22 = 19613954193064L;
         int var44 = var22 < 0L ? -1 : 1;
         Matrix4f var25 = new Matrix4f(var15).invertAffine().mul(var16);
         var13[var17] = var15.getTranslation(new Vector3f()).distance(var8)
            + 0.05F * N(var15.getNormalizedRotation(new Quaternionf()), var9)
            + var25.getTranslation(new Vector3f()).distance(var11)
            + 0.03F * N(var25.getNormalizedRotation(new Quaternionf()), var12);
         var14 = Math.max(var14, var13[var17]);
      }

      int var35 = 0;
      int var36 = var3 - 1;

      while (var35 < var3 - 1) {
         float var19 = var13[var35];
         long var20 = 19613954193064L;
         int var42 = var20 < 0L ? -1 : 1;
         float var23;
         int var24 = (var23 = var19 - 0.25F * var14) == 0.0F ? 0 : (var23 < 0.0F ? -1 : 1);
         long var47 = 19613954193064L;
         int var51 = var47 < 0L ? -1 : 1;
         if (var24 >= 0) {
            break;
         }

         var35++;
         long var28 = 19613954193065L;
         int var28x = var28 < 0L ? -1 : 1;
      }

      while (var36 > 0) {
         float var37 = var13[var36];
         long var39 = 19613954193064L;
         int var451 = var39 < 0L ? -1 : 1;
         long var43 = 19613954193064L;
         int var511 = var43 < 0L ? -1 : 1;
         if (!(var37 < 0.4F * var14)) {
            break;
         }

         var36--;
         long var48 = 19613954193064L;
         int var57 = var48 < 0L ? -1 : 1;
      }

      float var38 = var35 / 240.0F;
      float var40 = var36 / 240.0F;
      int[] var41 = new int[]{1, 2};
      int var42 = var41.length;
      int var44 = 0;

      while (var44 < var42) {
         int var45 = var41[var44];
         float[] var49 = var0.N[var45].still();
         long var26 = 19613954193064L;
         int var60 = var26 < 0L ? -1 : 1;
         if (var49 != null) {
            var38 = Math.min(var38, var49[0]);
            var40 = Math.max(var40, var49[1] + (var45 == 2 ? 0.06F : 0.02F));
         }

         var44++;
         long var29 = 19613954193064L;
         int var30 = var29 < 0L ? -1 : 1;
      }

      for (KnifeGestureRenderer.GestureSegment var50 : var1) {
         long var51 = 19613954193064L;
         int var62 = var51 < 0L ? -1 : 1;
         float var53;
         int var52 = (var53 = var50.F - 0.0F) == 0.0F ? 0 : (var53 < 0.0F ? -1 : 1);
         long var30 = 19613954193065L;
         int var33 = var30 < 0L ? -1 : 1;
         if (var52 > 0) {
            var38 = Math.min(var38, var50.F - 0.03F);
            var40 = Math.max(var40, var50.F + 0.08F);
         }

         long var33x = 19613954193064L;
         int var34 = var33x < 0L ? -1 : 1;
      }

      return new float[]{Math.max(0.0F, var38), Math.min(Math.max(var40, var38 + 0.2F), var4)};
   }

   static float decay(float var0, float var1, float var2, float var3) {
      if (var0 < 0.0F) {
         return -decay(-var0, -var1, var2, var3);
      } else if (!(var0 < 1.0E-6F) && !(var3 >= var2)) {
         float var4 = Math.min(var1, 0.0F);
         float var5 = var4 < 0.0F ? Math.min(var2, -5.0F * var0 / var4) : var2;
         if (var3 >= var5) {
            return 0.0F;
         } else {
            float var6 = var5 * var5;
            float var7 = var6 * var5;
            float var8 = var7 * var5;
            float var9 = var8 * var5;
            float var10 = (-8.0F * var4 * var5 - 20.0F * var0) / var6;
            float var11 = -(var10 * var6 + 6.0F * var4 * var5 + 12.0F * var0) / (2.0F * var9);
            float var12 = (3.0F * var10 * var6 + 16.0F * var4 * var5 + 30.0F * var0) / (2.0F * var8);
            float var13 = -(3.0F * var10 * var6 + 12.0F * var4 * var5 + 20.0F * var0) / (2.0F * var7);
            return ((((var11 * var3 + var12) * var3 + var13) * var3 + var10 * 0.5F) * var3 + var4) * var3 + var0;
         }
      } else {
         return 0.0F;
      }
   }

   private static void P(float[] var0, int var1, long var2, Matrix4f var4, Vector3f var5, Quaternionf var6) {
      var4.getTranslation(var5);
      var4.getNormalizedRotation(var6);
      var0[var1] = var5.x;
      var0[var1 + 1] = var5.y;
      var0[var1 + 2] = var5.z;
      var0[var1 + 3] = var6.x;
      var0[var1 + 4] = var6.y;
      var0[var1 + 5] = var6.z;
      var0[var1 + 6] = var6.w;
   }

   private static int a(List<GestureSegment> var0, long var1, float var3) {
      int var4 = 0;

      for (KnifeGestureRenderer.GestureSegment var6 : var0) {
         long var7 = 70623759842838L;
         int var10 = var7 < 0L ? -1 : 1;
         float var10x;
         int var11 = (var10x = var6.F - (var3 + 1.0E-6F)) == 0.0F ? 0 : (var10x < 0.0F ? -1 : 1);
         long var12 = 70623759842839L;
         int var14 = var12 < 0L ? -1 : 1;
         if (var11 <= 0) {
            var4 = var6.y;
         }

         long var14x = 70623759842839L;
         int var15 = var14x < 0L ? -1 : 1;
      }

      return var4;
   }

   private static void A(int var0, short var1, KnifeGestureRenderer.GestureTrack var2, float var3, short var4, float[] var5) {
      var2.N[0].eval(var3, var5, 0);
      var2.N[1].eval(var3, var5, 6);
      var2.N[2].eval(var3, var5, 12);
      var2.N[3].eval(var3, var5, 21);
      var2.N[4].eval(var3, var5, 14);
   }

   private static float[][] L(KnifeGestureRenderer.GestureTrack var0, int var1, float var2, long var3, List var5) {
      long var6 = 69468600543996L;
      short var8 = 16174;
      short var9 = 27458;
      int var10 = (int)(var6 << 48 >>> 48);
      long var11 = 70623759842839L;
      float[][] var13 = new float[var1][23];
      float var14 = 0.0010416667F;
      float[] var15 = new float[23];
      float[] var16 = new float[23];
      float[] var17 = new float[23];
      float[] var18 = new float[23];
      float[] var19 = new float[23];
      ArrayList var20 = new ArrayList<>(var0.L);
      var20.addAll(var5);
      var20.sort(Comparator.comparingDouble(KnifeGestureRenderer.GestureChannel::time));
      int var21 = 0;
      float var22 = 0.0F;
      int var23 = 0;

      while (var23 < var1) {
         float var24 = var23 / 240.0F;

         while (var22 < var24 - 1.0E-7F) {
            long var25 = 136287332365600L;
            int var27 = var25 < 0L ? -1 : 1;
            float var28 = Math.min(var14, var24 - var22);
            A(var8, var9, var0, var22 - var14, (short)var10, var15);
            long var29 = 136287332365600L;
            int var31 = var29 < 0L ? -1 : 1;
            A(var8, var9, var0, var22, (short)var10, var16);
            A(var8, var9, var0, var22 + var14, (short)var10, var17);
            boolean var32 = a(var0.S, var11, var22) == 2;
            int var33 = 0;

            while (var33 < e.length) {
               float var34 = (float)((Math.PI * 2) * var0.M[var33 * 2]);
               long var35 = 136287332365601L;
               int var35x = var35 < 0L ? -1 : 1;
               float var38x = var0.M[var33 * 2 + 1];
               int var39 = e[var33][0];

               while (var39 < e[var33][1]) {
                  long var40 = 136287332365601L;
                  int var39x = var40 < 0L ? -1 : 1;
                  long var43x = 136287332365600L;
                  int var57 = var43x < 0L ? -1 : 1;
                  if (var33 == 1 && var32) {
                     float var56 = (float)Math.exp(-var28 / 0.025F);
                     var19[var39] *= var56;
                     var18[var39] *= var56;
                     long var47 = 136287332365601L;
                     int var44 = var47 < 0L ? -1 : 1;
                  } else {
                     float var46 = (var17[var39] - 2.0F * var16[var39] + var15[var39]) / (var14 * var14);
                     var19[var39] += (-var34 * var34 * var18[var39] - 2.0F * var38x * var34 * var19[var39] - var46) * var28;
                     var18[var39] += var19[var39] * var28;
                  }

                  var39++;
                  long var57x = 136287332365601L;
               }

               var33++;
               long var54 = 136287332365600L;
               int var65 = var54 < 0L ? -1 : 1;
            }

            var22 += var28;

            while (var21 < var20.size()) {
               long var52 = 136287332365601L;
               int var37 = var52 < 0L ? -1 : 1;
               long var37x = 136287332365601L;
               int var60 = var37x < 0L ? -1 : 1;
               if (!(((KnifeGestureRenderer.GestureChannel)var20.get(var21)).V <= var22)) {
                  break;
               }

               KnifeGestureRenderer.GestureChannel var55 = (KnifeGestureRenderer.GestureChannel)var20.get(var21++);
               var19[e[var55.C][0] + var55.u] = var19[e[var55.C][0] + var55.u] + var55.a;
               long var41 = 136287332365601L;
               int var66 = var41 < 0L ? -1 : 1;
            }

            long var53 = 136287332365600L;
            int var58 = var53 < 0L ? -1 : 1;
         }

         A(var8, var9, var0, var24, (short)var10, var16);
         float var49 = Math.clamp((var2 - var24) / 0.14F, 0.0F, 1.0F);
         float var26 = var49 * var49 * (3.0F - 2.0F * var49);
         int var27 = 0;

         while (var27 < 23) {
            long var50 = 136287332365601L;
            int var511 = var50 < 0L ? -1 : 1;
            var13[var23][var27] = var16[var27] + (var27 < 20 ? var18[var27] * var26 : 0.0F);
            var27++;
            long var31 = 136287332365600L;
            int var56 = var31 < 0L ? -1 : 1;
         }

         var23++;
         long var51 = 136287332365601L;
         int var531 = var51 < 0L ? -1 : 1;
      }

      return var13;
   }

   private static void R(KnifeGestureRenderer.GestureTrack var0, List var1, float[][] var2, long var3, int var5, Matrix4f[] var6, Matrix4f[] var7, Matrix4f[] var8) {
      long var9 = 59883563577759L;
      short var11 = 13942;
      short var12 = 186;
      int var13 = (int)(var9 << 40 >>> 40);
      long var14 = 70623759842839L;
      long var16 = 10062722586133L;
      int var18 = 0;

      while (var18 < var5) {
         float[] var19 = (float[])var2[var18].clone();
         int var20 = a(var1, var14, var18 / 240.0F);
         if (var20 != 2) {
            long var21 = 80838359906135L;
            int var24 = var21 < 0L ? -1 : 1;
            var24 = var20 == 0 ? 0 : 14;
            float var25 = y(var1, var11, (byte)var12, var18 / 240.0F, var13);
            K(var16, var19, var24, var25);
         }

         KnifeGestureRenderer.Data var30 = KnifeGestureRenderer.Data.of(var19, 0, 6);
         KnifeGestureRenderer.Data var22 = KnifeGestureRenderer.Data.of(var19, 14, 6);
         var6[var18] = KnifePlacement.hand(var30, new Matrix4f());
         Matrix4f var23 = KnifePlacement.away(var19[20], new Matrix4f()).mul(KnifePlacement.hand(var22, new Matrix4f()));
         var7[var18] = KnifePlacement.mirror(var23, new Matrix4f());
         long var31 = 80838359906136L;
         int var27 = var31 < 0L ? -1 : 1;
         var8[var18] = var20 == 1
            ? KnifePlacement.mirror(KnifePlacement.knife(var0.U, var22, 1.0F, var23, new Matrix4f()), new Matrix4f())
            : KnifePlacement.knife(var0.U, var30, 1.0F, var6[var18], new Matrix4f());
         var18++;
         long var27x = 80838359906136L;
         int var28 = var27x < 0L ? -1 : 1;
      }
   }

   private static void K(long var0, float[] var2, int var3, float var4) {
      float var5 = var2[21] * var4;
      float var6 = var2[22] * var4;
      long var7 = 10062722586133L;
      int var16 = var7 < 0L ? -1 : 1;
      if (var5 == 0.0F) {
         long var10 = 10062722586132L;
         int var12 = var10 < 0L ? -1 : 1;
         if (var6 == 0.0F) {
            return;
         }
      }

      double var16x = Math.toRadians(var2[9] + var2[10] + var2[11]);
      double var12 = var16x + Math.toRadians(V);
      double var14 = var12 + Math.toRadians(W);
      var2[var3] += var5 * (float)Math.sin(var12);
      var2[var3 + 1] = var2[var3 + 1] + var5 * (float)Math.cos(var12);
      var2[var3 + 4] = var2[var3 + 4] + var6 * (float)Math.sin(var14);
      var2[var3 + 3] = var2[var3 + 3] + var6 * 0.5F * (float)Math.cos(var14);
   }

   private static KnifeGestureRenderer.GestureFrame[] O(KnifeGestureRenderer.GestureTrack var0, int var1, short var2, List var3, float[][] var4, int var5, int var6) {
      ArrayList<KnifeGestureRenderer.GestureFrame> var7 = new ArrayList();

      for (int var8 = 0; var8 < var3.size(); var8++) {
         KnifeGestureRenderer.GestureSegment var9 = (KnifeGestureRenderer.GestureSegment)var3.get(var8);
         if (var9.y == 2) {
            KnifeGestureRenderer.GestureSegment var10 = (KnifeGestureRenderer.GestureSegment)var3.get(var8 - 1);
            KnifeGestureRenderer.GestureSegment var11 = (KnifeGestureRenderer.GestureSegment)var3.get(var8 + 1);
            int var12 = Math.round(var9.F * 240.0F);
            int var13 = Math.min(var5 - 1, Math.round(var11.F * 240.0F));
            KnifeGestureRenderer.GestureFrame var14 = new KnifeGestureRenderer.GestureFrame(var10.y, var11.y, var12, var13, var9.G, var9.A, var9.T);
            var14.plan(var0, var4);
            var7.add(var14);
         }
      }

      return var7.toArray(x$0 -> new KnifeGestureRenderer.GestureFrame[x$0]);
   }

   private static float y(List var0, int var1, byte var2, float var3, int var4) {
      float var5 = 1.0F;

      for (int var6 = 0; var6 < var0.size(); var6++) {
         short var7 = 13942;
         if (((KnifeGestureRenderer.GestureSegment)var0.get(var6)).y == 2 && var6 + 1 < var0.size()) {
            float var8 = ((KnifeGestureRenderer.GestureSegment)var0.get(var6)).F;
            float var9 = ((KnifeGestureRenderer.GestureSegment)var0.get(var6 + 1)).F;
            float var10;
            if (var3 < var8) {
               var10 = var8 - var3;
            } else {
               var7 = 13942;
               var10 = var3 > var9 ? var3 - var9 : 0.0F;
            }

            var5 = Math.min(var5, k(var10 / 0.12F));
         }
      }

      return var5;
   }

   private static Matrix4f t(KnifeGestureRenderer.GestureTrack var0, long var1, float[][] var3, int var4, int var5) {
      long var6 = 59883563577759L;
      short var8 = 13942;
      short var9 = 186;
      int var10 = (int)(var6 << 40 >>> 40);
      long var11 = 10062722586133L;
      float[] var13 = (float[])var3[var4].clone();
      int var14 = var5 == 0 ? 0 : 14;
      float var15 = y(var0.S, var8, (byte)var9, var4 / 240.0F, var10);
      K(var11, var13, var14, var15);
      if (var5 == 1) {
         KnifeGestureRenderer.Data var20 = KnifeGestureRenderer.Data.of(var13, 14, 6);
         Matrix4f var19 = KnifePlacement.away(var13[20], new Matrix4f()).mul(KnifePlacement.hand(var20, new Matrix4f()));
         return KnifePlacement.mirror(KnifePlacement.knife(var0.U, var20, 1.0F, var19, new Matrix4f()), new Matrix4f());
      } else {
         KnifeGestureRenderer.Data var18 = KnifeGestureRenderer.Data.of(var13, 0, 6);
         return KnifePlacement.knife(var0.U, var18, 1.0F, KnifePlacement.hand(var18, new Matrix4f()), new Matrix4f());
      }
   }

   private static void E(long var0, KnifeGestureRenderer.GestureTrack var2, List var3, float[][] var4, Matrix4f[] var5, KnifeGestureRenderer.GestureFrame[] var6, int var7, List var8) {
      long var9 = 128484749532640L;
      byte var11 = 0;
      int var12 = 1960521690;
      int var13 = (int)(var9 << 48 >>> 48);
      float var14 = KnifePlacement.length(var2.U);
      Vector3f var15 = new Vector3f();
      Vector3f var16 = new Vector3f();
      Vector3f var17 = new Vector3f();
      Vector3f var18 = new Vector3f();
      double var19 = 0.0;
      double var21 = t(var4[0], var11, var12, var13);
      int var23 = 0;

      while (var23 < var7) {
         float var24 = var23 / 240.0F;
         var5[var23].transformPosition(var15.set(0.0F, var14, 0.0F));
         long var25 = 43589597932906L;
         int var47 = var25 < 0L ? -1 : 1;
         var5[var23].transformPosition(var17.set(0.0F, var14 * 0.55F, 0.0F));
         if (var23 == 0) {
            var16.set(var15);
            var18.set(var17);
            long var46 = 43589597932905L;
            int var291 = var46 < 0L ? -1 : 1;
         } else {
            float var28 = Math.max(var15.distance(var16), var17.distance(var18) * 1.6F) * 240.0F;
            var16.set(var15);
            var18.set(var17);
            double var29 = t(var4[var23], var11, var12, var13);
            double var31 = Math.abs(var29 - var21);
            var21 = var29;
            KnifeGestureRenderer.GestureFrame[] var33 = var6;
            int var34 = var6.length;
            int var35 = 0;

            while (var35 < var34) {
               KnifeGestureRenderer.GestureFrame var36 = var33[var35];
               long var37 = 43589597932906L;
               int var40 = var37 < 0L ? -1 : 1;
               long var40x = 43589597932905L;
               if (var23 > var36.M && var23 <= var36.O) {
                  var31 = Math.abs(Math.toDegrees(var36.P)) / (var36.O - var36.M);
               }

               var35++;
               long var43x = 43589597932906L;
               int var45 = var43x < 0L ? -1 : 1;
            }

            double var48 = var19;
            long var38 = 43589597932906L;
            int var41 = var38 < 0L ? -1 : 1;
            var19 += var31;
            if (Math.floor((var19 + 180.0) / 360.0) > Math.floor((var48 + 180.0) / 360.0)) {
               long var41x = 43589597932906L;
               if (var28 > 1.4F) {
                  float var44x = Math.clamp(var28 / 6.5F, 0.25F, 1.0F);
                  float var45 = 0.9F + 0.2F * Math.clamp(var28 / 9.0F, 0.0F, 1.0F);
                  var8.add(new KnifeGestureRenderer.GestureSound(var24, "swish", var44x, var45));
               }
            }
         }

         var23++;
         long var47x = 43589597932905L;
         int var46 = var47x < 0L ? -1 : 1;
      }
   }

   private static double t(float[] var0, short var1, int var2, int var3) {
      return var0[9] + var0[10] + var0[11];
   }

   static Vector3f axis(int var0) {
      return new Vector3f(var0 == 0 ? 1.0F : 0.0F, var0 == 1 ? 1.0F : 0.0F, var0 == 2 ? 1.0F : 0.0F);
   }

   static Matrix4f blend(Matrix4f var0, Matrix4f var1, float var2, Matrix4f var3) {
      Vector3f var4 = var0.getTranslation(new Vector3f());
      Vector3f var5 = var1.getTranslation(new Vector3f());
      Quaternionf var6 = var0.getNormalizedRotation(new Quaternionf());
      Quaternionf var7 = var1.getNormalizedRotation(new Quaternionf());
      return var3.translationRotate(var4.lerp(var5, var2), var6.slerp(var7, var2));
   }

   private static float N(Quaternionf var0, Quaternionf var1) {
      float var2 = (float)Math.sqrt(Y(var0.x - var1.x) + Y(var0.y - var1.y) + Y(var0.z - var1.z) + Y(var0.w - var1.w));
      float var3 = (float)Math.sqrt(Y(var0.x + var1.x) + Y(var0.y + var1.y) + Y(var0.z + var1.z) + Y(var0.w + var1.w));
      return (float)(4.0 * Math.asin(Math.min(1.0F, Math.min(var2, var3) / 2.0F)));
   }

   private static float Y(float var0) {
      return var0 * var0;
   }

   private static float k(float var0) {
      var0 = Math.clamp(var0, 0.0F, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static float o(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   private static String W(long var0, KnifeGestureRenderer.GestureTrick var2) {
      KnifeSwingState.ensureSourceClassInitialized();
      String var12;
      if (!var2.O && !var2.V) {
         var12 = "";
      } else {
         long var4 = 3242067439619L;
         int var7 = var4 < 0L ? -1 : 1;
         var12 = var2.O ? " twohand" : "";
         long var7x = 3242067439618L;
         int var10 = var7x < 0L ? -1 : 1;
         String var10x = var2.V ? " slash" : "";
         var12 = " |" + var12 + var10x;
      }

      return var12;
   }

   static float[][] keys(KnifeGestureRenderer.GestureTrick var0, String var1, int var2) {
      long var3 = 125615362938050L;
      long var5 = var3 ^ 10676489354844L;
      long var7 = var3 ^ 124029013927105L;
      String var9 = y[var0.w];
      String var10 = var0.Q;
      String var11 = var0.j;
      String var12 = W(var7, var0);
      String var13 = var0.x;
      Iterator var18 = H("[" + var9 + " " + var10 + " " + var11 + var12 + "]\n" + var13, var5).iterator();
      if (var18.hasNext()) {
         KnifeGestureRenderer.GestureTrack var19 = (KnifeGestureRenderer.GestureTrack)var18.next();
         return var19.N[List.of(p).indexOf(var1)].keys(var2);
      } else {
         return new float[][]{new float[0], new float[0]};
      }
   }

   static float[] curve(KnifeGestureRenderer.GestureTrick var0, String var1, float var2) {
      long var3 = 65699263947418L;
      long var5 = var3 ^ 70686672865284L;
      long var7 = var3 ^ 62887297880729L;
      String var9 = y[var0.w];
      String var10 = var0.Q;
      String var11 = var0.j;
      String var12 = W(var7, var0);
      String var13 = var0.x;
      Iterator var18 = H("[" + var9 + " " + var10 + " " + var11 + var12 + "]\n" + var13, var5).iterator();
      if (var18.hasNext()) {
         KnifeGestureRenderer.GestureTrack var19 = (KnifeGestureRenderer.GestureTrack)var18.next();
         int var20 = List.of(p).indexOf(var1);
         float[] var21 = new float[I[var20]];
         var19.N[var20].eval(var2, var21, 0);
         return var21;
      } else {
         return new float[0];
      }
   }

   static String packaged() {
      try {
         String var1;
         try (InputStream var0 = KnifeGestureRenderer.class.getResourceAsStream("/assets/purxiy/knife/tricks.txt")) {
            if (var0 == null) {
               throw new IllegalStateException("The knife tricks are missing: assets/purxiy/knife/tricks.txt");
            }

            var1 = new String(var0.readAllBytes(), StandardCharsets.UTF_8);
         }

         return var1;
      } catch (IOException var6) {
         throw new IllegalStateException("The knife tricks could not be read", var6);
      }
   }

   public static void warmUp() {
   }

   static {
      load(packaged());
   }

   public record Data(float M, float o, float X, float d, float q, float r, float v, float K, float p, float T, float F, float c, float L, float S) {
      static KnifeGestureRenderer.Data of(float[] var0, int var1, int var2) {
         return new KnifeGestureRenderer.Data(
            var0[var1],
            var0[var1 + 1],
            var0[var1 + 2],
            var0[var1 + 3],
            var0[var1 + 4],
            var0[var1 + 5],
            var0[var2],
            var0[var2 + 1],
            var0[var2 + 2],
            var0[var2 + 3],
            var0[var2 + 4],
            var0[var2 + 5],
            var0[var2 + 6],
            var0[var2 + 7]
         );
      }

      public float hx() {
         return this.M;
      }

      public float hy() {
         return this.o;
      }

      public float hz() {
         return this.X;
      }

      public float twist() {
         return this.d;
      }

      public float cock() {
         return this.q;
      }

      public float flex() {
         return this.r;
      }

      public float kx() {
         return this.v;
      }

      public float ky() {
         return this.K;
      }

      public float kz() {
         return this.p;
      }

      public float pitch() {
         return this.T;
      }

      public float yaw() {
         return this.F;
      }

      public float roll() {
         return this.c;
      }

      public float handleA() {
         return this.L;
      }

      public float handleB() {
         return this.S;
      }

      public float floatState27() {
         return this.M;
      }

      public float floatState11() {
         return this.o;
      }

      public float floatState17() {
         return this.X;
      }

      public float floatState33() {
         return this.d;
      }

      public float floatState48() {
         return this.q;
      }

      public float floatState45() {
         return this.r;
      }

      public float floatState30() {
         return this.v;
      }

      public float floatState50() {
         return this.K;
      }

      public float floatState35() {
         return this.p;
      }

      public float floatState29() {
         return this.T;
      }

      public float floatState36() {
         return this.F;
      }

      public float floatState25() {
         return this.c;
      }

      public float floatState57() {
         return this.L;
      }

      public float floatState14() {
         return this.S;
      }
   }

   public record GestureSound(float e, String N, float C, float n) {
      public float time() {
         return this.e;
      }

      public String sound() {
         return this.N;
      }

      public float volume() {
         return this.C;
      }

      public float pitch() {
         return this.n;
      }

      public float floatState23() {
         return this.e;
      }

      public String stringState53() {
         return this.N;
      }

      public float floatState16() {
         return this.C;
      }

      public float floatState22() {
         return this.n;
      }
   }

   public static final class GestureTransform {
      public final float T;
      private final KnifeGestureRenderer.GestureKey c;
      private final KnifeGestureRenderer.GestureKey N;
      private final KnifeGestureRenderer.GestureKey k;
      private final float Y;
      private final float z;
      private final float Z;
      private final float P;
      private final float G;
      private final float D;

      public static KnifeGestureRenderer.GestureTransform of(KnifeGestureRenderer.GestureTrick var0, float var1, KnifeGestureRenderer.GestureTransform var2, float var3, KnifeGestureRenderer.GestureTrick var4, float var5) {
         float var6 = 0.008333334F;
         KnifeGestureRenderer.GesturePose var7 = var0.at(var1, new KnifeGestureRenderer.GesturePose());
         KnifeGestureRenderer.GesturePose var8 = var0.at(var1 - var6, new KnifeGestureRenderer.GesturePose());
         if (var2 != null) {
            var2.apply(var7, var3);
            var2.apply(var8, var3 - var6);
         }

         return new KnifeGestureRenderer.GestureTransform(var7, var8, var4.at(var5, new KnifeGestureRenderer.GesturePose()), var4.at(var5 + var6, new KnifeGestureRenderer.GesturePose()), var6);
      }

      private GestureTransform(KnifeGestureRenderer.GesturePose var1, KnifeGestureRenderer.GesturePose var2, KnifeGestureRenderer.GesturePose var3, KnifeGestureRenderer.GesturePose var4, float var5) {
         this.c = new KnifeGestureRenderer.GestureKey(var1.N, var2.N, var3.N, var4.N, 0.008333334F);
         this.N = new KnifeGestureRenderer.GestureKey(W(var1), W(var2), W(var3), W(var4), 0.008333334F);
         this.k = new KnifeGestureRenderer.GestureKey(var1.G, var2.G, var3.G, var4.G, 0.008333334F);
         this.Y = var1.t - var3.t;
         this.z = (var1.t - var2.t - (var4.t - var3.t)) / 0.008333334F;
         this.Z = var1.c - KnifeGestureRenderer.GesturePose.nearest(var1.c, var3.c);
         this.P = (var1.c - var2.c - (var4.c - var3.c)) / 0.008333334F;
         this.G = var1.S - KnifeGestureRenderer.GesturePose.nearest(var1.S, var3.S);
         this.D = (var1.S - var2.S - (var4.S - var3.S)) / 0.008333334F;
         float var6 = this.c.B + this.N.B * 0.5F + (this.c.N + this.N.N * 0.5F) * 0.06F;
         this.T = Math.clamp(0.1F + var6 * 0.45F, 0.12F, 0.24F);
      }

      public KnifeGestureRenderer.GesturePose apply(KnifeGestureRenderer.GesturePose var1, float var2) {
         float var3 = Math.max(0.0F, var2);
         if (var3 >= this.T) {
            return var1;
         } else {
            Matrix4f var4 = W(var1);
            this.c.apply(var1.N, var3, this.T);
            this.N.apply(var4, var3, this.T);
            var1.r.set(var1.N).mul(var4);
            this.k.apply(var1.G, var3, this.T);
            var1.t = Math.clamp(var1.t + KnifeGestureRenderer.decay(this.Y, this.z, this.T, var3), 0.0F, 1.0F);
            var1.c = var1.c + KnifeGestureRenderer.decay(this.Z, this.P, this.T, var3);
            var1.S = var1.S + KnifeGestureRenderer.decay(this.G, this.D, this.T, var3);
            return var1;
         }
      }

      private static Matrix4f W(KnifeGestureRenderer.GesturePose var0) {
         return new Matrix4f(var0.N).invertAffine().mul(var0.r);
      }
   }

   private record GestureChannel(float V, int C, int u, float a) {
      public float time() {
         return this.V;
      }

      public int part() {
         return this.C;
      }

      public int channel() {
         return this.u;
      }

      public float amount() {
         return this.a;
      }

      public float floatState15() {
         return this.V;
      }

      public int integerState50() {
         return this.C;
      }

      public int integerState24() {
         return this.u;
      }

      public float floatState31() {
         return this.a;
      }
   }

   private record GestureSegment(float F, int y, float G, int A, float T) {
      public float time() {
         return this.F;
      }

      public int who() {
         return this.y;
      }

      public float turns() {
         return this.G;
      }

      public int axis() {
         return this.A;
      }

      public float gravity() {
         return this.T;
      }

      public float floatState36() {
         return this.F;
      }

      public int integerState52() {
         return this.y;
      }

      public float floatState28() {
         return this.G;
      }

      public int integerState38() {
         return this.A;
      }

      public float floatState29() {
         return this.T;
      }
   }

   private static final class GestureClip {
      final int Y;
      final List<float[]> o = new ArrayList<>();
      float[] p;
      float[][] G;
      float[][] H;
      private static final String[] b = new String[3];

      GestureClip(int var1) {
         this.Y = var1;
      }

      void add(String[] var1, KnifeGestureRenderer.GestureTrack var2, int var3) {
         if (var1.length != this.Y + 1) {
            throw var2.error("A key of this track needs a time and " + this.Y + " values on line " + var3);
         } else {
            float[] var4 = new float[var1.length];

            for (int var5 = 0; var5 < var1.length; var5++) {
               var4[var5] = Float.parseFloat(var1[var5]);
            }

            if (!this.o.isEmpty() && var4[0] <= this.o.getLast()[0]) {
               throw var2.error("Knife keys must move forward in time on line " + var3);
            } else {
               this.o.add(var4);
               this.p = null;
            }
         }
      }

      float end() {
         return this.o.isEmpty() ? 0.0F : this.o.getLast()[0];
      }

      float[] still() {
         long var1 = 47651863240951L;
         long var3 = var1 ^ 57378352536777L;
         if (this.o.size() < 2) {
            return null;
         } else {
            int var5 = 0;
            int var6 = this.o.size() - 1;

            while (var5 + 1 < this.o.size() && q(this.o.get(var5 + 1), var3, this.o.getFirst())) {
               var5++;
            }

            while (var6 > 0 && q(this.o.get(var6 - 1), var3, this.o.getLast())) {
               var6--;
            }

            return new float[]{this.o.get(var5)[0], this.o.get(var6)[0]};
         }
      }

      private static boolean q(float[] var0, long var1, float[] var3) {
         int var4 = 1;

         while (var4 < var0.length) {
            float var5;
            int var6 = (var5 = Math.abs(var0[var4] - var3[var4]) - 1.0E-4F) == 0.0F ? 0 : (var5 < 0.0F ? -1 : 1);
            long var7 = 34607239248958L;
            int var10 = var7 < 0L ? -1 : 1;
            long var10x = 34607239248958L;
            int var12 = var10x < 0L ? -1 : 1;
            if (var6 > 0) {
               return false;
            }

            var4++;
            long var12x = 34607239248957L;
            int var13 = var12x < 0L ? -1 : 1;
         }

         return true;
      }

      private void h(long var1) {
         long var3 = 138017339820206L;
         short var5 = 32134;
         char var6 = 'ꪃ';
         int var7 = (int)(var3 << 48 >>> 48);
         int var8 = this.o.size();
         this.p = new float[var8];
         this.G = new float[this.Y][var8];
         this.H = new float[this.Y][var8];
         int var9 = 0;

         while (var9 < var8) {
            long var10 = 36953516026232L;
            int var13 = var10 < 0L ? -1 : 1;
            this.p[var9] = this.o.get(var9)[0];
            var13 = 0;

            while (var13 < this.Y) {
               float[] var14 = this.G[var13];
               float[] var15 = this.o.get(var9);
               int var16 = var13 + 1;
               long var17 = 36953516026232L;
               int var20 = var17 < 0L ? -1 : 1;
               var14[var9] = var15[var16];
               var13++;
               long var20x = 36953516026232L;
            }

            var9++;
            long var24 = 36953516026233L;
            int var25 = var24 < 0L ? -1 : 1;
         }

         int var23 = 0;

         while (var23 < this.Y) {
            S(this.p, var5, this.G[var23], this.H[var23], var6, var7);
            var23++;
            long var11 = 36953516026233L;
            int var241 = var11 < 0L ? -1 : 1;
         }
      }

      void eval(float var1, float[] var2, int var3) {
         long var4 = 85133808404701L;
         long var6 = var4 ^ 119804545114532L;
         int var8 = this.o.size();
         if (var8 == 0) {
            for (int var9 = 0; var9 < this.Y; var9++) {
               var2[var3 + var9] = 0.0F;
            }
         } else {
            if (this.p == null) {
               this.h(var6);
            }

            if (var8 != 1 && !(var1 <= this.p[0]) && !(var1 >= this.p[var8 - 1])) {
               int var22 = 0;

               while (var22 < var8 - 2 && var1 > this.p[var22 + 1]) {
                  var22++;
               }

               float var23 = this.p[var22 + 1] - this.p[var22];
               float var11 = (var1 - this.p[var22]) / var23;
               float var12 = var11 * var11;
               float var13 = var12 * var11;
               float var14 = 2.0F * var13 - 3.0F * var12 + 1.0F;
               float var15 = var13 - 2.0F * var12 + var11;
               float var16 = -2.0F * var13 + 3.0F * var12;
               float var17 = var13 - var12;

               for (int var18 = 0; var18 < this.Y; var18++) {
                  float[] var19 = this.G[var18];
                  float[] var20 = this.H[var18];
                  var2[var3 + var18] = var14 * var19[var22] + var15 * var23 * var20[var22] + var16 * var19[var22 + 1] + var17 * var23 * var20[var22 + 1];
               }
            } else {
               int var21 = var8 != 1 && !(var1 <= this.p[0]) ? var8 - 1 : 0;

               for (int var10 = 0; var10 < this.Y; var10++) {
                  var2[var3 + var10] = this.G[var10][var21];
               }
            }
         }
      }

      float[][] keys(int var1) {
         long var2 = 20564214564455L;
         long var4 = var2 ^ 56247219285790L;
         if (this.p == null && !this.o.isEmpty()) {
            this.h(var4);
         }

         return this.o.isEmpty() ? new float[][]{new float[0], new float[0]} : new float[][]{this.p, this.G[var1]};
      }

      private static void S(float[] var0, int var1, float[] var2, float[] var3, int var4, int var5) {
         int var6 = var0.length;

         for (int var7 = 1; var7 < var6 - 1; var7++) {
            float var8 = var0[var7] - var0[var7 - 1];
            float var9 = var0[var7 + 1] - var0[var7];
            float var10 = (var2[var7] - var2[var7 - 1]) / var8;
            float var11 = (var2[var7 + 1] - var2[var7]) / var9;
            float var12 = var10 * var11;
            if (var12 <= 0.0F) {
               var3[var7] = 0.0F;
            } else {
               float var13 = 2.0F * var9 + var8;
               float var14 = var9 + 2.0F * var8;
               var3[var7] = (var13 + var14) / (var13 / var10 + var14 / var11);
            }
         }

         if (var6 > 0) {
            var3[0] = var3[var6 - 1] = 0.0F;
         }
      }
   }

   private static final class GestureTrack {
      final String D;
      final String v;
      final String P;
      final int U;
      final boolean i;
      final boolean X;
      final boolean J;
      final KnifeGestureRenderer.GestureClip[] N;
      final List<KnifeGestureRenderer.GestureSegment> F;
      final List<KnifeGestureRenderer.GestureSound> t;
      final List<KnifeGestureRenderer.GestureChannel> L;
      List<KnifeGestureRenderer.GestureSegment> S = List.of();
      final float[] M;
      final StringBuilder z;
      private static final String[] b = new String[11];

      GestureTrack(String var1) {
         this.S = List.of();
         this.D = var1;
         String[] var2 = var1.split("\\|");
         String[] var3 = var2[0].trim().split("\\s+");
         String var4 = var3[0].toLowerCase(Locale.ROOT);
         this.U = var4.equals("all") ? -1 : List.of(KnifeGestureRenderer.y).indexOf(var4);
         if ((this.U >= 0 || var4.equals("all")) && var3.length >= 2) {
            this.v = var3[1].toLowerCase(Locale.ROOT);
            if (!KnifeGestureRenderer.n.contains(this.v)) {
               throw new IllegalArgumentException("A trick is a draw, an inspect, a walk or a fidget: [" + var1 + "]");
            } else if (this.U < 0 && this.v.equals("draw")) {
               throw new IllegalArgumentException("Every knife has a draw of its own: [" + var1 + "]");
            } else {
               this.P = var3.length > 2
                  ? String.join(" ", List.of(var3).subList(2, var3.length))
                  : this.v.substring(0, 1).toUpperCase(Locale.ROOT) + this.v.substring(1);
               String var5 = var2.length > 1 ? var2[1].toLowerCase(Locale.ROOT) : "";
               this.i = var5.contains("twohand");
               this.X = var5.contains("slash");
               this.J = false;
               this.N = new KnifeGestureRenderer.GestureClip[KnifeGestureRenderer.p.length];

               for (int var6 = 0; var6 < KnifeGestureRenderer.p.length; var6++) {
                  this.N[var6] = new KnifeGestureRenderer.GestureClip(KnifeGestureRenderer.I[var6]);
               }

               this.F = new ArrayList<>();
               this.t = new ArrayList<>();
               this.L = new ArrayList<>();
               this.M = new float[]{4.2F, 0.62F, 7.0F, 0.45F, 4.2F, 0.62F};
               this.z = new StringBuilder();
            }
         } else {
            throw new IllegalArgumentException("Unknown knife trick: [" + var1 + "]");
         }
      }

      private GestureTrack(KnifeGestureRenderer.GestureTrack var1, int var2) {
         this.D = var1.D;
         this.v = var1.v;
         this.P = var1.P;
         this.U = var2;
         this.i = var1.i;
         this.X = var1.X;
         this.J = true;
         this.N = var1.N;
         this.F = var1.F;
         this.t = var1.t;
         this.L = var1.L;
         this.M = var1.M;
         this.z = var1.z;
      }

      KnifeGestureRenderer.GestureTrack forKnife(int var1) {
         return new KnifeGestureRenderer.GestureTrack(this, var1);
      }

      float duration() {
         float var1 = 0.0F;

         for (KnifeGestureRenderer.GestureClip var5 : this.N) {
            var1 = Math.max(var1, var5.end());
         }

         for (KnifeGestureRenderer.GestureSegment var9 : this.F) {
            var1 = Math.max(var1, var9.F);
         }

         for (KnifeGestureRenderer.GestureSound var10 : this.t) {
            var1 = Math.max(var1, var10.e);
         }

         for (KnifeGestureRenderer.GestureChannel var11 : this.L) {
            var1 = Math.max(var1, var11.V);
         }

         return var1;
      }

      IllegalArgumentException error(String var1) {
         return new IllegalArgumentException(var1 + " in [" + this.D + "]");
      }
   }

   public static final class GesturePose {
      public final Matrix4f N = new Matrix4f();
      public final Matrix4f G = new Matrix4f();
      public final Matrix4f r = new Matrix4f();
      public float t;
      public float c;
      public float S;
      public int U;

      public KnifeGestureRenderer.GesturePose set(KnifeGestureRenderer.GesturePose var1) {
         this.N.set(var1.N);
         this.G.set(var1.G);
         this.r.set(var1.r);
         this.t = var1.t;
         this.c = var1.c;
         this.S = var1.S;
         this.U = var1.U;
         return this;
      }

      public KnifeGestureRenderer.GesturePose rest(int var1) {
         KnifePlacement.hand(KnifeGestureRenderer.M, this.N);
         KnifePlacement.mirror(KnifePlacement.away(0.0F, new Matrix4f()).mul(KnifePlacement.hand(KnifeGestureRenderer.M, new Matrix4f())), this.G);
         KnifePlacement.knife(var1, KnifeGestureRenderer.M, 1.0F, this.N, this.r);
         this.t = this.c = this.S = 0.0F;
         this.U = 0;
         return this;
      }

      public KnifeGestureRenderer.GesturePose toward(KnifeGestureRenderer.GesturePose var1, float var2, KnifeGestureRenderer.GesturePose var3) {
         KnifeGestureRenderer.blend(this.N, var1.N, var2, var3.N);
         KnifeGestureRenderer.blend(this.G, var1.G, var2, var3.G);
         KnifeGestureRenderer.blend(this.r, var1.r, var2, var3.r);
         var3.t = this.t + (var1.t - this.t) * var2;
         var3.c = this.c + (nearest(this.c, var1.c) - this.c) * var2;
         var3.S = this.S + (nearest(this.S, var1.S) - this.S) * var2;
         var3.U = var2 < 1.0F ? this.U : var1.U;
         return var3;
      }

      public boolean resting(KnifeGestureRenderer.GesturePose var1, float var2, float var3) {
         long var4 = 33086831935733L;
         long var6 = var4 ^ 113167646319714L;
         return X(this.N, var6, var1.N, var2, var3)
            && X(this.r, var6, var1.r, var2, var3)
            && this.t <= 1.0E-4F
            && Math.abs(Math.IEEEremainder(this.c - var1.c, 360.0)) <= var3
            && Math.abs(Math.IEEEremainder(this.S - var1.S, 360.0)) <= var3;
      }

      static float nearest(float var0, float var1) {
         return var1 + 360.0F * Math.round((var0 - var1) / 360.0F);
      }

      private static boolean X(Matrix4f var0, long var1, Matrix4f var3, float var4, float var5) {
         KnifeSwingState.ensureSourceClassInitialized();
         Vector3f var6 = var0.getTranslation(new Vector3f());
         Vector3f var7 = var3.getTranslation(new Vector3f());
         Quaternionf var8 = var0.getNormalizedRotation(new Quaternionf());
         Quaternionf var9 = var3.getNormalizedRotation(new Quaternionf());
         return var6.distance(var7) <= var4 && Math.toDegrees(KnifeGestureRenderer.N(var8, var9)) <= var5;
      }
   }

   private static final class GestureKey {
      final Vector3f Q;
      final Vector3f h;
      final float B;
      final float p;
      final float N;
      final float I;

      GestureKey(Matrix4f var1, Matrix4f var2, Matrix4f var3, Matrix4f var4, float var5) {
         long var6 = 46549471523921L;
         long var8 = var6 ^ 86598442622181L;
         this.Q = new Vector3f();
         this.h = new Vector3f(1.0F, 0.0F, 0.0F);
         Vector3f var10 = var1.getTranslation(new Vector3f());
         Vector3f var11 = var3.getTranslation(new Vector3f());
         Vector3f var12 = new Vector3f(var10).sub(var11);
         Vector3f var13 = new Vector3f(var10)
            .sub(var2.getTranslation(new Vector3f()))
            .sub(new Vector3f(var4.getTranslation(new Vector3f())).sub(var11))
            .div(var5);
         this.B = var12.length();
         if (this.B > 1.0E-6F) {
            this.Q.set(var12).div(this.B);
         }

         this.p = this.B > 1.0E-6F ? var13.dot(this.Q) : 0.0F;
         Quaternionf var14 = var1.getNormalizedRotation(new Quaternionf());
         Quaternionf var15 = var3.getNormalizedRotation(new Quaternionf());
         Quaternionf var16 = new Quaternionf(var14).mul(new Quaternionf(var15).conjugate());
         if (var16.w < 0.0F) {
            var16.set(-var16.x, -var16.y, -var16.z, -var16.w);
         }

         float var17 = (float)Math.sqrt(var16.x * var16.x + var16.y * var16.y + var16.z * var16.z);
         this.N = (float)(2.0 * Math.atan2(var17, var16.w));
         if (var17 > 1.0E-6F) {
            this.h.set(var16.x / var17, var16.y / var17, var16.z / var17);
         }

         Vector3f var18 = a(var2.getNormalizedRotation(new Quaternionf()), var14, var5, var8);
         Vector3f var19 = a(var15, var4.getNormalizedRotation(new Quaternionf()), var5, var8);
         this.I = this.N > 1.0E-6F ? var18.sub(var19).dot(this.h) : 0.0F;
      }

      void apply(Matrix4f var1, float var2, float var3) {
         float var4 = KnifeGestureRenderer.decay(this.B, this.p, var3, var2);
         float var5 = KnifeGestureRenderer.decay(this.N, this.I, var3, var2);
         if (var4 != 0.0F || var5 != 0.0F) {
            Vector3f var6 = var1.getTranslation(new Vector3f()).fma(var4, this.Q);
            Quaternionf var7 = new Quaternionf().rotationAxis(var5, this.h).mul(var1.getNormalizedRotation(new Quaternionf()));
            var1.translationRotate(var6.x, var6.y, var6.z, var7);
         }
      }

      private static Vector3f a(Quaternionf var0, Quaternionf var1, float var2, long var3) {
         KnifeSwingState.ensureSourceClassInitialized();
         Quaternionf var5 = new Quaternionf(var1).mul(new Quaternionf(var0).conjugate());
         if (var5.w < 0.0F) {
            var5.set(-var5.x, -var5.y, -var5.z, -var5.w);
         }

         float var6 = (float)Math.sqrt(var5.x * var5.x + var5.y * var5.y + var5.z * var5.z);
         if (var6 < 1.0E-7F) {
            return new Vector3f();
         } else {
            float var7 = (float)(2.0 * Math.atan2(var6, var5.w));
            return new Vector3f(var5.x, var5.y, var5.z).mul(var7 / var6 / var2);
         }
      }
   }

   private static final class GestureFrame {
      final int i;
      final int K;
      final int M;
      final int O;
      final int Y;
      final float r;
      final float U;
      final float g;
      Vector3f C;
      Vector3f d;
      Vector3f k;
      Vector3f S;
      Quaternionf D;
      Quaternionf V;
      float P;
      float B = 1.0F;
      float T = 1.0F;
      private static final long[] a = new long[]{-2659116877714883903L, -16312530605382623L};
      private static final Integer[] b = new Integer[2];

      GestureFrame(int var1, int var2, int var3, int var4, float var5, int var6, float var7) {
         this.i = var1;
         this.K = var2;
         this.M = var3;
         this.O = var4;
         this.r = var5;
         this.Y = var6;
         this.U = var7;
         this.g = (var4 - var3) / 240.0F;
      }

      void plan(KnifeGestureRenderer.GestureTrack var1, float[][] var2) {
         long var3 = 46559938802630L;
         long var5 = var3 ^ 111399922013957L;
         float[] var7 = var2[this.M];
         Vector3f var8 = KnifePlacement.centre(var1.U, var7[12], var7[13]);
         this.plan(KnifeGestureRenderer.t(var1, var5, var2, this.M, this.i), KnifeGestureRenderer.t(var1, var5, var2, this.O, this.K), var8);
         if (this.P != 0.0F) {
            float var9 = this.P / this.g;
            Matrix4f var10 = KnifeGestureRenderer.t(var1, var5, var2, Math.max(0, this.M - 1), this.i);
            Matrix4f var11 = KnifeGestureRenderer.t(var1, var5, var2, this.M, this.i);
            Matrix4f var12 = KnifeGestureRenderer.t(var1, var5, var2, this.O, this.K);
            Matrix4f var13 = KnifeGestureRenderer.t(var1, var5, var2, Math.min(var2.length - 1, this.O + 1), this.K);
            this.B = Math.clamp(this.B(var10, var11) / var9, 0.0F, 1.6F);
            this.T = Math.clamp(this.B(var12, var13) / var9, 0.0F, 1.6F);
         }
      }

      private float B(Matrix4f var1, Matrix4f var2) {
         Quaternionf var3 = var1.getNormalizedRotation(new Quaternionf());
         Quaternionf var4 = var2.getNormalizedRotation(new Quaternionf());
         Quaternionf var5 = var3.conjugate().mul(var4);
         Vector3f var6 = KnifeGestureRenderer.axis(this.Y);
         float var7 = var5.x * var6.x + var5.y * var6.y + var5.z * var6.z;
         return (float)(2.0 * Math.atan2(var7, var5.w) * 240.0);
      }

      private float z(float var1) {
         float var2 = var1 * var1;
         float var3 = var2 * var1;
         return (var3 - 2.0F * var2 + var1) * this.B + (-2.0F * var3 + 3.0F * var2) + (var3 - var2) * this.T;
      }

      void plan(Matrix4f var1, Matrix4f var2, Vector3f var3) {
         this.C = var3;
         this.d = var1.transformPosition(new Vector3f(var3));
         this.k = var2.transformPosition(new Vector3f(var3));
         float var4 = this.g;
         this.S = new Vector3f(this.k).sub(this.d).add(0.0F, 0.5F * this.U * var4 * var4, 0.0F).div(var4);
         this.D = var1.getNormalizedRotation(new Quaternionf());
         Quaternionf var5 = var2.getNormalizedRotation(new Quaternionf());
         Quaternionf var6 = new Quaternionf(this.D).conjugate().mul(var5);
         Vector3f var7 = KnifeGestureRenderer.axis(this.Y);
         float var8 = var6.x * var7.x + var6.y * var7.y + var6.z * var7.z;
         float var9 = (float)(2.0 * Math.atan2(var8, var6.w));
         var9 = (float)Math.IEEEremainder(var9, Math.PI * 2);
         float var10 = (float)((Math.PI * 2) * Math.round(this.r - var9 / (Math.PI * 2)));
         this.P = var9 + var10;
         this.V = new Quaternionf().rotationAxis(-var9, var7).mul(var6);
      }

      Matrix4f at(float var1, Matrix4f var2) {
         float var3 = var1 / this.g;
         float var4 = this.z(var3);
         Vector3f var5 = new Vector3f(this.S).mul(var1).add(this.d).sub(0.0F, 0.5F * this.U * var1 * var1, 0.0F);
         Quaternionf var6 = new Quaternionf(this.D).rotateAxis(this.P * var4, KnifeGestureRenderer.axis(this.Y)).mul(new Quaternionf().slerp(this.V, var4));
         return var2.translationRotate(var5.x, var5.y, var5.z, var6).translate(-this.C.x, -this.C.y, -this.C.z);
      }

      void fly(KnifeGestureRenderer.GestureTrack var1, float[][] var2, Matrix4f[] var3) {
         long var4 = 116120539141404L;
         long var6 = var4 ^ 136497005168819L;
         long var8 = var4 ^ 42424330613215L;
         this.plan(var1, var2);
         int var10 = Math.max(1, Math.min(Math.round(12.0F), (this.O - this.M) / 4));
         Matrix4f var11 = new Matrix4f();
         var3[this.M].set(KnifeGestureRenderer.t(var1, var8, var2, this.M, this.i));
         var3[this.O].set(KnifeGestureRenderer.t(var1, var8, var2, this.O, this.K));

         for (int var12 = this.M + 1; var12 < this.O; var12++) {
            this.at((var12 - this.M) / 240.0F, var11);
            int var13 = var12 - this.M;
            int var14 = this.O - var12;
            if (var13 < var10) {
               KnifeGestureRenderer.blend(
                  KnifeGestureRenderer.t(var1, var8, n(var2, var12, this.M, var6, -1), var12, this.i), var11, KnifeGestureRenderer.k((float)var13 / var10), var3[var12]
               );
            } else if (var14 < var10) {
               KnifeGestureRenderer.blend(
                  KnifeGestureRenderer.t(var1, var8, n(var2, var12, this.O, var6, 1), var12, this.K), var11, KnifeGestureRenderer.k((float)var14 / var10), var3[var12]
               );
            } else {
               var3[var12].set(var11);
            }
         }
      }

      private static float[][] n(float[][] var0, int var1, int var2, long var3, int var5) {
         short var6 = 30134;
         float[][] var7 = (float[][])var0.clone();
         float[] var8 = (float[])var0[var1].clone();
         int var9 = Math.clamp((long)(var2 + var5), 0, var0.length - 1);
         float var10 = (float)(var1 - var2) * var5;
         int var11 = 6;

         while (var11 < 14) {
            long var12 = 23883323088303L;
            int var14 = var12 < 0L ? -1 : 1;
            var8[var11] = var0[var2][var11] + (var0[var9][var11] - var0[var2][var11]) * var10;
            var11++;
            long var14x = 23883323088303L;
            int var16 = var14x < 0L ? -1 : 1;
         }

         var7[var1] = var8;
         return var7;
      }

      void knock(float[][] var1, List<KnifeGestureRenderer.GestureChannel> var2) {
         if (!(KnifeGestureRenderer.G <= 0.0F) && this.S != null) {
            Vector3f var3 = new Vector3f(this.S).sub(0.0F, this.U * this.g, 0.0F);
            int var4 = this.K == 0 ? 0 : 14;
            int var5 = Math.min(this.O, var1.length - 1);
            int var6 = Math.max(0, var5 - 1);
            Vector3f var7 = new Vector3f(
                  var1[var5][var4] - var1[var6][var4], var1[var5][var4 + 1] - var1[var6][var4 + 1], var1[var5][var4 + 2] - var1[var6][var4 + 2]
               )
               .mul(240.0F);
            if (this.K == 1) {
               var3.x = -var3.x;
            }

            Vector3f var8 = var3.sub(var7).mul(KnifeGestureRenderer.G);
            float var9 = this.O / 240.0F;
            int var10 = this.K == 0 ? 0 : 2;

            for (int var11 = 0; var11 < 3; var11++) {
               var2.add(new KnifeGestureRenderer.GestureChannel(var9, var10, var11, var8.get(var11)));
            }
         }
      }
   }

   public static final class GestureTrick {
      public final int w;
      public final String Q;
      public final String j;
      public final boolean O;
      public final boolean V;
      public final boolean G;
      public final float P;
      public final float N;
      final float m;
      final int K;
      final float[] t;
      final KnifeGestureRenderer.GestureSound[] p;
      final String x;
      private static final long[] a = new long[]{-8521048196036463640L, -3978966387260713015L, -8182203664242037778L};
      private static final Integer[] b = new Integer[3];

      private GestureTrick(
         int var1,
         String var2,
         String var3,
         long var4,
         boolean var6,
         boolean var7,
         boolean var8,
         float var9,
         float var10,
         float var11,
         float[] var12,
         KnifeGestureRenderer.GestureSound[] var13,
         String var14
      ) {
         this.w = var1;
         this.Q = var2;
         this.j = var3;
         this.O = var6;
         this.V = var7;
         this.G = var8;
         this.P = var9;
         this.N = var10;
         this.m = var11;
         this.K = var12.length / 25;
         this.t = var12;
         this.p = var13;
         this.x = var14;
      }

      public float duration() {
         return this.m;
      }

      public List<KnifeGestureRenderer.GestureSound> events() {
         return List.of(this.p);
      }

      public KnifeGestureRenderer.GesturePose at(float var1, KnifeGestureRenderer.GesturePose var2) {
         long var3 = 138053446677153L;
         long var5 = var3 ^ 66199721875406L;
         float var7 = Math.clamp(var1, 0.0F, this.m) * 240.0F;
         int var8 = Math.min((int)var7, this.K - 2);
         float var9 = Math.clamp(var7 - var8, 0.0F, 1.0F);
         int var10 = var8 * 25;
         int var11 = var10 + 25;
         this.f(var10, var11, var9, 0, var5, var2.N);
         this.f(var10, var11, var9, 7, var5, var2.G);
         this.f(var10, var11, var9, 15, var5, var2.r);
         var2.t = KnifeGestureRenderer.o(this.t[var10 + 14], this.t[var11 + 14], var9);
         var2.c = KnifeGestureRenderer.o(this.t[var10 + 22], this.t[var11 + 22], var9);
         var2.S = KnifeGestureRenderer.o(this.t[var10 + 22 + 1], this.t[var11 + 22 + 1], var9);
         var2.U = (int)this.t[(var9 < 0.5F ? var10 : var11) + 24];
         return var2;
      }

      private void f(int var1, int var2, float var3, int var4, long var5, Matrix4f var7) {
         float[] var8 = this.t;
         Quaternionf var9 = new Quaternionf(var8[var1 + var4 + 3], var8[var1 + var4 + 4], var8[var1 + var4 + 5], var8[var1 + var4 + 6]);
         Quaternionf var10 = new Quaternionf(var8[var2 + var4 + 3], var8[var2 + var4 + 4], var8[var2 + var4 + 5], var8[var2 + var4 + 6]);
         var9.nlerp(var10, var3);
         var7.translationRotate(
            KnifeGestureRenderer.o(var8[var1 + var4], var8[var2 + var4], var3),
            KnifeGestureRenderer.o(var8[var1 + var4 + 1], var8[var2 + var4 + 1], var3),
            KnifeGestureRenderer.o(var8[var1 + var4 + 2], var8[var2 + var4 + 2], var3),
            var9
         );
      }

      @Override
      public String toString() {
         return KnifeGestureRenderer.y[this.w] + " " + this.Q + " " + this.j;
      }
   }
}
