package dev.purxiy.modules.hand;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.IntegerSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.Setting;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleClick;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.ui.DrawContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.class_11908;
import net.minecraft.class_11909;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_357;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_3675.class_307;

final class HandMovementScreen extends class_437 {
   private final HandMovementModule E;
   private final HandMovementStudioScreen L;
   private final DrawContext t = new DrawContext();
   private final List<Runnable> p = new ArrayList<>();
   private List<Setting<?>> q = List.of();
   private List<Setting<?>> I = List.of();
   private IntegerSetting T;
   private int Y;
   private int r;
   private int U;
   private int s;
   private boolean f;
   private boolean M;
   private boolean C;
   private static final long[] c = new long[]{
      3356408363585749823L,
      7483995263298440492L,
      2775239288007696017L,
      -1594184275664022404L,
      6958504587025229843L,
      3351887052442406392L,
      6341441851581376335L,
      4445957048817286441L,
      -6826357450816641166L,
      -4224457080278213276L,
      -5496211078080139639L,
      -8445676075291918971L,
      8108536233904919274L,
      8001367619969126346L,
      3200904575989173276L,
      -8543766053736457676L,
      -132144622301552301L,
      7617133318979050790L
   };
   private static final Integer[] d = new Integer[18];

   HandMovementScreen(HandMovementModule var1, HandMovementStudioScreen var2) {
      super(class_2561.method_43470(var2.title()));
      this.E = var1;
      this.L = var2;
   }

   protected void method_25426() {
      long var1 = 67018858666165L;
      long var3 = var1 ^ 120271951160165L;
      if (!this.M) {
         this.L.setOpen(true);
         this.M = true;
      }

      this.Y(var3);
   }

   private List<Setting<?>> K() {
      return this.E.settings().stream().filter(Setting::visible).toList();
   }

   private void Y(long var1) {
      int var3;
      int var4;
      int var5;
      int var6;
      int var7;
      int var8;
      int var9;
      int var10;
      int var11;
      int var12;
      int var13;
      int var14;
      long var15;
      long var17;
      byte var19;
      int var20;
      HandMovementScreen var21;
      label101: {
         long var22 = var1 ^ 13983934603577L;
         var3 = (int)((var1 ^ 13983934603577L) >>> 48);
         var4 = (int)((var1 ^ 13983934603577L) << 16 >>> 48);
         var5 = (int)(var22 << 32 >>> 32);
         long var24 = var1 ^ 102750575225057L;
         var6 = (int)((var1 ^ 102750575225057L) >>> 48);
         var7 = (int)((var1 ^ 102750575225057L) << 16 >>> 32);
         var8 = (int)(var24 << 48 >>> 48);
         long var26 = var1 ^ 9955389184162L;
         var9 = (int)((var1 ^ 9955389184162L) >>> 48);
         var10 = (int)((var1 ^ 9955389184162L) << 16 >>> 48);
         var11 = (int)(var26 << 32 >>> 32);
         long var28 = var1 ^ 124983603530718L;
         var12 = (int)((var1 ^ 124983603530718L) >>> 32);
         var13 = (int)((var1 ^ 124983603530718L) << 32 >>> 48);
         var14 = (int)(var28 << 48 >>> 48);
         var15 = var1 ^ 5442125058913L;
         var17 = var1 ^ 113934489171400L;
         this.method_37067();
         this.p.clear();
         SwingStyle.ensureSourceClassInitialized();
         this.T = null;
         this.q = this.K();
         this.f = this.L.dock().right();
         var19 = 0;
         var21 = this;
         int var30 = Math.min(200, Math.max(84, this.field_22789 / 3));
         if (var1 > 0L) {
            this.U = var30;
            var21 = this;
            if (this.f) {
               var20 = this.field_22789 - this.U - 8;
               break label101;
            }

            int var431 = 22065;
         }

         var20 = 8;
      }

      var21.r = var20;
      int var39 = Math.max(1, Math.min(11, (this.field_22790 - 61) / 22));
      int var23 = Math.max(0, (this.q.size() - 1) / var39);
      long var40 = 7663372252511823506L ^ var1;
      this.Y = Math.clamp((long)this.Y, 0, var23);
      this.I = this.q.subList(this.Y * var39, Math.min(this.q.size(), (this.Y + 1) * var39));
      int var41 = Math.max(a(7949, var40), this.U / 2 - 6);
      int var27 = this.r + this.U - var41 - 5;
      int var42 = 0;

      while (var42 < this.I.size()) {
         Setting var29 = this.I.get(var42);
         int var44 = 29 + 22 * var42;
         byte var32 = 0;
         byte var33 = var19;
         if (var1 >= 1L) {
            var33 = var32;
         }

         switch (var29 == null
            ? -1
            : (
               var33 <= 0 && var29 instanceof NumberSetting
                  ? 0
                  : (
                     var33 <= 1 && var29 instanceof BooleanSetting
                        ? 1
                        : (var33 <= 2 && var29 instanceof ModeSetting ? 2 : (var33 <= 3 && var29 instanceof IntegerSetting ? 3 : 4))
                  )
            )) {
            case 0:
               NumberSetting var34 = (NumberSetting)var29;
               HandMovementScreen.HandSettingsPanel var35 = new HandMovementScreen.HandSettingsPanel(var27, var44, var41, var34);
               if (var1 < 1L) {
                  continue;
               }

               this.method_37063(var35);
               this.p.add(var35::refresh);
               break;
            case 1:
               BooleanSetting var36 = (BooleanSetting)var29;
               if (var1 <= 1L) {
                  continue;
               }

               this.c(var27, var44, var41, () -> {
                  long var4x = (long)((short)var9) << 48 | (long)((short)var10) << 48 >>> 16 | (long)var11 << 32 >>> 32;
                  return class_2561.method_43470(var36.enabled() ? "On" : "Off");
               }, (char)var3, (char)var4, var5, var36::toggle);
               break;
            case 2:
               ModeSetting var37 = (ModeSetting)var29;
               if (var1 <= 1L) {
                  continue;
               }

               this.c(
                  var27,
                  var44,
                  var41,
                  () -> class_2561.method_43470(this.field_22793.method_27523((String)var37.get(), var41 - a(16755, 3669808754052623236L ^ var15))),
                  (char)var3,
                  (char)var4,
                  var5,
                  () -> var37.select(var37.index() + 1)
               );
               break;
            case 3:
               IntegerSetting var38 = (IntegerSetting)var29;
               if (var1 <= 1L) {
                  continue;
               }

               this.c(var27, var44, var41, () -> {
                  SwingStyle.ensureSourceClassInitialized();
                  boolean var4x = false;
                  IntegerSetting var5x = this.T;
                  if (var17 > 1L) {
                     if (this.T == var38) {
                        return class_2561.method_43470("Press key");
                     }

                     var5x = var38;
                  }

                  int var6x = (Integer)var5x.get();
                  if (var17 >= 1L) {
                     if (var6x >= 0) {
                        return class_307.field_1668.method_1447((Integer)var38.get()).method_27445();
                     }

                     var6x = -2056365209;
                  }

                  return class_2561.method_43470("Unbound");
               }, (char)var3, (char)var4, var5, () -> this.T = var38);
               break;
            default:
               throw new IllegalStateException("Unsupported Hand Studio setting: " + var29.id());
         }

         var42++;
      }

      int var43 = 3484;
      if (var1 >= 0L) {
         var42 = 29 + 22 * this.I.size();
         var43 = (this.U - 14) / 2;
      }

      ((class_4185)this.method_37063(ModuleClick.builder(class_2561.method_43470("Previous"), var4x -> {
         long var5x = (long)((char)var6) << 48 | (long)var7 << 32 >>> 16 | (long)((char)var8) << 48 >>> 48;
         long var7x = var5x ^ 94774343501180L;
         this.T(var7x, -1);
      }).method_46434(this.r + 5, var42, var43, 20).method_46431())).field_22763 = this.Y > 0;
      class_4185 var31 = (class_4185)this.method_37063(ModuleClick.builder(class_2561.method_43470("Next"), var4x -> {
         long var5x = (long)var12 << 32 | (long)((char)var13) << 48 >>> 32 | (long)((char)var14) << 48 >>> 48;
         long var7x = var5x ^ 135158994298435L;
         this.T(var7x, 1);
      }).method_46434(this.r + 9 + var43, var42, var43, 20).method_46431());
      int var45 = var19;
      if (var1 >= 1L) {
         var45 = var23;
      }

      var31.field_22763 = this.Y < var45;
      this.s = var42 + 25;
   }

   private void c(int var1, int var2, int var3, Supplier var4, char var5, char var6, int var7, Runnable var8) {
      long var9 = (long)var5 << 48 | (long)var6 << 48 >>> 16 | (long)var7 << 32 >>> 32;
      class_4185 var11 = ModuleClick.builder((class_2561)var4.get(), var1x -> var8.run())
         .method_46434(var1, var2, var3, a(9512, 6704534851986439567L ^ var9))
         .method_46431();
      this.method_37063(var11);
      this.p.add(() -> var11.method_25355((class_2561)var4.get()));
   }

   private void T(long var1, int var3) {
      long var4 = var1 ^ 12374415076765L;
      this.Y += var3;
      this.Y(var4);
   }

   public void method_25394(class_332 var1, int var2, int var3, float var4) {
      long var5 = 59910763715934L;
      long var7 = var5 ^ 114006520305416L;
      int var9 = (int)((var5 ^ 114006520305416L) >>> 32);
      int var10 = (int)((var5 ^ 114006520305416L) << 32 >>> 48);
      int var11 = (int)(var7 << 48 >>> 48);
      long var12 = var5 ^ 114263215924878L;
      if (this.f != this.L.dock().right() || !this.q.equals(this.K())) {
         this.Y(var12);
      }

      this.p.forEach(Runnable::run);
      RenderPipelines var14 = RenderPipelines.get();
      Canvas var15 = var14.menuCanvas();
      boolean var16 = this.C(var9, (short)var10, (short)var11, var2, var3);
      this.t.begin(var15, var14.time(), var14.delta(), var14.fromGui(var16 ? -100.0 : var2), var14.fromGui(var16 ? -100.0 : var3), this.C);
      this.L.render(this.t, 1.0F, var14.fromGui(this.f ? this.r : this.r + this.U));
      var1.method_25294(this.r, 8, this.r + this.U, this.s, -535292893);
      var1.method_25303(this.field_22793, "Hand Movement", this.r + 5, 16, -1385217);

      for (int var17 = 0; var17 < this.I.size(); var17++) {
         var1.method_25303(this.field_22793, this.field_22793.method_27523(this.I.get(var17).name(), this.U / 2 - 8), this.r + 5, 35 + 22 * var17, -1976338);
      }

      super.method_25394(var1, var2, var3, var4);
      if (this.L.consumeCloseRequest()) {
         this.method_25419();
      }
   }

   private boolean C(int var1, short var2, short var3, double var4, double var6) {
      SwingStyle.ensureSourceClassInitialized();
      return var4 >= this.r && var4 < this.r + this.U && var6 >= 8.0 && var6 < this.s;
   }

   private float o(double var1) {
      return RenderPipelines.get().fromGui(var1);
   }

   public boolean method_25402(class_11909 var1, boolean var2) {
      long var3 = 64803371587558L;
      long var5 = var3 ^ 117909940510128L;
      int var7 = (int)((var3 ^ 117909940510128L) >>> 32);
      int var8 = (int)((var3 ^ 117909940510128L) << 32 >>> 48);
      int var9 = (int)(var5 << 48 >>> 48);
      if (this.C(var7, (short)var8, (short)var9, var1.comp_4798(), var1.comp_4799())) {
         super.method_25402(var1, var2);
         return true;
      } else {
         this.C = var1.method_74245() == 0;
         return this.L.mouseDown(this.o(var1.comp_4798()), this.o(var1.comp_4799()), var1.method_74245()) || super.method_25402(var1, var2);
      }
   }

   public boolean method_25403(class_11909 var1, double var2, double var4) {
      if (super.method_25403(var1, var2, var4)) {
         return true;
      } else {
         this.L.mouseDrag(this.o(var1.comp_4798()), this.o(var1.comp_4799()));
         return this.C;
      }
   }

   public boolean method_25406(class_11909 var1) {
      super.method_25406(var1);
      this.L.mouseUp(this.o(var1.comp_4798()), this.o(var1.comp_4799()), var1.method_74245());
      this.C = false;
      return true;
   }

   public boolean method_25401(double var1, double var3, double var5, double var7) {
      long var9 = 6377789150208L;
      long var11 = var9 ^ 92366630575702L;
      int var13 = (int)((var9 ^ 92366630575702L) >>> 32);
      int var14 = (int)((var9 ^ 92366630575702L) << 32 >>> 48);
      int var15 = (int)(var11 << 48 >>> 48);
      return this.C(var13, (short)var14, (short)var15, var1, var3) || this.L.scroll(this.o(var1), this.o(var3), var7);
   }

   public boolean method_25404(class_11908 var1) {
      if (this.T != null) {
         if (var1.comp_4795() == 259 || var1.comp_4795() == 261) {
            this.T.set(-1);
         } else if (var1.comp_4795() >= 32 && var1.comp_4795() <= 348 && var1.comp_4795() != 256) {
            this.T.set(var1.comp_4795());
         }

         this.T = null;
         return true;
      } else {
         return this.L.keyPressed(var1.comp_4795()) || super.method_25404(var1);
      }
   }

   public boolean method_25421() {
      return false;
   }

   public void method_25419() {
      try {
         CosmeticRegistry.storeSettings(this.E.id(), this.E.values());
      } catch (IOException | IllegalArgumentException var2) {
         if (this.field_22787.field_1724 != null) {
            this.field_22787.field_1724.method_7353(class_2561.method_43470("Hand Studio settings could not be saved."), false);
         }

         return;
      }

      this.field_22787.method_1507(CosmeticRegistry.menu());
   }

   public void method_25432() {
      if (this.M) {
         this.M = false;
         this.C = false;
         this.L.setOpen(false);
      }
   }

   static void play(HandMovementKind var0) {
      UiAudioPolicy.play(var0.name());
   }

   static void play(HandMovementKind var0, float var1) {
      UiAudioPolicy.play(var0.name(), var1, 1.0F);
   }

   static void play(HandMovementKind var0, float var1, float var2) {
      UiAudioPolicy.play(var0.name(), var1, var2);
   }

   private static int a(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 582;
      if (d[var3] == null) {
         d[var3] = (int)(c[var3] ^ var1);
      }

      return d[var3];
   }

   private static final class HandSettingsPanel extends class_357 {
      private final NumberSetting N;

      HandSettingsPanel(int var1, int var2, int var3, NumberSetting var4) {
         super(var1, var2, var3, 20, class_2561.method_43473(), var4.normalized());
         this.N = var4;
         this.method_25346();
      }

      void refresh() {
         this.field_22753 = this.N.normalized();
         this.method_25346();
      }

      protected void method_25346() {
         this.method_25355(class_2561.method_43470(this.N.display()));
      }

      protected void method_25344() {
         this.N.setNormalized(this.field_22753);
      }
   }
}
