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
import es.caib.rfhab.persistence.validator.LlocRolValidator;

import es.caib.rfhab.back.form.webdb.LlocRolForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.rfhab.model.entity.LlocRol;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class LlocRolWebValidator extends AbstractWebValidator<LlocRolForm, LlocRol>
     implements Validator, LlocRolFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected LlocRolValidator<LlocRol> validator = new LlocRolValidator<LlocRol>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.LlocService.JNDI_NAME)
  protected es.caib.rfhab.ejb.LlocService llocEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.LlocRolService.JNDI_NAME)
  protected es.caib.rfhab.ejb.LlocRolService llocRolEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.RolService.JNDI_NAME)
  protected es.caib.rfhab.ejb.RolService rolEjb;



  public LlocRolWebValidator() {
    super();    
  }
  
  @Override
  public LlocRol getBeanOfForm(LlocRolForm form) {
    return  form.getLlocRol();
  }

  @Override
  public Class<LlocRolForm> getClassOfForm() {
    return LlocRolForm.class;
  }

  @Override
  public void validate(LlocRolForm __form, LlocRol __bean, Errors errors) {

    WebValidationResult<LlocRolForm> wvr;
    wvr = new WebValidationResult<LlocRolForm>(errors);

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


  public void validate(LlocRolForm __form, LlocRol __bean, Errors errors,
    WebValidationResult<LlocRolForm> wvr, boolean isNou) {

    BeanValidatorResult<LlocRol> __vr = new BeanValidatorResult<LlocRol>();
    validator.validate(__vr, __bean,
      isNou, llocEjb, llocRolEjb, rolEjb);

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

  public LlocRolValidator<LlocRol> getValidator() {
    return validator;
  }

  public void setValidator(LlocRolValidator<LlocRol> validator) {
    this.validator = validator;
  }

}