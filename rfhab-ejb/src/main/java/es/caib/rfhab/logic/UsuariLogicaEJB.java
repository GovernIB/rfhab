package es.caib.rfhab.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.UsuariEJB;
import es.caib.rfhab.model.fields.EntitatFields;
import es.caib.rfhab.model.fields.UsuariEntitatFields;
import es.caib.rfhab.model.fields.UsuariFields;
import es.caib.rfhab.persistence.UsuariJPA;

/**
 * 
 * @author jagarcia
 *
 */

@Stateless 
public class UsuariLogicaEJB extends UsuariEJB implements UsuariLogicaService {

	@Override
	@PermitAll
	public UsuariJPA crearUsuari(UsuariJPA usuario) throws I18NException, EJBException {

		try {
			create(usuario);	
		} catch(Throwable th) {
			
			log.error(" ==============================================" );
			log.error(" TIPUS EXCEPCIO: " + th.getClass());
			log.error(th.getMessage(), th);
			
			if (th instanceof I18NException) {
				throw (I18NException)th;
			} else {
				throw new I18NException("comodi", th.getMessage());
			}
			
		}
		
		return usuario;
	}

	@Override
	@PermitAll
	public UsuariJPA findByUsername(String username) throws I18NException {
		
		TypedQuery<UsuariJPA> query = getEntityManager().createQuery(
				"select u from UsuariJPA u LEFT JOIN FETCH u." + UsuariEntitatFields._TABLE_MODEL +"s ue LEFT JOIN FETCH ue." + EntitatFields._TABLE_MODEL + " e where u."+ UsuariFields.USERNAME.javaName  +" = :username", UsuariJPA.class);
		query.setParameter("username", username);
		
		List<UsuariJPA> resultats = query.getResultList();
		return (resultats.size() > 0) ? resultats.get(0) : null;
		
	}

	@Override
	@PermitAll
	public UsuariJPA findByNif(String nif) throws I18NException {

		TypedQuery<UsuariJPA> query = getEntityManager().createQuery(
				"select u from UsuariJPA u "
				+ "left join fetch u." + EntitatFields._TABLE_MODEL
				+ " where u."+ UsuariFields.NIF.javaName  +" = :nif", UsuariJPA.class);
		query.setParameter("nif", nif);
		
		List<UsuariJPA> resultats = query.getResultList();
		return (resultats.size() > 0) ? resultats.get(0) : null;
		
	}

	@Override
	@PermitAll
	public UsuariJPA update(UsuariJPA usuari) throws I18NException, EJBException {
		super.update(usuari);
		return usuari;
	}
	
}
