package es.caib.rfhab.persistence.validator;

import es.caib.rfhab.persistence.ScanWebJPA;
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
public class ScanWebBeanValidator 
      extends AbstractBeanValidator<ScanWebJPA> {


  // EJB's
  protected final es.caib.rfhab.model.dao.IScanWebManager __scanWebManager;

  protected final es.caib.rfhab.model.dao.IUsuariManager __usuariManager;


  public final ScanWebValidator<ScanWebJPA> _validator;


  public ScanWebBeanValidator(es.caib.rfhab.model.dao.IScanWebManager __scanWebManager,
     es.caib.rfhab.model.dao.IUsuariManager __usuariManager) { 
    this.__scanWebManager = __scanWebManager;
    this.__usuariManager = __usuariManager;
    _validator = new ScanWebValidator<ScanWebJPA>();
  }

  public ScanWebBeanValidator(ScanWebValidator<ScanWebJPA> _validator,
     es.caib.rfhab.model.dao.IScanWebManager __scanWebManager,
     es.caib.rfhab.model.dao.IUsuariManager __usuariManager) {
    this.__scanWebManager = __scanWebManager;
    this.__usuariManager = __usuariManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(ScanWebJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<ScanWebJPA> _bvr_ = new BeanValidatorResult<ScanWebJPA>();
    _validator.validate(_bvr_, target, isNou, __scanWebManager, __usuariManager);
    return _bvr_.getErrors();
  }
}
