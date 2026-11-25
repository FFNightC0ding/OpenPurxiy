package dev.purxiy.modules.shockwave;

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
import dev.purxiy.ui.DrawContext.TypeE;
import dev.purxiy.ui.clickgui.Studio;
import dev.purxiy.ui.clickgui.Studio.TypeQ;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.class_310;
import net.minecraft.class_746;

public final class ShockwaveRenderer4 implements Studio {
   private static final float u = 34.0F;
   private static final float E = 24.0F;
   private static final long X = 320L;
   private static final float G = 0.9F;
   private static final float W = 0.25F;
   private static final float F = 0.55F;
   private static final Map<ModuleDescriptor, ShockwaveRenderer4> f = new IdentityHashMap<>();
   private static ShockwaveRenderer4 j;
   private static final List<ShockwaveRenderer4.TypeI> s = new ArrayList<>();
   private static double y;
   private static long O = 0L;
   private final ModuleDescriptor U;
   private final ShockwaveFactory x;
   private boolean l;
   private boolean w;
   private boolean o;
   private boolean g;
   private double n;
   private int p;
   private double P;
   private List<ShockwaveFactory.Data2> b;
   private double V;
   private float v;
   private int Q;
   private final double[] Z;
   private boolean J;
   private final ShockwaveRenderer4.TypeF t;
   private final ShockwaveRenderer4.TypeF Y;
   private final ShockwaveRenderer4.TypeF k;
   private final ShockwaveRenderer4.TypeF r;
   private final List<ShockwaveRenderer4.TypeF> I;
   private final float[] A;
   private final float[] M;
   private boolean C;
   private final Animator d;
   private final Animator D;
   private final Animator m;
   private final Animator N;
   private float a;
   private float L;
   private float z;
   private boolean q;
   private float i;
   private float h;
   private long R;
   private boolean H;
   private final Object c;
   public static int B = 0;
   private static final long[] S = new long[]{
      3888545727500598534L,
      -8453253959952777485L,
      7773901510259900436L,
      1290879094774064545L,
      6598314520569154661L,
      -7397116978430480027L,
      1665834189490140314L,
      5203284202346147714L,
      -7471738611300804440L,
      2947064412547993310L,
      5272022316445796656L,
      -650568213557498268L,
      -1345813925980242493L,
      -6464293326523589489L,
      1745008309261262581L,
      -8997793605414356296L,
      5553718089099230333L,
      8746916586051200217L
   };

   private ShockwaveRenderer4(ModuleDescriptor var1, ShockwaveFactory var2, long var3, int var5) {
      ShockwaveModule.ensureSourceClassInitialized();
      this.n = -1.0;
      this.P = -1.0;
      this.b = List.of();
      this.V = -1.0;
      this.v = 1.0F;
      this.Z = new double[16];
      this.I = new ArrayList<>();
      this.A = new float[4];
      this.M = new float[4];
      this.d = Animator.smooth(0.0F);
      this.D = new Animator(0.0F, 420.0F, 41.0F);
      this.m = Animator.smooth(0.0F);
      this.N = Animator.smooth(0.0F);
      this.c = new Object();
      this.U = var1;
      this.x = var2;
      this.t = new ShockwaveRenderer4.TypeF("Play", "play", () -> this.k(24626985875206L));
      this.Y = new ShockwaveRenderer4.TypeF("Loop", "repeat", () -> {
         long var1x = 24626985875206L;
         ShockwaveModule.ensureSourceClassInitialized();
         boolean var3x = false;
         this.J = !this.J;
         long var4 = 69690936498316L;
         int var7x = var4 < 0L ? -1 : 1;
         UiAudioPolicy.play(this.J ? "TOGGLE_ON" : "TOGGLE_OFF");
         long var7xx = 69690936498317L;
         int var8 = var7xx < 0L ? -1 : 1;
         if (this.J && this.V < 0.0) {
            this.k(var1x);
         }
      });

      for (ShockwaveFactory.Data var7 : var2.w) {
         this.I.add(new ShockwaveRenderer4.TypeF(var7.label(), var7.icon(), () -> {
            long var2x = 114863906384669L;
            long var4 = var2x ^ 76958829588561L;
            this.S(var7, var4);
         }));
      }

      this.k = new ShockwaveRenderer4.TypeF("", "rotate-ccw", () -> {
         ShockwaveSupport2.resetView();
         UiAudioPolicy.play("TAB", 1.1F);
      });
      this.r = new ShockwaveRenderer4.TypeF("Done", "check", () -> {
         this.o = true;
         UiAudioPolicy.play("CLICK");
      });
   }

   public static ShockwaveRenderer4 of(ModuleDescriptor var0) {
      long var1 = 5855170955175L;
      long var3 = (var1 ^ 32311295652638L) >>> 32;
      int var5 = (int)((var1 ^ 32311295652638L) << 32 >>> 32);
      ShockwaveRenderer4 var6 = f.get(var0);
      if (var6 == null) {
         ShockwaveFactory var7 = ShockwaveFactory.of(var0);
         if (var7 == null) {
            return null;
         }

         var6 = new ShockwaveRenderer4(var0, var7, var3, var5);
         f.put(var0, var6);
      }

      return var6;
   }

   static boolean showing(ModuleDescriptor var0) {
      ShockwaveRenderer4 var1 = j;
      return var1 != null && var1.U == var0 && var1.w;
   }

   static void later(float var0, Consumer<class_746> var1) {
      s.add(new ShockwaveRenderer4.TypeI(y + var0, var1));
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
         && (j == null || j == this || !j.w);
   }

   public TypeQ dock() {
      return TypeQ.RIGHT;
   }

   public boolean keepsWorldRunning() {
      return true;
   }

   public void setOpen(boolean var1) {
      long var2 = 36245301445039L;
      long var4 = var2 ^ 88317480584601L;
      long var6 = var2 ^ 83680630829095L;
      class_310 var8 = class_310.method_1551();
      if (var1 != this.l) {
         this.l = var1;
         this.q = false;
         if (var1) {
            if (j != null && j != this) {
               j.Z(var4, true);
            }

            j = this;
            if (!this.w) {
               this.w = true;
            }

            ShockwaveSupport2.open();
            this.g = false;
            this.n = -1.0;
            this.p = this.h(var6);
            this.P = -1.0;
            this.H = false;
            this.d.snap(0.0F);
            this.m.snap(0.0F);
            this.o();
         } else {
            this.o();
            s.clear();
            this.J = false;
            ShockwaveSupport2.close(CosmeticRegistry.currentOwner() == null);
            if (var8.field_1724 != null) {
               this.x.closed(var8.field_1724);
            }
         }
      }
   }

   public boolean consumeCloseRequest() {
      boolean var1 = this.o;
      this.o = false;
      return var1;
   }

   private void Z(long var1, boolean var3) {
      ShockwaveModule.ensureSourceClassInitialized();
      if (this.l) {
         long var4 = 123849683207221L;
         int var7 = var4 < 0L ? -1 : 1;
         if (!var3) {
            return;
         }
      }

      if (var3) {
         long var8 = 123849683207222L;
         int var8x = var8 < 0L ? -1 : 1;
         if (this.l) {
            this.l = false;
            this.o();
            class_310 var7 = class_310.method_1551();
            if (var7.field_1724 != null) {
               this.x.closed(var7.field_1724);
            }

            ShockwaveSupport2.reset();
         }
      }

      if (this.w) {
         this.w = false;
      }

      if (j == this) {
         j = null;
      }
   }

   static void tick(class_310 var0) {
      long var1 = 105975413583703L;
      long var3 = var1 ^ 18443977763681L;
      ShockwaveRenderer4 var5 = j;
      if (var5 != null) {
         if (var0.field_1687 != null && var0.field_1724 != null) {
            if (var5.l && CosmeticRegistry.currentOwner() == null) {
               var5.setOpen(false);
            }

            if (!var5.l && ShockwaveSupport2.active() && CosmeticRegistry.currentOwner() == null) {
               ShockwaveSupport2.hurry();
            }

            if (!var5.l && !ShockwaveSupport2.active()) {
               var5.Z(var3, false);
            }
         } else {
            var5.Z(var3, true);
         }
      }
   }

   private void k(long var1) {
      ShockwaveModule.ensureSourceClassInitialized();
      class_310 var3 = class_310.method_1551();
      if (var3.field_1724 != null) {
         long var4 = 24626985875206L;
         int var7 = var4 < 0L ? -1 : 1;
         String var7x = this.x.note();
         long var8 = 24626985875205L;
         int var9 = var8 < 0L ? -1 : 1;
         if (var7x == null) {
            this.b = this.x.timeline();
            this.v = Math.max(0.5F, this.x.length());
            this.V = y;
            this.Q = 0;
            UiAudioPolicy.play("SELECT", 1.05F, 0.7F);
            return;
         }
      }

      UiAudioPolicy.play("DENY");
   }

   private void S(ShockwaveFactory.Data var1, long var2) {
      ShockwaveModule.ensureSourceClassInitialized();
      class_310 var4 = class_310.method_1551();
      if (var4.field_1724 != null && this.x.note() == null) {
         var1.action().accept(var4.field_1724);
      } else {
         UiAudioPolicy.play("DENY");
      }
   }

   private int h(long var1) {
      ShockwaveModule.ensureSourceClassInitialized();
      int var3 = 1;

      for (Setting var5 : this.U.settings()) {
         try {
            long var6 = 119762715095432L;
            int var8 = var6 < 0L ? -1 : 1;
            var3 = var3 * 31 + var5.save().hashCode();
         } catch (RuntimeException var91) {
            var3 *= 31;
         }
      }

      return var3;
   }

   private void o() {
      this.V = -1.0;
      this.Q = 0;
      this.b = List.of();
   }

   private void a(int var1, int var2, int var3) {
      long var4 = 75650647972088L;
      long var6 = var4 ^ 90882099474430L;
      long var8 = var4 ^ 44125016090992L;
      ShockwaveModule.ensureSourceClassInitialized();
      long var10 = System.nanoTime();
      float var12 = O == 0L ? 0.0F : Math.min(0.1F, (float)(var10 - O) / 1.0E9F);
      O = var10;
      y += var12;
      class_746 var13 = class_310.method_1551().field_1724;
      if (var13 != null) {
         short var14 = 17613;
         if (this.V >= 0.0) {
            double var15;
            for (var15 = y - this.V; this.Q < this.b.size() && this.b.get(this.Q).at() <= var15; this.b.get(this.Q++).action().accept(var13)) {
               if (this.Q < this.Z.length) {
                  this.Z[this.Q] = y;
               }
            }

            int var17 = 55544;
            double var18;
            var17 = (var18 = var15 - (this.v + (this.J ? 0.9F : 0.0F))) == 0.0 ? 0 : (var18 < 0.0 ? -1 : 1);
            var14 = 17613;
            if (var17 >= 0) {
               if (this.J && this.l) {
                  this.k(var6);
               } else if (var15 >= this.v + 1.2) {
                  this.o();
               }
            }
         }

         Iterator var23 = s.iterator();

         while (var23.hasNext()) {
            ShockwaveRenderer4.TypeI var16 = (ShockwaveRenderer4.TypeI)var23.next();
            if (var16.X <= y) {
               var23.remove();
               var16.U.accept(var13);
            }
         }

         char var24 = '\ud8f8';
         int var28 = this.h(var8);
         if (var28 != this.p) {
            this.p = var28;
            this.P = y;
         }

         if (this.P >= 0.0 && y - this.P >= 0.55F) {
            this.P = -1.0;
            var14 = 17613;
            boolean var29 = this.V >= 0.0 && y - this.V < this.v;
            if (this.l) {
               var24 = '\ud8f8';
               if (this.g && !var29 && this.x.note() == null) {
                  this.k(var6);
               }
            }
         }

         var14 = 17613;
         if (this.l && ShockwaveSupport2.settled()) {
            if (this.n < 0.0) {
               this.n = y;
            }

            if (!this.g) {
               var24 = '짿';
               if (y - this.n >= 0.25) {
                  this.g = true;
                  if (this.V < 0.0 && this.x.note() == null) {
                     this.k(var6);
                  }
               }
            }
         }
      }
   }

   public void render(DrawContext var1, float var2, float var3) {
      long var4 = 103533223925385L;
      long var6 = var4 ^ 90735520836873L;
      int var8 = (int)((var4 ^ 90735520836873L) >>> 48);
      int var9 = (int)((var4 ^ 90735520836873L) << 16 >>> 32);
      int var10 = (int)(var6 << 48 >>> 48);
      long var11 = var4 ^ 80305236313416L;
      long var13 = var4 ^ 9520707766462L;
      var6 = var4 ^ 29568651431537L;
      int var15 = (int)((var4 ^ 29568651431537L) >>> 32);
      int var16 = (int)((var4 ^ 29568651431537L) << 32 >>> 48);
      int var17 = (int)(var6 << 48 >>> 48);
      long var18 = var4 ^ 64973117499492L;
      this.a(var15, var16, var17);
      Canvas var20 = var1.canvas;
      this.a = var3;
      this.L = var20.width();
      this.z = var20.height();
      float var21 = 16.0F;
      float var22 = Math.max(var21 + 200.0F, var3 - 16.0F);
      float var23 = (var21 + var22) / 2.0F;
      float var24 = 110.0F;
      ShockwaveSupport2.frame(var23 / this.L * 2.0F - 1.0F, 1.0F - (this.z - var24) / this.z);
      float var25 = Easing.SMOOTHSTEP.apply(U((ShockwaveSupport2.progress() - 0.62F) / 0.38F, var11));
      float var26 = this.d.target(this.l ? var25 : 0.0F).update(MotionStyle.MENU.dt(var1.dt)) * var2;
      if (this.q) {
         var1.claim(this.c, TypeE.MOVE);
      }

      if (var26 <= 0.01F) {
         this.A[2] = 0.0F;
      } else {
         var20.pushAlpha(var26);
         float var27 = (1.0F - var26) * 18.0F;
         this.e((short)var8, var1, var20, var21, var22, var9, (short)var10, var27);
         this.S(var1, var20, var18, var27);
         this.e(var1, var13, var20, var21, var26);
         var20.popAlpha();
      }
   }

   private void e(short var1, DrawContext var2, Canvas var3, float var4, float var5, int var6, short var7, float var8) {
      long var11 = 13948836658048L;
      long var13 = var11 ^ 56179041700620L;
      long var15 = var11 ^ 138277430682446L;
      long var17 = var11 ^ 30303266105409L;
      long var19 = (var11 ^ 388871802666L) >>> 8;
      int var21 = (int)((var11 ^ 388871802666L) << 56 >>> 56);
      long var22 = var11 ^ 41211654178207L;
      int var24 = (int)((var11 ^ 41211654178207L) >>> 48);
      int var25 = (int)((var11 ^ 41211654178207L) << 16 >>> 32);
      int var26 = (int)(var22 << 48 >>> 48);
      ShockwaveModule.ensureSourceClassInitialized();
      String var27 = "Live preview";
      float var28 = var3.textWidth(Fonts.SEMIBOLD, var27, 9.5F);
      List var29 = this.J();
      this.C = false;
      float var30 = this.k(var3, var13, var29);
      boolean var31 = 32.0F + var28 + var30 + 10.0F > var5 - 16.0F;
      if (var31 && 26.0F + var30 + 10.0F > var5 - 16.0F) {
         this.C = true;
         var30 = this.k(var3, var13, var29);
      }

      float var32 = var31 ? 26.0F : 32.0F + var28;
      float var33 = var32 + var30 + 10.0F;
      float var34 = Math.max(16.0F, (16.0F + var5) / 2.0F - var33 / 2.0F);
      float var35 = this.z - 22.0F - 34.0F + var8;
      this.A[0] = var34;
      this.A[1] = var35 - 24.0F - 8.0F;
      this.A[2] = var33;
      this.A[3] = 66.0F;
      var3.shadow(var34, var35, var33, 34.0F, 17.0F, 22.0F, 0.0F, 0.0F, 6.0F, 1879048192);
      var3.roundRect(var34, var35, var33, 34.0F, 17.0F, Theme.tint(-300872416));
      var3.roundRectOutlineGradient(var34, var35, var33, 34.0F, 17.0F, 1.0F, 90.0F, 788529151, 184549375);
      float var36 = var35 + 17.0F;
      float var37 = MotionStyle.reduced() ? 0.7F : 0.55F + 0.45F * (float)Math.sin(var2.time * 3.2F);
      int var38 = 0;
      var3.glowCircle(var34 + 12.0F + 6.0F, var36, 3.0F, 7.0F, BlendMode.withAlpha(Icons.PINK, 0.45F * var37));
      var3.circle(var34 + 12.0F + 6.0F, var36, 3.0F, BlendMode.lerp(Icons.MAGENTA, Icons.PINK, var37));
      if (!var31) {
         var3.text(Fonts.SEMIBOLD, var27, var34 + 12.0F + 14.0F + 6.0F, var36, 9.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      float var39 = var34 + var32;
      int var40 = 212842356;
      boolean var41 = this.V >= 0.0 && y - this.V < this.v;
      float var42 = this.N.target(var41 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var2.dt));

      for (ShockwaveRenderer4.TypeF var44 : var29) {
         float var45 = this.r(var44, var15) ? 14.0F : 5.0F;
         if (this.r(var44, var15)) {
            var3.rect(var39 + var45 / 2.0F - 0.5F, var35 + 9.0F, 1.0F, 16.0F, 486539263);
         }

         boolean var10;
         float var46;
         float var47;
         float var48;
         boolean var49;
         label144: {
            var39 += var45;
            var46 = this.m(var19, var3, (byte)var21, var44);
            var47 = 24.0F;
            var48 = var35 + (34.0F - var47) / 2.0F;
            var44.e[0] = var39;
            var44.e[1] = var48;
            var44.e[2] = var46;
            var44.e[3] = var47;
            var49 = var44 != this.k || ShockwaveSupport2.moved();
            var40 = 212842356;
            if (var49) {
               var40 = 212842356;
               if (!this.q) {
                  boolean var50 = var2.masked();
                  if (!var50 && g(var44.e, (char)var24, var25, var2.mouseX, var2.mouseY, (char)var26)) {
                     var10 = true;
                     break label144;
                  }
               }
            }

            var10 = false;
         }

         if (var10) {
            var2.claim(var44, TypeE.HAND);
         }

         float var51 = var44.y.target(var10 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var2.dt));
         float var52 = var44.Y.update(MotionStyle.TOGGLE.dt(var2.dt));
         var3.push();
         var3.scaleAround(1.0F - 0.05F * var52, var39 + var46 / 2.0F, var48 + var47 / 2.0F);
         boolean var53 = var44 == this.Y && this.J;
         if (var44 == this.t) {
            var3.glow(var39, var48, var46, var47, var47 / 2.0F, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.22F + 0.2F * var51 + 0.15F * var42));
            var3.roundRectGradient(
               var39,
               var48,
               var46,
               var47,
               var47 / 2.0F,
               0.0F,
               BlendMode.withAlpha(Icons.INDIGO, 0.88F + 0.12F * var51),
               BlendMode.withAlpha(Icons.PURPLE, 0.88F + 0.12F * var51)
            );
            if (var42 > 0.01F && this.V >= 0.0) {
               float var54 = U((float)((y - this.V) / this.v), var17);
               var3.pushClip(var39, var48, var46, var47, var47 / 2.0F);
               var3.rect(var39, var48, var46 * var54, var47, BlendMode.withAlpha(-1, 0.13F * var42));
               var3.popClip();
            }
         } else if (var53) {
            var3.roundRectGradient(
               var39,
               var48,
               var46,
               var47,
               var47 / 2.0F,
               0.0F,
               BlendMode.withAlpha(Icons.INDIGO, 0.7F + 0.2F * var51),
               BlendMode.withAlpha(Icons.MAGENTA, 0.7F + 0.2F * var51)
            );
         } else {
            var3.roundRect(var39, var48, var46, var47, var47 / 2.0F, BlendMode.lerp(352321535, 654311423, var51));
         }

         var3.roundRectOutline(var39, var48, var46, var47, var47 / 2.0F, 1.0F, BlendMode.lerp(452984831, 1124073471, var51));
         if (var44 != this.t && !var53) {
            var38 = var49 ? BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var51) : BlendMode.withAlpha(Icons.TEXT_3, 0.6F);
         } else {
            var38 = Icons.TEXT;
         }

         if (!var44.R.isEmpty() && (!this.C || !this.I.contains(var44))) {
            String var9;
            label114: {
               if (var44 == this.t) {
                  float var55;
                  var38 = (var55 = var42 - 0.5F) == 0.0F ? 0 : (var55 < 0.0F ? -1 : 1);
                  if (var38 > 0) {
                     var9 = "rotate-ccw";
                     break label114;
                  }
               }

               var9 = var44.b;
            }

            int var56 = var44 == this.r ? BlendMode.lerp(Icons.PURPLE, Icons.TEXT, var51) : var38;
            var3.icon(var9, var39 + 11.0F + 5.5F, var48 + var47 / 2.0F, 11.0F, var56);
            var3.text(Fonts.MEDIUM, var44.R, var39 + 11.0F + 11.0F + 5.0F, var48 + var47 / 2.0F, 9.0F, var38, FontStyle.LEFT, FontWeight.MIDDLE);
         } else {
            var3.icon(var44.b, var39 + var46 / 2.0F, var48 + var47 / 2.0F, 12.0F, var38);
         }

         var3.pop();
         var39 += var46;
      }
   }

   private List<ShockwaveRenderer4.TypeF> J() {
      ArrayList var1 = new ArrayList();
      var1.add(this.t);
      var1.add(this.Y);
      var1.addAll(this.I);
      var1.add(this.k);
      var1.add(this.r);
      return var1;
   }

   private boolean r(ShockwaveRenderer4.TypeF var1, long var2) {
      ShockwaveModule.ensureSourceClassInitialized();
      return var1 == this.t || var1 == this.k || !this.I.isEmpty() && var1 == this.I.getFirst();
   }

   private float k(Canvas var1, long var2, List var4) {
      long var5 = 124711058462926L;
      long var7 = 55654081780L;
      short var9 = 170;
      ShockwaveModule.ensureSourceClassInitialized();
      float var10 = 0.0F;

      for (ShockwaveRenderer4.TypeF var12 : var4) {
         var10 += this.m(var7, var1, (byte)var9, var12) + (this.r(var12, var5) ? 14 : 5);
      }

      return var10;
   }

   private float m(long var1, Canvas var3, byte var4, ShockwaveRenderer4.TypeF var5) {
      ShockwaveModule.ensureSourceClassInitialized();
      return !var5.R.isEmpty() && (!this.C || !this.I.contains(var5)) ? 27.0F + var3.textWidth(Fonts.MEDIUM, var5.R, 9.0F) + 12.0F : 28.0F;
   }

   private void S(DrawContext var1, Canvas var2, long var3, float var5) {
      long var6 = 25427013620673L;
      short var8 = 18973;
      int var9 = -1532843806;
      float var10 = this.A[0] + this.A[2] / 2.0F;
      float var11 = this.z - 22.0F - 34.0F - 8.0F - 24.0F + var5;
      ShockwaveModule.ensureSourceClassInitialized();
      String var12 = this.x.note();
      if (var12 != null) {
         float var13 = var2.textWidth(Fonts.MEDIUM, var12, 8.5F);
         float var14 = var13 + 40.0F;
         float var15 = var10 - var14 / 2.0F;
         this.Y(var8, var9, var2, var15, var11, var14);
         var2.icon("info", var15 + 16.0F, var11 + 12.0F, 11.0F, Icons.PINK);
         var2.text(Fonts.MEDIUM, var12, var15 + 28.0F, var11 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      } else {
         List var50 = this.V >= 0.0 ? this.b : this.x.timeline();
         int var51 = var50.size();
         if (var51 != 0) {
            float var52 = 18.0F;
            float[] var16 = new float[var51];
            float var17 = 0.0F;

            for (int var18 = 0; var18 < var51; var18++) {
               var16[var18] = 12.0F + var2.textWidth(Fonts.MEDIUM, ((ShockwaveFactory.Data2)var50.get(var18)).label(), 8.5F);
               var17 += var16[var18] + (var18 > 0 ? var52 : 0.0F);
            }

            long var53 = 111317207645932L;
            int var21 = var53 < 0L ? -1 : 1;
            float var21x = var17 + 28.0F;
            long var22 = 111317207645933L;
            int var25 = var22 < 0L ? -1 : 1;
            float var25x = this.D.value() <= 0.0F ? this.D.snap(var21x).value() : this.D.target(var21x).update(var1.dt);
            float var26 = var10 - var25x / 2.0F;
            this.M[0] = var26;
            this.M[1] = var11;
            this.M[2] = var25x;
            this.M[3] = 24.0F;
            this.Y(var8, var9, var2, var26, var11, var25x);
            float var27 = var11 + 12.0F;
            long var28 = 111317207645932L;
            int var28x = var28 < 0L ? -1 : 1;
            double var31x = this.V >= 0.0 ? y - this.V : -1.0;
            float var33 = var10 - var17 / 2.0F;

            for (int var34 = 0; var34 < var51; var34++) {
               ShockwaveFactory.Data2 var35 = (ShockwaveFactory.Data2)var50.get(var34);
               boolean var36 = var31x >= var35.at();
               long var37 = 111317207645933L;
               int var36x = var37 < 0L ? -1 : 1;
               float var39x = var36 && var34 < this.Z.length ? (float)(y - this.Z[var34]) : 99.0F;
               float var40 = var36 ? (float)Math.exp(-var39x / 0.18F) : 0.0F;
               float var41 = var33 + 3.5F;
               if (var34 > 0) {
                  ShockwaveFactory.Data2 var42 = (ShockwaveFactory.Data2)var50.get(var34 - 1);
                  long var43 = 111317207645932L;
                  int var42x = var43 < 0L ? -1 : 1;
                  float var45x = var33 - var52 + 3.0F;
                  float var46 = var33 - 3.0F;
                  var2.rect(var45x, var27 - 0.5F, var46 - var45x, 1.0F, 587202559);
                  long var47 = 111317207645932L;
                  int var46x = var47 < 0L ? -1 : 1;
                  if (var31x >= var42.at()) {
                     float var49x = U((float)((var31x - var42.at()) / Math.max(0.05F, var35.at() - var42.at())), var6);
                     var2.rect(var45x, var27 - 0.75F, (var46 - var45x) * var49x, 1.5F, BlendMode.withAlpha(Icons.MAGENTA, 0.85F));
                  }
               }

               if (var40 > 0.01F) {
                  var2.glowCircle(var41, var27, 3.5F + 3.0F * var40, 9.0F, BlendMode.withAlpha(Icons.PINK, 0.55F * var40));
               }

               var2.circle(var41, var27, 3.0F + 1.2F * var40, var36 ? BlendMode.lerp(Icons.MAGENTA, -1, 0.5F * var40) : 1090519039);
               var2.text(Fonts.MEDIUM, var35.label(), var33 + 7.0F + 5.0F, var27, 8.5F, var36 ? Icons.TEXT : Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
               var33 += var16[var34] + var52;
            }
         }
      }
   }

   private void Y(int var1, int var2, Canvas var3, float var4, float var5, float var6) {
      var3.shadow(var4, var5, var6, 24.0F, 12.0F, 16.0F, 0.0F, 0.0F, 4.0F, 1342177280);
      var3.roundRect(var4, var5, var6, 24.0F, 12.0F, Theme.tint(-703525600));
      var3.roundRectOutline(var4, var5, var6, 24.0F, 12.0F, 1.0F, 452984831);
   }

   private void e(DrawContext var1, long var2, Canvas var4, float var5, float var6) {
      short var7;
      int var8;
      boolean var21;
      label44: {
         var7 = 18973;
         var8 = -1532843806;
         ShockwaveModule.ensureSourceClassInitialized();
         if (this.l) {
            long var10 = 95112108400183L;
            int varCmp = var10 < 0L ? -1 : 1;
            if (!this.H) {
               var21 = ShockwaveSupport2.settled();
               long var13 = 95112108400182L;
               int var14 = var13 < 0L ? -1 : 1;
               if (var21 && (this.n < 0.0 || y - this.n < 9.0)) {
                  var21 = true;
                  break label44;
               }
            }
         }

         var21 = false;
      }

      float var11 = this.m.target(var21 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt) * 0.6F);
      float var12;
      int varCmp = Float.compare(var12 = var11 - 0.01F, 0.0F);
      long var24 = 95112108400183L;
      int var16 = var24 < 0L ? -1 : 1;
      if (varCmp > 0) {
         String var16x = "Drag to look around  ·  Scroll to zoom  ·  Double-click to reset";
         float var17 = var4.textWidth(Fonts.MEDIUM, var16x, 8.5F);
         float var18 = var17 + 40.0F;
         float var19 = 22.0F - (1.0F - var11) * 6.0F;
         float var20 = Math.max(16.0F, (16.0F + this.a - 16.0F) / 2.0F - var18 / 2.0F);
         var4.pushAlpha(var11 / Math.max(var6, 0.01F) * var6);
         this.Y(var7, var8, var4, var20, var19, var18);
         var4.icon("mouse", var20 + 16.0F, var19 + 12.0F, 11.0F, Icons.PURPLE);
         var4.text(Fonts.MEDIUM, var16x, var20 + 28.0F, var19 + 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         var4.popAlpha();
      }
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      long var4 = 127262004092037L;
      long var6 = var4 ^ 99413997003418L;
      int var8 = (int)((var4 ^ 99413997003418L) >>> 48);
      int var9 = (int)((var4 ^ 99413997003418L) << 16 >>> 32);
      int var10 = (int)(var6 << 48 >>> 48);
      if (!this.l) {
         return false;
      } else if (this.A[2] > 0.0F && g(this.A, (char)var8, var9, var1, var2, (char)var10)) {
         if (var3 == 0) {
            for (ShockwaveRenderer4.TypeF var12 : this.J()) {
               if (g(var12.e, (char)var8, var9, var1, var2, (char)var10) && (var12 != this.k || ShockwaveSupport2.moved())) {
                  var12.Y.snap(1.0F).target(0.0F);
                  var12.U.run();
                  return true;
               }
            }
         }

         return true;
      } else if (var3 > 1) {
         return false;
      } else {
         long var11 = System.currentTimeMillis();
         if (var3 == 0 && var11 - this.R < 320L) {
            this.R = 0L;
            if (ShockwaveSupport2.moved()) {
               ShockwaveSupport2.resetView();
               UiAudioPolicy.play("TAB", 1.1F);
            }

            return true;
         } else {
            this.R = var11;
            this.q = true;
            this.i = var1;
            this.h = var2;
            return true;
         }
      }
   }

   public void mouseDrag(float var1, float var2) {
      if (this.q) {
         float var3 = var1 - this.i;
         float var4 = var2 - this.h;
         this.i = var1;
         this.h = var2;
         if (var3 != 0.0F || var4 != 0.0F) {
            ShockwaveSupport2.drag(var3, var4);
            this.H = true;
            this.R = 0L;
         }
      }
   }

   public void mouseUp(float var1, float var2, int var3) {
      this.q = false;
   }

   public boolean scroll(float var1, float var2, double var3) {
      if (this.l && var3 != 0.0) {
         ShockwaveSupport2.zoom(var3);
         this.H = true;
         return true;
      } else {
         return false;
      }
   }

   public boolean keyPressed(int var1) {
      long var2 = 73024856355970L;
      long var4 = var2 ^ 92425651602308L;
      if (!this.l) {
         return false;
      } else {
         switch (var1) {
            case 32:
               this.t.Y.snap(1.0F).target(0.0F);
               this.k(var4);
               return true;
            case 76:
               this.Y.U.run();
               return true;
            default:
               return false;
         }
      }
   }

   static ShockwaveRenderer4 current() {
      return j;
   }

   String describe() {
      return String.format(
         Locale.ROOT,
         "%s open %s lent %s playing %s loop %s clock %.2f, camera: %s",
         this.U.id(),
         this.l,
         this.w,
         this.V >= 0.0 ? String.format(Locale.ROOT, "%.2f", y - this.V) : "no",
         this.J,
         y,
         ShockwaveSupport2.describe()
      );
   }

   private static boolean g(float[] var0, char var1, int var2, float var3, float var4, char var5) {
      ShockwaveModule.ensureSourceClassInitialized();
      return var0[2] > 0.0F && var3 >= var0[0] && var3 <= var0[0] + var0[2] && var4 >= var0[1] && var4 <= var0[1] + var0[3];
   }

   private static float U(float var0, long var1) {
      ShockwaveModule.ensureSourceClassInitialized();
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   static void closeOwned() {
      long var0 = 18585492282430L;
      long var2 = var0 ^ 105842052814856L;
      if (j != null) {
         j.Z(var2, true);
      }

      ShockwaveSupport2.reset();
      f.clear();
      s.clear();
   }

   private static final class TypeF {
      final String R;
      final String b;
      final Runnable U;
      final float[] e = new float[4];
      final Animator y = Animator.smooth(0.0F);
      final Animator Y = Animator.snappy(0.0F);

      TypeF(String var1, String var2, Runnable var3) {
         this.R = var1;
         this.b = var2;
         this.U = var3;
      }
   }

   private record TypeI(double X, Consumer<class_746> U) {
      public double at() {
         return this.X;
      }

      public Consumer<class_746> action() {
         return this.U;
      }

      public double doubleState19() {
         return this.X;
      }

      public Consumer<class_746> consumerState26() {
         return this.U;
      }
   }
}
