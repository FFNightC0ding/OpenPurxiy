package dev.purxiy.social;

import com.mojang.authlib.GameProfile;
import dev.purxiy.hud.HudLayout;
import dev.purxiy.loader.fabric.AccountGate;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.TabListProvider;
import dev.purxiy.modules.SettingSection;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents.ClientStopping;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents.Disconnect;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1132;
import net.minecraft.class_11891;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_310;
import net.minecraft.class_3966;
import net.minecraft.class_634;
import net.minecraft.class_640;
import net.minecraft.class_642;
import net.minecraft.class_742;
import net.minecraft.class_9296;
import net.minecraft.class_9334;

public final class Friends {
   private static final FriendStore BOOK = new FriendStore();
   private static final long SAVE_SOON = 2000L;
   private static final long SAVE_SEEN = 60000L;
   private static final long RETRY = 60000L;
   private static FriendList.FriendIdentity me = new FriendList.FriendIdentity();
   private static Map<PlayerMetadata, Friends.FriendEntry> presence = Map.of();
   private static final Map<String, Long> failedAt = new HashMap<>();
   private static final Map<String, Friends.FriendEntry> devPresence = new HashMap<>();
   private static int asking = 0;
   private static boolean dirty;
   private static boolean seenDirty;
   private static long dirtyAt = 0L;
   private static long seenSavedAt = 0L;
   private static int ticks = 0;

   private Friends() {
   }

   public static void init() {
      FriendList.FriendRoster var0 = FriendList.load(file());

      for (int var1 = var0.friends().size() - 1; var1 >= 0; var1--) {
         BOOK.add(var0.friends().get(var1));
      }

      me = var0.me();
      ClientTickEvents.END_CLIENT_TICK.register(Friends::tick);
      ClientPlayConnectionEvents.DISCONNECT.register((Disconnect)(var0x, var1x) -> var1x.execute(() -> {
         presence = Map.of();
         save();
      }));
      ClientLifecycleEvents.CLIENT_STOPPING.register((ClientStopping)var0x -> save());
      if (!BOOK.isEmpty()) {
         ClientServices.LOGGER.info("Friends: {} on the list", BOOK.size());
      }
   }

   static Path folder() {
      return FabricLoader.getInstance().getConfigDir().resolve("purxiy").resolve("friends");
   }

   private static Path file() {
      return folder().resolve("friends.json");
   }

   public static List<PlayerMetadata> list() {
      return BOOK.all();
   }

   public static int count() {
      return BOOK.size();
   }

   public static int generation() {
      return BOOK.generation();
   }

   public static boolean is(class_1297 var0) {
      if (BOOK.isEmpty()) {
         return false;
      } else if (var0 instanceof class_1657 var3) {
         GameProfile var4 = var3.method_7334();
         return BOOK.matches(var4.id(), var4.name());
      } else if (!(var0 instanceof class_11891 var1)) {
         return false;
      } else {
         GameProfile var2 = profile(var1);
         return var2 != null && BOOK.matches(var2.id(), var2.name());
      }
   }

   public static boolean is( UUID var0,  String var1) {
      return BOOK.matches(var0, var1);
   }


   public static PlayerMetadata find( UUID var0,  String var1) {
      return BOOK.find(var0, var1);
   }

   public static Friends.FriendEntry presence(PlayerMetadata var0) {
      Friends.FriendEntry var1 = devPresence.isEmpty() ? null : devPresence.get(PlayerMetadata.key(var0.name));
      return var1 != null ? var1 : presence.getOrDefault(var0, Friends.FriendEntry.AWAY);
   }

   public static int here() {
      int var0 = 0;

      for (PlayerMetadata var2 : BOOK.all()) {
         if (presence(var2).state() != Friends.FriendState.OFFLINE) {
            var0++;
         }
      }

      return var0;
   }

   public static FriendList.FriendIdentity me() {
      return me;
   }

   public static List<GameProfile> strangers(int var0) {
      class_310 var1 = class_310.method_1551();
      class_634 var2 = var1.method_1562();
      if (var2 != null && var1.field_1724 != null) {
         ArrayList var3 = new ArrayList();

         for (class_742 var5 : var1.field_1687.method_18456()) {
            if (var3.size() >= var0) {
               break;
            }

            GameProfile var6 = var5.method_7334();
            if (var5 != var1.field_1724 && real(var6) && !BOOK.matches(var6.id(), var6.name())) {
               var3.add(var6);
            }
         }

         for (class_640 var8 : var2.method_45732()) {
            if (var3.size() >= var0) {
               break;
            }

            GameProfile var9 = var8.method_2966();
            if (!var9.id().equals(var1.field_1724.method_5667())
               && real(var9)
               && !BOOK.matches(var9.id(), var9.name())
               && var3.stream().noneMatch(var1x -> ((GameProfile)var1x).id().equals(var9.id()))) {
               var3.add(var9);
            }
         }

         return var3;
      } else {
         return List.of();
      }
   }

   private static boolean real(GameProfile var0) {
      return NamePolicy.minecraft(var0.name());
   }

   public static Friends.FriendAddResult addPlayer(String var0) {
      String var1 = NamePolicy.clean(var0, false);
      String var2 = NamePolicy.minecraftProblem(var1);
      if (var2 != null) {
         return new Friends.FriendAddResult((PlayerMetadata)null, var2, false);
      } else if (self(var1)) {
         return new Friends.FriendAddResult((PlayerMetadata)null, "That is you", false);
      } else {
         PlayerMetadata var3 = BOOK.named(PlayerMetadata.PlayerKind.PLAYER, var1);
         if (var3 == null) {
            var3 = BOOK.find((UUID)null, var1);
         }

         if (var3 != null) {
            return new Friends.FriendAddResult(var3, (String)null, true);
         } else {
            PlayerMetadata var4 = new PlayerMetadata(FriendList.newId(), PlayerMetadata.PlayerKind.PLAYER, var1, System.currentTimeMillis());
            BOOK.add(var4);
            changed();
            ask(var4);
            return new Friends.FriendAddResult(var4, (String)null, false);
         }
      }
   }

   public static Friends.FriendAddResult addPlayer(GameProfile var0) {
      PlayerMetadata var1 = BOOK.find(var0.id(), var0.name());
      if (var1 != null) {
         return new Friends.FriendAddResult(var1, (String)null, true);
      } else {
         PlayerMetadata var2 = new PlayerMetadata(FriendList.newId(), PlayerMetadata.PlayerKind.PLAYER, var0.name(), System.currentTimeMillis());
         BOOK.add(var2);
         if (var0.id().version() == 4) {
            BOOK.found(var2, var0.name(), var0.id());
         } else {
            ask(var2);
         }

         var2.seen = System.currentTimeMillis();
         var2.place = place(class_310.method_1551());
         changed();
         return new Friends.FriendAddResult(var2, (String)null, false);
      }
   }

   public static Friends.FriendAddResult addPurxiy(String var0, List<PlayerMetadata.AccountRef> var1) {
      PlayerMetadata var2 = BOOK.named(PlayerMetadata.PlayerKind.PURXIY, var0);
      if (var2 != null) {
         BOOK.accounts(var2, var1);
         changed();
         return new Friends.FriendAddResult(var2, (String)null, true);
      } else {
         PlayerMetadata var3 = new PlayerMetadata(FriendList.newId(), PlayerMetadata.PlayerKind.PURXIY, var0, System.currentTimeMillis());
         BOOK.add(var3);
         BOOK.accounts(var3, var1);
         changed();
         return new Friends.FriendAddResult(var3, (String)null, false);
      }
   }

   public static int remove(PlayerMetadata var0) {
      int var1 = BOOK.remove(var0);
      if (var1 >= 0) {
         changed();
      }

      return var1;
   }

   public static void restore(PlayerMetadata var0, int var1) {
      if (BOOK.byId(var0.id) == null) {
         BOOK.insert(var1, var0);
         changed();
      }
   }

   public static boolean self(String var0) {
      return class_310.method_1551().method_1548().method_1676().equalsIgnoreCase(var0);
   }

   static void accounts(PlayerMetadata var0, List<PlayerMetadata.AccountRef> var1) {
      BOOK.accounts(var0, var1);
      changed();
   }

   static void gone(PlayerMetadata var0) {
      if (!var0.gone) {
         var0.gone = true;
         changed();
      }
   }

   static void meChanged() {
      changed();
   }

   private static void changed() {
      dirty = true;
      dirtyAt = System.currentTimeMillis();
   }

   public static void save() {
      if (dirty || seenDirty) {
         dirty = false;
         seenDirty = false;
         seenSavedAt = System.currentTimeMillis();

         try {
            FriendList.save(file(), FriendList.write(BOOK.all(), me));
         } catch (IOException var1) {
            ClientServices.LOGGER.error("Could not save the friends list", var1);
         }
      }
   }

   static String token() throws IOException {
      return FriendList.token(folder().resolve("identity.key"));
   }

   public static String secret() throws IOException {
      return AccountGate.token();
   }

   public static void accountUsername(String var0) {
      if (!var0.equals(me.username)) {
         me.username = var0;
         me.reported = null;
         changed();
      }
   }

   public static boolean middleClick(class_310 var0) {
      if (SettingSection.PROFILE.middleClick.enabled() && var0.field_1724 != null && var0.field_1765 instanceof class_3966 var3) {
         GameProfile var2;
         if (var3.method_17782() instanceof class_1657 var5 && var5 != var0.field_1724) {
            var2 = var5.method_7334();
         } else {
            var2 = null;
         }

         if (var2 != null && NamePolicy.minecraft(var2.name())) {
            PlayerMetadata var6 = BOOK.find(var2.id(), var2.name());
            if (var6 != null) {
               remove(var6);
               HudLayout.push("Friends", "Removed " + TabListProvider.plain(var6.label()), "user-minus", false);
            } else {
               addPlayer(var2);
               HudLayout.push("Friends", "Added " + TabListProvider.plain(var2.name()), "user-plus", true);
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private static void tick(class_310 var0) {
      if (++ticks % 10 == 0) {
         long var1 = System.currentTimeMillis();
         lookups(var1);
         presence = presence(var0, var1);
         FriendApi.tick(var0, var1);
         if (dirty && var1 - dirtyAt > 2000L || seenDirty && var1 - seenSavedAt > 60000L) {
            save();
         }
      }
   }

   private static void lookups(long var0) {
      if (asking < 2) {
         for (PlayerMetadata var3 : BOOK.all()) {
            if (var3.kind == PlayerMetadata.PlayerKind.PLAYER && var3.lookup != PlayerMetadata.LookupState.FOUND && var3.lookup != PlayerMetadata.LookupState.UNKNOWN) {
               Long var4 = failedAt.get(var3.id);
               if ((var4 == null || var0 - var4 >= 60000L) && (var4 != null || var3.lookup != PlayerMetadata.LookupState.FAILED)) {
                  ask(var3);
                  if (asking >= 2) {
                     return;
                  }
               }
            }
         }
      }
   }

   private static void ask(PlayerMetadata var0) {
      String var1 = var0.name;
      asking++;
      failedAt.put(var0.id, Long.MAX_VALUE);
      FriendRequest.ask(var1).whenComplete((var2, var3) -> class_310.method_1551().execute(() -> {
         asking--;
         if (BOOK.byId(var0.id) != null && var0.name.equals(var1)) {
            if (var3 == null && var2 != null && var2.lookup() != PlayerMetadata.LookupState.FAILED) {
               failedAt.remove(var0.id);
               if (var2.lookup() == PlayerMetadata.LookupState.FOUND && var2.uuid() != null && var2.name() != null) {
                  BOOK.found(var0, var2.name(), var2.uuid());
                  PlayerMetadata var4 = BOOK.merge(var0);
                  if (var4 != null) {
                     ClientServices.LOGGER.info("Friends: {} and {} are the same account, kept one", var1, var4.name);
                  }
               } else {
                  var0.lookup = PlayerMetadata.LookupState.UNKNOWN;
               }

               changed();
            } else {
               var0.lookup = PlayerMetadata.LookupState.FAILED;
               failedAt.put(var0.id, System.currentTimeMillis());
            }
         } else {
            failedAt.remove(var0.id);
         }
      }));
   }

   private static Map<PlayerMetadata, Friends.FriendEntry> presence(class_310 var0, long var1) {
      class_634 var3 = var0.method_1562();
      if (!BOOK.isEmpty() && var0.field_1687 != null && var0.field_1724 != null && var3 != null) {
         IdentityHashMap var4 = new IdentityHashMap();
         String var5 = place(var0);
         UUID var6 = var0.field_1724.method_5667();

         for (class_640 var8 : var3.method_2880()) {
            GameProfile var9 = var8.method_2966();
            if (!var9.id().equals(var6)) {
               PlayerMetadata var10 = BOOK.find(var9.id(), var9.name());
               if (var10 != null) {
                  learn(var10, var9);
                  var4.put(var10, new Friends.FriendEntry(Friends.FriendState.ONLINE, 0.0F, var8));
                  seen(var10, var1, var5);
               }
            }
         }

         for (class_742 var13 : var0.field_1687.method_18456()) {
            if (var13 != var0.field_1724) {
               GameProfile var14 = var13.method_7334();
               PlayerMetadata var15 = BOOK.find(var14.id(), var14.name());
               if (var15 != null) {
                  Friends.FriendEntry var11 = (Friends.FriendEntry)var4.get(var15);
                  var4.put(
                     var15,
                     new Friends.FriendEntry(Friends.FriendState.NEARBY, var0.field_1724.method_5739(var13), var11 != null ? var11.info() : var3.method_2871(var14.id()))
                  );
                  seen(var15, var1, var5);
               }
            }
         }

         return var4;
      } else {
         return Map.of();
      }
   }

   private static void learn(PlayerMetadata var0, GameProfile var1) {
      if (var1.id().version() == 4) {
         if (var0.kind == PlayerMetadata.PlayerKind.PLAYER && var0.uuid == null && var0.name.equalsIgnoreCase(var1.name())) {
            BOOK.found(var0, var1.name(), var1.id());
            failedAt.remove(var0.id);
            changed();
         } else if (BOOK.renamed(var0, var1.id(), var1.name())) {
            ClientServices.LOGGER.info("Friends: {} renamed to {}", var0.id, var1.name());
            changed();
         }
      }
   }

   private static void seen(PlayerMetadata var0, long var1, String var3) {
      if (var1 - var0.seen > 5000L || !var3.equals(var0.place)) {
         var0.seen = var1;
         var0.place = var3;
         seenDirty = true;
      }
   }

   static String place(class_310 var0) {
      class_1132 var1 = var0.method_1576();
      class_642 var2 = var0.method_1558();
      if (var1 != null) {
         return var1.method_27728().method_150();
      } else if (var2 == null) {
         return "Minecraft";
      } else {
         boolean var3 = var2.field_3752 != null && !var2.field_3752.isBlank() && !var2.field_3752.equals("Minecraft Server");
         return var3 ? var2.field_3752 : var2.field_3761;
      }
   }


   private static GameProfile profile(class_11891 var0) {
      class_9296 var1 = var0.method_58694(class_9334.field_49617);
      if (var1 == null) {
         return null;
      } else {
         Optional var2 = var1.method_73317();
         return (GameProfile)var2.map(var1x -> new GameProfile(var1.method_73313().id(), (String)var1x)).orElse(null);
      }
   }

   public static void dev(String var0) {
      String[] var1 = var0.split(";");
      String var2 = var1[0];
      switch (var2) {
         case "add":
            ClientServices.LOGGER.info("Friends: add {} -> {}", var1[1], addPlayer(var1[1]));
            break;
         case "remove":
            PlayerMetadata var4 = BOOK.named(PlayerMetadata.PlayerKind.PLAYER, var1[1]);
            if (var4 == null) {
               var4 = BOOK.named(PlayerMetadata.PlayerKind.PURXIY, NamePolicy.clean(var1[1], true));
            }

            if (var4 != null) {
               remove(var4);
            }
            break;
         case "clear":
            BOOK.clear();
            changed();
            break;
         case "list":
            for (PlayerMetadata var10 : BOOK.all()) {
               ClientServices.LOGGER
                  .info(
                     "Friends: {} {} lookup {} accounts {} seen {} at {} -> {}",
                     new Object[]{var10.kind, var10.label(), var10.lookup, var10.accounts, var10.seen, var10.place, presence(var10).state()}
                  );
            }

            ClientServices.LOGGER.info("Friends: {} in all, me {}", BOOK.size(), me.username);
            break;
         case "seen":
            PlayerMetadata var5 = BOOK.named(PlayerMetadata.PlayerKind.PLAYER, var1[1]);
            if (var5 != null) {
               var5.seen = System.currentTimeMillis() - (long)(Double.parseDouble(var1[2]) * 60000.0);
               var5.place = var1.length > 3 ? var1[3] : null;
               changed();
            }
            break;
         case "presence":
            if (var1[1].equals("clear")) {
               devPresence.clear();
            } else {
               Friends.FriendState var9 = Friends.FriendState.valueOf(var1[2].toUpperCase(Locale.ROOT));
               devPresence.put(PlayerMetadata.key(var1[1]), new Friends.FriendEntry(var9, var1.length > 3 ? Float.parseFloat(var1[3]) : 0.0F, (class_640)null));
            }
            break;
         case "save":
            save();
            break;
         case "middle":
            try {
               Method var6 = class_310.class.getDeclaredMethod("pickBlock");
               var6.setAccessible(true);
               var6.invoke(class_310.method_1551());
            } catch (ReflectiveOperationException var7) {
               ClientServices.LOGGER.warn("Friends: no pick to make: {}", var7.toString());
            }
            break;
         default:
            ClientServices.LOGGER.warn("Unknown friends step {}", var0);
      }
   }

   public static enum FriendState {
      NEARBY,
      ONLINE,
      OFFLINE;

      private static Friends.FriendState[] $values() {
         return new Friends.FriendState[]{NEARBY, ONLINE, OFFLINE};
      }
   }

   public record FriendEntry(Friends.FriendState state, float distance,  class_640 info) {
      static final Friends.FriendEntry AWAY = new Friends.FriendEntry(Friends.FriendState.OFFLINE, 0.0F, (class_640)null);
   }

   public record FriendAddResult( PlayerMetadata friend,  String problem, boolean existed) {
   }
}
