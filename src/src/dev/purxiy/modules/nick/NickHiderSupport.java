// Sourced By DevFFNightmare
package dev.purxiy.modules.nick;

import java.lang.Character.UnicodeScript;
import java.util.Arrays;

final class NickHiderSupport {
   static final int K = 0;
   static final int n = 1;
   static final int d = 2;
   private static final String y = "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢ";
   private static final String o = "ℂcℊgℋhℌhℍhℎhℐiℑiℒlℓlℕnℙpℚqℛrℜrℝrℤzℨzℬbℭcℯeℰeℱfℳmℴoℹiᵃaᵇbᶜcᵈdᵉeᶠfᵍgʰhⁱiʲjᵏkˡlᵐmⁿnᵒoᵖpʳrˢsᵗtᵘuᵛvʷwˣxʸyᶻz⁰0¹1²2³3⁴4⁵5⁶6⁷7⁸8⁹9₀0₁1₂2₃3₄4₅5₆6₇7₈8₉9";
   private static final int[] w = new int[96];
   private static final char[] I = new char[96];



   private NickHiderSupport() {
   }

   static int fold(int var0) {
      if (var0 >= 128) {
         if (var0 >= 65281 && var0 <= 65374) {
            return fold(var0 - 65248);
         } else if (var0 >= 119808 && var0 <= 120483) {
            return 97 + (var0 - 119808) % 52 % 26;
         } else if (var0 >= 120782 && var0 <= 120831) {
            return 48 + (var0 - 120782) % 10;
         } else if (var0 >= 9398 && var0 <= 9423) {
            return 97 + var0 - 9398;
         } else if (var0 >= 9424 && var0 <= 9449) {
            return 97 + var0 - 9424;
         } else if (var0 >= 9312 && var0 <= 9320) {
            return 49 + var0 - 9312;
         } else if (var0 == 9450) {
            return 48;
         } else {
            int var1 = Arrays.binarySearch(w, var0);
            return var1 >= 0 ? I[var1] : Character.toLowerCase(var0);
         }
      } else {
         return var0 >= 65 && var0 <= 90 ? var0 + 32 : var0;
      }
   }

   static boolean isWord(int var0, int var1) {
      return var1 < 128
         ? var1 >= 97 && var1 <= 122 || var1 >= 48 && var1 <= 57 || var1 == 95
         : Character.isLetterOrDigit(var0) && UnicodeScript.of(var0) == UnicodeScript.LATIN;
   }

   static boolean ignorable(int var0) {
      return var0 >= 128 && Character.getType(var0) == 16;
   }

   static int variant(int var0) {
      if (var0 >= 65281 && var0 <= 65374) {
         return 2;
      } else {
         return var0 >= 128 && "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢ".indexOf(var0) >= 0 ? 1 : 0;
      }
   }

   static int restyle(int var0, int var1) {
      if (var1 != 1) {
         return var1 == 2 && var0 > 32 && var0 < 127 ? var0 + 65248 : var0;
      } else {
         int var2 = var0 >= 65 && var0 <= 90 ? var0 + 32 : var0;
         return var2 >= 97 && var2 <= 122 ? "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢ".charAt(var2 - 97) : var0;
      }
   }

   static int plain(int var0) {
      return var0 >= 65281 && var0 <= 65374 ? var0 - 65248 : var0;
   }

}
