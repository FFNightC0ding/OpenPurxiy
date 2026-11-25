package dev.purxiy.modules.nametags;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.client.ClientRegistry5;
import dev.purxiy.loader.fabric.client.ClientRegistry5.Listener;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.TrueTypeFont;
import dev.purxiy.ui.Icons;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_11719;
import net.minecraft.class_11767;
import net.minecraft.class_12137;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_4588;
import net.minecraft.class_5224;
import net.minecraft.class_5251;
import net.minecraft.class_5481;
import net.minecraft.class_11719.class_11721;
import net.minecraft.class_11767.class_12238;
import net.minecraft.class_327.class_11464;
import net.minecraft.class_327.class_11465;
import org.joml.Matrix4f;

public final class NameTagsRenderer3 {
   private static final String R = "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢ";
   private static final float r = 0.8F;
   private static final class_2960 Y = class_2960.method_60656("uniform");
   private static final Matrix4f v = new Matrix4f();
   private static final int u = 15728880;
   public final float N;
   private final float C;
   private final List<NameTagsRenderer3.TypeI> n;
   public final float K;
   public final boolean B;
   public static boolean Z;
   private static final String[] b = new String[3];
   private static final long[] c = new long[]{3581424850157792815L, 7716784156478834030L, -7085012437320516495L, 162662233816545128L};
   private static final Integer[] d = new Integer[4];

   private NameTagsRenderer3(float var1, float var2, List<NameTagsRenderer3.TypeI> var3, float var4, boolean var5) {
      this.N = var1;
      this.C = var2;
      this.n = var3;
      this.K = var4;
      this.B = var5;
   }

   public static NameTagsRenderer3 of(class_2561 var0, float var1, TrueTypeFont var2, TrueTypeFont var3, int var4, boolean var5, boolean var6) {
      return of(var0.method_30937(), var1, var2, var3, var4, var5, var6);
   }

   public static NameTagsRenderer3 of(class_5481 var0, float var1, TrueTypeFont var2, TrueTypeFont var3, int var4, boolean var5, boolean var6) {
      long var7 = 37557378205288L;
      long var9 = var7 ^ 138933649241222L;
      int var11 = (int)((var7 ^ 138933649241222L) >>> 32);
      int var12 = (int)((var7 ^ 138933649241222L) << 32 >>> 48);
      int var13 = (int)(var9 << 48 >>> 48);
      NameTagsRenderer3.TypeG var14 = new NameTagsRenderer3.TypeG(var11, var12, (char)var13);
      ClientRegistry5.sequence(var0).accept(var14);
      return new NameTagsRenderer3.TypeE(var14, var1, var2, var3, var4, var5, var6).build();
   }

   public NameTagsRenderer3 truncated(float var1, TrueTypeFont var2) {
      if (this.K <= var1) {
         return this;
      } else {
         String var3 = "…";
         float var4 = Math.max(0.0F, var1 - var2.measure(var3, this.N, 0.0F));
         ArrayList var5 = new ArrayList();
         float var6 = 0.0F;
         int var7 = -1;

         for (NameTagsRenderer3.TypeI var9 : this.n) {
            if (!(var9.M <= var4)) {
               if (var9.m != null) {
                  float var10 = this.N * var9.A;
                  int var11 = var9.Q.length();

                  while (var11 > 0 && var9.K + var9.m.measure(var9.Q.substring(0, var11), var10, 0.0F) > var4) {
                     var11 = var9.Q.offsetByCodePoints(var11, -1);
                  }

                  String var12 = var9.Q.substring(0, var11).stripTrailing();
                  if (!var12.isEmpty()) {
                     var6 = var9.K + var9.m.measure(var12, var10, 0.0F);
                     var5.add(NameTagsRenderer3.TypeI.inter(var9.m, var12, var9.K, var6, var9.A, var9.t, var9.i, var9.D));
                  }

                  var7 = var9.t;
               } else if (var9.U != null) {
                  ArrayList var14 = new ArrayList();

                  for (NameTagsRenderer3.TypeD var16 : var9.U) {
                     if (var16.e <= var4) {
                        var14.add(var16);
                        var6 = Math.max(var6, var16.e);
                     }
                  }

                  if (!var14.isEmpty()) {
                     var5.add(NameTagsRenderer3.TypeI.glyphs(var14, var9.K, var6));
                  }
               }
               break;
            }

            var5.add(var9);
            var6 = var9.M;
            var7 = var9.t;
         }

         float var13 = var6 + var2.measure(var3, this.N, 0.0F);
         var5.add(NameTagsRenderer3.TypeI.inter(var2, var3, var6, var13, 1.0F, var7, false, false));
         return new NameTagsRenderer3(this.N, this.C, var5, var13, this.B);
      }
   }

   private float S() {
      return this.N * this.C / 7.0F;
   }

   public boolean stale() {
      for (NameTagsRenderer3.TypeI var2 : this.n) {
         if (var2.U != null) {
            for (NameTagsRenderer3.TypeD var4 : var2.U) {
               if (var4.T.isClosed()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public void draw(Canvas var1, float var2, float var3) {
      long var4 = 97851064835145L;
      long var6 = var4 ^ 29798039230308L;
      Translations.pause();

      try {
         this.f(var1, var2, var3, var6);
      } finally {
         Translations.resume();
      }
   }

   private void f(Canvas var1, float var2, float var3, long var4) {
      TagPainter.ensureSourceClassInitialized();
      float var6 = var3 + this.C * this.N * 0.5F;
      class_12137 var7 = null;

      for (NameTagsRenderer3.TypeI var9 : this.n) {
         long var10 = 74660968319789L;
         int var22 = var10 < 0L ? -1 : 1;
         if (var9.E != 0) {
            NameTagsSupport.camera(var1, var2 + var9.K, var6, this.S(), var9.t);
            long var21 = 74660968319788L;
            int var26 = var21 < 0L ? -1 : 1;
         } else if (var9.m != null) {
            float var20 = this.N * var9.A;
            var1.text(var9.m, var9.Q, var2 + var9.K, var6, var20, var9.t, FontStyle.LEFT, FontWeight.BASELINE);
            float var23 = var9.M - var9.K;
            float var24 = Math.max(0.6F, this.N * 0.075F);
            if (var9.i) {
               var1.rect(var2 + var9.K, var6 + this.N * 0.13F, var23, var24, var9.t);
            }

            long var16 = 74660968319789L;
            int var29 = var16 < 0L ? -1 : 1;
            if (var9.D) {
               var1.rect(var2 + var9.K, var6 - this.C * this.N * 0.45F - var24 / 2.0F, var23, var24, var9.t);
            }

            long var25 = 74660968319789L;
            int var31 = var25 < 0L ? -1 : 1;
         } else {
            if (var7 == null) {
               var7 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
            }

            for (NameTagsRenderer3.TypeD var14 : var9.U) {
               long var15 = 74660968319788L;
               int var17 = var15 < 0L ? -1 : 1;
               boolean var17x = var14.T.isClosed();
               long var18 = 74660968319788L;
               int var19 = var18 < 0L ? -1 : 1;
               if (!var17x) {
                  var1.image(
                     var14.T,
                     var7,
                     false,
                     var2 + var14.S,
                     var6 + var14.w,
                     var14.e - var14.S,
                     var14.U - var14.w,
                     var14.E,
                     var14.o,
                     var14.G,
                     var14.K,
                     0.0F,
                     var14.m
                  );
               }
            }
         }

         long var22x = 74660968319789L;
         int var27 = var22x < 0L ? -1 : 1;
      }
   }

   public void drawShadow(Canvas var1, float var2, float var3, float var4) {
      long var5 = 7446725757569L;
      long var7 = var5 ^ 121395803127256L;
      Translations.pause();

      try {
         this.X(var1, var7, var2, var3, var4);
      } finally {
         Translations.resume();
      }
   }

   private void X(Canvas var1, long var2, float var4, float var5, float var6) {
      TagPainter.ensureSourceClassInitialized();
      float var7 = var5 + this.C * this.N * 0.5F;
      int var8 = BlendMode.withAlpha(-16777216, 0.75F * var6);
      class_12137 var9 = null;

      for (NameTagsRenderer3.TypeI var11 : this.n) {
         if (var11.E != 0) {
            float var22 = this.N * 0.08F;
            long var24 = 115094298654553L;
            int var27 = var24 < 0L ? -1 : 1;
            NameTagsSupport.cameraShadow(var1, var4 + var11.K + var22, var7 + var22, this.S(), BlendMode.withAlpha(-16777216, 0.6F * var6));
         } else if (var11.m != null) {
            var1.textGlow(
               var11.m, var11.Q, var4 + var11.K, var7 + this.N * 0.07F, this.N * var11.A, var8, FontStyle.LEFT, FontWeight.BASELINE, 0.0F, 0.6F, 1.6F
            );
            long var21 = 115094298654552L;
            int var25 = var21 < 0L ? -1 : 1;
         } else {
            if (var9 == null) {
               var9 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
            }

            float var12 = this.N * 0.08F;

            for (NameTagsRenderer3.TypeD var14 : var11.U) {
               long var15 = 115094298654552L;
               int var18 = var15 < 0L ? -1 : 1;
               boolean var18x = var14.T.isClosed();
               long var19 = 115094298654553L;
               int var20 = var19 < 0L ? -1 : 1;
               if (!var18x) {
                  var1.image(
                     var14.T,
                     var9,
                     false,
                     var4 + var14.S + var12,
                     var7 + var14.w + var12,
                     var14.e - var14.S,
                     var14.U - var14.w,
                     var14.E,
                     var14.o,
                     var14.G,
                     var14.K,
                     0.0F,
                     BlendMode.withAlpha(-16777216, 0.6F * var6 * BlendMode.alpha(var14.m) / 255.0F)
                  );
               }
            }
         }

         long var23 = 115094298654552L;
         int var26 = var23 < 0L ? -1 : 1;
      }
   }

   private static boolean W(class_11719 var0, long var1) {
      TagPainter.ensureSourceClassInitialized();
      return var0.equals(class_11719.field_61972) || var0 instanceof class_11721 var3 && var3.comp_4590().equals(Y);
   }

   private static boolean H(int var0, long var1) {
      TagPainter.ensureSourceClassInitialized();
      long var3 = 112605773601006L;
      int var6 = var3 < 0L ? -1 : 1;
      long var6x = 112605773601007L;
      int var7 = var6x < 0L ? -1 : 1;
      return var0 >= 128 && "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢ".indexOf(var0) >= 0;
   }

   private static boolean B(long var0, class_2583 var2, class_2583 var3) {
      TagPainter.ensureSourceClassInitialized();
      boolean var4 = var2.method_10984();
      long var5 = 13513633123984L;
      int var8 = var5 < 0L ? -1 : 1;
      if (var4 == var3.method_10984()) {
         long var8x = 13513633123985L;
         int var9 = var8x < 0L ? -1 : 1;
         if (var2.method_10965() == var3.method_10965()
            && var2.method_10986() == var3.method_10986()
            && Objects.equals(var2.method_10973(), var3.method_10973())) {
            return true;
         }
      }

      return false;
   }

   private static final class TypeD {
      final GpuTextureView T;
      float S;
      final float w;
      float e;
      final float U;
      final float E;
      final float o;
      final float G;
      final float K;
      final int m;

      TypeD(GpuTextureView var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, int var10) {
         this.T = var1;
         this.S = var2;
         this.w = var3;
         this.e = var4;
         this.U = var5;
         this.E = var6;
         this.o = var7;
         this.G = var8;
         this.K = var9;
         this.m = var10;
      }
   }

   private static final class TypeE {
      private final NameTagsRenderer3.TypeG R;
      private final float f;
      private final TrueTypeFont m;
      private final TrueTypeFont D;
      private final int S;
      private final boolean r;
      private final boolean O;
      private final float v;
      private final List<NameTagsRenderer3.TypeI> z = new ArrayList<>();
      private boolean G;
      private static final String a = "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢ";
      private static final long b = 8705823770385842176L;

      TypeE(NameTagsRenderer3.TypeG var1, float var2, TrueTypeFont var3, TrueTypeFont var4, int var5, boolean var6, boolean var7) {
         this.R = var1;
         this.f = var2;
         this.m = var3;
         this.D = var4;
         this.S = var5;
         this.r = var6;
         this.O = var7;
         this.v = var2 * var3.capHeight / 7.0F;
      }

      NameTagsRenderer3 build() {
         long var1 = 93925303993019L;
         long var3 = var1 ^ 85987429261067L;
         long var5 = var1 ^ 137787612842270L;
         long var7 = var1 ^ 98023286508586L;
         long var9 = var1 ^ 111813240903576L;
         long var11 = var1 ^ 63409625625659L;
         long var13 = var1 ^ 56103601953364L;
         NameTagsRenderer3.TypeG var15 = this.R;
         float var16 = 0.0F;
         int var17 = 0;

         while (var17 < var15.J) {
            if (this.P(var17, var5)) {
               float var28 = 8.0F * this.v;
               this.z.add(NameTagsRenderer3.TypeI.symbol(var15.U[var17], var16, var16 + var28, this.S(var9, var15.q[var17])));
               var16 += var28;
               var17++;
            } else if (this.d(var11, var17)) {
               int var27 = var17 + 1;

               while (var27 < var15.J && this.d(var11, var27) && !this.P(var27, var5)) {
                  var27++;
               }

               var16 = this.V(var17, var3, var27, var16);
               var17 = var27;
            } else {
               class_2583 var18 = var15.q[var17];
               boolean var19 = NameTagsRenderer3.H(var15.U[var17], var13);
               TrueTypeFont var20 = var18.method_10984() ? this.D : this.m;
               int var21 = this.S(var9, var18);
               StringBuilder var22 = new StringBuilder();

               int var23;
               for (var23 = var17;
                  var23 < var15.J
                     && !this.d(var11, var23)
                     && NameTagsRenderer3.H(var15.U[var23], var13) == var19
                     && NameTagsRenderer3.B(var7, var18, var15.q[var23]);
                  var23++
               ) {
                  int var24 = var15.U[var23];
                  var22.appendCodePoint(var19 ? 65 + "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢ".indexOf(var24) : var24);
               }

               float var29 = var19 ? 0.8F : 1.0F;
               String var25 = var22.toString();
               float var26 = var20.measure(var25, this.f * var29, 0.0F);
               this.z.add(NameTagsRenderer3.TypeI.inter(var20, var25, var16, var16 + var26, var29, var21, var18.method_10965(), var18.method_10986()));
               var16 += var26;
               var17 = var23;
            }
         }

         return new NameTagsRenderer3(this.f, this.m.capHeight, this.z, var16, this.G);
      }

      private boolean P(int var1, long var2) {
         TagPainter.ensureSourceClassInitialized();
         return !this.O && NameTagsSupport.drawn(this.R.q[var1], this.R.U[var1]);
      }

      private boolean d(long var1, int var3) {
         long var4 = 53804514229535L;
         long var6 = 112605773601007L;
         class_2583 var8 = this.R.q[var3];
         TagPainter.ensureSourceClassInitialized();
         int var9 = this.R.U[var3];
         if (!this.O && !var8.method_10987() && NameTagsRenderer3.W(var8.method_27708(), var4)) {
            if (!Character.isWhitespace(var9)) {
               long var10 = 119602026952320L;
               int var13 = var10 < 0L ? -1 : 1;
               if (!NameTagsRenderer3.H(var9, var6)) {
                  TrueTypeFont var13x = var8.method_10984() ? this.D : this.m;
                  return !var13x.hasGlyph(var9);
               }
            }

            return false;
         } else {
            return true;
         }
      }

      private int S(long var1, class_2583 var3) {
         TagPainter.ensureSourceClassInitialized();
         class_5251 var4 = var3.method_10973();
         return this.r && var4 != null ? NameTagsSupport2.readable(0xFF000000 | var4.method_27716(), this.S) : this.S;
      }

      private float V(int var1, long var2, int var4, float var5) {
         long var6 = 122114768694418L;
         byte var8 = 0;
         short var9 = 28432;
         int var10 = (int)(var6 << 32 >>> 32);
         NameTagsRenderer3.TypeG var11 = this.R;
         this.G = this.G | C((char)var8, var11, (char)var9, var1, var4, var10);
         TagPainter.ensureSourceClassInitialized();
         NameTagsRenderer3.TypeQ var12 = new NameTagsRenderer3.TypeQ(var11, var1, var4);
         class_327 var13 = class_310.method_1551().field_1772;
         class_11465 var14 = var13.method_71795(var12, 0.0F, 0.0F, -1, false, false, 0);
         final NameTagsRenderer3.TypeM var15 = new NameTagsRenderer3.TypeM(this.v, this.r ? -1 : this.S);
         var14.method_71801(new class_11464() {
            public void method_71797(class_12238 var1) {
               var15.take(var1);
            }

            public void method_71798(class_11767 var1) {
               var15.take(var1);
            }
         });
         float var16 = var13.method_27527().method_30875(var12) * this.v;
         boolean var17 = var15.o.isEmpty();
         long var18 = 30065614937519L;
         int var20 = var18 < 0L ? -1 : 1;
         if (!var17) {
            this.z.add(NameTagsRenderer3.TypeI.glyphs(var15.o, var5, var5 + var16));

            for (NameTagsRenderer3.TypeD var22 : var15.o) {
               var22.S += var5;
               var22.e += var5;
            }
         }

         return var5 + var16;
      }

      private static boolean C(char var0, NameTagsRenderer3.TypeG var1, char var2, int var3, int var4, int var5) {
         TagPainter.ensureSourceClassInitialized();

         for (int var6 = var3; var6 < var4; var6++) {
            boolean var7 = var1.q[var6].method_10987();
            if (var7) {
               return true;
            }
         }

         return false;
      }
   }

   private static final class TypeG implements class_5224 {
      int[] U = new int[32];
      class_2583[] q = new class_2583[32];
      int J;
      private static final long[] a = new long[]{5275067907381447094L, 4252792860573738604L};
      private static final Integer[] b = new Integer[2];

      private TypeG(int var1, int var2, char var3) {
      }

      public boolean accept(int var1, class_2583 var2, int var3) {
         if (!Character.isISOControl(var3) && Character.getType(var3) != 16) {
            if (this.J == this.U.length) {
               this.U = Arrays.copyOf(this.U, this.J * 2);
               this.q = Arrays.copyOf(this.q, this.J * 2);
            }

            this.U[this.J] = var3;
            this.q[this.J++] = var2;
            return true;
         } else {
            return true;
         }
      }
   }

   private static final class TypeI {
      final TrueTypeFont m;
      final String Q;
      final float K;
      final float A;
      final int t;
      final boolean i;
      final boolean D;
      final List<NameTagsRenderer3.TypeD> U;
      final int E;
      final float M;

      private TypeI(
         TrueTypeFont var1,
         String var2,
         float var3,
         float var4,
         float var5,
         int var6,
         boolean var7,
         boolean var8,
         List<NameTagsRenderer3.TypeD> var9,
         int var10
      ) {
         this.m = var1;
         this.Q = var2;
         this.K = var3;
         this.M = var4;
         this.A = var5;
         this.t = var6;
         this.i = var7;
         this.D = var8;
         this.U = var9;
         this.E = var10;
      }

      static NameTagsRenderer3.TypeI inter(TrueTypeFont var0, String var1, float var2, float var3, float var4, int var5, boolean var6, boolean var7) {
         return new NameTagsRenderer3.TypeI(var0, var1, var2, var3, var4, var5, var6, var7, null, 0);
      }

      static NameTagsRenderer3.TypeI glyphs(List<NameTagsRenderer3.TypeD> var0, float var1, float var2) {
         return new NameTagsRenderer3.TypeI(null, "", var1, var2, 1.0F, var0.isEmpty() ? -1 : var0.getFirst().m, false, false, var0, 0);
      }

      static NameTagsRenderer3.TypeI symbol(int var0, float var1, float var2, int var3) {
         return new NameTagsRenderer3.TypeI(null, "", var1, var2, 1.0F, var3, false, false, null, var0);
      }
   }

   private static final class TypeM implements class_4588 {
      final List<NameTagsRenderer3.TypeD> o = new ArrayList<>();
      private final float K;
      private final int z;
      private final float[] g = new float[4];
      private final float[] Z = new float[4];
      private final float[] p = new float[4];
      private final float[] C = new float[4];
      private final int[] t = new int[4];
      private int q;
      private GpuTextureView E;

      TypeM(float var1, int var2) {
         this.K = var1;
         this.z = var2;
      }

      void take(class_11767 var1) {
         long var2 = 44080654711038L;
         long var4 = var2 ^ 67735835002053L;
         int var6 = (int)((var2 ^ 67735835002053L) >>> 32);
         int var7 = (int)((var2 ^ 67735835002053L) << 32 >>> 56);
         int var8 = (int)(var4 << 40 >>> 40);
         this.E = var1.comp_4629();
         this.q = 0;
         var1.method_73403(NameTagsRenderer3.v, this, 15728880, false);
         this.T(var6, (byte)var7, var8);
         this.E = null;
      }

      private void T(int var1, byte var2, int var3) {
         TagPainter.ensureSourceClassInitialized();
         if (this.q == 4) {
            short var4 = -87;
            if (this.E != null) {
               int var5 = 0;
               int var6 = 0;

               for (int var7 = 1; var7 < 4; var7++) {
                  float var8 = this.g[var7];
                  var8 += this.Z[var7];
                  var4 = 5517;
                  if (var8 < this.g[var5] + this.Z[var5]) {
                     var5 = var7;
                  }

                  if (this.g[var7] + this.Z[var7] > this.g[var6] + this.Z[var6]) {
                     var6 = var7;
                  }
               }

               float var14 = Math.min(Math.min(this.g[0], this.g[1]), Math.min(this.g[2], this.g[3]));
               float var16 = Math.max(Math.max(this.g[0], this.g[1]), Math.max(this.g[2], this.g[3]));
               float var9 = Math.min(Math.min(this.Z[0], this.Z[1]), Math.min(this.Z[2], this.Z[3]));
               float var10 = Math.max(Math.max(this.Z[0], this.Z[1]), Math.max(this.Z[2], this.Z[3]));
               int var11 = this.t[var5];
               int var12 = this.z == -1 ? NameTagsSupport2.readable(var11, Icons.TEXT) : BlendMode.withAlpha(this.z, BlendMode.alpha(var11) / 255.0F);
               this.o
                  .add(
                     new NameTagsRenderer3.TypeD(
                        this.E,
                        var14 * this.K,
                        (var9 - 7.0F) * this.K,
                        var16 * this.K,
                        (var10 - 7.0F) * this.K,
                        this.p[var5],
                        this.C[var5],
                        this.p[var6],
                        this.C[var6],
                        var12
                     )
                  );
            }
         }

         this.q = 0;
      }

      public class_4588 method_22912(float var1, float var2, float var3) {
         long var4 = 102636573159906L;
         long var6 = var4 ^ 80081172712921L;
         int var8 = (int)((var4 ^ 80081172712921L) >>> 32);
         int var9 = (int)((var4 ^ 80081172712921L) << 32 >>> 56);
         int var10 = (int)(var6 << 40 >>> 40);
         if (this.q == 4) {
            this.T(var8, (byte)var9, var10);
         }

         this.g[this.q] = var1;
         this.Z[this.q] = var2;
         this.t[this.q] = -1;
         this.q++;
         return this;
      }

      public class_4588 method_1336(int var1, int var2, int var3, int var4) {
         return this.method_39415(BlendMode.argb(var4, var1, var2, var3));
      }

      public class_4588 method_39415(int var1) {
         if (this.q > 0) {
            this.t[this.q - 1] = var1;
         }

         return this;
      }

      public class_4588 method_22913(float var1, float var2) {
         if (this.q > 0) {
            this.p[this.q - 1] = var1;
            this.C[this.q - 1] = var2;
         }

         return this;
      }

      public class_4588 method_60796(int var1, int var2) {
         return this;
      }

      public class_4588 method_22921(int var1, int var2) {
         return this;
      }

      public class_4588 method_22914(float var1, float var2, float var3) {
         return this;
      }

      public class_4588 method_75298(float var1) {
         return this;
      }
   }

   private record TypeQ(NameTagsRenderer3.TypeG R, int a, int m) implements class_5481, Listener {
      public boolean accept(class_5224 var1) {
         for (int var2 = this.a; var2 < this.m; var2++) {
            if (!var1.accept(var2 - this.a, this.R.q[var2], this.R.U[var2])) {
               return false;
            }
         }

         return true;
      }

      public NameTagsRenderer3.TypeG letters() {
         return this.R;
      }

      public int from() {
         return this.a;
      }

      public int to() {
         return this.m;
      }

      public NameTagsRenderer3.TypeG nameTagTextLayout_StyledCodePointCollectorState1() {
         return this.R;
      }

      public int integerState26() {
         return this.a;
      }

      public int integerState17() {
         return this.m;
      }
   }
}
