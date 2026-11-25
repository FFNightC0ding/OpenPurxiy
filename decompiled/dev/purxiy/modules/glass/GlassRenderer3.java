package dev.purxiy.modules.glass;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.DrawContext.TypeE;
import java.nio.ByteBuffer;
import java.util.OptionalInt;
import java.util.Set;
import net.minecraft.class_10444;
import net.minecraft.class_1068;
import net.minecraft.class_12249;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1921;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4608;
import net.minecraft.class_5602;
import net.minecraft.class_591;
import net.minecraft.class_630;
import net.minecraft.class_6367;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

public final class GlassRenderer3 {
   private static final float n = 38.0F;
   private static final float g = 196.0F;
   public static final float x = 242.0F;
   private static final int y = -4222985;
   private static final int L = -1;
   private static final float X = (float)Math.toRadians(24.0);
   private static final GlassRenderer z = new GlassRenderer("preview");
   private static final GlassSupport J = new GlassSupport();
   private static final GlassSupport4 N = new GlassSupport4();
   private static final class_10444 W = new class_10444();
   private static final Matrix4f l = new Matrix4f();
   private static final Matrix4f m = new Matrix4f();
   private static class_6367 b;
   private static GpuBuffer Y;
   private static ByteBuffer r;
   private static class_591 Q;
   private static Set<class_630> q = Set.of();
   private static class_1799 d;
   private static String v;
   private final Animator O = Animator.smooth(0.0F);
   private float w;
   private float a;
   private float o;
   private boolean i;
   private double h;
   private float U = -0.45F;
   private boolean H;
   private float K;
   private float t;
   private static final String[] e = new String[10];
   private static final long k = 16L;

   public GlassRenderer3(GlassModule var1) {
      N.r = new float[24];
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 132387511753351L;
      long var8 = var6 ^ 109092354758636L;
      int var10 = (int)((var6 ^ 109092354758636L) >>> 48);
      int var11 = (int)((var6 ^ 109092354758636L) << 16 >>> 32);
      int var12 = (int)(var8 << 48 >>> 48);
      this.w = var2;
      this.a = var3;
      this.o = var4;
      this.i = var5 && !var1.masked();
      Canvas var13 = var1.canvas;
      var13.text(Fonts.MEDIUM, "Preview", var2, var3 + 13.0F, 13.5F, Icons.TEXT);
      var13.text(Fonts.REGULAR, "Drag to turn", var2 + var4, var3 + 15.0F, 10.5F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.TOP);
      float var14 = var3 + 38.0F;
      boolean var15 = this.i && var1.hover(this.O, var2, var14, var4, 196.0F, this.H ? TypeE.MOVE : TypeE.HAND);
      float var16 = this.O.target(!var15 && !this.H ? 0.0F : 1.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var17 = Math.min(var1.dt, 0.1F);
      this.h += var17;
      this.t = this.H ? 0.0F : this.t + var17;
      if (this.t > 1.2F) {
         this.U = this.U + var17 * 0.38F * Math.min(1.0F, (this.t - 1.2F) / 0.8F);
      }

      var13.roundRect(var2, var14, var4, 196.0F, 12.0F, Theme.tint(-15725031));
      float var18 = var2 + 3.0F;
      float var19 = var14 + 3.0F;
      float var20 = var4 - 6.0F;
      float var21 = 190.0F;
      var13.pushClip(var18, var19, var20, var21, 9.0F);
      this.O((short)var10, var13, var18, var19, var11, var20, (char)var12, var21);
      var13.popClip();
      var13.roundRectOutline(var2, var14, var4, 196.0F, 12.0F, 1.0F, BlendMode.withAlpha(Theme.tint(-4222985), 0.14F + var16 * 0.22F));
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      float var4 = this.a + 38.0F;
      if (this.i && var3 == 0 && !(var1 < this.w) && !(var1 >= this.w + this.o) && !(var2 < var4) && !(var2 >= var4 + 196.0F)) {
         this.H = true;
         this.K = var1;
         return true;
      } else {
         return false;
      }
   }

   public void mouseDrag(float var1, float var2) {
      if (this.H) {
         this.U = this.U + (var1 - this.K) * 0.013F;
         this.K = var1;
      }
   }

   public void mouseUp() {
      this.H = false;
   }

   private void O(short var1, Canvas var2, float var3, float var4, int var5, float var6, char var7, float var8) {
      long var9 = 30098418945387L;
      long var11 = var9 ^ 94758183708643L;
      long var13 = var9 ^ 89713230140055L;
      int var15 = (int)((var9 ^ 89713230140055L) >>> 32);
      int var16 = (int)((var9 ^ 89713230140055L) << 32 >>> 48);
      int var17 = (int)(var13 << 48 >>> 48);
      GlassRenderer4.ensureSourceClassInitialized();

      try {
         GpuDevice var18 = RenderSystem.getDevice();
         int var19 = GlassRenderer.ready() ? 1 : 0;
         if (var19 != 0 || !var18.precompilePipeline(GlassSupport3.I).isValid() || !this.U(var11)) {
            var2.roundRectGradient(var3, var4, var6, 190.0F, 9.0F, 90.0F, Theme.tint(-14870991), Theme.tint(-16053995));
            return;
         }

         float var20 = (float)Math.hypot(var2.transform().m00(), var2.transform().m01());
         float var21 = Math.max(0.5F, var2.pixelScale() * var20);
         int var22 = Math.max(1, Math.round(var6 * var21));
         int var23 = Math.max(1, Math.round(190.0F * var21));
         int var24 = 52587;
         if (b == null) {
            b = new class_6367("Purxiy glass preview", var22, var23, false);
         } else {
            label80: {
               if (b.field_1482 == var22) {
                  var24 = 459265425;
                  if (b.field_1481 == var23) {
                     break label80;
                  }
               }

               b.method_1234(var22, var23);
            }
         }

         if (Y == null) {
            Y = var18.createBuffer(ClientServices.label(() -> "Purxiy glass preview stage"), 136, 16L);
            r = MemoryUtil.memAlloc(16);
         }

         CommandEncoder var25 = var18.createCommandEncoder();
         int var33 = 52587;
         var25.writeToBuffer(Y.slice(), Std140Builder.intoBuffer(r.clear()).putVec4(var22, var23, (float)(this.h % 3600.0), 0.0F).get());
         GpuBuffer var26 = Theme.uniforms();
         RenderPass var27 = var25.createRenderPass(() -> "Purxiy glass preview stage", b.method_71639(), OptionalInt.of(-16777216));

         try {
            var27.setPipeline(GlassSupport3.I);
            var27.setUniform("GlassStage", Y);
            var27.setUniform("PurxiyTheme", var26);
            var33 = 711870517;
            var27.draw(0, 3);
            var24 = (int)var9;
            GlassRegistry.metric("drawn");
         } catch (Throwable var31) {
            if (var27 != null) {
               try {
                  var27.close();
               } catch (Throwable var30) {
                  var31.addSuppressed(var30);
               }
            }

            throw var31;
         }

         if (var27 != null) {
            var27.close();
         }

         this.A(var15, (short)var16, var6 / 190.0F, (char)var17);
         z.draw(J, b.method_30277(), b.method_71639(), var22, var23, N, null, null);
         var2.image(
            b.method_71639(), RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR), true, var3, var4, var6, 190.0F, 0.0F, 1.0F, 1.0F, 0.0F, 9.0F, -1
         );
         v = null;
      } catch (RuntimeException var321) {
         if (!var321.toString().equals(v)) {
            ClientServices.LOGGER.error("Purxiy glass preview could not render", var321);
         }

         v = var321.toString();
      }
   }

   private boolean U(long var1) {
      GlassRenderer4.ensureSourceClassInitialized();
      class_310 var3 = class_310.method_1551();
      long var4 = 85149042666119L;
      int var7 = var4 < 0L ? -1 : 1;
      if (Q == null) {
         Q = new class_591(var3.method_31974().method_32072(class_5602.field_27577), false);
         q = Set.of(Q.field_3394, Q.field_3483, Q.field_3484, Q.field_3486, Q.field_3482, Q.field_3479);
         d = new class_1799(class_1802.field_8802);
      }

      float var7x = (float)this.h;
      J.reset();
      class_591 var8 = Q;
      var8.method_63514();
      var8.field_3398.field_3675 = 0.22F * (float)Math.sin(var7x * 0.43F);
      var8.field_3398.field_3654 = 0.06F + 0.05F * (float)Math.sin(var7x * 0.61F);
      float var9 = 0.05F * (float)Math.sin(var7x * 1.3F);
      var8.field_3401.field_3674 = 0.07F + var9;
      var8.field_27433.field_3674 = -0.07F - var9;
      var8.field_3401.field_3654 = 0.04F * (float)Math.sin(var7x * 0.9F);
      var8.field_27433.field_3654 = -0.04F * (float)Math.sin(var7x * 0.9F);
      class_4587 var10 = new class_4587();
      var10.method_22907(class_7833.field_40716.rotation(this.U + (float) Math.PI));
      var10.method_22905(-1.0F, -1.0F, 1.0F);
      var10.method_46416(0.0F, -1.501F, 0.0F);
      class_1921 var11 = class_12249.method_76000(class_1068.method_4649());
      J.part(var8.method_63512(), var10, var11, null, -1, 1, 0, q);
      var3.method_65386().method_65598(W, d, class_811.field_4318, var3.field_1687, null, 0);
      var10 = new class_4587();
      var10.method_46416(1.32F, 0.86F + 0.07F * (float)Math.sin(var7x * 1.4F), 0.1F);
      var10.method_22907(class_7833.field_40716.rotation(var7x * 0.8F));
      var10.method_22907(class_7833.field_40718.rotationDegrees(-35.0F));
      var10.method_22905(2.1F, 2.1F, 2.1F);
      W.method_65604(var10, Glassclass_11659.into(J, 2), -1, class_4608.field_21444, 0);
      return !J.isEmpty();
   }

   private void A(int var1, short var2, float var3, char var4) {
      long var5 = 82223535955964L;
      long var7 = var5 ^ 138116578052995L;
      m.setPerspective(X, var3, 0.1F, 40.0F);
      l.setLookAt(0.1F, 1.25F, 5.6F, 0.52F, 0.95F, 0.0F, 0.0F, 1.0F, 0.0F);
      GlassSupport4 var9 = N;
      var9.y = true;
      var9.E.set(l);
      var9.k.set(m);
      var9.k.invert(var9.X);
      l.transformDirection(var9.n.set(0.0F, 1.0F, 0.0F)).normalize();
      var9.q.set(1.0F, 0.0F, 0.0F);
      var9.v.set(-2.6F, 3.1F, 0.8F);
      var9.N.set(3.2F, 0.7F, 0.2F);
      l.transformDirection(var9.B.set(0.9F, 0.85F, 0.75F)).normalize();
      var9.U = 0.55F;
      var9.w[0] = 0.93F;
      var9.w[1] = 0.86F;
      var9.w[2] = 1.0F;
      var9.I[0] = 0.09F;
      var9.I[1] = 0.07F;
      var9.I[2] = 0.17F;
      var9.e[0] = 0.42F;
      var9.e[1] = 0.31F;
      var9.e[2] = 0.66F;
      var9.Q = 0.0F;
      var9.o = 0.55F;
      var9.V = 0.36F;
      var9.b = (float)(this.h % 3600.0);
      GlassRegistry.look(var9);
      float[] var10 = var9.r;
      i(var10, 1, 0.0F, var7, 0.95F, 0.0F, 1.0F, 0.13F);
      i(var10, 2, 1.32F, var7, 0.86F, 0.1F, 0.45F, 0.61F);
      var9.T = 3;
   }

   private static void i(float[] var0, int var1, float var2, long var3, float var5, float var6, float var7, float var8) {
      GlassRenderer4.ensureSourceClassInitialized();
      Vector3f var9 = l.transformPosition(new Vector3f(var2, var5, var6));
      int var10 = var1 * 8;
      var0[var10] = var9.x;
      long var11 = 60842191459454L;
      int var13 = var11 < 0L ? -1 : 1;
      var0[var10 + 1] = var9.y;
      var0[var10 + 2] = var9.z;
      var0[var10 + 3] = var7;
      var0[var10 + 4] = 0.0F;
      var0[var10 + 5] = var8;
      var0[var10 + 6] = var1 == 2 ? 1.0F : 0.0F;
      var0[var10 + 7] = 0.0F;
   }
}
