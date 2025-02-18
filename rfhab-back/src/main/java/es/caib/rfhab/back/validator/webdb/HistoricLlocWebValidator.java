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
import es.caib.rfhab.persistence.validator.HistoricLlocValidator;

import es.caib.rfhab.back.form.webdb.HistoricLlocForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.rfhab.model.entity.HistoricLloc;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class HistoricLlocWebValidator extends AbstractWebValidator<HistoricLlocForm, HistoricLloc>
     implements Validator, HistoricLlocFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected HistoricLlocValidator<HistoricLloc> validator = new HistoricLlocValidator<HistoricLloc>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.HistoricLlocService.JNDI_NAME)
  protected es.caib.rfhab.ejb.HistoricLlocService historicLlocEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.LlocService.JNDI_NAME)
  protected es.caib.rfhab.ejb.LlocService llocEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.UsuariService.JNDI_NAME)
  protected es.caib.rfhab.ejb.UsuariService usuariEjb;



  public HistoricLlocWebValidator() {
    super();    
  }
  
  @Override
  public HistoricLloc getBeanOfForm(HistoricLlocForm form) {
    return  form.getHistoricLloc();
  }

  @Override
  public Class<HistoricLlocForm> getClassOfForm() {
    return HistoricLlocForm.class;
  }

  @Override
  public void validate(HistoricLlocForm __form, HistoricLloc __bean, Errors errors) {

    WebValidationResult<HistoricLlocForm> wvr;
    wvr = new WebValidationResult<HistoricLlocForm>(errors);

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


  public void validate(HistoricLlocForm __form, HistoricLloc __bean, Errors errors,
    WebValidationResult<HistoricLlocForm> wvr, boolean isNou) {

    BeanValidatorResult<HistoricLloc> __vr = new BeanValidatorResult<HistoricLloc>();
    validator.validate(__vr, __bean,
      isNou, historicLlocEjb, llocEjb, usuariEjb);

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

  public HistoricLlocValidator<HistoricLloc> getValidator() {
    return validator;
  }

  public void setValidator(HistoricLlocValidator<HistoricLloc> validator) {
    this.validator = validator;
  }

}