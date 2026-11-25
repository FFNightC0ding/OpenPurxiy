package dev.purxiy.modules.jumpcircles;

import dev.purxiy.loader.fabric.StudioSettingsScreen;

final class JumpCirclesSupport extends StudioSettingsScreen {
   JumpCirclesSupport(JumpCirclesModule var1) {
      super(var1, JumpCirclesRenderer.of(var1));
   }
}
