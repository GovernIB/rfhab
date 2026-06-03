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
import es.caib.rfhab.persistence.validator.SincroUnitatsValidator;

import es.caib.rfhab.back.form.webdb.SincroUnitatsForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.rfhab.model.entity.SincroUnitats;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class SincroUnitatsWebValidator extends AbstractWebValidator<SincroUnitatsForm, SincroUnitats>
     implements Validator, SincroUnitatsFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected SincroUnitatsValidator<SincroUnitats> validator = new SincroUnitatsValidator<SincroUnitats>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.SincroUnitatsService.JNDI_NAME)
  protected es.caib.rfhab.ejb.SincroUnitatsService sincroUnitatsEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.UsuariService.JNDI_NAME)
  protected es.caib.rfhab.ejb.UsuariService usuariEjb;



  public SincroUnitatsWebValidator() {
    super();    
  }
  
  @Override
  public SincroUnitats getBeanOfForm(SincroUnitatsForm form) {
    return  form.getSincroUnitats();
  }

  @Override
  public Class<SincroUnitatsForm> getClassOfForm() {
    return SincroUnitatsForm.class;
  }

  @Override
  public void validate(SincroUnitatsForm __form, SincroUnitats __bean, Errors errors) {

    WebValidationResult<SincroUnitatsForm> wvr;
    wvr = new WebValidationResult<SincroUnitatsForm>(errors);

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


  public void validate(SincroUnitatsForm __form, SincroUnitats __bean, Errors errors,
    WebValidationResult<SincroUnitatsForm> wvr, boolean isNou) {

    BeanValidatorResult<SincroUnitats> __vr = new BeanValidatorResult<SincroUnitats>();
    validator.validate(__vr, __bean,
      isNou, sincroUnitatsEjb, usuariEjb);

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

  public SincroUnitatsValidator<SincroUnitats> getValidator() {
    return validator;
  }

  public void setValidator(SincroUnitatsValidator<SincroUnitats> validator) {
    this.validator = validator;
  }

}