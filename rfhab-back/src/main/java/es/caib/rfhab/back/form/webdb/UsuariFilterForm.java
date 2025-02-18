
package es.caib.rfhab.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.rfhab.back.form.RFHabBaseFilterForm;

import es.caib.rfhab.model.fields.UsuariFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class UsuariFilterForm extends RFHabBaseFilterForm implements UsuariFields {

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


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String llinatge1;

  public java.lang.String getLlinatge1() {
    return this.llinatge1;
  }

  public void setLlinatge1(java.lang.String llinatge1) {
    this.llinatge1 = llinatge1;
  }


  private java.lang.String llinatge2;

  public java.lang.String getLlinatge2() {
    return this.llinatge2;
  }

  public void setLlinatge2(java.lang.String llinatge2) {
    this.llinatge2 = llinatge2;
  }


  private java.lang.String nif;

  public java.lang.String getNif() {
    return this.nif;
  }

  public void setNif(java.lang.String nif) {
    this.nif = nif;
  }


  private java.lang.String username;

  public java.lang.String getUsername() {
    return this.username;
  }

  public void setUsername(java.lang.String username) {
    this.username = username;
  }


  private java.lang.String correu;

  public java.lang.String getCorreu() {
    return this.correu;
  }

  public void setCorreu(java.lang.String correu) {
    this.correu = correu;
  }


  private java.lang.String idiomaID;

  public java.lang.String getIdiomaID() {
    return this.idiomaID;
  }

  public void setIdiomaID(java.lang.String idiomaID) {
    this.idiomaID = idiomaID;
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


  private java.lang.Long darreraEntitatDesde;

  public java.lang.Long getDarreraEntitatDesde() {
    return this.darreraEntitatDesde;
  }

  public void setDarreraEntitatDesde(java.lang.Long darreraEntitatDesde) {
    this.darreraEntitatDesde = darreraEntitatDesde;
  }


  private java.lang.Long darreraEntitatFins;

  public java.lang.Long getDarreraEntitatFins() {
    return this.darreraEntitatFins;
  }

  public void setDarreraEntitatFins(java.lang.Long darreraEntitatFins) {
    this.darreraEntitatFins = darreraEntitatFins;
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


  public UsuariFilterForm() {
  }
  
  public UsuariFilterForm(UsuariFilterForm __toClone) {
    super(__toClone);
    this.usuariIDDesde = __toClone.usuariIDDesde;
    this.usuariIDFins = __toClone.usuariIDFins;
    this.nom = __toClone.nom;
    this.llinatge1 = __toClone.llinatge1;
    this.llinatge2 = __toClone.llinatge2;
    this.nif = __toClone.nif;
    this.username = __toClone.username;
    this.correu = __toClone.correu;
    this.idiomaID = __toClone.idiomaID;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.darreraEntitatDesde = __toClone.darreraEntitatDesde;
    this.darreraEntitatFins = __toClone.darreraEntitatFins;
    this.dataBaixaDesde = __toClone.dataBaixaDesde;
    this.dataBaixaFins = __toClone.dataBaixaFins;
    this.mapOfIdiomaForIdiomaID = __toClone.mapOfIdiomaForIdiomaID;
    this.mapOfEntitatForDarreraEntitat = __toClone.mapOfEntitatForDarreraEntitat;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NIF ,CORREU }));
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
  private Map<String, String> mapOfIdiomaForIdiomaID;

  public Map<String, String> getMapOfIdiomaForIdiomaID() {
    return this.mapOfIdiomaForIdiomaID;
  }

  public void setMapOfIdiomaForIdiomaID(Map<String, String> mapOfIdiomaForIdiomaID) {
    this.mapOfIdiomaForIdiomaID = mapOfIdiomaForIdiomaID;
  }



  private Map<String, String> mapOfEntitatForDarreraEntitat;

  public Map<String, String> getMapOfEntitatForDarreraEntitat() {
    return this.mapOfEntitatForDarreraEntitat;
  }

  public void setMapOfEntitatForDarreraEntitat(Map<String, String> mapOfEntitatForDarreraEntitat) {
    this.mapOfEntitatForDarreraEntitat = mapOfEntitatForDarreraEntitat;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
