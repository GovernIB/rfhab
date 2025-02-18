package es.caib.rfhab.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.IdiomaEJB;
import es.caib.rfhab.model.fields.IdiomaFields;
import es.caib.rfhab.persistence.IdiomaJPA;

/**
 * 
 * @author jagarcia
 * 
 */

@Stateless
public class IdiomaLogicaEJB extends IdiomaEJB implements IdiomaLogicaService{
	
	@Override
	@PermitAll
	public IdiomaJPA findByCodi(String codi) throws I18NException {
		
		TypedQuery<IdiomaJPA> query = getEntityManager().createQuery(
				"select i from IdiomaJPA i " + "where i." + IdiomaFields.NOM.javaName  +" = :codi", IdiomaJPA.class
		);
		query.setParameter("codi", codi);
		
		List<IdiomaJPA> resultats = query.getResultList();
		
		return (resultats.size() > 0) ? resultats.get(0) : null;
	}
	
}
