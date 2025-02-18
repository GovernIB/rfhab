package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.FuncionariRolJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class FuncionariRolForm extends RFHabBaseForm {
  
  private FuncionariRolJPA funcionariRol;
  
  public FuncionariRolForm() {
  }
  
  public FuncionariRolForm(FuncionariRolForm __toClone) {
    super(__toClone);
      this.funcionariRol = __toClone.funcionariRol;
    this.listOfFuncionariForFuncionariID = __toClone.listOfFuncionariForFuncionariID;
    this.listOfRolForRolID = __toClone.listOfRolForRolID;
  }
  
  public FuncionariRolForm(FuncionariRolJPA funcionariRol, boolean nou) {
    super(nou);
    this.funcionariRol = funcionariRol;
  }
  
  public FuncionariRolJPA getFuncionariRol() {
    return funcionariRol;
  }
  public void setFuncionariRol(FuncionariRolJPA funcionariRol) {
    this.funcionariRol = funcionariRol;
  }
  
  
  private List<StringKeyValue> listOfFuncionariForFuncionariID;

  public List<StringKeyValue> getListOfFuncionariForFuncionariID() {
    return this.listOfFuncionariForFuncionariID;
  }

  public void setListOfFuncionariForFuncionariID(List<StringKeyValue> listOfFuncionariForFuncionariID) {
    this.listOfFuncionariForFuncionariID = listOfFuncionariForFuncionariID;
  }



  private List<StringKeyValue> listOfRolForRolID;

  public List<StringKeyValue> getListOfRolForRolID() {
    return this.listOfRolForRolID;
  }

  public void setListOfRolForRolID(List<StringKeyValue> listOfRolForRolID) {
    this.listOfRolForRolID = listOfRolForRolID;
  }



  
} // Final de Classe 
