package dev.purxiy.modules.profile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1132;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_642;
import net.minecraft.class_746;

public final class ProfileUpdater {
   private static final Gson Y = new GsonBuilder().disableHtmlEscaping().create();
   private static final long B = 60000000000L;
   private static final double g = 300.0;
   private static final float G = 5.0F;
   private static final double V = 12.0;
   private static final double F = 2.0;
   private static final ExecutorService w = ClientServices.visualExecutor("Purxiy Profile IO");
   private static ProfileLoader y;
   private static UUID A;
   private static ProfileLoader J;
   private static boolean x;
   private static long K = 0L;
   private static long M = 0L;
   private static long T = 0L;
   private static ProfileLoader.TypeQ O;
   private static ProfileLoader2 q;
   private static ProfileLoader.TypeB u;
   private static LocalDate m = LocalDate.now();
   private static long X = 0L;
   private static class_243 j;
   private static String l;
   private static float b;
   private static float Q;
   private static long f = 0L;
   private static boolean p = true;
   private static float R;
   private static float U;
   private static long S = 0L;
   private static final Int2FloatOpenHashMap h = new Int2FloatOpenHashMap();
   private static final long a = System.nanoTime();
   private static final double[] z = new double[21];
   private static final double[] c = new double[21];
   private static final long[] W = new long[21];
   private static int L = 0;
   private static int C = 0;
   private static ProfileUpdater.TypeN D;
   static ProfileModule n;
   private static ModuleContext Z;
   private static int v = 0;
   private static int k = 0;
   private static final long[] i = new long[]{7206471141945943671L, 4419359548310954745L, 1111828095987587566L};
   private static final Long[] o = new Long[3];

   private ProfileUpdater() {
   }

   public static ProfileLoader data() {
      long var0 = 94257373194760L;
      long var2 = var0 ^ 127714274781894L;
      return J != null ? J : x(var2, class_310.method_1551());
   }

   public static ProfileLoader.TypeQ live() {
      return J != null ? ProfileFactory.live(J) : O;
   }

   public static ProfileLoader2 journey() {
      ProfileLoader var0 = data();
      return J == null && q != null && q.length() >= 12.0 ? q : var0.n;
   }

   public static boolean journeyLive() {
      return J != null ? true : q != null && q.length() >= 12.0;
   }

   public static LocalDate today() {
      return J != null ? ProfileFactory.today() : LocalDate.now();
   }

   public static boolean demo() {
      return J != null;
   }

   private static boolean v() {
      return n.R.enabled();
   }

   public static void onBlockBroken() {
      if (O != null && v()) {
         data().day(m).E++;
         O.Q.E++;
         x = true;
      }
   }

   public static void onBlockPlaced() {
      if (O != null && v()) {
         data().day(m).t++;
         O.Q.t++;
         x = true;
      }
   }

   public static void onDamage(class_1297 var0, int var1) {
      class_310 var2 = class_310.method_1551();
      if (O != null
         && var0 != null
         && var2.field_1724 != null
         && var1 == var2.field_1724.method_5628()
         && var0 != var2.field_1724
         && var0 instanceof class_1309
         && v()) {
         data().day(m).n++;
         O.Q.n++;
         h.put(var0.method_5628(), H());
         x = true;
      }
   }

   public static void onEntityEvent(class_1297 var0, byte var1) {
      long var2 = 129349464381108L;
      long var4 = var2 ^ 43236100117792L;
      long var6 = var2 ^ 75545953386325L;
      class_310 var8 = class_310.method_1551();
      if (var1 == 3 && O != null && var0 instanceof class_1309 var9 && var9 != var8.field_1724 && h.containsKey(var9.method_5628())) {
         float var10 = h.remove(var9.method_5628());
         if (!(H() - var10 > 5.0F) && v()) {
            ProfileLoader3 var11 = data().day(m);
            if (var9 instanceof class_1657) {
               var11.k++;
               O.Q.k++;
            } else {
               var11.Y++;
               O.Q.Y++;
            }

            if (q != null && var8.field_1687 != null && n.o.enabled()) {
               q.mark("kill", S(var6, var8), (float)var9.method_23317(), (float)var9.method_23321(), y(var4));
            }

            x = true;
         }
      }
   }

   public static void onDeath() {
      long var0 = 83444060753475L;
      long var2 = var0 ^ 27566729219543L;
      long var4 = var0 ^ 135195786143650L;
      class_310 var6 = class_310.method_1551();
      if (O != null && var6.field_1724 != null && v()) {
         data().day(m).g++;
         O.Q.g++;
         if (q != null && var6.field_1687 != null && n.o.enabled()) {
            q.mark("death", S(var4, var6), (float)var6.field_1724.method_23317(), (float)var6.field_1724.method_23321(), y(var2));
         }

         p = true;
         x = true;
      }
   }

   public static void onChat() {
      f = System.nanoTime();
      if (O != null && v()) {
         data().day(m).L++;
         O.Q.L++;
         x = true;
      }
   }

   static void tick(class_310 var0) {
      long var1 = 99991331513299L;
      long var3 = var1 ^ 131386807334758L;
      long var5 = var1 ^ 138865779552782L;
      int var7 = (int)((var1 ^ 138865779552782L) >>> 32);
      int var8 = (int)((var1 ^ 138865779552782L) << 32 >>> 48);
      int var9 = (int)(var5 << 48 >>> 48);
      long var10 = var1 ^ 135707025711901L;
      int var12 = (int)((var1 ^ 92957318889637L) >>> 48);
      long var13 = (var1 ^ 92957318889637L) << 16 >>> 16;
      long var15 = var1 ^ 133413552199032L;
      var5 = var1 ^ 135481313147693L;
      int var17 = (int)((var1 ^ 135481313147693L) >>> 32);
      int var18 = (int)((var1 ^ 135481313147693L) << 32 >>> 48);
      int var19 = (int)(var5 << 48 >>> 48);
      var5 = var1 ^ 1239818814719L;
      int var20 = (int)((var1 ^ 1239818814719L) >>> 32);
      int var21 = (int)((var1 ^ 1239818814719L) << 32 >>> 48);
      int var22 = (int)(var5 << 48 >>> 48);
      long var23 = var1 ^ 118613685881394L;
      D(var20, var21, (char)var22);
      class_746 var25 = var0.field_1724;
      if (var25 != null && var0.field_1687 != null) {
         ProfileLoader var26 = x(var10, var0);
         long var27 = System.nanoTime();
         if (O == null) {
            t(var0, var26, var27, var15);
         }

         float var29 = (float)Math.min(0.25, (var27 - X) / 1.0E9);
         X = var27;
         m = LocalDate.now();
         boolean var30 = var0.method_1493();
         class_243 var31 = var25.method_73189();
         String var32 = S(var23, var0);
         float var33 = var25.method_36454();
         float var34 = var25.method_36455();
         boolean var35 = j != null && var32.equals(l);
         if (var35 && (var31.method_1025(j) > 1.0E-4 || Math.abs(var33 - b) > 0.05F || Math.abs(var34 - Q) > 0.05F)) {
            f = var27;
         }

         ProfileLoader3 var36 = var26.day(m);
         if (v() && !var30 && (n.n.enabled() || (var27 - f) / 1.0E9 < 300.0)) {
            var36.A += var29;
            O.Q.A += var29;
            if (u != null) {
               u.v += var29;
               u.d = System.currentTimeMillis();
            }

            x = true;
         }

         double var37 = var35 ? var31.field_1352 - j.field_1352 : 0.0;
         double var39 = var35 ? var31.field_1350 - j.field_1350 : 0.0;
         double var41 = Math.sqrt(var37 * var37 + var39 * var39);
         if (!var35 || var41 >= 12.0) {
            p = true;
            C = 0;
         } else if (var41 > 0.001 && !var30 && v()) {
            U(var17, (char)var18, var25, (char)var19, var41, var36);
            U(var17, (char)var18, var25, (char)var19, var41, O.Q);
         }

         boolean var43 = var35 && var41 < 12.0;
         Q(var26, var31, var27, (char)var12, var43, var13);
         y(var7, (char)var8, var0, var9, var31, var32);
         j = var31;
         l = var32;
         b = var33;
         Q = var34;
         if (x && var27 - K > 60000000000L) {
            c(var3, false);
         }
      } else if (O != null) {
         endSession();
      }
   }

   private static void U(int var0, char var1, class_746 var2, char var3, double var4, ProfileLoader3 var6) {
      ProfileLoader.ensureSourceClassInitialized();
      if (!ModuleWidget.claimed("mounted")) {
         short var7 = 14400;
         if (!var2.method_5765()) {
            var7 = 14400;
            boolean var8 = var2.method_6128();
            short var9 = 8649;
            if (var8) {
               var6.J += var4;
               return;
            }

            var9 = 8649;
            if (var2.method_31549().field_7479) {
               var6.d += var4;
            } else {
               var7 = 8649;
               var8 = var2.method_5681();
               if (!var8) {
                  var8 = var2.method_5869();
                  var9 = 8649;
                  if (!var8) {
                     var8 = var2.method_5624();
                     var9 = 14400;
                     if (var8) {
                        var9 = 14400;
                        var6.B += var4;
                     } else {
                        var6.j += var4;
                     }

                     return;
                  }
               }

               var6.u += var4;
            }

            return;
         }
      }

      var6.y += var4;
   }

   private static void Q(ProfileLoader var0, class_243 var1, long var2, char var4, boolean var5, long var6) {
      ProfileLoader.ensureSourceClassInitialized();
      C = var5 ? C + 1 : 0;
      int var8 = L + 1;
      int var9 = z.length;
      long var10 = 15918293017461L;
      int var13 = var10 < 0L ? -1 : 1;
      L = var8 % var9;
      z[L] = var1.field_1352;
      c[L] = var1.field_1350;
      W[L] = var2;
      if (C >= z.length - 1 && v()) {
         var13 = (L + 1) % z.length;
         double var14 = (var2 - W[var13]) / 1.0E9;
         if (var14 > 0.5) {
            double var16 = Math.hypot(var1.field_1352 - z[var13], var1.field_1350 - c[var13]) / var14;
            if (var16 < 120.0 && var16 > var0.H) {
               var0.H = var16;
               x = true;
            }
         }
      }
   }

   private static void y(int var0, char var1, class_310 var2, int var3, class_243 var4, String var5) {
      long var6 = 40395220029917L;
      long var8 = var6 ^ 130064666481225L;
      ProfileLoader.ensureSourceClassInitialized();
      if (q != null && n.o.enabled() && v()) {
         float var10 = (float)var4.field_1352;
         float var11 = (float)var4.field_1350;
         double var12 = var10 - R;
         double var14 = var11 - U;
         double var16 = var12 * var12 + var14 * var14;
         long var18 = System.nanoTime();
         short var20 = 9405;
         var20 = 9405;
         if (!p && !(var16 > 4.0)) {
            if (!(var16 > 0.2)) {
               return;
            }

            if (var18 - S <= 5000000000L) {
               return;
            }
         }

         q.add(var5, var10, var11, y(var8), p);
         p = false;
         R = var10;
         U = var11;
         S = var18;
      }
   }

   private static void t(class_310 var0, ProfileLoader var1, long var2, long var4) {
      ProfileLoader.ensureSourceClassInitialized();
      class_1132 var6 = var0.method_1576();
      class_642 var7 = var0.method_1558();
      String var8;
      String var9;
      if (var6 != null) {
         var9 = var6.method_27728().method_150();
         var8 = "world:" + var9;
      } else {
         long var10 = 39203533010603L;
         int var12 = var10 < 0L ? -1 : 1;
         if (var7 != null) {
            label52: {
               if (var7.field_3752 != null) {
                  long var14 = 39203533010602L;
                  int var151 = var14 < 0L ? -1 : 1;
                  if (!var7.field_3752.isBlank() && !var7.field_3752.equals("Minecraft Server")) {
                     var13 = true;
                     break label52;
                  }
               }

               var13 = false;
            }

            var9 = var13 ? var7.field_3752 : var7.field_3761;
            long var15 = 39203533010602L;
            boolean var18 = (var15 < 0L ? -1 : 1) != 0;
            var8 = "server:" + var7.field_3761.toLowerCase(Locale.ROOT);
         } else {
            var9 = "Minecraft";
            var8 = "other";
         }
      }

      long var17 = System.currentTimeMillis();
      O = new ProfileLoader.TypeQ(var17, var9, new ProfileLoader3());
      long var12x = 39203533010602L;
      int var14 = var12x < 0L ? -1 : 1;
      q = new ProfileLoader2(var17, var9);
      u = var1.place(var8, var9);
      X = var2;
      f = var2;
      j = null;
      l = null;
      p = true;
      C = 0;
      h.clear();
      if (var1.D == null) {
         var1.D = LocalDate.now();
      }
   }

   static void endSession() {
      long var0 = 16445342185602L;
      long var2 = var0 ^ 39011975851063L;
      ProfileLoader.TypeQ var4 = O;
      if (var4 != null) {
         ProfileLoader var5 = y;
         if (var5 != null) {
            if (var4.Q.A >= 10.0) {
               var5.addSession(var4);
            }

            if (q != null && q.length() >= 24.0) {
               var5.n = q;
            }

            x = true;
            c(var2, false);
         }

         O = null;
         q = null;
         u = null;
         j = null;
         h.clear();
      }
   }

   private static String S(long var0, class_310 var2) {
      ProfileLoader.ensureSourceClassInitialized();
      return var2.field_1687 == null ? "" : var2.field_1687.method_27983().method_29177().toString();
   }

   private static int y(long var0) {
      return O == null ? 0 : (int)((System.currentTimeMillis() - O.A) / 1000L);
   }

   private static float H() {
      return (float)(System.nanoTime() - a) / 1.0E9F;
   }

   private static Path o(int var0, UUID var1, int var2, byte var3) {
      return FabricLoader.getInstance().getGameDir().resolve("purxiy").resolve("profile").resolve(var1 + ".json");
   }

   private static ProfileLoader x(long var0, class_310 var2) {
      long var3 = 50095763559605L;
      long var5 = 109340717651895L;
      short var7 = 25457;
      int var8 = 14590615;
      int var9 = (int)(var5 << 56 >>> 56);
      ProfileLoader.ensureSourceClassInitialized();
      UUID var10 = var2.method_1548().method_44717();
      if (y != null && var10.equals(A)) {
         return y;
      } else {
         if (y != null && x) {
            c(var3, true);
         }

         ProfileLoader var11 = null;
         Path var12 = o(var7, var10, var8, (byte)var9);
         long var13 = 36961595424973L;
         int var23 = var13 < 0L ? -1 : 1;
         if (Files.isRegularFile(var12)) {
            try (BufferedReader var16 = Files.newBufferedReader(var12, StandardCharsets.UTF_8)) {
               var11 = ProfileLoader.load(JsonParser.parseReader(var16).getAsJsonObject());
            } catch (Exception var21) {
               ClientServices.LOGGER.error("Could not read the profile {}, starting a new one", var12, var21);

               try {
                  Files.copy(var12, var12.resolveSibling(var10 + ".broken.json"), StandardCopyOption.REPLACE_EXISTING);
               } catch (IOException var18) {
               }
            }
         }

         if (var11 == null) {
            var11 = new ProfileLoader();
            var11.D = LocalDate.now();
         }

         long var23x = 36961595424974L;
         int var17 = var23x < 0L ? -1 : 1;
         var11.T = var2.method_1548().method_1676();
         y = var11;
         A = var10;
         x = false;
         K = System.nanoTime();
         if (!var11.w) {
            c(var2, var11, var10);
         }

         return var11;
      }
   }

   private static void c(class_310 var0, ProfileLoader var1, UUID var2) {
      Path var3 = var0.method_1586().method_19636();
      D = new ProfileUpdater.TypeN(var1, CompletableFuture.supplyAsync(() -> ProfileSupport3.scan(var3, var2), w));
   }

   private static void D(int var0, int var1, char var2) {
      long var3 = 100955911513900L;
      long var5 = var3 ^ 130148062950297L;
      ProfileLoader.ensureSourceClassInitialized();
      ProfileUpdater.TypeN var7 = D;
      if (var7 != null && var7.task().isDone()) {
         D = null;
         ProfileLoader var8 = var7.target();
         if (y == var8) {
            ProfileSupport3.Data var9;
            try {
               var9 = var7.task().join();
            } catch (CompletionException var11) {
               ClientServices.LOGGER.warn("Could not import local world statistics");
               return;
            }

            char var10 = 'ꄾ';
            if (y == var8) {
               var10 = 'ꄾ';
               if (!var8.w) {
                  var8.E.add(var9.totals());
                  var8.q = var9.worlds();
                  var8.w = true;
                  x = true;
                  c(var5, false);
                  if (var9.worlds() > 0) {
                     ClientServices.LOGGER.info("Profile: imported the statistics of {} singleplayer world(s)", var9.worlds());
                  }

                  return;
               }
            }
         }
      }
   }

   private static void c(long var0, boolean var2) {
      ProfileLoader.ensureSourceClassInitialized();
      ProfileLoader var3 = y;
      UUID var4 = A;
      if (var3 != null && var4 != null && (J == null || var3 != J)) {
         JsonObject var5 = var3.save();
         String var6 = Y.toJson(var5);
         x = false;
         K = System.nanoTime();
         long var7 = ++M;
         Runnable var9 = () -> {
            long var4x = 127302177505987L;
            short var6x = 29639;
            char var7x = '\ud911';
            int var8 = (int)(var4x << 48 >>> 48);
            var4x = 109340717651895L;
            short var9x = 25457;
            int var10x = 14590615;
            int var11 = (int)(var4x << 56 >>> 56);
            H(var6x, var7x, o(var9x, var4, var10x, (byte)var11), var6, var8, var7);
         };
         long var10 = 50095763559604L;
         int var12 = var10 < 0L ? -1 : 1;
         if (var2) {
            var9.run();
         } else {
            w.execute(var9);
         }
      }
   }

   private static synchronized void H(int var0, int var1, Path var2, String var3, int var4, long var5) {
      ProfileLoader.ensureSourceClassInitialized();
      if (var5 >= T) {
         T = var5;

         try {
            Files.createDirectories(var2.getParent());
            Path var7 = var2.resolveSibling(var2.getFileName() + ".tmp");

            try (BufferedWriter var8 = Files.newBufferedWriter(var7, StandardCharsets.UTF_8)) {
               var8.write(var3);
            }

            try {
               Files.move(var7, var2, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            } catch (AtomicMoveNotSupportedException var121) {
               Files.move(var7, var2, StandardCopyOption.REPLACE_EXISTING);
            }
         } catch (IOException var141) {
            ClientServices.LOGGER.error("Could not save the profile {}", var2, var141);
         }
      }
   }

   static String snapshotForUi() {
      JsonObject var0 = data().save();
      if (q != null) {
         var0.add("journey", q.save());
      }

      if (O != null) {
         JsonObject var1 = O.Q.save();
         var1.addProperty("start", O.A);
         var1.addProperty("place", O.O);
         var0.add("live", var1);
      }

      return var0.toString();
   }

   static void closeTracker() {
      long var0 = 19224785643594L;
      long var2 = var0 ^ 67017775643903L;
      endSession();
      c(var2, true);
      w.shutdown();

      try {
         if (!w.awaitTermination(5L, TimeUnit.SECONDS)) {
            w.shutdownNow();
            throw new IllegalStateException("Profile IO did not stop");
         }
      } catch (InterruptedException var8) {
         w.shutdownNow();
         Thread.currentThread().interrupt();
         throw new IllegalStateException("Profile IO interrupted");
      } finally {
         D = null;
         y = null;
         A = null;
         J = null;
         h.clear();
      }
   }

   public static void install(ProfileModule var0, ModuleContext var1) {
      n = var0;
      Z = var1;
   }

   public static void metric(String var0) {
      if (Z != null) {
         Z.metric(var0, var0.equals("drawn") ? ++v : ++k);
      }
   }

   public static void uninstall() {
      closeTracker();
      n = null;
      Z = null;
      X = 0L;
   }

   private static long a(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 1906) & 32767;
      if (o[var3] == null) {
         o[var3] = i[var3] ^ var1;
      }

      return o[var3];
   }

   private record TypeN(ProfileLoader v, CompletableFuture<ProfileSupport3.Data> C) {
      public ProfileLoader target() {
         return this.v;
      }

      public CompletableFuture<ProfileSupport3.Data> task() {
         return this.C;
      }

      public ProfileLoader dailyStatisticsStoreState3() {
         return this.v;
      }

      public CompletableFuture<ProfileSupport3.Data> completableFutureState6() {
         return this.C;
      }
   }
}
