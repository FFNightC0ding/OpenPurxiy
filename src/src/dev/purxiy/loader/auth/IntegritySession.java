// Sourced By DevFFNightmare
package dev.purxiy.loader.auth;

public final class IntegritySession implements AutoCloseable {
   private final ServerAccount account;

   public IntegritySession(ServerAccount account) {
      this.account = account;
   }

   public IntegritySession() {
      this((ServerAccount)null);
   }

   public String session() {
      return this.account != null ? this.account.session() : "";
   }

   public void close() {
   }
}
