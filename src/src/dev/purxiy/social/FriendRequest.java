// Sourced By DevFFNightmare
package dev.purxiy.social;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.purxiy.loader.fabric.OfflineNetwork;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

final class FriendRequest {
   private static final String API = "https://api.mojang.com/users/profiles/minecraft/";
   private static final HttpClient HTTP = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5L)).followRedirects(Redirect.NORMAL).build();

   private FriendRequest() {
   }

   static CompletableFuture<FriendRequest.TypeJ> ask(String var0) {
      if (!var0.startsWith(".") && !var0.startsWith("*")) {
         HttpRequest var1 = HttpRequest.newBuilder(
               URI.create("https://api.mojang.com/users/profiles/minecraft/" + URLEncoder.encode(var0, StandardCharsets.UTF_8))
            )
            .timeout(Duration.ofSeconds(10L))
            .header("Accept", "application/json")
            .GET()
            .build();
         return OfflineNetwork.sendAsync(HTTP, var1, BodyHandlers.ofString(StandardCharsets.UTF_8))
            .handle(
               (var0x, var1x) -> {
                  if (var1x != null) {
                     return new FriendRequest.TypeJ(PlayerMetadata.TypeE.FAILED, (UUID)null, (String)null);
                  } else {
                     int var2 = var0x.statusCode();
                     if (var2 == 404 || var2 == 204) {
                        return new FriendRequest.TypeJ(PlayerMetadata.TypeE.UNKNOWN, (UUID)null, (String)null);
                     } else if (var2 != 200) {
                        return new FriendRequest.TypeJ(PlayerMetadata.TypeE.FAILED, (UUID)null, (String)null);
                     } else {
                        try {
                           JsonObject var3 = JsonParser.parseString(var0x.body()).getAsJsonObject();
                           UUID var4 = parse(var3.get("id").getAsString());
                           String var5 = var3.get("name").getAsString();
                           return var4 == null
                              ? new FriendRequest.TypeJ(PlayerMetadata.TypeE.FAILED, (UUID)null, (String)null)
                              : new FriendRequest.TypeJ(PlayerMetadata.TypeE.FOUND, var4, var5);
                        } catch (RuntimeException var6) {
                           return new FriendRequest.TypeJ(PlayerMetadata.TypeE.FAILED, (UUID)null, (String)null);
                        }
                     }
                  }
               }
            );
      } else {
         return CompletableFuture.completedFuture(new FriendRequest.TypeJ(PlayerMetadata.TypeE.UNKNOWN, (UUID)null, (String)null));
      }
   }

   
   static UUID parse(String var0) {
      String var1 = var0.replace("-", "");
      if (var1.length() != 32) {
         return null;
      } else {
         try {
            return new UUID(Long.parseUnsignedLong(var1.substring(0, 16), 16), Long.parseUnsignedLong(var1.substring(16), 16));
         } catch (NumberFormatException var3) {
            return null;
         }
      }
   }

   record TypeJ(PlayerMetadata.TypeE lookup,  UUID uuid,  String name) {
   }
}
