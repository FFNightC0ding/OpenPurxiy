package dev.purxiy.modules.nick;

record NickHiderData(int h, int Y, int G) {
   static final int X = 1;
   static int R = 2;
   static int v = 4;
   static int W = 8;
   static int j = 16;
   static int H = 31;

   static NickHiderData color(int var0) {
      return new NickHiderData(var0, 0, 31);
   }

   NickHiderData with(int var1) {
      return new NickHiderData(this.h, this.Y | var1, this.G & ~var1);
   }

   boolean has(int var1) {
      return (this.Y & var1) != 0;
   }

   boolean clears(int var1) {
      return (this.G & var1) != 0;
   }

   public int rgb() {
      return this.h;
   }

   public int on() {
      return this.Y;
   }

   public int off() {
      return this.G;
   }

   public int integerState30() {
      return this.h;
   }

   public int integerState42() {
      return this.Y;
   }

   public int integerState18() {
      return this.G;
   }
}
