package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.FuncionariJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class FuncionariForm extends RFHabBaseForm {
  
  private FuncionariJPA funcionari;
  
  public FuncionariForm() {
  }
  
  public FuncionariForm(FuncionariForm __toClone) {
    super(__toClone);
      this.funcionari = __toClone.funcionari;
    this.listOfValuesForTipusIdentificador = __toClone.listOfValuesForTipusIdentificador;
  }
  
  public FuncionariForm(FuncionariJPA funcionari, boolean nou) {
    super(nou);
    this.funcionari = funcionari;
  }
  
  public FuncionariJPA getFuncionari() {
    return funcionari;
  }
  public void setFuncionari(FuncionariJPA funcionari) {
    this.funcionari = funcionari;
  }
  
  
  private List<StringKeyValue> listOfValuesForTipusIdentificador;

  public List<StringKeyValue> getListOfValuesForTipusIdentificador() {
    return this.listOfValuesForTipusIdentificador;
  }

  public void setListOfValuesForTipusIdentificador(List<StringKeyValue> listOfValuesForTipusIdentificador) {
    this.listOfValuesForTipusIdentificador = listOfValuesForTipusIdentificador;
  }



  
} // Final de Classe 
