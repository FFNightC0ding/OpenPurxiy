// Sourced By DevFFNightmare
package dev.purxiy.modules.spotify;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

final class SpotifySupport8 {
   private static final Memory B = SpotifyCache.guid("BCDE0395-E52F-467C-8E3D-C4579291692E");
   private static final Memory P = SpotifyCache.guid("A95664D2-9614-4F35-A746-DE8DB63617E6");
   private static final Memory w = SpotifyCache.guid("77AA99A0-1BD6-484F-8BC7-2C654C9A9B6F");
   private static final Memory N = SpotifyCache.guid("BFB7FF88-7239-4FC9-8FA2-07C950BE9C6D");
   private static final Memory J = SpotifyCache.guid("87CE5498-68D6-44E5-9215-6DA47EF883D8");
   private static final Memory L = SpotifyCache.guid("C02216F6-8C67-4B5B-9D00-D008E73E0064");
   private static final int H = 0;
   private static final int e = 1;
   private static final int t = 2;
   
   private Pointer s;
   private final List<SpotifySupport8.TypeH> K = new ArrayList<>();




   private static final long f = 4294967295L;

   boolean open() {
      return this.s != null;
   }

   void start() throws SpotifyCache.TypeB {
      SpotifyCache.initThread();
      this.s = SpotifyCache.createInstance(B, P);
   }

   void close() {
      long var1 = 78329742193013L;
      long var3 = var1 ^ 101349591351121L;
      this.o(var3);
      SpotifyCache.release(this.s);
      this.s = null;
   }

   boolean found() {
      return !this.K.isEmpty();
   }

   private void o(long var1) {
      SpotifyCurve.ensureSourceClassInitialized();

      for (SpotifySupport8.TypeH var4 : this.K) {
         long var5 = 29759192337955L;
         if (29759192337955L == 0L) {
            boolean var10000 = false;
         } else {
            int var9 = var5 < 0L ? -1 : 1;
         }

         SpotifyCache.release(var4.meter());
         SpotifyCache.release(var4.volume());
         SpotifyCache.release(var4.control());
      }

      long var8 = 29759192337955L;
      if (29759192337955L == 0L) {
         boolean var10 = false;
      } else {
         int var11 = var8 < 0L ? -1 : 1;
      }

      this.K.clear();
   }

   void find(Set<String> var1) throws SpotifyCache.TypeB {
      long var2 = 54845714840850L;
      long var4 = var2 ^ 69308053302940L;
      long var6 = var2 ^ 47215000105782L;
      this.o(var6);
      Pointer var8 = this.s;
      if (var8 != null && !var1.isEmpty()) {
         PointerByReference var9 = new PointerByReference();
         SpotifyCache.check(SpotifyCache.call(var8, 3, 0, 1, var9), "EnumAudioEndpoints");
         Pointer var10 = var9.getValue();
         if (var10 != null) {
            try {
               int var11 = SpotifyCache.getInt(var10, 3, "Devices.Count");

               for (int var12 = 0; var12 < var11; var12++) {
                  PointerByReference var13 = new PointerByReference();
                  if (SpotifyCache.call(var10, 4, var12, var13) >= 0 && var13.getValue() != null) {
                     try {
                        this.N(var4, var13.getValue(), var1);
                     } finally {
                        SpotifyCache.release(var13.getValue());
                     }
                  }
               }
            } finally {
               SpotifyCache.release(var10);
            }
         }
      }
   }

   private void N(long var1, Pointer var3, Set var4) throws SpotifyCache.TypeB {
      byte var5 = 0;
      long var6 = 99774104508290L;
      SpotifyCurve.ensureSourceClassInitialized();
      PointerByReference var8 = new PointerByReference();
      if (SpotifyCache.call(var3, 3, w, 23, null, var8) >= 0 && var8.getValue() != null) {
         Pointer var9 = var8.getValue();

         try {
            Pointer var10 = SpotifyCache.getObject(var9, 5, "GetSessionEnumerator");
            long var11 = 16392996524941L;
            if (16392996524941L == 0L) {
               boolean var10000 = false;
            } else {
               int var39 = var11 < 0L ? -1 : 1;
            }

            if (var10 == null) {
               return;
            }

            boolean var14 = false;

            try {
               var14 = true;
               int var15 = SpotifyCache.getInt(var10, 3, "Sessions.Count");

               for (int var16 = 0; var16 < var15; var16++) {
                  PointerByReference var17 = new PointerByReference();
                  long var18 = 16392996524941L;
                  if (16392996524941L == 0L) {
                     boolean var40 = false;
                  } else {
                     int var41 = var18 < 0L ? -1 : 1;
                  }

                  long var21 = 16392996524942L;
                  if (16392996524942L == 0L) {
                     boolean var42 = false;
                  } else {
                     int var43 = var21 < 0L ? -1 : 1;
                  }

                  if (SpotifyCache.call(var10, 4, var16, var17) >= 0 && var17.getValue() != null) {
                     try {
                        this.T(var5, var6, var17.getValue(), var4);
                     } finally {
                        SpotifyCache.release(var17.getValue());
                     }
                  }
               }

               var14 = false;
            } finally {
               if (var14) {
                  SpotifyCache.release(var10);
               }
            }

            long var38 = 16392996524941L;
            if (16392996524941L == 0L) {
               boolean var44 = false;
            } else {
               int var45 = var38 < 0L ? -1 : 1;
            }

            SpotifyCache.release(var10);
         } finally {
            SpotifyCache.release(var9);
         }
      }
   }

   private void T(short var1, long var2, Pointer var4, Set var5) throws SpotifyCache.TypeB {
      long var6 = 99774104508290L;
      long var8 = var6 ^ 85145095390529L;
      SpotifyCurve.ensureSourceClassInitialized();
      Pointer var10 = SpotifyCache.queryInterface(var4, N);
      boolean var11 = false;
      boolean var12 = false;

      label103: {
         try {
            var12 = true;
            int var13 = SpotifyCache.getInt(var10, 3, "Session.State");
            long var14 = 99774104508289L;
            if (99774104508289L == 0L) {
               boolean var10000 = false;
            } else {
               int var25 = var14 < 0L ? -1 : 1;
            }

            if (var13 == 2) {
               var12 = false;
               break label103;
            }

            if (!var5.contains(A(var8, var10))) {
               var12 = false;
               break label103;
            }

            Pointer var17 = SpotifyCache.queryInterface(var10, J);
            Pointer var18 = null;

            try {
               var18 = SpotifyCache.queryInterface(var10, L);
            } catch (SpotifyCache.TypeB var23) {
            }

            this.K.add(new SpotifySupport8.TypeH(var10, var17, var18));
            var11 = true;
            var12 = false;
         } finally {
            if (var12 && !var11) {
               SpotifyCache.release(var10);
            }
         }

         return;
      }

      SpotifyCache.release(var10);
   }

   private static String A(long var0, Pointer var2) {
      SpotifyCurve.ensureSourceClassInitialized();
      PointerByReference var3 = new PointerByReference();
      if (SpotifyCache.call(var2, 12, var3) >= 0) {
         String var4 = SpotifyCache.taskString(var3.getValue()).toLowerCase(Locale.ROOT);
         int var5 = var4.lastIndexOf(".exe");
         if (var5 > 0) {
            int var6 = Math.max(var4.lastIndexOf(92, var5), var4.lastIndexOf(124, var5)) + 1;
            return var4.substring(var6, var5 + 4);
         }
      }

      IntByReference var7 = new IntByReference();
      return SpotifyCache.call(var2, 14, var7) >= 0 && var7.getValue() != 0
         ? ProcessHandle.of(var7.getValue() & 4294967295L)
            .flatMap(var0x -> var0x.info().command())
            .map(var0x -> var0x.substring(Math.max(var0x.lastIndexOf(92), var0x.lastIndexOf(47)) + 1).toLowerCase(Locale.ROOT))
            .orElse("")
         : "";
   }

   boolean alive() throws SpotifyCache.TypeB {
      for (SpotifySupport8.TypeH var2 : this.K) {
         if (SpotifyCache.getInt(var2.control(), 3, "Session.State") == 2) {
            return false;
         }
      }

      return true;
   }

   float volume() throws SpotifyCache.TypeB {
      if (this.K.isEmpty()) {
         return -1.0F;
      } else {
         FloatByReference var1 = new FloatByReference();
         SpotifyCache.check(SpotifyCache.call(this.K.getFirst().volume(), 4, var1), "GetMasterVolume");
         return var1.getValue();
      }
   }

   boolean muted() throws SpotifyCache.TypeB {
      return this.K.isEmpty() ? false : SpotifyCache.getInt(this.K.getFirst().volume(), 6, "GetMute") != 0;
   }

   void setVolume(float var1) throws SpotifyCache.TypeB {
      float var2 = Math.max(0.0F, Math.min(1.0F, var1));

      for (SpotifySupport8.TypeH var4 : this.K) {
         SpotifyCache.check(SpotifyCache.call(var4.volume(), 3, var2, null), "SetMasterVolume");
      }
   }

   void setMuted(boolean var1) throws SpotifyCache.TypeB {
      for (SpotifySupport8.TypeH var3 : this.K) {
         SpotifyCache.check(SpotifyCache.call(var3.volume(), 5, var1 ? 1 : 0, null), "SetMute");
      }
   }

   float peak() throws SpotifyCache.TypeB {
      float var1 = 0.0F;
      FloatByReference var2 = new FloatByReference();

      for (SpotifySupport8.TypeH var4 : this.K) {
         if (var4.meter() != null && SpotifyCache.call(var4.meter(), 3, var2) >= 0) {
            var1 = Math.max(var1, var2.getValue());
         }
      }

      return var1;
   }

private record TypeH(Pointer a, Pointer R,  Pointer D) {
      public Pointer control() {
         return this.a;
      }

      public Pointer volume() {
         return this.R;
      }

      
      public Pointer meter() {
         return this.D;
      }

      public Pointer pointerState9() {
         return this.a;
      }

      public Pointer pointerState10() {
         return this.R;
      }

      
      public Pointer pointerState11() {
         return this.D;
      }
   }
}
