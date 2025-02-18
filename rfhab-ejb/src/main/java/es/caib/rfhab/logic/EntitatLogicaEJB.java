package es.caib.rfhab.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import es.caib.rfhab.ejb.EntitatEJB;
import es.caib.rfhab.model.fields.EntitatFields;
import es.caib.rfhab.model.fields.UnitatFields;
import es.caib.rfhab.persistence.EntitatJPA;

/**
 * 
 * @autor jagarcia
 *
 */

@Stateless
public class EntitatLogicaEJB extends EntitatEJB implements EntitatLogicaService {

	@Override
	@PermitAll
	public long findEntitatByDir3(String dir3) {

		TypedQuery<EntitatJPA> query = getEntityManager().createQuery("SELECT e FROM EntitatJPA e WHERE e."
				+ EntitatFields.UNITATID.javaName + " IN ( SELECT u." + UnitatFields.UNITATID.javaName  + " FROM UnitatJPA u WHERE u." + UnitatFields.CODI.javaName + " = :dir3)",
				EntitatJPA.class);
		query.setParameter("dir3", dir3);

		List<EntitatJPA> entitats = query.getResultList();

		return (entitats.size() > 0) ? entitats.get(0).getEntitatID() : -1;

	}

}
