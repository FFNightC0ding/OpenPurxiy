// Sourced By DevFFNightmare
package dev.purxiy.modules.glass;

import dev.purxiy.loader.fabric.mixin.GlassRenderTypeAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import net.minecraft.class_10017;
import net.minecraft.class_1058;
import net.minecraft.class_1087;
import net.minecraft.class_11515;
import net.minecraft.class_11659;
import net.minecraft.class_11785;
import net.minecraft.class_11791;
import net.minecraft.class_12075;
import net.minecraft.class_1920;
import net.minecraft.class_1921;
import net.minecraft.class_2338;
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

final class Glassclass_11659 implements class_11659 {
   private static final float g = 0.55F;
   private final class_11659 K;
   private final class_11785 R;
   private final GlassSupport S;
   private final int n;
   private final boolean L;
   private final boolean u;
   private final boolean t;
   private final Set<class_630> B;
   private static final class_11659 U = new class_11659() {
      @Override
      public class_11785 method_73529(int var1) {
         return this;
      }

      @Override
      public <S> void method_73490(
         class_3879<? super S> var1,
         S var2,
         class_4587 var3,
         class_1921 var4,
         int var5,
         int var6,
         int var7,
          class_1058 var8,
         int var9,
          class_11792 var10
      ) {
      }

      @Override
      public void method_73494(
         class_630 var1,
         class_4587 var2,
         class_1921 var3,
         int var4,
         int var5,
          class_1058 var6,
         boolean var7,
         boolean var8,
         int var9,
          class_11792 var10,
         int var11
      ) {
      }

      @Override
      public void method_73480(
         class_4587 var1, class_811 var2, int var3, int var4, int var5, int[] var6, List<class_777> var7, class_1921 var8, class_10445 var9
      ) {
      }

      @Override
      public void method_73484(class_4587 var1, class_1921 var2, class_1087 var3, float var4, float var5, float var6, int var7, int var8, int var9) {
      }

      @Override
      public void method_73481(class_4587 var1, class_2680 var2, int var3, int var4, int var5) {
      }

      @Override
      public void method_73485(class_4587 var1, class_11791 var2) {
      }

      @Override
      public void method_73483(class_4587 var1, class_1921 var2, class_11660 var3) {
      }

      @Override
      public void method_73479(class_4587 var1, float var2, List<class_11680> var3) {
      }

      @Override
      public void method_73482(class_4587 var1,  class_243 var2, int var3, class_2561 var4, boolean var5, int var6, double var7, class_12075 var9) {
      }

      @Override
      public void method_73478(class_4587 var1, float var2, float var3, class_5481 var4, boolean var5, class_6415 var6, int var7, int var8, int var9, int var10) {
      }

      @Override
      public void method_73488(class_4587 var1, class_10017 var2, Quaternionf var3) {
      }

      @Override
      public void method_73486(class_4587 var1, class_10018 var2) {
      }

      @Override
      public void method_74315(class_11947 var1) {
      }
   };



   Glassclass_11659(class_11659 var1, class_11785 var2, GlassSupport var3, int var4, boolean var5, boolean var6, boolean var7, Set<class_630> var8) {
      this.K = var1;
      this.R = var2;
      this.S = var3;
      this.n = var4;
      this.L = var5;
      this.u = var6;
      this.t = var7;
      this.B = var8;
   }

   static Glassclass_11659 into(GlassSupport var0, int var1) {
      return new Glassclass_11659(U, U, var0, var1, true, true, true, Set.of());
   }

   @Override
   public class_11785 method_73529(int var1) {
      return new Glassclass_11659(this.K, this.K.method_73529(var1), this.S, this.n, this.L, this.u, this.t, this.B);
   }

   private boolean r(class_1921 var1, long var2) {
      GlassRenderer4.ensureSourceClassInitialized();
      boolean var4 = GlassRegistry.inItem();
      long var5 = 111079585504049L;
      if (111079585504049L == 0L) {
         boolean var10000 = false;
      } else {
         int var11 = var5 < 0L ? -1 : 1;
      }

      if (var4) {
         return this.u;
      } else {
         var4 = armour(var1);
         long var8 = 111079585504049L;
         if (111079585504049L == 0L) {
            boolean var12 = false;
         } else {
            int var13 = var8 < 0L ? -1 : 1;
         }

         return var4 ? this.L && this.t : this.L;
      }
   }

   static boolean armour(class_1921 var0) {
      return armour(((GlassRenderTypeAccessor)var0).purxiy$name());
   }

   static boolean armour(String var0) {
      return var0.startsWith("armor");
   }

   static boolean surface(class_1921 var0) {
      return surface(((GlassRenderTypeAccessor)var0).purxiy$name());
   }

   static boolean surface(String var0) {
      return var0.contains("outline")
         ? var0.contains("no_outline")
         : !var0.contains("glint")
            && !var0.contains("eyes")
            && !var0.contains("energy")
            && !var0.contains("emissive")
            && !var0.contains("shadow")
            && !var0.contains("lines")
            && !var0.startsWith("text")
            && !var0.contains("leash")
            && !var0.contains("crumbling")
            && !var0.contains("beacon")
            && !var0.contains("lightning")
            && !var0.contains("portal")
            && !var0.contains("gateway")
            && !var0.contains("breeze")
            && !var0.contains("dragon")
            && !var0.contains("water_mask")
            && !var0.contains("debug")
            && !var0.contains("weather");
   }

   @Override
   public <S> void method_73490(
      class_3879<? super S> var1,
      S var2,
      class_4587 var3,
      class_1921 var4,
      int var5,
      int var6,
      int var7,
       class_1058 var8,
      int var9,
       class_11792 var10
   ) {
      long var11 = 50912758006408L;
      long var13 = var11 ^ 121394494693903L;
      long var15 = var11 ^ 82775695120825L;
      if (!this.r(var4, var15)) {
         this.R.method_73490(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
      } else if (surface(var4)) {
         try {
            var1.method_2819(var2);
            this.S.part(var1.method_63512(), var3, var4, var8, var5, this.n, this.r(false, var13), this.B);
         } catch (RuntimeException var18) {
            GlassRegistry.fail(var18);
         }
      }
   }

   @Override
   public void method_73494(
      class_630 var1,
      class_4587 var2,
      class_1921 var3,
      int var4,
      int var5,
       class_1058 var6,
      boolean var7,
      boolean var8,
      int var9,
       class_11792 var10,
      int var11
   ) {
      long var12 = 83742371680517L;
      long var14 = var12 ^ 13261201338754L;
      long var16 = var12 ^ 45283743189556L;
      if (!this.r(var3, var16)) {
         this.R.method_73494(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
      } else if (surface(var3)) {
         try {
            this.S.part(var1, var2, var3, var6, var4, this.n, this.r(var8, var14), this.B);
         } catch (RuntimeException var19) {
            GlassRegistry.fail(var19);
         }
      }
   }

   @Override
   public void method_73480(class_4587 var1, class_811 var2, int var3, int var4, int var5, int[] var6, List<class_777> var7, class_1921 var8, class_10445 var9) {
      long var10 = 11720295149907L;
      long var12 = var10 ^ 81976151740884L;
      long var14 = var10 ^ 122794779814498L;
      if (!this.r(var8, var14)) {
         this.R.method_73480(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      } else if (surface(var8)) {
         try {
            this.S.quads(var1.method_23760(), var7, var8, var3, this.n, this.r(var9 != class_10445.field_55341, var12));
         } catch (RuntimeException var17) {
            GlassRegistry.fail(var17);
         }
      }
   }

   private int r(boolean var1, long var2) {
      GlassRenderer4.ensureSourceClassInitialized();
      int var4 = var1 ? 2 : 0;
      return GlassRegistry.inItem() ? var4 | 4 : var4;
   }

   @Override
   public void method_73484(class_4587 var1, class_1921 var2, class_1087 var3, float var4, float var5, float var6, int var7, int var8, int var9) {
      this.R.method_73484(var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }

   @Override
   public void method_73481(class_4587 var1, class_2680 var2, int var3, int var4, int var5) {
      this.R.method_73481(var1, var2, var3, var4, var5);
   }

   @Override
   public void method_73485(class_4587 var1, class_11791 var2) {
      this.R.method_73485(var1, var2);
   }

   @Override
   public void method_73483(class_4587 var1, class_1921 var2, class_11660 var3) {
      this.R.method_73483(var1, var2, var3);
   }

   @Override
   public void method_73479(class_4587 var1, float var2, List<class_11680> var3) {
      if (this.L) {
         ArrayList var4 = new ArrayList(var3.size());

         for (class_11680 var6 : var3) {
            var4.add(new class_11680(var6.comp_4546(), var6.comp_4547(), var6.comp_4548(), var6.comp_4549(), var6.comp_4550() * 0.55F));
         }

         var3 = var4;
      }

      this.R.method_73479(var1, var2, (List<class_11680>)var3);
   }

   @Override
   public void method_73482(class_4587 var1,  class_243 var2, int var3, class_2561 var4, boolean var5, int var6, double var7, class_12075 var9) {
      this.R.method_73482(var1, var2, var3, var4, var5, var6, var7, var9);
   }

   @Override
   public void method_73478(class_4587 var1, float var2, float var3, class_5481 var4, boolean var5, class_6415 var6, int var7, int var8, int var9, int var10) {
      this.R.method_73478(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
   }

   @Override
   public void method_73488(class_4587 var1, class_10017 var2, Quaternionf var3) {
      this.R.method_73488(var1, var2, var3);
   }

   @Override
   public void method_73486(class_4587 var1, class_10018 var2) {
      this.R.method_73486(var1, var2);
   }

   @Override
   public void method_74315(class_11947 var1) {
      this.R.method_74315(var1);
   }

}
