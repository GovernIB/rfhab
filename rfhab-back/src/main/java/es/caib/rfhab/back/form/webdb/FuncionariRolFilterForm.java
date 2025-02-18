
package es.caib.rfhab.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.rfhab.back.form.RFHabBaseFilterForm;

import es.caib.rfhab.model.fields.FuncionariRolFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class FuncionariRolFilterForm extends RFHabBaseFilterForm implements FuncionariRolFields {

  private java.lang.Long funcionariRolIDDesde;

  public java.lang.Long getFuncionariRolIDDesde() {
    return this.funcionariRolIDDesde;
  }

  public void setFuncionariRolIDDesde(java.lang.Long funcionariRolIDDesde) {
    this.funcionariRolIDDesde = funcionariRolIDDesde;
  }


  private java.lang.Long funcionariRolIDFins;

  public java.lang.Long getFuncionariRolIDFins() {
    return this.funcionariRolIDFins;
  }

  public void setFuncionariRolIDFins(java.lang.Long funcionariRolIDFins) {
    this.funcionariRolIDFins = funcionariRolIDFins;
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


  public FuncionariRolFilterForm() {
  }
  
  public FuncionariRolFilterForm(FuncionariRolFilterForm __toClone) {
    super(__toClone);
    this.funcionariRolIDDesde = __toClone.funcionariRolIDDesde;
    this.funcionariRolIDFins = __toClone.funcionariRolIDFins;
    this.funcionariIDDesde = __toClone.funcionariIDDesde;
    this.funcionariIDFins = __toClone.funcionariIDFins;
    this.rolIDDesde = __toClone.rolIDDesde;
    this.rolIDFins = __toClone.rolIDFins;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.mapOfFuncionariForFuncionariID = __toClone.mapOfFuncionariForFuncionariID;
    this.mapOfRolForRolID = __toClone.mapOfRolForRolID;
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
  private Map<String, String> mapOfFuncionariForFuncionariID;

  public Map<String, String> getMapOfFuncionariForFuncionariID() {
    return this.mapOfFuncionariForFuncionariID;
  }

  public void setMapOfFuncionariForFuncionariID(Map<String, String> mapOfFuncionariForFuncionariID) {
    this.mapOfFuncionariForFuncionariID = mapOfFuncionariForFuncionariID;
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
