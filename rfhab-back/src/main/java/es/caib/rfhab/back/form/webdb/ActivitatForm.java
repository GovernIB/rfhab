package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.ActivitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class ActivitatForm extends RFHabBaseForm {
  
  private ActivitatJPA activitat;
  
  public ActivitatForm() {
  }
  
  public ActivitatForm(ActivitatForm __toClone) {
    super(__toClone);
      this.activitat = __toClone.activitat;
    this.listOfFuncionariForFuncionariID = __toClone.listOfFuncionariForFuncionariID;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
    this.listOfValuesForEstat = __toClone.listOfValuesForEstat;
  }
  
  public ActivitatForm(ActivitatJPA activitat, boolean nou) {
    super(nou);
    this.activitat = activitat;
  }
  
  public ActivitatJPA getActivitat() {
    return activitat;
  }
  public void setActivitat(ActivitatJPA activitat) {
    this.activitat = activitat;
  }
  
  
  private List<StringKeyValue> listOfFuncionariForFuncionariID;

  public List<StringKeyValue> getListOfFuncionariForFuncionariID() {
    return this.listOfFuncionariForFuncionariID;
  }

  public void setListOfFuncionariForFuncionariID(List<StringKeyValue> listOfFuncionariForFuncionariID) {
    this.listOfFuncionariForFuncionariID = listOfFuncionariForFuncionariID;
  }



  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  private List<StringKeyValue> listOfValuesForEstat;

  public List<StringKeyValue> getListOfValuesForEstat() {
    return this.listOfValuesForEstat;
  }

  public void setListOfValuesForEstat(List<StringKeyValue> listOfValuesForEstat) {
    this.listOfValuesForEstat = listOfValuesForEstat;
  }



  
} // Final de Classe 
