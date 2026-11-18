// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.module;

import java.util.ArrayDeque;
import net.minecraft.class_1268;
import net.minecraft.class_1792;
import net.minecraft.class_310;
import net.minecraft.class_638;
import net.minecraft.class_746;

public final class ModuleCache {
   private static long sequence = 0L;
   private static long active = 0L;
   private static final ArrayDeque<ModuleCache.TypeH> releases = new ArrayDeque<>();

   private ModuleCache() {
   }

   private static void owner() {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Item actions require the owner thread");
      }
   }

   public static ModuleCache.TypeD acquire() {
      owner();
      if (active != 0L) {
         return null;
      } else {
         active = ++sequence;
         return new ModuleCache.TypeD(active);
      }
   }

   public static boolean busy() {
      owner();
      return active != 0L;
   }

   public static int pending() {
      owner();
      return releases.size();
   }

   public static void releaseNextTick(class_746 var0, class_638 var1, class_1268 var2, class_1792 var3) {
      owner();
      if (releases.size() >= 8) {
         throw new IllegalStateException("Pending input cleanup limit");
      } else {
         releases.addLast(new ModuleCache.TypeH(var0, var1, var2, var3));
      }
   }

   public static void drain(class_310 var0) {
      owner();

      while (!releases.isEmpty()) {
         ModuleCache.TypeH var1 = releases.removeFirst();
         if (var0.field_1724 == var1.player
            && var0.field_1687 == var1.level
            && var0.field_1761 != null
            && var1.player.method_6115()
            && var1.player.method_6058() == var1.hand
            && var1.player.method_6030().method_31574(var1.item)) {
            var0.field_1761.method_2897(var1.player);
         }
      }
   }

   public static void disconnected() {
      owner();
      active = 0L;
      releases.clear();
   }

   public static final class TypeD implements AutoCloseable {
      private final long identity;

      private TypeD(long var1) {
         this.identity = var1;
      }

      public boolean current() {
         ModuleCache.owner();
         return ModuleCache.active == this.identity;
      }

      @Override
      public void close() {
         ModuleCache.owner();
         if (ModuleCache.active == this.identity) {
            ModuleCache.active = 0L;
         }
      }
   }

   private record TypeH(class_746 player, class_638 level, class_1268 hand, class_1792 item) {
   }
}
