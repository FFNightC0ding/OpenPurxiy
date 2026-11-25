package dev.purxiy.ui.clickgui;

import dev.purxiy.ui.DrawContext;

public interface Studio {
   String title();

   String subtitle();

   String icon();

   default String action() {
      return "Open studio";
   }

   default boolean available() {
      return true;
   }

   default Studio.PanelSide dock() {
      return Studio.PanelSide.TOP_LEFT;
   }

   default boolean keepsWorldRunning() {
      return false;
   }

   void setOpen(boolean var1);

   void render(DrawContext var1, float var2, float var3);

   boolean mouseDown(float var1, float var2, int var3);

   void mouseDrag(float var1, float var2);

   void mouseUp(float var1, float var2, int var3);

   boolean scroll(float var1, float var2, double var3);

   boolean keyPressed(int var1);

   boolean consumeCloseRequest();

   public static enum PanelSide {
      TOP_LEFT,
      TOP_RIGHT,
      RIGHT;

      public boolean right() {
         return this != TOP_LEFT;
      }

      private static Studio.PanelSide[] $values() {
         return new Studio.PanelSide[]{TOP_LEFT, TOP_RIGHT, RIGHT};
      }
   }
}
