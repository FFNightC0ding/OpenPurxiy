package dev.purxiy.modules.crosshair;

import dev.purxiy.config.Setting;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleClick;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import java.io.IOException;
import java.util.LinkedHashMap;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;

public final class CustomCrosshairScreen extends class_437 {
   private final CustomCrosshairModule w;
   private String b = "";

   public CustomCrosshairScreen(CustomCrosshairModule var1) {
      super(class_2561.method_43470("Custom Crosshair"));
      this.w = var1;
   }

   protected void method_25426() {
      String[] var1 = new String[]{"Minimal", "Vesper", "Orbit"};

      for (int var2 = 0; var2 < var1.length; var2++) {
         int var3 = var2;
         this.method_37063(ModuleClick.builder(class_2561.method_43470(var1[var2]), var2x -> {
            CrosshairOverlay.ensureSourceClassInitialized();
            this.w.applyPreset(var3);
            LinkedHashMap var3x = new LinkedHashMap();

            for (Setting var5 : this.w.settings()) {
               long var6 = 69361334788960L;
               int var8 = var6 < 0L ? -1 : 1;
               var3x.put(var5.id(), var5.save().getAsString());
            }

            try {
               CosmeticRegistry.storeSettings(this.w.id(), var3x);
               long var10 = 69361334788960L;
               int var11 = var10 < 0L ? -1 : 1;
               this.b = "";
            } catch (IOException var9) {
               this.b = "Could not save preset";
            }
         }).method_46434(this.field_22789 / 2 - 154 + var2 * 105, 48, 98, 20).method_46431());
      }

      this.method_37063(
         ModuleClick.builder(class_2561.method_43470("All settings"), var1x -> CosmeticRegistry.settings(this.w.id()))
            .method_46434(this.field_22789 / 2 - 100, this.field_22790 - 56, 96, 20)
            .method_46431()
      );
      this.method_37063(
         ModuleClick.builder(class_2561.method_43470("Done"), var1x -> this.method_25419())
            .method_46434(this.field_22789 / 2 + 4, this.field_22790 - 56, 96, 20)
            .method_46431()
      );
   }

   public void method_25394(class_332 var1, int var2, int var3, float var4) {
      super.method_25394(var1, var2, var3, var4);
      var1.method_27534(this.field_22793, this.field_22785, this.field_22789 / 2, 22, -1714177);
      var1.method_25300(this.field_22793, "Presets are applied once. Every value stays editable.", this.field_22789 / 2, 78, -5661255);
      if (!this.b.isEmpty()) {
         var1.method_25300(this.field_22793, this.b, this.field_22789 / 2, this.field_22790 - 78, -28528);
      }

      if (ClientServices.drawing()) {
         RenderPipelines var5 = RenderPipelines.get();
         Canvas var6 = var5.hudCanvas();
         CrosshairOverlay.draw(
            var6,
            this.w,
            (String)this.w.k.get(),
            var6.width() / 2.0F,
            var6.height() / 2.0F,
            var6.height() / 720.0F,
            var5.time(),
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            1.0F,
            true
         );
      }
   }

   public boolean method_25421() {
      return false;
   }

   public void method_25419() {
      this.field_22787.method_1507(CosmeticRegistry.menu());
   }
}
