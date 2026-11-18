// Sourced By DevFFNightmare
package dev.purxiy.social;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class FriendStore {
   private final List<PlayerMetadata> friends = new ArrayList<>();
   private volatile Map<UUID, PlayerMetadata> byId = Map.of();
   private volatile Map<String, PlayerMetadata> byName = Map.of();
   private int generation;

   public List<PlayerMetadata> all() {
      return Collections.unmodifiableList(this.friends);
   }

   public int size() {
      return this.friends.size();
   }

   public boolean isEmpty() {
      return this.friends.isEmpty();
   }

   public int generation() {
      return this.generation;
   }

   
   public PlayerMetadata find( UUID var1,  String var2) {
      if (var1 != null) {
         PlayerMetadata var3 = this.byId.get(var1);
         if (var3 != null) {
            return var3;
         }
      }

      if (var2 != null && !var2.isEmpty()) {
         PlayerMetadata var5 = this.byName.get(PlayerMetadata.key(var2));
         if (var5 == null) {
            return null;
         } else {
            UUID var4 = var5.accountOf(var2);
            return var4 != null && var1 != null && var1.version() == 4 && !var4.equals(var1) ? null : var5;
         }
      } else {
         return null;
      }
   }

   public boolean matches( UUID var1,  String var2) {
      return this.find(var1, var2) != null;
   }

   
   public PlayerMetadata named(PlayerMetadata.TypeI var1, String var2) {
      for (PlayerMetadata var4 : this.friends) {
         if (var4.kind == var1 && var4.name.equalsIgnoreCase(var2)) {
            return var4;
         }
      }

      return null;
   }

   
   public PlayerMetadata byId(String var1) {
      for (PlayerMetadata var3 : this.friends) {
         if (var3.id.equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   public void add(PlayerMetadata var1) {
      this.insert(0, var1);
   }

   public void insert(int var1, PlayerMetadata var2) {
      if (!this.friends.contains(var2)) {
         this.friends.add(Math.max(0, Math.min(var1, this.friends.size())), var2);
         this.changed();
      }
   }

   public int remove(PlayerMetadata var1) {
      int var2 = this.friends.indexOf(var1);
      if (var2 >= 0) {
         this.friends.remove(var2);
         this.changed();
      }

      return var2;
   }

   public void clear() {
      this.friends.clear();
      this.changed();
   }

   public void changed() {
      HashMap var1 = new HashMap();
      HashMap var2 = new HashMap();

      for (int var3 = this.friends.size() - 1; var3 >= 0; var3--) {
         PlayerMetadata var4 = this.friends.get(var3);
         if (var4.kind == PlayerMetadata.TypeI.PLAYER) {
            var2.put(PlayerMetadata.key(var4.name), var4);
            if (var4.uuid != null) {
               var1.put(var4.uuid, var4);
            }
         } else {
            for (PlayerMetadata.TypeS var6 : var4.accounts) {
               var2.put(PlayerMetadata.key(var6.name()), var4);
               var1.put(var6.uuid(), var4);
            }
         }
      }

      this.byId = Map.copyOf(var1);
      this.byName = Map.copyOf(var2);
      this.generation++;
   }

   public void found(PlayerMetadata var1, String var2, UUID var3) {
      var1.name = var2;
      var1.uuid = var3;
      var1.lookup = PlayerMetadata.TypeE.FOUND;
      this.changed();
   }

   public boolean renamed(PlayerMetadata var1, UUID var2, String var3) {
      if (var1.kind == PlayerMetadata.TypeI.PLAYER) {
         if (var2.equals(var1.uuid) && !var1.name.equals(var3)) {
            var1.name = var3;
            this.changed();
            return true;
         } else {
            return false;
         }
      } else {
         for (int var4 = 0; var4 < var1.accounts.size(); var4++) {
            PlayerMetadata.TypeS var5 = var1.accounts.get(var4);
            if (var5.uuid().equals(var2) && !var5.name().equals(var3)) {
               var1.accounts.set(var4, new PlayerMetadata.TypeS(var2, var3));
               this.changed();
               return true;
            }
         }

         return false;
      }
   }

   public void accounts(PlayerMetadata var1, List<PlayerMetadata.TypeS> var2) {
      var1.accounts.clear();
      var1.accounts.addAll(var2);
      var1.gone = false;
      this.changed();
   }

   
   public PlayerMetadata merge(PlayerMetadata var1) {
      if (var1.kind == PlayerMetadata.TypeI.PLAYER && var1.uuid != null) {
         for (PlayerMetadata var3 : this.friends) {
            if (var3 != var1 && var3.kind == PlayerMetadata.TypeI.PLAYER && var1.uuid.equals(var3.uuid)) {
               PlayerMetadata var4 = var3.added <= var1.added ? var3 : var1;
               PlayerMetadata var5 = var4 == var3 ? var1 : var3;
               var4.name = var1.name;
               var4.lookup = PlayerMetadata.TypeE.FOUND;
               var4.seen = Math.max(var4.seen, var5.seen);
               if (var4.place == null) {
                  var4.place = var5.place;
               }

               this.remove(var5);
               return var5;
            }
         }

         return null;
      } else {
         return null;
      }
   }
}
