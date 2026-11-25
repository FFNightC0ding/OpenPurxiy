package dev.purxiy.modules.targethud;

import java.util.Locale;

public enum PvpMode {
   s("General", "General", "crosshair", "Armour, held item and who is ahead"),
   U("Crystal", "Crystal PvP", "gem", "Totem pops, totem in hand and armour wear"),
   X("Sword", "Sword", "sword", "Combo, hits traded and who is ahead"),
   Y("Axe", "Axe & shield", "axe", "Shield up, down or disabled, hits and armour"),
   D("Mace", "Mace", "hammer", "Height in the air, totem pops and wind charges"),
   l("UHC", "UHC", "apple", "Golden apples eaten, arrow hits and armour"),
   j("Pot", "Pot", "flask-conical", "Potions thrown, active effects and armour"),
   g("NethOP", "NethOP", "flask-round", "Potions, experience bottles and totem pops"),
   a("SMP", "SMP", "tent-tree", "Totem pops, ender pearls and golden apples"),
   P("Spear", "Spear", "move-up-right", "Speed, hits traded and who is ahead");

   public final String R;
   public final String S;
   public final String Z;
   public final String C;
   private final String z;

   private PvpMode(String nullxx, String nullxxx, String nullxxxx, String nullxxxxx) {
      this.R = nullxx;
      this.S = nullxxx;
      this.Z = nullxxxx;
      this.C = nullxxxxx;
      this.z = nullxx.toUpperCase(Locale.ROOT);
   }

   public String badge() {
      return this.z;
   }

   public static String[] labels() {
      PvpMode[] var0 = values();
      String[] var1 = new String[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = var0[var2].R;
      }

      return var1;
   }

   public static PvpMode byLabel(String var0) {
      for (PvpMode var4 : values()) {
         if (var4.R.equals(var0)) {
            return var4;
         }
      }

      return s;
   }
}
