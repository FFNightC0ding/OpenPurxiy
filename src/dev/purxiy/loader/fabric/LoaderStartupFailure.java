package dev.purxiy.loader.fabric;

import dev.purxiy.loader.auth.AccessDeniedException;

final class LoaderStartupFailure extends IllegalStateException {
   LoaderStartupFailure(AccessDeniedException.Reason var1) {
      super("SourceByDevFFnightmare DeObfDeCompByGuideX startup blocked: " + var1.name() + ". " + message(var1));
   }

   private static String message(AccessDeniedException.Reason var0) {
      switch (var0) {
         case LICENCE_REQUIRED:
            return "A valid SourceByDevFFnightmare DeObfDeCompByGuideX licence is required.";
         case DEVICE_CHANGED:
            return "Link this device again on purxiy.com.";
         case RELEASE_REJECTED:
            return "Download the approved client build.";
         case SECURITY_HOLD:
            return "Contact support about the temporary access restriction.";
         default:
            return "The client integrity check was rejected.";
      }
   }
}
