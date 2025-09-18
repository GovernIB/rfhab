package es.caib.rfhab.api.interna.secure.activitat;

import org.apache.log4j.Logger;

import es.caib.rfhab.commons.utils.RegistreActivitatTipus;
import es.caib.rfhab.model.entity.Activitat;
import es.caib.rfhab.model.fields.ActivitatFields;

import es.caib.rfhab.persistence.validator.ActivitatValidator;

import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.validation.IValidatorResult;
import org.fundaciobit.genapp.common.validation.ValidationUtils;

/**
 * 
 * @author jpou
 * 
 */
public class RegistreActivitatValidator extends ActivitatValidator<Activitat> {

  protected final Logger log = Logger.getLogger(getClass());

  public RegistreActivitatValidator() {
    super();
  }

  @Override
  public void validate(IValidatorResult<Activitat> __vr, Activitat __target__, boolean __isNou__,
      es.caib.rfhab.model.dao.IActivitatManager __activitatManager,
      es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager) {

    // Valors Not Null
    switch (RegistreActivitatTipus.fromValue(__target__.getTipus())) {
      case COPIA:
        __vr.rejectIfEmptyOrWhitespace(__target__,
            ActivitatFields.URL,
            "genapp.validation.required",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("identificadorCopiaAutentica"));
        // __vr.rejectIfEmptyOrWhitespace(__target__,
        // new StringField(_TABLE_MODEL, "identificadorCopiaAutentica",
        // "identificadorCopiaAutentica"),
        // "genapp.validation.required",
        // new
        // org.fundaciobit.genapp.common.i18n.I18NArgumentCode("identificadorCopiaAutentica"));

        break;
      case COMPAREIX:
        __vr.rejectIfEmptyOrWhitespace(__target__,
            ActivitatFields.TRAMIT,
            "genapp.validation.required",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tramit"));
        __vr.rejectIfEmptyOrWhitespace(__target__,
            ActivitatFields.PROCEDIMENT,
            "genapp.validation.required",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("procediment"));
        __vr.rejectIfEmptyOrWhitespace(__target__,
            ActivitatFields.ARXIUEXPEDIENTID,
            "genapp.validation.required",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("arxiuexpedientid"));
        __vr.rejectIfEmptyOrWhitespace(__target__,
            ActivitatFields.ARXIUDOCUMENTID,
            "genapp.validation.required",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("arxiudocumentid"));

        __vr.rejectIfEmptyOrWhitespace(__target__,
            ActivitatFields.INTERESSATNOM,
            "genapp.validation.required",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("nominteressat"));
        __vr.rejectIfEmptyOrWhitespace(__target__,
            ActivitatFields.INTERESSATLLINATGE1,
            "genapp.validation.required",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("llinatge1interessat"));
        __vr.rejectIfEmptyOrWhitespace(__target__,
            ActivitatFields.INTERESSATTIPUS,
            "genapp.validation.required",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusidentificaciointeressat"));
        __vr.rejectIfEmptyOrWhitespace(__target__,
            ActivitatFields.INTERESSATIDENTIFICACIO,
            "genapp.validation.required",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("identificaciointeressat"));

        Object representantNom = __vr.getFieldValue(__target__, ActivitatFields.REPRESENTANTNOM);
        Object representantLlinatge1 = __vr.getFieldValue(__target__, ActivitatFields.REPRESENTANTLLINATGE1);
        Object representantLlinatge2 = __vr.getFieldValue(__target__, ActivitatFields.REPRESENTANTLLINATGE2);
        Object representantTipus = __vr.getFieldValue(__target__, ActivitatFields.REPRESENTANTTIPUS);
        Object representantIdentificacio = __vr.getFieldValue(__target__, ActivitatFields.REPRESENTANTIDENTIFICACIO);
        if (!ValidationUtils.isEmpty(representantNom) || !ValidationUtils.isEmpty(representantLlinatge1)
            || !ValidationUtils.isEmpty(representantLlinatge2) || !ValidationUtils.isEmpty(representantTipus)
            || !ValidationUtils.isEmpty(representantIdentificacio)) {
          __vr.rejectIfEmptyOrWhitespace(__target__,
              ActivitatFields.REPRESENTANTNOM,
              "activitat.validation.noinformatrepresentant",
              new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("nomrepresentant"));
          __vr.rejectIfEmptyOrWhitespace(__target__,
              ActivitatFields.REPRESENTANTLLINATGE1,
              "activitat.validation.noinformatrepresentant",
              new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("llinatge1representant"));
          __vr.rejectIfEmptyOrWhitespace(__target__,
              ActivitatFields.REPRESENTANTTIPUS,
              "activitat.validation.noinformatrepresentant",
              new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusidentificaciorepresentant"));
          __vr.rejectIfEmptyOrWhitespace(__target__,
              ActivitatFields.REPRESENTANTIDENTIFICACIO,
              "activitat.validation.noinformatrepresentant",
              new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("identificaciorepresentant"));
        }
        __vr.rejectIfEmptyOrWhitespace(__target__,
            ActivitatFields.IDACTUACIOTRAMIT,
            "genapp.validation.required",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("idActuacioTramitFh"));

        break;
      case TRAMIT:
        __vr.rejectIfEmptyOrWhitespace(__target__,
            ActivitatFields.REGISTRE,
            "genapp.validation.required",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("registre"));
        __vr.rejectIfEmptyOrWhitespace(__target__,
            ActivitatFields.IDACTUACIOTRAMIT,
            "genapp.validation.required",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("idActuacioTramitFh"));

        break;
    }

    super.validate(__vr, __target__, __isNou__, __activitatManager, __funcionariManager);
  }
}