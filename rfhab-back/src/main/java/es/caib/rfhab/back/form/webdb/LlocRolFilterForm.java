
package es.caib.rfhab.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.rfhab.back.form.RFHabBaseFilterForm;

import es.caib.rfhab.model.fields.LlocRolFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class LlocRolFilterForm extends RFHabBaseFilterForm implements LlocRolFields {

  private java.lang.Long llocRolIDDesde;

  public java.lang.Long getLlocRolIDDesde() {
    return this.llocRolIDDesde;
  }

  public void setLlocRolIDDesde(java.lang.Long llocRolIDDesde) {
    this.llocRolIDDesde = llocRolIDDesde;
  }


  private java.lang.Long llocRolIDFins;

  public java.lang.Long getLlocRolIDFins() {
    return this.llocRolIDFins;
  }

  public void setLlocRolIDFins(java.lang.Long llocRolIDFins) {
    this.llocRolIDFins = llocRolIDFins;
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


  private java.lang.Long rolIDDesde;

  public java.lang.Long getRolIDDesde() {
    return this.rolIDDesde;
  }

  public void setRolIDDesde(java.lang.Long rolIDDesde) {
    this.rolIDDesde = rolIDDesde;
  }


  private java.lang.Long rolIDFins;

  public java.lang.Long getRolIDFins() {
    return this.rolIDFins;
  }

  public void setRolIDFins(java.lang.Long rolIDFins) {
    this.rolIDFins = rolIDFins;
  }


  public LlocRolFilterForm() {
  }
  
  public LlocRolFilterForm(LlocRolFilterForm __toClone) {
    super(__toClone);
    this.llocRolIDDesde = __toClone.llocRolIDDesde;
    this.llocRolIDFins = __toClone.llocRolIDFins;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.llocIDDesde = __toClone.llocIDDesde;
    this.llocIDFins = __toClone.llocIDFins;
    this.rolIDDesde = __toClone.rolIDDesde;
    this.rolIDFins = __toClone.rolIDFins;
    this.mapOfLlocForLlocID = __toClone.mapOfLlocForLlocID;
    this.mapOfRolForRolID = __toClone.mapOfRolForRolID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { LLOCID ,ROLID }));
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



  private Map<String, String> mapOfRolForRolID;

  public Map<String, String> getMapOfRolForRolID() {
    return this.mapOfRolForRolID;
  }

  public void setMapOfRolForRolID(Map<String, String> mapOfRolForRolID) {
    this.mapOfRolForRolID = mapOfRolForRolID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
