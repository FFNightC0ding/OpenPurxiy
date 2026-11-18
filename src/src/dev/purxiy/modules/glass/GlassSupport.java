// Sourced By DevFFNightmare
package dev.purxiy.modules.glass;

import dev.purxiy.loader.fabric.mixin.ModelPartAccessor;
import dev.purxiy.loader.fabric.mixin.RenderTypeAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.class_1058;
import net.minecraft.class_1921;
import net.minecraft.class_4587;
import net.minecraft.class_5611;
import net.minecraft.class_630;
import net.minecraft.class_777;
import net.minecraft.class_12247.class_12337;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_630.class_593;
import net.minecraft.class_630.class_618;
import net.minecraft.class_630.class_628;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;

final class GlassSupport {
   static final int E = 12;
   static final int L = 1;
   static final int p = 2;
   static final int B = 4;
   private float[] H = new float[49152];
   private int[] K = new int[4096];
   private int[] k = new int[4096];
   private int m;
   private final List<GlassSupport.Data> C = new ArrayList<>();
   
   private class_12337 b;
   private int X;
   private final Vector3f d = new Vector3f();
   private final Vector3f l = new Vector3f();
   private final float[] g = new float[8];
   private final float[] u = new float[12];
   private static final String a = "Sampler0";



   void reset() {
      this.m = 0;
      this.C.clear();
      this.b = null;
   }

   boolean isEmpty() {
      return this.m == 0;
   }

   List<GlassSupport.Data> runs() {
      long var1 = 57054666973045L;
      long var3 = var1 ^ 113123414940351L;
      this.p(var3);
      return this.C;
   }

   float[] data() {
      return this.H;
   }

   int[] lights() {
      return this.K;
   }

   int[] meta() {
      return this.k;
   }

   int vertices() {
      return this.m;
   }

   void quads(class_4665 var1, List<class_777> var2, class_1921 var3, int var4, int var5, int var6) {
      long var7 = 119064841026950L;
      long var9 = var7 ^ 137943632398000L;
      long var11 = var7 ^ 103762018208713L;
      if (!var2.isEmpty() && this.U(var11, var3)) {
         Matrix4f var13 = var1.method_23761();

         for (class_777 var15 : var2) {
            var1.method_56821(var15.comp_3723().method_68072(), this.l);

            for (int var16 = 0; var16 < 4; var16++) {
               Vector3fc var17 = var15.method_76648(var16);
               var13.transformPosition(var17.x(), var17.y(), var17.z(), this.d);
               this.u[var16 * 3] = this.d.x;
               this.u[var16 * 3 + 1] = this.d.y;
               this.u[var16 * 3 + 2] = this.d.z;
               long var18 = var15.method_76649(var16);
               this.g[var16 * 2] = class_5611.method_76641(var18);
               this.g[var16 * 2 + 1] = class_5611.method_76642(var18);
            }

            int var20 = var6 | 4;
            if (edgeStrip(this.g, var15.comp_3724())) {
               var20 |= 1;
            }

            this.Y(this.u, var9, this.g, this.l, var4, var5, var20);
         }
      }
   }

   static boolean edgeStrip(float[] var0, class_1058 var1) {
      int var2 = var1.method_45851().method_45807();
      int var3 = var1.method_45851().method_45815();
      float var4 = Math.abs(var1.method_4577() - var1.method_4594()) / Math.max(1, var2);
      float var5 = Math.abs(var1.method_4575() - var1.method_4593()) / Math.max(1, var3);
      return edgeStrip(var0, var4, var5);
   }

   static boolean edgeStrip(float[] var0, float var1, float var2) {
      float var3 = Math.min(Math.min(var0[0], var0[2]), Math.min(var0[4], var0[6]));
      float var4 = Math.max(Math.max(var0[0], var0[2]), Math.max(var0[4], var0[6]));
      float var5 = Math.min(Math.min(var0[1], var0[3]), Math.min(var0[5], var0[7]));
      float var6 = Math.max(Math.max(var0[1], var0[3]), Math.max(var0[5], var0[7]));
      return var4 - var3 < var1 * 1.5F || var6 - var5 < var2 * 1.5F;
   }

   void part(class_630 var1, class_4587 var2, class_1921 var3,  class_1058 var4, int var5, int var6, int var7, Set<class_630> var8) {
      long var9 = 6023474121718L;
      long var11 = var9 ^ 60943316319673L;
      long var13 = var9 ^ 54827609460357L;
      if (this.U(var11, var3)) {
         this.I(var1, var2, var13, var4, var5, var6, var7, var8);
      }
   }

   private void I(class_630 var1, class_4587 var2,  long var3, class_1058 var5, int var6, int var7, int var8, Set var9) {
      long var11 = var3 ^ 41350059671109L;
      long var13 = var3 ^ 0L;
      GlassRenderer4.ensureSourceClassInitialized();
      byte var15 = 0;
      if (var1.field_3665 && !var9.contains(var1)) {
         ModelPartAccessor var16 = (ModelPartAccessor)(Object)(var1);
         if (var3 > 0L) {
            var2.method_22903();
            var1.method_22703(var2);
         }

         if (!var1.field_38456) {
            class_4665 var17 = var2.method_23760();
            Matrix4f var18 = var17.method_23761();

            label104:
            for (class_628 var20 : var16.purxiy$cubes()) {
               class_593[] var21 = var20.field_3649;
               int var22 = var21.length;

               label102:
               while (true) {
                  int var23 = 0;

                  label98:
                  while (true) {
                     int var24 = var23;

                     label94:
                     while (true) {
                        int var25 = var22;

                        byte var10;
                        class_618[] var26;
                        while (true) {
                           if (var24 >= var25) {
                              if (var3 >= 1L) {
                                 continue label104;
                              }
                              continue label102;
                           }

                           class_593 var27 = var21[var23];
                           var10 = var15;
                           var26 = var27.comp_3184();
                           if (var3 < 0L) {
                              break;
                           }

                           var24 = var26.length;
                           var25 = 4;
                           if (var3 > 0L) {
                              if (var24 != 4) {
                                 if (var3 <= 1L) {
                                    continue label98;
                                 }
                                 break label94;
                              }

                              var17.method_56821(var27.comp_3185(), this.l);
                              var10 = 0;
                              break;
                           }
                        }

                        int var35 = var10;

                        while (var35 < 4) {
                           class_618 var28 = var26[var35];
                           var18.transformPosition(var28.method_74250(), var28.method_74251(), var28.method_74252(), this.d);
                           this.u[var35 * 3] = this.d.x;
                           var24 = var15;
                           this.u[var35 * 3 + 1] = this.d.y;
                           this.u[var35 * 3 + 2] = this.d.z;
                           if (var3 <= 1L) {
                              continue label94;
                           }

                           float var29 = var28.comp_3187();
                           float var30 = var28.comp_3188();
                           if (var3 > 1L) {
                              label88: {
                                 class_1058 var31 = var5;
                                 if (var3 > 1L) {
                                    if (var5 == null) {
                                       break label88;
                                    }

                                    var29 = var5.method_4580(var29);
                                    var31 = var5;
                                 }

                                 var30 = var31.method_4570(var30);
                              }

                              this.g[var35 * 2] = var29;
                              this.g[var35 * 2 + 1] = var30;
                              var35++;
                           }
                        }

                        if (var3 < 0L) {
                           continue label102;
                        }

                        this.Y(this.u, var11, this.g, this.l, var6, var7, var8);
                        break;
                     }

                     var23++;
                  }
               }
            }
         }

         Iterator var32 = var16.purxiy$children().values().iterator();

         while (true) {
            Object var33;
            if (var32.hasNext()) {
               var33 = var32.next();
            } else {
               var33 = var2;
               if (var3 >= 1L) {
                  var2.method_22909();
                  return;
               }
            }

            class_630 var34 = (class_630)var33;
            if (var3 > 0L) {
               this.I(var34, var2, var13, var5, var6, var7, var8, var9);
            }
         }
      }
   }

   private boolean U(long var1, class_1921 var3) {
      long var4 = 93486604797386L;
      GlassRenderer4.ensureSourceClassInitialized();
      class_12337 var6 = ((RenderTypeAccessor)var3).purxiy$setup().method_75926().get("Sampler0");
      if (var6 == null) {
         return false;
      } else {
         if (this.b != null) {
            long var7 = 55074561816143L;
            if (55074561816143L == 0L) {
               boolean var10000 = false;
            } else {
               int var10 = var7 < 0L ? -1 : 1;
            }

            if (this.b.comp_5226() == var6.comp_5226()) {
               return true;
            }
         }

         this.p(var4);
         this.b = var6;
         this.X = this.m;
         return true;
      }
   }

   private void p(long var1) {
      GlassRenderer4.ensureSourceClassInitialized();
      if (this.b != null) {
         long var3 = 93486604797385L;
         if (93486604797385L == 0L) {
            boolean var10000 = false;
         } else {
            int var6 = var3 < 0L ? -1 : 1;
         }

         if (this.m > this.X) {
            this.C.add(new GlassSupport.Data(this.b, this.X, this.m - this.X));
         }
      }

      this.b = null;
   }

   private void Y(float[] var1, long var2, float[] var4, Vector3f var5, int var6, int var7, int var8) {
      float var9 = Math.min(Math.min(var4[0], var4[2]), Math.min(var4[4], var4[6]));
      GlassRenderer4.ensureSourceClassInitialized();
      float var10 = Math.max(Math.max(var4[0], var4[2]), Math.max(var4[4], var4[6]));
      float var11 = Math.min(Math.min(var4[1], var4[3]), Math.min(var4[5], var4[7]));
      float var12 = Math.max(Math.max(var4[1], var4[3]), Math.max(var4[5], var4[7]));
      int var13 = this.m;
      int var14 = 4;
      if (var2 >= 0L) {
         var13 = this.m + 4;
         var14 = this.k.length;
      }

      if (var2 >= 0L) {
         if (var13 > var14) {
            int var15 = this.k.length * 2;
            this.H = Arrays.copyOf(this.H, var15 * 12);
            this.K = Arrays.copyOf(this.K, var15);
            this.k = Arrays.copyOf(this.k, var15);
         }

         var13 = var7 & 255;
         var14 = (var8 & 255) << 8;
      }

      int var18 = var13 | var14;

      for (int var16 = 0; var16 < 4; var16++) {
         int var17 = this.m * 12;
         this.H[var17] = var1[var16 * 3];
         this.H[var17 + 1] = var1[var16 * 3 + 1];
         this.H[var17 + 2] = var1[var16 * 3 + 2];
         this.H[var17 + 3] = var4[var16 * 2];
         this.H[var17 + 4] = var4[var16 * 2 + 1];
         this.H[var17 + 5] = var5.x;
         this.H[var17 + 6] = var5.y;
         this.H[var17 + 7] = var5.z;
         this.H[var17 + 8] = var9;
         this.H[var17 + 9] = var11;
         this.H[var17 + 10] = var10;
         this.H[var17 + 11] = var12;
         this.K[this.m] = var6;
         this.k[this.m] = var18;
         this.m++;
      }
   }

record Data(class_12337 Z, int q, int r) {
      public class_12337 texture() {
         return this.Z;
      }

      public int first() {
         return this.q;
      }

      public int count() {
         return this.r;
      }

      public class_12337 class_12247_class_12337State2() {
         return this.Z;
      }

      public int integerState11() {
         return this.q;
      }

      public int integerState12() {
         return this.r;
      }
   }
}
