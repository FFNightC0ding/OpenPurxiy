// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import com.mojang.blaze3d.shaders.ShaderType;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.mixin.ShaderPreprocessorAccess;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.HexFormat;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3262;
import net.minecraft.class_3298;
import net.minecraft.class_3300;

public final class ClientCodec {
   private static final Map<Long, ClientCodec.TypeI> owners = new LinkedHashMap<>();
   private static final Map<class_2960, String> published = new HashMap<>();
   private static long sequence = 0L;

   private ClientCodec() {
   }

   private static void check() {
      if (!class_310.method_1551().method_18854()) {
         throw new IllegalStateException("Shader source owner thread");
      }
   }

   public static int owners() {
      check();
      return owners.size();
   }

   public static int cachedOwnedShaders() {
      check();
      return ClientCache.cachedShaders(published.keySet());
   }

   public static AutoCloseable install(ModuleContext var0, List<String> var1) throws Exception {
      check();
      if (owners.size() < 128 && !var1.isEmpty() && var1.size() <= 256) {
         LinkedHashSet var2 = new LinkedHashSet();
         HashMap var3 = new HashMap();

         for (String var5 : var1) {
            if (!var5.matches("shaders/(core|include)/[a-z0-9_./-]+\\.(vsh|fsh|glsl)") || var5.contains("..") || var5.contains("//")) {
               throw new IllegalArgumentException("Unsafe shader resource path");
            }

            class_2960 var6 = ClientServices.id(var5);
            if (!var2.add(var6)) {
               throw new IllegalArgumentException("Duplicate shader resource");
            }

            byte[] var7 = read(var0, var5);

            try {
               var3.put(var6, HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(var7)));
            } finally {
               Arrays.fill(var7, (byte)0);
            }

            if (published.containsKey(var6) && !published.get(var6).equals(var3.get(var6))) {
               throw new IllegalStateException("Changed shader requires a client restart");
            }
         }

         if (published.size() + var3.keySet().stream().filter(var0x -> !published.containsKey(var0x)).count() > 2048L) {
            throw new IllegalStateException("Shader catalog limit");
         } else {
            long var11 = ++sequence;
            published.putAll(var3);
            owners.put(var11, new ClientCodec.TypeI(var0, Set.copyOf(var2), new HashMap<>()));
            return () -> {
               check();
               ClientCodec.TypeI var2x = owners.remove(var11);
               if (var2x != null) {
                  var2x.compiled().clear();
                  HashSet var3x = new HashSet();

                  for (ClientCodec.TypeI var5x : owners.values()) {
                     var3x.addAll(var5x.paths());
                  }

                  ClientCache.releaseShaders(var2x.paths(), var3x);
               }
            };
         }
      } else {
         throw new IllegalArgumentException("Shader resource limits");
      }
   }

   private static byte[] read(ModuleContext var0, String var1) throws IOException {
      byte[] var3;
      try (InputStream var2 = var0.resource("assets/purxiy/" + var1)) {
         if (var2 == null) {
            throw new FileNotFoundException("Missing owned shader resource");
         }

         byte[] var4 = var2.readNBytes(262145);
         if (var4.length > 262144) {
            Arrays.fill(var4, (byte)0);
            throw new IOException("Shader size limit");
         }

         var3 = var4;
      }

      return var3;
   }

   private static InputStream open(long var0, String var2) throws IOException {
      check();
      ClientCodec.TypeI var3 = owners.get(var0);
      if (var3 == null) {
         throw new IOException("Shader owner closed");
      } else {
         return new ByteArrayInputStream(read(var3.context(), var2));
      }
   }

   public static String shader(class_2960 var0, ShaderType var1) {
      check();
      if (!var0.method_12836().equals("purxiy")) {
         return null;
      } else {
         String var2 = var1 == ShaderType.VERTEX ? ".vsh" : ".fsh";
         String var3 = var0.method_12832();
         class_2960 var4 = ClientServices.id("shaders/" + var3 + var2);
         ClientCodec.TypeI var5 = null;

         for (ClientCodec.TypeI var7 : owners.values()) {
            if (var7.paths().contains(var4)) {
               var5 = var7;
               break;
            }
         }

         if (var5 == null) {
            return null;
         } else {
            var3 = String.valueOf(var0);
            String var22 = var3 + ":" + var1;
            if (var5.compiled().containsKey(var22)) {
               return var5.compiled().get(var22);
            } else {
               try {
                  class_3300 var23 = class_310.method_1551().method_1478();
                  HashMap var8 = new HashMap<>(var23.method_14488("shaders/include", var0x -> var0x.method_12832().endsWith(".glsl")));
                  class_3262 var9 = var23.method_14486(ClientServices.id("shaders/include/frame.glsl")).orElseThrow().method_45304();

                  for (Entry var11 : owners.entrySet()) {
                     long var12 = (Long)var11.getKey();

                     for (class_2960 var15 : ((ClientCodec.TypeI)var11.getValue()).paths()) {
                        var8.put(var15, new class_3298(var9, () -> open(var12, var15.method_12832())));
                     }
                  }

                  byte[] var24 = read(var5.context(), var4.method_12832());

                  String var25;
                  try {
                     var25 = String.join("", ShaderPreprocessorAccess.purxiy$preprocessor(var8, var4).method_34229(new String(var24, StandardCharsets.UTF_8)));
                  } finally {
                     Arrays.fill(var24, (byte)0);
                  }

                  var5.compiled().put(var22, var25);
                  return var25;
               } catch (Exception var20) {
                  throw new IllegalStateException("Owned shader preprocessing failed", var20);
               }
            }
         }
      }
   }

   private record TypeI(ModuleContext context, Set<class_2960> paths, Map<String, String> compiled) {
   }
}
