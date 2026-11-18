// Sourced By DevFFNightmare
package dev.purxiy.loader.security;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class VerifiedModule implements AutoCloseable {
   private static final int MAX_ENTRIES = 10000;
   private static final int MAX_ENTRY = 33554432;
   private static final long MAX_EXPANDED = 268435456L;
   private final ModuleSpec spec;
   private Map<String, byte[]> entries;

   private VerifiedModule(ModuleSpec var1, Map<String, byte[]> var2) {
      this.spec = var1;
      this.entries = var2;
   }

   public ModuleSpec spec() {
      return this.spec;
   }

   public static boolean safePath(String var0) {
      if (var0 == null || var0.isEmpty() || var0.length() > 1024) {
         return false;
      } else if (var0.indexOf('\\') >= 0 || var0.indexOf(0) >= 0 || var0.indexOf(':') >= 0 || var0.charAt(0) == '/') {
         return false;
      } else {
         for (String var4 : var0.split("/", -1)) {
            if (var4.isEmpty() || var4.equals(".") || var4.equals("..")) {
               return false;
            }
         }

         return true;
      }
   }

   static VerifiedModule read(ModuleSpec var0, byte[] var1) throws IOException {
      Map<String, byte[]> var2 = new LinkedHashMap<>();
      long var3 = 0L;

      try (ZipInputStream var5 = new ZipInputStream(new ByteArrayInputStream(var1))) {
         ZipEntry var6;
         while ((var6 = var5.getNextEntry()) != null) {
            if (!var6.isDirectory()) {
               String var7 = var6.getName();
               if (!safePath(var7) || var2.size() >= MAX_ENTRIES) {
                  throw new IOException("Unsafe module archive");
               }

               byte[] var8 = var5.readNBytes(MAX_ENTRY + 1);
               var3 += var8.length;
               if (var8.length > MAX_ENTRY || var3 > MAX_EXPANDED || var2.putIfAbsent(var7, var8) != null) {
                  throw new IOException("Invalid module entries");
               }
            }
         }
      }

      return new VerifiedModule(var0, var2);
   }

   public Map<String, byte[]> transfer() {
      Map<String, byte[]> var1 = this.entries;
      this.entries = null;
      return var1;
   }

   @Override
   public void close() {
      Map<String, byte[]> var1 = this.entries;
      this.entries = null;
      wipe(var1);
   }

   public static void wipe(Map<String, byte[]> var0) {
      if (var0 != null) {
         for (byte[] var4 : var0.values()) {
            if (var4 != null) {
               Arrays.fill(var4, (byte)0);
            }
         }

         var0.clear();
      }
   }
}
