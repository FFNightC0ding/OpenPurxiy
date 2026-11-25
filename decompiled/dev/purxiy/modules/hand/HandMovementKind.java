package dev.purxiy.modules.hand;

import dev.purxiy.loader.fabric.ClientServices;
import net.minecraft.class_3414;

public enum HandMovementKind {
   HOVER("hover", HandMovementKind.TypeM.v, 0.55F, 0.05F, 133778364000129L, 70),
   TYPE("type", HandMovementKind.TypeM.C, 0.55F, 0.07F, 133778364000129L, 24),
   SLIDER_TICK("slider_tick", HandMovementKind.TypeM.C, 0.5F, 0.0F, 133778364000129L, 34),
   ORBIT_TICK("orbit_tick", HandMovementKind.TypeM.t, 0.6F, 0.025F, 133778364000129L, 45),
   TAB("tab", HandMovementKind.TypeM.t, 0.6F, 0.02F, 133778364000129L, 60),
   CLICK("click", HandMovementKind.TypeM.t, 0.6F, 0.03F, 133778364000129L, 50),
   TOGGLE_ON("toggle_on", HandMovementKind.TypeM.t, 0.6F, 0.0F, 133778364000129L, 70),
   TOGGLE_OFF("toggle_off", HandMovementKind.TypeM.t, 0.6F, 0.0F, 133778364000129L, 70),
   SELECT("select", HandMovementKind.TypeM.j, 0.55F, 0.0F, 133778364000129L, 150),
   OPEN("open", HandMovementKind.TypeM.j, 0.55F, 0.0F, 133778364000129L, 300),
   CLOSE("close", HandMovementKind.TypeM.j, 0.55F, 0.0F, 133778364000129L, 250),
   EXPAND("expand", HandMovementKind.TypeM.j, 0.6F, 0.0F, 133778364000129L, 110),
   COLLAPSE("collapse", HandMovementKind.TypeM.j, 0.6F, 0.0F, 133778364000129L, 110),
   KEY_SET("key_set", HandMovementKind.TypeM.t, 0.6F, 0.0F, 133778364000129L, 110),
   DENY("deny", HandMovementKind.TypeM.t, 0.55F, 0.0F, 133778364000129L, 200),
   TARGET_LOCK("target_lock", HandMovementKind.TypeM.j, 0.5F, 0.02F, 133778364000129L, 350),
   TARGET_RELEASE("target_release", HandMovementKind.TypeM.j, 0.45F, 0.02F, 133778364000129L, 350),
   TARGET_POP("target_pop", HandMovementKind.TypeM.t, 0.6F, 0.0F, 133778364000129L, 90),
   TARGET_KILL("target_kill", HandMovementKind.TypeM.t, 0.6F, 0.0F, 133778364000129L, 600),
   STAFF_ALERT("staff_alert", HandMovementKind.TypeM.j, 0.55F, 0.0F, 133778364000129L, 900);

   public final class_3414 w;
   public final HandMovementKind.TypeM i;
   final float b;
   final float d;
   final long s;
   long h;
   private static final long j = 1000000L;

   private HandMovementKind(String nullxx, HandMovementKind.TypeM nullxxx, float nullxxxx, float nullxxxxx, long nullxxxxxx, int nullxxxxxxx) {
      this.w = class_3414.method_47908(ClientServices.id("ui." + nullxx));
      this.i = nullxxx;
      this.b = nullxxxx;
      this.d = nullxxxxx;
      this.s = nullxxxxxxx * 1000000L;
   }

   public static enum TypeM {
      v,
      C,
      t,
      j;
   }
}
