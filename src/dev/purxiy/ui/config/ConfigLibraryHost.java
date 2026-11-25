package dev.purxiy.ui.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.purxiy.loader.fabric.AccountGate;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.OfflineNetwork;
import dev.purxiy.loader.net.BoundedBody;
import dev.purxiy.social.Friends;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodySubscribers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import net.fabricmc.loader.api.FabricLoader;

public final class ConfigLibraryHost {

   static final String HOSTED = hosted();
   private static final List<String> LOCAL = List.of("http://127.0.0.1:8787", "http://127.0.0.1:5173", "http://[::1]:5173");
   private static final Duration TIMEOUT = Duration.ofSeconds(15L);
   private static final String AGENT = "Purxiy/" + version();
   private static final HttpClient HTTP = HttpClient.newBuilder()
      .version(Version.HTTP_1_1)
      .connectTimeout(Duration.ofMillis(2500L))
      .followRedirects(Redirect.NEVER)
      .build();

   private static volatile String base = configured();
   private static volatile long probedAt;

   private static volatile Boolean online;
   private static volatile boolean shareCodes;
   private static volatile boolean checking;
   private static volatile long checkedAt;

   private static volatile String client;

   private ConfigLibraryHost() {
   }


   private static String configured() {
      return AccountGate.origin().toString();
   }


   private static String hosted() {
      return AccountGate.origin().toString();
   }

   private static String version() {
      try {
         return FabricLoader.getInstance().getModContainer("purxiy").map(var0 -> var0.getMetadata().getVersion().getFriendlyString()).orElse("unknown");
      } catch (IllegalStateException | NullPointerException var1) {
         return "unavailable";
      }
   }

   private static boolean trustedOrigin(String var0) {
      try {
         URI var1 = URI.create(var0);
         return "https".equals(var1.getScheme())
            && var1.getHost() != null
            && var1.getUserInfo() == null
            && var1.getRawQuery() == null
            && var1.getRawFragment() == null
            && (var1.getPath().isEmpty() || var1.getPath().equals("/"));
      } catch (IllegalArgumentException var2) {
         return false;
      }
   }


   public static String site() {
      String var0 = base;
      return var0 != null ? var0 : configured();
   }

   private static String strip(String var0) {
      return var0.endsWith("/") ? var0.substring(0, var0.length() - 1) : var0;
   }

   static boolean local() {
      return false;
   }

   private static CompletableFuture<String> base() {
      String var0 = base;
      return var0 != null ? CompletableFuture.completedFuture(var0) : CompletableFuture.failedFuture(new IOException("No pinned production server origin"));
   }

   private static CompletableFuture<String> probe(int var0) {
      if (var0 >= LOCAL.size()) {
         probedAt = System.currentTimeMillis();
         online = false;
         return CompletableFuture.failedFuture(new IOException("No SourceByDevFFnightmare DeObfDeCompByGuideX server on this PC"));
      } else {
         String var1 = LOCAL.get(var0);
         HttpRequest var2 = HttpRequest.newBuilder(URI.create(var1 + "/api/configs/status")).timeout(Duration.ofSeconds(2L)).GET().build();
         return OfflineNetwork.sendAsync(HTTP, var2, textBody(4096)).handle((var2x, var3) -> {
            if (var3 == null && var2x.statusCode() == 200 && var2x.body().contains("\"slots\"")) {
               base = var1;
               ClientServices.LOGGER.info("Configs: found the SourceByDevFFnightmare DeObfDeCompByGuideX server at {}", var1);
               return CompletableFuture.completedFuture(var1);
            } else {
               return probe(var0 + 1);
            }
         }).thenCompose(var0x -> (CompletionStage<String>)var0x);
      }
   }

   private static void lost() {
      if (configured() == null) {
         base = null;
      }
   }


   private static String client() {
      String var0 = client;
      if (var0 == null) {
         try {
            client = var0 = Friends.secret();
         } catch (IOException | RuntimeException var2) {
            return null;
         }
      }

      return var0;
   }

   public static CompletableFuture<JsonObject> call(String var0, String var1,  JsonObject var2,  String var3) {
      return base().thenCompose(var4 -> {
         Builder var5 = HttpRequest.newBuilder(URI.create(var4 + var1)).timeout(TIMEOUT).header("Accept", "application/json").header("User-Agent", AGENT);
         if (var3 != null) {
            var5.header("Authorization", "Bearer " + var3);
         }

         if (var1.startsWith("/api/configs")) {
            String var6 = client();
            if (var6 != null) {
               var5.header("X-Purxiy-Client", var6);
            }
         }

         if (var2 != null) {
            var5.header("Content-Type", "application/json; charset=utf-8");
            var5.method(var0, BodyPublishers.ofString(var2.toString(), StandardCharsets.UTF_8));
         } else {
            var5.method(var0, !var0.equals("GET") && !var0.equals("DELETE") ? BodyPublishers.ofString("{}") : BodyPublishers.noBody());
         }

         return send(var5.build());
      });
   }

   static CompletableFuture<JsonObject> photo(String var0, byte[] var1, String var2) {
      return base()
         .thenCompose(
            var3 -> send(
               HttpRequest.newBuilder(URI.create(var3 + "/api/configs/" + var0 + "/photo"))
                  .timeout(Duration.ofSeconds(40L))
                  .header("Content-Type", "image/jpeg")
                  .header("Authorization", "Bearer " + var2)
                  .header("User-Agent", AGENT)
                  .PUT(BodyPublishers.ofByteArray(var1))
                  .build()
            )
         );
   }

   static CompletableFuture<byte[]> bytes(String var0) {
      return base()
         .thenCompose(
            var1 -> OfflineNetwork.sendAsync(
                  HTTP, HttpRequest.newBuilder(URI.create(var1 + var0)).timeout(Duration.ofSeconds(30L)).GET().build(), BoundedBody.handler(8388608)
               )
               .handle((var0xx, var1x) -> {
                  if (var1x != null) {
                     lost();
                     throw new CompletionException((Throwable)var1x);
                  } else if (var0xx.statusCode() != 200) {
                     throw new ConfigLibraryHost.LibraryException(var0xx.statusCode(), "status");
                  } else {
                     return var0xx.body();
                  }
               })
         );
   }

   private static CompletableFuture<JsonObject> send(HttpRequest var0) {
      CompletableFuture var1 = OfflineNetwork.sendAsync(HTTP, var0, textBody(1048576));
      return var1.<CompletableFuture<HttpResponse<String>>>handle((var1x, var2) -> {
         if (stale((Throwable)(var2))) {
            return OfflineNetwork.sendAsync(HTTP, var0, textBody(1048576));
         } else {
            return var2 != null ? CompletableFuture.failedFuture((Throwable)(var2)) : CompletableFuture.completedFuture((HttpResponse<String>)var1x);
         }
      }).thenCompose(var0x -> (CompletionStage<HttpResponse<String>>)var0x).handle((var0x, var1x) -> {
         if (var1x != null) {
            lost();
            online = false;
            throw new CompletionException((Throwable)var1x);
         } else {
            online = true;

            JsonObject var2;
            try {
               JsonElement var3 = JsonParser.parseString(new String(((java.net.http.HttpResponse<byte[]>)(Object)var0x).body(), java.nio.charset.StandardCharsets.UTF_8));
               var2 = var3.isJsonObject() ? var3.getAsJsonObject() : new JsonObject();
            } catch (RuntimeException var4) {
               var2 = new JsonObject();
            }

            if (((java.net.http.HttpResponse<byte[]>)(Object)var0x).statusCode() >= 200 && ((java.net.http.HttpResponse<byte[]>)(Object)var0x).statusCode() < 300) {
               return var2;
            } else {
               String var5 = var2.has("error") ? var2.get("error").getAsString() : "status";
               throw new ConfigLibraryHost.LibraryException(((java.net.http.HttpResponse<byte[]>)(Object)var0x).statusCode(), var5);
            }
         }
      });
   }

   private static BodyHandler<String> textBody(int var0) {
      return var1 -> BodySubscribers.mapping(BoundedBody.handler(var0).apply(var1), var0xx -> new String(var0xx, StandardCharsets.UTF_8));
   }

   private static boolean stale( Throwable var0) {
      Throwable var1 = var0 instanceof CompletionException && var0.getCause() != null ? var0.getCause() : var0;
      return var1 instanceof IOException && !(var1 instanceof HttpTimeoutException) && !(var1 instanceof ConnectException);
   }

   public static boolean unreachable(Throwable var0) {
      return !(
         (var0 instanceof CompletionException && var0.getCause() != null ? var0.getCause() : var0) instanceof ConfigLibraryHost.LibraryException var3
            && var3.status != 429
            && var3.status < 500
      );
   }


   public static String refusal(Throwable var0) {
      String var2;
      if ((var0 instanceof CompletionException && var0.getCause() != null ? var0.getCause() : var0) instanceof ConfigLibraryHost.LibraryException var3) {
         var2 = var3.code;
      } else {
         var2 = null;
      }

      return var2;
   }

   static long missedAt() {
      return base == null ? probedAt : 0L;
   }

   static void checkStatus(boolean var0) {
      long var1 = System.currentTimeMillis();
      if (!checking && (var0 || var1 - checkedAt >= 20000L)) {
         checking = true;
         checkedAt = var1;
         call("GET", "/api/configs/status", (JsonObject)null, (String)null).whenComplete((var0x, var1x) -> {
            boolean var2;
            label20: {
               online = var1x == null;
               if (var1x == null) {
                  JsonElement var3 = var0x.get("shareCodes");
                  if (var3 instanceof JsonElement && var3.isJsonPrimitive() && var3.getAsBoolean()) {
                     var2 = true;
                     break label20;
                  }
               }

               var2 = false;
            }

            shareCodes = var2;
            checkedAt = System.currentTimeMillis();
            checking = false;
         });
      }
   }

   static boolean online() {
      Boolean var0 = online;
      return var0 != null && var0;
   }

   static boolean unknown() {
      return online == null;
   }

   static boolean shareCodes() {
      return online() && shareCodes;
   }

   static final class LibraryException extends RuntimeException {
      final int status;
      final String code;

      LibraryException(int var1, String var2) {
         super(var1 + " " + var2);
         this.status = var1;
         this.code = var2;
      }
   }
}
