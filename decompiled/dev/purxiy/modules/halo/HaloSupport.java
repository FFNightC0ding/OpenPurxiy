package dev.purxiy.modules.halo;

import dev.purxiy.loader.fabric.StudioSettingsScreen;

final class HaloSupport extends StudioSettingsScreen {
   HaloSupport(HaloModule var1) {
      super(var1, HaloRenderer3.of(var1));
   }
}
