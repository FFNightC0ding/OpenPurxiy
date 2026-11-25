package dev.purxiy.render.pipeline;

import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import com.mojang.blaze3d.vertex.VertexFormatElement.Type;
import com.mojang.blaze3d.vertex.VertexFormatElement.Usage;

public final class SkyVertexFormat {
   public static final VertexFormatElement POS_LOCAL = allocate(Type.FLOAT, Usage.GENERIC, 4);
   public static final VertexFormatElement UV_SIZE = allocate(Type.FLOAT, Usage.GENERIC, 4);
   public static final VertexFormatElement RADII = allocate(Type.FLOAT, Usage.GENERIC, 4);
   public static final VertexFormatElement PARAMS = allocate(Type.FLOAT, Usage.GENERIC, 4);
   public static final VertexFormatElement CLIP = allocate(Type.FLOAT, Usage.GENERIC, 4);
   public static final VertexFormatElement EXTRA = allocate(Type.FLOAT, Usage.GENERIC, 4);
   public static final VertexFormatElement COLOR2 = allocate(Type.UBYTE, Usage.COLOR, 4);
   public static final VertexFormat UI = VertexFormat.builder()
      .add("PosLocal", POS_LOCAL)
      .add("UvSize", UV_SIZE)
      .add("Radii", RADII)
      .add("Params", PARAMS)
      .add("Clip", CLIP)
      .add("Extra", EXTRA)
      .add("Color", VertexFormatElement.COLOR)
      .add("Color2", COLOR2)
      .build();
   public static final int VERTEX_SIZE = UI.getVertexSize();
   public static final int QUAD_SIZE = VERTEX_SIZE * 4;
   public static final VertexFormat AMBIENCE = VertexFormat.builder()
      .add("Center", POS_LOCAL)
      .add("Shape", UV_SIZE)
      .add("Orient", RADII)
      .add("Color", VertexFormatElement.COLOR)
      .add("Color2", COLOR2)
      .build();
   public static final int AMBIENCE_VERTEX_SIZE = AMBIENCE.getVertexSize();
   public static final int OFF_POS_LOCAL = 0;
   public static final int OFF_UV_SIZE = 16;
   public static final int OFF_RADII = 32;
   public static final int OFF_PARAMS = 48;
   public static final int OFF_CLIP = 64;
   public static final int OFF_EXTRA = 80;
   public static final int OFF_COLOR = 96;
   public static final int OFF_COLOR2 = 100;

   private SkyVertexFormat() {
   }

   private static VertexFormatElement allocate(Type var0, Usage var1, int var2) {
      for (int var3 = 31; var3 > 6; var3--) {
         if (VertexFormatElement.byId(var3) == null) {
            return VertexFormatElement.register(var3, 0, var0, var1, var2);
         }
      }

      throw new IllegalStateException("SourceByDevFFnightmare DeObfDeCompByGuideX: no free vertex format element ids left");
   }

   static {
      if (VERTEX_SIZE != 104) {
         throw new IllegalStateException("Unexpected SourceByDevFFnightmare DeObfDeCompByGuideX vertex size " + VERTEX_SIZE);
      } else if (AMBIENCE_VERTEX_SIZE != 56) {
         throw new IllegalStateException("Unexpected SourceByDevFFnightmare DeObfDeCompByGuideX ambience vertex size " + AMBIENCE_VERTEX_SIZE);
      }
   }
}
