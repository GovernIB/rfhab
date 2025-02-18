package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.HistoricJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class HistoricForm extends RFHabBaseForm {
  
  private HistoricJPA historic;
  
  public HistoricForm() {
  }
  
  public HistoricForm(HistoricForm __toClone) {
    super(__toClone);
      this.historic = __toClone.historic;
    this.listOfFuncionariForFuncionariID = __toClone.listOfFuncionariForFuncionariID;
    this.listOfUsuariForUsuariID = __toClone.listOfUsuariForUsuariID;
  }
  
  public HistoricForm(HistoricJPA historic, boolean nou) {
    super(nou);
    this.historic = historic;
  }
  
  public HistoricJPA getHistoric() {
    return historic;
  }
  public void setHistoric(HistoricJPA historic) {
    this.historic = historic;
  }
  
  
  private List<StringKeyValue> listOfFuncionariForFuncionariID;

  public List<StringKeyValue> getListOfFuncionariForFuncionariID() {
    return this.listOfFuncionariForFuncionariID;
  }

  public void setListOfFuncionariForFuncionariID(List<StringKeyValue> listOfFuncionariForFuncionariID) {
    this.listOfFuncionariForFuncionariID = listOfFuncionariForFuncionariID;
  }



  private List<StringKeyValue> listOfUsuariForUsuariID;

  public List<StringKeyValue> getListOfUsuariForUsuariID() {
    return this.listOfUsuariForUsuariID;
  }

  public void setListOfUsuariForUsuariID(List<StringKeyValue> listOfUsuariForUsuariID) {
    this.listOfUsuariForUsuariID = listOfUsuariForUsuariID;
  }



  
} // Final de Classe 
