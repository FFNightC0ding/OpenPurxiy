package dev.purxiy.modules.knife;

import dev.purxiy.duck.RenderPassHolder;
import net.minecraft.class_11659;
import net.minecraft.class_12249;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_3489;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4608;
import net.minecraft.class_4587.class_4665;
import org.joml.Vector3f;

public final class KnifeWieldable {
   private static final int[] R = new int[]{0, 1, 2, 2};
   private static final int[] a = new int[]{2, 1, 0, 0};
   private static final Vector3f y = new Vector3f(-0.29F, 0.52F, 0.8F).normalize();
   private static final String[] p = new String[]{"katana", "greatsword", "scythe"};
   private static final KnifeWieldPipeline.WieldStage[] t = new KnifeWieldPipeline.WieldStage[]{KnifeWieldPipeline.WieldStage.H, KnifeWieldPipeline.WieldStage.X, KnifeWieldPipeline.WieldStage.g};
   private static final int[][] x = new int[][]{{-1647361, -6521857}, {-2300686, -9470048}, {-1585921, -6005505}, {-5192, -22195}};
   private static final String[] c = new String[6];

   private KnifeWieldable() {
   }

   public static boolean chosen() {
      KnifeModule var0 = KnifeSwingState.Z;
      return var0.enabled() && KnifeBlades.index((String)var0.z.get()) >= 0;
   }

   public static KnifeApi wieldable(class_1799 var0) {
      KnifeModule var1 = KnifeSwingState.Z;
      if (var1.enabled() && !var0.method_7960() && var0.method_31573(class_3489.field_42611)) {
         int var2 = KnifeBlades.index((String)var1.z.get());
         return var2 < 0 ? null : new KnifeWieldable.Data(var2, var0.method_7909());
      } else {
         return null;
      }
   }

   static KnifeApi stageBlade(int var0) {
      return var0 >= 0 && var0 < KnifeBlades.B.length ? new KnifeWieldable.Data(var0, class_1802.field_8802) : null;
   }

   private static int q(char var0, short var1, int var2) {
      KnifeSwingState.ensureSourceClassInitialized();
      String var3 = (String)KnifeSwingState.Z.F.get();
      byte var4 = -1;
      switch (var3.hashCode()) {
         case -789545394:
            if (var3.equals("Doppler")) {
               var4 = 1;
            }
            break;
         case 2225280:
            boolean var5 = var3.equals("Gold");
            if (var5) {
               var4 = 2;
            }
            break;
         case 416515707:
            if (var3.equals("Obsidian")) {
               var4 = 0;
            }
      }
      return switch (var4) {
         case 0 -> 1;
         case 1 -> 2;
         case 2 -> 3;
         default -> 0;
      };
   }

   private static void V(KnifeBuilder var0, class_4665 var1, class_4588 var2, long var3, int var5) {
      KnifeSwingState.ensureSourceClassInitialized();
      int[] var6 = var1.method_23761().determinant3x3() < 0.0F ? a : R;

      for (byte var7 = 0; var7 < var0.vertexCount(); var7 = (byte)(var7 + 3)) {
         for (int var11 : var6) {
            long var12 = 48675860257984L;
            int var15 = var12 < 0L ? -1 : 1;
            KnifeBuilder.Data var15x = var0.B[var7 + var11];
            var2.method_56824(var1, var15x.x(), var15x.y(), var15x.z())
               .method_39415(-1)
               .method_22913((var15x.x() + var15x.z() * 0.5F) * 4.0F, var15x.y() * 4.0F)
               .method_22922(class_4608.field_21444)
               .method_60803(var5)
               .method_60831(var1, var15x.nx(), var15x.ny(), var15x.nz());
         }

         long var16 = 48675860257985L;
         int var16x = var16 < 0L ? -1 : 1;
      }
   }

   record Data(int c, class_1792 B) implements KnifeApi {
      @Override
      public String sheet() {
         return KnifeWieldable.p[this.c];
      }

      @Override
      public Object identity() {
         return this;
      }

      @Override
      public void submit(class_4587 var1, class_11659 var2, int var3, boolean var4, float var5) {
         long var6 = 62197367904421L;
         long var8 = var6 ^ 90733956475783L;
         int var10 = (int)((var6 ^ 90733956475783L) >>> 48);
         int var11 = (int)((var6 ^ 90733956475783L) << 16 >>> 48);
         int var12 = (int)(var8 << 32 >>> 32);
         KnifeModule var13 = KnifeSwingState.Z;
         KnifeBuilder var14 = KnifeBlades.get(this.c, (String)var13.F.get()).blade();
         Vector3f var15 = new Vector3f();
         Vector3f var16 = new Vector3f();
         KnifeBlades.blade(this.c, var15, var16);
         var2.method_73483(
            var1,
            class_12249.method_75982(KnifeCache.atlas(null)),
            new KnifeWieldable.WieldMesh(
               var14,
               var3,
               KnifeWieldable.y,
               var15,
               var16.sub(var15),
               var5 < 0.0F ? Float.NaN : var5,
               KnifeWieldable.x[KnifeWieldable.q((char)var10, (short)var11, var12)][0]
            )
         );
         if (var4) {
            var2.method_73483(var1, class_12249.method_75997(), (var2x, var3xx) -> {
               long var4x = 84947457649466L;
               long var6x = var4x ^ 106684376004603L;
               KnifeWieldable.V(var14, var2x, var3xx, var6x, var3);
            });
         }
      }

      @Override
      public void blade(Vector3f var1, Vector3f var2) {
         KnifeBlades.blade(this.c, var1, var2);
      }

      @Override
      public int light() {
         long var1 = 129239451387681L;
         long var3 = var1 ^ 34764382598147L;
         int var5 = (int)((var1 ^ 34764382598147L) >>> 48);
         int var6 = (int)((var1 ^ 34764382598147L) << 16 >>> 48);
         int var7 = (int)(var3 << 32 >>> 32);
         return KnifeWieldable.x[KnifeWieldable.q((char)var5, (short)var6, var7)][0];
      }

      @Override
      public int deep() {
         long var1 = 62513714138222L;
         long var3 = var1 ^ 91052409241420L;
         int var5 = (int)((var1 ^ 91052409241420L) >>> 48);
         int var6 = (int)((var1 ^ 91052409241420L) << 16 >>> 48);
         int var7 = (int)(var3 << 32 >>> 32);
         return KnifeWieldable.x[KnifeWieldable.q((char)var5, (short)var6, var7)][1];
      }

      @Override
      public KnifeWieldPipeline.WieldStage ambient() {
         return KnifeWieldable.t[this.c];
      }

      @Override
      public String sounds() {
         return this.c == 0 ? "blade" : "heavy";
      }

      @Override
      public float volume() {
         return ((Double)KnifeSwingState.Z.w.get()).floatValue();
      }

      @Override
      public boolean soundOn(boolean var1) {
         KnifeModule var2 = KnifeSwingState.Z;
         return var1 ? !var2.B.is("Sword") : var2.H.enabled();
      }

      @Override
      public boolean slashFx() {
         return KnifeSwingState.Z.c.enabled();
      }

      @Override
      public boolean hitFx() {
         return KnifeSwingState.Z.n.enabled();
      }

      @Override
      public boolean ambientFx() {
         return KnifeSwingState.Z.t.enabled();
      }

      @Override
      public float fov() {
         return ((Double)KnifeSwingState.Z.P.get()).floatValue();
      }

      @Override
      public float tempo() {
         return ((Double)KnifeSwingState.Z.C.get()).floatValue();
      }

      @Override
      public boolean draws() {
         return KnifeSwingState.Z.R.enabled();
      }

      @Override
      public float idleEvery() {
         KnifeModule var1 = KnifeSwingState.Z;
         return var1.p.enabled() ? ((Double)var1.A.get()).floatValue() : 0.0F;
      }

      @Override
      public boolean flows() {
         return KnifeSwingState.Z.r.enabled();
      }

      @Override
      public float flowPause() {
         return ((Double)KnifeSwingState.Z.q.get()).floatValue();
      }

      @Override
      public float flowTempo() {
         return ((Double)KnifeSwingState.Z.x.get()).floatValue();
      }

      @Override
      public boolean airCuts() {
         return KnifeSwingState.Z.D.enabled();
      }

      public int model() {
         return this.c;
      }

      public class_1792 item() {
         return this.B;
      }

      public int integerState39() {
         return this.c;
      }

      public class_1792 minecraftObjectState2() {
         return this.B;
      }
   }

   private record WieldMesh(KnifeBuilder J, int t, Vector3f A, Vector3f Y, Vector3f E, float I, int T) implements RenderPassHolder {
      public void render(class_4665 var1, class_4588 var2) {
         Vector3f var3 = new Vector3f();
         Vector3f var4 = new Vector3f();
         int[] var5 = var1.method_23761().determinant3x3() < 0.0F ? KnifeWieldable.a : KnifeWieldable.R;
         float var6 = (this.T >> 16 & 0xFF) / 255.0F;
         float var7 = (this.T >> 8 & 0xFF) / 255.0F;
         float var8 = (this.T & 0xFF) / 255.0F;
         boolean var9 = !Float.isNaN(this.I);
         float var10 = this.E.length();
         float var11 = 1.0F / Math.max(1.0E-4F, this.E.lengthSquared());

         for (byte var12 = 0; var12 < this.J.vertexCount(); var12 = (byte)(var12 + 3)) {
            for (int var16 : var5) {
               KnifeBuilder.Data var17 = this.J.B[var12 + var16];
               var1.method_56821(var4.set(var17.nx(), var17.ny(), var17.nz()), var3);
               float var18 = (float)Math.pow(Math.max(0.0F, Math.abs(var3.dot(this.A))), 36.0) * var17.metal() * 0.42F;
               float var19 = 0.0F;
               if (var9 && var17.metal() > 0.5F) {
                  float var20 = ((var17.x() - this.Y.x) * this.E.x + (var17.y() - this.Y.y) * this.E.y + (var17.z() - this.Y.z) * this.E.z) * var11;
                  float var21 = (var20 - this.I) * var10 / 0.045F;
                  var19 = (float)Math.exp(-var21 * var21) * 0.85F;
               }

               int var24 = var17.color();
               int var25 = Math.min(255, (var24 >> 16 & 0xFF) + (int)(var18 * 225.0F + var19 * 255.0F * var6));
               int var22 = Math.min(255, (var24 >> 8 & 0xFF) + (int)(var18 * 238.0F + var19 * 255.0F * var7));
               int var23 = Math.min(255, (var24 & 0xFF) + (int)(var18 * 255.0F + var19 * 255.0F * var8));
               var2.method_56824(var1, var17.x(), var17.y(), var17.z())
                  .method_1336(var25, var22, var23, 255)
                  .method_22913(var17.u(), var17.v())
                  .method_22922(class_4608.field_21444)
                  .method_60803(var19 > 0.05F ? 15728880 : this.t)
                  .method_60831(var1, var17.nx(), var17.ny(), var17.nz());
            }
         }
      }

      public KnifeBuilder mesh() {
         return this.J;
      }

      public int light() {
         return this.t;
      }

      public Vector3f sheen() {
         return this.A;
      }

      public Vector3f start() {
         return this.Y;
      }

      public Vector3f along() {
         return this.E;
      }

      public float glint() {
         return this.I;
      }

      public int glow() {
         return this.T;
      }

      public KnifeBuilder knifeMaterialMeshState5() {
         return this.J;
      }

      public int integerState14() {
         return this.t;
      }

      public Vector3f vector3fState1() {
         return this.A;
      }

      public Vector3f vector3fState50() {
         return this.Y;
      }

      public Vector3f vector3fState51() {
         return this.E;
      }

      public float floatState40() {
         return this.I;
      }

      public int integerState36() {
         return this.T;
      }
   }
}
