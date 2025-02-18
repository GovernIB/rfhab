package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.HistoricLlocJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class HistoricLlocForm extends RFHabBaseForm {
  
  private HistoricLlocJPA historicLloc;
  
  public HistoricLlocForm() {
  }
  
  public HistoricLlocForm(HistoricLlocForm __toClone) {
    super(__toClone);
      this.historicLloc = __toClone.historicLloc;
    this.listOfLlocForLlocID = __toClone.listOfLlocForLlocID;
    this.listOfUsuariForUsuariID = __toClone.listOfUsuariForUsuariID;
  }
  
  public HistoricLlocForm(HistoricLlocJPA historicLloc, boolean nou) {
    super(nou);
    this.historicLloc = historicLloc;
  }
  
  public HistoricLlocJPA getHistoricLloc() {
    return historicLloc;
  }
  public void setHistoricLloc(HistoricLlocJPA historicLloc) {
    this.historicLloc = historicLloc;
  }
  
  
  private List<StringKeyValue> listOfLlocForLlocID;

  public List<StringKeyValue> getListOfLlocForLlocID() {
    return this.listOfLlocForLlocID;
  }

  public void setListOfLlocForLlocID(List<StringKeyValue> listOfLlocForLlocID) {
    this.listOfLlocForLlocID = listOfLlocForLlocID;
  }



  private List<StringKeyValue> listOfUsuariForUsuariID;

  public List<StringKeyValue> getListOfUsuariForUsuariID() {
    return this.listOfUsuariForUsuariID;
  }

  public void setListOfUsuariForUsuariID(List<StringKeyValue> listOfUsuariForUsuariID) {
    this.listOfUsuariForUsuariID = listOfUsuariForUsuariID;
  }



  
} // Final de Classe 
