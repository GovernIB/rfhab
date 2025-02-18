package es.caib.rfhab.persistence.validator;

import es.caib.rfhab.persistence.UnitatJPA;
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
public class UnitatBeanValidator 
      extends AbstractBeanValidator<UnitatJPA> {


  // EJB's
  protected final es.caib.rfhab.model.dao.IUnitatManager __unitatManager;


  public final UnitatValidator<UnitatJPA> _validator;


  public UnitatBeanValidator(es.caib.rfhab.model.dao.IUnitatManager __unitatManager) { 
    this.__unitatManager = __unitatManager;
    _validator = new UnitatValidator<UnitatJPA>();
  }

  public UnitatBeanValidator(UnitatValidator<UnitatJPA> _validator,
     es.caib.rfhab.model.dao.IUnitatManager __unitatManager) {
    this.__unitatManager = __unitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(UnitatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<UnitatJPA> _bvr_ = new BeanValidatorResult<UnitatJPA>();
    _validator.validate(_bvr_, target, isNou, __unitatManager);
    return _bvr_.getErrors();
  }
}
