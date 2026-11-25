package dev.purxiy.modules.profile;

public enum ProfileKind {
   P("Playtime", "clock"),
   V("Distance", "footprints"),
   p("Blocks", "pickaxe"),
   C("Combat", "swords");

   public final String S;
   public final String z;

   private ProfileKind(String nullxx, String nullxxx) {
      this.S = nullxx;
      this.z = nullxxx;
   }

   public double of(ProfileStats var1) {
      return switch (this) {
         case P -> var1.A;
         case V -> var1.distance();
         case p -> var1.blocks();
         case C -> var1.allKills();
      };
   }

   public String format(double var1) {
      return switch (this) {
         case P -> ProfileSupport.duration(var1);
         case V -> ProfileSupport.distance(var1);
         case p, C -> ProfileSupport.count(Math.round(var1));
      };
   }

   public String axis(double var1) {
      return switch (this) {
         case P -> ProfileSupport.durationShort(var1);
         case V -> ProfileSupport.distanceShort(var1);
         case p, C -> ProfileSupport.countShort(Math.round(var1));
      };
   }

   public double step(double var1, int var3) {
      double var4 = Math.max(var1, this.minimum()) / Math.max(1, var3);
      if (this == P) {
         double[] var13 = new double[]{60.0, 300.0, 600.0, 900.0, 1800.0, 3600.0, 7200.0, 10800.0, 14400.0, 21600.0, 28800.0, 43200.0};

         for (double var16 : var13) {
            if (var16 >= var4) {
               return var16;
            }
         }

         return Math.ceil(var4 / 43200.0) * 43200.0;
      } else {
         double var6 = Math.pow(10.0, Math.floor(Math.log10(var4)));

         for (double var11 : new double[]{1.0, 2.0, 2.5, 5.0, 10.0}) {
            if (var11 * var6 >= var4) {
               return Math.max(this != p && this != C ? 0.0 : 1.0, var11 * var6);
            }
         }

         return 10.0 * var6;
      }
   }

   public double minimum() {
      return switch (this) {
         case P -> 1800.0;
         case V -> 500.0;
         case p -> 50.0;
         case C -> 5.0;
      };
   }
}
