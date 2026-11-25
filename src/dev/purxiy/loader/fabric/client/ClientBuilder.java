package dev.purxiy.loader.fabric.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.class_12137;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_630;
import net.minecraft.class_8685;
import org.joml.Matrix4f;

public final class ClientBuilder {
   public static final int HEAD = 0;
   public static final int BODY = 1;
   public static final int RIGHT_ARM = 2;
   public static final int LEFT_ARM = 3;
   public static final int RIGHT_LEG = 4;
   public static final int LEFT_LEG = 5;
   public static final float[][] PIVOT = new float[][]{
      {0.0F, 0.0F, 0.0F}, {0.0F, 0.0F, 0.0F}, {-5.0F, 2.0F, 0.0F}, {5.0F, 2.0F, 0.0F}, {-1.9F, 12.0F, 0.0F}, {1.9F, 12.0F, 0.0F}
   };
   private static final SkyPipelineRouter.PipelineList<ClientBuilder.Listener> sculpted = SkyPipelineRouter.PipelineList.create();
   private static final SkyPipelineRouter.PipelineList<Supplier<class_8685>> profile = SkyPipelineRouter.PipelineList.create();
   private static final SkyPipelineRouter.PipelineList<ClientBuilder.SkinMeshProvider> models = SkyPipelineRouter.PipelineList.create();
   private static UUID lookupFor;
   private static Supplier<class_8685> lookup;

   private ClientBuilder() {
   }

   public static AutoCloseable sculpted(ClientBuilder.Listener var0) {
      return sculpted.subscribe(0, var0);
   }

   public static AutoCloseable profile(Supplier<class_8685> var0) {
      return profile.subscribe(0, var0);
   }

   public static AutoCloseable model(ClientBuilder.SkinMeshProvider var0) {
      return models.subscribe(0, var0);
   }

   public static ClientBuilder.SkinMeshSpec layers(class_8685 var0) {
      return models.invoke(var1 -> var1.body(var0), null);
   }

   public static int triangles(ByteBuffer var0, ClientBuilder.SkinMeshSpec var1, int var2, Matrix4f var3) {
      return models.invoke(var4 -> var4.triangles(var0, var1, var2, var3), 0);
   }

   public static void build(Matrix4f var0, int var1) {
      models.invoke(var2 -> {
         var2.build(var0, var1);
         return null;
      }, null);
   }

   public static float yourHeight() {
      return models.invoke(ClientBuilder.SkinMeshProvider::height, 1.0F);
   }

   public static float bodyWidth() {
      return models.invoke(ClientBuilder.SkinMeshProvider::width, 1.0F);
   }

   public static float armSpread() {
      return models.invoke(ClientBuilder.SkinMeshProvider::armSpread, 1.0F);
   }

   public static class_8685 skin() {
      return ArmorRenderProvider.dress(ownSkin());
   }

   public static GpuTextureView view(class_2960 var0) {
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

   public static class_12137 pixels() {
      return RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
   }

   public static class_12137 capeSampler(class_8685 var0) {
      return ArmorRenderProvider.wears(var0) ? ArmorRenderProvider.sampler() : pixels();
   }

   public static float[] bounds(class_630 var0) {
      return sculpted.invoke(var1 -> var1.bounds(var0), null);
   }

   public static int accent() {
      return sculpted.invoke(ClientBuilder.Listener::accent, 0);
   }

   public static class_8685 profile() {
      return profile.invoke(Supplier::get, null);
   }

   public static class_8685 ownSkin() {
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

   public interface Listener {
      float[] bounds(class_630 var1);

      int accent();
   }

   public interface SkinMeshProvider {
      ClientBuilder.SkinMeshSpec body(class_8685 var1);

      int triangles(ByteBuffer var1, ClientBuilder.SkinMeshSpec var2, int var3, Matrix4f var4);

      void build(Matrix4f var1, int var2);

      float height();

      float width();

      float armSpread();
   }

   public record SkinMeshSpec(class_8685 skin, boolean fullModel, boolean rigidHead, float[][] pivots, float[] headBounds) {
      public SkinMeshSpec(class_8685 skin, boolean fullModel, boolean rigidHead, float[][] pivots, float[] headBounds) {
         pivots = copy(pivots);
         headBounds = headBounds == null ? null : (float[])headBounds.clone();
         this.skin = skin;
         this.fullModel = fullModel;
         this.rigidHead = rigidHead;
         this.pivots = pivots;
         this.headBounds = headBounds;
      }

      public float[][] pivots() {
         return copy(this.pivots);
      }

      public float[] headBounds() {
         return this.headBounds == null ? null : (float[])this.headBounds.clone();
      }

      private static float[][] copy(float[][] var0) {
         return Arrays.stream(var0).map(var0x -> (float[])var0x.clone()).toArray(float[][]::new);
      }
   }
}
