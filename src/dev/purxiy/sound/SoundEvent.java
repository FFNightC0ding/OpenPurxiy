package dev.purxiy.sound;

import dev.purxiy.loader.fabric.ClientServices;
import net.minecraft.class_3414;

public enum SoundEvent {
   HOVER("hover", SoundEvent.SoundCue.HOVER, 0.55F, 0.05F, 70),
   TYPE("type", SoundEvent.SoundCue.TICKS, 0.55F, 0.07F, 24),
   SLIDER_TICK("slider_tick", SoundEvent.SoundCue.TICKS, 0.5F, 0.0F, 34),
   ORBIT_TICK("orbit_tick", SoundEvent.SoundCue.FEEDBACK, 0.6F, 0.025F, 45),
   TAB("tab", SoundEvent.SoundCue.FEEDBACK, 0.6F, 0.02F, 60),
   CLICK("click", SoundEvent.SoundCue.FEEDBACK, 0.6F, 0.03F, 50),
   TOGGLE_ON("toggle_on", SoundEvent.SoundCue.FEEDBACK, 0.6F, 0.0F, 70),
   TOGGLE_OFF("toggle_off", SoundEvent.SoundCue.FEEDBACK, 0.6F, 0.0F, 70),
   SELECT("select", SoundEvent.SoundCue.TRANSITIONS, 0.55F, 0.0F, 150),
   OPEN("open", SoundEvent.SoundCue.TRANSITIONS, 0.55F, 0.0F, 300),
   CLOSE("close", SoundEvent.SoundCue.TRANSITIONS, 0.55F, 0.0F, 250),
   EXPAND("expand", SoundEvent.SoundCue.TRANSITIONS, 0.6F, 0.0F, 110),
   COLLAPSE("collapse", SoundEvent.SoundCue.TRANSITIONS, 0.6F, 0.0F, 110),
   KEY_SET("key_set", SoundEvent.SoundCue.FEEDBACK, 0.6F, 0.0F, 110),
   DENY("deny", SoundEvent.SoundCue.FEEDBACK, 0.55F, 0.0F, 200),
   TARGET_LOCK("target_lock", SoundEvent.SoundCue.TRANSITIONS, 0.5F, 0.02F, 350),
   TARGET_RELEASE("target_release", SoundEvent.SoundCue.TRANSITIONS, 0.45F, 0.02F, 350),
   TARGET_POP("target_pop", SoundEvent.SoundCue.FEEDBACK, 0.6F, 0.0F, 90),
   TARGET_KILL("target_kill", SoundEvent.SoundCue.FEEDBACK, 0.6F, 0.0F, 600),
   STAFF_ALERT("staff_alert", SoundEvent.SoundCue.TRANSITIONS, 0.55F, 0.0F, 900);

   public final class_3414 event;
   public final SoundEvent.SoundCue group;
   final float volume;
   final float pitchJitter;
   final long minIntervalNanos;
   long lastPlayed;

   private SoundEvent(String nullxx, SoundEvent.SoundCue nullxxx, float nullxxxx, float nullxxxxx, int nullxxxxxx) {
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

   public static enum SoundCue {
      HOVER,
      TICKS,
      FEEDBACK,
      TRANSITIONS;

      private static SoundEvent.SoundCue[] $values() {
         return new SoundEvent.SoundCue[]{HOVER, TICKS, FEEDBACK, TRANSITIONS};
      }
   }
}
