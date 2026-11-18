// Sourced By DevFFNightmare
package dev.purxiy.modules.spotify;

import dev.purxiy.core.ImageFiles;

import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.texture.TextureLoader;
import dev.purxiy.ui.Icons;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.class_1011;
import net.minecraft.class_1011.class_1012;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

final class SpotifyUpdater3 {
   private static final int w = 256;
   private static final float p = 1.6F;
   private static final ExecutorService N = ClientServices.visualExecutor("Purxiy Album Art");
   
   private TextureLoader Z;
   private String d = "";
   
   private TextureLoader G;
   private float u = 1.0F;
   
   private CompletableFuture<SpotifyUpdater3.TypeI> z;
   private String s = "";
   private String j = "";
   private float q;
   private int W = Icons.PURPLE;
   private int e = Icons.PURPLE;

   private static final String[] b = new String[9];


   private static final long[] g = new long[]{6988715207522506513L, 2762925726644078495L};
   private static final Long[] h = new Long[2];

   void want(String var1) {
      if (!var1.equals(this.j)) {
         this.j = var1;
         this.q = 0.0F;
      }
   }

   void offer(String var1, byte[] var2) {
      if (!var1.equals(this.d) && !var1.equals(this.s)) {
         this.s = var1;
         CompletableFuture var3 = this.z;
         this.z = CompletableFuture.supplyAsync(() -> {
            byte var2x = 0;
            long var3x = 54232200282677L;
            return U(var1, (char)var2x, var2, var3x);
         }, N);
         if (var3 != null) {
            var3.thenAccept(var0 -> {});
         }
      }
   }

   void update(float var1) {
      long var2 = 120015613290240L;
      long var4 = var2 ^ 12513634148389L;
      CompletableFuture var6 = this.z;
      if (var6 != null && var6.isDone()) {
         this.z = null;
         this.s = "";

         try {
            SpotifyUpdater3.TypeI var7 = (SpotifyUpdater3.TypeI)var6.join();
            if (var7.key().equals(this.j)) {
               this.Z(TextureLoader.of("album art", var7.image()), var7.key(), var7.accent(), var4);
            } else {
               var7.image().close();
            }
         } catch (Exception var8) {
            ClientServices.LOGGER.debug("Could not decode album art", var8);
         }
      }

      if (!this.j.equals(this.d)) {
         this.q += var1;
         if (this.q > 1.6F && this.Z != null) {
            this.Z(null, this.j, Icons.PURPLE, var4);
         }
      }

      if (this.u < 1.0F) {
         this.u = Math.min(1.0F, this.u + var1 / 0.45F);
         if (this.u >= 1.0F && this.G != null) {
            this.G.close();
            this.G = null;
         }
      }

      if (this.Z == null) {
         this.e = Icons.PURPLE;
      }

      this.W = BlendMode.lerp(this.W, this.e, Math.min(1.0F, var1 * 3.5F));
   }

   private void Z( TextureLoader var1, String var2, int var3, long var4) {
      SpotifyCurve.ensureSourceClassInitialized();
      long var6 = 112454076499748L;
      if (112454076499748L == 0L) {
         boolean var10000 = false;
      } else {
         int var11 = var6 < 0L ? -1 : 1;
      }

      if (this.G != null) {
         this.G.close();
      }

      long var9 = 112454076499748L;
      if (112454076499748L == 0L) {
         boolean var12 = false;
      } else {
         int var13 = var9 < 0L ? -1 : 1;
      }

      this.G = this.Z;
      this.Z = var1;
      this.d = var2;
      this.u = this.G == null && var1 == null ? 1.0F : 0.0F;
      this.e = var3;
   }

   void clear() {
      long var1 = 28428111457182L;
      long var3 = var1 ^ 140308766829755L;
      this.j = "";
      if (this.Z != null || this.G != null) {
         this.Z(null, "", Icons.PURPLE, var3);
      }
   }

   
   TextureLoader current() {
      return this.Z;
   }

   
   TextureLoader previous() {
      return this.G;
   }

   float fade() {
      return this.u;
   }

   int accent() {
      return this.W;
   }

   private static SpotifyUpdater3.TypeI U(String var0, char var1, byte[] var2, long var3) {
      long var5 = 54232200282677L;
      long var7 = var5 ^ 121605698863512L;
      long var9 = var5 ^ 43415120647471L;
      SpotifyCurve.ensureSourceClassInitialized();
      if (var2.length > 8388608) {
         throw new IllegalArgumentException("Cover size limit");
      } else {
         ByteBuffer var11 = MemoryUtil.memAlloc(var2.length);

         SpotifyUpdater3.TypeI var36;
         try {
            MemoryStack var12 = MemoryStack.stackPush();

            try {
               label181: {
                  var11.put(var2).flip();
                  IntBuffer var13 = var12.mallocInt(1);
                  IntBuffer var14 = var12.mallocInt(1);
                  IntBuffer var15 = var12.mallocInt(1);
                  if (STBImage.stbi_info_from_memory(var11, var13, var14, var15) && var13.get(0) > 0 && var14.get(0) > 0) {
                     boolean var16 = false;
                     if (var13.get(0) <= 8192 && var14.get(0) <= 8192 && (long)var13.get(0) * var14.get(0) <= 16777216L) {
                        ByteBuffer var17 = STBImage.stbi_load_from_memory(var11, var13, var14, var15, 4);
                        var16 = false;
                        if (var17 == null) {
                           throw new IllegalStateException(new IOException("Unreadable cover: " + STBImage.stbi_failure_reason()));
                        }

                        boolean var18 = false;

                        SpotifyUpdater3.TypeI var19;
                        try {
                           var18 = true;
                           class_1011 var20 = R(var17, var13.get(0), var7, var14.get(0));
                           var19 = new SpotifyUpdater3.TypeI(var0, var20, U(var20, var9));
                           var18 = false;
                        } finally {
                           if (var18) {
                              STBImage.stbi_image_free(var17);
                           }
                        }

                        STBImage.stbi_image_free(var17);
                        var36 = var19;
                        break label181;
                     }
                  }

                  throw new IllegalArgumentException("Cover dimensions limit");
               }
            } catch (Throwable var33) {
               if (var12 != null) {
                  try {
                     var12.close();
                  } catch (Throwable var31) {
                     var33.addSuppressed(var31);
                  }
               }

               throw var33;
            }

            if (var12 != null) {
               var12.close();
            }
         } finally {
            MemoryUtil.memFree(var11);
         }

         return var36;
      }
   }

   private static class_1011 R(ByteBuffer var0, int var1, long var2, int var4) {
      int var5 = 13056;
      int var6 = Math.min(var1, var4);
      int var7 = (var1 - var6) / 2;
      SpotifyCurve.ensureSourceClassInitialized();
      int var8 = (var4 - var6) / 2;
      int var9 = Math.min(256, var6);
      class_1011 var10 = new class_1011(class_1012.field_4997, var9, var9, false);
      double var11 = (double)var6 / var9;
      int var13 = 0;

      while (var13 < var9) {
         int var14 = var8 + (int)Math.floor(var13 * var11);
         int var15 = Math.max(var14 + 1, var8 + (int)Math.floor((var13 + 1) * var11));

         for (int var16 = 0; var16 < var9; var16++) {
            int var17 = var7 + (int)Math.floor(var16 * var11);
            var5 = Math.max(var17 + 1, var7 + (int)Math.floor((var16 + 1) * var11));
            long var18 = 105327151498157L;
            if (105327151498157L == 0L) {
               boolean var10000 = false;
            } else {
               int var45 = var18 < 0L ? -1 : 1;
            }

            int var21 = var5;
            long var22 = 0L;
            long var24 = 0L;
            long var26 = 0L;
            long var28 = 0L;
            int var30 = 0;

            for (int var31 = var14; var31 < var15; var31++) {
               long var32 = 105327151498156L;
               if (105327151498156L == 0L) {
                  boolean var46 = false;
               } else {
                  int var47 = var32 < 0L ? -1 : 1;
               }

               for (int var35 = var17; var35 < var21; var35++) {
                  int var36 = (var31 * var1 + var35) * 4;
                  var22 += var0.get(var36) & 255;
                  var24 += var0.get(var36 + 1) & 255;
                  long var37 = 105327151498157L;
                  if (105327151498157L == 0L) {
                     boolean var48 = false;
                  } else {
                     int var49 = var37 < 0L ? -1 : 1;
                  }

                  var26 += var0.get(var36 + 2) & 255;
                  var28 += var0.get(var36 + 3) & 255;
                  var30++;
               }

               long var44 = 105327151498156L;
               if (105327151498156L == 0L) {
                  boolean var50 = false;
               } else {
                  int var51 = var44 < 0L ? -1 : 1;
               }
            }

            long var43 = 105327151498156L;
            if (105327151498156L == 0L) {
               boolean var52 = false;
            } else {
               int var53 = var43 < 0L ? -1 : 1;
            }

            var10.method_61941(var16, var13, (int)(var28 / var30) << 24 | (int)(var22 / var30) << 16 | (int)(var24 / var30) << 8 | (int)(var26 / var30));
         }

         var13++;
         long var42 = 105327151498156L;
         if (105327151498156L == 0L) {
            boolean var54 = false;
         } else {
            int var55 = var42 < 0L ? -1 : 1;
         }
      }

      long var41 = 105327151498157L;
      if (105327151498157L == 0L) {
         boolean var56 = false;
      } else {
         int var57 = var41 < 0L ? -1 : 1;
      }

      return var10;
   }

   private static int U(class_1011 var0, long var1) {
      long var3 = 42242983954796L;
      long var5 = 52639229157719L;
      byte var7 = 24;
      float[] var8 = new float[var7];
      float[] var9 = new float[var7];
      float[] var10 = new float[var7];
      SpotifyCurve.ensureSourceClassInitialized();
      float[] var11 = new float[var7];
      float[] var12 = new float[3];
      float var13 = 0.0F;

      for (byte var14 = 0; var14 < var0.method_4323(); var14 = (byte)(var14 + 2)) {
         long var15 = 24389874652954L;
         if (24389874652954L == 0L) {
            boolean var10000 = false;
         } else {
            int var51 = var15 < 0L ? -1 : 1;
         }

         for (byte var18 = 0; var18 < var0.method_4307(); var18 = (byte)(var18 + 2)) {
            int var19 = var0.method_61940(var18, var14);
            long var20 = 24389874652953L;
            if (24389874652953L == 0L) {
               boolean var52 = false;
            } else {
               int var53 = var20 < 0L ? -1 : 1;
            }

            float var22 = (var19 >> 16 & 0xFF) / 255.0F;
            float var23 = (var19 >> 8 & 0xFF) / 255.0F;
            float var24 = (var19 & 0xFF) / 255.0F;
            long var25 = 24389874652954L;
            if (24389874652954L == 0L) {
               boolean var54 = false;
            } else {
               int var55 = var25 < 0L ? -1 : 1;
            }

            m(var5, var22, var23, var24, var12);
            long var27 = 24389874652953L;
            if (24389874652953L == 0L) {
               boolean var56 = false;
            } else {
               int var57 = var27 < 0L ? -1 : 1;
            }

            if (!(var12[1] < 0.2F)) {
               float var30 = var12[2];
               long var31 = 24389874652953L;
               if (24389874652953L == 0L) {
                  boolean var58 = false;
               } else {
                  int var59 = var31 < 0L ? -1 : 1;
               }

               if (!(var30 < 0.18F)) {
                  float var33 = var12[1] * var12[1] * var12[2];
                  int var34 = Math.min(var7 - 1, (int)(var12[0] * var7));
                  var8[var34] += var33;
                  var9[var34] += var22 * var33;
                  var10[var34] += var23 * var33;
                  var11[var34] += var24 * var33;
                  var13 += var33;
               }
            }
         }

         long var39 = 24389874652954L;
         if (24389874652954L == 0L) {
            boolean var60 = false;
         } else {
            int var61 = var39 < 0L ? -1 : 1;
         }
      }

      long var37 = 24389874652953L;
      if (24389874652953L == 0L) {
         boolean var62 = false;
      } else {
         int var63 = var37 < 0L ? -1 : 1;
      }

      int var40 = -1;
      float var41 = 0.0F;

      for (int var42 = 0; var42 < var7; var42++) {
         float var21 = var8[var42];
         long var44 = 24389874652953L;
         if (24389874652953L == 0L) {
            boolean var64 = false;
         } else {
            int var65 = var44 < 0L ? -1 : 1;
         }

         float var46 = var21 + 0.5F * (var8[(var42 + var7 - 1) % var7] + var8[(var42 + 1) % var7]);
         long var26 = 24389874652954L;
         if (24389874652954L == 0L) {
            boolean var66 = false;
         } else {
            int var67 = var26 < 0L ? -1 : 1;
         }

         long var29 = 24389874652953L;
         if (24389874652953L == 0L) {
            boolean var68 = false;
         } else {
            int var69 = var29 < 0L ? -1 : 1;
         }

         if (var46 > var41) {
            var41 = var46;
            var40 = var42;
         }
      }

      int var17 = var0.method_4307();
      long var43 = 24389874652954L;
      if (24389874652954L == 0L) {
         boolean var70 = false;
      } else {
         int var71 = var43 < 0L ? -1 : 1;
      }

      var17 /= 2;
      long var45 = 24389874652954L;
      if (24389874652954L == 0L) {
         boolean var72 = false;
      } else {
         int var73 = var45 < 0L ? -1 : 1;
      }

      int var47 = var17 * (var0.method_4323() / 2);
      long var48 = 24389874652953L;
      if (24389874652953L == 0L) {
         boolean var74 = false;
      } else {
         int var75 = var48 < 0L ? -1 : 1;
      }

      if (var40 >= 0 && !(var8[var40] <= 0.0F)) {
         long var28 = 24389874652953L;
         if (24389874652953L == 0L) {
            boolean var76 = false;
         } else {
            int var77 = var28 < 0L ? -1 : 1;
         }

         float var49 = var47 * 0.012F;
         long var32 = 24389874652954L;
         if (24389874652954L == 0L) {
            boolean var78 = false;
         } else {
            int var79 = var32 < 0L ? -1 : 1;
         }

         if (!(var13 < var49)) {
            float var50 = var9[var40] / var8[var40];
            float var35 = var10[var40] / var8[var40];
            float var36 = var11[var40] / var8[var40];
            m(var5, var50, var35, var36, var12);
            return J(var12[0], Math.max(0.42F, Math.min(0.82F, var12[1])), var3, Math.max(0.8F, Math.min(0.98F, var12[2])));
         }
      }

      return Icons.PURPLE;
   }

   private static void m(long var0, float var2, float var3, float var4, float[] var5) {
      float var6 = Math.max(var2, Math.max(var3, var4));
      SpotifyCurve.ensureSourceClassInitialized();
      float var7 = Math.min(var2, Math.min(var3, var4));
      float var8 = var6 - var7;
      long var9 = 52639229157719L;
      if (52639229157719L == 0L) {
         boolean var10000 = false;
      } else {
         int var21 = var9 < 0L ? -1 : 1;
      }

      float var12;
      if (var8 <= 1.0E-6F) {
         long var13 = 52639229157719L;
         if (52639229157719L == 0L) {
            boolean var22 = false;
         } else {
            int var23 = var13 < 0L ? -1 : 1;
         }

         var12 = 0.0F;
      } else if (var6 == var2) {
         float var18 = (var3 - var4) / var8 + (var3 < var4 ? 6 : 0);
         long var14 = 52639229157718L;
         if (52639229157718L == 0L) {
            boolean var24 = false;
         } else {
            int var25 = var14 < 0L ? -1 : 1;
         }

         var12 = var18 / 6.0F;
      } else if (var6 == var3) {
         long var19 = 52639229157719L;
         if (52639229157719L == 0L) {
            boolean var26 = false;
         } else {
            int var27 = var19 < 0L ? -1 : 1;
         }

         var12 = ((var4 - var2) / var8 + 2.0F) / 6.0F;
      } else {
         var12 = ((var2 - var3) / var8 + 4.0F) / 6.0F;
      }

      long var20 = 52639229157718L;
      if (52639229157718L == 0L) {
         boolean var28 = false;
      } else {
         int var29 = var20 < 0L ? -1 : 1;
      }

      var5[0] = var12;
      long var16 = 52639229157719L;
      if (52639229157719L == 0L) {
         boolean var30 = false;
      } else {
         int var31 = var16 < 0L ? -1 : 1;
      }

      var5[1] = var6 <= 0.0F ? 0.0F : var8 / var6;
      var5[2] = var6;
   }

   private static int J(float var0, float var1, long var2, float var4) {
      SpotifyCurve.ensureSourceClassInitialized();
      int var5 = (int)Math.floor(var0 * 6.0F) % 6;
      float var6 = var0 * 6.0F - (float)Math.floor(var0 * 6.0F);
      float var7 = var4 * (1.0F - var1);
      float var8 = var4 * (1.0F - var6 * var1);
      float var9 = var4 * (1.0F - (1.0F - var6) * var1);
      float var10;
      float var11;
      float var12;
      switch (var5) {
         case 0:
            var10 = var4;
            var11 = var9;
            var12 = var7;
            long var13 = 42242983954796L;
            if (42242983954796L == 0L) {
               boolean var35 = false;
            } else {
               int var36 = var13 < 0L ? -1 : 1;
            }
            break;
         case 1:
            var10 = var8;
            var11 = var4;
            var12 = var7;
            long var16 = 42242983954795L;
            if (42242983954795L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var16 < 0L ? -1 : 1;
            }
            break;
         case 2:
            var10 = var7;
            var11 = var4;
            var12 = var9;
            long var19 = 42242983954796L;
            if (42242983954796L == 0L) {
               boolean var31 = false;
            } else {
               int var32 = var19 < 0L ? -1 : 1;
            }
            break;
         case 3:
            var10 = var7;
            var11 = var8;
            var12 = var4;
            long var22 = 42242983954796L;
            if (42242983954796L == 0L) {
               boolean var29 = false;
            } else {
               int var30 = var22 < 0L ? -1 : 1;
            }
            break;
         case 4:
            var10 = var9;
            var11 = var7;
            var12 = var4;
            long var25 = 42242983954795L;
            if (42242983954795L == 0L) {
               boolean var10000 = false;
            } else {
               int var28 = var25 < 0L ? -1 : 1;
            }
            break;
         default:
            var10 = var4;
            var11 = var7;
            var12 = var8;
      }

      return BlendMode.argb(1.0F, var10, var11, var12);
   }

   static void stopDecoder() {
      long var0 = 27928302926123L;
      long var2 = var0 ^ 115495342120207L;
      N.shutdown();
      g(N, var2, "album decoder");
   }

   private static void g(ExecutorService var0, long var1, String var3) {
      SpotifyCurve.ensureSourceClassInitialized();
      long var4 = 123610404758564L;
      if (123610404758564L == 0L) {
         boolean var10000 = false;
      } else {
         int var9 = var4 < 0L ? -1 : 1;
      }

      try {
         if (!var0.awaitTermination(6L, TimeUnit.SECONDS)) {
            var0.shutdownNow();
            throw new IllegalStateException(var3 + " did not stop");
         }
      } catch (InterruptedException var8) {
         var0.shutdownNow();
         Thread.currentThread().interrupt();
         throw new IllegalStateException(var3 + " interrupted");
      }
   }

   void closeOwned() {
      CompletableFuture var1 = this.z;
      this.z = null;
      if (var1 != null && var1.isDone() && !var1.isCompletedExceptionally() && !var1.isCancelled()) {
         ((SpotifyUpdater3.TypeI)var1.join()).image().close();
      }

      if (this.Z != null) {
         this.Z.close();
      }

      if (this.G != null) {
         this.G.close();
      }

      this.Z = null;
      this.G = null;
   }

private static long b(int var0, long var1) {
      byte var3 = 0;
      if (h[var3] == null) {
         h[var3] = g[var3] ^ 6988715207539283729L;
      }

      return h[var3];
   }

private record TypeI(String Y, class_1011 z, int C) {
      public String key() {
         return this.Y;
      }

      public class_1011 image() {
         return this.z;
      }

      public int accent() {
         return this.C;
      }

      public String stringState8() {
         return this.Y;
      }

      public class_1011 minecraftObjectState1() {
         return this.z;
      }

      public int integerState50() {
         return this.C;
      }
   }
}
