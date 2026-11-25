package dev.purxiy.modules.skins3d;

import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleClick;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.ui.DrawContext;
import java.io.IOException;
import net.minecraft.class_11909;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;

final class Skins3DScreen extends class_437 {
   private final Skins3DModule w;
   private final Skins3DRenderer2 u;
   private final DrawContext d = new DrawContext();
   private float P;
   private float K;
   private float z = 1.0F;
   private boolean A;
   private String a = "";

   Skins3DScreen(Skins3DModule var1) {
      super(class_2561.method_43470("3D Skins Wardrobe"));
      this.w = var1;
      this.u = new Skins3DRenderer2(var1);
   }

   protected void method_25426() {
      this.method_37063(ModuleClick.builder(class_2561.method_43470("Settings"), var1 -> {
         long var2 = 53445059612335L;
         SkinPreset.ensureSourceClassInitialized();
         if (this.L(var2)) {
            CosmeticRegistry.settings(this.w.id());
         }
      }).method_46434(this.field_22789 / 2 - 104, this.field_22790 - 26, 100, 20).method_46431());
      this.method_37063(
         ModuleClick.builder(class_2561.method_43470("Done"), var1 -> this.method_25419())
            .method_46434(this.field_22789 / 2 + 4, this.field_22790 - 26, 100, 20)
            .method_46431()
      );
   }

   public void method_25394(class_332 var1, int var2, int var3, float var4) {
      RenderPipelines var5 = RenderPipelines.get();
      Canvas var6 = var5.menuCanvas();
      float var7 = 864.0F;
      this.z = Math.max(0.15F, Math.min((var6.width() - 24.0F) / var7, (var6.height() - var5.fromGui(34.0) - 18.0F) / 474.0F));
      this.P = (var6.width() - var7 * this.z) / 2.0F;
      this.K = 10.0F;
      this.d.begin(var6, var5.time(), var5.delta(), this.u(var2), this.j(var3), this.A);
      var6.push();

      try {
         var6.translate(this.P, this.K);
         var6.scale(this.z);
         this.u.render(this.d, 0.0F, 0.0F, var7, var3 < this.field_22790 - 32);
      } finally {
         var6.pop();
      }

      super.method_25394(var1, var2, var3, var4);
      if (!this.a.isEmpty()) {
         var1.method_25300(this.field_22793, this.a, this.field_22789 / 2, this.field_22790 - 39, -23626);
      }
   }

   private float u(double var1) {
      return (RenderPipelines.get().fromGui(var1) - this.P) / this.z;
   }

   private float j(double var1) {
      return (RenderPipelines.get().fromGui(var1) - this.K) / this.z;
   }

   public boolean method_25402(class_11909 var1, boolean var2) {
      if (super.method_25402(var1, var2)) {
         return true;
      } else {
         this.A = this.u.mouseDown(this.u(var1.comp_4798()), this.j(var1.comp_4799()), var1.method_74245());
         return this.A;
      }
   }

   public boolean method_25403(class_11909 var1, double var2, double var4) {
      if (!this.A) {
         return super.method_25403(var1, var2, var4);
      } else {
         this.u.mouseDrag(this.u(var1.comp_4798()), this.j(var1.comp_4799()));
         return true;
      }
   }

   public boolean method_25406(class_11909 var1) {
      if (!this.A) {
         return super.method_25406(var1);
      } else {
         this.u.mouseUp();
         this.A = false;
         return true;
      }
   }

   private boolean L(long var1) {
      try {
         CosmeticRegistry.storeSettings(this.w.id(), this.w.values());
         return true;
      } catch (IOException | IllegalArgumentException var4) {
         this.a = "Could not save skin selection.";
         return false;
      }
   }

   public void method_25419() {
      long var1 = 36719781888287L;
      long var3 = var1 ^ 19786247786416L;
      if (this.L(var3)) {
         this.field_22787.method_1507(CosmeticRegistry.menu());
      }
   }

   public boolean method_25421() {
      return false;
   }

   public void method_25432() {
      this.u.mouseUp();
      this.A = false;
   }
}
