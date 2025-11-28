package es.caib.rfhab.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.rfhab.model.entity.LlocHabilitacio;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.rfhab.model.fields.LlocHabilitacioFields;
import es.caib.rfhab.model.fields.HabilitacioFields;
import es.caib.rfhab.model.fields.LlocFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class LlocHabilitacioValidator<I extends LlocHabilitacio>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements LlocHabilitacioFields {

    protected final Logger log = Logger.getLogger(getClass());


  public LlocHabilitacioValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.rfhab.model.dao.IHabilitacioManager __habilitacioManager
    ,es.caib.rfhab.model.dao.ILlocManager __llocManager
    ,es.caib.rfhab.model.dao.ILlocHabilitacioManager __llocHabilitacioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,DATACREACIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATACREACIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,LLOCID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LLOCID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,HABILITACIOID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(HABILITACIOID)));

    // Check size
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

    if (__vr.getFieldErrorCount(HABILITACIOID) == 0) {
      java.lang.Long __habilitacioid = __target__.getHabilitacioId();
      Long __count_ = null;
      try { __count_ = __habilitacioManager.count(HabilitacioFields.HABILITACIOID.equal(__habilitacioid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(HABILITACIOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("habilitacio.habilitacio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("habilitacio.habilitacioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__habilitacioid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}