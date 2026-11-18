// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.purxiy.loader.fabric.mixin.VisualPipelineAccess;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.class_10141;
import net.minecraft.class_10865;
import net.minecraft.class_10867;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_5944;

public final class ClientCache {
   private ClientCache() {
   }

   public static void release(RenderPipeline var0) {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Pipeline disposal requires render owner thread");
      } else {
         GpuDevice var1 = RenderSystem.getDevice();
         if (!(var1 instanceof class_10865)) {
            throw new IllegalStateException("Private visual pipeline cleanup requires the supported OpenGL backend");
         } else {
            class_10867 var2 = ((VisualPipelineAccess)var1).purxiy$programs().remove(var0);
            if (var2 != null && var2.comp_3802() != null && var2.comp_3802() != class_5944.field_57864) {
               var2.comp_3802().close();
            }
         }
      }
   }

   private static boolean includes(Set<class_2960> var0, class_2960 var1) {
      return var0.contains(var1.method_45136("shaders/" + var1.method_12832() + ".vsh"))
         || var0.contains(var1.method_45136("shaders/" + var1.method_12832() + ".fsh"));
   }

   public static void releaseShaders(Set<class_2960> var0, Set<class_2960> var1) {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Shader disposal owner thread");
      } else {
         Map var2 = ((VisualPipelineAccess)RenderSystem.getDevice()).purxiy$shaders();
         Iterator var3 = var2.entrySet().iterator();

         while (var3.hasNext()) {
            class_10141 var4 = (class_10141)((Entry)var3.next()).getValue();
            if (var4 != class_10141.field_57880 && includes(var0, var4.method_62881()) && !includes(var1, var4.method_62881())) {
               var3.remove();
               var4.close();
            }
         }
      }
   }

   public static int cachedShaders(Set<class_2960> var0) {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Shader inspection owner thread");
      } else {
         return (int)((VisualPipelineAccess)RenderSystem.getDevice())
            .purxiy$shaders()
            .values()
            .stream()
            .filter(var1 -> var1 != class_10141.field_57880 && includes(var0, var1.method_62881()))
            .count();
      }
   }
}
