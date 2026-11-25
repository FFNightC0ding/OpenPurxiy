package dev.purxiy.modules.creamykeys;

import dev.purxiy.loader.fabric.ClientServices;
import java.util.Arrays;
import java.util.Locale;
import net.minecraft.class_3414;

public final class CreamyKeySounds {
   public static final int d = 12;
   public static final int A = 12;
   public static final int o = 13;
   public static final int Z = 14;
   public static final int n = 3;
   public static final class_3414 E = D("mouse_left", 110599470278268L);
   public static final class_3414 y = D("mouse_right", 110599470278268L);
   public static final class_3414 P = D("mouse_middle", 110599470278268L);
   public static final class_3414 R = D("mouse_left_release", 110599470278268L);
   public static final class_3414 s = D("mouse_right_release", 110599470278268L);
   public static final class_3414 t = D("mouse_middle_release", 110599470278268L);
   public static final class_3414 L = D("scroll_up", 110599470278268L);
   public static final class_3414 S = D("scroll_down", 110599470278268L);
   private static final class_3414[][] h = H(false, 136718518614459L);
   private static final class_3414[][] M = H(true, 136718518614459L);
   private static final String[] b = new String[13];

   public static class_3414 mousePress(int var0, int var1) {
      return var0 == 2 ? P : h[var0 == 1 ? 1 : 0][Math.floorMod(var1, 3)];
   }

   public static class_3414 mouseRelease(int var0, int var1) {
      return var0 == 2 ? t : M[var0 == 1 ? 1 : 0][Math.floorMod(var1, 3)];
   }

   private static class_3414[][] H(boolean var0, long var1) {
      long var3 = 110599470278268L;
      class_3414[][] var5 = new class_3414[2][3];
      int var6 = 0;

      while (var6 < 2) {
         int var7 = 0;

         while (var7 < 3) {
            long var8 = 136718518614458L;
            int var11 = var8 < 0L ? -1 : 1;
            class_3414[] var11x = var5[var6];
            long var12 = 136718518614459L;
            int var15 = var12 < 0L ? -1 : 1;
            String var15x = var6 == 0 ? "left" : "right";
            long var16 = 136718518614459L;
            int var16x = var16 < 0L ? -1 : 1;
            String var19x = var0 ? "_release_" : "_";
            int var20 = var7 + 1;
            var11x[var7] = D("mouse_" + var15x + var19x + var20, var3);
            var7++;
            long var23 = 136718518614458L;
            int var20x = var23 < 0L ? -1 : 1;
         }

         var6++;
         long var25 = 136718518614459L;
         int var231 = var25 < 0L ? -1 : 1;
      }

      return var5;
   }

   private CreamyKeySounds() {
   }

   private static class_3414 D(String var0, long var1) {
      return class_3414.method_47908(ClientServices.id("creamy_keys." + var0));
   }

   public static enum Style {
      q(12989098271824L, "Creamy", "creamy", "Soft & smooth"),
      Q(12989098271824L, "Clacky", "clacky", "Crisp & bright"),
      M(12989098271824L, "Thocky", "thocky", "Deep & rounded"),
      b(12989098271824L, "Honey Creamy", "honey_creamy", "Warm & mellow");

      public final String F;
      public final String p;
      private final class_3414[] W;
      private final class_3414[] U;
      public static int r;
      private static final long[] d = new long[]{-2084103950140876560L, 7459538802385835683L};

      private Style(long nullxx, String nullxxx, String nullxxxx, String nullxxxxx) {
         long var8 = 110599470278268L;
         this.W = new class_3414[14];
         this.U = new class_3414[14];
         this.F = nullxxx;
         this.p = nullxxxxx;

         for (int var10 = 0; var10 < 14; var10++) {
            String var11 = String.format(Locale.ROOT, "%02d", var10 + 1);
            this.W[var10] = CreamyKeySounds.D(nullxxxx + ".press_" + var11, var8);
            this.U[var10] = CreamyKeySounds.D(nullxxxx + ".release_" + var11, var8);
            long var12 = 12989098271823L;
            int var14 = var12 < 0L ? -1 : 1;
         }
      }

      public class_3414 press(int var1) {
         return this.W[Math.floorMod(var1, 14)];
      }

      public class_3414 release(int var1) {
         return this.U[Math.floorMod(var1, 14)];
      }

      public static CreamyKeySounds.Style fromLabel(String var0) {
         return Arrays.stream(values()).filter(var1 -> var1.F.equals(var0)).findFirst().orElse(q);
      }
   }
}
