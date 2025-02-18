package es.caib.rfhab.back.form.webdb;

import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.TraduccioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TraduccioForm extends RFHabBaseForm {
  
  private TraduccioJPA traduccio;
  
  public TraduccioForm() {
  }
  
  public TraduccioForm(TraduccioForm __toClone) {
    super(__toClone);
      this.traduccio = __toClone.traduccio;
  }
  
  public TraduccioForm(TraduccioJPA traduccio, boolean nou) {
    super(nou);
    this.traduccio = traduccio;
  }
  
  public TraduccioJPA getTraduccio() {
    return traduccio;
  }
  public void setTraduccio(TraduccioJPA traduccio) {
    this.traduccio = traduccio;
  }
  
  
  
} // Final de Classe 
