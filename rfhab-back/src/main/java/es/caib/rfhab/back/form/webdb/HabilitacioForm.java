package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.HabilitacioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class HabilitacioForm extends RFHabBaseForm {
  
  private HabilitacioJPA habilitacio;
  
  public HabilitacioForm() {
  }
  
  public HabilitacioForm(HabilitacioForm __toClone) {
    super(__toClone);
      this.habilitacio = __toClone.habilitacio;
    this.listOfTraduccioForNomID = __toClone.listOfTraduccioForNomID;
  }
  
  public HabilitacioForm(HabilitacioJPA habilitacio, boolean nou) {
    super(nou);
    this.habilitacio = habilitacio;
  }
  
  public HabilitacioJPA getHabilitacio() {
    return habilitacio;
  }
  public void setHabilitacio(HabilitacioJPA habilitacio) {
    this.habilitacio = habilitacio;
  }
  
  java.util.List<es.caib.rfhab.model.entity.Idioma> idiomesTraduccio = null;

  public java.util.List<es.caib.rfhab.model.entity.Idioma> getIdiomesTraduccio() {
    return idiomesTraduccio;
  }

  public void setIdiomesTraduccio(java.util.List<es.caib.rfhab.model.entity.Idioma> idiomesTraduccio) {
    this.idiomesTraduccio = idiomesTraduccio;
  }
  
  private List<StringKeyValue> listOfTraduccioForNomID;

  public List<StringKeyValue> getListOfTraduccioForNomID() {
    return this.listOfTraduccioForNomID;
  }

  public void setListOfTraduccioForNomID(List<StringKeyValue> listOfTraduccioForNomID) {
    this.listOfTraduccioForNomID = listOfTraduccioForNomID;
  }



  
} // Final de Classe 
