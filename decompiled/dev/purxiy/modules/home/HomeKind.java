package dev.purxiy.modules.home;

import dev.purxiy.loader.fabric.ClientServices;
import net.minecraft.class_3414;

public enum HomeKind {
   HOVER("hover", 12687455654877L, HomeKind.TypeY.b, 0.55F, 0.05F, 70),
   TYPE("type", 12687455654877L, HomeKind.TypeY.s, 0.55F, 0.07F, 24),
   SLIDER_TICK("slider_tick", 12687455654877L, HomeKind.TypeY.s, 0.5F, 0.0F, 34),
   ORBIT_TICK("orbit_tick", 12687455654877L, HomeKind.TypeY.l, 0.6F, 0.025F, 45),
   TAB("tab", 12687455654877L, HomeKind.TypeY.l, 0.6F, 0.02F, 60),
   CLICK("click", 12687455654877L, HomeKind.TypeY.l, 0.6F, 0.03F, 50),
   TOGGLE_ON("toggle_on", 12687455654877L, HomeKind.TypeY.l, 0.6F, 0.0F, 70),
   TOGGLE_OFF("toggle_off", 12687455654877L, HomeKind.TypeY.l, 0.6F, 0.0F, 70),
   SELECT("select", 12687455654877L, HomeKind.TypeY.G, 0.55F, 0.0F, 150),
   OPEN("open", 12687455654877L, HomeKind.TypeY.G, 0.55F, 0.0F, 300),
   CLOSE("close", 12687455654877L, HomeKind.TypeY.G, 0.55F, 0.0F, 250),
   EXPAND("expand", 12687455654877L, HomeKind.TypeY.G, 0.6F, 0.0F, 110),
   COLLAPSE("collapse", 12687455654877L, HomeKind.TypeY.G, 0.6F, 0.0F, 110),
   KEY_SET("key_set", 12687455654877L, HomeKind.TypeY.l, 0.6F, 0.0F, 110),
   DENY("deny", 12687455654877L, HomeKind.TypeY.l, 0.55F, 0.0F, 200),
   TARGET_LOCK("target_lock", 12687455654877L, HomeKind.TypeY.G, 0.5F, 0.02F, 350),
   TARGET_RELEASE("target_release", 12687455654877L, HomeKind.TypeY.G, 0.45F, 0.02F, 350),
   TARGET_POP("target_pop", 12687455654877L, HomeKind.TypeY.l, 0.6F, 0.0F, 90),
   TARGET_KILL("target_kill", 12687455654877L, HomeKind.TypeY.l, 0.6F, 0.0F, 600),
   STAFF_ALERT("staff_alert", 12687455654877L, HomeKind.TypeY.G, 0.55F, 0.0F, 900);

   public final class_3414 b;
   public final HomeKind.TypeY p;
   final float B;
   final float l;
   final long f;
   long O;
   private static final long m = 1000000L;

   private HomeKind(String nullxx, long nullxxx, HomeKind.TypeY nullxxxx, float nullxxxxx, float nullxxxxxx, int nullxxxxxxx) {
      this.b = class_3414.method_47908(ClientServices.id("ui." + nullxx));
      this.p = nullxxxx;
      this.B = nullxxxxx;
      this.l = nullxxxxxx;
      this.f = nullxxxxxxx * 1000000L;
   }

   public static enum TypeY {
      b,
      s,
      l,
      G;
   }
}
