package es.caib.rfhab.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.fundaciobit.genapp.common.KeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SelectMultipleKeyValue;

import es.caib.rfhab.ejb.EntitatEJB;
import es.caib.rfhab.model.fields.EntitatFields;
import es.caib.rfhab.model.fields.EntitatQueryPath;
import es.caib.rfhab.model.fields.UnitatFields;
import es.caib.rfhab.persistence.EntitatJPA;

/**
 * 
 * @autor jagarcia
 * @autor jpou
 *
 */
@Stateless
public class EntitatLogicaEJB extends EntitatEJB implements EntitatLogicaService {

	@Override
	@PermitAll
	public long findEntitatByDir3(String dir3) {

		TypedQuery<EntitatJPA> query = getEntityManager().createQuery("SELECT e FROM EntitatJPA e WHERE e."
				+ EntitatFields.UNITATID.javaName + " IN ( SELECT u." + UnitatFields.UNITATID.javaName
				+ " FROM UnitatJPA u WHERE u." + UnitatFields.CODI.javaName + " = :dir3)",
				EntitatJPA.class);
		query.setParameter("dir3", dir3);

		List<EntitatJPA> entitats = query.getResultList();

		return (entitats.size() > 0) ? entitats.get(0).getEntitatID() : -1;
	}

	@Override
	@PermitAll
	public EntitatJPA findByPrimaryKey(Long _ID_) {
		return (EntitatJPA) super.findByPrimaryKey(_ID_);
	}

	@Override
	@PermitAll
	public String findCodiDir3ByEntitatId(Long entitatId) throws I18NException {
		// select *
		// from rfh_unitat ru
		// inner join rfh_entitat re on ru.unitatid = re.unitatid;
		SelectMultipleKeyValue<Long> smskv;
		final String SEPARATOR = "##";
		smskv = new SelectMultipleKeyValue<Long>(EntitatFields.ENTITATID.select, SEPARATOR,
				new EntitatQueryPath().UNITAT().CODI().select);
		List<KeyValue<Long>> list;
		list = executeQuery(smskv);
		for (KeyValue<Long> kv : list) {
			if (kv.getKey().equals(entitatId)) {
				return kv.getValue();
			}
		}
		return null;

		// EntitatQueryPath entitatQueryPath = new EntitatQueryPath();
		// Where unitatJoin =
		// entitatQueryPath.UNITAT().UNITATID().equal(UnitatFields.UNITATID);
		// Where entitatWhere = entitatQueryPath.ENTITATID().equal(entitatId);
		// java.util.List<Unitat> result = select(Where.AND(unitatJoin, entitatWhere));
		// return result.isEmpty() ? null : (UnitatJPA) result.get(0);
	}
}
