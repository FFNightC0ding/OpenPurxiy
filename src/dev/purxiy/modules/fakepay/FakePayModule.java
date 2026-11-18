// Sourced By DevFFNightmare
package dev.purxiy.modules.fakepay;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.TextSetting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientSupport2;
import net.minecraft.class_1109;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_5250;
import net.minecraft.class_746;

public final class FakePayModule extends ModuleDescriptor {
   private static final int b = 16777215;
   private static final int T = 16733525;
   public final TextSetting I = this.add(new TextSetting("command", "Command", "The pay command to answer, without the slash", "pay", 32));
   public final ModeSetting W;
   public final TextSetting L;
   public final ColorSetting Q;
   public final BooleanSetting l;
   public final BooleanSetting K;
   public final BooleanSetting k;
   public static boolean B;





   public FakePayModule() {
      super("fake_pay", "FakePay", "A /pay only you see, with the server's message and sounds. For clips", "banknote");
      this.section("Message");
      this.W = this.mode("feedback", "Show in", "Where the message appears", "Both", new String[]{"Both", "Action bar", "Chat"});
      this.L = this.add(new TextSetting("currency", "Currency", "The symbol before the amount", "$", 4));
      this.Q = this.color("currency_color", "Currency colour", null, -16740609, false);
      this.l = this.bool("sounds", "Sounds", "A level-up when it works, a villager's no when it does not", true);
      this.section("Rules");
      this.K = this.bool("check_balance", "Check balance", "With FakeStats on, refuse payments larger than the fake balance", true);
      this.k = this.bool("self_guard", "No paying yourself", "Refuse paying your own name, like the real command", true);
   }

   private String p(long var1) {
      FakePayCodec.ensureSourceClassInitialized();
      String var3 = this.I.get().strip();
      long var4 = 16041544690558L;
      if (16041544690558L == 0L) {
         boolean var10000 = false;
      } else {
         int var8 = var4 < 0L ? -1 : 1;
      }

      if (var3.startsWith("/")) {
         var3 = var3.substring(1);
      }

      int var7 = var3.indexOf(32);
      return var7 < 0 ? var3 : var3.substring(0, var7);
   }

   private boolean R(long var1, String var3) {
      long var4 = 31696903680863L;
      long var6 = 29390280065863L;
      long var8 = 16041544690558L;
      long var10 = 14258429834338L;
      FakePayCodec.ensureSourceClassInitialized();
      String var12 = this.p(var8);
      if (this.enabled() && !var12.isEmpty() && ClientSupport2.name(var3).equalsIgnoreCase(var12)) {
         class_310 var13 = class_310.method_1551();
         class_746 var14 = var13.field_1724;
         if (var14 == null) {
            return false;
         } else {
            String[] var15 = var3.strip().split("\\s+");
            long var16 = 117672031357609L;
            if (117672031357609L == 0L) {
               boolean var10000 = false;
            } else {
               int var42 = var16 < 0L ? -1 : 1;
            }

            int var19 = var15.length;
            long var20 = 117672031357608L;
            if (117672031357608L == 0L) {
               boolean var43 = false;
            } else {
               int var44 = var20 < 0L ? -1 : 1;
            }

            if (var19 < 3) {
               this.M(var6, var13, class_2561.method_43470("Usage: /" + var15[0] + " <player> <amount>"));
               return true;
            } else {
               String var23 = var15[1];
               double var24 = FakePayCodec.parse(var15[2]);
               long var26 = 117672031357609L;
               if (117672031357609L == 0L) {
                  boolean var45 = false;
               } else {
                  int var46 = var26 < 0L ? -1 : 1;
               }

               long var28 = 117672031357608L;
               if (117672031357608L == 0L) {
                  boolean var47 = false;
               } else {
                  int var48 = var28 < 0L ? -1 : 1;
               }

               if (Double.isFinite(var24)) {
                  double var30;
                  var19 = (var30 = var24 - 0.0) == 0.0 ? 0 : (var30 < 0.0 ? -1 : 1);
                  long var32 = 117672031357608L;
                  if (117672031357608L == 0L) {
                     boolean var49 = false;
                  } else {
                     int var50 = var32 < 0L ? -1 : 1;
                  }

                  if (var19 > 0) {
                     if (this.k.enabled() && var23.equalsIgnoreCase(var14.method_7334().name())) {
                        this.M(var6, var13, class_2561.method_43470("You can't pay yourself!"));
                        return true;
                     }

                     boolean var34 = ClientSupport2.paysFromBalance();
                     if (var34 && this.K.enabled() && ClientSupport2.balance() < var24) {
                        this.M(var6, var13, class_2561.method_43470("You don't have enough money!"));
                        return true;
                     }

                     if (var34) {
                        ClientSupport2.pay(var24);
                     }

                     class_5250 var35 = class_2561.method_43470("You paid " + var23).method_54663(16777215);
                     String var36 = this.L.get().strip();
                     long var37 = 117672031357608L;
                     if (117672031357608L == 0L) {
                        boolean var51 = false;
                     } else {
                        int var52 = var37 < 0L ? -1 : 1;
                     }

                     if (!var36.isEmpty()) {
                        var35.method_10852(class_2561.method_43470(" " + var36).method_54663(this.Q.get() & 16777215))
                           .method_10852(class_2561.method_43470(FakePayCodec.shortForm(var24)).method_54663(16777215));
                        long var39 = 117672031357609L;
                        if (117672031357609L == 0L) {
                           boolean var53 = false;
                        } else {
                           int var54 = var39 < 0L ? -1 : 1;
                        }
                     } else {
                        var35.method_10852(class_2561.method_43470(" " + FakePayCodec.shortForm(var24)).method_54663(16777215));
                     }

                     this.L(var14, var35, var10);
                     this.o(var4, var13, class_3417.field_14709);
                     return true;
                  }
               }

               this.M(var6, var13, class_2561.method_43470("Invalid amount: " + var15[2]));
               return true;
            }
         }
      } else {
         return false;
      }
   }

   private void M(long var1, class_310 var3, class_5250 var4) {
      long var5 = 31696903680863L;
      long var7 = 14258429834338L;
      if (var3.field_1724 != null) {
         this.L(var3.field_1724, var4.method_54663(16733525), var7);
      }

      this.o(var5, var3, class_3417.field_15008);
   }

   private void L(class_746 var1, class_2561 var2, long var3) {
      FakePayCodec.ensureSourceClassInitialized();
      if (!this.W.is("Chat")) {
         var1.method_7353(var2, true);
      }

      if (!this.W.is("Action bar")) {
         var1.method_7353(var2, false);
      }
   }

   private void o(long var1, class_310 var3, class_3414 var4) {
      if (this.l.enabled()) {
         var3.method_1483().method_4873(class_1109.method_4757(var4, 1.0F, 1.0F));
      }
   }

   @Override
   protected void attach(ModuleContext var1) {
      var1.own(ClientSupport2.command(var1x -> this.R(117672031357609L, var1x)));
   }

}
