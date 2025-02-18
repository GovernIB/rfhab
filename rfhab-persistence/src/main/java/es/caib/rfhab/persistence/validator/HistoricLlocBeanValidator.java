package es.caib.rfhab.persistence.validator;

import es.caib.rfhab.persistence.HistoricLlocJPA;
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
public class HistoricLlocBeanValidator 
      extends AbstractBeanValidator<HistoricLlocJPA> {


  // EJB's
  protected final es.caib.rfhab.model.dao.IHistoricLlocManager __historicLlocManager;

  protected final es.caib.rfhab.model.dao.ILlocManager __llocManager;

  protected final es.caib.rfhab.model.dao.IUsuariManager __usuariManager;


  public final HistoricLlocValidator<HistoricLlocJPA> _validator;


  public HistoricLlocBeanValidator(es.caib.rfhab.model.dao.IHistoricLlocManager __historicLlocManager,
     es.caib.rfhab.model.dao.ILlocManager __llocManager,
     es.caib.rfhab.model.dao.IUsuariManager __usuariManager) { 
    this.__historicLlocManager = __historicLlocManager;
    this.__llocManager = __llocManager;
    this.__usuariManager = __usuariManager;
    _validator = new HistoricLlocValidator<HistoricLlocJPA>();
  }

  public HistoricLlocBeanValidator(HistoricLlocValidator<HistoricLlocJPA> _validator,
     es.caib.rfhab.model.dao.IHistoricLlocManager __historicLlocManager,
     es.caib.rfhab.model.dao.ILlocManager __llocManager,
     es.caib.rfhab.model.dao.IUsuariManager __usuariManager) {
    this.__historicLlocManager = __historicLlocManager;
    this.__llocManager = __llocManager;
    this.__usuariManager = __usuariManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(HistoricLlocJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<HistoricLlocJPA> _bvr_ = new BeanValidatorResult<HistoricLlocJPA>();
    _validator.validate(_bvr_, target, isNou, __historicLlocManager, __llocManager, __usuariManager);
    return _bvr_.getErrors();
  }
}
