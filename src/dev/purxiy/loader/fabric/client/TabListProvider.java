package dev.purxiy.loader.fabric.client;

import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.class_2583;
import net.minecraft.class_310;
import net.minecraft.class_5223;
import net.minecraft.class_5224;
import net.minecraft.class_5348;
import net.minecraft.class_5481;
import net.minecraft.class_9296;
import net.minecraft.class_5225.class_5231;
import net.minecraft.class_5348.class_5245;
import net.minecraft.class_5348.class_5246;

public final class TabListProvider {
   private static final ReentrantReadWriteLock lifetime = new ReentrantReadWriteLock();
   private static TabListProvider.PlayerListBridge handler;
   private static int epoch = 0;
   private static long sequence = 0L;

   private TabListProvider() {
   }

   public static AutoCloseable install(TabListProvider.PlayerListBridge var0) {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Nick owner thread required");
      } else {
         WriteLock var1 = lifetime.writeLock();
         var1.lock();

         AutoCloseable var2;
         try {
            if (handler != null) {
               throw new IllegalStateException("Nick contract already owned");
            }

            handler = Objects.requireNonNull(var0);
            epoch += 1000003;
            long var3 = ++sequence;
            var2 = () -> {
               if (!class_310.method_1551().method_18854()) {
                  throw new IllegalStateException("Nick owner thread required");
               } else {
                  WriteLock var2x = lifetime.writeLock();
                  var2x.lock();

                  try {
                     if (sequence == var3) {
                        handler = null;
                        epoch += 1000003;
                     }
                  } finally {
                     var2x.unlock();
                  }
               }
            };
         } finally {
            var1.unlock();
         }

         return var2;
      }
   }

   private static <T> T call(Function<TabListProvider.PlayerListBridge, T> var0, Supplier<T> var1) {
      ReadLock var2 = lifetime.readLock();
      var2.lock();

      Object var3;
      try {
         var3 = handler == null ? var1.get() : var0.apply(handler);
      } catch (AssertionError | Exception | LinkageError var8) {
         throw new IllegalStateException("Nickname presentation failed");
      } finally {
         var2.unlock();
      }

      return (T)var3;
   }

   public static boolean active() {
      return call(TabListProvider.PlayerListBridge::active, () -> false);
   }

   public static int generation() {
      return call(var0 -> epoch + var0.generation(), () -> epoch);
   }

   public static void meet(GameProfile var0) {
      call(var1 -> {
         var1.meet(var0);
         return null;
      }, () -> null);
   }

   public static void playersChanged() {
      call(var0 -> {
         var0.playersChanged();
         return null;
      }, () -> null);
   }

   public static List<String> knownNames() {
      return call(var0 -> List.copyOf(var0.knownNames()), List::of);
   }

   public static boolean hideSkin(GameProfile var0) {
      return call(var1 -> var1.hideSkin(var0), () -> false);
   }

   public static boolean hideSkin(class_9296 var0) {
      return call(var1 -> var1.hideSkin(var0), () -> false);
   }

   public static class_5481 sequence(class_5481 var0) {
      return (class_5481)(!(var0 instanceof TabListProvider.Listener) && active() ? new TabListProvider.OrderedTextWrapper(var0) : var0);
   }

   public static class_5348 text(class_5348 var0) {
      return var0 instanceof TabListProvider.Listener ? var0 : call(var1 -> new TabListProvider.TextWrapper(var1.text(var0)), () -> var0);
   }

   public static float width(String var0, class_5231 var1) {
      return call(var2 -> var2.width(var0, var1), () -> rawWidth(class_5348.method_29430(var0), var1));
   }

   public static float width(class_5348 var0, class_5231 var1) {
      return call(var2 -> var2.width(var0, var1), () -> rawWidth(var0, var1));
   }

   private static float rawWidth(class_5348 var0, class_5231 var1) {
      float[] var2 = new float[]{0.0F};
      class_5223.method_27476(var0, class_2583.field_24360, (var2x, var3, var4) -> {
         var2[0] += var1.getWidth(var4, var3);
         return true;
      });
      return var2[0];
   }

   public static boolean iterate(String var0, class_2583 var1, class_5224 var2) {
      return call(var3 -> var3.iterate(var0, var1, var2), () -> class_5223.method_27479(var0, var1, var2));
   }

   public static String plain(String var0) {
      return call(var1 -> var1.plain(var0), () -> var0);
   }

   public static class_5481 snapshot(class_5481 var0) {
      ArrayList var1 = new ArrayList();
      var0.accept((var1x, var2, var3) -> {
         if (var1.size() >= 32768) {
            throw new IllegalArgumentException("Nickname text is too long");
         } else {
            var1.add(new TabListProvider.Glyph(var1x, var2, var3));
            return true;
         }
      });
      return new TabListProvider.GlyphList(List.copyOf(var1));
   }

   public interface Listener {
   }

   public interface PlayerListBridge {
      boolean active();

      int generation();

      void meet(GameProfile var1);

      void playersChanged();

      List<String> knownNames();

      boolean hideSkin(GameProfile var1);

      boolean hideSkin(class_9296 var1);

      boolean accept(class_5481 var1, class_5224 var2);

      class_5348 text(class_5348 var1);

      float width(String var1, class_5231 var2);

      float width(class_5348 var1, class_5231 var2);

      boolean iterate(String var1, class_2583 var2, class_5224 var3);

      String plain(String var1);

      class_5481 segment(TabListProvider.SuggestionView var1, class_5481 var2, int var3);
   }

   private record GlyphList(List<TabListProvider.Glyph> glyphs) implements class_5481, TabListProvider.Listener {
      @Override
      public boolean accept(class_5224 var1) {
         for (TabListProvider.Glyph var3 : this.glyphs) {
            if (!var1.accept(var3.index, var3.style, var3.cp)) {
               return false;
            }
         }

         return true;
      }
   }

   private record TextWrapper(class_5348 original) implements class_5348, TabListProvider.Listener {
      @Override
      public <T> Optional<T> method_27657(class_5245<T> var1) {
         return this.original.method_27657(var1);
      }

      @Override
      public <T> Optional<T> method_27658(class_5246<T> var1, class_2583 var2) {
         return this.original.method_27658(var1, var2);
      }
   }

   private record OrderedTextWrapper(class_5481 original) implements class_5481, TabListProvider.Listener {
      @Override
      public boolean accept(class_5224 var1) {
         return TabListProvider.call(var2 -> var2.accept(this.original, var1), () -> this.original.accept(var1));
      }
   }

   public static final class SuggestionView {
      private final String value;
      private final String suggestion;

      private SuggestionView(String var1, String var2) {
         this.value = var1;
         this.suggestion = var2;
      }

      public String value() {
         return this.value;
      }

      public String suggestion() {
         return this.suggestion;
      }

      public static TabListProvider.SuggestionView of(String var0, String var1) {
         return TabListProvider.active() ? new TabListProvider.SuggestionView(var0, var1) : null;
      }

      public class_5481 segment(class_5481 var1, int var2) {
         return TabListProvider.call(var3 -> var3.segment(this, var1, var2), () -> var1);
      }

      public class_5481 segment(String var1, int var2) {
         return this.segment(class_5481.method_30747(var1, class_2583.field_24360), var2);
      }
   }

   private record Glyph(int index, class_2583 style, int cp) {
   }
}
