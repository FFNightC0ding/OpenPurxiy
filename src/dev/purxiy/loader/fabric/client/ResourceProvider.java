package dev.purxiy.loader.fabric.client;

import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3262;
import net.minecraft.class_3298;

public final class ResourceProvider {
   private static final Map<class_2960, List<ResourceProvider.ResourceEntry>> entries = new HashMap<>();
   private static long sequence = 0L;

   private ResourceProvider() {
   }

   public static synchronized int owners() {
      return (int)entries.values().stream().flatMap(Collection::stream).map(ResourceProvider.ResourceEntry::owner).distinct().count();
   }

   public static AutoCloseable install(ModuleContext var0, List<String> var1) throws Exception {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Asset admission requires owner thread");
      } else if (!var1.isEmpty() && var1.size() <= 2048) {
         class_3262 var2 = class_310.method_1551().method_1478().method_14486(ClientServices.id("textures/brand/mark.png")).orElseThrow().method_45304();
         LinkedHashMap var3 = new LinkedHashMap();
         long var4 = 0L;

         try {
            for (String var8 : var1) {
               if (var8.matches("assets/purxiy/[a-zA-Z0-9_./-]+")
                  && !var8.contains("..")
                  && !var8.contains("//")
                  && var8.matches(".*\\.(png|ogg|json|glsl|vsh|fsh|glb|gltf|bin|obj|mtl|txt|md|vxm|pgv|mcmeta)$")) {
                  class_2960 var27 = ClientServices.id(var8.substring("assets/purxiy/".length()));
                  if (var3.containsKey(var27)) {
                     throw new IllegalArgumentException("Duplicate visual resource");
                  }

                  byte[] var29;
                  try (InputStream var28 = var0.resource(var8)) {
                     var29 = var28.readNBytes(8388609);
                  }

                  if (var29.length <= 8388608 && (var4 += var29.length) <= 67108864L) {
                     var3.put(var27, var29);
                     continue;
                  }

                  Arrays.fill(var29, (byte)0);
                  throw new IllegalArgumentException("Visual asset size limit");
               }

               throw new IllegalArgumentException("Unsafe visual resource");
            }

            long var26;
            synchronized (ResourceProvider.class) {
               HashMap var10 = new HashMap();

               for (Entry var12 : ((Iterable<Entry>)(Object)var3.entrySet())) {
                  String var13 = HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest((byte[])var12.getValue()));
                  var10.put((class_2960)var12.getKey(), var13);

                  for (ResourceProvider.ResourceEntry var15 : entries.getOrDefault(var12.getKey(), List.of())) {
                     if (!var15.digest().equals(var13)) {
                        throw new IllegalStateException("Two active modules supplied conflicting visual assets");
                     }
                  }
               }

               var26 = ++sequence;
               var3.forEach((var4x, var5) -> {
                  ArrayList var6 = new ArrayList<>(entries.getOrDefault(var4x, List.of()));
                  var6.add(new ResourceProvider.ResourceEntry(var26, (byte[])(var5), (String)var10.get(var4x), var2));
                  entries.put((class_2960)(var4x), List.copyOf(var6));
               });
            }

            var3.clear();
            return () -> {
               if (!class_310.method_1551().method_18854()) {
                  throw new IllegalStateException("Visual resource disposal owner thread");
               } else {
                  synchronized (ResourceProvider.class) {
                     Iterator var3x = entries.entrySet().iterator();

                     while (var3x.hasNext()) {
                        Entry var4x = (Entry)var3x.next();
                        ArrayList var5 = new ArrayList();

                        for (ResourceProvider.ResourceEntry var7 : ((Iterable<ResourceProvider.ResourceEntry>)(Object)(List)var4x.getValue())) {
                           if (var7.owner() == var26) {
                              Arrays.fill(var7.bytes(), (byte)0);
                           } else {
                              var5.add(var7);
                           }
                        }

                        if (var5.isEmpty()) {
                           var3x.remove();
                        } else {
                           var4x.setValue(List.copyOf(var5));
                        }
                     }
                  }
               }
            };
         } finally {
            var3.values().forEach(var0x -> Arrays.fill((byte[])var0x, (byte)0));
         }
      } else {
         throw new IllegalArgumentException("Visual asset entry limit");
      }
   }

   private static synchronized InputStream open(class_2960 var0, long var1) throws IOException {
      for (ResourceProvider.ResourceEntry var4 : entries.getOrDefault(var0, List.of())) {
         if (var4.owner() == var1) {
            return new ByteArrayInputStream((byte[])var4.bytes().clone());
         }
      }

      throw new IOException("Visual resource owner closed");
   }

   public static synchronized Optional<class_3298> resource(class_2960 var0) {
      List<ResourceProvider.ResourceEntry>var1 = entries.get(var0);
      if (var1 != null && !var1.isEmpty()) {
         ResourceProvider.ResourceEntry var2 = (ResourceProvider.ResourceEntry)var1.getFirst();
         long var3 = var2.owner();
         return Optional.of(new class_3298(var2.source(), () -> open(var0, var3)));
      } else {
         return Optional.empty();
      }
   }

   private record ResourceEntry(long owner, byte[] bytes, String digest, class_3262 source) {
   }
}
