package es.caib.rfhab.persistence.validator;

import es.caib.rfhab.persistence.FuncionariLlocJPA;
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
public class FuncionariLlocBeanValidator 
      extends AbstractBeanValidator<FuncionariLlocJPA> {


  // EJB's
  protected final es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager;

  protected final es.caib.rfhab.model.dao.IFuncionariLlocManager __funcionariLlocManager;

  protected final es.caib.rfhab.model.dao.ILlocManager __llocManager;

  protected final es.caib.rfhab.model.dao.IUsuariManager __usuariManager;


  public final FuncionariLlocValidator<FuncionariLlocJPA> _validator;


  public FuncionariLlocBeanValidator(es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager,
     es.caib.rfhab.model.dao.IFuncionariLlocManager __funcionariLlocManager,
     es.caib.rfhab.model.dao.ILlocManager __llocManager,
     es.caib.rfhab.model.dao.IUsuariManager __usuariManager) { 
    this.__funcionariManager = __funcionariManager;
    this.__funcionariLlocManager = __funcionariLlocManager;
    this.__llocManager = __llocManager;
    this.__usuariManager = __usuariManager;
    _validator = new FuncionariLlocValidator<FuncionariLlocJPA>();
  }

  public FuncionariLlocBeanValidator(FuncionariLlocValidator<FuncionariLlocJPA> _validator,
     es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager,
     es.caib.rfhab.model.dao.IFuncionariLlocManager __funcionariLlocManager,
     es.caib.rfhab.model.dao.ILlocManager __llocManager,
     es.caib.rfhab.model.dao.IUsuariManager __usuariManager) {
    this.__funcionariManager = __funcionariManager;
    this.__funcionariLlocManager = __funcionariLlocManager;
    this.__llocManager = __llocManager;
    this.__usuariManager = __usuariManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(FuncionariLlocJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<FuncionariLlocJPA> _bvr_ = new BeanValidatorResult<FuncionariLlocJPA>();
    _validator.validate(_bvr_, target, isNou, __funcionariManager, __funcionariLlocManager, __llocManager, __usuariManager);
    return _bvr_.getErrors();
  }
}
