package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.UnitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class UnitatForm extends RFHabBaseForm {
  
  private UnitatJPA unitat;
  
  public UnitatForm() {
  }
  
  public UnitatForm(UnitatForm __toClone) {
    super(__toClone);
      this.unitat = __toClone.unitat;
    this.listOfValuesForEstat = __toClone.listOfValuesForEstat;
  }
  
  public UnitatForm(UnitatJPA unitat, boolean nou) {
    super(nou);
    this.unitat = unitat;
  }
  
  public UnitatJPA getUnitat() {
    return unitat;
  }
  public void setUnitat(UnitatJPA unitat) {
    this.unitat = unitat;
  }
  
  
  private List<StringKeyValue> listOfValuesForEstat;

  public List<StringKeyValue> getListOfValuesForEstat() {
    return this.listOfValuesForEstat;
  }

  public void setListOfValuesForEstat(List<StringKeyValue> listOfValuesForEstat) {
    this.listOfValuesForEstat = listOfValuesForEstat;
  }



  
} // Final de Classe 
