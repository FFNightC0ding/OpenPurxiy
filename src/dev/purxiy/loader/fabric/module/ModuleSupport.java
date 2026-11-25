package dev.purxiy.loader.fabric.module;

import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_310;
import net.minecraft.class_3965;
import net.minecraft.class_746;
import net.minecraft.class_239.class_240;

public final class ModuleSupport {
   private static class_1657 attackedPlayer;
   private static class_746 clickedPlayer;
   private static class_746 holdingPlayer;
   private static int attackTick = 0;
   private static int clickTick = 0;
   private static class_1297 attackedTarget;
   private static class_1297 brokenShield;
   private static class_1657 brokenPlayer;
   private static int brokenUntil = 0;
   private static boolean down;
   private static boolean beganOnBlock;

   private ModuleSupport() {
   }

   private static void owner() {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Gameplay history requires the owner thread");
      }
   }

   public static void attacked(class_1657 var0) {
      attacked(var0, (class_1297)null);
   }

   public static void attacked(class_1657 var0, class_1297 var1) {
      owner();
      attackedPlayer = var0;
      attackedTarget = var1;
      attackTick = var0.field_6012;
   }

   public static boolean attackedThisTick(class_1657 var0) {
      owner();
      return attackedPlayer == var0 && attackTick == var0.field_6012;
   }

   public static int ticksSinceAttack(class_1657 var0) {
      owner();
      return attackedPlayer == var0 ? var0.field_6012 - attackTick : Integer.MAX_VALUE;
   }

   public static boolean recentlyHit(class_1657 var0, class_1297 var1, int var2) {
      owner();
      int var3 = ticksSinceAttack(var0);
      return attackedTarget == var1 && var3 >= 0 && var3 <= var2;
   }

   public static void shieldBroken(class_1657 var0, class_1297 var1, int var2) {
      owner();
      if (var2 >= 0 && var2 <= 1200) {
         brokenPlayer = var2 == 0 ? null : var0;
         brokenShield = var2 == 0 ? null : var1;
         brokenUntil = var0.field_6012 + var2;
      } else {
         throw new IllegalArgumentException("Shield history window");
      }
   }

   public static boolean shieldStillDown(class_1657 var0, class_1297 var1) {
      owner();
      return brokenPlayer == var0 && brokenShield == var1 && brokenUntil - var0.field_6012 > 0;
   }

   public static void clicked(class_746 var0) {
      owner();
      clickedPlayer = var0;
      clickTick = var0.field_6012;
   }

   public static boolean clickedThisTick(class_746 var0) {
      owner();
      return clickedPlayer == var0 && clickTick == var0.field_6012;
   }

   public static void observe(class_310 var0) {
      owner();
      if (attackedPlayer != var0.field_1724 || attackedPlayer != null && var0.field_1724.field_6012 - attackTick > 4) {
         attackedPlayer = null;
         attackedTarget = null;
      }

      if (clickedPlayer != var0.field_1724) {
         clickedPlayer = null;
      }

      if (brokenPlayer != var0.field_1724 || brokenPlayer != null && brokenUntil - var0.field_1724.field_6012 <= 0) {
         brokenPlayer = null;
         brokenShield = null;
      }

      if (var0.field_1724 != holdingPlayer) {
         holdingPlayer = var0.field_1724;
         down = false;
         beganOnBlock = false;
      }
   }

   public static void input(class_310 var0) {
      observe(var0);
      if (var0.field_1724 != null && var0.field_1755 == null && var0.method_18506() == null && !var0.method_1493()) {
         boolean var1 = var0.field_1690.field_1886.method_1434();
         if (var1 && !down) {
            boolean var2;
            if (var0.field_1765 instanceof class_3965 var4 && var4.method_17783() == class_240.field_1332) {
               var2 = true;
            } else {
               var2 = false;
            }

            beganOnBlock = var2;
         }

         if (!var1) {
            beganOnBlock = false;
         }

         down = var1;
      } else {
         down = false;
         beganOnBlock = false;
      }
   }

   public static boolean attackBeganOnBlock() {
      owner();
      return down && beganOnBlock;
   }

   public static void disconnected() {
      owner();
      attackedPlayer = null;
      attackedTarget = null;
      clickedPlayer = null;
      holdingPlayer = null;
      brokenPlayer = null;
      brokenShield = null;
      down = false;
      beganOnBlock = false;
   }
}
