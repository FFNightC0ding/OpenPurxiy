package dev.purxiy.social;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.ui.config.ConfigLibraryHost;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_310;
import net.minecraft.class_320;

public final class FriendApi {
   private static final long REFRESH = 600000L;
   private static final long RETRY = 120000L;
   private static final Map<String, Long> refreshedAt = new HashMap<>();
   private static long offlineUntil = 0L;
   private static long reportedAt = -120000L;
   private static boolean busy;

   private FriendApi() {
   }

   public static boolean offline() {
      return System.currentTimeMillis() < offlineUntil;
   }

   public static CompletableFuture<FriendApi.FriendProfile> lookup(String var0) {
      return signed("GET", "/api/friends/users/" + var0, (JsonObject)null);
   }

   public static CompletableFuture<FriendApi.FriendProfile> claim(String var0) {
      JsonObject var1 = new JsonObject();
      var1.addProperty("username", var0);
      var1.add("account", account(class_310.method_1551().method_1548()));
      return signed("PUT", "/api/friends/me", var1).thenApply(var0x -> {
         if (var0x.status() == FriendApi.FriendStatus.OK && var0x.username() != null) {
            FriendList.FriendIdentity var1x = Friends.me();
            var1x.username = var0x.username();
            var1x.reported = reported(class_310.method_1551().method_1548());
            Friends.meChanged();
         }

         return (FriendApi.FriendProfile)var0x;
      });
   }

   public static CompletableFuture<FriendApi.FriendProfile> release() {
      return signed("DELETE", "/api/friends/me", (JsonObject)null).thenApply(var0 -> {
         if (var0.status() != FriendApi.FriendStatus.OK && var0.status() != FriendApi.FriendStatus.NOT_FOUND) {
            return (FriendApi.FriendProfile)var0;
         } else {
            FriendList.FriendIdentity var1 = Friends.me();
            var1.username = null;
            var1.reported = null;
            Friends.meChanged();
            return FriendApi.FriendProfile.of(FriendApi.FriendStatus.OK);
         }
      });
   }

   private static CompletableFuture<FriendApi.FriendProfile> signed(String var0, String var1,  JsonObject var2) {
      String var3;
      try {
         var3 = Friends.token();
      } catch (IOException var5) {
         ClientServices.LOGGER.warn("Friends: no identity key: {}", var5.getMessage());
         return CompletableFuture.completedFuture(FriendApi.FriendProfile.of(FriendApi.FriendStatus.OFFLINE));
      }

      return ConfigLibraryHost.call(var0, var1, var2, var3).handle((var0x, var1x) -> {
         if (var1x != null) {
            return failed(var1x);
         } else {
            reached();
            return answer(var0x);
         }
      });
   }

   private static FriendApi.FriendProfile answer(JsonObject var0) {
      String var1 = var0.has("username") && !var0.get("username").isJsonNull() ? var0.get("username").getAsString() : null;
      ArrayList var2 = new ArrayList();
      JsonElement var3 = var0.get("accounts");
      if (var3 instanceof JsonArray) {
         for (JsonElement var5 : (JsonArray)var3) {
            JsonObject var6 = var5.getAsJsonObject();
            UUID var7 = FriendRequest.parse(var6.get("uuid").getAsString());
            String var8 = var6.get("name").getAsString();
            if (var7 != null && NamePolicy.minecraft(var8)) {
               var2.add(new PlayerMetadata.AccountRef(var7, var8));
            }
         }
      }

      return new FriendApi.FriendProfile(FriendApi.FriendStatus.OK, var1, var2);
   }

   private static FriendApi.FriendProfile failed(Throwable var0) {
      if (ConfigLibraryHost.unreachable(var0)) {
         String var5 = ConfigLibraryHost.refusal(var0);
         if ("slow_down".equals(var5)) {
            return FriendApi.FriendProfile.of(FriendApi.FriendStatus.LIMITED);
         } else {
            offlineUntil = System.currentTimeMillis() + 120000L;
            return FriendApi.FriendProfile.of(FriendApi.FriendStatus.OFFLINE);
         }
      } else {
         String var1 = ConfigLibraryHost.refusal(var0);
         String var3 = var1 == null ? "" : var1;

         return FriendApi.FriendProfile.of(switch (var3) {
            case "username_taken" -> FriendApi.FriendStatus.TAKEN;
            case "link_required", "no_token" -> FriendApi.FriendStatus.LOCKED;
            case "not_found", "no_username" -> FriendApi.FriendStatus.NOT_FOUND;
            default -> FriendApi.FriendStatus.INVALID;
         });
      }
   }

   private static void reached() {
      offlineUntil = 0L;
   }

   private static JsonObject account(class_320 var0) {
      JsonObject var1 = new JsonObject();
      var1.addProperty("uuid", var0.method_44717().toString());
      var1.addProperty("name", var0.method_1676());
      return var1;
   }

   private static String reported(class_320 var0) {
      String var1 = String.valueOf(var0.method_44717());
      return var1 + ":" + var0.method_1676();
   }

   static void tick(class_310 var0, long var1) {
      if (!busy && var1 >= offlineUntil) {
         FriendList.FriendIdentity var3 = Friends.me();
         class_320 var4 = var0.method_1548();
         if (var3.username != null && !reported(var4).equals(var3.reported) && var1 - reportedAt > 120000L) {
            reportedAt = var1;
            busy = true;

            String var9;
            try {
               var9 = Friends.token();
            } catch (IOException var8) {
               busy = false;
               return;
            }

            String var10 = reported(var4);
            ConfigLibraryHost.call("POST", "/api/friends/me/account", account(var4), var9).whenComplete((var3x, var4x) -> var0.execute(() -> {
               busy = false;
               if (var4x == null) {
                  reached();
                  var3.reported = var10;
                  Friends.meChanged();
               } else if (!ConfigLibraryHost.unreachable(var4x) && "no_username".equals(ConfigLibraryHost.refusal(var4x))) {
                  var3.username = null;
                  var3.reported = null;
                  Friends.meChanged();
               } else if (ConfigLibraryHost.unreachable(var4x)) {
                  offlineUntil = System.currentTimeMillis() + 120000L;
               }
            }));
         } else {
            for (PlayerMetadata var6 : Friends.list()) {
               if (var6.kind == PlayerMetadata.PlayerKind.PURXIY && var1 - refreshedAt.getOrDefault(var6.id, 0L) >= 600000L) {
                  refreshedAt.put(var6.id, var1);
                  busy = true;
                  String var7 = var6.name;
                  lookup(var7).whenComplete((var3x, var4x) -> var0.execute(() -> {
                     busy = false;
                     if (var3x != null && var6.name.equals(var7)) {
                        if (var3x.status() == FriendApi.FriendStatus.OK) {
                           Friends.accounts(var6, var3x.accounts());
                        } else if (var3x.status() == FriendApi.FriendStatus.NOT_FOUND) {
                           Friends.gone(var6);
                        }
                     }
                  }));
                  return;
               }
            }
         }
      }
   }

   public static void refreshSoon() {
      refreshedAt.replaceAll((var0, var1) -> Math.min(var1, System.currentTimeMillis() - 600000L + 3000L));
   }

   public static enum FriendStatus {
      OK,
      TAKEN,
      INVALID,
      NOT_FOUND,
      LIMITED,
      LOCKED,
      OFFLINE;

      private static FriendApi.FriendStatus[] $values() {
         return new FriendApi.FriendStatus[]{OK, TAKEN, INVALID, NOT_FOUND, LIMITED, LOCKED, OFFLINE};
      }
   }

   public record FriendProfile(FriendApi.FriendStatus status,  String username, List<PlayerMetadata.AccountRef> accounts) {
      static FriendApi.FriendProfile of(FriendApi.FriendStatus var0) {
         return new FriendApi.FriendProfile(var0, (String)null, List.of());
      }
   }
}
