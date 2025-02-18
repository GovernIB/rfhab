package es.caib.rfhab.persistence.validator;

import es.caib.rfhab.persistence.RolJPA;
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
public class RolBeanValidator 
      extends AbstractBeanValidator<RolJPA> {


  // EJB's
  protected final es.caib.rfhab.model.dao.IRolManager __rolManager;

  protected final es.caib.rfhab.model.dao.ITraduccioManager __traduccioManager;


  public final RolValidator<RolJPA> _validator;


  public RolBeanValidator(es.caib.rfhab.model.dao.IRolManager __rolManager,
     es.caib.rfhab.model.dao.ITraduccioManager __traduccioManager) { 
    this.__rolManager = __rolManager;
    this.__traduccioManager = __traduccioManager;
    _validator = new RolValidator<RolJPA>();
  }

  public RolBeanValidator(RolValidator<RolJPA> _validator,
     es.caib.rfhab.model.dao.IRolManager __rolManager,
     es.caib.rfhab.model.dao.ITraduccioManager __traduccioManager) {
    this.__rolManager = __rolManager;
    this.__traduccioManager = __traduccioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(RolJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<RolJPA> _bvr_ = new BeanValidatorResult<RolJPA>();
    _validator.validate(_bvr_, target, isNou, __rolManager, __traduccioManager);
    return _bvr_.getErrors();
  }
}
