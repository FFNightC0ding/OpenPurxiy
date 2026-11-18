// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

final class OwnDebuggerProbe {
   private OwnDebuggerProbe() {
   }

   static boolean present() {
      try {
         return OwnDebuggerProbe.Kernel.INSTANCE.IsDebuggerPresent();
      } catch (LinkageError | RuntimeException var1) {
         return false;
      }
   }

   public interface Kernel extends StdCallLibrary {
      OwnDebuggerProbe.Kernel INSTANCE = (OwnDebuggerProbe.Kernel)Native.load("kernel32", OwnDebuggerProbe.Kernel.class, W32APIOptions.DEFAULT_OPTIONS);

      boolean IsDebuggerPresent();
   }
}
