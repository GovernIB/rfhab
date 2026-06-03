package es.caib.rfhab.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.rfhab.model.entity.SincroUnitats;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.rfhab.model.fields.SincroUnitatsFields;
import es.caib.rfhab.model.fields.UsuariFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class SincroUnitatsValidator<I extends SincroUnitats>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements SincroUnitatsFields {

    protected final Logger log = Logger.getLogger(getClass());


  public SincroUnitatsValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.rfhab.model.dao.ISincroUnitatsManager __sincroUnitatsManager
    ,es.caib.rfhab.model.dao.IUsuariManager __usuariManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,DATACREACIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATACREACIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATADARRERASINCRO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATADARRERASINCRO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATAPRIMERASINCRO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATAPRIMERASINCRO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CODIENTITAT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODIENTITAT)));

    // Check size
    if (__vr.getFieldErrorCount(CODIENTITAT) == 0) {
      java.lang.String __codientitat = __target__.getCodiEntitat();
      if (__codientitat!= null && __codientitat.length() > 50) {
        __vr.rejectValue(CODIENTITAT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODIENTITAT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(OBSERVACIONS) == 0) {
      java.lang.String __observacions = __target__.getObservacions();
      if (__observacions!= null && __observacions.length() > 2147483647) {
        __vr.rejectValue(OBSERVACIONS, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(OBSERVACIONS)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
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
    if (__vr.getFieldErrorCount(USUARIID) == 0) {
      java.lang.Long __usuariid = __target__.getUsuariId();
      if (__usuariid != null ) {
        Long __count_ = null;
        try { __count_ = __usuariManager.count(UsuariFields.USUARIID.equal(__usuariid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(USUARIID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuari.usuari"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuari.usuariID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}