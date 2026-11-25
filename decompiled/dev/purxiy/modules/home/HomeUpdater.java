package dev.purxiy.modules.home;

import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.loader.fabric.client.ClientRenderer8;
import dev.purxiy.loader.fabric.client.ClientRenderer8.Listener2;
import dev.purxiy.render.RenderPipelines;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.class_1041;
import net.minecraft.class_1059;
import net.minecraft.class_10725;
import net.minecraft.class_1109;
import net.minecraft.class_156;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_437;
import net.minecraft.class_442;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;

public final class HomeUpdater {
   private static final HomeUpdater e = new HomeUpdater(8268405725055L);
   private static final long c = 20261003L;
   private static final float T = 64.0F;
   private final HomeAutoCloseable N = new HomeAutoCloseable();
   private final HomeUpdater2 H = new HomeUpdater2();
   private final HomeCodec R = new HomeCodec();
   private final HomeLoader I = new HomeLoader();
   private final HomeAutoCloseable.TypeI o = new HomeAutoCloseable.TypeI();
   private final HomeUpdater.TypeG y = new HomeUpdater.TypeG();
   private HomeCurve K;
   private HomeSupport5 B;
   private HomeSupport S;
   private HomeSupport A;
   private HomeCodec2 i;
   private CompletableFuture<HomeUpdater.TypeF> h;
   private CompletableFuture<HomeUpdater.TypeK> r;
   private long Z = 20261003L;
   private String U = "Mixed";
   private boolean a;
   private String W = "";
   private double d;
   private double s = -1000000.0;
   private double O = -1000000.0;
   private double l = -1.0;
   private boolean X;
   private boolean g = true;
   private long k = -1L;
   private int F;
   private long v;
   private boolean P;
   private float E;
   private float w;
   private final Animator M = Animator.smooth(0.0F);
   private final Animator n = Animator.smooth(0.0F);
   private final Animator t = Animator.smooth(0.0F);
   private final Animator D = Animator.smooth(0.0F);
   private final Animator Q = Animator.smooth(0.0F);
   private final Animator u = Animator.smooth(0.22F);
   private final List<HomeSupport.TypeV> G = new ArrayList<>();
   private final List<HomeSupport.TypeV> V = new ArrayList<>();
   private int f;
   private int b;
   private double j = -1.0;
   private final ExecutorService Y = ClientServices.visualExecutor("Purxiy menu world");
   static HomeModule z;
   private static ModuleContext J;
   private static int q = 0;
   private static int C = 0;
   private static final String[] p = new String[14];
   private static final long[] x = new long[]{
      -5343583433478640083L,
      2259833711376580702L,
      8761249935165991303L,
      4185001596081865460L,
      -2213806280413738085L,
      2142419733923771799L,
      6556103606547221727L,
      -8787415214281368016L
   };
   private static final Integer[] L = new Integer[8];
   private static final long[] ab = new long[]{2144522621137090870L, -8260300993729045209L, 6363001649829147347L, -7987722864500981968L};
   private static final Long[] bb = new Long[4];

   private HomeUpdater(long var1) {
   }

   public static HomeUpdater get() {
      return e;
   }

   public boolean drawBackground(class_437 var1) {
      long var2 = 6302115746173L;
      long var4 = var2 ^ 125258498708965L;
      long var6 = var2 ^ 29988929652436L;
      class_310 var8 = class_310.method_1551();
      if (!this.a && z.worldEnabled() && var8.field_1687 == null) {
         RenderPipelines var9 = RenderPipelines.get();
         if (this.k == var9.frame()) {
            return true;
         } else {
            try {
               float var10 = this.P ? 0.0F : Math.min(var9.delta(), 0.05F);
               this.k = var9.frame();
               this.v = System.nanoTime();
               this.d += var10;
               this.A(var4);
               if (this.l < 0.0 && !this.X && !HomeRenderer.holdsIntro()) {
                  this.beginIntro();
               }

               this.X(var1, var10, var6, var8);
               this.N.render(this.o);
               this.F++;
               ClientRenderer8.draw(var1, this.y);
               return true;
            } catch (Throwable var121) {
               metric("draw_failures");
               String var11 = var121.toString();
               if (!var11.equals(this.W)) {
                  this.W = var11;
                  ClientServices.LOGGER.error("Purxiy main menu world failed to draw; Minecraft's panorama takes over", var121);
               }

               this.a = true;
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public boolean readyForIntro() {
      if (this.a) {
         return true;
      } else if (this.B != null && this.S != null && this.i != null && this.h == null && this.r == null) {
         try {
            if (this.i.d != HomeCodec2.key(class_310.method_1551().method_72703().method_73025(class_10725.field_56382))) {
               return false;
            }
         } catch (RuntimeException var2) {
            return false;
         }

         return this.N.x.F > 0 && this.F > 2 && RenderPipelines.get().frame() - this.k <= 2L;
      } else {
         return false;
      }
   }

   public boolean drawnThisFrame() {
      return this.k == RenderPipelines.get().frame();
   }

   public boolean drawnLately() {
      return RenderPipelines.get().frame() - this.k <= 3L;
   }

   public void tick() {
      if (this.v != 0L && System.nanoTime() - this.v > 8000000000L) {
         this.N.releaseTarget();
         this.v = 0L;
      }
   }

   private void X(class_437 var1, float var2, long var3, class_310 var5) {
      long var6 = 122565964136385L;
      long var8 = 51606980832329L;
      long var10 = 120070927144676L;
      HomeModule var12 = z;
      HomeUpdater2.TypeZ var13 = this.H.update((String)var12.M.get(), this.d, 2.2);
      HomeSupport2.ensureSourceClassInitialized();
      HomeAutoCloseable.TypeI var14 = this.o;
      var14.v = var13;
      var14.X = this.i;
      var14.k = (float)(this.d % 3600.0);
      boolean var15 = MotionStyle.reduced();
      var14.F = var15 ? 0.25F : 1.0F;
      boolean var16 = var1 instanceof class_442 || var1.getClass().getName().startsWith("dev.purxiy.");
      float var17 = MotionStyle.MENU.dt(var2 == 0.0F ? RenderPipelines.get().delta() : var2);
      Animator var18 = this.D.target(var16 ? 0.0F : 1.0F);
      long var19 = 34072044836777L;
      int var22 = var19 < 0L ? -1 : 1;
      var14.m = var18.update(var17);
      var18 = this.Q.target(var16 ? 0.0F : 0.5F);
      long var22x = 34072044836776L;
      int var24 = var22x < 0L ? -1 : 1;
      var14.D = var18.update(var17);
      var14.M = this.u.target(var16 ? 0.2F : 0.36F).update(var17);
      var14.q = 1.0F - 0.12F * var14.D;
      this.B(var2, var6, var5, var14);
      this.m(var14, var8);
      this.Q(var10, var14);
      HomeSupport var24x = this.S;
      float var25 = var24x == null ? 0.0F : (float)Math.min(1.0, (this.d - this.s) / Math.max(0.1, var24x.j + 0.3));
      long var26 = 34072044836777L;
      int var28 = var26 < 0L ? -1 : 1;
      if (this.B != null && this.i != null) {
         this.R.update(var2, this.B, var24x, var25, var13.d, var15 ? 0.4F : 1.0F);
         long var28x = 34072044836777L;
         int var29 = var28x < 0L ? -1 : 1;
         if (this.K == null) {
            this.K = new HomeCurve(800);
         }

         this.K.reset();
         this.R.write(this.K, this.i, var14.k);
         this.N.D.upload(this.K.data(), this.K.quads(), null);
      }
   }

   private void B(float var1, long var2, class_310 var4, HomeAutoCloseable.TypeI var5) {
      HomeSupport2.ensureSourceClassInitialized();
      HomeModule var6 = z;
      HomeSupport5 var7 = this.B;
      boolean var8 = MotionStyle.reduced();
      float var9 = var6.q.is("Orbit") && !var8 ? (float)(1.7 * (Double)var4.field_1690.method_45581().method_41753()) : 0.0F;
      this.E = this.E + var1 * this.M.target(var9).update(var1);
      float var10 = this.w + this.E;
      float var11 = 9.0F;
      float var12 = var7 != null ? var7.x : 0.5F;
      float var13 = var7 != null ? var7.y : 70.0F;
      float var14 = var7 != null ? var7.E : 0.5F;
      if (var6.q.is("Drift") && !var8) {
         var10 += (float)Math.sin(this.d * 0.045) * 24.0F;
         var11 += (float)Math.sin(this.d * 0.07) * 1.6F;
         var13 += (float)Math.sin(this.d * 0.33) * 0.18F;
      }

      boolean var15;
      label85: {
         class_1041 var16 = var4.method_22683();
         float var17 = (float)(var4.field_1729.method_1603() / Math.max(1, var16.method_4480())) - 0.5F;
         float var18 = (float)(var4.field_1729.method_1604() / Math.max(1, var16.method_4507())) - 0.5F;
         float var19 = MotionStyle.HOVER.dt(var1) * 0.35F;
         Animator var20 = this.n.target(var8 ? 0.0F : var17);
         long var21 = 122565964136385L;
         var15 = (boolean)(var21 < 0L ? -1 : 1);
         var10 += var20.update(var19) * 3.2F;
         var11 += this.t.target(var8 ? 0.0F : var18).update(var19) * 2.0F;
         if (this.l < 0.0) {
            long var24 = 122565964136385L;
            int var36 = var24 < 0L ? -1 : 1;
            if (!this.X && HomeRenderer.holdsIntro()) {
               var15 = (boolean)1;
               break label85;
            }
         }

         var15 = (boolean)0;
      }

      if (this.l >= 0.0 || var15) {
         float var29 = var15 ? 0.0F : (float)Math.min(1.0, (this.d - this.l) / 2.7);
         float var301 = 1.0F - (float)Math.pow(1.0F - var29, 3.0);
         long var34 = 122565964136385L;
         int var37 = var34 < 0L ? -1 : 1;
         var301 = var301 * var301 * (3.0F - 2.0F * var301);
         var11 = HomeUpdater2.lerp(-20.0F, var11, var301);
         var13 += (1.0F - var301) * 2.5F;
         long var22 = 122565964136385L;
         boolean var34x = (var22 < 0L ? -1 : 1) != 0;
         var10 -= (1.0F - var301) * 16.0F;
         if (var29 >= 1.0F) {
            this.l = -1.0;
            this.X = true;
         }
      }

      class_276 var30 = var4.method_1522();
      float var32 = (float)Math.max(1, var30.field_1482) / Math.max(1, var30.field_1481);
      Matrix4f var35 = new Matrix4f().rotateX((float)Math.toRadians(var11)).rotateY((float)Math.toRadians(var10)).translate(-var12, -var13, -var14);
      Matrix4f var36 = new Matrix4f().perspective((float)Math.toRadians(64.0), var32, 0.05F, 420.0F);
      var5.s.set(var36).mul(var35);
      var5.s.invert(var5.Y);
      var5.V.set(var36);
      var5.A = -var35.m02();
      var5.Z = -var35.m12();
      var5.B = -var35.m22();
      var5.z = var12;
      var5.S = var13;
      var5.I = var14;
      var5.U = var35.m00();
      var5.l = var35.m10();
      var5.G = var35.m20();
      var5.T = var35.m01();
      var5.o = var35.m11();
      var5.d = var35.m21();
   }

   private void m(HomeAutoCloseable.TypeI var1, long var2) {
      long var4 = 5441323555439L;
      HomeSupport2.ensureSourceClassInitialized();
      HomeSupport var6 = this.S;
      var1.L = (float)Math.min(1000000.0, this.d - this.s);
      HomeSupport var7 = this.A;
      if (var7 != null) {
         var1.i = (float)(this.d - this.O);
         long var8 = 51606980832328L;
         int var26 = var8 < 0L ? -1 : 1;
         var1.u = var7.j;
         if (var1.i > var7.j * 0.3F + 0.4F) {
            this.A = null;
            this.N.U.clear();
         }
      } else {
         var1.i = 1000000.0F;
      }

      if (var6 != null) {
         double var24 = this.d - this.s;

         while (this.f < this.G.size()) {
            long var10 = 51606980832328L;
            int var27 = var10 < 0L ? -1 : 1;
            Object var13 = this.G.get(this.f);
            long var14 = 51606980832328L;
            int var29 = var14 < 0L ? -1 : 1;
            if (!(((HomeSupport.TypeV)var13).H <= var24)) {
               break;
            }

            HomeSupport.TypeV var16 = this.G.get(this.f++);
            long var17 = 51606980832329L;
            int var19 = var17 < 0L ? -1 : 1;
            double var19x;
            int var21 = (var19x = var24 - var16.H - 0.5) == 0.0 ? 0 : (var19x < 0.0 ? -1 : 1);
            long var22 = 51606980832329L;
            int var23 = var22 < 0L ? -1 : 1;
            if (var21 < 0) {
               this.R.boneMeal(var16.C + 0.5F, var16.d + 0.15F, var16.w + 0.5F, 9, 0.9F);
               this.L(class_3417.field_33433, var16.C + 0.5F, var4, var16.d, var16.w + 0.5F, 0.32F, 0.9F + (var16.C * 7 + var16.w * 13 & 7) * 0.04F);
            }
         }

         while (true) {
            long var25 = 51606980832328L;
            int var291 = var25 < 0L ? -1 : 1;
            if (this.b >= this.V.size()) {
               break;
            }

            long var27x = 51606980832328L;
            int var14x = var27x < 0L ? -1 : 1;
            if (!(this.V.get(this.b).T <= var24)) {
               break;
            }

            HomeSupport.TypeV var28x = this.V.get(this.b++);
            if (var24 - var28x.T < 0.5 && var28x.h > 0.0F) {
               this.R.boneMeal(var28x.q, var28x.W - 0.5F, var28x.G, 6, var28x.h * 1.6F);
               class_3414 var29x = var28x.cherry() ? class_3417.field_42559 : class_3417.field_28558;
               this.L(var29x, var28x.q, var4, var28x.W, var28x.G, 0.22F, 0.95F + (var28x.C * 5 + var28x.w * 3 & 7) * 0.03F);
            }
         }

         long var26 = 51606980832329L;
         int var31 = var26 < 0L ? -1 : 1;
      }
   }

   private void L(class_3414 var1, float var2, long var3, float var5, float var6, float var7, float var8) {
      HomeSupport2.ensureSourceClassInitialized();
      if (UiAudioPolicy.active() && !(this.d - this.j < 0.05)) {
         HomeAutoCloseable.TypeI var9 = this.o;
         float var10 = var2 - var9.z;
         float var11 = var5 - var9.S;
         float var12 = var6 - var9.I;
         float var13 = (float)Math.sqrt(var10 * var10 + var11 * var11 + var12 * var12);
         float var14 = var7 * (1.0F - HomeSupport5.smooth(6.0F, 42.0F, var13));
         long var15 = 5441323555438L;
         int var17 = var15 < 0L ? -1 : 1;
         if (!(var14 < 0.03F)) {
            this.j = this.d;
            class_310.method_1551().method_1483().method_4873(class_1109.method_4757(var1, var8, Math.min(1.0F, var14)));
         }
      }
   }

   public void beginIntro() {
      if (!this.X) {
         this.l = this.d;
         if (z.z.enabled() && !MotionStyle.reduced()) {
            this.s = this.d + 0.9;
            this.f = this.b = 0;
         }
      }
   }

   public void regrow() {
      long var1 = 97170727657632L;
      long var3 = var1 ^ 12152625936528L;
      if (this.B != null && this.i != null && this.r == null) {
         long var5 = class_156.method_648() ^ -7046029254386353131L * System.identityHashCode(this);
         z.H.set(Long.toHexString(var5));
         this.p(var5, (String)z.X.get(), true, var3);
      }
   }

   public boolean plant(double var1, double var3) {
      long var5 = 38535823094201L;
      long var7 = var5 ^ 123564066613641L;
      HomeSupport5 var9 = this.B;
      HomeSupport var10 = this.S;
      class_310 var11 = class_310.method_1551();
      if (var9 != null && var10 != null && this.i != null && this.r == null && var11.field_1755 != null && RenderPipelines.get().frame() - this.k <= 2L) {
         float var12 = (float)(var1 / Math.max(1, var11.field_1755.field_22789)) * 2.0F - 1.0F;
         float var13 = 1.0F - (float)(var3 / Math.max(1, var11.field_1755.field_22790)) * 2.0F;
         Vector4f var14 = new Vector4f(var12, var13, -1.0F, 1.0F).mul(this.o.Y);
         Vector4f var15 = new Vector4f(var12, var13, 1.0F, 1.0F).mul(this.o.Y);
         var14.div(var14.w);
         var15.div(var15.w);
         float var16 = var15.x - var14.x;
         float var17 = var15.y - var14.y;
         float var18 = var15.z - var14.z;
         float var19 = (float)Math.sqrt(var16 * var16 + var17 * var17 + var18 * var18);
         float var20 = var16 / var19;
         float var21 = var17 / var19;
         float var22 = var18 / var19;

         for (float var23 = 1.0F; var23 < 70.0F; var23 += 0.15F) {
            float var24 = this.o.z + var20 * var23;
            float var25 = this.o.S + var21 * var23;
            float var26 = this.o.I + var22 * var23;
            int var27 = (int)Math.floor(var24);
            int var28 = (int)Math.floor(var26);
            if (!HomeSupport5.inside(var27, var28)) {
               return false;
            }

            if (var25 <= var9.heightAt(var27, var28) + 1) {
               if (var27 * var27 + var28 * var28 >= 64 && !var9.water(var27, var28)) {
                  HomeSupport.TypeV var29 = var10.plant(var9, var27, var28, (float)(this.d - this.s) + 0.05F);
                  if (var29 == null) {
                     return false;
                  }

                  this.R.boneMeal(var27 + 0.5F, var29.d + 0.2F, var28 + 0.5F, 12, 1.1F);
                  this.p(var10.D, var10.O, false, var7);
                  return true;
               }

               return false;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public boolean growing() {
      HomeSupport var1 = this.S;
      return this.r != null || var1 != null && this.d - this.s < var1.j + 0.3 && this.d >= this.s - 1.0;
   }

   public float growProgress() {
      HomeSupport var1 = this.S;
      return this.r == null && var1 != null ? (float)Math.max(0.0, Math.min(1.0, (this.d - this.s) / (var1.j + 0.3))) : 0.0F;
   }

   private void A(long var1) {
      long var3 = 98871077874540L;
      long var5 = 130792993036901L;
      long var7 = 91731144464432L;
      HomeSupport2.ensureSourceClassInitialized();
      class_310 var9 = class_310.method_1551();

      class_1059 var10;
      try {
         var10 = var9.method_72703().method_73025(class_10725.field_56382);
      } catch (RuntimeException var22) {
         return;
      }

      label67: {
         Object var11 = HomeCodec2.key(var10);
         long var12 = 127918471431320L;
         int var26 = var12 < 0L ? -1 : 1;
         if (this.i != null) {
            long var15 = 127918471431319L;
            int var161 = var15 < 0L ? -1 : 1;
            if (this.i.d == var11) {
               break label67;
            }
         }

         HomeCodec2 var26x = HomeCodec2.read();
         long var16 = 127918471431319L;
         int var19 = var16 < 0L ? -1 : 1;
         if (var26x != null) {
            this.i = var26x;
            long var19x = 127918471431320L;
            int var19x = var19x < 0L ? -1 : 1;
            this.I.load();
            long var21x = 127918471431320L;
            int var21x = var21x < 0L ? -1 : 1;
            if (this.h == null) {
               this.f(var3);
            }

            if (this.S != null && this.r == null) {
               this.p(this.S.D, this.S.O, false, var7);
            }
         }
      }

      this.x(var5);
      Object var23 = z.X.get();
      if (this.S != null && this.r == null && !var23.equals(this.U)) {
         this.regrow();
      }
   }

   private void f(long var1) {
      HomeCodec2 var3 = this.i;
      long var4 = this.Z;
      HomeSupport5 var6 = this.B;
      this.h = CompletableFuture.supplyAsync(() -> {
         HomeSupport2.ensureSourceClassInitialized();
         long var4x = 65279772945437L;
         int var7 = var4x < 0L ? -1 : 1;
         HomeSupport5 var7x = var6 != null && var6.l == var4 ? var6 : new HomeSupport5(var4);
         HomeCurve[] var8 = HomeSupport3.terrain(var7x, var3);
         return new HomeUpdater.TypeF(var7x, var8[0], var8[1], var3);
      }, this.Y);
   }

   private void p(long var1, String var3, boolean var4, long var5) {
      HomeSupport5 var7 = this.B;
      HomeCodec2 var8 = this.i;
      HomeSupport var9 = var4 ? null : this.S;
      this.U = var3;
      this.r = CompletableFuture.supplyAsync(() -> {
         HomeSupport2.ensureSourceClassInitialized();
         HomeSupport var7x = var9 != null && var9.D == var1 ? var9 : new HomeSupport(var7, var1, var3);
         return new HomeUpdater.TypeK(var7x, HomeSupport3.forest(var7, var7x, var8), var8, var4);
      }, this.Y);
   }

   private void x(long var1) {
      long var3 = 98871077874540L;
      long var5 = 85166145039120L;
      short var7 = 19829;
      int var8 = 4837995;
      int var9 = (int)(var5 << 56 >>> 56);
      long var10 = 91731144464432L;
      var5 = 6766698459251L;
      short var12 = 1575;
      short var13 = 32424;
      int var14 = (int)(var5 << 48 >>> 48);
      long var15 = 132636898839435L;
      HomeSupport2.ensureSourceClassInitialized();
      CompletableFuture var17 = this.h;
      if (var17 != null && var17.isDone()) {
         long var18 = 130792993036900L;
         int var21 = var18 < 0L ? -1 : 1;
         this.h = null;
         HomeUpdater.TypeF var21x = (HomeUpdater.TypeF)this.W(var15, var17);
         long var22 = 130792993036900L;
         int var25 = var22 < 0L ? -1 : 1;
         if (var21x != null) {
            if (var21x.n == this.i) {
               this.N.x.upload(var21x.z.data(), var21x.z.quads(), var21x.n.d);
               this.N.k.upload(var21x.d.data(), var21x.d.quads(), var21x.n.d);
               boolean var25x = this.B == null;
               long var26 = 130792993036900L;
               int var26x = var26 < 0L ? -1 : 1;
               this.B = var21x.q;
               long var29 = 130792993036901L;
               int var281 = var29 < 0L ? -1 : 1;
               if (var25x) {
                  this.w = (float)Math.toDegrees(Math.atan2(var21x.q.j, -var21x.q.s)) + 26.0F;
               }
            } else {
               this.f(var3);
            }

            var21x.z.free();
            var21x.d.free();
         }
      }

      if (this.B != null && this.S == null && this.r == null && this.i != null) {
         this.p(this.L(var12, var13, var14), (String)z.X.get(), false, var10);
      }

      CompletableFuture var39 = this.r;
      long var19 = 130792993036900L;
      int var38 = var19 < 0L ? -1 : 1;
      if (var39 != null && var39.isDone()) {
         this.r = null;
         HomeUpdater.TypeK var40 = (HomeUpdater.TypeK)this.W(var15, var39);
         long var23 = 130792993036901L;
         int var411 = var23 < 0L ? -1 : 1;
         if (var40 == null) {
            return;
         }

         long var41x = 130792993036901L;
         int var44 = var41x < 0L ? -1 : 1;
         if (var40.X != this.i) {
            var40.k.free();
            this.p(var40.A.D, var40.A.O, false, var10);
            return;
         }

         label130: {
            var28 = var40.X.d;
            if (var40.f) {
               long var42 = 130792993036901L;
               int var32 = var42 < 0L ? -1 : 1;
               if (this.S != null) {
                  HomeAutoCloseable.TypeH var31 = this.N.X;
                  this.N.X = this.N.U;
                  this.N.U = var31;
                  this.A = this.S;
                  this.O = this.d;

                  for (HomeSupport.TypeV var33 : this.A.m) {
                     long var34 = 130792993036901L;
                     int var34x = var34 < 0L ? -1 : 1;
                     this.R.scatter(var33, var33.cherry() ? 3 : 2, 7842607);
                  }

                  this.s = this.d + 0.45;
                  long var45 = 130792993036901L;
                  int var53 = var45 < 0L ? -1 : 1;
                  this.f = this.b = 0;
                  break label130;
               }
            }

            if (this.g) {
               long var43 = 130792993036901L;
               int var47 = var43 < 0L ? -1 : 1;
               this.g = false;
               long var32 = 130792993036900L;
               int var35 = var32 < 0L ? -1 : 1;
               if (this.s > -100000.0) {
                  long var35x = 130792993036901L;
                  int var33 = var35x < 0L ? -1 : 1;
                  this.s = Math.max(this.s, this.d + 0.15);
               } else {
                  this.s = this.d - 100000.0;
               }
            }
         }

         this.N.X.upload(var40.k.data(), var40.k.quads(), var28);
         var40.k.free();
         this.S = var40.A;
         this.x(var7, var8, (byte)var9, var40.A);
      }
   }

   private void x(int var1, int var2, byte var3, HomeSupport var4) {
      this.G.clear();
      this.G.addAll(var4.m);
      HomeSupport2.ensureSourceClassInitialized();
      this.G.sort(Comparator.comparingDouble(var0 -> var0.H));
      this.V.clear();
      this.V.addAll(var4.m);
      this.V.sort(Comparator.comparingDouble(var0 -> var0.T));
      double var5 = this.d - this.s;

      for (this.f = 0; this.f < this.G.size(); this.f++) {
         double var7;
         int var9 = (var7 = this.G.get(this.f).H - (var5 - 0.5)) == 0.0 ? 0 : (var7 < 0.0 ? -1 : 1);
         if (var9 >= 0) {
            break;
         }
      }

      int var10 = 19829;
      this.b = 0;

      while (this.b < this.V.size() && this.V.get(this.b).T < var5 - 0.5) {
         this.b++;
      }

      var10 = 4837995;
   }

   private Object W(long var1, CompletableFuture var3) {
      try {
         return var3.join();
      } catch (RuntimeException var5) {
         ClientServices.LOGGER.error("Purxiy main menu world could not be built", var5);
         this.a = true;
         return null;
      }
   }

   private long L(int var1, short var2, int var3) {
      HomeSupport2.ensureSourceClassInitialized();
      String var4 = (String)z.H.get();
      if (!var4.isEmpty()) {
         try {
            return Long.parseUnsignedLong(var4, 16);
         } catch (NumberFormatException var7) {
         }
      }

      long var5 = class_156.method_648() * -7046029254386353131L;
      z.H.set(Long.toHexString(var5));
      return var5;
   }

   private void Q(long var1, HomeAutoCloseable.TypeI var3) {
      HomeSupport2.ensureSourceClassInitialized();
      float var4 = (float)(this.d * 0.6F);
      var3.W = var4;
      if (this.I.needsBuild(var4)) {
         HomeCurve var5 = this.I.build(var4, var3.S + 72.0F);
         if (var5 != null) {
            this.N.d.upload(var5.data(), var5.quads(), null);
            var5.free();
         }
      }
   }

   static void persist() {
      try {
         CosmeticRegistry.storeSettings("main_menu", z.values());
      } catch (IOException var1) {
         ClientServices.LOGGER.warn("Could not save menu scene settings");
      }
   }

   static void play(HomeKind var0) {
      UiAudioPolicy.play(var0.name());
   }

   void dispose() {
      this.Y.shutdown();

      try {
         if (!this.Y.awaitTermination(8L, TimeUnit.SECONDS)) {
            this.Y.shutdownNow();
            throw new IllegalStateException("Menu scene worker did not stop");
         }
      } catch (InterruptedException var6) {
         this.Y.shutdownNow();
         Thread.currentThread().interrupt();
         throw new IllegalStateException("Menu scene worker interrupted");
      } finally {
         if (this.h != null && this.h.isDone() && !this.h.isCompletedExceptionally()) {
            HomeUpdater.TypeF var3 = this.h.join();
            var3.z.free();
            var3.d.free();
         }

         if (this.r != null && this.r.isDone() && !this.r.isCompletedExceptionally()) {
            this.r.join().k.free();
         }

         this.h = null;
         this.r = null;
         if (this.K != null) {
            this.K.free();
            this.K = null;
         }

         this.N.close();
         this.B = null;
         this.S = null;
         this.A = null;
         this.i = null;
         this.G.clear();
         this.V.clear();
      }
   }

   public static void install(HomeModule var0, ModuleContext var1) {
      z = var0;
      J = var1;
   }

   public static void metric(String var0) {
      if (J != null) {
         J.metric(var0, var0.equals("drawn") ? ++q : ++C);
      }
   }

   public static void uninstall() {
      try {
         get().dispose();
      } finally {
         HomeRenderer2.closeTextures();
      }

      z = null;
      J = null;
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 4781) & 32767;
      if (bb[var3] == null) {
         bb[var3] = ab[var3] ^ var1;
      }

      return bb[var3];
   }

   private record TypeF(HomeSupport5 q, HomeCurve z, HomeCurve d, HomeCodec2 n) {
      public HomeSupport5 world() {
         return this.q;
      }

      public HomeCurve solid() {
         return this.z;
      }

      public HomeCurve water() {
         return this.d;
      }

      public HomeCodec2 sprites() {
         return this.n;
      }

      public HomeSupport5 proceduralTerrainHeightsState2() {
         return this.q;
      }

      public HomeCurve terrainMeshBuilderState2() {
         return this.z;
      }

      public HomeCurve terrainMeshBuilderState3() {
         return this.d;
      }

      public HomeCodec2 blockTextureSamplerState2() {
         return this.n;
      }
   }

   private final class TypeG implements Listener2 {
      private final Vector3f P = new Vector3f();
      private final Vector3f n = new Vector3f();
      private final Vector3f v = new Vector3f();
      private final Vector3f x = new Vector3f();

      public Matrix4fc viewProj() {
         return HomeUpdater.this.o.s;
      }

      public Matrix4fc projection() {
         return HomeUpdater.this.o.V;
      }

      public Vector3fc eye() {
         HomeAutoCloseable.TypeI var1 = HomeUpdater.this.o;
         return this.P.set(var1.z, var1.S, var1.I);
      }

      public Vector3fc right() {
         HomeAutoCloseable.TypeI var1 = HomeUpdater.this.o;
         return this.n.set(var1.U, var1.l, var1.G);
      }

      public Vector3fc up() {
         HomeAutoCloseable.TypeI var1 = HomeUpdater.this.o;
         return this.v.set(var1.T, var1.o, var1.d);
      }

      public Vector3fc forward() {
         HomeAutoCloseable.TypeI var1 = HomeUpdater.this.o;
         return this.x.set(var1.A, var1.Z, var1.B);
      }

      public GpuTextureView depth() {
         return HomeUpdater.this.N.depth();
      }

      public float trace(float var1, float var2, float var3, float var4, int[] var5) {
         HomeSupport5 var6 = HomeUpdater.this.B;
         HomeSupport var7 = HomeUpdater.this.S;
         if (var6 == null) {
            return Float.POSITIVE_INFINITY;
         } else {
            HomeAutoCloseable.TypeI var8 = HomeUpdater.this.o;
            float var9 = var8.z;
            float var10 = var8.S;
            float var11 = var8.I;
            int var12 = (int)Math.floor(var9);
            int var13 = (int)Math.floor(var10);
            int var14 = (int)Math.floor(var11);
            int var15 = var1 > 0.0F ? 1 : -1;
            int var16 = var2 > 0.0F ? 1 : -1;
            int var17 = var3 > 0.0F ? 1 : -1;
            float var18 = var1 != 0.0F ? Math.abs(1.0F / var1) : Float.POSITIVE_INFINITY;
            float var19 = var2 != 0.0F ? Math.abs(1.0F / var2) : Float.POSITIVE_INFINITY;
            float var20 = var3 != 0.0F ? Math.abs(1.0F / var3) : Float.POSITIVE_INFINITY;
            float var21 = var1 != 0.0F ? (var1 > 0.0F ? var12 + 1 - var9 : var9 - var12) * var18 : Float.POSITIVE_INFINITY;
            float var22 = var2 != 0.0F ? (var2 > 0.0F ? var13 + 1 - var10 : var10 - var13) * var19 : Float.POSITIVE_INFINITY;
            float var23 = var3 != 0.0F ? (var3 > 0.0F ? var14 + 1 - var11 : var11 - var14) * var20 : Float.POSITIVE_INFINITY;
            float var24 = 63.875F;
            float var25 = 0.0F;

            for (int var26 = 0; var26 < 2048 && var25 <= var4; var26++) {
               int var27 = var6.heightAt(var12, var14);
               if (var13 <= var27) {
                  var5[0] = 0;
                  return var25;
               }

               if (var13 >= var27 + 2 && var13 <= var27 + var6.plantHeight(var12, var14)) {
                  var5[0] = 2;
                  return var25;
               }

               if (var13 <= 63 && var6.water(var12, var14)) {
                  var5[0] = 1;
                  return var13 >= 63 && !(var2 >= 0.0F) ? Math.max(var25, (var24 - var10) / var2) : var25;
               }

               if (var7 != null) {
                  HomeSupport.TypeP var28 = var7.M.get(HomeSupport.pack(var12, var13, var14));
                  if (var28 != null && var28.R <= 3) {
                     var5[0] = 2;
                     return var25;
                  }
               }

               if (var21 < var22 && var21 < var23) {
                  var25 = var21;
                  var21 += var18;
                  var12 += var15;
               } else if (var22 < var23) {
                  var25 = var22;
                  var22 += var19;
                  var13 += var16;
               } else {
                  var25 = var23;
                  var23 += var20;
                  var14 += var17;
               }
            }

            return Float.POSITIVE_INFINITY;
         }
      }

      public float ground(float var1, float var2) {
         HomeSupport5 var3 = HomeUpdater.this.B;
         if (var3 == null) {
            return 65.0F;
         } else {
            int var4 = (int)Math.floor(var1);
            int var5 = (int)Math.floor(var2);
            return var3.water(var4, var5) ? 63.875F : var3.heightAt(var4, var5) + 1;
         }
      }

      public float dark() {
         HomeUpdater2.TypeZ var1 = HomeUpdater.this.o.v;
         return var1 != null ? var1.d : 0.0F;
      }

      public boolean settled() {
         return HomeUpdater.this.X && HomeUpdater.this.B != null && HomeUpdater.this.S != null;
      }
   }

   private record TypeK(HomeSupport A, HomeCurve k, HomeCodec2 X, boolean f) {
      public HomeSupport forest() {
         return this.A;
      }

      public HomeCurve mesh() {
         return this.k;
      }

      public HomeCodec2 sprites() {
         return this.X;
      }

      public boolean regrow() {
         return this.f;
      }

      public HomeSupport treeModelBuilderState2() {
         return this.A;
      }

      public HomeCurve terrainMeshBuilderState4() {
         return this.k;
      }

      public HomeCodec2 blockTextureSamplerState3() {
         return this.X;
      }

      public boolean booleanState32() {
         return this.f;
      }
   }
}
