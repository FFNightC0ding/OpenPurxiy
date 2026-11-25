package dev.purxiy.loader.fabric;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

final class NameValidation {
   private static final Pattern TYPE = Pattern.compile("[A-Za-z0-9_.$/]{1,240}");
   private static final Pattern METHOD = Pattern.compile("[A-Za-z0-9_$<>]{1,120}");

   private NameValidation() {
   }

   static List<NameValidation.FrameTrace> capture(Throwable var0) {
      ArrayList var1 = new ArrayList();

      for (int var2 = 0; var0 != null && var2 < 3; var2++) {
         ArrayList var3 = new ArrayList();

         for (StackTraceElement var7 : var0.getStackTrace()) {
            if (var3.size() == 16) {
               break;
            }

            String var8 = safe(TYPE, var7.getClassName());
            var3.add(var8 + "." + safe(METHOD, var7.getMethodName()) + ":" + var7.getLineNumber());
         }

         var1.add(new NameValidation.FrameTrace(safe(TYPE, var0.getClass().getName()), List.copyOf(var3)));
         Throwable var9 = var0.getCause();
         if (var9 == var0) {
            break;
         }

         var0 = var9;
      }

      return List.copyOf(var1);
   }

   private static String safe(Pattern var0, String var1) {
      return var1 != null && var0.matcher(var1).matches() ? var1 : "unknown";
   }

   record FrameTrace(String type, List<String> frames) {
   }
}
