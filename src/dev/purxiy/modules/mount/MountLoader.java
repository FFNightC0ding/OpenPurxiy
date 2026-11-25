package dev.purxiy.modules.mount;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import javax.imageio.ImageIO;
import org.lwjgl.system.MemoryUtil;

public final class MountLoader {
   public static final int y = 0;
   public static final int d = 1;
   public static final int H = 2;
   public static final int g = 3;
   public static final int u = 4;
   public static final int c = 5;
   public static final int v = 6;
   public static final int e = 7;
   public static final int R = 8;
   public static final int j = 9;
   public static final int m = 10;
   public static final int E = 11;
   public static final int n = 12;
   public static final int W = 13;
   public static final int U = 14;
   public static final int i = 15;
   public static final int F = 16;
   public static final int s = 24;
   public static final int S = 0;
   public static final int K = 1;
   public static final int T = 2;
   public static final int O = 3;
   public static final int p = 4;
   public static final int l = 5;
   public static final int A = 6;
   public static final int w = 7;
   public static final int J = 8;
   public static final int r = 9;
   public static final int x = 0;
   public static final int h = 1;
   public static final int Z = 2;
   public static final int Y = 28;
   public final String o;
   public final float[] a;
   public final float[] V;
   public final int k;
   public final float[] q;
   public final float[] D;
   public final List<MountLoader.Data> B;
   public final List<MountLoader.DrawRange> P;
   public final List<MountLoader.MountModelData> L;
   public ByteBuffer X;
   public ByteBuffer I;
   public final int f;
   public final int z;
   private static final String[] t = new String[48];
   private static final long[] C = new long[]{
      -5036134466642675808L,
      -2697895711265815706L,
      8942479939127253719L,
      -2615052428865452510L,
      3795883562872198472L,
      4206867002545291786L,
      7691006835622500282L,
      -661221823785397438L,
      5599242307269431742L,
      -6341527304474068337L,
      8624812692532351238L,
      -7565385483330089906L,
      -4499623907455304500L,
      -422451517892491179L,
      6387255186854689573L,
      -2049822386847471821L,
      323477745694229009L,
      -435196892974475512L,
      -6017936597761462582L,
      7315635705061096837L,
      8770561058716729485L,
      -4474146338233312951L,
      726409392415560556L,
      -6355800479747189548L,
      2210144972459169098L,
      -7561687135916562775L,
      2515280650945934697L,
      787556837103566999L,
      2411291470411216935L,
      -7638549375079594792L,
      -5054884511303187472L,
      -5681236929329963192L,
      -7260114681774749863L,
      -1137229036283651117L,
      -2315269498900669830L,
      4835463991214288835L
   };
   private static final Integer[] G = new Integer[36];

   private MountLoader(
      String var1,
      float[] var2,
      float[] var3,
      List<MountLoader.Data> var4,
      List<MountLoader.DrawRange> var5,
      List<MountLoader.MountModelData> var6,
      ByteBuffer var7,
      ByteBuffer var8,
      int var9,
      int var10,
      int var11,
      float[] var12,
      float[] var13
   ) {
      this.o = var1;
      this.a = var2;
      this.V = var3;
      this.B = var4;
      this.P = var5;
      this.L = var6;
      this.X = var7;
      this.I = var8;
      this.f = var9;
      this.z = var10;
      this.k = var11;
      this.q = var12;
      this.D = var13;
   }

   public static CompletableFuture<MountLoader> load(String var0) {
      return MountWorker.submit(() -> {
         long var1 = 3266762754324L;

         try {
            return R(var1, var0);
         } catch (Exception var4) {
            throw new IllegalStateException("Could not load vehicle " + var0, var4);
         }
      });
   }

   public void releaseCpu() {
      if (this.X != null) {
         MemoryUtil.memFree(this.X);
      }

      if (this.I != null) {
         MemoryUtil.memFree(this.I);
      }

      this.X = null;
      this.I = null;

      for (MountLoader.MountModelData var2 : this.L) {
         if (var2.O != null) {
            for (ByteBuffer var6 : var2.O) {
               MemoryUtil.memFree(var6);
            }

            var2.O = null;
         }
      }
   }

   private static MountLoader R(long var0, String var2) throws IOException, DataFormatException {
      long var3 = 1980380623L;
      char var5 = '\uec84';
      long var6 = 3648939597209L;
      long var8 = 87199710826134L;
      MountFactory.MountSpec.ensureSourceClassInitialized();

      byte[] var10;
      try (InputStream var11 = MountLoader.class.getResourceAsStream("/assets/purxiy/garage/" + var2 + ".pgv")) {
         if (var11 == null) {
            throw new IOException("missing " + var2);
         }

         var10 = var11.readAllBytes();
      }

      ByteBuffer var66 = ByteBuffer.wrap(var10).order(ByteOrder.LITTLE_ENDIAN);
      byte[] var12 = new byte[4];
      var66.get(var12);
      int var13 = var12[0];
      long var14 = 3266762754323L;
      int var17 = var14 < 0L ? -1 : 1;
      if (var13 == 80) {
         long var17x = 3266762754324L;
         int var19 = var17x < 0L ? -1 : 1;
         if (var12[1] == 71) {
            int var65 = var12[2];
            long var19x = 3266762754324L;
            int var21 = var19x < 0L ? -1 : 1;
            if (var65 == 86) {
               long var21x = 3266762754324L;
               int var23 = var21x < 0L ? -1 : 1;
               if (var12[3] == 49) {
                  byte[] var23x = new byte[var66.getInt()];
                  var66.get(var23x);
                  JsonObject var24 = (JsonObject)new Gson().fromJson(new String(var23x, StandardCharsets.UTF_8), JsonObject.class);
                  int var25 = var66.getInt();
                  int var26 = var66.getInt();
                  byte[] var27 = new byte[var26];
                  var66.get(var27);
                  byte[] var28 = new byte[var25];
                  Inflater var29 = new Inflater();
                  var29.setInput(var27);

                  int var30;
                  for (var30 = 0; var30 < var25; var30 += var29.inflate(var28, var30, var25 - var30)) {
                     long var31 = 3266762754324L;
                     int var70 = var31 < 0L ? -1 : 1;
                     boolean var33 = var29.finished();
                     long var34 = 3266762754324L;
                     int var36 = var34 < 0L ? -1 : 1;
                     if (var33) {
                        break;
                     }
                  }

                  long var69 = 3266762754324L;
                  int var731 = var69 < 0L ? -1 : 1;
                  var29.end();
                  if (var30 != var25) {
                     throw new IOException("short mesh data");
                  }

                  float[] var70 = n(var3, var5, var24.getAsJsonArray("lo"));
                  float[] var71 = n(var3, var5, var24.getAsJsonArray("hi"));
                  int var35 = var24.get("verts").getAsInt();
                  int var36 = var24.get("indices").getAsInt();
                  ArrayList var37 = new ArrayList();

                  for (JsonElement var39 : var24.getAsJsonArray("materials")) {
                     long var40 = 3266762754323L;
                     int var42 = var40 < 0L ? -1 : 1;
                     var37.add(S(var6, var39.getAsJsonObject()));
                  }

                  ArrayList var72 = new ArrayList();
                  float[][] var73x = new float[var24.getAsJsonArray("draws").size()][];
                  int var74 = 0;

                  for (JsonElement var42 : var24.getAsJsonArray("draws")) {
                     JsonObject var43 = var42.getAsJsonObject();
                     var72.add(
                        new MountLoader.DrawRange(
                           var43.get("mat").getAsInt(),
                           var43.get("part").getAsInt(),
                           var43.get("base").getAsInt(),
                           var43.get("verts").getAsInt(),
                           var43.get("first").getAsInt(),
                           var43.get("count").getAsInt()
                        )
                     );
                     var73x[var74++] = n(var3, var5, var43.getAsJsonArray("uv"));
                  }

                  long var75 = 3266762754323L;
                  int var821 = var75 < 0L ? -1 : 1;
                  ByteBuffer var44 = ByteBuffer.wrap(var28).order(ByteOrder.LITTLE_ENDIAN);
                  short[][] var45 = new short[7][var35];

                  for (int var46 = 0; var46 < 7; var46++) {
                     short var47 = 0;

                     for (int var48 = 0; var48 < var35; var48++) {
                        var65 = var47 + var44.getShort();
                        long var49 = 3266762754324L;
                        int var49x = var49 < 0L ? -1 : 1;
                        var47 = (short)var65;
                        var45[var46][var48] = var47;
                     }

                     long var78 = 3266762754324L;
                     int var50 = var78 < 0L ? -1 : 1;
                  }

                  byte[] var76 = new byte[var35];
                  byte[] var77 = new byte[var35];
                  byte[] var79 = new byte[var35];
                  var44.get(var76);
                  var44.get(var77);
                  var44.get(var79);
                  ByteBuffer var80 = MemoryUtil.memAlloc(var35 * 28).order(ByteOrder.nativeOrder());
                  float var50 = (var71[0] - var70[0]) / 65535.0F;
                  float var51 = (var71[1] - var70[1]) / 65535.0F;
                  float var52 = (var71[2] - var70[2]) / 65535.0F;

                  for (int var53 = 0; var53 < var72.size(); var53++) {
                     MountLoader.DrawRange var54 = (MountLoader.DrawRange)var72.get(var53);
                     float[] var55 = var73x[var53];
                     float var56 = var55[2] / 65535.0F;
                     float var57 = var55[3] / 65535.0F;

                     for (int var58 = var54.k; var58 < var54.k + var54.i; var58++) {
                        var80.putFloat(var70[0] + (var45[0][var58] & '\uffff') * var50);
                        var80.putFloat(var70[1] + (var45[1][var58] & '\uffff') * var51);
                        var80.putFloat(var70[2] + (var45[2][var58] & '\uffff') * var52);
                        var80.putFloat(var55[0] + (var45[3][var58] & '\uffff') * var56);
                        var80.putFloat(var55[1] + (var45[4][var58] & '\uffff') * var57);
                        int var59 = var45[5][var58] & '\uffff';
                        short var60 = var45[6][var58];
                        long var61 = 3266762754324L;
                        int var63 = var61 < 0L ? -1 : 1;
                        var63 = var60 & '\uffff';
                        var80.put((byte)(var59 >>> 8)).put((byte)var59).put((byte)(var63 >>> 8)).put((byte)var63);
                        var80.put(var76[var58]).put(var77[var58]).put(var79[var58]).put((byte)0);
                     }

                     long var86 = 3266762754324L;
                     int var101 = var86 < 0L ? -1 : 1;
                  }

                  long var81 = 3266762754324L;
                  int var921 = var81 < 0L ? -1 : 1;
                  var80.flip();
                  ByteBuffer var82x = MemoryUtil.memAlloc(var36 * 4).order(ByteOrder.nativeOrder());
                  int var83 = 0;

                  for (int var84 = 0; var84 < var36; var84++) {
                     var83 += var44.getInt();
                     long var87 = 3266762754324L;
                     int var102 = var87 < 0L ? -1 : 1;
                     var82x.putInt(var83);
                  }

                  var82x.flip();
                  long var85 = 3266762754323L;
                  int var99 = var85 < 0L ? -1 : 1;
                  ArrayList var88 = new ArrayList();

                  for (JsonElement var91 : var24.getAsJsonArray("textures")) {
                     JsonObject var62 = var91.getAsJsonObject();
                     byte[] var93 = new byte[var62.get("size").getAsInt()];
                     var66.get(var93);
                     var88.add(c(var93, var62.get("kind").getAsString(), var8));
                  }

                  long var90 = 3266762754323L;
                  int var106 = var90 < 0L ? -1 : 1;
                  JsonObject var92x = var24.getAsJsonObject("meta").getAsJsonObject("ground");
                  return new MountLoader(
                     var2,
                     var70,
                     var71,
                     var37,
                     var72,
                     var88,
                     var80,
                     var82x,
                     var35,
                     var36,
                     var92x.get("tex").getAsInt(),
                     n(var3, var5, var92x.getAsJsonArray("lo")),
                     n(var3, var5, var92x.getAsJsonArray("hi"))
                  );
               }
            }
         }
      }

      throw new IOException("not a vehicle file");
   }

   private static MountLoader.Data S(long var0, JsonObject var2) {
      long var3 = 1980380623L;
      char var5 = '\uec84';
      String var6 = var2.get("alpha").getAsString();
      return new MountLoader.Data(
         var2.get("name").getAsString(),
         var2.get("cls").getAsInt(),
         n(var3, var5, var2.getAsJsonArray("base")),
         var2.get("metal").getAsFloat(),
         var2.get("rough").getAsFloat(),
         var2.get("coat").getAsFloat(),
         n(var3, var5, var2.getAsJsonArray("emissive")),
         var2.get("emissive_gain").getAsFloat(),
         var6.equals("BLEND") ? 2 : (var6.equals("MASK") ? 1 : 0),
         var2.get("cutoff").getAsFloat(),
         var2.get("normal_scale").getAsFloat(),
         var2.get("tint").getAsFloat(),
         var2.get("tex_base").getAsInt(),
         var2.get("tex_mr").getAsInt(),
         var2.get("tex_normal").getAsInt(),
         var2.get("tex_emissive").getAsInt()
      );
   }

   private static float[] n(long var0, char var2, JsonArray var3) {
      float[] var4 = new float[var3.size()];
      MountFactory.MountSpec.ensureSourceClassInitialized();

      for (int var5 = 0; var5 < var4.length; var5++) {
         long var6 = 1980380623L;
         int var8 = var6 < 0L ? -1 : 1;
         var4[var5] = var3.get(var5).getAsFloat();
      }

      long var9 = 1980380622L;
      int var7 = var9 < 0L ? -1 : 1;
      return var4;
   }

   private static MountLoader.MountModelData c(byte[] var0, String var1, long var2) throws IOException {
      long var4 = 457026356011L;
      byte var6 = 75;
      MountFactory.MountSpec.ensureSourceClassInitialized();
      BufferedImage var7 = ImageIO.read(new ByteArrayInputStream(var0));
      if (var7 == null) {
         throw new IOException("undecodable texture");
      } else {
         int var8 = var7.getWidth();
         int var9 = 32450;
         int var10 = var7.getHeight();
         int[] var11 = var7.getRGB(0, 0, var8, var10, null, 0, var8);
         int var12 = 32 - Integer.numberOfLeadingZeros(Math.min(var8, var10));
         ByteBuffer[] var13 = new ByteBuffer[var12];
         boolean var14 = var1.equals("normal");
         int var15 = var8;
         int var16 = var10;

         for (int var17 = 0; var17 < var12; var17++) {
            ByteBuffer var18 = MemoryUtil.memAlloc(var15 * var16 * 4);

            for (int var22 : var11) {
               long var23 = 87199710826134L;
               int var25 = var23 < 0L ? -1 : 1;
               var18.put((byte)(var22 >> 16)).put((byte)(var22 >> 8)).put((byte)var22).put((byte)(var22 >>> 24));
            }

            long var28 = 87199710826134L;
            int var28x = var28 < 0L ? -1 : 1;
            var18.flip();
            long var29 = 87199710826133L;
            int var30 = var29 < 0L ? -1 : 1;
            var13[var17] = var18;
            var9 = var17 + 1;
            long var24 = 87199710826134L;
            int var32 = var24 < 0L ? -1 : 1;
            if (var9 < var12) {
               var11 = e(var11, var15, var16, var4, var14, var6);
               var15 = Math.max(1, var15 >> 1);
               var16 = Math.max(1, var16 >> 1);
            }
         }

         return new MountLoader.MountModelData(var8, var10, var1, var13);
      }
   }

   private static int[] e(int[] var0, int var1, int var2, long var3, boolean var5, byte var6) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      int var7 = Math.max(1, var1 >> 1);
      int var8 = Math.max(1, var2 >> 1);
      int[] var9 = new int[var7 * var8];

      for (int var10 = 0; var10 < var8; var10++) {
         int var11 = Math.min(var2 - 1, var10 * 2);
         int var12 = Math.min(var2 - 1, var10 * 2 + 1);

         for (int var13 = 0; var13 < var7; var13++) {
            int var14 = Math.min(var1 - 1, var13 * 2);
            byte var15 = 75;
            int var16 = Math.min(var1 - 1, var13 * 2 + 1);
            int var17 = var0[var11 * var1 + var14];
            int var18 = var0[var11 * var1 + var16];
            int var19 = var0[var12 * var1 + var14];
            int var20 = var0[var12 * var1 + var16];
            int var21 = (var17 >>> 24) + (var18 >>> 24) + (var19 >>> 24) + (var20 >>> 24) + 2 >> 2;
            int var22 = (var17 >> 16 & 0xFF) + (var18 >> 16 & 0xFF) + (var19 >> 16 & 0xFF) + (var20 >> 16 & 0xFF) + 2 >> 2;
            int var23 = (var17 >> 8 & 0xFF) + (var18 >> 8 & 0xFF) + (var19 >> 8 & 0xFF) + (var20 >> 8 & 0xFF) + 2 >> 2;
            int var24 = (var17 & 0xFF) + (var18 & 0xFF) + (var19 & 0xFF) + (var20 & 0xFF) + 2 >> 2;
            long var25 = 457026356011L;
            int var27 = var25 < 0L ? -1 : 1;
            long var27x = 457026356011L;
            int var29 = var27x < 0L ? -1 : 1;
            if (var5) {
               float var29x = var22 / 127.5F - 1.0F;
               float var30 = var23 / 127.5F - 1.0F;
               float var31 = var24 / 127.5F - 1.0F;
               long var32 = 457026356010L;
               int var35 = var32 < 0L ? -1 : 1;
               float var35x = (float)Math.sqrt(var29x * var29x + var30 * var30 + var31 * var31);
               if (var35x > 1.0E-4F) {
                  var22 = Math.clamp((long)Math.round((var29x / var35x + 1.0F) * 127.5F), 0, 255);
                  var23 = Math.clamp((long)Math.round((var30 / var35x + 1.0F) * 127.5F), 0, 255);
                  var24 = Math.clamp((long)Math.round((var31 / var35x + 1.0F) * 127.5F), 0, 255);
               }
            }

            var9[var10 * var7 + var13] = var21 << 24 | var22 << 16 | var23 << 8 | var24;
         }

         long var36 = 457026356011L;
         int var36x = var36 < 0L ? -1 : 1;
      }

      return var9;
   }

   public record Data(String E, int i, float[] g, float K, float N, float A, float[] t, float X, int L, float z, float J, float C, int T, int B, int f, int G) {
      public String name() {
         return this.E;
      }

      public int cls() {
         return this.i;
      }

      public float[] base() {
         return this.g;
      }

      public float metal() {
         return this.K;
      }

      public float rough() {
         return this.N;
      }

      public float coat() {
         return this.A;
      }

      public float[] emissive() {
         return this.t;
      }

      public float gain() {
         return this.X;
      }

      public int alpha() {
         return this.L;
      }

      public float cutoff() {
         return this.z;
      }

      public float normalScale() {
         return this.J;
      }

      public float tint() {
         return this.C;
      }

      public int texBase() {
         return this.T;
      }

      public int texMr() {
         return this.B;
      }

      public int texNormal() {
         return this.f;
      }

      public int texEmissive() {
         return this.G;
      }

      public String stringState33() {
         return this.E;
      }

      public int integerState35() {
         return this.i;
      }

      public float[] floatArrayState21() {
         return this.g;
      }

      public float floatState50() {
         return this.K;
      }

      public float floatState51() {
         return this.N;
      }

      public float floatState56() {
         return this.A;
      }

      public float[] floatArrayState38() {
         return this.t;
      }

      public float floatState17() {
         return this.X;
      }

      public int integerState1() {
         return this.L;
      }

      public float floatState19() {
         return this.z;
      }

      public float floatState10() {
         return this.J;
      }

      public float floatState16() {
         return this.C;
      }

      public int integerState36() {
         return this.T;
      }

      public int integerState32() {
         return this.B;
      }

      public int integerState3() {
         return this.f;
      }

      public int integerState18() {
         return this.G;
      }
   }

   public record DrawRange(int d, int F, int k, int i, int h, int n) {
      public int material() {
         return this.d;
      }

      public int part() {
         return this.F;
      }

      public int baseVertex() {
         return this.k;
      }

      public int vertexCount() {
         return this.i;
      }

      public int firstIndex() {
         return this.h;
      }

      public int indexCount() {
         return this.n;
      }

      public int integerState34() {
         return this.d;
      }

      public int integerState22() {
         return this.F;
      }

      public int integerState46() {
         return this.k;
      }

      public int integerState35() {
         return this.i;
      }

      public int integerState30() {
         return this.h;
      }

      public int integerState8() {
         return this.n;
      }
   }

   public static final class MountModelData {
      public final int r;
      public final int A;
      public final String Y;
      public ByteBuffer[] O;

      MountModelData(int var1, int var2, String var3, ByteBuffer[] var4) {
         this.r = var1;
         this.A = var2;
         this.Y = var3;
         this.O = var4;
      }
   }
}
