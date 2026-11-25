package dev.purxiy.modules.nick;

import java.util.Arrays;
import net.minecraft.class_2583;
import net.minecraft.class_5224;
import net.minecraft.class_5481;

public final class NickHiderFactory {
   private final int[] y;
   private final int[] e;
   private final int[] a;
   private final NickHiderData[] j;

   private NickHiderFactory(int[] var1, int[] var2, int[] var3, NickHiderData[] var4) {
      this.y = var1;
      this.e = var2;
      this.a = var3;
      this.j = var4;
   }

   public static NickHiderFactory of(String var0, String var1) {
      NickHiderBuilder var2 = NickHiderCache.table();
      if (var2 == null) {
         return null;
      } else {
         String var3 = var1 == null ? var0 : var0 + var1;
         NickHiderMasks var4 = NickHiderMasks.acquire();

         NickHiderFactory var16;
         try {
            int var5 = 0;

            while (var5 < var3.length()) {
               int var6 = var3.codePointAt(var5);
               var4.add(var5, var6, null, null);
               var5 += Character.charCount(var6);
            }

            if (!var4.run(var2)) {
               Object var14 = null;
               return (NickHiderFactory)var14;
            }

            int[] var15 = new int[var3.length()];
            int[] var7 = new int[var3.length()];
            int var8 = 0;

            for (int var9 = 0; var9 < var4.D; var9++) {
               int var10 = var4.W[var9];
               var15[var10] = var8;

               while (var8 < var4.U && var4.q[var8] == var9) {
                  var8++;
               }

               var7[var10] = var8;
            }

            var16 = new NickHiderFactory(var15, var7, Arrays.copyOf(var4.A, var4.U), Arrays.copyOf(var4.K, var4.U));
         } finally {
            var4.release();
         }

         return var16;
      }
   }

   public class_5481 segment(class_5481 var1, int var2) {
      return new NickHiderFactory.NickSegment(this, var1, var2);
   }

   public class_5481 segment(String var1, int var2) {
      return this.segment(class_5481.method_30747(var1, class_2583.field_24360), var2);
   }

   private record NickSegment(NickHiderFactory s, class_5481 K, int c) implements class_5481, NickHiderText.Listener {
      public boolean accept(class_5224 var1) {
         NickHiderFactory var2 = this.s;
         int[] var3 = new int[]{this.c};
         int[] var4 = new int[]{0};
         return this.K.accept((var4xx, var5, var6) -> {
            NickHiderCodec.ensureSourceClassInitialized();
            int var7 = var3[0];
            var3[0] += Character.charCount(var6);
            if (var7 >= 0 && var7 < var2.y.length) {
               for (int var8 = var2.y[var7]; var8 < var2.e[var7]; var8++) {
                  if (!var1.accept(var4[0]++, NickHiderText.paint(var5, var2.j[var8]), var2.a[var8])) {
                     return false;
                  }
               }

               return true;
            } else {
               return var1.accept((int)(var4[0]++), var5, var6);
            }
         });
      }

      public NickHiderFactory mask() {
         return this.s;
      }

      public class_5481 formatted() {
         return this.K;
      }

      public int offset() {
         return this.c;
      }

      public NickHiderFactory nicknameSegmentVisitorState1() {
         return this.s;
      }

      public class_5481 minecraftObjectState1() {
         return this.K;
      }

      public int integerState39() {
         return this.c;
      }
   }
}
