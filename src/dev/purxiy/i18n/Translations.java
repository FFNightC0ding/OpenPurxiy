// Sourced By DevFFNightmare
package dev.purxiy.i18n;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.font.TrueTypeFont;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Character.UnicodeBlock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_310;

public final class Translations {
   public static final String AUTO = "Auto";
   public static final String ENGLISH = "English";
   public static final String RUSSIAN = "Русский";
   private static final String DICTIONARY = "assets/purxiy/i18n/ru_ru.json";
   private static final String CYRILLIC = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя№";
   private static final Locale RU = Locale.forLanguageTag("ru");
   private static final Map<String, String> EXACT = new HashMap<>();
   private static final Map<String, String[]> FORMS = new HashMap<>();
   private static final List<Translations.TypeP> RULES = new ArrayList<>();
   private static final Map<String, String> BUILT = new LinkedHashMap<String, String>(256, 0.75F, true) {
      @Override
      protected boolean removeEldestEntry(Entry<String, String> var1) {
         return this.size() > 1024;
      }
   };
   private static Supplier<String> setting = () -> "Auto";
   
   private static String lastSetting;
   
   private static String lastGame;
   private static boolean noGame;
   private static volatile Translations.TypeM current = Translations.TypeM.ENGLISH;
   private static int revision = 0;
   private static int paused = 0;
   private static boolean loaded;
   private static boolean preloaded;
   
   private static final Path MISSING = path("purxiy.i18n.missing");
   private static final Set<String> missing = ConcurrentHashMap.newKeySet();
   private static int missingWritten = 0;
   private static boolean dumped;

   private Translations() {
   }

   
   private static Path path(String var0) {
      String var1 = System.getProperty(var0);
      return var1 != null && !var1.isBlank() ? Path.of(var1) : null;
   }

   public static void bind(Supplier<String> var0) {
      setting = var0;
   }

   public static Translations.TypeM current() {
      update();
      return current;
   }

   public static boolean russian() {
      return current() == Translations.TypeM.RUSSIAN;
   }

   public static Locale locale() {
      return russian() ? RU : Locale.ENGLISH;
   }

   public static int revision() {
      update();
      return revision;
   }

   public static void frame() {
      paused = 0;
   }

   public static void pause() {
      paused++;
   }

   public static void resume() {
      if (paused > 0) {
         paused--;
      }
   }

   public static CharSequence display(CharSequence var0) {
      if (paused <= 0 && var0 instanceof String var1 && !var1.isEmpty()) {
         update();
         if (current == Translations.TypeM.ENGLISH) {
            return var0;
         } else {
            String var2 = EXACT.get(var1);
            return var2 != null ? var2 : built(var1);
         }
      } else {
         return var0;
      }
   }

   public static String shown(String var0) {
      return display(var0).toString();
   }

   public static String tr(String var0) {
      if (var0 != null && !var0.isEmpty() && current() != Translations.TypeM.ENGLISH) {
         String var1 = EXACT.get(var0);
         return var1 != null ? var1 : built(var0);
      } else {
         return var0;
      }
   }

   public static String plural(double var0, String var2, String var3) {
      if (current() == Translations.TypeM.RUSSIAN) {
         String[] var4 = FORMS.get(var2 + "|" + var3);
         if (var4 != null) {
            return russianForm(var0, var4);
         }
      }

      return var0 == 1.0 ? var2 : var3;
   }

   private static String built(String var0) {
      String var1 = BUILT.get(var0);
      if (var1 != null) {
         return var1;
      } else {
         String var2 = var0;
         if (hasLatin(var0)) {
            for (Translations.TypeP var4 : RULES) {
               Matcher var5 = var4.pattern.matcher(var0);
               if (var5.matches()) {
                  var2 = var4.apply(var5);
                  break;
               }
            }

            if (var2 == var0 && MISSING != null && !hasCyrillic(var0)) {
               missing.add(var0);
            }
         }

         BUILT.put(var0, var2);
         return var2;
      }
   }

   private static boolean hasLatin(String var0) {
      for (int var1 = 0; var1 < var0.length(); var1++) {
         char var2 = var0.charAt(var1);
         if (var2 >= 'A' && var2 <= 'Z' || var2 >= 'a' && var2 <= 'z') {
            return true;
         }
      }

      return false;
   }

   private static boolean hasCyrillic(String var0) {
      for (int var1 = 0; var1 < var0.length(); var1++) {
         if (UnicodeBlock.of(var0.charAt(var1)) == UnicodeBlock.CYRILLIC) {
            return true;
         }
      }

      return false;
   }

   static String russianForm(double var0, String[] var2) {
      if (var2.length < 3) {
         return var2[Math.min(var2.length - 1, var0 == 1.0 ? 0 : 1)];
      } else {
         double var3 = Math.abs(var0);
         if (var3 != Math.rint(var3)) {
            return var2[1];
         } else {
            long var5 = (long)var3;
            long var7 = var5 % 10L;
            long var9 = var5 % 100L;
            if (var7 == 1L && var9 != 11L) {
               return var2[0];
            } else {
               return var7 >= 2L && var7 <= 4L && (var9 < 12L || var9 > 14L) ? var2[1] : var2[2];
            }
         }
      }
   }

   private static void update() {
      String var0 = setting.get();
      String var1 = gameLanguage();
      if (var0 != lastSetting || var1 != lastGame) {
         lastSetting = var0;
         lastGame = var1;
         byte var4 = -1;
         switch (var0.hashCode()) {
            case -1185086888:
               if (var0.equals("Русский")) {
                  var4 = 1;
               }
               break;
            case 60895824:
               if (var0.equals("English")) {
                  var4 = 0;
               }
         }

         Translations.TypeM var2;
         label61: {
            switch (var4) {
               case 0:
                  var2 = Translations.TypeM.ENGLISH;
                  break label61;
               case 1:
                  var2 = Translations.TypeM.RUSSIAN;
                  break label61;
            }

            var2 = var1 != null && var1.startsWith("ru") ? Translations.TypeM.RUSSIAN : Translations.TypeM.ENGLISH;
         }

         if (var2 == Translations.TypeM.RUSSIAN) {
            load();
            preload();
         }

         if (var2 != current) {
            current = var2;
            revision++;
            synchronized (BUILT) {
               BUILT.clear();
            }
         }
      }
   }

   
   private static String gameLanguage() {
      if (noGame) {
         return null;
      } else {
         try {
            class_310 var0 = class_310.method_1551();
            return var0 != null && var0.field_1690 != null ? var0.field_1690.field_1883 : null;
         } catch (LinkageError var1) {
            noGame = true;
            return null;
         }
      }
   }

   private static void preload() {
      if (!preloaded && Fonts.REGULAR != null) {
         preloaded = true;

         for (TrueTypeFont var3 : new TrueTypeFont[]{Fonts.MEDIUM, Fonts.SEMIBOLD, Fonts.REGULAR, Fonts.BOLD}) {
            var3.preload("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя№");
         }
      }
   }

   private static void load() {
      if (!loaded) {
         loaded = true;

         try {
            label85: {
               try (InputStream var0 = Translations.class.getClassLoader().getResourceAsStream("assets/purxiy/i18n/ru_ru.json")) {
                  if (var0 != null) {
                     JsonObject var1 = JsonParser.parseReader(new InputStreamReader(var0, StandardCharsets.UTF_8)).getAsJsonObject();

                     for (Entry var3 : var1.entrySet()) {
                        String var4 = (String)var3.getKey();
                        String var5 = ((JsonElement)var3.getValue()).getAsString();
                        if (!var4.startsWith("//")) {
                           if (var4.contains("{")) {
                              RULES.add(Translations.TypeP.of(var4, var5));
                           } else if (var4.contains("|")) {
                              FORMS.put(var4, var5.split("\\|"));
                           } else {
                              EXACT.put(var4, var5);
                           }
                        }
                     }

                     RULES.sort((var0x, var1x) -> Integer.compare(var1x.literal, var0x.literal));
                     ClientServices.LOGGER
                        .info("Purxiy: loaded {} translations, {} patterns and {} word forms", new Object[]{EXACT.size(), RULES.size(), FORMS.size()});
                     break label85;
                  }

                  ClientServices.LOGGER.warn("Purxiy: no dictionary at {}", "assets/purxiy/i18n/ru_ru.json");
               }

               return;
            }
         } catch (Exception var8) {
            ClientServices.LOGGER.error("Purxiy: could not read {}", "assets/purxiy/i18n/ru_ru.json", var8);
         }

         if (MISSING != null) {
            Thread var9 = new Thread(Translations::writeMissingLoop, "Purxiy i18n missing");
            var9.setDaemon(true);
            var9.start();
         }
      }
   }

   private static void writeMissingLoop() {
      while (true) {
         try {
            Thread.sleep(3000L);
            dumpOnce();
            if (missing.size() != missingWritten && MISSING != null) {
               missingWritten = missing.size();
               Files.createDirectories(MISSING.toAbsolutePath().getParent());
               Files.writeString(MISSING, String.join("\n", new TreeSet<>(missing)) + "\n", StandardCharsets.UTF_8);
            }
         } catch (InterruptedException var1) {
            return;
         } catch (Exception var2) {
            ClientServices.LOGGER.warn("Purxiy: could not write missing translations", var2);
         }
      }
   }

   private static void dumpOnce() throws IOException {
   }

   public static enum TypeM {
      ENGLISH,
      RUSSIAN;

      private static Translations.TypeM[] $values() {
         return new Translations.TypeM[]{ENGLISH, RUSSIAN};
      }
   }

   private record TypeP(Pattern pattern, String[] names, String template, int literal) {
      private static final Pattern PLACEHOLDER = Pattern.compile("\\{([nsr][0-9]?)}");
      private static final Pattern OUT = Pattern.compile("\\{([nsr][0-9]?)(?::([^}]*))?}");

      static Translations.TypeP of(String var0, String var1) {
         StringBuilder var2 = new StringBuilder("^");
         ArrayList var3 = new ArrayList();
         Matcher var4 = PLACEHOLDER.matcher(var0);
         int var5 = 0;

         int var6;
         for (var6 = 0; var4.find(); var5 = var4.end()) {
            var2.append(Pattern.quote(var0.substring(var5, var4.start())));
            var6 += var4.start() - var5;
            String var7 = var4.group(1);
            var3.add(var7);
            var2.append(var7.charAt(0) == 'n' ? "([-+−]?\\d+(?:[.,]\\d+)?)" : "(.+?)");
         }

         var2.append(Pattern.quote(var0.substring(var5))).append('$');
         var6 += var0.length() - var5;
         return new Translations.TypeP(Pattern.compile(var2.toString()), (String[])(var3.toArray(String[]::new)), var1, var6);
      }

      String apply(Matcher var1) {
         HashMap var2 = new HashMap();

         for (int var3 = 0; var3 < this.names.length; var3++) {
            var2.put(this.names[var3], var1.group(var3 + 1));
         }

         Matcher var8 = OUT.matcher(this.template);
         StringBuilder var4 = new StringBuilder();

         while (var8.find()) {
            String var6 = var8.group(1);
            String var7 = (String)(var2.getOrDefault(var6, ""));
            String var5;
            if (var8.group(2) != null) {
               var5 = Translations.russianForm(number(var7), var8.group(2).split("\\|"));
            } else if (var6.charAt(0) == 's') {
               var5 = Translations.tr(var7);
            } else {
               var5 = var7;
            }

            var8.appendReplacement(var4, Matcher.quoteReplacement(var5));
         }

         var8.appendTail(var4);
         return var4.toString();
      }

      private static double number(String var0) {
         try {
            return Double.parseDouble(var0.replace(',', '.').replace('−', '-'));
         } catch (NumberFormatException var2) {
            return 0.0;
         }
      }
   }
}
