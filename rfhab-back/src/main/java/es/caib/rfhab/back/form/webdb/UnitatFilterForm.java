
package es.caib.rfhab.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.rfhab.back.form.RFHabBaseFilterForm;

import es.caib.rfhab.model.fields.UnitatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class UnitatFilterForm extends RFHabBaseFilterForm implements UnitatFields {

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


  private java.lang.String codi;

  public java.lang.String getCodi() {
    return this.codi;
  }

  public void setCodi(java.lang.String codi) {
    this.codi = codi;
  }


  private java.lang.Integer versioDesde;

  public java.lang.Integer getVersioDesde() {
    return this.versioDesde;
  }

  public void setVersioDesde(java.lang.Integer versioDesde) {
    this.versioDesde = versioDesde;
  }


  private java.lang.Integer versioFins;

  public java.lang.Integer getVersioFins() {
    return this.versioFins;
  }

  public void setVersioFins(java.lang.Integer versioFins) {
    this.versioFins = versioFins;
  }


  private java.lang.String denominacio;

  public java.lang.String getDenominacio() {
    return this.denominacio;
  }

  public void setDenominacio(java.lang.String denominacio) {
    this.denominacio = denominacio;
  }


  private java.lang.String cooficial;

  public java.lang.String getCooficial() {
    return this.cooficial;
  }

  public void setCooficial(java.lang.String cooficial) {
    this.cooficial = cooficial;
  }


  private java.lang.String arrel;

  public java.lang.String getArrel() {
    return this.arrel;
  }

  public void setArrel(java.lang.String arrel) {
    this.arrel = arrel;
  }


  private java.lang.Integer arrelVersioDesde;

  public java.lang.Integer getArrelVersioDesde() {
    return this.arrelVersioDesde;
  }

  public void setArrelVersioDesde(java.lang.Integer arrelVersioDesde) {
    this.arrelVersioDesde = arrelVersioDesde;
  }


  private java.lang.Integer arrelVersioFins;

  public java.lang.Integer getArrelVersioFins() {
    return this.arrelVersioFins;
  }

  public void setArrelVersioFins(java.lang.Integer arrelVersioFins) {
    this.arrelVersioFins = arrelVersioFins;
  }


  private java.lang.String superior;

  public java.lang.String getSuperior() {
    return this.superior;
  }

  public void setSuperior(java.lang.String superior) {
    this.superior = superior;
  }


  private java.lang.Integer superiorVersioDesde;

  public java.lang.Integer getSuperiorVersioDesde() {
    return this.superiorVersioDesde;
  }

  public void setSuperiorVersioDesde(java.lang.Integer superiorVersioDesde) {
    this.superiorVersioDesde = superiorVersioDesde;
  }


  private java.lang.Integer superiorVersioFins;

  public java.lang.Integer getSuperiorVersioFins() {
    return this.superiorVersioFins;
  }

  public void setSuperiorVersioFins(java.lang.Integer superiorVersioFins) {
    this.superiorVersioFins = superiorVersioFins;
  }


  private java.lang.String estat;

  public java.lang.String getEstat() {
    return this.estat;
  }

  public void setEstat(java.lang.String estat) {
    this.estat = estat;
  }


  public UnitatFilterForm() {
  }
  
  public UnitatFilterForm(UnitatFilterForm __toClone) {
    super(__toClone);
    this.unitatIDDesde = __toClone.unitatIDDesde;
    this.unitatIDFins = __toClone.unitatIDFins;
    this.codi = __toClone.codi;
    this.versioDesde = __toClone.versioDesde;
    this.versioFins = __toClone.versioFins;
    this.denominacio = __toClone.denominacio;
    this.cooficial = __toClone.cooficial;
    this.arrel = __toClone.arrel;
    this.arrelVersioDesde = __toClone.arrelVersioDesde;
    this.arrelVersioFins = __toClone.arrelVersioFins;
    this.superior = __toClone.superior;
    this.superiorVersioDesde = __toClone.superiorVersioDesde;
    this.superiorVersioFins = __toClone.superiorVersioFins;
    this.estat = __toClone.estat;
    this.mapOfValuesForEstat = __toClone.mapOfValuesForEstat;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { CODI ,VERSIO ,DENOMINACIO ,COOFICIAL ,ESTAT }));
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
