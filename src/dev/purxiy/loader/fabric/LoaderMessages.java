package dev.purxiy.loader.fabric;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.purxiy.hud.HudLayout;
import dev.purxiy.loader.auth.AccessDeniedException;
import dev.purxiy.loader.auth.IntegritySession;
import dev.purxiy.loader.auth.ServerAccount;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import net.minecraft.class_128;
import net.minecraft.class_156;
import net.minecraft.class_310;
import org.slf4j.LoggerFactory;

final class LoaderMessages {
   static String status = "Deze build is nog niet beschikbaar.";
   static ServerAccount account;
   static IntegritySession admission;
   static boolean authorized;
   static JsonArray catalog = new JsonArray();
   private static final Map<String, CompletableFuture<Void>> requests = new HashMap<>();
   private static final ArrayDeque<JsonObject> startupQueue = new ArrayDeque<>();
   private static final Set<String> queuedStartup = new HashSet<>();
   private static String code;
   private static long nextPoll = 0L;
   private static long expires = 0L;
   private static boolean busy;
   private static AccessDeniedException pendingDenial;
   private static AccessDeniedException.Reason rejectedReason;
   private static boolean startupLoaded;

   static boolean menuAllowed() {
      return pendingDenial == null && rejectedReason == null;
   }

   static AccessDeniedException.Reason rejectionReason() {
      return rejectedReason;
   }

   static boolean requested(String var0) {
      return queuedStartup.contains(var0) || ModuleState.active != null && ModuleState.active.modules.isRequested(var0);
   }

   static void denied(AccessDeniedException var0) {
      if (var0.terminal()) {
         SecureRuntimeRestart.stopForAuthorization(var0);
      }

      if (rejectedReason == null && pendingDenial == null) {
         if (var0.terminal()) {
            authorized = false;
            pendingDenial = var0;
         } else if (var0.reason() == AccessDeniedException.Reason.LINK_REQUIRED) {
            authorized = false;
            status = "Koppel je account op de website om de client te gebruiken.";
         } else {
            status = "Toegang geweigerd. Controleer je account of probeer opnieuw.";
         }
      }
   }

   private static AccessDeniedException accessFailure(Throwable var0) {
      while ((var0 instanceof CompletionException || var0 instanceof ExecutionException) && var0.getCause() != null) {
         var0 = var0.getCause();
      }

      AccessDeniedException var1;
      if (var0 instanceof AccessDeniedException var2) {
         var1 = var2;
      } else {
         var1 = null;
      }

      return var1;
   }

   static void authorize() {
      OfflineBootstrap.authorize();
   }

   static void link() {
      OfflineBootstrap.authorize();
   }

   static void tick() {
      if (pendingDenial != null && rejectedReason == null) {
         AccessDeniedException var0 = pendingDenial;
         pendingDenial = null;
         rejectedReason = var0.reason();
         authorized = false;
         code = null;
         requests.clear();
         startupQueue.clear();
         queuedStartup.clear();
         if (ModuleState.active != null) {
            try {
               ModuleState.active.close();
            } catch (Exception var3) {
            }
         }

         if (account != null) {
            account.close();
         }
         status = switch (var0.reason()) {
            case LICENCE_REQUIRED -> "Geen geldige licentie. Open je account op purxiy.com.";
            case DEVICE_CHANGED -> "Dit apparaat is niet meer gekoppeld. Koppel het opnieuw op de website.";
            case RELEASE_REJECTED -> "Deze clientversie is niet goedgekeurd. Download de actuele loader.";
            case SECURITY_HOLD -> "Toegang tijdelijk geblokkeerd. Neem contact op via de website.";
            default -> "Clientcontrole geweigerd. Herstel de originele loader en herstart.";
         };
         class_128 var2 = class_128.method_560(new LoaderStartupFailure(rejectedReason), "SourceByDevFFnightmare DeObfDeCompByGuideX loader authorization");
         class_310.method_1551().method_1494(var2);
         LoggerFactory.getLogger("DeObfDeCompByGuideX/Loader").error("SOURCEBYDEVFFNIGHTMARE DEOBFDECOMPBYGUIDEX STARTUP ABORTED: {}; module host detached; fatal crash requested", rejectedReason);
      }

      if (rejectedReason == null) {
         if (authorized && class_310.method_1551().method_18506() == null && ModuleState.active != null && requests.size() < 4 && !startupQueue.isEmpty()) {
            JsonObject var4 = startupQueue.removeFirst();
            String var6 = var4.get("id").getAsString();
            queuedStartup.remove(var6);
            if (!ModuleState.active.modules.isRequested(var6)) {
               enable(var4);
            }
         }

         if (code != null && !busy && System.currentTimeMillis() >= nextPoll) {
            if (System.currentTimeMillis() >= expires) {
               code = null;
               status = "De koppelcode is verlopen.";
            } else {
               String var5 = code;
               busy = true;
               nextPoll = System.currentTimeMillis() + 3000L;
               CompletableFuture.<JsonObject>supplyAsync(() -> {
                  try {
                     return account.call("GET", "/api/client/link/" + var5, (JsonObject)null);
                  } catch (Exception var2x) {
                     throw new CompletionException(var2x);
                  }
               }, account.worker).whenComplete((var1, var2x) -> class_310.method_1551().execute(() -> {
                  busy = false;
                  if (menuAllowed() && var5.equals(code)) {
                     if (var2x != null) {
                        status = "Verbinding onderbroken. We proberen het opnieuw.";
                     } else if ("linked".equals(var1.get("state").getAsString())) {
                        code = null;
                        authorize();
                     } else if ("cancelled".equals(var1.get("state").getAsString())) {
                        code = null;
                        status = "Accountkoppeling geannuleerd.";
                     }
                  }
               }));
            }
         }
      }
   }

   static void moduleFailed(String var0) {
      status = "Module " + var0 + " is uitgezet na een fout.";

      try {
         CosmeticRegistry.requestEnabled(var0, false);
      } catch (RuntimeException | LinkageError var2) {
      }
   }

   static void toggle(JsonObject var0) {
      if (ModuleState.active != null && menuAllowed()) {
         if (!authorized) {
            authorize();
         } else if (ModuleCatalog.toggleable(var0)) {
            String var1 = var0.get("id").getAsString();
            if (queuedStartup.remove(var1)) {
               startupQueue.removeIf(var1x -> var1x.get("id").getAsString().equals(var1));
               status = "Module uitgezet.";
               remember(var0, false);
            } else if (ModuleState.active.modules.isRequested(var1)) {
               requests.remove(var1);
               ModuleState.active.modules.disable(var1);
               status = "Module uitgezet.";
               remember(var0, false);
               notify(var0, false);
            } else {
               enable(var0);
               remember(var0, true);
            }
         }
      }
   }

   static void reconfigure(JsonObject var0) {
      ModuleState var1 = ModuleState.active;
      if (authorized && menuAllowed() && var1 != null && var1.modules.isRequested(var0.get("id").getAsString())) {
         enable(var0);
      }
   }

   static boolean canLoad() {
      return requests.size() < 4;
   }

   private static void enable(JsonObject var0) {
      String var1 = var0.get("id").getAsString();
      Map var2 = SettingSchema.read(var0);
      status = "Module laden…";
      CompletableFuture var3 = ModuleState.active.modules.enable(var1, var0.get("build").getAsString(), var2);
      requests.put(var1, var3);
      var3.whenComplete((var3x, var4) -> class_310.method_1551().execute(() -> {
         if (requests.remove(var1, var3)) {
            if (var4 != null) {
               AccessDeniedException var4x = accessFailure((Throwable)(var4));
               if (var4x != null) {
                  denied(var4x);
               } else {
                  status = "Laden is niet gelukt. Controleer je account en verbinding.";
               }
            } else {
               status = "Module ingeschakeld.";
               notify(var0, true);
            }
         }
      }));
   }

   private static void notify(JsonObject var0, boolean var1) {
      if (ModuleState.active != null && ModuleState.active.runtime.isActive("notifications")) {
         HudLayout.push(LinkAccountScreen.name(var0), var1 ? "Enabled" : "Disabled", "bell", var1);
      }
   }

   private static void remember(JsonObject var0, boolean var1) {
      try {
         ModuleCatalog.enabled(var0, var1);
      } catch (IOException var3) {
         status = "Module gewijzigd, maar je keuze kon niet worden opgeslagen.";
      }
   }

   private static void lambda$link$6(JsonObject var0, Throwable var1) {
      class_310.method_1551().execute(() -> {
         busy = false;
         if (menuAllowed()) {
            if (var1 != null) {
               status = "Koppelen is niet gelukt. Probeer het opnieuw.";
            } else {
               code = var0.get("code").getAsString();
               expires = var0.get("expiresAt").getAsLong();
               nextPoll = 0L;
               if (!code.matches("[A-Z2-9]{4}-[A-Z2-9]{4}")) {
                  code = null;
                  status = "Koppelen is niet gelukt.";
               } else {
                  status = "Bevestig code " + code + " op de website.";
                  class_156.method_668().method_670(account.origin.resolve("/link/?code=" + code).toString());
               }
            }
         }
      });
   }

   private static JsonObject lambda$link$4() {
      try {
         return account.call("POST", "/api/client/link", account.machine());
      } catch (Exception var1) {
         throw new CompletionException(var1);
      }
   }

   private static void lambda$authorize$3(Void var0, Throwable var1) {
      class_310.method_1551().execute(() -> {
         busy = false;
         if (menuAllowed()) {
            if (var1 != null) {
               AccessDeniedException var1x = accessFailure(var1);
               if (var1x != null) {
                  denied(var1x);
               } else {
                  status = "Licentiecontrole niet bereikbaar. Probeer opnieuw; modules blijven gesloten.";
               }
            } else {
               authorized = true;
               status = "Licentie geldig. Je kunt je modules gebruiken.";
               if (!startupLoaded) {
                  startupLoaded = true;
                  ArrayList var6 = new ArrayList(catalog.asList());
                  List var2 = List.of("sound", "interface", "motion", "keybinds", "profile", "main_menu");
                  var6.sort(Comparator.comparingInt(var1xx -> {
                     int var2x = var2.indexOf(((com.google.gson.JsonElement)(Object)var1xx).getAsJsonObject().get("id").getAsString());
                     return var2x < 0 ? var2.size() : var2x;
                  }));

                  for (JsonElement var4 : (Iterable<JsonElement>)(Object)(var6)) {
                     JsonObject var5 = var4.getAsJsonObject();
                     if (ModuleCatalog.enabled(var5) && queuedStartup.add(var5.get("id").getAsString())) {
                        startupQueue.addLast(var5);
                     }
                  }
               }
            }
         }
      });
   }

   private static void lambda$authorize$0() {
      try {
         admission.session();
      } catch (Exception var1) {
         throw new CompletionException(var1);
      }
   }
}
