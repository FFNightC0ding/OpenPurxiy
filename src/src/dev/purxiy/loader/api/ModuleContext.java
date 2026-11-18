// Sourced By DevFFNightmare
package dev.purxiy.loader.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

public interface ModuleContext {
   Map<String, String> settings();

   AutoCloseable onFrame(DoubleConsumer var1);

   AutoCloseable onHud(Consumer<HudCanvas> var1);

   default AutoCloseable onHudLayout(Consumer<HudCanvas> callback) {
      throw new UnsupportedOperationException("HUD layout hooks unavailable");
   }

   default AutoCloseable hideHudLayers(Set<HudLayer> layers) {
      throw new UnsupportedOperationException("HUD layer hooks unavailable");
   }

   default AutoCloseable onWorldFrame(Consumer<WorldView> callback) {
      throw new UnsupportedOperationException("World hooks unavailable");
   }

   default AutoCloseable onWorldTime(WorldTimeTransform callback) {
      throw new UnsupportedOperationException("World time hooks unavailable");
   }

   default void refreshWorld() {
      throw new UnsupportedOperationException("World refresh unavailable");
   }

   <T extends AutoCloseable> T own(T var1);

   InputStream resource(String var1) throws IOException;

   void metric(String var1, double var2);
}
