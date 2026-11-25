package dev.purxiy.modules.home;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_1058;
import net.minecraft.class_1059;
import net.minecraft.class_10725;
import net.minecraft.class_11697;
import net.minecraft.class_2960;
import net.minecraft.class_310;

final class HomeCodec2 {
   static final String[] b = new String[]{
      "grass_block_top",
      "grass_block_side",
      "grass_block_side_overlay",
      "dirt",
      "stone",
      "sand",
      "gravel",
      "clay",
      "water_still",
      "oak_log",
      "oak_log_top",
      "oak_leaves",
      "oak_sapling",
      "birch_log",
      "birch_log_top",
      "birch_leaves",
      "birch_sapling",
      "cherry_log",
      "cherry_log_top",
      "cherry_leaves",
      "cherry_sapling",
      "spruce_log",
      "spruce_log_top",
      "spruce_leaves",
      "spruce_sapling",
      "short_grass",
      "tall_grass_bottom",
      "tall_grass_top",
      "fern",
      "poppy",
      "dandelion",
      "cornflower",
      "azure_bluet",
      "oxeye_daisy",
      "allium",
      "blue_orchid",
      "lilac_bottom",
      "lilac_top",
      "rose_bush_bottom",
      "rose_bush_top",
      "peony_bottom",
      "peony_top",
      "sugar_cane",
      "lily_pad",
      "firefly_bush",
      "firefly_bush_emissive",
      "bush",
      "pink_petals",
      "leaf_litter"
   };
   static final String[] Y = new String[]{
      "glint",
      "firefly",
      "cherry_0",
      "cherry_1",
      "cherry_2",
      "cherry_3",
      "cherry_4",
      "cherry_5",
      "cherry_6",
      "cherry_7",
      "cherry_8",
      "cherry_9",
      "cherry_10",
      "cherry_11",
      "leaf_0",
      "leaf_1",
      "leaf_2",
      "leaf_3",
      "leaf_4",
      "leaf_5",
      "leaf_6",
      "leaf_7",
      "leaf_8",
      "leaf_9",
      "leaf_10",
      "leaf_11"
   };
   private final Map<String, float[]> j;
   private final Map<String, float[]> W;
   final float[] s;
   final float[] p;
   final Object d;

   private HomeCodec2(long var1, class_1059 var3, class_1059 var4, class_1059 var5) {
      long var6 = 54876085799212L;
      byte var8 = 0;
      short var9 = 12776;
      int var10 = (int)(var6 << 32 >>> 32);
      HomeSupport2.ensureSourceClassInitialized();
      this.j = new HashMap<>();
      this.W = new HashMap<>();
      this.d = key(var3);
      String[] var11 = b;
      int var12 = var11.length;

      for (int var13 = 0; var13 < var12; var13++) {
         long var14 = 61607592754296L;
         int var17 = var14 < 0L ? -1 : 1;
         String var17x = var11[var13];
         this.j.put(var17x, g(var3.method_4608(class_2960.method_60656("block/" + var17x))));
      }

      long var24 = 61607592754297L;
      int var25 = var24 < 0L ? -1 : 1;

      for (String var19 : Y) {
         long var20 = 61607592754296L;
         int var21 = var20 < 0L ? -1 : 1;
         this.W.put(var19, g(var4.method_4608(class_2960.method_60656(var19))));
      }

      long var25x = 61607592754296L;
      int var27 = var25x < 0L ? -1 : 1;
      long var27x = 61607592754296L;
      int var29 = var27x < 0L ? -1 : 1;
      this.s = var5 != null ? g(var5.method_4608(class_2960.method_60656("sun"))) : new float[4];
      long var22 = 61607592754296L;
      int var31 = var22 < 0L ? -1 : 1;
      this.p = var5 != null ? g(var5.method_4608(class_2960.method_60656("moon/" + n((char)var8, var9, var10)))) : new float[4];
   }

   static HomeCodec2 read() {
      long var0 = 128790131342812L;
      long var2 = var0 ^ 84844539871653L;

      try {
         class_11697 var4 = class_310.method_1551().method_72703();
         class_1059 var5 = var4.method_73025(class_10725.field_56382);
         class_1059 var6 = var4.method_73025(class_10725.field_56389);
         class_1059 var7 = null;

         try {
            var7 = var4.method_73025(class_10725.field_63647);
         } catch (RuntimeException var9) {
         }

         return new HomeCodec2(var2, var5, var6, var7);
      } catch (RuntimeException var101) {
         return null;
      }
   }

   static Object key(class_1059 var0) {
      return var0.method_4608(class_2960.method_60656("block/grass_block_top"));
   }

   float[] block(String var1) {
      float[] var2 = this.j.get(var1);
      return var2 != null ? var2 : this.j.get("dirt");
   }

   float[] particle(String var1) {
      float[] var2 = this.W.get(var1);
      return var2 != null ? var2 : this.W.get("glint");
   }

   private static float[] g(class_1058 var0) {
      return new float[]{var0.method_4594(), var0.method_4593(), var0.method_4577(), var0.method_4575()};
   }

   private static String n(char var0, short var1, int var2) {
      double var3 = System.currentTimeMillis() / 8.64E7 - 10962.76;
      double var5 = (var3 % 29.530588 + 29.530588) % 29.530588 / 29.530588;
      String[] var7 = new String[]{
         "new_moon", "waxing_crescent", "first_quarter", "waxing_gibbous", "full_moon", "waning_gibbous", "third_quarter", "waning_crescent"
      };
      int var8 = (int)Math.floor(var5 * 8.0 + 0.5) % 8;
      return var8 == 0 ? "waxing_crescent" : var7[var8];
   }
}
