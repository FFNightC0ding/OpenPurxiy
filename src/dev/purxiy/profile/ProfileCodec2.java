// Sourced By DevFFNightmare
package dev.purxiy.profile;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ProfileCodec2 {
   static final int MAX_POINTS = 6000;
   static final int MAX_SEGMENTS = 400;
   public final long start;
   public final String place;
   public final List<ProfileCodec2.TypeY> segments = new ArrayList<>();
   public final List<ProfileCodec2.TypeB> marks = new ArrayList<>();
   private int points;

   public ProfileCodec2(long var1, String var3) {
      this.start = var1;
      this.place = var3;
   }

   void add(String var1, float var2, float var3, int var4, boolean var5) {
      ProfileCodec2.TypeY var6 = this.segments.isEmpty() ? null : this.segments.getLast();
      if (var6 == null || var5 || !var6.dimension.equals(var1)) {
         if (var6 != null && var6.size() < 2) {
            this.points = this.points - this.segments.removeLast().size();
         }

         var6 = new ProfileCodec2.TypeY(var1);
         this.segments.add(var6);
      }

      var6.add(var2, var3, var4);
      this.points++;
      if (this.points > 6000) {
         for (ProfileCodec2.TypeY var8 : this.segments) {
            var8.halve();
         }

         this.points = this.count();
      }

      while (this.segments.size() > 400) {
         this.points = this.points - this.segments.removeFirst().size();
      }
   }

   void mark(String var1, String var2, float var3, float var4, int var5) {
      if (this.marks.size() < 256) {
         this.marks.add(new ProfileCodec2.TypeB(var1, var2, var3, var4, var5));
      }
   }

   public int points() {
      return this.points;
   }

   private int count() {
      int var1 = 0;

      for (ProfileCodec2.TypeY var3 : this.segments) {
         var1 += var3.size();
      }

      return var1;
   }

   public double length() {
      double var1 = 0.0;

      for (ProfileCodec2.TypeY var4 : this.segments) {
         var1 += var4.length();
      }

      return var1;
   }

   public int duration() {
      int var1 = 0;

      for (ProfileCodec2.TypeY var3 : this.segments) {
         if (var3.size() > 0) {
            var1 = Math.max(var1, var3.times.getInt(var3.size() - 1));
         }
      }

      return var1;
   }

   
   public String mainDimension() {
      HashMap var1 = new HashMap(4);

      for (ProfileCodec2.TypeY var3 : this.segments) {
         double[] var4 = (double[])(var1.computeIfAbsent(var3.dimension, var0 -> new double[1]));
         var4[0] += var3.length() + 0.001 * var3.size();
      }

      String var9 = null;
      double var10 = -1.0;

      for (ProfileCodec2.TypeY var6 : this.segments) {
         double var7 = ((double[])var1.get(var6.dimension))[0];
         if (var7 > var10) {
            var10 = var7;
            var9 = var6.dimension;
         }
      }

      return var9;
   }

   JsonObject save() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("start", this.start);
      var1.addProperty("place", this.place);
      JsonArray var2 = new JsonArray();

      for (ProfileCodec2.TypeY var4 : this.segments) {
         if (var4.size() != 0) {
            JsonObject var5 = new JsonObject();
            var5.addProperty("dim", var4.dimension);
            JsonArray var6 = new JsonArray();

            for (int var7 = 0; var7 < var4.size(); var7++) {
               var6.add(Math.round(var4.xs.getFloat(var7) * 10.0F) / 10.0);
               var6.add(Math.round(var4.zs.getFloat(var7) * 10.0F) / 10.0);
               var6.add(var4.times.getInt(var7));
            }

            var5.add("p", var6);
            var2.add(var5);
         }
      }

      var1.add("segments", var2);
      JsonArray var8 = new JsonArray();

      for (ProfileCodec2.TypeB var10 : this.marks) {
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

   
   static ProfileCodec2 load(JsonObject var0) {
      try {
         ProfileCodec2 var1 = new ProfileCodec2(var0.get("start").getAsLong(), var0.has("place") ? var0.get("place").getAsString() : "");
         if (var0.has("segments")) {
            for (JsonElement var3 : var0.getAsJsonArray("segments")) {
               JsonObject var4 = var3.getAsJsonObject();
               ProfileCodec2.TypeY var5 = new ProfileCodec2.TypeY(var4.get("dim").getAsString());
               JsonArray var6 = var4.getAsJsonArray("p");

               for (byte var7 = 0; var7 + 2 < var6.size(); var7 += 3) {
                  var5.add(var6.get(var7).getAsFloat(), var6.get(var7 + 1).getAsFloat(), var6.get(var7 + 2).getAsInt());
               }

               if (var5.size() > 0) {
                  var1.segments.add(var5);
               }
            }

            var1.points = var1.count();
         }

         if (var0.has("marks")) {
            for (JsonElement var10 : var0.getAsJsonArray("marks")) {
               JsonObject var11 = var10.getAsJsonObject();
               var1.marks
                  .add(
                     new ProfileCodec2.TypeB(
                        var11.get("type").getAsString(),
                        var11.get("dim").getAsString(),
                        var11.get("x").getAsFloat(),
                        var11.get("z").getAsFloat(),
                        var11.get("t").getAsInt()
                     )
                  );
            }
         }

         return var1;
      } catch (RuntimeException var8) {
         return null;
      }
   }

   public record TypeB(String type, String dimension, float x, float z, int time) {
      public static final String DEATH = "death";
      public static final String KILL = "kill";
   }

   public static final class TypeY {
      public final String dimension;
      public final FloatArrayList xs = new FloatArrayList();
      public final FloatArrayList zs = new FloatArrayList();
      public final IntArrayList times = new IntArrayList();
      private double length;

      TypeY(String var1) {
         this.dimension = var1;
      }

      public int size() {
         return this.xs.size();
      }

      void add(float var1, float var2, int var3) {
         int var4 = this.xs.size();
         if (var4 > 0) {
            double var5 = var1 - this.xs.getFloat(var4 - 1);
            double var7 = var2 - this.zs.getFloat(var4 - 1);
            this.length = this.length + Math.sqrt(var5 * var5 + var7 * var7);
         }

         this.xs.add(var1);
         this.zs.add(var2);
         this.times.add(var3);
      }

      void halve() {
         int var1 = this.size();
         int var2 = 0;

         for (int var3 = 0; var3 < var1; var3++) {
            if (var3 % 2 == 0 || var3 == var1 - 1) {
               this.xs.set(var2, this.xs.getFloat(var3));
               this.zs.set(var2, this.zs.getFloat(var3));
               this.times.set(var2, this.times.getInt(var3));
               var2++;
            }
         }

         this.xs.size(var2);
         this.zs.size(var2);
         this.times.size(var2);
         this.length = this.measure();
      }

      public double length() {
         return this.length;
      }

      private double measure() {
         double var1 = 0.0;

         for (int var3 = 1; var3 < this.size(); var3++) {
            double var4 = this.xs.getFloat(var3) - this.xs.getFloat(var3 - 1);
            double var6 = this.zs.getFloat(var3) - this.zs.getFloat(var3 - 1);
            var1 += Math.sqrt(var4 * var4 + var6 * var6);
         }

         return var1;
      }
   }
}
