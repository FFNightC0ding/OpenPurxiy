package dev.purxiy.profile;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.core.ImageFiles;
import dev.purxiy.loader.fabric.client.ClientBuilder;
import dev.purxiy.loader.fabric.client.ArmorRenderProvider;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.render.Canvas;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.class_1044;
import net.minecraft.class_12137;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_8685;

public final class ProfileSkin {

   private static Supplier<class_8685> lookup;

   private static UUID lookupFor;

   private ProfileSkin() {
   }


   public static class_8685 skin() {
      class_8685 var0 = plain();
      return var0 == null ? null : ArmorRenderProvider.dress(var0);
   }


   private static class_8685 plain() {
      class_310 var0 = class_310.method_1551();
      if (var0.field_1724 != null) {
         return var0.field_1724.method_52814();
      } else {
         UUID var1 = var0.method_1548().method_44717();
         if (lookup == null || !var1.equals(lookupFor)) {
            lookup = var0.method_1582().method_73544(var0.method_53462(), false);
            lookupFor = var1;
         }

         return lookup.get();
      }
   }


   static GpuTextureView view( class_2960 var0) {
      if (var0 == null) {
         return null;
      } else {
         try {
            class_1044 var1 = class_310.method_1551().method_1531().method_4619(var0);
            return var1.method_71659();
         } catch (IllegalStateException var2) {
            return null;
         }
      }
   }

   static class_12137 pixels() {
      return RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
   }

   static class_12137 capeSampler(class_8685 var0) {
      return ClientBuilder.capeSampler(var0);
   }

   public static boolean face(Canvas var0, float var1, float var2, float var3, float var4, int var5) {
      if (SettingSection.PROFILE.photoPortrait() && ImageFiles.draw(var0, var1, var2, var3, var4, var5)) {
         return true;
      } else {
         class_8685 var6 = plain();
         GpuTextureView var7 = var6 == null ? null : view(var6.comp_1626().comp_3627());
         if (var7 == null) {
            return false;
         } else {
            class_12137 var8 = pixels();
            var0.image(var7, var8, false, var1, var2, var3, var3, 0.125F, 0.125F, 0.25F, 0.25F, var4, var5);
            var0.image(var7, var8, false, var1, var2, var3, var3, 0.625F, 0.125F, 0.75F, 0.25F, var4, var5);
            return true;
         }
      }
   }
}
