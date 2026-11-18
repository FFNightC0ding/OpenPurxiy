// Sourced By DevFFNightmare
package dev.purxiy.loader.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodySubscriber;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Flow.Subscription;

public final class BoundedBody implements BodySubscriber<byte[]> {
   private final int limit;
   private final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
   private final CompletableFuture<byte[]> body = new CompletableFuture<>();
   private Subscription subscription;



   private BoundedBody(int var1) {
      this.limit = var1;
   }

   public static BodyHandler<byte[]> handler(int var0) {
      return (responseInfo) -> new BoundedBody(var0);
   }

   @Override
   public CompletionStage<byte[]> getBody() {
      return this.body;
   }

   @Override
   public void onSubscribe(Subscription var1) {
      this.subscription = var1;
      var1.request(Long.MAX_VALUE);
   }

   public void onNext(List<ByteBuffer> var1) {
      for (ByteBuffer buffer : var1) {
         int size = buffer.remaining();
         if (this.bytes.size() + size > this.limit) {
            this.subscription.cancel();
            this.body.completeExceptionally(new IOException("Response body exceeds the configured limit"));
            return;
         }

         byte[] chunk = new byte[size];
         buffer.get(chunk);
         this.bytes.write(chunk, 0, size);
      }
   }

   @Override
   public void onError(Throwable var1) {
      this.body.completeExceptionally(var1);
   }

   @Override
   public void onComplete() {
      this.body.complete(this.bytes.toByteArray());
   }

static {
   }
}
