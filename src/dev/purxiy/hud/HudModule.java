package dev.purxiy.hud;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.Setting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.api.ModuleEntrypoint;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.CosmeticCatalog;
import dev.purxiy.loader.fabric.MenuCommands;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.modules.Category;
import dev.purxiy.modules.Module;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.TrueTypeFont;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;

public abstract class HudModule extends Module implements ModuleEntrypoint {
   public static final float MARGIN = 6.0F;
   protected static final float RADIUS = 7.0F;
   public final NumberSetting size;
   public final BooleanSetting background;
   public final NumberSetting opacity;
   final NumberSetting posX;
   final NumberSetting posY;
   final BooleanSetting placed;
   float x;
   float y;
   float width;
   float height;
   boolean laidOut;
   final float[] measure = new float[2];
   boolean pinned;
   float pinScale = 1.0F;
   final float[] pin = new float[3];

   private final Module owner;
   final Animator presence = Animator.smooth(0.0F);
   final Animator renderX = new Animator(0.0F, 420.0F, 42.0F);
   final Animator renderY = new Animator(0.0F, 420.0F, 42.0F);
   final Animator renderScale = new Animator(1.0F, 420.0F, 42.0F);
   boolean positioned;
   float pinFlight = 1.0F;
   float pinFromX;
   float pinFromY;
   float pinFromScale;
   boolean wanted;

   HudGroupModule group;

   HudGroupModule dockedTo;
   int dockedSlot = -1;
   final Animator dockX = new Animator(0.0F, 300.0F, 35.0F);
   final Animator dockY = new Animator(0.0F, 300.0F, 35.0F);
   final Animator dockScale = new Animator(0.0F, 300.0F, 35.0F);

   protected HudModule(String var1, String var2, String var3, String var4, double var5, double var7) {
      this(var1, var2, var3, var4, var5, var7, (ModuleDescriptor)null, "", 0.6);
   }

   protected HudModule(String var1, String var2, String var3, String var4, double var5, double var7,  Module var9, String var10, double var11) {
      super(var1, var2, var3, var4, Category.HUD);
      this.owner = var9;
      Object var13 = var9 != null ? var9 : this;
      this.size = ((Module)var13).number(
         var10 + "size", "Size", "Or scroll on it while the HUD editor is open", 1.0, 0.5, 2.0, 0.05, NumberSetting.Listener.PERCENT
      );
      this.background = ((Module)var13).bool(var10 + "background", "Background", "A frosted panel behind the element", true);
      this.opacity = ((Module)var13).number(var10 + "opacity", "Background opacity", (String)null, var11, 0.0, 1.0, 0.05, NumberSetting.Listener.PERCENT);
      NumberSetting var14 = this.opacity;
      BooleanSetting var15 = this.background;
      var14.visibleWhen(var15::enabled);
      this.posX = ((Module)var13).number(var10 + "x", "X", (String)null, var5, 0.0, 1.0, 0.0, NumberSetting.Listener.DECIMAL);
      this.posY = ((Module)var13).number(var10 + "y", "Y", (String)null, var7, 0.0, 1.0, 0.0, NumberSetting.Listener.DECIMAL);
      this.placed = ((Module)var13).bool(var10 + "placed", "Placed", (String)null, false);
      this.posX.visibleWhen(() -> false);
      this.posY.visibleWhen(() -> false);
      this.placed.visibleWhen(() -> false);
   }

   protected abstract void measure(HudBounds var1, float[] var2);

   protected abstract void render(Canvas var1, HudBounds var2);

   protected void renderVanilla(HudDragState var1, HudBounds var2) {
   }

   protected boolean hasContent(HudBounds var1) {
      return true;
   }

   protected boolean shown() {
      return this.enabled();
   }

   protected boolean pin(HudBounds var1, float[] var2) {
      return false;
   }

   protected final boolean pinned() {
      return this.pinned;
   }

   final boolean arranged() {
      return this.laidOut && !this.pinned && this.group == null;
   }

   protected boolean mergeable() {
      return false;
   }

   protected float cornerRadius() {
      return 7.0F;
   }

   protected void renderMiniature(Canvas var1, HudBounds var2) {
      this.render(var1, var2);
   }


   public final HudGroupModule group() {
      return this.group;
   }

   public final boolean onScreen() {
      return this.laidOut && this.presence.value() > 0.5F;
   }

   public final Module settingsModule() {
      return (Module)(this.owner != null ? this.owner : this);
   }

   protected boolean pointerDown(float var1, float var2, int var3) {
      return false;
   }

   protected void pointerDrag(float var1, float var2) {
   }

   protected void pointerUp(float var1, float var2) {
   }

   protected boolean pointerScroll(float var1, float var2, double var3) {
      return false;
   }


   protected String editorHint(float var1, float var2) {
      return null;
   }

   protected final float pointerX() {
      return HudLayoutEngine.active() ? this.localX(HudLayoutEngine.pointerX()) : Float.NaN;
   }

   protected final float pointerY() {
      return HudLayoutEngine.active() ? this.localY(HudLayoutEngine.pointerY()) : Float.NaN;
   }

   final float localX(float var1) {
      return (var1 - this.renderX.value()) / Math.max(0.001F, this.renderScale.value());
   }

   final float localY(float var1) {
      return (var1 - this.renderY.value()) / Math.max(0.001F, this.renderScale.value());
   }

   public final float scale() {
      return this.group != null ? this.group.size.floatValue() : this.size.floatValue();
   }

   protected final float contentWidth() {
      return this.width;
   }

   protected final float contentHeight() {
      return this.height;
   }

   public final float screenWidth() {
      return this.width * this.scale();
   }

   public final float screenHeight() {
      return this.height * this.scale();
   }

   final float renderWidth() {
      return this.width * this.renderScale.value();
   }

   final float renderHeight() {
      return this.height * this.renderScale.value();
   }

   public final float screenX() {
      return this.x;
   }

   public final float screenY() {
      return this.y;
   }

   protected final boolean rightSide(HudBounds var1) {
      return this.x + this.screenWidth() / 2.0F > var1.width / 2.0F;
   }

   protected final boolean bottomSide(HudBounds var1) {
      return this.y + this.screenHeight() / 2.0F > var1.height / 2.0F;
   }

   void moveTo(float var1, float var2, float var3, float var4) {
      float var5 = var3 - 12.0F - this.screenWidth();
      float var6 = var4 - 12.0F - this.screenHeight();
      this.posX.set(var5 > 0.0F ? Math.max(0.0F, Math.min(1.0F, (var1 - 6.0F) / var5)) : 0.5);
      this.posY.set(var6 > 0.0F ? Math.max(0.0F, Math.min(1.0F, (var2 - 6.0F) / var6)) : 0.5);
      this.x = 6.0F + (var5 > 0.0F ? this.posX.floatValue() * var5 : var5 / 2.0F);
      this.y = 6.0F + (var6 > 0.0F ? this.posY.floatValue() * var6 : var6 / 2.0F);
   }

   protected final void panel(Canvas var1, float var2, float var3) {
      this.panel(var1, 0.0F, 0.0F, var2, var3, 7.0F);
   }

   protected final void panel(Canvas var1, float var2, float var3, float var4, float var5, float var6) {
      if (this.group == null && this.background.enabled()) {
         float var7 = this.opacity.floatValue();
         if (!(var7 <= 0.01F)) {
            var1.shadow(var2, var3, var4, var5, var6, 10.0F, 0.0F, 0.0F, 2.0F, BlendMode.withAlpha(-16777216, 0.22F * var7));
            if (ClientServices.hudLook().glass()) {
               var1.glass(var2, var3, var4, var5, var6, BlendMode.withAlpha(Theme.tint(-16119788), 0.55F), var7, 1.15F, 0.012F);
            } else {
               var1.roundRect(var2, var3, var4, var5, var6, BlendMode.withAlpha(Theme.tint(-16053995), 0.78F * var7));
            }

            var1.roundRectCorners(var2, var3, var4, var5, var6, BlendMode.fade(352321535, var7), BlendMode.fade(184549375, var7), 16777215, 16777215);
            var1.roundRectOutlineGradient(var2, var3, var4, var5, var6, 1.0F, 90.0F, BlendMode.fade(654311423, var7), BlendMode.fade(150994943, var7));
         }
      }
   }

   protected final boolean needsShadow() {
      Object var1 = this.group != null ? this.group : this;
      return !((HudModule)var1).background.enabled() || ((HudModule)var1).opacity.floatValue() < 0.35F;
   }

   protected final float text(Canvas var1, TrueTypeFont var2, String var3, float var4, float var5, float var6, int var7, FontStyle var8, FontWeight var9) {
      if (this.needsShadow()) {
         var1.textGlow(
            var2, var3, var4, var5 + var6 * 0.07F, var6, BlendMode.withAlpha(-16777216, 0.75F * BlendMode.alpha(var7) / 255.0F), var8, var9, 0.0F, 0.6F, 1.6F
         );
      }

      return var1.text(var2, var3, var4, var5, var6, var7, var8, var9);
   }

   protected final void icon(Canvas var1, String var2, float var3, float var4, float var5, int var6) {
      if (this.needsShadow()) {
         var1.softCircle(var3, var4, var5 * 0.35F, var5 * 0.9F, 1426063360);
      }

      var1.icon(var2, var3, var4, var5, var6);
   }

   protected static int accent() {
      return Icons.PURPLE;
   }

   void updatePresence(boolean var1, float var2) {
      this.presence.target(var1 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var2));
   }

   protected HudModule(String var1, String var2, String var3, String var4, double var5, double var7, ModuleDescriptor var9, String var10, double var11) {
      this(var1, var2, var3, var4, var5, var7, (Module)var9, var10, var11);
   }

   @Override
   public final AutoCloseable start(ModuleContext var1) throws Exception {
      for (Setting var3 : this.settingsModule().settings()) {
         String var4 = var1.settings().get(var3.id());
         if (var4 != null) {
            MenuCommands.loadValue(var3, var4);
         }
      }

      var1.own(ClientServices.acquire(this.id()));
      var1.own(HudRegistry.install(this));
      this.opened(var1);
      this.setEnabled(true);
      var1.own(CosmeticCatalog.install(this, this.createPreview()));
      var1.onHudLayout(var0 -> HudRegistry.layout());
      var1.onHud(var1x -> {
         HudRegistry.renderLayer();
         var1.metric("drawn", 1.0);
      });
      return () -> {
         this.setEnabled(false);

         try {
            this.closeContent();
         } finally {
            HudRegistry.invalidate();
         }
      };
   }

   protected void opened(ModuleContext var1) throws Exception {
   }

   protected CosmeticCatalog.Listener createPreview() {
      return null;
   }

   protected void closeContent() throws Exception {
   }

   protected final HudModule section(String var1) {
      super.section(var1);
      return this;
   }

   final String editId() {
      return this.settingsModule().id();
   }

   final HudSlot.SlotRect editBounds() {
      return this.arranged() && this.presence.value() > 0.01F
         ? new HudSlot.SlotRect(this.renderX.value(), this.renderY.value(), this.renderWidth(), this.renderHeight())
         : null;
   }

   final void editPosition(float var1, float var2) {
      this.moveTo(var1, var2, RenderPipelines.get().hudCanvas().width(), RenderPipelines.get().hudCanvas().height());
      this.placed.set(true);
      this.renderX.snap(this.x);
      this.renderY.snap(this.y);
   }
}
