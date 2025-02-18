package es.caib.rfhab.persistence.validator;

import es.caib.rfhab.persistence.HistoricJPA;
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
public class HistoricBeanValidator 
      extends AbstractBeanValidator<HistoricJPA> {


  // EJB's
  protected final es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager;

  protected final es.caib.rfhab.model.dao.IHistoricManager __historicManager;

  protected final es.caib.rfhab.model.dao.IUsuariManager __usuariManager;


  public final HistoricValidator<HistoricJPA> _validator;


  public HistoricBeanValidator(es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager,
     es.caib.rfhab.model.dao.IHistoricManager __historicManager,
     es.caib.rfhab.model.dao.IUsuariManager __usuariManager) { 
    this.__funcionariManager = __funcionariManager;
    this.__historicManager = __historicManager;
    this.__usuariManager = __usuariManager;
    _validator = new HistoricValidator<HistoricJPA>();
  }

  public HistoricBeanValidator(HistoricValidator<HistoricJPA> _validator,
     es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager,
     es.caib.rfhab.model.dao.IHistoricManager __historicManager,
     es.caib.rfhab.model.dao.IUsuariManager __usuariManager) {
    this.__funcionariManager = __funcionariManager;
    this.__historicManager = __historicManager;
    this.__usuariManager = __usuariManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(HistoricJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<HistoricJPA> _bvr_ = new BeanValidatorResult<HistoricJPA>();
    _validator.validate(_bvr_, target, isNou, __funcionariManager, __historicManager, __usuariManager);
    return _bvr_.getErrors();
  }
}
