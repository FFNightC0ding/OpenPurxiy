// Sourced By DevFFNightmare
package dev.purxiy.loader.auth;

import com.google.gson.JsonObject;
import java.net.URI;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

public final class ServerAccount implements AutoCloseable {
   public final URI origin;
   public final Executor worker;
   private final JsonObject machine;
   private final String token;
   private final DeviceIdentity identity;

   public ServerAccount(URI origin, JsonObject machine, String token, DeviceIdentity identity) {
      this.origin = origin;
      this.machine = machine != null ? machine : new JsonObject();
      this.token = token != null ? token : "";
      this.identity = identity;
      this.worker = ForkJoinPool.commonPool();
   }

   public ServerAccount(URI origin, JsonObject machine, String token) {
      this(origin, machine, token, (DeviceIdentity)null);
   }

   public String token() {
      return this.token;
   }

   public JsonObject machine() {
      return this.machine;
   }

   public String session() {
      return this.token;
   }

   public JsonObject call(String method, String path, JsonObject body) throws Exception {
      throw new UnsupportedOperationException("Online account services are disabled in this build");
   }

   public void close() {
      if (this.identity != null) {
         try {
            this.identity.close();
         } catch (Exception ignored) {
         }
      }
   }
}
