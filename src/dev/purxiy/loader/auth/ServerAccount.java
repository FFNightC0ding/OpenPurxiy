package dev.purxiy.loader.auth;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
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
      URI target = this.origin.resolve(path);
      String payload = body != null ? body.toString() : "{}";
      HttpRequest.Builder builder = HttpRequest.newBuilder(target)
         .timeout(Duration.ofSeconds(20))
         .header("Content-Type", "application/json");
      if (this.token != null && !this.token.isEmpty()) {
         builder.header("Authorization", "Bearer " + this.token);
      }
      String verb = method == null ? "POST" : method.toUpperCase(java.util.Locale.ROOT);
      if (verb.equals("GET") || verb.equals("HEAD") || verb.equals("DELETE")) {
         builder.method(verb, HttpRequest.BodyPublishers.noBody());
      } else {
         builder.method(verb, HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8));
      }
      HttpResponse<String> response = HttpClient.newHttpClient().send(
         builder.build(), HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
      if (response.statusCode() < 200 || response.statusCode() >= 300) {
         throw new IOException("Account call failed: HTTP " + response.statusCode());
      }
      JsonElement parsed = JsonParser.parseString(response.body());
      return parsed != null && parsed.isJsonObject() ? parsed.getAsJsonObject() : new JsonObject();
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
