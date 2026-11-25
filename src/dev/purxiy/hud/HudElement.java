package dev.purxiy.hud;

import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
import net.minecraft.class_310;
import net.minecraft.class_746;

public final class HudElement {
   private static final long SECOND = 1000000000L;
   private static final LongArrayFIFOQueue[] CLICKS = new LongArrayFIFOQueue[]{new LongArrayFIFOQueue(), new LongArrayFIFOQueue()};
   public static final float[] FRAME_TIMES = new float[96];
   private static int frameIndex = 0;
   private static float fps = 60.0F;
   private static float fpsTimer;
   private static int fpsFrames = 0;
   private static float fpsAccumulated;
   private static double lastX;
   private static double lastZ;
   private static double lastY;
   private static boolean hasLast;
   private static float speed;
   private static float verticalSpeed;
   private static long sessionStart = 0L;
   private static boolean inWorld;

   private HudElement() {
   }

   public static void onClick(int var0) {
      if (var0 >= 0 && var0 < CLICKS.length) {
         CLICKS[var0].enqueue(System.nanoTime());
      }
   }

   public static int cps(int var0) {
      LongArrayFIFOQueue var1 = CLICKS[var0];
      long var2 = System.nanoTime() - 1000000000L;

      while (!var1.isEmpty() && var1.firstLong() < var2) {
         var1.dequeueLong();
      }

      return var1.size();
   }

   public static void frame(float var0) {
      FRAME_TIMES[frameIndex] = var0;
      frameIndex = (frameIndex + 1) % FRAME_TIMES.length;
      fpsTimer += var0;
      fpsFrames++;
      fpsAccumulated += var0;
      if (fpsTimer >= 0.25F) {
         fps = fpsFrames / Math.max(1.0E-4F, fpsAccumulated);
         fpsTimer = 0.0F;
         fpsFrames = 0;
         fpsAccumulated = 0.0F;
      }
   }

   public static int fps() {
      return Math.round(fps);
   }

   public static float frameTime(int var0) {
      return FRAME_TIMES[Math.floorMod(frameIndex - 1 - var0, FRAME_TIMES.length)];
   }

   public static void tick(class_310 var0) {
      class_746 var1 = var0.field_1724;
      boolean var2 = var1 != null && var0.field_1687 != null;
      if (var2 && !inWorld) {
         sessionStart = System.currentTimeMillis();
         hasLast = false;
      }

      inWorld = var2;
      if (!var2) {
         hasLast = false;
         speed = 0.0F;
      } else {
         double var3 = var1.method_23317();
         double var5 = var1.method_23318();
         double var7 = var1.method_23321();
         if (hasLast) {
            double var9 = var3 - lastX;
            double var11 = var7 - lastZ;
            float var13 = (float)Math.sqrt(var9 * var9 + var11 * var11) * 20.0F;
            speed = speed + (var13 - speed) * 0.5F;
            verticalSpeed = verticalSpeed + ((float)(var5 - lastY) * 20.0F - verticalSpeed) * 0.5F;
         }

         lastX = var3;
         lastY = var5;
         lastZ = var7;
         hasLast = true;
      }
   }

   public static float speed() {
      return speed;
   }

   public static float verticalSpeed() {
      return verticalSpeed;
   }

   public static long sessionMillis() {
      return inWorld ? System.currentTimeMillis() - sessionStart : 0L;
   }
}
