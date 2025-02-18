package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.UsuariJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class UsuariForm extends RFHabBaseForm {
  
  private UsuariJPA usuari;
  
  public UsuariForm() {
  }
  
  public UsuariForm(UsuariForm __toClone) {
    super(__toClone);
      this.usuari = __toClone.usuari;
    this.listOfIdiomaForIdiomaID = __toClone.listOfIdiomaForIdiomaID;
    this.listOfEntitatForDarreraEntitat = __toClone.listOfEntitatForDarreraEntitat;
  }
  
  public UsuariForm(UsuariJPA usuari, boolean nou) {
    super(nou);
    this.usuari = usuari;
  }
  
  public UsuariJPA getUsuari() {
    return usuari;
  }
  public void setUsuari(UsuariJPA usuari) {
    this.usuari = usuari;
  }
  
  
  private List<StringKeyValue> listOfIdiomaForIdiomaID;

  public List<StringKeyValue> getListOfIdiomaForIdiomaID() {
    return this.listOfIdiomaForIdiomaID;
  }

  public void setListOfIdiomaForIdiomaID(List<StringKeyValue> listOfIdiomaForIdiomaID) {
    this.listOfIdiomaForIdiomaID = listOfIdiomaForIdiomaID;
  }



  private List<StringKeyValue> listOfEntitatForDarreraEntitat;

  public List<StringKeyValue> getListOfEntitatForDarreraEntitat() {
    return this.listOfEntitatForDarreraEntitat;
  }

  public void setListOfEntitatForDarreraEntitat(List<StringKeyValue> listOfEntitatForDarreraEntitat) {
    this.listOfEntitatForDarreraEntitat = listOfEntitatForDarreraEntitat;
  }



  
} // Final de Classe 
