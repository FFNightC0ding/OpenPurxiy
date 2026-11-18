// Sourced By DevFFNightmare
package dev.purxiy.modules.home;

import java.io.InputStream;
import java.util.Arrays;
import net.minecraft.class_1011;
import net.minecraft.class_2960;
import net.minecraft.class_310;

final class HomeLoader {
   static final int w = 12;
   static final int R = 4;
   static final int l = 26;
   static final float z = 0.6F;
   private final boolean[] U = new boolean[65536];
   private boolean A;
   private int Z = Integer.MIN_VALUE;
   private static final String a = "textures/environment/clouds.png";



   void load() {
      this.A = false;
      this.Z = Integer.MIN_VALUE;
      class_310 var1 = class_310.method_1551();

      try (
         InputStream var2 = var1.method_1478().open(class_2960.method_60656("textures/environment/clouds.png"));
         class_1011 var3 = class_1011.method_4309(var2);
      ) {
         int var4 = var3.method_4307();
         int var5 = var3.method_4323();

         for (int var6 = 0; var6 < 256; var6++) {
            for (int var7 = 0; var7 < 256; var7++) {
               int var8 = var3.method_61940(var7 * var4 / 256, var6 * var5 / 256);
               this.U[var7 + var6 * 256] = var8 >>> 24 > 127;
            }
         }

         this.A = true;
      } catch (Exception var13) {
         Arrays.fill(this.U, false);
      }
   }

   private boolean q(int var1, int var2, char var3, int var4, int var5) {
      return this.U[Math.floorMod(var1, 256) + Math.floorMod(var2, 256) * 256];
   }

   static int windowFor(float var0) {
      return (int)Math.floor(var0 / 12.0F) - 26;
   }

   boolean needsBuild(float var1) {
      return this.A && windowFor(var1) != this.Z;
   }

   
   HomeCurve build(float var1, float var2) {
      long var3 = 132602790754614L;
      long var5 = var3 ^ 101648009700233L;
      int var7 = (int)((var3 ^ 101648009700233L) >>> 48);
      int var8 = (int)((var3 ^ 101648009700233L) << 16 >>> 32);
      int var9 = (int)(var5 << 48 >>> 48);
      if (!this.A) {
         return null;
      } else {
         int var10 = windowFor(var1);
         this.Z = var10;
         HomeCurve var11 = new HomeCurve(1500);

         for (int var12 = -26; var12 <= 26; var12++) {
            for (int var13 = var10; var13 <= var10 + 52; var13++) {
               if (this.q(var13, var12, (char)var7, var8, var9)) {
                  byte var14 = 3;
                  if (!this.q(var13, var12 - 1, (char)var7, var8, var9)) {
                     var14 = (byte)(var14 | 4);
                  }

                  if (!this.q(var13, var12 + 1, (char)var7, var8, var9)) {
                     var14 = (byte)(var14 | 8);
                  }

                  if (!this.q(var13 - 1, var12, (char)var7, var8, var9)) {
                     var14 = (byte)(var14 | 16);
                  }

                  if (!this.q(var13 + 1, var12, (char)var7, var8, var9)) {
                     var14 = (byte)(var14 | 32);
                  }

                  float var15 = var13 * 12;
                  float var16 = var12 * 12;
                  var11.box(var15, var2, var16, var15 + 12.0F, var2 + 4.0F, var16 + 12.0F, var14, 247);
               }
            }
         }

         return var11;
      }
   }

}
