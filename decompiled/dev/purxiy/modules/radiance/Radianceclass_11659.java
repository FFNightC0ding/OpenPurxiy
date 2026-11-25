package dev.purxiy.modules.radiance;

import java.util.List;
import net.minecraft.class_10017;
import net.minecraft.class_1058;
import net.minecraft.class_1087;
import net.minecraft.class_11659;
import net.minecraft.class_11785;
import net.minecraft.class_11791;
import net.minecraft.class_12075;
import net.minecraft.class_1921;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_3879;
import net.minecraft.class_4587;
import net.minecraft.class_5481;
import net.minecraft.class_630;
import net.minecraft.class_777;
import net.minecraft.class_811;
import net.minecraft.class_10017.class_10018;
import net.minecraft.class_10017.class_11680;
import net.minecraft.class_10444.class_10445;
import net.minecraft.class_11659.class_11660;
import net.minecraft.class_11659.class_11947;
import net.minecraft.class_11683.class_11792;
import net.minecraft.class_327.class_6415;
import org.joml.Quaternionf;

final class Radianceclass_11659 implements class_11659 {
   private final class_11659 F;
   private final class_11785 S;
   private final Radianceclass_11659.Listener u;

   Radianceclass_11659(class_11659 var1, class_11785 var2, Radianceclass_11659.Listener var3) {
      this.F = var1;
      this.S = var2;
      this.u = var3;
   }

   public class_11785 method_73529(int var1) {
      return new Radianceclass_11659(this.F, this.F.method_73529(var1), this.u);
   }

   private int O(long var1, int var3, class_1921 var4) {
      RadianceModule.ensureSourceClassInitialized();
      return var3 == 0 && !var4.method_24295() ? this.u.marker() : var3;
   }

   public <S> void method_73490(
      class_3879<? super S> var1, S var2, class_4587 var3, class_1921 var4, int var5, int var6, int var7, class_1058 var8, int var9, class_11792 var10
   ) {
      long var11 = 121264936568594L;
      long var13 = var11 ^ 106843691336059L;
      int var15 = this.O(var13, var9, var4);
      if (var15 != var9) {
         this.u.model(var1, var3);
      }

      this.S.method_73490(var1, var2, var3, var4, var5, var6, var7, var8, var15, var10);
   }

   public void method_73494(
      class_630 var1, class_4587 var2, class_1921 var3, int var4, int var5, class_1058 var6, boolean var7, boolean var8, int var9, class_11792 var10, int var11
   ) {
      long var12 = 42375817047499L;
      long var14 = var12 ^ 46094372744098L;
      int var16 = this.O(var14, var11, var3);
      if (var16 != var11) {
         this.u.part(var1, var2);
      }

      this.S.method_73494(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var16);
   }

   public void method_73480(class_4587 var1, class_811 var2, int var3, int var4, int var5, int[] var6, List<class_777> var7, class_1921 var8, class_10445 var9) {
      long var10 = 10514361611060L;
      long var12 = var10 ^ 7655208449373L;
      int var14 = this.O(var12, var5, var8);
      if (var14 != var5) {
         this.u.quads(var7, var1);
      }

      this.S.method_73480(var1, var2, var3, var4, var14, var6, var7, var8, var9);
   }

   public void method_73484(class_4587 var1, class_1921 var2, class_1087 var3, float var4, float var5, float var6, int var7, int var8, int var9) {
      long var10 = 45194094407702L;
      long var12 = var10 ^ 42317721532031L;
      int var14 = this.O(var12, var9, var2);
      if (var14 != var9) {
         this.u.block(var1);
      }

      this.S.method_73484(var1, var2, var3, var4, var5, var6, var7, var8, var14);
   }

   public void method_73481(class_4587 var1, class_2680 var2, int var3, int var4, int var5) {
      int var6 = var5 != 0 ? var5 : this.u.marker();
      if (var6 != var5) {
         this.u.block(var1);
      }

      this.S.method_73481(var1, var2, var3, var4, var6);
   }

   public void method_73485(class_4587 var1, class_11791 var2) {
      this.S.method_73485(var1, var2);
   }

   public void method_73483(class_4587 var1, class_1921 var2, class_11660 var3) {
      this.S.method_73483(var1, var2, var3);
   }

   public void method_73479(class_4587 var1, float var2, List<class_11680> var3) {
      this.S.method_73479(var1, var2, var3);
   }

   public void method_73482(class_4587 var1, class_243 var2, int var3, class_2561 var4, boolean var5, int var6, double var7, class_12075 var9) {
      this.S.method_73482(var1, var2, var3, var4, var5, var6, var7, var9);
   }

   public void method_73478(class_4587 var1, float var2, float var3, class_5481 var4, boolean var5, class_6415 var6, int var7, int var8, int var9, int var10) {
      this.S.method_73478(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
   }

   public void method_73488(class_4587 var1, class_10017 var2, Quaternionf var3) {
      this.S.method_73488(var1, var2, var3);
   }

   public void method_73486(class_4587 var1, class_10018 var2) {
      this.S.method_73486(var1, var2);
   }

   public void method_74315(class_11947 var1) {
      this.S.method_74315(var1);
   }

   interface Listener {
      int marker();

      default void model(class_3879<?> var1, class_4587 var2) {
      }

      default void part(class_630 var1, class_4587 var2) {
      }

      default void quads(List<class_777> var1, class_4587 var2) {
      }

      default void block(class_4587 var1) {
      }
   }
}
