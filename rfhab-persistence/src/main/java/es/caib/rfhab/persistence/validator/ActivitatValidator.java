package es.caib.rfhab.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.rfhab.model.entity.Activitat;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.rfhab.model.fields.ActivitatFields;
import es.caib.rfhab.model.fields.AutoritzacioFields;
import es.caib.rfhab.model.fields.FuncionariFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class ActivitatValidator<I extends Activitat>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements ActivitatFields {

    protected final Logger log = Logger.getLogger(getClass());


  public ActivitatValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.rfhab.model.dao.IActivitatManager __activitatManager
    ,es.caib.rfhab.model.dao.IAutoritzacioManager __autoritzacioManager
    ,es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,FUNCIONARIID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FUNCIONARIID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUS)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATACREACIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATACREACIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,INTERESSATNOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(INTERESSATNOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,INTERESSATLLINATGE1, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(INTERESSATLLINATGE1)));

    __vr.rejectIfEmptyOrWhitespace(__target__,INTERESSATLLINATGE2, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(INTERESSATLLINATGE2)));

    __vr.rejectIfEmptyOrWhitespace(__target__,INTERESSATTIPUS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(INTERESSATTIPUS)));

    __vr.rejectIfEmptyOrWhitespace(__target__,INTERESSATIDENTIFICACIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(INTERESSATIDENTIFICACIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ESTAT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ESTAT)));

    // Check size
    if (__vr.getFieldErrorCount(REGISTRE) == 0) {
      java.lang.String __registre = __target__.getRegistre();
      if (__registre!= null && __registre.length() > 50) {
        __vr.rejectValue(REGISTRE, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REGISTRE)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(TRAMIT) == 0) {
      java.lang.String __tramit = __target__.getTramit();
      if (__tramit!= null && __tramit.length() > 150) {
        __vr.rejectValue(TRAMIT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TRAMIT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(150)));
      }
    }

    if (__vr.getFieldErrorCount(CODISIA) == 0) {
      java.lang.String __codisia = __target__.getCodiSia();
      if (__codisia!= null && __codisia.length() > 150) {
        __vr.rejectValue(CODISIA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODISIA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(150)));
      }
    }

    if (__vr.getFieldErrorCount(INTERESSATNOM) == 0) {
      java.lang.String __interessatnom = __target__.getInteressatNom();
      if (__interessatnom!= null && __interessatnom.length() > 255) {
        __vr.rejectValue(INTERESSATNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(INTERESSATNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(INTERESSATLLINATGE1) == 0) {
      java.lang.String __interessatllinatge1 = __target__.getInteressatLlinatge1();
      if (__interessatllinatge1!= null && __interessatllinatge1.length() > 255) {
        __vr.rejectValue(INTERESSATLLINATGE1, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(INTERESSATLLINATGE1)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(INTERESSATLLINATGE2) == 0) {
      java.lang.String __interessatllinatge2 = __target__.getInteressatLlinatge2();
      if (__interessatllinatge2!= null && __interessatllinatge2.length() > 255) {
        __vr.rejectValue(INTERESSATLLINATGE2, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(INTERESSATLLINATGE2)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(INTERESSATIDENTIFICACIO) == 0) {
      java.lang.String __interessatidentificacio = __target__.getInteressatIdentificacio();
      if (__interessatidentificacio!= null && __interessatidentificacio.length() > 50) {
        __vr.rejectValue(INTERESSATIDENTIFICACIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(INTERESSATIDENTIFICACIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(REPRESENTANTNOM) == 0) {
      java.lang.String __representantnom = __target__.getRepresentantNom();
      if (__representantnom!= null && __representantnom.length() > 255) {
        __vr.rejectValue(REPRESENTANTNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REPRESENTANTNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(REPRESENTANTLLINATGE1) == 0) {
      java.lang.String __representantllinatge1 = __target__.getRepresentantLlinatge1();
      if (__representantllinatge1!= null && __representantllinatge1.length() > 255) {
        __vr.rejectValue(REPRESENTANTLLINATGE1, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REPRESENTANTLLINATGE1)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(REPRESENTANTLLINATGE2) == 0) {
      java.lang.String __representantllinatge2 = __target__.getRepresentantLlinatge2();
      if (__representantllinatge2!= null && __representantllinatge2.length() > 255) {
        __vr.rejectValue(REPRESENTANTLLINATGE2, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REPRESENTANTLLINATGE2)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(REPRESENTANTIDENTIFICACIO) == 0) {
      java.lang.String __representantidentificacio = __target__.getRepresentantIdentificacio();
      if (__representantidentificacio!= null && __representantidentificacio.length() > 50) {
        __vr.rejectValue(REPRESENTANTIDENTIFICACIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REPRESENTANTIDENTIFICACIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(ARXIUDOCUMENTID) == 0) {
      java.lang.String __arxiudocumentid = __target__.getArxiuDocumentID();
      if (__arxiudocumentid!= null && __arxiudocumentid.length() > 255) {
        __vr.rejectValue(ARXIUDOCUMENTID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARXIUDOCUMENTID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ARXIUEXPEDIENTID) == 0) {
      java.lang.String __arxiuexpedientid = __target__.getArxiuExpedientID();
      if (__arxiuexpedientid!= null && __arxiuexpedientid.length() > 255) {
        __vr.rejectValue(ARXIUEXPEDIENTID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARXIUEXPEDIENTID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(URL) == 0) {
      java.lang.String __url = __target__.getUrl();
      if (__url!= null && __url.length() > 255) {
        __vr.rejectValue(URL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(URL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
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

    if (__vr.getFieldErrorCount(AUTORITZACIOID) == 0) {
      java.lang.Long __autoritzacioid = __target__.getAutoritzacioID();
      if (__autoritzacioid != null ) {
        Long __count_ = null;
        try { __count_ = __autoritzacioManager.count(AutoritzacioFields.AUTORITZACIOID.equal(__autoritzacioid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(AUTORITZACIOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("autoritzacio.autoritzacio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("autoritzacio.autoritzacioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__autoritzacioid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}