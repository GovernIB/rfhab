package es.caib.rfhab.logic;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import javax.ejb.Local;

import es.caib.rfhab.pluginsib.rolsac.model.Tramits;

/**
 * Servei EJB per a la integració amb el catàleg de procediments i tràmits de Rolsac.
 *
 * @author jagarcia
 * @author jpou
 */
@Local
public interface RolsacLogicaService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/RolsacLogicaEJB!es.caib.rfhab.logic.RolsacLogicaService";

	public HashMap<String, String[]> obtenirProcedimentsByDir3(String codiDir3, String llengua) throws Exception;

	public HashMap<String, String[]> reobtenirProcedimentsAll(String llengua) throws Exception;

	public HashMap<String, String[]> obtenirTramits(String procedimentId, String llengua) throws Exception;

	public HashMap<String, String[]> obtenirTramitsAll(String llengua) throws Exception;

	public String getCodiDir3UnitatAdministrativa(String codi) throws InterruptedException, ExecutionException;

	public Tramits obtenirTramitPerId(String tramitId, String llengua) throws Exception;
}
