package dev.purxiy.theme;

record ColorPair(int rgb, int on, int off) {
   static final int BOLD = 1;
   static final int ITALIC = 2;
   static final int UNDERLINED = 4;
   static final int STRIKETHROUGH = 8;
   static final int OBFUSCATED = 16;
   static final int ALL = 31;

   static ColorPair color(int var0) {
      return new ColorPair(var0, 0, 31);
   }

   ColorPair with(int var1) {
      return new ColorPair(this.rgb, this.on | var1, this.off & ~var1);
   }

   boolean has(int var1) {
      return (this.on & var1) != 0;
   }

   boolean clears(int var1) {
      return (this.off & var1) != 0;
   }
}
