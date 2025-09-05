package es.caib.rfhab.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.rfhab.model.entity.Lloc;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.rfhab.model.fields.LlocFields;
import es.caib.rfhab.model.fields.EntitatFields;
import es.caib.rfhab.model.fields.UnitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class LlocValidator<I extends Lloc>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements LlocFields {

    protected final Logger log = Logger.getLogger(getClass());


  public LlocValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.rfhab.model.dao.IEntitatManager __entitatManager
    ,es.caib.rfhab.model.dao.ILlocManager __llocManager
    ,es.caib.rfhab.model.dao.IUnitatManager __unitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,CODILLOC, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODILLOC)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CODILLOCPROPI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODILLOCPROPI)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,UNITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(UNITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PERSONALOAMR, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PERSONALOAMR)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATACREACIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATACREACIO)));

    // Check size
    if (__vr.getFieldErrorCount(CODILLOC) == 0) {
      java.lang.String __codilloc = __target__.getCodiLloc();
      if (__codilloc!= null && __codilloc.length() > 50) {
        __vr.rejectValue(CODILLOC, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODILLOC)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(CODILLOCPROPI) == 0) {
      java.lang.String __codillocpropi = __target__.getCodiLlocPropi();
      if (__codillocpropi!= null && __codillocpropi.length() > 50) {
        __vr.rejectValue(CODILLOCPROPI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODILLOCPROPI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(CODILLOCPROPI) == 0) {
      String val = __target__.getCodiLlocPropi();
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("^LF\\d{6}$");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(CODILLOCPROPI, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODILLOCPROPI)));
        }
      }
    }

    if (__vr.getFieldErrorCount(EXPANSIO) == 0) {
      java.lang.String __expansio = __target__.getExpansio();
      if (__expansio!= null && __expansio.length() > 50) {
        __vr.rejectValue(EXPANSIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EXPANSIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = __target__.getNom();
      if (__nom!= null && __nom.length() > 255) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
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

      // Check Unique MULTIPLE for (expansio, codilloc)
      if (__vr.getFieldErrorCount(EXPANSIO) == 0 && __vr.getFieldErrorCount(CODILLOC) == 0) {
        java.lang.String __expansio = __target__.getExpansio();
        java.lang.String __codilloc = __target__.getCodiLloc();
        Long __count_ = null;
        try { __count_ = __llocManager.count(org.fundaciobit.genapp.common.query.Where.AND(EXPANSIO.equal(__expansio), CODILLOC.equal(__codilloc))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(EXPANSIO, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__expansio)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EXPANSIO)));
            __vr.rejectValue(CODILLOC, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__codilloc)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODILLOC)));
        }
      }

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(CODILLOCPROPI) == 0) {
        java.lang.String __codillocpropi = __target__.getCodiLlocPropi();
        Long __count_ = null;
        try { __count_ = __llocManager.count(org.fundaciobit.genapp.common.query.Where.AND(CODILLOCPROPI.equal(__codillocpropi))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(CODILLOCPROPI, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__codillocpropi)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODILLOCPROPI)));
        }
      }

      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (expansio, codilloc)
      if (__vr.getFieldErrorCount(EXPANSIO) == 0 && __vr.getFieldErrorCount(CODILLOC) == 0 && __vr.getFieldErrorCount(LLOCID) == 0) {
        java.lang.String __expansio = __target__.getExpansio();
        java.lang.String __codilloc = __target__.getCodiLloc();
        java.lang.Long __llocid = __target__.getLlocID();
        Long __count_ = null;
        try { __count_ = __llocManager.count(org.fundaciobit.genapp.common.query.Where.AND(EXPANSIO.equal(__expansio), CODILLOC.equal(__codilloc), LLOCID.notEqual(__llocid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(EXPANSIO, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__expansio)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EXPANSIO)));
            __vr.rejectValue(CODILLOC, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__codilloc)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODILLOC)));
        }
      }

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(CODILLOCPROPI) == 0 && __vr.getFieldErrorCount(LLOCID) == 0) {
        java.lang.String __codillocpropi = __target__.getCodiLlocPropi();
        java.lang.Long __llocid = __target__.getLlocID();
        Long __count_ = null;
        try { __count_ = __llocManager.count(org.fundaciobit.genapp.common.query.Where.AND(CODILLOCPROPI.equal(__codillocpropi), LLOCID.notEqual(__llocid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(CODILLOCPROPI, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__codillocpropi)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODILLOCPROPI)));
        }
      }

    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      java.lang.Long __entitatid = __target__.getEntitatID();
      Long __count_ = null;
      try { __count_ = __entitatManager.count(EntitatFields.ENTITATID.equal(__entitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ENTITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatid)));
      }
    }

    if (__vr.getFieldErrorCount(UNITATID) == 0) {
      java.lang.Long __unitatid = __target__.getUnitatID();
      Long __count_ = null;
      try { __count_ = __unitatManager.count(UnitatFields.UNITATID.equal(__unitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(UNITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("unitat.unitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("unitat.unitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__unitatid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}