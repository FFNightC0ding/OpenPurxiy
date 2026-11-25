package dev.purxiy.modules.afterglow;

import dev.purxiy.render.RenderPipelines;
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
import net.minecraft.class_4608;
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

final class Afterglowclass_11659 implements class_11659 {
   private final class_11659 L;
   private final class_11785 Z;
   private final AfterglowSupport2 t;

   Afterglowclass_11659(class_11659 var1, class_11785 var2, AfterglowSupport2 var3) {
      this.L = var1;
      this.Z = var2;
      this.t = var3;
   }

   public class_11785 method_73529(int var1) {
      return new Afterglowclass_11659(this.L, this.L.method_73529(var1), this.t);
   }

   private boolean c(long var1) {
      AfterglowRenderer.ensureSourceClassInitialized();
      return this.t.v > 0.45F;
   }

   public <S> void method_73490(
      class_3879<? super S> var1, S var2, class_4587 var3, class_1921 var4, int var5, int var6, int var7, class_1058 var8, int var9, class_11792 var10
   ) {
      class_1921 var11 = this.t.swap(var4);
      if (var11 != null) {
         this.t.capture(var1, var2, var3, RenderPipelines.get().frame());
         this.Z.method_73490(var1, var2, var3, var11, var5, class_4608.field_21444, var7, var8, 0, null);
      }
   }

   public void method_73494(
      class_630 var1, class_4587 var2, class_1921 var3, int var4, int var5, class_1058 var6, boolean var7, boolean var8, int var9, class_11792 var10, int var11
   ) {
      class_1921 var12 = this.t.swap(var3);
      if (var12 != null) {
         this.Z.method_73494(var1, var2, var12, var4, class_4608.field_21444, var6, var7, false, var9, null, 0);
      }
   }

   public void method_73480(class_4587 var1, class_811 var2, int var3, int var4, int var5, int[] var6, List<class_777> var7, class_1921 var8, class_10445 var9) {
      class_1921 var10 = this.t.swap(var8);
      if (var10 != null) {
         this.Z.method_73480(var1, var2, var3, class_4608.field_21444, 0, var6, var7, var10, class_10445.field_55341);
      }
   }

   public void method_73484(class_4587 var1, class_1921 var2, class_1087 var3, float var4, float var5, float var6, int var7, int var8, int var9) {
      class_1921 var10 = this.t.swap(var2);
      if (var10 != null) {
         this.Z.method_73484(var1, var10, var3, var4, var5, var6, var7, class_4608.field_21444, 0);
      }
   }

   public void method_73483(class_4587 var1, class_1921 var2, class_11660 var3) {
      class_1921 var4 = this.t.swap(var2);
      if (var4 != null) {
         this.Z.method_73483(var1, var4, var3);
      }
   }

   public void method_73481(class_4587 var1, class_2680 var2, int var3, int var4, int var5) {
      long var6 = 90634535734752L;
      long var8 = var6 ^ 48267267428544L;
      if (!this.c(var8)) {
         this.Z.method_73481(var1, var2, var3, var4, 0);
      }
   }

   public void method_73485(class_4587 var1, class_11791 var2) {
      long var3 = 139853943967250L;
      long var5 = var3 ^ 7396717777714L;
      if (!this.c(var5)) {
         this.Z.method_73485(var1, var2);
      }
   }

   public void method_73479(class_4587 var1, float var2, List<class_11680> var3) {
      this.Z.method_73479(var1, var2, this.t.shadow(var3));
   }

   public void method_73482(class_4587 var1, class_243 var2, int var3, class_2561 var4, boolean var5, int var6, double var7, class_12075 var9) {
   }

   public void method_73478(class_4587 var1, float var2, float var3, class_5481 var4, boolean var5, class_6415 var6, int var7, int var8, int var9, int var10) {
   }

   public void method_73488(class_4587 var1, class_10017 var2, Quaternionf var3) {
      this.Z.method_73488(var1, var2, var3);
   }

   public void method_73486(class_4587 var1, class_10018 var2) {
      this.Z.method_73486(var1, var2);
   }

   public void method_74315(class_11947 var1) {
      this.Z.method_74315(var1);
   }
}
