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
import es.caib.rfhab.persistence.validator.FuncionariLlocValidator;

import es.caib.rfhab.back.form.webdb.FuncionariLlocForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.rfhab.model.entity.FuncionariLloc;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class FuncionariLlocWebValidator extends AbstractWebValidator<FuncionariLlocForm, FuncionariLloc>
     implements Validator, FuncionariLlocFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected FuncionariLlocValidator<FuncionariLloc> validator = new FuncionariLlocValidator<FuncionariLloc>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.FuncionariService.JNDI_NAME)
  protected es.caib.rfhab.ejb.FuncionariService funcionariEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.FuncionariLlocService.JNDI_NAME)
  protected es.caib.rfhab.ejb.FuncionariLlocService funcionariLlocEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.LlocService.JNDI_NAME)
  protected es.caib.rfhab.ejb.LlocService llocEjb;



  public FuncionariLlocWebValidator() {
    super();    
  }
  
  @Override
  public FuncionariLloc getBeanOfForm(FuncionariLlocForm form) {
    return  form.getFuncionariLloc();
  }

  @Override
  public Class<FuncionariLlocForm> getClassOfForm() {
    return FuncionariLlocForm.class;
  }

  @Override
  public void validate(FuncionariLlocForm __form, FuncionariLloc __bean, Errors errors) {

    WebValidationResult<FuncionariLlocForm> wvr;
    wvr = new WebValidationResult<FuncionariLlocForm>(errors);

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


  public void validate(FuncionariLlocForm __form, FuncionariLloc __bean, Errors errors,
    WebValidationResult<FuncionariLlocForm> wvr, boolean isNou) {

    BeanValidatorResult<FuncionariLloc> __vr = new BeanValidatorResult<FuncionariLloc>();
    validator.validate(__vr, __bean,
      isNou, funcionariEjb, funcionariLlocEjb, llocEjb);

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

  public FuncionariLlocValidator<FuncionariLloc> getValidator() {
    return validator;
  }

  public void setValidator(FuncionariLlocValidator<FuncionariLloc> validator) {
    this.validator = validator;
  }

}