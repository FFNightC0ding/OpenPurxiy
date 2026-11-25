package dev.purxiy.hud;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.MenuCommands;
import dev.purxiy.modules.Module;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.persist.FCodec;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.minecraft.class_11876;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3675;
import net.minecraft.class_437;

public final class HudLayoutEngine {
   private static final float SNAP = 6.0F;
   private static final float DRAG_THRESHOLD = 2.0F;
   private static final long DOUBLE_CLICK = 300L;
   private static final float LABEL_H = 18.0F;
   private static final float HINT_H = 16.0F;
   private static final float SEAM_REACH = 5.0F;
   private static final float LINK_GAP = 14.0F;
   private static final float SEAM_H = 18.0F;
   private static boolean inChat;
   private static boolean inScreen;

   private static class_437 host;

   private static HudModule hovered;

   private static HudModule hoveredMember;

   private static HudModule dragging;

   private static HudModule pressed;
   private static float grabX;
   private static float grabY;
   private static float downX;
   private static float downY;
   private static boolean moved;
   private static List<HudLayoutEngine.AxisBand> guides = new ArrayList<>();
   private static float guideFade = -10.0F;

   private static HudModule scaled;
   private static float scaledAt = -10.0F;

   private static HudModule lastClicked;
   private static long lastClickTime = 0L;
   private static float lastHoverAt = -10.0F;
   private static final Animator APPEAR = Animator.smooth(0.0F);
   private static final Map<HudModule, Animator> HIGHLIGHT = new HashMap<>();

   private static HudModule flashed;
   private static float flashedAt = -10.0F;

   private static HudModule settle;

   private static HudModule mergeTarget;
   private static int mergeIndex = 0;
   private static boolean mergeRow;
   private static final Animator MERGE = Animator.smooth(0.0F);
   private static final Animator CARET_X = new Animator(0.0F, 520.0F, 46.0F);
   private static final Animator CARET_Y = new Animator(0.0F, 520.0F, 46.0F);

   private static HudModule caretFor;

   private static HudLayoutEngine.LayoutPass seam;

   private static HudLayoutEngine.LayoutPass shownSeam;
   private static final Animator SEAM_ALPHA = Animator.smooth(0.0F);
   private static float seamSince = -10.0F;

   private static HudModule labelFor;

   private static HudModule labelMember;
   private static float labelX;
   private static float labelY;
   private static float labelSwitchedAt = -10.0F;
   private static int labelSide = 1;
   private static int labelPlace = 1;
   private static int labelChoice = 0;
   private static final float[] LABEL = new float[4];
   private static final float[] GEAR = new float[4];
   private static final Animator GEAR_HOVER = Animator.smooth(0.0F);
   private static final Animator LABEL_ALPHA = Animator.smooth(0.0F);
   private static final Animator LABEL_DX = new Animator(0.0F, 600.0F, 49.0F);
   private static final Animator LABEL_DY = new Animator(0.0F, 600.0F, 49.0F);
   private static final Animator LABEL_W = new Animator(0.0F, 600.0F, 49.0F);
   private static final Animator SIZE_POP = Animator.bouncy(0.0F);
   private static final Animator HINT_Y = new Animator(0.0F, 260.0F, 32.0F);
   private static final Animator HINT_FADE = Animator.smooth(0.0F);
   private static final Animator HINT_W = new Animator(0.0F, 420.0F, 41.0F);
   private static String hint = "";
   private static String previousHint = "";
   private static float hintChangedAt = -10.0F;
   static final HudGroupEditor POPOVER = new HudGroupEditor();
   static final HudLayoutGrid OVERVIEW = new HudLayoutGrid();
   private static float devX = Float.NaN;
   private static float devY = Float.NaN;

   private HudLayoutEngine() {
   }

   public static boolean active() {
      return inChat || inScreen;
   }

   public static boolean previewing() {
      return inScreen;
   }


   public static HudModule dragged() {
      return dragging;
   }

   public static float hudShown() {
      return OVERVIEW.hudShown();
   }

   static float ghost(HudModule var0) {
      return var0 == dragging && MERGE.value() > 0.005F ? 1.0F - 0.45F * MERGE.value() : 1.0F;
   }

   static void beginChat(class_437 var0) {
      begin();
      inChat = true;
      host = var0;
   }

   static void endChat() {
      inChat = false;
      finish();
   }

   static void beginScreen(class_437 var0) {
      begin();
      inScreen = true;
      host = var0;
   }

   static void endScreen() {
      inScreen = false;
      finish();
   }

   private static void begin() {
      if (!active()) {
         APPEAR.snap(0.0F);
      }
   }

   public static void releaseOwnedState() {
      finish();
      HIGHLIGHT.clear();
      scaled = null;
      lastClicked = null;
      flashed = null;
      settle = null;
      caretFor = null;
      labelFor = null;
      labelMember = null;
      host = null;
   }

   private static void finish() {
      if (pressed != null) {
         pressed.pointerUp(Float.NaN, Float.NaN);
         pressed = null;
      }

      dragging = null;
      mergeTarget = null;
      MERGE.snap(0.0F);
      guides = new ArrayList<>();
      hovered = null;
      hoveredMember = null;
      seam = null;
      shownSeam = null;
      SEAM_ALPHA.snap(0.0F);
      POPOVER.reset();
      OVERVIEW.reset();
      FCodec.save();
   }

   private static MenuCommands.VisualModule settings() {
      return SettingSection.HUD;
   }

   public static boolean mouseClicked(float var0, float var1, int var2) {
      if (!active()) {
         return false;
      } else {
         if (POPOVER.visible()) {
            if (POPOVER.mouseDown(var0, var1, var2)) {
               return true;
            }

            if (POPOVER.active()) {
               HudModule var8 = var2 == 1 ? unitOrMemberAt(var0, var1) : null;
               if (var8 != null && !OVERVIEW.active()) {
                  POPOVER.open(var8);
               } else {
                  POPOVER.close();
               }

               return true;
            }
         }

         if (OVERVIEW.visible()) {
            if (!OVERVIEW.mouseDown(var0, var1, var2) && OVERVIEW.active()) {
               OVERVIEW.close();
            }

            return true;
         } else if (var2 == 0 && seamClick(var0, var1)) {
            return true;
         } else if (labelClick(var0, var1, var2)) {
            return true;
         } else {
            HudModule var3 = elementAt(var0, var1);
            if (var3 == null) {
               if (var2 == 1) {
                  OVERVIEW.open(var0, var1);
                  return true;
               } else {
                  return false;
               }
            } else {
               HudModule var4 = memberAt(var3, var0, var1);
               HudModule var5 = var4 != null ? var4 : var3;
               if (var2 == 0 && var5.pointerDown(var5.localX(var0), var5.localY(var1), var2)) {
                  pressed = var5;
                  return true;
               } else if (var2 == 1) {
                  POPOVER.open(var5);
                  return true;
               } else if (var2 != 0) {
                  return false;
               } else {
                  long var6 = System.currentTimeMillis();
                  if (lastClicked == var3 && var6 - lastClickTime < 300L) {
                     lastClicked = null;
                     centre(var3);
                     return true;
                  } else {
                     lastClicked = var3;
                     lastClickTime = var6;
                     guides = new ArrayList<>();
                     dragging = var3;
                     grabX = var0 - var3.x;
                     grabY = var1 - var3.y;
                     downX = var0;
                     downY = var1;
                     moved = false;
                     mergeTarget = null;
                     SoundRequest.play(SoundEvent.CLICK, 1.05F, 0.8F);
                     return true;
                  }
               }
            }
         }
      }
   }

   public static boolean mouseDragged(float var0, float var1) {
      if (POPOVER.dragging()) {
         POPOVER.mouseDrag(var0, var1);
         return true;
      } else if (pressed != null) {
         pressed.pointerDrag(pressed.localX(var0), pressed.localY(var1));
         return true;
      } else {
         HudModule var2 = dragging;
         if (var2 == null) {
            return false;
         } else if (!moved && Math.hypot(var0 - downX, var1 - downY) < 2.0) {
            return true;
         } else {
            moved = true;
            var2.placed.set(true);
            HudModule var3 = mergeTarget;
            findMerge(var2, var0, var1);
            if (mergeTarget != null) {
               HudBounds var4 = HudRegistry.layout();
               float var5 = clamp(var0 - grabX, 6.0F, var4.width - 6.0F - var2.screenWidth());
               float var6 = clamp(var1 - grabY, 6.0F, var4.height - 6.0F - var2.screenHeight());
               var2.moveTo(var5, var6, var4.width, var4.height);
               guides = new ArrayList<>();
               if (var3 != mergeTarget) {
                  SoundRequest.play(SoundEvent.ORBIT_TICK, 1.25F, 0.7F);
               }
            } else {
               place(var2, var0 - grabX, var1 - grabY, true);
            }

            return true;
         }
      }
   }

   public static boolean mouseReleased(float var0, float var1) {
      POPOVER.mouseUp();
      OVERVIEW.mouseUp();
      if (pressed != null) {
         HudModule var4 = pressed;
         pressed = null;
         var4.pointerUp(var4.localX(var0), var4.localY(var1));
         return true;
      } else {
         HudModule var2 = dragging;
         if (var2 == null) {
            return false;
         } else {
            dragging = null;
            guides = new ArrayList<>();
            HudModule var3 = mergeTarget;
            mergeTarget = null;
            if (moved && var3 != null) {
               merge(var2, var3, mergeIndex, mergeRow);
            } else if (moved) {
               SoundRequest.play(SoundEvent.TAB, 0.95F);
            }

            return true;
         }
      }
   }

   public static boolean mouseScrolled(float var0, float var1, double var2) {
      if (!active() || var2 == 0.0) {
         return false;
      } else if (!POPOVER.visible() && !OVERVIEW.visible()) {
         HudModule var4 = dragging != null ? dragging : elementAt(var0, var1);
         if (var4 == null) {
            return false;
         } else {
            HudModule var5 = dragging == null ? memberAt(var4, var0, var1) : null;
            HudModule var6 = var5 != null ? var5 : var4;
            if (dragging == null && var6.pointerScroll(var6.localX(var0), var6.localY(var1), var2)) {
               return true;
            } else {
               rescale(var4, var2 > 0.0 ? 1 : -1);
               return true;
            }
         }
      } else {
         if (!POPOVER.scroll(var0, var1, var2)) {
            OVERVIEW.scroll(var0, var1, var2);
         }

         return true;
      }
   }

   public static boolean keyPressed(int var0) {
      if (!active() || var0 != 256) {
         return false;
      } else if (POPOVER.active()) {
         POPOVER.close();
         return true;
      } else if (OVERVIEW.active()) {
         OVERVIEW.close();
         return true;
      } else {
         return false;
      }
   }


   private static HudModule elementAt(float var0, float var1) {
      return elementAt(var0, var1, (HudModule)null);
   }


   private static HudModule elementAt(float var0, float var1,  HudModule var2) {
      List var3 = HudRegistry.elements();

      for (int var4 = var3.size() - 1; var4 >= 0; var4--) {
         HudModule var5 = (HudModule)var3.get(var4);
         if (var5 != var2
            && var5.arranged()
            && !(var5.presence.value() < 0.5F)
            && var0 >= var5.x - 3.0F
            && var0 <= var5.x + var5.screenWidth() + 3.0F
            && var1 >= var5.y - 3.0F
            && var1 <= var5.y + var5.screenHeight() + 3.0F) {
            return var5;
         }
      }

      return null;
   }


   private static HudModule memberAt( HudModule var0, float var1, float var2) {
      if (var0 instanceof HudGroupModule var3) {
         float[] var4 = new float[4];
         HudModule var5 = null;
         float var6 = Float.MAX_VALUE;

         for (int var7 = 0; var7 < var3.list.size(); var7++) {
            HudModule var8 = var3.list.get(var7);
            if (var8.laidOut && !(var8.presence.value() < 0.3F)) {
               var3.slotRect(var7, var4);
               float var9 = var3.row() ? var1 : var2;
               float var10 = var3.row() ? var4[0] : var4[1];
               float var11 = var3.row() ? var4[2] : var4[3];
               float var12 = var9 < var10 ? var10 - var9 : (var9 > var10 + var11 ? var9 - var10 - var11 : 0.0F);
               if (var12 < var6) {
                  var6 = var12;
                  var5 = var8;
               }
            }
         }

         return var5;
      } else {
         return null;
      }
   }


   private static HudModule unitOrMemberAt(float var0, float var1) {
      HudModule var2 = elementAt(var0, var1);
      HudModule var3 = memberAt(var2, var0, var1);
      return var3 != null ? var3 : var2;
   }

   private static void place(HudModule var0, float var1, float var2, boolean var3) {
      HudBounds var4 = HudRegistry.layout();
      float var5 = var0.screenWidth();
      float var6 = var0.screenHeight();
      float var7 = var4.width - 6.0F - var5;
      float var8 = var4.height - 6.0F - var6;
      var1 = clamp(var1, 6.0F, var7);
      var2 = clamp(var2, 6.0F, var8);
      ArrayList<HudLayoutEngine.AxisBand>var9 = new ArrayList();
      if (var3 && settings().snap.enabled() && !altDown()) {
         float[] var10 = snapAxis(var0, var1, var2, var5, var6, true, var4, var9);
         var1 = clamp(var10[0], 6.0F, var7);
         float[] var11 = snapAxis(var0, var1, var2, var5, var6, false, var4, var9);
         var2 = clamp(var11[0], 6.0F, var8);
      }

      if (settings().noOverlap.enabled()) {
         float[] var14 = resolve(var0, var1, var2, var4);
         if (var14 == null) {
            return;
         }

         var1 = var14[0];
         var2 = var14[1];
      }

      if (!var9.isEmpty() && (guides.isEmpty() || !sameGuides(guides, var9))) {
         SoundRequest.play(SoundEvent.ORBIT_TICK, 1.1F, 0.6F);
      }

      guides = var9;
      var0.moveTo(var1, var2, var4.width, var4.height);
   }

   private static boolean altDown() {
      return class_3675.method_15987(class_310.method_1551().method_22683(), 342);
   }

   private static float[] snapAxis(
      HudModule var0, float var1, float var2, float var3, float var4, boolean var5, HudBounds var6, List<HudLayoutEngine.AxisBand> var7
   ) {
      float var8 = var5 ? var1 : var2;
      float var9 = var5 ? var3 : var4;
      float var10 = 6.001F;
      float var11 = 0.0F;
      boolean var12 = false;
      HudModule var13 = null;
      float var14 = (var5 ? var6.width : var6.height) / 2.0F;
      float var15 = var14 - (var8 + var9 / 2.0F);
      if (Math.abs(var15) < Math.abs(var10)) {
         var10 = var15;
         var11 = var14;
         var12 = true;
      }

      for (HudModule var17 : HudRegistry.elements()) {
         if (var17 != var0 && var17.arranged() && !(var17.presence.value() < 0.5F)) {
            float var18 = var5 ? var17.x : var17.y;
            float var19 = var5 ? var17.screenWidth() : var17.screenHeight();
            float[][] var20 = new float[][]{
               {var18, 0.0F}, {var18 + var19 / 2.0F, var9 / 2.0F}, {var18 + var19, var9}, {var18 + var19 + 3.0F, 0.0F}, {var18 - 3.0F, var9}
            };

            for (float[] var24 : var20) {
               float var25 = var24[0] - (var8 + var24[1]);
               if (Math.abs(var25) < Math.abs(var10)) {
                  var10 = var25;
                  var11 = var24[0];
                  var12 = false;
                  var13 = var17;
               }
            }
         }
      }

      if (Math.abs(var10) > 6.0F) {
         return new float[]{var8};
      } else {
         float var26 = var8 + var10;
         if (var12) {
            var7.add(new HudLayoutEngine.AxisBand(var5, var11, 0.0F, var5 ? var6.height : var6.width, true));
         } else if (var13 != null) {
            float var27 = var5 ? Math.min(var2, var13.y) : Math.min(var1, var13.x);
            float var28 = var5 ? Math.max(var2 + var4, var13.y + var13.screenHeight()) : Math.max(var1 + var3, var13.x + var13.screenWidth());
            var7.add(new HudLayoutEngine.AxisBand(var5, var11, var27 - 8.0F, var28 + 8.0F, false));
         }

         return new float[]{var26};
      }
   }


   private static float[] resolve(HudModule var0, float var1, float var2, HudBounds var3) {
      float var4 = var0.screenWidth();
      float var5 = var0.screenHeight();
      float var6 = 3.0F;

      for (int var7 = 0; var7 < 8; var7++) {
         HudModule var8 = null;

         for (HudModule var10 : HudRegistry.elements()) {
            if (blocks(var0, var10) && HudRegistry.overlaps(var0, var1, var2, var10, var6)) {
               var8 = var10;
               break;
            }
         }

         if (var8 == null) {
            return new float[]{var1, var2};
         }

         float[][] var19 = new float[][]{
            {var8.x - var6 - var4, var2}, {var8.x + var8.screenWidth() + var6, var2}, {var1, var8.y - var6 - var5}, {var1, var8.y + var8.screenHeight() + var6}
         };
         float var20 = Float.MAX_VALUE;
         float[] var11 = null;

         for (float[] var15 : var19) {
            if (!(var15[0] < 5.99F) && !(var15[0] > var3.width - 6.0F - var4 + 0.01F) && !(var15[1] < 5.99F) && !(var15[1] > var3.height - 6.0F - var5 + 0.01F)
               )
             {
               float var16 = Math.abs(var15[0] - var1) + Math.abs(var15[1] - var2);
               if (var16 < var20) {
                  var20 = var16;
                  var11 = var15;
               }
            }
         }

         if (var11 == null) {
            return null;
         }

         var1 = var11[0];
         var2 = var11[1];
      }

      for (HudModule var18 : HudRegistry.elements()) {
         if (blocks(var0, var18) && HudRegistry.overlaps(var0, var1, var2, var18, var6)) {
            return null;
         }
      }

      return new float[]{var1, var2};
   }

   private static boolean blocks(HudModule var0, HudModule var1) {
      if (var1 != var0 && var1.arranged() && !(var1.presence.value() <= 0.5F)) {
         List var2 = HudRegistry.elements();
         return var1.placed.enabled() || var2.indexOf(var1) < var2.indexOf(var0);
      } else {
         return false;
      }
   }

   private static void rescale(HudModule var0, int var1) {
      HudBounds var2 = HudRegistry.layout();
      double var3 = var0.size.get();
      double var5 = Math.round((var3 + 0.05 * var1) * 20.0) / 20.0;
      var5 = Math.max(var0.size.min(), Math.min(var0.size.max(), var5));
      if (var5 == var3) {
         SoundRequest.play(SoundEvent.DENY, 1.0F, 0.6F);
      } else {
         float var7 = var0.x + var0.screenWidth() / 2.0F;
         float var8 = var0.y + var0.screenHeight() / 2.0F;
         var0.size.set(var5);
         float var9 = clamp(var7 - var0.screenWidth() / 2.0F, 6.0F, var2.width - 6.0F - var0.screenWidth());
         float var10 = clamp(var8 - var0.screenHeight() / 2.0F, 6.0F, var2.height - 6.0F - var0.screenHeight());
         if (settings().noOverlap.enabled()) {
            float[] var11 = resolve(var0, var9, var10, var2);
            if (var11 == null) {
               var0.size.set(var3);
               SoundRequest.play(SoundEvent.DENY, 1.0F, 0.6F);
               return;
            }

            var9 = var11[0];
            var10 = var11[1];
         }

         var0.placed.set(true);
         var0.moveTo(var9, var10, var2.width, var2.height);
         scaled = var0;
         scaledAt = RenderPipelines.get().time();
         SIZE_POP.snap(0.0F).impulse(12.0F);
         float var13 = (float)((var5 - var0.size.min()) / (var0.size.max() - var0.size.min()));
         SoundRequest.play(SoundEvent.SLIDER_TICK, 0.8F + 0.5F * var13);
      }
   }

   static void centre(HudModule var0) {
      HudBounds var1 = HudRegistry.layout();
      float var2 = var1.width / 2.0F - var0.screenWidth() / 2.0F;
      float var3 = var0.y;
      if (settings().noOverlap.enabled()) {
         float[] var4 = resolve(var0, var2, var3, var1);
         if (var4 == null) {
            SoundRequest.play(SoundEvent.DENY, 1.0F, 0.6F);
            return;
         }

         var2 = var4[0];
         var3 = var4[1];
      }

      var0.placed.set(true);
      var0.moveTo(var2, var3, var1.width, var1.height);
      guides = new ArrayList<>(List.of(new HudLayoutEngine.AxisBand(true, var1.width / 2.0F, 0.0F, var1.height, true)));
      guideFade = RenderPipelines.get().time();
      SoundRequest.play(SoundEvent.SELECT, 1.0F, 0.7F);
   }

   static void openSettings(Module var0,  HudModule var1, float[] var2) {
      POPOVER.openAt(var0, var1, (HudModule)null, var2[0], var2[1], var2[2], var2[3]);
   }

   private static boolean canMerge(HudModule var0, HudModule var1) {
      return var0 != var1 && var0.mergeable() && var1.mergeable()
         ? var0 instanceof HudGroupModule || var1 instanceof HudGroupModule || HudRegistry.freeGroup() != null
         : false;
   }

   private static void findMerge(HudModule var0, float var1, float var2) {
      mergeTarget = null;
      if (!altDown()) {
         HudModule var3 = elementAt(var1, var2, var0);
         if (var3 != null && canMerge(var0, var3)) {
            if (!(var3 instanceof HudGroupModule var4)) {
               float var11 = var3.renderX.value() + var3.renderWidth() / 2.0F;
               float var12 = var3.renderY.value() + var3.renderHeight() / 2.0F;
               float var13 = (var1 - var11) / Math.max(1.0F, var3.renderWidth() / 2.0F);
               float var14 = (var2 - var12) / Math.max(1.0F, var3.renderHeight() / 2.0F);
               mergeRow = Math.abs(var13) >= Math.abs(var14) * 0.75F;
               mergeIndex = (mergeRow ? var13 : var14) < 0.0F ? 0 : 1;
            } else {
               boolean var5 = var4.row();
               float var6 = var5 ? var1 : var2;
               int var7 = var4.list.size();

               for (int var8 = 0; var8 < var4.list.size(); var8++) {
                  HudModule var9 = var4.list.get(var8);
                  if (var9.laidOut && !(var9.presence.value() < 0.3F)) {
                     float var10 = var5 ? var9.renderX.value() + var9.renderWidth() / 2.0F : var9.renderY.value() + var9.renderHeight() / 2.0F;
                     if (var6 < var10) {
                        var7 = var8;
                        break;
                     }
                  }
               }

               mergeRow = var5;
               mergeIndex = var7;
            }

            mergeTarget = var3;
         }
      }
   }

   static void merge(HudModule var0, HudModule var1, int var2, boolean var3) {
      if (!canMerge(var0, var1)) {
         SoundRequest.play(SoundEvent.DENY, 1.0F, 0.6F);
      } else {
         HudBounds var4 = HudRegistry.layout();
         Object var5;
         if (var0 instanceof HudGroupModule var6) {
            var5 = new ArrayList<>(var6.list);
         } else {
            var5 = List.of(var0);
         }

         List var14 = (List)var5;
         ArrayList var7;
         HudGroupModule var8;
         if (var1 instanceof HudGroupModule var9) {
            var8 = var9;
            var7 = new ArrayList<>(var9.list);
            var7.addAll(Math.max(0, Math.min(var2, var7.size())), var14);
            if (var0 instanceof HudGroupModule var10) {
               var10.clear();
            }
         } else {
            HudGroupModule var15;
            if (var0 instanceof HudGroupModule var17) {
               var15 = var17;
            } else {
               var15 = HudRegistry.freeGroup();
            }

            var8 = var15;
            if (var15 == null) {
               SoundRequest.play(SoundEvent.DENY, 1.0F, 0.6F);
               return;
            }

            var7 = new ArrayList();
            if (var2 == 0) {
               var7.addAll(var14);
               var7.add(var1);
            } else {
               var7.add(var1);
               var7.addAll(var14);
            }

            var15.axis.set(var3 ? "Row" : "Column");
            var15.size.set(var1.size.get());
            var15.background.set(var1.background.get());
            var15.opacity.set(var1.opacity.get());
            var15.placed.set(true);
            float[] var18 = new float[2];
            HudGroupModule.preferredSize(var7, var3, var18);
            float var11 = var15.size.floatValue();
            float var12 = var1.x;
            float var13 = var1.y;
            if (var2 == 0) {
               if (var3) {
                  var12 -= (var18[0] - var1.width) * var11;
               } else {
                  var13 -= (var18[1] - var1.height) * var11;
               }
            }

            var15.width = Math.max(1.0F, var18[0]);
            var15.height = Math.max(1.0F, var18[1]);
            var15.moveTo(
               clamp(var12, 6.0F, var4.width - 6.0F - var15.screenWidth()),
               clamp(var13, 6.0F, var4.height - 6.0F - var15.screenHeight()),
               var4.width,
               var4.height
            );
         }

         boolean var16 = !var8.laidOut;
         var8.setMembers(var7);

         for (HudModule var20 : (Iterable<HudModule>)(Object)(var7)) {
            var20.group = var8;
         }

         if (var16) {
            var8.appear();
         }

         settle = var8;
         flash(var8);
         SoundRequest.play(SoundEvent.SELECT, 1.12F, 0.8F);
      }
   }

   private static void swap(HudGroupModule var0, HudModule var1, HudModule var2) {
      ArrayList var3 = new ArrayList<>(var0.list);
      int var4 = var3.indexOf(var1);
      int var5 = var3.indexOf(var2);
      if (var4 >= 0 && var5 >= 0) {
         Collections.swap(var3, var4, var5);
         var0.setMembers(var3);
         flash(var0);
         SoundRequest.play(SoundEvent.TAB, 1.1F);
      }
   }

   private static void split(HudGroupModule var0, HudModule var1) {
      int var2 = var0.list.indexOf(var1);
      if (var2 > 0) {
         HudBounds var3 = HudRegistry.layout();
         ArrayList var4 = new ArrayList<>(var0.list.subList(0, var2));
         ArrayList var5 = new ArrayList<>(var0.list.subList(var2, var0.list.size()));
         boolean var6 = var0.row();
         float var7 = 10.0F * var0.scale();
         float var8 = var6 ? var0.x + var0.screenWidth() : var0.y + var0.screenHeight();
         boolean var9 = var8 + var7 <= (var6 ? var3.width : var3.height) - 6.0F;
         float[] var10 = new float[]{var0.size.floatValue(), var0.opacity.floatValue()};
         boolean var11 = var0.background.enabled();
         String var12 = var0.axis.get();
         HudLayoutEngine.GroupLayout var13 = part(var4, var6, var9 ? 0.0F : -var7);
         HudLayoutEngine.GroupLayout var14 = part(var5, var6, var9 ? var7 : 0.0F);
         var0.clear();
         apply(var13, var3, var12, var10, var11, var6);
         apply(var14, var3, var12, var10, var11, var6);
         SoundRequest.play(SoundEvent.COLLAPSE, 1.15F, 0.8F);
      }
   }

   private static HudLayoutEngine.GroupLayout part(List<HudModule> var0, boolean var1, float var2) {
      float var3 = Float.MAX_VALUE;
      float var4 = Float.MAX_VALUE;

      for (HudModule var6 : var0) {
         if (var6.laidOut) {
            var3 = Math.min(var3, var6.x);
            var4 = Math.min(var4, var6.y);
         }
      }

      if (var3 == Float.MAX_VALUE) {
         var3 = 0.0F;
         var4 = 0.0F;
      }

      return new HudLayoutEngine.GroupLayout(var0, var1 ? var3 + var2 : var3, var1 ? var4 : var4 + var2);
   }

   private static void apply(HudLayoutEngine.GroupLayout var0, HudBounds var1, String var2, float[] var3, boolean var4, boolean var5) {
      List var6 = var0.members();
      if (var6.size() >= 2) {
         HudGroupModule var7 = HudRegistry.freeGroup();
         if (var7 != null) {
            var7.axis.set(var2);
            var7.size.set((double)var3[0]);
            var7.opacity.set((double)var3[1]);
            var7.background.set(var4);
            var7.placed.set(true);
            float[] var13 = new float[2];
            HudGroupModule.preferredSize(var6, var5, var13);
            var7.width = Math.max(1.0F, var13[0]);
            var7.height = Math.max(1.0F, var13[1]);
            boolean var14 = !var7.laidOut;
            var7.setMembers(var6);

            for (HudModule var11 : ((Iterable<HudModule>)(Object)var6)) {
               var11.group = var7;
            }

            if (var14) {
               var7.appear();
            }

            var7.moveTo(
               clamp(var0.x(), 6.0F, var1.width - 6.0F - var7.screenWidth()),
               clamp(var0.y(), 6.0F, var1.height - 6.0F - var7.screenHeight()),
               var1.width,
               var1.height
            );
            flash(var7);
            return;
         }
      }

      for (HudModule var8 : ((Iterable<HudModule>)(Object)var6)) {
         float var9 = var8.x + (var5 ? var0.x() - firstX(var6) : 0.0F);
         float var10 = var8.y + (var5 ? 0.0F : var0.y() - firstY(var6));
         alone(var8, var3, var4, var9, var10, var1);
      }
   }

   private static float firstX(List<HudModule> var0) {
      float var1 = Float.MAX_VALUE;

      for (HudModule var3 : var0) {
         if (var3.laidOut) {
            var1 = Math.min(var1, var3.x);
         }
      }

      return var1 == Float.MAX_VALUE ? 0.0F : var1;
   }

   private static float firstY(List<HudModule> var0) {
      float var1 = Float.MAX_VALUE;

      for (HudModule var3 : var0) {
         if (var3.laidOut) {
            var1 = Math.min(var1, var3.y);
         }
      }

      return var1 == Float.MAX_VALUE ? 0.0F : var1;
   }

   private static void alone(HudModule var0, float[] var1, boolean var2, float var3, float var4, HudBounds var5) {
      var0.group = null;
      var0.size.set((double)var1[0]);
      var0.opacity.set((double)var1[1]);
      var0.background.set(var2);
      var0.placed.set(true);
      var0.moveTo(
         clamp(var3, 6.0F, var5.width - 6.0F - var0.screenWidth()), clamp(var4, 6.0F, var5.height - 6.0F - var0.screenHeight()), var5.width, var5.height
      );
      flash(var0);
   }

   static void detach(HudModule var0) {
      HudGroupModule var1 = var0.group;
      if (var1 != null) {
         HudBounds var2 = HudRegistry.layout();
         ArrayList var3 = new ArrayList<>(var1.list);
         var3.remove(var0);
         float[] var4 = new float[]{var1.size.floatValue(), var1.opacity.floatValue()};
         boolean var5 = var1.background.enabled();
         float var6 = 6.0F;
         float var7 = var1.y + var1.screenHeight() + var6;
         boolean var8 = var7 + var0.height * var4[0] <= var2.height - 6.0F;
         float var9 = var0.x;
         float var10 = var8 ? var7 : var1.y - var6 - var0.height * var4[0];
         if (var3.size() >= 2) {
            var1.setMembers(var3);
            flash(var1);
         } else {
            for (HudModule var12 : (Iterable<HudModule>)(Object)(var3)) {
               alone(var12, var4, var5, var12.x, var12.y, var2);
            }

            var1.clear();
         }

         alone(var0, var4, var5, var9, var10, var2);
         if (settings().noOverlap.enabled()) {
            float[] var13 = resolve(var0, var0.x, var0.y, var2);
            if (var13 != null) {
               var0.moveTo(var13[0], var13[1], var2.width, var2.height);
            }
         }

         SoundRequest.play(SoundEvent.COLLAPSE, 1.15F, 0.8F);
      }
   }

   private static void flash(HudModule var0) {
      flashed = var0;
      flashedAt = RenderPipelines.get().time();
   }


   private static HudLayoutEngine.LayoutPass findSeam(float var0, float var1, HudBounds var2) {
      if (elementAt(var0, var1) instanceof HudGroupModule var4) {
         boolean var5 = var4.row();
         float var6 = var5 ? var0 : var1;
         HudModule var7 = null;

         for (int var8 = 0; var8 < var4.list.size(); var8++) {
            HudModule var9 = var4.list.get(var8);
            if (var9.laidOut && !(var9.presence.value() < 0.5F)) {
               if (var7 != null) {
                  float var10 = var4.boundary(var8);
                  if (Math.abs(var6 - var10) <= 5.0F) {
                     HudLayoutEngine.LayoutPass var11 = new HudLayoutEngine.LayoutPass();
                     var11.a = var7;
                     var11.b = var9;
                     var11.group = var4;
                     var11.vertical = var5;
                     var11.x = var5 ? var10 : var4.renderX.value();
                     var11.y = var5 ? var4.renderY.value() : var10;
                     var11.length = var5 ? var4.renderHeight() : var4.renderWidth();
                     return var11;
                  }
               }

               var7 = var9;
            }
         }
      }

      List var22 = HudRegistry.elements();

      for (HudModule var24 : ((Iterable<HudModule>)(Object)var22)) {
         if (var24.arranged() && var24.mergeable() && !(var24.presence.value() < 0.5F)) {
            for (HudModule var26 : ((Iterable<HudModule>)(Object)var22)) {
               if (var26 != var24 && var26.arranged() && var26.mergeable() && !(var26.presence.value() < 0.5F)) {
                  float var27 = var24.renderX.value();
                  float var28 = var24.renderY.value();
                  float var29 = var24.renderWidth();
                  float var12 = var24.renderHeight();
                  float var13 = var26.renderX.value();
                  float var14 = var26.renderY.value();
                  float var15 = var26.renderWidth();
                  float var16 = var26.renderHeight();
                  float var17 = var13 - (var27 + var29);
                  float var18 = Math.max(var28, var14);
                  float var19 = Math.min(var28 + var12, var14 + var16);
                  if (var17 >= -1.0F && var17 <= 14.0F && var19 - var18 >= 0.5F * Math.min(var12, var16) && axisFits(var24, var26, true)) {
                     float var20 = var27 + var29 + var17 / 2.0F;
                     if (Math.abs(var0 - var20) <= 5.0F + Math.max(0.0F, var17) / 2.0F && var1 >= var18 && var1 <= var19) {
                        HudLayoutEngine.LayoutPass var34 = new HudLayoutEngine.LayoutPass();
                        var34.a = var24;
                        var34.b = var26;
                        var34.vertical = true;
                        var34.x = var20;
                        var34.y = var18;
                        var34.length = var19 - var18;
                        return var34;
                     }
                  }

                  var17 = var14 - (var28 + var12);
                  var18 = Math.max(var27, var13);
                  var19 = Math.min(var27 + var29, var13 + var15);
                  if (var17 >= -1.0F && var17 <= 14.0F && var19 - var18 >= 0.5F * Math.min(var29, var15) && axisFits(var24, var26, false)) {
                     float var33 = var28 + var12 + var17 / 2.0F;
                     if (Math.abs(var1 - var33) <= 5.0F + Math.max(0.0F, var17) / 2.0F && var0 >= var18 && var0 <= var19) {
                        HudLayoutEngine.LayoutPass var21 = new HudLayoutEngine.LayoutPass();
                        var21.a = var24;
                        var21.b = var26;
                        var21.vertical = false;
                        var21.x = var18;
                        var21.y = var33;
                        var21.length = var19 - var18;
                        return var21;
                     }
                  }
               }
            }
         }
      }

      return null;
   }

   private static boolean axisFits(HudModule var0, HudModule var1, boolean var2) {
      return !canMerge(var0, var1)
         ? false
         : !(var0 instanceof HudGroupModule var4 && var4.row() != var2) && !(var1 instanceof HudGroupModule var5 && var5.row() != var2);
   }

   private static void layoutSeam(HudLayoutEngine.LayoutPass var0, HudBounds var1) {
      Canvas var2 = RenderPipelines.get().canvas();
      int var3 = var0.buttons();
      float var4 = var3 == 2 ? 20.0F : 26.0F + var2.textWidth(Fonts.MEDIUM, "Merge", 8.0F) + 8.0F;
      float var5 = var3 * var4 + (var3 - 1) * 1 + 4.0F;
      float var6 = 18.0F;
      float var7;
      float var8;
      if (var0.vertical) {
         float var9 = var0.group != null ? var0.group.renderY.value() : Math.min(var0.a.renderY.value(), var0.b.renderY.value());
         float var10 = var0.group != null
            ? var0.group.renderY.value() + var0.group.renderHeight()
            : Math.max(var0.a.renderY.value() + var0.a.renderHeight(), var0.b.renderY.value() + var0.b.renderHeight());
         var7 = var0.x - var5 / 2.0F;
         float var11 = var9 - 5.0F - var6;
         float var12 = var10 + 5.0F;
         boolean var13 = var11 >= 4.0F && (free(var0, var7, var11, var5, var6) || !free(var0, var7, var12, var5, var6) || var12 + var6 > var1.height - 4.0F);
         var8 = var13 ? var11 : var12;
      } else {
         float var17 = var0.group != null ? var0.group.renderX.value() : Math.min(var0.a.renderX.value(), var0.b.renderX.value());
         float var20 = var0.group != null
            ? var0.group.renderX.value() + var0.group.renderWidth()
            : Math.max(var0.a.renderX.value() + var0.a.renderWidth(), var0.b.renderX.value() + var0.b.renderWidth());
         boolean var23 = (var17 + var20) / 2.0F < var1.width / 2.0F ? var20 + 5.0F + var5 <= var1.width - 4.0F : var17 - 5.0F - var5 < 4.0F;
         var7 = var23 ? var20 + 5.0F : var17 - 5.0F - var5;
         var8 = var0.y - var6 / 2.0F;
      }

      var7 = clamp(var7, 4.0F, var1.width - 4.0F - var5);
      var8 = clamp(var8, 4.0F, var1.height - 4.0F - var6);
      var0.capsule[0] = var7;
      var0.capsule[1] = var8;
      var0.capsule[2] = var5;
      var0.capsule[3] = var6;

      for (int var18 = 0; var18 < var3; var18++) {
         float[] var21 = var0.buttons[var18];
         var21[0] = var7 + 2.0F + var18 * (var4 + 1.0F);
         var21[1] = var8 + 2.0F;
         var21[2] = var4;
         var21[3] = var6 - 4.0F;
      }

      float var19 = var0.vertical ? var0.x - 5.0F - 3.0F : var0.x;
      float var22 = var0.vertical ? var0.y : var0.y - 5.0F - 3.0F;
      float var24 = var0.vertical ? 16.0F : var0.length;
      float var25 = var0.vertical ? var0.length : 16.0F;
      float var26 = Math.min(var19, var7 - 3.0F);
      float var14 = Math.min(var22, var8 - 3.0F);
      var0.zone[0] = var26;
      var0.zone[1] = var14;
      var0.zone[2] = Math.max(var19 + var24, var7 + var5 + 3.0F) - var26;
      var0.zone[3] = Math.max(var22 + var25, var8 + var6 + 3.0F) - var14;
   }

   private static boolean free(HudLayoutEngine.LayoutPass var0, float var1, float var2, float var3, float var4) {
      for (HudModule var6 : HudRegistry.elements()) {
         if (var6.arranged()
            && !(var6.presence.value() < 0.5F)
            && var6 != var0.a
            && var6 != var0.b
            && var6 != var0.group
            && overlapsRect(var6, var1, var2, var3, var4)) {
            return false;
         }
      }

      return true;
   }

   private static void updateSeam(float var0, float var1, HudBounds var2, float var3, boolean var4) {
      HudLayoutEngine.LayoutPass var5 = seam;
      if (var4) {
         seam = null;
      } else {
         if (var5 != null) {
            HudLayoutEngine.LayoutPass var6 = refresh(var5);
            if (var6 != null) {
               layoutSeam(var6, var2);
            }

            var5 = var6 != null && inside(var6.zone, var0, var1) ? var6 : null;
         }

         HudLayoutEngine.LayoutPass var8 = var5;
         if (var5 == null || !inside(var5.capsule, var0, var1)) {
            HudLayoutEngine.LayoutPass var7 = findSeam(var0, var1, var2);
            if (var7 != null) {
               layoutSeam(var7, var2);
               var8 = var7.same(var5) ? var5 : var7;
            }
         }

         if (var8 != null && !var8.same(seam)) {
            seamSince = var3;
         }

         seam = var8;
      }

      if (seam != null) {
         shownSeam = seam;
      }

      boolean var9 = seam != null && var3 - seamSince > 0.08F;
      SEAM_ALPHA.target(var9 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(RenderPipelines.get().delta()));
      if (SEAM_ALPHA.value() <= 0.01F && seam == null) {
         shownSeam = null;
      }
   }


   private static HudLayoutEngine.LayoutPass refresh(HudLayoutEngine.LayoutPass var0) {
      HudGroupModule var1 = var0.group;
      if (var1 != null) {
         if (var1.laidOut && var0.a.group == var1 && var0.b.group == var1) {
            int var2 = var1.list.indexOf(var0.b);
            if (var2 < 0) {
               return null;
            } else {
               float var3 = var1.boundary(var2);
               boolean var4 = var1.row();
               var0.vertical = var4;
               var0.x = var4 ? var3 : var1.renderX.value();
               var0.y = var4 ? var1.renderY.value() : var3;
               var0.length = var4 ? var1.renderHeight() : var1.renderWidth();
               return var0;
            }
         } else {
            return null;
         }
      } else {
         return var0.a.arranged() && var0.b.arranged() ? var0 : null;
      }
   }

   private static boolean seamClick(float var0, float var1) {
      HudLayoutEngine.LayoutPass var2 = seam;
      if (var2 != null && !(SEAM_ALPHA.value() < 0.5F) && inside(var2.capsule, var0, var1)) {
         HudGroupModule var3 = var2.group;
         if (var3 != null) {
            if (inside(var2.buttons[0], var0, var1)) {
               swap(var3, var2.a, var2.b);
               HudModule var7 = var2.a;
               var2.a = var2.b;
               var2.b = var7;
            } else if (inside(var2.buttons[1], var0, var1)) {
               split(var3, var2.b);
               seam = null;
            }

            return true;
         } else {
            HudModule var4 = var2.a;
            HudModule var5 = var2.b;
            boolean var6 = var2.vertical;
            if (var4 instanceof HudGroupModule) {
               merge(var5, var4, Integer.MAX_VALUE, var6);
            } else if (var5 instanceof HudGroupModule) {
               merge(var4, var5, 0, var6);
            } else {
               merge(var5, var4, 1, var6);
            }

            seam = null;
            return true;
         }
      } else {
         return false;
      }
   }

   private static boolean labelClick(float var0, float var1, int var2) {
      HudModule var3 = labelFor;
      if (var3 != null && !(LABEL_ALPHA.value() < 0.5F) && !(GEAR[2] <= 0.0F) && inside(LABEL, var0, var1) && var2 <= 1) {
         HudModule var4 = labelMember;
         POPOVER.open(var4 != null && var4.group == var3 ? var4 : var3);
         return true;
      } else {
         return false;
      }
   }

   private static boolean inLabelZone(float var0, float var1) {
      HudModule var2 = labelFor;
      if (var2 != null && !(LABEL_ALPHA.value() < 0.3F) && !(LABEL[2] <= 0.0F)) {
         float var3 = LABEL[0] - 2.0F;
         float var4 = LABEL[1] - 2.0F;
         float var5 = LABEL[0] + LABEL[2] + 2.0F;
         float var6 = LABEL[1] + LABEL[3] + 2.0F;
         if (var0 >= var3 && var0 <= var5 && var1 >= var4 && var1 <= var6) {
            return true;
         } else {
            float var7 = var2.renderX.value() - 3.0F;
            float var8 = var2.renderY.value() - 3.0F;
            float var9 = var7 + var2.renderWidth() + 6.0F;
            float var10 = var8 + var2.renderHeight() + 6.0F;

            return switch (labelPlace) {
               case -1 -> var0 >= var5 - 2.0F && var0 <= var7 + 2.0F && var1 >= var4 && var1 <= var6;
               default -> var1 >= var6 - 2.0F && var1 <= var8 + 2.0F && var0 >= var3 && var0 <= var5;
               case 1 -> var0 >= var9 - 2.0F && var0 <= var3 + 2.0F && var1 >= var4 && var1 <= var6;
               case 2 -> var1 >= var10 - 2.0F && var1 <= var4 + 2.0F && var0 >= var3 && var0 <= var5;
            };
         }
      } else {
         return false;
      }
   }

   static void renderBackdrop(Canvas var0, HudBounds var1) {
      float var2 = APPEAR.value() * hudShown();
      if (inScreen && !(var2 <= 0.01F)) {
         var0.pushAlpha(var2);
         var0.rect(0.0F, 0.0F, var1.width, var1.height, 1493172224);
         var0.rect(var1.width / 2.0F - 0.5F, 0.0F, 1.0F, var1.height, 285212671);
         var0.rect(0.0F, var1.height / 2.0F - 0.5F, var1.width, 1.0F, 285212671);
         var0.popAlpha();
      }
   }

   public static void renderOverlay(class_332 var0, class_437 var1) {
      RenderPipelines var2 = RenderPipelines.get();
      Canvas var3 = var2.canvas();
      float var4 = var2.delta();
      float var5 = var2.time();
      boolean var6 = APPEAR.value() <= 0.01F;
      float var7 = APPEAR.target(active() ? 1.0F : 0.0F).update(MotionStyle.MENU.dt(var4));
      if (!(var7 <= 0.01F)) {
         HudBounds var8 = HudRegistry.layout();
         float var9 = var2.hudCanvas().pixelScale() / var3.pixelScale();
         float var10 = pointerX();
         float var11 = pointerY();
         boolean var12 = POPOVER.visible() || OVERVIEW.visible();
         settleGroup(var8);
         if (dragging != null) {
            hovered = dragging;
         } else if (var12) {
            hovered = null;
         } else {
            HudModule var13 = elementAt(var10, var11);
            if (var13 == null && inLabelZone(var10, var11)) {
               var13 = labelFor;
            }

            hovered = var13;
         }

         HudModule var16 = hovered;
         if (!(var16 instanceof HudGroupModule) || dragging != null) {
            hoveredMember = null;
         } else if (var10 >= var16.x - 3.0F
            && var10 <= var16.x + var16.screenWidth() + 3.0F
            && var11 >= var16.y - 3.0F
            && var11 <= var16.y + var16.screenHeight() + 3.0F) {
            hoveredMember = memberAt(var16, var10, var11);
         }

         if (hovered != null) {
            lastHoverAt = var5;
         }

         updateSeam(var10, var11, var8, var5, var12 || dragging != null || pressed != null);
         float var14 = MERGE.target(mergeTarget != null && dragging != null ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var4));
         var3.push();
         var3.scale(var9);
         var3.pushAlpha(var7);
         float var15 = hudShown();
         var3.pushAlpha(var15);
         outlines(var3, var4, var5, var14);
         drawGuides(var3, var5);
         seamControls(var3, var4);
         label(var3, var8, var4, var5, var10, var11);
         hint(var3, var1, var8, var4, var5, var6);
         var3.popAlpha();
         OVERVIEW.render(var3, var8, var10, var11, var4, var5, POPOVER.visible());
         POPOVER.render(var3, var8, var10, var11, var4, var5);
         var3.popAlpha();
         var3.pop();
         if (dragging != null) {
            var0.method_74037(class_11876.field_62458);
         } else if (POPOVER.hand() || OVERVIEW.hand()) {
            var0.method_74037(class_11876.field_62455);
         } else if (!var12 && (hovered != null || seam != null && inside(seam.capsule, var10, var11))) {
            var0.method_74037(class_11876.field_62455);
         }
      }
   }

   private static void settleGroup(HudBounds var0) {
      HudModule var1 = settle;
      if (var1 != null && var1.laidOut) {
         settle = null;
         if (settings().noOverlap.enabled()) {
            float[] var2 = resolve(var1, var1.x, var1.y, var0);
            if (var2 != null) {
               var1.moveTo(var2[0], var2[1], var0.width, var0.height);
            }
         }
      }
   }

   private static void outlines(Canvas var0, float var1, float var2, float var3) {
      float var4 = Math.max(0.0F, 1.0F - (var2 - scaledAt) / 0.6F);
      float var5 = Math.max(0.0F, 1.0F - (var2 - flashedAt) / 0.7F);
      float[] var6 = new float[4];

      for (HudModule var8 : HudRegistry.elements()) {
         if (var8.arranged() && !(var8.presence.value() < 0.3F)) {
            Animator var9 = HIGHLIGHT.computeIfAbsent(var8, var0x -> Animator.smooth(0.0F));
            boolean var10 = var8 == mergeTarget && dragging != null;
            float var11 = var9.target(var8 != hovered && !var10 ? 0.0F : 1.0F).update(MotionStyle.HOVER.dt(var1));
            float var12 = (var8 == scaled ? var4 : 0.0F) + (var8 == flashed ? var5 : 0.0F);
            float var13 = var8.renderX.value() - 3.0F;
            float var14 = var8.renderY.value() - 3.0F;
            float var15 = var8.renderWidth() + 6.0F;
            float var16 = var8.renderHeight() + 6.0F;
            float var17 = Math.min(var8.cornerRadius() * var8.renderScale.value() + 3.0F, Math.min(var15, var16) / 2.0F);
            if (var8 == dragging) {
               var0.shadow(var13, var14, var15, var16, var17, 26.0F, 0.0F, 0.0F, 8.0F, BlendMode.withAlpha(-16777216, 0.33F * (1.0F - var3)));
            }

            float var18 = Math.min(1.0F, 0.3F * var11 + 0.45F * var12 + (var10 ? 0.22F * var3 : 0.0F));
            if (var18 > 0.01F) {
               var0.glow(var13, var14, var15, var16, var17, 18.0F, BlendMode.withAlpha(var10 ? Icons.MAGENTA : Icons.VIOLET, var18));
               var0.roundRect(var13, var14, var15, var16, var17, BlendMode.withAlpha(Icons.VIOLET, 0.07F * var11));
            }

            if (var8 instanceof HudGroupModule var19 && var8 == hovered && hoveredMember != null && dragging == null) {
               int var20 = var19.list.indexOf(hoveredMember);
               if (var20 >= 0) {
                  var19.slotRect(var20, var6);
                  float var21 = 1.5F;
                  float var22 = Math.min(var17 - 3.0F, Math.min(var6[2], var6[3]) / 2.0F);
                  var0.roundRect(
                     var6[0] + var21,
                     var6[1] + var21,
                     var6[2] - var21 * 2.0F,
                     var6[3] - var21 * 2.0F,
                     Math.max(2.0F, var22 - var21),
                     BlendMode.withAlpha(-1, 0.06F * var11)
                  );
               }
            }

            int var23 = BlendMode.withAlpha(var8 == dragging ? Icons.MAGENTA : Icons.PURPLE, 0.95F);
            int var24 = var8 instanceof HudGroupModule ? Theme.tint(784967935) : 654311423;
            var0.roundRectOutline(
               var13, var14, var15, var16, var17, 1.0F, BlendMode.lerp(BlendMode.lerp(var24, var23, var11), Icons.MAGENTA, Math.min(1.0F, 0.6F * var12))
            );
         }
      }

      caret(var0, var1, var3);
   }

   private static void caret(Canvas var0, float var1, float var2) {
      HudModule var3 = mergeTarget;
      if (var3 != null && !(var2 <= 0.01F) && dragging != null) {
         float var4 = var3.renderX.value();
         float var5 = var3.renderY.value();
         float var6 = var3.renderWidth();
         float var7 = var3.renderHeight();
         boolean var8 = mergeRow;
         float var9;
         if (!(var3 instanceof HudGroupModule var10)) {
            var9 = mergeIndex == 0 ? (var8 ? var4 : var5) : (var8 ? var4 + var6 : var5 + var7);
         } else {
            var9 = mergeIndex < var10.list.size() && var10.list.get(mergeIndex).laidOut ? var10.boundary(mergeIndex) : (var8 ? var4 + var6 : var5 + var7);
         }

         float var15 = var8 ? var9 : var4 + var6 / 2.0F;
         float var11 = var8 ? var5 + var7 / 2.0F : var9;
         if (caretFor != var3) {
            CARET_X.snap(var15);
            CARET_Y.snap(var11);
            caretFor = var3;
         }

         var15 = CARET_X.target(var15).update(var1);
         var11 = CARET_Y.target(var11).update(var1);
         float var12 = (var8 ? var7 : var6) + 8.0F;
         float var13 = 2.0F;
         float var14 = 0.85F + 0.15F * (float)Math.sin(RenderPipelines.get().time() * 7.0F);
         var0.pushAlpha(var2);
         if (var8) {
            var0.glow(var15 - var13 / 2.0F, var11 - var12 / 2.0F, var13, var12, var13 / 2.0F, 10.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.7F * var14));
            var0.roundRect(var15 - var13 / 2.0F, var11 - var12 / 2.0F, var13, var12, var13 / 2.0F, -1);
         } else {
            var0.glow(var15 - var12 / 2.0F, var11 - var13 / 2.0F, var12, var13, var13 / 2.0F, 10.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.7F * var14));
            var0.roundRect(var15 - var12 / 2.0F, var11 - var13 / 2.0F, var12, var13, var13 / 2.0F, -1);
         }

         var0.popAlpha();
      } else {
         caretFor = null;
      }
   }

   private static void seamControls(Canvas var0, float var1) {
      HudLayoutEngine.LayoutPass var2 = shownSeam;
      float var3 = SEAM_ALPHA.value();
      if (var2 != null && !(var3 <= 0.01F)) {
         float var4 = pointerX();
         float var5 = pointerY();
         var0.pushAlpha(var3);
         float var6 = 1.5F;
         if (var2.vertical) {
            var0.glow(var2.x - var6 / 2.0F, var2.y + 2.0F, var6, var2.length - 4.0F, var6 / 2.0F, 8.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.55F));
            var0.roundRect(var2.x - var6 / 2.0F, var2.y + 2.0F, var6, var2.length - 4.0F, var6 / 2.0F, BlendMode.withAlpha(-1, 0.9F));
         } else {
            var0.glow(var2.x + 2.0F, var2.y - var6 / 2.0F, var2.length - 4.0F, var6, var6 / 2.0F, 8.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.55F));
            var0.roundRect(var2.x + 2.0F, var2.y - var6 / 2.0F, var2.length - 4.0F, var6, var6 / 2.0F, BlendMode.withAlpha(-1, 0.9F));
         }

         float[] var7 = var2.capsule;
         float var8 = (1.0F - Easing.EMPHASIZED.apply(var3)) * 4.0F;
         var0.push();
         var0.translate(0.0F, var2.vertical ? (var7[1] < var2.y ? var8 : -var8) : 0.0F);
         var0.shadow(var7[0], var7[1], var7[2], var7[3], var7[3] / 2.0F, 14.0F, 0.0F, 0.0F, 4.0F, 1711276032);
         var0.roundRect(var7[0], var7[1], var7[2], var7[3], var7[3] / 2.0F, Theme.tint(-183234522));
         var0.roundRectOutline(var7[0], var7[1], var7[2], var7[3], var7[3] / 2.0F, 1.0F, BlendMode.withAlpha(Icons.PURPLE, 0.55F));
         int var9 = var2.buttons();

         for (int var10 = 0; var10 < var9; var10++) {
            float[] var11 = var2.buttons[var10];
            boolean var12 = inside(var11, var4, var5);
            float var13 = var2.hover[var10].target(var12 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1));
            if (var13 > 0.01F) {
               var0.roundRect(
                  var11[0],
                  var11[1],
                  var11[2],
                  var11[3],
                  var11[3] / 2.0F,
                  BlendMode.withAlpha(var10 == 1 && var2.group != null ? Icons.PINK : Icons.VIOLET, 0.35F * var13)
               );
            }

            int var14 = BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var13);
            float var15 = var11[1] + var11[3] / 2.0F;
            if (var2.group == null) {
               var0.icon("link", var11[0] + 6.0F + 5.0F, var15, 9.5F, BlendMode.lerp(Icons.PURPLE, Icons.TEXT, var13));
               var0.text(Fonts.MEDIUM, "Merge", var11[0] + 6.0F + 10.0F + 4.0F, var15, 8.0F, var14, FontStyle.LEFT, FontWeight.MIDDLE);
            } else {
               String var16 = var10 == 0 ? (var2.vertical ? "arrow-left-right" : "arrow-up-down") : "unlink";
               var0.icon(var16, var11[0] + var11[2] / 2.0F, var15, 9.5F, var14);
            }

            if (var10 > 0) {
               var0.rect(var11[0] - 1.0F, var7[1] + 4.0F, 1.0F, var7[3] - 8.0F, 452984831);
            }
         }

         var0.pop();
         var0.popAlpha();
      }
   }

   private static void label(Canvas var0, HudBounds var1, float var2, float var3, float var4, float var5) {
      HudModule var6 = mergeTarget != null && dragging != null ? mergeTarget : hovered;
      boolean var7 = var6 != null && (seam == null || var6 == mergeTarget);
      float var8 = LABEL_ALPHA.target(var7 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var2));
      if (var7 && var6 != labelFor) {
         boolean var9 = labelFor != null && var8 > 0.05F;
         labelFor = var6;
         labelChoice = 0;
         if (var9) {
            float[] var10 = labelBase(var6, LABEL_W.value(), var1);
            LABEL_DX.snap(labelX - var10[0]);
            LABEL_DY.snap(labelY - var10[1]);
            labelSwitchedAt = var3;
         } else {
            LABEL_DX.snap(0.0F);
            LABEL_DY.snap(0.0F);
            LABEL_W.snap(labelWidth(var0, var6));
            labelSwitchedAt = -10.0F;
         }
      }

      HudModule var28 = labelFor;
      if (var28 == null) {
         LABEL[2] = 0.0F;
      } else if (var8 <= 0.01F && !var7) {
         labelFor = null;
         LABEL[2] = 0.0F;
      } else {
         if (var7 && var28 == hovered && hoveredMember != null) {
            labelMember = hoveredMember;
         } else if (!(var28 instanceof HudGroupModule)) {
            labelMember = null;
         }

         float var29 = LABEL_W.target(labelWidth(var0, var28)).update(var2);
         float[] var11 = labelBase(var28, var29, var1);
         float var12 = var11[0] + LABEL_DX.target(0.0F).update(var2);
         float var13 = var11[1] + LABEL_DY.target(0.0F).update(var2);
         labelX = var12;
         labelY = var13;
         labelPlace = (int)var11[2];
         LABEL[0] = var12;
         LABEL[1] = var13;
         LABEL[2] = var29;
         LABEL[3] = 18.0F;
         float var14 = (1.0F - var8) * 5.0F;
         switch ((int)var11[2]) {
            case -1:
               var12 += var14;
               break;
            case 0:
            default:
               var13 += var14;
               break;
            case 1:
               var12 -= var14;
               break;
            case 2:
               var13 -= var14;
         }

         float var15 = SIZE_POP.update(var2);
         float var16 = Math.min(1.0F, 0.2F + (var3 - labelSwitchedAt) / 0.18F);
         float var17 = var28 == scaled ? Math.max(0.0F, 1.0F - (var3 - scaledAt) / 0.5F) : 0.0F;
         boolean var18 = var28 == mergeTarget && dragging != null;
         String var19 = labelName(var28);
         int var20 = Math.round(var28.scale() * 100.0F);
         String var21 = var20 + "%";
         float var22 = var0.textWidth(Fonts.SEMIBOLD, var19, 9.0F);
         float var23 = var0.textWidth(Fonts.SEMIBOLD, var21, 8.5F);
         float var24 = var13 + 9.0F;
         var0.pushAlpha(var8);
         var0.shadow(var12, var13, var29, 18.0F, 9.0F, 14.0F, 0.0F, 0.0F, 4.0F, 1711276032);
         var0.roundRect(var12, var13, var29, 18.0F, 9.0F, Theme.tint(-183234522));
         var0.roundRectOutline(
            var12, var13, var29, 18.0F, 9.0F, 1.0F, BlendMode.lerp(654311423, BlendMode.withAlpha(Icons.MAGENTA, 0.6F), Math.max(var17, var18 ? 1.0F : 0.0F))
         );
         var0.pushClip(var12, var13, var29, 18.0F, 9.0F);
         var0.pushAlpha(var16);
         float var25 = var12 + 9.0F;
         if (var18) {
            var0.icon("link", var25 + 4.5F, var24, 9.0F, Icons.MAGENTA);
            var25 += 13.0F;
         } else if (var28 instanceof HudGroupModule) {
            var0.icon("link", var25 + 4.0F, var24, 8.0F, Icons.PURPLE);
            var25 += 12.0F;
         }

         var0.text(Fonts.SEMIBOLD, var19, var25, var24, 9.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
         if (!var18) {
            var0.push();
            var0.translate(var25 + var22 + 6.0F + var23 / 2.0F, var24);
            var0.scale(1.0F + 0.16F * var15);
            var0.text(Fonts.SEMIBOLD, var21, 0.0F, 0.0F, 8.5F, BlendMode.lerp(Icons.PURPLE, Icons.TEXT, var17), FontStyle.CENTER, FontWeight.MIDDLE);
            var0.pop();
            float var26 = var12 + var29 - 6.0F - 6.5F;
            GEAR[0] = var26 - 7.5F;
            GEAR[1] = var13;
            GEAR[2] = 15.0F;
            GEAR[3] = 18.0F;
            float var27 = GEAR_HOVER.target(inside(LABEL, var4, var5) ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var2));
            var0.rect(var26 - 9.5F, var13 + 4.5F, 1.0F, 9.0F, 536870911);
            if (var27 > 0.01F) {
               var0.circle(var26, var24, 6.5F, BlendMode.withAlpha(Icons.VIOLET, 0.4F * var27));
            }

            var0.icon("settings-2", var26, var24, 9.0F, BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var27));
         } else {
            GEAR[2] = 0.0F;
         }

         var0.popAlpha();
         var0.popClip();
         var0.popAlpha();
      }
   }

   private static String labelName(HudModule var0) {
      if (var0 == mergeTarget && dragging != null) {
         return var0 instanceof HudGroupModule ? "Add to group" : Translations.tr("Merge with") + " " + Translations.tr(var0.name());
      } else if (var0 instanceof HudGroupModule var1) {
         HudModule var2 = labelMember;
         if (var2 != null && var1.list.contains(var2)) {
            return var2.name();
         } else {
            return var1.list.isEmpty() ? var0.name() : var1.list.getFirst().name();
         }
      } else {
         return var0.name();
      }
   }

   private static float labelWidth(Canvas var0, HudModule var1) {
      String var2 = labelName(var1);
      boolean var3 = var1 == mergeTarget && dragging != null;
      float var4 = var3 ? 13.0F : (var1 instanceof HudGroupModule ? 12.0F : 0.0F);
      if (var3) {
         return 9.0F + var4 + var0.textWidth(Fonts.SEMIBOLD, var2, 9.0F) + 10.0F;
      } else {
         float var5 = var1.scale();
         String var6 = Math.round(var5 * 100.0F) + "%";
         return 9.0F
            + var4
            + var0.textWidth(Fonts.SEMIBOLD, var2, 9.0F)
            + 6.0F
            + var0.textWidth(Fonts.SEMIBOLD, var6, 8.5F)
            + 8.0F
            + 1.0F
            + 6.0F
            + 13.0F
            + 6.0F;
      }
   }

   private static float[] labelBase(HudModule var0, float var1, HudBounds var2) {
      float var3 = var0.renderX.value();
      float var4 = var0.renderY.value();
      float var5 = var0.renderWidth();
      float var6 = var0.renderHeight();
      float var7 = var3 + var5 / 2.0F;
      if (labelChoice == 0 || !labelFits(labelRect(labelChoice, var3, var4, var5, var6, var1, var2), var2)) {
         boolean var8 = Math.abs(var7 - var2.width / 2.0F) < 24.0F ? labelSide > 0 : var7 < var2.width / 2.0F;
         int[] var9 = var8 ? new int[]{1, -1, 2, -2} : new int[]{-1, 1, 2, -2};
         int var10 = 0;

         for (int var14 : var9) {
            float[] var15 = labelRect(var14, var3, var4, var5, var6, var1, var2);
            if (labelFits(var15, var2)) {
               if (var10 == 0) {
                  var10 = var14;
               }

               if (labelClear(var15, var0)) {
                  var10 = var14;
                  break;
               }
            }
         }

         labelChoice = var10 != 0 ? var10 : (var4 - 9.0F - 18.0F < 4.0F ? 2 : -2);
         if (Math.abs(labelChoice) == 1) {
            labelSide = labelChoice;
         }
      }

      float[] var16 = labelRect(labelChoice, var3, var4, var5, var6, var1, var2);
      return new float[]{var16[0], var16[1], labelChoice};
   }

   private static float[] labelRect(int var0, float var1, float var2, float var3, float var4, float var5, HudBounds var6) {
      float var7 = 9.0F;
      float var8 = clamp(var2 + var4 / 2.0F - 9.0F, 4.0F, var6.height - 18.0F - 4.0F);
      float var9 = clamp(var1 + var3 / 2.0F - var5 / 2.0F, 4.0F, var6.width - var5 - 4.0F);

      return switch (var0) {
         case -1 -> new float[]{var1 - var7 - var5, var8, var5};
         default -> new float[]{var9, var2 - var7 - 18.0F, var5};
         case 1 -> new float[]{var1 + var3 + var7, var8, var5};
         case 2 -> new float[]{var9, var2 + var4 + var7, var5};
      };
   }

   private static boolean labelFits(float[] var0, HudBounds var1) {
      return var0[0] >= 4.0F && var0[0] + var0[2] <= var1.width - 4.0F && var0[1] >= 4.0F && var0[1] + 18.0F <= var1.height - 4.0F;
   }

   private static boolean labelClear(float[] var0, HudModule var1) {
      for (HudModule var3 : HudRegistry.elements()) {
         if (var3 != var1
            && var3.arranged()
            && !(var3.presence.value() < 0.5F)
            && var3.renderX.value() < var0[0] + var0[2] + 2.0F
            && var3.renderX.value() + var3.renderWidth() + 2.0F > var0[0]
            && var3.renderY.value() < var0[1] + 18.0F + 2.0F
            && var3.renderY.value() + var3.renderHeight() + 2.0F > var0[1]) {
            return false;
         }
      }

      return true;
   }

   private static void drawGuides(Canvas var0, float var1) {
      float var2 = dragging != null ? 1.0F : 1.0F - Math.min(1.0F, Math.max(0.0F, (var1 - guideFade - 0.5F) / 0.35F));
      if (!(var2 <= 0.01F)) {
         for (HudLayoutEngine.AxisBand var4 : guides) {
            int var5 = BlendMode.withAlpha(var4.screenCenter() ? Icons.MAGENTA : Icons.PURPLE, 0.9F * var2);
            if (var4.vertical()) {
               var0.glow(var4.position() - 0.5F, var4.from(), 1.0F, var4.to() - var4.from(), 0.5F, 6.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.35F * var2));
               var0.rect(var4.position() - 0.5F, var4.from(), 1.0F, var4.to() - var4.from(), var5);
            } else {
               var0.glow(var4.from(), var4.position() - 0.5F, var4.to() - var4.from(), 1.0F, 0.5F, 6.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.35F * var2));
               var0.rect(var4.from(), var4.position() - 0.5F, var4.to() - var4.from(), 1.0F, var5);
            }
         }
      }
   }

   private static void hint(Canvas var0, class_437 var1, HudBounds var2, float var3, float var4, boolean var5) {
      float var6 = inChat
         ? RenderPipelines.get().hudFromGui(var1.field_22790 - 14) - 8.0F
         : (var2.level != null ? RenderPipelines.get().hudFromGui(var1.field_22790 - 50) - 6.0F : var2.height - 14.0F);
      String var7 = hintText(var4);
      if (!var7.equals(hint)) {
         previousHint = var5 ? "" : hint;
         hint = var7;
         hintChangedAt = var5 ? -10.0F : var4;
      }

      float var8 = var0.textWidth(Fonts.MEDIUM, hint, 7.5F) + 20.0F;
      float var9 = var5 ? HINT_W.snap(var8).value() : HINT_W.target(var8).update(var3);
      float var10 = inChat ? var2.width - 6.0F - var9 : var2.width / 2.0F - var9 / 2.0F;
      if (dragging == null || var5) {
         HINT_Y.target(avoid(var10, var6 - 16.0F, var9));
      }

      if (var5) {
         HINT_Y.snap(HINT_Y.target());
      }

      float var11 = HINT_Y.update(var3);
      boolean var12 = dragging != null && overlapsRect(dragging, var10, var11, var9, 16.0F);
      float var13 = HINT_FADE.target(var12 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var3));
      float var14 = Easing.STANDARD.apply(Math.max(0.0F, Math.min(1.0F, (var4 - hintChangedAt) / 0.22F)));
      float var15 = var11 + 8.0F;
      var0.pushAlpha(1.0F - 0.8F * var13);
      var0.roundRect(var10, var11, var9, 16.0F, 8.0F, Theme.tint(-703525600));
      var0.roundRectOutline(var10, var11, var9, 16.0F, 8.0F, 1.0F, 452984831);
      var0.pushClip(var10, var11, var9, 16.0F, 8.0F);
      if (var14 < 1.0F && !previousHint.isEmpty()) {
         var0.pushAlpha(1.0F - var14);
         var0.text(Fonts.MEDIUM, previousHint, var10 + var9 / 2.0F, var15 - 5.0F * var14, 7.5F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
         var0.popAlpha();
      }

      var0.pushAlpha(var14);
      var0.text(Fonts.MEDIUM, hint, var10 + var9 / 2.0F, var15 + 5.0F * (1.0F - var14), 7.5F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
      var0.popAlpha();
      var0.popClip();
      var0.popAlpha();
   }

   private static float avoid(float var0, float var1, float var2) {
      float var3 = var1;

      for (int var4 = 0; var4 < 12; var4++) {
         HudModule var5 = null;

         for (HudModule var7 : HudRegistry.elements()) {
            if (var7.arranged() && var7.presence.value() > 0.5F && overlapsRect(var7, var0, var1, var2, 16.0F)) {
               var5 = var7;
               break;
            }
         }

         if (var5 == null) {
            return var1;
         }

         var1 = var5.y - 6.0F - 16.0F;
         if (var1 < 6.0F) {
            return var3;
         }
      }

      return var3;
   }

   private static String hintText(float var0) {
      float var1 = pointerX();
      float var2 = pointerY();
      if (POPOVER.active()) {
         return "Changes show at once  ·  Esc or a click outside closes this";
      } else {
         if (OVERVIEW.active()) {
            String var3 = OVERVIEW.hint(var1, var2);
            if (var3 != null) {
               return var3;
            }
         }

         if (dragging == null || !moved) {
            HudLayoutEngine.LayoutPass var8 = seam;
            if (var8 != null && SEAM_ALPHA.value() > 0.5F) {
               if (var8.group == null) {
                  return "Merge these two into one panel";
               } else if (inside(var8.buttons[1], var1, var2)) {
                  return "Split them apart here";
               } else {
                  return var8.vertical ? "Swap these two  ·  or split them apart here" : "Swap these two  ·  or split them apart here";
               }
            } else if (labelFor != null && GEAR[2] > 0.0F && inside(LABEL, var1, var2)) {
               return "Its settings, right here";
            } else {
               HudModule var4 = hovered;
               if (dragging == null && var4 != null) {
                  HudModule var5 = hoveredMember;
                  HudModule var6 = var5 != null ? var5 : var4;
                  String var7 = var6.editorHint(var6.localX(var1), var6.localY(var2));
                  if (var7 != null) {
                     return var7;
                  } else if (var4 instanceof HudGroupModule) {
                     return "Move along it to swap or split its parts  ·  Right-click a part for its settings";
                  } else {
                     return var4.mergeable()
                        ? "Scroll to resize  ·  Drag onto another to merge  ·  Right-click for settings"
                        : "Scroll to resize  ·  Double-click to centre  ·  Right-click for settings";
                  }
               } else {
                  return dragging == null && !(var0 - lastHoverAt < 0.35F)
                     ? "Drag an element to move it  ·  Right-click an empty spot for every element"
                     : "Scroll to resize  ·  Double-click to centre  ·  Right-click for settings";
               }
            }
         } else if (mergeTarget != null) {
            return "Release to merge them into one  ·  Hold Alt to place it freely";
         } else {
            return dragging.mergeable()
               ? "Drop it onto another element to merge them"
               : (settings().snap.enabled() ? "Hold Alt to place it freely" : "Release to drop it here");
         }
      }
   }

   private static boolean inside(float[] var0, float var1, float var2) {
      return var0[2] > 0.0F && var1 >= var0[0] && var1 <= var0[0] + var0[2] && var2 >= var0[1] && var2 <= var0[1] + var0[3];
   }

   private static boolean overlapsRect(HudModule var0, float var1, float var2, float var3, float var4) {
      float var5 = 4.0F;
      return var0.x < var1 + var3 + var5
         && var0.x + var0.screenWidth() + var5 > var1
         && var0.y < var2 + var4 + var5
         && var0.y + var0.screenHeight() + var5 > var2;
   }

   private static boolean sameGuides(List<HudLayoutEngine.AxisBand> var0, List<HudLayoutEngine.AxisBand> var1) {
      if (var0.size() != var1.size()) {
         return false;
      } else {
         for (int var2 = 0; var2 < var0.size(); var2++) {
            if (((HudLayoutEngine.AxisBand)var0.get(var2)).vertical() != ((HudLayoutEngine.AxisBand)var1.get(var2)).vertical()
               || Math.abs(((HudLayoutEngine.AxisBand)var0.get(var2)).position() - ((HudLayoutEngine.AxisBand)var1.get(var2)).position()) > 0.01F) {
               return false;
            }
         }

         return true;
      }
   }

   private static float clamp(float var0, float var1, float var2) {
      return var2 < var1 ? (var1 + var2) / 2.0F : Math.max(var1, Math.min(var2, var0));
   }

   public static float pointerX() {
      return Float.isNaN(devX) ? RenderPipelines.get().hudMouseX() : devX;
   }

   public static float pointerY() {
      return Float.isNaN(devY) ? RenderPipelines.get().hudMouseY() : devY;
   }

   public static void devPointer(float var0, float var1) {
      devX = var0;
      devY = var1;
   }

   public static void dev(String var0) {
      String[] var1 = var0.split(";");
      HashMap var2 = new HashMap();

      for (HudModule var4 : HudRegistry.elements()) {
         var2.put(var4.id(), var4);
      }

      HudModule var14 = var1.length > 1 ? (HudModule)var2.get(var1[1]) : null;
      String var15 = var1[0];
      switch (var15) {
         case "merge":
            HudModule var6 = var1.length > 2 ? (HudModule)var2.get(var1[2]) : null;
            if (var14 != null && var6 != null) {
               Object var16 = var14.group != null ? var14.group : var14;
               Object var17 = var6.group != null ? var6.group : var6;
               merge(
                  (HudModule)var16,
                  (HudModule)var17,
                  var1.length > 3 ? Integer.parseInt(var1[3]) : Integer.MAX_VALUE,
                  var1.length <= 4 || !var1[4].equals("column")
               );
            }
            break;
         case "split":
            if (var14 != null && var14.group != null) {
               split(var14.group, var14);
            }
            break;
         case "swap":
            HudModule var7 = var1.length > 2 ? (HudModule)var2.get(var1[2]) : null;
            if (var14 != null && var7 != null && var14.group != null && var14.group == var7.group) {
               swap(var14.group, var14, var7);
            }
            break;
         case "detach":
            if (var14 != null) {
               detach(var14);
            }
            break;
         case "settings":
            Module var8 = var1.length > 1 ? SettingSection.byId(var1[1]) : null;
            if (var14 != null) {
               POPOVER.open(var14);
            } else if (var8 != null) {
               HudBounds var19 = HudRegistry.layout();
               POPOVER.openAt(var8, (HudModule)null, (HudModule)null, var19.width / 2.0F - 60.0F, var19.height / 2.0F - 40.0F, 120.0F, 80.0F);
            }
            break;
         case "overview":
            if (var1.length > 1 && var1[1].equals("close")) {
               OVERVIEW.close();
            } else if (var1.length > 2 && var1[1].equals("hold")) {
               OVERVIEW.hold(Float.parseFloat(var1[2]), -1.0F);
            } else if (var1.length > 2 && var1[1].equals("holdout")) {
               OVERVIEW.hold(-1.0F, Float.parseFloat(var1[2]));
            } else if (var1.length > 2 && var1[1].equals("scroll")) {
               OVERVIEW.scrollBy(Double.parseDouble(var1[2]));
            } else {
               HudBounds var18 = HudRegistry.layout();
               OVERVIEW.open(
                  var1.length > 3 ? Float.parseFloat(var1[2]) : var18.width / 2.0F, var1.length > 3 ? Float.parseFloat(var1[3]) : var18.height / 2.0F
               );
            }
            break;
         case "seam":
            HudLayoutEngine.LayoutPass var9 = seam;
            int var10 = var1.length > 1 ? Integer.parseInt(var1[1]) : 0;
            if (var9 != null && var10 < var9.buttons()) {
               float[] var20 = var9.buttons[var10];
               float var21 = var20[0] + var20[2] / 2.0F;
               float var13 = var20[1] + var20[3] / 2.0F;
               SEAM_ALPHA.snap(1.0F);
               devPointer(var21, var13);
               mouseClicked(var21, var13, 0);
               mouseReleased(var21, var13);
            }
            break;
         case "close":
            POPOVER.close();
            OVERVIEW.close();
            break;
         case "esc":
            keyPressed(256);
            break;
         default:
            for (HudGroupModule var12 : HudRegistry.groups()) {
               if (!var12.free()) {
                  ClientServices.LOGGER
                     .info(
                        "HUD group {} {} [{}] at {},{} size {}",
                        new Object[]{var12.id(), var12.axis.get(), var12.members.get(), var12.x, var12.y, var12.scale()}
                     );
               }
            }
      }
   }

   public static String describe(HudModule var0) {
      return String.format(
         Locale.ROOT, "%s at %.1f,%.1f size %.2f%s", var0.id(), var0.x, var0.y, var0.scale(), var0.group != null ? " in " + var0.group.id() : ""
      );
   }

   private record GroupLayout(List<HudModule> members, float x, float y) {
   }

   private record AxisBand(boolean vertical, float position, float from, float to, boolean screenCenter) {
   }

   private static final class LayoutPass {
      HudModule a;
      HudModule b;

      HudGroupModule group;
      boolean vertical;
      float x;
      float y;
      float length;
      final float[] zone = new float[4];
      final float[] capsule = new float[4];
      final float[][] buttons = new float[2][4];
      final Animator[] hover = new Animator[]{Animator.smooth(0.0F), Animator.smooth(0.0F)};

      boolean same(HudLayoutEngine.LayoutPass var1) {
         return var1 != null && var1.a == this.a && var1.b == this.b && var1.group == this.group;
      }

      int buttons() {
         return this.group != null ? 2 : 1;
      }
   }
}
