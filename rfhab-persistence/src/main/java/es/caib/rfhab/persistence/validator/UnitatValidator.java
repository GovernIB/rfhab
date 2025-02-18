package es.caib.rfhab.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.rfhab.model.entity.Unitat;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.rfhab.model.fields.UnitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class UnitatValidator<I extends Unitat>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements UnitatFields {

    protected final Logger log = Logger.getLogger(getClass());


  public UnitatValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.rfhab.model.dao.IUnitatManager __unitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,CODI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)));

    __vr.rejectIfEmptyOrWhitespace(__target__,VERSIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(VERSIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DENOMINACIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DENOMINACIO)));

    // Check size
    if (__vr.getFieldErrorCount(CODI) == 0) {
      java.lang.String __codi = __target__.getCodi();
      if (__codi!= null && __codi.length() > 50) {
        __vr.rejectValue(CODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(DENOMINACIO) == 0) {
      java.lang.String __denominacio = __target__.getDenominacio();
      if (__denominacio!= null && __denominacio.length() > 255) {
        __vr.rejectValue(DENOMINACIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DENOMINACIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(COOFICIAL) == 0) {
      java.lang.String __cooficial = __target__.getCooficial();
      if (__cooficial!= null && __cooficial.length() > 255) {
        __vr.rejectValue(COOFICIAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(COOFICIAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ARREL) == 0) {
      java.lang.String __arrel = __target__.getArrel();
      if (__arrel!= null && __arrel.length() > 50) {
        __vr.rejectValue(ARREL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARREL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(SUPERIOR) == 0) {
      java.lang.String __superior = __target__.getSuperior();
      if (__superior!= null && __superior.length() > 50) {
        __vr.rejectValue(SUPERIOR, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SUPERIOR)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(ESTAT) == 0) {
      java.lang.String __estat = __target__.getEstat();
      if (__estat!= null && __estat.length() > 5) {
        __vr.rejectValue(ESTAT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ESTAT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(5)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}