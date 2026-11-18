// Sourced By DevFFNightmare
package dev.purxiy.hud;

import net.minecraft.class_10799;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_9848;
import org.joml.Matrix3x2fStack;

public final class HudDragState {
   private class_332 graphics;
   private float originX;
   private float originY;
   private float scale;

   void begin(class_332 var1, float var2, float var3, float var4) {
      this.graphics = var1;
      this.originX = var2;
      this.originY = var3;
      this.scale = var4;
   }

   void end() {
      this.graphics = null;
   }

   public void item(class_1799 var1, float var2, float var3, float var4) {
      if (!var1.method_7960()) {
         Matrix3x2fStack var5 = this.graphics.method_51448();
         var5.pushMatrix();
         var5.translate(this.originX + var2 * this.scale, this.originY + var3 * this.scale);
         var5.scale(var4 * this.scale / 16.0F);
         this.graphics.method_51427(var1, 0, 0);
         var5.popMatrix();
      }
   }

   public void sprite(class_2960 var1, float var2, float var3, float var4, float var5) {
      Matrix3x2fStack var6 = this.graphics.method_51448();
      var6.pushMatrix();
      var6.translate(this.originX + var2 * this.scale, this.originY + var3 * this.scale);
      var6.scale(var4 * this.scale / 18.0F);
      this.graphics.method_52707(class_10799.field_56883, var1, 0, 0, 18, 18, class_9848.method_61317(Math.max(0.0F, Math.min(1.0F, var5))));
      var6.popMatrix();
   }
}
