// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import dev.purxiy.loader.auth.AccessDeniedException;
import org.slf4j.LoggerFactory;

final class SecureRuntimeRestart {
   private SecureRuntimeRestart() {
   }

   static void stopForAuthorization(AccessDeniedException var0) {
      LoggerFactory.getLogger("PurxiyLoader").warn("Authorization denied: {}", var0 != null ? var0.getMessage() : "");
   }
}
