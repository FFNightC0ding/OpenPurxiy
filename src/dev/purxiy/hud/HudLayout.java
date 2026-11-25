package dev.purxiy.hud;

import dev.purxiy.render.RenderPipelines;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class HudLayout {
   public static final int MAX = 4;
   static final List<HudLayout.HudToast> TOASTS = new ArrayList<>();

   public static void clear() {
      TOASTS.clear();
   }

   private HudLayout() {
   }

   public static void push(String var0, String var1, String var2, boolean var3) {
      push(var0, var1, var2, var3, 0.0F);
   }

   public static void push(String var0, String var1, String var2, boolean var3, float var4) {
      float var5 = RenderPipelines.get().time();
      TOASTS.removeIf(var1x -> var1x.title().equals(var0));
      TOASTS.addFirst(new HudLayout.HudToast(var0, var1, var2, var3, var5, var4));

      while (TOASTS.size() > 4) {
         TOASTS.removeLast();
      }
   }

   public static List<HudLayout.HudToast> toasts() {
      return Collections.unmodifiableList(TOASTS);
   }

   public static void prune(float var0, float var1) {
      TOASTS.removeIf(var2 -> var0 - var2.createdAt() > var2.lifetime(var1) + 0.6F);
   }

   public record HudToast(String title, String message, String icon, boolean positive, float createdAt, float life) {
      public HudToast(String var1, String var2, String var3, boolean var4, float var5) {
         this(var1, var2, var3, var4, var5, 0.0F);
      }

      public float lifetime(float var1) {
         return Math.max(var1, this.life);
      }
   }
}
