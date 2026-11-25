package dev.purxiy.social;

import java.util.regex.Pattern;

public final class NamePolicy {
   private static final Pattern MINECRAFT = Pattern.compile("[.*]?[A-Za-z0-9_]{2,16}");
   private static final Pattern USERNAME = Pattern.compile("[A-Za-z0-9_]{3,16}");

   private NamePolicy() {
   }

   public static String clean(String var0, boolean var1) {
      String var2 = var0.strip();
      if (var1 && var2.startsWith("@")) {
         var2 = var2.substring(1).strip();
      }

      return var2;
   }

   public static boolean minecraft(String var0) {
      return var0.length() <= 16 && MINECRAFT.matcher(var0).matches();
   }

   public static boolean username(String var0) {
      return USERNAME.matcher(var0).matches();
   }


   public static String minecraftProblem(String var0) {
      if (var0.isEmpty()) {
         return "Type a Minecraft name first";
      } else if (var0.length() > 16) {
         return "Minecraft names are at most 16 characters";
      } else {
         return minecraft(var0) ? null : "Minecraft names are letters, digits and _ only";
      }
   }


   public static String usernameProblem(String var0) {
      if (var0.isEmpty()) {
         return "Type a username first";
      } else if (var0.length() >= 3 && var0.length() <= 16) {
         return username(var0) ? null : "Usernames are letters, digits and _ only";
      } else {
         return "Usernames are 3 to 16 characters";
      }
   }
}
