// Sourced By DevFFNightmare
package dev.purxiy.profile;

public enum ProfileKind {
   PLAYTIME("Playtime", "clock"),
   DISTANCE("Distance", "footprints"),
   BLOCKS("Blocks", "pickaxe"),
   COMBAT("Combat", "swords");

   public final String label;
   public final String icon;

   private ProfileKind(String nullxx, String nullxxx) {
      this.label = nullxx;
      this.icon = nullxxx;
   }

   public double of(ProfileLoader var1) {
      return switch (this) {
         case PLAYTIME -> var1.play;
         case DISTANCE -> var1.distance();
         case BLOCKS -> var1.blocks();
         case COMBAT -> var1.allKills();
      };
   }

   public String format(double var1) {
      return switch (this) {
         case PLAYTIME -> ProfileSupport2.duration(var1);
         case DISTANCE -> ProfileSupport2.distance(var1);
         case BLOCKS, COMBAT -> ProfileSupport2.count(Math.round(var1));
      };
   }

   public String axis(double var1) {
      return switch (this) {
         case PLAYTIME -> ProfileSupport2.durationShort(var1);
         case DISTANCE -> ProfileSupport2.distanceShort(var1);
         case BLOCKS, COMBAT -> ProfileSupport2.countShort(Math.round(var1));
      };
   }

   public double step(double var1, int var3) {
      double var4 = Math.max(var1, this.minimum()) / Math.max(1, var3);
      if (this == PLAYTIME) {
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
               return Math.max(this != BLOCKS && this != COMBAT ? 0.0 : 1.0, var11 * var6);
            }
         }

         return 10.0 * var6;
      }
   }

   public double minimum() {
      return switch (this) {
         case PLAYTIME -> 1800.0;
         case DISTANCE -> 500.0;
         case BLOCKS -> 50.0;
         case COMBAT -> 5.0;
      };
   }

   private static ProfileKind[] $values() {
      return new ProfileKind[]{PLAYTIME, DISTANCE, BLOCKS, COMBAT};
   }
}
