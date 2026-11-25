package dev.purxiy.loader.fabric;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.purxiy.loader.fabric.client.ChatCommandRouter;
import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import net.minecraft.class_2558;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_2558.class_11506;

public final class LocalCommands {
   private static final ArrayDeque<LocalCommands.PendingNotice> pending = new ArrayDeque<>();

   private LocalCommands() {
   }

   private static void check() {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Local command owner thread");
      }
   }

   private static JsonObject roles() {
      for (JsonElement var1 : LoaderMessages.catalog) {
         JsonObject var2 = var1.getAsJsonObject();
         if (var2.get("id").getAsString().equals("roles")) {
            return var2;
         }
      }

      return null;
   }

   public static boolean chat(String var0) {
      check();
      String var1 = var0.strip();
      if (var1.regionMatches(true, 0, ".fr", 0, 3) && (var1.length() == 3 || var1.length() > 3 && var1.charAt(3) == ' ')) {
         if (var0.length() > 1024) {
            return true;
         } else if (ModuleState.moduleActive("roles")) {
            ChatCommandRouter.chat(var0);
            return true;
         } else {
            queue(new LocalCommands.PendingNotice(var0, (class_2558)null, System.nanoTime() + TimeUnit.SECONDS.toNanos(60L)));
            return true;
         }
      } else {
         return ChatCommandRouter.chat(var0);
      }
   }

   public static boolean click(class_2558 var0) {
      check();
      if (!(var0 instanceof class_11506 var1 && var1.comp_4378().equals(class_2960.method_60655("purxiy", "roles")))) {
         return ChatCommandRouter.click(var0);
      } else if (var1.comp_4379().flatMap(var0x -> var0x.method_68658()).orElse("").length() > 1024) {
         return true;
      } else if (ModuleState.moduleActive("roles")) {
         ChatCommandRouter.click(var0);
         return true;
      } else {
         queue(new LocalCommands.PendingNotice((String)null, var0, System.nanoTime() + TimeUnit.SECONDS.toNanos(60L)));
         return true;
      }
   }

   private static void queue(LocalCommands.PendingNotice var0) {
      JsonObject var1 = roles();
      if (var1 != null && LoaderMessages.authorized && LoaderMessages.menuAllowed()) {
         if (pending.size() >= 16) {
            LoaderMessages.status = "Wacht tot de module is geladen.";
         } else {
            pending.addLast(var0);
            if (!LoaderMessages.requested("roles")) {
               LoaderMessages.toggle(var1);
            }
         }
      } else {
         LoaderMessages.status = "Deze lokale opdracht is nog niet beschikbaar.";
      }
   }

   static void tick() {
      check();
      if (LoaderMessages.authorized && LoaderMessages.menuAllowed()) {
         while (!pending.isEmpty()) {
            LocalCommands.PendingNotice var0 = pending.peekFirst();
            if (System.nanoTime() <= var0.deadline && LoaderMessages.requested("roles")) {
               if (!ModuleState.moduleActive("roles")) {
                  return;
               }

               pending.removeFirst();
               if (var0.chat != null) {
                  ChatCommandRouter.chat(var0.chat);
               } else {
                  ChatCommandRouter.click(var0.click);
               }
            } else {
               pending.removeFirst();
            }
         }
      } else {
         pending.clear();
      }
   }

   private record PendingNotice(String chat, class_2558 click, long deadline) {
   }
}
