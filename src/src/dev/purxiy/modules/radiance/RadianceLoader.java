// Sourced By DevFFNightmare
package dev.purxiy.modules.radiance;

import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;

final class RadianceLoader {
   private GpuTexture k;
   private GpuTexture i;
   private GpuTexture U;
   private GpuTexture p;
   GpuTextureView W;
   GpuTextureView X;
   GpuTextureView b;
   GpuTextureView z;
   private int t;
   private int V;



   void ensure(int var1, int var2) {
      if (this.k == null || this.t != var1 || this.V != var2) {
         this.close();
         GpuDevice var3 = RenderSystem.getDevice();
         byte var4 = 13;
         int var5 = (var1 + 1) / 2;
         int var6 = (var2 + 1) / 2;
         this.k = var3.createTexture(() -> "Purxiy radiance mask", var4, TextureFormat.RED8, var1, var2, 1, 1);
         this.i = var3.createTexture(() -> "Purxiy radiance coarse field A", var4, TextureFormat.RGBA8, var5, var6, 1, 1);
         this.U = var3.createTexture(() -> "Purxiy radiance coarse field B", var4, TextureFormat.RGBA8, var5, var6, 1, 1);
         this.p = var3.createTexture(() -> "Purxiy radiance field", var4, TextureFormat.RGBA8, var1, var2, 1, 1);
         this.W = var3.createTextureView(this.k);
         this.X = var3.createTextureView(this.i);
         this.b = var3.createTextureView(this.U);
         this.z = var3.createTextureView(this.p);
         this.t = var1;
         this.V = var2;
      }
   }

   boolean fits(int var1, int var2) {
      return this.k != null && this.t == var1 && this.V == var2;
   }

   void close() {
      if (this.k != null) {
         this.W.close();
         this.X.close();
         this.b.close();
         this.z.close();
         this.k.close();
         this.i.close();
         this.U.close();
         this.p.close();
         this.k = null;
      }
   }

}
