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
import es.caib.rfhab.persistence.validator.HistoricValidator;

import es.caib.rfhab.back.form.webdb.HistoricForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.rfhab.model.entity.Historic;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class HistoricWebValidator extends AbstractWebValidator<HistoricForm, Historic>
     implements Validator, HistoricFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected HistoricValidator<Historic> validator = new HistoricValidator<Historic>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.FuncionariService.JNDI_NAME)
  protected es.caib.rfhab.ejb.FuncionariService funcionariEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.HistoricService.JNDI_NAME)
  protected es.caib.rfhab.ejb.HistoricService historicEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.UsuariService.JNDI_NAME)
  protected es.caib.rfhab.ejb.UsuariService usuariEjb;



  public HistoricWebValidator() {
    super();    
  }
  
  @Override
  public Historic getBeanOfForm(HistoricForm form) {
    return  form.getHistoric();
  }

  @Override
  public Class<HistoricForm> getClassOfForm() {
    return HistoricForm.class;
  }

  @Override
  public void validate(HistoricForm __form, Historic __bean, Errors errors) {

    WebValidationResult<HistoricForm> wvr;
    wvr = new WebValidationResult<HistoricForm>(errors);

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


  public void validate(HistoricForm __form, Historic __bean, Errors errors,
    WebValidationResult<HistoricForm> wvr, boolean isNou) {

    BeanValidatorResult<Historic> __vr = new BeanValidatorResult<Historic>();
    validator.validate(__vr, __bean,
      isNou, funcionariEjb, historicEjb, usuariEjb);

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

  public HistoricValidator<Historic> getValidator() {
    return validator;
  }

  public void setValidator(HistoricValidator<Historic> validator) {
    this.validator = validator;
  }

}