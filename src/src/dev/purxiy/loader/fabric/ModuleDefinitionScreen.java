// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import com.google.gson.JsonObject;
import dev.purxiy.loader.fabric.client.ClientRenderer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.class_11908;
import net.minecraft.class_2561;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_3675.class_307;

final class ModuleDefinitionScreen extends class_437 {
   private final class_437 parent;
   private final JsonObject entry;
   private final List<JsonObject> settings;
   private final Map<String, String> draft;
   private final List<String> labels = new ArrayList<>();
   private String error = "";
   private int page;
   private String keyBeingEdited;
   private class_4185 keyButton;

   ModuleDefinitionScreen(class_437 var1, JsonObject var2) {
      super(class_2561.method_43470(LinkAccountScreen.name(var2)));
      this.parent = var1;
      this.entry = var2;
      this.settings = var2.getAsJsonArray("settingDefinitions").asList().stream().map(var0 -> var0.getAsJsonObject()).filter(SettingSchema::editable).toList();
      this.draft = SettingSchema.read(var2);
   }

   @Override
   protected void method_25426() {
      this.keyBeingEdited = null;
      this.keyButton = null;
      this.labels.clear();
      int var1 = Math.min(this.field_22789 - 24, 360);
      int var2 = (this.field_22789 - var1) / 2;
      int var3 = Math.min(140, var1 / 2);
      int var4 = var2 + var1 - var3;
      int var5 = Math.max(1, (this.field_22790 - 108) / 24);
      this.page = Math.clamp((long)this.page, 0, Math.max(0, (this.settings.size() - 1) / var5));
      byte var6 = 48;

      for (int var7 = this.page * var5; var7 < Math.min(this.settings.size(), (this.page + 1) * var5); var7++) {
         JsonObject var8 = this.settings.get(var7);
         String var9 = var8.get("name").getAsString();
         String var10 = var8.get("id").getAsString();
         this.labels.add(this.field_22793.method_27523(var9, var1 - var3 - 10));
         String var11 = var8.get("kind").getAsString();
         switch (var11) {
            case "BooleanSetting":
               this.method_37063(ModuleClick.builder(toggleLabel(this.draft.get(var10)), var2x -> {
                  String var3x = Boolean.toString(!Boolean.parseBoolean(this.draft.get(var10)));
                  this.draft.put(var10, var3x);
                  var2x.method_25355(toggleLabel(var3x));
               }).method_46434(var4, var6, var3, 20).method_46431());
               break;
            case "ModeSetting":
               List var13 = var8.getAsJsonArray("choices").asList().stream().map(var0 -> var0.getAsString()).toList();
               this.method_37063(ModuleClick.builder(class_2561.method_43470(this.draft.get(var10)), var3x -> {
                  String var4x = (String)var13.get((var13.indexOf(this.draft.get(var10)) + 1) % var13.size());
                  this.draft.put(var10, var4x);
                  var3x.method_25355(class_2561.method_43470(var4x));
               }).method_46434(var4, var6, var3, 20).method_46431());
               break;
            case "KeySetting":
               this.method_37063(ModuleClick.builder(keyLabel(this.draft.get(var10)), var2x -> {
                  this.keyBeingEdited = var10;
                  this.keyButton = var2x;
                  var2x.method_25355(class_2561.method_43470("Druk op een toets…"));
               }).method_46434(var4, var6, var3, 20).method_46431());
               break;
            case "PlayerListSetting":
            case "NameListSetting":
            case "SelectionSetting":
               this.method_37063(
                  ModuleClick.builder(
                        class_2561.method_43470("Bewerken…"), var2x -> this.field_22787.method_1507(new SelectionEditorScreen(this, var8, this.draft))
                     )
                     .method_46434(var4, var6, var3, 20)
                     .method_46431()
               );
               break;
            case "NumberSetting":
            case "ColorSetting":
            case "TextSetting":
               boolean var14 = var8.get("kind").getAsString().equals("TextSetting");
               boolean var15 = var14 && var8.has("masked") && var8.get("masked").getAsBoolean();
               Object var16 = var15
                  ? new ModuleDefinitionScreen.TypeL(
                     this.field_22793, var4, var6, var3, class_2561.method_43470(var9), this.draft.get(var10), var2x -> this.draft.put(var10, var2x)
                  )
                  : new class_342(this.field_22793, var4, var6, var3, 20, class_2561.method_43470(var9));
               ((net.minecraft.class_342)(Object)var16).method_1880(var14 ? (var8.has("maxLength") ? var8.get("maxLength").getAsInt() : 1024) : 32);
               if (!var15) {
                  ((net.minecraft.class_342)(Object)var16).method_1852(this.draft.get(var10));
                  ((net.minecraft.class_342)(Object)var16).method_1863(var2x -> this.draft.put(var10, var2x));
               }

               try { class_437.class.getDeclaredMethod("method_37063", class_364.class).invoke(this, var16); } catch (ReflectiveOperationException ignored) {}
               break;
            default:
               throw new IllegalStateException("Unknown editable setting");
         }

         var6 += 24;
      }

      int var17 = Math.max(35, (var1 - 12) / 4);
      int var18 = this.field_22790 - 28;
      this.method_37063(
            ModuleClick.builder(class_2561.method_43470("Vorige"), var1x -> this.changePage(-1)).method_46434(var2, var18, var17, 20).method_46431()
         )
         .field_22763 = this.page > 0;
      this.method_37063(
            ModuleClick.builder(class_2561.method_43470("Volgende"), var1x -> this.changePage(1))
               .method_46434(var2 + var17 + 4, var18, var17, 20)
               .method_46431()
         )
         .field_22763 = (this.page + 1) * var5 < this.settings.size();
      this.method_37063(
         ModuleClick.builder(class_2561.method_43470("Terug"), var1x -> this.method_25419())
            .method_46434(var2 + 2 * (var17 + 4), var18, var17, 20)
            .method_46431()
      );
      this.method_37063(
         ModuleClick.builder(class_2561.method_43470("Opslaan"), var1x -> this.save()).method_46434(var2 + 3 * (var17 + 4), var18, var17, 20).method_46431()
      );
   }

   private void changePage(int var1) {
      this.keyBeingEdited = null;
      this.keyButton = null;
      this.page += var1;
      this.method_37067();
      this.method_25426();
   }

   private void save() {
      try {
         SettingSchema.save(this.entry, this.draft);
         LoaderMessages.reconfigure(this.entry);
         this.method_25419();
      } catch (IllegalArgumentException var2) {
         this.error = "Ongeldige waarde voor " + var2.getMessage();
      } catch (IOException var3) {
         this.error = "Opslaan is niet gelukt.";
      }
   }

   private static class_2561 toggleLabel(String var0) {
      return class_2561.method_43470(Boolean.parseBoolean(var0) ? "Aan" : "Uit");
   }

   private static class_2561 keyLabel(String var0) {
      int var1 = Integer.parseInt(var0);
      return (class_2561)(var1 == -1 ? class_2561.method_43470("Niet ingesteld") : class_307.field_1668.method_1447(var1).method_27445());
   }

   @Override
   public boolean method_25404(class_11908 var1) {
      if (this.keyBeingEdited == null) {
         return super.method_25404(var1);
      } else {
         int var2 = var1.comp_4795();
         if (var2 != 256) {
            this.draft.put(this.keyBeingEdited, Integer.toString(var2 != 259 && var2 != 261 ? var2 : -1));
         }

         this.keyButton.method_25355(keyLabel(this.draft.get(this.keyBeingEdited)));
         this.keyBeingEdited = null;
         this.keyButton = null;
         return true;
      }
   }

   @Override
   public void method_25419() {
      this.field_22787.method_1507(this.parent);
   }

   @Override
   public boolean method_25421() {
      return ClientServices.interfaceLook().pause();
   }

   @Override
   public void method_25394(class_332 var1, int var2, int var3, float var4) {
      ClientRenderer.render(var2, var3);
      super.method_25394(var1, var2, var3, var4);
      int var5 = (this.field_22789 - Math.min(this.field_22789 - 24, 360)) / 2;
      var1.method_27534(this.field_22793, this.field_22785, this.field_22789 / 2, 14, -1);
      if (!this.error.isEmpty()) {
         var1.method_27534(this.field_22793, class_2561.method_43470(this.error), this.field_22789 / 2, 30, -34953);
      }

      for (int var6 = 0; var6 < this.labels.size(); var6++) {
         var1.method_25303(this.field_22793, this.labels.get(var6), var5, 54 + var6 * 24, -2236963);
      }
   }

   private static final class TypeL extends class_342 {
      private String raw;
      private boolean rewriting;

      TypeL(class_327 var1, int var2, int var3, int var4, class_2561 var5, String var6, Consumer<String> var7) {
         super(var1, var2, var3, var4, 20, var5);
         this.raw = var6;
         this.method_1880(Math.max(1024, var6.length()));
         this.method_1852("•".repeat(var6.length()));
         this.method_1863(var2x -> {
            if (!this.rewriting) {
               this.raw = var2x;
               var7.accept(var2x);
            }
         });
      }

      @Override
      public void method_25365(boolean var1) {
         super.method_25365(var1);
         if (this.raw != null) {
            this.rewriting = true;

            try {
               this.method_1852(var1 ? this.raw : "•".repeat(this.raw.length()));
            } finally {
               this.rewriting = false;
            }
         }
      }
   }
}
