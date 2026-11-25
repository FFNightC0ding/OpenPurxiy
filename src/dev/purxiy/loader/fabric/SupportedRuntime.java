package dev.purxiy.loader.fabric;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.CodeSource;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public final class SupportedRuntime {
   private static final Set<String> REQUIRED = Set.of(
      "net.jpountz.lz4.LZ4Factory",
      "com.google.common.collect.ImmutableList",
      "io.netty.buffer.ByteBuf",
      "io.netty.util.Version",
      "io.netty.handler.codec.ByteToMessageDecoder",
      "io.netty.handler.codec.compression.ZlibDecoder",
      "io.netty.handler.codec.http.HttpObjectDecoder",
      "io.netty.handler.ssl.SslHandler",
      "io.netty.resolver.AddressResolver",
      "io.netty.channel.Channel",
      "io.netty.channel.epoll.Epoll",
      "io.netty.channel.kqueue.KQueue",
      "io.netty.channel.unix.Unix",
      "org.apache.logging.log4j.LogManager",
      "org.apache.logging.log4j.core.LoggerContext",
      "org.apache.logging.slf4j.Log4jLoggerFactory"
   );

   private SupportedRuntime() {
   }

   public static void require(JsonObject var0) {
      try {
         Map var1 = parse(var0);
         HashMap var2 = new HashMap();

         for (Entry var4 : ((Iterable<Entry>)(Object)var1.entrySet())) {
            Class var5 = Class.forName((String)var4.getKey(), false, SupportedRuntime.class.getClassLoader());
            CodeSource var6 = var5.getProtectionDomain().getCodeSource();
            if (var6 == null || !var6.getLocation().getProtocol().equals("file")) {
               throw new IOException("Unpinned library origin");
            }

            Path var7 = Path.of(var6.getLocation().toURI()).toRealPath();
            String var8 = (String)var2.get(var7);
            if (var8 == null) {
               var8 = hash(var7);
               var2.put(var7, var8);
            }

            if (!var8.equals(var4.getValue())) {
               throw new IOException("Unsupported loaded library: " + (String)var4.getKey());
            }
         }
      } catch (Exception | LinkageError var9) {
         throw new SupportedRuntime.Refused(var9);
      }
   }

   static Map<String, String> parse(JsonObject var0) throws IOException {
      if (var0 != null
         && var0.has("format")
         && var0.get("format").getAsInt() == 1
         && var0.has("id")
         && var0.get("id").getAsString().equals("purxiy-secure-20261114")
         && var0.has("libraries")
         && var0.get("libraries").isJsonObject()) {
         JsonObject var1 = var0.getAsJsonObject("libraries");
         if (!var1.keySet().equals(REQUIRED)) {
            throw new IOException("Incomplete or unexpected runtime policy");
         } else {
            TreeMap var2 = new TreeMap();

            for (String var4 : REQUIRED) {
               JsonElement var5 = var1.get(var4);
               if (!var5.isJsonPrimitive() || !var5.getAsJsonPrimitive().isString() || !var5.getAsString().matches("[a-f0-9]{64}")) {
                  throw new IOException("Invalid runtime library digest");
               }

               var2.put(var4, var5.getAsString());
            }

            return Map.copyOf(var2);
         }
      } else {
         throw new IOException("Missing secure runtime policy");
      }
   }

   private static String hash(Path var0) throws Exception {
      BasicFileAttributes var1 = Files.readAttributes(var0, BasicFileAttributes.class);
      if (var1.isRegularFile() && var1.size() >= 1L && var1.size() <= 33554432L) {
         MessageDigest var2 = MessageDigest.getInstance("SHA-256");

         try (InputStream var3 = Files.newInputStream(var0)) {
            byte[] var4 = new byte[65536];

            int var5;
            while ((var5 = var3.read(var4)) != -1) {
               var2.update(var4, 0, var5);
            }
         }

         BasicFileAttributes var8 = Files.readAttributes(var0, BasicFileAttributes.class);
         if (var1.size() == var8.size() && var1.lastModifiedTime().equals(var8.lastModifiedTime()) && Objects.equals(var1.fileKey(), var8.fileKey())) {
            return HexFormat.of().formatHex(var2.digest());
         } else {
            throw new IOException("Library changed while checking");
         }
      } else {
         throw new IOException("Library dimensions");
      }
   }

   public static final class Refused extends IllegalStateException {
      private Refused(Throwable var1) {
         super("SourceByDevFFnightmare DeObfDeCompByGuideX requires the secure Fabric startup configuration. Install the supported SourceByDevFFnightmare DeObfDeCompByGuideX runtime before starting this build.", var1);
      }
   }
}
