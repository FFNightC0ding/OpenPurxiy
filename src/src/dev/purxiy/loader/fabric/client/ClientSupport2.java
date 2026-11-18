// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import java.util.Locale;
import net.minecraft.class_2558;
import net.minecraft.class_2561;

public final class ClientSupport2 {
   private static final ClientSupport3.TypeX<ClientSupport2.Listener2> commands = ClientSupport3.TypeX.create();
   private static final ClientSupport3.TypeX<ClientSupport2.Listener3> stats = ClientSupport3.TypeX.create();
   private static final ClientSupport3.TypeX<ClientSupport2.Listener2> chat = ClientSupport3.TypeX.create();
   private static final ClientSupport3.TypeX<ClientSupport2.Listener> clicks = ClientSupport3.TypeX.create();

   private ClientSupport2() {
   }

   public static AutoCloseable command(ClientSupport2.Listener2 var0) {
      return commands.subscribe(0, var0);
   }

   public static AutoCloseable chat(ClientSupport2.Listener2 var0) {
      return chat.subscribe(0, var0);
   }

   public static AutoCloseable click(ClientSupport2.Listener var0) {
      return clicks.subscribe(0, var0);
   }

   public static boolean chat(String var0) {
      boolean[] var1 = new boolean[]{false};
      return chat.invoke(var2 -> var1[0] || (var1[0] = var2.intercept(var0)), false);
   }

   public static boolean click(class_2558 var0) {
      boolean[] var1 = new boolean[]{false};
      return clicks.invoke(var2 -> var1[0] || (var1[0] = var2.intercept(var0)), false);
   }

   public static AutoCloseable stats(ClientSupport2.Listener3 var0) {
      if (stats.subscribers() != 0) {
         throw new IllegalStateException("Local stats already owned");
      } else {
         return stats.subscribe(0, var0);
      }
   }

   public static boolean intercept(String var0) {
      if (var0 != null && var0.length() <= 32767) {
         boolean[] var1 = new boolean[]{false};
         return commands.invoke(var2 -> var1[0] || (var1[0] = var2.intercept(var0)), false);
      } else {
         return false;
      }
   }

   public static String name(String var0) {
      String var1 = var0.strip();
      if (var1.startsWith("/")) {
         var1 = var1.substring(1);
      }

      int var2 = var1.indexOf(32);
      return (var2 < 0 ? var1 : var1.substring(0, var2)).toLowerCase(Locale.ROOT);
   }

   public static boolean paysFromBalance() {
      return stats.invoke(ClientSupport2.Listener3::paysFromBalance, false);
   }

   public static double balance() {
      return stats.invoke(ClientSupport2.Listener3::balance, 0.0);
   }

   public static void pay(double var0) {
      if (Double.isFinite(var0) && !(var0 <= 0.0)) {
         stats.invoke(var2 -> {
            var2.pay(var0);
            return null;
         }, null);
      } else {
         throw new IllegalArgumentException("Invalid local payment");
      }
   }

   public static void beginSidebar() {
      stats.invoke(var0 -> {
         var0.beginSidebar();
         return null;
      }, null);
   }

   public static class_2561 lineForWidth(class_2561 var0) {
      return stats.invoke(var1 -> var1.lineForWidth(var0), var0);
   }

   public static class_2561 lineForDraw(class_2561 var0) {
      return stats.invoke(var1 -> var1.lineForDraw(var0), var0);
   }

   @FunctionalInterface
   public interface Listener {
      boolean intercept(class_2558 var1);
   }

   @FunctionalInterface
   public interface Listener2 {
      boolean intercept(String var1);
   }

   public interface Listener3 {
      boolean paysFromBalance();

      double balance();

      void pay(double var1);

      void beginSidebar();

      class_2561 lineForWidth(class_2561 var1);

      class_2561 lineForDraw(class_2561 var1);
   }
}
