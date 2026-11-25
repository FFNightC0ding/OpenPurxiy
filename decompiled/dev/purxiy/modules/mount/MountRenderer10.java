package dev.purxiy.modules.mount;

import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_636;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public final class MountRenderer10 {
   private static final MountSupport2 C = new MountSupport2();
   private static final MountSupport2 M = new MountSupport2();
   private static float[] O;
   private static long D = 0L;
   private static final Matrix4f T = new Matrix4f();
   private static boolean x;

   private MountRenderer10() {
   }

   public static void render(WorldRenderContext var0, Matrix4f var1) {
      long var2 = 17984879999397L;
      long var4 = (var2 ^ 44329393677536L) >>> 8;
      int var6 = (int)((var2 ^ 44329393677536L) << 56 >>> 56);
      if (!MountRenderer13.capturing()) {
         T.set(var1);
         x = true;
         MountFactory.Data var7 = MountUpdater4.spec().screen();
         if (MountRenderer5.cockpitActive() && var7 != null) {
            Matrix4f var8 = new Matrix4f(MountRenderer5.modelView()).mul(var1).invert();
            Vector3f var9 = var8.transformPosition(new Vector3f());
            Vector3f var10 = var8.transformDirection(new Vector3f(0.0F, 0.0F, -1.0F)).normalize();
            O = o(var7, var4, (byte)var6, var9, var10);
            MountRenderer2.hover(O);
         } else {
            O = null;
            MountRenderer2.hover(null);
         }
      }
   }

   static Matrix4f bodyMatrix() {
      return T;
   }

   static boolean framed() {
      return x;
   }

   private static float[] o(MountFactory.Data var0, long var1, byte var3, Vector3f var4, Vector3f var5) {
      MountFactory.Data7.ensureSourceClassInitialized();
      Vector3f var6 = var0.normal();
      float var7 = var5.dot(var6);
      if (Math.abs(var7) < 1.0E-5F) {
         return null;
      } else {
         Vector3f var8 = var0.topLeft();
         float var9 = new Vector3f(var8).sub(var4).dot(var6) / var7;
         if (!(var9 <= 0.0F) && !(var9 > 2.5F)) {
            Vector3f var10 = new Vector3f(var4).add(new Vector3f(var5).mul(var9)).sub(var8);
            float var11 = var10.dot(var0.right()) / var0.width();
            float var12 = var10.dot(var0.down()) / var0.height();
            return var11 >= 0.0F && var11 <= 1.0F && var12 >= 0.0F && var12 <= 1.0F ? new float[]{var11, var12} : null;
         } else {
            return null;
         }
      }
   }

   public static void keyState(class_304 var0, boolean var1) {
      class_310 var2 = class_310.method_1551();
      if (var2 != null && var2.field_1690 != null) {
         MountSupport2 var3 = var0 == var2.field_1690.field_1886 ? C : (var0 == var2.field_1690.field_1904 ? M : null);
         if (var3 != null) {
            var3.key(var1);
            if (!MountRenderer5.cockpitActive() || !MountUpdater4.controlsAvailable(var2)) {
               var3.discard();
            }
         }
      }
   }

   public static boolean aimedAtScreen() {
      return O != null && MountRenderer5.cockpitActive() && MountUpdater4.controlsAvailable(class_310.method_1551());
   }

   public static float[] hoverUv() {
      return aimedAtScreen() ? (float[])O.clone() : null;
   }

   public static long touches() {
      return D;
   }

   public static boolean attack() {
      long var0 = 120811201947229L;
      long var2 = var0 ^ 35604531615367L;
      return l(var2, C);
   }

   public static boolean use() {
      long var0 = 122127233873639L;
      long var2 = var0 ^ 38017604978237L;
      return l(var2, M);
   }

   public static boolean blockAttackHold() {
      long var0 = 50967759596628L;
      int var2 = (int)((var0 ^ 103555023541796L) >>> 56);
      long var3 = (var0 ^ 103555023541796L) << 8 >>> 8;
      if (!aimedAtScreen()) {
         return false;
      } else {
         B((byte)var2, var3);
         return true;
      }
   }

   private static boolean l(long var0, MountSupport2 var2) {
      byte var3 = 0;
      long var4 = 123643842542192L;
      MountFactory.Data7.ensureSourceClassInitialized();
      boolean var6 = var2.take();
      if (!aimedAtScreen()) {
         return false;
      } else {
         if (var6) {
            MountRenderer2.click(O[0], O[1]);
            D++;
         }

         B(var3, var4);
         return true;
      }
   }

   private static void B(byte var0, long var1) {
      MountFactory.Data7.ensureSourceClassInitialized();
      class_636 var3 = class_310.method_1551().field_1761;
      if (var3 != null && var3.method_2923()) {
         var3.method_2925();
      }
   }

   static void tick(class_310 var0) {
      if (!MountRenderer5.cockpitActive()) {
         O = null;
      }

      if (!MountUpdater4.controlsAvailable(var0) || !MountRenderer5.cockpitActive() || var0.field_1724 == null || var0.field_1724.method_6115()) {
         C.discard();
         M.discard();
      }
   }

   static void closeOwned() {
      O = null;
      x = false;
      C.discard();
      M.discard();
   }
}
