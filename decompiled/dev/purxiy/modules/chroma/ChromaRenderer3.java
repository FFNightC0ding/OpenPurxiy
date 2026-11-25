package dev.purxiy.modules.chroma;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Snippet;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.DrawContext.TypeE;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1011;
import net.minecraft.class_10789;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_6367;
import org.lwjgl.system.MemoryUtil;

public final class ChromaRenderer3 {
   public static final float G = 214.0F;
   private static final float k = 51.0F;
   private static final float a = 8.0F;
   private static final String[] D = new String[]{
      "..............................CCC...",
      "...OOO.......................CCCCC..",
      "..OOOOO......................CCCCC..",
      "..OOOOO.......................CKC...",
      "...OLO..........g..............K....",
      "....L......GGGGGGt.............K..a.",
      ".g..L.p..GGDDDDDDGG.c.....GGGGGGGGGG",
      "GGGGGGGGGDDDDDDDDDDGGGWWWWDDDDDDDDDD",
      "DDDDDDDDDDDSDDDDDDDDDDDDDDDDSDDDDDDD",
      "DDDSDDDDSSSSSDDDDDSSSSSSSSSSSDDDDDDD"
   };
   private static final int i = 36;
   private static final int O = 10;
   private static final float T = 21.45F;
   private static final float A = 26.55F;
   private static final float f = 2.0F;
   private static final float w = 3.1F;
   private static final float V = 0.6F;
   private static final float q = 14.0F;
   private static final float u = -1.5F;
   private static final int j = 80;
   private static final int C = 2672;
   private static final int b = -4222985;
   private static final int H = -8798118;
   private static final int K = -10899920;
   private static final int Q = -12618012;
   private static final RenderPipeline c = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/chroma_preview"))
      .withVertexShader(ClientServices.id("core/chroma"))
      .withFragmentShader(ClientServices.id("core/chroma_preview"))
      .withUniform("PurxiyChromaPreview", class_10789.field_60031)
      .withSampler("SceneSampler")
      .withoutBlend()
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static class_6367 L;
   private static GpuTexture Z;
   private static GpuTextureView y;
   private static boolean e;
   private static GpuBuffer S;
   private static ByteBuffer F;
   private final ChromaModule d;
   private final Object W;
   private final List<ChromaRenderer3.TypeQ> M;
   private float B;
   private float v;
   private boolean m;
   private float U;
   private float r;
   private float h;
   private float I;
   private float E;
   private float z;
   private float N;
   private float J;
   private float l;
   private float n;
   private float g;
   private boolean Y;
   private static final long[] s = new long[]{
      248851796817390984L,
      8547280440249412804L,
      123777210829699801L,
      -6202562907223012747L,
      6502408237375840151L,
      1011469731115714141L,
      4127113595037953831L,
      702675550056820145L,
      5169019733330495615L,
      2011973346681261299L,
      -1815093876644559960L,
      -8064980698618173088L,
      4998529760860868721L,
      -4226154327271720947L,
      -3603967865725601865L,
      -798684431248691446L,
      -2909194066165868447L,
      -3755469913594321071L,
      -7204600211360999721L,
      -2783420360684340025L,
      1517582671260156702L,
      -4235452185676256600L,
      -6492861070780911407L,
      4517313785405209156L,
      1995616881119057804L,
      -5750578604348383434L,
      5881996328552422572L,
      -5511198035871751914L,
      6373143748194601158L,
      1717773093629728836L,
      -6951784445639861634L,
      -7337384422888057832L,
      8326415942213383533L,
      -4704641534306256662L,
      -2988610157322433830L,
      -2104645379011483684L,
      -5364857701963848112L,
      -384784282909916800L,
      -9077756350869617532L,
      2341609471513809382L,
      -8548012796359998334L,
      7954002076476731342L,
      362835874182511773L,
      -6171255951621465319L,
      3818172780524377846L,
      -5714759925174831371L,
      898767139067501465L,
      -9069881482188913609L,
      5401903819139861637L,
      8094254400590683202L,
      7410576871478748569L,
      2302190726632227877L,
      666285952076302033L,
      3230790917049306814L,
      4918882240754207009L
   };
   private static final long x = 2672L;

   public ChromaRenderer3(ChromaModule var1) {
      long var2 = 115301781484454L;
      long var4 = var2 ^ 94996667092876L;
      this.W = new Object();
      this.M = new ArrayList<>();
      this.B = 6.4F;
      this.v = Float.NaN;
      this.d = var1;
      this.q(this.B, var4);
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 133304119751460L;
      long var8 = var6 ^ 31057056404729L;
      int var10 = (int)((var6 ^ 31057056404729L) >>> 32);
      int var11 = (int)((var6 ^ 31057056404729L) << 32 >>> 48);
      int var12 = (int)(var8 << 48 >>> 48);
      var8 = var6 ^ 62522436851184L;
      int var13 = (int)((var6 ^ 62522436851184L) >>> 48);
      int var14 = (int)((var6 ^ 62522436851184L) << 16 >>> 32);
      int var15 = (int)(var8 << 48 >>> 48);
      long var16 = var6 ^ 52899465253071L;
      long var18 = var6 ^ 132664491630999L;
      this.U = var2;
      this.r = var3;
      this.h = var4;
      this.Y = var5 && !var1.masked();
      Canvas var20 = var1.canvas;
      var20.text(Fonts.MEDIUM, "Walk, and colour follows", var2, var3 + 10.0F, 13.5F, Icons.TEXT);
      var20.text(Fonts.REGULAR, "The world waits in grey until you pass by", var2, var3 + 30.0F, 11.5F, Icons.TEXT_2);
      this.I = var2;
      this.E = var3 + 51.0F;
      this.z = var4;
      this.N = 155.0F;
      boolean var21 = this.Y && var1.hover(this.W, this.I, this.E, this.z, this.N, TypeE.HAND);
      this.V((short)var13, var14, (char)var15);
      this.P(var10, var11, var12, Math.min(var1.dt, 0.1F));
      var20.roundRect(this.I, this.E, this.z, this.N, 12.0F, Theme.tint(-15067866));
      this.o(var18, var20);
      this.A(var20, var16);
      var20.roundRectOutline(this.I, this.E, this.z, this.N, 12.0F, 1.0F, BlendMode.withAlpha(Theme.tint(-4222985), var21 ? 0.4F : 0.18F));
      float var22 = var20.textWidth(Fonts.MEDIUM, "CLICK TO SPLASH", 8.5F);
      var20.roundRect(this.I + 9.0F, this.E + 8.0F, var22 + 12.0F, 17.0F, 8.5F, BlendMode.withAlpha(Theme.tint(-15593956), var21 ? 0.62F : 0.46F));
      var20.text(Fonts.MEDIUM, "CLICK TO SPLASH", this.I + 15.0F, this.E + 12.0F, 8.5F, var21 ? Icons.TEXT : Icons.TEXT_2);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      long var4 = 54964235617320L;
      int var6 = (int)((var4 ^ 8205194332727L) >>> 56);
      long var7 = (var4 ^ 8205194332727L) << 8 >>> 8;
      if (this.Y && var3 == 0 && !(var1 < this.I) && !(var1 >= this.I + this.z) && !(var2 < this.E) && !(var2 >= this.E + this.N)) {
         float var9 = (this.J + (var1 - this.I) / this.z * (this.n - this.J)) * 36.0F;
         float var10 = (this.l + (var2 - this.E) / this.N * (this.g - this.l)) * 10.0F;
         this.C(var9, var10, this.n() * 1.3F, (byte)var6, this.B, var7);
         UiAudioPolicy.play("CLICK");
         return true;
      } else {
         return false;
      }
   }

   private void V(short var1, int var2, char var3) {
      ChromaCodec.ensureSourceClassInitialized();
      float var4 = 3.6F;
      float var5 = this.z / this.N;
      if (var5 >= var4) {
         float var6 = 36.0F / var5;
         this.J = 0.0F;
         this.n = 1.0F;
         this.g = 1.0F;
         this.l = 1.0F - var6 / 10.0F;
      } else {
         float var7 = 10.0F * var5;
         this.l = 0.0F;
         this.g = 1.0F;
         this.J = (1.0F - var7 / 36.0F) * 0.5F;
         this.n = this.J + var7 / 36.0F;
      }
   }

   private float n() {
      return 1.2F + this.d.s.floatValue() * 0.22F;
   }

   private float B(long var1) {
      return switch (this.d.g.index()) {
         case 0 -> 2.4F;
         case 1 -> 3.8F;
         case 2 -> 5.5F;
         default -> 14.0F;
      };
   }

   private void P(int var1, int var2, int var3, float var4) {
      long var5 = 111060344694749L;
      long var7 = var5 ^ 95370980853019L;
      long var9 = var5 ^ 127816045451909L;
      ChromaCodec.ensureSourceClassInitialized();
      float var11 = this.B;
      this.B += var4;
      if (this.B >= 14.0F) {
         this.B -= 14.0F;
         this.M.clear();
         this.v = Float.NaN;
         this.m = false;
         var11 = 0.0F;
      }

      this.l(var11, this.B, var9);
      float var12 = this.B(var7);
      float var13 = this.B;
      this.M.removeIf(var2x -> {
         ChromaCodec.ensureSourceClassInitialized();
         return var13 - var2x.D > var12 + 1.4F;
      });
   }

   private void l(float var1, float var2, long var3) {
      long var5 = 79576506486559L;
      byte var7 = 0;
      int var8 = 1214241126;
      int var9 = (int)(var5 << 48 >>> 48);
      var5 = 72806842306653L;
      short var10 = 16951;
      char var11 = '꧹';
      int var12 = (int)(var5 << 48 >>> 48);
      byte var13 = 0;
      long var14 = 59971434499615L;
      float var16 = this.n();
      ChromaCodec.ensureSourceClassInitialized();
      float var17 = -1.5F + 3.1F * var1;
      float var18 = -1.5F + 3.1F * var2;
      if (Float.isNaN(this.v)) {
         this.v = var17 - 0.6F;
      }

      for (float var19 = this.v + 0.6F; var19 <= var18; var19 += 0.6F) {
         this.v = var19;
         float var20 = var1 + (var19 - var17) / 3.1F;
         if (var19 > 21.45F) {
            float var21;
            int var22 = (var21 = var19 - 26.55F) == 0.0F ? 0 : (var21 < 0.0F ? -1 : 1);
            long var23 = 18954724097367L;
            int var26 = var23 < 0L ? -1 : 1;
            if (var22 < 0) {
               continue;
            }
         }

         long var34 = 18954724097368L;
         int var34x = var34 < 0L ? -1 : 1;
         float var24;
         int var25 = (var24 = var19 - 26.55F) == 0.0F ? 0 : (var24 < 0.0F ? -1 : 1);
         long var26 = 18954724097367L;
         int var29 = var26 < 0L ? -1 : 1;
         if (var25 >= 0 && !this.m) {
            this.m = true;
            this.C(var19, z(var7, var8, (short)var9, var19), var16 * 1.35F, var13, var20, var14);
         } else if (var19 > -1.0F) {
            long var29x = 18954724097368L;
            int var29x = var29x < 0L ? -1 : 1;
            if (var19 < 37.0F) {
               ChromaRenderer3.TypeQ var31x = new ChromaRenderer3.TypeQ(var19, z(var7, var8, (short)var9, var19), var16, var20, 0.55F, 0.45F);
               this.a(var10, (short)var11, var31x, (char)var12);
            }
         }
      }

      long var33 = 18954724097367L;
      int var33x = var33 < 0L ? -1 : 1;
   }

   private void q(float var1, long var2) {
      long var4 = 56895583511238L;
      long var6 = 18954724097368L;
      this.M.clear();
      this.v = Float.NaN;
      this.m = false;
      this.l(0.0F, var1, var6);
      float var8 = this.B(var4);
      this.M.removeIf(var2x -> {
         ChromaCodec.ensureSourceClassInitialized();
         return var1 - var2x.D > var8 + 1.4F;
      });
   }

   private void C(float var1, float var2, float var3, byte var4, float var5, long var6) {
      long var8 = 59971434499615L;
      long var10 = var8 ^ 128353385700930L;
      int var12 = (int)((var8 ^ 128353385700930L) >>> 32);
      int var13 = (int)((var8 ^ 128353385700930L) << 32 >>> 48);
      int var14 = (int)(var10 << 48 >>> 48);
      ChromaCodec.ensureSourceClassInitialized();
      ChromaRenderer3.TypeQ var15 = new ChromaRenderer3.TypeQ(var1, var2, var3, var5, 0.32F, 1.0F);
      this.a(var12, (short)var13, var15, (char)var14);
      ThreadLocalRandom var16 = ThreadLocalRandom.current();

      for (int var17 = 0; var17 < 5; var17++) {
         double var18 = var16.nextDouble() * Math.PI * 2.0;
         float var20 = var3 * (1.05F + 0.55F * var16.nextFloat());
         float var21 = var3 * (0.16F + 0.12F * var16.nextFloat());
         float var22 = var5 + 0.05F + 0.14F * var16.nextFloat();
         var15 = new ChromaRenderer3.TypeQ(var1 + (float)Math.cos(var18) * var20, var2 + (float)Math.sin(var18) * var20 * 0.8F, var21, var22, 0.22F, 1.0F);
         this.a(var12, (short)var13, var15, (char)var14);
      }
   }

   private void a(int var1, short var2, ChromaRenderer3.TypeQ var3, char var4) {
      ChromaCodec.ensureSourceClassInitialized();
      if (this.M.size() >= 80) {
         this.M.removeFirst();
      }

      this.M.add(var3);
   }

   private static float k(int var0, long var1) {
      String[] var3 = D;
      ChromaCodec.ensureSourceClassInitialized();
      int var4 = Math.clamp((long)var0, 0, 35);
      int var5 = 0;

      while (true) {
         if (var5 >= 10) {
            return 10.0F;
         }

         char var6 = var3[var5].charAt(var4);
         long var7 = 4469470408462L;
         int var10 = var7 < 0L ? -1 : 1;
         short var10x = 13256;
         long var11 = 4469470408461L;
         int var13 = var11 < 0L ? -1 : 1;
         if (var6 == 'G') {
            break;
         }

         long var13x = 4469470408461L;
         int var15 = var13x < 0L ? -1 : 1;
         var10x = 21356;
         long var15x = 4469470408461L;
         int var17 = var15x < 0L ? -1 : 1;
         if (var6 == 'D') {
            break;
         }

         long var17x = 4469470408462L;
         int var19 = var17x < 0L ? -1 : 1;
         var10x = 2986;
         long var19x = 4469470408461L;
         int var21 = var19x < 0L ? -1 : 1;
         if (var6 == 'S') {
            break;
         }

         long var21x = 4469470408461L;
         int var22 = var21x < 0L ? -1 : 1;
         if (var6 == 'W') {
            break;
         }

         var5++;
      }

      return var5;
   }

   private static float z(short var0, int var1, short var2, float var3) {
      long var4 = 79576506486559L;
      long var6 = var4 ^ 83904204955665L;
      return Math.min(k((int)Math.floor(var3 - 0.3F), var6), k((int)Math.floor(var3 + 0.3F), var6));
   }

   private static float G(short var0, float var1, long var2) {
      long var4 = 103422729372354L;
      long var6 = var4 ^ 24533430767069L;
      int var8 = (int)((var4 ^ 24533430767069L) >>> 48);
      int var9 = (int)((var4 ^ 24533430767069L) << 16 >>> 32);
      int var10 = (int)(var6 << 48 >>> 48);
      ChromaCodec.ensureSourceClassInitialized();
      boolean var11 = false;
      if (var1 > 21.45F) {
         long var12 = 103422729372353L;
         int var14 = var12 < 0L ? -1 : 1;
         if (var1 < 26.55F) {
            float var14x = (var1 - 21.45F) / 5.0999985F;
            float var15 = z((short)var8, var9, (short)var10, 21.45F);
            float var16 = z((short)var8, var9, (short)var10, 26.55F);
            return var15 + (var16 - var15) * var14x - 2.0F * (float)Math.sin(Math.PI * var14x) - 0.45F;
         }
      }

      return z((short)var8, var9, (short)var10, var1) - 0.45F - 0.12F * (float)Math.abs(Math.sin(Math.PI * var1 / 0.6F));
   }

   private void o(long var1, Canvas var3) {
      long var4 = 100847397061046L;
      long var6 = 121131722389995L;
      ChromaCodec.ensureSourceClassInitialized();
      GpuDevice var8 = RenderSystem.getDevice();
      if (this.h(var4, var8) && var8.precompilePipeline(c).isValid()) {
         float var9 = (float)Math.hypot(var3.transform().m00(), var3.transform().m01());
         float var10 = Math.max(0.5F, var3.pixelScale() * var9);
         int var11 = Math.max(1, Math.round(this.z * var10));
         int var12 = Math.max(1, Math.round(this.N * var10));
         long var13 = 1739785867955L;
         int var26 = var13 < 0L ? -1 : 1;
         if (L == null) {
            L = new class_6367("Purxiy chroma preview", var11, var12, false);
         } else {
            label70: {
               if (L.field_1482 == var11) {
                  long var16 = 1739785867955L;
                  int var18 = var16 < 0L ? -1 : 1;
                  if (L.field_1481 == var12) {
                     break label70;
                  }
               }

               L.method_1234(var11, var12);
            }
         }

         CommandEncoder var26x = var8.createCommandEncoder();
         var26x.writeToBuffer(S.slice(), this.K(var11, var12, var6));
         RenderPass var17 = var26x.createRenderPass(() -> "Purxiy chroma preview", L.method_71639(), OptionalInt.empty());

         try {
            var17.setPipeline(c);
            RenderSystem.bindDefaultUniforms(var17);
            long var18 = 1739785867954L;
            int var21 = var18 < 0L ? -1 : 1;
            var17.setUniform("PurxiyChromaPreview", S);
            var17.bindTexture("SceneSampler", y, RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR));
            var17.draw(0, 3);
            ChromaRenderer2.metric("drawn");
            long var21x = 1739785867954L;
            int var21x = var21x < 0L ? -1 : 1;
         } catch (Throwable var23) {
            if (var17 != null) {
               try {
                  var17.close();
               } catch (Throwable var22) {
                  var23.addSuppressed(var22);
               }
            }

            throw var23;
         }

         if (var17 != null) {
            var17.close();
         }

         var3.image(
            L.method_71639(),
            RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR),
            true,
            this.I,
            this.E,
            this.z,
            this.N,
            0.0F,
            1.0F,
            1.0F,
            0.0F,
            12.0F,
            -1
         );
      }
   }

   private ByteBuffer K(int var1, int var2, long var3) {
      long var5 = 86441506979202L;
      long var7 = 10109919757696L;
      long var9 = 56895583511238L;
      long var11 = 75375890897655L;
      ChromaKind var13 = ChromaKind.of((String)this.d.Z.get());
      ChromaCodec.ensureSourceClassInitialized();
      float var14 = (this.n - this.J) * 36.0F / var1;
      Std140Builder var15 = Std140Builder.intoBuffer(F.clear())
         .putVec4(var1, var2, var14, this.B)
         .putVec4(this.J, this.l, this.n, this.g)
         .putVec4(36.0F, 10.0F, this.d.f.floatValue(), 0.0F)
         .putVec4(H(var13.Z, var5), b(var11, var13.Z), f(var13.Z, var7), 0.0F)
         .putVec4(H(var13.V, var5), b(var11, var13.V), f(var13.V, var7), 0.0F)
         .putVec4(H(var13.r, var5), b(var11, var13.r), f(var13.r, var7), 0.0F);
      int var16 = Math.min(this.M.size(), 80);
      var15.putIVec4(var16, 0, 0, 0);
      float var17 = this.B;
      float var18 = this.B(var9);
      float var19 = 1.0F - ChromaRenderer2.smoothstep(13.0F, 13.9F, var17);

      for (int var20 = 0; var20 < 80; var20++) {
         long var21 = 121131722389994L;
         int var35 = var21 < 0L ? -1 : 1;
         if (var20 >= var16) {
            var15.putVec4(0.0F, 0.0F, 0.0F, 0.0F).putVec4(0.0F, 0.0F, 0.0F, 0.0F);
            long var24 = 121131722389995L;
            int var26 = var24 < 0L ? -1 : 1;
         } else {
            ChromaRenderer3.TypeQ var35x = this.M.get(var20);
            float var25 = var17 - var35x.D;
            long var26 = 121131722389994L;
            int var29 = var26 < 0L ? -1 : 1;
            float var29x = var25 <= 0.0F ? 0.0F : Math.min(var25 / var35x.i, 1.0F);
            float var30 = 1.0F - var29x;
            float var31 = var35x.y * (1.0F - var30 * var30 * var30);
            float var32 = (1.0F - ChromaRenderer2.smoothstep(var18, var18 + 1.3F, var25)) * var19;
            float var33 = var25 < 0.0F ? 0.0F : var35x.z * (1.0F - ChromaRenderer2.smoothstep(0.25F, 1.6F, var25));
            var15.putVec4(var35x.L, var35x.Y, var31, var32).putVec4(var33, 0.0F, 0.0F, 0.0F);
         }
      }

      long var34 = 121131722389994L;
      int var22 = var34 < 0L ? -1 : 1;
      return var15.get();
   }

   private void A(Canvas var1, long var2) {
      long var4 = 79576506486559L;
      byte var6 = 0;
      int var7 = 1214241126;
      int var8 = (int)(var4 << 48 >>> 48);
      byte var9 = 0;
      long var10 = 103422729372354L;
      ChromaCodec.ensureSourceClassInitialized();
      float var12 = -1.5F + 3.1F * this.B;
      if (!(var12 < -0.8F) && !(var12 > 36.8F)) {
         float var13 = this.I + (var12 / 36.0F - this.J) / (this.n - this.J) * this.z;
         float var14 = this.E + (G(var9, var12, var10) / 10.0F - this.l) / (this.g - this.l) * this.N;
         float var15 = this.z / ((this.n - this.J) * 36.0F);
         float var16 = var15 * 0.26F;
         float var17 = this.E + (z(var6, var7, (short)var8, var12) / 10.0F - this.l) / (this.g - this.l) * this.N;
         var1.softCircle(var13, var17, var16 * 1.3F, var16, BlendMode.withAlpha(-16777216, 0.18F));
         var1.softCircle(var13, var14, var16 * 2.6F, var16 * 2.0F, BlendMode.withAlpha(Theme.tint(-4222985), 0.45F));
         var1.circle(var13, var14, var16, BlendMode.lerp(Theme.tint(-4222985), -1, 0.6F));
      }
   }

   private boolean h(long var1, GpuDevice var3) {
      long var4 = 22600067498166L;
      ChromaCodec.ensureSourceClassInitialized();
      if (S == null) {
         S = var3.createBuffer(ClientServices.label(() -> "Purxiy chroma preview"), 136, 2672L);
         F = MemoryUtil.memAlloc(2672);
      }

      if (Z != null) {
         return true;
      } else if (e) {
         return false;
      } else {
         try {
            class_1011 var6 = U(var4);

            boolean var7;
            try {
               Z = var3.createTexture(() -> "Purxiy chroma preview landscape", 5, TextureFormat.RGBA8, var6.method_4307(), var6.method_4323(), 1, 1);
               y = var3.createTextureView(Z);
               var3.createCommandEncoder().writeToTexture(Z, var6);
               var7 = true;
            } catch (Throwable var111) {
               if (var6 != null) {
                  try {
                     var6.close();
                  } catch (Throwable var10) {
                     var111.addSuppressed(var10);
                  }
               }

               throw var111;
            }

            if (var6 != null) {
               var6.close();
            }

            return var7;
         } catch (Exception var12) {
            e = true;
            ClientServices.LOGGER.warn("Purxiy could not make Chroma's preview landscape", var12);
            return false;
         }
      }
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private static class_1011 U(long var0) throws Exception {
      long var2 = 19541830050564L;
      long var4 = 57694361628610L;
      class_1011 var6 = new class_1011(576, 160, true);
      class_1011 var7 = F("grass_block_side", var4, 0, false);
      class_1011 var8 = F("grass_block_side_overlay", var4, -8798118, false);
      I(var7, var2, var8, 0, 0);
      var8.close();
      class_1011 var9 = F("water_still", var4, -12618012, true);
      class_1011[] var10 = new class_1011[128];
      var10[71] = var7;
      var10[68] = F("dirt", var4, 0, false);
      ChromaCodec.ensureSourceClassInitialized();
      var10[83] = F("stone", var4, 0, false);
      var10[76] = F("oak_log", var4, 0, false);
      var10[79] = F("oak_leaves", var4, -10899920, false);
      var10[75] = F("cherry_log", var4, 0, false);
      var10[67] = F("cherry_leaves", var4, 0, false);
      var10[87] = var9;
      var10[112] = F("poppy", var4, 0, false);
      var10[100] = F("dandelion", var4, 0, false);
      var10[99] = F("cornflower", var4, 0, false);
      var10[97] = F("allium", var4, 0, false);
      var10[116] = F("pink_tulip", var4, 0, false);
      var10[98] = F("azure_bluet", var4, 0, false);
      var10[103] = F("short_grass", var4, -8798118, false);
      boolean var11 = false;
      boolean var34 = false;
      boolean var34x = false /* VF: Semaphore variable */;

      try {
         var34x = true;
         var34 = true;
         var11 = true;

         for (int var37 = 0; var37 < 10; var37++) {
            for (int var13x = 0; var13x < 36; var13x++) {
               long var14 = 22600067498166L;
               int var42 = var14 < 0L ? -1 : 1;
               class_1011 var17x = var10[D[var37].charAt(var13x)];
               long var18 = 22600067498165L;
               int var53 = var18 < 0L ? -1 : 1;
               long var21 = 22600067498166L;
               int var22 = var21 < 0L ? -1 : 1;
               if (var17x != null) {
                  I(var6, var2, var17x, var13x * 16, var37 * 16);
               }
            }
         }

         var11 = false;
         var34 = false;
         var34x = false;
      } finally {
         if (var34x) {
            if (var34 && var11) {
               for (class_1011 var27 : var10) {
                  long var28 = 22600067498166L;
                  int var31 = var28 < 0L ? -1 : 1;
                  long var31x = 22600067498165L;
                  int var32 = var31x < 0L ? -1 : 1;
                  if (var27 != null) {
                     var27.close();
                  }
               }
            }
         }
      }

      if (var34 && var11) {
         for (class_1011 var27x : var10) {
            long var28 = 22600067498166L;
            int var19 = var28 < 0L ? -1 : 1;
            long var31 = 22600067498165L;
            int var52 = var31 < 0L ? -1 : 1;
            if (var27x != null) {
               var27x.close();
            }
         }
      }

      if (var11) {
         for (class_1011 var46 : var10) {
            long var49 = 22600067498166L;
            int var62 = var49 < 0L ? -1 : 1;
            long var51 = 22600067498165L;
            int var69 = var51 < 0L ? -1 : 1;
            if (var46 != null) {
               var46.close();
            }
         }
      }

      for (class_1011 var15 : var10) {
         long var16 = 22600067498166L;
         int var64 = var16 < 0L ? -1 : 1;
         long var19 = 22600067498166L;
         int var70 = var19 < 0L ? -1 : 1;
         long var52 = 22600067498165L;
         int var23 = var52 < 0L ? -1 : 1;
         if (var15 != null) {
            var15.close();
         }
      }

      long var37 = 22600067498165L;
      int var46x = var37 < 0L ? -1 : 1;

      for (int var44 = 0; var44 < var6.method_4323(); var44++) {
         for (int var47 = 0; var47 < var6.method_4307(); var47++) {
            int var42 = var6.method_61940(var47, var44);
            long var50 = 22600067498166L;
            int var67 = var50 < 0L ? -1 : 1;
            float var53 = (var42 >>> 24) / 255.0F;
            var6.method_61941(
               var47,
               var44,
               var42 & 0xFF000000
                  | Math.round((var42 >> 16 & 0xFF) * var53) << 16
                  | Math.round((var42 >> 8 & 0xFF) * var53) << 8
                  | Math.round((var42 & 0xFF) * var53)
            );
         }

         long var48 = 22600067498166L;
         int var60 = var48 < 0L ? -1 : 1;
      }

      long var45 = 22600067498166L;
      int var58 = var45 < 0L ? -1 : 1;
      return var6;
   }

   private static class_1011 F(String var0, long var1, int var3, boolean var4) throws Exception {
      ChromaCodec.ensureSourceClassInitialized();
      class_310 var5 = class_310.method_1551();

      class_1011 var21;
      try (InputStream var6 = var5.method_1478().open(class_2960.method_60656("textures/block/" + var0 + ".png"))) {
         class_1011 var7 = class_1011.method_4309(var6);

         try {
            int var8 = var7.method_4307();
            class_1011 var9 = new class_1011(16, 16, true);
            int var10 = 0;

            while (var10 < 16) {
               for (int var11 = 0; var11 < 16; var11++) {
                  long var12 = 57694361628610L;
                  int var15 = var12 < 0L ? -1 : 1;
                  var15 = var7.method_61940(var11 * var8 / 16, var10 * var8 / 16);
                  if (var3 != 0) {
                     var15 = var15 & 0xFF000000
                        | Math.round((var15 >> 16 & 0xFF) * (var3 >> 16 & 0xFF) / 255.0F) << 16
                        | Math.round((var15 >> 8 & 0xFF) * (var3 >> 8 & 0xFF) / 255.0F) << 8
                        | Math.round((var15 & 0xFF) * (var3 & 0xFF) / 255.0F);
                  }

                  var9.method_61941(var11, var10, var4 ? var15 | 0xFF000000 : var15);
               }

               var10++;
               long var20 = 57694361628610L;
               int var14 = var20 < 0L ? -1 : 1;
            }

            var21 = var9;
         } catch (Throwable var18) {
            if (var7 != null) {
               try {
                  var7.close();
               } catch (Throwable var17) {
                  var18.addSuppressed(var17);
               }
            }

            throw var18;
         }

         if (var7 != null) {
            var7.close();
         }
      }

      return var21;
   }

   private static void I(class_1011 var0, long var1, class_1011 var3, int var4, int var5) {
      ChromaCodec.ensureSourceClassInitialized();
      int var6 = 0;

      while (var6 < 16) {
         for (int var7 = 0; var7 < 16; var7++) {
            int var8 = var3.method_61940(var7, var6);
            int var9 = var8 >>> 24;
            long var10 = 19541830050563L;
            int var23 = var10 < 0L ? -1 : 1;
            if (var9 == 0) {
               long var13 = 19541830050563L;
               int var15 = var13 < 0L ? -1 : 1;
            } else {
               var23 = var0.method_61940(var4 + var7, var5 + var6);
               int var14 = var4 + var7;
               int var15 = var5 + var6;
               long var16 = 19541830050563L;
               int var19 = var16 < 0L ? -1 : 1;
               long var19x = 19541830050563L;
               int var19x = var19x < 0L ? -1 : 1;
               var0.method_61941(
                  var14,
                  var15,
                  var9 == 255 ? var8 : BlendMode.lerp(var23 | 0xFF000000, var8 | 0xFF000000, var9 / 255.0F) & 16777215 | Math.max(var23 >>> 24, var9) << 24
               );
            }
         }

         var6++;
         long var21 = 19541830050563L;
         int var221 = var21 < 0L ? -1 : 1;
      }

      long var22 = 19541830050564L;
      int var231 = var22 < 0L ? -1 : 1;
   }

   private static float H(int var0, long var1) {
      return (var0 >>> 16 & 0xFF) / 255.0F;
   }

   private static float b(long var0, int var2) {
      return (var2 >>> 8 & 0xFF) / 255.0F;
   }

   private static float f(int var0, long var1) {
      return (var0 & 0xFF) / 255.0F;
   }

   private record TypeQ(float L, float Y, float y, float D, float i, float z) {
      public float x() {
         return this.L;
      }

      public float y() {
         return this.Y;
      }

      public float radius() {
         return this.y;
      }

      public float born() {
         return this.D;
      }

      public float grow() {
         return this.i;
      }

      public float wet() {
         return this.z;
      }

      public float floatState57() {
         return this.L;
      }

      public float floatState26() {
         return this.Y;
      }

      public float floatState24() {
         return this.D;
      }

      public float floatState12() {
         return this.i;
      }

      public float floatState19() {
         return this.z;
      }
   }
}
