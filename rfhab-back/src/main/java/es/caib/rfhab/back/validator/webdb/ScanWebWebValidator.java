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
import es.caib.rfhab.persistence.validator.ScanWebValidator;

import es.caib.rfhab.back.form.webdb.ScanWebForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.rfhab.model.entity.ScanWeb;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class ScanWebWebValidator extends AbstractWebValidator<ScanWebForm, ScanWeb>
     implements Validator, ScanWebFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected ScanWebValidator<ScanWeb> validator = new ScanWebValidator<ScanWeb>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.ScanWebService.JNDI_NAME)
  protected es.caib.rfhab.ejb.ScanWebService scanWebEjb;

  @javax.ejb.EJB(mappedName = es.caib.rfhab.ejb.UsuariService.JNDI_NAME)
  protected es.caib.rfhab.ejb.UsuariService usuariEjb;



  public ScanWebWebValidator() {
    super();    
  }
  
  @Override
  public ScanWeb getBeanOfForm(ScanWebForm form) {
    return  form.getScanWeb();
  }

  @Override
  public Class<ScanWebForm> getClassOfForm() {
    return ScanWebForm.class;
  }

  @Override
  public void validate(ScanWebForm __form, ScanWeb __bean, Errors errors) {

    WebValidationResult<ScanWebForm> wvr;
    wvr = new WebValidationResult<ScanWebForm>(errors);

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


  public void validate(ScanWebForm __form, ScanWeb __bean, Errors errors,
    WebValidationResult<ScanWebForm> wvr, boolean isNou) {

    BeanValidatorResult<ScanWeb> __vr = new BeanValidatorResult<ScanWeb>();
    validator.validate(__vr, __bean,
      isNou, scanWebEjb, usuariEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
        if (!errors.hasFieldErrors(get(FITXERID))){
            CommonsMultipartFile fitxerID = ((ScanWebForm)__form).getFitxerID();
            if (fitxerID == null || fitxerID.isEmpty()) {
                errors.rejectValue(get(FITXERID), "genapp.validation.required",
                new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(FITXERID)) },
                null);
            }
        }

    }

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public ScanWebValidator<ScanWeb> getValidator() {
    return validator;
  }

  public void setValidator(ScanWebValidator<ScanWeb> validator) {
    this.validator = validator;
  }

}