package es.caib.rfhab.api.interna.utils;

import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;

/**
 * 
 * @author jpou
 * 
 */
public class I18NLogicUtilsApiInterna extends I18NCommonUtils {

  static {
    BUNDLES = new String[] { "api_interna", "logicmissatges", "rfhab_genapp", "genapp", "ValidationMessages",
        "persistence.LabelsPersistence", "ejb.LabelsEJB" };
  }

}
