package es.caib.rfhab.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.rfhab.model.entity.Funcionari;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.rfhab.model.fields.FuncionariFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class FuncionariValidator<I extends Funcionari>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements FuncionariFields {

    protected final Logger log = Logger.getLogger(getClass());


  public FuncionariValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,NUMERO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NUMERO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,LLINATGE1, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LLINATGE1)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSIDENTIFICADOR, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSIDENTIFICADOR)));

    __vr.rejectIfEmptyOrWhitespace(__target__,IDENTIFICADOR, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDENTIFICADOR)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USUARI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARI)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CORREU, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CORREU)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATACREACIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATACREACIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));

    // Check size
    if (__vr.getFieldErrorCount(NUMERO) == 0) {
      java.lang.String __numero = __target__.getNumero();
      if (__numero!= null && __numero.length() > 10) {
        __vr.rejectValue(NUMERO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NUMERO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(10)));
      }
    }

    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = __target__.getNom();
      if (__nom!= null && __nom.length() > 255) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(LLINATGE1) == 0) {
      java.lang.String __llinatge1 = __target__.getLlinatge1();
      if (__llinatge1!= null && __llinatge1.length() > 255) {
        __vr.rejectValue(LLINATGE1, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LLINATGE1)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(LLINATGE2) == 0) {
      java.lang.String __llinatge2 = __target__.getLlinatge2();
      if (__llinatge2!= null && __llinatge2.length() > 255) {
        __vr.rejectValue(LLINATGE2, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LLINATGE2)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(IDENTIFICADOR) == 0) {
      java.lang.String __identificador = __target__.getIdentificador();
      if (__identificador!= null && __identificador.length() > 50) {
        __vr.rejectValue(IDENTIFICADOR, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDENTIFICADOR)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(USUARI) == 0) {
      java.lang.String __usuari = __target__.getUsuari();
      if (__usuari!= null && __usuari.length() > 50) {
        __vr.rejectValue(USUARI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(CORREU) == 0) {
      java.lang.String __correu = __target__.getCorreu();
      if (__correu!= null && __correu.length() > 255) {
        __vr.rejectValue(CORREU, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CORREU)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(CORREU) == 0) {
      String val = __target__.getCorreu();
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-ñçÇ]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-ñçÇ]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(CORREU, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CORREU)));
        }
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
      if (__vr.getFieldErrorCount(IDENTIFICADOR) == 0) {
        java.lang.String __identificador = __target__.getIdentificador();
        Long __count_ = null;
        try { __count_ = __funcionariManager.count(org.fundaciobit.genapp.common.query.Where.AND(IDENTIFICADOR.equal(__identificador))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(IDENTIFICADOR, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__identificador)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDENTIFICADOR)));
        }
      }

      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(IDENTIFICADOR) == 0 && __vr.getFieldErrorCount(FUNCIONARIID) == 0) {
        java.lang.String __identificador = __target__.getIdentificador();
        java.lang.Long __funcionariid = __target__.getFuncionariID();
        Long __count_ = null;
        try { __count_ = __funcionariManager.count(org.fundaciobit.genapp.common.query.Where.AND(IDENTIFICADOR.equal(__identificador), FUNCIONARIID.notEqual(__funcionariid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(IDENTIFICADOR, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__identificador)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDENTIFICADOR)));
        }
      }

    }

    // Fields with References to Other tables 
  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}