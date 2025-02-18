package es.caib.rfhab.persistence.validator;

import es.caib.rfhab.persistence.ActivitatJPA;
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
public class ActivitatBeanValidator 
      extends AbstractBeanValidator<ActivitatJPA> {


  // EJB's
  protected final es.caib.rfhab.model.dao.IActivitatManager __activitatManager;

  protected final es.caib.rfhab.model.dao.IAutoritzacioManager __autoritzacioManager;

  protected final es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager;


  public final ActivitatValidator<ActivitatJPA> _validator;


  public ActivitatBeanValidator(es.caib.rfhab.model.dao.IActivitatManager __activitatManager,
     es.caib.rfhab.model.dao.IAutoritzacioManager __autoritzacioManager,
     es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager) { 
    this.__activitatManager = __activitatManager;
    this.__autoritzacioManager = __autoritzacioManager;
    this.__funcionariManager = __funcionariManager;
    _validator = new ActivitatValidator<ActivitatJPA>();
  }

  public ActivitatBeanValidator(ActivitatValidator<ActivitatJPA> _validator,
     es.caib.rfhab.model.dao.IActivitatManager __activitatManager,
     es.caib.rfhab.model.dao.IAutoritzacioManager __autoritzacioManager,
     es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager) {
    this.__activitatManager = __activitatManager;
    this.__autoritzacioManager = __autoritzacioManager;
    this.__funcionariManager = __funcionariManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(ActivitatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<ActivitatJPA> _bvr_ = new BeanValidatorResult<ActivitatJPA>();
    _validator.validate(_bvr_, target, isNou, __activitatManager, __autoritzacioManager, __funcionariManager);
    return _bvr_.getErrors();
  }
}
