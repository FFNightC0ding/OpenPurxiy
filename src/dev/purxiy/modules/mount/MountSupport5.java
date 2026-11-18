// Sourced By DevFFNightmare
package dev.purxiy.modules.mount;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import java.io.ByteArrayOutputStream;
import java.util.Locale;

final class MountSupport5 {
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
   private MountData.TypeN d = MountData.TypeN.k;
   
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

   void start() throws MountCache.TypeD {
      MountCache.initThread();
      Pointer var1 = MountCache.activationFactory("Windows.Media.Control.GlobalSystemMediaTransportControlsSessionManager", a);

      try {
         Pointer var2 = MountCache.getRequired(var1, 6, "RequestAsync");
         Pointer var3 = MountCache.awaitObject(var2, 5000L, "RequestAsync");
         if (var3 == null) {
            throw new MountCache.TypeD("RequestAsync returned null", -2147467259);
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

   
   MountData poll(boolean var1, boolean var2) throws MountCache.TypeD {
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

            MountData.TypeN var32;
            int var33;
            int var34;
            try {
               var32 = MountData.TypeN.of(MountCache.getInt(var31, 7, "PlaybackStatus"));
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
            if (var32 == MountData.TypeN.w && var25 > 0L) {
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
            return new MountData(this.o, this.T, this.C, this.i, this.c, var40, Math.max(0L, var37), var29, var32, var34, MountData.TypeQ.of(var33), this.y);
         }
      }
   }

   private void C(Pointer var1, long var2) throws MountCache.TypeD {
      long var4 = 80526657964808L;
      long var6 = 80442127783258L;
      long var8 = 5624070888567L;
      MountFactory.Data7.ensureSourceClassInitialized();
      Pointer var10 = null;
      String var11 = "";
      if (!this.X) {
         var10 = MountCache.getObject(var1, 6, "GetCurrentSession");
         if (var10 == null || !t(var8, var10)) {
            long var12 = 8260659336006L;
            if (8260659336006L == 0L) {
               boolean var10000 = false;
            } else {
               int var41 = var12 < 0L ? -1 : 1;
            }

            Pointer var15 = L(var1, var6);
            long var16 = 8260659336007L;
            if (8260659336007L == 0L) {
               boolean var42 = false;
            } else {
               int var43 = var16 < 0L ? -1 : 1;
            }

            if (var15 != null) {
               MountCache.release(var10);
               var10 = var15;
            }
         }

         long var34 = 8260659336006L;
         if (8260659336006L == 0L) {
            boolean var44 = false;
         } else {
            int var45 = var34 < 0L ? -1 : 1;
         }

         if (var10 != null) {
            var11 = MountCache.getString(var10, 6, "SourceAppUserModelId");
         }
      }

      long var35 = 8260659336007L;
      if (8260659336007L == 0L) {
         boolean var46 = false;
      } else {
         int var47 = var35 < 0L ? -1 : 1;
      }

      if (var10 == null) {
         Pointer var36 = MountCache.getRequired(var1, 7, "GetSessions");
         boolean var38 = false;

         try {
            label258: {
               var38 = true;
               int var17 = MountCache.getInt(var36, 7, "Sessions.Size");

               for (int var18 = 0; var18 < var17; var18++) {
                  long var19 = 8260659336006L;
                  if (8260659336006L == 0L) {
                     boolean var48 = false;
                  } else {
                     int var49 = var19 < 0L ? -1 : 1;
                  }

                  if (var10 != null) {
                     var38 = false;
                     break label258;
                  }

                  PointerByReference var21 = new PointerByReference();
                  MountCache.check(MountCache.call(var36, 6, var18, var21), "Sessions.GetAt");
                  Pointer var22 = var21.getValue();
                  long var23 = 8260659336007L;
                  if (8260659336007L == 0L) {
                     boolean var50 = false;
                  } else {
                     int var51 = var23 < 0L ? -1 : 1;
                  }

                  if (var22 == null) {
                     long var25 = 8260659336006L;
                     if (8260659336006L == 0L) {
                        boolean var52 = false;
                     } else {
                        int var53 = var25 < 0L ? -1 : 1;
                     }
                  } else {
                     String var40 = MountCache.getString(var22, 6, "SourceAppUserModelId");
                     long var26 = 8260659336007L;
                     if (8260659336007L == 0L) {
                        boolean var54 = false;
                     } else {
                        int var55 = var26 < 0L ? -1 : 1;
                     }

                     if (this.X && !isSpotify(var40)) {
                        MountCache.release(var22);
                     } else {
                        var10 = var22;
                        var11 = var40;
                        long var29 = 8260659336006L;
                        if (8260659336006L == 0L) {
                           boolean var56 = false;
                        } else {
                           int var57 = var29 < 0L ? -1 : 1;
                        }
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
         if (8260659336007L == 0L) {
            boolean var58 = false;
         } else {
            int var59 = var39 < 0L ? -1 : 1;
         }

         MountCache.release(var36);
      }

      if (var10 != null) {
         long var37 = 8260659336007L;
         if (8260659336007L == 0L) {
            boolean var60 = false;
         } else {
            int var61 = var37 < 0L ? -1 : 1;
         }

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
      MountFactory.Data7.ensureSourceClassInitialized();

      try {
         Pointer var3 = MountCache.getRequired(var2, 9, "GetPlaybackInfo");

         boolean var4;
         try {
            var4 = MountData.TypeN.of(MountCache.getInt(var3, 7, "PlaybackStatus")) == MountData.TypeN.w;
         } finally {
            MountCache.release(var3);
         }

         return var4;
      } catch (MountCache.TypeD var9) {
         return false;
      }
   }

   
   private static Pointer L(Pointer var0, long var1) throws MountCache.TypeD {
      long var3 = 5624070888567L;
      MountFactory.Data7.ensureSourceClassInitialized();
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
            if (80442127783257L == 0L) {
               boolean var41 = false;
            } else {
               int var42 = var12 < 0L ? -1 : 1;
            }

            long var15 = 80442127783258L;
            if (80442127783258L == 0L) {
               boolean var43 = false;
            } else {
               int var44 = var15 < 0L ? -1 : 1;
            }

            if (var7 >= 3) {
               break;
            }

            PointerByReference var17 = new PointerByReference();
            MountCache.check(MountCache.call(var5, 6, var11, var17), "Sessions.GetAt");
            Pointer var18 = var17.getValue();
            if (var18 == null) {
               long var19 = 80442127783257L;
               if (80442127783257L == 0L) {
                  boolean var45 = false;
               } else {
                  int var46 = var19 < 0L ? -1 : 1;
               }
            } else {
               boolean var39 = false;

               try {
                  boolean var20 = isSpotify(MountCache.getString(var18, 6, "SourceAppUserModelId"));
                  boolean var21 = t(var3, var18);
                  long var22 = 80442127783258L;
                  if (80442127783258L == 0L) {
                     boolean var47 = false;
                  } else {
                     int var48 = var22 < 0L ? -1 : 1;
                  }

                  int var24 = var21 ? (var20 ? 3 : 2) : (var20 ? 1 : 0);
                  long var25 = 80442127783257L;
                  if (80442127783257L == 0L) {
                     boolean var49 = false;
                  } else {
                     int var50 = var25 < 0L ? -1 : 1;
                  }

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
         if (80442127783257L == 0L) {
            boolean var51 = false;
         } else {
            int var52 = var38 < 0L ? -1 : 1;
         }

         var8 = true;
         long var14 = 80442127783258L;
         if (80442127783258L == 0L) {
            boolean var53 = false;
         } else {
            int var54 = var14 < 0L ? -1 : 1;
         }

         var9 = false;
      } finally {
         if (var9) {
            long var29 = 80442127783257L;
            if (80442127783257L == 0L) {
               boolean var10000 = false;
            } else {
               int var40 = var29 < 0L ? -1 : 1;
            }

            MountCache.release(var5);
            if (!var8) {
               MountCache.release(var6);
            }
         }
      }

      MountCache.release(var5);
      return var6;
   }

   private static int C(long var0, Pointer var2) throws MountCache.TypeD {
      MountFactory.Data7.ensureSourceClassInitialized();
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

   private void k(Pointer var1, short var2, char var3, int var4) throws MountCache.TypeD {
      long var5 = 75287847427514L;
      long var7 = var5 ^ 90894292990821L;
      MountFactory.Data7.ensureSourceClassInitialized();
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
                  var14 = (0 != 0 ? 1 : 0);
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

   
   private static byte[] p(Pointer var0, long var1) throws MountCache.TypeD {
      MountFactory.Data7.ensureSourceClassInitialized();
      Pointer var3 = MountCache.getObject(var0, 15, "Thumbnail");
      if (var3 == null) {
         return null;
      } else {
         Object var7;
         try {
            Pointer var5 = MountCache.getRequired(var3, 6, "OpenReadAsync");
            Pointer var6 = MountCache.awaitObject(var5, 3000L, "OpenReadAsync");
            if (var6 != null) {
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
                        if (25098369984222L == 0L) {
                           boolean var10000 = false;
                        } else {
                           int var39 = var14 < 0L ? -1 : 1;
                        }

                        if (var13 > 0) {
                           var9.write(var10.getByteArray(0L, var13), 0, var13);
                        }

                        long var17 = 25098369984222L;
                        if (25098369984222L == 0L) {
                           boolean var40 = false;
                        } else {
                           int var41 = var17 < 0L ? -1 : 1;
                        }

                        if (var12 == 1) {
                           break;
                        }

                        if (var13 <= 0) {
                           long var19 = 25098369984223L;
                           if (25098369984223L == 0L) {
                              boolean var42 = false;
                           } else {
                              int var43 = var19 < 0L ? -1 : 1;
                           }
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
                  if (25098369984222L == 0L) {
                     boolean var44 = false;
                  } else {
                     int var45 = var38 < 0L ? -1 : 1;
                  }

                  MountCache.release(var37);
                  return var4;
               } finally {
                  MountCache.release(var6);
               }
            }

            var7 = null;
         } finally {
            MountCache.release(var3);
         }

         return (byte[])var7;
      }
   }

   boolean send(MountApi var1) throws MountCache.TypeD {
      Pointer var2 = this.v;
      if (var2 == null) {
         return false;
      } else {
         PointerByReference var3 = new PointerByReference();
         String var4;
         int var5;
         switch (var1) {
            case MountApi.Data4 var8:
               var4 = "TryPlayAsync";
               var5 = MountCache.call(var2, 10, var3);
               break;
            case MountApi.Data6 var9:
               var4 = "TryPauseAsync";
               var5 = MountCache.call(var2, 11, var3);
               break;
            case MountApi.Data3 var10:
               var4 = "TryTogglePlayPauseAsync";
               var5 = MountCache.call(var2, 20, var3);
               break;
            case MountApi.Data2 var11:
               var4 = "TrySkipNextAsync";
               var5 = MountCache.call(var2, 16, var3);
               break;
            case MountApi.Data5 var12:
               var4 = "TrySkipPreviousAsync";
               var5 = MountCache.call(var2, 17, var3);
               break;
            case MountApi.Data9 var13:
               var4 = "TryChangePlaybackPositionAsync";
               var5 = MountCache.call(var2, 24, Math.max(0L, var13.positionMs()) * 10000L, var3);
               break;
            case MountApi.Data8 var14:
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
