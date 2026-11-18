// Sourced By DevFFNightmare
package dev.purxiy.loader.auth;

import dev.purxiy.loader.security.SecuritySupport2;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class AccessDeniedException extends SecurityException {
   private final AccessDeniedException.Reason reason;
   public static boolean b;
   private static String[] a;
   private static String[] c;



   public AccessDeniedException(AccessDeniedException.Reason var1) {
      super("Client access denied: " + var1.name());
      long var2 = 110537552439257L;
      this.reason = var1;
   }

   public AccessDeniedException.Reason reason() { return this.reason; }

   public boolean terminal() { return false; }

   public static AccessDeniedException response(int var0, byte[] var1) { return new AccessDeniedException(AccessDeniedException.Reason.ACCESS_REFUSED); }

static {
   }

   public static enum Reason {
      LINK_REQUIRED,
      LICENCE_REQUIRED,
      DEVICE_CHANGED,
      RELEASE_REJECTED,
      SECURITY_HOLD,
      SESSION_REQUIRED,
      ACCESS_REFUSED,
      INTEGRITY_REJECTED;

      public static int b;
      private static long[] a;
      private static Integer[] c;

// $VF: Failed to inline enum fields
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      static {
      }
   }
}
