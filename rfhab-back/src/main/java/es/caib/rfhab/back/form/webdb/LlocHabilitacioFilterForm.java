
package es.caib.rfhab.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.rfhab.back.form.RFHabBaseFilterForm;

import es.caib.rfhab.model.fields.LlocHabilitacioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class LlocHabilitacioFilterForm extends RFHabBaseFilterForm implements LlocHabilitacioFields {

  private java.lang.Long llocHabilitacioIDDesde;

  public java.lang.Long getLlocHabilitacioIDDesde() {
    return this.llocHabilitacioIDDesde;
  }

  public void setLlocHabilitacioIDDesde(java.lang.Long llocHabilitacioIDDesde) {
    this.llocHabilitacioIDDesde = llocHabilitacioIDDesde;
  }


  private java.lang.Long llocHabilitacioIDFins;

  public java.lang.Long getLlocHabilitacioIDFins() {
    return this.llocHabilitacioIDFins;
  }

  public void setLlocHabilitacioIDFins(java.lang.Long llocHabilitacioIDFins) {
    this.llocHabilitacioIDFins = llocHabilitacioIDFins;
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


  private java.lang.Long habilitacioIdDesde;

  public java.lang.Long getHabilitacioIdDesde() {
    return this.habilitacioIdDesde;
  }

  public void setHabilitacioIdDesde(java.lang.Long habilitacioIdDesde) {
    this.habilitacioIdDesde = habilitacioIdDesde;
  }


  private java.lang.Long habilitacioIdFins;

  public java.lang.Long getHabilitacioIdFins() {
    return this.habilitacioIdFins;
  }

  public void setHabilitacioIdFins(java.lang.Long habilitacioIdFins) {
    this.habilitacioIdFins = habilitacioIdFins;
  }


  public LlocHabilitacioFilterForm() {
  }
  
  public LlocHabilitacioFilterForm(LlocHabilitacioFilterForm __toClone) {
    super(__toClone);
    this.llocHabilitacioIDDesde = __toClone.llocHabilitacioIDDesde;
    this.llocHabilitacioIDFins = __toClone.llocHabilitacioIDFins;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.llocIDDesde = __toClone.llocIDDesde;
    this.llocIDFins = __toClone.llocIDFins;
    this.habilitacioIdDesde = __toClone.habilitacioIdDesde;
    this.habilitacioIdFins = __toClone.habilitacioIdFins;
    this.mapOfLlocForLlocID = __toClone.mapOfLlocForLlocID;
    this.mapOfHabilitacioForHabilitacioId = __toClone.mapOfHabilitacioForHabilitacioId;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { LLOCID ,HABILITACIOID }));
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



  private Map<String, String> mapOfHabilitacioForHabilitacioId;

  public Map<String, String> getMapOfHabilitacioForHabilitacioId() {
    return this.mapOfHabilitacioForHabilitacioId;
  }

  public void setMapOfHabilitacioForHabilitacioId(Map<String, String> mapOfHabilitacioForHabilitacioId) {
    this.mapOfHabilitacioForHabilitacioId = mapOfHabilitacioForHabilitacioId;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
