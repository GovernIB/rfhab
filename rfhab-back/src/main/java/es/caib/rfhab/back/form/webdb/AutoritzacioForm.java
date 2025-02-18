package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.AutoritzacioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class AutoritzacioForm extends RFHabBaseForm {
  
  private AutoritzacioJPA autoritzacio;
  
  public AutoritzacioForm() {
  }
  
  public AutoritzacioForm(AutoritzacioForm __toClone) {
    super(__toClone);
      this.autoritzacio = __toClone.autoritzacio;
    this.listOfLlocForLlocID = __toClone.listOfLlocForLlocID;
    this.listOfFuncionariForFuncionariID = __toClone.listOfFuncionariForFuncionariID;
  }
  
  public AutoritzacioForm(AutoritzacioJPA autoritzacio, boolean nou) {
    super(nou);
    this.autoritzacio = autoritzacio;
  }
  
  public AutoritzacioJPA getAutoritzacio() {
    return autoritzacio;
  }
  public void setAutoritzacio(AutoritzacioJPA autoritzacio) {
    this.autoritzacio = autoritzacio;
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



  
} // Final de Classe 
