package es.caib.rfhab.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import java.util.List;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.rfhab.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.rfhab.persistence.validator.RolValidator;

import es.caib.rfhab.back.form.webdb.RolForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.rfhab.model.entity.Rol;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class RolWebValidator extends AbstractWebValidator<RolForm, Rol>
     implements Validator, RolFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected RolValidator<Rol> validator = new RolValidator<Rol>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.RolService.JNDI_NAME)
  protected es.caib.rfhab.ejb.RolService rolEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.TraduccioService.JNDI_NAME)
  protected es.caib.rfhab.ejb.TraduccioService traduccioEjb;



  public RolWebValidator() {
    super();    
  }
  
  @Override
  public Rol getBeanOfForm(RolForm form) {
    return  form.getRol();
  }

  @Override
  public Class<RolForm> getClassOfForm() {
    return RolForm.class;
  }

  @Override
  public void validate(RolForm __form, Rol __bean, Errors errors) {

java.util.List<Field<?>> _ignoreFields = new java.util.ArrayList<Field<?>>();
_ignoreFields.add(NOMID);
    WebValidationResult<RolForm> wvr;
    wvr = new WebValidationResult<RolForm>(errors, _ignoreFields);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean(String.valueOf(objNou));
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(RolForm __form, Rol __bean, Errors errors,
    WebValidationResult<RolForm> wvr, boolean isNou) {

  {
      es.caib.rfhab.persistence.RolJPA __jpa;
      __jpa = (es.caib.rfhab.persistence.RolJPA)__bean;
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.rfhab.persistence.TraduccioJPA tradJPA = __jpa.getNom();
      if (tradJPA != null) {
        // TODO ERROR
        java.util.Map<String,es.caib.rfhab.persistence.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
        int countNotNull = 0;
        for (String _idioma : _trad.keySet()) {
          es.caib.rfhab.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
          if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
          } else {
            countNotNull++;
          }
        }

          if (countNotNull  == _trad.size()) {
            // OK Tot esta ple
          } else {
            for (String _idioma : _trad.keySet()) {
              es.caib.rfhab.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
              if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
                errors.rejectValue("rol.nom", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
                errors.rejectValue("rol.nom.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
              }
            }
          }
      } else {
        errors.rejectValue("rol.nom", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
      }
    }

  }
    BeanValidatorResult<Rol> __vr = new BeanValidatorResult<Rol>();
    validator.validate(__vr, __bean,
      isNou, rolEjb, traduccioEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }


  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public RolValidator<Rol> getValidator() {
    return validator;
  }

  public void setValidator(RolValidator<Rol> validator) {
    this.validator = validator;
  }

}