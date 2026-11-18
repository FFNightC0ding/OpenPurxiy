// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import net.minecraft.class_310;

public final class FriendMetadata {
   private static Set<UUID> friendIds = Set.of();
   private static Set<String> friendNames = Set.of();
   private static UnaryOperator<String> names;
   private static long friendsGeneration = 0L;
   private static long namesGeneration = 0L;
   private static boolean friendsOwned;

   private FriendMetadata() {
   }

   public static boolean friend(UUID var0, String var1) {
      owner();
      return var0 != null && friendIds.contains(var0) || var1 != null && friendNames.contains(var1.toLowerCase(Locale.ROOT));
   }

   public static String shown(String var0) {
      owner();
      UnaryOperator var1 = names;
      return var1 == null ? var0 : (String)Objects.requireNonNull(var1.apply(var0));
   }

   public static AutoCloseable friendSnapshot(Set<UUID> var0, Set<String> var1) {
      owner();
      if (friendsOwned) {
         throw new IllegalStateException("Friend metadata already owned");
      } else if (var0.size() <= 2048 && var1.size() <= 2048 && !var1.stream().anyMatch(var0x -> var0x == null || var0x.length() > 64)) {
         friendIds = Set.copyOf(var0);
         friendNames = var1.stream().map(var0x -> var0x.toLowerCase(Locale.ROOT)).collect(Collectors.toUnmodifiableSet());
         friendsOwned = true;
         long var2 = ++friendsGeneration;
         return () -> {
            owner();
            if (var2 == friendsGeneration) {
               friendIds = Set.of();
               friendNames = Set.of();
               friendsOwned = false;
               friendsGeneration++;
            }
         };
      } else {
         throw new IllegalArgumentException("Friend metadata limits");
      }
   }

   public static AutoCloseable namePolicy(UnaryOperator<String> var0) {
      owner();
      if (names != null) {
         throw new IllegalStateException("Name policy already owned");
      } else {
         names = Objects.requireNonNull(var0);
         long var1 = ++namesGeneration;
         return () -> {
            owner();
            if (var1 == namesGeneration) {
               names = null;
               namesGeneration++;
            }
         };
      }
   }

   private static void owner() {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Player metadata requires owner thread");
      }
   }
}
