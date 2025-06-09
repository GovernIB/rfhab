package es.caib.rfhab.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.UsuariService;
import es.caib.rfhab.persistence.UsuariJPA;

/**
 * 
 * @author jagarcia
 *
 */
@Local
public interface UsuariLogicaService extends UsuariService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/UsuariLogicaEJB!es.caib.rfhab.logic.UsuariLogicaService";

	public UsuariJPA crearUsuari(UsuariJPA usuario) throws I18NException, javax.ejb.EJBException;

	public UsuariJPA findByUsername(String username) throws I18NException;

	public UsuariJPA findByNif(String nif) throws I18NException;

	public UsuariJPA update(UsuariJPA usuari) throws I18NException, javax.ejb.EJBException;

}
