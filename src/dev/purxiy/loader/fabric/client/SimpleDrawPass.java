package dev.purxiy.loader.fabric.client;

import com.mojang.blaze3d.textures.GpuTextureView;
import net.minecraft.class_437;
import org.joml.Matrix4fc;
import org.joml.Vector3fc;

public final class SimpleDrawPass {
   public static final int GROUND = 0;
   public static final int WATER = 1;
   public static final int TREE = 2;
   private static final SkyPipelineRouter.PipelineList<SimpleDrawPass.Listener> draws = SkyPipelineRouter.PipelineList.create();

   private SimpleDrawPass() {
   }

   public static AutoCloseable install(SimpleDrawPass.Listener var0) {
      return draws.subscribe(0, var0);
   }

   public static void draw(class_437 var0, SimpleDrawPass.CameraFrame var1) {
      draws.invoke(var2 -> {
         var2.draw(var0, var1);
         return null;
      }, null);
   }

   @FunctionalInterface
   public interface Listener {
      void draw(class_437 var1, SimpleDrawPass.CameraFrame var2);
   }

   public interface CameraFrame {
      Matrix4fc viewProj();

      Matrix4fc projection();

      Vector3fc eye();

      Vector3fc right();

      Vector3fc up();

      Vector3fc forward();

      GpuTextureView depth();

      float trace(float var1, float var2, float var3, float var4, int[] var5);

      float ground(float var1, float var2);

      float dark();

      boolean settled();
   }
}
