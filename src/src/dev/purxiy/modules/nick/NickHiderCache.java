// Sourced By DevFFNightmare
package dev.purxiy.modules.nick;

import com.mojang.authlib.GameProfile;
import dev.purxiy.config.KeybindSetting;
import dev.purxiy.loader.fabric.client.ClientRegistry5;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import net.minecraft.class_2583;
import net.minecraft.class_310;
import net.minecraft.class_5224;
import net.minecraft.class_5348;
import net.minecraft.class_5481;
import net.minecraft.class_634;
import net.minecraft.class_640;
import net.minecraft.class_742;
import net.minecraft.class_746;
import net.minecraft.class_9296;
import net.minecraft.class_5225.class_5231;

public final class NickHiderCache {
   static final String c = "Purxiy";
   static final String k = "Player";
   private static final String Z = "00000000-0000-0000-0000-000000000000";
   private static final Pattern V = Pattern.compile("[.*!~+\\-]?[A-Za-z0-9_]{2,16}|[.*!~+\\-][A-Za-z0-9_][A-Za-z0-9_ ]{0,14}[A-Za-z0-9_]");
   private static final int E = 4096;
   private static volatile boolean U;
   private static volatile NickHiderCache.TypeB L = NickHiderCache.TypeB.u;
   private static volatile boolean g = true;
   private static volatile int t;
   private static final Map<UUID, NickHiderCache.TypeQ> f = new LinkedHashMap<>();
   private static final Set<UUID> x = ConcurrentHashMap.newKeySet();
   private static final Map<String, Integer> I = new ConcurrentHashMap<>();
   private static int C = 1;
   private static int w = 0;
   private static NickHiderModule d;
   private static final Map<ClientRegistry5.TypeX, NickHiderFactory> D = Collections.synchronizedMap(new WeakHashMap<>());

   private static final String[] b = new String[10];

   private static final Integer[] h = new Integer[2];

   private NickHiderCache() {
   }

   public static void init() {
      throw new IllegalStateException("Use scoped nickname lifecycle");
   }

   static void setEnabled(boolean var0) {
      U = var0;
      g = true;
      t++;
   }

   public static boolean active() {
      long var0 = 2180480202901L;
      long var2 = var0 ^ 6161332088517L;
      return U && M(var2).T != null;
   }

   
   static NickHiderBuilder table() {
      long var0 = 54111650306114L;
      long var2 = var0 ^ 58651990298130L;
      return U ? M(var2).T : null;
   }

   public static int generation() {
      long var0 = 111550995174688L;
      long var2 = var0 ^ 106744429462384L;
      if (U) {
         M(var2);
      }

      return t;
   }

   public static void meet( GameProfile var0) {
      long var1 = 99281366819549L;
      long var3 = var1 ^ 8890750498407L;
      long var5 = var1 ^ 46565993858992L;
      long var7 = var1 ^ 120666290711051L;
      class_310 var9 = class_310.method_1551();
      if (var0 != null && var9 != null && isRealPlayer(var0) && !C(var9, var0, var7)) {
         R(var0, var3);
         if (W(var5)) {
            g = true;
         }
      }
   }

   public static void playersChanged() {
      long var0 = 95899028624770L;
      long var2 = var0 ^ 43063365267695L;
      if (W(var2)) {
         g = true;
      }
   }

   private static boolean W(long var0) {
      NickHiderCodec.ensureSourceClassInitialized();
      long var2 = 123223737886061L;
      if (123223737886061L == 0L) {
         boolean var10000 = false;
      } else {
         int var5 = var2 < 0L ? -1 : 1;
      }

      return U && d.l.enabled();
   }

   public static List<String> knownNames() {
      TreeSet var0 = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
      class_310 var1 = class_310.method_1551();
      class_634 var2 = var1.method_1562();
      if (var2 != null) {
         for (class_640 var4 : var2.method_2880()) {
            if (isRealPlayer(var4.method_2966())) {
               var0.add(var4.method_2966().name());
            }
         }
      }

      for (NickHiderCache.TypeQ var6 : f.values()) {
         var0.add(var6.name());
      }

      return new ArrayList<>(var0);
   }

   public static boolean hideSkin( GameProfile var0) {
      long var1 = 9264280662075L;
      long var3 = var1 ^ 99481863556150L;
      return var0 != null && l(var0.id(), var3, var0.name());
   }

   public static boolean hideSkin(class_9296 var0) {
      long var1 = 68080275390864L;
      long var3 = var1 ^ 123129257688477L;
      GameProfile var5 = var0.method_73313();
      return l(var5.id(), var3, var0.method_73317().orElse(var5.name()));
   }

   private static boolean l( UUID var0,  long var1, String var3) {
      long var4 = 4815241205328L;
      NickHiderCodec.ensureSourceClassInitialized();
      if (!U) {
         return false;
      } else {
         NickHiderCache.TypeB var6 = M(var4);
         long var7 = 90260600781836L;
         if (90260600781836L == 0L) {
            boolean var10000 = false;
         } else {
            int var14 = var7 < 0L ? -1 : 1;
         }

         boolean var10 = var6.isSelf(var0, var3);
         long var11 = 90260600781837L;
         if (90260600781837L == 0L) {
            boolean var15 = false;
         } else {
            int var16 = var11 < 0L ? -1 : 1;
         }

         return var10 ? var6.o : var6.E && var6.isOther(var0, var3);
      }
   }

   private static NickHiderCache.TypeB M(long var0) {
      long var2 = 127688442131678L;
      NickHiderCodec.ensureSourceClassInitialized();
      NickHiderCache.TypeB var4 = L;
      if (g || var4.stale()) {
         class_310 var5 = class_310.method_1551();
         long var6 = 4815241205327L;
         if (4815241205327L == 0L) {
            boolean var10000 = false;
         } else {
            int var9 = var6 < 0L ? -1 : 1;
         }

         if (var5 != null && var5.method_18854()) {
            var4 = P(var2, var5);
         }
      }

      return var4;
   }

   static void tick(class_310 var0) {
      long var1 = 134844722688170L;
      long var3 = var1 ^ 139375337170682L;
      int var5 = U ? M(var3).L : 0;
      if (var5 != w) {
         w = var5;
         var0.field_1705.method_1743().method_1817();
      }
   }

   private static NickHiderCache.TypeB P(long var0, class_310 var2) {
      long var3 = 92421745491819L;
      long var5 = 90562415580346L;
      long var7 = 82454481510017L;
      long var9 = 775127794L;
      char var11 = '銻';
      NickHiderCodec.ensureSourceClassInitialized();
      g = false;
      if (!U) {
         L = NickHiderCache.TypeB.u;
         return L;
      } else {
         NickHiderModule var12 = d;
         class_746 var13 = var2.field_1724;
         class_634 var14 = var2.method_1562();
         HashSet var15 = new HashSet();
         HashSet var16 = new HashSet();
         var15.add(var2.method_1548().method_44717());
         var16.add(var2.method_1548().method_1676());
         if (var14 != null) {
            GameProfile var17 = var14.method_2879();
            var15.add(var17.id());
            var16.add(var17.name());
         }

         if (var13 != null) {
            var15.add(var13.method_7334().id());
            var16.add(var13.method_7334().name());
         }

         if (var14 != null) {
            for (class_640 var18 : var14.method_2880()) {
               long var19 = 127688442131678L;
               if (127688442131678L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var78 = var19 < 0L ? -1 : 1;
               }

               if (!var15.contains(var18.method_2966().id()) && isRealPlayer(var18.method_2966())) {
                  R(var18.method_2966(), var5);
               }
            }
         }

         long var48 = 127688442131677L;
         if (127688442131677L == 0L) {
            boolean var79 = false;
         } else {
            int var80 = var48 < 0L ? -1 : 1;
         }

         if (var2.field_1687 != null) {
            for (class_742 var21 : var2.field_1687.method_18456()) {
               if (!var15.contains(var21.method_5667()) && isRealPlayer(var21.method_7334())) {
                  R(var21.method_7334(), var5);
               }
            }
         }

         NickHiderBuilder.TypeR var51 = new NickHiderBuilder.TypeR();
         int var52 = 0;

         for (KeybindSetting.TypeC var23 : var12.S.get()) {
            String var24 = var23.from().strip();
            NickHiderCodec var25 = NickHiderCodec.parse(var23.to());
            int var49 = var24.length();
            long var26 = 127688442131678L;
            if (127688442131678L == 0L) {
               boolean var81 = false;
            } else {
               int var82 = var26 < 0L ? -1 : 1;
            }

            if (var49 >= 2 && !var25.isEmpty()) {
               boolean var29 = var25.S.isBlank();
               long var30 = 127688442131677L;
               if (127688442131677L == 0L) {
                  boolean var83 = false;
               } else {
                  int var84 = var30 < 0L ? -1 : 1;
               }

               if (!var29) {
                  var51.add(var24, NickHiderBuilder.Data.of(var24, var25, 3));
                  var51.add(var25.S, NickHiderBuilder.Data.of(var25.S, null, 4));
                  var52++;
               }
            }
         }

         long var53 = 127688442131678L;
         if (127688442131678L == 0L) {
            boolean var85 = false;
         } else {
            int var86 = var53 < 0L ? -1 : 1;
         }

         long var54 = 127688442131678L;
         if (127688442131678L == 0L) {
            boolean var87 = false;
         } else {
            int var88 = var54 < 0L ? -1 : 1;
         }

         long var27 = 127688442131678L;
         if (127688442131678L == 0L) {
            boolean var89 = false;
         } else {
            int var90 = var27 < 0L ? -1 : 1;
         }

         if (var12.g.enabled()) {
            NickHiderCodec var55 = l(var9, (char)var11, var12.E.get(), "Purxiy");

            for (String var32 : (Iterable<String>)(Object)(var16)) {
               long var33 = 127688442131677L;
               if (127688442131677L == 0L) {
                  boolean var91 = false;
               } else {
                  int var92 = var33 < 0L ? -1 : 1;
               }

               if (var32 != null) {
                  int var50 = var32.length();
                  long var36 = 127688442131678L;
                  if (127688442131678L == 0L) {
                     boolean var93 = false;
                  } else {
                     int var94 = var36 < 0L ? -1 : 1;
                  }

                  if (var50 >= 2) {
                     var51.add(var32, NickHiderBuilder.Data.of(var32, var55, 2));
                     var52++;
                  }
               }
            }

            long var58 = 127688442131678L;
            if (127688442131678L == 0L) {
               boolean var95 = false;
            } else {
               int var96 = var58 < 0L ? -1 : 1;
            }

            for (String var70 : var12.R.get().split("[,;\\n]")) {
               String var37 = var70.strip();
               long var38 = 127688442131678L;
               if (127688442131678L == 0L) {
                  boolean var97 = false;
               } else {
                  int var98 = var38 < 0L ? -1 : 1;
               }

               if (var37.length() >= 2) {
                  var51.add(var37, NickHiderBuilder.Data.of(var37, var55, 2));
                  var52++;
               }
            }

            long var64 = 127688442131677L;
            if (127688442131677L == 0L) {
               boolean var99 = false;
            } else {
               int var100 = var64 < 0L ? -1 : 1;
            }

            for (UUID var71 : (Iterable<UUID>)(Object)(var15)) {
               long var73 = 127688442131678L;
               if (127688442131678L == 0L) {
                  boolean var101 = false;
               } else {
                  int var102 = var73 < 0L ? -1 : 1;
               }

               var52 += Y(var51, var7, var71);
            }

            long var68 = 127688442131678L;
            if (127688442131678L == 0L) {
               boolean var103 = false;
            } else {
               int var104 = var68 < 0L ? -1 : 1;
            }

            var51.add(var55.S, NickHiderBuilder.Data.of(var55.S, null, 4));
         }

         if (var12.l.enabled()) {
            String var56 = var12.A.get();
            String var59 = "Player";
            String var65 = l(var9, (char)var11, var56, var59).L.strip();

            for (Entry var69 : f.entrySet()) {
               NickHiderCache.TypeQ var72 = (NickHiderCache.TypeQ)var69.getValue();
               String var74 = var72.name();
               long var75 = 127688442131678L;
               if (127688442131678L == 0L) {
                  boolean var105 = false;
               } else {
                  int var106 = var75 < 0L ? -1 : 1;
               }

               boolean var40 = var15.contains(var69.getKey());
               long var41 = 127688442131677L;
               if (127688442131677L == 0L) {
                  boolean var107 = false;
               } else {
                  int var108 = var41 < 0L ? -1 : 1;
               }

               if (!var40) {
                  var40 = var16.contains(var74);
                  long var43 = 127688442131677L;
                  if (127688442131677L == 0L) {
                     boolean var109 = false;
                  } else {
                     int var110 = var43 < 0L ? -1 : 1;
                  }

                  if (var40) {
                     long var45 = 127688442131677L;
                     if (127688442131677L == 0L) {
                        boolean var111 = false;
                     } else {
                        int var112 = var45 < 0L ? -1 : 1;
                     }
                  } else {
                     NickHiderCodec var77 = var72.standIn(var65);
                     var51.add(var74, NickHiderBuilder.Data.of(var74, var77, 0));
                     var51.add(var77.S, NickHiderBuilder.Data.of(var77.S, null, 4));
                     var52 += 1 + w(var51, (UUID)var69.getKey(), var72, var3);
                  }
               }
            }
         }

         HashSet var57 = new HashSet();

         for (String var62 : (Iterable<String>)(Object)(var16)) {
            if (var62 != null) {
               var57.add(var62.toLowerCase(Locale.ROOT));
            }
         }

         long var61 = 127688442131678L;
         if (127688442131678L == 0L) {
            boolean var113 = false;
         } else {
            int var114 = var61 < 0L ? -1 : 1;
         }

         t++;
         L = new NickHiderCache.TypeB(var52 > 0 ? var51.build() : null, var12, var13, var14, var15, var57);
         return L;
      }
   }

   private static void R(GameProfile var0, long var1) {
      long var3 = 139677647098808L;
      byte var5 = 0;
      short var6 = 32521;
      int var7 = (int)(var3 << 32 >>> 32);
      NickHiderCodec.ensureSourceClassInitialized();
      NickHiderCache.TypeQ var8 = f.get(var0.id());
      if (var8 == null) {
         int var9 = f.size();
         long var10 = 90562415580346L;
         if (90562415580346L == 0L) {
            boolean var10000 = false;
         } else {
            int var17 = var10 < 0L ? -1 : 1;
         }

         if (var9 >= 4096) {
            Iterator var13 = f.entrySet().iterator();
            Entry var14 = (Entry)var13.next();
            var13.remove();
            x.remove(var14.getKey());
            d(((NickHiderCache.TypeQ)var14.getValue()).name(), (char)var5, (short)var6, var7);
         }

         f.put(var0.id(), new NickHiderCache.TypeQ(var0.name(), C++));
         x.add(var0.id());
         I.merge(var0.name().toLowerCase(Locale.ROOT), 1, Integer::sum);
      } else {
         String var15 = var8.name();
         long var16 = 90562415580346L;
         if (90562415580346L == 0L) {
            boolean var18 = false;
         } else {
            int var19 = var16 < 0L ? -1 : 1;
         }

         if (!var15.equals(var0.name())) {
            f.put(var0.id(), new NickHiderCache.TypeQ(var0.name(), var8.number()));
            d(var8.name(), (char)var5, (short)var6, var7);
            I.merge(var0.name().toLowerCase(Locale.ROOT), 1, Integer::sum);
         }
      }
   }

   private static void d(String var0, char var1, short var2, int var3) {
      I.computeIfPresent(var0.toLowerCase(Locale.ROOT), (var0x, var1x) -> {
         NickHiderCodec.ensureSourceClassInitialized();
         long var2x = 23794285438162L;
         if (23794285438162L == 0L) {
            boolean var10000 = false;
         } else {
            int var9 = var2x < 0L ? -1 : 1;
         }

         int var5 = var1x;
         long var6 = 23794285438163L;
         if (23794285438163L == 0L) {
            boolean var10 = false;
         } else {
            int var11 = var6 < 0L ? -1 : 1;
         }

         return var5 > 1 ? var1x - 1 : null;
      });
   }

   private static boolean C(class_310 var0, GameProfile var1, long var2) {
      NickHiderCodec.ensureSourceClassInitialized();
      UUID var4 = var1.id();
      class_634 var5 = var0.method_1562();
      return var4.equals(var0.method_1548().method_44717())
         || var5 != null && var4.equals(var5.method_2879().id())
         || var0.field_1724 != null && var4.equals(var0.field_1724.method_5667());
   }

   private static NickHiderCodec l(long var0, char var2, String var3, String var4) {
      NickHiderCodec.ensureSourceClassInitialized();
      NickHiderCodec var5 = NickHiderCodec.parse(var3);
      return !var5.isEmpty() && !var5.S.isBlank() ? var5 : NickHiderCodec.parse(var4);
   }

   private static int Y(NickHiderBuilder.TypeR var0,  long var1, UUID var3) {
      long var4 = 92421745491819L;
      return w(var0, var3, null, var4);
   }

   private static int w(NickHiderBuilder.TypeR var0,  UUID var1,  NickHiderCache.TypeQ var2, long var3) {
      NickHiderCodec.ensureSourceClassInitialized();
      if (var1 != null && (var1.getMostSignificantBits() != 0L || var1.getLeastSignificantBits() != 0L)) {
         long var5 = 92421745491819L;
         if (92421745491819L == 0L) {
            boolean var10000 = false;
         } else {
            int var15 = var5 < 0L ? -1 : 1;
         }

         String var8 = var2 != null && var2.O != null ? var2.O : var1.toString();
         String var9 = var2 != null && var2.q != null ? var2.q : var8.replace("-", "");
         long var10 = 92421745491818L;
         if (92421745491818L == 0L) {
            boolean var16 = false;
         } else {
            int var17 = var10 < 0L ? -1 : 1;
         }

         long var13 = 92421745491819L;
         if (92421745491819L == 0L) {
            boolean var18 = false;
         } else {
            int var19 = var13 < 0L ? -1 : 1;
         }

         if (var2 != null) {
            var2.O = var8;
            var2.q = var9;
         }

         var0.add(var8, NickHiderBuilder.Data.of(var8, NickHiderCodec.plain("00000000-0000-0000-0000-000000000000"), 1));
         var0.add(var9, NickHiderBuilder.Data.of(var9, NickHiderCodec.plain("00000000-0000-0000-0000-000000000000".replace("-", "")), 1));
         return 2;
      } else {
         return 0;
      }
   }

   public static boolean isRealPlayer( GameProfile var0) {
      return var0 != null && var0.id() != null && var0.id().version() != 2 && var0.name() != null && V.matcher(var0.name()).matches();
   }

   public static void devMeet(UUID var0, String var1) {
      if (!f.containsKey(var0)) {
         f.put(var0, new NickHiderCache.TypeQ(var1, C++));
         x.add(var0);
         I.merge(var1.toLowerCase(Locale.ROOT), 1, Integer::sum);
      }

      g = true;
   }

   static void install(NickHiderModule var0) {
      d = var0;
   }

   static void release() {
      setEnabled(false);
      L = NickHiderCache.TypeB.u;
      f.clear();
      x.clear();
      I.clear();
      D.clear();
      d = null;
      class_310.method_1551().field_1705.method_1743().method_1817();
   }

   static ClientRegistry5.Listener2 handler() {
      return new ClientRegistry5.Listener2() {
         @Override
         public boolean active() {
            return NickHiderCache.active();
         }

         @Override
         public int generation() {
            return NickHiderCache.generation();
         }

         @Override
         public void meet(GameProfile var1) {
            NickHiderCache.meet(var1);
         }

         @Override
         public void playersChanged() {
            NickHiderCache.playersChanged();
         }

         @Override
         public List<String> knownNames() {
            return NickHiderCache.knownNames();
         }

         @Override
         public boolean hideSkin(GameProfile var1) {
            return NickHiderCache.hideSkin(var1);
         }

         @Override
         public boolean hideSkin(class_9296 var1) {
            return NickHiderCache.hideSkin(var1);
         }

         @Override
         public boolean accept(class_5481 var1, class_5224 var2) {
            return NickHiderSupport2.sequence(var1).accept(var2);
         }

         @Override
         public class_5348 text(class_5348 var1) {
            return NickHiderSupport2.exportText(var1);
         }

         @Override
         public float width(String var1, class_5231 var2) {
            return NickHiderSupport2.width(var1, var2);
         }

         @Override
         public float width(class_5348 var1, class_5231 var2) {
            return NickHiderSupport2.width(var1, var2);
         }

         @Override
         public boolean iterate(String var1, class_2583 var2, class_5224 var3) {
            return NickHiderSupport2.iterate(var1, var2, var3);
         }

         @Override
         public String plain(String var1) {
            return NickHiderSupport2.plain(var1);
         }

         @Override
         public class_5481 segment(ClientRegistry5.TypeX var1, class_5481 var2, int var3) {
            NickHiderFactory var4 = NickHiderCache.D.computeIfAbsent(var1, var0 -> NickHiderFactory.of(var0.value(), var0.suggestion()));
            return var4 == null ? var2 : ClientRegistry5.snapshot(var4.segment(var2, var3));
         }
      };
   }

private static final class TypeB {
      static final NickHiderCache.TypeB u = new NickHiderCache.TypeB();
      
      final NickHiderBuilder T;
      final boolean o;
      final boolean E;
      final int L;
      
      private final Object[] b;
      
      private final Object F;
      
      private final Object I;
      private final Set<UUID> X;
      private final Set<String> D;

      private static final Integer[] c = new Integer[3];

      private TypeB() {
         this.T = null;
         this.o = false;
         this.E = false;
         this.L = 0;
         this.b = null;
         this.F = null;
         this.I = null;
         this.X = Set.of();
         this.D = Set.of();
      }

      TypeB( NickHiderBuilder var1, NickHiderModule var2,  Object var3,  Object var4, Set<UUID> var5, Set<String> var6) {
         long var7 = 2714688887183L;
         long var9 = var7 ^ 38232011338324L;
         int var11 = (int)((var7 ^ 38232011338324L) >>> 48);
         int var12 = (int)((var7 ^ 38232011338324L) << 16 >>> 32);
         int var13 = (int)(var9 << 48 >>> 48);
         this.T = var1;
         this.b = O((char)var11, var2, var12, (char)var13);
         this.o = var2.o.enabled();
         this.E = var2.W.enabled();
         this.L = Objects.hash(true, var2.g.get(), var2.E.get(), var2.R.get(), var2.l.get(), var2.A.get(), var2.S.get());
         this.F = var3;
         this.I = var4;
         this.X = var5;
         this.D = var6;
      }

      private static Object[] O(char var0, NickHiderModule var1, int var2, char var3) {
         return new Object[]{var1.g.get(), var1.E.get(), var1.R.get(), var1.o.get(), var1.l.get(), var1.A.get(), var1.W.get(), var1.S.get()};
      }

      boolean stale() {
         if (this.b == null) {
            return NickHiderCache.U;
         } else {
            class_310 var1 = class_310.method_1551();
            if (var1 != null && var1.field_1724 == this.F && var1.method_1562() == this.I) {
               NickHiderModule var2 = NickHiderCache.d;
               Object[] var3 = this.b;
               return var2.g.get() != var3[0]
                  || var2.E.get() != var3[1]
                  || var2.R.get() != var3[2]
                  || var2.o.get() != var3[3]
                  || var2.l.get() != var3[4]
                  || var2.A.get() != var3[5]
                  || var2.W.get() != var3[6]
                  || var2.S.get() != var3[7];
            } else {
               return true;
            }
         }
      }

      boolean isSelf( UUID var1,  String var2) {
         return var1 != null && this.X.contains(var1) || var2 != null && this.D.contains(var2.toLowerCase(Locale.ROOT));
      }

      boolean isOther( UUID var1,  String var2) {
         return var1 != null && NickHiderCache.x.contains(var1) || var2 != null && NickHiderCache.I.containsKey(var2.toLowerCase(Locale.ROOT));
      }

}

   private static final class TypeQ {
      final String d;
      final int u;
      
      private String g;
      
      private NickHiderCodec h;
      
      private String O;
      
      private String q;

      TypeQ(String var1, int var2) {
         this.d = var1;
         this.u = var2;
      }

      String name() {
         return this.d;
      }

      int number() {
         return this.u;
      }

      NickHiderCodec standIn(String var1) {
         if (this.h == null || !var1.equals(this.g)) {
            this.h = NickHiderCodec.parse(var1 + " " + this.u);
            this.g = var1;
         }

         return this.h;
      }
   }
}
