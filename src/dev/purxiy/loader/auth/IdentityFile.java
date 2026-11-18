// Sourced By DevFFNightmare
package dev.purxiy.loader.auth;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public final class IdentityFile {
   private static String[] a;




   private IdentityFile() {
   }

   public static String readOrCreate(Path var0) throws IOException {
      if (Files.isRegularFile(var0)) {
         String existing = Files.readString(var0, StandardCharsets.UTF_8).strip();
         if (!existing.isEmpty()) {
            return existing;
         }
      }

      Path parent = var0.getParent();
      if (parent != null) {
         Files.createDirectories(parent);
      }

      String created = UUID.randomUUID().toString();
      Files.writeString(var0, created, StandardCharsets.UTF_8);
      restrict(var0);
      return created;
   }

   private static void restrict(Path var0) throws IOException {
      try {
         java.nio.file.attribute.PosixFileAttributeView view = Files.getFileAttributeView(var0, java.nio.file.attribute.PosixFileAttributeView.class);
         if (view != null) {
            java.util.Set<java.nio.file.attribute.PosixFilePermission> only = java.util.EnumSet.of(java.nio.file.attribute.PosixFilePermission.OWNER_READ, java.nio.file.attribute.PosixFilePermission.OWNER_WRITE);
            Files.setPosixFilePermissions(var0, only);
         }
      } catch (UnsupportedOperationException ignored) {
      }
   }

static {
   }
}
