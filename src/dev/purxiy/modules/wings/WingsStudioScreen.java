package dev.purxiy.modules.wings;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.Setting;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.DrawContext.Cursor;
import dev.purxiy.ui.clickgui.Studio;
import dev.purxiy.ui.clickgui.Studio.PanelSide;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.class_310;
import net.minecraft.class_746;

public final class WingsStudioScreen implements Studio {
   private static final float N = 34.0F;
   private static final float G = 24.0F;
   private static final long Q = 320L;
   private static final float W = 0.9F;
   private static final float J = 0.25F;
   private static final float h = 0.55F;
   private static final Map<ModuleDescriptor, WingsStudioScreen> F = new IdentityHashMap<>();
   private static WingsStudioScreen Z;
   private static final List<WingsStudioScreen.WingAction> b = new ArrayList<>();
   private static double Y;
   private static long X = 0L;
   private final ModuleDescriptor A;
   private final WingsFactory d;
   private boolean a;
   private boolean o;
   private boolean z;
   private boolean D;
   private double K;
   private int l;
   private double w;
   private List<WingsFactory.CosmeticEntry> p;
   private double g;
   private float n;
   private int j;
   private final double[] M;
   private boolean V;
   private final WingsStudioScreen.WingStudioState e;
   private final WingsStudioScreen.WingStudioState y;
   private final WingsStudioScreen.WingStudioState c;
   private final WingsStudioScreen.WingStudioState x;
   private final List<WingsStudioScreen.WingStudioState> R;
   private final float[] I;
   private final float[] U;
   private boolean k;
   private final Animator H;
   private final Animator t;
   private final Animator L;
   private final Animator r;
   private float s;
   private float f;
   private float P;
   private boolean S;
   private float E;
   private float B;
   private long v;
   private boolean C;
   private final Object T;
   private static final Integer[] u = new Integer[18];

   private WingsStudioScreen(long var1, ModuleDescriptor var3, WingsFactory var4) {
      long var5 = 110429702371650L;
      var5 = 29316683865660L;
      WingsState.ensureSourceClassInitialized();
      this.K = -1.0;
      this.w = -1.0;
      this.p = List.of();
      this.g = -1.0;
      this.n = 1.0F;
      this.M = new double[16];
      this.R = new ArrayList<>();
      this.I = new float[4];
      this.U = new float[4];
      this.H = Animator.smooth(0.0F);
      this.t = new Animator(0.0F, 420.0F, 41.0F);
      this.L = Animator.smooth(0.0F);
      this.r = Animator.smooth(0.0F);
      this.T = new Object();
      this.A = var3;
      this.d = var4;
      this.e = new WingsStudioScreen.WingStudioState("Play", "play", () -> this.L((short)0, 115626504820645L));
      this.y = new WingsStudioScreen.WingStudioState("Loop", "repeat", () -> {
         long var1x = 29316683865660L;
         int var3x = (int)((var1x ^ 126996650133913L) >>> 48);
         long var4x = (var1x ^ 126996650133913L) << 16 >>> 16;
         WingsState.ensureSourceClassInitialized();
         this.V = !this.V;
         short var6 = 7740;
         UiAudioPolicy.play(this.V ? "TOGGLE_ON" : "TOGGLE_OFF");
         if (this.V && this.g < 0.0) {
            this.L((short)var3x, var4x);
         }
      });

      for (WingsFactory.Data var8 : var4.g) {
         long var9 = 1648577012294L;
         int var11 = var9 < 0L ? -1 : 1;
         this.R.add(new WingsStudioScreen.WingStudioState(var8.label(), var8.icon(), () -> {
            long var2 = 39308578936164L;
            byte var4x = 0;
            int var5x = 599801314;
            int var6 = (int)(var2 << 48 >>> 48);
            this.M(var8, var4x, var5x, (short)var6);
         }));
      }

      long var13 = 1648577012294L;
      int var14 = var13 < 0L ? -1 : 1;
      this.c = new WingsStudioScreen.WingStudioState("", "rotate-ccw", () -> {
         WingsSupport.resetView();
         UiAudioPolicy.play("TAB", 1.1F);
      });
      this.x = new WingsStudioScreen.WingStudioState("Done", "check", () -> {
         this.z = true;
         int var1x = -1320055111;
         UiAudioPolicy.play("CLICK");
      });
   }

   public static WingsStudioScreen of(ModuleDescriptor var0) {
      long var1 = 73226286772224L;
      long var3 = var1 ^ 74657337659975L;
      WingsStudioScreen var5 = F.get(var0);
      if (var5 == null) {
         WingsFactory var6 = WingsFactory.of(var0);
         if (var6 == null) {
            return null;
         }

         var5 = new WingsStudioScreen(var3, var0, var6);
         F.put(var0, var5);
      }

      return var5;
   }

   static boolean showing(ModuleDescriptor var0) {
      WingsStudioScreen var1 = Z;
      return var1 != null && var1.A == var0 && var1.o;
   }

   static void later(float var0, Consumer<class_746> var1) {
      b.add(new WingsStudioScreen.WingAction(Y + var0, var1));
   }

   public String title() {
      return "Live preview";
   }

   public String subtitle() {
      return "Step out of yourself and watch it play on you";
   }

   public String icon() {
      return "scan-eye";
   }

   public String action() {
      return "Preview";
   }

   public boolean available() {
      class_310 var1 = class_310.method_1551();
      return var1.field_1687 != null
         && var1.field_1724 != null
         && var1.field_1724.method_5805()
         && !var1.field_1724.method_7325()
         && (Z == null || Z == this || !Z.o);
   }

   public PanelSide dock() {
      return PanelSide.RIGHT;
   }

   public boolean keepsWorldRunning() {
      return true;
   }

   public void setOpen(boolean var1) {
      long var2 = 118760509481894L;
      long var4 = var2 ^ 60800959763422L;
      long var6 = var2 ^ 77114041706245L;
      int var8 = (int)((var2 ^ 77114041706245L) >>> 32);
      int var9 = (int)((var2 ^ 77114041706245L) << 32 >>> 56);
      int var10 = (int)(var6 << 40 >>> 40);
      class_310 var11 = class_310.method_1551();
      if (var1 != this.a) {
         this.a = var1;
         this.S = false;
         if (var1) {
            if (Z != null && Z != this) {
               Z.Y(var4, true);
            }

            Z = this;
            if (!this.o) {
               this.o = true;
            }

            WingsSupport.open();
            this.D = false;
            this.K = -1.0;
            this.l = this.o(var8, (byte)var9, var10);
            this.w = -1.0;
            this.C = false;
            this.H.snap(0.0F);
            this.L.snap(0.0F);
            this.T();
         } else {
            this.T();
            b.clear();
            this.V = false;
            WingsSupport.close(CosmeticRegistry.currentOwner() == null);
            if (var11.field_1724 != null) {
               this.d.closed(var11.field_1724);
            }
         }
      }
   }

   public boolean consumeCloseRequest() {
      boolean var1 = this.z;
      this.z = false;
      return var1;
   }

   private void Y(long var1, boolean var3) {
      WingsState.ensureSourceClassInitialized();
      if (this.a) {
         long var4 = 100396041302136L;
         int var7 = var4 < 0L ? -1 : 1;
         if (!var3) {
            return;
         }
      }

      if (var3) {
         long var8 = 100396041302136L;
         int var8x = var8 < 0L ? -1 : 1;
         if (this.a) {
            this.a = false;
            this.T();
            class_310 var7 = class_310.method_1551();
            if (var7.field_1724 != null) {
               this.d.closed(var7.field_1724);
            }

            WingsSupport.reset();
         }
      }

      if (this.o) {
         this.o = false;
      }

      if (Z == this) {
         Z = null;
      }
   }

   static void tick(class_310 var0) {
      long var1 = 131529311440270L;
      long var3 = var1 ^ 49406275632630L;
      WingsStudioScreen var5 = Z;
      if (var5 != null) {
         if (var0.field_1687 != null && var0.field_1724 != null) {
            if (var5.a && CosmeticRegistry.currentOwner() == null) {
               var5.setOpen(false);
            }

            if (!var5.a && WingsSupport.active() && CosmeticRegistry.currentOwner() == null) {
               WingsSupport.hurry();
            }

            if (!var5.a && !WingsSupport.active()) {
               var5.Y(var3, false);
            }
         } else {
            var5.Y(var3, true);
         }
      }
   }

   private void L(short var1, long var2) {
      WingsState.ensureSourceClassInitialized();
      class_310 var4 = class_310.method_1551();
      if (var4.field_1724 != null) {
         String var5 = this.d.note();
         long var6 = 115626504820644L;
         int var8 = var6 < 0L ? -1 : 1;
         if (var5 == null) {
            this.p = this.d.timeline();
            this.n = Math.max(0.5F, this.d.length());
            this.g = Y;
            this.j = 0;
            UiAudioPolicy.play("SELECT", 1.05F, 0.7F);
            return;
         }
      }

      UiAudioPolicy.play("DENY");
   }

   private void M(WingsFactory.Data var1, short var2, int var3, short var4) {
      WingsState.ensureSourceClassInitialized();
      class_310 var5 = class_310.method_1551();
      if (var5.field_1724 != null && this.d.note() == null) {
         var1.action().accept(var5.field_1724);
      } else {
         UiAudioPolicy.play("DENY");
      }
   }

   private int o(int var1, byte var2, int var3) {
      WingsState.ensureSourceClassInitialized();
      int var4 = 1;

      for (Setting var6 : this.A.settings()) {
         try {
            var4 = var4 * 31 + var6.save().hashCode();
         } catch (RuntimeException var8) {
            var4 *= 31;
         }
      }

      return var4;
   }

   private void T() {
      this.g = -1.0;
      this.j = 0;
      this.p = List.of();
   }

   private void H(int var1, byte var2, int var3) {
      long var4 = 73449794274479L;
      int var6 = (int)((var4 ^ 48259197997834L) >>> 48);
      long var7 = (var4 ^ 48259197997834L) << 16 >>> 16;
      long var9 = var4 ^ 115366211518476L;
      int var11 = (int)((var4 ^ 115366211518476L) >>> 32);
      int var12 = (int)((var4 ^ 115366211518476L) << 32 >>> 56);
      int var13 = (int)(var9 << 40 >>> 40);
      WingsState.ensureSourceClassInitialized();
      long var14 = System.nanoTime();
      float var16 = X == 0L ? 0.0F : Math.min(0.1F, (float)(var14 - X) / 1.0E9F);
      X = var14;
      Y += var16;
      class_746 var17 = class_310.method_1551().field_1724;
      if (var17 != null) {
         int var18 = 15041711;
         if (this.g >= 0.0) {
            double var19;
            for (var19 = Y - this.g; this.j < this.p.size() && this.p.get(this.j).at() <= var19; this.p.get(this.j++).action().accept(var17)) {
               if (this.j < this.M.length) {
                  this.M[this.j] = Y;
               }
            }

            int var21 = 17101;
            double var22;
            var21 = (var22 = var19 - (this.n + (this.V ? 0.9F : 0.0F))) == 0.0 ? 0 : (var22 < 0.0 ? -1 : 1);
            if (var21 >= 0) {
               if (this.V && this.a) {
                  this.L((short)var6, var7);
               } else if (var19 >= this.n + 1.2) {
                  this.T();
               }
            }
         }

         Iterator var27 = b.iterator();

         while (var27.hasNext()) {
            WingsStudioScreen.WingAction var20 = (WingsStudioScreen.WingAction)var27.next();
            if (var20.w <= Y) {
               var27.remove();
               var20.O.accept(var17);
            }
         }

         int var28 = this.o(var11, (byte)var12, var13);
         var18 = 15041711;
         if (var28 != this.l) {
            this.l = var28;
            this.w = Y;
         }

         if (this.w >= 0.0 && Y - this.w >= 0.55F) {
            this.w = -1.0;
            boolean var30 = this.g >= 0.0 && Y - this.g < this.n;
            if (this.a && this.D) {
               var18 = 15041711;
               if (!var30 && this.d.note() == null) {
                  this.L((short)var6, var7);
               }
            }
         }

         var18 = 15041711;
         if (this.a && WingsSupport.settled()) {
            if (this.K < 0.0) {
               this.K = Y;
            }

            if (!this.D && Y - this.K >= 0.25) {
               short var31 = 92;
               this.D = true;
               if (this.g < 0.0) {
                  var31 = 17101;
                  if (this.d.note() == null) {
                     this.L((short)var6, var7);
                  }
               }
            }
         }
      }
   }

   public void render(DrawContext var1, float var2, float var3) {
      long var4 = 72375384010566L;
      long var6 = var4 ^ 63649234658824L;
      long var8 = var4 ^ 22183154880624L;
      long var10 = var4 ^ 30260796436359L;
      long var12 = var4 ^ 3429160741865L;
      int var14 = (int)((var4 ^ 3429160741865L) >>> 32);
      int var15 = (int)((var4 ^ 3429160741865L) << 32 >>> 56);
      int var16 = (int)(var12 << 40 >>> 40);
      long var17 = var4 ^ 45197570668339L;
      this.H(var14, (byte)var15, var16);
      Canvas var19 = var1.canvas;
      this.s = var3;
      this.f = var19.width();
      this.P = var19.height();
      float var20 = 16.0F;
      float var21 = Math.max(var20 + 200.0F, var3 - 16.0F);
      float var22 = (var20 + var21) / 2.0F;
      float var23 = 110.0F;
      WingsSupport.frame(var22 / this.f * 2.0F - 1.0F, 1.0F - (this.P - var23) / this.P);
      float var24 = Easing.SMOOTHSTEP.apply(T((WingsSupport.progress() - 0.62F) / 0.38F, var6));
      float var25 = this.H.target(this.a ? var24 : 0.0F).update(MotionStyle.MENU.dt(var1.dt)) * var2;
      if (this.S) {
         var1.claim(this.T, Cursor.MOVE);
      }

      if (var25 <= 0.01F) {
         this.I[2] = 0.0F;
      } else {
         var19.pushAlpha(var25);
         float var26 = (1.0F - var25) * 18.0F;
         this.K(var1, var19, var20, var21, var17, var26);
         this.d(var1, var19, var26, var10);
         this.I(var1, var19, var8, var20, var25);
         var19.popAlpha();
      }
   }

   private void K(DrawContext var1, Canvas var2, float var3, float var4, long var5, float var7) {
      long var10 = 132148812597582L;
      long var12 = 43535052576281L;
      long var14 = 68455013795339L;
      long var16 = 137235531479585L;
      byte var18 = 0;
      int var19 = 2094048026;
      int var20 = (int)(var16 << 48 >>> 48);
      long var21 = 718239222L;
      short var23 = 28711;
      String var24 = "Live preview";
      WingsState.ensureSourceClassInitialized();
      float var25 = var2.textWidth(Fonts.SEMIBOLD, var24, 9.5F);
      List<WingsStudioScreen.WingStudioState>var26 = this.j();
      this.k = false;
      float var27 = this.n(var14, var2, var26);
      boolean var28 = 32.0F + var25 + var27 + 10.0F > var4 - 16.0F;
      if (var28 && 26.0F + var27 + 10.0F > var4 - 16.0F) {
         this.k = true;
         var27 = this.n(var14, var2, var26);
      }

      float var29 = var28 ? 26.0F : 32.0F + var25;
      float var30 = var29 + var27 + 10.0F;
      float var31 = Math.max(16.0F, (16.0F + var4) / 2.0F - var30 / 2.0F);
      float var32 = this.P - 22.0F - 34.0F + var7;
      this.I[0] = var31;
      this.I[1] = var32 - 24.0F - 8.0F;
      this.I[2] = var30;
      this.I[3] = 66.0F;
      var2.shadow(var31, var32, var30, 34.0F, 17.0F, 22.0F, 0.0F, 0.0F, 6.0F, 1879048192);
      var2.roundRect(var31, var32, var30, 34.0F, 17.0F, Theme.tint(-300872416));
      var2.roundRectOutlineGradient(var31, var32, var30, 34.0F, 17.0F, 1.0F, 90.0F, 788529151, 184549375);
      float var33 = var32 + 17.0F;
      float var34 = MotionStyle.reduced() ? 0.7F : 0.55F + 0.45F * (float)Math.sin(var1.time * 3.2F);
      long var35 = 115209614727284L;
      int var35x = var35 < 0L ? -1 : 1;
      var2.glowCircle(var31 + 12.0F + 6.0F, var33, 3.0F, 7.0F, BlendMode.withAlpha(Icons.PINK, 0.45F * var34));
      var2.circle(var31 + 12.0F + 6.0F, var33, 3.0F, BlendMode.lerp(Icons.MAGENTA, Icons.PINK, var34));
      if (!var28) {
         var2.text(Fonts.SEMIBOLD, var24, var31 + 12.0F + 14.0F + 6.0F, var33, 9.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      float var38x = var31 + var29;
      long var39 = 115209614727285L;
      boolean var42x = this.g >= 0.0 && Y - this.g < this.n;
      float var43 = this.r.target(var42x ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));

      for (WingsStudioScreen.WingStudioState var45 : var26) {
         float var46 = this.u(var45, var12) ? 14.0F : 5.0F;
         long var47 = 115209614727285L;
         int var45x = var47 < 0L ? -1 : 1;
         if (this.u(var45, var12)) {
            var2.rect(var38x + var46 / 2.0F - 0.5F, var32 + 9.0F, 1.0F, 16.0F, 486539263);
         }

         float var51;
         float var52;
         boolean var53;
                   boolean var8;
          float var66;
          float var50;
          label221: {
             var66 = var38x + var46;
            var50 = this.u(var2, (char)var18, var45, var19, (short)var20);
            var51 = 24.0F;
            var52 = var32 + (34.0F - var51) / 2.0F;
            var45.G[0] = var66;
            var45.G[1] = var52;
            var45.G[2] = var50;
            var45.G[3] = var51;
            var53 = var45 != this.c || WingsSupport.moved();
            long var54 = 115209614727285L;
            var8 = false;
            if (var53) {
               long var56 = 115209614727284L;
               int var54x = var56 < 0L ? -1 : 1;
               if (!this.S) {
                  boolean var58 = var1.masked();
                  long var59 = 115209614727284L;
                  int var37 = var59 < 0L ? -1 : 1;
                  if (!var58 && G(var45.G, var21, (char)var23, var1.mouseX, var1.mouseY)) {
                     var8 = true;
                     break label221;
                  }
               }
            }

            var8 = false;
         }

         if (var8) {
            var1.claim(var45, Cursor.HAND);
         }

         float var55 = var45.p.target(var8 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         float var72 = var45.n.update(MotionStyle.TOGGLE.dt(var1.dt));
         var2.push();
         var2.scaleAround(1.0F - 0.05F * var72, var66 + var50 / 2.0F, var52 + var51 / 2.0F);
         boolean var57 = var45 == this.y && this.V;
         long var73 = 115209614727284L;
         int var37 = var73 < 0L ? -1 : 1;
         if (var45 == this.e) {
            long var61 = 115209614727284L;
            int var77 = var61 < 0L ? -1 : 1;
            var2.glow(var66, var52, var50, var51, var51 / 2.0F, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.22F + 0.2F * var55 + 0.15F * var43));
            var2.roundRectGradient(
               var66,
               var52,
               var50,
               var51,
               var51 / 2.0F,
               0.0F,
               BlendMode.withAlpha(Icons.INDIGO, 0.88F + 0.12F * var55),
               BlendMode.withAlpha(Icons.PURPLE, 0.88F + 0.12F * var55)
            );
            if (var43 > 0.01F) {
               long var64 = 115209614727285L;
               int var61x = var64 < 0L ? -1 : 1;
                               if (this.g >= 0.0) {
                   float var66p = T((float)((Y - this.g) / this.n), var10);
                   long var67 = 115209614727285L;
                   int var64x = var67 < 0L ? -1 : 1;
                   var2.pushClip(var66p, var52, var50, var51, var51 / 2.0F);
                   var2.rect(var66p, var52, var50 * var66p, var51, BlendMode.withAlpha(-1, 0.13F * var43));
                   var2.popClip();
                }
            }
         } else {
            long var74 = 115209614727284L;
            int var801 = var74 < 0L ? -1 : 1;
            if (var57) {
               long var77 = 115209614727285L;
               int var86 = var77 < 0L ? -1 : 1;
               var2.roundRectGradient(
                  var66,
                  var52,
                  var50,
                  var51,
                  var51 / 2.0F,
                  0.0F,
                  BlendMode.withAlpha(Icons.INDIGO, 0.7F + 0.2F * var55),
                  BlendMode.withAlpha(Icons.MAGENTA, 0.7F + 0.2F * var55)
               );
            } else {
               var2.roundRect(var66, var52, var50, var51, var51 / 2.0F, BlendMode.lerp(352321535, 654311423, var55));
            }
         }

         label203: {
            var2.roundRectOutline(var66, var52, var50, var51, var51 / 2.0F, 1.0F, BlendMode.lerp(452984831, 1124073471, var55));
            if (var45 != this.e) {
               long var75 = 115209614727284L;
               int var65 = var75 < 0L ? -1 : 1;
               if (!var57) {
                  var37 = var53 ? BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var55) : BlendMode.withAlpha(Icons.TEXT_3, 0.6F);
                  break label203;
               }
            }

            var37 = Icons.TEXT;
         }

         long var62 = 115209614727285L;
         int var82 = var62 < 0L ? -1 : 1;
         String var9;
         if (!var45.o.isEmpty() && (!this.k || !this.R.contains(var45))) {
            label187: {
               if (var45 == this.e) {
                  float var78;
                  var37 = (var78 = var43 - 0.5F) == 0.0F ? 0 : (var78 < 0.0F ? -1 : 1);
                  long var79 = 115209614727285L;
                  int var91 = var79 < 0L ? -1 : 1;
                  if (var37 > 0) {
                     var9 = "rotate-ccw";
                     break label187;
                  }
               }

               var9 = var45.E;
            }

            int var80x = var45 == this.x ? BlendMode.lerp(Icons.PURPLE, Icons.TEXT, var55) : var37;
            var2.icon(var9, var66 + 11.0F + 5.5F, var52 + var51 / 2.0F, 11.0F, var80x);
            var2.text(Fonts.MEDIUM, var45.o, var66 + 11.0F + 11.0F + 5.0F, var52 + var51 / 2.0F, 9.0F, var37, FontStyle.LEFT, FontWeight.MIDDLE);
         } else {
            var2.icon(var45.E, var66 + var50 / 2.0F, var52 + var51 / 2.0F, 12.0F, var37);
            long var65 = 115209614727285L;
            int var89 = var65 < 0L ? -1 : 1;
         }

         var2.pop();
         var38x = var66 + var50;
      }
   }

   private List<WingsStudioScreen.WingStudioState> j() {
      ArrayList var1 = new ArrayList();
      var1.add(this.e);
      var1.add(this.y);
      var1.addAll(this.R);
      var1.add(this.c);
      var1.add(this.x);
      return var1;
   }

   private boolean u(WingsStudioScreen.WingStudioState var1, long var2) {
      WingsState.ensureSourceClassInitialized();
      return var1 == this.e || var1 == this.c || !this.R.isEmpty() && var1 == this.R.getFirst();
   }

   private float n(long var1, Canvas var3, List<WingStudioState> var4) {
      long var5 = 43535052576281L;
      long var7 = 137235531479585L;
      byte var9 = 0;
      int var10 = 2094048026;
      int var11 = (int)(var7 << 48 >>> 48);
      WingsState.ensureSourceClassInitialized();
      float var12 = 0.0F;

      for (WingsStudioScreen.WingStudioState var14 : var4) {
         var12 += this.u(var3, (char)var9, var14, var10, (short)var11) + (this.u(var14, var5) ? 14 : 5);
      }

      return var12;
   }

   private float u(Canvas var1, char var2, WingsStudioScreen.WingStudioState var3, int var4, short var5) {
      WingsState.ensureSourceClassInitialized();
      return !var3.o.isEmpty() && (!this.k || !this.R.contains(var3)) ? 27.0F + var1.textWidth(Fonts.MEDIUM, var3.o, 9.0F) + 12.0F : 28.0F;
   }

   private void d(DrawContext var1, Canvas var2, float var3, long var4) {
      long var6 = 132148812597582L;
      long var8 = 32932398742284L;
      WingsState.ensureSourceClassInitialized();
      float var10 = this.I[0] + this.I[2] / 2.0F;
      float var11 = this.P - 22.0F - 34.0F - 8.0F - 24.0F + var3;
      String var12 = this.d.note();
      if (var12 != null) {
         float var13 = var2.textWidth(Fonts.MEDIUM, var12, 8.5F);
         float var14 = var13 + 40.0F;
         float var15 = var10 - var14 / 2.0F;
         this.i(var2, var15, var8, var11, var14);
         var2.icon("info", var15 + 16.0F, var11 + 12.0F, 11.0F, Icons.PINK);
         var2.text(Fonts.MEDIUM, var12, var15 + 28.0F, var11 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      } else {
         List var50 = this.g >= 0.0 ? this.p : this.d.timeline();
         int var51 = var50.size();
         if (var51 != 0) {
            float var52 = 18.0F;
            float[] var16 = new float[var51];
            float var17 = 0.0F;

            for (int var18 = 0; var18 < var51; var18++) {
               var16[var18] = 12.0F + var2.textWidth(Fonts.MEDIUM, ((WingsFactory.CosmeticEntry)var50.get(var18)).label(), 8.5F);
               var17 += var16[var18] + (var18 > 0 ? var52 : 0.0F);
            }

            long var53 = 99327846924480L;
            int var21 = var53 < 0L ? -1 : 1;
            float var21x = var17 + 28.0F;
            long var22 = 99327846924481L;
            int var24 = var22 < 0L ? -1 : 1;
            float var24x = this.t.value() <= 0.0F ? this.t.snap(var21x).value() : this.t.target(var21x).update(var1.dt);
            float var25 = var10 - var24x / 2.0F;
            this.U[0] = var25;
            this.U[1] = var11;
            this.U[2] = var24x;
            this.U[3] = 24.0F;
            this.i(var2, var25, var8, var11, var24x);
            float var26 = var11 + 12.0F;
            long var27 = 99327846924481L;
            int var30 = var27 < 0L ? -1 : 1;
            double var30x = this.g >= 0.0 ? Y - this.g : -1.0;
            float var32 = var10 - var17 / 2.0F;

            for (int var33 = 0; var33 < var51; var33++) {
               WingsFactory.CosmeticEntry var34 = (WingsFactory.CosmeticEntry)var50.get(var33);
               boolean var35 = var30x >= var34.at();
               long var36 = 99327846924480L;
               int var36x = var36 < 0L ? -1 : 1;
               float var38x = var35 && var33 < this.M.length ? (float)(Y - this.M[var33]) : 99.0F;
               float var39 = var35 ? (float)Math.exp(-var38x / 0.18F) : 0.0F;
               float var40 = var32 + 3.5F;
               if (var33 > 0) {
                  WingsFactory.CosmeticEntry var41 = (WingsFactory.CosmeticEntry)var50.get(var33 - 1);
                  long var42 = 99327846924480L;
                  int var42x = var42 < 0L ? -1 : 1;
                  float var44x = var32 - var52 + 3.0F;
                  long var45 = 99327846924481L;
                  int var45x = var45 < 0L ? -1 : 1;
                  float var48x = var32 - 3.0F;
                  var2.rect(var44x, var26 - 0.5F, var48x - var44x, 1.0F, 587202559);
                  if (var30x >= var41.at()) {
                     float var49 = T((float)((var30x - var41.at()) / Math.max(0.05F, var34.at() - var41.at())), var6);
                     var2.rect(var44x, var26 - 0.75F, (var48x - var44x) * var49, 1.5F, BlendMode.withAlpha(Icons.MAGENTA, 0.85F));
                  }
               }

               if (var39 > 0.01F) {
                  var2.glowCircle(var40, var26, 3.5F + 3.0F * var39, 9.0F, BlendMode.withAlpha(Icons.PINK, 0.55F * var39));
               }

               var2.circle(var40, var26, 3.0F + 1.2F * var39, var35 ? BlendMode.lerp(Icons.MAGENTA, -1, 0.5F * var39) : 1090519039);
               var2.text(Fonts.MEDIUM, var34.label(), var32 + 7.0F + 5.0F, var26, 8.5F, var35 ? Icons.TEXT : Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
               var32 += var16[var33] + var52;
            }
         }
      }
   }

   private void i(Canvas var1, float var2, long var3, float var5, float var6) {
      var1.shadow(var2, var5, var6, 24.0F, 12.0F, 16.0F, 0.0F, 0.0F, 4.0F, 1342177280);
      var1.roundRect(var2, var5, var6, 24.0F, 12.0F, Theme.tint(-703525600));
      var1.roundRectOutline(var2, var5, var6, 24.0F, 12.0F, 1.0F, 452984831);
   }

   private void I(DrawContext var1, Canvas var2, long var3, float var5, float var6) {
      long var7;
      boolean var21;
      label44: {
         var7 = 32932398742284L;
         WingsState.ensureSourceClassInitialized();
         if (this.a) {
            long var10 = 94557431463734L;
            int varCmp = var10 < 0L ? -1 : 1;
            if (!this.C) {
               var21 = WingsSupport.settled();
               long var13 = 94557431463733L;
               int var14 = var13 < 0L ? -1 : 1;
               if (var21 && (this.K < 0.0 || Y - this.K < 9.0)) {
                  var21 = true;
                  break label44;
               }
            }
         }

         var21 = false;
      }

      float var11 = this.L.target(var21 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt) * 0.6F);
      float var12;
      int varCmp = Float.compare(var12 = var11 - 0.01F, 0.0F);
      long var24 = 94557431463734L;
      int var16 = var24 < 0L ? -1 : 1;
      if (varCmp > 0) {
         String var16x = "Drag to look around  ·  Scroll to zoom  ·  Double-click to reset";
         float var17 = var2.textWidth(Fonts.MEDIUM, var16x, 8.5F);
         float var18 = var17 + 40.0F;
         float var19 = 22.0F - (1.0F - var11) * 6.0F;
         float var20 = Math.max(16.0F, (16.0F + this.s - 16.0F) / 2.0F - var18 / 2.0F);
         var2.pushAlpha(var11 / Math.max(var6, 0.01F) * var6);
         this.i(var2, var20, var7, var19, var18);
         var2.icon("mouse", var20 + 16.0F, var19 + 12.0F, 11.0F, Icons.PURPLE);
         var2.text(Fonts.MEDIUM, var16x, var20 + 28.0F, var19 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         var2.popAlpha();
      }
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      long var4 = 30588837145925L;
      long var6 = (var4 ^ 54002523037026L) >>> 16;
      int var8 = (int)((var4 ^ 54002523037026L) << 48 >>> 48);
      if (!this.a) {
         return false;
      } else if (this.I[2] > 0.0F && G(this.I, var6, (char)var8, var1, var2)) {
         if (var3 == 0) {
            for (WingsStudioScreen.WingStudioState var10 : this.j()) {
               if (G(var10.G, var6, (char)var8, var1, var2) && (var10 != this.c || WingsSupport.moved())) {
                  var10.n.snap(1.0F).target(0.0F);
                  var10.e.run();
                  return true;
               }
            }
         }

         return true;
      } else if (var3 > 1) {
         return false;
      } else {
         long var9 = System.currentTimeMillis();
         if (var3 == 0 && var9 - this.v < 320L) {
            this.v = 0L;
            if (WingsSupport.moved()) {
               WingsSupport.resetView();
               UiAudioPolicy.play("TAB", 1.1F);
            }

            return true;
         } else {
            this.v = var9;
            this.S = true;
            this.E = var1;
            this.B = var2;
            return true;
         }
      }
   }

   public void mouseDrag(float var1, float var2) {
      if (this.S) {
         float var3 = var1 - this.E;
         float var4 = var2 - this.B;
         this.E = var1;
         this.B = var2;
         if (var3 != 0.0F || var4 != 0.0F) {
            WingsSupport.drag(var3, var4);
            this.C = true;
            this.v = 0L;
         }
      }
   }

   public void mouseUp(float var1, float var2, int var3) {
      this.S = false;
   }

   public boolean scroll(float var1, float var2, double var3) {
      if (this.a && var3 != 0.0) {
         WingsSupport.zoom(var3);
         this.C = true;
         return true;
      } else {
         return false;
      }
   }

   public boolean keyPressed(int var1) {
      long var2 = 12849195505878L;
      int var4 = (int)((var2 ^ 108331071229811L) >>> 48);
      long var5 = (var2 ^ 108331071229811L) << 16 >>> 16;
      if (!this.a) {
         return false;
      } else {
         switch (var1) {
            case 32:
               this.e.n.snap(1.0F).target(0.0F);
               this.L((short)var4, var5);
               return true;
            case 76:
               this.y.e.run();
               return true;
            default:
               return false;
         }
      }
   }

   static WingsStudioScreen current() {
      return Z;
   }

   String describe() {
      return String.format(
         Locale.ROOT,
         "%s open %s lent %s playing %s loop %s clock %.2f, camera: %s",
         this.A.id(),
         this.a,
         this.o,
         this.g >= 0.0 ? String.format(Locale.ROOT, "%.2f", Y - this.g) : "no",
         this.V,
         Y,
         WingsSupport.describe()
      );
   }

   private static boolean G(float[] var0, long var1, char var3, float var4, float var5) {
      WingsState.ensureSourceClassInitialized();
      return var0[2] > 0.0F && var4 >= var0[0] && var4 <= var0[0] + var0[2] && var5 >= var0[1] && var5 <= var0[1] + var0[3];
   }

   private static float T(float var0, long var1) {
      WingsState.ensureSourceClassInitialized();
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   static void closeOwned() {
      long var0 = 102914870879222L;
      long var2 = var0 ^ 7520390402958L;
      if (Z != null) {
         Z.Y(var2, true);
      }

      WingsSupport.reset();
      F.clear();
      b.clear();
   }

   private record WingAction(double w, Consumer<class_746> O) {
      public double at() {
         return this.w;
      }

      public Consumer<class_746> action() {
         return this.O;
      }

      public double doubleState4() {
         return this.w;
      }

      public Consumer<class_746> consumerState27() {
         return this.O;
      }
   }

   private static final class WingStudioState {
      final String o;
      final String E;
      final Runnable e;
      final float[] G = new float[4];
      final Animator p = Animator.smooth(0.0F);
      final Animator n = Animator.snappy(0.0F);

      WingStudioState(String var1, String var2, Runnable var3) {
         this.o = var1;
         this.E = var2;
         this.e = var3;
      }
   }
}
