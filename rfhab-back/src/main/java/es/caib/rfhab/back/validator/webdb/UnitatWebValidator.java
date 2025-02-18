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
import es.caib.rfhab.persistence.validator.UnitatValidator;

import es.caib.rfhab.back.form.webdb.UnitatForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.rfhab.model.entity.Unitat;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class UnitatWebValidator extends AbstractWebValidator<UnitatForm, Unitat>
     implements Validator, UnitatFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected UnitatValidator<Unitat> validator = new UnitatValidator<Unitat>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.UnitatService.JNDI_NAME)
  protected es.caib.rfhab.ejb.UnitatService unitatEjb;



  public UnitatWebValidator() {
    super();    
  }
  
  @Override
  public Unitat getBeanOfForm(UnitatForm form) {
    return  form.getUnitat();
  }

  @Override
  public Class<UnitatForm> getClassOfForm() {
    return UnitatForm.class;
  }

  @Override
  public void validate(UnitatForm __form, Unitat __bean, Errors errors) {

    WebValidationResult<UnitatForm> wvr;
    wvr = new WebValidationResult<UnitatForm>(errors);

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


  public void validate(UnitatForm __form, Unitat __bean, Errors errors,
    WebValidationResult<UnitatForm> wvr, boolean isNou) {

    BeanValidatorResult<Unitat> __vr = new BeanValidatorResult<Unitat>();
    validator.validate(__vr, __bean,
      isNou, unitatEjb);

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

  public UnitatValidator<Unitat> getValidator() {
    return validator;
  }

  public void setValidator(UnitatValidator<Unitat> validator) {
    this.validator = validator;
  }

}