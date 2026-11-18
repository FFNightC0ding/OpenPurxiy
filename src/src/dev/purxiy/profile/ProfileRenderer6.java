// Sourced By DevFFNightmare
package dev.purxiy.profile;

import com.mojang.authlib.GameProfile;
import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.Marker;
import dev.purxiy.loader.fabric.ModuleState;
import dev.purxiy.loader.fabric.client.ClientRegistry5;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.social.FriendApi;
import dev.purxiy.social.FriendProfile;
import dev.purxiy.social.Friends;
import dev.purxiy.social.NamePolicy;
import dev.purxiy.social.PlayerMetadata;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.widget.Slider;
import dev.purxiy.web.ProfileWebsite;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.class_310;

final class ProfileRenderer6 {
   static final float ADD_H = 132.0F;
   private static final float TILE_MIN = 232.0F;
   private static final float TILE_H = 64.0F;
   private static final float TILE_GAP = 10.0F;
   private static final float FACE = 40.0F;
   private static final int IGN = 0;
   private static final int USERNAME = 1;
   private static int lastMode = 0;
   private final ProfileRenderer9.TypeH mode = new ProfileRenderer9.TypeH(new String[]{"Minecraft name", "Username"}, new String[]{"gamepad-2", "at-sign"});
   private final Slider field;
   private final ProfileRenderer9.TypeB add = new ProfileRenderer9.TypeB();
   private final ProfileRenderer9.TypeB act = new ProfileRenderer9.TypeB();
   private final ProfileRenderer9.TypeB act2 = new ProfileRenderer9.TypeB();
   private final Map<String, ProfileRenderer6.TypeK> tiles = new HashMap<>();
   private final List<ProfileRenderer6.TypeK> leaving = new ArrayList<>();
   private final Map<UUID, ProfileRenderer6.TypeW> chips = new HashMap<>();
   private final List<ProfileRenderer6.TypeW> shownChips = new ArrayList<>();
   private final Animator scroll = Animator.smooth(0.0F);
   private String text = "";
   private boolean claiming;
   
   private ProfileRenderer6.TypeQ notice;
   
   private ProfileRenderer6.TypeQ fading;
   private float fadedAt = -100.0F;
   
   private String busy;
   
   private Runnable actRun;
   
   private Runnable act2Run;
   private float scrollTarget;
   private float maxScroll;
   private float time;
   private float gridTop;
   private float gridBottom;
   private boolean entered;

   ProfileRenderer6() {
      this.mode.select(lastMode);
      this.field = new Slider(
            new Slider.Listener() {
               @Override
               public String get() {
                  return ProfileRenderer6.this.text;
               }

               @Override
               public void set(String var1) {
                  ProfileRenderer6.this.text = var1.replace('\n', ' ').replace('\r', ' ');
               }

               @Override
               public int maxLength() {
                  return 17;
               }

               @Override
               public String placeholder() {
                  return ProfileRenderer6.this.claiming
                     ? "Pick your username"
                     : (ProfileRenderer6.this.mode.selected() == 0 ? "Their Minecraft name" : "Their Purxiy username");
               }
            }
         )
         .onSubmit(this::submit)
         .suggestions(this::suggestions)
         .icon("user-round-plus");
   }

   private List<String> suggestions() {
      if (!this.claiming && this.mode.selected() == 0 && !ModuleState.moduleActive("nick_hider")) {
         ArrayList var1 = new ArrayList();

         for (String var3 : Marker.knownNames()) {
            if (!Friends.is((UUID)null, var3) && !Friends.self(var3)) {
               var1.add(var3);
            }
         }

         return var1;
      } else {
         return List.of();
      }
   }

   void enter(float var1) {
      this.entered = false;
      this.time = var1;
      FriendApi.refreshSoon();
   }

   void leave() {
      this.field.blur();
      if (this.claiming && this.busy == null) {
         this.claiming = false;
         this.text = "";
      }
   }

   boolean typing() {
      return this.field.focused();
   }

   void render(DrawContext var1, float var2, float var3, float var4, float var5, float var6, boolean var7) {
      Canvas var8 = var1.canvas;
      this.time = var1.time;
      float var9 = Easing.EMPHASIZED.apply(ProfileCurve.clamp01((var6 - 0.08F) / 0.55F));
      float var10 = Easing.EMPHASIZED.apply(ProfileCurve.clamp01((var6 - 0.16F) / 0.55F));
      if (var9 > 0.005F) {
         var8.push();
         var8.translate(0.0F, (1.0F - var9) * 18.0F);
         var8.pushAlpha(var9);
         this.addCard(var1, var8, var2, var3, var4, var7 && var9 > 0.95F);
         var8.popAlpha();
         var8.pop();
      } else {
         this.add.hide();
         this.act.hide();
         this.act2.hide();
      }

      float var11 = var3 + 132.0F + 16.0F;
      if (var10 > 0.005F) {
         var8.push();
         var8.translate(0.0F, (1.0F - var10) * 18.0F);
         var8.pushAlpha(var10);
         this.list(var1, var8, var2, var11, var4, var3 + var5 - var11, var6 - 0.16F, var7 && var10 > 0.95F);
         var8.popAlpha();
         var8.pop();
      }
   }

   private void addCard(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6) {
      ProfileRenderer9.card(var2, var3, var4, var5, 132.0F, 0.0F);
      boolean var7 = this.mode.selected() == 0;
      String var8 = this.claiming ? "Your username" : "Add a friend";
      String var9 = this.claiming
         ? "Purxiy players add you by it, whichever account you play on"
         : (
            var7
               ? "Silent Aura, Aim Assist and the other combat modules leave friends alone"
               : "A Purxiy player, recognised on every Minecraft account they play"
         );
      float var10 = this.claiming ? 0.0F : this.mode.width();
      ProfileRenderer9.title(var2, var8, var9, var3, var4, var5 - 40.0F - var10 - 24.0F);
      if (!this.claiming) {
         this.mode.render(var1, var3 + var5 - 20.0F, var4 + 30.0F, var6 && this.busy == null);
      }

      float var11 = var4 + 82.0F;
      boolean var12 = this.busy != null;
      String var13 = this.claiming ? "Save" : "Add";
      String var14 = this.claiming ? "check" : "user-plus";
      float var15 = Math.max(98.0F, ProfileRenderer9.TypeB.width(var2, var14, var13, 32.0F));
      float var16 = var3 + var5 - 20.0F - var15;
      float var17 = var3 + 20.0F;
      this.field.icon(var7 && !this.claiming ? "user-round-plus" : "at-sign");
      this.field.render(var1, var17, var11, var16 - 10.0F - var17, var6 && !var12);
      this.add.render(var1, var16, var11, var15, 32.0F, var12 ? null : var14, var12 ? "" : var13, ProfileRenderer9.TypeI.PRIMARY, var6 && !var12);
      if (var12) {
         float var18 = this.time * 360.0F * 1.1F;
         var2.arc(var16 + var15 / 2.0F, var11, 7.0F, 2.0F, var18, 250.0F, BlendMode.withAlpha(Icons.TEXT, 0.95F), BlendMode.withAlpha(Icons.TEXT, 0.1F), true);
      }

      this.line(var1, var2, var3 + 20.0F, var4 + 132.0F - 17.0F, var5 - 40.0F, var6);
   }

   private void line(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6) {
      this.shownChips.clear();
      boolean var7 = false;
      if (this.fading != null) {
         float var8 = (this.time - this.fadedAt) * MotionStyle.MENU.speed() / 0.16F;
         if (var8 >= 1.0F) {
            this.fading = null;
         } else {
            var2.pushAlpha((1.0F - var8) * (1.0F - var8));
            this.message(var1, var2, this.fading, var3, var4 + var8 * 6.0F, var5, false);
            var2.popAlpha();
         }
      }

      ProfileRenderer6.TypeQ var10 = this.notice;
      if (var10 != null && (this.time - var10.born()) * MotionStyle.MENU.speed() > var10.life()) {
         this.dismiss();
         var10 = null;
      }

      if (this.busy != null) {
         float var9 = this.time * 360.0F * 1.1F;
         var2.arc(var3 + 7.0F, var4, 5.5F, 1.6F, var9, 250.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.95F), BlendMode.withAlpha(Icons.MAGENTA, 0.1F), true);
         var2.text(Fonts.MEDIUM, this.busy + "…", var3 + 20.0F, var4, 11.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      } else if (var10 != null) {
         float var11 = Easing.EMPHASIZED.apply(Math.min(1.0F, (this.time - var10.born()) * MotionStyle.MENU.speed() / 0.35F));
         var2.pushAlpha(var11);
         var7 = this.message(var1, var2, var10, var3, var4 + (1.0F - var11) * 6.0F, var5, var6);
         var2.popAlpha();
      } else if (this.claiming) {
         var7 = this.claimLine(var1, var2, var3, var4, var5, var6);
      } else if (this.mode.selected() == 1) {
         var7 = this.usernameLine(var1, var2, var3, var4, var5, var6);
      } else {
         this.aroundLine(var1, var2, var3, var4, var5, var6);
      }

      if (!var7) {
         this.act.hide();
         this.act2.hide();
         this.actRun = null;
         this.act2Run = null;
      }
   }

   private boolean message(DrawContext var1, Canvas var2, ProfileRenderer6.TypeQ var3, float var4, float var5, float var6, boolean var7) {
      int var8 = var3.warn() ? -29785 : Icons.MAGENTA;
      var2.circle(var4 + 8.0F, var5, 9.0F, BlendMode.withAlpha(var8, 0.16F));
      var2.icon(var3.icon(), var4 + 8.0F, var5, 10.5F, var3.warn() ? -29785 : Theme.tint(-1783297));
      float var9 = var3.action() == null ? 0.0F : ProfileRenderer9.TypeB.width(var2, (String)null, var3.action(), 22.0F) + 8.0F;
      String var10 = FontMetrics.ellipsize(Fonts.MEDIUM, var3.text(), 11.0F, var6 - 24.0F - var9, 0.0F);
      float var11 = var2.text(Fonts.MEDIUM, var10, var4 + 24.0F, var5, 11.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      if (var3.action() != null && var3.run() != null) {
         this.act.render(var1, var4 + 24.0F + var11 + 8.0F, var5, 22.0F, (String)null, var3.action(), ProfileRenderer9.TypeI.TEXT, var7);
         this.act2.hide();
         Runnable var12 = var3.run();
         this.actRun = () -> {
            var12.run();
            this.dismiss();
            SoundRequest.play(SoundEvent.CLICK);
         };
         this.act2Run = null;
         return true;
      } else {
         return false;
      }
   }

   private void aroundLine(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6) {
      List var7 = Friends.strangers(8);
      if (var7.isEmpty()) {
         var2.icon("mouse-pointer-click", var3 + 7.0F, var4, 11.5F, Icons.TEXT_3);
         var2.text(
            Fonts.MEDIUM,
            "Tip: middle-click a player in game to add them as a friend",
            var3 + 22.0F,
            var4,
            11.0F,
            Icons.TEXT_3,
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
      } else {
         float var8 = var3 + var2.text(Fonts.MEDIUM, "Around you", var3, var4, 11.0F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE) + 12.0F;
         float var9 = var3 + var5;
         Translations.pause();

         for (GameProfile var11 : (Iterable<GameProfile>)(Object)(var7)) {
            String var12 = ClientRegistry5.plain(var11.name());
            float var13 = 24.0F + var2.textWidth(Fonts.MEDIUM, var12, 11.0F) + 6.0F + 12.0F + 10.0F;
            if (var8 + var13 > var9) {
               break;
            }

            ProfileRenderer6.TypeW var14 = this.chips.computeIfAbsent(var11.id(), var1x -> new ProfileRenderer6.TypeW(var11));
            var14.x = var8;
            var14.h = 24.0F;
            var14.y = var4 - var14.h / 2.0F;
            var14.w = var13;
            boolean var15 = var6 && var1.hover(var14, var14.x, var14.y, var14.w, var14.h, DrawContext.TypeE.HAND);
            float var16 = var14.hover.target(var15 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
            var2.roundRect(var14.x, var14.y, var14.w, var14.h, var14.h / 2.0F, BlendMode.lerp(251658239, BlendMode.withAlpha(Icons.VIOLET, 0.22F), var16));
            var2.roundRectOutline(
               var14.x, var14.y, var14.w, var14.h, var14.h / 2.0F, 1.0F, BlendMode.lerp(385875967, BlendMode.withAlpha(Icons.PURPLE, 0.6F), var16)
            );
            if (!FriendProfile.draw(var2, var11, var14.x + 4.0F, var4 - 8.0F, 16.0F, 4.0F, -1)) {
               var2.circle(var14.x + 12.0F, var4, 8.0F, 587202559);
            }

            var2.text(Fonts.MEDIUM, var12, var14.x + 26.0F, var4, 11.0F, BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var16), FontStyle.LEFT, FontWeight.MIDDLE);
            var2.icon("plus", var14.x + var14.w - 14.0F, var4, 11.0F, BlendMode.lerp(Icons.TEXT_3, Icons.TEXT, var16));
            this.shownChips.add(var14);
            var8 += var13 + 6.0F;
         }

         Translations.resume();
      }
   }

   private boolean usernameLine(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6) {
      String var7 = Friends.me().username;
      ProfileWebsite.TypeT var8 = ProfileWebsite.link();
      if (var8 != null) {
         var2.icon("link", var3 + 7.0F, var4, 11.5F, Theme.tint(-4611329));
         float var14 = var3
            + 22.0F
            + var2.text(Fonts.MEDIUM, "Confirm this code on purxiy.com:", var3 + 22.0F, var4, 11.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE)
            + 5.0F;
         Translations.pause();
         var14 += var2.text(Fonts.SEMIBOLD, var8.code(), var14, var4, 11.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE, 0.06F) + 10.0F;
         Translations.resume();
         var14 += this.act.render(var1, var14, var4, 22.0F, "external-link", "Open", ProfileRenderer9.TypeI.TEXT, var6) + 2.0F;
         this.act2.render(var1, var14, var4, 22.0F, (String)null, "Cancel", ProfileRenderer9.TypeI.TEXT, var6);
         this.actRun = ProfileWebsite::reopenLink;
         this.act2Run = ProfileWebsite::cancelLink;
         return true;
      } else if (FriendApi.offline() && var7 == null) {
         var2.icon("cloud-off", var3 + 7.0F, var4, 11.5F, -29785);
         var2.text(
            Fonts.MEDIUM,
            FontMetrics.ellipsize(Fonts.MEDIUM, "Usernames need the Purxiy server, which can't be reached right now", 11.0F, var5 - 22.0F, 0.0F),
            var3 + 22.0F,
            var4,
            11.0F,
            Icons.TEXT_2,
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
         return false;
      } else {
         if (var7 != null) {
            var2.icon("badge-check", var3 + 7.0F, var4, 11.5F, Theme.tint(-4611329));
            float var9 = var3 + 22.0F + var2.text(Fonts.MEDIUM, "You are", var3 + 22.0F, var4, 11.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE) + 4.0F;
            Translations.pause();
            var9 += var2.text(Fonts.SEMIBOLD, "@" + var7, var9, var4, 11.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
            Translations.resume();
            var9 += 10.0F;
            var9 += this.act.render(var1, var9, var4, 22.0F, "copy", "Copy", ProfileRenderer9.TypeI.TEXT, var6) + 2.0F;
            this.act2.render(var1, var9, var4, 22.0F, "pen-line", "Change", ProfileRenderer9.TypeI.TEXT, var6);
            this.actRun = () -> {
               class_310.method_1551().field_1774.method_1455("@" + var7);
               SoundRequest.play(SoundEvent.CLICK);
               this.say("copy", "Copied @" + var7 + ": send it to your friends");
            };
            this.act2Run = ProfileWebsite.linked() ? () -> ProfileWebsite.openSite("/dashboard/account") : this::startClaim;
         } else {
            var2.icon("at-sign", var3 + 7.0F, var4, 11.5F, Icons.TEXT_3);
            float var13 = var3
               + 22.0F
               + var2.text(
                  Fonts.MEDIUM, "Your username comes with your Purxiy account", var3 + 22.0F, var4, 11.0F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE
               )
               + 10.0F;
            this.act.render(var1, var13, var4, 22.0F, "link", "Link account", ProfileRenderer9.TypeI.TEXT, var6);
            this.act2.hide();
            this.actRun = ProfileWebsite::startLink;
            this.act2Run = null;
         }

         return true;
      }
   }

   private boolean claimLine(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6) {
      String var7 = Friends.me().username;
      float var8 = var3
         + var2.text(Fonts.MEDIUM, "3 to 16 letters, digits or _  ·  Esc to cancel", var3, var4, 11.0F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE)
         + 12.0F;
      if (var7 != null) {
         this.act.render(var1, var8, var4, 22.0F, (String)null, "Give up @" + var7, ProfileRenderer9.TypeI.DANGER, var6);
         this.act2.hide();
         this.actRun = this::release;
         this.act2Run = null;
         return true;
      } else {
         return false;
      }
   }

   private void list(DrawContext var1, Canvas var2, float var3, float var4, float var5, float var6, float var7, boolean var8) {
      ProfileRenderer9.card(var2, var3, var4, var5, var6, 0.0F);
      List var9 = Friends.list();
      int var10 = var9.size();
      String var11;
      if (var10 == 0) {
         var11 = "Nobody yet";
      } else {
         String var12 = var10 + (var10 == 1 ? " friend" : " friends");
         int var13 = Friends.here();
         var11 = class_310.method_1551().field_1687 == null
            ? var12
            : (var13 > 0 ? var12 + "  ·  " + var13 + " here now" : var12 + "  ·  nobody here right now");
      }

      ProfileRenderer9.title(var2, "Friends", var11, var3, var4, var5 - 40.0F);
      float var31 = var4 + 60.0F;
      float var32 = var4 + var6 - 10.0F;
      this.gridTop = var31;
      this.gridBottom = var32;
      this.sync(var9);
      if (var10 == 0 && this.leaving.isEmpty()) {
         this.maxScroll = 0.0F;
         this.empty(var2, var3, var31, var5, var32 - var31, var7);
      } else {
         ArrayList var14 = new ArrayList(var9);
         var14.sort(
            Comparator.<PlayerMetadata>comparingInt(var0 -> Friends.presence(var0).state().ordinal())
               .thenComparingLong(var0 -> Friends.presence(var0).state() == Friends.TypeF.OFFLINE ? -var0.seen() : 0L)
               .thenComparingLong(var0 -> -var0.added)
         );
         float var15 = var3 + 14.0F;
         float var16 = var5 - 28.0F;
         int var17 = Math.max(1, (int)((var16 + 10.0F) / 242.0F));
         float var18 = (var16 - (var17 - 1) * 10.0F) / var17;
         int var19 = (var14.size() + var17 - 1) / var17;
         float var20 = var19 * 64.0F + Math.max(0, var19 - 1) * 10.0F;
         this.maxScroll = Math.max(0.0F, var20 - (var32 - var31));
         this.scrollTarget = Math.max(0.0F, Math.min(this.maxScroll, this.scrollTarget));
         float var21 = this.scroll.target(this.scrollTarget).update(MotionStyle.MENU.dt(var1.dt));
         var2.pushClip(var3 + 4.0F, var31 - 6.0F, var5 - 8.0F, var32 - var31 + 12.0F);
         Iterator var22 = this.leaving.iterator();

         while (var22.hasNext()) {
            ProfileRenderer6.TypeK var23 = (ProfileRenderer6.TypeK)var22.next();
            float var24 = var23.appear.target(0.0F).update(MotionStyle.MENU.dt(var1.dt) * 1.4F);
            if (var24 <= 0.01F) {
               var22.remove();
            } else {
               this.tile(var1, var2, var23, var15 + var23.x.value(), var31 + var23.y.value() - var21, var18, var24, var31, var32, false);
            }
         }

         for (int var33 = 0; var33 < var14.size(); var33++) {
            ProfileRenderer6.TypeK var35 = this.tiles.get(((PlayerMetadata)var14.get(var33)).id);
            if (var35 != null) {
               float var25 = var33 % var17 * (var18 + 10.0F);
               float var26 = var33 / var17 * 74.0F;
               if (!var35.placed) {
                  var35.x.snap(var25);
                  var35.y.snap(var26);
                  var35.placed = true;
               }

               float var27 = var35.x.target(var25).update(MotionStyle.MENU.dt(var1.dt) * 0.8F);
               float var28 = var35.y.target(var26).update(MotionStyle.MENU.dt(var1.dt) * 0.8F);
               float var29 = this.entered ? 1.0F : Easing.EMPHASIZED.apply(ProfileCurve.clamp01((var7 - var33 * 0.04F) / 0.45F));
               float var30 = var35.appear.target(1.0F).update(MotionStyle.MENU.dt(var1.dt)) * var29;
               this.tile(var1, var2, var35, var15 + var27, var31 + var28 - var21, var18, var30, var31, var32, var8);
            }
         }

         if (var7 > var14.size() * 0.04F + 0.5F) {
            this.entered = true;
         }

         var2.popClip();
         if (this.maxScroll > 1.0F) {
            float var34 = var32 - var31 - 8.0F;
            float var36 = Math.max(28.0F, var34 * (var32 - var31) / var20);
            float var37 = var31 + 4.0F + (var34 - var36) * (var21 / this.maxScroll);
            var2.roundRect(var3 + var5 - 7.0F, var37, 2.5F, var36, 1.25F, BlendMode.withAlpha(-1, 0.16F));
         }
      }
   }

   private void sync(List<PlayerMetadata> var1) {
      HashMap var2 = new HashMap();

      for (PlayerMetadata var4 : var1) {
         var2.put(var4.id, var4);
         if (!this.tiles.containsKey(var4.id)) {
            ProfileRenderer6.TypeK var5 = new ProfileRenderer6.TypeK(var4, this.time);
            if (!this.entered) {
               var5.appear.snap(1.0F);
               var5.born = -100.0F;
            }

            this.tiles.put(var4.id, var5);
         }
      }

      Iterator var6 = this.tiles.entrySet().iterator();

      while (var6.hasNext()) {
         Entry var7 = (Entry)var6.next();
         if (!var2.containsKey(var7.getKey())) {
            ProfileRenderer6.TypeK var8 = (ProfileRenderer6.TypeK)var7.getValue();
            var8.leftAt = this.time;
            var8.br = 0.0F;
            this.leaving.add(var8);
            var6.remove();
         }
      }
   }

   private void tile(
      DrawContext var1, Canvas var2, ProfileRenderer6.TypeK var3, float var4, float var5, float var6, float var7, float var8, float var9, boolean var10
   ) {
      var3.br = 0.0F;
      if (!(var5 + 64.0F < var8 - 8.0F) && !(var5 > var9 + 8.0F) && !(var7 <= 0.005F)) {
         PlayerMetadata var11 = var3.friend;
         Friends.TypeI var12 = Friends.presence(var11);
         boolean var13 = var12.state() != Friends.TypeF.OFFLINE;
         float var14 = Math.min(var5 + 64.0F - var8 + 8.0F, var9 - var5 + 8.0F);
         float var15 = var7 * smooth(var14 / 46.0F);
         boolean var16 = var10 && var1.mouseY >= var8 - 4.0F && var1.mouseY <= var9 + 4.0F;
         boolean var17 = var16 && var1.hover(var3.key, var4, var5, var6, 64.0F, DrawContext.TypeE.ARROW);
         float var18 = var3.hover.target(var17 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         float var19 = Math.max(0.0F, 1.0F - (this.time - var3.born) / 1.6F);
         var2.pushAlpha(var15);
         var2.push();
         var2.scaleAround(0.94F + 0.06F * var7, var4 + var6 / 2.0F, var5 + 32.0F);
         var2.translate(0.0F, (1.0F - var7) * 8.0F - var18 * 1.5F);
         if (var19 > 0.01F) {
            var2.glow(var4, var5, var6, 64.0F, 14.0F, 22.0F, BlendMode.withAlpha(Icons.VIOLET, 0.42F * var19 * var19));
         }

         var2.roundRect(var4, var5, var6, 64.0F, 14.0F, BlendMode.lerp(201326591, 369098751, var18));
         var2.roundRectOutline(
            var4,
            var5,
            var6,
            64.0F,
            14.0F,
            1.0F,
            BlendMode.lerp(BlendMode.lerp(318767103, 654311423, var18), BlendMode.withAlpha(Icons.PURPLE, 0.7F), var19 * 0.8F)
         );
         float var20 = var4 + 12.0F;
         float var21 = var5 + 12.0F;
         if (var13) {
            float var22 = MotionStyle.reduced() ? 0.8F : 0.6F + 0.4F * (float)Math.sin(this.time * 2.6F);
            var2.glow(var20, var21, 40.0F, 40.0F, 10.0F, 14.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.3F * var22));
         }

         if (!FriendProfile.draw(var2, var11, var20, var21, 40.0F, 10.0F, -1)) {
            var2.roundRectGradient(var20, var21, 40.0F, 40.0F, 10.0F, 135.0F, Icons.INDIGO, Icons.PURPLE);
            Translations.pause();
            var2.text(Fonts.SEMIBOLD, initial(var11), var20 + 20.0F, var21 + 20.0F, 16.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
            Translations.resume();
         }

         var2.roundRectOutline(var20, var21, 40.0F, 40.0F, 10.0F, 1.0F, var13 ? BlendMode.withAlpha(Icons.PINK, 0.55F) : 486539263);
         if (var13) {
            float var33 = var20 + 40.0F - 2.0F;
            float var23 = var21 + 40.0F - 2.0F;
            float var24 = MotionStyle.reduced() ? 0.8F : 0.55F + 0.45F * (float)Math.sin(this.time * 3.2F);
            var2.circle(var33, var23, 5.5F, Theme.tint(-15593950));
            var2.glowCircle(var33, var23, 3.2F, 7.0F, BlendMode.withAlpha(Icons.PINK, 0.5F * var24));
            var2.circle(var33, var23, 3.2F, BlendMode.lerp(Icons.MAGENTA, Icons.PINK, var24));
         }

         float var34 = var20 + 40.0F + 12.0F;
         float var35 = var4 + var6 - var34 - 12.0F;
         Translations.pause();
         String var36 = FontMetrics.ellipsize(Fonts.SEMIBOLD, ClientRegistry5.plain(var11.label()), 13.0F, var35 - 22.0F, 0.0F);
         var2.text(Fonts.SEMIBOLD, var36, var34, var5 + 23.0F, 13.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
         Translations.resume();
         String var25 = this.status(var11, var12);
         int var26 = var13 ? Theme.tint(-804628) : (var11.lookup() == PlayerMetadata.TypeE.UNKNOWN ? BlendMode.withAlpha(-29785, 0.85F) : Icons.TEXT_2);
         var2.text(
            Fonts.REGULAR,
            FontMetrics.ellipsize(Fonts.REGULAR, var25, 11.0F, var35, 0.0F),
            var34,
            var5 + 42.0F,
            11.0F,
            var26,
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
         float var27 = Math.max(var18, var3.close.value());
         if (var27 > 0.01F && var3.leftAt < 0.0F) {
            float var28 = var4 + var6 - 17.0F;
            float var29 = var5 + 17.0F;
            float var30 = 10.0F;
            boolean var31 = var16 && var1.hover(var3.closeKey, var28 - var30, var29 - var30, var30 * 2.0F, var30 * 2.0F, DrawContext.TypeE.HAND);
            float var32 = var3.close.target(var31 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
            var2.pushAlpha(var27);
            var2.circle(var28, var29, var30, BlendMode.lerp(285212671, BlendMode.withAlpha(-29785, 0.2F), var32));
            var2.icon("x", var28, var29, 11.0F, BlendMode.lerp(Icons.TEXT_2, -29785, var32));
            var2.popAlpha();
            var3.bx = var28;
            var3.by = var29;
            var3.br = !var17 && !var31 ? 0.0F : var30;
         } else {
            var3.close.target(0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         }

         var2.pop();
         var2.popAlpha();
      }
   }

   private String status(PlayerMetadata var1, Friends.TypeI var2) {
      boolean var3 = var1.kind == PlayerMetadata.TypeI.PURXIY;
      String var4 = var3 && var2.info() != null ? " as " + ClientRegistry5.plain(var2.info().method_2966().name()) : "";
      switch (var2.state()) {
         case NEARBY:
            String var5 = var3 ? "Nearby" + var4 : "Nearby";
            return var5 + "  ·  " + Math.max(1, Math.round(var2.distance())) + " m";
         case ONLINE:
            return var3 ? "Online" + var4 : "Online here";
         default:
            if (!var3 && var1.lookup() == PlayerMetadata.TypeE.PENDING && var1.uuid() == null) {
               return "Looking up…";
            } else if (!var3 && var1.lookup() == PlayerMetadata.TypeE.UNKNOWN && var1.seen() == 0L) {
               return "Not a Minecraft account";
            } else if (var1.seen() <= 0L) {
               if (var3) {
                  if (var1.gone()) {
                     return "Gave up their username";
                  } else {
                     List var9 = var1.accounts();
                     if (var9.isEmpty()) {
                        return "No account known yet";
                     } else {
                        String var10 = ClientRegistry5.plain(((PlayerMetadata.TypeS)var9.getFirst()).name());
                        return var9.size() == 1 ? "Plays as " + var10 : "Plays as " + var10 + " and " + (var9.size() - 1) + " more";
                     }
                  }
               } else {
                  Instant var8 = Instant.ofEpochMilli(var1.added);
                  return "Friends since " + ProfileSupport2.date(LocalDate.ofInstant(var8, ZoneId.systemDefault()));
               }
            } else {
               String var6 = var1.place();
               String var7 = ago(var1.seen());
               return "Seen " + var7 + (var6 != null && !var6.isBlank() ? "  ·  " + var6 : "");
            }
      }
   }

   private static String ago(long var0) {
      long var2 = Math.max(0L, (System.currentTimeMillis() - var0) / 60000L);
      if (var2 < 1L) {
         return "just now";
      } else if (var2 < 60L) {
         return var2 + " min ago";
      } else {
         long var4 = var2 / 60L;
         if (var4 < 24L) {
            return var4 + " h ago";
         } else {
            long var6 = var4 / 24L;
            if (var6 < 7L) {
               return var6 + (var6 == 1L ? " day ago" : " days ago");
            } else {
               Instant var8 = Instant.ofEpochMilli(var0);
               return "on " + ProfileSupport2.date(LocalDate.ofInstant(var8, ZoneId.systemDefault()));
            }
         }
      }
   }

   private static String initial(PlayerMetadata var0) {
      String var1 = ClientRegistry5.plain(var0.name());
      return var1.isEmpty() ? "?" : var1.substring(0, 1).toUpperCase(Locale.ROOT);
   }

   private void empty(Canvas var1, float var2, float var3, float var4, float var5, float var6) {
      float var7 = Easing.EMPHASIZED.apply(ProfileCurve.clamp01((var6 - 0.1F) / 0.6F));
      float var8 = var2 + var4 / 2.0F;
      float var9 = var3 + var5 * 0.4F + (1.0F - var7) * 6.0F;
      float var10 = MotionStyle.reduced() ? 1.0F : 0.85F + 0.15F * (float)Math.sin(this.time * 1.2F);
      var1.pushAlpha(var7);
      var1.glowCircle(var8, var9, 30.0F, 34.0F, BlendMode.withAlpha(Icons.VIOLET, 0.2F * var10));
      var1.circle(var8, var9, 30.0F, 318767103);
      var1.arc(var8, var9, 30.5F, 1.2F, -90.0F, 360.0F, BlendMode.withAlpha(Icons.PURPLE, 0.6F), BlendMode.withAlpha(Icons.INDIGO, 0.2F), false);
      var1.icon("users-round", var8, var9, 24.0F, Icons.TEXT_2);
      var1.text(Fonts.SEMIBOLD, "No friends yet", var8, var9 + 52.0F, 14.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
      float var11 = Math.min(var4 - 60.0F, 430.0F);
      var1.paragraph(
         Fonts.REGULAR,
         "Add someone above by their Minecraft name, or middle-click a player in game",
         var8 - var11 / 2.0F,
         var9 + 66.0F,
         11.5F,
         var11,
         1.45F,
         Icons.TEXT_2,
         FontStyle.CENTER
      );
      var1.popAlpha();
   }

   private void submit() {
      if (this.busy == null) {
         if (this.claiming) {
            this.claim();
         } else if (this.mode.selected() == 0) {
            Friends.TypeK var1 = Friends.addPlayer(this.text);
            if (var1.problem() != null) {
               this.warn(var1.problem());
            } else {
               PlayerMetadata var2 = var1.friend();
               this.text = "";
               this.field.focus();
               if (var1.existed()) {
                  this.say("user-check", ClientRegistry5.plain(var2.label()) + " is already your friend");
                  this.light(var2);
               } else {
                  SoundRequest.play(SoundEvent.TOGGLE_ON);
                  this.say("user-plus", "Added " + ClientRegistry5.plain(var2.name()), "Undo", () -> Friends.remove(var2));
               }
            }
         } else {
            String var5 = NamePolicy.clean(this.text, true);
            String var6 = NamePolicy.usernameProblem(var5);
            if (var6 == null && var5.equalsIgnoreCase(Friends.me().username == null ? "" : Friends.me().username)) {
               var6 = "That is you";
            }

            if (var6 != null) {
               this.warn(var6);
            } else {
               for (PlayerMetadata var4 : Friends.list()) {
                  if (var4.kind == PlayerMetadata.TypeI.PURXIY && var4.name().equalsIgnoreCase(var5)) {
                     this.text = "";
                     this.say("user-check", var4.label() + " is already your friend");
                     this.light(var4);
                     return;
                  }
               }

               this.busy = "Looking up @" + var5;
               this.field.blur();
               FriendApi.lookup(var5).whenComplete((var2x, var3) -> class_310.method_1551().execute(() -> {
                  this.busy = null;
                  if (var2x == null) {
                     this.warn("Can't reach the Purxiy server: add them by their Minecraft name for now");
                  } else {
                     switch (var2x.status()) {
                        case OK:
                           String var3x = var2x.username() == null ? var5 : var2x.username();
                           Friends.TypeK var4x = Friends.addPurxiy(var3x, var2x.accounts());
                           this.text = "";
                           PlayerMetadata var5x = var4x.friend();
                           SoundRequest.play(SoundEvent.TOGGLE_ON);
                           String var6x = var2x.accounts().isEmpty() ? "" : "  ·  plays as " + ClientRegistry5.plain(var2x.accounts().getFirst().name());
                           this.say("user-plus", "Added @" + var3x + var6x, "Undo", () -> Friends.remove(var5x));
                           break;
                        case TAKEN:
                        default:
                           this.warn("Usernames are 3 to 16 letters, digits or _");
                           break;
                        case LIMITED:
                           this.warn("Too many tries: wait a moment");
                           break;
                        case OFFLINE:
                           this.warn("Can't reach the Purxiy server: add them by their Minecraft name for now");
                           break;
                        case NOT_FOUND:
                           this.warn("Nobody has the username @" + var5);
                           break;
                        case LOCKED:
                           this.warn("Link your Purxiy account first: then you can add friends by username");
                     }
                  }
               }));
            }
         }
      }
   }

   private void startClaim() {
      this.claiming = true;
      this.dismiss();
      String var1 = Friends.me().username;
      this.text = var1 == null ? "" : var1;
      this.field.focus();
      this.field.selectAll();
      SoundRequest.play(SoundEvent.EXPAND);
   }

   private void endClaim() {
      this.claiming = false;
      this.text = "";
      this.field.blur();
      SoundRequest.play(SoundEvent.COLLAPSE);
   }

   private void claim() {
      String var1 = NamePolicy.clean(this.text, true);
      String var2 = NamePolicy.usernameProblem(var1);
      if (var2 != null) {
         this.warn(var2);
      } else if (var1.equals(Friends.me().username)) {
         this.endClaim();
      } else {
         this.busy = "Saving @" + var1;
         this.field.blur();
         FriendApi.claim(var1).whenComplete((var2x, var3) -> class_310.method_1551().execute(() -> {
            this.busy = null;
            FriendApi.TypeT var3x = var2x == null ? FriendApi.TypeT.OFFLINE : var2x.status();
            switch (var3x) {
               case OK:
                  this.claiming = false;
                  this.text = "";
                  SoundRequest.play(SoundEvent.TOGGLE_ON);
                  this.say("badge-check", "You are @" + Friends.me().username + " now: friends can add you by it");
                  break;
               case TAKEN:
                  this.warn("@" + var1 + " is taken: try another");
                  break;
               case LIMITED:
                  this.warn("Too many tries: wait a moment");
                  break;
               case OFFLINE:
                  this.warn("Can't reach the Purxiy server right now");
                  break;
               default:
                  this.warn("That username can't be used: try another");
            }

            if (this.claiming) {
               this.field.focus();
            }
         }));
      }
   }

   private void release() {
      String var1 = Friends.me().username;
      if (var1 != null && this.busy == null) {
         this.busy = "Saving @" + var1;
         FriendApi.release().whenComplete((var2, var3) -> class_310.method_1551().execute(() -> {
            this.busy = null;
            if (var2 != null && var2.status() == FriendApi.TypeT.OK) {
               this.claiming = false;
               this.text = "";
               this.say("at-sign", "You gave up @" + var1);
            } else {
               this.warn("Can't reach the Purxiy server right now");
            }
         }));
      }
   }

   private void remove(PlayerMetadata var1) {
      int var2 = Friends.remove(var1);
      if (var2 >= 0) {
         SoundRequest.play(SoundEvent.TOGGLE_OFF);
         this.say("user-minus", "Removed " + ClientRegistry5.plain(var1.label()), "Undo", () -> Friends.restore(var1, var2));
      }
   }

   private void light(PlayerMetadata var1) {
      ProfileRenderer6.TypeK var2 = this.tiles.get(var1.id);
      if (var2 != null) {
         var2.born = this.time;
      }
   }

   private void say(String var1, String var2) {
      this.show(new ProfileRenderer6.TypeQ(var1, var2, false, (String)null, (Runnable)null, this.time, 3.2F));
   }

   private void say(String var1, String var2, String var3, Runnable var4) {
      this.show(new ProfileRenderer6.TypeQ(var1, var2, false, var3, var4, this.time, 6.0F));
   }

   private void warn(String var1) {
      SoundRequest.play(SoundEvent.DENY);
      this.show(new ProfileRenderer6.TypeQ("circle-alert", var1, true, (String)null, (Runnable)null, this.time, 4.0F));
   }

   private void show(ProfileRenderer6.TypeQ var1) {
      if (this.notice != null) {
         this.fading = this.notice;
         this.fadedAt = this.time;
      }

      this.notice = var1;
   }

   private void dismiss() {
      if (this.notice != null) {
         this.fading = this.notice;
         this.fadedAt = this.time;
         this.notice = null;
      }
   }

   boolean mouseDown(float var1, float var2, int var3) {
      if (this.busy == null && this.actRun != null && this.act.hit(var1, var2)) {
         this.act.press();
         this.actRun.run();
         return true;
      } else if (this.busy == null && this.act2Run != null && this.act2.hit(var1, var2)) {
         this.act2.press();
         this.act2Run.run();
         return true;
      } else if (!this.claiming && this.busy == null && this.mode.mouseDown(var1, var2)) {
         lastMode = this.mode.selected();
         this.dismiss();
         if (this.mode.selected() == 1) {
            FriendApi.refreshSoon();
         }

         this.field.focus();
         return true;
      } else if (this.add.hit(var1, var2)) {
         this.add.press();
         SoundRequest.play(SoundEvent.CLICK);
         this.submit();
         return true;
      } else if (this.busy == null && this.field.mouseDown(var1, var2)) {
         return true;
      } else {
         for (ProfileRenderer6.TypeW var5 : this.shownChips) {
            if (var1 >= var5.x && var1 <= var5.x + var5.w && var2 >= var5.y && var2 <= var5.y + var5.h) {
               Friends.TypeK var6 = Friends.addPlayer(var5.profile);
               if (var6.friend() != null && !var6.existed()) {
                  SoundRequest.play(SoundEvent.TOGGLE_ON);
                  PlayerMetadata var7 = var6.friend();
                  this.say("user-plus", "Added " + ClientRegistry5.plain(var7.name()), "Undo", () -> Friends.remove(var7));
               }

               return true;
            }
         }

         if (var2 >= this.gridTop - 4.0F && var2 <= this.gridBottom + 4.0F) {
            for (ProfileRenderer6.TypeK var9 : this.tiles.values()) {
               float var10 = var1 - var9.bx;
               float var11 = var2 - var9.by;
               if (var9.br > 0.0F && var10 * var10 + var11 * var11 <= var9.br * var9.br) {
                  this.remove(var9.friend);
                  return true;
               }
            }
         }

         return false;
      }
   }

   void mouseDrag(float var1, float var2) {
      this.field.mouseDrag(var1, var2);
   }

   void mouseUp() {
      this.field.mouseUp();
   }

   boolean scroll(double var1) {
      if (this.maxScroll <= 0.0F) {
         return false;
      } else {
         this.scrollTarget = (float)Math.max(0.0, Math.min((double)this.maxScroll, this.scrollTarget - var1 * 48.0));
         return true;
      }
   }

   boolean keyPressed(int var1, boolean var2) {
      if (this.field.focused()) {
         if (var1 == 256 && this.claiming) {
            this.endClaim();
            return true;
         } else {
            this.field.keyPressed(var1);
            return true;
         }
      } else if (var1 == 256 && this.claiming && this.busy == null) {
         this.endClaim();
         return true;
      } else if (var2 && var1 == 86 && this.busy == null) {
         this.field.focus();
         this.field.keyPressed(var1);
         return true;
      } else {
         return false;
      }
   }

   boolean charTyped(int var1) {
      if (this.field.focused()) {
         this.field.charTyped(var1);
         return true;
      } else if (this.busy == null && var1 != 47 && !Character.isWhitespace(var1) && !Character.isISOControl(var1)) {
         this.field.focus();
         this.field.charTyped(var1);
         return true;
      } else {
         return false;
      }
   }

   void dev(String var1, String var2) {
      switch (var1) {
         case "type":
            this.text = var2;
            this.field.focus();
            break;
         case "submit":
            this.submit();
            break;
         case "mode":
            this.mode.select(Integer.parseInt(var2));
            lastMode = this.mode.selected();
            this.dismiss();
            break;
         case "remove":
            for (PlayerMetadata var6 : Friends.list()) {
               if (var6.name().equalsIgnoreCase(var2)) {
                  this.remove(var6);
                  return;
               }
            }
            break;
         case "undo":
            if (this.actRun != null) {
               this.actRun.run();
            }
            break;
         case "claim":
            this.startClaim();
            break;
         case "blur":
            this.field.blur();
            break;
         case "scroll":
            this.scrollTarget = Float.parseFloat(var2);
      }
   }

   private static float smooth(float var0) {
      var0 = var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static final class TypeK {
      final PlayerMetadata friend;
      final Animator x = Animator.smooth(0.0F);
      final Animator y = Animator.smooth(0.0F);
      final Animator appear = Animator.smooth(0.0F);
      final Animator hover = Animator.smooth(0.0F);
      final Animator close = Animator.smooth(0.0F);
      final Object key = new Object();
      final Object closeKey = new Object();
      float born;
      float leftAt = -1.0F;
      boolean placed;
      float bx;
      float by;
      float br;

      TypeK(PlayerMetadata var1, float var2) {
         this.friend = var1;
         this.born = var2;
      }
   }

   private record TypeQ(String icon, String text, boolean warn,  String action,  Runnable run, float born, float life) {
   }

   private static final class TypeW {
      final GameProfile profile;
      final Animator hover = Animator.smooth(0.0F);
      float x;
      float y;
      float w;
      float h;

      TypeW(GameProfile var1) {
         this.profile = var1;
      }
   }
}
