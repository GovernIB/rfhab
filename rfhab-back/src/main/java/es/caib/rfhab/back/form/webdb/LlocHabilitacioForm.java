package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.LlocHabilitacioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class LlocHabilitacioForm extends RFHabBaseForm {
  
  private LlocHabilitacioJPA llocHabilitacio;
  
  public LlocHabilitacioForm() {
  }
  
  public LlocHabilitacioForm(LlocHabilitacioForm __toClone) {
    super(__toClone);
      this.llocHabilitacio = __toClone.llocHabilitacio;
    this.listOfLlocForLlocID = __toClone.listOfLlocForLlocID;
    this.listOfHabilitacioForHabilitacioId = __toClone.listOfHabilitacioForHabilitacioId;
  }
  
  public LlocHabilitacioForm(LlocHabilitacioJPA llocHabilitacio, boolean nou) {
    super(nou);
    this.llocHabilitacio = llocHabilitacio;
  }
  
  public LlocHabilitacioJPA getLlocHabilitacio() {
    return llocHabilitacio;
  }
  public void setLlocHabilitacio(LlocHabilitacioJPA llocHabilitacio) {
    this.llocHabilitacio = llocHabilitacio;
  }
  
  
  private List<StringKeyValue> listOfLlocForLlocID;

  public List<StringKeyValue> getListOfLlocForLlocID() {
    return this.listOfLlocForLlocID;
  }

  public void setListOfLlocForLlocID(List<StringKeyValue> listOfLlocForLlocID) {
    this.listOfLlocForLlocID = listOfLlocForLlocID;
  }



  private List<StringKeyValue> listOfHabilitacioForHabilitacioId;

  public List<StringKeyValue> getListOfHabilitacioForHabilitacioId() {
    return this.listOfHabilitacioForHabilitacioId;
  }

  public void setListOfHabilitacioForHabilitacioId(List<StringKeyValue> listOfHabilitacioForHabilitacioId) {
    this.listOfHabilitacioForHabilitacioId = listOfHabilitacioForHabilitacioId;
  }



  
} // Final de Classe 
