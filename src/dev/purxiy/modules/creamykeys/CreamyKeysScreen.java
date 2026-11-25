package dev.purxiy.modules.creamykeys;

import dev.purxiy.config.Setting;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleClick;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_4185;
import net.minecraft.class_437;

public final class CreamyKeysScreen extends class_437 {
   private final CreamyKeysModule W;
   private final List<class_4185> R = new ArrayList<>();
   private String X = "";
   private static final long c = 2697589901154582593L;

   public CreamyKeysScreen(CreamyKeysModule var1) {
      super(class_2561.method_43470("Creamy Keys"));
      this.W = var1;
   }

   protected void method_25426() {
      this.R.clear();
      int var1 = 0;

      for (CreamyKeySounds.Style var5 : CreamyKeySounds.Style.values()) {
         class_4185 var6 = ModuleClick.builder(class_2561.method_43470(var5.F), var2 -> {
            long var3 = 48412964661021L;
            this.W.C.set(var5.F);
            this.W.preview(var5, 65);
            this.X(var3);
         }).method_46434(this.field_22789 / 2 - 100, 48 + var1++ * 25, 200, 20).method_46431();
         this.method_37063(var6);
         this.R.add(var6);
      }

      String[] var7 = new String[]{"Left click", "Right click", "Middle click"};

      for (int var8 = 0; var8 < 3; var8++) {
         int var9 = var8;
         class_4185 var10 = ModuleClick.builder(class_2561.method_43470(var7[var8]), var2 -> this.W.previewMouse(var9))
            .method_46434(this.field_22789 / 2 - 154 + var8 * 105, 160, 98, 20)
            .method_46431();
         this.method_37063(var10);
         this.R.add(var10);
      }

      this.method_37063(
         ModuleClick.builder(class_2561.method_43470("All settings"), var1x -> CosmeticRegistry.settings(this.W.id()))
            .method_46434(this.field_22789 / 2 - 100, this.field_22790 - 52, 96, 20)
            .method_46431()
      );
      this.method_37063(
         ModuleClick.builder(class_2561.method_43470("Done"), var1x -> this.method_25419())
            .method_46434(this.field_22789 / 2 + 4, this.field_22790 - 52, 96, 20)
            .method_46431()
      );
   }

   private void X(long var1) {
      CreamyKeysModule.ensureSourceClassInitialized();
      LinkedHashMap var3 = new LinkedHashMap();

      for (Setting var5 : this.W.settings()) {
         long var6 = 48412964661021L;
         int var8 = var6 < 0L ? -1 : 1;
         var3.put(var5.id(), var5.save().getAsString());
      }

      try {
         long var10 = 48412964661021L;
         int var11 = var10 < 0L ? -1 : 1;
         CosmeticRegistry.storeSettings(this.W.id(), var3);
         this.X = "";
      } catch (IOException var9) {
         this.X = "Could not save style";
      }
   }

   public boolean previewHovered() {
      return this.R.stream().anyMatch(class_339::method_49606);
   }

   public void method_25393() {
      this.W.audition();
   }

   public void method_25394(class_332 var1, int var2, int var3, float var4) {
      super.method_25394(var1, var2, var3, var4);
      var1.method_27534(this.field_22793, this.field_22785, this.field_22789 / 2, 22, -1714177);
      var1.method_25300(this.field_22793, "Press any key to listen. Style: " + (String)this.W.C.get(), this.field_22789 / 2, 190, -4609332);
      if (!this.X.isEmpty()) {
         var1.method_25300(this.field_22793, this.X, this.field_22789 / 2, this.field_22790 - 72, -28528);
      }
   }

   public boolean method_25421() {
      return false;
   }

   public void method_25419() {
      this.field_22787.method_1507(CosmeticRegistry.menu());
   }
}
