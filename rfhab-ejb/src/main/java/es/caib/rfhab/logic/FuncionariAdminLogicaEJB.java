package es.caib.rfhab.logic;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import es.caib.rfhab.commons.utils.Constants;
import es.caib.rfhab.ejb.FuncionariEJB;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.persistence.FuncionariJPA;

/**
 * 
 * @autor jpou
 *
 */

@Stateless
public class FuncionariAdminLogicaEJB extends FuncionariEJB implements FuncionariAdminLogicaService {

	@Override
	@RolesAllowed({ Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS })
	public String getMaxFuncionariNumero() throws SecurityException, NoSuchFieldException {

		StringBuilder queryString = new StringBuilder(
				"select max(rf." + FuncionariFields.NUMERO.javaName + ") from " + FuncionariJPA.class.getName()
						+ " rf where rf." + FuncionariFields.NUMERO.javaName + " like '" + Constants.SQL_NUMERO_PATTERN
						+ "' escape '" + Constants.SQL_LIKE_ESCAPE_PATTERN + "'");
		Class<?> numeroClass = FuncionariFields.NUMERO.getClass().getField("javaName").getType();//TODO:comentar a anadal que getJavaClass no funciona perquè javaClass és javaName
		TypedQuery<?> query = getEntityManager().createQuery(queryString.toString(),
				numeroClass);
		List<?> resultats = query.getResultList();

		return (!resultats.isEmpty()) ? (resultats.get(0).toString()) : null;
	}
}
