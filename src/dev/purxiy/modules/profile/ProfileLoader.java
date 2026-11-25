package dev.purxiy.modules.profile;

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

public final class ProfileLoader {
   static final int G = 1;
   static final int C = 160;
   public String T = "";
   public LocalDate D;
   public final ProfileStats E = new ProfileStats();
   public boolean w;
   public int q;
   public final TreeMap<LocalDate, ProfileStats> c = new TreeMap<>();
   public final Map<String, ProfileLoader.ProfileRecord> b = new LinkedHashMap<>();
   public final List<ProfileLoader.ProfileCacheEntry> a = new ArrayList<>();
   public double H;
   public ProfileTrack n;
   public static boolean F;

   public ProfileStats day(LocalDate var1) {
      return this.c.computeIfAbsent(var1, var0 -> new ProfileStats());
   }

   public ProfileLoader.ProfileRecord place(String var1, String var2) {
      ProfileLoader.ProfileRecord var3 = this.b.computeIfAbsent(var1, var1x -> new ProfileLoader.ProfileRecord(var2));
      var3.i = var2;
      return var3;
   }

   void addSession(ProfileLoader.ProfileCacheEntry var1) {
      this.a.add(var1);

      while (this.a.size() > 160) {
         this.a.removeFirst();
      }
   }

   JsonObject save() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("version", 1);
      var1.addProperty("name", this.T);
      if (this.D != null) {
         var1.addProperty("since", this.D.toString());
      }

      JsonObject var2 = this.E.save();
      var2.addProperty("done", this.w);
      var2.addProperty("worlds", this.q);
      var1.add("baseline", var2);
      JsonObject var3 = new JsonObject();
      this.c.forEach((var1x, var2x) -> {
         if (!var2x.isEmpty()) {
            var3.add(var1x.toString(), var2x.save());
         }
      });
      var1.add("days", var3);
      JsonObject var4 = new JsonObject();
      this.b.forEach((var1x, var2x) -> {
         JsonObject var3x = new JsonObject();
         var3x.addProperty("name", var2x.i);
         var3x.addProperty("play", Math.round(var2x.v));
         var3x.addProperty("last", var2x.d);
         var4.add(var1x, var3x);
      });
      var1.add("places", var4);
      JsonArray var5 = new JsonArray();

      for (ProfileLoader.ProfileCacheEntry var7 : this.a) {
         JsonObject var8 = var7.Q.save();
         var8.addProperty("start", var7.A);
         var8.addProperty("place", var7.O);
         var5.add(var8);
      }

      var1.add("sessions", var5);
      JsonObject var9 = new JsonObject();
      var9.addProperty("top_speed", Math.round(this.H * 100.0) / 100.0);
      var1.add("records", var9);
      if (this.n != null) {
         var1.add("journey", this.n.save());
      }

      return var1;
   }

   static ProfileLoader load(JsonObject var0) {
      long var1 = 66396250864205L;
      long var3 = var1 ^ 136590402961242L;
      long var5 = var1 ^ 72118983716438L;
      ProfileLoader var7 = new ProfileLoader();
      var7.T = t(var0, var5, "name", "");
      var7.D = N(t(var0, var5, "since", ""), var3);
      if (var0.has("baseline") && var0.get("baseline").isJsonObject()) {
         JsonObject var8 = var0.getAsJsonObject("baseline");
         var7.E.add(ProfileStats.load(var8));
         var7.w = var8.has("done") && var8.get("done").getAsBoolean();
         var7.q = (int)ProfileStats.num(var8, "worlds");
      }

      if (var0.has("days") && var0.get("days").isJsonObject()) {
         for (Entry var9 : var0.getAsJsonObject("days").entrySet()) {
            LocalDate var10 = N((String)var9.getKey(), var3);
            if (var10 != null && ((JsonElement)var9.getValue()).isJsonObject()) {
               var7.c.put(var10, ProfileStats.load(((JsonElement)var9.getValue()).getAsJsonObject()));
            }
         }
      }

      if (var0.has("places") && var0.get("places").isJsonObject()) {
         for (Entry var15 : var0.getAsJsonObject("places").entrySet()) {
            if (((JsonElement)var15.getValue()).isJsonObject()) {
               JsonObject var17 = ((JsonElement)var15.getValue()).getAsJsonObject();
               ProfileLoader.ProfileRecord var11 = new ProfileLoader.ProfileRecord(t(var17, var5, "name", (String)var15.getKey()));
               var11.v = ProfileStats.num(var17, "play");
               var11.d = (long)ProfileStats.num(var17, "last");
               var7.b.put((String)var15.getKey(), var11);
            }
         }
      }

      if (var0.has("sessions") && var0.get("sessions").isJsonArray()) {
         for (JsonElement var16 : var0.getAsJsonArray("sessions")) {
            if (var16.isJsonObject()) {
               JsonObject var18 = var16.getAsJsonObject();
               var7.addSession(new ProfileLoader.ProfileCacheEntry((long)ProfileStats.num(var18, "start"), t(var18, var5, "place", ""), ProfileStats.load(var18)));
            }
         }
      }

      if (var0.has("records") && var0.get("records").isJsonObject()) {
         var7.H = ProfileStats.num(var0.getAsJsonObject("records"), "top_speed");
      }

      if (var0.has("journey") && var0.get("journey").isJsonObject()) {
         var7.n = ProfileTrack.load(var0.getAsJsonObject("journey"));
      }

      return var7;
   }

   private static String t(JsonObject var0, long var1, String var3, String var4) {
      try {
         long var5 = 138489451877403L;
         int var7 = var5 < 0L ? -1 : 1;
         return var0.has(var3) ? var0.get(var3).getAsString() : var4;
      } catch (RuntimeException var81) {
         return var4;
      }
   }

   private static LocalDate N(String var0, long var1) {
      try {
         return var0.isEmpty() ? null : LocalDate.parse(var0);
      } catch (DateTimeParseException var4) {
         return null;
      }
   }

   public static void ensureSourceClassInitialized() {
   }

   public static final class ProfileRecord {
      public String i;
      public double v;
      public long d;

      ProfileRecord(String var1) {
         this.i = var1;
      }
   }

   public static final class ProfileCacheEntry {
      public final long A;
      public final String O;
      public final ProfileStats Q;

      public ProfileCacheEntry(long var1, String var3, ProfileStats var4) {
         this.A = var1;
         this.O = var3;
         this.Q = var4;
      }
   }
}
