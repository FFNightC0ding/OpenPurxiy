package dev.purxiy.modules.spotify;

import dev.purxiy.render.font.TrueTypeFont;
import java.util.ArrayList;
import java.util.List;

final class SpotifyUpdater2<S> {
   final List<SpotifyUpdater2.TypeH<S>> h = new ArrayList<>();
   private int J;
   private SpotifyUpdater4.TypeM<?> f;
   private int i;
   private float S;

   void update(SpotifyUpdater4<?> var1, double var2, boolean var4, SpotifyUpdater2.Listener<S> var5) {
      long var6 = 128392513063360L;
      long var8 = var6 ^ 57094780861591L;
      float var10 = var1.clock();
      SpotifyUpdater4.TypeM var11 = var4 ? var1.front() : null;
      if (var11 != null && var11.C && var11.t.k == SpotifyUpdater4.TypeA.G && !var11.U) {
         SpotifyUpdater4.TypeN var12 = var11.t;
         if (var11 != this.f) {
            this.f = var11;
            this.i = 0;
         }

         int var13 = this.i;

         while (var13 < var12.o.length && var2 >= var12.F[var13] - var12.j) {
            var13++;
         }

         for (this.i = Math.max(this.i, var13 - 2); this.i < var13; this.i++) {
            SpotifyUpdater2.TypeH var14 = new SpotifyUpdater2.TypeH(var12, this.i, this.J++, var10);

            for (SpotifyUpdater2.TypeH var16 : this.h) {
               if (!var16.leaving() && var16.M == var14.M) {
                  var16.e = var10;
               }
            }

            var14.Y = var5.place(var14, this.h);
            this.h.add(var14);
         }
      }

      float var17 = 1.0F - (float)Math.exp(-7.0F * Math.max(0.0F, var10 - this.S));
      this.S = var10;
      SpotifyUpdater2.TypeH var18 = null;

      for (int var19 = this.h.size() - 1; var19 >= 0; var19--) {
         SpotifyUpdater2.TypeH var20 = this.h.get(var19);
         if (var20.leaving() || var20.Y != null && this.N(var1, var8, var20.W)) {
            if (!var20.leaving() && !var5.keeps(var20)) {
               var20.e = var10;
               var20.z = true;
            }
         } else {
            var20.e = var10;
         }

         if (var20.leaving() && var10 - var20.e > SpotifyRenderer.breakTime(var20)) {
            this.h.remove(var19);
         } else {
            boolean var21 = !var20.leaving() && var18 == null;
            if (var21) {
               var18 = var20;
            }

            var20.V = var20.V + ((var21 ? 1 : 0) - var20.V) * var17;
         }
      }
   }

   void leaveAll(float var1) {
      for (SpotifyUpdater2.TypeH var3 : this.h) {
         if (!var3.leaving()) {
            var3.e = var1;
         }
      }
   }

   void clear() {
      this.h.clear();
      this.f = null;
   }

   private boolean N(SpotifyUpdater4 var1, long var2, SpotifyUpdater4.TypeN var4) {
      SpotifyCurve.ensureSourceClassInitialized();

      for (SpotifyUpdater4.TypeM var6 : var1.X) {
         long var7 = 78240815494486L;
         int var10 = var7 < 0L ? -1 : 1;
         if (var6.t == var4) {
            boolean var10x = var6.leaving();
            long var11 = 78240815494486L;
            int var12 = var11 < 0L ? -1 : 1;
            if (!var10x && !var6.U) {
               return true;
            }
         }
      }

      return false;
   }

   interface Listener<S> {
      S place(SpotifyUpdater2.TypeH<S> var1, List<SpotifyUpdater2.TypeH<S>> var2);

      boolean keeps(SpotifyUpdater2.TypeH<S> var1);
   }

   static final class TypeH<S> {
      final SpotifyUpdater4.TypeN W;
      final int g;
      final int M;
      final int F;
      final float P;
      float e = Float.NaN;
      boolean z;
      float V = 1.0F;
      S Y;
      private String[] A;
      private float[] k = new float[0];
      private float J;
      private float E;

      TypeH(SpotifyUpdater4.TypeN var1, int var2, int var3, float var4) {
         this.W = var1;
         this.g = var2;
         this.F = var3;
         this.M = (var2 & 1) == 0 ? -1 : 1;
         this.P = var4;
      }

      boolean leaving() {
         return !Float.isNaN(this.e);
      }

      String text() {
         return this.W.o[this.g];
      }

      float width() {
         return this.W.W[this.g];
      }

      String[] letters() {
         long var1 = 97942396084253L;
         long var3 = var1 ^ 19610058588283L;
         this.O(var3);
         return this.A;
      }

      float letterX(int var1) {
         long var2 = 89013063467912L;
         long var4 = var2 ^ 26606637605870L;
         this.O(var4);
         return this.k[var1];
      }

      float inkBelow() {
         long var1 = 25026093692889L;
         long var3 = var1 ^ 103375438157759L;
         this.O(var3);
         return this.J;
      }

      float inkAbove() {
         long var1 = 124896681094190L;
         long var3 = var1 ^ 63023970962504L;
         this.O(var3);
         return this.E;
      }

      private void O(long var1) {
         SpotifyCurve.ensureSourceClassInitialized();
         if (this.A == null) {
            String var3 = this.text();
            ArrayList var4 = new ArrayList();
            int var5 = 0;

            while (var5 < var3.length()) {
               int var6 = var5 + Character.charCount(var3.codePointAt(var5));
               long var7 = 80016031981670L;
               int var19 = var7 < 0L ? -1 : 1;
               var4.add(var3.substring(var5, var6));
               var5 = var6;
            }

            this.A = var4.toArray(new String[0]);
            long var18 = 80016031981669L;
            int var9 = var18 < 0L ? -1 : 1;
            this.k = new float[this.A.length];
            StringBuilder var9x = new StringBuilder();

            for (int var10 = 0; var10 < this.A.length; var10++) {
               String var11 = this.A[var10];
               TrueTypeFont var12 = SpotifyUpdater4.T;
               String var13 = String.valueOf(var9x);
               long var14 = 80016031981669L;
               int var17 = var14 < 0L ? -1 : 1;
               float var17x = var12.measure(var13 + var11, 64.0F, 0.0F);
               this.k[var10] = var17x - SpotifyUpdater4.T.measure(var11, 64.0F, 0.0F);
               var9x.append(var11);
            }

            float[] var19 = SpotifyUpdater4.T.inkExtent(var3);
            long var20 = 80016031981670L;
            int var211 = var20 < 0L ? -1 : 1;
            this.J = var19[0];
            long var21x = 80016031981670L;
            int var141 = var21x < 0L ? -1 : 1;
            this.E = var19[1] > 0.0F ? var19[1] : SpotifyUpdater4.T.capHeight;
         }
      }
   }
}
