package es.caib.rfhab.persistence.validator;

import es.caib.rfhab.persistence.LlocJPA;
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
public class LlocBeanValidator 
      extends AbstractBeanValidator<LlocJPA> {


  // EJB's
  protected final es.caib.rfhab.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.rfhab.model.dao.ILlocManager __llocManager;

  protected final es.caib.rfhab.model.dao.IUnitatManager __unitatManager;


  public final LlocValidator<LlocJPA> _validator;


  public LlocBeanValidator(es.caib.rfhab.model.dao.IEntitatManager __entitatManager,
     es.caib.rfhab.model.dao.ILlocManager __llocManager,
     es.caib.rfhab.model.dao.IUnitatManager __unitatManager) { 
    this.__entitatManager = __entitatManager;
    this.__llocManager = __llocManager;
    this.__unitatManager = __unitatManager;
    _validator = new LlocValidator<LlocJPA>();
  }

  public LlocBeanValidator(LlocValidator<LlocJPA> _validator,
     es.caib.rfhab.model.dao.IEntitatManager __entitatManager,
     es.caib.rfhab.model.dao.ILlocManager __llocManager,
     es.caib.rfhab.model.dao.IUnitatManager __unitatManager) {
    this.__entitatManager = __entitatManager;
    this.__llocManager = __llocManager;
    this.__unitatManager = __unitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(LlocJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<LlocJPA> _bvr_ = new BeanValidatorResult<LlocJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __llocManager, __unitatManager);
    return _bvr_.getErrors();
  }
}
