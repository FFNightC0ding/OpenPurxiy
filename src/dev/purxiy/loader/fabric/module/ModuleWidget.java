package dev.purxiy.loader.fabric.module;

import dev.purxiy.loader.api.HudLayer;
import dev.purxiy.loader.fabric.ModuleState;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import net.minecraft.class_10185;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_636;
import net.minecraft.class_6880;
import net.minecraft.class_746;

public final class ModuleWidget {
   private static int dispatchDepth = 0;
   private static long sequence = 0L;
   private static final List<ModuleWidget.TaskQueue<?>> channels = new ArrayList<>();
   private static final ModuleWidget.TaskQueue<ModuleWidget.Listener> keys = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<ModuleWidget.KeyHandler> keyInterceptors = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<ModuleWidget.MouseButtonHandler> buttonInterceptors = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<ModuleWidget.MousePressHandler> buttons = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<ModuleWidget.DamageHandler> damage = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<ModuleWidget.HudExtractHandler> extraction = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<Consumer<class_310>> ticks = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<Consumer<class_310>> clientTickStart = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<Consumer<class_310>> inputPhase = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<Predicate<class_746>> sustain = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<ModuleWidget.ScrollHandler> scroll = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<DoubleUnaryOperator> fov = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<DoubleUnaryOperator> mouse = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<BooleanSupplier> hand = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<Consumer<class_310>> attacks = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<Consumer<class_310>> disconnect = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<IntUnaryOperator> repeat = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<UnaryOperator<class_10185>> input = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<ModuleWidget.SprintHandler> sprint = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<Consumer<class_746>> playerTick = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<ModuleWidget.InteractHandler> clicks = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<Predicate<class_310>> attackClicks = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<Consumer<class_310>> startAttacks = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<ModuleWidget.AttackHandler> actualAttacks = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<ModuleWidget.SoundHandler> sounds = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<Runnable> afterWorld = new ModuleWidget.TaskQueue<>();
   private static final ModuleWidget.TaskQueue<ModuleWidget.NamedTask> claims = new ModuleWidget.TaskQueue<>();

   private ModuleWidget() {
   }

   private static void owner() {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Game hooks require owner thread");
      }
   }

   public static int subscribers() {
      owner();
      return channels.stream().mapToInt(var0 -> var0.listeners.size()).sum();
   }

   public static AutoCloseable onKey(ModuleWidget.Listener var0) {
      return keys.add(0, var0);
   }

   public static AutoCloseable interceptKey(int var0, ModuleWidget.KeyHandler var1) {
      return keyInterceptors.add(var0, var1);
   }

   public static AutoCloseable interceptMouse(int var0, ModuleWidget.MouseButtonHandler var1) {
      return buttonInterceptors.add(var0, var1);
   }

   public static AutoCloseable onMouseButton(ModuleWidget.MousePressHandler var0) {
      return buttons.add(0, var0);
   }

   public static AutoCloseable onDamage(ModuleWidget.DamageHandler var0) {
      return damage.add(0, var0);
   }

   public static AutoCloseable onHudExtraction(ModuleWidget.HudExtractHandler var0) {
      return extraction.add(0, var0);
   }

   public static AutoCloseable onTick(Consumer<class_310> var0) {
      return ticks.add(0, var0);
   }

   public static AutoCloseable onClientTickStart(int var0, Consumer<class_310> var1) {
      return clientTickStart.add(var0, var1);
   }

   public static AutoCloseable onInputPhase(int var0, Consumer<class_310> var1) {
      return inputPhase.add(var0, var1);
   }

   public static AutoCloseable onSustainUse(Predicate<class_746> var0) {
      return sustain.add(0, var0);
   }

   public static AutoCloseable onScroll(int var0, ModuleWidget.ScrollHandler var1) {
      return scroll.add(var0, var1);
   }

   public static AutoCloseable onFov(int var0, DoubleUnaryOperator var1) {
      return fov.add(var0, var1);
   }

   public static AutoCloseable onMouseScale(int var0, DoubleUnaryOperator var1) {
      return mouse.add(var0, var1);
   }

   public static AutoCloseable onHideHand(BooleanSupplier var0) {
      return hand.add(0, var0);
   }

   public static AutoCloseable beforeAttack(Consumer<class_310> var0) {
      return attacks.add(0, var0);
   }

   public static AutoCloseable onDisconnect(Consumer<class_310> var0) {
      return disconnect.add(0, var0);
   }

   public static AutoCloseable repeatUse(IntUnaryOperator var0) {
      return repeat.add(0, var0);
   }

   public static AutoCloseable onInput(int var0, UnaryOperator<class_10185> var1) {
      return input.add(var0, var1);
   }

   public static AutoCloseable onSprint(ModuleWidget.SprintHandler var0) {
      return sprint.add(0, var0);
   }

   public static AutoCloseable beforePlayerTick(Consumer<class_746> var0) {
      return playerTick.add(0, var0);
   }

   public static AutoCloseable onContainerClick(ModuleWidget.InteractHandler var0) {
      return clicks.add(0, var0);
   }

   public static AutoCloseable onAttackClick(int var0, Predicate<class_310> var1) {
      return attackClicks.add(var0, var1);
   }

   public static AutoCloseable onStartAttack(Consumer<class_310> var0) {
      return startAttacks.add(0, var0);
   }

   public static AutoCloseable onActualAttack(ModuleWidget.AttackHandler var0) {
      return actualAttacks.add(0, var0);
   }

   public static AutoCloseable onSound(ModuleWidget.SoundHandler var0) {
      return sounds.add(0, var0);
   }

   public static AutoCloseable onAfterWorld(Runnable var0) {
      return afterWorld.add(0, var0);
   }

   public static AutoCloseable claim(String var0, BooleanSupplier var1) {
      if (var0 != null && var0.matches("[a-z][a-z0-9_-]{0,63}")) {
         return claims.add(0, new ModuleWidget.NamedTask(var0, Objects.requireNonNull(var1)));
      } else {
         throw new IllegalArgumentException("Invalid input claim");
      }
   }

   public static boolean claimed(String var0) {
      owner();

      for (ModuleWidget.TaskSlot var2 : claims.listeners) {
         if (((ModuleWidget.NamedTask)var2.callback()).name().equals(var0) && ((ModuleWidget.NamedTask)var2.callback()).active().getAsBoolean()) {
            return true;
         }
      }

      return false;
   }

   private static <T> T dispatch(Supplier<T> var0, T var1) {
      owner();
      dispatchDepth++;

      Object var2;
      try {
         return (T)var0.get();
      } catch (AssertionError | Exception | LinkageError var8) {
         if (dispatchDepth > 1) {
            throw var8;
         }

         dispatchDepth--;
         ModuleState.callbackFailed(var8);
         dispatchDepth++;
         var2 = var1;
      } finally {
         dispatchDepth--;
      }

      return (T)var2;
   }

   public static void key(int var0, int var1, int var2) {
      dispatch(() -> {
         for (ModuleWidget.TaskSlot var4 : keys.listeners) {
            ((ModuleWidget.Listener)var4.callback()).key(var0, var1, var2);
         }

         return null;
      }, null);
   }

   public static boolean interceptKey(int var0, int var1, int var2) {
      return dispatch(() -> {
         for (ModuleWidget.TaskSlot var4 : keyInterceptors.listeners) {
            if (((ModuleWidget.KeyHandler)var4.callback()).key(var0, var1, var2)) {
               return true;
            }
         }

         return false;
      }, false);
   }

   public static boolean interceptMouse(int var0, int var1) {
      return dispatch(() -> {
         for (ModuleWidget.TaskSlot var3 : buttonInterceptors.listeners) {
            if (((ModuleWidget.MouseButtonHandler)var3.callback()).button(var0, var1)) {
               return true;
            }
         }

         return false;
      }, false);
   }

   public static void mouseButton(int var0, int var1) {
      dispatch(() -> {
         for (ModuleWidget.TaskSlot var3 : buttons.listeners) {
            ((ModuleWidget.MousePressHandler)var3.callback()).button(var0, var1);
         }

         return null;
      }, null);
   }

   public static void damage(class_1297 var0, int var1) {
      dispatch(() -> {
         for (ModuleWidget.TaskSlot var3 : damage.listeners) {
            ((ModuleWidget.DamageHandler)var3.callback()).damage(var0, var1);
         }

         return null;
      }, null);
   }

   public static boolean extractHud(HudLayer var0) {
      return dispatch(() -> {
         boolean var1 = false;

         for (ModuleWidget.TaskSlot var3 : extraction.listeners) {
            var1 |= ((ModuleWidget.HudExtractHandler)var3.callback()).extracted(var0);
         }

         return var1;
      }, false);
   }

   public static void tick(class_310 var0) {
      dispatch(() -> {
         for (ModuleWidget.TaskSlot var2 : ticks.listeners) {
            ((Consumer)var2.callback()).accept(var0);
         }

         return null;
      }, null);
   }

   public static void clientTickStart(class_310 var0) {
      dispatch(() -> {
         for (ModuleWidget.TaskSlot var2 : clientTickStart.listeners) {
            ((Consumer)var2.callback()).accept(var0);
         }

         return null;
      }, null);
   }

   public static void inputPhase(class_310 var0) {
      dispatch(() -> {
         for (ModuleWidget.TaskSlot var2 : inputPhase.listeners) {
            ((Consumer)var2.callback()).accept(var0);
         }

         return null;
      }, null);
   }

   public static boolean sustainUse(class_746 var0) {
      return dispatch(() -> {
         for (ModuleWidget.TaskSlot var2 : sustain.listeners) {
            if (((Predicate)var2.callback()).test(var0)) {
               return true;
            }
         }

         return false;
      }, false);
   }

   public static boolean scroll(double var0, double var2) {
      return Double.isFinite(var0) && Double.isFinite(var2) ? dispatch(() -> {
         for (ModuleWidget.TaskSlot var5 : scroll.listeners) {
            if (((ModuleWidget.ScrollHandler)var5.callback()).scroll(var0, var2)) {
               return true;
            }
         }

         return false;
      }, false) : false;
   }

   public static double fov(double var0) {
      return dispatch(() -> {
         double var2 = var0;

         for (ModuleWidget.TaskSlot var5 : fov.listeners) {
            var2 = ((DoubleUnaryOperator)var5.callback()).applyAsDouble(var2);
         }

         if (Double.isFinite(var2) && !(var2 < 0.0) && (var2 != 0.0 || var0 == 0.0) && !(var2 >= 180.0)) {
            return var2;
         } else {
            throw new IllegalStateException("Invalid FOV");
         }
      }, var0);
   }

   public static double mouse(double var0) {
      return dispatch(() -> {
         double var2 = var0;

         for (ModuleWidget.TaskSlot var5 : mouse.listeners) {
            var2 = ((DoubleUnaryOperator)var5.callback()).applyAsDouble(var2);
         }

         if (!Double.isFinite(var2)) {
            throw new IllegalStateException("Invalid mouse output");
         } else {
            return var2;
         }
      }, var0);
   }

   public static boolean hideHand(boolean var0) {
      return dispatch(() -> {
         if (var0) {
            return true;
         } else {
            for (ModuleWidget.TaskSlot var2 : hand.listeners) {
               if (((BooleanSupplier)var2.callback()).getAsBoolean()) {
                  return true;
               }
            }

            return false;
         }
      }, var0);
   }

   public static void beforeAttack(class_310 var0) {
      dispatch(() -> {
         for (ModuleWidget.TaskSlot var2 : attacks.listeners) {
            ((Consumer)var2.callback()).accept(var0);
         }

         return null;
      }, null);
   }

   public static void disconnected(class_310 var0) {
      dispatch(() -> {
         for (ModuleWidget.TaskSlot var2 : disconnect.listeners) {
            ((Consumer)var2.callback()).accept(var0);
         }

         return null;
      }, null);
      ModuleCache.disconnected();
      ModuleSupport.disconnected();
   }

   public static int repeatUse(int var0) {
      return dispatch(() -> {
         int var1 = var0;

         for (ModuleWidget.TaskSlot var3 : repeat.listeners) {
            int var4 = ((IntUnaryOperator)var3.callback()).applyAsInt(var1);
            if (var4 < 1 || var4 > var1) {
               throw new IllegalStateException("Invalid use interval");
            }

            var1 = var4;
         }

         return var1;
      }, var0);
   }

   public static class_10185 input(class_10185 var0) {
      return dispatch(() -> {
         class_10185 var1 = var0;

         for (ModuleWidget.TaskSlot var3 : input.listeners) {
            var1 = (class_10185)Objects.requireNonNull(((UnaryOperator)var3.callback()).apply(var1));
         }

         return var1;
      }, var0);
   }

   public static boolean sprint(class_1309 var0, boolean var1) {
      return !class_310.method_1551().method_18854() ? var1 : dispatch(() -> {
         boolean var2 = var1;

         for (ModuleWidget.TaskSlot var4 : sprint.listeners) {
            var2 = ((ModuleWidget.SprintHandler)var4.callback()).sprint(var0, var2);
         }

         return var2;
      }, var1);
   }

   public static void playerTick(class_746 var0) {
      dispatch(() -> {
         for (ModuleWidget.TaskSlot var2 : playerTick.listeners) {
            ((Consumer)var2.callback()).accept(var0);
         }

         return null;
      }, null);
   }

   public static boolean click(class_636 var0, int var1, int var2, int var3, class_1713 var4, class_1657 var5) {
      return dispatch(() -> {
         for (ModuleWidget.TaskSlot var7 : clicks.listeners) {
            if (((ModuleWidget.InteractHandler)var7.callback()).click(var0, var1, var2, var3, var4, var5)) {
               return true;
            }
         }

         return false;
      }, false);
   }

   public static boolean allowAttackClick(class_310 var0) {
      return dispatch(() -> {
         for (ModuleWidget.TaskSlot var2 : attackClicks.listeners) {
            if (!((Predicate)var2.callback()).test(var0)) {
               return false;
            }
         }

         return true;
      }, true);
   }

   public static void startAttack(class_310 var0) {
      dispatch(() -> {
         for (ModuleWidget.TaskSlot var2 : startAttacks.listeners) {
            ((Consumer)var2.callback()).accept(var0);
         }

         return null;
      }, null);
   }

   public static void actualAttack(class_1657 var0, class_1297 var1) {
      dispatch(() -> {
         for (ModuleWidget.TaskSlot var3 : actualAttacks.listeners) {
            ((ModuleWidget.AttackHandler)var3.callback()).attack(var0, var1);
         }

         return null;
      }, null);
   }

   public static void sound(class_6880<class_3414> var0, double var1, double var3, double var5) {
      dispatch(() -> {
         for (ModuleWidget.TaskSlot var8 : sounds.listeners) {
            ((ModuleWidget.SoundHandler)var8.callback()).sound(var0, var1, var3, var5);
         }

         return null;
      }, null);
   }

   public static void afterWorld() {
      dispatch(() -> {
         for (ModuleWidget.TaskSlot var1 : afterWorld.listeners) {
            ((Runnable)var1.callback()).run();
         }

         return null;
      }, null);
   }

   @FunctionalInterface
   public interface Listener {
      void key(int var1, int var2, int var3);
   }

   @FunctionalInterface
   public interface MouseButtonHandler {
      boolean button(int var1, int var2);
   }

   @FunctionalInterface
   public interface AttackHandler {
      void attack(class_1657 var1, class_1297 var2);
   }

   @FunctionalInterface
   public interface ScrollHandler {
      boolean scroll(double var1, double var3);
   }

   @FunctionalInterface
   public interface KeyHandler {
      boolean key(int var1, int var2, int var3);
   }

   @FunctionalInterface
   public interface MousePressHandler {
      void button(int var1, int var2);
   }

   @FunctionalInterface
   public interface HudExtractHandler {
      boolean extracted(HudLayer var1);
   }

   @FunctionalInterface
   public interface SoundHandler {
      void sound(class_6880<class_3414> var1, double var2, double var4, double var6);
   }

   @FunctionalInterface
   public interface DamageHandler {
      void damage(class_1297 var1, int var2);
   }

   @FunctionalInterface
   public interface InteractHandler {
      boolean click(class_636 var1, int var2, int var3, int var4, class_1713 var5, class_1657 var6);
   }

   @FunctionalInterface
   public interface SprintHandler {
      boolean sprint(class_1309 var1, boolean var2);
   }

   private record TaskSlot<T>(int priority, long sequence, T callback) {
   }

   private record NamedTask(String name, BooleanSupplier active) {
   }

   private static final class TaskQueue<T> {
      private List<ModuleWidget.TaskSlot<T>> listeners = List.of();

      TaskQueue() {
         ModuleWidget.channels.add(this);
      }

      AutoCloseable add(int var1, T var2) {
         ModuleWidget.owner();
         if (ModuleWidget.dispatchDepth != 0) {
            throw new IllegalStateException("Hook mutation during dispatch");
         } else if (this.listeners.size() >= 128) {
            throw new IllegalStateException("Game hook limit");
         } else {
            ModuleWidget.TaskSlot var3 = new ModuleWidget.TaskSlot<>(var1, ++ModuleWidget.sequence, Objects.requireNonNull(var2));
            ArrayList var4 = new ArrayList<>(this.listeners);
            var4.add(var3);
            var4.sort(Comparator.<ModuleWidget.TaskSlot>comparingInt(var0 -> var0.priority()).thenComparingLong(var0 -> var0.sequence()));
            this.listeners = List.copyOf(var4);
            long var5 = var3.sequence();
            return () -> {
               ModuleWidget.owner();
               if (ModuleWidget.dispatchDepth != 0) {
                  throw new IllegalStateException("Hook mutation during dispatch");
               } else {
                  this.listeners = this.listeners.stream().filter(var2x -> var2x.sequence() != var5).toList();
               }
            };
         }
      }
   }
}
