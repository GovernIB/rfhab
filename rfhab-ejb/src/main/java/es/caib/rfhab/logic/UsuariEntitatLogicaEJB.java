package es.caib.rfhab.logic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.UsuariEntitatEJB;
import es.caib.rfhab.model.entity.UsuariEntitat;
import es.caib.rfhab.model.fields.UsuariEntitatFields;
import es.caib.rfhab.persistence.EntitatJPA;
import es.caib.rfhab.persistence.UsuariEntitatJPA;

/**
 * 
 * @autor jagarcia
 *
 */

@Stateless
public class UsuariEntitatLogicaEJB extends UsuariEntitatEJB implements UsuariEntitatLogicaService {

	private static final long serialVersionUID = 1536000901655993565L;

	@Override
	@PermitAll
	public List<UsuariEntitatJPA> findAllByUsuariId(@NotNull long usuarioID) throws I18NException {

		List<UsuariEntitat> list = select(UsuariEntitatFields.USUARIID.equal(usuarioID));

		List<UsuariEntitatJPA> list2 = new ArrayList<UsuariEntitatJPA>(list.size());
		for (UsuariEntitat usuariEntitat : list) {
			list2.add((UsuariEntitatJPA) usuariEntitat);
			System.out.println("UsuariEntitatJPA: " + usuariEntitat.getUsuariEntitatID() + " - " + usuariEntitat.getEntitatID() + " - " + usuariEntitat.getUsuariID());
		}

		return list2;

	}

	@Override
	@PermitAll
	public List<EntitatJPA> findAllByUsuariIdWithEntitat(@NotNull long usuarioID) throws I18NException {

		StringBuilder query = new StringBuilder(
				"SELECT e FROM EntitatJPA as e WHERE e.entitatID in ( SELECT ue.entitatID From UsuariEntitatJPA as ue WHERE ue.usuariID = :usuarioID)");

		Query q = getEntityManager().createQuery(query.toString());

		q.setParameter("usuarioID", usuarioID);
		
		List<EntitatJPA> list = q.getResultList();
		
		return list;

	}

}
