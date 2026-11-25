package dev.purxiy.modules.zoom;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_310;
import net.minecraft.class_638;

public final class ZoomModule extends ModuleDescriptor {
   public final ModeSetting x;
   public final NumberSetting G;
   public final NumberSetting X;
   public final NumberSetting z;
   public final NumberSetting t;
   public final NumberSetting E;
   public final BooleanSetting e;
   public final BooleanSetting w;
   public final BooleanSetting R;
   public final BooleanSetting p;
   public final BooleanSetting o;
   public final BooleanSetting V;
   public final BooleanSetting l;
   private final ZoomWidget v = new ZoomWidget();
   private class_638 m;
   private long s;
   private int r = -1;
   private String u = "";

   public ZoomModule() {
      super("zoom", "Zoom", "A smooth lens, one key away", "zoom-in");
      this.setKey(67);
      this.enabledByDefault();
      this.section("Controls");
      this.x = this.mode("activation", "Activation", "Hold the key or click to toggle", "Hold", new String[]{"Hold", "Toggle"});
      this.G = this.number("factor", "Zoom amount", "Starting magnification", 4.0, 1.25, 32.0, 0.05, Listener.MULTIPLIER);
      this.section("Animation");
      this.e = this.bool("smooth", "Smooth zoom", "Ease gently into and out of focus", true);
      this.X = this.number("duration", "Zoom in", "Time to settle into focus", 240.0, 80.0, 800.0, 10.0, Listener.unit(" ms"));
      this.X.visibleWhen(this.e::enabled);
      this.z = this.number("release_duration", "Zoom out", "Time to return to your normal view", 180.0, 80.0, 800.0, 10.0, Listener.unit(" ms"));
      this.z.visibleWhen(this.e::enabled);
      this.section("Scroll wheel");
      this.w = this.bool("scroll", "Scroll to adjust", "Fine-tune zoom without changing slots", true);
      this.t = this.number("scroll_speed", "Scroll step", "Magnification change per wheel notch", 0.15, 0.02, 0.5, 0.01, Listener.PERCENT);
      this.t.visibleWhen(this.w::enabled);
      this.R = this.bool("invert_scroll", "Reverse scroll", "Scroll down to move closer", false);
      this.R.visibleWhen(this.w::enabled);
      this.p = this.bool("remember", "Remember zoom", "Keep your scrolled amount for next time", false);
      this.p.visibleWhen(this.w::enabled);
      this.section("Camera & display");
      this.o = this.bool("auto_sensitivity", "Adaptive sensitivity", "Keep aiming precise at every distance", true);
      this.E = this.number("sensitivity", "Aim speed", "Fine-tune mouse speed while zoomed", 1.0, 0.1, 2.0, 0.05, Listener.PERCENT);
      this.E.visibleWhen(this.o::enabled);
      this.V = this.bool("hide_hand", "Hide hand", "Keep held items out of the view", true);
      this.l = this.bool("indicator", "Zoom indicator", "A small magnification readout", true);
   }

   public boolean keybindToggles() {
      return false;
   }

   protected void onDisable() {
      this.v.reset();
   }

   private boolean t(long var1, class_310 var3, char var4) {
      ZoomRenderer.ensureSourceClassInitialized();
      if (this.enabled() && this.key() != -1) {
         char var5 = '龜';
         if (var3.field_1687 != null) {
            var5 = '龜';
            if (var3.field_1724 != null) {
               long var6 = 1833046364L;
               int var8 = var6 < 0L ? -1 : 1;
               if (var3.field_1724.method_5805() && var3.field_1755 == null) {
                  long var8x = 1833046365L;
                  int var10 = var8x < 0L ? -1 : 1;
                  if (var3.method_18506() == null && var3.method_1569() && var3.field_1729.method_1613()) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private boolean c(long var1, class_310 var3) {
      long var4;
      char var6;
      label38: {
         var4 = 1833046365L;
         var6 = '龜';
         ZoomRenderer.ensureSourceClassInitialized();
         if (var3.field_1687 == this.m && this.key() == this.r) {
            long var7 = 43136833385865L;
            int var9 = var7 < 0L ? -1 : 1;
            if (((String)this.x.get()).equals(this.u)) {
               break label38;
            }
         }

         this.v.reset();
         this.m = var3.field_1687;
         this.r = this.key();
         this.u = (String)this.x.get();
      }

      boolean var11 = this.t(var4, var3, var6);
      if (!var11) {
         this.v.cancel();
      }

      long var8 = 43136833385865L;
      int var10 = var8 < 0L ? -1 : 1;
      if (var3.field_1687 == null || var3.field_1724 == null || !var3.method_1569()) {
         this.v.reset();
      }

      return var11;
   }

   public void onKey(int var1, int var2) {
      long var3 = 37032667870528L;
      long var5 = var3 ^ 7240318845130L;
      boolean var7 = this.c(var5, class_310.method_1551());
      if (var1 == this.key() && var1 != -1) {
         if (var2 == 0) {
            this.v.release(this.x.is("Toggle"));
         } else if (var2 == 1 && var7) {
            this.v.press(this.x.is("Toggle"), (Double)this.G.get());
         }
      }
   }

   public boolean onScroll(double var1, double var3) {
      long var5 = 57691425668977L;
      long var7 = var5 ^ 21181827615483L;
      if (this.c(var7, class_310.method_1551()) && this.v.active() && this.w.enabled()) {
         double var9 = var3 == 0.0 ? -var1 : var3;
         if (Double.isFinite(var9) && var9 != 0.0) {
            this.v.scroll(this.R.enabled() ? -var9 : var9, (Double)this.t.get());
            if (this.p.enabled()) {
               this.G.set(this.v.targetFactor());
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void frame() {
      long var1 = 72891950153362L;
      long var3 = var1 ^ 111535626832664L;
      class_310 var5 = class_310.method_1551();
      this.c(var3, var5);
      long var6 = System.nanoTime();
      double var8 = this.s == 0L ? 0.0 : Math.max(0.0, (var6 - this.s) / 1.0E9);
      this.s = var6;
      double var10 = !this.e.enabled() ? 0.0 : (this.v.active() ? (Double)this.X.get() : (Double)this.z.get() / 1000.0);
      this.v.advance(var8, var10);
   }

   public double magnification() {
      return this.v.factor();
   }

   public boolean active() {
      return this.v.active();
   }

   public float presence() {
      return (float)Math.min(1.0, Math.log(this.magnification()) / Math.log(1.25));
   }

   public boolean hidesHand() {
      return this.V.enabled() && this.magnification() > 1.01;
   }

   public double mouseScale() {
      return this.o.enabled() && !(this.magnification() <= 1.0) ? (1.0 + ((Double)this.E.get() - 1.0) * this.presence()) / this.magnification() : 1.0;
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ModuleWidget.onKey((var1x, var2, var3) -> this.onKey(var1x, var3)));
      var1.own(ModuleWidget.onScroll(100, this::onScroll));
      var1.own(ModuleWidget.onFov(100, var1x -> ZoomWidget.zoomFov((float)var1x, this.magnification())));
      var1.own(ModuleWidget.onMouseScale(100, var1x -> var1x * this.mouseScale()));
      var1.own(ModuleWidget.onHideHand(this::hidesHand));
      var1.own(ModuleWidget.onDisconnect(var1x -> {
         this.v.reset();
         this.m = null;
         this.s = 0L;
      }));
      var1.own(ClientServices.acquire(this.id()));
      var1.onFrame(var2 -> {
         ZoomRenderer.ensureSourceClassInitialized();
         this.frame();
         int var4 = 44345;
         long var5 = 104239058067833L;
         int var7 = var5 < 0L ? -1 : 1;
         var4 = 201793913;
         var1.metric("magnification", this.magnification());
         var1.metric("active", this.active() ? 1.0 : 0.0);
      });
      var1.onHud(var1x -> {
         if (ClientServices.drawing()) {
            ZoomRenderer.render(this);
         }
      });
   }
}
