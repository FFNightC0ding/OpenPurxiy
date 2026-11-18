// Sourced By DevFFNightmare
package dev.purxiy.loader.security;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.time.Clock;
import javax.crypto.SecretKey;

public final class ModuleVerifier {
   private final PublicKey trustedKey;
   private final Clock clock;
   private static String[] a;




   public ModuleVerifier(PublicKey var1, Clock var2) {
      long var3 = 108239799915008L;
      this.trustedKey = var1;
      this.clock = var2;
   }

   public VerifiedModule verify(ModuleEnvelope var1, SecretKey var2, String var3, String var4) throws IOException, GeneralSecurityException { return null; }

   public static byte[] hash(byte[] var0) throws GeneralSecurityException { return null; }

static {
   }
}
