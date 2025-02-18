package es.caib.rfhab.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.rfhab.model.entity.Entitat;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.rfhab.model.fields.EntitatFields;
import es.caib.rfhab.model.fields.UnitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class EntitatValidator<I extends Entitat>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements EntitatFields {

    protected final Logger log = Logger.getLogger(getClass());


  public EntitatValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.rfhab.model.dao.IEntitatManager __entitatManager
    ,es.caib.rfhab.model.dao.IUnitatManager __unitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,ACTIU, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ACTIU)));

    // Check size
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = __target__.getNom();
      if (__nom!= null && __nom.length() > 255) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
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
    if (__vr.getFieldErrorCount(UNITATID) == 0) {
      java.lang.Long __unitatid = __target__.getUnitatID();
      if (__unitatid != null ) {
        Long __count_ = null;
        try { __count_ = __unitatManager.count(UnitatFields.UNITATID.equal(__unitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(UNITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("unitat.unitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("unitat.unitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__unitatid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}