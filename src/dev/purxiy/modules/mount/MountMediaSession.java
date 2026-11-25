package dev.purxiy.modules.mount;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import java.io.ByteArrayOutputStream;
import java.util.Locale;

final class MountMediaSession {
   private static final String u = "Windows.Media.Control.GlobalSystemMediaTransportControlsSessionManager";
   private static final Memory a = MountCache.guid("2050C4EE-11A0-57DE-AED7-C97C70338245");
   private static final long Y = 10000L;
   private static final long l = 1000000000L;
   private static final long B = 500000000L;
   private static final long f = 250000000L;
   private static final int p = 8388608;
   private Pointer q;
   private Pointer v;
   private String o = "";
   private boolean X = true;
   private long n;
   private long t;
   private long E;
   private int y;
   private String C = "";
   private String i = "";
   private String c = "";
   private long T;
   private boolean J;
   private int L;
   private long z = -1L;
   private long r = -1L;
   private MountData.MountType d = MountData.MountType.k;
   byte[] G;
   private static final long[] j = new long[]{
      2602348243462097041L,
      8738904136252130661L,
      7596377793006995333L,
      5619304817221907149L,
      6787928826147670176L,
      779311383328991442L,
      -1745764839154265167L,
      7104971737233922291L
   };
   private static final Long[] k = new Long[8];

   boolean open() {
      return this.q != null;
   }

   void start() throws MountCache.MountException {
      MountCache.initThread();
      Pointer var1 = MountCache.activationFactory("Windows.Media.Control.GlobalSystemMediaTransportControlsSessionManager", a);

      try {
         Pointer var2 = MountCache.getRequired(var1, 6, "RequestAsync");
         Pointer var3 = MountCache.awaitObject(var2, 5000L, "RequestAsync");
         if (var3 == null) {
            throw new MountCache.MountException("RequestAsync returned null", -2147467259);
         }

         this.q = var3;
      } finally {
         MountCache.release(var1);
      }
   }

   void close() {
      long var1 = 55349369078629L;
      long var3 = var1 ^ 135695560362093L;
      this.s(var3);
      MountCache.release(this.q);
      this.q = null;
   }

   private void s(long var1) {
      MountCache.release(this.v);
      this.v = null;
      this.o = "";
      this.C = this.i = this.c = "";
      this.z = -1L;
      this.y = 0;
      this.J = false;
   }

   MountData poll(boolean var1, boolean var2) throws MountCache.MountException {
      long var3 = 8740359266511L;
      long var5 = var3 ^ 74261450767733L;
      int var7 = (int)((var3 ^ 74261450767733L) >>> 48);
      int var8 = (int)((var3 ^ 74261450767733L) << 16 >>> 48);
      int var9 = (int)(var5 << 32 >>> 32);
      long var10 = var3 ^ 482439869320L;
      long var12 = var3 ^ 85824427328327L;
      Pointer var14 = this.q;
      if (var14 == null) {
         return null;
      } else {
         long var15 = System.nanoTime();
         if (var1 != this.X) {
            this.X = var1;
            this.n = 0L;
         }

         if (this.v == null || var2 || var15 - this.n > 1000000000L) {
            this.n = var15;
            this.C(var14, var10);
         }

         Pointer var17 = this.v;
         if (var17 == null) {
            return null;
         } else {
            Pointer var18 = MountCache.getRequired(var17, 8, "GetTimelineProperties");

            long var19;
            long var21;
            long var23;
            long var25;
            try {
               var19 = MountCache.getLong(var18, 6, "StartTime");
               var21 = MountCache.getLong(var18, 7, "EndTime");
               var23 = MountCache.getLong(var18, 10, "Position");
               var25 = MountCache.getLong(var18, 11, "LastUpdatedTime");
            } finally {
               MountCache.release(var18);
            }

            long var27 = MountCache.preciseFileTime();
            long var29 = System.nanoTime();
            Pointer var31 = MountCache.getRequired(var17, 9, "GetPlaybackInfo");

            MountData.MountType var32;
            int var33;
            int var34;
            try {
               var32 = MountData.MountType.of(MountCache.getInt(var31, 7, "PlaybackStatus"));
               var33 = MountCache.getReferenceInt(var31, 9, "AutoRepeatMode", -1);
               var34 = MountCache.getReferenceBoolean(var31, 11, "IsShuffleActive");
               if (var2 || var15 - this.t > 500000000L) {
                  this.t = var15;
                  this.y = C(var12, var31);
               }
            } finally {
               MountCache.release(var31);
            }

            long var35 = var21 - var19;
            long var37 = (var23 - var19) / 10000L;
            if (var32 == MountData.MountType.w && var25 > 0L) {
               long var39 = var27 - var25;
               if (var39 > 0L && var39 < 36000000000L) {
                  var37 += var39 / 10000L;
               }
            }

            boolean var48 = var35 != this.z || var32 != this.d || this.r > 3000L && var37 < 1500L;
            this.z = var35;
            this.d = var32;
            this.r = var37;
            if (var2 || var48 || this.J || var15 - this.E > 250000000L) {
               this.E = var15;
               this.k(var17, (short)var7, (char)var8, var9);
            }

            long var40 = Math.max(0L, var35 / 10000L);
            return new MountData(this.o, this.T, this.C, this.i, this.c, var40, Math.max(0L, var37), var29, var32, var34, MountData.MountCategory.of(var33), this.y);
         }
      }
   }

   private void C(Pointer var1, long var2) throws MountCache.MountException {
      long var4 = 80526657964808L;
      long var6 = 80442127783258L;
      long var8 = 5624070888567L;
      MountFactory.MountSpec.ensureSourceClassInitialized();
      Pointer var10 = null;
      String var11 = "";
      if (!this.X) {
         var10 = MountCache.getObject(var1, 6, "GetCurrentSession");
         if (var10 == null || !t(var8, var10)) {
            long var12 = 8260659336006L;
            int var37 = var12 < 0L ? -1 : 1;
            Pointer var15 = L(var1, var6);
            long var16 = 8260659336007L;
            int var18 = var16 < 0L ? -1 : 1;
            if (var15 != null) {
               MountCache.release(var10);
               var10 = var15;
            }
         }

         long var34 = 8260659336006L;
         int var36 = var34 < 0L ? -1 : 1;
         if (var10 != null) {
            var11 = MountCache.getString(var10, 6, "SourceAppUserModelId");
         }
      }

      long var35 = 8260659336007L;
      int var371 = var35 < 0L ? -1 : 1;
      if (var10 == null) {
         Pointer var36 = MountCache.getRequired(var1, 7, "GetSessions");
         boolean var38 = false;

         try {
            label214: {
               var38 = true;
               int var17 = MountCache.getInt(var36, 7, "Sessions.Size");

               for (int var18 = 0; var18 < var17; var18++) {
                  long var19 = 8260659336006L;
                  int var21 = var19 < 0L ? -1 : 1;
                  if (var10 != null) {
                     var38 = false;
                     break label214;
                  }

                  PointerByReference var21x = new PointerByReference();
                  MountCache.check(MountCache.call(var36, 6, var18, var21x), "Sessions.GetAt");
                  Pointer var22 = var21x.getValue();
                  long var23 = 8260659336007L;
                  int var40 = var23 < 0L ? -1 : 1;
                  if (var22 == null) {
                     long var25 = 8260659336006L;
                     int var261 = var25 < 0L ? -1 : 1;
                  } else {
                     String var40x = MountCache.getString(var22, 6, "SourceAppUserModelId");
                     long var26 = 8260659336007L;
                     int var29 = var26 < 0L ? -1 : 1;
                     if (this.X && !isSpotify(var40x)) {
                        MountCache.release(var22);
                     } else {
                        var10 = var22;
                        var11 = var40x;
                        long var29x = 8260659336006L;
                     }
                  }
               }

               var38 = false;
            }
         } finally {
            if (var38) {
               MountCache.release(var36);
            }
         }

         long var39 = 8260659336007L;
         int var44 = var39 < 0L ? -1 : 1;
         MountCache.release(var36);
      }

      if (var10 != null) {
         long var37x = 8260659336007L;
         int var42 = var37x < 0L ? -1 : 1;
         if (this.v != null && var11.equals(this.o)) {
            MountCache.release(this.v);
            this.v = var10;
            return;
         }
      }

      this.s(var4);
      this.v = var10;
      this.o = var11;
      if (var10 != null) {
         this.E = 0L;
         this.t = 0L;
      }
   }

   static boolean isSpotify(String var0) {
      return var0.toLowerCase(Locale.ROOT).contains("spotify");
   }

   private static boolean t(long var0, Pointer var2) {
      MountFactory.MountSpec.ensureSourceClassInitialized();

      try {
         Pointer var3 = MountCache.getRequired(var2, 9, "GetPlaybackInfo");

         boolean var4;
         try {
            var4 = MountData.MountType.of(MountCache.getInt(var3, 7, "PlaybackStatus")) == MountData.MountType.w;
         } finally {
            MountCache.release(var3);
         }

         return var4;
      } catch (MountCache.MountException var91) {
         return false;
      }
   }

   private static Pointer L(Pointer var0, long var1) throws MountCache.MountException {
      long var3 = 5624070888567L;
      MountFactory.MountSpec.ensureSourceClassInitialized();
      Pointer var5 = MountCache.getRequired(var0, 7, "GetSessions");
      Pointer var6 = null;
      int var7 = 0;
      boolean var8 = false;
      boolean var9 = false;

      try {
         var9 = true;
         int var10 = MountCache.getInt(var5, 7, "Sessions.Size");

         for (int var11 = 0; var11 < var10; var11++) {
            long var12 = 80442127783257L;
            int var15 = var12 < 0L ? -1 : 1;
            long var15x = 80442127783258L;
            int var17 = var15x < 0L ? -1 : 1;
            if (var7 >= 3) {
               break;
            }

            PointerByReference var17x = new PointerByReference();
            MountCache.check(MountCache.call(var5, 6, var11, var17x), "Sessions.GetAt");
            Pointer var18 = var17x.getValue();
            if (var18 == null) {
               long var19 = 80442127783257L;
               int var21 = var19 < 0L ? -1 : 1;
            } else {
               boolean var39 = false;

               try {
                  boolean var20 = isSpotify(MountCache.getString(var18, 6, "SourceAppUserModelId"));
                  boolean var21 = t(var3, var18);
                  long var22 = 80442127783258L;
                  int var24 = var22 < 0L ? -1 : 1;
                  var24 = var21 ? (var20 ? 3 : 2) : (var20 ? 1 : 0);
                  long var25 = 80442127783257L;
                  int var26 = var25 < 0L ? -1 : 1;
                  if (var24 > var7) {
                     MountCache.release(var6);
                     var6 = var18;
                     var7 = var24;
                     var39 = true;
                  }
               } finally {
                  if (!var39) {
                     MountCache.release(var18);
                  }
               }
            }
         }

         long var38 = 80442127783257L;
         int var14 = var38 < 0L ? -1 : 1;
         var8 = true;
         long var14x = 80442127783258L;
         int var15x = var14x < 0L ? -1 : 1;
         var9 = false;
      } finally {
         if (var9) {
            long var29 = 80442127783257L;
            int var31 = var29 < 0L ? -1 : 1;
            MountCache.release(var5);
            if (!var8) {
               MountCache.release(var6);
            }
         }
      }

      MountCache.release(var5);
      return var6;
   }

   private static int C(long var0, Pointer var2) throws MountCache.MountException {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      Pointer var3 = MountCache.getObject(var2, 6, "Controls");
      if (var3 == null) {
         return 0;
      } else {
         int var5;
         try {
            int var4 = 0;
            var4 |= MountCache.getBoolean(var3, 6, "IsPlayEnabled") ? 1 : 0;
            var4 |= MountCache.getBoolean(var3, 7, "IsPauseEnabled") ? 2 : 0;
            var4 |= MountCache.getBoolean(var3, 12, "IsNextEnabled") ? 4 : 0;
            var4 |= MountCache.getBoolean(var3, 13, "IsPreviousEnabled") ? 8 : 0;
            var4 |= MountCache.getBoolean(var3, 16, "IsPlayPauseToggleEnabled") ? 128 : 0;
            var4 |= MountCache.getBoolean(var3, 17, "IsShuffleEnabled") ? 32 : 0;
            var4 |= MountCache.getBoolean(var3, 18, "IsRepeatEnabled") ? 64 : 0;
            var5 = var4 | (MountCache.getBoolean(var3, 20, "IsPlaybackPositionEnabled") ? 16 : 0);
         } finally {
            MountCache.release(var3);
         }

         return var5;
      }
   }

   private void k(Pointer var1, short var2, char var3, int var4) throws MountCache.MountException {
      long var5 = 75287847427514L;
      long var7 = var5 ^ 90894292990821L;
      MountFactory.MountSpec.ensureSourceClassInitialized();
      Pointer var9 = MountCache.getRequired(var1, 7, "TryGetMediaPropertiesAsync");
      Pointer var10 = MountCache.awaitObject(var9, 1500L, "TryGetMediaPropertiesAsync");
      if (var10 != null) {
         try {
            String var11 = MountCache.getString(var10, 6, "Title");
            String var12 = MountCache.getString(var10, 9, "Artist");
            String var13 = MountCache.getString(var10, 10, "AlbumTitle");
            if (var12.isEmpty()) {
               var12 = MountCache.getString(var10, 8, "AlbumArtist");
            }

            short var14 = 0;
            if (!var11.equals(this.C) || !var12.equals(this.i) || !var13.equals(this.c)) {
               this.C = var11;
               this.i = var12;
               this.c = var13;
               this.T++;
               this.G = null;
               this.J = !var11.isEmpty();
               this.L = 0;
            }

            if (this.J) {
               byte[] var15 = p(var10, var7);
               if (var15 != null) {
                  int var16 = var15.length;
                  if (var16 > 0) {
                     this.G = var15;
                     this.J = false;
                     return;
                  }
               }

               var14 = 17529;
               if (++this.L > 24) {
                  this.J = false;
               }
            }
         } finally {
            MountCache.release(var10);
         }
      }
   }

   private static byte[] p(Pointer var0, long var1) throws MountCache.MountException {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      Pointer var3 = MountCache.getObject(var0, 15, "Thumbnail");
      if (var3 == null) {
         return null;
      } else {
         byte[] var39;
         try {
            Pointer var5 = MountCache.getRequired(var3, 6, "OpenReadAsync");
            Pointer var6 = MountCache.awaitObject(var5, 3000L, "OpenReadAsync");
            if (var6 == null) {
               Object var7 = null;
               return (byte[])var7;
            }

            try {
               Pointer var37 = MountCache.streamOver(var6);
               boolean var8 = false;

               byte[] var4;
               try {
                  var8 = true;
                  ByteArrayOutputStream var9 = new ByteArrayOutputStream(65536);
                  Memory var10 = new Memory(65536L);
                  IntByReference var11 = new IntByReference();

                  while (var9.size() < 8388608) {
                     int var12 = MountCache.call(var37, 3, var10, (int)var10.size(), var11);
                     MountCache.check(var12, "IStream.Read");
                     int var13 = var11.getValue();
                     long var14 = 25098369984222L;
                     int var17 = var14 < 0L ? -1 : 1;
                     if (var13 > 0) {
                        var9.write(var10.getByteArray(0L, var13), 0, var13);
                     }

                     long var17x = 25098369984222L;
                     int var19 = var17x < 0L ? -1 : 1;
                     if (var12 == 1) {
                        break;
                     }

                     if (var13 <= 0) {
                        long var19x = 25098369984223L;
                        int var21 = var19x < 0L ? -1 : 1;
                        break;
                     }
                  }

                  var4 = var9.toByteArray();
                  var8 = false;
               } finally {
                  if (var8) {
                     MountCache.release(var37);
                  }
               }

               long var38 = 25098369984222L;
               int var38x = var38 < 0L ? -1 : 1;
               MountCache.release(var37);
               var39 = var4;
            } finally {
               MountCache.release(var6);
            }
         } finally {
            MountCache.release(var3);
         }

         return var39;
      }
   }

   boolean send(MountApi var1) throws MountCache.MountException {
      Pointer var2 = this.v;
      if (var2 == null) {
         return false;
      } else {
         PointerByReference var3 = new PointerByReference();
         String var4;
         int var5;
         switch (var1) {
            case MountApi.Play var8:
               var4 = "TryPlayAsync";
               var5 = MountCache.call(var2, 10, var3);
               break;
            case MountApi.Pause var9:
               var4 = "TryPauseAsync";
               var5 = MountCache.call(var2, 11, var3);
               break;
            case MountApi.TogglePlayPause var10:
               var4 = "TryTogglePlayPauseAsync";
               var5 = MountCache.call(var2, 20, var3);
               break;
            case MountApi.SkipNext var11:
               var4 = "TrySkipNextAsync";
               var5 = MountCache.call(var2, 16, var3);
               break;
            case MountApi.SkipPrevious var12:
               var4 = "TrySkipPreviousAsync";
               var5 = MountCache.call(var2, 17, var3);
               break;
            case MountApi.Seek var13:
               var4 = "TryChangePlaybackPositionAsync";
               var5 = MountCache.call(var2, 24, Math.max(0L, var13.positionMs()) * 10000L, var3);
               break;
            case MountApi.SetShuffle var14:
               var4 = "TryChangeShuffleActiveAsync";
               var5 = MountCache.call(var2, 23, (byte)(var14.on() ? 1 : 0), var3);
               break;
            case MountApi.Data var15:
               var4 = "TryChangeAutoRepeatModeAsync";
               var5 = MountCache.call(var2, 21, var15.mode().winrt(), var3);
               break;
            default:
               return false;
         }

         MountCache.check(var5, var4);
         Pointer var6 = var3.getValue();
         return var6 != null && MountCache.awaitBoolean(var6, 2000L, var4);
      }
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 1612) & 32767;
      if (k[var3] == null) {
         k[var3] = j[var3] ^ var1;
      }

      return k[var3];
   }
}
