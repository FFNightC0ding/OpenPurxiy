// Sourced By DevFFNightmare
package dev.purxiy.social;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.render.Canvas;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_12137;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_634;
import net.minecraft.class_640;
import net.minecraft.class_9296;

public final class FriendProfile {
   private static final Map<String, class_9296> PROFILES = new HashMap<>();

   private FriendProfile() {
   }

   public static boolean draw(Canvas var0, PlayerMetadata var1, float var2, float var3, float var4, float var5, int var6) {
      GpuTextureView var7 = view(var1);
      if (var7 == null) {
         return false;
      } else {
         face(var0, var7, var2, var3, var4, var5, var6);
         return true;
      }
   }

   public static boolean draw(Canvas var0, GameProfile var1, float var2, float var3, float var4, float var5, int var6) {
      class_634 var7 = class_310.method_1551().method_1562();
      class_640 var8 = var7 == null ? null : var7.method_2871(var1.id());
      GpuTextureView var9 = var8 == null ? null : texture(var8.method_52810().comp_1626().comp_3627());
      if (var9 == null) {
         return false;
      } else {
         face(var0, var9, var2, var3, var4, var5, var6);
         return true;
      }
   }

   private static void face(Canvas var0, GpuTextureView var1, float var2, float var3, float var4, float var5, int var6) {
      class_12137 var7 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
      var0.image(var1, var7, false, var2, var3, var4, var4, 0.125F, 0.125F, 0.25F, 0.25F, var5, var6);
      var0.image(var1, var7, false, var2, var3, var4, var4, 0.625F, 0.125F, 0.75F, 0.25F, var5, var6);
   }

   public static boolean ready(PlayerMetadata var0) {
      if (Friends.presence(var0).info() != null) {
         return true;
      } else {
         class_9296 var1 = profile(var0);
         return var1 != null && class_310.method_1551().method_73362().method_74320(var1).isDone();
      }
   }

   
   private static GpuTextureView view(PlayerMetadata var0) {
      class_640 var1 = Friends.presence(var0).info();
      if (var1 != null) {
         GpuTextureView var2 = texture(var1.method_52810().comp_1626().comp_3627());
         if (var2 != null) {
            return var2;
         }
      }

      class_9296 var3 = profile(var0);
      return var3 == null ? null : class_310.method_1551().method_73362().method_73495(var3).method_74051();
   }

   
   private static class_9296 profile(PlayerMetadata var0) {
      UUID var1 = var0.minecraftId();
      if (var1 == null) {
         return null;
      } else {
         String var2 = var1.toString();
         class_9296 var3 = PROFILES.get(var2);
         if (var3 == null) {
            if (PROFILES.size() > 256) {
               PROFILES.clear();
            }

            var3 = class_9296.method_73312(var1);
            PROFILES.put(var2, var3);
         }

         return var3;
      }
   }

   
   private static GpuTextureView texture( class_2960 var0) {
      if (var0 == null) {
         return null;
      } else {
         try {
            return class_310.method_1551().method_1531().method_4619(var0).method_71659();
         } catch (IllegalStateException var2) {
            return null;
         }
      }
   }
}
