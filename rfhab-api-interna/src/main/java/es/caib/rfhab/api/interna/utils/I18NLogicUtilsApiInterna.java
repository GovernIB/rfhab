package es.caib.rfhab.api.interna.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;

/**
 * 
 * @author jpou
 * 
 */
public class I18NLogicUtilsApiInterna extends I18NCommonUtils {

  static {
    BUNDLES = new String[] { "api_interna", "logicmissatges", "rfhab_genapp", "genapp" };
    // BUNDLES = new String[] { "api_interna", "logicmissatges", "rfhab_genapp", "genapp", "ValidationMessages", "persistence.LabelsPersistence", "ejb.LabelsEJB" };
  }

  // public static String tradueix(boolean useCodeIfNotExist, Locale loc, String code, String... args) {
  //   return tradueix(useCodeIfNotExist ? code : null, loc, code, args);
  // }

  // public static String tradueix(Locale loc, String code, String... args) {
  //   return tradueix((String) null, loc, code, args);
  // }

  // public static String tradueix(String valueIfNotExist, Locale loc, String code, String... args) {
  //   String msg = null;
  //   Exception lastException = null;
  //   String[] var6 = I18NLogicUtilsApiInterna.BUNDLES;
  //   int var7 = var6.length;
  //   int var8 = 0;

  //   while (var8 < var7) {
  //     String res = var6[var8];
  //     String key = res + "_" + loc.toString();
  //     ResourceBundle resource = (ResourceBundle) bundles.get(key);
  //     if (resource == null) {
  //       resource = ResourceBundle.getBundle(res, loc, UTF8CONTROL);
  //       bundles.put(key, resource);
  //     }

  //     try {
  //       msg = resource.getString(code);
  //       break;
  //     } catch (Exception var13) {
  //       lastException = var13;
  //       ++var8;
  //     }
  //   }

  //   if (msg == null) {
  //     if (valueIfNotExist == null) {
  //       String lang = loc.toString().toUpperCase();
  //       if (lastException == null) {
  //         lastException = new Exception();
  //       }

  //       log.error(
  //           "La clau de traducció [" + code + "] per l'idioma " + lang + " no existeix: " + lastException.getMessage(),
  //           lastException);
  //       return "{" + lang + "_" + code + "}";
  //     } else {
  //       return valueIfNotExist;
  //     }
  //   } else {
  //     if (args != null && args.length != 0) {
  //       msg = MessageFormat.format(msg, (Object[]) args);
  //     }

  //     return msg;
  //   }
  // }

}
