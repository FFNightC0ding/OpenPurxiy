package dev.purxiy.loader.fabric.client;

import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_10444;
import net.minecraft.class_10515;
import net.minecraft.class_11566;
import net.minecraft.class_11659;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_811;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public final class ClientUpdater {
   private static final SkyPipelineRouter.PipelineList<ClientUpdater.Listener> listeners = SkyPipelineRouter.PipelineList.create();
   public static final class_10515<ClientUpdater.ModulePayload> RENDERER = new ClientUpdater.ModulePayloadDecoder();

   private ClientUpdater() {
   }

   public static AutoCloseable install(ClientUpdater.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Knife item contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean update(class_10444 var0, class_1799 var1, class_811 var2,  class_11566 var3) {
      return listeners.invoke(var4 -> var4.update(var0, var1, var2, var3), false);
   }

   public static Supplier<Vector3fc[]> extents(Vector3fc[] var0) {
      Vector3fc[] var1 = new Vector3fc[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = new Vector3f(var0[var2]);
      }

      return () -> var1;
   }

   public interface Listener {
      boolean update(class_10444 var1, class_1799 var2, class_811 var3,  class_11566 var4);

      void submit( ClientUpdater.ModulePayload var1, class_811 var2, class_4587 var3, class_11659 var4, int var5, int var6, boolean var7, int var8);
   }

   private static final class ModulePayloadDecoder implements class_10515<ClientUpdater.ModulePayload> {
      public void method_65694(ClientUpdater.ModulePayload var1, class_811 var2, class_4587 var3, class_11659 var4, int var5, int var6, boolean var7, int var8) {
         this.submit(var1, var2, var3, var4, var5, var6, var7, var8);
      }

      public void submit( ClientUpdater.ModulePayload var1, class_811 var2, class_4587 var3, class_11659 var4, int var5, int var6, boolean var7, int var8) {
         ClientUpdater.listeners.invoke(var8x -> {
            var8x.submit(var1, var2, var3, var4, var5, var6, var7, var8);
            return null;
         }, null);
      }

      @Override
      public void method_72175(Consumer<Vector3fc> var1) {
      }


      public ClientUpdater.ModulePayload method_65695(class_1799 var1) {
         return null;
      }


      public ClientUpdater.ModulePayload extractArgument(class_1799 var1) {
         return null;
      }
   }

   public record ModulePayload(int model, String finish, String camo, int great) {
   }
}
