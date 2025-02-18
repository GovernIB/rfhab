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
import es.caib.rfhab.persistence.validator.FuncionariRolValidator;

import es.caib.rfhab.back.form.webdb.FuncionariRolForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.rfhab.model.entity.FuncionariRol;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class FuncionariRolWebValidator extends AbstractWebValidator<FuncionariRolForm, FuncionariRol>
     implements Validator, FuncionariRolFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected FuncionariRolValidator<FuncionariRol> validator = new FuncionariRolValidator<FuncionariRol>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.FuncionariService.JNDI_NAME)
  protected es.caib.rfhab.ejb.FuncionariService funcionariEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.FuncionariRolService.JNDI_NAME)
  protected es.caib.rfhab.ejb.FuncionariRolService funcionariRolEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.RolService.JNDI_NAME)
  protected es.caib.rfhab.ejb.RolService rolEjb;



  public FuncionariRolWebValidator() {
    super();    
  }
  
  @Override
  public FuncionariRol getBeanOfForm(FuncionariRolForm form) {
    return  form.getFuncionariRol();
  }

  @Override
  public Class<FuncionariRolForm> getClassOfForm() {
    return FuncionariRolForm.class;
  }

  @Override
  public void validate(FuncionariRolForm __form, FuncionariRol __bean, Errors errors) {

    WebValidationResult<FuncionariRolForm> wvr;
    wvr = new WebValidationResult<FuncionariRolForm>(errors);

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


  public void validate(FuncionariRolForm __form, FuncionariRol __bean, Errors errors,
    WebValidationResult<FuncionariRolForm> wvr, boolean isNou) {

    BeanValidatorResult<FuncionariRol> __vr = new BeanValidatorResult<FuncionariRol>();
    validator.validate(__vr, __bean,
      isNou, funcionariEjb, funcionariRolEjb, rolEjb);

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

  public FuncionariRolValidator<FuncionariRol> getValidator() {
    return validator;
  }

  public void setValidator(FuncionariRolValidator<FuncionariRol> validator) {
    this.validator = validator;
  }

}