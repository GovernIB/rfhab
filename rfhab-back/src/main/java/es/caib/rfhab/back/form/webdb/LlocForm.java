package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.LlocJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class LlocForm extends RFHabBaseForm {
  
  private LlocJPA lloc;
  
  public LlocForm() {
  }
  
  public LlocForm(LlocForm __toClone) {
    super(__toClone);
      this.lloc = __toClone.lloc;
    this.listOfValuesForPersonalOamr = __toClone.listOfValuesForPersonalOamr;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
    this.listOfUnitatForUnitatID = __toClone.listOfUnitatForUnitatID;
  }
  
  public LlocForm(LlocJPA lloc, boolean nou) {
    super(nou);
    this.lloc = lloc;
  }
  
  public LlocJPA getLloc() {
    return lloc;
  }
  public void setLloc(LlocJPA lloc) {
    this.lloc = lloc;
  }
  
  
  private List<StringKeyValue> listOfValuesForPersonalOamr;

  public List<StringKeyValue> getListOfValuesForPersonalOamr() {
    return this.listOfValuesForPersonalOamr;
  }

  public void setListOfValuesForPersonalOamr(List<StringKeyValue> listOfValuesForPersonalOamr) {
    this.listOfValuesForPersonalOamr = listOfValuesForPersonalOamr;
  }



  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  private List<StringKeyValue> listOfUnitatForUnitatID;

  public List<StringKeyValue> getListOfUnitatForUnitatID() {
    return this.listOfUnitatForUnitatID;
  }

  public void setListOfUnitatForUnitatID(List<StringKeyValue> listOfUnitatForUnitatID) {
    this.listOfUnitatForUnitatID = listOfUnitatForUnitatID;
  }



  
} // Final de Classe 
