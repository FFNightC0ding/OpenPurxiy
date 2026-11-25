package dev.purxiy.loader.fabric;

import dev.purxiy.loader.auth.AccessDeniedException;
import dev.purxiy.loader.auth.IntegritySession;
import dev.purxiy.loader.auth.ServerAccount;
import java.util.function.Consumer;

final class LiveAccessMonitor implements AutoCloseable {
   private final ServerAccount account;
   private final IntegritySession security;
   private final Consumer<AccessDeniedException> denial;

   LiveAccessMonitor(ServerAccount var1, IntegritySession var2, Consumer<AccessDeniedException> var3) {
      this.account = var1;
      this.security = var2;
      this.denial = var3;
   }

   @Override
   public void close() {
   }
}
