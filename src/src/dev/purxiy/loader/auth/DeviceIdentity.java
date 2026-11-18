// Sourced By DevFFNightmare
package dev.purxiy.loader.auth;

public interface DeviceIdentity extends AutoCloseable {
   String publicKey();

   byte[] sign(byte[] var1) throws Exception;

   @Override
   default void close() throws Exception {
   }
}
