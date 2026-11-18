// Sourced By DevFFNightmare
package dev.purxiy.sound;

import dev.purxiy.loader.fabric.ClientServices;
import net.minecraft.class_3414;

public enum SoundEvent {
   HOVER("hover", SoundEvent.TypeS.HOVER, 0.55F, 0.05F, 70),
   TYPE("type", SoundEvent.TypeS.TICKS, 0.55F, 0.07F, 24),
   SLIDER_TICK("slider_tick", SoundEvent.TypeS.TICKS, 0.5F, 0.0F, 34),
   ORBIT_TICK("orbit_tick", SoundEvent.TypeS.FEEDBACK, 0.6F, 0.025F, 45),
   TAB("tab", SoundEvent.TypeS.FEEDBACK, 0.6F, 0.02F, 60),
   CLICK("click", SoundEvent.TypeS.FEEDBACK, 0.6F, 0.03F, 50),
   TOGGLE_ON("toggle_on", SoundEvent.TypeS.FEEDBACK, 0.6F, 0.0F, 70),
   TOGGLE_OFF("toggle_off", SoundEvent.TypeS.FEEDBACK, 0.6F, 0.0F, 70),
   SELECT("select", SoundEvent.TypeS.TRANSITIONS, 0.55F, 0.0F, 150),
   OPEN("open", SoundEvent.TypeS.TRANSITIONS, 0.55F, 0.0F, 300),
   CLOSE("close", SoundEvent.TypeS.TRANSITIONS, 0.55F, 0.0F, 250),
   EXPAND("expand", SoundEvent.TypeS.TRANSITIONS, 0.6F, 0.0F, 110),
   COLLAPSE("collapse", SoundEvent.TypeS.TRANSITIONS, 0.6F, 0.0F, 110),
   KEY_SET("key_set", SoundEvent.TypeS.FEEDBACK, 0.6F, 0.0F, 110),
   DENY("deny", SoundEvent.TypeS.FEEDBACK, 0.55F, 0.0F, 200),
   TARGET_LOCK("target_lock", SoundEvent.TypeS.TRANSITIONS, 0.5F, 0.02F, 350),
   TARGET_RELEASE("target_release", SoundEvent.TypeS.TRANSITIONS, 0.45F, 0.02F, 350),
   TARGET_POP("target_pop", SoundEvent.TypeS.FEEDBACK, 0.6F, 0.0F, 90),
   TARGET_KILL("target_kill", SoundEvent.TypeS.FEEDBACK, 0.6F, 0.0F, 600),
   STAFF_ALERT("staff_alert", SoundEvent.TypeS.TRANSITIONS, 0.55F, 0.0F, 900);

   public final class_3414 event;
   public final SoundEvent.TypeS group;
   final float volume;
   final float pitchJitter;
   final long minIntervalNanos;
   long lastPlayed;

   private SoundEvent(String nullxx, SoundEvent.TypeS nullxxx, float nullxxxx, float nullxxxxx, int nullxxxxxx) {
      this.event = class_3414.method_47908(ClientServices.id("ui." + nullxx));
      this.group = nullxxx;
      this.volume = nullxxxx;
      this.pitchJitter = nullxxxxx;
      this.minIntervalNanos = nullxxxxxx * 1000000L;
   }

   private static SoundEvent[] $values() {
      return new SoundEvent[]{
         HOVER,
         TYPE,
         SLIDER_TICK,
         ORBIT_TICK,
         TAB,
         CLICK,
         TOGGLE_ON,
         TOGGLE_OFF,
         SELECT,
         OPEN,
         CLOSE,
         EXPAND,
         COLLAPSE,
         KEY_SET,
         DENY,
         TARGET_LOCK,
         TARGET_RELEASE,
         TARGET_POP,
         TARGET_KILL,
         STAFF_ALERT
      };
   }

   public static enum TypeS {
      HOVER,
      TICKS,
      FEEDBACK,
      TRANSITIONS;

      private static SoundEvent.TypeS[] $values() {
         return new SoundEvent.TypeS[]{HOVER, TICKS, FEEDBACK, TRANSITIONS};
      }
   }
}
