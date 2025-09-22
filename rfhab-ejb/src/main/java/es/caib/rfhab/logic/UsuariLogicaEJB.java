package es.caib.rfhab.logic;

import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;

import javax.annotation.security.PermitAll;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.rfhab.ejb.UsuariEJB;
import es.caib.rfhab.model.fields.EntitatFields;
import es.caib.rfhab.model.fields.UsuariEntitatFields;
import es.caib.rfhab.model.fields.UsuariFields;
import es.caib.rfhab.persistence.UsuariJPA;

/**
 * 
 * @author jagarcia
 * @author jpou
 *
 */

@Stateless
public class UsuariLogicaEJB extends UsuariEJB implements UsuariLogicaService {

	@Override
	@PermitAll
	public UsuariJPA crearUsuari(UsuariJPA usuario) throws I18NException, EJBException {

		try {
			create(usuario);
		} catch (Throwable th) {

			log.error(" ==============================================");
			log.error(" TIPUS EXCEPCIO: " + th.getClass());
			log.error(th.getMessage(), th);

			if (th instanceof I18NException) {
				throw (I18NException) th;
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
				"select u from UsuariJPA u LEFT JOIN FETCH u." + UsuariEntitatFields._TABLE_MODEL
						+ "s ue LEFT JOIN FETCH ue." + EntitatFields._TABLE_MODEL + " e where u."
						+ UsuariFields.USERNAME.javaName + " = :username",
				UsuariJPA.class);
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
						+ " where u." + UsuariFields.NIF.javaName + " = :nif",
				UsuariJPA.class);
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

	@Override
	@PermitAll
	public String checkIsActiuIteNif(UsuariJPA usuari, String language) throws I18NException {
		String userName = usuari.getUsername();
		String userNif = usuari.getNif();
		if (!usuari.isActiu()) {
			String errorNoActiu = I18NCommonUtils.tradueix(new Locale(language),
					"usuari.error.noactiu",
					new String[] { userName });
			log.error(errorNoActiu);
			// throw new RestException(errorNoActiu, Status.BAD_REQUEST);
			throw new I18NException(errorNoActiu);
		}

		Timestamp usuariDataBaixa = usuari.getDataBaixa();
		Timestamp ara = new Timestamp(System.currentTimeMillis());
		if (usuariDataBaixa != null && usuariDataBaixa.compareTo(ara) <= 0) {
			String errorDonatDeBaixa = I18NCommonUtils.tradueix(new Locale(language),
					"usuari.error.donatdebaixa",
					new String[] { userName });
			log.error(errorDonatDeBaixa);
			throw new I18NException(errorDonatDeBaixa);
		}

		if (userNif == null || userNif.isEmpty()) {
			String errorNoNif = I18NCommonUtils.tradueix(new Locale(language),
					"usuari.error.notenif",
					new String[] { userName });
			log.error(errorNoNif);
			throw new I18NException(errorNoNif);
		}

		return userNif;
	}
}
