// Sourced By DevFFNightmare
package dev.purxiy.render.font;

import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final class Fonts {
   public static final String PRELOAD = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~áàâäãåçéèêëíìîïñóòôöõúùûüýÿÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝ€£¥©®°±×÷·•–—…‘’“”«»→←↑↓";
   public static final GlyphAtlas ATLAS = new GlyphAtlas();
   private static final ExecutorService WORKERS = Executors.newFixedThreadPool(2, new Fonts.TypeO());
   public static TrueTypeFont REGULAR;
   public static TrueTypeFont MEDIUM;
   public static TrueTypeFont SEMIBOLD;
   public static TrueTypeFont BOLD;
   public static TrueTypeFont ICONS;
   private static boolean loaded;

   private Fonts() {
   }

   public static void load() {
      if (!loaded) {
         loaded = true;

         try {
            REGULAR = font("Inter Regular", "inter-regular.ttf");
            MEDIUM = font("Inter Medium", "inter-medium.ttf");
            SEMIBOLD = font("Inter SemiBold", "inter-semibold.ttf");
            BOLD = font("Inter Bold", "inter-bold.ttf");
            ICONS = font("Lucide", "lucide.ttf");
         } catch (IOException var4) {
            throw new IllegalStateException("Purxiy: failed to load fonts", var4);
         }

         for (TrueTypeFont var3 : new TrueTypeFont[]{MEDIUM, SEMIBOLD, REGULAR, BOLD}) {
            var3.preload(
               " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~áàâäãåçéèêëíìîïñóòôöõúùûüýÿÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝ€£¥©®°±×÷·•–—…‘’“”«»→←↑↓"
            );
         }

         IconCodepoints.load();
      }
   }

   private static TrueTypeFont font(String var0, String var1) throws IOException {
      return new TrueTypeFont(var0, "assets/purxiy/fonts/" + var1, ATLAS, WORKERS);
   }

   private static final class TypeO implements ThreadFactory {
      private final AtomicInteger counter = new AtomicInteger();

      @Override
      public Thread newThread(Runnable var1) {
         String var2 = "Purxiy Glyph Worker #" + this.counter.incrementAndGet();
         Thread var3 = AccessController.doPrivileged((PrivilegedAction<Thread>)(() -> new Thread((ThreadGroup)null, var1, var2, 0L, false)));
         var3.setContextClassLoader(Fonts.class.getClassLoader());
         var3.setDaemon(true);
         var3.setPriority(4);
         return var3;
      }
   }
}
