// Sourced By DevFFNightmare
package dev.purxiy.ui.config;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.widget.Slider;
import java.util.function.BiConsumer;

final class ConfigLibraryWidget extends ConfigLibraryRenderer4 {
   private static final float TILE = 96.0F;
   private static final String[] ICONS = new String[]{"sparkles", "copy"};
   private static final String[] TITLES = new String[]{"Blank", "Current setup"};
   private static final String[] LINES = new String[]{"Purxiy as new, nothing set up", "Everything you have on now"};
   private final Slider name;
   private final BiConsumer<String, Boolean> create;
   private final ConfigLibraryRenderer8.TypeC cancel = new ConfigLibraryRenderer8.TypeC();
   private final ConfigLibraryRenderer8.TypeC make = new ConfigLibraryRenderer8.TypeC();
   private final Object[] tileKeys = new Object[]{new Object(), new Object()};
   private final Animator[] tileHover = new Animator[]{Animator.smooth(0.0F), Animator.smooth(0.0F)};
   private final Animator chosen;
   private final float[] tileX = new float[2];
   private float tileY;
   private float tileW;
   private String draft;
   private int start;

   ConfigLibraryWidget(float[] var1, boolean var2, BiConsumer<String, Boolean> var3) {
      super(var1);
      this.create = var3;
      this.start = var2 ? 0 : 1;
      this.chosen = Animator.snappy(this.start);
      this.draft = ConfigLibraryTransform.newName();
      this.name = new Slider(new Slider.Listener() {
         @Override
         public String get() {
            return ConfigLibraryWidget.this.draft;
         }

         @Override
         public void set(String var1) {
            ConfigLibraryWidget.this.draft = var1.replace('\n', ' ');
         }

         @Override
         public int maxLength() {
            return 32;
         }

         @Override
         public String placeholder() {
            return "Config name";
         }
      }).onSubmit(this::submit);
      this.name.focus();
      this.name.selectAll();
   }

   @Override
   float width() {
      return 470.0F;
   }

   @Override
   String title() {
      return "New config";
   }

   
   @Override
   String subtitle() {
      return "A config keeps your whole setup: modules, settings, HUD and keys.";
   }

   @Override
   float bodyHeight(Canvas var1, float var2) {
      return 198.0F;
   }

   @Override
   void body(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6) {
      this.name.render(var1, var3, var4 + 16.0F, var5, var6);
      float var7 = var4 + 32.0F + 26.0F;
      ConfigLibraryRenderer8.label(var2, "START FROM", var3, var7, Icons.TEXT_3, FontStyle.LEFT);
      this.tileY = var7 + 14.0F;
      this.tileW = (var5 - 12.0F) / 2.0F;
      float var8 = this.chosen.target(this.start).update(MotionStyle.TOGGLE.dt(var1.dt));

      for (int var9 = 0; var9 < 2; var9++) {
         this.tileX[var9] = var3 + var9 * (this.tileW + 12.0F);
         float var10 = Math.max(0.0F, 1.0F - Math.abs(var8 - var9));
         this.tile(var1, var2, var9, this.tileX[var9], this.tileY, this.tileW, var10, var6);
      }

      String var11 = this.start == 0 ? "Your keybinds and menu settings stay as they are." : "It is in use right away: change things, then press Save.";
      var2.text(
         Fonts.REGULAR,
         FontMetrics.ellipsize(Fonts.REGULAR, var11, 11.5F, var5, 0.0F),
         var3,
         this.tileY + 96.0F + 20.0F,
         11.5F,
         Icons.TEXT_3,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
   }

   private void tile(DrawContext var1, Canvas var2, int var3, float var4, float var5, float var6, float var7, boolean var8) {
      boolean var9 = var8 && var1.hover(this.tileKeys[var3], var4, var5, var6, 96.0F, DrawContext.TypeE.HAND);
      float var10 = this.tileHover[var3].target(var9 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var11 = 16.0F;
      if (var7 > 0.01F) {
         var2.glow(var4, var5, var6, 96.0F, var11, 20.0F, BlendMode.withAlpha(Icons.VIOLET, 0.16F * var7));
      }

      var2.roundRect(
         var4, var5, var6, 96.0F, var11, BlendMode.lerp(BlendMode.lerp(184549375, 318767103, var10), BlendMode.withAlpha(Icons.VIOLET, 0.11F), var7)
      );
      var2.roundRectOutline(
         var4, var5, var6, 96.0F, var11, 1.0F, BlendMode.lerp(BlendMode.lerp(385875967, 721420287, var10), BlendMode.withAlpha(Icons.MAGENTA, 0.6F), var7)
      );
      ConfigLibraryRenderer4.iconTile(var2, var4 + 14.0F, var5 + 14.0F + 16.0F, 32.0F, ICONS[var3], Icons.MAGENTA, var7);
      float var12 = var4 + var6 - 14.0F - 8.0F;
      float var13 = var5 + 14.0F + 8.0F;
      var2.roundRectOutline(
         var12 - 8.0F, var13 - 8.0F, 16.0F, 16.0F, 8.0F, 1.2F, BlendMode.lerp(1090519039, BlendMode.withAlpha(Theme.tint(-1783297), 0.9F), var7)
      );
      if (var7 > 0.01F) {
         var2.circle(var12, var13, 4.5F * var7, BlendMode.withAlpha(Theme.tint(-1783297), var7));
      }

      var2.text(
         Fonts.SEMIBOLD,
         TITLES[var3],
         var4 + 14.0F,
         var5 + 96.0F - 34.0F,
         13.5F,
         BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, Math.max(var7, var10)),
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      var2.text(
         Fonts.REGULAR,
         FontMetrics.ellipsize(Fonts.REGULAR, LINES[var3], 11.5F, var6 - 28.0F, 0.0F),
         var4 + 14.0F,
         var5 + 96.0F - 16.0F,
         11.5F,
         Icons.TEXT_2,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
   }

   @Override
   void footer(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6) {
      float var7 = ConfigLibraryRenderer8.TypeC.width(var2, "plus", "Create config", 34.0F);
      this.make.render(var1, var4 - var7, var5, 34.0F, "plus", "Create config", ConfigLibraryRenderer8.TypeI.PRIMARY, var6);
      float var8 = ConfigLibraryRenderer8.TypeC.width(var2, (String)null, "Cancel", 34.0F);
      this.cancel.render(var1, var4 - var7 - 8.0F - var8, var5, 34.0F, (String)null, "Cancel", ConfigLibraryRenderer8.TypeI.TEXT, var6);
   }

   @Override
   boolean press(float var1, float var2) {
      if (this.name.mouseDown(var1, var2)) {
         return true;
      } else {
         for (int var3 = 0; var3 < 2; var3++) {
            if (var1 >= this.tileX[var3] && var1 <= this.tileX[var3] + this.tileW && var2 >= this.tileY && var2 <= this.tileY + 96.0F) {
               if (this.start != var3) {
                  this.start = var3;
                  SoundRequest.play(SoundEvent.TOGGLE_ON);
               }

               return true;
            }
         }

         if (this.cancel.hit(var1, var2)) {
            this.cancel.press();
            this.close();
            SoundRequest.play(SoundEvent.COLLAPSE);
            return true;
         } else if (this.make.hit(var1, var2)) {
            this.make.press();
            this.submit();
            return true;
         } else {
            return false;
         }
      }
   }

   @Override
   void submit() {
      if (!this.closing()) {
         this.close();
         this.create.accept(this.draft, this.start == 0);
      }
   }

   @Override
   boolean key(int var1, boolean var2) {
      if (var1 == 256) {
         return false;
      } else if (this.name.focused()) {
         this.name.keyPressed(var1);
         return true;
      } else if (var1 != 263 && var1 != 262) {
         return false;
      } else {
         this.start = var1 == 263 ? 0 : 1;
         SoundRequest.play(SoundEvent.TOGGLE_ON);
         return true;
      }
   }

   @Override
   void typed(int var1) {
      if (!this.name.focused()) {
         this.name.focus();
      }

      this.name.charTyped(var1);
   }

   @Override
   void mouseDrag(float var1, float var2) {
      this.name.mouseDrag(var1, var2);
   }

   @Override
   void mouseUp() {
      this.name.mouseUp();
   }

   void dev(int var1,  String var2) {
      this.start = var1;
      if (var2 != null) {
         this.draft = var2;
      }
   }
}
