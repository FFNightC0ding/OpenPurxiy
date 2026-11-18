// Sourced By DevFFNightmare
package dev.purxiy.profile;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import dev.purxiy.core.ImageFiles;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.social.Friends;
import dev.purxiy.ui.config.ConfigLibrarySupport3;
import dev.purxiy.web.ProfileWebsite;
import dev.purxiy.web.WebLinks;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.class_310;

public final class ProfileUpdater {
   private static final long EVERY = 180000L;
   private static final long BACK_OFF = 60000L;
   private static long nextAt = 0L;
   private static long lastUpload = 0L;
   private static boolean busy;
   private static boolean wanted;
   private static boolean inWorld;
   private static int ticks = 0;

   private ProfileUpdater() {
   }

   public static void init() {
      ClientTickEvents.END_CLIENT_TICK.register(ProfileUpdater::tick);
      ClientLifecycleEvents.CLIENT_STOPPING.register(ProfileUpdater::stopping);
   }

   static void soon() {
      nextAt = Math.min(nextAt, System.currentTimeMillis() + 5000L);
   }

   private static void tick(class_310 var0) {
      if (++ticks % 20 == 0) {
         long var1 = System.currentTimeMillis();
         boolean var3 = SettingSection.PROFILE.sync.enabled();
         if (wanted != var3) {
            wanted = var3;
            nextAt = 0L;
         }

         if (inWorld && var0.field_1687 == null && var1 - lastUpload >= 15000L) {
            soon();
         }

         inWorld = var0.field_1687 != null;
         if (!busy) {
            if (!var3) {
               if (ProfileWebsite.profileShown() && var1 >= nextAt) {
                  forget(var0, var1);
               }
            } else if (ProfileWebsite.linked() && WebLinks.allows() && ProfileSupport.available() && !ProfileSupport.demo() && var1 >= nextAt) {
               send(var0, var1);
            }
         }
      }
   }

   private static void send(class_310 var0, long var1) {
      String var3 = token();
      if (var3 != null) {
         busy = true;
         nextAt = var1 + 180000L;
         lastUpload = var1;
         upload(var3).whenComplete((var1x, var2) -> var0.execute(() -> {
            busy = false;
            if (var2 == null) {
               ProfileWebsite.profileShown(true);
            } else {
               if (wanted) {
                  nextAt = System.currentTimeMillis() + 60000L;
               }

               ClientServices.LOGGER.info("Profile: not shown on the website yet ({})", String.valueOf(ConfigLibrarySupport3.refusal(var2)));
            }
         }));
      }
   }

   private static CompletableFuture<JsonObject> upload(String var0) {
      JsonObject var1 = ProfileSupport.data().save();
      var1.remove("journey");
      var1.remove("live");
      CompletableFuture var2 = SettingSection.PROFILE.photoPortrait() ? ImageFiles.websitePortrait() : CompletableFuture.completedFuture("");
      return var2.<JsonObject, JsonObject>thenCombine(CompletableFuture.supplyAsync(ProfileWebsite::machine), (var1x, var2x) -> {
         if (var1x != null) {
            if (((String)var1x).isEmpty()) {
               var1.add("portrait", JsonNull.INSTANCE);
            } else {
               var1.addProperty("portrait", (String)var1x);
            }
         }

         ((JsonObject)var2x).add("profile", var1);
         return (JsonObject)var2x;
      }).thenCompose(var1x -> ConfigLibrarySupport3.call("PUT", "/api/client/profile", (JsonObject)(var1x), var0));
   }

   private static void stopping(class_310 var0) {
      if (!busy
         && SettingSection.PROFILE.sync.enabled()
         && ProfileWebsite.linked()
         && WebLinks.allows()
         && ProfileSupport.available()
         && !ProfileSupport.demo()
         && System.currentTimeMillis() - lastUpload >= 15000L) {
         String var1 = token();
         if (var1 != null) {
            try {
               upload(var1).get(2L, TimeUnit.SECONDS);
               ProfileWebsite.profileShown(true);
            } catch (InterruptedException var3) {
               Thread.currentThread().interrupt();
            } catch (TimeoutException | ExecutionException var4) {
               ClientServices.LOGGER.debug("Profile: final update deferred until next start");
            }
         }
      }
   }

   private static void forget(class_310 var0, long var1) {
      String var3 = token();
      if (var3 != null) {
         busy = true;
         nextAt = var1 + 60000L;
         ConfigLibrarySupport3.call("DELETE", "/api/client/profile", (JsonObject)null, var3).whenComplete((var1x, var2) -> var0.execute(() -> {
            busy = false;
            if (var2 == null) {
               ProfileWebsite.profileShown(false);
               nextAt = 0L;
            }
         }));
      }
   }

   private static String token() {
      try {
         return Friends.secret();
      } catch (IOException var1) {
         return null;
      }
   }
}
