package es.caib.rfhab.persistence.validator;

import es.caib.rfhab.persistence.EntitatJPA;
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
public class EntitatBeanValidator 
      extends AbstractBeanValidator<EntitatJPA> {


  // EJB's
  protected final es.caib.rfhab.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.rfhab.model.dao.IUnitatManager __unitatManager;


  public final EntitatValidator<EntitatJPA> _validator;


  public EntitatBeanValidator(es.caib.rfhab.model.dao.IEntitatManager __entitatManager,
     es.caib.rfhab.model.dao.IUnitatManager __unitatManager) { 
    this.__entitatManager = __entitatManager;
    this.__unitatManager = __unitatManager;
    _validator = new EntitatValidator<EntitatJPA>();
  }

  public EntitatBeanValidator(EntitatValidator<EntitatJPA> _validator,
     es.caib.rfhab.model.dao.IEntitatManager __entitatManager,
     es.caib.rfhab.model.dao.IUnitatManager __unitatManager) {
    this.__entitatManager = __entitatManager;
    this.__unitatManager = __unitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(EntitatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<EntitatJPA> _bvr_ = new BeanValidatorResult<EntitatJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __unitatManager);
    return _bvr_.getErrors();
  }
}
