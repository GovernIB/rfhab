package es.caib.rfhab.persistence.validator;

import es.caib.rfhab.persistence.AutoritzacioJPA;
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
public class AutoritzacioBeanValidator 
      extends AbstractBeanValidator<AutoritzacioJPA> {


  // EJB's
  protected final es.caib.rfhab.model.dao.IAutoritzacioManager __autoritzacioManager;

  protected final es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager;

  protected final es.caib.rfhab.model.dao.ILlocManager __llocManager;


  public final AutoritzacioValidator<AutoritzacioJPA> _validator;


  public AutoritzacioBeanValidator(es.caib.rfhab.model.dao.IAutoritzacioManager __autoritzacioManager,
     es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager,
     es.caib.rfhab.model.dao.ILlocManager __llocManager) { 
    this.__autoritzacioManager = __autoritzacioManager;
    this.__funcionariManager = __funcionariManager;
    this.__llocManager = __llocManager;
    _validator = new AutoritzacioValidator<AutoritzacioJPA>();
  }

  public AutoritzacioBeanValidator(AutoritzacioValidator<AutoritzacioJPA> _validator,
     es.caib.rfhab.model.dao.IAutoritzacioManager __autoritzacioManager,
     es.caib.rfhab.model.dao.IFuncionariManager __funcionariManager,
     es.caib.rfhab.model.dao.ILlocManager __llocManager) {
    this.__autoritzacioManager = __autoritzacioManager;
    this.__funcionariManager = __funcionariManager;
    this.__llocManager = __llocManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(AutoritzacioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<AutoritzacioJPA> _bvr_ = new BeanValidatorResult<AutoritzacioJPA>();
    _validator.validate(_bvr_, target, isNou, __autoritzacioManager, __funcionariManager, __llocManager);
    return _bvr_.getErrors();
  }
}
