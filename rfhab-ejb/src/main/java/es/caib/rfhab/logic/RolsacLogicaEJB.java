package es.caib.rfhab.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.security.PermitAll;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import es.caib.rfhab.commons.utils.Configuracio;
import es.caib.rfhab.pluginsib.rolsac.model.Plantillas;
import es.caib.rfhab.pluginsib.rolsac.model.Procediments;
import es.caib.rfhab.pluginsib.rolsac.model.Tramits;
import es.caib.rfhab.pluginsib.rolsac.model.respostes.RespuestaProcediments;
import es.caib.rfhab.pluginsib.rolsac.model.respostes.RespuestaSimple;
import es.caib.rfhab.pluginsib.rolsac.model.respostes.RespuestaTramits;

/**
 * EJB Singleton per a la integració amb el catàleg de procediments i tràmits de
 * Rolsac.
 * Gestiona internament una caché d'aplicació compartida per a tots els
 * accessos.
 *
 * @author jagarcia
 * @author jpou
 */
// @Stateless
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN) // La caché de Caffeine ja és thread-safe, per tant no cal el
														// bloqueig del contenidor
public class RolsacLogicaEJB implements RolsacLogicaService {

	protected final Logger log = Logger.getLogger(this.getClass());

	private static final String FILTRE_PROCEDIMENTS = "{\"activo\":\"1\",\"telematico\":\"1\",\"disponibleFuncionarioHabilitado\":\"1\"}";
	private static final String DI3_VALUE_FROM_FILTER = "#########";
	private static final String FILTRE_PROCEDIMENTS_BY_DI3 = "{\"activo\":\"1\",\"telematico\":\"1\",\"disponibleFuncionarioHabilitado\":\"1\", \"codigoUADir3\":\""
			+ DI3_VALUE_FROM_FILTER + "\", \"buscarEnDescendientesUA\":\"1\"}";
	private static final String FILTRE_PAGINACIO = "{\"page\":\"1\",\"size\":\"500\"}";

	final String endpoint;
	final String usuari;
	final String pass;
	final long tempsCatxe;

	AsyncLoadingCache<String, HashMap<String, String[]>> procedimentsAllCatxe;
	AsyncLoadingCache<String, HashMap<String, String[]>> tramitsFhAllCatxe;
	AsyncLoadingCache<String, String> codiDir3UnitatsAdministrativesCatxe;

	public RolsacLogicaEJB() {
		super();
		endpoint = Configuracio.getRolsacEndpoint();
		usuari = Configuracio.getRolsacUsername();
		pass = Configuracio.getRolsacPassword();
		tempsCatxe = Configuracio.getRolsacCacheTtl();

		procedimentsAllCatxe = Caffeine.newBuilder()
				.expireAfterWrite(tempsCatxe, TimeUnit.MILLISECONDS)
				.buildAsync(k -> getProcedimentsPerLlenguaFromService(k));

		tramitsFhAllCatxe = Caffeine.newBuilder()
				.expireAfterWrite(tempsCatxe, TimeUnit.MILLISECONDS)
				.buildAsync(k -> getTramitsPerLlenguaFromService(k));

		codiDir3UnitatsAdministrativesCatxe = Caffeine.newBuilder()
				.expireAfterWrite(tempsCatxe, TimeUnit.MILLISECONDS)
				.buildAsync(k -> getCodiDir3PerUnitatAdministrativaFromService(k));
	}

	@Override
	@PermitAll
	public HashMap<String, String[]> obtenirProcedimentsByDir3(String codiDir3, String llengua) throws Exception {
		HashMap<String, String[]> procedimentsPerLlengua = null;
		HashMap<String, String[]> procedimentsTrobats = new HashMap<String, String[]>();
		java.util.concurrent.CompletableFuture<HashMap<String, String[]>> future = procedimentsAllCatxe
				.getIfPresent(llengua);
		if (future != null) {
			procedimentsPerLlengua = future.thenApply(resultats -> {
				if (resultats == null) {
					log.info("No s'han trobat procediments per a la llengua: " + llengua);
					return new HashMap<String, String[]>();
				} else {
					log.info("S'han trobat " + resultats.size() + " procediments per a la llengua: " + llengua);
					return resultats;
				}
			}).get();
		} else {
			procedimentsPerLlengua = new HashMap<String, String[]>();
		}

		for (Map.Entry<String, String[]> entry : procedimentsPerLlengua.entrySet()) {
			String[] values = entry.getValue();
			if (values != null && values.length > 1 && codiDir3.equals(values[3])) {
				procedimentsTrobats.put(entry.getKey(), values);
			}
		}
		if (procedimentsTrobats.isEmpty()) {
			HashMap<String, String[]> resultats = getProcedimentsPerLlenguaFromService(llengua,
					java.util.Arrays.asList(codiDir3));
			if (resultats != null) {
				procedimentsTrobats.putAll(resultats);
				log.info("S'han trobat " + resultats.size() + " procediments per a la unitat amb codi DIR3: "
						+ codiDir3);
				procedimentsPerLlengua.putAll(resultats);
				procedimentsAllCatxe.put(llengua,
						java.util.concurrent.CompletableFuture.completedFuture(procedimentsPerLlengua));
			}
		}
		return procedimentsTrobats;
	}

	@Override
	@PermitAll
	public HashMap<String, String[]> reobtenirProcedimentsAll(String llengua) throws Exception {
		HashMap<String, String[]> procedimentsPerLlengua = getProcedimentsPerLlenguaFromService(llengua);
		procedimentsAllCatxe.put(llengua,
				java.util.concurrent.CompletableFuture.completedFuture(procedimentsPerLlengua));

		if (procedimentsPerLlengua == null) {
			log.info("No s'han trobat procediments per a la llengua: " + llengua);
			return new HashMap<String, String[]>();
		} else {
			log.info("S'han trobat " + procedimentsPerLlengua.size() + " procediments per a la llengua: " + llengua);
			return procedimentsPerLlengua;
		}
	}

	private HashMap<String, String[]> getProcedimentsPerLlenguaFromService(String llengua, List<String> codisDir3)
			throws Exception {
		if (llengua == null || llengua.isEmpty()) {
			llengua = "ca";
		}

		final String entitat = "procedimientos";

		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(usuari, pass));
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

		HashMap<String, String[]> resultats = null;
		for (String codiDir3 : codisDir3) {
			final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("lang", llengua);
			final String filtreProcediments = FILTRE_PROCEDIMENTS_BY_DI3.replace(DI3_VALUE_FROM_FILTER, codiDir3);
			map.add("filtro", filtreProcediments);
			map.add("filtroPaginacion", FILTRE_PAGINACIO);

			final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

			log.info("Cridant a Rolsac: " + endpoint + entitat + " amb filtre: " + filtreProcediments);
			final ResponseEntity<RespuestaProcediments> responseProcedimientos = restTemplate
					.postForEntity(endpoint + entitat, request, RespuestaProcediments.class);

			if (responseProcedimientos != null && responseProcedimientos.getBody() != null) {
				if (!responseProcedimientos.getBody().getStatus().equals("200")
						|| (responseProcedimientos.getBody().getNumeroElementos().equals("0"))) {
					return null;
				} else {
					final List<Procediments> respostaProcediments = responseProcedimientos.getBody().getResultado();
					if (respostaProcediments != null)
						log.info("S'han trobat " + respostaProcediments.size() + " procediments");
					else
						log.info("No s'han trobat procediments");

					if (resultats == null) {
						resultats = new HashMap<String, String[]>();
					}

					if (respostaProcediments != null)
						for (Procediments procediment : respostaProcediments) {
							log.info(procediment.getCodigo() + " - " + procediment.getCodigoSIA() + " - "
									+ procediment.getNombre());
							resultats.put(String.valueOf(procediment.getCodigo()),
									new String[] { procediment.getNombre().replace("'", "`"),
											llengua, procediment.getCodigoSIA(), codiDir3 });
						}
				}
			}
		}
		return resultats;
	}

	private HashMap<String, String[]> getProcedimentsPerLlenguaFromService(String llengua) throws Exception {
		if (llengua == null || llengua.isEmpty()) {
			llengua = "ca";
		}

		final String entitat = "procedimientos";

		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(usuari, pass));
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

		final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("lang", llengua);
		map.add("filtro", FILTRE_PROCEDIMENTS);
		map.add("filtroPaginacion", FILTRE_PAGINACIO);

		final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		log.info("Cridant a Rolsac: " + endpoint + entitat);
		final ResponseEntity<RespuestaProcediments> responseProcedimientos = restTemplate
				.postForEntity(endpoint + entitat, request, RespuestaProcediments.class);

		if (responseProcedimientos != null && responseProcedimientos.getBody() != null) {
			if (!responseProcedimientos.getBody().getStatus().equals("200")
					|| (responseProcedimientos.getBody().getNumeroElementos().equals("0"))) {
				return null;
			} else {
				final List<Procediments> respostaProcediments = responseProcedimientos.getBody().getResultado();
				if (respostaProcediments != null)
					log.info("S'han trobat " + respostaProcediments.size() + " procediments");
				else
					log.info("No s'han trobat procediments");

				HashMap<String, String[]> resultats = new HashMap<String, String[]>();

				if (respostaProcediments != null)
					for (Procediments procediment : respostaProcediments) {
						log.info(procediment.getCodigo() + " - " + procediment.getCodigoSIA() + " - "
								+ procediment.getNombre());
						resultats.put(String.valueOf(procediment.getCodigo()),
								new String[] { procediment.getNombre().replace("'", "`"),
										llengua, procediment.getCodigoSIA(), null });
					}

				return resultats;
			}
		}
		return null;
	}

	@Override
	@PermitAll
	public HashMap<String, String[]> obtenirTramits(String procedimentId, String llengua) throws Exception {
		HashMap<String, String[]> tramitsPerLlengua = null;
		HashMap<String, String[]> tramitsTrobats = new HashMap<String, String[]>();
		java.util.concurrent.CompletableFuture<HashMap<String, String[]>> future = tramitsFhAllCatxe
				.getIfPresent(llengua);
		if (future != null) {
			tramitsPerLlengua = future.thenApply(resultats -> {
				if (resultats == null) {
					log.info("No s'han trobat tràmits per a la llengua: " + llengua);
					return new HashMap<String, String[]>();
				} else {
					log.info("S'han trobat " + resultats.size() + " tràmits per a la llengua: " + llengua);
					return resultats;
				}
			}).get();
		} else {
			tramitsPerLlengua = new HashMap<String, String[]>();
		}

		for (Map.Entry<String, String[]> entry : tramitsPerLlengua.entrySet()) {
			String[] values = entry.getValue();
			if (values != null && values.length > 1 && procedimentId.equals(values[1])) {
				tramitsTrobats.put(entry.getKey(), values);
			}
		}
		if (tramitsTrobats.isEmpty()) {
			HashMap<String, String[]> resultats = getTramitsPerLlenguaFromService(procedimentId, llengua);
			if (resultats != null) {
				tramitsTrobats.putAll(resultats);
				log.info("S'han trobat " + resultats.size() + " tràmits per al procediment: " + procedimentId);
				tramitsPerLlengua.putAll(resultats);
				tramitsFhAllCatxe.put(llengua,
						java.util.concurrent.CompletableFuture.completedFuture(tramitsPerLlengua));
			}
		}
		return tramitsTrobats;
	}

	@Override
	@PermitAll
	public Tramits obtenirTramitPerId(String tramitId, String llengua) throws Exception {
		if (tramitId == null || tramitId.isEmpty()) {
			log.warn("El codi del tràmit no pot ser null o buit");
			return null;
		}

		final String entitat = "tramites/" + tramitId;

		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(usuari, pass));
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

		final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("lang", llengua);

		final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		log.info("Cridant a Rolsac: " + endpoint + entitat);

		final ResponseEntity<RespuestaTramits> responseTramits = restTemplate
				.postForEntity(endpoint + entitat, request, RespuestaTramits.class);

		if (responseTramits != null && responseTramits.getBody() != null) {
			if (!responseTramits.getBody().getStatus().equals("200")) {
				log.warn("Error en la resposta de Rolsac: status=" + responseTramits.getBody().getStatus());
				return null;
			} else {
				final List<Tramits> tramit = responseTramits.getBody().getResultado();
				if (tramit != null && !tramit.isEmpty()) {
					log.info("S'ha trobat el tramit: " + tramit + " per al codi: " + tramitId);
					return tramit.get(0);
				} else {
					log.info("No s'ha trobat el tramit: " + tramitId);
					return null;
				}
			}
		}

		log.warn("Resposta nul·la de Rolsac per al tràmit: " + tramitId);
		return null;
	}

	private HashMap<String, String[]> getTramitsPerLlenguaFromService(String procedimentId, String llengua)
			throws Exception {
		if (llengua == null || llengua.isEmpty()) {
			llengua = "ca";
		}
		if (procedimentId == null) {
			procedimentId = "";
		}

		final String entitat = "tramites";

		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(usuari, pass));
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

		final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("idioma", llengua);
		final String filtreTramits = "{\"codigoProcedimiento\":\"" + procedimentId + "\"}";
		map.add("filtro", filtreTramits);
		map.add("filtroOrdenacion", FILTRE_PAGINACIO);

		final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		log.info("Cridant a Rolsac: " + endpoint + entitat);
		final ResponseEntity<RespuestaTramits> responseTramites = restTemplate.postForEntity(endpoint + entitat,
				request, RespuestaTramits.class);

		if (responseTramites != null && responseTramites.getBody() != null) {
			if (!responseTramites.getBody().getStatus().equals("200")
					|| (responseTramites.getBody().getNumeroElementos().equals("0"))) {
				return null;
			} else {
				final List<Tramits> respostaTramits = responseTramites.getBody().getResultado();
				log.info("S'han trobat " + respostaTramits.size() + " tramits");

				HashMap<String, String[]> resultats = new HashMap<String, String[]>();

				for (Tramits tramit : respostaTramits) {
					log.info("Tràmit trobat - Codi: " + tramit.getCodigo() +
							", Nom: " + tramit.getNombre() +
							", Codi Procediment: " + tramit.getLink_procedimiento().getCodigo() +
							", Llengua: " + llengua +
							", Versió: " + tramit.getVersio() +
							", Paràmetres: " + tramit.getParametros() +
							", IdTraTel: " + tramit.getIdTraTel() +
							", Codi Organ Competent: " + tramit.getLink_organCompetent().getCodigo());
					Plantillas plantillaTramit = tramit.getPlantilla();
					resultats.put(String.valueOf(tramit.getCodigo()),
							new String[] {
									(plantillaTramit != null && plantillaTramit.getNombre() != null)
											? plantillaTramit.getNombre().replace("'", "`")
											: tramit.getNombre().replace("'", "`"),
									tramit.getLink_procedimiento().getCodigo(),
									llengua,
									String.valueOf(tramit.getCodigo()),
									(plantillaTramit != null && plantillaTramit.getVersion() != null)
											? String.valueOf(plantillaTramit.getVersion())
											: String.valueOf(tramit.getVersio()),
									(plantillaTramit != null && plantillaTramit.getParametros() != null)
											? String.valueOf(plantillaTramit.getParametros())
											: String.valueOf(tramit.getParametros()),
									(plantillaTramit != null && plantillaTramit.getIdentificador() != null)
											? String.valueOf(plantillaTramit.getIdentificador().trim())
											: String.valueOf(tramit.getIdTraTel().trim()),
									tramit.getLink_organCompetent().getCodigo()
							});
				}

				return resultats;
			}
		}

		return null;
	}

	@Override
	@PermitAll
	public HashMap<String, String[]> obtenirTramitsAll(String llengua) throws Exception {
		return tramitsFhAllCatxe.get(llengua).thenApply(resultats -> {
			if (resultats == null) {
				log.info("No s'han trobat tramits per a la llengua: " + llengua);
				return new HashMap<String, String[]>();
			} else {
				log.info("S'han trobat " + resultats.size() + " tramits per a la llengua: " + llengua);
				return resultats;
			}
		}).get();
	}

	private HashMap<String, String[]> getTramitsPerLlenguaFromService(String llengua) throws Exception {
		if (llengua == null || llengua.isEmpty()) {
			llengua = "ca";
		}

		return getTramitsPerLlenguaFromService(null, llengua);
	}

	@Override
	@PermitAll
	public String getCodiDir3UnitatAdministrativa(String codi) throws InterruptedException, ExecutionException {
		if (codi == null || codi.isEmpty()) {
			log.warn("El codi de la unitat administrativa no pot ser null o buit");
			return null;
		}

		return codiDir3UnitatsAdministrativesCatxe.get(codi).thenApply(resultat -> {
			if (resultat == null || resultat.isEmpty()) {
				log.info("No s'ha trobat el codiDir3 per a la unitat administrativa: " + codi);
				return null;
			} else {
				log.info("S'ha trobat el codiDir3: " + resultat + " per a la unitat administrativa: " + codi);
				return resultat;
			}
		}).get();
	}

	private String getCodiDir3PerUnitatAdministrativaFromService(String codi) throws Exception {
		if (codi == null || codi.isEmpty()) {
			log.warn("El codi de la unitat administrativa no pot ser null o buit");
			return null;
		}

		final String entitat = "unidades_administrativas/codigoDir3/" + codi;

		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(usuari, pass));
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

		final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

		final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		log.info("Cridant a Rolsac: " + endpoint + entitat);

		final ResponseEntity<RespuestaSimple> responseUnitatAdministrativa = restTemplate
				.postForEntity(endpoint + entitat, request, RespuestaSimple.class);

		if (responseUnitatAdministrativa != null && responseUnitatAdministrativa.getBody() != null) {
			if (!responseUnitatAdministrativa.getBody().getStatus().equals("200")) {
				log.warn(
						"Error en la resposta de Rolsac: status=" + responseUnitatAdministrativa.getBody().getStatus());
				return null;
			} else {
				final String codiDir3 = responseUnitatAdministrativa.getBody().getResultado();
				if (codiDir3 != null && !codiDir3.isEmpty()) {
					log.info("S'ha trobat el codiDir3: " + codiDir3 + " per a la unitat administrativa: " + codi);
					return codiDir3;
				} else {
					log.info("No s'ha trobat el codiDir3 per a la unitat administrativa: " + codi);
					return null;
				}
			}
		}

		log.warn("Resposta nul·la de Rolsac per a la unitat administrativa: " + codi);
		return null;
	}
}
