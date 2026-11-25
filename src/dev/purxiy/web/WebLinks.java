package dev.purxiy.web;

import dev.purxiy.loader.fabric.AccountGate;

public final class WebLinks {
   private WebLinks() {
   }

   public static boolean allows() {
      return AccountGate.authorized();
   }
}
