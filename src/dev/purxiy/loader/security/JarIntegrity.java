package dev.purxiy.loader.security;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.util.zip.ZipFile;

public final class JarIntegrity {
   public static String INVENTORY;
   public static String SIGNATURE;
   public static int MAGIC = 0;
   public static int MAX_ENTRIES = 0;
   public static int MAX_METADATA = 0;
   public static long MAX_ENTRY = 0L;
   public static long MAX_TOTAL = 0L;
   public static boolean b;
   private static String[] a;



   private static long[] f;
   private static Long[] g;

   private JarIntegrity() {
   }

   public static JarIntegrity.Claim claim(Path var0) throws IOException, GeneralSecurityException { return null; }

   public static JarIntegrity.Snapshot verify(Path var0, PublicKey var1) throws IOException, GeneralSecurityException { return null; }

   private static byte[] read(ZipFile var0, String var1, int var2) throws IOException, GeneralSecurityException { return null; }

   public static byte[] digest(InputStream var0, long var1) throws IOException, GeneralSecurityException { return null; }

private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 32511) & 32767;
      if (g[var3] == null) {
         g[var3] = f[var3] ^ var1;
      }

      return g[var3];
   }

   static {
   }

   public static final class Claim {
      private final String release;
      private final String manifestHash;

      public Claim(String var1, String var2) {
         this.release = var1;
         this.manifestHash = var2;
      }

      @Override
      public final String toString() { return null; }

      @Override
      public final int hashCode() { return 0; }

      @Override
      public final boolean equals(Object var1) { return false; }

      public String release() { return null; }

      public String manifestHash() { return null; }

      static {
      }
   }

   public static final class Failure extends GeneralSecurityException {
      private final String signal;

      public Failure(String var1) {
         super(var1);
         this.signal = var1;
      }

      public String signal() { return null; }

      static {
      }
   }

   public static final class Snapshot {
      private final String release;
      private final String manifestHash;
      private final long size;
      private final long modifiedMillis;

      public Snapshot(String var1, String var2, long var3, long var5) {
         this.release = var1;
         this.manifestHash = var2;
         this.size = var3;
         this.modifiedMillis = var5;
      }

      @Override
      public final String toString() { return null; }

      @Override
      public final int hashCode() { return 0; }

      @Override
      public final boolean equals(Object var1) { return false; }

      public String release() { return null; }

      public String manifestHash() { return null; }

      public long size() { return 0L; }

      public long modifiedMillis() { return 0L; }

      static {
      }
   }

   private class IntegrityChunk {
      private final long a;
      private final byte[] b;

      private IntegrityChunk(long var1, byte[] var3) {
         this.a = var1;
         this.b = var3;
      }

      @Override
      public final String toString() { return null; }

      @Override
      public final int hashCode() { return 0; }

      @Override
      public final boolean equals(Object var1) { return false; }

      public long a() { return 0L; }

      public byte[] b() { return null; }

      static {
      }
   }
}
