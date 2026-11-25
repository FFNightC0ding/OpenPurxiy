package dev.purxiy.modules.mount;

import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.TabListProvider;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.font.TrueTypeFont;
import dev.purxiy.render.texture.TextureLoader;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
import net.minecraft.class_12137;
import net.minecraft.class_156;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_638;
import net.minecraft.class_1011.class_1012;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryUtil;

public final class MountBootRenderer {
   public static final float q = 400.0F;
   private static final float T = 3.1F;
   private static final float H = 96.0F;
   private static final float i = 14.0F;
   private static final float d = 12.0F;
   private static final long D = 350000000L;
   private static final DateTimeFormatter c = DateTimeFormatter.ofPattern("HH:mm");
   private static final MountMapTiles p = new MountMapTiles();
   private static MountBootRenderer.BootScreen v = MountBootRenderer.BootScreen.Z;
   private static MountBootRenderer.BootScreen u = MountBootRenderer.BootScreen.Z;
   private static float k = 1.0F;
   private static long a = 0L;
   private static long w = 0L;
   private static TextureLoader V;
   private static TextureLoader j;
   private static GpuTexture r;
   private static GpuTexture C;
   private static GpuTextureView E;
   private static GpuTextureView s;
   private static ByteBuffer b;
   private static ByteBuffer L;
   private static long P = 0L;
   private static double G;
   private static double N;
   private static int z = 2;
   private static BufferedImage m;
   private static long g = Long.MIN_VALUE;
   private static byte[] x;
   private static CompletableFuture<BufferedImage> W;
   private static CompletableFuture<MountMapTiles.Data> I;
   private static boolean J;
   private static boolean U;
   private static final Matrix4f n = new Matrix4f();
   private static final Matrix4f S = new Matrix4f();
   private static float B = -1.0F;
   private static float h = -1.0F;
   private static boolean F;
   private static final String[] f = new String[37];
   private static final long[] t = new long[]{
      -2679163633066773073L, 5649874020854558185L, 2411540677621602306L, 7712995870496272454L, 6263039610312129367L, 8120076840112254852L, 3817150096041236896L
   };
   private static final Long[] y = new Long[7];

   private MountBootRenderer() {
   }

   public static void boot() {
      F = true;
      v = u = MountBootRenderer.BootScreen.Z;
      k = 1.0F;
   }

   private static float S(int var0, short var1, int var2) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      return a == 0L ? 99.0F : (float)(System.nanoTime() - a) / 1.0E9F;
   }

   public static void hover(float[] var0) {
      B = var0 == null ? -1.0F : var0[0];
      h = var0 == null ? -1.0F : var0[1];
   }

   public static void render(Matrix4f var0, Matrix4f var1, Matrix4f var2) {
      long var3 = 56959504246228L;
      long var5 = var3 ^ 14642662161836L;
      long var7 = var3 ^ 18706405647103L;
      long var9 = var3 ^ 78483118048534L;
      long var11 = var3 ^ 63908245509816L;
      long var13 = var3 ^ 102070320777573L;
      int var15 = (int)((var3 ^ 102070320777573L) >>> 32);
      int var16 = (int)((var3 ^ 102070320777573L) << 32 >>> 48);
      int var17 = (int)(var13 << 48 >>> 48);
      var13 = var3 ^ 13410254126910L;
      int var18 = (int)((var3 ^ 13410254126910L) >>> 32);
      int var19 = (int)((var3 ^ 13410254126910L) << 32 >>> 48);
      int var20 = (int)(var13 << 48 >>> 48);
      int var21 = (int)((var3 ^ 50057826401186L) >>> 48);
      long var22 = (var3 ^ 50057826401186L) << 16 >>> 16;
      long var24 = var3 ^ 130142809396834L;
      int var26 = (int)((var3 ^ 31105301492503L) >>> 32);
      int var27 = (int)((var3 ^ 31105301492503L) << 32 >>> 32);
      long var28 = var3 ^ 108246981210937L;
      class_310 var30 = class_310.method_1551();
      MountFactory.MountSpec var31 = MountInteraction.spec();
      MountFactory.Data var32 = var31.screen();
      if (var32 != null && MountCameraState.cockpitActive() && !(MountInteraction.boarding(MountCameraState.partialTick()) < 0.6F)) {
         float var33 = var32.width() / var32.height();
         float var34 = 400.0F * var33;
         Vector3f var35 = var32.right();
         Vector3f var36 = var32.down();
         Vector3f var37 = var32.normal();
         Vector3f var38 = var32.topLeft();
         float var39 = var32.width() / var34;
         float var40 = var32.height() / 400.0F;
         S.set(var2)
            .mul(
               new Matrix4f(
                  var35.x * var39,
                  var35.y * var39,
                  var35.z * var39,
                  0.0F,
                  var36.x * var40,
                  var36.y * var40,
                  var36.z * var40,
                  0.0F,
                  var37.x,
                  var37.y,
                  var37.z,
                  0.0F,
                  var38.x + var37.x * 0.002F,
                  var38.y + var37.y * 0.002F,
                  var38.z + var37.z * 0.002F,
                  1.0F
               )
            );
         n.set(var1).mul(var0).mul(S);
         float var41 = D(n, var34, var28, var30.method_1522().field_1482, var30.method_1522().field_1481);
         if (!(var41 <= 0.0F)) {
            long var42 = System.nanoTime();
            if (F) {
               F = false;
               a = var42;
            }

            float var44 = w == 0L ? 0.0F : Math.min(0.1F, (float)(var42 - w) / 1.0E9F);
            w = var42;
            k = Math.min(1.0F, k + var44 / 0.28F);
            MountTransport var45 = MountTransport.get();
            var45.keepAlive(false);
            MountData var46 = a(var11);
            C(var46, var45.artwork(), var5);
            if (v == MountBootRenderer.BootScreen.Z || v == MountBootRenderer.BootScreen.W) {
               W(var24, var30, var42);
            }

            m(var15, var16, var17);
            Canvas var47 = RenderPipelines.get().beginSheet(n, var34, 400.0F, var41, var1, 0.0F);
            if (var31.kind() != MountFactory.Vehicle.CAR) {
               float var48 = var31.kind() == MountFactory.Vehicle.MOTORBIKE ? 0.05F : 0.13F;
               B(var9, var47, var34, var32, var48);
            }

            var47.pushClip(0.0F, 0.0F, var34, 400.0F, 24.0F);
            float var53 = S(var18, (short)var19, var20);
            q(var47, var34, var31, var53, var26, var27);
            if (var53 < 3.1F) {
               L(var47, var34, var31, var53, var7);
               if (var53 > 2.5F) {
                  var47.pushAlpha((var53 - 2.5F) / 0.6F);
                  m((char)var21, var47, var34, var31, var46, var22, var45, var42, var53);
                  var47.popAlpha();
               }
            } else {
               m((char)var21, var47, var34, var31, var46, var22, var45, var42, var53);
            }

            var47.popClip();
            var47.roundRectOutlineGradient(0.5F, 0.5F, var34 - 1.0F, 399.0F, 24.0F, 1.4F, 90.0F, 1090519039, 285212671);
            RenderPipelines.get().flushSheet();
         }
      }
   }

   private static void B(long var0, Canvas var2, float var3, MountFactory.Data var4, float var5) {
      float var6 = 400.0F / var4.height();
      float var7 = var3 / 2.0F;
      float var8 = 0.013F * var6;
      float var9 = var5 * var6;
      var2.roundRectGradient(var7 - var8 / 2.0F, 400.0F - 0.03F * var6, var8, var9 + 0.03F * var6, var8 / 2.0F, 0.0F, -13882317, -16053490);
      var2.roundRectGradient(var7 - 0.05F * var6, 400.0F - 0.012F * var6, 0.1F * var6, 0.02F * var6, 0.009F * var6, 90.0F, -14935007, -16053490);
   }

   private static float D(Matrix4f var0, float var1, long var2, int var4, int var5) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      Vector4f var6 = var0.transform(new Vector4f(var1 / 2.0F, 160.0F, 0.0F, 1.0F));
      Vector4f var7 = var0.transform(new Vector4f(var1 / 2.0F, 240.0F, 0.0F, 1.0F));
      if (!(var6.w <= 0.01F)) {
         long var8 = 89880207595244L;
         int var11 = var8 < 0L ? -1 : 1;
         if (!(var7.w <= 0.01F)) {
            float var11x = (var6.x / var6.w - var7.x / var7.w) * var4 * 0.5F;
            float var12 = (var6.y / var6.w - var7.y / var7.w) * var5 * 0.5F;
            return Math.max(0.25F, (float)Math.hypot(var11x, var12) / 80.0F);
         }
      }

      return 0.0F;
   }

   private static void q(Canvas var0, float var1, MountFactory.MountSpec var2, float var3, int var4, int var5) {
      var0.roundRectGradient(0.0F, 0.0F, var1, 400.0F, 0.0F, 135.0F, Theme.tint(-15265998), Theme.tint(-16382451));
      float var6 = (float)Math.sin(var3 * 0.22F);
      var0.glowCircle(var1 * 0.72F + var6 * 40.0F, 376.0F, var1 * 0.22F, 190.0F, BlendMode.withAlpha(Theme.tint(-8630785), 0.42F));
      var0.glowCircle(var1 * 0.18F - var6 * 30.0F, 32.0F, var1 * 0.18F, 160.0F, BlendMode.withAlpha(Theme.tint(-3069451), 0.26F));
      var0.glowCircle(var1 * 0.96F, 48.0F, var1 * 0.12F, 120.0F, BlendMode.withAlpha(-13912321, 0.16F));
   }

   private static float J(float var0) {
      var0 = Math.clamp(var0, 0.0F, 1.0F);
      return 1.0F - (1.0F - var0) * (1.0F - var0) * (1.0F - var0);
   }

   private static void t(long var0) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      long var2 = 10322261987764L;
      int var4 = var2 < 0L ? -1 : 1;
      if (V == null) {
         try {
            V = TextureLoader.load("textures/brand/mark.png");
            j = TextureLoader.load("textures/brand/wordmark.png");
         } catch (RuntimeException var5) {
         }
      }
   }

   private static void L(Canvas var0, float var1, MountFactory.MountSpec var2, float var3, long var4) {
      long var6 = 10322261987765L;
      t(var6);
      float var8 = J(var3 / 0.45F);
      MountFactory.MountSpec.ensureSourceClassInitialized();
      float var9 = 1.0F - Math.clamp((var3 - 2.55F) / 0.55F, 0.0F, 1.0F);
      var0.pushAlpha(var8 * var9);
      float var10 = var1 / 2.0F;
      float var11 = 170.0F;
      float var12 = J((var3 - 0.15F) / 0.8F);
      float var13 = 118.0F * (0.84F + 0.16F * var12);
      float var14 = 0.5F + 0.5F * (float)Math.sin(var3 * 3.4F);
      long var15 = 38270434903850L;
      int var18 = var15 < 0L ? -1 : 1;
      var0.glowCircle(var10, var11, 56.0F + var14 * 6.0F, 90.0F, BlendMode.withAlpha(Theme.tint(Icons.MAGENTA), 0.34F * var12));
      if (V != null) {
         var0.pushAlpha(var12);
         var0.image(V, var10 - var13 / 2.0F, var11 - var13 / 2.0F, var13, var13);
         var0.popAlpha();
      }

      float var18x = J((var3 - 0.75F) / 0.6F);
      if (j != null) {
         long var19 = 38270434903851L;
         int var26 = var19 < 0L ? -1 : 1;
         if (var18x > 0.0F) {
            float var21 = 30.0F;
            float var22 = var21 * j.aspect();
            var0.pushAlpha(var18x);
            var0.image(j, var10 - var22 / 2.0F, var11 + 74.0F + (1.0F - var18x) * 10.0F, var22, var21);
            var0.popAlpha();
         }
      }

      float var25 = Math.clamp((var3 - 1.0F) / 1.5F, 0.0F, 1.0F);
      float var20 = Math.min(280.0F, var1 * 0.36F);
      float var26 = 346.0F;
      var0.roundRect(var10 - var20 / 2.0F, var26, var20, 5.0F, 2.5F, 620756991);
      long var27 = 38270434903850L;
      int var24 = var27 < 0L ? -1 : 1;
      if (var25 > 0.0F) {
         var0.roundRectGradient(
            var10 - var20 / 2.0F, var26, Math.max(5.0F, var20 * J(var25)), 5.0F, 2.5F, 0.0F, Theme.tint(Icons.VIOLET), Theme.tint(Icons.PINK)
         );
      }

      float var24x = J((var3 - 1.25F) / 0.6F);
      var0.pushAlpha(var24x);
      var0.text(Fonts.MEDIUM, var2.name(), var10, var26 - 26.0F, 26.0F, BlendMode.withAlpha(Icons.TEXT, 0.92F), FontStyle.CENTER, FontWeight.MIDDLE);
      var0.popAlpha();
      var0.popAlpha();
   }

   private static void m(char var0, Canvas var1, float var2, MountFactory.MountSpec var3, MountData var4, long var5, MountTransport var7, long var8, float var10) {
      long var11 = 33307839536758L;
      long var13 = var11 ^ 119558640322664L;
      long var15 = var11 ^ 119836525899401L;
      int var17 = (int)((var11 ^ 119836525899401L) >>> 32);
      int var18 = (int)((var11 ^ 119836525899401L) << 32 >>> 48);
      int var19 = (int)(var15 << 48 >>> 48);
      long var20 = var11 ^ 70001011294915L;
      long var22 = var11 ^ 43979383448789L;
      long var24 = var11 ^ 8533698423578L;
      long var26 = var11 ^ 51905859071953L;
      long var28 = var11 ^ 90338084045236L;
      long var30 = var11 ^ 21983200410151L;
      MountFactory.MountSpec.ensureSourceClassInitialized();
      boolean var32 = var2 < 640.0F;
      float var33 = var32 ? 0.0F : 96.0F;
      float var34 = var33 + (var32 ? 14.0F : 0.0F);
      float var35 = 14.0F;
      float var36 = var2 - var34 - 14.0F;
      float var37 = 372.0F;
      float var38 = J((var10 - 2.75F) / 0.55F);
      MountBootRenderer.Data var39 = MountBootRenderer.Data.of(var4, var7, var8, m);
      if (!var32) {
         W(var1, 11.2F, 14.0F, 80.6F, var37, 26.0F, var22);
      }

      if (var32) {
         W(var1, var34, var35, var36, var37, 22.0F, var22);
         K(var1, var34, var35, var36, var37, var20, var3, var39);
      } else {
         MountBootRenderer.BootScreen var40 = v;
         MountBootRenderer.BootRect[] var41 = k(var28, var40, var34, var35, var36, var37);

         for (MountBootRenderer.BootRect var45 : var41) {
            long var46 = 33307839536758L;
            int var48 = var46 < 0L ? -1 : 1;
            W(var1, var45.q, var45.H + (1.0F - var38) * 18.0F, var45.i, var45.f, 22.0F, var22);
         }

         x(var1, var3, var13);
         var1.pushAlpha(k);
         float var52 = (1.0F - J(k)) * 10.0F;
         var1.push();
         long var53 = 33307839536758L;
         int var52x = var53 < 0L ? -1 : 1;
         var1.translate(0.0F, var52 + (1.0F - var38) * 18.0F);
         switch (var40) {
            case W:
               long var54 = 33307839536757L;
               int var54x = var54 < 0L ? -1 : 1;
               T(var1, var17, var18, var41[0], true, var19);
               break;
            case V:
               long var49 = 33307839536757L;
               int var49x = var49 < 0L ? -1 : 1;
               L(var1, var41[0], var30, var39, true);
               break;
            case q:
               V(var1, var41[0], var26, var41[1], var3);
               break;
            default:
               T(var1, var17, var18, var41[0], false, var19);
               L(var1, var41[1], var30, var39, false);
               w(var1, var41[2], var3, var24);
         }

         var1.pop();
         var1.popAlpha();
      }
   }

   private static MountBootRenderer.BootRect[] k(long var0, MountBootRenderer.BootScreen var2, float var3, float var4, float var5, float var6) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      switch (var2) {
         case W:
         case V:
            return new MountBootRenderer.BootRect[]{new MountBootRenderer.BootRect(var3, 14.0F, var5, 372.0F)};
         case q:
            float var7 = var5 * 0.62F;
            return new MountBootRenderer.BootRect[]{
               new MountBootRenderer.BootRect(var3, 14.0F, var7, 372.0F), new MountBootRenderer.BootRect(var3 + var7 + 12.0F, 14.0F, var5 - var7 - 12.0F, 372.0F)
            };
         default:
            float var8 = var5 * 0.54F;
            float var9 = var5 - var8 - 12.0F;
            float var10 = 230.64F;
            return new MountBootRenderer.BootRect[]{
               new MountBootRenderer.BootRect(var3, 14.0F, var8, 372.0F),
               new MountBootRenderer.BootRect(var3 + var8 + 12.0F, 14.0F, var9, var10),
               new MountBootRenderer.BootRect(var3 + var8 + 12.0F, 14.0F + var10 + 12.0F, var9, 372.0F - var10 - 12.0F)
            };
      }
   }

   private static void W(Canvas var0, float var1, float var2, float var3, float var4, float var5, long var6) {
      var0.glass(var1, var2, var3, var4, var5, BlendMode.withAlpha(Theme.tint(-15857124), 0.46F), 1.0F, 1.3F, 0.012F);
      var0.roundRectCorners(var1, var2, var3, var4, var5, 352321535, 150994943, 0, 0);
      var0.roundRectOutlineGradient(var1, var2, var3, var4, var5, 1.2F, 90.0F, 956301311, 234881023);
   }

   private static void x(Canvas var0, MountFactory.MountSpec var1, long var2) {
      long var4 = 73942656038555L;
      long var6 = 127118875133596L;
      MountFactory.MountSpec.ensureSourceClassInitialized();
      float var8 = 51.5F;
      var0.text(Fonts.SEMIBOLD, LocalTime.now().format(c), var8, 48.0F, 25.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
      String[] var9 = new String[]{"house", "map", "music", "gauge"};
      MountBootRenderer.BootScreen[] var10 = MountBootRenderer.BootScreen.values();

      for (int var11 = 0; var11 < 4; var11++) {
         float var12 = i(var6, var11);
         boolean var13 = v == var10[var11];
         boolean var14 = L(var11, var4);
         if (var13) {
            long var15 = 126409472792094L;
            int var17 = var15 < 0L ? -1 : 1;
            var0.glowCircle(var8, var12, 22.0F, 26.0F, BlendMode.withAlpha(Theme.tint(Icons.MAGENTA), 0.28F));
            var0.roundRectGradient(var8 - 28.0F, var12 - 28.0F, 56.0F, 56.0F, 18.0F, 135.0F, Theme.tint(Icons.INDIGO), Theme.tint(Icons.MAGENTA));
         } else if (var14) {
            var0.roundRect(var8 - 28.0F, var12 - 28.0F, 56.0F, 56.0F, 18.0F, 587202559);
         }

         String var19 = var9[var11];
         long var16 = 126409472792093L;
         int var18 = var16 < 0L ? -1 : 1;
         var0.icon(var19, var8, var12, 30.0F, var13 ? -1 : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var14 ? 1.0F : 0.0F));
      }
   }

   private static float i(long var0, int var2) {
      return 104 + var2 * 72;
   }

   private static boolean L(int var0, long var1) {
      long var3 = 305547507849L;
      long var5 = 127118875133596L;
      float var7 = h * 400.0F;
      MountFactory.MountSpec.ensureSourceClassInitialized();
      long var8 = 73942656038555L;
      int var11 = var8 < 0L ? -1 : 1;
      if (B >= 0.0F) {
         float var11x = B * y(var3);
         long var12 = 73942656038554L;
         int var13 = var12 < 0L ? -1 : 1;
         if (var11x < 96.0F && Math.abs(var7 - i(var5, var0)) < 34.0F) {
            return true;
         }
      }

      return false;
   }

   private static float y(long var0) {
      MountFactory.Data var2 = MountInteraction.spec().screen();
      return var2 == null ? 960.0F : 400.0F * var2.width() / var2.height();
   }

   private static void T(Canvas var0, int var1, int var2, MountBootRenderer.BootRect var3, boolean var4, int var5) {
      long var6 = 126128299651327L;
      long var8 = var6 ^ 80773853028337L;
      float var10 = 6.0F;
      MountFactory.MountSpec.ensureSourceClassInitialized();
      float var11 = var3.q + var10;
      float var12 = var3.H + var10;
      float var13 = var3.i - var10 * 2.0F;
      float var14 = var3.f - var10 * 2.0F;
      if (E != null) {
         float var15 = Math.max(var13 / 160.0F, var14 / 112.0F);
         float var16 = var13 / var15 / 160.0F;
         float var17 = var14 / var15 / 112.0F;
         float var18 = 0.5F + (float)G / 160.0F;
         float var19 = 0.5F + (float)N / 112.0F;
         class_12137 var20 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
         var0.image(
            E, var20, false, var11, var12, var13, var14, var18 - var16 / 2.0F, var19 - var17 / 2.0F, var18 + var16 / 2.0F, var19 + var17 / 2.0F, 17.0F, -1
         );
      } else {
         var0.roundRect(var11, var12, var13, var14, 17.0F, 872415231);
      }

      var0.roundRectCorners(var11, var12, var13, var14, 17.0F, 335939092, 335939092, 1879442964, 1879442964);
      float var27 = var11 + var13 / 2.0F;
      float var28 = var12 + var14 / 2.0F;
      float var29 = MountInteraction.yaw(MountCameraState.partialTick());
      var0.glowCircle(var27, var28, 14.0F, 30.0F, 1428917503);
      var0.circle(var27, var28, 15.0F, -1);
      var0.circle(var27, var28, 12.5F, -16087809);
      D(var0, var27, var28, 8.5F, var29 + 180.0F);
      class_310 var30 = class_310.method_1551();
      class_243 var31 = MountInteraction.renderPosition(1.0F);
      float var32 = var11 + 12.0F;
      float var21 = var12 + var14 - 52.0F;
      m(var0, var32, var8, var21, 186.0F, 40.0F, 14.0F);
      var0.text(
         Fonts.SEMIBOLD,
         Math.round(var31.field_1352) + "  ·  " + Math.round(var31.field_1350),
         var11 + 12.0F + 93.0F,
         var12 + var14 - 32.0F,
         25.0F,
         Icons.TEXT,
         FontStyle.CENTER,
         FontWeight.MIDDLE
      );
      m(var0, var11 + var13 - 54.0F, var8, var12 + 12.0F, 42.0F, 42.0F, 21.0F);
      var0.text(
         Fonts.SEMIBOLD, Translations.russian() ? "С" : "N", var11 + var13 - 33.0F, var12 + 33.0F, 25.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE
      );
      if (var4) {
         m(var0, var11 + var13 - 62.0F, var8, var12 + var14 / 2.0F - 62.0F, 50.0F, 124.0F, 18.0F);
         var0.icon("plus", var11 + var13 - 37.0F, var12 + var14 / 2.0F - 30.0F, 26.0F, Icons.TEXT);
         var0.roundRect(var11 + var13 - 54.0F, var12 + var14 / 2.0F - 1.0F, 34.0F, 1.5F, 0.7F, 1090519039);
         var0.icon("minus", var11 + var13 - 37.0F, var12 + var14 / 2.0F + 30.0F, 26.0F, Icons.TEXT);
         String var22 = var30.field_1687 == null ? "" : var30.field_1687.method_27983().method_29177().method_12832();

         String var25 = Translations.tr(switch (var22) {
            case "overworld" -> "Overworld";
            case "the_nether" -> "Nether";
            case "the_end" -> "The End";
            default -> var22.replace('_', ' ');
         });
         float var26 = var0.textWidth(Fonts.SEMIBOLD, var25, 26.0F) + 34.0F;
         m(var0, var11 + 12.0F, var8, var12 + 12.0F, var26, 42.0F, 15.0F);
         var0.text(Fonts.SEMIBOLD, var25, var11 + 12.0F + var26 / 2.0F, var12 + 33.0F, 26.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
      }
   }

   private static void D(Canvas var0, float var1, float var2, float var3, float var4) {
      double var5 = Math.toRadians(var4);
      float var7 = (float)Math.sin(var5);
      float var8 = (float)Math.cos(var5);
      float var9 = var1 + var7 * 8.5F;
      float var10 = var2 - var8 * 8.5F;
      float var11 = var1 + (-var7 * 8.5F * 0.75F - var8 * 8.5F * 0.69F);
      float var12 = var2 + (var8 * 8.5F * 0.75F - var7 * 8.5F * 0.69F);
      float var13 = var1 + (-var7 * 8.5F * 0.75F + var8 * 8.5F * 0.69F);
      float var14 = var2 + (var8 * 8.5F * 0.75F + var7 * 8.5F * 0.69F);
      float var15 = var1 - var7 * 8.5F * 0.3F;
      float var16 = var2 + var8 * 8.5F * 0.3F;
      var0.triangle(var9, var10, var13, var14, var15, var16, -1, -1, -1);
      var0.triangle(var9, var10, var15, var16, var11, var12, -1, -1, -1);
   }

   private static void m(Canvas var0, float var1, long var2, float var4, float var5, float var6, float var7) {
      var0.roundRect(var1, var4, var5, var6, var7, -1207236074);
      var0.roundRectOutline(var1, var4, var5, var6, var7, 1.0F, 587202559);
   }

   private static void L(Canvas var0, MountBootRenderer.BootRect var1, long var2, MountBootRenderer.Data var4, boolean var5) {
      long var6 = 6422594080822L;
      long var8 = 133307609833470L;
      long var10 = 99150813940610L;
      long var12 = 116566880994810L;
      MountFactory.MountSpec.ensureSourceClassInitialized();
      float var14 = var1.q + 18.0F;
      float var15 = var1.H + 16.0F;
      float var16 = var1.i - 36.0F;
      float var17 = var1.f - 32.0F;
      if (!var4.present()) {
         float var18 = var1.q + var1.i / 2.0F;
         r(var0, var18, var1.H + var1.f * 0.3F, 26.0F, var10);
         var0.text(Fonts.SEMIBOLD, "Spotify", var18, var1.H + var1.f * 0.3F + 48.0F, 30.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
         var0.textEllipsized(
            Fonts.MEDIUM, Translations.tr(var4.idle()), var18, var1.H + var1.f * 0.3F + 84.0F, 25.0F, var16, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE
         );
      } else {
         float var39 = var5 ? Math.min(var17, 220.0F) : 78.0F;
         O(var0, var14, var12, var15, var39);
         float var19 = var14 + var39 + 18.0F;
         float var20 = var16 - var39 - 18.0F;
         float var21 = var5 ? 42.0F : 30.0F;
         r(var0, var19 + 11.0F, var15 + 13.0F, 11.0F, var10);
         TrueTypeFont var22 = Fonts.MEDIUM;
         boolean var23 = var4.playing();
         long var24 = 15072267625552L;
         int var27 = var24 < 0L ? -1 : 1;
         String var27x = Translations.tr(var23 ? "Now playing" : "Paused");
         long var28 = 15072267625552L;
         int var31 = var28 < 0L ? -1 : 1;
         float var31x = var19 + 30.0F;
         float var32 = var15 + 14.0F;
         long var33 = 15072267625552L;
         int var33x = var33 < 0L ? -1 : 1;
         var0.text(var22, var27x, var31x, var32, 25.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         if (var5) {
            long var36x = 15072267625553L;
            int var35 = var36x < 0L ? -1 : 1;
            var0.textEllipsized(Fonts.BOLD, var4.title(), var19, var15 + 74.0F, var21, var20, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
            var0.textEllipsized(Fonts.MEDIUM, var4.artist(), var19, var15 + 122.0F, 28.0F, var20, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
            w(var0, var19, var15 + 166.0F, var20, var4, var6);
            a(var0, var19 + var20 / 2.0F, var15 + var17 - 40.0F, var4, 94.0F, var8);
         } else {
            var0.textEllipsized(Fonts.BOLD, var4.title(), var19, var15 + 50.0F, var21, var20, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
            var0.textEllipsized(Fonts.MEDIUM, var4.artist(), var14, var15 + var39 + 24.0F, 26.0F, var16, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
            a(var0, var1.q + var1.i / 2.0F, var1.H + var1.f - 44.0F, var4, 74.0F, var8);
         }
      }
   }

   private static void w(Canvas var0, float var1, float var2, float var3, MountBootRenderer.Data var4, long var5) {
      long var7 = 135109620982703L;
      MountFactory.MountSpec.ensureSourceClassInitialized();
      var0.roundRect(var1, var2, var3, 7.0F, 3.5F, 822083583);
      float var9 = var4.durationMs() > 0L ? Math.clamp((float)var4.positionMs() / (float)var4.durationMs(), 0.0F, 1.0F) : 0.0F;
      long var10 = 6422594080821L;
      int var13 = var10 < 0L ? -1 : 1;
      if (var9 > 0.0F) {
         var0.roundRectGradient(var1, var2, Math.max(7.0F, var3 * var9), 7.0F, 3.5F, 0.0F, -1, Theme.tint(-1849089));
      }

      var0.text(Fonts.MEDIUM, R(var4.positionMs(), var7), var1, var2 + 28.0F, 25.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      TrueTypeFont var13x = Fonts.MEDIUM;
      long var14;
      int var16 = (var14 = var4.durationMs() - 0L) == 0L ? 0 : (var14 < 0L ? -1 : 1);
      long var17 = 6422594080822L;
      int var18 = var17 < 0L ? -1 : 1;
      var0.text(
         var13x,
         var16 > 0 ? R(var4.durationMs(), var7) : Translations.tr("LIVE"),
         var1 + var3,
         var2 + 28.0F,
         25.0F,
         Icons.TEXT_2,
         FontStyle.RIGHT,
         FontWeight.MIDDLE
      );
   }

   private static void a(Canvas var0, float var1, float var2, MountBootRenderer.Data var3, float var4, long var5) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      var0.circle(var1, var2, 34.0F, BlendMode.withAlpha(-1, 0.92F));
      boolean var7 = var3.playing();
      long var8 = 133307609833469L;
      int var11 = var8 < 0L ? -1 : 1;
      String var11x = var7 ? "pause" : "play";
      long var12 = 133307609833470L;
      int var13 = var12 < 0L ? -1 : 1;
      var0.icon(var11x, var1, var2, 30.0F, -15593952);
      var0.icon("skip-back", var1 - var4, var2, 34.0F, var3.previous() ? Icons.TEXT : Icons.TEXT_3);
      var0.icon("skip-forward", var1 + var4, var2, 34.0F, var3.next() ? Icons.TEXT : Icons.TEXT_3);
   }

   private static void O(Canvas var0, float var1, long var2, float var4, float var5) {
      label22: {
         long var6 = 99150813940610L;
         MountFactory.MountSpec.ensureSourceClassInitialized();
         if (s != null) {
            long var8 = 116566880994809L;
            int var11 = var8 < 0L ? -1 : 1;
            if (U) {
               long var11x = 116566880994809L;
               int var12 = var11x < 0L ? -1 : 1;
               var0.image(s, RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR), false, var1, var4, var5, var5, 0.0F, 0.0F, 1.0F, 1.0F, 14.0F, -1);
               break label22;
            }
         }

         var0.roundRectGradient(var1, var4, var5, var5, 14.0F, 135.0F, Theme.tint(-13359012), Theme.tint(-15586252));
         r(var0, var1 + var5 / 2.0F, var4 + var5 / 2.0F, var5 * 0.26F, var6);
      }

      var0.roundRectOutline(var1, var4, var5, var5, 14.0F, 1.0F, 654311423);
   }

   private static void r(Canvas var0, float var1, float var2, float var3, long var4) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      var0.circle(var1, var2, var3, -14756000);

      for (int var6 = 0; var6 < 3; var6++) {
         float var7 = var3 * (0.72F - var6 * 0.12F);
         var0.arc(var1, var2 + var3 * (0.52F + var6 * 0.22F) * 0.9F - var7 * 0.1F, var7 * 1.15F, var3 * 0.13F, 225.0F, 90.0F, -16118772);
      }
   }

   private static void w(Canvas var0, MountBootRenderer.BootRect var1, MountFactory.MountSpec var2, long var3) {
      long var5 = 26780389514044L;
      short var7 = 6235;
      int var8 = 4954779;
      int var9 = (int)(var5 << 56 >>> 56);
      float var10 = var1.H + var1.f / 2.0F;
      float var11 = MountInteraction.speedKmh(MountCameraState.partialTick());
      var0.text(Fonts.BOLD, Integer.toString(Math.round(var11)), var1.q + 20.0F, var10 - 4.0F, 56.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      float var12 = var0.textWidth(Fonts.BOLD, Integer.toString(Math.round(var11)), 56.0F);
      var0.text(Fonts.MEDIUM, "km/h", var1.q + 20.0F + var12 + 8.0F, var10 + 10.0F, 25.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      String var13 = w(var7, var8, (byte)var9);
      float var14 = var1.q + var1.i - 46.0F;
      var0.roundRectGradient(var14 - 28.0F, var10 - 28.0F, 56.0F, 56.0F, 16.0F, 135.0F, Theme.tint(Icons.INDIGO), Theme.tint(Icons.MAGENTA));
      var0.text(Fonts.BOLD, var13, var14, var10, 34.0F, -1, FontStyle.CENTER, FontWeight.MIDDLE);
   }

   private static String w(int var0, int var1, byte var2) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      int var3 = MountInteraction.dashboardGear();
      return var3 < 0 ? "R" : (var3 == 0 ? "N" : Integer.toString(var3));
   }

   private static void V(Canvas var0, MountBootRenderer.BootRect var1, long var2, MountBootRenderer.BootRect var4, MountFactory.MountSpec var5) {
      long var6 = 12822392419874L;
      long var8 = 26780389514044L;
      short var10 = 6235;
      int var11 = 4954779;
      int var12 = (int)(var8 << 56 >>> 56);
      MountFactory.MountSpec.ensureSourceClassInitialized();
      float var13 = MountCameraState.partialTick();
      float var14 = var1.q + var1.i / 2.0F;
      float var15 = var1.H + var1.f * 0.56F;
      float var16 = Math.min(var1.i, var1.f) * 0.36F;
      float var17 = MountInteraction.rpm(var13);
      var0.arc(var14, var15, var16, 13.0F, 135.0F, 270.0F, 536870911);
      if (var17 > 0.005F) {
         var0.arc(var14, var15, var16, 13.0F, 135.0F, 270.0F * var17, Theme.tint(Icons.VIOLET), Theme.tint(Icons.PINK), true);
      }

      float var18 = MountInteraction.speedKmh(var13);
      var0.text(Fonts.BOLD, Integer.toString(Math.round(var18)), var14, var15 - 8.0F, 78.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
      var0.text(Fonts.MEDIUM, "km/h", var14, var15 + 44.0F, 26.0F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
      var0.text(Fonts.SEMIBOLD, var5.name(), var1.q + 22.0F, var1.H + 34.0F, 28.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      float var19 = var4.q + 18.0F;
      float var20 = var4.H + 18.0F;
      float var21 = var4.i - 36.0F;
      var0.text(Fonts.MEDIUM, Translations.tr("Gear"), var19, var20 + 12.0F, 25.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      var0.text(Fonts.BOLD, w(var10, var11, (byte)var12), var19 + var21, var20 + 12.0F, 34.0F, Icons.TEXT, FontStyle.RIGHT, FontWeight.MIDDLE);
      u(var0, var19, var20 + 62.0F, var21, "Throttle", var6, MountInteraction.throttle(var13), Icons.MAGENTA);
      u(var0, var19, var20 + 128.0F, var21, "Brake", var6, MountInteraction.brake(var13), -42406);
      float var22 = MountInteraction.yaw(var13);
      int var23 = 36384;
      long var24 = 54418640417191L;
      int var27 = var24 < 0L ? -1 : 1;
      var27 = 1404808260 - var23;
      var23 = -1404782149;
      int var26 = 1404776871;
      var0.text(Fonts.MEDIUM, Translations.tr("Heading"), var19, var20 + 212.0F, 25.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      var0.text(Fonts.BOLD, MountBootRenderer.Data.heading(var22), var19 + var21, var20 + 212.0F, 34.0F, Icons.TEXT, FontStyle.RIGHT, FontWeight.MIDDLE);
      long var28 = 54418640417190L;
      int var30 = var28 < 0L ? -1 : 1;
      var27 = 1404771889;
      var23 = 1404787751;
      var26 = 1404776871;
      var0.text(Fonts.MEDIUM, Translations.tr("Lights"), var19, var20 + 268.0F, 25.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      var0.text(
         Fonts.SEMIBOLD,
         Translations.tr(MountTransform.headlights() > 0.5F ? "On" : "Auto"),
         var19 + var21,
         var20 + 268.0F,
         28.0F,
         Icons.TEXT,
         FontStyle.RIGHT,
         FontWeight.MIDDLE
      );
   }

   private static void u(Canvas var0, float var1, float var2, float var3, String var4, long var5, float var7, int var8) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      var0.text(Fonts.MEDIUM, Translations.tr(var4), var1, var2, 25.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      long var9 = 12822392419874L;
      int var12 = var9 < 0L ? -1 : 1;
      float var12x = var2 + 22.0F;
      long var13 = 12822392419873L;
      int var14 = var13 < 0L ? -1 : 1;
      var0.roundRect(var1, var12x, var3, 8.0F, 4.0F, 620756991);
      if (var7 > 0.01F) {
         var0.roundRect(var1, var2 + 22.0F, Math.max(8.0F, var3 * var7), 8.0F, 4.0F, Theme.tint(var8));
      }
   }

   private static void K(Canvas var0, float var1, float var2, float var3, float var4, long var5, MountFactory.MountSpec var7, MountBootRenderer.Data var8) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      float var9 = MountInteraction.speedKmh(MountCameraState.partialTick());
      float var10 = var1 + var3 / 2.0F;
      TrueTypeFont var11 = Fonts.BOLD;
      String var12 = Integer.toString(Math.round(var9));
      float var13 = 147.92F;
      long var14 = 37252207484085L;
      int var17 = var14 < 0L ? -1 : 1;
      var0.text(var11, var12, var10, var13, 110.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
      long var17x = 37252207484084L;
      int var20 = var17x < 0L ? -1 : 1;
      var0.text(Fonts.MEDIUM, "km/h", var10, 219.92F, 30.0F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
      if (var8.present()) {
         long var20x = 37252207484084L;
         var0.textEllipsized(Fonts.SEMIBOLD, var8.title(), var10, 344.0F, 30.0F, var3 - 40.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
      } else {
         var0.text(Fonts.SEMIBOLD, LocalTime.now().format(c), var10, 344.0F, 32.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
      }
   }

   public static boolean click(float var0, float var1) {
      long var2 = 69733517270208L;
      long var4 = var2 ^ 87122183738689L;
      long var6 = var2 ^ 60094444631096L;
      long var8 = var2 ^ 60190724943788L;
      long var10 = var2 ^ 12469861330201L;
      long var12 = var2 ^ 634110346794L;
      int var14 = (int)((var2 ^ 634110346794L) >>> 32);
      int var15 = (int)((var2 ^ 634110346794L) << 32 >>> 48);
      int var16 = (int)(var12 << 48 >>> 48);
      long var17 = var2 ^ 69454948623433L;
      long var19 = var2 ^ 126505931752194L;
      long var21 = var2 ^ 84598824573532L;
      long var23 = var2 ^ 88249979712254L;
      if (S(var14, (short)var15, var16) < 2.6999998F) {
         return false;
      } else {
         float var25 = y(var17);
         float var26 = var0 * var25;
         float var27 = var1 * 400.0F;
         MountData var28 = a(var8);
         boolean var29 = var28 != null && !var28.title().isBlank();
         if (var25 < 640.0F) {
            return x(var28, var29, var23);
         } else if (var26 < 96.0F) {
            for (int var42 = 0; var42 < 4; var42++) {
               if (Math.abs(var27 - i(var21, var42)) < 36.0F) {
                  R(MountBootRenderer.BootScreen.values()[var42], var6);
                  return true;
               }
            }

            return false;
         } else {
            float var30 = 96.0F;
            float var31 = 14.0F;
            float var32 = var25 - 96.0F - 14.0F;
            float var33 = 372.0F;
            MountBootRenderer.BootRect[] var34 = k(var19, v, var30, var31, var32, var33);
            switch (v) {
               case Z:
                  if (var34[0].has(var26, var27)) {
                     R(MountBootRenderer.BootScreen.W, var6);
                     return true;
                  }

                  if (var34[1].has(var26, var27)) {
                     if (!var29) {
                        return r(var10);
                     }

                     float var43 = var34[1].H + var34[1].f - 44.0F;
                     float var44 = var34[1].q + var34[1].i / 2.0F;
                     if (Math.abs(var27 - var43) < 42.0F && U(var26, var4, var44, 74.0F, var28)) {
                        return true;
                     }

                     R(MountBootRenderer.BootScreen.V, var6);
                     return true;
                  }

                  if (var34[2].has(var26, var27)) {
                     R(MountBootRenderer.BootScreen.q, var6);
                     return true;
                  }
                  break;
               case W:
                  MountBootRenderer.BootRect var35 = var34[0];
                  if (var26 > var35.q + var35.i - 78.0F && Math.abs(var27 - (var35.H + var35.f / 2.0F)) < 66.0F) {
                     p.zoom(var27 < var35.H + var35.f / 2.0F);
                     P = 0L;
                     return true;
                  }
                  break;
               case V:
                  if (!var29) {
                     return r(var10);
                  }

                  MountBootRenderer.BootRect var36 = var34[0];
                  float var37 = Math.min(var36.f - 32.0F, 220.0F);
                  float var38 = var36.q + 18.0F + var37 + 18.0F;
                  float var39 = var36.i - 36.0F - var37 - 18.0F;
                  float var40 = var36.H + 16.0F + (var36.f - 32.0F) - 40.0F;
                  float var41 = var38 + var39 / 2.0F;
                  if (Math.abs(var27 - var40) < 42.0F) {
                     return U(var26, var4, var41, 94.0F, var28);
                  }
            }

            return false;
         }
      }
   }

   private static boolean x(MountData var0, boolean var1, long var2) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      if (var1 && var0.can(128)) {
         MountTransport.get().send(new MountApi.TogglePlayPause());
         return true;
      } else {
         return false;
      }
   }

   private static boolean U(float var0, long var1, float var3, float var4, MountData var5) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      if (var5 == null) {
         return false;
      } else {
         MountTransport var6 = MountTransport.get();
         long var7 = 123491841395073L;
         int var10 = var7 < 0L ? -1 : 1;
         float var10x = var0 - var3;
         long var11 = 123491841395073L;
         int var13 = var11 < 0L ? -1 : 1;
         if (!(Math.abs(var10x) < 42.0F)) {
            long var24 = 123491841395072L;
            int var271 = var24 < 0L ? -1 : 1;
            float var15;
            int var25 = (var15 = Math.abs(var0 - (var3 - var4)) - 40.0F) == 0.0F ? 0 : (var15 < 0.0F ? -1 : 1);
            long var26 = 123491841395072L;
            int var27 = var26 < 0L ? -1 : 1;
            if (var25 < 0) {
               boolean var27x = var5.can(8);
               long var20 = 123491841395073L;
               int var211 = var20 < 0L ? -1 : 1;
               if (var27x) {
                  var6.send(new MountApi.SkipPrevious());
                  return true;
               }
            }

            if (Math.abs(var0 - (var3 + var4)) < 40.0F && var5.can(4)) {
               var6.send(new MountApi.SkipNext());
               return true;
            } else {
               return false;
            }
         } else {
            long var13x = 123491841395072L;
            int var16 = var13x < 0L ? -1 : 1;
            boolean var16x = var5.can(128);
            long var17 = 123491841395072L;
            int var17x = var17 < 0L ? -1 : 1;
            if (var16x) {
               var6.send(new MountApi.TogglePlayPause());
            } else {
               if (var5.playing()) {
                  long var19x = 123491841395073L;
                  boolean var21x = var5.can(2);
                  long var22 = 123491841395073L;
                  int var22x = var22 < 0L ? -1 : 1;
                  if (var21x) {
                     var6.send(new MountApi.Pause());
                     return true;
                  }
               }

               if (!var5.playing() && var5.can(1)) {
                  var6.send(new MountApi.Play());
               }
            }

            return true;
         }
      }
   }

   private static boolean r(long var0) {
      class_156.method_668().method_670("spotify:");
      return true;
   }

   public static void devPage(String var0) {
      long var1 = 133321451118172L;
      long var3 = var1 ^ 123740629038756L;
      R(MountBootRenderer.BootScreen.valueOf(var0.toUpperCase(Locale.ROOT)), var3);
   }

   private static void R(MountBootRenderer.BootScreen var0, long var1) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      long var3 = 10770930788599L;
      int var5 = var3 < 0L ? -1 : 1;
      if (var0 != v) {
         u = v;
         v = var0;
         k = 0.0F;
      }
   }

   private static MountData a(long var0) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      MountData var2 = MountTransport.get().state();
      long var3 = 10798206415723L;
      int var5 = var3 < 0L ? -1 : 1;
      return var2 != null && var2.app().toLowerCase(Locale.ROOT).contains("spotify") ? var2 : null;
   }

   private static void W(long var0, class_310 var2, long var3) {
      long var5 = 45248541759556L;
      MountFactory.MountSpec.ensureSourceClassInitialized();
      if (I != null && I.isDone()) {
         MountMapTiles.Data var7 = null;

         try {
            var7 = I.join();
         } catch (RuntimeException var9) {
         }

         I = null;
         if (var7 != null) {
            C(var5, var7);
         }
      }

      if (I == null && var3 >= P && var2.field_1687 != null) {
         P = var3 + 350000000L;
         class_243 var10 = MountInteraction.renderPosition(1.0F);
         class_638 var8 = var2.field_1687;
         I = MountWorker.submit(() -> p.update(var8, var10.field_1352, var10.field_1350, var3));
      }
   }

   private static void C(long var0, MountMapTiles.Data var2) {
      G = var2.offsetX();
      MountFactory.MountSpec.ensureSourceClassInitialized();
      N = var2.offsetZ();
      z = var2.step();
      BufferedImage var3 = var2.image();
      int var4 = var3.getWidth();
      int var5 = var3.getHeight();
      GpuDevice var6 = RenderSystem.getDevice();
      long var7 = 45248541759555L;
      int var16 = var7 < 0L ? -1 : 1;
      if (r == null) {
         r = var6.createTexture(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX screen map"), 5, TextureFormat.RGBA8, var4, var5, 1, 1);
         E = var6.createTextureView(r);
         b = MemoryUtil.memAlloc(var4 * var5 * 4);
      }

      b.clear();

      for (int var10 = 0; var10 < var5; var10++) {
         for (int var11 = 0; var11 < var4; var11++) {
            int var12 = var3.getRGB(var11, var10);
            long var13 = 45248541759556L;
            int var14 = var13 < 0L ? -1 : 1;
            b.put((byte)(var12 >> 16)).put((byte)(var12 >> 8)).put((byte)var12).put((byte)-1);
         }

         long var17 = 45248541759556L;
         int var19 = var17 < 0L ? -1 : 1;
      }

      long var16x = 45248541759556L;
      int var18 = var16x < 0L ? -1 : 1;
      b.flip();
      var6.createCommandEncoder().writeToTexture(r, b, class_1012.field_4997, 0, 0, 0, 0, var4, var5);
   }

   private static void C(MountData var0, MountTransport.Data var1, long var2) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      long var4 = var0 == null ? Long.MIN_VALUE : var0.track();
      long var6 = 68842582268023L;
      int var18 = var6 < 0L ? -1 : 1;
      if (var4 != g) {
         g = var4;
         long var9 = 68842582268024L;
         int var12 = var9 < 0L ? -1 : 1;
         m = null;
         x = null;
         long var11 = 68842582268023L;
         int var14 = var11 < 0L ? -1 : 1;
         U = false;
         if (W != null) {
            W.cancel(false);
         }

         W = null;
      }

      long var18x = 68842582268023L;
      int var19 = var18x < 0L ? -1 : 1;
      if (W != null && W.isDone()) {
         try {
            m = W.join();
            J = m != null;
         } catch (RuntimeException var15) {
            m = null;
         }

         W = null;
      }

      if (var0 != null) {
         long var12 = 68842582268024L;
         int var21 = var12 < 0L ? -1 : 1;
         if (var1 != null) {
            long var14 = 68842582268023L;
            int var14x = var14 < 0L ? -1 : 1;
            if (var1.track() == var4 && var1.data() != x) {
               x = var1.data();
               byte[] var16x = x;
               W = MountWorker.submit(() -> {
                  byte[] var16 = var16x;
                  long var1x = 2405392947430L;
                  long var3 = var1x ^ 27347913344297L;
                  return O(var3, var16);
               });
            }
         }
      }
   }

   private static void m(int var0, int var1, int var2) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      if (J && m != null) {
         J = false;
         int var3 = m.getWidth();
         GpuDevice var4 = RenderSystem.getDevice();
         short var5 = 11441;
         var5 = 28440;
         if (C == null) {
            C = var4.createTexture(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX screen art"), 5, TextureFormat.RGBA8, var3, var3, 1, 1);
            s = var4.createTextureView(C);
            L = MemoryUtil.memAlloc(var3 * var3 * 4);
         }

         L.clear();

         for (int var6 = 0; var6 < var3; var6++) {
            for (int var7 = 0; var7 < var3; var7++) {
               int var8 = m.getRGB(var7, var6);
               L.put((byte)(var8 >> 16)).put((byte)(var8 >> 8)).put((byte)var8).put((byte)-1);
            }
         }

         L.flip();
         var4.createCommandEncoder().writeToTexture(C, L, class_1012.field_4997, 0, 0, 0, 0, var3, var3);
         U = true;
      }
   }

   private static BufferedImage O(long var0, byte[] var2) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      long var3 = 29615447776718L;
      int var34 = var3 < 0L ? -1 : 1;
      if (var2 != null && var2.length != 0 && var2.length <= 12582912) {
         try {
            Object var36;
            try (MemoryCacheImageInputStream var6 = new MemoryCacheImageInputStream(new ByteArrayInputStream(var2))) {
               Iterator var7 = ImageIO.getImageReaders(var6);
               if (!var7.hasNext()) {
                  return null;
               }

               ImageReader var8 = (ImageReader)var7.next();

               try {
                  var8.setInput(var6, true, true);
                  int var9 = var8.getWidth(0);
                  int var10 = var8.getHeight(0);
                  long var11 = 29615447776718L;
                  int var13 = var11 < 0L ? -1 : 1;
                  if (var9 >= 1) {
                     long var13x = 29615447776719L;
                     int var15 = var13x < 0L ? -1 : 1;
                     if (var10 >= 1) {
                        long var15x = 29615447776718L;
                        int var17 = var15x < 0L ? -1 : 1;
                        if (var9 <= 4096 && var10 <= 4096) {
                           ImageReadParam var17x = var8.getDefaultReadParam();
                           int var18 = Math.max(1, Math.min(var9, var10) / 512);
                           var17x.setSourceSubsampling(var18, var18, 0, 0);
                           BufferedImage var19 = var8.read(0, var17x);
                           int var20 = Math.min(var19.getWidth(), var19.getHeight());
                           int var21 = (var19.getWidth() - var20) / 2;
                           int var22 = (var19.getHeight() - var20) / 2;
                           BufferedImage var23 = new BufferedImage(280, 280, 1);
                           Graphics2D var24 = var23.createGraphics();
                           var24.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                           var24.drawImage(var19, 0, 0, 280, 280, var21, var22, var21 + var20, var22 + var20, null);
                           var24.dispose();
                           return var23;
                        }
                     }
                  }

                  var36 = null;
               } finally {
                  var8.dispose();
               }
            }

            return (BufferedImage)var36;
         } catch (Exception var341) {
            return null;
         }
      } else {
         return null;
      }
   }

   private static String R(long var0, long var2) {
      long var4 = Math.max(0L, var0) / 1000L;
      return var4 / 60L + ":" + (var4 % 60L < 10L ? "0" : "") + var4 % 60L;
   }

   public static void close() {
      if (E != null) {
         E.close();
      }

      if (r != null) {
         r.close();
      }

      if (s != null) {
         s.close();
      }

      if (C != null) {
         C.close();
      }

      if (b != null) {
         MemoryUtil.memFree(b);
      }

      if (L != null) {
         MemoryUtil.memFree(L);
      }

      s = null;
      E = null;
      C = null;
      r = null;
      L = null;
      b = null;
      if (W != null) {
         W.cancel(false);
      }

      W = null;
      m = null;
      x = null;
      g = Long.MIN_VALUE;
      U = false;
      if (I != null) {
         I.cancel(false);
      }

      I = null;
      p.clear();
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 4592) & 32767;
      if (y[var3] == null) {
         y[var3] = t[var3] ^ var1;
      }

      return y[var3];
   }

   record Data(String p, String X, boolean n, long g, long I, boolean U, boolean J, String N) {
      private static final String[] b = new String[11];

      boolean present() {
         return this.p != null && !this.p.isBlank();
      }

      static MountBootRenderer.Data of(MountData var0, MountTransport var1, long var2, BufferedImage var4) {
         if (var0 != null && !var0.title().isBlank()) {
            return new MountBootRenderer.Data(
               TabListProvider.plain(var0.title()),
               TabListProvider.plain(var0.artist()),
               var0.playing(),
               var0.positionAt(var2),
               var0.durationMs(),
               var0.can(8),
               var0.can(4),
               ""
            );
         } else {
            String var5 = switch (MountBootRenderer.BootState.m[var1.health().ordinal()]) {
               case 1 -> "Windows player unavailable";
               case 2 -> "Player not connected";
               default -> "Nothing playing";
            };
            return new MountBootRenderer.Data("", "", false, 0L, 0L, false, false, var5);
         }
      }

      static String heading(float var0) {
         String[] var1 = Translations.russian()
            ? new String[]{"Ю", "ЮЗ", "З", "СЗ", "С", "СВ", "В", "ЮВ"}
            : new String[]{"S", "SW", "W", "NW", "N", "NE", "E", "SE"};
         return var1[Math.floorMod(Math.round(var0 / 45.0F), 8)];
      }

      public String title() {
         return this.p;
      }

      public String artist() {
         return this.X;
      }

      public boolean playing() {
         return this.n;
      }

      public long positionMs() {
         return this.g;
      }

      public long durationMs() {
         return this.I;
      }

      public boolean previous() {
         return this.U;
      }

      public boolean next() {
         return this.J;
      }

      public String idle() {
         return this.N;
      }

      public String stringState12() {
         return this.p;
      }

      public String stringState6() {
         return this.X;
      }

      public boolean booleanState33() {
         return this.n;
      }

      public long longState18() {
         return this.g;
      }

      public long longState54() {
         return this.I;
      }

      public boolean booleanState31() {
         return this.U;
      }

      public boolean booleanState35() {
         return this.J;
      }

      public String stringState53() {
         return this.N;
      }
   }

   private static enum BootScreen {
      Z,
      W,
      V,
      q;
   }

   static class BootState {
      static final int[] m = new int[MountTransport.TransportMode.values().length];

      static {
         try {
            m[MountTransport.TransportMode.n.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
         }

         try {
            m[MountTransport.TransportMode.F.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
         }
      }
   }

   private record BootRect(float q, float H, float i, float f) {
      boolean has(float var1, float var2) {
         return var1 >= this.q && var2 >= this.H && var1 <= this.q + this.i && var2 <= this.H + this.f;
      }

      public float x() {
         return this.q;
      }

      public float y() {
         return this.H;
      }

      public float w() {
         return this.i;
      }

      public float h() {
         return this.f;
      }

      public float floatState48() {
         return this.q;
      }

      public float floatState7() {
         return this.H;
      }

      public float floatState12() {
         return this.i;
      }

      public float floatState55() {
         return this.f;
      }
   }
}
