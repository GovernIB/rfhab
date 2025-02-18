
package es.caib.rfhab.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.rfhab.back.form.RFHabBaseFilterForm;

import es.caib.rfhab.model.fields.LlocFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class LlocFilterForm extends RFHabBaseFilterForm implements LlocFields {

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


  private java.lang.String codiLloc;

  public java.lang.String getCodiLloc() {
    return this.codiLloc;
  }

  public void setCodiLloc(java.lang.String codiLloc) {
    this.codiLloc = codiLloc;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.util.List<java.lang.Integer> personalOamrSelect;

  public java.util.List<java.lang.Integer> getPersonalOamrSelect() {
    return this.personalOamrSelect;
  }

  public void setPersonalOamrSelect(java.util.List<java.lang.Integer> personalOamrSelect) {
    this.personalOamrSelect = personalOamrSelect;
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


  private java.sql.Timestamp dataBaixaDesde;

  public java.sql.Timestamp getDataBaixaDesde() {
    return this.dataBaixaDesde;
  }

  public void setDataBaixaDesde(java.sql.Timestamp dataBaixaDesde) {
    this.dataBaixaDesde = dataBaixaDesde;
  }


  private java.sql.Timestamp dataBaixaFins;

  public java.sql.Timestamp getDataBaixaFins() {
    return this.dataBaixaFins;
  }

  public void setDataBaixaFins(java.sql.Timestamp dataBaixaFins) {
    this.dataBaixaFins = dataBaixaFins;
  }


  private java.lang.String observacions;

  public java.lang.String getObservacions() {
    return this.observacions;
  }

  public void setObservacions(java.lang.String observacions) {
    this.observacions = observacions;
  }


  private java.lang.Long unitatIDDesde;

  public java.lang.Long getUnitatIDDesde() {
    return this.unitatIDDesde;
  }

  public void setUnitatIDDesde(java.lang.Long unitatIDDesde) {
    this.unitatIDDesde = unitatIDDesde;
  }


  private java.lang.Long unitatIDFins;

  public java.lang.Long getUnitatIDFins() {
    return this.unitatIDFins;
  }

  public void setUnitatIDFins(java.lang.Long unitatIDFins) {
    this.unitatIDFins = unitatIDFins;
  }


  public LlocFilterForm() {
  }
  
  public LlocFilterForm(LlocFilterForm __toClone) {
    super(__toClone);
    this.llocIDDesde = __toClone.llocIDDesde;
    this.llocIDFins = __toClone.llocIDFins;
    this.codiLloc = __toClone.codiLloc;
    this.nom = __toClone.nom;
    this.personalOamrSelect = __toClone.personalOamrSelect;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.dataBaixaDesde = __toClone.dataBaixaDesde;
    this.dataBaixaFins = __toClone.dataBaixaFins;
    this.observacions = __toClone.observacions;
    this.unitatIDDesde = __toClone.unitatIDDesde;
    this.unitatIDFins = __toClone.unitatIDFins;
    this.mapOfValuesForPersonalOamr = __toClone.mapOfValuesForPersonalOamr;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
    this.mapOfUnitatForUnitatID = __toClone.mapOfUnitatForUnitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { CODILLOC ,NOM ,PERSONALOAMR }));
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
  private Map<String, String> mapOfValuesForPersonalOamr;

  public Map<String, String> getMapOfValuesForPersonalOamr() {
    return this.mapOfValuesForPersonalOamr;
  }

  public void setMapOfValuesForPersonalOamr(Map<String, String> mapOfValuesForPersonalOamr) {
    this.mapOfValuesForPersonalOamr = mapOfValuesForPersonalOamr;
  }



  private Map<String, String> mapOfEntitatForEntitatID;

  public Map<String, String> getMapOfEntitatForEntitatID() {
    return this.mapOfEntitatForEntitatID;
  }

  public void setMapOfEntitatForEntitatID(Map<String, String> mapOfEntitatForEntitatID) {
    this.mapOfEntitatForEntitatID = mapOfEntitatForEntitatID;
  }



  private Map<String, String> mapOfUnitatForUnitatID;

  public Map<String, String> getMapOfUnitatForUnitatID() {
    return this.mapOfUnitatForUnitatID;
  }

  public void setMapOfUnitatForUnitatID(Map<String, String> mapOfUnitatForUnitatID) {
    this.mapOfUnitatForUnitatID = mapOfUnitatForUnitatID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
