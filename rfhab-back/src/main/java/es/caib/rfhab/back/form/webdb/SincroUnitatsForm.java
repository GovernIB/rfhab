package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.SincroUnitatsJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class SincroUnitatsForm extends RFHabBaseForm {
  
  private SincroUnitatsJPA sincroUnitats;
  
  public SincroUnitatsForm() {
  }
  
  public SincroUnitatsForm(SincroUnitatsForm __toClone) {
    super(__toClone);
      this.sincroUnitats = __toClone.sincroUnitats;
    this.listOfUsuariForUsuariId = __toClone.listOfUsuariForUsuariId;
  }
  
  public SincroUnitatsForm(SincroUnitatsJPA sincroUnitats, boolean nou) {
    super(nou);
    this.sincroUnitats = sincroUnitats;
  }
  
  public SincroUnitatsJPA getSincroUnitats() {
    return sincroUnitats;
  }
  public void setSincroUnitats(SincroUnitatsJPA sincroUnitats) {
    this.sincroUnitats = sincroUnitats;
  }
  
  
  private List<StringKeyValue> listOfUsuariForUsuariId;

  public List<StringKeyValue> getListOfUsuariForUsuariId() {
    return this.listOfUsuariForUsuariId;
  }

  public void setListOfUsuariForUsuariId(List<StringKeyValue> listOfUsuariForUsuariId) {
    this.listOfUsuariForUsuariId = listOfUsuariForUsuariId;
  }



  
} // Final de Classe 
