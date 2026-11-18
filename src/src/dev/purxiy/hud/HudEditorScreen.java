// Sourced By DevFFNightmare
package dev.purxiy.hud;

import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.render.RenderPipelines;
import net.minecraft.class_11908;
import net.minecraft.class_11909;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;

public final class HudEditorScreen extends class_437 {
   
   private final class_437 parent;
   private AutoCloseable rendererLease;

   public HudEditorScreen( class_437 var1) {
      super(class_2561.method_43470("HUD editor"));
      this.parent = var1;
   }

   @Override
   protected void method_25426() {
      if (this.rendererLease == null) {
         try {
            this.rendererLease = ClientServices.acquire("hud-editor-screen");
         } catch (Exception var2) {
            throw new IllegalStateException(var2);
         }
      }

      HudLayoutEngine.beginScreen(this);
   }

   @Override
   public void method_25432() {
      HudLayoutEngine.endScreen();
      if (this.rendererLease != null) {
         try {
            this.rendererLease.close();
         } catch (Exception var2) {
         }

         this.rendererLease = null;
      }
   }

   @Override
   public void method_25419() {
      this.field_22787.method_1507(this.parent);
   }

   @Override
   public boolean method_25421() {
      return false;
   }

   @Override
   public void method_25420(class_332 var1, int var2, int var3, float var4) {
      if (this.field_22787.field_1687 == null) {
         this.method_57728(var1, var4);
      }
   }

   @Override
   public void method_25394(class_332 var1, int var2, int var3, float var4) {
      if (this.field_22787.field_1687 == null) {
         HudRegistry.extract(var1, this.field_22787.method_61966());
      }

      HudRegistry.renderLayer();
      HudLayoutEngine.renderOverlay(var1, this);
   }

   private static float hud(double var0) {
      return RenderPipelines.get().hudFromGui(var0);
   }

   @Override
   public boolean method_25402(class_11909 var1, boolean var2) {
      return HudLayoutEngine.mouseClicked(hud(var1.comp_4798()), hud(var1.comp_4799()), var1.method_74245());
   }

   @Override
   public boolean method_25403(class_11909 var1, double var2, double var4) {
      return HudLayoutEngine.mouseDragged(hud(var1.comp_4798()), hud(var1.comp_4799()));
   }

   @Override
   public boolean method_25406(class_11909 var1) {
      return HudLayoutEngine.mouseReleased(hud(var1.comp_4798()), hud(var1.comp_4799()));
   }

   @Override
   public boolean method_25401(double var1, double var3, double var5, double var7) {
      return HudLayoutEngine.mouseScrolled(hud(var1), hud(var3), var7);
   }

   @Override
   public boolean method_25404(class_11908 var1) {
      return HudLayoutEngine.keyPressed(var1.comp_4795()) || super.method_25404(var1);
   }
}
