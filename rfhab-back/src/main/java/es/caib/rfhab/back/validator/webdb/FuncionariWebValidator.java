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
import es.caib.rfhab.persistence.validator.FuncionariValidator;

import es.caib.rfhab.back.form.webdb.FuncionariForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.rfhab.model.entity.Funcionari;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class FuncionariWebValidator extends AbstractWebValidator<FuncionariForm, Funcionari>
     implements Validator, FuncionariFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected FuncionariValidator<Funcionari> validator = new FuncionariValidator<Funcionari>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.FuncionariService.JNDI_NAME)
  protected es.caib.rfhab.ejb.FuncionariService funcionariEjb;



  public FuncionariWebValidator() {
    super();    
  }
  
  @Override
  public Funcionari getBeanOfForm(FuncionariForm form) {
    return  form.getFuncionari();
  }

  @Override
  public Class<FuncionariForm> getClassOfForm() {
    return FuncionariForm.class;
  }

  @Override
  public void validate(FuncionariForm __form, Funcionari __bean, Errors errors) {

    WebValidationResult<FuncionariForm> wvr;
    wvr = new WebValidationResult<FuncionariForm>(errors);

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


  public void validate(FuncionariForm __form, Funcionari __bean, Errors errors,
    WebValidationResult<FuncionariForm> wvr, boolean isNou) {

    BeanValidatorResult<Funcionari> __vr = new BeanValidatorResult<Funcionari>();
    validator.validate(__vr, __bean,
      isNou, funcionariEjb);

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

  public FuncionariValidator<Funcionari> getValidator() {
    return validator;
  }

  public void setValidator(FuncionariValidator<Funcionari> validator) {
    this.validator = validator;
  }

}