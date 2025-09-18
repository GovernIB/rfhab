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
import es.caib.rfhab.persistence.validator.ActivitatValidator;

import es.caib.rfhab.back.form.webdb.ActivitatForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.rfhab.model.entity.Activitat;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class ActivitatWebValidator extends AbstractWebValidator<ActivitatForm, Activitat>
     implements Validator, ActivitatFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected ActivitatValidator<Activitat> validator = new ActivitatValidator<Activitat>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.ActivitatService.JNDI_NAME)
  protected es.caib.rfhab.ejb.ActivitatService activitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.FuncionariService.JNDI_NAME)
  protected es.caib.rfhab.ejb.FuncionariService funcionariEjb;



  public ActivitatWebValidator() {
    super();    
  }
  
  @Override
  public Activitat getBeanOfForm(ActivitatForm form) {
    return  form.getActivitat();
  }

  @Override
  public Class<ActivitatForm> getClassOfForm() {
    return ActivitatForm.class;
  }

  @Override
  public void validate(ActivitatForm __form, Activitat __bean, Errors errors) {

    WebValidationResult<ActivitatForm> wvr;
    wvr = new WebValidationResult<ActivitatForm>(errors);

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


  public void validate(ActivitatForm __form, Activitat __bean, Errors errors,
    WebValidationResult<ActivitatForm> wvr, boolean isNou) {

    BeanValidatorResult<Activitat> __vr = new BeanValidatorResult<Activitat>();
    validator.validate(__vr, __bean,
      isNou, activitatEjb, funcionariEjb);

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

  public ActivitatValidator<Activitat> getValidator() {
    return validator;
  }

  public void setValidator(ActivitatValidator<Activitat> validator) {
    this.validator = validator;
  }

}