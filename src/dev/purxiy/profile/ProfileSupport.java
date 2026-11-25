package dev.purxiy.profile;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.purxiy.loader.fabric.client.ProfileProvider;
import java.time.LocalDate;
import net.minecraft.class_310;

public final class ProfileSupport {
   private static long next = 0L;
   private static long revision = -1L;
   private static ProfileDay cached = new ProfileDay();
   private static ProfileDay.DayCache live;

   private ProfileSupport() {
   }

   public static boolean available() {
      return ProfileProvider.available();
   }

   public static ProfileDay data() {
      long var0 = System.nanoTime();
      long var2 = ProfileProvider.revision();
      if (var2 != revision || var0 - next >= 0L) {
         JsonObject var4 = JsonParser.parseString(ProfileProvider.snapshot()).getAsJsonObject();
         cached = ProfileDay.load(var4);
         JsonObject var5 = var4.getAsJsonObject("live");
         live = var5 == null ? null : new ProfileDay.DayCache(var5.get("start").getAsLong(), var5.get("place").getAsString(), ProfileLoader.load(var5));
         next = var0 + 250000000L;
         revision = var2;
      }

      return cached;
   }

   public static ProfileDay.DayCache live() {
      data();
      return live;
   }

   public static ProfileTrackCodec journey() {
      return data().journey;
   }

   public static boolean journeyLive() {
      return class_310.method_1551().field_1687 != null && journey() != null;
   }

   public static LocalDate today() {
      return LocalDate.now();
   }

   public static boolean demo() {
      return false;
   }

   public static void setDemo(boolean var0) {
      if (var0) {
         throw new IllegalArgumentException("Sample profiles are unavailable in release builds");
      }
   }
}
