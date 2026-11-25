package dev.purxiy.modules.spotify;

import java.io.StringReader;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

final class SpotifyError {
   private static final String[] b = new String[17];
   private static final long[] c = new long[]{2231936878033567458L, -982151706458462970L, 1885077571587005402L};
   private static final long[] e = new long[]{5761632728350384365L, 4908007781370209031L, 2055748078469614633L, 7281388500271439644L};
   private static final Long[] f = new Long[4];

   private SpotifyError() {
   }

   static SpotifyLyrics parse(String var0) {
      long var1 = 37125545895071L;
      long var3 = var1 ^ 9152830631433L;
      long var5 = var1 ^ 81944403030411L;
      long var7 = var1 ^ 111283122672294L;
      if (var0.isBlank()) {
         return SpotifyLyrics.f;
      } else {
         try {
            DocumentBuilderFactory var9 = DocumentBuilderFactory.newInstance();
            var9.setNamespaceAware(true);
            var9.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
            var9.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            var9.setFeature("http://xml.org/sax/features/external-general-entities", false);
            var9.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            var9.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "");
            var9.setAttribute("http://javax.xml.XMLConstants/property/accessExternalSchema", "");
            var9.setXIncludeAware(false);
            var9.setExpandEntityReferences(false);
            DocumentBuilder var10 = var9.newDocumentBuilder();
            var10.setErrorHandler(new DefaultHandler() {
               @Override
               public void fatalError(SAXParseException var1) throws SAXParseException {
                  throw var1;
               }
            });
            Document var11 = var10.parse(new InputSource(new StringReader(var0)));
            NodeList var12 = var11.getElementsByTagNameNS("*", "p");
            StringBuilder var13 = new StringBuilder();

            for (int var14 = 0; var14 < var12.getLength(); var14++) {
               Element var15 = (Element)var12.item(var14);
               long var16 = time(var15.getAttribute("begin"));
               if (var16 >= 0L && !c(var15, var5)) {
                  var13.append(W(var16, var7, '[', ']')).append(W(var16, var7, '<', '>'));
                  T(var15, var3, var13, var16);
                  var13.append('\n');
               }
            }

            return SpotifyLyricParser.parse(var13.toString());
         } catch (Exception var181) {
            throw new IllegalArgumentException("Invalid TTML lyrics");
         }
      }
   }

   private static boolean c(Element var0, long var1) {
      SpotifyCurve.ensureSourceClassInitialized();
      int var3 = 0;

      while (var3 < var0.getAttributes().getLength()) {
         Node var4 = var0.getAttributes().item(var3);
         if (var1 > 1L) {
            if ("role".equals(var4.getLocalName())) {
               String var5 = var4.getNodeValue();
               return var5.equals("x-translation") || var5.equals("x-roman") || var5.equals("x-bg");
            }

            var3++;
         }
      }

      return false;
   }

   private static void T(Node var0, long var1, StringBuilder var3, long var4) {
      long var6 = var1 ^ 0L;
      long var8 = var1 ^ 73478908632962L;
      long var10 = var1 ^ 120280857509039L;
      SpotifyCurve.ensureSourceClassInitialized();
      Node var12 = var0.getFirstChild();
      byte var13 = 0;

      while (true) {
         if (var12 == null) {
            if (var1 > 1L) {
               return;
            }
         } else {
            label54:
            if (var12 instanceof Element var14) {
               Element var19 = var14;
               if (var1 > 1L) {
                  label49: {
                     byte var17 = (byte)(c(var14, var8) ? 1 : 0);
                     if (var1 > 0L) {
                        if (var17 == 0) {
                           break label49;
                        }

                        var17 = var13;
                     }

                     if (var17 == 0) {
                        break label54;
                     }
                  }

                  var19 = var14;
                  int var20 = -103937540;
               }

               long var21 = time(var19.getAttribute("begin"));
               if (var21 >= var4) {
                  var3.append(W(var21, var10, '<', '>'));
               }

               if ("br".equals(var14.getLocalName())) {
                  var3.append(' ');
                  if (var1 < 0L) {
                  }
               } else {
                  T(var14, var6, var3, var4);
               }
            } else {
               label71: {
                  label60: {
                     short var15 = var12.getNodeType();
                     byte var16 = var13;
                     if (var1 >= 1L) {
                        if (var15 == 3) {
                           break label60;
                        }

                        var15 = var12.getNodeType();
                        var16 = 4;
                     }

                     if (var15 != var16) {
                        break label71;
                     }
                  }

                  var3.append(var12.getNodeValue().replaceAll("\\s+", " "));
               }
            }
         }

         var12 = var12.getNextSibling();
      }
   }

   static long time(String var0) {
      try {
         String var1 = var0.strip();
         double var2;
         if (var1.endsWith("ms")) {
            var2 = Double.parseDouble(var1.substring(0, var1.length() - 2)) / 1000.0;
         } else {
            if (var1.endsWith("s")) {
               var1 = var1.substring(0, var1.length() - 1);
            }

            String[] var4 = var1.split(":", -1);
            if (var4.length > 3) {
               return -1L;
            }

            var2 = 0.0;

            for (String var8 : var4) {
               double var9 = Double.parseDouble(var8);
               if (var9 < 0.0) {
                  return -1L;
               }

               var2 = var2 * 60.0 + var9;
            }
         }

         return Double.isFinite(var2) && var2 >= 0.0 && var2 <= 86400.0 ? Math.round(var2 * 1000.0) : -1L;
      } catch (NumberFormatException var111) {
         return -1L;
      }
   }

   private static String W(long var0, long var2, char var4, char var5) {
      return String.format(
         Locale.ROOT,
         "¦D",
         var4,
         var0 / b(18736, 5761558050277211316L ^ var2),
         var0 / b(19582, 2055681065570875896L ^ var2) % b(23710, 7281463763802106137L ^ var2),
         var0 % b(25427, 4908074664077170390L ^ var2),
         var5
      );
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 17341) & 32767;
      if (f[var3] == null) {
         f[var3] = e[var3] ^ var1;
      }

      return f[var3];
   }
}
