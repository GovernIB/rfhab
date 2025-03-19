package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.LlocRolJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class LlocRolForm extends RFHabBaseForm {
  
  private LlocRolJPA llocRol;
  
  public LlocRolForm() {
  }
  
  public LlocRolForm(LlocRolForm __toClone) {
    super(__toClone);
      this.llocRol = __toClone.llocRol;
    this.listOfLlocForLlocID = __toClone.listOfLlocForLlocID;
    this.listOfRolForRolID = __toClone.listOfRolForRolID;
  }
  
  public LlocRolForm(LlocRolJPA llocRol, boolean nou) {
    super(nou);
    this.llocRol = llocRol;
  }
  
  public LlocRolJPA getLlocRol() {
    return llocRol;
  }
  public void setLlocRol(LlocRolJPA llocRol) {
    this.llocRol = llocRol;
  }
  
  
  private List<StringKeyValue> listOfLlocForLlocID;

  public List<StringKeyValue> getListOfLlocForLlocID() {
    return this.listOfLlocForLlocID;
  }

  public void setListOfLlocForLlocID(List<StringKeyValue> listOfLlocForLlocID) {
    this.listOfLlocForLlocID = listOfLlocForLlocID;
  }



  private List<StringKeyValue> listOfRolForRolID;

  public List<StringKeyValue> getListOfRolForRolID() {
    return this.listOfRolForRolID;
  }

  public void setListOfRolForRolID(List<StringKeyValue> listOfRolForRolID) {
    this.listOfRolForRolID = listOfRolForRolID;
  }



  
} // Final de Classe 
