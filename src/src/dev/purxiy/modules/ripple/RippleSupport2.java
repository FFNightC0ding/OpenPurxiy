// Sourced By DevFFNightmare
package dev.purxiy.modules.ripple;

import dev.purxiy.loader.fabric.mixin.ModelPartAccessor;
import dev.purxiy.loader.fabric.mixin.RenderTypeAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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

final class RippleSupport2 {
   static final int R = 8;
   private float[] f = new float[32768];
   private int[] Z = new int[4096];
   private int j;
   private final List<RippleSupport2.Data> N = new ArrayList<>();
   private int O = -1;
   
   private class_12337 K;
   private int W;
   private final Vector3f T = new Vector3f();
   private final Vector3f G = new Vector3f();
   private static final String a = "Sampler0";



   void reset() {
      this.j = 0;
      this.N.clear();
      this.K = null;
      this.O = -1;
   }

   void body(int var1) {
      this.O = var1;
   }

   List<RippleSupport2.Data> runs() {
      long var1 = 44346777933990L;
      long var3 = var1 ^ 55364076277173L;
      int var5 = (int)((var1 ^ 55364076277173L) >>> 32);
      int var6 = (int)((var1 ^ 55364076277173L) << 32 >>> 48);
      int var7 = (int)(var3 << 48 >>> 48);
      this.W(var5, var6, (short)var7);
      return this.N;
   }

   float[] data() {
      return this.f;
   }

   int[] slots() {
      return this.Z;
   }

   int vertices() {
      return this.j;
   }

   void quads(class_4665 var1, List<class_777> var2, class_1921 var3) {
      long var4 = 130659357821598L;
      long var6 = var4 ^ 79669092604940L;
      int var8 = (int)((var4 ^ 79669092604940L) >>> 48);
      int var9 = (int)((var4 ^ 79669092604940L) << 16 >>> 48);
      int var10 = (int)(var6 << 32 >>> 32);
      long var11 = var4 ^ 4327991967350L;
      if (this.O >= 0 && !var2.isEmpty() && this.i((short)var8, var3, (char)var9, var10)) {
         Matrix4f var13 = var1.method_23761();

         for (class_777 var15 : var2) {
            var1.method_56821(var15.comp_3723().method_68072(), this.G);

            for (int var16 = 0; var16 < 4; var16++) {
               Vector3fc var17 = var15.method_76648(var16);
               var13.transformPosition(var17.x(), var17.y(), var17.z(), this.T);
               long var18 = var15.method_76649(var16);
               this.Z(this.T, class_5611.method_76641(var18), class_5611.method_76642(var18), var11, this.G);
            }
         }
      }
   }

   void part(class_630 var1, class_4587 var2, class_1921 var3,  class_1058 var4) {
      long var5 = 64588577788565L;
      long var7 = var5 ^ 4530457969671L;
      int var9 = (int)((var5 ^ 4530457969671L) >>> 48);
      int var10 = (int)((var5 ^ 4530457969671L) << 16 >>> 48);
      int var11 = (int)(var7 << 32 >>> 32);
      long var12 = var5 ^ 124934187239262L;
      if (this.O >= 0 && this.i((short)var9, var3, (char)var10, var11)) {
         this.o(var12, var1, var2, var4);
      }
   }

   private void o(long var1, class_630 var3,  class_4587 var4, class_1058 var5) {
      long var6 = var1 ^ 68326522216739L;
      long var8 = var1 ^ 0L;
      RippleRenderer.ensureSourceClassInitialized();
      if (var3.field_3665) {
         ModelPartAccessor var10 = (ModelPartAccessor)(Object)(var3);
         if (var1 > 1L) {
            var4.method_22903();
            var3.method_22703(var4);
         }

         if (!var3.field_38456) {
            class_4665 var11 = var4.method_23760();
            Matrix4f var12 = var11.method_23761();

            label85:
            for (Object var14 : var10.purxiy$cubes()) {
               class_628 var15 = (class_628)var14;
               class_593[] var16 = var15.field_3649;
               int var17 = var16.length;

               label81:
               while (true) {
                  byte var18 = 0;

                  label79:
                  while (true) {
                     int var19 = var18;

                     while (var19 < var17) {
                        class_593 var20 = var16[var19];
                        var11.method_56821(var20.comp_3185(), this.G);
                        class_618[] var21 = var20.comp_3184();
                        int var22 = var21.length;
                        var18 = 0;
                        if (var1 < 1L) {
                           continue label79;
                        }

                        int var23 = 0;

                        while (var23 < var22) {
                           class_618 var24 = var21[var23];
                           var12.transformPosition(var24.method_74250(), var24.method_74251(), var24.method_74252(), this.T);
                           if (var1 < 0L) {
                              continue label85;
                           }

                           float var25 = var24.comp_3187();
                           float var26 = var24.comp_3188();
                           if (var1 > 1L) {
                              label70: {
                                 class_1058 var27 = var5;
                                 if (var1 >= 1L) {
                                    if (var5 == null) {
                                       break label70;
                                    }

                                    var25 = var5.method_4580(var25);
                                    var27 = var5;
                                 }

                                 var26 = var27.method_4570(var26);
                              }

                              this.Z(this.T, var25, var26, var6, this.G);
                              var23++;
                           }
                        }

                        var19++;
                        if (var1 < 0L) {
                           continue label81;
                        }
                     }

                     if (var1 >= 0L) {
                     }
                     continue label85;
                  }
               }
            }
         }

         Iterator var28 = var10.purxiy$children().values().iterator();

         while (true) {
            Object var29;
            if (var28.hasNext()) {
               var29 = var28.next();
            } else {
               var29 = var4;
               if (var1 >= 0L) {
                  var4.method_22909();
                  return;
               }
            }

            class_630 var30 = (class_630)var29;
            if (var1 > 0L) {
               this.o(var8, var30, var4, var5);
            }
         }
      }
   }

   private boolean i(short var1, class_1921 var2, char var3, int var4) {
      long var5 = 68860798228114L;
      long var7 = var5 ^ 40337703781249L;
      int var9 = (int)((var5 ^ 40337703781249L) >>> 32);
      int var10 = (int)((var5 ^ 40337703781249L) << 32 >>> 48);
      int var11 = (int)(var7 << 48 >>> 48);
      RippleRenderer.ensureSourceClassInitialized();
      class_12337 var12 = ((RenderTypeAccessor)var2).purxiy$setup().method_75926().get("Sampler0");
      if (var12 == null) {
         return false;
      } else if (this.K != null && this.K.comp_5226() == var12.comp_5226()) {
         return true;
      } else {
         this.W(var9, var10, (short)var11);
         this.K = var12;
         this.W = this.j;
         return true;
      }
   }

   private void W(int var1, int var2, short var3) {
      RippleRenderer.ensureSourceClassInitialized();
      if (this.K != null && this.j > this.W) {
         this.N.add(new RippleSupport2.Data(this.K, this.W, this.j - this.W));
      }

      this.K = null;
   }

   private void Z(Vector3f var1, float var2, float var3, long var4, Vector3f var6) {
      RippleRenderer.ensureSourceClassInitialized();
      byte var7 = 0;
      int var8 = this.j;
      int var9 = var7;
      if (var4 > 0L) {
         var9 = this.Z.length;
      }

      if (var4 >= 0L) {
         if (this.j == var9) {
            this.Z = Arrays.copyOf(this.Z, this.Z.length * 2);
            this.f = Arrays.copyOf(this.f, this.f.length * 2);
         }

         var8 = this.j;
         var9 = 8;
      }

      int var10 = var8 * var9;
      this.f[var10] = var1.x;
      this.f[var10 + 1] = var1.y;
      this.f[var10 + 2] = var1.z;
      this.f[var10 + 3] = var2;
      this.f[var10 + 4] = var3;
      this.f[var10 + 5] = var6.x;
      this.f[var10 + 6] = var6.y;
      this.f[var10 + 7] = var6.z;
      this.Z[this.j] = this.O;
      this.j++;
   }

record Data(class_12337 U, int v, int z) {
      public class_12337 texture() {
         return this.U;
      }

      public int first() {
         return this.v;
      }

      public int count() {
         return this.z;
      }

      public class_12337 class_12247_class_12337State6() {
         return this.U;
      }

      public int integerState4() {
         return this.v;
      }

      public int integerState19() {
         return this.z;
      }
   }
}
