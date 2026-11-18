// Sourced By DevFFNightmare
package dev.purxiy.modules.mount;

public final class MountSupport2 {
   private boolean f;
   private int o;

   public void key(boolean var1) {
      if (var1 && !this.f) {
         this.o = Math.min(32, this.o + 1);
      }

      this.f = var1;
   }

   public boolean take() {
      if (this.o == 0) {
         return false;
      } else {
         this.o--;
         return true;
      }
   }

   public void discard() {
      this.o = 0;
   }
}
