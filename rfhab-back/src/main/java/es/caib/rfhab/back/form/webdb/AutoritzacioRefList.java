
package es.caib.rfhab.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.rfhab.ejb.AutoritzacioService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.rfhab.model.fields.AutoritzacioFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class AutoritzacioRefList extends RefListBase
    implements AutoritzacioFields {

  @EJB(mappedName = AutoritzacioService.JNDI_NAME)
  private AutoritzacioService autoritzacioEjb;

  public AutoritzacioRefList(AutoritzacioRefList __clone) {
    super(__clone);
    this.autoritzacioEjb = __clone.autoritzacioEjb;
  }
  public AutoritzacioRefList() {
    setSelects(new Select<?>[] { CODISIA.select, CAI.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = autoritzacioEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
