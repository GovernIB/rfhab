package es.caib.rfhab.persistence.validator;

import es.caib.rfhab.persistence.FuncionariRolJPA;
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
public class FuncionariRolBeanValidator 
      extends AbstractBeanValidator<FuncionariRolJPA> {


  // EJB's
  protected final es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager;

  protected final es.caib.rfhab.model.dao.IFuncionariRolManager __funcionariRolManager;

  protected final es.caib.rfhab.model.dao.IRolManager __rolManager;


  public final FuncionariRolValidator<FuncionariRolJPA> _validator;


  public FuncionariRolBeanValidator(es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager,
     es.caib.rfhab.model.dao.IFuncionariRolManager __funcionariRolManager,
     es.caib.rfhab.model.dao.IRolManager __rolManager) { 
    this.__funcionariManager = __funcionariManager;
    this.__funcionariRolManager = __funcionariRolManager;
    this.__rolManager = __rolManager;
    _validator = new FuncionariRolValidator<FuncionariRolJPA>();
  }

  public FuncionariRolBeanValidator(FuncionariRolValidator<FuncionariRolJPA> _validator,
     es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager,
     es.caib.rfhab.model.dao.IFuncionariRolManager __funcionariRolManager,
     es.caib.rfhab.model.dao.IRolManager __rolManager) {
    this.__funcionariManager = __funcionariManager;
    this.__funcionariRolManager = __funcionariRolManager;
    this.__rolManager = __rolManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(FuncionariRolJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<FuncionariRolJPA> _bvr_ = new BeanValidatorResult<FuncionariRolJPA>();
    _validator.validate(_bvr_, target, isNou, __funcionariManager, __funcionariRolManager, __rolManager);
    return _bvr_.getErrors();
  }
}
