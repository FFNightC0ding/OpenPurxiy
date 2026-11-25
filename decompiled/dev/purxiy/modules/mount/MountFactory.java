package dev.purxiy.modules.mount;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.purxiy.loader.fabric.ClientServices;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.joml.Vector3f;

public final class MountFactory {
   private static final Map<String, MountFactory.Data7> f = new LinkedHashMap<>();
   private static boolean U;
   public static int z = 0;
   private static final String[] b = new String[66];
   private static final long[] c = new long[]{8516117211460139047L, -3773782776925830323L, 5663540194816491186L, -7466972947203861446L};
   private static final Integer[] d = new Integer[4];

   private MountFactory() {
   }

   private static void G(long var0) {
      long var2 = 113210463428260L;
      short var4 = 26358;
      char var5 = '\udd75';
      int var6 = (int)(var2 << 48 >>> 48);
      MountFactory.Data7.ensureSourceClassInitialized();
      if (!U) {
         U = true;

         try (InputStream var7 = MountFactory.class.getResourceAsStream("/assets/purxiy/garage/vehicles.json")) {
            if (var7 == null) {
               throw new IllegalStateException("vehicles.json is missing");
            }

            JsonObject var8 = (JsonObject)new Gson().fromJson(new InputStreamReader(var7, StandardCharsets.UTF_8), JsonObject.class);

            for (JsonElement var10 : var8.getAsJsonArray("vehicles")) {
               MountFactory.Data7 var11 = s(var4, var10.getAsJsonObject(), (short)var5, (char)var6);
               f.put(var11.name(), var11);
            }
         } catch (Exception var141) {
            ClientServices.LOGGER.error("Purxiy Garage could not read its catalogue", var141);
         }
      }
   }

   public static List<String> names() {
      long var0 = 54043631004792L;
      long var2 = var0 ^ 25028247826486L;
      G(var2);
      return List.copyOf(f.keySet());
   }

   public static MountFactory.Data7 get(String var0) {
      long var1 = 72063462171869L;
      long var3 = var1 ^ 112625881970835L;
      G(var3);
      MountFactory.Data7 var5 = f.get(var0);
      return var5 != null ? var5 : f.values().iterator().next();
   }

   private static MountFactory.Data7 s(int var0, JsonObject var1, short var2, char var3) {
      long var4 = 113210463428260L;
      long var6 = var4 ^ 101904530330055L;
      long var8 = var4 ^ 44302234231433L;
      Vector3f var10 = M(var1, "lo");
      MountFactory.Data7.ensureSourceClassInitialized();
      Vector3f var11 = M(var1, "hi");
      JsonObject var12 = var1.getAsJsonObject("wheels");
      MountFactory.Data6[] var13 = new MountFactory.Data6[4];
      String[] var14 = new String[]{"fl", "fr", "rl", "rr"};

      for (int var15 = 0; var15 < 4; var15++) {
         JsonObject var16 = var12.getAsJsonObject(var14[var15]);
         if (var16 != null) {
            var13[var15] = new MountFactory.Data6(
               M(var16, "c"),
               var16.get("r").getAsFloat(),
               var16.get("w").getAsFloat(),
               var16.has("a") ? M(var16, "a").normalize() : new Vector3f(1.0F, 0.0F, 0.0F)
            );
         }
      }

      MountFactory.Data6 var32 = var13[0];
      MountFactory.Data6 var33 = var13[2];
      float var17 = Math.abs(var32.center().z - var33.center().z);
      int var18 = 7844;
      float var19 = var13[1] == null ? 0.0F : Math.abs(var13[0].center().x - var13[1].center().x);
      MountFactory.Data var20 = null;
      int var34 = 7844;
      int var21 = -579508239;
      var34 = (short)7844;
      var34 = 579508911;
      if (var1.has("screen")) {
         JsonObject var22 = var1.getAsJsonObject("screen");
         var20 = new MountFactory.Data(M(var22, "tl"), M(var22, "tr"), M(var22, "bl"));
      }

      ArrayList var38 = new ArrayList();
      int var23 = -8843;
      int var24 = 26358;
      var21 = -579508237;
      var23 = 579521929;
      var24 = (int)var4;
      if (var1.has("mirrors")) {
         for (JsonElement var26 : var1.getAsJsonArray("mirrors")) {
            JsonObject var27 = var26.getAsJsonObject();
            var38.add(
               new MountFactory.Data3(
                  M(var27, "c"),
                  var27.get("w").getAsFloat(),
                  var27.get("h").getAsFloat(),
                  var27.get("yaw").getAsFloat(),
                  var27.get("fov").getAsFloat(),
                  var27.has("inner") && var27.get("inner").getAsBoolean(),
                  var27.has("tl") ? M(var27, "tl") : null,
                  var27.has("tl") ? M(var27, "tr") : null,
                  var27.has("tl") ? M(var27, "bl") : null
               )
            );
         }
      }

      List var41 = t(var6, var1, "exhaust");
      List var42 = t(var6, var1, "tails");
      ArrayList var43 = new ArrayList();
      if (var1.has("head")) {
         for (JsonElement var29 : var1.getAsJsonArray("head")) {
            JsonObject var30 = var29.getAsJsonObject();
            var43.add(
               new MountFactory.Data5(M(var30, "p"), M(var30, "d"), var30.has("color") ? Integer.parseInt(var30.get("color").getAsString(), 16) : 16773856)
            );
         }
      }

      ArrayList var44 = new ArrayList();

      for (JsonElement var47 : var1.getAsJsonArray("paints")) {
         JsonObject var31 = var47.getAsJsonObject();
         var44.add(new MountFactory.Data4(var31.get("name").getAsString(), 0xFF000000 | Integer.parseInt(var31.get("color").getAsString(), 16)));
      }

      MountFactory.TypeK var46 = MountFactory.TypeK.valueOf(var1.get("kind").getAsString().toUpperCase(Locale.ROOT));
      return new MountFactory.Data7(
         var1.get("id").getAsString(),
         var1.get("name").getAsString(),
         var1.get("line").getAsString(),
         var46,
         var10,
         var11,
         var13,
         var17,
         var19,
         M(var1, "seat"),
         M(var1, "eye"),
         V(var1, var8, "steer"),
         V(var1, var8, "fork"),
         V(var1, var8, "swing"),
         V(var1, var8, "crank"),
         var20,
         var38,
         var41,
         var43,
         var42,
         var1.get("engine").getAsString(),
         var44,
         var1.has("turn") ? var1.get("turn").getAsFloat() : 32.0F,
         var1.has("camera") ? var1.get("camera").getAsFloat() : 1.0F,
         var1.has("reach") ? var1.get("reach").getAsFloat() : -0.7F,
         var1.has("grip") ? M(var1, "grip") : null,
         var1.has("steer_vis") ? var1.get("steer_vis").getAsFloat() : 30.0F,
         var1.has("rider") ? var1.get("rider").getAsFloat() : 1.0F,
         var1.has("rim") ? var1.get("rim").getAsFloat() : 0.18F,
         var1.has("grip_l") ? M(var1, "grip_l") : null,
         var1.has("grip_r") ? M(var1, "grip_r") : null,
         var1.has("credit") ? var1.get("credit").getAsString() : ""
      );
   }

   private static MountFactory.Data2 V(JsonObject var0, long var1, String var3) {
      MountFactory.Data7.ensureSourceClassInitialized();
      long var4 = 86570293673004L;
      int var7 = var4 < 0L ? -1 : 1;
      if (!var0.has(var3)) {
         return null;
      } else {
         JsonObject var7x = var0.getAsJsonObject(var3);
         return new MountFactory.Data2(M(var7x, "p"), M(var7x, "a"), var7x.has("ratio") ? var7x.get("ratio").getAsFloat() : 1.0F);
      }
   }

   private static List t(long var0, JsonObject var2, String var3) {
      MountFactory.Data7.ensureSourceClassInitialized();
      ArrayList var4 = new ArrayList();
      if (var2.has(var3)) {
         for (JsonElement var6 : var2.getAsJsonArray(var3)) {
            long var7 = 64152351657827L;
            int var9 = var7 < 0L ? -1 : 1;
            var4.add(K(var6.getAsJsonArray()));
         }
      }

      return var4;
   }

   private static Vector3f M(JsonObject var0, String var1) {
      return K(var0.getAsJsonArray(var1));
   }

   private static Vector3f K(JsonArray var0) {
      return new Vector3f(var0.get(0).getAsFloat(), var0.get(1).getAsFloat(), var0.get(2).getAsFloat());
   }

   public record Data(Vector3f n, Vector3f t, Vector3f c) {
      public float width() {
         return new Vector3f(this.t).sub(this.n).length();
      }

      public float height() {
         return new Vector3f(this.c).sub(this.n).length();
      }

      public Vector3f right() {
         return new Vector3f(this.t).sub(this.n).normalize();
      }

      public Vector3f down() {
         return new Vector3f(this.c).sub(this.n).normalize();
      }

      public Vector3f normal() {
         return this.down().cross(this.right(), new Vector3f()).normalize();
      }

      public Vector3f topLeft() {
         return this.n;
      }

      public Vector3f topRight() {
         return this.t;
      }

      public Vector3f bottomLeft() {
         return this.c;
      }

      public Vector3f vector3fState14() {
         return this.n;
      }

      public Vector3f vector3fState7() {
         return this.t;
      }

      public Vector3f vector3fState55() {
         return this.c;
      }
   }

   public record Data2(Vector3f J, Vector3f B, float i) {
      public Vector3f pivot() {
         return this.J;
      }

      public Vector3f axis() {
         return this.B;
      }

      public float ratio() {
         return this.i;
      }

      public Vector3f vector3fState56() {
         return this.J;
      }

      public Vector3f vector3fState17() {
         return this.B;
      }

      public float floatState12() {
         return this.i;
      }
   }

   public record Data3(Vector3f B, float G, float I, float L, float N, boolean H, Vector3f d, Vector3f a, Vector3f b) {
      public Vector3f center() {
         return this.B;
      }

      public float width() {
         return this.G;
      }

      public float height() {
         return this.I;
      }

      public float yaw() {
         return this.L;
      }

      public float fov() {
         return this.N;
      }

      public boolean inner() {
         return this.H;
      }

      public Vector3f tl() {
         return this.d;
      }

      public Vector3f tr() {
         return this.a;
      }

      public Vector3f bl() {
         return this.b;
      }

      public Vector3f vector3fState17() {
         return this.B;
      }

      public float floatState28() {
         return this.G;
      }

      public float floatState40() {
         return this.I;
      }

      public float floatState57() {
         return this.L;
      }

      public float floatState51() {
         return this.N;
      }

      public boolean booleanState45() {
         return this.H;
      }

      public Vector3f vector3fState12() {
         return this.d;
      }

      public Vector3f vector3fState57() {
         return this.a;
      }

      public Vector3f vector3fState10() {
         return this.b;
      }
   }

   public record Data4(String h, int S) {
      public String name() {
         return this.h;
      }

      public int color() {
         return this.S;
      }

      public String stringState9() {
         return this.h;
      }

      public int integerState5() {
         return this.S;
      }
   }

   public record Data5(Vector3f W, Vector3f X, int k) {
      public Vector3f position() {
         return this.W;
      }

      public Vector3f direction() {
         return this.X;
      }

      public int color() {
         return this.k;
      }

      public Vector3f vector3fState38() {
         return this.W;
      }

      public Vector3f vector3fState58() {
         return this.X;
      }

      public int integerState46() {
         return this.k;
      }
   }

   public record Data6(Vector3f t, float M, float O, Vector3f x) {
      public Vector3f center() {
         return this.t;
      }

      public float radius() {
         return this.M;
      }

      public float halfWidth() {
         return this.O;
      }

      public Vector3f axle() {
         return this.x;
      }

      public Vector3f vector3fState7() {
         return this.t;
      }

      public float floatState27() {
         return this.M;
      }

      public float floatState13() {
         return this.O;
      }

      public Vector3f vector3fState27() {
         return this.x;
      }
   }

   public record Data7(
      String q,
      String z,
      String S,
      MountFactory.TypeK l,
      Vector3f y,
      Vector3f m,
      MountFactory.Data6[] c,
      float n,
      float x,
      Vector3f j,
      Vector3f d,
      MountFactory.Data2 r,
      MountFactory.Data2 p,
      MountFactory.Data2 N,
      MountFactory.Data2 X,
      MountFactory.Data I,
      List<MountFactory.Data3> P,
      List<Vector3f> O,
      List<MountFactory.Data5> B,
      List<Vector3f> f,
      String H,
      List<MountFactory.Data4> V,
      float T,
      float A,
      float G,
      Vector3f J,
      float C,
      float Q,
      float Z,
      Vector3f F,
      Vector3f i,
      String w
   ) {
      public static boolean t;

      public float halfWidth() {
         return Math.max(Math.abs(this.y.x), Math.abs(this.m.x));
      }

      public float halfLength() {
         return Math.max(Math.abs(this.y.z), Math.abs(this.m.z));
      }

      public float height() {
         return this.m.y;
      }

      public boolean bike() {
         return this.l == MountFactory.TypeK.MOTORBIKE || this.l == MountFactory.TypeK.BICYCLE;
      }

      public boolean hasEngine() {
         return this.l != MountFactory.TypeK.BICYCLE;
      }

      public String id() {
         return this.q;
      }

      public String name() {
         return this.z;
      }

      public String line() {
         return this.S;
      }

      public MountFactory.TypeK kind() {
         return this.l;
      }

      public Vector3f lo() {
         return this.y;
      }

      public Vector3f hi() {
         return this.m;
      }

      public MountFactory.Data6[] wheels() {
         return this.c;
      }

      public float wheelbase() {
         return this.n;
      }

      public float track() {
         return this.x;
      }

      public Vector3f seat() {
         return this.j;
      }

      public Vector3f eye() {
         return this.d;
      }

      public MountFactory.Data2 steer() {
         return this.r;
      }

      public MountFactory.Data2 fork() {
         return this.p;
      }

      public MountFactory.Data2 swing() {
         return this.N;
      }

      public MountFactory.Data2 crank() {
         return this.X;
      }

      public MountFactory.Data screen() {
         return this.I;
      }

      public List<MountFactory.Data3> mirrors() {
         return this.P;
      }

      public List<Vector3f> exhausts() {
         return this.O;
      }

      public List<MountFactory.Data5> headlights() {
         return this.B;
      }

      public List<Vector3f> tails() {
         return this.f;
      }

      public String engine() {
         return this.H;
      }

      public List<MountFactory.Data4> paints() {
         return this.V;
      }

      public float turnAngle() {
         return this.T;
      }

      public float cameraScale() {
         return this.A;
      }

      public float reach() {
         return this.G;
      }

      public Vector3f grip() {
         return this.J;
      }

      public float steerVisual() {
         return this.C;
      }

      public float riderScale() {
         return this.Q;
      }

      public float rimRadius() {
         return this.Z;
      }

      public Vector3f gripLeft() {
         return this.F;
      }

      public Vector3f gripRight() {
         return this.i;
      }

      public String credit() {
         return this.w;
      }

      public static void ensureSourceClassInitialized() {
      }

      public String stringState17() {
         return this.q;
      }

      public String stringState50() {
         return this.z;
      }

      public String stringState37() {
         return this.S;
      }

      public MountFactory.TypeK vehicleCatalog_VehicleKindState5() {
         return this.l;
      }

      public Vector3f vector3fState37() {
         return this.y;
      }

      public Vector3f vector3fState3() {
         return this.m;
      }

      public MountFactory.Data6[] vehicleCatalog_VehicleWheelGeometryArrayState1() {
         return this.c;
      }

      public float floatState22() {
         return this.n;
      }

      public float floatState1() {
         return this.x;
      }

      public Vector3f vector3fState2() {
         return this.j;
      }

      public Vector3f vector3fState12() {
         return this.d;
      }

      public MountFactory.Data2 vehicleCatalog_VehicleSteeringAxisState1() {
         return this.r;
      }

      public MountFactory.Data2 vehicleCatalog_VehicleSteeringAxisState2() {
         return this.p;
      }

      public MountFactory.Data2 vehicleCatalog_VehicleSteeringAxisState3() {
         return this.N;
      }

      public MountFactory.Data2 vehicleCatalog_VehicleSteeringAxisState4() {
         return this.X;
      }

      public MountFactory.Data vehicleCatalog_VehicleMirrorSurfaceState1() {
         return this.I;
      }

      public List<MountFactory.Data3> listState30() {
         return this.P;
      }

      public List<Vector3f> listState38() {
         return this.O;
      }

      public List<MountFactory.Data5> listState13() {
         return this.B;
      }

      public List<Vector3f> listState1() {
         return this.f;
      }

      public String stringState19() {
         return this.H;
      }

      public List<MountFactory.Data4> listState8() {
         return this.V;
      }

      public float floatState29() {
         return this.T;
      }

      public float floatState56() {
         return this.A;
      }

      public float floatState28() {
         return this.G;
      }

      public Vector3f vector3fState56() {
         return this.J;
      }

      public float floatState16() {
         return this.C;
      }

      public float floatState34() {
         return this.Q;
      }

      public float floatState43() {
         return this.Z;
      }

      public Vector3f vector3fState31() {
         return this.F;
      }

      public Vector3f vector3fState30() {
         return this.i;
      }

      public String stringState59() {
         return this.w;
      }
   }

   public static enum TypeK {
      CAR,
      MOTORBIKE,
      BICYCLE,
      QUAD;
   }
}
