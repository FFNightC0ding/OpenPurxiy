// Sourced By DevFFNightmare
package dev.purxiy.modules.roles;

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

public final class RolesRenderer2 {
   private static final String J = "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢ";
   private static final float o = 0.8F;
   private static final class_2960 m = class_2960.method_60656("uniform");
   private static final Matrix4f r = new Matrix4f();
   private static final int Y = 15728880;
   public final float S;
   private final float g;
   private final List<RolesRenderer2.TypeC> a;
   public final float U;
   public final boolean Q;
   public static boolean y;

   private static final String[] c = new String[3];
   private static final long[] d = new long[]{-1123748004260345746L, 8801838713577871714L, -5644385983681592162L, -990592987923542249L};
   private static final Integer[] e = new Integer[4];

   private RolesRenderer2(float var1, float var2, List<RolesRenderer2.TypeC> var3, float var4, boolean var5) {
      this.S = var1;
      this.g = var2;
      this.a = var3;
      this.U = var4;
      this.Q = var5;
   }

   public static RolesRenderer2 of(class_2561 var0, float var1, TrueTypeFont var2, TrueTypeFont var3, int var4, boolean var5, boolean var6) {
      return of(var0.method_30937(), var1, var2, var3, var4, var5, var6);
   }

   public static RolesRenderer2 of(class_5481 var0, float var1, TrueTypeFont var2, TrueTypeFont var3, int var4, boolean var5, boolean var6) {
      long var7 = 136512678907795L;
      long var9 = var7 ^ 64188016899180L;
      RolesRenderer2.TypeY var11 = new RolesRenderer2.TypeY(var9);
      ClientRegistry5.sequence(var0).accept(var11);
      return new RolesRenderer2.TypeN(var11, var1, var2, var3, var4, var5, var6).build();
   }

   public RolesRenderer2 truncated(float var1, TrueTypeFont var2) {
      if (this.U <= var1) {
         return this;
      } else {
         String var3 = "…";
         float var4 = Math.max(0.0F, var1 - var2.measure(var3, this.S, 0.0F));
         ArrayList var5 = new ArrayList();
         float var6 = 0.0F;
         int var7 = -1;

         for (RolesRenderer2.TypeC var9 : this.a) {
            if (!(var9.I <= var4)) {
               if (var9.K != null) {
                  float var10 = this.S * var9.F;
                  int var11 = var9.i.length();

                  while (var11 > 0 && var9.l + var9.K.measure(var9.i.substring(0, var11), var10, 0.0F) > var4) {
                     var11 = var9.i.offsetByCodePoints(var11, -1);
                  }

                  String var12 = var9.i.substring(0, var11).stripTrailing();
                  if (!var12.isEmpty()) {
                     var6 = var9.l + var9.K.measure(var12, var10, 0.0F);
                     var5.add(RolesRenderer2.TypeC.inter(var9.K, var12, var9.l, var6, var9.F, var9.E, var9.C, var9.U));
                  }

                  var7 = var9.E;
               } else if (var9.e != null) {
                  ArrayList var14 = new ArrayList();

                  for (RolesRenderer2.TypeP var16 : var9.e) {
                     if (var16.w <= var4) {
                        var14.add(var16);
                        var6 = Math.max(var6, var16.w);
                     }
                  }

                  if (!var14.isEmpty()) {
                     var5.add(RolesRenderer2.TypeC.glyphs(var14, var9.l, var6));
                  }
               }
               break;
            }

            var5.add(var9);
            var6 = var9.I;
            var7 = var9.E;
         }

         float var13 = var6 + var2.measure(var3, this.S, 0.0F);
         var5.add(RolesRenderer2.TypeC.inter(var2, var3, var6, var13, 1.0F, var7, false, false));
         return new RolesRenderer2(this.S, this.g, var5, var13, this.Q);
      }
   }

   private float w() {
      return this.S * this.g / 7.0F;
   }

   public boolean stale() {
      for (RolesRenderer2.TypeC var2 : this.a) {
         if (var2.e != null) {
            for (RolesRenderer2.TypeP var4 : var2.e) {
               if (var4.J.isClosed()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public void draw(Canvas var1, float var2, float var3) {
      long var4 = 130897633702221L;
      long var6 = var4 ^ 37666518931131L;
      Translations.pause();

      try {
         this.k(var1, var2, var6, var3);
      } finally {
         Translations.resume();
      }
   }

   private void k(Canvas var1, float var2, long var3, float var5) {
      RolesRegistry.ensureSourceClassInitialized();
      float var6 = var5 + this.g * this.S * 0.5F;
      class_12137 var7 = null;

      for (RolesRenderer2.TypeC var9 : this.a) {
         long var10 = 93789495388150L;
         if (93789495388150L == 0L) {
            boolean var10000 = false;
         } else {
            int var26 = var10 < 0L ? -1 : 1;
         }

         if (var9.m != 0) {
            RolesSupport2.camera(var1, var2 + var9.l, var6, this.w(), var9.E);
            long var21 = 93789495388149L;
            if (93789495388149L == 0L) {
               boolean var35 = false;
            } else {
               int var36 = var21 < 0L ? -1 : 1;
            }
         } else if (var9.K != null) {
            float var20 = this.S * var9.F;
            var1.text(var9.K, var9.i, var2 + var9.l, var6, var20, var9.E, FontStyle.LEFT, FontWeight.BASELINE);
            float var23 = var9.I - var9.l;
            float var24 = Math.max(0.6F, this.S * 0.075F);
            if (var9.C) {
               var1.rect(var2 + var9.l, var6 + this.S * 0.13F, var23, var24, var9.E);
            }

            long var16 = 93789495388150L;
            if (93789495388150L == 0L) {
               boolean var31 = false;
            } else {
               int var32 = var16 < 0L ? -1 : 1;
            }

            if (var9.U) {
               var1.rect(var2 + var9.l, var6 - this.g * this.S * 0.45F - var24 / 2.0F, var23, var24, var9.E);
            }

            long var25 = 93789495388150L;
            if (93789495388150L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var25 < 0L ? -1 : 1;
            }
         } else {
            if (var7 == null) {
               var7 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
            }

            for (RolesRenderer2.TypeP var14 : var9.e) {
               long var15 = 93789495388149L;
               if (93789495388149L == 0L) {
                  boolean var27 = false;
               } else {
                  int var28 = var15 < 0L ? -1 : 1;
               }

               boolean var17 = var14.J.isClosed();
               long var18 = 93789495388150L;
               if (93789495388150L == 0L) {
                  boolean var29 = false;
               } else {
                  int var30 = var18 < 0L ? -1 : 1;
               }

               if (!var17) {
                  var1.image(
                     var14.J,
                     var7,
                     false,
                     var2 + var14.K,
                     var6 + var14.r,
                     var14.w - var14.K,
                     var14.Z - var14.r,
                     var14.V,
                     var14.d,
                     var14.n,
                     var14.S,
                     0.0F,
                     var14.D
                  );
               }
            }
         }

         long var22 = 93789495388150L;
         if (93789495388150L == 0L) {
            boolean var37 = false;
         } else {
            int var38 = var22 < 0L ? -1 : 1;
         }
      }
   }

   public void drawShadow(Canvas var1, float var2, float var3, float var4) {
      long var5 = 117769238271480L;
      int var7 = (int)((var5 ^ 109109475667229L) >>> 32);
      int var8 = (int)((var5 ^ 109109475667229L) << 32 >>> 32);
      Translations.pause();

      try {
         this.F(var7, var1, var2, var8, var3, var4);
      } finally {
         Translations.resume();
      }
   }

   private void F(int var1, Canvas var2, float var3, int var4, float var5, float var6) {
      RolesRegistry.ensureSourceClassInitialized();
      float var7 = var5 + this.g * this.S * 0.5F;
      int var8 = BlendMode.withAlpha(-16777216, 0.75F * var6);
      class_12137 var9 = null;

      for (RolesRenderer2.TypeC var11 : this.a) {
         if (var11.m != 0) {
            float var16 = this.S * 0.08F;
            RolesSupport2.cameraShadow(var2, var3 + var11.l + var16, var7 + var16, this.w(), BlendMode.withAlpha(-16777216, 0.6F * var6));
         } else if (var11.K != null) {
            var2.textGlow(
               var11.K, var11.i, var3 + var11.l, var7 + this.S * 0.07F, this.S * var11.F, var8, FontStyle.LEFT, FontWeight.BASELINE, 0.0F, 0.6F, 1.6F
            );
         } else {
            if (var9 == null) {
               var9 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
            }

            float var12 = this.S * 0.08F;

            for (RolesRenderer2.TypeP var14 : var11.e) {
               boolean var15 = var14.J.isClosed();
               if (!var15) {
                  var2.image(
                     var14.J,
                     var9,
                     false,
                     var3 + var14.K + var12,
                     var7 + var14.r + var12,
                     var14.w - var14.K,
                     var14.Z - var14.r,
                     var14.V,
                     var14.d,
                     var14.n,
                     var14.S,
                     0.0F,
                     BlendMode.withAlpha(-16777216, 0.6F * var6 * BlendMode.alpha(var14.D) / 255.0F)
                  );
               }
            }
         }
      }
   }

   private static boolean u(class_11719 var0, long var1) {
      RolesRegistry.ensureSourceClassInitialized();
      return var0.equals(class_11719.field_61972) || var0 instanceof class_11721 var3 && var3.comp_4590().equals(m);
   }

   private static boolean s(long var0, int var2) {
      RolesRegistry.ensureSourceClassInitialized();
      long var3 = 113679627815887L;
      if (113679627815887L == 0L) {
         boolean var10000 = false;
      } else {
         int var9 = var3 < 0L ? -1 : 1;
      }

      long var6 = 113679627815886L;
      if (113679627815886L == 0L) {
         boolean var10 = false;
      } else {
         int var11 = var6 < 0L ? -1 : 1;
      }

      return var2 >= 128 && "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢ".indexOf(var2) >= 0;
   }

   private static boolean k(class_2583 var0, class_2583 var1, long var2) {
      RolesRegistry.ensureSourceClassInitialized();
      boolean var4 = var0.method_10984();
      boolean var5 = var1.method_10984();
      long var6 = 113146139810611L;
      if (113146139810611L == 0L) {
         boolean var10000 = false;
      } else {
         int var12 = var6 < 0L ? -1 : 1;
      }

      if (var4 == var5) {
         var4 = var0.method_10965();
         long var9 = 113146139810611L;
         if (113146139810611L == 0L) {
            boolean var13 = false;
         } else {
            int var14 = var9 < 0L ? -1 : 1;
         }

         if (var4 == var1.method_10965() && var0.method_10986() == var1.method_10986() && Objects.equals(var0.method_10973(), var1.method_10973())) {
            return true;
         }
      }

      return false;
   }

private static final class TypeC {
      
      final TrueTypeFont K;
      final String i;
      final float l;
      final float F;
      final int E;
      final boolean C;
      final boolean U;
      
      final List<RolesRenderer2.TypeP> e;
      final int m;
      final float I;

      private TypeC(
          TrueTypeFont var1,
         String var2,
         float var3,
         float var4,
         float var5,
         int var6,
         boolean var7,
         boolean var8,
          List<RolesRenderer2.TypeP> var9,
         int var10
      ) {
         this.K = var1;
         this.i = var2;
         this.l = var3;
         this.I = var4;
         this.F = var5;
         this.E = var6;
         this.C = var7;
         this.U = var8;
         this.e = var9;
         this.m = var10;
      }

      static RolesRenderer2.TypeC inter(TrueTypeFont var0, String var1, float var2, float var3, float var4, int var5, boolean var6, boolean var7) {
         return new RolesRenderer2.TypeC(var0, var1, var2, var3, var4, var5, var6, var7, null, 0);
      }

      static RolesRenderer2.TypeC glyphs(List<RolesRenderer2.TypeP> var0, float var1, float var2) {
         return new RolesRenderer2.TypeC(null, "", var1, var2, 1.0F, var0.isEmpty() ? -1 : ((RolesRenderer2.TypeP)var0.getFirst()).D, false, false, var0, 0);
      }

      static RolesRenderer2.TypeC symbol(int var0, float var1, float var2, int var3) {
         return new RolesRenderer2.TypeC(null, "", var1, var2, 1.0F, var3, false, false, null, var0);
      }
   }

   private record TypeI(RolesRenderer2.TypeY x, int F, int q) implements class_5481, ClientRegistry5.Listener {
      @Override
      public boolean accept(class_5224 var1) {
         for (int var2 = this.F; var2 < this.q; var2++) {
            if (!var1.accept(var2 - this.F, this.x.a[var2], this.x.e[var2])) {
               return false;
            }
         }

         return true;
      }

      public RolesRenderer2.TypeY letters() {
         return this.x;
      }

      public int from() {
         return this.F;
      }

      public int to() {
         return this.q;
      }

      public RolesRenderer2.TypeY roleTextLayout_StyledTextCollectorState1() {
         return this.x;
      }

      public int integerState22() {
         return this.F;
      }

      public int integerState11() {
         return this.q;
      }
   }

   private static final class TypeK implements class_4588 {
      final List<RolesRenderer2.TypeP> i = new ArrayList<>();
      private final float r;
      private final int N;
      private final float[] u = new float[4];
      private final float[] t = new float[4];
      private final float[] f = new float[4];
      private final float[] K = new float[4];
      private final int[] q = new int[4];
      private int P;
      
      private GpuTextureView L;

      TypeK(float var1, int var2) {
         this.r = var1;
         this.N = var2;
      }

      void take(class_11767 var1) {
         long var2 = 80599083005136L;
         long var4 = (var2 ^ 67738667526390L) >>> 16;
         int var6 = (int)((var2 ^ 67738667526390L) << 48 >>> 48);
         this.L = var1.comp_4629();
         this.P = 0;
         var1.method_73403(RolesRenderer2.r, this, 15728880, false);
         this.f(var4, (short)var6);
         this.L = null;
      }

      private void f(long var1, short var3) {
         RolesRegistry.ensureSourceClassInitialized();
         if (this.P == 4) {
            short var4 = 7206;
            if (this.L != null) {
               int var5 = 0;
               int var6 = 0;

               for (int var7 = 1; var7 < 4; var7++) {
                  float var8 = this.u[var7];
                  var8 += this.t[var7];
                  float var9 = this.u[var5] + this.t[var5];
                  var4 = 7206;
                  if (var8 < var9) {
                     var5 = var7;
                  }

                  if (this.u[var7] + this.t[var7] > this.u[var6] + this.t[var6]) {
                     var6 = var7;
                  }
               }

               float var19 = this.u[0];
               float var21 = this.u[1];
               long var22 = 1960201345L;
               if (1960201345L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var24 = var22 < 0L ? -1 : 1;
               }

               float var11 = Math.min(Math.min(var19, var21), Math.min(this.u[2], this.u[3]));
               float var12 = Math.max(Math.max(this.u[0], this.u[1]), Math.max(this.u[2], this.u[3]));
               float var13 = Math.min(Math.min(this.t[0], this.t[1]), Math.min(this.t[2], this.t[3]));
               float var14 = Math.max(Math.max(this.t[0], this.t[1]), Math.max(this.t[2], this.t[3]));
               int var15 = this.q[var5];
               short var16 = 7206;
               var16 = 7206;
               int var17 = this.N == -1 ? RolesSupport3.readable(var15, Icons.TEXT) : BlendMode.withAlpha(this.N, BlendMode.alpha(var15) / 255.0F);
               this.i
                  .add(
                     new RolesRenderer2.TypeP(
                        this.L,
                        var11 * this.r,
                        (var13 - 7.0F) * this.r,
                        var12 * this.r,
                        (var14 - 7.0F) * this.r,
                        this.f[var5],
                        this.K[var5],
                        this.f[var6],
                        this.K[var6],
                        var17
                     )
                  );
            }
         }

         this.P = 0;
      }

      @Override
      public class_4588 method_22912(float var1, float var2, float var3) {
         long var4 = 57550772618943L;
         long var6 = (var4 ^ 70926449957529L) >>> 16;
         int var8 = (int)((var4 ^ 70926449957529L) << 48 >>> 48);
         if (this.P == 4) {
            this.f(var6, (short)var8);
         }

         this.u[this.P] = var1;
         this.t[this.P] = var2;
         this.q[this.P] = -1;
         this.P++;
         return this;
      }

      @Override
      public class_4588 method_1336(int var1, int var2, int var3, int var4) {
         return this.method_39415(BlendMode.argb(var4, var1, var2, var3));
      }

      @Override
      public class_4588 method_39415(int var1) {
         if (this.P > 0) {
            this.q[this.P - 1] = var1;
         }

         return this;
      }

      @Override
      public class_4588 method_22913(float var1, float var2) {
         if (this.P > 0) {
            this.f[this.P - 1] = var1;
            this.K[this.P - 1] = var2;
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

   private static final class TypeN {
      private final RolesRenderer2.TypeY Z;
      private final float x;
      private final TrueTypeFont o;
      private final TrueTypeFont e;
      private final int A;
      private final boolean s;
      private final boolean P;
      private final float D;
      private final List<RolesRenderer2.TypeC> I = new ArrayList<>();
      private boolean c;
      private static final String a = "\u1d00\u0299\u1d04\u1d05\u1d07\ua730\u0262\u029c\u026a\u1d0a\u1d0b\u029f\u1d0d\u0274\u1d0f\u1d18\u01eb\u0280\ua731\u1d1b\u1d1c\u1d20\u1d21x\u028f\u1d22";
      private static final long b = -1057605915353546752L;

      TypeN(RolesRenderer2.TypeY var1, float var2, TrueTypeFont var3, TrueTypeFont var4, int var5, boolean var6, boolean var7) {
         this.Z = var1;
         this.x = var2;
         this.o = var3;
         this.e = var4;
         this.A = var5;
         this.s = var6;
         this.P = var7;
         this.D = var2 * var3.capHeight / 7.0F;
      }

      RolesRenderer2 build() {
         long var1 = 30966098103461L;
         long var3 = var1 ^ 135573907081066L;
         long var5 = var1 ^ 44290821177017L;
         long var7 = var1 ^ 107644267626398L;
         long var9 = var1 ^ 131006001050468L;
         long var11 = var1 ^ 49385680670887L;
         int var13 = (int)((var1 ^ 49385680670887L) >>> 48);
         int var14 = (int)((var1 ^ 49385680670887L) << 16 >>> 32);
         int var15 = (int)(var11 << 48 >>> 48);
         long var16 = var1 ^ 135026125543318L;
         RolesRenderer2.TypeY var18 = this.Z;
         float var19 = 0.0F;
         int var20 = 0;

         while (var20 < var18.A) {
            if (this.S(var20, (short)var13, var14, (char)var15)) {
               float var31 = 8.0F * this.D;
               this.I.add(RolesRenderer2.TypeC.symbol(var18.e[var20], var19, var19 + var31, this.a(var5, var18.a[var20])));
               var19 += var31;
               var20++;
            } else if (this.x(var20, var9)) {
               int var30 = var20 + 1;

               while (var30 < var18.A && this.x(var30, var9) && !this.S(var30, (short)var13, var14, (char)var15)) {
                  var30++;
               }

               var19 = this.O(var20, var30, var7, var19);
               var20 = var30;
            } else {
               class_2583 var21 = var18.a[var20];
               boolean var22 = RolesRenderer2.s(var3, var18.e[var20]);
               TrueTypeFont var23 = var21.method_10984() ? this.e : this.o;
               int var24 = this.a(var5, var21);
               StringBuilder var25 = new StringBuilder();

               int var26;
               for (var26 = var20;
                  var26 < var18.A && !this.x(var26, var9) && RolesRenderer2.s(var3, var18.e[var26]) == var22 && RolesRenderer2.k(var21, var18.a[var26], var16);
                  var26++
               ) {
                  int var27 = var18.e[var26];
                  var25.appendCodePoint(var22 ? 65 + a.indexOf(var27) : var27);
               }

               float var32 = var22 ? 0.8F : 1.0F;
               String var28 = var25.toString();
               float var29 = var23.measure(var28, this.x * var32, 0.0F);
               this.I.add(RolesRenderer2.TypeC.inter(var23, var28, var19, var19 + var29, var32, var24, var21.method_10965(), var21.method_10986()));
               var19 += var29;
               var20 = var26;
            }
         }

         return new RolesRenderer2(this.x, this.o.capHeight, this.I, var19, this.c);
      }

      private boolean S(int var1, short var2, int var3, char var4) {
         RolesRegistry.ensureSourceClassInitialized();
         return !this.P && RolesSupport2.drawn(this.Z.a[var1], this.Z.e[var1]);
      }

      private boolean x(int var1, long var2) {
         long var4 = 113679627815887L;
         long var6 = 17157675881575L;
         RolesRegistry.ensureSourceClassInitialized();
         class_2583 var8 = this.Z.a[var1];
         int var9 = this.Z.e[var1];
         if (!this.P && !var8.method_10987() && RolesRenderer2.u(var8.method_27708(), var6)) {
            if (!Character.isWhitespace(var9)) {
               long var10 = 117716151238592L;
               if (117716151238592L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var14 = var10 < 0L ? -1 : 1;
               }

               if (!RolesRenderer2.s(var4, var9)) {
                  TrueTypeFont var13 = var8.method_10984() ? this.e : this.o;
                  return !var13.hasGlyph(var9);
               }
            }

            return false;
         } else {
            return true;
         }
      }

      private int a(long var1, class_2583 var3) {
         RolesRegistry.ensureSourceClassInitialized();
         class_5251 var4 = var3.method_10973();
         return this.s && var4 != null ? RolesSupport3.readable((int)b | var4.method_27716(), this.A) : this.A;
      }

      private float O(int var1, int var2, long var3, float var5) {
         long var6 = 138996369379659L;
         RolesRenderer2.TypeY var8 = this.Z;
         RolesRegistry.ensureSourceClassInitialized();
         this.c = this.c | K(var8, var1, var2, var6);
         RolesRenderer2.TypeI var9 = new RolesRenderer2.TypeI(var8, var1, var2);
         class_327 var10 = class_310.method_1551().field_1772;
         class_11465 var11 = var10.method_71795(var9, 0.0F, 0.0F, -1, false, false, 0);
         final RolesRenderer2.TypeK var12 = new RolesRenderer2.TypeK(this.D, this.s ? -1 : this.A);
         var11.method_71801(new class_11464() {
            @Override
            public void method_71797(class_12238 var1) {
               var12.take(var1);
            }

            @Override
            public void method_71798(class_11767 var1) {
               var12.take(var1);
            }
         });
         float var13 = var10.method_27527().method_30875(var9) * this.D;
         long var14 = 138328969212730L;
         if (138328969212730L == 0L) {
            boolean var10000 = false;
         } else {
            int var21 = var14 < 0L ? -1 : 1;
         }

         long var17 = 138328969212730L;
         if (138328969212730L == 0L) {
            boolean var22 = false;
         } else {
            int var23 = var17 < 0L ? -1 : 1;
         }

         if (!var12.i.isEmpty()) {
            this.I.add(RolesRenderer2.TypeC.glyphs(var12.i, var5, var5 + var13));

            for (RolesRenderer2.TypeP var20 : var12.i) {
               var20.K += var5;
               var20.w += var5;
            }
         }

         return var5 + var13;
      }

      private static boolean K(RolesRenderer2.TypeY var0, int var1, int var2, long var3) {
         RolesRegistry.ensureSourceClassInitialized();

         for (int var5 = var1; var5 < var2; var5++) {
            boolean var6 = var0.a[var5].method_10987();
            long var7 = 138996369379659L;
            if (138996369379659L == 0L) {
               boolean var10000 = false;
            } else {
               int var11 = var7 < 0L ? -1 : 1;
            }

            if (var6) {
               return true;
            }
         }

         long var10 = 138996369379658L;
         if (138996369379658L == 0L) {
            boolean var12 = false;
         } else {
            int var13 = var10 < 0L ? -1 : 1;
         }

         return false;
      }

}

   private static final class TypeP {
      final GpuTextureView J;
      float K;
      final float r;
      float w;
      final float Z;
      final float V;
      final float d;
      final float n;
      final float S;
      final int D;

      TypeP(GpuTextureView var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, int var10) {
         this.J = var1;
         this.K = var2;
         this.r = var3;
         this.w = var4;
         this.Z = var5;
         this.V = var6;
         this.d = var7;
         this.n = var8;
         this.S = var9;
         this.D = var10;
      }
   }

   private static final class TypeY implements class_5224 {
      int[] e = new int[32];
      class_2583[] a = new class_2583[32];
      int A;
      private static final long[] b = new long[]{5803490112407975821L, -3145577414298822131L};
      private static final Integer[] c = new Integer[2];

      private TypeY(long var1) {
      }

      @Override
      public boolean accept(int var1, class_2583 var2, int var3) {
         if (!Character.isISOControl(var3) && Character.getType(var3) != 16) {
            if (this.A == this.e.length) {
               this.e = Arrays.copyOf(this.e, this.A * 2);
               this.a = Arrays.copyOf(this.a, this.A * 2);
            }

            this.e[this.A] = var3;
            this.a[this.A++] = var2;
            return true;
         } else {
            return true;
         }
      }

}
}
