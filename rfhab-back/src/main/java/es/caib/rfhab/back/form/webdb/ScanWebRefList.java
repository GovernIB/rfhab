
package es.caib.rfhab.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.rfhab.ejb.ScanWebService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.rfhab.model.fields.ScanWebFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class ScanWebRefList extends RefListBase
    implements ScanWebFields {

  @EJB(mappedName = ScanWebService.JNDI_NAME)
  private ScanWebService scanWebEjb;

  public ScanWebRefList(ScanWebRefList __clone) {
    super(__clone);
    this.scanWebEjb = __clone.scanWebEjb;
  }
  public ScanWebRefList() {
    setSelects(new Select<?>[] { DIGITALID.select, TRANSACTIONID.select, TRANSACTIONWEBID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = scanWebEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
