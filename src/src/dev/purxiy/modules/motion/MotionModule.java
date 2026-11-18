// Sourced By DevFFNightmare
package dev.purxiy.modules.motion;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.PreferenceModule;

public final class MotionModule extends PreferenceModule {
   public final NumberSetting k = this.number(
      "global", "Overall speed", "Scales every animation in Purxiy", 1.0, 0.5, 2.0, 0.05, NumberSetting.Listener.MULTIPLIER
   );
   public final NumberSetting C = this.number(
      "menu", "Menu transitions", "Opening, closing and moving between views", 1.0, 0.5, 2.0, 0.05, NumberSetting.Listener.MULTIPLIER
   );
   public final NumberSetting G = this.number(
      "orbit", "Orbit", "How quickly the ring turns to a category", 1.0, 0.5, 2.0, 0.05, NumberSetting.Listener.MULTIPLIER
   );
   public final NumberSetting s = this.number("hover", "Hover", "Highlights that follow your cursor", 1.0, 0.5, 2.0, 0.05, NumberSetting.Listener.MULTIPLIER);
   public final NumberSetting t = this.number(
      "toggles", "Switches", "Switches, sliders and button presses", 1.0, 0.5, 2.0, 0.05, NumberSetting.Listener.MULTIPLIER
   );
   public final NumberSetting u = this.number("panels", "Panels", "Settings panels opening and closing", 1.0, 0.5, 2.0, 0.05, NumberSetting.Listener.MULTIPLIER);
   public final NumberSetting q = this.number(
      "cascade", "Cascade", "Modules flowing in one after another", 1.0, 0.5, 2.0, 0.05, NumberSetting.Listener.MULTIPLIER
   );
   public final BooleanSetting g = this.bool("reduce", "Reduce motion", "Calmer: no parallax, floating or cascades", false);
   public static boolean f;
   public static boolean Z;



   public MotionModule() {
      super("motion", "Motion", "Animation speeds for every kind of animation", "waves");
   }

   @Override
   protected AutoCloseable apply(ModuleContext var1) {
      AutoCloseable var2 = var1.own(
         ClientServices.leaseMotion(
            this.k.floatValue(),
            this.g.enabled(),
            this.C.floatValue(),
            this.G.floatValue(),
            this.s.floatValue(),
            this.t.floatValue(),
            this.u.floatValue(),
            this.q.floatValue()
         )
      );
      var1.metric("applied", 1.0);
      return var2;
   }

}
