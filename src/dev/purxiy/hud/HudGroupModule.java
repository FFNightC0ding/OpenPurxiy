package dev.purxiy.hud;

import dev.purxiy.anim.Animator;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.TextSetting;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class HudGroupModule extends HudModule {
   static final int SLOTS = 8;
   static final String ROW = "Row";
   static final String COLUMN = "Column";
   final TextSetting members;
   final ModeSetting axis;
   final List<HudModule> list = new ArrayList<>();
   private float[] offsetX = new float[4];
   private float[] offsetY = new float[4];
   private float[] slotStart = new float[4];
   private float[] slotLength = new float[4];
   private float sideChoice = -1.0F;
   private final Animator align = Animator.smooth(0.0F);

   HudGroupModule(int var1) {
      super("hud_group_" + var1, "Group", "Elements merged into one panel", "link", 0.0, 0.0, SettingSection.HUD, "group" + var1 + "_", 0.6);
      this.members = SettingSection.HUD.internal(new TextSetting("group" + var1 + "_members", "Members", (String)null, "", 512));
      this.axis = SettingSection.HUD.internal(new ModeSetting("group" + var1 + "_axis", "Direction", (String)null, "Row", List.of("Row", "Column")));
      this.size.visibleWhen(() -> false);
      this.background.visibleWhen(() -> false);
      this.opacity.visibleWhen(() -> false);
   }

   boolean row() {
      return this.axis.is("Row");
   }

   boolean free() {
      return this.members.get().isEmpty();
   }

   void resolve(Map<String, HudModule> var1, Set<HudModule> var2) {
      this.list.clear();
      String var3 = this.members.get();
      if (!var3.isEmpty()) {
         for (String var7 : var3.split(",")) {
            HudModule var8 = (HudModule)var1.get(var7.trim());
            if (var8 != null && var8.mergeable() && var2.add(var8)) {
               this.list.add(var8);
            }
         }

         this.ensure(this.list.size());
      }
   }

   void setMembers(List<HudModule> var1) {
      List var2 = List.copyOf(var1);
      StringBuilder var3 = new StringBuilder();

      for (HudModule var5 : ((Iterable<HudModule>)(Object)var2)) {
         if (!var3.isEmpty()) {
            var3.append(',');
         }

         var3.append(var5.id());
      }

      this.members.set(var3.toString());
      this.list.clear();
      this.list.addAll(var2);
      this.ensure(this.list.size());
   }

   void clear() {
      this.sideChoice = -1.0F;
      this.members.reset();
      this.axis.reset();
      this.list.clear();
      this.posX.reset();
      this.posY.reset();
      this.placed.reset();
      this.size.reset();
      this.background.reset();
      this.opacity.reset();
   }

   void appear() {
      float var1 = 0.0F;

      for (HudModule var3 : this.list) {
         var1 = Math.max(var1, var3.laidOut ? var3.presence.value() : 0.0F);
      }

      this.presence.snap(var1);
      this.positioned = false;
   }

   List<HudModule> present() {
      ArrayList var1 = new ArrayList();

      for (HudModule var3 : this.list) {
         if (var3.laidOut && var3.presence.value() > 0.3F) {
            var1.add(var3);
         }
      }

      return var1;
   }

   @Override
   protected boolean shown() {
      for (HudModule var2 : this.list) {
         if (var2.shown()) {
            return true;
         }
      }

      return false;
   }

   @Override
   protected boolean hasContent(HudBounds var1) {
      for (HudModule var3 : this.list) {
         if (var3.wanted) {
            return true;
         }
      }

      return false;
   }

   @Override
   protected boolean mergeable() {
      return true;
   }

   @Override
   protected float cornerRadius() {
      float var1 = this.contentHeight();
      return this.row() && var1 <= 26.0F ? var1 / 2.0F : 8.0F;
   }

   @Override
   protected void measure(HudBounds var1, float[] var2) {
      int var3 = this.list.size();
      this.ensure(var3);
      boolean var4 = this.row();
      float var5 = 0.0F;
      float var6 = 0.0F;

      for (int var7 = 0; var7 < var3; var7++) {
         HudModule var8 = this.list.get(var7);
         float var9 = var8.laidOut ? ease(var8.presence.value()) : 0.0F;
         float var10 = var8.laidOut ? (var4 ? var8.width : var8.height) : 0.0F;
         this.slotStart[var7] = var5;
         this.slotLength[var7] = var10 * var9;
         var5 += var10 * var9;
         if (var8.laidOut) {
            var6 = Math.max(var6, (var4 ? var8.height : var8.width) * var9);
         }
      }

      float var13 = var4 ? 0.5F : this.side(var1);

      for (int var14 = 0; var14 < var3; var14++) {
         HudModule var15 = this.list.get(var14);
         if (var15.laidOut) {
            float var16 = var4 ? var15.width : var15.height;
            float var11 = this.slotStart[var14] + (this.slotLength[var14] - var16) / 2.0F;
            float var12 = (var6 - (var4 ? var15.height : var15.width)) * var13;
            this.offsetX[var14] = var4 ? var11 : var12;
            this.offsetY[var14] = var4 ? var12 : var11;
         }
      }

      var2[0] = var4 ? var5 : var6;
      var2[1] = var4 ? var6 : var5;
   }

   private float side(HudBounds var1) {
      float var2 = this.x + this.screenWidth() / 2.0F;
      float var3 = var1.width / 2.0F;
      float var4 = Math.abs(var2 - var3) / Math.max(1.0F, var1.width);
      float var5 = this.sideChoice == 0.5F ? (var4 > 0.1F ? (var2 > var3 ? 1 : 0) : 0.5F) : (var4 < 0.06F ? 0.5F : (var2 > var3 ? 1.0F : 0.0F));
      if (this.sideChoice < 0.0F) {
         this.align.snap(var5);
      }

      this.sideChoice = var5;
      return this.align.target(var5).update(var1.dt);
   }

   @Override
   protected void render(Canvas var1, HudBounds var2) {
      float var3 = this.contentWidth();
      float var4 = this.contentHeight();
      this.panel(var1, 0.0F, 0.0F, var3, var4, this.cornerRadius());
      boolean var5 = this.row();
      float var6 = -1.0F;

      for (int var7 = 0; var7 < this.list.size(); var7++) {
         HudModule var8 = this.list.get(var7);
         if (var8.laidOut) {
            float var9 = Math.max(0.0F, Math.min(1.0F, var8.presence.value()));
            if (var6 >= 0.0F) {
               int var10 = BlendMode.withAlpha(-1, 0.14F * Math.min(var6, var9));
               float var11 = this.slotStart[var7];
               if (var5) {
                  float var12 = Math.min(var4 * 0.28F, 8.0F);
                  var1.roundRect(var11 - 0.5F, var12, 1.0F, var4 - var12 * 2.0F, 0.5F, var10);
               } else {
                  var1.roundRect(7.0F, var11 - 0.5F, var3 - 14.0F, 1.0F, 0.5F, var10);
               }
            }

            var6 = var9;
         }
      }
   }

   void placeMembers(HudBounds var1) {
      float var2 = this.renderScale.value();
      float var3 = this.renderX.value();
      float var4 = this.renderY.value();
      float var5 = this.scale();

      for (int var6 = 0; var6 < this.list.size(); var6++) {
         HudModule var7 = this.list.get(var6);
         if (var7.laidOut) {
            var7.x = this.x + this.offsetX[var6] * var5;
            var7.y = this.y + this.offsetY[var6] * var5;
            float var8 = var3 + this.offsetX[var6] * var2;
            float var9 = var4 + this.offsetY[var6] * var2;
            if (!var7.positioned) {
               var7.dockX.snap(0.0F);
               var7.dockY.snap(0.0F);
               var7.dockScale.snap(0.0F);
               var7.positioned = true;
            } else if (var7.dockedTo != this || var7.dockedSlot != var6) {
               var7.dockX.snap(var7.renderX.value() - var8);
               var7.dockY.snap(var7.renderY.value() - var9);
               var7.dockScale.snap(var7.renderScale.value() - var2);
            }

            var7.dockedTo = this;
            var7.dockedSlot = var6;
            var7.renderX.snap(var8 + var7.dockX.target(0.0F).update(var1.dt));
            var7.renderY.snap(var9 + var7.dockY.target(0.0F).update(var1.dt));
            var7.renderScale.snap(var2 + var7.dockScale.target(0.0F).update(var1.dt));
         }
      }
   }

   void slotRect(int var1, float[] var2) {
      float var3 = this.renderScale.value();
      boolean var4 = this.row();
      float var5 = this.slotStart[var1] * var3;
      float var6 = this.slotLength[var1] * var3;
      var2[0] = this.renderX.value() + (var4 ? var5 : 0.0F);
      var2[1] = this.renderY.value() + (var4 ? 0.0F : var5);
      var2[2] = var4 ? var6 : this.renderWidth();
      var2[3] = var4 ? this.renderHeight() : var6;
   }

   float boundary(int var1) {
      return (this.row() ? this.renderX.value() : this.renderY.value()) + this.slotStart[var1] * this.renderScale.value();
   }

   static void preferredSize(List<HudModule> var0, boolean var1, float[] var2) {
      float var3 = 0.0F;
      float var4 = 0.0F;

      for (HudModule var6 : var0) {
         if (var6.laidOut) {
            var3 += var1 ? var6.width : var6.height;
            var4 = Math.max(var4, var1 ? var6.height : var6.width);
         }
      }

      var2[0] = var1 ? var3 : var4;
      var2[1] = var1 ? var4 : var3;
   }

   private void ensure(int var1) {
      if (this.offsetX.length < var1) {
         int var2 = Math.max(var1, this.offsetX.length * 2);
         this.offsetX = new float[var2];
         this.offsetY = new float[var2];
         this.slotStart = new float[var2];
         this.slotLength = new float[var2];
      }
   }

   private static float ease(float var0) {
      var0 = Math.max(0.0F, Math.min(1.0F, var0));
      return var0 * var0 * (3.0F - 2.0F * var0);
   }
}
