// Sourced By DevFFNightmare
package dev.purxiy.modules.mount;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

final class MountSupport {
   private static final Memory H = MountCache.guid("BCDE0395-E52F-467C-8E3D-C4579291692E");
   private static final Memory e = MountCache.guid("A95664D2-9614-4F35-A746-DE8DB63617E6");
   private static final Memory Z = MountCache.guid("77AA99A0-1BD6-484F-8BC7-2C654C9A9B6F");
   private static final Memory o = MountCache.guid("BFB7FF88-7239-4FC9-8FA2-07C950BE9C6D");
   private static final Memory T = MountCache.guid("87CE5498-68D6-44E5-9215-6DA47EF883D8");
   private static final Memory p = MountCache.guid("C02216F6-8C67-4B5B-9D00-D008E73E0064");
   private static final int G = 0;
   private static final int P = 1;
   private static final int d = 2;
   
   private Pointer y;
   private final List<MountSupport.TypeZ> x = new ArrayList<>();




   private static final long g = 4294967295L;

   boolean open() {
      return this.y != null;
   }

   void start() throws MountCache.TypeD {
      MountCache.initThread();
      this.y = MountCache.createInstance(H, e);
   }

   void close() {
      long var1 = 139741694258319L;
      long var3 = var1 ^ 60872944047147L;
      this.s(var3);
      MountCache.release(this.y);
      this.y = null;
   }

   boolean found() {
      return !this.x.isEmpty();
   }

   private void s(long var1) {
      MountFactory.Data7.ensureSourceClassInitialized();

      for (MountSupport.TypeZ var4 : this.x) {
         Pointer var5 = var4.meter();
         long var6 = 79461999392931L;
         if (79461999392931L == 0L) {
            boolean var10000 = false;
         } else {
            int var10 = var6 < 0L ? -1 : 1;
         }

         MountCache.release(var5);
         MountCache.release(var4.volume());
         MountCache.release(var4.control());
      }

      long var9 = 79461999392932L;
      if (79461999392932L == 0L) {
         boolean var11 = false;
      } else {
         int var12 = var9 < 0L ? -1 : 1;
      }

      this.x.clear();
   }

   void find(Set<String> var1) throws MountCache.TypeD {
      long var2 = 131762992366309L;
      long var4 = var2 ^ 69901773830721L;
      long var6 = var2 ^ 20732085792458L;
      this.s(var4);
      Pointer var8 = this.y;
      if (var8 != null && !var1.isEmpty()) {
         PointerByReference var9 = new PointerByReference();
         MountCache.check(MountCache.call(var8, 3, 0, 1, var9), "EnumAudioEndpoints");
         Pointer var10 = var9.getValue();
         if (var10 != null) {
            try {
               int var11 = MountCache.getInt(var10, 3, "Devices.Count");

               for (int var12 = 0; var12 < var11; var12++) {
                  PointerByReference var13 = new PointerByReference();
                  if (MountCache.call(var10, 4, var12, var13) >= 0 && var13.getValue() != null) {
                     try {
                        this.m(var13.getValue(), var6, var1);
                     } finally {
                        MountCache.release(var13.getValue());
                     }
                  }
               }
            } finally {
               MountCache.release(var10);
            }
         }
      }
   }

   private void m(Pointer var1, long var2, Set var4) throws MountCache.TypeD {
      long var5 = 34711170420206L;
      MountFactory.Data7.ensureSourceClassInitialized();
      PointerByReference var7 = new PointerByReference();
      if (MountCache.call(var1, 3, Z, 23, null, var7) >= 0 && var7.getValue() != null) {
         Pointer var8 = var7.getValue();

         try {
            Pointer var9 = MountCache.getObject(var8, 5, "GetSessionEnumerator");
            long var10 = 111108232916015L;
            if (111108232916015L == 0L) {
               boolean var10000 = false;
            } else {
               int var38 = var10 < 0L ? -1 : 1;
            }

            if (var9 == null) {
               return;
            }

            boolean var13 = false;

            try {
               var13 = true;
               int var14 = MountCache.getInt(var9, 3, "Sessions.Count");

               for (int var15 = 0; var15 < var14; var15++) {
                  PointerByReference var16 = new PointerByReference();
                  long var17 = 111108232916014L;
                  if (111108232916014L == 0L) {
                     boolean var39 = false;
                  } else {
                     int var40 = var17 < 0L ? -1 : 1;
                  }

                  long var20 = 111108232916014L;
                  if (111108232916014L == 0L) {
                     boolean var41 = false;
                  } else {
                     int var42 = var20 < 0L ? -1 : 1;
                  }

                  if (MountCache.call(var9, 4, var15, var16) >= 0 && var16.getValue() != null) {
                     try {
                        this.T(var16.getValue(), var4, var5);
                     } finally {
                        MountCache.release(var16.getValue());
                     }
                  }
               }

               var13 = false;
            } finally {
               if (var13) {
                  MountCache.release(var9);
               }
            }

            long var37 = 111108232916014L;
            if (111108232916014L == 0L) {
               boolean var43 = false;
            } else {
               int var44 = var37 < 0L ? -1 : 1;
            }

            MountCache.release(var9);
         } finally {
            MountCache.release(var8);
         }
      }
   }

   private void T(Pointer var1, Set var2, long var3) throws MountCache.TypeD {
      long var5 = 48500828313112L;
      MountFactory.Data7.ensureSourceClassInitialized();
      Pointer var7 = MountCache.queryInterface(var1, o);
      boolean var8 = false;
      boolean var9 = false;

      label114: {
         try {
            var9 = true;
            int var10 = MountCache.getInt(var7, 3, "Session.State");
            long var11 = 34711170420206L;
            if (34711170420206L == 0L) {
               boolean var10000 = false;
            } else {
               int var23 = var11 < 0L ? -1 : 1;
            }

            if (var10 == 2) {
               var9 = false;
               break label114;
            }

            if (!var2.contains(t(var7, var5))) {
               var9 = false;
               break label114;
            }

            Pointer var14 = MountCache.queryInterface(var7, T);
            Pointer var15 = null;

            try {
               var15 = MountCache.queryInterface(var7, p);
            } catch (MountCache.TypeD var21) {
            }

            this.x.add(new MountSupport.TypeZ(var7, var14, var15));
            long var16 = 34711170420205L;
            if (34711170420205L == 0L) {
               boolean var24 = false;
            } else {
               int var25 = var16 < 0L ? -1 : 1;
            }

            var8 = true;
            var9 = false;
         } finally {
            if (var9 && !var8) {
               MountCache.release(var7);
            }
         }

         return;
      }

      MountCache.release(var7);
   }

   private static String t(Pointer var0, long var1) {
      MountFactory.Data7.ensureSourceClassInitialized();
      PointerByReference var3 = new PointerByReference();
      if (MountCache.call(var0, 12, var3) >= 0) {
         String var4 = MountCache.taskString(var3.getValue()).toLowerCase(Locale.ROOT);
         int var5 = var4.lastIndexOf(".exe");
         if (var5 > 0) {
            int var6 = Math.max(var4.lastIndexOf(92, var5), var4.lastIndexOf(124, var5)) + 1;
            return var4.substring(var6, var5 + 4);
         }
      }

      IntByReference var7 = new IntByReference();
      return MountCache.call(var0, 14, var7) >= 0 && var7.getValue() != 0
         ? ProcessHandle.of(var7.getValue() & 4294967295L)
            .flatMap(var0x -> var0x.info().command())
            .map(var0x -> var0x.substring(Math.max(var0x.lastIndexOf(92), var0x.lastIndexOf(47)) + 1).toLowerCase(Locale.ROOT))
            .orElse("")
         : "";
   }

   boolean alive() throws MountCache.TypeD {
      for (MountSupport.TypeZ var2 : this.x) {
         if (MountCache.getInt(var2.control(), 3, "Session.State") == 2) {
            return false;
         }
      }

      return true;
   }

   float volume() throws MountCache.TypeD {
      if (this.x.isEmpty()) {
         return -1.0F;
      } else {
         FloatByReference var1 = new FloatByReference();
         MountCache.check(MountCache.call(this.x.getFirst().volume(), 4, var1), "GetMasterVolume");
         return var1.getValue();
      }
   }

   boolean muted() throws MountCache.TypeD {
      return this.x.isEmpty() ? false : MountCache.getInt(this.x.getFirst().volume(), 6, "GetMute") != 0;
   }

   void setVolume(float var1) throws MountCache.TypeD {
      float var2 = Math.max(0.0F, Math.min(1.0F, var1));

      for (MountSupport.TypeZ var4 : this.x) {
         MountCache.check(MountCache.call(var4.volume(), 3, var2, null), "SetMasterVolume");
      }
   }

   void setMuted(boolean var1) throws MountCache.TypeD {
      for (MountSupport.TypeZ var3 : this.x) {
         MountCache.check(MountCache.call(var3.volume(), 5, var1 ? 1 : 0, null), "SetMute");
      }
   }

   float peak() throws MountCache.TypeD {
      float var1 = 0.0F;
      FloatByReference var2 = new FloatByReference();

      for (MountSupport.TypeZ var4 : this.x) {
         if (var4.meter() != null && MountCache.call(var4.meter(), 3, var2) >= 0) {
            var1 = Math.max(var1, var2.getValue());
         }
      }

      return var1;
   }

private record TypeZ(Pointer s, Pointer r,  Pointer m) {
      public Pointer control() {
         return this.s;
      }

      public Pointer volume() {
         return this.r;
      }

      
      public Pointer meter() {
         return this.m;
      }

      public Pointer pointerState2() {
         return this.s;
      }

      public Pointer pointerState3() {
         return this.r;
      }

      
      public Pointer pointerState4() {
         return this.m;
      }
   }
}
