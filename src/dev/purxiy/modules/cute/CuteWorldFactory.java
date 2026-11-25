package dev.purxiy.modules.cute;

import dev.purxiy.loader.fabric.mixin.ModelPartAccessor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import net.minecraft.class_583;
import net.minecraft.class_630;
import net.minecraft.class_630.class_593;
import net.minecraft.class_630.class_618;
import net.minecraft.class_630.class_628;

public final class CuteWorldFactory {
   private static final Map<class_583<?>, List<CuteWorldTransform.TextureQuad>> p = new WeakHashMap<>();
   public static final List<CuteWorldTransform.TextureQuad> w = List.of(
      new CuteWorldTransform.TextureQuad(0.125, 0.125, 0.25, 0.25, CuteWorldTransform.CutePart.g),
      new CuteWorldTransform.TextureQuad(0.625, 0.125, 0.75, 0.25, CuteWorldTransform.CutePart.J)
   );

   private CuteWorldFactory() {
   }

   public static List<CuteWorldTransform.TextureQuad> of(class_583<?> var0) {
      return p.computeIfAbsent(var0, var0x -> g(50532400952376L, var0x));
   }

   private static List g(long var0, class_583 var2) {
      long var3 = 119199195433397L;
      long var5 = 21591072937099L;
      ArrayList var7 = new ArrayList();
      CuteWorldUpdater.ensureSourceClassInitialized();
      W(var2.method_63512(), var7, var3);
      String var8 = var2.getClass().getSimpleName();
      if (var8.equals("FrogModel")) {
         var7.replaceAll(
            var0x -> new CuteWorldTransform.TextureQuad(
               ((CuteWorldTransform.TextureQuad)var0x).u0(),
               ((CuteWorldTransform.TextureQuad)var0x).v0(),
               ((CuteWorldTransform.TextureQuad)var0x).u1(),
               ((CuteWorldTransform.TextureQuad)var0x).v1(),
               CuteWorldTransform.CutePart.I
            )
         );
         l(var2.method_63512(), "left_eye", CuteWorldTransform.CutePart.T, var7, var5);
         l(var2.method_63512(), "right_eye", CuteWorldTransform.CutePart.T, var7, var5);
      }

      label48: {
         if (var7.isEmpty()) {
            long var9 = 50532400952376L;
            int var12 = var9 < 0L ? -1 : 1;
            boolean var12x = var8.contains("Ghast");
            long var13 = 50532400952376L;
            int var16 = var13 < 0L ? -1 : 1;
            if (var12x) {
               break label48;
            }

            long var16x = 50532400952375L;
            var12x = var8.contains("Bee");
            long var18x = 50532400952375L;
            if (var12x) {
               break label48;
            }
         }

         if (var8.equals("SlimeModel")) {
            l(var2.method_63512(), "cube", CuteWorldTransform.CutePart.I, var7, var5);
            l(var2.method_63512(), "left_eye", CuteWorldTransform.CutePart.T, var7, var5);
            l(var2.method_63512(), "right_eye", CuteWorldTransform.CutePart.T, var7, var5);
         }

         return List.copyOf(var7);
      }

      l(var2.method_63512(), "body", CuteWorldTransform.CutePart.K, var7, var5);
      return List.copyOf(var7);
   }

   private static void W(class_630 var0, List var1, long var2) {
      long var4 = var2 ^ 0L;
      long var6 = var2 ^ 45639030189052L;
      CuteWorldUpdater.ensureSourceClassInitialized();
      Iterator var8 = ((ModelPartAccessor)(Object)var0).purxiy$children().entrySet().iterator();
      byte var9 = 0;

      while (var8.hasNext()) {
         Object var10;
         label42: {
            Entry var11;
            label41: {
               label48: {
                  int var12;
                  label49: {
                     var11 = (Entry)var8.next();
                     String var13 = (String)var11.getKey();
                     var10 = var13;
                     if (var2 > 1L) {
                        var12 = 968083020;
                        if (var2 < 1L) {
                           break label49;
                        }

                        int var14 = 968096693;
                     }

                     if (var13.equals("head")) {
                        break label48;
                     }

                     var10 = var13;
                     var12 = var9;
                  }

                  if (var2 > 0L) {
                     if (var12 != 0) {
                        break label42;
                     }

                     var12 = 968096694;
                  }

                  if (!((String)var10).endsWith("_head")) {
                     break label41;
                  }
               }

               Q((class_630)var11.getValue(), CuteWorldTransform.CutePart.K, var1, var6);
            }

            var10 = var11.getValue();
         }

         W((class_630)var10, var1, var4);
      }
   }

   private static void l(class_630 var0, String var1, CuteWorldTransform.CutePart var2, List var3, long var4) {
      long var6 = var4 ^ 94867298891458L;
      long var8 = var4 ^ 0L;
      CuteWorldUpdater.ensureSourceClassInitialized();

      for (Entry var11 : ((ModelPartAccessor)(Object)var0).purxiy$children().entrySet()) {
         Object var12 = (String)var11.getKey();
         if (var4 > 0L) {
            if (!var12.equals(var1)) {
               l((class_630)var11.getValue(), var1, var2, var3, var8);
               continue;
            }

            var12 = var11.getValue();
         }

         Q((class_630)var12, var2, var3, var6);
         if (var4 < 1L) {
         }
      }
   }

   private static void Q(class_630 var0, CuteWorldTransform.CutePart var1, List var2, long var3) {
      long var6 = var3 ^ 23730068384793L;
      CuteWorldUpdater.ensureSourceClassInitialized();
      class_593 var8 = null;
      class_628 var9 = null;
      float var10 = 0.0F;
      Iterator var11 = ((ModelPartAccessor)(Object)var0).purxiy$cubes().iterator();
      boolean var12 = false;

      while (var11.hasNext()) {
         class_628 var13 = (class_628)var11.next();
         float var14 = (var13.field_3648 - var13.field_3645) * (var13.field_3647 - var13.field_3644);
         float var15 = var14;

         label142:
         while (!(var15 <= var10)) {
            for (class_593 var19 : var13.field_3649) {
               if (var3 > 0L) {
                  class_593 var20 = var19;
                  if (var3 > 1L) {
                     var15 = var19.comp_3185().z();
                     if (var3 <= 1L) {
                        continue label142;
                     }

                     if (!(var15 < -0.9F)) {
                        continue;
                     }

                     var20 = var19;
                  }

                  var8 = var20;
                  var9 = var13;
                  var10 = var14;
               }
            }

            if (var3 >= 1L) {
            }
            break;
         }
      }

      if (var8 != null && !(var10 < 2.0F)) {
         double var36 = 1.0;
         double var37 = 1.0;
         double var38 = 0.0;
         double var39 = 0.0;
         class_618[] var21 = var8.comp_3184();
         int var22 = var21.length;
         int var23 = 0;

         double var24;
         double var26;
         while (true) {
            if (var23 >= var22) {
               if (var3 <= 1L) {
                  return;
               }

               var24 = var36;
               var26 = 0.0;
               break;
            }

            class_618 var28 = var21[var23];
            var36 = Math.min(var36, (double)var28.comp_3187());
            var24 = var37;
            var26 = var28.comp_3188();
            if (var3 <= 1L) {
               break;
            }

            var37 = Math.min(var37, var26);
            var38 = Math.max(var38, (double)var28.comp_3187());
            var39 = Math.max(var39, (double)var28.comp_3188());
            var23++;
         }

         if (var3 >= 1L) {
            if (!(var24 >= var26)) {
               return;
            }

            var24 = var37;
            var26 = 0.0;
         }

         if (var24 >= var26 && var38 <= 1.0 && var39 <= 1.0) {
            double var40 = var38;
            if (var3 > 1L) {
               if (!(var38 > var36)) {
                  return;
               }

               var40 = var39;
            }

            if (var40 > var37) {
               double var30 = 0.54;
               if (var1 == CuteWorldTransform.CutePart.K && var9 != null) {
                  var30 = Q(var0, var9, 0.0F, var6, 0.0F, var30);

                  for (Entry var33 : ((ModelPartAccessor)(Object)var0).purxiy$children().entrySet()) {
                     boolean var5;
                     label99: {
                        label98: {
                           label97: {
                              Object var34 = (String)var33.getKey();
                              if (var3 >= 0L) {
                                 if (var34.equals("nose")) {
                                    break label97;
                                 }

                                 var34 = var33.getKey();
                              }

                              if (!((String)var34).equals("snout")) {
                                 Object var35 = var33;
                                 if (var3 >= 0L) {
                                    var35 = var33.getKey();
                                 }

                                 var5 = ((String)var35).equals("muzzle");
                                 if (var3 < 0L) {
                                    break label99;
                                 }

                                 if (!var5) {
                                    break label98;
                                 }
                              }
                           }

                           class_630 var41 = (class_630)var33.getValue();
                           var30 = Q(var41, var9, var41.field_3656, var6, var41.field_3655, var30);
                        }

                        var5 = var12;
                     }

                     if (var5) {
                        break;
                     }
                  }
               }

               if (var3 >= 0L) {
                  var2.add(new CuteWorldTransform.TextureQuad(var36, var37, var38, var39, var1, var30));
               }
            }
         }
      }
   }

   private static double Q(class_630 var0, class_628 var1, float var2, long var3, float var5, double var6) {
      CuteWorldUpdater.ensureSourceClassInitialized();
      Iterator var8 = ((ModelPartAccessor)(Object)var0).purxiy$cubes().iterator();
      byte var9 = 0;

      while (var8.hasNext()) {
         byte var10;
         label74: {
            class_628 var11 = (class_628)var8.next();
            label73:
            if (var11 != var1) {
               float var12 = var11.field_3643;
               float var13 = var5;
               if (var3 > 1L) {
                  float var14;
                  var10 = (byte)((var14 = var11.field_3643 + var5 - (var1.field_3643 - 0.1F)) == 0.0F ? 0 : (var14 < 0.0F ? -1 : 1));
                  if (var3 <= 0L) {
                     break label74;
                  }

                  if (var10 >= 0) {
                     break label73;
                  }

                  var12 = var11.field_3648 - var11.field_3645;
                  var13 = var1.field_3648;
               }

               if (var3 > 0L) {
                  float var18;
                  var10 = (byte)((var18 = var12 - (var13 - var1.field_3645) * 0.4F) == 0.0F ? 0 : (var18 < 0.0F ? -1 : 1));
                  if (var3 < 0L) {
                     break label74;
                  }

                  if (var10 < 0) {
                     break label73;
                  }

                  var12 = var11.field_3644 + var2 - var1.field_3644;
                  var13 = var1.field_3647 - var1.field_3644;
               }

               double var19 = var12 / var13;
               double var16;
               var10 = (byte)((var16 = var19 - 0.25) == 0.0 ? 0 : (var16 < 0.0 ? -1 : 1));
               if (var3 < 0L) {
                  break label74;
               }

               if (var10 > 0) {
                  var6 = Math.max(0.28, Math.min(var6, var19 - 0.16));
               }
            }

            var10 = var9;
         }

         if (var10 != 0) {
            break;
         }
      }

      return var6;
   }
}
