package dev.purxiy.modules.shockwave;

import dev.purxiy.loader.fabric.StudioSettingsScreen;

final class ShockwaveSupport extends StudioSettingsScreen {
   ShockwaveSupport(ShockwaveModule var1) {
      super(var1, ShockwaveRenderer4.of(var1));
   }
}
