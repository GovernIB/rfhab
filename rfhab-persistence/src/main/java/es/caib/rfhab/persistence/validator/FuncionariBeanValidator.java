package es.caib.rfhab.persistence.validator;

import es.caib.rfhab.persistence.FuncionariJPA;
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
public class FuncionariBeanValidator 
      extends AbstractBeanValidator<FuncionariJPA> {


  // EJB's
  protected final es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager;


  public final FuncionariValidator<FuncionariJPA> _validator;


  public FuncionariBeanValidator(es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager) { 
    this.__funcionariManager = __funcionariManager;
    _validator = new FuncionariValidator<FuncionariJPA>();
  }

  public FuncionariBeanValidator(FuncionariValidator<FuncionariJPA> _validator,
     es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager) {
    this.__funcionariManager = __funcionariManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(FuncionariJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<FuncionariJPA> _bvr_ = new BeanValidatorResult<FuncionariJPA>();
    _validator.validate(_bvr_, target, isNou, __funcionariManager);
    return _bvr_.getErrors();
  }
}
