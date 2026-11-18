// Sourced By DevFFNightmare
package dev.purxiy.ui.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.purxiy.loader.fabric.ClientServices;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.class_310;

final class ConfigLibraryUpdater {
   private static final long REFRESH_MS = 30000L;
   private static final long[] BACKOFF = new long[]{3000L, 8000L, 20000L, 45000L, 90000L};
   private static final ScheduledExecutorService TIMER = Executors.newSingleThreadScheduledExecutor(var0 -> {
      Thread var1 = new Thread(var0, "Purxiy Configs");
      var1.setDaemon(true);
      return var1;
   });
   private static final List<ConfigLibraryFactory2> COMMUNITY = new ArrayList<>();
   private static final Map<String, ConfigLibraryFactory2> BY_CODE = new HashMap<>();
   private static ConfigLibraryUpdater.TypeB state = ConfigLibraryUpdater.TypeB.IDLE;
   private static boolean listing;
   private static long listedAt = 0L;
   private static boolean busy;
   
   private static String working;
   private static boolean reconciled;
   private static int failures = 0;
   private static long retryAt = 0L;
   private static boolean timerSet;
   private static final Map<String, String> REFUSED = new HashMap<>();
   private static final Deque<String[]> NOTICES = new ArrayDeque<>();

   private ConfigLibraryUpdater() {
   }

   private static void main(Runnable var0) {
      class_310.method_1551().execute(var0);
   }

   
   static String[] notice() {
      return NOTICES.poll();
   }

   private static void tell(String var0, String var1) {
      if (NOTICES.size() < 6) {
         NOTICES.add(new String[]{var0, var1});
      }
   }

   static ConfigLibraryUpdater.TypeG status(ConfigLibraryFactory2 var0) {
      if (REFUSED.containsKey(var0.id) && REFUSED.get(var0.id).equals(signature(var0))) {
         return ConfigLibraryUpdater.TypeG.REFUSED;
      } else if (!var0.unsynced()) {
         return ConfigLibraryUpdater.TypeG.ONLINE;
      } else if (busy && var0.id.equals(working)) {
         return ConfigLibraryUpdater.TypeG.SENDING;
      } else {
         return failures > 0 ? ConfigLibraryUpdater.TypeG.WAITING : ConfigLibraryUpdater.TypeG.SENDING;
      }
   }

   static boolean unreachable() {
      return failures > 0 || state == ConfigLibraryUpdater.TypeB.OFFLINE;
   }

   private static String signature(ConfigLibraryFactory2 var0) {
      return var0.edited + "|" + var0.photo;
   }

   static void kick() {
      if (!busy) {
         long var0 = System.currentTimeMillis();
         if (var0 < retryAt) {
            later(retryAt - var0);
         } else {
            List var2 = ConfigLibraryTransform.shared();
            String var3 = ConfigLibraryTransform.nextUnpublish();
            boolean var4 = var3 != null;

            for (ConfigLibraryFactory2 var6 : (Iterable<ConfigLibraryFactory2>)(Object)(var2)) {
               var4 |= var6.unsynced() && !refused(var6);
            }

            if (!var4) {
               long var7 = ConfigLibraryTransform.unpublishDue();
               if (var7 != Long.MAX_VALUE) {
                  later(var7 - var0);
               }
            } else if (!reconciled) {
               reconcile();
            } else if (var3 != null) {
               unpublish(var3);
            } else {
               for (ConfigLibraryFactory2 var9 : (Iterable<ConfigLibraryFactory2>)(Object)(var2)) {
                  if (var9.unsynced() && !refused(var9)) {
                     push(var9);
                     return;
                  }
               }
            }
         }
      }
   }

   private static boolean refused(ConfigLibraryFactory2 var0) {
      return signature(var0).equals(REFUSED.get(var0.id));
   }

   private static void later(long var0) {
      if (!timerSet) {
         timerSet = true;
         TIMER.schedule(() -> main(() -> {
            timerSet = false;
            kick();
         }), Math.max(50L, var0), TimeUnit.MILLISECONDS);
      }
   }

   private static void run( String var0, CompletableFuture<JsonObject> var1, BiConsumer<JsonObject, Throwable> var2) {
      busy = true;
      working = var0;
      var1.whenComplete((var1x, var2x) -> main(() -> {
         busy = false;
         working = null;
         if (var2x != null && ConfigLibrarySupport3.unreachable(var2x)) {
            failures++;
            retryAt = System.currentTimeMillis() + BACKOFF[Math.min(BACKOFF.length - 1, failures - 1)];
            later(retryAt - System.currentTimeMillis());
         } else {
            failures = 0;
            retryAt = 0L;

            try {
               var2.accept(var1x, var2x);
            } catch (RuntimeException var4) {
               ClientServices.LOGGER.error("Configs: could not take in the server's answer", var4);
            }

            kick();
         }
      }));
   }

   private static void reconcile() {
      run((String)null, ConfigLibrarySupport3.call("GET", "/api/configs/mine", (JsonObject)null, ConfigLibraryLoader.token()), (var0, var1) -> {
         if (var1 != null) {
            reconciled = true;
         } else {
            reconciled = true;
            HashSet var2 = new HashSet();

            for (JsonElement var4 : array(var0, "configs")) {
               var2.add(var4.getAsJsonObject().get("code").getAsString());
            }

            HashSet var6 = new HashSet();

            for (ConfigLibraryFactory2 var5 : ConfigLibraryTransform.mine()) {
               if (var5.code != null) {
                  var6.add(var5.code);
               }
            }

            for (ConfigLibraryFactory2 var10 : new ArrayList<>(ConfigLibraryTransform.shared())) {
               if (var10.code != null && var10.synced > 0L && !var2.contains(var10.code)) {
                  ConfigLibraryTransform.lost(var10);
                  tell("cloud-off", "“" + var10.name + "” is no longer online");
               }
            }

            for (String var11 : (Iterable<String>)(Object)(var2)) {
               if (!var6.contains(var11)) {
                  ConfigLibraryTransform.forget(var11);
               }
            }
         }
      });
   }

   private static void unpublish(String var0) {
      run(
         (String)null,
         ConfigLibrarySupport3.call("DELETE", "/api/configs/" + var0, (JsonObject)null, ConfigLibraryLoader.token()),
         (var1, var2) -> ConfigLibraryTransform.unpublished(var0)
      );
   }

   private static void push(ConfigLibraryFactory2 var0) {
      long var1 = var0.edited;
      String var3 = var0.photo;
      String var4 = ConfigLibraryLoader.token();
      if (var0.code == null) {
         run(var0.id, ConfigLibrarySupport3.call("POST", "/api/configs", payload(var0), var4), (var3x, var4x) -> {
            if (var4x != null) {
               refusedBy(var0, var4x);
            } else {
               JsonObject var5x = var3x.getAsJsonObject("config");
               var0.code = var5x.get("code").getAsString();
               var0.synced = var1;
               var0.syncedPhoto = null;
               var0.likes = var5x.get("likes").getAsInt();
               var0.downloads = var5x.get("downloads").getAsInt();
               ConfigLibraryTransform.persist();
            }
         });
      } else if (var0.synced != var1) {
         run(var0.id, ConfigLibrarySupport3.call("PUT", "/api/configs/" + var0.code, payload(var0), var4), (var3x, var4x) -> {
            if (var4x != null) {
               refusedBy(var0, var4x);
            } else {
               var0.synced = var1;
               ConfigLibraryTransform.persist();
            }
         });
      } else if (var3 != null) {
         byte[] var5 = ConfigLibraryRenderer13.read(var0);
         if (var5 == null) {
            var0.photo = null;
            ConfigLibraryTransform.changed(var0);
            return;
         }

         run(var0.id, ConfigLibrarySupport3.photo(var0.code, var5, var4), (var2, var3x) -> {
            if (var3x != null) {
               refusedBy(var0, var3x);
            } else {
               var0.syncedPhoto = var3;
               ConfigLibraryTransform.persist();
            }
         });
      } else {
         run(var0.id, ConfigLibrarySupport3.call("DELETE", "/api/configs/" + var0.code + "/photo", (JsonObject)null, var4), (var1x, var2) -> {
            if (var2 != null) {
               refusedBy(var0, var2);
            } else {
               var0.syncedPhoto = null;
               ConfigLibraryTransform.persist();
            }
         });
      }
   }

   private static void refusedBy(ConfigLibraryFactory2 var0, Throwable var1) {
      String var2 = ConfigLibrarySupport3.refusal(var1);
      if ("not_found".equals(var2) && var0.code != null) {
         ConfigLibraryTransform.lost(var0);
         tell("cloud-off", "“" + var0.name + "” is no longer online");
      } else if ("slots_full".equals(var2)) {
         if (reconciled) {
            reconciled = false;
            REFUSED.put(var0.id, signature(var0));
            ConfigLibraryTransform.unshare(var0);
            tell("circle-alert", "All your online slots are taken: “" + var0.name + "” stays offline");
         }
      } else {
         REFUSED.put(var0.id, signature(var0));
         String var4 = var2 == null ? "" : var2;

         String var3 = switch (var4) {
            case "too_large" -> "too large to share";
            case "not_jpeg", "photo_size" -> "its photo could not be sent";
            default -> "the server would not take it";
         };
         ClientServices.LOGGER.warn("Configs: the server refused {} ({})", var0.name, var2);
         tell("circle-alert", "“" + var0.name + "” is not online: " + var3);
      }
   }

   private static JsonObject payload(ConfigLibraryFactory2 var0) {
      JsonObject var1 = ConfigLibraryTransform.body(var0);
      var1.remove("v");
      JsonArray var2 = new JsonArray();

      for (String var4 : ConfigLibraryFactory.of(var0.modules)) {
         var2.add(var4);
      }

      var1.add("tags", var2);
      if (!var1.has("settings")) {
         var1.add("settings", new JsonObject());
      }

      return var1;
   }

   static ConfigLibraryUpdater.TypeB state() {
      return state;
   }

   static List<ConfigLibraryFactory2> community() {
      return Collections.unmodifiableList(COMMUNITY);
   }

   static void refresh(boolean var0) {
      long var1 = System.currentTimeMillis();
      if (!listing && (var0 || var1 - listedAt >= 30000L) && (!var0 || var1 - listedAt >= 1500L)) {
         listing = true;
         listedAt = var1;
         if (COMMUNITY.isEmpty()) {
            state = ConfigLibraryUpdater.TypeB.LOADING;
         }

         ConfigLibrarySupport3.call("GET", "/api/configs", (JsonObject)null, ConfigLibraryLoader.token()).whenComplete((var0x, var1x) -> main(() -> {
            listing = false;
            listedAt = System.currentTimeMillis();
            if (var1x == null) {
               take(var0x);
               state = ConfigLibraryUpdater.TypeB.READY;
               failures = 0;
               retryAt = 0L;
               kick();
            } else if (COMMUNITY.isEmpty() || ConfigLibrarySupport3.unreachable(var1x)) {
               state = ConfigLibraryUpdater.TypeB.OFFLINE;
            }
         }));
      }
   }

   private static void take(JsonObject var0) {
      HashSet var1 = new HashSet();

      for (JsonElement var3 : array(var0, "featured")) {
         var1.add(var3.getAsString());
      }

      ArrayList var9 = new ArrayList();
      LinkedHashSet var10 = new LinkedHashSet();

      for (JsonElement var5 : array(var0, "configs")) {
         JsonObject var6 = var5.getAsJsonObject();
         String var7 = var6.get("code").getAsString();
         if (var10.add(var7)) {
            ConfigLibraryFactory2 var8 = BY_CODE.computeIfAbsent(var7, var1x -> fresh(var6));
            update(var8, var6);
            var8.featured = var1.contains(var7);
            var9.add(var8);
         }
      }

      BY_CODE.keySet().retainAll(var10);
      COMMUNITY.clear();
      COMMUNITY.addAll(var9);

      for (ConfigLibraryFactory2 var12 : ConfigLibraryTransform.mine()) {
         ConfigLibraryFactory2 var13 = var12.code == null ? null : BY_CODE.get(var12.code);
         if (var13 != null && var13.own) {
            var12.likes = var13.likes;
            var12.downloads = var13.downloads;
         }
      }
   }

   private static ConfigLibraryFactory2 fresh(JsonObject var0) {
      return new ConfigLibraryFactory2(
         var0.get("code").getAsString(),
         "",
         "",
         "",
         List.of(),
         new String[0],
         ConfigLibraryFactory2.TypeT.COMMUNITY,
         var0.has("createdAt") ? var0.get("createdAt").getAsLong() : System.currentTimeMillis(),
         0.5F
      );
   }

   private static void update(ConfigLibraryFactory2 var0, JsonObject var1) {
      var0.code = var1.get("code").getAsString();
      var0.name = ConfigLibraryTransform.clean(text(var1, "name"));
      var0.author = text(var1, "author");
      var0.description = text(var1, "description");
      var0.hue = var1.has("hue") ? Math.max(0.0F, Math.min(1.0F, var1.get("hue").getAsFloat())) : 0.5F;
      LinkedHashSet var2 = new LinkedHashSet();

      for (JsonElement var4 : array(var1, "modules")) {
         var2.add(var4.getAsString());
      }

      if (!var2.equals(var0.modules)) {
         var0.modules.clear();
         var0.modules.addAll(var2);
         var0.modulesChanged();
      }

      ArrayList var7 = new ArrayList();

      for (JsonElement var5 : array(var1, "tags")) {
         var7.add(var5.getAsString());
      }

      var0.tags = (String[])(var7.toArray(String[]::new));
      String var11;
      if (var1.get("photo") instanceof JsonObject var6) {
         var11 = var6.get("hash").getAsString();
      } else {
         var11 = null;
      }

      var0.photo = var11;
      var0.likes = var1.has("likes") ? var1.get("likes").getAsInt() : 0;
      var0.downloads = var1.has("downloads") ? var1.get("downloads").getAsInt() : 0;
      var0.liked = var1.has("liked") && var1.get("liked").getAsBoolean();
      var0.own = var1.has("mine") && var1.get("mine").getAsBoolean();
      var0.added = var1.has("added") && var1.get("added").getAsBoolean() || ConfigLibraryTransform.addedFrom(var0.code);
      var0.edited = var1.has("updatedAt") ? var1.get("updatedAt").getAsLong() : var0.created;
      if (var1.get("settings") instanceof JsonObject var12) {
         var0.settings = var12;
      }
   }

   static void get(String var0, ConfigLibraryFactory2.TypeT var1, BiConsumer<ConfigLibraryFactory2, String> var2) {
      ConfigLibrarySupport3.call("POST", "/api/configs/" + var0.toUpperCase(Locale.ROOT) + "/get", (JsonObject)null, ConfigLibraryLoader.token())
         .whenComplete((var2x, var3) -> main(() -> {
            if (var3 != null) {
               var2.accept(null, ConfigLibrarySupport3.unreachable(var3) ? "offline" : Objects.requireNonNullElse(ConfigLibrarySupport3.refusal(var3), "error"));
            } else {
               JsonObject var4 = var2x.getAsJsonObject("config");
               ConfigLibraryFactory2 var5 = BY_CODE.get(var4.get("code").getAsString());
               if (var5 == null) {
                  var5 = fresh(var4);
               }

               update(var5, var4);
               var5.added = true;
               var2.accept(ConfigLibraryTransform.adopt(var5, var1), null);
            }
         }));
   }

   static void like(ConfigLibraryFactory2 var0, Consumer<String> var1) {
      if (var0.code != null) {
         boolean var2 = !var0.liked;
         var0.liked = var2;
         var0.likes = Math.max(0, var0.likes + (var2 ? 1 : -1));
         JsonObject var3 = new JsonObject();
         var3.addProperty("liked", var2);
         ConfigLibrarySupport3.call("POST", "/api/configs/" + var0.code + "/like", var3, ConfigLibraryLoader.token())
            .whenComplete((var3x, var4) -> main(() -> {
               if (var4 != null) {
                  if (var0.liked == var2) {
                     var0.liked = !var2;
                     var0.likes = Math.max(0, var0.likes + (var2 ? -1 : 1));
                  }

                  String var5 = ConfigLibrarySupport3.refusal(var4);
                  if (var5 != null) {
                     var1.accept(var5);
                  }
               } else {
                  JsonObject var6 = var3x.getAsJsonObject("config");
                  var0.likes = var6.get("likes").getAsInt();
                  var0.liked = var6.get("liked").getAsBoolean();
               }
            }));
      }
   }

   static void report(ConfigLibraryFactory2 var0, BiConsumer<Boolean, String> var1) {
      if (var0.code != null) {
         ConfigLibrarySupport3.call("POST", "/api/configs/" + var0.code + "/report", (JsonObject)null, ConfigLibraryLoader.token())
            .whenComplete((var1x, var2) -> main(() -> var1.accept(var2 == null, var2 == null ? null : ConfigLibrarySupport3.refusal(var2))));
      }
   }

   private static JsonArray array(JsonObject var0, String var1) {
      JsonArray var3;
      if (var0.get(var1) instanceof JsonArray var4) {
         var3 = var4;
      } else {
         var3 = new JsonArray();
      }

      return var3;
   }

   private static String text(JsonObject var0, String var1) {
      JsonElement var2 = var0.get(var1);
      return var2 != null && var2.isJsonPrimitive() ? var2.getAsString() : "";
   }

   public static void deleteOwn(ConfigLibraryFactory2 var0, BiConsumer<Boolean, String> var1) {
      if (var0.own && var0.code != null && var0.code.matches("PRX-[A-Z2-9]{4}-[A-Z2-9]{4}")) {
         String var2 = var0.code;
         ConfigLibrarySupport3.call("DELETE", "/api/configs/" + var2, (JsonObject)null, ConfigLibraryLoader.token()).whenComplete((var2x, var3) -> main(() -> {
            if (var3 != null && !"not_found".equals(ConfigLibrarySupport3.refusal(var3))) {
               var1.accept(false, ConfigLibrarySupport3.unreachable(var3) ? "offline" : ConfigLibrarySupport3.refusal(var3));
            } else {
               COMMUNITY.removeIf(var1xxx -> var2.equals(var1xxx.code));
               BY_CODE.remove(var2);

               for (ConfigLibraryFactory2 var4 : new ArrayList<>(ConfigLibraryTransform.mine())) {
                  if (var2.equals(var4.code)) {
                     ConfigLibraryTransform.lost(var4);
                  }
               }

               var1.accept(true, null);
            }
         }));
      } else {
         var1.accept(false, "not_owner");
      }
   }

   static enum TypeB {
      IDLE,
      LOADING,
      READY,
      OFFLINE;

      private static ConfigLibraryUpdater.TypeB[] $values() {
         return new ConfigLibraryUpdater.TypeB[]{IDLE, LOADING, READY, OFFLINE};
      }
   }

   static enum TypeG {
      ONLINE,
      SENDING,
      WAITING,
      REFUSED;

      private static ConfigLibraryUpdater.TypeG[] $values() {
         return new ConfigLibraryUpdater.TypeG[]{ONLINE, SENDING, WAITING, REFUSED};
      }
   }
}
