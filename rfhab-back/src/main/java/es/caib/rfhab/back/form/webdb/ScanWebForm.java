package es.caib.rfhab.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.rfhab.back.form.RFHabBaseForm;
import es.caib.rfhab.persistence.ScanWebJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class ScanWebForm extends RFHabBaseForm {
  
  private ScanWebJPA scanWeb;
  
  
  private CommonsMultipartFile fitxerID;
  private boolean fitxerIDDelete;
  
  public ScanWebForm() {
  }
  
  public ScanWebForm(ScanWebForm __toClone) {
    super(__toClone);
      this.scanWeb = __toClone.scanWeb;
    this.listOfUsuariForUsuariID = __toClone.listOfUsuariForUsuariID;
  }
  
  public ScanWebForm(ScanWebJPA scanWeb, boolean nou) {
    super(nou);
    this.scanWeb = scanWeb;
  }
  
  public ScanWebJPA getScanWeb() {
    return scanWeb;
  }
  public void setScanWeb(ScanWebJPA scanWeb) {
    this.scanWeb = scanWeb;
  }
  
  
  public CommonsMultipartFile getFitxerID() {
    return fitxerID;
  }
  
   public void setFitxerID(CommonsMultipartFile fitxerID) {
    this.fitxerID = fitxerID;
  }
  public boolean isFitxerIDDelete() {
    return fitxerIDDelete;
  }
  
  public void setFitxerIDDelete(boolean fitxerIDDelete) {
    this.fitxerIDDelete = fitxerIDDelete;
   }
  private List<StringKeyValue> listOfUsuariForUsuariID;

  public List<StringKeyValue> getListOfUsuariForUsuariID() {
    return this.listOfUsuariForUsuariID;
  }

  public void setListOfUsuariForUsuariID(List<StringKeyValue> listOfUsuariForUsuariID) {
    this.listOfUsuariForUsuariID = listOfUsuariForUsuariID;
  }



  
} // Final de Classe 
