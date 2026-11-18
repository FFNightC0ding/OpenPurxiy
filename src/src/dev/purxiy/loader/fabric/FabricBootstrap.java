// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import dev.purxiy.hud.HudElement;
import dev.purxiy.loader.auth.AccessDeniedException;
import dev.purxiy.loader.fabric.client.ClientRenderer4;
import dev.purxiy.loader.fabric.client.ClientRenderer5;
import dev.purxiy.loader.fabric.client.ClientRenderer9;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import dev.purxiy.ui.clickgui.MenuScreen;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents.Load;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents.ClientStopping;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.EndTick;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents.Disconnect;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents.BeforeEntities;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents.BeforeTranslucent;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents.AfterInit;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents.AfterKeyPress;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents.AllowKeyPress;
import net.minecraft.class_2960;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_433;
import net.minecraft.class_442;
import net.minecraft.class_304.class_11900;
import net.minecraft.class_3675.class_307;
import org.lwjgl.glfw.GLFW;
import org.slf4j.LoggerFactory;

public final class FabricBootstrap implements ClientModInitializer {
   public void onInitializeClient() {
      ScreenEvents.AFTER_INIT.register((AfterInit)(var0, var1x, var2x, var3) -> {
         if (var1x instanceof class_442 || var1x instanceof class_433) {
            ScreenKeyboardEvents.afterKeyPress(var1x).register((AfterKeyPress)(var1xx, var2xx) -> {
               if (MenuKeyBinding.menuMatches(var2xx) && LoaderMessages.authorized && LoaderMessages.menuAllowed() && var0.method_18506() == null) {
                  var0.method_1507(new MenuScreen(var1xx));
               }
            });
            ScreenKeyboardEvents.allowKeyPress(var1x).register((AllowKeyPress)(var0x, var1xx) -> ClientRenderer4.allowKey(var1xx.comp_4795()));
         }
      });
      ClientEntityEvents.ENTITY_LOAD.register((Load)(var0, var1x) -> ClientSupport3.entityLoaded(var0));
      WorldRenderEvents.BEFORE_ENTITIES.register((BeforeEntities)var0 -> ClientSupport3.keepDepth(false));
      WorldRenderEvents.BEFORE_TRANSLUCENT.register((BeforeTranslucent)var0 -> {
         ClientSupport3.keepDepth(true);
         ClientRenderer9.world(var0);
         ClientRenderer5.render(var0);
      });
      class_11900 var1 = class_11900.method_74698(class_2960.method_60655("purxiy_loader", "main"));
      GarageActions.init();
      class_304 var2 = KeyBindingHelper.registerKeyBinding(new class_304("key.purxiy_loader.menu", class_307.field_1668, 344, var1));
      MenuKeyBinding.menu(var2);
      ClientTickEvents.END_CLIENT_TICK.register((EndTick)var1x -> {
         while (var2.method_1436()) {
            if (LoaderMessages.authorized && LoaderMessages.menuAllowed() && var1x.method_18506() == null) {
               var1x.method_1507(CosmeticRegistry.menu());
            }
         }

         LoaderMessages.tick();
         CosmeticRegistry.tickChanges();
         if (var1x.field_1755 instanceof MenuScreen && GLFW.glfwGetMouseButton(var1x.method_22683().method_4490(), 0) != 1) {
            MenuCommands.flush();
         }

         LocalCommands.tick();
         RuntimeInfo.tick(var1x);
         HudElement.tick(var1x);
         ModuleWidget.tick(var1x);
      });
      HudElementRegistry.attachElementBefore(
         VanillaHudElements.CHAT, class_2960.method_60655("purxiy_loader", "modules"), (var0, var1x) -> ModuleState.hud(var0)
      );
      OwnerThread.install();
      ClientPlayConnectionEvents.DISCONNECT.register((Disconnect)(var0, var1x) -> ModuleWidget.disconnected(var1x));
      if (ModuleState.active == null) {
         OfflineBootstrap.init();
      }
      ClientLifecycleEvents.CLIENT_STOPPING.register((ClientStopping)var0 -> {
         if (ModuleState.active != null) {
            try {
               ModuleState.active.close();
            } catch (Exception var2x) {
               LoggerFactory.getLogger("PurxiyLoader").error("Module cleanup failed");
            }
         }

         if (LoaderMessages.account != null) {
            LoaderMessages.account.close();
         }
      });
   }

   private static PublicKey key(String var0) throws Exception {
      return KeyFactory.getInstance("Ed25519").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(var0)));
   }

   private static void lambda$onInitializeClient$12(AccessDeniedException var0) {
      if (var0.terminal()) {
         SecureRuntimeRestart.stopForAuthorization(var0);
      } else {
         class_310.method_1551().execute(() -> LoaderMessages.denied(var0));
      }
   }

   private static void lambda$onInitializeClient$10(AccessDeniedException var0) {
      if (var0.terminal()) {
         SecureRuntimeRestart.stopForAuthorization(var0);
      } else {
         class_310.method_1551().execute(() -> LoaderMessages.denied(var0));
      }
   }
}
