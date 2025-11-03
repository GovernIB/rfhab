
package es.caib.rfhab.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.rfhab.back.form.RFHabBaseFilterForm;

import es.caib.rfhab.model.fields.ActivitatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class ActivitatFilterForm extends RFHabBaseFilterForm implements ActivitatFields {

  private java.lang.Long activitatIDDesde;

  public java.lang.Long getActivitatIDDesde() {
    return this.activitatIDDesde;
  }

  public void setActivitatIDDesde(java.lang.Long activitatIDDesde) {
    this.activitatIDDesde = activitatIDDesde;
  }


  private java.lang.Long activitatIDFins;

  public java.lang.Long getActivitatIDFins() {
    return this.activitatIDFins;
  }

  public void setActivitatIDFins(java.lang.Long activitatIDFins) {
    this.activitatIDFins = activitatIDFins;
  }


  private java.lang.Long funcionariIDDesde;

  public java.lang.Long getFuncionariIDDesde() {
    return this.funcionariIDDesde;
  }

  public void setFuncionariIDDesde(java.lang.Long funcionariIDDesde) {
    this.funcionariIDDesde = funcionariIDDesde;
  }


  private java.lang.Long funcionariIDFins;

  public java.lang.Long getFuncionariIDFins() {
    return this.funcionariIDFins;
  }

  public void setFuncionariIDFins(java.lang.Long funcionariIDFins) {
    this.funcionariIDFins = funcionariIDFins;
  }


  private java.util.List<java.lang.Integer> tipusSelect;

  public java.util.List<java.lang.Integer> getTipusSelect() {
    return this.tipusSelect;
  }

  public void setTipusSelect(java.util.List<java.lang.Integer> tipusSelect) {
    this.tipusSelect = tipusSelect;
  }


  private java.lang.String registre;

  public java.lang.String getRegistre() {
    return this.registre;
  }

  public void setRegistre(java.lang.String registre) {
    this.registre = registre;
  }


  private java.lang.String tramit;

  public java.lang.String getTramit() {
    return this.tramit;
  }

  public void setTramit(java.lang.String tramit) {
    this.tramit = tramit;
  }


  private java.lang.String codiSia;

  public java.lang.String getCodiSia() {
    return this.codiSia;
  }

  public void setCodiSia(java.lang.String codiSia) {
    this.codiSia = codiSia;
  }


  private java.lang.Long autoritzacioIDDesde;

  public java.lang.Long getAutoritzacioIDDesde() {
    return this.autoritzacioIDDesde;
  }

  public void setAutoritzacioIDDesde(java.lang.Long autoritzacioIDDesde) {
    this.autoritzacioIDDesde = autoritzacioIDDesde;
  }


  private java.lang.Long autoritzacioIDFins;

  public java.lang.Long getAutoritzacioIDFins() {
    return this.autoritzacioIDFins;
  }

  public void setAutoritzacioIDFins(java.lang.Long autoritzacioIDFins) {
    this.autoritzacioIDFins = autoritzacioIDFins;
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


  private java.lang.String interessatNom;

  public java.lang.String getInteressatNom() {
    return this.interessatNom;
  }

  public void setInteressatNom(java.lang.String interessatNom) {
    this.interessatNom = interessatNom;
  }


  private java.lang.String interessatLlinatge1;

  public java.lang.String getInteressatLlinatge1() {
    return this.interessatLlinatge1;
  }

  public void setInteressatLlinatge1(java.lang.String interessatLlinatge1) {
    this.interessatLlinatge1 = interessatLlinatge1;
  }


  private java.lang.String interessatLlinatge2;

  public java.lang.String getInteressatLlinatge2() {
    return this.interessatLlinatge2;
  }

  public void setInteressatLlinatge2(java.lang.String interessatLlinatge2) {
    this.interessatLlinatge2 = interessatLlinatge2;
  }


  private java.lang.Integer interessatTipusDesde;

  public java.lang.Integer getInteressatTipusDesde() {
    return this.interessatTipusDesde;
  }

  public void setInteressatTipusDesde(java.lang.Integer interessatTipusDesde) {
    this.interessatTipusDesde = interessatTipusDesde;
  }


  private java.lang.Integer interessatTipusFins;

  public java.lang.Integer getInteressatTipusFins() {
    return this.interessatTipusFins;
  }

  public void setInteressatTipusFins(java.lang.Integer interessatTipusFins) {
    this.interessatTipusFins = interessatTipusFins;
  }


  private java.lang.String interessatIdentificacio;

  public java.lang.String getInteressatIdentificacio() {
    return this.interessatIdentificacio;
  }

  public void setInteressatIdentificacio(java.lang.String interessatIdentificacio) {
    this.interessatIdentificacio = interessatIdentificacio;
  }


  private java.lang.String representantNom;

  public java.lang.String getRepresentantNom() {
    return this.representantNom;
  }

  public void setRepresentantNom(java.lang.String representantNom) {
    this.representantNom = representantNom;
  }


  private java.lang.String representantLlinatge1;

  public java.lang.String getRepresentantLlinatge1() {
    return this.representantLlinatge1;
  }

  public void setRepresentantLlinatge1(java.lang.String representantLlinatge1) {
    this.representantLlinatge1 = representantLlinatge1;
  }


  private java.lang.String representantLlinatge2;

  public java.lang.String getRepresentantLlinatge2() {
    return this.representantLlinatge2;
  }

  public void setRepresentantLlinatge2(java.lang.String representantLlinatge2) {
    this.representantLlinatge2 = representantLlinatge2;
  }


  private java.lang.Integer representantTipusDesde;

  public java.lang.Integer getRepresentantTipusDesde() {
    return this.representantTipusDesde;
  }

  public void setRepresentantTipusDesde(java.lang.Integer representantTipusDesde) {
    this.representantTipusDesde = representantTipusDesde;
  }


  private java.lang.Integer representantTipusFins;

  public java.lang.Integer getRepresentantTipusFins() {
    return this.representantTipusFins;
  }

  public void setRepresentantTipusFins(java.lang.Integer representantTipusFins) {
    this.representantTipusFins = representantTipusFins;
  }


  private java.lang.String representantIdentificacio;

  public java.lang.String getRepresentantIdentificacio() {
    return this.representantIdentificacio;
  }

  public void setRepresentantIdentificacio(java.lang.String representantIdentificacio) {
    this.representantIdentificacio = representantIdentificacio;
  }


  private java.lang.Integer tramitVersioDesde;

  public java.lang.Integer getTramitVersioDesde() {
    return this.tramitVersioDesde;
  }

  public void setTramitVersioDesde(java.lang.Integer tramitVersioDesde) {
    this.tramitVersioDesde = tramitVersioDesde;
  }


  private java.lang.Integer tramitVersioFins;

  public java.lang.Integer getTramitVersioFins() {
    return this.tramitVersioFins;
  }

  public void setTramitVersioFins(java.lang.Integer tramitVersioFins) {
    this.tramitVersioFins = tramitVersioFins;
  }


  private java.lang.String arxiuDocumentID;

  public java.lang.String getArxiuDocumentID() {
    return this.arxiuDocumentID;
  }

  public void setArxiuDocumentID(java.lang.String arxiuDocumentID) {
    this.arxiuDocumentID = arxiuDocumentID;
  }


  private java.lang.String arxiuExpedientID;

  public java.lang.String getArxiuExpedientID() {
    return this.arxiuExpedientID;
  }

  public void setArxiuExpedientID(java.lang.String arxiuExpedientID) {
    this.arxiuExpedientID = arxiuExpedientID;
  }


  private java.util.List<java.lang.Integer> estatSelect;

  public java.util.List<java.lang.Integer> getEstatSelect() {
    return this.estatSelect;
  }

  public void setEstatSelect(java.util.List<java.lang.Integer> estatSelect) {
    this.estatSelect = estatSelect;
  }


  private java.lang.String url;

  public java.lang.String getUrl() {
    return this.url;
  }

  public void setUrl(java.lang.String url) {
    this.url = url;
  }


  private java.sql.Timestamp dataActivitatDesde;

  public java.sql.Timestamp getDataActivitatDesde() {
    return this.dataActivitatDesde;
  }

  public void setDataActivitatDesde(java.sql.Timestamp dataActivitatDesde) {
    this.dataActivitatDesde = dataActivitatDesde;
  }


  private java.sql.Timestamp dataActivitatFins;

  public java.sql.Timestamp getDataActivitatFins() {
    return this.dataActivitatFins;
  }

  public void setDataActivitatFins(java.sql.Timestamp dataActivitatFins) {
    this.dataActivitatFins = dataActivitatFins;
  }


  private java.lang.String idActuacioTramit;

  public java.lang.String getIdActuacioTramit() {
    return this.idActuacioTramit;
  }

  public void setIdActuacioTramit(java.lang.String idActuacioTramit) {
    this.idActuacioTramit = idActuacioTramit;
  }


  private java.lang.String procediment;

  public java.lang.String getProcediment() {
    return this.procediment;
  }

  public void setProcediment(java.lang.String procediment) {
    this.procediment = procediment;
  }


  public ActivitatFilterForm() {
  }
  
  public ActivitatFilterForm(ActivitatFilterForm __toClone) {
    super(__toClone);
    this.activitatIDDesde = __toClone.activitatIDDesde;
    this.activitatIDFins = __toClone.activitatIDFins;
    this.funcionariIDDesde = __toClone.funcionariIDDesde;
    this.funcionariIDFins = __toClone.funcionariIDFins;
    this.tipusSelect = __toClone.tipusSelect;
    this.registre = __toClone.registre;
    this.tramit = __toClone.tramit;
    this.codiSia = __toClone.codiSia;
    this.autoritzacioIDDesde = __toClone.autoritzacioIDDesde;
    this.autoritzacioIDFins = __toClone.autoritzacioIDFins;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.interessatNom = __toClone.interessatNom;
    this.interessatLlinatge1 = __toClone.interessatLlinatge1;
    this.interessatLlinatge2 = __toClone.interessatLlinatge2;
    this.interessatTipusDesde = __toClone.interessatTipusDesde;
    this.interessatTipusFins = __toClone.interessatTipusFins;
    this.interessatIdentificacio = __toClone.interessatIdentificacio;
    this.representantNom = __toClone.representantNom;
    this.representantLlinatge1 = __toClone.representantLlinatge1;
    this.representantLlinatge2 = __toClone.representantLlinatge2;
    this.representantTipusDesde = __toClone.representantTipusDesde;
    this.representantTipusFins = __toClone.representantTipusFins;
    this.representantIdentificacio = __toClone.representantIdentificacio;
    this.tramitVersioDesde = __toClone.tramitVersioDesde;
    this.tramitVersioFins = __toClone.tramitVersioFins;
    this.arxiuDocumentID = __toClone.arxiuDocumentID;
    this.arxiuExpedientID = __toClone.arxiuExpedientID;
    this.estatSelect = __toClone.estatSelect;
    this.url = __toClone.url;
    this.dataActivitatDesde = __toClone.dataActivitatDesde;
    this.dataActivitatFins = __toClone.dataActivitatFins;
    this.idActuacioTramit = __toClone.idActuacioTramit;
    this.procediment = __toClone.procediment;
    this.mapOfFuncionariForFuncionariID = __toClone.mapOfFuncionariForFuncionariID;
    this.mapOfValuesForTipus = __toClone.mapOfValuesForTipus;
    this.mapOfValuesForEstat = __toClone.mapOfValuesForEstat;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { FUNCIONARIID ,TIPUS ,INTERESSATIDENTIFICACIO ,DATAACTIVITAT ,PROCEDIMENT }));
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
  private Map<String, String> mapOfFuncionariForFuncionariID;

  public Map<String, String> getMapOfFuncionariForFuncionariID() {
    return this.mapOfFuncionariForFuncionariID;
  }

  public void setMapOfFuncionariForFuncionariID(Map<String, String> mapOfFuncionariForFuncionariID) {
    this.mapOfFuncionariForFuncionariID = mapOfFuncionariForFuncionariID;
  }



  private Map<String, String> mapOfValuesForTipus;

  public Map<String, String> getMapOfValuesForTipus() {
    return this.mapOfValuesForTipus;
  }

  public void setMapOfValuesForTipus(Map<String, String> mapOfValuesForTipus) {
    this.mapOfValuesForTipus = mapOfValuesForTipus;
  }



  private Map<String, String> mapOfValuesForEstat;

  public Map<String, String> getMapOfValuesForEstat() {
    return this.mapOfValuesForEstat;
  }

  public void setMapOfValuesForEstat(Map<String, String> mapOfValuesForEstat) {
    this.mapOfValuesForEstat = mapOfValuesForEstat;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
