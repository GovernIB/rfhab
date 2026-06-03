
package es.caib.rfhab.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.rfhab.back.form.RFHabBaseFilterForm;

import es.caib.rfhab.model.fields.SincroUnitatsFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class SincroUnitatsFilterForm extends RFHabBaseFilterForm implements SincroUnitatsFields {

  private java.lang.Long sincrounitatsIdDesde;

  public java.lang.Long getSincrounitatsIdDesde() {
    return this.sincrounitatsIdDesde;
  }

  public void setSincrounitatsIdDesde(java.lang.Long sincrounitatsIdDesde) {
    this.sincrounitatsIdDesde = sincrounitatsIdDesde;
  }


  private java.lang.Long sincrounitatsIdFins;

  public java.lang.Long getSincrounitatsIdFins() {
    return this.sincrounitatsIdFins;
  }

  public void setSincrounitatsIdFins(java.lang.Long sincrounitatsIdFins) {
    this.sincrounitatsIdFins = sincrounitatsIdFins;
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


  private java.sql.Timestamp dataDarreraSincroDesde;

  public java.sql.Timestamp getDataDarreraSincroDesde() {
    return this.dataDarreraSincroDesde;
  }

  public void setDataDarreraSincroDesde(java.sql.Timestamp dataDarreraSincroDesde) {
    this.dataDarreraSincroDesde = dataDarreraSincroDesde;
  }


  private java.sql.Timestamp dataDarreraSincroFins;

  public java.sql.Timestamp getDataDarreraSincroFins() {
    return this.dataDarreraSincroFins;
  }

  public void setDataDarreraSincroFins(java.sql.Timestamp dataDarreraSincroFins) {
    this.dataDarreraSincroFins = dataDarreraSincroFins;
  }


  private java.sql.Timestamp dataPrimeraSincroDesde;

  public java.sql.Timestamp getDataPrimeraSincroDesde() {
    return this.dataPrimeraSincroDesde;
  }

  public void setDataPrimeraSincroDesde(java.sql.Timestamp dataPrimeraSincroDesde) {
    this.dataPrimeraSincroDesde = dataPrimeraSincroDesde;
  }


  private java.sql.Timestamp dataPrimeraSincroFins;

  public java.sql.Timestamp getDataPrimeraSincroFins() {
    return this.dataPrimeraSincroFins;
  }

  public void setDataPrimeraSincroFins(java.sql.Timestamp dataPrimeraSincroFins) {
    this.dataPrimeraSincroFins = dataPrimeraSincroFins;
  }


  private java.lang.String codiEntitat;

  public java.lang.String getCodiEntitat() {
    return this.codiEntitat;
  }

  public void setCodiEntitat(java.lang.String codiEntitat) {
    this.codiEntitat = codiEntitat;
  }


  private java.lang.String observacions;

  public java.lang.String getObservacions() {
    return this.observacions;
  }

  public void setObservacions(java.lang.String observacions) {
    this.observacions = observacions;
  }


  private java.lang.Long usuariIdDesde;

  public java.lang.Long getUsuariIdDesde() {
    return this.usuariIdDesde;
  }

  public void setUsuariIdDesde(java.lang.Long usuariIdDesde) {
    this.usuariIdDesde = usuariIdDesde;
  }


  private java.lang.Long usuariIdFins;

  public java.lang.Long getUsuariIdFins() {
    return this.usuariIdFins;
  }

  public void setUsuariIdFins(java.lang.Long usuariIdFins) {
    this.usuariIdFins = usuariIdFins;
  }


  public SincroUnitatsFilterForm() {
  }
  
  public SincroUnitatsFilterForm(SincroUnitatsFilterForm __toClone) {
    super(__toClone);
    this.sincrounitatsIdDesde = __toClone.sincrounitatsIdDesde;
    this.sincrounitatsIdFins = __toClone.sincrounitatsIdFins;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.dataDarreraSincroDesde = __toClone.dataDarreraSincroDesde;
    this.dataDarreraSincroFins = __toClone.dataDarreraSincroFins;
    this.dataPrimeraSincroDesde = __toClone.dataPrimeraSincroDesde;
    this.dataPrimeraSincroFins = __toClone.dataPrimeraSincroFins;
    this.codiEntitat = __toClone.codiEntitat;
    this.observacions = __toClone.observacions;
    this.usuariIdDesde = __toClone.usuariIdDesde;
    this.usuariIdFins = __toClone.usuariIdFins;
    this.mapOfUsuariForUsuariId = __toClone.mapOfUsuariForUsuariId;
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
  private Map<String, String> mapOfUsuariForUsuariId;

  public Map<String, String> getMapOfUsuariForUsuariId() {
    return this.mapOfUsuariForUsuariId;
  }

  public void setMapOfUsuariForUsuariId(Map<String, String> mapOfUsuariForUsuariId) {
    this.mapOfUsuariForUsuariId = mapOfUsuariForUsuariId;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
