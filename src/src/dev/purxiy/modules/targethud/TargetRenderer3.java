// Sourced By DevFFNightmare
package dev.purxiy.modules.targethud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.client.ClientRegistry5;
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

public final class TargetRenderer3 {
   private static final String l = "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢ";
   private static final float q = 0.8F;
   private static final class_2960 T = class_2960.method_60656("uniform");
   private static final Matrix4f j = new Matrix4f();
   private static final int I = 15728880;
   public final float L;
   private final float O;
   private final List<TargetRenderer3.TypeD> U;
   public final float u;
   public final boolean E;
   public static int h = 0;

   private static final String[] b = new String[3];
   private static final long[] c = new long[]{8984670873195050212L, -7215605810744066191L, 2749549596940415411L, -759171400443949317L};
   private static final Integer[] d = new Integer[4];

   private TargetRenderer3(float var1, float var2, List<TargetRenderer3.TypeD> var3, float var4, boolean var5) {
      this.L = var1;
      this.O = var2;
      this.U = var3;
      this.u = var4;
      this.E = var5;
   }

   public static TargetRenderer3 of(class_2561 var0, float var1, TrueTypeFont var2, TrueTypeFont var3, int var4, boolean var5, boolean var6) {
      return of(var0.method_30937(), var1, var2, var3, var4, var5, var6);
   }

   public static TargetRenderer3 of(class_5481 var0, float var1, TrueTypeFont var2, TrueTypeFont var3, int var4, boolean var5, boolean var6) {
      long var7 = 9505166548255L;
      long var9 = var7 ^ 68107886744473L;
      TargetRenderer3.TypeM var11 = new TargetRenderer3.TypeM(var9);
      ClientRegistry5.sequence(var0).accept(var11);
      return new TargetRenderer3.TypeC(var11, var1, var2, var3, var4, var5, var6).build();
   }

   public TargetRenderer3 truncated(float var1, TrueTypeFont var2) {
      if (this.u <= var1) {
         return this;
      } else {
         String var3 = "…";
         float var4 = Math.max(0.0F, var1 - var2.measure(var3, this.L, 0.0F));
         ArrayList var5 = new ArrayList();
         float var6 = 0.0F;
         int var7 = -1;

         for (TargetRenderer3.TypeD var9 : this.U) {
            if (!(var9.F <= var4)) {
               if (var9.d != null) {
                  float var10 = this.L * var9.O;
                  int var11 = var9.H.length();

                  while (var11 > 0 && var9.K + var9.d.measure(var9.H.substring(0, var11), var10, 0.0F) > var4) {
                     var11 = var9.H.offsetByCodePoints(var11, -1);
                  }

                  String var12 = var9.H.substring(0, var11).stripTrailing();
                  if (!var12.isEmpty()) {
                     var6 = var9.K + var9.d.measure(var12, var10, 0.0F);
                     var5.add(TargetRenderer3.TypeD.inter(var9.d, var12, var9.K, var6, var9.O, var9.U, var9.v, var9.M));
                  }

                  var7 = var9.U;
               } else if (var9.k != null) {
                  ArrayList var14 = new ArrayList();

                  for (TargetRenderer3.TypeO var16 : var9.k) {
                     if (var16.a <= var4) {
                        var14.add(var16);
                        var6 = Math.max(var6, var16.a);
                     }
                  }

                  if (!var14.isEmpty()) {
                     var5.add(TargetRenderer3.TypeD.glyphs(var14, var9.K, var6));
                  }
               }
               break;
            }

            var5.add(var9);
            var6 = var9.F;
            var7 = var9.U;
         }

         float var13 = var6 + var2.measure(var3, this.L, 0.0F);
         var5.add(TargetRenderer3.TypeD.inter(var2, var3, var6, var13, 1.0F, var7, false, false));
         return new TargetRenderer3(this.L, this.O, var5, var13, this.E);
      }
   }

   private float J() {
      return this.L * this.O / 7.0F;
   }

   public boolean stale() {
      for (TargetRenderer3.TypeD var2 : this.U) {
         if (var2.k != null) {
            for (TargetRenderer3.TypeO var4 : var2.k) {
               if (var4.Q.isClosed()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public void draw(Canvas var1, float var2, float var3) {
      long var4 = 81469090061553L;
      long var6 = var4 ^ 2458054761635L;
      Translations.pause();

      try {
         this.h(var1, var2, var6, var3);
      } finally {
         Translations.resume();
      }
   }

   private void h(Canvas var1, float var2, long var3, float var5) {
      TargetHud.ensureSourceClassInitialized();
      float var6 = var5 + this.O * this.L * 0.5F;
      class_12137 var7 = null;

      for (TargetRenderer3.TypeD var9 : this.U) {
         long var10 = 79322934624338L;
         if (79322934624338L == 0L) {
            boolean var10000 = false;
         } else {
            int var26 = var10 < 0L ? -1 : 1;
         }

         if (var9.V != 0) {
            TargetSupport4.camera(var1, var2 + var9.K, var6, this.J(), var9.U);
            long var21 = 79322934624338L;
            if (79322934624338L == 0L) {
               boolean var35 = false;
            } else {
               int var36 = var21 < 0L ? -1 : 1;
            }
         } else if (var9.d != null) {
            float var20 = this.L * var9.O;
            var1.text(var9.d, var9.H, var2 + var9.K, var6, var20, var9.U, FontStyle.LEFT, FontWeight.BASELINE);
            float var23 = var9.F - var9.K;
            float var24 = Math.max(0.6F, this.L * 0.075F);
            if (var9.v) {
               var1.rect(var2 + var9.K, var6 + this.L * 0.13F, var23, var24, var9.U);
            }

            long var16 = 79322934624337L;
            if (79322934624337L == 0L) {
               boolean var31 = false;
            } else {
               int var32 = var16 < 0L ? -1 : 1;
            }

            if (var9.M) {
               var1.rect(var2 + var9.K, var6 - this.O * this.L * 0.45F - var24 / 2.0F, var23, var24, var9.U);
            }

            long var25 = 79322934624338L;
            if (79322934624338L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var25 < 0L ? -1 : 1;
            }
         } else {
            if (var7 == null) {
               var7 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
            }

            for (TargetRenderer3.TypeO var14 : var9.k) {
               long var15 = 79322934624338L;
               if (79322934624338L == 0L) {
                  boolean var27 = false;
               } else {
                  int var28 = var15 < 0L ? -1 : 1;
               }

               boolean var17 = var14.Q.isClosed();
               long var18 = 79322934624337L;
               if (79322934624337L == 0L) {
                  boolean var29 = false;
               } else {
                  int var30 = var18 < 0L ? -1 : 1;
               }

               if (!var17) {
                  var1.image(
                     var14.Q,
                     var7,
                     false,
                     var2 + var14.F,
                     var6 + var14.e,
                     var14.a - var14.F,
                     var14.H - var14.e,
                     var14.n,
                     var14.B,
                     var14.j,
                     var14.S,
                     0.0F,
                     var14.I
                  );
               }
            }
         }

         long var22 = 79322934624338L;
         if (79322934624338L == 0L) {
            boolean var37 = false;
         } else {
            int var38 = var22 < 0L ? -1 : 1;
         }
      }
   }

   public void drawShadow(Canvas var1, float var2, float var3, float var4) {
      long var5 = 65037726762100L;
      long var7 = var5 ^ 122554940777181L;
      Translations.pause();

      try {
         this.d(var7, var1, var2, var3, var4);
      } finally {
         Translations.resume();
      }
   }

   private void d(long var1, Canvas var3, float var4, float var5, float var6) {
      float var7 = var5 + this.O * this.L * 0.5F;
      TargetHud.ensureSourceClassInitialized();
      int var8 = BlendMode.withAlpha(-16777216, 0.75F * var6);
      class_12137 var9 = null;

      for (TargetRenderer3.TypeD var11 : this.U) {
         if (var11.V != 0) {
            float var22 = this.L * 0.08F;
            long var24 = 92703868855976L;
            if (92703868855976L == 0L) {
               boolean var30 = false;
            } else {
               int var31 = var24 < 0L ? -1 : 1;
            }

            TargetSupport4.cameraShadow(var3, var4 + var11.K + var22, var7 + var22, this.J(), BlendMode.withAlpha(-16777216, 0.6F * var6));
         } else if (var11.d != null) {
            var3.textGlow(
               var11.d, var11.H, var4 + var11.K, var7 + this.L * 0.07F, this.L * var11.O, var8, FontStyle.LEFT, FontWeight.BASELINE, 0.0F, 0.6F, 1.6F
            );
            long var21 = 92703868855976L;
            if (92703868855976L == 0L) {
               boolean var28 = false;
            } else {
               int var29 = var21 < 0L ? -1 : 1;
            }
         } else {
            if (var9 == null) {
               var9 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
            }

            float var12 = this.L * 0.08F;

            for (TargetRenderer3.TypeO var14 : var11.k) {
               long var15 = 92703868855976L;
               if (92703868855976L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var25 = var15 < 0L ? -1 : 1;
               }

               boolean var18 = var14.Q.isClosed();
               long var19 = 92703868855977L;
               if (92703868855977L == 0L) {
                  boolean var26 = false;
               } else {
                  int var27 = var19 < 0L ? -1 : 1;
               }

               if (!var18) {
                  var3.image(
                     var14.Q,
                     var9,
                     false,
                     var4 + var14.F + var12,
                     var7 + var14.e + var12,
                     var14.a - var14.F,
                     var14.H - var14.e,
                     var14.n,
                     var14.B,
                     var14.j,
                     var14.S,
                     0.0F,
                     BlendMode.withAlpha(-16777216, 0.6F * var6 * BlendMode.alpha(var14.I) / 255.0F)
                  );
               }
            }
         }

         long var23 = 92703868855977L;
         if (92703868855977L == 0L) {
            boolean var32 = false;
         } else {
            int var33 = var23 < 0L ? -1 : 1;
         }
      }
   }

   private static boolean i(class_11719 var0, long var1) {
      TargetHud.ensureSourceClassInitialized();
      return var0.equals(class_11719.field_61972) || var0 instanceof class_11721 var3 && var3.comp_4590().equals(T);
   }

   private static boolean D(int var0, long var1) {
      TargetHud.ensureSourceClassInitialized();
      long var3 = 20029591570287L;
      if (20029591570287L == 0L) {
         boolean var10000 = false;
      } else {
         int var9 = var3 < 0L ? -1 : 1;
      }

      long var6 = 20029591570286L;
      if (20029591570286L == 0L) {
         boolean var10 = false;
      } else {
         int var11 = var6 < 0L ? -1 : 1;
      }

      return var0 >= 128 && "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢ".indexOf(var0) >= 0;
   }

   private static boolean N(long var0, class_2583 var2, class_2583 var3) {
      TargetHud.ensureSourceClassInitialized();
      boolean var4 = var2.method_10984();
      long var5 = 111421591434870L;
      if (111421591434870L == 0L) {
         boolean var10000 = false;
      } else {
         int var8 = var5 < 0L ? -1 : 1;
      }

      return var4 == var3.method_10984()
         && var2.method_10965() == var3.method_10965()
         && var2.method_10986() == var3.method_10986()
         && Objects.equals(var2.method_10973(), var3.method_10973());
   }

private static final class TypeC {
      private final TargetRenderer3.TypeM c;
      private final float G;
      private final TrueTypeFont y;
      private final TrueTypeFont D;
      private final int Q;
      private final boolean I;
      private final boolean p;
      private final float J;
      private final List<TargetRenderer3.TypeD> o = new ArrayList<>();
      private boolean g;
      private static final String a = "\u1d00\u0299\u1d04\u1d05\u1d07\ua730\u0262\u029c\u026a\u1d0a\u1d0b\u029f\u1d0d\u0274\u1d0f\u1d18\u01eb\u0280\ua731\u1d1b\u1d1c\u1d20\u1d21x\u028f\u1d22";
      private static final long b = -2590284111830581248L;

      TypeC(TargetRenderer3.TypeM var1, float var2, TrueTypeFont var3, TrueTypeFont var4, int var5, boolean var6, boolean var7) {
         this.c = var1;
         this.G = var2;
         this.y = var3;
         this.D = var4;
         this.Q = var5;
         this.I = var6;
         this.p = var7;
         this.J = var2 * var3.capHeight / 7.0F;
      }

      TargetRenderer3 build() {
         long var1 = 100303130992397L;
         long var3 = var1 ^ 68650625051003L;
         long var5 = var1 ^ 88776872393384L;
         int var7 = (int)((var1 ^ 88776872393384L) >>> 32);
         int var8 = (int)((var1 ^ 88776872393384L) << 32 >>> 48);
         int var9 = (int)(var5 << 48 >>> 48);
         long var10 = var1 ^ 76184058895069L;
         long var12 = var1 ^ 80325095905378L;
         long var14 = var1 ^ 21267741388732L;
         long var16 = var1 ^ 131437773060411L;
         TargetRenderer3.TypeM var18 = this.c;
         float var19 = 0.0F;
         int var20 = 0;

         while (var20 < var18.j) {
            if (this.s(var20, var10)) {
               float var31 = 8.0F * this.J;
               this.o.add(TargetRenderer3.TypeD.symbol(var18.Z[var20], var19, var19 + var31, this.c(var7, var8, var9, var18.J[var20])));
               var19 += var31;
               var20++;
            } else if (this.K(var14, var20)) {
               int var30 = var20 + 1;

               while (var30 < var18.j && this.K(var14, var30) && !this.s(var30, var10)) {
                  var30++;
               }

               var19 = this.H(var16, var20, var30, var19);
               var20 = var30;
            } else {
               class_2583 var21 = var18.J[var20];
               boolean var22 = TargetRenderer3.D(var18.Z[var20], var12);
               TrueTypeFont var23 = var21.method_10984() ? this.D : this.y;
               int var24 = this.c(var7, var8, var9, var21);
               StringBuilder var25 = new StringBuilder();

               int var26;
               for (var26 = var20;
                  var26 < var18.j
                     && !this.K(var14, var26)
                     && TargetRenderer3.D(var18.Z[var26], var12) == var22
                     && TargetRenderer3.N(var3, var21, var18.J[var26]);
                  var26++
               ) {
                  int var27 = var18.Z[var26];
                  var25.appendCodePoint(var22 ? 65 + a.indexOf(var27) : var27);
               }

               float var32 = var22 ? 0.8F : 1.0F;
               String var28 = var25.toString();
               float var29 = var23.measure(var28, this.G * var32, 0.0F);
               this.o.add(TargetRenderer3.TypeD.inter(var23, var28, var19, var19 + var29, var32, var24, var21.method_10965(), var21.method_10986()));
               var19 += var29;
               var20 = var26;
            }
         }

         return new TargetRenderer3(this.G, this.y.capHeight, this.o, var19, this.g);
      }

      private boolean s(int var1, long var2) {
         TargetHud.ensureSourceClassInitialized();
         return !this.p && TargetSupport4.drawn(this.c.J[var1], this.c.Z[var1]);
      }

      private boolean K(long var1, int var3) {
         long var4 = 34063662407439L;
         long var6 = 20029591570287L;
         TargetHud.ensureSourceClassInitialized();
         class_2583 var8 = this.c.J[var3];
         int var9 = this.c.Z[var3];
         if (!this.p && !var8.method_10987() && TargetRenderer3.i(var8.method_27708(), var4)) {
            if (!Character.isWhitespace(var9)) {
               long var10 = 79639101157553L;
               if (79639101157553L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var14 = var10 < 0L ? -1 : 1;
               }

               if (!TargetRenderer3.D(var9, var6)) {
                  TrueTypeFont var13 = var8.method_10984() ? this.D : this.y;
                  return !var13.hasGlyph(var9);
               }
            }

            return false;
         } else {
            return true;
         }
      }

      private int c(int var1, int var2, int var3, class_2583 var4) {
         TargetHud.ensureSourceClassInitialized();
         class_5251 var5 = var4.method_10973();
         return this.I && var5 != null ? TargetSupport3.readable((int)b | var5.method_27716(), this.Q) : this.Q;
      }

      private float H(long var1, int var3, int var4, float var5) {
         long var6 = 2130271856969L;
         short var8 = 495;
         char var9 = '︘';
         int var10 = (int)(var6 << 48 >>> 48);
         TargetRenderer3.TypeM var11 = this.c;
         this.g = this.g | c(var11, var8, (short)var9, var3, var4, (char)var10);
         TargetRenderer3.TypeW var12 = new TargetRenderer3.TypeW(var11, var3, var4);
         class_327 var13 = class_310.method_1551().field_1772;
         TargetHud.ensureSourceClassInitialized();
         class_11465 var14 = var13.method_71795(var12, 0.0F, 0.0F, -1, false, false, 0);
         final TargetRenderer3.TypeY var15 = new TargetRenderer3.TypeY(this.J, this.I ? -1 : this.Q);
         var14.method_71801(new class_11464() {
            @Override
            public void method_71797(class_12238 var1) {
               var15.take(var1);
            }

            @Override
            public void method_71798(class_11767 var1) {
               var15.take(var1);
            }
         });
         float var16 = var13.method_27527().method_30875(var12) * this.J;
         long var17 = 49147742512693L;
         if (49147742512693L == 0L) {
            boolean var10000 = false;
         } else {
            int var25 = var17 < 0L ? -1 : 1;
         }

         long var20 = 49147742512694L;
         if (49147742512694L == 0L) {
            boolean var26 = false;
         } else {
            int var27 = var20 < 0L ? -1 : 1;
         }

         if (!var15.R.isEmpty()) {
            this.o.add(TargetRenderer3.TypeD.glyphs(var15.R, var5, var5 + var16));

            for (TargetRenderer3.TypeO var24 : var15.R) {
               var24.F += var5;
               var24.a += var5;
            }
         }

         return var5 + var16;
      }

      private static boolean c(TargetRenderer3.TypeM var0, int var1, short var2, int var3, int var4, char var5) {
         TargetHud.ensureSourceClassInitialized();

         for (int var6 = var3; var6 < var4; var6++) {
            boolean var7 = var0.J[var6].method_10987();
            if (var7) {
               return true;
            }
         }

         return false;
      }

}

   private static final class TypeD {
      
      final TrueTypeFont d;
      final String H;
      final float K;
      final float O;
      final int U;
      final boolean v;
      final boolean M;
      
      final List<TargetRenderer3.TypeO> k;
      final int V;
      final float F;

      private TypeD(
          TrueTypeFont var1,
         String var2,
         float var3,
         float var4,
         float var5,
         int var6,
         boolean var7,
         boolean var8,
          List<TargetRenderer3.TypeO> var9,
         int var10
      ) {
         this.d = var1;
         this.H = var2;
         this.K = var3;
         this.F = var4;
         this.O = var5;
         this.U = var6;
         this.v = var7;
         this.M = var8;
         this.k = var9;
         this.V = var10;
      }

      static TargetRenderer3.TypeD inter(TrueTypeFont var0, String var1, float var2, float var3, float var4, int var5, boolean var6, boolean var7) {
         return new TargetRenderer3.TypeD(var0, var1, var2, var3, var4, var5, var6, var7, null, 0);
      }

      static TargetRenderer3.TypeD glyphs(List<TargetRenderer3.TypeO> var0, float var1, float var2) {
         return new TargetRenderer3.TypeD(null, "", var1, var2, 1.0F, var0.isEmpty() ? -1 : ((TargetRenderer3.TypeO)var0.getFirst()).I, false, false, var0, 0);
      }

      static TargetRenderer3.TypeD symbol(int var0, float var1, float var2, int var3) {
         return new TargetRenderer3.TypeD(null, "", var1, var2, 1.0F, var3, false, false, null, var0);
      }
   }

   private static final class TypeM implements class_5224 {
      int[] Z = new int[32];
      class_2583[] J = new class_2583[32];
      int j;
      private static final long[] a = new long[]{5728574674747307122L, -75656557472772025L};
      private static final Integer[] b = new Integer[2];

      private TypeM(long var1) {
      }

      @Override
      public boolean accept(int var1, class_2583 var2, int var3) {
         if (!Character.isISOControl(var3) && Character.getType(var3) != 16) {
            if (this.j == this.Z.length) {
               this.Z = Arrays.copyOf(this.Z, this.j * 2);
               this.J = Arrays.copyOf(this.J, this.j * 2);
            }

            this.Z[this.j] = var3;
            this.J[this.j++] = var2;
            return true;
         } else {
            return true;
         }
      }

}

   private static final class TypeO {
      final GpuTextureView Q;
      float F;
      final float e;
      float a;
      final float H;
      final float n;
      final float B;
      final float j;
      final float S;
      final int I;

      TypeO(GpuTextureView var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, int var10) {
         this.Q = var1;
         this.F = var2;
         this.e = var3;
         this.a = var4;
         this.H = var5;
         this.n = var6;
         this.B = var7;
         this.j = var8;
         this.S = var9;
         this.I = var10;
      }
   }

   private record TypeW(TargetRenderer3.TypeM N, int t, int p) implements class_5481, ClientRegistry5.Listener {
      @Override
      public boolean accept(class_5224 var1) {
         for (int var2 = this.t; var2 < this.p; var2++) {
            if (!var1.accept(var2 - this.t, this.N.J[var2], this.N.Z[var2])) {
               return false;
            }
         }

         return true;
      }

      public TargetRenderer3.TypeM letters() {
         return this.N;
      }

      public int from() {
         return this.t;
      }

      public int to() {
         return this.p;
      }

      public TargetRenderer3.TypeM targetNameTextLayout_StyledCodePointCollectorState2() {
         return this.N;
      }

      public int integerState14() {
         return this.t;
      }

      public int integerState10() {
         return this.p;
      }
   }

   private static final class TypeY implements class_4588 {
      final List<TargetRenderer3.TypeO> R = new ArrayList<>();
      private final float b;
      private final int s;
      private final float[] z = new float[4];
      private final float[] o = new float[4];
      private final float[] H = new float[4];
      private final float[] m = new float[4];
      private final int[] u = new int[4];
      private int L;
      
      private GpuTextureView S;

      TypeY(float var1, int var2) {
         this.b = var1;
         this.s = var2;
      }

      void take(class_11767 var1) {
         long var2 = 51346146512649L;
         long var4 = var2 ^ 82007682295257L;
         this.S = var1.comp_4629();
         this.L = 0;
         var1.method_73403(TargetRenderer3.j, this, 15728880, false);
         this.O(var4);
         this.S = null;
      }

      private void O(long var1) {
         TargetHud.ensureSourceClassInitialized();
         if (this.L == 4) {
            long var3 = 110118970026704L;
            if (110118970026704L == 0L) {
               boolean var10000 = false;
            } else {
               int var27 = var3 < 0L ? -1 : 1;
            }

            if (this.S != null) {
               int var6 = 0;
               int var7 = 0;

               for (int var8 = 1; var8 < 4; var8++) {
                  long var9 = 110118970026704L;
                  if (110118970026704L == 0L) {
                     boolean var28 = false;
                  } else {
                     int var29 = var9 < 0L ? -1 : 1;
                  }

                  float var11 = this.z[var8];
                  long var12 = 110118970026703L;
                  if (110118970026703L == 0L) {
                     boolean var30 = false;
                  } else {
                     int var31 = var12 < 0L ? -1 : 1;
                  }

                  float var15;
                  int var16 = (var15 = var11 + this.o[var8] - (this.z[var6] + this.o[var6])) == 0.0F ? 0 : (var15 < 0.0F ? -1 : 1);
                  long var17 = 110118970026704L;
                  if (110118970026704L == 0L) {
                     boolean var32 = false;
                  } else {
                     int var33 = var17 < 0L ? -1 : 1;
                  }

                  if (var16 < 0) {
                     var6 = var8;
                  }

                  if (this.z[var8] + this.o[var8] > this.z[var7] + this.o[var7]) {
                     var7 = var8;
                  }
               }

               float var21 = this.z[0];
               long var22 = 110118970026704L;
               if (110118970026704L == 0L) {
                  boolean var34 = false;
               } else {
                  int var35 = var22 < 0L ? -1 : 1;
               }

               float var23 = Math.min(Math.min(var21, this.z[1]), Math.min(this.z[2], this.z[3]));
               float var24 = Math.max(Math.max(this.z[0], this.z[1]), Math.max(this.z[2], this.z[3]));
               float var13 = Math.min(Math.min(this.o[0], this.o[1]), Math.min(this.o[2], this.o[3]));
               float var14 = Math.max(Math.max(this.o[0], this.o[1]), Math.max(this.o[2], this.o[3]));
               int var25 = this.u[var6];
               long var26 = 110118970026703L;
               if (110118970026703L == 0L) {
                  boolean var36 = false;
               } else {
                  int var37 = var26 < 0L ? -1 : 1;
               }

               long var18 = 110118970026704L;
               if (110118970026704L == 0L) {
                  boolean var38 = false;
               } else {
                  int var39 = var18 < 0L ? -1 : 1;
               }

               int var20 = this.s == -1 ? TargetSupport3.readable(var25, Icons.TEXT) : BlendMode.withAlpha(this.s, BlendMode.alpha(var25) / 255.0F);
               this.R
                  .add(
                     new TargetRenderer3.TypeO(
                        this.S,
                        var23 * this.b,
                        (var13 - 7.0F) * this.b,
                        var24 * this.b,
                        (var14 - 7.0F) * this.b,
                        this.H[var6],
                        this.m[var6],
                        this.H[var7],
                        this.m[var7],
                        var20
                     )
                  );
            }
         }

         this.L = 0;
      }

      @Override
      public class_4588 method_22912(float var1, float var2, float var3) {
         long var4 = 115391713313799L;
         long var6 = var4 ^ 14111786299095L;
         if (this.L == 4) {
            this.O(var6);
         }

         this.z[this.L] = var1;
         this.o[this.L] = var2;
         this.u[this.L] = -1;
         this.L++;
         return this;
      }

      @Override
      public class_4588 method_1336(int var1, int var2, int var3, int var4) {
         return this.method_39415(BlendMode.argb(var4, var1, var2, var3));
      }

      @Override
      public class_4588 method_39415(int var1) {
         if (this.L > 0) {
            this.u[this.L - 1] = var1;
         }

         return this;
      }

      @Override
      public class_4588 method_22913(float var1, float var2) {
         if (this.L > 0) {
            this.H[this.L - 1] = var1;
            this.m[this.L - 1] = var2;
         }

         return this;
      }

      @Override
      public class_4588 method_60796(int var1, int var2) {
         return this;
      }

      @Override
      public class_4588 method_22921(int var1, int var2) {
         return this;
      }

      @Override
      public class_4588 method_22914(float var1, float var2, float var3) {
         return this;
      }

      @Override
      public class_4588 method_75298(float var1) {
         return this;
      }
   }
}
