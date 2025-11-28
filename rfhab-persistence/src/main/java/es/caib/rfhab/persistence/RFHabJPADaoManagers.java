package es.caib.rfhab.persistence;

import es.caib.rfhab.model.*;
import es.caib.rfhab.model.dao.*;
import javax.persistence.EntityManager;

public final class RFHabJPADaoManagers implements IRFHabDaoManagers{

   private final ActivitatJPAManager rfh_activitat;
   private final AutoritzacioJPAManager rfh_autoritzacio;
   private final EntitatJPAManager rfh_entitat;
   private final FitxerJPAManager rfh_fitxer;
   private final FuncionariJPAManager rfh_funcionari;
   private final FuncionariLlocJPAManager rfh_funcionarilloc;
   private final HabilitacioJPAManager rfh_habilitacio;
   private final HistoricJPAManager rfh_historic;
   private final HistoricLlocJPAManager rfh_historiclloc;
   private final IdiomaJPAManager rfh_idioma;
   private final LlocJPAManager rfh_lloc;
   private final LlocHabilitacioJPAManager rfh_llochabilitacio;
   private final PluginJPAManager rfh_plugin;
   private final ScanWebJPAManager rfh_digitalib;
   private final TraduccioJPAManager rfh_traduccio;
   private final UnitatJPAManager rfh_unitat;
   private final UsuariJPAManager rfh_usuari;
   private final UsuariEntitatJPAManager rfh_usuarientitat;

  public  RFHabJPADaoManagers(EntityManager __em) {
    this.rfh_activitat = new ActivitatJPAManager(__em);
    this.rfh_autoritzacio = new AutoritzacioJPAManager(__em);
    this.rfh_entitat = new EntitatJPAManager(__em);
    this.rfh_fitxer = new FitxerJPAManager(__em);
    this.rfh_funcionari = new FuncionariJPAManager(__em);
    this.rfh_funcionarilloc = new FuncionariLlocJPAManager(__em);
    this.rfh_habilitacio = new HabilitacioJPAManager(__em);
    this.rfh_historic = new HistoricJPAManager(__em);
    this.rfh_historiclloc = new HistoricLlocJPAManager(__em);
    this.rfh_idioma = new IdiomaJPAManager(__em);
    this.rfh_lloc = new LlocJPAManager(__em);
    this.rfh_llochabilitacio = new LlocHabilitacioJPAManager(__em);
    this.rfh_plugin = new PluginJPAManager(__em);
    this.rfh_digitalib = new ScanWebJPAManager(__em);
    this.rfh_traduccio = new TraduccioJPAManager(__em);
    this.rfh_unitat = new UnitatJPAManager(__em);
    this.rfh_usuari = new UsuariJPAManager(__em);
    this.rfh_usuarientitat = new UsuariEntitatJPAManager(__em);
  }

    public IActivitatManager getActivitatManager() {
        return this.rfh_activitat;
    };

    public IAutoritzacioManager getAutoritzacioManager() {
        return this.rfh_autoritzacio;
    };

    public IEntitatManager getEntitatManager() {
        return this.rfh_entitat;
    };

    public IFitxerManager getFitxerManager() {
        return this.rfh_fitxer;
    };

    public IFuncionariManager getFuncionariManager() {
        return this.rfh_funcionari;
    };

    public IFuncionariLlocManager getFuncionariLlocManager() {
        return this.rfh_funcionarilloc;
    };

    public IHabilitacioManager getHabilitacioManager() {
        return this.rfh_habilitacio;
    };

    public IHistoricManager getHistoricManager() {
        return this.rfh_historic;
    };

    public IHistoricLlocManager getHistoricLlocManager() {
        return this.rfh_historiclloc;
    };

    public IIdiomaManager getIdiomaManager() {
        return this.rfh_idioma;
    };

    public ILlocManager getLlocManager() {
        return this.rfh_lloc;
    };

    public ILlocHabilitacioManager getLlocHabilitacioManager() {
        return this.rfh_llochabilitacio;
    };

    public IPluginManager getPluginManager() {
        return this.rfh_plugin;
    };

    public IScanWebManager getScanWebManager() {
        return this.rfh_digitalib;
    };

    public ITraduccioManager getTraduccioManager() {
        return this.rfh_traduccio;
    };

    public IUnitatManager getUnitatManager() {
        return this.rfh_unitat;
    };

    public IUsuariManager getUsuariManager() {
        return this.rfh_usuari;
    };

    public IUsuariEntitatManager getUsuariEntitatManager() {
        return this.rfh_usuarientitat;
    };


}