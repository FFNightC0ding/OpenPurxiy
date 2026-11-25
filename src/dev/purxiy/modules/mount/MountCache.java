package dev.purxiy.modules.mount;

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

final class MountCache {
   static final int D = 0;
   static final int V = 1;
   private static final int o = -2147417850;
   static final Memory r = guid("00000036-0000-0000-C000-000000000046");
   static final Memory L = guid("0000000C-0000-0000-C000-000000000046");
   private static final NativeLibrary i = NativeLibrary.getInstance("combase");
   private static final NativeLibrary U = NativeLibrary.getInstance("ole32");
   private static final NativeLibrary Q = NativeLibrary.getInstance("kernel32");
   private static final NativeLibrary d = NativeLibrary.getInstance("shcore");
   private static final Function Z = i.getFunction("RoInitialize", 63);
   private static final Function X = i.getFunction("RoGetActivationFactory", 63);
   private static final Function f = i.getFunction("WindowsCreateString", 63);
   private static final Function S = i.getFunction("WindowsDeleteString", 63);
   private static final Function g = i.getFunction("WindowsGetStringRawBuffer", 63);
   private static final Function P = U.getFunction("CoCreateInstance", 63);
   private static final Function a = U.getFunction("CoTaskMemFree", 63);
   private static final Function z = Q.getFunction("GetSystemTimePreciseAsFileTime", 63);
   private static final Function M = d.getFunction("CreateStreamOverRandomAccessStream", 63);
   private static final ThreadLocal<Boolean> c = ThreadLocal.withInitial(() -> false);
   private static final long k = 1000000L;

   private MountCache() {
   }

   static void initThread() throws MountCache.MountException {
      if (!c.get()) {
         int var0 = Z.invokeInt(new Object[]{1});
         if (var0 >= 0) {
            c.set(true);
         }

         if (var0 < 0 && var0 != -2147417850) {
            throw new MountCache.MountException("RoInitialize", var0);
         }
      }
   }

   static Pointer activationFactory(String var0, Memory var1) throws MountCache.MountException {
      long var2 = 75116321839446L;
      long var4 = var2 ^ 77422361709953L;
      Pointer var6 = hstring(var0);

      Pointer var8;
      try {
         PointerByReference var7 = new PointerByReference();
         check(X.invokeInt(new Object[]{var6, var1, var7}), "RoGetActivationFactory " + var0);
         var8 = b(var4, var7.getValue(), "RoGetActivationFactory " + var0);
      } finally {
         deleteString(var6);
      }

      return var8;
   }

   static Pointer createInstance(Memory var0, Memory var1) throws MountCache.MountException {
      long var2 = 44627546708368L;
      long var4 = var2 ^ 46924747083079L;
      PointerByReference var6 = new PointerByReference();
      check(P.invokeInt(new Object[]{var0, null, 23, var1, var6}), "CoCreateInstance");
      return b(var4, var6.getValue(), "CoCreateInstance");
   }

   static int call(Pointer var0, int var1, Object... var2) {
      Pointer var3 = var0.getPointer(0L).getPointer((long)var1 * Native.POINTER_SIZE);
      Object[] var4 = new Object[var2.length + 1];
      var4[0] = var0;
      System.arraycopy(var2, 0, var4, 1, var2.length);
      return Function.getFunction(var3, 63).invokeInt(var4);
   }

   static void check(int var0, String var1) throws MountCache.MountException {
      if (var0 < 0) {
         throw new MountCache.MountException(var1, var0);
      }
   }

   private static Pointer b(long var0, Pointer var2, String var3) throws MountCache.MountException {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      if (var2 == null) {
         throw new MountCache.MountException(var3 + " returned null", -2147467261);
      } else {
         return var2;
      }
   }

   static void release(Pointer var0) {
      if (var0 != null) {
         call(var0, 2);
      }
   }

   static Pointer queryInterface(Pointer var0, Memory var1) throws MountCache.MountException {
      long var2 = 139959179519709L;
      long var4 = var2 ^ 137928124510730L;
      PointerByReference var6 = new PointerByReference();
      check(call(var0, 0, var1, var6), "QueryInterface");
      return b(var4, var6.getValue(), "QueryInterface");
   }

   static Pointer getObject(Pointer var0, int var1, String var2) throws MountCache.MountException {
      PointerByReference var3 = new PointerByReference();
      check(call(var0, var1, var3), var2);
      return var3.getValue();
   }

   static Pointer getRequired(Pointer var0, int var1, String var2) throws MountCache.MountException {
      long var3 = 16341524063398L;
      long var5 = var3 ^ 14190090432625L;
      return b(var5, getObject(var0, var1, var2), var2);
   }

   static int getInt(Pointer var0, int var1, String var2) throws MountCache.MountException {
      IntByReference var3 = new IntByReference();
      check(call(var0, var1, var3), var2);
      return var3.getValue();
   }

   static long getLong(Pointer var0, int var1, String var2) throws MountCache.MountException {
      LongByReference var3 = new LongByReference();
      check(call(var0, var1, var3), var2);
      return var3.getValue();
   }

   static boolean getBoolean(Pointer var0, int var1, String var2) throws MountCache.MountException {
      ByteByReference var3 = new ByteByReference();
      check(call(var0, var1, var3), var2);
      return var3.getValue() != 0;
   }

   static String getString(Pointer var0, int var1, String var2) throws MountCache.MountException {
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

   static int getReferenceInt(Pointer var0, int var1, String var2, int var3) throws MountCache.MountException {
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

   static int getReferenceBoolean(Pointer var0, int var1, String var2) throws MountCache.MountException {
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

   static Pointer awaitObject(Pointer var0, long var1, String var3) throws MountCache.MountException {
      long var4 = 14974051155073L;
      long var6 = var4 ^ 128954640320240L;

      Pointer var9;
      try {
         W(var6, var0, var1, var3);
         PointerByReference var8 = new PointerByReference();
         check(call(var0, 8, var8), var3 + " results");
         var9 = var8.getValue();
      } finally {
         release(var0);
      }

      return var9;
   }

   static boolean awaitBoolean(Pointer var0, long var1, String var3) throws MountCache.MountException {
      long var4 = 127527076464890L;
      long var6 = var4 ^ 12278429594251L;

      boolean var9;
      try {
         W(var6, var0, var1, var3);
         ByteByReference var8 = new ByteByReference();
         check(call(var0, 8, var8), var3 + " results");
         var9 = var8.getValue() != 0;
      } finally {
         release(var0);
      }

      return var9;
   }

   private static void W(long var0, Pointer var2, long var3, String var5) throws MountCache.MountException {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      Pointer var6 = queryInterface(var2, r);
      boolean var7 = false;

      try {
         var7 = true;
         long var8 = System.nanoTime() + var3 * 1000000L;
         IntByReference var10 = new IntByReference();
         int var11 = 0;

         while (true) {
            check(call(var6, 7, var10), var5 + " status");
            int var12 = var10.getValue();
            if (var12 == 1) {
               var7 = false;
               break;
            }

            long var13 = 132864770882160L;
            int var32 = var13 < 0L ? -1 : 1;
            if (var12 != 2) {
               long var16 = 132864770882161L;
               int var18 = var16 < 0L ? -1 : 1;
               if (var12 != 3) {
                  long var18x;
                  int var20 = (var18x = System.nanoTime() - var8) == 0L ? 0 : (var18x < 0L ? -1 : 1);
                  long var21 = 132864770882160L;
                  int var28 = var21 < 0L ? -1 : 1;
                  if (var20 > 0) {
                     call(var6, 9);
                     throw new MountCache.MountException(var5 + " (timed out)", -2147023436);
                  }

                  if (var11 < 20) {
                     Thread.onSpinWait();
                     long var23 = 132864770882161L;
                     int var24 = var23 < 0L ? -1 : 1;
                  } else {
                     try {
                        Thread.sleep(1L);
                     } catch (InterruptedException var281) {
                        Thread.currentThread().interrupt();
                        call(var6, 9);
                        throw new MountCache.MountException(var5 + " (interrupted)", -2147023673);
                     }
                  }

                  var11++;
                  continue;
               }
            }

            IntByReference var32x = new IntByReference();
            call(var6, 8, var32x);
            throw new MountCache.MountException(var5, var12 == 2 ? -2147023673 : var32x.getValue());
         }
      } finally {
         if (var7) {
            release(var6);
         }
      }

      long var30 = 132864770882161L;
      int var311 = var30 < 0L ? -1 : 1;
      long var31x = 132864770882161L;
      int var33 = var31x < 0L ? -1 : 1;
      release(var6);
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

   static Pointer hstring(String var0) throws MountCache.MountException {
      char[] var1 = var0.toCharArray();
      Memory var2 = new Memory((var1.length + 1) * 2L);
      var2.write(0L, var1, 0, var1.length);
      var2.setChar(var1.length * 2L, '\u0000');
      PointerByReference var3 = new PointerByReference();
      check(f.invokeInt(new Object[]{var2, var1.length, var3}), "WindowsCreateString");
      return var3.getValue();
   }

   static String string(Pointer var0) {
      if (var0 == null) {
         return "";
      } else {
         IntByReference var1 = new IntByReference();
         Pointer var2 = g.invokePointer(new Object[]{var0, var1});
         return var2 != null && var1.getValue() > 0 ? new String(var2.getCharArray(0L, var1.getValue())) : "";
      }
   }

   static void deleteString(Pointer var0) {
      if (var0 != null) {
         S.invokeInt(new Object[]{var0});
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
            a.invoke(new Object[]{var0});
         }

         return var1;
      }
   }

   static long preciseFileTime() {
      LongByReference var0 = new LongByReference();
      z.invoke(new Object[]{var0});
      return var0.getValue();
   }

   static Pointer streamOver(Pointer var0) throws MountCache.MountException {
      long var1 = 1884265626598L;
      long var3 = var1 ^ 3906726506289L;
      PointerByReference var5 = new PointerByReference();
      check(M.invokeInt(new Object[]{var0, L, var5}), "CreateStreamOverRandomAccessStream");
      return b(var3, var5.getValue(), "CreateStreamOverRandomAccessStream");
   }

   static void endThread() {
      if (c.get()) {
         i.getFunction("RoUninitialize", 63).invokeVoid(new Object[0]);
         c.remove();
      }
   }

   static final class MountException extends Exception {
      final int v;
      private static final String a = "%s failed: 0x%08X";

      MountException(String var1, int var2) {
         super(String.format(Locale.ROOT, "%s failed: 0x%08X", var1, var2));
         this.v = var2;
      }
   }
}
