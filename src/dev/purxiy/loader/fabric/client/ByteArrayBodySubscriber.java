package dev.purxiy.loader.fabric.client;

import java.io.IOException;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodySubscriber;
import java.net.http.HttpResponse.BodySubscribers;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Flow.Subscription;

public final class ByteArrayBodySubscriber implements BodySubscriber<byte[]> {
   private final BodySubscriber<byte[]> delegate = BodySubscribers.ofByteArray();
   private final int limit;
   private Subscription subscription;
   private long received;
   private boolean terminal;

   private ByteArrayBodySubscriber(int var1) {
      if (var1 >= 1 && var1 <= 8388608) {
         this.limit = var1;
      } else {
         throw new IllegalArgumentException("HTTP body limit");
      }
   }

   public static BodyHandler<byte[]> handler(int var0) {
      return var1 -> new ByteArrayBodySubscriber(var0);
   }

   @Override
   public CompletionStage<byte[]> getBody() {
      return this.delegate.getBody();
   }

   @Override
   public void onSubscribe(Subscription var1) {
      if (this.subscription != null) {
         var1.cancel();
      } else {
         this.subscription = var1;
         this.delegate.onSubscribe(var1);
      }
   }

   public void onNext(List<ByteBuffer> var1) {
      if (!this.terminal) {
         for (ByteBuffer var3 : var1) {
            this.received = this.received + var3.remaining();
         }

         if (this.received > this.limit) {
            this.terminal = true;
            this.subscription.cancel();
            this.delegate.onError(new IOException("HTTP body size limit"));
         } else {
            this.delegate.onNext(var1);
         }
      }
   }

   @Override
   public void onError(Throwable var1) {
      if (!this.terminal) {
         this.terminal = true;
         this.delegate.onError(var1);
      }
   }

   @Override
   public void onComplete() {
      if (!this.terminal) {
         this.terminal = true;
         this.delegate.onComplete();
      }
   }
}
