// Sourced By DevFFNightmare
package dev.purxiy.social;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public final class FriendList {
   private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
   private static final int VERSION = 1;

   private FriendList() {
   }

   public static FriendList.TypeI read(String var0) {
      ArrayList var1 = new ArrayList();
      FriendList.TypeO var2 = new FriendList.TypeO();
      JsonObject var3 = JsonParser.parseString(var0).getAsJsonObject();
      JsonElement var4 = var3.get("friends");
      if (var4 instanceof JsonArray) {
         for (JsonElement var6 : (JsonArray)var4) {
            try {
               PlayerMetadata var7 = friend(var6.getAsJsonObject());
               if (var7 != null) {
                  var1.add(var7);
               }
            } catch (RuntimeException var8) {
            }
         }
      }

      if (var3.get("me") instanceof JsonObject var10) {
         var2.username = string(var10, "username");
         var2.reported = string(var10, "reported");
      }

      return new FriendList.TypeI(var1, var2);
   }

   
   private static PlayerMetadata friend(JsonObject var0) {
      String var1 = string(var0, "name");
      if (var1 == null) {
         return null;
      } else {
         PlayerMetadata.TypeI var2 = "purxiy".equals(string(var0, "kind")) ? PlayerMetadata.TypeI.PURXIY : PlayerMetadata.TypeI.PLAYER;
         String var3 = string(var0, "id");
         long var4 = var0.has("added") ? var0.get("added").getAsLong() : System.currentTimeMillis();
         PlayerMetadata var6 = new PlayerMetadata(var3 == null ? newId() : var3, var2, var1, var4);
         var6.uuid = uuid(string(var0, "uuid"));
         JsonElement var7 = var0.get("accounts");
         if (var7 instanceof JsonArray) {
            for (JsonElement var9 : (JsonArray)var7) {
               JsonObject var10 = var9.getAsJsonObject();
               UUID var11 = uuid(string(var10, "uuid"));
               String var12 = string(var10, "name");
               if (var11 != null && var12 != null) {
                  var6.accounts.add(new PlayerMetadata.TypeS(var11, var12));
               }
            }
         }

         if (var2 == PlayerMetadata.TypeI.PLAYER) {
            var6.lookup = var6.uuid != null
               ? PlayerMetadata.TypeE.FOUND
               : (var0.has("unknown") && var0.get("unknown").getAsBoolean() ? PlayerMetadata.TypeE.UNKNOWN : PlayerMetadata.TypeE.PENDING);
         } else {
            var6.lookup = PlayerMetadata.TypeE.FOUND;
            var6.gone = var0.has("gone") && var0.get("gone").getAsBoolean();
         }

         var6.seen = var0.has("seen") ? var0.get("seen").getAsLong() : 0L;
         var6.place = string(var0, "place");
         return var6;
      }
   }

   public static String write(List<PlayerMetadata> var0, FriendList.TypeO var1) {
      JsonObject var2 = new JsonObject();
      var2.addProperty("version", 1);
      JsonArray var3 = new JsonArray();

      for (PlayerMetadata var5 : var0) {
         var3.add(json(var5));
      }

      var2.add("friends", var3);
      JsonObject var6 = new JsonObject();
      if (var1.username != null) {
         var6.addProperty("username", var1.username);
      }

      if (var1.reported != null) {
         var6.addProperty("reported", var1.reported);
      }

      var2.add("me", var6);
      return GSON.toJson(var2);
   }

   private static JsonObject json(PlayerMetadata var0) {
      JsonObject var1 = new JsonObject();
      var1.addProperty("id", var0.id);
      var1.addProperty("kind", var0.kind == PlayerMetadata.TypeI.PURXIY ? "purxiy" : "player");
      var1.addProperty("name", var0.name);
      if (var0.uuid != null) {
         var1.addProperty("uuid", var0.uuid.toString());
      }

      if (!var0.accounts.isEmpty()) {
         JsonArray var2 = new JsonArray();

         for (PlayerMetadata.TypeS var4 : var0.accounts) {
            JsonObject var5 = new JsonObject();
            var5.addProperty("uuid", var4.uuid().toString());
            var5.addProperty("name", var4.name());
            var2.add(var5);
         }

         var1.add("accounts", var2);
      }

      if (var0.lookup == PlayerMetadata.TypeE.UNKNOWN) {
         var1.addProperty("unknown", true);
      }

      if (var0.gone) {
         var1.addProperty("gone", true);
      }

      var1.addProperty("added", var0.added);
      if (var0.seen > 0L) {
         var1.addProperty("seen", var0.seen);
      }

      if (var0.place != null) {
         var1.addProperty("place", var0.place);
      }

      return var1;
   }

   public static FriendList.TypeI load(Path var0) {
      if (!Files.isRegularFile(var0)) {
         return new FriendList.TypeI(new ArrayList<>(), new FriendList.TypeO());
      } else {
         try {
            return read(Files.readString(var0, StandardCharsets.UTF_8));
         } catch (IOException | RuntimeException var4) {
            try {
               Files.copy(var0, var0.resolveSibling("friends.unreadable.json"), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException var3) {
            }

            return new FriendList.TypeI(new ArrayList<>(), new FriendList.TypeO());
         }
      }
   }

   public static void save(Path var0, String var1) throws IOException {
      Files.createDirectories(var0.getParent());
      Path var2 = var0.resolveSibling(var0.getFileName() + ".tmp");
      Files.writeString(var2, var1, StandardCharsets.UTF_8);

      try {
         Files.move(var2, var0, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
      } catch (AtomicMoveNotSupportedException var4) {
         Files.move(var2, var0, StandardCopyOption.REPLACE_EXISTING);
      }
   }

   public static String token(Path var0) throws IOException {
      if (Files.isRegularFile(var0)) {
         String var1 = Files.readString(var0, StandardCharsets.US_ASCII).strip();
         if (var1.matches("[\\w-]{32,128}")) {
            return var1;
         }
      }

      byte[] var3 = new byte[32];
      new SecureRandom().nextBytes(var3);
      String var2 = Base64.getUrlEncoder().withoutPadding().encodeToString(var3);
      save(var0, var2);
      return var2;
   }

   static String newId() {
      byte[] var0 = new byte[6];
      new SecureRandom().nextBytes(var0);
      return Base64.getUrlEncoder().withoutPadding().encodeToString(var0);
   }

   
   private static String string(JsonObject var0, String var1) {
      JsonElement var2 = var0.get(var1);
      return var2 != null && var2.isJsonPrimitive() ? var2.getAsString() : null;
   }

   
   private static UUID uuid( String var0) {
      if (var0 == null) {
         return null;
      } else {
         try {
            return UUID.fromString(var0);
         } catch (IllegalArgumentException var2) {
            return null;
         }
      }
   }

   public record TypeI(List<PlayerMetadata> friends, FriendList.TypeO me) {
   }

   public static final class TypeO {
      
      public String username;
      
      public String reported;
   }
}
