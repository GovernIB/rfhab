package es.caib.rfhab.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.rfhab.model.entity.Rol;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.rfhab.model.fields.RolFields;
import es.caib.rfhab.model.fields.TraduccioFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class RolValidator<I extends Rol>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements RolFields {

    protected final Logger log = Logger.getLogger(getClass());


  public RolValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.rfhab.model.dao.IRolManager __rolManager
    ,es.caib.rfhab.model.dao.ITraduccioManager __traduccioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,NOMID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOMID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CODI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)));

    // Check size
    if (__vr.getFieldErrorCount(CODI) == 0) {
      java.lang.String __codi = __target__.getCodi();
      if (__codi!= null && __codi.length() > 50) {
        __vr.rejectValue(CODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
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
    if (__vr.getFieldErrorCount(NOMID) == 0) {
      java.lang.Long __nomid = __target__.getNomID();
      Long __count_ = null;
      try { __count_ = __traduccioManager.count(TraduccioFields.TRADUCCIOID.equal(__nomid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(NOMID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__nomid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}