package dev.purxiy.loader.runtime;

final class OwnerThread {
   private final Thread owner = Thread.currentThread();

   void check() {
      if (Thread.currentThread() != this.owner) {
         throw new IllegalStateException("Module lifecycle must run on the owner thread");
      }
   }
}
