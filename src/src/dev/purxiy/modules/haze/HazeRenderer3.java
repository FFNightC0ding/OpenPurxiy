// Sourced By DevFFNightmare
package dev.purxiy.modules.haze;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.loader.fabric.client.ClientSupport;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.nio.ByteBuffer;
import java.util.OptionalInt;
import net.minecraft.class_10789;
import net.minecraft.class_290;
import net.minecraft.class_6367;

import org.lwjgl.system.MemoryUtil;

public final class HazeRenderer3 {
   public static final float h = 258.0F;
   private static final float T = 44.0F;
   private static final float e = 126.0F;
   private static final float M = 180.0F;
   private static final float l = 56.0F;
   private static final int P = -4222985;
   private static final String[] K = HazeCatalog.names();
   private static final int q = 80;
   private static final RenderPipeline f = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/haze_preview"))
      .withVertexShader(ClientServices.id("core/haze"))
      .withFragmentShader(ClientServices.id("core/haze_preview"))
      .withUniform("PurxiyHazePreview", class_10789.field_60031)
      .withoutBlend()
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   
   private static class_6367 b;
   
   private static GpuBuffer z;
   
   private static ByteBuffer G;
   
   private static String S;
   private final HazeModule H;
   private final Animator[] y = new Animator[K.length];
   private final Animator[] X = new Animator[K.length];
   private final float[] F = new float[13];
   private boolean Y;
   private float j;
   private float g;
   private float N;
   private float a;
   private float m;
   private float I;
   private int v = -1;
   private boolean s;

   private static final String[] d = new String[12];

   private static final Integer[] k = new Integer[17];
   private static final long n = 80L;

   public HazeRenderer3(HazeModule var1) {
      this.H = var1;

      for (int var2 = 0; var2 < K.length; var2++) {
         this.y[var2] = Animator.smooth(0.0F);
         this.X[var2] = Animator.snappy(var2 == var1.H.index() ? 1.0F : 0.0F);
      }
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 9734836810354L;
      long var8 = var6 ^ 16960938348539L;
      long var10 = var6 ^ 99286647668889L;
      long var12 = var6 ^ 31948780735141L;
      int var14 = (int)((var6 ^ 31948780735141L) >>> 32);
      int var15 = (int)((var6 ^ 31948780735141L) << 32 >>> 48);
      int var16 = (int)(var12 << 48 >>> 48);
      this.j = var2;
      this.g = var3;
      this.N = var4;
      this.s = var5 && !var1.masked();
      float var17 = Math.min(var1.dt, 0.1F);
      this.m += var17;
      this.I = this.I + var17 * this.H.V.floatValue();
      Canvas var18 = var1.canvas;
      var18.text(Fonts.MEDIUM, "Choose a colour", var2, var3 + 10.0F, 13.5F, Icons.TEXT);
      var18.text(Fonts.REGULAR, "A valley in the haze, in your strength, height and glow", var2, var3 + 30.0F, 11.5F, Icons.TEXT_2);
      int var19 = this.H.H.index();
      int var20 = this.v >= 0 ? this.v : var19;
      this.Z(HazeCatalog.byName(K[var20], this.H.d.argb()), var14, (char)var15, var17, (short)var16);
      float var21 = var3 + 44.0F;
      var18.roundRect(var2, var21, var4, 126.0F, 10.0F, BlendMode.lerp(Theme.tint(-15725031), Theme.tint(-4222985), 0.06F));
      var18.pushClip(var2 + 3.0F, var21 + 3.0F, var4 - 6.0F, 120.0F, 8.0F);
      this.K(var18, var2 + 3.0F, var21 + 3.0F, var10, var4 - 6.0F, 120.0F);
      var18.popClip();
      var18.roundRectOutline(var2, var21, var4, 126.0F, 10.0F, 1.0F, BlendMode.withAlpha(Theme.tint(-4222985), 0.14F));
      this.d(var1, var18, var3 + 180.0F, var19, var8);
      HazeCatalog var22 = HazeCatalog.byName(K[var20], this.H.d.argb());
      var18.circle(var2 + 3.0F, var3 + 258.0F - 12.0F, 2.5F, Theme.tint(-4222985));
      var18.textEllipsized(Fonts.REGULAR, var22.T, var2 + 14.0F, var3 + 258.0F - 12.0F, 10.5F, var4 - 14.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   private void d(DrawContext var1, Canvas var2, float var3, int var4, long var5) {
      long var7 = 12633425703608L;
      long var9 = 103590651047569L;
      this.a = this.N / K.length;
      HazeRenderer2.ensureSourceClassInitialized();
      float var11 = Math.min(13.5F, this.a * 0.3F);
      float var12 = var3 + 4.0F + var11;
      int var13 = -1;

      for (int var14 = 0; var14 < K.length; var14++) {
         float var15 = this.j + this.a * (var14 + 0.5F);
         long var16 = 8485107006856L;
         if (8485107006856L == 0L) {
            boolean var10000 = false;
         } else {
            int var34 = var16 < 0L ? -1 : 1;
         }

         long var19 = 8485107006856L;
         if (8485107006856L == 0L) {
            boolean var35 = false;
         } else {
            int var36 = var19 < 0L ? -1 : 1;
         }

         boolean var22 = this.s && var1.hover(this.y[var14], var15 - this.a / 2.0F, var3, this.a, 56.0F, DrawContext.TypeE.HAND);
         if (var22) {
            var13 = var14;
         }

         float var23 = this.y[var14].target(var22 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         float var24 = this.X[var14].target(var14 == var4 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
         float var25 = 1.6F * var23;
         float var26 = var11 * (1.0F + 0.07F * var23);
         HazeCatalog var27 = HazeCatalog.byName(K[var14], this.H.d.argb());
         long var28 = 8485107006856L;
         if (8485107006856L == 0L) {
            boolean var37 = false;
         } else {
            int var38 = var28 < 0L ? -1 : 1;
         }

         if (var14 < HazeCatalog.U.size()) {
            d(var2, var15, var12 - var25, var26, var27, var23, var24, var9);
            long var30 = 8485107006857L;
            if (8485107006857L == 0L) {
               boolean var39 = false;
            } else {
               int var40 = var30 < 0L ? -1 : 1;
            }
         } else {
            v(var2, var15, var12 - var25, var26, var27, var23, var24, var7);
         }

         int var33 = BlendMode.lerp(BlendMode.lerp(Icons.TEXT_3, Icons.TEXT_2, var23), Icons.TEXT, var24);
         var2.textEllipsized(
            var24 > 0.5F ? Fonts.SEMIBOLD : Fonts.MEDIUM,
            K[var14],
            var15,
            var12 + var11 + 15.0F,
            10.5F,
            this.a - 4.0F,
            var33,
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
      }

      long var32 = 8485107006857L;
      if (8485107006857L == 0L) {
         boolean var41 = false;
      } else {
         int var42 = var32 < 0L ? -1 : 1;
      }

      this.v = var13;
   }

   private static void d(Canvas var0, float var1, float var2, float var3, HazeCatalog var4, float var5, float var6, long var7) {
      long var9 = 26484098992069L;
      int var11 = 0xFF000000 | var4.R;
      HazeRenderer2.ensureSourceClassInitialized();
      int var12 = 0xFF000000 | var4.c;
      int var13 = 0xFF000000 | var4.r;
      float var14 = var2 + var3 * 0.45F;
      long var15 = 103590651047569L;
      if (103590651047569L == 0L) {
         boolean var10000 = false;
      } else {
         int var18 = var15 < 0L ? -1 : 1;
      }

      var0.softCircle(var1, var14, var3 * 0.75F, var3 * 0.9F, BlendMode.withAlpha(-16777216, 0.35F));
      if (var6 > 0.01F || var5 > 0.01F) {
         var0.glowCircle(var1, var2, var3 * 0.8F, var3 * 1.5F, BlendMode.withAlpha(var11, 0.42F * Math.max(var6, var5 * 0.5F)));
      }

      var0.circleRadial(var1, var2, var3, BlendMode.shade(var11, 0.14F), BlendMode.shade(var12, -0.2F));
      var0.softCircle(var1 + var3 * 0.28F, var2 + var3 * 0.3F, var3 * 0.3F, var3 * 0.45F, BlendMode.withAlpha(var13, 0.75F));
      var0.softCircle(var1 - var3 * 0.3F, var2 - var3 * 0.36F, var3 * 0.22F, var3 * 0.42F, BlendMode.withAlpha(-1, 0.42F));
      var0.ring(var1, var2, var3 - 0.5F, 1.0F, 620756991);
      b(var0, var1, var2, var3, var6, var9);
   }

   private static void v(Canvas var0, float var1, float var2, float var3, HazeCatalog var4, float var5, float var6, long var7) {
      long var9 = 26484098992069L;
      HazeRenderer2.ensureSourceClassInitialized();
      float var11 = var2 + var3 * 0.45F;
      float var12 = var3 * 0.75F;
      long var13 = 12633425703607L;
      if (12633425703607L == 0L) {
         boolean var10000 = false;
      } else {
         int var25 = var13 < 0L ? -1 : 1;
      }

      label50: {
         var0.softCircle(var1, var11, var12, var3 * 0.9F, BlendMode.withAlpha(-16777216, 0.35F));
         if (!(var6 > 0.01F)) {
            long var16 = 12633425703607L;
            if (12633425703607L == 0L) {
               boolean var26 = false;
            } else {
               int var27 = var16 < 0L ? -1 : 1;
            }

            if (!(var5 > 0.01F)) {
               break label50;
            }
         }

         var0.glowCircle(var1, var2, var3 * 0.8F, var3 * 1.5F, BlendMode.withAlpha(0xFF000000 | var4.R, 0.42F * Math.max(var6, var5 * 0.5F)));
      }

      var11 = Math.max(3.0F, var3 * 0.28F);
      var12 = var3 - var11 / 2.0F;

      for (int var22 = 0; var22 < 12; var22++) {
         float var14 = var22 / 12.0F;
         long var15 = 12633425703607L;
         if (12633425703607L == 0L) {
            boolean var28 = false;
         } else {
            int var29 = var15 < 0L ? -1 : 1;
         }

         int var18 = ClientSupport.hsvToRgb(var14, 0.6F, 1.0F);
         int var19 = ClientSupport.hsvToRgb((var22 + 1) / 12.0F, 0.6F, 1.0F);
         var0.arc(var1, var2, var12, var11, -90 + var22 * 30 - 0.6F, 31.2F, var18, var19, false);
      }

      var13 = 12633425703608L;
      if (12633425703608L == 0L) {
         boolean var30 = false;
      } else {
         int var31 = var13 < 0L ? -1 : 1;
      }

      float var24 = var3 - var11 - 1.5F;
      var0.circleRadial(var1, var2, var24, BlendMode.shade(0xFF000000 | var4.R, 0.14F), BlendMode.shade(0xFF000000 | var4.c, -0.2F));
      b(var0, var1, var2, var3, var6, var9);
   }

   private static void b(Canvas var0, float var1, float var2, float var3, float var4, long var5) {
      if (var4 > 0.01F) {
         var0.ring(var1, var2, var3 + 2.5F + 1.5F * var4, 1.5F, BlendMode.withAlpha(-1, 0.9F * var4));
      }
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      if (this.s && var3 == 0) {
         float var4 = this.g + 180.0F;
         if (!(var2 < var4) && !(var2 >= var4 + 56.0F) && !(var1 < this.j) && !(var1 >= this.j + this.N) && !(this.a <= 0.0F)) {
            int var5 = Math.clamp((long)((int)((var1 - this.j) / this.a)), 0, K.length - 1);
            if (this.H.H.index() != var5) {
               this.H.H.select(var5);
               UiAudioPolicy.play("SELECT");
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private void Z(HazeCatalog var1, int var2, char var3, float var4, short var5) {
      HazeRenderer2.ensureSourceClassInitialized();
      int[] var6 = new int[]{var1.R, var1.c, var1.r, HazeCatalog.mix(var1.E, var1.N, 0.6F)};
      float var7 = this.Y ? 1.0F - (float)Math.exp(-var4 * 9.0) : 1.0F;
      this.Y = true;

      for (int var8 = 0; var8 < 4; var8++) {
         for (int var9 = 0; var9 < 3; var9++) {
            this.F[var8 * 3 + var9] = this.F[var8 * 3 + var9] + (HazeCatalog.channel(var6[var8], var9) - this.F[var8 * 3 + var9]) * var7;
         }
      }

      this.F[12] = this.F[12] + (var1.L - this.F[12]) * var7;
   }

   private void K(Canvas var1, float var2, float var3, long var4, float var6, float var7) {
      byte var8 = 0;
      long var9 = 135182667016294L;
      HazeRenderer2.ensureSourceClassInitialized();

      try {
         GpuDevice var11 = RenderSystem.getDevice();
         if (!var11.precompilePipeline(f).isValid()) {
            return;
         }

         float var12 = (float)Math.hypot(var1.transform().m00(), var1.transform().m01());
         float var13 = Math.max(0.5F, var1.pixelScale() * var12);
         int var14 = Math.max(1, Math.round(var6 * var13));
         int var15 = Math.max(1, Math.round(120.0F * var13));
         long var16 = 90805945561835L;
         if (90805945561835L == 0L) {
            boolean var10000 = false;
         } else {
            int var31 = var16 < 0L ? -1 : 1;
         }

         if (b == null) {
            b = new class_6367("Purxiy haze preview", var14, var15, false);
         } else {
            label99: {
               if (b.field_1482 == var14) {
                  long var19 = 90805945561835L;
                  if (90805945561835L == 0L) {
                     boolean var32 = false;
                  } else {
                     int var33 = var19 < 0L ? -1 : 1;
                  }

                  if (b.field_1481 == var15) {
                     break label99;
                  }
               }

               b.method_1234(var14, var15);
            }
         }

         if (z == null) {
            z = var11.createBuffer(ClientServices.label(() -> "Purxiy haze preview"), 136, 80L);
            G = MemoryUtil.memAlloc(80);
         }

         CommandEncoder var30 = var11.createCommandEncoder();
         var30.writeToBuffer(z.slice(), this.Q((byte)var8, var14, var9, var15));
         long var20 = 90805945561835L;
         if (90805945561835L == 0L) {
            boolean var34 = false;
         } else {
            int var35 = var20 < 0L ? -1 : 1;
         }

         try (RenderPass var23 = var30.createRenderPass(() -> "Purxiy haze preview", b.method_71639(), OptionalInt.empty())) {
            var23.setPipeline(f);
            long var24 = 90805945561834L;
            if (90805945561834L == 0L) {
               boolean var36 = false;
            } else {
               int var37 = var24 < 0L ? -1 : 1;
            }

            RenderSystem.bindDefaultUniforms(var23);
            var23.setUniform("PurxiyHazePreview", z);
            int var26 = 1746974596;
            var23.draw(0, 3);
            int var18 = 1746989803;
            HazeRenderer2.metric("drawn");
         }

         var1.image(
            b.method_71639(), RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR), true, var2, var3, var6, 120.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, -1
         );
      } catch (RuntimeException var29) {
         if (!var29.toString().equals(S)) {
            ClientServices.LOGGER.error("Purxiy haze preview could not render", var29);
         }

         S = var29.toString();
      }
   }

   private ByteBuffer Q(byte var1, int var2, long var3, int var5) {
      HazeModule var6 = this.H;
      float[] var7 = this.F;
      float var8 = HazeRenderer2.c[0] * (float)Math.pow(var6.U.floatValue(), 1.2);
      float var9 = var6.k.floatValue() * HazeRenderer2.c[5];
      return Std140Builder.intoBuffer(G.clear())
         .putVec4(var2, var5, this.m % 3600.0F, this.I % 3600.0F)
         .putVec4(var7[0], var7[1], var7[2], var8)
         .putVec4(var7[3], var7[4], var7[5], var6.Q.floatValue())
         .putVec4(var7[6], var7[7], var7[8], var7[12])
         .putVec4(var7[9] * var9, var7[10] * var9, var7[11] * var9, var6.s.floatValue())
         .get();
   }

}
