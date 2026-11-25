package dev.purxiy.ui.config;

import com.google.gson.JsonObject;
import dev.purxiy.modules.Module;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.render.BlendMode;
import dev.purxiy.ui.Theme;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public final class ConfigLibraryStats {
   private static final float HUE_FROM = 240.0F;
   private static final float HUE_TO = 338.0F;
   public final String id;
   public String name;
   public String author;
   public String description;
   public final Set<String> modules;

   public JsonObject settings;
   public String[] tags;
   public final ConfigLibraryStats.ConfigOrigin source;
   public final long created;
   public long edited;
   public int likes;
   public int downloads;
   public boolean liked;
   public boolean featured;
   public boolean added;
   public boolean own;
   public float hue;

   public String photo;

   public String code;

   public String origin;
   public long synced;

   public String syncedPhoto;

   private ConfigLibraryNebula.NebulaStar figure;

   ConfigLibraryStats(
      String var1, String var2, String var3, String var4, Collection<String> var5, String[] var6, ConfigLibraryStats.ConfigOrigin var7, long var8, float var10
   ) {
      this.id = var1;
      this.name = var2;
      this.author = var3;
      this.description = var4;
      this.modules = new LinkedHashSet<>(var5);
      this.tags = var6;
      this.source = var7;
      this.created = var8;
      this.edited = var8;
      this.hue = var10;
   }

   static ConfigLibraryStats create(String var0, String var1, String var2, Collection<String> var3, ConfigLibraryStats.ConfigOrigin var4) {
      long var5 = System.currentTimeMillis();
      return new ConfigLibraryStats(UUID.randomUUID().toString(), var0, var1, var2, var3, new String[0], var4, var5, hueOf(var0));
   }

   static float hueOf(String var0) {
      int var1 = var0.toLowerCase(Locale.ROOT).hashCode() * -1640531535;
      return (var1 >>> 8 & 65535) / 65535.0F;
   }

   public boolean mine() {
      return this.source != ConfigLibraryStats.ConfigOrigin.COMMUNITY;
   }

   void modulesChanged() {
      this.figure = null;
   }

   ConfigLibraryNebula.NebulaStar figure() {
      if (this.figure == null) {
         this.figure = ConfigLibraryNebula.figure(this.modules);
      }

      return this.figure;
   }

   public int moduleCount() {
      int var1 = 0;

      for (String var3 : this.modules) {
         Module var4 = SettingSection.byId(var3);
         if (var4 != null && var4.toggleable()) {
            var1++;
         }
      }

      return var1;
   }

   public int accent() {
      return Theme.tint(hsv(240.0F + 98.0F * this.hue, 0.58F, 1.0F));
   }

   int accentDeep() {
      float var1 = 240.0F + 98.0F * this.hue;
      return Theme.tint(hsv(var1 - 34.0F, 0.78F, 0.92F));
   }

   int night() {
      return BlendMode.lerp(Theme.tint(-16120041), this.accentDeep(), 0.2F);
   }

   static int hsv(float var0, float var1, float var2) {
      float var3 = (var0 % 360.0F + 360.0F) % 360.0F / 60.0F;
      int var4 = (int)Math.floor(var3);
      float var5 = var3 - var4;
      float var6 = var2 * (1.0F - var1);
      float var7 = var2 * (1.0F - var1 * var5);
      float var8 = var2 * (1.0F - var1 * (1.0F - var5));
      float var9;
      float var10;
      float var11;
      switch (var4) {
         case 0:
            var9 = var2;
            var10 = var8;
            var11 = var6;
            break;
         case 1:
            var9 = var7;
            var10 = var2;
            var11 = var6;
            break;
         case 2:
            var9 = var6;
            var10 = var2;
            var11 = var8;
            break;
         case 3:
            var9 = var6;
            var10 = var7;
            var11 = var2;
            break;
         case 4:
            var9 = var8;
            var10 = var6;
            var11 = var2;
            break;
         default:
            var9 = var2;
            var10 = var6;
            var11 = var7;
      }

      return BlendMode.argb(1.0F, var9, var10, var11);
   }

   boolean unsynced() {
      return this.code == null || this.synced != this.edited || !Objects.equals(this.syncedPhoto, this.photo);
   }

   public static enum ConfigOrigin {
      SAVED,
      PASTED,
      FILE,
      ADDED,
      COMMUNITY;

      private static ConfigLibraryStats.ConfigOrigin[] $values() {
         return new ConfigLibraryStats.ConfigOrigin[]{SAVED, PASTED, FILE, ADDED, COMMUNITY};
      }
   }
}
