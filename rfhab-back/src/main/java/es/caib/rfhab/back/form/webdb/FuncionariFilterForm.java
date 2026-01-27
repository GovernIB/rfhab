
package es.caib.rfhab.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.rfhab.back.form.RFHabBaseFilterForm;

import es.caib.rfhab.model.fields.FuncionariFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class FuncionariFilterForm extends RFHabBaseFilterForm implements FuncionariFields {

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


  private java.lang.String numero;

  public java.lang.String getNumero() {
    return this.numero;
  }

  public void setNumero(java.lang.String numero) {
    this.numero = numero;
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


  private java.util.List<java.lang.Integer> tipusIdentificadorSelect;

  public java.util.List<java.lang.Integer> getTipusIdentificadorSelect() {
    return this.tipusIdentificadorSelect;
  }

  public void setTipusIdentificadorSelect(java.util.List<java.lang.Integer> tipusIdentificadorSelect) {
    this.tipusIdentificadorSelect = tipusIdentificadorSelect;
  }


  private java.lang.String identificador;

  public java.lang.String getIdentificador() {
    return this.identificador;
  }

  public void setIdentificador(java.lang.String identificador) {
    this.identificador = identificador;
  }


  private java.lang.String usuari;

  public java.lang.String getUsuari() {
    return this.usuari;
  }

  public void setUsuari(java.lang.String usuari) {
    this.usuari = usuari;
  }


  private java.lang.String correu;

  public java.lang.String getCorreu() {
    return this.correu;
  }

  public void setCorreu(java.lang.String correu) {
    this.correu = correu;
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


  private java.lang.String observacions;

  public java.lang.String getObservacions() {
    return this.observacions;
  }

  public void setObservacions(java.lang.String observacions) {
    this.observacions = observacions;
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


  public FuncionariFilterForm() {
  }
  
  public FuncionariFilterForm(FuncionariFilterForm __toClone) {
    super(__toClone);
    this.funcionariIDDesde = __toClone.funcionariIDDesde;
    this.funcionariIDFins = __toClone.funcionariIDFins;
    this.numero = __toClone.numero;
    this.nom = __toClone.nom;
    this.llinatge1 = __toClone.llinatge1;
    this.llinatge2 = __toClone.llinatge2;
    this.tipusIdentificadorSelect = __toClone.tipusIdentificadorSelect;
    this.identificador = __toClone.identificador;
    this.usuari = __toClone.usuari;
    this.correu = __toClone.correu;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.observacions = __toClone.observacions;
    this.dataBaixaDesde = __toClone.dataBaixaDesde;
    this.dataBaixaFins = __toClone.dataBaixaFins;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.mapOfValuesForTipusIdentificador = __toClone.mapOfValuesForTipusIdentificador;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NUMERO ,NOM ,IDENTIFICADOR ,USUARI }));
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
  private Map<String, String> mapOfValuesForTipusIdentificador;

  public Map<String, String> getMapOfValuesForTipusIdentificador() {
    return this.mapOfValuesForTipusIdentificador;
  }

  public void setMapOfValuesForTipusIdentificador(Map<String, String> mapOfValuesForTipusIdentificador) {
    this.mapOfValuesForTipusIdentificador = mapOfValuesForTipusIdentificador;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
