package dev.purxiy.modules.sound;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.PreferenceModule;

public final class SoundModule extends PreferenceModule {
   public final BooleanSetting F = this.bool("enabled", "Interface sounds", "Soft sounds for every interaction", true);
   public final NumberSetting A = this.number("volume", "Volume", null, 0.6, 0.0, 1.0, 0.01, Listener.PERCENT);
   public final BooleanSetting N = this.bool("hover", "Hover ticks", "A faint tick when your cursor finds something", true);
   public final BooleanSetting Z = this.bool("ticks", "Slider & typing ticks", "Tiny detents while dragging and typing", true);
   public final BooleanSetting J = this.bool("transitions", "Transitions", "Opening, closing and panel sounds", true);

   public SoundModule() {
      super("sound", "Sound", "Volume and which sounds you hear", "volume-2");
      this.A.visibleWhen(this.F::enabled);
      this.N.visibleWhen(this.F::enabled);
      this.Z.visibleWhen(this.F::enabled);
      this.J.visibleWhen(this.F::enabled);
   }

   protected AutoCloseable apply(ModuleContext var1) {
      return var1.own(SoundRegistry.install(this, var1));
   }
}
