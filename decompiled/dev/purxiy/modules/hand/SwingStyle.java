package dev.purxiy.modules.hand;

import net.minecraft.class_4587;
import net.minecraft.class_7833;

public enum SwingStyle {
   m,
   f,
   v,
   Q,
   a;

   private static final float u = (float) Math.PI;
   public static int O = 0;

   public void item(float var1, float var2, SwingStyle.TypeJ var3) {
      long var4 = 126804489084358L;
      long var6 = var4 ^ 83518567757881L;
      float var8 = Z(var6, var1);
      switch (this) {
         case m:
            float var9 = (float)Math.sqrt(var8);
            float var10 = D(var9 * (float) Math.PI);
            var3.J = -0.4F * var10;
            var3.H = 0.2F * D(var9 * 2.0F * (float) Math.PI);
            var3.I = -0.2F * D(var8 * (float) Math.PI);
            var3.m = 45.0F;
            var3.j = -20.0F * D(var8 * var8 * (float) Math.PI);
            var3.V = -20.0F * var10;
            var3.y = -80.0F * var10;
            break;
         case f:
            float var11 = easeOut(var8, 2.1F);
            float var12 = D(var11 * (float) Math.PI);
            var3.J = -0.4F * var12;
            var3.H = 0.2F * D(var11 * 2.0F * (float) Math.PI);
            var3.I = -0.2F * D(var8 * (float) Math.PI);
            var3.m = 45.0F;
            var3.j = -20.0F * D(var11 * var11 * (float) Math.PI);
            var3.V = -20.0F * var12;
            var3.y = -80.0F * var12;
            break;
         case v:
            float var13 = easeOut(var8, 2.6F);
            float var14 = D(var13 * (float) Math.PI);
            var3.J = -0.14F * var14;
            var3.H = -0.16F * var14 + 0.07F * D(var13 * 2.0F * (float) Math.PI);
            var3.I = -0.3F * var14;
            var3.m = 18.0F;
            var3.j = -6.0F * var14;
            var3.V = -10.0F * var14;
            var3.y = -108.0F * var14;
            break;
         case Q:
            float var15 = easeOut(var8, 2.3F);
            float var16 = D(var15 * (float) Math.PI);
            var3.J = -0.34F * var16;
            var3.H = 0.07F * D(var15 * 2.0F * (float) Math.PI) - 0.03F * var16;
            var3.I = -0.16F * D(var8 * (float) Math.PI);
            var3.m = 70.0F;
            var3.j = 8.0F * var16;
            var3.V = -22.0F * var16;
            var3.y = -52.0F * var16;
            break;
         case a:
            float var17 = easeOut(var8, 3.0F);
            float var18 = D(var17 * (float) Math.PI);
            var3.J = -0.1F * var18;
            var3.H = 0.05F * var18;
            var3.I = -0.42F * var18;
            var3.m = 0.0F;
            var3.j = 0.0F;
            var3.V = -4.0F * var18;
            var3.y = -9.0F * var18;
      }

      var3.scale(var2);
   }

   public void arm(float var1, float var2, SwingStyle.TypeJ var3) {
      long var4 = 78900578389894L;
      long var6 = var4 ^ 140068260260473L;
      float var8 = Z(var6, var1);
      var3.m = 45.0F;
      switch (this) {
         case m:
            float var9 = (float)Math.sqrt(var8);
            var3.J = -0.3F * D(var9 * (float) Math.PI);
            var3.H = 0.4F * D(var9 * 2.0F * (float) Math.PI);
            var3.I = -0.4F * D(var8 * (float) Math.PI);
            var3.j = 70.0F * D(var9 * (float) Math.PI);
            var3.V = -20.0F * D(var8 * var8 * (float) Math.PI);
            var3.y = 0.0F;
            break;
         case f:
            float var10 = easeOut(var8, 2.1F);
            var3.J = -0.3F * D(var10 * (float) Math.PI);
            var3.H = 0.4F * D(var10 * 2.0F * (float) Math.PI);
            var3.I = -0.4F * D(var8 * (float) Math.PI);
            var3.j = 70.0F * D(var10 * (float) Math.PI);
            var3.V = -20.0F * D(var10 * var10 * (float) Math.PI);
            var3.y = 0.0F;
            break;
         case v:
            float var11 = easeOut(var8, 2.6F);
            float var12 = D(var11 * (float) Math.PI);
            var3.J = -0.18F * var12;
            var3.H = 0.18F * D(var11 * 2.0F * (float) Math.PI) - 0.12F * var12;
            var3.I = -0.42F * var12;
            var3.j = 34.0F * var12;
            var3.V = -26.0F * var12;
            var3.y = -18.0F * var12;
            break;
         case Q:
            float var13 = easeOut(var8, 2.3F);
            float var14 = D(var13 * (float) Math.PI);
            var3.J = -0.42F * var14;
            var3.H = 0.26F * D(var13 * 2.0F * (float) Math.PI);
            var3.I = -0.3F * D(var8 * (float) Math.PI);
            var3.j = 95.0F * var14;
            var3.V = -12.0F * var14;
            var3.y = 0.0F;
            break;
         case a:
            float var15 = easeOut(var8, 3.0F);
            float var16 = D(var15 * (float) Math.PI);
            var3.J = -0.22F * var16;
            var3.H = 0.1F * var16;
            var3.I = -0.62F * var16;
            var3.j = 26.0F * var16;
            var3.V = -6.0F * var16;
            var3.y = 0.0F;
      }

      var3.scale(var2);
   }

   public float restBase(boolean var1) {
      float var2;
      if (var1) {
         var2 = 45.0F;
      } else {
         switch (this) {
            case v:
               var2 = 18.0F;
               break;
            case Q:
               var2 = 70.0F;
               break;
            case a:
               var2 = 0.0F;
               break;
            default:
               var2 = 45.0F;
         }
      }

      return var2;
   }

   static float easeOut(float var0, float var1) {
      return 1.0F - (float)Math.pow(1.0F - var0, var1);
   }

   private static float D(float var0) {
      return (float)Math.sin(var0);
   }

   private static float Z(long var0, float var2) {
      return var2 < 0.0F ? 0.0F : Math.min(var2, 1.0F);
   }

   public static void ensureSourceClassInitialized() {
   }

   public static final class TypeJ {
      public float J;
      public float H;
      public float I;
      public float m;
      public float j;
      public float V;
      public float y;

      public SwingStyle.TypeJ set(SwingStyle.TypeJ var1) {
         this.J = var1.J;
         this.H = var1.H;
         this.I = var1.I;
         this.m = var1.m;
         this.j = var1.j;
         this.V = var1.V;
         this.y = var1.y;
         return this;
      }

      public SwingStyle.TypeJ rest(float var1) {
         this.J = this.H = this.I = this.j = this.V = this.y = 0.0F;
         this.m = var1;
         return this;
      }

      public SwingStyle.TypeJ lerp(SwingStyle.TypeJ var1, float var2) {
         this.J = this.J + (var1.J - this.J) * var2;
         this.H = this.H + (var1.H - this.H) * var2;
         this.I = this.I + (var1.I - this.I) * var2;
         this.m = this.m + (var1.m - this.m) * var2;
         this.j = this.j + (var1.j - this.j) * var2;
         this.V = this.V + (var1.V - this.V) * var2;
         this.y = this.y + (var1.y - this.y) * var2;
         return this;
      }

      SwingStyle.TypeJ scale(float var1) {
         this.J *= var1;
         this.H *= var1;
         this.I *= var1;
         this.j *= var1;
         this.V *= var1;
         this.y *= var1;
         return this;
      }

      public void applyItem(class_4587 var1, int var2) {
         var1.method_46416(var2 * this.J, this.H, this.I);
         var1.method_22907(class_7833.field_40716.rotationDegrees(var2 * (this.m + this.j)));
         var1.method_22907(class_7833.field_40718.rotationDegrees(var2 * this.V));
         var1.method_22907(class_7833.field_40714.rotationDegrees(this.y));
         var1.method_22907(class_7833.field_40716.rotationDegrees(var2 * -this.m));
      }

      public void applyArm(class_4587 var1, int var2) {
         var1.method_46416(var2 * this.J, this.H, this.I);
         var1.method_22907(class_7833.field_40716.rotationDegrees(var2 * this.m));
         var1.method_22907(class_7833.field_40716.rotationDegrees(var2 * this.j));
         var1.method_22907(class_7833.field_40718.rotationDegrees(var2 * this.V));
         var1.method_22907(class_7833.field_40714.rotationDegrees(this.y));
      }
   }
}
