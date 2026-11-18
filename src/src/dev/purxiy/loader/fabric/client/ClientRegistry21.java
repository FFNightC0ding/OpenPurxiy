// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_350;
import net.minecraft.class_437;
import net.minecraft.class_8021;

public final class ClientRegistry21 {
   private static final ClientSupport3.TypeX<ClientRegistry21.Listener> listeners = ClientSupport3.TypeX.create();

   public static AutoCloseable install(ClientRegistry21.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean skins(class_437 var0) {
      return listeners.invoke(var1 -> var1.skins(var0), false);
   }

   public static boolean active() {
      return listeners.invoke(var0 -> var0.active(), false);
   }

   public static void begin(class_437 var0, class_332 var1, int var2, int var3) {
      listeners.invoke(var4 -> {
         var4.begin(var0, var1, var2, var3);
         return null;
      }, null);
   }

   public static void end(class_437 var0) {
      listeners.invoke(var1 -> {
         var1.end(var0);
         return null;
      }, null);
   }

   public static void widgetBegin(class_339 var0, class_332 var1) {
      listeners.invoke(var2 -> {
         var2.widgetBegin(var0, var1);
         return null;
      }, null);
   }

   public static void widgetEnd(class_339 var0, class_332 var1) {
      listeners.invoke(var2 -> {
         var2.widgetEnd(var0, var1);
         return null;
      }, null);
   }

   public static void scissor(class_332 var0, int var1, int var2, int var3, int var4) {
      listeners.invoke(var5 -> {
         var5.scissor(var0, var1, var2, var3, var4);
         return null;
      }, null);
   }

   public static void unscissor() {
      listeners.invoke(var0 -> {
         var0.unscissor();
         return null;
      }, null);
   }

   public static boolean sprite(class_2960 var0, int var1, int var2, int var3, int var4, int var5) {
      return listeners.invoke(var6 -> var6.sprite(var0, var1, var2, var3, var4, var5), false);
   }

   public static boolean texture(class_2960 var0, int var1, int var2, int var3, int var4) {
      return listeners.invoke(var5 -> var5.texture(var0, var1, var2, var3, var4), false);
   }

   public static boolean fill(class_332 var0, int var1, int var2, int var3, int var4, int var5) {
      return listeners.invoke(var6 -> var6.fill(var0, var1, var2, var3, var4, var5), false);
   }

   public static boolean label(class_339 var0, class_2561 var1, int var2) {
      return listeners.invoke(var3 -> var3.label(var0, var1, var2), false);
   }

   public static boolean link(class_339 var0, class_2561 var1) {
      return listeners.invoke(var2 -> var2.link(var0, var1), false);
   }

   public static boolean title(class_339 var0, class_2561 var1) {
      return listeners.invoke(var2 -> var2.title(var0, var1), false);
   }

   public static boolean sliderLabel(class_339 var0, class_2561 var1) {
      return listeners.invoke(var2 -> var2.sliderLabel(var0, var1), false);
   }

   public static boolean tabLabel(class_339 var0, class_2561 var1, boolean var2) {
      return listeners.invoke(var3 -> var3.tabLabel(var0, var1, var2), false);
   }

   public static void listRow(class_350<?> var0, class_8021 var1, boolean var2, boolean var3) {
      listeners.invoke(var4 -> {
         var4.listRow(var0, var1, var2, var3);
         return null;
      }, null);
   }

   public static boolean hidesSelection(class_350<?> var0) {
      return listeners.invoke(var1 -> var1.hidesSelection(var0), false);
   }

   public static boolean click() {
      return listeners.invoke(var0 -> var0.click(), false);
   }

   public interface Listener {
      boolean skins(class_437 var1);

      boolean active();

      void begin(class_437 var1, class_332 var2, int var3, int var4);

      void end(class_437 var1);

      void widgetBegin(class_339 var1, class_332 var2);

      void widgetEnd(class_339 var1, class_332 var2);

      void scissor(class_332 var1, int var2, int var3, int var4, int var5);

      void unscissor();

      boolean sprite(class_2960 var1, int var2, int var3, int var4, int var5, int var6);

      boolean texture(class_2960 var1, int var2, int var3, int var4, int var5);

      boolean fill(class_332 var1, int var2, int var3, int var4, int var5, int var6);

      boolean label(class_339 var1, class_2561 var2, int var3);

      boolean link(class_339 var1, class_2561 var2);

      boolean title(class_339 var1, class_2561 var2);

      boolean sliderLabel(class_339 var1, class_2561 var2);

      boolean tabLabel(class_339 var1, class_2561 var2, boolean var3);

      void listRow(class_350<?> var1, class_8021 var2, boolean var3, boolean var4);

      boolean hidesSelection(class_350<?> var1);

      boolean click();
   }
}
