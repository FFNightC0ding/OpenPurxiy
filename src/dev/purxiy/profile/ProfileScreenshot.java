package dev.purxiy.profile;

import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.render.RenderPipelines;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import net.minecraft.class_1011;
import net.minecraft.class_156;
import net.minecraft.class_310;

final class ProfileScreenshot {
   private static final DateTimeFormatter NAME = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss");

   private static volatile Path saved;
   private static volatile boolean failed;

   private ProfileScreenshot() {
   }

   static void capture(float var0, float var1, float var2, float var3, float var4, float var5) {
      saved = null;
      failed = false;
      int var6 = Math.round(var0 * var5);
      int var7 = Math.round(var1 * var5);
      int var8 = Math.round(var2 * var5);
      int var9 = Math.round(var3 * var5);
      float var10 = var4 * var5;
      RenderPipelines.get().requestScreenshot(var5x -> class_156.method_27958().execute(() -> {
         try {
            class_1011 var6x = var5x;

            try (class_1011 var7x = new class_1011(var8, var9, false)) {
               int var8x = Math.max(0, var6);
               int var9x = Math.max(0, var7);
               int var10x = Math.min(var8, var5x.method_4307() - var8x);
               int var11 = Math.min(var9, var5x.method_4323() - var9x);
               var5x.method_47594(var7x, var8x, var9x, 0, 0, var10x, var11, false, false);

               for (int var12 = 0; var12 < var9; var12++) {
                  for (int var13 = 0; var13 < var8; var13++) {
                     float var14 = coverage(var13 + 0.5F, var12 + 0.5F, var8, var9, var10);
                     int var15 = var7x.method_61940(var13, var12);
                     int var16 = Math.round((var15 >>> 24 & 0xFF) * var14);
                     var7x.method_61941(var13, var12, var16 << 24 | var15 & 16777215);
                  }
               }

               Path var22 = class_310.method_1551().field_1697.toPath().resolve("screenshots");
               Files.createDirectories(var22);
               LocalDateTime var23 = LocalDateTime.now();
               Path var24 = var22.resolve("purxiy-card_" + var23.format(NAME) + ".png");
               var7x.method_4314(var24);
               saved = var24;
               ClientServices.LOGGER.info("Saved your SourceByDevFFnightmare DeObfDeCompByGuideX card as {}", var24);
            } catch (Throwable var20) {
               if (var5x != null) {
                  try {
                     var6x.close();
                  } catch (Throwable var17) {
                     var20.addSuppressed(var17);
                  }
               }

               throw var20;
            }

            if (var5x != null) {
               var5x.close();
            }
         } catch (Exception var21) {
            failed = true;
            ClientServices.LOGGER.error("Could not save the SourceByDevFFnightmare DeObfDeCompByGuideX card", var21);
         }
      }));
   }


   static Path takeSaved() {
      Path var0 = saved;
      saved = null;
      return var0;
   }

   static boolean takeFailed() {
      boolean var0 = failed;
      failed = false;
      return var0;
   }

   static float coverage(float var0, float var1, float var2, float var3, float var4) {
      float var5 = Math.abs(var0 - var2 / 2.0F) - (var2 / 2.0F - var4);
      float var6 = Math.abs(var1 - var3 / 2.0F) - (var3 / 2.0F - var4);
      float var7 = (float)Math.hypot(Math.max(var5, 0.0F), Math.max(var6, 0.0F)) + Math.min(Math.max(var5, var6), 0.0F) - var4;
      return Math.max(0.0F, Math.min(1.0F, 0.5F - var7));
   }
}
