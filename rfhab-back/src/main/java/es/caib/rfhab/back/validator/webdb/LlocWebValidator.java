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
import es.caib.rfhab.persistence.validator.LlocValidator;

import es.caib.rfhab.back.form.webdb.LlocForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.rfhab.model.entity.Lloc;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class LlocWebValidator extends AbstractWebValidator<LlocForm, Lloc>
     implements Validator, LlocFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected LlocValidator<Lloc> validator = new LlocValidator<Lloc>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.EntitatService.JNDI_NAME)
  protected es.caib.rfhab.ejb.EntitatService entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.LlocService.JNDI_NAME)
  protected es.caib.rfhab.ejb.LlocService llocEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.UnitatService.JNDI_NAME)
  protected es.caib.rfhab.ejb.UnitatService unitatEjb;



  public LlocWebValidator() {
    super();    
  }
  
  @Override
  public Lloc getBeanOfForm(LlocForm form) {
    return  form.getLloc();
  }

  @Override
  public Class<LlocForm> getClassOfForm() {
    return LlocForm.class;
  }

  @Override
  public void validate(LlocForm __form, Lloc __bean, Errors errors) {

    WebValidationResult<LlocForm> wvr;
    wvr = new WebValidationResult<LlocForm>(errors);

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


  public void validate(LlocForm __form, Lloc __bean, Errors errors,
    WebValidationResult<LlocForm> wvr, boolean isNou) {

    BeanValidatorResult<Lloc> __vr = new BeanValidatorResult<Lloc>();
    validator.validate(__vr, __bean,
      isNou, entitatEjb, llocEjb, unitatEjb);

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

  public LlocValidator<Lloc> getValidator() {
    return validator;
  }

  public void setValidator(LlocValidator<Lloc> validator) {
    this.validator = validator;
  }

}