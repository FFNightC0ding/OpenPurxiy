// Sourced By DevFFNightmare
package dev.purxiy.loader.runtime;

import dev.purxiy.loader.api.HudCanvas;
import dev.purxiy.loader.api.HudLayer;
import dev.purxiy.loader.api.WorldTimeTransform;
import dev.purxiy.loader.api.WorldView;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

public final class FrameRouter {
   private final OwnerThread owner = new OwnerThread();
   private final ArrayList<FrameRouter.Subscription> subscriptions = new ArrayList<>();
   private FrameRouter.Subscription[] snapshot = new FrameRouter.Subscription[0];
   private int dispatchDepth;
   private final ArrayList<FrameRouter.HudSubscription> hud = new ArrayList<>();
   private FrameRouter.HudSubscription[] hudSnapshot = new FrameRouter.HudSubscription[0];
   private final ArrayList<FrameRouter.HudSubscription> hudLayouts = new ArrayList<>();
   private FrameRouter.HudSubscription[] hudLayoutSnapshot = new FrameRouter.HudSubscription[0];
   private final ArrayList<FrameRouter.HudSuppression> hudSuppressions = new ArrayList<>();
   private final EnumSet<HudLayer> hiddenHudLayers = EnumSet.noneOf(HudLayer.class);
   private final ArrayList<FrameRouter.WorldSubscription> worlds = new ArrayList<>();
   private FrameRouter.WorldSubscription[] worldSnapshot = new FrameRouter.WorldSubscription[0];
   private final ArrayList<FrameRouter.TimeSubscription> times = new ArrayList<>();
   private FrameRouter.TimeSubscription[] timeSnapshot = new FrameRouter.TimeSubscription[0];
   private boolean worldRefresh;

   AutoCloseable suppressHud(Set<HudLayer> layers) {
      this.requireIdle();
      FrameRouter.HudSuppression subscription = new FrameRouter.HudSuppression(Set.copyOf(layers));
      this.hudSuppressions.add(subscription);
      this.hiddenHudLayers.addAll(subscription.layers);
      return subscription;
   }

   public boolean hidesHud(HudLayer layer) {
      this.owner.check();
      return this.hiddenHudLayers.contains(layer);
   }

   AutoCloseable subscribeWorld(Consumer<WorldView> callback) {
      this.owner.check();
      FrameRouter.WorldSubscription subscription = new FrameRouter.WorldSubscription(Objects.requireNonNull(callback));
      this.worlds.add(subscription);
      this.worldSnapshot = this.worlds.toArray(FrameRouter.WorldSubscription[]::new);
      return subscription;
   }

   AutoCloseable subscribeTime(WorldTimeTransform callback) {
      this.owner.check();
      FrameRouter.TimeSubscription subscription = new FrameRouter.TimeSubscription(Objects.requireNonNull(callback));
      this.times.add(subscription);
      this.timeSnapshot = this.times.toArray(FrameRouter.TimeSubscription[]::new);
      return subscription;
   }

   void requestWorldRefresh() {
      this.owner.check();
      this.worldRefresh = true;
   }

   public void world(WorldView view) {
      this.owner.check();
      Objects.requireNonNull(view);
      this.dispatchDepth++;

      try {
         if (this.worldRefresh) {
            this.worldRefresh = false;
            if (view.present()) {
               view.refreshLighting();
            }
         }

         for (FrameRouter.WorldSubscription subscription : this.worldSnapshot) {
            Consumer<WorldView> callback = subscription.callback;
            if (callback != null) {
               callback.accept(view);
            }
         }
      } finally {
         this.dispatchDepth--;
      }
   }

   public long worldTime(long value, boolean skyLight, boolean fixedTime) {
      this.owner.check();
      this.dispatchDepth++;

      long var13;
      try {
         for (FrameRouter.TimeSubscription subscription : this.timeSnapshot) {
            WorldTimeTransform callback = subscription.callback;
            if (callback != null) {
               value = callback.apply(value, skyLight, fixedTime);
            }
         }

         var13 = value;
      } finally {
         this.dispatchDepth--;
      }

      return var13;
   }

   AutoCloseable subscribeHud(Consumer<HudCanvas> callback) {
      this.owner.check();
      FrameRouter.HudSubscription subscription = new FrameRouter.HudSubscription(Objects.requireNonNull(callback), false);
      this.hud.add(subscription);
      this.hudSnapshot = this.hud.toArray(FrameRouter.HudSubscription[]::new);
      return subscription;
   }

   AutoCloseable subscribeHudLayout(Consumer<HudCanvas> callback) {
      this.owner.check();
      FrameRouter.HudSubscription subscription = new FrameRouter.HudSubscription(Objects.requireNonNull(callback), true);
      this.hudLayouts.add(subscription);
      this.hudLayoutSnapshot = this.hudLayouts.toArray(FrameRouter.HudSubscription[]::new);
      return subscription;
   }

   public void hud(HudCanvas canvas) {
      this.owner.check();
      this.dispatchDepth++;

      try {
         for (FrameRouter.HudSubscription subscription : this.hudLayoutSnapshot) {
            Consumer<HudCanvas> callback = subscription.callback;
            if (callback != null) {
               callback.accept(canvas);
            }
         }

         for (FrameRouter.HudSubscription subscriptionx : this.hudSnapshot) {
            Consumer<HudCanvas> callback = subscriptionx.callback;
            if (callback != null) {
               callback.accept(canvas);
            }
         }
      } finally {
         this.dispatchDepth--;
      }
   }

   AutoCloseable subscribe(DoubleConsumer callback) {
      this.owner.check();
      FrameRouter.Subscription subscription = new FrameRouter.Subscription(Objects.requireNonNull(callback));
      this.subscriptions.add(subscription);
      this.snapshot = this.subscriptions.toArray(FrameRouter.Subscription[]::new);
      return subscription;
   }

   public void frame(double seconds) {
      this.owner.check();
      if (Double.isFinite(seconds) && !(seconds < 0.0)) {
         this.dispatchDepth++;

         try {
            for (FrameRouter.Subscription subscription : this.snapshot) {
               DoubleConsumer callback = subscription.callback;
               if (callback != null) {
                  callback.accept(seconds);
               }
            }
         } finally {
            this.dispatchDepth--;
         }
      } else {
         throw new IllegalArgumentException("Invalid frame duration");
      }
   }

   void requireIdle() {
      this.owner.check();
      if (this.dispatchDepth != 0) {
         throw new IllegalStateException("Schedule lifecycle changes after hook dispatch");
      }
   }

   public int subscriberCount() {
      this.owner.check();
      return this.subscriptions.size() + this.hud.size() + this.hudLayouts.size() + this.hudSuppressions.size() + this.worlds.size() + this.times.size();
   }

   private final class HudSubscription implements AutoCloseable {
      private Consumer<HudCanvas> callback;
      private final boolean layout;

      private HudSubscription(Consumer<HudCanvas> value, boolean layout) {
         this.callback = value;
         this.layout = layout;
      }

      @Override
      public void close() {
         FrameRouter.this.owner.check();
         this.callback = null;
         if (this.layout) {
            FrameRouter.this.hudLayouts.remove(this);
            FrameRouter.this.hudLayoutSnapshot = FrameRouter.this.hudLayouts.toArray(FrameRouter.HudSubscription[]::new);
         } else {
            FrameRouter.this.hud.remove(this);
            FrameRouter.this.hudSnapshot = FrameRouter.this.hud.toArray(FrameRouter.HudSubscription[]::new);
         }
      }
   }

   private final class HudSuppression implements AutoCloseable {
      private final Set<HudLayer> layers;

      private HudSuppression(Set<HudLayer> layers) {
         this.layers = layers;
      }

      @Override
      public void close() {
         FrameRouter.this.requireIdle();
         if (FrameRouter.this.hudSuppressions.remove(this)) {
            FrameRouter.this.hiddenHudLayers.clear();

            for (FrameRouter.HudSuppression remaining : FrameRouter.this.hudSuppressions) {
               FrameRouter.this.hiddenHudLayers.addAll(remaining.layers);
            }
         }
      }
   }

   private final class Subscription implements AutoCloseable {
      private DoubleConsumer callback;

      private Subscription(DoubleConsumer callback) {
         this.callback = callback;
      }

      @Override
      public void close() {
         FrameRouter.this.owner.check();
         this.callback = null;
         FrameRouter.this.subscriptions.remove(this);
         FrameRouter.this.snapshot = FrameRouter.this.subscriptions.toArray(FrameRouter.Subscription[]::new);
      }
   }

   private final class TimeSubscription implements AutoCloseable {
      private WorldTimeTransform callback;

      private TimeSubscription(WorldTimeTransform value) {
         this.callback = value;
      }

      @Override
      public void close() {
         FrameRouter.this.owner.check();
         this.callback = null;
         FrameRouter.this.times.remove(this);
         FrameRouter.this.timeSnapshot = FrameRouter.this.times.toArray(FrameRouter.TimeSubscription[]::new);
      }
   }

   private final class WorldSubscription implements AutoCloseable {
      private Consumer<WorldView> callback;

      private WorldSubscription(Consumer<WorldView> value) {
         this.callback = value;
      }

      @Override
      public void close() {
         FrameRouter.this.owner.check();
         this.callback = null;
         FrameRouter.this.worlds.remove(this);
         FrameRouter.this.worldSnapshot = FrameRouter.this.worlds.toArray(FrameRouter.WorldSubscription[]::new);
      }
   }
}
