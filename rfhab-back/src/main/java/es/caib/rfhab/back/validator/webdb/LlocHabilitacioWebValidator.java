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
import es.caib.rfhab.persistence.validator.LlocHabilitacioValidator;

import es.caib.rfhab.back.form.webdb.LlocHabilitacioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.rfhab.model.entity.LlocHabilitacio;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class LlocHabilitacioWebValidator extends AbstractWebValidator<LlocHabilitacioForm, LlocHabilitacio>
     implements Validator, LlocHabilitacioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected LlocHabilitacioValidator<LlocHabilitacio> validator = new LlocHabilitacioValidator<LlocHabilitacio>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.HabilitacioService.JNDI_NAME)
  protected es.caib.rfhab.ejb.HabilitacioService habilitacioEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.LlocService.JNDI_NAME)
  protected es.caib.rfhab.ejb.LlocService llocEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.LlocHabilitacioService.JNDI_NAME)
  protected es.caib.rfhab.ejb.LlocHabilitacioService llocHabilitacioEjb;



  public LlocHabilitacioWebValidator() {
    super();    
  }
  
  @Override
  public LlocHabilitacio getBeanOfForm(LlocHabilitacioForm form) {
    return  form.getLlocHabilitacio();
  }

  @Override
  public Class<LlocHabilitacioForm> getClassOfForm() {
    return LlocHabilitacioForm.class;
  }

  @Override
  public void validate(LlocHabilitacioForm __form, LlocHabilitacio __bean, Errors errors) {

    WebValidationResult<LlocHabilitacioForm> wvr;
    wvr = new WebValidationResult<LlocHabilitacioForm>(errors);

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


  public void validate(LlocHabilitacioForm __form, LlocHabilitacio __bean, Errors errors,
    WebValidationResult<LlocHabilitacioForm> wvr, boolean isNou) {

    BeanValidatorResult<LlocHabilitacio> __vr = new BeanValidatorResult<LlocHabilitacio>();
    validator.validate(__vr, __bean,
      isNou, habilitacioEjb, llocEjb, llocHabilitacioEjb);

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

  public LlocHabilitacioValidator<LlocHabilitacio> getValidator() {
    return validator;
  }

  public void setValidator(LlocHabilitacioValidator<LlocHabilitacio> validator) {
    this.validator = validator;
  }

}