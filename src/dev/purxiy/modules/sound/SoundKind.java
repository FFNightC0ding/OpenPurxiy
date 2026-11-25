package dev.purxiy.modules.sound;

import dev.purxiy.loader.fabric.ClientServices;
import net.minecraft.class_3414;

public enum SoundKind {
   HOVER("hover", SoundKind.SoundChannel.r, 135255965330200L, 0.55F, 0.05F, 70),
   TYPE("type", SoundKind.SoundChannel.X, 135255965330200L, 0.55F, 0.07F, 24),
   SLIDER_TICK("slider_tick", SoundKind.SoundChannel.X, 135255965330200L, 0.5F, 0.0F, 34),
   ORBIT_TICK("orbit_tick", SoundKind.SoundChannel.b, 135255965330200L, 0.6F, 0.025F, 45),
   TAB("tab", SoundKind.SoundChannel.b, 135255965330200L, 0.6F, 0.02F, 60),
   CLICK("click", SoundKind.SoundChannel.b, 135255965330200L, 0.6F, 0.03F, 50),
   TOGGLE_ON("toggle_on", SoundKind.SoundChannel.b, 135255965330200L, 0.6F, 0.0F, 70),
   TOGGLE_OFF("toggle_off", SoundKind.SoundChannel.b, 135255965330200L, 0.6F, 0.0F, 70),
   SELECT("select", SoundKind.SoundChannel.c, 135255965330200L, 0.55F, 0.0F, 150),
   OPEN("open", SoundKind.SoundChannel.c, 135255965330200L, 0.55F, 0.0F, 300),
   CLOSE("close", SoundKind.SoundChannel.c, 135255965330200L, 0.55F, 0.0F, 250),
   EXPAND("expand", SoundKind.SoundChannel.c, 135255965330200L, 0.6F, 0.0F, 110),
   COLLAPSE("collapse", SoundKind.SoundChannel.c, 135255965330200L, 0.6F, 0.0F, 110),
   KEY_SET("key_set", SoundKind.SoundChannel.b, 135255965330200L, 0.6F, 0.0F, 110),
   DENY("deny", SoundKind.SoundChannel.b, 135255965330200L, 0.55F, 0.0F, 200),
   TARGET_LOCK("target_lock", SoundKind.SoundChannel.c, 135255965330200L, 0.5F, 0.02F, 350),
   TARGET_RELEASE("target_release", SoundKind.SoundChannel.c, 135255965330200L, 0.45F, 0.02F, 350),
   TARGET_POP("target_pop", SoundKind.SoundChannel.b, 135255965330200L, 0.6F, 0.0F, 90),
   TARGET_KILL("target_kill", SoundKind.SoundChannel.b, 135255965330200L, 0.6F, 0.0F, 600),
   STAFF_ALERT("staff_alert", SoundKind.SoundChannel.c, 135255965330200L, 0.55F, 0.0F, 900);

   public final class_3414 v;
   public final SoundKind.SoundChannel F;
   final float w;
   final float x;
   final long U;
   long H;
   public static int n = 0;
   private static final String[] c = new String[41];
   private static final long g = 1000000L;

   private SoundKind(String nullxx, SoundKind.SoundChannel nullxxx, long nullxxxx, float nullxxxxx, float nullxxxxxx, int nullxxxxxxx) {
      this.v = class_3414.method_47908(ClientServices.id("ui." + nullxx));
      this.F = nullxxx;
      this.w = nullxxxxx;
      this.x = nullxxxxxx;
      this.U = nullxxxxxxx * 1000000L;
   }

   public static enum SoundChannel {
      r,
      X,
      b,
      c;

      public static int g = 1000000;
   }
}
