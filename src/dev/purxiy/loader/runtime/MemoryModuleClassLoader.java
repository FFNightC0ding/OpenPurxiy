// Sourced By DevFFNightmare
package dev.purxiy.loader.runtime;

import dev.purxiy.loader.security.ModuleSpec;
import dev.purxiy.loader.security.VerifiedModule;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.SecureClassLoader;
import java.util.Arrays;
import java.util.Map;

final class MemoryModuleClassLoader extends SecureClassLoader implements AutoCloseable {
   private final ModuleSpec spec;
   private final Map<String, byte[]> entries;
   private boolean closed;
   private int definedCount;

   MemoryModuleClassLoader(VerifiedModule module, ClassLoader parent) {
      super(parent);
      this.spec = module.spec();
      this.entries = module.transfer();
   }

   @Override
   protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
      synchronized (this.getClassLoadingLock(name)) {
         if (!this.spec.owns(name)) {
            return super.loadClass(name, resolve);
         } else {
            Class<?> loaded = this.findLoadedClass(name);
            if (loaded == null) {
               loaded = this.findClass(name);
            }

            if (resolve) {
               this.resolveClass(loaded);
            }

            return loaded;
         }
      }
   }

   @Override
   protected synchronized Class<?> findClass(String name) throws ClassNotFoundException {
      if (!this.closed && this.spec.owns(name)) {
         String path = name.replace('.', '/') + ".class";
         byte[] bytes = this.entries.get(path);
         if (bytes == null) {
            throw new ClassNotFoundException(name);
         } else {
            Class<?> defined = this.defineClass(name, bytes, 0, bytes.length);
            this.entries.remove(path);
            Arrays.fill(bytes, (byte)0);
            this.definedCount++;
            return defined;
         }
      } else {
         throw new ClassNotFoundException(name);
      }
   }

   synchronized int definedCount() {
      return this.definedCount;
   }

   boolean owns(String className) {
      return this.spec.owns(className);
   }

   InputStream moduleResource(String name) {
      if (!VerifiedModule.safePath(name)) {
         throw new IllegalArgumentException("Unsafe resource path");
      } else {
         synchronized (this) {
            if (this.closed) {
               throw new IllegalStateException("Module classloader is closed");
            } else if (name.endsWith(".class")) {
               return null;
            } else {
               byte[] bytes = this.entries.get(name);
               return bytes == null ? null : new ByteArrayInputStream((byte[])bytes.clone());
            }
         }
      }
   }

   @Override
   public InputStream getResourceAsStream(String name) {
      if (VerifiedModule.safePath(name) && !name.endsWith(".class")) {
         InputStream local = this.moduleResource(name);
         if (local != null) {
            return local;
         }
      }

      return super.getResourceAsStream(name);
   }

   @Override
   public synchronized void close() {
      this.closed = true;
      VerifiedModule.wipe(this.entries);
   }
}
