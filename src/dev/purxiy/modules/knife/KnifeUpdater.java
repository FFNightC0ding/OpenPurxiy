package dev.purxiy.modules.knife;

import dev.purxiy.loader.fabric.client.ClientUpdater;
import dev.purxiy.loader.fabric.client.ClientUpdater.ModulePayload;
import java.util.Arrays;
import net.minecraft.class_10444;
import net.minecraft.class_10515;
import net.minecraft.class_11566;
import net.minecraft.class_11659;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_3489;
import net.minecraft.class_4587;
import net.minecraft.class_746;
import net.minecraft.class_7833;
import net.minecraft.class_804;
import net.minecraft.class_811;
import net.minecraft.class_10444.class_10445;
import net.minecraft.class_10444.class_10446;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public final class KnifeUpdater {
   private static final class_10515<ModulePayload> t = ClientUpdater.RENDERER;
   private static final class_804 l = Y(0.0F, -90.0F, 55.0F, 0.0F, 4.0F, 0.5F, 0.85F);
   private static final class_804 w = Y(0.0F, 90.0F, -55.0F, 0.0F, 4.0F, 0.5F, 0.85F);
   static float I = 0.225F;
   static float V = 1.7F;
   static float E = -65.0F;
   static float s = 12.0F;
   static float Q = 1.0F;
   private static int P = 0;
   private static final float[][] p = new float[KnifeModels.u.length][];
   private static final float[][] f = new float[KnifeBlades.B.length][];
   private static final Integer[] d = new Integer[2];

   private KnifeUpdater() {
   }

   public static boolean update(class_10444 var0, class_1799 var1, class_811 var2, class_11566 var3) {
      long var4 = 30876147592387L;
      long var6 = var4 ^ 80957584482371L;
      if (!shown(var1, var2, var3)) {
         return false;
      } else {
         KnifeModule var8 = KnifeSwingState.Z;
         ModulePayload var9 = new ModulePayload(
            KnifeModels.index((String)var8.U.get()),
            (String)var8.F.get(),
            (String)var8.camoSetting().get(),
            var8.enabled() ? KnifeBlades.index((String)var8.z.get()) : -1
         );
         var0.method_70946(t);
         var0.method_70946(var9);
         if (P != 0) {
            var0.method_70946(P);
         }

         class_10446 var10 = var0.method_65601();
         if (var1.method_7958()) {
            var10.method_65615(class_10445.field_55342);
            var0.method_70947();
            var0.method_70946(class_10445.field_55342);
         }

         var10.method_65617(t, var9);
         var10.method_67996(true);
         var10.method_67993(var2 == class_811.field_4323 ? w : (var2 == class_811.field_4320 ? l : class_804.field_4284));
         var10.method_67995(ClientUpdater.extents(p(var6, var9, var2)));
         return true;
      }
   }

   public static boolean shown(class_1799 var0, class_811 var1, class_11566 var2) {
      KnifeModule var3 = KnifeSwingState.Z;
      if (!var3.enabled() || var0.method_7960() || !var0.method_31573(class_3489.field_42611)) {
         return false;
      } else if (var1 == class_811.field_4317) {
         return true;
      } else if (var1 != class_811.field_4323 && var1 != class_811.field_4320) {
         return false;
      } else {
         class_746 var4 = class_310.method_1551().field_1724;
         if (var4 != null && var2 == var4) {
            boolean var5 = var4.method_6079() == var0 && var4.method_6047() != var0;
            return !var5 || var3.d.enabled();
         } else {
            return false;
         }
      }
   }

   static void place(class_4587 var0, ModulePayload var1, class_811 var2) {
      long var3 = 74619825457191L;
      long var5 = var3 ^ 36566012133274L;
      long var7 = var3 ^ 7479180491490L;
      if (var1.great() >= 0) {
         g(var0, var5, var1.great(), var2);
      } else if (var2 == class_811.field_4317) {
         float[] var9 = G(var7, var1.model());
         var0.method_46416(0.5F, 0.5F, 0.5F);
         var0.method_22905(var9[0], var9[0], var9[0]);
         var0.method_22907(class_7833.field_40718.rotationDegrees(-45.0F));
         var0.method_22907(class_7833.field_40716.rotationDegrees(s));
         var0.method_46416(-var9[1], -var9[2], -var9[3]);
      } else {
         var0.method_46416(I, I, 0.5F);
         var0.method_22907(class_7833.field_40718.rotationDegrees(E));
         var0.method_22905(V, V, V);
         Vector3f var10 = KnifePlacement.grip(var1.model());
         var0.method_46416(-var10.x, -var10.y, -var10.z);
      }
   }

   private static void g(class_4587 var0, long var1, int var3, class_811 var4) {
      long var5 = 51291183049105L;
      KnifeSwingState.ensureSourceClassInitialized();
      if (var4 == class_811.field_4317) {
         float[] var7 = L(var5, var3);
         long var8 = 108424169355197L;
         int var10 = var8 < 0L ? -1 : 1;
         var0.method_46416(0.5F, 0.5F, 0.5F);
         var0.method_22905(var7[0], var7[0], var7[0]);
         var0.method_22907(class_7833.field_40718.rotationDegrees(-45.0F));
         var0.method_22907(class_7833.field_40716.rotationDegrees(s));
         var0.method_46416(-var7[1], -var7[2], -var7[3]);
      } else {
         var0.method_46416(I, I, 0.5F);
         var0.method_22907(class_7833.field_40718.rotationDegrees(E));
         var0.method_22905(1.15F, 1.15F, 1.15F);
         var0.method_46416(0.0F, KnifeBlades.g[var3][0], 0.0F);
      }
   }

   private static float[] L(long var0, int var2) {
      KnifeSwingState.ensureSourceClassInitialized();
      long var3 = 51291183049104L;
      int var6 = var3 < 0L ? -1 : 1;
      if (f[var2] == null) {
         KnifeBuilder var6x = KnifeBlades.get(var2, "Signature").blade();
         Matrix4f var7 = new Matrix4f().rotateZ((float)Math.toRadians(-45.0)).rotateY((float)Math.toRadians(s));
         Vector3f var8 = new Vector3f(Float.MAX_VALUE);
         Vector3f var9 = new Vector3f(-Float.MAX_VALUE);
         Vector3f var10 = new Vector3f();

         for (int var11 = 0; var11 < var6x.vertexCount(); var11++) {
            var7.transformPosition(var10.set(var6x.x(var11), var6x.y(var11), var6x.z(var11)));
            long var12 = 51291183049105L;
            int var15 = var12 < 0L ? -1 : 1;
            var8.min(var10);
            var9.max(var10);
         }

         Vector3f var16 = new Vector3f(var8).add(var9);
         long var17 = 51291183049105L;
         int var17x = var17 < 0L ? -1 : 1;
         Vector3f var15 = var16.mul(0.5F);
         new Matrix4f(var7).invert().transformPosition(var15);
         f[var2] = new float[]{Q / Math.max(var9.x - var8.x, var9.y - var8.y), var15.x, var15.y, var15.z};
      }

      return f[var2];
   }

   private static float[] G(long var0, int var2) {
      KnifeSwingState.ensureSourceClassInitialized();
      long var3 = 75937871164101L;
      int var6 = var3 < 0L ? -1 : 1;
      if (p[var2] == null) {
         KnifeBuilder var6x = KnifeModels.get(var2, "Signature").posed(var2, KnifeGestureRenderer.M);
         Matrix4f var7 = new Matrix4f().rotateZ((float)Math.toRadians(-45.0)).rotateY((float)Math.toRadians(s));
         Vector3f var8 = new Vector3f(Float.MAX_VALUE);
         Vector3f var9 = new Vector3f(-Float.MAX_VALUE);
         Vector3f var10 = new Vector3f();

         for (int var11 = 0; var11 < var6x.vertexCount(); var11++) {
            var7.transformPosition(var10.set(var6x.x(var11), var6x.y(var11), var6x.z(var11)));
            long var12 = 75937871164101L;
            int var15 = var12 < 0L ? -1 : 1;
            var8.min(var10);
            var9.max(var10);
         }

         Vector3f var16 = new Vector3f(var8).add(var9);
         long var17 = 75937871164101L;
         int var17x = var17 < 0L ? -1 : 1;
         Vector3f var15 = var16.mul(0.5F);
         new Matrix4f(var7).invert().transformPosition(var15);
         p[var2] = new float[]{Q / Math.max(var9.x - var8.x, var9.y - var8.y), var15.x, var15.y, var15.z};
      }

      return p[var2];
   }

   private static Vector3fc[] p(long var0, ModulePayload var2, class_811 var3) {
      long var4 = 32310142064719L;
      KnifeSwingState.ensureSourceClassInitialized();
      KnifeBuilder var6 = var2.great() >= 0 ? I(var4, var2).blade() : I(var4, var2).posed(var2.model(), KnifeGestureRenderer.M);
      class_4587 var7 = new class_4587();
      place(var7, var2, var3);
      Matrix4f var8 = var7.method_23760().method_23761();
      Vector3f var9 = new Vector3f(Float.MAX_VALUE);
      Vector3f var10 = new Vector3f(-Float.MAX_VALUE);
      Vector3f var11 = new Vector3f();

      for (int var12 = 0; var12 < var6.vertexCount(); var12++) {
         var8.transformPosition(var11.set(var6.x(var12), var6.y(var12), var6.z(var12)));
         var9.min(var11);
         var10.max(var11);
      }

      long var20 = 94238056352896L;
      int var15 = var20 < 0L ? -1 : 1;
      Vector3fc[] var15x = new Vector3fc[8];

      for (int var16 = 0; var16 < 8; var16++) {
         long var17 = 94238056352896L;
         int var18 = var17 < 0L ? -1 : 1;
         var15x[var16] = new Vector3f((var16 & 1) == 0 ? var9.x : var10.x, (var16 & 2) == 0 ? var9.y : var10.y, (var16 & 4) == 0 ? var9.z : var10.z);
      }

      long var21 = 94238056352896L;
      int var171 = var21 < 0L ? -1 : 1;
      return var15x;
   }

   public static void tune(float var0, float var1, float var2, float var3, float var4) {
      I = var0;
      V = var1;
      E = var2;
      s = var3;
      Q = var4;
      Arrays.fill(p, null);
      Arrays.fill(f, null);
      P++;
   }

   private static class_804 Y(float var0, float var1, float var2, float var3, float var4, float var5, float var6) {
      return new class_804(new Vector3f(var0, var1, var2), new Vector3f(var3 / 16.0F, 0.25F, 0.03125F), new Vector3f(0.85F, 0.85F, 0.85F));
   }

   private static KnifeModels.Model I(long var0, ModulePayload var2) {
      KnifeSwingState.ensureSourceClassInitialized();
      int var3 = var2.great();
      long var4 = 32310142064718L;
      int var6 = var4 < 0L ? -1 : 1;
      return var3 >= 0 ? KnifeBlades.get(var2.great(), var2.finish()) : KnifeModels.get(var2.model(), var2.finish(), var2.camo());
   }

   static void submit(ModulePayload var0, class_811 var1, class_4587 var2, class_11659 var3, int var4, int var5, boolean var6, int var7) {
      long var8 = 43363905492726L;
      long var10 = var8 ^ 63852081614521L;
      if (var0 != null) {
         var2.method_22903();

         try {
            place(var2, var0, var1);
            KnifeMaterialMesh.submit(
               I(var10, var0),
               KnifeCache.atlas(var0.great() >= 0 ? null : KnifeCamos.find(var0.model(), var0.camo())),
               0.0F,
               0.0F,
               var2,
               var3,
               var4,
               var5,
               var6,
               var1 == class_811.field_4317 ? null : class_310.method_1551().field_1773.method_19418().method_23767()
            );
         } finally {
            var2.method_22909();
         }
      }
   }
}
