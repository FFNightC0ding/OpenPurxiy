package dev.purxiy.modules.profile;

import com.google.gson.JsonObject;

public final class ProfileLoader3 {
   public double A;
   public double j;
   public double B;
   public double u;
   public double J;
   public double y;
   public double d;
   public int E;
   public int t;
   public int n;
   public int k;
   public int Y;
   public int g;
   public int L;
   public static int q = 0;

   public double distance() {
      return this.j + this.B + this.u + this.J + this.y + this.d;
   }

   public int blocks() {
      return this.E + this.t;
   }

   public int allKills() {
      return this.k + this.Y;
   }

   public boolean isEmpty() {
      return this.A <= 0.0 && this.distance() <= 0.0 && this.blocks() == 0 && this.n == 0 && this.allKills() == 0 && this.g == 0 && this.L == 0;
   }

   public void add(ProfileLoader3 var1) {
      this.A = this.A + var1.A;
      this.j = this.j + var1.j;
      this.B = this.B + var1.B;
      this.u = this.u + var1.u;
      this.J = this.J + var1.J;
      this.y = this.y + var1.y;
      this.d = this.d + var1.d;
      this.E = this.E + var1.E;
      this.t = this.t + var1.t;
      this.n = this.n + var1.n;
      this.k = this.k + var1.k;
      this.Y = this.Y + var1.Y;
      this.g = this.g + var1.g;
      this.L = this.L + var1.L;
   }

   public ProfileLoader3 copy() {
      ProfileLoader3 var1 = new ProfileLoader3();
      var1.add(this);
      return var1;
   }

   JsonObject save() {
      long var1 = 33316742583536L;
      long var3 = var1 ^ 46528592295209L;
      long var5 = var1 ^ 98948911714018L;
      int var7 = (int)((var1 ^ 98948911714018L) >>> 32);
      int var8 = (int)((var1 ^ 98948911714018L) << 32 >>> 48);
      int var9 = (int)(var5 << 48 >>> 48);
      JsonObject var10 = new JsonObject();
      W(var10, "play", var3, this.A);
      W(var10, "foot", var3, this.j);
      W(var10, "sprint", var3, this.B);
      W(var10, "swim", var3, this.u);
      W(var10, "glide", var3, this.J);
      W(var10, "ride", var3, this.y);
      W(var10, "fly", var3, this.d);
      y(var10, var7, (short)var8, (char)var9, "broken", this.E);
      y(var10, var7, (short)var8, (char)var9, "placed", this.t);
      y(var10, var7, (short)var8, (char)var9, "hits", this.n);
      y(var10, var7, (short)var8, (char)var9, "kills", this.k);
      y(var10, var7, (short)var8, (char)var9, "mob_kills", this.Y);
      y(var10, var7, (short)var8, (char)var9, "deaths", this.g);
      y(var10, var7, (short)var8, (char)var9, "chat", this.L);
      return var10;
   }

   static ProfileLoader3 load(JsonObject var0) {
      ProfileLoader3 var1 = new ProfileLoader3();
      var1.A = num(var0, "play");
      var1.j = num(var0, "foot");
      var1.B = num(var0, "sprint");
      var1.u = num(var0, "swim");
      var1.J = num(var0, "glide");
      var1.y = num(var0, "ride");
      var1.d = num(var0, "fly");
      var1.E = (int)num(var0, "broken");
      var1.t = (int)num(var0, "placed");
      var1.n = (int)num(var0, "hits");
      var1.k = (int)num(var0, "kills");
      var1.Y = (int)num(var0, "mob_kills");
      var1.g = (int)num(var0, "deaths");
      var1.L = (int)num(var0, "chat");
      return var1;
   }

   private static void W(JsonObject var0, String var1, long var2, double var4) {
      if (var4 > 0.0) {
         var0.addProperty(var1, Math.round(var4 * 10.0) / 10.0);
      }
   }

   private static void y(JsonObject var0, int var1, short var2, char var3, String var4, int var5) {
      if (var5 > 0) {
         var0.addProperty(var4, var5);
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
