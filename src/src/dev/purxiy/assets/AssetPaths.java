// Sourced By DevFFNightmare
package dev.purxiy.assets;

import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientRegistry24;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Optional;
import net.minecraft.class_3298;
import org.lwjgl.system.MemoryUtil;

public final class AssetPaths {
   private AssetPaths() {
   }

   public static InputStream open(String var0) throws IOException {
      if (var0.startsWith("assets/purxiy/") && !var0.contains("..") && !var0.contains("\\")) {
         String var1 = var0.substring("assets/purxiy/".length());
         if (var1.matches("[a-z0-9_./-]+")) {
            Optional var2 = ClientRegistry24.resource(ClientServices.id(var1));
            if (var2.isPresent()) {
               return ((class_3298)var2.get()).method_14482();
            }
         }

         InputStream var3 = AssetPaths.class.getResourceAsStream("/" + var0);
         if (var3 == null) {
            throw new IOException("Missing renderer resource: " + var0);
         } else {
            return var3;
         }
      } else {
         throw new IOException("Invalid renderer resource");
      }
   }

   public static byte[] bytes(String var0) throws IOException {
      byte[] var2;
      try (InputStream var1 = open(var0)) {
         byte[] var3 = var1.readNBytes(8388609);
         if (var3.length > 8388608) {
            throw new IOException("Renderer resource exceeds size limit");
         }

         var2 = var3;
      }

      return var2;
   }

   public static ByteBuffer direct(String var0) throws IOException {
      byte[] var1 = bytes(var0);
      return MemoryUtil.memAlloc(var1.length).put(var1).flip();
   }
}
