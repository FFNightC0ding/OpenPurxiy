package dev.purxiy.modules.profile;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ProfileTrack {
   static final int J = 6000;
   static final int V = 400;
   public final long X;
   public final String I;
   public final List<ProfileTrack.TrackSeries> l = new ArrayList<>();
   public final List<ProfileTrack.Data> w = new ArrayList<>();
   private int K;

   public ProfileTrack(long var1, String var3) {
      this.X = var1;
      this.I = var3;
   }

   void add(String var1, float var2, float var3, int var4, boolean var5) {
      long var6 = 71907888080459L;
      long var8 = var6 ^ 131009165928069L;
      ProfileTrack.TrackSeries var10 = this.l.isEmpty() ? null : this.l.getLast();
      if (var10 == null || var5 || !var10.d.equals(var1)) {
         if (var10 != null && var10.size() < 2) {
            this.K = this.K - this.l.removeLast().size();
         }

         var10 = new ProfileTrack.TrackSeries(var1);
         this.l.add(var10);
      }

      var10.add(var2, var3, var4);
      this.K++;
      if (this.K > 6000) {
         for (ProfileTrack.TrackSeries var12 : this.l) {
            var12.halve();
         }

         this.K = this.j(var8);
      }

      while (this.l.size() > 400) {
         this.K = this.K - this.l.removeFirst().size();
      }
   }

   void mark(String var1, String var2, float var3, float var4, int var5) {
      if (this.w.size() < 256) {
         this.w.add(new ProfileTrack.Data(var1, var2, var3, var4, var5));
      }
   }

   public int points() {
      return this.K;
   }

   private int j(long var1) {
      int var3 = 0;
      ProfileLoader.ensureSourceClassInitialized();

      for (ProfileTrack.TrackSeries var5 : this.l) {
         long var6 = 59651440509133L;
         int var8 = var6 < 0L ? -1 : 1;
         var3 += var5.size();
      }

      return var3;
   }

   public double length() {
      double var1 = 0.0;

      for (ProfileTrack.TrackSeries var4 : this.l) {
         var1 += var4.length();
      }

      return var1;
   }

   public int duration() {
      int var1 = 0;

      for (ProfileTrack.TrackSeries var3 : this.l) {
         if (var3.size() > 0) {
            var1 = Math.max(var1, var3.c.getInt(var3.size() - 1));
         }
      }

      return var1;
   }

   public String mainDimension() {
      HashMap var1 = new HashMap(4);

      for (ProfileTrack.TrackSeries var3 : this.l) {
         ((double[])var1.computeIfAbsent(var3.d, var0 -> new double[1]))[0] += var3.length() + 0.001 * var3.size();
      }

      String var9 = null;
      double var10 = -1.0;

      for (ProfileTrack.TrackSeries var6 : this.l) {
         double var7 = ((double[])var1.get(var6.d))[0];
         if (var7 > var10) {
            var10 = var7;
            var9 = var6.d;
         }
      }

      return var9;
   }

   JsonObject save() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("start", this.X);
      var1.addProperty("place", this.I);
      JsonArray var2 = new JsonArray();

      for (ProfileTrack.TrackSeries var4 : this.l) {
         if (var4.size() != 0) {
            JsonObject var5 = new JsonObject();
            var5.addProperty("dim", var4.d);
            JsonArray var6 = new JsonArray();

            for (int var7 = 0; var7 < var4.size(); var7++) {
               var6.add(Math.round(var4.D.getFloat(var7) * 10.0F) / 10.0);
               var6.add(Math.round(var4.j.getFloat(var7) * 10.0F) / 10.0);
               var6.add(var4.c.getInt(var7));
            }

            var5.add("p", var6);
            var2.add(var5);
         }
      }

      var1.add("segments", var2);
      JsonArray var8 = new JsonArray();

      for (ProfileTrack.Data var10 : this.w) {
         JsonObject var11 = new JsonObject();
         var11.addProperty("type", var10.type());
         var11.addProperty("dim", var10.dimension());
         var11.addProperty("x", Math.round(var10.x() * 10.0F) / 10.0);
         var11.addProperty("z", Math.round(var10.z() * 10.0F) / 10.0);
         var11.addProperty("t", var10.time());
         var8.add(var11);
      }

      var1.add("marks", var8);
      return var1;
   }

   static ProfileTrack load(JsonObject var0) {
      long var1 = 133307285253519L;
      long var3 = var1 ^ 87405462695233L;

      try {
         ProfileTrack var5 = new ProfileTrack(var0.get("start").getAsLong(), var0.has("place") ? var0.get("place").getAsString() : "");
         if (var0.has("segments")) {
            for (JsonElement var7 : var0.getAsJsonArray("segments")) {
               JsonObject var8 = var7.getAsJsonObject();
               ProfileTrack.TrackSeries var9 = new ProfileTrack.TrackSeries(var8.get("dim").getAsString());
               JsonArray var10 = var8.getAsJsonArray("p");

               for (byte var11 = 0; var11 + 2 < var10.size(); var11 = (byte)(var11 + 3)) {
                  var9.add(var10.get(var11).getAsFloat(), var10.get(var11 + 1).getAsFloat(), var10.get(var11 + 2).getAsInt());
               }

               if (var9.size() > 0) {
                  var5.l.add(var9);
               }
            }

            var5.K = var5.j(var3);
         }

         if (var0.has("marks")) {
            for (JsonElement var14 : var0.getAsJsonArray("marks")) {
               JsonObject var15 = var14.getAsJsonObject();
               var5.w
                  .add(
                     new ProfileTrack.Data(
                        var15.get("type").getAsString(),
                        var15.get("dim").getAsString(),
                        var15.get("x").getAsFloat(),
                        var15.get("z").getAsFloat(),
                        var15.get("t").getAsInt()
                     )
                  );
            }
         }

         return var5;
      } catch (RuntimeException var121) {
         return null;
      }
   }

   public record Data(String g, String h, float v, float F, int y) {
      public static String H;
      public static String O;

      public String type() {
         return this.g;
      }

      public String dimension() {
         return this.h;
      }

      public float x() {
         return this.v;
      }

      public float z() {
         return this.F;
      }

      public int time() {
         return this.y;
      }

      public String stringState34() {
         return this.g;
      }

      public String stringState9() {
         return this.h;
      }

      public float floatState30() {
         return this.v;
      }

      public float floatState36() {
         return this.F;
      }

      public int integerState52() {
         return this.y;
      }
   }

   public static final class TrackSeries {
      public final String d;
      public final FloatArrayList D = new FloatArrayList();
      public final FloatArrayList j = new FloatArrayList();
      public final IntArrayList c = new IntArrayList();
      private double W;

      TrackSeries(String var1) {
         this.d = var1;
      }

      public int size() {
         return this.D.size();
      }

      void add(float var1, float var2, int var3) {
         int var4 = this.D.size();
         if (var4 > 0) {
            double var5 = var1 - this.D.getFloat(var4 - 1);
            double var7 = var2 - this.j.getFloat(var4 - 1);
            this.W = this.W + Math.sqrt(var5 * var5 + var7 * var7);
         }

         this.D.add(var1);
         this.j.add(var2);
         this.c.add(var3);
      }

      void halve() {
         long var1 = 48510943370650L;
         long var3 = var1 ^ 85604412968898L;
         int var5 = this.size();
         int var6 = 0;

         for (int var7 = 0; var7 < var5; var7++) {
            if (var7 % 2 == 0 || var7 == var5 - 1) {
               this.D.set(var6, this.D.getFloat(var7));
               this.j.set(var6, this.j.getFloat(var7));
               this.c.set(var6, this.c.getInt(var7));
               var6++;
            }
         }

         this.D.size(var6);
         this.j.size(var6);
         this.c.size(var6);
         this.W = this.f(var3);
      }

      public double length() {
         return this.W;
      }

      private double f(long var1) {
         ProfileLoader.ensureSourceClassInitialized();
         double var3 = 0.0;

         for (int var5 = 1; var5 < this.size(); var5++) {
            double var6 = this.D.getFloat(var5) - this.D.getFloat(var5 - 1);
            double var8 = this.j.getFloat(var5) - this.j.getFloat(var5 - 1);
            var3 += Math.sqrt(var6 * var6 + var8 * var8);
         }

         return var3;
      }
   }
}
