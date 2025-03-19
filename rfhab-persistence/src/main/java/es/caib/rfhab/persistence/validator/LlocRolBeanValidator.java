package es.caib.rfhab.persistence.validator;

import es.caib.rfhab.persistence.LlocRolJPA;
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
public class LlocRolBeanValidator 
      extends AbstractBeanValidator<LlocRolJPA> {


  // EJB's
  protected final es.caib.rfhab.model.dao.ILlocManager __llocManager;

  protected final es.caib.rfhab.model.dao.ILlocRolManager __llocRolManager;

  protected final es.caib.rfhab.model.dao.IRolManager __rolManager;


  public final LlocRolValidator<LlocRolJPA> _validator;


  public LlocRolBeanValidator(es.caib.rfhab.model.dao.ILlocManager __llocManager,
     es.caib.rfhab.model.dao.ILlocRolManager __llocRolManager,
     es.caib.rfhab.model.dao.IRolManager __rolManager) { 
    this.__llocManager = __llocManager;
    this.__llocRolManager = __llocRolManager;
    this.__rolManager = __rolManager;
    _validator = new LlocRolValidator<LlocRolJPA>();
  }

  public LlocRolBeanValidator(LlocRolValidator<LlocRolJPA> _validator,
     es.caib.rfhab.model.dao.ILlocManager __llocManager,
     es.caib.rfhab.model.dao.ILlocRolManager __llocRolManager,
     es.caib.rfhab.model.dao.IRolManager __rolManager) {
    this.__llocManager = __llocManager;
    this.__llocRolManager = __llocRolManager;
    this.__rolManager = __rolManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(LlocRolJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<LlocRolJPA> _bvr_ = new BeanValidatorResult<LlocRolJPA>();
    _validator.validate(_bvr_, target, isNou, __llocManager, __llocRolManager, __rolManager);
    return _bvr_.getErrors();
  }
}
