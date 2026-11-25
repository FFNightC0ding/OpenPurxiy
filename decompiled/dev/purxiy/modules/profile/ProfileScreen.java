package dev.purxiy.modules.profile;

import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleClick;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;

final class ProfileScreen extends class_437 {
   private final ProfileModule C;

   ProfileScreen(ProfileModule var1) {
      super(class_2561.method_43470("Profile"));
      this.C = var1;
   }

   protected void method_25426() {
      this.method_37063(
         ModuleClick.builder(class_2561.method_43470("Settings"), var1 -> CosmeticRegistry.settings(this.C.id()))
            .method_46434(this.field_22789 / 2 - 105, this.field_22790 - 29, 100, 20)
            .method_46431()
      );
      this.method_37063(
         ModuleClick.builder(class_2561.method_43470("Done"), var1 -> this.method_25419())
            .method_46434(this.field_22789 / 2 + 5, this.field_22790 - 29, 100, 20)
            .method_46431()
      );
   }

   public void method_25394(class_332 var1, int var2, int var3, float var4) {
      super.method_25394(var1, var2, var3, var4);
      ProfileLoader3 var5 = ProfileSupport2.lifetime(ProfileUpdater.data());
      var1.method_25300(this.field_22793, this.C.displayName(), this.field_22789 / 2, 24, -1385217);
      String[] var6 = new String[]{
         "Play time: " + ProfileSupport.duration(var5.A),
         "Distance: " + ProfileSupport.distance(var5.j + var5.B + var5.u + var5.J + var5.y + var5.d),
         "Blocks placed: " + var5.t,
         "Blocks broken: " + var5.E,
         "Kills: " + var5.k,
         "Deaths: " + var5.g
      };

      for (int var7 = 0; var7 < var6.length; var7++) {
         var1.method_25300(this.field_22793, var6[var7], this.field_22789 / 2, 48 + var7 * 15, -1976338);
      }
   }

   public boolean method_25421() {
      return false;
   }

   public void method_25419() {
      this.field_22787.method_1507(CosmeticRegistry.menu());
   }
}
