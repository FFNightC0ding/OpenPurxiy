package dev.purxiy.time;

public final class TimeSupport {
   public static int a = 0;
   public static boolean b;



   private TimeSupport() {
   }

   public static int selectedTime(String var0, int var1) {
      return switch (var0) {
         case "Morning" -> 0;
         case "Day" -> 6000;
         case "Sunset" -> 12000;
         case "Night" -> 18000;
         default -> Math.floorMod(var1, 24000);
      };
   }

   public static long visualTime(long var0, int var2) {
      return var0 - Math.floorMod(var0, 24000L) + Math.floorMod(var2, 24000);
   }

}
