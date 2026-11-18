// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;

public final class AccountGate {
   private AccountGate() {
   }

   public static boolean authorized() {
      return LoaderMessages.authorized && LoaderMessages.menuAllowed();
   }

   public static boolean ready() {
      return authorized() && UiAudioPolicy.active() && ModuleState.moduleActive("interface") && ModuleState.moduleActive("profile");
   }

   public static URI origin() {
      if (LoaderMessages.account == null) {
         throw new IllegalStateException("Account not initialized");
      } else {
         return LoaderMessages.account.origin;
      }
   }

   public static String token() throws IOException {
      if (authorized() && LoaderMessages.account != null) {
         return LoaderMessages.account.token();
      } else {
         throw new IOException("Account is not admitted");
      }
   }

   public static JsonObject machine() {
      if (authorized() && LoaderMessages.account != null) {
         return LoaderMessages.account.machine();
      } else {
         throw new IllegalStateException("Account is not admitted");
      }
   }
}
