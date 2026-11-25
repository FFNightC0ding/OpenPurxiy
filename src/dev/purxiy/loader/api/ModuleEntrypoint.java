package dev.purxiy.loader.api;

public interface ModuleEntrypoint {
   AutoCloseable start(ModuleContext var1) throws Exception;
}
