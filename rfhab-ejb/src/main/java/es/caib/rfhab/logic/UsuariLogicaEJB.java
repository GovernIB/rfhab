package es.caib.rfhab.logic;

import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.commons.utils.IdentificacioTipus;
import es.caib.rfhab.commons.utils.RegistreActivitatTipus;
import es.caib.rfhab.ejb.UsuariEJB;
import es.caib.rfhab.logic.utils.RegistreActivitatService.RegistreActivitatValidator;
import es.caib.rfhab.logic.utils.TicketAccesDto.RpersonaInfo;
import es.caib.rfhab.model.entity.Activitat;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.entity.Usuari;
import es.caib.rfhab.model.fields.EntitatFields;
import es.caib.rfhab.model.fields.UsuariEntitatFields;
import es.caib.rfhab.model.fields.UsuariFields;
import es.caib.rfhab.persistence.UsuariJPA;
import es.caib.rfhab.persistence.validator.ActivitatValidator;
import es.caib.rfhab.pluginsib.rolsac.RolsacPlugin;

/**
 * 
 * @author jagarcia
 * @author jpou
 *
 */

@Stateless
public class UsuariLogicaEJB extends UsuariEJB implements UsuariLogicaService {

	@EJB(mappedName = ActivitatLogicaService.JNDI_NAME)
	protected ActivitatLogicaService activitatLogicaEjb;

	@EJB(mappedName = SistramitLogicaService.JNDI_NAME)
	protected SistramitLogicaService sistramitLogicaEjb;

	protected ActivitatValidator<Activitat> activitatValidator = new RegistreActivitatValidator();

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
	public String checkIsActiuIteNif(Integer usuariId, String language) throws I18NException {
		Usuari usuari = findByPrimaryKey(usuariId);
		if (usuari == null) {
			String errorNoActiu = I18NCommonUtils.tradueix(new Locale(language),
					"error.notfound",
					new String[] { I18NCommonUtils.tradueix(new Locale(language), "usuari.usuari"),
							I18NCommonUtils.tradueix(new Locale(language), "usuari.usuariID"),
							String.valueOf(usuariId) });
			log.error(errorNoActiu);
			throw new I18NException(errorNoActiu);
		}

		return checkIsActiuIteNif(usuari, language);
	}

	@Override
	@PermitAll
	public String checkIsActiuIteNif(Usuari usuari, String language) throws I18NException {
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

	/**
	 * Retorna el ticket d'accés per a fer un tràmit a SISTRA.
	 * 
	 * @return String amb el ticket d'accés.
	 * @throws I18NException
	 */
	@Override
	@PermitAll
	public String registraActivitatIobteTicketAccessFh(Funcionari funcionari, String codiDir3, RpersonaInfo interessat,
			RpersonaInfo representant, String idTramiteCatalogo, String ticketLanguage, String ticketParametros,
			boolean servicioCatalogo, String tramite, String tramitVersio, String unitatAdministrativa,
			Timestamp dataActivitat, String procediment, String arxiuExpedientId, String arxiuDocumentId,
			RolsacPlugin rolsacPlugin)
			throws I18NException {
		try {
			String idActuacioTramitFh = java.util.UUID.randomUUID().toString();
			Activitat newAct = activitatLogicaEjb.registraNovaActivitat(ticketLanguage, activitatValidator,
					RegistreActivitatTipus.COMPAREIX, null,
					null,
					idActuacioTramitFh, tramite, tramitVersio, procediment, unitatAdministrativa,
					interessat.getNombre(), interessat.getApellido1(), interessat.getApellido2(),
					IdentificacioTipus.DNI,
					interessat.getNif(), representant != null ? representant.getNombre() : null,
					representant != null ? representant.getApellido1() : null,
					representant != null ? representant.getApellido2() : null,
					representant != null ? IdentificacioTipus.DNI : null,
					representant != null ? representant.getNif() : null, arxiuExpedientId, arxiuDocumentId,
					dataActivitat, funcionari, funcionari.getEntitatID(), rolsacPlugin);

			return sistramitLogicaEjb.getTicketAccesoFh(funcionari, codiDir3, interessat, representant,
					idTramiteCatalogo, ticketLanguage, ticketParametros, servicioCatalogo, tramite,
					Integer.valueOf(tramitVersio),
					idActuacioTramitFh);

		} catch (Exception e) {
			log.error("Error registrant nova activitat de INICI tràmit. Error: " + e.getMessage());
			throw e;
		}
	}

}
