package dev.purxiy.modules.spotify;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import java.io.ByteArrayOutputStream;
import java.util.Locale;

final class SpotifyQueue {
   private static final String x = "Windows.Media.Control.GlobalSystemMediaTransportControlsSessionManager";
   private static final Memory w = SpotifyCache.guid("2050C4EE-11A0-57DE-AED7-C97C70338245");
   private static final long k = 10000L;
   private static final long a = 1000000000L;
   private static final long N = 500000000L;
   private static final long r = 250000000L;
   private static final int d = 8388608;
   private Pointer i;
   private Pointer O;
   private String A = "";
   private boolean T = true;
   private long h;
   private long l;
   private long X;
   private int H;
   private String m = "";
   private String b = "";
   private String M = "";
   private long L;
   private boolean B;
   private int c;
   private long p = -1L;
   private long G = -1L;
   private SpotifyData.RepeatMode Y = SpotifyData.RepeatMode.V;
   byte[] U;
   private static final long[] n = new long[]{
      7559519014225312054L,
      2902017368282014443L,
      2337837175177531455L,
      7997445603372448402L,
      3346641141979613603L,
      -3274811727603286574L,
      1237593813336868635L,
      4925302435215891518L
   };
   private static final Long[] o = new Long[8];

   boolean open() {
      return this.i != null;
   }

   void start() throws SpotifyCache.SpotifyException {
      SpotifyCache.initThread();
      Pointer var1 = SpotifyCache.activationFactory("Windows.Media.Control.GlobalSystemMediaTransportControlsSessionManager", w);

      try {
         Pointer var2 = SpotifyCache.getRequired(var1, 6, "RequestAsync");
         Pointer var3 = SpotifyCache.awaitObject(var2, 5000L, "RequestAsync");
         if (var3 == null) {
            throw new SpotifyCache.SpotifyException("RequestAsync returned null", -2147467259);
         }

         this.i = var3;
      } finally {
         SpotifyCache.release(var1);
      }
   }

   void close() {
      long var1 = 112424355241166L;
      long var3 = var1 ^ 82158299769730L;
      this.d(var3);
      SpotifyCache.release(this.i);
      this.i = null;
   }

   private void d(long var1) {
      SpotifyCache.release(this.O);
      this.O = null;
      this.A = "";
      this.m = this.b = this.M = "";
      this.p = -1L;
      this.H = 0;
      this.B = false;
   }

   SpotifyData poll(boolean var1, boolean var2) throws SpotifyCache.SpotifyException {
      long var3 = 101681144826223L;
      long var5 = var3 ^ 106180546463184L;
      long var7 = var3 ^ 136337954259504L;
      long var9 = var3 ^ 56857093147569L;
      Pointer var11 = this.i;
      if (var11 == null) {
         return null;
      } else {
         long var12 = System.nanoTime();
         if (var1 != this.T) {
            this.T = var1;
            this.h = 0L;
         }

         if (this.O == null || var2 || var12 - this.h > 1000000000L) {
            this.h = var12;
            this.B(var11, var5);
         }

         Pointer var14 = this.O;
         if (var14 == null) {
            return null;
         } else {
            Pointer var15 = SpotifyCache.getRequired(var14, 8, "GetTimelineProperties");

            long var16;
            long var18;
            long var20;
            long var22;
            try {
               var16 = SpotifyCache.getLong(var15, 6, "StartTime");
               var18 = SpotifyCache.getLong(var15, 7, "EndTime");
               var20 = SpotifyCache.getLong(var15, 10, "Position");
               var22 = SpotifyCache.getLong(var15, 11, "LastUpdatedTime");
            } finally {
               SpotifyCache.release(var15);
            }

            long var24 = SpotifyCache.preciseFileTime();
            long var26 = System.nanoTime();
            Pointer var28 = SpotifyCache.getRequired(var14, 9, "GetPlaybackInfo");

            SpotifyData.RepeatMode var29;
            int var30;
            int var31;
            try {
               var29 = SpotifyData.RepeatMode.of(SpotifyCache.getInt(var28, 7, "PlaybackStatus"));
               var30 = SpotifyCache.getReferenceInt(var28, 9, "AutoRepeatMode", -1);
               var31 = SpotifyCache.getReferenceBoolean(var28, 11, "IsShuffleActive");
               if (var2 || var12 - this.l > 500000000L) {
                  this.l = var12;
                  this.H = o(var9, var28);
               }
            } finally {
               SpotifyCache.release(var28);
            }

            long var32 = var18 - var16;
            long var34 = (var20 - var16) / 10000L;
            if (var29 == SpotifyData.RepeatMode.t && var22 > 0L) {
               long var36 = var24 - var22;
               if (var36 > 0L && var36 < 36000000000L) {
                  var34 += var36 / 10000L;
               }
            }

            boolean var45 = var32 != this.p || var29 != this.Y || this.G > 3000L && var34 < 1500L;
            this.p = var32;
            this.Y = var29;
            this.G = var34;
            if (var2 || var45 || this.B || var12 - this.X > 250000000L) {
               this.X = var12;
               this.U(var7, var14);
            }

            long var37 = Math.max(0L, var32 / 10000L);
            return new SpotifyData(this.A, this.L, this.m, this.b, this.M, var37, Math.max(0L, var34), var26, var29, var31, SpotifyData.PlaybackState.of(var30), this.H);
         }
      }
   }

   private void B(Pointer var1, long var2) throws SpotifyCache.SpotifyException {
      long var4 = 36581296229688L;
      long var6 = 82765073984481L;
      byte var8 = 0;
      int var9 = 1262894805;
      int var10 = (int)(var6 << 48 >>> 48);
      long var11 = 48959096092492L;
      Pointer var13 = null;
      SpotifyCurve.ensureSourceClassInitialized();
      String var14 = "";
      if (!this.T) {
         var13 = SpotifyCache.getObject(var1, 6, "GetCurrentSession");
         if (var13 == null || !I(var4, var13)) {
            long var15 = 66968896244927L;
            int var41 = var15 < 0L ? -1 : 1;
            Pointer var19 = s(var8, var9, var1, (char)var10);
            long var20 = 66968896244926L;
            int var20x = var20 < 0L ? -1 : 1;
            if (var19 != null) {
               SpotifyCache.release(var13);
               var13 = var19;
            }
         }

         long var38 = 66968896244927L;
         int var391 = var38 < 0L ? -1 : 1;
         if (var13 != null) {
            var14 = SpotifyCache.getString(var13, 6, "SourceAppUserModelId");
         }
      }

      long var39 = 66968896244926L;
      int var40 = var39 < 0L ? -1 : 1;
      if (var13 == null) {
         Pointer var18 = SpotifyCache.getRequired(var1, 7, "GetSessions");
         boolean var42 = false;

         try {
            label221: {
               var42 = true;
               int var44 = SpotifyCache.getInt(var18, 7, "Sessions.Size");

               for (int var21 = 0; var21 < var44; var21++) {
                  long var22 = 66968896244927L;
                  int var24 = var22 < 0L ? -1 : 1;
                  if (var13 != null) {
                     var42 = false;
                     break label221;
                  }

                  PointerByReference var24x = new PointerByReference();
                  SpotifyCache.check(SpotifyCache.call(var18, 6, var21, var24x), "Sessions.GetAt");
                  Pointer var25 = var24x.getValue();
                  long var26 = 66968896244926L;
                  int var46 = var26 < 0L ? -1 : 1;
                  if (var25 == null) {
                     long var28 = 66968896244927L;
                     int var291 = var28 < 0L ? -1 : 1;
                  } else {
                     String var46x = SpotifyCache.getString(var25, 6, "SourceAppUserModelId");
                     long var29 = 66968896244927L;
                     int var32 = var29 < 0L ? -1 : 1;
                     if (this.T && !isSpotify(var46x)) {
                        SpotifyCache.release(var25);
                     } else {
                        var13 = var25;
                        var14 = var46x;
                        long var32x = 66968896244926L;
                     }
                  }
               }

               var42 = false;
            }
         } finally {
            if (var42) {
               SpotifyCache.release(var18);
            }
         }

         long var45 = 66968896244927L;
         int var50 = var45 < 0L ? -1 : 1;
         SpotifyCache.release(var18);
      }

      if (var13 != null && this.O != null) {
         boolean var40x = var14.equals(this.A);
         long var43 = 66968896244926L;
         int var49 = var43 < 0L ? -1 : 1;
         if (var40x) {
            SpotifyCache.release(this.O);
            this.O = var13;
            return;
         }
      }

      long var41 = 66968896244927L;
      int var47 = var41 < 0L ? -1 : 1;
      this.d(var11);
      this.O = var13;
      this.A = var14;
      if (var13 != null) {
         this.X = 0L;
         this.l = 0L;
      }
   }

   static boolean isSpotify(String var0) {
      return var0.toLowerCase(Locale.ROOT).contains("spotify");
   }

   private static boolean I(long var0, Pointer var2) {
      SpotifyCurve.ensureSourceClassInitialized();

      try {
         Pointer var3 = SpotifyCache.getRequired(var2, 9, "GetPlaybackInfo");

         boolean var4;
         try {
            var4 = SpotifyData.RepeatMode.of(SpotifyCache.getInt(var3, 7, "PlaybackStatus")) == SpotifyData.RepeatMode.t;
         } finally {
            SpotifyCache.release(var3);
         }

         return var4;
      } catch (SpotifyCache.SpotifyException var91) {
         return false;
      }
   }

   private static Pointer s(short var0, int var1, Pointer var2, char var3) throws SpotifyCache.SpotifyException {
      long var4 = 82765073984481L;
      long var6 = var4 ^ 116561150961369L;
      Pointer var8 = SpotifyCache.getRequired(var2, 7, "GetSessions");
      Pointer var9 = null;
      SpotifyCurve.ensureSourceClassInitialized();
      int var10 = 0;
      boolean var11 = false;
      boolean var12 = false;

      try {
         var12 = true;
         int var13 = SpotifyCache.getInt(var8, 7, "Sessions.Size");

         for (int var14 = 0; var14 < var13; var14++) {
            int var15 = 1262894805;
            var15 = 1262894805;
            if (var10 >= 3) {
               break;
            }

            PointerByReference var16 = new PointerByReference();
            SpotifyCache.check(SpotifyCache.call(var8, 6, var14, var16), "Sessions.GetAt");
            Pointer var17 = var16.getValue();
            if (var17 != null) {
               boolean var18 = false;

               try {
                  boolean var19 = isSpotify(SpotifyCache.getString(var17, 6, "SourceAppUserModelId"));
                  boolean var20 = I(var6, var17);
                  var15 = 1262894805;
                  int var21 = var20 ? (var19 ? 3 : 2) : (var19 ? 1 : 0);
                  if (var21 > var10) {
                     SpotifyCache.release(var9);
                     var9 = var17;
                     var10 = var21;
                     var18 = true;
                  }
               } finally {
                  if (!var18) {
                     SpotifyCache.release(var17);
                  }
               }
            }
         }

         var11 = true;
         var12 = false;
      } finally {
         if (var12) {
            SpotifyCache.release(var8);
            if (!var11) {
               SpotifyCache.release(var9);
            }
         }
      }

      SpotifyCache.release(var8);
      return var9;
   }

   private static int o(long var0, Pointer var2) throws SpotifyCache.SpotifyException {
      SpotifyCurve.ensureSourceClassInitialized();
      Pointer var3 = SpotifyCache.getObject(var2, 6, "Controls");
      if (var3 == null) {
         return 0;
      } else {
         int var5;
         try {
            int var4 = 0;
            var4 |= SpotifyCache.getBoolean(var3, 6, "IsPlayEnabled") ? 1 : 0;
            var4 |= SpotifyCache.getBoolean(var3, 7, "IsPauseEnabled") ? 2 : 0;
            var4 |= SpotifyCache.getBoolean(var3, 12, "IsNextEnabled") ? 4 : 0;
            var4 |= SpotifyCache.getBoolean(var3, 13, "IsPreviousEnabled") ? 8 : 0;
            var4 |= SpotifyCache.getBoolean(var3, 16, "IsPlayPauseToggleEnabled") ? 128 : 0;
            var4 |= SpotifyCache.getBoolean(var3, 17, "IsShuffleEnabled") ? 32 : 0;
            var4 |= SpotifyCache.getBoolean(var3, 18, "IsRepeatEnabled") ? 64 : 0;
            var5 = var4 | (SpotifyCache.getBoolean(var3, 20, "IsPlaybackPositionEnabled") ? 16 : 0);
         } finally {
            SpotifyCache.release(var3);
         }

         return var5;
      }
   }

   private void U(long var1, Pointer var3) throws SpotifyCache.SpotifyException {
      byte var4 = 0;
      long var5 = 61852108986103L;
      Pointer var7 = SpotifyCache.getRequired(var3, 7, "TryGetMediaPropertiesAsync");
      SpotifyCurve.ensureSourceClassInitialized();
      Pointer var8 = SpotifyCache.awaitObject(var7, 1500L, "TryGetMediaPropertiesAsync");
      if (var8 != null) {
         try {
            String var9 = SpotifyCache.getString(var8, 6, "Title");
            String var10 = SpotifyCache.getString(var8, 9, "Artist");
            String var11 = SpotifyCache.getString(var8, 10, "AlbumTitle");
            if (var10.isEmpty()) {
               var10 = SpotifyCache.getString(var8, 8, "AlbumArtist");
            }

            if (!var9.equals(this.m) || !var10.equals(this.b) || !var11.equals(this.M)) {
               this.m = var9;
               this.b = var10;
               this.M = var11;
               this.L++;
               this.U = null;
               this.B = !var9.isEmpty();
               this.c = 0;
            }

            if (this.B) {
               byte[] var12 = O(var8, var4, var5);
               if (var12 != null) {
                  int var13 = var12.length;
                  long var14 = 43455872093022L;
                  int var17 = var14 < 0L ? -1 : 1;
                  if (var13 > 0) {
                     this.U = var12;
                     long var17x = 43455872093022L;
                     int var18 = var17x < 0L ? -1 : 1;
                     this.B = false;
                     return;
                  }
               }

               long var22 = 43455872093022L;
               int var15 = var22 < 0L ? -1 : 1;
               if (++this.c > 24) {
                  this.B = false;
               }
            }
         } finally {
            SpotifyCache.release(var8);
         }
      }
   }

   private static byte[] O(Pointer var0, short var1, long var2) throws SpotifyCache.SpotifyException {
      SpotifyCurve.ensureSourceClassInitialized();
      Pointer var4 = SpotifyCache.getObject(var0, 15, "Thumbnail");
      if (var4 == null) {
         return null;
      } else {
         byte[] var37;
         try {
            Pointer var6 = SpotifyCache.getRequired(var4, 6, "OpenReadAsync");
            Pointer var7 = SpotifyCache.awaitObject(var6, 3000L, "OpenReadAsync");
            if (var7 == null) {
               Object var8 = null;
               return (byte[])var8;
            }

            boolean var33 = false;

            byte[] var5;
            try {
               var33 = true;
               Pointer var9 = SpotifyCache.streamOver(var7);

               try {
                  ByteArrayOutputStream var10 = new ByteArrayOutputStream(65536);
                  Memory var11 = new Memory(65536L);
                  IntByReference var12 = new IntByReference();

                  while (var10.size() < 8388608) {
                     int var13 = SpotifyCache.call(var9, 3, var11, (int)var11.size(), var12);
                     SpotifyCache.check(var13, "IStream.Read");
                     int var14 = var12.getValue();
                     boolean var15 = false;
                     if (var14 > 0) {
                        var10.write(var11.getByteArray(0L, var14), 0, var14);
                     }

                     long var16 = 61852108986103L;
                     int var19 = var16 < 0L ? -1 : 1;
                     if (var13 == 1 || var14 <= 0) {
                        break;
                     }
                  }

                  var5 = var10.toByteArray();
               } finally {
                  SpotifyCache.release(var9);
               }

               var33 = false;
            } finally {
               if (var33) {
                  SpotifyCache.release(var7);
               }
            }

            long var34 = 61852108986103L;
            int var36 = var34 < 0L ? -1 : 1;
            SpotifyCache.release(var7);
            var37 = var5;
         } finally {
            SpotifyCache.release(var4);
         }

         return var37;
      }
   }

   boolean send(SpotifyApi var1) throws SpotifyCache.SpotifyException {
      Pointer var2 = this.O;
      if (var2 == null) {
         return false;
      } else {
         PointerByReference var3 = new PointerByReference();
         String var4;
         int var5;
         switch (var1) {
            case SpotifyApi.Play var8:
               var4 = "TryPlayAsync";
               var5 = SpotifyCache.call(var2, 10, var3);
               break;
            case SpotifyApi.Pause var9:
               var4 = "TryPauseAsync";
               var5 = SpotifyCache.call(var2, 11, var3);
               break;
            case SpotifyApi.Data var10:
               var4 = "TryTogglePlayPauseAsync";
               var5 = SpotifyCache.call(var2, 20, var3);
               break;
            case SpotifyApi.SkipNext var11:
               var4 = "TrySkipNextAsync";
               var5 = SpotifyCache.call(var2, 16, var3);
               break;
            case SpotifyApi.SkipPrevious var12:
               var4 = "TrySkipPreviousAsync";
               var5 = SpotifyCache.call(var2, 17, var3);
               break;
            case SpotifyApi.Seek var13:
               var4 = "TryChangePlaybackPositionAsync";
               var5 = SpotifyCache.call(var2, 24, Math.max(0L, var13.positionMs()) * 10000L, var3);
               break;
            case SpotifyApi.SetShuffle var14:
               var4 = "TryChangeShuffleActiveAsync";
               var5 = SpotifyCache.call(var2, 23, (byte)(var14.on() ? 1 : 0), var3);
               break;
            case SpotifyApi.SetRepeat var15:
               var4 = "TryChangeAutoRepeatModeAsync";
               var5 = SpotifyCache.call(var2, 21, var15.mode().winrt(), var3);
               break;
            default:
               return false;
         }

         SpotifyCache.check(var5, var4);
         Pointer var6 = var3.getValue();
         return var6 != null && SpotifyCache.awaitBoolean(var6, 2000L, var4);
      }
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 6286) & 32767;
      if (o[var3] == null) {
         o[var3] = n[var3] ^ var1;
      }

      return o[var3];
   }
}
