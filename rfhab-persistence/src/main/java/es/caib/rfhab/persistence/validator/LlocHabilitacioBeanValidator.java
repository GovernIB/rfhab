package es.caib.rfhab.persistence.validator;

import es.caib.rfhab.persistence.LlocHabilitacioJPA;
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
public class LlocHabilitacioBeanValidator 
      extends AbstractBeanValidator<LlocHabilitacioJPA> {


  // EJB's
  protected final es.caib.rfhab.model.dao.IHabilitacioManager __habilitacioManager;

  protected final es.caib.rfhab.model.dao.ILlocManager __llocManager;

  protected final es.caib.rfhab.model.dao.ILlocHabilitacioManager __llocHabilitacioManager;


  public final LlocHabilitacioValidator<LlocHabilitacioJPA> _validator;


  public LlocHabilitacioBeanValidator(es.caib.rfhab.model.dao.IHabilitacioManager __habilitacioManager,
     es.caib.rfhab.model.dao.ILlocManager __llocManager,
     es.caib.rfhab.model.dao.ILlocHabilitacioManager __llocHabilitacioManager) { 
    this.__habilitacioManager = __habilitacioManager;
    this.__llocManager = __llocManager;
    this.__llocHabilitacioManager = __llocHabilitacioManager;
    _validator = new LlocHabilitacioValidator<LlocHabilitacioJPA>();
  }

  public LlocHabilitacioBeanValidator(LlocHabilitacioValidator<LlocHabilitacioJPA> _validator,
     es.caib.rfhab.model.dao.IHabilitacioManager __habilitacioManager,
     es.caib.rfhab.model.dao.ILlocManager __llocManager,
     es.caib.rfhab.model.dao.ILlocHabilitacioManager __llocHabilitacioManager) {
    this.__habilitacioManager = __habilitacioManager;
    this.__llocManager = __llocManager;
    this.__llocHabilitacioManager = __llocHabilitacioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(LlocHabilitacioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<LlocHabilitacioJPA> _bvr_ = new BeanValidatorResult<LlocHabilitacioJPA>();
    _validator.validate(_bvr_, target, isNou, __habilitacioManager, __llocManager, __llocHabilitacioManager);
    return _bvr_.getErrors();
  }
}
