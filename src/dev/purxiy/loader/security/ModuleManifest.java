package dev.purxiy.loader.security;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ModuleManifest {
   public static int MAX_PAYLOAD = 0;
   public static int MAX_METADATA = 0;
   private static int MAGIC = 0;
   private final ModuleSpec spec;
   private final int plainSize;
   private final byte[] plainHash;
   private final byte[] cipherHash;
   private final byte[] nonce;
   private static String[] a;
   private static String[] b;



   public ModuleManifest(ModuleSpec var1, int var2, byte[] var3, byte[] var4, byte[] var5) {
      long var6 = 54858261621316L;
      int var10000 = ModuleSpec.b;
      int var8 = var10000;
      byte[] var11;
      var10000 = var2;
      if (var8 == 0) {
         if (var2 <= 0) {
            throw new IllegalArgumentException("Invalid package dimensions");
         }

         var10000 = var2;
      }

      if (var8 == 0) {
         if (var10000 > 33554432) {
            throw new IllegalArgumentException("Invalid package dimensions");
         }

         var10000 = var3.length;
      }

      label52: {
         if (var8 == 0) {
            if (var10000 != 32) {
               throw new IllegalArgumentException("Invalid package dimensions");
            }

            var11 = var4;
            if (var8 != 0) {
               break label52;
            }

            var10000 = var4.length;
         }

         if (var10000 != 32) {
            throw new IllegalArgumentException("Invalid package dimensions");
         }

         var11 = var5;
      }

      if (var11.length == 12) {
         this.spec = var1;
         this.plainSize = var2;
         this.plainHash = (byte[])var3.clone();
         this.cipherHash = (byte[])var4.clone();
         this.nonce = (byte[])var5.clone();
      } else {
         throw new IllegalArgumentException("Invalid package dimensions");
      }
   }

   public ModuleSpec spec() { return null; }

   public int plainSize() { return 0; }

   public byte[] plainHash() { return null; }

   public byte[] cipherHash() { return null; }

   public byte[] nonce() { return null; }

   public static byte[] identity(ModuleSpec var0) { return null; }

   public byte[] encode() { return null; }

   public static ModuleManifest decode(byte[] var0) throws IOException { return null; }

static {
   }
}
