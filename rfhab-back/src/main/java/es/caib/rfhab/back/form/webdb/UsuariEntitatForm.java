package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.UsuariEntitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class UsuariEntitatForm extends RFHabBaseForm {
  
  private UsuariEntitatJPA usuariEntitat;
  
  public UsuariEntitatForm() {
  }
  
  public UsuariEntitatForm(UsuariEntitatForm __toClone) {
    super(__toClone);
      this.usuariEntitat = __toClone.usuariEntitat;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
    this.listOfUsuariForUsuariID = __toClone.listOfUsuariForUsuariID;
  }
  
  public UsuariEntitatForm(UsuariEntitatJPA usuariEntitat, boolean nou) {
    super(nou);
    this.usuariEntitat = usuariEntitat;
  }
  
  public UsuariEntitatJPA getUsuariEntitat() {
    return usuariEntitat;
  }
  public void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
  }
  
  
  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  private List<StringKeyValue> listOfUsuariForUsuariID;

  public List<StringKeyValue> getListOfUsuariForUsuariID() {
    return this.listOfUsuariForUsuariID;
  }

  public void setListOfUsuariForUsuariID(List<StringKeyValue> listOfUsuariForUsuariID) {
    this.listOfUsuariForUsuariID = listOfUsuariForUsuariID;
  }



  
} // Final de Classe 
