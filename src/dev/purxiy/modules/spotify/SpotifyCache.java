package dev.purxiy.modules.spotify;

import com.sun.jna.Function;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import com.sun.jna.ptr.PointerByReference;
import java.util.Locale;

final class SpotifyCache {
   static final int q = 0;
   static final int p = 1;
   private static final int G = -2147417850;
   static final Memory f = guid("00000036-0000-0000-C000-000000000046");
   static final Memory Y = guid("0000000C-0000-0000-C000-000000000046");
   private static final NativeLibrary T = NativeLibrary.getInstance("combase");
   private static final NativeLibrary n = NativeLibrary.getInstance("ole32");
   private static final NativeLibrary x = NativeLibrary.getInstance("kernel32");
   private static final NativeLibrary M = NativeLibrary.getInstance("shcore");
   private static final Function w = T.getFunction("RoInitialize", 63);
   private static final Function O = T.getFunction("RoGetActivationFactory", 63);
   private static final Function c = T.getFunction("WindowsCreateString", 63);
   private static final Function X = T.getFunction("WindowsDeleteString", 63);
   private static final Function N = T.getFunction("WindowsGetStringRawBuffer", 63);
   private static final Function F = n.getFunction("CoCreateInstance", 63);
   private static final Function k = n.getFunction("CoTaskMemFree", 63);
   private static final Function d = x.getFunction("GetSystemTimePreciseAsFileTime", 63);
   private static final Function z = M.getFunction("CreateStreamOverRandomAccessStream", 63);
   private static final ThreadLocal<Boolean> l = ThreadLocal.withInitial(() -> false);
   private static final long[] e = new long[]{
      3877113514484275883L,
      -2328042950910264195L,
      2565500301910952201L,
      -6161933404757120124L,
      -9206801688288670229L,
      3917141995655544010L,
      -1648311626226607190L,
      -3857913312722253611L,
      4940541282678061637L,
      7475523163306443027L
   };
   private static final long h = 1000000L;

   private SpotifyCache() {
   }

   static void initThread() throws SpotifyCache.SpotifyException {
      if (!l.get()) {
         int var0 = w.invokeInt(new Object[]{1});
         if (var0 >= 0) {
            l.set(true);
         }

         if (var0 < 0 && var0 != -2147417850) {
            throw new SpotifyCache.SpotifyException("RoInitialize", var0);
         }
      }
   }

   static Pointer activationFactory(String var0, Memory var1) throws SpotifyCache.SpotifyException {
      long var2 = 83727890251605L;
      long var4 = var2 ^ 62357282451466L;
      Pointer var6 = hstring(var0);

      Pointer var8;
      try {
         PointerByReference var7 = new PointerByReference();
         check(O.invokeInt(new Object[]{var6, var1, var7}), "RoGetActivationFactory " + var0);
         var8 = V(var4, var7.getValue(), "RoGetActivationFactory " + var0);
      } finally {
         deleteString(var6);
      }

      return var8;
   }

   static Pointer createInstance(Memory var0, Memory var1) throws SpotifyCache.SpotifyException {
      long var2 = 17168897466082L;
      long var4 = var2 ^ 135298826916285L;
      PointerByReference var6 = new PointerByReference();
      check(F.invokeInt(new Object[]{var0, null, 23, var1, var6}), "CoCreateInstance");
      return V(var4, var6.getValue(), "CoCreateInstance");
   }

   static int call(Pointer var0, int var1, Object... var2) {
      Pointer var3 = var0.getPointer(0L).getPointer((long)var1 * Native.POINTER_SIZE);
      Object[] var4 = new Object[var2.length + 1];
      var4[0] = var0;
      System.arraycopy(var2, 0, var4, 1, var2.length);
      return Function.getFunction(var3, 63).invokeInt(var4);
   }

   static void check(int var0, String var1) throws SpotifyCache.SpotifyException {
      if (var0 < 0) {
         throw new SpotifyCache.SpotifyException(var1, var0);
      }
   }

   private static Pointer V(long var0, Pointer var2, String var3) throws SpotifyCache.SpotifyException {
      SpotifyCurve.ensureSourceClassInitialized();
      if (var2 == null) {
         throw new SpotifyCache.SpotifyException(var3 + " returned null", -2147467261);
      } else {
         return var2;
      }
   }

   static void release(Pointer var0) {
      if (var0 != null) {
         call(var0, 2);
      }
   }

   static Pointer queryInterface(Pointer var0, Memory var1) throws SpotifyCache.SpotifyException {
      long var2 = 88427714463871L;
      long var4 = var2 ^ 40666529711904L;
      PointerByReference var6 = new PointerByReference();
      check(call(var0, 0, var1, var6), "QueryInterface");
      return V(var4, var6.getValue(), "QueryInterface");
   }

   static Pointer getObject(Pointer var0, int var1, String var2) throws SpotifyCache.SpotifyException {
      PointerByReference var3 = new PointerByReference();
      check(call(var0, var1, var3), var2);
      return var3.getValue();
   }

   static Pointer getRequired(Pointer var0, int var1, String var2) throws SpotifyCache.SpotifyException {
      long var3 = 140154888137758L;
      long var5 = var3 ^ 13093725709121L;
      return V(var5, getObject(var0, var1, var2), var2);
   }

   static int getInt(Pointer var0, int var1, String var2) throws SpotifyCache.SpotifyException {
      IntByReference var3 = new IntByReference();
      check(call(var0, var1, var3), var2);
      return var3.getValue();
   }

   static long getLong(Pointer var0, int var1, String var2) throws SpotifyCache.SpotifyException {
      LongByReference var3 = new LongByReference();
      check(call(var0, var1, var3), var2);
      return var3.getValue();
   }

   static boolean getBoolean(Pointer var0, int var1, String var2) throws SpotifyCache.SpotifyException {
      ByteByReference var3 = new ByteByReference();
      check(call(var0, var1, var3), var2);
      return var3.getValue() != 0;
   }

   static String getString(Pointer var0, int var1, String var2) throws SpotifyCache.SpotifyException {
      PointerByReference var3 = new PointerByReference();
      check(call(var0, var1, var3), var2);
      Pointer var4 = var3.getValue();

      String var5;
      try {
         var5 = string(var4);
      } finally {
         deleteString(var4);
      }

      return var5;
   }

   static int getReferenceInt(Pointer var0, int var1, String var2, int var3) throws SpotifyCache.SpotifyException {
      Pointer var4 = getObject(var0, var1, var2);
      if (var4 == null) {
         return var3;
      } else {
         int var5;
         try {
            var5 = getInt(var4, 6, var2 + ".Value");
         } finally {
            release(var4);
         }

         return var5;
      }
   }

   static int getReferenceBoolean(Pointer var0, int var1, String var2) throws SpotifyCache.SpotifyException {
      Pointer var3 = getObject(var0, var1, var2);
      if (var3 == null) {
         return -1;
      } else {
         int var4;
         try {
            var4 = getBoolean(var3, 6, var2 + ".Value") ? 1 : 0;
         } finally {
            release(var3);
         }

         return var4;
      }
   }

   static Pointer awaitObject(Pointer var0, long var1, String var3) throws SpotifyCache.SpotifyException {
      long var4 = 110354695409462L;
      long var6 = var4 ^ 50879733598176L;
      int var8 = (int)((var4 ^ 50879733598176L) >>> 48);
      int var9 = (int)((var4 ^ 50879733598176L) << 16 >>> 48);
      int var10 = (int)(var6 << 32 >>> 32);

      Pointer var12;
      try {
         e((short)var8, var0, var1, (short)var9, var3, var10);
         PointerByReference var11 = new PointerByReference();
         check(call(var0, 8, var11), var3 + " results");
         var12 = var11.getValue();
      } finally {
         release(var0);
      }

      return var12;
   }

   static boolean awaitBoolean(Pointer var0, long var1, String var3) throws SpotifyCache.SpotifyException {
      long var4 = 23324362644592L;
      long var6 = var4 ^ 104647016600742L;
      int var8 = (int)((var4 ^ 104647016600742L) >>> 48);
      int var9 = (int)((var4 ^ 104647016600742L) << 16 >>> 48);
      int var10 = (int)(var6 << 32 >>> 32);

      boolean var12;
      try {
         e((short)var8, var0, var1, (short)var9, var3, var10);
         ByteByReference var11 = new ByteByReference();
         check(call(var0, 8, var11), var3 + " results");
         var12 = var11.getValue() != 0;
      } finally {
         release(var0);
      }

      return var12;
   }

   private static void e(short var0, Pointer var1, long var2, short var4, String var5, int var6) throws SpotifyCache.SpotifyException {
      SpotifyCurve.ensureSourceClassInitialized();
      Pointer var7 = queryInterface(var1, f);
      boolean var8 = false;

      try {
         var8 = true;
         long var9 = System.nanoTime() + var2 * 1000000L;
         IntByReference var11 = new IntByReference();
         int var12 = 0;

         while (true) {
            check(call(var7, 7, var11), var5 + " status");
            int var13 = var11.getValue();
            if (var13 == 1) {
               var8 = false;
               break;
            }

            if (var13 != 2) {
               short var14 = 0;
               if (var13 != 3) {
                  long var15;
                  int var17 = (var15 = System.nanoTime() - var9) == 0L ? 0 : (var15 < 0L ? -1 : 1);
                  var14 = 18971;
                  if (var17 > 0) {
                     call(var7, 9);
                     throw new SpotifyCache.SpotifyException(var5 + " (timed out)", -2147023436);
                  }

                  if (var12 < 20) {
                     Thread.onSpinWait();
                  } else {
                     try {
                        Thread.sleep(1L);
                     } catch (InterruptedException var22) {
                        Thread.currentThread().interrupt();
                        call(var7, 9);
                        throw new SpotifyCache.SpotifyException(var5 + " (interrupted)", -2147023673);
                     }
                  }

                  var12++;
                  continue;
               }
            }

            IntByReference var24 = new IntByReference();
            call(var7, 8, var24);
            throw new SpotifyCache.SpotifyException(var5, var13 == 2 ? -2147023673 : var24.getValue());
         }
      } finally {
         if (var8) {
            release(var7);
         }
      }

      release(var7);
   }

   static Memory guid(String var0) {
      String var1 = var0.replace("-", "");
      Memory var2 = new Memory(16L);
      var2.setInt(0L, (int)Long.parseLong(var1.substring(0, 8), 16));
      var2.setShort(4L, (short)Integer.parseInt(var1.substring(8, 12), 16));
      var2.setShort(6L, (short)Integer.parseInt(var1.substring(12, 16), 16));

      for (int var3 = 0; var3 < 8; var3++) {
         var2.setByte(8 + var3, (byte)Integer.parseInt(var1.substring(16 + var3 * 2, 18 + var3 * 2), 16));
      }

      return var2;
   }

   static Pointer hstring(String var0) throws SpotifyCache.SpotifyException {
      char[] var1 = var0.toCharArray();
      Memory var2 = new Memory((var1.length + 1) * 2L);
      var2.write(0L, var1, 0, var1.length);
      var2.setChar(var1.length * 2L, '\u0000');
      PointerByReference var3 = new PointerByReference();
      check(c.invokeInt(new Object[]{var2, var1.length, var3}), "WindowsCreateString");
      return var3.getValue();
   }

   static String string(Pointer var0) {
      if (var0 == null) {
         return "";
      } else {
         IntByReference var1 = new IntByReference();
         Pointer var2 = N.invokePointer(new Object[]{var0, var1});
         return var2 != null && var1.getValue() > 0 ? new String(var2.getCharArray(0L, var1.getValue())) : "";
      }
   }

   static void deleteString(Pointer var0) {
      if (var0 != null) {
         X.invokeInt(new Object[]{var0});
      }
   }

   static String taskString(Pointer var0) {
      if (var0 == null) {
         return "";
      } else {
         String var1;
         try {
            var1 = var0.getWideString(0L);
         } finally {
            k.invoke(new Object[]{var0});
         }

         return var1;
      }
   }

   static long preciseFileTime() {
      LongByReference var0 = new LongByReference();
      d.invoke(new Object[]{var0});
      return var0.getValue();
   }

   static Pointer streamOver(Pointer var0) throws SpotifyCache.SpotifyException {
      long var1 = 119048525137596L;
      long var3 = var1 ^ 27311185447395L;
      PointerByReference var5 = new PointerByReference();
      check(z.invokeInt(new Object[]{var0, Y, var5}), "CreateStreamOverRandomAccessStream");
      return V(var3, var5.getValue(), "CreateStreamOverRandomAccessStream");
   }

   static void endThread() {
      if (l.get()) {
         T.getFunction("RoUninitialize", 63).invokeVoid(new Object[0]);
         l.remove();
      }
   }

   static final class SpotifyException extends Exception {
      final int d;
      private static final String a = "%s failed: 0x%08X";

      SpotifyException(String var1, int var2) {
         super(String.format(Locale.ROOT, "%s failed: 0x%08X", var1, var2));
         this.d = var2;
      }
   }
}
