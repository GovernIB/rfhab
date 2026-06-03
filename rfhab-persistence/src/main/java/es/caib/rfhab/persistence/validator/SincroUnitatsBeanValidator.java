package es.caib.rfhab.persistence.validator;

import es.caib.rfhab.persistence.SincroUnitatsJPA;
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
public class SincroUnitatsBeanValidator 
      extends AbstractBeanValidator<SincroUnitatsJPA> {


  // EJB's
  protected final es.caib.rfhab.model.dao.ISincroUnitatsManager __sincroUnitatsManager;

  protected final es.caib.rfhab.model.dao.IUsuariManager __usuariManager;


  public final SincroUnitatsValidator<SincroUnitatsJPA> _validator;


  public SincroUnitatsBeanValidator(es.caib.rfhab.model.dao.ISincroUnitatsManager __sincroUnitatsManager,
     es.caib.rfhab.model.dao.IUsuariManager __usuariManager) { 
    this.__sincroUnitatsManager = __sincroUnitatsManager;
    this.__usuariManager = __usuariManager;
    _validator = new SincroUnitatsValidator<SincroUnitatsJPA>();
  }

  public SincroUnitatsBeanValidator(SincroUnitatsValidator<SincroUnitatsJPA> _validator,
     es.caib.rfhab.model.dao.ISincroUnitatsManager __sincroUnitatsManager,
     es.caib.rfhab.model.dao.IUsuariManager __usuariManager) {
    this.__sincroUnitatsManager = __sincroUnitatsManager;
    this.__usuariManager = __usuariManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(SincroUnitatsJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<SincroUnitatsJPA> _bvr_ = new BeanValidatorResult<SincroUnitatsJPA>();
    _validator.validate(_bvr_, target, isNou, __sincroUnitatsManager, __usuariManager);
    return _bvr_.getErrors();
  }
}
