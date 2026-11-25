package dev.purxiy.modules.keybinds;

import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.MenuKeyBinding;
import dev.purxiy.loader.fabric.ModuleClick;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.loader.fabric.MenuKeyBinding.BindingRow;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.minecraft.class_11908;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_4185;
import net.minecraft.class_437;

final class KeybindsScreen extends class_437 {
   private final class_437 e = CosmeticRegistry.menu();
   private final Map<String, KeybindsScreen.PendingRebind> H = new LinkedHashMap<>();
   private final List<class_4185> v = new ArrayList<>();
   private final List<String> K = new ArrayList<>();
   private String s = "";
   private String M = "";
   private String I = "";
   private String S;
   private int y;
   private class_4185 Y;
   private class_4185 R;
   public static int k = 0;
   private static final String[] b = new String[21];
   private static final long[] c = new long[]{
      8830162014679361895L,
      -4768542352318364740L,
      -8821438988025725995L,
      8184029383403103220L,
      -6826349950837209229L,
      6276570488872079262L,
      -6601050758276937625L,
      -4464294259074259277L,
      -4079527047056356410L,
      -7454855406262096834L,
      6311534770345000646L,
      -1908517499776216846L
   };
   private static final Integer[] d = new Integer[12];
   private static final long f = 5000L;

   KeybindsScreen() {
      super(class_2561.method_43470("Keybinds"));
   }

   protected void method_25426() {
      long var1 = 127375259799402L;
      long var3 = var1 ^ 47206493222773L;
      int var5 = (int)((var1 ^ 47206493222773L) >>> 48);
      int var6 = (int)((var1 ^ 47206493222773L) << 16 >>> 32);
      int var7 = (int)(var3 << 48 >>> 48);
      this.v.clear();
      this.S = null;
      int var8 = Math.min(520, this.field_22789 - 24);
      int var9 = (this.field_22789 - var8) / 2;
      class_342 var10 = new class_342(this.field_22793, var9, 40, var8, 20, class_2561.method_43470("Search bindings"));
      var10.method_47404(class_2561.method_43470("Search bindings"));
      var10.method_1880(80);
      var10.method_1852(this.s);
      var10.method_1863(var1x -> {
         long var2 = 98096035887135L;
         byte var4 = 0;
         int var5x = 1496826719;
         int var6x = (int)(var2 << 48 >>> 48);
         this.s = var1x;
         this.y = 0;
         this.p((char)var4, var5x, var6x);
      });
      this.method_37063(var10);
      this.Y = (class_4185)this.method_37063(ModuleClick.builder(class_2561.method_43470("Previous"), var1x -> {
         long var2 = 98096035887135L;
         byte var4 = 0;
         int var5x = 1496826719;
         int var6x = (int)(var2 << 48 >>> 48);
         this.y--;
         this.p((char)var4, var5x, var6x);
      }).method_46434(var9, this.field_22790 - 28, 85, 20).method_46431());
      this.R = (class_4185)this.method_37063(ModuleClick.builder(class_2561.method_43470("Next"), var1x -> {
         long var2 = 98096035887135L;
         byte var4 = 0;
         int var5x = 1496826719;
         int var6x = (int)(var2 << 48 >>> 48);
         this.y++;
         this.p((char)var4, var5x, var6x);
      }).method_46434(var9 + var8 - 85, this.field_22790 - 28, 85, 20).method_46431());
      this.method_37063(
         ModuleClick.builder(class_2561.method_43470("Back"), var1x -> this.method_25419())
            .method_46434(this.field_22789 / 2 - 40, this.field_22790 - 28, 80, 20)
            .method_46431()
      );
      this.p((char)var5, var6, var7);
   }

   private List e(long var1) {
      LinkedHashMap var3 = new LinkedHashMap();
      Iterator var4 = MenuKeyBinding.bound().iterator();

      while (true) {
         Object var5;
         if (var4.hasNext()) {
            var5 = var4.next();
         } else {
            var5 = this.H.values();
            if (var1 >= 0L) {
               ((Collection)var5).removeIf(var0 -> ((KeybindsScreen.PendingRebind)var0).L < System.currentTimeMillis());
               this.H.forEach((var1x, var2) -> var3.putIfAbsent(var1x, var2.l));
               return List.copyOf(var3.values());
            }
         }

         BindingRow var6 = (BindingRow)var5;
         if (var1 >= 0L) {
            var3.put(var6.id(), var6);
         }
      }
   }

   private void p(char var1, int var2, int var3) {
      long var6 = (long)var1 << 48 | (long)var2 << 32 >>> 16 | (long)var3 << 48 >>> 48;
      long var8 = var6 ^ 79043462761050L;
      long var10 = var6 ^ 6631862249165L;
      long var12 = var6 ^ 110334186203154L;
      this.S = null;
      this.v.forEach(var1x -> this.method_37066(var1x));
      this.v.clear();
      this.K.clear();
      byte var14 = 0;
      List<BindingRow> var15 = this.e(var8);
      this.I = var15.toString() + this.H.keySet();
      List<BindingRow> var16 = var15.stream()
         .filter(var1x -> (((BindingRow)var1x).title() + " " + ((BindingRow)var1x).action()).toLowerCase(Locale.ROOT).contains(this.s.strip().toLowerCase(Locale.ROOT)))
         .toList();
      HashMap<Object, Integer> var17 = new HashMap<>();

      for (BindingRow var19 : MenuKeyBinding.bound()) {
         var17.merge(var19.key(), 1, (a, b) -> a + b);
      }

      int var35 = Math.max(1, (this.field_22790 - a(9375, 6843827298119499537L ^ var6)) / a(120, 8730179433753123825L ^ var6));
      int var36 = Math.min(a(24401, 8535139484175418577L ^ var6), this.field_22789 - a(6234, 2011781462091531218L ^ var6));
      int var20 = (this.field_22789 - var36) / 2;
      this.y = Math.clamp((long)this.y, 0, Math.max(0, (var16.size() - 1) / var35));
      this.Y.field_22763 = this.y > 0;
      int var21 = this.y;
      if (var1 >= 0) {
         var21 = (this.y + 1) * var35;
      }

      this.R.field_22763 = var21 < var16.size();
      int var22 = a(2750, 2016119452000784692L ^ var6);

      for (BindingRow var24 : var16.subList(this.y * var35, Math.min(var16.size(), (this.y + 1) * var35))) {
         byte var25 = (byte)(this.H.containsKey(var24.id()) ? 1 : 0);
         String var26 = var24.title();
         String var27 = var24.action();
         int var28 = var17.getOrDefault(var24.key(), 0);
         byte var29 = var14;
         if (var1 >= 0) {
            var29 = 1;
         }

         String var30 = var28 > var29 && var25 == 0 ? " · shared key" : "";
         String var32 = var26 + " · " + var27 + var30;
         this.K.add(this.field_22793.method_27523(var32, Math.max(a(27616, 6631050692864503916L ^ var6), var36 - a(24726, 4467197516407372573L ^ var6))));
         class_4185 var33 = (class_4185)this.method_37063(
            ModuleClick.builder(class_2561.method_43470(var25 != 0 ? "None" : MenuKeyBinding.keyName(var24.key())), var4x -> {
                  this.S = var24.id();
                  var4x.method_25355(class_2561.method_43470("Press a key…"));
               })
               .method_46434(
                  var20 + var36 - a(3860, 3143392833437144215L ^ var6), var22, a(17733, 5190682678072925896L ^ var6), a(26703, 4780243401927095245L ^ var6)
               )
               .method_46431()
         );
         var33.field_22763 = var25 == 0;
         if (var3 >= 0) {
            this.v.add(var33);
            if (var1 >= 0) {
               if (var24.removable()) {
                  List var4;
                  String var5;
                  label67: {
                     var4 = this.v;
                     if (var2 > 0) {
                        if (var25 != 0) {
                           var5 = "Undo";
                           break label67;
                        }

                        int var33x = 1017167027;
                     }

                     var5 = "Remove";
                  }

                  var4.add(
                     this.method_37063(
                        ModuleClick.builder(class_2561.method_43470(var5), var5x -> {
                              long var7 = var10 ^ 6631862249165L;
                              int var9 = (int)((var10 ^ 6631862249165L) >>> 48);
                              int var10x = (int)((var10 ^ 6631862249165L) << 16 >>> 32);
                              int var11 = (int)(var7 << 48 >>> 48);
                              byte var12x = 0;

                              try {
                                 int var13;
                                 label40: {
                                    label39: {
                                       MenuKeyBinding.set(var24.id(), var25 != 0 ? this.H.get(var24.id()).l.key() : -1);
                                       var13 = var12x;
                                       if (var10 > 0L) {
                                          if (var25 == 0) {
                                             this.H.put(var24.id(), new KeybindsScreen.PendingRebind(var24, System.currentTimeMillis() + 5000L));
                                             break label39;
                                          }

                                          this.H.remove(var24.id());
                                          var13 = var12x;
                                       }

                                       if (var10 < 1L) {
                                          break label40;
                                       }
                                    }

                                    var13 = var25;
                                 }

                                 String var6x;
                                 label31: {
                                    if (var10 >= 1L) {
                                       if (var13 != 0) {
                                          var6x = "KEY_SET";
                                          break label31;
                                       }

                                       var13 = 627808867;
                                    }

                                    var6x = "TOGGLE_OFF";
                                 }

                                 UiAudioPolicy.play(var6x);
                                 this.M = "";
                                 this.p((char)var9, var10x, var11);
                              } catch (Exception var14x) {
                                 this.M = "Could not save this key.";
                              }
                           })
                           .method_46434(
                              var20 + var36 - a(27712, 2030516274424248271L ^ var6),
                              var22,
                              a(13090, 938417432604158115L ^ var6),
                              a(26703, 4780243401927095245L ^ var6)
                           )
                           .method_46431()
                     )
                  );
               }

               var22 += 26;
            }
         }
      }
   }

   public boolean method_25404(class_11908 var1) {
      long var2 = 55938562002540L;
      long var4 = var2 ^ 118575276848755L;
      int var6 = (int)((var2 ^ 118575276848755L) >>> 48);
      int var7 = (int)((var2 ^ 118575276848755L) << 16 >>> 32);
      int var8 = (int)(var4 << 48 >>> 48);
      if (this.S == null) {
         return super.method_25404(var1);
      } else {
         String var9 = this.S;
         this.S = null;
         if (var1.comp_4795() != 256) {
            try {
               int var10 = var1.comp_4795() != 261 && var1.comp_4795() != 259 ? var1.comp_4795() : -1;
               MenuKeyBinding.set(var9, var10);
               UiAudioPolicy.play("KEY_SET");
               this.M = "";
            } catch (Exception var111) {
               this.M = var9.equals("menu") ? "The menu must keep a key." : "Could not save this key.";
            }
         }

         this.p((char)var6, var7, var8);
         return true;
      }
   }

   public void method_25393() {
      long var1 = 54797388359674L;
      long var3 = var1 ^ 51689864582591L;
      long var5 = var1 ^ 115316675928037L;
      int var7 = (int)((var1 ^ 115316675928037L) >>> 48);
      int var8 = (int)((var1 ^ 115316675928037L) << 16 >>> 32);
      int var9 = (int)(var5 << 48 >>> 48);
      if (this.S == null && !this.I.equals(this.e(var3).toString() + this.H.keySet())) {
         this.p((char)var7, var8, var9);
      }
   }

   public void method_25419() {
      UiAudioPolicy.play("CLOSE");
      this.field_22787.method_1507(this.e);
   }

   public boolean method_25421() {
      return false;
   }

   public void method_25394(class_332 var1, int var2, int var3, float var4) {
      super.method_25394(var1, var2, var3, var4);
      var1.method_27534(this.field_22793, this.field_22785, this.field_22789 / 2, 15, -1);
      int var5 = (this.field_22789 - Math.min(520, this.field_22789 - 24)) / 2;

      for (int var6 = 0; var6 < this.K.size(); var6++) {
         var1.method_25303(this.field_22793, this.K.get(var6), var5, 82 + var6 * 26, -2236963);
      }

      if (this.K.isEmpty()) {
         var1.method_27534(this.field_22793, class_2561.method_43470("No bound keys"), this.field_22789 / 2, 85, -4473925);
      }

      if (!this.M.isEmpty()) {
         var1.method_27534(this.field_22793, class_2561.method_43470(this.M), this.field_22789 / 2, this.field_22790 - 50, -34953);
      }
   }

   private static int a(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 25493;
      if (d[var3] == null) {
         d[var3] = (int)(c[var3] ^ var1);
      }

      return d[var3];
   }

   private record PendingRebind(BindingRow l, long L) {
      public BindingRow binding() {
         return this.l;
      }

      public long until() {
         return this.L;
      }

      public BindingRow keybindRegistry_ModuleKeyBindingState1() {
         return this.l;
      }

      public long longState48() {
         return this.L;
      }
   }
}
