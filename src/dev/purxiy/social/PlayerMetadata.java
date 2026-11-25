package dev.purxiy.social;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public final class PlayerMetadata {
   public final String id;
   public final PlayerMetadata.PlayerKind kind;
   public final long added;
   String name;

   UUID uuid;
   final List<PlayerMetadata.AccountRef> accounts = new ArrayList<>();
   PlayerMetadata.LookupState lookup = PlayerMetadata.LookupState.PENDING;
   boolean gone;
   long seen;

   String place;

   PlayerMetadata(String var1, PlayerMetadata.PlayerKind var2, String var3, long var4) {
      this.id = var1;
      this.kind = var2;
      this.name = var3;
      this.added = var4;
   }

   public String name() {
      return this.name;
   }

   public String label() {
      return this.kind == PlayerMetadata.PlayerKind.PURXIY ? "@" + this.name : this.name;
   }


   public UUID uuid() {
      return this.uuid;
   }

   public List<PlayerMetadata.AccountRef> accounts() {
      return List.copyOf(this.accounts);
   }

   public PlayerMetadata.LookupState lookup() {
      return this.lookup;
   }

   public boolean gone() {
      return this.gone;
   }

   public long seen() {
      return this.seen;
   }


   public String place() {
      return this.place;
   }


   UUID accountOf(String var1) {
      if (this.kind == PlayerMetadata.PlayerKind.PLAYER) {
         return this.name.equalsIgnoreCase(var1) ? this.uuid : null;
      } else {
         for (PlayerMetadata.AccountRef var3 : this.accounts) {
            if (var3.name().equalsIgnoreCase(var1)) {
               return var3.uuid();
            }
         }

         return null;
      }
   }


   public String minecraftName() {
      if (this.kind == PlayerMetadata.PlayerKind.PLAYER) {
         return this.name;
      } else {
         return this.accounts.isEmpty() ? null : this.accounts.getFirst().name();
      }
   }


   public UUID minecraftId() {
      if (this.kind == PlayerMetadata.PlayerKind.PLAYER) {
         return this.uuid;
      } else {
         return this.accounts.isEmpty() ? null : this.accounts.getFirst().uuid();
      }
   }

   static String key(String var0) {
      return var0.toLowerCase(Locale.ROOT);
   }

   @Override
   public String toString() {
      String var1 = this.label();
      return var1 + (this.uuid != null ? " (" + this.uuid + ")" : "");
   }

   public static enum LookupState {
      PENDING,
      FOUND,
      UNKNOWN,
      FAILED;

      private static PlayerMetadata.LookupState[] $values() {
         return new PlayerMetadata.LookupState[]{PENDING, FOUND, UNKNOWN, FAILED};
      }
   }

   public static enum PlayerKind {
      PLAYER,
      PURXIY;

      private static PlayerMetadata.PlayerKind[] $values() {
         return new PlayerMetadata.PlayerKind[]{PLAYER, PURXIY};
      }
   }

   public record AccountRef(UUID uuid, String name) {
   }
}
