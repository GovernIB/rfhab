package es.caib.rfhab.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.rfhab.model.entity.FuncionariRol;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.rfhab.model.fields.FuncionariRolFields;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.RolFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class FuncionariRolValidator<I extends FuncionariRol>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements FuncionariRolFields {

    protected final Logger log = Logger.getLogger(getClass());


  public FuncionariRolValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager
    ,es.caib.rfhab.model.dao.IFuncionariRolManager __funcionariRolManager
    ,es.caib.rfhab.model.dao.IRolManager __rolManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,FUNCIONARIID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FUNCIONARIID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ROLID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ROLID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATACREACIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATACREACIO)));

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
    if (__vr.getFieldErrorCount(FUNCIONARIID) == 0) {
      java.lang.Long __funcionariid = __target__.getFuncionariID();
      Long __count_ = null;
      try { __count_ = __funcionariManager.count(FuncionariFields.FUNCIONARIID.equal(__funcionariid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(FUNCIONARIID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("funcionari.funcionari"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("funcionari.funcionariID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__funcionariid)));
      }
    }

    if (__vr.getFieldErrorCount(ROLID) == 0) {
      java.lang.Long __rolid = __target__.getRolID();
      Long __count_ = null;
      try { __count_ = __rolManager.count(RolFields.ROLID.equal(__rolid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ROLID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("rol.rol"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("rol.rolID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__rolid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}