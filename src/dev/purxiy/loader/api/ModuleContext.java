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
      return () -> { };
   }

   default AutoCloseable hideHudLayers(Set<HudLayer> layers) {
      return () -> { };
   }

   default AutoCloseable onWorldFrame(Consumer<WorldView> callback) {
      return () -> { };
   }

   default AutoCloseable onWorldTime(WorldTimeTransform callback) {
      return () -> { };
   }

   default void refreshWorld() {

   }

   <T extends AutoCloseable> T own(T var1);

   InputStream resource(String var1) throws IOException;

   void metric(String var1, double var2);
}
