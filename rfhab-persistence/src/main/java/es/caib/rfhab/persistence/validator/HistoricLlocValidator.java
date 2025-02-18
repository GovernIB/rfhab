package es.caib.rfhab.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.rfhab.model.entity.HistoricLloc;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.rfhab.model.fields.HistoricLlocFields;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.model.fields.UsuariFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class HistoricLlocValidator<I extends HistoricLloc>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements HistoricLlocFields {

    protected final Logger log = Logger.getLogger(getClass());


  public HistoricLlocValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.rfhab.model.dao.IHistoricLlocManager __historicLlocManager
    ,es.caib.rfhab.model.dao.ILlocManager __llocManager
    ,es.caib.rfhab.model.dao.IUsuariManager __usuariManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,LLOCID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LLOCID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NUMEROCAI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NUMEROCAI)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATACREACIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATACREACIO)));

    // Check size
    if (__vr.getFieldErrorCount(NUMEROCAI) == 0) {
      java.lang.String __numerocai = __target__.getNumeroCai();
      if (__numerocai!= null && __numerocai.length() > 50) {
        __vr.rejectValue(NUMEROCAI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NUMEROCAI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
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
    if (__vr.getFieldErrorCount(LLOCID) == 0) {
      java.lang.Long __llocid = __target__.getLlocID();
      Long __count_ = null;
      try { __count_ = __llocManager.count(LlocFields.LLOCID.equal(__llocid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(LLOCID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("lloc.lloc"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("lloc.llocID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__llocid)));
      }
    }

    if (__vr.getFieldErrorCount(USUARIID) == 0) {
      java.lang.Long __usuariid = __target__.getUsuariID();
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