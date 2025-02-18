
package es.caib.rfhab.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.rfhab.back.form.RFHabBaseFilterForm;

import es.caib.rfhab.model.fields.HistoricLlocFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class HistoricLlocFilterForm extends RFHabBaseFilterForm implements HistoricLlocFields {

  private java.lang.Long historicllocIDDesde;

  public java.lang.Long getHistoricllocIDDesde() {
    return this.historicllocIDDesde;
  }

  public void setHistoricllocIDDesde(java.lang.Long historicllocIDDesde) {
    this.historicllocIDDesde = historicllocIDDesde;
  }


  private java.lang.Long historicllocIDFins;

  public java.lang.Long getHistoricllocIDFins() {
    return this.historicllocIDFins;
  }

  public void setHistoricllocIDFins(java.lang.Long historicllocIDFins) {
    this.historicllocIDFins = historicllocIDFins;
  }


  private java.lang.Long llocIDDesde;

  public java.lang.Long getLlocIDDesde() {
    return this.llocIDDesde;
  }

  public void setLlocIDDesde(java.lang.Long llocIDDesde) {
    this.llocIDDesde = llocIDDesde;
  }


  private java.lang.Long llocIDFins;

  public java.lang.Long getLlocIDFins() {
    return this.llocIDFins;
  }

  public void setLlocIDFins(java.lang.Long llocIDFins) {
    this.llocIDFins = llocIDFins;
  }


  private java.lang.String numeroCai;

  public java.lang.String getNumeroCai() {
    return this.numeroCai;
  }

  public void setNumeroCai(java.lang.String numeroCai) {
    this.numeroCai = numeroCai;
  }


  private java.lang.String observacions;

  public java.lang.String getObservacions() {
    return this.observacions;
  }

  public void setObservacions(java.lang.String observacions) {
    this.observacions = observacions;
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


  public HistoricLlocFilterForm() {
  }
  
  public HistoricLlocFilterForm(HistoricLlocFilterForm __toClone) {
    super(__toClone);
    this.historicllocIDDesde = __toClone.historicllocIDDesde;
    this.historicllocIDFins = __toClone.historicllocIDFins;
    this.llocIDDesde = __toClone.llocIDDesde;
    this.llocIDFins = __toClone.llocIDFins;
    this.numeroCai = __toClone.numeroCai;
    this.observacions = __toClone.observacions;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.usuariIDDesde = __toClone.usuariIDDesde;
    this.usuariIDFins = __toClone.usuariIDFins;
    this.mapOfLlocForLlocID = __toClone.mapOfLlocForLlocID;
    this.mapOfUsuariForUsuariID = __toClone.mapOfUsuariForUsuariID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { LLOCID ,NUMEROCAI ,DATACREACIO }));
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
  private Map<String, String> mapOfLlocForLlocID;

  public Map<String, String> getMapOfLlocForLlocID() {
    return this.mapOfLlocForLlocID;
  }

  public void setMapOfLlocForLlocID(Map<String, String> mapOfLlocForLlocID) {
    this.mapOfLlocForLlocID = mapOfLlocForLlocID;
  }



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
