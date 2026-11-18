// Sourced By DevFFNightmare
package dev.purxiy.persist;

import dev.purxiy.loader.fabric.MenuCommands;
import dev.purxiy.modules.Category;
import dev.purxiy.ui.config.ConfigLibraryTransform;

public final class FCodec {
   public static Category lastCategory = Category.RENDER;
   private static boolean saving;

   private FCodec() {
   }

   public static void save() {
      if (!saving) {
         saving = true;

         try {
            MenuCommands.flush();
            ConfigLibraryTransform.liveSaved();
         } finally {
            saving = false;
         }
      }
   }
}
