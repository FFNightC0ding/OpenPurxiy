// Sourced By DevFFNightmare
package dev.purxiy.loader.security;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.time.Clock;
import java.time.Instant;
import javax.crypto.spec.SecretKeySpec;

public final class ModuleGrant implements AutoCloseable {
   public static int MAX_BYTES = 0;
   private final byte[] contentKey;
   private final Instant expiresAt;
   private boolean closed;
   private static String[] a;
   private static String[] b;


   private static long[] e;
   private static Long[] f;

   private ModuleGrant(byte[] var1, Instant var2) {
      this.contentKey = var1;
      this.expiresAt = var2;
   }

   public Instant expiresAt() { return null; }

   public synchronized SecretKeySpec key() { return null; }

   @Override
   public synchronized void close() {}

   public static ModuleGrant verify(
      byte[] var0, PublicKey var1, Clock var2, String var3, String var4, String var5, String var6, byte[] var7, byte[] var8, byte[] var9
   ) throws IOException, GeneralSecurityException { return null; }

private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 8615) & 32767;
      if (f[var3] == null) {
         f[var3] = e[var3] ^ var1;
      }

      return f[var3];
   }

   static {
   }
}
