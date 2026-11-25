package dev.purxiy.render.pipeline;

public final class PipelineHandle {
   public static final int RECT = 0;
   public static final int SHADOW = 1;
   public static final int ARC = 2;
   public static final int IMAGE = 3;
   public static final int TEXT = 4;
   public static final int GLASS = 5;
   public static final int FLAG_STRAIGHT_ALPHA = 1;
   public static final int FLAG_ROUND_CAPS = 2;
   public static final int FLAG_CONIC = 4;
   public static final int FLAG_RADIAL = 8;
   public static final int FLAG_ADDITIVE = 16;
   public static final int FLAG_GRADIENT_BORDER = 32;
   public static final int FLAG_SHEEN = 64;
   public static final int FLAG_LOGO = 128;

   private PipelineHandle() {
   }
}
