// Sourced By DevFFNightmare
package dev.purxiy.loader.security;

import java.time.Instant;
import java.util.List;

public final class ModuleSpec {
   public static final int API_VERSION = 1;
   public static int b = 0;
   private final String id;
   private final String build;
   private final String entrypoint;
   private final List<String> packages;
   private final Instant expiresAt;

   public ModuleSpec(String id, String build, String entrypoint, List<String> packages, Instant expiresAt) {
      this.id = id;
      this.build = build;
      this.entrypoint = entrypoint;
      this.packages = List.copyOf(packages);
      this.expiresAt = expiresAt;
   }

   public boolean owns(String name) {
      if (name == null) {
         return false;
      }

      for (String prefix : this.packages) {
         if (name.startsWith(prefix)) {
            return true;
         }
      }

      return false;
   }

   public String id() {
      return this.id;
   }

   public String build() {
      return this.build;
   }

   public String entrypoint() {
      return this.entrypoint;
   }

   public List<String> packages() {
      return this.packages;
   }

   public Instant expiresAt() {
      return this.expiresAt;
   }

   @Override
   public String toString() {
      return "ModuleSpec[" + this.id + ", " + this.build + "]";
   }

   @Override
   public int hashCode() {
      return this.id.hashCode();
   }

   @Override
   public boolean equals(Object other) {
      return other instanceof ModuleSpec spec && this.id.equals(spec.id) && this.build.equals(spec.build);
   }
}
