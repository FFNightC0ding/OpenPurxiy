package dev.purxiy.modules.fakestats;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.TextSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientSupport2;
import dev.purxiy.loader.fabric.client.ClientSupport2.Listener3;
import java.util.Locale;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_310;

public final class FakeStatsModule extends ModuleDescriptor implements Listener3 {
   private static final int Q = 32;
   private static final int T = 15;
   private static final String[] p = new String[]{"money", "balance", "bal", "coins", "cash", "$"};
   private static final String[] A = new String[]{"shard"};
   private static final String[] a = new String[]{"kill"};
   private static final String[] b = new String[]{"death"};
   private static final String[] U = new String[]{"playtime", "play time", "time played", "played"};
   public final ModeSetting u;
   public final TextSetting N;
   public final NumberSetting F;
   public final TextSetting g;
   public final NumberSetting l;
   public final TextSetting P;
   public final NumberSetting q;
   public final TextSetting j;
   public final NumberSetting J;
   public final TextSetting z;
   public final NumberSetting s;
   public final BooleanSetting L;
   public final BooleanSetting V;
   private double n;
   private String y;
   private int W;
   private int B;
   public static boolean v;

   public FakeStatsModule() {
      super("fake_stats", "FakeStats", "Your sidebar shows the money, shards and kills you type. Only on your screen", "chart-column");
      long var1 = 21250039993728L;
      long var3 = var1 ^ 15625135843928L;
      long var5 = var1 ^ 27439092116674L;
      int var7 = (int)((var1 ^ 27439092116674L) >>> 32);
      int var8 = (int)((var1 ^ 27439092116674L) << 32 >>> 48);
      int var9 = (int)(var5 << 48 >>> 48);
      this.y = "";
      this.u = this.mode(
         "match",
         "Find lines",
         "By position counts lines from the top (DonutSMP has no labels); By name looks for words such as Money or Kills",
         "By position",
         new String[]{"By position", "By name"}
      );
      this.section("Money");
      this.N = this.p("money", "Money", var3, "Your fake balance, such as 1m, 250k or 1500. Empty leaves it alone", "1m");
      this.F = this.j(var7, "money_line", "Money line", 1, (char)var8, (char)var9);
      this.section("Shards");
      this.g = this.p("shards", "Shards", var3, "Empty leaves the shards alone", "");
      this.l = this.j(var7, "shards_line", "Shards line", 2, (char)var8, (char)var9);
      this.section("Kills");
      this.P = this.p("kills", "Kills", var3, "Empty leaves the kills alone", "");
      this.q = this.j(var7, "kills_line", "Kills line", 3, (char)var8, (char)var9);
      this.section("Deaths");
      this.j = this.p("deaths", "Deaths", var3, "Empty leaves the deaths alone", "");
      this.J = this.j(var7, "deaths_line", "Deaths line", 4, (char)var8, (char)var9);
      this.section("Playtime");
      this.z = this.p("playtime", "Playtime", var3, "Any text, such as 365d 12h. Empty leaves it alone", "");
      this.s = this.j(var7, "playtime_line", "Playtime line", 5, (char)var8, (char)var9);
      this.section("Commands");
      this.L = this.bool("balance_command", "Answer /bal", "/bal, /balance and /money show your fake balance", true);
      this.V = this.bool("deduct_on_pay", "Pay from balance", "FakePay takes what you pay off the balance", true);
   }

   private TextSetting p(String var1, String var2, long var3, String var5, String var6) {
      return (TextSetting)this.add(new TextSetting(var1, var2, var5, var6, 32));
   }

   private NumberSetting j(int var1, String var2, String var3, int var4, char var5, char var6) {
      NumberSetting var7 = this.number(var2, var3, "Which sidebar line, counted from the top", var4, 1.0, 15.0, 1.0, Listener.INTEGER);
      var7.visibleWhen(() -> this.u.is("By position"));
      return var7;
   }

   protected void onEnable() {
      long var1 = 102104655783218L;
      long var3 = var1 ^ 55486084644143L;
      this.i(var3);
   }

   private void i(long var1) {
      FakeStatsCodec.ensureSourceClassInitialized();
      this.y = (String)this.N.get();
      double var3 = FakeStatsCodec.parse(this.y);
      this.n = !Double.isFinite(var3) ? 0.0 : Math.max(0.0, var3);
   }

   private void K(long var1) {
      long var3 = 121684139022365L;
      FakeStatsCodec.ensureSourceClassInitialized();
      if (!((String)this.N.get()).equals(this.y)) {
         this.i(var3);
      }
   }

   public boolean paysFromBalance() {
      return this.enabled() && this.V.enabled() && !((String)this.N.get()).isBlank();
   }

   public double balance() {
      long var1 = 76312391116251L;
      long var3 = var1 ^ 72871903601144L;
      this.K(var3);
      return this.n;
   }

   public void pay(double var1) {
      long var3 = 35148868099697L;
      long var5 = var3 ^ 27310234753618L;
      this.K(var5);
      this.n = Math.max(0.0, this.n - Math.max(0.0, var1));
   }

   public void beginSidebar() {
      this.W = 0;
      this.B = 0;
   }

   public class_2561 lineForWidth(class_2561 var1) {
      long var2 = 103874875189068L;
      long var4 = var2 ^ 86589205230583L;
      return this.enabled() ? this.n(var1, true, var4) : var1;
   }

   public class_2561 lineForDraw(class_2561 var1) {
      long var2 = 103308861493127L;
      long var4 = var2 ^ 84991633857340L;
      return this.enabled() ? this.n(var1, false, var4) : var1;
   }

   private class_2561 n(class_2561 var1, boolean var2, long var3) {
      long var5 = 7838635869219L;
      long var7 = 75646261551602L;
      FakeStatsCodec.ensureSourceClassInitialized();
      String var9 = class_124.method_539(var1.getString());
      if (var9 != null && !var9.isBlank()) {
         long var10 = 18390510523579L;
         int var13 = var10 < 0L ? -1 : 1;
         this.K(var5);
         var13 = var2 ? this.W++ : this.B++;
         String var14 = var9.toLowerCase(Locale.ROOT);
         if (this.q(this.N, this.F, var7, p, var13, var14)) {
            return FakeStatsCodec.replaceNumber(var1, FakeStatsCodec.shortForm(this.n));
         } else {
            long var15 = 18390510523579L;
            int var20 = var15 < 0L ? -1 : 1;
            String[] var20x = A;
            if (this.q(this.g, this.l, var7, var20x, var13, var14)) {
               return FakeStatsCodec.replaceNumber(var1, ((String)this.g.get()).strip());
            } else {
               String[] var19 = a;
               if (this.q(this.P, this.q, var7, var19, var13, var14)) {
                  return FakeStatsCodec.replaceNumber(var1, ((String)this.P.get()).strip());
               } else if (this.q(this.j, this.J, var7, b, var13, var14)) {
                  return FakeStatsCodec.replaceNumber(var1, ((String)this.j.get()).strip());
               } else {
                  return this.q(this.z, this.s, var7, U, var13, var14) ? FakeStatsCodec.replaceValue(var1, ((String)this.z.get()).strip()) : var1;
               }
            }
         }
      } else {
         return var1;
      }
   }

   private boolean q(TextSetting var1, NumberSetting var2, long var3, String[] var5, int var6, String var7) {
      FakeStatsCodec.ensureSourceClassInitialized();
      boolean var8 = ((String)var1.get()).isBlank();
      long var9 = 75646261551601L;
      int var12 = var9 < 0L ? -1 : 1;
      if (var8) {
         return false;
      } else {
         var8 = this.u.is("By position");
         long var12x = 75646261551602L;
         int var22 = var12x < 0L ? -1 : 1;
         if (var8) {
            long var22x = 75646261551602L;
            int var24 = var22x < 0L ? -1 : 1;
            return var6 + 1 == var2.intValue();
         } else {
            for (String var17 : var5) {
               long var18 = 75646261551601L;
               int var19 = var18 < 0L ? -1 : 1;
               if (var7.contains(var17)) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   private boolean D(char var1, int var2, short var3, String var4) {
      FakeStatsCodec.ensureSourceClassInitialized();
      if (this.enabled() && this.L.enabled() && !((String)this.N.get()).isBlank()) {
         String var5 = ClientSupport2.name(var4);
         if (!var5.equals("bal") && !var5.equals("balance") && !var5.equals("money")) {
            return false;
         } else {
            class_310 var6 = class_310.method_1551();
            if (var6.field_1724 == null) {
               return false;
            } else {
               String[] var7 = var4.strip().split("\\s+");
               int var8 = 272053387;
               var8 = 272053387;
               if (var7.length > 1 && !var7[1].equalsIgnoreCase(var6.field_1724.method_7334().name())) {
                  return false;
               } else {
                  var6.field_1724
                     .method_7353(
                        class_2561.method_43470("You have ")
                           .method_27692(class_124.field_1080)
                           .method_10852(class_2561.method_43470(FakeStatsCodec.shortForm(this.balance())).method_27692(class_124.field_1060)),
                        false
                     );
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   protected void attach(ModuleContext var1) {
      var1.own(ClientSupport2.stats(this));
      var1.own(ClientSupport2.command(var1x -> this.D('\u0000', 272053387, (short)-23684, var1x)));
   }
}
