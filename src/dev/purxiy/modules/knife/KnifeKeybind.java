package dev.purxiy.modules.knife;

import dev.purxiy.loader.fabric.ClientServices;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1058;
import net.minecraft.class_10725;
import net.minecraft.class_11944;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_3940;
import net.minecraft.class_4066;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import net.minecraft.class_3940.class_11941;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class KnifeKeybind extends class_3940 {
   private final KnifeKeybind.KnifeAction P;
   private final float F;
   private final float k;
   private final float j;
   private final Vector3f Q;
   private float c;
   private float S;
   private final float v;

   private KnifeKeybind(
      short var1,
      class_638 var2,
      class_243 var3,
      Vector3f var4,
      KnifeKeybind.KnifeAction var5,
      class_1058 var6,
      short var7,
      int var8,
      float var9,
      int var10,
      int var11
   ) {
      super(initializeController(var2), var3.field_1352, var3.field_1351, var3.field_1350, var6);
      ThreadLocalRandom var12 = ThreadLocalRandom.current();
      this.P = var5;
      this.field_3852 = var4.x;
      this.field_3869 = var4.y;
      this.field_3850 = var4.z;
      this.field_3862 = false;
      this.field_3844 = 0.0F;
      this.field_3847 = var10;
      this.F = var9;
      this.field_17867 = var9;
      this.k = var12.nextFloat() * 6.283F;
      this.j = (var12.nextFloat() - 0.5F) * (var5 == KnifeKeybind.KnifeAction.B ? 0.5F : 0.12F);
      this.Q = new Vector3f(var12.nextFloat() - 0.5F, var12.nextFloat() - 0.5F, var12.nextFloat() - 0.5F).normalize();
      this.field_62637 = this.field_62638 = var12.nextFloat() * 6.283F;
      this.method_74305((var8 >> 16 & 0xFF) / 255.0F, (var8 >> 8 & 0xFF) / 255.0F, (var8 & 0xFF) / 255.0F);
      this.v = var5 == KnifeKeybind.KnifeAction.B ? 0.92F : (var5 == KnifeKeybind.KnifeAction.Y ? 0.55F : 0.95F);
      this.field_62636 = 0.0F;
   }

   private static class_638 initializeController(class_638 var0) {
      KnifeSwingState.ensureSourceClassInitialized();
      return var0;
   }

   protected class_11941 method_74255() {
      return class_11941.field_62641;
   }

   public void method_3070() {
      this.field_3858 = this.field_3874;
      this.field_3838 = this.field_3854;
      this.field_3856 = this.field_3871;
      this.field_62638 = this.field_62637;
      this.S = this.c;
      if (this.field_3866++ >= this.field_3847) {
         this.method_3085();
      } else {
         float var1 = this.field_3866;
         switch (this.P) {
            case B:
               this.field_3852 = this.field_3852 + Math.sin(var1 * 0.31F + this.k) * 0.0016F;
               this.field_3850 = this.field_3850 + Math.cos(var1 * 0.27F + this.k * 1.3F) * 0.0016F;
               this.field_3869 = this.field_3869 * 0.94F - 0.0011F;
               this.field_3852 *= 0.955F;
               this.field_3850 *= 0.955F;
               this.c = this.c + this.j;
               break;
            case A:
               this.field_3869 = this.field_3869 * 0.93F + 9.0E-4F;
               this.field_3852 = this.field_3852 * 0.95F + Math.sin(var1 * 0.5F + this.k) * 7.0E-4F;
               this.field_3850 = this.field_3850 * 0.95F + Math.cos(var1 * 0.45F + this.k) * 7.0E-4F;
               this.field_62637 = this.field_62637 + this.j;
               break;
            case Y:
               this.field_3869 = this.field_3869 * 0.9F + 6.0E-4F;
               this.field_3852 *= 0.94F;
               this.field_3850 *= 0.94F;
               this.field_62637 = this.field_62637 + this.j;
               break;
            case i:
               this.field_3852 *= 0.88F;
               this.field_3869 = this.field_3869 * 0.88F + 4.0E-4F;
               this.field_3850 *= 0.88F;
               this.field_62637 = this.field_62637 + this.j;
               break;
            case q:
               this.field_3869 -= 0.011F;
               this.field_3852 *= 0.86F;
               this.field_3869 *= 0.9F;
               this.field_3850 *= 0.86F;
         }

         this.method_3069(this.field_3852, this.field_3869, this.field_3850);
         float var2 = var1 / this.field_3847;
         float var3 = Math.min(1.0F, var1 / (this.P == KnifeKeybind.KnifeAction.q ? 1.0F : 4.0F));
         float var4 = 1.0F - Math.max(0.0F, (var2 - 0.55F) / 0.45F);
         float var5 = this.P != KnifeKeybind.KnifeAction.A && this.P != KnifeKeybind.KnifeAction.i ? 1.0F : 0.75F + 0.25F * (float)Math.sin(var1 * 1.7F + this.k);
         this.field_62636 = this.v * var3 * var4 * var4 * var5;

         this.field_17867 = this.F * switch (this.P) {
            case Y -> 0.6F + 0.9F * var2;
            case i -> 0.7F + 0.3F * (float)Math.sin(var1 * 0.9F + this.k);
            case q -> 1.0F - 0.6F * var2;
            default -> 1.0F;
         };
      }
   }

   public void method_3074(class_11944 var1, class_4184 var2, float var3) {
      if (this.P == KnifeKeybind.KnifeAction.q) {
         Vector3f var4 = new Quaternionf(var2.method_23767())
            .conjugate()
            .transform(new Vector3f((float)this.field_3852, (float)this.field_3869, (float)this.field_3850));
         Quaternionf var5 = new Quaternionf(var2.method_23767()).rotateZ((float)Math.atan2(var4.y, var4.x));
         this.method_60373(var1, var2, var5, var3);
      } else if (this.P != KnifeKeybind.KnifeAction.B) {
         super.method_3074(var1, var2, var3);
      } else {
         float var6 = class_3532.method_16439(var3, this.S, this.c);
         Quaternionf var7 = new Quaternionf(var2.method_23767())
            .rotateAxis(var6, this.Q)
            .rotateZ(class_3532.method_16439(var3, this.field_62638, this.field_62637));
         this.method_60373(var1, var2, var7, var3);
      }
   }

   protected int method_3068(float var1) {
      return this.P == KnifeKeybind.KnifeAction.B ? Math.max(super.method_3068(var1), 10485920) : 15728880;
   }

   private static class_1058 C(int var0, int var1, String var2, byte var3) {
      try {
         return class_310.method_1551().method_72703().method_73025(class_10725.field_56389).method_4608(ClientServices.id("wield_" + var2));
      } catch (RuntimeException var5) {
         return null;
      }
   }

   private static float p(long var0) {
      KnifeSwingState.ensureSourceClassInitialized();
      class_4066 var2 = (class_4066)class_310.method_1551().field_1690.method_42475().method_41753();
      return var2 == class_4066.field_18197 ? 1.0F : (var2 == class_4066.field_18198 ? 0.45F : 0.0F);
   }

   private static boolean G(int var0, long var1) {
      long var3 = 119700568970322L;
      long var5 = var3 ^ 57605205018688L;
      KnifeSwingState.ensureSourceClassInitialized();
      float var7 = p(var5);
      return var7 >= 1.0F || ThreadLocalRandom.current().nextFloat() < var7;
   }

   static void shed(class_638 var0, KnifeWieldPipeline.WieldStage var1, class_243 var2, Vector3f var3, int var4, int var5) {
      long var6 = 17994856664911L;
      long var8 = var6 ^ 130064865186441L;
      int var10 = (int)((var6 ^ 130064865186441L) >>> 32);
      int var11 = (int)((var6 ^ 130064865186441L) << 32 >>> 40);
      int var12 = (int)(var8 << 56 >>> 56);
      int var13 = (int)((var6 ^ 136890085710621L) >>> 32);
      long var14 = (var6 ^ 136890085710621L) << 32 >>> 32;
      var8 = var6 ^ 52728296041201L;
      int var16 = (int)((var6 ^ 52728296041201L) >>> 48);
      int var17 = (int)((var6 ^ 52728296041201L) << 16 >>> 48);
      int var18 = (int)(var8 << 32 >>> 32);
      long var19 = var6 ^ 47268299376066L;
      if (G(var13, var14)) {
         ThreadLocalRandom var21 = ThreadLocalRandom.current();

         KnifeKeybind.KnifeAction var22 = switch (KnifeKeybind.KnifeBinding.n[var1.ordinal()]) {
            case 1 -> KnifeKeybind.KnifeAction.B;
            case 2 -> KnifeKeybind.KnifeAction.A;
            case 3 -> KnifeKeybind.KnifeAction.Y;
            default -> KnifeKeybind.KnifeAction.i;
         };

         String var23 = switch (var22) {
            case B -> var21.nextInt(3) == 0 ? "petal_b" : "petal";
            case A -> "ember";
            case Y -> "wisp";
            default -> "sparkle";
         };
         class_1058 var24 = C(var10, var11, var23, (byte)var12);
         if (var24 != null) {
            int var25 = switch (var22) {
               case B -> L(-11290, var4, 0.35F + var21.nextFloat() * 0.25F, var19);
               case A -> L(var4, -1, var21.nextFloat() * 0.4F, var19);
               case Y -> L(var5, var4, var21.nextFloat() * 0.6F, var19);
               default -> L(var4, -1, 0.5F, var19);
            };

            float var26 = switch (var22) {
               case B -> 0.036F + var21.nextFloat() * 0.02F;
               case A -> 0.012F + var21.nextFloat() * 0.012F;
               case Y -> 0.05F + var21.nextFloat() * 0.04F;
               default -> 0.018F + var21.nextFloat() * 0.012F;
            };

            int var27 = switch (var22) {
               case B -> 40 + var21.nextInt(30);
               case A -> 22 + var21.nextInt(22);
               case Y -> 26 + var21.nextInt(18);
               default -> 18 + var21.nextInt(14);
            };
            class_310.method_1551()
               .field_1713
               .method_3058(new KnifeKeybind((short)var16, var0, var2, var3, var22, var24, (short)var17, var25, var26, var27, var18));
         }
      }
   }

   static void spark(class_638 var0, class_243 var1, Vector3f var2, int var3, boolean var4) {
      long var5 = 63096862223104L;
      long var7 = var5 ^ 104952711313094L;
      int var9 = (int)((var5 ^ 104952711313094L) >>> 32);
      int var10 = (int)((var5 ^ 104952711313094L) << 32 >>> 40);
      int var11 = (int)(var7 << 56 >>> 56);
      int var12 = (int)((var5 ^ 94279228440402L) >>> 32);
      long var13 = (var5 ^ 94279228440402L) << 32 >>> 32;
      var7 = var5 ^ 7472712502974L;
      int var15 = (int)((var5 ^ 7472712502974L) >>> 48);
      int var16 = (int)((var5 ^ 7472712502974L) << 16 >>> 48);
      int var17 = (int)(var7 << 32 >>> 32);
      long var18 = var5 ^ 4134463119757L;
      if (G(var12, var13)) {
         class_1058 var20 = C(var9, var10, "streak", (byte)var11);
         if (var20 != null) {
            ThreadLocalRandom var21 = ThreadLocalRandom.current();
            int var22 = L(var3, -1, 0.35F + var21.nextFloat() * 0.4F, var18);
            float var23 = (var4 ? 0.07F : 0.055F) + var21.nextFloat() * 0.03F;
            class_310.method_1551()
               .field_1713
               .method_3058(
                  new KnifeKeybind((short)var15, var0, var1, var2, KnifeKeybind.KnifeAction.q, var20, (short)var16, var22, var23, 7 + var21.nextInt(8), var17)
               );
         }
      }
   }

   private static int L(int var0, int var1, float var2, long var3) {
      int var5 = var0 >> 16 & 0xFF;
      int var6 = var0 >> 8 & 0xFF;
      int var7 = var0 & 0xFF;
      int var8 = var1 >> 16 & 0xFF;
      int var9 = var1 >> 8 & 0xFF;
      int var10 = var1 & 0xFF;
      return 0xFF000000
         | Math.round(var5 + (var8 - var5) * var2) << 16
         | Math.round(var6 + (var9 - var6) * var2) << 8
         | Math.round(var7 + (var10 - var7) * var2);
   }

   static class KnifeBinding {
      static final int[] n = new int[KnifeWieldPipeline.WieldStage.values().length];

      static {
         try {
            n[KnifeWieldPipeline.WieldStage.H.ordinal()] = 1;
         } catch (NoSuchFieldError var3) {
         }

         try {
            n[KnifeWieldPipeline.WieldStage.X.ordinal()] = 2;
         } catch (NoSuchFieldError var2) {
         }

         try {
            n[KnifeWieldPipeline.WieldStage.g.ordinal()] = 3;
         } catch (NoSuchFieldError var1) {
         }
      }
   }

   static enum KnifeAction {
      B,
      A,
      Y,
      i,
      q;
   }
}
