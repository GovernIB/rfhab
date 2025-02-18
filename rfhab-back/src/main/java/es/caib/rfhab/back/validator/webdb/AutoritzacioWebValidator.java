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
import es.caib.rfhab.persistence.validator.AutoritzacioValidator;

import es.caib.rfhab.back.form.webdb.AutoritzacioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.rfhab.model.entity.Autoritzacio;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class AutoritzacioWebValidator extends AbstractWebValidator<AutoritzacioForm, Autoritzacio>
     implements Validator, AutoritzacioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected AutoritzacioValidator<Autoritzacio> validator = new AutoritzacioValidator<Autoritzacio>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.AutoritzacioService.JNDI_NAME)
  protected es.caib.rfhab.ejb.AutoritzacioService autoritzacioEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.FuncionariService.JNDI_NAME)
  protected es.caib.rfhab.ejb.FuncionariService funcionariEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.LlocService.JNDI_NAME)
  protected es.caib.rfhab.ejb.LlocService llocEjb;



  public AutoritzacioWebValidator() {
    super();    
  }
  
  @Override
  public Autoritzacio getBeanOfForm(AutoritzacioForm form) {
    return  form.getAutoritzacio();
  }

  @Override
  public Class<AutoritzacioForm> getClassOfForm() {
    return AutoritzacioForm.class;
  }

  @Override
  public void validate(AutoritzacioForm __form, Autoritzacio __bean, Errors errors) {

    WebValidationResult<AutoritzacioForm> wvr;
    wvr = new WebValidationResult<AutoritzacioForm>(errors);

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


  public void validate(AutoritzacioForm __form, Autoritzacio __bean, Errors errors,
    WebValidationResult<AutoritzacioForm> wvr, boolean isNou) {

    BeanValidatorResult<Autoritzacio> __vr = new BeanValidatorResult<Autoritzacio>();
    validator.validate(__vr, __bean,
      isNou, autoritzacioEjb, funcionariEjb, llocEjb);

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

  public AutoritzacioValidator<Autoritzacio> getValidator() {
    return validator;
  }

  public void setValidator(AutoritzacioValidator<Autoritzacio> validator) {
    this.validator = validator;
  }

}