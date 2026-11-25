package dev.purxiy.loader.fabric;

import com.sun.jna.platform.win32.Crypt32Util;
import dev.purxiy.loader.auth.DeviceIdentity;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclEntryType;
import java.nio.file.attribute.AclFileAttributeView;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.EnumSet;
import java.util.List;

final class WindowsDeviceIdentity implements DeviceIdentity {
   private final Path file;
   private PrivateKey privateKey;
   private PublicKey publicKey;
   private boolean closed;

   WindowsDeviceIdentity(Path var1) {
      this.file = var1;
   }

   private synchronized void load() throws Exception {
      if (this.closed) {
         throw new IllegalStateException("Device identity closed");
      } else if (this.privateKey == null) {
         if (!Files.exists(this.file)) {
            Files.createDirectories(this.file.getParent());
            KeyPair var1 = KeyPairGenerator.getInstance("Ed25519").generateKeyPair();
            ByteArrayOutputStream var2 = new ByteArrayOutputStream();
            DataOutputStream var3 = new DataOutputStream(var2);
            byte[] var4 = var1.getPrivate().getEncoded();
            byte[] var5 = var1.getPublic().getEncoded();
            var3.writeInt(1347573828);
            var3.writeInt(1);
            var3.writeInt(var4.length);
            var3.write(var4);
            var3.writeInt(var5.length);
            var3.write(var5);
            byte[] var6 = var2.toByteArray();

            byte[] var7;
            try {
               var7 = Crypt32Util.cryptProtectData(var6);
            } finally {
               Arrays.fill(var6, (byte)0);
               Arrays.fill(var4, (byte)0);
            }

            Path var8 = Files.createTempFile(this.file.getParent(), "device-", ".tmp");

            try {
               AclFileAttributeView var9 = Files.getFileAttributeView(var8, AclFileAttributeView.class);
               if (var9 == null) {
                  throw new IOException("Windows credential ACL unavailable");
               }

               var9.setAcl(
                  List.of(
                     AclEntry.newBuilder()
                        .setType(AclEntryType.ALLOW)
                        .setPrincipal(Files.getOwner(var8))
                        .setPermissions(EnumSet.allOf(AclEntryPermission.class))
                        .build()
                  )
               );
               Files.write(var8, var7);

               try {
                  Files.move(var8, this.file);
               } catch (FileAlreadyExistsException var32) {
               }
            } finally {
               Arrays.fill(var7, (byte)0);
               Files.deleteIfExists(var8);
            }
         }

         if (Files.size(this.file) > 8192L || Files.size(this.file) == 0L) {
            throw new IOException("Device credential dimensions");
         } else {
            byte[] var37 = Crypt32Util.cryptUnprotectData(Files.readAllBytes(this.file));
            byte[] var38 = null;

            try (DataInputStream var39 = new DataInputStream(new ByteArrayInputStream(var37))) {
               if (var39.readInt() != 1347573828 || var39.readInt() != 1) {
                  throw new IOException("Device credential format");
               } else {
                  int var40 = var39.readInt();
                  if (var40 < 1 || var40 > 256) {
                     throw new IOException("Private key dimensions");
                  } else {
                     var38 = var39.readNBytes(var40);
                     int var41 = var39.readInt();
                     if (var41 < 1 || var41 > 256) {
                        throw new IOException("Public key dimensions");
                     } else {
                        byte[] var42 = var39.readNBytes(var41);
                        if (var38.length != var40 || var42.length != var41 || var39.read() != -1) {
                           throw new IOException("Truncated device key");
                        } else {
                           KeyFactory var43 = KeyFactory.getInstance("Ed25519");
                           PrivateKey var44 = var43.generatePrivate(new PKCS8EncodedKeySpec(var38));
                           PublicKey var45 = var43.generatePublic(new X509EncodedKeySpec(var42));
                           Signature var10 = Signature.getInstance("Ed25519");
                           var10.initSign(var44);
                           var10.update(new byte[]{1, 2, 3});
                           byte[] var11 = var10.sign();
                           var10.initVerify(var45);
                           var10.update(new byte[]{1, 2, 3});
                           if (var10.verify(var11)) {
                              this.privateKey = var44;
                              this.publicKey = var45;
                           } else {
                              throw new GeneralSecurityException("Device key pair mismatch");
                           }
                        }
                     }
                  }
               }
            } finally {
               Arrays.fill(var37, (byte)0);
               if (var38 != null) {
                  Arrays.fill(var38, (byte)0);
               }
            }
         }
      }
   }

   @Override
   public synchronized String publicKey() {
      try {
         this.load();
         return Base64.getEncoder().encodeToString(this.publicKey.getEncoded());
      } catch (Exception var2) {
         throw new IllegalStateException("Device identity unavailable; restore or relink this installation");
      }
   }

   @Override
   public synchronized byte[] sign(byte[] var1) throws Exception {
      this.load();
      Signature var2 = Signature.getInstance("Ed25519");
      var2.initSign(this.privateKey);
      var2.update(var1);
      return var2.sign();
   }

   @Override
   public synchronized void close() {
      this.closed = true;
      this.privateKey = null;
      this.publicKey = null;
   }
}
