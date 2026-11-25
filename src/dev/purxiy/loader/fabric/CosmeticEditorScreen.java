package dev.purxiy.loader.fabric;

import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.ui.DrawContext;
import java.io.IOException;
import java.util.function.Supplier;
import net.minecraft.class_11909;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;

public class CosmeticEditorScreen extends class_437 {
   private final ModuleDescriptor module;
   private final CosmeticEditorScreen.Listener wardrobe;
   private final Supplier<class_437> preview;
   private final DrawContext frame = new DrawContext();
   private float left;
   private float top;
   private float scale = 1.0F;
   private boolean dragging;
   private String error = "";

   protected CosmeticEditorScreen(ModuleDescriptor var1, CosmeticEditorScreen.Listener var2, Supplier<class_437> var3) {
      super(class_2561.method_43470(var1.name()));
      this.module = var1;
      this.wardrobe = var2;
      this.preview = var3;
   }

   @Override
   protected void method_25426() {
      int var1 = this.preview == null ? 2 : 3;
      int var2 = this.field_22789 / 2 - var1 * 52;
      this.method_37063(ModuleClick.builder(class_2561.method_43470("Settings"), var1x -> {
         if (this.save()) {
            CosmeticRegistry.settings(this.module.id());
         }
      }).method_46434(var2, this.field_22790 - 26, 100, 20).method_46431());
      if (this.preview != null) {
         this.method_37063(ModuleClick.builder(class_2561.method_43470("Live preview"), var1x -> {
            if (this.save()) {
               ClientTheme.resetAll();
               this.field_22787.method_1507(this.preview.get());
            }
         }).method_46434(var2 + 104, this.field_22790 - 26, 100, 20).method_46431());
      }

      this.method_37063(
         ModuleClick.builder(class_2561.method_43470("Done"), var1x -> this.method_25419())
            .method_46434(var2 + 104 * (var1 - 1), this.field_22790 - 26, 100, 20)
            .method_46431()
      );
   }

   @Override
   public void method_25394(class_332 var1, int var2, int var3, float var4) {
      RenderPipelines var5 = RenderPipelines.get();
      Canvas var6 = var5.menuCanvas();
      float var7 = 864.0F;
      this.scale = Math.max(0.1F, Math.min((var6.width() - 24.0F) / var7, (var6.height() - var5.fromGui(35.0) - 18.0F) / this.wardrobe.height()));
      this.left = (var6.width() - var7 * this.scale) / 2.0F;
      this.top = 10.0F;
      this.frame.begin(var6, var5.time(), var5.delta(), this.pointerX(var2), this.pointerY(var3), this.dragging);
      var6.push();

      try {
         var6.translate(this.left, this.top);
         var6.scale(this.scale);
         this.wardrobe.render(this.frame, var7);
      } finally {
         var6.pop();
      }

      super.method_25394(var1, var2, var3, var4);
      if (!this.error.isEmpty()) {
         var1.method_25300(this.field_22793, this.error, this.field_22789 / 2, this.field_22790 - 39, -23626);
      }
   }

   private float pointerX(double var1) {
      return (RenderPipelines.get().fromGui(var1) - this.left) / this.scale;
   }

   private float pointerY(double var1) {
      return (RenderPipelines.get().fromGui(var1) - this.top) / this.scale;
   }

   @Override
   public boolean method_25402(class_11909 var1, boolean var2) {
      if (super.method_25402(var1, var2)) {
         return true;
      } else {
         this.dragging = this.wardrobe.mouseDown(this.pointerX(var1.comp_4798()), this.pointerY(var1.comp_4799()), var1.method_74245());
         return this.dragging;
      }
   }

   @Override
   public boolean method_25403(class_11909 var1, double var2, double var4) {
      if (!this.dragging) {
         return super.method_25403(var1, var2, var4);
      } else {
         this.wardrobe.mouseDrag(this.pointerX(var1.comp_4798()), this.pointerY(var1.comp_4799()));
         return true;
      }
   }

   @Override
   public boolean method_25406(class_11909 var1) {
      if (!this.dragging) {
         return super.method_25406(var1);
      } else {
         this.wardrobe.mouseUp();
         this.dragging = false;
         return true;
      }
   }

   private boolean save() {
      try {
         CosmeticRegistry.storeSettings(this.module.id(), this.module.values());
         return true;
      } catch (IOException | IllegalArgumentException var2) {
         this.error = "Could not save cosmetic selection.";
         return false;
      }
   }

   @Override
   public void method_25419() {
      if (this.save()) {
         this.field_22787.method_1507(CosmeticRegistry.menu());
      }
   }

   @Override
   public void method_25432() {
      this.wardrobe.mouseUp();
      this.dragging = false;
   }

   @Override
   public boolean method_25421() {
      return false;
   }

   public interface Listener {
      float height();

      void render(DrawContext var1, float var2);

      boolean mouseDown(float var1, float var2, int var3);

      default void mouseDrag(float var1, float var2) {
      }

      default void mouseUp() {
      }
   }
}
