package dev.purxiy.modules.shockwave;

import it.unimi.dsi.fastutil.longs.Long2FloatOpenHashMap;
import java.nio.charset.StandardCharsets;
import net.minecraft.class_1922;
import net.minecraft.class_2248;
import net.minecraft.class_2350;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3532;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2350.class_2351;

final class ShockwaveUpdater {
   private static final int C;
   private static final int e;
   private static final long j;
   private static final float b = Float.NaN;
   private final Long2FloatOpenHashMap K = new Long2FloatOpenHashMap();
   private final class_2339 f = new class_2339();
   private long M;

   ShockwaveUpdater() {
      this.K.defaultReturnValue(-Float.MAX_VALUE);
   }

   void clear() {
      this.K.clear();
   }

   void tick(long var1) {
      if (var1 - this.M > 400000000L) {
         this.M = var1;
         this.K.clear();
      }
   }

   float top(class_1922 var1, int var2, int var3, double var4) {
      long var6 = 114358180375083L;
      long var8 = var6 ^ 2390995140006L;
      int var10 = class_3532.method_15357(var4);
      long var11 = (long)(var2 & 4194303) << 42 | (long)(var3 & 4194303) << 20 | var10 & 1048575;
      float var13 = this.K.get(var11);
      if (var13 != -Float.MAX_VALUE) {
         return var13;
      } else {
         float var14 = this.H(var1, var2, var3, var10, var8);
         this.K.put(var11, var14);
         return var14;
      }
   }

   private float H(class_1922 var1, int var2, int var3, int var4, long var5) {
      ShockwaveModule.ensureSourceClassInitialized();
      boolean var7 = false;

      for (int var8 = var4 + 2; var8 >= var4 - 3; var8--) {
         class_2680 var9 = var1.method_8320(this.f.method_10103(var2, var8, var3));
         long var10 = 116748567025549L;
         int var13 = var10 < 0L ? -1 : 1;
         boolean var13x = var9.method_26215();
         long var14 = 116748567025548L;
         class_265 var26;
         if (var13x) {
            long var17 = 116748567025549L;
            int var17x = var17 < 0L ? -1 : 1;
            var7 = true;
         } else {
            if (!var9.method_26227().method_15769()) {
               return Float.NaN;
            }

            label73: {
               var26 = var9.method_26218(var1, this.f);
               var13x = var26.method_1110();
               long var18 = 116748567025549L;
               int var18x = var18 < 0L ? -1 : 1;
               if (!var13x) {
                  var13x = var9.method_26225();
                  long var20 = 116748567025548L;
                  int var20x = var20 < 0L ? -1 : 1;
                  if (var13x && class_2248.method_9501(var26.method_20538(class_2350.field_11036), class_2350.field_11036)) {
                     var13x = true;
                     break label73;
                  }
               }

               var13x = false;
            }

            long var19 = 116748567025549L;
            int var31 = var19 < 0L ? -1 : 1;
            if (var13x) {
               return var7 ? (float)(var8 + var26.method_1105(class_2351.field_11052)) : Float.NaN;
            }

            if (!var9.method_26220(var1, this.f).method_1110()) {
               return Float.NaN;
            }

            var7 = true;
            long var21 = 116748567025549L;
            int var22 = var21 < 0L ? -1 : 1;
         }
      }

      return Float.NaN;
   }

   static {
      long var0 = 95484438768368L;
      long[] var2 = new long[2];
      int var3 = 0;
      String var4 = "\u001a\u0096Z\u001a´\rêò\fÖ\u0007ñ´\rêó";
      int var5 = "\u001a\u0096Z\u001a´\rêò\fÖ\u0007ñ´\rêó".length();
      byte var6 = 0;

      do {
         int var7 = var6;
         var6 = (byte)(var6 + 8);
         byte[] var8 = var4.substring(var7, var6).getBytes(StandardCharsets.ISO_8859_1);
         var7 = var3++;
         long var9 = (
               (var8[0] & 255L) << 56
                  | (var8[1] & 255L) << 48
                  | (var8[2] & 255L) << 40
                  | (var8[3] & 255L) << 32
                  | (var8[4] & 255L) << 24
                  | (var8[5] & 255L) << 16
                  | (var8[6] & 255L) << 8
                  | var8[7] & 255L
            )
            ^ var0;
         var2[var7] = var9;
      } while (var6 < var5);

      e = (int)var2[1];
      C = (int)var2[0];
      long var12 = 95484166958832L ^ var0;
      j = var12;
   }
}
