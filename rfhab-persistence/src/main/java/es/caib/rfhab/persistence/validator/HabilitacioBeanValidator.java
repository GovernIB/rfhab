package es.caib.rfhab.persistence.validator;

import es.caib.rfhab.persistence.HabilitacioJPA;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import java.util.List;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.validation.AbstractBeanValidator;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class HabilitacioBeanValidator 
      extends AbstractBeanValidator<HabilitacioJPA> {


  // EJB's
  protected final es.caib.rfhab.model.dao.IHabilitacioManager __habilitacioManager;

  protected final es.caib.rfhab.model.dao.ITraduccioManager __traduccioManager;


  public final HabilitacioValidator<HabilitacioJPA> _validator;


  public HabilitacioBeanValidator(es.caib.rfhab.model.dao.IHabilitacioManager __habilitacioManager,
     es.caib.rfhab.model.dao.ITraduccioManager __traduccioManager) { 
    this.__habilitacioManager = __habilitacioManager;
    this.__traduccioManager = __traduccioManager;
    _validator = new HabilitacioValidator<HabilitacioJPA>();
  }

  public HabilitacioBeanValidator(HabilitacioValidator<HabilitacioJPA> _validator,
     es.caib.rfhab.model.dao.IHabilitacioManager __habilitacioManager,
     es.caib.rfhab.model.dao.ITraduccioManager __traduccioManager) {
    this.__habilitacioManager = __habilitacioManager;
    this.__traduccioManager = __traduccioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(HabilitacioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<HabilitacioJPA> _bvr_ = new BeanValidatorResult<HabilitacioJPA>();
    _validator.validate(_bvr_, target, isNou, __habilitacioManager, __traduccioManager);
    return _bvr_.getErrors();
  }
}
