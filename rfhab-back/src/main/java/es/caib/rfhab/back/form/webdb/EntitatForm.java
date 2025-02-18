package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.EntitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EntitatForm extends RFHabBaseForm {
  
  private EntitatJPA entitat;
  
  public EntitatForm() {
  }
  
  public EntitatForm(EntitatForm __toClone) {
    super(__toClone);
      this.entitat = __toClone.entitat;
    this.listOfUnitatForUnitatID = __toClone.listOfUnitatForUnitatID;
  }
  
  public EntitatForm(EntitatJPA entitat, boolean nou) {
    super(nou);
    this.entitat = entitat;
  }
  
  public EntitatJPA getEntitat() {
    return entitat;
  }
  public void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }
  
  
  private List<StringKeyValue> listOfUnitatForUnitatID;

  public List<StringKeyValue> getListOfUnitatForUnitatID() {
    return this.listOfUnitatForUnitatID;
  }

  public void setListOfUnitatForUnitatID(List<StringKeyValue> listOfUnitatForUnitatID) {
    this.listOfUnitatForUnitatID = listOfUnitatForUnitatID;
  }



  
} // Final de Classe 
