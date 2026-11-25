package dev.purxiy.loader.security;

public final class ModuleEnvelope {
   private final byte[] manifest;
   private final byte[] signature;
   private final byte[] ciphertext;

   public ModuleEnvelope(byte[] manifest, byte[] signature, byte[] ciphertext) {
      if (manifest == null || manifest.length == 0) {
         throw new IllegalArgumentException("empty manifest");
      }
      if (signature == null || signature.length == 0) {
         throw new IllegalArgumentException("empty signature");
      }
      if (ciphertext == null || ciphertext.length == 0) {
         throw new IllegalArgumentException("empty ciphertext");
      }
      this.manifest = manifest.clone();
      this.signature = signature.clone();
      this.ciphertext = ciphertext.clone();
   }

   public byte[] manifest() { return this.manifest.clone(); }

   public byte[] signature() { return this.signature.clone(); }

   public byte[] ciphertext() { return this.ciphertext.clone(); }
}
