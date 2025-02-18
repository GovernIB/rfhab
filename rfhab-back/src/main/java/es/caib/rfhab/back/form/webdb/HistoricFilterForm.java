
package es.caib.rfhab.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.rfhab.back.form.RFHabBaseFilterForm;

import es.caib.rfhab.model.fields.HistoricFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class HistoricFilterForm extends RFHabBaseFilterForm implements HistoricFields {

  private java.lang.Long historicIDDesde;

  public java.lang.Long getHistoricIDDesde() {
    return this.historicIDDesde;
  }

  public void setHistoricIDDesde(java.lang.Long historicIDDesde) {
    this.historicIDDesde = historicIDDesde;
  }


  private java.lang.Long historicIDFins;

  public java.lang.Long getHistoricIDFins() {
    return this.historicIDFins;
  }

  public void setHistoricIDFins(java.lang.Long historicIDFins) {
    this.historicIDFins = historicIDFins;
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


  public HistoricFilterForm() {
  }
  
  public HistoricFilterForm(HistoricFilterForm __toClone) {
    super(__toClone);
    this.historicIDDesde = __toClone.historicIDDesde;
    this.historicIDFins = __toClone.historicIDFins;
    this.funcionariIDDesde = __toClone.funcionariIDDesde;
    this.funcionariIDFins = __toClone.funcionariIDFins;
    this.numeroCai = __toClone.numeroCai;
    this.observacions = __toClone.observacions;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.usuariIDDesde = __toClone.usuariIDDesde;
    this.usuariIDFins = __toClone.usuariIDFins;
    this.mapOfFuncionariForFuncionariID = __toClone.mapOfFuncionariForFuncionariID;
    this.mapOfUsuariForUsuariID = __toClone.mapOfUsuariForUsuariID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NUMEROCAI }));
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
