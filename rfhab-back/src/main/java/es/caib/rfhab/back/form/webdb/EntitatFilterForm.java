
package es.caib.rfhab.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.rfhab.back.form.RFHabBaseFilterForm;

import es.caib.rfhab.model.fields.EntitatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EntitatFilterForm extends RFHabBaseFilterForm implements EntitatFields {

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


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
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


  public EntitatFilterForm() {
  }
  
  public EntitatFilterForm(EntitatFilterForm __toClone) {
    super(__toClone);
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.nom = __toClone.nom;
    this.unitatIDDesde = __toClone.unitatIDDesde;
    this.unitatIDFins = __toClone.unitatIDFins;
    this.dataBaixaDesde = __toClone.dataBaixaDesde;
    this.dataBaixaFins = __toClone.dataBaixaFins;
    this.mapOfUnitatForUnitatID = __toClone.mapOfUnitatForUnitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { ENTITATID ,NOM }));
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
