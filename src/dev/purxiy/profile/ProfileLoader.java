// Sourced By DevFFNightmare
package dev.purxiy.profile;

import com.google.gson.JsonObject;

public final class ProfileLoader {
   public double play;
   public double foot;
   public double sprint;
   public double swim;
   public double glide;
   public double ride;
   public double fly;
   public int broken;
   public int placed;
   public int hits;
   public int kills;
   public int mobKills;
   public int deaths;
   public int chat;

   public double distance() {
      return this.foot + this.sprint + this.swim + this.glide + this.ride + this.fly;
   }

   public int blocks() {
      return this.broken + this.placed;
   }

   public int allKills() {
      return this.kills + this.mobKills;
   }

   public boolean isEmpty() {
      return this.play <= 0.0 && this.distance() <= 0.0 && this.blocks() == 0 && this.hits == 0 && this.allKills() == 0 && this.deaths == 0 && this.chat == 0;
   }

   public void add(ProfileLoader var1) {
      this.play = this.play + var1.play;
      this.foot = this.foot + var1.foot;
      this.sprint = this.sprint + var1.sprint;
      this.swim = this.swim + var1.swim;
      this.glide = this.glide + var1.glide;
      this.ride = this.ride + var1.ride;
      this.fly = this.fly + var1.fly;
      this.broken = this.broken + var1.broken;
      this.placed = this.placed + var1.placed;
      this.hits = this.hits + var1.hits;
      this.kills = this.kills + var1.kills;
      this.mobKills = this.mobKills + var1.mobKills;
      this.deaths = this.deaths + var1.deaths;
      this.chat = this.chat + var1.chat;
   }

   public ProfileLoader copy() {
      ProfileLoader var1 = new ProfileLoader();
      var1.add(this);
      return var1;
   }

   JsonObject save() {
      JsonObject var1 = new JsonObject();
      put(var1, "play", this.play);
      put(var1, "foot", this.foot);
      put(var1, "sprint", this.sprint);
      put(var1, "swim", this.swim);
      put(var1, "glide", this.glide);
      put(var1, "ride", this.ride);
      put(var1, "fly", this.fly);
      put(var1, "broken", this.broken);
      put(var1, "placed", this.placed);
      put(var1, "hits", this.hits);
      put(var1, "kills", this.kills);
      put(var1, "mob_kills", this.mobKills);
      put(var1, "deaths", this.deaths);
      put(var1, "chat", this.chat);
      return var1;
   }

   static ProfileLoader load(JsonObject var0) {
      ProfileLoader var1 = new ProfileLoader();
      var1.play = num(var0, "play");
      var1.foot = num(var0, "foot");
      var1.sprint = num(var0, "sprint");
      var1.swim = num(var0, "swim");
      var1.glide = num(var0, "glide");
      var1.ride = num(var0, "ride");
      var1.fly = num(var0, "fly");
      var1.broken = (int)num(var0, "broken");
      var1.placed = (int)num(var0, "placed");
      var1.hits = (int)num(var0, "hits");
      var1.kills = (int)num(var0, "kills");
      var1.mobKills = (int)num(var0, "mob_kills");
      var1.deaths = (int)num(var0, "deaths");
      var1.chat = (int)num(var0, "chat");
      return var1;
   }

   private static void put(JsonObject var0, String var1, double var2) {
      if (var2 > 0.0) {
         var0.addProperty(var1, Math.round(var2 * 10.0) / 10.0);
      }
   }

   private static void put(JsonObject var0, String var1, int var2) {
      if (var2 > 0) {
         var0.addProperty(var1, var2);
      }
   }

   static double num(JsonObject var0, String var1) {
      try {
         return var0.has(var1) ? Math.max(0.0, var0.get(var1).getAsDouble()) : 0.0;
      } catch (RuntimeException var3) {
         return 0.0;
      }
   }
}
