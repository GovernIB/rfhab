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
import es.caib.rfhab.persistence.validator.HabilitacioValidator;

import es.caib.rfhab.back.form.webdb.HabilitacioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.rfhab.model.entity.Habilitacio;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class HabilitacioWebValidator extends AbstractWebValidator<HabilitacioForm, Habilitacio>
     implements Validator, HabilitacioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected HabilitacioValidator<Habilitacio> validator = new HabilitacioValidator<Habilitacio>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.HabilitacioService.JNDI_NAME)
  protected es.caib.rfhab.ejb.HabilitacioService habilitacioEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.TraduccioService.JNDI_NAME)
  protected es.caib.rfhab.ejb.TraduccioService traduccioEjb;



  public HabilitacioWebValidator() {
    super();    
  }
  
  @Override
  public Habilitacio getBeanOfForm(HabilitacioForm form) {
    return  form.getHabilitacio();
  }

  @Override
  public Class<HabilitacioForm> getClassOfForm() {
    return HabilitacioForm.class;
  }

  @Override
  public void validate(HabilitacioForm __form, Habilitacio __bean, Errors errors) {

java.util.List<Field<?>> _ignoreFields = new java.util.ArrayList<Field<?>>();
_ignoreFields.add(NOMID);
    WebValidationResult<HabilitacioForm> wvr;
    wvr = new WebValidationResult<HabilitacioForm>(errors, _ignoreFields);

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


  public void validate(HabilitacioForm __form, Habilitacio __bean, Errors errors,
    WebValidationResult<HabilitacioForm> wvr, boolean isNou) {

  {
      es.caib.rfhab.persistence.HabilitacioJPA __jpa;
      __jpa = (es.caib.rfhab.persistence.HabilitacioJPA)__bean;
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
                errors.rejectValue("habilitacio.nom", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
                errors.rejectValue("habilitacio.nom.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
              }
            }
          }
      } else {
        errors.rejectValue("habilitacio.nom", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
      }
    }

  }
    BeanValidatorResult<Habilitacio> __vr = new BeanValidatorResult<Habilitacio>();
    validator.validate(__vr, __bean,
      isNou, habilitacioEjb, traduccioEjb);

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

  public HabilitacioValidator<Habilitacio> getValidator() {
    return validator;
  }

  public void setValidator(HabilitacioValidator<Habilitacio> validator) {
    this.validator = validator;
  }

}