// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import net.minecraft.class_1944;
import net.minecraft.class_2338;

public final class ClientSupport4 {
   private final long[] positions;
   private final byte[] sky;
   private final byte[] block;

   public ClientSupport4(long[] var1, byte[] var2, byte[] var3) {
      if (var1.length <= 32768 && var1.length == var2.length && var2.length == var3.length) {
         this.positions = (long[])var1.clone();
         this.sky = (byte[])var2.clone();
         this.block = (byte[])var3.clone();
      } else {
         throw new IllegalArgumentException("Invalid section light snapshot");
      }
   }

   public int at(class_1944 var1, class_2338 var2) {
      long var3 = var2.method_10063();

      for (int var5 = 0; var5 < this.positions.length; var5++) {
         if (this.positions[var5] == var3) {
            return var1 == class_1944.field_9284 ? this.sky[var5] : this.block[var5];
         }
      }

      return -1;
   }
}
