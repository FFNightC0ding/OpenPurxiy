// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import com.google.gson.JsonObject;
import dev.purxiy.loader.fabric.client.ClientRenderer;
import java.io.IOException;
import java.util.ArrayList;
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
import net.minecraft.class_3675.class_307;

final class LinkAccountScreen extends class_437 {
   private static final int ROW_HEIGHT = 24;
   private final List<class_4185> rows = new ArrayList<>();
   private final Map<class_4185, JsonObject> toggles = new LinkedHashMap<>();
   private String query = "";
   private int page;
   private class_4185 previous;
   private class_4185 next;
   private JsonObject binding;
   private class_4185 bindingButton;
   private long pageRevision;

   LinkAccountScreen() {
      super(class_2561.method_43470("Purxiy"));
   }

   @Override
   protected void method_25426() {
      this.rows.clear();
      this.binding = null;
      this.bindingButton = null;
      int var1 = Math.min(360, this.field_22789 - 24);
      int var2 = (this.field_22789 - var1) / 2;
      int var3 = Math.min(118, var1 / 2);
      this.method_37063(
         ModuleClick.builder(class_2561.method_43470("Account koppelen"), var0 -> LoaderMessages.link())
            .method_46434(var2 + var1 - var3, 62, var3, 20)
            .method_46431()
      );
      class_342 var4 = new class_342(this.field_22793, var2, 62, var1 - var3 - 8, 20, class_2561.method_43470("Modules zoeken"));
      var4.method_47404(class_2561.method_43470("Modules zoeken"));
      var4.method_1880(80);
      var4.method_1852(this.query);
      var4.method_1863(var1x -> {
         this.query = var1x;
         this.page = 0;
         this.refresh();
      });
      this.method_37063(var4);
      this.previous = this.method_37063(ModuleClick.builder(class_2561.method_43470("Vorige"), var1x -> {
         this.page--;
         this.refresh();
      }).method_46434(var2, this.field_22790 - 28, 75, 20).method_46431());
      this.next = this.method_37063(ModuleClick.builder(class_2561.method_43470("Volgende"), var1x -> {
         this.page++;
         this.refresh();
      }).method_46434(var2 + var1 - 75, this.field_22790 - 28, 75, 20).method_46431());
      this.refresh();
   }

   private void refresh() {
      this.pageRevision = CosmeticRegistry.revision();
      this.binding = null;
      this.bindingButton = null;
      this.rows.forEach(var1x -> this.method_37066(var1x));
      this.rows.clear();
      this.toggles.clear();
      int var1 = Math.min(360, this.field_22789 - 24);
      int var2 = (this.field_22789 - var1) / 2;
      int var3 = Math.max(1, (this.field_22790 - 130) / 24);
      List var4 = LoaderMessages.catalog
         .asList()
         .stream()
         .map(var0 -> var0.getAsJsonObject())
         .filter(
            var1x -> {
               String var2x = name(var1x);
               return (var2x + " " + var1x.get("id").getAsString() + " " + (var1x.has("category") ? var1x.get("category").getAsString() : ""))
                  .toLowerCase(Locale.ROOT)
                  .contains(this.query.strip().toLowerCase(Locale.ROOT));
            }
         )
         .toList();
      this.page = Math.clamp((long)this.page, 0, Math.max(0, (var4.size() - 1) / var3));
      this.previous.field_22763 = this.page > 0;
      this.next.field_22763 = (this.page + 1) * var3 < var4.size();
      byte var5 = 94;

      for (int var6 = this.page * var3; var6 < Math.min(var4.size(), (this.page + 1) * var3); var6++) {
         JsonObject var7 = (JsonObject)var4.get(var6);
         boolean var8 = CosmeticRegistry.available(var7.get("id").getAsString());
         boolean var9 = var8
            || var7.has("settingDefinitions")
               && var7.getAsJsonArray("settingDefinitions").asList().stream().anyMatch(var0 -> SettingSchema.editable(var0.getAsJsonObject()));
         int var10 = var9 ? 96 : 0;
         int var11 = ModuleCatalog.keyToggles(var7) ? 52 : 0;
         class_4185 var12 = this.method_37063(
            ModuleClick.builder(rowLabel(var7), var1x -> LoaderMessages.toggle(var7)).method_46434(var2, var5, var1 - var10 - var11, 20).method_46431()
         );
         var12.field_22763 = ModuleCatalog.toggleable(var7);
         this.rows.add(var12);
         this.toggles.put(var12, var7);
         if (var11 > 0) {
            this.rows.add(this.method_37063(ModuleClick.builder(bindingLabel(var7), var2x -> {
               this.binding = var7;
               this.bindingButton = var2x;
               var2x.method_25355(class_2561.method_43470("…"));
            }).method_46434(var2 + var1 - var10 - var11 + 4, var5, var11 - 4, 20).method_46431()));
         }

         if (var9) {
            this.rows.add(this.method_37063(ModuleClick.builder(class_2561.method_43470(var8 ? "Openen" : "Instellingen"), var2x -> {
               if (CosmeticRegistry.available(var7.get("id").getAsString())) {
                  CosmeticRegistry.open(var7.get("id").getAsString());
               } else {
                  this.field_22787.method_1507(new ModuleDefinitionScreen(this, var7));
               }
            }).method_46434(var2 + var1 - var10 + 4, var5, var10 - 4, 20).method_46431()));
         }

         var5 += 24;
      }
   }

   private static class_2561 bindingLabel(JsonObject var0) {
      int var1 = ModuleCatalog.key(var0);
      return (class_2561)(var1 == -1 ? class_2561.method_43470("Toets") : class_307.field_1668.method_1447(var1).method_27445());
   }

   @Override
   public boolean method_25404(class_11908 var1) {
      if (this.binding == null) {
         return super.method_25404(var1);
      } else {
         int var2 = var1.comp_4795();
         if (var2 != 256) {
            try {
               ModuleCatalog.key(this.binding, var2 != 261 && var2 != 259 ? var2 : -1);
            } catch (IOException | IllegalArgumentException var4) {
               LoaderMessages.status = "Toets opslaan is niet gelukt.";
            }
         }

         this.bindingButton.method_25355(bindingLabel(this.binding));
         this.binding = null;
         this.bindingButton = null;
         return true;
      }
   }

   @Override
   public void method_25393() {
      if (this.pageRevision != CosmeticRegistry.revision()) {
         this.refresh();
      }

      this.toggles.forEach((var0, var1) -> var0.method_25355(rowLabel(var1)));
   }

   static String name(JsonObject var0) {
      return var0.has("name") ? var0.get("name").getAsString() : var0.get("id").getAsString();
   }

   private static class_2561 rowLabel(JsonObject var0) {
      ModuleState var1 = ModuleState.active;
      String var2 = var0.get("id").getAsString();
      String var3 = "Uit";
      if (var1 != null && var1.runtime.isActive(var2)) {
         var3 = "Aan";
      } else if (LoaderMessages.requested(var2)) {
         var3 = "Laden…";
      }

      String var4 = name(var0);
      return class_2561.method_43470(var4 + " · " + var3);
   }

   @Override
   public void method_25394(class_332 var1, int var2, int var3, float var4) {
      ClientRenderer.render(var2, var3);
      super.method_25394(var1, var2, var3, var4);
      var1.method_27534(this.field_22793, this.field_22785, this.field_22789 / 2, 15, -1);
      var1.method_27534(
         this.field_22793,
         class_2561.method_43470(this.field_22793.method_27523(LoaderMessages.status, this.field_22789 - 24)),
         this.field_22789 / 2,
         40,
         -2236963
      );
   }

   @Override
   public boolean method_25421() {
      return ClientServices.interfaceLook().pause();
   }
}
