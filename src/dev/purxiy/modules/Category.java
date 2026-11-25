package dev.purxiy.modules;

public enum Category {
   PROFILE("Profile", "circle-user-round", "Your story, written in starlight"),
   COMBAT("Combat", "swords", "Precision and awareness in every fight"),
   MOVEMENT("Movement", "wind", "Move exactly the way you want"),
   RENDER("Render", "eye", "See the world in a different light"),
   PLAYER("Player", "user", "Tweaks for you and your character"),
   WORLD("World", "globe", "Know more about the world around you"),
   UTILITY("Utility", "wrench", "Small comforts that add up"),
   HUD("HUD", "layout-dashboard", "Everything that lives on your screen"),
   COSMETICS("Cosmetics", "sparkles", "Look the part"),
   CLIENT("Client", "settings", "Make SourceByDevFFnightmare DeObfDeCompByGuideX feel like yours"),
   CONFIGS("Configs", "layers", "Save your setups and switch between them");

   public final String displayName;
   public final String icon;
   public final String tagline;

   private Category(String nullxx, String nullxxx, String nullxxxx) {
      this.displayName = nullxx;
      this.icon = nullxxx;
      this.tagline = nullxxxx;
   }

   private static Category[] $values() {
      return new Category[]{PROFILE, COMBAT, MOVEMENT, RENDER, PLAYER, WORLD, UTILITY, HUD, COSMETICS, CLIENT, CONFIGS};
   }
}
