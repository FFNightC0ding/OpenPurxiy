package dev.purxiy.modules.nametags;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.NameDecorationProvider;
import dev.purxiy.loader.fabric.client.TabListProvider;
import dev.purxiy.loader.fabric.mixin.NameTagNameInvoker;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.RenderPipelines.FogRange;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap.Entry;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import net.fabricmc.fabric.api.client.rendering.v1.FabricRenderState;
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.class_10017;
import net.minecraft.class_10055;
import net.minecraft.class_11890;
import net.minecraft.class_11901;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1311;
import net.minecraft.class_1531;
import net.minecraft.class_1542;
import net.minecraft.class_1569;
import net.minecraft.class_1799;
import net.minecraft.class_1814;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_3726;
import net.minecraft.class_3959;
import net.minecraft.class_3988;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_638;
import net.minecraft.class_898;
import net.minecraft.class_9064;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;

public final class NameTagsCapture {
   private static final RenderStateDataKey<class_1297> I = RenderStateDataKey.create(ClientServices.label(() -> {
      int var0x = 1910037830;
      int var1x = -1910037027;
      int var2 = 1910035528;
      return "purxiy:name_tag";
   }));
   private static final float z = 0.3F;
   private static final float w = 0.55F;
   private static final float Z = 0.12F;
   private static final float l = 4.0F;
   private static final float F = 0.4F;
   private static final float b = 0.62F;
   private static final float P = 0.05F;
   private static final int C = 48;
   private static final double k = 64.0;
   private static final double e = 0.9925;
   private static final int E = 256;
   private static final Matrix4f A = new Matrix4f();
   private static final Vector4f y = new Vector4f();
   private static float D = 1.428148F;
   private static double U;
   private static double V;
   private static double G;
   private static float o;
   private static float x;
   private static float m = -1.0F;
   private static boolean p;
   private static final List<NameTagsCapture.NameTagData> H = new ArrayList<>();
   private static final Int2ObjectOpenHashMap<NameTagsCapture.NameTagCodec> q = new Int2ObjectOpenHashMap();
   private static final List<NameTagsCapture.NameTagGroup> u = new ArrayList<>();
   private static final List<NameTagsCapture.NameTagRender> R = new ArrayList<>();
   private static final List<NameTagsCapture.NameTagBatch> f = new ArrayList<>();
   private static final List<NameTagsCapture.NameTagBatch> M = new ArrayList<>();
   private static class_638 i;
   private static long n = 0L;
   private static float W;
   private static int J = 0;
   private static boolean K;
   private static double Y;
   private static double a;
   private static double t;
   private static double X;
   private static int r = 0;
   private static int h = 0;
   private static final Set<class_10017> B = Collections.newSetFromMap(new WeakHashMap<>());
   static NameTagsModule c;
   private static ModuleContext v;
   private static int Q = 0;
   private static int g = 0;
   private static final String[] j = new String[24];
   private static final long[] s = new long[]{
      3478877635315444397L,
      -8955952975983060925L,
      -4407539242607474706L,
      -3861237376974650130L,
      4079787140700826510L,
      -6834117144198898036L,
      6064109553998445465L,
      6237827089079680580L,
      -1381361263163417751L,
      -3628941003543098744L,
      -3406120280265255967L
   };

   private NameTagsCapture() {
   }

   private static NameTagsModule w() {
      return c;
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2) {
      H.clear();
      p = w().enabled();
      if (p) {
         A.set(var2).mul(var1);
         D = Math.abs(var2.m11());
         class_243 var3 = var0.method_71156();
         U = var3.field_1352;
         V = var3.field_1351;
         G = var3.field_1350;
         Vector3fc var4 = var0.method_19335();
         o = var4.x();
         x = var4.y();
         m = var4.z();
      }
   }

   public static void extract(class_1297 var0, class_10017 var1) {
      long var2 = 47149178079174L;
      long var4 = var2 ^ 378347056569L;
      int var6 = (int)((var2 ^ 378347056569L) >>> 48);
      int var7 = (int)((var2 ^ 378347056569L) << 16 >>> 48);
      int var8 = (int)(var4 << 32 >>> 32);
      boolean var9 = p && var1.field_53337 != null && U(var0, (short)var6, (char)var7, var8);
      if (var9 || ((FabricRenderState)var1).getData(I) != null) {
         B.add(var1);
         ((FabricRenderState)var1).setData(I, var9 ? var0 : null);
      }
   }

   private static boolean U(class_1297 var0, short var1, char var2, int var3) {
      TagPainter.ensureSourceClassInitialized();
      NameTagsModule var4 = w();
      boolean var5 = var0 instanceof class_11890;
      if (var5) {
         return true;
      } else {
         var5 = var0 instanceof class_1542;
         return var5 ? !var4.r.is("Off") : var4.n.enabled() && var0 instanceof class_1309 && !(var0 instanceof class_1531);
      }
   }

   public static boolean capture(class_10017 var0, class_4587 var1) {
      class_1297 var2 = (class_1297)((FabricRenderState)var0).getData(I);
      if (var2 == null || !p) {
         return false;
      } else if (var2 instanceof class_1542) {
         return true;
      } else {
         class_243 var3 = var0.field_53338;
         class_2561 var4 = var0.field_53337;
         if (var3 != null && var4 != null) {
            class_10055 var5 = var0 instanceof class_10055 var6 ? var6 : null;
            float var8 = var5 != null && var5.field_62758 ? 0.25F : 0.0F;
            Vector3f var7 = var1.method_23760()
               .method_23761()
               .transformPosition((float)var3.field_1352, (float)var3.field_1351 + var8, (float)var3.field_1350, new Vector3f());
            H.add(
               new NameTagsCapture.NameTagData(
                  var2,
                  U + var7.x,
                  V + var7.y,
                  G + var7.z,
                  var4,
                  var5 != null ? var5.field_53525 : null,
                  !var0.field_53334,
                  var5 != null ? var5.field_53520.comp_1626().comp_3627() : null
               )
            );
            return true;
         } else {
            return true;
         }
      }
   }

   public static boolean showOwn(class_1309 var0) {
      NameTagsModule var1 = w();
      if (var1.enabled() && var1.V.enabled()) {
         class_310 var2 = class_310.method_1551();
         return var0 == var2.field_1724
            && var0 == var2.method_1560()
            && !var2.field_1690.method_31044().method_31034()
            && class_310.method_1498()
            && !var0.method_5767()
            && !ModuleWidget.claimed("preview-hud");
      } else {
         return false;
      }
   }

   public static boolean bounds(class_1297 var0, float[] var1) {
      NameTagsCapture.NameTagCodec var2 = (NameTagsCapture.NameTagCodec)q.get(var0.method_5628());
      if (var2 != null && var2.d == var0 && RenderPipelines.get().frame() - var2.W <= 1L) {
         var1[0] = var2.f;
         var1[1] = var2.R;
         var1[2] = var2.T;
         var1[3] = var2.N;
         return true;
      } else {
         return false;
      }
   }

   public static void clear() {
      q.clear();
      u.clear();
      H.clear();
      R.clear();
   }

   public static void render() {
      long var0 = 122738940906443L;
      long var2 = var0 ^ 15766512258501L;
      long var4 = var0 ^ 123944882241067L;
      long var6 = var0 ^ 65974924911046L;
      boolean var8 = p;
      p = false;
      class_310 var9 = class_310.method_1551();
      class_638 var10 = var9.field_1687;
      if (var8 && var10 != null) {
         if (var10 != i) {
            clear();
            i = var10;
         }

         long var11 = System.nanoTime();
         float var13 = n == 0L ? 0.0F : Math.min(0.1F, (float)(var11 - n) / 1.0E9F);
         n = var11;
         W += var13;
         J = 48;
         NameTagsModule var14 = w();
         float var15 = var9.method_61966().method_60637(true);
         Canvas var16 = RenderPipelines.get().worldCanvas();
         J(var10, var14, var13, var15, var4);
         A(var10, var14, var2, var13, var15);
         H.clear();
         long var17 = System.nanoTime();
         if (!var9.field_1690.field_1842) {
            RenderPipelines var19 = RenderPipelines.get();
            var19.beginWorld();
            float var20 = var16.width();
            float var21 = var16.height();
            R.clear();
            ObjectIterator var22 = q.values().iterator();

            while (var22.hasNext()) {
               NameTagsCapture.NameTagCodec var23 = (NameTagsCapture.NameTagCodec)var22.next();
               var23.place(var20, var21, var14);
            }

            for (NameTagsCapture.NameTagGroup var29 : u) {
               var29.place(var20, var21, var14);
            }

            if (!R.isEmpty()) {
               R.sort(Comparator.<NameTagsCapture.NameTagRender>comparingDouble(var0x -> var0x.p).reversed());
               TagPainter.Look var27 = var14.look();
               float var30 = var14.C.floatValue();

               for (NameTagsCapture.NameTagRender var25 : R) {
                  var25.draw(var16, var27, var30);
               }
            }

            long var28 = System.nanoTime();
            var19.flushWorld();
            if (!R.isEmpty()) {
               metric("drawn");
            }

            if (K) {
               q(var11, var17, var28, var6);
            }
         }
      } else if (!q.isEmpty() || !u.isEmpty()) {
         clear();
      }
   }

   private static void J(class_638 var0, NameTagsModule var1, float var2, float var3, long var4) {
      long var6 = 120803684679201L;
      long var8 = 81223423894673L;
      TagPainter.ensureSourceClassInitialized();

      for (NameTagsCapture.NameTagData var11 : H) {
         boolean var12 = U(var6, var11.j, var1);
         long var13 = 34202038072799L;
         int var22 = var13 < 0L ? -1 : 1;
         if (!var12) {
            long var21 = 34202038072799L;
            int var171 = var21 < 0L ? -1 : 1;
         } else {
            NameTagsCapture.NameTagCodec var16 = (NameTagsCapture.NameTagCodec)q.get(var11.j.method_5628());
            long var17 = 34202038072799L;
            int var18 = var17 < 0L ? -1 : 1;
            if (var16 == null || var16.d != var11.j) {
               var16 = new NameTagsCapture.NameTagCodec(var11.j);
               q.put(var11.j.method_5628(), var16);
            }

            var16.submit(var11, var1, var3);
         }
      }

      long var20 = 34202038072800L;
      int var20x = var20 < 0L ? -1 : 1;
      O(var0, var8, var1, var3);
      ObjectIterator var19 = q.int2ObjectEntrySet().fastIterator();

      while (var19.hasNext()) {
         NameTagsCapture.NameTagCodec var14 = (NameTagsCapture.NameTagCodec)((Entry)var19.next()).getValue();
         boolean var15 = var14.update(var0, var1, var2, var3);
         long var22 = 34202038072799L;
         int var26 = var22 < 0L ? -1 : 1;
         if (!var15) {
            var19.remove();
         }
      }
   }

   private static void O(class_638 var0, long var1, NameTagsModule var3, float var4) {
      long var5 = 46977567894143L;
      byte var7 = 0;
      short var8 = 10937;
      int var9 = (int)(var5 << 32 >>> 32);
      TagPainter.ensureSourceClassInitialized();
      class_310 var10 = class_310.method_1551();
      class_1297 var11 = var10.method_1560();
      class_898 var12 = var10.method_1561();
      double var13 = (Double)var3.I.get();
      double var15 = var13 * var13;

      for (class_1297 var18 : var0.method_18112()) {
         long var19 = 81223423894672L;
         int var22 = var19 < 0L ? -1 : 1;
         if (var18 != var11) {
            long var22x = 81223423894673L;
            int var24 = var22x < 0L ? -1 : 1;
            var24 = var18 instanceof class_1542 ? 1 : 0;
            long var25 = 81223423894673L;
            int var28 = var25 < 0L ? -1 : 1;
            if (var24 == 0) {
               long var28x = 81223423894673L;
               boolean var54 = U(var18, var7, (char)var8, var9);
               long var30x = 81223423894672L;
               if (!var54) {
                  long var57 = 81223423894673L;
                  int var60 = var57 < 0L ? -1 : 1;
               } else {
                  double var33x = var18.method_5649(U, V, G);
                  NameTagsCapture.NameTagCodec var35 = (NameTagsCapture.NameTagCodec)q.get(var18.method_5628());
                  boolean var36 = var35 != null && var35.d == var18;
                  double var37;
                  int var54x = (var37 = var33x - var15) == 0.0 ? 0 : (var37 < 0.0 ? -1 : 1);
                  long var39 = 81223423894672L;
                  int var41 = var39 < 0L ? -1 : 1;
                  if (var54x <= 0) {
                     if (var36 && var35.O) {
                        long var58 = 81223423894672L;
                        int var63 = var58 < 0L ? -1 : 1;
                     } else {
                        NameTagNameInvoker var41x = (NameTagNameInvoker)var12.method_3953(var18);
                        boolean var42 = var41x.purxiy$shouldShowName(var18, var33x);
                        long var43 = 81223423894673L;
                        int var42x = var43 < 0L ? -1 : 1;
                        if (!var42) {
                           long var59 = 81223423894672L;
                           int var67 = var59 < 0L ? -1 : 1;
                        } else {
                           class_2561 var46x = var41x.purxiy$nameTag(var18);
                           class_243 var47 = var18.method_56072().method_55675(class_9064.field_47745, 0, var18.method_61415(var4));
                           if (var46x != null) {
                              if (var47 == null) {
                                 long var60 = 81223423894672L;
                                 int var69 = var60 < 0L ? -1 : 1;
                              } else {
                                 class_11901 var48 = var18 instanceof class_11901 var49 ? var49 : null;
                                 class_2561 var61 = var48 != null && var33x < 100.0 ? var48.method_74193() : null;
                                 float var50 = var48 != null && var48.method_74194() ? 0.25F : 0.0F;
                                 long var51 = 81223423894673L;
                                 int var49x = var51 < 0L ? -1 : 1;
                                 if (!var36) {
                                    var35 = new NameTagsCapture.NameTagCodec(var18);
                                    q.put(var18.method_5628(), var35);
                                 }

                                 var35.submit(
                                    new NameTagsCapture.NameTagData(
                                       var18,
                                       class_3532.method_16436(var4, var18.field_6038, var18.method_23317()) + var47.field_1352,
                                       class_3532.method_16436(var4, var18.field_5971, var18.method_23318()) + var47.field_1351 + var50,
                                       class_3532.method_16436(var4, var18.field_5989, var18.method_23321()) + var47.field_1350,
                                       NameDecorationProvider.name(var18, var46x),
                                       var61,
                                       !var18.method_21751(),
                                       var48 != null ? var48.method_52814().comp_1626().comp_3627() : null
                                    ),
                                    var3,
                                    var4
                                 );
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      long var54 = 81223423894673L;
      int var511 = var54 < 0L ? -1 : 1;
   }

   private static boolean U(long var0, class_1297 var2, NameTagsModule var3) {
      TagPainter.ensureSourceClassInitialized();
      double var4 = (Double)var3.I.get();
      return var2.method_5649(U, V, G) <= var4 * var4;
   }

   private static int U(long var0, NameTagsModule var2) {
      TagPainter.ensureSourceClassInitialized();
      int var3 = TabListProvider.generation() * 31;
      long var4 = 12864369103432L;
      int var6 = var4 < 0L ? -1 : 1;
      var3 *= 31;
      var6 = (var2.Q.enabled() ? 1 : 0) * 31;
      long var7 = 12864369103431L;
      int var9 = var7 < 0L ? -1 : 1;
      return var3 + var6 + (var2.f.is("Minecraft") ? 1 : 0);
   }

   private static float W(NameTagsModule var0, long var1) {
      TagPainter.ensureSourceClassInitialized();
      String var3 = (String)var0.F.get();
      byte var4 = -1;
      switch (var3.hashCode()) {
         case 2249058:
            if (var3.equals("Hide")) {
               long var5 = 82506661720329L;
               int var7 = var5 < 0L ? -1 : 1;
               var4 = 0;
            }
            break;
         case 2576157:
            if (var3.equals("Show")) {
               var4 = 1;
            }
      }
      return switch (var4) {
         case 0 -> 0.0F;
         case 1 -> 1.0F;
         default -> 0.4F;
      };
   }

   private static void A(class_638 var0, NameTagsModule var1, long var2, float var4, float var5) {
      long var6 = 21954477962272L;
      short var8 = 5111;
      int var9 = 11328564;
      int var10 = (int)(var6 << 56 >>> 56);
      TagPainter.ensureSourceClassInitialized();

      for (NameTagsCapture.NameTagGroup var12 : u) {
         var12.Y = false;
      }

      long var21 = 107715894822414L;
      int var20 = var21 < 0L ? -1 : 1;
      if (!var1.r.is("Off")) {
         n(var0, var1, var5, var8, var9, (byte)var10);
      }

      Iterator var20x = u.iterator();

      while (var20x.hasNext()) {
         NameTagsCapture.NameTagGroup var15 = (NameTagsCapture.NameTagGroup)var20x.next();
         boolean var16 = var15.update(var0, var1, var4);
         long var17 = 107715894822413L;
         int var18 = var17 < 0L ? -1 : 1;
         if (!var16) {
            var20x.remove();
         }
      }
   }

   private static void n(class_638 var0, NameTagsModule var1, float var2, int var3, int var4, byte var5) {
      long var6 = 21954477962272L;
      long var8 = var6 ^ 18658136957960L;
      int var10 = (int)((var6 ^ 87139320238029L) >>> 56);
      long var11 = (var6 ^ 87139320238029L) << 8 >>> 8;
      double var13 = (Double)var1.o.get();
      TagPainter.ensureSourceClassInitialized();
      double var15 = var13 * var13;
      M.addAll(f);
      f.clear();

      for (class_1297 var18 : var0.method_18112()) {
         boolean var19 = var18 instanceof class_1542;
         if (var19) {
            class_1542 var20 = (class_1542)var18;
            var19 = var20.method_31481();
            int var21 = 32;
            if (!var19) {
               var19 = var20.method_6983().method_7960();
               var21 = 11328564;
               if (var19) {
                  var21 = 11328564;
               } else {
                  double var22 = class_3532.method_16436(var2, var20.field_6014, var20.method_23317());
                  double var24 = class_3532.method_16436(var2, var20.field_6036, var20.method_23318());
                  double var26 = class_3532.method_16436(var2, var20.field_5969, var20.method_23321());
                  double var28 = var22 - U;
                  double var30 = var24 - V;
                  double var32 = var26 - G;
                  double var34 = var28 * var28 + var30 * var30 + var32 * var32;
                  if (var34 > var15) {
                     var21 = 11328564;
                  } else {
                     NameTagsCapture.NameTagBatch var36 = M.isEmpty() ? new NameTagsCapture.NameTagBatch() : M.removeLast();
                     var36.W = var20.method_6983();
                     var36.p = var22;
                     var36.x = var24;
                     var36.H = var26;
                     var36.y = var34;
                     f.add(var36);
                     int var37 = 5111;
                     if (f.size() == 256) {
                        var37 = 11328564;
                        break;
                     }
                  }
               }
            }
         }
      }

      if (!f.isEmpty()) {
         f.sort(Comparator.comparingDouble(var0x -> var0x.y));
         int var38 = f.size();
         double[] var39 = new double[var38];
         double[] var42 = new double[var38];
         double[] var43 = new double[var38];
         int[] var47 = new int[var38];

         for (int var48 = 0; var48 < var38; var48++) {
            NameTagsCapture.NameTagBatch var23 = f.get(var48);
            var39[var48] = var23.p;
            var42[var48] = var23.x;
            var43[var48] = var23.H;
         }

         int var49 = 32;
         int var53;
         if (var1.O.enabled()) {
            var49 = NameTagsHealth.cluster(var39, var42, var43, var38, 1.25, var47);
            var53 = var49;
         } else {
            int var54 = 0;

            while (var54 < var38) {
               var47[var54] = var54++;
            }

            var53 = var38;
         }

         int var55 = -1;
         if (var1.r.is("Looking at")) {
            var55 = C(var53, var47, var8, var38);
            if (var55 < 0) {
               return;
            }
         }

         for (int var25 = 0; var25 < var53; var25++) {
            if (var55 < 0 || var25 == var55) {
               double var56 = 0.0;
               double var58 = 0.0;
               double var60 = -Double.MAX_VALUE;
               int var61 = 0;

               for (int var33 = 0; var33 < var38; var33++) {
                  var49 = var47[var33];
                  if (var49 == var25) {
                     var56 += var39[var33];
                     var58 += var43[var33];
                     var60 = Math.max(var60, var42[var33]);
                     var61++;
                  }
               }

               var56 /= var61;
               var49 = 11328564;
               var58 /= var61;
               NameTagsCapture.NameTagGroup var62 = N(var56, (byte)var10, var60, var58, var11);
               var62.c = var56;
               var62.X = var60 + 0.55F;
               var62.T = var58;
               var62.k = var60;
               var62.Y = true;
               var62.fill(var25, var47, var38, var1);
            }
         }
      }
   }

   private static int C(int var0, int[] var1, long var2, int var4) {
      TagPainter.ensureSourceClassInitialized();
      int var5 = -1;
      double var6 = 0.9925;

      for (int var8 = 0; var8 < var0; var8++) {
         for (int var9 = 0; var9 < var4; var9++) {
            long var10 = 3365165338664L;
            int var13 = var10 < 0L ? -1 : 1;
            var13 = var1[var9];
            long var14 = 3365165338663L;
            int var16 = var14 < 0L ? -1 : 1;
            if (var13 == var8) {
               NameTagsCapture.NameTagBatch var16x = f.get(var9);
               long var17 = 3365165338663L;
               int var20 = var17 < 0L ? -1 : 1;
               double var20x = var16x.p - U;
               double var22 = var16x.x + 0.2 - V;
               double var24 = var16x.H - G;
               double var26 = Math.sqrt(var20x * var20x + var22 * var22 + var24 * var24);
               double var28 = (var20x * o + var22 * x + var24 * m) / Math.max(1.0E-6, var26);
               long var30 = 3365165338663L;
               int var30x = var30 < 0L ? -1 : 1;
               if (var28 > var6) {
                  var6 = var28;
                  var5 = var8;
               }
            }
         }

         long var33 = 3365165338663L;
         int var11 = var33 < 0L ? -1 : 1;
      }

      long var32 = 3365165338664L;
      int var331 = var32 < 0L ? -1 : 1;
      return var5;
   }

   private static NameTagsCapture.NameTagGroup N(double var0, byte var2, double var3, double var5, long var7) {
      TagPainter.ensureSourceClassInitialized();
      NameTagsCapture.NameTagGroup var9 = null;
      double var10 = 2.25;

      for (NameTagsCapture.NameTagGroup var13 : u) {
         boolean var15 = false;
         if (var13.Y) {
            long var16 = 101941466545132L;
            int var18 = var16 < 0L ? -1 : 1;
         } else {
            double var32 = var13.c - var0;
            double var18 = var13.k - var3;
            double var20 = var13.T - var5;
            double var22 = var32 * var32 + var18 * var18 + var20 * var20;
            long var24 = 101941466545132L;
            int var26 = var24 < 0L ? -1 : 1;
            double var26x;
            int var28 = (var26x = var22 - var10) == 0.0 ? 0 : (var26x < 0.0 ? -1 : 1);
            long var29 = 101941466545132L;
            int var30 = var29 < 0L ? -1 : 1;
            if (var28 < 0) {
               var10 = var22;
               var9 = var13;
            }
         }
      }

      long var31 = 101941466545132L;
      int var321 = var31 < 0L ? -1 : 1;
      if (var9 == null) {
         var9 = new NameTagsCapture.NameTagGroup();
         u.add(var9);
      }

      return var9;
   }

   static String icon(class_1297 var0) {
      class_1299 var1 = var0.method_5864();
      if (var1 == class_1299.field_6055) {
         return "dog";
      } else if (var1 == class_1299.field_16281 || var1 == class_1299.field_6081) {
         return "cat";
      } else if (var1 == class_1299.field_6104 || var1 == class_1299.field_6132 || var1 == class_1299.field_6108) {
         return "bird";
      } else if (var1 == class_1299.field_6140) {
         return "rabbit";
      } else if (var1 == class_1299.field_6113) {
         return "turtle";
      } else if (var1 == class_1299.field_20346
         || var1 == class_1299.field_6079
         || var1 == class_1299.field_6084
         || var1 == class_1299.field_6125
         || var1 == class_1299.field_6128) {
         return "bug";
      } else if (var0 instanceof class_1569) {
         return "skull";
      } else if (var0 instanceof class_3988) {
         return "user-round";
      } else {
         class_1311 var2 = var1.method_5891();
         return var2 != class_1311.field_6300 && var2 != class_1311.field_24460 && var2 != class_1311.field_30092 && var2 != class_1311.field_34447
            ? "paw-print"
            : "fish";
      }
   }

   static int rarity(class_1799 var0) {
      return switch (NameTagsCapture.NameTagFrame.R[var0.method_7932().ordinal()]) {
         case 1 -> -8054;
         case 2 -> -8394766;
         case 3 -> -1595649;
         default -> Theme.tint(-4607274);
      };
   }

   private static boolean y(double var0, double var2, long var4, double var6) {
      TagPainter.ensureSourceClassInitialized();
      y.set((float)(var0 - U), (float)(var2 - V), (float)(var6 - G), 1.0F);
      A.transform(y);
      return y.w > 0.05F;
   }

   private static float h(float var0) {
      return (y.x / y.w * 0.5F + 0.5F) * var0;
   }

   private static float W(float var0) {
      return (0.5F - y.y / y.w * 0.5F) * var0;
   }

   private static float D(double var0, double var2, double var4) {
      double var6 = var0 - U;
      double var8 = var2 - V;
      double var10 = var4 - G;
      return (float)Math.sqrt(var6 * var6 + var8 * var8 + var10 * var10);
   }

   private static boolean E(int var0, double var1, double var3, short var5, int var6, double var7) {
      TagPainter.ensureSourceClassInitialized();
      int var9 = 2 * Math.max(1, (int)Math.ceil(D(var1, var3, var7) / 64.0));
      if (J < var9) {
         if (J < 48) {
            return false;
         }

         var9 = J;
      }

      J -= var9;
      return true;
   }

   private static boolean C(class_638 var0, double var1, int var3, double var4, int var6, double var7, int var9) {
      class_243 var10 = new class_243(U, V, G);
      TagPainter.ensureSourceClassInitialized();
      class_243 var11 = new class_243(var1, var4, var7);
      return var10.method_1025(var11) < 1.0E-4
         ? true
         : var0.method_17742(new class_3959(var10, var11, class_3960.field_23142, class_242.field_1348, class_3726.method_16194())).method_17783()
            == class_240.field_1333;
   }

   public static void setTiming(boolean var0) {
      K = var0;
      Y = 0.0;
      a = 0.0;
      t = 0.0;
      X = 0.0;
      r = 0;
      h = 0;
   }

   private static void q(long var0, long var2, long var4, long var6) {
      long var8 = System.nanoTime();
      Y += (var8 - var0) / 1000000.0;
      a += (var2 - var0) / 1000000.0;
      t += (var4 - var2) / 1000000.0;
      X = Math.max(X, (var2 - var0) / 1000000.0);
      h = h + R.size();
      if (++r == 240) {
         FogRange var10 = RenderPipelines.get().worldStats();
         ClientServices.LOGGER
            .info(
               "Name tags: {} ms CPU per frame (update {}, slowest {}, draw {}, flush {}), {} tags, {} quads in {} passes, {} captures",
               new Object[]{
                  String.format(Locale.ROOT, "%.3f", Y / r),
                  String.format(Locale.ROOT, "%.3f", a / r),
                  String.format(Locale.ROOT, "%.3f", X),
                  String.format(Locale.ROOT, "%.3f", t / r),
                  String.format(Locale.ROOT, "%.3f", (Y - a - t) / r),
                  h / r,
                  var10.quads,
                  var10.passes,
                  var10.captures
               }
            );
         Y = 0.0;
         a = 0.0;
         t = 0.0;
         X = 0.0;
         r = 0;
         h = 0;
      }
   }

   private static void E(long var0) {
      TagPainter.ensureSourceClassInitialized();

      for (class_10017 var3 : B) {
         long var4 = 39537553717991L;
         int var6 = var4 < 0L ? -1 : 1;
         ((FabricRenderState)var3).setData(I, null);
      }

      long var7 = 39537553717991L;
      int var8 = var7 < 0L ? -1 : 1;
      B.clear();
   }

   public static void install(NameTagsModule var0, ModuleContext var1) {
      c = var0;
      v = var1;
   }

   public static void metric(String var0) {
      if (v != null) {
         v.metric(var0, var0.equals("drawn") ? ++Q : ++g);
      }
   }

   public static void uninstall() {
      long var0 = 75369837009762L;
      long var2 = var0 ^ 113773239378309L;
      clear();
      f.clear();
      M.clear();
      i = null;
      p = false;
      E(var2);
      c = null;
      v = null;
      n = 0L;
   }

   private static final class NameTagEntry {
      final class_1799 m;
      int R;

      NameTagEntry(class_1799 var1) {
         this.m = var1;
      }
   }

   private record NameTagRender(TagPainter.TagPlate Y, TagPainter.TagGroup H, float j, float S, float E, float y, float q, float p) {
      void draw(Canvas var1, TagPainter.Look var2, float var3) {
         float var4 = this.y;
         var1.push();
         var1.translate(this.j, this.S + (1.0F - var4) * 4.0F * this.E);
         var1.scale(this.E * (0.9F + 0.1F * var4));
         var1.pushAlpha(this.q);
         if (this.Y != null) {
            TagPainter.draw(var1, this.Y, var2, var3);
         } else if (this.H != null) {
            TagPainter.draw(var1, this.H, var2, var3, NameTagsCapture.W);
         }

         var1.popAlpha();
         var1.pop();
      }

      public TagPainter.TagPlate entity() {
         return this.Y;
      }

      public TagPainter.TagGroup item() {
         return this.H;
      }

      public float x() {
         return this.j;
      }

      public float y() {
         return this.S;
      }

      public float scale() {
         return this.E;
      }

      public float presence() {
         return this.y;
      }

      public float alpha() {
         return this.q;
      }

      public float distance() {
         return this.p;
      }

      public TagPainter.TagPlate tagPainter_EntityTagLayoutState2() {
         return this.Y;
      }

      public TagPainter.TagGroup tagPainter_ItemTagLayoutState2() {
         return this.H;
      }

      public float floatState38() {
         return this.j;
      }

      public float floatState14() {
         return this.S;
      }

      public float floatState44() {
         return this.E;
      }

      public float floatState48() {
         return this.q;
      }

      public float floatState35() {
         return this.p;
      }
   }

   static class NameTagFrame {
      static final int[] R = new int[class_1814.values().length];

      static {
         try {
            R[class_1814.field_8907.ordinal()] = 1;
         } catch (NoSuchFieldError var3) {
         }

         try {
            R[class_1814.field_8903.ordinal()] = 2;
         } catch (NoSuchFieldError var2) {
         }

         try {
            R[class_1814.field_8904.ordinal()] = 3;
         } catch (NoSuchFieldError var1) {
         }
      }
   }

   private static final class NameTagGroup {
      final TagPainter.TagGroup K = new TagPainter.TagGroup();
      final Animator G = new Animator(0.0F, 260.0F, 30.0F);
      final Animator J = Animator.smooth(1.0F);
      final List<NameTagsCapture.NameTagEntry> g = new ArrayList<>();
      double c;
      double X;
      double T;
      double k;
      boolean Y;
      float h;
      boolean P;
      boolean S;
      int Q;
      int H;
      private static final String a = "Minecraft";

      void fill(int var1, int[] var2, int var3, NameTagsModule var4) {
         long var5 = 107573803189823L;
         long var7 = var5 ^ 116983139210359L;
         this.g.clear();

         for (int var9 = 0; var9 < var3; var9++) {
            if (var2[var9] == var1) {
               class_1799 var10 = NameTagsCapture.f.get(var9).W;
               NameTagsCapture.NameTagEntry var11 = null;

               for (NameTagsCapture.NameTagEntry var13 : this.g) {
                  if (class_1799.method_31577(var13.m, var10)) {
                     var11 = var13;
                     break;
                  }
               }

               if (var11 == null) {
                  var11 = new NameTagsCapture.NameTagEntry(var10);
                  this.g.add(var11);
               }

               var11.R = var11.R + var10.method_7947();
            }
         }

         this.g
            .sort(
               Comparator.<NameTagsCapture.NameTagEntry>comparingInt(var0 -> var0.m.method_7932().ordinal())
                  .reversed()
                  .thenComparing(Comparator.<NameTagsCapture.NameTagEntry>comparingInt(var0 -> var0.R).reversed())
            );
         int var18 = 1;

         for (NameTagsCapture.NameTagEntry var22 : this.g) {
            var18 = var18 * 31 + class_1799.method_57355(var22.m);
            var18 = var18 * 31 + var22.R;
         }

         int var21 = NameTagsCapture.U(var7, var4);
         boolean var23 = false;

         for (TagPainter.TagGroup.TagEntry var26 : this.K.O) {
            var23 |= var26.r.B || var26.r.stale();
         }

         if (var18 != this.Q || var21 != this.H || this.K.O.isEmpty() || var23) {
            this.Q = var18;
            this.H = var21;
            this.K.O.clear();
            int var25 = this.g.size();
            int var27 = var25 <= 4 ? var25 : 3;
            boolean var14 = var4.f.is("Minecraft");

            for (int var15 = 0; var15 < var27; var15++) {
               NameTagsCapture.NameTagEntry var16 = this.g.get(var15);
               TagPainter.TagGroup.TagEntry var17 = new TagPainter.TagGroup.TagEntry();
               var17.r = NameTagsPlate.of(var16.m.method_7964(), 8.0F, Fonts.MEDIUM, Fonts.SEMIBOLD, Icons.TEXT, var4.Q.enabled(), var14)
                  .truncated(118.0F, Fonts.MEDIUM);
               var17.M = var16.R > 1 ? "×" + NameTagsHealth.count(var16.R) : "";
               var17.X = NameTagsCapture.rarity(var16.m);
               var17.t = var16.m.method_7958();
               this.K.O.add(var17);
            }

            this.K.B = var25 - var27;
         }
      }

      boolean update(class_638 var1, NameTagsModule var2, float var3) {
         long var4 = 110802895672413L;
         long var6 = var4 ^ 1834947479313L;
         int var8 = (int)((var4 ^ 1834947479313L) >>> 32);
         int var9 = (int)((var4 ^ 1834947479313L) << 32 >>> 48);
         int var10 = (int)(var6 << 48 >>> 48);
         long var11 = var4 ^ 52554754942295L;
         var6 = var4 ^ 83723085369801L;
         int var13 = (int)((var4 ^ 83723085369801L) >>> 32);
         int var14 = (int)((var4 ^ 83723085369801L) << 32 >>> 48);
         int var15 = (int)(var6 << 48 >>> 48);
         float var16 = this.G.target(this.Y ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var3));
         if (!this.Y && var16 <= 0.002F) {
            return false;
         } else {
            float var17 = NameTagsCapture.W(var2, var11);
            boolean var18 = false;
            if (var17 >= 1.0F) {
               this.P = false;
            } else if (this.Y
               && (this.S || this.h == 0.0F)
               && NameTagsCapture.W >= this.h
               && NameTagsCapture.E(var13, this.c, this.X, (short)var14, var15, this.T)) {
               var18 = this.h == 0.0F;
               this.h = NameTagsCapture.W + 0.05F;
               this.P = !NameTagsCapture.C(var1, this.c, var8, this.k + 0.2, var9, this.T, var10)
                  && !NameTagsCapture.C(var1, this.c, var8, this.X, var9, this.T, var10);
            }

            float var19 = this.P ? var17 : 1.0F;
            if (var18) {
               this.J.snap(var19);
            }

            this.J.target(var19).update(var3);
            return true;
         }
      }

      void place(float var1, float var2, NameTagsModule var3) {
         long var4 = 92919367038375L;
         long var6 = var4 ^ 109504276849758L;
         this.S = false;
         if (!this.K.O.isEmpty() && NameTagsCapture.y(this.c, this.X, var6, this.T)) {
            float var8 = NameTagsCapture.h(var1);
            float var9 = NameTagsCapture.W(var2);
            this.S = var8 >= -80.0F && var8 <= var1 + 80.0F && var9 >= -60.0F && var9 <= var2 + 120.0F;
            float var10 = this.G.value();
            float var11 = var10 * this.J.value();
            if (this.S && !(var11 <= 0.004F)) {
               TagPainter.measure(this.K);
               float var12 = NameTagsCapture.D(this.c, this.X, this.T);
               float var13 = var3.N.floatValue() * NameTagsHealth.scale(var12, NameTagsCapture.D, var3.t.floatValue());
               NameTagsCapture.R.add(new NameTagsCapture.NameTagRender(null, this.K, var8, var9, var13, var10, var11, var12));
            }
         }
      }
   }

   private static final class NameTagBatch {
      class_1799 W = class_1799.field_8037;
      double p;
      double x;
      double H;
      double y;
   }

   private record NameTagData(class_1297 j, double o, double R, double e, class_2561 x, class_2561 K, boolean w, class_2960 i) {
      public class_1297 entity() {
         return this.j;
      }

      public double xx() {
         return this.o;
      }

      public double y() {
         return this.R;
      }

      public double z() {
         return this.e;
      }

      public class_2561 name() {
         return this.x;
      }

      public class_2561 score() {
         return this.K;
      }

      public boolean seeThrough() {
         return this.w;
      }

      public class_2960 skin() {
         return this.i;
      }

      public class_1297 minecraftObjectState6() {
         return this.j;
      }

      public double doubleState51() {
         return this.o;
      }

      public double doubleState54() {
         return this.R;
      }

      public double doubleState42() {
         return this.e;
      }

      public class_2561 minecraftObjectState2() {
         return this.x;
      }

      public class_2561 minecraftObjectState3() {
         return this.K;
      }

      public boolean booleanState8() {
         return this.w;
      }

      public class_2960 minecraftObjectState11() {
         return this.i;
      }
   }

   private static final class NameTagCodec {
      final class_1297 d;
      final TagPainter.TagPlate G = new TagPainter.TagPlate();
      final Animator b = new Animator(0.0F, 240.0F, 29.0F);
      final Animator H = Animator.smooth(1.0F);
      final Animator z = new Animator(1.0F, 200.0F, 28.0F);
      class_2561 p;
      class_2561 F;
      class_2960 i;
      int o;
      boolean w = true;
      boolean O;
      float B;
      double Y;
      double u;
      double a;
      double h;
      double I;
      double A;
      float V;
      boolean x;
      float K = -1.0F;
      float U;
      float k;
      boolean y;
      float f;
      float R;
      float T;
      float N;
      long W = -4611686018427387904L;
      private static final String[] e = new String[3];

      NameTagCodec(class_1297 var1) {
         this.d = var1;
      }

      void submit(NameTagsCapture.NameTagData var1, NameTagsModule var2, float var3) {
         long var4 = 43544920568759L;
         long var6 = var4 ^ 48557551985151L;
         this.O = true;
         this.B = NameTagsCapture.W;
         this.Y = var1.o;
         this.u = var1.R;
         this.a = var1.e;
         this.h = var1.o - class_3532.method_16436(var3, this.d.field_6014, this.d.method_23317());
         this.I = var1.R - class_3532.method_16436(var3, this.d.field_6036, this.d.method_23318());
         this.A = var1.e - class_3532.method_16436(var3, this.d.field_5969, this.d.method_23321());
         this.w = var1.w;
         class_2561 var8 = var2.s.enabled() ? var1.K : null;
         class_2960 var9 = var2.d.enabled() ? var1.i : null;
         int var10 = NameTagsCapture.U(var6, var2);
         NameTagsPlate var11 = this.G.g;
         if (var11 == null || var11.B || var10 != this.o || !Objects.equals(var1.x, this.p) || !Objects.equals(var8, this.F) || var11.stale()) {
            boolean var12 = var2.f.is("Minecraft");
            this.G.g = NameTagsPlate.of(var1.x, 9.0F, Fonts.SEMIBOLD, Fonts.BOLD, Icons.TEXT, var2.Q.enabled(), var12);
            this.G.m = var8 == null ? null : NameTagsPlate.of(var8, 7.0F, Fonts.MEDIUM, Fonts.SEMIBOLD, Icons.TEXT_2, var2.Q.enabled(), var12);
            this.p = var1.x;
            this.F = var8;
            this.o = var10;
         }

         this.i = var9;
         this.G.r = var9;
         this.G.K = var9 == null && var2.d.enabled() && !(this.d instanceof class_11890) ? NameTagsCapture.icon(this.d) : null;
      }

      boolean update(class_638 var1, NameTagsModule var2, float var3, float var4) {
         long var5 = 97908329221893L;
         long var7 = var5 ^ 32617313814989L;
         long var9 = var5 ^ 58079829389604L;
         long var11 = var5 ^ 66389380897865L;
         int var13 = (int)((var5 ^ 66389380897865L) >>> 32);
         int var14 = (int)((var5 ^ 66389380897865L) << 32 >>> 48);
         int var15 = (int)(var11 << 48 >>> 48);
         long var16 = var5 ^ 89222653064474L;
         long var18 = var5 ^ 19817439198735L;
         var11 = var5 ^ 125271526132369L;
         int var20 = (int)((var5 ^ 125271526132369L) >>> 32);
         int var21 = (int)((var5 ^ 125271526132369L) << 32 >>> 48);
         int var22 = (int)(var11 << 48 >>> 48);
         boolean var23 = !this.d.method_31481() && this.d.method_73183() == var1;
         boolean var24 = this.d instanceof class_1309 var25 && var25.method_29504();
         if (!this.O && var23) {
            this.Y = class_3532.method_16436(var4, this.d.field_6014, this.d.method_23317()) + this.h;
            this.u = class_3532.method_16436(var4, this.d.field_6036, this.d.method_23318()) + this.I;
            this.a = class_3532.method_16436(var4, this.d.field_5969, this.d.method_23321()) + this.A;
         }

         float var31;
         if (!var24 && !this.b(var7) && NameTagsCapture.U(var9, this.d, var2)) {
            if (this.O || NameTagsCapture.W - this.B < 0.12F) {
               var31 = 1.0F;
            } else if (var23 && !this.y && NameTagsCapture.W - this.B < 4.0F) {
               var31 = this.b.target();
            } else {
               var31 = 0.0F;
            }
         } else {
            var31 = 0.0F;
         }

         float var32 = this.b.target(var31).update(MotionStyle.HOVER.dt(var3));
         if (var32 <= 0.002F && var31 == 0.0F) {
            return false;
         } else {
            float var27 = NameTagsCapture.W(var2, var18);
            boolean var28 = false;
            if (this.w && var27 >= 1.0F) {
               this.x = false;
            } else if (var31 > 0.0F
               && (this.y || this.V == 0.0F)
               && NameTagsCapture.W >= this.V
               && NameTagsCapture.E(var20, this.Y, this.u, (short)var21, var22, this.a)) {
               var28 = this.V == 0.0F;
               this.V = NameTagsCapture.W + 0.05F;
               this.x = !NameTagsCapture.C(var1, this.Y, var13, this.u - 0.25, var14, this.a, var15)
                  && !NameTagsCapture.C(var1, this.Y, var13, this.u + 0.3F, var14, this.a, var15);
            }

            float var29 = !this.x ? 1.0F : (this.w ? var27 : 0.0F);
            if (var28) {
               this.H.snap(var29);
            }

            this.H.target(var29).update(var3);
            this.s(var2, var16, var3);
            this.O = false;
            return true;
         }
      }

      private void s(NameTagsModule var1, long var2, float var4) {
         TagPainter.ensureSourceClassInitialized();
         TagPainter.TagPlate var5 = this.G;
         long var6 = 8975603317278L;
         int var9 = var6 < 0L ? -1 : 1;
         if (!var1.w.is("Off")) {
            class_1297 var9x = this.d;
            long var10 = 8975603317278L;
            int var12 = var10 < 0L ? -1 : 1;
            long var12x = 8975603317279L;
            int var14 = var12x < 0L ? -1 : 1;
            if (var9x instanceof class_1309 var14x) {
               long var15 = 8975603317278L;
               int var18 = var15 < 0L ? -1 : 1;
               float var18x = Math.max(1.0F, var14x.method_6063());
               float var19 = var14x.method_6032();
               long var20 = 8975603317279L;
               int var20x = var20 < 0L ? -1 : 1;
               float var22x = class_3532.method_15363(Float.isNaN(var19) ? var18x : var14x.method_6032(), 0.0F, var18x);
               float var23 = Math.max(0.0F, var14x.method_6067());
               long var24 = 8975603317278L;
               int var24x = var24 < 0L ? -1 : 1;
               if (this.K >= 0.0F) {
                  long var27 = 8975603317279L;
                  int var26 = var27 < 0L ? -1 : 1;
                  if (var22x < this.K - 0.01F) {
                     this.U = Math.max(this.U, Math.max(this.z.value(), this.K / var18x));
                     this.k = NameTagsCapture.W + 0.4F;
                     var5.s = 1.0F;
                  }
               }

               if (this.K < 0.0F) {
                  this.z.snap(var22x / var18x);
               }

               this.K = var22x;
               float var30x = this.z.target(var22x / var18x).update(var4);
               long var28 = 8975603317278L;
               int var27 = var28 < 0L ? -1 : 1;
               if (NameTagsCapture.W > this.k) {
                  this.U = Math.max(var30x, this.U - var4 * 1.4F);
               }

               var5.s = Math.max(0.0F, var5.s - var4 / 0.5F);
               var5.R = var1.w.is("Bar") ? 1 : 2;
               var5.u = var30x;
               var5.P = this.U;
               var5.W = Math.min(1.0F, var23 / var18x);
               var5.z = var22x + var23;
               var5.U = var23 > 0.0F;
               var5.o = var22x / var18x <= 0.3F;
               return;
            }
         }

         var5.R = 0;
      }

      private boolean b(long var1) {
         TagPainter.ensureSourceClassInitialized();
         class_310 var3 = class_310.method_1551();
         return this.d == var3.method_1560() && var3.field_1690.method_31044().method_31034();
      }

      void place(float var1, float var2, NameTagsModule var3) {
         long var4 = 4817256549534L;
         long var6 = var4 ^ 71227109919318L;
         long var8 = var4 ^ 56573586382183L;
         this.y = false;
         if (this.b(var6)) {
            this.b.snap(0.0F);
         } else {
            float var10 = this.b.value();
            float var11 = var10 * this.H.value() * (this.w ? 1.0F : 0.62F);
            if (NameTagsCapture.y(this.Y, this.u + 0.3F, var8, this.a)) {
               float var12 = NameTagsCapture.h(var1);
               float var13 = NameTagsCapture.W(var2);
               this.y = var12 > -60.0F && var12 < var1 + 60.0F && var13 > -40.0F && var13 < var2 + 80.0F;
               if (this.y && !(var11 <= 0.004F) && this.G.g != null) {
                  TagPainter.measure(this.G);
                  float var14 = NameTagsCapture.D(this.Y, this.u, this.a);
                  float var15 = var3.N.floatValue() * NameTagsHealth.scale(var14, NameTagsCapture.D, var3.t.floatValue());
                  NameTagsCapture.R.add(new NameTagsCapture.NameTagRender(this.G, null, var12, var13, var15, var10, var11, var14));
                  float var16 = this.G.width() * var15 / 2.0F;
                  this.f = (var12 - var16) / var1;
                  this.T = (var12 + var16) / var1;
                  this.R = (var13 - TagPainter.height(this.G) * var15) / var2;
                  this.N = var13 / var2;
                  this.W = RenderPipelines.get().frame();
               }
            }
         }
      }
   }
}
