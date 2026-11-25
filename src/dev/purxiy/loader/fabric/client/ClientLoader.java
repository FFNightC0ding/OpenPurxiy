package dev.purxiy.loader.fabric.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.mixin.OwnedSoundAccess;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;
import net.minecraft.class_1110;
import net.minecraft.class_1111;
import net.minecraft.class_1115;
import net.minecraft.class_1144;
import net.minecraft.class_1146;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3419;
import net.minecraft.class_1111.class_1112;

public final class ClientLoader {
   private static final Map<Long, Map<class_2960, ClientLoader.ResourceReload>> owners = new LinkedHashMap<>();
   private static final Gson GSON = new GsonBuilder().registerTypeAdapter(class_1110.class, new class_1115()).create();
   private static long sequence;

   private ClientLoader() {
   }

   private static void owner() {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Sound registry owner thread");
      }
   }

   public static int owners() {
      owner();
      return owners.size();
   }

   public static AutoCloseable install(ModuleContext var0, String var1) throws IOException {
      owner();

      JsonObject var3;
      try (
         InputStream var2 = var0.resource(var1);
         InputStreamReader var4 = new InputStreamReader(var2, StandardCharsets.UTF_8);
      ) {
         var3 = JsonParser.parseReader(var4).getAsJsonObject();
      }

      if (!var3.isEmpty() && var3.size() <= 256 && owners.size() < 128) {
         LinkedHashMap var16 = new LinkedHashMap();

         for (Entry var6 : var3.entrySet()) {
            if (!((String)var6.getKey()).matches("[a-z0-9_.-]{1,96}")) {
               throw new IllegalArgumentException("Owned sound identity");
            }

            class_2960 var7 = ClientServices.id((String)var6.getKey());

            for (Map var9 : owners.values()) {
               if (var9.containsKey(var7)) {
                  throw new IllegalStateException("Sound event already owned");
               }
            }

            class_1110 var20 = (class_1110)GSON.fromJson((JsonElement)var6.getValue(), class_1110.class);
            if (var20.method_4761().isEmpty() || var20.method_4761().size() > 64) {
               throw new IllegalArgumentException("Owned sound file count");
            }

            class_1146 var22 = new class_1146(var7, var20.method_4762());

            for (class_1111 var11 : var20.method_4761()) {
               if (var11.method_4768() != class_1112.field_5474
                  || !var11.method_4767().method_12836().equals("purxiy")
                  || ResourceProvider.resource(var11.method_4766()).isEmpty()) {
                  throw new IllegalArgumentException("Sound must refer to its admitted private file");
               }

               var22.method_4885(var11);
            }

            var16.put(var7, new ClientLoader.ResourceReload(var22, List.copyOf(var20.method_4761())));
         }

         OwnedSoundAccess var17 = (OwnedSoundAccess)class_310.method_1551().method_1483();
         Stream var18 = var16.keySet().stream();
         Map var19 = var17.purxiy$events();
         if (var18.anyMatch(var19::containsKey)) {
            throw new IllegalStateException("An existing sound event cannot be replaced by a payload");
         } else {
            long var21 = ++sequence;
            owners.put(var21, Map.copyOf(var16));
            bind(var16);
            return () -> {
               owner();
               Map var2x = owners.remove(var21);
               if (var2x != null) {
                  class_1144 var3x = class_310.method_1551().method_1483();
                  OwnedSoundAccess var4x = (OwnedSoundAccess)var3x;
                  var2x.forEach((var2xx, var3xx) -> {
                     var3x.method_4875((class_2960)(var2xx), (class_3419)null);
                     var4x.purxiy$events().remove(var2xx, ((ClientLoader.ResourceReload)(Object)var3xx).event());

                     for (class_1111 var5 : ((ClientLoader.ResourceReload)(Object)var3xx).files()) {
                        var4x.purxiy$files().remove(var5.method_4766());
                     }
                  });
               }
            };
         }
      } else {
         throw new IllegalArgumentException("Owned sound catalog limit");
      }
   }

   private static void bind(Map<class_2960, ClientLoader.ResourceReload> var0) {
      OwnedSoundAccess var1 = (OwnedSoundAccess)class_310.method_1551().method_1483();
      var0.forEach((var1x, var2) -> {
         var1.purxiy$events().put(var1x, var2.event());

         for (class_1111 var4 : var2.files()) {
            var1.purxiy$files().put(var4.method_4766(), ResourceProvider.resource(var4.method_4766()).orElseThrow());
         }
      });
   }

   public static void reload() {
      owner();

      for (Map var1 : owners.values()) {
         bind(var1);
      }
   }

   private record ResourceReload(class_1146 event, List<class_1111> files) {
   }
}
