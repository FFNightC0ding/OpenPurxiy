package dev.purxiy.modules.hand;

import dev.purxiy.loader.fabric.ClientServices;
import net.minecraft.class_3414;

public enum HandMovementKind {
   HOVER("hover", HandMovementKind.SwingPhase.v, 0.55F, 0.05F, 133778364000129L, 70),
   TYPE("type", HandMovementKind.SwingPhase.C, 0.55F, 0.07F, 133778364000129L, 24),
   SLIDER_TICK("slider_tick", HandMovementKind.SwingPhase.C, 0.5F, 0.0F, 133778364000129L, 34),
   ORBIT_TICK("orbit_tick", HandMovementKind.SwingPhase.t, 0.6F, 0.025F, 133778364000129L, 45),
   TAB("tab", HandMovementKind.SwingPhase.t, 0.6F, 0.02F, 133778364000129L, 60),
   CLICK("click", HandMovementKind.SwingPhase.t, 0.6F, 0.03F, 133778364000129L, 50),
   TOGGLE_ON("toggle_on", HandMovementKind.SwingPhase.t, 0.6F, 0.0F, 133778364000129L, 70),
   TOGGLE_OFF("toggle_off", HandMovementKind.SwingPhase.t, 0.6F, 0.0F, 133778364000129L, 70),
   SELECT("select", HandMovementKind.SwingPhase.j, 0.55F, 0.0F, 133778364000129L, 150),
   OPEN("open", HandMovementKind.SwingPhase.j, 0.55F, 0.0F, 133778364000129L, 300),
   CLOSE("close", HandMovementKind.SwingPhase.j, 0.55F, 0.0F, 133778364000129L, 250),
   EXPAND("expand", HandMovementKind.SwingPhase.j, 0.6F, 0.0F, 133778364000129L, 110),
   COLLAPSE("collapse", HandMovementKind.SwingPhase.j, 0.6F, 0.0F, 133778364000129L, 110),
   KEY_SET("key_set", HandMovementKind.SwingPhase.t, 0.6F, 0.0F, 133778364000129L, 110),
   DENY("deny", HandMovementKind.SwingPhase.t, 0.55F, 0.0F, 133778364000129L, 200),
   TARGET_LOCK("target_lock", HandMovementKind.SwingPhase.j, 0.5F, 0.02F, 133778364000129L, 350),
   TARGET_RELEASE("target_release", HandMovementKind.SwingPhase.j, 0.45F, 0.02F, 133778364000129L, 350),
   TARGET_POP("target_pop", HandMovementKind.SwingPhase.t, 0.6F, 0.0F, 133778364000129L, 90),
   TARGET_KILL("target_kill", HandMovementKind.SwingPhase.t, 0.6F, 0.0F, 133778364000129L, 600),
   STAFF_ALERT("staff_alert", HandMovementKind.SwingPhase.j, 0.55F, 0.0F, 133778364000129L, 900);

   public final class_3414 w;
   public final HandMovementKind.SwingPhase i;
   final float b;
   final float d;
   final long s;
   long h;
   private static final long j = 1000000L;

   private HandMovementKind(String nullxx, HandMovementKind.SwingPhase nullxxx, float nullxxxx, float nullxxxxx, long nullxxxxxx, int nullxxxxxxx) {
      this.w = class_3414.method_47908(ClientServices.id("ui." + nullxx));
      this.i = nullxxx;
      this.b = nullxxxx;
      this.d = nullxxxxx;
      this.s = nullxxxxxxx * 1000000L;
   }

   public static enum SwingPhase {
      v,
      C,
      t,
      j;
   }
}
