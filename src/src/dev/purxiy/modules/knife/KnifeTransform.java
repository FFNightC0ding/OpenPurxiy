// Sourced By DevFFNightmare
package dev.purxiy.modules.knife;

import dev.purxiy.loader.fabric.ClientServices;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class KnifeTransform {
   private static final Map<String, KnifeTransform.TypeH> R = new HashMap<>();
   private static final String L = System.getProperty("purxiy.wieldSheets");

   private static final String[] b = new String[47];
   private static final long[] c = new long[]{-8742439455913222866L, -4710603967252621221L};


   private KnifeTransform() {
   }

   private static void T(float[] var0, float[][] var1, boolean[] var2, long var3, int var5, float[][] var6) {
      KnifeUpdater2.ensureSourceClassInitialized();
      int var7 = var0.length;

      for (int var8 = 0; var8 < var7; var8++) {
         long var9 = 46656259779867L;
         if (46656259779867L == 0L) {
            boolean var10000 = false;
         } else {
            int var29 = var9 < 0L ? -1 : 1;
         }

         if (var8 != 0) {
            long var12 = 46656259779867L;
            if (46656259779867L == 0L) {
               boolean var30 = false;
            } else {
               int var31 = var12 < 0L ? -1 : 1;
            }

            if (var8 != var7 - 1 && !var2[var8]) {
               float var14 = var0[var8] - var0[var8 - 1];
               float var15 = var0[var8 + 1] - var0[var8];
               float var16 = (var1[var8][var5] - var1[var8 - 1][var5]) / var14;
               float var17 = (var1[var8 + 1][var5] - var1[var8][var5]) / var15;
               long var18 = 46656259779866L;
               if (46656259779866L == 0L) {
                  boolean var34 = false;
               } else {
                  int var35 = var18 < 0L ? -1 : 1;
               }

               float var20 = var16 * var17;
               long var21 = 46656259779866L;
               if (46656259779866L == 0L) {
                  boolean var36 = false;
               } else {
                  int var37 = var21 < 0L ? -1 : 1;
               }

               if (var20 <= 0.0F) {
                  var6[var8][var5] = 0.0F;
                  long var23 = 46656259779866L;
                  if (46656259779866L == 0L) {
                     boolean var38 = false;
                  } else {
                     int var39 = var23 < 0L ? -1 : 1;
                  }
               } else {
                  float var28 = (var16 * var15 + var17 * var14) / (var14 + var15);
                  float var24 = 3.0F * Math.min(Math.abs(var16), Math.abs(var17));
                  var6[var8][var5] = Math.copySign(Math.min(Math.abs(var28), var24), var28);
               }
               continue;
            }
         }

         var6[var8][var5] = 0.0F;
         long var27 = 46656259779866L;
         if (46656259779866L == 0L) {
            boolean var32 = false;
         } else {
            int var33 = var27 < 0L ? -1 : 1;
         }
      }

      long var26 = 46656259779867L;
      if (46656259779867L == 0L) {
         boolean var40 = false;
      } else {
         int var41 = var26 < 0L ? -1 : 1;
      }
   }

   public static synchronized KnifeTransform.TypeH sheet(String var0) {
      long var1 = 33534988711942L;
      int var3 = (int)((var1 ^ 119466745508075L) >>> 56);
      long var4 = (var1 ^ 119466745508075L) << 8 >>> 8;
      KnifeTransform.TypeH var6 = R.get(var0);
      if (var6 == null) {
         var6 = n((byte)var3, var0, var4);
         R.put(var0, var6);
      }

      return var6;
   }

   public static synchronized void clear() {
      R.clear();
   }

   private static KnifeTransform.TypeH n(byte var0, String var1, long var2) {
      KnifeUpdater2.ensureSourceClassInitialized();
      String var4 = null;

      try {
         if (L != null) {
            Path var5 = Path.of(L, var1 + ".txt");
            long var6 = 126274152122605L;
            if (126274152122605L == 0L) {
               boolean var10000 = false;
            } else {
               int var15 = var6 < 0L ? -1 : 1;
            }

            if (Files.exists(var5)) {
               var4 = Files.readString(var5, StandardCharsets.UTF_8);
            }
         }

         if (var4 == null) {
            try (InputStream var13 = KnifeTransform.class.getResourceAsStream("/assets/purxiy/wield/" + var1 + ".txt")) {
               if (var13 != null) {
                  var4 = new String(var13.readAllBytes(), StandardCharsets.UTF_8);
               }
            }
         }
      } catch (IOException var12) {
         ClientServices.LOGGER.error("Could not read the {} wield sheet", var1, var12);
      }

      KnifeTransform.TypeH var14 = new KnifeTransform.TypeH(var1);
      if (var4 == null) {
         ClientServices.LOGGER.warn("No wield sheet for {}", var1);
         return var14;
      } else {
         try {
            parse(var4, var14);
         } catch (RuntimeException var9) {
            ClientServices.LOGGER.error("The {} wield sheet is broken: {}", var1, var9.getMessage());
         }

         return var14;
      }
   }

   static void parse(String var0, KnifeTransform.TypeH var1) {
      long var2 = 59197084748316L;
      int var4 = (int)((var2 ^ 8912354557851L) >>> 56);
      long var5 = (var2 ^ 8912354557851L) << 8 >>> 8;
      String var7 = null;
      String var8 = null;
      float var9 = 0.0F;
      float var10 = -1.0F;
      float var11 = -1.0F;
      float var12 = -1.0F;
      float var13 = -1.0F;
      float var14 = -1.0F;
      ArrayList var15 = new ArrayList();
      ArrayList var16 = new ArrayList();
      ArrayList var17 = new ArrayList();
      ArrayList var18 = new ArrayList();
      ArrayList var19 = new ArrayList();
      String[] var20 = var0.split("\n");

      for (int var21 = 0; var21 <= var20.length; var21++) {
         String var22 = var21 < var20.length ? var20[var21] : "end";
         int var23 = var22.indexOf(35);
         if (var23 >= 0) {
            var22 = var22.substring(0, var23);
         }

         String var24 = var22.trim();
         if (!var24.isEmpty()) {
            String[] var25 = var24.split("\\s+");
            String var26 = var25[0].toLowerCase(Locale.ROOT);
            boolean var27 = Character.isDigit(var26.charAt(0)) || var26.charAt(0) == '.';
            if (!var27 && var7 != null) {
               if (var15.isEmpty()) {
                  throw new IllegalArgumentException("clip " + var7 + " has no keys");
               }

               float[] var28 = new float[var15.size()];
               boolean[] var29 = new boolean[var15.size()];
               boolean[] var30 = new boolean[var15.size()];

               for (int var31 = 0; var31 < var28.length; var31++) {
                  var28[var31] = (Float)var15.get(var31);
                  var29[var31] = (Boolean)var17.get(var31);
                  var30[var31] = (Boolean)var18.get(var31);
               }

               var19.sort((var0x, var1x) -> Float.compare(((KnifeTransform.Data)var0x).time(), ((KnifeTransform.Data)var1x).time()));
               float var45 = Math.max(var9, var28[var28.length - 1]);
               var1.a
                  .put(
                     var7,
                     new KnifeTransform.TypeU(
                        var7,
                        var8,
                        var45,
                        var10,
                        var11,
                        var12,
                        Math.max(0.0F, var13),
                        var14 < 0.0F ? var45 : var14,
                        var10 >= 0.0F ? var10 + 0.07F : var45,
                        1.0F,
                        var28,
                        (float[][])(var16.toArray(new float[0][])),
                        var29,
                        var30,
                        var19
                     )
                  );
               var7 = null;
            }

            String var42 = var27 ? "key" : var26;
            switch (var42) {
               case "end":
                  break;
               case "grips":
                  var1.k = Float.parseFloat(var25[1]);
                  var1.b = Float.parseFloat(var25[2]);
                  break;
               case "scale":
                  var1.M = Float.parseFloat(var25[1]);
                  break;
               case "set":
                  var1.q.put(var25[1], Float.parseFloat(var25[2]));
                  break;
               case "pose":
                  float[] var44 = new float[8];
                  var44[6] = 1.0F;

                  int var46;
                  for (var46 = 0; 2 + var46 < var25.length && var46 < 6 && O(var25[2 + var46]); var46++) {
                     var44[var46] = Float.parseFloat(var25[2 + var46]);
                  }

                  float[] var32 = null;
                  float[] var33 = null;

                  for (int var50 = 2 + var46; var50 < var25.length; var50++) {
                     if (var25[var50].startsWith("aim=")) {
                        var32 = C(var25[var50].substring(4));
                     } else if (var25[var50].startsWith("edge=")) {
                        var33 = C(var25[var50].substring(5));
                     } else {
                        a(var25[var50], var44, null, (byte)var4, var5, 0.0F, "line " + (var21 + 1));
                     }
                  }

                  if (var32 != null) {
                     aimed(var44, var32, var33, null);
                  } else if (var46 != 6) {
                     throw new IllegalArgumentException("line " + (var21 + 1) + ": a pose is a position and its angles, or a position and aim= edge=");
                  }

                  var1.T.put(var25[1], var44);
                  break;
               case "clip":
                  var7 = var25[1];
                  var9 = Float.parseFloat(var25[2]);
                  var14 = -1.0F;
                  var13 = -1.0F;
                  var12 = -1.0F;
                  var11 = -1.0F;
                  var10 = -1.0F;
                  var8 = null;
                  var15.clear();
                  var16.clear();
                  var17.clear();
                  var18.clear();
                  var19.clear();

                  for (int var47 = 3; var47 < var25.length; var47++) {
                     String var51 = var25[var47];
                     switch (var51) {
                        case "hit":
                           var10 = Float.parseFloat(var25[++var47]);
                           break;
                        case "arc":
                           var11 = Float.parseFloat(var25[++var47]);
                           var12 = Float.parseFloat(var25[++var47]);
                           break;
                        case "flow":
                           var13 = Float.parseFloat(var25[++var47]);
                           var14 = Float.parseFloat(var25[++var47]);
                           break;
                        default:
                           if (!var25[var47].startsWith("label=")) {
                              throw new IllegalArgumentException("line " + (var21 + 1) + ": unknown clip option " + var25[var47]);
                           }

                           var8 = var25[var47].substring(6).replace('_', ' ');
                     }
                  }
                  break;
               case "key":
                  if (var7 == null) {
                     throw new IllegalArgumentException("line " + (var21 + 1) + ": a key outside a clip");
                  }

                  float var34 = Float.parseFloat(var25[0]);
                  float[] var35 = new float[8];
                  int var36;
                  if (var25[1].startsWith("@")) {
                     String[] var53 = var25[1].substring(1).split("\\+", 2);
                     float[] var38 = var1.T.get(var53[0]);
                     if (var38 == null) {
                        throw new IllegalArgumentException("line " + (var21 + 1) + ": no pose " + var53[0]);
                     }

                     System.arraycopy(var38, 0, var35, 0, 8);
                     if (var53.length > 1) {
                        String[] var39 = var53[1].split(",");

                        for (int var40 = 0; var40 < var39.length && var40 < 6; var40++) {
                           var35[var40] += Float.parseFloat(var39[var40]);
                        }
                     }

                     var36 = 2;
                  } else {
                     int var37;
                     for (var37 = 0; 1 + var37 < var25.length && var37 < 6 && O(var25[1 + var37]); var37++) {
                        var35[var37] = Float.parseFloat(var25[1 + var37]);
                     }

                     if (var37 != 3 && var37 != 6) {
                        throw new IllegalArgumentException("line " + (var21 + 1) + ": a key is a position and its angles, or a position and aim= edge=");
                     }

                     if (var37 == 3 && !var16.isEmpty()) {
                        System.arraycopy(var16.getLast(), 3, var35, 3, 3);
                     }

                     var35[6] = var16.isEmpty() ? 1.0F : ((float[])var16.getLast())[6];
                     var35[7] = var16.isEmpty() ? 0.0F : ((float[])var16.getLast())[7];
                     var36 = 1 + var37;
                  }

                  if (var25[1].startsWith("@") && !var16.isEmpty()) {
                     float[] var54 = (float[])var16.getLast();
                     var35[6] = var54[6];
                     var35[7] = var54[7];
                     var35[3] = nearest(var54[3], var35[3]);
                     var35[5] = nearest(var54[5], var35[5]);
                  }

                  boolean var55 = false;
                  boolean var56 = false;
                  float[] var57 = null;
                  float[] var58 = null;

                  for (int var41 = var36; var41 < var25.length; var41++) {
                     if (var25[var41].equals("short")) {
                        var56 = true;
                     } else if (var25[var41].startsWith("aim=")) {
                        var57 = C(var25[var41].substring(4));
                     } else if (var25[var41].startsWith("edge=")) {
                        var58 = C(var25[var41].substring(5));
                     } else {
                        var55 |= a(var25[var41], var35, var19, (byte)var4, var5, var34, "line " + (var21 + 1));
                     }
                  }

                  if (var57 != null) {
                     aimed(var35, var57, var58, var16.isEmpty() ? null : (float[])var16.getLast());
                  }

                  if (!var15.isEmpty() && var34 <= (Float)var15.getLast()) {
                     throw new IllegalArgumentException("line " + (var21 + 1) + ": keys must move on in time");
                  }

                  var18.add(var56 && !var16.isEmpty());
                  var15.add(var34);
                  var16.add(var35);
                  var17.add(var55);
                  break;
               default:
                  throw new IllegalArgumentException("line " + (var21 + 1) + ": unknown line " + var25[0]);
            }
         }
      }
   }

   private static boolean O(String var0) {
      try {
         Float.parseFloat(var0);
         return true;
      } catch (NumberFormatException var2) {
         return false;
      }
   }

   private static float[] C(String var0) {
      String[] var1 = var0.split(",");
      return new float[]{Float.parseFloat(var1[0]), Float.parseFloat(var1[1]), Float.parseFloat(var1[2])};
   }

   static void aimed(float[] var0, float[] var1,  float[] var2,  float[] var3) {
      Vector3f var4 = new Vector3f(var1[0], var1[1], var1[2]).normalize();
      float var5 = (float)Math.toDegrees(Math.asin(Math.clamp(-var4.z, -1.0F, 1.0F)));
      float var6 = (float)Math.toDegrees(Math.atan2(-var4.x, var4.y));
      float var7 = var3 != null ? var3[5] : var0[5];
      if (var2 != null) {
         Quaternionf var8 = KnifeCurve.rotation(var6, var5, 0.0F, new Quaternionf()).invert();
         Vector3f var9 = var8.transform(new Vector3f(var2[0], var2[1], var2[2]));
         var7 = (float)Math.toDegrees(Math.atan2(-var9.z, var9.x));
      }

      if (var3 != null) {
         var6 = nearest(var3[3], var6);
         var7 = nearest(var3[5], var7);
      }

      var0[3] = var6;
      var0[4] = var5;
      var0[5] = var7;
   }

   static float nearest(float var0, float var1) {
      return var1 + 360.0F * Math.round((var0 - var1) / 360.0F);
   }

   private static boolean a(String var0, float[] var1,  List var2, byte var3,  long var4, float var6, String var7) {
      KnifeUpdater2.ensureSourceClassInitialized();
      boolean var8 = var0.equals("stop");
      boolean var9 = false;
      if (var8) {
         return true;
      } else {
         String[] var10 = var0.split("=", 2);
         long var11 = 67954780632454L;
         if (67954780632454L == 0L) {
            boolean var10000 = false;
         } else {
            int var25 = var11 < 0L ? -1 : 1;
         }

         if (var10.length < 2) {
            throw new IllegalArgumentException(var7 + ": unknown option " + var0);
         } else {
            String var14 = var10[0];
            byte var15 = -1;
            switch (var14.hashCode()) {
               case 3282:
                  if (var14.equals("fx")) {
                     var15 = 3;
                  }
                  break;
               case 3317767:
                  if (var14.equals("left")) {
                     var15 = 0;
                  }
                  break;
               case 109526449:
                  if (var14.equals("slide")) {
                     long var23 = 67954780632455L;
                     if (67954780632455L == 0L) {
                        boolean var28 = false;
                     } else {
                        int var29 = var23 < 0L ? -1 : 1;
                     }

                     var15 = 1;
                  }
                  break;
               case 109627663:
                  if (var14.equals("sound")) {
                     long var16 = 67954780632455L;
                     if (67954780632455L == 0L) {
                        boolean var26 = false;
                     } else {
                        int var27 = var16 < 0L ? -1 : 1;
                     }

                     var15 = 2;
                  }
            }

            switch (var15) {
               case 0:
                  var1[6] = Float.parseFloat(var10[1]);
                  break;
               case 1:
                  var1[7] = Float.parseFloat(var10[1]);
                  long var24 = 67954780632455L;
                  if (67954780632455L == 0L) {
                     boolean var32 = false;
                  } else {
                     int var33 = var24 < 0L ? -1 : 1;
                  }
                  break;
               case 2:
               case 3:
                  if (var2 == null) {
                     throw new IllegalArgumentException(var7 + ": " + var10[0] + " belongs to a key");
                  }

                  String[] var18 = var10[1].split("/");
                  long var19 = 67954780632455L;
                  if (67954780632455L == 0L) {
                     boolean var30 = false;
                  } else {
                     int var31 = var19 < 0L ? -1 : 1;
                  }

                  float var21 = var18.length > 1 ? Float.parseFloat(var18[1]) : 1.0F;
                  float var22 = var18.length > 2 ? Float.parseFloat(var18[2]) : 1.0F;
                  var2.add(new KnifeTransform.Data(var6, var10[0], var18[0], var21, var22));
                  break;
               default:
                  throw new IllegalArgumentException(var7 + ": unknown option " + var0);
            }

            return false;
         }
      }
   }

public record Data(float Z, String J, String k, float L, float j) {
      public float time() {
         return this.Z;
      }

      public String kind() {
         return this.J;
      }

      public String name() {
         return this.k;
      }

      public float volume() {
         return this.L;
      }

      public float pitch() {
         return this.j;
      }

      public float floatState43() {
         return this.Z;
      }

      public String stringState45() {
         return this.J;
      }

      public String stringState48() {
         return this.k;
      }

      public float floatState57() {
         return this.L;
      }

      public float floatState38() {
         return this.j;
      }
   }

   public static final class TypeH {
      public final String z;
      public final Map<String, float[]> T = new HashMap<>();
      public final Map<String, KnifeTransform.TypeU> a = new HashMap<>();
      public final Map<String, Float> q = new HashMap<>();
      public float k = 0.07F;
      public float b = 0.22F;
      public float M = 1.0F;
      private static final String c = "ready";

      TypeH(String var1) {
         this.z = var1;
      }

      
      public KnifeTransform.TypeU clip(String var1) {
         return this.a.get(var1);
      }

      public float number(String var1, float var2) {
         Float var3 = this.q.get(var1);
         return var3 != null ? var3 : var2;
      }

      public KnifeCurve pose(String var1, KnifeCurve var2) {
         float[] var3 = this.T.get(var1);
         if (var3 == null) {
            var3 = this.T.get(c);
         }

         return var3 == null ? var2.set(new KnifeCurve()) : var2.set(var3);
      }

      public boolean has(String var1) {
         return this.T.containsKey(var1);
      }

}

   public static final class TypeU {
      public final String o;
      public final float E;
      public final float T;
      public final float R;
      public final float v;
      
      public final String N;
      public final float J;
      public final float t;
      public final float y;
      public final float g;
      final float[] e;
      final float[][] h;
      final float[][] n;
      final boolean[] W;
      public final List<KnifeTransform.Data> U;

      TypeU(
         String var1,
          String var2,
         float var3,
         float var4,
         float var5,
         float var6,
         float[] var7,
         float[][] var8,
         boolean[] var9,
         boolean[] var10,
         List<KnifeTransform.Data> var11
      ) {
         this(var1, var2, var3, var4, var5, var6, 0.0F, var3, var4 >= 0.0F ? var4 + 0.07F : var3, 1.0F, var7, var8, var9, var10, var11);
      }

      TypeU(
         String var1,
          String var2,
         float var3,
         float var4,
         float var5,
         float var6,
         float var7,
         float var8,
         float var9,
         float var10,
         float[] var11,
         float[][] var12,
         boolean[] var13,
         boolean[] var14,
         List<KnifeTransform.Data> var15
      ) {
         long var16 = 54718636289761L;
         long var18 = var16 ^ 30422011118586L;
         this.N = var2;
         this.W = var14;
         this.o = var1;
         this.E = var3;
         this.T = var4;
         this.R = var5;
         this.v = var6;
         this.J = Math.clamp(var7, 0.0F, var3);
         this.t = Math.clamp(var8, this.J, var3);
         this.y = Math.min(var9, var3);
         this.g = var10;
         this.e = var11;
         this.h = var12;
         this.U = List.copyOf(var15);
         this.n = new float[var11.length][8];

         for (int var20 = 0; var20 < 8; var20++) {
            KnifeTransform.T(var11, var12, var13, var18, var20, this.n);
         }
      }

      public KnifeCurve at(float var1, KnifeCurve var2) {
         int var3 = this.e.length;
         if (var3 == 1 || var1 <= this.e[0]) {
            return var2.set(this.h[0]);
         } else if (var1 >= this.e[var3 - 1]) {
            return var2.set(this.h[var3 - 1]);
         } else {
            int var4 = 0;
            int var5 = var3 - 1;

            while (var5 - var4 > 1) {
               int var6 = var4 + var5 >>> 1;
               if (var1 >= this.e[var6]) {
                  var4 = var6;
               } else {
                  var5 = var6;
               }
            }

            int var19 = var4;
            float var7 = this.e[var4 + 1] - this.e[var4];
            float var8 = (var1 - this.e[var4]) / var7;
            float var9 = var8 * var8;
            float var10 = var9 * var8;
            float var11 = 2.0F * var10 - 3.0F * var9 + 1.0F;
            float var12 = var10 - 2.0F * var9 + var8;
            float var13 = -2.0F * var10 + 3.0F * var9;
            float var14 = var10 - var9;

            for (int var15 = 0; var15 < 8; var15++) {
               var2.R[var15] = var11 * this.h[var19][var15]
                  + var12 * var7 * this.n[var19][var15]
                  + var13 * this.h[var19 + 1][var15]
                  + var14 * var7 * this.n[var19 + 1][var15];
            }

            if (this.W[var19 + 1]) {
               float[] var20 = this.h[var19];
               float[] var16 = this.h[var19 + 1];
               Quaternionf var17 = KnifeCurve.rotation(var20[3], var20[4], var20[5], new Quaternionf());
               Quaternionf var18 = KnifeCurve.rotation(var16[3], var16[4], var16[5], new Quaternionf());
               KnifeCurve.angles(var17.slerp(var18, var13), var2.R[3], var2.R[5], var2.R);
            }

            return var2;
         }
      }

      public KnifeCurve first(KnifeCurve var1) {
         return var1.set(this.h[0]);
      }

      public KnifeCurve last(KnifeCurve var1) {
         return var1.set(this.h[this.h.length - 1]);
      }

      public int keys() {
         return this.e.length;
      }
   }
}
