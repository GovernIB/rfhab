package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.FuncionariLlocJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class FuncionariLlocForm extends RFHabBaseForm {
  
  private FuncionariLlocJPA funcionariLloc;
  
  public FuncionariLlocForm() {
  }
  
  public FuncionariLlocForm(FuncionariLlocForm __toClone) {
    super(__toClone);
      this.funcionariLloc = __toClone.funcionariLloc;
    this.listOfLlocForLlocID = __toClone.listOfLlocForLlocID;
    this.listOfFuncionariForFuncionariID = __toClone.listOfFuncionariForFuncionariID;
    this.listOfUsuariForUsuariID = __toClone.listOfUsuariForUsuariID;
  }
  
  public FuncionariLlocForm(FuncionariLlocJPA funcionariLloc, boolean nou) {
    super(nou);
    this.funcionariLloc = funcionariLloc;
  }
  
  public FuncionariLlocJPA getFuncionariLloc() {
    return funcionariLloc;
  }
  public void setFuncionariLloc(FuncionariLlocJPA funcionariLloc) {
    this.funcionariLloc = funcionariLloc;
  }
  
  
  private List<StringKeyValue> listOfLlocForLlocID;

  public List<StringKeyValue> getListOfLlocForLlocID() {
    return this.listOfLlocForLlocID;
  }

  public void setListOfLlocForLlocID(List<StringKeyValue> listOfLlocForLlocID) {
    this.listOfLlocForLlocID = listOfLlocForLlocID;
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
