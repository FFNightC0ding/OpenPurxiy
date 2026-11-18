// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.PushPromiseHandler;
import java.util.concurrent.CompletableFuture;

public final class OfflineNetwork {
   private static IOException denied(HttpRequest var0) {
      if (var0 != null) {
         URI var1 = var0.uri();
         PrintStream var2 = System.out;
         String var3 = var0.method();
         var2.println(
            "[Purxiy Offline Network] Blocked " + var3 + " to " + var1.getScheme() + "://" + var1.getHost() + (var1.getPort() < 0 ? "" : ":" + var1.getPort())
         );
      }

      return new IOException("Network disabled in Purxiy Offline");
   }

   public static <T> HttpResponse<T> send(HttpClient var0, HttpRequest var1, BodyHandler<T> var2) throws IOException {
      throw denied(var1);
   }

   public static <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpClient var0, HttpRequest var1, BodyHandler<T> var2) {
      return CompletableFuture.failedFuture(denied(var1));
   }

   public static <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpClient var0, HttpRequest var1, BodyHandler<T> var2, PushPromiseHandler<T> var3) {
      return CompletableFuture.failedFuture(denied(var1));
   }
}
