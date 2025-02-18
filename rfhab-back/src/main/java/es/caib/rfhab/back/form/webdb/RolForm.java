package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.RolJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class RolForm extends RFHabBaseForm {
  
  private RolJPA rol;
  
  public RolForm() {
  }
  
  public RolForm(RolForm __toClone) {
    super(__toClone);
      this.rol = __toClone.rol;
    this.listOfTraduccioForNomID = __toClone.listOfTraduccioForNomID;
  }
  
  public RolForm(RolJPA rol, boolean nou) {
    super(nou);
    this.rol = rol;
  }
  
  public RolJPA getRol() {
    return rol;
  }
  public void setRol(RolJPA rol) {
    this.rol = rol;
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
