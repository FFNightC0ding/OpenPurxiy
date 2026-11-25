package dev.purxiy.modules.knife;

import java.util.ArrayList;
import java.util.List;

public final class KnifeCamos {
   public static final String q = "Original";
   static final List<List<KnifeCamos.Camo>> Y = List.of(
      List.of(
         new KnifeCamos.Camo("Fade", "fade", "Amber, magenta and violet anodized steel"),
         new KnifeCamos.Camo("Doppler", "doppler", "Ruby and violet smoke beneath polished steel"),
         new KnifeCamos.Camo("Crimson Web", "crimson_web", "Black spiderwebs over a crimson lacquer"),
         new KnifeCamos.Camo("Tiger Tooth", "tiger_tooth", "Sharp black stripes on warm golden metal")
      ),
      List.of(
         new KnifeCamos.Camo("Marble Fade", "marble_fade", "Flowing red, blue and gold marble"),
         new KnifeCamos.Camo("Sapphire", "sapphire", "Deep blue crystalline swirls"),
         new KnifeCamos.Camo("Slaughter", "slaughter", "Scarlet and silver abstract camouflage"),
         new KnifeCamos.Camo("Black Laminate", "black_laminate", "Dark steel with layered charcoal grips")
      ),
      List.of(
         new KnifeCamos.Camo("Case Hardened", "case_hardened", "Blue, violet and gold heat-treated steel"),
         new KnifeCamos.Camo("Lore", "lore", "Engraved gold scrollwork and an olive grip"),
         new KnifeCamos.Camo("Damascus Steel", "damascus_steel", "Silver and charcoal folded-steel waves"),
         new KnifeCamos.Camo("Autotronic", "autotronic", "Red technical inlays over brushed steel")
      ),
      List.of(
         new KnifeCamos.Camo("Gold", "cod_gold", "Black and gold with engraved contour lines"),
         new KnifeCamos.Camo("Damascus", "cod_damascus", "Cobalt and copper folded-metal ripples"),
         new KnifeCamos.Camo("Orion", "orion", "Violet nebulae with bright amber islands"),
         new KnifeCamos.Camo("Interstellar", "interstellar", "A cyan and magenta galaxy full of stars")
      ),
      List.of(
         new KnifeCamos.Camo("Chroma", "chroma", "A rainbow of crystal facets with gold accents"),
         new KnifeCamos.Camo("Ice Shard", "ice_shard", "Frozen blue facets and delicate white fractures"),
         new KnifeCamos.Camo("Gemstone", "gemstone", "Amethyst crystal, gilded details and a wrapped grip"),
         new KnifeCamos.Camo("Candy", "candy", "Red and ivory candy stripes with an emerald grip")
      )
   );

   private KnifeCamos() {
   }

   public static List<KnifeCamos.Camo> forModel(int var0) {
      return Y.get(var0);
   }

   public static String game(int var0) {
      return var0 < 3 ? "Counter-Strike" : (var0 == 3 ? "Call of Duty" : "Murder Mystery 2");
   }

   public static String settingId(int var0) {
      return new String[]{"karambit_camo", "butterfly_camo", "bayonet_camo", "tactical_camo", "crystal_camo"}[var0];
   }

   public static List<String> choices(int var0) {
      ArrayList var1 = new ArrayList();
      var1.add("Original");

      for (KnifeCamos.Camo var3 : forModel(var0)) {
         var1.add(var3.name());
      }

      return List.copyOf(var1);
   }

   public static KnifeCamos.Camo find(int var0, String var1) {
      for (KnifeCamos.Camo var3 : forModel(var0)) {
         if (var3.name().equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   public record Camo(String a, String C, String Z) {
      public String texture() {
         return "textures/knife/camos/" + this.C + ".png";
      }

      public String swatch() {
         return "textures/knife/camos/" + this.C + "_swatch.png";
      }

      public String name() {
         return this.a;
      }

      public String id() {
         return this.C;
      }

      public String detail() {
         return this.Z;
      }

      public String stringState40() {
         return this.a;
      }

      public String stringState15() {
         return this.C;
      }

      public String stringState43() {
         return this.Z;
      }
   }
}
