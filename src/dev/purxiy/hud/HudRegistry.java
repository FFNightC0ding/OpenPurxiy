package dev.purxiy.hud;

import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.Setting;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ClientTheme;
import dev.purxiy.loader.fabric.MenuCommands;
import dev.purxiy.modules.Module;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3928;
import net.minecraft.class_9779;

public final class HudRegistry {
   public static final float GAP = 3.0F;
   private static final float PIN_FLIGHT = 0.5F;
   private static final List<HudModule> ELEMENTS = new ArrayList<>();
   private static final List<HudGroupModule> GROUPS = new ArrayList<>();
   private static final Map<String, HudModule> BY_ID = new HashMap<>();
   private static final Set<HudModule> CLAIMED = new HashSet<>();
   private static final HudBounds CONTEXT = new HudBounds();
   private static final HudDragState VANILLA = new HudDragState();
   private static long laidOutFrame = -1L;
   private static long drawnFrame = -1L;

   private HudRegistry() {
   }

   public static void registerElements(Consumer<Module> var0) {
            for (HudModule var1 : ELEMENTS) {
         var0.accept(var1.settingsModule());
      }
   }

   public static List<HudModule> elements() {
      return ELEMENTS;
   }

   static List<HudGroupModule> groups() {
      return GROUPS;
   }

   public static boolean layout(Setting<?> var0) {
      for (HudModule var2 : ELEMENTS) {
         if (var0 != var2.posX && var0 != var2.posY && var0 != var2.placed) {
            if (!(var2 instanceof HudGroupModule var3) || var0 != var3.members && var0 != var3.axis && var0 != var3.size) {
               continue;
            }

            return true;
         }

         return true;
      }

      return false;
   }


   static HudGroupModule freeGroup() {
      for (HudGroupModule var1 : GROUPS) {
         if (var1.free()) {
            return var1;
         }
      }

      return null;
   }

   public static void init() {
   }

   private static boolean shouldRender() {
      class_310 var0 = class_310.method_1551();
      if (ClientTheme.active()) {
         return false;
      } else {
         return HudLayoutEngine.previewing()
            ? true
            : var0.field_1687 != null && var0.field_1724 != null && !var0.field_1690.field_1842 && !(var0.field_1755 instanceof class_3928);
      }
   }

   public static void extract(class_332 var0, class_9779 var1) {
      if (shouldRender()) {
         HudBounds var2 = layout();
         if (!(HudLayoutEngine.hudShown() < 0.5F)) {
            float var3 = RenderPipelines.get().hudToGui();

            for (HudModule var5 : ELEMENTS) {
               if (var5.laidOut && !(var5.presence.value() < 0.85F)) {
                  float var6 = var5.renderScale.value();
                  VANILLA.begin(var0, var5.renderX.value() * var3, var5.renderY.value() * var3, var6 * var3);

                  try {
                     var5.renderVanilla(VANILLA, var2);
                  } finally {
                     VANILLA.end();
                  }
               }
            }
         }
      }
   }

   public static void renderLayer() {
      if (drawnFrame != RenderPipelines.get().frame()) {
         drawnFrame = RenderPipelines.get().frame();
         RenderPipelines var0 = RenderPipelines.get();
         var0.setHudScale(ClientServices.hudLook().scale());
         if (shouldRender()) {
            HudBounds var1 = layout();
            Canvas var2 = var0.hudCanvas();
            HudLayoutEngine.renderBackdrop(var2, var1);
            float var3 = HudLayoutEngine.hudShown();
            if (var3 > 0.005F) {
               var2.pushAlpha(var3);
               HudModule var4 = HudLayoutEngine.dragged();

               for (HudModule var6 : ELEMENTS) {
                  if (var6 != var4 && var6.group == null) {
                     drawUnit(var2, var6, var1);
                  }
               }

               if (var4 != null) {
                  drawUnit(var2, var4, var1);
               }

               var2.popAlpha();
            }
         }
      }
   }

   private static void drawUnit(Canvas var0, HudModule var1, HudBounds var2) {
      float var3 = HudLayoutEngine.ghost(var1);
      var0.pushAlpha(var3);
      draw(var0, var1, var2);
      if (var1 instanceof HudGroupModule var4) {
         for (HudModule var6 : var4.list) {
            float var7 = Math.abs(var6.dockX.value()) + Math.abs(var6.dockY.value());
            var0.pushAlpha(1.0F - 0.55F * Math.min(1.0F, var7 / 24.0F));
            draw(var0, var6, var2);
            var0.popAlpha();
         }
      }

      var0.popAlpha();
   }

   private static void draw(Canvas var0, HudModule var1, HudBounds var2) {
      float var3 = var1.presence.value();
      if (var1.laidOut && !(var3 <= 0.005F)) {
         float var4 = var1.renderScale.value();
         float var5 = var1.width;
         float var6 = var1.height;
         var0.push();
         var0.translate(var1.renderX.value() + var5 * var4 / 2.0F, var1.renderY.value() + var6 * var4 / 2.0F);
         var0.scale(var4 * (0.92F + 0.08F * Math.min(1.0F, var3)));
         var0.translate(-var5 / 2.0F, -var6 / 2.0F);
         var0.pushAlpha(Math.min(1.0F, var3));
         var1.render(var0, var2);
         var0.popAlpha();
         var0.pop();
      }
   }

   static HudBounds layout() {
      RenderPipelines var0 = RenderPipelines.get();
      HudBounds var1 = CONTEXT;
      if (laidOutFrame == var0.frame()) {
         return var1;
      } else {
         laidOutFrame = var0.frame();
         class_310 var2 = class_310.method_1551();
         Canvas var3 = var0.hudCanvas();
         var1.mc = var2;
         var1.player = var2.field_1724;
         var1.level = var2.field_1687;
         var1.time = var0.time();
         var1.dt = var0.delta();
         var1.partialTick = var2.method_61966().method_60637(true);
         var1.editing = HudLayoutEngine.active();
         var1.width = var3.width();
         var1.height = var3.height();
         HudElement.frame(var1.dt);
         resolveGroups();

         for (HudModule var5 : ELEMENTS) {
            if (var5 instanceof HudGroupModule var6 && var6.list.isEmpty()) {
               var6.laidOut = false;
               var6.positioned = false;
               var6.presence.snap(0.0F);
            } else {
               boolean var14 = var5.shown() && (var1.editing || var5.hasContent(var1));
               var5.wanted = var14;
               var5.updatePresence(var14, var1.dt);
               if (!var14 && var5.presence.value() <= 0.005F) {
                  var5.laidOut = false;
                  var5.positioned = false;
               } else {
                  var5.measure(var1, var5.measure);
                  var5.width = Math.max(1.0F, var5.measure[0]);
                  var5.height = Math.max(1.0F, var5.measure[1]);
                  if (var5.group != null) {
                     var5.laidOut = true;
                     var5.pinned = false;
                  } else {
                     float var7 = var1.width - 12.0F - var5.screenWidth();
                     float var8 = var1.height - 12.0F - var5.screenHeight();
                     var5.x = 6.0F + (var7 > 0.0F ? var5.posX.floatValue() * var7 : var7 / 2.0F);
                     var5.y = 6.0F + (var8 > 0.0F ? var5.posY.floatValue() * var8 : var8 / 2.0F);
                     var5.laidOut = true;
                     boolean var9 = var5.pinned;
                     var5.pinned = var5.pin(var1, var5.pin);
                     if (var5.pinned) {
                        var5.x = var5.pin[0];
                        var5.y = var5.pin[1];
                        var5.pinScale = var5.pin[2];
                        if (!var9 && var5.positioned) {
                           var5.pinFromX = var5.renderX.value();
                           var5.pinFromY = var5.renderY.value();
                           var5.pinFromScale = var5.renderScale.value();
                           var5.pinFlight = 0.0F;
                        }
                     }
                  }
               }
            }
         }

         flowUnplaced(var1);

         for (HudModule var12 : ELEMENTS) {
            if (var12.laidOut && var12.group == null) {
               var12.dockedTo = null;
               if (var12.pinned) {
                  follow(var12, var1.dt);
               } else if (!var12.positioned) {
                  var12.renderX.snap(var12.x);
                  var12.renderY.snap(var12.y);
                  var12.renderScale.snap(var12.scale());
                  var12.positioned = true;
               } else {
                  var12.renderX.target(var12.x).update(var1.dt);
                  var12.renderY.target(var12.y).update(var1.dt);
                  var12.renderScale.target(var12.scale()).update(var1.dt);
               }
            }
         }

         for (HudGroupModule var13 : GROUPS) {
            if (var13.laidOut) {
               var13.placeMembers(var1);
            }
         }

         return var1;
      }
   }

   private static void resolveGroups() {
      CLAIMED.clear();

      for (HudModule var1 : ELEMENTS) {
         var1.group = null;
      }

      for (HudGroupModule var5 : GROUPS) {
         var5.resolve(BY_ID, CLAIMED);

         for (HudModule var3 : var5.list) {
            var3.group = var5;
         }
      }
   }

   private static void follow(HudModule var0, float var1) {
      float var2 = var0.x;
      float var3 = var0.y;
      float var4 = var0.pinScale;
      if (!var0.positioned) {
         var0.pinFlight = 1.0F;
         var0.positioned = true;
      }

      if (var0.pinFlight < 1.0F) {
         var0.pinFlight = Math.min(1.0F, var0.pinFlight + MotionStyle.MENU.dt(var1) / 0.5F);
         float var5 = Easing.STANDARD.apply(var0.pinFlight);
         var2 = var0.pinFromX + (var2 - var0.pinFromX) * var5;
         var3 = var0.pinFromY + (var3 - var0.pinFromY) * var5;
         var4 = var0.pinFromScale + (var4 - var0.pinFromScale) * var5;
      }

      var0.renderX.snap(var2);
      var0.renderY.snap(var3);
      var0.renderScale.snap(var4);
   }

   private static void flowUnplaced(HudBounds var0) {
      for (int var1 = 0; var1 < ELEMENTS.size(); var1++) {
         HudModule var2 = ELEMENTS.get(var1);
         if (var2.arranged() && !var2.placed.enabled()) {
            boolean var3 = var2.y + var2.screenHeight() / 2.0F > var0.height / 2.0F;

            for (int var4 = 0; var4 < ELEMENTS.size(); var4++) {
               boolean var5 = false;

               for (int var6 = 0; var6 < ELEMENTS.size(); var6++) {
                  HudModule var7 = ELEMENTS.get(var6);
                  if (var6 != var1
                     && (var6 < var1 || var7.placed.enabled())
                     && var7.arranged()
                     && var7.presence.value() > 0.05F
                     && overlaps(var2, var2.x, var2.y, var7, 3.0F)) {
                     var2.y = var3 ? var7.y - var2.screenHeight() - 3.0F : var7.y + var7.screenHeight() + 3.0F;
                     var5 = true;
                  }
               }

               if (!var5) {
                  break;
               }
            }

            var2.y = Math.max(6.0F, Math.min(var0.height - 6.0F - var2.screenHeight(), var2.y));
         }
      }
   }

   static boolean overlaps(HudModule var0, float var1, float var2, HudModule var3, float var4) {
      return var1 < var3.x + var3.screenWidth() + var4
         && var1 + var0.screenWidth() + var4 > var3.x
         && var2 < var3.y + var3.screenHeight() + var4
         && var2 + var0.screenHeight() + var4 > var3.y;
   }

   public static void invalidate() {
      laidOutFrame = -1L;
      drawnFrame = -1L;
   }

   public static AutoCloseable install(HudModule var0) {
      if (BY_ID.putIfAbsent(var0.id(), var0) != null) {
         throw new IllegalStateException("HUD already owned");
      } else {
         if (GROUPS.isEmpty()) {
            for (int var1 = 1; var1 <= 8; var1++) {
               HudGroupModule var2 = new HudGroupModule(var1);
               GROUPS.add(var2);
               ELEMENTS.add(var2);
            }

            MenuCommands.loadGroups();
         }

         ELEMENTS.add(ELEMENTS.size() - GROUPS.size(), var0);
         invalidate();
         return () -> {
            HudLayoutEngine.releaseOwnedState();
            ELEMENTS.remove(var0);
            BY_ID.remove(var0.id(), var0);
            CLAIMED.clear();

            for (HudGroupModule var2x : GROUPS) {
               var2x.list.remove(var0);
            }

            var0.group = null;
            var0.dockedTo = null;
            invalidate();
            CONTEXT.mc = null;
            CONTEXT.level = null;
            CONTEXT.player = null;
         };
      }
   }

   public static void persist() {
      for (HudModule var1 : List.copyOf(ELEMENTS)) {
         if (!(var1 instanceof HudGroupModule)) {
            MenuCommands.persistHud(var1);
         }
      }

      MenuCommands.saveGroups(GROUPS);
   }

   public static List<HudModule> parts(String var0) {
      return ELEMENTS.stream().filter(var1 -> !(var1 instanceof HudGroupModule) && var1.settingsModule().id().equals(var0)).toList();
   }

   public static boolean applyValues(String var0, Map<String, String> var1) {
      boolean var2 = false;

      for (HudModule var4 : parts(var0)) {
         for (Setting var6 : var4.settingsModule().settings()) {
            if (var1.containsKey(var6.id())) {
               MenuCommands.loadValue(var6, (String)var1.get(var6.id()));
            }
         }

         var2 = true;
      }

      invalidate();
      return var2;
   }
}
