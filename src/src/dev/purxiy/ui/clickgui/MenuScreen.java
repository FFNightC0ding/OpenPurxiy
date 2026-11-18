// Sourced By DevFFNightmare
package dev.purxiy.ui.clickgui;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.hud.HudEditorScreen;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.MenuCommands;
import dev.purxiy.loader.fabric.MenuKeyBinding;
import dev.purxiy.modules.Category;
import dev.purxiy.modules.Module;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.persist.FCodec;
import dev.purxiy.profile.ProfileRenderer11;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.texture.TextureLoader;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.KeyLabel;
import dev.purxiy.ui.config.ConfigLibraryRenderer14;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import net.minecraft.class_11876;
import net.minecraft.class_11905;
import net.minecraft.class_11908;
import net.minecraft.class_11909;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;

public final class MenuScreen extends class_437 {
   
   private static TextureLoader mark;
   
   private static TextureLoader wordmark;
   
   private final class_437 parent;
   private final DrawContext frame = new DrawContext();
   private final StudioTab backdrop = new StudioTab();
   private final StudioPanel orbit;
   private final LayoutEditor grid = new LayoutEditor();
   private final ModuleSearch search = new ModuleSearch();
   private final ProfileRenderer11 profile = new ProfileRenderer11();
   private final ConfigLibraryRenderer14 configs = new ConfigLibraryRenderer14();
   
   private ModuleList panel;
   private final Animator menu = new Animator(0.0F, 150.0F, 24.0F);
   private final Animator view = new Animator(0.0F, 140.0F, 23.5F);
   private final Animator searchButtonHover = Animator.smooth(0.0F);
   private final Object searchButtonKey = new Object();
   private Category category;
   private boolean closing;
   private boolean pointerDown;
   private float wheel;
   
   private Object lastHover;
   private float appliedScale = -1.0F;
   private final Animator scale = Animator.smooth(1.0F);
   
   private Module pendingPanel;
   
   private Category pendingOpen;
   private float searchX;
   private float searchY;
   private float searchW;
   private float searchH;
   private boolean pendingStudio;
   private AutoCloseable rendererLease;
   private boolean studioPressed;
   private float devMouseX = Float.NaN;
   private float devMouseY = Float.NaN;
   private int lastButton;

   public MenuScreen( class_437 var1) {
      super(class_2561.method_43470("Purxiy"));
      this.parent = var1;
      if (mark == null) {
         mark = TextureLoader.load("textures/brand/mark.png");
         wordmark = TextureLoader.load("textures/brand/wordmark.png");
      }

      this.category = SettingSection.shown(FCodec.lastCategory) ? FCodec.lastCategory : Category.RENDER;
      this.orbit = new StudioPanel(this.category, mark, wordmark);
      this.menu.target(1.0F);
   }

   public MenuScreen( class_437 var1, Module var2) {
      this(var1);
      var2 = Objects.requireNonNull(SettingSection.byId(var2.id()));
      this.category = var2.category();
      FCodec.lastCategory = this.category;
      this.orbit.snapTo(this.category);
      this.view.snap(1.0F);
      this.grid.show(this.category, RenderPipelines.get().time(), 0, 0.0F);
      this.showProfile(this.category, 0, 0.0F);
      this.pendingPanel = var2;
   }

   public static MenuScreen studio( class_437 var0, Module var1) {
      MenuScreen var2 = new MenuScreen(var0, var1);
      var2.pendingStudio = true;
      return var2;
   }

   @Override
   protected void method_25426() {
      if (this.rendererLease == null) {
         try {
            this.rendererLease = ClientServices.acquire("menu-screen");
            SoundRequest.play(SoundEvent.OPEN);
         } catch (Exception var2) {
            throw new IllegalStateException(var2);
         }
      }
   }

   @Override
   public boolean method_25421() {
      Studio var1 = this.panel != null ? this.panel.studio() : null;
      return ClientServices.interfaceLook().pause() && (var1 == null || !var1.keepsWorldRunning());
   }

   @Override
   public void method_25420(class_332 var1, int var2, int var3, float var4) {
      if (this.field_22787.field_1687 == null) {
         this.method_57728(var1, var4);
      }
   }

   @Override
   public void method_25432() {
      if (this.panel != null) {
         this.panel.closeStudio();
      }

      FCodec.save();
      if (this.rendererLease != null) {
         try {
            this.rendererLease.close();
         } catch (Exception var2) {
         }

         this.rendererLease = null;
      }
   }

   @Override
   public void method_25394(class_332 var1, int var2, int var3, float var4) {
      RenderPipelines var5 = RenderPipelines.get();
      float var6 = var5.delta();
      if (this.appliedScale < 0.0F) {
         this.scale.snap(ClientServices.interfaceLook().scale());
      }

      if (!this.pointerDown) {
         this.scale.target(ClientServices.interfaceLook().scale());
      }

      this.appliedScale = this.scale.update(MotionStyle.MENU.dt(var6));
      var5.setUserScale(this.appliedScale);
      Canvas var7 = var5.canvas();
      float var8 = var5.time();
      float var9 = Float.isNaN(this.devMouseX) ? var5.mouseX() : this.devMouseX;
      float var10 = Float.isNaN(this.devMouseY) ? var5.mouseY() : this.devMouseY;
      DrawContext var11 = this.frame;
      var11.begin(var7, var8, var6, var9, var10, this.pointerDown);
      float var12 = this.menu.update(MotionStyle.MENU.dt(var6));
      float var13 = this.view.update(MotionStyle.MENU.dt(var6));
      if (this.closing && var12 < 0.02F) {
         this.field_22787.method_1507(this.parent);
      } else {
         float var14 = clamp01(var12);
         float var15 = clamp01(var13);
         if (this.pendingOpen != null && this.orbit.settled()) {
            this.openCategory(this.pendingOpen);
         }

         if (this.pendingPanel != null && var15 > 0.9F) {
            ModuleRow var16 = this.grid.card(this.pendingPanel);
            if (var16 != null && var16.w > 0.0F) {
               this.openPanel(var16);
               this.pendingPanel = null;
            } else if (var16 == null) {
               this.pendingPanel = null;
            }
         }

         if (this.pendingStudio && this.panel != null && this.panel.progress() > 0.85F) {
            this.pendingStudio = false;
            this.panel.openStudio();
         }

         float var21 = this.panel != null ? this.panel.docked() : 0.0F;
         this.backdrop.render(var11, var14 * (1.0F - var21));
         var7.captureBackdrop();
         boolean var17 = this.panel != null || this.search.visible();
         boolean var18 = !MenuCommands.allowed();
         var11.mask(var17 || this.closing || this.configs.modal() || var18);
         if (var21 < 0.999F) {
            var7.pushAlpha(1.0F - var21);
            this.orbit.render(var11, var14, var15, this.category);
            this.grid.render(var11, var15, var14, this.view.target() < 0.5F);
            this.profile.render(var11, var15, var14, this.view.target() < 0.5F);
            this.configs.render(var11, var15, var14, this.view.target() < 0.5F);
            this.searchButton(var11, var7, var14);
            this.orbit.renderTooltips(var11, var14, this.category);
            var7.popAlpha();
         }

         this.configs.renderOverlay(var11, var14, this.search.visible() || this.closing || var18);
         if (this.panel != null) {
            Studio var19 = this.panel.studio();
            if (var19 != null || var21 > 0.001F) {
               Studio var20 = this.panel.module.studio();
               if (var20 != null) {
                  var11.mask(this.search.active() || this.closing || var18 || this.panel.contains(var11.mouseX, var11.mouseY));
                  var20.render(var11, var21, var20.dock().right() ? this.panel.left() : this.panel.right());
                  if (var19 != null && var19.consumeCloseRequest()) {
                     this.panel.closeStudio();
                  }
               }
            }

            var11.mask(this.search.active() || this.closing || var18);
            this.panel.render(var11);
            if (this.panel.finished()) {
               this.grid.hide((ModuleRow)null);
               this.panel = null;
            }
         }

         var11.mask(this.closing || var18);
         this.search.render(var11);
         var11.mask(this.closing);
         Object var22 = var11.hoverTarget();
         if (var22 != null && var22 != this.lastHover && !this.orbit.dragging()) {
            SoundRequest.play(SoundEvent.HOVER);
         }

         this.lastHover = var22;
         switch (var11.cursor()) {
            case HAND:
               var1.method_74037(class_11876.field_62455);
               break;
            case TEXT:
               var1.method_74037(class_11876.field_62453);
               break;
            case MOVE:
               var1.method_74037(class_11876.field_62458);
         }
      }
   }

   private void searchButton(DrawContext var1, Canvas var2, float var3) {
      float var4 = var2.width();
      String var5 = "Search";
      float var6 = var2.textWidth(Fonts.MEDIUM, var5, 12.5F);
      this.searchH = 36.0F;
      this.searchW = 38.0F + var6 + 10.0F + 24.0F + 8.0F;
      float var7 = Math.max(32.0F, Math.min(72.0F, var4 * 0.045F));
      this.searchX = var4 - var7 - this.searchW;
      this.searchY = 40.0F - this.searchH / 2.0F;
      boolean var8 = var1.hover(this.searchButtonKey, this.searchX, this.searchY, this.searchW, this.searchH, DrawContext.TypeE.HAND);
      float var9 = this.searchButtonHover.target(var8 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      var2.pushAlpha(var3);
      var2.roundRect(this.searchX, this.searchY, this.searchW, this.searchH, this.searchH / 2.0F, BlendMode.lerp(285212671, 486539263, var9));
      var2.roundRectOutline(this.searchX, this.searchY, this.searchW, this.searchH, this.searchH / 2.0F, 1.0F, BlendMode.lerp(452984831, 872415231, var9));
      float var10 = this.searchY + this.searchH / 2.0F;
      var2.icon("search", this.searchX + 14.0F + 8.0F, var10, 15.0F, BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var9));
      var2.text(
         Fonts.MEDIUM,
         var5,
         this.searchX + 14.0F + 16.0F + 8.0F,
         var10,
         12.5F,
         BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var9),
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      KeyLabel.keyCap(var2, "/", this.searchX + this.searchW - 8.0F - 24.0F, var10, 10.0F, Icons.TEXT_3);
      var2.popAlpha();
   }

   private float now() {
      return RenderPipelines.get().time();
   }

   private boolean inCategory() {
      return this.view.target() > 0.5F;
   }

   private void openCategory(Category var1) {
      if (!this.inCategory() && (this.orbit.front() != var1 || !this.orbit.settled())) {
         this.orbit.focus(var1);
         this.pendingOpen = var1;
      } else {
         this.pendingOpen = null;
         this.category = var1;
         FCodec.lastCategory = var1;
         this.orbit.focus(var1);
         this.view.target(1.0F);
         this.grid.show(var1, this.now(), 0, 0.2F);
         this.showProfile(var1, 0, 0.2F);
         SoundRequest.play(SoundEvent.SELECT);
      }
   }

   private void switchCategory(Category var1) {
      if (var1 != this.category) {
         int var2 = Integer.signum(var1.ordinal() - this.category.ordinal());
         this.category = var1;
         FCodec.lastCategory = var1;
         this.orbit.focus(var1);
         this.grid.show(var1, this.now(), var2, 0.04F);
         this.showProfile(var1, var2, 0.04F);
         SoundRequest.play(SoundEvent.TAB);
      }
   }

   private void backToOrbit() {
      if (this.inCategory()) {
         this.view.target(0.0F);
         SoundRequest.play(SoundEvent.COLLAPSE);
      }
   }

   private void openPanel(ModuleRow var1) {
      if (this.panel == null) {
         this.panel = new ModuleList(var1.module, var1);
         this.grid.hide(var1);
         SoundRequest.play(SoundEvent.EXPAND);
      }
   }

   private void showProfile(Category var1, int var2, float var3) {
      if (var1 == Category.PROFILE) {
         this.profile.show(this.now(), var2, var3);
      } else {
         this.profile.hide(this.now(), var2);
      }

      if (var1 == Category.CONFIGS) {
         this.configs.show(this.now(), var2, var3);
      } else {
         this.configs.hide(this.now(), var2);
      }
   }

   private void openProfileSettings() {
      if (this.panel == null) {
         float[] var1 = this.profile.settingsOrigin();
         ModuleRow var2 = new ModuleRow(SettingSection.PROFILE);
         var2.x = var1[0];
         var2.y = var1[1];
         var2.w = var1[2];
         var2.h = 112.0F;
         this.panel = new ModuleList(SettingSection.PROFILE, var2);
         SoundRequest.play(SoundEvent.EXPAND);
      }
   }

   private void openModule(Module var1) {
      if (!this.inCategory() || this.category != var1.category()) {
         if (this.inCategory()) {
            this.switchCategory(var1.category());
         } else {
            this.openCategory(var1.category());
         }
      }

      this.pendingPanel = var1;
   }

   private void close() {
      if (!this.closing) {
         this.closing = true;
         this.menu.target(0.0F);
         this.search.hide();
         SoundRequest.play(SoundEvent.CLOSE);
      }
   }

   private void handle(ModuleSearch.TypeG var1) {
      if (var1.openSettings() != null) {
         this.openModule(var1.openSettings());
      }
   }

   private float toCanvas(double var1) {
      return RenderPipelines.get().fromGui(var1);
   }

   public boolean creamyPreviewHovered() {
      return !this.closing
         && !this.search.active()
         && this.panel != null
         && this.panel.creamyPreviewAt(RenderPipelines.get().mouseX(), RenderPipelines.get().mouseY());
   }

   @Override
   public boolean method_25402(class_11909 var1, boolean var2) {
      return this.pointerPressed(this.toCanvas(var1.comp_4798()), this.toCanvas(var1.comp_4799()), var1.method_74245());
   }

   @Override
   public boolean method_25406(class_11909 var1) {
      this.lastButton = var1.method_74245();
      return this.pointerReleased(this.toCanvas(var1.comp_4798()), this.toCanvas(var1.comp_4799()));
   }

   @Override
   public boolean method_25403(class_11909 var1, double var2, double var4) {
      float var6 = this.toCanvas(var1.comp_4798());
      float var7 = this.toCanvas(var1.comp_4799());
      if (!MenuCommands.allowed()) {
         return true;
      } else {
         this.orbit.mouseDrag(var6);
         if (this.panel == null) {
            this.profile.mouseDrag(var6, var7);
            this.configs.mouseDrag(var6, var7);
         }

         if (this.panel != null) {
            this.panel.mouseDrag(var6, var7);
            Studio var8 = this.panel.studio();
            if (var8 != null && this.studioPressed) {
               var8.mouseDrag(var6, var7);
            }
         }

         return true;
      }
   }

   private boolean pointerPressed(float var1, float var2, int var3) {
      this.pointerDown = true;
      if (this.closing) {
         return true;
      } else if (!MenuCommands.allowed()) {
         return true;
      } else if (this.search.active()) {
         this.handle(this.search.mouseDown(var1, var2, var3));
         return true;
      } else if (this.panel != null) {
         if (!this.panel.closing()) {
            Studio var6 = this.panel.studio();
            if (var6 != null && !this.panel.contains(var1, var2)) {
               this.studioPressed = var6.mouseDown(var1, var2, var3);
            } else {
               this.panel.mouseDown(var1, var2, var3);
            }
         }

         return true;
      } else if (this.inCategory() && this.category == Category.CONFIGS && this.configs.modal()) {
         this.configs.mouseDown(var1, var2, var3, this.now());
         return true;
      } else if (var1 >= this.searchX && var1 <= this.searchX + this.searchW && var2 >= this.searchY && var2 <= this.searchY + this.searchH) {
         this.search.show(this.now());
         return true;
      } else {
         StudioPanel.TypeU var4 = this.orbit.mouseDown(var1, var2);
         if (this.inCategory()) {
            switch (var4) {
               case TAB:
                  this.switchCategory(this.orbit.hitCategory());
                  break;
               case HOME:
                  this.backToOrbit();
                  break;
               default:
                  if (this.category == Category.CONFIGS) {
                     this.configs.mouseDown(var1, var2, var3, this.now());
                     return true;
                  }

                  if (this.category == Category.PROFILE) {
                     ProfileRenderer11.TypeK var5 = this.profile.mouseDown(var1, var2, var3, this.now());
                     if (var5 == ProfileRenderer11.TypeK.SETTINGS) {
                        this.openProfileSettings();
                     }

                     if (var5 != ProfileRenderer11.TypeK.NONE) {
                        return true;
                     }
                  }

                  LayoutEditor.TypeD var7 = this.grid.mouseDown(var1, var2, var3);
                  if (var7 == LayoutEditor.TypeD.OPEN_PANEL && this.grid.target() != null) {
                     this.openPanel(this.grid.target());
                  } else if (var7 == LayoutEditor.TypeD.EDIT_HUD) {
                     SoundRequest.play(SoundEvent.EXPAND);
                     this.field_22787.method_1507(new HudEditorScreen(this));
                  }
            }
         } else {
            switch (var4) {
               case OPEN_FRONT:
                  this.openCategory(this.orbit.front());
                  break;
               case FOCUS:
                  this.orbit.focus(this.orbit.hitCategory());
                  SoundRequest.play(SoundEvent.CLICK);
            }
         }

         return true;
      }
   }

   private boolean pointerReleased(float var1, float var2) {
      this.pointerDown = false;
      this.orbit.mouseUp();
      if (!MenuCommands.allowed()) {
         return true;
      } else {
         if (this.panel != null) {
            Studio var3 = this.panel.studio();
            if (var3 != null && this.studioPressed) {
               var3.mouseUp(var1, var2, this.lastButton);
            }

            this.studioPressed = false;
            this.panel.mouseUp();
         } else if (!this.closing) {
            this.grid.mouseUp(var1, var2, this.now());
            this.configs.mouseUp(var1, var2, this.now());
         }

         this.profile.mouseUp(var1, var2, this.now());
         return true;
      }
   }

   @Override
   public boolean method_25401(double var1, double var3, double var5, double var7) {
      if (!this.closing && !this.search.active() && MenuCommands.allowed()) {
         if (this.panel == null) {
            if (this.inCategory()) {
               if (this.category == Category.PROFILE) {
                  this.profile.scroll(var7);
               } else if (this.category == Category.CONFIGS) {
                  this.configs.scroll(this.toCanvas(var1), this.toCanvas(var3), var7);
               } else {
                  this.grid.scroll(var7);
               }

               return true;
            } else {
               this.wheel += (float)var7;

               while (Math.abs(this.wheel) >= 1.0F) {
                  int var12 = this.wheel > 0.0F ? -1 : 1;
                  this.orbit.rotate(var12);
                  this.wheel += var12;
               }

               return true;
            }
         } else {
            Studio var9 = this.panel.studio();
            float var10 = this.toCanvas(var1);
            float var11 = this.toCanvas(var3);
            if (var9 == null || this.panel.contains(var10, var11) || !var9.scroll(var10, var11, var7)) {
               this.panel.scroll(var7);
            }

            return true;
         }
      } else {
         return true;
      }
   }

   @Override
   public boolean method_25404(class_11908 var1) {
      int var2 = var1.comp_4795();
      if (this.closing) {
         return true;
      } else if (!MenuCommands.allowed()) {
         if (var2 == 256 || MenuKeyBinding.menuMatches(var1)) {
            this.close();
         }

         return true;
      } else if (this.search.active()) {
         this.handle(this.search.keyPressed(var2, var1.method_74239(), var1.method_74240(), this.now()));
         return true;
      } else if (this.panel == null) {
         if (this.inCategory() && this.category == Category.CONFIGS && this.configs.keyPressed(var2, var1.method_74240(), var1.method_74239(), this.now())) {
            return true;
         } else if (this.inCategory()
            && this.category == Category.PROFILE
            && this.profile.keyPressed(var2, var1.method_74240(), var1.method_74239(), this.now())) {
            return true;
         } else if (MenuKeyBinding.menuMatches(var1)) {
            this.close();
            return true;
         } else {
            switch (var2) {
               case 32:
               case 257:
               case 335:
                  if (!this.inCategory()) {
                     this.openCategory(this.orbit.front());
                  }
                  break;
               case 70:
                  if (var1.method_74240()) {
                     this.search.show(this.now());
                  }
                  break;
               case 256:
                  if (this.inCategory()) {
                     this.backToOrbit();
                  } else {
                     this.close();
                  }
                  break;
               case 262:
                  if (this.inCategory()) {
                     this.switchCategory(StudioPanel.next(this.category, 1));
                  } else {
                     this.orbit.rotate(1);
                  }
                  break;
               case 263:
                  if (this.inCategory()) {
                     this.switchCategory(StudioPanel.next(this.category, -1));
                  } else {
                     this.orbit.rotate(-1);
                  }
            }

            return true;
         }
      } else {
         Studio var3 = this.panel.studio();
         if (var3 == null || !var3.keyPressed(var2)) {
            this.panel.keyPressed(var2);
         }

         return true;
      }
   }

   @Override
   public boolean method_25400(class_11905 var1) {
      int var2 = var1.comp_4793();
      if (!MenuCommands.allowed()) {
         return true;
      } else if (!this.closing && this.panel == null) {
         if (!this.search.active() && this.inCategory() && this.category == Category.CONFIGS && this.configs.charTyped(var2, this.now())) {
            return true;
         } else if (!this.search.active() && this.inCategory() && this.category == Category.PROFILE && this.profile.charTyped(var2, this.now())) {
            return true;
         } else {
            if (!this.search.active()) {
               if (Character.isWhitespace(var2) || Character.isISOControl(var2)) {
                  return true;
               }

               this.search.show(this.now());
               if (var2 == 47) {
                  return true;
               }
            }

            this.handle(this.search.charTyped(var2, this.now()));
            return true;
         }
      } else {
         if (!this.closing && this.panel != null) {
            this.panel.charTyped(var2);
         }

         return true;
      }
   }

   @Override
   public void method_29638(List<Path> var1) {
      if (!this.closing && this.panel == null && MenuCommands.allowed()) {
         this.search.hide();
         if (!this.inCategory()) {
            this.openCategory(Category.CONFIGS);
         } else if (this.category != Category.CONFIGS) {
            this.switchCategory(Category.CONFIGS);
         }

         RenderPipelines var2 = RenderPipelines.get();
         this.configs.drop(var1, var2.mouseX(), var2.mouseY(), this.now());
      }
   }

   public void devAction(String var1, String var2) {
      switch (var1) {
         case "rotate":
            this.orbit.rotate(Integer.parseInt(var2));
            break;
         case "open":
            this.openCategory(this.orbit.front());
            break;
         case "category":
            this.openCategory(Category.valueOf(var2.toUpperCase(Locale.ROOT)));
            break;
         case "tab":
            this.switchCategory(Category.valueOf(var2.toUpperCase(Locale.ROOT)));
            break;
         case "panel":
            Module var5 = SettingSection.byId(var2);
            if (var5 != null) {
               this.openModule(var5);
            }
            break;
         case "back":
            this.backToOrbit();
            break;
         case "closepanel":
            if (this.panel != null) {
               this.panel.close();
            }
            break;
         case "toggle":
            Module var6 = SettingSection.byId(var2);
            if (var6 != null) {
               var6.toggle();
            }
            break;
         case "scroll":
            if (this.panel != null) {
               this.panel.scroll(Double.parseDouble(var2));
            }
            break;
         case "search":
            this.search.show(this.now());
            var2.codePoints().forEach(var1x -> this.search.charTyped(var1x, this.now()));
            break;
         case "move":
            String[] var7 = var2.split(";");
            this.devMouseX = Float.parseFloat(var7[0]);
            this.devMouseY = Float.parseFloat(var7[1]);
            break;
         case "click":
            String[] var8 = var2.split(";");
            float var9 = Float.parseFloat(var8[0]);
            float var10 = Float.parseFloat(var8[1]);
            this.devMouseX = var9;
            this.devMouseY = var10;
            this.pointerPressed(var9, var10, 0);
            this.pointerReleased(var9, var10);
            break;
         case "edithud":
            this.field_22787.method_1507(new HudEditorScreen(this));
            break;
         case "close":
            this.close();
            break;
         default:
            throw new IllegalArgumentException("Unknown menu action");
      }
   }

   private static float clamp01(float var0) {
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }
}
