package dev.purxiy.modules.creamykeys;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1109;
import net.minecraft.class_310;
import net.minecraft.class_3414;

public final class CreamyKeysModule extends ModuleDescriptor {
   private ModuleContext U;
   private int l;
   public final ModeSetting C;
   public final BooleanSetting o;
   public final BooleanSetting i;
   public final BooleanSetting S;
   public final NumberSetting Z;
   public final NumberSetting m;
   public final NumberSetting X;
   public final NumberSetting F;
   public final NumberSetting n;
   public final NumberSetting t;
   private final Map<Integer, CreamyKeysModule.TypeB> T;
   private final Map<Integer, CreamyKeysModule.TypeB> P;
   private final Map<Integer, Integer> N;
   private final Map<Integer, Integer> k;
   private final List<CreamyKeysModule.TypeR> b;
   private final Map<Integer, Long> e;
   private final Map<Integer, Long> v;
   private final long[] A;
   private final boolean[] O;
   private long w;
   private int J;
   private long Y;
   private double g;
   private long y;
   public static int G;
   private static String[] a;
   private static String[] c;
   private static long[] d;
   private static Integer[] f;
   private static long h;

   public CreamyKeysModule() {
      super("creamy_keys", "Creamy Keys", "Your keyboard, with a sound of its own", "keyboard");
      long var1 = 58183763680668L;
      this.T = new HashMap<>();
      this.P = new HashMap<>();
      this.N = new HashMap<>();
      this.k = new HashMap<>();
      this.b = new ArrayList<>();
      this.e = new HashMap<>();
      this.v = new HashMap<>();
      this.A = new long[3];
      this.O = new boolean[3];
      this.C = this.mode(
         "style",
         "Sound style",
         "Click a key to select and listen",
         "Creamy",
         Arrays.stream(CreamyKeySounds.Style.values()).map(var0 -> var0.F).toArray(String[]::new)
      );
      this.section("Keyboard");
      this.o = this.bool("keyboard", "Keyboard sounds", "Hear each press and release", true);
      this.Z = this.number("keyboard_volume", "Keyboard volume", null, 0.7, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.m = this.number("release_volume", "Release volume", "Relative to the key press", 0.85, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.section("Mouse");
      this.i = this.bool("mouse", "Mouse clicks", "Distinct left, right and middle clicks", true);
      this.X = this.number("mouse_volume", "Click volume", null, 0.65, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.S = this.bool("scroll", "Scroll wheel", "A soft notch in either direction", true);
      this.F = this.number("scroll_volume", "Scroll volume", null, 0.5, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.section("Tuning");
      this.n = this.number("pitch", "Pitch", "Deep to bright", 1.0, 0.88, 1.12, 0.01, Listener.PERCENT);
      this.t = this.number("variation", "Natural variation", "Alternate recorded takes with subtle touch variation", 0.02, 0.0, 0.08, 0.01, Listener.PERCENT);
      this.Z.visibleWhen(this.o::enabled);
      this.m.visibleWhen(this.o::enabled);
      this.X.visibleWhen(this.i::enabled);
      this.F.visibleWhen(this.S::enabled);
   }

   public CreamyKeySounds.Style style() {
      return CreamyKeySounds.Style.fromLabel((String)this.C.get());
   }

   public void onKeyboardInput(int var1, int var2, int var3) {
      long var4 = 78982541466826L;
      long var6 = var4 ^ 136362947274299L;
      long var8 = var4 ^ 83622238518307L;
      long var10 = var4 ^ 51601681997147L;
      long var12 = var4 ^ 128155998401413L;
      long var14 = var4 ^ 139463092993805L;
      int var16 = (int)((var4 ^ 139463092993805L) >>> 48);
      int var17 = (int)((var4 ^ 139463092993805L) << 16 >>> 48);
      int var18 = (int)(var14 << 32 >>> 32);
      long var19 = var4 ^ 120103916935551L;
      int var21 = var1 == -1 ? -var2 - 2 : var1;
      long var22 = System.nanoTime();
      if (var3 == 0) {
         this.e.remove(var21);
      } else if (var3 == 1 && this.e.putIfAbsent(var21, var22) == null) {
         this.v.put(var21, var22);
      }

      if (!class_310.method_1551().method_1569()) {
         this.T.clear();
      } else if (var3 == 0) {
         CreamyKeysModule.TypeB var24 = this.T.remove(var21);
         if (var24 != null) {
            this.W(var24.release(), var24.pitch(), var24.gain() * this.Z.floatValue() * this.m.floatValue(), var10, 0);
         }
      } else if (!this.T(var8)) {
         this.T.clear();
      } else if (var3 == 1 && !this.T.containsKey(var21)) {
         CreamyKeySounds.Style var28 = this.style();
         int var25 = this.A(var21, var6);
         float var26 = this.Z(var19, o(var21, var12), true);
         float var27 = this.M((short)var16, (short)var17, var18);
         this.T.put(var21, new CreamyKeysModule.TypeB(var28.release(var25), var26, var27));
         this.W(var28.press(var25), var26, var27 * this.Z.floatValue(), var10, 0);
      }
   }

   public void onMouseInput(int var1, int var2) {
      long var3 = 11341255058654L;
      long var5 = var3 ^ 133161369831693L;
      long var7 = var3 ^ 109313003988303L;
      long var9 = var3 ^ 56428642731801L;
      int var11 = (int)((var3 ^ 56428642731801L) >>> 48);
      int var12 = (int)((var3 ^ 56428642731801L) << 16 >>> 48);
      int var13 = (int)(var9 << 32 >>> 32);
      long var14 = var3 ^ 36003744538987L;
      if (var1 >= 0 && var1 < 3) {
         if (var2 == 1 && !this.O[var1]) {
            this.A[var1] = System.nanoTime();
         }

         this.O[var1] = var2 == 1 || var2 != 0 && this.O[var1];
      }

      if (!this.enabled() || !this.i.enabled() || !class_310.method_1551().method_1569()) {
         this.P.clear();
      } else if (var2 == 0) {
         CreamyKeysModule.TypeB var16 = this.P.remove(var1);
         if (var16 != null) {
            this.W(var16.release(), var16.pitch(), var16.gain() * this.X.floatValue(), var7, 0);
         }
      } else if (var2 == 1 && !this.P.containsKey(var1)) {
         int var19 = this.j(var1, var5);
         float var17 = this.Z(var14, 1.0F, true);
         float var18 = this.M((short)var11, (short)var12, var13);
         this.P.put(var1, new CreamyKeysModule.TypeB(CreamyKeySounds.mouseRelease(var1, var19), var17, var18));
         this.W(CreamyKeySounds.mousePress(var1, var19), var17, var18 * this.X.floatValue(), var7, 0);
      }
   }

   public void onMouseScroll(double var1) {
      long var3 = 68581371491981L;
      long var5 = var3 ^ 96087645239068L;
      long var7 = var3 ^ 22752745812792L;
      if (Double.isFinite(var1) && var1 != 0.0) {
         this.w = System.nanoTime();
         this.J = var1 > 0.0 ? 1 : -1;
      }

      if (!this.enabled() || !this.S.enabled() || !class_310.method_1551().method_1569()) {
         this.g = 0.0;
         this.y = 0L;
      } else if (Double.isFinite(var1) && var1 != 0.0) {
         this.g += var1;
         if (!(Math.abs(this.g) < 1.0)) {
            boolean var9 = this.g > 0.0;
            this.g %= 1.0;
            long var10 = System.nanoTime();
            if (var10 - this.y >= 9000000L) {
               this.y = var10;
               this.W(var9 ? CreamyKeySounds.L : CreamyKeySounds.S, this.Z(var7, 1.0F, false), this.F.floatValue(), var5, 0);
            }
         }
      }
   }

   public void preview(CreamyKeySounds.Style var1, int var2) {
      long var3 = 70390915254074L;
      long var5 = var3 ^ 136158485183947L;
      long var7 = var3 ^ 45349834114731L;
      long var9 = var3 ^ 126847630092405L;
      long var11 = var3 ^ 133074984756477L;
      int var13 = (int)((var3 ^ 133074984756477L) >>> 48);
      int var14 = (int)((var3 ^ 133074984756477L) << 16 >>> 48);
      int var15 = (int)(var11 << 32 >>> 32);
      long var16 = var3 ^ 117559569547919L;
      int var18 = this.A(var2, var5);
      float var19 = this.Z(var16, o(var2, var9), true);
      float var20 = this.M((short)var13, (short)var14, var15);
      this.W(var1.press(var18), var19, var20 * this.Z.floatValue(), var7, 0);
      this.W(var1.release(var18), var19, var20 * this.Z.floatValue() * this.m.floatValue(), var7, 3);
   }

   public void previewMouse(int var1) {
      long var2 = 134890182339492L;
      long var4 = var2 ^ 10866438817399L;
      long var6 = var2 ^ 21532497275445L;
      long var8 = var2 ^ 74407475726435L;
      int var10 = (int)((var2 ^ 74407475726435L) >>> 48);
      int var11 = (int)((var2 ^ 74407475726435L) << 16 >>> 48);
      int var12 = (int)(var8 << 32 >>> 32);
      long var13 = var2 ^ 88239037427217L;
      int var15 = this.j(var1, var4);
      float var16 = this.Z(var13, 1.0F, true);
      float var17 = this.M((short)var10, (short)var11, var12);
      this.W(CreamyKeySounds.mousePress(var1, var15), var16, var17 * this.X.floatValue(), var6, 0);
      this.W(CreamyKeySounds.mouseRelease(var1, var15), var16, var17 * this.X.floatValue(), var6, 2);
   }

   public boolean keyDown(int var1) {
      return this.T.containsKey(var1);
   }

   public void audition() {
      this.Y = System.nanoTime() + 250000000L;
   }

   private boolean T(long var1) {
      boolean var3 = this.enabled();
      long var4 = 13023943172328L;
      int var6 = var4 < 0L ? -1 : 1;
      return var3 && this.o.enabled() || System.nanoTime() < this.Y;
   }

   public boolean physicallyDown(int var1) {
      return this.e.containsKey(var1);
   }

   public long lastPress(int var1) {
      return this.v.getOrDefault(var1, 0L);
   }

   public boolean buttonDown(int var1) {
      return var1 >= 0 && var1 < 3 && this.O[var1];
   }

   public long lastClick(int var1) {
      return var1 >= 0 && var1 < 3 ? this.A[var1] : 0L;
   }

   public long lastScroll() {
      return this.w;
   }

   public int scrollDirection() {
      return this.J;
   }

   public void tick() {
      long var1 = 127724557543763L;
      long var3 = var1 ^ 140679227378106L;
      long var5 = System.nanoTime();
      this.b.removeIf(var3x -> {
         long var4 = 83917021576666L;
         long var6 = var4 ^ 41135216917579L;
         if (var5 < var3x.due()) {
            return false;
         } else {
            this.W(var3x.sound(), var3x.pitch(), var3x.volume(), var6, 0);
            return true;
         }
      });
      boolean var7 = class_310.method_1551().method_1569();
      if (!var7) {
         this.e.clear();
         Arrays.fill(this.O, false);
      }

      if (!var7 || !this.T(var3) && var5 > this.Y + 2000000000L) {
         this.T.clear();
      }

      if (!this.enabled() || !var7 || !this.i.enabled()) {
         this.P.clear();
      }

      if (!this.enabled() || !var7 || !this.S.enabled()) {
         this.g = 0.0;
         this.y = 0L;
      }
   }

   protected void onDisable() {
      this.b.clear();
      this.e.clear();
      this.v.clear();
      this.Y = 0L;
      Arrays.fill(this.O, false);
      this.T.clear();
      this.P.clear();
      this.N.clear();
      this.k.clear();
      this.g = 0.0;
      this.y = 0L;
   }

   private void W(class_3414 var1, float var2, float var3, long var4, int var6) {
      float var7;
      int var8 = (var7 = var3 - 0.001F) == 0.0F ? 0 : (var7 < 0.0F ? -1 : 1);
      long var9 = 115706381140368L;
      int var12 = var9 < 0L ? -1 : 1;
      if (var8 > 0) {
         if (var6 > 0) {
            this.b.add(new CreamyKeysModule.TypeR(var1, var2, var3, System.nanoTime() + var6 * h));
            long var12x = 115706381140369L;
            int var13 = var12x < 0L ? -1 : 1;
         } else {
            this.U.metric("plays", ++this.l);
            this.U.metric("gain", Math.min(var3, 1.0F));
            this.U.metric("pitch", var2);
            class_310.method_1551().method_1483().method_4873(class_1109.method_4757(var1, var2, Math.min(var3, 1.0F)));
         }
      }
   }

   private float Z(long var1, float var3, boolean var4) {
      float var5 = var4 ? this.t.floatValue() * 0.15F : 0.0F;
      float var6 = var5 > 0.0F ? ThreadLocalRandom.current().nextFloat(-var5, var5) : 0.0F;
      return Math.clamp(this.n.floatValue() * var3 * (1.0F + var6), 0.5F, 2.0F);
   }

   private static int r(int var0, long var1) {
      long var3 = 122392617999381L;
      int var6 = var3 < 0L ? -1 : 1;
      if (var0 == a(25364, 7457293024873821622L)) {
         return a(21500, 8527917392200992089L);
      } else {
         long var6x = 122392617999381L;
         int var8 = var6x < 0L ? -1 : 1;
         long var8x = 122392617999380L;
         int var10 = var8x < 0L ? -1 : 1;
         if (var0 != a(22478, 5408918318266941795L)) {
            long var10x = 122392617999381L;
            int var12 = var10x < 0L ? -1 : 1;
            if (var0 != a(16164, 1594032777046026639L)) {
               long var12x = 122392617999381L;
               int var14 = var12x < 0L ? -1 : 1;
               long var14x = 122392617999381L;
               int var29 = var14x < 0L ? -1 : 1;
               if (var0 >= a(8772, 1997905350738212069L)) {
                  long var16 = 122392617999380L;
                  int var30 = var16 < 0L ? -1 : 1;
                  long var18 = 122392617999380L;
                  int var20 = var18 < 0L ? -1 : 1;
                  if (var0 <= a(24490, 6085786521077549316L)) {
                     return (var0 - a(15225, 5304672709535133142L)) % a(24720, 6902115543731779128L);
                  }
               }

               long var29x = 122392617999381L;
               int var36 = var29x < 0L ? -1 : 1;
               long var30 = 2020255621197564546L;
               long var20 = 122392617999380L;
               int var23 = var20 < 0L ? -1 : 1;
               if (var0 >= a(23598, var30)) {
                  long var23x = 122392617999381L;
                  int var23x = var23x < 0L ? -1 : 1;
                  long var25x = 569114845625507964L;
                  long var27 = 122392617999381L;
                  int var27x = var27 < 0L ? -1 : 1;
                  if (var0 <= a(14038, var25x)) {
                     return (a(3598, 8350937989332097198L) + var0 - a(18213, 3583523417891475846L)) % a(24720, 6902115543731779128L);
                  }
               }

               return Math.floorMod(var0 * a(7842, 3692078051779758086L), a(24720, 6902115543731779128L));
            }
         }

         return a(4035, 7151151897781885285L);
      }
   }

   private static float o(int var0, long var1) {
      return 0.997F + Math.floorMod(var0 * a(6629, 8959886603588456268L), a(21182, 8491396218150749209L)) * 0.001F;
   }

   private int A(int var1, long var2) {
      long var4 = 122392617999381L;
      int var6 = r(var1, var4);
      if (!(this.t.floatValue() <= 0.001F)) {
         long var7 = 65768689220336L;
         int var10 = var7 < 0L ? -1 : 1;
         if (var6 < a(24720, 6902115543731779128L)) {
            Integer var10x = this.N.get(var1);
            int var11 = var10x == null
               ? var6
               : (var10x + (ThreadLocalRandom.current().nextBoolean() ? 4 : a(24974, 3704226193355028273L))) % a(24720, 6902115543731779128L);
            this.N.put(var1, var11);
            return var11;
         }
      }

      return var6;
   }

   private int j(int var1, long var2) {
      if (!(this.t.floatValue() <= 0.001F)) {
         long var4 = 126772861414867L;
         int var7 = var4 < 0L ? -1 : 1;
         if (var1 != 2) {
            Integer var7x = this.k.get(var1);
            int var8 = var7x == null ? 0 : (var7x + ThreadLocalRandom.current().nextInt(1, 3)) % 3;
            this.k.put(var1, var8);
            return var8;
         }
      }

      return 0;
   }

   private float M(short var1, short var2, int var3) {
      float var4 = this.t.floatValue() * 1.5F;
      return var4 <= 0.0F ? 1.0F : 1.0F + ThreadLocalRandom.current().nextFloat(-var4, var4);
   }

   protected void attach(ModuleContext var1) throws Exception {
      this.U = var1;
      var1.own(ModuleWidget.onKey(this::onKeyboardInput));
      var1.own(ModuleWidget.onMouseButton((var1x, var2) -> {
         if (var2 != 1 || !(class_310.method_1551().field_1755 instanceof CreamyKeysScreen var3 && var3.previewHovered())) {
            this.onMouseInput(var1x, var2);
         }
      }));
      var1.own(ModuleWidget.onScroll(0, (var1x, var3) -> {
         this.onMouseScroll(var3);
         return false;
      }));
      var1.own(ModuleWidget.onTick(var1x -> this.tick()));
      var1.own(ModuleWidget.onDisconnect(var1x -> this.onDisable()));
      var1.own(CosmeticRegistry.register(this.id(), this.getClass().getClassLoader(), () -> new CreamyKeysScreen(this)));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new CreamyKeysRenderer2(this);
   }

   private static int a(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 22191;
      if (f[var3] == null) {
         f[var3] = (int)(d[var3] ^ var1);
      }

      return f[var3];
   }

   public static void ensureSourceClassInitialized() {
   }

   private record TypeB(class_3414 m, float h, float c) {
      public class_3414 release() {
         return this.m;
      }

      public float pitch() {
         return this.h;
      }

      public float gain() {
         return this.c;
      }

      public class_3414 minecraftObjectState10() {
         return this.m;
      }

      public float floatState5() {
         return this.h;
      }

      public float floatState25() {
         return this.c;
      }
   }

   private record TypeR(class_3414 g, float n, float D, long k) {
      public class_3414 sound() {
         return this.g;
      }

      public float pitch() {
         return this.n;
      }

      public float volume() {
         return this.D;
      }

      public long due() {
         return this.k;
      }

      public class_3414 minecraftObjectState11() {
         return this.g;
      }

      public float floatState22() {
         return this.n;
      }

      public float floatState24() {
         return this.D;
      }

      public long longState33() {
         return this.k;
      }
   }
}
