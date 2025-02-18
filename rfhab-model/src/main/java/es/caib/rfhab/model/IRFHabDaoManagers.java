package es.caib.rfhab.model;

import es.caib.rfhab.model.dao.*;

public interface IRFHabDaoManagers {
	public IActivitatManager getActivitatManager();
	public IAutoritzacioManager getAutoritzacioManager();
	public IEntitatManager getEntitatManager();
	public IFitxerManager getFitxerManager();
	public IFuncionariManager getFuncionariManager();
	public IFuncionariLlocManager getFuncionariLlocManager();
	public IFuncionariRolManager getFuncionariRolManager();
	public IHistoricManager getHistoricManager();
	public IHistoricLlocManager getHistoricLlocManager();
	public IIdiomaManager getIdiomaManager();
	public ILlocManager getLlocManager();
	public IPluginManager getPluginManager();
	public IRolManager getRolManager();
	public IScanWebManager getScanWebManager();
	public ITraduccioManager getTraduccioManager();
	public IUnitatManager getUnitatManager();
	public IUsuariManager getUsuariManager();
	public IUsuariEntitatManager getUsuariEntitatManager();

}