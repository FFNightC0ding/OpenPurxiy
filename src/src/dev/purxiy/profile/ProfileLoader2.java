// Sourced By DevFFNightmare
package dev.purxiy.profile;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public final class ProfileLoader2 {
   static final int VERSION = 1;
   static final int MAX_SESSIONS = 160;
   public String name = "";
   
   public LocalDate since;
   public final ProfileLoader baseline = new ProfileLoader();
   public boolean imported;
   public int importedWorlds;
   public final TreeMap<LocalDate, ProfileLoader> days = new TreeMap<>();
   public final Map<String, ProfileLoader2.TypeD> places = new LinkedHashMap<>();
   public final List<ProfileLoader2.TypeW> sessions = new ArrayList<>();
   public double topSpeed;
   
   public ProfileCodec2 journey;

   public ProfileLoader day(LocalDate var1) {
      return this.days.computeIfAbsent(var1, var0 -> new ProfileLoader());
   }

   public ProfileLoader2.TypeD place(String var1, String var2) {
      ProfileLoader2.TypeD var3 = this.places.computeIfAbsent(var1, var1x -> new ProfileLoader2.TypeD(var2));
      var3.name = var2;
      return var3;
   }

   void addSession(ProfileLoader2.TypeW var1) {
      this.sessions.add(var1);

      while (this.sessions.size() > 160) {
         this.sessions.removeFirst();
      }
   }

   JsonObject save() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("version", 1);
      var1.addProperty("name", this.name);
      if (this.since != null) {
         var1.addProperty("since", this.since.toString());
      }

      JsonObject var2 = this.baseline.save();
      var2.addProperty("done", this.imported);
      var2.addProperty("worlds", this.importedWorlds);
      var1.add("baseline", var2);
      JsonObject var3 = new JsonObject();
      this.days.forEach((var1x, var2x) -> {
         if (!var2x.isEmpty()) {
            var3.add(var1x.toString(), var2x.save());
         }
      });
      var1.add("days", var3);
      JsonObject var4 = new JsonObject();
      this.places.forEach((var1x, var2x) -> {
         JsonObject var3x = new JsonObject();
         var3x.addProperty("name", var2x.name);
         var3x.addProperty("play", Math.round(var2x.play));
         var3x.addProperty("last", var2x.last);
         var4.add(var1x, var3x);
      });
      var1.add("places", var4);
      JsonArray var5 = new JsonArray();

      for (ProfileLoader2.TypeW var7 : this.sessions) {
         JsonObject var8 = var7.totals.save();
         var8.addProperty("start", var7.start);
         var8.addProperty("place", var7.place);
         var5.add(var8);
      }

      var1.add("sessions", var5);
      JsonObject var9 = new JsonObject();
      var9.addProperty("top_speed", Math.round(this.topSpeed * 100.0) / 100.0);
      var1.add("records", var9);
      if (this.journey != null) {
         var1.add("journey", this.journey.save());
      }

      return var1;
   }

   static ProfileLoader2 load(JsonObject var0) {
      ProfileLoader2 var1 = new ProfileLoader2();
      var1.name = string(var0, "name", "");
      var1.since = date(string(var0, "since", ""));
      if (var0.has("baseline") && var0.get("baseline").isJsonObject()) {
         JsonObject var2 = var0.getAsJsonObject("baseline");
         var1.baseline.add(ProfileLoader.load(var2));
         var1.imported = var2.has("done") && var2.get("done").getAsBoolean();
         var1.importedWorlds = (int)ProfileLoader.num(var2, "worlds");
      }

      if (var0.has("days") && var0.get("days").isJsonObject()) {
         for (Entry var3 : var0.getAsJsonObject("days").entrySet()) {
            LocalDate var4 = date((String)var3.getKey());
            if (var4 != null && ((JsonElement)var3.getValue()).isJsonObject()) {
               var1.days.put(var4, ProfileLoader.load(((JsonElement)var3.getValue()).getAsJsonObject()));
            }
         }
      }

      if (var0.has("places") && var0.get("places").isJsonObject()) {
         for (Entry var9 : var0.getAsJsonObject("places").entrySet()) {
            if (((JsonElement)var9.getValue()).isJsonObject()) {
               JsonObject var11 = ((JsonElement)var9.getValue()).getAsJsonObject();
               ProfileLoader2.TypeD var5 = new ProfileLoader2.TypeD(string(var11, "name", (String)var9.getKey()));
               var5.play = ProfileLoader.num(var11, "play");
               var5.last = (long)ProfileLoader.num(var11, "last");
               var1.places.put((String)var9.getKey(), var5);
            }
         }
      }

      if (var0.has("sessions") && var0.get("sessions").isJsonArray()) {
         for (JsonElement var10 : var0.getAsJsonArray("sessions")) {
            if (var10.isJsonObject()) {
               JsonObject var12 = var10.getAsJsonObject();
               var1.addSession(new ProfileLoader2.TypeW((long)ProfileLoader.num(var12, "start"), string(var12, "place", ""), ProfileLoader.load(var12)));
            }
         }
      }

      if (var0.has("records") && var0.get("records").isJsonObject()) {
         var1.topSpeed = ProfileLoader.num(var0.getAsJsonObject("records"), "top_speed");
      }

      if (var0.has("journey") && var0.get("journey").isJsonObject()) {
         var1.journey = ProfileCodec2.load(var0.getAsJsonObject("journey"));
      }

      return var1;
   }

   private static String string(JsonObject var0, String var1, String var2) {
      try {
         return var0.has(var1) ? var0.get(var1).getAsString() : var2;
      } catch (RuntimeException var4) {
         return var2;
      }
   }

   
   private static LocalDate date(String var0) {
      try {
         return var0.isEmpty() ? null : LocalDate.parse(var0);
      } catch (DateTimeParseException var2) {
         return null;
      }
   }

   public static final class TypeD {
      public String name;
      public double play;
      public long last;

      TypeD(String var1) {
         this.name = var1;
      }
   }

   public static final class TypeW {
      public final long start;
      public final String place;
      public final ProfileLoader totals;

      public TypeW(long var1, String var3, ProfileLoader var4) {
         this.start = var1;
         this.place = var3;
         this.totals = var4;
      }
   }
}
