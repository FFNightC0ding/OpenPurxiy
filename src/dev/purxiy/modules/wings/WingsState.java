package dev.purxiy.modules.wings;

import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.client.CabinStateProvider;
import net.minecraft.class_10055;
import net.minecraft.class_10192;
import net.minecraft.class_1304;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_310;
import net.minecraft.class_4050;
import net.minecraft.class_746;
import net.minecraft.class_9334;
import org.joml.Vector3f;

public final class WingsState {
   private static final WingsUpdater B = new WingsUpdater();
   private static final WingsTransform.WingVertex K = new WingsTransform.WingVertex();
   private static final Vector3f L = new Vector3f();
   private static WingDesign g;
   private static long Y = 0L;
   private static boolean A;
   private static WingDesign k;
   static WingsModule e;
   private static ModuleContext R;
   private static int N = 0;
   private static int n = 0;
   public static boolean U;

   private WingsState() {
   }

   public static WingsModule module() {
      return e;
   }

   public static boolean wears(class_10055 var0) {
      class_746 var1 = class_310.method_1551().field_1724;
      return module().enabled()
         && var1 != null
         && var0.field_53528 == var1.method_5628()
         && !var0.field_53333
         && !var0.field_53542
         && !var0.method_62613(class_4050.field_18078)
         && !CabinStateProvider.inClosedCabin(var0);
   }

   public static boolean hidesElytra(class_10055 var0) {
      return module().m.enabled() && wears(var0);
   }

   public static WingDesign shown() {
      if (k != null) {
         return k;
      } else {
         return g != null && System.nanoTime() < Y ? g : module().selected();
      }
   }

   public static void tryOn(WingDesign var0) {
      g = var0;
      Y = System.nanoTime() + 400000000L;
   }

   public static WingsUpdater rig() {
      return B;
   }

   static WingsTransform.WingVertex update(class_10055 var0) {
      long var1 = 68808675784557L;
      long var3 = var1 ^ 46421888730420L;
      WingsModule var5 = module();
      WingDesign var6 = shown();
      boolean var7 = B.update(
         var0, class_310.method_1551().field_1687 == null ? null : class_310.method_1551().field_1687.method_8469(var0.field_53528), var5, var6.w
      );
      if (!var7 && A) {
         return K;
      } else {
         A = true;
         K.clear();
         int var8 = var5.p.get();
         float var9 = ((Double)var5.k.get()).floatValue();
         float var10 = B.time();
         boolean var11 = T(var3, var0.field_53418);
         boolean var12 = var0.field_53532 && var0.field_53520 != null && var0.field_53520.comp_1627() != null && !var11;
         L.set(1.7F, 2.6F, 2.2F + (var11 ? 1.05F : 0.0F) + (var12 ? 0.9F : 0.0F));
         float var13 = var0.field_53411 ? var0.method_64259() : 0.0F;
         K.lightFrom(0.0F, -0.9F * (1.0F - var13), 0.9F * var13);
         WingsTransform.build(K, B.e, true, L, var6, var8, var9, var10);
         L.z += 0.15F;
         WingsTransform.build(K, B.l, false, L, var6, var8, var9, var10);
         return K;
      }
   }

   private static boolean T(long var0, class_1799 var2) {
      class_10192 var3 = (class_10192)var2.method_58694(class_9334.field_54196);
      return var3 != null
         && var3.comp_3174() == class_1304.field_6174
         && !var2.method_31574(class_1802.field_8833)
         && !var2.method_57826(class_9334.field_54197);
   }

   public static void preview(WingsUpdater.WingPhase var0, float var1) {
      B.act(var0, var1);
   }

   public static void install(WingsModule var0, ModuleContext var1) {
      e = var0;
      R = var1;
   }

   public static void metric(String var0) {
      if (R != null) {
         R.metric(var0, var0.equals("drawn") ? ++N : ++n);
      }
   }

   public static void uninstall() {
      WingsCache.release();
      K.clear();
      g = null;
      k = null;
      A = false;
      e = null;
      R = null;
   }

   public static void ensureSourceClassInitialized() {
   }
}
