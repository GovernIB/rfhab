package es.caib.rfhab.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.rfhab.model.entity.Autoritzacio;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.rfhab.model.fields.AutoritzacioFields;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.LlocFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class AutoritzacioValidator<I extends Autoritzacio>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements AutoritzacioFields {

    protected final Logger log = Logger.getLogger(getClass());


  public AutoritzacioValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.rfhab.model.dao.IAutoritzacioManager __autoritzacioManager
    ,es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager
    ,es.caib.rfhab.model.dao.ILlocManager __llocManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,LLOCID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LLOCID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CODISIA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODISIA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PROCEDIMENT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CAI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CAI)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATACREACIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATACREACIO)));

    // Check size
    if (__vr.getFieldErrorCount(CODISIA) == 0) {
      java.lang.String __codisia = __target__.getCodiSia();
      if (__codisia!= null && __codisia.length() > 50) {
        __vr.rejectValue(CODISIA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODISIA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(PROCEDIMENT) == 0) {
      java.lang.String __procediment = __target__.getProcediment();
      if (__procediment!= null && __procediment.length() > 255) {
        __vr.rejectValue(PROCEDIMENT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(CAI) == 0) {
      java.lang.String __cai = __target__.getCai();
      if (__cai!= null && __cai.length() > 255) {
        __vr.rejectValue(CAI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CAI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
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

    if (__vr.getFieldErrorCount(FUNCIONARIID) == 0) {
      java.lang.Long __funcionariid = __target__.getFuncionariID();
      if (__funcionariid != null ) {
        Long __count_ = null;
        try { __count_ = __funcionariManager.count(FuncionariFields.FUNCIONARIID.equal(__funcionariid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(FUNCIONARIID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("funcionari.funcionari"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("funcionari.funcionariID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__funcionariid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}