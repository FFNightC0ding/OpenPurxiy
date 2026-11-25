package dev.purxiy.web;

import com.google.gson.JsonObject;
import dev.purxiy.loader.fabric.AccountGate;
import dev.purxiy.loader.fabric.ClientServices;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_156;

public final class ProfileWebsite {
   private static boolean profileShown;
   private static boolean profileLoaded;

   private ProfileWebsite() {
   }

   public static boolean linked() {
      return AccountGate.authorized();
   }

   public static ProfileWebsite.ShortCode link() {
      return null;
   }

   public static void reopenLink() {
      openSite("/dashboard/account");
   }

   public static void startLink() {
      openSite("/dashboard/account");
   }

   public static void cancelLink() {
   }

   private static Path profileFile() {
      return FabricLoader.getInstance().getConfigDir().resolve("purxiy/loader/profile-website.json");
   }

   public static synchronized boolean profileShown() {
      if (!profileLoaded) {
         profileLoaded = true;
         Path var0 = profileFile();
         if (Files.exists(var0)) {
            profileShown = true;

            try (InputStream var1 = Files.newInputStream(var0)) {
               byte[] var2 = var1.readNBytes(17);
               String var3 = new String(var2, StandardCharsets.UTF_8).trim();
               if (var2.length > 16 || !var3.equals("true") && !var3.equals("false")) {
                  throw new IOException("Invalid profile sync marker");
               }

               profileShown = Boolean.parseBoolean(var3);
            } catch (IOException var6) {
               ClientServices.LOGGER.warn("Could not read website profile marker; pending opt-out will be retried", var6);
            }
         }
      }

      return profileShown;
   }

   public static synchronized void profileShown(boolean var0) {
      if (profileShown() != var0) {
         profileShown = var0;
         Path var1 = profileFile();
         Path var2 = null;

         try {
            Files.createDirectories(var1.getParent());
            var2 = Files.createTempFile(var1.getParent(), "profile-website-", ".tmp");
            Files.writeString(var2, Boolean.toString(var0) + "\n", StandardCharsets.UTF_8);

            try {
               Files.move(var2, var1, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
            } catch (AtomicMoveNotSupportedException var13) {
               Files.move(var2, var1, StandardCopyOption.REPLACE_EXISTING);
            }
         } catch (IOException var14) {
            ClientServices.LOGGER.warn("Could not save website profile marker", var14);
         } finally {
            if (var2 != null) {
               try {
                  Files.deleteIfExists(var2);
               } catch (IOException var12) {
               }
            }
         }
      }
   }

   public static JsonObject machine() {
      return AccountGate.machine();
   }

   public static void openSite(String var0) {
      if (var0.startsWith("/") && !var0.startsWith("//")) {
         class_156.method_668().method_673(AccountGate.origin().resolve(var0));
      } else {
         throw new IllegalArgumentException("Website path");
      }
   }

   public record ShortCode(String code, long expiresAt) {
   }
}
