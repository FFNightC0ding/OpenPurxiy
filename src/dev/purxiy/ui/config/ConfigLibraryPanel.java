package dev.purxiy.ui.config;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.modules.Category;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.widget.Slider;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.class_156;

public final class ConfigLibraryPanel {
   private static final float TOP = 150.0F;
   private static final float GAP = 20.0F;
   private static final float BUTTON = 36.0F;
   private final ConfigLibraryThumbnail bar = new ConfigLibraryThumbnail();
   private final ConfigLibraryFade library = new ConfigLibraryFade();
   private final ConfigLibraryBanner discover = new ConfigLibraryBanner();
   private final ConfigLibraryMetrics views = new ConfigLibraryMetrics(new String[]{"Library", "Discover"}, new String[]{"library", "compass"}, true);
   private final Animator view = Animator.smooth(0.0F);
   private final Animator tabsShown = Animator.smooth(0.0F);
   private final Animator searchShown = Animator.smooth(0.0F);
   private final ConfigLibraryDash.DashBar importButton = new ConfigLibraryDash.DashBar();
   private final ConfigLibraryDash.DashBar newButton = new ConfigLibraryDash.DashBar();
   private final ConfigLibraryDash.DashBar emptyNew = new ConfigLibraryDash.DashBar();
   private final ConfigLibraryDash.DashBar emptyImport = new ConfigLibraryDash.DashBar();
   private final ConfigLibraryArc effects = new ConfigLibraryArc();
   private final ConfigLibraryRenderer toasts = new ConfigLibraryRenderer();
   private final Slider search = new Slider(new Slider.Listener() {
      @Override
      public String get() {
         return ConfigLibraryPanel.this.query;
      }

      @Override
      public void set(String var1) {
         String var2 = var1.replace('\n', ' ');
         if (!var2.equals(ConfigLibraryPanel.this.query)) {
            ConfigLibraryPanel.this.query = var2;
            ConfigLibraryPanel.this.library.scrollTo(0.0F);
            ConfigLibraryPanel.this.discover.grid.scrollTo(0.0F);
         }
      }

      @Override
      public int maxLength() {
         return 32;
      }

      @Override
      public String placeholder() {
         return "Search configs";
      }
   }).icon("search");
   private String query = "";

   private ConfigLibraryDetail sheet;

   private ConfigLibraryContextMenu menu;

   private ConfigLibraryPage dialog;

   private ConfigLibraryCover renaming;
   private boolean shown;
   private int direction;
   private float enterTime = -100.0F;
   private float exitTime = -100.0F;
   private float time;
   private float alpha;
   private boolean interactive;
   private float noticedAt = -10.0F;
   private float tabsX = -1.0F;

   public void show(float var1, int var2, float var3) {
      if (!this.shown) {
         this.shown = true;
         this.direction = var2;
         this.enterTime = var1 + var3;
         this.library.enter(this.enterTime);
         if (this.views.selected() == 1) {
            this.discover.enter(this.enterTime);
         }

         ConfigLibraryHost.checkStatus(false);
      }
   }

   public void hide(float var1, int var2) {
      if (this.shown) {
         this.shown = false;
         this.direction = var2;
         this.exitTime = var1;
         this.search.blur();
         this.finishRename(false);
         if (this.menu != null) {
            this.menu.close();
         }

         if (this.sheet != null) {
            this.sheet.close();
         }

         if (this.dialog != null) {
            this.dialog.close();
         }
      }
   }

   public boolean modal() {
      return this.sheet != null || this.menu != null || this.dialog != null;
   }

   public static String orbitLine() {
      int var0 = ConfigLibraryTransform.mine().size();
      String var1 = var0 + (var0 == 1 ? " config" : " configs");
      ConfigLibraryStats var2 = ConfigLibraryTransform.active();
      return var2 == null ? var1 : var1 + "  ·  " + (ConfigLibraryTransform.changedSinceSaved() ? "unsaved changes" : var2.name + " in use");
   }

   public static int inUse() {
      return ConfigLibraryTransform.active() != null ? 1 : 0;
   }

   private boolean discoverable() {
      return ConfigLibraryHost.online() || this.views.selected() == 1;
   }

   public void render(DrawContext var1, float var2, float var3, boolean var4) {
      float var5 = var1.time;
      this.time = var5;
      ConfigLibraryFile.tick(var5);
      if (this.shown && var5 - this.noticedAt > 1.0F) {
         this.noticedAt = var5;
         String[] var6 = ConfigLibraryUpdater.notice();
         if (var6 != null) {
            this.toasts.show(var6[0], var6[1], var5);
         }

         ConfigLibraryHost.checkStatus(false);
      }

      float var22 = this.shown ? 0.0F : (var5 - this.exitTime) * MotionStyle.MENU.speed() / 0.18F;
      this.interactive = false;
      this.alpha = 0.0F;
      if ((this.shown || !(var22 >= 1.0F)) && !(var2 <= 0.01F)) {
         Canvas var7 = var1.canvas;
         float var8 = var4 ? smooth((var2 - 0.45F) / 0.4F) : smooth(var2 * 1.6F - 0.3F);
         float var9 = var8 * var3 * (this.shown ? 1.0F : 1.0F - Easing.CUBIC_OUT.apply(clamp01(var22)));
         this.alpha = var9;
         if (!(var9 <= 0.005F)) {
            float var10 = (var5 - this.enterTime) * MotionStyle.CASCADE.speed();
            this.interactive = this.shown && !var1.masked() && var2 > 0.95F && var3 > 0.95F && !this.modal();
            boolean var11 = this.interactive;
            float var12 = var7.width();
            float var13 = var7.height();
            float var14 = Math.max(32.0F, Math.min(72.0F, var12 * 0.045F));
            float var15 = var12 - var14 * 2.0F;
            float var16 = var13 - 150.0F - 46.0F;
            float var17 = this.shown ? this.direction * 30 * (1.0F - Easing.EMPHASIZED.apply(clamp01(var10 / 0.5F))) : -this.direction * 26 * var22;
            var7.pushAlpha(var9);
            var7.push();
            var7.translate(var17, 0.0F);
            float var18 = smooth((var2 - 0.5F) / 0.5F);
            this.header(var1, var7, var14, var12, var18, var11);
            float var19 = this.view.target(this.views.selected()).update(MotionStyle.MENU.dt(var1.dt));
            if (var19 < 0.999F) {
               var7.pushAlpha(1.0F - var19);
               var7.push();
               var7.translate(-var19 * 40.0F, 0.0F);
               this.library(var1, var7, var14, var15, var16, var10, var11 && var19 < 0.05F);
               var7.pop();
               var7.popAlpha();
            }

            if (var19 > 0.001F) {
               var7.pushAlpha(var19);
               var7.push();
               var7.translate((1.0F - var19) * 40.0F, 0.0F);
               this.discover.render(var1, var14, 150.0F, var15, var16, this.query, 1.0F, var10, var11 && var19 > 0.95F);
               var7.pop();
               var7.popAlpha();
            }

            var7.pop();
            String var20 = var19 < 0.5F
               ? "Click a config to look inside  ·  Right-click for more  ·  Ctrl+V to import  ·  Esc for the orbit"
               : "Click a config to look inside  ·  Type to search  ·  Esc for the orbit";
            float var21 = Math.abs(var19 - 0.5F) * 2.0F;
            var7.text(
               Fonts.MEDIUM, var20, var12 / 2.0F, var13 - 24.0F, 10.5F, BlendMode.withAlpha(Icons.TEXT_3, var18 * var21), FontStyle.CENTER, FontWeight.MIDDLE
            );
            var7.popAlpha();
         }
      }
   }

   private void header(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6) {
      if (!(var5 <= 0.01F)) {
         float var7 = (1.0F - var5) * 10.0F;
         var2.pushAlpha(var5);
         var2.text(Fonts.BOLD, Category.CONFIGS.displayName, var3, 82.0F + var7, 28.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.TOP, -0.01F);
         var2.text(Fonts.REGULAR, Category.CONFIGS.tagline, var3, 118.0F + var7, 13.0F, Icons.TEXT_2);
         float var8 = 104.0F + var7;
         float var9 = var4 - var3;
         boolean var10 = this.views.selected() == 0;
         float var11 = ConfigLibraryDash.DashBar.width(var2, "plus", "New config", 36.0F);
         float var12 = ConfigLibraryDash.DashBar.width(var2, "import", "Import", 36.0F);
         float var13 = var9;
         if (var10) {
            var13 = var9 - var11;
            this.newButton.render(var1, var13, var8, 36.0F, "plus", "New config", ConfigLibraryDash.DashTone.PRIMARY, var6);
            var13 -= 8.0F + var12;
            this.importButton.render(var1, var13, var8, 36.0F, "import", "Import", ConfigLibraryDash.DashTone.QUIET, var6);
            var13 -= 14.0F;
         } else {
            this.newButton.shown = false;
            this.importButton.shown = false;
         }

         boolean var14 = this.search.focused() || !this.query.isEmpty() || !var10;
         float var15 = this.searchShown.target(var14 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
         if (var15 > 0.01F) {
            float var16 = 230.0F * (0.6F + 0.4F * var15);
            var2.pushAlpha(var15);
            this.search.render(var1, var13 - var16, var8, var16, var6 && var15 > 0.5F);
            var2.popAlpha();
            var13 -= (var16 + 12.0F) * var15;
         }

         float var20 = this.tabsShown.target(this.discoverable() ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
         this.tabsX = -1.0F;
         if (var20 > 0.01F) {
            float var17 = this.views.width(var2);
            var2.pushAlpha(var20);
            this.views.render(var1, var13 - var17, var8, var6 && var20 > 0.5F);
            var2.popAlpha();
            this.tabsX = var13 - var17;
         }

         var2.popAlpha();
      }
   }

   private void library(DrawContext var1, Canvas var2, float var3, float var4, float var5, float var6, boolean var7) {
      float var8 = Easing.EMPHASIZED.apply(clamp01(var6 / 0.55F));
      var2.push();
      var2.translate(0.0F, (1.0F - var8) * 14.0F);
      var2.pushAlpha(var8);
      this.bar.render(var1, var3, 150.0F, var4, var8, var7);
      var2.popAlpha();
      var2.pop();
      float var9 = 238.0F;
      float var10 = var5 - 68.0F - 20.0F;
      ArrayList var11 = new ArrayList();

      for (ConfigLibraryStats var13 : ConfigLibraryTransform.mine()) {
         if (ConfigLibraryTransform.matches(var13, this.query)) {
            var11.add(var13);
         }
      }

      this.library.render(var1, var11, var3, var9, var4, var10, 0.0F, 1.0F, var7);
      this.emptyNew.shown = false;
      this.emptyImport.shown = false;
      if (ConfigLibraryTransform.mine().isEmpty()) {
         this.empty(var1, var2, var3 + var4 / 2.0F, var9 + Math.min(var10 * 0.36F, 170.0F), var8, var7);
      } else if (var11.isEmpty()) {
         float var14 = var9 + 80.0F;
         var2.text(Fonts.SEMIBOLD, "No configs match", var3 + var4 / 2.0F, var14, 14.0F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
         var2.text(Fonts.REGULAR, "Fewer words find more", var3 + var4 / 2.0F, var14 + 20.0F, 11.5F, Icons.TEXT_3, FontStyle.CENTER, FontWeight.MIDDLE);
      }
   }

   private void empty(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6) {
      var2.pushAlpha(var5);
      var2.glowCircle(var3, var4, 26.0F, 40.0F, BlendMode.withAlpha(Icons.VIOLET, 0.16F));
      var2.circle(var3, var4, 30.0F, 268435455);
      var2.ring(var3, var4, 30.0F, 1.0F, 536870911);
      var2.icon("layers", var3, var4, 24.0F, Icons.TEXT_2);
      var2.text(Fonts.SEMIBOLD, "No configs yet", var3, var4 + 52.0F, 16.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
      var2.text(
         Fonts.REGULAR,
         "A config keeps your whole setup, so you can switch between setups in a click.",
         var3,
         var4 + 75.0F,
         12.0F,
         Icons.TEXT_2,
         FontStyle.CENTER,
         FontWeight.MIDDLE
      );
      float var7 = ConfigLibraryDash.DashBar.width(var2, "plus", "New config", 34.0F);
      float var8 = ConfigLibraryDash.DashBar.width(var2, "import", "Import", 34.0F);
      float var9 = var3 - (var7 + 8.0F + var8) / 2.0F;
      this.emptyNew.render(var1, var9, var4 + 114.0F, 34.0F, "plus", "New config", ConfigLibraryDash.DashTone.PRIMARY, var6);
      this.emptyImport.render(var1, var9 + var7 + 8.0F, var4 + 114.0F, 34.0F, "import", "Import", ConfigLibraryDash.DashTone.QUIET, var6);
      var2.popAlpha();
   }

   public void renderOverlay(DrawContext var1, float var2, boolean var3) {
      Canvas var4 = var1.canvas;
      if (this.sheet != null || this.menu != null || this.dialog != null || this.effects.busy() || this.shown && !(this.alpha <= 0.005F)) {
         boolean var5 = var1.masked();
         var1.mask(var3);
         var4.pushAlpha(var2);
         if (this.sheet != null) {
            this.sheet.render(var1);
            if (this.sheet.finished()) {
               this.library.hide((ConfigLibraryCover)null);
               this.discover.grid.hide((ConfigLibraryCover)null);
               this.sheet = null;
            }
         }

         if (this.dialog != null) {
            this.dialog.render(var1);
            if (this.dialog.finished()) {
               this.dialog = null;
            }
         }

         if (this.menu != null) {
            this.menu.render(var1);
            if (this.menu.finished()) {
               this.menu = null;
            }
         }

         this.effects.render(var4, var1.time, var1.dt);
         var4.pushAlpha(this.alpha);
         this.toasts.render(var1, var4.width() / 2.0F, var4.height() - 44.0F, !var3 && this.shown);
         var4.popAlpha();
         var4.popAlpha();
         var1.mask(var5);
      }
   }

   private ConfigLibraryFade grid() {
      return this.views.selected() == 0 ? this.library : this.discover.grid;
   }

   private void switchView(int var1) {
      this.views.select(var1);
      this.finishRename(false);
      if (var1 == 1) {
         this.discover.enter(this.time);
      } else {
         this.library.enter(this.time);
      }
   }

   private static float[] rect(ConfigLibraryDash.DashBar var0) {
      return new float[]{var0.x, var0.y, var0.w, var0.h};
   }

   private void openNew(float[] var1, boolean var2) {
      if (this.dialog == null) {
         this.finishRename(false);
         this.search.blur();
         this.dialog = new ConfigLibraryWidget(var1, var2, this::create);
         SoundRequest.play(SoundEvent.EXPAND);
      }
   }

   private void create(String var1, boolean var2) {
      ConfigLibraryTransform.TransformStep var3 = ConfigLibraryTransform.create(var1, var2);
      ConfigLibraryStats var4 = var3.entry();
      if (this.views.selected() != 0) {
         this.switchView(0);
      }

      this.library.scrollTo(0.0F);
      ConfigLibraryCover var5 = this.library.card(var4);
      var5.bornAt = this.time + 0.2F;
      this.bar.saved(this.time);
      String var6 = var2 ? "“" + var4.name + "” is in use: set up your modules, then press Save" : "Saved your setup as “" + var4.name + "”";
      this.toasts.show(var2 ? "sparkles" : "save", var6, "Undo", () -> {
         ConfigLibraryTransform.uncreate(var3);
         SoundRequest.play(SoundEvent.TOGGLE_OFF);
      }, false, this.time);
      SoundRequest.play(SoundEvent.SELECT);
   }

   private void save(float var1) {
      ConfigLibraryStats var2 = ConfigLibraryTransform.active();
      if (var2 == null) {
         this.openNew(this.bar.saveButton(), false);
      } else if (ConfigLibraryTransform.changedSinceSaved()) {
         ConfigLibraryTransform.update(var2);
         ConfigLibraryCover var3 = this.library.existing(var2);
         if (var3 != null) {
            var3.loadedAt = var1;
         }

         this.bar.saved(var1);
         this.toasts.show("save", "Saved your changes to “" + var2.name + "”", var1);
         SoundRequest.play(SoundEvent.SELECT);
      }
   }

   private void revert(float var1) {
      ConfigLibraryStats var2 = ConfigLibraryTransform.active();
      if (var2 != null && ConfigLibraryTransform.changedSinceSaved()) {
         ConfigLibraryTransform.TransformUndo var3 = ConfigLibraryTransform.revert();
         ConfigLibraryCover var4 = this.library.existing(var2);
         if (var4 != null) {
            var4.loadedAt = var1;
         }

         this.toasts.show("rotate-ccw", "“" + var2.name + "” is back as you saved it", "Undo", () -> {
            if (var3 != null) {
               ConfigLibraryTransform.undo(var3);
            }

            SoundRequest.play(SoundEvent.TOGGLE_OFF);
         }, false, var1);
         SoundRequest.play(SoundEvent.TOGGLE_ON);
      }
   }

   private void load(ConfigLibraryStats var1, float var2) {
      boolean var3 = ConfigLibraryTransform.isActive(var1);
      if (var3) {
         this.revert(var2);
      } else {
         ConfigLibraryTransform.TransformUndo var4 = ConfigLibraryTransform.apply(var1);
         ConfigLibraryCover var5 = this.library.existing(var1);
         if (var5 != null) {
            var5.loadedAt = var2;
         }

         this.toasts.show("check", "“" + var1.name + "” is now in use", "Undo", () -> {
            ConfigLibraryTransform.undo(var4);
            SoundRequest.play(SoundEvent.TOGGLE_OFF);
         }, false, var2);
         SoundRequest.play(SoundEvent.TOGGLE_ON);
      }
   }

   private ConfigLibraryCheckoutPage openImport(float[] var1) {
      if (this.dialog instanceof ConfigLibraryCheckoutPage var3 && !var3.closing()) {
         return var3;
      } else {
         this.finishRename(false);
         this.search.blur();
         ConfigLibraryCheckoutPage var4 = new ConfigLibraryCheckoutPage(var1, var1x -> this.imported(var1x));
         this.dialog = var4;
         SoundRequest.play(SoundEvent.EXPAND);
         return var4;
      }
   }

   private void imported(ConfigLibraryCheckoutPage.CheckoutEntry var1) {
      List var2;
      if (var1.file() != null) {
         var2 = ConfigLibraryTransform.importFiles(List.of(var1.file()));
      } else {
         var2 = List.of(ConfigLibraryTransform.add(var1.entry()));
      }

      if (var2.isEmpty()) {
         this.toasts.warn("That config could not be added", this.time);
         SoundRequest.play(SoundEvent.DENY);
      } else {
         float[] var3 = this.dialog != null ? this.dialog.point(0.5F, 0.75F) : new float[]{RenderPipelines.get().mouseX(), RenderPipelines.get().mouseY()};
         this.arrive(var2, var3[0], var3[1], this.time);
         this.offerLoad((ConfigLibraryStats)var2.getFirst(), "Added “" + ((ConfigLibraryStats)var2.getFirst()).name + "” to your library");
      }
   }

   private void offerLoad(ConfigLibraryStats var1, String var2) {
      this.toasts.show("download", var2, "Load", () -> this.load(var1, this.time), false, this.time);
   }

   private void quickPaste(float var1, float var2, float var3) {
      String var4 = ConfigLibraryTransform.clipboard();
      ConfigLibraryStats var5 = ConfigLibraryError.code(var4) == null ? ConfigLibraryTransform.read(var4) : null;
      if (var5 != null) {
         ConfigLibraryTransform.add(var5);
         this.arrive(List.of(var5), var1, var2, var3);
         this.offerLoad(var5, "Imported “" + var5.name + "” from your clipboard");
      } else {
         this.openImport(new float[]{var1 - 20.0F, var2 - 20.0F, 40.0F, 40.0F}).paste();
      }
   }

   private void arrive(List<ConfigLibraryStats> var1, float var2, float var3, float var4) {
      if (this.views.selected() != 0) {
         this.switchView(0);
      }

      this.library.scrollTo(0.0F);

      for (int var5 = 0; var5 < var1.size(); var5++) {
         ConfigLibraryStats var6 = (ConfigLibraryStats)var1.get(var5);
         ConfigLibraryCover var7 = this.library.card(var6);
         var7.bornAt = Float.MAX_VALUE;
         float[] var8 = this.library.cell(var5);
         this.effects.orb(var2, var3, var8[0], var8[1], var6.accent(), var4, () -> {
            var7.bornAt = this.time;
            SoundRequest.play(SoundEvent.SELECT);
         });
      }
   }

   private void openExport(ConfigLibraryStats var1, float[] var2) {
      if (this.dialog == null && var1.mine()) {
         this.finishRename(false);
         this.dialog = new ConfigLibrarySupport(var2, var1, new ConfigLibrarySupport.Listener() {
            @Override
            public void copied(ConfigLibraryStats var1) {
               ConfigLibraryCover var2x = ConfigLibraryPanel.this.library.existing(var1);
               if (var2x != null) {
                  var2x.loadedAt = ConfigLibraryPanel.this.time;
               }
            }

            @Override
            public void saveFile(ConfigLibraryStats var1) {
               ConfigLibraryPanel.this.export(var1, ConfigLibraryPanel.this.time);
            }

            @Override
            public void publish(ConfigLibraryStats var1, float var2x, float var3) {
               ConfigLibraryPanel.this.toggleShare(var1, var2x, var3, ConfigLibraryPanel.this.time);
            }
         });
         SoundRequest.play(SoundEvent.EXPAND);
      }
   }

   private void copy(ConfigLibraryStats var1, float var2) {
      ConfigLibraryTransform.copy(var1);
      this.toasts.show("copy", "Copied the config code of “" + var1.name + "”: anyone can import it", var2);
      SoundRequest.play(SoundEvent.KEY_SET);
   }

   private void duplicate(ConfigLibraryStats var1, float var2) {
      ConfigLibraryStats var3 = ConfigLibraryTransform.duplicate(var1);
      this.library.card(var3).bornAt = var2 + 0.15F;
      this.toasts.show("copy-plus", "Duplicated as “" + var3.name + "”", var2);
      SoundRequest.play(SoundEvent.SELECT);
   }

   private void delete(ConfigLibraryStats var1, float var2) {
      ConfigLibraryCover var3 = this.library.existing(var1);
      if (var3 != null && var3.shown) {
         this.effects.dust(var3.x, var3.y, var3.w, 194.0F, var1.accent(), var2, 90);
      }

      if (var3 == this.renaming) {
         this.finishRename(true);
      }

      ConfigLibraryTransform.SlotAssignment var4 = ConfigLibraryTransform.delete(var1);
      this.toasts.show("trash-2", "Deleted “" + var1.name + "”", "Undo", () -> this.restore(var4), false, var2);
      SoundRequest.play(SoundEvent.COLLAPSE);
   }

   private void restore(ConfigLibraryTransform.SlotAssignment var1) {
      ConfigLibraryTransform.restore(var1);
      ConfigLibraryCover var2 = this.library.card(var1.entry());
      var2.placed = false;
      var2.bornAt = this.time;
      SoundRequest.play(SoundEvent.SELECT);
   }

   private void toggleShare(ConfigLibraryStats var1, float var2, float var3, float var4) {
      if (ConfigLibraryTransform.isShared(var1)) {
         ConfigLibraryTransform.unshare(var1);
         this.effects.ring(var2, var3, var1.accent(), var4, 8.0F, 30.0F);
         this.toasts.show("cloud-off", "“" + var1.name + "” is no longer in Discover", var4);
         SoundRequest.play(SoundEvent.COLLAPSE);
      } else if (!ConfigLibraryTransform.share(var1)) {
         this.toasts.warn("All 5 places in Discover are in use: take one out first", var4);
         SoundRequest.play(SoundEvent.DENY);
      } else {
         this.effects.ring(var2, var3, var1.accent(), var4, 8.0F, 46.0F);
         this.toasts
            .show(
               "globe",
               ConfigLibraryUpdater.unreachable()
                  ? "“" + var1.name + "” goes into Discover once the server can be reached"
                  : "Published “" + var1.name + "”: anyone can find it in Discover",
               var4
            );
         SoundRequest.play(SoundEvent.EXPAND);
      }
   }

   private void get(ConfigLibraryStats var1, float var2, float var3, float var4) {
      if (!var1.mine() && !var1.added && !var1.own && var1.code != null) {
         var1.added = true;
         float[] var5 = this.views.center(0);
         this.effects.orb(var2, var3, var5[0], var5[1], var1.accent(), var4, () -> {
            this.views.bump(0);
            SoundRequest.play(SoundEvent.SELECT);
         });
         ConfigLibraryUpdater.get(
            var1.code,
            ConfigLibraryStats.ConfigOrigin.ADDED,
            (var2x, var3x) -> {
               if (var2x == null) {
                  var1.added = false;
                  this.toasts
                     .warn("offline".equals(var3x) ? "Can't reach the SourceByDevFFnightmare DeObfDeCompByGuideX server: try again in a moment" : "Could not add \"" + var1.name + "\"", this.time);
                  SoundRequest.play(SoundEvent.DENY);
               } else {
                  this.library.card(var2x).bornAt = this.time + 0.3F;
                  this.offerLoad(var2x, "Added \"" + var1.name + "\" to your library");
               }
            }
         );
      }
   }

   private void like(ConfigLibraryStats var1, float var2, float var3, float var4) {
      if (!var1.own) {
         ConfigLibraryUpdater.like(var1, var1x -> {
            if ("link_required".equals(var1x)) {
               this.toasts.warn("Link your SourceByDevFFnightmare DeObfDeCompByGuideX account to like configs", this.time);
            }
         });
         if (var1.liked) {
            this.effects.burst(var2, var3, Icons.PINK, var4, 10, 80.0F);
            SoundRequest.play(SoundEvent.TOGGLE_ON);
         } else {
            SoundRequest.play(SoundEvent.TOGGLE_OFF);
         }
      }
   }

   private void choosePhoto(ConfigLibraryStats var1) {
      SoundRequest.play(SoundEvent.CLICK);
      ConfigLibraryFile.choose(var2 -> {
         if (var2 != null) {
            this.setPhoto(var1, var2);
         }
      });
   }

   private void setPhoto(ConfigLibraryStats var1, Path var2) {
      if (var1.mine()) {
         ConfigLibraryFile.importPicture(var1, var2, (var2x, var3) -> {
            if (var2x == null) {
               this.toasts.warn(var3 == null ? "That picture could not be read. Try another PNG or JPG." : var3, this.time);
               SoundRequest.play(SoundEvent.DENY);
            } else {
               ConfigLibraryTransform.photo(var1, var2x);
               this.toasts.show("image", "“" + var1.name + "” has a new photo", this.time);
               SoundRequest.play(SoundEvent.SELECT);
            }
         });
      }
   }

   private void removePhoto(ConfigLibraryStats var1, float var2) {
      String var3 = var1.photo;
      if (var3 != null) {
         ConfigLibraryTransform.photo(var1, (String)null);
         this.toasts.show("image-off", "Took the photo off “" + var1.name + "”", "Undo", () -> ConfigLibraryTransform.photo(var1, var3), false, var2);
         SoundRequest.play(SoundEvent.COLLAPSE);
      }
   }

   private void copyCode(ConfigLibraryStats var1, float var2) {
      if (var1.code != null) {
         ConfigLibraryTransform.toClipboard(var1.code);
         this.toasts.show("copy", "Copied " + var1.code + ": anyone can paste it into SourceByDevFFnightmare DeObfDeCompByGuideX", var2);
         SoundRequest.play(SoundEvent.KEY_SET);
      }
   }

   private void report(ConfigLibraryStats var1, float var2) {
      ConfigLibraryUpdater.report(var1, (var2x, var3) -> {
         if (var2x) {
            this.toasts.show("flag", "Reported “" + var1.name + "”: thank you, we will look at it", this.time);
         } else if ("link_required".equals(var3)) {
            this.toasts.warn("Link your SourceByDevFFnightmare DeObfDeCompByGuideX account to report configs", this.time);
         } else {
            this.toasts.warn("Can't reach the SourceByDevFFnightmare DeObfDeCompByGuideX server: try again in a moment", this.time);
         }
      });
      SoundRequest.play(SoundEvent.CLICK);
   }

   private void export(ConfigLibraryStats var1, float var2) {
      Path var3 = ConfigLibraryTransform.export(var1);
      if (var3 == null) {
         this.toasts.warn("Could not export “" + var1.name + "”", var2);
         SoundRequest.play(SoundEvent.DENY);
      } else {
         this.toasts.show("file-down", "Saved “" + var1.name + "” as a file in your configs folder", "Show", this::openFolder, false, var2);
         SoundRequest.play(SoundEvent.KEY_SET);
      }
   }

   private void openFolder() {
      try {
         Files.createDirectories(ConfigLibraryTransform.folder());
         class_156.method_668().method_60932(ConfigLibraryTransform.folder());
         SoundRequest.play(SoundEvent.CLICK);
      } catch (Exception var2) {
         ClientServices.LOGGER.warn("Could not open the configs folder", var2);
         SoundRequest.play(SoundEvent.DENY);
      }
   }

   private void openSheet(ConfigLibraryStats var1,  ConfigLibraryCover var2, float[] var3, float var4) {
      if (this.sheet == null) {
         this.finishRename(false);
         this.sheet = new ConfigLibraryDetail(var1, var2, var3, var4);
         this.grid().hide(var2);
         SoundRequest.play(SoundEvent.EXPAND);
      }
   }

   private void openSheet(ConfigLibraryCover var1) {
      this.openSheet(var1.entry, var1, var1.cover(), 18.0F);
   }

   private void openMenu(ConfigLibraryCover var1, float var2, float var3, float var4) {
      ConfigLibraryStats var5 = var1.entry;
      ArrayList var6 = new ArrayList();
      if (var5.mine()) {
         boolean var7 = ConfigLibraryTransform.isActive(var5);
         if (var7 && ConfigLibraryTransform.changedSinceSaved()) {
            var6.add(new ConfigLibraryContextMenu.ContextAction("save", "Save changes", "Ctrl+S", false, () -> this.save(this.time)));
            var6.add(new ConfigLibraryContextMenu.ContextAction("rotate-ccw", "Undo changes", () -> this.revert(this.time)));
         } else if (!var7) {
            var6.add(new ConfigLibraryContextMenu.ContextAction("check", "Load", () -> this.load(var5, this.time)));
         }

         var6.add(new ConfigLibraryContextMenu.ContextAction("share", "Export", () -> this.openExport(var5, new float[]{var2 - 20.0F, var3 - 20.0F, 40.0F, 40.0F})));
         var6.add(new ConfigLibraryContextMenu.ContextAction("pen-line", "Rename", () -> this.startRename(var1)));
         var6.add(new ConfigLibraryContextMenu.ContextAction("copy-plus", "Duplicate", () -> this.duplicate(var5, this.time)));
         var6.add(
            new ConfigLibraryContextMenu.ContextAction(
               var5.photo == null ? "image-plus" : "image", var5.photo == null ? "Add photo" : "Change photo", () -> this.choosePhoto(var5)
            )
         );
         if (var5.photo != null) {
            var6.add(new ConfigLibraryContextMenu.ContextAction("image-off", "Remove photo", () -> this.removePhoto(var5, this.time)));
         }

         var6.add(new ConfigLibraryContextMenu.ContextAction("trash-2", "Delete", (String)null, true, () -> this.delete(var5, this.time)));
      } else {
         if (var5.own) {
            var6.add(
               new ConfigLibraryContextMenu.ContextAction(
                  "trash-2", "Delete from Discover", (String)null, true, () -> ConfigLibraryUpdater.deleteOwn(var5, (var1xx, var2x) -> {
                     if (var1xx) {
                        this.toasts.show("trash-2", "Removed from Discover", (String)null, (Runnable)null, false, this.time);
                     } else {
                        this.toasts.warn("Could not delete: " + var2x, this.time);
                     }
                  })
               )
            );
         }

         if (!var5.added && !var5.own) {
            var6.add(new ConfigLibraryContextMenu.ContextAction("download", "Get", () -> this.get(var5, var2, var3, this.time)));
         }

         if (!var5.own) {
            var6.add(new ConfigLibraryContextMenu.ContextAction("heart", var5.liked ? "Unlike" : "Like", () -> this.like(var5, var2, var3, this.time)));
         }

         var6.add(new ConfigLibraryContextMenu.ContextAction("eye", "Look inside", () -> this.openSheet(var1)));
         if (!var5.own) {
            var6.add(new ConfigLibraryContextMenu.ContextAction("flag", "Report", (String)null, true, () -> this.report(var5, this.time)));
         }
      }

      this.menu = new ConfigLibraryContextMenu(var2, var3, var6, var4);
   }

   private void startRename(ConfigLibraryCover var1) {
      this.finishRename(false);
      if (this.views.selected() != 0) {
         this.switchView(0);
      }

      this.search.blur();
      var1.startRename();
      this.renaming = var1;
      this.library.reveal(var1);
      SoundRequest.play(SoundEvent.CLICK);
   }

   private void finishRename(boolean var1) {
      if (this.renaming != null) {
         this.renaming.endRename(var1);
         this.renaming = null;
      }
   }

   private void sheetAction(ConfigLibraryDetail var1, ConfigLibraryDetail.DetailTab var2, float var3) {
      ConfigLibraryStats var4 = var1.entry;
      switch (var2) {
         case CLOSE:
            var1.close();
            SoundRequest.play(SoundEvent.COLLAPSE);
            break;
         case LOAD:
            this.load(var4, var3);
            break;
         case SAVE:
            this.save(var3);
            break;
         case REVERT:
            this.revert(var3);
            break;
         case GET:
            float[] var5 = var1.primaryButton();
            this.get(var4, var5[0], var5[1], var3);
            break;
         case EXPORT:
            float[] var6 = var1.exportButton();
            var1.close();
            this.openExport(var4, var6);
            break;
         case LIKE:
            float[] var7 = var1.secondButton();
            this.like(var4, var7[0], var7[1], var3);
            break;
         case RENAME:
            var1.startRename();
            SoundRequest.play(SoundEvent.CLICK);
            break;
         case DUPLICATE:
            var1.close();
            this.duplicate(var4, var3 + 0.25F);
            break;
         case PHOTO:
            this.choosePhoto(var4);
            break;
         case UNPHOTO:
            this.removePhoto(var4, var3);
            break;
         case COPY_CODE:
            this.copyCode(var4, var3);
            break;
         case REPORT:
            this.report(var4, var3);
            var1.reported();
            break;
         case DELETE:
            float[] var8 = var1.rect();
            this.effects.dust(var8[0], var8[1], var8[2], var8[3], var4.accent(), var3, 160);
            this.sheet = null;
            this.library.hide((ConfigLibraryCover)null);
            this.discover.grid.hide((ConfigLibraryCover)null);
            this.delete(var4, var3);
      }
   }

   public void mouseDown(float var1, float var2, int var3, float var4) {
      this.time = var4;
      if (this.menu != null) {
         if (!this.menu.closing()) {
            this.menu.mouseDown(var1, var2);
         }
      } else if (this.dialog != null) {
         this.dialog.mouseDown(var1, var2);
      } else if (this.sheet != null) {
         if (!this.sheet.closing()) {
            this.sheetAction(this.sheet, this.sheet.mouseDown(var1, var2), var4);
         }
      } else if (!this.toasts.mouseDown(var1, var2, var4)) {
         boolean var5 = this.search.mouseDown(var1, var2);
         if (this.renaming != null) {
            Slider var6 = this.renaming.field();
            if (var6 != null && var6.mouseDown(var1, var2)) {
               return;
            }

            this.finishRename(false);
         }

         if (!var5 && this.interactive) {
            if (this.tabsX >= 0.0F) {
               int var7 = this.views.mouseDown(var1, var2);
               if (var7 >= 0) {
                  if (var7 != Math.round(this.view.target())) {
                     this.switchView(var7);
                  }

                  return;
               }
            }

            if (this.newButton.hit(var1, var2)) {
               this.newButton.press();
               this.openNew(rect(this.newButton), true);
            } else if (this.importButton.hit(var1, var2)) {
               this.importButton.press();
               this.openImport(rect(this.importButton));
            } else if (this.views.selected() == 0) {
               this.libraryDown(var1, var2, var3, var4);
            } else {
               this.discoverDown(var1, var2, var3, var4);
            }
         }
      }
   }

   private void libraryDown(float var1, float var2, int var3, float var4) {
      switch (this.bar.mouseDown(var1, var2)) {
         case SAVE:
            this.save(var4);
            return;
         case REVERT:
            this.revert(var4);
            return;
         case SAVE_AS_NEW:
            this.openNew(this.bar.saveButton(), false);
            return;
         case OPEN:
            ConfigLibraryStats var5 = ConfigLibraryTransform.active();
            if (var5 != null) {
               this.openSheet(var5, (ConfigLibraryCover)null, this.bar.thumbRect(), 13.0F);
            }

            return;
         default:
            if (this.emptyNew.hit(var1, var2)) {
               this.emptyNew.press();
               this.openNew(rect(this.emptyNew), true);
            } else if (this.emptyImport.hit(var1, var2)) {
               this.emptyImport.press();
               this.openImport(rect(this.emptyImport));
            } else {
               ConfigLibraryCover var6 = this.library.cardAt(var1, var2);
               if (var6 != null) {
                  this.cardDown(var6, this.library, var1, var2, var3, var4);
               }
            }
      }
   }

   private void discoverDown(float var1, float var2, int var3, float var4) {
      ConfigLibraryBanner.BannerItem var5 = this.discover.mouseDown(var1, var2, var4);
      switch (var5.kind()) {
         case GET:
            float[] var6 = this.discover.getButton();
            this.get(var5.entry(), var6[0], var6[1], var4);
            return;
         case PREVIEW:
            float[] var7 = this.discover.bannerSky();
            this.openSheet(var5.entry(), (ConfigLibraryCover)null, var7, 22.0F);
            return;
         case LIKE:
            this.like(var5.entry(), var1, var2, var4);
            return;
         case CONSUMED:
            return;
         case RETRY:
            ConfigLibraryUpdater.refresh(true);
            SoundRequest.play(SoundEvent.CLICK);
            return;
         case LIBRARY:
            this.views.select(0);
            this.switchView(0);
            SoundRequest.play(SoundEvent.TAB);
            return;
         default:
            ConfigLibraryCover var8 = this.discover.grid.cardAt(var1, var2);
            if (var8 != null) {
               this.cardDown(var8, this.discover.grid, var1, var2, var3, var4);
            }
      }
   }

   private void cardDown(ConfigLibraryCover var1, ConfigLibraryFade var2, float var3, float var4, int var5, float var6) {
      if (var5 == 1) {
         this.openMenu(var1, var3, var4, var6);
      } else {
         switch (var1.hit(var3, var4)) {
            case MORE:
               var1.more.press();
               this.openMenu(var1, var1.more.x + var1.more.d, var1.more.y + var1.more.d + 6.0F, var6);
               break;
            case EXPORT:
               var1.export.press();
               this.openExport(var1.entry, new float[]{var1.export.x, var1.export.y, var1.export.d, var1.export.d});
               break;
            case LIKE:
               var1.like.press();
               this.like(var1.entry, var1.like.x + var1.like.d / 2.0F, var1.like.y + var1.like.d / 2.0F, var6);
               break;
            case LOAD:
               var1.pressMain();
               this.load(var1.entry, var6);
               break;
            case GET:
               var1.pressMain();
               this.get(var1.entry, var3, var4, var6);
               break;
            case OPEN:
               var2.press(var1);
         }
      }
   }

   public void mouseUp(float var1, float var2, float var3) {
      this.time = var3;
      this.search.mouseUp();
      if (this.renaming != null && this.renaming.field() != null) {
         this.renaming.field().mouseUp();
      }

      if (this.sheet != null) {
         this.sheet.mouseUp();
      }

      if (this.dialog != null) {
         this.dialog.mouseUp();
      }

      ConfigLibraryCover var4 = this.library.release(var1, var2);
      ConfigLibraryCover var5 = this.discover.grid.release(var1, var2);
      if (var5 != null) {
         var4 = var5;
      }

      if (var4 != null && this.sheet == null && this.menu == null && this.dialog == null && this.interactive) {
         this.openSheet(var4);
      }
   }

   public void mouseDrag(float var1, float var2) {
      this.search.mouseDrag(var1, var2);
      if (this.renaming != null && this.renaming.field() != null) {
         this.renaming.field().mouseDrag(var1, var2);
      }

      if (this.sheet != null) {
         this.sheet.mouseDrag(var1, var2);
      }

      if (this.dialog != null) {
         this.dialog.mouseDrag(var1, var2);
      }
   }

   public boolean scroll(float var1, float var2, double var3) {
      if (this.menu != null || this.dialog != null) {
         return true;
      } else if (this.sheet != null) {
         this.sheet.scroll(var3);
         return true;
      } else {
         this.grid().scroll(var3);
         return true;
      }
   }


   private Slider focusedField() {
      if (this.sheet != null && this.sheet.field() != null && this.sheet.field().focused()) {
         return this.sheet.field();
      } else if (this.renaming != null && this.renaming.field() != null && this.renaming.field().focused()) {
         return this.renaming.field();
      } else {
         return this.search.focused() ? this.search : null;
      }
   }

   public boolean keyPressed(int var1, boolean var2, boolean var3, float var4) {
      this.time = var4;
      if (this.dialog != null) {
         this.dialog.keyPressed(var1, var2);
         return true;
      } else {
         Slider var5 = this.focusedField();
         if (var5 != null) {
            boolean var10 = var5 != this.search;
            if (var1 == 256 && var10) {
               if (this.sheet != null && this.sheet.field() == var5) {
                  this.sheet.endRename(true);
               } else {
                  this.finishRename(true);
               }

               SoundRequest.play(SoundEvent.CLICK);
               return true;
            } else {
               var5.keyPressed(var1);
               if (var10 && !var5.focused()) {
                  if (this.sheet != null && this.sheet.field() == var5) {
                     this.sheet.endRename(false);
                  } else {
                     this.finishRename(false);
                  }
               }

               return true;
            }
         } else if (this.menu != null) {
            if (var1 == 256) {
               this.menu.close();
               SoundRequest.play(SoundEvent.COLLAPSE);
            }

            return true;
         } else if (this.sheet != null) {
            if (var1 == 256) {
               this.sheet.close();
               SoundRequest.play(SoundEvent.COLLAPSE);
            }

            return true;
         } else if (!this.interactive) {
            return false;
         } else {
            float var6 = RenderPipelines.get().mouseX();
            float var7 = RenderPipelines.get().mouseY();
            if (var2 && var1 == 86) {
               this.quickPaste(var6, var7, var4);
               return true;
            } else if (var2 && var1 == 67) {
               ConfigLibraryCover var8 = this.grid().cardAt(var6, var7);
               ConfigLibraryStats var9 = var8 != null ? var8.entry : ConfigLibraryTransform.active();
               if (var9 != null) {
                  this.copy(var9, var4);
               }

               return true;
            } else if (var2 && var1 == 83) {
               this.save(var4);
               return true;
            } else if (var2 && var1 == 78) {
               this.openNew(new float[]{var6 - 20.0F, var7 - 20.0F, 40.0F, 40.0F}, true);
               return true;
            } else if (var2 && var1 == 70) {
               this.search.focus();
               return true;
            } else if (var1 == 256 && !this.query.isEmpty()) {
               this.query = "";
               this.search.blur();
               SoundRequest.play(SoundEvent.CLICK);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   public boolean charTyped(int var1, float var2) {
      this.time = var2;
      if (this.dialog != null) {
         this.dialog.typed(var1);
         return true;
      } else {
         Slider var3 = this.focusedField();
         if (var3 != null) {
            var3.charTyped(var1);
            return true;
         } else if (this.modal()) {
            return true;
         } else if (this.interactive && var1 != 47 && !Character.isWhitespace(var1) && !Character.isISOControl(var1)) {
            this.search.focus();
            this.search.charTyped(var1);
            return true;
         } else {
            return false;
         }
      }
   }

   public void drop(List<Path> var1, float var2, float var3, float var4) {
      this.time = var4;
      ArrayList var5 = new ArrayList();
      ArrayList var6 = new ArrayList();

      for (Path var8 : var1) {
         (ConfigLibraryFile.picture(var8) ? var5 : var6).add(var8);
      }

      if (!var5.isEmpty()) {
         ConfigLibraryStats var9 = this.photoTarget(var2, var3);
         if (var9 == null) {
            this.toasts.warn("Drop a picture on one of your configs to give it a photo", var4);
            SoundRequest.play(SoundEvent.DENY);
         } else {
            this.effects.ring(var2, var3, var9.accent(), var4, 6.0F, 120.0F);
            this.setPhoto(var9, (Path)var5.getFirst());
         }

         if (var6.isEmpty()) {
            return;
         }
      }

      if (this.menu != null) {
         this.menu.close();
      }

      if (this.sheet != null) {
         this.sheet.close();
      }

      if (this.dialog != null) {
         this.dialog.close();
      }

      this.effects.ring(var2, var3, Icons.PURPLE, var4, 6.0F, 150.0F);
      List var10 = ConfigLibraryTransform.importFiles(var6);
      if (var10.isEmpty()) {
         this.toasts.warn(var6.size() == 1 ? "That file is not a SourceByDevFFnightmare DeObfDeCompByGuideX config" : "None of those files is a SourceByDevFFnightmare DeObfDeCompByGuideX config", var4);
         SoundRequest.play(SoundEvent.DENY);
      } else {
         this.arrive(var10, var2, var3, var4);
         if (var10.size() == 1) {
            this.offerLoad((ConfigLibraryStats)var10.getFirst(), "Added “" + ((ConfigLibraryStats)var10.getFirst()).name + "” from a file");
         } else {
            this.toasts.show("file-plus-2", "Added " + var10.size() + " configs from files", var4);
         }
      }
   }


   private ConfigLibraryStats photoTarget(float var1, float var2) {
      if (this.sheet != null && !this.sheet.closing()) {
         return this.sheet.entry.mine() ? this.sheet.entry : null;
      } else {
         if (this.views.selected() == 0) {
            ConfigLibraryCover var3 = this.library.cardAt(var1, var2);
            if (var3 != null) {
               return var3.entry;
            }

            float[] var4 = this.bar.thumbRect();
            if (var1 >= var4[0] - 12.0F && var1 <= var4[0] + 300.0F && var2 >= var4[1] - 12.0F && var2 <= var4[1] + var4[3] + 12.0F) {
               return ConfigLibraryTransform.active();
            }
         }

         return null;
      }
   }

   public void dev(String var1, String var2) {
      ConfigLibraryTransform.dry = true;
      float var3 = this.time;
      String[] var4 = var2.split(";");
      List var5 = this.grid().shown();
      ConfigLibraryCover var6 = null;

      try {
         int var7 = var2.isEmpty() ? -1 : Integer.parseInt(var4[0]);
         var6 = var7 >= 0 && var7 < var5.size() ? (ConfigLibraryCover)var5.get(var7) : null;
      } catch (NumberFormatException var14) {
      }

      switch (var1) {
         case "view":
            this.switchView(var2.startsWith("d") ? 1 : 0);
            break;
         case "new":
            this.openNew(rect(this.newButton), !var2.startsWith("c"));
            if (this.dialog instanceof ConfigLibraryWidget var15 && var4.length > 1) {
               var15.dev(var2.startsWith("c") ? 1 : 0, var4[1]);
            }
            break;
         case "create":
         case "importadd":
            if (this.dialog != null) {
               this.dialog.submit();
            }
            break;
         case "make":
            this.create(var4.length > 1 ? var4[1] : "", !var2.startsWith("c"));
            break;
         case "save":
         case "update":
            this.save(var3);
            break;
         case "revert":
            this.revert(var3);
            break;
         case "saveasnew":
            this.openNew(this.bar.saveButton(), false);
            break;
         case "open":
            if (var6 != null) {
               this.openSheet(var6);
            }
            break;
         case "close":
            if (this.sheet != null) {
               this.sheet.close();
            }

            if (this.menu != null) {
               this.menu.close();
            }

            if (this.dialog != null) {
               this.dialog.close();
            }
            break;
         case "menu":
            if (var6 != null) {
               this.openMenu(var6, var6.x + var6.w - 12.0F, var6.y + 46.0F, var3);
            }
            break;
         case "load":
            if (var6 != null) {
               this.load(var6.entry, var3);
            }
            break;
         case "copy":
            if (var6 != null) {
               this.copy(var6.entry, var3);
            }
            break;
         case "export":
            if (var6 != null) {
               this.openExport(var6.entry, new float[]{var6.x + var6.w - 60.0F, var6.y + 12.0F, 28.0F, 28.0F});
            }
            break;
         case "exportcopy":
            if (this.dialog instanceof ConfigLibrarySupport var16) {
               var16.copyCode();
            }
            break;
         case "sharecode":
            if (this.dialog instanceof ConfigLibrarySupport var17) {
               var17.askCode();
            }
            break;
         case "file":
            if (var6 != null) {
               this.export(var6.entry, var3);
            }
            break;
         case "publish":
            if (var6 != null) {
               this.toggleShare(var6.entry, var6.x + var6.w - 60.0F, var6.y + 26.0F, var3);
            }
            break;
         case "import":
            this.openImport(rect(this.importButton));
            break;
         case "importtext":
         case "importcode":
            this.openImport(rect(this.importButton)).dev(var2);
            break;
         case "importpaste":
            this.openImport(rect(this.importButton)).paste();
            break;
         case "importfile":
            this.openImport(rect(this.importButton)).file(Path.of(var2));
            break;
         case "paste":
            if (var2.equals("none")) {
               ConfigLibraryTransform.dryClipboard("");
            } else if (var6 != null) {
               ConfigLibraryTransform.dryClipboard(ConfigLibraryTransform.encode(var6.entry));
            } else {
               ConfigLibraryTransform.dryClipboard(var2);
            }

            this.quickPaste(760.0F, 300.0F, var3);
            break;
         case "delete":
            if (var6 != null) {
               this.delete(var6.entry, var3);
            }
            break;
         case "undo":
            this.toasts.act(var3);
            break;
         case "rename":
            if (var6 != null) {
               this.startRename(var6);
            }
            break;
         case "duplicate":
            if (var6 != null) {
               this.duplicate(var6.entry, var3);
            }
            break;
         case "get":
            if (var6 != null) {
               this.get(var6.entry, var6.x + var6.w - 40.0F, var6.y + 194.0F - 30.0F, var3);
            }
            break;
         case "like":
            if (var6 != null) {
               this.like(var6.entry, var6.x + var6.w - 80.0F, var6.y + 26.0F, var3);
            }
            break;
         case "report":
            if (var6 != null) {
               this.report(var6.entry, var3);
            }
            break;
         case "photo":
            if (var6 != null && var4.length > 1) {
               this.setPhoto(var6.entry, Path.of(var2.substring(var2.indexOf(59) + 1)));
            }
            break;
         case "sheetphoto":
            if (this.sheet != null) {
               this.setPhoto(this.sheet.entry, Path.of(var2));
            }
            break;
         case "choose":
            if (var6 != null) {
               this.choosePhoto(var6.entry);
            }
            break;
         case "unphoto":
            if (var6 != null) {
               this.removePhoto(var6.entry, var3);
            }
            break;
         case "refresh":
            ConfigLibraryUpdater.refresh(true);
            break;
         case "feature":
            this.discover.feature(Integer.parseInt(var2), var3);
            break;
         case "tag":
            this.discover.tag(var2);
            break;
         case "sort":
            this.discover.sort(Integer.parseInt(var2));
            break;
         case "search":
            this.query = var2;
            this.library.scrollTo(0.0F);
            this.discover.grid.scrollTo(0.0F);
            break;
         case "scroll":
            this.grid().scrollTo(Float.parseFloat(var2));
            break;
         case "sheetscroll":
            if (this.sheet != null) {
               this.sheet.scrollTo(Float.parseFloat(var2));
            }
            break;
         case "drop":
            if (var4.length >= 3 && var4[0].matches("[0-9.]+") && var4[1].matches("[0-9.]+")) {
               this.drop(List.of(Path.of(var2.substring(var4[0].length() + var4[1].length() + 2))), Float.parseFloat(var4[0]), Float.parseFloat(var4[1]), var3);
            } else {
               this.drop(List.of(Path.of(var2)), 760.0F, 420.0F, var3);
            }
            break;
         case "empty":
            ConfigLibraryTransform.clear();
            break;
         case "status":
            ConfigLibraryHost.checkStatus(true);
            ConfigLibraryStats var12 = ConfigLibraryTransform.active();
            ClientServices.LOGGER
               .info(
                  "Configs: {} configs, in use {}, {} unsaved changes, server online {} (share codes {})",
                  new Object[]{
                     ConfigLibraryTransform.mine().size(),
                     var12 == null ? "none" : var12.name,
                     ConfigLibraryTransform.unsavedChanges(),
                     ConfigLibraryHost.online(),
                     ConfigLibraryHost.shareCodes()
                  }
               );
            break;
         case "roundtrip":
            throw new IllegalArgumentException("Use the external configuration acceptance suite");
         case "stats":
            RenderPipelines.FogRange var13 = RenderPipelines.get().stats();
            ClientServices.LOGGER
               .info(
                  "Configs page: {} quads, {} batches, {} passes, {} ms on the CPU",
                  new Object[]{var13.quads, var13.batches, var13.passes, String.format(Locale.ROOT, "%.2f", var13.cpuMillis)}
               );
            break;
         default:
            ClientServices.LOGGER.warn("Unknown configs dev action {}", var1);
      }
   }

   private static float clamp01(float var0) {
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   private static float smooth(float var0) {
      var0 = clamp01(var0);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }
}
