// Sourced By DevFFNightmare
package dev.purxiy.modules.targethud;

import dev.purxiy.loader.fabric.ClientServices;
import net.minecraft.class_3414;

public enum TargetKind {
   HOVER("hover", 28137773401708L, TargetKind.TypeH.c, 0.55F, 0.05F, 70),
   TYPE("type", 28137773401708L, TargetKind.TypeH.E, 0.55F, 0.07F, 24),
   SLIDER_TICK("slider_tick", 28137773401708L, TargetKind.TypeH.E, 0.5F, 0.0F, 34),
   ORBIT_TICK("orbit_tick", 28137773401708L, TargetKind.TypeH.W, 0.6F, 0.025F, 45),
   TAB("tab", 28137773401708L, TargetKind.TypeH.W, 0.6F, 0.02F, 60),
   CLICK("click", 28137773401708L, TargetKind.TypeH.W, 0.6F, 0.03F, 50),
   TOGGLE_ON("toggle_on", 28137773401708L, TargetKind.TypeH.W, 0.6F, 0.0F, 70),
   TOGGLE_OFF("toggle_off", 28137773401708L, TargetKind.TypeH.W, 0.6F, 0.0F, 70),
   SELECT("select", 28137773401708L, TargetKind.TypeH.J, 0.55F, 0.0F, 150),
   OPEN("open", 28137773401708L, TargetKind.TypeH.J, 0.55F, 0.0F, 300),
   CLOSE("close", 28137773401708L, TargetKind.TypeH.J, 0.55F, 0.0F, 250),
   EXPAND("expand", 28137773401708L, TargetKind.TypeH.J, 0.6F, 0.0F, 110),
   COLLAPSE("collapse", 28137773401708L, TargetKind.TypeH.J, 0.6F, 0.0F, 110),
   KEY_SET("key_set", 28137773401708L, TargetKind.TypeH.W, 0.6F, 0.0F, 110),
   DENY("deny", 28137773401708L, TargetKind.TypeH.W, 0.55F, 0.0F, 200),
   TARGET_LOCK("target_lock", 28137773401708L, TargetKind.TypeH.J, 0.5F, 0.02F, 350),
   TARGET_RELEASE("target_release", 28137773401708L, TargetKind.TypeH.J, 0.45F, 0.02F, 350),
   TARGET_POP("target_pop", 28137773401708L, TargetKind.TypeH.W, 0.6F, 0.0F, 90),
   TARGET_KILL("target_kill", 28137773401708L, TargetKind.TypeH.W, 0.6F, 0.0F, 600),
   STAFF_ALERT("staff_alert", 28137773401708L, TargetKind.TypeH.J, 0.55F, 0.0F, 900);

   public final class_3414 J;
   public final TargetKind.TypeH e;
   final float j;
   final float x;
   final long u;
   long I;




   private static final long q = 1000000L;

   private TargetKind(String nullxx, long nullxxx, TargetKind.TypeH nullxxxx, float nullxxxxx, float nullxxxxxx, int nullxxxxxxx) {
      this.J = class_3414.method_47908(ClientServices.id("ui." + nullxx));
      this.e = nullxxxx;
      this.j = nullxxxxx;
      this.x = nullxxxxxx;
      this.u = nullxxxxxxx * 1000000L;
   }

public static enum TypeH {
      c,
      E,
      W,
      J;

}
}
