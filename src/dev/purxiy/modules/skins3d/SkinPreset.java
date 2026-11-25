package dev.purxiy.modules.skins3d;

import dev.purxiy.loader.fabric.ClientServices;
import net.minecraft.class_2960;
import net.minecraft.class_7920;
import net.minecraft.class_8685;
import net.minecraft.class_12079.class_10726;

public enum SkinPreset {
   n("own", "Your skin", 22974, "Yours", 7473584, (byte)-106, "Your own skin, with its outer layer in real 3D", false, 1.0F, SkinPreset.Data.P, -4222985),
   U(
      "egirl",
      "E-Girl",
      22974,
      "Original",
      7473584,
      (byte)-106,
      "Split-dye hair down her back, a choker and platform boots",
      true,
      0.95F,
      new SkinPreset.Data(1.0F, 0.92F, 0.92F, 0.9F, 0.9F),
      -546605
   ),
   G(
      "stargazer",
      "Stargazer",
      22974,
      "Original",
      7473584,
      (byte)-106,
      "A hooded mage, stars stitched into the cloak, eyes that glow",
      false,
      1.04F,
      new SkinPreset.Data(1.0F, 1.07F, 1.08F, 1.02F, 1.02F),
      -3561473
   ),
   L(
      "shinobi",
      "Shinobi",
      22974,
      "Original",
      7473584,
      (byte)-106,
      "Hood and mask, a headband in the wind, a sword across the back",
      false,
      1.0F,
      new SkinPreset.Data(0.98F, 0.95F, 0.94F, 0.95F, 0.95F),
      -4883457
   ),
   d(
      "peter_griffin",
      "Peter Griffin",
      14648057342155L,
      "Icon",
      "A round belly, wire glasses, a cleft chin and rolled-up sleeves",
      false,
      1.0F,
      SkinPreset.Data.P,
      -9262216,
      true
   ),
   B(
      "spiderman",
      "Spider-Man",
      14648057342155L,
      "Icon",
      "Tom Holland's Homecoming suit, a detailed face and a removable cloth mask",
      false,
      0.96F,
      SkinPreset.Data.P,
      -2806984,
      true
   ),
   e(
      "mario",
      "Mario",
      14648057342155L,
      "Icon",
      "Tiny voxels, a round nose, a scalloped moustache and stitched blue overalls",
      false,
      0.84F,
      SkinPreset.Data.P,
      -1235664,
      true
   ),
   C(
      "pikachu",
      "Pikachu",
      14648057342155L,
      "Icon",
      "Black-tipped ears, red cheek pouches and a solid lightning-bolt tail",
      false,
      0.5F,
      SkinPreset.Data.P,
      -403385,
      true
   ),
   g(
      "spongebob",
      "SpongeBob",
      14648057342155L,
      "Icon",
      "A tiny-voxel sponge, bright blue eyes, a big smile and square pants",
      true,
      0.76F,
      SkinPreset.Data.P,
      -6837,
      true
   ),
   I(
      "uwu_girl",
      "E-Girl 2",
      14648057342155L,
      "Icon",
      "The UwU Girl: wavy brown hair, a pink cat headset and a soft grey hoodie",
      true,
      0.96F,
      SkinPreset.Data.P,
      -1261359,
      true
   );

   static final class_2960 T = ClientServices.id("textures/skins3d/spiderman_unmasked.png");
   static final class_2960 Q = ClientServices.id("models/skins3d/spiderman_unmasked.vxm");
   static final class_2960 H = ClientServices.id("models/skins3d/spiderman_mask_shell.vxm");
   static final class_2960 q = ClientServices.id("models/skins3d/spiderman_mask_dropped.vxm");
   public final String x;
   public final String N;
   public final String P;
   public final String c;
   public final boolean o;
   public final float t;
   public final SkinPreset.Data h;
   public final int y;
   private final class_2960 b;
   private final class_2960 F;
   private class_8685 u;
   private class_8685 J;
   public static int S = 0;

   private SkinPreset(
      String nullxx,
      String nullxxx,
      int nullxxxx,
      String nullxxxxx,
      int nullxxxxxx,
      byte nullxxxxxxx,
      String nullxxxxxxxx,
      boolean nullxxxxxxxxx,
      float nullxxxxxxxxxx,
      SkinPreset.Data nullxxxxxxxxxxx,
      int nullxxxxxxxxxxxx
   ) {
      this(nullxx, nullxxx, 14648057342155L, nullxxxxx, nullxxxxxxxx, nullxxxxxxxxx, nullxxxxxxxxxx, nullxxxxxxxxxxx, nullxxxxxxxxxxxx, false);
      long var14 = 98674491895958L;
      long var16 = var14 ^ 93376720967773L;
   }

   private SkinPreset(
      String nullxx,
      String nullxxx,
      long nullxxxx,
      String nullxxxxx,
      String nullxxxxxx,
      boolean nullxxxxxxx,
      float nullxxxxxxxx,
      SkinPreset.Data nullxxxxxxxxx,
      int nullxxxxxxxxxx,
      boolean nullxxxxxxxxxxx
   ) {
      this.x = nullxx;
      this.N = nullxxx;
      this.P = nullxxxxx;
      this.c = nullxxxxxx;
      this.o = nullxxxxxxx;
      this.t = nullxxxxxxxx;
      this.h = nullxxxxxxxxx;
      this.y = nullxxxxxxxxxx;
      long var14 = 14648057342154L;
      int var16 = var14 < 0L ? -1 : 1;
      var16 = 2076109922;
      this.b = nullxx.equals("own") ? null : ClientServices.id("textures/skins3d/" + nullxx + ".png");
      this.F = nullxxxxxxxxxxx ? ClientServices.id("models/skins3d/" + nullxx + ".vxm") : null;
   }

   public class_2960 texture() {
      return this == B && Skins3DUpdater.unmasked() ? T : this.b;
   }

   public class_2960 voxelModel() {
      return this == B && Skins3DUpdater.unmasked() ? Q : this.F;
   }

   public static class_2960 voxelModel(class_2960 var0) {
      if (var0.equals(T)) {
         return Q;
      } else {
         for (SkinPreset var4 : values()) {
            if (var0.equals(var4.b)) {
               return var4.F;
            }
         }

         return null;
      }
   }

   public static SkinPreset sculpted(class_2960 var0) {
      if (var0.equals(T)) {
         return B;
      } else {
         for (SkinPreset var4 : values()) {
            if (var4.F != null && var0.equals(var4.b)) {
               return var4;
            }
         }

         return null;
      }
   }

   static boolean spiderTexture(class_2960 var0) {
      return var0.equals(B.b) || var0.equals(T);
   }

   public class_8685 dress(class_8685 var1) {
      class_2960 var2 = this.texture();
      if (var2 == null) {
         return var1;
      } else {
         if (var1 != this.u || this.J == null || !this.J.comp_1626().comp_3627().equals(var2)) {
            String var3 = this.x;
            String var4 = var2.equals(T) ? "_unmasked" : "";
            class_10726 var6 = new class_10726(ClientServices.id("skins3d/" + var3 + var4), var2);
            this.J = new class_8685(var6, var1.comp_1627(), var1.comp_1628(), this.o ? class_7920.field_41122 : class_7920.field_41123, var1.comp_1630());
            this.u = var1;
         }

         return this.J;
      }
   }

   public static String[] labels() {
      SkinPreset[] var0 = values();
      String[] var1 = new String[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = var0[var2].N;
      }

      return var1;
   }

   public static SkinPreset byLabel(String var0) {
      for (SkinPreset var4 : values()) {
         if (var4.N.equals(var0)) {
            return var4;
         }
      }

      return n;
   }

   public static SkinPreset byKey(String var0) {
      for (SkinPreset var4 : values()) {
         if (var4.x.equalsIgnoreCase(var0) || var4.N.equalsIgnoreCase(var0)) {
            return var4;
         }
      }

      return null;
   }

   public static void ensureSourceClassInitialized() {
   }

   public record Data(float p, float Y, float M, float w, float b, float y, float q) {
      public static final SkinPreset.Data P = new SkinPreset.Data(1.0F, 1.0F, 1.0F, 1.0F, 1.0F);

      public Data(float var1, float var2, float var3, float var4, float var5) {
         this(var1, var1, var1, var2, var3, var4, var5);
      }

      public float scale(float var1) {
         return var1 * 32.0F / (24.0F + 8.0F * this.Y);
      }

      public float headWidth() {
         return this.p;
      }

      public float headHeight() {
         return this.Y;
      }

      public float headDepth() {
         return this.M;
      }

      public float bodyWidth() {
         return this.w;
      }

      public float bodyDepth() {
         return this.b;
      }

      public float legWidth() {
         return this.y;
      }

      public float legDepth() {
         return this.q;
      }

      public float floatState35() {
         return this.p;
      }

      public float floatState26() {
         return this.Y;
      }

      public float floatState27() {
         return this.M;
      }

      public float floatState6() {
         return this.w;
      }

      public float floatState32() {
         return this.b;
      }

      public float floatState2() {
         return this.y;
      }

      public float floatState48() {
         return this.q;
      }
   }
}
