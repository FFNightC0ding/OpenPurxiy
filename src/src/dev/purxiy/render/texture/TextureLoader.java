// Sourced By DevFFNightmare
package dev.purxiy.render.texture;

import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import dev.purxiy.assets.AssetPaths;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import net.minecraft.class_1011;
import net.minecraft.class_12137;

public final class TextureLoader implements AutoCloseable {
   private final String name;
   private final int width;
   private final int height;
   
   private GpuTexture texture;
   
   private GpuTextureView view;
   
   private class_1011 pending;

   private TextureLoader(String var1, class_1011 var2) {
      this.name = var1;
      this.width = var2.method_4307();
      this.height = var2.method_4323();
      this.pending = var2;
   }

   public static TextureLoader of(String var0, class_1011 var1) {
      return new TextureLoader(var0, var1);
   }

   public static TextureLoader load(String var0) {
      try {
         TextureLoader var2;
         try (InputStream var1 = AssetPaths.open("assets/purxiy/" + var0)) {
            var2 = new TextureLoader(var0, class_1011.method_4309(var1));
         }

         return var2;
      } catch (IOException var7) {
         throw new IllegalStateException("Purxiy: failed to load texture " + var0, var7);
      }
   }

   public int width() {
      return this.width;
   }

   public int height() {
      return this.height;
   }

   public float aspect() {
      return (float)this.width / this.height;
   }

   public GpuTextureView view() {
      if (this.view == null) {
         this.upload();
      }

      return this.view;
   }

   public class_12137 sampler() {
      return RenderSystem.getSamplerCache().method_76520(FilterMode.LINEAR, true);
   }

   private void upload() {
      class_1011 var1 = this.pending;
      if (var1 == null) {
         throw new IllegalStateException("Purxiy texture " + this.name + " was closed");
      } else {
         this.pending = null;
         premultiply(var1);
         ArrayList var2 = new ArrayList();
         var2.add(var1);
         class_1011 var3 = var1;

         while (var3.method_4307() > 1 && var3.method_4323() > 1 && var2.size() < 8) {
            var3 = downsample(var3);
            var2.add(var3);
         }

         GpuDevice var4 = RenderSystem.getDevice();
         this.texture = var4.createTexture(() -> "Purxiy " + this.name, 5, TextureFormat.RGBA8, this.width, this.height, 1, var2.size());
         CommandEncoder var5 = var4.createCommandEncoder();

         for (int var6 = 0; var6 < var2.size(); var6++) {
            class_1011 var7 = (class_1011)var2.get(var6);
            int var8 = this.texture.getWidth(var6);
            int var9 = this.texture.getHeight(var6);
            var5.writeToTexture(this.texture, var7, var6, 0, 0, 0, Math.min(var8, var7.method_4307()), Math.min(var9, var7.method_4323()), 0, 0);
            var7.close();
         }

         this.view = var4.createTextureView(this.texture);
      }
   }

   private static void premultiply(class_1011 var0) {
      for (int var1 = 0; var1 < var0.method_4323(); var1++) {
         for (int var2 = 0; var2 < var0.method_4307(); var2++) {
            int var3 = var0.method_61940(var2, var1);
            int var4 = var3 >>> 24;
            if (var4 != 255) {
               int var5 = (var3 >> 16 & 0xFF) * var4 / 255;
               int var6 = (var3 >> 8 & 0xFF) * var4 / 255;
               int var7 = (var3 & 0xFF) * var4 / 255;
               var0.method_61941(var2, var1, var4 << 24 | var5 << 16 | var6 << 8 | var7);
            }
         }
      }
   }

   private static class_1011 downsample(class_1011 var0) {
      int var1 = Math.max(1, var0.method_4307() >> 1);
      int var2 = Math.max(1, var0.method_4323() >> 1);
      class_1011 var3 = new class_1011(var1, var2, false);
      int var4 = var0.method_4307();
      int var5 = var0.method_4323();

      for (int var6 = 0; var6 < var2; var6++) {
         for (int var7 = 0; var7 < var1; var7++) {
            int var8 = Math.min(var7 * 2, var4 - 1);
            int var9 = Math.min(var7 * 2 + 1, var4 - 1);
            int var10 = Math.min(var6 * 2, var5 - 1);
            int var11 = Math.min(var6 * 2 + 1, var5 - 1);
            int var12 = var0.method_61940(var8, var10);
            int var13 = var0.method_61940(var9, var10);
            int var14 = var0.method_61940(var8, var11);
            int var15 = var0.method_61940(var9, var11);
            int var16 = (var12 >>> 24) + (var13 >>> 24) + (var14 >>> 24) + (var15 >>> 24) + 2 >> 2;
            int var17 = (var12 >> 16 & 0xFF) + (var13 >> 16 & 0xFF) + (var14 >> 16 & 0xFF) + (var15 >> 16 & 0xFF) + 2 >> 2;
            int var18 = (var12 >> 8 & 0xFF) + (var13 >> 8 & 0xFF) + (var14 >> 8 & 0xFF) + (var15 >> 8 & 0xFF) + 2 >> 2;
            int var19 = (var12 & 0xFF) + (var13 & 0xFF) + (var14 & 0xFF) + (var15 & 0xFF) + 2 >> 2;
            var3.method_61941(var7, var6, var16 << 24 | var17 << 16 | var18 << 8 | var19);
         }
      }

      return var3;
   }

   @Override
   public void close() {
      if (this.pending != null) {
         this.pending.close();
         this.pending = null;
      }

      if (this.view != null) {
         this.view.close();
         this.view = null;
      }

      if (this.texture != null) {
         this.texture.close();
         this.texture = null;
      }
   }
}
