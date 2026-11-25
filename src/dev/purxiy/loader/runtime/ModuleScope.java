package dev.purxiy.loader.runtime;

import dev.purxiy.loader.api.HudCanvas;
import dev.purxiy.loader.api.HudLayer;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.api.WorldTimeTransform;
import dev.purxiy.loader.api.WorldView;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

final class ModuleScope implements ModuleContext, AutoCloseable {
   private final OwnerThread owner = new OwnerThread();
   private final FrameRouter router;
   private final Map<String, String> settings;
   private final BiConsumer<String, Double> metrics;
   private final ArrayList<AutoCloseable> hooks = new ArrayList<>();
   private final ArrayList<AutoCloseable> resources = new ArrayList<>();
   private MemoryModuleClassLoader loader;
   private boolean closing;
   private boolean closed;

   ModuleScope(FrameRouter router, MemoryModuleClassLoader loader, Map<String, String> settings, BiConsumer<String, Double> metrics) {
      this.router = router;
      this.loader = loader;
      this.settings = Map.copyOf(settings);
      this.metrics = metrics;
   }

   private void requireAccess() {
      this.owner.check();
      if (this.closed) {
         throw new IllegalStateException("Module scope is closed");
      }
   }

   private void requireOpen() {
      this.requireAccess();
      if (this.closing) {
         throw new IllegalStateException("Module scope is closing");
      }
   }

   @Override
   public Map<String, String> settings() {
      this.requireAccess();
      return this.settings;
   }

   @Override
   public AutoCloseable onFrame(DoubleConsumer callback) {
      this.requireOpen();
      AutoCloseable registration = this.router.subscribe(callback);
      this.hooks.add(registration);
      return registration;
   }

   @Override
   public <T extends AutoCloseable> T own(T resource) {
      this.requireOpen();
      this.resources.add(Objects.requireNonNull(resource));
      return resource;
   }

   @Override
   public AutoCloseable onHud(Consumer<HudCanvas> callback) {
      this.requireOpen();
      AutoCloseable registration = this.router.subscribeHud(callback);
      this.hooks.add(registration);
      return registration;
   }

   @Override
   public AutoCloseable hideHudLayers(Set<HudLayer> layers) {
      this.requireOpen();
      AutoCloseable registration = this.router.suppressHud(layers);
      this.hooks.add(registration);
      return registration;
   }

   @Override
   public AutoCloseable onHudLayout(Consumer<HudCanvas> callback) {
      this.requireOpen();
      AutoCloseable registration = this.router.subscribeHudLayout(callback);
      this.hooks.add(registration);
      return registration;
   }

   @Override
   public AutoCloseable onWorldFrame(Consumer<WorldView> callback) {
      this.requireOpen();
      AutoCloseable registration = this.router.subscribeWorld(callback);
      this.hooks.add(registration);
      return registration;
   }

   @Override
   public AutoCloseable onWorldTime(WorldTimeTransform callback) {
      this.requireOpen();
      AutoCloseable registration = this.router.subscribeTime(callback);
      this.hooks.add(registration);
      return registration;
   }

   @Override
   public void refreshWorld() {
      this.requireAccess();
      this.router.requestWorldRefresh();
   }

   @Override
   public InputStream resource(String name) throws IOException {
      this.requireOpen();
      InputStream stream = this.loader.moduleResource(name);
      if (stream == null) {
         throw new IOException("Missing module resource: " + name);
      } else {
         return this.own(stream);
      }
   }

   @Override
   public void metric(String name, double value) {
      this.requireAccess();
      if (name.matches("[a-z][a-z0-9_.-]{0,95}") && Double.isFinite(value)) {
         this.metrics.accept(name, value);
      } else {
         throw new IllegalArgumentException("Invalid numeric metric");
      }
   }

   void detach() {
      this.owner.check();
      this.router.requireIdle();
      this.closing = true;
      closeAll(this.hooks);
   }

   @Override
   public void close() throws Exception {
      this.owner.check();
      if (!this.closed) {
         this.detach();

         try {
            closeAll(this.resources);
         } finally {
            this.closed = true;
            this.loader = null;
         }
      }
   }

   private static void closeAll(ArrayList<AutoCloseable> owned) {
      int failed = 0;

      try {
         for (int i = owned.size() - 1; i >= 0; i--) {
            try {
               owned.get(i).close();
            } catch (LinkageError | AssertionError | Exception var7) {
               failed++;
            }
         }
      } finally {
         owned.clear();
      }

      if (failed != 0) {
         throw new IllegalStateException("Module cleanup failed for " + failed + " resource(s)");
      }
   }
}
