package dev.purxiy.core;

import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.texture.TextureLoader;
import dev.purxiy.ui.Icons;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1011;
import net.minecraft.class_156;

public final class ImageFiles {
   private static final Set<String> READABLE = Set.of("png", "jpg", "jpeg", "jfif", "bmp", "gif", "tga");
   private static final Set<String> UNREADABLE = Set.of("webp", "heic", "heif", "avif", "tif", "tiff", "svg");
   private static final int SHORT_SIDE = 640;
   private static final long MAX_BYTES = 62914560L;
   private static final float MAX_ZOOM = 4.0F;
   private static final ExecutorService IO = Executors.newSingleThreadExecutor(var0 -> {
      Thread var1 = new Thread(var0, "SourceByDevFFnightmare DeObfDeCompByGuideX Photo");
      var1.setDaemon(true);
      var1.setPriority(4);
      return var1;
   });

   private static TextureLoader texture;
   private static int width = 1;
   private static int height = 1;
   private static float changedAt = -100.0F;
   private static long lastScan = 0L;
   private static final AtomicReference<ImageFiles.ImageLoad> READY = new AtomicReference<>();
   private static volatile boolean scanning;
   private static volatile boolean reading;

   private static volatile String problem;
   private static final Set<String> FAILED = ConcurrentHashMap.newKeySet();
   private static String handed = "";
   private static boolean scannedOnce;

   private ImageFiles() {
   }

   public static Path folder() {
      return FabricLoader.getInstance().getGameDir().resolve("purxiy").resolve("photo");
   }

   public static void init() {
      IdentityPaths.load();
      requestScan();
   }

   public static void update() {
      ImageFiles.ImageLoad var0 = READY.getAndSet(null);
      if (var0 != null) {
         apply(var0);
      }

      if (!scanning && System.nanoTime() - lastScan > 1000000000L) {
         requestScan();
      }
   }

   public static boolean present() {
      update();
      return texture != null;
   }

   public static float changedAt() {
      return changedAt;
   }

   public static boolean busy() {
      return reading;
   }


   public static String problem() {
      return problem;
   }

   public static float aspect() {
      return (float)width / height;
   }

   private static void requestScan() {
      lastScan = System.nanoTime();
      scanning = true;
      IO.execute(() -> {
         try {
            scan();
         } catch (RuntimeException var4) {
            ClientServices.LOGGER.error("Could not look for a profile photo", var4);
         } finally {
            scanning = false;
         }
      });
   }

   private static void apply(ImageFiles.ImageLoad var0) {
      if (texture != null) {
         texture.close();
         texture = null;
      }

      if (var0.image() != null) {
         width = var0.image().method_4307();
         height = var0.image().method_4323();
         texture = TextureLoader.of("profile photo", var0.image());
         if (var0.fresh() || !IdentityPaths.framed) {
            resetFrame();
         }
      }

      changedAt = RenderPipelines.get().time();
      IdentityPaths.save();
   }

   private static void scan() {
      List<ImageFiles.ImageRecord>var0 = list(folder());
      String var1 = IdentityPaths.photo;
      ImageFiles.ImageRecord var2 = null;
      ImageFiles.ImageRecord var3 = null;

      for (ImageFiles.ImageRecord var5 : ((Iterable<ImageFiles.ImageRecord>)(Object)var0)) {
         if (var5.key().equals(var1)) {
            var2 = var5;
         }
      }

      for (ImageFiles.ImageRecord var10 : ((Iterable<ImageFiles.ImageRecord>)(Object)var0)) {
         if (var10 != var2 && !FAILED.contains(var10.key()) && (var3 == null || var10.added() > var3.added())) {
            var3 = var10;
         }
      }

      if (var3 != null) {
         class_1011 var8 = read(var3);
         if (var8 != null) {
            IdentityPaths.photo = var3.key();
            handed = var3.key();
            hand(new ImageFiles.ImageLoad(var3.key(), var8, true));

            for (ImageFiles.ImageRecord var6 : ((Iterable<ImageFiles.ImageRecord>)(Object)var0)) {
               if (var6 != var3 && READABLE.contains(var6.extension())) {
                  moveToPrevious(var6.path());
               }
            }

            scannedOnce = true;
            return;
         }
      }

      if (var2 == null) {
         IdentityPaths.photo = "";
         if (!handed.isEmpty() || !scannedOnce) {
            handed = "";
            hand(new ImageFiles.ImageLoad("", (class_1011)null, false));
         }
      } else if (!var2.key().equals(handed)) {
         class_1011 var9 = read(var2);
         if (var9 != null) {
            handed = var2.key();
            hand(new ImageFiles.ImageLoad(var2.key(), var9, false));
         }
      }

      scannedOnce = true;
   }

   private static void hand(ImageFiles.ImageLoad var0) {
      ImageFiles.ImageLoad var1 = READY.getAndSet(var0);
      if (var1 != null && var1.image() != null) {
         var1.image().close();
      }
   }


   private static class_1011 read(ImageFiles.ImageRecord var0) {
      if (!READABLE.contains(var0.extension())) {
         FAILED.add(var0.key());
         problem = "This kind of picture is not supported. Use a PNG or a JPG.";
         return null;
      } else {
         reading = true;

         class_1011 var14;
         try {
            if (Files.size(var0.path()) > 62914560L) {
               FAILED.add(var0.key());
               problem = "That picture is too large. Use one under 60 MB.";
               Object var1 = null;
               return (class_1011)var1;
            }

            VersionGate.PixelImage var2 = VersionGate.decode(Files.readAllBytes(var0.path()), 640);
            class_1011 var12 = new class_1011(var2.width(), var2.height(), false);
            int[] var13 = var2.argb();

            for (int var5 = 0; var5 < var2.height(); var5++) {
               for (int var6 = 0; var6 < var2.width(); var6++) {
                  var12.method_61941(var6, var5, var13[var5 * var2.width() + var6]);
               }
            }

            problem = null;
            var14 = var12;
         } catch (IOException | RuntimeException var10) {
            FAILED.add(var0.key());
            problem = "That picture could not be read. Try another PNG or JPG.";
            ClientServices.LOGGER.warn("Could not read the picture {}: {}", var0.path(), var10.getMessage());
            Object var3 = null;
            return (class_1011)var3;
         } finally {
            reading = false;
         }

         return var14;
      }
   }

   private static List<ImageFiles.ImageRecord> list(Path var0) {
      ArrayList var1 = new ArrayList();
      if (!Files.isDirectory(var0)) {
         return var1;
      } else {
         try (Stream var2 = Files.list(var0)) {
            for (Path var4 : (Iterable<Path>) var2::iterator) {
               String var5 = var4.getFileName().toString();
               if (!var5.startsWith(".") && !var5.startsWith("~$")) {
                  try {
                     BasicFileAttributes var6 = Files.readAttributes(var4, BasicFileAttributes.class);
                     if (var6.isRegularFile() && var6.size() != 0L) {
                        ImageFiles.ImageRecord var7 = new ImageFiles.ImageRecord(
                           var4,
                           var5,
                           var5 + "|" + var6.size() + "|" + var6.lastModifiedTime().toMillis(),
                           Math.max(var6.creationTime().toMillis(), var6.lastModifiedTime().toMillis())
                        );
                        if (READABLE.contains(var7.extension()) || UNREADABLE.contains(var7.extension())) {
                           var1.add(var7);
                        }
                     }
                  } catch (IOException var9) {
                  }
               }
            }
         } catch (IOException var11) {
            ClientServices.LOGGER.warn("Could not look into {}: {}", var0, var11.getMessage());
         }

         return var1;
      }
   }

   private static void moveToPrevious(Path var0) {
      try {
         Path var1 = Files.createDirectories(var0.resolveSibling("previous"));
         Files.move(var0, unique(var1, var0.getFileName().toString()));
      } catch (IOException var2) {
         ClientServices.LOGGER.warn("Could not move {} to previous: {}", var0, var2.getMessage());
      }
   }

   private static Path unique(Path var0, String var1) {
      Path var2 = var0.resolve(var1);
      int var3 = var1.lastIndexOf(46);
      String var4 = var3 < 0 ? var1 : var1.substring(0, var3);
      String var5 = var3 < 0 ? "" : var1.substring(var3);

      for (int var6 = 2; Files.exists(var2); var6++) {
         var2 = var0.resolve(var4 + " (" + var6 + ")" + var5);
      }

      return var2;
   }

   public static void openFolder() {
      Path var0 = folder();

      try {
         Files.createDirectories(var0);
      } catch (IOException var2) {
         ClientServices.LOGGER.warn("Could not make {}: {}", var0, var2.getMessage());
      }

      class_156.method_668().method_60932(var0);
      lastScan = 0L;
   }

   public static boolean importFiles(List<Path> var0) {
      Path var1 = null;

      for (Path var3 : var0) {
         String var4 = var3.getFileName().toString().toLowerCase(Locale.ROOT);
         int var5 = var4.lastIndexOf(46);
         String var6 = var5 < 0 ? "" : var4.substring(var5 + 1);
         if (Files.isRegularFile(var3) && (READABLE.contains(var6) || UNREADABLE.contains(var6))) {
            var1 = var3;
            break;
         }
      }

      if (var1 == null) {
         problem = "That is not a picture. Use a PNG or a JPG.";
         return false;
      } else {
         scanning = true;
         Path var7 = var1;
         IO.execute(() -> {
            try {
               Path var1x = Files.createDirectories(folder());
               Path var2 = unique(var1x, var7.getFileName().toString());
               Files.copy(var7, var2);
               Files.setLastModifiedTime(var2, FileTime.from(Instant.now()));
               scan();
            } catch (IOException | RuntimeException var6x) {
               problem = "That picture could not be copied.";
               ClientServices.LOGGER.warn("Could not copy {} to the photo folder: {}", var7, var6x.getMessage());
            } finally {
               scanning = false;
               lastScan = System.nanoTime();
            }
         });
         return true;
      }
   }

   public static void remove() {
      String var0 = IdentityPaths.photo;
      ImageFiles.ImageLoad var1 = READY.getAndSet(null);
      if (var1 != null && var1.image() != null) {
         var1.image().close();
      }

      if (texture != null) {
         texture.close();
         texture = null;
      }

      changedAt = RenderPipelines.get().time();
      problem = null;
      IO.execute(() -> {
         for (ImageFiles.ImageRecord var2 : list(folder())) {
            if (var2.key().equals(var0)) {
               moveToPrevious(var2.path());
            }
         }

         IdentityPaths.photo = "";
         handed = "";
         IdentityPaths.save();
      });
   }

   private static float[] window() {
      float var0 = Math.min(width, height) / IdentityPaths.zoom;
      float var1 = var0 / 2.0F / width;
      float var2 = var0 / 2.0F / height;
      float var3 = clamp(IdentityPaths.centreX, var1, 1.0F - var1);
      float var4 = clamp(IdentityPaths.centreY, var2, 1.0F - var2);
      return new float[]{var3 - var1, var4 - var2, var3 + var1, var4 + var2};
   }

   public static void pan(float var0, float var1) {
      float var2 = Math.min(width, height) / IdentityPaths.zoom;
      IdentityPaths.centreX = IdentityPaths.centreX - var0 * var2 / width;
      IdentityPaths.centreY = IdentityPaths.centreY - var1 * var2 / height;
      IdentityPaths.framed = true;
      keepInside();
   }

   public static void zoom(float var0, float var1, float var2) {
      float var3 = Math.min(width, height) / IdentityPaths.zoom;
      float var4 = IdentityPaths.centreX + var1 * var3 / width;
      float var5 = IdentityPaths.centreY + var2 * var3 / height;
      IdentityPaths.zoom = clamp(IdentityPaths.zoom * var0, 1.0F, 4.0F);
      float var6 = Math.min(width, height) / IdentityPaths.zoom;
      IdentityPaths.centreX = var4 - var1 * var6 / width;
      IdentityPaths.centreY = var5 - var2 * var6 / height;
      IdentityPaths.framed = true;
      keepInside();
   }

   public static float zoomLevel() {
      return IdentityPaths.zoom;
   }

   public static void resetFrame() {
      IdentityPaths.zoom = 1.0F;
      IdentityPaths.centreX = 0.5F;
      IdentityPaths.centreY = height > width * 1.05F ? 0.42F : 0.5F;
      IdentityPaths.framed = false;
      keepInside();
   }

   public static boolean framedByHand() {
      return IdentityPaths.framed;
   }

   public static void saveFrame() {
      IdentityPaths.save();
   }

   public static CompletableFuture<String> websitePortrait() {
      String var0 = IdentityPaths.photo;
      float var1 = IdentityPaths.centreX;
      float var2 = IdentityPaths.centreY;
      float var3 = IdentityPaths.zoom;
      return CompletableFuture.supplyAsync(() -> {
         if (var0.isEmpty()) {
            return "";
         } else {
            try {
               for (ImageFiles.ImageRecord var5 : list(folder())) {
                  if (var5.key().equals(var0)) {
                     return croppedJpeg(var5, var1, var2, var3);
                  }
               }

               return "";
            } catch (IOException | RuntimeException var6) {
               return null;
            }
         }
      }, IO);
   }

   private static String croppedJpeg(ImageFiles.ImageRecord var0, float var1, float var2, float var3) throws IOException {
      byte[] var5;
      try (InputStream var4 = Files.newInputStream(var0.path())) {
         var5 = var4.readNBytes(62914561);
      }

      if (var5.length > 62914560L) {
         return null;
      } else {
         VersionGate.PixelImage var6 = VersionGate.decode(var5, 640);
         int var7 = var6.width();
         int var8 = var6.height();
         int var9 = Math.max(1, Math.round(Math.min(var7, var8) / Math.max(1.0F, var3)));
         int var10 = Math.clamp((long)Math.round(var1 * var7 - var9 / 2.0F), 0, var7 - var9);
         int var11 = Math.clamp((long)Math.round(var2 * var8 - var9 / 2.0F), 0, var8 - var9);
         BufferedImage var12 = new BufferedImage(var9, var9, 2);
         var12.setRGB(0, 0, var9, var9, var6.argb(), var11 * var7 + var10, var7);
         short var13 = 160;
         BufferedImage var14 = new BufferedImage(var13, var13, 1);
         Graphics2D var15 = var14.createGraphics();

         try {
            var15.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            var15.drawImage(var12, 0, 0, var13, var13, (ImageObserver)null);
         } finally {
            var15.dispose();
         }

         ByteArrayOutputStream var16 = new ByteArrayOutputStream();
         return ImageIO.write(var14, "jpeg", var16) && var16.size() <= 32768 ? Base64.getEncoder().encodeToString(var16.toByteArray()) : null;
      }
   }

   private static void keepInside() {
      float var0 = Math.min(width, height) / IdentityPaths.zoom;
      float var1 = var0 / 2.0F / width;
      float var2 = var0 / 2.0F / height;
      IdentityPaths.centreX = clamp(IdentityPaths.centreX, var1, 1.0F - var1);
      IdentityPaths.centreY = clamp(IdentityPaths.centreY, var2, 1.0F - var2);
   }

   public static boolean draw(Canvas var0, float var1, float var2, float var3, float var4, int var5) {
      update();
      if (texture == null) {
         return false;
      } else {
         float[] var6 = window();
         var0.image(texture.view(), texture.sampler(), true, var1, var2, var3, var3, var6[0], var6[1], var6[2], var6[3], var4, var5);
         return true;
      }
   }

   public static void drawOrInitial(Canvas var0, float var1, float var2, float var3, String var4) {
      if (!draw(var0, var1 - var3 / 2.0F, var2 - var3 / 2.0F, var3, var3 / 2.0F, -1)) {
         initial(var0, var1, var2, var3, var4);
      }
   }

   public static void initial(Canvas var0, float var1, float var2, float var3, String var4) {
      float var5 = var3 / 2.0F;
      var0.roundRectGradient(var1 - var5, var2 - var5, var3, var3, var5, 135.0F, Icons.INDIGO, Icons.MAGENTA);
      var0.roundRectCorners(var1 - var5, var2 - var5, var3, var3, var5, 822083583, 352321535, 16777215, 16777215);
      String var6 = initialOf(var4);
      if (var6.isEmpty()) {
         var0.icon("user-round", var1, var2, var3 * 0.44F, BlendMode.withAlpha(Icons.TEXT, 0.92F));
      } else {
         Translations.pause();
         var0.text(Fonts.SEMIBOLD, var6, var1, var2 + var3 * 0.02F, var3 * 0.44F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
         Translations.resume();
      }
   }

   public static String initialOf(String var0) {
      int var2 = 0;

      while (var2 < var0.length()) {
         int var1 = var0.codePointAt(var2);
         if (Character.isLetterOrDigit(var1)) {
            return new String(Character.toChars(var1)).toUpperCase(Locale.ROOT);
         }

         var2 += Character.charCount(var1);
      }

      return "";
   }

   private static float clamp(float var0, float var1, float var2) {
      return var2 < var1 ? (var1 + var2) / 2.0F : (var0 < var1 ? var1 : Math.min(var0, var2));
   }

   private record ImageRecord(Path path, String name, String key, long added) {
      String extension() {
         int var1 = this.name.lastIndexOf(46);
         return var1 < 0 ? "" : this.name.substring(var1 + 1).toLowerCase(Locale.ROOT);
      }
   }

   private record ImageLoad(String key,  class_1011 image, boolean fresh) {
   }
}
