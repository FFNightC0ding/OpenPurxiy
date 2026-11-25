package dev.purxiy.loader.runtime;

import dev.purxiy.loader.security.VerifiedModule;
import java.time.Instant;

@FunctionalInterface
public interface ModuleSource {
   ModuleSource.Prepared fetch(String var1, String var2) throws Exception;

   public record Prepared(VerifiedModule module, Instant authorizedUntil) implements AutoCloseable {
      @Override
      public void close() {
         this.module.close();
      }
   }

   public interface Renewable extends ModuleSource {
      Instant renew(String var1, String var2) throws Exception;
   }
}
