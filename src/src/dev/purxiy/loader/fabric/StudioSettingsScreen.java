// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.IntegerSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.Setting;
import dev.purxiy.config.TextSetting;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.clickgui.Studio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.class_11908;
import net.minecraft.class_11909;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_357;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_3675.class_307;

public class StudioSettingsScreen extends class_437 {
   private final ModuleDescriptor module;
   private final Studio studio;
   private final DrawContext frame = new DrawContext();
   private final List<Runnable> refresh = new ArrayList<>();
   private List<Setting<?>> visible = List.of();
   private List<Setting<?>> rows = List.of();
   private IntegerSetting keyTarget;
   private int page;
   private int left;
   private int panelWidth;
   private int panelBottom;
   private boolean dockRight;
   private boolean open;
   private boolean pressed;

   public StudioSettingsScreen(ModuleDescriptor var1, Studio var2) {
      super(class_2561.method_43470(var2.title()));
      this.module = var1;
      this.studio = var2;
   }

   @Override
   protected void method_25426() {
      if (!this.open) {
         this.studio.setOpen(true);
         this.open = true;
      }

      this.rebuildSettings();
   }

   private List<Setting<?>> visibleSettings() {
      return this.module.settings().stream().filter(Setting::visible).toList();
   }

   private void rebuildSettings() {
      this.method_37067();
      this.refresh.clear();
      this.keyTarget = null;
      this.visible = this.visibleSettings();
      this.dockRight = this.studio.dock().right();
      this.panelWidth = Math.min(200, Math.max(84, this.field_22789 / 3));
      this.left = this.dockRight ? this.field_22789 - this.panelWidth - 8 : 8;
      int var1 = Math.max(1, Math.min(11, (this.field_22790 - 61) / 22));
      int var2 = Math.max(0, (this.visible.size() - 1) / var1);
      this.page = Math.clamp((long)this.page, 0, var2);
      this.rows = this.visible.subList(this.page * var1, Math.min(this.visible.size(), (this.page + 1) * var1));
      int var3 = Math.max(38, this.panelWidth / 2 - 6);
      int var4 = this.left + this.panelWidth - var3 - 5;

      for (int var5 = 0; var5 < this.rows.size(); var5++) {
         Setting var6 = this.rows.get(var5);
         int var7 = 29 + 22 * var5;
         if (var6 instanceof NumberSetting var8) {
            StudioSettingsScreen.TypeZ var9 = new StudioSettingsScreen.TypeZ(var4, var7, var3, var8);
            this.method_37063(var9);
            List var10 = this.refresh;
            var10.add((Runnable) var9::refresh);
         } else if (var6 instanceof BooleanSetting var13) {
            Supplier var18 = () -> class_2561.method_43470(var13.enabled() ? "On" : "Off");
            this.addChoice(var4, var7, var3, var18, var13::toggle);
         } else if (var6 instanceof ModeSetting var14) {
            this.addChoice(
               var4, var7, var3, () -> class_2561.method_43470(this.field_22793.method_27523(var14.get(), var3 - 10)), () -> var14.select(var14.index() + 1)
            );
         } else if (var6 instanceof IntegerSetting var15) {
            this.addChoice(
               var4,
               var7,
               var3,
               () -> (class_2561)(this.keyTarget == var15
                  ? class_2561.method_43470("Press key")
                  : (var15.get() < 0 ? class_2561.method_43470("Unbound") : class_307.field_1668.method_1447(var15.get()).method_27445())),
               () -> this.keyTarget = var15
            );
         } else if (var6 instanceof ColorSetting var16) {
            class_342 var19 = new class_342(this.field_22793, var4, var7, var3, 20, class_2561.method_43470(var16.name()));
            var19.method_1880(8);
            var19.method_1852(Integer.toHexString(var16.get()));
            var19.method_1863(var1x -> {
               try {
                  if (var1x.length() == 6 || var1x.length() == 8) {
                     var16.set((int)Long.parseLong(var1x, 16));
                  }
               } catch (NumberFormatException var3x) {
               }
            });
            this.method_37063(var19);
         } else {
            if (!(var6 instanceof TextSetting var17)) {
               throw new IllegalStateException("Unsupported studio setting: " + var6.id());
            }

            class_342 var20 = new class_342(this.field_22793, var4, var7, var3, 20, class_2561.method_43470(var17.name()));
            var20.method_1880(1024);
            var20.method_1852(var17.get());
            var20.method_1863(var17::set);
            this.method_37063(var20);
         }
      }

      int var11 = 29 + 22 * this.rows.size();
      int var12 = (this.panelWidth - 14) / 2;
      this.method_37063(
            ModuleClick.builder(class_2561.method_43470("Previous"), var1x -> this.movePage(-1)).method_46434(this.left + 5, var11, var12, 20).method_46431()
         )
         .field_22763 = this.page > 0;
      this.method_37063(
            ModuleClick.builder(class_2561.method_43470("Next"), var1x -> this.movePage(1))
               .method_46434(this.left + 9 + var12, var11, var12, 20)
               .method_46431()
         )
         .field_22763 = this.page < var2;
      this.panelBottom = var11 + 25;
   }

   private void addChoice(int var1, int var2, int var3, Supplier<class_2561> var4, Runnable var5) {
      class_4185 var6 = ModuleClick.builder((class_2561)var4.get(), var1x -> var5.run()).method_46434(var1, var2, var3, 20).method_46431();
      this.method_37063(var6);
      this.refresh.add(() -> var6.method_25355((class_2561)var4.get()));
   }

   private void movePage(int var1) {
      this.page += var1;
      this.rebuildSettings();
   }

   @Override
   public void method_25420(class_332 var1, int var2, int var3, float var4) {
   }

   @Override
   public void method_25394(class_332 var1, int var2, int var3, float var4) {
      if (this.dockRight != this.studio.dock().right() || !this.visible.equals(this.visibleSettings())) {
         this.rebuildSettings();
      }

      this.refresh.forEach(Runnable::run);
      RenderPipelines var5 = RenderPipelines.get();
      Canvas var6 = var5.menuCanvas();
      boolean var7 = this.inPanel(var2, var3);
      this.frame.begin(var6, var5.time(), var5.delta(), var5.fromGui(var7 ? -100.0 : var2), var5.fromGui(var7 ? -100.0 : var3), this.pressed);
      this.studio.render(this.frame, 1.0F, var5.fromGui(this.dockRight ? this.left : this.left + this.panelWidth));
      var1.method_25294(this.left, 8, this.left + this.panelWidth, this.panelBottom, -535292893);
      var1.method_25303(this.field_22793, this.module.name(), this.left + 5, 16, -1385217);

      for (int var8 = 0; var8 < this.rows.size(); var8++) {
         var1.method_25303(
            this.field_22793, this.field_22793.method_27523(this.rows.get(var8).name(), this.panelWidth / 2 - 8), this.left + 5, 35 + 22 * var8, -1976338
         );
      }

      super.method_25394(var1, var2, var3, var4);
      if (this.studio.consumeCloseRequest()) {
         this.method_25419();
      }
   }

   private boolean inPanel(double var1, double var3) {
      return var1 >= this.left && var1 < this.left + this.panelWidth && var3 >= 8.0 && var3 < this.panelBottom;
   }

   private float coordinate(double var1) {
      return RenderPipelines.get().fromGui(var1);
   }

   @Override
   public boolean method_25402(class_11909 var1, boolean var2) {
      if (this.inPanel(var1.comp_4798(), var1.comp_4799())) {
         super.method_25402(var1, var2);
         return true;
      } else {
         this.pressed = var1.method_74245() == 0;
         return this.studio.mouseDown(this.coordinate(var1.comp_4798()), this.coordinate(var1.comp_4799()), var1.method_74245())
            || super.method_25402(var1, var2);
      }
   }

   @Override
   public boolean method_25403(class_11909 var1, double var2, double var4) {
      if (super.method_25403(var1, var2, var4)) {
         return true;
      } else {
         this.studio.mouseDrag(this.coordinate(var1.comp_4798()), this.coordinate(var1.comp_4799()));
         return this.pressed;
      }
   }

   @Override
   public boolean method_25406(class_11909 var1) {
      super.method_25406(var1);
      this.studio.mouseUp(this.coordinate(var1.comp_4798()), this.coordinate(var1.comp_4799()), var1.method_74245());
      this.pressed = false;
      return true;
   }

   @Override
   public boolean method_25401(double var1, double var3, double var5, double var7) {
      return this.inPanel(var1, var3) || this.studio.scroll(this.coordinate(var1), this.coordinate(var3), var7);
   }

   @Override
   public boolean method_25404(class_11908 var1) {
      if (this.keyTarget != null) {
         if (var1.comp_4795() == 259 || var1.comp_4795() == 261) {
            this.keyTarget.set(-1);
         } else if (var1.comp_4795() >= 32 && var1.comp_4795() <= 348 && var1.comp_4795() != 256) {
            this.keyTarget.set(var1.comp_4795());
         }

         this.keyTarget = null;
         return true;
      } else {
         return this.method_25399() instanceof class_342 && var1.comp_4795() != 256
            ? super.method_25404(var1)
            : this.studio.keyPressed(var1.comp_4795()) || super.method_25404(var1);
      }
   }

   @Override
   public boolean method_25421() {
      return false;
   }

   @Override
   public void method_25419() {
      try {
         CosmeticRegistry.storeSettings(this.module.id(), this.module.values());
      } catch (IOException | IllegalArgumentException var2) {
         if (this.field_22787.field_1724 != null) {
            this.field_22787.field_1724.method_7353(class_2561.method_43470("Studio settings could not be saved."), false);
         }

         return;
      }

      this.field_22787.method_1507(CosmeticRegistry.menu());
   }

   @Override
   public void method_25432() {
      if (this.open) {
         this.open = false;
         this.pressed = false;
         this.studio.setOpen(false);
      }
   }

   private static final class TypeZ extends class_357 {
      private final NumberSetting setting;

      TypeZ(int var1, int var2, int var3, NumberSetting var4) {
         super(var1, var2, var3, 20, class_2561.method_43473(), var4.normalized());
         this.setting = var4;
         this.method_25346();
      }

      void refresh() {
         this.field_22753 = this.setting.normalized();
         this.method_25346();
      }

      @Override
      protected void method_25346() {
         this.method_25355(class_2561.method_43470(this.setting.display()));
      }

      @Override
      protected void method_25344() {
         this.setting.setNormalized(this.field_22753);
      }
   }
}
