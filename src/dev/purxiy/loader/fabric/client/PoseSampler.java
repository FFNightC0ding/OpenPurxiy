package dev.purxiy.loader.fabric.client;

import org.joml.Matrix4f;

@FunctionalInterface
public interface PoseSampler {
   void at(float var1, Matrix4f var2);
}
