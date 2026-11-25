package dev.purxiy.modules.sound;

import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1109;
import net.minecraft.class_310;

public final class SoundRegistry {
   private static final float d = 0.6F;
   private static int X = 0;
   private static SoundModule u;
   private static ModuleContext R;
   private static int z = 0;
   private static final String[] b = new String[3];

   public static AutoCloseable install(SoundModule var0, ModuleContext var1) {
      u = var0;
      R = var1;
      return UiAudioPolicy.subscribeDetailed((var0x, var1x, var2) -> play(SoundKind.valueOf(var0x), var1x, var2));
   }

   private SoundRegistry() {
   }

   public static void play(SoundKind var0) {
      play(var0, 1.0F, 1.0F);
   }

   public static void play(SoundKind var0, float var1) {
      play(var0, var1, 1.0F);
   }

   public static void play(SoundKind var0, float var1, float var2) {
      long var3 = 102630088780261L;
      long var5 = var3 ^ 68168217942168L;
      int var7 = (int)((var3 ^ 68168217942168L) >>> 48);
      int var8 = (int)((var3 ^ 68168217942168L) << 16 >>> 32);
      int var9 = (int)(var5 << 48 >>> 48);
      SoundModule var10 = u;
      if (var10.F.enabled() && L(var10, var0.F, (char)var7, var8, (short)var9)) {
         long var11 = System.nanoTime();
         long var13 = var11 - var0.H;
         if (var13 >= var0.U) {
            var0.H = var11;
            if (var0 == SoundKind.HOVER) {
               X = var13 < 220000000L ? Math.min(X + 1, 6) : 0;
               var2 *= 1.0F / (1.0F + X * 0.35F);
            }

            float var15 = var0.w * var2 * (float)((Double)var10.A.get() / 0.6F);
            if (!(var15 <= 0.001F)) {
               float var16 = var0.x > 0.0F ? (float)ThreadLocalRandom.current().nextDouble(-var0.x, var0.x) : 0.0F;
               float var17 = Math.max(0.5F, Math.min(2.0F, var1 * (1.0F + var16)));
               R.metric("plays", ++z);
               R.metric("gain", Math.min(1.0F, var15));
               R.metric("pitch", var17);
               class_310.method_1551().method_1483().method_4873(class_1109.method_4757(var0.v, var17, Math.min(1.0F, var15)));
            }
         }
      }
   }

   private static boolean L(SoundModule var0, SoundKind.SoundChannel var1, char var2, int var3, short var4) {
      return switch (SoundRegistry.SoundHandle.Y[var1.ordinal()]) {
         case 1 -> var0.N.enabled();
         case 2 -> var0.Z.enabled();
         case 3 -> var0.J.enabled();
         case 4 -> true;
         default -> throw new MatchException(null, null);
      };
   }

   static class SoundHandle {
      static final int[] Y = new int[SoundKind.SoundChannel.values().length];

      static {
         try {
            Y[SoundKind.SoundChannel.r.ordinal()] = 1;
         } catch (NoSuchFieldError var4) {
         }

         try {
            Y[SoundKind.SoundChannel.X.ordinal()] = 2;
         } catch (NoSuchFieldError var3) {
         }

         try {
            Y[SoundKind.SoundChannel.c.ordinal()] = 3;
         } catch (NoSuchFieldError var2) {
         }

         try {
            Y[SoundKind.SoundChannel.b.ordinal()] = 4;
         } catch (NoSuchFieldError var1) {
         }
      }
   }
}
