
package es.caib.rfhab.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.rfhab.back.form.RFHabBaseFilterForm;

import es.caib.rfhab.model.fields.ScanWebFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class ScanWebFilterForm extends RFHabBaseFilterForm implements ScanWebFields {

  private java.lang.Long digitalIDDesde;

  public java.lang.Long getDigitalIDDesde() {
    return this.digitalIDDesde;
  }

  public void setDigitalIDDesde(java.lang.Long digitalIDDesde) {
    this.digitalIDDesde = digitalIDDesde;
  }


  private java.lang.Long digitalIDFins;

  public java.lang.Long getDigitalIDFins() {
    return this.digitalIDFins;
  }

  public void setDigitalIDFins(java.lang.Long digitalIDFins) {
    this.digitalIDFins = digitalIDFins;
  }


  private java.lang.Long transactionIDDesde;

  public java.lang.Long getTransactionIDDesde() {
    return this.transactionIDDesde;
  }

  public void setTransactionIDDesde(java.lang.Long transactionIDDesde) {
    this.transactionIDDesde = transactionIDDesde;
  }


  private java.lang.Long transactionIDFins;

  public java.lang.Long getTransactionIDFins() {
    return this.transactionIDFins;
  }

  public void setTransactionIDFins(java.lang.Long transactionIDFins) {
    this.transactionIDFins = transactionIDFins;
  }


  private java.lang.String transactionWebID;

  public java.lang.String getTransactionWebID() {
    return this.transactionWebID;
  }

  public void setTransactionWebID(java.lang.String transactionWebID) {
    this.transactionWebID = transactionWebID;
  }


  private java.lang.Long statusDesde;

  public java.lang.Long getStatusDesde() {
    return this.statusDesde;
  }

  public void setStatusDesde(java.lang.Long statusDesde) {
    this.statusDesde = statusDesde;
  }


  private java.lang.Long statusFins;

  public java.lang.Long getStatusFins() {
    return this.statusFins;
  }

  public void setStatusFins(java.lang.Long statusFins) {
    this.statusFins = statusFins;
  }


  private java.lang.String fileInfo;

  public java.lang.String getFileInfo() {
    return this.fileInfo;
  }

  public void setFileInfo(java.lang.String fileInfo) {
    this.fileInfo = fileInfo;
  }


  private java.lang.String signedFileInfo;

  public java.lang.String getSignedFileInfo() {
    return this.signedFileInfo;
  }

  public void setSignedFileInfo(java.lang.String signedFileInfo) {
    this.signedFileInfo = signedFileInfo;
  }


  private java.lang.String metadades;

  public java.lang.String getMetadades() {
    return this.metadades;
  }

  public void setMetadades(java.lang.String metadades) {
    this.metadades = metadades;
  }


  private java.lang.String missatge;

  public java.lang.String getMissatge() {
    return this.missatge;
  }

  public void setMissatge(java.lang.String missatge) {
    this.missatge = missatge;
  }


  private java.lang.Long usuariIDDesde;

  public java.lang.Long getUsuariIDDesde() {
    return this.usuariIDDesde;
  }

  public void setUsuariIDDesde(java.lang.Long usuariIDDesde) {
    this.usuariIDDesde = usuariIDDesde;
  }


  private java.lang.Long usuariIDFins;

  public java.lang.Long getUsuariIDFins() {
    return this.usuariIDFins;
  }

  public void setUsuariIDFins(java.lang.Long usuariIDFins) {
    this.usuariIDFins = usuariIDFins;
  }


  private java.sql.Timestamp dataCreacioDesde;

  public java.sql.Timestamp getDataCreacioDesde() {
    return this.dataCreacioDesde;
  }

  public void setDataCreacioDesde(java.sql.Timestamp dataCreacioDesde) {
    this.dataCreacioDesde = dataCreacioDesde;
  }


  private java.sql.Timestamp dataCreacioFins;

  public java.sql.Timestamp getDataCreacioFins() {
    return this.dataCreacioFins;
  }

  public void setDataCreacioFins(java.sql.Timestamp dataCreacioFins) {
    this.dataCreacioFins = dataCreacioFins;
  }


  private java.lang.Long entitatIDDesde;

  public java.lang.Long getEntitatIDDesde() {
    return this.entitatIDDesde;
  }

  public void setEntitatIDDesde(java.lang.Long entitatIDDesde) {
    this.entitatIDDesde = entitatIDDesde;
  }


  private java.lang.Long entitatIDFins;

  public java.lang.Long getEntitatIDFins() {
    return this.entitatIDFins;
  }

  public void setEntitatIDFins(java.lang.Long entitatIDFins) {
    this.entitatIDFins = entitatIDFins;
  }


  public ScanWebFilterForm() {
  }
  
  public ScanWebFilterForm(ScanWebFilterForm __toClone) {
    super(__toClone);
    this.digitalIDDesde = __toClone.digitalIDDesde;
    this.digitalIDFins = __toClone.digitalIDFins;
    this.transactionIDDesde = __toClone.transactionIDDesde;
    this.transactionIDFins = __toClone.transactionIDFins;
    this.transactionWebID = __toClone.transactionWebID;
    this.statusDesde = __toClone.statusDesde;
    this.statusFins = __toClone.statusFins;
    this.fileInfo = __toClone.fileInfo;
    this.signedFileInfo = __toClone.signedFileInfo;
    this.metadades = __toClone.metadades;
    this.missatge = __toClone.missatge;
    this.usuariIDDesde = __toClone.usuariIDDesde;
    this.usuariIDFins = __toClone.usuariIDFins;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.mapOfUsuariForUsuariID = __toClone.mapOfUsuariForUsuariID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }


  protected OrderBy[] defaultOrderBy = null;


  public OrderBy[] getDefaultOrderBy() {
    return this.defaultOrderBy;
  }

  public void setDefaultOrderBy(OrderBy[] defOrderBy) {
    this.defaultOrderBy = defOrderBy;
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

   // -----------------------
   // Maps de referencies.
   // -----------------------
  private Map<String, String> mapOfUsuariForUsuariID;

  public Map<String, String> getMapOfUsuariForUsuariID() {
    return this.mapOfUsuariForUsuariID;
  }

  public void setMapOfUsuariForUsuariID(Map<String, String> mapOfUsuariForUsuariID) {
    this.mapOfUsuariForUsuariID = mapOfUsuariForUsuariID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
