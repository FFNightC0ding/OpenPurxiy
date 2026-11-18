// Sourced By DevFFNightmare
package dev.purxiy.render;

import com.mojang.blaze3d.buffers.GpuBuffer;
import dev.purxiy.render.pipeline.VertexBuffer;
import java.nio.ByteBuffer;
import org.joml.Matrix4f;

final class ClipRect {
   final String label;
   final VertexBuffer quads = new VertexBuffer();
   final Canvas canvas = new Canvas(this.quads);
   final Matrix4f projection = new Matrix4f();
   final RenderPipelines.TypeM stats = new RenderPipelines.TypeM();
   float userScale = 1.0F;
   float pixelScale = 1.0F;
   boolean open;
   GpuBuffer vertexBuffer;
   GpuBuffer frameUniforms;
   ByteBuffer frameStaging;

   ClipRect(String var1) {
      this.label = var1;
   }
}
