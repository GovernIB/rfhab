
package es.caib.rfhab.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.rfhab.back.form.RFHabBaseFilterForm;

import es.caib.rfhab.model.fields.FuncionariLlocFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class FuncionariLlocFilterForm extends RFHabBaseFilterForm implements FuncionariLlocFields {

  private java.lang.Long funcionarillocIDDesde;

  public java.lang.Long getFuncionarillocIDDesde() {
    return this.funcionarillocIDDesde;
  }

  public void setFuncionarillocIDDesde(java.lang.Long funcionarillocIDDesde) {
    this.funcionarillocIDDesde = funcionarillocIDDesde;
  }


  private java.lang.Long funcionarillocIDFins;

  public java.lang.Long getFuncionarillocIDFins() {
    return this.funcionarillocIDFins;
  }

  public void setFuncionarillocIDFins(java.lang.Long funcionarillocIDFins) {
    this.funcionarillocIDFins = funcionarillocIDFins;
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


  private java.sql.Date dataIniciDesde;

  public java.sql.Date getDataIniciDesde() {
    return this.dataIniciDesde;
  }

  public void setDataIniciDesde(java.sql.Date dataIniciDesde) {
    this.dataIniciDesde = dataIniciDesde;
  }


  private java.sql.Date dataIniciFins;

  public java.sql.Date getDataIniciFins() {
    return this.dataIniciFins;
  }

  public void setDataIniciFins(java.sql.Date dataIniciFins) {
    this.dataIniciFins = dataIniciFins;
  }


  private java.sql.Date dataFiDesde;

  public java.sql.Date getDataFiDesde() {
    return this.dataFiDesde;
  }

  public void setDataFiDesde(java.sql.Date dataFiDesde) {
    this.dataFiDesde = dataFiDesde;
  }


  private java.sql.Date dataFiFins;

  public java.sql.Date getDataFiFins() {
    return this.dataFiFins;
  }

  public void setDataFiFins(java.sql.Date dataFiFins) {
    this.dataFiFins = dataFiFins;
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


  public FuncionariLlocFilterForm() {
  }
  
  public FuncionariLlocFilterForm(FuncionariLlocFilterForm __toClone) {
    super(__toClone);
    this.funcionarillocIDDesde = __toClone.funcionarillocIDDesde;
    this.funcionarillocIDFins = __toClone.funcionarillocIDFins;
    this.llocIDDesde = __toClone.llocIDDesde;
    this.llocIDFins = __toClone.llocIDFins;
    this.funcionariIDDesde = __toClone.funcionariIDDesde;
    this.funcionariIDFins = __toClone.funcionariIDFins;
    this.dataIniciDesde = __toClone.dataIniciDesde;
    this.dataIniciFins = __toClone.dataIniciFins;
    this.dataFiDesde = __toClone.dataFiDesde;
    this.dataFiFins = __toClone.dataFiFins;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.usuariIDDesde = __toClone.usuariIDDesde;
    this.usuariIDFins = __toClone.usuariIDFins;
    this.mapOfLlocForLlocID = __toClone.mapOfLlocForLlocID;
    this.mapOfFuncionariForFuncionariID = __toClone.mapOfFuncionariForFuncionariID;
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
  private Map<String, String> mapOfLlocForLlocID;

  public Map<String, String> getMapOfLlocForLlocID() {
    return this.mapOfLlocForLlocID;
  }

  public void setMapOfLlocForLlocID(Map<String, String> mapOfLlocForLlocID) {
    this.mapOfLlocForLlocID = mapOfLlocForLlocID;
  }



  private Map<String, String> mapOfFuncionariForFuncionariID;

  public Map<String, String> getMapOfFuncionariForFuncionariID() {
    return this.mapOfFuncionariForFuncionariID;
  }

  public void setMapOfFuncionariForFuncionariID(Map<String, String> mapOfFuncionariForFuncionariID) {
    this.mapOfFuncionariForFuncionariID = mapOfFuncionariForFuncionariID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
