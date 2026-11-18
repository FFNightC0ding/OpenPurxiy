// Sourced By DevFFNightmare
package dev.purxiy.ui.config;

import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.core.VersionGate;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.texture.TextureLoader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.HexFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.minecraft.class_1011;
import net.minecraft.class_12137;
import net.minecraft.class_310;

import org.lwjgl.PointerBuffer;
import org.lwjgl.stb.STBIWriteCallback;
import org.lwjgl.stb.STBImageResize;
import org.lwjgl.stb.STBImageWrite;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.util.tinyfd.TinyFileDialogs;

final class ConfigLibraryRenderer13 {
   static final Set<String> READABLE = Set.of("png", "jpg", "jpeg", "jfif", "bmp", "gif", "tga");
   private static final Set<String> UNREADABLE = Set.of("webp", "heic", "heif", "avif", "tif", "tiff", "svg");
   private static final int LONG_SIDE = 1280;
   private static final int QUALITY = 88;
   private static final long MAX_BYTES = 62914560L;
   private static final int CARD_SHORT = 300;
   private static final int MAX_TEXTURES = 24;
   private static final float IDLE_SECONDS = 20.0F;
   private static final int BACKDROP = 1183266;
   private static final ExecutorService IO = Executors.newSingleThreadExecutor(var0 -> {
      Thread var1 = new Thread(var0, "Purxiy Config Photos");
      var1.setDaemon(true);
      var1.setPriority(4);
      return var1;
   });
   private static final Map<String, ConfigLibraryRenderer13.TypeQ> SHOWN = new HashMap<>();
   private static final Set<String> LOADING = new HashSet<>();
   private static final Set<String> FAILED = new HashSet<>();
   private static final Set<String> IMPORTING = new HashSet<>();
   private static boolean picking;
   private static float now;

   private ConfigLibraryRenderer13() {
   }

   
   private static String key(ConfigLibraryFactory2 var0) {
      if (var0.photo == null) {
         return null;
      } else {
         return var0.mine() ? "mine/" + var0.photo : "discover/" + var0.code + "-" + var0.photo + ".jpg";
      }
   }

   private static Path file(String var0) {
      return ConfigLibraryLoader.folder().resolve(var0.startsWith("mine/") ? "photos/" + var0.substring(5) : var0);
   }

   
   static byte[] read(ConfigLibraryFactory2 var0) {
      String var1 = key(var0);
      if (var1 != null && var0.mine()) {
         try {
            return Files.readAllBytes(file(var1));
         } catch (IOException var3) {
            return null;
         }
      } else {
         return null;
      }
   }

   static void tick(float var0) {
      now = var0;

      for (ConfigLibraryRenderer13.TypeQ var2 : SHOWN.values()) {
         if (!var2.uploaded && var2.texture != null && var0 - var2.usedAt < 1.0F) {
            var2.texture.view();
            var2.uploaded = true;
            break;
         }
      }

      if (SHOWN.size() > 0) {
         ArrayList var6 = new ArrayList();

         for (Entry var3 : SHOWN.entrySet()) {
            var6.add(var3);
         }

         var6.sort(
            (var0x, var1) -> Float.compare(((ConfigLibraryRenderer13.TypeQ)((java.util.Map.Entry<?, ?>)var0x).getValue()).usedAt, ((ConfigLibraryRenderer13.TypeQ)((java.util.Map.Entry<?, ?>)var1).getValue()).usedAt)
         );
         int var8 = SHOWN.size() - 24;

         for (Entry var4 : ((Iterable<Entry>)(Object)var6)) {
            ConfigLibraryRenderer13.TypeQ var5 = (ConfigLibraryRenderer13.TypeQ)var4.getValue();
            if (var8 > 0 || var0 - var5.usedAt > 20.0F) {
               close(var5);
               SHOWN.remove(var4.getKey());
               var8--;
            }
         }
      }
   }

   private static void close(ConfigLibraryRenderer13.TypeQ var0) {
      if (var0.texture != null) {
         var0.texture.close();
         var0.texture = null;
      }
   }

   
   static ConfigLibraryRenderer13.TypeQ get(ConfigLibraryFactory2 var0, boolean var1) {
      String var2 = key(var0);
      if (var2 == null) {
         return null;
      } else {
         String var3 = var2 + (var1 ? "|big" : "|card");
         ConfigLibraryRenderer13.TypeQ var4 = SHOWN.get(var3);
         if (var4 != null) {
            var4.usedAt = now;
            if (!var4.uploaded) {
               return null;
            } else {
               if (Float.isNaN(var4.readyAt)) {
                  var4.readyAt = now;
               }

               return var4;
            }
         } else {
            if (!LOADING.contains(var3) && !FAILED.contains(var2)) {
               load(var3, var2, var1, var0.mine() ? null : "/api/configs/" + var0.code + "/photo?v=" + var0.photo);
            }

            return null;
         }
      }
   }

   static boolean has(ConfigLibraryFactory2 var0) {
      String var1 = key(var0);
      return var1 != null && !FAILED.contains(var1);
   }

   static boolean importing(ConfigLibraryFactory2 var0) {
      return IMPORTING.contains(var0.id);
   }

   static float appear(ConfigLibraryFactory2 var0, boolean var1) {
      ConfigLibraryRenderer13.TypeQ var2 = get(var0, var1);
      if (var2 != null && var2.texture != null) {
         float var3 = Math.min(1.0F, Math.max(0.0F, (now - var2.readyAt) / 0.45F));
         return var3 * var3 * (3.0F - 2.0F * var3);
      } else {
         return 0.0F;
      }
   }

   static float draw(
      Canvas var0,
      ConfigLibraryFactory2 var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      boolean var10,
      ConfigLibraryRenderer13.Mode var11,
      float var12
   ) {
      ConfigLibraryRenderer13.TypeQ var13 = get(var1, var10);
      if (var13 != null && var13.texture != null && !(var4 <= 0.0F) && !(var5 <= 0.0F)) {
         float var14 = appear(var1, var10);
         float var15 = var4 / var5;
         float var16 = 1.0F;
         float var17 = 1.0F;
         if (var13.aspect > var15) {
            var16 = var15 / var13.aspect;
         } else {
            var17 = var13.aspect / var15;
         }

         var16 /= var7;
         var17 /= var7;
         float var18 = 0.5F + var8 * (1.0F - var16) * 0.5F;
         float var19 = 0.5F + var9 * (1.0F - var17) * 0.5F;
         float var20 = var18 - var16 / 2.0F;
         float var21 = var19 - var17 / 2.0F;
         float var22 = var18 + var16 / 2.0F;
         float var23 = var19 + var17 / 2.0F;
         GpuTextureView var24 = var13.texture.view();
         class_12137 var25 = var13.texture.sampler();
         byte var26 = -1;
         int var27 = 16777215;
         var0.pushAlpha(var14);
         switch (var11) {
            case BOTTOM:
               float var28 = var5 * (1.0F - var12);
               float var29 = var21 + (var23 - var21) * (1.0F - var12);
               float var30 = Math.min(2.0F, var5 * var12);
               var0.imageCorners(
                  var24,
                  var25,
                  true,
                  var2,
                  var3,
                  var4,
                  var28 + var30,
                  var20,
                  var21,
                  var22,
                  var29 + (var23 - var21) * var30 / var5,
                  var6,
                  var6,
                  0.0F,
                  0.0F,
                  var26,
                  var26,
                  var26,
                  var26
               );
               var0.imageCorners(
                  var24, var25, true, var2, var3 + var28, var4, var5 - var28, var20, var29, var22, var23, 0.0F, 0.0F, var6, var6, var26, var26, var27, var27
               );
               break;
            case LEFT:
               float var31 = var4 * var12;
               float var32 = var20 + (var22 - var20) * var12;
               float var33 = Math.min(2.0F, var31);
               var0.imageCorners(var24, var25, true, var2, var3, var31, var5, var20, var21, var32, var23, var6, 0.0F, 0.0F, var6, var27, var26, var26, var27);
               var0.imageCorners(
                  var24,
                  var25,
                  true,
                  var2 + var31 - var33,
                  var3,
                  var4 - var31 + var33,
                  var5,
                  var32 - (var22 - var20) * var33 / var4,
                  var21,
                  var22,
                  var23,
                  0.0F,
                  var6,
                  var6,
                  0.0F,
                  var26,
                  var26,
                  var26,
                  var26
               );
               break;
            default:
               var0.image(var24, var25, true, var2, var3, var4, var5, var20, var21, var22, var23, var6, var26);
         }

         var0.popAlpha();
         return var14;
      } else {
         return 0.0F;
      }
   }

   private static void load(String var0, String var1, boolean var2,  String var3) {
      LOADING.add(var0);
      Path var4 = file(var1);
      Runnable var5 = () -> {
         class_1011 var4x = null;

         try {
            Files.setLastModifiedTime(var4, FileTime.fromMillis(System.currentTimeMillis()));
            VersionGate.TypeV var5x = VersionGate.decode(Files.readAllBytes(var4), var2 ? 4096 : 300);
            var4x = new class_1011(var5x.width(), var5x.height(), false);
            int[] var6 = var5x.argb();

            for (int var7 = 0; var7 < var5x.height(); var7++) {
               for (int var8 = 0; var8 < var5x.width(); var8++) {
                  var4x.method_61941(var8, var7, var6[var7 * var5x.width() + var8]);
               }
            }
         } catch (IOException | RuntimeException var9) {
            ClientServices.LOGGER.warn("Could not show the config photo {}: {}", var4, var9.toString());
         }

         class_1011 var10 = var4x;
         class_310.method_1551().execute(() -> {
            LOADING.remove(var0);
            if (var10 == null) {
               FAILED.add(var1);
            } else {
               ConfigLibraryRenderer13.TypeQ var3xx = new ConfigLibraryRenderer13.TypeQ();
               var3xx.aspect = (float)var10.method_4307() / var10.method_4323();
               var3xx.texture = TextureLoader.of("config photo", var10);
               var3xx.usedAt = now;
               ConfigLibraryRenderer13.TypeQ var4xx = SHOWN.put(var0, var3xx);
               if (var4xx != null) {
                  close(var4xx);
               }
            }
         });
      };
      if (var3 != null && !Files.isRegularFile(var4)) {
         ConfigLibrarySupport3.bytes(var3).whenCompleteAsync((var4x, var5x) -> {
            if (var5x == null && var4x != null) {
               try {
                  Files.createDirectories(var4.getParent());
                  Path var6 = var4.resolveSibling(var4.getFileName() + ".part");
                  Files.write(var6, var4x);
                  Files.move(var6, var4, StandardCopyOption.REPLACE_EXISTING);
               } catch (IOException var7) {
                  ClientServices.LOGGER.warn("Could not keep the config photo {}: {}", var4, var7.toString());
               }

               var5.run();
            } else {
               class_310.method_1551().execute(() -> {
                  LOADING.remove(var0);
                  if (var5x != null && !ConfigLibrarySupport3.unreachable(var5x)) {
                     FAILED.add(var1);
                  }
               });
            }
         }, IO);
      } else {
         IO.execute(var5);
      }
   }

   static void choose(Consumer<Path> var0) {
      if (!picking) {
         picking = true;
         Thread var1 = new Thread(
            () -> {
               String var1x = null;

               try {
                  MemoryStack var2 = MemoryStack.stackPush();

                  try {
                     String[] var3 = new String[]{"*.png", "*.jpg", "*.jpeg", "*.jfif", "*.bmp", "*.gif", "*.tga"};
                     PointerBuffer var4 = var2.mallocPointer(var3.length);

                     for (String var8 : var3) {
                        var4.put(var2.UTF8(var8));
                     }

                     var4.flip();
                     Path var13 = Path.of(System.getProperty("user.home"), "Pictures");
                     String var14 = Files.isDirectory(var13) ? var13 + File.separator : "";
                     var1x = TinyFileDialogs.tinyfd_openFileDialog(
                        Translations.tr("Choose a photo for this config"), var14, var4, Translations.tr("Pictures (PNG, JPG)"), false
                     );
                  } catch (Throwable var10) {
                     if (var2 != null) {
                        try {
                           var2.close();
                        } catch (Throwable var9) {
                           var10.addSuppressed(var9);
                        }
                     }

                     throw var10;
                  }

                  if (var2 != null) {
                     var2.close();
                  }
               } catch (Throwable var11) {
                  ClientServices.LOGGER.warn("Could not open the file picker", var11);
               }

               String var12 = var1x;
               class_310.method_1551().execute(() -> {
                  picking = false;
                  var0.accept(var12 != null && !var12.isBlank() ? Path.of(var12) : null);
               });
            },
            "Purxiy Photo Picker"
         );
         var1.setDaemon(true);
         var1.start();
      }
   }

   static boolean picture(Path var0) {
      String var1 = extension(var0);
      return READABLE.contains(var1) || UNREADABLE.contains(var1);
   }

   private static String extension(Path var0) {
      String var1 = var0.getFileName().toString().toLowerCase(Locale.ROOT);
      int var2 = var1.lastIndexOf(46);
      return var2 < 0 ? "" : var1.substring(var2 + 1);
   }

   static void importPicture(ConfigLibraryFactory2 var0, Path var1, BiConsumer<String, String> var2) {
      if (!READABLE.contains(extension(var1))) {
         var2.accept(null, "This kind of picture is not supported. Use a PNG or a JPG.");
      } else {
         IMPORTING.add(var0.id);
         IO.execute(() -> {
            try {
               if (Files.size(var1) > 62914560L) {
                  finish(var0, (String)null, "That picture is too large. Use one under 60 MB.", var2);
                  return;
               }

               encode(var0, Files.readAllBytes(var1), var2);
            } catch (IOException var4) {
               finish(var0, (String)null, "That picture could not be read. Try another PNG or JPG.", var2);
            }
         });
      }
   }

   static void importBytes(ConfigLibraryFactory2 var0, byte[] var1, BiConsumer<String, String> var2) {
      IMPORTING.add(var0.id);
      IO.execute(() -> encode(var0, var1, var2));
   }

   private static void encode(ConfigLibraryFactory2 var0, byte[] var1, BiConsumer<String, String> var2) {
      try {
         VersionGate.TypeV var3 = VersionGate.decode(var1, 960);
         int var4 = var3.width();
         int var5 = var3.height();
         if (Math.min(var4, var5) < 16) {
            finish(var0, (String)null, "That picture is too small for a photo.", var2);
            return;
         }

         float var6 = Math.min(1.0F, 1280.0F / Math.max(var4, var5));
         int var7 = Math.max(16, Math.round(var4 * var6));
         int var8 = Math.max(16, Math.round(var5 * var6));
         ByteBuffer var9 = MemoryUtil.memAlloc(var4 * var5 * 3);
         ByteBuffer var10 = MemoryUtil.memAlloc(var7 * var8 * 3);

         byte[] var11;
         try {
            int[] var12 = var3.argb();

            for (int var16 : var12) {
               int var17 = var16 >>> 24;
               var9.put((byte)blend(var16 >> 16 & 0xFF, 18, var17));
               var9.put((byte)blend(var16 >> 8 & 0xFF, 14, var17));
               var9.put((byte)blend(var16 & 0xFF, 34, var17));
            }

            var9.flip();
            if (var7 == var4 && var8 == var5) {
               MemoryUtil.memCopy(var9, var10);
            } else if (!STBImageResize.stbir_resize_uint8(var9, var4, var5, 0, var10, var7, var8, 0, 3)) {
               throw new IOException("resize failed");
            }

            ByteArrayOutputStream var28 = new ByteArrayOutputStream(262144);
            STBIWriteCallback var30 = STBIWriteCallback.create((var1x, var3x, var5x) -> {
               ByteBuffer var6x = STBIWriteCallback.getData(var3x, var5x);
               byte[] var7x = new byte[var5x];
               var6x.get(var7x);
               var28.write(var7x, 0, var5x);
            });

            try {
               if (STBImageWrite.stbi_write_jpg_to_func(var30, 0L, var7, var8, 3, var10, 88) == 0) {
                  throw new IOException("JPEG encoding failed");
               }
            } catch (Throwable var24) {
               if (var30 != null) {
                  try {
                     var30.close();
                  } catch (Throwable var23) {
                     var24.addSuppressed(var23);
                  }
               }

               throw var24;
            }

            if (var30 != null) {
               var30.close();
            }

            var11 = var28.toByteArray();
         } finally {
            MemoryUtil.memFree(var9);
            MemoryUtil.memFree(var10);
         }

         String var27 = var0.id.substring(0, Math.min(8, var0.id.length()));
         String var29 = var27 + "-" + hash(var11).substring(0, 12) + ".jpg";
         Path var31 = ConfigLibraryLoader.photos().resolve(var29);
         Files.createDirectories(var31.getParent());
         Path var32 = var31.resolveSibling(var29 + ".tmp");
         Files.write(var32, var11);
         Files.move(var32, var31, StandardCopyOption.REPLACE_EXISTING);
         finish(var0, var29, (String)null, var2);
      } catch (IOException | RuntimeException var26) {
         ClientServices.LOGGER.warn("Could not read a picture for config {}: {}", var0.name, var26.toString());
         finish(var0, (String)null, "That picture could not be read. Try another PNG or JPG.", var2);
      }
   }

   private static int blend(int var0, int var1, int var2) {
      return (var0 * var2 + var1 * (255 - var2) + 127) / 255;
   }

   private static void finish(ConfigLibraryFactory2 var0,  String var1,  String var2, BiConsumer<String, String> var3) {
      class_310.method_1551().execute(() -> {
         IMPORTING.remove(var0.id);
         var3.accept(var1, var2);
      });
   }

   static void adopt(ConfigLibraryFactory2 var0, ConfigLibraryFactory2 var1) {
      String var2 = key(var0);
      if (var2 != null) {
         Path var3 = file(var2);
         String var4 = "/api/configs/" + var0.code + "/photo?v=" + var0.photo;
         IMPORTING.add(var1.id);
         IO.execute(() -> {
            try {
               byte[] var4x = Files.isRegularFile(var3) ? Files.readAllBytes(var3) : ConfigLibrarySupport3.bytes(var4).join();
               encode(var1, var4x, (var1xx, var2xx) -> {
                  if (var1xx != null) {
                     var1.photo = var1xx;
                     ConfigLibraryTransform.persist();
                  }
               });
            } catch (IOException | RuntimeException var5) {
               ClientServices.LOGGER.warn("Could not bring the photo of {} along: {}", var0.name, var5.toString());
               finish(var1, (String)null, (String)null, (var0xx, var1xx) -> {});
            }
         });
      }
   }

   private static String hash(byte[] var0) {
      try {
         return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(var0));
      } catch (NoSuchAlgorithmException var2) {
         throw new IllegalStateException(var2);
      }
   }

   static void tidy(List<ConfigLibraryFactory2> var0) {
      HashSet var1 = new HashSet();

      for (ConfigLibraryFactory2 var3 : var0) {
         if (var3.photo != null) {
            var1.add(var3.photo);
         }
      }

      IO.execute(() -> {
         long var1x = 86400000L;
         long var3x = System.currentTimeMillis();
         sweep(ConfigLibraryLoader.photos(), var1xx -> !var1.contains(var1xx), var3x - var1x);
         sweep(ConfigLibraryLoader.discover(), var0xx -> true, var3x - 14L * var1x);
      });
   }

   private static void sweep(Path var0, Predicate<String> var1, long var2) {
      if (Files.isDirectory(var0)) {
         try (Stream var4 = Files.list(var0)) {
            Iterator var5 = var4.iterator();

            while (var5.hasNext()) {
               Path var6 = (Path)var5.next();
               String var7 = var6.getFileName().toString();

               try {
                  if (Files.isRegularFile(var6) && var1.test(var7) && Files.getLastModifiedTime(var6).toMillis() < var2) {
                     Files.delete(var6);
                  }
               } catch (IOException var10) {
               }
            }
         } catch (IOException var12) {
         }
      }
   }

   static enum Mode {
      NONE,
      BOTTOM,
      LEFT;

      private static ConfigLibraryRenderer13.Mode[] $values() {
         return new ConfigLibraryRenderer13.Mode[]{NONE, BOTTOM, LEFT};
      }
   }

   static final class TypeQ {
      
      private TextureLoader texture;
      float aspect = 1.0F;
      float readyAt = Float.NaN;
      float usedAt;
      boolean uploaded;
   }
}
