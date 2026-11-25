package dev.purxiy.ui.config;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.ClientServices;
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
import dev.purxiy.ui.KeyLabel;
import dev.purxiy.ui.Theme;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import net.minecraft.class_310;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.util.tinyfd.TinyFileDialogs;

final class ConfigLibraryCheckoutPage extends ConfigLibraryPage {
   private static final float BOX = 56.0F;
   private static final float DIGIT_W = 44.0F;
   private static final float DIGIT_H = 50.0F;
   private static final float DIGIT_GAP = 8.0F;
   private static final float CARD = 62.0F;
   private final Consumer<ConfigLibraryCheckoutPage.CheckoutEntry> add;
   private final ConfigLibraryDash.DashBar cancel = new ConfigLibraryDash.DashBar();
   private final ConfigLibraryDash.DashBar confirm = new ConfigLibraryDash.DashBar();
   private final ConfigLibraryDash.DashBar fromFile = new ConfigLibraryDash.DashBar();
   private final ConfigLibraryDash.DashState clear = new ConfigLibraryDash.DashState();
   private final Object boxKey = new Object();
   private final Animator boxHover = Animator.smooth(0.0F);
   private final Animator shake = new Animator(0.0F, 1400.0F, 14.0F);
   private final Animator readyShown = Animator.smooth(0.0F);
   private final ConfigLibraryNebula.NebulaCloud look = new ConfigLibraryNebula.NebulaCloud();
   private final StringBuilder digits = new StringBuilder();
   private ConfigLibraryCheckoutPage.PasteState pasted = ConfigLibraryCheckoutPage.PasteState.NONE;
   private int pastedLength;
   private ConfigLibraryCheckoutPage.LookupState lookup = ConfigLibraryCheckoutPage.LookupState.IDLE;

   private ConfigLibraryStats ready;

   private Path readyFile;
   private String readyFrom = "";
   private float boxX;
   private float boxY;
   private float boxW;
   private float typedAt = -10.0F;
   private float time;
   private boolean picking;

   ConfigLibraryCheckoutPage(float[] var1, Consumer<ConfigLibraryCheckoutPage.CheckoutEntry> var2) {
      super(var1);
      this.add = var2;
      ConfigLibraryHost.checkStatus(false);
   }

   @Override
   float width() {
      return 480.0F;
   }

   @Override
   String title() {
      return "Import a config";
   }


   @Override
   String subtitle() {
      return "Paste a config code someone shared with you, or type a 5-digit share code.";
   }

   @Override
   float bodyHeight(Canvas var1, float var2) {
      return 152.0F + (this.ready != null ? 84.0F : 0.0F);
   }

   @Override
   void body(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6) {
      this.time = var1.time;
      this.pasteBox(var1, var2, var3, var4, var5, var6);
      float var7 = var4 + 56.0F + 26.0F;
      ConfigLibraryDash.label(var2, "OR TYPE A SHARE CODE", var3, var7, Icons.TEXT_3, FontStyle.LEFT);
      this.digits(var2, var3, var7 + 14.0F, var1.time);
      float var8 = this.readyShown.target(this.ready != null ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      if (this.ready != null && var8 > 0.01F) {
         float var9 = var7 + 14.0F + 50.0F + 22.0F;
         var2.pushAlpha(var8);
         var2.push();
         var2.translate(0.0F, (1.0F - var8) * 8.0F);
         this.card(var1, var2, this.ready, var3, var9, var5, var6);
         var2.pop();
         var2.popAlpha();
      } else {
         this.clear.shown = false;
      }
   }

   private void pasteBox(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6) {
      this.boxX = var3;
      this.boxY = var4;
      this.boxW = var5;
      boolean var7 = var6 && var1.hover(this.boxKey, var3, var4, var5, 56.0F, DrawContext.Cursor.HAND);
      float var8 = this.boxHover.target(var7 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var9 = this.shake.update(MotionStyle.TOGGLE.dt(var1.dt));
      var2.push();
      var2.translate(var9, 0.0F);
      float var10 = 16.0F;
      float var11 = var4 + 28.0F;
      int var12 = this.pasted == ConfigLibraryCheckoutPage.PasteState.NOT_A_CONFIG ? -29785 : Icons.MAGENTA;
      if (this.pasted == ConfigLibraryCheckoutPage.PasteState.NONE) {
         var2.roundRect(var3, var4, var5, 56.0F, var10, BlendMode.withAlpha(-1, 0.03F + 0.03F * var8));
         float var13 = MotionStyle.reduced() ? 0.0F : var1.time * 8.0F * var8;
         ConfigLibraryDash.dashed(
            var2,
            var3 + 0.5F,
            var4 + 0.5F,
            var5 - 1.0F,
            55.0F,
            var10,
            6.0F,
            5.0F,
            1.2F,
            BlendMode.withAlpha(BlendMode.lerp(-1, Icons.PURPLE, var8), 0.22F + 0.22F * var8),
            var13
         );
      } else {
         var2.roundRect(var3, var4, var5, 56.0F, var10, BlendMode.withAlpha(var12, 0.08F + 0.04F * var8));
         var2.roundRectOutline(var3, var4, var5, 56.0F, var10, 1.0F, BlendMode.withAlpha(var12, 0.45F + 0.2F * var8));
      }
      String var21 = switch (this.pasted) {
         case NONE -> "clipboard-paste";
         case OK -> "check";
         case NOT_A_CONFIG -> "circle-alert";
      };
      float var15 = var3 + 16.0F + 16.0F;
      var2.circle(
         var15,
         var11,
         16.0F,
         this.pasted == ConfigLibraryCheckoutPage.PasteState.NONE ? BlendMode.withAlpha(-1, 0.06F + 0.04F * var8) : BlendMode.withAlpha(var12, 0.18F)
      );
      var2.icon(
         var21,
         var15,
         var11,
         15.0F,
         this.pasted == ConfigLibraryCheckoutPage.PasteState.NONE
            ? BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var8)
            : (this.pasted == ConfigLibraryCheckoutPage.PasteState.OK ? Theme.tint(-1783297) : -29785)
      );

      var21 = switch (this.pasted) {
         case NONE -> "Paste a config code";
         case OK -> "Config code pasted";
         case NOT_A_CONFIG -> "That isn't a SourceByDevFFnightmare DeObfDeCompByGuideX config code";
      };

      String var23 = switch (this.pasted) {
         case NONE -> "Click here or press Ctrl+V";
         case OK -> this.pastedLength + " letters  ·  click to paste another";
         case NOT_A_CONFIG -> "Copy one with Export in SourceByDevFFnightmare DeObfDeCompByGuideX, then paste it here";
      };
      float var18 = var15 + 16.0F + 14.0F;
      float var19 = var5 - (var18 - var3) - 16.0F - 64.0F;
      var2.text(
         Fonts.MEDIUM,
         FontMetrics.ellipsize(Fonts.MEDIUM, var21, 13.0F, var19, 0.0F),
         var18,
         var11 - 8.0F,
         13.0F,
         Icons.TEXT,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      var2.text(
         Fonts.REGULAR,
         FontMetrics.ellipsize(Fonts.REGULAR, var23, 11.5F, var19, 0.0F),
         var18,
         var11 + 10.0F,
         11.5F,
         Icons.TEXT_2,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      float var20 = var2.textWidth(Fonts.SEMIBOLD, "Ctrl V", 10.5F) + 12.0F;
      KeyLabel.keyCap(var2, "Ctrl V", var3 + var5 - 16.0F - Math.max(20.5F, var20), var11, 10.5F, BlendMode.lerp(Icons.TEXT_3, Icons.TEXT_2, var8));
      var2.pop();
   }

   private void digits(Canvas var1, float var2, float var3, float var4) {
      boolean var5 = ConfigLibraryHost.shareCodes() || ConfigLibraryHost.unknown();
      int var6 = this.digits.length();
      float var7 = var3 + 25.0F;
      var1.pushAlpha(!var5 && var6 <= 0 ? 0.55F : 1.0F);

      for (int var8 = 0; var8 < 5; var8++) {
         float var9 = var2 + var8 * 52.0F;
         boolean var10 = var8 == var6 && this.lookup != ConfigLibraryCheckoutPage.LookupState.LOOKING;
         boolean var11 = var8 < var6;
         int var12 = this.lookup == ConfigLibraryCheckoutPage.LookupState.FOUND
            ? BlendMode.withAlpha(Theme.tint(-1783297), 0.6F)
            : (
               this.lookup != ConfigLibraryCheckoutPage.LookupState.MISSING && this.lookup != ConfigLibraryCheckoutPage.LookupState.OFFLINE
                  ? (var10 ? BlendMode.withAlpha(Icons.MAGENTA, 0.6F) : (var11 ? 822083583 : 486539263))
                  : BlendMode.withAlpha(-29785, 0.55F)
            );
         var1.roundRect(var9, var3, 44.0F, 50.0F, 12.0F, var11 ? 352321535 : 184549375);
         var1.roundRectOutline(var9, var3, 44.0F, 50.0F, 12.0F, 1.0F, var12);
         if (var11) {
            var1.text(Fonts.SEMIBOLD, String.valueOf(this.digits.charAt(var8)), var9 + 22.0F, var7, 21.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
         } else if (var10) {
            float var13 = var4 - this.typedAt;
            float var14 = !(var13 < 0.5F) && !MotionStyle.reduced() ? 0.5F + 0.5F * (float)Math.cos((var13 - 0.5F) * 5.5F) : 1.0F;
            var1.roundRect(var9 + 22.0F - 0.8F, var7 - 10.0F, 1.6F, 20.0F, 0.8F, BlendMode.withAlpha(Icons.MAGENTA, Math.max(0.0F, var14)));
         }
      }

      var1.popAlpha();
      float var15 = var2 + 220.0F + 32.0F + 18.0F;
      int var16 = Icons.TEXT_3;
      String var17;
      switch (this.lookup) {
         case LOOKING:
            for (int var19 = 0; var19 < 3; var19++) {
               float var20 = MotionStyle.reduced() ? 0.5F : (float)(0.5 + 0.5 * Math.sin(var4 * 4.2F - var19 * 0.9F));
               var1.circle(var15 + 4.0F + var19 * 11, var7, 2.6F + 0.8F * var20, BlendMode.withAlpha(Theme.tint(-1783297), 0.25F + 0.6F * var20));
            }

            var1.text(Fonts.MEDIUM, "Looking it up", var15 + 36.0F, var7, 11.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
            return;
         case FOUND:
            var17 = "Found it";
            var16 = Theme.tint(-1783297);
            break;
         case MISSING:
            var17 = "No config has this code";
            var16 = -29785;
            break;
         case OFFLINE:
            var17 = "Can't reach the SourceByDevFFnightmare DeObfDeCompByGuideX server";
            var16 = -29785;
            break;
         default:
            var17 = var5 ? "Made with Export → Share code" : "Comes with the SourceByDevFFnightmare DeObfDeCompByGuideX server";
      }

      if (this.lookup == ConfigLibraryCheckoutPage.LookupState.FOUND) {
         var1.icon("check", var15 + 6.0F, var7, 13.0F, var16);
         var15 += 18.0F;
      }

      float var18 = this.boxX + this.boxW - var15;
      var1.text(Fonts.MEDIUM, FontMetrics.ellipsize(Fonts.MEDIUM, var17, 11.5F, var18, 0.0F), var15, var7, 11.5F, var16, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   private void card(DrawContext var1, Canvas var2, ConfigLibraryStats var3, float var4, float var5, float var6, boolean var7) {
      var2.rect(var4, var5 - 11.0F, var6, 1.0F, 318767103);
      float var8 = var5 + 11.0F;
      var2.roundRect(var4, var8, var6, 62.0F, 16.0F, BlendMode.withAlpha(var3.accent(), 0.08F));
      var2.roundRectOutline(var4, var8, var6, 62.0F, 16.0F, 1.0F, BlendMode.withAlpha(BlendMode.lerp(var3.accent(), -1, 0.25F), 0.4F));
      float var9 = 46.0F;
      float var10 = var4 + 8.0F;
      var2.pushClip(var10, var8 + 8.0F, var9, var9, 12.0F);
      this.look.time = var1.time;
      this.look.fill = true;
      this.look.hover = 0.4F;
      this.look.starScale = 0.7F;
      ConfigLibraryNebula.draw(var2, var3, var10, var8 + 8.0F, var9, var9, this.look);
      var2.popClip();
      var2.roundRectOutline(var10, var8 + 8.0F, var9, var9, 12.0F, 1.0F, 587202559);
      float var11 = var10 + var9 + 14.0F;
      float var12 = var8 + 31.0F;
      float var13 = var6 - (var11 - var4) - 46.0F;
      Translations.pause();
      var2.text(
         Fonts.SEMIBOLD,
         FontMetrics.ellipsize(Fonts.SEMIBOLD, var3.name, 14.0F, var13, 0.0F),
         var11,
         var12 - 9.0F,
         14.0F,
         Icons.TEXT,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      Translations.resume();
      String var14 = Translations.tr(ConfigLibraryDash.modules(var3.moduleCount()))
         + "  ·  "
         + Translations.tr("by")
         + " "
         + var3.author
         + "  ·  "
         + this.readyFrom;
      Translations.pause();
      var2.text(
         Fonts.REGULAR,
         FontMetrics.ellipsize(Fonts.REGULAR, var14, 11.5F, var13, 0.0F),
         var11,
         var12 + 10.0F,
         11.5F,
         Icons.TEXT_2,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      Translations.resume();
      this.clear.render(var1, var4 + var6 - 12.0F - 13.0F, var12, 26.0F, "x", false, 0, 1.0F, var7);
   }

   @Override
   void footer(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6) {
      this.fromFile.render(var1, var3 - 10.0F, var5, 34.0F, "file-up", "From a file", ConfigLibraryDash.DashTone.TEXT, var6 && !this.picking);
      float var7 = ConfigLibraryDash.DashBar.width(var2, "plus", "Add to library", 34.0F);
      if (this.ready != null) {
         this.confirm.render(var1, var4 - var7, var5, 34.0F, "plus", "Add to library", ConfigLibraryDash.DashTone.PRIMARY, var6);
      } else {
         ConfigLibraryPage.disabled(var1, this.confirm, var4 - var7, var5, 34.0F, "plus", "Add to library");
      }

      float var8 = ConfigLibraryDash.DashBar.width(var2, (String)null, "Cancel", 34.0F);
      this.cancel.render(var1, var4 - var7 - 8.0F - var8, var5, 34.0F, (String)null, "Cancel", ConfigLibraryDash.DashTone.TEXT, var6);
   }

   void paste() {
      String var1 = ConfigLibraryTransform.clipboard();
      String var2 = ConfigLibraryError.code(var1);
      if (var2 != null) {
         this.digits.setLength(0);
         this.digits.append(var2);
         this.typedAt = this.time;
         this.lookUp();
      } else {
         ConfigLibraryStats var3 = ConfigLibraryTransform.read(var1);
         if (var3 == null) {
            this.pasted = ConfigLibraryCheckoutPage.PasteState.NOT_A_CONFIG;
            this.shake.snap(0.0F).impulse(-260.0F);
            SoundRequest.play(SoundEvent.DENY);
            if (this.readyFile == null && this.lookup != ConfigLibraryCheckoutPage.LookupState.FOUND) {
               this.ready = null;
            }
         } else {
            this.pasted = ConfigLibraryCheckoutPage.PasteState.OK;
            this.pastedLength = var1.strip().length();
            this.offer(var3, (Path)null, Translations.tr("pasted"));
            this.digits.setLength(0);
            this.lookup = ConfigLibraryCheckoutPage.LookupState.IDLE;
            SoundRequest.play(SoundEvent.SELECT);
         }
      }
   }

   private void offer(ConfigLibraryStats var1,  Path var2, String var3) {
      this.ready = var1;
      this.readyFile = var2;
      this.readyFrom = var3;
      this.readyShown.snap(0.0F);
   }

   private void lookUp() {
      String var1 = this.digits.toString();
      this.lookup = ConfigLibraryCheckoutPage.LookupState.LOOKING;
      ConfigLibraryError.fetch(var1, (var2, var3) -> {
         if (!this.closing() && var1.contentEquals(this.digits)) {
            if (var2 == null) {
               this.lookup = "offline".equals(var3) ? ConfigLibraryCheckoutPage.LookupState.OFFLINE : ConfigLibraryCheckoutPage.LookupState.MISSING;
               SoundRequest.play(SoundEvent.DENY);
            } else {
               this.lookup = ConfigLibraryCheckoutPage.LookupState.FOUND;
               this.pasted = ConfigLibraryCheckoutPage.PasteState.NONE;
               String var4 = Translations.tr("share code");
               this.offer(var2, (Path)null, var4 + " " + var1);
               SoundRequest.play(SoundEvent.SELECT);
            }
         }
      });
   }

   private void typeDigit(char var1) {
      if (this.digits.length() < 5 && this.lookup != ConfigLibraryCheckoutPage.LookupState.LOOKING) {
         this.digits.append(var1);
         this.typedAt = this.time;
         this.lookup = ConfigLibraryCheckoutPage.LookupState.IDLE;
         SoundRequest.play(SoundEvent.TYPE);
         if (this.digits.length() == 5) {
            this.lookUp();
         }
      } else {
         SoundRequest.play(SoundEvent.DENY);
      }
   }

   private void backspace() {
      if (!this.digits.isEmpty()) {
         this.digits.setLength(this.digits.length() - 1);
         this.typedAt = this.time;
         if (this.lookup == ConfigLibraryCheckoutPage.LookupState.FOUND
            && this.ready != null
            && this.readyFile == null
            && this.pasted == ConfigLibraryCheckoutPage.PasteState.NONE) {
            this.ready = null;
         }

         this.lookup = ConfigLibraryCheckoutPage.LookupState.IDLE;
         SoundRequest.play(SoundEvent.TYPE, 0.9F);
      }
   }

   private void chooseFile() {
      if (!this.picking) {
         this.picking = true;
         SoundRequest.play(SoundEvent.CLICK);
         Thread var1 = new Thread(
            () -> {
               String var1x = null;

               try {
                  MemoryStack var2 = MemoryStack.stackPush();

                  try {
                     PointerBuffer var3 = var2.mallocPointer(2);
                     var3.put(var2.UTF8("*.json"));
                     var3.put(var2.UTF8("*.txt"));
                     var3.flip();
                     Path var4 = ConfigLibraryTransform.folder();
                     String var5 = Files.isDirectory(var4) ? var4 + File.separator : "";
                     var1x = TinyFileDialogs.tinyfd_openFileDialog(
                        Translations.tr("Import a config"), var5, var3, Translations.tr("SourceByDevFFnightmare DeObfDeCompByGuideX configs (JSON)"), false
                     );
                  } catch (Throwable var7) {
                     if (var2 != null) {
                        try {
                           var2.close();
                        } catch (Throwable var6) {
                           var7.addSuppressed(var6);
                        }
                     }

                     throw var7;
                  }

                  if (var2 != null) {
                     var2.close();
                  }
               } catch (Throwable var8) {
                  ClientServices.LOGGER.warn("Could not open the file picker", var8);
               }

               String var9 = var1x;
               class_310.method_1551().execute(() -> {
                  this.picking = false;
                  if (var9 != null && !var9.isBlank() && !this.closing()) {
                     this.file(Path.of(var9));
                  }
               });
            },
            "SourceByDevFFnightmare DeObfDeCompByGuideX Config Picker"
         );
         var1.setDaemon(true);
         var1.start();
      }
   }

   void file(Path var1) {
      try {
         if (Files.size(var1) > 8388608L) {
            throw new IllegalArgumentException("too large");
         }

         String var2 = Files.readString(var1, StandardCharsets.UTF_8);
         ConfigLibraryStats var3 = ConfigLibraryTransform.read(var2);
         if (var3 != null) {
            this.offer(var3, var1, var1.getFileName().toString());
            this.pasted = ConfigLibraryCheckoutPage.PasteState.NONE;
            SoundRequest.play(SoundEvent.SELECT);
            return;
         }
      } catch (Exception var4) {
         ClientServices.LOGGER.warn("Could not read config file {}: {}", var1, var4.toString());
      }

      this.pasted = ConfigLibraryCheckoutPage.PasteState.NOT_A_CONFIG;
      this.shake.snap(0.0F).impulse(-260.0F);
      SoundRequest.play(SoundEvent.DENY);
   }

   @Override
   boolean press(float var1, float var2) {
      if (this.clear.hit(var1, var2)) {
         this.clear.press();
         this.ready = null;
         this.readyFile = null;
         this.pasted = ConfigLibraryCheckoutPage.PasteState.NONE;
         this.digits.setLength(0);
         this.lookup = ConfigLibraryCheckoutPage.LookupState.IDLE;
         SoundRequest.play(SoundEvent.TOGGLE_OFF);
         return true;
      } else if (var1 >= this.boxX && var1 <= this.boxX + this.boxW && var2 >= this.boxY && var2 <= this.boxY + 56.0F) {
         this.paste();
         return true;
      } else if (this.fromFile.hit(var1, var2)) {
         this.fromFile.press();
         this.chooseFile();
         return true;
      } else if (this.cancel.hit(var1, var2)) {
         this.close();
         SoundRequest.play(SoundEvent.COLLAPSE);
         return true;
      } else if (this.confirm.hit(var1, var2)) {
         this.confirm.press();
         this.submit();
         return true;
      } else {
         return false;
      }
   }

   @Override
   void submit() {
      ConfigLibraryStats var1 = this.ready;
      if (var1 != null && !this.closing()) {
         this.close();
         this.add.accept(new ConfigLibraryCheckoutPage.CheckoutEntry(var1, this.readyFile));
      } else {
         SoundRequest.play(SoundEvent.DENY);
      }
   }

   @Override
   boolean key(int var1, boolean var2) {
      if (var2 && var1 == 86) {
         this.paste();
         return true;
      } else if (var1 == 259) {
         this.backspace();
         return true;
      } else {
         return false;
      }
   }

   @Override
   void typed(int var1) {
      if (var1 >= 48 && var1 <= 57) {
         this.typeDigit((char)var1);
      }
   }

   void dev(String var1) {
      if (ConfigLibraryError.code(var1) != null) {
         this.digits.setLength(0);

         for (char var5 : var1.strip().toCharArray()) {
            this.typeDigit(var5);
         }
      } else {
         ConfigLibraryTransform.dryClipboard(var1);
         this.paste();
      }
   }

   private static enum LookupState {
      IDLE,
      LOOKING,
      FOUND,
      MISSING,
      OFFLINE;

      private static ConfigLibraryCheckoutPage.LookupState[] $values() {
         return new ConfigLibraryCheckoutPage.LookupState[]{IDLE, LOOKING, FOUND, MISSING, OFFLINE};
      }
   }

   private static enum PasteState {
      NONE,
      OK,
      NOT_A_CONFIG;

      private static ConfigLibraryCheckoutPage.PasteState[] $values() {
         return new ConfigLibraryCheckoutPage.PasteState[]{NONE, OK, NOT_A_CONFIG};
      }
   }

   record CheckoutEntry(ConfigLibraryStats entry,  Path file) {
   }
}
